/*!
 * Lc4e Javascript Library
 * author zhuxi - v1.0.0 (2015-04-09T11:23:51+0800)
 * http://www.lc4e.com/ | Released under MIT license
 * 
 * Include jquery (http://jquery.com/) semantic-ui (http://semantic-ui.com/)  animatescroll
 * pjax (https://github.com/defunkt/jquery-pjax)
 */

(function ($) {

    // pjax
    function fnPjax(selector, container, options) {
        var context = this;
        return this.on('click.pjax', selector, function (event) {
            var opts = $.extend({}, optionsFor(container, options))
            if (!opts.container)
                opts.container = $(this).attr('data-pjax') || context
            handleClick(event, opts)
        });
    }

    function handleClick(event, container, options) {
        options = optionsFor(container, options);

        var link = event.currentTarget;

        if (link.tagName.toUpperCase() !== 'A')
            throw "$.fn.pjax or $.pjax.click requires an anchor element";

        if (event.which > 1 || event.metaKey || event.ctrlKey || event.shiftKey || event.altKey)
            return;

        if (location.protocol !== link.protocol || location.hostname !== link.hostname)
            return;

        if (link.href.indexOf('#') > -1 && stripHash(link) == stripHash(location))
            return;

        if (event.isDefaultPrevented())
            return;

        var defaults = {
            url: link.href,
            container: $(link).attr('data-pjax'),
            target: link
        };

        var opts = $.extend({}, defaults, options);
        var clickEvent = $.Event('pjax:click');
        $(link).trigger(clickEvent, [opts]);

        if (!clickEvent.isDefaultPrevented()) {
            pjax(opts);
            event.preventDefault();
            $(link).trigger('pjax:clicked', [opts]);
        }
    }

    function handleSubmit(event, container, options) {
        options = optionsFor(container, options);

        var form = event.currentTarget;

        if (form.tagName.toUpperCase() !== 'FORM')
            throw "$.pjax.submit requires a form element";

        var defaults = {
            type: form.method.toUpperCase(),
            url: form.action,
            container: $(form).attr('data-pjax'),
            target: form
        }

        if (defaults.type !== 'GET' && window.FormData !== undefined) {
            defaults.data = new FormData(form);
            defaults.processData = false;
            defaults.contentType = false;
        } else {

            if ($(form).find(':file').length) {
                return;
            }

            defaults.data = $(form).serializeArray();
        }

        pjax($.extend({}, defaults, options));

        event.preventDefault();
    }

    function pjax(options) {
        options = $.extend(true, {}, $.ajaxSettings, pjax.defaults, options);

        if ($.isFunction(options.url)) {
            options.url = options.url();
        }

        var target = options.target;

        var hash = parseURL(options.url).hash;

        var context = options.context = findContainerFor(options.container);

        if (!options.data)
            options.data = {};
        if ($.isArray(options.data)) {
            options.data.push({
                name: '_pjax',
                value: context.selector
            });
        } else {
            options.data._pjax = context.selector;
        }

        function fire(type, args, props) {
            if (!props)
                props = {};
            props.relatedTarget = target;
            var event = $.Event(type, props);
            context.trigger(event, args);
            return !event.isDefaultPrevented();
        }

        var timeoutTimer;

        options.beforeSend = function (xhr, settings) {

            if (settings.type !== 'GET') {
                settings.timeout = 0;
            }

            xhr.setRequestHeader('X-PJAX', 'true');
            xhr.setRequestHeader('X-PJAX-Container', context.selector);

            if (!fire('pjax:beforeSend', [xhr, settings]))
                return false;

            if (settings.timeout > 0) {
                timeoutTimer = setTimeout(function () {
                    if (fire('pjax:timeout', [xhr, options]))
                        xhr.abort('timeout')
                }, settings.timeout);

                // Clear timeout setting so jquerys internal timeout
                // isn't invoked
                settings.timeout = 0;
            }

            var url = parseURL(settings.url);
            if (hash)
                url.hash = hash;
            options.requestUrl = stripInternalParams(url);
        }

        options.complete = function (xhr, textStatus) {
            if (timeoutTimer)
                clearTimeout(timeoutTimer);

            fire('pjax:complete', [xhr, textStatus, options]);

            fire('pjax:end', [xhr, options]);
        }

        options.error = function (xhr, textStatus, errorThrown) {
            var container = extractContainer("", xhr, options);

            var allowed = fire('pjax:error', [xhr, textStatus, errorThrown, options]);
            if (options.type == 'GET' && textStatus !== 'abort' && allowed) {
                locationReplace(container.url);
            }
        }

        options.success = function (data, status, xhr) {
            var previousState = pjax.state;

            var currentVersion = (typeof $.pjax.defaults.version === 'function') ? $.pjax.defaults.version() : $.pjax.defaults.version;

            var latestVersion = xhr.getResponseHeader('X-PJAX-Version');

            var container = extractContainer(data, xhr, options);

            var url = parseURL(container.url);
            if (hash) {
                url.hash = hash;
                container.url = url.href;
            }

            if (currentVersion && latestVersion && currentVersion !== latestVersion) {
                locationReplace(container.url);
                return;
            }

            if (!container.contents) {
                locationReplace(container.url);
                return;
            }

            pjax.state = {
                id: options.id || uniqueId(),
                url: container.url,
                title: container.title,
                container: context.selector,
                fragment: options.fragment,
                timeout: options.timeout
            }

            if (options.push || options.replace) {
                window.history.replaceState(pjax.state, container.title, container.url);
            }

            try {
                document.activeElement.blur();
            } catch (e) {
            }

            if (container.title)
                document.title = container.title;

            fire('pjax:beforeReplace', [container.contents, options], {
                state: pjax.state,
                previousState: previousState
            });
            context.html(container.contents);

            var autofocusEl = context.find('input[autofocus], textarea[autofocus]').last()[0];
            if (autofocusEl && document.activeElement !== autofocusEl) {
                autofocusEl.focus();
            }

            executeScriptTags(container.scripts);

            var scrollTo = options.scrollTo;

            if (hash) {
                var name = decodeURIComponent(hash.slice(1));
                var target = document.getElementById(name) || document.getElementsByName(name)[0];
                if (target)
                    scrollTo = $(target).offset().top;
            }

            if (typeof scrollTo == 'number')
                $(window).scrollTop(scrollTo);

            fire('pjax:success', [data, status, xhr, options]);
        }

        if (!pjax.state) {
            pjax.state = {
                id: uniqueId(),
                url: window.location.href,
                title: document.title,
                container: context.selector,
                fragment: options.fragment,
                timeout: options.timeout
            };
            window.history.replaceState(pjax.state, document.title);
        }

        abortXHR(pjax.xhr);

        pjax.options = options;
        var xhr = pjax.xhr = $.ajax(options);

        if (xhr.readyState > 0) {
            if (options.push && !options.replace) {

                cachePush(pjax.state.id, cloneContents(context));

                window.history.pushState(null, "", options.requestUrl);
            }

            fire('pjax:start', [xhr, options]);
            fire('pjax:send', [xhr, options]);
        }

        return pjax.xhr;
    }

    function pjaxReload(container, options) {
        var defaults = {
            url: window.location.href,
            push: false,
            replace: true,
            scrollTo: false
        };

        return pjax($.extend(defaults, optionsFor(container, options)));
    }

    function locationReplace(url) {
        window.history.replaceState(null, "", pjax.state.url);
        window.location.replace(url);
    }

    var initialPop = true
    var initialURL = window.location.href;
    var initialState = window.history.state;

    if (initialState && initialState.container) {
        pjax.state = initialState;
    }

    if ('state' in window.history) {
        initialPop = false;
    }

    function onPjaxPopstate(event) {

        if (!initialPop) {
            abortXHR(pjax.xhr);
        }

        var previousState = pjax.state;
        var state = event.state;
        var direction;

        if (state && state.container) {

            if (initialPop && initialURL == state.url)
                return;

            if (previousState) {

                if (previousState.id === state.id)
                    return;

                direction = previousState.id < state.id ? 'forward' : 'back';
            }

            var cache = cacheMapping[state.id] || [];
            var container = $(cache[0] || state.container), contents = cache[1];

            if (container.length) {
                if (previousState) {

                    cachePop(direction, previousState.id, cloneContents(container));
                }

                var popstateEvent = $.Event('pjax:popstate', {
                    state: state,
                    direction: direction
                });
                container.trigger(popstateEvent);

                var options = {
                    id: state.id,
                    url: state.url,
                    container: container,
                    push: false,
                    fragment: state.fragment,
                    timeout: state.timeout,
                    scrollTo: false
                };

                if (contents) {
                    container.trigger('pjax:start', [null, options]);

                    pjax.state = state;
                    if (state.title)
                        document.title = state.title;
                    var beforeReplaceEvent = $.Event('pjax:beforeReplace', {
                        state: state,
                        previousState: previousState
                    });
                    container.trigger(beforeReplaceEvent, [contents, options]);
                    container.html(contents);

                    container.trigger('pjax:end', [null, options]);
                } else {
                    pjax(options);
                }

                container[0].offsetHeight;
            } else {
                locationReplace(location.href);
            }
        }
        initialPop = false;
    }

    function fallbackPjax(options) {
        var url = $.isFunction(options.url) ? options.url() : options.url, method = options.type ? options.type.toUpperCase() : 'GET';

        var form = $('<form>', {
            method: method === 'GET' ? 'GET' : 'POST',
            action: url,
            style: 'display:none'
        });

        if (method !== 'GET' && method !== 'POST') {
            form.append($('<input>', {
                type: 'hidden',
                name: '_method',
                value: method.toLowerCase()
            }));
        }

        var data = options.data;
        if (typeof data === 'string') {
            $.each(data.split('&'), function (index, value) {
                var pair = value.split('=');
                form.append($('<input>', {
                    type: 'hidden',
                    name: pair[0],
                    value: pair[1]
                }));
            });
        } else if ($.isArray(data)) {
            $.each(data, function (index, value) {
                form.append($('<input>', {
                    type: 'hidden',
                    name: value.name,
                    value: value.value
                }));
            });
        } else if (typeof data === 'object') {
            var key;
            for (key in data)
                form.append($('<input>', {
                    type: 'hidden',
                    name: key,
                    value: data[key]
                }))
        }

        $(document.body).append(form);
        form.submit();
    }

    function abortXHR(xhr) {
        if (xhr && xhr.readyState < 4) {
            xhr.onreadystatechange = $.noop;
            xhr.abort();
        }
    }

    function uniqueId() {
        return (new Date).getTime();
    }

    function cloneContents(container) {
        var cloned = container.clone();

        cloned.find('script').each(function () {
            if (!this.src)
                jQuery._data(this, 'globalEval', false);
        })
        return [container.selector, cloned.contents()];
    }

    function stripInternalParams(url) {
        url.search = url.search.replace(/([?&])(_pjax|_)=[^&]*/g, '');
        return url.href.replace(/\?($|#)/, '$1');
    }

    function parseURL(url) {
        var a = document.createElement('a');
        a.href = url;
        return a;
    }

    function stripHash(location) {
        return location.href.replace(/#.*/, '');
    }

    function optionsFor(container, options) {

        if (container && options)
            options.container = container;

        else if ($.isPlainObject(container))
            options = container;

        else
            options = {
                container: container
            };

        if (options.container)
            options.container = findContainerFor(options.container);

        return options;
    }

    function findContainerFor(container) {
        container = $(container);

        if (!container.length) {
            throw "no pjax container for " + container.selector;
        } else if (container.selector !== '' && container.context === document) {
            return container;
        } else if (container.attr('id')) {
            return $('#' + container.attr('id'));
        } else {
            throw "cant get selector for pjax container!";
        }
    }

    function findAll(elems, selector) {
        return elems.filter(selector).add(elems.find(selector));
    }

    function parseHTML(html) {
        return $.parseHTML(html, document, true);
    }

    function extractContainer(data, xhr, options) {
        var obj = {}, fullDocument = /<html/i.test(data);

        var serverUrl = xhr.getResponseHeader('X-PJAX-URL');
        obj.url = serverUrl ? stripInternalParams(parseURL(serverUrl)) : options.requestUrl;

        if (fullDocument) {
            var $head = $(parseHTML(data.match(/<head[^>]*>([\s\S.]*)<\/head>/i)[0]));
            var $body = $(parseHTML(data.match(/<body[^>]*>([\s\S.]*)<\/body>/i)[0]));
        } else {
            var $head = $body = $(parseHTML(data));
        }

        if ($body.length === 0)
            return obj;

        obj.title = findAll($head, 'title').last().text();

        if (options.fragment) {

            if (options.fragment === 'body') {
                var $fragment = $body;
            } else {
                var $fragment = findAll($body, options.fragment).first();
            }

            if ($fragment.length) {
                obj.contents = options.fragment === 'body' ? $fragment : $fragment.contents();

                if (!obj.title)
                    obj.title = $fragment.attr('title') || $fragment.data('title');
            }

        } else if (!fullDocument) {
            obj.contents = $body;
        }

        if (obj.contents) {

            obj.contents = obj.contents.not(function () {
                return $(this).is('title');
            })

            obj.contents.find('title').remove();

            obj.scripts = findAll(obj.contents, 'script[src]').remove();
            obj.contents = obj.contents.not(obj.scripts);
        }

        if (obj.title)
            obj.title = $.trim(obj.title);

        return obj;
    }

    function executeScriptTags(scripts) {
        if (!scripts)
            return;

        var existingScripts = $('script[src]');

        scripts.each(function () {
            var src = this.src;
            var matchedScripts = existingScripts.filter(function () {
                return this.src === src
            });
            if (matchedScripts.length)
                return;

            var script = document.createElement('script');
            var type = $(this).attr('type');
            if (type)
                script.type = type;
            script.src = $(this).attr('src');
            document.head.appendChild(script);
        })
    }

    var cacheMapping = {};
    var cacheForwardStack = [];
    var cacheBackStack = [];

    function cachePush(id, value) {
        cacheMapping[id] = value;
        cacheBackStack.push(id);

        trimCacheStack(cacheForwardStack, 0);

        trimCacheStack(cacheBackStack, pjax.defaults.maxCacheLength);
    }

    function cachePop(direction, id, value) {
        var pushStack, popStack;
        cacheMapping[id] = value;

        if (direction === 'forward') {
            pushStack = cacheBackStack;
            popStack = cacheForwardStack;
        } else {
            pushStack = cacheForwardStack;
            popStack = cacheBackStack;
        }

        pushStack.push(id);
        if (id = popStack.pop())
            delete cacheMapping[id];

        trimCacheStack(pushStack, pjax.defaults.maxCacheLength);
    }

    function trimCacheStack(stack, length) {
        while (stack.length > length)
            delete cacheMapping[stack.shift()];
    }

    function findVersion() {
        return $('meta').filter(function () {
            var name = $(this).attr('http-equiv');
            return name && name.toUpperCase() === 'X-PJAX-VERSION';
        }).attr('content')
    }

    function enable() {
        $.fn.pjax = fnPjax
        $.pjax = pjax
        $.pjax.enable = $.noop
        $.pjax.disable = disable
        $.pjax.click = handleClick
        $.pjax.submit = handleSubmit
        $.pjax.reload = pjaxReload
        $.pjax.defaults = {
            timeout: 650,
            push: true,
            replace: false,
            type: 'GET',
            dataType: 'html',
            scrollTo: 0,
            maxCacheLength: 20,
            version: findVersion
        }
        $(window).on('popstate.pjax', onPjaxPopstate);
    }

    function disable() {
        $.fn.pjax = function () {
            return this
        };
        $.pjax = fallbackPjax;
        $.pjax.enable = enable;
        $.pjax.disable = $.noop;
        $.pjax.click = $.noop;
        $.pjax.submit = $.noop;
        $.pjax.reload = function () {
            window.location.reload();
        }

        $(window).off('popstate.pjax', onPjaxPopstate);
    }

    if ($.inArray('state', $.event.props) < 0)
        $.event.props.push('state');

    $.support.pjax = window.history && window.history.pushState && window.history.replaceState && !navigator.userAgent.match(/((iPod|iPhone|iPad).+\bOS\s+[1-4]\D|WebApps\/.+CFNetwork)/);

    $.support.pjax ? enable() : disable();

    /* animate scroll */
    // defines various easing effects
    $.easing['jswing'] = $.easing['swing'];
    $.extend($.easing, {
        def: 'easeOutQuad',
        swing: function (x, t, b, c, d) {
            return $.easing[$.easing.def](x, t, b, c, d);
        },
        easeInQuad: function (x, t, b, c, d) {
            return c * (t /= d) * t + b;
        },
        easeOutQuad: function (x, t, b, c, d) {
            return -c * (t /= d) * (t - 2) + b;
        },
        easeInOutQuad: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1)
                return c / 2 * t * t + b;
            return -c / 2 * ((--t) * (t - 2) - 1) + b;
        },
        easeInCubic: function (x, t, b, c, d) {
            return c * (t /= d) * t * t + b;
        },
        easeOutCubic: function (x, t, b, c, d) {
            return c * ((t = t / d - 1) * t * t + 1) + b;
        },
        easeInOutCubic: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1)
                return c / 2 * t * t * t + b;
            return c / 2 * ((t -= 2) * t * t + 2) + b;
        },
        easeInQuart: function (x, t, b, c, d) {
            return c * (t /= d) * t * t * t + b;
        },
        easeOutQuart: function (x, t, b, c, d) {
            return -c * ((t = t / d - 1) * t * t * t - 1) + b;
        },
        easeInOutQuart: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1)
                return c / 2 * t * t * t * t + b;
            return -c / 2 * ((t -= 2) * t * t * t - 2) + b;
        },
        easeInQuint: function (x, t, b, c, d) {
            return c * (t /= d) * t * t * t * t + b;
        },
        easeOutQuint: function (x, t, b, c, d) {
            return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
        },
        easeInOutQuint: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1)
                return c / 2 * t * t * t * t * t + b;
            return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
        },
        easeInSine: function (x, t, b, c, d) {
            return -c * Math.cos(t / d * (Math.PI / 2)) + c + b;
        },
        easeOutSine: function (x, t, b, c, d) {
            return c * Math.sin(t / d * (Math.PI / 2)) + b;
        },
        easeInOutSine: function (x, t, b, c, d) {
            return -c / 2 * (Math.cos(Math.PI * t / d) - 1) + b;
        },
        easeInExpo: function (x, t, b, c, d) {
            return (t == 0) ? b : c * Math.pow(2, 10 * (t / d - 1)) + b;
        },
        easeOutExpo: function (x, t, b, c, d) {
            return (t == d) ? b + c : c * (-Math.pow(2, -10 * t / d) + 1) + b;
        },
        easeInOutExpo: function (x, t, b, c, d) {
            if (t == 0)
                return b;
            if (t == d)
                return b + c;
            if ((t /= d / 2) < 1)
                return c / 2 * Math.pow(2, 10 * (t - 1)) + b;
            return c / 2 * (-Math.pow(2, -10 * --t) + 2) + b;
        },
        easeInCirc: function (x, t, b, c, d) {
            return -c * (Math.sqrt(1 - (t /= d) * t) - 1) + b;
        },
        easeOutCirc: function (x, t, b, c, d) {
            return c * Math.sqrt(1 - (t = t / d - 1) * t) + b;
        },
        easeInOutCirc: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1)
                return -c / 2 * (Math.sqrt(1 - t * t) - 1) + b;
            return c / 2 * (Math.sqrt(1 - (t -= 2) * t) + 1) + b;
        },
        easeInElastic: function (x, t, b, c, d) {
            var s = 1.70158;
            var p = 0;
            var a = c;
            if (t == 0)
                return b;
            if ((t /= d) == 1)
                return b + c;
            if (!p)
                p = d * .3;
            if (a < Math.abs(c)) {
                a = c;
                var s = p / 4;
            } else
                var s = p / (2 * Math.PI) * Math.asin(c / a);
            return -(a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
        },
        easeOutElastic: function (x, t, b, c, d) {
            var s = 1.70158;
            var p = 0;
            var a = c;
            if (t == 0)
                return b;
            if ((t /= d) == 1)
                return b + c;
            if (!p)
                p = d * .3;
            if (a < Math.abs(c)) {
                a = c;
                var s = p / 4;
            } else
                var s = p / (2 * Math.PI) * Math.asin(c / a);
            return a * Math.pow(2, -10 * t) * Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b;
        },
        easeInOutElastic: function (x, t, b, c, d) {
            var s = 1.70158;
            var p = 0;
            var a = c;
            if (t == 0)
                return b;
            if ((t /= d / 2) == 2)
                return b + c;
            if (!p)
                p = d * (.3 * 1.5);
            if (a < Math.abs(c)) {
                a = c;
                var s = p / 4;
            } else
                var s = p / (2 * Math.PI) * Math.asin(c / a);
            if (t < 1)
                return -.5 * (a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
            return a * Math.pow(2, -10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p) * .5 + c + b;
        },
        easeInBack: function (x, t, b, c, d, s) {
            if (s == undefined)
                s = 1.70158;
            return c * (t /= d) * t * ((s + 1) * t - s) + b;
        },
        easeOutBack: function (x, t, b, c, d, s) {
            if (s == undefined)
                s = 1.70158;
            return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        },
        easeInOutBack: function (x, t, b, c, d, s) {
            if (s == undefined)
                s = 1.70158;
            if ((t /= d / 2) < 1)
                return c / 2 * (t * t * (((s *= (1.525)) + 1) * t - s)) + b;
            return c / 2 * ((t -= 2) * t * (((s *= (1.525)) + 1) * t + s) + 2) + b;
        },
        easeInBounce: function (x, t, b, c, d) {
            return c - $.easing.easeOutBounce(x, d - t, 0, c, d) + b;
        },
        easeOutBounce: function (x, t, b, c, d) {
            if ((t /= d) < (1 / 2.75)) {
                return c * (7.5625 * t * t) + b;
            } else if (t < (2 / 2.75)) {
                return c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b;
            } else if (t < (2.5 / 2.75)) {
                return c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b;
            } else {
                return c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375) + b;
            }
        },
        easeInOutBounce: function (x, t, b, c, d) {
            if (t < d / 2)
                return $.easing.easeInBounce(x, t * 2, 0, c, d) * .5 + b;
            return $.easing.easeOutBounce(x, t * 2 - d, 0, c, d) * .5 + c * .5 + b;
        }
    });

    $.fn.animatescroll = function (options) {

        // fetches options
        var opts = $.extend({}, $.fn.animatescroll.defaults, options);

        // make sure the callback is a function
        if (typeof opts.onScrollStart == 'function') {
            // brings the scope to the callback
            opts.onScrollStart.call(this);
        }

        if (opts.element == "html,body") {
            // Get the distance of particular id or class from top
            var offset = this.offset().top;

            // Scroll the page to the desired position
            $(opts.element).stop().animate({
                scrollTop: offset - opts.padding
            }, opts.scrollSpeed, opts.easing);
        } else {
            // Scroll the element to the desired position
            $(opts.element).stop().animate({
                scrollTop: this.offset().top - this.parent().offset().top + this.parent().scrollTop() - opts.padding
            }, opts.scrollSpeed, opts.easing);
        }

        setTimeout(function () {

            // make sure the callback is a function
            if (typeof opts.onScrollEnd == 'function') {
                // brings the scope to the callback
                opts.onScrollEnd.call(this);
            }
        }, opts.scrollSpeed);
    };

    // default options
    $.fn.animatescroll.defaults = {
        easing: "swing",
        scrollSpeed: 800,
        padding: 0,
        element: "html,body"
    };

    $.fn.Lc4eAnimate = function (options) {
        var defaults = {
            speed: "normal",
            animation: "slideInDown",
            show: true,
            interval: 100,
            onComplete: function ($thedom) {
            },
            onFinish: function ($that) {

            },
            onBefore: function ($thedom) {
            }
        }, $that = $(this), count = 0, flag = false;

        function animate() {
            var $thedom = $($that[count]), animateClass = new Array(), InCount = count;
            animateClass.push('animated-');
            animateClass.push(options.speed);
            animateClass.push(" ");
            animateClass.push(options.animation);
            animateClass = animateClass.join("");
            options.onBefore();
            $thedom.show();
            $thedom.addClass(animateClass).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $thedom.removeClass(animateClass)
                options.onComplete($thedom);
                if (options.show) {
                    $thedom.show();
                } else {
                    $thedom.hide();
                }
                if (InCount == $that.length - 1) {
                    options.onFinish($that);
                }
            });
            count += 1;
        }

        function animateIndex() {
            return function () {
                $.requestAnimationFrame(animate);
            }
        }

        options = $.extend(defaults, options);
        for (var i = 0, len = $that.length; i < len; i++) {
            setTimeout(animateIndex(), options.interval * i);
        }
        return $that;
    }
    $.fn.Lc4eDimmer = function (options, data) {
        var defaults = {
                type: 'loader', // loader or dimmer
                color: 'black',
                content: "Loading",
            }, loaderDefaults = {
                size: 'small', // small mini medium large
                direction: "" // indeterminate
            }, template = '<div class="ui active {Color} dimmer">{Content}</div>',

            Loadertemplate = '<div class="ui {Direction} {Size} text loader">{Content}</div>';

        return this.each(function () {
            var $that = $(this), html = "";
            switch (options) {
                case "toggle":
                {
                    if ($that.find('.ui.active.dimmer').length != 0) {
                        $that.find('.ui.dimmer').removeClass('active');
                    } else {
                        if ($that.find('.ui.dimmer').length != 0) {
                            $that.find('.ui.dimmer').addClass('active');
                        } else {
                            options = options = $.extend(defaults, data);
                            if (options.type == "dimmer") {
                                html = template.replace(/{Content}/, options.content).replace(/{Color}/, options.color == "black" ? "" : "inverted");
                            } else {
                                options = $.extend(loaderDefaults, options);
                                html = template.replace(/{Content}/, Loadertemplate).replace(/{Content}/, options.content).replace(/{Color}/, options.color == "black" ? "" : "inverted").replace(/{Direction}/, options.direction).replace(/{Size}/, options.size);
                            }
                            $that.append(html);
                        }
                    }
                    break;
                }
                case "hide":
                {
                    $that.find('.ui.active.dimmer').removeClass('active');
                    break;
                }
                case "show":
                {
                    $that.find('.ui.dimmer').addClass('active')
                    break;
                }
                case "remove":
                {
                    $that.find('.ui.dimmer').remove();
                    break;
                }
                default:
                {
                    options = $.extend(defaults, options);
                    if (options.type == "dimmer") {
                        html = template.replace(/{Content}/, options.content).replace(/{Color}/, options.color == "black" ? "" : "inverted");
                    } else {
                        options = $.extend(loaderDefaults, options);
                        html = template.replace(/{Content}/, Loadertemplate).replace(/{Content}/, options.content).replace(/{Color}/, options.color == "black" ? "" : "inverted").replace(/{Direction}/, options.direction).replace(/{Size}/, options.size);
                    }
                    $that.find('.ui.dimmer').remove();
                    $that.append(html);
                    break;
                }
            }
        })
    }
    $.fn.Lc4eModal = function (options) {
        var defaults = {
            Id: null,
            title: "Message",
            content: "This is a Lc4e Test Modal",
            closable: true, // enable esc or click dim page to close
            // modal
            useCSS: true,
            closeIcon: true,
            transition: "scale", // extend transition
            duration: 400,// animation
            type: "basic", // basic standard
            size: "small", // fullscreen ,small,large,long
            allowMultiple: false, // multiple modal
            offset: 2,
            context: 'body',
            queue: false,
            easing: "easeOutExpo",
            selector: {
                close: '.close',
            },
            dimmerSettings: {
                closable: false,
                useCSS: true
            },
            onDeny: function () {
            },
            onApprove: function () {
            },
            onShow: function () {
            },
            onVisible: function () {

            },
            onHide: function () {
            },
            onAfterHide: function () {
            },
            onHidden: function () {
            },
            OtherButtons: [],
            OtherButtonsClass: [],
            OtherButtonsClick: [],
        }, basciDefaults = {
            IconClass: 'warning circle',
            bottonNames: ['<i class="Remove icon"></i>No', '<i class="checkmark icon"></i>Yes'],
            buttonClass: ["deny close red basic inverted", "approve close green basic inverted"]
        }, standardDefaults = {
            bottonNames: ['Close'],
            buttonClass: ['basic close']
        }, basicModalHtml = '<div id="{ModalId}" class="ui basic {Mutiple} {Size} modal">{CloseIcon}<div class="header">{Title}</div><div class="content"><div class="image"><i class="{IconClass} icon"></i></div><div class="description">{Content}</div></div><div class="actions"><div class="{ButtonNumber} fluid ui inverted buttons">{Buttons}</div></div></div></div>', buttonHtml = '<div {ButtonId} class="ui {ButtonClass} button">{ButtonName}</div>', standardModalHtml = '<div id="{ModalId}" class="ui standard {Mutiple} {Size} modal">{CloseIcon}<div class="header">{Title}</div><div class="content">{Content}</div><div class="actions">{Buttons}</div></div>', NumberEng = [
            'one', 'two', 'three', 'four', 'five', 'six'], $operate;
        options = $.extend(defaults, options);

        this.each(function () {
            var $obj = $(this), modalId = options.Id == null ? "Lc4eModal-" + $.Lc4eRandom() : options.Id, $modalObj, html = "", buttonsHtml = "";
            switch (options.type) {
                case "basic":
                {
                    options = $.extend(basciDefaults, options);
                    html = basicModalHtml;
                    html = html.replace(/{IconClass}/, options.IconClass).replace(/{ButtonNumber}/, NumberEng[options.bottonNames.length - 1]);
                    break;
                }
                case "standard":
                {
                    options = $.extend(standardDefaults, options);
                    html = standardModalHtml;
                    break;
                }
                default:
                {
                    return false;
                    break;
                }
            }
            for (var i = 0, len = options.bottonNames.length; i < len; i++) {
                buttonsHtml += buttonHtml.replace(/{ButtonId}/, "").replace(/{ButtonClass}/, options.buttonClass[i]).replace(/{ButtonName}/, options.bottonNames[i]);
            }
            for (var i = 0, len = options.OtherButtons.length; i < len; i++) {
                buttonsHtml += buttonHtml.replace(/{ButtonId}/, 'id="' + modalId + '-button-' + i + '"').replace(/{ButtonClass}/, options.OtherButtonsClass[i]).replace(/{ButtonName}/, options.OtherButtons[i]);
            }
            html = html.replace(/{Mutiple}/, options.allowMultiple ? "coupled" : "").replace(/{CloseIcon}/, options.closeIcon ? '<i class="close icon"></i>' : "").replace(/{Size}/, options.size).replace(/{ModalId}/, modalId).replace(/{Title}/, options.title).replace(/{Content}/, options.content)
                .replace(/{Buttons}/, buttonsHtml);

            $obj.append(html);
            $modalObj = $("#" + modalId); // get modal
            while (options.OtherButtonsClick.length != options.OtherButtonsClass.length) {
                options.OtherButtonsClass.push("");
            }
            for (var i = 0, len = options.OtherButtonsClick.length; i < len; i++) {
                if (typeof (options.OtherButtonsClick[i]) == "function") {
                    $('#' + modalId + "-button-" + i).on('click', options.OtherButtonsClick[i]);
                }
            }
            options.onHidden = function () {
                options.onAfterHide();
                $modalObj.remove();
                $('.ui.dimmer.modals.page').remove();
            }
            $modalObj.modal(options);
            $modalObj.modal('show');
            $operate = $modalObj;
            return false;
        });
        return $operate;
    }

    $.fn.Lc4eProgress = function (option, data) {
        var defaults = {
            type: "standard",
            color: "blue",
            autoSuccess: true,
            showActivity: true,
            limitValues: true,
            label: "percent",
            precision: 0.001,
            indicating: true,
            total: false,
            value: false,
            autoUpdateSpeed: 200,
            autoUpdate: true,
            showLoading: true,
            onChange: function (percent, value, total) {
            },
            onComplete: function (total) {
            },
            onSuccess: function (total) {
            },
            onActive: function (value, total) {
            },
            onError: function (value, total) {
            },
            onWarning: function (value, total) {
            }
        }, attachDefaults = {
            location: "bottom"
        }, standardtemplate = '<div id="lc4eProgress"><div class="ui mini progress {Indicating} {Color}" id="lc4eProgressBar"><div class="bar"></div></div>{Loading}</div>', attachedBar = '<div {Id} class="ui attached progress {Color} {Indicating} {Location}"><div class="bar"></div></div>', $operate;

        var options, $that = $(this), html = "", template = "", progressId = "", $progressBar;
        switch (option) {
            case "setPercent":
            {
                $progressBar = $that;
                $that.progress({
                    percent: data
                });
                break;
            }
            case "end":
            {
                $progressBar = $that;
                $that.progress({
                    percent: '100'
                }).find('.bar').one('webkitTransitionEnd transitionend mozTransitionEnd oTransitionEnd', function () {
                    if ($progressBar.attr("id") == "lc4eProgressBar") {
                        $('#lc4eProgress').remove();
                    } else {
                        $progressBar.remove();
                    }
                });
                break;
            }
            case "hide":
            {
                $progressBar = undefined;
                $that.remove();
                break;
            }
            case "increment":
            {
                $progressBar = $that;
                $that.progress("increment");
                break;
            }
            case "autoincrement":
            {
                $progressBar = $that;
                var progressInter = setInterval(function () {
                    $progressBar.progress('increment');
                }, options.autoUpdateSpeed);
                $that.data('interval', progressInter);
                break;
            }
            case "stop":
            {
                $progressBar = $that;
                clearInterval($that.data('interval'));
                $that.removeData('interval');
                break;
            }
            default:
            {
                options = $.extend(defaults, option);
                if (options.type == "attached") {
                    options = $.extend(attachDefaults, options);
                    if ($that.find('.ui.attached.progress').data('interval') != undefined) {
                        clearInterval($that.find('.ui.attached.progress').data('interval'));
                    }
                    $that.find('.ui.attached.progress').remove();
                    progressId = 'AttachedBar-' + $.Lc4eRandom();
                    template = attachedBar.replace(/{Location}/, options.location).replace(/{Id}/, 'id="' + progressId + '"');
                } else {
                    template = standardtemplate.replace(/{Loading}/, options.showLoading ? '<div class="spinner"><div class="ui mini active loader"></div></div>' : "");
                    progressId = "lc4eProgressBar";
                    if ($('#' + progressId).data('interval') != undefined) {
                        clearInterval($('#' + progressId).data('interval'));
                    }
                    $('#lc4eProgress').remove();
                }

                html = template.replace(/{Color}/, options.indicating ? "" : options.color).replace(/{Indicating}/, options.indicating ? "indicating" : "");
                if (options.type == "attached" && options.location == "top") {
                    $that.prepend(html);
                } else {
                    $that.append(html);
                }
                $progressBar = $('#' + progressId);

                options.onSuccess = function (total) {
                    options.onComplete(total);
                    clearInterval($progressBar.data('interval'));
                    $progressBar.removeData('interval');
                    if ($progressBar.attr("id") == "lc4eProgressBar") {
                        $('#lc4eProgress').remove();
                    } else {
                        $progressBar.remove();
                    }
                }
                $progressBar.progress(options);
                clearInterval($progressBar.data('interval'));
                if (options.autoUpdate) {
                    var progressInter = setInterval(function () {
                        $progressBar.progress('increment');
                    }, options.autoUpdateSpeed);
                    $progressBar.data('interval', progressInter);
                }

                break;
            }
        }
        return $progressBar;
    }
    $.fn.Lc4eAjaxTemplate = function (options) {
        var defaults = {
            url: null,
            data: {},
            templateUrl: null,
            empty: true,
            enableAnimate: true,
            needToken: false,
            speed: "normal",
            animation: "slideInDown",
            interval: 100,
            onComplete: function ($thedom) {
            },
            onFinish: function ($that) {
            },
            onBefore: function ($thedom) {
            }
        }, tmpajax, dtd = $.Deferred(), thisDom = this;
        options = $.extend(defaults, options);

        if (options.needToken) {
            tmpajax = $.Lc4eAjax;
        } else {
            tmpajax = $.ajax;
        }
        tmpajax({
            url: options.templateUrl,
            type: 'get'
        }).done(function (data) {
            dtd.resolve(data);
        })
        tmpajax({
            url: options.url,
            type: 'post',
            data: options.data,
            dataType: 'json'
        }).done(function (data) {
            dtd.done(function (templateHtml) {
                var itemArray = templateHtml.match(/{#(.*?)#}/g), dataLength = data.length;
                thisDom.each(function () {
                    var $that = $(this), count = 0;
                    if (options.empty) {
                        $that.empty()
                    }
                    function animateIndex() {
                        return function () {
                            $.requestAnimationFrame(animate);
                        }
                    }

                    function animate() {
                        var domHtml = templateHtml, InCount = count;
                        for (var i = 0, len = itemArray.length; i < len; i++) {
                            var item = itemArray[i], itemlen = item.length;
                            domHtml = domHtml.replace(new RegExp(item, 'g'), $.Lc4eGetter(data[InCount], $.trim(item.substring(2, itemlen - 2))));
                        }
                        var $thedom = $(domHtml), animateClass = new Array();
                        animateClass.push('animated-');
                        animateClass.push(options.speed);
                        animateClass.push(" ");
                        animateClass.push(options.animation);
                        animateClass = animateClass.join("");
                        options.onBefore();
                        $that.append($thedom);
                        $thedom.addClass(animateClass).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            $thedom.removeClass(animateClass)
                            options.onComplete($thedom);
                            if (InCount == dataLength - 1) {
                                options.onFinish($that);
                            }
                        });

                        count += 1;
                    }

                    if (options.enableAnimate) {
                        for (var i = 0, len = dataLength; i < len; i++) {
                            setTimeout(animateIndex(), options.interval * i);
                        }
                    } else {
                        for (var i = 0, len = dataLength; i < len; i++) {
                            var domHtml = templateHtml;
                            for (var i = 0, len = itemArray.length; i < len; i++) {
                                var item = itemArray[i], itemlen = item.length;
                                domHtml = domHtml.replace(new RegExp(item, 'g'), $.Lc4eGetter(data[i], $.trim(item.substring(2, itemlen - 2))));
                            }
                            $that.append(domHtml);
                        }
                    }
                })
            })
        })

        return $(this);

    };
    $.extend({
        Lc4eGetter: function (obj, path) {
            if (!path)
                return obj;
            var keys = path.split('.'), key, len = keys.length;
            for (var i = 0; i < len; i++) {
                key = keys[i];
                if (obj) {
                    obj = obj[key];
                }
            }
            return obj;
        },
        Lc4eAjax: function (data) {

            data.beforeSend = function (xhr) {
                var tk = 'l' + 'c' + '4' + 'e' + '-' + 't' + 'o' + 'k' + 'e' + 'n', l = data.url.length.toString(), t = new Date().getTime().toString();
                xhr.setRequestHeader(tk, l + t + l);
                if (typeof data.bfSend == "function") {
                    data.bfSend.call();
                }
            }

            return $.ajax(data);
        },
        Lc4eRandom: function () {
            function random(a, b) {
                return Math.random() > 0.5 ? -1 : 1;
            }

            return ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'Q', 'q', 'W', 'w', 'E', 'e', 'R', 'r', 'T', 't', 'Y', 'y', 'U', 'u', 'I', 'i', 'O', 'o', 'P', 'p', 'A', 'a', 'S', 's', 'D', 'd', 'F', 'f', 'G', 'g', 'H', 'h', 'J', 'j', 'K', 'k', 'L', 'l', 'Z', 'z', 'X', 'x', 'C', 'c', 'V',
                'v', 'B', 'b', 'N', 'n', 'M', 'm'].sort(random).join('').substring(5, 20);
        },
        Lc4eModal: function (options) {
            return $("body").Lc4eModal(options);
        },
        Lc4eToDate: function (unixTime) {
            function unix2human(unixtime) {
                var dateObj = new Date(unixtime);
                var UnixTimeToDate = dateObj.getFullYear() + '-' + (dateObj.getMonth() + 1) + '-' + dateObj.getDate() + ' ' + p(dateObj.getHours()) + ':' + p(dateObj.getMinutes()) + ':' + p(dateObj.getSeconds());
                return UnixTimeToDate;
            }

            function p(s) {
                return s < 10 ? '0' + s : s;
            }

            return unix2human(unixTime);
        },
        Lc4eProgress: function (option, data) {
            return $("body").Lc4eProgress(option, data);
        },
        requestAnimationFrame: function (callback) {
            var requestAnimation = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function (callback) {
                    window.setTimeout(callback, 0);
                };
            return requestAnimation(callback);
        },
    });
})(jQuery);
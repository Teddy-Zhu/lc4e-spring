/*!
 * Lc4e Javascript Library
 * author zhuxi - v1.0.0 (2015-04-09T11:23:51+0800)
 * http://www.lc4e.com/ | Released under MIT license
 * 
 * Include jquery (http://jquery.com/) semantic-ui (http://semantic-ui.com/)
 * animatescroll(http://plugins.compzets.com/animatescroll/)
 */

(function ($) {

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
            if (typeof opts.onScrollEnd == 'function') {
                opts.onScrollEnd.call(this);
            }
        }, opts.scrollSpeed);
    };

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
        }, $that = $(this), count = 0;

        function animate() {
            var $thedom = $($that[count]), animateClass = [], InCount = count;
            animateClass.push('animated-');
            animateClass.push(options.speed);
            animateClass.push(" ");
            animateClass.push(options.animation);
            animateClass = animateClass.join("");
            options.onBefore();
            $thedom.show();
            $thedom.addClass(animateClass).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $thedom.removeClass(animateClass);
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
    };
    $.fn.Lc4eDimmer = function (options, data) {
        var defaults = {
                type: 'loader', // loader or dimmer
                color: 'black',
                content: "Loading"
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
                                html = template.replace(new RegExp('{Content}', 'g'), options.content).replace(new RegExp('{Color}', 'g'), options.color == "black" ? "" : "inverted");
                            } else {
                                options = $.extend(loaderDefaults, options);
                                html = template.replace(new RegExp('{Content}', 'g'), Loadertemplate).replace(new RegExp('{Content}', 'g'), options.content).replace(new RegExp('{Color}', 'g'),
                                    options.color == "black" ? "" : "inverted").replace(new RegExp('{Direction}', 'g'), options.direction).replace(new RegExp('{Size}', 'g'), options.size);
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
                    $that.find('.ui.dimmer').addClass('active');
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
                        html = template.replace(new RegExp('{Content}', 'g'), options.content).replace(new RegExp('{Color}', 'g'), options.color == "black" ? "" : "inverted");
                    } else {
                        options = $.extend(loaderDefaults, options);
                        html = template.replace(new RegExp('{Content}', 'g'), Loadertemplate).replace(new RegExp('{Content}', 'g'), options.content).replace(new RegExp('{Color}', 'g'), options.color == "black" ? "" : "inverted").replace(new RegExp('{Direction}', 'g'), options.direction).replace(new RegExp('{Size}', 'g'), options.size);
                    }
                    $that.find('.ui.dimmer').remove();
                    $that.append(html);
                    break;
                }
            }
        })
    };
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
                    close: '.close'
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
                OtherButtonsClick: []
            }, basciDefaults = {
                IconClass: 'warning circle',
                bottonNames: ['<i class="Remove icon"></i>No', '<i class="checkmark icon"></i>Yes'],
                buttonClass: ["deny close red basic inverted", "approve close green basic inverted"]
            }, standardDefaults = {
                bottonNames: ['Close'],
                buttonClass: ['basic close']
            },
            basicModalHtml = '<div id="{ModalId}" class="ui basic {Mutiple} {Size} modal">{CloseIcon}<div class="header">{Title}</div><div class="content"><div class="image"><i class="{IconClass} icon"></i></div><div class="description">{Content}</div></div><div class="actions"><div class="{ButtonNumber} fluid ui inverted buttons">{Buttons}</div></div></div></div>',
            buttonHtml = '<div {ButtonId} class="ui {ButtonClass} button">{ButtonName}</div>', standardModalHtml = '<div id="{ModalId}" class="ui standard {Mutiple} {Size} modal">{CloseIcon}<div class="header">{Title}</div><div class="content">{Content}</div><div class="actions">{Buttons}</div></div>',
            NumberEng = ['one', 'two', 'three', 'four', 'five', 'six'], $operate;
        options = $.extend(defaults, options);

        this.each(function () {
            var $obj = $(this), modalId = options.Id == null ? "Lc4eModal-" + $.Lc4eRandom() : options.Id, $modalObj, html = "", buttonsHtml = "";
            switch (options.type) {
                case "basic":
                {
                    options = $.extend(basciDefaults, options);
                    html = basicModalHtml;
                    html = html.replace(new RegExp('{IconClass}', 'g'), options.IconClass).replace(new RegExp('{ButtonNumber}', 'g'), NumberEng[options.bottonNames.length - 1]);
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
                }
            }
            for (var i = 0, len = options.bottonNames.length; i < len; i++) {
                buttonsHtml += buttonHtml.replace(new RegExp('{ButtonId}', 'g'), "").replace(new RegExp('{ButtonClass}', 'g'), options.buttonClass[i]).replace(new RegExp('{ButtonName}', 'g'), options.bottonNames[i]);
            }
            for (var i = 0, len = options.OtherButtons.length; i < len; i++) {
                buttonsHtml += buttonHtml.replace(new RegExp('{ButtonId}', 'g'), 'id="' + modalId + '-button-' + i + '"').replace(new RegExp('{ButtonClass}', 'g'), options.OtherButtonsClass[i]).replace(new RegExp('{ButtonName}', 'g'), options.OtherButtons[i]);
            }
            html = html.replace(new RegExp('{Mutiple}', 'g'), options.allowMultiple ? "coupled" : "").replace(new RegExp('{CloseIcon}', 'g'), options.closeIcon ? '<i class="close icon"></i>' : "").replace(new RegExp('{Size}', 'g'), options.size).
                replace(new RegExp('{ModalId}', 'g'), modalId).replace(new RegExp('{Title}', 'g'), options.title).
                replace(new RegExp('{Content}', 'g'), options.content).replace(new RegExp('{Buttons}'), buttonsHtml);

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
            };
            $modalObj.modal(options);
            $modalObj.modal('show');
            $operate = $modalObj;
            return false;
        });
        return $operate;
    };

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
            }, standardtemplate = '<div id="lc4eProgress"><div class="ui mini progress {Indicating} {Color}" id="lc4eProgressBar"><div class="bar"></div></div>{Loading}</div>',
            attachedBar = '<div {Id} class="ui attached progress {Color} {Indicating} {Location}"><div class="bar"></div></div>', $operate;

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
                    template = attachedBar.replace(new RegExp('{Location}', 'g'), options.location).replace(new RegExp('{Id}', 'g'), 'id="' + progressId + '"');
                } else {
                    template = standardtemplate.replace(new RegExp('{Loading}', 'g'), options.showLoading ? '<div class="spinner"><div class="ui mini active loader"></div></div>' : "");
                    progressId = "lc4eProgressBar";
                    if ($('#' + progressId).data('interval') != undefined) {
                        clearInterval($('#' + progressId).data('interval'));
                    }
                    $('#lc4eProgress').remove();
                }

                html = template.replace(new RegExp('{Color}', 'g'), options.indicating ? "" : options.color).replace(new RegExp('{Indicating}', 'g'), options.indicating ? "indicating" : "");
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
                };
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
    };
    $.fn.Lc4eAjaxTemplate = function (options) {
        var defaults = {
            url: null,
            data: {},
            templateUrl: null,
            empty: true,
            enableAnimate: true,
            needToken: false,
            Lc4eCache: {
                use: false,
                name: '',
                dom: ''
            },
            speed: "normal",
            animation: "slideInDown",
            dataVal: '',
            interval: 100,
            onSingleFinish: function ($thedom) {
            },
            onFinish: function ($that) {
            },
            onSingleBefore: function ($thedom) {
            },
            onBefore: function ($thedom) {
            }
        }, dtd = $.Deferred(), thisDom = this, $cacheDom;
        options = $.extend(true, defaults, options);

        $cacheDom = $(options.cacheDom);

        var getTemplateAjax = function () {
            $.Lc4eAjax({
                url: options.templateUrl,
                type: 'get',
                needToken: options.needToken
            }).done(function (data) {
                dtd.resolve(data);
                if (options.Lc4eCache.use) {
                    $cacheDom.data(options.Lc4eCache.name, data);
                }
            })
        };
        if (options.Lc4eCache.use) {
            var html = $cacheDom.data(options.Lc4eCache.name);
            if (html) {
                dtd.resolve(html);
            } else {
                getTemplateAjax.call();
            }
        } else {
            getTemplateAjax.call();
        }

        $.Lc4eAjax({
            url: options.url,
            type: 'get',
            data: options.data,
            dataType: 'json',
            needToken: options.needToken,
        }).success(function (data) {
            if (options.dataVal) {
                data = $.Lc4eGetter(data, options.dataVal);
            }
            dtd.done(function (templateHtml) {
                options.onBefore();
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
                        var $thedom = $(domHtml), animateClass = [];
                        animateClass.push('animated-');
                        animateClass.push(options.speed);
                        animateClass.push(" ");
                        animateClass.push(options.animation);
                        animateClass = animateClass.join("");
                        options.onSingleBefore();
                        $that.append($thedom);
                        $thedom.addClass(animateClass).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            $thedom.removeClass(animateClass);
                            options.onSingleFinish($thedom);
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
            });
        });
        return $(this);
    };
    $.extend({
        Lc4eGetter: function (obj, path) {
            if (!path)
                return obj;
            var keys = path.split('.'), key, len = keys.length;
            for (var i = 0; i < len; i++) {
                if (obj) {
                    obj = obj[keys[i]];
                }
            }
            return obj;
        },
        Lc4eRandomColor: function () {
            return '#' + ('00000' + (Math.random() * 0x1000000 << 0).toString(16)).slice(-6)
        }
        ,
        Lc4eAjax: function (data) {
            var loptions = {};
            data = $.extend(true, {
                    cjson: true,
                }, data
            )
            if (data["cjson"]) {
                var tdata = data["data"];
                if(typeof tdata == "object"){
                    tdata = JSON.stringify(tdata);
                }
                data["data"] = tdata;
                data = $.extend(true, {
                        type: "post",
                        dataType: "json",
                        contentType: "application/json"
                    }, data
                )
            }else{
                data = $.extend(true, {
                        type: 'post',
                        dataType: 'json',
                    }, data
                )
            }
            if (data.hasOwnProperty("beforeSend")) {
                loptions["beforeSend"] = data["beforeSend"];
            }
            if (data.needToken) {
                data.beforeSend = function (xhr) {
                    var tk = 'l' + 'c' + '4' + 'e' + '-' + 't' + 'o' + 'k' + 'e' + 'n', l = data.url.length.toString(), t = new Date().getTime().toString();
                    xhr.setRequestHeader(tk, l + t + l);
                    if (typeof loptions.beforeSend === "function") {
                        loptions.beforeSend(xhr);
                    }
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
            return $.lc4e.Lc4eToDate.unix2human(unixTime);
        },
        Lc4eProgress: function (option, data) {
            return $("body").Lc4eProgress(option, data);
        },
        requestAnimationFrame: function (callback) {
            var requestAnimation = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function (callback) {
                    window.setTimeout(callback, 0);
                };
            return requestAnimation(callback);
        }
    });

    $.lc4e = $.lc4e || {};

    $.extend($.lc4e, {
        version: '1.0',
        Lc4eToDate: {
            unix2human: function (unixtime) {
                var dateObj = new Date(unixtime);
                var UnixTimeToDate = dateObj.getFullYear() + '-' + (dateObj.getMonth() + 1) + '-' + dateObj.getDate() + ' ' + $.lc4e.Lc4eToDate.p(dateObj.getHours()) + ':' + $.lc4e.Lc4eToDate.p(dateObj.getMinutes()) + ':' + $.lc4e.Lc4eToDate.p(dateObj.getSeconds());
                return UnixTimeToDate;
            },
            p: function (s) {
                return s < 10 ? '0' + s : s;
            }
        },
        Lc4ePJAX: {
            support: function () {
                return window.history && window.history.pushState && window.history.replaceState && !navigator.userAgent.match(/(iPod|iPhone|iPad|WebApps\/.+CFNetwork)/) && window.localStorage
            },
        },
    })

})
(jQuery);
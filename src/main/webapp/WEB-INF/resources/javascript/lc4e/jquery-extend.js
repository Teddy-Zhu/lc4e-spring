/*!
 * Lc4e Javascript Library
 * author zhuxi - v1.0.0 (2015-04-09T11:23:51+0800)
 * http://www.lc4e.com/ | Released under MIT license
 * 
 * Include jquery (http://jquery.com/) semantic-ui (http://semantic-ui.com/) animatescroll
 * moment (http://momentjs.com/)
 */

(function($) {
	function fnPjax(selector, container, options) {
		  var context = this
		  return this.on('click.pjax', selector, function(event) {
		    var opts = $.extend({}, optionsFor(container, options))
		    if (!opts.container)
		      opts.container = $(this).attr('data-pjax') || context
		    handleClick(event, opts)
		  })
		}

		// Public: pjax on click handler
		//
		// Exported as $.pjax.click.
		//
		// event - "click" jQuery.Event
		// options - pjax options
		//
		// Examples
		//
		// $(document).on('click', 'a', $.pjax.click)
		// // is the same as
		// $(document).pjax('a')
		//
		// $(document).on('click', 'a', function(event) {
// var container = $(this).closest('[data-pjax-container]')
// $.pjax.click(event, container)
		// })
		//
		// Returns nothing.
		function handleClick(event, container, options) {
		  options = optionsFor(container, options)

		  var link = event.currentTarget

		  if (link.tagName.toUpperCase() !== 'A')
		    throw "$.fn.pjax or $.pjax.click requires an anchor element"

		  // Middle click, cmd click, and ctrl click should open
		  // links in a new tab as normal.
		  if ( event.which > 1 || event.metaKey || event.ctrlKey || event.shiftKey || event.altKey )
		    return

		  // Ignore cross origin links
		  if ( location.protocol !== link.protocol || location.hostname !== link.hostname )
		    return

		  // Ignore case when a hash is being tacked on the current URL
		  if ( link.href.indexOf('#') > -1 && stripHash(link) == stripHash(location) )
		    return

		  // Ignore event with default prevented
		  if (event.isDefaultPrevented())
		    return

		  var defaults = {
		    url: link.href,
		    container: $(link).attr('data-pjax'),
		    target: link
		  }

		  var opts = $.extend({}, defaults, options)
		  var clickEvent = $.Event('pjax:click')
		  $(link).trigger(clickEvent, [opts])

		  if (!clickEvent.isDefaultPrevented()) {
		    pjax(opts)
		    event.preventDefault()
		    $(link).trigger('pjax:clicked', [opts])
		  }
		}

		// Public: pjax on form submit handler
		//
		// Exported as $.pjax.submit
		//
		// event - "click" jQuery.Event
		// options - pjax options
		//
		// Examples
		//
		// $(document).on('submit', 'form', function(event) {
// var container = $(this).closest('[data-pjax-container]')
// $.pjax.submit(event, container)
		// })
		//
		// Returns nothing.
		function handleSubmit(event, container, options) {
		  options = optionsFor(container, options)

		  var form = event.currentTarget

		  if (form.tagName.toUpperCase() !== 'FORM')
		    throw "$.pjax.submit requires a form element"

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
		    // Can't handle file uploads, exit
		    if ($(form).find(':file').length) {
		      return;
		    }

		    // Fallback to manually serializing the fields
		    defaults.data = $(form).serializeArray();
		  }

		  pjax($.extend({}, defaults, options))

		  event.preventDefault()
		}

		// Loads a URL with ajax, puts the response body inside a container,
		// then pushState()'s the loaded URL.
		//
		// Works just like $.ajax in that it accepts a jQuery ajax
		// settings object (with keys like url, type, data, etc).
		//
		// Accepts these extra keys:
		//
		// container - Where to stick the response body.
// $(container).html(xhr.responseBody)
// push - Whether to pushState the URL. Defaults to true (of course).
		// replace - Want to use replaceState instead? That's cool.
		//
		// Use it just like $.ajax:
		//
		// var xhr = $.pjax({ url: this.href, container: '#main' })
		// console.log( xhr.readyState )
		//
		// Returns whatever $.ajax returns.
		function pjax(options) {
		  options = $.extend(true, {}, $.ajaxSettings, pjax.defaults, options)

		  if ($.isFunction(options.url)) {
		    options.url = options.url()
		  }

		  var target = options.target

		  var hash = parseURL(options.url).hash

		  var context = options.context = findContainerFor(options.container)

		  // We want the browser to maintain two separate internal caches: one
		  // for pjax'd partial page loads and one for normal page loads.
		  // Without adding this secret parameter, some browsers will often
		  // confuse the two.
		  if (!options.data) options.data = {}
		  if ($.isArray(options.data)) {
		    options.data.push({name: '_pjax', value: context.selector})
		  } else {
		    options.data._pjax = context.selector
		  }

		  function fire(type, args, props) {
		    if (!props) props = {}
		    props.relatedTarget = target
		    var event = $.Event(type, props)
		    context.trigger(event, args)
		    return !event.isDefaultPrevented()
		  }

		  var timeoutTimer

		  options.beforeSend = function(xhr, settings) {
		    // No timeout for non-GET requests
		    // Its not safe to request the resource again with a fallback
			// method.
		    if (settings.type !== 'GET') {
		      settings.timeout = 0
		    }

		    xhr.setRequestHeader('X-PJAX', 'true')
		    xhr.setRequestHeader('X-PJAX-Container', context.selector)

		    if (!fire('pjax:beforeSend', [xhr, settings]))
		      return false

		    if (settings.timeout > 0) {
		      timeoutTimer = setTimeout(function() {
		        if (fire('pjax:timeout', [xhr, options]))
		          xhr.abort('timeout')
		      }, settings.timeout)

		      // Clear timeout setting so jquerys internal timeout isn't
				// invoked
		      settings.timeout = 0
		    }

		    var url = parseURL(settings.url)
		    if (hash) url.hash = hash
		    options.requestUrl = stripInternalParams(url)
		  }

		  options.complete = function(xhr, textStatus) {
		    if (timeoutTimer)
		      clearTimeout(timeoutTimer)

		    fire('pjax:complete', [xhr, textStatus, options])

		    fire('pjax:end', [xhr, options])
		  }

		  options.error = function(xhr, textStatus, errorThrown) {
		    var container = extractContainer("", xhr, options)

		    var allowed = fire('pjax:error', [xhr, textStatus, errorThrown, options])
		    if (options.type == 'GET' && textStatus !== 'abort' && allowed) {
		      locationReplace(container.url)
		    }
		  }

		  options.success = function(data, status, xhr) {
		    var previousState = pjax.state;

		    // If $.pjax.defaults.version is a function, invoke it first.
		    // Otherwise it can be a static string.
		    var currentVersion = (typeof $.pjax.defaults.version === 'function') ?
		      $.pjax.defaults.version() :
		      $.pjax.defaults.version

		    var latestVersion = xhr.getResponseHeader('X-PJAX-Version')

		    var container = extractContainer(data, xhr, options)

		    var url = parseURL(container.url)
		    if (hash) {
		      url.hash = hash
		      container.url = url.href
		    }

		    // If there is a layout version mismatch, hard load the new url
		    if (currentVersion && latestVersion && currentVersion !== latestVersion) {
		      locationReplace(container.url)
		      return
		    }

		    // If the new response is missing a body, hard load the page
		    if (!container.contents) {
		      locationReplace(container.url)
		      return
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
		      window.history.replaceState(pjax.state, container.title, container.url)
		    }

		    // Clear out any focused controls before inserting new page
			// contents.
		    try {
		      document.activeElement.blur()
		    } catch (e) { }

		    if (container.title) document.title = container.title

		    fire('pjax:beforeReplace', [container.contents, options], {
		      state: pjax.state,
		      previousState: previousState
		    })
		    context.html(container.contents)

		    // FF bug: Won't autofocus fields that are inserted via JS.
		    // This behavior is incorrect. So if theres no current focus,
			// autofocus
		    // the last field.
		    //
		    // http://www.w3.org/html/wg/drafts/html/master/forms.html
		    var autofocusEl = context.find('input[autofocus], textarea[autofocus]').last()[0]
		    if (autofocusEl && document.activeElement !== autofocusEl) {
		      autofocusEl.focus();
		    }

		    executeScriptTags(container.scripts)

		    var scrollTo = options.scrollTo

		    // Ensure browser scrolls to the element referenced by the URL
			// anchor
		    if (hash) {
		      var name = decodeURIComponent(hash.slice(1))
		      var target = document.getElementById(name) || document.getElementsByName(name)[0]
		      if (target) scrollTo = $(target).offset().top
		    }

		    if (typeof scrollTo == 'number') $(window).scrollTop(scrollTo)

		    fire('pjax:success', [data, status, xhr, options])
		  }


		  // Initialize pjax.state for the initial page load. Assume we're
		  // using the container and options of the link we're loading for the
		  // back button to the initial page. This ensures good back button
		  // behavior.
		  if (!pjax.state) {
		    pjax.state = {
		      id: uniqueId(),
		      url: window.location.href,
		      title: document.title,
		      container: context.selector,
		      fragment: options.fragment,
		      timeout: options.timeout
		    }
		    window.history.replaceState(pjax.state, document.title)
		  }

		  // Cancel the current request if we're already pjaxing
		  abortXHR(pjax.xhr)

		  pjax.options = options
		  var xhr = pjax.xhr = $.ajax(options)

		  if (xhr.readyState > 0) {
		    if (options.push && !options.replace) {
		      // Cache current container element before replacing it
		      cachePush(pjax.state.id, cloneContents(context))

		      window.history.pushState(null, "", options.requestUrl)
		    }

		    fire('pjax:start', [xhr, options])
		    fire('pjax:send', [xhr, options])
		  }

		  return pjax.xhr
		}

		// Public: Reload current page with pjax.
		//
		// Returns whatever $.pjax returns.
		function pjaxReload(container, options) {
		  var defaults = {
		    url: window.location.href,
		    push: false,
		    replace: true,
		    scrollTo: false
		  }

		  return pjax($.extend(defaults, optionsFor(container, options)))
		}

		// Internal: Hard replace current state with url.
		//
		// Work for around WebKit
		// https://bugs.webkit.org/show_bug.cgi?id=93506
		//
		// Returns nothing.
		function locationReplace(url) {
		  window.history.replaceState(null, "", pjax.state.url)
		  window.location.replace(url)
		}


		var initialPop = true
		var initialURL = window.location.href
		var initialState = window.history.state

		// Initialize $.pjax.state if possible
		// Happens when reloading a page and coming forward from a different
		// session history.
		if (initialState && initialState.container) {
		  pjax.state = initialState
		}

		// Non-webkit browsers don't fire an initial popstate event
		if ('state' in window.history) {
		  initialPop = false
		}

		// popstate handler takes care of the back and forward buttons
		//
		// You probably shouldn't use pjax on pages with other pushState
		// stuff yet.
		function onPjaxPopstate(event) {

		  // Hitting back or forward should override any pending PJAX request.
		  if (!initialPop) {
		    abortXHR(pjax.xhr)
		  }

		  var previousState = pjax.state
		  var state = event.state
		  var direction

		  if (state && state.container) {
		    // When coming forward from a separate history session, will get an
		    // initial pop with a state we are already at. Skip reloading the
			// current
		    // page.
		    if (initialPop && initialURL == state.url) return

		    if (previousState) {
		      // If popping back to the same state, just skip.
		      // Could be clicking back from hashchange rather than a
				// pushState.
		      if (previousState.id === state.id) return

		      // Since state IDs always increase, we can deduce the navigation
				// direction
		      direction = previousState.id < state.id ? 'forward' : 'back'
		    }

		    var cache = cacheMapping[state.id] || []
		    var container = $(cache[0] || state.container), contents = cache[1]

		    if (container.length) {
		      if (previousState) {
		        // Cache current container before replacement and inform the
		        // cache which direction the history shifted.
		        cachePop(direction, previousState.id, cloneContents(container))
		      }

		      var popstateEvent = $.Event('pjax:popstate', {
		        state: state,
		        direction: direction
		      })
		      container.trigger(popstateEvent)

		      var options = {
		        id: state.id,
		        url: state.url,
		        container: container,
		        push: false,
		        fragment: state.fragment,
		        timeout: state.timeout,
		        scrollTo: false
		      }

		      if (contents) {
		        container.trigger('pjax:start', [null, options])

		        pjax.state = state
		        if (state.title) document.title = state.title
		        var beforeReplaceEvent = $.Event('pjax:beforeReplace', {
		          state: state,
		          previousState: previousState
		        })
		        container.trigger(beforeReplaceEvent, [contents, options])
		        container.html(contents)

		        container.trigger('pjax:end', [null, options])
		      } else {
		        pjax(options)
		      }

		      // Force reflow/relayout before the browser tries to restore the
		      // scroll position.
		      container[0].offsetHeight
		    } else {
		      locationReplace(location.href)
		    }
		  }
		  initialPop = false
		}

		// Fallback version of main pjax function for browsers that don't
		// support pushState.
		//
		// Returns nothing since it retriggers a hard form submission.
		function fallbackPjax(options) {
		  var url = $.isFunction(options.url) ? options.url() : options.url,
		      method = options.type ? options.type.toUpperCase() : 'GET'

		  var form = $('<form>', {
		    method: method === 'GET' ? 'GET' : 'POST',
		    action: url,
		    style: 'display:none'
		  })

		  if (method !== 'GET' && method !== 'POST') {
		    form.append($('<input>', {
		      type: 'hidden',
		      name: '_method',
		      value: method.toLowerCase()
		    }))
		  }

		  var data = options.data
		  if (typeof data === 'string') {
		    $.each(data.split('&'), function(index, value) {
		      var pair = value.split('=')
		      form.append($('<input>', {type: 'hidden', name: pair[0], value: pair[1]}))
		    })
		  } else if ($.isArray(data)) {
		    $.each(data, function(index, value) {
		      form.append($('<input>', {type: 'hidden', name: value.name, value: value.value}))
		    })
		  } else if (typeof data === 'object') {
		    var key
		    for (key in data)
		      form.append($('<input>', {type: 'hidden', name: key, value: data[key]}))
		  }

		  $(document.body).append(form)
		  form.submit()
		}

		// Internal: Abort an XmlHttpRequest if it hasn't been completed,
		// also removing its event handlers.
		function abortXHR(xhr) {
		  if ( xhr && xhr.readyState < 4) {
		    xhr.onreadystatechange = $.noop
		    xhr.abort()
		  }
		}

		// Internal: Generate unique id for state object.
		//
		// Use a timestamp instead of a counter since ids should still be
		// unique across page loads.
		//
		// Returns Number.
		function uniqueId() {
		  return (new Date).getTime()
		}

		function cloneContents(container) {
		  var cloned = container.clone()
		  // Unmark script tags as already being eval'd so they can get
			// executed again
		  // when restored from cache. HAXX: Uses jQuery internal method.
		  cloned.find('script').each(function(){
		    if (!this.src) jQuery._data(this, 'globalEval', false)
		  })
		  return [container.selector, cloned.contents()]
		}

		// Internal: Strip internal query params from parsed URL.
		//
		// Returns sanitized url.href String.
		function stripInternalParams(url) {
		  url.search = url.search.replace(/([?&])(_pjax|_)=[^&]*/g, '')
		  return url.href.replace(/\?($|#)/, '$1')
		}

		// Internal: Parse URL components and returns a Locationish object.
		//
		// url - String URL
		//
		// Returns HTMLAnchorElement that acts like Location.
		function parseURL(url) {
		  var a = document.createElement('a')
		  a.href = url
		  return a
		}

		// Internal: Return the `href` component of given URL object with the
		// hash
		// portion removed.
		//
		// location - Location or HTMLAnchorElement
		//
		// Returns String
		function stripHash(location) {
		  return location.href.replace(/#.*/, '')
		}

		// Internal: Build options Object for arguments.
		//
		// For convenience the first parameter can be either the container or
		// the options object.
		//
		// Examples
		//
		// optionsFor('#container')
		// // => {container: '#container'}
		//
		// optionsFor('#container', {push: true})
		// // => {container: '#container', push: true}
		//
		// optionsFor({container: '#container', push: true})
		// // => {container: '#container', push: true}
		//
		// Returns options Object.
		function optionsFor(container, options) {
		  // Both container and options
		  if ( container && options )
		    options.container = container

		  // First argument is options Object
		  else if ( $.isPlainObject(container) )
		    options = container

		  // Only container
		  else
		    options = {container: container}

		  // Find and validate container
		  if (options.container)
		    options.container = findContainerFor(options.container)

		  return options
		}

		// Internal: Find container element for a variety of inputs.
		//
		// Because we can't persist elements using the history API, we must be
		// able to find a String selector that will consistently find the
		// Element.
		//
		// container - A selector String, jQuery object, or DOM Element.
		//
		// Returns a jQuery object whose context is `document` and has a
		// selector.
		function findContainerFor(container) {
		  container = $(container)

		  if ( !container.length ) {
		    throw "no pjax container for " + container.selector
		  } else if ( container.selector !== '' && container.context === document ) {
		    return container
		  } else if ( container.attr('id') ) {
		    return $('#' + container.attr('id'))
		  } else {
		    throw "cant get selector for pjax container!"
		  }
		}

		// Internal: Filter and find all elements matching the selector.
		//
		// Where $.fn.find only matches descendants, findAll will test all the
		// top level elements in the jQuery object as well.
		//
		// elems - jQuery object of Elements
		// selector - String selector to match
		//
		// Returns a jQuery object.
		function findAll(elems, selector) {
		  return elems.filter(selector).add(elems.find(selector));
		}

		function parseHTML(html) {
		  return $.parseHTML(html, document, true)
		}

		// Internal: Extracts container and metadata from response.
		//
		// 1. Extracts X-PJAX-URL header if set
		// 2. Extracts inline <title> tags
		// 3. Builds response Element and extracts fragment if set
		//
		// data - String response data
		// xhr - XHR response
		// options - pjax options Object
		//
		// Returns an Object with url, title, and contents keys.
		function extractContainer(data, xhr, options) {
		  var obj = {}, fullDocument = /<html/i.test(data)

		  // Prefer X-PJAX-URL header if it was set, otherwise fallback to
		  // using the original requested url.
		  var serverUrl = xhr.getResponseHeader('X-PJAX-URL')
		  obj.url = serverUrl ? stripInternalParams(parseURL(serverUrl)) : options.requestUrl

		  // Attempt to parse response html into elements
		  if (fullDocument) {
		    var $head = $(parseHTML(data.match(/<head[^>]*>([\s\S.]*)<\/head>/i)[0]))
		    var $body = $(parseHTML(data.match(/<body[^>]*>([\s\S.]*)<\/body>/i)[0]))
		  } else {
		    var $head = $body = $(parseHTML(data))
		  }

		  // If response data is empty, return fast
		  if ($body.length === 0)
		    return obj

		  // If there's a <title> tag in the header, use it as
		  // the page's title.
		  obj.title = findAll($head, 'title').last().text()

		  if (options.fragment) {
		    // If they specified a fragment, look for it in the response
		    // and pull it out.
		    if (options.fragment === 'body') {
		      var $fragment = $body
		    } else {
		      var $fragment = findAll($body, options.fragment).first()
		    }

		    if ($fragment.length) {
		      obj.contents = options.fragment === 'body' ? $fragment : $fragment.contents()

		      // If there's no title, look for data-title and title attributes
		      // on the fragment
		      if (!obj.title)
		        obj.title = $fragment.attr('title') || $fragment.data('title')
		    }

		  } else if (!fullDocument) {
		    obj.contents = $body
		  }

		  // Clean up any <title> tags
		  if (obj.contents) {
		    // Remove any parent title elements
		    obj.contents = obj.contents.not(function() { return $(this).is('title') })

		    // Then scrub any titles from their descendants
		    obj.contents.find('title').remove()

		    // Gather all script[src] elements
		    obj.scripts = findAll(obj.contents, 'script[src]').remove()
		    obj.contents = obj.contents.not(obj.scripts)
		  }

		  // Trim any whitespace off the title
		  if (obj.title) obj.title = $.trim(obj.title)

		  return obj
		}

		// Load an execute scripts using standard script request.
		//
		// Avoids jQuery's traditional $.getScript which does a XHR request and
		// globalEval.
		//
		// scripts - jQuery object of script Elements
		//
		// Returns nothing.
		function executeScriptTags(scripts) {
		  if (!scripts) return

		  var existingScripts = $('script[src]')

		  scripts.each(function() {
		    var src = this.src
		    var matchedScripts = existingScripts.filter(function() {
		      return this.src === src
		    })
		    if (matchedScripts.length) return

		    var script = document.createElement('script')
		    var type = $(this).attr('type')
		    if (type) script.type = type
		    script.src = $(this).attr('src')
		    document.head.appendChild(script)
		  })
		}

		// Internal: History DOM caching class.
		var cacheMapping      = {}
		var cacheForwardStack = []
		var cacheBackStack    = []

		// Push previous state id and container contents into the history
		// cache. Should be called in conjunction with `pushState` to save the
		// previous container contents.
		//
		// id - State ID Number
		// value - DOM Element to cache
		//
		// Returns nothing.
		function cachePush(id, value) {
		  cacheMapping[id] = value
		  cacheBackStack.push(id)

		  // Remove all entries in forward history stack after pushing a new
			// page.
		  trimCacheStack(cacheForwardStack, 0)

		  // Trim back history stack to max cache length.
		  trimCacheStack(cacheBackStack, pjax.defaults.maxCacheLength)
		}

		// Shifts cache from directional history cache. Should be
		// called on `popstate` with the previous state id and container
		// contents.
		//
		// direction - "forward" or "back" String
		// id - State ID Number
		// value - DOM Element to cache
		//
		// Returns nothing.
		function cachePop(direction, id, value) {
		  var pushStack, popStack
		  cacheMapping[id] = value

		  if (direction === 'forward') {
		    pushStack = cacheBackStack
		    popStack  = cacheForwardStack
		  } else {
		    pushStack = cacheForwardStack
		    popStack  = cacheBackStack
		  }

		  pushStack.push(id)
		  if (id = popStack.pop())
		    delete cacheMapping[id]

		  // Trim whichever stack we just pushed to to max cache length.
		  trimCacheStack(pushStack, pjax.defaults.maxCacheLength)
		}

		// Trim a cache stack (either cacheBackStack or cacheForwardStack) to be
		// no
		// longer than the specified length, deleting cached DOM elements as
		// necessary.
		//
		// stack - Array of state IDs
		// length - Maximum length to trim to
		//
		// Returns nothing.
		function trimCacheStack(stack, length) {
		  while (stack.length > length)
		    delete cacheMapping[stack.shift()]
		}

		// Public: Find version identifier for the initial page load.
		//
		// Returns String version or undefined.
		function findVersion() {
		  return $('meta').filter(function() {
		    var name = $(this).attr('http-equiv')
		    return name && name.toUpperCase() === 'X-PJAX-VERSION'
		  }).attr('content')
		}

		// Install pjax functions on $.pjax to enable pushState behavior.
		//
		// Does nothing if already enabled.
		//
		// Examples
		//
// $.pjax.enable()
		//
		// Returns nothing.
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
		  $(window).on('popstate.pjax', onPjaxPopstate)
		}

		// Disable pushState behavior.
		//
		// This is the case when a browser doesn't support pushState. It is
		// sometimes useful to disable pushState for debugging on a modern
		// browser.
		//
		// Examples
		//
// $.pjax.disable()
		//
		// Returns nothing.
		function disable() {
		  $.fn.pjax = function() { return this }
		  $.pjax = fallbackPjax
		  $.pjax.enable = enable
		  $.pjax.disable = $.noop
		  $.pjax.click = $.noop
		  $.pjax.submit = $.noop
		  $.pjax.reload = function() { window.location.reload() }

		  $(window).off('popstate.pjax', onPjaxPopstate)
		}


		// Add the state property to jQuery's event object so we can use it in
		// $(window).bind('popstate')
		if ( $.inArray('state', $.event.props) < 0 )
		  $.event.props.push('state')

		// Is pjax supported by this browser?
		$.support.pjax =
		  window.history && window.history.pushState && window.history.replaceState &&
		  // pushState isn't reliable on iOS until 5.
		  !navigator.userAgent.match(/((iPod|iPhone|iPad).+\bOS\s+[1-4]\D|WebApps\/.+CFNetwork)/)

		$.support.pjax ? enable() : disable()
	/* animate scroll */
	// defines various easing effects
	$.easing['jswing'] = $.easing['swing'];
	$.extend($.easing, {
		def : 'easeOutQuad',
		swing : function(x, t, b, c, d) {
			return $.easing[$.easing.def](x, t, b, c, d);
		},
		easeInQuad : function(x, t, b, c, d) {
			return c * (t /= d) * t + b;
		},
		easeOutQuad : function(x, t, b, c, d) {
			return -c * (t /= d) * (t - 2) + b;
		},
		easeInOutQuad : function(x, t, b, c, d) {
			if ((t /= d / 2) < 1)
				return c / 2 * t * t + b;
			return -c / 2 * ((--t) * (t - 2) - 1) + b;
		},
		easeInCubic : function(x, t, b, c, d) {
			return c * (t /= d) * t * t + b;
		},
		easeOutCubic : function(x, t, b, c, d) {
			return c * ((t = t / d - 1) * t * t + 1) + b;
		},
		easeInOutCubic : function(x, t, b, c, d) {
			if ((t /= d / 2) < 1)
				return c / 2 * t * t * t + b;
			return c / 2 * ((t -= 2) * t * t + 2) + b;
		},
		easeInQuart : function(x, t, b, c, d) {
			return c * (t /= d) * t * t * t + b;
		},
		easeOutQuart : function(x, t, b, c, d) {
			return -c * ((t = t / d - 1) * t * t * t - 1) + b;
		},
		easeInOutQuart : function(x, t, b, c, d) {
			if ((t /= d / 2) < 1)
				return c / 2 * t * t * t * t + b;
			return -c / 2 * ((t -= 2) * t * t * t - 2) + b;
		},
		easeInQuint : function(x, t, b, c, d) {
			return c * (t /= d) * t * t * t * t + b;
		},
		easeOutQuint : function(x, t, b, c, d) {
			return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
		},
		easeInOutQuint : function(x, t, b, c, d) {
			if ((t /= d / 2) < 1)
				return c / 2 * t * t * t * t * t + b;
			return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
		},
		easeInSine : function(x, t, b, c, d) {
			return -c * Math.cos(t / d * (Math.PI / 2)) + c + b;
		},
		easeOutSine : function(x, t, b, c, d) {
			return c * Math.sin(t / d * (Math.PI / 2)) + b;
		},
		easeInOutSine : function(x, t, b, c, d) {
			return -c / 2 * (Math.cos(Math.PI * t / d) - 1) + b;
		},
		easeInExpo : function(x, t, b, c, d) {
			return (t == 0) ? b : c * Math.pow(2, 10 * (t / d - 1)) + b;
		},
		easeOutExpo : function(x, t, b, c, d) {
			return (t == d) ? b + c : c * (-Math.pow(2, -10 * t / d) + 1) + b;
		},
		easeInOutExpo : function(x, t, b, c, d) {
			if (t == 0)
				return b;
			if (t == d)
				return b + c;
			if ((t /= d / 2) < 1)
				return c / 2 * Math.pow(2, 10 * (t - 1)) + b;
			return c / 2 * (-Math.pow(2, -10 * --t) + 2) + b;
		},
		easeInCirc : function(x, t, b, c, d) {
			return -c * (Math.sqrt(1 - (t /= d) * t) - 1) + b;
		},
		easeOutCirc : function(x, t, b, c, d) {
			return c * Math.sqrt(1 - (t = t / d - 1) * t) + b;
		},
		easeInOutCirc : function(x, t, b, c, d) {
			if ((t /= d / 2) < 1)
				return -c / 2 * (Math.sqrt(1 - t * t) - 1) + b;
			return c / 2 * (Math.sqrt(1 - (t -= 2) * t) + 1) + b;
		},
		easeInElastic : function(x, t, b, c, d) {
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
		easeOutElastic : function(x, t, b, c, d) {
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
		easeInOutElastic : function(x, t, b, c, d) {
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
		easeInBack : function(x, t, b, c, d, s) {
			if (s == undefined)
				s = 1.70158;
			return c * (t /= d) * t * ((s + 1) * t - s) + b;
		},
		easeOutBack : function(x, t, b, c, d, s) {
			if (s == undefined)
				s = 1.70158;
			return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
		},
		easeInOutBack : function(x, t, b, c, d, s) {
			if (s == undefined)
				s = 1.70158;
			if ((t /= d / 2) < 1)
				return c / 2 * (t * t * (((s *= (1.525)) + 1) * t - s)) + b;
			return c / 2 * ((t -= 2) * t * (((s *= (1.525)) + 1) * t + s) + 2) + b;
		},
		easeInBounce : function(x, t, b, c, d) {
			return c - $.easing.easeOutBounce(x, d - t, 0, c, d) + b;
		},
		easeOutBounce : function(x, t, b, c, d) {
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
		easeInOutBounce : function(x, t, b, c, d) {
			if (t < d / 2)
				return $.easing.easeInBounce(x, t * 2, 0, c, d) * .5 + b;
			return $.easing.easeOutBounce(x, t * 2 - d, 0, c, d) * .5 + c * .5 + b;
		}
	});

	$.fn.animatescroll = function(options) {

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
				scrollTop : offset - opts.padding
			}, opts.scrollSpeed, opts.easing);
		} else {
			// Scroll the element to the desired position
			$(opts.element).stop().animate({
				scrollTop : this.offset().top - this.parent().offset().top + this.parent().scrollTop() - opts.padding
			}, opts.scrollSpeed, opts.easing);
		}

		setTimeout(function() {

			// make sure the callback is a function
			if (typeof opts.onScrollEnd == 'function') {
				// brings the scope to the callback
				opts.onScrollEnd.call(this);
			}
		}, opts.scrollSpeed);
	};

	// default options
	$.fn.animatescroll.defaults = {
		easing : "swing",
		scrollSpeed : 800,
		padding : 0,
		element : "html,body"
	};

	$.fn.Lc4eAnimate = function(options) {
		var defaults = {
			speed : "normal",
			animation : "slideInDown",
			show : true,
			interval : 100,
			onComplete : function($thedom) {
			},
			onFinish : function($that) {

			},
			onBefore : function($thedom) {
			}
		}, $that = $(this), count = 0;
		var requestAnimationFrame = (function() {
			return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(callback) {
				window.setTimeout(callback, 0);
			};
		})();

		function animate() {
			var $thedom = $($that[count]), animateClass = ((options.speed == 'normal') ? 'animated' : ('animated-' + options.speed)) + ' ' + options.animation;
			options.onBefore();
			$thedom.show();
			$thedom.addClass(animateClass).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
				$thedom.removeClass(animateClass)
				options.onComplete($thedom);
				if (options.show) {
					$thedom.show();
				} else {
					$thedom.hide();
				}
				if (count == $that.length - 1) {
					options.onFinish($that);
				}
			});
			count++;
		}

		function animateIndex() {
			return function() {
				requestAnimationFrame(animate);
			}
		}
		options = $.extend(defaults, options);
		for (var i = 0, len = $that.length; i < len; i++) {
			setTimeout(animateIndex(), options.interval * i);
		}
		return $that;
	}
	$.fn.Lc4eDimmer = function(options, data) {
		var defaults = {
			type : 'loader', // loader or dimmer
			color : 'black',
			content : "Loading",
		}, loaderDefaults = {
			size : 'small', // small mini medium large
			direction : "" // indeterminate
		}, template = '<div class="ui active {Color} dimmer">{Content}</div>',

		Loadertemplate = '<div class="ui {Direction} {Size} text loader">{Content}</div>';

		return this.each(function() {
			var $that = $(this), html = "";
			switch (options) {
			case "toggle": {
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
			case "hide": {
				$that.find('.ui.active.dimmer').removeClass('active');
				break;
			}
			case "show": {
				$that.find('.ui.dimmer').addClass('active')
				break;
			}
			case "remove": {
				$that.find('.ui.dimmer').remove();
				break;
			}
			default: {
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
	$.fn.Lc4eModal = function(options) {
		var defaults = {
			Id : null,
			title : "Message",
			content : "This is a Lc4e Test Modal",
			closable : true, // enable esc or click dim page to close
			// modal
			useCSS : true,
			closeIcon : true,
			transition : "scale", // extend transition
			duration : 400,// animation
			type : "basic", // basic standard
			size : "small", // fullscreen ,small,large,long
			allowMultiple : false, // multiple modal
			offset : 2,
			context : 'body',
			queue : false,
			easing : "easeOutExpo",
			selector : {
				close : '.close',
			},
			dimmerSettings : {
				closable : false,
				useCSS : true
			},
			onDeny : function() {
			},
			onApprove : function() {
			},
			onShow : function() {
			},
			onVisible : function() {

			},
			onHide : function() {
			},
			onAfterHide : function() {
			},
			onHidden : function() {
			},
			OtherButtons : [],
			OtherButtonsClass : [],
			OtherButtonsClick : [],
		}, basciDefaults = {
			IconClass : 'warning circle',
			bottonNames : [ '<i class="Remove icon"></i>No', '<i class="checkmark icon"></i>Yes' ],
			buttonClass : [ "deny close red basic inverted", "approve close green basic inverted" ]
		}, standardDefaults = {
			bottonNames : [ 'Close' ],
			buttonClass : [ 'basic close' ]
		}, basicModalHtml = '<div id="{ModalId}" class="ui basic {Mutiple} {Size} modal">{CloseIcon}<div class="header">{Title}</div><div class="content"><div class="image"><i class="{IconClass} icon"></i></div><div class="description">{Content}</div></div><div class="actions"><div class="{ButtonNumber} fluid ui inverted buttons">{Buttons}</div></div></div></div>', buttonHtml = '<div {ButtonId} class="ui {ButtonClass} button">{ButtonName}</div>', standardModalHtml = '<div id="{ModalId}" class="ui standard {Mutiple} {Size} modal">{CloseIcon}<div class="header">{Title}</div><div class="content">{Content}</div><div class="actions">{Buttons}</div></div>', NumberEng = [
				'one', 'two', 'three', 'four', 'five', 'six' ], $operate;
		options = $.extend(defaults, options);

		this.each(function() {
			var $obj = $(this), modalId = options.Id == null ? "Lc4eModal-" + $.Lc4eRandom() : options.Id, $modalObj, html = "", buttonsHtml = "";
			switch (options.type) {
			case "basic": {
				options = $.extend(basciDefaults, options);
				html = basicModalHtml;
				html = html.replace(/{IconClass}/, options.IconClass).replace(/{ButtonNumber}/, NumberEng[options.bottonNames.length - 1]);
				break;
			}
			case "standard": {
				options = $.extend(standardDefaults, options);
				html = standardModalHtml;
				break;
			}
			default: {
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
			options.onHidden = function() {
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

	$.fn.Lc4eProgress = function(option, data) {
		var defaults = {
			type : "standard",
			color : "blue",
			autoSuccess : true,
			showActivity : true,
			limitValues : true,
			label : "percent",
			precision : 0.001,
			indicating : true,
			total : false,
			value : false,
			autoUpdateSpeed : 200,
			autoUpdate : true,
			showLoading : true,
			onChange : function(percent, value, total) {
			},
			onComplete : function(total) {
			},
			onSuccess : function(total) {
			},
			onActive : function(value, total) {
			},
			onError : function(value, total) {
			},
			onWarning : function(value, total) {
			}
		}, attachDefaults = {
			location : "bottom"
		}, standardtemplate = '<div id="lc4eProgress"><div class="ui mini progress {Indicating} {Color}" id="lc4eProgressBar"><div class="bar"></div></div>{Loading}</div>', attachedBar = '<div {Id} class="ui attached progress {Color} {Indicating} {Location}"><div class="bar"></div></div>', $operate;

		var options, $that = $(this), html = "", template = "", progressId = "", $progressBar;
		switch (option) {
		case "setPercent": {
			$progressBar = $that;
			$that.progress({
				percent : data
			});
			break;
		}
		case "end": {
			$progressBar = $that;
			$that.progress({
				percent : '100'
			}).find('.bar').one('webkitTransitionEnd transitionend mozTransitionEnd oTransitionEnd', function() {
				if ($progressBar.attr("id") == "lc4eProgressBar") {
					$('#lc4eProgress').remove();
				} else {
					$progressBar.remove();
				}
			});
			break;
		}
		case "hide": {
			$progressBar = undefined;
			$that.remove();
			break;
		}
		case "increment": {
			$progressBar = $that;
			$that.progress("increment");
			break;
		}
		case "autoincrement": {
			$progressBar = $that;
			var progressInter = setInterval(function() {
				$progressBar.progress('increment');
			}, options.autoUpdateSpeed);
			$that.data('interval', progressInter);
			break;
		}
		case "stop": {
			$progressBar = $that;
			clearInterval($that.data('interval'));
			$that.removeData('interval');
			break;
		}
		default: {
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

			options.onSuccess = function(total) {
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
				var progressInter = setInterval(function() {
					$progressBar.progress('increment');
				}, options.autoUpdateSpeed);
				$progressBar.data('interval', progressInter);
			}

			break;
		}
		}
		return $progressBar;
	}
	$.extend({
		Lc4eAjax : function(data) {
			if (typeof data.bfSend == "function") {
				data.beforeSend = function(xhr) {
					var tk = 'l' + 'c' + '4' + 'e' + '-' + 't' + 'o' + 'k' + 'e' + 'n', l = data.url.length.toString(), t = new Date().getTime().toString();
					console.log(l + t + l);
					xhr.setRequestHeader(tk, l + t + l);
					data.bfSend.call();
				}
			}
			$.ajax(data);
		},
		Lc4eRandom : function() {
			function random(a, b) {
				return Math.random() > 0.5 ? -1 : 1;
			}
			return [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'Q', 'q', 'W', 'w', 'E', 'e', 'R', 'r', 'T', 't', 'Y', 'y', 'U', 'u', 'I', 'i', 'O', 'o', 'P', 'p', 'A', 'a', 'S', 's', 'D', 'd', 'F', 'f', 'G', 'g', 'H', 'h', 'J', 'j', 'K', 'k', 'L', 'l', 'Z', 'z', 'X', 'x', 'C', 'c', 'V',
					'v', 'B', 'b', 'N', 'n', 'M', 'm' ].sort(random).join('').substring(5, 20);
		},
		Lc4eModal : function(options) {
			return $("body").Lc4eModal(options);
		},
		Lc4eToDate : function(unixTime) {
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
		Lc4eProgress : function(option, data) {
			return $("body").Lc4eProgress(option, data);
		}
	});
})(jQuery);
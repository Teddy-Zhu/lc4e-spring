/*!
 * Lc4e Javascript Library
 * author zhuxi - v1.0.0 (2015-04-09T11:23:51+0800)
 * http://www.lc4e.com/ | Released under MIT license
 * 
 * Include jquery (http://jquery.com/) semantic-ui (http://semantic-ui.com/) animatescroll
 * waypoint (http://imakewebthings.com/waypoints)
 */

(function($) {

	/* waypoint */
	/*
	 * ! Waypoints - 3.1.1 Copyright © 2011-2015 Caleb Troughton Licensed under
	 * the MIT license.
	 * https://github.com/imakewebthings/waypoints/blog/master/licenses.txt
	 */
	(function() {
		'use strict'

		var keyCounter = 0
		var allWaypoints = {}

		/* http://imakewebthings.com/waypoints/api/waypoint */
		function Waypoint(options) {
			if (!options) {
				throw new Error('No options passed to Waypoint constructor')
			}
			if (!options.element) {
				throw new Error('No element option passed to Waypoint constructor')
			}
			if (!options.handler) {
				throw new Error('No handler option passed to Waypoint constructor')
			}

			this.key = 'waypoint-' + keyCounter
			this.options = Waypoint.Adapter.extend({}, Waypoint.defaults, options)
			this.element = this.options.element
			this.adapter = new Waypoint.Adapter(this.element)
			this.callback = options.handler
			this.axis = this.options.horizontal ? 'horizontal' : 'vertical'
			this.enabled = this.options.enabled
			this.triggerPoint = null
			this.group = Waypoint.Group.findOrCreate({
				name : this.options.group,
				axis : this.axis
			})
			this.context = Waypoint.Context.findOrCreateByElement(this.options.context)

			if (Waypoint.offsetAliases[this.options.offset]) {
				this.options.offset = Waypoint.offsetAliases[this.options.offset]
			}
			this.group.add(this)
			this.context.add(this)
			allWaypoints[this.key] = this
			keyCounter += 1
		}

		/* Private */
		Waypoint.prototype.queueTrigger = function(direction) {
			this.group.queueTrigger(this, direction)
		}

		/* Private */
		Waypoint.prototype.trigger = function(args) {
			if (!this.enabled) {
				return

			}
			if (this.callback) {
				this.callback.apply(this, args)
			}
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/destroy */
		Waypoint.prototype.destroy = function() {
			this.context.remove(this)
			this.group.remove(this)
			delete allWaypoints[this.key]
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/disable */
		Waypoint.prototype.disable = function() {
			this.enabled = false
			return this
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/enable */
		Waypoint.prototype.enable = function() {
			this.context.refresh()
			this.enabled = true
			return this
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/next */
		Waypoint.prototype.next = function() {
			return this.group.next(this)
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/previous */
		Waypoint.prototype.previous = function() {
			return this.group.previous(this)
		}

		/* Private */
		Waypoint.invokeAll = function(method) {
			var allWaypointsArray = []
			for ( var waypointKey in allWaypoints) {
				allWaypointsArray.push(allWaypoints[waypointKey])
			}
			for (var i = 0, end = allWaypointsArray.length; i < end; i++) {
				allWaypointsArray[i][method]()
			}
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/destroy-all */
		Waypoint.destroyAll = function() {
			Waypoint.invokeAll('destroy')
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/disable-all */
		Waypoint.disableAll = function() {
			Waypoint.invokeAll('disable')
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/enable-all */
		Waypoint.enableAll = function() {
			Waypoint.invokeAll('enable')
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/refresh-all */
		Waypoint.refreshAll = function() {
			Waypoint.Context.refreshAll()
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/viewport-height */
		Waypoint.viewportHeight = function() {
			return window.innerHeight || document.documentElement.clientHeight
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/viewport-width */
		Waypoint.viewportWidth = function() {
			return document.documentElement.clientWidth
		}

		Waypoint.adapters = []

		Waypoint.defaults = {
			context : window,
			continuous : true,
			enabled : true,
			group : 'default',
			horizontal : false,
			offset : 0
		}

		Waypoint.offsetAliases = {
			'bottom-in-view' : function() {
				return this.context.innerHeight() - this.adapter.outerHeight()
			},
			'right-in-view' : function() {
				return this.context.innerWidth() - this.adapter.outerWidth()
			}
		}

		window.Waypoint = Waypoint
	}());
	(function() {
		'use strict'

		function requestAnimationFrameShim(callback) {
			window.setTimeout(callback, 1000 / 60)
		}

		var keyCounter = 0
		var contexts = {}
		var Waypoint = window.Waypoint
		var oldWindowLoad = window.onload

		/* http://imakewebthings.com/waypoints/api/context */
		function Context(element) {
			this.element = element
			this.Adapter = Waypoint.Adapter
			this.adapter = new this.Adapter(element)
			this.key = 'waypoint-context-' + keyCounter
			this.didScroll = false
			this.didResize = false
			this.oldScroll = {
				x : this.adapter.scrollLeft(),
				y : this.adapter.scrollTop()
			}
			this.waypoints = {
				vertical : {},
				horizontal : {}
			}

			element.waypointContextKey = this.key
			contexts[element.waypointContextKey] = this
			keyCounter += 1

			this.createThrottledScrollHandler()
			this.createThrottledResizeHandler()
		}

		/* Private */
		Context.prototype.add = function(waypoint) {
			var axis = waypoint.options.horizontal ? 'horizontal' : 'vertical'
			this.waypoints[axis][waypoint.key] = waypoint
			this.refresh()
		}

		/* Private */
		Context.prototype.checkEmpty = function() {
			var horizontalEmpty = this.Adapter.isEmptyObject(this.waypoints.horizontal)
			var verticalEmpty = this.Adapter.isEmptyObject(this.waypoints.vertical)
			if (horizontalEmpty && verticalEmpty) {
				this.adapter.off('.waypoints')
				delete contexts[this.key]
			}
		}

		/* Private */
		Context.prototype.createThrottledResizeHandler = function() {
			var self = this

			function resizeHandler() {
				self.handleResize()
				self.didResize = false
			}

			this.adapter.on('resize.waypoints', function() {
				if (!self.didResize) {
					self.didResize = true
					Waypoint.requestAnimationFrame(resizeHandler)
				}
			})
		}

		/* Private */
		Context.prototype.createThrottledScrollHandler = function() {
			var self = this
			function scrollHandler() {
				self.handleScroll()
				self.didScroll = false
			}

			this.adapter.on('scroll.waypoints', function() {
				if (!self.didScroll || Waypoint.isTouch) {
					self.didScroll = true
					Waypoint.requestAnimationFrame(scrollHandler)
				}
			})
		}

		/* Private */
		Context.prototype.handleResize = function() {
			Waypoint.Context.refreshAll()
		}

		/* Private */
		Context.prototype.handleScroll = function() {
			var triggeredGroups = {}
			var axes = {
				horizontal : {
					newScroll : this.adapter.scrollLeft(),
					oldScroll : this.oldScroll.x,
					forward : 'right',
					backward : 'left'
				},
				vertical : {
					newScroll : this.adapter.scrollTop(),
					oldScroll : this.oldScroll.y,
					forward : 'down',
					backward : 'up'
				}
			}

			for ( var axisKey in axes) {
				var axis = axes[axisKey]
				var isForward = axis.newScroll > axis.oldScroll
				var direction = isForward ? axis.forward : axis.backward

				for ( var waypointKey in this.waypoints[axisKey]) {
					var waypoint = this.waypoints[axisKey][waypointKey]
					var wasBeforeTriggerPoint = axis.oldScroll < waypoint.triggerPoint
					var nowAfterTriggerPoint = axis.newScroll >= waypoint.triggerPoint
					var crossedForward = wasBeforeTriggerPoint && nowAfterTriggerPoint
					var crossedBackward = !wasBeforeTriggerPoint && !nowAfterTriggerPoint
					if (crossedForward || crossedBackward) {
						waypoint.queueTrigger(direction)
						triggeredGroups[waypoint.group.id] = waypoint.group
					}
				}
			}

			for ( var groupKey in triggeredGroups) {
				triggeredGroups[groupKey].flushTriggers()
			}

			this.oldScroll = {
				x : axes.horizontal.newScroll,
				y : axes.vertical.newScroll
			}
		}

		/* Private */
		Context.prototype.innerHeight = function() {
			/* eslint-disable eqeqeq */
			if (this.element == this.element.window) {
				return Waypoint.viewportHeight()
			}
			/* eslint-enable eqeqeq */
			return this.adapter.innerHeight()
		}

		/* Private */
		Context.prototype.remove = function(waypoint) {
			delete this.waypoints[waypoint.axis][waypoint.key]
			this.checkEmpty()
		}

		/* Private */
		Context.prototype.innerWidth = function() {
			/* eslint-disable eqeqeq */
			if (this.element == this.element.window) {
				return Waypoint.viewportWidth()
			}
			/* eslint-enable eqeqeq */
			return this.adapter.innerWidth()
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/context-destroy */
		Context.prototype.destroy = function() {
			var allWaypoints = []
			for ( var axis in this.waypoints) {
				for ( var waypointKey in this.waypoints[axis]) {
					allWaypoints.push(this.waypoints[axis][waypointKey])
				}
			}
			for (var i = 0, end = allWaypoints.length; i < end; i++) {
				allWaypoints[i].destroy()
			}
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/context-refresh */
		Context.prototype.refresh = function() {
			/* eslint-disable eqeqeq */
			var isWindow = this.element == this.element.window
			/* eslint-enable eqeqeq */
			var contextOffset = this.adapter.offset()
			var triggeredGroups = {}
			var axes

			this.handleScroll()
			axes = {
				horizontal : {
					contextOffset : isWindow ? 0 : contextOffset.left,
					contextScroll : isWindow ? 0 : this.oldScroll.x,
					contextDimension : this.innerWidth(),
					oldScroll : this.oldScroll.x,
					forward : 'right',
					backward : 'left',
					offsetProp : 'left'
				},
				vertical : {
					contextOffset : isWindow ? 0 : contextOffset.top,
					contextScroll : isWindow ? 0 : this.oldScroll.y,
					contextDimension : this.innerHeight(),
					oldScroll : this.oldScroll.y,
					forward : 'down',
					backward : 'up',
					offsetProp : 'top'
				}
			}

			for ( var axisKey in axes) {
				var axis = axes[axisKey]
				for ( var waypointKey in this.waypoints[axisKey]) {
					var waypoint = this.waypoints[axisKey][waypointKey]
					var adjustment = waypoint.options.offset
					var oldTriggerPoint = waypoint.triggerPoint
					var elementOffset = 0
					var freshWaypoint = oldTriggerPoint == null
					var contextModifier, wasBeforeScroll, nowAfterScroll
					var triggeredBackward, triggeredForward

					if (waypoint.element !== waypoint.element.window) {
						elementOffset = waypoint.adapter.offset()[axis.offsetProp]
					}

					if (typeof adjustment === 'function') {
						adjustment = adjustment.apply(waypoint)
					} else if (typeof adjustment === 'string') {
						adjustment = parseFloat(adjustment)
						if (waypoint.options.offset.indexOf('%') > -1) {
							adjustment = Math.ceil(axis.contextDimension * adjustment / 100)
						}
					}

					contextModifier = axis.contextScroll - axis.contextOffset
					waypoint.triggerPoint = elementOffset + contextModifier - adjustment
					wasBeforeScroll = oldTriggerPoint < axis.oldScroll
					nowAfterScroll = waypoint.triggerPoint >= axis.oldScroll
					triggeredBackward = wasBeforeScroll && nowAfterScroll
					triggeredForward = !wasBeforeScroll && !nowAfterScroll

					if (!freshWaypoint && triggeredBackward) {
						waypoint.queueTrigger(axis.backward)
						triggeredGroups[waypoint.group.id] = waypoint.group
					} else if (!freshWaypoint && triggeredForward) {
						waypoint.queueTrigger(axis.forward)
						triggeredGroups[waypoint.group.id] = waypoint.group
					} else if (freshWaypoint && axis.oldScroll >= waypoint.triggerPoint) {
						waypoint.queueTrigger(axis.forward)
						triggeredGroups[waypoint.group.id] = waypoint.group
					}
				}
			}

			for ( var groupKey in triggeredGroups) {
				triggeredGroups[groupKey].flushTriggers()
			}

			return this
		}

		/* Private */
		Context.findOrCreateByElement = function(element) {
			return Context.findByElement(element) || new Context(element)
		}

		/* Private */
		Context.refreshAll = function() {
			for ( var contextId in contexts) {
				contexts[contextId].refresh()
			}
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/context-find-by-element */
		Context.findByElement = function(element) {
			return contexts[element.waypointContextKey]
		}

		window.onload = function() {
			if (oldWindowLoad) {
				oldWindowLoad()
			}
			Context.refreshAll()
		}

		Waypoint.requestAnimationFrame = function(callback) {
			var requestFn = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || requestAnimationFrameShim
			requestFn.call(window, callback)
		}
		Waypoint.Context = Context
	}());
	(function() {
		'use strict'

		function byTriggerPoint(a, b) {
			return a.triggerPoint - b.triggerPoint
		}

		function byReverseTriggerPoint(a, b) {
			return b.triggerPoint - a.triggerPoint
		}

		var groups = {
			vertical : {},
			horizontal : {}
		}
		var Waypoint = window.Waypoint

		/* http://imakewebthings.com/waypoints/api/group */
		function Group(options) {
			this.name = options.name
			this.axis = options.axis
			this.id = this.name + '-' + this.axis
			this.waypoints = []
			this.clearTriggerQueues()
			groups[this.axis][this.name] = this
		}

		/* Private */
		Group.prototype.add = function(waypoint) {
			this.waypoints.push(waypoint)
		}

		/* Private */
		Group.prototype.clearTriggerQueues = function() {
			this.triggerQueues = {
				up : [],
				down : [],
				left : [],
				right : []
			}
		}

		/* Private */
		Group.prototype.flushTriggers = function() {
			for ( var direction in this.triggerQueues) {
				var waypoints = this.triggerQueues[direction]
				var reverse = direction === 'up' || direction === 'left'
				waypoints.sort(reverse ? byReverseTriggerPoint : byTriggerPoint)
				for (var i = 0, end = waypoints.length; i < end; i += 1) {
					var waypoint = waypoints[i]
					if (waypoint.options.continuous || i === waypoints.length - 1) {
						waypoint.trigger([ direction ])
					}
				}
			}
			this.clearTriggerQueues()
		}

		/* Private */
		Group.prototype.next = function(waypoint) {
			this.waypoints.sort(byTriggerPoint)
			var index = Waypoint.Adapter.inArray(waypoint, this.waypoints)
			var isLast = index === this.waypoints.length - 1
			return isLast ? null : this.waypoints[index + 1]
		}

		/* Private */
		Group.prototype.previous = function(waypoint) {
			this.waypoints.sort(byTriggerPoint)
			var index = Waypoint.Adapter.inArray(waypoint, this.waypoints)
			return index ? this.waypoints[index - 1] : null
		}

		/* Private */
		Group.prototype.queueTrigger = function(waypoint, direction) {
			this.triggerQueues[direction].push(waypoint)
		}

		/* Private */
		Group.prototype.remove = function(waypoint) {
			var index = Waypoint.Adapter.inArray(waypoint, this.waypoints)
			if (index > -1) {
				this.waypoints.splice(index, 1)
			}
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/first */
		Group.prototype.first = function() {
			return this.waypoints[0]
		}

		/* Public */
		/* http://imakewebthings.com/waypoints/api/last */
		Group.prototype.last = function() {
			return this.waypoints[this.waypoints.length - 1]
		}

		/* Private */
		Group.findOrCreate = function(options) {
			return groups[options.axis][options.name] || new Group(options)
		}

		Waypoint.Group = Group
	}());
	(function() {
		'use strict'

		var $ = window.jQuery
		var Waypoint = window.Waypoint

		function JQueryAdapter(element) {
			this.$element = $(element)
		}

		$.each([ 'innerHeight', 'innerWidth', 'off', 'offset', 'on', 'outerHeight', 'outerWidth', 'scrollLeft', 'scrollTop' ], function(i, method) {
			JQueryAdapter.prototype[method] = function() {
				var args = Array.prototype.slice.call(arguments)
				return this.$element[method].apply(this.$element, args)
			}
		})

		$.each([ 'extend', 'inArray', 'isEmptyObject' ], function(i, method) {
			JQueryAdapter[method] = $[method]
		})

		Waypoint.adapters.push({
			name : 'jquery',
			Adapter : JQueryAdapter
		})
		Waypoint.Adapter = JQueryAdapter
	}());
	(function() {
		'use strict'

		var Waypoint = window.Waypoint

		function createExtension(framework) {
			return function() {
				var waypoints = []
				var overrides = arguments[0]

				if (framework.isFunction(arguments[0])) {
					overrides = framework.extend({}, arguments[1])
					overrides.handler = arguments[0]
				}

				this.each(function() {
					var options = framework.extend({}, overrides, {
						element : this
					})
					if (typeof options.context === 'string') {
						options.context = framework(this).closest(options.context)[0]
					}
					waypoints.push(new Waypoint(options))
				})

				return waypoints
			}
		}

		if (window.jQuery) {
			window.jQuery.fn.waypoint = createExtension(window.jQuery)
		}
		if (window.Zepto) {
			window.Zepto.fn.waypoint = createExtension(window.Zepto)
		}
	}());

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
		}, $that = $(this), animate = function($thedom, complete) {
			var animateClass = ((options.speed == 'normal') ? 'animated' : ('animated-' + options.speed)) + ' ' + options.animation;
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
				if (complete) {
					options.onFinish($that);
				}
			});
		};
		function animateIndex(i, complete) {
			return function() {
				animate($($that[i]), complete);
			}
		}
		options = $.extend(defaults, options);
		for (var i = 0, len = $that.length; i < len; i++) {
			if (i == $that.length - 1) {
				setTimeout(animateIndex(i, true), options.interval * i);
			} else {
				setTimeout(animateIndex(i), options.interval * i);
			}
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
})(jQuery)
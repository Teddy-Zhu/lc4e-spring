require.config({
	paths : {
		"jquery" : "/plugins/jquery/jquery.min",
		"semantic" : "/plugins/semantic-ui/semantic.min",
		"lc4e" : "/plugins/lc4e/jquery-extend",
		"se-accordion" : "/plugins/semantic-ui/components/accordion.min",
		"se-api" : "/plugins/semantic-ui/components/api.min",
		"se-breadcrumb" : "/plugins/semantic-ui/components/breadcrumb.min",
		"se-checkbox" : "/plugins/semantic-ui/components/checkbox.min",
		"se-dimmer" : "/plugins/semantic-ui/components/dimmer.min",
		"se-form" : "/plugins/semantic-ui/components/form.min",
		"se-dropdown" : "/plugins/semantic-ui/components/dropdown.min",
		"se-modal" : "/plugins/semantic-ui/components/modal.min",
		"se-nag" : "/plugins/semantic-ui/components/nag.min",
		"se-popup" : "/plugins/semantic-ui/components/popup.min",
		"se-progress" : "/plugins/semantic-ui/components/progress.min",
		"se-rating" : "/plugins/semantic-ui/components/rating.min",
		"se-search" : "/plugins/semantic-ui/components/search.min",
		"se-shape" : "/plugins/semantic-ui/components/shape.min",
		"se-sidebar" : "/plugins/semantic-ui/components/sidebar.min",
		"se-site" : "/plugins/semantic-ui/components/site.min",
		"se-state" : "/plugins/semantic-ui/components/state.min",
		"se-sticky" : "/plugins/semantic-ui/components/sticky.min",
		"se-tab" : "/plugins/semantic-ui/components/tab.min",
		"se-table" : "/plugins/semantic-ui/components/table.min",
		"se-transition" : "/plugins/semantic-ui/components/transition.min",
		"se-video" : "/plugins/semantic-ui/components/video.min",
		"se-visibility" : "/plugins/semantic-ui/components/visibility.min",
	},
	shim : {
		'lc4e' : [ 'jquery', 'semantic' ],
		'semantic' : [ 'jquery' ],
		"se-accordion" : [ 'jquery' ],
		"se-api" : [ 'jquery' ],
		"se-breadcrumb" : [ 'jquery' ],
		"se-checkbox" : [ 'jquery' ],
		"se-dimmer" : [ 'jquery' ],
		"se-form" : [ 'jquery' ],
		"se-dropdown" : [ 'jquery' ],
		"se-modal" : [ 'jquery' ],
		"se-nag" : [ 'jquery' ],
		"se-popup" : [ 'jquery' ],
		"se-progress" : [ 'jquery' ],
		"se-rating" : [ 'jquery' ],
		"se-search" : [ 'jquery' ],
		"se-shape" : [ 'jquery' ],
		"se-sidebar" : [ 'jquery' ],
		"se-site" : [ 'jquery' ],
		"se-state" : [ 'jquery' ],
		"se-sticky" : [ 'jquery' ],
		"se-tab" : [ 'jquery' ],
		"se-table" : [ 'jquery' ],
		"se-transition" : [ 'jquery' ],
		"se-video" : [ 'jquery' ],
		"se-visibility" : [ 'jquery' ],
	}
});
require([ 'jquery', 'lc4e', 'semantic' ], function($) {
	$(function() {
		var requestAnimationFrame = (function() {
			return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(callback) {
				window.setTimeout(callback, 0);
			};
		})();
		$('#menu .ui.dropdown.item').dropdown({
			action : "nothing",
			transition : "scale",
			on : 'click'
		});

		$('#searchSite').on('focus', function() {
			$(this).addClass('expended');
		}).on('blur', function() {
			$(this).removeClass('expended')
		})

		$('#expendHeader').on('click', function() {
			$('#menu').toggleClass('expended');
		})

		$('#menu .column div:first a').on('click', function() {
			$('#menu .column>.menu').slideToggle();
		});

		$('#config-tool-options .angle.double.left.icon').on('click', function() {
			if ($($('#config-tool-options .ui.animated.selection.list:not(.hidden)').transition('fade left').attr('data-parent')).transition('fade left').attr('id') == 'menu1') {
				$(this).addClass('transition hidden');
			}
		});

		$("#config-tool-options .ui.list .item[data-target^='#']").on('click', function() {
			$(this).parent().transition('fade left');
			$($(this).attr('data-target')).transition('fade left');
			$('#config-tool-options .angle.double.left.icon').removeClass('transition hidden');
		});

		$('#config-tool-cog').on('click', function() {
			$('#config-tool').toggleClass('closed');
		});

		$('html').visibility({
			offset : -1,
			once : false,
			continuous : false,
			onTopPassed : function() {
				function animate() {
					$('#menu').addClass('fixed');
					$('#GTTop').transition('fade');
				}
				requestAnimationFrame(animate);
			},
			onTopPassedReverse : function() {
				function animate() {
					$('#menu.fixed').removeClass('fixed');
					$('#GTTop').transition('fade');
				}
				requestAnimationFrame(animate);
			}
		});

		$('#userItem .ui.image').popup({
			position : 'bottom center',
			transition : "horizontal flip",
			popup : $('#userCardPop'),
			exclusive : false,
			hideOnScroll : false,
			on : 'click',
			closable : true
		});

		$('#config-tool-options .ui.checkbox').checkbox();

		$('#fixFooter').checkbox({
			onChange : function(e) {
				$('#content').toggleClass('footerFixed');
				$('.ui.footer').toggleClass('fixed');
			}
		})

		$('#boxedLayout').checkbox({
			onChange : function(e) {
				$('#articlelist').toggleClass('nobox');
			}
		})

		$('#announce').shape();
		$('#announce').data('interval', setInterval(function() {
			$('#announce').shape('flip down');
		}, 3000));

		$.get('Articles').done(function(data) {
			$('#articlelist>.ui.divided.items').append(data);
			$('#articlelist>.ui.divided.items>.item').Lc4eAnimate({
				animation : 'fadeInUpArt',
				speed : 'fast',
				interval : 100,
				onComplete : function($that) {
					$that.find('.content>.extra>.ui.dropdown.button').dropdown();
					$that.find('.ui.fluid.image img').popup();
				},
			})
		})

		$.get('TopHots').done(function(data) {
			$('#todayHot>.ui.divided.items').append(data);
			$('#todayHot>.ui.divided.items>.item').Lc4eAnimate({
				animation : 'fadeInRightArt',
				speed : 'fast',
				interval : 80,
			})
		})

		$('#GTTop').on('click', function(e) {
			e.preventDefault();
			$('html').animatescroll({
				scrollSpeed : 1000,
				easing : 'easeOutBounce'
			});
		})
	})
});

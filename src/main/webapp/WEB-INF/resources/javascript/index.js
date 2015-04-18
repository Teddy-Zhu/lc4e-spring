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
		'lc4e' : [ 'jquery', 'se-dimmer', 'se-modal','se-progress'],
		'semantic' : [ 'jquery' ],
		"se-accordion" : [ 'jquery' ],
		"se-api" : [ 'jquery' ],
		"se-breadcrumb" : [ 'jquery' ],
		"se-checkbox" : [ 'jquery' ],
		"se-dimmer" : [ 'jquery' ],
		"se-form" :[ 'jquery' ],
		"se-dropdown" : [ 'jquery' ],
		"se-modal" :[ 'jquery' ],
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
		"se-video" :[ 'jquery' ],
		"se-visibility" : [ 'jquery' ],
	}
});
require([ 'jquery', 'lc4e' ,'se-search','se-checkbox','se-shape','se-dropdown','se-visibility','se-transition'], function($) {
	$(function() {
		$('#menu .ui.dropdown.item').dropdown({
			action : "nothing",
			transition : "scale",
			duration : '800',
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

		/*
		 * $('#menu').visibility({ onUpdate : function(data) { if (data.width <
		 * 600) { $('#menu').addClass('fluid vertical'); } else {
		 * $('#menu.fluid.vertical').removeClass('fluid
		 * vertical').find('.column>.menu').show(); } } });
		 */
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
			onTopPassed : function() {
				$('#menu').addClass('fixed').find('.column').addClass('reduce');
			},
			onTopPassedReverse : function() {
				$('#menu.fixed').removeClass('fixed').find('.column').removeClass('reduce');
			}
		});

		$('#config-tool-options .ui.checkbox').checkbox();

		$('#fixFooter').checkbox({
			onChange : function(e) {
				$('.ui.footer').toggleClass('fixed');
			}
		})

		$('#announce').shape();
		$('#announce').data('interval', setInterval(function() {
			$('#announce').shape('flip down');
		}, 3000));

	})
});

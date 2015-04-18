$(function() {
	$("#testModal").on("click", function() {
		$.Lc4eModal({
			type : "standard",
			OtherButtons : [ "Test Button" ],
			OtherButtonsClick : [ function() {
				alert('asd');
			} ],
			OtherButtonsClass : [ "close" ]
		}).popup({
			title : 'The modal',
			content : 'the is the standard modal',
			position : 'bottom center'
		}).popup('show');
	});
	$("#testProgress").on("click", function() {
		$.Lc4eProgress().popup({
			title : 'The progress',
			content : 'the is the top progress',
			position : 'bottom center'
		}).popup('show').next().find('.loading.icon').popup({
			title : 'The loading',
			content : 'the is the top progress\'s loading',
			position : 'left center'
		}).popup('show');
	})
	$('#testLoader').on("click", function() {
		$('#idcard').Lc4eDimmer('toggle');
	})

	$("#testAttachedProgress").on("click", function() {
		$('#idcard').Lc4eProgress({
			type : "attached"
		}).popup({
			title : 'The progress',
			content : 'the is the attached progress',
			position : 'bottom center'
		}).popup('show');
	})
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

/*	$('#menu').visibility({
		onUpdate : function(data) {
			if (data.width < 600) {
				$('#menu').addClass('fluid vertical');
			} else {
				$('#menu.fluid.vertical').removeClass('fluid vertical').find('.column>.menu').show();
			}
		}
	});
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
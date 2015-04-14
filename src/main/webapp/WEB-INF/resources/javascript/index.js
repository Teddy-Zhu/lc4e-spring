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
		transition : "horizontal flip",
		on : 'hover'
	});

	$('#searchSite').on('focus', function() {
		$(this).animate({
			width : '+=100px',
		});
	}).on('blur', function() {
		$(this).animate({
			width : '-=100px',
		});
	})
	
	$('#menu').visibility({
		onUpdate : function(data) {
			if (data.width < 600) {
				$('#menu').addClass('fluid vertical');
			} else {
				$('#menu').removeClass('fluid vertical');
			}
		}
	});
	
	$('body').visibility({
		offset : -1,
		once : false,
		continuous : false,
		onTopPassed : function() {
			$('#menu').addClass('light fixed').find('.column').animate({
				'padding-top' : '-=0.5rem',
				'padding-bottom' : '-=0.5rem',
			}, 200);
		},
		onTopPassedReverse : function() {
			$('#menu').removeClass('light fixed').find('.column').animate({
				'padding-top' : '+=0.5rem',
				'padding-bottom' : '+=0.5rem',
			}, 300);
		}
	});

})
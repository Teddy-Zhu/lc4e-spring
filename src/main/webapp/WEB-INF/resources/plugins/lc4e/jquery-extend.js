/*!
 * Lc4e Javascript Library
 * author zhuxi - v1.0.0 (2015-04-09T11:23:51+0800)
 * http://www.lc4e.com/ | Released under MIT license
 * 
 * Include jquery (http://jquery.com/) semantic-ui (http://semantic-ui.com/)
 */

(function($) {
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
			precision : 1,
			indicating : true,
			total : false,
			value : false,
			autoUpdateSpeed : 300,
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

		this.each(function() {
			var options, $that = $(this), html = "", template = "", progressId = "", $progressBar;
			switch (option) {
			case "setPercent": {
				$progressBar = $that;
				$that.progress({
					percent : data
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

				options.onSuccess = function() {
					options.onComplete();
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
			$operate = $progressBar;
			return false;
		});
		return $operate;
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
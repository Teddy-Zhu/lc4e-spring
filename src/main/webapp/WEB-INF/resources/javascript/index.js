require.config({
    paths: {
        "jquery": "/plugins/jquery/2.1.3/jquery.min",
        "semantic": "http://semantic-ui.com/dist/semantic.min",
        "lc4e": "/js/lc4e/jquery-extend",
        "se-accordion": "/plugins/semantic-ui/2.0/components/accordion.min",
        "se-api": "/plugins/semantic-ui/2.0/components/api.min",
        "se-breadcrumb": "/plugins/semantic-ui/2.0/components/breadcrumb.min",
        "se-checkbox": "/plugins/semantic-ui/2.0/components/checkbox.min",
        "se-dimmer": "/plugins/semantic-ui/2.0/components/dimmer.min",
        "se-form": "/plugins/semantic-ui/2.0/components/form.min",
        "se-dropdown": "/plugins/semantic-ui/2.0/components/dropdown.min",
        "se-modal": "/plugins/semantic-ui/2.0/components/modal.min",
        "se-nag": "/plugins/semantic-ui/2.0/components/nag.min",
        "se-popup": "/plugins/semantic-ui/2.0/components/popup.min",
        "se-progress": "/plugins/semantic-ui/2.0/components/progress.min",
        "se-rating": "/plugins/semantic-ui/2.0/components/rating.min",
        "se-search": "/plugins/semantic-ui/2.0/components/search.min",
        "se-shape": "/plugins/semantic-ui/2.0/components/shape.min",
        "se-sidebar": "/plugins/semantic-ui/2.0/components/sidebar.min",
        "se-site": "/plugins/semantic-ui/2.0/components/site.min",
        "se-state": "/plugins/semantic-ui/2.0/components/state.min",
        "se-sticky": "/plugins/semantic-ui/2.0/components/sticky.min",
        "se-tab": "/plugins/semantic-ui/2.0/components/tab.min",
        "se-table": "/plugins/semantic-ui/2.0/components/table.min",
        "se-transition": "/plugins/semantic-ui/2.0/components/transition.min",
        "se-video": "/plugins/semantic-ui/2.0/components/video.min",
        "se-visibility": "/plugins/semantic-ui/2.0/components/visibility.min",
    },
    shim: {
        'lc4e': ['jquery', 'semantic'],
        'semantic': ['jquery'],
        "se-accordion": ['jquery'],
        "se-api": ['jquery'],
        "se-breadcrumb": ['jquery'],
        "se-checkbox": ['jquery'],
        "se-dimmer": ['jquery'],
        "se-form": ['jquery'],
        "se-dropdown": ['jquery'],
        "se-modal": ['jquery'],
        "se-nag": ['jquery'],
        "se-popup": ['jquery'],
        "se-progress": ['jquery'],
        "se-rating": ['jquery'],
        "se-search": ['jquery'],
        "se-shape": ['jquery'],
        "se-sidebar": ['jquery'],
        "se-site": ['jquery'],
        "se-state": ['jquery'],
        "se-sticky": ['jquery'],
        "se-tab": ['jquery'],
        "se-table": ['jquery'],
        "se-transition": ['jquery'],
        "se-video": ['jquery'],
        "se-visibility": ['jquery'],
    }
});
require(['jquery', 'lc4e', 'semantic'], function ($) {
    $(function () {
        var getArticles;
        $('#menu .ui.dropdown.item').dropdown({
            action: "nothing",
            transition: "scale",
            on: 'click'
        });
        $('#searchSite').on('focus', function () {
            $(this).addClass('expended');
        }).on('blur', function () {
            $(this).removeClass('expended')
        });

        $('#expendHeader').on('click', function () {
            $('#menu').toggleClass('expended');
        });

        $('#menu .column div:first a').on('click', function () {
            $('#menu>.column>.allmenus').transition({
                animation: "swing down",
                onComplete: function () {
                    $('#menu>.column>.allmenus').toggleClass('menuhidden').removeClass("transition visible hidden").attr('style', '');
                }
            });
        });

        $('#config-tool-options .angle.double.left.icon').on('click', function () {
            if ($($('#config-tool-options .ui.animated.selection.list:not(.hidden)').transition('fade left').attr('data-parent')).transition('fade left').attr('id') == 'menu1') {
                $(this).addClass('transition hidden');
            }
        });

        $("#config-tool-options .ui.list .item[data-target^='#']").on('click', function () {
            $(this).parent().transition('fade left');
            $($(this).attr('data-target')).transition('fade left');
            $('#config-tool-options .angle.double.left.icon').removeClass('transition hidden');
        });

        $('#config-tool-cog').on('click', function () {
            $('#config-tool').toggleClass('closed');
        });

        $('html').visibility({
            offset: -1,
            once: false,
            continuous: false,
            onTopPassed: function () {
                $.requestAnimationFrame(function () {
                    $('#menu').addClass('fixed');
                    $('#GTTop').transition('fade');
                });
            },
            onTopPassedReverse: function () {
                $.requestAnimationFrame(function () {
                    $('#menu').removeClass('fixed');
                    $('#GTTop').transition('fade');
                });
            }
        });

        $('#userItem .ui.image').popup({
            position: 'bottom center',
            transition: "horizontal flip",
            popup: $('#userCardPop'),
            exclusive: false,
            hideOnScroll: false,
            on: 'click',
            closable: true
        });

        $('#config-tool-options .ui.checkbox').checkbox();

        $('#fixFooter').checkbox({
            onChange: function (e) {
                $('#content').toggleClass('footerFixed');
                $('.ui.footer').toggleClass('fixed');
            }
        });

        $('#boxedLayout').checkbox({
            onChange: function (e) {
                $('#articlelist').toggleClass('nobox');
            }
        });

        $('#announce').shape();
        $('#announce').data('interval', setInterval(function () {
            $('#announce').shape('flip down');
        }, 3000));


        var page = parseInt($("#articlelist").attr("page"));
        $.Lc4eAjax({
            url: "/Articles",
            cjson: false,
            type: "get",
            dataType: "html"
        }).done(function (data) {
            $('#articlelist>.ui.divided.items').append(data);
            $('#articlelist>.ui.divided.items>.item').
                transition({
                    animation: 'fade up',
                    duration: 500,
                    interval: 100,
                    onComplete: function () {
                        $('#articlelist>.ui.divided.items>.item').find('.ui.fluid.image img').popup();
                        $("#articlelist").attr("page", page);
                    }
                })
        })

        $.get('/TopHots').done(function (data) {
            $('#todayHot>.ui.divided.items,#yesterdayHot>.ui.divided.items').empty().append(data);
            $('#todayHot>.ui.divided.items>.item,#yesterdayHot>.ui.divided.items>.item').
                transition({
                    animation: 'fade right',
                    duration: 300,
                    interval: 80,
                })
        });

        $('#GTTop').on('click', function (e) {
            e.preventDefault();
            $('html').animatescroll({
                scrollSpeed: 1000,
                easing: 'easeOutBounce'
            });
        });

        $('#prePage,#nextPage').on('click', function () {
            var page = parseInt($("#articlelist").attr("page")) + 1;
            $.Lc4eAjax({
                url: "/Articles",
                cjson: false,
                type: "get",
                dataType: "html"
            }).done(function (data) {
                $('#articlelist>.ui.divided.items').empty().append(data);
                $('#articlelist>.ui.divided.items>.item').
                    transition({
                        animation: 'fade up',
                        duration: 500,
                        interval: 100,
                        onComplete: function () {
                            $('#articlelist>.ui.divided.items>.item').find('.ui.fluid.image img').popup();
                            $("#articlelist").attr("page", page);
                        }
                    })
            })
        })
    })
});

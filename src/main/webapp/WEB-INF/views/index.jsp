<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head><c:import url="/WEB-INF/views/common/title.jsp"></c:import>
    <link rel="stylesheet" type="text/css" class="ui" href="/css/view/index.css">
</head>
<body>
<!-- Header Menu Start -->
<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
<!-- Heard Menu End -->
<!-- Content Start -->
<div id="content">
    <div id="mainContent" class="ui page grid centered">
        <div id="leftContent" class="ten wide column">
            <div id="announcement" class="ui segment">
                <div class="item">
                    <div class="ui white label">
                        <i class="announcement icon"></i>

                        <div class="hidden-mb">AnnounceMent</div>
                    </div>
                    <div id="announce" class="ui text shape">
                        <div class="sides">
                            <div class="active ui header side">Did you know? This side starts visible.</div>
                            <div class="ui header side">Help, its another side!</div>
                            <div class="ui header side">This is the last side</div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="articlelist" class="ui stacked raised segment" page="${page}">
                <div class="ui divided items"></div>
            </div>
            <div id="articlebottons" class="ui raised clearing segment">
                <div id="prePage" class="ui left floated labeled icon button">
                    <i class="angle double left icon"></i>
                    <spring:message code="index.preBtn"/>
                </div>
                <div id="nextPage" class="ui right floated right labeled icon button">
                    <i class="angle double right icon"></i>
                    <spring:message code="index.nextBtn"/>
                </div>

            </div>

        </div>
        <div id="rightContent" class="four wide column">
            <div id="todayHot" class="ui raised  segment">
                <h4 class="ui horizontal header divider">
                    <i class="bar chart icon"></i> Today HotSpot
                </h4>

                <div class="ui divided items"></div>
            </div>
            <div id="yesterdayHot" class="ui raised  segment">
                <h4 class="ui horizontal header divider">
                    <i class="bar chart icon"></i> Yesterday HotSpot
                </h4>

                <div class="ui divided items"></div>
            </div>
            <div class="ui vertical rectangle test ad" data-text="Advertisement"></div>
        </div>
    </div>
</div>
<!-- Content  End -->
<!-- Right Buttons Start -->
<a id="GTTop" href="#" tabindex="1"><i class="circular large arrow up icon"></i></a>
<!-- Right Buttons End -->
<!-- Slider Bar Start -->
<div id="config-tool" class="closed" style="display: none;">
    <a id="config-tool-cog"> <i class="large setting loading icon"></i>
    </a>

    <div id="config-tool-options" class="ui segment">
        <h4 class="ui header">
            Area List<i class="linked angle double left icon transition hidden"></i>
        </h4>

        <div id="menu1" class="ui animated selection list">
            <div class="item" data-target="#menu2">
                Fixed Header<i class="angle double right icon"></i>
            </div>
            <div class="item">
                <div class="ui slider checkbox">
                    <input type="checkbox" name="public"> <label>Fixed Left Menu</label>
                </div>
            </div>
            <div class="item">
                <div id="fixFooter" class="ui toggle checkbox">
                    <input type="checkbox" name="public"> <label>Fixed Footer</label>
                </div>
            </div>
            <div class="item">
                <div id="boxedLayout" class="ui checkbox">
                    <input type="checkbox" name="public"> <label>Boxed Layout</label>
                </div>
            </div>
            <div class="item">
                <div class="ui toggle red checkbox">
                    <input type="checkbox" name="public"> <label>Right-to-Left</label>
                </div>
            </div>
        </div>
        <div id="menu2" data-parent="#menu1" class="ui animated selection list transition hidden">
            <div class="item">second select</div>
            <div class="item">second select</div>
            <div class="item" data-target="#menu3">
                third select <i class="angle double right icon"></i>
            </div>
        </div>
        <div id="menu3" data-parent="#menu2" class="ui animated selection list transition hidden">
            <div class="item">third select11</div>
        </div>
        <h4 class="ui header">Skin Color</h4>

        <div class="ui list">
            <a class="ui red label"></a> <a class="ui teal label"></a> <a class="ui blue label"></a> <a
                class="ui purple label"></a> <a class="ui green label"></a> <a class="ui red label"></a>
        </div>
    </div>
</div>
<!-- Slider Bar End -->
<c:import url="common/footer.jsp"></c:import>

<script src="/plugins/require.js/2.1.17/require.min.js" data-main="/js/index"></script>

</body>
</html>
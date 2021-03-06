<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div id="menuheader"></div>
<div id="menu" class="ui secondary menu">
    <div class="column">
        <div class="hidden-pc">
            <a class="item linked"> <i class="content icon"></i> Menus
            </a>
        </div>
        <div class="allmenus">
            <div class="left menu">
                <img class="logo ui image item hidden-mb" src="/images/logo.png">
                <c:import url="/WEB-INF/views/common/menudropdown.jsp"></c:import>
            </div>
            <div class="right menu">
                <div class="item">
                    <div class="ui icon input">
                        <input id="searchSite" type="text" placeholder="Search..."> <i class="search link icon"></i>
                    </div>
                </div>
                <shiro:user>
                    <div id="userItem" class="item">

                        <img class="ui headered linked image" src="/images/wireframe/image.png"></img>
                        <div class="ui tiny top right attached label">22</div>


                        <div id="userCardPop" class="ui flowing popup">
                            <div id="userCard" class="ui card">
                                <div class="content">
                                    <div class="centered aligned header"><shiro:principal/></div>
                                    <div class="ui clearing divider"></div>
                                    <div class="description">
                                        <div class="ui divided items">
                                            <div class="item">
                                                <i class="comments outline icon"></i> Comments <a
                                                    class="ui right floated label"> 11 </a>
                                            </div>
                                            <div class="item">
                                                <i class="diamond icon"></i> Diamonds <a class="ui right floated label">
                                                111 </a>
                                            </div>
                                            <div class="item">
                                                <i class="mail outline icon"></i> Messages <a
                                                    class="ui right floated label">
                                                2111 </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="extra content">
								<span class="left floated"> <i class="users icon"></i> Follows <a
                                        class="ui transparent circular label"> 10 </a>
								</span> <span class="right floated"> <i class="star icon"></i> Favorites <a
                                        class="ui transparent circular label"> 5 </a>
								</span>
                                </div>
                                <div class="ui two  bottom attached buttons">
                                    <div class="ui primary button">
                                        <i class="setting icon"></i> Settings
                                    </div>
                                    <div class="ui button">
                                        <i class="sign out icon"></i>
                                        Sign Out
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </shiro:user>
                <shiro:guest>
                    <div class="ui item animated fade button">
                        <div class="visible content">Sign Up</div>
                        <div class="hidden content">
                            <i class="add user icon"></i>
                        </div>
                    </div>
                    <div class="ui item animated button">
                        <div class="visible content">Sign In</div>
                        <div class="hidden content">
                            <i class="user icon"></i>
                        </div>
                    </div>
                </shiro:guest>

                <div id="expendHeader" class="ui item hidden-mb">
                    <div class="ui linked label">
                        <i class="maximize icon"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
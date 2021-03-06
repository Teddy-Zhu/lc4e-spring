<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:forEach items="${lists}" var="art">
    <div class="item" style="display: none;">
        <div class="ui fluid tiny image hidden-mb">
            <div class="ui teal ribbon label">${art.user}</div>
            <img src="${art.imageUrl}" data-title="${art.popUp.title}" data-content="${art.popUp.content}">
        </div>
        <div class="content">
            <a class="header"><i class="red protect icon"></i>${art.articleTitle}</a>

            <div class="extra">
                <a class="ui blue label hidden-pc">${art.user}</a> <a class="ui label">${art.category}</a>

                <div class="ui transparent label">
                    <i class="calendar icon"></i>${art.publishTime}
                </div>
                <div class="ui transparent label">
                    <i class="comments outline icon"></i>${art.comments}
                </div>

                <div class="ui transparent label">
                    <i class="comment icon"></i>Last Reply
                </div>
                <a class="ui label">${art.lastCommentUser}</a>
            </div>
        </div>
    </div>
</c:forEach>
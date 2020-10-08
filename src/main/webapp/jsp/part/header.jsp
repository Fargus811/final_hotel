<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/header.css" type="text/css">
<div class='header' style="z-index:2">
    <div><a class='site-name' href="">Hotel</a></div>
    <div class='button'href="catalog">Catalog</div>
    <a class='button' href="partnership">Partnership</a>
    <div class="listener" hidden></div>
    <!--
    <div class="user-block authorized">
        <div class="username">{{user.displayname}}</div>
        <img src="assets/provile.png" alt="Profile-Img" style="float: right;width: 60px; margin-top: 5px;">
        <div class='userbox'>
            <div *ngIf="currentPath=='/personal/profile'; else homeInactive" class='button active'>Profile</div>
            <ng-template #homeInactive><a class='button' routerLink="personal/profile">Profile</a></ng-template>
            <div *ngIf="currentPath=='/personal/mysubscriptions'; else catalogInactive" class='button active'>My subscriptions
            </div>
            <ng-template #catalogInactive><a class='button' routerLink="personal/mysubscriptions">My subscriptions</a>
            </ng-template>
            <div *ngIf="currentPath=='/personal/mywallet'; else walletInactive" class='button active'>My wallet</div>
            <ng-template #walletInactive><a class='button' routerLink="personal/mywallet">My wallet</a></ng-template>
            <a class="button" routerLink="">LogOut</a>
        </div>
    </div>
    -->
        <div class="user-block unauthorized">
            <a class='button auth primary' href="${pageContext.request.contextPath}/jsp/registration.jsp">SIGN UP</a>
            <a class='button auth secondary' href="${pageContext.request.contextPath}/jsp/login.jsp">SIGN IN</a>
        </div>
</div>
<div class="header-correction"></div>

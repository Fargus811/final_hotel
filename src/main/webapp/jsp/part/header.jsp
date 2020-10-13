<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,600,900&amp;lang=en"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/header.css" type="text/css">
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
    <div class='header' style="z-index:2">
        <div><a class='site-name' href=""><fmt:message key="text.hotel"/></a></div>
        <div class='button' href="catalog"><fmt:message key="text.catalog"/></div>
        <a class='button' href="partnership"><fmt:message key="text.partnership"/></a>
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
            <a class='button auth primary' href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message
                    key="text.registration.title"/></a>
            <a class='button auth secondary' href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message
                    key="text.logIn.title"/></a>
        </div>
    </div>
    <div class="header-correction"></div>
</fmt:bundle>

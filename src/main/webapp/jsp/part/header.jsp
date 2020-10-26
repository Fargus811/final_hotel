<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/header.css" type="text/css">
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
    <div class='header' style="z-index:2">
        <div><a class='site-name' href="${pageContext.request.contextPath}/index.jsp"><fmt:message
                key="text.hotel"/></a></div>
        <div class='button' href="catalog"><fmt:message key="text.catalog"/></div>
        <a class='button' href="partnership"><fmt:message key="text.partnership"/></a>
        <c:choose>
            <c:when test="${empty sessionScope.sessionUser}">
                <div class="user-block unauthorized">
                    <a class='button auth primary'
                       href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message
                            key="text.registration.title"/></a>
                    <a class='button auth secondary'
                       href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message
                            key="text.logIn.title"/></a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="user-block authorized">
                    <div class="username">${sessionUser.firstName}</div>
                    <img src="/resources/images/profile.jpg" alt="Profile-Img"
                         style="float: right;width: 60px; margin-top: 5px;">
                    <div class='userbox'>
                        <div class='button'><a style="color: white"
                                               href="${pageContext.servletContext.contextPath}/controller?command=show_my_profile">Profile</a>
                        </div>
                        <div class='button'><a style="color: white"
                                               href="${pageContext.servletContext.contextPath}/controller?command=logout">LogOut</a>
                        </div>
                        <div class='button'><a style="color: white"
                                               href="${pageContext.servletContext.contextPath}/controller?command=show_user_bookings">My
                            bookings</a></div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="header-correction"></div>
</fmt:bundle>

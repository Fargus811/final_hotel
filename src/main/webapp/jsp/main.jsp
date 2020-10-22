<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
    <title>Grand Hotel</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<jsp:include page="/jsp/part/header.jsp"/>
<c:forEach var="elem" items="${rooms}">
    <div class="room-item" style="float: left; margin-top: 50px">
        <img class="room-photo" src="${elem.photoPath}" alt="Room Image" width="210" height="120">
        <div class="room-name">${elem.name}</div>
        <div class="roomGrade">${elem.roomGrade}</div>
        <div class="room-desc">${elem.description}</div>
        <div class="room-item-bottom">
            <div class="room-cost">${elem.cost}💵</div>
            <div class="room-beds">🛏 ${elem.numberOfBeds}</div>
            <div class="room-max-persons">👥 ${elem.maxPersons}</div>
            <div class="room-advantages">
                <c:if test="${elem.hasWifi}">
                    <div class="room-advantage">Wi-Fi</div>
                </c:if>
                <c:if test="${elem.hasTV}">
                    <div class="room-advantage">📺</div>
                </c:if>
                <c:if test="${elem.hasBathroom}">
                    <div class="room-advantage">🛁</div>
                </c:if>
            </div>
        </div>
    </div>
</c:forEach>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</fmt:bundle>
</html>

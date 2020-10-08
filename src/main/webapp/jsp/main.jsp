<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,600,900&amp;lang=en"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/part/header.jsp"/>
<c:forEach var="elem" items="${rooms}">
<div class="room-item" style="float: left">
    <img class="room-photo" src="${elem.photoPath}" alt="Room Image" width="210" height="120">
    <div class="room-name">${elem.name}</div>
    <div class="grade">${elem.grade}</div>
    <div class="room-desc">${elem.description}</div>
    <div class="room-item-bottom">
        <div class="room-cost">${elem.cost}💵</div>
        <div class="room-beds">🛏 ${elem.numberOfBeds}</div>
        <div class="room-max-persons">👥 ${elem.maxPersons}</div>
        <div class="room-advantage">Wi-Fi ${elem.hasWifi}</div>
        <div class="room-advantage">📺  ${elem.hasTV}</div>
        <div class="room-advantage">🛁  ${elem.hasBathroom}</div>
    </div>
</div>
</c:forEach>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>

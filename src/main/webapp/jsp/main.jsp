<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
    <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,600,900&amp;lang=en" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/part/header.jsp" />
<h1>Main</h1>
<div class="room-item">
    <img class="room-photo" src="" alt="Photo" width="210" height="120">
    <div class="room-name">Люкс</div>
    <div class="grade">Элитный</div>
    <div class="room-desc">Сосать/мыть посуду</div>
    <div class="room-item-bottom">
        <div class="room-cost">$1999.99</div>
        <div class="room-beds">🛏 4</div>
        <div class="room-max-persons">👥 6</div>
        <div class="room-advantage">Wi-Fi ✅</div>
        <div class="room-advantage">📺 ✅</div>
        <div class="room-advantage">🛁 ✅</div>
    </div>
</div>
<td><c:out value="${ line }" /></td>
<c:forEach var="room" items="${rooms}">
    <div>{$room.}</div>
</c:forEach>
<jsp:include page="/jsp/part/footer.jsp" />
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
    <title>Grand Hotel</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<jsp:include page="/jsp/part/header.jsp"/>
<div class="fade-block left"></div>
<div class="fade-block right"></div>
<div class="scroll">
    <div class="room-container">
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
                    <ctg:onlyForAdmin>
                        <div class="modal-footer">
                            <form action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" value="show_room_to_update_info"/>
                                <input type="hidden" name="roomId" value="${elem.id}"/>
                                <button type="submit" class="btn btn-outline-info">✏️</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" value="delete_room"/>
                                <input type="hidden" name="roomId" value="${elem.id}"/>
                                <button type="submit" class="btn btn-outline-danger">🗑</button>
                            </form>
                        </div>
                    </ctg:onlyForAdmin>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="row justify-content-center">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:if test="${hasPrev}">
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="show_all_rooms"/>
                    <input type="hidden" name="numberOfPage" value="${numberOfPage-1}"/>
                    <li class="page-item">
                        <button type="submit" class="page-link">Previous</button>
                    </li>
                </form>
            </c:if>
            <c:if test="${hasNext}">
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="show_all_rooms"/>
                    <input type="hidden" name="numberOfPage" value="${numberOfPage+1}"/>
                    <li class="page-item">
                        <button type="submit" class="page-link">Next</button>
                    </li>
                </form>
            </c:if>
        </ul>
    </nav>
</div>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</fmt:bundle>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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
<fmt:bundle basename="text">
    <fmt:setLocale value="${locale}"/>
    <jsp:include page="/jsp/part/header.jsp"/>
    <div class="d-flex justify-content-center">
        <div class="container" style="text-align: center; margin: 20px">
            <label> ✅ Заселение ${booking.startDate} ➖ ❌ Выселение ${booking.endDate}</label>
            <label>Итоговая сумма бронирования составляет: ${cost}💵</label>
            <c:if test="${sessionUser.role == 'ADMIN'}">
                <c:if test="${booking.bookingStatus.ordinal() == 0}">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="add_room_to_booking">
                        <input type="hidden" name="cost" value="${cost}">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <div class="form-group" style="margin-top: 20px">
                            <div class="row">
                                <button type="submit" class="button auth secondary" style="background-color: blue;
                    top: 40px;right: 250px;">
                                    <fmt:message key="text.bookings.addRoom"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </c:if>
                <c:if test="${booking.bookingStatus.ordinal() < 2}">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change_booking_status">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <input type="hidden" name="bookingStatusId" value="2">
                        <div class="form-group" style="margin-top: 20px">
                            <div class="row">
                                <button type="submit" class="btn btn-outline-warning"
                                        style="margin-bottom: 10px;margin-left: 340px;">
                                    <fmt:message key="text.admin.booking.cancel"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </c:if>
            <c:if test="${sessionUser.role == 'USER'}">
                <c:if test="${requestScope.booking.bookingStatus.ordinal() < 2}">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="pay_for_booking">
                        <input type="hidden" name="cost" value="${cost}">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <div class="form-group">
                            <div class="row">
                                <button type="submit" class="btn btn-outline-success" style="margin-left: 340px">Pay
                                </button>
                            </div>
                        </div>
                    </form>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="delete_room_from_booking">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <div class="form-group">
                            <div class="row">
                                <button type="submit" class="btn btn-outline-warning" style="margin-left: 340px;">
                                    <fmt:message key="text.bookings.cancel.room"/>
                                </button>
                            </div>
                        </div>
                    </form>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change_booking_status">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <input type="hidden" name="bookingStatusId" value="2">
                        <div class="form-group">
                            <div class="row">
                                <button type="submit" class="btn btn-outline-warning"
                                        style="margin-bottom: 10px;margin-left: 340px;">
                                    <fmt:message key="text.admin.booking.cancel"/>
                                </button>
                            </div>
                            <div class="row" style="margin-left: 120px;margin-top: 20px;">
                                <label><fmt:message key="text.bookings.call"/> +795882745654</label>
                            </div>
                        </div>
                    </form>
                </c:if>
            </c:if>
        </div>
        <div class="room-container">
            <div class="room-item" style="margin-top: 50px">
                <img class="room-photo" src="${room.photoPath}" alt="Room Image" width="210" height="120">
                <div class="room-name">${room.name}</div>
                <div class="roomGrade">${room.roomGrade}</div>
                <div class="room-desc">${room.description}</div>
                <div class="room-item-bottom">
                    <div class="room-cost">${room.cost}💵</div>
                    <div class="room-beds">🛏 ${room.numberOfBeds}</div>
                    <div class="room-max-persons">👥 ${room.maxPersons}</div>
                    <div class="room-advantages">
                        <c:if test="${room.hasWifi}">
                            <div class="room-advantage">Wi-Fi</div>
                        </c:if>
                        <c:if test="${room.hasTV}">
                            <div class="room-advantage">📺</div>
                        </c:if>
                        <c:if test="${room.hasBathroom}">
                            <div class="room-advantage">🛁</div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/jsp/part/footer.jsp"/>
</fmt:bundle>
</body>
</html>

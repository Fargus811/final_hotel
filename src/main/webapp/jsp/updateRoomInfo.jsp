<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/makeBooking.css" type="text/css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">

<head>
    <title>Create Room</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:bundle basename="text">
</head>
<body>
<jsp:include page="/jsp/part/header.jsp"/>
<div class="justify-content-center">
    <div class="container" style="margin: 50px 20px 20px 40px">
        <c:if test="${not empty sessionScope.imageStatus}">
            <div class="alert alert-success" role="alert">
                Success
            </div>
        </c:if>
        <c:if test="${empty sessionScope.imageStatus}">
            <form action="${pageContext.request.contextPath}/upload_image_controller/" enctype="multipart/form-data" method="post">
                <div class="form-group" style="margin-top: 50px">
                    <div class="col">
                        <div class="row">
                            <label><fmt:message key="text.create.roomImage"/></label>
                        </div>
                        <div class="row">
                            <input type="file" name="file" id="file"/>
                        </div>
                        <div class="row">
                            <input type="hidden" name="image_type" value="exterior_small">
                            <input type="hidden" name="update" value="1">
                            <input type="hidden" name="roomId" value="${room.id}"/>
                        </div>
                        <div class="row">
                            <button  type="submit" class="button auth secondary" style="background-color: blue;background-color: blue;margin-right: 1011px">
                                <fmt:message key="text.createRoom.download"/></button>
                        </div>
                    </div>

                </div>
            </form>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="update_room_info"/>
            <input type="hidden" name="roomId" value="${room.id}"/>
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="inputRoomName"><fmt:message key="text.create.name"/></label>
                    <input pattern="^[А-Яа-яЁё\s]+$" class="form-control" name="roomName" id="inputRoomName"
                           title="<fmt:message key="text.create.roomDesc.title"/>"
                           placeholder="<fmt:message key="text.create.name"/>" value="${room.name}" maxlength="20"
                           required>
                </div>
                <div class="form-group col-md-6">
                    <label for="inputCost"><fmt:message key="text.create.cost"/></label>
                    <input pattern="^(149|1[5-9][0-9]|[2-9][0-9]{2}|[1-8][0-9]{3}|9[0-8][0-9]{2}|99[0-8][0-9]|999[0-9]|1[0-9]{4}|2000[01])$"
                           title="<fmt:message key="text.create.costInfo"/>" class="form-control" value="${room.cost}"
                           name="roomCost" id="inputCost"
                           placeholder="<fmt:message key="text.create.cost"/>"
                           min="149" max="20001" required>
                </div>
            </div>
            <div class="form-check form-check-inline" style="margin-bottom: 20px">
                <c:choose>
                    <c:when test="${room.hasWifi}">
                        <input class="form-check-input" name="hasWifi" type="checkbox"
                               id="inlineCheckbox1"
                               value="${room.hasWifi}" checked>
                    </c:when>
                    <c:otherwise>
                        <input class="form-check-input" name="hasWifi" type="checkbox"
                               id="inlineCheckbox1"
                               value="${room.hasWifi}">
                    </c:otherwise>
                </c:choose>
                <label class="form-check-label" for="inlineCheckbox1"
                       style="margin-right: 20px">Wifi</label>
                <c:choose>
                    <c:when test="${room.hasTV}">
                        <input class="form-check-input" name="hasTV" type="checkbox"
                               id="inlineCheckbox2"
                               value="${room.hasTV}" checked>
                    </c:when>
                    <c:otherwise>
                        <input class="form-check-input" name="hasTV" type="checkbox"
                               id="inlineCheckbox2"
                               value="${room.hasTV}">
                    </c:otherwise>
                </c:choose>
                <label class="form-check-label" for="inlineCheckbox2"
                       style="margin-right: 20px">TV</label>
                <c:choose>
                <c:when test="${room.hasBathroom}">
                <input class="form-check-input" name="hasBathroom" type="checkbox"
                       id="inlineCheckbox3"
                       value="${room.hasBathroom}" checked>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" name="hasBathroom" type="checkbox"
                           id="inlineCheckbox3"
                           value="${room.hasBathroom}">
                </c:otherwise>
                </c:choose>
                <label class="form-check-label" for="inlineCheckbox3"
                       style="margin-right: 20px"><fmt:message key="text.create.bathroom"/></label>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <div class="form-group">
                        <span class="form-label"><fmt:message key="text.create.roomGrade"/></span>
                        <select class="form-control" name="gradeId">
                            <option value="${room.roomGrade.ordinal()}" selected>${room.roomGrade.name()}</option>
                            <option value="0"><fmt:message key="text.create.roomGrade.economy"/></option>
                            <option value="1"><fmt:message key="text.create.roomGrade.standard"/></option>
                            <option value="2"><fmt:message key="text.create.roomGrade.suite"/></option>
                            <option value="3"><fmt:message key="text.create.roomGrade.premium"/></option>
                            <option value="4"><fmt:message key="text.create.roomGrade.premier"/></option>
                        </select>
                        <span class="select-arrow"></span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <span class="form-label"><fmt:message key="text.create.rooms"/></span>
                        <select class="form-control" name="numberOfRooms">
                            <option value="${room.numberOfRooms}" selected>${room.numberOfRooms}</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </select>
                        <span class="select-arrow"></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span class="form-label"><fmt:message key="text.create.beds"/></span>
                        <select class="form-control" name="numberOfBeds">
                            <option value="${room.numberOfBeds}" selected>${room.numberOfBeds}</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </select>
                        <span class="select-arrow"></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span class="form-label"><fmt:message key="text.create.persons"/></span>
                        <select class="form-control" name="maxPersons">
                            <option value="${room.maxPersons}" selected>${room.maxPersons}</option>
                            <option value="2">2</option>
                            <option value="4">4</option>
                            <option value="6">6</option>
                            <option value="8">8</option>
                        </select>
                        <span class="select-arrow"></span>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="inputRoomDescription">Room description</label>
                    <input pattern="^[А-Яа-яЁё\s]+$" class="form-control" name="roomDescription"
                           id="inputRoomDescription" maxlength="50" value="${room.description}"
                           title="<fmt:message key="text.create.roomDesc.title"/>">
                </div>
            </div>
            <c:if test="${not empty sessionScope.imageStatus}">
                <div class="form-btn">
                    <button type="submit" class="button auth secondary" style="background-color: blue; margin-bottom: 20px; background-color: blue;right: 940px;"><fmt:message key="text.admin.update"/></button>
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert" style="margin: 20px">
                    <fmt:message key="text.profileSettings.invalidData"/>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
<script src="${pageContext.servletContext.contextPath}/resources/js/protect_f5.js"></script>
</body>
</fmt:bundle>
</html>

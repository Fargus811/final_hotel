<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/makeBooking.css" type="text/css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
<head>
<meta charset="utf-8">
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
    <title>Create Booking</title>
    </head>
    <body>
    <jsp:include page="/jsp/part/header.jsp"/>
    <div id="booking" class="section">
        <div class="section-center">
            <div class="container">
                <div class="row">
                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="create_booking"/>
                        <div class="col-md-7 col-md-push-5">
                            <div class="booking-cta">
                                <h1><fmt:message key="text.create.h1"/></h1>
                                <p><fmt:message key="text.create.desc"/></p>
                            </div>
                        </div>
                        <div class="col-md-4 col-md-pull-7">
                            <div class="booking-form">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <span class="form-label"><fmt:message key="text.create.checkIn"/>*</span>
                                            <input class="form-control" name="startDate" type="date" required
                                                   not-validated>
                                            <span class="error-message" style="margin: unset" for="startDate" hidden>
                                                <fmt:message key="text.createBooking.error"/>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <span class="form-label"><fmt:message key="text.create.checkOut"/>*</span>
                                            <input class="form-control" name="endDate" type="date" required
                                                   not-validated>
                                            <span class="error-message" style="margin: unset" for="endDate" hidden>
                                                <fmt:message key="text.profileSettings.invalidData"/></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-check form-check-inline" style="margin-bottom: 20px">
                                    <input class="form-check-input" name="hasWifi" type="checkbox"
                                           id="inlineCheckbox1"
                                           value="option1">
                                    <label class="form-check-label" for="inlineCheckbox1"
                                           style="margin-right: 20px">Wifi</label>
                                    <input class="form-check-input" name="hasTV" type="checkbox"
                                           id="inlineCheckbox2"
                                           value="option2">
                                    <label class="form-check-label" for="inlineCheckbox2"
                                           style="margin-right: 20px">TV</label>
                                    <input class="form-check-input" name="hasBathroom" type="checkbox"
                                           id="inlineCheckbox3"
                                           value="option3">
                                    <label class="form-check-label" for="inlineCheckbox3"
                                           style="margin-right: 20px"><fmt:message key="text.create.bathroom"/></label>
                                </div>
                                <div class="row">
                                    <div class="col-sm-8">
                                        <div class="form-group">
                                            <span class="form-label"><fmt:message key="text.create.roomGrade"/></span>
                                            <select class="form-control" name="gradeId">
                                                <option value="0"><fmt:message
                                                        key="text.create.roomGrade.economy"/></option>
                                                <option value="1"><fmt:message
                                                        key="text.create.roomGrade.standard"/></option>
                                                <option value="2"><fmt:message
                                                        key="text.create.roomGrade.suite"/></option>
                                                <option value="3"><fmt:message
                                                        key="text.create.roomGrade.premier"/></option>
                                                <option value="4"><fmt:message
                                                        key="text.create.roomGrade.premium"/></option>
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
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </select>
                                            <span class="select-arrow"></span>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <span class="form-label"><fmt:message key="text.create.beds"/></span>
                                            <select class="form-control" name="numberOfBeds">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </select>
                                            <span class="select-arrow"></span>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <span class="form-label"><fmt:message key="text.create.persons"/></span>
                                            <select class="form-control" name="maxPersons">
                                                <option value="2">2</option>
                                                <option value="4">4</option>
                                                <option value="6">6</option>
                                                <option value="8">8</option>
                                            </select>
                                            <span class="select-arrow"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-btn">
                                    <button id="create-booking" class="submit-btn"><fmt:message
                                            key="text.createBooking.button"/></button>
                                </div>
                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger" role="alert" style="margin: 20px">
                                        <fmt:message key="text.profileSettings.invalidData"/>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/jsp/part/footer.jsp"/>
    <script src="${pageContext.servletContext.contextPath}/resources/js/check_in_validator.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/protect_f5.js"></script>
    </body>
</fmt:bundle>
</html>

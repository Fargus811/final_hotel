<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
    <title>Registration</title>
</head>
<body>
<jsp:include page="/jsp/part/header.jsp"/>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="container-fluid">
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="registration" />
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputEmail4"><fmt:message key="text.registration.email"/>*</label>
                    <input name="email" type="email" class="form-control" id="inputEmail4" placeholder="<fmt:message key="text.registration.email"/>"
                           pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+" not-validated>
                    <span id="error-inputEmail4" class="error-message" hidden><fmt:message key="text.registration.invalidEmail"/></span>
                    <small id="emailHelp" class="form-text text-muted"><fmt:message key="text.registration.privacyEmail"/></small>
                </div>
                <div class="form-group col-md-6">
                    <label for="inputPassword4"><fmt:message key="text.registration.password"/>*</label>
                    <input name="password" type="password" class="form-control" id="inputPassword4" placeholder="<fmt:message key="text.registration.password"/>"
                           pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$" not-validated>
                    <span id="error-inputPassword4" class="error-message" hidden><fmt:message key="text.registration.passwordError"/></span>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputFirstName"><fmt:message key="text.registration.firstName"/>*</label>
                    <input name="firstName" type="firstName" class="form-control" id="inputFirstName"
                           placeholder="<fmt:message key="text.registration.firstName"/>" pattern="[A-ZА-Я][a-zа-я\-]{1,32}" not-validated>
                    <span id="error-inputFirstName" class="error-message" hidden><fmt:message key="text.registration.firstNameError"/></span>
                </div>
                <div class="form-group col-md-6">
                    <label for="inputLastName"><fmt:message key="text.registration.lastName"/>*</label>
                    <input name="lastName" type="lastName" class="form-control" id="inputLastName"
                           placeholder="<fmt:message key="text.registration.lastName"/>" pattern="[A-ZА-Я][a-zа-я\-]{1,32}" not-validated>
                    <span id="error-inputLastName" class="error-message" hidden><fmt:message key="text.registration.lastNameError"/></span>
                </div>
                <div class="row" style="margin-left: 20px;margin-bottom: 20px;">
                <c:if test="${not empty error}">
                    <span class="text-danger"><fmt:message key="text.logIn.error"/></span>
                </c:if>
                </div>
            </div>
            <button type="submit" class="btn btn-primary" id="submit-button" disabled><fmt:message key="text.registration.button"/></button>
        </form>
    </div>
    </div>
</div>
<script src="${pageContext.servletContext.contextPath}/resources/js/registration_validator.js"></script>
</fmt:bundle>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>

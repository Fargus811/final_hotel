<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Login</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<jsp:include page="/jsp/part/header.jsp"/>
<div class="row justify-content-center" style="margin-bottom: 50px;">
    <form formGroup="registerForm">
        <div class="form-group">
            <label for="inputEmail"><fmt:message key="text.logIn.email"/></label>
            <input type="email" class="form-control" id="InputEmail"
                   aria-describedby="emailHelp"
                   placeholder="Enter email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+" not-validated>
            <span class="text-danger" hidden><fmt:message key="text.logIn.email.error"/></span>
        </div>
        <div class="form-group">
            <label for="inputPassword"><fmt:message key="text.logIn.password"/></label>
            <input type="password" class="form-control" id="InputPassword" placeholder="<fmt:message key="text.logIn.password"/>"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$" not-validated>
            <span class="text-danger" hidden><fmt:message key="text.logIn.password.error"/></span>
        </div>
        <div class="form-group">
            <label><fmt:message key="text.logIn.password.confirm"/></label>
            <input type="inputConfirmPassword" class="form-control" id="InputConfirmPassword" placeholder="<fmt:message key="text.logIn.password"/>">
            <span class="text-danger" hidden><fmt:message key="text.logIn.password.error"/></span>
            <span class="text-danger" hidden>Passwords doesn't match</span>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-success" style="margin-top: 25px;" disabled><fmt:message key="text.logIn.button"/></button>
        </div>
    </form>
</div>
</body>
</fmt:bundle>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>

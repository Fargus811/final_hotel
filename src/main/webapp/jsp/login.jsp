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
    <title>Login</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<jsp:include page="/jsp/part/header.jsp"/>
<div class="row justify-content-center" style="margin-bottom: 50px;">
    <form action="${pageContext.request.contextPath}/controller" method="POST" style="margin-top: 50px">
        <input type="hidden" name="command" value="login"/>
        <div class="form-group">
            <label for="inputEmail"><fmt:message key="text.logIn.email"/></label>
            <input name="email" type="email" class="form-control" id="InputEmail"
                   aria-describedby="emailHelp"
                   placeholder="Enter email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+">
            <span class="text-danger" hidden><fmt:message key="text.logIn.email.error"/></span>
        </div>
        <div class="form-group">
            <label for="inputPassword"><fmt:message key="text.logIn.password"/></label>
            <input name="password" type="password" class="form-control" id="InputPassword"
                   placeholder="<fmt:message key="text.logIn.password"/>"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$"
                   title="<fmt:message key="text.registration.passwordError"/>">
            <span class="text-danger" hidden><fmt:message key="text.logIn.password.error"/></span>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-success" style="margin-top: 25px;"><fmt:message
                    key="text.logIn.button"/></button>
        </div>
    </form>
</div>
<div class="row justify-content-center" style="margin-bottom: 50px; margin-top: 20px">
    <c:if test="${not empty error}">
        <span class="text-danger"><fmt:message key="text.logIn.error"/></span>
    </c:if>
    <c:if test="${not empty errorBan}">
        <span class="text-danger"><fmt:message key="text.logIn.banned.error"/></span>
    </c:if>
</div>
</body>
</fmt:bundle>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>

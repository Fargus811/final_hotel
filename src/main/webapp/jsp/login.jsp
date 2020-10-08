<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <title>Login</title>
</head>
<body>
<jsp:include page="/jsp/part/header.jsp"/>
<div class="row justify-content-center" style="margin-bottom: 50px;">
    <form formGroup="registerForm">
        <input id="role" formControlName="role" value="0" hidden>
        <div class="form-group">
            <label for="InputEmail">Email</label>
            <input formControlName="email" type="email" class="form-control" id="InputEmail"
                   aria-describedby="emailHelp"
                   placeholder="Enter email">
            <span class="text-danger">Email is required</span>
            <span class="text-danger">Enter a valid email address</span>
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="InputEmail">Password</label>
            <input formControlName="name" type="text" class="form-control" id="name" placeholder="name">
            <span class="text-danger">Name is required</span>
        </div>
        <div class="form-group">
            <div class="form-group">
                <label for="InputPassword">Password</label>
                <input formControlName="password" type="password" class="form-control" id="InputPassword"
                       placeholder="Password">
                <span class="text-danger">Password is required</span>
            </div>
            <label>Confirm Password</label>
            <input type="password" class="form-control" formControlName="confirmPassword" placeholder="Password">
            <span class="text-danger">Confirm Password is required</span>
            <span class="text-danger">Passwords doesn't match</span>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-success" style="margin-top: 25px;">Register</button>
        </div>
    </form>
</div>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>

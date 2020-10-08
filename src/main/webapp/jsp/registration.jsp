<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-10-08
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Registration</title>
</head>
<body>
<jsp:include page="/jsp/part/header.jsp"/>
<div class="container-fluid">
    <form action="${pageContext.request.contextPath}/user/signupform" method="POST">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input name="email" type="email" class="form-control" id="inputEmail4" placeholder="Email"
                       pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Password</label>
                <input name="password" type="password" class="form-control" id="inputPassword4" placeholder="Password"
                       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputFirstName">First name</label>
                <input name="firstName" type="firtsname" class="form-control" id="inputFirstName"
                       placeholder="First name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputSurname">Surname</label>
                <input name="surname" type="surname" class="form-control" id="inputSurname" placeholder="Surname">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form>
</div>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>

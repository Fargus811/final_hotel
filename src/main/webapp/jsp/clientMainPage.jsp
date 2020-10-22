<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,600,900&amp;lang=en"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
<head>
    <fmt:setLocale value="${locale}"/>
    <fmt:bundle basename="text">
    <title>Title</title>
</head>
<jsp:include page="/jsp/part/header.jsp"/>
<body>
<div class="container">
    <div class="row">
        <div class="col" style="margin-top: 50px">
            <img class="img-profile img-circle img-responsive" src="https://bootdey.com/img/Content/avatar/avatar6.png"
                 alt=""
                 width="150px" ,
                 height="150px">
            <nav class="side-menu" style="width: 150px">
                <ul class="nav">
                    <li class="active"><a href="profile"><span class="fa fa-user"></span> Profile</a>
                    <li><a href="${pageContext.servletContext.contextPath}/controller?command=show_user_bookings"><span
                            class="fa fa-bookmark"></span> Bookings</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/jsp/createBooking.jsp"><span
                            class="glyphicon glyphicon-plus" aria-hidden="true"></span>Create Booking</a></li>
                    <li><a routerLink="mywallet"><span class="fa fa-envelope"></span> Contact</a></li>
                </ul>
            </nav>
        </div>
        <div class="col-md" style="margin-top: 50px">
            <div class="content-header-wrapper">
                <h2 class="title">My Profile</h2>
            </div>
            <div class="content-utilities">
                <div class="col-md-9  admin-content" id="profile">
                    <div class="panel panel-info" style="margin: 1em;">
                        <div class="panel-heading">
                            <h3 class="panel-title">Name</h3>
                        </div>
                        <div class="panel-body">${user.firstName} ${user.lastName}</div>
                    </div>
                    <div class="panel panel-info" style="margin: 1em;">
                        <div class="panel-heading">
                            <h3 class="panel-title">Email</h3>
                        </div>
                        <div class="panel-body">${user.email}</div>
                        <div class="panel-heading">
                            <h3 class="panel-title">Balance</h3>
                        </div>
                        <div class="panel-body">${user.balance}$</div>
                    </div>
                    <div class="panel-body">
                        <a role="button" (click)="openModal()" class="btn btn-primary btn-lg">Edit</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/part/footer.jsp"/>
</body>
</fmt:bundle>

</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/makeBooking.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
    <title>Error-403</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
    <jsp:include page="/jsp/part/header.jsp"/>
    <div id="main" class="container">
        <div class="background fail"></div>
        <div class="row justify-content-center">
            <div class="col-md-4 col-md-offset-4">
                <br>
                <br>
                <br>
                <br>
                <br>
                <div class="panel panel-success">
                    <div class="panel-heading info">
                        <h3 class="panel-title"><fmt:message key="text.info"/> 403</h3>
                    </div>
                    <div class="panel-body info">
                        <div class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg" width="150" height="150" viewBox="0 0 40 37">
                                <path stroke="#E22" stroke-width="7" d="m7,6 26,26m0-26-26,26"/>
                            </svg>
                        </div>
                        Доступ запрещён
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/jsp/part/footer.jsp"/>
</fmt:bundle>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Client Bookings</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <link rel="Stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"
          src="https://code.jquery.com/jquery-1.12.3.js" target="_blank">
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<jsp:include page="/jsp/part/header.jsp"/>
<h1 style="margin-top: 80px; margin-left: 20px;"><fmt:message key="text.admin.users.h1"/></h1>
<div id="MyTable_wrapper" class="dataTables_wrapper" style="margin: 40px; margin-bottom: 100px; margin-top: 50px">
    <table id="MyTable" class="display" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th><fmt:message key="text.client.name"/></th>
            <th><fmt:message key="text.logIn.email"/></th>
            <th><fmt:message key="text.client.balance"/></th>
            <th><fmt:message key="text.admin.users.status"/></th>
            <th><fmt:message key="text.admin.users.action"/></th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th>Name</th>
        </tr>
        </tfoot>
        <tbody>
        <c:forEach var="elem" items="${users}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.firstName} ${elem.lastName}</td>
                <td>${elem.email}</td>
                <td>${elem.balance}$</td>
                <td>${elem.accountStatus}</td>
                <td> <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <input type="hidden" name="command" value="show_user_bookings"/>
                    <input type="hidden" name="userId" value="${elem.id}"/>
                    <button type="submit" class="btn-primary" style="margin-right: 35px;background-color: #002583">View bookings</button>
                    <c:choose>
                        <c:when test="${elem.accountStatus == 'ACTIVE'}">
                            <button type="submit" class="danger"><fmt:message key="text.admin.users.ban"/></button></c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-primary" style="background-color: #002583"><fmt:message key="text.admin.users.unblock"/></button>
                    </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.servletContext.contextPath}/resources/js/pagination.js"></script>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</fmt:bundle>
</html>

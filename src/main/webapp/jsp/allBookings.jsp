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
<h1 style="margin: 60px"><fmt:message key="text.clientBook.h1"/></h1>
<div id="MyTable_wrapper" class="dataTables_wrapper" style="margin: 40px; margin-bottom: 100px; margin-top: 50px">
    <table id="MyTable" class="display" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th><fmt:message key="text.bookings.startDate"/></th>
            <th><fmt:message key="text.bookings.endDate"/></th>
            <th><fmt:message key="text.bookings.cost"/></th>
            <th><fmt:message key="text.bookings.room"/></th>
            <th><fmt:message key="text.bookings.status"/></th>
            <th><fmt:message key="text.bookings.action"/></th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th>Name</th>
        </tr>
        </tfoot>
        <tbody>
        <c:forEach var="elem" items="${bookings}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.startDate}</td>
                <td>${elem.endDate}</td>
                <td>${elem.cost}</td>
                <td><c:choose>
                    <c:when test="${empty elem.room}">
                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="show_free_room_by_condition"/>
                        <input type="hidden" name="bookingId" value="${elem.id}"/>
                        <button type="submit" class='button auth primary'
                                style="margin-right: 110px;margin-bottom: 10px;"
                                href="${pageContext.request.contextPath}/controller?command=show_free_room_by_condition">
                            <fmt:message
                                    key="text.client.chooseRoom"/></>
                        </form>
                    </c:when>
                    <c:otherwise>${elem.room.name}
                    </c:otherwise>
                </c:choose></td>
                <td>${elem.bookingStatus}</td>
                <td><c:choose>
                    <c:when test="${empty elem.room}">
                        <button class='button auth disabled' style="margin-right: 75px;margin-bottom: 10px;" disabled>
                            <fmt:message
                                    key="text.bookings.viewDetails"/></button>
                    </c:when>
                    <c:otherwise> <a class='button auth primary' style="margin-right: 110px;margin-bottom: 10px;"
                                     href="${pageContext.request.contextPath}/controller?command=show_free_room_by_condition"><fmt:message
                            key="text.bookings.viewDetails"/></a>
                    </c:otherwise>
                </c:choose></td>
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
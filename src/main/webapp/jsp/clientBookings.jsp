<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Client Bookings</title>
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
<h1 style="margin: 40px"><fmt:message key="text.clientBook.h1"/></h1>
<div id="MyTable_wrapper" class="dataTables_wrapper" style="margin: 40px; margin-bottom: 100px; margin-top: 50px">
    <table id="MyTable" class="display" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Cost</th>
            <th>Room</th>
            <th>Status</th>
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
                    No room
                      </c:when>
                      <c:otherwise>${elem.room}
                      </c:otherwise>
                     </c:choose></td>
                <td>${elem.bookingStatus}</td>
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

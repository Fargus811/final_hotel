<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client Bookings</title>
</head>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<link rel="Stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"
      src="https://code.jquery.com/jquery-1.12.3.js" target="_blank">
<body>
<jsp:include page="/jsp/part/header.jsp"/>
<div id="MyTable_wrapper" class="dataTables_wrapper" style="margin: 40px; margin-bottom: 100px">
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
        <td>${elem.room}</td>
        <td>${elem.bookingStatus}</td>
    </tr>
</c:forEach>

    </tbody>
</table>
</div>
<script>
    $(document).ready(function () {
        $('#MyTable').DataTable({
            initComplete: function () {
                this.api().columns().every(function () {
                    var column = this;
                    var select = $('<select><option value=""></option></select>')
                        .appendTo($(column.footer()).empty())
                        .on('change', function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            //to select and search from grid
                            column
                                .search(val ? '^' + val + '$' : '', true, false)
                                .draw();
                        });

                    column.data().unique().sort().each(function (d, j) {
                        select.append('<option value="' + d + '">' + d + '</option>')
                    });
                });
            }
        });
    });
</script>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>

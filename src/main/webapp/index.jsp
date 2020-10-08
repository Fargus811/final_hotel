<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <title>$Grand Hotel$</title>
</head>
<body>
<jsp:forward page="/controller" >
    <jsp:param name="command" value="show_all_rooms" />
</jsp:forward>
</body>
</html>


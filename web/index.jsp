<%-- 
    Document   : index.jsp
    Created on : Jul 14, 2021, 12:45:46 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:set var="book" value="${BOOK}"/>

    <c:if test="${not empty book}">

        ${book.title}

    </c:if>
</body>
</html>

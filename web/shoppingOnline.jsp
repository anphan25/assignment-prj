<%-- 
    Document   : shoppingOnline
    Created on : Jul 5, 2021, 4:08:40 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Online</title>
    </head>
    <body>
        <h1>Online Shopping</h1>
        <c:set var="productsList" value="${sessionScope.PRODUCTS_LIST}"/>
        <c:if test="${not empty productsList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${productsList}" varStatus="counter">
                    <form action="addItem">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${product.name}
                            </td>
                            <td>
                                ${product.price}
                            </td>
                            <td>
                                <input type="submit" value="Add to Cart"/>
                                <input type="hidden" name="productName" value="${product.name}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <a href="view">View Your Cart</a>
</body>
</html>

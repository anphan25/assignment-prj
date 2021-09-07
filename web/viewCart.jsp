<%-- 
    Document   : viewCart
    Created on : Jul 5, 2021, 9:31:27 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your Cart Include:</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="items" value="${cart.getItems()}"/>
        <c:if test="${not empty items}">
            <form action="removeCart">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="key" items="${items.keySet()}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${key}
                                </td>
                                <td>
                                    ${items.get(key)}
                                </td>
                                <td>
                                    ${cart.getPrice(key)}
                                </td>
                                <td>
                                    ${cart.getTotal(key)}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${key}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                                <a href="shoppingPage">Add more items to cart</a>
                            </td>
                            <td colspan="3">
                                <input type="submit" value="Remove Selected Items" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <form action="chechOut">
                <input type="submit" value="Check Out" />
            </form>
        </c:if>
        <c:if test="${empty items}">
            <h2>
                No cart is existed !!!
            </h2>
            <a href="shoppingPage">Shopping Page</a>
        </c:if>
    </body>
</html>

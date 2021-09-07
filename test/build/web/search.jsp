<%-- 
    Document   : search
    Created on : Jul 4, 2021, 2:34:43 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <font color="red">Welcome ${sessionScope.LASTNAME}</font>
        <form action="search" method="POST">
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" />
        </form>
        <form action="logout">
            <input type="submit" value="Logout" />
        </form>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="searchPageServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />(6 - 20 chars)<br/>
                                    <c:set var="errors" value="${sessionScope.UPDATE_ERROR}"/>
                                    <c:if test="${dto.username.equals(errors.flagUsername)}">
                                        <c:if test="${not empty errors.passwordLengthErr}"> 
                                            <font color="red">
                                            ${errors.passwordLengthErr}
                                            </font><br/>
                                        </c:if>
                                    </c:if>   
                                </td>
                                <td>
                                    ${dto.lastname}
                                    <input type="hidden" name="txtLastname" value="${dto.lastname}" />
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.isAdmin}">
                                               checked="checked"
                                           </c:if> />

                                </td>
                                <td>
                                    <c:url var="deleteLink" value="delete">
                                        <c:param name="username" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction"/>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                                <td>
                                    <input type="submit" value="Edit" name="btAction"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h1>
                NO RECORD IS MATCHED !!!
            </h1>
        </c:if>
    </c:if>
</body>
</html>

<%-- 
    Document   : createNewAccount.jsp
    Created on : Jul 6, 2021, 1:35:19 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <h1>Create New Account!</h1>
        <form action="create" method="POST">
            <c:set var="errors" value="${requestScope.INSERT_ERROR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6 - 12 chars)<br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                    ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="${param.txtPassword}" /> (6 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                    ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm*  <input type="password" name="txtConfirm" value="${param.txtConfirm}" /> <br/>
            <c:if test="${not empty errors.confirmNotMathced}">
                <font color="red">
                    ${errors.confirmNotMathced}
                </font><br/>
            </c:if>
            Lastname* <input type="text" name="txtLastname" value="${param.txtLastname}" /> (2 - 50 chars)<br/>
            <c:if test="${not empty errors.lastnameLengthErr}">
                <font color="red">
                    ${errors.lastnameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>

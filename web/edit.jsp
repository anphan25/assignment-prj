<%-- 
    Document   : edit
    Created on : Jul 5, 2021, 2:24:16 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
        <style>
            .hidden{
                display: none;
            }
        </style>

    </head>
    <body>
        <h1>Edit Page!</h1>
        <c:set var="lastSearchValue" value="${param.lastSearchValue}"/>

            <form action="choose">  
                <table border="1">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Password</th>
                            <th>LastName</th>
                            <th>isAdmin</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:set var="errors" value="${sessionScope.EDIT_ERROR}"/>
                        <tr>
                            <td>
                                ${param.txtUsername}
                                <input type="hidden" name="txtUsername" value="${param.txtUsername}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${param.txtPassword}" />(6 - 20 chars)
                                <c:if test="${not empty errors.passwordLengthErr}">
                                    <font color="red">
                                        ${errors.passwordLengthErr}
                                    </font>
                                </c:if>
                            </td>
                            <td>
                                <input type="text" name="txtLastname" value="${param.txtLastname}" />(2 - 50 chars)
                                <c:if test="${not empty errors.lastnameLengthErr}">
                                    <font color="red">
                                        ${errors.lastnameLengthErr}
                                    </font>
                                </c:if>
                            </td>
                            <td>
                                <c:set var="Admin" value="${param.chkAdmin}"/>
                                <input type="checkbox" name="chkAdmin" value="ON" 
                                       <c:if test="${not empty Admin}">
                                           checked="checked"
                                       </c:if>
                                       />
                            </td>
                        </tr>
                    </tbody>
                </table>  
                <input type="hidden" name="lastSearchValue" value="${lastSearchValue}" />
                <input class="btnCancel hidden" type="submit" value="Cancel" name="btAction" />
                <input class="btnConfirm hidden" type="submit" value="Confirm" name="btAction"/>
            </form>
        <button class="btnUpdate">Update</button><br/>
        
        <script type="text/javascript">
            const btnUpdate = document.querySelector(".btnUpdate");
            const btnCancel = document.querySelector(".btnCancel");
            const btnConfirm = document.querySelector(".btnConfirm");
            btnUpdate.addEventListener("click", function () {
                btnCancel.classList.remove("hidden");
                btnConfirm.classList.remove("hidden");
                btnUpdate.classList.add("hidden");
            });
        </script>
    </body>
</html>

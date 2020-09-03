<%-- 
    Document   : logout
    Created on : 28 Aug, 2020, 12:49:21 AM
    Author     : Abhinav Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            session.removeAttribute("userObject");
            session.removeAttribute("cap654Object");
            session.removeAttribute("adm325Object");
            session.removeAttribute("displayMessage");
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>

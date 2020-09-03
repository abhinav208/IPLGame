<%-- 
    Document   : player_rules
    Created on : 28 Aug, 2020, 1:13:22 AM
    Author     : Abhinav Kumar
--%>

<%@page import="IPL_BEANS.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="../Images/iplLogo.JPG" type="image/gif" sizes="16x16">
        <link rel="stylesheet" href="../Styles/iplStyles.css" type="text/css" />
        <link rel="stylesheet" href="../Styles/iplIndex.css" type="text/css" />
        <script src="../Styles/iplScript.js"></script>
        <script src="../Styles/tabs.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="../Styles/tabs.css" />
    </head>
    <body style="margin-top: 0px; background-image: url(../Images/bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("userObject");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>
        <div><%@include file="player_menu.jsp"%></div>
        <div style="width: 100%; height: 25px; background-color: white; font:1em Verdana, Geneva, sans-serif; color: #820D00;"><center>Hello <%=authUser.getUserFullname()%> (<%=authUser.getUserName()%>)</center></div>
        <br/><br/>
        <div id="content">
            <div id="tabs">
                <ul>
                    <li><a href="#" rel="tab_BID.html" class="selected"  onclick="loadit(this)">Bid</a></li>
                    <li><a href="#" rel="tab_GAW.html" onClick="loadit(this)">Guess and Win</a></li>
                    <li><a href="#" rel="tab_NBU.html" onClick="loadit(this)">NBU</a></li>
                </ul>
                <iframe id="container"></iframe>
            </div>
        </div>
        <br/>
    </body>
</html>

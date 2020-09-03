<%-- 
    Document   : player_password_change
    Created on : 28 Aug, 2020, 12:28:20 AM
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
    </head>
    <body style="margin-top: 0px; background-image: url(../Images/bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("userObject");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
            String oldPass = authUser.getUserPassword();
            String userID = authUser.getUserID();
        %>
        <div><%@include file="player_menu.jsp"%></div>
        <div style="width: 100%; height: 25px; background-color: white; font:1em Verdana, Geneva, sans-serif; color: #820D00;"><center>Hello <%=authUser.getUserFullname()%> (<%=authUser.getUserName()%>)</center></div>
        <br/>
        <div>
            <form action="ChangeUserPassword" method="POST" onsubmit="return validateChangePassword();">
                <table style="width: 60%; background-color: white;" border="1" align="center" cellpadding="10" cellspacing="0">
                    <tr>
                        <td colspan="2"><div style="font:1em Verdana, Geneva, sans-serif; color: #820D00;"><b>Change Password</b></div></td>
                    </tr>
                    <tr>
                        <td><b>Old Password</b></td>
                        <td><input type="password" name="oldPass" id="oldPass"/>
                            <input type="hidden" name="oldPassSystem" id="oldPassSystem" value="<%= oldPass%>"/></td>
                            <input type="hidden" name="userID" value="<%= userID%>"/></td>
                    </tr>
                    <tr>
                        <td><b>New Password</b></td>
                        <td><input type="password" name="newPass" id="newPass"/></td>
                    </tr>
                    <tr>
                        <td><b>Confirm Password</b></td>
                        <td><input type="password" name="confPass" id="confPass"/></td>
                    </tr>
                    <tr align="center">
                        <td colspan="2"><input type="submit" value="Change Password" class="button login_button"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <%
            String displayMessage = (String) session.getAttribute("displayMessage");
            if (displayMessage != null) {
                session.removeAttribute("displayMessage");
        %>
        <div id="displayMessageDiv" class="modal">
            <div class="modal-content animate container">
                <h2 class="login_table_head"><b><%=displayMessage%></b></h2>
                <button type="button" onclick="document.getElementById('displayMessageDiv').style.display='none'" class="button login_button">Ok</button>
            </div>
        </div>
        <%}%>
    </body>
</html>

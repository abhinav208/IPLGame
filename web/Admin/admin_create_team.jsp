<%-- 
    Document   : admin_create_team
    Created on : Mar 25, 2016, 3:40:03 AM
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
    <body style="margin-left: 20px; margin-top: 0px; background-image: url(../Images/admin_bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("adm325Object");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>

        <div><%@include file="admin_menu.jsp"%></div>
        <div>
            <br/><br/>
            <form action="CreateTeam" method="POST">
                <table border="1" cellpadding="3" cellspacing="0" style="width: 400px; margin-bottom: 20px;" align="center" id="adminCreateTable">
                    <tr align="center">
                        <th colspan="2"><b>Create Team</b></th>
                    </tr>
                    <tr>
                        <td><b>Team Name :</b></td>
                        <td><input type="text" name="teamName" /></td>
                    </tr>
                    <tr align="center">
                        <td colspan="2"><input type="Submit" value="Submit" class="button login_button"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="teamList"></div>
        <script>
            AllTeams();
        </script>
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
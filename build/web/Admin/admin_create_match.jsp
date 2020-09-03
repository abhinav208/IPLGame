<%-- 
    Document   : admin_create_match
    Created on : 27 Aug, 2020, 12:11:49 AM
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
    <body style="margin-top: 0px; background-image: url(../Images/admin_bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("adm325Object");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>
        <div><%@include file="admin_menu.jsp"%></div>
        <div>
            <br/><br/>
            <form action="CreateMatch" method="POST">
                <table border="1" cellpadding="3" cellspacing="0" style="width: 400px; margin-bottom: 20px;" align="center" id="adminCreateTable">
                    <tr align="center">
                        <th colspan="2"><b>Create Match</b></th>
                    </tr>
                    <tr>
                        <td><b>Team 1 : </b></td>
                        <td><div><%@include file="all_teams_drop_down1.jsp"%></div></td>
                    </tr>
                    <tr>
                        <td><b>Team 2 : </b></td>
                        <td><div><%@include file="all_teams_drop_down2.jsp"%></div></td>
                    </tr>
                    <tr>
                        <td><b>Round Type</b></td>
                        <td>
                            <select name="roundType">
                                <option value="LEAGUE">LEAGUE</option>
                                <option value="KNOCK OUT">KNOCK OUT</option>
                                <option value="FINAL">FINAL</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Match Date </b></td>
                        <td>
                            <input type="date" name="matchDate" onchange="updateDay(this.value)"/>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Start Time </b></td>
                        <td>
                            <input type="time" name="matchTime"/>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Match Day</b></td>
                        <td>
                            <input type="text" name="matchDay" id="matchDay" readonly="true"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="2"><input type="submit" value="Submit" class="button login_button"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="matchList"></div>                   
        <script>
            AllMatches();
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

<%-- 
    Document   : player_bug_page
    Created on : 22 Apr, 2020, 8:04:07 PM
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
        %>
        <div><%@include file="player_menu.jsp"%></div>
        <div style="width: 100%; height: 25px; background-color: white; font:1em Verdana, Geneva, sans-serif; color: #820D00;"><center>Hello <%=authUser.getUserFullname()%> (<%=authUser.getUserName()%>)</center></div>
        <BR/><BR/>
        <div>
            <form action="ReportIssue" method="POST">
                <table style="width: 60%; background-color: white;" border="1" align="center" cellpadding="10" cellspacing="0">
                    <tr><td colspan="2"><div style="font:1em Verdana, Geneva, sans-serif; color: #820D00;"><b>Issue/Bug Report</b></div></td></tr>
                    <tr>
                        <td><div style="font:0.8em Verdana, Geneva, sans-serif;"><b>Subject</b></div></td>
                        <td>
                            <input type="text" name="issueSubject" style="width: 100%; height: 100%; font:0.8em Verdana, Geneva, sans-serif;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><div style="font:0.8em Verdana, Geneva, sans-serif;"><b>Description</b></div></td>
                        <td><textarea rows="5" cols="20" name="issueDesc" style="width: 100%; height: 100%; font:0.8em Verdana, Geneva, sans-serif;"></textarea></td>
                    </tr>
                    <tr align="center">
                        <td colspan="2"> <input type="Submit" value="Report Issue" class="button login_button"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="bugList"></div>
        <script>
            AllBugs();
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

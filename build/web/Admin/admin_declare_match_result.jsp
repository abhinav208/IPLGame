<%-- 
    Document   : admin_declare_match_result
    Created on : 27 Aug, 2020, 2:17:46 AM
    Author     : Abhinav Kumar
--%>

<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.MatchBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.MatchLogic"%>
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
            <br/>
            <table style="width: 80%; background-color: white;" border="1" align="center" cellpadding="20" cellspacing="0" id="mainTable">
                <tr>
                    <th>
                <div style="margin-left: 10px;margin-top: 0px;font:1.5em Verdana, Geneva, sans-serif; color: #820D00;"><b>Select Match</b>&nbsp;&nbsp;

                    <select onchange="getClosedMatchDetails()" id="matchSelect" style="height: 30px; width: 550px; font:0.48em Verdana, Geneva, sans-serif; font-weight: bold;">
                        <option value=SELECT>SELECT</option>
                        <%
                            MatchLogic matchLogic = new MatchLogic();
                            ArrayList<MatchBean> closedMatchList = matchLogic.getClosedMatchList();
                            Iterator<MatchBean> listIterator = closedMatchList.iterator();
                            while (listIterator.hasNext()) {
                                MatchBean matchBean = listIterator.next();
                                String matchID = matchBean.getMatchID();
                                String matchDate = matchBean.getMatchDate();
                                String team1Name = matchBean.getTeam1Name();
                                String team2Name = matchBean.getTeam2Name();
                                String value = "M" + matchID + " - " + matchDate + " - " + team1Name + " Vs " + team2Name;
                        %>
                        <option value="<%= matchID%>"><%= value%></option>
                        <%}%>
                    </select>
                </div>
                </th>
                <tr>
                <tr id="myRow" align="center">
                    <td style="width: 400px;">
                        <div id="matchDetails"></div>
                    </td>
                </tr>
            </table>
        </div>
        <div style="display: none;" id="curtainContent">
            <img src="../Images/closed_curtain.jpg" style="width: 70%; height: 90%;"/>
        </div>
        <script>bidCurtain()</script>
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

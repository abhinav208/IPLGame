<%-- 
    Document   : player_bid_page
    Created on : 22 Apr, 2020, 8:04:07 PM
    Author     : abhinav
--%>

<%@page import="IPL_BEANS.MatchGraphBean"%>
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
    <body style="margin-top: 0px; background-image: url(../Images/bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("userObject");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>
        <div><%@include file="player_menu.jsp"%></div>
        <div style="width: 100%; height: 25px; background-color: white; font:1em Verdana, Geneva, sans-serif; color: #820D00;"><center>Hello <%=authUser.getUserFullname()%> (<%=authUser.getUserName()%>)</center></div>
        <div>
            <br/>
            <table style="width: 80%; background-color: white;" border="1" align="center" cellpadding="20" cellspacing="0" id="mainTable">
                <tr>
                    <th>
                <div style="margin-left: 10px;margin-top: 0px;font:1.5em Verdana, Geneva, sans-serif; color: #820D00;"><b>Select Match</b>&nbsp;&nbsp;
                    <form action="player_graph.jsp">
                        <select onchange="getGraphMatchDetails()" id="matchSelect" style="height: 30px; width: 550px; font:0.48em Verdana, Geneva, sans-serif; font-weight: bold;">
                            <option value=SELECT>SELECT</option>
                            <%
                                MatchLogic matchLogic = new MatchLogic();
                                ArrayList<MatchBean> closedMatchList = matchLogic.getResultDeclareMatchList();
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
                        <input type="Submit" value="View Graph" class=" button login_button"/>
                    </form>
                </div>
                </th>
                <tr>
                <tr id="myRow" align="center">
                    <td style="width: 400px;">
                        <div id="matchDetails">
                            <%
                                ArrayList<MatchGraphBean> graphDataListGlobal = (ArrayList<MatchGraphBean>) session.getAttribute("graphDataList");
                                if (graphDataListGlobal != null) {
                            %>
                            <%@include file="ajx_graph_match_details.jsp"%>
                            <%}%>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>

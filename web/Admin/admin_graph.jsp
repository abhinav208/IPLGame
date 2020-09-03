<%-- 
    Document   : admin_graph
    Created on : 27 Aug, 2020, 3:54:36 AM
    Author     : Abhinav Kumar
--%>

<%@page import="IPL_BEANS.MatchBean"%>
<%@page import="IPL_LOGIC.MatchLogic"%>
<%@page import="IPL_BEANS.MatchGraphBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@page import="IPL_BEANS.UserBean"%>
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
                    <form action="admin_graph.jsp">
                        <select onchange="getAdminGraphMatchDetails()" id="matchSelect" style="height: 30px; width: 550px; font:0.48em Verdana, Geneva, sans-serif; font-weight: bold;">
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
                            <%@include file="ajx_admin_graph_match_details.jsp"%>
                            <%}%>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>

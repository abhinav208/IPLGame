<%-- 
    Document   : ajx_all_teams_list
    Created on : 24 Aug, 2020, 8:52:10 PM
    Author     : Abhinav Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_BEANS.TeamBean"%>
<br/><br/><br/>
<table border="1" cellspacing="0" cellpadding="5" style="width: 500px; margin-bottom: 20px;" align="center" id="adminTable">
    <tr align="center">
        <th colspan="2"><b>Team Details</b></th>
    </tr>
    <tr align="center">
        <td><b>Team ID</b></td>
        <td><b>Team Name</b></td>
    </tr>
    <%
        ArrayList<TeamBean> teamList = (ArrayList<TeamBean>) request.getAttribute("teamList");
        Iterator<TeamBean> itr = teamList.iterator();
        while (itr.hasNext()) {
            TeamBean teamBean = itr.next();
    %>
    <tr>
        <td><%out.println(teamBean.getTeamID());%></td>
        <td><%out.println(teamBean.getTeamName());%></td>
    </tr>
    <%}%>
</table>
<br/><br/>

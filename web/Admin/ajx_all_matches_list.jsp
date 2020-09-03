<%-- 
    Document   : ajx_all_matches_list
    Created on : 27 Aug, 2020, 1:01:13 AM
    Author     : Abhinav Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="IPL_BEANS.MatchBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<br/><br/><br/>
<table border="1" cellspacing="0" cellpadding="5" style="width: 1200px; margin-bottom: 20px;" align="center" id="adminTable">
    <tr align="center">
        <th colspan="9"><b>Match Details</b></th>
    </tr>
    <tr align="center">
        <td><b>S.No</b></td>
        <td><b>Match ID</b></td>
        <td><b>Team1</b></td>
        <td><b>Team2</b></td>
        <td><b>Match Date</b></td>
        <td><b>Match Day</b></td>
        <td><b>Match Type</b></td>
        <td><b>Match Time</b></td>
        <td><b>Match Status</b></td>
    </tr>
<%
      ArrayList<MatchBean> matchList = (ArrayList<MatchBean>)request.getAttribute("matchList");
      Iterator<MatchBean> itr = matchList.iterator();
      while(itr.hasNext())
                   {
        MatchBean matchBean = itr.next();
%>
<tr>
    <td><%out.println(matchBean.getMatchID());%></td>
    <td><%out.println(matchBean.getMatchID());%></td>
    <td><%out.println(matchBean.getTeam1Name());%></td>
    <td><%out.println(matchBean.getTeam2Name());%></td>
    <td><%out.println(matchBean.getMatchDate());%></td>
    <td><%out.println(matchBean.getMatchDay());%></td>
    <td><%out.println(matchBean.getRoundType());%></td>
    <td><%out.println(matchBean.getStartTime());%> &nbsp; PM</td>
    <td><%out.println(matchBean.getMatchStatus());%></td>
</tr>
<%}%>
</table>
<br/><br/>

<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.TeamBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.TeamLogic"%>
<select name="teamNames2">
<%
    
    TeamLogic teamLogic2  = new TeamLogic();
    ArrayList<TeamBean> teamList2 = teamLogic2.getAllTeams();
    Iterator<TeamBean> itr2 = teamList2.iterator();
    while(itr2.hasNext())
               {
           TeamBean teamBean2 = itr2.next();
%>
<option value="<%out.println(teamBean2.getTeamID());%>"><%out.println(teamBean2.getTeamName());%></option>
<%}%>
</select>

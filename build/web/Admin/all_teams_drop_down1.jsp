<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.TeamBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.TeamLogic"%>
<select name="teamNames1">
<%
    
    TeamLogic teamLogic1  = new TeamLogic();
    ArrayList<TeamBean> teamList1 = teamLogic1.getAllTeams();
    Iterator<TeamBean> itr1 = teamList1.iterator();
    while(itr1.hasNext())
               {
           TeamBean teamBean1 = itr1.next();
%>
<option value="<%out.println(teamBean1.getTeamID());%>"><%out.println(teamBean1.getTeamName());%></option>
<%}%>
</select>

<%-- 
    Document   : player_match_list
    Created on : 28 Aug, 2020, 12:03:24 AM
    Author     : Abhinav Kumar
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.MatchLogic"%>
<%@page import="IPL_BEANS.MatchBean"%>
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
            <br/><br/>
            <table style="width: 1200px;" align="center" id="bidTable">
                <th style="width: 35px;">S.No</th>
                <th style="width: 170px;">Team1</th>
                <th style="width: 170px;">Team2</th>
                <th style="width: 100px;"> Date</th>
                <th style="width: 100px;"> Day</th>
                <th style="width: 100px;"> Time</th>
                <th style="width: 100px;">Team1 Collection</th>
                <th style="width: 100px;">Team2 Collection</th>
                <th style="width: 100px;">Your Points</th>
                <th style="width: 100px;">Status</th>
                <tr>
                    <th style="width: 35px;">&nbsp;</th>
                    <th style="width: 170px;"><input type="text" style="width: 100%;" id="team1Search" onkeyup="myFunction('bidTable',this.id,1)"/></th>
                    <th style="width: 170px;"><input type="text" style="width: 100%;" id="team2Search" onkeyup="myFunction('bidTable',this.id,2)"/></th>
                    <th style="width: 100px;"><input type="text" style="width: 100%;" id="dateSearch" onkeyup="myFunction('bidTable',this.id,3)"/></th>
                    <th style="width: 100px;"><input type="text" style="width: 100%;" id="daySearch" onkeyup="myFunction('bidTable',this.id,4)"/></th>
                    <th style="width: 100px;"><input type="text" style="width: 100%;" id="timeSearch" onkeyup="myFunction('bidTable',this.id,5)"/></th>
                    <th style="width: 100px;"><input style="width: 100%;" type="text" id="team1PointSearch" onkeyup="myFunction('bidTable',this.id,6)"/></th>
                    <th style="width: 100px;"><input style="width: 100%;" type="text" id="team2PointSearch" onkeyup="myFunction('bidTable',this.id,7)"/></th>
                    <th style="width: 100px;"><input type="text" style="width: 100%;" id="pointSearch" onkeyup="myFunction('bidTable',this.id,8)"/></th>
                    <th style="width: 100px;"><input type="text" style="width: 100%;" id="statusSearch" onkeyup="myFunction('bidTable',this.id,9)"/></th>
                </tr>
                <%
                    MatchBean matchBean = new MatchBean();
                    matchBean.setUserBean(authUser);
                    MatchLogic matchLogic = new MatchLogic(matchBean);
                    ArrayList<MatchBean> matchList = matchLogic.getAllMatchList();
                    Iterator<MatchBean> itr = matchList.iterator();
                    int i = 0;
                    while (itr.hasNext()) {
                        matchBean = itr.next();
                        i++;
                        String matchID = matchBean.getMatchID();
                        String team1ID = matchBean.getTeam1ID();
                        String team2ID = matchBean.getTeam2ID();
                        String userID = matchBean.getUserBean().getUserID();
                        String team1Name = matchBean.getTeam1Name();
                        String team2Name = matchBean.getTeam2Name();
                        String matchDate = matchBean.getMatchDate();
                        String matchDay = matchBean.getMatchDay();
                        String matchTime = matchBean.getStartTime();
                        String matchStatus = matchBean.getMatchStatus();
                        String team1Collection = matchBean.getTeam1Collection();
                        String team2Collection = matchBean.getTeam2Collection();
                        String usedBidingPoints = matchBean.getUserBean().getUserBidPoints();
                %>
                
                <tr>
                    <td style="width: 35px;"><%= i%></td>
                    <td style="width: 170px;"><a href="popup_team_player_list.jsp?teamID=<%= team1ID%>" onClick="return popup(this, 'notes')"><%= team1Name%></a></td>
                    <td style="width: 170px;"><a href="popup_team_player_list.jsp?teamID=<%= team2ID%>" onClick="return popup(this, 'notes')"><%= team2Name%></a></td>
                    <td style="width: 100px;"><%= matchDate%></td>
                    <td style="width: 100px;"><%= matchDay%></td>
                    <td style="width: 100px;"><%= matchTime%>&nbsp; PM</td>
                    <td style="width: 100px;"><a href="popup_team_collection.jsp?matchID=<%= matchID%>&teamID=<%= team1ID%>" onClick="return popup(this, 'notes')"><%= team1Collection%></a></td>
                    <td style="width: 100px;"><a href="popup_team_collection.jsp?matchID=<%= matchID%>&teamID=<%= team2ID%>" onClick="return popup(this, 'notes')"><%= team2Collection%></a></td>
                    <td style="width: 100px;"><a href="popup_user_collection.jsp?matchID=<%= matchID%>&userID=<%= userID%>" onClick="return popup(this, 'notes')"><%= usedBidingPoints%></a></td>
                    <td style="width: 100px;"><%= matchStatus%></td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>

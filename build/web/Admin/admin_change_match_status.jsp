<%-- 
    Document   : admin_change_match_status
    Created on : 27 Aug, 2020, 1:45:18 AM
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
    <body style="margin-top: 0px; background-image: url(../Images/admin_bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("adm325Object");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>
        <div><%@include file="admin_menu.jsp"%></div>
        <br/><br/>
        <div><form action="MatchBidCLose" method="Post">
                <table style="width: 1200px;" align="center" style="width: 1200px;" align="center" id="adminTable" border="2">
                    <tr>
                        <th style="width: 35px;">S.No</th>
                        <th style="width: 170px;">Team1</th>
                        <th style="width: 170px;">Team2</th>
                        <th style="width: 100px;"> Date</th>
                        <th style="width: 100px;"> Day</th>
                        <th style="width: 100px;"> Time</th>
                        <th style="width: 100px;">Team1 Collection</th>
                        <th style="width: 100px;">Team2 Collection</th>
                        <th style="width: 100px;">Status</th>
                        <th style="width: 100px;"></th>
                    </tr>
                    <%
                        UserBean userBean = new UserBean();
                        userBean.setUserID("0");
                        MatchBean matchBean = new MatchBean();
                        matchBean.setUserBean(userBean);
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
                            String buttonDisplay, buttonValue;
                            if (matchStatus.equals("BID NOT OPEN")) {
                                buttonDisplay = "BID OPEN";
                                buttonValue = matchID + "-" + buttonDisplay;
                            } else {
                                if (matchStatus.equals("BID OPEN")) {
                                    buttonDisplay = "BID CLOSED";
                                    buttonValue = matchID + "-" + buttonDisplay;
                                } else {
                                    buttonDisplay = "BID ALREADY CLOSE";
                                    buttonValue = matchID + "-" + buttonDisplay;
                                }
                            }
                    %>
                    <tr>
                        <td style="width: 35px;"><%= i%></td>
                        <td style="width: 170px;"><a href="#"><%= team1Name%></a></td>
                        <td style="width: 170px;"><a href="#"><%= team2Name%></a></td>
                        <td style="width: 100px;"><%= matchDate%></td>
                        <td style="width: 100px;"><%= matchDay%></td>
                        <td style="width: 100px;"><%= matchTime%>&nbsp; PM</td>
                        <td style="width: 100px;"><a href="../IPL_Players/popup_team_collection.jsp?matchID=<%= matchID%>&teamID=<%= team1ID%>" onClick="return popup(this, 'notes')"><%= team1Collection%></a></td>
                        <td style="width: 100px;"><a href="../IPL_Players/popup_team_collection.jsp?matchID=<%= matchID%>&teamID=<%= team2ID%>" onClick="return popup(this, 'notes')"><%= team2Collection%></a></td>
                        <td style="width: 100px;"><%= matchStatus%></td>
                        <td style="width: 100px;"><button type="submit" name="statusButton" class="button login_button" value="<%= buttonValue%>" ><%=buttonDisplay%></button></td>
                    </tr>

                    <%}%>
                </table></form>
        </div>
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

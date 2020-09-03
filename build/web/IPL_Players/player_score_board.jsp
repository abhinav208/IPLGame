<%-- 
    Document   : player_bid_page
    Created on : 22 Apr, 2020, 8:04:07 PM
    Author     : abhinav
--%>

<%@page import="IPL_DAO.GeneralFlagDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="IPL_LOGIC.ScoreBoardLogic"%>
<%@page import="IPL_BEANS.ScoreBoardBean"%>
<%@page import="java.util.ArrayList"%>
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
            <%
                ScoreBoardBean scoreBoardBean_Login = new ScoreBoardBean();
                scoreBoardBean_Login.setUserBean(authUser);
                ScoreBoardLogic scoreBoardLogic_Login = new ScoreBoardLogic(scoreBoardBean_Login);
                scoreBoardBean_Login = scoreBoardLogic_Login.getScoreBoardUsers();
                UserBean scoreLoginUser = scoreBoardBean_Login.getUserBean();
                String userName_Login = scoreLoginUser.getUserName();
                String totalPoint_Login = scoreLoginUser.getUserPoints();
                String gainPoint_Login = scoreLoginUser.getUserEarnedPoints();
                String losePoint_Login = scoreLoginUser.getUserLosedPoints();
                String userPosition_Login = scoreLoginUser.getUserPosition();
                String userBidPoints_Login = scoreLoginUser.getUserBidPoints();
            %>
            <br/><br/>
            <table id="bidTable2" style="width: 60%;" align="center">
                <tr><th colspan="6"><b>Your Score Board</b></th></tr>
                <tr>
                    <td><b>Username</b></td>
                    <td><b>Avail Points</b></td>
                    <td><b>Open Bid Points</b></td>
                    <td><b>Gain</b></td>
                    <td><b>Loss</b></td>
                    <td><b>Position</b></td>
                </tr>
                <tr>
                    <td><%= userName_Login%></td>
                    <td><%= totalPoint_Login%></td>
                    <td><%= userBidPoints_Login%></td>
                    <td><%= gainPoint_Login%></td>
                    <td><%= losePoint_Login%></td>
                    <td><%= userPosition_Login%></td>
                </tr>
            </table>
        </div>
        <br/> <br/>

        <div>
            <%GeneralFlagDao gd = new GeneralFlagDao();
            boolean viewOnlyTeamFlag = gd.getViewOnlyTeamStatus();
            if (viewOnlyTeamFlag) {
            %><%@include file="part_score_board_team.jsp"%>
            <%} else {%>
            <%@include file="part_score_board_all.jsp"%>
            <%}%>
        </div>
        <br/><br/><br/>
    </body>
</html>

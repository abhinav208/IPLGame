<%-- 
    Document   : part_score_board_all
    Created on : 22 Apr, 2020, 8:04:07 PM
    Author     : Abhinav Kumar
--%>

<%@page import="IPL_LOGIC.ScoreBoardLogic"%>
<%@page import="IPL_BEANS.ScoreBoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.UserBean"%>
<div>
    <table  id="bidTable" style="width: 60%;" align="center">
        <tr><th colspan="6"><b>All Users Scores</b></th></tr>
        <tr>
            <th><b>S.No</b></th>
            <th><b>Team</b></th>
            <th><b>Username</b></th>
            <th><b>Name</b></th>
            <th><b>Points</b></th>
            <th><b>Position</b></th>
        </tr>
        <tr>
            <th style="width: 35px; background-color: #FFFFFF;">&nbsp;</th>
            <th style="width: 50px; background-color: #FFFFFF;"><input type="text" id="teamSearch" onkeyup="myFunction('bidTable',this.id,1)"/></th>
            <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="userNameSearch" onkeyup="myFunction('bidTable',this.id,2)"/></th>
            <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="nameSearch" onkeyup="myFunction('bidTable',this.id,3)"/></th>
            <th style="width: 40px; background-color: #FFFFFF;"><input type="text" id="pointSearch" onkeyup="myFunction('bidTable',this.id,4)"/></th>
            <th style="width: 30px; background-color: #FFFFFF;"><input type="text" id="positionSearch" onkeyup="myFunction('bidTable',this.id,5)"/></th>                    
        </tr>
        <%
            UserBean loginUser = (UserBean) session.getAttribute("userObject");
            String loginUserTeam = loginUser.getUserTeam();
            String userID = "", userName = "", totalPoint = "", userPosition = "", userTeam = "", userFullName = "";
            ScoreBoardBean scoreBoardBean = new ScoreBoardBean();
            scoreBoardBean.setUserBean(loginUser);
            ScoreBoardLogic scoreBoardLogic = new ScoreBoardLogic(scoreBoardBean);
            scoreBoardBean = scoreBoardLogic.getScoreBoardUsers();
            UserBean scoreUser = scoreBoardBean.getUserBean();
            ArrayList<UserBean> scoreBoardList = scoreBoardBean.getScoreBoardList();
            Iterator itr = scoreBoardList.iterator();
            int sNo = 0;
            while (itr.hasNext()) {
                scoreUser = (UserBean) itr.next();
                userID = scoreUser.getUserID().trim();
                userName = scoreUser.getUserName();
                totalPoint = scoreUser.getUserPoints();
                userPosition = scoreUser.getUserPosition();
                userTeam = scoreUser.getUserTeam();
                userFullName = scoreUser.getUserFullname();
                if (userTeam.equalsIgnoreCase(loginUserTeam)) {
                    sNo++;
        %>

        <tr>
            <td><%= sNo%></td>
            <td><%= userTeam%></td>
            <td><a href="popup_user_trx_history.jsp?userID=<%= userID%>" onClick="return popup(this, 'notes')"><%= userName%></a></td>
            <td><%= userFullName%></td>
            <td><%= totalPoint%></td>
            <td><%= userPosition%></td>
        </tr>
        <%}}%>
    </table>
</div>

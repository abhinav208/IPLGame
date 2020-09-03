<%-- 
    Document   : admin_check_user_list
    Created on : 24 Aug, 2020, 11:37:26 PM
    Author     : Abhinav Kumar
--%>
<%@page import="IPL_LOGIC.ScoreBoardLogic"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_BEANS.ScoreBoardBean"%>
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
    <body style="margin-left: 20px; margin-top: 0px; background-image: url(../Images/admin_bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("adm325Object");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>

        <div><%@include file="admin_menu.jsp"%></div>
        <%
            UserBean userBean = new UserBean();
            userBean.setUserID("0");
            ScoreBoardBean scoreBoardBean = new ScoreBoardBean();
            scoreBoardBean.setUserBean(userBean);
            ScoreBoardLogic scoreBoardLogic = new ScoreBoardLogic(scoreBoardBean);
            scoreBoardBean = scoreBoardLogic.getScoreBoardUsers();

            UserBean scoreUser = scoreBoardBean.getUserBean();
            String userId;
            String userTeam;
            String userName;
            String userEmail;
            String totalPoint;
            String userPosition;
            String userStatus;

            ArrayList<UserBean> scoreBoardList = scoreBoardBean.getScoreBoardList();
        %>
        <br/><br/>
        <div>
            <table border="1" cellpadding="3" cellspacing="0" style="width: 1200px;" align="center" id="adminTable">
                <tr><th colspan="7"><b>All Users Scores</b></th></tr>
                <tr>
                    <th><b>Position</b></th>
                    <th><b>Team</b></th>
                    <th><b>Username</b></th>
                    <th><b>Name</b></th>
                    <th><b>Email Id</b></th>
                    <th><b>Points</b></th>
                    <th><b>Status</b></th>
                </tr>
                <tr>
                    <th style="background-color: #FFFFFF;"><input type="text" id="positionSearch" onkeyup="myFunction('adminTable',this.id,0)" style="width: 40px;"/></th>
                    <th style="background-color: #FFFFFF;"><input type="text" id="userNameSearch" onkeyup="myFunction('adminTable',this.id,1)" style="width: 50px;"/></th>
                    <th style="background-color: #FFFFFF;"><input type="text" id="userNameSearch" onkeyup="myFunction('adminTable',this.id,2)" style="width: 150px;"/></th>
                    <th style="background-color: #FFFFFF;"><input type="text" id="nameSearch" onkeyup="myFunction('adminTable',this.id,3)" style="width: 200px;"/></th>
                    <th style="background-color: #FFFFFF;"><input type="text" id="emailSearch" onkeyup="myFunction('adminTable',this.id,4)" style="width: 250px;"/></th>
                    <th style="background-color: #FFFFFF;"><input type="text" id="pointSearch" onkeyup="myFunction('adminTable',this.id,5)" style="width: 80px;"/></th>
                    <th style="background-color: #FFFFFF;"><input type="text" id="statusSearch" onkeyup="myFunction('adminTable',this.id,6)" style="width: 60px;"/></th>
                </tr>
                <%
                    Iterator itr = scoreBoardList.iterator();
                    int sNo = 0;
                    while (itr.hasNext()) {
                        sNo++;
                        scoreUser = (UserBean) itr.next();
                        userTeam = scoreUser.getUserTeam();
                        userId = scoreUser.getUserName();
                        userName = scoreUser.getUserFullname();
                        userEmail = scoreUser.getUserEmail();
                        totalPoint = scoreUser.getUserPoints();
                        userPosition = scoreUser.getUserPosition();
                        userStatus = scoreUser.getUserStatus();
                %>

                <tr>
                    <td><%= userPosition%></td>
                    <td><%= userTeam%></td>
                    <td><%= userId%></td>
                    <td><%= userName%></td>
                    <td><%= userEmail%></td>
                    <td><%= totalPoint%></td>
                    <td><%= userStatus%></td>
                </tr>
                <%}%>
            </table>
        </form>
    </div>
    <br/><br/>
</body>
</html>

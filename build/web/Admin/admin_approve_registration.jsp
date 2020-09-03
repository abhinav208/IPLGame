<%-- 
    Document   : admin_approve_registration
    Created on : 10 May, 2020, 7:48:55 PM
    Author     : Abhinav Kumar
--%>

<%@page import="IPL_LOGIC.UserLogic"%>
<%@page import="java.util.Iterator"%>
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
    <body style="margin-top: 0px; background-image: url(../Images/admin_bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("adm325Object");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>
        <div><%@include file="admin_menu.jsp"%></div>
        <%
            String userId;
            String userName;
            String userEmail;
            String totalPoint;
            String userStatus;
            String user_userName;
            String user_team;
            UserBean UserDetails = new UserBean();
            UserLogic userLogic = new UserLogic();
        %>
        <br/><br/>
        <div><form action="updateUserStatus" method="Post">
                <input type="hidden" name="originPage" value="admin_approve_registration.jsp">
                <center>
                    <button type="submit" name="statusButton" value="ACTIVE" class="button login_button">Active</button>
                    <button type="submit" name="statusButton" value="INACTIVE" class="button login_button">In-Active</button>
                </center>
                <br/><br/>
                <table border="1" cellpadding="3" cellspacing="0" style="width: 1200px;" align="center" id="adminTable">

                    <tr><th colspan="8"><b>All Users</b></th></tr>
                    <tr>
                        <th><b></b></th>
                        <th><b>UserID</b></th>
                        <th><b>Username</b></th>
                        <th><b>Name</b></th>
                        <th><b>Team</b></th>
                        <th><b>Email Id</b></th>
                        <th><b>Points</b></th>
                        <th><b>Status</b></th>
                    </tr>
                    <tr>
                        <th style="background-color: #FFFFFF;"><b></b></th>
                        <th style="background-color: #FFFFFF;"><input type="text" id="userIDSearch" onkeyup="myFunction('adminTable',this.id,1)" style="width: 40px;"/></th>
                        <th style="background-color: #FFFFFF;"><input type="text" id="userNameSearch" onkeyup="myFunction('adminTable',this.id,2)" style="width: 150px;"/></th>
                        <th style="background-color: #FFFFFF;"><input type="text" id="nameSearch" onkeyup="myFunction('adminTable',this.id,3)" style="width: 150px;"/></th>
                        <th style="background-color: #FFFFFF;"><input type="text" id="teamSearch" onkeyup="myFunction('adminTable',this.id,4)" style="width: 80px;"/></th>
                        <th style="background-color: #FFFFFF;"><input type="text" id="emailSearch" onkeyup="myFunction('adminTable',this.id,5)" style="width: 250px;"/></th>
                        <th style="background-color: #FFFFFF;"><input type="text" id="pointSearch" onkeyup="myFunction('adminTable',this.id,6)" style="width: 60px;"/></th>
                        <th style="background-color: #FFFFFF;"><input type="text" id="statusSearch" onkeyup="myFunction('adminTable',this.id,7)" style="width: 60px;"/></th>
                    </tr>
                    <%
                        ArrayList<UserBean> AllUserList = userLogic.getAllUserDetails();
                        Iterator itr = AllUserList.iterator();
                        while (itr.hasNext()) {
                            UserDetails = (UserBean) itr.next();
                            userId = UserDetails.getUserID();
                            user_userName = UserDetails.getUserName();
                            userName = UserDetails.getUserFullname();
                            userEmail = UserDetails.getUserEmail();
                            totalPoint = UserDetails.getUserPoints();
                            userStatus = UserDetails.getUserStatus();
                            user_team = UserDetails.getUserTeam();
                            if (userStatus.equals("APPLIED")) {
                    %>

                    <tr>
                        <td><input type="checkbox" name="userIDs" value="<%=userId%>"/></td>
                        <td><%= userId%></td>
                        <td><%= user_userName%></td>
                        <td><%= userName%></td>
                        <td><%= user_team%></td>
                        <td><%= userEmail%></td>
                        <td><%= totalPoint%></td>
                        <td><%= userStatus%></td>
                    </tr>
                    <%}}%>
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

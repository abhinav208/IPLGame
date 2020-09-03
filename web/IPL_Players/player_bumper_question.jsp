<%-- 
    Document   : player_bumper_question
    Created on : 22 Apr, 2020, 8:04:07 PM
    Author     : abhinav
--%>

<%@page import="IPL_DAO.GeneralFlagDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.BumperQuestionLogic"%>
<%@page import="IPL_BEANS.BumperQuestionBean"%>
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
            boolean redirectFlag = false;
            if (authUser == null) {
                redirectFlag = true;
            }
            GeneralFlagDao gd = new GeneralFlagDao();
            String viewPageFlag = gd.getBumperstatus();
            if (viewPageFlag.equals("NOT OPEN")) {
                redirectFlag = true;
            }
            if (redirectFlag) {
                response.sendRedirect("../logout.jsp");
            }
        %>
        <div><%@include file="player_menu.jsp"%></div>
        <div style="width: 100%; height: 25px; background-color: white; font:1em Verdana, Geneva, sans-serif; color: #820D00;"><center>Hello <%=authUser.getUserFullname()%> (<%=authUser.getUserName()%>)</center></div>
        <div>
            <form action="SaveBumperAnswers" method="POST">
                <br/><br/>
                <table border="1" style="width: 750px;" align="center" id="bidTable">
                    <tr>
                        <th><b>S.No</b></th>
                        <th><b>Questions</b></th>
                        <th><b>Answer</b></th>
                    </tr>
                    <%
                        BumperQuestionBean bumberQuestionBean = new BumperQuestionBean();
                        BumperQuestionLogic bumberLogic = new BumperQuestionLogic(bumberQuestionBean);
                        bumberLogic.openConnection();
                        ArrayList<BumperQuestionBean> questionList = bumberLogic.prepareActiveBumberQuestionArrayList();
                        bumberQuestionBean.setUserBean(authUser);
                        ArrayList<BumperQuestionBean> userList = bumberLogic.getUserAsnwers();
                        bumberLogic.closeConnection();
                        Iterator<BumperQuestionBean> questionsIterator = questionList.iterator();
                        int rowCounter = 0;

                        while (questionsIterator.hasNext()) {
                            rowCounter++;
                            BumperQuestionBean question = questionsIterator.next();
                            String question_ID = question.getQustionID().trim();
                            String guess_question = question.getBumperQuestion();
                            String userAnswer = "", userAttempt = "False";

                            Iterator userListIterator = userList.iterator();
                            while (userListIterator.hasNext()) {
                                BumperQuestionBean tempBumpBean = (BumperQuestionBean) userListIterator.next();
                                if (tempBumpBean.getQustionID().trim().equalsIgnoreCase(question_ID)) {
                                    userAnswer = tempBumpBean.getUserAnswer().trim();
                                    userAttempt = "True";

                                }
                            }
                    %>
                    <tr>
                        <td><%= rowCounter%></td>
                        <td><%= guess_question%></td>
                        <td>
                            <% if (viewPageFlag.equals("OPEN")) {%>
                            <input type="text" name="BQID<%= question_ID%>" value="<%=userAnswer%>" class="login_text_box"/>
                            <%} else {%>
                            <input type="text" name="BQID<%= question_ID%>" readonly value="<%=userAnswer%>" class="login_text_box"/>
                            <%}%>
                            <input type="hidden" name="ATTBQID<%= question_ID%>" value="<%=userAttempt%>"/>
                            <input type="hidden" name="quesID" value="<%= question_ID%>"/>
                        </td>
                    </tr>
                    <%}
                        if (rowCounter == 0) {%>
                    <tr align="center">
                        <td colspan="3">No questions are Opened</td>
                    </tr>
                    <%} else {%>
                    <tr>
                        <td colspan="3">
                            <% if (viewPageFlag.equals("OPEN")) {%>
                            <div style="float: left; color: black; font-size: 12px; background-color: yellow;"> <b>***Known abbreviations are accepted</b></div>
                            <br/>
                            <div style="float: right;">
                                <input type="submit" value="Submit" class=" button login_button"/>
                            </div>
                            <%}%>
                        </td>
                    </tr>
                    <%}%>
                </table>
            </form>
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

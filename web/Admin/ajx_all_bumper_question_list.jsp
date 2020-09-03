<%-- 
    Document   : ajx_all_bumper_question_list
    Created on : 28 Aug, 2020, 11:10:31 PM
    Author     : Abhinav Kumar
--%>

<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.BumperQuestionBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br/><br/><br/>
<table border="1" cellspacing="0" cellpadding="5" style="width: 500px; margin-bottom: 20px;" align="center" id="adminTable">
    <tr align="center">
        <th colspan="3"><b>Bumper Question</b></th>
    </tr>
    <tr align="center">
        <td><b>Number</b></td>
        <td><b>Question</b></td>
        <td><b>Status</b></td>
    </tr>
    <%
        ArrayList<BumperQuestionBean> questionList = (ArrayList<BumperQuestionBean>) request.getAttribute("bumperQuestionList");
        Iterator<BumperQuestionBean> itr = questionList.iterator();
        while (itr.hasNext()) {
            BumperQuestionBean questionBean = itr.next();
    %>
    <tr>
        <td><%out.println(questionBean.getQustionID());%></td>
        <td><%out.println(questionBean.getBumperQuestion());%></td>
        <td><%out.println(questionBean.getQuestionStatus());%></td>
    </tr>
    <%}%>
</table>
<br/><br/>

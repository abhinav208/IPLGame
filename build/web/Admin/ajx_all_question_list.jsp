<%-- 
    Document   : ajx_all_question_list
    Created on : 25 Aug, 2020, 12:58:27 AM
    Author     : Abhinav Kumar
--%>

<%@page import="IPL_BEANS.QuestionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<br/><br/><br/>
<table border="1" cellspacing="0" cellpadding="5" style="width: 500px; margin-bottom: 20px;" align="center" id="adminTable">
    <tr align="center">
        <th colspan="2"><b>Question Details</b></th>
    </tr>
    <tr align="center">
        <td><b>Number</b></td>
        <td><b>Question</b></td>
    </tr>
    <%
        ArrayList<QuestionBean> questionList = (ArrayList<QuestionBean>) request.getAttribute("questionList");
        Iterator<QuestionBean> itr = questionList.iterator();
        while (itr.hasNext()) {
            QuestionBean questionBean = itr.next();
    %>
    <tr>
        <td><%out.println(questionBean.getQuestionID());%></td>
        <td><%out.println(questionBean.getUploadQuestion());%></td>
    </tr>
    <%}%>
</table>
<br/><br/>

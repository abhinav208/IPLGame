<%-- 
    Document   : ajx_user_all_bugs_list
    Created on : 2 Sep, 2020, 12:11:42 AM
    Author     : Abhinav Kumar
--%>

<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.BugBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br/><br/><br/>
<table border="1" cellspacing="0" cellpadding="5" style="width: 1200px; margin-bottom: 20px;" align="center" id="bidTable">
    <tr align="center">
        <th colspan="9"><b>User Issues</b></th>
    </tr>
    <tr align="center">
        <td><b>Issue Id.</b></td>
        <td><b>Subject</b></td>
        <td><b>Detail</b></td>
        <td><b>Solution</b></td>
    </tr>
    <%
        ArrayList<BugBean> return_BugArray = (ArrayList<BugBean>) request.getAttribute("return_BugArray");
        Iterator<BugBean> itr = return_BugArray.iterator();
        while (itr.hasNext()) {
            BugBean bb = itr.next();
    %>
    <tr>
        <td><%out.println(bb.getBugId());%></td>
        <td><%out.println(bb.getBugSubject());%></td>
        <td><%out.println(bb.getBugDetail());%></td>
        <td><%out.println(bb.getBugSolution());%></td>
    </tr>
    <%}%>
</table>
<br/><br/>

<%-- 
    Document   : player_bid_page
    Created on : 22 Apr, 2020, 8:04:07 PM
    Author     : abhinav
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.TransactionLogic"%>
<%@page import="IPL_BEANS.TransactionBean"%>
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
            <br/>
            <table style="width: 1200px;" align="center" id="bidTable">
                <th colspan="8"><b>User Transaction Details</b></th>
                <tr>
                    <th><b>S.No</b></th>
                    <th><b>Type</b></th>
                    <th><b>Title</b></th>
                    <th><b>Date</b></th>
                    <th><b>Time</b></th>
                    <th><b>Amount</b></th>
                    <th><b>Opening Balance</b></th>
                    <th><b>Closing Balance</b></th>
                </tr>
                <tr>
                    <th style="width: 35px; background-color: #FFFFFF;">&nbsp;</th>
                    <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="typeSearch" onkeyup="myFunction('bidTable',this.id,1)"/></th>
                    <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="titleSearch" onkeyup="myFunction('bidTable',this.id,2)"/></th>
                    <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="dateSearch" onkeyup="myFunction('bidTable',this.id,3)"/></th>
                    <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="timeSearch" onkeyup="myFunction('bidTable',this.id,4)"/></th>
                    <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="amountSearch" onkeyup="myFunction('bidTable',this.id,5)"/></th>
                    <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="openingPointSearch" onkeyup="myFunction('bidTable',this.id,6)"/></th>
                    <th style="width: 60px; background-color: #FFFFFF;"><input type="text" id="closingPointSearch" onkeyup="myFunction('bidTable',this.id,7)"/></th>
                </tr>

                <%
                    TransactionBean trxBean = new TransactionBean();
                    trxBean.setUserID(authUser.getUserID());
                    TransactionLogic transactionLogic = new TransactionLogic(trxBean);
                    ArrayList<TransactionBean> trxList = transactionLogic.getUserTrxList();
                    Iterator<TransactionBean> listIterator = trxList.iterator();
                    int row_counter = 0;
                    while (listIterator.hasNext()) {
                        row_counter++;
                        trxBean = listIterator.next();
                        String transactionType = trxBean.getTrxType().toUpperCase().trim();
                        String transactionTitle = trxBean.getTrxTitle().toUpperCase().trim();
                        String transactionDate = trxBean.getTrxDate().trim();
                        String transactionTime = trxBean.getTrxTime().trim();
                        String transactionAmount = trxBean.getTrxAmount().trim();
                        String openingBalance = trxBean.getOpeningBalance().trim();
                        String closingBalance = trxBean.getClosingBalance().trim();

                %>
                <tr>
                    <td><%= row_counter%></td>
                    <td><%= transactionType%></td>
                    <td><%= transactionTitle%></td>
                    <td><%= transactionDate%></td>
                    <td><%= transactionTime%></td>
                    <td><%= transactionAmount%></td>
                    <td><%= openingBalance%></td>
                    <td><%= closingBalance%></td>
                </tr>
                <%}%>
            </table>
        </div>
        <br/><br/>
    </body>
</html>

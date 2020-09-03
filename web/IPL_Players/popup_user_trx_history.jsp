<%-- 
    Document   : ipl_player_match_list
    Created on : Apr 5, 2016, 2:48:15 AM
    Author     : Abhinav Kumar
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
        <script src="../Styles/iplScripts.js"></script>
        <link rel="stylesheet" href="../Styles/iplStyles.css" type="text/css" />
    </head>
    <body style="background-color: #ccc; padding: 0px; margin: 0px; overflow-y: scroll;">
        <div>
            <br/>
            <table style="width: 100%;" align="center" id="bidTable">
                <th colspan="8"><b>User Transaction Details</b></th>
                <tr>
                <td><b>S.No</b></td>
                <td><b>Type</b></td>
                <td><b>Title</b></td>
                <td><b>Date</b></td>
                <td><b>Time</b></td>
                <td><b>Amount</b></td>
                <td><b>Opening Balance</b></td>
                <td><b>Closing Balance</b></td>
            </tr>
            <%
                 String userID = request.getParameter("userID");
                 TransactionBean trxBean = new TransactionBean();
                 trxBean.setUserID(userID);
                 TransactionLogic transactionLogic = new TransactionLogic(trxBean);
                 ArrayList<TransactionBean> trxList =  transactionLogic.getUserTrxList();
                 Iterator<TransactionBean> listIterator = trxList.iterator();
                 int row_counter = 0;
                 while(listIterator.hasNext())
                                         {
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

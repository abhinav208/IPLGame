<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../Styles/iplStyles.css" type="text/css" />
        <script src="../Styles/iplScript.js"></script>
    </head>
    <body style="background-color: #ccc; padding: 0px; margin: 0px; overflow-y: scroll;">
        <%@page import="java.sql.SQLException"%>
        <%@page import="IPL_LOGIC.MatchCollectionLogic"%>
        <%@page import="java.util.Iterator"%>
        <%@page import="IPL_BEANS.MatchCollection"%>
        <%@page import="java.util.ArrayList"%>
        <br/>
        <table border="1" id="bidTable" style="width: 100%;" align="center">
            <th colspan="7">User Collection Details</th>
            <tr>
                <td><b>S.No</b></td>
                <td><b>Team Name</b></td>
                <td><b>Bid Points</b></td>
                <td><b>Bid Date</b></td>
                <td><b>Bid Time</b></td>
                <td><b>Result Points</b></td>
                <td><b>Bid Status</b></td>
            </tr>
            <%
                String matchID = request.getParameter("matchID");
                String userID = request.getParameter("userID");
                MatchCollection colBean = new MatchCollection();
                colBean.setMatchID(matchID);
                colBean.setUserID(userID);
                ArrayList<MatchCollection> collectionList = null;
                MatchCollectionLogic collectionLogic = new MatchCollectionLogic(colBean);
                try {
                    if (!collectionLogic.openConnection()) {
                        System.out.println("POP_TEAM_COLLECTION:openConnection: Exception occured while "
                                + "opening connection is : \n");
                        throw new SQLException();
                    }
                    collectionList = collectionLogic.getUserCollectionList();
                    if (!collectionLogic.closeConnection()) {
                        System.out.println("POP_TEAM_COLLECTION:closeConnection: Exception occured while "
                                + "closing connection is : \n");
                        throw new SQLException();
                    }
                } catch (SQLException sqlException) {
                    System.out.println("POP_TEAM_COLLECTION:closeConnection: Exception occured while "
                            + "closing connection is : \n " + sqlException);
                }
                Iterator<MatchCollection> itr = collectionList.iterator();
                int i = 0;
                while (itr.hasNext()) {
                    i++;
                    MatchCollection collectionBean = itr.next();
                    String teamName = collectionBean.getBidTeam();
                    String bidPoint = collectionBean.getAmount();
                    String bidDate = collectionBean.getBidDate();
                    String bidTime = collectionBean.getBidTime();
                    String resultAmt = collectionBean.getResultAmount();
                    String colStatus = collectionBean.getColStatus();
            %>
            <tr>
                <td><%out.println(i);%></td>
                <td><%out.println(teamName);%></td>
                <td><%out.println(bidPoint);%></td>
                <td><%out.println(bidDate);%></td>
                <td><%out.println(bidTime);%></td>
                <td><%out.println(resultAmt);%></td>
                <td><%out.println(colStatus);%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
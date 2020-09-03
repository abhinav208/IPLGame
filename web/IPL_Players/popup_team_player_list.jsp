<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.TeamLogic"%>
<%@page import="IPL_BEANS.TeamBean"%>
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
                <th colspan="2"><b>Team Player List</b></th>

                <%
                    String teamID = request.getParameter("teamID");
                    TeamBean teamBean = new TeamBean();
                    teamBean.setTeamID(teamID);
                    TeamLogic teamLogic = new TeamLogic(teamBean);
                    ArrayList<TeamBean> trxList = teamLogic.getPlayerNames();
                    Iterator<TeamBean> listIterator = trxList.iterator();
                    int row_counter = 0;
                    while (listIterator.hasNext()) {
                        row_counter++;
                        teamBean = listIterator.next();
                        String playerName = teamBean.getPlayerName().toUpperCase().trim();

                %>
                <tr>
                    <td><%= row_counter%></td>
                    <td><%= playerName%></td>
                </tr>
                <%}%>
            </table>
        </div>
        <br/><br/>
    </body>
</html>

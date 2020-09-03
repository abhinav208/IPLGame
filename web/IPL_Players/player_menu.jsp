<%@page import="IPL_DAO.GeneralFlagDao"%>
<%@page import="IPL_BEANS.UserBean"%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
        <meta http-equiv="content-type" content="text/html;charset=utf-8" />
        <link type="text/css" href="../Styles/menu.css" rel="stylesheet" />
        <script type="text/javascript" src="../Styles/menu.js"></script>
    </head>
    <body>
        <%
            UserBean menuUser = (UserBean) session.getAttribute("cap654Object");
        %>
        <style type="text/css">
            body {
                overflow:hidden;
            }
            div#copyright { display: none; }
        </style>

        <div id="menu">
            <ul class="menu">
                <li><a href="player_bid_page.jsp" class="parent"><span>Bid Page</span></a></li>
                <li><a href="player_match_list.jsp" class="parent"><span>Match List</span></a></li>
                <li><a href="player_declare_match_result.jsp" class="parent"><span>Match Result</span></a></li>
                <li><a href="player_score_board.jsp" class="parent"><span>Score Board</span></a></li>
                <%
                    GeneralFlagDao gdg = new GeneralFlagDao();
                    if (!gdg.getBumperstatus().equals("NOT OPEN")) {
                %>
                <li><a href="player_bumper_question.jsp" class="parent"><span>Bumper Question</span></a></li>
                <%}%>
                <li><a href="player_trx_list.jsp" class="parent"><span>Transaction History</span></a></li>
                <li><a href="player_graph.jsp" class="parent"><span>Graph</span></a></li>
                <%if (menuUser != null) {%>
                <li><a href="captain_approve_registration.jsp" class="parent"><span>Approve Registration</span></a></li>
                <%}%>
                <li><a href="player_bug_page.jsp" class="parent"><span>Issue/Bug</span></a></li>
                <li><a href="player_rules.jsp" class="parent"><span>Rules</span></a></li>
                <li><a href="player_password_change.jsp" class="parent"><span>Change Password</span></a></li>
                <li><a href="../logout.jsp" class="parent"><span>Logout</span></a></li>
            </ul>
        </div>
        <div><a href="#"></a></div>
    </body>
</html>
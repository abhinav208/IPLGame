<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.QuestionBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_BEANS.MatchBean"%>
<%
    MatchBean matchBean = (MatchBean) request.getAttribute("matchDetail");
    String matchID = matchBean.getMatchID();
    String team1Name = matchBean.getTeam1Name();
    String team1ID = matchBean.getTeam1ID();
    String team2Name = matchBean.getTeam2Name();
    String team2ID = matchBean.getTeam2ID();
    String matchDate = matchBean.getMatchDate();
    String matchDay = matchBean.getMatchDay();
    String matchTime = matchBean.getStartTime();
    String matchType = matchBean.getRoundType();
    String team1Collection = matchBean.getTeam1Collection();
    String team2Collection = matchBean.getTeam2Collection();
    String usedBidingPoints = matchBean.getUserBean().getUserBidPoints();
    String userPoints = matchBean.getUserBean().getUserPoints();
    String userID = matchBean.getUserBean().getUserID();
%>

<table border="1" style="width: 75%;" cellpadding="5" id="bidTable">

    <th colspan="2"><b>Match Details</b></th>

    <tr>
        <td style="width: 150px;"><b>Match Date</b></td>
        <td style="width: 200px;"><div id="matchDate"><%=matchDate%></div></td>
    </tr>
    <tr>
        <td><b>Match Day</b></td>
        <td><div id="matchDay"><%=matchDay%></div></td>
    </tr>
    <tr>
        <td><b>Match Time</b></td>
        <td><div id="matchTime"><%=matchTime%></div></td>
    </tr>
    <tr>
        <td><b>Team 1</b></td>
        <td><a href="popup_team_player_list.jsp?teamID=<%= team1ID%>" onClick="return popup(this, 'notes')"><%=team1Name%></a></td>
    </tr>
    <tr>
        <td><b>Team 2</b></td>
        <td><a href="popup_team_player_list.jsp?teamID=<%= team2ID%>" onClick="return popup(this, 'notes')"><%=team2Name%></a></td>
    </tr>
    <tr>
        <td><b><%=team1Name%> Collection</b></td>
        <td><a href="popup_team_collection.jsp?matchID=<%= matchID%>&teamID=<%= team1ID%>" onClick="return popup(this, 'notes')"><%=team1Collection%></a></td>
    </tr>
    <tr>
        <td><b><%=team2Name%> Collection</b></td>
        <td><a href="popup_team_collection.jsp?matchID=<%= matchID%>&teamID=<%= team2ID%>" onClick="return popup(this, 'notes')"><%=team2Collection%></a></td>
    </tr>
    <tr>
        <td><b>Your Biding Points</b></td>
        <td><a href="popup_user_collection.jsp?matchID=<%= matchID%>&userID=<%= userID%>" onClick="return popup(this, 'notes')"><%=usedBidingPoints%></a></td>
    </tr>
</table>
<br/><br/>
<form action="SaveUserBid" method="POST">
    <table border="1" style="width: 75%" id="bidTable">

        <th colspan="2"><b>Bid More</b>
            <input type="hidden" name="selectedMatchID" value="<%= matchID%>" id="selectedMatchID"/>
            <input type="hidden" name="matchType" value="<%=matchType%>" />
        </th>

        <tr>
            <td style="width: 200px;"><b>Your Balance</b></td>
            <td style="width: 200px;"><div id="userTotalPoint"><%=userPoints%></div></td>
        </tr>
        <tr>
            <td><b>Select Team</b></td>
            <td><div>
                    <input type="radio" id="team1Name" name="selectedTeam" value="<%=team1ID%>">
                    <label for="team1Name"><%=team1Name%></label><br>
                    <input type="radio" id="team2Name" name="selectedTeam" value="<%=team2ID%>">
                    <label for="team2Name"><%=team2Name%></label><br>
                </div></td>
        </tr>
        <tr>
            <td><b>Bid Points</b></td>
            <td><div><input type="text" name="bidPoints" value="" class="login_text_box"/></div></td>
        </tr>
    </table>                    
    <br/><br/>
    <table border="1" style="width: 75%" align="center" id="bidTable">

        <th><b>S.No</b></th>
        <th><b>Questions</b></th>
        <th><b>Answer</b></th>

        <%
            ArrayList<QuestionBean> questionList = (ArrayList<QuestionBean>) request.getAttribute("questionList");
            Iterator<QuestionBean> questionsIterator = questionList.iterator();
            int rowCounter = 0;
            while (questionsIterator.hasNext()) {
                rowCounter++;
                QuestionBean question = questionsIterator.next();
                String question_ID = question.getQuestionID();
                String guess_question = question.getUploadQuestion();
                guess_question = guess_question.replaceAll("(?i)team( )*1", team1Name);
                guess_question = guess_question.replaceAll("(?i)team( )*2", team2Name);
                String userAnswer = question.getUserAnswer();
                if (userAnswer == null || userAnswer.equalsIgnoreCase("NO ANSWER")) {
                    userAnswer = "";
                }
                if (rowCounter == 1) {
        %>
        <input type="hidden" name="matchID" value="<%= matchID%>"/>
        <%}%>
        <tr>
            <td><%= rowCounter%></td>
            <td><%= guess_question%></td>
            <td>
                <input type="text" id="A<%= question_ID%>" value="<%= userAnswer%>" name="QID<%= question_ID%>" class="login_text_box"/>
                <input type="hidden" id="P<%= question_ID%>" name="PQID<%= question_ID%>" value="<%= userAnswer%>"/>
                <input type="hidden" name="quesID" value="<%= question_ID%>"/></td>
        </tr>
        <%}
            if (rowCounter == 0) {%>
        <tr align="center">
            <td colspan="3">No questions are there for today's Match</td>
        </tr>
        <%} else {%>
        <tr>
            <td colspan="3">
                <div style="float: left; color: black; font-size: 12px; background-color: yellow;"> 
                    <b>
                        *** For answers, please make sure to answer in digits for all the Qs expect the questions where we have to answer a player name (0 should be there instead of NIL, ZERO, NONE etc.) <br/>
                        *** For player names, please use full names and copy the names from the bidding page
                    </b>
                </div>
            </td>
        </tr>
        <%}%>
    </table>
    <br/><br/>
    <div><input type="submit" value="Submit" class=" button login_button"/></div>
</form>

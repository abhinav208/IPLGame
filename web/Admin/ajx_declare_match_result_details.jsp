<%@page import="java.util.TreeSet"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="IPL_BEANS.MatchCollection"%>
<%@page import="IPL_LOGIC.QuestionLogic"%>
<%@page import="IPL_BEANS.QuestionBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.MatchBean"%>
<%@page import="java.util.ArrayList"%>
<br/><br/>
<form action="DeclareMatchResult" method="POST">
    <table border="1" style="width: 75%;" cellpadding="5" id="adminTable">
        <tr align="center">
            <th colspan="2"><b>Match Details</b></th>
        </tr>
        <%
            ArrayList<MatchBean> matchList = (ArrayList<MatchBean>) request.getAttribute("matchList");
            Iterator<MatchBean> matchListIterator = matchList.iterator();
            String matchID = "";
            while (matchListIterator.hasNext()) {
                MatchBean matchBean = matchListIterator.next();
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
                matchID = matchBean.getMatchID().trim();
        %>
        <tr>
            <td><b>Team1</b></td>
            <td>
                <input type="hidden" name="team1ID" value="<%= team1ID%>"/>
                <input type="hidden" name="team1Name" value="<%= team1Name%>"/>
                <%= team1Name%></td>
        </tr>
        <tr>
            <td><b>Team2</b></td>
            <td><input type="hidden" name="team2ID" value="<%= team1ID%>"/>
                <input type="hidden" name="team2Name" value="<%= team2Name%>"/>
                <%= team2Name%></td>
        </tr>
        <tr>
            <td><b>Date</b></td>
            <td><%= matchDate%></td>
        </tr>
        <tr>
            <td><b>Day</b></td>
            <td><%= matchDay%></td>
        </tr>
        <tr>
            <td><b>Time</b></td>
            <td><%= matchTime%></td>
        </tr>
        <tr>
            <td><b>Team 1 Collection</b></td>
            <td><%= team1Collection%>
                <input type="hidden" name="team1Collection" value="<%= team1Collection%>"
            </td>
        </tr>
        <tr>
            <td><b>Team 2 Collection</b></td>
            <td><%= team2Collection%>
                <input type="hidden" name="team2Collection" value="<%= team2Collection%>"</td>
        </tr>
        <tr>
            <td><b>Winner</b></td>
            <td><div>
                    <input type="radio" name="winnerTeam" value="<%= team1ID%>"/> <%= team1Name%> <br/>
                    <input type="radio" name="winnerTeam" value="<%= team2ID%>"/> <%= team2Name%> <br/> 
                    <input type="radio" name="winnerTeam" value="draw"/> Draw
                    <input type="hidden" name="matchID" value="<%= matchID%>"/>
                    <input type="hidden" name="matchType" value="<%= matchType%>"/>
                </div></td>
        </tr>
        <%}%>
    </table>
    <BR/><BR/>
    <table border="1" style="width: 75%;" cellpadding="5" id="adminTable">
        <tr>
            <th><b>S.No</b></th>
            <th><b>Question</b></th>
            <th><b>Answer</b></th>
        </tr>
        <%
            ArrayList<QuestionBean> questionList = (ArrayList<QuestionBean>) request.getAttribute("questionList");
            Iterator<QuestionBean> quesListIterator = questionList.iterator();
            int rowCounter = 0;
            while (quesListIterator.hasNext()) {
                rowCounter++;
                QuestionBean question = quesListIterator.next();
                String upldQues = question.getUploadQuestion();
                String quesID = question.getQuestionID();
        %>
        <tr>
            <td><%= rowCounter%></td>
            <td><%= upldQues%></td>
            <td>
                <input type="hidden" name="quesID" value="<%= quesID%>"/>
                <%
                    QuestionBean answerBean = new QuestionBean();
                    answerBean.setMatchID(matchID);
                    QuestionLogic quesLogic = new QuestionLogic(question);
                    quesLogic.openConnection();
                    ArrayList<QuestionBean> match_answer_buff = quesLogic.prepareAllAttemptedUserAnswers();
                    quesLogic.closeConnection();
                    Iterator<QuestionBean> ansListIterator = match_answer_buff.iterator();
                    LinkedHashSet<String> answerSet = new LinkedHashSet();
                    while (ansListIterator.hasNext()) {
                        QuestionBean answer = ansListIterator.next();
                        if (answer.getQuestionID().equalsIgnoreCase(quesID)) {
                            answerSet.add(answer.getUserAnswer());
                        }
                    }
                    TreeSet<String> answerSetTree = new TreeSet<String>(answerSet);
                    Iterator<String> ansIterator = answerSetTree.iterator();
                %>
                <div class="multiselect">
                    <div id="checkboxes<%=rowCounter%>" class="checkboxes">
                        <input type="text" name="QID<%= quesID%>"/>
                        <%
                            while (ansIterator.hasNext()) {
                                String answerValue = ansIterator.next();
                        %>
                        <label><input type="checkbox" value="<%=answerValue%>" name="QID<%= quesID%>"><%=answerValue%></label>
                            <%}%>
                    </div>
                </div>
            </td>
        </tr>
        <%}%>
    </table>
    <input class="button login_button" type="submit" value="Declare"/></td>
</form>
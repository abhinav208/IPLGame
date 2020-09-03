<%@page import="java.util.ListIterator"%>
<%@page import="IPL_BEANS.DeclareMatchResult"%>
<%@page import="IPL_LOGIC.MatchCollectionLogic"%>
<%@page import="IPL_BEANS.MatchCollection"%>
<%@page import="IPL_LOGIC.QuestionLogic"%>
<%@page import="IPL_BEANS.UserBean"%>
<%@page import="IPL_BEANS.QuestionBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.MatchBean"%>
<%@page import="java.util.ArrayList"%>

<table align="center" border="1" >
    <tr>
        <td>
            <table border="1"  id="bidTable2">
                <th colspan="2"><b>Match Details</b></th>
                <%
                    String win_team_id = "0";
                    ArrayList<MatchBean> matchList = (ArrayList<MatchBean>) request.getAttribute("matchList");
                    Iterator<MatchBean> matchListIterator = matchList.iterator();
                    String team1Name = "", team2Name = "", MatchID = "", team1ID = "", team2ID = "";
                    while (matchListIterator.hasNext()) {
                        MatchBean matchBean = matchListIterator.next();
                        MatchID = matchBean.getMatchID();
                        team1Name = matchBean.getTeam1Name();
                        team1ID = matchBean.getTeam1ID();
                        team2Name = matchBean.getTeam2Name();
                        team2ID = matchBean.getTeam2ID();
                        String matchDate = matchBean.getMatchDate();
                        String matchDay = matchBean.getMatchDay();
                        String matchTime = matchBean.getStartTime();
                        String team1Collection = matchBean.getTeam1Collection();
                        String team2Collection = matchBean.getTeam2Collection();
                        String matchID = matchBean.getMatchID().trim();
                        win_team_id = matchBean.getResultBean().getTeamID();
                %>
                <tr>
                    <td><b>Team1</b></td>
                    <td>
                        <input type="hidden" name="team1ID" value="<%= team1ID%>"/>
                        <%= team1Name%></td>
                </tr>
                <tr>
                    <td><b>Team2</b></td>
                    <td><input type="hidden" name="team2ID" value="<%= team1ID%>"/>
                        <%= team2Name%></td>
                </tr>
                <tr>
                    <td><b>Date</b></td>
                    <td><%= matchDate + " (" + matchDay + ")"%> </td>
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
                <%}%>
            </table>
        </td>
        <td valign="top">
            <table border="1" id="bidTable2">
                <tr>
                    <th><b>S.No</b></th>
                    <th><b>Question</b></th>
                </tr>
                <%
                    ArrayList<QuestionBean> questionList = (ArrayList<QuestionBean>) request.getAttribute("questionList");
                    Iterator<QuestionBean> quesListIterator = questionList.iterator();
                    String[] AnswerArray = new String[4];
                    int rowCounter = 0;
                    String QuestionIDs = "";
                    while (quesListIterator.hasNext()) {
                        rowCounter++;
                        QuestionBean question = quesListIterator.next();
                        String upldQues = question.getUploadQuestion();
                        upldQues = upldQues.replaceAll("(?i)team( )*1", team1Name);
                        upldQues = upldQues.replaceAll("(?i)team( )*2", team2Name);
                        String quesID = question.getQuestionID();
                        String corrAns = question.getCorrectAnswer();
                        AnswerArray[rowCounter - 1] = corrAns;
                        QuestionIDs = QuestionIDs + "," + quesID;
                %>
                <tr>
                    <td><%= rowCounter%></td>
                    <td><%= upldQues%></td>
                </tr>

                <%}%>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <table border="1" cellpadding="3" cellspacing="0" id="bidTable">
                <%
                    String userId;
                    String userName = "";
                    String userEmail;
                    String totalPoint;
                    String userStatus;
                    String user_userName;
                    String user_team = "";
                    UserBean UserDetails = new UserBean();
                %>
                <tr>
                    <th><b>Team</b></th>
                    <th><b>Name</b></th>
                    <th><b><%= team1Name%></b></th>
                    <th><b><%= team2Name%></b></th>
                    <th><b>Total Gain</b></th>
                    <th><b>Question 1</b></th>
                    <th><b>Question 2</b></th>
                    <th><b>Question 3</b></th>
                    <th><b>Question 4</b></th>
                </tr>
                <tr>
                    <th ><input type="text" id="teamSearch" onkeyup="myFunction('bidTable',this.id,0)" style="width: 60px;" /></th>
                    <th ><input type="text" id="nameSearch" onkeyup="myFunction('bidTable',this.id,1)" style="width: 100px;"/></th>
                    <th ><input type="text" id="team1Search" onkeyup="myFunction('bidTable',this.id,2)" style="width: 60px;"/></th>
                    <th ><input type="text" id="team2Search" onkeyup="myFunction('bidTable',this.id,3)" style="width: 60px;"/></th>
                    <th ><input type="text" id="gainSearch" onkeyup="myFunction('bidTable',this.id,4)" style="width: 60px;"/></th>
                    <th ><input type="text" id="ques1Search" onkeyup="myFunction('bidTable',this.id,5)" style="width: 80px;"/></th>
                    <th ><input type="text" id="ques2Search" onkeyup="myFunction('bidTable',this.id,6)" style="width: 80px;"/></th>
                    <th ><input type="text" id="ques3Search" onkeyup="myFunction('bidTable',this.id,7)" style="width: 80px;"/></th>
                    <th ><input type="text" id="ques4Search" onkeyup="myFunction('bidTable',this.id,8)" style="width: 80px;"/></th>    
                </tr>
                <%
                    ArrayList<DeclareMatchResult> declare_list = (ArrayList<DeclareMatchResult>) request.getAttribute("declare_list");


                    ListIterator<DeclareMatchResult> declare_list_iterator = declare_list.listIterator();
                    while (declare_list_iterator.hasNext()) {
                        DeclareMatchResult declare_obj = declare_list_iterator.next();
                        user_team = declare_obj.getTeam_name();
                        userName = declare_obj.getUser_name();
                        String team1Point = declare_obj.getTeam1_collection();
                        String team2Point = declare_obj.getTeam2_collection();
                        String totalGain = declare_obj.getTotal_gain();
                        String declare_team1_id = declare_obj.getTeam1_id();
                        String declare_team2_id = declare_obj.getTeam2_id();
                        String QuesColor1 = "", QuesColor2 = "",
                                QuesColor3 = "", QuesColor4 = "";

                %>

                <tr>
                    <td><%= user_team%></td>
                    <td><%= userName%></td>

                    <%
                        if (team1ID.trim().equalsIgnoreCase(declare_team1_id.trim())
                                && Integer.parseInt(win_team_id) == Integer.parseInt(declare_team1_id)) {
                    %>
                    <td style="background: green;"><%= team1Point%></td>
                    <%} else if (team1ID.trim().equalsIgnoreCase(declare_team2_id.trim())
                            && Integer.parseInt(win_team_id) == Integer.parseInt(declare_team2_id)) {%>
                    <td style="background: green;"><%= team2Point%></td>
                    <%} else if (team1ID.trim().equalsIgnoreCase(declare_team1_id.trim())) {%>
                    <td style=""><%= team1Point%></td>
                    <%} else if (team1ID.trim().equalsIgnoreCase(declare_team2_id.trim())) {%>
                    <td style=""><%= team2Point%></td>
                    <%} else {%>
                    <td style="">0</td>
                    <%}%>





                    <%
                        if (team2ID.trim().equalsIgnoreCase(declare_team1_id.trim())
                                && Integer.parseInt(win_team_id) == Integer.parseInt(declare_team1_id)) {
                    %>
                    <td style="background: green;"><%= team1Point%></td>
                    <%} else if (team2ID.trim().equalsIgnoreCase(declare_team2_id.trim())
                            && Integer.parseInt(win_team_id) == Integer.parseInt(declare_team2_id)) {%>
                    <td style="background: green;"><%= team2Point%></td> 
                    <%} else if (team2ID.trim().equalsIgnoreCase(declare_team2_id.trim())) {%>
                    <td style=""><%= team2Point%></td>
                    <%} else if (team2ID.trim().equalsIgnoreCase(declare_team1_id.trim())) {%>
                    <td style=""><%= team1Point%></td>
                    <%} else {%>
                    <td style="">0</td>
                    <%}%>



                    <td><%= totalGain%></td>
                    <%

                        String answer_arr[] = declare_obj.getQues_ans_arr();
                        for (int i = 0; i < answer_arr.length; i++) {
                            String answer_combd = answer_arr[i];
                            String print_answer = "";
                            if (answer_combd != null) {

                                String answer_split[] = answer_combd.trim().split("\\|");
                                print_answer = answer_split[0];
                                if (answer_split[1].trim().equalsIgnoreCase("CORRECT")) {
                    %>
                    <td style="background-color: green;"><%= print_answer%></td>

                    <%} else {%>
                    <td><%= print_answer%></td>
                    <%}
                    } else {%>
                    <td><%= print_answer%></td>
                    <%}%>
                    <%}%>
                </tr>
                <%}%>
            </table>
        </td>
    </tr>
</table>
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.MatchBean;
import IPL_BEANS.MatchCollection;
import IPL_BEANS.MatchGenealFlagBean;
import IPL_BEANS.QuestionBean;
import IPL_BEANS.TeamBean;
import IPL_BEANS.TransactionBean;
import IPL_BEANS.UserBean;
import IPL_LOGIC.MatchCollectionLogic;
import IPL_LOGIC.MatchGenealFlagLogic;
import IPL_LOGIC.MatchLogic;
import IPL_LOGIC.QuestionLogic;
import IPL_LOGIC.TeamLogic;
import IPL_LOGIC.TransactionLogic;
import IPL_LOGIC.UserLogic;
import IPL_UTILITY.MailFunction;
import IPL_UTILITY.MyServerDateTime;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abhinav Kumar
 */
public class SaveUserBid extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        UserBean userBean = new UserBean();
        String teamID;
        try {
            userBean = (UserBean) session.getAttribute("userObject");
        } catch (NullPointerException npe) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("../logout.jsp");
            dispatcher.forward(request, response);
        }
        try {
            teamID = request.getParameter("selectedTeam").trim();
        } catch (NullPointerException npe) {
            teamID = "";
        }
        try {
            System.out.println("************************************************");
            System.out.println("SaveUserBid Called: USER_ID : " + userBean.getUserID());

            UserLogic userLogic = new UserLogic(userBean);
            userBean.setUserPoints(Integer.toString(userLogic.getUserPoints()));
            String matchID = request.getParameter("selectedMatchID").trim();
            String matchType = request.getParameter("matchType").trim();

            MatchBean MB = new MatchBean();
            MB.setMatchID(matchID);
            MatchLogic ML = new MatchLogic(MB);
            String matchStatus = ML.getMatchStatus();
            System.out.println(matchStatus);

            String displayMessage = "Noting to Save";
            String bidPoints = request.getParameter("bidPoints").trim();

            String quesIDs[] = request.getParameterValues("quesID");
            String userAnswer[] = new String[quesIDs.length];
            String preUserAnswer[] = new String[quesIDs.length];
            ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
            QuestionBean questionBean = null;
            boolean saveAnswer = false;
            for (int quesNum = 0; quesNum < quesIDs.length; quesNum++) {
                userAnswer[quesNum] = request.getParameter("QID" + quesIDs[quesNum]).trim();
                preUserAnswer[quesNum] = request.getParameter("PQID" + quesIDs[quesNum]).trim();
                if (!(userAnswer[quesNum].equals(preUserAnswer[quesNum]))) {
                    saveAnswer = true;
                }
            }

            boolean saveBid = true;
            if (teamID.trim().equals("") && bidPoints.trim().equals("")) {
                saveBid = false;
            }
            if (teamID.trim().equals("") && (!(bidPoints.trim().equals("")))) {
                saveBid = false;
                displayMessage = "Select Bidding team";
            }
            if ((!(teamID.trim().equals(""))) && bidPoints.trim().equals("")) {
                saveBid = false;
                displayMessage = "Enter Bid Amount";
            }


            if (matchStatus.equalsIgnoreCase("BID OPEN")) {

                // Save User Bid
                if (saveBid) {
                    MatchGenealFlagBean matchGenealFlagBean = new MatchGenealFlagBean();
                    matchGenealFlagBean.setMatchType(matchType);
                    MatchGenealFlagLogic matchGenealFlagLogic = new MatchGenealFlagLogic(matchGenealFlagBean);
                    matchGenealFlagBean = matchGenealFlagLogic.getMatchFlagDetails();
                    MatchCollection MC_temp = new MatchCollection();
                    MC_temp.setMatchID(matchID);
                    MC_temp.setUserID(userBean.getUserID());
                    MatchCollectionLogic MCL_temp = new MatchCollectionLogic(MC_temp);
                    MCL_temp.openConnection();
                    int totalBidUser = MCL_temp.getUserPerMatchCollection();
                    int lastBidteam = MCL_temp.getUserBidTeam();
                    MCL_temp.closeConnection();

                    userBean.setUserBidPoints(bidPoints);
                    int minimumAmount = Integer.parseInt(matchGenealFlagBean.getMatchMinBidAmount());
                    if (totalBidUser == 0) {
                        int percentageBidPoint = (int) Math.floor(Integer.parseInt(userBean.getUserPoints()) * (Integer.parseInt(matchGenealFlagBean.getMatchBidPercentage())) / 100);
                        if (percentageBidPoint > minimumAmount) {
                            minimumAmount = percentageBidPoint;
                        }
                    }
                    if ((Integer.parseInt(userBean.getUserPoints()) < minimumAmount) && (Integer.parseInt(userBean.getUserPoints()) > 0)) {
                        minimumAmount = Integer.parseInt(userBean.getUserPoints());
                    }
                    boolean validation_falg = false;
                    if (Integer.parseInt(bidPoints) > Integer.parseInt(userBean.getUserPoints())) {
                        validation_falg = true;
                        displayMessage = "You do not have , sufficient balance!";
                    }
                    if (Integer.parseInt(bidPoints) < minimumAmount && !validation_falg) {
                        validation_falg = true;
                        displayMessage = "Minimum bidding amount is " + minimumAmount;
                    }
                    if (matchGenealFlagBean.getMatchBidBothSide().equalsIgnoreCase("FALSE")) {
                        if ((lastBidteam != 0) && (lastBidteam != Integer.parseInt(teamID)) && !validation_falg) {
                            validation_falg = true;
                            displayMessage = "You can Bid on only one team";
                        }
                    }

                    if (!validation_falg) {
                        String trxTime = MyServerDateTime.getServerTime();
                        String trxDate = MyServerDateTime.getServerDate();

                        MatchCollection matchCollection = new MatchCollection();
                        matchCollection.setUserID(userBean.getUserID());
                        matchCollection.setAmount(bidPoints);
                        matchCollection.setBidDate(trxDate);
                        matchCollection.setBidTime(trxTime);
                        matchCollection.setBidTeam(teamID);
                        matchCollection.setMatchID(matchID);
                        matchCollection.setResultAmount("0");
                        matchCollection.setColStatus("BID OPEN");
                        MatchCollectionLogic matchCollectionLogic = new MatchCollectionLogic(matchCollection);

                        matchCollectionLogic.openConnection();
                        if (matchCollectionLogic.saveCollectionDetails()) {
                            TransactionBean trxBean = new TransactionBean();
                            trxBean.setUserID(userBean.getUserID());
                            trxBean.setTrxType("DEBIT");
                            trxBean.setTrxTitle("BIDING#M" + matchID);
                            trxBean.setTrxTime(trxTime);
                            trxBean.setTrxDate(trxDate);
                            trxBean.setTrxAmount(bidPoints);
                            trxBean.setOpeningBalance(userBean.getUserPoints());
                            trxBean.setClosingBalance(Integer.toString(Integer.parseInt(userBean.getUserPoints())
                                    - Integer.parseInt(bidPoints)));
                            TransactionLogic trxLogic = new TransactionLogic(trxBean);
                            if (trxLogic.saveUserTransaction()) {
                                String availableBalance = userBean.getUserPoints();
                                userBean.setUserPoints(trxBean.getClosingBalance());
                                userLogic = new UserLogic(userBean);
                                if (userLogic.updateUserPoints()) {
                                    displayMessage = "Bid is saved, we wish you good luck !!!";
                                    MailFunction mailfunction = new MailFunction();
                                    TeamBean teamBean = new TeamBean();
                                    teamBean.setTeamID(teamID);
                                    TeamLogic teamlog = new TeamLogic(teamBean);
                                    mailfunction.sendBIDMail(userBean.getUserEmail(), matchID, teamlog.returnTeamNameById(), trxBean.getTrxAmount(), trxBean.getOpeningBalance(), trxBean.getClosingBalance());
                                } else {
                                    userBean.setUserPoints(availableBalance);
                                    trxLogic = new TransactionLogic(trxBean);
                                    if (!trxLogic.deleteEXPUserTransaction()) {
                                        System.out.println("IPL_MANUAL_DATA_CORRECTION Required");
                                    }
                                    matchCollectionLogic = new MatchCollectionLogic(matchCollection);
                                    if (!matchCollectionLogic.deleteEXPCollection()) {

                                        System.out.println("IPL_MANUAL_DATA_CORRECTION Required");
                                    }

                                    displayMessage = "Due to some technical issue, Bid is not completed";
                                    System.out.println("USER ID : " + userBean.getUserID());
                                    System.out.println("BID_AMOUNT : " + userBean.getUserBidPoints());
                                    System.out.println("BID_TEAM : " + teamID);
                                    System.out.println("USER_BALANCE : " + userBean.getUserPoints());
                                    System.out.println("SERVER_DATE : " + trxDate);
                                    System.out.println("SERVER_TIME : " + trxTime);
                                }
                            } else {
                                matchCollectionLogic = new MatchCollectionLogic(matchCollection);
                                if (!matchCollectionLogic.deleteEXPCollection()) {
                                    System.out.println("IPL_MANUAL_DATA_CORRECTION Required");
                                }
                                displayMessage = "Due to some technical issue, Bid is not completed";
                                System.out.println("USER ID : " + userBean.getUserID());
                                System.out.println("BID_AMOUNT : " + userBean.getUserBidPoints());
                                System.out.println("BID_TEAM : " + teamID);
                                System.out.println("USER_BALANCE : " + userBean.getUserPoints());
                                System.out.println("SERVER_DATE : " + trxDate);
                                System.out.println("SERVER_TIME : " + trxTime);
                            }
                        } else {
                            displayMessage = "Due to some technical issue, Bid is not completed";
                            System.out.println("USER ID : " + userBean.getUserID());
                            System.out.println("BID_AMOUNT : " + userBean.getUserBidPoints());
                            System.out.println("BID_TEAM : " + teamID);
                            System.out.println("USER_BALANCE : " + userBean.getUserPoints());
                            System.out.println("SERVER_DATE : " + trxDate);
                            System.out.println("SERVER_TIME : " + trxTime);
                        }
                    }

                    System.out.println("BID STATUS: USER_ID = " + userBean.getUserID() + "\n"
                            + " Display Message : " + displayMessage);
                    System.out.println("************************************************");
                }

                // Save Match Question Answer
                if (saveAnswer) {
                    for (int i = 0; i < userAnswer.length; i++) {
                        questionBean = new QuestionBean();
                        questionBean.setUserBean(userBean);
                        questionBean.setMatchID(matchID);
                        questionBean.setQuestionID(quesIDs[i]);
                        String userAns = userAnswer[i];
                        if (userAns == null || userAns.equalsIgnoreCase("") || userAns.isEmpty() || userAns.length() == 0) {
                            questionBean.setUserAnswer("NO ANSWER");
                        } else {
                            questionBean.setUserAnswer(userAnswer[i]);
                        }
                        questionBean.setAnswerStatus("ATTEMPTED");
                        questionList.add(questionBean);
                    }
                    questionBean.setQuestionList(questionList);
                    QuestionLogic questionLogic = new QuestionLogic(questionBean);
                    if (displayMessage.equals("Noting to Save")) {
                        displayMessage = questionLogic.saveUserMatchAnswer();
                    } else {
                        displayMessage = displayMessage +"<BR/>"+ questionLogic.saveUserMatchAnswer();
                    }
                }
            } else {
                displayMessage = "Match Bid is closed";
            }
            session.setAttribute("displayMessage", displayMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("player_bid_page.jsp");
            dispatcher.forward(request, response);

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

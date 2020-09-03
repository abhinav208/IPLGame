/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.MatchGenealFlagBean;
import IPL_BEANS.QuestionBean;
import IPL_LOGIC.MatchGenealFlagLogic;
import IPL_LOGIC.QuestionLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhinav Kumar
 */
public class DeclareQuestionResult extends HttpServlet {

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
        try {
            String quesIDs[] = request.getParameterValues("quesID");
            String matchID = request.getParameter("matchID").trim();
            String matchType = request.getParameter("matchType").trim();
            String winnerTeamID = request.getParameter("winnerTeam").trim();
            String displayMessage = (String) request.getAttribute("displayMessage");
            ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();


            MatchGenealFlagBean matchGenealFlagBean = new MatchGenealFlagBean();
            matchGenealFlagBean.setMatchType(matchType);
            MatchGenealFlagLogic matchGenealFlagLogic = new MatchGenealFlagLogic(matchGenealFlagBean);
            matchGenealFlagBean = matchGenealFlagLogic.getMatchFlagDetails();
            String correctAnswerAmount = matchGenealFlagBean.getMatchQuestionAmount();

            String adminAnswers[] = new String[quesIDs.length];
            for (int quesNum = 0; quesNum < quesIDs.length; quesNum++) {
                if (winnerTeamID.equalsIgnoreCase("draw")) {
                    adminAnswers[quesNum] = "";
                } else {
                    String[] answerList = request.getParameterValues("QID" + quesIDs[quesNum]);
                    StringBuilder builder = new StringBuilder();
                    for (String s : answerList) {
                        builder.append("," + s);
                    }
                    adminAnswers[quesNum] = builder.toString();
                    adminAnswers[quesNum] = adminAnswers[quesNum].replaceFirst(",", "");
                }
                QuestionBean questionBean = new QuestionBean();
                questionBean.setQuestionID(quesIDs[quesNum]);
                questionBean.setMatchID(matchID);
                questionBean.setCorrectAnswer(adminAnswers[quesNum]);
                questionList.add(questionBean);
            }

            QuestionBean questionBean = new QuestionBean();
            questionBean.setMatchID(matchID);
            questionBean.setQuestionList(questionList);
            QuestionLogic quesLogic = new QuestionLogic(questionBean);
            quesLogic.openConnection();
            if (quesLogic.updateCorrectAnswers()) {
                if (quesLogic.validateUserAnswers(matchID, correctAnswerAmount)) {
                    displayMessage = displayMessage + "<BR/>User asnwers validated";
                } else {
                    displayMessage = displayMessage + "<BR/>User asnwers not validated";
                }
            } else {
                displayMessage = displayMessage + "<BR/>Answers Not updated successfully.";
            }
            quesLogic.closeConnection();
            request.setAttribute("displayMessage", displayMessage);
            request.setAttribute("matchGenealFlagBean", matchGenealFlagBean);
            RequestDispatcher dispatcher = request.getRequestDispatcher("NBUTransactions");
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

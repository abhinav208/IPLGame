/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.MatchBean;
import IPL_BEANS.MatchCollection;
import IPL_BEANS.ResultBean;
import IPL_LOGIC.MatchCollectionLogic;
import IPL_LOGIC.MatchLogic;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhinav Kumar
 */
public class DeclareMatchResult extends HttpServlet {

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
            String matchID = request.getParameter("matchID").trim();
            String winnerTeamID = request.getParameter("winnerTeam").trim();
            String team1Collection = request.getParameter("team1Collection").trim();
            String team2Collection = request.getParameter("team2Collection").trim();
            String team1ID = request.getParameter("team1ID").trim();
            String team2ID = request.getParameter("team2ID").trim();
            String team1Name = request.getParameter("team1Name").trim();
            String team2Name = request.getParameter("team2Name").trim();
            String winningComment = "";
            String displayMessage = "";
            if (winnerTeamID.trim().equals(team1ID.trim())) {
                winningComment = team1Name + " Won";
            } else if (winnerTeamID.trim().equals(team2ID.trim())) {
                winningComment = team2Name + " Won";
            } else {
                winningComment = "Match is Draw/Cancelled";
            }

            ResultBean resultBean = new ResultBean();
            resultBean.setMatchID(matchID);
            resultBean.setTeamID(winnerTeamID);
            resultBean.setTeam1Collection(team1Collection);
            resultBean.setTeam2Collection(team2Collection);
            resultBean.setWinningRemarks(winningComment);

            MatchBean matchBean = new MatchBean();
            matchBean.setResultBean(resultBean);
            matchBean.setTeam1Collection(team1Collection);
            matchBean.setTeam2Collection(team2Collection);
            matchBean.setMatchID(matchID);
            MatchLogic matchLogic = new MatchLogic(matchBean);

            boolean flag = false;
            if (winnerTeamID.equalsIgnoreCase("draw")) {
                flag = matchLogic.updateDrawMatchResult();
            } else {
                flag = matchLogic.updateMatchResult();
            }

            if (flag) {
                matchBean.setMatchStatus("RESULT DECLARED");
                displayMessage = matchLogic.updateMatchStatus();
                MatchCollection collectionBean = new MatchCollection();
                collectionBean.setMatchID(matchID);
                collectionBean.setWinTeamID(winnerTeamID);
                collectionBean.setTeam1Amount(team1Collection);
                collectionBean.setTeam2Amount(team2Collection);
                collectionBean.setTeam1ID(team1ID);
                collectionBean.setTeam2ID(team2ID);
                MatchCollectionLogic colLogic = new MatchCollectionLogic(collectionBean);
                if (winnerTeamID.equalsIgnoreCase("draw")) {
                    displayMessage = displayMessage + "<BR/>" + colLogic.updateDrawMatchCollectionForMatchDeclare();
                } else {
                    displayMessage = displayMessage + "<BR/>" + colLogic.updateMatchCollectionForMatchDeclare();
                }

            } else {
                displayMessage = "Match Result updation failed...,";
            }

            request.setAttribute("displayMessage", displayMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DeclareQuestionResult");
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

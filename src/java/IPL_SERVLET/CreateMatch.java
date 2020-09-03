/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.MatchBean;
import IPL_BEANS.ResultBean;
import IPL_BEANS.TeamBean;
import IPL_LOGIC.MatchLogic;
import IPL_LOGIC.TeamLogic;
import IPL_UTILITY.DateManipulation;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CreateMatch extends HttpServlet {

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
            String team1 = request.getParameter("teamNames1").trim();
            String team2 = request.getParameter("teamNames2").trim();
            String matchDate = request.getParameter("matchDate").trim();
            String[] parts = matchDate.split("-");
            String[] months = {"January","February","March","April","May","June","July","August","September","October","November",""};
            String completeDate =  parts[0].trim()+"-"+months[Integer.parseInt(parts[1].trim())-1]+"-"+parts[2].trim();
            String roundType = request.getParameter("roundType").trim();
            String startTime = request.getParameter("matchTime").trim();
            String matchDay = request.getParameter("matchDay").trim();
            MatchBean matchBean = new MatchBean();
            matchBean.setMatchDate(DateManipulation.convertInSQLFormat(completeDate));
            matchBean.setTeam1ID(team1);
            matchBean.setTeam2ID(team2);
            matchBean.setRoundType(roundType);
            matchBean.setStartTime(startTime);
            matchBean.setMatchDay(matchDay);
            matchBean.setMatchStatus("BID NOT OPEN");
            TeamBean teamBean = new TeamBean();
            teamBean.setTeamName("NOT DISCLOSED");
            TeamLogic teamLogic = new TeamLogic(teamBean);
            ResultBean resultBean = new ResultBean();
            resultBean.setTeamID(teamLogic.returnTeamIdByName());
            resultBean.setTeam1Collection("0");
            resultBean.setTeam2Collection("0");
            resultBean.setWinningRemarks("RESULT NOT DECLARED");
            matchBean.setResultBean(resultBean);
            MatchLogic matchLogic = new MatchLogic(matchBean);
            String displayMessage = matchLogic.saveMatchDetails();
            HttpSession session=request.getSession();  
            session.setAttribute("displayMessage", displayMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_create_match.jsp");
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_AJAX_SERVLET;

import IPL_BEANS.MatchBean;
import IPL_BEANS.QuestionBean;
import IPL_BEANS.UserBean;
import IPL_LOGIC.QuestionLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
public class GetOpenBidMatchDetails extends HttpServlet {

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
            String matchIDRequest = request.getParameter("matchID").trim();
            MatchBean matchBeanFinal = new MatchBean();
            HttpSession session = request.getSession();
            ArrayList<MatchBean> matchList = (ArrayList<MatchBean>) session.getAttribute("matchList");
            UserBean userBean = (UserBean) session.getAttribute("userObject");
            
            Iterator<MatchBean> itr = matchList.iterator();
            while (itr.hasNext()) {
                MatchBean matchBean = itr.next();
                String matchID = matchBean.getMatchID();
                if (matchID.equals(matchIDRequest)) {
                    matchBeanFinal = matchBean;
                }
            }
            
            QuestionBean question = new QuestionBean();
            question.setMatchID(matchIDRequest);
            question.setUserBean(userBean);
            QuestionLogic questionLogic = new QuestionLogic(question);
            questionLogic.openConnection();
            questionLogic.prepareMatchQuestionArrayList();
            questionLogic.closeConnection();
            ArrayList<QuestionBean> questionList = questionLogic.fillUserAnswersIfExists();
            
            request.setAttribute("questionList", questionList);
            request.setAttribute("matchDetail", matchBeanFinal);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ajx_open_match_details.jsp");
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.BumperQuestionBean;
import IPL_BEANS.UserBean;
import IPL_LOGIC.BumperQuestionLogic;
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
public class SaveBumperAnswers extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            UserBean userBean = new UserBean();
            try {
                userBean = (UserBean) session.getAttribute("userObject");
            } catch (NullPointerException npe) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("../logout.jsp");
                dispatcher.forward(request, response);
            }
            String quesIDs[] = request.getParameterValues("quesID");
            String userAnswer[] = new String[quesIDs.length];
            String attemptAnswers[] = new String[quesIDs.length];
            for (int quesNum = 0; quesNum < quesIDs.length; quesNum++) {
                userAnswer[quesNum] = request.getParameter("BQID" + quesIDs[quesNum]).trim();
                attemptAnswers[quesNum] = request.getParameter("ATTBQID" + quesIDs[quesNum]).trim();
            }
            String displayMessage;
            BumperQuestionBean bumperBean = new BumperQuestionBean();
            BumperQuestionLogic bumperLogic = new BumperQuestionLogic(bumperBean);

            if (bumperLogic.saveUserAnswers(quesIDs, userAnswer, attemptAnswers, userBean)) {
                displayMessage = "Answers Saved Successfully, we wish you good luck !!!";
            } else {
                displayMessage = "Due to some technical issue, answers cannot be saved.";
            }
            session.setAttribute("displayMessage", displayMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("player_bumper_question.jsp");
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

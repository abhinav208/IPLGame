/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_AJAX_SERVLET;

import IPL_BEANS.BugBean;
import IPL_BEANS.UserBean;
import IPL_LOGIC.BugLogic;
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
public class GetAllUserBugs extends HttpServlet {

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
            BugBean bb = new BugBean();
            bb.setUserBean(userBean);
            BugLogic bl = new BugLogic(bb);
            ArrayList<BugBean> return_BugArray = bl.getAllUserBugDetails();
            request.setAttribute("return_BugArray", return_BugArray);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ajx_user_all_bugs_list.jsp");
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

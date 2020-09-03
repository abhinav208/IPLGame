/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.UserBean;
import IPL_DAO.GeneralFlagDao;
import IPL_LOGIC.UserLogic;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abhinav
 */
public class UserRegistration extends HttpServlet {

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
            String fullName = request.getParameter("fullName").trim();
            String userName = request.getParameter("uName").trim();
            String userEmail = request.getParameter("email").trim();
            String userTeam = request.getParameter("userTeam").trim();
            String displayMessage = "";
            UserBean userBean = new UserBean();
            userBean.setUserFullname(fullName.toUpperCase());
            userBean.setUserName(userName);
            userBean.setUserEmail(userEmail.trim().toLowerCase());
            userBean.setUserStatus("APPLIED");
            userBean.setUserType("PLAYER");
            userBean.setUserPoints("1000");
            userBean.setUserTeam(userTeam);
            GeneralFlagDao rd = new GeneralFlagDao();
            boolean registrationCloseFlag = rd.getRegistrationStatus();
            if (registrationCloseFlag) {
                displayMessage = "Registration Closed";
            } else {
                UserLogic userLogic = new UserLogic(userBean);
                displayMessage = userLogic.insertNewUsersDetails();
            }
            HttpSession session = request.getSession();
            session.setAttribute("displayMessage", displayMessage);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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

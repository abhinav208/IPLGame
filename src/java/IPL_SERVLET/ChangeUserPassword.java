/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.UserBean;
import IPL_LOGIC.UserLogic;
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
public class ChangeUserPassword extends HttpServlet {

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
            String userID = request.getParameter("userID").trim();
            String userNewPass = request.getParameter("newPass").trim();

            UserBean userBean = new UserBean();
            userBean.setUserID(userID);
            userBean.setUserPassword(userNewPass);

            UserLogic userLogic = new UserLogic(userBean);
            String displayMessage = "";
            HttpSession session = request.getSession();
            if (userLogic.updateUserPassword()) {
                displayMessage = "Your Password updated successfully, Please login again.";
                session.setAttribute("displayMessage", displayMessage);
                session.removeAttribute("userObject");
                session.removeAttribute("cap654Object");
                session.removeAttribute("adm325Object");
                response.sendRedirect("../index.jsp");
            } else {
                displayMessage = "Due to some technical issue, Password cannot be changed.";
                session.setAttribute("displayMessage", displayMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("player_password_change.jsp");
                dispatcher.forward(request, response);
            }


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

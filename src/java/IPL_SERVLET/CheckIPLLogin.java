/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.UserBean;
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
 * @author abhin
 */
public class CheckIPLLogin extends HttpServlet {

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
            String userName = request.getParameter("userName").trim();
            String userPass = request.getParameter("userPass").trim();
            UserBean userBean = new UserBean();
            userBean.setUserName(userName);
            userBean.setUserPassword(userPass);

            UserLogic userLogic = new UserLogic(userBean);
            String displayMessage = "Invalid UserName or Password.";
            boolean flag = userLogic.checkUserLogin();
            HttpSession session = request.getSession(true);
            session.removeAttribute("adm325Object");
            session.removeAttribute("cap654Object");
            session.removeAttribute("userObject");
            session.removeAttribute("graphDataList");
            if (flag) {
                if (userBean.getUserType().equalsIgnoreCase("ADMIN")) {
                    session.setAttribute("adm325Object", userBean);
                    response.sendRedirect("./Admin/admin_home.jsp");
                } else if (userBean.getUserType().equalsIgnoreCase("PLAYER")) {
                    session.setAttribute("userObject", userBean);
                    response.sendRedirect("./IPL_Players/player_bid_page.jsp");
                } else if (userBean.getUserType().equalsIgnoreCase("CAPTAIN")) {
                    session.setAttribute("userObject", userBean);
                    session.setAttribute("cap654Object", userBean);
                    response.sendRedirect("./IPL_Players/player_bid_page.jsp");
                }
            } else {
                session.setAttribute("displayMessage", displayMessage);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
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

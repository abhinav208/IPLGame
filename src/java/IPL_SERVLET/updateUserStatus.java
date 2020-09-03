/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_SERVLET;

import IPL_BEANS.UserBean;
import IPL_LOGIC.UserLogic;
import IPL_UTILITY.MailFunction;
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
public class updateUserStatus extends HttpServlet {

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

        String updateStatus = request.getParameter("statusButton").trim();
        String originPage = request.getParameter("originPage").trim();
        String[] userIDs = request.getParameterValues("userIDs");
        System.out.println(userIDs.length);

        String userID, displayMessage = "", userStatus;
        MailFunction mailfunction = new MailFunction();
        boolean check_flag;
        for (int i = 0; i < userIDs.length; i++) {
            userID = userIDs[i].trim();


            try {
                UserBean userBean = new UserBean();
                userBean.setUserID(userID);
                UserLogic userLogic = new UserLogic(userBean);
                userBean = userLogic.getUserDetail();
                userStatus = userBean.getUserStatus();
                if (userStatus.equalsIgnoreCase(updateStatus)) {
                    displayMessage = displayMessage + "<BR/>" + userBean.getUserName() + " Status is already set as " + updateStatus;
                } else {
                    userBean.setUserStatus(updateStatus);
                    userLogic = new UserLogic(userBean);
                    check_flag = userLogic.updateUserStatus();
                    if (check_flag == true) {
                        displayMessage = displayMessage + "<BR/>" + userBean.getUserName() + " Status is updated as " + updateStatus;
                        /////////// Mail Function //////////////
                        if (updateStatus.equalsIgnoreCase("ACTIVE")) {
                            mailfunction.sendPasswordMail(userBean);
                        } else {
                            mailfunction.sendMail("", userBean.getUserEmail(), "IPL Bid Hunt Registration", "Your User id " + userBean.getUserName() + " is In-Active");
                        }
                    } else {
                        displayMessage = displayMessage + "<BR/>" + userBean.getUserName() + " Status failed to update as " + updateStatus;
                    }
                }

            } finally {
                //out.close();
            }

        }
        System.out.println(displayMessage);
        HttpSession session = request.getSession();
        session.setAttribute("displayMessage", displayMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher(originPage);
        dispatcher.forward(request, response);
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

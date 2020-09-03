/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_AJAX_SERVLET;

import IPL_BEANS.DeclareMatchResult;
import IPL_BEANS.MatchBean;
import IPL_BEANS.MatchCollection;
import IPL_BEANS.QuestionBean;
import IPL_BEANS.UserBean;
import IPL_DAO.GeneralFlagDao;
import IPL_LOGIC.MatchCollectionLogic;
import IPL_LOGIC.MatchLogic;
import IPL_LOGIC.QuestionLogic;
import IPL_LOGIC.UserLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhinav Kumar
 */
public class GetDeclaredMatchResult extends HttpServlet {

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
    public ArrayList<DeclareMatchResult> prepareUserViewPage(ArrayList match_answer_buff,
            ArrayList match_colection_buff, ArrayList AllUserList, ArrayList questionList) {
        ArrayList<DeclareMatchResult> declare_list = new ArrayList<DeclareMatchResult>();
        ListIterator<UserBean> user_list_iterator = AllUserList.listIterator();
        while (user_list_iterator.hasNext()) {
            UserBean user_obj = user_list_iterator.next();
            DeclareMatchResult declare_obj = new DeclareMatchResult();
            declare_obj.setUser_name(user_obj.getUserFullname().toUpperCase());

            declare_obj.setTeam_name(user_obj.getUserTeam());

            int team_col[] = {0, 0};
            int team_id[] = {0, 0};
            int total_gain = 0;
            ListIterator<MatchCollection> match_col_buff_itr = match_colection_buff.listIterator();
            while (match_col_buff_itr.hasNext()) {
                MatchCollection match_col_obj = match_col_buff_itr.next();
                if (match_col_obj.getUserID().trim().equalsIgnoreCase(user_obj.getUserID().trim())) {
                    if (Integer.parseInt(match_col_obj.getBidTeam()) == team_id[0] || team_id[0] == 0) {
                        team_col[0] += Integer.parseInt(match_col_obj.getAmount());
                        team_id[0] = Integer.parseInt(match_col_obj.getBidTeam().trim());
                    } else if (Integer.parseInt(match_col_obj.getBidTeam()) == team_id[1] || team_id[1] == 0) {
                        team_col[1] += Integer.parseInt(match_col_obj.getAmount());
                        team_id[1] = Integer.parseInt(match_col_obj.getBidTeam().trim());
                    }

                    if (Integer.parseInt(match_col_obj.getResultAmount().trim()) > 0) {
                        total_gain += Integer.parseInt(match_col_obj.getResultAmount().trim());
                    }
                }
            }
            declare_obj.setTeam1_id(Integer.toString(team_id[0]));
            declare_obj.setTeam2_id(Integer.toString(team_id[1]));
            declare_obj.setTeam1_collection(Integer.toString(team_col[0]));
            declare_obj.setTeam2_collection(Integer.toString(team_col[1]));
            declare_obj.setTotal_gain(Integer.toString(total_gain));

            // code for answer
            ListIterator<QuestionBean> ques_list_iterator = questionList.listIterator();
            int index = -1;
            String user_answer[] = new String[4];
            while (ques_list_iterator.hasNext()) {
                index++;
                QuestionBean quesBean = ques_list_iterator.next();
                ListIterator<QuestionBean> match_ques_ans_itr = match_answer_buff.listIterator();
                while (match_ques_ans_itr.hasNext()) {
                    QuestionBean ques_bean = match_ques_ans_itr.next();

                    if (ques_bean.getUserBean().getUserID().trim().
                            equalsIgnoreCase(user_obj.getUserID().trim())
                            && quesBean.getQuestionID().trim().
                            equalsIgnoreCase(ques_bean.getQuestionID().trim())) {
                        String correct_answer_comb = quesBean.getCorrectAnswer();
                        // System.out.println("Correct Answer : " + correct_answer_comb);
                        String split_answer[] = correct_answer_comb.split(",");
                        boolean ans_flag = false;
                        for (int i = 0; i < split_answer.length; i++) {
                            String correct_answer = split_answer[i].trim();
                            if (correct_answer.trim().equalsIgnoreCase(ques_bean.getUserAnswer().trim())) {
                                ans_flag = true;
                            }
                        }
                        if (ans_flag) {
                            user_answer[index] = ques_bean.getUserAnswer().trim() + "|CORRECT";
                        } else {
                            user_answer[index] = ques_bean.getUserAnswer().trim() + "|WRONG";
                        }
                    }
                }

            }
            declare_obj.setQues_ans_arr(user_answer);

            declare_list.add(declare_obj);
        }
        return declare_list;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String matchID = request.getParameter("matchID").trim();
            String userTeam = request.getParameter("userTeam").trim();
            MatchBean matchBean = new MatchBean();
            matchBean.setMatchID(matchID);
            MatchLogic matchLogic = new MatchLogic(matchBean);
            ArrayList<MatchBean> matchList = matchLogic.getDelcaredMatchDetails();
            QuestionBean question = new QuestionBean();
            question.setMatchID(matchID);
            QuestionLogic quesLogic = new QuestionLogic(question);
            quesLogic.openConnection();
            ArrayList<QuestionBean> questionList = quesLogic.prepareMatchQuestionArrayList();
            MatchCollection collection_bean = new MatchCollection();
            ArrayList<QuestionBean> match_answer_buff = quesLogic.prepareAllAttemptedUserAnswers();
            quesLogic.closeConnection();

            collection_bean.setMatchID(question.getMatchID());
            MatchCollectionLogic collection_logic = new MatchCollectionLogic(collection_bean);
            ArrayList<MatchCollection> match_colection_buff = collection_logic.getTotalMatchCollection();

            UserBean userbean = new UserBean();
            userbean.setUserTeam(userTeam);
            UserLogic userLogic = new UserLogic(userbean);
            GeneralFlagDao gd = new GeneralFlagDao();
            ArrayList<UserBean> AllUserList;
            if (gd.getViewOnlyTeamStatus()) {
                AllUserList = userLogic.getAllUserTeamDetails();
            } else {
                AllUserList = userLogic.getAllUserDetails();
            }

            ArrayList<DeclareMatchResult> declare_list = prepareUserViewPage(match_answer_buff, match_colection_buff,
                    AllUserList, questionList);
            request.setAttribute("matchList", matchList);
            request.setAttribute("questionList", questionList);
            request.setAttribute("userList", AllUserList);
            request.setAttribute("declare_list", declare_list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ajx_declared_match_details.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            System.out.println(ex);
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

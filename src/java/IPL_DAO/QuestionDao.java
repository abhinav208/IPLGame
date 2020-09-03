/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.QuestionBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class QuestionDao {

    public Statement stmt;
    public ResultSet result;
    public Connection con;
    public QuestionBean questionBean;

    public QuestionDao() {
    }

    public QuestionDao(QuestionBean questionBean) {
        this.questionBean = questionBean;
    }

    public boolean uploadMatchQuestions() {
        boolean check_flag = false;
        String query = "INSERT INTO GUESS_QUESTIONS(QUESTION) "
                + "VALUES('" + questionBean.getUploadQuestion().trim() + "')";
        try {
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:uploadMatchQuestions: Exception occured "
                    + "while uploading guess questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public ResultSet getQuestionList() {
        String query = "SELECT * FROM GUESS_QUESTIONS";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:getQuestionList_DAO: Exception occured "
                    + "while fetching question list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean assignMatchQuestions() {
        boolean check_flag = false;
        String query = "INSERT INTO MATCH_ASSIGN_QUESTIONS (MATCH_ID,QUES_ID,CORRECT_ANSWER) "
                + "VALUES(" + questionBean.getMatchID().trim()
                + "," + questionBean.getQuestionID().trim()
                + ",'" + questionBean.getCorrectAnswer().trim() + "')";
        try {
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:assignMatchQuestions: Exception occured "
                    + "while assigning match questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public ResultSet getAllUploadedQuestions() {
        String query = "SELECT * FROM GUESS_QUESTIONS";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:uploadMatchQuestions: Exception occured "
                    + "while uploading guess questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getMatchAssignedQuestions() {
        String query = "SELECT MAQ.*,GQ.QUESTION FROM MATCH_ASSIGN_QUESTIONS MAQ, "
                + "GUESS_QUESTIONS GQ WHERE "
                + "MATCH_ID = " + questionBean.getMatchID().trim()
                + " AND GQ.QUES_ID = MAQ.QUES_ID";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:getMatchAssignedQuestions: Exception occured "
                    + "while fetching match assigned questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getAllAttemptedQuestionForMatch() {
        String query = "SELECT * FROM MATCH_USER_ANSWERS WHERE MATCH_ID = " + questionBean.getMatchID().trim();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:getAllAttemptedQuestionForMatch: Exception occured "
                    + "while fetching all atempted user answers , Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet checkUserAnswerExists() {

        String query = "SELECT * FROM MATCH_USER_ANSWERS "
                + " WHERE MATCH_ID = " + questionBean.getMatchID().trim()
                + " AND QUES_ID = " + questionBean.getQuestionID().trim()
                + " AND USER_ID = " + questionBean.getUserBean().getUserID().trim();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:checkUserAnswerExists: Exception occured "
                    + "while checking existence of user answers , Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean insertUserMatchAnswer() {
        boolean check_flag = false;
        String query = "INSERT INTO MATCH_USER_ANSWERS(USER_ID,MATCH_ID,QUES_ID,USER_ANSWER,ANSWER_STATUS) "
                + "VALUES(" + questionBean.getUserBean().getUserID()
                + "," + questionBean.getMatchID().trim()
                + "," + questionBean.getQuestionID().trim()
                + ",'" + questionBean.getUserAnswer().trim().toUpperCase()
                + "','" + questionBean.getAnswerStatus().trim() + "')";
        try {
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:insertUserMatchAnswer: Exception occured "
                    + "while inserting user answer, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public boolean updateUserAnswer() {
        boolean check_flag = false;
        String query = "UPDATE MATCH_USER_ANSWERS "
                + "SET USER_ANSWER = '" + questionBean.getUserAnswer().trim().toUpperCase()
                + "',ANSWER_STATUS = '" + questionBean.getAnswerStatus()
                + "' WHERE MATCH_ID = " + questionBean.getMatchID().trim()
                + " AND QUES_ID = " + questionBean.getQuestionID().trim()
                + " AND USER_ID = " + questionBean.getUserBean().getUserID().trim();
        try {
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:updateUserAnswer: Exception occured "
                    + "while updating user answer, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public boolean updateCorrectAnswerForQuestion() {
        boolean check_flag = false;
        System.out.println(questionBean.getCorrectAnswer() + " " + questionBean.getMatchID() + " " + questionBean.getQuestionID());
        String query = "UPDATE MATCH_ASSIGN_QUESTIONS SET CORRECT_ANSWER = '" + questionBean.getCorrectAnswer() + "' "
                + "WHERE MATCH_ID = " + questionBean.getMatchID().trim()
                + " AND QUES_ID = " + questionBean.getQuestionID().trim();
        try {
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:updateCorrectAnswerForQuestion: Exception occured "
                    + "while updating correct answer, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public boolean updateUserAsnwerStatus() {
        boolean check_flag = false;
        String query = "UPDATE MATCH_USER_ANSWERS SET ANSWER_STATUS = 'EVALUATED' "
                + "WHERE MATCH_ID = " + questionBean.getMatchID().trim()
                + " AND QUES_ID = " + questionBean.getQuestionID().trim()
                + " AND USER_ID = " + questionBean.getUserBean().getUserID();
        try {
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:updateUserAsnwerStatus: Exception occured "
                    + "while updating user answer status, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public ResultSet getCorrectAnswerForQuestion() {
        String query = "SELECT * FROM MATCH_ASSIGN_QUESTIONS WHERE MATCH_ID = " + questionBean.getMatchID().trim()
                + " AND QUES_ID = " + questionBean.getQuestionID();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("QuestionDao:getCorrectAnswerForQuestion: Exception occured "
                    + "while fetching correct answer , Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }
}

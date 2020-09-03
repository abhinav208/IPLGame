/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.BumperQuestionBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class BumperQuestionDao {

    BumperQuestionBean bumper;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public BumperQuestionDao(BumperQuestionBean bumper) {
        this.bumper = bumper;
    }

    public BumperQuestionDao() {
    }

    public boolean uploadBumperQuestions() {
        boolean check_flag = false;
        String query = "INSERT INTO BUMPER_QUESTION(BUMPER_QUESTION,BUMPER_ANSWER,QUESTION_STATUS) "
                + "VALUES('" + bumper.getBumperQuestion().trim() + "'"
                + ",'" + bumper.getBumberAnswer() + "'"
                + ",'" + bumper.getQuestionStatus() + "')";
        System.out.println(query);
        try {
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionDao:uploadBumperQuestions: Exception occured "
                    + "while uploading bumper questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public ResultSet getAllBumperQuestions() {
        String query = "SELECT * FROM bumper_question";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionDao:getAllBumperQuestions: Exception occured "
                    + "while featching Bumper questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getActiveBumperQuestions() {
        String query = "SELECT * FROM bumper_question where QUESTION_STATUS = 'ACTIVE'";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionDao:getAllBumperQuestions: Exception occured "
                    + "while featching Bumper questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet checkUserAnswered() {
        try {
            String query = "SELECT * FROM BUMPER_USER_ANSWER WHERE USER_ID = " + bumper.getUserBean().getUserID().trim();
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionDao:checkUserAnswered: Exception occured "
                    + "while checking user answered bumper questions, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean updateBumperAnswers() {
        boolean check_flag = false;
        try {
            String query = "UPDATE BUMPER_USER_ANSWER SET USER_ANSWER = '" + bumper.getUserAnswer().trim() + "', "
                    + "ANSWER_STATUS = '" + bumper.getAnswerStatus().trim() + "', "
                    + "ANSWER_DATE = '" + bumper.getAnswerDate().trim() + "', "
                    + "ANSWER_TIME = '" + bumper.getAnswerTime().trim() + "' "
                    + "WHERE USER_ID = " + bumper.getUserBean().getUserID().trim()
                    + " AND QUESTION_ID = '" + bumper.getQustionID().trim() + "'";
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionDao:updateBumperAnswers: Exception occured "
                    + "while updating Bumper asnwers, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public boolean insertBumperAnswer() {
        boolean check_flag = false;
        try {
            String query = "INSERT INTO BUMPER_USER_ANSWER(USER_ID,QUESTION_ID,USER_ANSWER,"
                    + "ANSWER_DATE,ANSWER_TIME,ANSWER_STATUS) VALUES(" + bumper.getUserBean().getUserID().trim()
                    + ",'" + bumper.getQustionID().trim() + "','" + bumper.getUserAnswer().trim()
                    + "','" + bumper.getAnswerDate().trim() + "','" + bumper.getAnswerTime().trim()
                    + "','" + bumper.getAnswerStatus().trim() + "')";
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionDao:insertBumperAnswer: Exception occured "
                    + "while inserting Bumper asnwers, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }
}

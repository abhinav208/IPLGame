/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.BumperQuestionBean;
import IPL_BEANS.UserBean;
import IPL_DAO.BumperQuestionDao;
import IPL_UTILITY.IplBidConnection;
import IPL_UTILITY.MyServerDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Abhinav Kumar
 */
public class BumperQuestionLogic {

    BumperQuestionBean bumperQuestionBean;
    BumperQuestionDao bumperQuestionDao;

    public BumperQuestionLogic(BumperQuestionBean bumperQuestionBean) {
        this.bumperQuestionBean = bumperQuestionBean;
    }

    public BumperQuestionLogic() {
    }

    public boolean openConnection() {

        boolean check_flag = false;
        bumperQuestionDao = new BumperQuestionDao(bumperQuestionBean);
        try {
            bumperQuestionDao.con = IplBidConnection.openConnection();
            bumperQuestionDao.stmt = bumperQuestionDao.con.createStatement();
            check_flag = true;
        } catch (Exception ex) {
            System.out.println("BumperQuestionLogic:openConnection: Error Occured while "
                    + "opening connection \n" + ex);
        }
        return check_flag;
    }

    public boolean closeConnection() {
        boolean check_flag = false;
        try {
            bumperQuestionDao.stmt.close();
            bumperQuestionDao.con.close();
            check_flag = true;
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionLogic:closeConnection: Exception occured while "
                    + "closing connection is : \n " + sqlException);
        }
        return check_flag;
    }

    public String saveUploadedQuestions() {
        String returnMessage;
        openConnection();
        if (bumperQuestionDao.uploadBumperQuestions()) {
            returnMessage = "Question Uploaded successfully";
        } else {
            returnMessage = "Due to some techinical issue, questions cannot be uploaded";
        }
        closeConnection();
        return returnMessage;
    }

    public ArrayList<BumperQuestionBean> prepareBumberQuestionArrayList() {
        openConnection();
        ResultSet result = bumperQuestionDao.getAllBumperQuestions();
        ArrayList<BumperQuestionBean> questionList = new ArrayList<BumperQuestionBean>();
        try {
            while (result.next()) {
                BumperQuestionBean quesBean = new BumperQuestionBean();
                quesBean.setQustionID(result.getString("Question_id"));
                quesBean.setBumperQuestion(result.getString("bumper_question"));
                quesBean.setBumberAnswer(result.getString("bumper_answer"));
                quesBean.setQuestionStatus(result.getString("question_status"));
                questionList.add(quesBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionLogic:prepareBumberQuestionArrayList: Exception occured while "
                    + "preparing bumper questions list is : \n " + sqlException);
        }
        closeConnection();
        return questionList;
    }

    public ArrayList<BumperQuestionBean> prepareActiveBumberQuestionArrayList() {
        ResultSet result = bumperQuestionDao.getActiveBumperQuestions();
        ArrayList<BumperQuestionBean> questionList = new ArrayList<BumperQuestionBean>();
        try {
            while (result.next()) {
                BumperQuestionBean quesBean = new BumperQuestionBean();
                quesBean.setQustionID(result.getString("Question_id"));
                quesBean.setBumperQuestion(result.getString("bumper_question"));
                quesBean.setBumberAnswer(result.getString("bumper_answer"));
                quesBean.setQuestionStatus(result.getString("question_status"));
                questionList.add(quesBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionLogic:prepareBumberQuestionArrayList: Exception occured while "
                    + "preparing bumper questions list is : \n " + sqlException);
        }
        return questionList;
    }

    public ArrayList<BumperQuestionBean> getUserAsnwers() {
        ResultSet result = bumperQuestionDao.checkUserAnswered();
        ArrayList<BumperQuestionBean> userAnswerList = new ArrayList<BumperQuestionBean>();
        try {
            while (result.next()) {
                BumperQuestionBean bumperBean = new BumperQuestionBean();
                UserBean userBean = new UserBean();
                userBean.setUserID(result.getString("USER_ID"));
                bumperBean.setUserBean(userBean);
                bumperBean.setQustionID(result.getString("QUESTION_ID"));
                bumperBean.setUserAnswer(result.getString("USER_ANSWER"));
                userAnswerList.add(bumperBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionLogic:getUserAsnwers: Exception occured while "
                    + "fetching user answers : \n " + sqlException);
        }
        return userAnswerList;
    }

    public boolean saveUserAnswers(String[] quesIDs, String[] userAnswers, String[] attemptAnswers, UserBean userBean) {

        boolean check_flag = true;
        int loopLength = quesIDs.length;
        openConnection();
        for (int loopControl = 0; loopControl < loopLength; loopControl++) {
            bumperQuestionBean.setUserBean(userBean);
            bumperQuestionBean.setUserAnswer(userAnswers[loopControl]);
            bumperQuestionBean.setQustionID(quesIDs[loopControl]);
            bumperQuestionBean.setAnswerDate(MyServerDateTime.getServerDate());
            bumperQuestionBean.setAnswerTime(MyServerDateTime.getServerTime());
            if (bumperQuestionBean.getUserAnswer() == null || bumperQuestionBean.getUserAnswer().trim().length() == 0
                    || bumperQuestionBean.getUserAnswer().trim().isEmpty()) {
                bumperQuestionBean.setAnswerStatus("NOT-ATTEMPTED");
            } else {
                bumperQuestionBean.setAnswerStatus("ATTEMPTED");
            }
            if (attemptAnswers[loopControl].equalsIgnoreCase("True")) {
                check_flag = bumperQuestionDao.updateBumperAnswers();
            } else if (bumperQuestionBean.getAnswerStatus().equals("ATTEMPTED")) {
                check_flag = bumperQuestionDao.insertBumperAnswer();
            }
            if (!check_flag) {
                break;
            }
        }
        closeConnection();
        return check_flag;
    }
}

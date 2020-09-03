/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.MatchGenealFlagBean;
import IPL_BEANS.QuestionBean;
import IPL_BEANS.TransactionBean;
import IPL_BEANS.UserBean;
import IPL_DAO.QuestionDao;
import IPL_UTILITY.IplBidConnection;
import IPL_UTILITY.MyServerDateTime;
import IPL_UTILITY.RandomRange;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Abhinav Kumar
 */
public class QuestionLogic {

    QuestionBean questionBean;
    QuestionDao questionDao;

    public QuestionLogic() {
    }

    public QuestionLogic(QuestionBean questionBean) {
        this.questionBean = questionBean;
    }

    public boolean openConnection() {
        boolean check_flag = false;
        questionDao = new QuestionDao(questionBean);
        try {
            questionDao.con = IplBidConnection.openConnection();
            questionDao.stmt = questionDao.con.createStatement();
            check_flag = true;
        } catch (Exception ex) {
            System.out.println("QuestionLogic:openConnection: Error Occured while "
                    + "opening connection \n" + ex);
        }
        return check_flag;
    }

    public boolean closeConnection() {
        boolean check_flag = false;
        try {
            questionDao.stmt.close();
            questionDao.con.close();
            check_flag = true;
        } catch (SQLException sqlException) {
            System.out.println("QuestionLogic:closeConnection: Exception occured while "
                    + "closing connection is : \n " + sqlException);
        }
        return check_flag;
    }

    public String saveUploadedQuestions() {
        String returnMessage;
        openConnection();
        if (questionDao.uploadMatchQuestions()) {
            returnMessage = "Question Uploaded successfully";
        } else {
            returnMessage = "Due to some techinical issue, questions cannot be uploaded";
        }
        closeConnection();
        return returnMessage;
    }

    public ArrayList<QuestionBean> getAllQuestion() {
        openConnection();
        ResultSet result = questionDao.getQuestionList();
        ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
        try {
            while (result.next()) {
                questionBean = new QuestionBean();
                questionBean.setQuestionID(result.getString("QUES_ID"));
                questionBean.setUploadQuestion(result.getString("QUESTION"));
                questionList.add(questionBean);
            }
            closeConnection();
        } catch (SQLException sqlException) {
            System.out.println("TeamLogic:getAllTeams: Exception occured "
                    + "while preparing team list is : \n " + sqlException);
        }

        return questionList;
    }

    public boolean assignMatchQuestions() {
        ArrayList<QuestionBean> selectedList = selectMatchQuestion();
        Iterator<QuestionBean> listIterator = selectedList.iterator();
        openConnection();
        boolean checkFlag = true;
        String matchID = questionBean.getMatchID();
        while (listIterator.hasNext()) {
            questionBean = listIterator.next();
            questionBean.setMatchID(matchID);
            questionBean.setCorrectAnswer("NOT DECLARED");
            questionDao.questionBean = questionBean;
            if (!questionDao.assignMatchQuestions()) {
                System.out.println("QuestionLogic:prepareQuestionArrayList: Exception occured while "
                        + "assigning match questions");
                checkFlag = false;
                break;
            }
        }
        closeConnection();
        return checkFlag;
    }

    public ArrayList<QuestionBean> selectMatchQuestion() {
        ArrayList<QuestionBean> completeList = prepareQuestionArrayList();
        int ques_array_length = completeList.size();
        int randomArray[] = new int[4];
        int arrayCounter = 0;
        boolean loopControl = true;
        while (loopControl) {
            if (!(arrayCounter < 4)) {
                break;
            }
            RandomRange randomRange = new RandomRange();
            int newRandomNumber = randomRange.showRandomInteger(0, ques_array_length);
            boolean check_flag = true;
            for (int arrayChecker = 0; arrayChecker < randomArray.length; arrayChecker++) {
                if (randomArray[arrayChecker] == newRandomNumber) {
                    check_flag = false;
                }
            }

            if (check_flag) {
                randomArray[arrayCounter] = newRandomNumber;
                arrayCounter++;
            } else {

                continue;
            }
        }
        ArrayList<QuestionBean> selectedList = new ArrayList<QuestionBean>();
        for (arrayCounter = 0; arrayCounter < randomArray.length; arrayCounter++) {
            selectedList.add(completeList.get(randomArray[arrayCounter] - 1));
        }
        return selectedList;
    }

    public ArrayList<QuestionBean> prepareQuestionArrayList() {
        openConnection();
        ResultSet result = questionDao.getAllUploadedQuestions();
        ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
        try {
            while (result.next()) {
                QuestionBean quesBean = new QuestionBean();
                quesBean.setQuestionID(result.getString("QUES_ID"));
                quesBean.setUploadQuestion(result.getString("QUESTION"));
                questionList.add(quesBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionLogic:prepareQuestionArrayList: Exception occured while "
                    + "preparing questions list is : \n " + sqlException);
        }
        closeConnection();
        return questionList;
    }

    public ArrayList<QuestionBean> prepareMatchQuestionArrayList() {
        //openConnection();
        ResultSet result = questionDao.getMatchAssignedQuestions();
        ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
        try {
            while (result.next()) {
                QuestionBean quesBean = new QuestionBean();
                quesBean.setQuestionID(result.getString("QUES_ID"));
                quesBean.setUploadQuestion(result.getString("QUESTION"));
                quesBean.setCorrectAnswer(result.getString("CORRECT_ANSWER"));
                quesBean.setMatchID(questionBean.getMatchID());
                quesBean.setUserBean(questionBean.getUserBean());
                questionList.add(quesBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionLogic:prepareMatchQuestionArrayList: Exception occured while "
                    + "preparing match assign questions list is : \n " + sqlException);
        }
        //closeConnection();
        questionBean.setQuestionList(questionList);
        return questionList;
    }

    public ArrayList<QuestionBean> prepareAllAttemptedUserAnswers() {
        ResultSet result = questionDao.getAllAttemptedQuestionForMatch();
        ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
        try {
            while (result.next()) {
                QuestionBean quesBean = new QuestionBean();
                UserBean userBean = new UserBean();
                userBean.setUserID(result.getString("USER_ID"));
                quesBean.setUserBean(userBean);
                quesBean.setQuestionID(result.getString("QUES_ID"));
                quesBean.setUserAnswer(result.getString("USER_ANSWER"));
                questionList.add(quesBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionLogic:prepareAllAttemptedUserAnswers: Exception occured while "
                    + " preparing list for user attempted answers: \n " + sqlException);
        }
        return questionList;
    }

    public ArrayList<QuestionBean> fillUserAnswersIfExists() {
        openConnection();
        ArrayList<QuestionBean> questionList = questionBean.getQuestionList();
        Iterator<QuestionBean> itr = questionList.iterator();
        while (itr.hasNext()) {
            QuestionBean quesBean = itr.next();
            questionDao.questionBean = quesBean;
            if (checkUserAlreadyAnswered()) {
                quesBean.setUserAnswer(getUserUpdatedAnswer());
            }
        }
        closeConnection();
        return questionList;
    }

    public boolean checkUserAlreadyAnswered() {
        boolean check_flag = false;
        ResultSet result = questionDao.checkUserAnswerExists();
        try {
            while (result.next()) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionLogic:checkUserAlreadyAnswered: Exception occured while "
                    + "checking user already answered or not : \n " + sqlException);
        }
        return check_flag;
    }

    public String getUserUpdatedAnswer() {
        String user_answer = "";
        ResultSet result = questionDao.checkUserAnswerExists();
        try {
            while (result.next()) {
                user_answer = result.getString("USER_ANSWER");
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionLogic:checkUserAlreadyAnswered: Exception occured while "
                    + "checking user already answered or not : \n " + sqlException);
        }
        return user_answer;
    }

    public String saveUserMatchAnswer() {
        ArrayList<QuestionBean> questionList = questionBean.getQuestionList();
        Iterator<QuestionBean> questionIterator = questionList.iterator();
        String returnMessage = "";
        openConnection();
        while (questionIterator.hasNext()) {
            QuestionBean quesBean = questionIterator.next();
            questionDao.questionBean = quesBean;
            if (!checkUserAlreadyAnswered()) {
                if (questionDao.insertUserMatchAnswer()) {
                    returnMessage = "Your answers have been saved, We wish you good luck ";
                } else {
                    returnMessage = "Due to some technical issue, Your answers cannot be inserted ";
                }
            } else {
                if (questionDao.updateUserAnswer()) {
                    returnMessage = "Your answers have been updated, We wish you good luck ";
                } else {
                    returnMessage = "Due to some technical issue, Your answers cannot be updated ";
                }
            }
        }
        closeConnection();
        return returnMessage;
    }

    public boolean updateCorrectAnswers() {
        boolean check_flag = false;
        Iterator listIterator = questionBean.getQuestionList().iterator();
        while (listIterator.hasNext()) {
            QuestionBean quesBean = (QuestionBean) listIterator.next();
            quesBean.setMatchID(questionBean.getMatchID());
            questionDao.questionBean = quesBean;
            check_flag = questionDao.updateCorrectAnswerForQuestion();
            if (!check_flag) {
                break;
            }
        }
        return check_flag;
    }

    public boolean validateUserAnswers(String matchID, String correctAnswerAmount) {
        boolean check_flag = false;
        ArrayList<QuestionBean> questionList = prepareAllAttemptedUserAnswers();
        Iterator listIterator = questionList.iterator();
        int i = 0;

        while (listIterator.hasNext()) {
            QuestionBean quesBean = (QuestionBean) listIterator.next();
            UserBean userBean = quesBean.getUserBean();
            UserLogic userLogic = new UserLogic(userBean);
            int user_available_answer = userLogic.getUserPoints();
            quesBean.setMatchID(matchID);
            questionDao.questionBean = quesBean;
            String correctAnswer = getCorrectAsnwer();
            String userAnswer = quesBean.getUserAnswer();
            String correctAns[] = correctAnswer.split(",");
            boolean answerJudgeFlag = false;
            for (int arrayCounter = 0; arrayCounter < correctAns.length; arrayCounter++) {
                if (correctAns[arrayCounter].trim().equalsIgnoreCase(userAnswer.trim())) {
                    answerJudgeFlag = true;
                }
            }
            if (answerJudgeFlag) {
                i++;
                userBean.setUserPoints(Integer.toString(user_available_answer + Integer.parseInt(correctAnswerAmount)));
                userLogic = new UserLogic(userBean);
                if (userLogic.updateUserPoints()) {
                    TransactionBean trxBean = new TransactionBean();
                    trxBean.setUserID(userBean.getUserID());
                    trxBean.setTrxType("CREDIT");
                    trxBean.setTrxTitle("QUES-WIN#M" + matchID);
                    trxBean.setTrxTime(MyServerDateTime.getServerTime());
                    trxBean.setTrxDate(MyServerDateTime.getServerDate());
                    trxBean.setTrxAmount(correctAnswerAmount);
                    trxBean.setOpeningBalance(Integer.toString(user_available_answer));
                    trxBean.setClosingBalance(Integer.toString(user_available_answer + +Integer.parseInt(correctAnswerAmount)));
                    TransactionLogic trxLogic = new TransactionLogic(trxBean);
                    check_flag = trxLogic.saveUserTransaction();
                }
            }
            questionDao.updateUserAsnwerStatus();
        }
        if (i == 0) {
            check_flag = true;
        }
        return check_flag;
    }

    public String getCorrectAsnwer() {
        ResultSet result = questionDao.getCorrectAnswerForQuestion();
        String correct_answer = null;
        try {
            while (result.next()) {
                correct_answer = result.getString("CORRECT_ANSWER");
            }
        } catch (SQLException sqlException) {
            System.out.println("QuestionLogic:getCorrectAsnwer: Exception occured while "
                    + " returning correct asnwer for question: \n " + sqlException);
        }
        return correct_answer;
    }
}

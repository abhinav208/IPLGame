/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

import java.util.ArrayList;

/**
 *
 * @author Abhinav Kumar
 */
public class QuestionBean {
    
    String questionID;
    String uploadQuestion;
    String correctAnswer;
    String userAnswer;
    String matchID;
    UserBean userBean;
    String answerStatus;
    
    ArrayList<QuestionBean> questionList;

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getUploadQuestion() {
        return uploadQuestion;
    }

    public void setUploadQuestion(String uploadQuestion) {
        this.uploadQuestion = uploadQuestion;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }    

    public ArrayList<QuestionBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<QuestionBean> questionList) {
        this.questionList = questionList;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }   

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }
}

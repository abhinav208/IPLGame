/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

/**
 *
 * @author abhinaku
 */
public class BumperQuestionBean {
    String qustionID;
    String bumperQuestion;
    String bumberAnswer;
    String questionStatus;
    String userAnswer;
    UserBean userBean;
    String answerDate;
    String answerTime;
    String answerStatus;

    public String getQustionID() {
        return qustionID;
    }

    public void setQustionID(String qustionID) {
        this.qustionID = qustionID;
    }

    public String getBumperQuestion() {
        return bumperQuestion;
    }

    public void setBumperQuestion(String bumperQuestion) {
        this.bumperQuestion = bumperQuestion;
    }

    public String getBumberAnswer() {
        return bumberAnswer;
    }

    public void setBumberAnswer(String bumberAnswer) {
        this.bumberAnswer = bumberAnswer;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }
}

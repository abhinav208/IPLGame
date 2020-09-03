/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

import java.util.ArrayList;

/**
 *
 * @author ANUJGUPTA
 */
public class ScoreBoardBean {
    
    UserBean userBean;
    ArrayList<UserBean> scoreBoardList;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public ArrayList<UserBean> getScoreBoardList() {
        return scoreBoardList;
    }

    public void setScoreBoardList(ArrayList<UserBean> scoreBoardList) {
        this.scoreBoardList = scoreBoardList;
    }
}

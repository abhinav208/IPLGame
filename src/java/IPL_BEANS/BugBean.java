/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

/**
 *
 * @author Abhinav Kumar
 */
public class BugBean {

    UserBean userBean;
    String bugId;
    String bugSubject;
    String bugDetail;
    String bugSolution;

    public String getBugSubject() {
        return bugSubject;
    }

    public void setBugSubject(String bugSubject) {
        this.bugSubject = bugSubject;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getBugDetail() {
        return bugDetail;
    }

    public void setBugDetail(String bugDetail) {
        this.bugDetail = bugDetail;
    }

    public String getBugSolution() {
        return bugSolution;
    }

    public void setBugSolution(String bugSolution) {
        this.bugSolution = bugSolution;
    }
}

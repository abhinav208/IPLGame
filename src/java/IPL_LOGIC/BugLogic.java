/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.BugBean;
import IPL_DAO.BugDao;
import IPL_UTILITY.IplBidConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Abhinav Kumar
 */
public class BugLogic {

    BugBean bugBean;
    BugDao bugDao;

    public BugLogic(BugBean bugBean) {
        this.bugBean = bugBean;
        bugDao = new BugDao(bugBean);
    }

    public BugLogic() {
        bugDao = new BugDao();
    }

    public boolean openConnection() {

        boolean check_flag = false;
        bugDao = new BugDao(bugBean);
        try {
            bugDao.con = IplBidConnection.openConnection();
            bugDao.stmt = bugDao.con.createStatement();
            check_flag = true;
        } catch (Exception ex) {
            System.out.println("BugLogic:openConnection: Error Occured while "
                    + "opening connection \n" + ex);
        }
        return check_flag;
    }

    public boolean closeConnection() {
        boolean check_flag = false;
        try {
            bugDao.stmt.close();
            bugDao.con.close();
            check_flag = true;
        } catch (SQLException sqlException) {
            System.out.println("BugLogic:closeConnection: Exception occured while "
                    + "closing connection is : \n " + sqlException);
        }
        return check_flag;
    }

    public String insertNewBugDetails() {
        String returnMessage = "";
        openConnection();
        if (bugDao.saveUserDetails()) {
            returnMessage = "Issue/Bug Save Successfull.";
        } else {
            returnMessage = "Due to some technical issue, Issue/Bug details cannot be saved";
        }
        closeConnection();
        return returnMessage;
    }

    public ArrayList<BugBean> getAllUserBugDetails() {
        ArrayList<BugBean> return_BugArray = new ArrayList<BugBean>();
        openConnection();
        ResultSet result = bugDao.getAllUserBug();
        try {
            while (result.next()) {
                bugBean = new BugBean();
                bugBean.setBugId(result.getString("bug_id"));
                bugBean.setBugSubject(result.getString("bug_subject"));
                bugBean.setBugDetail(result.getString("bug_description"));
                bugBean.setBugSolution(result.getString("bug_solution"));
                return_BugArray.add(bugBean);
            }
            closeConnection();
        } catch (SQLException sqlException) {
            System.out.println("BugLogic:getAllUserBugDetails: Exception occured "
                    + "while get User all Bug Details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return return_BugArray;
    }
}

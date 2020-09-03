/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.BugBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class BugDao {

    BugBean bugbean;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public BugDao() {
    }

    public BugDao(BugBean bugbean) {
        this.bugbean = bugbean;
    }

    public boolean saveUserDetails() {
        boolean returnFlag = false;
        String query = "INSERT INTO bug_detail"
                + "(user_id, bug_subject, bug_description, bug_solution) VALUES"
                + "('" + bugbean.getUserBean().getUserName().trim() + "',"
                + "'" + bugbean.getBugSubject() + "',"
                + "'" + bugbean.getBugDetail() + "',"
                + "'" + bugbean.getBugSolution() + "')";
        try {

            if (stmt.executeUpdate(query) != 0) {
                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("BugDao:saveUserDetails_DAO: Exception occured "
                    + "while bug save, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }
    
    public ResultSet getAllUserBug() {
        String query = "SELECT * FROM bug_detail WHERE user_id = '"+bugbean.getUserBean().getUserName()+"'";
        System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("BugDao:getAllUserBug_DAO: Exception occured "
                    + "while checking user bugs, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }
}

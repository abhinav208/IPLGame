/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.UserBean;
import IPL_UTILITY.IplBidConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author abhinav
 */
public class UserDao {

    UserBean userBean;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public UserDao() {
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("UserDao:Default_Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public UserDao(UserBean userBean) {
        this.userBean = userBean;
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("UserDao:Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public ResultSet checkUserNameExistence() {
        String query = "SELECT * FROM USER_DETAILS "
                + "WHERE USER_NAME = '" + userBean.getUserName().trim() + "'";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:checkUserNameExistence: Exception occured "
                    + "while checking user username, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet checkEmailExistence() {
        String query = "SELECT * FROM USER_DETAILS "
                + "WHERE USER_EMAIL = '" + userBean.getUserEmail().trim() + "'";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:checkEmailExistence_DAO: Exception occured "
                    + "while checking user email, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean saveUserDetails() {
        boolean returnFlag = false;
        String query = "INSERT INTO USER_DETAILS(USER_NAME,FULL_NAME,"
                + "USER_EMAIL,USER_PASSWORD,USER_POINTS,USER_STATUS,USER_TYPE,USER_TEAM) "
                + "VALUES('" + userBean.getUserName().trim() + "','"
                + userBean.getUserFullname().toUpperCase().trim() + "','"
                + userBean.getUserEmail().trim() + "','"
                + userBean.getUserPassword().trim() + "','"
                + userBean.getUserPoints().trim() + "','"
                + userBean.getUserStatus().trim() + "','"
                + userBean.getUserType().trim() + "','"
                + userBean.getUserTeam().trim() + "')";
        try {

            if (stmt.executeUpdate(query) != 0) {
                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("UserDao:saveUserDetails_DAO: Exception occured "
                    + "while user registration, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }

    public ResultSet getLastInsertedUserRecord() {
        String query = "SELECT * FROM USER_DETAILS "
                + "WHERE USER_NAME='" + userBean.getUserName().trim() + "'";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:getLastInsertedUserRecord: Exception occured "
                    + "while fetching last inserted user record , Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getUserDetailsByUsernameOrEmail() {
        String query = "SELECT * FROM USER_DETAILS "
                + "WHERE USER_NAME='" + userBean.getUserName().trim() + "' or USER_EMAIL='" + userBean.getUserName().trim() + "'";
        System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:getUserDetailsByUsernameOrEmail_DAO: Exception occured "
                    + "while checking user authentication, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet checkAuthentication() {
        String query = "SELECT * FROM USER_DETAILS "
                + "WHERE USER_NAME='" + userBean.getUserName().trim() + "' "
                + "AND USER_PASSWORD='" + userBean.getUserPassword().trim() + "' "
                + "AND USER_STATUS ='ACTIVE'";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:checkAuthentication_DAO: Exception occured "
                    + "while checking user authentication, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getAllUser() {
        String query = "SELECT * FROM USER_DETAILS WHERE USER_TYPE in ('PLAYER','CAPTAIN')";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:getAllUser_DAO: Exception occured "
                    + "while checking user authentication, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getAllUserTeam() {
        String query = "SELECT * FROM USER_DETAILS WHERE USER_TYPE in ('PLAYER','CAPTAIN')"
                + "AND USER_TEAM = '" + userBean.getUserTeam().trim() + "'";;
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:getAllUser_DAO: Exception occured "
                    + "while checking user authentication, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getUserDetails() {
        String query = "SELECT * FROM USER_DETAILS "
                + "WHERE USER_ID=" + userBean.getUserID().trim();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:getUserDetails_DAO: Exception occured "
                    + "while checking user authentication, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean updateUserStatus() {
        boolean checkFlag = false;
        String query = "UPDATE USER_DETAILS SET USER_STATUS='"
                + userBean.getUserStatus().trim().toUpperCase() + "' "
                + "WHERE USER_ID=" + userBean.getUserID().trim().toUpperCase();
        try {
            stmt.executeUpdate(query);
            checkFlag = true;
        } catch (SQLException sqlException) {
            System.out.println("UserDao:updateUserStatus_DAO: Exception occured "
                    + "while updating user status, Please check below message "
                    + "for more details : \n" + sqlException);
        }

        return checkFlag;
    }

    public ResultSet getUserPoints() {
        String query = "SELECT USER_POINTS FROM USER_DETAILS "
                + "WHERE USER_ID=" + userBean.getUserID().trim();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("UserDao:getUserPoints_DAO: Exception occured "
                    + "while fetching user points, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean updateUserPassword() {
        boolean checkFlag = false;
        String query = "UPDATE USER_DETAILS SET "
                + "USER_PASSWORD = '" + userBean.getUserPassword().trim() + "' "
                + "WHERE USER_ID=" + userBean.getUserID().trim();
        try {
            stmt.executeUpdate(query);
            checkFlag = true;
        } catch (SQLException sqlException) {
            System.out.println("UserDao:updateUserPassword_DAO: Exception occured "
                    + "while updating user password, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }

    public boolean updateUserPoints() {
        boolean checkFlag = false;
        String query = "UPDATE USER_DETAILS SET "
                + "USER_POINTS = " + userBean.getUserPoints().trim() + " "
                + "WHERE USER_ID=" + userBean.getUserID().trim();
        try {
            stmt.executeUpdate(query);
            checkFlag = true;
        } catch (SQLException sqlException) {
            System.out.println("UserDao:updateUserPoints_DAO: Exception occured "
                    + "while updating user points, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }
}

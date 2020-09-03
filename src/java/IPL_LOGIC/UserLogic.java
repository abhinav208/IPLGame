/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.TransactionBean;
import IPL_BEANS.UserBean;
import IPL_DAO.UserDao;
import IPL_UTILITY.MyServerDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author abhinav
 */
public class UserLogic {

    UserBean userBean;
    UserDao userDao;

    public UserLogic() {
        userDao = new UserDao();
    }

    public UserLogic(UserBean userBean) {
        this.userBean = userBean;
        userDao = new UserDao(userBean);
    }

    public boolean validateUserEmail() {
        String userEmailParts[] = userBean.getUserEmail().split("@");
        boolean checkFlag = false;
        if (userEmailParts.length > 2) {
            return checkFlag;
        }
        if (!userEmailParts[1].equalsIgnoreCase("amdocs.com")) {
            return checkFlag;
        }
        checkFlag = true;
        return checkFlag;
    }

    public boolean isEmailExists() {
        boolean checkFlag = false;
        ResultSet result = userDao.checkEmailExistence();
        try {
            while (result.next()) {
                checkFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:isEmailExists: Exception occured "
                    + "while checking user email existence, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }

    public boolean isUserNameExists() {
        boolean checkFlag = false;
        ResultSet result = userDao.checkUserNameExistence();
        try {
            while (result.next()) {
                checkFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:isUserNameExists: Exception occured "
                    + "while checking user name existence, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }

    public String generateNewPassword() {
        double randomNumber = Math.random();
        String randomString = Double.toString(randomNumber);
        String newPassword = randomString.split("\\.")[1].substring(0, 8);
        return newPassword;
    }

    public void getLastInsertedUserID() {
        ResultSet result = userDao.getLastInsertedUserRecord();
        try {
            while (result.next()) {
                userBean.setUserID(result.getString("USER_ID"));
            }
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:getLastInsertedUserID: Exception occured "
                    + "while getting user id for last inserted record, Please check below message "
                    + "for more details : \n" + sqlException);
        }
    }

    public String insertNewUsersDetails() {
        String returnMessage = "";
        if (validateUserEmail()) {
            if (!isEmailExists()) {
                if (!isUserNameExists()) {
                    userBean.setUserPassword(generateNewPassword());
                    if (userDao.saveUserDetails()) {
                        getLastInsertedUserID();
                        TransactionBean trxBean = new TransactionBean();
                        trxBean.setUserID(userBean.getUserID());
                        trxBean.setTrxType("CREDIT");
                        trxBean.setTrxTitle("NEW REGISTRATION");
                        trxBean.setTrxTime(MyServerDateTime.getServerTime());
                        trxBean.setTrxDate(MyServerDateTime.getServerDate());
                        trxBean.setTrxAmount("1000");
                        trxBean.setOpeningBalance("0");
                        trxBean.setClosingBalance("1000");
                        TransactionLogic trxLogic = new TransactionLogic(trxBean);
                        if (trxLogic.saveUserTransaction()) {
                            returnMessage = "Registration Successfull. Please check your mail box for login details";
                        }

                    } else {
                        returnMessage = "Due to some technical issue, User details cannot be saved";
                    }
                } else {
                    returnMessage = "UserName is already registered, Try your luck with next";
                    return returnMessage;
                }
            } else {
                returnMessage = "Email is already registered!";
                return returnMessage;
            }
        } else {
            returnMessage = "Email entered is not valid";
        }
        try {
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:insertNewUsersDetails: Exception occured "
                    + "while closing connection is : \n" + sqlException);
        }
        return returnMessage;
    }

    public UserBean getUserDetailByUsernameOrEmail() {
        ResultSet result = userDao.getUserDetailsByUsernameOrEmail();
        try {
            while (result.next()) {
                userBean = new UserBean();
                userBean.setUserID(result.getString("USER_ID"));
                userBean.setUserName(result.getString("USER_NAME"));
                userBean.setUserFullname(result.getString("FULL_NAME"));
                userBean.setUserEmail(result.getString("USER_EMAIL"));
                userBean.setUserPoints(result.getString("USER_POINTS"));
                userBean.setUserStatus(result.getString("USER_STATUS"));
                userBean.setUserPassword(result.getString("USER_PASSWORD"));
            }
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:getUserDetailByUsernameOrEmail: Exception occured "
                    + "while getting user details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return userBean;
    }

    public boolean checkUserLogin() {
        ResultSet result = userDao.checkAuthentication();
        boolean checkFlag = false;
        try {
            int i = 0;
            while (result.next()) {
                i++;
                if (i == 2) {
                    System.out.println("More than one row return for user login");
                    throw new SQLException();
                }
                if (i == 1) {
                    checkFlag = true;
                }
                userBean.setUserID(result.getString("USER_ID"));
                userBean.setUserFullname(result.getString("FULL_NAME").toUpperCase());
                userBean.setUserEmail(result.getString("USER_EMAIL"));
                userBean.setUserPoints(result.getString("USER_POINTS"));
                userBean.setUserStatus(result.getString("USER_STATUS").toUpperCase());
                userBean.setUserType(result.getString("USER_TYPE"));
                userBean.setUserTeam(result.getString("USER_TEAM"));
            }
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:checkUserLogin: Exception occured "
                    + "while checking user login, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }

    public ArrayList getAllUserDetails() {
        ArrayList<UserBean> return_UserArray = new ArrayList<UserBean>();
        ResultSet result = userDao.getAllUser();
        try {
            while (result.next()) {
                userBean = new UserBean();
                userBean.setUserID(result.getString("USER_ID"));
                userBean.setUserName(result.getString("USER_NAME"));
                userBean.setUserFullname(result.getString("FULL_NAME"));
                userBean.setUserEmail(result.getString("USER_EMAIL"));
                userBean.setUserPoints(result.getString("USER_POINTS"));
                userBean.setUserStatus(result.getString("USER_STATUS"));
                userBean.setUserTeam(result.getString("USER_TEAM"));
                return_UserArray.add(userBean);
            }
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:getAllUserDetails: Exception occured "
                    + "while get all User Details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return return_UserArray;
    }

    public ArrayList getAllUserTeamDetails() {
        ArrayList<UserBean> return_UserArray = new ArrayList<UserBean>();
        ResultSet result = userDao.getAllUserTeam();
        try {
            while (result.next()) {
                userBean = new UserBean();
                userBean.setUserID(result.getString("USER_ID"));
                userBean.setUserName(result.getString("USER_NAME"));
                userBean.setUserFullname(result.getString("FULL_NAME"));
                userBean.setUserEmail(result.getString("USER_EMAIL"));
                userBean.setUserPoints(result.getString("USER_POINTS"));
                userBean.setUserStatus(result.getString("USER_STATUS"));
                userBean.setUserTeam(result.getString("USER_TEAM"));
                return_UserArray.add(userBean);
            }
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:getAllUserDetails: Exception occured "
                    + "while get all User Details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return return_UserArray;
    }

    public UserBean getUserDetail() {
        ResultSet result = userDao.getUserDetails();
        try {
            while (result.next()) {
                userBean = new UserBean();
                userBean.setUserID(result.getString("USER_ID"));
                userBean.setUserName(result.getString("USER_NAME"));
                userBean.setUserFullname(result.getString("FULL_NAME"));
                userBean.setUserEmail(result.getString("USER_EMAIL"));
                userBean.setUserPoints(result.getString("USER_POINTS"));
                userBean.setUserStatus(result.getString("USER_STATUS"));
                userBean.setUserPassword(result.getString("USER_PASSWORD"));
            }
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:getUserDetail: Exception occured "
                    + "while getting user details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return userBean;
    }

    public boolean updateUserStatus() {
        boolean check_flag = userDao.updateUserStatus();
        try {
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:updateUserStatus: Exception occured "
                    + "while updating user status, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public int getUserPoints() {
        int return_point = 0;
        ResultSet result = userDao.getUserPoints();
        try {
            while (result.next()) {
                return_point = result.getInt("USER_POINTS");
            }
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:getUserPoints: Exception occured "
                    + "while preparin user points, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return return_point;
    }

    public boolean updateUserPassword() {
        boolean check_flag = userDao.updateUserPassword();
        try {
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:updateUserPassword: Exception occured "
                    + "while updating user password, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }

    public boolean updateUserPoints() {
        boolean check_flag = userDao.updateUserPoints();
        try {
            userDao.stmt.close();
            userDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("UserLogic:getUserPoints: Exception occured "
                    + "while preparin user points, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return check_flag;
    }
}

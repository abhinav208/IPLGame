/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

/**
 *
 * @author abhinav
 */
public class UserBean {

    String userID;
    String userName;
    String userFullname;
    String userEmail;
    String userPassword;
    String userPoints;
    String userBidPoints;
    String userStatus;
    String userType;
    String userEarnedPoints;
    String userLosedPoints;
    String userPosition;
    String userTeam;

    public String getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(String userTeam) {
        this.userTeam = userTeam;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(String userPoints) {
        this.userPoints = userPoints;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserBidPoints() {
        return userBidPoints;
    }

    public void setUserBidPoints(String userBidPoints) {
        this.userBidPoints = userBidPoints;
    }

    public String getUserEarnedPoints() {
        return userEarnedPoints;
    }

    public void setUserEarnedPoints(String userEarnedPoints) {
        this.userEarnedPoints = userEarnedPoints;
    }

    public String getUserLosedPoints() {
        return userLosedPoints;
    }

    public void setUserLosedPoints(String userLosedPoints) {
        this.userLosedPoints = userLosedPoints;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
}

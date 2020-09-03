/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.TeamBean;
import IPL_UTILITY.IplBidConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class TeamDao {

    TeamBean teamBean;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public TeamDao() {
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("TeamDao:Default_Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public TeamDao(TeamBean teamBean) {
        this.teamBean = teamBean;
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("TeamDao:Default_Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public ResultSet getTeamList() {
        String query = "SELECT * FROM TEAM_DETAILS";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("TeamDao:getTeamList_DAO: Exception occured "
                    + "while fetching team list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean insertTeamDetails() {
        boolean returnFlag = false;
        String query = "INSERT INTO TEAM_DETAILS(TEAM_NAME)"
                + " VALUES('" + teamBean.getTeamName().trim().toUpperCase() + "')";
        try {

            if (stmt.executeUpdate(query) != 0) {
                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("TeamDao:insertTeamDetails_DAO: Exception occured "
                    + "while inserting team details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }

    public ResultSet getTeamIDByTeamName() {
        String query = "SELECT TEAM_ID FROM TEAM_DETAILS WHERE TEAM_NAME='" + teamBean.getTeamName().trim().toUpperCase() + "'";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("TeamDao:getTeamIDByTeamName: Exception occured "
                    + "while fetching team id by passing team name, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getTeamPlayers() {
        String query = "SELECT PLAYER_NAME FROM TEAM_PLAYER_DETAIL WHERE TEAM_ID = " + teamBean.getTeamID().trim();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("TeamDao:getTeamPlayers: Exception occured "
                    + "while fetching team player list by team id, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getTeamNameByTeamID() {
        String query = "SELECT TEAM_NAME FROM TEAM_DETAILS WHERE TEAM_ID=" + teamBean.getTeamID();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("TeamDao:getTeamIDByTeamName: Exception occured "
                    + "while fetching team id by passing team name, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }
}

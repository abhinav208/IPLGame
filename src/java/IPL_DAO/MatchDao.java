/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.MatchBean;
import IPL_BEANS.ResultBean;
import IPL_UTILITY.IplBidConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchDao {

    MatchBean matchBean;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public MatchDao() {
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("MatchDao:Default_Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public MatchDao(MatchBean matchBean) {
        this.matchBean = matchBean;
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("MatchDao:Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public ResultSet getMatchList() {
        String query = "SELECT MD.*,TD.TEAM_NAME TEAM1_NAME,TD2.TEAM_NAME TEAM2_NAME "
                + "FROM MATCH_DETAILS MD, TEAM_DETAILS TD, TEAM_DETAILS TD2 "
                + "WHERE MD.TEAM1_ID = TD.TEAM_ID "
                + "AND MD.TEAM2_ID = TD2.TEAM_ID ORDER BY MD.MATCH_ID";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:getMatchList_DAO: Exception occured "
                    + "while fetching macth list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean insertMatchDetails() {
        boolean returnFlag = false;
        String query = "INSERT INTO MATCH_DETAILS(TEAM1_ID,TEAM2_ID,MATCH_DATE,ROUND_TYPE,START_TIME,"
                + "MATCH_STATUS,MATCH_DAY) VALUES(" + matchBean.getTeam1ID().trim()
                + "," + matchBean.getTeam2ID().trim()
                + ",'" + matchBean.getMatchDate().trim()
                + "','" + matchBean.getRoundType().trim().toUpperCase()
                + "','" + matchBean.getStartTime().trim()
                + "','" + matchBean.getMatchStatus().trim().toUpperCase()
                + "','" + matchBean.getMatchDay().trim().toUpperCase() + "')";
        try {

            if (stmt.executeUpdate(query) != 0) {

                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:insertMatchDetails: Exception occured "
                    + "while inserting match details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }

    public ResultSet getLastInsertedMatchID() {
        String query = "SELECT MAX(MATCH_ID) FROM MATCH_DETAILS";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:getLastInsertedMatchID: Exception occured "
                    + "while fetching last macth id, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean saveMatchResult() {

        boolean returnFlag = false;
        ResultBean resultBean = matchBean.getResultBean();
        String query = "INSERT INTO MATCH_RESULT(MATCH_ID,WIN_TEAM_ID,WINNING_REMARKS,TEAM1_COLLECTION,TEAM2_COLLECTION) "
                + "VALUES(" + resultBean.getMatchID().trim()
                + "," + resultBean.getTeamID().trim()
                + ",'" + resultBean.getWinningRemarks().trim().toUpperCase()
                + "'," + resultBean.getTeam1Collection().trim()
                + "," + resultBean.getTeam2Collection().trim() + ")";
        try {

            if (stmt.executeUpdate(query) != 0) {

                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:saveMatchResult: Exception occured "
                    + "while inserting match result details, Please check below message "
                    + "for more details : \n" + sqlException);
        }

        return returnFlag;
    }

    public boolean updateMatchStatus() {
        boolean checkFlag = false;
        String query = "UPDATE MATCH_DETAILS SET MATCH_STATUS='"
                + matchBean.getMatchStatus() + "' WHERE "
                + "MATCH_ID = " + matchBean.getMatchID().trim();
        try {
            stmt.executeUpdate(query);
            checkFlag = true;
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:updateMatchStatus_DAO: Exception occured "
                    + "while updating match status, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }

    public ResultSet getClosedMatch() {
        String query = "SELECT MD.*,TD.TEAM_NAME TEAM1_NAME,TD2.TEAM_NAME TEAM2_NAME "
                + "FROM MATCH_DETAILS MD, TEAM_DETAILS TD, TEAM_DETAILS TD2 "
                + "WHERE MD.TEAM1_ID = TD.TEAM_ID "
                + "AND MD.TEAM2_ID = TD2.TEAM_ID "
                + "AND MD.MATCH_STATUS = 'BID CLOSED' ORDER BY MD.MATCH_ID";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:getOpenMatch_DAO: Exception occured "
                    + "while fetching open match list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getClosedMatchDetailsByID() {
        String query = "SELECT MD.*,TD.TEAM_NAME TEAM1_NAME,TD2.TEAM_NAME TEAM2_NAME "
                + "FROM MATCH_DETAILS MD, TEAM_DETAILS TD, TEAM_DETAILS TD2 "
                + "WHERE MD.TEAM1_ID = TD.TEAM_ID "
                + "AND MD.TEAM2_ID = TD2.TEAM_ID "
                + "AND MD.MATCH_STATUS = 'BID CLOSED' "
                + "AND MD.MATCH_ID = " + matchBean.getMatchID().trim()
                + " ORDER BY MD.MATCH_ID";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:getOpenMatch_DAO: Exception occured "
                    + "while fetching open match list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getResultDeclareMatch() {
        String query = "SELECT MD.*,TD.TEAM_NAME TEAM1_NAME,TD2.TEAM_NAME TEAM2_NAME "
                + "FROM MATCH_DETAILS MD, TEAM_DETAILS TD, TEAM_DETAILS TD2 "
                + "WHERE MD.TEAM1_ID = TD.TEAM_ID "
                + "AND MD.TEAM2_ID = TD2.TEAM_ID "
                + "AND MD.MATCH_STATUS = 'RESULT DECLARED' ORDER BY MD.MATCH_ID DESC";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:getResultDeclareMatch_DAO: Exception occured "
                    + "while fetching result declared match list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getDeclaredMatchDetailsByID() {
        String query = "SELECT MD.*,TD.TEAM_NAME TEAM1_NAME,TD2.TEAM_NAME TEAM2_NAME, MR.WIN_TEAM_ID "
                + "FROM MATCH_DETAILS MD, TEAM_DETAILS TD, TEAM_DETAILS TD2, MATCH_RESULT MR "
                + "WHERE MD.TEAM1_ID = TD.TEAM_ID "
                + "AND MD.TEAM2_ID = TD2.TEAM_ID "
                + "AND MD.MATCH_ID = MR.MATCH_ID "
                + "AND MD.MATCH_STATUS = 'RESULT DECLARED' "
                + "AND MD.MATCH_ID = " + matchBean.getMatchID().trim()
                + " ORDER BY MD.MATCH_ID";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:getDeclaredMatchDetailsByID_DAO: Exception occured "
                    + "while fetching open match list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getOpenMatch() {
        String query = "SELECT MD.*,TD.TEAM_NAME TEAM1_NAME,TD2.TEAM_NAME TEAM2_NAME "
                + "FROM MATCH_DETAILS MD, TEAM_DETAILS TD, TEAM_DETAILS TD2 "
                + "WHERE MD.TEAM1_ID = TD.TEAM_ID "
                + "AND MD.TEAM2_ID = TD2.TEAM_ID "
                + "AND MD.MATCH_STATUS = 'BID OPEN' ORDER BY MD.MATCH_ID";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:getOpenMatch_DAO: Exception occured "
                    + "while fetching open match list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet checkMatchStatus() {
        String query = "SELECT MD.MATCH_STATUS FROM MATCH_DETAILS MD "
                + "WHERE MD.MATCH_ID = " + matchBean.getMatchID().trim();
        //System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:checkMatchStatus_DAO: Exception occured "
                    + "while fetching match status, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean updateMatchResultForMatchID() {
        boolean returnFlag = false;
        String query = "UPDATE MATCH_RESULT "
                + " SET WIN_TEAM_ID = " + matchBean.getResultBean().getTeamID().trim()
                + ", WINNING_REMARKS = '" + matchBean.getResultBean().getWinningRemarks().trim().toUpperCase()
                + "', TEAM1_COLLECTION = " + matchBean.getTeam1Collection().trim()
                + ",  TEAM2_COLLECTION = " + matchBean.getTeam2Collection().trim()
                + " WHERE MATCH_ID = " + matchBean.getMatchID().trim();
        try {

            if (stmt.executeUpdate(query) != 0) {

                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:saveMatchResult: Exception occured "
                    + "while inserting match result details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }

    public boolean updateDrawMatchResultForMatchID() {
        boolean returnFlag = false;
        String query = "UPDATE MATCH_RESULT "
                + " SET WINNING_REMARKS = '" + matchBean.getResultBean().getWinningRemarks().trim().toUpperCase()
                + "', TEAM1_COLLECTION = " + matchBean.getTeam1Collection().trim()
                + ",  TEAM2_COLLECTION = " + matchBean.getTeam2Collection().trim()
                + " WHERE MATCH_ID = " + matchBean.getMatchID().trim();
        try {

            if (stmt.executeUpdate(query) != 0) {

                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchDao:updateDrawMatchResultForMatchID: Exception occured "
                    + "while inserting match result details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }
}

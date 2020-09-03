/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.MatchCollection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchCollectionDao {

    MatchCollection collectionBean;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public MatchCollectionDao() {
    }

    public MatchCollectionDao(MatchCollection collectionBean) {
        this.collectionBean = collectionBean;
    }

    public MatchCollection getCollectionBean() {
        return collectionBean;
    }

    public void setCollectionBean(MatchCollection collectionBean) {
        this.collectionBean = collectionBean;
    }

    public ResultSet getUserOpenMatchBidPoints() {
        String query = "SELECT UD.USER_ID UID,UD.USER_EMAIL,UD.USER_NAME,UD.FULL_NAME,UD.USER_TEAM,"
                + "UD.USER_STATUS,UD.USER_POINTS,UD.USER_TYPE, IFNULL(SUM(MC.AMOUNT),'0') BID_POINTS "
                + "FROM USER_DETAILS UD LEFT JOIN MATCH_COLLECTION MC  "
                + "ON MC.USER_ID = UD.USER_ID "
                + "AND MC.COL_STATUS = 'BID OPEN'"
                + "GROUP BY UD.USER_ID "
                + "ORDER BY UD.USER_POINTS DESC";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getUserOpenMatchCollection: Exception occured "
                    + "while fetching collection for open match and user, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;

    }

    public ResultSet getUserLosedPoints() {
        String query = "SELECT IFNULL(SUM(MC.AMOUNT),'0') From MATCH_COLLECTION MC "
                + "WHERE MC.USER_ID = " + collectionBean.getUserID()
                + " AND MC.COL_STATUS = 'LOSS'";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getUserLosedPoints: Exception occured "
                    + "while fetching collection for losed points and user, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getUserEarnedPoints() {
        String query = "SELECT IFNULL( SUM( TRX.TRX_AMOUNT ) , '0' ) FROM USER_TRX_HISTORY TRX "
                + "WHERE TRX.USER_ID = " + collectionBean.getUserID().trim()
                + " AND (TRX.TRX_TITLE LIKE 'BIDING-GAIN%' "
                + "OR TRX.TRX_TITLE LIKE 'QUES-WIN%' OR TRX.TRX_TITLE LIKE 'NBU-BENEFITS%')";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getUserEarnedPoints: Exception occured "
                    + "while fetching collection for eared points and user, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getTeamMatchCollection() {
        String query = "SELECT MC.*,UD.USER_NAME FROM MATCH_COLLECTION MC , USER_DETAILS UD "
                + "WHERE MATCH_ID=" + collectionBean.getMatchID().trim()
                + " AND BID_TEAM=" + collectionBean.getBidTeam().trim()
                + " AND MC.USER_ID = UD.USER_ID ORDER BY MC.BID_DATE,MC.BID_TIME";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getTeamMatchCollection_DAO: Exception occured "
                    + "while fetching collection for match and team, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getUserMatchCollection() {
        String query = "SELECT MC.*,UD.USER_NAME,TD.TEAM_NAME FROM MATCH_COLLECTION MC , USER_DETAILS UD, TEAM_DETAILS TD "
                + "WHERE MC.MATCH_ID=" + collectionBean.getMatchID().trim()
                + " AND MC.USER_ID=" + collectionBean.getUserID().trim()
                + " AND MC.USER_ID = UD.USER_ID "
                + " AND MC.BID_TEAM = TD.TEAM_ID ORDER BY MC.BID_DATE,MC.BID_TIME";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getUserMatchCollection_DAO: Exception occured "
                    + "while fetching collection for match and user, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getPerTeamMatchCollection() {
        String query = "SELECT MC.*,UD.USER_NAME FROM MATCH_COLLECTION MC , USER_DETAILS UD "
                + "WHERE MATCH_ID = " + collectionBean.getMatchID().trim()
                + " AND BID_TEAM = " + collectionBean.getBidTeam().trim()
                + " AND MC.USER_ID = UD.USER_ID "
                + " AND UD.USER_TEAM like (SELECT concat('%', USER_TEAM) FROM USER_DETAILS "
                + " USR WHERE USR.USER_ID = " + collectionBean.getUserID().trim() + ")"
                + " ORDER BY MC.BID_DATE,MC.BID_TIME";
        System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getTeamMatchCollection_DAO: Exception occured "
                    + "while fetching collection for match and team, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getCollectionDetailsForMatchID() {
        String query = "SELECT * FROM MATCH_COLLECTION "
                + "WHERE MATCH_ID = " + collectionBean.getMatchID();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getCollectionDetailsForMatchID: Exception occured "
                    + "while fetching collection for eared points and user, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getUserTotalBidPointPerMatch() {
        String query = "SELECT IFNULL(SUM(MC.AMOUNT),'0') Total_Point "
                + "FROM match_collection MC WHERE MC.MATCH_ID = " + collectionBean.getMatchID().trim()
                + " and MC.USER_ID = " + collectionBean.getUserID().trim();
        //System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getUserTotalBidPointPerMatch: Exception occured "
                    + "while fetching user points per Match, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getUserBidTeamPerMatch() {
        String query = "SELECT distinct(MC.BID_TEAM)"
                + "FROM match_collection MC WHERE MC.MATCH_ID = " + collectionBean.getMatchID().trim()
                + " and MC.USER_ID = " + collectionBean.getUserID().trim();
        //System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getUserBidTeamPerMatch: Exception occured "
                    + "while fetching User Bid Team per Match, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean insertMatchCollection() {
        boolean returnFlag = false;
        String query = "INSERT INTO MATCH_COLLECTION(MATCH_ID,USER_ID,AMOUNT,BID_DATE,"
                + "BID_TIME,BID_TEAM,RESULT_AMOUNT,COL_STATUS) VALUES(" + collectionBean.getMatchID().trim()
                + "," + collectionBean.getUserID().trim()
                + "," + collectionBean.getAmount().trim()
                + ",'" + collectionBean.getBidDate().trim()
                + "','" + collectionBean.getBidTime().trim()
                + "'," + collectionBean.getBidTeam().trim()
                + "," + collectionBean.getResultAmount().trim()
                + ",'" + collectionBean.getColStatus().trim().toUpperCase() + "')";
        try {

            if (stmt.executeUpdate(query) != 0) {
                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:insertMatchCollection_DAO: Exception occured "
                    + "while inserting match collection details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }

    public boolean deleteMatchCollectionEXP() {
        boolean checkFlag = false;
        String query = "DELETE FROM MATCH_COLLECTION WHERE"
                + " MATCH_ID = " + collectionBean.getMatchID().trim()
                + " AND USER_ID = " + collectionBean.getUserID().trim()
                + " AND AMOUNT = " + collectionBean.getAmount().trim()
                + " AND BID_DATE = '" + collectionBean.getBidDate().trim() + "' "
                + " AND BID_TIME = '" + collectionBean.getBidTime().trim() + "' "
                + " AND BID_TEAM = " + collectionBean.getBidTeam().trim()
                + " AND RESULT_AMOUNT = " + collectionBean.getResultAmount()
                + " AND COL_STATUS = '" + collectionBean.getColStatus() + "'";
        try {
            stmt.executeUpdate(query);
            checkFlag = true;
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:deleteMatchCollectionEXP: Exception occured "
                    + "while deleting collection details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }

    public boolean updateResultAndStatusAmount() {
        boolean checkFlag = false;
        String query = "UPDATE MATCH_COLLECTION "
                + " SET RESULT_AMOUNT=" + collectionBean.getResultAmount().trim()
                + " ,COL_STATUS = '" + collectionBean.getColStatus().trim().toUpperCase()
                + "' WHERE COLLECTION_ID=" + collectionBean.getCollectionID().trim();
        System.out.println(query);
        try {
            stmt.executeUpdate(query);
            checkFlag = true;
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:updateResultAmount_DAO: Exception occured "
                    + "while updating result amount, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return checkFlag;
    }

    public ResultSet getDistinctBidUserForMatchID() {
        String query = "SELECT DISTINCT USER_ID, MATCH_ID FROM MATCH_COLLECTION "
                + "WHERE MATCH_ID = " + collectionBean.getMatchID();
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getDistinctBidUserForMatchID: Exception occured "
                    + "while fetching collection for distinct user id, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getUnBidUserDetails() {
        String query = "SELECT UD.* FROM USER_DETAILS UD  "
                + "WHERE UD.USER_ID NOT IN( SELECT MC.USER_ID FROM MATCH_COLLECTION MC "
                + "WHERE MATCH_ID = " + collectionBean.getMatchID().trim() + ")"
                + " AND USER_STATUS = 'ACTIVE' "
                + "AND USER_TYPE = 'PLAYER' "
                + "AND UD.USER_POINTS > 0";
        System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getUnBidUserDetails: Exception occured "
                    + "while fetching non bid user details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public ResultSet getTotalTeamCollection() {
        String query = "SELECT USER_TEAM as TEAM_NAME, sum(USER_POINTS) as TEAM_POINT, "
                + "count(*) as TEAM_COUNT FROM user_details WHERE USER_STATUS = 'ACTIVE' GROUP BY USER_TEAM";
        System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:getTotalTeamCollection_DAO: Exception occured "
                    + "while fetching total collection and team, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }

    public boolean insertDataForGraph() {
        boolean returnFlag = false;
        String query = "INSERT INTO match_graph_data(match_id, team_id, team_amount, team_count) VALUES ("
                + collectionBean.getMatchID()
                + ",'" + collectionBean.getWinTeamID() + "'"
                + ",'" + collectionBean.getAmount() + "'"
                + ",'"+ collectionBean.getResultAmount() +"')";
        System.out.println(query);
        try {
            if (stmt.executeUpdate(query) != 0) {
                returnFlag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionDao:insertDataForGraph_DAO: Exception occured "
                    + "while inserting data regarding graph, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return returnFlag;
    }
}

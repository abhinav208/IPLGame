/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.MatchCollection;
import IPL_BEANS.MatchGenealFlagBean;
import IPL_BEANS.TransactionBean;
import IPL_BEANS.UserBean;
import IPL_DAO.GeneralFlagDao;
import IPL_DAO.MatchCollectionDao;
import IPL_UTILITY.IplBidConnection;
import IPL_UTILITY.MyServerDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchCollectionLogic {

    MatchCollection matchCollectionBean;
    MatchCollectionDao matchCollectionDao;

    public MatchCollectionLogic() {
    }

    public MatchCollectionLogic(MatchCollection matchCollectionBean) {
        this.matchCollectionBean = matchCollectionBean;

    }

    public MatchCollection getMatchCollectionBean() {
        return matchCollectionBean;
    }

    public void setMatchCollectionBean(MatchCollection matchCollectionBean) {
        this.matchCollectionBean = matchCollectionBean;
    }

    public boolean closeConnection() {
        boolean check_flag = false;
        try {
            matchCollectionDao.stmt.close();
            matchCollectionDao.con.close();
            check_flag = true;
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:closeConnection: Exception occured while "
                    + "closing connection is : \n " + sqlException);
        }
        return check_flag;
    }

    public boolean openConnection() {
        boolean check_flag = false;
        matchCollectionDao = new MatchCollectionDao();
        matchCollectionDao.setCollectionBean(matchCollectionBean);
        try {
            matchCollectionDao.con = IplBidConnection.openConnection();
            matchCollectionDao.stmt = matchCollectionDao.con.createStatement();
            check_flag = true;
        } catch (Exception ex) {
            System.out.println("MatchCollectionLogic:openConnection: Error Occured while "
                    + "opening connection \n" + ex);
        }
        return check_flag;
    }

    public ArrayList<UserBean> getScoreBoardUsers() {
        ResultSet result = matchCollectionDao.getUserOpenMatchBidPoints();
        ArrayList<UserBean> scoreBoardList = new ArrayList<UserBean>();
        try {
            while (result.next()) {
                String user_type = result.getString("USER_TYPE");
                String user_status = result.getString("USER_STATUS");
                if (user_type.trim().equalsIgnoreCase("ADMIN") || user_status.trim().equalsIgnoreCase("INACTIVE") || user_status.trim().equalsIgnoreCase("APPLIED")) {
                    continue;
                }
                UserBean userBean = new UserBean();
                userBean.setUserID(result.getString("UID"));
                userBean.setUserBidPoints(result.getString("BID_POINTS"));
                userBean.setUserPoints(result.getString("USER_POINTS"));
                userBean.setUserName(result.getString("USER_NAME"));
                userBean.setUserFullname(result.getString("FULL_NAME"));
                userBean.setUserStatus(user_status);
                userBean.setUserEmail(result.getString("USER_EMAIL"));
                userBean.setUserTeam(result.getString("USER_TEAM"));
                scoreBoardList.add(userBean);
            }

        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getScoreBoardUsers: Exception occured while "
                    + "calculating user collection is : \n " + sqlException);
        }
        return scoreBoardList;
    }

    public String getUserEarnedPoints() {
        ResultSet result = matchCollectionDao.getUserEarnedPoints();
        String earnedCollection = "";
        try {
            while (result.next()) {
                earnedCollection = result.getString(1);
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getUserEarnedPoints: Exception occured while "
                    + "calculating user earned collection is : \n " + sqlException);
        }
        return earnedCollection;
    }

    public String getUserLosedPoints() {
        ResultSet result = matchCollectionDao.getUserLosedPoints();
        String losedCollection = "";
        try {
            while (result.next()) {
                losedCollection = result.getString(1);
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getUserLosedPoints: Exception occured while "
                    + "calculating user losed collection is : \n " + sqlException);
        }
        return losedCollection;
    }

    public int getTeamCollection() {
        int team_amount = 0;
        ResultSet result = matchCollectionDao.getTeamMatchCollection();
        try {
            while (result.next()) {
                team_amount = team_amount + result.getInt("AMOUNT");
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getTeamCollection: Exception occured while "
                    + "calculating team collection is : \n " + sqlException);
        }
        return team_amount;
    }

    public int getUserCollection() {
        int team_amount = 0;
        ResultSet result = matchCollectionDao.getUserMatchCollection();
        try {
            while (result.next()) {
                team_amount = team_amount + result.getInt("AMOUNT");
            }

        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getUserCollection: Exception occured while "
                    + "calculating user collection is : \n " + sqlException);
        }
        return team_amount;
    }

    public ArrayList<MatchCollection> getTeamCollectionList() {
        ResultSet result;
        GeneralFlagDao gd = new GeneralFlagDao();
        if (gd.getViewOnlyTeamStatus() && matchCollectionBean.getLoseTeamID().equals("SET")) {
            result = matchCollectionDao.getPerTeamMatchCollection();
        } else {
            result = matchCollectionDao.getTeamMatchCollection();
        }
        ArrayList<MatchCollection> collectionList = new ArrayList<MatchCollection>();
        try {
            while (result.next()) {
                MatchCollection colBean = new MatchCollection();
                colBean.setUserID(result.getString("USER_NAME"));
                colBean.setBidDate(result.getString("BID_DATE"));
                colBean.setBidTime(result.getString("BID_TIME"));
                colBean.setAmount(result.getString("AMOUNT"));
                colBean.setResultAmount(result.getString("RESULT_AMOUNT"));
                colBean.setColStatus(result.getString("COL_STATUS"));
                collectionList.add(colBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getTeamCollectionList: Exception occured while "
                    + "preparing team collection list is : \n " + sqlException);
        }
        return collectionList;
    }

    public ArrayList<MatchCollection> getUserCollectionList() {
        ResultSet result = matchCollectionDao.getUserMatchCollection();
        ArrayList<MatchCollection> collectionList = new ArrayList<MatchCollection>();
        try {
            while (result.next()) {
                MatchCollection colBean = new MatchCollection();
                colBean.setUserID(result.getString("USER_NAME"));
                colBean.setBidDate(result.getString("BID_DATE"));
                colBean.setBidTime(result.getString("BID_TIME"));
                colBean.setBidTeam(result.getString("TEAM_NAME"));
                colBean.setAmount(result.getString("AMOUNT"));
                colBean.setResultAmount(result.getString("RESULT_AMOUNT"));
                colBean.setColStatus(result.getString("COL_STATUS"));
                collectionList.add(colBean);
            }

        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getUserCollection: Exception occured while "
                    + "calculating user collection is : \n " + sqlException);
        }
        return collectionList;
    }

    public ArrayList<MatchCollection> getTotalMatchCollection() {
        openConnection();
        ResultSet result = matchCollectionDao.getCollectionDetailsForMatchID();
        ArrayList<MatchCollection> collectionList = new ArrayList<MatchCollection>();
        try {
            while (result.next()) {
                MatchCollection colBean = new MatchCollection();
                colBean.setCollectionID(result.getString("COLLECTION_ID"));
                colBean.setMatchID(result.getString("MATCH_ID"));
                colBean.setUserID(result.getString("USER_ID"));
                colBean.setBidTeam(result.getString("BID_TEAM"));
                colBean.setAmount(result.getString("AMOUNT"));
                colBean.setBidDate(result.getString("BID_DATE"));
                colBean.setBidTime(result.getString("BID_TIME"));
                colBean.setResultAmount(result.getString("RESULT_AMOUNT"));
                colBean.setColStatus(result.getString("COL_STATUS"));
                collectionList.add(colBean);
            }
            closeConnection();
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getUserCollection: Exception occured while "
                    + "calculating user collection is : \n " + sqlException);
        }
        return collectionList;
    }

    public int getUserPerMatchCollection() {
        int user_amount = 0;
        ResultSet result = matchCollectionDao.getUserTotalBidPointPerMatch();
        try {
            while (result.next()) {
                user_amount = result.getInt("Total_Point");
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getUserPerMatchCollection: "
                    + "Exception occured while "
                    + "calculating user per match collection is : \n " + sqlException);
        }
        return user_amount;
    }

    public int getUserBidTeam() {
        int bid_team = 0;
        ResultSet result = matchCollectionDao.getUserBidTeamPerMatch();
        try {
            while (result.next()) {
                bid_team = result.getInt("BID_TEAM");
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getUserBidTeam: Exception occured while "
                    + "getting user bid team : \n " + sqlException);
        }
        return bid_team;
    }

    public boolean saveCollectionDetails() {
        boolean check_flag = matchCollectionDao.insertMatchCollection();
        closeConnection();
        return check_flag;
    }

    public boolean deleteEXPCollection() {
        boolean check_flag = matchCollectionDao.deleteMatchCollectionEXP();
        closeConnection();
        return check_flag;
    }

    public String updateMatchCollectionForMatchDeclare() {
        String returnMessage = "";
        ArrayList<MatchCollection> collectionList = getTotalMatchCollection();
        Iterator colListIterator = collectionList.iterator();
        String WiningAmount;
        String losingAmount;
        openConnection();
        while (colListIterator.hasNext()) {
            MatchCollection matchCol = (MatchCollection) colListIterator.next();
            String bidID = matchCol.getBidTeam();
            UserBean userBean = new UserBean();
            userBean.setUserID(matchCol.getUserID());
            UserLogic userLogic = new UserLogic(userBean);
            String userBidAmount = matchCol.getAmount();
            int winAmount = 0;
            int losAmount = 0;
            int userAmount = Integer.parseInt(userBidAmount);
            if (bidID.trim().equalsIgnoreCase(matchCollectionBean.getWinTeamID())) {
                matchCol.setColStatus("GAIN");
                if (matchCollectionBean.getWinTeamID().trim().equalsIgnoreCase(matchCollectionBean.getTeam1ID())) {
                    WiningAmount = matchCollectionBean.getTeam1Amount();
                    losingAmount = matchCollectionBean.getTeam2Amount();
                    winAmount = Integer.parseInt(WiningAmount);
                    losAmount = Integer.parseInt(losingAmount);
                } else {
                    losingAmount = matchCollectionBean.getTeam1Amount();
                    WiningAmount = matchCollectionBean.getTeam2Amount();
                    winAmount = Integer.parseInt(WiningAmount);
                    losAmount = Integer.parseInt(losingAmount);
                }
                System.out.println(userAmount + " " + winAmount + " " + losAmount);
                double resultAmount = ((((double) userAmount / winAmount)) * losAmount);
                matchCol.setResultAmount(Double.toString(resultAmount));
                int userAvBalance = userLogic.getUserPoints();
                double newUserBalance = userAvBalance + resultAmount;
                userBean.setUserPoints(Double.toString(newUserBalance + Double.parseDouble(userBidAmount)));
                userLogic = new UserLogic(userBean);
                matchCollectionDao.setCollectionBean(matchCol);
                if (matchCollectionDao.updateResultAndStatusAmount()) {
                    if (userLogic.updateUserPoints()) {
                        TransactionBean trxBean = new TransactionBean();
                        trxBean.setUserID(userBean.getUserID());
                        trxBean.setTrxType("CREDIT");
                        trxBean.setTrxTitle("BIDING-GAIN#M" + matchCol.getMatchID());
                        trxBean.setTrxTime(MyServerDateTime.getServerTime());
                        trxBean.setTrxDate(MyServerDateTime.getServerDate());
                        trxBean.setTrxAmount(Double.toString(resultAmount));
                        trxBean.setOpeningBalance(Integer.toString(userAvBalance));
                        trxBean.setClosingBalance(Double.toString(newUserBalance));
                        TransactionLogic trxLogic = new TransactionLogic(trxBean);
                        if (trxLogic.saveUserTransaction()) {
                            trxBean = new TransactionBean();
                            trxBean.setUserID(userBean.getUserID());
                            trxBean.setTrxType("CREDIT");
                            trxBean.setTrxTitle("BIDING-RETURN#M" + matchCol.getMatchID());
                            trxBean.setTrxTime(MyServerDateTime.getServerTime());
                            trxBean.setTrxDate(MyServerDateTime.getServerDate());
                            trxBean.setTrxAmount(userBidAmount);
                            trxBean.setOpeningBalance(Double.toString(newUserBalance));
                            trxBean.setClosingBalance(Double.toString(newUserBalance + Double.parseDouble(userBidAmount)));
                            trxLogic = new TransactionLogic(trxBean);
                            if (trxLogic.saveUserTransaction()) {
                                returnMessage = returnMessage + "All Done well";
                            } else {
                                returnMessage = returnMessage + "Problem in updating transaction";
                            }
                        } else {
                            returnMessage = returnMessage + "Problem in updating transaction";
                        }
                    } else {
                        returnMessage = returnMessage + "Problem in updating user points";
                    }
                } else {
                    returnMessage = returnMessage + "Problem in updating match collection";
                }

            } else {
                matchCol.setColStatus("LOSS");
                matchCol.setResultAmount("0");
                matchCollectionDao.setCollectionBean(matchCol);
                if (matchCollectionDao.updateResultAndStatusAmount()) {
                } else {
                    returnMessage = returnMessage + "Problem in updating match collection in else";
                }
            }
        }
        closeConnection();
        if (returnMessage.toLowerCase().contains("problem")) {
            returnMessage = "Issue in User points update...!!";
        } else {
            returnMessage = "User Points updated Successfully";
        }
        return returnMessage;
    }

    public String updateDrawMatchCollectionForMatchDeclare() {
        String returnMessage = "";
        ArrayList<MatchCollection> collectionList = getTotalMatchCollection();
        Iterator colListIterator = collectionList.iterator();
        openConnection();
        while (colListIterator.hasNext()) {
            MatchCollection matchCol = (MatchCollection) colListIterator.next();

            UserBean userBean = new UserBean();
            userBean.setUserID(matchCol.getUserID());
            UserLogic userLogic = new UserLogic(userBean);
            String userBidAmount = matchCol.getAmount();
            double resultAmount = 0;
            int userAvBalance = userLogic.getUserPoints();
            double newUserBalance = userAvBalance + resultAmount;
            userBean.setUserPoints(Double.toString(newUserBalance + Double.parseDouble(userBidAmount)));
            userLogic = new UserLogic(userBean);
            matchCollectionDao.setCollectionBean(matchCol);
            if (matchCollectionDao.updateResultAndStatusAmount()) {
                if (userLogic.updateUserPoints()) {
                    TransactionBean trxBean = new TransactionBean();
                    trxBean.setUserID(userBean.getUserID());
                    trxBean.setTrxType("CREDIT");
                    trxBean.setTrxTitle("BIDING-RETURN#M" + matchCol.getMatchID());
                    trxBean.setTrxTime(MyServerDateTime.getServerTime());
                    trxBean.setTrxDate(MyServerDateTime.getServerDate());
                    trxBean.setTrxAmount(userBidAmount);
                    trxBean.setOpeningBalance(Double.toString(userAvBalance));
                    trxBean.setClosingBalance(Double.toString(userAvBalance + Double.parseDouble(userBidAmount)));
                    TransactionLogic trxLogic = new TransactionLogic(trxBean);
                    if (trxLogic.saveUserTransaction()) {
                        returnMessage = returnMessage + "All Done well";
                    } else {
                        returnMessage = returnMessage + "Problem in updating transaction";
                    }
                } else {
                    returnMessage = returnMessage + "Problem in updating transaction";
                }
            } else {
                returnMessage = returnMessage + "Problem in updating user points";
            }
        }
        closeConnection();
        if (returnMessage.toLowerCase().contains("problem")) {
            returnMessage = "Issue in User points update...!!";
        } else {
            returnMessage = "User Points updated Successfully";
        }

        return returnMessage;
    }

    public String provideBidUserBenefits(MatchGenealFlagBean matchGenealFlagBean) {
        String returnMessage = "";
        ArrayList<MatchCollection> collectionList = getTotalMatchCollectionForDistinctUser();
        Iterator<MatchCollection> listIteraor = collectionList.iterator();
        int totalBenefitBalance = deductNonBidUserPoints(matchGenealFlagBean);
        int totalUser = collectionList.size();
        int perPersonBenefit = totalBenefitBalance / totalUser;
        while (listIteraor.hasNext()) {
            MatchCollection matchCollection = listIteraor.next();
            UserBean userBean = new UserBean();
            userBean.setUserID(matchCollection.getUserID());
            UserLogic userLogic = new UserLogic(userBean);
            int availablePoints = userLogic.getUserPoints();
            int newBalance = availablePoints + perPersonBenefit;
            userBean.setUserPoints(Integer.toString(newBalance));
            userLogic = new UserLogic(userBean);
            if (userLogic.updateUserPoints()) {
                TransactionBean trxBean = new TransactionBean();
                trxBean.setUserID(userBean.getUserID());
                trxBean.setTrxType("CREDIT");
                trxBean.setTrxTitle("NBU-BENEFITS#M" + matchCollection.getMatchID());
                trxBean.setTrxTime(MyServerDateTime.getServerTime());
                trxBean.setTrxDate(MyServerDateTime.getServerDate());
                trxBean.setTrxAmount(Integer.toString(perPersonBenefit));
                trxBean.setOpeningBalance(Integer.toString(availablePoints));
                trxBean.setClosingBalance(Integer.toString(newBalance));
                TransactionLogic trxLogic = new TransactionLogic(trxBean);
                if (trxLogic.saveUserTransaction()) {
                    returnMessage = returnMessage + "All Done well";
                } else {
                    returnMessage = returnMessage + "Problem in updating transaction, but user points updated";
                    break;
                }
            } else {
                returnMessage = "User Points not updated";
                break;
            }
        }
        if (returnMessage.toLowerCase().contains("points")) {
            returnMessage = "Issue in NBU point update...!!";
        } else {
            returnMessage = "NBU Point updated Successfully";
        }
        return returnMessage;
    }

    public ArrayList<MatchCollection> getTotalMatchCollectionForDistinctUser() {
        openConnection();
        ResultSet result = matchCollectionDao.getDistinctBidUserForMatchID();
        ArrayList<MatchCollection> collectionList = new ArrayList<MatchCollection>();
        try {
            while (result.next()) {
                MatchCollection colBean = new MatchCollection();
                colBean.setMatchID(result.getString("MATCH_ID"));
                colBean.setUserID(result.getString("USER_ID"));
                collectionList.add(colBean);
            }
            closeConnection();
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:getTotalMatchCollectionForDistinctUser: Exception occured while "
                    + "preparing distinct bid user list: \n " + sqlException);
        }
        return collectionList;
    }

    public int deductNonBidUserPoints(MatchGenealFlagBean matchGenealFlagBean) {
        ArrayList<UserBean> nonBidList = prepareNonBidUserList();
        Iterator<UserBean> listIteraor = nonBidList.iterator();
        int nonBidCollection = 0;
        String returnMessage = "";
        while (listIteraor.hasNext()) {
            UserBean userBean = listIteraor.next();
            int available_point = Integer.parseInt(userBean.getUserPoints());

            int remaining_balance = 0;
            int transactionAmount = 0;
            int amountDeduct = Integer.parseInt(matchGenealFlagBean.getMatchNBUFineAmount());
            int percentageNBU = (int) Math.floor(Integer.parseInt(userBean.getUserPoints()) * ((Integer.parseInt(matchGenealFlagBean.getMatchNBUFinePercentage())) / 100));
            if (percentageNBU > amountDeduct) {
                amountDeduct = percentageNBU;
            }
            if (available_point > amountDeduct) {
                remaining_balance = available_point - amountDeduct;
                transactionAmount = amountDeduct;
            } else {
                remaining_balance = 0;
                transactionAmount = available_point;
            }
            nonBidCollection = nonBidCollection + transactionAmount;
            userBean.setUserPoints(Integer.toString(remaining_balance));
            UserLogic userLogic = new UserLogic(userBean);
            if (userLogic.updateUserPoints()) {
                TransactionBean trxBean = new TransactionBean();
                trxBean.setUserID(userBean.getUserID());
                trxBean.setTrxType("DEBIT");
                trxBean.setTrxTitle("NBU-FINE#M" + matchCollectionBean.getMatchID());
                trxBean.setTrxTime(MyServerDateTime.getServerTime());
                trxBean.setTrxDate(MyServerDateTime.getServerDate());
                trxBean.setTrxAmount(Integer.toString(transactionAmount));
                trxBean.setOpeningBalance(Integer.toString(available_point));
                trxBean.setClosingBalance(Integer.toString(remaining_balance));
                TransactionLogic trxLogic = new TransactionLogic(trxBean);
                if (trxLogic.saveUserTransaction()) {
                    returnMessage = returnMessage + "All Done well";
                } else {
                    returnMessage = returnMessage + "Problem in updating transaction, but user points updated";
                    break;
                }
            } else {
                returnMessage = "User Points not updated";
                break;
            }
        }
        System.out.println(returnMessage);
        return nonBidCollection;
    }

    public ArrayList<UserBean> prepareNonBidUserList() {
        openConnection();
        ArrayList<UserBean> nonBidList = new ArrayList<UserBean>();
        ResultSet result = matchCollectionDao.getUnBidUserDetails();
        try {
            while (result.next()) {
                UserBean userBean = new UserBean();
                userBean.setUserID(result.getString("USER_ID"));
                userBean.setUserPoints(result.getString("USER_POINTS"));
                nonBidList.add(userBean);
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:prepareNonBidUserList: Exception occured while "
                    + "preparing non bid user list : \n " + sqlException);
        }
        closeConnection();
        return nonBidList;
    }

    public boolean saveGraphData() {
        boolean check_flag = false;
        openConnection();
        ArrayList<MatchCollection> teamCollectList = prepareTeamCollectList();
        Iterator<MatchCollection> listIteraor = teamCollectList.iterator();
        while (listIteraor.hasNext()) {
            MatchCollection mcb = listIteraor.next();
            matchCollectionDao.setCollectionBean(mcb);
            check_flag = matchCollectionDao.insertDataForGraph();
            if (!check_flag) {
                break;
            }
        }
        closeConnection();
        return check_flag;
    }

    public ArrayList<MatchCollection> prepareTeamCollectList() {
        ArrayList<MatchCollection> teamCollectList = new ArrayList<MatchCollection>();
        ResultSet resultOut = matchCollectionDao.getTotalTeamCollection();
        try {
            while (resultOut.next()) {
                MatchCollection mcb = new MatchCollection();
                mcb.setMatchID(matchCollectionBean.getMatchID());
                mcb.setWinTeamID(resultOut.getString("TEAM_NAME"));
                mcb.setAmount(resultOut.getString("TEAM_POINT"));
                mcb.setResultAmount(resultOut.getString("TEAM_COUNT"));
                teamCollectList.add(mcb);
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchCollectionLogic:prepareTeamCollectList: Exception occured while "
                    + "prepraing team collection : \n " + sqlException);
        }
        return teamCollectList;
    }
}

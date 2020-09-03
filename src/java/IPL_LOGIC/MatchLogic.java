/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.MatchBean;
import IPL_BEANS.MatchCollection;
import IPL_BEANS.QuestionBean;
import IPL_BEANS.ResultBean;
import IPL_BEANS.UserBean;
import IPL_DAO.MatchDao;
import IPL_UTILITY.DateManipulation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchLogic {

    MatchBean matchBean;
    MatchDao matchDao;

    public MatchLogic() {
        matchDao = new MatchDao();
    }

    public MatchLogic(MatchBean matchBean) {
        this.matchBean = matchBean;
        matchDao = new MatchDao(this.matchBean);
    }

    public ArrayList<MatchBean> getAllMatchList() {

        ResultSet result = matchDao.getMatchList();
        ArrayList<MatchBean> matchList = new ArrayList<MatchBean>();
        try {

            while (result.next()) {
                MatchBean matchBean = new MatchBean();
                matchBean.setMatchID(result.getString("MATCH_ID"));
                matchBean.setTeam1ID(result.getString("TEAM1_ID"));
                matchBean.setTeam2ID(result.getString("TEAM2_ID"));
                matchBean.setMatchDay(result.getString("MATCH_DAY"));
                matchBean.setMatchDate(DateManipulation.convertInDisplayFormat(result.getString("MATCH_DATE")));
                matchBean.setMatchStatus(result.getString("MATCH_STATUS"));
                matchBean.setRoundType(result.getString("ROUND_TYPE"));
                matchBean.setStartTime(result.getString("START_TIME"));
                matchBean.setTeam1Name(result.getString("TEAM1_NAME"));
                matchBean.setTeam2Name(result.getString("TEAM2_NAME"));
                MatchCollection matchCollection = new MatchCollection();
                matchCollection.setBidTeam(matchBean.getTeam1ID());
                matchCollection.setMatchID(matchBean.getMatchID());
                matchCollection.setUserID(this.matchBean.getUserBean().getUserID());
                MatchCollectionLogic matchCollectionLogic = new MatchCollectionLogic(matchCollection);
                if (!matchCollectionLogic.openConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while opening "
                            + "collection dao connection");
                    throw new SQLException();
                }
                matchBean.setTeam1Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                matchCollection.setBidTeam(matchBean.getTeam2ID());
                matchCollectionLogic.setMatchCollectionBean(matchCollection);
                matchCollectionLogic.matchCollectionDao.setCollectionBean(matchCollection);
                matchBean.setTeam2Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                UserBean userBean = new UserBean();
                userBean.setUserID(this.matchBean.getUserBean().getUserID());
                userBean.setUserBidPoints(Integer.toString(matchCollectionLogic.getUserCollection()));
                UserLogic userLogic = new UserLogic(userBean);
                userBean.setUserPoints(Integer.toString(userLogic.getUserPoints()));
                matchBean.setUserBean(userBean);
                if (!matchCollectionLogic.closeConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while closing "
                            + "collection dao connection");
                }
                matchList.add(matchBean);
            }
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getAllMatchList: Exception occured "
                    + "while preparing all matches list : \n " + sqlException);
        }
        return matchList;
    }

    public String saveMatchDetails() {
        String returnMessage;
        if (matchDao.insertMatchDetails()) {
            returnLastMatchID();
            if (matchDao.saveMatchResult()) {
                QuestionBean questionBean = new QuestionBean();
                questionBean.setMatchID(matchBean.getMatchID());
                QuestionLogic questionLogic = new QuestionLogic(questionBean);
                if (questionLogic.assignMatchQuestions()) {
                    returnMessage = "Match created successfully";
                } else {
                    returnMessage = "Match created successfully, result details saved, but questions are not assigned";
                }
            } else {
                returnMessage = "Match Details saved successfully, but result details not saved";
            }
        } else {
            returnMessage = "Due to some technical issue, match details cannot be saved";
        }
        try {
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:saveMatchDetails: Exception occured "
                    + "while closing connection is : \n " + sqlException);
        }
        return returnMessage;
    }

    public void returnLastMatchID() {
        ResultSet result = matchDao.getLastInsertedMatchID();
        try {
            while (result.next()) {
                String matchID = result.getString(1);
                matchBean.getResultBean().setMatchID(matchID);
                matchBean.setMatchID(matchID);
            }
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:returnLastMatchID: Exception occured "
                    + "while preparing last inserted match id : \n " + sqlException);
        }
    }

    public String updateMatchStatus() {
        String returnMessage;
        if (matchDao.updateMatchStatus()) {
            returnMessage = "Match Status udated as " + matchBean.getMatchStatus();
        } else {
            returnMessage = "Due to some technical issue, match status cannot be updated";
        }
        try {
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:updateMatchStatus: Exception occured "
                    + "while closing connection is : \n " + sqlException);
        }
        return returnMessage;
    }

    public ArrayList<MatchBean> getClosedMatchList() {
        ResultSet result = matchDao.getClosedMatch();
        ArrayList<MatchBean> matchList = new ArrayList<MatchBean>();
        try {
            while (result.next()) {
                MatchBean matchBean = new MatchBean();
                matchBean.setMatchID(result.getString("MATCH_ID"));
                matchBean.setTeam1ID(result.getString("TEAM1_ID"));
                matchBean.setTeam2ID(result.getString("TEAM2_ID"));
                matchBean.setMatchDay(result.getString("MATCH_DAY"));
                matchBean.setMatchDate(DateManipulation.convertInDisplayFormat(result.getString("MATCH_DATE")));
                matchBean.setMatchStatus(result.getString("MATCH_STATUS"));
                matchBean.setRoundType(result.getString("ROUND_TYPE"));
                matchBean.setStartTime(result.getString("START_TIME"));
                matchBean.setTeam1Name(result.getString("TEAM1_NAME"));
                matchBean.setTeam2Name(result.getString("TEAM2_NAME"));
                matchList.add(matchBean);
            }
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getOpenMatchList: Exception occured "
                    + "while preparing open matches list : \n " + sqlException);
        }
        return matchList;
    }

    public ArrayList<MatchBean> getClosedMatchDetails() {
        ResultSet result = matchDao.getClosedMatchDetailsByID();
        ArrayList<MatchBean> matchList = new ArrayList<MatchBean>();
        try {
            while (result.next()) {
                MatchBean matchBean = new MatchBean();
                matchBean.setMatchID(result.getString("MATCH_ID"));
                matchBean.setTeam1ID(result.getString("TEAM1_ID"));
                matchBean.setTeam2ID(result.getString("TEAM2_ID"));
                matchBean.setMatchDay(result.getString("MATCH_DAY"));
                matchBean.setMatchDate(DateManipulation.convertInDisplayFormat(result.getString("MATCH_DATE")));
                matchBean.setMatchStatus(result.getString("MATCH_STATUS"));
                matchBean.setRoundType(result.getString("ROUND_TYPE"));
                matchBean.setStartTime(result.getString("START_TIME"));
                matchBean.setTeam1Name(result.getString("TEAM1_NAME"));
                matchBean.setTeam2Name(result.getString("TEAM2_NAME"));
                MatchCollection matchCollection = new MatchCollection();
                matchCollection.setBidTeam(matchBean.getTeam1ID());
                matchCollection.setMatchID(matchBean.getMatchID());
                MatchCollectionLogic matchCollectionLogic = new MatchCollectionLogic(matchCollection);
                if (!matchCollectionLogic.openConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while opening "
                            + "collection dao connection");
                    throw new SQLException();
                }
                matchBean.setTeam1Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                matchCollection.setBidTeam(matchBean.getTeam2ID());
                matchCollectionLogic.setMatchCollectionBean(matchCollection);
                matchCollectionLogic.matchCollectionDao.setCollectionBean(matchCollection);
                matchBean.setTeam2Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                if (!matchCollectionLogic.closeConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while closing "
                            + "collection dao connection");
                }
                matchList.add(matchBean);
            }
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getOpenMatchList: Exception occured "
                    + "while preparing open matches list : \n " + sqlException);
        }
        return matchList;
    }

    public ArrayList<MatchBean> getResultDeclareMatchList() {
        ResultSet result = matchDao.getResultDeclareMatch();
        ArrayList<MatchBean> matchList = new ArrayList<MatchBean>();
        try {
            while (result.next()) {
                MatchBean matchBean = new MatchBean();
                matchBean.setMatchID(result.getString("MATCH_ID"));
                matchBean.setTeam1ID(result.getString("TEAM1_ID"));
                matchBean.setTeam2ID(result.getString("TEAM2_ID"));
                matchBean.setMatchDay(result.getString("MATCH_DAY"));
                matchBean.setMatchDate(DateManipulation.convertInDisplayFormat(result.getString("MATCH_DATE")));
                matchBean.setMatchStatus(result.getString("MATCH_STATUS"));
                matchBean.setRoundType(result.getString("ROUND_TYPE"));
                matchBean.setStartTime(result.getString("START_TIME"));
                matchBean.setTeam1Name(result.getString("TEAM1_NAME"));
                matchBean.setTeam2Name(result.getString("TEAM2_NAME"));
                matchList.add(matchBean);
            }
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getResultDeclareMatchList: Exception occured "
                    + "while preparing open matches list : \n " + sqlException);
        }
        return matchList;
    }

    public ArrayList<MatchBean> getDelcaredMatchDetails() {
        ResultSet result = matchDao.getDeclaredMatchDetailsByID();
        ArrayList<MatchBean> matchList = new ArrayList<MatchBean>();
        try {
            while (result.next()) {
                MatchBean matchBean = new MatchBean();
                matchBean.setMatchID(result.getString("MATCH_ID"));
                matchBean.setTeam1ID(result.getString("TEAM1_ID"));
                matchBean.setTeam2ID(result.getString("TEAM2_ID"));
                matchBean.setMatchDay(result.getString("MATCH_DAY"));
                matchBean.setMatchDate(DateManipulation.convertInDisplayFormat(result.getString("MATCH_DATE")));
                matchBean.setMatchStatus(result.getString("MATCH_STATUS"));
                matchBean.setRoundType(result.getString("ROUND_TYPE"));
                matchBean.setStartTime(result.getString("START_TIME"));
                matchBean.setTeam1Name(result.getString("TEAM1_NAME"));
                matchBean.setTeam2Name(result.getString("TEAM2_NAME"));
                ResultBean result_bean = new ResultBean();
                result_bean.setTeamID(result.getString("WIN_TEAM_ID"));
                matchBean.setResultBean(result_bean);
                MatchCollection matchCollection = new MatchCollection();
                matchCollection.setBidTeam(matchBean.getTeam1ID());
                matchCollection.setMatchID(matchBean.getMatchID());
                MatchCollectionLogic matchCollectionLogic = new MatchCollectionLogic(matchCollection);
                if (!matchCollectionLogic.openConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while opening "
                            + "collection dao connection");
                    throw new SQLException();
                }
                matchBean.setTeam1Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                matchCollection.setBidTeam(matchBean.getTeam2ID());
                matchCollectionLogic.setMatchCollectionBean(matchCollection);
                matchCollectionLogic.matchCollectionDao.setCollectionBean(matchCollection);
                matchBean.setTeam2Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                if (!matchCollectionLogic.closeConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while closing "
                            + "collection dao connection");
                }
                matchList.add(matchBean);
            }
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getOpenMatchList: Exception occured "
                    + "while preparing open matches list : \n " + sqlException);
        }
        return matchList;
    }

    public ArrayList<MatchBean> getOpenMatchList() {
        ResultSet result = matchDao.getOpenMatch();
        ArrayList<MatchBean> matchList = new ArrayList<MatchBean>();
        try {
            while (result.next()) {
                MatchBean matchBean = new MatchBean();
                matchBean.setMatchID(result.getString("MATCH_ID"));
                matchBean.setTeam1ID(result.getString("TEAM1_ID"));
                matchBean.setTeam2ID(result.getString("TEAM2_ID"));
                matchBean.setMatchDay(result.getString("MATCH_DAY"));
                matchBean.setMatchDate(DateManipulation.convertInDisplayFormat(result.getString("MATCH_DATE")));
                matchBean.setMatchStatus(result.getString("MATCH_STATUS"));
                matchBean.setRoundType(result.getString("ROUND_TYPE"));
                matchBean.setStartTime(result.getString("START_TIME"));
                matchBean.setTeam1Name(result.getString("TEAM1_NAME"));
                matchBean.setTeam2Name(result.getString("TEAM2_NAME"));
                MatchCollection matchCollection = new MatchCollection();
                matchCollection.setBidTeam(matchBean.getTeam1ID());
                matchCollection.setMatchID(matchBean.getMatchID());
                matchCollection.setUserID(this.matchBean.getUserBean().getUserID());
                MatchCollectionLogic matchCollectionLogic = new MatchCollectionLogic(matchCollection);
                if (!matchCollectionLogic.openConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while opening "
                            + "collection dao connection");
                    throw new SQLException();
                }
                matchBean.setTeam1Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                matchCollection.setBidTeam(matchBean.getTeam2ID());
                matchCollectionLogic.setMatchCollectionBean(matchCollection);
                matchCollectionLogic.matchCollectionDao.setCollectionBean(matchCollection);
                matchBean.setTeam2Collection(Integer.toString(matchCollectionLogic.getTeamCollection()));
                UserBean userBean = new UserBean();
                userBean.setUserID(this.matchBean.getUserBean().getUserID());
                userBean.setUserBidPoints(Integer.toString(matchCollectionLogic.getUserCollection()));
                UserLogic userLogic = new UserLogic(userBean);
                userBean.setUserPoints(Integer.toString(userLogic.getUserPoints()));
                matchBean.setUserBean(userBean);
                if (!matchCollectionLogic.closeConnection()) {
                    System.out.println("MatchLogic:getOpenMatchList: Exception occured while closing "
                            + "collection dao connection");
                }
                matchList.add(matchBean);
            }
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getOpenMatchList: Exception occured "
                    + "while preparing open matches list : \n " + sqlException);
        }
        return matchList;
    }

    public String getMatchStatus() {
        ResultSet result = matchDao.checkMatchStatus();
        String matchStatus = "";
        try {
            while (result.next()) {
                matchStatus = result.getString("MATCH_STATUS");
            }
            matchDao.stmt.close();
            matchDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getMatchStatus: Exception occured "
                    + "while getting match status : \n " + sqlException);
        }
        return matchStatus;
    }
    
    public boolean updateMatchResult()
    {
        return matchDao.updateMatchResultForMatchID();
    }
    
    public boolean updateDrawMatchResult()
    {
        return matchDao.updateDrawMatchResultForMatchID();
    }
}

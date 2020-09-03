/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.MatchGraphBean;
import IPL_DAO.MatchGraphDao;
import IPL_UTILITY.IplBidConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchGraphLogic {
    MatchGraphBean matchGraphBean;
    MatchGraphDao matchGraphDao;
    
    public MatchGraphLogic(MatchGraphBean matchGraphBean) {
        this.matchGraphBean = matchGraphBean;
        matchGraphDao = new MatchGraphDao(matchGraphBean);
    }

    public MatchGraphLogic() {
        matchGraphDao = new MatchGraphDao();
    }
    
    public boolean openConnection() {

        boolean check_flag = false;
        matchGraphDao = new MatchGraphDao(matchGraphBean);
        try {
            matchGraphDao.con = IplBidConnection.openConnection();
            matchGraphDao.stmt = matchGraphDao.con.createStatement();
            check_flag = true;
        } catch (Exception ex) {
            System.out.println("MatchGenealFlagLogic:openConnection: Error Occured while "
                    + "opening connection \n" + ex);
        }
        return check_flag;
    }

    public boolean closeConnection() {
        boolean check_flag = false;
        try {
            matchGraphDao.stmt.close();
            matchGraphDao.con.close();
            check_flag = true;
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionLogic:closeConnection: Exception occured while "
                    + "closing connection is : \n " + sqlException);
        }
        return check_flag;
    }
    
    public ArrayList<MatchGraphBean> getGraphDataByMatchId() {
        openConnection();
        ResultSet result = matchGraphDao.getGraphDataDeclaredMatch();
        ArrayList<MatchGraphBean> graphDataList = new ArrayList<MatchGraphBean>();
        try {
            while (result.next()) {
                MatchGraphBean matchGraphBean = new MatchGraphBean();
                matchGraphBean.setMatchId(result.getString("MATCH_ID"));
                matchGraphBean.setTeamId(result.getString("TEAM_ID"));
                matchGraphBean.setTeamAmount(result.getString("TEAM_AMOUNT"));
                matchGraphBean.setTeamCount(result.getString("TEAM_COUNT"));
                graphDataList.add(matchGraphBean);
            }
            closeConnection();
        } catch (SQLException sqlException) {
            System.out.println("MatchLogic:getResultDeclareMatchList: Exception occured "
                    + "while preparing open matches list : \n " + sqlException);
        }
        return graphDataList;
    }
}

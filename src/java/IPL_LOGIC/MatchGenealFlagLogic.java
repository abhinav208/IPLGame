/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.MatchGenealFlagBean;
import IPL_DAO.MatchGenealFlagDao;
import IPL_UTILITY.IplBidConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchGenealFlagLogic {

    MatchGenealFlagBean matchGenealFlagBean;
    MatchGenealFlagDao matchGenealFlagDao;

    public MatchGenealFlagLogic(MatchGenealFlagBean matchGenealFlagBean) {
        this.matchGenealFlagBean = matchGenealFlagBean;
        matchGenealFlagDao = new MatchGenealFlagDao(matchGenealFlagBean);
    }

    public MatchGenealFlagLogic() {
        matchGenealFlagDao = new MatchGenealFlagDao();
    }

    public boolean openConnection() {

        boolean check_flag = false;
        matchGenealFlagDao = new MatchGenealFlagDao(matchGenealFlagBean);
        try {
            matchGenealFlagDao.con = IplBidConnection.openConnection();
            matchGenealFlagDao.stmt = matchGenealFlagDao.con.createStatement();
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
            matchGenealFlagDao.stmt.close();
            matchGenealFlagDao.con.close();
            check_flag = true;
        } catch (SQLException sqlException) {
            System.out.println("BumperQuestionLogic:closeConnection: Exception occured while "
                    + "closing connection is : \n " + sqlException);
        }
        return check_flag;
    }

    public MatchGenealFlagBean getMatchFlagDetails() {
        openConnection();
        ResultSet result = matchGenealFlagDao.getMatchFlagDetailsByMatchType();
        try {
            while (result.next()) {
                matchGenealFlagBean = new MatchGenealFlagBean();
                matchGenealFlagBean.setMatchType(result.getString("match_type"));
                matchGenealFlagBean.setMatchMinBidAmount(result.getString("minimum_bid_amount"));
                matchGenealFlagBean.setMatchBidPercentage(result.getString("bid_percentage"));
                matchGenealFlagBean.setMatchBidBothSide(result.getString("bid_both_side"));
                matchGenealFlagBean.setMatchNBUFineAmount(result.getString("nbu_fine_amount"));
                matchGenealFlagBean.setMatchNBUFinePercentage(result.getString("nbu_fine_percentage"));
                matchGenealFlagBean.setMatchQuestionAmount(result.getString("question_correct"));
            }
            closeConnection();
        } catch (SQLException sqlException) {
            System.out.println("MatchGenealFlagLogic:getMatchFlagDetails: Exception occured "
                    + "while getting Match Flag Details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return matchGenealFlagBean;
    }
}

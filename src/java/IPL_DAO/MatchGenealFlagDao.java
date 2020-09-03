/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.MatchGenealFlagBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchGenealFlagDao {

    MatchGenealFlagBean matchGenealFlagBean;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public MatchGenealFlagDao() {
    }

    public MatchGenealFlagDao(MatchGenealFlagBean matchGenealFlagBean) {
        this.matchGenealFlagBean = matchGenealFlagBean;
    }

    public ResultSet getMatchFlagDetailsByMatchType() {
        String query = "SELECT * FROM match_general_flag "
                + "WHERE match_type='" + matchGenealFlagBean.getMatchType().trim() + "'";
        System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchGenealFlagDao:getMatchFlagDetailsByMatchType_DAO: Exception occured "
                    + "while getting Match Flag Details, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.MatchGraphBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhinav Kumar
 */
public class MatchGraphDao {

    MatchGraphBean matchGraphBean;
    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public MatchGraphDao() {
    }

    public MatchGraphDao(MatchGraphBean matchGraphBean) {
        this.matchGraphBean = matchGraphBean;
    }
    
    public ResultSet getGraphDataDeclaredMatch() {
        String query = "SELECT * FROM match_graph_data WHERE match_id = "+matchGraphBean.getMatchId();
        System.out.println(query);
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("MatchGraphDao:getGraphDataDeclaredMatch_DAO: Exception occured "
                    + "while fetching graph data list, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return result;
    }
}

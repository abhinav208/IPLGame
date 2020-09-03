/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_UTILITY.IplBidConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author abhinav
 */
public class GeneralFlagDao {

    public Statement stmt;
    public ResultSet result;
    public Connection con;

    public GeneralFlagDao() {
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("GeneralFlagDao:Default_Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public Boolean getRegistrationStatus() {
        Boolean registrationStatus = true;
        String query = "SELECT flag_status FROM general_flag WHERE flag_name = 'Registation_close'";
        try {
            result = stmt.executeQuery(query);
            while (result.next()) {
                if (result.getString("flag_status").equalsIgnoreCase("False")) {
                    registrationStatus = false;
                }
            }
            con.close();
        } catch (SQLException sqlException) {
            System.out.println("GeneralFlagDao:getRegistrationStatus: Exception occured "
                    + "while fetching applied Registration status, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return registrationStatus;
    }

    public Boolean getViewOnlyTeamStatus() {
        Boolean registrationStatus = true;
        String query = "SELECT flag_status FROM general_flag WHERE flag_name = 'View_only_team'";
        try {
            result = stmt.executeQuery(query);
            while (result.next()) {
                if (result.getString("flag_status").equalsIgnoreCase("False")) {
                    registrationStatus = false;
                }
            }
            con.close();
        } catch (SQLException sqlException) {
            System.out.println("GeneralFlagDao:getViewOnlyTeamStatus: Exception occured "
                    + "while fetching applied view only Team status, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return registrationStatus;
    }

    public String getBumperstatus() {
        String bumperStatus = "";
        String query = "SELECT flag_status FROM general_flag WHERE flag_name = 'Bumber_status'";
        try {
            result = stmt.executeQuery(query);
            while (result.next()) {
                bumperStatus = result.getString("flag_status");
            }
            con.close();
        } catch (SQLException sqlException) {
            System.out.println("GeneralFlagDao:getBumperstatus: Exception occured "
                    + "while fetching applied Bumper status, Please check below message "
                    + "for more details : \n" + sqlException);
        }
        return bumperStatus;
    }
}

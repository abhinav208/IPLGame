/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_UTILITY;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author abhinav
 */
public class IplBidConnection {

    public static Connection openConnection() {
        Connection con = null;
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/iplbidding";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, "root", "");
        } catch (Exception ex) {
            System.out.println("MyConnection:openConnection: Error in opening"
                    + " connection with DB is \n" + ex);
        }
        return con;
    }

    public static void main(String args[]) {
        IplBidConnection.openConnection();
    }
}

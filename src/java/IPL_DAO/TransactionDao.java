/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_DAO;

import IPL_BEANS.TransactionBean;
import IPL_UTILITY.IplBidConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author abhinav
 */
public class TransactionDao {

    public Statement stmt;
    public ResultSet result;
    public Connection con;
    TransactionBean trxBean;

    public TransactionDao() {
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("TransactionDao:Default_Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public TransactionDao(TransactionBean trxBean) {
        this.trxBean = trxBean;
        try {
            con = IplBidConnection.openConnection();
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("TransactionDao:Constructor: Error Occured while "
                    + "opening connection \n" + ex);
        }
    }

    public boolean insertUserTransaction() {
        boolean check_flag = false;
        try {
            String query = "INSERT INTO USER_TRX_HISTORY(USER_ID,TRX_DATE,TRX_TIME,TRX_TITLE,TRX_TYPE,TRX_AMOUNT,"
                    + "OPENING_BALANCE,CLOSING_BALANCE) VALUES(" + trxBean.getUserID().trim()
                    + ",'" + trxBean.getTrxDate().trim()
                    + "','" + trxBean.getTrxTime().trim()
                    + "','" + trxBean.getTrxTitle().toUpperCase().trim()
                    + "','" + trxBean.getTrxType().toUpperCase().trim()
                    + "'," + trxBean.getTrxAmount().trim()
                    + "," + trxBean.getOpeningBalance().trim()
                    + "," + trxBean.getClosingBalance().trim() + ")";
            System.out.println(query);
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("TransactionDao:insertUserTransaction: Error Occured while "
                    + "inserting transaction details \n" + sqlException);
        } catch (NullPointerException nullException) {
            System.out.println("TransactionDao:insertUserTransaction - NPEXP: Error Occured while "
                    + "inserting transaction details \n" + nullException);
        }
        return check_flag;
    }

    public ResultSet getUserTransactions() {
        String query = "SELECT * FROM USER_TRX_HISTORY WHERE USER_ID = " + trxBean.getUserID().trim()
                + " ORDER BY TRX_SEQ_NO ASC";
        try {
            result = stmt.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println("TransactionDao:getUserTransactions: Error Occured while "
                    + "fetching user transaction details \n" + sqlException);
        } catch (NullPointerException nullException) {
            System.out.println("TransactionDao:getUserTransactions - NPEXP: Error Occured while "
                    + "fetching user transaction details \n" + nullException);
        }
        return result;
    }

    public boolean deleteUserTransactionEXP() {
        boolean check_flag = false;
        try {
            String query = "DELETE FROM USER_TRX_HISTORY WHERE"
                    + " USER_ID = " + trxBean.getUserID().trim()
                    + " AND TRX_DATE = '" + trxBean.getTrxDate().trim()
                    + " ' AND TRX_TIME = '" + trxBean.getTrxTime().trim()
                    + " ' AND TRX_TITLE = '" + trxBean.getTrxTitle().trim()
                    + " ' AND TRX_TYPE = '" + trxBean.getTrxType().trim()
                    + " ' AND TRX_AMOUNT = " + trxBean.getTrxAmount().trim()
                    + " AND OPENING_BALANCE = " + trxBean.getOpeningBalance().trim()
                    + " AND CLOSING_BALANCE = " + trxBean.getClosingBalance().trim();
            if (stmt.executeUpdate(query) != 0) {
                check_flag = true;
            }
        } catch (SQLException sqlException) {
            System.out.println("TransactionDao:deleteUserTransactionEXP: Error Occured while "
                    + "deleting transaction details \n" + sqlException);
        } catch (NullPointerException nullException) {
            System.out.println("TransactionDao:deleteUserTransactionEXP - NPEXP: Error Occured while "
                    + "deleting transaction details \n" + nullException);
        }
        return check_flag;
    }
}

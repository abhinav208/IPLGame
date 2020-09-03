/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.TransactionBean;
import IPL_DAO.TransactionDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author abhinav
 */
public class TransactionLogic {

    TransactionDao transationDao;
    TransactionBean trxBean;

    public TransactionLogic() {
        transationDao = new TransactionDao();
    }

    public TransactionLogic(TransactionBean trxBean) {
        this.trxBean = trxBean;
        transationDao = new TransactionDao(this.trxBean);
    }

    public boolean saveUserTransaction() {
        boolean check_flag;
        check_flag = transationDao.insertUserTransaction();
        closeConnection();
        return check_flag;
    }

    public boolean closeConnection() {
        boolean check_flag = false;
        try {
            transationDao.stmt.close();
            transationDao.con.close();
            check_flag = true;
        } catch (SQLException sqlException) {
            System.out.println("TransactionLogic:closeConnection: Exception occured while "
                    + "closing connection is : \n " + sqlException);
        }
        return check_flag;
    }

    public ArrayList<TransactionBean> getUserTrxList() {
        ResultSet result = transationDao.getUserTransactions();
        ArrayList<TransactionBean> trxList = new ArrayList<TransactionBean>();
        try {
            while (result.next()) {
                TransactionBean trxBean = new TransactionBean();
                trxBean.setUserID(result.getString("USER_ID"));
                trxBean.setTrxType(result.getString("TRX_TYPE"));
                trxBean.setTrxTitle(result.getString("TRX_TITLE"));
                trxBean.setTrxTime(result.getString("TRX_TIME"));
                trxBean.setTrxDate(result.getString("TRX_DATE"));
                trxBean.setTrxAmount(result.getString("TRX_AMOUNT"));
                trxBean.setOpeningBalance(result.getString("OPENING_BALANCE"));
                trxBean.setClosingBalance(result.getString("CLOSING_BALANCE"));
                trxList.add(trxBean);
            }
            closeConnection();
        } catch (SQLException sqlException) {
            System.out.println("TransactionLogic:getUserTrxList: Error Occured while "
                    + "preparing user transaction details \n" + sqlException);
        } catch (NullPointerException nullException) {
            System.out.println("TransactionLogic:getUserTrxList - NPEXP: Error Occured while "
                    + "preparing user transaction details \n" + nullException);
        }
        return trxList;
    }

    public boolean deleteEXPUserTransaction() {
        boolean check_flag;
        check_flag = transationDao.deleteUserTransactionEXP();
        closeConnection();
        return check_flag;
    }
}

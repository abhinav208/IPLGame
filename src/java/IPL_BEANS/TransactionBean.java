/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

/**
 *
 * @author abhinav
 */
public class TransactionBean {

    String trxSeqNo;
    String userID;
    String trxDate;
    String trxTime;
    String trxTitle;
    String trxType;
    String trxAmount;
    String openingBalance;
    String closingBalance;

    public String getTrxSeqNo() {
        return trxSeqNo;
    }

    public void setTrxSeqNo(String trxSeqNo) {
        this.trxSeqNo = trxSeqNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(String trxTime) {
        this.trxTime = trxTime;
    }

    public String getTrxTitle() {
        return trxTitle;
    }

    public void setTrxTitle(String trxTitle) {
        this.trxTitle = trxTitle;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(String trxAmount) {
        this.trxAmount = trxAmount;
    }
}

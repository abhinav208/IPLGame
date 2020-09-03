/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_UTILITY;

import IPL_BEANS.UserBean;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author abhin
 */
public class MailFunction {
    Message msg;
    private String host;
    private String addressFrom;
    private String addressTo;
    private String subject;
    private String addressCC;
    private String id;
    private InternetAddress mailbox;
    private String endLine;

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAddressCC() {
        return addressCC;
    }

    public void setAddressCC(String addressCC) {
        this.addressCC = addressCC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InternetAddress getMailbox() {
        return mailbox;
    }

    public void setMailbox(InternetAddress mailbox) {
        this.mailbox = mailbox;
    }

    public String getEndLine() {
        return endLine;
    }

    public void setEndLine(String endLine) {
        this.endLine = endLine;
    }

    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "punemail.corp.amdocs.com");
        //props.put("mail.smtp.host","10.20.50.22");
        props.put("mail.smtp.socketFactory.port", "25");
        //props.put("host",host);
        return (Session.getInstance(props, null));

    }

    public void sendMail(String ccMailID, String toMailID, String mailSubject, String messageBody) {
        setAddressTo(toMailID);
        setAddressCC(ccMailID);
        Message msg = new MimeMessage(getSession());
        setAddressFrom("admin@iplbidhunt.com");
        try {
            msg.setFrom(new InternetAddress(getAddressFrom()));
            InternetAddress address[] = InternetAddress.parse(getAddressTo());
            InternetAddress addressCC[] = InternetAddress.parse(getAddressCC());
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.addRecipients(Message.RecipientType.CC, addressCC);
            msg.setSubject(mailSubject);
            msg.setSentDate(new Date());
            msg.setContent(messageBody, "text/html; charset=utf-8");
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendBIDMail(String toMailID, String matchID, String teamName, String amount, String openBalance, String closeBalance) {
       String ccMailID = "", mailSubject = "IPL Bid Confirmation";
       String messageBody="<html>"
               + "<head>"
               + "<style>"
               + "table{"
               + "  font-family: arial, sans-serif;"
               + "  "
               + "  width: 400px;}"
               + ""
               + "td{"
               + "  border: 1px solid #dddddd;"
               + "  text-align: left;"
               + "  padding: 8px;"
               + "  background-color: #dddddd;}"
               + ""
               + "th {"
               + "  border: 1px solid #dddddd;"
               + "  text-align: left;"
               + "  padding: 8px;"
               + "  background-color: #0000cc;"
               + "color: white;}"
               + "</style>"
               + "</head>"
               + "<body>"
               + "<table border='1' width=\"50px\">"
               + "<tr><th colspan=\"2\">Bid Confirmation</th></tr>"
               + "<tr><td>Match</td><td>"+matchID.trim()+"</td></tr>"
               + "<tr><td>Team</td><td>"+teamName.trim()+"</td></tr>"
               + "<tr><td>Amount</td><td>"+amount.trim()+"</td></tr>"
               + "<tr><td>Opening Balance</td><td>"+openBalance.trim()+"</td></tr>"
               + "<tr><td>Closing Balance</td><td>"+closeBalance.trim()+"</td></tr>"
               + "</table>"
               + "</body>"
               + "</html>";
       MailFunction mailfunction = new MailFunction();
       mailfunction.sendMail(ccMailID, toMailID, mailSubject, messageBody);
    }
    
    public void sendPasswordMail(UserBean userBean) {
       String ccMailID = "", mailSubject = "IPL Credentials";
       String messageBody="<html>"
               + "<head>"
               + "<style>"
               + "table{"
               + "  font-family: arial, sans-serif;"
               + "  "
               + "  width: 400px;}"
               + ""
               + "td{"
               + "  border: 1px solid #dddddd;"
               + "  text-align: left;"
               + "  padding: 8px;"
               + "  background-color: #dddddd;}"
               + ""
               + "th {"
               + "  border: 1px solid #dddddd;"
               + "  text-align: left;"
               + "  padding: 8px;"
               + "  background-color: #0000cc;"
               + "color: white;}"
               + "</style>"
               + "</head>"
               + "<body>"
               + "<table border='1' width=\"50px\">"
               + "<tr><th colspan=\"2\">IPL Credentials</th></tr>"
               + "<tr><td>Username</td><td>"+userBean.getUserName().trim()+"</td></tr>"
               + "<tr><td>Email</td><td>"+userBean.getUserEmail().trim()+"</td></tr>"
               + "<tr><td>Password</td><td>"+userBean.getUserPassword().trim()+"</td></tr>"
               + "</table>"
               + "</body>"
               + "</html>";
       MailFunction mailfunction = new MailFunction();
       mailfunction.sendMail(ccMailID, userBean.getUserEmail().trim(), mailSubject, messageBody);
    }
    
    public void sendBugMail(UserBean userBean, String issueSubject, String issueDesc) {
       String ccMailID = "", mailSubject = "IPL Bid Hunt Issue/Bug";
       String messageBody="<html>"
               + "<head>"
               + "<style>"
               + "table{"
               + "  font-family: arial, sans-serif;"
               + "  "
               + "  width: 400px;}"
               + ""
               + "td{"
               + "  border: 1px solid #dddddd;"
               + "  text-align: left;"
               + "  padding: 8px;"
               + "  background-color: #dddddd;}"
               + ""
               + "th {"
               + "  border: 1px solid #dddddd;"
               + "  text-align: left;"
               + "  padding: 8px;"
               + "  background-color: #0000cc;"
               + "color: white;}"
               + "</style>"
               + "</head>"
               + "<body>"
               + "<table border='1' width=\"50px\">"
               + "<tr><th colspan=\"2\">IPL Bid Hunt Issue/Bug</th></tr>"
               + "<tr><td>UserID</td><td>"+userBean.getUserID().trim()+"</td></tr>"
               + "<tr><td>Username</td><td>"+userBean.getUserName().trim()+"</td></tr>"
               + "<tr><td>Email</td><td>"+userBean.getUserEmail().trim()+"</td></tr>"
               + "<tr><td>Issue Subject</td><td>"+issueSubject+"</td></tr>"
               + "<tr><td>Issue</td><td>"+issueDesc+"</td></tr>"
               + "</table>"
               + "</body>"
               + "</html>";
       MailFunction mailfunction = new MailFunction();
       mailfunction.sendMail(ccMailID, "abhinav.kumar21@amdocs.com,SapanKumar.Patro@amdocs.com", mailSubject, messageBody);
    }
}

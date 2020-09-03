<%-- 
    Document   : admin_home
    Created on : 22 Apr, 2020, 8:05:21 PM
    Author     : Abhinav Kumar
--%>

<%@page import="IPL_BEANS.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="../Images/iplLogo.JPG" type="image/gif" sizes="16x16">
        <link rel="stylesheet" href="../Styles/iplStyles.css" type="text/css" />
        <link rel="stylesheet" href="../Styles/iplIndex.css" type="text/css" />
        <script src="../Styles/iplScript.js"></script>
    </head>
    <body style="margin-top: 0px; background-image: url(../Images/admin_bg.jpg); overflow-y: scroll;">
        <%
            UserBean authUser = (UserBean) session.getAttribute("adm325Object");
            if (authUser == null) {
                response.sendRedirect("../logout.jsp");
            }
        %>
        <div><%@include file="admin_menu.jsp"%></div>
        <div>
            <br/><br/>
            <table border="0" cellpadding="3" cellspacing="0" style="width: 80%; margin-bottom: 20px;" align="center">
                <tr>
                    <td>
                        <table border="1" cellpadding="3" cellspacing="0" style="width: 400px; margin-bottom: 20px; margin-top: 20px;" align="left" id="adminCreateTable">
                            <tr align="center">
                                <th colspan="2"><b>General Setting</b></th>
                            </tr>
                            <tr>
                                <td><b>Registration</b></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><b>View own team</b></td>
                                <td></td>
                            </tr>
                            

                            <tr align="center">
                                <td colspan="2"><input type="submit" value="Submit" class="button login_button"/></td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table border="1" cellpadding="3" cellspacing="0" style="width: 400px; margin-bottom: 20px; margin-top: 20px;" align="left" id="adminCreateTable">
                            <tr align="center">
                                <th colspan="2"><b>Bumper Question Setting</b></th>
                            </tr>
                            <tr>
                                <td><b>Status</b></td>
                                <td></td>
                            </tr>
                            

                            <tr align="center">
                                <td colspan="2"><input type="submit" value="Submit" class="button login_button"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table border="1" cellspacing="0" cellpadding="5" style="width: 1200px; margin-bottom: 20px; margin-top: 20px" align="center" id="adminTable">
                            <tr align="center">
                                <th colspan="9"><b>Match Setting</b></th>
                            </tr>
                            <tr align="center">
                                <td><b>Match Type</b></td>
                                <td><b>Min Bid Amount</b></td>
                                <td><b>Min Bid Percentage</b></td>
                                <td><b>Bid on both Side</b></td>
                                <td><b>NBU Amount</b></td>
                                <td><b>NBU Percentage</b></td>
                                <td><b>Question Amount</b></td>
                            </tr>
                            <tr align="center">
                                <td>League</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr align="center">
                                <td>Knock Out</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr align="center">
                                <td>Final</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr align="center">
                                <td colspan="7"><input type="submit" value="Submit" class="button login_button"/></td>
                            </tr>
                        </table>
                </tr>
            </table>
        </div>
    </body>
</html>

<%-- 
    Document   : index
    Created on : 17 Apr, 2020, 10:54:55 PM
    Author     : abhinav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IplBidHunt</title>
        <link rel="icon" href="Images/iplLogo.JPG" type="image/gif" sizes="16x16">
        <link rel="stylesheet" href="Styles/iplIndex.css" type="text/css" />
        <link rel="stylesheet" href="Styles/iplStyles.css" type="text/css" />
        <script src="Styles/iplScript.js"></script>
    </head>
    <body>
        <div style="height: 96vh; width: 98vw; background-image: url('Images/homePage.png'); background-size: cover">
            <div style="height: 5%; width: 85%; margin: auto; opacity: 0.5;"></div>
            <div style="height: 90%; width: 85%; margin: auto; border-radius: 15px; opacity: 0.85; background-color: white;">
                <div style="height: 100%; width: 60%; float: left;">
                    <div style="height: 25%; width: 20%; background-image: url('Images/iplLogo.JPG'); background-repeat: no-repeat; border-top-left-radius: 15px;"></div>
                    <div style="height: 63%; width: 90%; background-image: url('Images/frnt_img.jpg'); background-size: cover; background-repeat: no-repeat; margin: auto; border-radius: 10px;"></div>
                </div>
                <div id="div1" class="column2-div">
                    <div id="div2" class="column2-main-div">
                        <div class="column2-head-div">
                            <h3 class="h3-column2">
                                Log In
                            </h3>
                        </div>
                        <div class="column2-body-div">
                            <form name="LoginForm" action="CheckIPLLogin" method="POST" onsubmit="return validateLoginForm()"> 
                                <table style="width: 90%; padding-top: 20%;" cellpadding="5" cellspacing="0" border="0" align="center">
                                    <tr>
                                        <td class="login_table_label">Username</td>
                                    </tr>
                                    <tr>
                                        <td><input type="text" name="userName" class="login_text_box"/></td>
                                    </tr>
                                    <tr>
                                        <td class="login_table_label">Password</td>
                                    </tr>
                                    <tr>
                                        <td><input type="password" name="userPass" class="login_text_box"/></td>
                                    </tr>
                                    <tr align="right">
                                        <td><input type="submit" value="Login" class="button login_button"/></td>
                                    </tr>
                                </table>
                            </form>
                            <span class="span-column2" style="margin-left: 10px;"><p id="errorLoginMessage"></p></span>
                        </div>
                        <div class="column2-bottom-div">
                            <span class="span-column2">
                                Don't have an account? <a href="#" style="color: #007bff;" onclick="onClickCall()">Sign Up</a><br>
                                <a href="#" style="color: #007bff;" onclick="onClickCall3()">Forgot your password?</a>
                            </span>
                        </div>
                    </div>
                    <div id="div3" style="transform: rotateY(180deg);" class="column2-main-div">
                        <div class="column2-head-div">
                            <h3 class="h3-column2">
                                New User Registration
                            </h3>
                        </div>
                        <div class="column2-body-div">
                            <form name="RegistrationForm" action="UserRegistration" method="POST" onsubmit="return validateForm()">
                                <table style="width: 90%; padding-top: 10%;" cellpadding="5" cellspacing="0" border="0" align="center">
                                    <tr>
                                        <td class="login_table_label">Name</td>
                                        <td>
                                            <input type="text"  name="fullName" class="login_text_box" placeholder="Ram Singh"/></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="login_table_label">User Name</td>
                                        <td><input type="text"  name="uName" class="login_text_box" placeholder="ram123Singh"/></td>
                                    </tr>
                                    <tr>
                                        <td class="login_table_label">User Email</td>
                                        <td><input type="text"  name="email" class="login_text_box" placeholder="abc@amdocs.com"/></td>
                                    </tr>

                                    <tr>
                                        <td class="login_table_label">Team Name</td>
                                        <td><select class="login_text_box" name="userTeam">
                                                <option selected disabled>--Choose Team--</option>
                                                <option value="Chaukidaar">CHAUKIDAAR</option>
                                                <option value="Free_Man">FREE MAN</option>
                                                <option value="Staktimaan">STAKTIMAAN</option>
                                                <option value="I_MAN">i Man</option>
                                                <option value="IT_WO-MAN">IT Wo-man</option>
                                                <option value="ALL_ACES">ALL_ACES</option>
                                                <option value="Boxing_Bulls">Boxing Bulls</option>
                                                <option value="Shatranj_Ke_Khilari">Shatranj Ke Khilari</option>
                                                <option value="NA">NA</option>
                                            </select></td>
                                    </tr>
                                    <tr align="right">
                                        <td colspan="2"><input type="submit"  value="Register" class="button login_button"/></td>
                                    </tr>
                                </table>
                            </form>
                            <span class="span-column2" style="margin-left: 10px;"><p id="errorRegistrationMessage"></p></span>
                        </div>
                        <div class="column2-bottom-div">
                            <span class="span-column2">
                                Already have an account? <a href="#" style="color: #007bff;" onclick="onClickCall2()">Log In</a><br>
                            </span>
                        </div>
                    </div>
                    <div id="div4" style="transform: rotateX(180deg); display: none" class="column2-main-div">
                        <div class="column2-head-div">
                            <h3 class="h3-column2">
                                Recover username/password
                            </h3>
                        </div>
                        <div class="column2-body-div">
                            <form name="ForgetForm" action="forgetPassword" method="POST" onsubmit="return validateForgetForm()">
                                <table style="width: 90%; padding-top: 20%;" cellpadding="5" cellspacing="0" border="0" align="center">
                                    <tr>
                                        <td class="login_table_label">Enter Username or Email</td>
                                    </tr>
                                    <tr>
                                        <td><input type="text" name="forgetID" class="login_text_box"/></td>
                                    </tr>
                                    <tr align="right">
                                        <td><input type="submit" value="Submit" class="button login_button"/></td>
                                    </tr>
                                </table>
                            </form>
                            <span class="span-column2" style="margin-left: 10px;"><p id="errorForgetMessage"></p></span>
                        </div>
                        <div class="column2-bottom-div" >
                            <span class="span-column2">
                                Remember password/username <a href="#" style="color: #007bff;" onclick="onClickCall4()">Sign Up</a><br>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div style="height: 5%; width: 85%; margin: auto;"></div>
        </div>
        <%
            String displayMessage = (String) session.getAttribute("displayMessage");
            if (displayMessage != null) {
                session.removeAttribute("displayMessage");
        %>
        <div id="displayMessageDiv" class="modal">
            <div class="modal-content animate container">
                <h2 class="login_table_head"><b><%=displayMessage%></b></h2>
                <button type="button" onclick="document.getElementById('displayMessageDiv').style.display='none'" class="button login_button">Ok</button>
            </div>
        </div>
        <%}%>
    </body>
</html>

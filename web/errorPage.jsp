<%-- 
    Document   : errorPage
    Created on : 10 May, 2020, 8:10:17 PM
    Author     : Abhinav Kumar
--%>
<%@ page isErrorPage="true" %> 
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
                <div style="height: 100%; width: 100%; float: left;">
                    <div style="height: 25%; width: 10%; background-image: url('Images/iplLogo.JPG'); background-repeat: no-repeat; border-top-left-radius: 15px;float: left;"></div>
                    <div style="height: 90%; width: 90%; float: right;">
                        <h1>Sorry an exception occurred!</h1> 
                        Exception is: <%= exception %>  
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
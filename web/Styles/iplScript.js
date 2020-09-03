/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function validateForm() {
    var fullName = document.forms["RegistrationForm"]["fullName"];
    var uName = document.forms["RegistrationForm"]["uName"];
    var email = document.forms["RegistrationForm"]["email"];
    var userTeam = document.forms["RegistrationForm"]["userTeam"];
    var errorMessage="";
    
    if (fullName.value == "") {
        errorMessage+="&#8226; Name must be filled.";
        fullName.style.borderColor = "red";
        fullName.style.borderWidth = "1px";
    }
    else {
        fullName.style.borderColor = "";
        fullName.style.borderWidth = "";
    }
                
    if (uName.value == "") {
        errorMessage+="&#8226; username must be filled.";
        uName.style.borderColor = "red";
        uName.style.borderWidth = "1px";
    }
    else {
        uName.style.borderColor = "";
        uName.style.borderWidth = "";
    }
                
    if (email.value == "") {
        errorMessage+="&#8226; Email must be filled.";
        email.style.borderColor = "red";
        email.style.borderWidth = "1px";
    }
    else {
        email.style.borderColor = "";
        email.style.borderWidth = "";
    }
                
    if (userTeam.value == "--Choose Team--") {
        errorMessage+="&#8226; Team must be Selected.";
        userTeam.style.borderColor = "red";
        userTeam.style.borderWidth = "1px";
    }
    else {
        userTeam.style.borderColor = "";
        userTeam.style.borderWidth = "";
    }
    
    if (errorMessage != "") {
        document.getElementById("errorRegistrationMessage").innerHTML = errorMessage;
        return false;
    }
    return true;
}

function validateLoginForm() {
    var uName = document.forms["LoginForm"]["userName"];
    var uPass = document.forms["LoginForm"]["userPass"];
    var errorMessage="";
    
    if (uName.value == "") {
        errorMessage+="&#8226; Username must be filled.";
        uName.style.borderColor = "red";
        uName.style.borderWidth = "1px";
    }
    else {
        uName.style.borderColor = "";
        uName.style.borderWidth = "";
    }
                
    if (uPass.value == "") {
        errorMessage+="&#8226; Password must be filled.";
        uPass.style.borderColor = "red";
        uPass.style.borderWidth = "1px";
    }
    else {
        uPass.style.borderColor = "";
        uPass.style.borderWidth = "";
    }
    
    if (errorMessage != "") {
        document.getElementById("errorLoginMessage").innerHTML = errorMessage;
        return false;
    }
    return true;
}

function cleanRegistrationForm () {
    var fullName = document.forms["RegistrationForm"]["fullName"];
    fullName.style.borderColor = "";
    fullName.style.borderWidth = "";
    fullName.value="";
    var uName = document.forms["RegistrationForm"]["uName"];
    uName.style.borderColor = "";
    uName.style.borderWidth = "";
    uName.value="";
    var email = document.forms["RegistrationForm"]["email"];
    email.style.borderColor = "";
    email.style.borderWidth = "";
    email.value="";
    var userTeam = document.forms["RegistrationForm"]["userTeam"];
    userTeam.style.borderColor = "";
    userTeam.style.borderWidth = "";
    userTeam.value="--Choose Team--";
    document.getElementById("errorRegistrationMessage").innerHTML = "";
}

function cleanForgetForm () {
    var forgetId = document.forms["ForgetForm"]["forgetID"];
    forgetId.style.borderColor = "";
    forgetId.style.borderWidth = "";
    forgetId.value="";
    document.getElementById("errorForgetMessage").innerHTML = "";
}

function cleanLoginForm () {
    var uName = document.forms["LoginForm"]["userName"];
    var uPass = document.forms["LoginForm"]["userPass"];
    uName.style.borderColor = "";
    uName.style.borderWidth = "";
    uName.value="";
    uPass.style.borderColor = "";
    uPass.style.borderWidth = "";
    uPass.value="";
    document.getElementById("errorLoginMessage").innerHTML = "";
}

function onClickCall () {
    document.getElementById("div1").style.transform = "rotateY(180deg)";
    cleanRegistrationForm();
}
function onClickCall2 () {
    document.getElementById("div1").style.transform = "rotateY(0deg)";
    cleanLoginForm();
}
function onClickCall3 () {
    document.getElementById("div3").style.display = "None";
    document.getElementById("div4").style.display = "Block";
    document.getElementById("div1").style.transform = "rotateX(180deg)";
    cleanForgetForm();
}
function onClickCall4 () {
    document.getElementById("div3").style.display = "Block";
    document.getElementById("div4").style.display = "None";
    document.getElementById("div1").style.transform = "rotateX(0deg)";
    cleanLoginForm();
}

function validateForgetForm() {
    var forgetId = document.forms["ForgetForm"]["forgetID"];
    var errorMessage="";
    
    if (forgetId.value == "") {
        errorMessage+="&#8226; Username or Email must be filled.";
        forgetId.style.borderColor = "red";
        forgetId.style.borderWidth = "1px";
    }
    else {
        forgetId.style.borderColor = "";
        forgetId.style.borderWidth = "";
    }
    if (errorMessage != "") {
        document.getElementById("errorForgetMessage").innerHTML = errorMessage;
        return false;
    }
    return true;
}

function AllTeams()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("teamList").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetAllTeams",true);
    xmlhttp.send();
}

function AllBugs()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("bugList").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetAllUserBugs",true);
    xmlhttp.send();
}

function AllQuestion()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("questionList").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetAllQuestion",true);
    xmlhttp.send();
}

function AllBumperQuestion()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("bumperQuestionList").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetAllBumperQuestion",true);
    xmlhttp.send();
}

function updateDay(dateValue) {
    var d = new Date(dateValue)
    var weekday = new Array(7);
    weekday[0] =  "Sunday";
    weekday[1] = "Monday";
    weekday[2] = "Tuesday";
    weekday[3] = "Wednesday";
    weekday[4] = "Thursday";
    weekday[5] = "Friday";
    weekday[6] = "Saturday";

    var n = weekday[d.getDay()];
    document.getElementById("matchDay").value = n;
}

function AllMatches()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("matchList").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetAllMatches",true);
    xmlhttp.send();
}

function myFunction(idTable,idElement,elementNumber) {
    var input, filter, table, tr, td, i;
    input = document.getElementById(idElement);
    filter = input.value.toUpperCase();
    table = document.getElementById(idTable);
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[elementNumber];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }       
    }
}

function getClosedMatchDetails()
{
    var matchID = document.getElementById("matchSelect").value;
    if(matchID=="SELECT")
    {
        return;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("matchDetails").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetMatchDetailsToDeclareResult?matchID="+matchID,true);
    xmlhttp.send();
}

function popup(mylink, windowname) {
    if (! window.focus)return true;
    var href;
    if (typeof(mylink) == 'string') href=mylink; else href=mylink.href;
    window.open(href, windowname, 'width=800,height=600,scrollbars=yes');
    return false;
}

function validateChangePassword()
{
    var oldPassSystem = document.getElementById("oldPassSystem").value;
    var oldPass = document.getElementById("oldPass").value;
    var newPass = document.getElementById("newPass").value;
    var confPass = document.getElementById("confPass").value;
    if(newPass==null || newPass.trim()=="")
    {
        alert("Blank field is not allowed.");
        return false;
    }
    if(confPass==null || confPass.trim()=="")
    {
        alert("Blank field is not allowed.");
        return false;
    }
    if(oldPass.trim()!=oldPassSystem.trim())
    {
        alert("Old Password incorrect");
        return false;
    }
    if(newPass.trim()!=confPass.trim())
    {
        alert("Password Mismatch");
        return false;
    }
    if(newPass.trim().length < 8)
    {
        alert("Password must be at least 8 characters");
        return false;
    }
    if(newPass.trim().length > 15)
    {
        alert("Password max length is 15");
        return false;
    }
    if(newPass.trim()==oldPass.trim())
    {
        alert("No Update required , New password is same as old");
        return false;
    }
    return true;
}

function getDeclaredMatchDetails()
{
    var matchID = document.getElementById("matchSelect").value;
    var userTeam = document.getElementById("userTeam").value;
    if(matchID=="SELECT")
    {
        return;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("matchDetails").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetDeclaredMatchResult?userTeam="+userTeam+"&matchID="+matchID,true);
    xmlhttp.send();
}

function AssignBidPageValue()
{
    var matchID = document.getElementById("matchSelect").value;
    if(matchID=="SELECT")
    {
        return;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("matchDetails").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetOpenBidMatchDetails?matchID="+matchID,true);
    xmlhttp.send();
}

function bidCurtain() {
    var length = matchSelect.options.length;
    if(length==1)
    {
        document.getElementById("matchDetails").innerHTML = document.getElementById("curtainContent").innerHTML;
    }
}

function getGraphMatchDetails()
{
    var matchID = document.getElementById("matchSelect").value;
    if(matchID=="SELECT")
    {
        return;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            //document.getElementById("matchDetails").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetGraphMatchResult?matchID="+matchID,true);
    xmlhttp.send();
}

function getAdminGraphMatchDetails()
{
    var matchID = document.getElementById("matchSelect").value;
    if(matchID=="SELECT")
    {
        return;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            //document.getElementById("matchDetails").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","GetAdminGraphMatchResult?matchID="+matchID,true);
    xmlhttp.send();
}
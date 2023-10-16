<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.Date"%>
<%@page errorPage="webErrorPage.jsp" isErrorPage="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<!-- pop CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	
<fmt:requestEncoding value="UTF-8" />
<html>
<%@ page import="org.springframework.security.AuthenticationException"%>
<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<fmt:setLocale value="en_US" scope="page" />
<fmt:setBundle basename="resources.CommonLables_en_US"
	var="englishLabels" scope="application" />
<fmt:setBundle basename="resources.CommonLables_gu" var="gujaratiLabels"
	scope="application" />
<fmt:setBundle basename="resources.Constants" var="constant"
	scope="request" />



<%
	String flag = (String) request.getAttribute("captchaValidateFlag");
String userName = (String) request.getAttribute("userName");
String pwd = (String) request.getAttribute("pwd");
%>

<c:set value="<%=flag%>" var="flag"></c:set>
<c:set value="<%=userName%>" var="userName"></c:set>
<c:set value="<%=pwd%>" var="pwd"></c:set>

<fmt:message var="digitalSignature" key="ENABLE_DIGITAL_SIGNATURE"
	bundle="${constant}" />
<fmt:setBundle basename="resources.Project" var="project" scope="page" />

<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/login.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/taglib.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/keyboard.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/statusbar.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/exprcpt.css"/>' type="text/css" />

<script type="text/javascript"
	src='<c:url value="/script/login/statusbar.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/script/common/prototype.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/script/common/commonfunctions.js"/>'></script>
<script type="text/javascript"
	src="<c:url value="/script/login/frmUtils_1.0.js"/>"></script>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/themes/mahakosh.css"/>' />
<script type="text/javascript"
	src="<c:url value="/script/login/getLoginWindow.js"/>"></script>
<script type="text/javascript"
	src='<c:url value="/script/pensionpay/PensionersCorner.js"/>'></script>


<script type="text/javascript"
	src="<c:url value="/script/login/md5.js"/>"></script>

<script type="text/javascript">
	function getLoginPage() {
		openLoginWindow();

		window.open('', '_parent', '');
		win = top;
		self.opener = this;
		self.close();
	}

	function checkEnter() {
		var key;
		if (window.event)
			key = window.event.keyCode;
		else
			key = e.which;
		if (key == 13) {
			getLoginPage();
		}
	}

	function sitUnderMaintain(){
		alert("Page is Under Development");
	}
</script>

<script>

function startLoginToShalarth() {
	alert("hello i m roshaN");
	var urlToOpen = "./login.jsp?user=1";
	var mwname = 'IFMS';
		var dt = new Date();
		mwname = mwname + dt.getMilliseconds();
	
	var userName = document.getElementById('username').value;
	//alert("username is *****"+userName);
	var pwd = document.getElementById('password').value;
	//alert("pwd is *****"+pwd);
	var prop = 'width='+screen.availWidth+',height='+screen.availHeight+',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	var child= window.open(urlToOpen, mwname, prop);
	child.moveTo( 0, 0 );
	child.resizeTo(screen.availWidth, screen.availHeight);
	child.focus();

	document.forms[0].j_username.value=userName;
	document.forms[0].j_password.value=pwd;
	var pwd1=CryptoJS.MD5(pwd).toString();
	document.forms[0].j_password.value=pwd1;

	document.forms[0].btnSubmit.disabled = true ;
	showProgressbar_login('Signing in...<br>Please wait...');
	window.setTimeout('document.forms[0].submit();',500);
	//window.open('', '_parent', '');
	//win = top;
	//self.opener = this;
	//self.close();
}



function capLock(e)
{ 		
	kc = e.keyCode?e.keyCode:e.which;
	sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false); 
	
	
	//alert("the msg is" + document.getElementById('divErrorMsg').innerHTML+"|");			
	if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk)){				

		document.getElementById('divErrorMsg').innerHTML='Caps Lock is on. <br>';	
		document.getElementById('divErrorMsg').style.fontFamily='arial';
		document.getElementById('divErrorMsg').style.fontSize='15px';
		document.getElementById('divErrorMsg').style.fontWeight='900';
		document.getElementById('divErrorMsg').style.color='red';
		document.getElementById('divErrorMsg').style.display='inline';
		document.getElementById('divErrorMsg').style.visibility='visible';
	}  
	else if(document.getElementById('divErrorMsg').innerHTML.toLowerCase() == 
		'Caps Lock is on. <br>'.toLowerCase())
	{
		document.getElementById('divErrorMsg').style.visibility='hidden';
	}
} 
function checkEnterForLogin()
{
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
		key = e.which;
	if (key==13) {
		startLoginInShalarth();
	}
}

function forgotPassword1()
{
	//alert("hii");
	//document.getElementById('MainTable').style.visibility='hidden';
	//document.getElementById('loginControlsTD').style.display='none';
	//document.getElementById('MainTable').style.display='none';

	//document.getElementById("ForgotPwdTable").style.display='';




		showProgressbar_login('Please wait...<br>Your Request is in Progress.');
		var varLocale = "";
		if(document.forms[0].locale[0].checked == true) varLocale = document.forms[0].locale[0].value;
		else varLocale = document.forms[0].locale[1].value;
		document.forms[0].action = "hdiits.htm?viewName=acl-forgotPassword&locale="+varLocale;
		document.forms[0].submit();
}

function forgotPassword()
{
	showProgressbar_login('Please wait...<br>Your Request is in Progress.');

	/*var varLocale = "";
	
	if(document.forms[0].locale[0].checked == true) varLocale = document.forms[0].locale[0].value;
	else varLocale = document.forms[0].locale[1].value;	*/
	
	//document.forms[0].action = "hdiits.htm?viewName=acl-forgotPassword&locale="+varLocale;
	document.forms[0].action = "hdiits.htm?viewName=forgotPasswordRedirect";
	document.forms[0].submit();
}

function showProgressbar_login_page(message)
{

	if( !message ) message = 'Please wait<br>Your Request is in progress...';
	disableBackground_login_page();
	var statusBar =	document.getElementById('statusbar');
	var statusbarwidth = 360;
	var statusbarheight = 100;
	statusBar.style.visibility = 'visible';
	document.getElementById('statuBarTd1').innerHTML = message;
	statusBar.style.left = ((document.body.offsetWidth - statusbarwidth)/2)+'px';
	var st = document.documentElement.scrollTop;
	var sh = document.documentElement.scrollHeight;
	var ch = document.documentElement.clientHeight;
	statusBar.style.top = (st + ((ch - statusbarheight)/2))+'px';
	statusBar.style.top = ( (sh/2) - (statusbarheight/2) )+'px';
}
function disableBackground_login_page()
{
	var st = document.documentElement.scrollTop;
	var sh = document.documentElement.scrollHeight;
	var ch = document.documentElement.clientHeight;
}
function enableBackground_login()
{

}
function backToLogin()
{
	document.getElementById('loginControlsTD').style.display='';
	document.getElementById('MainTable').style.display='';

	document.getElementById("ForgotPwdTable").style.display='none';
}


//function goToCOntroller()
//{
//alert("hiii");
//}

function goToCOntr44oller()
{
 var ajaxRequest;  // The variable that makes Ajax possible!
	alert("hiii");
 try{
   		// Opera 8.0+, Firefox, Safari
   		ajaxRequest = new XMLHttpRequest();
   		alert("hiii");
 	}
 catch (e)
 	{
   		// Internet Explorer Browsers
   		try{
      			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
   			}
   			catch (e) 
   			{
      			try{
         			ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
      				}
      			catch (e)
      				{
         				// Something went wrong
         				alert("Your browser broke!");
         				return false;
      				}
   			}		
 	}
 alert("hiii333");
 ajaxRequest.onreadystatechange = function()
 {
   	if(ajaxRequest.readyState == 4)
   	{
      var ajaxDisplay = document.getElementById('div1');
      ajaxDisplay.innerHTML = ajaxRequest.responseText;
   }
 }
 alert("hiii333");
 var action="getCaptcha";
 var queryString = "?action=" + action ;
 ajaxRequest.open("post", "http://localhost:8080/dcps/CaptchaController" +queryString, true);
 ajaxRequest.send(null); 
}


function goToCOntroller()
{
	document.getElementById('captcha_code').value='';
	 var text = "";
	 var possible = "023456789";
	//ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz
	 for( var i=0; i < 6; i++ )
	    text += possible.charAt(Math.floor(Math.random() * possible.length))+' ';

   // var g = Math.ceil(Math.random() * 10)+ '';  
   	var code = text;
   //var code="1"; 
    document.getElementById("txtCaptcha").innerHTML = code;
    document.getElementById("captcha").value=code;
    
  //  document.getElementById("txtCaptcha").style.color  = "red";
   // alert(code.split(' ').join('').length);
    document.getElementById("capLength").value = code.split(' ').join('').length;
}
function ValidCaptcha(){
	//alert("hiii");
	 //var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
	document.getElementById('divCaptchaMsg').style.visibility='hidden';
    var str1 = document.getElementById('captcha').value;
    var str2 = document.getElementById('captcha_code').value;
   
    str1=str1.split(' ').join('');
    str2=str2.split(' ').join('');
    var str1Len=document.getElementById('capLength').value;
    //alert("str1Len"+str1Len);
    var str2Len = str2.length;
   // alert("str2Len"+str2Len);
    //alert("string 1 is*******"+str1);
    //alert("str2 2 is*******"+str2);
	if(str2Len >=str1Len){
    
    if (str1 == str2) {
        return true;
        document.getElementById('divCaptchaMsg').style.visibility='hidden';
    }

    else{
    	document.getElementById('captcha_code').value='';
    	document.getElementById('divCaptchaMsg').innerHTML='Captcha not Matched. <br>';	
		document.getElementById('divCaptchaMsg').style.fontFamily='arial';
		document.getElementById('divCaptchaMsg').style.fontSize='13px';
		document.getElementById('divCaptchaMsg').style.fontWeight='900';
		document.getElementById('divCaptchaMsg').style.color='red';
		document.getElementById('divCaptchaMsg').style.display='inline';
		document.getElementById('divCaptchaMsg').style.visibility='visible';
        goToCOntroller();   
   		return false;
    } 
   }
}



function resetAll(){

	document.getElementById('username').value='';
	document.getElementById('password').value='';
	document.getElementById('captcha_code').value='';
	//goToCOntroller();
}



function getDashBoard()
{
	showProgressbar_login('Please wait...<br>Your Request is in Progress.');

	document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1";
	document.forms[0].submit();
}
function validateCaptcha()
{
	//showProgressbar_login('Please wait...<br>Your Request is in Progress.');
	//alert("helloooo");
	document.forms[0].action = "hdiits.htm?viewName=captchaRedirect&executeFlag=1";
	document.forms[0].submit();
	
}


function refresh()
{
	//alert("hiiii inside refresh");
document.getElementById("captchaImageNew").src="./CaptchaServlet?"+Math.random();

}


</script>
<style type="text/css">
.textboxSTyle {
	border: 1px solid #848484;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	border-radius: 30px;
	outline: 0;
	height: 25px;
	width: 275px;
	padding-left: 10px;
	padding-right: 10px;
}

.input_img {
	background-image: url("images/bgcolor.gif");
	text-align: center;
	border: medium none;
	font-weight: bold;
	color: red;
	font-family: arial;
	height: 27px;
	width: 135px;
	font-size: 20px;
}

.align {
	margin: 0 auto;
	width: 250px;
}

.bigbutton {
	padding: 2px;
	background: #FFF url(themes/ifmsblue/images/wintop.jpg) repeat;
	border: 1px solid black;
	color: white;
	font-family: 'arial';
	font-weight: bold;
	font-size: 11px;
}

.tabstyle {
	border-width: 5px 1px 1px 1px;
	border-color: #c06f20; /*change*/
	border-style: solid;
	padding: 0px;
}

#MainTable {
	/* background-color: burlywood; */
	/* background-image: linear-gradient(60deg, #a9c3c7, #cebae1); */
	/* background-image: linear-gradient(0deg, #ccc, #b2ad6b); */
	background-image: linear-gradient(0deg, #fff, #ff8e00);
	/* 		background-image: linear-gradient(180deg, #fff, #ff8e00); */
}

.inputField {
	height: 24px !important;
	border: 10px;
	border-radius: 5px;
	box-shadow: 0px 0px 2px #817d7d;
}

.noticeText {
	border: 1px solid #00137b;
	border-radius: 5px;
	padding: 2px;
}

.FaqBtn {
	padding: 2px 10px;
	font-size: 10px;
	text-decoration: none !important;
}

body * {
	font-family: arial;
}

.tbl-h {
	font-size: 16px;
}

.sideBarNotice {
	background-color: #824000;
}
</style>

<style>
.blink {
	animation: blinker 0.6s linear infinite;
	color: #1c87c9;
}
blink {
	animation: blinker 0.6s linear infinite;
	color: #1c87c9;
}

@keyframes blinker { 50% {
	opacity: 0;
}

}
.blink-one {
	animation: blinker-one 1s linear infinite;
}

@keyframes blinker-one { 0% {
	opacity: 0;
}

}
.blink-two {
	animation: blinker-two 1.4s linear infinite;
}

@keyframes blinker-two { 100% {
	opacity: 0;
}
}
</style>


<%
	String notice = request.getParameter("n");
%>
<c:set value="<%=notice%>" var="notice"></c:set>





<body onbeforeunload="Close()" onunload="HandleOnClose()" onload=""
	oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false">
	<form name="loginForm" autocomplete="off"
		action="<c:url value='j_spring_security_check'/>" method="POST">

		<input type="hidden" value="${flag}" id="flag"> <input
			type="hidden" value="${userName}" id="userName"> <input
			type="hidden" value="${pwd}" id="pwd">



		<c:if test="${notice == null}">

			<center>
				<br /> <br /> <br />
				<fieldset class="tabstyle"
					style="width: 85%; background-color: #E4E5ED;">
					<table id="MainTable" width="100%" border="0">
						<tr>
							<td colspan="3">
								<!--<div class="firsttext">Integrated Financial Management System - Mahakosh </div>
		     <div class="secondtext">Finance Department, Government of Maharashtra</div>-->
								<img src="images/HomePageImages/FianlHomePG_1_11.jpg" width=100%
								style="background-repeat: no-repeat;"></img>
							</td>
						</tr>
						<!-- <tr>
	  <td colspan="3"><div><font color="red"> Important Notice : Please untick component GIS Arrear recovery from Employee Eligibility for Allowances and Deductions screen in Assistant login and tick to
					GIS Arrear on the same screen. Last date for this is 16th December 2013.</font></div><br/> 
	</td>
	</tr> -->
						<tr>
							<td colspan="3" style="background-color: #ffe8ca;"><marquee
									style="font-size: small" width="100%">
									<b> <font style="font-family: arial; color: #a50000;">
											Notice:- Revision of 6th to 7th PC has been started. for more
											details click on "Revision 6th PC to 7th PC User
											Manual".(सहाव्या ते सातव्या पीसीचे पुनरावलोकन सुरू झाले आहे.
											अधिक माहितीसाठी रिव्हिजन 6 व्या पीसी ते 7 व्या पीसी यूजर
											मॅन्युअल वर क्लिक करा.)</font>
									</b>
								</marquee></td>

						</tr>
						<tr class="sideBarNotice">
							<td style="width: 30%" align='center' class="tbl-h ">HTESevaarth
								Login</td>
							<td style="width: 40%" align='center' class="tbl-h ">About
								HTESevaarth</td>
							<td style="width: 30%;" align='center' class="tbl-h ">Notice
								Board</td>
						</tr>

						<tr>

							<td style="width: 30%" align="left" bordercolor="#c06f20"
								valign="top">
								<div style="overflow-y: auto; height: 250px">

									<ul>
										<table>
											<tr></tr>
											<tr></tr>
											<tr>
												<td colspan="6" align="center"><font
													style="font-size: 13px; font-weight: bold; color: #FF3333;">
														<%
															session.setAttribute("mainFunc", "SetNormalWin");
														// error1 = Your IP Address is blocked.
														String errors[] = request.getParameterValues("error");
														if (errors != null && errors.length > 0) {
															for (int x = 0; x < errors.length; x++) {
																if (errors[x].equals("error1")) {
															out.print("Your IP Address is blocked.");
																}
															}
														} else {
															AuthenticationException authenticationException = (AuthenticationException) session
															.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);

															if (authenticationException != null) {
														%> <%=authenticationException.getMessage()%> <%
 	}
 }
 %>
												</font></td>
											</tr>
											<tr>
												<td><input type="hidden" name="capLength"
													id="capLength"> <input type="hidden" name="captcha"
													id="captcha"></td>
											</tr>
											<!--<tr>
			<td>Captcha</td>
			<td><img src="./CaptchaServlet">session.getAttribute("captcha123")3") %> </td>
			</tr>
			
			-->
											<tr>


											</tr>
											<tr>

												<td><span style="font-size: 13px; font-weight: bold;">User
														Name</span></td>
												<td><input class="inputField" type="text" id="username"
													value="" name="username" color="black" maxlength="18"
													style="width: 136px; height: 17px; border: 10px;"
													onkeypress="javascript: checkEnterForLogin();"></td>
											</tr>
											<tr>
											</tr>
											<tr>
												<td><span style="font-size: 13px; font-weight: bold;">Password</span></td>
												<td><input type="password" id="password"
													name="password" maxlength="16" value=""
													class="keyboardInput inputField"
													style="width: 136px; height: 17px; border: none;"
													onkeypress="capLock(event);checkEnterForLogin();"></td>
											</tr>

											<tr>
												<td><span style="font-size: 13px; font-weight: bold;">Captcha</span></td>
												<td><img id="captchaImageNew" src="./CaptchaServlet"><img
													src="images/rr.gif" onclick="refresh();"
													title="Click here to refresh the image"></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td><input type="text" id="captcha_code" onblur=""
													name="captcha_code" title="Enter Captcha here."
													class="required inputField" value=""
													style="width: 136px; height: 17px; border: 10px;" /></td>
											</tr>

											<tr>
												<td></td>
												<td>
													<div id="divCaptchaMsg">
														<font color="yellow"
															style="font-family: Arial; font-size: 11px;"> <b><strong><label
																	id="errorLabel"> </label></strong></b></font>
													</div>
												</td>
											</tr>

											<tr style="">
												<td colspan="5"><font class="Labelerrormsg"><a
														href="#"
														style="padding-left: 80px; text-decoration: none; color: black; font-size: 13px; font-weight: bold;"
														onclick="alert('Please contact support staff to update password');">
															Forgot Password </a></font></td>
											</tr>
											<tr>
												<td></td>
												<td>
													<div id="divErrorMsg">
														<font color="yellow"
															style="font-family: Arial; font-size: 11px;"> <b><strong><label
																	id="errorLabel"> </label></strong></b></font>
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="3" align="center"><input type="button"
													value="Submit" name="btnSubmit" class="buttontag"
													onclick="validateCaptcha();"> <input type="button"
													value="Reset" class="buttontag"
													onclick="javascript : resetAll();"></td>
											</tr>

										</table>
									</ul>



								</div>



							</td>
							<td
								style="width: 40%; height: 250px; text-align: justify; padding-top: 20px; font-weight: bold; font-size: 14px; font-family: arial; color: #442200;"
								align="justify" bordercolor="#c06f20" valign="top">
								HTESevaarth is a centralized web based Integrated System of
								personnel information and Payroll for Grant-in-Aid Institution
								in Maharashtra. It is an important component of IFMS
								(Intregrated Finance Management System) with facility for data
								exchange with other important modules of Directorate of Accounts
								and Treasuries. It is the first step in the direction of
								achieving the aim of paper less electronic payroll system i.e.
								paybill generation, electronic submission, electronic audit and
								electronic payment to employees along with e-payslips. <br /> <br />
								<center>
									<span>View FAQs in</span> <a href="images/FAQ_English.pdf"
										target="_blank" class="buttontag FaqBtn">English</a> <a
										href="images/FAQ_marathi.pdf" target="_blank"
										class="buttontag FaqBtn">मराठी</a>
								</center>
							</td>
							<td style="width: 22%" bordercolor="#c06f20" valign="middle">
								<marquee direction="up" SCROLLDELAY=6 scrollamount="2.5"
									behavior="scroll" onmouseover="stop()" onmouseout="start()">
									<ul>

										<li class="noticeText"><h4><font color="#736AFF" style="font-style: verdana"><b>Note - Update CSRF Form and Nps File Generate tab is available in respective JD under " Worklist  > NPS > Pran Registration " </b></font></h4></li>

<li><h4><font color="#736AFF" style="font-style: verdana"><b>Note - Update Pran No tab is available in respective JD under " Worklist  > NPS > Pran Registration " </b></font></h4></li>

										<li class="noticeText">
											
												<font color="#736AFF" style="font-style: verdana"><b>Note
														- Revision of 6th to 7th PC has been started. for more
														details click on "Revision 6th PC to 7th PC User
														Manual".(सहाव्या ते सातव्या पीसीचे पुनरावलोकन सुरू झाले
														आहे. अधिक माहितीसाठी रिव्हिजन 6 व्या पीसी ते 7 व्या पीसी
														यूजर मॅन्युअल वर क्लिक करा.)</b></font>
											

										</li>






									</ul>

									<span> </span>
									</div>
								</marquee>
							</td>



						</tr>
						<tr class="sideBarNotice">
							<td style="width: 30%" align='center'>7th Pay Comission</td>
							<td style="width: 40%" align='center'>Important Links/Level
								Matrix</td>
							<td style="width: 30%;" align='center'>Useful Documents</td>




						</tr>
						<tr align="left">
							<td style="width: 30%" bordercolor="#c06f20" valign="top">
								<ul>
									<li>
										<!-- 				<a href="images/Support_Staff_Link_7.pdf" target="_blank" >Support Staff Link</a> -->
										<a href="images/Revision_6PC_To_7PC_HTE_Sevaarth.pdf"
										target="_blank" style="font-size: 14px; font-weight: bold;" class="blink">Revision
												6th PC to 7th PC User Manual</a>
									</li>
									<!--<li>
 			<a href="images/MasterTrainers.pdf"target="_blank" style="font-size: 12px; font-weight: bold;">District Master Trainers Support Staff Team</a> 
			</li> -->

								</ul>
							</td>
							<td style="width: 30%" bordercolor="#c06f20" valign="top">

								<ul>

									<li><a href="http://finance.maharashtra.gov.in"
										target="_blank" style="font-size: 14px; font-weight: bold;">http://finance.maharashtra.gov.in</a></li>
									<li><a href="mailto:htesevaarth1@gmail.com"
										style="font-size: 14px; font-weight: bold;"><font
											color="#066fcc">Level 1 Helpdesk e-mail -
												htesevaarth2@gmail.com</font></a></li>
									<li><a href="mailto:ba.sevaarth@gmail.com" class="underline" style="font-size: 14px; font-weight: bold; text-decoration: none;">Level
											No 2:<br> ba.sevaarth@gmail.com/ ba.sevaarth@mahait.org
									</a></li>
<li><font color="#066fcc" style="font-size: 14px; font-weight: bold;">Nodal Officer Details:- <br><a href="mailto:do.asthahte-mh@gov.in" style="font-size: 14px; font-weight: bold;"> Amol Ugalmugale - amol.ugalmugale@gov.in </a> <br> 
                      <a href="mailto:desk16@dvet.gov.in" style="font-size: 14px; font-weight: bold;">  R. V. Vasave   desk16@dvet.gov.in </a></font></label></li>
								</ul>

							</td>

							<td style="width: 30%" valign="top" style="padding-left:5px;">




								<ul>


									<li><a href="images/StepsUsingTribal.pdf" target="_blank"
										style="font-size: 14px; font-weight: bold;">Step wise use
											of application</a></li>

									<!-- 	<li><a href="images/FAQ.pdf" target="_blank" style="font-size: 14px; font-weight: bold;">Frequently
											Asked Questions</a></li> -->

									<li><a href="#" onclick="javascript:getDashBoard()"
										style="font-size: 14px; font-weight: bold;">Graphical
											Summary</a></li>



								</ul>
							</td>
						</tr>


					</table>
				</fieldset>
			</center>
		</c:if>


		<body onbeforeunload="Close()" onunload="HandleOnClose()"
			onload="javascript: startLogin()">
			<script type="text/javascript"
				src='<c:url value="/script/common/wz_tooltip.js"/>'></script>
			<script type="text/javascript"
				src='<c:url value="/script/common/tip_balloon.js"/>'></script>
			<div id='statusbar'>
				<table style="font-family: arial;">
					<tr>
						<td id='imgtd'>&nbsp;</td>
						<td align='left' valign='middle' id='statuBarTd1'></td>
					</tr>
				</table>
			</div>
			<!--<div style="width: 100%; text-align: center;">-->
			<!--	 <a style="font-size: 10pt;font-weight: boldl;font-family:verdana;" href="javascript:openFaq();">-->
			<!--	 	<b>Shalarth Frequently Asked Questions - Updated on (22/08/2012)</b>-->
			<!--	 </a>-->
			<!--	<sup><blink><b style="color: red;font-size: 14">New</b></blink></sup>-->
			<!--</div>-->
			<!--
<div style="width: 100%; text-align: center;">

	<sup><blink><b style="color: red;font-size: 10pt;font-family:verdana;">Coming Soon</b></blink></sup>
	<a style="font-size: 9pt;font-family:verdana;font-weight: bold;color: red;" href="images/pdf3.pdf" target="_blank" >Click Here - Contact List of School Education Department Officials - Click Here</a>
	<sup><blink><b style="color: red;font-size: 10pt;font-family:verdana;">Coming Soon</b></blink></sup>

</div>


<table>
<tr align="center">
	

		<tr><td></td></tr>
		<tr align="center">
		<td>
			<a href="images/Do's_&_Don't.pdf" target="_blank" style="size:15px; color:#FF4000; "><b>Do's and Don'ts for using Shalarth</b></a>
		</td>
	</tr>
	
	<tr align="center">
		<td>
			<a href="images/District Master Trainer List .pdf" target="_blank" style="size:15px; color:#FF4000; "><b>District_master_trainer_list</b></a>
		</td>
	</tr>
	
</table>
<div style="width: 100%; text-align: center;">

	<sup><blink><b style="color: red;font-size: 14pt;font-family:verdana;">New</b></blink></sup>
	<a style="font-size: 12pt;font-family:verdana;font-weight: bold;color: red;" href="images/faq.pdf" target="_blank" ><b>Click Here - Frequently Asked Questions(FAQ) - Click Here</b></a>
	<sup><blink><b style="color: red;font-size: 14pt;font-family:verdana;">New</b></blink></sup>
	
</div>
-->
			<br />
			<br />
			<br />
			<br />








			<br />
			<br />
			<table border=0 cellspacing=0 cellpadding=0
				style="font-family: arial; vertical-align: middle;" align="center"
				border=1 style="display:none">
				<tr>

					<td id=loginControlsTD style="display: ''">






						<table align="center" id="Table_01" border="0" cellpadding="0"
							cellspacing="0" style="display: none">
							<tr>
								<td colspan="6" align="center"><font
									style="font-size: 13px; font-weight: bold; color: #FF3333;">
										<%
											session.setAttribute("mainFunc", "SetNormalWin");
										// error1 = Your IP Address is blocked.
										String errors[] = request.getParameterValues("error");
										if (errors != null && errors.length > 0) {
											for (int x = 0; x < errors.length; x++) {
												if (errors[x].equals("error1")) {
											out.print("Your IP Address is blocked.");
												}
											}
										} else {
											AuthenticationException authenticationException = (AuthenticationException) session
											.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);

											if (authenticationException != null) {
										%> <%=authenticationException.getMessage()%> <%
 	}
 }
 %>
								</font></td>

							</tr>
							<tr>
								<td></td>
							</tr>

							<tr>
								<td colspan="7" style="padding-left: 0px;"><img
									src="images/loginImg/Login_img_2_01.gif" width="643"
									height="191" alt=""></td>
							</tr>
							<tr>
								<td rowspan="7"><img
									src="images/loginImg/Login_img_2_020.gif" width="291"
									height="284" alt=""></td>
								<td><img src="images/loginImg/Login_img_2_03.gif"
									width="81" height="21" alt=""></td>
								<td width="139" height="21"><input type="text"
									value="<%=request.getParameter("userName")%>" name="j_username"
									maxlength="18"
									style="width: 136px; height: 17px; border: none;"
									onkeyup="javascript: checkEnter();"> <script
										type="text/javascript" language="JavaScript">
										document.forms[0].j_username.focus();
									</script></td>
								<td colspan="3"><img
									src="images/loginImg/Login_img_2_05.gif" width="132"
									height="21" alt=""></td>
							</tr>
							<tr>
								<td colspan="5"><img
									src="images/loginImg/Login_img_2_06.gif" width="352" height="9"
									alt=""></td>
							</tr>
							<tr>
								<td><img src="images/loginImg/Login_img_2_07.gif"
									width="81" height="23" alt=""></td>
								<td width="139" height="23" id="td_txtPassword"><input
									type="password" value="<%=request.getParameter("password")%>"
									name="j_password" maxlength="16" class="keyboardInput"
									style="width: 136px; height: 17px; border: none;"
									onkeypress="capLock(event);checkEnter();"></td>
								<td colspan="3"><img
									src="images/loginImg/Login_img_2_09.gif" width="132"
									height="23" alt=""></td>
							</tr>
							</br>
							</br>
							<tr>
								<td colspan="5" background="images/loginImg/Login_img_2_10.gif"
									height="19" alt="" align="left" id="vKeyboard"><font
									style="padding-left: 80px; padding-top: 50px; font-size: 12px; font-weight: bold; color: #006699;">Use
										Virtual Keyboard</font></td>
							</tr>
							<tr>
								<td><img src="images/loginImg/Login_img_2_11.jpg"
									width="81" height="21" alt=""></td>

								<td colspan="3"
									style="background-image: url('images/loginImg/Login_img_2_12.gif');">
									<input type="radio" style="visibility: hidden;" name="locale"
									value="gu" onkeyup="javascript: checkEnter();"> <input
									type="radio" name="locale" style="visibility: hidden;"
									value="en_US" onkeyup="javascript: checkEnter();"
									checked="checked">
								</td>
								<td><img src="images/loginImg/Login_img_2_13.gif"
									width="99" height="21" alt=""></td>
							</tr>
							<tr>
								<td colspan="3" align="center"
									style="background-image: url('images/loginImg/Login_img_2_14.gif');">
									<input type="button" value="Submit" name="btnSubmit"
									class="buttontag" onclick="javascript: startLogin();">
									<input type="reset" value="Reset" class="buttontag"
									onclick="javascript : resetFields();">
								</td>
								<td><img src="images/loginImg/Login_img_2_15.gif"
									width="19" height="30" alt=""></td>
								<td><img src="images/loginImg/Login_img_2_16.gif"
									width="99" height="30" alt=""></td>
							</tr>

							<tr valign="top">
								<td colspan="5"
									style="background-image: url('images/loginImg/Login_img_2_017.gif');"
									width="352" height="161"><font class="Labelerrormsg"><a
										href="#"
										style="padding-left: 80px; font-size: 10pt; text-decoration: none; color: black;"
										onclick="forgotPassword();">Forgot Password </a></font> <c:if
										test="${digitalSignature eq 'Y'}"> 
										    &nbsp;&nbsp;&nbsp;&nbsp;
											<A href="#"
											style="font-size: 10pt; text-decoration: none; color: black;"
											onclick="loginWithSignature()">Login Using Digital
											Signature</A>
										<input style="display: none;" type="hidden"
											name="loginSignature" value="loginSignature">
										<input style="display: none;" type="hidden"
											name="loginRandomNumber" value="123">
									</c:if> <%-- New tr added to Login with Digital Signature ... end --%></td>
							</tr>
							<tr>
								<td><img src="images/loginImg/spacer.gif" width="288"
									height="1" alt=""></td>
								<td><img src="images/loginImg/spacer.gif" width="81"
									height="1" alt=""></td>
								<td><img src="images/loginImg/spacer.gif" width="139"
									height="1" alt=""></td>
								<td><img src="images/loginImg/spacer.gif" width="14"
									height="1" alt=""></td>
								<td><img src="images/loginImg/spacer.gif" width="19"
									height="1" alt=""></td>
								<td><img src="images/loginImg/spacer.gif" width="99"
									height="1" alt=""></td>
							</tr>
							<tr align="center">
								<td align="center">
									<div id="divErrorMsg">
										<font color="yellow"
											style="font-family: Arial; font-size: 11px;"> <strong><label
												id="errorLabel"> </label></strong></font>
									</div>
								</td>
							</tr>
						</table>
						</form>
					</td>



				</tr>

			</table>


			<!--<a id="userManual" onclick="userManual(divUserManual)" style="size:15px; color:#FF4000;"><u>User Manual (04-02-2013)</u></a>
-->
			<div id="divUserManual" style="display: none">
				<fieldset class="tabstyle">
					<table align="center" width="70%" border="1" style="display: none">


						<tr>
							<td width="30%">MUNICIPAL CORPORATION/COUNCIL SCHOOLS</td>
							<td width="30%">PRIVATE AIDED SCHOOLS</td>
							<td width="30%">ZILLA PARISHAD SCHOOLS</td>
						</tr>
						<tr>

							<td width="20%"><a
								href="images/tree_images/1 Configuring the Institution.pdf"
								target="_blank">Configuring the Institution</a></td>

							<td width="20%"><a
								href="images/portletImages/1 Configuring the Institution.pdf"
								target="_blank">Configuring the Institution</a></td>


							<td width="15%"><a
								href="images/loginImg/1 Configuring the Institution.pdf"
								target="_blank">Configuring the Institution</a></td>

						</tr>


						<tr width="20%">

							<td width="20%"><a
								href="images/tree_images/2 Process of Configuration.pdf"
								target="_blank">Process of Configration</a></td>
							<td width="20%"><a
								href="images/portletImages/2 Process of Configuration.pdf"
								target="_blank">Process of Configration</a></td>
							<td width="20%"><a
								href="images/loginImg/2 Process of Configuration.pdf"
								target="_blank">Process of Configration</a></td>

						</tr>


						<tr width="20%">

							<td width="20%"><a
								href="images/tree_images/3 Entering Data of Institution.pdf"
								target="_blank">Entering Data of Institution</a></td>
							<td width="20%"><a
								href="images/portletImages/3 Entering Data of Institution.pdf"
								target="_blank">Entering Data of Institution</a></td>
							<td width="20%"><a
								href="images/loginImg/3 Entering Data of Institution.pdf"
								target="_blank">Entering Data of Institution</a></td>




						</tr>


						<tr width="20%">

							<td width="20%"><a
								href="images/tree_images/4 Entry of Posts.pdf" target="_blank">Entry
									of Posts</a></td>
							<td width="20%"><a
								href="images/portletImages/4 Entry of Posts.pdf" target="_blank">Entry
									of Posts</a></td>
							<td width="20%"><a
								href="images/loginImg/4 Entry of Posts.pdf" target="_blank">Entry
									of Posts</a></td>





						</tr>


						<tr width="20%">

							<td width="20%"><a
								href="images/tree_images/5 Entry of Employee Details.pdf"
								target="_blank">Entry of Employee Details</a></td>
							<td width="20%"><a
								href="images/portletImages/5 Entry of Employee Details.pdf"
								target="_blank">Entry of Employee Details</a></td>
							<td width="20%"><a
								href="images/loginImg/5 Entry of Employee Details.pdf"
								target="_blank">Entry of Employee Details</a></td>




						</tr>
						<tr width="20%">

							<td width="20%"><a
								href="images/loginImg/Master_Data_Configuration.pdf"
								target="_blank">Master Data Configuration</a></td>

							<td width="20%"><a
								href="images/loginImg/Master_Data_Configuration.pdf"
								target="_blank">Master Data Configuration</a></td>

							<td width="20%"><a
								href="images/loginImg/Master_Data_Configuration.pdf"
								target="_blank">Master Data Configuration</a></td>




						</tr>



					</table>
				</fieldset>
			</div>




			<!--
	
	
	
	

	
	
	
	<table>
		<tr><td><br></td></tr>
		<tr align="center">
		<td>
			<a href="images/SupportStaffLink.pdf" target="_blank" style="size:15px; color:#FF4000; ">Support Staff Link</a>
		</td>
	</tr>
</table>





-->
			<!--<B>HttpSession Information:</B> -->
			<!-- <UL>
 <LI>Served From Server instance: <bSystem.getProperty("com.sun.aas.instanceName")e") %></b></LI>
<LI>Executed Server IP Address: <bjava.net.InetAddress.getLocalHost().getHostAddress()s() %></b></LI> 
<LI>Executed From Server Port: <b><%= request.getServerPort()%></b></LI>
</UL>
-->
			<!-- Copyright & Disclaimer -->




			<table cellpadding="0" cellspacing="0" width="770"
				class="CopyrightLine" border="0" align="center"
				style="display: none">
				<tr>

				</tr>
				<%--

	<tr valign="middle">
		<td align="center" class="Label" style="color:#848484;">Copyright Â© 2012 Tata Consultancy Services Limited
	</tr>

	<tr valign="bottom">
		<td align="center" class="Label" style="color:#848484;">Site best viewed in <span
			class="Label" style="color: #4b78ad">Microsoft Internet
		Explorer 6.0+</span><span style="color:#848484;">&nbsp;in </span><span class="Label" style="color: #4b78ad">1024x768</span><span style="color:#848484;">&nbsp;resolution</span></td>
	</tr>
	
	<tr><td></td></tr>
	
	--%>

			</table>





			<!-- Added for Digital Singature  start..... -->
			<c:if test="${digitalSignature eq 'Y'}">
				<OBJECT style="LEFT: 0px; TOP: 0px"
					CLASSID="clsid:5220CB21-C88D-11cf-B347-00AA00A28331">
					<PARAM NAME="LPKPath" VALUE="tcssigner/TCSSigner.lpk">
				</OBJECT>
				<OBJECT id=TCSSigner1 style="LEFT: 0px; TOP: 0px"
					codeBase="tcssigner/TCSSigner.cab#Version=6,1,0,0"
					classid="clsid:AD1959BC-3260-4003-AEF1-8C845E82EEB5" VIEWASTEXT>
					<PARAM NAME="_Version" VALUE="65536">
					<PARAM NAME="_ExtentX" VALUE="2646">
					<PARAM NAME="_ExtentY" VALUE="1323">
					<PARAM NAME="_StockProps" VALUE="0">
				</OBJECT>
				<script type="text/javascript" language="javascript">
		generateRandomNoForDigiLogin('${pageContext.request.contextPath}');
	</script>
			</c:if>
			<!--- Added for Digital Signature end..... -->

			<input type="hidden" name="show_menu" value="Y" style="display: none">
			<script type="text/javascript" language="javascript">
	init();
	Browser();
	setInterval('blinkIt()',300);
	var obj = document.getElementById('td_txtPassword');
	var lgnToolTipX = findPosX(obj) + 120 ;
	var lgnToolTipY = findPosY(obj) + 55;
	if(window.name == "")
	{
			window.location.href="/index.jsp";
	}
	 
	function Close()
	{

		if (event.clientY < 0 || window.event.altKey)
		{
			var wname = window.name;
			if(!window.opener || (window.name != "" && wname.substring(0,4) == "IFMS"))		
			{
				setTimeout('myclose=false',100);
				myclose=true;
			}
		}
	}

	function HandleOnClose()
	{
		if (myclose==true)
		{
		    var url = '${pageContext.request.contextPath}/'+"j_spring_security_logout?closeType=onclose";
		    var myAjax = new Ajax.Request(url,
			{
						method: 'post',
						asynchronous: false,
						onFailure: function(){ alert('Could Not Logout Properly... '); } 
			} );
		}
	}
</script>


			<%--START added for forgot password--%>

			<CENTER>
				<fieldset class="tabstyle"
					style="width: 85%; background-color: #F6F7F9;">
					<table id="ForgotPwdTable" width="100%" border="0"
						style="display: none;">
						<tr>
							<td width="100%" colspan="3"><img width="100%"
								style="background-repeat: no-repeat;"
								src="images/HomePageImages/FianlHomePG_1_11.jpg" /></td>
						</tr>

						<tr>
							<td width="20%">User Name:</td>
							<td width="30%" align="left"><input type="text"></td>
							<td width="50%">&nbsp;</td>
							<td></td>
						</tr>

						<tr>
							<td width="20%">Mobile Number:</td>
							<td width="30%" align="left"><input type="text"></td>
							<td width="50%">&nbsp;</td>
						</tr>

						<tr>
							<td width="20%">TAN Number:</td>
							<td width="30%" align="left"><input type="text"></td>
							<td width="50%">&nbsp;</td>
						</tr>

						<tr>
							<td width="100%" colspan="3" align="center"><hdiits:button
									name="BTN.BACK" id="Back" type="button" captionid="BTN.BACK"
									bundle="${englishLabels}" onclick="backToLogin();"
									style="width:15%" />&nbsp;</td>
						</tr>
					</table>
					</fieldst>
			</CENTER>

			<%--END added for forgot password--%>



			<%
				//out.print("SessionID : " + session.getId());
			session.removeAttribute("locale");
			session.removeAttribute("loginVO");
			session.removeAttribute("loginDetailsMap");
			%>
			<script>
if(document.getElementById("flag").value == 'N'){

	alert("Captcha Not matched.");

	
}


if(document.getElementById("flag").value == 'Y'){
	//alert("matched");
	var user=document.getElementById("userName").value;
	var pwd=document.getElementById("pwd").value
	document.getElementById("username").value=user;
		document.getElementById("password").value=pwd;
	startLoginInShalarth(user,pwd);
}

</script>
		<div class="container">
				<!--  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    Open modal
  </button>  -->

				<!-- The Modal -->
				<!-- <div class="modal" id="myModal">
					<div class="modal-dialog"
						style="margin-top: 13% !important; box-shadow: 2px 2px 7px #000; border: 1px solid #824000;">
						<div class="modal-content" style="background-color: #f8efef;">

							Modal Header
							<div class="modal-header"
								style="background: #d76f14; color: #fff;">
								<h4 class="modal-title">
									Important Instruction <i class='fa fa-user'
										style='font-size: 48px; color: red'></i>
								</h4>
								<button type="button" class="close" data-dismiss="modal"
									onClick="closeModal();" style="color: #fff">&times;</button>
							</div>

							Modal body
							<div class="modal-body">
								<h4 class="text-center">Update the DDO details, DDO Bank
									balance details and cash flow carry forward for every month at
									BEAMS portal to avoid bds rejection.</h4>
								<br> <br>
								<h4 class="text-center">बीडीएस नकार टाळण्यासाठी डीडीओ
									तपशील, डीडीओ बँक शिल्लक तपशील आणि रोख प्रवाह दरमहासाठी बीएएमएस
									पोर्टलवर अद्यतनित करा.</h4>
							</div>

							Modal footer
							<div class="modal-footer">
								<button type="button" class="btn" data-dismiss="modal"
									onClick="closeModal();"
									style="background: #d76f14; color: #fff;">OK</button>
							</div>

						</div>
					</div>
				</div> -->

			</div>
			<script>
function myFunction() {
  /*alert("hhh");*/
  document.getElementById("myModal").style.display="block";	
}
document.getElementById("demo").innerHTML = myFunction();
function closeModal() {
	  /*alert("hhh");*/
	  document.getElementById("myModal").style.display="none";	
	}
</script>
		</body>
</html>

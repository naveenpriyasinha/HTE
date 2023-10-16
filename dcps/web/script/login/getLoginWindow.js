function openLoginWindow(wname){
	//var urlToOpen = "hdiits.htm?viewName=login&theme=defaulttheme";
	var urlToOpen = "login.jsp";
	var mwname = 'IFMS';
	if( wname ){
		mwname = wname;
	}else{
		var dt = new Date();
		mwname = mwname + dt.getMilliseconds();
	}
	var prop = 'width='+screen.availWidth+',height='+screen.availHeight+',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	var child= window.open(urlToOpen, mwname, prop);
	child.moveTo( 0, 0 );
	child.resizeTo(screen.availWidth, screen.availHeight);
	child.focus();


}

function openfaq(wname){
	//var urlToOpen = "hdiits.htm?viewName=login&theme=defaulttheme";
	
	showProgressbar_login('Please wait...<br>Your Request is in Progress.');

	var varLocale = "";
	
	if(document.forms[0].locale[0].checked == true) varLocale = document.forms[0].locale[0].value;
	else varLocale = document.forms[0].locale[1].value;	
	
	document.forms[0].action = "ifms.htm?viewName=faq";


	document.forms[0].submit();
		
}
function ajaxfunctionForLogout (ctx){
	var url = ctx + '/ifms.htm';
	var pars = 'actionFlag=closeApplicationOnLogout';
	var myAjax = new Ajax.Request(url,{
		method: 'get',
		parameters: pars,
		onComplete: openLoginWindow(window.name)		
	});
}

// Move From hdiits/web/web-inf/jsp/core/login.jsp.
function pause(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}
function init()
{
	document.forms[0].j_username.focus();
}

function startLogin()
{
	if(document.forms[0].btnSubmit.disabled==false)
	{					
		if (document.forms[0].j_username.value=="")
		{
			alert("Please Enter User Name.");
			document.forms[0].j_username.focus();
			return false;
		}
		if (document.forms[0].j_password.value=="")
		{
			alert("Please Enter Password.");
			document.forms[0].j_password.focus();
			return false;
		}
		document.forms[0].btnSubmit.disabled = true ;
		showProgressbar_login('Signing in...<br>Please wait...');
		window.setTimeout('document.forms[0].submit();',500);
	}
}

function checkEnter()
{
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
		key = e.which;
	if (key==13) {
		startLogin();
	}
}

function getX() {
  var maxX;
  maxX = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft) + (document.documentElement.clientWidth != 0 ? document.documentElement.clientWidth : document.body.clientWidth);
  return maxX;
}

function Browser() {

  var ua, s, i;
  var maxX,maxY;
  this.isIE    = false;  // Internet Explorer
  this.isNS    = false;  // Netscape
  this.version = null;

  ua = navigator.userAgent;
  s = "MSIE";
  
  if ((i = ua.indexOf(s)) >= 0)
  {
    this.isIE = true;
    this.version = parseFloat(ua.substr(i + s.length));
   	
    var maxHeight;	
   	var d = 0.9616;
	   
    if (this.version==7)
    	{    		
    		maxX=getX();    		
    		var x=Math.round(maxX/1.65);
    		if (maxX==780)
    		{
    			x=maxX/1.3;
    		}     		
    		maxHeight = (screen.availHeight)*d;    		
    		var top = (maxHeight - 564)/2;			
    		document.write("<style> DIV.imgAttr {position:relative; top:"+top+"px;)</style>");
	   		document.write("<style> DIV.double {position:absolute; top:280px; left:"+x+"px;  color:#996600; font-family:verdana;) </style>");    		
    		return;
    	}
    else if(this.version==6)
    	{           
    		maxHeight = screen.availHeight * d;
			var top = (maxHeight - 564)/2;				
			document.write("<style> DIV.imgAttr {position:relative; top:"+top+"px;)</style>");
    		document.write("<style> DIV.double {position:absolute; top:275px; left:486px;  color:#996600; font-family:verdana;) </style>");
    		return;
    	}
        
    //document.write("<div class=double > <b> <fmt:message key="USER_NAME" bundle="${englishLabels}" /> &nbsp; <input type=text name=userName maxlength=15 style=width:140px;color:#996600 /> ");        
    return;
  }
}

function forgotPassword()
{
	showProgressbar_login('Please wait...<br>Your Request is in Progress.');

	var varLocale = "";
	
	if(document.forms[0].locale[0].checked == true) varLocale = document.forms[0].locale[0].value;
	else varLocale = document.forms[0].locale[1].value;	
	
	document.forms[0].action = "hdiits.htm?viewName=acl-forgotPassword&locale="+varLocale;


	document.forms[0].submit();
}


function signData()
{
	try
	{
		var data = document.forms[0].loginRandomNumber.value;
		var TCSSigner1 = document.getElementById('TCSSigner1');
		var vSignature = "none";
		if(TCSSigner1.setDetached(1))
		{
			if(TCSSigner1.updateData (data))
			{
				if(TCSSigner1.selectSigningCertFromUI())
				{
					vSignature = TCSSigner1.sign();
					document.forms[0].loginSignature.value = vSignature;
				}
			}
		}
	}
	catch(e)
	{
		alert("Error in Digital Signature !" + e.message );
	}

	return vSignature;
}
function loginWithSignature()
{
	var temp = 	signData();
	if(temp == '')
	{
		alert("Please select Certificate to Login.");
	}
	else if( temp != "none")
	{
		showProgressbar_login('Signing in...<br>Please wait...');
		document.loginForm.action="hdiits.htm?actionFlag=validateLogin";
		document.loginForm.submit();
	}
}

function showProgressbar_login(message)
{
	if( !message ) message = 'Please wait<br>Your Request is in progress...';
	disableBackground_login();
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
function hideProgressbar_login()
{
    if(document.getElementById('statusbar')!= null)
    {
		document.getElementById('statusbar').style.visibility='hidden';
		enableBackground_login();
	 }
}
function disableBackground_login()
{
	var st = document.documentElement.scrollTop;
	var sh = document.documentElement.scrollHeight;
	var ch = document.documentElement.clientHeight;
}
function enableBackground_login()
{

}

function showPwdSecurityTip()
{
	Tip('Use Virtual Keyboard for <br> your <B>Password Security.</B>',CLICKCLOSE, true,FIX,[lgnToolTipX,lgnToolTipY], FADEIN,500,BALLOONIMGPATH,'themes/${themename}/images/tooltip/');
}
function generateRandomNoForDigiLogin(ctx)
{
	showProgressbar_login('Signing in...<br>Please wait...');
	var ajaxCallURL = ctx + '/hdiits.htm?actionFlag=getRandomNo';
	var url_ps = ajaxCallURL;
    var myAjax = new Ajax.Request(url_ps,{
				method: 'get',
				asynchronous: true,
				onSuccess: function(transport){
					if (200 == transport.status) 
					{
						populateRandomNoData(transport);
					}
		  }// transport 
		});
    hideProgressbar_login();
}
function populateRandomNoData(transport)
{
	var XMLDoc=transport.responseXML.documentElement;
	var entries = XMLDoc.getElementsByTagName('element');
	if( entries.length != 0 )
	{
		document.getElementById("loginRandomNumber").value = entries[0].childNodes[0].text;
	}
	else
	{
		alert("Random Number is not Generated !!!");
	}
	hideProgressbar_login();
}

function startLoginInShalarth()
{
	//alert("username is *****");
	
	 var str2 = document.getElementById('captcha_code').value;
	 str2=str2.split(' ').join('');
	 var str1=document.getElementById('captcha').value;
	 str1=str1.split(' ').join('');
	if(str1==str2){
	var userName = document.getElementById('username').value;
	//alert("username is *****"+userName);
	var pwd = document.getElementById('password').value;
	//alert("pwd is *****"+pwd);
	//alert(document.forms[0].j_username.value);
	if(userName==""){
	alert("Please Enter User Name.");
	return false;
	}
	if(pwd==""){
		alert("Please Enter Password.");
		return false;
	}
	document.forms[0].j_username.value=userName;
	document.forms[0].j_password.value=pwd;
	//alert(document.forms[0].j_username.value);

	pwd=document.forms[0].j_password.value;
	var pwd1=CryptoJS.MD5(pwd).toString();
	//document.forms[0].j_password.value=pwd1;
	document.forms[0].j_password.value=pwd;
	document.forms[0].btnSubmit.disabled = true ;
	showProgressbar_login("Please wait<br>While Your Request is in Progress ...");
	window.setTimeout('document.forms[0].submit();',500);
	}
	
	else if(str2.length>0){
		
		document.getElementById('captcha_code').value='';
		document.getElementById('divCaptchaMsg').innerHTML='Please enter correct captcha. <br>';	
		document.getElementById('divCaptchaMsg').style.fontFamily='arial';
		document.getElementById('divCaptchaMsg').style.fontSize='11px';
		document.getElementById('divCaptchaMsg').style.fontWeight='900';
		document.getElementById('divCaptchaMsg').style.color='red';
		document.getElementById('divCaptchaMsg').style.display='inline';
		document.getElementById('divCaptchaMsg').style.visibility='visible';
	}
	else{
		document.getElementById('divCaptchaMsg').innerHTML='Please enter captcha. <br>';	
		document.getElementById('divCaptchaMsg').style.fontFamily='arial';
		document.getElementById('divCaptchaMsg').style.fontSize='11px';
		document.getElementById('divCaptchaMsg').style.fontWeight='900';
		document.getElementById('divCaptchaMsg').style.color='red';
		document.getElementById('divCaptchaMsg').style.display='inline';
		document.getElementById('divCaptchaMsg').style.visibility='visible';
	}
	
}

function startLoginInShalarth(user,pwd1)
{
//alert("helooo");

	var userName = user;
//	alert("helooo"+userName);
	//alert("username is *****"+userName);
	var pwd = pwd1;
	//alert("pwd"+pwd);
	//alert("pwd is *****"+pwd);
	//alert(document.forms[0].j_username.value);
	if(userName==""){
	alert("Please Enter User Name.");
	return false;
	}
	if(pwd==""){
		alert("Please Enter Password.");
		return false;
	}
	document.loginForm.j_username.value=userName;
	//alert("helooo32312323");
	document.loginForm.j_password.value=pwd;
	//alert(document.forms[0].j_username.value);

	pwd=document.loginForm.j_password.value;
	var pwd1=CryptoJS.MD5(pwd).toString();
	document.loginForm.j_password.value=pwd;
	document.loginForm.btnSubmit.disabled = true ;
	showProgressbar_login("Please wait<br>While Your Request is in Progress ...");
	window.setTimeout('document.forms[0].submit();',500);
	
	
	
}


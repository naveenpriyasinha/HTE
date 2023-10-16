//
// These two functions 'disableBackground()' & 'enableBackground()'
// requires following <div> element available on the html/jsp page
// 
// <div id="fadeBackground" class="fade_background"></div>
// 
// Also the 'fade_background' css class should be available.
// example css class. 
//
//.fade_background{
//	display: none;
//	position: absolute;
//	top: 0%;
//	left: 0%;
//	width: 100%;
//	height: 100%;
//	background-color: gray;
//	z-index:1001;
//	-moz-opacity: 0.5;
//	opacity:.50;
//	filter: alpha(opacity=50);
//}
//
// Note: We need to keep z-index a higher value so that this div is
// above all other elements in the jsp/html page

// show the 'fadeBackground' div so that all other 
// elements are not accesible

function disableBackground()
{
	// display the fadeBackground
	document.getElementById('fadeBackground').style.display='block';
	
	if(window.mlrunShim == true)
	{
		var st = document.documentElement.scrollTop;
		var sh = document.documentElement.scrollHeight;
		var ch = document.documentElement.clientHeight;
		document.getElementById('fadeBackground').style.height = Math.max(st + ch, sh);
	}
	else
	{
		changePosition();
	}

	if (this.addEventListener)
    {
		this.addEventListener("scroll", changePosition, false);
    }
    else if (this.attachEvent)
    {
    	this.detachEvent("onscroll", changePosition);
    	this.attachEvent("onscroll", changePosition);
    }
}

// Hide the 'fadeBackground' div so that all other 
// elements are now accesible
function enableBackground()
{
	if (this.removeEventListener)
    {
		this.removeEventListener("scroll", changePosition, false);
    }
    else if (this.detachEvent)
    {
    	this.detachEvent("onscroll", changePosition);
    }
	// hide the fadeBackground	
	document.getElementById('fadeBackground').style.display='none';
}

function changePosition()
{
	var statusBar =	document.getElementById('statusbar');
	var statusbarheight = 100;
	var screenheight = screen.height;
	var screenwidth = screen.width;
	
	var sl = window.pageXOffset || document.body.scrollLeft || document.documentElement.scrollLeft;
	sl =  sl ? sl : 0;
	if(screenwidth > sl)
	{
		document.getElementById('fadeBackground').style.left = sl + 'px';
	}

	var st = window.pageYOffset || document.body.scrollTop || document.documentElement.scrollTop;
	st =  st ? st : 0;

	if(screenheight > st)
	{
		document.getElementById('fadeBackground').style.top = st + 'px';
	}

	var ch = document.documentElement.clientHeight;
	statusBar.style.top = (st + ((ch - statusbarheight)/2))+'px';
}

function opacity(id, opacStart, opacEnd, millisec) { 
    //speed for each frame 
    var speed = Math.round(millisec / 100); 
    var timer = 1;
    var ostep  = 1;

    //determine the direction for the blending, if start and end are the same nothing happens 
    if(opacStart > opacEnd) { 
    	ostep  = Math.round((opacStart - opacEnd)/speed);
        for(i = opacStart; i >= opacEnd; i=i-ostep) { 
            setTimeout("changeOpac(" + i + ",'" + id + "')",(timer * speed)); 
            timer++; 
        } 
    } else if(opacStart < opacEnd) { 
    	ostep  = Math.round((opacEnd - opacStart)/speed);
        for(i = opacStart; i <= opacEnd; i=i+ostep){ 
            setTimeout("changeOpac(" + i + ",'" + id + "')",(timer * speed)); 
            timer++; 
        } 
    } 
}

//change the opacity for different browsers 
function changeOpac(opacity, id) { 
    var object = document.getElementById(id).style; 
    object.opacity = (opacity / 100); 
    object.MozOpacity = (opacity / 100); 
    object.KhtmlOpacity = (opacity / 100); 
    object.filter = "alpha(opacity=" + opacity + ")"; 
} 

// Move From hdiits/web/web-inf/jsp/core/model.jsp.

function provideProperHeight()
{	
	/* 
	  document.write("<style>#content{height:635px;}</style>");
	  document.write("<style>DIV.tabcontentstyle{height:596px;}</style>");
	  document.write("<style>DIV.tabcontent{height:554px;}</style>");
	*/
	
	  /* added to fix width of all worklists starts :shripal*/
	  var availWidth = screen.availWidth - 66;	
	  document.write("<style>.scrollablediv{width:"+availWidth+"px;}</style>");
	  /* added to fix width of all worklists ends*/
	  
	  
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
	   	var d1 = 635/834;
		var d2 = 596/834;
		var d3 = 554/834;   
	    if (this.version==7)
	    {        		
	    	maxHeightContent = (screen.availHeight)*d1;    		
	    	maxHeightTabcontentstyle = (screen.availHeight)*d2;
	    	maxHeightTabcontent = (screen.availHeight)*d3;	    	   
		
//			document.write("<style>#content{height:"+maxHeightContent+"px;}</style>");
//		  	document.write("<style>DIV.tabcontentstyle{height:"+maxHeightTabcontentstyle+"px;}</style>");
//		  	document.write("<style>DIV.tabcontent{height:"+maxHeightTabcontent+"px;}</style>");	    
		  	document.write("<style>#content{height:"+maxHeightContent+"px;min-height: 450px;height:auto !important;}</style>");
		  	document.write("<style>DIV.tabcontentstyle{height:"+maxHeightTabcontentstyle+"px;min-height: 450px;height:auto !important;}</style>");
		  	document.write("<style>DIV.tabcontent{height:"+maxHeightTabcontent+"px;min-height: 450px;height:auto !important;}</style>");	    		
	    }
	    else if(this.version==6)
	    {           
	    	maxHeightContent = (screen.availHeight)*d1;    		
	    	maxHeightTabcontentstyle = (screen.availHeight)*d2;
	    	maxHeightTabcontent = (screen.availHeight)*d3;	  		    	
			document.write("<style>#content{height:635px;}</style>");
		  	document.write("<style>DIV.tabcontentstyle{height:596px;}</style>");
		  	document.write("<style>DIV.tabcontent{height:554px;}</style>");	    		
	    	 		
//			document.write("<style>#content{height:"+maxHeightContent+"px;}</style>");
//		  	document.write("<style>DIV.tabcontentstyle{height:"+maxHeightTabcontentstyle+"px;}</style>");
//		  	document.write("<style>DIV.tabcontent{height:"+maxHeightTabcontent+"px;}</style>");	    		
	    }
	  }
}	

//To disable right click on page --- starts
	//var message="Event not allowed";
	function clickIE4()
	{
		if (event.button==2)
		{
			//alert(message);
			return false;
		}
	}
	function clickNS4(e)
	{
		if (document.layers||document.getElementById&&!document.all)
		{
			if (e.which==2||e.which==3)
			{
				//alert(message);
				return false;
			}
		}
	}
// To disable right click on page --- ends	


// Move From hdiits/web/web-inf/jsp/core/messagePage.jsp
function submitMessageForm()
{
//var varUrl = "ifms.htm?"+document.getElementById("msgAction").value;
var varUrl = document.getElementById("msgAction").value;
showProgressbar();
document.forms[0].action=varUrl;
document.forms[0].submit();
}


//START OF : 202414
//FUNCTION RELATED TO ATTACHED ONLOAD EVENT OF WINDOW.
var onLoadAttachedEvents = new Array();
//if flag = true check in onload event list and add if not prsent
//flag = false add in onload event
function addWindowOnloadEvent (eventName,flag)
{
	//var t1 = new Browser();
//	alert (t1.isIE);
//	alert (t1.isMozilla );
//	alert ("onLoadAttachEvents LENGTH :"+onLoadAttachedEvents.length)	;
	//alert ("EVENTNAME :"+eventName);
	if (flag )
	{
		for(var index=0; index<onLoadAttachedEvents.length; index++) 
		{
//			alert ("onLoadAttachedEvents [index] :" +onLoadAttachedEvents [index]);
//			alert ("EVENTNAME.TOSTRING() :"+eventName.toString());
			var mindex =  onLoadAttachedEvents .indexOf (eventName.toString());
			if (mindex >= 0)
//			if (onLoadAttachedEvents [index].indexOf(eventName.toString()) > 1);
			{
//				alert ("MATCH WITH LIST ");
				return ;
			}
		}
	}
	onLoadAttachedEvents[onLoadAttachedEvents.length] = eventName.toString();
	if (browserVars.type.ie)
	{
		window.attachEvent("onload",eventName);
	}
	else if (browserVars.type.moz)
	{
		addOnLoadEventMozilla (eventName); 
	}
	else
	{
		alert ("ADD BROWSER SUPPORT ONLOAD SCRIPT ");
	}
}
function addOnLoadEventMozilla(func) 
{
	var oldonload = window.onload;
	if (isFunction(func)) 
	{
		if (!isFunction(oldonload)) 
		{
		  window.onload = func;
		}
		else 
		{
		  window.onload = function() 
		  		{
				func();
			  	oldonload();
		  		};
		}
	} 
	else 
	{
		if (isObject(func) && isFunction(func.onload)) 
		{
			// callback event?
			window.onload = function() 
			{
				if (isFunction(oldonload)) 
				{
					oldonload();
				}
				// onload des objektes aufrufen
				func.onload();
			};
		}
	}
}

function showAlertMsgForDWR (msg)
{
	alert (msg);
}
//END  OF : 202414 

//function of frmKeyConfig.js
/*document.attachEvent("onkeydown",disableEscapeKey);*/

if(document.addEventListener)
{
	document.addEventListener("onkeydown", onKeyDownEvent, false);
}
else if(document.attachEvent)
{
	document.attachEvent("onkeydown",onKeyDownEvent);
}

/*function disableEscapeKey(){
	if (event.keyCode==27) {
		return false;
	}	
}
*/

function onKeyDownEvent(event)
{
	if (event.keyCode==27) {
		return false;
	}
	
	if(event.keyCode == 117)
	{
		// case 116 : // 'F5' 
		alert('F6 is Diabled');
		event.returnValue = false; 
		event.keyCode = 0; 
		 
	}
	
	if(event.keyCode == 116)
	{
		alert('Refreshing Page is disabled');
		if (event.preventDefault){
			event.preventDefault();
			return false;
		}
		// case 116 : // 'F5' 
		event.keyCode = 0;
		event.returnValue = false;
		//window.status = "We have disabled F5"; 
	}
//	
//	//For disabling the ALT key added by Kapil Devani
//	if(event.keyCode == 18)
//	{
//		alert('ALT Key has been disabled');
//		event.returnValue = false; 
//		event.keyCode = 0; 
//		
//	}
		
	if ( (event.altKey) || ((event.keyCode == 8) &&  (event.srcElement.type != "text" &&
		      event.srcElement.type != "textarea" &&
		      event.srcElement.type != "password")) || 
		      ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||		    	
		      (event.keyCode == 116) ||  (event.keyCode == 122) || (event.keyCode == 114)) {
		      event.keyCode = 0;
		      event.returnValue = false;
		      }
	
}


//from Preference.js

function storePreferences(urlStr)
{	
	var hiddenDivCntObj = document.getElementById('divCount');
	var paramStr = "";
	if(hiddenDivCntObj != null && hiddenDivCntObj != 'undefined')
	{	
		var divCount = hiddenDivCntObj.value;	
		var divName;
		var divHiddenParaName;	
		
		paramStr = "&divCount="+divCount;		
		for(var i = 0;i<divCount;i++)
		{
			divName = "homePageDiv_"+i;
			divStatusParaName = "hpDiv_"+i;
			var divObj = document.getElementById(divName);		
			paramStr = paramStr+"&"+divStatusParaName+"="+divObj.style.display;
		}
		//alert("paramStr ---> "+paramStr);
		storeMenuPreferences(paramStr);			
	}
	//document.forms[0].action = urlStr;
	//document.forms[0].submit();
	//code for opening page in new window as dialog direct from menu
	var dialogview = getdialogview(urlStr);
	if (dialogview)
	{
		var mwname = 'IFMS';
		var prop = 'width='+screen.availWidth+',height='+screen.availHeight+',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
		window.open(urlStr,mwname,prop);
		if(getCloseParentWindow(urlStr))
			self.close();//code added to close current window as new window is already opened
		return;
	}
	
	var contentUsingAjaxFlag = getContentUsingAjaxStatus(urlStr);
	
	if(!controlAjaxMenuLoad || !contentUsingAjaxFlag)
	{
		showProgressbar();
		urlStr = urlStr.replace(/contentUsingAjax=true/gi,"contentUsingAjax=false");
		window.location.href=urlStr;
	}
	else
	{ 
		loadContentbyAjax(urlStr,document,true);
	}
	
}


	function storeMenuPreferences(paramStr)
	{			
		
		try
    	{   
    	// Firefox, Opera 8.0+, Safari    
    	
    	xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{    // Internet Explorer    
			try
      		{
      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
      		   
      		}
		    catch (e)
		    {
		          try
        		  {                	      
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}
    	
    	var contextPath = document.getElementById('projectContextPath').value;		  
        var url = contextPath+"?actionFlag=manageUserPreferences&functionality=storeUserMenuPref"+paramStr;       
        //alert(url);
        xmlHttp.open("POST", encodeURI(url) , false);         
        xmlHttp.onreadystatechange = function()
        {        	
        	if (xmlHttp.readyState == 4) 
        	{  			  	
				if (xmlHttp.status == 200) 
				{
					
				}//end of if
				
			}
        }
        xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	     
	}
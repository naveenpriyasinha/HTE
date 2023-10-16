//These flags will be used as control flags. Make these both false stop all ajax advantages from menu and submit
controlAjaxMenuLoad = false;//make it false when something went wrong and you need to avoid all ajax advantages
controlAjaxSubmit = false;//make it false when something went wrong and you need to avoid all ajax advantages
//these are admin flag dont try to change it without permission

//Flags will be used globally to set reset whether not added in url or jsp
contentUsingAjax = false;//this flag will be used globally when not added in menu
pageSubmitFlag = false;//this flag will be used globally when not added in jsp for submit
//In acl_element_mst Url contentUsingAjax=false can be added to avoid menu clicked page load using ajax
//In acl_element_mst Url dialogview=true can be added to open in new window
//In jsp enableAjaxSubmit(false) can be added to avoid page submit using ajax
showAjaxProgressBarFlag = true;//Make it false if progress bar is not required. to see fast page load

function collectAllParaForAjax(urlStr)
{
	var paraStr = urlStr ;
	try
	{
	 	paraStr = paraStr + run();
	}
	catch(e)
	{
	}
	return paraStr;
}

function loadContentbyAjax(urlStr,parentObj,isFromMenu)
{
	if(urlStr.indexOf("self.close()") != -1)
	{
		self.close();
		return;
	}
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
	
	parentObj.getElementById('windowLocationHref').value = urlStr;
	showAjaxProgress(parentObj);
	urlStr += "&contentUsingAjax=true";
	
	var paraStr = urlStr;
	//alert('urlStr='+urlStr);
	//if page is submitting using ajax then only this will required
	if(!isFromMenu)
		paraStr = collectAllParaForAjax(urlStr);
	xmlHttp= GetXmlHttpObject();   
	xmlHttp.onreadystatechange=function () { callBackLoadContentbyAjax(parentObj);  }  ;
	xmlHttp.open("POST",urlStr,true);//ture async : false sync
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(paraStr);
}


 responseval = new Array();
function callBackLoadContentbyAjax(parentObj)
{
	if(xmlHttp.readyState==complete_state)
    {
      try
      {
	      var responseValue = xmlHttp.responseText;
	      prepareResponses(responseValue);
	      var currentApplicationResponse = responseval[0];
	      var contentResponse = responseval[1];
	      var tempcontentResponse = responseval[2];
	      
	      //parentObj.getElementById("testtxt0").value=currentApplicationResponse;
	      //parentObj.getElementById("testtxt1").value=contentResponse;
	      //parentObj.getElementById("testtxt2").value=tempcontentResponse;
	      
	      setContentHtml("currentApplication",currentApplicationResponse,parentObj);	
	      setContentHtml("content",contentResponse,parentObj);	
	      setContentHtml("tempcontent",tempcontentResponse,parentObj);
      }
      catch(e){}
      hideProgressbar();
      hideAjaxProgress(parentObj);
    }	
}

function prepareResponses(tempresponseValue)
{
	var count = 0;
	var flag = true;
	var compstr = "";
	var start = new Array();
	var startTag = new Array();
	
	var last = tempresponseValue.length;
	while(flag)
	{
		compstr = '<div id="ajaxResponse'+count+'">';
		if(tempresponseValue.indexOf(compstr) != -1)
		{
			start[count] = tempresponseValue.indexOf(compstr);
			startTag[count] = compstr.length;
			count++;
		}
		else 
		flag = false;			
	}
	
	for(i=0;i<count;i++)
	{
		//alert("i= "+i+" and startvl="+start[i]+" and startTag[i]="+startTag[i]);
		if(i==1)
		responseval[i] = tempresponseValue.substr(start[i]+startTag[i],start[i+1]-start[i]-10-startTag[i]);
		else if(i<(count-1))
		responseval[i] = tempresponseValue.substr(start[i]+startTag[i],start[i+1]-start[i]-8-startTag[i]);
		else
		responseval[i] = tempresponseValue.substr(start[i]+startTag[i],last-start[i]-8-startTag[i]);
	}
	/*
	for(i=0;i<count;i++)
	{
		alert("i= "+i+" and responseval[i]=\n"+responseval[i]);
	}
	*/
	
	
}

 
function setContentHtml(div,content,parentObj) 
{
	     
	     var mycontent = content;
	     //mycontent = mycontent.replace(/contentUsingAjax=true/gi,'contentUsingAjax=false');
	     //The above line was used for display tag href
	     parentObj.getElementById(div).innerHTML=mycontent;
	    
	     var searchContent = content;
       
         var tempScriptTagStart = "<script type='text/javascript'>";
         var tempScriptTagEnd = "</script>";
         
         searchContent = searchContent.replace(/javaScript/gi,'javascript');
         searchContent = searchContent.replace(/<script>/gi,tempScriptTagStart);
         searchContent = searchContent.replace(/<SCRIPT type='text\/javascript'>/gi,tempScriptTagStart);
         searchContent = searchContent.replace(/<SCRIPT type="text\/javascript">/gi,tempScriptTagStart);
         searchContent = searchContent.replace(/<SCRIPT/gi,"<script");
         searchContent = searchContent.replace(/<\/script>/gi,tempScriptTagEnd);
         searchContent = searchContent.replace(/=javascript/gi,"='javascript'");   
                  
         var tempCssTagStart = "<style type='text/css'>";
         var tempCssTagStartWithMedia = "<style type='text/css' media='print'>";
         var tempCssTagEnd = "</style>";
         searchContent = searchContent.replace(/<style>/gi,tempCssTagStart);
         searchContent = searchContent.replace(/<STYLE type='text\/css'>/gi,tempCssTagStart);
         searchContent = searchContent.replace(/<STYLE type="text\/css">/gi,tempCssTagStart);
         searchContent = searchContent.replace(/<STYLE type="text\/css" media='print'>/gi,tempCssTagStartWithMedia);
         earchContent = searchContent.replace(/<STYLE type="text\/css" media="print">/gi,tempCssTagStartWithMedia);
         searchContent = searchContent.replace(/<STYLE type='text\/css' media='print'>/gi,tempCssTagStartWithMedia);
         earchContent = searchContent.replace(/<STYLE type='text\/css' media="print">/gi,tempCssTagStartWithMedia);
         earchContent = searchContent.replace(/media=>/gi,"media=");
         searchContent = searchContent.replace(/<\/STYLE>/gi,tempCssTagEnd);
         
                  
        
         
         addIncludedJsFiles(searchContent,parentObj);
         addIncludedCssFiles(searchContent,parentObj);
         //here settimeout is used to solve behaviour problem in lc.
         //It is because of js is not append in head and inline scripts try to call it.
         //addInlineScripts(searchContent);
         setTimeout(function(){ addInlineScripts(searchContent) }, 500); 
         addInLineCssBlcoks(searchContent,parentObj);
}

function addIncludedJsFiles(content,parentObj)
{
     var jsSearch = content;
     var jsScriptTag = jsSearch.match(/<script[^>]*src(.*?)<\/script>/g );
     var jsScriptTagArr = null;
     if(jsScriptTag != null)
     {
	 	jsScriptTagArr = jsScriptTag.toString().split(',');
     }
	
	if(jsScriptTagArr != null && jsScriptTagArr.length>0)
	{
	 for(i=0;i<jsScriptTagArr.length;i++)
	 {
		jsScriptTagArr[i] = jsScriptTagArr[i].toString().replace(/<script[^>]*src=['"]?([^'">]*)[\W\w]*/i, "$1");
		var head = parentObj.getElementsByTagName("head")[0];
		var script = parentObj.createElement("script");
		script.type = "text/javascript";
		script.src = jsScriptTagArr[i];
		head.appendChild(script);
	 }
	}
}

function addIncludedCssFiles(content,parentObj)
{
     var cssSearch = content;
     var cssSearchTag = cssSearch.match(/<link[^>]*href(.*?)>/gi );
     var cssSearchTagArr = null;
     if(cssSearchTag != null)
     { 
     	cssSearchTagArr = cssSearchTag.toString().split(',');
     }
	
	if(cssSearchTagArr != null && cssSearchTagArr.length>0)
	{
	 for(i=0;i<cssSearchTagArr.length;i++)
	 {
		cssSearchTagArr[i] = cssSearchTagArr[i].toString().replace(/<link[^>]*href=['"]?([^'">]*)[\W\w]*/i, "$1");
		var head = parentObj.getElementsByTagName("head")[0];
		var css = parentObj.createElement("link");
		css.type = "text/css";
		css.rel = "stylesheet";
		css.href = cssSearchTagArr[i];
		head.appendChild(css);
	 }
	}
}
	
function addInlineScripts(content)
{
		 var searchRegEx = content;
		 var script;
         var is_singleq = 0; var singleq = "'";
         var is_doubleq = 0; var doubleq = '"';
         var is_escaped = 0; var escap = "\\"; 
         var layer = 0;
         searchRegEx = removeComments(searchRegEx);
         while( script = searchRegEx.match(/(<script[^>]+javascript[^>]+>\s*(<!--)?)/))
         {
            searchRegEx = searchRegEx.substr(searchRegEx.indexOf(RegExp.$1) + RegExp.$1.length);
            if (!(endscript = searchRegEx.match(/((-->)?\s*<\/script>)/))) 
            	break;
            
            block = searchRegEx.substr(0, searchRegEx.indexOf(RegExp.$1));
            searchRegEx = searchRegEx.substring(block.length + RegExp.$1.length);
            block = removeComments(block);

            while(func = block.match(/(function(.+?)\((.*?)\)\s*\{)/))
            {
               //alert('withfucntion Before evaluting block =\n'+block.substr(0,block.indexOf(RegExp.$1)));
               eval(block.substr(0,block.indexOf(RegExp.$1)));
               // for evaluating non functions between fucntions
               
               block = block.substr(block.indexOf(RegExp.$1) + RegExp.$1.length);
               name = RegExp.$2;
               param = RegExp.$3;

               is_singleq = 0;
               is_doubleq = 0;
               is_escaped = 0;
               layer = 0;
               

               for(i=0;i<block.length;i++) 
               {
                  c = block.substr(i,1);
                  if ((is_singleq || is_doubleq) && is_escaped) 
                  {
                     is_escaped = 0;
                  } 
                  else if (!is_doubleq && (c==singleq)) 
                  {
                     is_singleq = !is_singleq;
                  }
                  else if (!is_singleq && (c==doubleq)) 
                  {
                     is_doubleq = !is_doubleq;
                  }
                  else if ((is_singleq || is_doubleq) && (c==escap)) 
                  {
                     is_escaped = 1;
                  }
                  else if ( c=="{") 
                  {
                     layer++;
                  } 
                  else if ( c=="}") 
                  {
                     if ( layer==0 ) 
                     {
                        break;
                     }
                     layer--;
                  }
               }
               

               code = block.substr(0,i-1);
               block = block.substr(i +1);
				
			   code = code.replace(/\\n/g, '#!N!#');
               code = code.replace(/\n/g, '\\n');
               code = code.replace(/#!N!#/g, '\\\\n');
               code = code.replace(/\\r/g, '#!R!#');
               code = code.replace(/\r/g, '\\r');
               code = code.replace(/#!R!#/g, '\\\\r');
               code = code.replace(/\"/g,'\\"');
               code = code.replace(/\\'/g,"`");
               //this will work only for alert confirm msg means where only view of ' is required
               code = code.replace(/'/g,"\\'");
               code = code.replace(/\//g,"\\/");
               //this is added to solve when regexp crated using /string/ and it is using /as ending tag 
               //alert("fun name="+name + "\n code=\n"+code);
               eval(name + " = new Function('"+param+"','"+code+"');");
               block = removeComments(block);
            }
            //alert('Nonfucntion Before evaluting block =\n'+block);
            eval(block); // for evaluating non functions
         }
}	

function addInLineCssBlcoks(content,parentObj)
{
	 var searchRegEx = content;	
     var css;
     
     while( css = searchRegEx.match(/(<style[^>]+text\/css[^>]+>\s*(<!--)?)/))
     {
        cssBlcokTagArr = css.toString().split(',');
        if(cssBlcokTagArr != null && cssBlcokTagArr.length>0)
		{
	 		for(i=0;i<cssBlcokTagArr.length;i++)
	 		{
				cssBlcokTagArr[i] = cssBlcokTagArr[i].toString().replace(/<style[^>]*text\/css=['"]?([^'">]*)[\W\w]*/i, "$1");
	 		}
		}
        searchRegEx = searchRegEx.substr(searchRegEx.indexOf(RegExp.$1) + RegExp.$1.length);
        if (!(endcss = searchRegEx.match(/((-->)?\s*<\/style>)/))) break;
        block = searchRegEx.substr(0, searchRegEx.indexOf(RegExp.$1));
        searchRegEx = searchRegEx.substring(block.length + RegExp.$1.length);
        var cssBlock = parentObj.createElement('style');
		cssBlock.setAttribute("type", "text/css");
		if(cssBlcokTagArr[0].toString().indexOf("media") != -1)
		{
			mediaStart = cssBlcokTagArr[0].toString().indexOf("media")+7;
			mediaLength = 5;
			media = cssBlcokTagArr[0].substr(mediaStart,mediaLength);
			cssBlock.setAttribute("media", media);
		}
		cssBlock.styleSheet.cssText = block;
		var head = parentObj.getElementsByTagName('head')[0];
		head.appendChild(cssBlock);
     }
}

function removeComments(str) 
{
    str = ('__' + str + '__').split('');
    var mode = {
        singleQuote: false,
        doubleQuote: false,
        regex: false,
        blockComment: false,
        lineComment: false,
        condComp: false 
    };
    for (var i = 0, l = str.length; i < l; i++) {
 
        if (mode.regex) {
            if (str[i] === '/' && str[i-1] !== '\\') {
                mode.regex = false;
            }
            continue;
        }
 
        if (mode.singleQuote) {
            if (str[i] === "'" && str[i-1] !== '\\') {
                mode.singleQuote = false;
            }
            continue;
        }
 
        if (mode.doubleQuote) {
            if (str[i] === '"' && str[i-1] !== '\\') {
                mode.doubleQuote = false;
            }
            continue;
        }
 
        if (mode.blockComment) {
            if (str[i] === '*' && str[i+1] === '/') {
                str[i+1] = '';
                mode.blockComment = false;
            }
            str[i] = '';
            continue;
        }
 
        if (mode.lineComment) {
            if (str[i+1] === '\n' || str[i+1] === '\r') {
                mode.lineComment = false;
            }
            str[i] = '';
            continue;
        }
 
        if (mode.condComp) {
            if (str[i-2] === '@' && str[i-1] === '*' && str[i] === '/') {
                mode.condComp = false;
            }
            continue;
        }
 
        mode.doubleQuote = str[i] === '"';
        mode.singleQuote = str[i] === "'";
 
        if (str[i] === '/') {
 
            if (str[i+1] === '*' && str[i+2] === '@') {
                mode.condComp = true;
                continue;
            }
            if (str[i+1] === '*') {
                str[i] = '';
                mode.blockComment = true;
                continue;
            }
            if (str[i+1] === '/') {
                str[i] = '';
                mode.lineComment = true;
                continue;
            }
            mode.regex = true;
 
        }
 
    }
    return str.join('').slice(2, -2);
}

  ObjformNo = "0";
  setFormForAjaxSubmitCalled = false;
  function setFormForAjaxSubmit(Objform)
  {
  	setFormForAjaxSubmitCalled = true;
  	for(i=0;i<document.forms.length;i++)
  	{
  		if(document.forms[i] == Objform)
  			ObjformNo = i;
  	}
  }
  
  pageSubmitFlagTemp = pageSubmitFlag;
  function enableAjaxSubmit(flag)
  {
  	pageSubmitFlagTemp = flag;
  	if(!controlAjaxSubmit || !flag)
  	{
  		return;
  	}
 
  	for(i=0;i<document.forms.length;i++)
  	{
  		document.forms[i].submit = function(){doAjaxSubmit();};	
  		//document.forms[i].onsubmit = function(){doAjaxSubmit();};
  	}
  	if(!setFormForAjaxSubmitCalled)
  		ObjformNo = 0;
	pageSubmitFlagTemp = pageSubmitFlag;  		
  }
  
 
  
function doAjaxSubmit()
{
  	var loadContentbyAjaxUrl = document.forms[0].action ;
  	if(ObjformNo != 0)
  	{
	  	for(i=0;i<document.forms.length;i++)
	  	{
	  		if( i == ObjformNo)
	  		{
	  			loadContentbyAjaxUrl = document.forms[i].action;
	  			break;
	  		}
	  	}
  	}
   	loadContentbyAjax(loadContentbyAjaxUrl,document,false);
   	//setAjaxSubmitCalled = false;
  	return;
}

function getThisPageHref()
{
	return 	document.getElementById('windowLocationHref').value;
}

function getParentPageHref(parentWindow)
{
	return 	parentWindow.document.getElementById('windowLocationHref').value;
}
  
function reloadThisPage(flag)
{
	if(!controlAjaxSubmit || !flag)
	{
		window.location.reload();
		return;
	}
	var windowLocationHref = getThisPageHref();
	loadContentbyAjax(windowLocationHref,document,true);
}

function reloadParentPage(parentWindow,flag)
{
	if(!controlAjaxSubmit || !flag)
	{
		parentWindow.location.reload();
		return;
	}
	
	var windowLocationHref = getParentPageHref(parentWindow);
	parentWindow.loadContentbyAjax(windowLocationHref,parentWindow.document,true);
}


function reloadThisPageUsingHref(urlStr,flag)
{
	if(!controlAjaxSubmit || !flag)
	{
		document.getElementById('windowLocationHref').value = urlStr ;
		window.location.href = urlStr;
		return;
	}
	loadContentbyAjax(urlStr,document,true);	
}

function reloadParentPageUsingHref(urlStr,parentWindow,flag)
{
	if(!controlAjaxSubmit || !flag)
	{
		parentWindow.document.getElementById('windowLocationHref').value = urlStr ;
		parentWindow.location.href = urlStr;
		return;
	}
	parentWindow.loadContentbyAjax(urlStr,parentWindow.document,true);
}

/*
function showAjaxProgress(parentObj)
{
	if(parentObj.getElementById("ajaxLoadingImage") != null)
	parentObj.getElementById("ajaxLoadingImage").style.display = "block";
	if(parentObj.getElementById("nav") != null)
	parentObj.getElementById("nav").style.display = "none";
}

function hideAjaxProgress(parentObj)
{
	if(parentObj.getElementById("ajaxLoadingImage") != null)
	parentObj.getElementById("ajaxLoadingImage").style.display = "none";
	if(parentObj.getElementById("nav") != null)
	parentObj.getElementById("nav").style.display = "block";
}
*/

function getContentUsingAjaxStatus(urlStr)
{
	var outer = null;
	var inner = null;
	var flag = contentUsingAjax;

	outer = urlStr.split("&");
	if(outer != null && outer.length > 0)
	{
		for(i=0;i<outer.length;i++)
		{
			inner = outer[i].split("=");
			if(inner != null && inner.length > 0)
			{
				if(inner[0] == 'contentUsingAjax')
				{
					if(inner[1] == 'true' || inner[1] == 'false')
					{	
						if(inner[1] == 'true')
							flag = true;
						else if(inner[1] == 'false')
							flag = false;
						break;
					}	
				}
			}	
		}
	}
	//for reporting framework parameterpage
	/*
	if(urlStr.indexOf("actionFlag=reportService") != -1 &&  urlStr.indexOf("action=parameterPage") != -1)
	{
		flag = true;
	}
	*/
	return flag;
}  

function getdialogview(urlStr)
{
	var outer = null;
	var inner = null;
	var flag = false;
	outer = urlStr.split("&");
	if(outer != null && outer.length > 0)
	{
		for(i=0;i<outer.length;i++)
		{
			inner = outer[i].split("=");
			if(inner != null && inner.length > 0)
			{
				if(inner[0] == 'dialogview')
				{
					if(inner[1] == 'true' || inner[1] == 'false')
					{	
						if(inner[1] == 'true')
							flag = true;
						else if(inner[1] == 'false')
							flag = false;
						break;
					}	
				}
			}	
		}
	}
	return flag;
} 

function showAjaxProgress(parentObj)
{
	if(!showAjaxProgressBarFlag)
	return;	
	disableBackground();
	var message = 'Please wait<br>Your Request is in progress';
	var statusBar =	parentObj.getElementById('statusbar');
	var statusbarwidth = 360;
	var statusbarheight = 100;
	statusBar.style.visibility = 'visible';
	parentObj.getElementById('statuBarTd1').innerHTML = message;
	statusBar.style.left = ((parentObj.body.offsetWidth - statusbarwidth)/2)+'px';
	var st = parentObj.documentElement.scrollTop;
	var sh = parentObj.documentElement.scrollHeight;
	var ch = parentObj.documentElement.clientHeight;
	statusBar.style.top = (st + ((ch - statusbarheight)/2))+'px';
	statusBar.style.top = ( (sh/2) - (statusbarheight/2) )+'px';
	//if(parentObj.getElementById("nav") != null)
	//parentObj.getElementById("nav").style.display = "none";
}

function hideAjaxProgress(parentObj)
{
	if(parentObj.getElementById('statusbar')!= null)
    {
		parentObj.getElementById('statusbar').style.visibility='hidden';
		//if(parentObj.getElementById("nav") != null)
		//parentObj.getElementById("nav").style.display = "block";
	}
	enableBackground();
}

function getCloseParentWindow(urlStr)
{
	var outer = null;
	var inner = null;
	var flag = false;
	outer = urlStr.split("&");
	if(outer != null && outer.length > 0)
	{
		for(i=0;i<outer.length;i++)
		{
			inner = outer[i].split("=");
			if(inner != null && inner.length > 0)
			{
				if(inner[0] == 'closeparentwindow')
				{
					if(inner[1] == 'true' || inner[1] == 'false')
					{	
						if(inner[1] == 'true')
							flag = true;
						else if(inner[1] == 'false')
							flag = false;
						break;
					}	
				}
			}	
		}
	}
	return flag;
} 
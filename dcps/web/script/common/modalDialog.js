function DetectBrowser()
{
	var user_Agent=navigator.userAgent.toLowerCase();
	// For Navigator 
	var is_ie;
	var is_nav  = ((user_Agent.indexOf('mozilla')!=-1) && (user_Agent.indexOf('spoofer')==-1)
                && (user_Agent.indexOf('compatible') == -1) && (user_Agent.indexOf('opera')==-1)
                && (user_Agent.indexOf('webtv')==-1) && (user_Agent.indexOf('hotjava')==-1));
                
    // For Interner Explorer
    this.is_ie     = ((user_Agent.indexOf("msie") != -1) && (user_Agent.indexOf("opera") == -1)
    				&& (user_Agent.indexOf("firefox") == -1) );
   				
    // For AOL
    is_aol   = (user_Agent.indexOf("aol") != -1);
    
    // For Opera
    is_opera = (user_Agent.indexOf("opera") != -1);
    
    // For Web-TV
    is_webtv = (user_Agent.indexOf("webtv") != -1); 
    
    // For Hot Java
    is_hotjava = (user_Agent.indexOf("hotjava") != -1);
}


function displayModalDialog(url,iwin_name,istyle)
{	
	url = url+"&skip=dialog";
	var width = 1050;
	var height = 900;
	var top = 0;
	var left = 0;
	var resize = 0;
	var scroll = 1;
	var style = istyle;
	var sName = "";
	var status = 1;
	var win_name = "";
	var min = "yes";
	
	try
	{
		if(iwin_name != "")
		{
			var temp = iwin_name.toLowerCase();
			
			if(temp.search("=") != -1 || temp.search(",") != -1)
			{
				if(temp.search("width") != -1 || temp.search("height") != -1 || temp.search("resizable") != -1 || temp.search("status") != -1 || temp.search("top") != -1 || temp.search("left") != -1)
				{
					style = iwin_name;
				}
			}
			else
			{
				win_name = iwin_name;
			}
		}
	}
	catch(e)
	{
		win_name = "";
	}
	
	try
	{
		if(style != "" )
		{
			var options = style.split(",");
			
			for(var i=0;i<options.length;i++)
			{
			
				var temp =(options[i]).toLowerCase();
				
				if(temp.search("width") != -1)
				{
					width = eval(temp);
				}
				if(temp.search("height") != -1)
				{
					height = eval(temp);
				}
				if(temp.search("top") != -1)
				{
					top = eval(temp);
				}
				if(temp.search("left") != -1)
				{
					left = eval(temp);
				}
				if(temp.search("resizable") != -1)
				{
					if(temp.search("yes") != -1)
					{
						resize = "yes";
					}
					else if(temp.search("no") != -1)
					{
						resize = "no";
					}
					else
					{
						resize = eval(temp);
					}
				}
				if(temp.search("status") != -1)
				{
					if(temp.search("yes") != -1)
					{
						status = "yes";
					}
					else if(temp.search("no") != -1)
					{
						status = "no";
					}
					else
					{
						status = eval(temp);
					}
				}
				if(temp.search("scrollbars") != -1)
				{
					if(temp.search("yes") != -1)
					{
						scroll = "yes";
					}
					else if(temp.search("no") != -1)
					{
						scroll = "no";
					}
					else
					{
						scroll = eval(temp);
					}
					scroll="yes";
				}
				
				if(temp.search("minimize") != -1)
				{
					if(temp.search("yes") != -1)
					{
						min = "yes";
					}
					else if(temp.search("no") != -1)
					{
						min = "no";
					}
					else
					{
						min = eval(temp);
					}
				}
				
				
			}
		}
	}// try 
	catch(e)
	{}
	
	//var ie = getBrowserUsed();
	var browsers = new DetectBrowser();   
	if(browsers.is_ie)
	{
		window.showModalDialog(url,window,'resizable: ' + resize + ';scroll: ' + scroll + ';dialogWidth: ' + width + ' px;status: '+ status +';dialogHeight: ' + height + ' px;dialogLeft: '+ left + 'px;dialogTop: ' + top + 'px;maximize: ' + resize + ' ;minimize: ' + min + ';');
		
	}
	else
	{

		window.open(url,win_name,style);
		
	}

} // end of  showHdiitModalDialog(url,input1,input2)


// To fix the problem of Submitting page in IE 7.0

function setWindowName(window,formObj)
{
	var formName = formObj.name;
	var winName = formName + "_WinName";
	window.name = winName
	formObj.target=winName;
	var anchs = formObj.getElementsByTagName('a');
	for(i=0;i<anchs.length;i++)
	{
		anchs[i].target = window.name;
	}
}



// To fix the Problem of window.location.navigate(), assign(), replace(), locatin.href

function fixDialog()
{

	goToURL = function(p_strURL){
			var oLink = document.createElement("A");
			document.body.insertAdjacentElement('beforeEnd', oLink);
			with(oLink){
				href = p_strURL;
				click();
			}
		}
		
		window.navigate = goToURL; 
		window.location.assign = goToURL; 
		window.location.replace = goToURL; 
		window.location.reload = function(){window.navigate(window.location.href)}
		

}


function openDrillDownReport(url)
{
	var dt = new Date();
	wndName = "hdiits" + dt.getMilliseconds();
	var childWindow = window.open(url,wndName,'width=610, height=280, top=0, left=0, resizable=no, titlebar=no, menubar=no, scrollbars=yes, toolbar=no');
	childWindow.moveTo( 0, 0 );
    childWindow.resizeTo( screen.availWidth, screen.availHeight );
    childWindow.focus();    
    
    win = top;
	win.opener = top;	
	win.close ();	
}

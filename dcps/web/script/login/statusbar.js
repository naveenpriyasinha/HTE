//document.write("<div id='statusbar'><table><tr><td rowspan=2 id='imgtd'>&nbsp;</td><td align='left' valign='middle'>Please wait</td></tr><tr><td align='left' valign='middle'>Your Request is in progress...</td></tr></table></div>");
//document.write("<div id='statusbar'><table><tr><td id='imgtd'>&nbsp;</td><td align='left' valign='middle' id='statuBarTd1'></td></tr></table></div>");

function showProgressbar(message)
{
	if(!message)
	{
		if(document.getElementById('hidnLangIdReq') != null)
		{
			var langIdReq = document.getElementById('hidnLangIdReq').value ;
			if(langIdReq == 2)
			{
				message = document.getElementById('hidnMsgDispl').value ;
			}
			else
			{
				message = 'Please wait<br>Your Request is in progress...';
			}
		}
		else
		{
			message = 'Please wait<br>Your Request is in progress...';
		}
	}
	
	disableBackground();

	var statusBar =	document.getElementById('statusbar');
	var statusbarwidth = 360;
	var statusbarheight = 100;
	
	statusBar.style.visibility = 'visible';
	document.getElementById('statuBarTd1').innerHTML = message;
	
	//document.getElementById('statuBarTd2').innerHTML = 'Your Request is in progress...';		
    
	statusBar.style.left = ((document.body.offsetWidth - statusbarwidth)/2)+'px';
	var st = document.documentElement.scrollTop;
	var sh = document.documentElement.scrollHeight;
	var ch = document.documentElement.clientHeight;
	statusBar.style.top = (st + ((ch - statusbarheight)/2))+'px';
}
function hideProgressbar()
{
    if(document.getElementById('statusbar')!= null)
    {
		document.getElementById('statusbar').style.visibility='hidden';
		enableBackground();
	 }
}

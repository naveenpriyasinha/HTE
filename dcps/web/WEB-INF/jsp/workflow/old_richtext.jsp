<%
try{	
%>
<%@ page import="com.tcs.sgv.wf.interfaces.WFConstants"%>
<%@ page import="java.util.ResourceBundle" %>
<%@page import="java.util.Enumeration"%>
<html>
<head>
<STYLE TYPE="text/css">
.rteImage {
	background: #D3D3D3;
	border: 1px solid #D3D3D3;
	cursor: pointer;
	cursor: hand;
}

.rteImageRaised, .rteImage:hover {
	background: #D3D3D3;
	border: 1px outset;
	cursor: pointer;
	cursor: hand;
}

.rteImageLowered, .rteImage:active {
	background: #D3D3D3;
	border: 1px inset;
	cursor: pointer;
	cursor: hand;
}

.rteVertSep {
	margin: 0 0px 0 0px;
}

.rteBack {
	background: #D3D3D3;
	border: 1px outset;
	letter-spacing: 0;
	padding: 0px;
}

.rteBack tbody td, .rteBack td {
	background: #D3D3D3;
	padding: 0;
}

.rteDiv {
	
	position: relative;
}
</STYLE>
</head>
<script>

// Cross-Browser Rich Text Editor
// http://www.kevinroth.com/rte/demo.htm
// Written by Kevin Roth (kevin@NOSPAMkevinroth.com - remove NOSPAM)
//init variables
var isRichText = false;
var rng;
var currentRTE;
var allRTEs = "";

var isIE;
var isGecko;
var isSafari;
var isKonqueror;

var imagesPath;
var includesPath;
var cssFile;


function initRTE(imgPath, incPath, css) {

	//set browser vars
	var ua = navigator.userAgent.toLowerCase();
	isIE = ((ua.indexOf("msie") != -1) && (ua.indexOf("opera") == -1) && (ua.indexOf("webtv") == -1)); 
	isGecko = (ua.indexOf("gecko") != -1);
	isSafari = (ua.indexOf("safari") != -1);
	isKonqueror = (ua.indexOf("konqueror") != -1);
	
	//check to see if designMode mode is available
	if (document.getElementById && document.designMode && !isSafari && !isKonqueror) {
		isRichText = true;
	}
	
	if (!isIE) document.captureEvents(Event.MOUSEOVER | Event.MOUSEOUT | Event.MOUSEDOWN | Event.MOUSEUP);
	document.onmouseover = raiseButton;
	document.onmouseout  = normalButton;
	document.onmousedown = lowerButton;
	document.onmouseup   = raiseButton;
	
	//set paths vars
	imagesPath = imgPath;
	includesPath = incPath;
	cssFile = css;
	
	if (isRichText) document.writeln('<style type="text/css">@import "' + includesPath + 'rte.css";</style>');
	
	//for testing standard textarea, uncomment the following line
	//isRichText = false;
}

function writeRichText(rte, html, width, height, buttons, readOnly) {

	if (isRichText) {
	
		if (allRTEs.length > 0) allRTEs += ";";
		allRTEs += rte;
		writeRTE(rte, html, width, height, buttons, readOnly);
	} else {
	
		writeDefault(rte, html, width, height, buttons, readOnly);
	}
}

function writeDefault(rte, html, width, height, buttons, readOnly) {
	if (!readOnly) {
		document.writeln('<textarea name="' + rte + '" id="' + rte + '" style="width: ' + width + 'px; height: ' + height + 'px;">' + html + '</textarea>');
	} else {
		document.writeln('<textarea name="' + rte + '" id="' + rte + '" style="width: ' + width + 'px; height: ' + height + 'px;" readonly>' + html + '</textarea>');
	}
}

function raiseButton(e) {
	if (isIE) {
		var el = window.event.srcElement;
	} else {
		var el= e.target;
	}
	
	className = el.className;
	if (className == 'rteImage' || className == 'rteImageLowered') {
		el.className = 'rteImageRaised';
	}
}

function normalButton(e) {
	if (isIE) {
		var el = window.event.srcElement;
	} else {
		var el= e.target;
	}
	
	className = el.className;
	if (className == 'rteImageRaised' || className == 'rteImageLowered') {
		el.className = 'rteImage';
	}
}

function lowerButton(e) {
	if (isIE) {
		var el = window.event.srcElement;
	} else {
		var el= e.target;
	}
	
	className = el.className;
	if (className == 'rteImage' || className == 'rteImageRaised') {
		el.className = 'rteImageLowered';
	}
}

function writeRTE(rte, html, width, height, buttons, readOnly) {
	if (readOnly) buttons = false;
	
	//adjust minimum table widths
	if (isIE) {
		if (buttons && (width < 600)) width = '95%';
		var tablewidth = width;
	} else {
		if (buttons && (width < 500)) width = '95%';
		var tablewidth = width + 4;
	}
	
	if (buttons == true) {
		document.writeln('<table class="rteBack" cellpadding="0" cellspacing="0" id="Buttons2_' + rte + '" width="' + tablewidth + '">');
		document.writeln('	<tr >');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'bold.gif" width="25" height="24" alt="Bold"  title="bold" onClick="FormatText(\'' + rte + '\', \'bold\', \'\')"></td>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'italic.gif" width="25" height="24" alt="Italic" onClick="FormatText(\'' + rte + '\', \'italic\', \'\')"></td>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'underline.gif" width="25" height="24" alt="Underline" title="underline" onClick="FormatText(\'' + rte + '\', \'underline\', \'\')"></td>');
		
		//Added By Core Ahmedabad For Justify Full Option 
        if (isIE) {
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'spellcheck.gif" width="25" height="24" alt="Spell Check" title="Spell Check" onClick="checkspell()"></td>');			
		}   
		document.writeln('		<td><div id="forecolor_' + rte + '"><img class="rteImage" src="' + imagesPath + 'textcolor.gif" width="25" height="24" alt="color" title="color" onClick="FormatText(\'' + rte + '\', \'forecolor\', \'\')"></div></td>');
		document.writeln('		<td><div id="hilitecolor_' + rte + '"><img class="rteImage" src="' + imagesPath + 'bgcolor.gif" width="25" height="24" alt="bgcolor" title="bgcolor" onClick="FormatText(\'' + rte + '\', \'hilitecolor\', \'\')"></div></td>');
    	document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'justifyfull.gif" width="25" height="24" alt="Justify Full" title="Justify Full"  onClick="FormatText(\'' + rte + '\', \'justifyfull\', \'\')"></td>');
    	document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'left_just.gif" width="25" height="24" alt="left" title="left" onClick="FormatText(\'' + rte + '\', \'justifyleft\', \'\')"></td>');
		document.writeln('		<td><img class="rteImages" src="' + imagesPath + 'centre.gif" width="25" height="24" alt="center" title="center" onClick="FormatText(\'' + rte + '\', \'justifycenter\', \'\')"></td>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'right_just.gif" width="25" height="24" alt="right" title="right" onClick="FormatText(\'' + rte + '\', \'justifyright\', \'\')"></td>');
		
   	    document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'insert_table.gif" width="25" height="24" alt="Insert Table" title="Insert Table" onClick="dlgInsertTable(\'' + rte + '\', \'table\', \'\')"></td>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'numbered_list.gif" width="25" height="24" alt="Olist" title="Olist" onClick="FormatText(\'' + rte + '\', \'insertorderedlist\', \'\')"></td>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'list.gif" width="25" height="24" alt="Ulist" title="Ulist" onClick="FormatText(\'' + rte + '\', \'insertunorderedlist\', \'\')"></td>');
		//document.writeln('		<td><img class="rteVertSep" src="' + imagesPath + 'blackdot.gif" width="1" height="20" border="0" alt=""></td>');
		
   // document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'hyperlink.gif" width="25" height="24" alt="insert link" title="link" onClick="FormatText(\'' + rte + '\', \'createlink\')"></td>');
   // document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'internet.gif" width="15" height="12" alt="insert web link" title="web link" onClick="dlgInsertLink(\'' + rte + '\', \'link\')"></td>');
		//document.writeln('		<td><img class="rteVertSep" src="' + imagesPath + 'blackdot.gif" width="1" height="20" border="0" alt=""></td><td></td>');
		//document.writeln('<td width="100%"></td>');
		document.writeln('		<td width="100%">');
					document.writeln('		</td>');
					document.writeln('	</tr>');
					document.writeln('</table>');
					document.writeln('<table class="rteBack" cellpadding="0" cellspacing="0" id="Buttons2_' + rte + '" width="' + tablewidth + '">');
					document.writeln('	<tr>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'cut.gif" width="25" height="24" alt="cut" title="cut" onClick="FormatText(\'' + rte + '\', \'cut\')"></td>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'copy.gif" width="25" height="24" alt="copy" title="copy" onClick="FormatText(\'' + rte + '\', \'copy\')"></td>');
		document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'paste.gif" width="25" height="24" alt="paste" title="paste" onClick="FormatText(\'' + rte + '\', \'paste\')"></td>');
    
    					document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'hr.gif" width="25" height="24" alt="Horizontal Rule" title="Horizontal Rule" onClick="FormatText(\'' + rte + '\', \'inserthorizontalrule\', \'\')"></td>');
		document.writeln('		<td>');
  
		document.writeln('			<select id="fontname_' + rte + '" onchange="Select(\'' + rte + '\', this.id)">');
    document.writeln('				<option value="Font" selected>[Font]</option>');
    <%
    ResourceBundle lResourceBundle1 =  ResourceBundle.getBundle(WFConstants.WFCONSTANTS_PROPERTYFILE_PATH);  
    ResourceBundle lResourceBundle2 =  ResourceBundle.getBundle("resources/WFLables_en_US"); 
	  int count=1;
	  Enumeration strings=lResourceBundle1.getKeys();
	  while(strings.hasMoreElements())
	  {
		  String str=(String)strings.nextElement();		  
		  if(str.substring(0,4).equalsIgnoreCase("font"))
			  count++;
	  }	  
	  String objString=new  String();
   for(int i=1;i<count;i++)
   {
	   objString=lResourceBundle1.getString(("font")+Integer.toString(i));	  
	   int index =objString.indexOf(",");
	   String value =objString.substring(0,index);	  
	   value.trim();
	%>
      
       document.writeln('<option value="<%=objString%>"> <%=value%></option>');	
      
    <%
      } 
  
    %>
		document.writeln('</select>');
		document.writeln('</td>');
		
		document.writeln('<td>');
   
		document.writeln('<select unselectable="on" id="fontsize_' + rte + '" onchange="Select(\'' + rte + '\', this.id);">');
    	lcounter = 0;  
<%
    
   //String[] objArrayList2=lResourceBundle2.getStringArray("size");        
   //int len2=objArrayList2.length;
   //if(len2!=0){
	   int count1=1;
	  Enumeration strings1=lResourceBundle1.getKeys();
	  while(strings1.hasMoreElements())
	  {
		  String str=(String)strings1.nextElement();		
		  if(str.substring(0,4).equalsIgnoreCase("size"))
			  count1++;
	  }
   for(int i=1;i<count1;i++)
   {
	   objString=lResourceBundle1.getString(("size")+Integer.toString(i));
	  
	%>
      lcounter = lcounter+1;
      if (lcounter == 1)
      {
        document.writeln('<option value="<%=objString%>"---- selected---- ><%=objString%></option>');
      }
      else
      {
        document.writeln('<option value="<%=objString%>" ><%=objString%></option>');	
      }
    <%
      }   
    %>
   		document.writeln('</select>');
    	document.writeln('</td>');  
		document.writeln('<td width="100%"></td>');
		document.writeln('<tr>'); 
        document.writeln('<td colspan=22 >'); 
		document.writeln('<select unselectable="on" id="StdNotings_' + rte + '" onchange="fn_ShowStdNotings(\'' + rte + '\', this.id);" STYLE="width:600%">');     
        document.writeln('<option value="StdNotings" >[Standred Noting]</option>');	        
<%    
	int count2=-1;
	Enumeration strings2=lResourceBundle2.getKeys();
	while(strings2.hasMoreElements())
	{
		  String str=(String)strings2.nextElement();		
		  if(str.substring(0,4).equalsIgnoreCase("noti"))
			  count2++;
	}  
   for(int i=1;i<count2;i++)
   {
	   objString=lResourceBundle2.getString(("notings")+Integer.toString(i));
	  
	%>  
        document.writeln('<option value="<%=objString%>" ><%=objString%></option>');	    
    <%
      }   
    %>
    document.writeln('</select>');
    document.writeln('</td>'); 
    document.writeln('</tr>'); 
   
    //End - for Standard Notings by Kanan Vaidya (Ahmedabad) on 28 Dec 2005
        
		document.writeln('</table>');
	}
	document.writeln('<iframe id="' + rte + '" name="' + rte + '" width="' + width + 'px" height="' + height + 'px"  onblur= "updateRTE(id);fn_OnChange(id);" onfocus= hidePallete(id);></iframe>'); //modified to solve paste notings problem - Kanan Vaidya - Ahmedabad - 16 Mar 2006
	//if (!readOnly) document.writeln('<br /><input type="checkbox" id="chkSrc' + rte + '" onclick="toggleHTMLSrc(\'' + rte + '\');" />&nbsp;View Source');
					
	document.writeln('<iframe width="154" height="104" id="cp' + rte + '" src="common/palette.html" marginwidth="0" marginheight="0" scrolling="no" style="visibility:hidden; position: absolute;"></iframe>');
	document.writeln('<input type="hidden" id="hdn' + rte + '" name="' + rte + '" value="">');
	document.getElementById('hdn' + rte).value = html;
	enableDesignMode(rte, html, readOnly);
}

function enableDesignMode(rte, html, readOnly) {
	var frameHtml = "<html id=\"" + rte + "\">\n";
	frameHtml += "<head>\n";
	//to reference your stylesheet, set href property below to your stylesheet path and uncomment
	if (cssFile.length > 0) {
		frameHtml += "<link media=\"all\" type=\"text/css\" href=\"" + cssFile + "\" rel=\"stylesheet\">\n";
	} else {
		frameHtml += "<style>\n";
		frameHtml += "body {\n";
		frameHtml += "	background: #FFFFFF;\n";
    frameHtml += "	font-size: 18px;\n";
		frameHtml += "	margin: 0px;\n";
		frameHtml += "	padding: 0px;\n";
		frameHtml += "}\n";
    frameHtml += "p{margin: 0px}\n";
		frameHtml += "</style>\n";
	}
  frameHtml += "</head>\n";
  frameHtml += "<body>\n";
	frameHtml += html + "\n";
	frameHtml += "</body>\n";
	frameHtml += "</html>";
	
	if (document.all) {
    var oRTE = frames[rte].document;
		oRTE.open();
		oRTE.write(frameHtml);
		oRTE.close();
		if (!readOnly) oRTE.designMode = "On";
   	} else {
		try {
			if (!readOnly) document.getElementById(rte).contentDocument.designMode = "on";
			try {
				var oRTE = document.getElementById(rte).contentWindow.document;
				oRTE.open();
				oRTE.write(frameHtml);
				oRTE.close();
				if (isGecko && !readOnly) {
					//attach a keyboard handler for gecko browsers to make keyboard shortcuts work
          oRTE.addEventListener("keypress", kb_handler, true);
				}
			} catch (e) {
				alert("string message");
			}
		} catch (e) {
			//gecko may take some time to enable design mode.
			//Keep looping until able to set.
			if (isGecko) {
        setTimeout("enableDesignMode('" + rte + "', '" + html + "', " + readOnly + ");", 10);
			} else {
				return false;
			}
		}
	}
}

function updateRTEs() {
  
	var vRTEs = allRTEs.split(";");
	for (var i = 0; i < vRTEs.length; i++) {
		updateRTE(vRTEs[i]);
	}
}

//start - added to solve paste notings problem - Kanan Vaidya - Ahmedabad - 16 Mar 2006
function fn_OnChange(rte)
{  
  var obj;
  if (document.all) 
  {    
    obj = frames[rte].document.getElementsByTagName("p");      
	} 
  else 
  {     
    obj = rte.contentWindow.document.getElementsByTagName("p");  
	}    
  var li=0; 
  if(obj!=null)
  {
    for(li=0;li<obj.length;li++) 
    {   
      obj[li].style.margin='0 px';   
      obj[li].style.textIndent='0 px';    
    }
  }
}
//end - added to solve paste notings problem - Kanan Vaidya - Ahmedabad - 16 Mar 2006

function updateRTE(rte) {  
	if (!isRichText) return;
	 
	//set message value
	var oHdnMessage = document.getElementById('hdn' + rte);
	var oRTE = document.getElementById(rte);
	var readOnly = false;
	
	//check for readOnly mode
	if (document.all) {
    if (frames[rte].document.designMode != "On") readOnly = true;
	} else {
		if (document.getElementById(rte).contentDocument.designMode != "on") readOnly = true;
	}
	
	if (isRichText && !readOnly) {
		//if viewing source, switch back to design view
   /*
		if (document.getElementById("chkSrc" + rte).checked) {
			document.getElementById("chkSrc" + rte).checked = false;
     	toggleHTMLSrc(rte);
		}*/
		
		if (oHdnMessage.value == null) oHdnMessage.value = "";
		if (document.all) {    
			oHdnMessage.value = frames[rte].document.body.innerHTML;
		} else {			
      oHdnMessage.value = oRTE.contentWindow.document.body.innerHTML;
		}
		
		//if there is no content (other than formatting) set value to nothing
		if (stripHTML(oHdnMessage.value.replace("&nbsp;", " ")) == "" 
			&& oHdnMessage.value.toLowerCase().search("<hr") == -1
			&& oHdnMessage.value.toLowerCase().search("<img") == -1) oHdnMessage.value = "";
		//fix for gecko
		if (escape(oHdnMessage.value) == "%3Cbr%3E%0D%0A%0D%0A%0D%0A") oHdnMessage.value = "";
	}
}

function toggleHTMLSrc(rte) {
	//contributed by Bob Hutzel (thanks Bob!)
	var oRTE;
	if (document.all) {
		oRTE = frames[rte].document;
	} else {
		oRTE = document.getElementById(rte).contentWindow.document;
	}
	if (document.getElementById("chkSrc" + rte).checked) {
		document.getElementById("Buttons1_" + rte).style.visibility = "hidden";
		document.getElementById("Buttons2_" + rte).style.visibility = "hidden";
		if (document.all) {
			oRTE.body.innerText = oRTE.body.innerHTML;
		} else {
			var htmlSrc = oRTE.createTextNode(oRTE.body.innerHTML);
			oRTE.body.innerHTML = "";
			oRTE.body.appendChild(htmlSrc);
		}
	} else { 
	//alert(document.getElementById("Buttons1_" + rte));
		document.getElementById("Buttons1_" + rte).style.visibility = "visible";
		document.getElementById("Buttons2_" + rte).style.visibility = "visible";
		if (document.all) {
			//fix for IE
			var output = escape(oRTE.body.innerText);
			output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
			output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");
			
			oRTE.body.innerHTML = unescape(output);
		} else {
			var htmlSrc = oRTE.body.ownerDocument.createRange();
			htmlSrc.selectNodeContents(oRTE.body);
			oRTE.body.innerHTML = htmlSrc.toString();
		}
	}
}

//Function to format text in the text box
function FormatText(rte, command, option) {
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
		
		//get current selected range
		var selection = oRTE.document.selection; 
		if (selection != null) {
			rng = selection.createRange();
		}
	} else {
		oRTE = document.getElementById(rte).contentWindow;
		//get currently selected range
		var selection = oRTE.getSelection();
		rng = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
	}
	if(command=="inserttable")
		{
			//alert("tanle");
		strPage = "common/dlg_ins_table.html";
			strAttr = "status:no;dialogWidth:350px;dialogHeight:400px;help:no";
		html = showModalDialog(strPage, window, strAttr);
				
	}
	
	try {
		if ((command == "forecolor") || (command == "hilitecolor")) {
		
			//save current values
			parent.command = command;
			currentRTE = rte;
			
			//position and show color palette
			buttonElement = document.getElementById(command + '_' + rte);
			// Ernst de Moor: Fix the amount of digging parents up, in case the RTE editor itself is displayed in a div.
			document.getElementById('cp' + rte).style.left = getOffsetLeft(buttonElement, 10) + "px";
			document.getElementById('cp' + rte).style.top = (getOffsetTop(buttonElement, 10) + buttonElement.offsetHeight + 4) + "px";
			if (document.getElementById('cp' + rte).style.visibility == "hidden") {
				document.getElementById('cp' + rte).style.visibility = "visible";
				document.getElementById('cp' + rte).style.display = "inline";
			} else {
				document.getElementById('cp' + rte).style.visibility = "hidden";
				document.getElementById('cp' + rte).style.display = "none";
			}
		} else if (command == "createlink") {
			 //save current values
			      parent.command = command;
			      currentRTE = rte;
			      var szURL = prompt("Enter a URL:", "");
			try {
				//ignore error for blank urls
				rteCommand(rte, "Unlink", null);
				rteCommand(rte, "CreateLink", szURL);
			} catch (e) {
				//do nothing
			}
			      //var url="/IWAS/FrontServlet?requestType=CorrespondenceRH&actionVal=view&link=RichtextIncomingRetrieve&flag=Retrieve&FileID=<//%=lStrFileID%>&RichTextId="+rte;
			     // window.open(url,'Corres','width=510, height=370, top=220,left=260,resizable=no,scrollbars=yes');
		} 
		else if(command=="inserttable") {
			
			if(html)
			{
				oRTE.focus();
				rng.pasteHTML(html);
			}
		} else {
			oRTE.focus();
		  	oRTE.document.execCommand(command, false, option);
			oRTE.focus();
		}
	} catch (e) {
		alert(e);
	}
}
function dlgInsertTable(rte, command) {
	//function to open/close insert table dialog
	//save current values
  parent.command = command;
	currentRTE = rte;
	strPage = "common/dlg_ins_table.html";
	strAttr = "status:no;dialogWidth:350px;dialogHeight:400px;help:no";
	html = showModalDialog(strPage, window, strAttr);
	//alert(html);
	if(html)
  insertHTML(html,currentRTE);
}

function dlgInsertLink(rte, command) {
	//function to open/close insert table dialog
	//save current values
	parent.command = command;
	currentRTE = rte;
  InsertLink = "";
  strPage = "common/dlg_ins_table.html";
  strAttr = "status:no;dialogWidth:350px;dialogHeight:200px;help:no";
	InsertLink = showModalDialog(strPage,window, strAttr); 
  if(InsertLink==undefined)
  InsertLink = "";
	//get currently highlighted text and set link text value
	insertHTML(InsertLink ,currentRTE);
}


function insertHTML(html,curRTE) {
	//function to add HTML -- thanks dannyuk1982

 var rte = curRTE;
	
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
	} else {
		oRTE = document.getElementById(rte).contentWindow;
	}
	
	oRTE.focus();
	if (document.all) {
		var oRng = oRTE.document.selection.createRange();
    oRng.pasteHTML(html);
		oRng.collapse(false);
		oRng.select();
	} else {
		oRTE.document.execCommand('insertHTML', false, html);
	}
}


//Function to set color
function setColor(color) {
	var rte = currentRTE;
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
	} else {
		oRTE = document.getElementById(rte).contentWindow;
	}
	
	var parentCommand = parent.command;
	if (document.all) {
		//retrieve selected range
		var sel = oRTE.document.selection; 
		if (parentCommand == "hilitecolor") parentCommand = "backcolor";
		if (sel != null) {
			var newRng = sel.createRange();
			newRng = rng;
			newRng.select();
		}
	}
	oRTE.focus();
	oRTE.document.execCommand(parentCommand, false, color);
	oRTE.focus();
	document.getElementById('cp' + rte).style.visibility = "hidden";
	document.getElementById('cp' + rte).style.display = "none";
}

//Function to add image
function AddImage(rte) {
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
		
		//get current selected range
		var selection = oRTE.document.selection; 
		if (selection != null) {
			rng = selection.createRange();
		}
	} else {
		oRTE = document.getElementById(rte).contentWindow;
		
		//get currently selected range
		var selection = oRTE.getSelection();
		rng = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
	}
	
	imagePath = prompt('Enter Image URL:', 'http://');				
	if ((imagePath != null) && (imagePath != "")) {
		oRTE.focus();
		oRTE.document.execCommand('InsertImage', false, imagePath);
		oRTE.focus();
	}
}

//function to perform spell check
function checkspell() {
	try {
		var tmpis = new ActiveXObject("ieSpell.ieSpellExtension");
		tmpis.CheckAllLinkedDocuments(document);
	}
	catch(exception) {
		alert('ieSpell not detected. Please install. ');
	}
}

// Ernst de Moor: Fix the amount of digging parents up, in case the RTE editor itself is displayed in a div.
function getOffsetTop(elm, parents_up) {
	var mOffsetTop = elm.offsetTop;
	var mOffsetParent = elm.offsetParent;
	
	if(!parents_up) {
		parents_up = 10000; // arbitrary big number
	}
	while(parents_up>0 && mOffsetParent) {
		mOffsetTop += mOffsetParent.offsetTop;
		mOffsetParent = mOffsetParent.offsetParent;
		parents_up--;
	}
	
	return mOffsetTop;
}

// Ernst de Moor: Fix the amount of digging parents up, in case the RTE editor itself is displayed in a div.
function getOffsetLeft(elm, parents_up) {
	var mOffsetLeft = elm.offsetLeft;
	var mOffsetParent = elm.offsetParent;
	
	if(!parents_up) {
		parents_up = 10000; // arbitrary big number
	}
	while(parents_up>0 && mOffsetParent) {
		mOffsetLeft += mOffsetParent.offsetLeft;
		mOffsetParent = mOffsetParent.offsetParent;
		parents_up--;
	}
	
	return mOffsetLeft;
}

//Added for Standard Notings by Kanan Vaidya (Ahmedabad) on 28 Dec 2005
function fn_ShowStdNotings(rte, selectname) 
{
	var idx = document.getElementById(selectname).selectedIndex;
	// First one is always a label
	if (idx != 0) 
  {
		var curNotings=eval("document.forms[0]."+rte+".value"); 
    var selected= document.getElementById(selectname).options[idx].value;
    if (curNotings!='')
    {
      curNotings=curNotings+'<br>';
    }
    curNotings=curNotings+selected;        
		document.getElementById(selectname).selectedIndex = 0;    
    enableDesignMode(rte,curNotings,false);
    updateRTE(rte);
	}  
}
//End - for Standard Notings by Kanan Vaidya (Ahmedabad) on 28 Dec 2005

function Select(rte, selectname) {
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
		
		//get current selected range
		var selection = oRTE.document.selection; 
		if (selection != null) {
			rng = selection.createRange();
		}
	} else {
		oRTE = document.getElementById(rte).contentWindow;
		
		//get currently selected range
		var selection = oRTE.getSelection();
		rng = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
	}
	
	var idx = document.getElementById(selectname).selectedIndex;
	// First one is always a label
	if (idx != -1) {
		var selected = document.getElementById(selectname).options[idx].value;
		var cmd = selectname.replace('_' + rte, '');
		oRTE.focus();
		oRTE.document.execCommand(cmd, false, selected);
		oRTE.focus();
		document.getElementById(selectname).selectedIndex = idx; //0
	}
}

function kb_handler(evt) {
	var rte = evt.target.id;
	
	//contributed by Anti Veeranna (thanks Anti!)
  if (evt.ctrlKey) {
    var key = String.fromCharCode(evt.charCode).toLowerCase();
		var cmd = '';
		switch (key) {
			case 'b': cmd = "bold"; break;
			case 'i': cmd = "italic"; break;
			case 'u': cmd = "underline"; break;
		};
		if (cmd) {
			FormatText(rte, cmd, true);
			//evt.target.owsnerDocument.execCommand(cmd, false, true);
			// stop the event bubble
			evt.preventDefault();
			evt.stopPropagation();
		}
 	}
}

function docChanged (evt) {
	//alert('changed');
}

function stripHTML(oldString) {
	var newString = oldString.replace(/(<([^>]+)>)/ig,"");
	
	//replace carriage returns and line feeds
   newString = newString.replace(/\r\n/g," ");
   newString = newString.replace(/\n/g," ");
   newString = newString.replace(/\r/g," ");
	
	//trim string
	newString = trim(newString);
	
	return newString;
}

function trim(inputString) {
   // Removes leading and trailing spaces from the passed string. Also removes
   // consecutive spaces and replaces it with one space. If something besides
   // a string is passed in (null, custom object, etc.) then return the input.
   if (typeof inputString != "string") return inputString;
   var retValue = inputString;
   var ch = retValue.substring(0, 1);
	
   while (ch == " ") { // Check for spaces at the beginning of the string
      retValue = retValue.substring(1, retValue.length);
      ch = retValue.substring(0, 1);
   }
   ch = retValue.substring(retValue.length-1, retValue.length);
	
   while (ch == " ") { // Check for spaces at the end of the string
      retValue = retValue.substring(0, retValue.length-1);
      ch = retValue.substring(retValue.length-1, retValue.length);
   }
	
	// Note that there are two spaces in the string - look for multiple spaces within the string
   while (retValue.indexOf("  ") != -1) {
		// Again, there are two spaces in each of the strings
      retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length);
   }
   return retValue; // Return the trimmed string back to the user
}

// Function to hide the pallette if clicked on editor
function hidePallete(id)
{		
  if (document.getElementById('cp' + id).style.visibility != "hidden")
	{
		document.getElementById('cp' + id).style.visibility = "hidden";	
		document.getElementById('cp' + id).style.display = "none";
	}// End of if
	
}
initRTE("themes/defaulttheme/images/workflow/", "", "");
</script>
<body>

		<script language="JavaScript" type="text/javascript"> 
		<%String richText=(String)request.getParameter("richTextName");
		richText="rte"+richText;%>
		var richtext='<%=richText%>'
		if(richtext!=null)
				writeRichText(richtext,'', 300, 150, true, false);			
		</script><br>
		
		
	<!-- </form>  -->
</body>
</html>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>



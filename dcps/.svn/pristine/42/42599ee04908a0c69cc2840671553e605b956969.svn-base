
//Contents for menu 1


		
var disappeardelay=250  //menu disappear speed onMouseout (in miliseconds)
var horizontaloffset=2 //horizontal offset of menu from default location. (0-5 is a good value)


/////No further editting needed

var ie4=document.all
var ns6=document.getElementById&&!document.all

if (ie4||ns6)
{
//change
//document.write('<div id="dropmenudiv" onMouseover="clearhidemenu()" onMouseout="dynamichide(event)"></div>')

document.write('<div id="dropmenudiv_0" onMouseover="clearhidemenu(0)" onMouseout="dynamichide(event,0)"></div>')
document.write('<div id="dropmenudiv_1" onMouseover="clearhidemenu(1)" onMouseout="dynamichide(event,1)"></div>')
document.write('<div id="dropmenudiv_2" onMouseover="clearhidemenu(2)" onMouseout="dynamichide(event,2)"></div>')
document.write('<div id="dropmenudiv_3" onMouseover="clearhidemenu(3)" onMouseout="dynamichide(event,3)"></div>')
document.write('<div id="dropmenudiv_4" onMouseover="clearhidemenu(4)" onMouseout="dynamichide(event,4)"></div>')
document.write('<div id="dropmenudiv_5" onMouseover="clearhidemenu(5)" onMouseout="dynamichide(event,5)"></div>')
document.write('<div id="dropmenudiv_6" onMouseover="clearhidemenu(6)" onMouseout="dynamichide(event,6)"></div>')

}


function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}
function showhide(obj, e, visible, hidden, menuwidth){

if (ie4||ns6)
dropmenuobj.style.left=dropmenuobj.style.top=100

dropmenuobj.widthobj=dropmenuobj.style
dropmenuobj.widthobj.width=menuwidth
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover")
obj.visibility=visible
else if (e.type=="click")
obj.visibility=hidden
obj.zIndex=4
obj.position="relative"
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=0
if (whichedge=="rightedge"){
var windowedge=ie4 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x-obj.offsetWidth < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure+obj.offsetWidth
}
else{
var topedge=ie4 && !window.opera? iecompattest().scrollTop : window.pageYOffset
var windowedge=ie4 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure){ //move menu up?
edgeoffset=dropmenuobj.contentmeasure-obj.offsetHeight
if ((dropmenuobj.y-topedge)<dropmenuobj.contentmeasure) //up no good either? (position at top of viewable window then)
edgeoffset=dropmenuobj.y
}
}
return edgeoffset
}

function populatemenu(what){
var menu = what;
var str = "<table id='dropmenutable' class='menutable'> ";
var row = "";
var i=0;
for (i=0;i<menu.length;i++)
{
	row +="<tr><td class='menutd'>" + menu[i] + "</td></tr>";
}
row+="</table>";
str+=row;

if (ie4||ns6)
dropmenuobj.innerHTML=str;//.join("")

}

function populatemenu1(what,menuLink,submenu,toolTipMsg,levelIndex,hrefForModule)
{
var menu = what;
var str = "<table id='dropmenutable' class='menutable'> ";
var row = "";
var i=0;
var j;
var str1;
for (i=0;i<submenu.length;i++)
{
    j=submenu[i];
	
	//row +="<tr><td class='menutd'><A title='"+ toolTipMsg [j]+"' href='"+menuLink[j] +"'>" + menu[j] + "</a></td></tr>";
	
	///////////////////////////////////
		
		if(menuLink[j].charAt(0)=="#")
		{
			if(hrefForModule[j] == "#")
			{
				row +="<tr><td id='mtd' class='menutd menuparent'><A title='"+ toolTipMsg [j]+"' href='"+hrefForModule[j]+"' onMouseOver=\""+menuLink[j].substring(1) +"\">" + menu[j] + "</a></td></tr>";
			}
			else
			{				
				row +="<tr><td id='mtd' class='menutd menuparent'><A title='"+ toolTipMsg [j]+"'onclick = \"removeAllMenuOnClick()\" href='"+hrefForModule[j]+"' onMouseOver=\""+menuLink[j].substring(1) +"\">" + menu[j] + "</a></td></tr>";
			}
		}
		else
		{
			row +="<tr><td class='menutd'><A title='"+ toolTipMsg [j]+"'onclick = \"removeAllMenuOnClick()\" href='"+menuLink[j] +"'>" + menu[j] + "</a></td></tr>";
		}
	
	///////////////////////////////////
}
row+="</table>";
str+=row;

if (ie4||ns6)
//change
dropmenuobj.innerHTML=str;
	/*
	if(levelIndex>0)
	{
		dropmenuobj.innerHTML+=str;
	}
	else
	{		
		dropmenuobj.innerHTML=str;
	}*/
}

function dropdownmenu1(obj, e, menucontents,mainMenuLink,toolTipMsg,menuwidth,submenu,levelIndex,hrefForModule)
{
	if (window.event) event.cancelBubble=true
	else if (e.stopPropagation) e.stopPropagation()
	clearhidemenu(levelIndex)
	var iter = 0;
	
	//change
	//dropmenuobj=document.getElementById? document.getElementById("dropmenudiv") : dropmenudiv
	//dropmenuobj=document.getElementById? document.getElementById("dropmenudiv_"+levelIndex) : dropmenudiv		
	
	
	if(levelIndex == 0)
	{		
		while(document.getElementById("dropmenudiv_"+iter))
		{				
			dropmenuobj = document.getElementById("dropmenudiv_"+iter);				
			dropmenuobj.style.visibility = "hidden";
			iter++;
		}
	}
	
	dropmenuobj=document.getElementById? document.getElementById("dropmenudiv_"+levelIndex) : dropmenudiv
	dropmenuobj.style.visibility = "visible";
	
	
	menudivobj=document.getElementById("nav");
	//populatemenu(menucontents)
	
	populatemenu1(menucontents,mainMenuLink,submenu,toolTipMsg,levelIndex,hrefForModule)

	if (ie4||ns6)
	{
		showhide(dropmenuobj.style, e, "visible", "hidden", menuwidth)
		
		tbl = document.getElementById("mainmenutable");
		
		//dropmenuTbl = document.getElementById("dropmenutable");		

		dropmenuobj.x=getposOffset(tbl, "left");
		//dropmenuobj.x=getposOffset(dropmenuTbl, "left");	

		dropmenuobj.y=getposOffset(obj, "top")
		//dropmenuobj.y=getposOffset(dropmenuTbl, "top")
		//dropmenuobj.y=getposOffset(tbl, "top");			
	
		//change
//		dropmenuobj.style.left=tbl.offsetWidth-3;//-dropmenuobj.x-clearbrowseredge(obj, "rightedge") + obj.offsetWidth+horizontaloffset +"px"
		dropmenuobj.style.left=tbl.offsetWidth-3+165*(levelIndex);//-dropmenuobj.x-clearbrowseredge(obj, "rightedge") + obj.offsetWidth+horizontaloffset +"px"
		//dropmenuobj.style.left=dropmenuTbl.offsetWidth-3;//-dropmenuobj.x-clearbrowseredge(obj, "rightedge") + obj.offsetWidth+horizontaloffset +"px"
	
		
		
		dropmenuobj.style.top=dropmenuobj.y-getposOffset(tbl, "top")-5;
		//dropmenuobj.style.top=dropmenuobj.y-getposOffset(dropmenuTbl, "top")-5;
		
		dropmenuobj.position="relative";		
	
		//change		
		dropmenuobj.style.visibility="visible";

		return clickreturnvalue()
	}
}

function dropdownmenu(obj, e, menucontents, menuwidth){
if (window.event) event.cancelBubble=true
else if (e.stopPropagation) e.stopPropagation()
clearhidemenu()
dropmenuobj=document.getElementById? document.getElementById("dropmenudiv") : dropmenudiv
menudivobj=document.getElementById("nav");
populatemenu(menucontents)
if (ie4||ns6){
showhide(dropmenuobj.style, e, "visible", "hidden", menuwidth)
tbl = document.getElementById("mainmenutable");
dropmenuobj.x=getposOffset(tbl, "left")
dropmenuobj.y=getposOffset(obj, "top")
dropmenuobj.style.left=tbl.offsetWidth-3;//-dropmenuobj.x-clearbrowseredge(obj, "rightedge") + obj.offsetWidth+horizontaloffset +"px"
dropmenuobj.style.top=dropmenuobj.y-getposOffset(tbl, "top")-5;
dropmenuobj.position="relative";
}
return clickreturnvalue()
}
function clickreturnvalue(){
if (ie4||ns6) return false
else return true
}
function contains_ns6(a, b) {
while (b.parentNode)
if ((b = b.parentNode) == a)
return true;
return false;
}

function dynamichide(e,hideIndex){

dropmenuobjThis = document.getElementById("dropmenudiv_"+hideIndex); 
if (ie4&&!dropmenuobjThis.contains(e.toElement))
delayhidemenu(hideIndex)
else if (ns6&&e.currentTarget!= e.relatedTarget&& !contains_ns6(e.currentTarget, e.relatedTarget))
delayhidemenu(hideIndex)
}

function hidemenu(hideIndex)
{
//	dropmenuobjThis = document.getElementById("dropmenudiv_"+hideIndex); 
	var iter=0;
	if (typeof dropmenuobjThis!="undefined")
	{
		if (ie4||ns6)
		{
			while(document.getElementById("dropmenudiv_"+iter))
			{
				dropmenuobjThis = document.getElementById("dropmenudiv_"+iter);
				dropmenuobjThis.style.visibility="hidden"
				iter++;
			}
		}
	}
}


function hideAllOpenSubmenu(e)
{
	if(e.toElement == '[object]')
	{
		var iter = 0;
		while(document.getElementById("dropmenudiv_"+iter))
		{				
			dropmenuobjToHide = document.getElementById("dropmenudiv_"+iter);				
			dropmenuobjToHide.style.visibility = "hidden";
			iter++;
		}		
	}
}

function removeAllMenuOnClick()
{
	var iter = 0;
	while(document.getElementById("dropmenudiv_"+iter))
	{				
		dropmenuobjToHide = document.getElementById("dropmenudiv_"+iter);				
		dropmenuobjToHide.style.visibility = "hidden";
		iter++;
	}
} 

function delayhidemenu(hideIndex){
if (ie4||ns6)
delayhide=setTimeout("hidemenu("+hideIndex+")",disappeardelay)
}
function displayRightMenuValue(firstLevelChildIndex,menArray,linkArray ,toolTipMsg ,hrefForModule)
{
	var i ;
	var firstLevelIndex;
	for (i=0;i<totalMainMenus;i++)
	{
		firstLevelIndex = firstLevelChildIndex[i];
		var linkStr = linkArray[firstLevelIndex];			
		var str ="";
		var menuStr = menArray[firstLevelIndex];//rPad(menArray[i]);
		var moduleHref = hrefForModule[firstLevelIndex];
		if(linkStr.charAt(0)=="#")
		{
			if(moduleHref == "#")
			{
				str= "<tr id='mtr'><td id='mtd' class='menutd menuparent' onMouseOver=''>" +			
				"<a href='"+moduleHref+"' title='"+toolTipMsg[firstLevelIndex] +"' onMouseout=\"hideAllOpenSubmenu(event)\" onMouseOver=\""+linkStr.substring(1) +"\">"+menuStr+"</a>" + 
				"</td></tr>";
			}
			else
			{
				str= "<tr id='mtr'><td id='mtd' class='menutd menuparent' onMouseOver=''>" +			
				"<a href='"+moduleHref+"' title='"+toolTipMsg[firstLevelIndex] +"' onclick = \"removeAllMenuOnClick()\" onMouseout=\"hideAllOpenSubmenu(event)\" onMouseOver=\""+linkStr.substring(1) +"\">"+menuStr+"</a>" + 
				"</td></tr>";							
			}
		}
		else
		{
			str = "<tr><td nowrap class='menutd'>" +
			"<a  title='"+toolTipMsg[firstLevelIndex] +"'onclick = \"removeAllMenuOnClick()\" onMouseout=\"hideAllOpenSubmenu(event)\" href='"+linkArray[firstLevelIndex] + "'>"+menuStr+"</a></td></tr>";
		}
		
		document.write(str);
	}
}
function clearhidemenu(currentFocusDivIndex)
{
	startingIndexToHide = currentFocusDivIndex+1;
	var hideModuleObj;
	while(document.getElementById("dropmenudiv_"+startingIndexToHide))
	{
		hideModuleObj = document.getElementById("dropmenudiv_"+startingIndexToHide);
		hideModuleObj.style.visibility = "hidden";
		startingIndexToHide++;
	}
			
	if (typeof delayhide!="undefined")
	clearTimeout(delayhide)
	var obj = document.getElementsByTagName("div").item("menu");
}
function displayRightMenu(){
	var obj = document.getElementsByTagName("div").item("menuLine");
    obj.style.visibility="hidden";	
	var obj = document.getElementsByTagName("div").item("menu");
    obj.style.visibility="visible";	
}
function hideRightMenu()
{
	var obj = document.getElementsByTagName("div").item("menu");
    obj.style.visibility="hidden";	
	var obj = document.getElementsByTagName("div").item("menuLine");
    obj.style.visibility="visible";	
	hidemenu(event);
}
function rPad(str)
{
	if (str.length>30)
		return str;	
	var str1 = str;
	var p ;
//	for(p=str.length;p<15;p++)
//		str1 = str1+ "&nbsp;";
	return str1;
}

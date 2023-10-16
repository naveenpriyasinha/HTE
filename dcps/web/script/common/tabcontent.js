//** Tab Content script- � Dynamic Drive DHTML code library (http://www.dynamicdrive.com)
//** Last updated: Nov 8th, 06
var globalNoSave=true;
var enabletabpersistence=1 //enable tab persistence via session only cookies, so selected tab is remembered?
var currentTab=0;
////NO NEED TO EDIT BELOW////////////////////////
var tabcontentIDs=new Object()

function expandcontent(linkobj ){

var ulid=linkobj.parentNode.parentNode.id //id of UL element
var ullist=document.getElementById(ulid).getElementsByTagName("li") //get list of LIs corresponding to the tab contents
	for (var i=0; i<ullist.length; i++)
	{
		ullist[i].getElementsByTagName("a")[0].className="unselected"  //deselect all tabs
		if (typeof tabcontentIDs[ulid][i]!="undefined") //if tab content within this array index exists (exception: More tabs than there are tab contents)
			document.getElementById(tabcontentIDs[ulid][i]).style.display="none" //hide all tab contents
	}
linkobj.className="selected"  //highlight currently clicked on tab
document.getElementById(linkobj.getAttribute("rel")).style.display="block" //expand corresponding tab content
var tabCont = linkobj.getAttribute("rel");
var tabContIndex = tabCont.substring(8);
currentTab = tabContIndex -1;
updateButtonStatus(linkobj);
saveselectedtabcontentid(ulid, linkobj.getAttribute("rel"))

//	START : CODE FOR FILL COMBO BOX TAB WISE
//	ADDED BY 202414 : CHIRAGKUMAR SHAH
//	alert ("FLAG :"+window.FILL_COMBO_BOX_TAB_WISE);
	if(window.FILL_COMBO_BOX_TAB_WISE)
	{
		fillTabWiseComboBox();
	}
//	END   : CODE FOR FILL COMBO BOX TAB WISE	
}

function expandtab(tabcontentid, tabnumber){ //interface for selecting a tab (plus expand corresponding content)
currentTab = tabnumber;
var thetab=document.getElementById(tabcontentid).getElementsByTagName("a")[tabnumber]
if (thetab.getAttribute("rel"))

expandcontent(thetab)

}

function savetabcontentids(ulid, relattribute){// save ids of tab content divs
if (typeof tabcontentIDs[ulid]=="undefined") //if this array doesn't exist yet
tabcontentIDs[ulid]=new Array()
tabcontentIDs[ulid][tabcontentIDs[ulid].length]=relattribute
}

function saveselectedtabcontentid(ulid, selectedtabid){ //set id of clicked on tab as selected tab id & enter into cookie
if (enabletabpersistence==1) //if persistence feature turned on
setCookie(ulid, selectedtabid)
}

function getullistlinkbyId(ulid, tabcontentid)
{ //returns a tab link based on the ID of the associated tab content
	var ullist=document.getElementById(ulid).getElementsByTagName("li")
	for (var i=0; i<ullist.length; i++)
	{
		if (ullist[i].getElementsByTagName("a")[0].getAttribute("rel")==tabcontentid)
		{
			return ullist[i].getElementsByTagName("a")[0]
			break
		}
	}
}
function printNavigationBar()
{
	var divContainer = document.getElementById("tabContainer")
	var navigationDIV = document.createElement('div');
	navigationDIV.innerHTML='<jsp:include page="../core/tabnavigation.jsp"/>';
	divContainer.appendChild(navigationDIV);
}

function goToTab(cnt)
{
	currentTab=cnt;
	expandtab("mainTab",currentTab);	
}

function goToFirstTab(ulObj)
{
	currentTab=0;
	expandtab(ulObj.id,currentTab);
}

function goToNextTab()
{
//	alert(currentTab);
	if(document.getElementsByName('saveDraftButton')[0])
	{
		globalNoSave=false;
	}
	var maxSize = document.getElementById('mainTab').getElementsByTagName("a").length;
	if(currentTab==(maxSize-1))
		return;
	currentTab=currentTab+1;
	expandtab("mainTab",currentTab);
	
}
function goToPrevTab()
{
	if(document.getElementsByName('saveDraftButton')[0])
	{
		globalNoSave=false;
	}
	var maxSize = document.getElementById('mainTab').getElementsByTagName("a").length;
	if (currentTab==0)
		return;
	currentTab=currentTab-1;
	expandtab("mainTab",currentTab);
	
	
}


function initializetabcontent(){
//printNavigationBar();
var divs = document.getElementsByTagName('div');

for(var j=0; j<divs.length; j++)
{
	divclass = divs[j].className;
	if(divclass.indexOf('tabcontentstyle') != -1 || divclass.indexOf('tabcontent') != -1){
		divs[j].style.height = '100%';
	}
}
for (var i=0; i<arguments.length; i++){ //loop through passed UL ids
if (enabletabpersistence==0 && getCookie(arguments[i])!="") //clean up cookie if persist=off
setCookie(arguments[i], "")
var clickedontab=getCookie(arguments[i]) //retrieve ID of last clicked on tab from cookie, if any
var ulobj=document.getElementById(arguments[i])
var ulist=ulobj.getElementsByTagName("li") //array containing the LI elements within UL
for (var x=0; x<ulist.length; x++){ //loop through each LI element
var ulistlink=ulist[x].getElementsByTagName("a")[0]
if (ulistlink.getAttribute("rel")){
savetabcontentids(arguments[i], ulistlink.getAttribute("rel")) //save id of each tab content as loop runs
ulistlink.onclick=function(){
expandcontent(this)
return false
}
//added for li click - start
ulist[x].onclick=function(){
expandcontent(this.getElementsByTagName("a")[0])
return false
}
//added for li click - end
if (ulist[x].getElementsByTagName("a")[0].className == "selected" && clickedontab=="") //if a tab is set to be selected by default
expandcontent(ulistlink) //auto load currenly selected tab content
}
} //end inner for loop
if (clickedontab!=""){ //if a tab has been previously clicked on per the cookie value
var culistlink=getullistlinkbyId(arguments[i], clickedontab)
if (typeof culistlink!="undefined") //if match found between tabcontent id and rel attribute value
expandcontent(culistlink) //auto load currenly selected tab content
else //else if no match found between tabcontent id and rel attribute value (cookie mis-association)
expandcontent(ulist[0].getElementsByTagName("a")[0]) //just auto load first tab instead
}
} //end outer for loop
goToFirstTab(ulobj);
}


function getCookie(Name){ 
var re=new RegExp(Name+"=[^;]+", "i"); //construct RE to search for target name/value pair
if (document.cookie.match(re)) //if cookie found
return document.cookie.match(re)[0].split("=")[1] //return its value
return ""
}

function setCookie(name, value){
document.cookie = name+"="+value //cookie value is domain wide (path=/)
}
function printNavigationButtons(tdid)
  {
    if (navDisplay)
    {
    	document.getElementById(tdid).innerHTML += "<input type='button' value='Previous' onClick='goToPrevTab()'>" +
		"<input type='button' value='Next' onClick='goToNextTab()'>";
	}	
  }
  function getTabNumber( fieldName )
{
	var f = document.getElementsByName(fieldName);		
	var parentNode = f[0].parentNode;
	var tabno = '';
	var nodeName = '';
	
	while(nodeName!='recursive')
	{
		parentNode = parentNode.parentNode;
		nodeName = parentNode.nodeName;
		if(nodeName=='div' || nodeName=='DIV')
		{
			tabno = parentNode.getAttribute("tabno");
			if(tabno>=0)
			{
				break;
			}
		}
	}
    
	//return f[0].parentNode.getAttribute("tabno");
	return parentNode.getAttribute("tabno");
	
}

function updateButtonStatus(linkobj)
{
	var maxSize = document.getElementById('mainTab').getElementsByTagName("a").length;
	
	if(globalNoSave==false)
	{
	saveDataFromTab();
	globalNoSave=true;
	}
if (document.getElementById('Next') && document.getElementById('Prev'))
{
	if(currentTab==(maxSize-1))
		document.getElementById('Next').disabled = true;

	if (currentTab != 0)
		document.getElementById('Prev').disabled = false;
	
	if (currentTab==0)
		document.getElementById('Prev').disabled = true;

	if (currentTab != (maxSize-1))
		document.getElementById('Next').disabled = false;
}
}
  
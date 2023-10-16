<%@ include file="../core/include.jsp" %>
     
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resultObj" value="${result}"/>
<c:set var="resValue" value="${resultObj.resultValue}"/> 
<c:set var="resData" value="${resValue.resData}"/> 
<c:set var="modData" value="${resValue.modData}"/>
<fmt:setBundle basename="resources.expacct.expacctAlerts" var="expacctAlert" scope="request"/>

<style>
.chkBox
{
 	outline: none;
}
</style>


<script  type="text/javascript"  src="script/common/statusbar.js"></script>

<html>
<body> 
<table>
<form name="userLevelConfig" action="post">
<tr>
<b>Note:</b> 
<tr>1.Please clear cache and temporary internet files after you assign or revoke new role to any user 
<tr>2.Wait for approximately 15 mins for reflection.</tr>
<tr>3.Please process all the data from the worklist before you revoke any roles from a user.</tr>
</tr>

<tr>

<td>
Modules
	<select id="hierarchy" name="hierarchy" onchange="getData();">
	<option value="module">Please select a module</option>
	</select>
</td>
</tr>
<tr>

<td>
User Name<input type="text" id="txtSrchUser" name="txtSrchUser"></input> 
<input type="button" class="bigbutton" id="btnSearch" name="btnSearch" value="Search" onclick="getSearchResult();"></input>
</td>
<td>
<input  type="hidden" id="txtPostid" name="txtPostid"></input>
<input  type="text" id="txtUserName" name="txtUserName" disabled="disabled" ></input>
<input type="button" class="bigbutton" id="btnAddUser" name="btnAddUser" value="Add User" onclick="addUserforType();"></input>
<input type="button" class="bigbutton" id="btncanSrch" name="btncanSrch" value="Cancel Search" onclick="canSearch();"></input>
</td>
</tr>	
	
	
	<table width="100%" border="0">
	<tr>
	<td width="30%" valign="top">
	<div id="uNames" align="left" style="border: thin ,black;">
	<b>All Users For the Module</b>
		<select multiple="multiple" name="allUnames" id="allUnames" style="width: 100%;height: 400px;" onclick="getAllLevelForUser();"></select>
	</div>
	</td>
	
	<td width="40%" valign="top">
	<div id="uLevel" align="left" style="border: 1px solid black;background-color: white ;height: 390px; overflow: scroll;" >
	<b>All Roles </b>
		<table>
		<tbody id="allLevels"></tbody>
		</table>
	</div>
	</td>
	<td width="40%" valign="top">
	<div id="allUDtls" align="left" style="border: 1px solid black;background-color: white ;height: 390px; overflow: scroll;"  >
	<b>All Roles Mapped to the User</b>
	<table >
		<tbody id="allDtls"></tbody>
		</table>
	</div>
	</td>
	</tr>
	<tr>
	</tr>
	<tr align="center">
	<td colspan="2">
	<input type="button" id="btnChanges" class="bigbutton" name="btnChanges" value="Apply Changes" disabled="disabled" onClick="applyChanges();"/>
	<!-- <input type="button" id="btnShowScreen" class="bigbutton" name="btnShowScreen" value="Role Element Screen"  onClick="getRoleElement();"/> -->
	<input type="button" id="btnClose" class="bigbutton" name="btnClose" value="Close"  onClick="window.location.href='ifms.htm?viewName=homePage'"/>
	</td>

	</tr>
	</table>
	
</form>
</table>
</body>
<script>

getModules();

 allUserForSrch= new Array();
 newId=new Array();
 newName=new Array();
 indice=0;
 y =null;
 allUsers=null;
 allUserForSrchLower= null;
 allUserIdForSrch= new Array();	
var workFlow =null;
var allUserNames=new Array();

function getDataOnLoad()
{
var finalData = '${resData}';
setAllData(finalData);
}

function getModules()
{

// var modOpt = document.getElementById("wf").value;

	var varTable = document.getElementById("allLevels");
	
	/*delete table*/
			var count=varTable.rows.length;
			
			for(var t = count ;t >= 0 ;t--)
			{
				 try
				 {
				 varTable.deleteRow(t);
				 }catch(e){}
			}
			
	/*deletion of table done*/		
		
			
	/*empty the select element for new users*/
			
			for(var i=document.getElementById("allUnames").options.length; i>=0;i--)
			{
					document.getElementById("allUnames").options.remove(i);
			}
	/*emptied the select element*/
			
			

	/*empty the select element for modules*/
			
			for(var i=document.getElementById("hierarchy").options.length; i>=0;i--)
			{
					document.getElementById("hierarchy").options.remove(i);
			}
	/*emptied the select element*/
			
	  	   	xmlHttp=GetXmlHttpObject();		
		   	if (xmlHttp==null)
		   	{
			   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
			   return;
		   	} 
		   	 
		   	var uri='ifms.htm?actionFlag=getAllModules';
		  	xmlHttp.onreadystatechange=getDataForModules;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);

}
/*
function getDataForModules()
{
	if (xmlHttp.readyState==complete_state)
    { 
    var state = xmlHttp.responseText;
	var arrOut = state.split(">");
	alert
	var allModules = arrOut[0];
	var allModNames =  allModules.split('^');
	var roleId =new Array();
	allNames = document.getElementById('hierarchy');
	
	 x =document.createElement('option');
	 x.text="Please select a module";
	 x.value="Please select a module";
	 allNames.add(x);
			 
		for(var i=0; i<=allModNames.length ;i++)
		{  
			if(allModNames[i] != "")
			{
			 var module = allModNames[i].split('~');
			 var role = module[1].split('!');
			 y= document.createElement('option');
			 y.text=module[0];
			 if(document.getElementById("wf").value == 'NoWorkflow' || document.getElementById("wf").value =='LCDiv')
			 {
			 var roleWF = role[1].split('&');
			 y.value=roleWF[0];
			 workFlow= roleWF[1];
			 }
			 else
			 {
			 var roleWF = role[1].split('&');
			 y.value=role[0];
			 workFlow= roleWF[1];
			 }
			 allNames.add(y);
			
			}
		}
	}
}

*/

function getDataForModules()
{
	if (xmlHttp.readyState==complete_state)
    { 
    var state = xmlHttp.responseText;
	var arrOut = state.split(">");
	var allModules = arrOut[0];
	var allModNames =  allModules.split('$');
	var roleId =new Array();
	allNames = document.getElementById('hierarchy');
	
	 x =document.createElement('option');
	 x.text="Please select a module";
	 x.value="module";
	 allNames.add(x);
			 
		for(var i=0; i<allModNames.length ;i++)
		{  
			if(allModNames[i] != "")
			{
			 var module = allModNames[i].split('!');	
			 var modName = module[0].split('~');
			 y= document.createElement('option');
			 y.text=modName[0];
			 y.value=modName[1]+'~'+module[1];
			 allNames.add(y);
			}
		}
	}	
}

function getData()
{
document.getElementById('allUnames').disabled=false;

	/*to delete the table for next type*/

	var varTable = document.getElementById("allLevels");
	
	/*delete table*/
			var count=varTable.rows.length;
			
			for(var t = count ;t >= 0 ;t--)
			{
				 try
				 {
				 varTable.deleteRow(t);
				 }catch(e){}
			}
			
	/*deletion of table done*/		
		
			
	/*empty the select element for new users*/
			
			for(var i=document.getElementById("allUnames").options.length; i>=0;i--)
			{
					document.getElementById("allUnames").options.remove(i);
			}
	/*emptied the select element*/
			
			
			if( document.getElementById("hierarchy")[document.getElementById("hierarchy").options.selectedIndex].value != 'module')
			{
	  	   	xmlHttp=GetXmlHttpObject();		
		   	if (xmlHttp==null)
		   	{
			   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
			   return;
		   	} 
			
		   	var uri='ifms.htm?actionFlag=getAllDataForUsers&Desc='+document.getElementById("hierarchy").value;// +'&workFlow='+workFlow;

		   	xmlHttp.onreadystatechange=getDataForDesc;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);
		   	}
		   	else
		   	{
		   	// alert('Please select a valid module');
		   	
		   		/*delete table showing all levels for the user for all types*/
			var varTableLevel = document.getElementById("allDtls");
			var countL=varTableLevel.rows.length;
			for(var k = countL ;k >= 0 ;k--)
			{
				 try
				 {
				 varTableLevel.deleteRow(k);
				 }catch(e){}
			}
			
			/*deletion done*/
			
		   	document.getElementById('txtUserName').value="";
   			document.getElementById('txtSrchUser').value="";
		   	}
}

function getDataForDesc()
{
if (xmlHttp.readyState==complete_state)
	    { 
	    var state = xmlHttp.responseText;
		  setAllData(state);
		}
}

/*setting user name and level*/
function setAllData(state)
{
	   var arrOut = state.split(">");
		var allUname = arrOut[0];
		var uName =  allUname.split('^');
		allUsers = document.getElementById('allUnames');
		allUserNames =  uName;
		
		for(var i=0; i<uName.length ;i++)
		{  
			if(uName[i] != "")
			{
			 var userPid = uName[i].split('~');
			 y= document.createElement('option');
			 y.text=userPid[0];
			 y.value=userPid[1];
			 allUsers.add(y);
			 allUserForSrch[i] = userPid[0];
			 allUserIdForSrch[i] = userPid[1];
			}
		}
		
		var levelDesc = arrOut[1];
		var userLevel = levelDesc.split('^');
		var levelDesc= "";
	 	var allLevels = document.getElementById("allLevels");
	 	
		for(var i=0; i< userLevel.length;i++)
		{
		 var tr = document.createElement("tr");
		 levelDesc = userLevel[i].split('~');
		 if(levelDesc != null && levelDesc != "")
		 {
			 var td = document.createElement("td");
			 var x= document.createElement("input");
	       	 x.type = "checkbox"; 
	       	 x.name = "chkbx";
	       	 x.id=levelDesc[0];
			 x.value=levelDesc[1];
			 x.style.border='none';
			 x.style.marginRight="20px";
			 
			 td.appendChild(x); 
			
			 var text = document.createTextNode(x.value); 
	       	 td.appendChild(text); 
	      		 tr.appendChild(td);
	      		 allLevels.appendChild(tr);	
	     		 }	
		}
} 
function getAllLevelForUser()
{
document.getElementById('btnAddUser').disabled = true;
document.getElementById('txtSrchUser').value="";
document.getElementById('txtPostid').value= "";
document.getElementById('txtUserName').value= "";
document.getElementById('btnChanges').disabled= false;
		/*delete table showing all levels for the user for all types*/
			var varTableLevel = document.getElementById("allDtls");
			var countL=varTableLevel.rows.length;
			for(var k = countL ;k >= 0 ;k--)
			{
				 try
				 {
				 varTableLevel.deleteRow(k);
				 }catch(e){}
			}
			
			/*deletion done*/

	var postId="";
	
		for(var i=0; i<document.getElementById("allUnames").options.length;i++)
			{
			if(i == document.getElementById("allUnames").options.selectedIndex)
				{
					postId=document.getElementById("allUnames").options[i].value;
				}
				
			}
						
			xmlHttp=GetXmlHttpObject();		
		   	if (xmlHttp==null)
		   	{
			   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
			   return;
		   	} 
		 
		   	var uri='ifms.htm?actionFlag=getAllLevelForUser&postId='+postId+'&Desc='+document.getElementById("hierarchy").value;// +"&workFlow="+workFlow;

		   	           
		   	xmlHttp.onreadystatechange=getLevelForUser;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);
}
function getLevelForUser()
{
if (xmlHttp.readyState==complete_state)
	    { 
		  var state = xmlHttp.responseText;
		
			var allAssdLevels = state.split('~');	
			

		 	 var arrLevelDesc = allAssdLevels[0].split('^');
		

			var checkBx = document.userLevelConfig.elements;
			
			for(var t = 0 ;t < checkBx.length;t++)
			{
			if(checkBx[t].type =='checkbox' && checkBx[t].name =='chkbx')
			{
					checkBx[t].checked = false;
			}
			}
		 		
		  for(var i=0 ;i< arrLevelDesc.length;i++)
		  {
		  if(arrLevelDesc[i] != "")
		  {
		  	var level = arrLevelDesc[i];
			   	if(document.getElementById(level).value != "")
			  	{
			  		document.getElementById(level).checked = true;
			  	}
		  	}
		  }
		 
		 var arrUserLvlAssd =  allAssdLevels[1].split('>');
		 
		 document.getElementById('allDtls').disabled ='true';
		 
		 var allDtls= document.getElementById('allDtls');
		 
		 for(var i=0;i< arrUserLvlAssd.length;i++)
		 {
		  	 var rowData = arrUserLvlAssd[i].split('/');
		  	 if(rowData != "" && rowData!=null)
		  	 {
		   	 var levelDesc = rowData[0];
		     var levelType = rowData[1];
			 var td = document.createElement("td");
			 var tr = document.createElement("tr");
			 var x= document.createElement("input");
	       	 x.type = "text"; 
	       	 x.name = "txt";
	       	 x.id=rowData[0];
			 x.value=rowData[1]+" -- "+rowData[0];
			 
			 var text = document.createTextNode(x.value); 
	       	 td.appendChild(text); 
      		 tr.appendChild(td);
      		 allDtls.appendChild(tr);	
      		 }
		 }
		 
		}
}

function applyChanges()
{
document.getElementById("btnChanges").disabled= true;
showProgressbar();
var postId="";

		for(var i=0; i<document.getElementById("allUnames").options.length;i++)
			{
			if(i == document.getElementById("allUnames").options.selectedIndex)
				{
					postId=document.getElementById("allUnames").options[i].value;
				}
			}
		
		
	var checkBx = document.userLevelConfig.elements;
		var levelForUser ="";	
			for(var t = 0 ;t < checkBx.length;t++)
			{
				if(checkBx[t].type =='checkbox' && checkBx[t].name =='chkbx')
				{
						if(checkBx[t].checked) 
						{
						 levelForUser=levelForUser+checkBx[t].id+"~";
						}
				}
			}

			xmlHttp=GetXmlHttpObject();		
		   	if (xmlHttp==null)
		   	{
			   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
			   return;
		   	} 
		   
		   if(postId != '' && postId != null)
		   {	
		   	var module= document.getElementById("hierarchy").options[document.getElementById("hierarchy").options.selectedIndex].text;
		    var uri='ifms.htm?actionFlag=insertDataForUserLevel&userLevelId='+levelForUser+'&pId='+postId+'&Desc='+document.getElementById("hierarchy").value+'&module='+module;// +'&workFlow='+workFlow;

		   	           
		    xmlHttp.onreadystatechange=setLevelForUser;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);
		   	}
		   	else
		   	{
		   	alert('Please select a user for which the role is to be Assigned.');
		   	document.getElementById("btnChanges").disabled= false;
		   	hideProgressbar();
		   	return;
		   	}
		 	
}
function setLevelForUser()
{
if (xmlHttp.readyState==complete_state)
	    {
	   	var state = xmlHttp.responseText;
	   	alert(state);
	   	document.getElementById("btnChanges").disabled= false;
	   	hideProgressbar();
	   //  location.reload();
	  //   reloadThisPage();
	    }
}

function srchUser()
{
	xmlHttp=GetXmlHttpObject();		
	   	if (xmlHttp==null)
	   	{
		   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
		   return;
	   	} 
   		var uri='ifms.htm?actionFlag=getUsers&uName='+document.getElementById("txtSrchUser").value+'&Desc='+document.getElementById("hierarchy").value;
	   	           
	   	xmlHttp.onreadystatechange=getUserForConfig;
	   	xmlHttp.open("POST",uri,false);
	   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   	xmlHttp.send(uri);
		   	 	
}

function getUserForConfig()
{
if (xmlHttp.readyState==complete_state)
	    {
	   	var state = xmlHttp.responseText;
	   	alert(state);
	   	document.getElementById("btnChanges").disabled= false;
	   	hideProgressbar();
	    // location.reload();
	  //   reloadThisPage();
	    }
}

function getRoleElement()
{
	window.open('ifms.htm?actionFlag=getElementConfigPg','','height=700,width=1000,top=100,left=100,status=no,toolbar=no,menubar=no,location=no');
}

function getSearchResult()
{

for(var i=0; i<document.getElementById("allUnames").options.length;i++)
{
document.getElementById("allUnames").options.selectedIndex = false;
}

document.getElementById('allUnames').disabled=true;
document.getElementById('btnAddUser').disabled = false;

var checkBx = document.userLevelConfig.elements;
			
			for(var t = 0 ;t < checkBx.length;t++)
			{
			if(checkBx[t].type =='checkbox' && checkBx[t].name =='chkbx')
			{
					checkBx[t].checked = false;
			}
			}

document.getElementById('btnChanges').disabled= true;
	if(document.getElementById('txtSrchUser').value == "")
	{
	alert("Please Enter a value to Search");
	document.getElementById('allUnames').disabled=false;
	return;
	}
	else if( document.getElementById("hierarchy")[document.getElementById("hierarchy").options.selectedIndex].value == 'module')
	{
	alert("Please Select a module");
	document.getElementById('allUnames').disabled=false;
	return;
	}
	else
	{
	xmlHttp=GetXmlHttpObject();		
		   	if (xmlHttp==null)
		   	{
			   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
			   return;
		   	} 
		   	 
		   	var uri='ifms.htm?actionFlag=getUserDtls&userName='+document.getElementById('txtSrchUser').value;
		   	           
		   	xmlHttp.onreadystatechange=getUserName;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);
	}

}
/*
function getUserName()
{
var pid = "";
var uName="";

if (xmlHttp.readyState==complete_state)
	    {
	   	var state = xmlHttp.responseText;
	   		if(state != null)
	  		{
	   			var userDtls = state.split("~");
	   			pid=userDtls[0];
	   			uName=userDtls[1];
	   			document.getElementById('txtUserName').value=uName;
	   			document.getElementById('txtPostid').value=pid;
	   		}
	    }
}

function addUserforType()
{
	var checkBx = document.userLevelConfig.elements;
		var levelForUser ="";	

			for(var t = 0 ;t < checkBx.length;t++)
			{
				if(checkBx[t].type =='checkbox' && checkBx[t].name =='chkbx')
				{
						if(checkBx[t].checked) 
						{
						 levelForUser=levelForUser+checkBx[t].id+"~";

						}
				}
			}

	var allLevels = new Array();
	allLevels = levelForUser.split("~");

	if(allLevels[0] !="" && allLevels != null)
	{
		var msg ="Are you sure you want to Add User \n "+document.getElementById('txtUserName').value+" to "+ document.getElementById("hierarchy").value +"\n for Levels "+allLevels; 
		if(confirm(msg)== true)
		{
			xmlHttp=GetXmlHttpObject();		
			   	if (xmlHttp==null)
			   	{
				   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
				   return;
			   	}
			   	 
			   	var module= document.getElementById("hierarchy").options[document.getElementById("hierarchy").options.selectedIndex].text; 
			   	var uri='ifms.htm?actionFlag=insertDataForUserLevel&postId='+document.getElementById('txtPostid').value+'&Desc='+document.getElementById("hierarchy").value+'&userLevelId='+levelForUser+'&module='+module;
			   	           
			   	xmlHttp.onreadystatechange=getUserForConfig;
			   	xmlHttp.open("POST",uri,false);
			   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			   	xmlHttp.send(uri);
		}
	}
	else
	{
	alert('Please select levels for the user to be assigned.');
	}

}
*/

function getUserName()
{
var pid = "";
var uName="";

if (xmlHttp.readyState==complete_state)
	    {
	   	var state = xmlHttp.responseText;
	   
	   		if(state == 'NoUser')
	   		{
	   			alert("Please Enter a Valid UserName");
	   			document.getElementById('txtUserName').value="";
	   			document.getElementById('allUnames').disabled=false;
	   		}
	   		else
	   		{
	   			var userDtls = state.split("~");
	   			pid=userDtls[0];
	   			uName=userDtls[1];
	   			document.getElementById('txtUserName').value=uName;
	   			document.getElementById('txtPostid').value=pid;
	   		}
	    }
}

function addUserforType()
{

for(var i=0; i<allUserNames.length ;i++)
{  
	if(allUserNames[i] != "")
	{
	var userName= allUserNames[i].split('~');
		if(userName[1] == document.getElementById('txtPostid').value)
		{
		alert('The User is already assigned to this module.\n '+
		'Kindly select the user from the UserNames available and select the role and Click on Apply Changes ');
		return;
		}
	}
}


if(document.getElementById('txtUserName').value != "")
{
document.getElementById('allUnames').disabled=false;
	var checkBx = document.userLevelConfig.elements;
		var levelForUser ="";	
		var levelNameForUser ="";
			for(var t = 0 ;t < checkBx.length;t++)
			{
				if(checkBx[t].type =='checkbox' && checkBx[t].name =='chkbx')
				{
						if(checkBx[t].checked) 
						{
						 levelForUser=levelForUser+checkBx[t].id+"~";
						 levelNameForUser = levelNameForUser+checkBx[t].value+"`";
						}
				}
			}

	var allLevels = new Array();
	allLevels = levelForUser.split("~");
	
	var allLevelName = new Array();
	allLevelName =levelNameForUser.split("`");
	
	var module =document.getElementById("hierarchy").options[document.getElementById("hierarchy").options.selectedIndex].text;
	
	if(allLevels[0] !="" && allLevels != null)
	{
		var msg ="Are you sure you want to Add User \n "+document.getElementById('txtUserName').value+" to "+ module +"\n for Levels "+allLevelName; 
		if(confirm(msg)== true)
		{
			xmlHttp=GetXmlHttpObject();		
			   	if (xmlHttp==null)
			   	{
				   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
				   return;
			   	}
			   	
			   	// var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value; 
			   	var module= document.getElementById("hierarchy").options[document.getElementById("hierarchy").options.selectedIndex].text; 
			   	var uri='ifms.htm?actionFlag=insertDataForUserLevel&postId='+document.getElementById('txtPostid').value+'&Desc='+document.getElementById("hierarchy").value+'&userLevelId='+levelForUser+'&module='+module;// +'&locCode='+locationCd;
			   	           
			   	xmlHttp.onreadystatechange=getUserForConfig;
			   	xmlHttp.open("POST",uri,false);
			   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			   	xmlHttp.send(uri);
		}
	}
	else
	{
	alert('Please select levels for the user to be assigned.');
	document.getElementById('allUnames').disabled=true;
	}
}
else
{
alert('Please enter a user Name');
}
	
}
function canSearch()
{
document.getElementById('allUnames').disabled=false;
document.getElementById('txtSrchUser').value="";
document.getElementById('btnAddUser').disabled = true;
document.getElementById('txtUserName').value="";
document.getElementById('txtPostid').value="";

var checkBx = document.userLevelConfig.elements;
			
for(var t = 0 ;t < checkBx.length;t++)
{
	if(checkBx[t].type =='checkbox' && checkBx[t].name =='chkbx')
	{
			checkBx[t].checked = false;
	}
}
			
			
}

// getDataOnLoad();

</script>
</html>



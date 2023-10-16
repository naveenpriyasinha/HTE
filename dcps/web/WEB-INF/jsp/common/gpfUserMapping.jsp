<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map,com.tcs.sgv.common.valueobject.CmnLocationMst,
com.tcs.sgv.common.helper.SessionHelper,java.util.ArrayList,com.tcs.sgv.apps.common.valuebeans.ComboValuesVO"%>
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

<%

String userType = "DAT";
if(request.getParameter("userType") != null)
	userType = request.getParameter("userType");
ResultObject rs = (ResultObject) request.getAttribute("result");
Map objectArgs = (Map) rs.getResultValue();

CmnLocationMst lObjCmnLocationMst = SessionHelper.getLocationVO(objectArgs);
String loginDeptId = lObjCmnLocationMst.getDepartmentId()+"";
System.out.println("loginDeptId--"+loginDeptId);
ArrayList deptList = new ArrayList();


ComboValuesVO lObjComboValuesVO1 = new ComboValuesVO();;
lObjComboValuesVO1.setId("100003");
lObjComboValuesVO1.setDesc("Treasury Office");
deptList.add(lObjComboValuesVO1);


ComboValuesVO lObjComboValuesVO2 = new ComboValuesVO();;
lObjComboValuesVO2.setId("100004");
lObjComboValuesVO2.setDesc("Pay and Accounts Office");
deptList.add(lObjComboValuesVO2);

ComboValuesVO lObjComboValuesVO3 = new ComboValuesVO();;
lObjComboValuesVO3.setId("100005");
lObjComboValuesVO3.setDesc("Pension Payment Office");
deptList.add(lObjComboValuesVO3);

ComboValuesVO lObjComboValuesVO4 = new ComboValuesVO();;
lObjComboValuesVO4.setId("100006");
lObjComboValuesVO4.setDesc("Sub Treasury Office");
deptList.add(lObjComboValuesVO4);

ComboValuesVO lObjComboValuesVO5 = new ComboValuesVO();;
lObjComboValuesVO5.setId("100001");
lObjComboValuesVO5.setDesc("Sachivalay Department");
deptList.add(lObjComboValuesVO5);

ComboValuesVO lObjComboValuesVO6 = new ComboValuesVO();;
lObjComboValuesVO6.setId("100011");
lObjComboValuesVO6.setDesc("Head of Department");
deptList.add(lObjComboValuesVO6);

/*
ComboValuesVO lObjComboValuesVO7 = new ComboValuesVO();;
lObjComboValuesVO7.setId("100010");
lObjComboValuesVO7.setDesc("Local Fund Office");
deptList.add(lObjComboValuesVO7);

ComboValuesVO lObjComboValuesVO8 = new ComboValuesVO();;
lObjComboValuesVO8.setId("100007");
lObjComboValuesVO8.setDesc("DDO Department");
deptList.add(lObjComboValuesVO8);

ComboValuesVO lObjComboValuesVO9 = new ComboValuesVO();;
lObjComboValuesVO9.setId("400001");
lObjComboValuesVO9.setDesc("Pay Verification Unit,Gnr");
deptList.add(lObjComboValuesVO9);


ComboValuesVO lObjComboValuesVO10 = new ComboValuesVO();;
lObjComboValuesVO10.setId("230001");
lObjComboValuesVO10.setDesc("PWD Department");
deptList.add(lObjComboValuesVO10);

ComboValuesVO lObjComboValuesVO11 = new ComboValuesVO();;
lObjComboValuesVO11.setId("100008");
lObjComboValuesVO11.setDesc("LC_Division");
deptList.add(lObjComboValuesVO11);

ComboValuesVO lObjComboValuesVO12 = new ComboValuesVO();;
lObjComboValuesVO12.setId("100009");
lObjComboValuesVO12.setDesc("LC Sub Division");
deptList.add(lObjComboValuesVO12);
*/


%>

<script  type="text/javascript"  src="script/common/statusbar.js"></script>


<html>
<body> 

<form name="userLevelConfig" action="post">
<table width="100%">
<tr><td><b>Note:</b></tr> 
<tr><td>1.Please clear cache and temporary internet files after you assign or revoke new role to any user</td></tr> 
<tr><td>2.Wait for approximately 15 mins for reflection.</td></tr>
<tr><td>3.Please process all the data from the worklist before you revoke any roles from a user.</td></tr>
</table>
<hr>

<table border="0" width="100%">
<tr>
<td width="15%">Offices :</td>
<td width="15%">
	<select id="Offices" name="Offices" onchange="getLocation();" tabindex="10">
	<option value="Office">Select an Office</option>
	
	
	<%
	ComboValuesVO lObjComboValuesVOCurr = null;
	for(int i=0;i<deptList.size();i++)
	{
		lObjComboValuesVOCurr = (ComboValuesVO)deptList.get(i);
		if(userType.equalsIgnoreCase("DAT"))
		{	
			if(loginDeptId.equals(lObjComboValuesVOCurr.getId())){%>
				<option value="<%=lObjComboValuesVOCurr.getId()%>" selected="selected" ><%=lObjComboValuesVOCurr.getDesc()%></option>
			<%}else{%>
				<option value="<%=lObjComboValuesVOCurr.getId()%>"><%=lObjComboValuesVOCurr.getDesc()%></option>
			<%}%>	
		
		<%} else if (userType.equalsIgnoreCase("TO") && loginDeptId.equals(lObjComboValuesVOCurr.getId()))
		{%>
		<option value="<%=lObjComboValuesVOCurr.getId()%>" selected="selected" ><%=lObjComboValuesVOCurr.getDesc()%></option>
		<%}%>
	
	<%} %>
	</select>
	</td>
	<td width="15%">All Locations :</td>
	<td width="55%"> <select id="Loc" name="Loc" onchange="getModules();getPostNameFromPostId(0);" tabindex="20">
						<option value="Loc">Please select a Location</option>
						</select>
	</td>
</tr>

<tr>
<td>Activities :</td>
<td>
	<select id="hierarchy" name="hierarchy" onchange="getData();getPostNameFromPostId(0);" tabindex="30">
	<option value="module">Please Select Activity</option>
	</select>
</td>	
<td  colspan="2">(If user is not in the below user list for the activity then search user here)</td>
</tr>

<tr>
<td>User's Login Name : </td>
<td colspan="3">
<input type="text" id="txtSrchUser" name="txtSrchUser" tabindex="40"></input> 
<input type="button" class="bigbutton" id="btnSearch" name="btnSearch" value="Search" onclick="getSearchResult();" tabindex="50"></input>
<input  type="hidden" id="txtPostid" name="txtPostid"></input>
<input  type="hidden" id="userType" name="userType" value="<%=userType%>"></input>

<input  type="text" id="txtUserName" name="txtUserName" disabled="disabled" tabindex="60"></input>
<input type="button" class="bigbutton" id="btnAddUser" name="btnAddUser" value="Add User" onclick="addUserforType();" tabindex="70"></input>
<input type="button" class="bigbutton" id="btncanSrch" name="btncanSrch" value="Cancel Search" onclick="canSearch();" tabindex="80"></input>
</td>
</tr>	
<tr>
<td colspan="4">
	<table width="100%" border="0">
	<tr>
	<td width="30%" valign="top">
	<div id="uNames" align="left" style="border: thin ,black;">
	<b>All Users For the Activity</b>
		<select multiple="multiple" name="allUnames" id="allUnames" style="width: 100%;height: 400px;" onclick="getAllLevelForUser();"></select>
	</div>
	</td>
	
	<td width="40%" valign="top">
	<b>All Sub Activities </b>
	<div id="uLevel" align="left" style="border: 1px solid black;background-color: white ;height: 390px; overflow: scroll;" >
	
		<table>
		<tbody id="allLevels"></tbody>
		</table>
	</div>
	</td>
	<td width="40%" valign="top">
	<b>All Sub Activities Mapped to the User</b>
	<div id="allUDtls" align="left" style="border: 1px solid black;background-color: white ;height: 390px; overflow: scroll;"  >
	
	<table >
		<tbody id="allDtls"></tbody>
		</table>
	</div>
	</td>
	</tr>
	
	<tr>
	<td colspan="2" align="left"> User's Post Name:
	<div id="tdPostName" style="display: inline"></div>
	</td>
	</tr>
	<tr align="center">
 	<td colspan="2">
	<input type="button" id="btnChanges" class="bigbutton" name="btnChanges" value="Apply Changes" disabled="disabled" onClick="applyChanges();" tabindex="90"/>
	<!-- <input type="button" id="btnShowScreen" class="bigbutton" name="btnShowScreen" value="Role Element Screen"  onClick="getRoleElement();"/> -->
	<input type="button" id="btnClose" class="bigbutton" name="btnClose" value="Close"  onClick="window.location.href='ifms.htm?viewName=homePage'" tabindex="100"/>
	</td>
	
	</tr>
	</table>
</td>
</tr>	
</table>	
</form>

</body>
<script>
getLocation();
allPostName = new Array();
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

function getLocation()
{	
/*to remove the username which has been searched for another module on change of module*/
document.getElementById('txtUserName').value="";
document.getElementById('txtSrchUser').value="";

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
	

	var officeCode = document.getElementById("Offices")[document.getElementById("Offices").options.selectedIndex].value ;
	
	/*empty the select box for location*/
			
			for(var i=document.getElementById("Loc").options.length; i>=0;i--)
			{
					document.getElementById("Loc").options.remove(i);
			}
	/*emptied the select box*/
	
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
	
	
	/*to show the combo box of the for module*/
			 allNames = document.getElementById('hierarchy');
	
			 x =document.createElement('option');
			 x.text="Please Select Activity";
			 x.value="module";
			 allNames.add(x);
	/*to show the combo box for the module*/
	
	if(document.getElementById("Offices")[document.getElementById("Offices").options.selectedIndex].value != 'Office')
	{
 	xmlHttp=GetXmlHttpObject();		
   	if (xmlHttp==null)
   	{
	   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
	   return;
   	} 
   	 
   	var userType = document.getElementById("userType").value;
   	var uri='ifms.htm?actionFlag=getAllLocation&officeCode='+officeCode+"&userType="+userType;// &Desc='+modOpt;
   	           
   	xmlHttp.onreadystatechange=getAllLocs;
   	xmlHttp.open("POST",uri,false);
   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   	xmlHttp.send(uri);
   	}
   	else
   	{
   	/*to display the combo box asking for location*/
   	var locName = 	document.getElementById('Loc');
	 x =document.createElement('option');
	 x.text="Please select a Location";
	 x.value="module";
	 locName.add(x);
   	}
	
}

function getAllLocs()
{
/*for all locations*/
	if (xmlHttp.readyState==complete_state)
    { 

    var state = xmlHttp.responseText;
	var arrLoc = state;
	
	var allLocs = arrLoc.split("^");
	
	var locName = 	document.getElementById('Loc');
	 x =document.createElement('option');
	 x.text="Please select a Location";
	 x.value="Loc";
	 locName.add(x);
	
	for(var i=0; i<allLocs.length ;i++)
		{  
			if(allLocs[i] != "")
			{
			 var loc = allLocs[i].split("~");			
			 y= document.createElement('option');
			 y.text=loc[1];
			 y.value=loc[0];
			 locName.add(y);
			}
		}
	document.getElementById('Loc').focus();	
	}
}


function getModules()
{

/*to remove the username which has been searched for another module on change of module*/
document.getElementById('txtUserName').value="";
document.getElementById('txtSrchUser').value="";

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
			
				
	  	   	xmlHttp=GetXmlHttpObject();		
		   	if (xmlHttp==null)
		   	{
			   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
			   return;
		   	} 
		   	
		   	if(document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value != 'Loc' && 
		   		document.getElementById("Offices")[document.getElementById("Offices").options.selectedIndex].value != 'Office')
		   	{
		   	var officeCode = document.getElementById("Offices")[document.getElementById("Offices").options.selectedIndex].value ;
		   	var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value;
		   	var uri='ifms.htm?actionFlag=getAllModules&locCode='+locationCd+'&officeCode='+officeCode; // &Desc='+modOpt;
		   	           
		   	xmlHttp.onreadystatechange=getDataForModules;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);
			}
			else
			{
				/*
				if(document.getElementById("Offices")[document.getElementById("Offices").options.selectedIndex].value != 'Office')
				{
				alert('Please select a valid Office');
				}
				else
				{
				alert('Kindly select a valid Location');
				} 
				*/
			
			/*to show the combo box of the for module*/
			 allNames = document.getElementById('hierarchy');
	
			 x =document.createElement('option');
			 x.text="Please Select Activity";
			 x.value="module";
			 allNames.add(x);
			 
		 	document.getElementById('txtUserName').value="";
   			document.getElementById('txtSrchUser').value="";
			}
}

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
	 x.text="Please Select Activity";
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
/*to remove the username which has been searched for another module on change of module*/
document.getElementById('txtUserName').value="";
document.getElementById('txtSrchUser').value="";

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
			
	  	   	xmlHttp=GetXmlHttpObject();		
		   	if (xmlHttp==null)
		   	{
			   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
			   return;
		   	} 

			if( document.getElementById("hierarchy")[document.getElementById("hierarchy").options.selectedIndex].value != 'module')
			{
			var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value;
		   	var uri='ifms.htm?actionFlag=getAllDataForUsers&Desc='+document.getElementById("hierarchy").value+'&locCode='+locationCd;// +'&workFlow='+workFlow;
		   	           
		   	xmlHttp.onreadystatechange=getDataForDesc;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);
		   	}
		   	else
		   	{
		   //	alert('Please select a valid module');
		   	
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
			 allPostName[userPid[1]] = userPid[2];
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
		   	
		   	var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value; 
		   	if(locationCd == 'Loc')
		   	{
		   		document.getElementById("Loc").focus();
		   		return false;
		   	}
		   	else if(document.getElementById("hierarchy").value == 'module')
		   	{
		   		document.getElementById("hierarchy").focus();
		   		return false;
		   	}
		   	else if(postId == "")
		   	{
		   		document.getElementById("allUnames").focus();
		   		return false;
		   	}
		   			   	
		   	getPostNameFromPostId(postId);
		   	var uri='ifms.htm?actionFlag=getAllLevelForUser&postId='+postId+'&Desc='+document.getElementById("hierarchy").value+"&workFlow="+workFlow+'&locCode='+locationCd;
		   	           
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
			
		  	if(document.getElementById(level) != null && document.getElementById(level).value != "")
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

function getPostNameFromPostId(postId)
{
	if(postId == 0)
		document.getElementById('tdPostName').innerHTML = '';
	else if(allPostName[postId] != null)
   		document.getElementById('tdPostName').innerHTML = '<b>'+allPostName[postId]+'</b>';
   	else
   		document.getElementById('tdPostName').innerHTML = '';
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
		   	var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value;
		    var uri='ifms.htm?actionFlag=insertDataForUserLevel&userLevelId='+levelForUser+'&pId='+postId+'&module='+module+'&locCode='+locationCd+'&Desc='+document.getElementById("hierarchy").value;
		   	//alert(uri);           
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
	   	getData();
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
	   		
	   	var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value;
   		var uri='ifms.htm?actionFlag=getUsers&uName='+document.getElementById("txtSrchUser").value+'&Desc='+document.getElementById("hierarchy").value+'&locCode='+locationCd;
	   	           
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
	   	document.getElementById("btnChanges").disabled= false;
	   	getData();
	   	alert(state);
	   	hideProgressbar();
	    // location.reload();
	    // reloadThisPage();
	    }
}

function getRoleElement()
{
	window.open('ifms.htm?actionFlag=getElementConfigPg','','height=700,width=1000,top=100,left=100,status=no,toolbar=no,menubar=no,location=no');
}

function getSearchResult()
{
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
	else if(document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value == 'Loc')
	{
	alert("Please Select a Location");
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
		   	 	
		   	var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value;
		   	var uri='ifms.htm?actionFlag=getUserDtls&userName='+document.getElementById('txtSrchUser').value+'&locCode='+locationCd;
		   	           
		   	xmlHttp.onreadystatechange=getUserName;
		   	xmlHttp.open("POST",uri,false);
		   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   	xmlHttp.send(uri);
	}
}
function getUserName()
{
var pid = "";
var uName="";

if (xmlHttp.readyState==complete_state)
	    {
	   	var state = xmlHttp.responseText;
	   
	   		if(state == 'NoUser')
	   		{
	   			alert("This User Is Not valid for this Location");
	   			document.getElementById('txtUserName').value="";
	   			document.getElementById('allUnames').disabled=false;
	   		}
	   		else if(state == 'NoPost')
	   		{
	   			alert("This User Is Not assigned to any post");
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
		
		//if(userName[0].indexOf(document.getElementById('txtUserName').value) != -1)
		if(userName[0].indexOf(document.getElementById('txtSrchUser').value) != -1)
		{
			alert('The User is already assigned to this module.\n '+
				  'Kindly select the user from the UserNames available and select the role and Click on Apply Changes ');
			canSearch();	
			getAllLevelForUser();  
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
		var msg ="Are you sure you want to Add \n User: "+document.getElementById('txtUserName').value+"\n To Module: "+ module +"\n For Levels: "+allLevelName; 
		if(confirm(msg)== true)
		{
			xmlHttp=GetXmlHttpObject();		
			   	if (xmlHttp==null)
			   	{
				   alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
				   return;
			   	}
			   	
			   	var locationCd = document.getElementById("Loc")[document.getElementById("Loc").options.selectedIndex].value; 
			   	var module= document.getElementById("hierarchy").options[document.getElementById("hierarchy").options.selectedIndex].text; 
			   	var uri='ifms.htm?actionFlag=insertDataForUserLevel&postId='+document.getElementById('txtPostid').value+'&Desc='+document.getElementById("hierarchy").value+'&userLevelId='+levelForUser+'&module='+module+'&locCode='+locationCd;
			   	           
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
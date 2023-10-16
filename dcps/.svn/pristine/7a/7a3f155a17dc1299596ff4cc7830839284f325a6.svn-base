function ajaxfunctionForGetRole()
{
	addOrUpdateRecord("showPostRoleMappingTable", "getPostRoleMappingData", new Array('postId'), true);
	document.getElementById("getRoles").disabled=false;
	document.getElementById("btnTable").style.display='';
	document.getElementById("empIno").style.display='';
	
}          
function showPostRoleMappingTable()
{
	if (xmlHttp.readyState == 4) 
	{ 				
		if (xmlHttp.status == 200) 
		{ 					
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=xmlHttp.responseXML.documentElement;
			var displayFieldArray = new Array();
			var roleIdList = XMLDoc.getElementsByTagName('roleId');
			var length = roleIdList.length;
			
			document.getElementById("empName").innerHTML = XMLDoc.getElementsByTagName('empName')[0].text; // Employee Full Name
			document.getElementById("userName").innerHTML = XMLDoc.getElementsByTagName('userName')[0].text; // User Name
			document.getElementById("department").innerHTML = XMLDoc.getElementsByTagName('departmentName')[0].text; // Department Name
			document.getElementById("location").innerHTML = XMLDoc.getElementsByTagName('locationName')[0].text; // Office Name
			document.getElementById("designation").innerHTML = XMLDoc.getElementsByTagName('designationName')[0].text; // Designation
			document.getElementById("branch").innerHTML = XMLDoc.getElementsByTagName('branchName')[0].text; // Branch
			
			
			var tableRows = document.getElementById("postRoleTable").rows.length;
			if(tableRows > 1)
			{
				for (var iCnt=tableRows-1;iCnt>=1;iCnt--)
				{
					document.getElementById("postRoleTable").deleteRow(iCnt);
				}
			}
			
			for(i=0; i<length; i++)
			{
				displayFieldArray[0]=XMLDoc.getElementsByTagName('roleId')[i].text;
				displayFieldArray[1]=XMLDoc.getElementsByTagName('roleName')[i].text;
				displayFieldArray[2]=XMLDoc.getElementsByTagName('roleDesc')[i].text;
				displayFieldArray[3]=XMLDoc.getElementsByTagName('startDate')[i].text;
				displayFieldArray[4]=XMLDoc.getElementsByTagName('endDate')[i].text;
				displayFieldArray[5]=XMLDoc.getElementsByTagName('statusFlag')[i].text;
				
				addDataInTableRole("postRoleTable", displayFieldArray,"deleteRecord", "editRecord", "M", true);
			}
			
		}
	}
}

function submitFormData()
{
	document.frmPostRole.action="hdiits.htm?actionFlag=savePostRoleMappingData";
	document.frmPostRole.submit();
}

function searchRoles()
{
	var href = "./hdiits.htm?actionFlag=showAdminRoleDtl&checkbox=true" ;
	objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
}

function addRequestForSelectedRoleDtls(selectedRoleIdArray,selectedRoleNameArray,selectedRoleDescArray)
{	
	var alertFlag=false;
	var alertRoleName="";
	for(var i=0; i<selectedRoleIdArray.length; i++)
	{
		var statusFlag=true;
		var displayFieldArray = new Array();
		displayFieldArray[0]=selectedRoleIdArray[i];
		displayFieldArray[1]=selectedRoleNameArray[i];
		displayFieldArray[2]=selectedRoleDescArray[i];
		displayFieldArray[3]=showdate();
		displayFieldArray[4]="";
		displayFieldArray[5]="1";
		
		var tableRows = document.getElementById("postRoleTable").rows.length;
		for (var iCnt=0; iCnt<tableRows; iCnt++)
		{
			trow=document.getElementById('postRoleTable').rows[iCnt];
			if(trow.id==selectedRoleIdArray[i])
			{
				alertFlag=true;
				alertRoleName = alertRoleName +selectedRoleNameArray[i] +",";
				statusFlag=false;
				break;
			}
		}
		if(statusFlag)
		{
			addDataInTableRole("postRoleTable", displayFieldArray,"deleteRecord", "editRecord", "N", false);					
		}
	}
	if(alertFlag)
	{
		alertRoleName = alertRoleName.substring(0,alertRoleName.length-1);
		var alertString = alertRoleName+" "+postRoleMappingLabel[0];
		alert(alertString);
	}
}
function openAppWindow(actionFlag)
{
	objChildWindow = window.open(actionFlag,"","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=20, left=50, width=900, height=680, copyhistory=no");			
	objChildWindow.focus();
}
function searchSelectedPost()
{
	var txtPostName = document.getElementById("txtPostName").value;
	var href = "hdiits.htm?actionFlag=showAdminPostDtl&radio=true&txtPostName="+txtPostName ;
	objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=630, copyhistory=no");			
}
function getSelectedPost(selectedArray)
{
	document.getElementById("postId").value=selectedArray[0];
	document.frmPostRole.txtPostName.value=selectedArray[1];
	ajaxfunctionForGetRole();
}
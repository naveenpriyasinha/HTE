function displayText(){
	if(document.getElementById('txtSearchType').value==""){
		document.getElementById('txtSearchType').value=cmnRoleMappingLabel[7];
		document.getElementById('txtSearchType').style.color='#808080';
	}
}
function removeText(){
	if(document.getElementById('txtSearchType').value==cmnRoleMappingLabel[7]){
		document.getElementById('txtSearchType').value="";
		document.getElementById('txtSearchType').style.color='black';
	}
}
function setBlankValue()
{
	document.getElementById("userId").value="";
}
function sendAjexRequestForSelectedUserDtls()//This funtion is Calling form showUserDtl.jsp
{
	addOrUpdateRecord("responseForSelectedUserDtls", "getAllMappedRolesSelectedUser", new Array('userId'), true);
}	

function responseForSelectedUserDtls()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;	
			var XMLDoc=getDOMFromXML(xmlStr);
			
			document.getElementById("userTableId").style.display='';
			document.getElementById("empName").innerHTML = XMLDoc.getElementsByTagName('empName')[0].text; // Employee Full Name
			document.getElementById("userNm").innerHTML = XMLDoc.getElementsByTagName('userName')[0].text; // User Name
			document.getElementById("location").innerHTML = XMLDoc.getElementsByTagName('locationName')[0].text; // Office Name
			document.getElementById("designation").innerHTML = XMLDoc.getElementsByTagName('designationName')[0].text; // Designation
			document.getElementById("branch").innerHTML = XMLDoc.getElementsByTagName('branchName')[0].text; // Branch
		 	document.getElementById("postName").innerHTML= XMLDoc.getElementsByTagName('postName')[0].text;
		 	
			document.frmUserRoleMpg.UserName.value = XMLDoc.getElementsByTagName('empName')[0].text; // Employee Full Name
		 	
			var displayFieldArray = new Array();
			var mappedRoleList = XMLDoc.getElementsByTagName('roleMapped');
			var tableRows = document.getElementById("userActiveRoleDtls").rows.length;
			if(tableRows > 1)
			{
				for (var iCnt=tableRows-1;iCnt>=1;iCnt--)
				{
					document.getElementById("userActiveRoleDtls").deleteRow(iCnt);
				}
			}
			if(mappedRoleList!=null)
			{
				document.getElementById("userActiveRoleDtls").style.display='';
				document.getElementById("savebtnTableId").style.display='';
				document.getElementById("getRoles").disabled=false;

			    for ( var i = 0 ; i < mappedRoleList.length ; i++ )
			    {
					displayFieldArray[0]=XMLDoc.getElementsByTagName('roleId')[i].text; 
					displayFieldArray[1]=XMLDoc.getElementsByTagName('roleName')[i].text; 
					displayFieldArray[2]=XMLDoc.getElementsByTagName('roleDesc')[i].text;
					displayFieldArray[3]=XMLDoc.getElementsByTagName('startDate')[i].text; 
					displayFieldArray[4]=XMLDoc.getElementsByTagName('endDate')[i].text; 
					displayFieldArray[5]=XMLDoc.getElementsByTagName('activeFlag')[i].text;
					addDataInTableRole("userActiveRoleDtls", displayFieldArray,"deleteRecord", "editRecord", "M", true);
				}
			}
		}
	}	
}	

function submitFormData()
{
	document.frmUserRoleMpg.action="hdiits.htm?actionFlag=saveUserRoleMpgDtls";
	document.frmUserRoleMpg.submit();
}

function addRequestForSelectedRoleDtls(selectedRoleIdArray,selectedRoleNameArray,selectedRoleDescArray)//This funtion is Calling form showAdminRoleDtl.jsp
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
		
		var tableRows = document.getElementById("userActiveRoleDtls").rows.length;
		for (var iCnt=0; iCnt<tableRows; iCnt++)
		{
			trow=document.getElementById('userActiveRoleDtls').rows[iCnt];
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
			addDataInTableRole("userActiveRoleDtls", displayFieldArray,"deleteRecord", "editRecord", "N", false);
		}
	}
	if(alertFlag)
	{
		alertRoleName = alertRoleName.substring(0,alertRoleName.length-1);
		var alertString = alertRoleName+" "+cmnRoleMappingLabel[6];
		alert(alertString);
	}
}

function openAppWindow(actionFlag)
{
	objChildWindow = window.open(actionFlag,"","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=20, left=50, width=900, height=680, copyhistory=no");			
}

function removeComboValueOnEmpReq(empSearchReqFlag)
{
	if(empSearchReqFlag!="Y")
	{
		var searchTypeCombo =document.getElementById('searchType');
		if(searchTypeCombo!=null)
		{
			searchTypeCombo.remove(0);
		}
		document.getElementById("txtEmployeeNameTdID").style.display='none';
		document.getElementById("txtSearchTypeTdID").style.display='';
	}
}

var empArray = new Array();
function empSearch(from)
{
	if (from.length == 0)
    {
    	alert('Select atleast one record');
    	return false;
    }
    else if (from.length > 1)
    {
	    alert('Only one record can be selected');
    	return false;
    }		       
    
    document.getElementById("userId").value = "";
	
	for(var i=0; i<from.length; i++)
	{	
		empArray[i] = from[i].split("~"); 	
	}
	
	var empRecord = empArray[0]; 
	
	document.frmUserRoleMpg.txtEmployeeName.value = empRecord[0]; // Employee Id
	document.frmUserRoleMpg.name_txtEmployeeName.value = empRecord[1]; // Employee Full Name
	document.frmUserRoleMpg.userId.value = empRecord[2]; // user id
	
	addOrUpdateRecord("responseForSelectedUserDtls", "getAllMappedRolesSelectedUser", new Array('userId'), true);
}

function checkForEmpSearch(searchComboValue)
{
	var id = searchComboValue.value;	
	if(id == 'empName')
	{
		document.getElementById("txtSearchTypeTdID").style.display='none';
		document.getElementById("txtEmployeeNameTdID").style.display='';
	}
	else
	{
		document.getElementById("txtEmployeeNameTdID").style.display='none';
		document.getElementById("txtSearchTypeTdID").style.display='';
	}
}
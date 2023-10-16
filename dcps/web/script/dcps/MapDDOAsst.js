function showAllForMapDDOAsst()
{
	self.location.href = "ifms.htm?actionFlag=loadMapDDOAsst&elementId=700204&requestForSearch=Yes";
}

function showEmpListForMapDDOAsst()
{
	var txtSevaarthId = document.getElementById("txtSevaarthId").value.trim();
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	if(txtSevaarthId == "" && txtEmployeeName == "" )
		{
			alert('Please enter search criteria');
			return;
		}
	var url = "ifms.htm?actionFlag=loadMapDDOAsst&elementId=700204&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="+txtEmployeeName+"&requestForSearch=Yes";
	showProgressbar('Please Wait<br>Your request is in progress...');
	document.MapDDOAsstForm.action = url ;
	enableAjaxSubmit(true);
	document.MapDDOAsstForm.submit();
}

function AssignAsDDOAsst()
{
	showProgressbar();
	var finalSelectedEmployee=0;
	var totalEmployees = document.getElementById("hdnCounter").value.trim() ;
	var dcpsEmpIds = "" ;
	var orgEmpMstIds = "" ;
	var hidDDOAsstOrNot ;
	var lBlFlagAlreadyAsst = false;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{ 
			finalSelectedEmployee = k ;
			hidDDOAsstOrNot = document.getElementById("hidDDOAsstOrNot"+k).value.trim();
			if(hidDDOAsstOrNot == 'Y')
				{
					lBlFlagAlreadyAsst = true;
				}
		}
	}
	
	if(lBlFlagAlreadyAsst)
		{
			alert('Some employees selected are already DDO assistants. Please deselect them first.');
			hideProgressbar();
			return false;
		}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one employee');
		hideProgressbar();
		return false; 
	}
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value.trim()  ;
				orgEmpMstIds = orgEmpMstIds + document.getElementById("hidOrgEmpMstId"+i).value.trim()  ;
			}
			else
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value.trim() + "~" ;
				orgEmpMstIds = orgEmpMstIds + document.getElementById("hidOrgEmpMstId"+i).value.trim() + "~"  ;
			}
		}
	}	
	
	var uri="ifms.htm?actionFlag=MapDDOAsst&requestFor=Assign";
	var url="dcpsEmpIds="+dcpsEmpIds+"&orgEmpMstIds="+orgEmpMstIds;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
		        	getDataStateChangedForAssignAsDDOAssts(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForAssignAsDDOAssts(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(lBlFlag=='true')
	{
			alert("The selected employees are Mapped as DDO Assistant");
			hideProgressbar();
			self.location.href = 'ifms.htm?actionFlag=loadMapDDOAsst&elementId=700204';
	}
}
function DeAssignFromDDOAsst()
{
	showProgressbar();
	var finalSelectedEmployee=0;
	var totalEmployees = document.getElementById("hdnCounter").value.trim() ;
	var dcpsEmpIds = "" ;
	var orgEmpMstIds = "" ;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{ 
			finalSelectedEmployee = k ;
		}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one employee');
		hideProgressbar();
		return false; 
	}
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value.trim()  ;
				orgEmpMstIds = orgEmpMstIds + document.getElementById("hidOrgEmpMstId"+i).value.trim()  ;
			}
			else
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value.trim() + "~" ;
				orgEmpMstIds = orgEmpMstIds + document.getElementById("hidOrgEmpMstId"+i).value.trim()  ;
			}
		}
	}	
	
	var uri="ifms.htm?actionFlag=MapDDOAsst&requestFor=DeAssign";
	var url="dcpsEmpIds="+dcpsEmpIds+"&orgEmpMstIds="+orgEmpMstIds;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForDeAssignDDOAssts(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForDeAssignDDOAssts(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(lBlFlag=='true')
	{
			alert("The selected employees are De-Assigned from DDO Assistant's Role");
			hideProgressbar();
			self.location.href = 'ifms.htm?actionFlag=loadMapDDOAsst&elementId=700204';
	}
}
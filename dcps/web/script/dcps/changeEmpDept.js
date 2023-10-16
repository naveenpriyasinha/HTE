function populateEmpData(){

	var dcpsId= document.getElementById("txtDCPSId").value;
	if(dcpsId=="")
	{
		alert('Please Enter DCPS Id');
		return false;
	}
	 
	var uri = "ifms.htm?actionFlag=populateEmpData";
	var url="dcpsId="+dcpsId;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForGettingEmpDtls(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function dataStateChangedForGettingEmpDtls(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var dcpsIdValidOrNot = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(dcpsIdValidOrNot == 'true')
	{
		var empId=XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		var name=XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
		var ddoCode=XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
		var parentDept = XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
		var treasuryCode = XmlHiddenValues[0].childNodes[5].firstChild.nodeValue;
	
		document.getElementById('txtEmpId').value=empId;
		document.getElementById('txtName').value=name;
		document.getElementById('txtTreasuryCode').value=treasuryCode;
		document.getElementById('txtDDOCode').value=ddoCode;
		document.getElementById('txtCurrParentDept').value=parentDept;
	
		document.getElementById('txtName').disabled = true ;
		document.getElementById('txtTreasuryCode').disabled = true ;
		document.getElementById('txtDDOCode').disabled = true ;
		document.getElementById('txtCurrParentDept').disabled = true ;
	}
	else
	{
		alert('Entered DCPS Id is not valid');
	}
}

function ClearData(){
	document.getElementById("txtDCPSId").value='';
	document.getElementById("txtEmpId").value='';
	document.getElementById("txtName").value='';
	document.getElementById("txtTreasuryCode").value='';
	document.getElementById("txtDDOCode").value='';
	document.getElementById("txtCurrParentDept").value='';
	document.getElementById("cmbDept").selectedIndex=0;
}

function SaveChanges()
{
	if(!chkEmpty(document.getElementById('txtDCPSId'),'DCPS ID') || 
			!chkEmpty(document.getElementById('cmbDept'),'New Parent Department') ){
		return;
	}
	if(document.getElementById('txtEmpId').value==""){
		alert("DCPS Id does not exist");
		return;
	}

	var empId=document.getElementById('txtEmpId').value;
	var w = document.getElementById('cmbDept').selectedIndex;
	var selected_text = document.getElementById('cmbDept').options[w].text;
	var deptName = document.getElementById('cmbDept').options[w].value;

	if(deptName == document.getElementById('txtCurrParentDept').value){
		alert("Current and New Department can not be same");
		return;
	}
	
	//Save Data Using Ajax
	
	var uri = "ifms.htm?actionFlag=saveChangeInParentDept";
	var url = "empId="+empId+"&deptName="+deptName;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForSaveChangeEmpDept(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function dataStateChangedForSaveChangeEmpDept(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries  = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XMLEntries[0].childNodes[0].firstChild.nodeValue;
	if(successFlag)
	{
		alert("Parent Department has been changed");
		self.location.reload(true);
	}
}
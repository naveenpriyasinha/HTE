function searchDept()
{
	//alert('Inside searchLocation function');
	document.frmAdminCrtDept.action='hdiits.htm?actionFlag=showAdminDeptMst';
	document.frmAdminCrtDept.submit();
}

function addNewEntry()
{
	//alert('addNewEntry method called');
	document.forms[0].action='hdiits.htm?actionFlag=addAdminOrgDeptMst';
	document.forms[0].submit();
}


function deleteData()
{	
	var isChecked = false;
	for (var i = 0; i < document.forms[0].deletedata.length; i++) 
	{
   			if (document.forms[0].deletedata[i].checked) 
   			{
     			isChecked = true;
  			}
	}
	if(document.frmAdminCrtDept.deletedata.checked)
	{	
		isChecked = true;
	}
	if(isChecked)
	{
		var answer=confirm("Are you sure want to delete the selected data?");
		if(answer)
		{
			document.forms[0].action="hdiits.htm?actionFlag=deleteAdminOrgDeptMst";
			document.forms[0].submit();
		}
	}
	else
	{
		alert(selectCheckBoxalert);
	}
}

function test(reqId)
{
//	alert('test of js called and reqId is >>>> '+reqId);
	document.forms[0].action="hdiits.htm?actionFlag=editAdminOrgDeptMst&reqId="+reqId;
	document.forms[0].submit();		
}

function closeWindow()
{
	var multiLang = document.getElementById("multiLangHdn").value;
	document.frmAdminCrtDept.action = "hrms.htm?actionFlag=showAdminDeptMst&multiLang="+multiLang;
   	document.frmAdminCrtDept.submit();
}

function compareStartDateToEndDate()
{		
	if(document.getElementById("dtstartDate").value!="" && document.getElementById("dtendDate").value!="")
	{			
		var lFrmDate=document.getElementById("dtstartDate").value;							
		var lToDate=document.getElementById("dtendDate").value;					
		if(compareDate(lFrmDate,lToDate) < 0 )
		{
			alert(validateDatealert);
			document.getElementById('dtendDate').value='';				
		} 
	}
}

function compareStartDateToEndDateGu()
{		
	if(document.getElementById("dtstartDate_gu").value!="" && document.getElementById("dtendDate_gu").value!="")
	{			
		var lFrmDate=document.getElementById("dtstartDate_gu").value;							
		var lToDate=document.getElementById("dtendDate_gu").value;					
		if(compareDate(lFrmDate,lToDate) < 0 )
		{
			alert(validateDatealert);
			document.getElementById('dtendDate_gu').value='';				
		} 
	}
}

function showdate() {
	var t = new Date;
	var day = t.getDate();
	var month = t.getMonth() + 1;
	var year = t.getFullYear();
	if (day < 10) day = "0" + day;
	if (month < 10) month = "0" + month;
	document.frmAdminCrtDept.dtstartDate.value=day + '/' + month + '/' + year;
	document.frmAdminCrtDept.dtstartDate_gu.value=day + '/' + month + '/' + year;
}

function getCheckedRadioValue(radioName)
{
	var radioValue = "";
	
	objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
	//alert('objRadio '+objRadio);
	
	for (iter=0;iter<objRadio.length;iter++)
	{
	//	alert('iter '+iter);
		if (objRadio[iter].checked)
		{
		//	alert('inside if');
			radioValue = objRadio[iter].value;
			break;			
		}
	}
//	alert('radioValue '+radioValue);
	return 	radioValue;
}

function resetData()
{
	document.frmAdminCrtDept.txtdeptName.value = '';
	document.frmAdminCrtDept.txtdeptShrtName.value = '';
	document.getElementById('cmbParentDepartment').options[0].selected= true ;
	document.frmAdminCrtDept.dtstartDate.value = '';
	document.frmAdminCrtDept.dtendDate.value = '';
	var rdoLength = document.frmAdminCrtDept.rdoActiveFlag.length;	
	for(var i = 0; i < rdoLength; i++)
	{
		document.frmAdminCrtDept.rdoActiveFlag[i].checked = false;
	}
	
	document.frmAdminCrtDept.txtdeptName_gu.value = '';
	document.frmAdminCrtDept.txtdeptShrtName_gu.value = '';
	document.getElementById('cmbParentDepartment_gu').options[0].selected= true ;
	document.frmAdminCrtDept.dtstartDate_gu.value = '';
	document.frmAdminCrtDept.dtendDate_gu.value = '';
	var rdoLength2 = document.frmAdminCrtDept.rdoActiveFlag_gu.length;	
	for(var i = 0; i < rdoLength2; i++)
	{
		document.frmAdminCrtDept.rdoActiveFlag_gu[i].checked = false;
	}
}

function copyEngToGuj()
{
	//alert('copyEngToGuj called ');
	
	document.frmAdminCrtDept.cmbParentDepartment_gu.value = document.frmAdminCrtDept.cmbParentDepartment.value;
	document.frmAdminCrtDept.cmbParentDepartment_gu.disabled = true;
	
	document.frmAdminCrtDept.dtstartDate_gu.value = document.frmAdminCrtDept.dtstartDate.value;
	document.frmAdminCrtDept.dtstartDate_gu.disable = true;
	
	document.frmAdminCrtDept.dtendDate_gu.value = document.frmAdminCrtDept.dtendDate.value;
	document.frmAdminCrtDept.dtendDate_gu.disable = true;
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
	if(stausFlagVal == 1) {
		document.frmAdminCrtDept.rdoActiveFlag_gu[0].checked = true;
	}
	if(stausFlagVal == 2) {
		document.frmAdminCrtDept.rdoActiveFlag_gu[1].checked = true;
	}
	if(stausFlagVal == 3) {
		document.frmAdminCrtDept.rdoActiveFlag_gu[2].checked = true;
	}
	document.frmAdminCrtDept.rdoActiveFlag_gu[0].disabled = true;
	document.frmAdminCrtDept.rdoActiveFlag_gu[1].disabled = true;
	document.frmAdminCrtDept.rdoActiveFlag_gu[2].disabled = true;
}

function copyGujToEng()
{	
	document.frmAdminCrtDept.cmbParentDepartment.value = document.frmAdminCrtDept.cmbParentDepartment_gu.value;
	document.frmAdminCrtDept.cmbParentDepartment.disabled = true;
	
	document.frmAdminCrtDept.dtstartDate.value = document.frmAdminCrtDept.dtstartDate_gu.value;
	document.frmAdminCrtDept.dtstartDate.disabled = true;
	
	document.frmAdminCrtDept.dtendDate.value = document.frmAdminCrtDept.dtendDate_gu.value;
	document.frmAdminCrtDept.dtendDate.disabled = true;
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
	if(stausFlagVal == 1) {
		document.frmAdminCrtDept.rdoActiveFlag[0].checked = true;
	}
	if(stausFlagVal == 2) {
		document.frmAdminCrtDept.rdoActiveFlag[1].checked = true;
	}
	if(stausFlagVal == 3) {
		document.frmAdminCrtDept.rdoActiveFlag[2].checked = true;
	}
	document.frmAdminCrtDept.rdoActiveFlag[0].disabled = true;
	document.frmAdminCrtDept.rdoActiveFlag[1].disabled = true;
	document.frmAdminCrtDept.rdoActiveFlag[2].disabled = true;
}

function submit_frmAdminCrtDept()
{
	var langId = document.getElementById('langId').value;
	if(langId == '1')
	{
		copyEngToGuj();
		var fieldArrayEng1 = new Array('txtdeptName', 'txtdeptShrtName','dtstartDate'); 
		var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
		
		if(statusValidationEng1)
		{				
			var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
			
			if(stausFlagVal == "")
			{
				alert(selectRadioalert);
				return false;
			}
			else if(multiLang=='false' || multiLang==false)
			{
				document.frmAdminCrtDept.action="hrms.htm?actionFlag=SubmitAdminDeptData";
				document.frmAdminCrtDept.submit();
			}
			else
			{
				var fieldArrayGuj1 = new Array('txtdeptName_gu', 'txtdeptShrtName_gu'); 
				var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
				if(statusValidationGuj1)
				{
					var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
					if(stausFlagVal == "")
					{
						selectRequiredTab("rdoActiveFlag_gu");
						alert(selectRadioalert);
						return false;
					}
					else
					{
						document.frmAdminCrtDept.action="hrms.htm?actionFlag=SubmitAdminDeptData";
						document.frmAdminCrtDept.submit();
					}
				}					
			}
		}
	}
	else if(langId == '2')
	{
		copyGujToEng();	
		var fieldArrayGuj1 = new Array('txtdeptName_gu', 'txtdeptShrtName_gu'); 
		var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
		if(statusValidationGuj1)
		{
			/*if(document.getElementById("cmbDepartment_gu").value == "0")
			{
				alert(selectDeptalert);
				return false;
			}*/
			
			var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
			if(stausFlagVal == "")
			{
				alert(selectRadioalert);
				return false;
			}
			else
			{
				var fieldArrayEng1 = new Array('txtdeptName', 'txtdeptShrtName','dtstartDate'); 
				var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
				
				if(statusValidationEng1)
				{	
					var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");							
					if(stausFlagVal == "")
					{
						selectRequiredTab("rdoActiveFlag");
						alert(selectRadioalert);
						return false;
					}
					else					
					{
						document.frmAdminCrtDept.action="hrms.htm?actionFlag=SubmitAdminDeptData";
						document.frmAdminCrtDept.submit();
					}
				}						
			}
		}					
	}
}
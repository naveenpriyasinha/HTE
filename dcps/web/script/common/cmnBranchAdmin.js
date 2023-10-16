function addNewEntry()
{
	showProgressbar();
	document.forms[0].action='hdiits.htm?actionFlag=addAdminBranchDtl';
	document.forms[0].submit();
}
function editBranchRecord(branchCode){
	
	showProgressbar();
	document.forms[0].action="hdiits.htm?actionFlag=editAdminCmnBranchData&branchCode="+branchCode;
	document.forms[0].submit();		
}
 
function deleteData()
{	
	var isChecked = false;
	if(document.forms[0].deletedata != null)
	{
		for (var i = 0; i < document.forms[0].deletedata.length; i++) 
		{
	   			if (document.forms[0].deletedata[i].checked) 
	   			{
	   				
	     			isChecked = true;
	  			}
		}
		if(document.frmAdminCrtBranch.deletedata.checked)
		{	
			isChecked = true;
		}
		if(isChecked )
		{
			var answer = confirm(showBranchAlertArray[0]);
			if(answer)
			{
				showProgressbar(); 
				document.forms[0].action='hdiits.htm?actionFlag=deleteAdminCmnBranchData';
				document.forms[0].submit();
			}
		}
		else
		{
			alert(showBranchAlertArray[1]); 
		}
	}
}
function searchBranch()
{
	showProgressbar();
	document.frmAdminCrtBranch.action='hdiits.htm?actionFlag=showAdminBranchDtl';
	document.frmAdminCrtBranch.submit();
}
function submit_frmAdminCrtBranch()
{
		if(langId == '1')
		{
			copyEngToGuj();
			 		
			var fieldArrayEng1;
			
			if (enableDispNm=='true')
			{
				fieldArrayEng1 = new Array('txtBranchName', 'txtBranchDesc','txtDisplayName');
			}
			else
			{
				fieldArrayEng1 = new Array('txtBranchName', 'txtBranchDesc');
			}
			
			
			var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
			
			if(statusValidationEng1)
			{				
				var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
				
				if(stausFlagVal == "")
				{
					alert(createBranchAlertArray[0]);
					return false;
				}
				else if(multiLang=='false' || multiLang==false)
				{
					processSubmit();
				}
				else
				{
					var fieldArrayGuj1;
					
					if(enableDispNm=='true')
					{
						fieldArrayGuj1= new Array('txtBranchName_gu', 'txtBranchDesc_gu','txtDisplayName_gu');
					}
					else
					{
						fieldArrayGuj1= new Array('txtBranchName_gu', 'txtBranchDesc_gu');
					}
					
					var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
					if(statusValidationGuj1)
					{
						processSubmit();
					}						
				}					
			}	
		 
		}
		else
		{
			copyGujToEng();
			var fieldArrayEng1;
			
			if(enableDispNm=='true')
			{
				fieldArrayEng1= new Array('txtBranchName_gu', 'txtBranchDesc_gu','txtDisplayName_gu');
			}
			else
			{
				fieldArrayEng1= new Array('txtBranchName_gu', 'txtBranchDesc_gu');
			}
			
			var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
			
			if(statusValidationEng1)
			{				
				var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
				
				if(stausFlagVal == "")
				{
					alert(createBranchAlertArray[0]);
					return false;
				}
				else if(multiLang=='false' || multiLang==false)
				{
					processSubmit();
				}
				else
				{
					var fieldArrayGuj1;
					
					if (enableDispNm=='true')
					{
						fieldArrayGuj1 = new Array('txtBranchName', 'txtBranchDesc','txtDisplayName');
					}
					else
					{
						fieldArrayGuj1 = new Array('txtBranchName', 'txtBranchDesc');
					}
					
					var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
					if(statusValidationGuj1)
					{
						processSubmit();
					}						
				}					
			}
		}
}
function copyEngToGuj()
{	 
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
	if(stausFlagVal == 1) // active
		document.frmAdminCrtBranch.rdoActiveFlag_gu[0].checked = true;
	if(stausFlagVal == 2) // deactive
		document.frmAdminCrtBranch.rdoActiveFlag_gu[1].checked = true;
	if(stausFlagVal == 3) // delete
		document.frmAdminCrtBranch.rdoActiveFlag_gu[2].checked = true;
		
	document.frmAdminCrtBranch.rdoActiveFlag_gu[0].disabled = true;
	document.frmAdminCrtBranch.rdoActiveFlag_gu[1].disabled = true;
	document.frmAdminCrtBranch.rdoActiveFlag_gu[2].disabled = true;		
	
	document.getElementById('branchStartDate_gu').value = document.getElementById('branchStartDate').value;
	document.frmAdminCrtBranch.branchStartDate_gu.disabled = true;
	 
	document.getElementById('branchEndDate_gu').value = document.getElementById('branchEndDate').value;
	document.frmAdminCrtBranch.branchEndDate_gu.disabled = true;	
}
function copyGujToEng()
{
	 
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
	if(stausFlagVal == 1) // active
		document.frmAdminCrtBranch.rdoActiveFlag[0].checked = true;
	if(stausFlagVal == 2) // deactive
		document.frmAdminCrtBranch.rdoActiveFlag[1].checked = true;
	if(stausFlagVal == 3) // delete
		document.frmAdminCrtBranch.rdoActiveFlag[2].checked = true;	
				
	document.frmAdminCrtBranch.rdoActiveFlag[0].disabled = true;
	document.frmAdminCrtBranch.rdoActiveFlag[1].disabled = true;
	document.frmAdminCrtBranch.rdoActiveFlag[2].disabled = true;	
				
	document.getElementById('branchStartDate').value = document.getElementById('branchStartDate_gu').value;
	document.frmAdminCrtBranch.branchStartDate.disabled = true;
	 
	
	document.getElementById('branchEndDate').value = document.getElementById('branchEndDate_gu').value;
	document.frmAdminCrtBranch.branchEndDate.disabled = true;
}
function resetData()
{
	document.frmAdminCrtBranch.txtBranchName.value = '';
	document.frmAdminCrtBranch.txtBranchDesc.value = '';
	
	document.frmAdminCrtBranch.txtBranchName_gu.value = '';
	document.frmAdminCrtBranch.txtBranchDesc_gu.value = '';
	if(enableDispNm=='true' || enableDispNm==true)
	{
		document.frmAdminCrtBranch.txtDisplayName.value = '';
		document.frmAdminCrtBranch.txtDisplayName_gu.value = '';
	}
	document.frmAdminCrtBranch.branchEndDate.value = '';
	document.frmAdminCrtBranch.branchEndDate.value = '';
}

function compareBranchNames()
{
	if(multiLang=='true' || multiLang==true)
	{
		var branchName=document.getElementById('txtBranchName').value;
		var branchName_gu=document.getElementById('txtBranchName_gu').value;

		if(branchName.toLowerCase() == branchName_gu.toLowerCase())
		{
			alert(createBranchAlertArray[1]);
			return false;
		}
	}
	return true;
}
function createArrayToPassValue()
{
	 
	var flag=document.getElementById('flag').value;
	var langID = document.getElementById('langId').value;
	var multiLangVal = document.getElementById('multiLang').value;
	
	if(flag=='add' && multiLangVal == 'false')
	{
		branchArray =new Array('flag','txtBranchName','multiLang','enableDispNm');
	}
	else if(flag=='add' && multiLangVal == 'true')
	{
		branchArray = new Array('flag','txtBranchName','txtBranchName_gu','multiLang','enableDispNm');
	}
	else if(flag=='edit' && multiLangVal == 'false')
	{
		branchArray = new Array('flag','branchCode','txtBranchName','multiLang','enableDispNm');
	}
	else if(flag=='edit' && multiLangVal == 'true')	
	{
		branchArray = new Array('flag','branchCode','txtBranchName','txtBranchName_gu','multiLang','enableDispNm');
	}
	if(enableDispNm=='true' && multiLangVal == 'true')
	{
		branchArray = branchArray.concat(new Array('txtDisplayName','txtDisplayName_gu'));
	}
	if(enableDispNm=='true' && multiLangVal == 'false')
	{
		branchArray[branchArray.length]='txtDisplayName';
	}
}
function processSubmit()
{
	if(compareBranchNames())
	{
		showProgressbar();
		createArrayToPassValue();
		addOrUpdateRecord("processResponseForValidateBranchNm","validateBranchName",branchArray,false);
	}
}
function processResponseForValidateBranchNm()
{
	var tempAlert1, tempAlert2;
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);  
			var validateValue = XMLDoc.getElementsByTagName('nameFlag');
			var validateLang= XMLDoc.getElementsByTagName('langFlag');
			var validateFlag=validateValue[0].childNodes[0].text;
			var langFlag;
			
			if(validateFlag =='true')
			{
				if(enableDispNm=='true')
				{
					var dispNmValidateValue = XMLDoc.getElementsByTagName('dispNameFlag');
					var dispNmValidateLang= XMLDoc.getElementsByTagName('dispNameLangFlag');
					var dispNmValidateFlag=dispNmValidateValue[0].childNodes[0].text;
					if(dispNmValidateFlag =='true')
					{
						document.frmAdminCrtBranch.action='hdiits.htm?actionFlag=submitAdminBranchDtl';
						document.frmAdminCrtBranch.submit();
					}
					else
					{
						langFlag=dispNmValidateLang[0].childNodes[0].text;
						hideProgressbar();
						generateAlert(langFlag,alertArray[8]);
						return false;
					}
				}
				else
				{
					document.frmAdminCrtBranch.action='hdiits.htm?actionFlag=submitAdminBranchDtl';
					document.frmAdminCrtBranch.submit();
				}
			}
			else
			{
				langFlag=validateLang[0].childNodes[0].text;
				hideProgressbar();
				generateAlert(langFlag,alertArray[0]);
				return false;
			}
		}
	}
}
function generateAlert(langFlag,varAlert)
{
	var tempAlert1, tempAlert2;
	if(langFlag =='gu')
	{
		tempAlert1=alertArray[3];
		tempAlert2=alertArray[2];
	}
	else if(langFlag =='en')
	{
		tempAlert1=alertArray[5];
		tempAlert2=alertArray[4];
	}
	else if(langFlag == 'both')
	{
		tempAlert1=alertArray[7];
		tempAlert2=alertArray[6];
	}
	var nameExistAlert;
	if(multiLang=='true')
	{
		nameExistAlert=varAlert+' '+tempAlert1+' '+alertArray[1]+' '+tempAlert2;
	}
	else
	{
		nameExistAlert=varAlert+' '+alertArray[1];
	}
	alert(nameExistAlert);
}
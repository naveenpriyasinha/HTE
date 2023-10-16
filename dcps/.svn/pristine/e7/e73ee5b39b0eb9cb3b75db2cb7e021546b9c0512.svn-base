function searchGrade()
{
	//alert('Inside searchLocation function');
	document.frmAdminCrtGradeMst.action='hdiits.htm?actionFlag=showAdminGradeMst';
	document.frmAdminCrtGradeMst.submit();
}

function addNewEntry()
{
	//alert('addNewEntry method called');
	document.forms[0].action='hdiits.htm?actionFlag=addAdminOrgGradeMst';
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
	if(document.frmAdminCrtGradeMst.deletedata.checked)
	{	
		isChecked = true;
	}
	if(isChecked)
	{
		var answer=confirm("Are you sure want to delete the selected data?");
		if(answer)
		{
			document.forms[0].action="hdiits.htm?actionFlag=deleteAdminOrgGradeMst";
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
	document.forms[0].action="hdiits.htm?actionFlag=editAdminOrgGradeMst&reqId="+reqId;
	document.forms[0].submit();		
}

function closeWindow()
{
	var multiLang = document.getElementById("multiLangHdn").value;
	document.frmAdminCrtGradeMst.action = "hrms.htm?actionFlag=showAdminGradeMst&multiLang="+multiLang;
   	document.frmAdminCrtGradeMst.submit();
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
	document.frmAdminCrtGradeMst.txtgradeName.value = '';
	document.frmAdminCrtGradeMst.txtgradeDesc.value = '';
	var rdoLength = document.frmAdminCrtGradeMst.rdoActiveFlag.length;	
	for(var i = 0; i < rdoLength; i++)
	{
		document.frmAdminCrtGradeMst.rdoActiveFlag[i].checked = false;
	}
	
	document.frmAdminCrtGradeMst.txtgradeName_gu.value = '';
	document.frmAdminCrtGradeMst.txtgradeDesc_gu.value = '';
	var rdoLength2 = document.frmAdminCrtGradeMst.rdoActiveFlag_gu.length;	
	for(var i = 0; i < rdoLength2; i++)
	{
		document.frmAdminCrtGradeMst.rdoActiveFlag_gu[i].checked = false;
	}
}

function copyEngToGuj()
{
	//alert('copyEngToGuj called ');
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
	if(stausFlagVal == 1) {
		document.frmAdminCrtGradeMst.rdoActiveFlag_gu[0].checked = true;
	}
	if(stausFlagVal == 2) {
		document.frmAdminCrtGradeMst.rdoActiveFlag_gu[1].checked = true;
	}
	if(stausFlagVal == 3) {
		document.frmAdminCrtGradeMst.rdoActiveFlag_gu[2].checked = true;
	}
	document.frmAdminCrtGradeMst.rdoActiveFlag_gu[0].disabled = true;
	document.frmAdminCrtGradeMst.rdoActiveFlag_gu[1].disabled = true;
	document.frmAdminCrtGradeMst.rdoActiveFlag_gu[2].disabled = true;
}

function copyGujToEng()
{	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
	if(stausFlagVal == 1) {
		document.frmAdminCrtGradeMst.rdoActiveFlag[0].checked = true;
	}
	if(stausFlagVal == 2) {
		document.frmAdminCrtGradeMst.rdoActiveFlag[1].checked = true;
	}
	if(stausFlagVal == 3) {
		document.frmAdminCrtGradeMst.rdoActiveFlag[2].checked = true;
	}
	document.frmAdminCrtGradeMst.rdoActiveFlag[0].disabled = true;
	document.frmAdminCrtGradeMst.rdoActiveFlag[1].disabled = true;
	document.frmAdminCrtGradeMst.rdoActiveFlag[2].disabled = true;
}


function submit_frmAdminCrtGrade()
{
	

	var langId = document.getElementById('langId').value;
	//alert('txtgradeName &&& '+document.getElementById('txtgradeName').value);
	//alert('txtgradeName_gu &&& '+document.getElementById('txtgradeName_gu').value);
	if(document.getElementById('txtgradeName').value==document.getElementById('txtgradeName_gu').value){
		alert(gradeNameAlert);
		/*if(langId==1){
		
			document.getElementById('txtgradeName').focus();
		}else{
			document.getElementById('txtgradeName_gu').focus();
		}*/
		return;
	}
	
	if(langId == '1')
	{
		chkGradeName('1',flag);
		copyEngToGuj();
		var fieldArrayEng1 = new Array('txtgradeName', 'txtgradeDesc'); 
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
				document.frmAdminCrtGradeMst.action="hrms.htm?actionFlag=SubmitAdminOrgGradeMstData";
				document.frmAdminCrtGradeMst.submit();
			}
			else
			{
				var fieldArrayGuj1 = new Array('txtgradeName_gu', 'txtgradeDesc_gu'); 
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
						document.frmAdminCrtGradeMst.action="hrms.htm?actionFlag=SubmitAdminOrgGradeMstData";
						document.frmAdminCrtGradeMst.submit();
					}
				}					
			}
		}
	}
	else if(langId == '2')
	{
		chkGradeName('2',flag);
		copyGujToEng();	
		var fieldArrayGuj1 = new Array('txtgradeName_gu', 'txtgradeDesc_gu'); 
		var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
		if(statusValidationGuj1)
		{
			
			var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
			if(stausFlagVal == "")
			{
				alert(selectRadioalert);
				return false;
			}
			else
			{
				var fieldArrayEng1 = new Array('txtgradeName', 'txtgradeDesc'); 
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
						document.frmAdminCrtGradeMst.action="hrms.htm?actionFlag=SubmitAdminOrgGradeMstData";
						document.frmAdminCrtGradeMst.submit();
					}
				}						
			}
		}					
	}
}


function addOrUpdateRecordforGrade(methodName, actionFlag, gradeName, langId,progressBarFlag,reqType) 
{	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	if(progressBarFlag != false)
	{
		showProgressbar("Please Wait...");	
	}
			
//	var reqBody = getRequestBody(fieldArray);	
	var url='hdiits.htm?actionFlag=' + actionFlag + '&' + 'gradeName='+gradeName + '&langId='+langId +'&reqType='+reqType;
	methodName = methodName + "()";
	
	xmlHttp.onreadystatechange=function() {
		if(xmlHttp.readyState == 4) {
			eval(methodName);
			if(progressBarFlag != false)
			{
				hideProgressbar();
			}
		}	
	}

	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(null);
}

function chkGradeName(langId,reqType){
	
//	var langId = document.getElementById('langId').value;
//	alert(langId);
	if(langId==1){
		var gradeName = document.getElementById('txtgradeName').value;
//		alert('grade Name >> '+gradeName);
		addOrUpdateRecordforGrade('processGradeNameResponse','validateGradeName',gradeName,1,reqType);	
	}else{
		var gradeName = document.getElementById('txtgradeName_gu').value;
//		alert('grade Name >> '+gradeName);
		addOrUpdateRecordforGrade('processGradeNameResponse','validateGradeName',gradeName,2,reqType);		
	}
}


function processGradeNameResponse(){

	if (xmlHttp.readyState == 4) 
	{
//		alert('ajax response >>> '+xmlHttp.responseText);
		var encXML=xmlHttp.responseText;
		if(encXML != "error"){
			if(encXML == 'true' || encXML == true){
				alert('Grade Name with already exist, please enter different name');
			}
		}else{
			alert('Error ajax in response');
		}
	}
}
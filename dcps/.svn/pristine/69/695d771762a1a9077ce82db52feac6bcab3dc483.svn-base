var districtCombo=-1;
var districtCombo_gu=-1;

var talCombo=-1;
var talCombo_gu=-1;

var cCombo=-1;
var cCombo_gu=-1;

var asgnBranchList = "";
var unasgnBranchList = "";

function addOrUpdateRecordforLocation(methodName, actionFlag, fieldArray, langId,progressBarFlag) 
{	
	/*xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	}*/
	if(progressBarFlag != false)
	{
		showProgressbar("Please Wait...");	
	}
			
	var reqBody = getRequestBody(fieldArray);	
	var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody+ '&langId='+langId;
	/*methodName = methodName + "()";
	
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
	*/
	new Ajax.Request(encodeURI(url),
	{
		method: 'POST',
		onSuccess: function(resXmlHttp)
		{
			window[methodName](resXmlHttp);
			if(progressBarFlag != false)
			{
				hideProgressbar();
			}
		},
	    onFailure: alertOnFailureForMstScr,
	    asynchronous: false
	} );
}	

function searchLocation()
{
	showProgressbar();
	document.frmAdminCrtLocation.action='hdiits.htm?actionFlag=showAdminLocationMst';
	document.frmAdminCrtLocation.submit();
}

function addNewEntry()
{
	showProgressbar();
	document.forms[0].action='hdiits.htm?actionFlag=addAdminCmnLocationMst';
	document.forms[0].submit();
}

function deleteData()
{	
	var isChecked = false;
	if(document.forms[0].deletedata != null)//added by uzma
	{
		for (var i = 0; i < document.forms[0].deletedata.length; i++) 
		{
	   			if (document.forms[0].deletedata[i].checked) 
	   			{
	     			isChecked = true;
	  			}
		}
		if(document.frmAdminCrtLocation.deletedata.checked)
		{	
			isChecked = true;
		}
		if(isChecked)
		{
			var answer=confirm(confirmLocationDelete);
			if(answer)
			{
				showProgressbar();
				document.forms[0].action="hdiits.htm?actionFlag=deleteAdminCmnLocationMst";
				document.forms[0].submit();
			}
		}
		else
		{
			alert(selectCheckBoxalert);
		}
	}
}


function test(reqId)
{
//	alert('test of js called and reqId is >>>> '+reqId);
	showProgressbar();
	document.forms[0].action="hdiits.htm?actionFlag=editAdminCmnLocationMst&reqId="+reqId;
	document.forms[0].submit();		
}



function getDistrictCombo()
{
	var langId = document.getElementById('langId').value;
	var dist = "";
	
	if(langId==1){
		var talukaCmb=document.getElementById('cmbTaluka');
		talukaCmb.options.length = 1;
		
		var cityCmb=document.getElementById('cmbCity');		
		cityCmb.options.length = 1;
		
		var distCmb=document.getElementById('cmbDistrict');
		dist=document.getElementById('cmbDistrict').value;
	   	var len=distCmb.options.length;
		for(var i=0;i<=len;i++)
		{
			distCmb.remove(1);
		}
	}else{
		var talukaCmb=document.getElementById('cmbTaluka_gu');
		talukaCmb.options.length = 1;
		
		var cityCmb=document.getElementById('cmbCity_gu');		
		cityCmb.options.length = 1;
		
		var distCmb=document.getElementById('cmbDistrict_gu');
		dist=document.getElementById('cmbDistrict_gu').value;
	   	var len=distCmb.options.length;
		for(var i=0;i<=len;i++)
		{
			distCmb.remove(1);
		}
	}
	if(langId==1){
		addOrUpdateRecordforLocation('addDistrictCombo','getDistrictforLocationAdmin',new Array('cmbState'),1);	
	}else{
		addOrUpdateRecordforLocation('addDistrictCombo','getDistrictforLocationAdmin',new Array('cmbState_gu'),2);		
	}
}

function addDistrictCombo(resXmlHttp)
{	
	var encXML=resXmlHttp.responseText;
	var responseArray = encXML.split("~");
	var langId = responseArray[1];
	if(responseArray!="error")
	{
		if(langId ==1){
			document.getElementById('cmbDistrict').disabled='';
			document.getElementById('cmbDistrict').length = 1;
		}else{
			document.getElementById('cmbDistrict_gu').disabled='';
			document.getElementById('cmbDistrict_gu').length = 1;	
		}
		var districtList=responseArray[0].split("$");
		for (var i=0; i < districtList.length;++i){
			var keyValPair=districtList[i].split("/");
			if(langId ==1){
				addOption(document.getElementById('cmbDistrict'), keyValPair[1], keyValPair[0]);
			}else{
				addOption(document.getElementById('cmbDistrict_gu'), keyValPair[1], keyValPair[0]);
			}
			
		}
		
		if(langId ==1)
		{
			if (districtCombo != -1)
			{
				document.getElementById('cmbDistrict').value = districtCombo;
				districtCombo = -1;
				checkGujaratiDropdownValue(document.getElementById("cmbDistrict"));
			}
		}
		else
		{
			if (districtCombo_gu != -1)
			{
				document.getElementById('cmbDistrict_gu').value = districtCombo_gu;
				districtCombo_gu = -1;
				checkGujaratiDropdownValue(document.getElementById("cmbDistrict_gu"));
			}
		}
		
	}else
	{
		if(langId ==1){
			document.getElementById('cmbDistrict').length = 1;
		}else{
			document.getElementById('cmbDistrict_gu').length = 1;
		}
	}	
}

function getTalukaCityCombo()
{
	var langId = document.getElementById('langId').value;
	
	if(langId==1){
		var talukaCmb=document.getElementById('cmbTaluka');
		//var taluka=document.getElementById('cmbTaluka').value;
	   	talukaCmb.options.length = 1;
		addOrUpdateRecordforLocation('addTalukaCombo','getTalukaforLocationAdmin',new Array('cmbDistrict'),1);		
	}else{
		var talukaCmb=document.getElementById('cmbTaluka_gu');
		var taluka=document.getElementById('cmbTaluka_gu').value;
		talukaCmb.options.length = 1;
		addOrUpdateRecordforLocation('addTalukaCombo','getTalukaforLocationAdmin',new Array('cmbDistrict_gu'),2);			
	}

	if(langId==1){
		var cityCmb=document.getElementById('cmbCity');		
		//var city=document.getElementById('cmbCity').value;	
		cityCmb.options.length = 1;
		addOrUpdateRecordforLocation('addCityCombo','getCityforLocationAdmin',new Array('cmbDistrict'),1);		
	}else{
		var cityCmb=document.getElementById('cmbCity_gu');	
		//var city=document.getElementById('cmbCity_gu').value;
		cityCmb.options.length = 1;
		addOrUpdateRecordforLocation('addCityCombo','getCityforLocationAdmin',new Array('cmbDistrict_gu'),2);			
	}
}

function addTalukaCombo(resXmlHttp)
{	
	var encXML=resXmlHttp.responseText;
	var responseArray = encXML.split("~");
	var langId = responseArray[1];
	
	if(responseArray[0]!="error")
	{
		if(langId == 1){
			document.getElementById('cmbTaluka').disabled='';
			document.getElementById('cmbTaluka').length = 1;	
		}else{
			document.getElementById('cmbTaluka_gu').disabled='';
			document.getElementById('cmbTaluka_gu').length = 1;
		}
		
		var talukaList=responseArray[0].split("$");
		for (var i=0; i < talukaList.length;++i){
			var keyValPair=talukaList[i].split("/");
			if(langId == 1){
				addOption(document.getElementById('cmbTaluka'), keyValPair[1], keyValPair[0]);
			}else{
				addOption(document.getElementById('cmbTaluka_gu'), keyValPair[1], keyValPair[0]);
			}
		}
		
		if(langId ==1)
		{
			if (talCombo != -1)
			{
				document.getElementById('cmbTaluka').value = talCombo;
				talCombo = -1;
				checkGujaratiDropdownValue(document.getElementById("cmbTaluka"));
			}
		}
		else
		{
			if (talCombo_gu != -1)
			{
				document.getElementById('cmbTaluka_gu').value = talCombo_gu;
				talCombo_gu = -1;
				checkGujaratiDropdownValue(document.getElementById("cmbTaluka_gu"));
			}
		}
		
	}else
	{
		document.getElementById('cmbTaluka').length = 1;
		document.getElementById('cmbTaluka_gu').length = 1;
	}	
}

function addCityCombo(resXmlHttp)
{	
	var encXML=resXmlHttp.responseText;
	var responseArray = encXML.split("~");
	var langId = responseArray[1];
	
	if(responseArray[0]!="error")
	{
		if(langId == 1){
			document.getElementById('cmbCity').disabled='';
			document.getElementById('cmbCity').length = 1;	
		}else{
			document.getElementById('cmbCity_gu').disabled='';
			document.getElementById('cmbCity_gu').length = 1;	
		}
		
		var cityList=responseArray[0].split("$");
		for (var i=0; i < cityList.length;++i){
			var keyValPair=cityList[i].split("/");
			if(langId == 1){
				addOption(document.getElementById('cmbCity'), keyValPair[1], keyValPair[0]);
			}else{
				addOption(document.getElementById('cmbCity_gu'), keyValPair[1], keyValPair[0]);
			}
		}
		
		if(langId ==1)
		{
			if (cCombo != -1)
			{
				document.getElementById('cmbCity').value = cCombo;
				cCombo = -1;
				checkGujaratiDropdownValue(document.getElementById("cmbCity"));
			}
		}
		else
		{
			if (cCombo_gu != -1)
			{
				document.getElementById('cmbCity_gu').value = cCombo_gu;
				cCombo_gu = -1;
				checkGujaratiDropdownValue(document.getElementById("cmbCity_gu"));
			}
		}
		
	}else
	{
		if(langId == 1){
			document.getElementById('cmbCity').length = 1;
		}else{
			document.getElementById('cmbCity_gu').length = 1;
		}
	}	
}

function addOption(selectbox,text,value )
{
	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = value;
	selectbox.options.add(optn);
}




function copyEngToGuj()
{
	showMultiLangData();
	
	document.frmAdminCrtLocation.cmbDepartment_gu.value = document.frmAdminCrtLocation.cmbDepartment.value;
	document.frmAdminCrtLocation.cmbDepartment_gu.disabled = true;
	checkGujaratiDropdownValue(document.getElementById("cmbDepartment_gu"));
	
	document.frmAdminCrtLocation.cmbParentLoc_gu.disabled = true;
	
	document.frmAdminCrtLocation.txtAddressOne_gu.value = document.frmAdminCrtLocation.txtAddressOne.value;
	document.frmAdminCrtLocation.txtAddressOne_gu.disabled = true;
	
	document.frmAdminCrtLocation.txtAddressTwo_gu.value =document.frmAdminCrtLocation.txtAddressTwo.value;
	document.frmAdminCrtLocation.txtAddressTwo_gu.disabled = true;
	
	document.frmAdminCrtLocation.cmbState_gu.value = document.frmAdminCrtLocation.cmbState.value;
	document.frmAdminCrtLocation.cmbState_gu.disabled = true;
	checkGujaratiDropdownValue(document.getElementById("cmbState_gu"));
	
	districtCombo_gu=document.frmAdminCrtLocation.cmbDistrict.value;
	
	if(districtCombo_gu!="" && document.getElementById('cmbState_gu').value!="")
	{
		addOrUpdateRecordforLocation('addDistrictCombo','getDistrictforLocationAdmin&districtCode='+districtCombo_gu,new Array('cmbState_gu'),2);
	}
	document.frmAdminCrtLocation.cmbDistrict_gu.disabled = true;
	
	talCombo_gu=document.getElementById('cmbTaluka').value;
	
	if(talCombo_gu!="" && document.getElementById('cmbDistrict_gu').value!="")
	{
		addOrUpdateRecordforLocation('addTalukaCombo','getTalukaforLocationAdmin&talukaCode='+talCombo_gu,new Array('cmbDistrict_gu'),2);
	}
	document.frmAdminCrtLocation.cmbTaluka_gu.disabled = true;
	
	cCombo_gu=document.getElementById('cmbCity').value;
	
	if(cCombo_gu!="" && document.getElementById('cmbDistrict_gu').value!="")
	{
		addOrUpdateRecordforLocation('addCityCombo','getCityforLocationAdmin&cityCode='+cCombo_gu,new Array('cmbDistrict_gu'),2);
	}
	document.frmAdminCrtLocation.cmbCity_gu.disabled = true;
	
	document.frmAdminCrtLocation.txtPincode_gu.value = document.frmAdminCrtLocation.txtPincode.value
	document.frmAdminCrtLocation.txtPincode_gu.disabled = true;
	
	document.frmAdminCrtLocation.cmbLocationType_gu.value = document.frmAdminCrtLocation.cmbLocationType.value;
	document.frmAdminCrtLocation.cmbLocationType_gu.disabled = true;
	checkGujaratiDropdownValue(document.getElementById("cmbLocationType_gu"));
	
	document.frmAdminCrtLocation.dtstartDate_gu.value = document.frmAdminCrtLocation.dtstartDate.value;
	document.frmAdminCrtLocation.dtstartDate_gu.disable = true;
	
	document.frmAdminCrtLocation.dtendDate_gu.value = document.frmAdminCrtLocation.dtendDate.value;
	document.frmAdminCrtLocation.dtendDate_gu.disable = true;
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
	if(stausFlagVal == 1) {
		document.frmAdminCrtLocation.rdoActiveFlag_gu[0].checked = true;
	}
	if(stausFlagVal == 2) {
		document.frmAdminCrtLocation.rdoActiveFlag_gu[1].checked = true;
	}
	if(stausFlagVal == 3) {
		document.frmAdminCrtLocation.rdoActiveFlag_gu[2].checked = true;
	}
	document.frmAdminCrtLocation.rdoActiveFlag_gu[0].disabled = true;
	document.frmAdminCrtLocation.rdoActiveFlag_gu[1].disabled = true;
	document.frmAdminCrtLocation.rdoActiveFlag_gu[2].disabled = true;
	
	document.frmAdminCrtLocation.officeCode_gu.value = document.frmAdminCrtLocation.officeCode.value;
	document.frmAdminCrtLocation.officeCode_gu.disabled = true;
	
	document.getElementById('phoneNumber_gu').value = document.getElementById('phoneNumber').value;
	document.getElementById('phoneNumber_gu').disabled = true;
	
	document.getElementById('faxNumber_gu').value = document.getElementById('faxNumber').value;
	document.getElementById('faxNumber_gu').disabled = true;
	
	document.getElementById('emailId_gu').value = document.getElementById('emailId').value;
	document.getElementById('emailId_gu').disabled = true;
}

function copyGujToEng()
{	
	showMultiLangData();
	document.frmAdminCrtLocation.cmbDepartment.value = document.frmAdminCrtLocation.cmbDepartment_gu.value;
	document.frmAdminCrtLocation.cmbDepartment.disabled = true;
	
	document.frmAdminCrtLocation.cmbParentLoc.disabled = true;
	
	document.frmAdminCrtLocation.txtAddressOne.value = document.frmAdminCrtLocation.txtAddressOne_gu.value
	document.frmAdminCrtLocation.txtAddressOne.disabled = true;
	
	document.frmAdminCrtLocation.txtAddressTwo.value = document.frmAdminCrtLocation.txtAddressTwo_gu.value
	document.frmAdminCrtLocation.txtAddressTwo.disabled = true;
	
	document.frmAdminCrtLocation.cmbState.value = document.frmAdminCrtLocation.cmbState_gu.value;
	document.frmAdminCrtLocation.cmbState.disabled = true;
	
	districtCombo=document.frmAdminCrtLocation.cmbDistrict_gu.value;
	
	if(districtCombo!="" && document.getElementById('cmbState').value!="")
	{
		addOrUpdateRecordforLocation('addDistrictCombo','getDistrictforLocationAdmin&districtCode='+districtCombo,new Array('cmbState'),1);
	}
	document.frmAdminCrtLocation.cmbDistrict.disabled = true;
	
	talCombo=document.getElementById('cmbTaluka_gu').value;
	
	if(talCombo!="" && document.getElementById('cmbDistrict').value!="")
	{
		addOrUpdateRecordforLocation('addTalukaCombo','getTalukaforLocationAdmin&talukaCode='+talCombo,new Array('cmbDistrict'),1);
	}
	document.frmAdminCrtLocation.cmbTaluka.disabled = true;
	
	cCombo=document.getElementById('cmbCity_gu').value;
	
	if(cCombo!="" && document.getElementById('cmbDistrict').value!="")
	{
		addOrUpdateRecordforLocation('addCityCombo','getCityforLocationAdmin&cityCode='+cCombo,new Array('cmbDistrict'),1);
	}
	
	document.frmAdminCrtLocation.cmbCity.disabled = true;
	
	document.frmAdminCrtLocation.txtPincode.value = document.frmAdminCrtLocation.txtPincode_gu.value
	document.frmAdminCrtLocation.txtPincode.disabled = true;
	
	document.frmAdminCrtLocation.cmbLocationType.value = document.frmAdminCrtLocation.cmbLocationType_gu.value;
	document.frmAdminCrtLocation.cmbLocationType.disabled = true;
	
	document.frmAdminCrtLocation.dtstartDate.value = document.frmAdminCrtLocation.dtstartDate_gu.value;
	document.frmAdminCrtLocation.dtstartDate.disabled = true;
	
	document.frmAdminCrtLocation.dtendDate.value = document.frmAdminCrtLocation.dtendDate_gu.value;
	document.frmAdminCrtLocation.dtendDate.disabled = true;
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
	if(stausFlagVal == 1) {
		document.frmAdminCrtLocation.rdoActiveFlag[0].checked = true;
	}
	if(stausFlagVal == 2) {
		document.frmAdminCrtLocation.rdoActiveFlag[1].checked = true;
	}
	if(stausFlagVal == 3) {
		document.frmAdminCrtLocation.rdoActiveFlag[2].checked = true;
	}
	document.frmAdminCrtLocation.rdoActiveFlag[0].disabled = true;
	document.frmAdminCrtLocation.rdoActiveFlag[1].disabled = true;
	document.frmAdminCrtLocation.rdoActiveFlag[2].disabled = true;
	
	document.frmAdminCrtLocation.officeCode.value = document.frmAdminCrtLocation.officeCode_gu.value;
	document.frmAdminCrtLocation.officeCode.disabled = true;
	
	document.getElementById('phoneNumber').value = document.getElementById('phoneNumber_gu').value;
	document.getElementById('phoneNumber').disabled = true;
	
	document.getElementById('faxNumber').value = document.getElementById('faxNumber_gu').value;
	document.getElementById('faxNumber').disabled = true;
	
	document.getElementById('emailId').value = document.getElementById('emailId_gu').value;
	document.getElementById('emailId').disabled = true;
}

function getCheckedRadioValue(radioName)
{
	var radioValue = "";
	
	objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
	
	for (iter=0;iter<objRadio.length;iter++)
	{
		if (objRadio[iter].checked)
		{
			radioValue = objRadio[iter].value;
			break;			
		}
	}
	return 	radioValue;
}

function resetData()
{
	document.frmAdminCrtLocation.txtlocName.value = '';
	document.frmAdminCrtLocation.txtLocShrtName.value = '';
	document.getElementById('cmbDepartment').options[0].selected= true ;
	document.getElementById('cmbParentLoc').value= '' ;
	document.frmAdminCrtLocation.txtAddressOne.value = '';
	document.frmAdminCrtLocation.txtAddressTwo.value = '';
	document.getElementById('cmbState').value=gujaratStateCode;
	document.getElementById('cmbDistrict').options[0].selected= true ;
	document.getElementById('cmbTaluka').options[0].selected= true ;
	document.getElementById('cmbCity').options[0].selected= true ;
	document.frmAdminCrtLocation.txtPincode.value = '';
	document.getElementById('cmbLocationType').value='Other_LocType';
	document.frmAdminCrtLocation.dtendDate.value = '';

	document.frmAdminCrtLocation.txtlocName_gu.value = '';
	document.frmAdminCrtLocation.txtLocShrtName_gu.value = '';
	document.getElementById('cmbDepartment_gu').options[0].selected= true ;
	document.getElementById('cmbParentLoc_gu').value= '' ;
	document.frmAdminCrtLocation.txtAddressOne_gu.value = '';
	document.frmAdminCrtLocation.txtAddressTwo_gu.value = '';
	document.getElementById('cmbState_gu').value=gujaratStateCode;
	document.getElementById('cmbDistrict_gu').options[0].selected= true ;
	document.getElementById('cmbTaluka_gu').options[0].selected= true ;
	document.getElementById('cmbCity_gu').options[0].selected= true ;
	document.frmAdminCrtLocation.txtPincode_gu.value = '';
	document.getElementById('cmbLocationType_gu').value='Other_LocType';
	document.frmAdminCrtLocation.dtendDate_gu.value = '';
	
	document.getElementById("locationCodeHdn").value="";
	document.frmAdminCrtLocation.officeCode_gu.value = '';
	document.frmAdminCrtLocation.officeCode.value = '';
	
	showdate();
	document.frmAdminCrtLocation.rdoActiveFlag[0].checked = true;
	document.frmAdminCrtLocation.rdoActiveFlag_gu[0].checked = true;
	getDistrictCombo();
	
	document.getElementById('phoneNumber').value = "";
	document.getElementById('phoneNumber_gu').value = "";
	
	document.getElementById('faxNumber').value = "";
	document.getElementById('faxNumber_gu').value = "";
	
	document.getElementById('emailId').value = "";
	document.getElementById('emailId_gu').value = "";
}



	function closeCurrWindow()//name chaged by uzma
	{
		showProgressbar();
		var multiLang = document.getElementById("multiLangHdn").value;
		document.frmAdminCrtLocation.action = "hrms.htm?actionFlag=showAdminLocationMst&multiLang="+multiLang;
	   	document.frmAdminCrtLocation.submit();
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



function submit_frmAdminCrtLocation()
{
	
	var langId = document.getElementById('langId').value;
	if(langId == '1')
	{
		copyEngToGuj();
		var fieldArrayEng1 = new Array('txtlocName', 'txtLocShrtName', 'cmbDepartment','cmbState','cmbDistrict','txtPincode', 'cmbLocationType','dtstartDate','officeCode','phoneNumber','faxNumber','emailId'); 
		var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
		if(statusValidationEng1)
		{				
			var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
			
			if(stausFlagVal == "")
			{
				alert(selectRadioalert);
				return false;
			}
			else if(document.getElementById("txtPincode").value.length<6)
			{
				alert(locationAlertArray[9]);
				document.getElementById("txtPincode").focus();
				return false;
			}
			else if(multiLang=='false' || multiLang==false)
			{
				/*document.frmAdminCrtLocation.action="hrms.htm?actionFlag=SubmitAdminLocationData";
				document.frmAdminCrtLocation.submit();*/
				if(compareLocationName())
				{
					showProgressbar();
					createArrayToPassValue();
					addOrUpdateRecord("processResponseForVaildateOfficeCode","validateOfficeCode",locationArray,false);
				}
			}
			else
			{
				var fieldArrayGuj1 = new Array('txtlocName_gu', 'txtLocShrtName_gu'); 
				var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
				if(statusValidationGuj1)
				{
					if(document.getElementById("cmbDepartment_gu").value == "0")
					{
						selectRequiredTab("cmbDepartment_gu");
						alert(selectDeptalert);
						return false;
					}
					document.frmAdminCrtLocation.cmbLocationType_gu.disabled = false;
					document.frmAdminCrtLocation.cmbState_gu.disabled = false;
					document.frmAdminCrtLocation.cmbDistrict_gu.disabled = false;
					var fieldArrayGuj2 = new Array('cmbState_gu','cmbDistrict_gu','txtPincode_gu', 'cmbLocationType_gu', 'dtstartDate_gu'); 
					var statusValidationGuj2 = validateSpecificFormFields(fieldArrayGuj2);
					document.frmAdminCrtLocation.cmbLocationType_gu.disabled = true;
					document.frmAdminCrtLocation.cmbState_gu.disabled = true;
					document.frmAdminCrtLocation.cmbDistrict_gu.disabled = true;
					if(statusValidationGuj2)
					{
						if(document.getElementById("cmbLocationType_gu").value == "0")
						{
							selectRequiredTab("cmbLocationType_gu");
							alert(selLocTypealert);
							return false;
						}
						var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
						if(stausFlagVal == "")
						{
							selectRequiredTab("rdoActiveFlag_gu");
							alert(selectRadioalert);
							return false;
						}
						else
						{
							var uniqueLocFlag = compareLocationName();
							if(uniqueLocFlag)
							{
								/*document.getElementById('txtAddressOne_gu').disabled=false;
								document.getElementById('txtAddressTwo_gu').disabled=false;
								document.getElementById('txtPincode_gu').disabled=false;
								document.frmAdminCrtLocation.action="hrms.htm?actionFlag=SubmitAdminLocationData";
								document.frmAdminCrtLocation.submit();*/
								showProgressbar();
								createArrayToPassValue();
								addOrUpdateRecord("processResponseForVaildateOfficeCode","validateOfficeCode",locationArray,false);
							}
						}
					}						
				}					
			}
			
		}
	}
	else if(langId == '2')
	{
		copyGujToEng();	
		var fieldArrayGuj1 = new Array('txtlocName_gu', 'txtLocShrtName_gu'); 
		var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
		if(statusValidationGuj1)
		{
			if(document.getElementById("cmbDepartment_gu").value == "0")
			{
				alert(selectDeptalert);
				return false;
			}
			var fieldArrayGuj2 = new Array('cmbState_gu','cmbDistrict_gu','txtPincode_gu', 'cmbLocationType_gu', 'dtstartDate_gu','officeCode_gu','phoneNumber_gu','faxNumber_gu','emailId_gu'); 
			var statusValidationGuj2 = validateSpecificFormFields(fieldArrayGuj2);
			if(statusValidationGuj2)
			{
				var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
				if(stausFlagVal == "")
				{
					alert(selectRadioalert);
					return false;
				}
				else if(document.getElementById("txtPincode_gu").value.length<6)
				{
					alert(locationAlertArray[9]);
					document.getElementById("txtPincode_gu").focus();
					return false;
				}
				else
				{
					document.frmAdminCrtLocation.cmbLocationType.disabled = false;
					document.frmAdminCrtLocation.cmbState.disabled = false;
					document.frmAdminCrtLocation.cmbDistrict.disabled = false;
					document.frmAdminCrtLocation.cmbDepartment.disabled = false;
					var fieldArrayEng1 = new Array('txtlocName', 'txtLocShrtName', 'cmbDepartment','cmbState','cmbDistrict','txtPincode', 'cmbLocationType','dtstartDate'); 
					var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
					document.frmAdminCrtLocation.cmbLocationType.disabled = true;
					document.frmAdminCrtLocation.cmbState.disabled = true;
					document.frmAdminCrtLocation.cmbDistrict.disabled = true;
					document.frmAdminCrtLocation.cmbDepartment.disabled = true;
					if(statusValidationEng1)
					{	
						if(document.getElementById("cmbDepartment").value == "0")
						{
							selectRequiredTab("cmbDepartment");
							alert(selectDeptalert);
							return false;
						}	
						if(document.getElementById("cmbLocationType").value == "0")
						{
							selectRequiredTab("cmbLocationType");
							alert(selLocTypealert);
							return false;
						}	
						var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");							
						if(stausFlagVal == "")
						{
							selectRequiredTab("rdoActiveFlag");
							alert(selectRadioalert);
							return false;
						}
						else					
						{
							/*document.getElementById('txtAddressOne').disabled=false;
							document.getElementById('txtAddressTwo').disabled=false;
							document.getElementById('txtPincode').disabled=false;
							
							document.frmAdminCrtLocation.action="hrms.htm?actionFlag=SubmitAdminLocationData";
							document.frmAdminCrtLocation.submit();*/
							var uniqueLocFlag = compareLocationName();
							if(uniqueLocFlag)
							{
								showProgressbar();
								createArrayToPassValue();
								addOrUpdateRecord("processResponseForVaildateOfficeCode","validateOfficeCode",locationArray,false);
							}
						}
					}						
				}
			}				
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
		document.frmAdminCrtLocation.dtstartDate.value=day + '/' + month + '/' + year;
		document.frmAdminCrtLocation.dtstartDate_gu.value=day + '/' + month + '/' + year;
	}
	
	function setBlankValue()
	{
		document.getElementById("locationCodeHdn").value="";
		var langId = document.getElementById('langId').value;
		if(langId==1)
		{
			document.getElementById('cmbParentLoc_gu').value=""
		}
		else if(langId==2)
		{
			document.getElementById('cmbParentLoc').value=""
		}
	}
	function setBlankInLocationCode()
	{
		var langId = document.getElementById('langId').value;
		if(langId==1 && document.getElementById('cmbParentLoc').value=="")
		{
			document.getElementById('locationCodeHdn').value="";
			document.getElementById('cmbParentLoc_gu').value=="";
		}
		else if(langId==2 && document.getElementById('cmbParentLoc_gu').value=="")
		{
			document.getElementById('locationCodeHdn').value="";
			document.getElementById('cmbParentLoc').value=="";
		}
	}
	function showMultiLangData()
	{
		var varLocationCode = document.getElementById("locationCodeHdn").value;
		addOrUpdateRecordforLocation("showMultiLangDataResponce", "getMultiLangData&locationCode="+varLocationCode, new Array('locationCodeHdn'));
	}
	function showMultiLangDataResponce(resXmlHttp)
	{
		var xmlStr = resXmlHttp.responseText;
		var XMLDoc=xmlHttp.responseXML.documentElement;
		
		var locationName = XMLDoc.getElementsByTagName('locationName')[0].firstChild.nodeValue;
		
		var langId = document.getElementById('langId').value;
		if(langId==1)
		{	
			document.getElementById('cmbParentLoc_gu').value=locationName;
		}
		else if(langId==2)
		{
			document.getElementById('cmbParentLoc').value=locationName;
		}
	}
	
	function checkGujaratiDropdownValue(object)
	{
		if (object != null && object != undefined)
		{
			if (object.selectedIndex < 0)
				object.options[0].selected = true;
		}
	}
	
	function compareLocationName()
	{
		var multiLangVal = document.getElementById('multiLangHdn').value;
		if(multiLangVal == 'true')
		{
			var locNm = document.getElementById('txtlocName').value;
			var locNm_gu=document.getElementById('txtlocName_gu').value;
			if(locNm.toLowerCase() == locNm_gu.toLowerCase())
			{
				alert(locationAlertArray[10]);
				return false;
			}
		}
		return true;
	}
	
	function createArrayToPassValue()
	{
		 
		var flag=document.getElementById('flag').value;
		var langID = document.getElementById('langId').value;
		var multiLangVal = document.getElementById('multiLangHdn').value;
		
		if(flag=="add" && multiLangVal == 'false')
		{
			locationArray =new Array('flag','officeCode','txtlocName','multiLangHdn');
		}
		else if(flag=="add" && multiLangVal == 'true')
		{
			locationArray = new Array('flag','officeCode','txtLocName','txtLocName_gu','multiLangHdn');
		}
		else if(flag=="edit" && multiLangVal == 'false')
		{
			locationArray = new Array("flag","officeCode","txtLocName","locationCode","multiLangHdn");
		}
		else if(flag=="edit" && multiLangVal == 'true')	
		{
			locationArray = new Array("flag","officeCode","txtLocName","locationCode","txtLocName_gu","multiLangHdn");
		}
	}
	
	function processResponseForVaildateOfficeCode()
	{
		if (xmlHttp.readyState == 4) 
		{
			if (xmlHttp.status == 200) 
			{
				var xmlStr = xmlHttp.responseText;
				var XMLDoc=getDOMFromXML(xmlStr);  
				var validateValue = XMLDoc.getElementsByTagName('locCodeFlag');
				var validateLocValue = XMLDoc.getElementsByTagName('locNameFlag');
				var validateLocFlag =validateLocValue[0].childNodes[0].text;
				var validateFlag = validateValue[0].childNodes[0].text;
				var langValue = XMLDoc.getElementsByTagName('Language');
				if(validateFlag == 'true' && validateLocFlag == 'true')
				{
					var langId = document.getElementById('langId').value;
					
					if (langId == 1)
					{
						document.getElementById('txtAddressOne_gu').disabled=false;
						document.getElementById('txtAddressTwo_gu').disabled=false;
						document.getElementById('txtPincode_gu').disabled=false;
					}
					else
					{
						document.getElementById('txtAddressOne').disabled=false;
						document.getElementById('txtAddressTwo').disabled=false;
						document.getElementById('txtPincode').disabled=false;
					}
					
					var asgnBrnchObj = document.getElementById('assignedBranch');
					for(var i=0; i<asgnBrnchObj.length; i++)
					{
						asgnBrnchObj.options[i].selected = true;
					}
						
					document.frmAdminCrtLocation.action="hrms.htm?actionFlag=SubmitAdminLocationData";
					document.frmAdminCrtLocation.submit();
				}
				else
				{
					
					var temp;
					var temp3;
					if (validateLocFlag == 'false')
					{
						var langFlag = langValue[0].childNodes[0].text;
						if(langFlag == 'en')
						{
							temp=locationAlertArray[1];
							temp3=locationAlertArray[2];
						}
						else if(langFlag == 'gu') 
						{
							temp=locationAlertArray[3];
							temp3=locationAlertArray[4];
							
						}
						else if(langFlag == 'both')
						{
							temp=locationAlertArray[5];
							temp3=locationAlertArray[6];
						}
						var nameExistAlert=locationAlertArray[7]+' '+temp3+' '+locationAlertArray[8]+' '+temp;
						if(multiLang=='false')
						{
							nameExistAlert=locationAlertArray[7]+locationAlertArray[8];
						}
						hideProgressbar();
						alert(nameExistAlert);
						return false;
					}
					else if(validateFlag == 'false')
					{
						hideProgressbar();
						alert(locationAlertArray[0]);
						return false;
					}
				}
			}
		}
	}
	function setOtherLocTypeForUpdate(object)
	{
		if (object != null && object != undefined)
		{
			if (object.selectedIndex < 0)
				object.value='Other_LocType';
		}
	}
	

	function moveBranch(typeOfMove)
	{
		var source = "";
		var destination = "";
		
		if (typeOfMove == 'Next')
		{
			source = "unassignedBranch";
			destination = "assignedBranch";
			
			if (document.getElementById(destination) != null && document.getElementById(destination).length == 1)
			{
				var str = new String(document.getElementById(destination).options[0].value).trim();
				if (str == '')
				{
					document.getElementById(destination).length = 0;
				}
			}
		}
		else
		{
			source = "assignedBranch";
			destination = "unassignedBranch";
		}
		
		var x=document.getElementById(source);
		
		for(var i=0;i<x.length;i++)
		{		
			if(x.options[i].selected)
			{
				var y=document.createElement('option');
				y.text=x.options[i].text;
				y.value=x.value;
				x.remove(i);
				var z=document.getElementById(destination);
				try
		   		{
		    			z.add(y,null);
		   		}
		 		catch(ex)
		   		{
		   			 z.add(y); 
		   		}
				i=i-1;
			}
		}
	 }
	
	function getAsgnAndUnasgnBrnch()
	{
		if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			if(encXML!="error")
			{
				var responseArray = encXML.split("~");
				if(responseArray[0] != 'error')
				{
					asgnBranchList = responseArray[0];
				}
				
				if(responseArray[1] != 'error')
				{
					unasgnBranchList = responseArray[1];
				}
				resetbrnchLocMapping();
			}	
		}
	}
	
	function resetbrnchLocMapping()
	{
		var asgnBrnchObj = document.getElementById("assignedBranch");
		asgnBrnchObj.length=0;
		if(asgnBranchList != "")
		{
			var assignBranch = asgnBranchList.split("$");
			for (var i=0; i < assignBranch.length;++i)
			{
				var keyValPair=assignBranch[i].split("/");
				addOption(asgnBrnchObj, keyValPair[1], keyValPair[0]);
			}
		}
		
		var unAsgnBrnchObj = document.getElementById("unassignedBranch");
		unAsgnBrnchObj.length=0;
		if(unasgnBranchList != "")
		{
			var unassignBranch = unasgnBranchList.split("$");
			for (var i=0; i < unassignBranch.length;++i)
			{
				var keyValPair=unassignBranch[i].split("/");
				addOption(unAsgnBrnchObj, keyValPair[1], keyValPair[0]);
			}
		}
	}
//added by uzma
function displayText(){
	var langId = document.getElementById('langId').value;
	if(langId==1)
	{
		if(document.getElementById('cmbParentLoc').value==""){
			document.getElementById('cmbParentLoc').value=locationAlertArray[11];
			document.getElementById('cmbParentLoc').style.color='#808080';
		}
	}
	else if(langId==2)
	{
		if(document.getElementById('cmbParentLoc_gu').value==""){
			document.getElementById('cmbParentLoc_gu').value=locationAlertArray[11];
			document.getElementById('cmbParentLoc_gu').style.color='#808080';
		}
	}
	
}
function removeText(){
	var langId = document.getElementById('langId').value;
	if(langId==1)
	{
		if(document.getElementById('cmbParentLoc').value==locationAlertArray[11]){
			document.getElementById('cmbParentLoc').value="";
			document.getElementById('cmbParentLoc').style.color='black';
		}
	}
	else if(langId==2)
	{
		if(document.getElementById('cmbParentLoc_gu').value==locationAlertArray[11]){
			document.getElementById('cmbParentLoc_gu').value="";
			document.getElementById('cmbParentLoc_gu').style.color='black';
		}
	}
}
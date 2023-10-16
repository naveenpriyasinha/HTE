function saveEmpChangeNameReq()
{
	var fieldArray = new Array('empLastNameTxt','empFirstNameTxt','empLastNameGujTxt','empFirstNameGujTxt','GazetteNum','gazetteDate','gazetteEfeStartDate','issuingAuthoTxt');
	statusValidation = validateSpecificFormFields(fieldArray);
	if(statusValidation)
	{
		if(checkNameChangedOrNot())
		{
			document.empChangeNamefrm.action="hrms.htm?actionFlag=saveEmpChangeNameReq";
			document.empChangeNamefrm.submit();
		}
		else
		{
			alert(empChangeNameAlertArray[2]);
			setFocusSelection("SalutationTxt");
		}
	}
}
function checkNameChangedOrNot()
{
	var empNewNameArr = new Array();
	
	empNewNameArr[0] = document.empChangeNamefrm.SalutationTxt.value;
	empNewNameArr[1] = document.empChangeNamefrm.empLastNameTxt.value;
	empNewNameArr[2] = document.empChangeNamefrm.empFirstNameTxt.value;
	empNewNameArr[3] = document.empChangeNamefrm.empMiddleNameTxt.value;
	
	empNewNameArr[4] = document.empChangeNamefrm.SalutationTxt.value;		
	empNewNameArr[5] = document.empChangeNamefrm.empLastNameGujTxt.value;
	empNewNameArr[6] = document.empChangeNamefrm.empFirstNameGujTxt.value;
	empNewNameArr[7] = document.empChangeNamefrm.empMiddleNameGujTxt.value;
	
	if((empNewNameArr[0]==empOldNameArr[0] || empNewNameArr[4]==empOldNameArr[4]) && empNewNameArr[1]==empOldNameArr[1] && empNewNameArr[2]==empOldNameArr[2] && empNewNameArr[3]==empOldNameArr[3] && empNewNameArr[5]==empOldNameArr[5] && empNewNameArr[6]==empOldNameArr[6] && empNewNameArr[7]==empOldNameArr[7])
		return false;
	else
		return true;
}
function checkDate(flagType)
{
	if(document.empChangeNamefrm.gazetteDate.value!=""&& document.empChangeNamefrm.gazetteEfeStartDate.value!="" && compareDate(document.empChangeNamefrm.gazetteDate.value,document.empChangeNamefrm.gazetteEfeStartDate.value) < 0)
	{
		if(flagType=='G')
		{
			alert(empChangeNameAlertArray[0]);
			//alert("Gazette date should be less than gazette effecative start date");
			document.empChangeNamefrm.gazetteDate.value="";
		}
		else if(flagType=='E')
		{
			alert(empChangeNameAlertArray[1]);
			//alert("Gazette effecative start date should be greater than Gazette date");
			document.empChangeNamefrm.gazetteEfeStartDate.value="";
		}
	}
}
function closeWindow()
{
	document.empChangeNamefrm.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	document.empChangeNamefrm.submit();
}
function empChangeOnLoadFun()
{
		initializetabcontent("maintab");
		
		if(empChangeNameFlag)
		{
			document.empChangeNamefrm.SalutationTxt.disabled=true;
			document.empChangeNamefrm.empLastNameTxt.disabled=true;
			document.empChangeNamefrm.empFirstNameTxt.disabled=true;
			document.empChangeNamefrm.empMiddleNameTxt.disabled=true;
			document.empChangeNamefrm.empLastNameGujTxt.disabled=true;
			document.empChangeNamefrm.empFirstNameGujTxt.disabled=true;
			document.empChangeNamefrm.empMiddleNameGujTxt.disabled=true;
			document.empChangeNamefrm.GazetteNum.disabled=true;
			document.empChangeNamefrm.issuingAuthoTxt.disabled=true;
			document.empChangeNamefrm.gazetteDateTxt.disabled=true;
			document.empChangeNamefrm.gazetteEfeStartDateTxt.disabled=true;
			
			document.getElementById('target_uploadattachmentLink').style.display='none';
			document.getElementById('formTable1attachmentLink').firstChild.firstChild.style.display='none';
		}
		else
		{
			document.empChangeNamefrm.gazetteDate.readOnly=true;
			document.empChangeNamefrm.gazetteEfeStartDate.readOnly=true;
			
			document.empChangeNamefrm.SalutationTxt.value=empOldNameArr[0];
			
			document.empChangeNamefrm.empLastNameTxt.value=empOldNameArr[1];
			document.empChangeNamefrm.empFirstNameTxt.value=empOldNameArr[2];
			document.empChangeNamefrm.empMiddleNameTxt.value=empOldNameArr[3];
			
			document.empChangeNamefrm.empLastNameGujTxt.value=empOldNameArr[5];
			document.empChangeNamefrm.empFirstNameGujTxt.value=empOldNameArr[6];
			document.empChangeNamefrm.empMiddleNameGujTxt.value=empOldNameArr[7];
		}
}
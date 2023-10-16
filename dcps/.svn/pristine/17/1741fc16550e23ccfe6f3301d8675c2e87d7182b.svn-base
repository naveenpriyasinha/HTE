function clearAll(){
	document.getElementById("txtEmpName").value="";
	document.getElementById("txtGpfAccNo").value="";
	document.getElementById("txtOffName").value="";
	document.getElementById("txtCurrentBal").value="";
	document.getElementById("txtEffectFromDate").value="";
	document.getElementById("txtApplicableToDate").value="";	
	document.getElementById("txtCurrentInt").value="";
	document.getElementById("txtAmtAfterInterest").value="";
}
function clearAllForOffice(){
	document.getElementById("cmbOfficeName").value="";
	document.getElementById("cmbGroup").value = "";			
	//document.getElementById("txtEffectFromDateOffice").value="";
	//document.getElementById("txtApplicableToDateOffice").value="";
	document.getElementById("cmbYearForAllEmp").value = "-1";
}
function popUpEmpDtls(){
	
	var sevaarthID = document.getElementById("txtSevaarthId").value;
	if(sevaarthID!=""){
	var uri="ifms.htm?actionFlag=popUpEmpDtlsForInterestCal";
	var url = "&sevaarthID=" + sevaarthID;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getEmpDtlsMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	}
	else{
		clearAll();
	}
		
}
function getEmpDtlsMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].text;
	if(lBlFlag == "true"){
		var lStrEmpName = XmlHiddenValues[0].childNodes[1].text;
		var lStrGpfAccNo = XmlHiddenValues[0].childNodes[2].text;
		var lStrOfficeName = XmlHiddenValues[0].childNodes[3].text;
		var lStrCurrBal = XmlHiddenValues[0].childNodes[4].text;
		var lStrCurrInterestRate = XmlHiddenValues[0].childNodes[5].text;
		
		if(lStrEmpName == ""){
			alert("Invalid SevaarthId!");
			document.getElementById('txtSevaarthId').value = "";
		}
		document.getElementById("txtEmpName").value=lStrEmpName;
		document.getElementById("txtGpfAccNo").value=lStrGpfAccNo;
		document.getElementById("txtOffName").value=lStrOfficeName;
		document.getElementById("txtCurrentBal").value=lStrCurrBal;
		document.getElementById("txtCurrentInt").value=lStrCurrInterestRate;
		if(parseInt(lStrCurrInterestRate) == 0){
			alert("Interest Rate not configured for the employee.");
		}
	}
}


function calculateInterest(){

	var effectFromDate = document.getElementById("txtEffectFromDate").value;
	var appliToDate = document.getElementById("txtApplicableToDate").value;
	if (!chkEmpty(document.getElementById("txtEffectFromDate"), "Effect From Date") ||
			!chkEmpty(document.getElementById("txtApplicableToDate"), "Applicable To Date"))
	{
		return false;
	}
	var gpfAccNo = document.getElementById("txtGpfAccNo").value;
	var uri="ifms.htm?actionFlag=popUpInterestRate";
	var url = "&effectFromDate="+effectFromDate+"&gpfAccNo="+gpfAccNo;
	var myAjax = new Ajax.Request(uri,
    {
        method: 'post',
        asynchronous: false,
        parameters:url,
        onSuccess: function(myAjax) {
				getCalculatedInterest(myAjax);
		},
        onFailure: function(){ alert('Something went wrong...');} 
    } );
}
function getCalculatedInterest(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lStrInterestRate = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrInterestAmount = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	if(parseInt(lStrInterestRate) == 0){
		alert("Interest Rate is 0. Are you sure,you want to proceed?");
	}
	var currentBal = document.getElementById("txtCurrentBal").value;
	document.getElementById("txtAmtAfterInterest").value = Math.round(Number(currentBal) + Number(lStrInterestAmount));
	document.getElementById("hidInterestAmount").value = Math.round(Number(lStrInterestAmount));
		
	//var effectFromDate = document.getElementById("txtEffectFromDate").value;
	//var appliToDate = document.getElementById("txtApplicableToDate").value;		
	//var currentBal = document.getElementById("txtCurrentBal").value;	
	//var interestRate = lStrInterestRate;

	//var lArrEffectFromDate = effectFromDate.split("/");	
	//var date1 = new Date(lArrEffectFromDate[1] + "/" + lArrEffectFromDate[0] + "/" + lArrEffectFromDate[2]);
	//var lArrAppliToDate = appliToDate.split("/");	
	//var date2 = new Date(lArrAppliToDate[1] + "/" + lArrAppliToDate[0] + "/" + lArrAppliToDate[2]);
	
	//var ONE_DAY = 1000 * 60 * 60 * 24;
    //var date1_ms = date1.getTime();
    //var date2_ms = date2.getTime();
    //var difference_ms = Math.abs(date1_ms - date2_ms);
    //var days= Math.round(difference_ms/ONE_DAY)+1;
    //var year = (Number(days))/365;
   	//var amount = (Number(currentBal)*Number(interestRate)*Number(year))/100;
	//document.getElementById("txtAmtAfterInterest").value = (Number(currentBal)+Number(amount)).toFixed(2);
		
}
function validateEmpData(){
	if (!chkEmpty(document.getElementById("txtSevaarthId"), "Sevaarth Id")
			|| !chkEmpty(document.getElementById("txtEffectFromDate"), "Effect From Date") ||
			!chkEmpty(document.getElementById("txtApplicableToDate"), "Applicable To Date"))			
	{
		return false;
	}
	return true;
	}
function validateOfficeData(){
if (!chkEmpty(document.getElementById("cmbOfficeName"), "Office Name")
		|| !chkEmpty(document.getElementById("cmbGroup"),"Group")
		|| !chkEmpty(document.getElementById("cmbYearForAllEmp"),"Financial Year")) 
{
	return false;
}

var curYear = document.getElementById("hidFinYear").value;
var selectedYear = document.getElementById("cmbYearForAllEmp").value;
selectedYear = selectedYear.substring(0,4);

if(curYear < selectedYear){
	alert("Can not calculate interest for future date");
	return false;
}

return true;

}
function getEmpDtlsUsingOfficeName(){
		
	if(!validateOfficeData()){
		return false;
	}
	var interestCal = document.getElementById("txtCurrentOfficeInt").value;
	if(parseInt(interestCal) == 0 || interestCal==""){
		alert("Interest Rate is 0. Are you sure,you want to proceed?");
	}
	var officeId = document.getElementById("cmbOfficeName").value;
	var groupId = document.getElementById("cmbGroup").value;
	//var effectFromDate = document.getElementById("txtEffectFromDateOffice").value;
	//var applicableToDate = document.getElementById("txtApplicableToDateOffice").value;
	var financialYear = document.getElementById("cmbYearForAllEmp").value;
	
	/*var uri="ifms.htm?actionFlag=popUpEmpDtlsUsingOfficeName";
	var url = "&officeId=" + officeId+"&groupId=" + groupId+"&financialYear=" + financialYear;
	
	document.GPFInterestCalc.action = uri+url ;
	document.GPFInterestCalc.submit();*/
	
	var uri = 'ifms.htm?actionFlag=popUpEmpDtlsUsingOfficeName';
	var url = "&officeId=" + officeId+"&groupId=" + groupId+"&financialYear=" + financialYear;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	getInterestCalcMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
	return true;
	
}

function getInterestCalcMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		
	if(lblSaveFlag == "schedule"){
		alert("Schedule Generation Of Some Month Of Selected Financial Year Is Pending");
	}
}	

function validateInterestDate(){
	var effectFromDate = document.getElementById("txtEffectFromDate").value;
	var lArrEffectFromDate = effectFromDate.split("/");	
	var day = lArrEffectFromDate[0];
	var month = lArrEffectFromDate[1];
	if(effectFromDate!=""){
		if(day!="01"){
			alert('From Date should be start date of a Financial year');	
			 document.getElementById("txtEffectFromDate").value="";	
		}
		else if (month!="04"){
			alert('From Date should be start date of a Financial year');
			document.getElementById("txtEffectFromDate").value="";
		}
	}
	compareFromDate();
}
function validateInterestDateForOffice(){
	var effectFromDate = document.getElementById("txtEffectFromDateOffice").value;
	var appliToDate = document.getElementById("txtApplicableToDateOffice").value;
	if(effectFromDate!=""){
		var lArrEffectFromDate = effectFromDate.split("/");			
		var effectDay = lArrEffectFromDate[0];
		var effectMonth = lArrEffectFromDate[1];
		if(effectDay!="01"){
			alert('From Date should be start date of a Financial year');	
			document.getElementById("txtEffectFromDateOffice").value="";	
		}
		else if (effectMonth!="04"){
			alert('From Date should be start date of a Financial year');
			document.getElementById("txtEffectFromDateOffice").value="";
		}
	}
	if(appliToDate!=""){
		var lArrAppliToDate = appliToDate.split("/");
		var appliDay = lArrAppliToDate[0];
		var appliMonth = lArrAppliToDate[1];
		if(appliDay!="31"){
			alert('To Date should be end date of a Financial year');	
			document.getElementById("txtApplicableToDateOffice").value="";	
		}
		else if (appliMonth!="03"){
			alert('To Date should be end date of a Financial year');
			document.getElementById("txtApplicableToDateOffice").value="";
		}
	}
	compareDatesForOffice();
}
function approveInterestRateCase()
{
	var url="";
	if(document.getElementById("tblEmpDtls").style.display==''&&document.getElementById("tblOfficeDtls").style.display==''){
		alert('Please Enter Sevaarth ID or select Office Name');
		return false;		
	}
//	else if(document.getElementById("tblEmpDtls").style.display=='inline'){
	else if(document.getElementById("rdoForSingleEmp").checked){
		if(!validateEmpData()|| !chkEmpty(document.getElementById("txtAmtAfterInterest"), "Amount After Interest")){
			return false;
		}		
		
		var gpfAccNo = document.getElementById("txtGpfAccNo").value;
		var amtAfterInterest = document.getElementById("txtAmtAfterInterest").value;
		var effectFromDate = document.getElementById("txtEffectFromDate").value;
		//var financialYear = document.getElementById("cmbYearForEmp").value;
		var currentInt = document.getElementById("txtCurrentInt").value;
		var interestAmount = document.getElementById("hidInterestAmount").value;
		url=url+"&empGpfAccNo="+gpfAccNo+"&amtAfterInterest="+amtAfterInterest+"&effectFromDate="+effectFromDate+"&currentInt="+currentInt+"&reqType=single"+ "&interestAmount="+interestAmount;
	}
//	else if(document.getElementById("tblOfficeDtls").style.display=='inline'){
	else if(document.getElementById("rdoForAllEmp").checked){

		if(!chkEmpty(document.getElementById("cmbYearForAllEmp"), "Financial Year"))
		{
			return false;
		}
		var totalSelectedEmployees= document.getElementById("totalCount").value;
		var flag=0;
		var gpfAccNo="";
		var yearlyId="";
		var amtAfterInterest="";
		var arrChkBox = document.getElementsByName("chkReq");
		var currrentRecord = "";
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked == true)
			{
				currrentRecord = (arrChkBox[i].id.split("_"))[1];
				gpfAccNo= gpfAccNo + document.getElementById("hdnGpfAccNo"+currrentRecord).value + "~";
				yearlyId= yearlyId + document.getElementById("chkReq_"+currrentRecord).value + "~";
				amtAfterInterest=amtAfterInterest+document.getElementById("hidAmountAfterInt"+currrentRecord).value + "~";
				flag = 1;			
			}
		}
		var interestRate = Math.round(Number(document.getElementById("txtCurrentOfficeInt").value));
		//var fromDate = document.getElementById("txtEffectFromDateOffice").value;
		var financialYear = document.getElementById("cmbYearForAllEmp").value;
		if(flag == 1){
			url =url+ "&interestRate="+interestRate+"&gpfAccNo="+gpfAccNo+"&financialYear="+financialYear+"&reqType=multiple"+"&yearlyId="+yearlyId+"&amtAfterInterest="+amtAfterInterest;
		}else{
			alert("Please select an entry to Approve.");
			return false;
		}
	}
	var uri="ifms.htm?actionFlag=approveInterestCase";
	var myAjax = new Ajax.Request(uri,
					{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getApprovedMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	
}
function getApprovedMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrScheduleStatus = XmlHiddenValues[0].childNodes[1].text;
	var lStrApprove = XmlHiddenValues[0].childNodes[2].text;
	if(lBlFlag=='true' && lStrScheduleStatus =='' && lStrApprove ==''){
		alert('Interest Calculation data saved successfully');
		self.location.reload();
	}else if (lStrScheduleStatus == "pendingSchedules"){
		alert('Schedule generation for some months is pending for the selected financial year.');
	}else if (lStrApprove == "Approved"){
		alert('Schedule is already Approved');
	}
}
function enableSection(){
	var radioSelected = false;
	var selectedId;
	for(var i=0;i< document.forms[0].rdoSelectEmp.length;i++)
	{
		if(document.forms[0].rdoSelectEmp[i].checked)
		{
			radioSelected = true;
			selectedId = i;
		}
	}
	if(!radioSelected)
	{
		alert('Please Select a Radio Button');
		return;
	}
	if(selectedId == 0){
		document.getElementById("tblOfficeDtls").style.display='inline';
		document.getElementById("tblEmpDtls").style.display='none';
	}
	else{
		document.getElementById("tblEmpDtls").style.display='inline';
		document.getElementById("tblOfficeDtls").style.display='none';
	}
}
function populateInterestRate(){

	var officeId = document.getElementById("cmbOfficeName").value;
	var groupId = document.getElementById("cmbGroup").value;	
	if(officeId == "-1" || groupId == "-1"){
		return;
	}else{
		var uri="ifms.htm?actionFlag=popUpInterestRate";
		var url = "&officeId=" + officeId+"&groupId=" + groupId;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getInterestRateMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}
function getInterestRateMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lStrInterestRate = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(parseInt(lStrInterestRate) == 0){
		alert("Interest Rate data not configured for selected group.");
	}
		document.getElementById("txtCurrentOfficeInt").value=lStrInterestRate;
}
function compareFromDate(){
	var effectFromDate = document.getElementById("txtEffectFromDate").value;
	var appliToDate = document.getElementById("txtApplicableToDate").value;
	var	day = effectFromDate.split("/")[0];
	var month = effectFromDate.split("/")[1]; 
	var year = effectFromDate.split("/")[2];
	var fromDate = new Date(year, month-1, day); 
	day = appliToDate.split("/")[0];
	month = appliToDate.split("/")[1];
	year = appliToDate.split("/")[2];
	var toDate = new Date(year, month-1, day);
	if(effectFromDate != "" && appliToDate != "" && fromDate>toDate){
		alert("To Date should be grater than From Date");
		document.getElementById("txtApplicableToDate").value = "";
	}
}
function compareDatesForOffice(){
	var effectFromDate = document.getElementById("txtEffectFromDateOffice").value;
	var appliToDate = document.getElementById("txtApplicableToDateOffice").value;
	var	day = effectFromDate.split("/")[0];
	var month = effectFromDate.split("/")[1]; 
	var year = effectFromDate.split("/")[2];
	var fromDate = new Date(year, month-1, day); 
	day = appliToDate.split("/")[0];
	month = appliToDate.split("/")[1];
	year = appliToDate.split("/")[2];
	var toDate = new Date(year, month-1, day);
	if(effectFromDate != "" && appliToDate != "" && fromDate>toDate){
		alert("To Date should be grater than From Date");
		document.getElementById("txtApplicableToDateOffice").value = "";
	}
}
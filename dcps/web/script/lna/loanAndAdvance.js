function SaveRequest()
{
	var requestValue = document.getElementById("hidRequestType").value;
	if(requestValue == '800028'){
		saveComputerAdvance();
	}else if(requestValue == '800029'){
		saveHouseAdvance();		
	}else if(requestValue == '800030'){
		saveMotorAdvance();
	}
}
function saveComputerAdvance(){
	var documentCheckList = document.LNARequestProcessForm.cbDocCheckListCA;
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateComputerAdvance())
	{
		return;
	}
	var uri="ifms.htm?actionFlag=saveComputerAdvance";
	var url = "&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getSaveRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function saveHouseAdvance(){
	
	var documentCheckList;
	if(document.getElementById('ChecklistHBAForPP').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAPP;
	}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBACF;
	}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBARB;
	}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBABL;
	}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBASR;
	}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAER;
	}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAOF;
	}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBALC;
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBA;
	}
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateHouseAdvance())
	{		
		return;
	}
	
	var uri="ifms.htm?actionFlag=saveHouseAdvance";
	var url = "&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getSaveRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function saveMotorAdvance(){
	
	var documentCheckList;
	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCAHC;
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCA;
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCANew;
	}	
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateMotorcarAdvance())
	{		
		return;
	}
	var uri="ifms.htm?actionFlag=saveMotorCarAdvance";
	var url = "&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getSaveRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getSaveRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lSevaarthID = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lUserType = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var lReqType = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	if (lblSaveFlag=="true") {
		
		alert('Request saved successfully');		
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&txtSevaarthId="+lSevaarthID+"&criteria=1&requestType="+lReqType+"&userType="+lUserType+"&elementId=800025";
	}

}
function forwardRequest(flag){
	var requestValue = document.getElementById("hidRequestType").value;
	if(requestValue == '800028'){
		fwdComputerAdvance(flag);
	}else if(requestValue == '800029'){
		fwdHouseAdvance(flag);		
	}else if(requestValue == '800030'){
		fwdMotorCarAdvance(flag);
	}
}
function fwdComputerAdvance(flag){
	
	var documentCheckList = document.LNARequestProcessForm.cbDocCheckListCA;
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateComputerAdvance())
	{
		
		return false;
	}
	var uri="";	
	var url = runForm(0)+"&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck;
	
	if(flag == 1){
		uri="ifms.htm?actionFlag=forwardCAToDEOVerifier";				
	}else if(flag == 2){
		uri="ifms.htm?actionFlag=forwardCAToHO";
	}else if(flag == 3){
		if ( !chkEmpty(document.getElementById("txtSanctionedDateCA"), "Sanctioned Date")){
			return false;
		}
		uri="ifms.htm?actionFlag=forwardCAToAsstHOD";
	}else if(flag == 4){
		uri="ifms.htm?actionFlag=forwardCAToHOD";
	}else if(flag == 5){
		uri="ifms.htm?actionFlag=forwardOfflineEntryCAToHOD";
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				if(flag == 1){
					getForwardAdvanceRequestMsg(myAjax);
				}else if(flag == 2){
					getForwardAdvanceRequestMsgDEO(myAjax);
				}else if(flag == 3){
					getForwardAdvanceRequestMsgHO(myAjax);
				}else if(flag == 4){
					getForwardAdvanceRequestMsgHOD(myAjax);
				}else if(flag == 5 ){
					getFwdAdvanceReqMsgHODAsst(myAjax);
				}
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
}
function fwdHouseAdvance(flag){
	
	var documentCheckList;
	if(document.getElementById('ChecklistHBAForPP').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAPP;
	}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBACF;
	}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBARB;
	}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBABL;
	}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBASR;
	}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAER;
	}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAOF;
	}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBALC;
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBA;
	}
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateHouseAdvance())
	{		
		return false;
	}
	var uri="";	
	var url = runForm(0)+"&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck;
	
	if(flag == 1){
		uri="ifms.htm?actionFlag=forwardHBAToDEOVerifier";				
	}
	else if(flag == 2){
		uri="ifms.htm?actionFlag=forwardHBAToHO";
	}else if(flag == 3){
		var subType = document.getElementById("cmbHBAType").value;
		if (subType==800038){
			
		}else if (subType==800058){
			
		}else{
			if (!chkEmpty(document.getElementById("txtSanctionedDateHBA"), "Sanctioned Date"))
			{
				return false;
			}
		}
		uri="ifms.htm?actionFlag=forwardHBAToAsstHOD";
	}else if(flag == 4){
		uri="ifms.htm?actionFlag=forwardHBAToHOD";
	}else if(flag == 5){
		uri="ifms.htm?actionFlag=forwardOfflineEntryHBAToHOD";
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				if(flag == 1){
					getForwardAdvanceRequestMsg(myAjax);
				}else if(flag == 2){
					getForwardAdvanceRequestMsgDEO(myAjax);
				}else if(flag == 3){
					getForwardAdvanceRequestMsgHO(myAjax);
				}else if(flag == 4){
					getForwardAdvanceRequestMsgHOD(myAjax);
				}else if(flag == 5 ){
					getFwdAdvanceReqMsgHODAsst(myAjax);
				}
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );

	
}
function fwdMotorCarAdvance(flag){
	
	var documentCheckList;
	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCAHC;
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCA;
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCANew;
	}	
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateMotorcarAdvance())
	{
		
		return false;
	}
	var uri="";
	var url = runForm(0)+"&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck;
	
	if(flag == 1){
		uri="ifms.htm?actionFlag=forwardMCAToDEOVerifier";				
	}else if(flag == 2){
		uri="ifms.htm?actionFlag=forwardMCAToHO";
	}else if(flag == 3){
		if ( !chkEmpty(document.getElementById("txtSanctionedDateMCA"), "Sanctioned Date")){
			return false;
		}
		uri="ifms.htm?actionFlag=forwardMCAToAsstHOD";
	}else if(flag == 4){
		uri="ifms.htm?actionFlag=forwardMCAToHOD";
	}else if(flag == 5){
		uri="ifms.htm?actionFlag=forwardOfflineEntryMCAToHOD";
	}
	var myAjax = new Ajax.Request(uri,
		       {
        method: 'post',
        asynchronous: false,
        parameters:url,
        onSuccess: function(myAjax) {
		if(flag == 1){
			getForwardAdvanceRequestMsg(myAjax);
		}else if(flag == 2){
			getForwardAdvanceRequestMsgDEO(myAjax);
		}else if(flag == 3){
			getForwardAdvanceRequestMsgHO(myAjax);
		}else if(flag == 4){
			getForwardAdvanceRequestMsgHOD(myAjax);
		}else if(flag == 5 ){
			getFwdAdvanceReqMsgHODAsst(myAjax);
		}
		},
        onFailure: function(){ alert('Something went wrong...');} 
          } );
	
}
function getForwardAdvanceRequestMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrTransId = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrOrgEmpId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var currDate = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	if (lblForwardFlag=="true") {
		
		alert('Transaction ID '+lStrTransId+' has been generated successfully against the Sevaarth ID '+lStrOrgEmpId+' for the Request Dated '+currDate);		
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&elementId=800011&userType=DEO";
	}
}
function getFwdAdvanceReqMsgHODAsst(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrTransId = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrOrgEmpId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var currDate = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	if (lblForwardFlag=="true") {
		
		alert('Transaction ID '+lStrTransId+' has been generated successfully against the Sevaarth ID '+lStrOrgEmpId+' for the Request Dated '+currDate);		
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&elementId=800025&userType=HODASST2";
	}
}
function getForwardAdvanceRequestMsgDEO(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag=="true") {
		
		alert('Request has been forwarded to Head Officer');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800021&userType=DEOAPP";
	}

}
function getForwardAdvanceRequestMsgHO(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag=="true") {
		
		alert('Request has been forwarded to Assistant Head Of Department');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800022&userType=HO";
	}

}
function getForwardAdvanceRequestMsgHOD(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag=="true") {
		
		alert('Request has been forwarded to Head Of Department');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800023&userType=HODASST";
	}

}

function approveRequest(){
	var requestValue = document.getElementById("hidRequestType").value;
	if(requestValue == '800028'){
		approveComputerAdvance();
	}else if(requestValue == '800029' ){
		approveHouseAdvance();
	}else if(requestValue == '800030'){
		approveMotorCarAdvance();
	}
}
function approveComputerAdvance(){
	
	if(!validateComputerAdvance())
	{		
		return false;
	}
	if ( !chkEmpty(document.getElementById("txtSanctionedDateCA"), "Sanctioned Date")) 
	{	
					
		return false;
	}
	var uri = "ifms.htm?actionFlag=approveComputerAdvance";
	var url = runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApprovedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function approveHouseAdvance(){
	
	if(!validateHouseAdvance())
	{		
		return false;
	}
	var Disbursement2 = document.getElementById("txtDisbursement2").value;
	var Disbursement3 = document.getElementById("txtDisbursement3").value;
	var Disbursement4 = document.getElementById("txtDisbursement4").value;
	
	var subType = document.getElementById("cmbHBAType").value;
	if (subType==800038){
		if(!chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")){
			return false;
		}
		if(Disbursement2 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")){
				return false;
			}
		}
		if(Disbursement3 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date"))
			{
				return false;
			}
		} 
		
	}else if (subType==800058){
		if(!chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")){
			return false;
		}		
		if(Disbursement2 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")){
				return false;
			}
		}
		if(Disbursement3 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date")){
				return false;
			}
		}
		if(Disbursement4 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate4"), "Sanction Date"))
			{
				return false;
			}
		} 
	}else{
		if (!chkEmpty(document.getElementById("txtSanctionedDateHBA"), "Sanctioned Date")){
			return false;
		}
	}
	
	var uri = "ifms.htm?actionFlag=approveHouseAdvance";
	var url = runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApprovedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function approveMotorCarAdvance(){
	
	if(!validateMotorcarAdvance())
	{		
		return false;
	}
	if (!chkEmpty(document.getElementById("txtSanctionedDateMCA"), "Sanctioned Date")) 
	{		
		return false;
	}
	var uri = "ifms.htm?actionFlag=approveMotorCarAdvance";
	var url = runForm(0);
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApprovedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getApprovedRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblForwardFlag=="true") {
		
		alert('Request has been approved');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800024&userType=HOD";
	}
}
function rejectRequest(flag){
	var requestValue = document.getElementById("hidRequestType").value;
	if(requestValue == '800028'){
		rejectComputerAdvance(flag);
	}else if(requestValue == '800029' ){
		rejectHouseAdvance(flag);
	}else if(requestValue == '800030'){
		rejectMotorCarAdvance(flag);
	}
}
function rejectComputerAdvance(flag){	
	
	var ComAdvanceId = document.getElementById("hidComAdvanceId").value;
	var uri = "";
	var url = "";
	if(flag == 1){
		var DEORemarks = document.getElementById("txtApproverRemarksCA").value;	
		if(DEORemarks==""){
			
			alert("Verifier Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectComAdvanceByDEOApprover";
		url = "ComAdvanceId=" + ComAdvanceId + "&DEORemarks=" + DEORemarks;
	}else if(flag == 2){
		var HORemarks = document.getElementById("txtHORemarks").value;
		if(HORemarks==""){
			
			alert("HO Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectComAdvanceByHO";
		url = "ComAdvanceId=" + ComAdvanceId	+ "&HORemarks=" + HORemarks;
	}else if(flag == 3){
		uri = "ifms.htm?actionFlag=rejectComAdvanceByHO";
		url = "ComAdvanceId=" + ComAdvanceId	+ "&HORemarks=" + HORemarks;
	}else if(flag == 4){
		var HORemarks = document.getElementById("txtHORemarks").value;
		if(HORemarks==""){
			
			alert("HOD Remarks cannot be empty");
			document.getElementById("txtHORemarks").focus();
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectComAdvanceByHO";
		url = "ComAdvanceId=" + ComAdvanceId	+ "&HORemarks=" + HORemarks;
	}

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getRejectedRequestMsg(myAjax,flag);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function rejectHouseAdvance(flag){
	
	var HouseAdvanceId = document.getElementById("hidHouseAdvanceId").value;
	var uri = "";
	var url = "";
	if(flag == 1){
		
		var DEORemarks = document.getElementById("txtApproverRemarksHBA").value;		
		if(DEORemarks==""){
			
			alert("Verifier Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectHBAByDEOApprover";
		url = "HouseAdvanceId=" + HouseAdvanceId + "&DEORemarks=" + DEORemarks;
	}
	else if(flag == 2){
		
		var HORemarks = document.getElementById("txtHORemarks").value;
		if(HORemarks==""){
			
			alert("HO Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectHBAByHO";
		url = "HouseAdvanceId=" + HouseAdvanceId	+ "&HORemarks=" + HORemarks;
	}else if(flag == 3){
		uri = "ifms.htm?actionFlag=rejectHBAByHO";
		url = "HouseAdvanceId=" + HouseAdvanceId	+ "&HORemarks=" + HORemarks;
	}else if(flag == 4){
		var HORemarks = document.getElementById("txtHORemarks").value;
		if(HORemarks==""){
			
			alert("HOD Remarks cannot be empty");
			document.getElementById("txtHORemarks").focus();
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectHBAByHO";
		url = "HouseAdvanceId=" + HouseAdvanceId	+ "&HORemarks=" + HORemarks;
	}

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getRejectedRequestMsg(myAjax,flag);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function rejectMotorCarAdvance(flag){	
	
	var MotorAdvanceId = document.getElementById("hidMotorAdvanceId").value;
	var uri = "";
	var url = "";
	if(flag == 1){
		
		var DEORemarks = document.getElementById("txtApproverRemarksMCA").value;
		if(DEORemarks==""){
			
			alert("Verifier Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectMCAByDEOApprover";
		url = "MotorAdvanceId=" + MotorAdvanceId + "&DEORemarks=" + DEORemarks;
	}
	else if(flag == 2){
		
		var HORemarks = document.getElementById("txtHORemarks").value;
		if(HORemarks==""){
			
			alert("HO Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectMCAByHO";
		url = "MotorAdvanceId=" + MotorAdvanceId	+ "&HORemarks=" + HORemarks;
	}else if(flag == 3){		
		uri = "ifms.htm?actionFlag=rejectMCAByHO";
		url = "MotorAdvanceId=" + MotorAdvanceId	+ "&HORemarks=" + HORemarks;
	}else if(flag == 4){
		var HORemarks = document.getElementById("txtHORemarks").value;
		if(HORemarks==""){
			
			alert("HOD Remarks cannot be empty");
			document.getElementById("txtHORemarks").focus();
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectMCAByHO";
		url = "MotorAdvanceId=" + MotorAdvanceId	+ "&HORemarks=" + HORemarks;
	}

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getRejectedRequestMsg(myAjax,flag);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getRejectedRequestMsg(myAjax,flag)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblForwardFlag=="true") {
		alert('Request has been rejected');	
		if(flag == 1){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800021&userType=DEOAPP";
		}else if(flag == 2){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800022&userType=HO";
		}else if(flag == 3){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800023&userType=HODASST";
		}else if(flag == 4){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800024&userType=HOD";
		}
	}

}
function validateComputerAdvance() {	
	if (!chkEmpty(document.getElementById("cmbComputerSubType"), "Select Sub Type")
			|| !chkEmpty(document.getElementById("txtAppDateCA"), "Physical Application Received Date")
			|| !chkEmpty(document.getElementById("txtReqAmountCA"),"Requested Amount")				
			|| !chkEmpty(document.getElementById("txtActualCostCA"),"Actual Cost")
			|| !chkEmpty(document.getElementById("txtSancAmountCA"),"Sanction Amount")			
			|| !chkEmpty(document.getElementById("txtSancInstallmentsCA"), "Sanctioned No. of Installments")
			|| !chkEmpty(document.getElementById("txtInstallmentAmountCA"), "Installment Amount")) 
	{
		return false;
	}
	var documentCheckList = document.LNARequestProcessForm.cbDocCheckListCA;
	if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
			|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked )
	{
		alert("Please select Mandatory Checklists");
		return false;
	}
	if(document.getElementById("txtOddInstallmentAmtCA").value!=""){
		if (!chkEmpty(document.getElementById("cmbOddInstallNoCA"), "Odd Installment No"))
		{
			return false;
		}
	}
	/*var attachmentTable = document.getElementById("myTableProofCA");
	
	if(attachmentTable.rows.length < 2){
		alert("Please attach atleast one Proof");
		return false;
	}*/

	return true;
}
function validateHouseAdvance() {	
	if(document.getElementById("cmbHBAType").value=="-1"){
		alert("Please select sub type");
		document.getElementById("cmbHBAType").focus();
		return false;
	}
	var subType = document.getElementById("cmbHBAType").value;
	if (!chkEmpty(document.getElementById("cmbPayCommissionHBA"), "Pay Commission GR")
			|| !chkEmpty(document.getElementById("cmbPayScaleHBA"), "Pay scale")
			|| !chkEmpty(document.getElementById("txtBasicPay"), "Basic Pay")
			|| !chkEmpty(document.getElementById("txtAppDateHBA"), "Physical Application Received Date")
			|| !chkEmpty(document.getElementById("txtReqAmountHBA"),"Requested Amount")) 
	{
		return false;
	}
	if(document.getElementById("rdoSuspended").checked){
		if (!chkEmpty(document.getElementById("txtGuarantor1"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtEmpName1"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtBasicPay1"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtDdoCode1"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtDdoName1"),"Guarantor Details")
				|| !chkEmpty(document.getElementById("txtGuarantor2"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtEmpName2"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtBasicPay2"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtDdoCode2"), "Guarantor Details")
				|| !chkEmpty(document.getElementById("txtDdoName2"),"Guarantor Details")) 
		{
			return false;
		}
	}
	if (subType==800038){
		if(!chkEmpty(document.getElementById("txtSanctionedAmountHBA2"), "Sanction Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement1"), "Disbursement1 Amount")
				|| !chkEmpty(document.getElementById("txtSancPrinInstallHBA2"), "Sanctioned No. of Principal Installments"))
			/*|| !chkEmpty(document.getElementById("txtDisbursement2"), "Disbursement2 Amount")
			|| !chkEmpty(document.getElementById("txtDisbursement3"), "Disbursement3 Amount")				
			|| !chkEmpty(document.getElementById("txtSancInterInstallHBA2"), "Sanctioned No. of Interest Installments")
			|| !chkEmpty(document.getElementById("txtInterestAmountHBA2"), "Interest Amount")
			|| !chkEmpty(document.getElementById("txtPrinInstallmentAmountHBA"), "Principal Installment Amount per month")
			|| !chkEmpty(document.getElementById("txtInterInstallmentAmountHBA2"), "Interest Installment Amount per month")
			|| !chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")
			|| !chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")
			|| !chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date")*/
		{
				return false;
		}
		if(document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoHBA2"), "Odd Principal Installment No"))
			{
				return false;
			}
		}
		if(document.getElementById("txtOddInterestInstallmentAmtHBA2").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoHBA2"), "Odd Interest Installment No"))
			{
				return false;
			}
		}
	}else if (subType==800058){
		if(!chkEmpty(document.getElementById("txtSanctionedAmountHBA2"), "Sanction Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement1"), "Disbursement1 Amount")
				|| !chkEmpty(document.getElementById("txtSancPrinInstallHBA2"), "Sanctioned No. of Principal Installments"))
				/*|| !chkEmpty(document.getElementById("txtDisbursement2"), "Disbursement2 Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement3"), "Disbursement3 Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement4"), "Disbursement4 Amount")				
				|| !chkEmpty(document.getElementById("txtSancInterInstallHBA2"), "Sanctioned No. of Interest Installments")
				|| !chkEmpty(document.getElementById("txtInterestAmountHBA2"), "Interest Amount")
				|| !chkEmpty(document.getElementById("txtPrinInstallmentAmountHBA"), "Principal Installment Amount per month")
				|| !chkEmpty(document.getElementById("txtInterInstallmentAmountHBA2"), "Interest Installment Amount per month")
				|| !chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")
				|| !chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")
				|| !chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date")
				|| !chkEmpty(document.getElementById("txtReleaseDate4"), "Sanction Date")*/
			{
				return false;
			}
		if(document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoHBA2"), "Odd Principal Installment No"))
			{
				return false;
			}
		}
		if(document.getElementById("txtOddInterestInstallmentAmtHBA2").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoHBA2"), "Odd Interest Installment No"))
			{
				return false;
			}
		}
	}else{
		if(!chkEmpty(document.getElementById("txtSanctionedAmountHBA"),"Sanctioned Amount")
				|| !chkEmpty(document.getElementById("txtSancPrinInstallHBA"),"Sanctioned No. of Principal Installments")
				|| !chkEmpty(document.getElementById("txtSancInterInstallHBA"), "Sanctioned No. of Interest Installments")
				|| !chkEmpty(document.getElementById("txtInterestAmountHBA"), "Interest Amount")
				|| !chkEmpty(document.getElementById("txtPrincipalInstallmentAmtHBA"),"Principal Installment Amount per month")
				|| !chkEmpty(document.getElementById("txtInterInstallmentAmountHBA"), "Interest Installment Amount per month"))			
		{
			return false;
		}
		if(document.getElementById("txtOddPrincipalInstallmentAmtHBA").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoHBA"), "Odd Principal Installment No"))
			{
				return false;
			}
		}
		if(document.getElementById("txtOddInterestInstallmentAmtHBA").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoHBA"), "Odd Interest Installment No"))
			{
				return false;
			}
		}
	}
	if(document.getElementById('ChecklistHBAForPP').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAPP;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked
				|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBACF;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBABL;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked || !documentCheckList[13].checked
				|| !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked || !documentCheckList[17].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBASR;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked 
				|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAOF;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[9].checked 
				|| !documentCheckList[10].checked || !documentCheckList[11].checked || !documentCheckList[12].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAER;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked
				 || !documentCheckList[8].checked || !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBALC;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBARB;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBA;
		var reqType=document.getElementById("cmbHBAType").value;	
		if(reqType==800037){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked
					|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800038){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800039){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800041){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[9].checked 
					|| !documentCheckList[10].checked || !documentCheckList[11].checked || !documentCheckList[12].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800042){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked || !documentCheckList[13].checked
					|| !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked || !documentCheckList[17].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800058){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800059){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked 
					|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800060){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}
	}
	
	/*var attachmentTable = document.getElementById("myTableProofHBA");
	
	if(attachmentTable.rows.length < 2){
		alert("Please attach atleast one Proof");
		return false;
	}
*/
	return true;
}
function validateMotorcarAdvance() {	
	if (!chkEmpty(document.getElementById("cmbVehicleSubType"), "Vehicle Sub Type")
			|| !chkEmpty(document.getElementById("cmbPayCommissionMCA"), "Pay Commission GR")
			|| !chkEmpty(document.getElementById("txtAppDateMCA"), "Physical Application Received Date")
			|| !chkEmpty(document.getElementById("txtReqAmountMCA"),"Requested Amount")) 
	{
		return false;
	}
	
	if(document.getElementById("rdoNew").checked){
		if(!chkEmpty(document.getElementById("txtExShowPriceMCA"),"Exshowroom Price")){
			return false;
		}
	}
	if(!chkEmpty(document.getElementById("txtSancAmountMCA"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtSancPrincipalInstallMCA"),"Sanctioned No. of Principal Installments")
			|| !chkEmpty(document.getElementById("txtSancInterInstallMCA"), "Sanctioned No. of Interest Installments")
			|| !chkEmpty(document.getElementById("txtInterestAmountMCA"), "Interest Amount")
			|| !chkEmpty(document.getElementById("txtPrinInstallmentAmountMCA"),"Principal Installment Amount per month")
			|| !chkEmpty(document.getElementById("txtInterInstallmentAmountMCA"), "Interest Installment Amount per month"))			
	{
		return false;
	}
	
	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCAHC;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCA;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked )
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCANew;
		var reqType=document.getElementById("cmbVehicleSubType").value;	
		if(reqType==800035){
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else{
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked )
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}
	}	
	
	
	if(document.getElementById("txtOddPrincipalInstallmentAmtMCA").value!=""){
		if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoMCA"), "Odd Installment No"))
		{
			return false;
		}
	}
	if(document.getElementById("txtOddInterestInstallmentAmtMCA").value!=""){
		if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoMCA"), "Odd Installment No"))
		{
			return false;
		}
	}
	/*var attachmentTable = document.getElementById("myTableProofMCA");
	
	if(attachmentTable.rows.length < 2){
		alert("Please attach atleast one Proof");
		return false;
	}
*/
	return true;
}


function displayGuarantorDtls(){
	if(document.getElementById("rdoYes").checked){
		
		document.getElementById('trGuarantor').style.display = '';
	}
	else{
		document.getElementById('trGuarantor').style.display = 'none';
	}
}

function backButton(flag)
{
	if(flag == 1){
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&userType=DEO&elementId=800011";
	}else if(flag == 2){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=DEOAPP&elementId=800021";
	}else if(flag == 3){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=HO&elementId=800022";
	}else if(flag == 4){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=HODASST&elementId=800023";
	}else if(flag == 5){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=HOD&elementId=800024";
	}else if(flag == 6){
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&userType=HODASST2&elementId=800025";
	}else if(flag == 7){
		self.location.href = "ifms.htm?actionFlag=LNAloadDraftRequestList&userType=HODASST2&elementId=800029";
	}
	
}
function greaterThanCurrDateValidation(fieldname) 
{
	var	day = fieldname.value.split("/")[0];
	var month = fieldname.value.split("/")[1]; 
	var year = fieldname.value.split("/")[2];

	var dt = new Date(year, month-1, day); 
	var today = new Date();
	
	if(dt>today) 
	{
		alert("Physical Application Date can not be greater than Current Date");
		fieldname.value="";
		fieldname.focus();
		return false; 
	}
	
	return true;
}
function lessThanCurrDateValidation(fieldname) 
{
	var	day = fieldname.value.split("/")[0];
	var month = fieldname.value.split("/")[1]; 
	var year = fieldname.value.split("/")[2];
	
	var dt = new Date(year, month-1, day);
	var today = new Date();
	var newDt = new Date(today.getFullYear(), today.getMonth(), today.getDate());
	var dtDiff = dt - newDt;
	if(fieldname!=""){
		if (dtDiff<0){ 
			alert("Sanction Date can not be greater than Current Date");
			fieldname.value="";
			fieldname.focus();
			return false; 
		}
	}	
	return true;
}
function showRowCell(element)
{
    var cell, row;    
    if (element.parentNode) 
    {
    	
      do
      	cell = element.parentNode;
    	while (cell.tagName.toLowerCase() != 'td')
     	row = cell.parentNode;
    }
    else if (element.parentElement) 
    {
       do
      	cell= element.parentElement;
    	  while (cell.tagName.toLowerCase() != 'td')
      		row = cell.parentElement;
    }
    return row.rowIndex;
}
function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
	document.getElementById("btnAddNewCheckListCA").style.display='';
	document.getElementById("btnAddNewCheckListMCA").style.display='';
	document.getElementById("btnAddNewCheckListHBA").style.display='';
	//document.getElementById("btnAddNewCheckListCA").disabled=false;
}

function dateDiffInMonth(startDate,endDate){
	var d1Y = startDate.getFullYear();
    var d2Y = endDate.getFullYear();
    var d1M = startDate.getMonth();
    var d2M = endDate.getMonth();
    var totalMonth=(d2M+12*d2Y)-(d1M+12*d1Y);
    return totalMonth;
}
function validateBasicPay(){
	
	var payCommission= document.getElementById("cmbPayCommissionHBA").value;
	var index = document.LNARequestProcessForm.cmbPayScaleHBA.selectedIndex ;
	var payScale = document.LNARequestProcessForm.cmbPayScaleHBA[index].text ;
	var basicPay = document.getElementById("txtBasicPay").value;
	var payArray;
	var count=0;
	
	if((basicPay != null && basicPay != "") && (document.getElementById("cmbPayScaleHBA").value != null && document.getElementById("cmbPayScaleHBA").value != -1) )
	{	
		if(payCommission == '800054'){
			var tempArray = payScale.split("(");
			payArray = tempArray[0].split("-");
			var payIn = basicPay - (tempArray[1].split(")"))[0];
			for(k=0;k<payArray.length;k++){
				temp = payArray[k];
				payArray[k] = temp.trim();

			}
			if(parseInt(payIn)< parseInt(payArray[0]) || parseInt(payIn) >parseInt(payArray[1])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				document.getElementById("txtBasicPay").focus();
				return;
			}
			
		}else if(payCommission == '800053'){
			
			payArray = payScale.split("-");
		
			var temp;
			for(k=0;k<payArray.length;k++){
				temp = payArray[k];
				payArray[k] = temp.trim();
			}
			for(var j=0;j<payArray.length;j++){
				if(payArray[j] == 'EB')
				{
					payArray.splice(j,1); 
				}
			}
			if(basicPay == payArray[payArray.length - 1]){
				return;
			}
			for(var i=0;i<payArray.length;i+=2){
		
				if(i != 0){
					if(parseInt(basicPay)>parseInt(payArray[i])){
						count = i;
						continue;
					}
					
					else{
						var start = payArray[i-2];
						var variance = payArray[i-1];
	
						if((basicPay-start)%variance != 0){
							alert("The Basic Pay is not in accordance with the Pay Scale selected");
							document.getElementById("txtBasicPay").value = '';
						}
						return;
					}
				}
				else{
					if(parseInt(basicPay)<parseInt(payArray[i])){

						alert("The Basic Pay is not in accordance with the Pay Scale selected");
						document.getElementById("txtBasicPay").value = '';
						return;
					}
				}
				count = i;
			}
			if(count!=0 && parseInt(basicPay)>parseInt(payArray[count])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				return;
			}
		}
	}
	
}

function GetScalePostfromDesg()
{
	  var payScalelength=document.getElementById("cmbPayScaleHBA").length;
	  for(i=1;i<payScalelength;i++)
	  {
		  var lgth = document.getElementById("cmbPayScaleHBA").options.length -1;				  
		  document.getElementById("cmbPayScaleHBA").options[lgth] = null;
	  }
	  var commissionId;
	  var payCommission= document.getElementById("cmbPayCommissionHBA").value;
	  if(payCommission == '800053'){
		  commissionId = 700015;
	  }else{
		  commissionId = 700016;
	  }
	  var url = './hrms.htm?actionFlag=GetScalefromDesignation';
	  var uri = '&commissionId='+commissionId+'&ifAjax=TRUE';		  
	  url = url + uri;
	  
	  var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
						GetScalesPostsfromDesg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function GetScalesPostsfromDesg(myAjax){	
	
	  var XMLDoc =  myAjax.responseXML.documentElement;	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('scale-mapping');
	  var scale = document.getElementById("cmbPayScaleHBA");
	  for ( var i = 0 ; i < XmlHiddenValues.length ; i++ )
	  {
		   var val=XmlHiddenValues[i].childNodes[0].firstChild.nodeValue;
		   var text =XmlHiddenValues[i].childNodes[1].firstChild.nodeValue; 
		   var theOption = new Option;
		   theOption.value=val;
		   theOption.text=text;			
		   scale.options[i+1]=theOption;
      }
}
function validateRemarks(field,alrtString)
{
  var str=field.value;
  if (!str || trim(str)=="") { return  true; }
  re1 = /[^a-z^0-9\/\." "]/gi;
  if(str.search(re1) < 0)
  {
  	return true;
  }
  else
  {
  	alert(alrtString);
  	field.focus();
  }
  return (str.search(re1) < 0 ? true : false);
}
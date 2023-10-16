function SaveRequest()
{
	var requestArr = document.GPFRequestProcessForm.RadioButtonRequest ;
	var requestValue;
	for (var i=0; i<requestArr.length; i++)
	{
		  if (requestArr[i].checked == true)
		  {
			  requestValue = requestArr[i].value ;
		  }
	}	
	if(requestValue == '1'){
		saveChangeSubscription();
	}else if(requestValue == '2'){
		saveRefundableAdvance();
		
	}else if(requestValue == '3'){
		saveNonRefundableAdvance();
		
	}else if(requestValue == '4'){
		saveFinalWithdrawal();
	}

}

function saveChangeSubscription(){
	

	var uri="ifms.htm?actionFlag=saveChangeSubscription";
	var url = runForm(0);

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getSaveRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function saveRefundableAdvance(){

	var requestName= "RA";
	var saveOrUpdateFlag = 1;
	var clubFlag = "0";
	if(document.getElementById("cbClubInstallment").checked){
		clubFlag = "1";
	}
	var cbSpecialCase='N';
	if(document.getElementById("cbSpecialCase").checked){
		cbSpecialCase='Y';
	}
	var uri = 'ifms.htm?actionFlag=saveRefundableAdvance';
	var url = runForm(0);
	url = url+"&requestName="+requestName+"&clubFlag="+clubFlag+"&cbSpecialCase="+cbSpecialCase;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
		getSaveAdvanceRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function saveNonRefundableAdvance(){

	var requestName= "NRA";
	
	var saveOrUpdateFlag = 1;

	var cbSpecialCase='N';
	if(document.getElementById("cbSpecialCase").checked){
		cbSpecialCase='Y';
	}
	var uri = 'ifms.htm?actionFlag=saveRefundableAdvance';
	var url = runForm(0);
	url = url+"&requestName="+requestName+"&cbSpecialCase="+cbSpecialCase;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
		getSaveAdvanceRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function saveFinalWithdrawal(){

	var uri="ifms.htm?actionFlag=saveFinalWithdrawal";
	var url =runForm(0);
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				getSaveAdvanceRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getSaveAdvanceRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblSaveFlag) {
		alert('Request saved successfully as Draft');		
		self.location.href = "ifms.htm?viewName=GPFEmpSearch";
	}

}
function forwardRequest(flag){
	var requestArr = document.GPFRequestProcessForm.RadioButtonRequest ;
	var requestValue;
	for (var i=0; i<requestArr.length; i++)
	{
		  if (requestArr[i].checked == true)
		  {
			  requestValue = requestArr[i].value ;
		  }
	}	
	var oneRequestAlreadyExists =document.getElementById("hidReqInProgress").value;
	if(requestValue == '1'){
		if(flag == 1 && oneRequestAlreadyExists== 'true'){
			alert("One Request For Change Subscription is already in progress for the employee.You can save the request as Draft.");
			return;
		}
		fwdChangeSubscription(flag);
	}else if(requestValue == '2'){
		if(flag == 1 && oneRequestAlreadyExists== 'true'){
			alert("One Request For Advance is already in progress for the employee.You can save the request as Draft.");
			return;
		}
		fwdRefundableAdvance(flag);
		
	}else if(requestValue == '3'){
		if(flag == 1 && oneRequestAlreadyExists== 'true'){
			alert("One Request For Advance is already in progress for the employee.You can save the request as Draft.");
			return;
		}
		fwdNonRefundableAdvance(flag);
		
	}else if(requestValue == '4'){
		if(flag == 1 && oneRequestAlreadyExists== 'true'){
			alert("One Request For Final Withdrawal is already in progress for the employee.You can save the request as Draft.");
			return;
		}
		fwdFinalWithdrawal(flag);
	}
}

function fwdChangeSubscription(flag){
	if(!validateChangeSubs())
	{
		return;
	}
	var ChangeSubId = document.getElementById("hidChangeSubID").value;
	var ForwardToPost = document.getElementById("ForwardToPost").value;
	var uri="";
	
	var url = runForm(0);
	
	if(flag == 1){
		uri="ifms.htm?actionFlag=forwardChangeSubToDEOApprover";
		url = url + "&ChangeSubId=" + ChangeSubId + "&ForwardToPost=" + ForwardToPost;
	}
	else if(flag == 2){
		var deoRemarks = document.getElementById("txtApproverRemarks").value;		
		uri="ifms.htm?actionFlag=forwardChangeSubToHO";
		url = "ChangeSubId=" + ChangeSubId + "&ForwardToPost=" + ForwardToPost + "&deoRemarks=" + deoRemarks;
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				if(flag == 1){
					getForwardRequestPopup(myAjax,"Change Subscription");
				}
				else{
					getForwardRequestMsg(myAjax);
				}
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
}
function fwdRefundableAdvance(flag){
	
	var url = runForm(0);
	var uri = "";
	var requestName= "RA";
	var clubFlag = "0";
	if(document.getElementById("cbClubInstallment").checked){
		clubFlag = "1";
	}
	if (flag==1){
		if(!validateRAdata()){
			return;
		}
		var total= document.getElementById("voClubbing").rows.length-1;
		
		if(total>0 ){
			var chkBox = document.getElementById("cbClubInstallment");
			if(!chkBox.checked){
				alert("You need to club the previous advance for applying a new advance! ");
				return;
			}else{
				var requestAmount = document.getElementById("txtAdvanceAmount").value;
				var outstandingAmount = document.getElementById("txtOutstanding1").value;
				var sancAmount = document.getElementById("txtSancAmount").value;
				if(Number(requestAmount)<Number(outstandingAmount)){
					alert("Requested Amount should not be less than outstanding amount of clubbed request.");
					return;
				}else if(sancAmount !="" && Number(sancAmount)<Number(outstandingAmount)){
					alert("Outstanding amount of clubbed request is greater than the eligible advance amount for employee.");
					return;
				}
				updatePayableAmt(1);
			}
		}
		
		var cbSpecialCase='N';
		if(document.getElementById("cbSpecialCase").checked){
			cbSpecialCase='Y';
		}
		uri = 'ifms.htm?actionFlag=forwardRefundableAdvance';
		url = url+"&requestName="+requestName+"&clubFlag="+clubFlag+"&cbSpecialCase="+cbSpecialCase;
	}
	else if(flag == 2){
		var txtApproverRemarks = document.getElementById('txtApproverRemarks').value;
		uri = 'ifms.htm?actionFlag=forwardRefundableAdvanceToHO';
		url = url+"&requestName="+requestName+"&txtApproverRemarks="+txtApproverRemarks;
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {	
					if (flag==1){
						getForwardAdvanceRequestMsg(myAjax,"Refundable Advance");
					}else{
						getForwardAdvanceRequestMsgDEO(myAjax);
					}
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function validateSancDetailsRA(){
	if(!chkEmpty(document.getElementById("txtSancAmount"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtSancInstallments"),"Sanctioned No Of Installments")
			|| !chkEmpty(document.getElementById("txtInstallmentAmount"), "Installment Amount")
			|| !chkEmpty(document.getElementById("txtOddInstallmentAmt"), "Odd Installment Amount")
			|| !chkEmpty(document.getElementById("txtPayableAmount"), "Payable Amount")
			|| !chkEmpty(document.getElementById("txtOutstandingBalance"), "Account Balance")){
		return false;
	}
	return true;
}
function fwdNonRefundableAdvance(flag){
	
	var url = runForm(0);
	var uri = "";
	var requestName= "NRA";
	if (flag==1){
		if(!validateNRAdata() || !validateAppDateNRA() || !validateSancDetailsNRA()){
			return;
		}
		var cbSpecialCase='N';
		if(document.getElementById("cbSpecialCaseNRA").checked){
			cbSpecialCase='Y';
		}
		uri = 'ifms.htm?actionFlag=forwardRefundableAdvance';
		url = url+"&requestName="+requestName+"&cbSpecialCase="+cbSpecialCase;
	}
	else if(flag == 2){
		var txtApproverRemarks = document.getElementById('txtApproverRemarks').value;
		uri = 'ifms.htm?actionFlag=forwardRefundableAdvanceToHO';
		url = url+"&requestName="+requestName+"&txtApproverRemarks"+txtApproverRemarks;
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				if(flag==1){
					getForwardAdvanceRequestMsg(myAjax,"Non-refundable Advance");
				}else{
					getForwardAdvanceRequestMsgDEO(myAjax);
				}
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function validateSancDetailsNRA(){
	if(!chkEmpty(document.getElementById("txtSancAmountNRA"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtOutstandingBalanceNRA"), "Account Balance")){
		return false;
	}
	var purpose = document.getElementById("cmblstPurposeCategory2").value;
	if(purpose=="800024"){
		var releaseInstallments = document.getElementById("txtSancPayInstNo").value;
		if(!chkEmpty(document.getElementById("txtSancPayInstNo"),"Sanctioned No Of Installments")){
			return false;
		}
		else if(!chkEmpty(document.getElementById("txtInstallment1"),"Installment 1")
				|| !chkEmpty(document.getElementById("txtReleaseDate1"), "Release Date for Installment 1")
				|| !chkEmpty(document.getElementById("txtInstallment2"), "Installment 2")
				|| !chkEmpty(document.getElementById("txtReleaseDate2"), "Release Date for Installment 2")){
			return false;
		}else{
			
			if(releaseInstallments == "3" && (!chkEmpty(document.getElementById("txtInstallment3"), "Installment 3")
					|| !chkEmpty(document.getElementById("txtReleaseDate3"), "Release Date for Installment 3"))){
				return false;
			}else if(releaseInstallments == "4" && (!chkEmpty(document.getElementById("txtInstallment3"), "Installment 3")
					|| !chkEmpty(document.getElementById("txtReleaseDate3"), "Release Date for Installment 3")
					|| !chkEmpty(document.getElementById("txtInstallment4"), "Installment 4")
					|| !chkEmpty(document.getElementById("txtReleaseDate4"), "Release Date for Installment 4"))){
				return false;
			}
		}
		var installment1 = Math.abs(document.getElementById("txtInstallment1").value);
		var installment2 = Math.abs(document.getElementById("txtInstallment2").value);
		var installment3 = Math.abs(document.getElementById("txtInstallment3").value);
		var installment4 = Math.abs(document.getElementById("txtInstallment4").value);
		var requestAmount = document.getElementById("txtAdvanceAmount2").value;
		if((releaseInstallments == "2" && (Number(installment1)+Number(installment2)) != Number(requestAmount))
				|| (releaseInstallments == "3" && (Number(installment1)+Number(installment2)+Number(installment3)) != Number(requestAmount))
				|| (releaseInstallments == "4" && (Number(installment1)+Number(installment2)+Number(installment3)+Number(installment4)) != Number(requestAmount))){
			alert("The sum of release installment should be equal to request amount.");
			return false;
		}
	}
	return true;
}
function fwdFinalWithdrawal(flag){
	var uri="";
	var url = runForm(0);
	if(flag == 1){
		if(!validateFinalwithdrawal()){
			return;
		}
		uri="ifms.htm?actionFlag=forwardFinalWithdrawalDEOApprover";
		
	}else if(flag == 2){
		var txtApproverRemarks = document.getElementById('txtApproverRemarks').value;
		url=url+"&txtApproverRemarks"+txtApproverRemarks;
		uri="ifms.htm?actionFlag=forwardFinalWithdrawalToHO";
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					if(flag==1){
						getForwardAdvanceRequestMsg(myAjax,"Final Withdrawal");
					}else{
						getForwardAdvanceRequestMsgDEO(myAjax);
					}
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );

}
function validateSancDetailsFW(){
	if(!chkEmpty(document.getElementById("txtSancAmountFW"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtOutstandingBalanceFW"), "Account Balance")){
		return false;
	}
	return true;
}
function getForwardAdvanceRequestMsg(myAjax, requestType){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrTransId = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrOrgEmpId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var lStrGpfAccNo = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	if (lblForwardFlag) {
		alert('Transaction ID '+lStrTransId+' has been generated successfully against the Sevaarth ID '+lStrOrgEmpId+' and GPF A/C No '+lStrGpfAccNo +' for the request of '+requestType);		
		self.location.href = "ifms.htm?viewName=GPFEmpSearch";
	}
}
function getForwardAdvanceRequestMsgDEO(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag) {
		alert('Request has been forwarded to Head Officer');		
		self.location.href = "ifms.htm?actionFlag=loadGPFRequestList&userType=DEOAPP";
	}

}
function approveRequest(){
	
	var requestArr = document.GPFRequestProcessForm.RadioButtonRequest ;
	
	var requestValue;
	for (var i=0; i<requestArr.length; i++)
	{
		  if (requestArr[i].checked == true)
		  {
			  requestValue = requestArr[i].value ;
		  }
	}		
	if(requestValue == '1'){
		approveChangeSubscription();
	}else if(requestValue == '2' ){
		approveRefundableAdvance();
	}else if(requestValue == '3'){
		approveNonRefundableAdvance();
	}else if(requestValue == '4'){
		approveFinalWithdrawal();
	}

}
function approveChangeSubscription(){
	
	var uri = "ifms.htm?actionFlag=approveChangeSubByHO";
	var url = runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApprovedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function approveRefundableAdvance(){
	if(!validateSancDetailsRA() || !validateOrderDetail()){
		return;
	}
	var txtOrderNo = document.getElementById('txtOrderNo').value;

	var txtOrderDate = document.getElementById('txtOrderDate').value;
	var txtHORemarks = document.getElementById('txtHORemarks').value;
	var requestName= "RA";
	var url = runForm(0);
	var uri = 'ifms.htm?actionFlag=approveRefundableAdvance';
	url = url+"&requestName="+requestName+"&txtOrderNo="+txtOrderNo+"&txtOrderDate="+txtOrderDate+"&txtHORemarks="+txtHORemarks;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApprovedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function approveNonRefundableAdvance(){
	if(!validateSancDetailsNRA() || !validateOrderDetail()){
		return;
	}
	var txtOrderNo = document.getElementById('txtOrderNo').value;

	var txtOrderDate = document.getElementById('txtOrderDate').value;
	var txtHORemarks = document.getElementById('txtHORemarks').value;
	var requestName= "NRA";
	var url = runForm(0);
	var uri = 'ifms.htm?actionFlag=approveRefundableAdvance';
	url = url+"&requestName="+requestName+"&txtOrderNo="+txtOrderNo+"&txtOrderDate="+txtOrderDate+"&txtHORemarks="+txtHORemarks;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApprovedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function approveFinalWithdrawal(){
	if(!validateSancDetailsFW() || !validateOrderDetail()){
		return;
	}
	var uri = "ifms.htm?actionFlag=approveFinalWithdrawalByHO";
	var url = runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApprovedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function rejectRequest(flag){
	var requestArr = document.GPFRequestProcessForm.RadioButtonRequest ;
	var requestValue;
	for (var i=0; i<requestArr.length; i++)
	{
		  if (requestArr[i].checked == true)
		  {
			  requestValue = requestArr[i].value ;
		  }
	}	
	if(requestValue == '1'){
		rejectChangeSubscription(flag);
	}else if(requestValue == '2' ){
		rejectRefundableAdvance(flag);
	}else if(requestValue == '3'){
		rejectNonRefundableAdvance(flag);
	}else if(requestValue == '4'){
		rejectFinalWithdrawal(flag);
	}
}
function rejectChangeSubscription(flag){
	
	var ChangeSubId = document.getElementById("hidChangeSubID").value;
	var uri = "";
	var url = "";
	if(flag == 1){
		if (!chkEmpty(document.getElementById("txtApproverRemarks"),"Verifier Remarks")){
			return;
		}
		var txtDEORemarks = document.getElementById("txtApproverRemarks").value;		
		uri = "ifms.htm?actionFlag=rejectChangeSubByDEOApprover";
		url = "ChangeSubId=" + ChangeSubId + "&txtDEORemarks=" + txtDEORemarks;
	}
	else if(flag == 2){
		if (!chkEmpty(document.getElementById("txtHORemarks"),"Approver Remarks")){
			return;
		}
		var txtHORemarks = document.getElementById("txtHORemarks").value;
		uri = "ifms.htm?actionFlag=rejectChangeSubByHO";
		url = "ChangeSubId=" + ChangeSubId	+ "&txtHORemarks=" + txtHORemarks;
	}

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getRejectedRequestMsg(myAjax,flag);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function rejectRefundableAdvance(flag){
	
	var requestName = "RA";

		
	var url = runForm(0);
	var uri = "";
	
	if (flag==1){
		if (!chkEmpty(document.getElementById("txtApproverRemarks"),"Verifier Remarks")){
			return;
		}
		var txtApproverRemarks = document.getElementById('txtApproverRemarks').value;
		uri = 'ifms.htm?actionFlag=rejectAdvanceByDEOApprover';
		url = url+"&txtApproverRemarks"+txtApproverRemarks;
	}
	else if(flag == 2){
		if (!chkEmpty(document.getElementById("txtHORemarks"),"Approver Remarks")){
			return;
		}
		var txtOrderNo = document.getElementById('txtOrderNo').value;
		var txtOrderDate = document.getElementById('txtOrderDate').value;
		var txtHORemarks = document.getElementById('txtHORemarks').value;
		uri = 'ifms.htm?actionFlag=rejectAdvanceByHO';
		url = url+"&requestName="+requestName+"&txtOrderNo="+txtOrderNo+"&txtOrderDate="+txtOrderDate+"&txtHORemarks="+txtHORemarks;
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getRejectedRequestMsg(myAjax, flag);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function rejectNonRefundableAdvance(flag){
	var requestName = "NRA";

	
	var url = runForm(0);
	var uri = "";
	
	if (flag==1){
		if (!chkEmpty(document.getElementById("txtApproverRemarks"),"Verifier Remarks")){
			return;
		}
		var txtApproverRemarks = document.getElementById('txtApproverRemarks').value;
		uri = 'ifms.htm?actionFlag=rejectAdvanceByDEOApprover';
		url = url+"&requestName="+requestName+"&txtApproverRemarks"+txtApproverRemarks;
	}
	else if(flag == 2){
		if (!chkEmpty(document.getElementById("txtHORemarks"),"Approver Remarks")){
			return;
		}
		var txtOrderNo = document.getElementById('txtOrderNo').value;
		var txtOrderDate = document.getElementById('txtOrderDate').value;
		var txtHORemarks = document.getElementById('txtHORemarks').value;
		uri = 'ifms.htm?actionFlag=rejectAdvanceByHO';
		url = url+"&requestName="+requestName+"&txtOrderNo="+txtOrderNo+"&txtOrderDate="+txtOrderDate+"&txtHORemarks="+txtHORemarks;
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax,flag) {
					getRejectedRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function rejectFinalWithdrawal(flag){
	
	
	var uri = ""; 
	var url = runForm(0);
	if(flag == 1 ){
		if (!chkEmpty(document.getElementById("txtApproverRemarks"),"Verifier Remarks")){
			return;
		}
		uri = "ifms.htm?actionFlag=rejectFinalWithdrawalByDEOApprover"; 
		
	}else if(flag == 2){
		if (!chkEmpty(document.getElementById("txtHORemarks"),"Approver Remarks")){
			return;
		}
		uri = "ifms.htm?actionFlag=rejectFinalWithdrawalByHO";
		
	}
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getRejectedRequestMsg(myAjax,flag);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );

}
function validateOrderDetail(){
	if (!chkEmpty(document.getElementById("txtOrderNo"),"Order No")
			|| !chkEmpty(document.getElementById("txtOrderDate"),"Order Date")) 
	{				
		return false;
	}
	return true;
}
function validateChangeSubs() {	
		if (!chkEmpty(document.getElementById("txtPhyAppReceivedDate"), "Physical Application Received Date")
				|| !chkEmpty(document.getElementById("cmblstEffectFromMonth"),"Effective From Month")				
				|| !chkEmpty(document.getElementById("txtNewSubscription"), "New Subscription")) 
		{
			return false;
		}
	
		return true;
}

function validateFinalwithdrawal(){
	if (!chkEmpty(document.getElementById("txtAppDateFW"),"Application Date")			
			|| !chkEmpty(document.getElementById("txtFinalAmount"),"Final Amount"))
	{							
		return false;
	}
	//var reason = document.getElementById("cmblstReason").value;
	var superAnnDate = document.getElementById("hidDateOfSuperAnnuation").value;
	var	day = superAnnDate.split("/")[0];
	var month = superAnnDate.split("/")[1]; 
	var year = superAnnDate.split("/")[2];

	var dt = new Date(year, month-1, Number(day)+1); 
	var today = new Date();

	if(today<dt) 
	{
		alert("Employee is not eligible for Final Withdrawal before Retirement");
		return false;
	}
	var attachmentTable = document.getElementById("myTableProof2");
	
	if(attachmentTable.rows.length < 2){
		alert("Please attach atleast one Proof");
		return false;
	}
//	if(document.getElementById("hidFinalEligibility").value == "false" ){
//		alert("Employee is not eligible for Final Withdrawal before Retirement");
//		return false;
//	}

	return true;
}

function validateRAdata(){

	var index = document.GPFRequestProcessForm.cmblstPurposeCategory.selectedIndex ;

	if (!chkEmpty(document.getElementById("txtAppDateRA"),"Application Date")
			|| !chkEmpty(document.getElementById("txtAdvanceAmount"),"Advance Amount")
			|| !chkEmpty(document.getElementById("txtNoOfInstallment"),"No of Installment")
			|| !chkEmpty(document.getElementById("cmblstPurposeCategory"),"Purpose Category"))
	{		
		return false;
	}
	if(index==8){
		if (!chkEmpty(document.getElementById("textareaOther"),"Other"))
		{			
			return false;
		}
	}
//	var attachmentTable = document.getElementById("myTableProof");
//
//	if(attachmentTable.rows.length < 2){
//		alert("Please attach atleast one Proof");
//		return false;
//	}

//	if(document.getElementById("cbClubInstallment").checked){
//		var checkedRadio = false;  
//		var clubRadio= document.GPFRequestProcessForm.radioButtonClubRequest;  
//		if(!clubRadio.checked)     {
//			alert("Please select the previous advance request for Clubbing");
//			return false;
//		}
//		
//	}
	return true;

}
function validateNRAdata(){
	var index = document.GPFRequestProcessForm.cmblstPurposeCategory2.selectedIndex ;
	
	if (!chkEmpty(document.getElementById("txtAppDate2"),"Application Date")
			|| !chkEmpty(document.getElementById("txtAdvanceAmount2"),"Amount to be withdrawn")
			|| !chkEmpty(document.getElementById("cmblstPurposeCategory2"),"Purpose Category"))
	{							
		return false;
	}
	if(index==6){
		if (!chkEmpty(document.getElementById("textareaOther2"),"Other"))
		{			
			return false;
		}
	}
	if(!document.getElementById("cmblstSubType").disabled && !chkEmpty(document.getElementById("cmblstSubType"),"Sub Type")){
		return false;
	}
//	var attachmentTable = document.getElementById("myTableProof1");
//	
//	if(attachmentTable.rows.length < 2){
//		alert("Please attach atleast one Proof");
//		return false;
//	}
	return true;
}
function getSaveRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lIncreDFlag = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;

	if (lIncreDFlag==1) {
		alert('As per the Rules, Employee is not eligible to change the subscription for this financial year(only 3 times in a year)');
	}
	else if (lIncreDFlag==2) {
		alert('As per the Rules, Employee is not eligible to increase the subscription for this financial year(increase 2 times in a year)');
	}
	else if (lIncreDFlag==3) {
		alert('As per the Rules, Employee is not eligible to decrease the subscription for this financial year(decrease 1 time in a year)');
	}
	else if (lblSaveFlag) {
		alert('Request saved successfully as Draft');		
		self.location.href = "ifms.htm?viewName=GPFEmpSearch";
	}

}
function getForwardRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag) {
		alert('Request has been forwarded to Head Officer');		
		self.location.href = "ifms.htm?actionFlag=loadGPFRequestList&userType=DEOAPP";
	}

}
function getForwardRequestPopup(myAjax,requestType)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lIncreDFlag = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrTransId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var lStrOrgEmpId = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	var lStrGpfAccNo = XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
	if (lIncreDFlag==1) {
		alert('As per the Rules, Employee is not eligible to change the subscription for this financial year(only 3 times in a year)');
	}
	else if (lIncreDFlag==2) {
		alert('As per the Rules, Employee is not eligible to increase the subscription for this financial year(increase 2 times in a year)');
	}
	else if (lIncreDFlag==3) {
		alert('As per the Rules, Employee is not eligible to decrease the subscription for this financial year(decrease 1 time in a year)');
	}
	else if (lblForwardFlag) {
		alert('Transaction ID '+lStrTransId+' has been generated successfully against the Sevaarth ID '+lStrOrgEmpId+' and GPF A/C No '+lStrGpfAccNo +' for the request of '+requestType);		
		self.location.href = "ifms.htm?viewName=GPFEmpSearch";
	}

}
function getRejectedRequestMsg(myAjax,flag)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblForwardFlag) {
		alert('Request has been rejected');	
		if(flag == 1){
			self.location.href = "ifms.htm?actionFlag=loadGPFRequestList&userType=DEOAPP";
		}else{
			self.location.href = "ifms.htm?actionFlag=loadGPFRequestList&userType=HO";
		}
	}

}
function getApprovedRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblForwardFlag) {
		alert('Request has been approved successfully.');		
		self.location.href = "ifms.htm?actionFlag=loadGPFRequestList&userType=HO";
	}
}

function actionOnOtherReason(field){
	var purpose = field.value;
	if (purpose == "800009"){
		document.getElementById('textareaOther').readOnly=false;
	}
	else{
		document.getElementById('textareaOther').readOnly=true;
	}
}
function currentDateValidation(fieldname) 
{
	var	day = fieldname.value.split("/")[0];
	var month = fieldname.value.split("/")[1]; 
	var year = fieldname.value.split("/")[2];

	var dt = new Date(year, month-1, Number(day)+1); 
	var today = new Date();

	if(today>dt) 
	{
		alert("Order Date should be greater than Current Date");
		fieldname.value="";
		fieldname.focus();
		return false; 
	}
	return true;
}

function previousDateValidation(fieldname) 
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
function backButton(flag)
{
	if(flag == 1){
		self.location.href = "ifms.htm?viewName=GPFEmpSearch";
	}else if(flag == 2){
		self.location.href = "ifms.htm?actionFlag=loadGPFRequestList&userType=DEOAPP";
	}else if(flag == 3){
		self.location.href = "ifms.htm?actionFlag=loadGPFRequestList&userType=HO";
	}
}
function window_new_update(url)
{
	var height = screen.height - 300;
   	var width = screen.width - 400;
   	var urlstring = url;
   	var urlstyle ="height="+height+",width="+width+",titlebar=no,directories=no,location=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,left=150";
   	   	 
	var newWindow = null;
   	newWindow = window.open(urlstring,"Withdrawal",urlstyle,"false");

}
var sancAmountFlag = 0;
var selectedClubbedReqRowNo = Number.NaN;
function enableClubbing(){
	if(document.getElementById("cbClubInstallment").checked)
	{
		selectedClubbedReqRowNo = 1;
		var requestAmount = document.getElementById("txtAdvanceAmount").value;
		if(requestAmount != ""){
			var outstandingAmount = document.getElementById("txtOutstanding1").value;
			var sancAmount = document.getElementById("txtSancAmount").value;
			if(Number(requestAmount)<Number(outstandingAmount)){
				alert("Requested Amount should not be less than outstanding amount of clubbed request.");
				//document.getElementById("txtAdvanceAmount").value= "";
				//document.getElementById("txtAdvanceAmount").focus();
				return false;
			}else if(sancAmount !="" && Number(sancAmount)<Number(outstandingAmount)){
				alert("Outstanding amount of clubbed request is greater than the eligible advance amount for employee.");
				//document.getElementById("txtAdvanceAmount").value= "";
				//document.getElementById("txtAdvanceAmount").focus();
				return false;
			}
			updatePayableAmt(1);
		}
	}else{
		updatePayableAmt(Number.NaN);
	}
	return true;
}
function checkClubbedReq(rowNum){

	
}
function checkEligibleAmount(){
	var basicPay = document.getElementById("hidBasicPay").value;
	var accBalance = document.getElementById("hidNetBalance").value;
	
	var totalBasicPay = Number(basicPay)*3;
	var halfBalance = Number(accBalance)/2; 
	
	if(Number(totalBasicPay)<Number(halfBalance)){
		document.getElementById("txtEligibleAmt").value = totalBasicPay;
	}
	else{
		document.getElementById("txtEligibleAmt").value = halfBalance;
	}
	popupSancDetails();
}
function validateNoOfInstallment(){
	
	var noOfInstallment = document.getElementById("txtNoOfInstallment").value;
	if(noOfInstallment != null && noOfInstallment!=""){
		if(document.getElementById("cbSpecialCase").checked && (Number(noOfInstallment)<12 || Number(noOfInstallment) >36)){
			alert("No of installment should be between 12 and 36");
			document.getElementById("txtNoOfInstallment").value='';
		}
		else if(!document.getElementById("cbSpecialCase").checked && (Number(noOfInstallment)<12 || Number(noOfInstallment) >24))
		{
			alert("No of installment should be between 12 and 24");
			document.getElementById("txtNoOfInstallment").value='';
		}
		else{
			var strDate = document.getElementById("hidDateOfSuperAnnuation").value;
			
			var	day = strDate.split("/")[0];
			var month = strDate.split("/")[1]; 
			var year = strDate.split("/")[2];
			var retirementDate = new Date(year, month-1, day); 
			 
			var currDate = new Date();
			
			 var d1Y = currDate.getFullYear();
		     var d2Y = retirementDate.getFullYear();
		     var d1M = currDate.getMonth();
		     var d2M = retirementDate.getMonth();

		     var leftMonths = (d2M+12*d2Y)-(d1M+12*d1Y);
		     if(leftMonths<noOfInstallment){
		    	 alert("As per Employee's retirement date, number of installments can not be greater than "+leftMonths);
		    	 document.getElementById("txtNoOfInstallment").value='';
		    	 return;
		     }
			var sancAmount = document.getElementById("txtSancAmount").value;
			if(sancAmount != ""){
				document.getElementById("txtSancInstallments").value = noOfInstallment;
				
				 var inst= Math.floor(sancAmount/noOfInstallment);
				 document.getElementById("txtInstallmentAmount").value = inst;
				 var oddInst = sancAmount - (inst*(noOfInstallment-1));
				 if(oddInst == inst)
					 document.getElementById("txtOddInstallmentAmt").value = 0;
				 else
					 document.getElementById("txtOddInstallmentAmt").value = oddInst;
			}
		}
	}
}

function popupSancDetails(){

	var advanceAmount = document.getElementById("txtAdvanceAmount").value;
	var accBalance = document.getElementById("hidNetBalance").value;
	if(Number(advanceAmount)>Number(accBalance)){
		alert("Advance Amount can not be greater than your account balance");
		document.getElementById("txtAdvanceAmount").value = "";
		return;
	}
	var eligibleAmount = document.getElementById("txtEligibleAmt").value;
	if(advanceAmount != "" ){
		if(eligibleAmount == "" || eligibleAmount == null){
			var basicPay = document.getElementById("hidBasicPay").value;
			
			var totalBasicPay = Number(basicPay)*3;
			var halfBalance = Number(accBalance)/2; 
			
			if(Number(totalBasicPay)<Number(halfBalance)){
				eligibleAmount = totalBasicPay;
			}
			else{
				eligibleAmount = halfBalance;
			}
		}
		if(Number(advanceAmount)<=Number(eligibleAmount)){
			document.getElementById("txtSancAmount").value = advanceAmount;
			if(document.getElementById("cbClubInstallment").checked){
				checkClubbedReq(selectedClubbedReqRowNo);
			}else{
				document.getElementById("txtPayableAmount").value = advanceAmount;
				document.getElementById("txtOutstandingBalance").value = document.getElementById("hidNetBalance").value - advanceAmount;
			}
			
			sancAmountFlag = 0;
		}
		else{
			document.getElementById("txtSancAmount").value = eligibleAmount;
			sancAmountFlag = 1;
			alert("As per the rules, you are not eligible to take advance more than "+eligibleAmount);
//			document.getElementById("txtAdvanceAmount").value = "";
//			document.getElementById("txtSancAmount").value = "";
//			document.getElementById("txtPayableAmount").value = "";
//			document.getElementById("txtOutstandingBalance").value = "";
//			return;
//			document.getElementById("txtAdvanceAmount").value = eligibleAmount;
			if(document.getElementById("cbClubInstallment").checked){
				//checkClubbedReq(selectedClubbedReqRowNo);
				selectedClubbedReqRowNo = 1;
				var outstandingAmount = document.getElementById("txtOutstanding1").value;
					if(Number(eligibleAmount)<Number(outstandingAmount)){
						alert("Outstanding amount of clubbed request is greater than the eligible advance amount for employee.");
						//document.getElementById("txtAdvanceAmount").value= "";
						//document.getElementById("txtAdvanceAmount").focus();
						return;
					}
					updatePayableAmt(1);
				
			}else{
				document.getElementById("txtPayableAmount").value = eligibleAmount;
				document.getElementById("txtOutstandingBalance").value = document.getElementById("hidNetBalance").value - eligibleAmount;
			}
			
		}
	}else if(eligibleAmount != 0){
		document.getElementById("txtSancAmount").value = eligibleAmount;
		if(document.getElementById("cbClubInstallment").checked){
			updatePayableAmt(selectedClubbedReqRowNo);
		}else{
			document.getElementById("txtPayableAmount").value = eligibleAmount;
			document.getElementById("txtOutstandingBalance").value = document.getElementById("hidNetBalance").value - eligibleAmount;
		}
		sancAmountFlag = 1;
	}
	var sancAmount = document.getElementById("txtSancAmount").value;
	var noOfInstallment = document.getElementById("txtNoOfInstallment").value;
	if(sancAmount != "" && noOfInstallment != ""){
	document.getElementById("txtSancInstallments").value = noOfInstallment;
	var inst= Math.floor(sancAmount/noOfInstallment);
	document.getElementById("txtInstallmentAmount").value = inst;
	var oddInst = sancAmount - (inst*(noOfInstallment-1));
	 if(oddInst == inst)
		 document.getElementById("txtOddInstallmentAmt").value = 0;
	 else
		 document.getElementById("txtOddInstallmentAmt").value = oddInst;
	}
}
	
function updatePayableAmt(rowNum){
	var sancAmount = document.getElementById("txtSancAmount").value;
	if(isNaN(rowNum)){
		document.getElementById("txtPayableAmount").value = sancAmount;
		document.getElementById("txtOutstandingBalance").value = document.getElementById("hidNetBalance").value - sancAmount;
	}else{
		var clubbedRequestOutstanding = document.getElementById("txtOutstanding"+rowNum).value;
		if(sancAmount != "" && clubbedRequestOutstanding != ""){
		selectedClubbedReqRowNo = rowNum;
		document.getElementById("txtPayableAmount").value = sancAmount - clubbedRequestOutstanding;
		document.getElementById("txtOutstandingBalance").value = document.getElementById("hidNetBalance").value - (sancAmount - clubbedRequestOutstanding);
		}
	}
}
function validateSancAmountRA(){
	var sancAmount = document.getElementById("txtSancAmount").value;
	var reqAmount =document.getElementById("txtAdvanceAmount").value;
	if(sancAmount != null && sancAmount !=""){
//		if(Number(reqAmount) > Number(sancAmount)){
//			alert("You are not allowed to decrease the sanctioned amount.");
//			document.getElementById("txtSancAmount").value = document.getElementById("txtAdvanceAmount").value;
//		}else{
			var diff;
			if(document.getElementById("cbClubInstallment").checked){
				diff = document.getElementById("txtOutstanding1").value;
			}else{
				diff = 0;
			}
			//var diff =  Number(sancAmount) - Number(reqAmount);
			//var oldVal = document.getElementById("txtPayableAmount").value;
			document.getElementById("txtPayableAmount").value = Number(sancAmount)-Number(diff);
			document.getElementById("txtOutstandingBalance").value = Number(document.getElementById("hidNetBalance").value) - Number(document.getElementById("txtPayableAmount").value);
			populateSancDetailsHO();
//		}
	}
}
function populateSancDetailsHO(){
	var sancAmount = document.getElementById("txtSancAmount").value;
	var inst = document.getElementById("txtSancInstallments").value;
	if(inst != null && inst != ""){
		var instAmount= Math.floor(sancAmount/inst);
		document.getElementById("txtInstallmentAmount").value = instAmount;
		 var oddInst = sancAmount - (instAmount*(inst-1));
		 if(oddInst == instAmount)
			 document.getElementById("txtOddInstallmentAmt").value = 0;
		 else
			 document.getElementById("txtOddInstallmentAmt").value = oddInst;
	}
}
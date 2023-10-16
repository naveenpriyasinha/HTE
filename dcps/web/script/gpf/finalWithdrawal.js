var sancAmountFlag = 0;
function checkEligibleAmountFW(){
	var accBalance = document.getElementById("hidNetBalance").value;
	document.getElementById("txtEligibleAmtFW").value = Number(accBalance)*(0.9);
	popupSancDetailsForFW();
}
function popupSancDetailsForFW(){
	var advanceAmount = document.getElementById("txtFinalAmount").value;
	var eligibleAmount = document.getElementById("txtEligibleAmtFW").value;
	var accBalance = document.getElementById("hidNetBalance").value;
	if(Number(advanceAmount)>Number(accBalance)){
		alert("Final Amount can not be greater than your account balance.");
		document.getElementById("txtFinalAmount").value = "";
		return;
	}
	if(advanceAmount != "" ){
		if(eligibleAmount == "" || eligibleAmount == null){
			eligibleAmount = Number(accBalance)*(0.9);
		}
		if(Number(advanceAmount)<Number(eligibleAmount)){
			document.getElementById("txtSancAmountFW").value = advanceAmount;
			document.getElementById("txtOutstandingBalanceFW").value = document.getElementById("hidNetBalance").value - advanceAmount;
			sancAmountFlag = 0;
		}
		else{
			document.getElementById("txtSancAmountFW").value = eligibleAmount;
			document.getElementById("txtOutstandingBalanceFW").value = document.getElementById("hidNetBalance").value - eligibleAmount;
			sancAmountFlag = 1;
		}
	}else if(eligibleAmount != 0){
		document.getElementById("txtSancAmountFW").value = eligibleAmount;
		document.getElementById("txtOutstandingBalanceFW").value = document.getElementById("hidNetBalance").value - eligibleAmount;
		sancAmountFlag = 1;
	}
}

function validateSancAmountFW(){
	var sancAmount = document.getElementById("txtSancAmountFW").value;
	var reqAmount =document.getElementById("txtFinalAmount").value;
//	if(Number(reqAmount) > Number(sancAmount)){
//		alert("You are not allowed to decrease the sanctioned amount.");
//		document.getElementById("txtSancAmountFW").value = document.getElementById("txtFinalAmount").value;
//	}else{
		 
		var diff =  Number(sancAmount) - Number(reqAmount);
		document.getElementById("txtOutstandingBalanceFW").value = Number(document.getElementById("txtOutstandingBalanceFW").value) - Number(diff);
//	}
}
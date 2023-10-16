function popupSancDetailsCA(){

	var reqAmount = document.getElementById("txtReqAmountCA").value;
	var actualCost = document.getElementById("txtActualCostCA").value;
	var maxSancAmount = 20000;
	if(reqAmount != "" && actualCost != "" ){
		if(reqAmount<maxSancAmount){
			if(Number(reqAmount)<Number(actualCost)){
				document.getElementById("txtSancAmountCA").value = reqAmount;
			}
			else{
				document.getElementById("txtSancAmountCA").value = actualCost;
			}
		}else{
			if(Number(actualCost)>maxSancAmount){
				document.getElementById("txtSancAmountCA").value = maxSancAmount;
			}
			else{
				document.getElementById("txtSancAmountCA").value = actualCost;
			}			
		}
	}
}
function checkEligibleAmountCA(){
	var reqAmount = document.getElementById("txtReqAmountCA").value;
	var actualCost = document.getElementById("txtActualCostCA").value;
	if(reqAmount != "" && actualCost != "" ){
		if(reqAmount<20000){
			if(Number(reqAmount)<Number(actualCost)){
				document.getElementById("txtEligibleAmtCA").value = reqAmount;
			}
			else{
				document.getElementById("txtEligibleAmtCA").value = actualCost;
			}
		}else{
			if(Number(reqAmount)<Number(actualCost)){
				document.getElementById("txtEligibleAmtCA").value = 20000;
			}
			else{
				document.getElementById("txtEligibleAmtCA").value = actualCost;
			}			
		}
	}
}
function validateNoOfInstallmentsCA(){
	document.getElementById("txtInstallmentAmountCA").value = "";
	document.getElementById("txtOddInstallmentAmtCA").value = "";
	var sancInstallments = document.getElementById("txtSancInstallmentsCA").value;
	var DOS = document.getElementById("hidDateOfSuperAnnuation").value;
	var lArrDate = DOS.split("/");	
	var dateofSuperAnnu = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	var currDate = new Date();
	var futureDate = new Date(currDate);
	futureDate.setMonth(futureDate.getMonth() + Number(sancInstallments));
	var maxInstallment=dateDiffInMonth(currDate, dateofSuperAnnu);
	if(sancInstallments!=""){
		if(Number(sancInstallments==0)){
			alert('Sanction Installments Should be greater than 0');
			document.getElementById("txtSancInstallmentsCA").value="";
			document.getElementById("txtSancInstallmentsCA").focus();
			return false;
		}
		if(Number(sancInstallments>50)){
			alert('Sanction Installments Should be less than 50');
			document.getElementById("txtSancInstallmentsCA").value="";
			document.getElementById("txtSancInstallmentsCA").focus();
			return false;
		}
		if(futureDate>dateofSuperAnnu){
			alert('Considering Retirement Date Maximum Number of Total Installments for Employee are '+maxInstallment);
			document.getElementById("txtSancInstallmentsCA").value="";
			document.getElementById("txtSancInstallmentsCA").focus();
			return false;
		}
		var sancAmount = document.getElementById("txtSancAmountCA").value;
		var noOfInstallment = document.getElementById("txtSancInstallmentsCA").value;
		if(sancAmount != "" && noOfInstallment != ""){
			var inst= Math.floor(sancAmount/noOfInstallment);
			var oddValue = sancAmount - (inst*(noOfInstallment-1));
			if (inst==oddValue){
				document.getElementById("txtInstallmentAmountCA").value = inst;
				document.getElementById("cmbOddInstallNoCA").value = -1;
				document.getElementById("cmbOddInstallNoCA").disabled = true;				
			} else{
				document.getElementById("txtInstallmentAmountCA").value = inst;
				document.getElementById("txtOddInstallmentAmtCA").value = oddValue;
				document.getElementById("cmbOddInstallNoCA").disabled = false;
			}
		}
	}
	return true;
}
function validateReqAmountCA(flag){
	var reqAmount;
	if(flag==1){
		reqAmount= document.getElementById("txtReqAmountCA");
		if(reqAmount.value>20000){
			alert('Computer Advance Amount should be less than or Equals to 20,000');
			return false;
		}
	}else{
		reqAmount= document.getElementById("txtSancAmountCA");
		if(reqAmount.value>20000){
			alert('Computer Advance Amount should be less than or Equals to 20,000');
			reqAmount.value="";
			reqAmount.focus();
			return false;
		}
	}	
	return true;
}

function addNewCheckListCA(){
	
	var rowCnt = document.getElementById("hidRowCountCA").value;
	var newRow =  document.getElementById("tblChecklistCA").insertRow(document.getElementById("tblChecklistCA").rows.length);	
	var Cell1 = newRow.insertCell(0);
	var Cell2 = newRow.insertCell(1);
	
	Cell1.innerHTML = 'Enter Document Name     <input type="text" name="txtChecklistNameCA" id="txtChecklistNameCA"/>';
	Cell2.innerHTML = '<input type="button" class="bigbutton" style="width: 100px" value="OK" id="btnOk" name="btnOk" onclick=\'displayChecklistCA(this,"tblChecklistCA",'+rowCnt+')\'"/> <img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblChecklistCA")\'/>';
	document.getElementById("btnAddNewCheckListCA").style.display='none';
	//document.getElementById("btnAddNewCheckListCA").disabled=true;
}

function displayChecklistCA(obj, tblId ,rowCnt){
	
	var label=document.getElementById("txtChecklistNameCA").value;
	if(label!=""){
		var colCnt = document.getElementById("hidCheckListsCA").value;
		var newRow =  document.getElementById("tblChecklistCA").insertRow(document.getElementById("tblChecklistCA").rows.length);	
		var rowID = showRowCell(obj);		
		var tbl = document.getElementById(tblId); 
		tbl.deleteRow(rowID);
		var Cell1 = newRow.insertCell(0);
		Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="'+label+'">'+label +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblChecklistCA")\'/>';
		document.getElementById("btnAddNewCheckListCA").style.display='';
	}else{
		alert('Please Enter Document Name');
	}			
}



var sancAmountFlag = 0;
function actionOnOtherReasonNRA(){
	var purpose = document.getElementById('cmblstPurposeCategory2').value;
	if (purpose == "800026"){
		document.getElementById('textareaOther2').readOnly=false;
		document.getElementById('cmblstSubType').disabled = 'disabled';
	}
	else{
		document.getElementById('textareaOther2').readOnly=true;
		if(purpose != "800024" && purpose !="800025"){
			document.getElementById('cmblstSubType').disabled = 'disabled';
		}
	}
	enableInstlmnt();
	if(purpose !="800025"){
		checkEligibleAmountNRA();
	}
	
}
function checkEligibleAmountNRA(){
	
	var basicPay = document.getElementById("hidBasicPay").value;
	var accBalance = document.getElementById("hidNetBalance").value;
	var purpose = document.getElementById("cmblstPurposeCategory2").value;
	var totalAmount = Number(basicPay)*6;
	var halfAccBal = Number(accBalance)/2;
	if(document.getElementById("hidFinalEligibility").value == "true"){
		document.getElementById("txtEligibleAmtNRA").value=accBalance*(0.9);
	}
	else if(document.getElementById("cbSpecialCaseNRA").checked && purpose!=800025 && purpose!=800026){
		document.getElementById("txtEligibleAmtNRA").value=accBalance*(0.75);
	}
	else{
		if(purpose!="800025"){
			if(Number(totalAmount)<Number(halfAccBal)){
				document.getElementById("txtEligibleAmtNRA").value = totalAmount;
			}
			else{
				document.getElementById("txtEligibleAmtNRA").value = halfAccBal;
			}
		}
		else{
			var purposeSubType = document.getElementById("cmblstSubType").value;
			var temp;
			if(purposeSubType != "-1"){
			
			if(purposeSubType == "800017"){
			if(10000<Number(accBalance)/2){
					temp = 10000;
				}
				else{
					temp = Number(accBalance)/2;
				}
			}
			if(purposeSubType == "800018"){
				if(50000<Number(accBalance)/2){
					temp = 50000;
				}
				else{
					temp = Number(accBalance)/2;
				}
			}
			if(purposeSubType == "800019"){
				temp = 5000;
			}
			document.getElementById("txtEligibleAmtNRA").value = temp;
			}
		}
	}
	popupSancDetailsForNRA();
}
function popupSancDetailsForNRA(){
	var advanceAmount = document.getElementById("txtAdvanceAmount2").value;
	var accBalance = document.getElementById("hidNetBalance").value;
	var eligibleAmount = document.getElementById("txtEligibleAmtNRA").value;
	
	if(advanceAmount != "" ){
		if(Number(advanceAmount)>Number(accBalance)){
			alert("Advance Amount can not be greater than GPF account balance");
			document.getElementById("txtAdvanceAmount2").value = "";
			return;
		}
		var purpose = document.getElementById("cmblstPurposeCategory2").value;
		if(eligibleAmount == "" || eligibleAmount == null){

			if(document.getElementById("hidFinalEligibility").value == "true"){
				eligibleAmount=accBalance*(0.9);
			}else if(document.getElementById("cbSpecialCaseNRA").checked && purpose!=800025 && purpose!=800026){
				eligibleAmount=accBalance*(0.75);
			}else{
				var basicPay = document.getElementById("hidBasicPay").value;
	
				var totalAmount = Number(basicPay)*6;
				var halfAccBalance = Number(accBalance) /2; 
				if(Number(totalAmount)<Number(halfAccBalance)){
					eligibleAmount = totalAmount;
				}
				else{
					eligibleAmount = halfAccBalance;
				}
			}
		}
		if(Number(advanceAmount)<Number(eligibleAmount)){
			document.getElementById("txtSancAmountNRA").value = advanceAmount;
			document.getElementById("txtOutstandingBalanceNRA").value = document.getElementById("hidNetBalance").value - advanceAmount;
			sancAmountFlag = 0;
		}
		else{
			document.getElementById("txtSancAmountNRA").value = eligibleAmount;
			alert("As per the rules, employee is not eligible to take advance more than "+eligibleAmount);
			//document.getElementById("txtAdvanceAmount2").value = eligibleAmount;

			document.getElementById("txtOutstandingBalanceNRA").value = document.getElementById("hidNetBalance").value - eligibleAmount;
			sancAmountFlag = 1;
		}
	}else if(eligibleAmount != 0){
		document.getElementById("txtSancAmountNRA").value = eligibleAmount;
		document.getElementById("txtOutstandingBalanceNRA").value = document.getElementById("hidNetBalance").value - eligibleAmount;
		sancAmountFlag = 1;
	}
}
function validateSancAmountNRA(){
	var sancAmount = document.getElementById("txtSancAmountNRA").value;
	var reqAmount =document.getElementById("txtAdvanceAmount2").value;
//	if(Number(reqAmount) > Number(sancAmount)){
//		alert("You are not allowed to decrease the sanctioned amount.");
//		document.getElementById("txtSancAmountNRA").value = document.getElementById("txtAdvanceAmount2").value;
//	}else{
		 
		var diff =  Number(sancAmount) - Number(reqAmount);
		document.getElementById("txtOutstandingBalanceNRA").value = Number(document.getElementById("txtOutstandingBalanceNRA").value) - Number(diff);
//	}
}
//function checkEligibleAmountMotorAdvance(){
//	var purpose = document.getElementById("cmblstSubType").value;
//	var outstandingBal = 60000;
//	var advanceAmount = document.getElementById("txtAdvanceAmount2").value;
//	
//	var temp;
//	
//	if(purpose == "800017"){
//		if(10000<Number(outstandingBal)/2){
//			temp = 10000;
//		}
//		else{
//			temp = Number(outstandingBal)/2;
//		}
//	
//		if(advanceAmount>temp){
//			document.getElementById("txtAdvanceAmount2").value ="";
//			alert('Requested amount is greater than the allowed amount for 2 Wheeler(Motor Cycle/Scooter)');
//		}
//	}
//	if(purpose == "800018"){
//		if(50000<Number(outstandingBal)/2){
//			temp = 50000;
//		}
//		else{
//			temp = Number(outstandingBal)/2;
//		}
//		
//		if(advanceAmount>temp){
//			document.getElementById("txtAdvanceAmount2").value ="";
//			alert('Requested amount is greater than the allowed amount for 4 Wheeler(Motor Car)');
//		}
//	}
//	if(purpose == "800019"){
//		if(advanceAmount>5000){
//			document.getElementById("txtAdvanceAmount2").value ="";
//			alert('Requested amount is greater than the allowed amount for Overhauling or repair of motor car');
//		}
//	}
//}
function validateAppDateNRA(){
	
	var purpose = document.getElementById("cmblstPurposeCategory2").value;
	var purposeSubType = document.getElementById("cmblstSubType").value;
	
	var combo1 = document.getElementById("cmblstSubType"); 
	var cmbChanges = combo1.options[combo1.selectedIndex].text;
	var combo2 = document.getElementById("cmblstPurposeCategory2"); 
	var cmbChanges2 = combo2.options[combo2.selectedIndex].text;
	
	lAppDate = document.getElementById("txtSysDate2").value;
	var lArrAppDate = lAppDate.split("/");
	var appdate = new Date(lArrAppDate[1] + "/" + lArrAppDate[0] + "/" + lArrAppDate[2]);
	if(purposeSubType != "-1"){
	if(purpose=="800024"){
		
		if(purposeSubType == "800011" || purposeSubType == "800012" || purposeSubType == "800013" || purposeSubType == "800014"){
			getApplicationDate("hidDateOfJoin","hidDateToCompare",10);
			var lStrDateDOJ = document.getElementById("hidDateToCompare").value;
			var lArrDateDOJ = lStrDateDOJ.split("/");
			var dateDOJ = new Date(lArrDateDOJ[1] + "/" + lArrDateDOJ[0] + "/" + lArrDateDOJ[2]);
			if(appdate<dateDOJ){
				alert('Employee will be eligible to take Non-refundable advance for '+cmbChanges+', only after completion of 10 years of service');
				return false;
			}
		}
		if(purposeSubType == "800015"){
			getApplicationDate("hidDateOfSuperAnnuation","hidDateToCompare",-1);
			var lStrDateDOS = document.getElementById("hidDateToCompare").value;
			var lArrDateDOS = lStrDateDOS.split("/");
			var dateDOS = new Date(lArrDateDOS[1] + "/" + lArrDateDOS[0] + "/" + lArrDateDOS[2]);
			
			var lStrDate = document.getElementById("hidDateOfSuperAnnuation").value;
			var lArrDate = lStrDate.split("/");
			var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
			if(appdate<dateDOS || appdate>date){
				alert('Employee will be eligible to take Non-refundable advance for '+cmbChanges+', Within one year before the date of superannuation');
				return false;
			}
		}
		
		
	}
	else if(purpose=="800025"){
		if(purposeSubType == "800017" || purposeSubType == "800018"){
			getApplicationDate("hidDateOfJoin","hidDateToCompare",15);
			var lStrDateDOJ = document.getElementById("hidDateToCompare").value;
			var lArrDateDOJ = lStrDateDOJ.split("/");
			var dateDOJ = new Date(lArrDateDOJ[1] + "/" + lArrDateDOJ[0] + "/" + lArrDateDOJ[2]);
					
			if(appdate<dateDOJ){
				alert('Employee will be eligible to take Non-refundable advance for '+cmbChanges+', only after completion of 15 years of service');
				return false;
			}
		}
		if(purposeSubType == "800019"){
			getApplicationDate("hidDateOfJoin","hidDateToCompare",28);
			var lStrDateDOJ = document.getElementById("hidDateToCompare").value;
			var lArrDateDOJ = lStrDateDOJ.split("/");
			var dateDOJ = new Date(lArrDateDOJ[1] + "/" + lArrDateDOJ[0] + "/" + lArrDateDOJ[2]);
			getApplicationDate("hidDateOfSuperAnnuation","hidDateToCompare",-3);
			lStrDateDOS = document.getElementById("hidDateToCompare").value;
			lArrDateDOS = lStrDateDOS.split("/");
			dateDOS = new Date(lArrDateDOS[1] + "/" + lArrDateDOS[0] + "/" + lArrDateDOS[2]);
			var tempDate = new Date();
			if(dateDOJ>dateDOS){
				tempDate = dateDOS;
			}
			else{
				tempDate = dateDOJ;
			}
			if(appdate<tempDate){
				alert('Employee will be eligible to take Non-refundable advance for '+cmbChanges+', only after completion of 28 years of service or within 3 years of retirement (whichever is earlier)');
				return false;
			}
		}
		
	}
	else if(purpose=="800021" ||purpose=="800022" ||purpose=="800023"){
		getApplicationDate("hidDateOfJoin","hidDateToCompare",20);
		var lStrDateDOJ = document.getElementById("hidDateToCompare").value;
		var lArrDateDOJ = lStrDateDOJ.split("/");
		var dateDOJ = new Date(lArrDateDOJ[1] + "/" + lArrDateDOJ[0] + "/" + lArrDateDOJ[2]);
		
		if(appdate<dateDOJ){
			alert('Employee will be eligible to take Non-refundable advance for '+cmbChanges2+', only after completion of 20 years of service');
			return false;
		}
	}
	}
	return true;
}
function getApplicationDate(sourceFieldId,targetFieldId,value)
{
	lStrDate = document.getElementById(sourceFieldId).value;
	
	var lArrDate = lStrDate.split("/");
	
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	
	if(lStrDate == "")
	{
		document.getElementById(targetFieldId).value = (new String(""));
		return;
	}
	if (value=="-1"){
			date.setFullYear(date.getFullYear()-1);
	}
	else if (value=="-3"){
		date.setFullYear(date.getFullYear()-3);
	}
	else{
			date.setFullYear(date.getFullYear()+value);
	}
	
	if(lArrDate[0] == 1)
	{
		
		var x;
		if(lArrDate[1] == 1)
		{
	
			x = DaysArray2(12 ,lArrDate[2]-1);
			lArrDate[1] = 13;
			date.setFullYear(date.getFullYear()-1);
		}
		else
		{
	
			if(lArrDate[1]-1 == 2 )
			{
				x = daysInFebruaryFP(lArrDate[2]);
			}
			else
			{
				x = DaysArrayFP(lArrDate[2]);
			}
		}
		
		document.getElementById(targetFieldId).value =  (new String(x).length > 1 ? x : "0" + new String(x)) + "/" +(new String(lArrDate[1] -1).length > 1 ? lArrDate[1] -1 : "0"+new String(lArrDate[1] -1))+"/"+date.getFullYear();
		
	}
	else
	{	
				
		document.getElementById(targetFieldId).value = (new String(lArrDate[0]-1).length > 1 ? lArrDate[0]-1 : "0" + new String(lArrDate[0]-1)) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + new String(lArrDate[1]) )+"/"+date.getFullYear();
		
	}
	
}
function DaysArray2(m,y)
{
	var days = '';
	if (m==4 || m==6 || m==9 || m==11)
	{
		days = 30;
	}
	else
	if(m == 1)
	{
		days = daysInFebruary2(y)
	}
	else
	{
		days = 31;
	}
		return days;
}

function daysInFebruary2(year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function daysInFebruaryFP (year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArrayFP(n)
{
	var daysFp;
	for (var i = 1; i <= n; i++)
	{
		if(i != 2)
		{
			daysFp = 31
		}
		if (i==4 || i==6 || i==9 || i==11)
		{
			daysFp = 30
		}
	 }
}
function validateInstallmentNoNRA(){
	var inst=document.getElementById("txtSancPayInstNo").value;
	if(inst<2 || inst>4){
		alert("Sanctioned No Of Installments should be between 2 and 4");
		document.getElementById("txtSancPayInstNo").value="";
	}else if(inst =="2"){
		document.getElementById("txtInstallment3").readOnly=true;
		document.getElementById("txtReleaseDate3").readOnly=true;
		document.getElementById("txtInstallment4").readOnly=true;
		document.getElementById("txtReleaseDate4").readOnly=true;
	}else if(inst =="3"){
		document.getElementById("txtInstallment3").readOnly=false;
		document.getElementById("txtReleaseDate3").readOnly=false;
		document.getElementById("txtInstallment4").readOnly=true;
		document.getElementById("txtReleaseDate4").readOnly=true;
	}else if(inst =="4"){
		document.getElementById("txtInstallment3").readOnly=false;
		document.getElementById("txtReleaseDate3").readOnly=false;
		document.getElementById("txtInstallment4").readOnly=false;
		document.getElementById("txtReleaseDate4").readOnly=false;
	}
}

function enableInstlmnt()
{
	var purpose = document.getElementById("cmblstPurposeCategory2").value;
	
	if(purpose=="800024")
	{
		document.getElementById("Installment").style.display = "";
		document.getElementById("Installment1").style.display = "";
		document.getElementById("Installment2").style.display = "";
	}
	else
	{
		document.getElementById("Installment").style.display = "none";
		document.getElementById("Installment1").style.display = "none";
		document.getElementById("Installment2").style.display = "none";
	}
}

function compareDates(Date1,Date2,flag)
{
	var one_day = 1000*60*60*24; 

	var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    
    var date1 = new Date(la_Date1[2],la_Date1[1]-1,la_Date1[0]);
    var date2 = new Date(la_Date2[2],la_Date2[1]-1,la_Date2[0]);
    
	var Diff = Math.floor((date2.getTime() - date1.getTime())/(one_day)); 
	
	if(flag == '=' && Diff == 0){
		return true;
	}

    else if( (flag == '<' && Diff<=0) || (flag == '>' && Diff>=0))
    {
        return false;
    }
    else {
    	return true;
    }
}


function validateReleaseDates()
{	
	for(var lIntCnt=1;lIntCnt<4;lIntCnt++)
	{
		//if(document.getElementById("txtReleaseDate"+lIntCnt).value != "" && document.getElementById("txtReleaseDate"+(lIntCnt +1)).value != "")
		//{
			if(compareDates(document.getElementById("txtReleaseDate"+(lIntCnt +1)).value,document.getElementById("txtReleaseDate"+lIntCnt).value,'>') == false)
			{
				alert("Release Date"+(lIntCnt+1)+" should be greater than Release Date"+lIntCnt);
				document.getElementById("txtReleaseDate"+(lIntCnt+1)).value = "";
				break;
			}
		//}
	}
		
}

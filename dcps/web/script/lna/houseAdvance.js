function checkEligibleAmountHBA(){
	var reqAmount = document.getElementById("txtReqAmountHBA").value;
	var loanRePayAmt = document.getElementById("txtLoanRepaymentCapacity").value;
	var basic = document.getElementById("txtBasicPay").value;
	var basicPlusDP = (Number(basic)+Number(basic)*0.5)*50;
	
	if(reqAmount != "" && loanRePayAmt!=""){
		if(Number(reqAmount)<Number(loanRePayAmt)){
			if(Number(reqAmount)<Number(basicPlusDP)){
					document.getElementById("txtEligibleAmtHBA").value = reqAmount;
			}else{
					document.getElementById("txtEligibleAmtHBA").value = basicPlusDP;
			}
		}else if(Number(loanRePayAmt)<Number(basicPlusDP)){
				document.getElementById("txtEligibleAmtHBA").value = loanRePayAmt;
		}else{
				document.getElementById("txtEligibleAmtHBA").value = basicPlusDP;
		}
	}
}
function popupSancDtlsHBA(flag){
	
	var reqAmount;
	var maxSancAmt;
	if(flag==1){
		reqAmount = document.getElementById("txtReqAmountHBA");
	}else if (flag==2){
		reqAmount = document.getElementById("txtSanctionedAmountHBA2");		
	}else if (flag==3){
		reqAmount = document.getElementById("txtSanctionedAmountHBA");		
	}
	var loanRePayAmt = document.getElementById("txtLoanRepaymentCapacity").value;
	var basic = document.getElementById("txtBasicPay").value;
	var basicAndDP = (Number(basic)+Number(basic)*0.5)*50;
	var reqType=document.getElementById("cmbHBAType").value;
	if(reqType==800037 || reqType==800060){
		maxSancAmt = 180000; 
	}else if(reqType==800059){
		maxSancAmt = 90000;
	}else{ 
		maxSancAmt = 750000;
	}
	if(reqAmount.value != "" && loanRePayAmt!=""){
		if(Number(reqAmount.value)<Number(loanRePayAmt)){
			if(Number(reqAmount.value)<Number(basicAndDP)){
				if(reqType==800038||reqType==800058){
					document.getElementById("txtSanctionedAmountHBA2").value = reqAmount.value;
				}else{
					document.getElementById("txtSanctionedAmountHBA").value = reqAmount.value;
				}
			}else{
				if(reqType==800038||reqType==800058){
					document.getElementById("txtSanctionedAmountHBA2").value = basicAndDP;
				}else{
					document.getElementById("txtSanctionedAmountHBA").value = basicAndDP;
				}
			}
		}else if(Number(loanRePayAmt)<Number(basicAndDP)){
			if(reqType==800038||reqType==800058){
				document.getElementById("txtSanctionedAmountHBA2").value = Math.round(loanRePayAmt);
			}else{
				document.getElementById("txtSanctionedAmountHBA").value = Math.round(loanRePayAmt);
			}
		}else{
			if(reqType==800038||reqType==800058){
				document.getElementById("txtSanctionedAmountHBA2").value = basicAndDP;
			}else{
				document.getElementById("txtSanctionedAmountHBA").value = basicAndDP;
			}
		}
		
		if(reqType==800038||reqType==800058){
			if(document.getElementById("txtSanctionedAmountHBA2").value>maxSancAmt){
				document.getElementById("txtSanctionedAmountHBA2").value = maxSancAmt;
			}
		}else{
			if(document.getElementById("txtSanctionedAmountHBA").value>maxSancAmt){
				document.getElementById("txtSanctionedAmountHBA").value = maxSancAmt;
			}
		}
	}	
	var sancAmount=document.getElementById("txtSanctionedAmountHBA2").value;
	if(sancAmount!=""){
		document.getElementById("txtPrincipalAmountHBA").value=sancAmount;
		if(reqType==800038){
			document.getElementById("txtDisbursement1").value=(sancAmount*0.3).toFixed(2);
			//document.getElementById("txtDisbursement2").value=(sancAmount*0.4).toFixed(2);
			//document.getElementById("txtDisbursement3").value=(sancAmount*0.3).toFixed(2);
		}else if(reqType==800058){
			document.getElementById("txtDisbursement1").value=(sancAmount*0.25).toFixed(2);
			//document.getElementById("txtDisbursement2").value=(sancAmount*0.25).toFixed(2);
			//document.getElementById("txtDisbursement3").value=(sancAmount*0.4).toFixed(2);
			//document.getElementById("txtDisbursement4").value=(sancAmount*0.1).toFixed(2);
		}
	}
	var sanctionAmount;
	if(reqType==800038 || reqType==800058){
		sanctionAmount = document.getElementById("txtSanctionedAmountHBA2").value;
	}else{
		sanctionAmount = document.getElementById("txtSanctionedAmountHBA").value;
	}
	if(reqAmount.value != "" && loanRePayAmt!=""){
		if(Number(sanctionAmount)<=50000){
			document.getElementById("txtInterestRateHBA").value = 5;
		}else if(Number(sanctionAmount)>50000 && Number(sanctionAmount)<=150000){
			document.getElementById("txtInterestRateHBA").value = 6.5;
		}else if(Number(sanctionAmount)>150000 && Number(sanctionAmount)<=500000){
			document.getElementById("txtInterestRateHBA").value = 8.5;
		}else if(Number(sanctionAmount)<=750000){
			document.getElementById("txtInterestRateHBA").value = 9.5;
		}
	}
}
function validateReqAmountHBA(flag){
	
	var reqType = document.getElementById("cmbHBAType").value;
	var reqAmount;
	if(flag==1){
		reqAmount = document.getElementById("txtReqAmountHBA");
	}else if (flag==2){
		reqAmount = document.getElementById("txtSanctionedAmountHBA");
	}else if (flag==3){
		reqAmount = document.getElementById("txtSanctionedAmountHBA2");		
	}
	if(reqAmount.value!=""){
		if(reqType==800037){
			if(reqAmount.value>180000){
				alert('For Plot Purchase Advance Amount should be less than or Equals to 1,80,000');
				return false;
			}
		}else if(reqType==800038){
			if(reqAmount.value>750000){
				alert('For Consrtuction of Flat/House Advance Amount should be less than or Equals to 7,50,000');
				return false;
			}
		}else if(reqType==800039){
			if(reqAmount.value>750000){
				alert('For Ready Build Flat/House Purchase Advance Amount should be less than or Equals to 7,50,000');
				return false;
			}
		}else if(reqType==800041){
			if(reqAmount.value>750000){
				alert('For Old Flat/House Advance Amount should be less than or Equals to 7,50,000');
				return false;
			}
		}else if(reqType==800042){
			if(reqAmount.value>750000){
				alert('For Bank Loan Repayment Advance Amount should be less than or Equals to 7,50,000');
				return false;
			}
		}else if(reqType==800058){
			if(reqAmount.value>750000){
				alert('For Plot Purchase and Later Construction Advance Amount should be less than or Equals to 7,50,000');
				return false;
			}
		}else if(reqType==800059){
			if(reqAmount.value>90000){
				alert('For Special Repairs Advance Amount should be less than or Equals to 90,000');
				return false;
			}
		}else if(reqType==800060){
			if(reqAmount.value>180000){
				alert('For Extension of Rooms Advance Amount should be less than or Equals to 1,80,000');
				return false;
			}
		}
		if(flag!=1){
			var loanRepayAmt = document.getElementById("txtLoanRepaymentCapacity").value
			if(reqAmount.value>Number(loanRepayAmt)){
				alert('Sanction Amount is greater than Loan Repayment Capacity of this Employee');
				reqAmount.value="";
				reqAmount.focus();
			}	
			var basic = document.getElementById("txtBasicPay").value;
			var basicAndDP = (Number(basic)+Number(basic)*0.5)*50;
			if(reqAmount.value>Number(basicAndDP)){
				alert('Maximum Sanction Amount for this Employee is '+basicAndDP +'\n Sanction Amount is Greater than this Amount');
				reqAmount.value="";
				reqAmount.focus();
			}	
		}
	}
	document.getElementById("txtPrincipalAmountHBA").value = reqAmount.value;
	return true;
}
function popupLoanRepayCapacity(){
	
	var basicPay = document.getElementById("txtBasicPay").value;
	var appDate = document.getElementById("txtAppDateHBA").value;
	var DOS = document.getElementById("hidDateOfSuperAnnuation").value;
	var DOJ = document.getElementById("hidDateOfJoining").value;
	var requestedAmount = document.getElementById("txtReqAmountHBA").value;
	var lArrDate = DOS.split("/");	
	var dateofSuperAnnu = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	var lArrDOJ = DOJ.split("/");
	var dateOfJoing = new Date(lArrDOJ[1] + "/" + lArrDOJ[0] + "/" + lArrDOJ[2]);
	var lArrAppDate = appDate.split("/");
	if(lArrAppDate[1]==12){
		lArrAppDate[2]++;
		lArrAppDate[1] = 1;
	}else{
		lArrAppDate[1]++;
	}
	var dtAppDate = new Date(lArrAppDate[1] + "/" + lArrAppDate[0] + "/" + lArrAppDate[2]);
	var one_day=1000*60*60*24;
	var dayDiff = Math.ceil((dateofSuperAnnu.getTime() - dateOfJoing.getTime())/Number(one_day));
	var halfYearlyPenService = Math.floor(dayDiff/180);
	var leftDays= dayDiff%180;
	if(leftDays>=75){
		halfYearlyPenService++;
	}
	var totalMonthOfservice=dateDiffInMonth(dtAppDate, dateofSuperAnnu);
	var DCRG = (Number(basicPay)*Number(halfYearlyPenService))/4;
	var loanRepayAmt;
    if(requestedAmount!="" && basicPay!="" && appDate!=""){
		if(totalMonthOfservice>240){
			loanRepayAmt = Number(basicPay)*0.35*Number(totalMonthOfservice);
		}else if(totalMonthOfservice>120 && totalMonthOfservice<=240){
			loanRepayAmt = (Number(basicPay)*0.40*Number(totalMonthOfservice))-(Number(DCRG)*0.65);
		}else if(totalMonthOfservice<=120){
			loanRepayAmt = (Number(basicPay)*0.50*Number(totalMonthOfservice))-(Number(DCRG)*0.75);
		}
		if(loanRepayAmt<0){
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
			alert('For this Applicant Loan Repayment Capacity is Negative so Applicant is not Eligible For House Building Advance');
			self.location.href="ifms.htm?actionFlag=loadLNASearchForm&userType=HODASST2&elementId=800025";
		}else{
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
		}
		
    }
}
function validateNoOfInstallmentsHBA(){
	document.getElementById("txtPrinInstallmentAmountHBA").value = "";
	document.getElementById("txtOddPrincipalInstallmentAmtHBA").value = "";
	document.getElementById("txtOddInterestInstallmentAmtHBA").value = "";
	document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value = "";	
	document.getElementById("txtPrincipalInstallmentAmtHBA").value = "";
	document.getElementById("txtInterInstallmentAmountHBA").value = "";	
	document.getElementById("txtInterestAmountHBA").value="";
	
	var reqType=document.getElementById("cmbHBAType").value;
	var sancPrinInst;
	var sancInterInst;
	var lStrReleaseDate1;
	var lStrReleaseDate2;
	var lStrReleaseDate3;
	var lStrReleaseDate4;
	var disbursement1;
	var disbursement2;
	var disbursement3;
	var disbursement4;
	var lArrReleaseDate1;
	var lArrReleaseDate2;
	var lArrReleaseDate3;
	var lArrReleaseDate4;
	var releaseDate1;
	var releaseDate2;
	var releaseDate3;
	var releaseDate4;	
	var noOfInstallment;
	var dateDiff1;
	var dateDiff2;
	var dateDiff3;
	var remainingInstallment;
	var installmentAmt;
	var totalAmount;
	var subPrin;
	var sancAmount;
	var inst;
	var oddValue;
	var interestRate;
	var interestAmount;
	if(reqType==800038||reqType==800058){
		sancPrinInst = document.getElementById("txtSancPrinInstallHBA2");
		sancInterInst = document.getElementById("txtSancInterInstallHBA2");
	}else{
		sancPrinInst = document.getElementById("txtSancPrinInstallHBA");
		sancInterInst = document.getElementById("txtSancInterInstallHBA");
	}
	var DOS = document.getElementById("hidDateOfSuperAnnuation").value;
	var lArrDate = DOS.split("/");	
	var dateofSuperAnnu = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	var currDate = new Date();
	var futureDate = new Date(currDate);
	futureDate.setMonth(futureDate.getMonth() + (Number(sancPrinInst.value)+Number(sancInterInst.value)));
	
	var maxInstallment=dateDiffInMonth(currDate, dateofSuperAnnu);
	 if(sancPrinInst.value!=""){
		 if(sancPrinInst.value==0){
				alert('Sanctioned No. of Principal Installments Should be greater than 0');
				sancPrinInst.value="";
				sancPrinInst.focus();
				return false;
			}
	 }
	if(sancInterInst.value!=""){
		if(sancInterInst.value==0){ 
			alert('Sanctioned No. of Interest Installments Should be greater than 0');
			sancInterInst.value="";
			sancInterInst.focus();
			return false;
		}
	}
    if(sancPrinInst.value!=""||sancInterInst.value!=""){
    	if(sancPrinInst.value>192){
			alert('Sanction Installments Should be less than 192');
			sancPrinInst.value="";
			sancPrinInst.focus();
			return false;
		}
		if(sancInterInst.value>48){
			alert('Sanctioned No. of Interest Installments Should be less than 48');
			sancInterInst.value="";
			sancInterInst.focus();
			return false;
		}
		if(futureDate>dateofSuperAnnu){
			alert('Considering Retirement Date Maximum Number of Total Installments for Employee are '+maxInstallment);
			sancPrinInst.value="";
			sancInterInst.value="";
			sancPrinInst.focus();
			return false;
		}
		
		if(reqType==800038){
			var principalAmount=document.getElementById("txtPrincipalAmountHBA").value;
			noOfInstallment = Number(sancPrinInst.value);
			if(noOfInstallment != "" && principalAmount != ""){
				inst= Math.floor(Number(principalAmount)/noOfInstallment);
				oddValue = Number(principalAmount) - (inst*(noOfInstallment-1));
				if (inst==oddValue){
					document.getElementById("txtPrinInstallmentAmountHBA").value = inst;
					document.getElementById("cmbOddPrincipalInstallNoHBA2").value = -1;
					document.getElementById("cmbOddPrincipalInstallNoHBA2").disabled = true;
				} else{
					document.getElementById("txtPrinInstallmentAmountHBA").value = inst;
					document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value = oddValue;
					document.getElementById("cmbOddPrincipalInstallNoHBA2").disabled = false;
				}
			}
			lStrReleaseDate1=document.getElementById("txtReleaseDate1").value;
			lStrReleaseDate2=document.getElementById("txtReleaseDate2").value;
			lStrReleaseDate3=document.getElementById("txtReleaseDate3").value;
			
			if(lStrReleaseDate1!=""&&lStrReleaseDate2!=""&&lStrReleaseDate3!=""){
			
				lArrReleaseDate1 = lStrReleaseDate1.split("/");
				releaseDate1 = new Date(lArrReleaseDate1[1] + "/" + lArrReleaseDate1[0] + "/" + lArrReleaseDate1[2]);
							
				lArrReleaseDate2 = lStrReleaseDate2.split("/");
				releaseDate2 = new Date(lArrReleaseDate2[1] + "/" + lArrReleaseDate2[0] + "/" + lArrReleaseDate2[2]);
							
				lArrReleaseDate3 = lStrReleaseDate3.split("/");
				releaseDate3 = new Date(lArrReleaseDate3[1] + "/" + lArrReleaseDate3[0] + "/" + lArrReleaseDate3[2]);
				
				dateDiff1 = dateDiffInMonth(releaseDate1, releaseDate2);
				dateDiff2 = dateDiffInMonth(releaseDate2, releaseDate3);
				remainingInstallment = Number(noOfInstallment)-(Number(dateDiff1)+Number(dateDiff2));
			
				dateDiff1 = Number(dateDiff1)-2;
								
				disbursement1=document.getElementById("txtDisbursement1").value;
				disbursement2=document.getElementById("txtDisbursement2").value;
				disbursement3=document.getElementById("txtDisbursement3").value;
						
				installmentAmt=document.getElementById("txtPrinInstallmentAmountHBA").value;
				totalAmount = disbursement1;
				subPrin = Number(disbursement1)*2;
	
				while(Number(dateDiff1) > 0)
				{
					totalAmount = Number(totalAmount)-Number(installmentAmt);
					subPrin = Number(subPrin) + Number(totalAmount);
					dateDiff1--;				
				}				
				totalAmount = Number(totalAmount) + Number(disbursement2) - Number(installmentAmt);
				subPrin = Number(subPrin) + Number(totalAmount);
				
				while(Number(dateDiff2) > 1)
				{
					totalAmount = Number(totalAmount)-Number(installmentAmt);
					subPrin = Number(subPrin) + Number(totalAmount);				
					dateDiff2--;				
				}
				
				totalAmount = Number(totalAmount) + Number(disbursement3) - Number(installmentAmt);
				subPrin = Number(subPrin) + Number(totalAmount);
				
				while(remainingInstallment >= 0)
				{
					totalAmount = Number(totalAmount)-Number(installmentAmt);
					subPrin = Number(subPrin) + Number(totalAmount);
					remainingInstallment--;
				}
				interestRate = document.getElementById("txtInterestRateHBA").value;
				interestAmount = Math.round((Number(subPrin)/12)*(Number(interestRate)/100));
				document.getElementById("txtInterestAmountHBA2").value = interestAmount;
				noOfInstallment = Number(sancInterInst.value);
				sancAmount=document.getElementById("txtSanctionedAmountHBA2").value;
				if(sancAmount != "" && sancPrinInst.value != "" && sancInterInst.value != ""){
					inst= Math.floor(Number(interestAmount)/noOfInstallment);
					oddValue = Number(interestAmount) - (inst*(noOfInstallment-1));
					if (inst==oddValue){
						/*document.getElementById("txtInterInstallmentAmountHBA2").value = inst;
						document.getElementById("cmbOddInterestInstallNoHBA2").value = -1;
						document.getElementById("cmbOddInterestInstallNoHBA2").disabled = true;*/
					} else{
						/*document.getElementById("txtInterInstallmentAmountHBA2").value = inst;
						document.getElementById("txtOddInterestInstallmentAmtHBA2").value = oddValue;
						document.getElementById("cmbOddInterestInstallNoHBA2").disabled = false;*/
					}
				}
			}
		}else if(reqType==800058){
			principalAmount=document.getElementById("txtPrincipalAmountHBA").value;
			noOfInstallment = Number(sancPrinInst.value);
			if(noOfInstallment != "" && principalAmount != ""){
				inst= Math.floor(Number(principalAmount)/noOfInstallment);
				oddValue = Number(principalAmount) - (inst*(noOfInstallment-1));
				if (inst==oddValue){
					document.getElementById("txtPrinInstallmentAmountHBA").value = inst;
					document.getElementById("cmbOddPrincipalInstallNoHBA2").value = -1;
					document.getElementById("cmbOddPrincipalInstallNoHBA2").disabled = true;
				} else{
					document.getElementById("txtPrinInstallmentAmountHBA").value = inst;
					document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value = oddValue;
					document.getElementById("cmbOddPrincipalInstallNoHBA2").disabled = false;
				}
			}
			lStrReleaseDate1=document.getElementById("txtReleaseDate1").value;
			lStrReleaseDate2=document.getElementById("txtReleaseDate2").value;
			lStrReleaseDate3=document.getElementById("txtReleaseDate3").value;
			lStrReleaseDate4=document.getElementById("txtReleaseDate4").value;
			
			if(lStrReleaseDate1!=""&&lStrReleaseDate2!=""&&lStrReleaseDate3!=""&&lStrReleaseDate4!=""){
				lArrReleaseDate1 = lStrReleaseDate1.split("/");
				releaseDate1 = new Date(lArrReleaseDate1[1] + "/" + lArrReleaseDate1[0] + "/" + lArrReleaseDate1[2]);
							
				lArrReleaseDate2 = lStrReleaseDate2.split("/");
				releaseDate2 = new Date(lArrReleaseDate2[1] + "/" + lArrReleaseDate2[0] + "/" + lArrReleaseDate2[2]);
							
				lArrReleaseDate3 = lStrReleaseDate3.split("/");
				releaseDate3 = new Date(lArrReleaseDate3[1] + "/" + lArrReleaseDate3[0] + "/" + lArrReleaseDate3[2]);
							
				lArrReleaseDate4 = lStrReleaseDate4.split("/");
				releaseDate4 = new Date(lArrReleaseDate4[1] + "/" + lArrReleaseDate4[0] + "/" + lArrReleaseDate4[2]);
				
				dateDiff1 = dateDiffInMonth(releaseDate1, releaseDate2);
				dateDiff2 = dateDiffInMonth(releaseDate2, releaseDate3);
				dateDiff3 = dateDiffInMonth(releaseDate3, releaseDate4);
				remainingInstallment = Number(noOfInstallment)-(Number(dateDiff1)+Number(dateDiff2)+Number(dateDiff3));
				
				dateDiff1 = Number(dateDiff1)-2;
								
				disbursement1=document.getElementById("txtDisbursement1").value;
				disbursement2=document.getElementById("txtDisbursement2").value;
				disbursement3=document.getElementById("txtDisbursement3").value;
				disbursement4=document.getElementById("txtDisbursement4").value;
				
				installmentAmt=document.getElementById("txtPrinInstallmentAmountHBA").value;
				totalAmount = disbursement1;
				subPrin = Number(disbursement1)*2;
				
				while(Number(dateDiff1) > 0)
				{
					totalAmount = Number(totalAmount)-Number(installmentAmt);
					subPrin = Number(subPrin) + Number(totalAmount);
					dateDiff1--;				
				}				
				totalAmount = Number(totalAmount) + Number(disbursement2) - Number(installmentAmt);
				subPrin = Number(subPrin) + Number(totalAmount);
				
				while(Number(dateDiff2) > 1)
				{
					totalAmount = Number(totalAmount)-Number(installmentAmt);
					subPrin = Number(subPrin) + Number(totalAmount);
					dateDiff2--;				
				}
				
				totalAmount = Number(totalAmount) + Number(disbursement3) - Number(installmentAmt);
				subPrin = Number(subPrin) + Number(totalAmount);
				while(Number(dateDiff3) > 1)
				{
					totalAmount = Number(totalAmount)-Number(installmentAmt);
					subPrin = Number(subPrin) + Number(totalAmount);
					dateDiff3--;				
				}
				
				totalAmount = Number(totalAmount) + Number(disbursement4) - Number(installmentAmt);
				subPrin = Number(subPrin) + Number(totalAmount);
				
				while(remainingInstallment >= 0)
				{
					totalAmount = Number(totalAmount)-Number(installmentAmt);
					subPrin = Number(subPrin) + Number(totalAmount);
					remainingInstallment--;
				}			
				interestRate = document.getElementById("txtInterestRateHBA").value;
				interestAmount = Math.round((Number(subPrin)/12)*(Number(interestRate)/100));
				document.getElementById("txtInterestAmountHBA2").value = interestAmount;
				noOfInstallment = Number(sancInterInst.value);
				sancAmount=document.getElementById("txtSanctionedAmountHBA2").value;
				if(sancAmount != "" && sancPrinInst.value != "" && sancInterInst.value != ""){
					inst= Math.floor(Number(interestAmount)/noOfInstallment);
					oddValue = Number(interestAmount) - (inst*(noOfInstallment-1));
					if (inst==oddValue){
						/*document.getElementById("txtInterInstallmentAmountHBA2").value = inst;
						document.getElementById("cmbOddInterestInstallNoHBA2").value = -1;
						document.getElementById("cmbOddInterestInstallNoHBA2").disabled = true;*/
					} else{
						/*document.getElementById("txtInterInstallmentAmountHBA2").value = inst;
						document.getElementById("txtOddInterestInstallmentAmtHBA2").value = oddValue;
						document.getElementById("cmbOddInterestInstallNoHBA2").disabled = false;*/
					}
				}
			}
		}else{
			sancAmount=document.getElementById("txtSanctionedAmountHBA").value;
			noOfInstallment = Number(sancPrinInst.value);
			if(sancAmount != "" && sancPrinInst.value != "" && sancInterInst.value != ""){
				inst= Math.floor(sancAmount/noOfInstallment);
				oddValue = sancAmount - (inst*(noOfInstallment-1));
				if (inst==oddValue){
					document.getElementById("txtPrincipalInstallmentAmtHBA").value = inst;
					document.getElementById("cmbOddPrincipalInstallNoHBA").value = -1;
					document.getElementById("cmbOddPrincipalInstallNoHBA").disabled = true;
				} else{
					document.getElementById("txtPrincipalInstallmentAmtHBA").value = inst;
					document.getElementById("txtOddPrincipalInstallmentAmtHBA").value = oddValue;
					document.getElementById("cmbOddPrincipalInstallNoHBA").disabled = false;
				}
				var capInstAmount = document.getElementById("txtPrincipalInstallmentAmtHBA").value;
				interestRate = document.getElementById("txtInterestRateHBA").value;
				interestAmount = Math.round(Number(sancPrinInst.value)*(Number(sancPrinInst.value)+1)/2*(Number(capInstAmount)/12)*(Number(interestRate)/100));
				inst = Math.floor(Number(interestAmount)/Number(sancInterInst.value));
				oddValue = interestAmount - (inst*(sancInterInst.value-1));
				if (inst==oddValue){
					document.getElementById("txtInterInstallmentAmountHBA").value = inst;
					document.getElementById("cmbOddInterestInstallNoHBA").value = -1;
					document.getElementById("cmbOddInterestInstallNoHBA").disabled = true;
				} else{
					document.getElementById("txtInterInstallmentAmountHBA").value = inst;
					document.getElementById("txtOddInterestInstallmentAmtHBA").value = oddValue;
					document.getElementById("cmbOddInterestInstallNoHBA").disabled = false;
				}
				document.getElementById("txtInterestAmountHBA").value = interestAmount;				
			}
		}
	}
	return true;
}
function changeSancDtls(){
	var reqType=document.getElementById("cmbHBAType").value;
	
	if(reqType==800038 || reqType==800058){
		document.getElementById('tblSancDtls2').style.display = '';
		document.getElementById('tblSancDtls1').style.display = 'none';
		document.getElementById('tdHideColMsgDis2').style.display = 'none';
		document.getElementById('trHideColMsgDis3').style.display = 'none';
		document.getElementById('trHideColMsgDis4').style.display = 'none';
		document.getElementById('tdHideColMsgRel2').style.display = 'none';
		document.getElementById('tdHideColMsg2').style.display = 'none';
		document.getElementById('trHideColMsgRel3').style.display = 'none';
		document.getElementById('tdHideColDis2').style.display = 'none';
		document.getElementById('tdHideColDis3').style.display = 'none';
		document.getElementById('tdHideColDis4').style.display = 'none';
		document.getElementById('trHideColMsgRel4').style.display = 'none';
	}else{		
		document.getElementById('tblSancDtls2').style.display = 'none';
		document.getElementById('tblSancDtls1').style.display = '';
	}
	if(reqType==800037){
		document.getElementById('ChecklistHBAForPP').style.display = '';
		document.getElementById('ChecklistHBAForCF').style.display = 'none';
		document.getElementById('ChecklistHBAForRB').style.display = 'none';
		document.getElementById('ChecklistHBAForBL').style.display = 'none';
		document.getElementById('ChecklistHBAForSR').style.display = 'none';
		document.getElementById('ChecklistHBAForOF').style.display = 'none';
		document.getElementById('ChecklistHBAForLC').style.display = 'none';
		document.getElementById('ChecklistHBAForER').style.display = 'none';
		tableName='tblChecklistHBAPP';
	}else if(reqType==800041){
		document.getElementById('ChecklistHBAForPP').style.display = 'none';
		document.getElementById('ChecklistHBAForCF').style.display = 'none';
		document.getElementById('ChecklistHBAForRB').style.display = 'none';
		document.getElementById('ChecklistHBAForBL').style.display = 'none';
		document.getElementById('ChecklistHBAForSR').style.display = 'none';
		document.getElementById('ChecklistHBAForOF').style.display = '';
		document.getElementById('ChecklistHBAForLC').style.display = 'none';
		document.getElementById('ChecklistHBAForER').style.display = 'none';
		tableName='tblChecklistHBAOF';
	}else if(reqType==800038){
		document.getElementById('ChecklistHBAForPP').style.display = 'none';
		document.getElementById('ChecklistHBAForCF').style.display = '';
		document.getElementById('ChecklistHBAForRB').style.display = 'none';
		document.getElementById('ChecklistHBAForBL').style.display = 'none';
		document.getElementById('ChecklistHBAForSR').style.display = 'none';
		document.getElementById('ChecklistHBAForOF').style.display = 'none';
		document.getElementById('ChecklistHBAForLC').style.display = 'none';
		document.getElementById('ChecklistHBAForER').style.display = 'none';
		tableName='tblChecklistHBACF';
	}else if(reqType==800058){
		document.getElementById('ChecklistHBAForPP').style.display = 'none';
		document.getElementById('ChecklistHBAForCF').style.display = 'none';
		document.getElementById('ChecklistHBAForRB').style.display = 'none';
		document.getElementById('ChecklistHBAForBL').style.display = 'none';
		document.getElementById('ChecklistHBAForSR').style.display = 'none';
		document.getElementById('ChecklistHBAForOF').style.display = 'none';
		document.getElementById('ChecklistHBAForLC').style.display = '';
		document.getElementById('ChecklistHBAForER').style.display = 'none';
		tableName='tblChecklistHBALC';
	}else if(reqType==800060){
		document.getElementById('ChecklistHBAForPP').style.display = 'none';
		document.getElementById('ChecklistHBAForCF').style.display = 'none';
		document.getElementById('ChecklistHBAForRB').style.display = 'none';
		document.getElementById('ChecklistHBAForBL').style.display = 'none';
		document.getElementById('ChecklistHBAForSR').style.display = 'none';
		document.getElementById('ChecklistHBAForOF').style.display = 'none';
		document.getElementById('ChecklistHBAForLC').style.display = 'none';
		document.getElementById('ChecklistHBAForER').style.display = '';
		tableName='tblChecklistHBAER';
	}else if(reqType==800039){
		document.getElementById('ChecklistHBAForPP').style.display = 'none';
		document.getElementById('ChecklistHBAForCF').style.display = 'none';
		document.getElementById('ChecklistHBAForRB').style.display = '';
		document.getElementById('ChecklistHBAForBL').style.display = 'none';
		document.getElementById('ChecklistHBAForSR').style.display = 'none';
		document.getElementById('ChecklistHBAForOF').style.display = 'none';
		document.getElementById('ChecklistHBAForLC').style.display = 'none';
		document.getElementById('ChecklistHBAForER').style.display = 'none';
		tableName='tblChecklistHBARB';
	}else if(reqType==800042){
		document.getElementById('ChecklistHBAForPP').style.display = 'none';
		document.getElementById('ChecklistHBAForCF').style.display = 'none';
		document.getElementById('ChecklistHBAForRB').style.display = 'none';
		document.getElementById('ChecklistHBAForBL').style.display = '';
		document.getElementById('ChecklistHBAForSR').style.display = 'none';
		document.getElementById('ChecklistHBAForOF').style.display = 'none';
		document.getElementById('ChecklistHBAForLC').style.display = 'none';
		document.getElementById('ChecklistHBAForER').style.display = 'none';
		tableName='tblChecklistHBABL';
	}else if(reqType==800059){
		document.getElementById('ChecklistHBAForPP').style.display = 'none';
		document.getElementById('ChecklistHBAForCF').style.display = 'none';
		document.getElementById('ChecklistHBAForRB').style.display = 'none';
		document.getElementById('ChecklistHBAForBL').style.display = 'none';
		document.getElementById('ChecklistHBAForSR').style.display = '';
		document.getElementById('ChecklistHBAForOF').style.display = 'none';
		document.getElementById('ChecklistHBAForLC').style.display = 'none';
		document.getElementById('ChecklistHBAForER').style.display = 'none';
		tableName='tblChecklistHBASR';
	}
}
var tableName;	
function addNewCheckListHBA(){
	var rowCnt;	
	var rowElementName;
	var colElementName;
	tableName="";
	if(document.getElementById('ChecklistHBAForPP').style.display == ''){
		tableName="tblChecklistHBAPP";
		rowCnt=document.getElementById("hidRowCountPP").value;
		rowElementName="hidRowCountPP";
		colElementName="hidColCountPP";
	}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
		tableName="tblChecklistHBACF";
		rowCnt=document.getElementById("hidRowCountCF").value;
		rowElementName="hidRowCountCF";
		colElementName="hidColCountCF";
	}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
		tableName="tblChecklistHBARB";
		rowCnt=document.getElementById("hidRowCountRB").value;
		rowElementName="hidRowCountRB";
		colElementName="hidColCountRB";
	}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
		tableName="tblChecklistHBABL";
		rowCnt=document.getElementById("hidRowCountBL").value;
		rowElementName="hidRowCountBL";
		colElementName="hidColCountBL";
	}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
		tableName="tblChecklistHBASR";
		rowCnt=document.getElementById("hidRowCountSR").value;
		rowElementName="hidRowCountSR";
		colElementName="hidColCountSR";
	}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
		tableName="tblChecklistHBAOF";
		rowCnt=document.getElementById("hidRowCountOF").value;
		rowElementName="hidRowCountOF";
		colElementName="hidColCountOF";
	}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
		tableName="tblChecklistHBAER";
		rowCnt=document.getElementById("hidRowCountER").value;
		rowElementName="hidRowCountER";
		colElementName="hidColCountER";
	}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
		tableName="tblChecklistHBALC";
		rowCnt=document.getElementById("hidRowCountLC").value;
		rowElementName="hidRowCountLC";
		colElementName="hidColCountLC";
	}else{
		tableName="tblChecklistHBA";
		rowCnt=document.getElementById("hidRowCount").value;
		rowElementName="hidRowCount";
		colElementName="hidColCountPP";
	}
	var newRow =  document.getElementById(tableName).insertRow(document.getElementById(tableName).rows.length);
	
	var Cell1 = newRow.insertCell(0);
	var Cell2 = newRow.insertCell(1);
	var Cell3 = newRow.insertCell(2);
	
	Cell1.innerHTML = 'Enter Document Name';
	Cell2.innerHTML = '<input type="text" name="txtChecklistNameHBA" id="txtChecklistNameHBA"/>';
	Cell3.innerHTML = '<input type="button" class="bigbutton" style="width: 100px" value="OK" id="btnOk" name="btnOk" onclick=\'displayChecklistHBA(this,'+rowCnt+','+rowElementName+','+colElementName+')\'"/> <img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
	
	document.getElementById("btnAddNewCheckListHBA").style.display='none';
	//document.getElementById("btnAddNewCheckListHBA").disabled=true;
	
}

function displayChecklistHBA(obj,rowCnt,rowElementName,colElementName){
	var label=document.getElementById("txtChecklistNameHBA").value;
	if(label!=""){
		var colCnt = colElementName.value;
		var newRow =  document.getElementById(tableName).insertRow(document.getElementById(tableName).rows.length);
		var rowID = showRowCell(obj);
		document.getElementById(tableName).deleteRow(rowID);	
		var Cell1 = newRow.insertCell(0);
		
		if(document.getElementById('ChecklistHBAForPP').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBAPP" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBACF" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBARB" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBABL" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBASR" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBAOF" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBAER" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBALC" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else{
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListHBA" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}
		document.getElementById("btnAddNewCheckListHBA").style.display='';
		//document.getElementById("btnAddNewCheckListCA").disabled=false;
	}else{
		alert('Please Enter Document Name');
	}
}
function popUpDP(){	
	var basicPay = document.getElementById("txtBasicPay").value;
	if(basicPay != '')
		document.getElementById("txtDP").value = Number(basicPay)*0.5;
	else
		document.getElementById("txtDP").value = '';
}
function displayGuarantorDtls(){
	if(document.getElementById("rdoSuspended").checked){
		document.getElementById('fieldsetGuarantor').style.display = '';
	}else{	
		document.getElementById('fieldsetGuarantor').style.display = 'none';
	}
}
function validateSubType(){
	var msg = document.getElementById("hidReqExists").value;
	var reqType = document.getElementById("cmbHBAType").value;
	if(msg=="OneReqExists" && reqType!=800059){
		alert('Applicant has already taken one House Building Advance So he is only Eligible for Special Repairs Advance');
		document.getElementById("cmbHBAType").value = -1;
		return false;
	}
	return true;
}
function popUpGuarantorDtls(flag){
	
	var empCode;
	if(flag==1){
		empCode= document.getElementById("txtGuarantor1").value;
	}else if(flag==2){
		empCode= document.getElementById("txtGuarantor2").value;
	}
	if(empCode=="" || empCode==null){
		return false;
	}
	var uri="ifms.htm?actionFlag=popUpGuarantorDtls";
	var url = "&empCode="+empCode;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				if(flag==1){
					getReqMsg(myAjax);
				}else if(flag==2){
					getReqMsg1(myAjax);
				}
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getReqMsg(myAjax)
{	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrEmpName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrDdocode = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var lStrDdoName = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	var lStrBasicPay = XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
	if(lblSaveFlag=="true"){
		if(lStrEmpName!=null){
			document.getElementById("txtEmpName1").value=lStrEmpName;
		}
		if(lStrDdocode!=null){
			document.getElementById("txtDdoCode1").value=lStrDdocode;
		}
		if(lStrDdoName!=null){
			document.getElementById("txtDdoName1").value=lStrDdoName;
		}
		if(lStrBasicPay!=null){
			document.getElementById("txtBasicPay1").value=lStrBasicPay;
		}
	}else{
		alert('Invalid Employee Code');
		document.getElementById("txtGuarantor1").value="";
		document.getElementById("txtEmpName1").value="";
		document.getElementById("txtDdoCode1").value="";
		document.getElementById("txtDdoName1").value="";
		document.getElementById("txtBasicPay1").value="";
	}
}

function getReqMsg1(myAjax)
{
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrEmpName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrDdocode = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var lStrDdoName = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	var lStrBasicPay = XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
	if(lblSaveFlag=="true"){
		if(lStrEmpName!=null){
			document.getElementById("txtEmpName2").value=lStrEmpName;
		}
		if(lStrDdocode!=null){
			document.getElementById("txtDdoCode2").value=lStrDdocode;
		}
		if(lStrDdoName!=null){
			document.getElementById("txtDdoName2").value=lStrDdoName;
		}
		if(lStrBasicPay!=null){
			document.getElementById("txtBasicPay2").value=lStrBasicPay;
		}
	}else{
		alert('Invalid Employee Code');
		document.getElementById("txtGuarantor2").value="";
		document.getElementById("txtEmpName2").value="";
		document.getElementById("txtDdoCode2").value="";
		document.getElementById("txtDdoName2").value="";
		document.getElementById("txtBasicPay2").value="";
	}
}

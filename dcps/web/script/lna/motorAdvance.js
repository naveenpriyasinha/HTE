function validateNewOrOldVehicle(){
	
	var payCommission=document.getElementById("hidPayCommisssion").value;
	var payCom=document.getElementById("cmbPayCommissionMCA").value;
	var lStrPC = payCommission.match(/5th/i);
	if(payCom!=-1){
		if(lStrPC=="5th"&&payCom!=800053){
			alert("Please select 5th Pay Commossion GR because Employee is in 5th Pay Commission");
			document.getElementById("cmbPayCommissionMCA").value=-1;
			return false;
		}else if(lStrPC==null&&payCom!=800054){
			alert("Please select 6th Pay Commossion GR because Employee is in 6th Pay Commission");
			document.getElementById("cmbPayCommissionMCA").value=-1;
			return false;
		}
	}
	if(document.getElementById("rdoOld").checked){
		document.getElementById("txtExShowPriceMCA").value='';
		document.getElementById("txtExShowPriceMCA").readOnly=true;
	}else{
		document.getElementById("txtExShowPriceMCA").readOnly=false;
	}
	if(payCom!=-1){
		if(document.getElementById("rdoOld").checked&&payCom==800054){
			alert("In 6th PC GR, there is no provision for employee to take Vehicle Advance for purchase of any Old Vehicle");
			document.getElementById("rdoNew").checked=true;
			document.getElementById("txtExShowPriceMCA").readOnly=false;
			return false;
		}
	}
	return true;
}
function validateReqAmountMCA(flag){
	var reqAmount;
	if(flag==1){
		reqAmount = document.getElementById("txtReqAmountMCA");
	}else if (flag==2){
		reqAmount = document.getElementById("txtSancAmountMCA");
	}
	
	var reqType=document.getElementById("cmbVehicleSubType").value;
	var empGroup=document.getElementById("hidEmpGroup").value;
	var reqPCType=document.getElementById("cmbPayCommissionMCA").value;
	
	var payCommission=document.getElementById("hidPayCommisssion").value;
	var payinPB=document.getElementById("hidPayinPB").value;
	
	var empDesignation=document.getElementById("hidDesignation").value;	
	var lStrDsgn = empDesignation.match(/driver/i);
	var basic = document.getElementById("hidBasicPay").value;
	
	var radio = document.LNARequestProcessForm.rdoVehicleType;
	var radioValue;
	for (i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}
	if(reqType!=-1&&reqPCType!=-1){
		if(reqType==800061){
			if(reqPCType==800053){
				document.getElementById("txtInterestRateMCA").value=12;
			}else{
				document.getElementById("txtInterestRateMCA").value=10;
			}
		}else{
			document.getElementById("txtInterestRateMCA").value=10;
		}
	}
	if(reqType!=-1){
		if(lStrDsgn==null){
			if(empGroup=="D"&&reqType!=800051){
				alert('Group D Employees are only Eligible for Bycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;			
			}
			if(empGroup=="A"&&reqType==800051){
				alert('Group A Employees are not Eligible to take Bycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
			if(empGroup=="B"&&reqType==800051){
				alert('Group B Employees are not Eligible to take Bycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
			if(empGroup=="BnGz"&&reqType==800051){
				alert('Group BnGz Employees are not Eligible to take Bycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
			if(empGroup=="C"&&reqType==800061){
				alert('Group C Employees are not Eligible to take Motor Car Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
		}else{
			if(reqType==800061){
				alert('Employees whose Designation is Driver, are not Eligible for Motor Car Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
		}
	}
	if(lStrDsgn==null){
		if(payCommission=="5th Pay Commission"){
			if(reqType==800061){
				if(basic<10500){
					alert('Employee is not Eligible for Motor Car Advance because Basic pay is less than 10,500');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}else if(reqType==800051){
				if(basic>5000){
					alert('Employee is not Eligible for Bycle Advance because Basic pay is greater than 5,000');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
		}else{
			if(reqType==800032 || reqType==800033 || reqType==800034 || reqType==800035){
				if(payinPB<8650){				
					alert('Employee is not Eligible for Advance because Pay in Pay Band is less than 8,650');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}else if(reqType==800061){
				if(payinPB<19530){
					alert('Employee is not Eligible for Motor Car Advance because Pay in Pay Band is less than 19,530');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}else if(reqType==800051){
				if(payinPB>2800){
					alert('Employee is not Eligible for Bycle Advance because Pay in Pay Band is greater than 2,800');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
		}
	}
	if((reqPCType==800053||reqPCType==800054)&&radioValue=="N"){
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==800032){
					if(reqAmount.value>45000){
						alert('For New With Gears (Motor Cycle) Advance Amount should be less than or Equals to 45,000');
						return false;
					}		
			}
			else if(reqType==800033){
					if(reqAmount.value>28000){
						alert('For New Without Gears (Scooter) Advance Amount should be less than or Equals to 28,000');
						return false;
					}			
			}
			else if(reqType==800034){
					if(reqAmount.value>14000){
						alert('For New Moped Advance Amount should be less than or Equals to 14,000');
						return false;
					}
			}
			else if(reqType==800035){
					if(reqAmount.value>45000){
						alert('For New Motor bike for Handicapped Advance Amount should be less than or Equals to 45,000');
						return false;
					}
			}
			else if(reqType==800051){
				if(reqAmount.value>2500){
					alert('For New Bycle Advance Amount should be less than or Equals to 2,500');
					return false;
				}
			}
			else if(reqType==800061){
				if(reqAmount.value>180000){
					alert('For New Motor Car Advance Amount should be less than or Equals to 1,80,000');
					return false;
				}
			}
		}
	}else if(reqPCType==800053&&radioValue=="O"){
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==800032){
					if(reqAmount.value>22500){
						alert('For Old With Gears (Motor Cycle) Advance Amount should be less than or Equals to 22,500');
						return false;
					}		
			}
			else if(reqType==800033){
					if(reqAmount.value>14000){
						alert('For Old Without Gears (Scooter) Advance Amount should be less than or Equals to 14,000');
						return false;
					}			
			}
			else if(reqType==800034){
					if(reqAmount.value>7000){
						alert('For Old Moped Advance Amount should be less than or Equals to 7,000');
						return false;
					}
			}
			else if(reqType==800035){
					if(reqAmount.value>22500){
						alert('For Old Motor bike for Handicapped Advance Amount should be less than or Equals to 22,500');
						return false;
					}
			}
			else if(reqType==800051){
				if(reqAmount.value>1250){
					alert('For Old Bycle Advance Amount should be less than or Equals to 1,250');
					return false;
				}
			}
			else if(reqType==800061){
					if(reqAmount.value>75000){
						alert('For Old Motor Car Advance Amount should be less than or Equals to 75,000');
						return false;
					}
			}
		}
	}
	if(flag!=1){
		if(payCommission==800053){
			if(reqAmount.value!="" || reqAmount.value!=null){
				var exShowPrice = document.getElementById("txtExShowPriceMCA").value
				if(reqAmount.value>Number(exShowPrice)){
					alert('Sanction Amount is greater than Ex Showroom Price');
					reqAmount.value="";
					reqAmount.focus();
				}	
				var basicAndDP;
				if(radioValue=="N"){
						if(reqType==800032||reqType==800035){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*6;
						}else if(reqType==800033){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
						}else if(reqType==800034){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						}else if(reqType==800061){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*8;
						}
						if(reqAmount.value>Number(basicAndDP)){
							alert('Maximum Sanction Amount for this Employee is '+basicAndDP +'\n Sanction Amount is Greater than this Amount');
							reqAmount.value="";
							reqAmount.focus();
						}	
				}else{
					if(reqType==800032||reqType==800035){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*3;
					}else if(reqType==800033){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
					}else if(reqType==800034){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
					}else if(reqType==800061){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
					}
					if(reqAmount.value>Number(basicAndDP)){
						alert('Maximum Sanction Amount for this Employee is '+basicAndDP +'\n Sanction Amount is Greater than this Amount');
						reqAmount.value="";
						reqAmount.focus();
					}	
				}
			}
		}else{
			if(reqAmount.value!="" || reqAmount.value!=null){
				var exShowPrice = document.getElementById("txtExShowPriceMCA").value
				if(reqAmount.value>Number(exShowPrice)){
					alert('Sanction Amount is greater than Ex Showroom Price');
					reqAmount.value="";
					reqAmount.focus();
				}	
			}
			var payInPB=document.getElementById("hidPayinPB").value;
			var compareAmount;
			if(reqType==800032||reqType==800035){
				compareAmount = Number(payInPB)*6;
			}else if(reqType==800033){
				compareAmount = Number(payInPB)*4;
			}else if(reqType==800034){
				compareAmount = Number(payInPB)*2;
			}else if(reqType==800061){
				compareAmount = Number(payInPB)*8;
			}
			if(reqAmount.value>Number(compareAmount)){
				alert('Maximum Sanction Amount for this Employee is '+payInPB +'\n Sanction Amount is Greater than this Amount');
				reqAmount.value="";
				reqAmount.focus();
			}	
		}
	}
	return true;
}
function popupSancDtlsMCA(flag){
	var payCommission = document.getElementById("cmbPayCommissionMCA").value;
	var reqType=document.getElementById("cmbVehicleSubType").value;
	var maxSancAmt;
	
	var reqAmount;
	if(flag==1){
		reqAmount = document.getElementById("txtReqAmountMCA");
	}else if (flag==2){
		reqAmount = document.getElementById("txtSancAmountMCA");
	}
	var exShowPrice = document.getElementById("txtExShowPriceMCA").value;
	var basic = document.getElementById("hidBasicPay").value;
	var basicPlusDP;
	var payInPB=document.getElementById("hidPayinPB").value;
	var compareAmount;
		
	var radio = document.LNARequestProcessForm.rdoVehicleType;
	var radioValue;
	for (i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}
	if(payCommission==800053){
		if(reqAmount.value != "" && reqType!="-1"){
				if(radioValue=="N"){
					if(reqType==800032||reqType==800035){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*6;
						maxSancAmt = 45000;
					}else if(reqType==800033){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
						maxSancAmt = 28000;
					}else if(reqType==800034){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						maxSancAmt = 14000;
					}else if(reqType==800061){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*8;
						maxSancAmt = 180000;
					}
					if(reqType==800051){	
						maxSancAmt = 2500;
						if(reqAmount.value<maxSancAmt){
							if(Number(reqAmount.value)<Number(exShowPrice)){
								document.getElementById("txtSancAmountMCA").value = reqAmount.value;
							}else{
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
							}
						}else{
							if(Number(exShowPrice)>maxSancAmt){
								document.getElementById("txtSancAmountMCA").value = maxSancAmt;
							}
							else{
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
							}			
						}
					}else{
						if(Number(reqAmount.value)<Number(basicPlusDP)){
							if(Number(reqAmount.value)<Number(exShowPrice)){
								document.getElementById("txtSancAmountMCA").value = reqAmount.value;
							}else{
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
							}
						}else if(Number(exShowPrice)<Number(basicPlusDP)){
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
						}else{
								document.getElementById("txtSancAmountMCA").value = basicPlusDP;
						}
						
						if(document.getElementById("txtSancAmountMCA").value>maxSancAmt){
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
						}
					}
				}else{
					if(reqType==800032||reqType==800035){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*3;
						maxSancAmt = 22500;
					}else if(reqType==800033){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						maxSancAmt = 14000;
					}else if(reqType==800034){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						maxSancAmt = 7000;
					}else if(reqType==800061){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
						maxSancAmt = 75000;
					}
					if(reqType==800051){
						maxSancAmt = 1250;
						if(reqAmount.value<maxSancAmt){
							document.getElementById("txtSancAmountMCA").value = reqAmount.value;
						}else{
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
						}					
					}
					else{
						if(Number(reqAmount.value)<Number(basicPlusDP)){
							document.getElementById("txtSancAmountMCA").value = reqAmount.value;
						}
						else{
							document.getElementById("txtSancAmountMCA").value = basicPlusDP;
						}
						
						if(document.getElementById("txtSancAmountMCA").value>maxSancAmt){
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
						}
					}
				}
			}
	}else{
		if(reqAmount.value != "" && reqType!="-1"){
			if(reqType==800032||reqType==800035){
				compareAmount = Number(payInPB)*6;
				maxSancAmt = 45000;
			}else if(reqType==800033){
				compareAmount = Number(payInPB)*4;
				maxSancAmt = 28000;
			}else if(reqType==800034){
				compareAmount = Number(payInPB)*2;
				maxSancAmt = 14000;
			}else if(reqType==800061){
				compareAmount = Number(payInPB)*8;
				maxSancAmt = 180000;
			}
			if(reqType==800051){
				maxSancAmt = 2500;
				if(reqAmount.value<maxSancAmt){
					if(Number(reqAmount.value)<Number(exShowPrice)){
						document.getElementById("txtSancAmountMCA").value = reqAmount.value;
					}else{
						document.getElementById("txtSancAmountMCA").value = exShowPrice;
					}
				}else{
					if(Number(exShowPrice)>maxSancAmt){
						document.getElementById("txtSancAmountMCA").value = maxSancAmt;
					}
					else{
						document.getElementById("txtSancAmountMCA").value = exShowPrice;
					}			
				}
			}else{
				if(Number(reqAmount.value)<Number(compareAmount)){
					if(Number(reqAmount.value)<Number(exShowPrice)){
						document.getElementById("txtSancAmountMCA").value = reqAmount.value;
					}else{
						document.getElementById("txtSancAmountMCA").value = exShowPrice;
					}
				}else if(Number(exShowPrice)<Number(compareAmount)){
						document.getElementById("txtSancAmountMCA").value = exShowPrice;
				}else{
						document.getElementById("txtSancAmountMCA").value = compareAmount;
				}
				
				if(document.getElementById("txtSancAmountMCA").value>maxSancAmt){
					document.getElementById("txtSancAmountMCA").value = maxSancAmt;
				}
			}
		}
	}
}
function validateNoOfInstallmentsMCA(){
	document.getElementById("txtPrinInstallmentAmountMCA").value = "";
	document.getElementById("txtInterInstallmentAmountMCA").value = "";
	document.getElementById("txtOddPrincipalInstallmentAmtMCA").value = "";
	document.getElementById("txtOddInterestInstallmentAmtMCA").value = "";
	document.getElementById("txtInterestAmountMCA").value = "";
	var reqType=document.getElementById("cmbVehicleSubType").value;
	var sancPrinInst = document.getElementById("txtSancPrincipalInstallMCA").value;
	var sancInterInst = document.getElementById("txtSancInterInstallMCA").value;
	var radio = document.LNARequestProcessForm.rdoVehicleType;
	var radioValue;
	for (i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}
	var DOS = document.getElementById("hidDateOfSuperAnnuation").value;
	var lArrDate = DOS.split("/");	
	var dateofSuperAnnu = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	var currDate = new Date();
	var futureDate = new Date(currDate);
	var d1Y = currDate.getFullYear();
    var d2Y = dateofSuperAnnu.getFullYear();
    var d1M = currDate.getMonth();
    var d2M = dateofSuperAnnu.getMonth();
    var maxInstallment=(d2M+12*d2Y)-(d1M+12*d1Y);
	futureDate.setMonth(futureDate.getMonth() + (Number(sancPrinInst)+Number(sancInterInst)));
	if(futureDate>dateofSuperAnnu){
		alert('Considering Retirement Date Maximum Number of installments for Employee are '+maxInstallment);
		document.getElementById("txtSancPrincipalInstallMCA").value="";
		document.getElementById("txtSancInterInstallMCA").value="";		
		document.getElementById("txtSancPrincipalInstallMCA").focus();
		return false;
	}
	if(sancPrinInst!=""){
		if(Number(sancPrinInst==0)){
			alert('Sanctioned No. of Principal Installments Should be greater than 0');
			document.getElementById("txtSancPrincipalInstallMCA").value="";
			document.getElementById("txtSancPrincipalInstallMCA").focus();
			return false;
		}
	}
	if(sancInterInst!=""){
		if(Number(sancInterInst==0)){ 
			alert('Sanctioned No. of Interest Installments Should be greater than 0');
			document.getElementById("txtSancInterInstallMCA").value="";
			document.getElementById("txtSancInterInstallMCA").focus();
			return false;
		}
	}
	if(sancPrinInst!="" || sancInterInst!=""){		
		if(reqType==800032&&radioValue=="N"){
				if(Number(sancPrinInst)+Number(sancInterInst)>60){
					alert('Sanctioned No. of Total Installments Should be less than 60');
					document.getElementById("txtSancPrincipalInstallMCA").value="";
					document.getElementById("txtSancInterInstallMCA").value="";
					document.getElementById("txtSancPrincipalInstallMCA").focus();
					return false;
				}
		}else if(reqType==800032&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>30){
				alert('Sanctioned No. of Total Installments Should be less than 30');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800033&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>48){
				alert('Sanctioned No. of Total Installments Should be less than 48');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800033&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>24){
				alert('Sanctioned No. of Total Installments Should be less than 24');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800034&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>30){
				alert('Sanctioned No. of Total Installments Should be less than 30');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800034&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>15){
				alert('Sanctioned No. of Total Installments Should be less than 15');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800035&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>60){
				alert('Sanctioned No. of Total Installments Should be less than 60');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800035&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>30){
				alert('Sanctioned No. of Total Installments Should be less than 30');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";	
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800051&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>10){
				alert('Sanctioned No. of Total Installments Should be less than 10');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800051&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>5){
				alert('Sanctioned No. of Total Installments Should be less than 5');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==800061&&radioValue=="N"){
			if(Number(sancPrinInst)>100){
				alert('Sanctioned No. of Principal Installments Should be less than 100');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
			if(Number(sancInterInst)>60){
				alert('Sanctioned No. of Interest Installments Should be less than 60');
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").focus();
				return false;
			}
		}else if(reqType==800061&&radioValue=="O"){
			if(Number(sancPrinInst)>50){
				alert('Sanctioned No. of Principal Installments Should be less than 50');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
			if(Number(sancInterInst)>30){
				alert('Sanctioned No. of Interest Installments Should be less than 30');
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").focus();
				return false;
			}
		}
	}
	
	var sancAmount = document.getElementById("txtSancAmountMCA").value;
	if(sancAmount != "" && sancPrinInst != "" && sancInterInst != ""){
		
		var inst= Math.floor(sancAmount/sancPrinInst);
		var oddValue = sancAmount - (inst*(sancPrinInst-1));
		if (inst==oddValue){
			document.getElementById("txtPrinInstallmentAmountMCA").value = inst;
			document.getElementById("cmbOddPrincipalInstallNoMCA").value = -1;
			document.getElementById("cmbOddPrincipalInstallNoMCA").disabled = true;
		} else{
			document.getElementById("txtPrinInstallmentAmountMCA").value = inst;
			document.getElementById("txtOddPrincipalInstallmentAmtMCA").value = oddValue;
			document.getElementById("cmbOddPrincipalInstallNoMCA").disabled = false;
		}
		var capInstAmount = document.getElementById("txtPrinInstallmentAmountMCA").value;
		var interestRate = document.getElementById("txtInterestRateMCA").value;
		if(interestRate!=""){
			var interestAmount = Math.round(Number(sancPrinInst)*(Number(sancPrinInst)+1)/2*(Number(capInstAmount)/12)*(Number(interestRate)/100));
			inst = Math.floor(Number(interestAmount)/Number(sancInterInst));
			oddValue = interestAmount - (inst*(sancInterInst-1));
		}
		if (inst==oddValue){
			document.getElementById("txtInterInstallmentAmountMCA").value = inst;
			document.getElementById("cmbOddInterestInstallNoMCA").value = -1;
			document.getElementById("cmbOddInterestInstallNoMCA").disabled = true;
		} else{
			document.getElementById("txtInterInstallmentAmountMCA").value = inst;
			document.getElementById("txtOddInterestInstallmentAmtMCA").value = oddValue;
			document.getElementById("cmbOddInterestInstallNoMCA").disabled = false;
		}
		document.getElementById("txtInterestAmountMCA").value = interestAmount;		
	}
}
function changeSancDtlsMCA(){
	var reqType=document.getElementById("cmbVehicleSubType").value;	
	if(reqType==800035){
		document.getElementById('ChecklistMCAHandicap').style.display = '';
		document.getElementById('ChecklistMCA').style.display = 'none';
		tableName='tblChecklistMCAHandicap';
	}else {
		document.getElementById('ChecklistMCAHandicap').style.display = 'none';
		document.getElementById('ChecklistMCA').style.display = '';
		tableName='tblChecklistMCA';
	}
}
var tableName;
function addNewCheckListMCA(){
	
	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		tableName="tblChecklistMCAHandicap";
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		tableName="tblChecklistMCA";
	}else{
		tableName="tblChecklistMCANew";
	}
	var newRow =  document.getElementById(tableName).insertRow(document.getElementById(tableName).rows.length);	
	var rowCnt=document.getElementById("hidRowCountMCA").value;
	
	var Cell1 = newRow.insertCell(0);
	var Cell2 = newRow.insertCell(1);
	
	Cell1.innerHTML = 'Enter Document Name     <input type="text" name="txtChecklistNameMCA" id="txtChecklistNameMCA"/>';
	Cell2.innerHTML = '<input type="button" class="bigbutton" style="width: 100px" value="OK" id="btnOk" name="btnOk" onclick=\'displayChecklistMCA(this,"tblChecklistMCA",'+rowCnt+')\'"/> <img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
	document.getElementById("btnAddNewCheckListMCA").style.display='none';
	//document.getElementById("btnAddNewCheckListMCA").disabled=true;
}
function displayChecklistMCA(obj, tblId,rowCnt){
	var label=document.getElementById("txtChecklistNameMCA").value;
	if(label!=""){
		var colCnt = document.getElementById("hidCheckListsMCA").value;
		var newRow =  document.getElementById(tableName).insertRow(document.getElementById(tableName).rows.length);
		var rowID = showRowCell(obj);
		document.getElementById(tableName).deleteRow(rowID);	
		var Cell1 = newRow.insertCell(0);
		if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListMCAHC" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistMCA').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListMCA" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else {
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListMCANew" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}
		document.getElementById("btnAddNewCheckListMCA").style.display='';
		//document.getElementById("btnAddNewCheckListCA").disabled=false;
	}else{
		alert('Please Enter Document Name');
	}
}
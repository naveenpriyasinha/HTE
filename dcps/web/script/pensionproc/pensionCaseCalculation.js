function divexpand(){
	if(document.getElementById("deathgratuity").checked || document.getElementById("retiregratuity").checked || document.getElementById("servicegratuity").checked)
	{
		document.getElementById("checkboxdepend").style.display="";
	}
	else {
		document.getElementById("checkboxdepend").style.display="none";
	}
	//added by ankita
	var gratuity_type;
	if(document.getElementById("deathgratuity").checked)
	{
		gratuity_type="death";
	}
	else if(document.getElementById("retiregratuity").checked)
	{
		gratuity_type="ret";
	}
	else if(document.getElementById("servicegratuity").checked)
	{
		gratuity_type="serv";
	}
	document.getElementById("gratuity_type").value=gratuity_type;
}

function setPensionAmnt()
{
	
	var lengthServBrkDtls = document.getElementById("tblServBrkDtls").rows.length;
	for(var i=0;i<=Number(lengthServBrkDtls);i++){
		calTotalSrvcBrk(i);
	}
	calActualServiceDays();
	setTotalDaysOfSrvcBrk('txtDateOfBrkFrom','txtDateOfBrkTo');
	
	/*************************SMS MEANS SIX MONTH SERVICE*************************/
	var noOfSms = 0;
	var pensionAmnt = 0;
	var emolument = 0;
	var length = Number(document.getElementById("tblAvgPayCalc").rows.length) - 1;
	/*****************some validation************starts***************************/
	
	if(document.getElementById("txtDateOfRetiremt").value == "" )
	{
		alert('Please enter date of retirement.');
		document.getElementById("txtDateOfRetiremt").focus();
		return;
	}
	if(document.getElementById("txtDateOfStartingService").value == "")
	{
		alert('Please enter date of starting service.');
		document.getElementById("txtDateOfStartingService").focus();
		return;
	}

	if(document.getElementById("cmbPayComsn").value == "" || document.getElementById("cmbPayComsn").value == "-1")
	{
		alert('Please Select Type of Pay Commission');
		return;
	}
	
	/*****************some validation***************ends**********************/
	
	
	/*****************CVP Rate Calculation***************starts**********************/ // remaining....
	
	if(document.getElementById("txtDateOfBirth").value == "" )
	{
		alert('Please enter date of birth.');
		document.getElementById("txtDateOfBirth").focus();
		return;
	}
	
	var DOR = document.getElementById("txtDateOfRetiremt").value;
	var DORArr = DOR.split("/"); 
	var DOB = document.getElementById("txtDateOfBirth").value;
	var DOBArr = DOB.split("/");
	var DOJ = document.getElementById("txtDateOfStartingService").value;
	var DOJArr = DOJ.split("/");
	var age = ageFromDOBAndDOR(DOB,DOR);
	var cvpTempDate = new Date();
	cvpTempDate.setFullYear(2009,2,27);
	cvpTempDate.setMonth(cvpTempDate.getMonth()-1);
	document.getElementById("hdnCvpTempDate").value = "27/02/2009";
	

	 if(compareTwoDates(document.getElementById("txtDateOfRetiremt"), document.getElementById("hdnCvpTempDate"),">") == true && document.getElementById("cmbPayComsn").options[document.getElementById("cmbPayComsn").selectedIndex].text == '6th Pay Commission')
	{
		 
		// alert("here ei ma a ");
		age = Number(age) + 1;
	}
	if(Number(age) > 85)
	{
		age = 85;
	}
	var cvpRate;
	if(Number(age) < 29)
	{
		alert('CVP Rate is not applicable.');
		cvpRate=1;
		return;
	}
	else
	{
		getCVPRateFromAge(age,document.getElementById("cmbPayComsn").options[document.getElementById("cmbPayComsn").selectedIndex].text);
		cvpRate = document.getElementById("hdnCvpRate").value;
	}
	// 
	
	/*****************CVP Rate Calculation***************ends**********************/
	
	
	/*************************SMS Calculation**********starts*****************/
	
	var smsYear = Math.abs(Number(document.getElementById("hidTotalyear").value)-Number(document.getElementById("hidTotalBrkyear").value));
	var smsMonth = Math.abs(Number(document.getElementById("hidTotalMonth").value)-Number(document.getElementById("hidTotalBrkMonth").value));
	var smsDays = Math.abs(Number(document.getElementById("hidTotalDays").value)-Number(document.getElementById("hidTotalBrkDays").value));
	
	//check for VOLUNTARY RETIREMENT 
	if(document.getElementById("cmbClassOfPnsn").options[document.getElementById("cmbClassOfPnsn").selectedIndex].text == 'Voluntary Pension')
	{
		if(Number(smsYear) >= 20 && Number(smsMonth) >= 3)
		{
			smsYear = Number(smsYear) + 5;
		}
		if(Number(smsYear) >= 33)
		{
			noOfSms = 66;
		}
	}
	
	if(Number(smsYear) > 0)
	{
		noOfSms = 2 * Number(smsYear);
		if(Number(noOfSms) > 66)
		{
			noOfSms = 66;
		}
		else
		{
			if(Number(noOfSms) == 66)
			{
				noOfSms = noOfSms;
			}
			else //no. of SMS are less than 66
			{
				if(Number(smsMonth) >=3 && Number(smsMonth) < 9)
				{
					noOfSms = noOfSms + 1;
				}
				else if (Number(smsMonth) >=9)
				{
					noOfSms = noOfSms + 2;
				}
				else // smsMonth is less than 3
				{
					noOfSms = noOfSms;
				}
			}
		}
	}
	/*************************SMS Calculation**********ends*****************/
	
	//getValidEmoluments();
	
	var emolumentFromDate = document.getElementById("txtEmolumentFromDate").value;
	var emolumentFromDateArr = emolumentFromDate.split("/");
	var emolumentFromDt = new Date();
	emolumentFromDt.setFullYear(emolumentFromDateArr[2],emolumentFromDateArr[1],emolumentFromDateArr[0]);
	emolumentFromDt.setMonth(emolumentFromDt.getMonth()-1);
	
	var emolumentToDate = document.getElementById("txtEmolumentToDate").value;
	var emolumentToDateArr = emolumentToDate.split("/");
	var emolumentToDt = new Date();
	emolumentToDt.setFullYear(emolumentToDateArr[2],emolumentToDateArr[1],emolumentToDateArr[0]);
	emolumentToDt.setMonth(emolumentToDt.getMonth()-1);
	
	var fromDateArr  = document.getElementsByName("txtPeriodFromDate");
	var totalArr = document.getElementsByName("txtTotal");
	var basicPay = document.getElementById("txtBasicPay").value;
	var dp = document.getElementById("txtDP").value;
    var paycomm = document.getElementById("cmbPayComsn").value;
	if(basicPay == "")
	{
		alert('Please enter basic pay amount.');
		document.getElementById("txtBasicPay").focus();
		return;
	}
	if(dp == "" && paycomm == "5THPAYCOMSN")
	{
		alert('Please enter DP amount.');
		document.getElementById("txtDP").focus();
		return;
	}
	var total = Number(basicPay) + Number(dp);
	
	/******************************************************Calculate Pension Amounts************Starts****************************************************/
	var additionTotal = 0;
	var monthDiff;
	var pensionAmntArr = new Array(); // mainly used to compare last emolument with avg. of all emolument for six pay commisis 
	if(length != 1)
	{
		for(var j=0;j<fromDateArr.length;j++)
		{
			var tempDate = new Date();
			var tempDateArr = fromDateArr[j].value.split("/");
			tempDate.setFullYear(tempDateArr[2],tempDateArr[1],tempDateArr[0]);
			tempDate.setMonth(tempDate.getMonth()-1);
			//take each and every period from date as tempdate and check this with emolument from date
			if(compareTwoDates(fromDateArr[j],document.getElementById("txtEmolumentFromDate"),"<") == true || compareTwoDates(fromDateArr[j],document.getElementById("txtEmolumentFromDate"),"=") == true)
			{
				//if only 1 row is entered by pensioner then take this (total amount * 10) as last 10 emolument's total
				if(j==fromDateArr.length-1)
				{
					pensionAmnt = pensionAmnt + 10 * Number(totalArr[j].value);
					//preapre emoluments Array in pensionAmntArr
					for(var k =0;k<10;k++)
					{
						pensionAmntArr[k]=Number(totalArr[j].value);
					}
					break;
				}
				
				var tempDateNext = new Date();
				var tempDateArrNext = fromDateArr[j+1].value.split("/");
				tempDateNext.setFullYear(tempDateArrNext[2],tempDateArrNext[1],tempDateArrNext[0]);
				tempDateNext.setMonth(tempDateNext.getMonth()-1);
				//check next row period from date to decide the month difference and total accordingly 
				if(compareTwoDates(fromDateArr[j+1],document.getElementById("txtEmolumentFromDate"),"<") == true || compareTwoDates(fromDateArr[j+1],document.getElementById("txtEmolumentFromDate"),"=") == true)
				{
						if(j==fromDateArr.length-1)
						{
							pensionAmnt = pensionAmnt + 10 * Number(totalArr[j].value);
							//prepare emoluments Array in pensionAmntArr
							for(var k =0;k<10;k++)
							{
								pensionAmntArr[k]=Number(totalArr[j].value);
							}
							break; // IF IT IS LAST ROW THEN CONSIDER THIS VALUE AND BREAK LOOP 
						}
						else
						{
							continue;  // IF ANOTHER ROW IS LESS THAN EMOLUMENT FROM DATE THEN SKIP CURRENT ITERATION 
						}
				}
				else 
				{
					additionTotal =  Number(totalArr[j].value);
					if(tempDateArrNext[2] == emolumentFromDateArr[2]) // both year are equal
					{
						//check jth row's period from date's month is less than  emolument from date month
						if(Number(tempDateArr[1]) < Number(emolumentFromDateArr[1]))
						{
							pensionAmnt = pensionAmnt + additionTotal * Math.abs(Number(tempDateArrNext[1])-Number(emolumentFromDateArr[1]));
							//prepare emoluments Array in pensionAmntArr
							if(pensionAmntArr.length == 0)
							{
								for(var k =0;k<Math.abs(Number(tempDateArrNext[1])-Number(emolumentFromDateArr[1]));k++)
								{
									pensionAmntArr[k]=Number(totalArr[j].value);
								}
							}
							else
							{
								var tmpCnt = pensionAmntArr.length + Math.abs(Number(tempDateArrNext[1])-Number(emolumentFromDateArr[1])); 
								for(var k =pensionAmntArr.length;k<tmpCnt;k++)
								{
									pensionAmntArr[k]=Number(totalArr[j].value);
								}
							}
						}
						else if(Number(tempDateArr[1]) == Number(emolumentFromDateArr[1]))
						{
							pensionAmnt = pensionAmnt + additionTotal * Math.abs(Number(tempDateArrNext[1])-Number(tempDateArr[1]));
							//prepare emoluments Array in pensionAmntArr 
							if(pensionAmntArr.length == 0)
							{
								for(var k =0;k<Math.abs(Number(tempDateArrNext[1])-Number(tempDateArr[1]));k++)
								{
									pensionAmntArr[k]=Number(totalArr[j].value);
								}
							}
							else
							{
								var tmpCnt = pensionAmntArr.length + Math.abs(Number(tempDateArrNext[1])-Number(tempDateArr[1])); 
								for(var k =pensionAmntArr.length;k<tmpCnt;k++)
								{
									pensionAmntArr[k]=Number(totalArr[j].value);
								}
							}
						}
						else
						{
							pensionAmnt = pensionAmnt + additionTotal * Math.abs(Number(tempDateArrNext[1])-Number(emolumentFromDateArr[1]));
							//prepare emoluments Array in pensionAmntArr
							if(pensionAmntArr.length == 0)
							{
								for(var k =0;k<Math.abs(Number(tempDateArrNext[1])-Number(emolumentFromDateArr[1]));k++)
								{
									pensionAmntArr[k]=Number(totalArr[j].value);
								}
							}
							else
							{
								var tmpCnt = pensionAmntArr.length + Math.abs(Number(tempDateArrNext[1])-Number(emolumentFromDateArr[1])); 
								for(var k =pensionAmntArr.length;k<tmpCnt;k++)
								{
									pensionAmntArr[k]=Number(totalArr[j].value);
								}
							}
						}
					}
					else if (tempDateArrNext[2] > emolumentFromDateArr[2]) // (j+1) date's year is greater than emolument from date year
					{
						monthDiff =Math.abs(12 - Number(emolumentFromDateArr[1])) + Math.abs(Number(tempDateArrNext[1]));
						pensionAmnt = pensionAmnt + (additionTotal * monthDiff);
						//prepare emoluments Array in pensionAmntArr
						if(pensionAmntArr.length == 0)
						{
							for(var k =0;k<Math.abs(Number(emolumentFromDateArr[1])-Number(tempDateArrNext[1]));k++)
							{
								pensionAmntArr[k]=Number(totalArr[j].value);
							}
						}
						else
						{
							var tmpCnt = pensionAmntArr.length + monthDiff ; 
							for(var k =pensionAmntArr.length;k<tmpCnt;k++)
							{
								pensionAmntArr[k]=Number(totalArr[j].value);
							}
						}
					}
				}
			}
			//execute when jth row's date lies between emolument from date and emolument to date 
			else if(compareTwoDates(fromDateArr[j],document.getElementById("txtEmolumentFromDate"),">") == true && compareTwoDates(fromDateArr[j],document.getElementById("txtEmolumentToDate"),"<") == true)			
			{
				if(j==fromDateArr.length-1)
				{
					if(pensionAmnt == 0)
					{
						pensionAmnt = pensionAmnt + 10 * Number(totalArr[j].value);
						//prepare emoluments Array in pensionAmntArr
						for(var k =0;k<10;k++)
						{
							pensionAmntArr[k]=Number(totalArr[j].value);
						}
						break;
					}
					else
					{
						additionTotal =  Number(totalArr[j].value);
						if(tempDateArr[2] == emolumentToDateArr[2]) // both year are equal
						{
							//check emolument to date's day to decide month should be added or not
							if(Number(emolumentToDateArr[0]) > 15)
							{
								monthDiff = Math.abs(Number(tempDateArr[1]) -  Number(emolumentToDateArr[1])) + 1;
								pensionAmnt = pensionAmnt + (additionTotal * monthDiff);
								//prepare emoluments Array in pensionAmntArr
								if(pensionAmntArr.length == 0)
								{
									for(var k =0;k<Math.abs(Number(tempDateArr[1])-Number(emolumentToDateArr[1]));k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
								else
								{
									var tmpCnt = pensionAmntArr.length + monthDiff; 
									for(var k =pensionAmntArr.length;k<tmpCnt;k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
							}
							else
							{
								monthDiff = Math.abs(Number(tempDateArr[1])-Number(emolumentToDateArr[1]));
								pensionAmnt = pensionAmnt + (additionTotal * monthDiff);
								//prepare emoluments Array in pensionAmntArr
								if(pensionAmntArr.length == 0)
								{
									for(var k =0;k<Math.abs(Number(tempDateArr[1])-Number(emolumentToDateArr[1]));k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
								else
								{
									var tmpCnt = pensionAmntArr.length + monthDiff; 
									for(var k =pensionAmntArr.length;k<tmpCnt;k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
							}
							break;
						}
						else if (tempDateArr[2] < emolumentToDateArr[2]) //jth row's year is less then emolument date's year
						{
							//check emolument to date's day to decide month should be added or not
							if(Number(emolumentToDateArr[0]) > 15)
							{
								monthDiff = Math.abs((12 - Number(tempDateArr[1])) + Number(emolumentToDateArr[1])) + 1;
								pensionAmnt = pensionAmnt + (additionTotal * monthDiff);
								//prepare emoluments Array in pensionAmntArr
								if(pensionAmntArr.length == 0)
								{
									for(var k =0;k<Math.abs(Number(tempDateArr[1])-Number(emolumentToDateArr[1]));k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
								else
								{
									var tmpCnt = pensionAmntArr.length + monthDiff; 
									for(var k =pensionAmntArr.length;k<tmpCnt;k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
							}
							else
							{
								monthDiff = Math.abs((12 - Number(tempDateArr[1])) + Number(emolumentToDateArr[1]));
								pensionAmnt = pensionAmnt + (additionTotal * monthDiff);
								//prepare emoluments Array in pensionAmntArr
								if(pensionAmntArr.length == 0)
								{
									for(var k =0;k<Math.abs(Number(tempDateArr[1])-Number(emolumentToDateArr[1]));k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
								else
								{
									var tmpCnt = pensionAmntArr.length + monthDiff; 
									for(var k =pensionAmntArr.length;k<tmpCnt;k++)
									{
										pensionAmntArr[k]=Number(totalArr[j].value);
									}
								}
							}
							break;
						}
					}
				}
				var tempDateNext = new Date();
				var tempDateArrNext = fromDateArr[j+1].value.split("/");
				tempDateNext.setFullYear(tempDateArrNext[2],tempDateArrNext[1],tempDateArrNext[0]);
				tempDateNext.setMonth(tempDateNext.getMonth()-1);
				additionTotal =  Number(totalArr[j].value);
				
				//compare (j+1) row's date year with emolument from date year
				if(tempDateArrNext[2] == emolumentFromDateArr[2]) // both year are equal
				{
					pensionAmnt = pensionAmnt + additionTotal * Math.abs(Number(tempDateArr[1])-Number(tempDateArrNext[1]));
					//prepare emoluments Array in pensionAmntArr
					if(pensionAmntArr.length == 0)
					{
						for(var k =0;k<Math.abs(Number(tempDateArr[1])-Number(tempDateArrNext[1]));k++)
						{
							pensionAmntArr[k]=Number(totalArr[j].value);
						}
					}
					else
					{
						var tmpCnt = pensionAmntArr.length + Math.abs(Number(tempDateArr[1])-Number(tempDateArrNext[1])); 
						for(var k =pensionAmntArr.length;k<tmpCnt;k++)
						{
							pensionAmntArr[k]=Number(totalArr[j].value);
						}
					}
				}
				else if (tempDateArrNext[2] > emolumentFromDateArr[2]) // (j+1) row's date year is greater than emolument from date year
				{
					monthDiff = Math.abs((12 - Number(tempDateArr[1])) + Number(tempDateArrNext[1]));
					pensionAmnt = pensionAmnt + (additionTotal * monthDiff);
					if(pensionAmntArr.length == 0)
					{
						for(var k =0;k<Math.abs(Number(tempDateArr[1])-Number(tempDateArrNext[1]));k++)
						{
							pensionAmntArr[k]=Number(totalArr[j].value);
						}
					}
					else
					{
						var tmpCnt = pensionAmntArr.length + monthDiff; 
						for(var k =pensionAmntArr.length;k<tmpCnt;k++)
						{
							pensionAmntArr[k]=Number(totalArr[j].value);
						}
					}
				}
			}
		}
		if(document.getElementById("cmbPayComsn").options[document.getElementById("cmbPayComsn").selectedIndex].text == "5th Pay Commission")
		{
			
			
			pensionAmnt = (pensionAmnt/20) *  (Number(noOfSms)/66);
			
			if(document.getElementById("cmbPnsnCatg").options[document.getElementById("cmbPnsnCatg").selectedIndex].text == 'Others')
			{
				if(Number(pensionAmnt)>18375)
				{
					pensionAmnt = 18375;
				}
			}
			else
			{
				if(Number(pensionAmnt)>22500)
				{
					pensionAmnt = 22500;
				}
			}
			document.getElementById("txtTotPnsnAmt").value = Math.round(pensionAmnt); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math.round(pensionAmnt); // Total Pension ReadOnly
			document.getElementById("txtMonthAmt").value =Math.round(pensionAmnt/3); //Monthly Commuted Pension amount
			document.getElementById("txtComPnsnAmt").value=Math.round(pensionAmnt/3); // Commuted Pension amount(same as monthly commuted amount)
			document.getElementById("txtCVPAmt").value=Math.round(Number(document.getElementById("txtComPnsnAmt").value)*12*(cvpRate));   //CVP Amount
			//Reduced Pension Amount
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
			
			
			
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("txtDateOfExpiry"), "<") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("txtDateOfExpiry"), "=") == true)
			{
				document.getElementById("txtServGratyAmt").readOnly=true;
				document.getElementById("txtServGratyAmt").value=0;
				// Total DCRG Amount
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
			}
			else
			{
				document.getElementById("txtTotDCRGAmt").readOnly=true;
				//document.getElementById("txtTotDCRGAmt").value=0;
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
				// Service Gratuity
				/**************************************************************************/
				if(Number(noOfSms) < 10)
				{
					document.getElementById("txtServGratyAmt").value=0;
				}
				else if(Number(noOfSms) >= 10 && Number(noOfSms) < 20)
				{
					document.getElementById("txtServGratyAmt").value= (((Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))/4)*6);
				}
				/**************************************************************************/
				
				if(Number(document.getElementById("txtServGratyAmt").value) > 350000)
				{
					document.getElementById("txtServGratyAmt").value = 350000;
				}
			}
			//in case of death of employee while in service then DCRG is paid according to following conditions.....
			if(compareTwoDates(document.getElementById("txtDateOfExpiry"),document.getElementById("txtDateOfRetiremt"), "<") == true)
			{
				document.getElementById("txtTotDCRGAmt").disabled = false;
				if(Number(noOfSms) < 2)
				{
					document.getElementById("txtTotDCRGAmt").value= 2 * (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value));
				}
				else if(Number(noOfSms) >= 2 && Number(noOfSms) <10)
				{
					document.getElementById("txtTotDCRGAmt").value= 6 * (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value));
				}
				else if(Number(noOfSms) >= 10 && Number(noOfSms) <40)
				{
					document.getElementById("txtTotDCRGAmt").value= 12 * (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value));
				}
				else if(Number(noOfSms)  >= 40)
				{
					document.getElementById("txtTotDCRGAmt").value= (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))* (Number(noOfSms)/2);
				}
			}
			//Maximum Gratuity amount must not exceed 350000 for 5th pay commission
			if(Number(document.getElementById("txtTotDCRGAmt").value) > 350000)
			{
				document.getElementById("txtTotDCRGAmt").value = 350000;
			}
			
			//FP1 Amount
			if((Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value)/2) <= Math.round(pensionAmnt))
			{
				document.getElementById("txtFP1TotlAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value)/2);
			}
			else
			{
				document.getElementById("txtFP1TotlAmt").value=Math.round(pensionAmnt);
			}
			//FP2 Amount
			document.getElementById("txtFP2TotlAmt").value=((Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*0.30);
			
		}
		if(document.getElementById("cmbPayComsn").options[document.getElementById("cmbPayComsn").selectedIndex].text == "6th Pay Commission")
		{
			document.getElementById("hdnTempDate").value="31/10/2006";
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate"), "<") == true)
			{
				pensionAmnt = Number(pensionAmntArr[9]);
			}
			else
			{
				//last emolument  is : pensionAmntArr[9]
				if(Number(Number(pensionAmnt)/10) >= Number(pensionAmntArr[9]))
				{
					pensionAmnt = Number(Number(pensionAmnt)/10) ;
				}
				else
				{
					pensionAmnt = Number(pensionAmntArr[9]);
				}
			}
			if(Number(pensionAmnt) > 67000)
			{
				pensionAmnt = 67000;
			}
			pensionAmnt = (pensionAmnt/2) *  (Number(noOfSms)/66);
			document.getElementById("txtTotPnsnAmt").value = Math.round(pensionAmnt); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math.round(pensionAmnt); // Total Pension ReadOnly
			document.getElementById("txtMonthAmt").value =(Math.round(pensionAmnt)*4)/10; //Monthly Commuted Pension amount
			document.getElementById("txtComPnsnAmt").value=(Math.round(pensionAmnt)*4)/10; // Commuted Pension amount
			document.getElementById("txtCVPAmt").value=Math.round((Math.round(pensionAmnt)*4/10))*12*(cvpRate);   //CVP
			
			//Reduced Pension Amount
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
			
			//consider the age on next birth date and get CVP Rate as per revised CVP Rate for pensioner retired after 27/02/2009 : remaining
			
			// check wether the pensioner is retired btwn 01/01/2006 and 27/02/2009 : 
			// if yes then two option should be given a.Not to revise CVP b. Apply for 40% of revised pension
			document.getElementById("hdnTempDate1").value="01/01/2006";
			document.getElementById("hdnTempDate2").value="27/02/2009";
			

		//	if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate1"), ">") == true && compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate2"), "<") == true)
			{
				//condition 10 is remaining.........
			}
			
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"), document.getElementById("txtDateOfExpiry"), "<") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"), document.getElementById("txtDateOfExpiry"), "=") == true)
			{
				document.getElementById("txtServGratyAmt").readOnly=true;
				document.getElementById("txtServGratyAmt").value=0;
				// Total DCRG Amount				
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
			}
			else
			{
				document.getElementById("txtTotDCRGAmt").readOnly=true;
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
				// Service Gratuity
				if(Number(noOfSms) < 20)
				{
					document.getElementById("txtServGratyAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4) * 2;
				}
			}
			
			//in case of death of employee while in service then DCRG is paid according to following conditions.....
			if(compareTwoDates(document.getElementById("txtDateOfExpiry"),document.getElementById("txtDateOfRetiremt"), "<") == true)
			{
				document.getElementById("txtTotDCRGAmt").disabled = false;
				document.getElementById("txtTotDCRGAmt").value= 2 * (Number(document.getElementById("txtTotDCRGAmt").value));
			}
			//check wether the pensioner retirement date is greter than or equal to 01/09/2009 or not
			document.getElementById("hdnTempDate3").value="01/09/2009";
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate3"), ">") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate3"), "=") == true)
			{
				if(Number(document.getElementById("txtTotDCRGAmt").value) > 700000)
				{
					document.getElementById("txtTotDCRGAmt").value = 700000;
				}
			}
			//check wether the pensioner retirement date is less than 01/09/2009  and greater than 31/12/2005
			document.getElementById("hdnTempDate4").value="31/12/2005";
			
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate3"), "<") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate4"), ">") == true)
			{
				
				if(Number(document.getElementById("txtTotDCRGAmt").value) > 500000)
				{
					document.getElementById("txtTotDCRGAmt").value = 500000;
				}
			}
			//FP1 Amount
			if((Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value)/2) <= Math.round(pensionAmnt))
			{
				document.getElementById("txtFP1TotlAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value)/2);
			}
			else
			{
				document.getElementById("txtFP1TotlAmt").value=Math.round(pensionAmnt);
			}
			//FP2 Amount
			document.getElementById("txtFP2TotlAmt").value=((Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*0.30);
			
		}
	}
	else //length == 1
	{
		if(document.getElementById("cmbPayComsn").options[document.getElementById("cmbPayComsn").selectedIndex].text == "5th Pay Commission")
		{
			pensionAmnt = (Math.abs((Number(totalArr[0].value))/2)) *  (Number(noOfSms)/66);
			
			if(document.getElementById("cmbPnsnCatg").options[document.getElementById("cmbPnsnCatg").selectedIndex].text == 'Others')
			{
				if(Number(pensionAmnt)>18375)
				{
					pensionAmnt = 18375;
				}
			}
			else
			{
				if(Number(pensionAmnt)>22500)
				{
					pensionAmnt = 22500;
				}
			}
			
			document.getElementById("txtTotPnsnAmt").value = Math.round(pensionAmnt);
			document.getElementById("txtTotlPnsnAmt").value = Math.round(pensionAmnt);
			document.getElementById("txtMonthAmt").value =Math.round(pensionAmnt/3); //Monthly Commuted Pension amount
			document.getElementById("txtComPnsnAmt").value=Math.round(pensionAmnt/3); // Commuted Pension amount
			document.getElementById("txtCVPAmt").value=Math.round((Math.round(pensionAmnt/3))*12*(cvpRate));   //CVP Amount
			//Reduced Pension Amount
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
			
			
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("txtDateOfExpiry"), "<") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("txtDateOfExpiry"), "=") == true)
			{
				document.getElementById("txtServGratyAmt").readOnly=true;
				document.getElementById("txtServGratyAmt").value=0;
				// Total DCRG Amount
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
			}
			else
			{
				document.getElementById("txtTotDCRGAmt").readOnly=true;
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
				// Service Gratuity
				/**************************************************************************/
				if(Number(noOfSms) < 10)
				{
					document.getElementById("txtServGratyAmt").value=0;
				}
				else if(Number(noOfSms) >= 10 && Number(noOfSms) < 20)
				{
					document.getElementById("txtServGratyAmt").value= (((Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))/4)*6);
				}
				/**************************************************************************/
				
				if(Number(document.getElementById("txtServGratyAmt").value) > 350000)
				{
					document.getElementById("txtServGratyAmt").value = 350000;
				}
			}
			//in case of death of employee while in service then DCRG is paid according to following conditions.....
			if(compareTwoDates(document.getElementById("txtDateOfExpiry"),document.getElementById("txtDateOfRetiremt"), "<") == true)
			{
				document.getElementById("txtTotDCRGAmt").disabled = false;
				if(Number(noOfSms) < 2)
				{
					document.getElementById("txtTotDCRGAmt").value= 2 * (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value));
				}
				else if(Number(noOfSms) >= 2 && Number(noOfSms) <10)
				{
					document.getElementById("txtTotDCRGAmt").value= 6 * (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value));
				}
				else if(Number(noOfSms) >= 10 && Number(noOfSms) <40)
				{
					document.getElementById("txtTotDCRGAmt").value= 12 * (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value));
				}
				else if(Number(noOfSms)  >= 40)
				{
					document.getElementById("txtTotDCRGAmt").value= (Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))* (Number(noOfSms)/2);
				}
			}
			
			//Maximum Gratuity amount must not exceed 350000 for 5th pay commission
			if(Number(document.getElementById("txtTotDCRGAmt").value) > 350000)
			{
				document.getElementById("txtTotDCRGAmt").value = 350000;
			}
						
			//FP1 Amount
			if((Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value)/2) <= Math.round(pensionAmnt))
			{
				document.getElementById("txtFP1TotlAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value)/2);
			}
			else
			{
				document.getElementById("txtFP1TotlAmt").value=Math.round(pensionAmnt);
			}
			//FP2 Amount
			document.getElementById("txtFP2TotlAmt").value=((Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*0.30);
		}
		if(document.getElementById("cmbPayComsn").options[document.getElementById("cmbPayComsn").selectedIndex].text == "6th Pay Commission")
		{
			for(var k =0;k<10;k++)
			{
				pensionAmntArr[k]=Number(totalArr[0].value);
			}
			//(emolumentToDate is less than 31/10/2006 then consider pensionAmnt = (total /2) * (noOfSms/66))
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate"), "<") == true)
			{
				pensionAmnt = Number(pensionAmntArr[9]);
			}
			//last emolument  is : pensionAmntArr[9]
			if(Number(totalArr[0].value) >= Number(pensionAmntArr[9]))
			{
				pensionAmnt = Number(totalArr[0].value) ;
			}
			else
			{
				pensionAmnt = Number(pensionAmntArr[9]) ;
			}
			if(Number(pensionAmnt) > 67000)
			{
				pensionAmnt = 67000;
			}
			pensionAmnt = (pensionAmnt/2) *  (Number(noOfSms)/66);

			document.getElementById("txtTotPnsnAmt").value = Math.round(pensionAmnt); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math.round(pensionAmnt); // Total Pension ReadOnly
			document.getElementById("txtMonthAmt").value =(Math.round(pensionAmnt)*4)/10; //Monthly Commuted Pension amount
			document.getElementById("txtComPnsnAmt").value=(Math.round(pensionAmnt)*4)/10; // Commuted Pension amount
			document.getElementById("txtCVPAmt").value=Math.round(((Math.round(pensionAmnt)*4/10))*12*(cvpRate));   //CVP
						
			//Reduced Pension Amount
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
			
			//consider the age on next birth date and get CVP Rate as per revised CVP Rate for pensioner retired after 27/02/2009 : remaining
			
			// check wether the pensioner is retired btwn 01/01/2006 and 27/02/2009 : 
			// if yes then two option should be given a.Not to revise CVP b. Apply for 40% of revised pension

		//	if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate1"), ">") == true && compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate2"), "<") == true)
			{
				//condition 10 is remaining.........
			}
			
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"), document.getElementById("txtDateOfExpiry"), "<") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"), document.getElementById("txtDateOfExpiry"), "=") == true)
			{
				document.getElementById("txtServGratyAmt").readOnly=true;
				document.getElementById("txtServGratyAmt").value=0;
				// Total DCRG Amount
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
			}
			else
			{
				document.getElementById("txtTotDCRGAmt").readOnly=true;
				if(paycomm == "5THPAYCOMSN"){
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}else{
					document.getElementById("txtTotDCRGAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4);
				}
				// Service Gratuity
				if(Number(noOfSms) < 20)
				{
					document.getElementById("txtServGratyAmt").value=(Number(document.getElementById("txtDP").value)+Number(document.getElementById("txtBasicPay").value))*(Number(noOfSms)/4) * 2;
				}
			}
			
			//in case of death of employee while in service then DCRG is paid according to following conditions.....
			if(compareTwoDates(document.getElementById("txtDateOfExpiry"),document.getElementById("txtDateOfRetiremt"), "<") == true)
			{
				document.getElementById("txtTotDCRGAmt").disabled = false;
				document.getElementById("txtTotDCRGAmt").value= 2 * (Number(document.getElementById("txtTotDCRGAmt").value));
			}
			
			//check wether the pensioner retirement date is greter than or equal to 01/09/2009 or not

			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate3"), ">") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate3"), "=") == true)
			{
				if(Number(document.getElementById("txtTotDCRGAmt").value) > 700000)
				{
					document.getElementById("txtTotDCRGAmt").value = 700000;
				}
			}
			//check wether the pensioner retirement date is less than 01/09/2009  and greater than 31/12/2005
			
			if(compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate3"), "<") == true || compareTwoDates(document.getElementById("txtDateOfRetiremt"),document.getElementById("hdnTempDate4"), ">") == true)
			{
				
				if(Number(document.getElementById("txtTotDCRGAmt").value) > 500000)
				{
					document.getElementById("txtTotDCRGAmt").value = 500000;
				}
			}
			
			//FP1 Amount
			if((Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value)/2) <= Math.round(pensionAmnt))
			{
				document.getElementById("txtFP1TotlAmt").value=(Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value)/2);
			}
			else
			{
				document.getElementById("txtFP1TotlAmt").value=Math.round(pensionAmnt);
			}
			//FP2 Amount
			document.getElementById("txtFP2TotlAmt").value=((Number(document.getElementById("txtGradePay").value)+Number(document.getElementById("txtBasicPay").value))*0.30);
		}
	}
	/******************************************************Calculate Pension Amounts************Ends****************************************************/
	return;
}
/*---------Comparing two dates---------*/
function compareTwoDates(fieldName1,fieldName2,flag)
{
	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;
    var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var day1=parseFloat(la_Date1[0]);
    var month1=parseFloat(la_Date1[1]);
    var year1=parseFloat(la_Date1[2]);

    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    var day2=parseFloat(la_Date2[0]);
    var month2=parseFloat(la_Date2[1]);
    var year2=parseFloat(la_Date2[2]);

    if (year2 == year1 && month2 == month1 && day2 == day1)
    {
    	if(flag == '=')
    	{
    	    return true;
    	}
    	else
    	{
	        return false;
	    }
    }
    else if( year2 > year1 )
    {
    	if(flag == '<')
    	{
    		 return true;
    	}
    	else
    	{
    		return false;
    	}
       
    }
    else if( year2 < year1 && flag != '=')
    {
        if(flag == '<')
        {
    	    return false;
    	}
    	else if(flag == '>')
    	{
    		return true;
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
        	if(flag == '<')
        	{
        		return true;
        		
        	}
        	else
        	{
        		return false;
        	}
        }
        else if( month2 < month1 )
        {     
            if(flag == '<')
	        {
    		    return false;
	    	}
    		else if(flag == '>')
    		{
    			return true;
    		}
        }
        else
        {
            if( day2 > day1 )
            {
            	if(flag == '<')
            	{
            		return true;
            	}
            	else
            	{
            		return false;
            	}
            }
            else if( day2 < day1 )
            {
                 if(flag == '<')
			     {
                	 return false;
			   	}
		    	else if(flag == '>')
    			{
		    		return true;
		    	}
            }
        }
    }
}
function setCVPAmt()
{
	document.getElementById("txtComPnsnAmt").value=document.getElementById("txtMonthAmt").value;
}

function setTotalPnsnAmt()
{
	document.getElementById("txtTotlPnsnAmt").value=document.getElementById("txtTotPnsnAmt").value;
	document.getElementById("txtRedsPnsnAmt").value=Number(document.getElementById("txtTotlPnsnAmt").value)-Number(document.getElementById("txtComPnsnAmt").value);
}
function getValidEmoluments()
{
	
	var inwardPensionId = document.getElementById("hdnInwardPensionId").value;
	var url = "ifms.htm?actionFlag=getValidEmoluments&inwardPensionId="+inwardPensionId; 
	var myAjax = new Ajax.Request(url,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {validEmolumentsCaseStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});
}
function validEmolumentsCaseStateChanged(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var totalRecords = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	
	/*var count=1;
	while(count<=(2*totalRecords))
	{
		var optn = document.createElement("OPTION");
		optn.value = XmlHiddenValues[0].childNodes[count].childNodes[0].nodeValue;
		optn.text = XmlHiddenValues[0].childNodes[count+1].childNodes[0].nodeValue;
		document.getElementById("cmbDisplayBranchName").options.add(optn);
		count=count+2;
	}
		
	var cmbTargetBranchName = document.forms.auditorMappingInfo.cmbTargetBranchName;
		
	for(i=cmbTargetBranchName.length-1;i>=0;i--)
	{
		for(j=1;j<cmbDisplayBranchName.length;j++)
		{
			if(cmbTargetBranchName.options[i] != null)
			{
				if(cmbTargetBranchName.options[i].value == cmbDisplayBranchName.options[j].value)
				{
					cmbTargetBranchName.remove(cmbTargetBranchName.options[i].index);
				}
			}
		}
	}*/
}
function getCVPRateFromAge(Age,PayCommission)
{
	var uri='ifms.htm?actionFlag=getCvpRate&Age='+Age+'&PayCommission='+PayCommission;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {getCVPRateFromAgeCaseStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});
	
}
function getCVPRateFromAgeCaseStateChanged(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCCVPRATE');
	var cvpRate = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	
	if(cvpRate != "")
	{
		document.getElementById("hdnCvpRate").value=cvpRate;
		document.getElementById("txtCvpRate").value=cvpRate;
	}
}
function ageFromDOBAndDOR(birthDate,retirementDate)
{
	var birthArrDate=birthDate.split("/");							
	var bday=birthArrDate[0];
	var bmo=birthArrDate[1];
	var byr=parseInt(birthArrDate[2]);
	var ClassOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	var age;
	//alert(ClassOfPnsn);
	var retirementArrDate=retirementDate.split("/");							
	var rday=retirementArrDate[0];
	var rmo=retirementArrDate[1];
	var ryr=parseInt(retirementArrDate[2]);
	//alert(bmo);
	//alert(rmo);
	//added by shraddha for superann case 
	if(ClassOfPnsn=="SUPERANNU"){
	if((bday==01 && bmo==01) ){
		//alert("Inside if");
		age = byr - 1;
		 
	}
	else {
		age=byr;
		//alert ("inside else");
	}
	}
	
	//added by shraddha
	else{
		
		//alert("Inside else hii");
	//if(bmo < rmo || bmo==rmo){
		if(bmo < rmo || (bmo==rmo && bday<rday)){
	age = byr;
	}
	else{
		age=byr+1;
	}
	
	}
	//alert(age);
	ryr = Number(ryr)+1;
	//alert(ryr);
	if(isNaN(ryr-age)==true)
	{
		return 0;
	}
	else if((ryr-age)>150 || (ryr-age)<=-1)			
	{			
		return "N.A.";
	}
	else
	{	
		return (Number(ryr-age));	
		
	}
}
function resetAllAmnt()
{
	document.getElementById("txtMonthAmt").value=0;
	document.getElementById("txtCVPAmt").value=0;
	document.getElementById("txtTotPnsnAmt").value=0;
	document.getElementById("txtRedsPnsnAmt").value=0;
	document.getElementById("txtTotlPnsnAmt").value=0;
	document.getElementById("txtComPnsnAmt").value=0;
	document.getElementById("txtServGratyAmt").value=0;
	document.getElementById("txtTotDCRGAmt").value=0;
	document.getElementById("txtFP1TotlAmt").value=0;
	document.getElementById("txtFP2TotlAmt").value=0;
}

function resetAllAmntRevision()
{
	document.getElementById("txtNewMonthAmt").value=0;
	document.getElementById("txtDiffMonthAmt").value=0;
	document.getElementById("txtNewCVPAmt").value=0;
	document.getElementById("txtDiffCVPAmt").value=0;
	document.getElementById("txtNewTotPnsnAmt").value=0;
	document.getElementById("txtNewRedsPnsnAmt").value=0;
	document.getElementById("txtNewTotlPnsnAmt").value=0;
	document.getElementById("txtNewComPnsnAmt").value=0;
	document.getElementById("txtDiffRedsPnsnAmt").value=0;
	document.getElementById("txtDiffTotlPnsnAmt").value=0;	
	document.getElementById("txtNewServGratyAmt").value=0;
	document.getElementById("txtDiffServGratyAmt").value=0;
	document.getElementById("txtNewTotDCRGAmt").value=0;
	document.getElementById("txtDiffTotDCRGAmt").value=0;
	document.getElementById("txtNewFP1TotlAmt").value=0;
	document.getElementById("txtDiffFP1TotlAmt").value=0;
	document.getElementById("txtNewFP2TotlAmt").value=0;
	document.getElementById("txtDiffFP2TotlAmt").value=0;
}

function newPensionCalculation(){
	
	
	
	if(IsEmptyFun("cmbClassOfPnsn",CLSOFPNSN,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbPayComsn",PAYCOMSN,'0')==false)
	{
		return false;
	}	
	if(IsEmptyFun("txtDateOfBirth",DATEOFBIRTH,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDateOfRetiremt",DATEOFRETMNT,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDateOfStartingService",DATEOFSTARTSERV,'0')==false)
	{
		return false;
	}
	var CalcGridLength=document.getElementById("hidCalcGridSize").value;
	var DOR = document.getElementById("txtDateOfRetiremt").value;
	var DPConsideringDate = "01/08/2004";
	var payComm = document.getElementById("cmbPayComsn").value;
	//alert("payComm"+payComm);
	if(CalcGridLength>=1)
	 {
		for(var rowServCnt=0;rowServCnt<Number(CalcGridLength);rowServCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtPeriodFromDate"+rowServCnt,FROM,'1')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAvgPayBasic"+rowServCnt,BASICPAY,'1')==false)
				{
					return false;
				}			
				
			}
			catch(ex)
			{
				
			}
		}
	 }
	
	var ClassOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	var PayComsn = document.getElementById("cmbPayComsn").value;
	var PnsnCatg = document.getElementById("cmbPnsnCatg").value;
	var DOB = document.getElementById("txtDateOfBirth").value;
	var DOR = document.getElementById("txtDateOfRetiremt").value;
	var DOJ = document.getElementById("txtDateOfStartingService").value;
	var DOE = document.getElementById("txtDateOfExpiry").value;
	
	var FirstDate6thPC = document.getElementById("hdnFirstDate6thPC").value;
	var ActualDate6thPC = document.getElementById("hdnActualDate6thPC").value;
	var DCRGMinDate = document.getElementById("hdnDCRGMinDate").value;
	var DCRGMaxDate = document.getElementById("hdnDCRGMaxDate").value;
	
	var commutPer = document.getElementById("txtDoWantCommute").value;
	
	if(PnsnCatg == 'High Court' && ClassOfPnsn != 'FAMILYPNSN'){
		if(commutPer == ""){
			alert('Please enter commuted percentage')
			return false;
		}
	}
	
	var lengthServBrkDtls = document.getElementById("tblServBrkDtls").rows.length;
	for(var i=0;i<=Number(lengthServBrkDtls);i++){
		calTotalSrvcBrk(i);
	}
	calActualServiceDays();
	setTotalDaysOfSrvcBrk('txtDateOfBrkFrom','txtDateOfBrkTo');
	
	var DORArr = DOR.split("/");	
	var DOBArr = DOB.split("/");
	
	var totalSms = smsCalculation();
	var lastEmolument = "";
	var npaAllow = false;
	var lastNPA = 0;
	var avgEmolument = document.getElementById("txtAvgPay").value;
	
	
	
	if(PnsnCatg == 'Medical Officer')
	{
		if(document.getElementById("chkBoxNPA").checked == true){
			npaAllow = true;
		}
	}
	var PensionAmount = "";
	var serviceGratiity = "";
	var DCRGamount = "";
	var cvpAmount = "";
	var commutedPension = "";
	var payCom = "";
	var efpAmount = "";
	var rrAmount = "";
	
	var lastBasic = document.getElementById("txtAvgPayBasic9").value;
	var lastGradePay = document.getElementById("txtAvgPayDP9").value;
	//if(npaAllow){
		lastNPA = document.getElementById("txtNPA9").value;
	//}
	
	if(PayComsn == '6THPAYCOMSN'){
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>')){
			if(Number(lastBasic)>67000)
				lastBasic = 67000
			if(Number(lastGradePay)>12000)
				lastGradePay = 12000
				
			lastEmolument = Number(lastBasic)+Number(lastGradePay)+Number(lastNPA);
		//Pension Calculation
		
		if(Number(totalSms) > 20){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				//No pension calculate
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					
					PensionAmount = (Number(lastEmolument)/2)*(Number(totalSms)/66);
					
				}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
				}
				
			}else{
				PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
			}
			if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS')
			{
			
				if(Number(PensionAmount) > 39500)
					PensionAmount = 39500;
				
				
				if(Number(PensionAmount) < 2882 && Number(PensionAmount) > 0)
					PensionAmount = 2882;
			}else{
				if(Number(PensionAmount) > 45000)
					PensionAmount = 45000;
				
				if(Number(PensionAmount) < 3500 && Number(PensionAmount) > 0)
					PensionAmount = 3500;
			}
		
			document.getElementById("txtTotPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension ReadOnly
		
		}
		
		
			//Service Gratiity
		
			
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				
				if(Number(totalSms) < 2)
				{
					serviceGratiity = 2 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 2 && Number(totalSms) <10)
				{
					serviceGratiity = 6 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 10 && Number(totalSms) <40)
				{
					serviceGratiity = 12 * Number(lastEmolument);
					
				}
				else if(Number(totalSms)  >= 40)
				{
					serviceGratiity = (Number(lastEmolument)/2) * Number(totalSms);
				
				}
				
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
					
					
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}else if(compareDatesWithoutAlert(DOE,DCRGMinDate,'>')){
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
				}		
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
							if(Number(serviceGratiity)>700000)
								serviceGratiity = 700000;
						
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
					if(Number(serviceGratiity)>700000)
						serviceGratiity = 700000;
				
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
				}
			}else{
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						
						//edited by shraddha
						if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						else{
						//edited by aditya
							if(Number(serviceGratiity)>700000)
								serviceGratiity = 700000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						//edited by shraddha
						if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						
						else{
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
						}
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
					
						//edited by shraddha
						if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						else{
						//edited by aditya
					if(Number(serviceGratiity)>700000)
						serviceGratiity = 700000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						//edited by shraddha
						if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						else{
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
						}
					}
				}
			}
			
			document.getElementById("txtServGratyAmt").value = Math.ceil(serviceGratiity);	
			
			
		//DCRG calculation
		
		var withheldAmt= document.getElementById("txtWitheldGratuity").value;
		//alert ("withheldAmt*****"+withheldAmt);
		
		DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
		
		document.getElementById("txtTotDCRGAmt").value = Math.ceil(DCRGamount);
		
		//CVP Calculation 
		
		
		if(document.getElementById("radioDoWantCommuteYes").checked && Number(totalSms) > 20){
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '5th Pay Commission';
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '6th Pay Commission';
			}
			document.getElementById("txtComPnsnAmt").value = Math.ceil(commutedPension);
			document.getElementById("txtMonthAmt").value = Math.ceil(commutedPension);
			var age = ageFromDOBAndDOR(DOB,DOR);
		
			if(Number(age) > 81)
			{
				age = 81;
			}
			getCVPRateFromAge(age,payCom);
			cvpRate = document.getElementById("hdnCvpRate").value;
			
			document.getElementById("txtCVPAmt").value=Math.ceil(Number(commutedPension)*12*Number(cvpRate));
			
		}
		if(ClassOfPnsn != 'FAMILYPNSN'){
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
		}
		//	EFP and RR amount claculation
		
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
				efpAmount = Number(lastEmolument)/2;
			}
			
		}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
				//cahanged on 18-3-2016
				efpAmount = Number(lastEmolument)/2;
			}
		}
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(efpAmount) > 39500)
				efpAmount = 39500;
			
			if(Number(efpAmount) < 2882 && Number(efpAmount) > 0)
				efpAmount = 2882;
		}else{
			if(Number(efpAmount) > 45000)
				efpAmount = 45000;
			
			if(Number(efpAmount) < 3500 && Number(efpAmount) > 0)
				efpAmount = 3500;
		}
		document.getElementById("txtFP1TotlAmt").value = Math.ceil(efpAmount);
		
		rrAmount = Number(lastEmolument) * 0.3;
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(rrAmount) > 39500)
				rrAmount = 39500;
			
			if(Number(rrAmount) < 2882 && Number(rrAmount) > 0)
				rrAmount = 2882;
		}else{
			if(Number(rrAmount) > 45000)
				rrAmount = 45000;
			
			if(Number(rrAmount) < 3500 && Number(rrAmount) > 0)
				rrAmount = 3500;
		}
		
		document.getElementById("txtFP2TotlAmt").value = Math.ceil(rrAmount);
		}else{
			resetAllAmnt();
		}
	}
	
	
	//added by shraddha for Padmanabhan comm on 29/12/2015
	
	else if(PayComsn == 'PadmanabhanComm'){
		
		
		
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>')){
			if(Number(lastBasic)>76450)
				lastBasic = 76450
			if(Number(lastGradePay)>12000)
				lastGradePay = 12000
				
			lastEmolument = Number(lastBasic)+Number(lastGradePay)+Number(lastNPA);
		//Pension Calculation
		
		if(Number(totalSms) > 20){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				//No pension calculate
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					
					PensionAmount = (Number(lastEmolument)/2)*(Number(totalSms)/66);
					
				}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
				}
				
			}else{
				PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
			}
			if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS')
			{
				if(Number(PensionAmount) > 39500)
					PensionAmount = 39500;
				
				if(Number(PensionAmount) < 2882 && Number(PensionAmount) > 0)
					PensionAmount = 2882;
			}else{
				if(Number(PensionAmount) > 45000)
					PensionAmount = 45000;
				
				if(Number(PensionAmount) < 3500 && Number(PensionAmount) > 0)
					PensionAmount = 3500;
			}
		
			document.getElementById("txtTotPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension ReadOnly
		
		}//Service Gratiity
		
		
		
		if(ClassOfPnsn == 'FAMILYPNSN'){
			
			if(Number(totalSms) < 2)
			{
				serviceGratiity = 2 * Number(lastEmolument);
				
			}
			else if(Number(totalSms) >= 2 && Number(totalSms) <10)
			{
				serviceGratiity = 6 * Number(lastEmolument);
				
			}
			else if(Number(totalSms) >= 10 && Number(totalSms) <40)
			{
				serviceGratiity = 12 * Number(lastEmolument);
				
			}
			else if(Number(totalSms)  >= 40)
			{
				serviceGratiity = (Number(lastEmolument)/2) * Number(totalSms);
			
			}
			
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
			
				if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
					//edited by aditya
					if(Number(serviceGratiity)>1000000)
						serviceGratiity = 1000000;
				}
				
				
		}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				
				if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
					//edited by aditya
					if(Number(serviceGratiity)>1000000)
						serviceGratiity = 1000000;
				}else if(compareDatesWithoutAlert(DOE,DCRGMinDate,'>')){
					if(Number(serviceGratiity)>1000000)
						serviceGratiity = 1000000;
				}
			}		
		}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
			
			serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
			
		
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
								|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
					//edited by aditya
						if(Number(serviceGratiity)>1000000)
							serviceGratiity = 1000000;
					
				}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
						|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
				
					if(Number(serviceGratiity)>1000000)
						serviceGratiity = 1000000;
				}
			}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
						|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
					//edited by aditya
				if(Number(serviceGratiity)>1000000)
					serviceGratiity = 1000000;
			
				}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
						|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
		
					if(Number(serviceGratiity)>1000000)
						serviceGratiity = 1000000;
				}
			}
		}else{
			
			serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
			
			
			
			
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
								|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
					
					//edited by shraddha
					if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
						if(Number(serviceGratiity)>1000000)
							serviceGratiity = 1000000;
					}
					else{
					//edited by aditya
						if(Number(serviceGratiity)>1000000)
							serviceGratiity = 1000000;
					}
				}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
						|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
				
					//edited by shraddha
					if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
						if(Number(serviceGratiity)>1000000)
							serviceGratiity = 1000000;
					}
					
					else{
					if(Number(serviceGratiity)>1000000)
						serviceGratiity = 1000000;
					}
				}
			}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
						|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
				
					//edited by shraddha
					if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
						if(Number(serviceGratiity)>1000000)
							serviceGratiity = 1000000;
					}
					else{
					//edited by aditya
				if(Number(serviceGratiity)>1000000)
					serviceGratiity = 1000000;
					}
				}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
						|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
		
					//edited by shraddha
					if(PnsnCatg == 'IAS' || PnsnCatg == 'IPS' || PnsnCatg == 'IFS'){
						if(Number(serviceGratiity)>1000000)
							serviceGratiity = 1000000;
					}
					else{
					if(Number(serviceGratiity)>1000000)
						serviceGratiity = 1000000;
					}
				}
			}
		}
		
		document.getElementById("txtServGratyAmt").value = Math.ceil(serviceGratiity);	
		
		
	//DCRG calculation
	
	 withheldAmt= document.getElementById("txtWitheldGratuity").value;
	//alert ("withheldAmt*****"+withheldAmt);
	
	DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
	
	document.getElementById("txtTotDCRGAmt").value = Math.ceil(DCRGamount);
	
		//CVP Calculation 
		
		
		if(document.getElementById("radioDoWantCommuteYes").checked && Number(totalSms) > 20){
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '5th Pay Commission';
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = 'Padmanabhan Comm.';
			}
			document.getElementById("txtComPnsnAmt").value = Math.ceil(commutedPension);
			document.getElementById("txtMonthAmt").value = Math.ceil(commutedPension);
			var age = ageFromDOBAndDOR(DOB,DOR);
			
			if(Number(age) > 81)
			{
				age = 81;
			}
			getCVPRateFromAge(age,payCom);
			cvpRate = document.getElementById("hdnCvpRate").value;
			
			document.getElementById("txtCVPAmt").value=Math.ceil(Number(commutedPension)*12*Number(cvpRate));
			
		}
		if(ClassOfPnsn != 'FAMILYPNSN'){
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
		}
		//	EFP and RR amount claculation
		
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
			
		}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
		}
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(efpAmount) > 39500)
				efpAmount = 39500;
			
			if(Number(efpAmount) < 2882 && Number(efpAmount) > 0)
				efpAmount = 2882;
		}else{
			if(Number(efpAmount) > 45000)
				efpAmount = 45000;
			
			if(Number(efpAmount) < 3500 && Number(efpAmount) > 0)
				efpAmount = 3500;
		}
		document.getElementById("txtFP1TotlAmt").value = Math.ceil(efpAmount);
		
		rrAmount = Number(lastEmolument) * 0.3;
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(rrAmount) > 39500)
				rrAmount = 39500;
			
			if(Number(rrAmount) < 2882 && Number(rrAmount) > 0)
				rrAmount = 2882;
		}else{
			if(Number(rrAmount) > 45000)
				rrAmount = 45000;
			
			if(Number(rrAmount) < 3500 && Number(rrAmount) > 0)
				rrAmount = 3500;
		}
		
		document.getElementById("txtFP2TotlAmt").value = Math.ceil(rrAmount);
		}else{
			resetAllAmnt();
		}
	}
	//--------------------------ends----------------------
	else if(PayComsn == '5THPAYCOMSN'){
		
		lastEmolument = Number(lastBasic)+Number(lastGradePay)+Number(lastNPA);
		
		if(Number(totalSms) > 20){
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				PensionAmount = (Number(avgEmolument)/2 * Number(totalSms) / 66);
			}else{
				PensionAmount = (Number(avgEmolument)/2 * Number(totalSms) / 66);
			}
			document.getElementById("txtTotPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension ReadOnly
			
			if(document.getElementById("radioDoWantCommuteYes").checked){
				
				document.getElementById("txtMonthAmt").value =Math.ceil(PensionAmount/3); //Monthly Commuted Pension amount
				document.getElementById("txtComPnsnAmt").value=Math.ceil(PensionAmount/3); // Commuted Pension amount(same as monthly commuted amount)
				
				var age = ageFromDOBAndDOR(DOB,DOR);
				
				if(Number(age) > 85)
				{
					age = 85;
				}
				getCVPRateFromAge(age,'5th Pay Commission');
				cvpRate = document.getElementById("hdnCvpRate").value;
				
				document.getElementById("txtCVPAmt").value=Math.ceil(Number(document.getElementById("txtComPnsnAmt").value)*12*Number(cvpRate));
			}
			
		}
		
		/*else if(Number(totalSms) <= 20){
			
			serviceGratiity = (Number(lastEmolument)/4) *6 ;
		}
		document.getElementById("txtServGratyAmt").value = Math.ceil(serviceGratiity);*/
		
		if(Number(totalSms) < 2)
		{
			serviceGratiity = 2 * Number(lastEmolument);
			
		}
		else if(Number(totalSms) >= 2 && Number(totalSms) <10)
		{
			serviceGratiity = 6 * Number(lastEmolument);
			
		}
		else if(Number(totalSms) >= 10 && Number(totalSms) <40)
		{
			serviceGratiity = 12 * Number(lastEmolument);
			
		}
		else if(Number(totalSms)  >= 40)
		{
			serviceGratiity = (Number(lastEmolument)/2) * Number(totalSms);
			
		}
		
		if(Number(serviceGratiity)>350000)
			serviceGratiity = 350000;
		
		
		document.getElementById("txtServGratyAmt").value = Math.ceil(serviceGratiity);
		
		if(ClassOfPnsn != 'FAMILYPNSN'){
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
		}
		
		 withheldAmt= document.getElementById("txtWitheldGratuity").value;
			//alert ("withheldAmt*****"+withheldAmt);
			
			DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
		
		
		document.getElementById("txtTotDCRGAmt").value = serviceGratiity;
		
		efpAmount = (Number(lastEmolument)/2 < Number(PensionAmount)) ? Number(lastEmolument)/2 : PensionAmount;
		document.getElementById("txtFP1TotlAmt").value = Math.ceil(efpAmount);
		
		rrAmount = Number(lastEmolument) * 0.3;
		document.getElementById("txtFP2TotlAmt").value = Math.ceil(rrAmount);
	}
	
	//Added by shraddha for 7th pay on 27/01/2017
	if(PayComsn == '7THPAYCOMSN'){
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>')){
			/*if(Number(lastBasic)>67000)
				lastBasic = 67000
			if(Number(lastGradePay)>12000)
				lastGradePay = 12000
				*/
			lastEmolument = Number(lastBasic)+Number(lastGradePay)+Number(lastNPA);
		//Pension Calculation
		
		if(Number(totalSms) > 20){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				//No pension calculate
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					
					PensionAmount = (Number(lastEmolument)/2)*(Number(totalSms)/66);
					
				}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
				}
				
			}else{
				PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
			}
			if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS')
			{
				if(Number(PensionAmount) > 125000)
					PensionAmount = 125000;
				
				if(Number(PensionAmount) < 9000 && Number(PensionAmount) > 0)
					PensionAmount = 9000;
			}else{
				if(Number(PensionAmount) > 125000)
					PensionAmount = 125000;
				
				if(Number(PensionAmount) < 9000 && Number(PensionAmount) > 0)
					PensionAmount = 9000;
			}
		
			document.getElementById("txtTotPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension ReadOnly
		
		}
			//Service Gratiity
		
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				
				if(Number(totalSms) < 2)
				{
					serviceGratiity = 2 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 2 && Number(totalSms) <10)
				{
					serviceGratiity = 6 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 10 && Number(totalSms) <40)
				{
					serviceGratiity = 12 * Number(lastEmolument);
					
				}
				else if(Number(totalSms)  >= 40)
				{
					serviceGratiity = (Number(lastEmolument)/2) * Number(totalSms);
				
				}
				
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
					
					
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}else if(compareDatesWithoutAlert(DOE,DCRGMinDate,'>')){
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
				}		
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
					if(Number(serviceGratiity)>2000000)
						serviceGratiity = 2000000;
				
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
				}
			}else{
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						else{
						//edited by aditya
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						
						else{
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
						}
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
					
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						else{
						//edited by aditya
					if(Number(serviceGratiity)>2000000)
						serviceGratiity = 2000000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						else{
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
						}
					}
				}
			}
			
			document.getElementById("txtServGratyAmt").value = Math.ceil(serviceGratiity);	
			
			
		//DCRG calculation
		
		var withheldAmt= document.getElementById("txtWitheldGratuity").value;
		//alert ("withheldAmt*****"+withheldAmt);
		
		DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
		
	
		
		document.getElementById("txtTotDCRGAmt").value = Math.ceil(DCRGamount);
		//CVP Calculation 
		
		
		if(document.getElementById("radioDoWantCommuteYes").checked && Number(totalSms) > 20){
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '5th Pay Commission';
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '6th Pay Commission';
			}
			document.getElementById("txtComPnsnAmt").value = Math.ceil(commutedPension);
			document.getElementById("txtMonthAmt").value = Math.ceil(commutedPension);
			var age = ageFromDOBAndDOR(DOB,DOR);
		
			if(Number(age) > 81)
			{
				age = 81;
			}
			getCVPRateFromAge(age,payCom);
			cvpRate = document.getElementById("hdnCvpRate").value;
			
			document.getElementById("txtCVPAmt").value=Math.ceil(Number(commutedPension)*12*Number(cvpRate));
			
		}
		if(ClassOfPnsn != 'FAMILYPNSN'){
			document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value) - Number(document.getElementById("txtComPnsnAmt").value);
		}
		//	EFP and RR amount claculation
		
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = Number(lastEmolument)/2;
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
			
		}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = Number(lastEmolument)/2;
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
		}
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			/*if(Number(efpAmount) > 39500)
				efpAmount = 39500;
			
			if(Number(efpAmount) < 2882 && Number(efpAmount) > 0)
				efpAmount = 2882;*/
			if(efpAmount > 75000 && Number(efpAmount) > 0){
				efpAmount=75000;
			}
			if(efpAmount < 9000 && Number(efpAmount) > 0){
				efpAmount=9000;
			}
		}else{
			/*if(Number(efpAmount) > 45000)
				efpAmount = 45000;
			
			if(Number(efpAmount) < 3500 && Number(efpAmount) > 0)
				efpAmount = 3500;*/
			if(efpAmount > 75000 && Number(efpAmount) > 0){
				efpAmount=75000;
			}
			if(efpAmount < 9000 && Number(efpAmount) > 0){
				efpAmount=9000;
			}
		}
		
		//Added by shraddha for EFP,FP limit
		
		
		document.getElementById("txtFP1TotlAmt").value = Math.ceil(efpAmount);
		
		rrAmount = Number(lastEmolument) * 0.3;
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			/*if(Number(rrAmount) > 39500)
				rrAmount = 39500;
			
			if(Number(rrAmount) < 2882 && Number(rrAmount) > 0)
				rrAmount = 2882;*/
			if(rrAmount > 75000 && Number(rrAmount) > 0){
				rrAmount= 75000;
			}
			if(rrAmount < 9000 && Number(rrAmount) > 0){
				rrAmount=9000;
			}
		}else{
			/*if(Number(rrAmount) > 45000)
				rrAmount = 45000;
			
			if(Number(rrAmount) < 3500 && Number(rrAmount) > 0)
				rrAmount = 3500;*/
			if(rrAmount > 75000 && Number(rrAmount) > 0){
				rrAmount= 75000;
			}
			if(rrAmount < 9000 && Number(rrAmount) > 0){
				rrAmount=9000;
			}
		}
		
		document.getElementById("txtFP2TotlAmt").value = Math.ceil(rrAmount);
		}else{
			resetAllAmnt();
		}
	}
	

}

function newPensionCalculationRevision(){
	
	if(IsEmptyFun("cmbClassOfPnsn",CLSOFPNSN,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbPayComsn",PAYCOMSN,'0')==false)
	{
		return false;
	}	
	if(IsEmptyFun("txtDateOfBirth",DATEOFBIRTH,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDateOfRetiremt",DATEOFRETMNT,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDateOfStartingService",DATEOFSTARTSERV,'0')==false)
	{
		return false;
	}
	var CalcGridLength=document.getElementById("hidCalcGridSize").value;
	var DOR = document.getElementById("txtDateOfRetiremt").value;
	var DPConsideringDate = "01/08/2004";
	var payComm = document.getElementById("cmbPayComsn").value;
	
	if(CalcGridLength>=1)
	 {
		for(var rowServCnt=0;rowServCnt<Number(CalcGridLength);rowServCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtPeriodFromDate"+rowServCnt,FROM,'1')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAvgPayBasic"+rowServCnt,BASICPAY,'1')==false)
				{
					return false;
				}			
				
			}
			catch(ex)
			{
				
			}
		}
	 }
	
	var ClassOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	var PayComsn = document.getElementById("cmbPayComsn").value;
	var PnsnCatg = document.getElementById("cmbPnsnCatg").value;
	var DOB = document.getElementById("txtDateOfBirth").value;
	var DOR = document.getElementById("txtDateOfRetiremt").value;
	var DOJ = document.getElementById("txtDateOfStartingService").value;
	var DOE = document.getElementById("txtDateOfExpiry").value;
	
	var FirstDate6thPC = document.getElementById("hdnFirstDate6thPC").value;
	var ActualDate6thPC = document.getElementById("hdnActualDate6thPC").value;
	var DCRGMinDate = document.getElementById("hdnDCRGMinDate").value;
	var DCRGMaxDate = document.getElementById("hdnDCRGMaxDate").value;
	
	var commutPer = document.getElementById("txtDoWantCommute").value;
	
	if(PnsnCatg == 'High Court' && ClassOfPnsn != 'FAMILYPNSN'){
		if(commutPer == ""){
			alert('Please enter commuted percentage')
			return false;
		}
	}
	
	var lengthServBrkDtls = document.getElementById("tblServBrkDtls").rows.length;
	for(var i=0;i<=Number(lengthServBrkDtls);i++){
		calTotalSrvcBrk(i);
	}
	calActualServiceDays();
	setTotalDaysOfSrvcBrk('txtDateOfBrkFrom','txtDateOfBrkTo');
	
	var DORArr = DOR.split("/");	
	var DOBArr = DOB.split("/");
	
	var totalSms = smsCalculation();
	var lastEmolument = "";
	var npaAllow = false;
	var lastNPA = 0;
	var avgEmolument = document.getElementById("txtAvgPay").value;
	
	
	
	if(PnsnCatg == 'Medical Officer')
	{
		if(document.getElementById("chkBoxNPA").checked == true){
			npaAllow = true;
		}
	}
	var PensionAmount = "";
	var serviceGratiity = "";
	var DCRGamount = "";
	var cvpAmount = "";
	var commutedPension = "";
	var payCom = "";
	var efpAmount = "";
	var rrAmount = "";
	
	var lastBasic = document.getElementById("txtAvgPayBasic9").value;
	var lastGradePay = document.getElementById("txtAvgPayDP9").value;
	//if(npaAllow){
		lastNPA = document.getElementById("txtNPA9").value;
	//}
	
	if(PayComsn == '6THPAYCOMSN'){
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>')){
			if(Number(lastBasic)>67000)
				lastBasic = 67000
			if(Number(lastGradePay)>12000)
				lastGradePay = 12000
				
			lastEmolument = Number(lastBasic)+Number(lastGradePay)+Number(lastNPA);
		//Pension Calculation
		
		if(Number(totalSms) > 20){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				//No pension calculate
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					
					PensionAmount = (Number(lastEmolument)/2)*(Number(totalSms)/66);
					
				}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
				}
				
			}else{
				PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
			}
			if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS')
			{
				if(Number(PensionAmount) > 39500)
					PensionAmount = 39500;
				
				if(Number(PensionAmount) < 2882 && Number(PensionAmount) > 0)
					PensionAmount = 2882;
			}else{
				if(Number(PensionAmount) > 45000)
					PensionAmount = 45000;
				
				if(Number(PensionAmount) < 3500 && Number(PensionAmount) > 0)
					PensionAmount = 3500;
			}
		
			document.getElementById("txtNewTotPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtNewTotlPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension ReadOnly
		
		}
			//Service Gratiity
		
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				
				if(Number(totalSms) < 2)
				{
					serviceGratiity = 2 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 2 && Number(totalSms) <10)
				{
					serviceGratiity = 6 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 10 && Number(totalSms) <40)
				{
					serviceGratiity = 12 * Number(lastEmolument);
					
				}
				else if(Number(totalSms)  >= 40)
				{
					serviceGratiity = (Number(lastEmolument)/2) * Number(totalSms);
				
				}
				
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
					
					
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}else if(compareDatesWithoutAlert(DOE,DCRGMinDate,'>')){
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
				}		
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
							if(Number(serviceGratiity)>700000)
								serviceGratiity = 700000;
						
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
					if(Number(serviceGratiity)>700000)
						serviceGratiity = 700000;
				
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
					}
				}
			}else{
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						else{
						//edited by aditya
							if(Number(serviceGratiity)>700000)
								serviceGratiity = 700000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						
						else{
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
						}
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
					
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						else{
						//edited by aditya
					if(Number(serviceGratiity)>700000)
						serviceGratiity = 700000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>1000000)
								serviceGratiity = 1000000;
						}
						else{
						if(Number(serviceGratiity)>700000)
							serviceGratiity = 700000;
						}
					}
				}
			}
			
			document.getElementById("txtServGratyAmt").value = Math.ceil(serviceGratiity);	
			
			
		//DCRG calculation
		
		var withheldAmt= document.getElementById("txtWitheldGratuity").value;
		//alert ("withheldAmt*****"+withheldAmt);
		
		DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
		
	
		
		document.getElementById("txtTotDCRGAmt").value = Math.ceil(DCRGamount);
		//CVP Calculation 
		
		
		if(document.getElementById("radioDoWantCommuteYes").checked && Number(totalSms) > 20){
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '5th Pay Commission';
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '6th Pay Commission';
			}
			document.getElementById("txtNewComPnsnAmt").value = Math.ceil(commutedPension);
			document.getElementById("txtNewMonthAmt").value = Math.ceil(commutedPension);
			var age = ageFromDOBAndDOR(DOB,DOR);
		
			if(Number(age) > 81)
			{
				age = 81;
			}
			getCVPRateFromAge(age,payCom);
			cvpRate = document.getElementById("hdnCvpRate").value;
			
			document.getElementById("txtNewCVPAmt").value=Math.ceil(Number(commutedPension)*12*Number(cvpRate));
			
		}
		if(ClassOfPnsn != 'FAMILYPNSN'){
			document.getElementById("txtNewRedsPnsnAmt").value = Number(document.getElementById("txtNewTotlPnsnAmt").value) - Number(document.getElementById("txtNewComPnsnAmt").value);
		}
		//	EFP and RR amount claculation
		
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = Number(lastEmolument)/2;
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
			
		}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = Number(lastEmolument)/2;
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
		}
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(efpAmount) > 39500)
				efpAmount = 39500;
			
			if(Number(efpAmount) < 2882 && Number(efpAmount) > 0)
				efpAmount = 2882;
		}else{
			if(Number(efpAmount) > 45000)
				efpAmount = 45000;
			
			if(Number(efpAmount) < 3500 && Number(efpAmount) > 0)
				efpAmount = 3500;
		}
		document.getElementById("txtNewFP1TotlAmt").value = Math.ceil(efpAmount);
		
		rrAmount = Number(lastEmolument) * 0.3;
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(rrAmount) > 39500)
				rrAmount = 39500;
			
			if(Number(rrAmount) < 2882 && Number(rrAmount) > 0)
				rrAmount = 2882;
		}else{
			if(Number(rrAmount) > 45000)
				rrAmount = 45000;
			
			if(Number(rrAmount) < 3500 && Number(rrAmount) > 0)
				rrAmount = 3500;
		}
		
		document.getElementById("txtNewFP2TotlAmt").value = Math.ceil(rrAmount);
		}else{
			resetAllAmnt();
		}
	}else if(PayComsn == '5THPAYCOMSN'){
		
		lastEmolument = Number(lastBasic)+Number(lastGradePay)+Number(lastNPA);
		
		if(Number(totalSms) > 20){
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				PensionAmount = (Number(avgEmolument)/2 * Number(totalSms) / 66);
			}else{
				PensionAmount = (Number(avgEmolument)/2 * Number(totalSms) / 66);
			}
			document.getElementById("txtNewTotPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtNewTotlPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension ReadOnly
			
			if(document.getElementById("radioDoWantCommuteYes").checked){
				
				document.getElementById("txtNewMonthAmt").value =Math.ceil(PensionAmount/3); //Monthly Commuted Pension amount
				document.getElementById("txtNewComPnsnAmt").value=Math.ceil(PensionAmount/3); // Commuted Pension amount(same as monthly commuted amount)
				
				var age = ageFromDOBAndDOR(DOB,DOR);
			
				if(Number(age) > 85)
				{
					age = 85;
				}
				getCVPRateFromAge(age,'5th Pay Commission');
				cvpRate = document.getElementById("hdnCvpRate").value;
				
				document.getElementById("txtNewCVPAmt").value=Math.ceil(Number(document.getElementById("txtNewComPnsnAmt").value)*12*Number(cvpRate));
			}
			
		}else if(Number(totalSms) <= 20){
			
			serviceGratiity = (Number(lastEmolument)/4) *6 ;
		}
		document.getElementById("txtNewServGratyAmt").value = Math.ceil(serviceGratiity);
		
		if(Number(totalSms) < 2)
		{
			DCRGamount = 2 * Number(lastEmolument);
		}
		else if(Number(totalSms) >= 2 && Number(totalSms) <10)
		{
			DCRGamount = 6 * Number(lastEmolument);
		}
		else if(Number(totalSms) >= 10 && Number(totalSms) <40)
		{
			DCRGamount = 12 * Number(lastEmolument);
		}
		else if(Number(totalSms)  >= 40)
		{
			DCRGamount = (Number(lastEmolument)/2) * Number(totalSms);
		}
		
		if(Number(DCRGamount)>350000)
			DCRGamount = 350000;
		
		if(ClassOfPnsn != 'FAMILYPNSN'){
			document.getElementById("txtNewRedsPnsnAmt").value = Number(document.getElementById("txtNewTotlPnsnAmt").value) - Number(document.getElementById("txtNewComPnsnAmt").value);
		}
		
		document.getElementById("txtNewTotDCRGAmt").value = DCRGamount;
		
		efpAmount = (Number(lastEmolument)/2 < Number(PensionAmount)) ? Number(lastEmolument)/2 : PensionAmount;
		document.getElementById("txtNewFP1TotlAmt").value = Math.ceil(efpAmount);
		
		rrAmount = Number(lastEmolument) * 0.3;
		document.getElementById("txtNewFP2TotlAmt").value = Math.ceil(rrAmount);
	}
	//Added by shraddha for 7th Pay comm
	if(PayComsn == '7THPAYCOMSN'){
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>')){
			if(Number(lastBasic)>67000)
				lastBasic = 67000
			if(Number(lastGradePay)>12000)
				lastGradePay = 12000
				
			lastEmolument = Number(lastBasic)+Number(lastGradePay)+Number(lastNPA);
		//Pension Calculation
		
		if(Number(totalSms) > 20){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				//No pension calculate
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					
					PensionAmount = (Number(lastEmolument)/2)*(Number(totalSms)/66);
					
				}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
				}
				
			}else{
				PensionAmount = ((Number(lastEmolument)/2) > (Number(avgEmolument)/2) ) ? (Number(lastEmolument)/2) : (Number(avgEmolument)/2);
			}
			if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS')
			{
				if(Number(PensionAmount) > 125000)
					PensionAmount = 125000;
				
				if(Number(PensionAmount) < 9000 && Number(PensionAmount) > 0)
					PensionAmount = 9000;
			}else{
				if(Number(PensionAmount) > 125000)
					PensionAmount = 125000;
				
				if(Number(PensionAmount) < 9000 && Number(PensionAmount) > 0)
					PensionAmount = 9000;
			}
		
			document.getElementById("txtNewTotPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtNewTotlPnsnAmt").value = Math.ceil(PensionAmount); // Total Pension ReadOnly
		
		}
			//Service Gratiity
		
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				
				if(Number(totalSms) < 2)
				{
					serviceGratiity = 2 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 2 && Number(totalSms) <10)
				{
					serviceGratiity = 6 * Number(lastEmolument);
					
				}
				else if(Number(totalSms) >= 10 && Number(totalSms) <40)
				{
					serviceGratiity = 12 * Number(lastEmolument);
					
				}
				else if(Number(totalSms)  >= 40)
				{
					serviceGratiity = (Number(lastEmolument)/2) * Number(totalSms);
				
				}
				
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
					
					
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					
					if(compareDatesWithoutAlert(DOE,DCRGMinDate,'<')){
						//edited by aditya
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}else if(compareDatesWithoutAlert(DOE,DCRGMinDate,'>')){
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
				}		
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						//edited by aditya
					if(Number(serviceGratiity)>2000000)
						serviceGratiity = 2000000;
				
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
					}
				}
			}else{
				
				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;
				
			
				
				
				if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
									|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
						
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						else{
						//edited by aditya
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
					
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						
						else{
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
						}
					}
				}else  if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
					if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'<') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioBn").checked))){
					
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						else{
						//edited by aditya
					if(Number(serviceGratiity)>2000000)
						serviceGratiity = 2000000;
						}
					}else if((compareDatesWithoutAlert(DOR,DCRGMaxDate,'>') 
							|| (compareDatesWithoutAlert(DOR,DCRGMaxDate,'=') && document.getElementById("radioAn").checked))){
			
						//edited by shraddha
						if(PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS'){
							if(Number(serviceGratiity)>2000000)
								serviceGratiity = 2000000;
						}
						else{
						if(Number(serviceGratiity)>2000000)
							serviceGratiity = 2000000;
						}
					}
				}
			}
			
			document.getElementById("txtServGratyAmt").value = Math.ceil(serviceGratiity);	
			
			
		//DCRG calculation
		
		var withheldAmt= document.getElementById("txtWitheldGratuity").value;
		//alert ("withheldAmt*****"+withheldAmt);
		
		DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
		
	
		
		document.getElementById("txtTotDCRGAmt").value = Math.ceil(DCRGamount);
		//CVP Calculation 
		
		
		if(document.getElementById("radioDoWantCommuteYes").checked && Number(totalSms) > 20){
			if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
				
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) / 3;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '5th Pay Commission';
			}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
				if(ClassOfPnsn == 'FAMILYPNSN'){
					
				}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}else{
					if(PnsnCatg != 'High Court'){
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}else{						
						commutedPension = Number(PensionAmount) * Number(commutPer)/ 100;
					}
				}
				payCom = '6th Pay Commission';
			}
			document.getElementById("txtNewComPnsnAmt").value = Math.ceil(commutedPension);
			document.getElementById("txtNewMonthAmt").value = Math.ceil(commutedPension);
			var age = ageFromDOBAndDOR(DOB,DOR);
		
			if(Number(age) > 81)
			{
				age = 81;
			}
			getCVPRateFromAge(age,payCom);
			cvpRate = document.getElementById("hdnCvpRate").value;
			
			document.getElementById("txtNewCVPAmt").value=Math.ceil(Number(commutedPension)*12*Number(cvpRate));
			
		}
		if(ClassOfPnsn != 'FAMILYPNSN'){
			document.getElementById("txtNewRedsPnsnAmt").value = Number(document.getElementById("txtNewTotlPnsnAmt").value) - Number(document.getElementById("txtNewComPnsnAmt").value);
		}
		//	EFP and RR amount claculation
		
		if(compareDatesWithoutAlert(DOR,FirstDate6thPC,'>') && compareDatesWithoutAlert(DOR,ActualDate6thPC,'<')){
			
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = Number(lastEmolument)/2;
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
			
		}else if(compareDatesWithoutAlert(DOR,ActualDate6thPC,'>')){
			if(ClassOfPnsn == 'FAMILYPNSN'){
				efpAmount = Number(lastEmolument)/2;
			}else if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65'){
				efpAmount = Number(lastEmolument)/2;
			}else{
				efpAmount = Number(lastEmolument)/2;
				//efpAmount = ((Number(lastEmolument)/2) < Number(avgEmolument)) ? (Number(lastEmolument)/2)*(Number(totalSms)/66) : Number(avgEmolument) *(Number(totalSms)/66);
			}
		}
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(efpAmount) > 39500)
				efpAmount = 39500;
			
			if(Number(efpAmount) < 2882 && Number(efpAmount) > 0)
				efpAmount = 2882;
		}else{
			if(Number(efpAmount) > 45000)
				efpAmount = 45000;
			
			if(Number(efpAmount) < 3500 && Number(efpAmount) > 0)
				efpAmount = 3500;
		}
		
		//Added by shraddha for EFP,FP limit
		
		if(efpAmount > 125000 && Number(efpAmount) > 0){
			efpAmount=125000;
		}
		if(efpAmount < 9000 && Number(efpAmount) > 0){
			efpAmount=9000;
		}
		document.getElementById("txtNewFP1TotlAmt").value = Math.ceil(efpAmount);
		
		rrAmount = Number(lastEmolument) * 0.3;
		if(PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS'){
			if(Number(rrAmount) > 39500)
				rrAmount = 39500;
			
			if(Number(rrAmount) < 2882 && Number(rrAmount) > 0)
				rrAmount = 2882;
		}else{
			if(Number(rrAmount) > 45000)
				rrAmount = 45000;
			
			if(Number(rrAmount) < 3500 && Number(rrAmount) > 0)
				rrAmount = 3500;
		}
		if(rrAmount > 75000 && Number(rrAmount) > 0){
			rrAmount= 75000;
		}
		if(rrAmount < 9000 && Number(rrAmount) > 0){
			rrAmount=9000;
		}
		document.getElementById("txtNewFP2TotlAmt").value = Math.ceil(rrAmount);
		}else{
			resetAllAmnt();
		}
	}
	
	
	setNewCVPMonthlyAmount();
	setNewCVPAmount();
	setNewTotalPensionAmount();
	setNewReducePensionAmount();
	setNewServGratuityAmount();
	setNewDCRGAmount();
	setNewFP1Amount();
	setNewFP2Amount();
}

function smsCalculation(){
	
	//var smsYear = Math.abs(Number(document.getElementById("hidTotalyear").value)-Number(document.getElementById("hidTotalBrkyear").value));
	//var smsMonth = Math.abs(Number(document.getElementById("hidTotalMonth").value)-Number(document.getElementById("hidTotalBrkMonth").value));
	//var smsDays = Math.abs(Number(document.getElementById("hidTotalDays").value)-Number(document.getElementById("hidTotalBrkDays").value));
	
	var smsYear = document.getElementById("txtQualifyingServYear").value;
	var smsMonth = document.getElementById("txtQualifyingServMonth").value;
	var smsDays = document.getElementById("txtQualifyingServDay").value;
	
	//alert(smsYear);
	var noOfSms;
	var ClassOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	if(ClassOfPnsn == 'FAMILYPNSN' || ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65')
	{
		if(Number(smsYear) >= 20)
		{
		//	smsYear = Number(smsYear) + 5;
			//added by ankita 14-10-2015
			smsYear = Number(smsYear) ;
		}
		if(Number(smsYear) >= 33)
		{
			noOfSms = 66;
		}
	}
	
	if(Number(smsYear) > 0)
	{
		noOfSms = 2 * Number(smsYear);
		if(Number(noOfSms) > 66)
		{
			noOfSms = 66;
		}
		else
		{
			if(Number(noOfSms) == 66)
			{
				noOfSms = noOfSms;
			}
			else //no. of SMS are less than 66
			{
				if(Number(smsMonth) >=3 && Number(smsMonth) < 9)
				{
					noOfSms = noOfSms + 1;
				}
				else if (Number(smsMonth) >=9)
				{
					noOfSms = noOfSms + 2;
				}
				else // smsMonth is less than 3
				{
					noOfSms = noOfSms;
				}
			}
		}
	}
	
	return noOfSms;
}
function setNewCVPMonthlyAmount(){
	
	var oldCvpMonthtyAmt = document.getElementById("txtMonthAmt").value.trim();
	var newCvpMonthtyAmt = document.getElementById("txtNewMonthAmt").value.trim();
	
	if(oldCvpMonthtyAmt != '' && newCvpMonthtyAmt != ''){		
		document.getElementById("txtDiffMonthAmt").value = Number(newCvpMonthtyAmt) - Number(oldCvpMonthtyAmt);		
	}
	document.getElementById("txtNewComPnsnAmt").value = newCvpMonthtyAmt;
}
function setNewCVPAmount(){
	
	var oldCvpAmt = document.getElementById("txtCVPAmt").value.trim();
	var newCvpAmt = document.getElementById("txtNewCVPAmt").value.trim();
	
	if(oldCvpAmt != '' && newCvpAmt != ''){		
		document.getElementById("txtDiffCVPAmt").value = Number(newCvpAmt) - Number(oldCvpAmt);		
	}
}
function setNewTotalPensionAmount(){
	
	var oldPensionAmt = document.getElementById("txtTotPnsnAmt").value.trim();
	var newPensionAmt = document.getElementById("txtNewTotPnsnAmt").value.trim();
	
	if(oldPensionAmt != '' && newPensionAmt != ''){		
		document.getElementById("txtDiffTotPnsnAmt").value = Number(newPensionAmt) - Number(oldPensionAmt);		
	}
	document.getElementById("txtNewTotlPnsnAmt").value = newPensionAmt;
}

function setNewReducePensionAmount(){
	
	var newCvpAmt = document.getElementById("txtNewComPnsnAmt").value.trim();
	var newPensionAmt = document.getElementById("txtNewTotPnsnAmt").value.trim();
	
	if(newCvpAmt != '' && newPensionAmt != ''){		
		document.getElementById("txtNewRedsPnsnAmt").value = Number(newPensionAmt) - Number(newCvpAmt);		
	}
	
	var oldRedsAmt = document.getElementById("txtRedsPnsnAmt").value.trim();
	var newRedsAmt = document.getElementById("txtNewRedsPnsnAmt").value.trim();
	
	if(oldRedsAmt != '' && newRedsAmt != ''){		
		document.getElementById("txtDiffRedsPnsnAmt").value = Number(newRedsAmt) - Number(oldRedsAmt);		
	}
	
	var oldPensionAmt = document.getElementById("txtTotlPnsnAmt").value.trim();
	var newPensionAmt = document.getElementById("txtNewTotPnsnAmt").value.trim();
	
	if(oldPensionAmt != '' && newPensionAmt != ''){		
		document.getElementById("txtDiffTotlPnsnAmt").value = Number(newPensionAmt) - Number(oldPensionAmt);		
	}
}
function setNewServGratuityAmount(){
	
	var oldServGratuityAmt = document.getElementById("txtServGratyAmt").value.trim();
	var newServGratuityAmt = document.getElementById("txtNewServGratyAmt").value.trim();
	
	if(oldServGratuityAmt != '' && newServGratuityAmt != ''){		
		document.getElementById("txtDiffServGratyAmt").value = Number(newServGratuityAmt) - Number(oldServGratuityAmt);		
	}
}
function setNewDCRGAmount(){
	
	var oldDCRGAmt = document.getElementById("txtTotDCRGAmt").value.trim();
	var newDCRGAmt = document.getElementById("txtNewTotDCRGAmt").value.trim();
	
	if(oldDCRGAmt != '' && newDCRGAmt != ''){		
		document.getElementById("txtDiffTotDCRGAmt").value = Number(newDCRGAmt) - Number(oldDCRGAmt);		
	}
}
function setNewFP1Amount(){
	
	var oldFP1Amt = document.getElementById("txtFP1TotlAmt").value.trim();
	var newFP1Amt = document.getElementById("txtNewFP1TotlAmt").value.trim();
	
	if(oldFP1Amt != '' && newFP1Amt != ''){		
		document.getElementById("txtDiffFP1TotlAmt").value = Number(newFP1Amt) - Number(oldFP1Amt);		
	}
}
function setNewFP2Amount(){
	
	var oldFP2Amt = document.getElementById("txtFP2TotlAmt").value.trim();
	var newFP2Amt = document.getElementById("txtNewFP2TotlAmt").value.trim();
	
	if(oldFP2Amt != '' && newFP2Amt != ''){		
		document.getElementById("txtDiffFP2TotlAmt").value = Number(newFP2Amt) - Number(oldFP2Amt);		
	}
}
function validateGratuity(){
	
	var ServGratyAmt = document.getElementById("txtServGratyAmt").value.trim();
	var WitheldGratuity = document.getElementById("txtWitheldGratuity").value.trim();
	
	if(WitheldGratuity != "" && ServGratyAmt != ""){
		if(Number(WitheldGratuity) > Number(ServGratyAmt)){
			alert("Witheld Gratuity Amount is not greater than Service Gratuity Amount");
			document.getElementById("txtWitheldGratuity").value = "";
			document.getElementById("txtWitheldGratuity").focus();
		}
	}
}

function init_for_gratuity()
{	
	//alert("in");
	var gratuity_flag=document.getElementById("gratuity_type").value;
//alert("gratuity_flag"+gratuity_flag);
	if(gratuity_flag=="death")
	{
		document.getElementById("deathgratuity").checked=true;
	}
	
else if(gratuity_flag=="ret")
{
	document.getElementById("retiregratuity").checked=true;
	
}
else if(gratuity_flag=="serv")
{
	document.getElementById("servicegratuity").checked=true;
	
}
	divexpand();
	
}
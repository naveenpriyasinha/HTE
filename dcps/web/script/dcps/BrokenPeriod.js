function SearchEmployee(){
	
	
	//showProgressbar("Please Wait...");
	
	var searchName = document.getElementById("txtSearchName").value;
	var yearId = document.getElementById("cmbYear").value;
	var monthId = document.getElementById("cmbMonth").value;
	if(searchName == "")
	{
		alert('Please enter name in search option');
		return ;
	}
	if(yearId == -1)
	{
		alert('Please enter pay year');
		return ;
	}
	if(monthId == -1)
	{
		alert('Please enter pay month');
		return ;
	}
	document.getElementById("btnSubmitData").disabled=true;
	self.location.href="ifms.htm?actionFlag=searchForBrokenPay&txtSearchName="+searchName+"&yearId="+yearId+"&monthId="+monthId;
}

function checkForOtherReason(counter)
{
	var reasonForBrokenPeriod = document.getElementById('cmbReasonForBrokenPeriod'+counter).options[document.getElementById('cmbReasonForBrokenPeriod'+counter).selectedIndex].text;
	if(reasonForBrokenPeriod.trim() == 'Others')
	{
		document.getElementById("labelForRemarksForOtherReason"+counter).style.display = 'inline' ;
	}
	else
	{
		document.getElementById("labelForRemarksForOtherReason"+counter).style.display = 'none' ;
	}
}


function AddNewRowBrokenPrd()
{
	var table=document.getElementById("vo");
	var nextRow= Number (document.getElementById("hidTotalRows").value) + 1 ;
	
	var newRow = table.insertRow(-1);
	color2 = "rgb(255, 218, 178)";
	newRow.style.borderColor = color2;
	
	var Cell1 = newRow.insertCell(-1);
	Cell1.style.border ="1px solid rgb(255, 218, 178)";
	Cell1.innerHTML = '<input type="text" size="10" name="txtFromDate" id="txtFromDate'+nextRow+'" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 	onBlur="validateDate(this);checkDateForThisMonth(this,'+nextRow+');setNoOfDays('+nextRow+')" value="" />' 
			+ '&nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick="window_open(\'txtFromDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>' ;
	//Cell1.innerHTML = Cell1.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForFromDate'+ nextRow +'" >*</label>' ;
	
	var Cell2 = newRow.insertCell(-1);
	Cell2.style.border ="1px solid rgb(255, 218, 178)";
	Cell2.innerHTML = '<input type="text" size="10" name="txtToDate" id="txtToDate'+nextRow+'" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 	onBlur="validateDate(this);checkDateForThisMonth(this,'+nextRow+');checkSameMonth(this,'+nextRow+');setNoOfDays('+nextRow+');loadSalaryFromRuleEngine('+nextRow+')" value="" />' 
	+ '&nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick="window_open(\'txtToDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>' ;
	//Cell2.innerHTML = Cell2.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForToDate'+ nextRow +'" >*</label>' ;
	
	var Cell3 = newRow.insertCell(-1);
	Cell3.style.border ="1px solid rgb(255, 218, 178)";
	Cell3.innerHTML = '<input type="text" size="8" name="txtNoOfDays" id="txtNoOfDays'+nextRow+'"  value="" readonly="readonly" />';
	//Cell3.innerHTML = Cell3.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForNoOfDays'+ nextRow +'" >*</label>';
	
	var Cell4 = newRow.insertCell(-1);
	Cell4.style.border ="1px solid rgb(255, 218, 178)";
	Cell4.innerHTML = '<input type="text" size="8" name="txtBasicPay" id="txtBasicPay'+nextRow+'" onkeypress="digitFormat(this);" value="" onblur="calcGrossAmount('+nextRow+');calcNetPay('+nextRow+');" />';
	//Cell4.innerHTML = Cell4.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForBasicPay'+ nextRow +'" >*</label>';
	
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	
	var ArrayAllowancesFromJSP = document.getElementsByName("hidAllowanceCode");
	var ArrayAllowancesNamesFromJSP = document.getElementsByName("hidAllowanceName");
	var nextlInt=0 ;
	
	for(var lInt=0;lInt < totalAllowances;lInt++ )
	{
		nextlInt = nextlInt + 1;
		var CellForAllowance = newRow.insertCell(-1);

		if( Number(ArrayAllowancesFromJSP[lInt].value)==Number('77')  || Number(ArrayAllowancesFromJSP[lInt].value)==Number('78') || Number(ArrayAllowancesFromJSP[lInt].value)==Number('75') || Number(ArrayAllowancesFromJSP[lInt].value)==Number('76') || Number(ArrayAllowancesFromJSP[lInt].value)==Number('36')||Number(ArrayAllowancesFromJSP[lInt].value)==Number('72')){
		CellForAllowance.style.border ="1px solid rgb(255, 218, 178)";
		CellForAllowance.innerHTML = '<input type="text" onkeypress="digitFormat(this);" id="txtAllowance'+ nextlInt + "" + nextRow+'" size="5" onblur="checkServcExpDate('+nextRow+',this);calcGrossAmount('+nextRow+');calcNetPay('+nextRow+');" />' +
									 '<input type="hidden" name="hidAllowanceCode" id="hidAllowanceCode'+ nextlInt + nextRow+'" value="'+ArrayAllowancesFromJSP[lInt].value +'"/>' + 
									 '<input type="hidden" name="hidAllowanceName" id="hidAllowanceName'+ nextlInt + nextRow+'" value="'+ArrayAllowancesNamesFromJSP[lInt].value+'" />' ;
		}
		else
		{
			CellForAllowance.style.border ="1px solid rgb(255, 218, 178)";
			CellForAllowance.innerHTML = '<input type="text" onkeypress="digitFormat(this);" id="txtAllowance'+ nextlInt + "" + nextRow+'" size="5" onblur="calcGrossAmount('+nextRow+');calcNetPay('+nextRow+');" />' +
										 '<input type="hidden" name="hidAllowanceCode" id="hidAllowanceCode'+ nextlInt + nextRow+'" value="'+ArrayAllowancesFromJSP[lInt].value +'"/>' + 
										 '<input type="hidden" name="hidAllowanceName" id="hidAllowanceName'+ nextlInt + nextRow+'" value="'+ArrayAllowancesNamesFromJSP[lInt].value+'" />' ;
			
		}
		//	CellForAllowance.innerHTML = CellForAllowance.innerHTML  + '&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator" id="labelForAllowance'+ nextlInt + nextRow+'" >*</label>';
		
	}
	
	var Cell5 = newRow.insertCell(-1); 
	Cell5.style.border ="1px solid rgb(255, 218, 178)";
	Cell5.innerHTML = '<input type="text" size="8" name="txtGrossAmt" onkeypress="digitFormat(this);" id="txtGrossAmt'+nextRow+'" value="" readonly="readonly" />' ;
	//Cell5.innerHTML =  Cell5.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForGrossAmt'+ nextRow +'" >*</label>';
	
	var ArrayDeductionFromJSP = document.getElementsByName("hidDeductionCode");
	var ArrayDeductionNamesFromJSP =  document.getElementsByName("hidDeductionName");
	nextlInt=0 ;
	
	for(lInt=0;lInt < totalDeductions;lInt++ )
	{
		nextlInt = nextlInt + 1;
		var CellForDeduction = newRow.insertCell(-1);
		CellForDeduction.style.border ="1px solid rgb(255, 218, 178)";
		CellForDeduction.innerHTML = '<input type="text"  onkeypress="digitFormat(this);" id="txtDeduction'+ nextlInt + "" + nextRow+'" size="5" onblur="calcTotalDeduction('+nextRow+');calcNetPay('+nextRow+');" />' +
									 '<input type="hidden" name="hidDeductionCode" id="hidDeductionCode'+ nextlInt + nextRow+'" value="'+ArrayDeductionFromJSP[lInt].value +'"/>' +
									 '<input type="hidden" name="hidDeductionName" id="hidDeductionName'+ nextlInt + nextRow+'" value="'+ArrayDeductionNamesFromJSP[lInt].value+'" />' ;
		//CellForDeduction.innerHTML = CellForDeduction.innerHTML + '&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator" id="labelForDeduction'+ nextlInt + nextRow+'" >*</label>';
		
	}
	var Cell6 = newRow.insertCell(-1); 
	Cell6.style.border ="1px solid rgb(255, 218, 178)";
	Cell6.innerHTML = '<input type="text" size="8" name="txtTotalDeduction" id="txtTotalDeduction'+nextRow+'" onkeypress="digitFormat(this);" value="" readonly="readonly" />' ;
	//Cell6.innerHTML =  Cell6.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForTotalDeduction'+ nextRow +'" >*</label>';
	
	var Cell7 = newRow.insertCell(-1); 
	Cell7.style.border ="1px solid rgb(255, 218, 178)";
	Cell7.innerHTML = '<input type="text" size="8" name="txtNetPay" id="txtNetPay'+nextRow+'" onkeypress="digitFormat(this);" value="" readonly="readonly" />' ;
	//Cell7.innerHTML =  Cell7.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForNetPay'+ nextRow +'" >*</label>';
	
	var Cell8 = newRow.insertCell(-1); 
	Cell8.style.border ="1px solid rgb(255, 218, 178)";
	Cell8.width="190px";
	Cell8.innerHTML = '<select name="cmbReasonForBrokenPeriod" id="cmbReasonForBrokenPeriod'+nextRow+'" style="width: 90%;" onChange="checkForOtherReason('+nextRow+');" >'
				 	+ '<option value="-1">--Select--</option>' + LISTREASONSFORBROKENPERIOD +'</select>';
	//Cell8.innerHTML = Cell8.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForReason'+nextRow+'" >*</label>';
	
	var Cell9 = newRow.insertCell(-1); 
	Cell9.style.border ="1px solid rgb(255, 218, 178)";
	Cell9.innerHTML = '<textarea name="txtRemarks" id="txtRemarks'+nextRow+'" rows="2" cols="20" ></textarea>';
	//Cell9.innerHTML = Cell9.innerHTML  + '&nbsp;<label class="mandatoryindicator" id="labelForRemarksForOtherReason'+nextRow+'" style="display:none" >*</label>';
	
	var Cell10 = newRow.insertCell(-1);
	Cell10.style.border ="1px solid rgb(255, 218, 178)";
	Cell10.innerHTML = '<a href=# onclick="DeleteRowBrokenPrd('+nextRow+');"> <label id="DeleteRowBrokenPrd">Delete</label></a>';
	
	document.getElementById("hidTotalRows").value = Number (document.getElementById("hidTotalRows").value) + 1 ;
}
function DeleteRowBrokenPrd(counter)
{
	if(counter == 1)
	{
		alert('You cannot delete the first row.');
		return ;
	}
	var answer = confirm('Are you sure you want to delete the row?');
	if (answer)
	{
	var current = window.event.srcElement;
    while ( (current = current.parentElement)  && current.tagName !="TR");
         current.parentElement.removeChild(current);
    
    document.getElementById("hidTotalRows").value = Number(document.getElementById("hidTotalRows").value) - 1 ;
	}
}

function saveBrokenPrdData()
{
	if(!validateSaveForBrokenPeriodPay())
	{
		return false;
	}
	
	var totalRows = document.getElementById("hidTotalRows").value;
	var year = document.getElementById("cmbYear").value;
	var month = document.getElementById("cmbMonth").value;
	var eisEmpId = document.getElementById("txtEmployeeId").value;
	var fromDates = "";
	var toDates = "";
	var noOfDays = "";
	var basicPays = "";
	var netPays = "";
	var reasons = "";
	var remarks = "";
	
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	var allowancesCodes = "";
	var allowancesValues = "";
	var deductionCodes = "";
	var deductionValues = "";
	
	for(var counter=1;counter<=totalRows;counter++)
	{
		fromDates = fromDates + document.getElementById("txtFromDate"+counter).value + "~";
		toDates = toDates + document.getElementById("txtToDate"+counter).value + "~";
		noOfDays = noOfDays + document.getElementById("txtNoOfDays"+counter).value + "~";
		basicPays = basicPays + document.getElementById("txtBasicPay"+counter).value + "~";
		netPays = netPays + document.getElementById("txtNetPay"+counter).value + "~";
		reasons = reasons + document.getElementById("cmbReasonForBrokenPeriod"+counter).value + "~";
		remarks = remarks + document.getElementById("txtRemarks"+counter).value + "~";
		
		//getting allowances for the employee
		
		for(var lInt=1;lInt<=totalAllowances;lInt++)
		{
			if(lInt==totalAllowances)
			{
				allowancesCodes = allowancesCodes + document.getElementById("hidAllowanceCode"+lInt+counter).value + ":" + "~";
				allowancesValues = allowancesValues + document.getElementById("txtAllowance"+lInt+counter).value + ":" + "~";
			}
			else
			{
				allowancesCodes = allowancesCodes + document.getElementById("hidAllowanceCode"+lInt+counter).value + ":";
				allowancesValues = allowancesValues + document.getElementById("txtAllowance"+lInt+counter).value + ":";
			}
		}
		
		//getting deductions for the employee
		
		for(lInt=1;lInt<=totalDeductions;lInt++)
		{
			if(lInt==totalDeductions)
			{
				deductionCodes = deductionCodes + document.getElementById("hidDeductionCode"+lInt+counter).value + ":" + "~";
				deductionValues = deductionValues + document.getElementById("txtDeduction"+lInt+counter).value + ":" + "~";
			}
			else
			{
				deductionCodes = deductionCodes + document.getElementById("hidDeductionCode"+lInt+counter).value + ":";
				deductionValues = deductionValues + document.getElementById("txtDeduction"+lInt+counter).value + ":";
			}
		}
	}
	
	var uri = 'ifms.htm?actionFlag=saveBrokenPeriodPay';
	var url = 'year='+year+'&month='+ month +'&eisEmpId=' + eisEmpId 
		+ '&fromDates='+fromDates +'&toDates='+toDates+'&noOfDays='+noOfDays+'&basicPays='+basicPays
		+ '&netPays='+netPays + '&reasons=' + reasons + '&remarks=' + remarks 
		+ '&allowancesCodes=' + allowancesCodes + '&allowancesValues=' + allowancesValues 
		+ '&deductionCodes=' + deductionCodes + '&deductionValues=' + deductionValues ;
	
	//alert(url);
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForSaveBrokenPrds(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForSaveBrokenPrds(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(lBlFlag=='true')
	{
		alert('Broken Period Pays are saved for the employee.');
		var txtSearchName = document.getElementById("txtSearchName").value;
		var yearId = document.getElementById("cmbYear").value;
		var monthId = document.getElementById("cmbMonth").value;
		self.location.href = "ifms.htm?actionFlag=searchForBrokenPay&txtSearchName="+txtSearchName+"&yearId="+yearId+"&monthId="+monthId;
	}
}

function checkDateForThisMonth(dateField,counter)
{
	//alert('Inside checkDateForThisMonth');
	var retFlag=checkFutureDate(dateField,counter);
	
	//return retFlag;
	var dateValue = dateField.value.trim() ;
	if(dateValue == "")
	{
		return;
	}
	var year = document.getElementById('cmbYear').options[document.getElementById('cmbYear').selectedIndex].text.trim().substring(0,4);
	var nextYear = Number(year) + 1;
	var month = Number(document.getElementById("cmbMonth").value.trim());
	//alert('month'+month);
	
	var yearFromDate =  dateValue.substring(6,10);
	var monthFromDate = Number(dateValue.substring(3,5));
	//alert('monthFromDate '+monthFromDate);
	/*if(! ((yearFromDate == year) || (yearFromDate == nextYear)) )
	{
		alert('Year should be from selected year only');
		dateField.value =  "";
		document.getElementById("txtNoOfDays"+counter).value = "";
		return false;
	}*/
	/*if(month != monthFromDate)
	{
		alert('Month should be from selected month only');
		dateField.value =  "";
		document.getElementById("txtNoOfDays"+counter).value = "";
		return false;
	}*/
	
	/*if(monthFromDate == 1 || monthFromDate == 2 || monthFromDate==3)
	{
		//alert('Month is 1 || 2 || 3');
		if(yearFromDate == year)
		{
			alert('Date should be from this financial year only');
			dateField.value =  "";
			document.getElementById("txtNoOfDays"+counter).value = "";
			return false;
		}
	}
	else
	{
		if(yearFromDate == nextYear)
		{ 
			alert('Date should be from this financial year only');
			dateField.value =  "";
			document.getElementById("txtNoOfDays"+counter).value = "";
			return false;
		}
	}*/
	
	// Below code checks for overlapping period
	var one_day = 1000*60*60*24;
	str3 = document.getElementById("txtFromDate"+counter).value.trim(); 
	str4 = document.getElementById("txtToDate"+counter).value.trim(); 
	
	if(str3 != "" && str4 != "")
	{
		var dt3  = parseInt(str3.substring(0,2),10); 
	    var mon3 = parseInt(str3.substring(3,5),10); 
	    var yr3  = parseInt(str3.substring(6,10),10); 
	    var dt4  = parseInt(str4.substring(0,2),10); 
	    var mon4 = parseInt(str4.substring(3,5),10); 
	    var yr4  = parseInt(str4.substring(6,10),10); 
	    
	    var date3 = new Date(yr3, mon3-1, dt3);
	    var date4 = new Date(yr4, mon4-1, dt4);
		
		var totalRows = document.getElementById("hidTotalRows").value;
		
		for(var i=1;i<=totalRows;i++){
			
		    var str1 = document.getElementById("txtFromDate"+i).value; 
		    var str2 = document.getElementById("txtToDate"+i).value; 
	
		    var dt1  = parseInt(str1.substring(0,2),10); 
		    var mon1 = parseInt(str1.substring(3,5),10); 
		    var yr1  = parseInt(str1.substring(6,10),10); 
		    var dt2  = parseInt(str2.substring(0,2),10); 
		    var mon2 = parseInt(str2.substring(3,5),10); 
		    var yr2  = parseInt(str2.substring(6,10),10); 
	
		    var date1 = new Date(yr1, mon1-1, dt1); 
		    var date2 = new Date(yr2, mon2-1, dt2);
	
		    var Diff1 = Math.floor((date3.getTime() - date1.getTime())/(one_day));
		    var Diff2 = Math.floor((date2.getTime() - date3.getTime())/(one_day));
		    var Diff3 = Math.floor((date4.getTime() - date1.getTime())/(one_day));
		    var Diff4 = Math.floor((date2.getTime() - date4.getTime())/(one_day));
	
			if(i!=counter && ((Diff1>=0 && Diff2>=0) || (Diff3>=0 && Diff4>=0))){
				
				alert('The broken period pay dates overlaps in the same month.');
				document.getElementById("txtFromDate"+counter).value="";
				document.getElementById("txtToDate"+counter).value="";
				return false;
			}
		}
	}
	
	return true;
}

function setNoOfDays(counter)
{
	//alert("hiii");
	var fromDate = document.getElementById("txtFromDate"+counter).value;
	var toDate = document.getElementById("txtToDate"+counter).value;
	if(fromDate != "" && toDate != "")
	{
		var fromDay = fromDate.substring(0,2);
		var toDay = toDate.substring(0,2);
		var daysDiff = Number(toDay) - Number(fromDay) + 1 ;
		//alert(daysDiff);
		if(Number(daysDiff)>0){
		document.getElementById("txtNoOfDays"+counter).value = daysDiff ;
		return true;
		}
		else{
			alert("To date can not be less than from date.");
			document.getElementById("txtToDate"+counter).value='';
			return false;
		}
	}
	return true;
}

function validateSaveForBrokenPeriodPay()
{
	var totalRows = document.getElementById("hidTotalRows").value;
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	
	for(var k=1;k<=totalRows;k++)
	{
		if(!chkEmpty(document.getElementById("txtFromDate"+k),'From Date') || 
				!chkEmpty(document.getElementById("txtToDate"+k),'To Date') ||
				!chkEmpty(document.getElementById("txtBasicPay"+k),'Basic Pay') ||
				!chkEmpty(document.getElementById("txtNetPay"+k),'Net Pay') ||
				!chkEmpty(document.getElementById("cmbReasonForBrokenPeriod"+k),'Reason'))
		{
			return false;
		}
		
		if(document.getElementById("cmbReasonForBrokenPeriod"+k).value == '700336' && document.getElementById("txtRemarks"+k).value == "")
		{
			alert('Remarks must be entered for other reason');
			return false;
		}
		//Checks for allowances not empty
		for(var lInt=1;lInt<=totalAllowances;lInt++)
		{
				if(document.getElementById("txtAllowance"+lInt+k).value == "")
				{
					var allowanceName =  document.getElementById("hidAllowanceName"+lInt+k).value ;
					alert('Please enter value for '+ allowanceName);
					return false;
				}
		}
		//Checks for deductions not empty
		for(lInt=1;lInt<=totalDeductions;lInt++)
		{
				if(document.getElementById("txtDeduction"+lInt+k).value == "")
				{
					var deductionName =  document.getElementById("hidDeductionName"+lInt+k).value ;
					alert('Please enter value for '+ deductionName);
					return false;
				}
		}
		
	}
	return true;
}


function calcNetPay(counter)
{
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	var basicPay = document.getElementById("txtBasicPay"+counter).value;
	var totalValueOfAllowances = 0;
	var totalValueOfDeductions = 0;
	//alert(totalAllowances);
	for(var lInt=1;lInt<=totalAllowances;lInt++)
	{
			if(document.getElementById("txtAllowance"+lInt+counter).value != "")
			{
				totalValueOfAllowances = Number(totalValueOfAllowances) + Number(document.getElementById("txtAllowance"+lInt+counter).value.trim());
			}
	}
	
	for(lInt=1;lInt<=totalDeductions;lInt++)
	{
			if(document.getElementById("txtDeduction"+lInt+counter).value != "")
			{
				totalValueOfDeductions = Number(totalValueOfDeductions) + Number(document.getElementById("txtDeduction"+lInt+counter).value.trim());
			}
	}
	var netPay = Number(basicPay) + Number(totalValueOfAllowances) - Number(totalValueOfDeductions);
	document.getElementById("txtNetPay"+counter).value = netPay;
}

function calcGrossAmount(counter)
{
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var basicPay = document.getElementById("txtBasicPay"+counter).value;
	var totalValueOfAllowances = 0;
	
	
	for(var lInt=1;lInt<=totalAllowances;lInt++)
	{
			if(document.getElementById("txtAllowance"+lInt+counter).value != "")
			{
				totalValueOfAllowances = Number(totalValueOfAllowances) + Number(document.getElementById("txtAllowance"+lInt+counter).value.trim());
			}
	}
	var GrossAmount = Number(basicPay) + Number(totalValueOfAllowances);
	document.getElementById("txtGrossAmt"+counter).value = GrossAmount;
}

function calcTotalDeduction(counter)
{
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	var totalValueOfDeductions = 0;
	for(lInt=1;lInt<=totalDeductions;lInt++)
	{
			if(document.getElementById("txtDeduction"+lInt+counter).value != "")
			{
				totalValueOfDeductions = Number(totalValueOfDeductions) + Number(document.getElementById("txtDeduction"+lInt+counter).value.trim());
			}
	}
	var TotalDeduction = Number(totalValueOfDeductions);
	document.getElementById("txtTotalDeduction"+counter).value =TotalDeduction;
}

function checkSameMonth(toDateObj,counter){

	var fromDate=document.getElementById("txtFromDate"+counter).value;
	var toDate=toDateObj.value;
	
	var fromdateArray=fromDate.split("/");
	
	var todateArray=toDate.split("/");
	
	if(fromdateArray[1]!=todateArray[1]){
		alert("Please Select same month in a row");
		toDateObj.value="";
		return false;
	}
		
	if(fromdateArray[2]!=todateArray[2]){
		alert("Please Select same year in a row");
		toDateObj.value="";
		return false;
	}
return true;
	
}
function checkFutureDate(dateField,counter){
	var fromDate=dateField.value.trim() ;
	var selYear = document.getElementById('cmbYear').options[document.getElementById('cmbYear').selectedIndex].text.trim();
	var month = Number(document.getElementById("cmbMonth").value.trim());
	var fromdateArray=fromDate.split("/");
	var selYearArray=selYear.split("-");
	var year="";
	if(month<4)
		year=selYearArray[1];
	if(month>3)
		year=selYearArray[0];
	
	
	if(Number(fromdateArray[2])>Number(year)){
		alert("Please Select Year Lesser than the Selected Pay Year");
		dateField.value="";
		dateField.focus();
		return false;
	}
	if(Number(fromdateArray[2])==Number(year)){
		if(fromdateArray[1]>month)
		{
			alert("Please Select Month Lesser than the Selected Pay Month");
			dateField.value="";
			dateField.focus();
			return false;
		}
	}
	return true;
	
}

function checkServcExpDate(counter,obj){
	var fromDate=document.getElementById("txtFromDate"+counter).value;
	var fromdateArray=fromDate.split("/");
	var allowMonthGpf='${allowMonthGpf}';
	var allowyearGpf='${allowyearGpf}';
	if(Number(allowyearGpf)==Number(fromdateArray[2])){
		if(Number(fromdateArray[1])<=Number(allowMonthGpf))
    		return true;
    	else
    	{
    		obj.value="";
    		//alert("asdgfsdg");
    		return false;
    	}
    }
    else if(Number(fromdateArray[2])<allowyearGpf){
    	return true;
    }
    else
    	{
    	obj.value="";
    	//alert("asdgfsdg");
    	return false;
    	}
	
	
	
}




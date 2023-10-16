var Row_ID_Advnc=0;
var Row_ID_Instlmnt=0;
var Row_Id_Rec=0
function advanceDtlsTableAddRow()
{	
	Row_ID_Advnc = document.getElementById("hidAdvanceGridSize").value;
		
	var table=document.getElementById("tblAdvanceDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	
	//var newRow =  document.all("tblAdvanceDtls").insertRow();	  		
	   	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell(4);
	Cell5.className = "tds";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell(5);
	Cell6.className = "tds";
   	Cell6.align="center";
	var Cell7 = newRow.insertCell(6);
	Cell7.className = "tds";
   	Cell7.align="center";
     	
   	Cell1.innerHTML = '<input type="hidden" name="hdnAdvRecoveryDtlsId" id="hdnAdvRecoveryDtlsId'+Row_ID_Advnc+'"/><select name="cmbTypeOfAdvance" id="cmbTypeOfAdvance'+Row_ID_Advnc+'"  style="width: 50%"> <option value="-1">--SELECT--</option>'+ LISTADVANCE +'</select>';
   	Cell2.innerHTML = '<input type="text" name="txtAdvanceAmount" id="txtAdvanceAmount'+Row_ID_Advnc+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);calAdvanceAmtTotal(this,\'txtAdvanceAmount\')" onkeypress="amountFormat(this);"  style="text-align: right"/>';
	Cell3.innerHTML = '<input type="text" name="txtNoOfInstallment" id="txtNoOfInstallment'+Row_ID_Advnc+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);" onKeyPress="numberFormat(this)"/>';
	Cell4.innerHTML = '<select name="cmbAdvanceStartMonth" id="cmbAdvanceStartMonth'+Number(Row_ID_Advnc)+'" ><option value="-1">--Select--</option>' +LISTMONTHS +'</select>'
		+'&nbsp;<select name="cmbAdvanceStartYear" id="cmbAdvanceStartYear'+Number(Row_ID_Advnc)+'" ><option value="-1">--Select--</option>'+ LISTYEARS +'</select>';
	Cell5.innerHTML = '<select name="cmbAdvanceEndMonth" id="cmbAdvanceEndMonth'+Number(Row_ID_Advnc)+'" ><option value="-1">--Select--</option>' +LISTMONTHS +'</select>'
	+'&nbsp;<select name="cmbAdvanceEndYear" id="cmbAdvanceEndYear'+Number(Row_ID_Advnc)+'" ><option value="-1">--Select--</option>'+ LISTYEARS +'</select>';
	Cell6.innerHTML = '<input type="text" name="txtAdvSchemeCode" id="txtAdvSchemeCode'+Number(Row_ID_Advnc)+'" size="12" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" /><a href="#" id="txtAdvSchemeCode'+Number(Row_ID_Advnc)+'" onclick="showSchemeCodePopup(this);"><img src="images/search.gif" /></a>';
	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblAdvanceDtls\')" /> ';
	document.getElementById("hidAdvanceGridSize").value = Number(Row_ID_Advnc)+1;  	
}

function assdDuesDtlsTableAddRow()
{	
	Row_ID_Instlmnt = document.getElementById("hidInstlGridSize").value;
	
	var table=document.getElementById("tblAssessedDuesDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
//	var newRow =  document.all("tblAssessedDuesDtls").insertRow();		  		
	  	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell(4);
	Cell5.className = "tds";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell(5);
	Cell6.className = "tds";
   	Cell6.align="center";
	var Cell7 = newRow.insertCell(6);
	Cell7.className = "tds";
   	Cell7.align="center";
     	
   	Cell1.innerHTML = '<input type="hidden" name="hdnDueRecoveryDtlId" id="hdnDueRecoveryDtlId'+Row_ID_Instlmnt+'" /><input type="text" name="txtNature" id="txtNature'+Row_ID_Instlmnt+'" size="25" onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="60"/>';
   	Cell2.innerHTML = '<input type="text" name="txtDuesAmount" id="txtDuesAmount'+Row_ID_Instlmnt+'" size="15" onfocus="onFocus(this)" onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
	Cell3.innerHTML = '<input type="text" name="txtDuesNoOfInstlmt" id="txtDuesNoOfInstlmt'+Row_ID_Instlmnt+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);" onKeyPress="numberFormat(this)"/>';
	Cell4.innerHTML = '<select name="cmbDuesStartMonth" id="cmbDuesStartMonth'+Number(Row_ID_Instlmnt)+'" ><option value="-1">--Select--</option>' +LISTMONTHS +'</select>'
	+'&nbsp;<select name="cmbDuesStartYear" id="cmbDuesStartYear'+Number(Row_ID_Instlmnt)+'" ><option value="-1">--Select--</option>'+ LISTYEARS +'</select>';
	Cell5.innerHTML = '<select name="cmbDuesEndMonth" id="cmbDuesEndMonth'+Number(Row_ID_Instlmnt)+'" ><option value="-1">--Select--</option>' +LISTMONTHS +'</select>'
	+'&nbsp;<select name="cmbDuesEndYear" id="cmbDuesEndYear'+Number(Row_ID_Instlmnt)+'" ><option value="-1">--Select--</option>'+ LISTYEARS +'</select>';
	Cell6.innerHTML = '<input type="text" name="txtAssdDueSchemeCode" id="txtAssdDueSchemeCode'+Number(Row_ID_Instlmnt)+'" size="11" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" /><a href="#" id="txtAssdDueSchemeCode'+Number(Row_ID_Instlmnt)+'" onclick="showSchemeCodePopup(this);"><img src="images/search.gif"/></a>';
	Cell7.innerHTML ='<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblAssessedDuesDtls\')" /> ';
   	document.getElementById("hidInstlGridSize").value = Number(Row_ID_Instlmnt)+1;  	
}

function dcrgRecoveryTableAddRow()
{
	if(document.getElementById('tblDCRGRecoveyDtls').rows.length == 10)
	 {
		 alert('You cannot add more than ten DCRG recoveries.');
		 return;
	 }
	
	Row_Id_Rec = document.getElementById("hidRecGridSize").value;
	
	var table=document.getElementById("tblDCRGRecoveyDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
//	var newRow =  document.all("tblDCRGRecoveyDtls").insertRow();	  		
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
      	
	Cell1.innerHTML = '<input type="hidden" name="hdnDcrgRecoveryDtlId" id="hdnDcrgRecoveryDtlId'+Row_Id_Rec+'" /><input type="text" name="txtDcrgNature" id="txtDcrgNature'+Row_Id_Rec+'" size="25" onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="60"/>';
   	Cell2.innerHTML = '<input type="text" name="txtDcrgRecAmount" id="txtDcrgRecAmount'+Row_Id_Rec+'" size="15" onfocus="onFocus(this)" onblur="onBlur(this);setValidAmountFormat(this);setTotalRecoveryAmnt(\'txtDcrgRecAmount\');" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell3.innerHTML = '<input type="text" name="txtDcrgSchemeCode" id="txtDcrgSchemeCode'+Row_Id_Rec+'" size="12" onfocus="onFocus(this)"   onblur="onBlur(this);validateSchemeCode(this);setTotalRecoveryAmnt(\'txtDcrgRecAmount\');" /><a href="#" id="txtDcrgSchemeCode'+Row_Id_Rec+'" onclick="showSchemeCodePopup(this);"><img src="images/search.gif" /></a>';
   	Cell4.innerHTML ='<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblDCRGRecoveyDtls\');setTotalRecoveryAmnt(\'txtDcrgRecAmount\');" /> ';
	
	document.getElementById("hidRecGridSize").value = Number(Row_Id_Rec)+1; 
}

function calAdvanceAmtTotal(field,fieldName)
{
	try
	{
		if(field.value!='')
		{
			var rowCount=Number(document.getElementById("hidAdvanceGridSize").value);
			var total=0;
			for(var cnt=0;cnt<(rowCount+1);cnt++)
			{
				if(document.getElementById(fieldName+cnt)!=null && document.getElementById(fieldName+cnt).value!='')
				{
					total=total+Number(document.getElementById(fieldName+cnt).value);
					document.getElementById("txtBalanceOfAdvance").value=total;
					
				}
			}
			
		}
	}catch(ex)
	{
		
	}
	return false;
}

function addAdvanceDtl(obj)
{
	if(obj.value=='Y')
	{
		document.getElementById("btnAdvDtlsAddRow").disabled=false;
	}
	else
	{
		if(document.getElementById('tblAdvanceDtls').rows.length > 1)
		{
			alert("You cannot select this option, as Advance details present.");
			document.getElementById(obj.id).checked=false;
			document.getElementById("radioBalanceOfAdvanceY").checked="checked";
		}
		else
		{
			document.getElementById("btnAdvDtlsAddRow").disabled=true;
		}
	}
}
function addAssessedDues(obj)
{
	if(obj.value=='Y')
	{
		document.getElementById("btnDuesDtlsAddRow").disabled=false;
	}
	else
	{
		if(document.getElementById('tblAssessedDuesDtls').rows.length > 1)
		{
			alert("You cannot select this option, as Recovery details present.");
			document.getElementById(obj.id).checked=false;
			document.getElementById("radioOthrAsmtY").checked="checked";
		}
		else
		{
			document.getElementById("btnDuesDtlsAddRow").disabled=true;
		}
		
	}
}

function addDCRGRecoveryDtl(obj)
{
	if(obj.value=='Y')
	{
		document.getElementById("btnDcrgRecDtlsAddRow").disabled=false;
	}
	else
	{
		if(document.getElementById('tblDCRGRecoveyDtls').rows.length > 1)
		{
			alert("You cannot select this option, as DCRG Recovery details present.");
			document.getElementById(obj.id).checked=false;
			document.getElementById("radioRecGovtDuesY").checked="checked";
		}
		else
		{
			document.getElementById("btnDcrgRecDtlsAddRow").disabled=true;
		}
	}
}
function validateRecoveryDtls()
{
	var commencementDt = document.getElementById("txtDateOfCommencement").value;
	if(document.getElementById("txtOverPaymentAmt").value != "" && document.getElementById("txtOverPaymentAmt").value != "0.00" &&  document.getElementById("txtOverPaymentAmt").value != "0")
	{
		if(IsEmptyFun("cmbOverPayFromMonth",STARTMONTH,'3')==false)
		{
			return false;
		}
		else if(IsEmptyFun("cmbOverPayFromYear",STARTYEAR,'3')==false)
		{
			return false;
		}
		else if(IsEmptyFun("txtOverPaySchemeCode",SCHEMECODE,'3')==false)
		{
			return false;
		}
		var overPayFromDate="";
		var overPayFromMonth=document.getElementById("cmbOverPayFromMonth").value;
		var overPayFromYear=document.getElementById("cmbOverPayFromYear").value;
		if(overPayFromMonth<10)
		{
			overPayFromDate="01/0" + overPayFromMonth + "/" + overPayFromYear;
		}
		else
		{
			overPayFromDate="01/"+ overPayFromMonth + "/" + overPayFromYear;
		}
		if(compareDate(commencementDt,overPayFromDate) == false)
		{
			alert(COMDTGRTRECDT);
			goToFieldTab('cmbOverPayFromMonth',3);
			return false;
		}
		
	}
	if(document.getElementById("txtArrearOfLicenseFee").value != "" && document.getElementById("txtArrearOfLicenseFee").value != "0.00" &&  document.getElementById("txtArrearOfLicenseFee").value != "0")
	{
		if(IsEmptyFun("cmbArrearFromMonth",STARTMONTH,'3')==false)
		{
			return false;
		}
		else if(IsEmptyFun("cmbArrearFromYear",STARTYEAR,'3')==false)
		{
			return false;
		}
		else if(IsEmptyFun("txtArrearSchemeCode",SCHEMECODE,'3')==false)
		{
			return false;
		}
		var arrearFromDate="";
		var arrearFromMonth=document.getElementById("cmbArrearFromMonth").value;
		var arrearFromYear=document.getElementById("cmbArrearFromYear").value;
		if(arrearFromMonth<10)
		{
			arrearFromDate="01/0" + arrearFromMonth + "/" + arrearFromYear;
		}
		else
		{
			arrearFromDate="01/"+ arrearFromMonth + "/" + arrearFromYear;
		}
		if(compareDate(commencementDt,arrearFromDate) == false)
		{
			alert(COMDTGRTRECDT);
			goToFieldTab('cmbArrearFromMonth',3);
			return false;
		}
	}
	if(document.getElementById("txtLicenseFeeForGovt").value != "" && document.getElementById("txtLicenseFeeForGovt").value != "0.00" &&  document.getElementById("txtLicenseFeeForGovt").value != "0")
	{
		if(IsEmptyFun("cmbLicenseFeeFromMonth",STARTMONTH,'3')==false)
		{
			return false;
		}
		else if(IsEmptyFun("cmbLicenseFeeFromYear",STARTYEAR,'3')==false)
		{
			return false;
		}
		else if(IsEmptyFun("txtLicFeeSchemeCode",SCHEMECODE,'3')==false)
		{
			return false;
		}
		var licFeeFromDate="";
		var licFeeFromMonth=document.getElementById("cmbLicenseFeeFromMonth").value;
		var licFeeFromYear=document.getElementById("cmbLicenseFeeFromYear").value;
		if(licFeeFromMonth<10)
		{
			licFeeFromDate="01/0" + licFeeFromMonth + "/" + licFeeFromYear;
		}
		else
		{
			licFeeFromDate="01/"+ licFeeFromMonth + "/" + licFeeFromYear;
		}
		if(compareDate(commencementDt,licFeeFromDate) == false)
		{
			alert(COMDTGRTRECDT);
			goToFieldTab('cmbLicenseFeeFromMonth',3);
			return false;
		}
	}
	var advCntLength=document.getElementById("hidAdvanceGridSize").value;
	
	 if(document.getElementById('tblAdvanceDtls').rows.length > 1)
	 {
		for(var rowAdvCnt=0;rowAdvCnt<Number(advCntLength);rowAdvCnt++)
		{		
			try
			{	
				
				if(IsEmptyFun("cmbTypeOfAdvance"+rowAdvCnt,ADVANCETYPE,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAdvanceAmount"+rowAdvCnt,AMOUNT,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtNoOfInstallment"+rowAdvCnt,NOOFINSTL,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbAdvanceStartMonth"+rowAdvCnt,STARTMONTH,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbAdvanceStartYear"+rowAdvCnt,STARTYEAR,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbAdvanceEndMonth"+rowAdvCnt,ENDMONTH,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbAdvanceEndYear"+rowAdvCnt,ENDYEAR,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAdvSchemeCode"+rowAdvCnt,SCHEMECODE,'3')==false)
				{
					return false;
				}
				var advFromMonth=Number(document.getElementById("cmbAdvanceStartMonth"+rowAdvCnt).value);
				var advFromYear=Number(document.getElementById("cmbAdvanceStartYear"+rowAdvCnt).value);
				var advToMonth= Number(document.getElementById("cmbAdvanceEndMonth"+rowAdvCnt).value);
				var advToYear=Number(document.getElementById("cmbAdvanceEndYear"+rowAdvCnt).value);
				var noOfInstallments=Number(document.getElementById("txtNoOfInstallment"+rowAdvCnt).value);
				
				var advFromDate="";
				
				if(advFromMonth<10)
				{
					advFromDate="01/0" + advFromMonth + "/" + advFromYear;
				}
				else
				{
					advFromDate="01/"+ advFromMonth + "/" + advFromYear;
				}
				if(compareDate(commencementDt,advFromDate) == false)
				{
					alert(COMDTGRTRECDT);
					goToFieldTab("cmbAdvanceStartMonth"+rowAdvCnt,3);
					return false;
				}
				if(advFromYear > advToYear)
				{
					goToFieldTab('cmbAdvanceStartYear'+rowAdvCnt,3);
			    	alert("Start Year should be less than End Year");
			    	return false;
				}
				else if(advFromYear == advToYear)
				{
					if(advFromMonth > advToMonth)
					{
					   	alert("Start month-year should be less than End month-year");
				    	goToFieldTab('cmbAdvanceStartMonth'+rowAdvCnt,3);
				    	return false;
					}
				}
				var monthDiff=Number(getMonthDiff(advFromYear,advFromMonth,advToYear,advToMonth));
				
				if(monthDiff != noOfInstallments)
				{
					  	alert("Difference between Start month-year and End month-year should be same as number of installments");
						goToFieldTab('txtNoOfInstallment'+rowAdvCnt,3);
						return false;
				}
				
				
			}
			catch(ex)
			{
				
			}
		 }
	   }
		var dueCntLength=document.getElementById("hidInstlGridSize").value;
		
		 if(document.getElementById('tblAssessedDuesDtls').rows.length > 1)
		 {
			for(var rowDueCnt=0;rowDueCnt<Number(dueCntLength);rowDueCnt++)
			{		
				try
				{	
					if(IsEmptyFun("txtNature"+rowDueCnt,NATURE,'3')==false)
					{
						return false;
					}
					else if(IsEmptyFun("txtDuesAmount"+rowDueCnt,AMOUNT,'3')==false)
					{
						return false;
					}
					else if(IsEmptyFun("txtDuesNoOfInstlmt"+rowDueCnt,NOOFINSTL,'3')==false)
					{
						return false;
					}
					else if(IsEmptyFun("cmbDuesStartMonth"+rowDueCnt,STARTMONTH,'3')==false)
					{
						return false;
					}
					else if(IsEmptyFun("cmbDuesStartYear"+rowDueCnt,STARTYEAR,'3')==false)
					{
						return false;
					}
					else if(IsEmptyFun("cmbDuesEndMonth"+rowDueCnt,ENDMONTH,'3')==false)
					{
						return false;
					}
					else if(IsEmptyFun("cmbDuesEndYear"+rowDueCnt,ENDYEAR,'3')==false)
					{
						return false;
					}
					else if(IsEmptyFun("txtAssdDueSchemeCode"+rowDueCnt,SCHEMECODE,'3')==false)
					{
						return false;
					}
					var dueFromMonth=Number(document.getElementById("cmbDuesStartMonth"+rowDueCnt).value);
					var dueFromYear=Number(document.getElementById("cmbDuesStartYear"+rowDueCnt).value);
					var dueToMonth= Number(document.getElementById("cmbDuesEndMonth"+rowDueCnt).value);
					var dueToYear=Number(document.getElementById("cmbDuesEndYear"+rowDueCnt).value);
					var dueNoOfInstlmts=Number(document.getElementById("txtDuesNoOfInstlmt"+rowDueCnt).value);
					
					var dueFromDate="";
					
					if(dueFromMonth<10)
					{
						dueFromDate="01/0" + dueFromMonth + "/" + dueFromYear;
					}
					else
					{
						dueFromDate="01/"+ dueFromMonth + "/" + dueFromYear;
					}
					if(compareDate(commencementDt,dueFromDate) == false)
					{
						alert(COMDTGRTRECDT);
						goToFieldTab("cmbDuesStartMonth"+rowDueCnt,3);
						return false;
					}
					if(dueFromYear > dueToYear)
					{
						goToFieldTab('cmbDuesStartYear'+rowDueCnt,3);
				    	alert("Start Year should be less than End Year");
				    	return false;
					}
					else if(dueFromYear == dueToYear)
					{
						if(dueFromMonth > dueToMonth)
						{
						   	alert("Start month-year should be less than End month-year");
					    	goToFieldTab('cmbDuesEndMonth'+rowDueCnt,3);
					    	return false;
						}
					}
					var dueMonthDiff=Number(getMonthDiff(dueFromYear,dueFromMonth,dueToYear,dueToMonth));
					if(dueMonthDiff != dueNoOfInstlmts)
					{
						  	alert("Difference between start month-year and end month-year should be same as number of installments");
							goToFieldTab('txtDuesNoOfInstlmt'+rowDueCnt,3);
							return false;
					}
				}
				catch(ex)
				{
					
				}
			}
		 }
//		 var recCntLength=document.getElementById("hidRecGridSize").value;
			
//		 if(document.getElementById('tblDCRGRecoveyDtls').rows.length > 1)
//		 {
//			for(var rowRecCnt=0;rowRecCnt<Number(recCntLength);rowRecCnt++)
//			{		
//				try
//				{	
//					if(IsEmptyFun("txtDcrgNature"+rowRecCnt,NATURE,'3')==false)
//					{
//						return false;
//					}
//					else if(IsEmptyFun("txtDcrgRecAmount"+rowRecCnt,AMOUNT,'3')==false)
//					{
//						return false;
//					}
//					else if(IsEmptyFun("txtDcrgSchemeCode"+rowRecCnt,SCHEMECODE,'3')==false)
//					{
//						return false;
//					}
//				}
//				catch(ex)
//				{
//					
//				}
//			 }
//			if(calculateDcrgRecovery()==false)
//			 {
//				 return false;
//			 }
//		 }
		 
	
	 return true;
}
function calcOtherRecovery()
{
	var OtherRecovery=0;
	if(validateRecoveryDtls())
	{
		var currentDate=document.getElementById("hdnCurrDate").value;
		var lArrDate = currentDate.split("/");
		var currentMonth=Number(lArrDate[1]);
		var currentYear=lArrDate[2];
		
		//Calculate recovery from advance detail for current month
		var AdvanceAmount=document.getElementsByName("txtAdvanceAmount");
		var NoOfInstallment=document.getElementsByName("txtNoOfInstallment");
		var AdvanceStartMonth=document.getElementsByName("cmbAdvanceStartMonth");
		var AdvanceStartYear=document.getElementsByName("cmbAdvanceStartYear");
		var AdvanceEndMonth=document.getElementsByName("cmbAdvanceEndMonth");
		var AdvanceEndYear=document.getElementsByName("cmbAdvanceEndYear");
		
		if(AdvanceStartMonth.length>0)
		{
			for(var i=0;i<AdvanceStartMonth.length;i++)
			{
				
				if((currentYear >= Number(AdvanceStartYear[i].value)) &&  (currentYear <= Number(AdvanceEndYear[i].value)))
				{
					if((currentMonth >= Number(AdvanceStartMonth[i].value)) && (currentMonth <= Number(AdvanceEndMonth[i].value)))
					{
						var Installment=Number(AdvanceAmount[i].value)/Number(NoOfInstallment[i].value);
						OtherRecovery=Number(OtherRecovery)+Number(Installment);
					}
				}
				
			}
		}
		//Calculate recovery from Assessed Dues detail for current month
		
		var DueAmount=document.getElementsByName("txtDuesAmount");
		var DuesNoOfInstallment=document.getElementsByName("txtDuesNoOfInstlmt");
		var DuesStartMonth=document.getElementsByName("cmbDuesStartMonth");
		var DuesStartYear=document.getElementsByName("cmbDuesStartYear");
		var DuesEndMonth=document.getElementsByName("cmbDuesEndMonth");
		var DuesEndYear=document.getElementsByName("cmbDuesEndYear");
		if(DueAmount.length>0)
		{
			for(var j=0;j<DueAmount.length;j++)
			{
				if((currentYear >= DuesStartYear[j].value) &&  (currentYear <= DuesEndYear[j].value))
				{
					if((currentMonth >= Number(DuesStartMonth[j].value)) && (currentMonth <= Number(DuesEndMonth[j].value)))
					{
						var DueInstallment=Number(DueAmount[j].value)/Number(DuesNoOfInstallment[j].value);
						OtherRecovery=Number(OtherRecovery)+Number(DueInstallment);
						
					}
				}
				
			}
		}
		var OverPayAmount = Number(document.getElementById("txtOverPaymentAmt").value);
		var OverPayAmountForMonth = Number(document.getElementById("cmbOverPayFromMonth").value);
		var OverPayAmountForYear = Number(document.getElementById("cmbOverPayFromYear").value);
		var ArrearAmount = Number(document.getElementById("txtArrearOfLicenseFee").value);
		var ArrearAmountForMonth = Number(document.getElementById("cmbArrearFromMonth").value);
		var ArrearAmountForYear = Number(document.getElementById("cmbArrearFromYear").value);
		var LicenceFee = Number(document.getElementById("txtLicenseFeeForGovt").value);
		var LicenceFeeForMonth = Number(document.getElementById("cmbLicenseFeeFromMonth").value);
		var LicenceFeeForYear = Number(document.getElementById("cmbLicenseFeeFromYear").value);
		
		if(OverPayAmount != 0)
		{
			if(OverPayAmountForMonth==currentMonth && OverPayAmountForYear==currentYear)
			{
				OtherRecovery=Number(OtherRecovery)+OverPayAmount;
			}
		}
		if(ArrearAmount != 0)
		{
			if(ArrearAmountForMonth==currentMonth && ArrearAmountForYear==currentYear)
			{
				OtherRecovery=Number(OtherRecovery)+ArrearAmount;
			}
		}
		if(LicenceFee != 0)
		{
			if(LicenceFeeForMonth==currentMonth && LicenceFeeForYear==currentYear)
			{
				OtherRecovery=Number(OtherRecovery)+LicenceFee;
			}
		}
		
		document.getElementById("txtOtherRecovery").value=OtherRecovery +".00";
		if(document.getElementById("txtGrossAmount").value != "")
		{
			var netAmount=Number(document.getElementById("txtGrossAmount").value)-Number(OtherRecovery);
			document.getElementById("txtNetAmount").value=netAmount+".00";
		}
	}
	
}

function calculateDcrgRecovery()
{
	 var recCntLength=document.getElementById("hidRecGridSize").value;
	 var totalDcrgAmount=0;
	 if(document.getElementById('tblDCRGRecoveyDtls').rows.length > 1)
	 {
		var flag=0;
		for(var rowRecCnt=0;rowRecCnt<Number(recCntLength);rowRecCnt++)
		{		
			try
			{	
				if(flag==0)
				{
					flag=1;
					totalDcrgAmount=Number(document.getElementById("txtDcrgRecAmount"+rowRecCnt).value);
				}
				else
				{
					totalDcrgAmount=Number(totalDcrgAmount)+Number(document.getElementById("txtDcrgRecAmount"+rowRecCnt).value);
				}
				
			}
			catch(ex)
			{
				
			}
		 }
		if(document.getElementById("txtTotalDcrgAmount").value !='' && document.getElementById("txtTotalDcrgAmount").value !='0.00')
		{
			var dcrgAmount=document.getElementById("txtTotalDcrgAmount").value;
			if(dcrgAmount < totalDcrgAmount)
			{
				alert("Dcrg Amount Should be greater than Recovery of Dcrg");
				goToFieldTab('txtTotalDcrgAmount',1);
				return false;
				
			}
		}
	 }
	 return true;
}

function getMonthDiff(fromYear,fromMonth,toYear,toMonth)
{
	var monthDiff=0;
	var monthCnt=0;
	fromYear=Number(fromYear);
	toYear=Number(toYear);
	fromMonth=Number(fromMonth);
	toMonth=Number(toMonth);
	
	if(toMonth < fromMonth)
	{
		toYear=toYear-1;
	}
	if(toYear > fromYear)
	{
		monthDiff=(toYear-fromYear)*12;
	}
	if(toMonth > fromMonth)
	{
		monthCnt=toMonth-fromMonth+1;
	}
	if(toMonth < fromMonth)
	{
		var months=fromMonth;
		while(months<=12)
		{
			monthCnt++;
			months++;
		}
		monthCnt=monthCnt+toMonth;
		
	}
	if((fromMonth == toMonth) && (fromYear == toYear))
	{
		monthDiff = 1;
	}
	if((fromMonth == toMonth) && (fromYear != toYear))
	{
		monthDiff = monthDiff+1;
	}
	if((fromYear == toYear))
	monthDiff=monthDiff+monthCnt;
	return monthDiff;
}

function LeapYear(year)
{	
	if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0))
	{
		return true;
	}
	else 	
	{
		return false;		
	}
}
function getDateDiffDays(strDate1,strDate2)
{
	strDate1 = strDate1.split("/"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	starttime = new Date(starttime.valueOf()); 
	alert(strDate1[2]);
	//End date split to UK date format 
	strDate2 = strDate2.split("/"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	endtime = new Date(endtime.valueOf()); 
	if(endtime >= starttime) 
	{ 
		var setDay = 0; 
		var lIntPenSerYear = strDate2[2] - strDate1[2];
		var lIntPenSerMonth = strDate2[1]- strDate1[1];
		var lIntPenSerDay = strDate2[0] - strDate1[0]; 
		lIntPenSerDay = lIntPenSerDay + 1;
		var intMonth = parseInt(strDate1[1]);

		var intday = parseInt(strDate1[0]);
		intYear = parseInt(strDate1[2]);
		while(parseInt(strDate2[2]) >= intYear)
		{ 
			if (intMonth>=13) 
			{ 
				intMonth=1;
				intYear++;
			}
			if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) 
			{
				setDay = 31; 	
			}
			if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) 
			{
				setDay = 30;
			}
			if (intMonth == 2) 
			{
				if (LeapYear(intYear) == true) 
				{
					setDay = 29;
				}
				else 
				{
					setDay = 28;
				}
			}
			if(setDay!=0)
			{
				while(lIntPenSerDay > setDay)
				{
					lIntPenSerDay -= setDay;
					lIntPenSerMonth++;
					if(lIntPenSerMonth==12)
					{
						lIntPenSerMonth=0;
						lIntPenSerYear++;
					}
				}
				while(lIntPenSerDay < 0)
				{
					lIntPenSerDay = setDay + lIntPenSerDay;
					lIntPenSerMonth--;
					if(lIntPenSerMonth<=-1)
					{
						lIntPenSerMonth=12+lIntPenSerMonth;
						lIntPenSerYear--; 
					}
				}
				if(lIntPenSerMonth <=-1)
				{
					lIntPenSerMonth=12+lIntPenSerMonth;
					lIntPenSerYear--; 
				}
				if(strDate1.toString() == strDate2.toString())
					{lIntPenSerDay=1;}
				alert(lIntPenSerMonth);
				//document.getElementById("period").value=lIntPenSerYear*365+lIntPenSerMonth*setDay+lIntPenSerDay;
				return(lIntPenSerYear*365+lIntPenSerMonth*setDay);
				
			}
			else 
			{
				return '0~0~0'; 
			}
			intMonth++; 
		}
	}
	else
	{
		return '0~0~0'; 
	}
} 

function validateSchemeCode(object)
{
	var schemeCode=object.value;
	var url;
	if(schemeCode != "")
	{
		url="ifms.htm?actionFlag=validateSchemeCode&SchemeType=R&SchemeCode="+schemeCode;
		validateSchemeCodeUsingAjax(url,object.id,schemeCode);
	}
}
function validateSchemeCodeUsingAjax(uri,elementId,schemeCode)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&SchemeCode="+schemeCode,
		        onSuccess: function(myAjax) {
					getStateChangedForValidSchemeCode(myAjax,elementId);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
//   xmlHttp=GetXmlHttpObject();
//
//   if (xmlHttp==null)
//   {
//      return;
//   }  
//           
//   xmlHttp.onreadystatechange=function validateSchemeCodeOnStateChanged() 
//   { 
//		
//	   if (xmlHttp.readyState==complete_state)
//	   { 
//		  var XMLDoc=xmlHttp.responseXML.documentElement;
//	   
//	      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//	    
//	     if(XmlHiddenValues[0].childNodes[0].text == 'N')
//	     {
//	    	 alert("Please Enter Correct Scheme Code.");
//	    	 document.getElementById(elementId).value="";
//	    	 document.getElementById(elementId).focus();
//	     }
//
//	   }
//	};
//   xmlHttp.open("POST",uri,false);
//   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//   xmlHttp.send(uri);
   
}
function getStateChangedForValidSchemeCode(myAjax,elementId)
{
	   var XMLDoc =  myAjax.responseXML.documentElement;
	   
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	    
	     if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue == 'N')
	     {
	    	 alert("Please Enter Correct Scheme Code.");
	    	 document.getElementById(elementId).value="";
    	 document.getElementById(elementId).focus();
	     }

}

function showSchemeCodeList(elementId)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var url='ifms.htm?actionFlag=loadSchemeCodePopUp&schemeType=R&elementId='+elementId+'&schemeCode='+document.getElementById(elementId).value;
	var urlstyle = "height=500,width=650,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "DCRGHistory", urlstyle);
}

function showSchemeCodePopup(object)
{
	var schemeCode;
	if(object.value == undefined)
	{
		schemeCode ="";
	}
	else
	{
		schemeCode=object.value;	
	}
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var url='ifms.htm?actionFlag=loadSchemeCodePopUp&schemeType=R&elementId='+object.id+'&schemeCode='+schemeCode;
	var urlstyle = "height=500,width=650,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "DCRGHistory", urlstyle);
}
function setTotalRecoveryAmnt(FieldName)
{
	if(document.getElementById('tblDCRGRecoveyDtls').rows.length == 1)
	{
		document.getElementById("txtTotRecoveryAmnt").value = Number("0.00");
	}
	var rowCount=Number(document.getElementById("hidRecGridSize").value);
	var total=0;
	for(var cnt=0;cnt<(rowCount+1);cnt++)
	{
		if(document.getElementById(FieldName+cnt)!=null && document.getElementById(FieldName+cnt).value!='')
		{
			total=total+Number(document.getElementById(FieldName+cnt).value);
			document.getElementById("txtTotRecoveryAmnt").value=total;
			
		}
	}
	document.getElementById("txtDcrgPayableAmount").value = Number(document.getElementById("txtAmntAfterWithHeld").value) - Number(document.getElementById("txtTotRecoveryAmnt").value);
}
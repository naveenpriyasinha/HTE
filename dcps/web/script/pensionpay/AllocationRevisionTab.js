var Row_ID_CVP=0;
function cvpRestDtlTableAddRow()
{	
	Row_ID_CVP = document.getElementById("hidCvpGridSize").value;
	
	var table=document.getElementById("tblCvpRestnDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	//var newRow =  document.all("tblCvpRestnDtls").insertRow();	  			  		
	
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
      	   
   	Cell1.innerHTML = '<input type="text" name="txtCvpRestnAmt" id="txtCvpRestnAmt'+Row_ID_CVP+'" size="15"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>'
   	                   +'<input type="hidden" name="hdnCvpRestnDtlId" id="hdnCvpRestnDtlId'+Row_ID_CVP+'" />';	
	Cell2.innerHTML = '<input type="text" name="txtRestnFromDate" id="txtRestnFromDate'+Row_ID_CVP+'" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);setCvpRestnToDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtRestnFromDate'+Row_ID_CVP+'\', 375, 570, \'\', \'\', '+Number(Row_ID_CVP)+');" />';
   	Cell3.innerHTML = '<input type="text" name="txtRestnToDate" id="txtRestnToDate'+Row_ID_CVP+'" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtRestnToDate'+Row_ID_CVP+'\', 375, 570, \'\', \'\', '+Number(Row_ID_CVP)+');" />';
   	Cell4.innerHTML = '<input type="checkbox" id="chkRestnAplnRecvd'+Number(Row_ID_CVP)+'" name="chkRestnAplnRecvd"  value="Y"/>';
   	Cell5.innerHTML = '<input type="text" name="txtRestnAplnRecvdDate" id="txtRestnAplnRecvdDate'+Row_ID_CVP+'" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);validateApplnRecvdDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtRestnAplnRecvdDate'+Row_ID_CVP+'\', 375, 570, \'\', \'\', '+Number(Row_ID_CVP)+');" />';
   	Cell6.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblCvpRestnDtls\')" /> ';
   	document.getElementById("hidCvpGridSize").value = Number(Row_ID_CVP)+1;  
}

function validateAllocationAmt()
{
	var totalPension=0;
	var bf010436=document.getElementById("txtBefore01041936").value;
	var af010436=document.getElementById("txtAfter01041936").value;
	var af011156=document.getElementById("txtAfter01111956").value;
	var bf010560=document.getElementById("txtAfter01051960").value;
	var orgZillaPard=document.getElementById("txtZillaParishad").value;
	
	if(bf010436.length>0)
	{
		totalPension=bf010436;
		
	}
	if(af010436.length>0)
	{
		totalPension=Number(totalPension)+Number(af010436);
	}
	if(af011156.length>0)
	{
		totalPension=Number(totalPension)+Number(af011156);
	}
	if(bf010560.length>0)
	{
		totalPension=Number(totalPension)+Number(bf010560);
	}
	if(orgZillaPard.length>0)
	{
		totalPension=Number(totalPension)+Number(orgZillaPard);
	}
	totalPension=roundTotal(totalPension, 2);
	document.getElementById("hdnPensionAmount").value=totalPension;
	//setValidAmountFormat(document.getElementById("hdnPensionAmount"));
	var pnsnAmount=document.getElementById("txtPensionAmount").value;

	if(pnsnAmount.length>0)
	{
		if(pnsnAmount != totalPension)
		{
			alert("Total of allocation amounts should be same as Pension amount.");
			goToTab(1);
			document.getElementById("txtBefore01041936").focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	return true;
	
}
function roundTotal(rnum, rlength) { 
	// Arguments: number to round, number of decimal places
	  var newnumber = Math.round(rnum*Math.pow(10,rlength))/Math.pow(10,rlength);
	  return parseFloat(newnumber); // Output the result to the form field (change for your purposes)
}
function roundNumber(object, rlength) { 
	  var elementId = object.id;
	  var rnum = object.value;
	// Arguments: number to round, number of decimal places
	  var newnumber = Math.round(rnum*Math.pow(10,rlength))/Math.pow(10,rlength);
	  document.getElementById(elementId).value = parseFloat(newnumber); // Output the result to the form field (change for your purposes)
}

function enableAllocationFields(obj)
{
	
	if(obj.value=='Y')
	{
		document.getElementById("txtPensionAmount").value = document.getElementById("txtBasicPensionAmt").value;
		//setValidAmountFormat(document.getElementById("txtPensionAmount"));
		document.getElementById("txtBefore01041936").disabled=false;
		document.getElementById("txtAfter01041936").disabled=false;
		document.getElementById("txtAfter01111956").disabled=false;
		document.getElementById("txtAfter01051960").disabled=false;
		document.getElementById("txtZillaParishad").disabled=false;
	}
	else
	{
		document.getElementById("txtPensionAmount").value = document.getElementById("txtBasicPensionAmt").value;
		document.getElementById("txtBefore01041936").disabled=true;
		document.getElementById("txtAfter01041936").disabled=true;
		document.getElementById("txtAfter01111956").disabled=true;
		document.getElementById("txtAfter01051960").disabled=true;
		document.getElementById("txtAfter01051960").value = document.getElementById("txtPensionAmount").value;
		document.getElementById("txtZillaParishad").disabled=true;
		document.getElementById("txtBefore01041936").value="";
		document.getElementById("txtAfter01041936").value="";
		document.getElementById("txtAfter01111956").value="";
		document.getElementById("txtZillaParishad").value="";

	}
}

function validateAllocationRevisionDtls()
{
	if(IsEmptyFun("txtPensionAmount",PNSNAMOUNT,'1')==false)
	{
		return false;
	}
	var commencementDt = document.getElementById("txtDateOfCommencement").value;
	var cvpCntLength=document.getElementById("hidCvpGridSize").value;
	var restnPrvsToMonth=0;
	var restnPrvsToYear=0;
	var restnPrvsMonth=0;
	var restnPrvsYear=0;
	var nextDate="";
	var flag=0;
	 if(document.getElementById('tblCvpRestnDtls').rows.length > 1)
	 {
		for(var rowCvpCnt=0;rowCvpCnt<Number(cvpCntLength);rowCvpCnt++)
		{		
			try
			{	
				if(IsEmptyFun("txtCvpRestnAmt"+rowCvpCnt,AMOUNT,'1')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtRestnFromDate"+rowCvpCnt,COMTNFROMDATE,'1')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtRestnToDate"+rowCvpCnt,COMTNTODATE,'1')==false)
				{
					return false;
				}
				else if(document.getElementById("chkRestnAplnRecvd"+rowCvpCnt).checked)
				{
					if(IsEmptyFun("txtRestnAplnRecvdDate"+rowCvpCnt,'Please Enter Restoration Application Received Date.','1')==false)
					{
						return false;
					}
				}
				
								
				var restnFromDate=document.getElementById("txtRestnFromDate"+rowCvpCnt).value;
				var restnToDate=document.getElementById("txtRestnToDate"+rowCvpCnt).value;
				var restnApplnRecvdDate = document.getElementById("txtRestnAplnRecvdDate"+rowCvpCnt).value;
				
				if(commencementDt != restnFromDate)
				{
					if(compareDate(commencementDt,restnFromDate) == false)
					{
						alert(COMDTRESTDT);
						goToFieldTab("txtRestnFromDate"+rowCvpCnt,1);
						return false;
					}
				}
				if(compareDate(restnFromDate,restnToDate) == false)
				{
					alert(COMTNFROMTODATE);
					goToFieldTab("txtRestnToDate"+rowCvpCnt,1);
					return false;
				}
				var prvsMonthDate = getPreviousMonthDate(restnToDate);
								
				if(restnApplnRecvdDate.length > 0 && prvsMonthDate.length >0)
				{
						if(compareDate(prvsMonthDate,restnApplnRecvdDate) == false)
						{
							alert("Please Enter Application received date one month earlier or greater than restoration to date.");
							document.getElementById("txtRestnAplnRecvdDate"+rowCvpCnt).value="";
							goToFieldTab("txtRestnAplnRecvdDate"+rowCvpCnt,1);
							return false;
						}
				}
				//validation of restoration from date
//				if(flag == 1)
//				{
//					if(nextDate != restnFromDate)
//					{
//						alert("Restoration From Date must be next date of previous restoration To Date.");
//						goToFieldTab('txtRestnFromDate'+rowCvpCnt,1);
//						return false;
//					}
//				}
//				nextDate = getNextRestnDate(document.getElementById("txtRestnToDate"+rowCvpCnt).value);
				
//				if(flag == 1)
//				{
//					if(restnPrvsToMonth < 12)
//					{
//						if(restnFromMonth != restnPrvsMonth || restnFromYear != restnPrvsToYear)
//						{
//							alert("Start month should be next month of previous restoration end month.");
//							goToFieldTab('cmbRestnFromMonth'+rowCvpCnt,4);
//							return false;
//							
//						}
//					}
//					if(restnPrvsToMonth == 12)
//					{
//						if(restnFromMonth != 1 || restnFromYear != restnPrvsYear)
//						{
//							alert("Start month should be next month of previous restoration end month.");
//							goToFieldTab('cmbRestnFromMonth'+rowCvpCnt,4);
//							return false;
//							
//						}
//					}
//				}
//				restnPrvsToMonth= Number(document.getElementById("cmbRestnToMonth"+rowCvpCnt).value);
//				restnPrvsToYear=Number(document.getElementById("cmbRestnToYear"+rowCvpCnt).value);
//				restnPrvsMonth=restnPrvsToMonth+1;
//				restnPrvsYear=restnPrvsToYear+1;
				flag=1;
			}
			catch(ex)
			{
				
			}
		 }
	 }
	 return true;
}

function onChangePnsnAmt()
{
	 if(document.getElementById("radioAllocIndicatorN").checked)
	 {
		 document.getElementById("txtAfter01051960").value = document.getElementById("txtPensionAmount").value;
	 }
}

function getPnsnrRevisionDtls()
{
	var ppoNo=document.getElementById("txtPpoNo").value;
	var pnsnrName=document.getElementById("txtPnsnrName").value;
	var ppoInwardDate=document.getElementById("hdnPpoInwardDate").value;
	var pensionerCode=document.getElementById("hdnPensionerCode").value;
	var ropType=document.getElementById("cmbRopType").value;
	var basicPnsnAmt=document.getElementById("txtBasicPensionAmt").value;
	//var commutationAmt=document.getElementById("txtTotalCvpAmount").value;
	var cvpMonthlyAmt=document.getElementById("txtCvpMonthly").value;
	var fp1Amount=document.getElementById("txtFp1amount").value;
	var fp2Amount=document.getElementById("txtFp2amount").value;
	var dcrgAmount=document.getElementById("txtTotalDcrgAmount").value;
	
	var url="ifms.htm?actionFlag=loadPensionerRevisionDtls&ppoNo="+ ppoNo +"&pnsnrName="+ pnsnrName +"&ppoInwardDate="+ ppoInwardDate +"&pensionerCode="+pensionerCode
				+ "&ropType=" + ropType + "&basicPnsnAmt=" + basicPnsnAmt + "&cvpMonthlyAmt=" + cvpMonthlyAmt
				+ "&fp1Amount=" + fp1Amount + "&fp2Amount=" + fp2Amount + "&dcrgAmount=" + dcrgAmount;
	
	var newWindow;
	var urlstyle = "height=400,width=1150,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow = window.open(url, "CorrectionDtls", urlstyle);
}

function getNextRestnDate(lStrDate)
{
//	var lStrDate = document.getElementById("txtDateOfRetirement").value;
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		//document.getElementById("hdnCommencementDate").value = (new String(""));
		return "";
	}
	date.setDate(date.getDate()+1);

	if(date.getMonth()==11)
	 {
		return (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();

	 }
	 else
	 {
		  return (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();

	 }
}

function validateApplnRecvdDate(object)
{
	var elementId = object.id;
	var rowId = elementId.substring(21);
	var toDate = document.getElementById("txtRestnToDate"+rowId).value;
	var prvsMonthDate = getPreviousMonthDate(toDate);
		
	if(object.value.length > 0 && prvsMonthDate.length >0)
	{
			if(compareDate(prvsMonthDate,object.value) == false)
			{
				alert("Please Enter Application received date one month earlier or greater than restoration to date.");
				document.getElementById("txtRestnAplnRecvdDate"+rowId).value="";
				goToFieldTab("txtRestnAplnRecvdDate"+rowId,1);
				return false;
			}
	}
	return true;
}

function getPreviousMonthDate(lStrDate)
{
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);

	if(lStrDate == "")
	{
		return "";
	}
	date.setDate(date.getDate() - 30); 

	if(date.getMonth()==11)
    {
		return (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
	}
	else
	{
	   	return (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
	}
}
function setCvpRestnToDate(object)
{
	lStrDate = object.value;
	var rowCnt = object.id.substring(16);
	
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById("txtRestnToDate"+rowCnt).value = (new String(""));
		return;
	}
	date.setFullYear(date.getFullYear()+ 15);
	date.setDate(date.getDate() - 1);
	
	var day = new String(date.getDate()).length > 1 ? date.getDate() : "0" + new String(date.getDate());
	var month = (new String(date.getMonth()+1)).length > 1 ? date.getMonth()+1 : "0" + new String(date.getMonth()+1);
	var year = date.getFullYear();
		
//	document.getElementById("txtRestnToDate"+rowCnt).value = new String(date.getDate()).length > 1 ? date.getDate() : "0" + new String(date.getDate())
//															+ "/" +(new String(date.getMonth()+1)).length > 1 ? date.getMonth()+1 : "0" + new String(date.getMonth()+1)
//															+ "/" + date.getFullYear();
	document.getElementById("txtRestnToDate"+rowCnt).value = day+"/"+month+"/"+year;
	
//	if(lArrDate[0] == 1)
//	{
//		var x;
//		if(lArrDate[1] == 1)
//		{
//			x = DaysArray2(12 ,lArrDate[2]-1);
//			lArrDate[1] = 13;
//			date.setFullYear(date.getFullYear()-1);
//		}
//		else
//		{
//			if(lArrDate[1]-1 == 2 )
//			{
//				x = daysInFebruaryFP(lArrDate[2]);
//			}
//			else
//			{
//				alert(date.getMonth());
//				x = DaysArrayFP(date.getMonth());
//			}
//		}
//		document.getElementById("txtRestnToDate"+rowCnt).value =  (new String(x).length > 1 ? x : "0" + new String(x)) + "/" +(new String(lArrDate[1] -1).length > 1 ? lArrDate[1] -1 : "0"+new String(lArrDate[1] -1))+"/"+date.getFullYear();
//
//	}
//	else
//	{
//		document.getElementById("txtRestnToDate"+rowCnt).value = (new String(lArrDate[0]-1).length > 1 ? lArrDate[0]-1 : "0" + new String(lArrDate[0]-1)) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + new String(lArrDate[1]) )+"/"+date.getFullYear();
//	}
}
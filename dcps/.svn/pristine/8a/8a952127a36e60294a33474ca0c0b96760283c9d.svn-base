var EvtgridCount = 0;
var SergridCount = 0;
var qulfSerCount=0;

function advAthoDtlsAddRow()
{
	var rowCnt = document.getElementById("hidAuthoGridSize").value;
	var newRow =  document.getElementById("tblAuthoTypeAdvDtls").insertRow(document.getElementById("tblAuthoTypeAdvDtls").rows.length);
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
   	var Cell8 = newRow.insertCell(7);
	Cell8.className = "tds";
   	Cell8.align="center";	
		
	Cell1.innerHTML = '<select name="cmbTypeOfOrder" id="cmbTypeOfOrder'+Number(rowCnt)+'"><option value="-1">-- Select --</option>' +ORDERTYPE+ '</select>';
	Cell2.innerHTML = '<input type="text" name="txtAuthoOrderNo" maxlength="100" id="txtAuthoOrderNo'+Number(rowCnt)+'" onblur="onBlur(this);" />';
	Cell3.innerHTML = '<input type="text" name="txtAuthoOrderDate"  id="txtAuthoOrderDate'+Number(rowCnt)+'" onblur="onBlur(this);chkValidDate(this);"  onkeypress="digitFormat(this);dateFormat(this);"   maxlength="10"  size="10" value=""/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtAuthoOrderDate'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');"/>';
	Cell4.innerHTML = '<input type="text" name="txtAuthoPnsnrName" maxlength="100" id="txtAuthoPnsnrName'+Number(rowCnt)+'" onblur="onBlur(this);" />';
	Cell5.innerHTML = '<input type="text" name="txtAuthoBasicAmt" id="txtAuthoBasicAmt'+Number(rowCnt)+'" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);"    style="width:90px;text-align: right" />';
	Cell6.innerHTML = '<input type="text" name="txtAuthoEFP" id="txtAuthoEFP'+Number(rowCnt)+'" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);"    style="width:90px;text-align: right" />';
	Cell7.innerHTML = '<input type="text" name="txtAuthoFP" id="txtAuthoFP'+Number(rowCnt)+'" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);"    style="width:90px;text-align: right" />';	
	Cell8.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick="adjustTotalAdvanceAmnt('+Number(rowCnt)+');RemoveTableRow(this,\'tblAuthoTypeAdvDtls\');"/>';
	document.getElementById("hidAuthoGridSize").value = Number(rowCnt)+1;
}

function validPensionCaseAutho()
{
	
	 var advDtlCntLength=document.getElementById("hidAuthoGridSize").value;
	 if(advDtlCntLength>=1)
	 {
		for(var rowAdvCnt=0;rowAdvCnt<Number(advDtlCntLength);rowAdvCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("cmbTypeOfOrder"+rowAdvCnt,"Please select Order Type",'6')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAuthoOrderNo"+rowAdvCnt,"Please enter Order No",'6')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAuthoOrderDate"+rowAdvCnt,"Please enter Order Date",'6')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAuthoPnsnrName"+rowAdvCnt,"Please enter Pensioner Name ",'6')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAuthoBasicAmt"+rowAdvCnt,"Please enter Basic amount",'6')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAuthoEFP"+rowAdvCnt,"Please enter Enhanced Family Pension Amount",'6')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAuthoFP"+rowAdvCnt,"Please enter Family Pension Amount",'6')==false)
				{
					return false;
				}							
				 
			}
			catch(ex)
			{
				
			}
		}
	 }	
	return true;
}

function validateAgDtls(){
	
	/*if(IsEmptyFun("txtAgDept","Please enter Department Name ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgDesignationName","Please enter Designation Name ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgPayScale","Please enter Payscale ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgPayBand","Please enter Payband ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgPayComm","Please enter Pay commission ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgCategory","Please enter Category/Series ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgGroup","Please enter Group Class ",'6')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtAgSalutation","Please enter Salutation ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgReligion","Please enter Religion ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgState","Please enter State ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgCity","Please enter City ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgRelation","Please enter Relation ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgTreasury","Please enter Treasury ",'6')==false)
	{
		return false;
	}
	else if(IsEmptyFun("txtAgTrnsType","Please Detail of Transaction Type ",'6')==false)
	{
		return false;
	}
	*/
	return true;
}
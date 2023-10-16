var hstDcrgDtlsPkArr = new Array();
var i = 0;
function setAmountAftrWithHeld()
{
	document.getElementById("txtAmntAfterWithHeld").value=Number(document.getElementById("txtTotalDcrgAmount").value)- Number(document.getElementById("txtWithheldAmnt").value);
	document.getElementById("txtDcrgPayableAmount").value=Number(document.getElementById("txtTotalDcrgAmount").value)- Number(document.getElementById("txtWithheldAmnt").value);
	if(document.getElementById('tblDCRGRecoveyDtls').rows.length == 1)
	{
		document.getElementById("txtTotRecoveryAmnt").value = Number("0.00");
	}
}
function validateDcrgHistDtls()
{
	if(IsEmptyFun("txtDcrgOrder",GPONO,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDcrgPaidDate",GPODATE,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtTotalDcrgAmount",TOTALGRTYAMT,'4')==false)
	{
		return false;
	}
//	if(IsEmptyFun("txtWithheldAmnt",WITHHELDAMT,'4')==false)
//	{
//		return false;
//	}
	var recCntLength=document.getElementById("hidRecGridSize").value;
	
	 if(document.getElementById('tblDCRGRecoveyDtls').rows.length > 1)
	 {
		for(var rowRecCnt=0;rowRecCnt<Number(recCntLength);rowRecCnt++)
		{		
			try
			{	
				if(IsEmptyFun("txtDcrgNature"+rowRecCnt,NATURE,'4')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtDcrgRecAmount"+rowRecCnt,AMOUNT,'4')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtDcrgSchemeCode"+rowRecCnt,SCHEMECODE,'4')==false)
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
function saveDcrgHistoryDtls(flag)
{
	var uri;
	var action;
	if(flag == 'H')
	{
		uri = 'ifms.htm?actionFlag=saveDcrgHistoryDtls&hstDcrgDtlsPkArr='+hstDcrgDtlsPkArr+'&flag='+flag;
		var myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function(myAjax) {saveDcrgHistoryDtlsStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...')} 
				});
	}
	if(flag == 'S')
	{
		if(document.getElementById("hdnPensionerCode").value != "")
		{
			if(validateDcrgHistDtls())
			{
					var txtDcrgNatureArr = document.getElementsByName("txtDcrgNature");
					var txtDcrgNature="";
					for(var i=0;i<txtDcrgNatureArr.length;i++)
					{
						txtDcrgNature = txtDcrgNature + txtDcrgNatureArr[i].value + "~";  
					}
					
					var txtDcrgRecAmountArr = document.getElementsByName("txtDcrgRecAmount");
					var txtDcrgRecAmount="";
					for(var i=0;i<txtDcrgRecAmountArr.length;i++)
					{
						txtDcrgRecAmount = txtDcrgRecAmount + txtDcrgRecAmountArr[i].value + "~";  
					}
					
					
					var txtDcrgSchemeCodeArr = document.getElementsByName("txtDcrgSchemeCode");
					var txtDcrgSchemeCode="";
					for(var i=0;i<txtDcrgSchemeCodeArr.length;i++)
					{
						txtDcrgSchemeCode = txtDcrgSchemeCode + txtDcrgSchemeCodeArr[i].value + "~";  
					}
					
					uri = 'ifms.htm?actionFlag=saveDcrgHistoryDtls&GPONo='+document.getElementById("txtDcrgOrder").value+'&GPODate='+document.getElementById("txtDcrgPaidDate").value+
								'&VoucherNo='+document.getElementById("txtDcrgVoucherNo").value+'&VoucherDate='+document.getElementById("txtDcrgVoucherDate").value+'&TotalGratuityAmnt='+document.getElementById("txtTotalDcrgAmount").value+
								'&WithHeldAmnt='+document.getElementById("txtWithheldAmnt").value+'&TotRecoveryAmnt='+document.getElementById("txtTotRecoveryAmnt").value+'&PayableAmount='+document.getElementById("txtDcrgPayableAmount").value+
								'&PayingAuthority='+document.getElementById("cmbPayingAuthority").value+'&PensionerCode='+document.getElementById("hdnPensionerCode").value+
								'&AmntAfterWithHeld='+document.getElementById("txtAmntAfterWithHeld").value+'&txtDcrgNature='+txtDcrgNature+
								'&txtDcrgRecAmount='+txtDcrgRecAmount+'&txtDcrgSchemeCode='+txtDcrgSchemeCode;
				
					var myAjax = new Ajax.Request(uri,
					{
				        method: 'post',
				        asynchronous: false,
				        parameters:uri,
				        onSuccess: function(myAjax) {saveDcrgHistoryDtlsStateChanged(myAjax);},
				        onFailure: function(){ alert('Something went wrong...')} 
					});
			}
	    }
		else
		{
			alert("Please save the case incase pension case is not saved.");
		}
	}
}
function saveDcrgHistoryDtlsStateChanged(myAjax) 
{ 
   var XMLDoc=myAjax.responseXML.documentElement;
   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
   var string = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	if(string == 'Add')
	{
		alert("DCRG History details has been saved successfully.");
		document.getElementById("txtDcrgOrder").value="";
		document.getElementById("txtDcrgPaidDate").value="";
		document.getElementById("txtDcrgVoucherNo").value="";
		document.getElementById("txtDcrgVoucherDate").value="";
		document.getElementById("txtTotalDcrgAmount").value="";
		document.getElementById("txtWithheldAmnt").value="";
		document.getElementById("txtAmntAfterWithHeld").value="";
		document.getElementById("txtTotRecoveryAmnt").value="";
		document.getElementById("txtDcrgPayableAmount").value="";
		document.getElementById("cmbPayingAuthority").value="-1";
		for(var i = document.getElementById("tblDCRGRecoveyDtls").rows.length; i > 1;i--)
	    {
			document.getElementById("tblDCRGRecoveyDtls").deleteRow(i -1);
	    } 
	}
	if(string == 'Update' || string == 'null')
	{
		alert("DCRG History details has been updated successfully.");
		winCls();
		//window.opener.location.reload();
		//hstDcrgDtlsPkArr.length = 0;
	}
}
function IsEmptyFun(varStr,alrtStr)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element.length == 0 || element == "-1" || element == "0.00")
	{
		alert(alrtStr);
		document.getElementById(varStr).focus();
		return false;
	}
	return true;
}

function dcrgHistoryTableAddRow()
{
	Row_ID_DcrgHis = document.getElementById("hidDcrgHistorySize").value;	
	var table=document.getElementById("tblDcrgHistory");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount);			  			 	
	
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
   	var Cell9 = newRow.insertCell(8);
	Cell9.className = "tds";
   	Cell9.align="center";
	var Cell10 = newRow.insertCell(9);
	Cell10.className = "tds";
   	Cell10.align="center";
	  	
   	Cell1.innerHTML = '<input type="hidden" name="hdnDcrgDtlsId" id="hdnDcrgDtlsId'+Row_ID_DcrgHis+'"/><input type="text" name="txtOrderNo" id="txtOrderNo'+Row_ID_DcrgHis+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);"/>';
	Cell2.innerHTML = '<input type="text" name="txtOrderDate" id="txtOrderDate'+Row_ID_DcrgHis+'" maxlength="10" size="8" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOrderDate'+Row_ID_DcrgHis+'\', 375, 570, \'\', \'\', '+Number(Row_ID_DcrgHis)+');" />';
	Cell3.innerHTML = '<input type="text" name="txtOrderAmt" id="txtOrderAmt'+Row_ID_DcrgHis+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
	Cell4.innerHTML = '<input type="text" name="txtAmount" id="txtAmount'+Row_ID_DcrgHis+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
	Cell5.innerHTML = '<input type="text" name="txtDcrgVoucherNo" id="txtDcrgVoucherNo'+Row_ID_DcrgHis+'" size="10" />';
   	Cell6.innerHTML = '<input type="text" name="txtDcrgVoucherDate" id="txtDcrgVoucherDate'+Row_ID_DcrgHis+'" maxlength="10" size="8" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtDcrgVoucherDate'+Row_ID_DcrgHis+'\', 375, 570, \'\', \'\', '+Number(Row_ID_DcrgHis)+');" />';
   	Cell7.innerHTML = '<input type="text" name="txtPaymentAuth" id="txtPaymentAuth'+Row_ID_DcrgHis+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)"/>';
   	Cell8.innerHTML = '<input type="text" name="txtWithHeldAmnt" id="txtWithHeldAmnt'+Row_ID_DcrgHis+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell9.innerHTML = '<input type="text" name="txtTotalRecoveryAmount" id="txtTotalRecoveryAmount'+Row_ID_DcrgHis+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell10.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblDcrgHistory\')" /> ';   	
   	document.getElementById("hidDcrgHistorySize").value = Number(Row_ID_DcrgHis)+1; 
}

function saveDcrgHistory()
{
	if(validateDcrgHistory())
	{		
		if(document.getElementById("tblDcrgHistory").rows.length>1)
		{
			var dcrgDtlsId=document.getElementsByName("hdnDcrgDtlsId");
			var dcrgOrderNo=document.getElementsByName("txtOrderNo");
			var dcrgOrderDate=document.getElementsByName("txtOrderDate");
			var dcrgOrderAmount=document.getElementsByName("txtOrderAmt");
			var dcrgAmount=document.getElementsByName("txtAmount");
			var dcrgVoucherNo=document.getElementsByName("txtDcrgVoucherNo");
			var dcrgVoucherDate=document.getElementsByName("txtDcrgVoucherDate");
			var dcrgPaymentAuth=document.getElementsByName("txtPaymentAuth");
			var innerHtmlStr="";
			var rowCount=dcrgOrderNo.length;
			
			for(var i=0;i<rowCount;i++)
			{
				innerHtmlStr=innerHtmlStr+"<input type='hidden' name='hdnDcrgDtlsId' id='hdnDcrgDtlsId'"+i+"' value='"+dcrgDtlsId[i].value+"' />"
					+"<input type='hidden' name='hdnDcrgOrderNo' id='hdnDcrgOrderNo'"+i+"' value='"+dcrgOrderNo[i].value+"' />"
					+"<input type='hidden' name='hdnDcrgOrderDate' id='hdnDcrgOrderDate'"+i+"' value='"+dcrgOrderDate[i].value+"'  />"
					+"<input type='hidden' name='hdnDcrgOrderAmount' id='hdnDcrgOrderAmount'"+i+"' value='"+dcrgOrderAmount[i].value+"' />"
					+"<input type='hidden' name='hdnDcrgAmount' id='hdnDcrgAmount'"+i+"' value='"+dcrgAmount[i].value+"' />"
					+"<input type='hidden' name='hdnDcrgVoucherNo' id='hdnDcrgVoucherNo'"+i+"' value='"+dcrgVoucherNo[i].value+"' />"
					+"<input type='hidden' name='hdnDcrgVoucherDate' id='hdnDcrgVoucherDate'"+i+"' value='"+dcrgVoucherDate[i].value+"' />"
					+"<input type='hidden' name='hdnDcrgPaymentAuth' id='hdnDcrgPaymentAuth'"+i+"' value='"+dcrgPaymentAuth[i].value+"'/>";
			}
			
			window.opener.document.getElementById('divDcrgHst').innerHTML=innerHtmlStr;
		}
		alert("Dcrg History Saved Successfully");
		window.close();     
	}
}

function validateDcrgHistory()
{
	var dcrgHistoryLength=document.getElementById("hidDcrgHistorySize").value;		
	if(document.getElementById('tblDcrgHistory').rows.length > 1)
	{				
		for(var rowCnt=0;rowCnt<Number(dcrgHistoryLength);rowCnt++)
		{
				try
				{						
					if(IsEmptyFun("txtOrderNo"+rowCnt,ORDERNO) == false)
					{								
						return false;
					}
					else if(IsEmptyFun("txtOrderDate"+rowCnt,ORDERDATE) == false)
					{						
						return false;
					}
					else if(IsEmptyFun("txtOrderAmt"+rowCnt,ORDERAMOUNT) == false)
					{						
						return false;
					}
					else if(IsEmptyFun("txtAmount"+rowCnt,AMOUNT) == false)
					{						
						return false;
					}
					else if(IsEmptyFun("txtDcrgVoucherNo"+rowCnt,VOUCHERNO) == false)
					{						
						return false;
					}
					else if(IsEmptyFun("txtDcrgVoucherDate"+rowCnt,VOUCHERDATE) == false)
					{						
						return false;
					}
					else if(IsEmptyFun("txtPaymentAuth"+rowCnt,PAYMENTAUTH) == false)
					{						
						return false;
					}
				}
				catch (ex) 
				{
					
				}
		}										
	}	
	return true;
}
function showDcrgHistory()
{
	if(document.getElementById("hdnPensionerCode").value != "")
	{
		var newWindow;
		var height = screen.height - 200;
		var width = screen.width - 200;
		var url='ifms.htm?actionFlag=loadDcrgHistory&PensionerCode='+document.getElementById("hdnPensionerCode").value;
		var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=50,left=100";
		newWindow = window.open(url, "DCRGHistory", urlstyle);
	}
	else
	{
		alert("Please save the case incase pension case is not saved.");
	}
}
function deleteHstDcrgDtlsPk(hstPkVal,count)
{
	if(document.getElementById('RecoveyDtls').rows.length > 1)
	{
		var tbl = document.getElementById("RecoveyDtls");
		var rowID = "tr"+count;
		var row = document.getElementById(rowID);     
		if(row != null)
		{
			row.parentNode.removeChild(row);
		}
		hstDcrgDtlsPkArr[i]=hstPkVal;	
		i = i + 1;
	}
	else
	{
		hstDcrgDtlsPkArr[i]=hstPkVal;	
		i = i + 1;
	}
}
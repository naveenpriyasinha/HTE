/* ===============================Dynamic Row For Change Subscription=================================== */
var serialNo_Sub;
var tbody;

function addRowSubscription()
{
	if(!chkSubDtls())
	{
		return false;
	}
	
	serialNo_Sub = document.getElementById('tblSubscriptionDtls').rows.length;
	
	if(serialNo_Sub >3){
		alert("Only three subscruption are allowed per financial year");
		return false;
	}
	
	var finYear;
	var month = document.getElementById('cmbMonth').options[document.getElementById("cmbMonth").selectedIndex].text;
	var year = document.getElementById('cmbYear').options[document.getElementById('cmbYear').selectedIndex].text;	
	
	tbody = document.getElementById('tblSubscriptionDtls').getElementsByTagName('tbody')[0]; 
	var row = document.createElement('TR'); 	

	var cell1=document.createElement('TD');	
	var cell2=document.createElement('TD');
	var cell3=document.createElement('TD');
	var cell4=document.createElement('TD');

	cell1.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtSubscriptionSrno\" value='"+serialNo_Sub+"' readonly=\"readonly\" />"+"<input type='hidden' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"hidSubDtlsPk\" readonly=\"readonly\" />";
	cell2.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtSubMonth\" value='"+month+"' readonly=\"readonly\" /><input type=\"hidden\" name=\"hidSubMonth\" value='"+document.getElementById('cmbMonth').value+"' />";
	cell3.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtSubYear\" value='" +year+"' readonly=\"readonly\"  /><input type=\"hidden\" name=\"hidSubYear\" value='"+document.getElementById('cmbYear').value+"'/>";
	cell4.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtSubscrAmount\" value='"+document.getElementById("txtSubAmount").value+"' readonly=\"readonly\"  />";	
	cell1.align="center";
	cell1.className = "tds";
	cell2.align="center";
	cell2.className = "tds";
	cell3.align="center";
	cell3.className = "tds";
	cell4.align="center";
	cell4.className = "tds";
		

	var cell5=document.createElement('TD');
	cell5.innerHTML="<img src=\"images/CalendarImages/DeleteIcon.gif\"  onclick=\"deleteRow_Sub()\" />";
	cell5.align="center";
	cell5.className = "tds";
	
		
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	
	tbody.appendChild(row); 	
	clearFieldSub();
	return true;
}

function chkSubDtls()
{
	if(!chkEmpty(document.getElementById("cmbMonth"),"Month")
			|| !chkEmpty(document.getElementById("cmbYear"),"Year")
			|| !chkEmpty(document.getElementById("txtSubAmount"),"Subscription Amount"))
	{
		return false;
	}
	return true;
}

function clearFieldSub()
{
	document.getElementById("cmbMonth").value = -1;
	document.getElementById("cmbYear").value = 24;
	document.getElementById("txtSubAmount").value = "";
}

function deleteRow_Sub() {  
	
	var current = window.event.srcElement;
	var TD = current.parentElement;
	var TR = TD.parentElement;
	var lArrAllTds = TR.childNodes;
	
	var currentShare = lArrAllTds[3].childNodes[0].value;
	
	while ( (current = current.parentElement)  && current.tagName !="TR");
      current.parentElement.removeChild(current);
		
}
/* ===============================Dynamic Row Change Subscription End=================================== */


/* ===============================Dynamic Row For RA History=================================== */

var serialNo_RA_His;
function addRowRAHistory()
{
	if(!chkRAHisDtls())
	{
		return false;
	}
	
	var purpose = document.getElementById('cmbPurposeCategoryHistory').options[document.getElementById("cmbPurposeCategoryHistory").selectedIndex].text;
	serialNo_RA_His = document.getElementById('tblRAHistoryDtls').rows.length;
	
	tbody = document.getElementById('tblRAHistoryDtls').getElementsByTagName('tbody')[0]; 
	var row = document.createElement('TR'); 		

	var cell1=document.createElement('TD');	
	var cell2=document.createElement('TD');
	var cell3=document.createElement('TD');
	var cell4=document.createElement('TD');
	var cell5=document.createElement('TD');
	var cell6=document.createElement('TD');
	var cell7=document.createElement('TD');
	var cell8=document.createElement('TD');
	var cell9=document.createElement('TD');
	var cell10=document.createElement('TD');	
	

	cell1.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtRAHistorySrnoRA\" value='"+serialNo_RA_His+"' readonly=\"readonly\" /><input type='hidden' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"hidRADtlsPk\" readonly=\"readonly\" />";
	cell2.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtPurposeOfAdvanceRA\" value='"+purpose+"' readonly=\"readonly\" /><input type=\"hidden\" name=\"hidPurposeOfAdvanceRA\" value='"+document.getElementById('cmbPurposeCategoryHistory').value+"' />";
	cell3.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtSancAmountRA\" value='"+document.getElementById("txtSancAmountHistory").value+"' readonly=\"readonly\"  />";
	cell4.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtSancDateRA\" value='"+document.getElementById("txtSancDateHistory").value+"' readonly=\"readonly\"  />";
	cell5.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtVoucherNoRA\" value='"+document.getElementById("txtVchrNoHisRA").value+"' readonly=\"readonly\"  />";
	cell6.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtVoucherDateRA\" value='"+document.getElementById("txtVchrDateHisRA").value+"' readonly=\"readonly\"  />";
	cell7.innerHTML = "<input type='text' style=\"border: none;\" size=\"7\" style=\"text-align: center\" name=\"txtNoOfInstlmntRA\" value='"+document.getElementById("txtNoOfInstlmntHis").value+"' readonly=\"readonly\"  />";
	cell8.innerHTML = "<input type='text' style=\"border: none;\" size=\"7\" style=\"text-align: center\" name=\"txtInstlmntAmtPmRA\" value='"+document.getElementById("txtInstlmntAmtPerMonHis").value+"' readonly=\"readonly\"  />";
	cell9.innerHTML = "<input type='text' style=\"border: none;\" size=\"7\" style=\"text-align: center\" name=\"txtFirstOddInstlmntRA\" value='"+document.getElementById("txtFirstOddInstlmntHis").value+"' readonly=\"readonly\"  />";
	cell10.innerHTML = "<input type='text' style=\"border: none;\" size=\"7\" style=\"text-align: center\" name=\"txtRecoveredInstlmntRA\" value='"+document.getElementById("txtRecoveredInstlmntHis").value+"' readonly=\"readonly\"  />";
	
	cell1.align="center";
	cell1.className = "tds";
	cell2.align="center";
	cell2.className = "tds";
	cell3.align="center";
	cell3.className = "tds";
	cell4.align="center";
	cell4.className = "tds";
	cell5.align="center";
	cell5.className = "tds";
	cell6.align="center";
	cell6.className = "tds";
	cell7.align="center";
	cell7.className = "tds";
	cell8.align="center";
	cell8.className = "tds";
	cell9.align="center";
	cell9.className = "tds";
	cell10.align="center";
	cell10.className = "tds";
		

	var cell11=document.createElement('TD');
	cell11.innerHTML="<img src=\"images/CalendarImages/DeleteIcon.gif\"  onclick=\"deleteRow_RA_His()\" />";
	cell11.align="center";
	cell11.className = "tds";
	
		
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	row.appendChild(cell6);
	row.appendChild(cell7);
	row.appendChild(cell8);
	row.appendChild(cell9);
	row.appendChild(cell10);
	row.appendChild(cell11);
	
	tbody.appendChild(row); 	
	clearFieldRAHis();
	return true;
}

function deleteRow_RA_His()
{  	
	var current = window.event.srcElement;
	var TD = current.parentElement;
	var TR = TD.parentElement;
	var lArrAllTds = TR.childNodes;
	
	var currentShare = lArrAllTds[3].childNodes[0].value;
	
	while ( (current = current.parentElement)  && current.tagName !="TR");
      current.parentElement.removeChild(current);
		
}

function clearFieldRAHis()
{
	document.getElementById("cmbPurposeCategoryHistory").value = -1;
	document.getElementById("txtSancAmountHistory").value = "";
	document.getElementById("txtSancDateHistory").value = "";
	document.getElementById("txtVchrNoHisRA").value = "";
	document.getElementById("txtVchrDateHisRA").value = "";
	document.getElementById("txtNoOfInstlmntHis").value = "";
	document.getElementById("txtInstlmntAmtPerMonHis").value = "";
	document.getElementById("txtFirstOddInstlmntHis").value = "";
	document.getElementById("txtRecoveredInstlmntHis").value = "";
}

function chkRAHisDtls()
{
	if(!chkEmpty(document.getElementById("cmbPurposeCategoryHistory"),"Purpose Of Advance")
			|| !chkEmpty(document.getElementById("txtSancAmountHistory"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtSancDateHistory"),"Sanctioned Date")
			|| !chkEmpty(document.getElementById("txtVchrNoHisRA"),"Voucher No")
			|| !chkEmpty(document.getElementById("txtVchrDateHisRA"),"Voucher Date")
			|| !chkEmpty(document.getElementById("txtNoOfInstlmntHis"),"No Of Installment")
			|| !chkEmpty(document.getElementById("txtInstlmntAmtPerMonHis"),"Installment Amount Per Month")
			|| !chkEmpty(document.getElementById("txtFirstOddInstlmntHis"),"First Odd Installment")
			|| !chkEmpty(document.getElementById("txtRecoveredInstlmntHis"),"Installment Recovered"))
	{
		return false;
	}
	return true;
}
/* ===============================Dynamic Row RA History End=================================== */


/* ===============================Get Installment Amount For RA and NRA=================================== */
function getInstlmntAmountHis()
{
	var sancAmount = document.getElementById("txtSancAmountHistory").value;
	var noOfInstallment = document.getElementById("txtNoOfInstlmntHis").value;
	if((Number(sancAmount)>0) && (Number(noOfInstallment)>0))
	{
		var inst = Math.floor(sancAmount/noOfInstallment);
		document.getElementById("txtInstlmntAmtPerMonHis").value = inst;
		var oddInst = sancAmount - (inst*(noOfInstallment-1));
		if(oddInst == inst)
			 document.getElementById("txtFirstOddInstlmntHis").value = 0;
		else
			 document.getElementById("txtFirstOddInstlmntHis").value = oddInst;
	}	
}

function getInstlmntAmountCur()
{
	var sancAmount = document.getElementById("txtSancAmountCur").value;
	var noOfInstallment = document.getElementById("txtNoOfInstlmntCur").value;
	if((Number(sancAmount)>0) && (Number(noOfInstallment)>0))
	{
		var inst = Math.floor(sancAmount/noOfInstallment);
		document.getElementById("txtInstlmntAmtPerMonCur").value = inst;
		var oddInst = sancAmount - (inst*(noOfInstallment-1));
		if(oddInst == inst)
			 document.getElementById("txtFirstOddInstlmntCur").value = 0;
		else
			 document.getElementById("txtFirstOddInstlmntCur").value = oddInst;
	}	
}

function getInstlmntAmountNRA()
{
	var sancAmount = document.getElementById("txtSancAmtNRA").value;
	var noOfInstallment = document.getElementById("txtNoOfInstlmntNRA").value;
	if((Number(sancAmount)>0) && (Number(noOfInstallment)>0))
	{
		var inst = Math.floor(sancAmount/noOfInstallment);
		document.getElementById("txtInstlmntAmtPerDisbrNRA").value = inst;
	}
}
/* ===============================Installment Amount For RA and NRA End=================================== */




/* ===============================Dynamic Row For NRA=================================== */


var serialNo_NRA;
function addRowNRA()
{
	if(!chkNRADtls())
	{
		return false;
	}
	
	var purpose = document.getElementById('cmbPurposeCategoryNRA').options[document.getElementById("cmbPurposeCategoryNRA").selectedIndex].text;
	serialNo_NRA = document.getElementById('tblNRADtls').rows.length;
	
	tbody = document.getElementById('tblNRADtls').getElementsByTagName('tbody')[0]; 
	var row = document.createElement('TR'); 		

	var cell1=document.createElement('TD');	
	var cell2=document.createElement('TD');
	var cell3=document.createElement('TD');
	var cell4=document.createElement('TD');
	var cell5=document.createElement('TD');
	var cell6=document.createElement('TD');
	var cell7=document.createElement('TD');
	var cell8=document.createElement('TD');	

	cell1.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtNRASrno\" value='"+serialNo_NRA+"' readonly=\"readonly\" /><input type='hidden' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"hidNRADtlsPk\" readonly=\"readonly\" />";
	cell2.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtPurposeOfAdvanceNRA\" value='"+purpose+"' readonly=\"readonly\" /><input type=\"hidden\" name=\"hidPurposeOfAdvanceNRA\" value='"+document.getElementById('cmbPurposeCategoryNRA').value+"' />";
	cell3.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtSancAmountNRA\" value='"+document.getElementById("txtSancAmtNRA").value+"' readonly=\"readonly\"  />";
	cell4.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtSanctnDateNRA\" value='"+document.getElementById("txtSancDateNRA").value+"' readonly=\"readonly\"  />";
	cell5.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtVoucherNoNRA\" value='"+document.getElementById("txtVchrNoNRA").value+"' readonly=\"readonly\"  />";
	cell6.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtVoucherDateNRA\" value='"+document.getElementById("txtVchrDateNRA").value+"' readonly=\"readonly\"  />";
	cell7.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtNoOfInstlmntsNRA\" value='"+document.getElementById("txtNoOfInstlmntNRA").value+"' readonly=\"readonly\"  />";
	cell8.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtInstlmntAmtPerDisbrmnt\" value='"+document.getElementById("txtInstlmntAmtPerDisbrNRA").value+"' readonly=\"readonly\"  />";
	
	cell1.align="center";
	cell1.className = "tds";
	cell2.align="center";
	cell2.className = "tds";
	cell3.align="center";
	cell3.className = "tds";
	cell4.align="center";
	cell4.className = "tds";
	cell5.align="center";
	cell5.className = "tds";
	cell6.align="center";
	cell6.className = "tds";
	cell7.align="center";
	cell7.className = "tds";
	cell8.align="center";
	cell8.className = "tds";
		

	var cell9=document.createElement('TD');
	cell9.innerHTML="<img src=\"images/CalendarImages/DeleteIcon.gif\"  onclick=\"deleteRow_NRA()\" />";
	cell9.align="center";
	cell9.className = "tds";
	
		
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	row.appendChild(cell6);
	row.appendChild(cell7);
	row.appendChild(cell8);
	row.appendChild(cell9);
	
	tbody.appendChild(row); 	
	clearFieldNRA();
	return true;
}

function deleteRow_NRA()
{  	
	var current = window.event.srcElement;
	var TD = current.parentElement;
	var TR = TD.parentElement;
	var lArrAllTds = TR.childNodes;
	
	var currentShare = lArrAllTds[3].childNodes[0].value;
	
	while ( (current = current.parentElement)  && current.tagName !="TR");
      current.parentElement.removeChild(current);
		
}

function clearFieldNRA()
{
	document.getElementById("cmbPurposeCategoryNRA").value = -1;
	document.getElementById("txtSancAmtNRA").value = "";
	document.getElementById("txtSancDateNRA").value = "";
	document.getElementById("txtVchrNoNRA").value = "";
	document.getElementById("txtVchrDateNRA").value = "";
	document.getElementById("txtNoOfInstlmntNRA").value = "";
	document.getElementById("txtInstlmntAmtPerDisbrNRA").value = "";	
}

function chkNRADtls()
{
	if(!chkEmpty(document.getElementById("cmbPurposeCategoryNRA"),"Purpose Of Advance")
			|| !chkEmpty(document.getElementById("txtSancAmtNRA"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtSancDateNRA"),"Sanctioned Date")
			|| !chkEmpty(document.getElementById("txtVchrNoNRA"),"Voucher No")
			|| !chkEmpty(document.getElementById("txtVchrDateNRA"),"Voucher Date")
			|| !chkEmpty(document.getElementById("txtNoOfInstlmntNRA"),"No Of Installment")
			|| !chkEmpty(document.getElementById("txtInstlmntAmtPerDisbrNRA"),"Installment Amount Per Disburment"))
	{
		return false;
	}
	return true;
}
/* ===============================Dynamic Row NRA End=================================== */



/* ===============================Dynamic Row RA Current=================================== */
var Row_ID_RA_CUR;

try{
if((document.getElementById("hidScheduleSize").value) > 0){
	Row_ID_RA_CUR = document.getElementById("hidScheduleSize").value;
}else{
	Row_ID_RA_CUR =0;
}
}catch(e){Row_ID_RA_CUR =0;}

function addRowRACurrent()
{	
	var table=document.getElementById("tblRACurDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount);
	var sr_no = Number(Row_ID_RA_CUR) + 1;
	//Row_ID_RA_CUR = rowCount -1;
	
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
	  	
   	
	Cell1.innerHTML = '<input type="label" id="lblSrNoRaCur'+Row_ID_RA_CUR+'" name="lblSrNoRaCur" value="'+sr_no+'" style="text-align: center" size="3" readonly/><input type="hidden" style="border: none;" size="5" style="text-align: center" name="hidSchdlDtlsPk" readonly="readonly" />';
	Cell2.innerHTML = '<input type="radio" name="radioVoucherChallan'+Row_ID_RA_CUR+'" id="radioVoucher'+Row_ID_RA_CUR+'" value="V" onclick="whichRow(this,'+Row_ID_RA_CUR+');" size="7"/>Voucher  <input type="radio" name="radioVoucherChallan'+Row_ID_RA_CUR+'" id="radioChallan'+Row_ID_RA_CUR+'" value="C" onclick="whichRow(this,'+Row_ID_RA_CUR+');" size="7" />Challan';
	Cell3.innerHTML = '';
	Cell4.innerHTML = '<select name="cmbMonthCur" id="cmbMonthCur'+Row_ID_RA_CUR+'" style="width: 100px"> '+LISTMONTHS+' </select>';
   	Cell5.innerHTML = '<select name="cmbYearCur" id="cmbYearCur'+Row_ID_RA_CUR+'" style="width: 65px"> '+LISTYEARS+' </select>';
	Cell6.innerHTML = '<input type="text" name="txtInstAmountRACur" id="txtInstAmountRACur'+Row_ID_RA_CUR+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell7.innerHTML = '<input type="text" name="txtVchrChallnNoRACur" id="txtVchrChallnNoRACur'+Row_ID_RA_CUR+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);"/>';
   	Cell8.innerHTML = '<input type="text" name="txtVchrChallnDateRACur" id="txtVchrChallnDateRACur'+Row_ID_RA_CUR+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img id="imgVoucher'+Row_ID_RA_CUR+'" src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtVchrChallnDateRACur'+Row_ID_RA_CUR+'\', 375, 570, \'\', \'\', '+Number(Row_ID_RA_CUR)+');" />';   	
   	Cell9.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblRACurDtls\')" /> ';   	 
   	
   	Row_ID_RA_CUR++;
}

function showRowCell(element)
{
	var cell, row;    
 
    if (element.parentNode) 
    {
      do
      	cell = element.parentNode;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentNode;
    }
    else if (element.parentElement) 
    {
      do
      	cell= element.parentElement;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentElement;
    }
    
    return row.rowIndex;
}


function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
}

function removeAllRow()
{		
	var table = document.getElementById("tblRACurDtls");	
	try{
		for(var i=table.rows.length-1;i>0;i--)
		{						
			table.deleteRow(i);
		}
	}catch(ex)
	{
		
	}
	Row_ID_RA_CUR = 0;
}

function addRowRecInstlmnt()
{
	var row = document.getElementById("txtRecoveredInstlmntCur").value;
	if(row > 0)
	{
		//removeAllRow();
		for(var i=0;i<Number(row);i++)
		{
			addRowRACurrent();
		}
	}
}
/* ===============================Dynamic Row RA Current END=================================== */





function validateSubDate()
{
	var mon = document.getElementById('cmbMonth').value;
	var year = document.getElementById('cmbYear').value;	
	if(Number(mon)>3 && Number(year)==25)
	{
		alert("Date Should be within current Financial year");
		document.getElementById('cmbMonth').value = -1;
	}
	else if(Number(mon)<4 && Number(year)==24)
	{
		alert("Date Should be within current Financial year");
		document.getElementById('cmbMonth').value = -1;
	}
	
	
}

function whichRow(obj,Row_No){ 
	var par=obj.parentNode; 
	
	while(par.nodeName.toLowerCase()!='tr'){ 
		par=par.parentNode; 
	} 
	enableDisableVchrChallan(Row_No,par.rowIndex);
} 

function enableDisableVchrChallan(Row_No,Row_Cnt)
{	
	if(document.getElementById("radioVoucher"+Row_No).checked)
	{				
		document.getElementById("cmbMonthCur"+Row_No).disabled = false;
		document.getElementById("cmbYearCur"+Row_No).disabled = false;
		document.getElementById("tblRACurDtls").rows[Row_Cnt].cells[2].innerHTML = '<input type="text" id="txtInstlmntVchr'+Row_No+'" name="txtInstlmntVchr" size=5 onkeypress="digitFormat(this);" onblur="validateInstallment('+Row_No+','+Row_Cnt+')">';
	}
	else if(document.getElementById("radioChallan"+Row_No).checked)
	{				
		document.getElementById("cmbMonthCur"+Row_No).disabled = true;
		document.getElementById("cmbYearCur"+Row_No).disabled = true;
		document.getElementById("tblRACurDtls").rows[Row_Cnt].cells[2].innerHTML = 'From:<input type="text" id="txtInstlmntChallanFrom'+Row_No+'" name="txtInstlmntChallanFrom'+Row_No+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment('+Row_No+','+Row_Cnt+')"/>To:<input type="text" id="txtInstlmntChallanTo'+Row_No+'" name="txtInstlmntChallanTo'+Row_No+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment('+Row_No+','+Row_Cnt+')"/>';		
	}
}

function validateInstallment(row,Row_Cnt)
{
	var type;
	var from;
	var to;
	var instlmnt;
	var from_prev;
	var to_prev;
	var instlmnt_prev;
	
	var prev_row = document.getElementById("tblRACurDtls").rows[Row_Cnt-1].cells[0].childNodes[0].value;
	prev_row = Number(prev_row) - 1;
	if(document.getElementById("radioVoucher"+row).checked){
		type = "Voucher";
	}else{
		type = "Challan";
	}
	
	
	if(type=="Voucher")
	{
		instlmnt = document.getElementById("txtInstlmntVchr"+row).value;
		if(instlmnt > 0){
			if(row == 0){
				return;
			}
			else{
				if(document.getElementById("radioVoucher"+prev_row).checked){				
					instlmnt_prev = document.getElementById("txtInstlmntVchr"+prev_row).value;
					
					if(Number(instlmnt_prev) > 0)
					{
						if(Number(instlmnt_prev)>=Number(instlmnt)){
							alert("Installment No. are not in sequence");
							document.getElementById("txtInstlmntVchr"+row).value= "";
							document.getElementById("txtInstlmntVchr"+row).focus();
							return;
						}
					}
					else
					{
						alert("Previous Installment No. can not be blank");						
						document.getElementById("txtInstlmntVchr"+prev_row).focus();
						return;
					}
				}
				else{
					to_prev = document.getElementById("txtInstlmntChallanTo"+prev_row).value;
					
					if(Number(to_prev) > 0)
					{
						if(Number(to_prev)>=Number(instlmnt)){
							alert("Installment No. are not in sequence");
							document.getElementById("txtInstlmntVchr"+row).value= "";
							document.getElementById("txtInstlmntVchr"+row).focus();
							return;
						}
					}
					else
					{
						alert("Previous Installment No. can not be blank");						
						document.getElementById("txtInstlmntChallanTo"+prev_row).focus();
						return;
					}
				}
			}
		}
	}
	else if(type=="Challan")
	{
		from = document.getElementById("txtInstlmntChallanFrom"+row).value;
		to = document.getElementById("txtInstlmntChallanTo"+row).value;
		if(from > 0){
			if(Number(to)>0){
				if(Number(from) > Number(to))
				{
					alert("Installment From can not be greater than Installment To");
					document.getElementById("txtInstlmntChallanTo"+row).value = "";
					document.getElementById("txtInstlmntChallanTo"+row).focus();
					return;
				}
			}	
			if(Number(row)>0){
				if(document.getElementById("radioVoucher"+prev_row).checked){
					instlmnt_prev = document.getElementById("txtInstlmntVchr"+prev_row).value;
					
					if(Number(instlmnt_prev) > 0)
					{
						if(Number(instlmnt_prev)>=Number(from)){
							alert("Installment No. are not in sequence");
							document.getElementById("txtInstlmntChallanFrom"+row).value= "";
							document.getElementById("txtInstlmntChallanTo"+row).value= "";
							document.getElementById("txtInstlmntChallanFrom"+row).focus();
							return;
						}
					}
					else
					{
						alert("Previous Installment No. can not be blank");						
						document.getElementById("txtInstlmntVchr"+prev_row).focus();
						return;
					}
				}
				else{
					to_prev = document.getElementById("txtInstlmntChallanTo"+prev_row).value;
					
					if(Number(to_prev) > 0)
					{
						if(Number(to_prev)>=Number(from)){
							alert("Installment No. are not in sequence");
							document.getElementById("txtInstlmntChallanFrom"+row).value= "";
							document.getElementById("txtInstlmntChallanTo"+row).value= "";
							document.getElementById("txtInstlmntChallanFrom"+row).focus();
							return;
						}
					}
					else
					{
						alert("Previous Installment No. can not be blank");						
						document.getElementById("txtInstlmntChallanTo"+prev_row).focus();
						return;
					}
				}
			}
		}
	}
}


function saveData()
{
	if((!validateSaveData()) || (!validateChangeSubscription())){
		return false;
	}
	
	var recoveredInstlmnt = maxInstallmentNo();
	var outstandingAmount = calculateOutstandingAmount();
	
	var uri = 'ifms.htm?actionFlag=saveGPFDataEntryForm&userType=DEO&recoveredInstlmnt='+recoveredInstlmnt+'&scheduleSize='+Row_ID_RA_CUR+'&outstndAmount='+outstandingAmount;
	var url = runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	getSaveMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
	return true;
}

function forwardData()
{		
	if((!validateData()) || (!validateChangeSubscription())){
		return false;
	}	
	
	var recoveredInstlmnt = maxInstallmentNo();
	var outstandingAmount = calculateOutstandingAmount();
	
	var uri = 'ifms.htm?actionFlag=forwardGPFDataEntryForm&userType=DEO&recoveredInstlmnt='+recoveredInstlmnt+'&scheduleSize='+Row_ID_RA_CUR+'&outstndAmount='+outstandingAmount;
	var url = runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	getForwardMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
	return true;
}

function validateData()
{
	if(!chkEmpty(document.getElementById("txtEmployeeName"),"Employee Name")
			|| !chkEmpty(document.getElementById("txtAmount"),"Opening Balance")
			|| !chkEmpty(document.getElementById("txtPrevSubAmt"),"Opening Year Subscription"))
	{
		return false;
	}
	
	if(document.getElementById('tblRACurDtls').rows.length > 1)
	{		
		for(var rowCnt=0;rowCnt<Number(Row_ID_RA_CUR);rowCnt++)
		{				
				try
				{	
					if(!(document.getElementById('radioVoucher'+rowCnt).checked))
					{
						if(!(document.getElementById('radioChallan'+rowCnt).checked))
						{
							alert("Please select voucher or Challan");
							document.getElementById('radioVoucher'+rowCnt).focus();
							return false;
						}						
					}
					
					if(document.getElementById("txtInstAmountRACur"+rowCnt).value == "")
					{
						alert("Please Enter Installment Amount");
						document.getElementById("txtInstAmountRACur"+rowCnt).focus();
						return false;
					}
					
					else if(document.getElementById('radioVoucher'+rowCnt).checked)
					{
						if(document.getElementById("txtVchrChallnNoRACur"+rowCnt).value == "")
						{
							alert("Please Enter Voucher No");
							document.getElementById("txtVchrChallnNoRACur"+rowCnt).focus();
							return false;
						}
						else if(document.getElementById("txtVchrChallnDateRACur"+rowCnt).value == "")
						{
							alert("Please Enter Voucher Date");
							document.getElementById("txtVchrChallnDateRACur"+rowCnt).focus();
							return false;
						}
						else if(document.getElementById("txtInstlmntVchr"+rowCnt).value == "")
						{
							alert("Please Enter Installment No");
							document.getElementById("txtInstlmntVchr"+rowCnt).focus();
							return false;
						}
					}
					else if(document.getElementById('radioChallan'+rowCnt).checked)
					{
						if(document.getElementById("txtVchrChallnNoRACur"+rowCnt).value == "")
						{
							alert("Please Enter Challan No");
							document.getElementById("txtVchrChallnNoRACur"+rowCnt).focus();
							return false;
						}
						else if(document.getElementById("txtVchrChallnDateRACur"+rowCnt).value == "")
						{
							alert("Please Enter Challan Date");
							document.getElementById("txtVchrChallnDateRACur"+rowCnt).focus();
							return false;
						}
						else if(document.getElementById("txtInstlmntChallanFrom"+rowCnt).value == "")
						{
							alert("Please Enter Installment No");
							document.getElementById("txtInstlmntChallanFrom"+rowCnt).focus();
							return false;
						}
						else if(document.getElementById("txtInstlmntChallanTo"+rowCnt).value == "")
						{
							alert("Please Enter Installment No");
							document.getElementById("txtInstlmntChallanTo"+rowCnt).focus();
							return false;
						}						
						
					}
																													
	
				}
				catch(e)
				{
					
				}
		}
	}
	
	if(!(document.getElementById("cmbPurposeCategoryCur").value == "-1"))
	{
		if(!chkEmpty(document.getElementById("txtSancAmountCur"),"Sanction Amount")
				|| !chkEmpty(document.getElementById("txtSancDateCur"),"Sanction Date")
				|| !chkEmpty(document.getElementById("txtVchrNoCurRA"),"Voucher No")
				|| !chkEmpty(document.getElementById("txtVchrDateCurRA"),"Voucher Date")
				|| !chkEmpty(document.getElementById("txtNoOfInstlmntCur"),"No of Installment")
				|| !chkEmpty(document.getElementById("txtInstlmntAmtPerMonCur"),"Installment Amount Per Month")
				|| !chkEmpty(document.getElementById("txtFirstOddInstlmntCur"),"First Odd Installment"))
		{
			return false;
		}
	}
	
	return true;
}

function validateSaveData()
{
	if(!chkEmpty(document.getElementById("txtEmployeeName"),"Employee Name")
			|| !chkEmpty(document.getElementById("txtAmount"),"Opening Balance")
			|| !chkEmpty(document.getElementById("txtPrevSubAmt"),"Opening Year Subscription"))
	{
		return false;
	}
	return true;
}

function getSaveMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblSaveFlag == "true") {
		alert('Data Saved Successfully');		
		self.location.href = 'ifms.htm?actionFlag=loadDataEntryDraftRequest';
	}
	else if(lblSaveFlag == "Exist"){
		alert("Data Already Exist For The Employee");
		self.location.href = 'ifms.htm?actionFlag=loadDataEntryDraftRequest';
	}
	else{
		alert("Data Not Saved");
	}
}

function getForwardMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblSaveFlag == "true") {
		alert('Data Forwarded Successfully');		
		self.location.href = 'ifms.htm?actionFlag=loadDataEntryForm&userType=DEO';
	}
	else if(lblSaveFlag == "Exist"){
		alert("Data Already Exist For The Employee");		
	}
	else{
		alert("Data Not Forwarded");
	}
}

function approveRequest()
{
	var uri = 'ifms.htm?actionFlag=approveGPFDataEntryForm';
	var url = runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	getApproveMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getApproveMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblSaveFlag == "true") {
		alert('Request Approved Successfully');
		self.location.href = "ifms.htm?actionFlag=loadDataEntryForm&userType=DDO";
	}
	else{
		alert("Request Not Approved");
	}
}


function validateCurAdvance()
{
	if(document.getElementById("cmbPurposeCategoryCur").value == "-1")
	{
		document.getElementById("txtSancAmountCur").value="";
		document.getElementById("txtSancDateCur").value="";
		document.getElementById("txtVchrNoCurRA").value="";
		document.getElementById("txtVchrDateCurRA").value="";
		document.getElementById("txtNoOfInstlmntCur").value="";
		document.getElementById("txtNoOfInstlmntCur").value="";
		document.getElementById("txtInstlmntAmtPerMonCur").value="";
		document.getElementById("txtFirstOddInstlmntCur").value="";
	}
}

function validateTotalNoOfInstallmentCur()
{
	var noOfInstallment = document.getElementById("txtNoOfInstlmntCur").value;
	if(noOfInstallment > 0){	
		if(Number(noOfInstallment)<12 || Number(noOfInstallment) >36){
			alert("No of installment should be between 12 and 36");
			document.getElementById("txtNoOfInstlmntCur").value='';
			document.getElementById("txtNoOfInstlmntCur").focus();
			return false;
		}
		else{
			getInstlmntAmountCur();
		}
	}
}

function validateInstNoNRA()
{
	var inst=document.getElementById("txtNoOfInstlmntNRA").value;
	if(inst > 0){
		if(inst<2 || inst>4){
			alert("Sanctioned No Of Installments should be between 2 and 4");
			document.getElementById("txtNoOfInstlmntNRA").value="";
			document.getElementById("txtNoOfInstlmntNRA").focus();
		}else{
			getInstlmntAmountNRA();
		}
	}
}

function validateTotalNoOfInstallmentHis()
{
	var noOfInstallment = document.getElementById("txtNoOfInstlmntHis").value;
	if(noOfInstallment >0){
		if(Number(noOfInstallment)<12 || Number(noOfInstallment) >36){
			alert("No of installment should be between 12 and 36");
			document.getElementById("txtNoOfInstlmntHis").value='';
			document.getElementById("txtNoOfInstlmntHis").focus();
			return false;
		}
		else{
			getInstlmntAmountHis();
		}
	}
}

function validateChangeSubscription()
{
	var openingBalc = document.getElementById("txtPrevSubAmt").value;
	var amount = document.getElementsByName("txtSubscrAmount");	
	var incCount = 0;
	var decCount = 0;
	
	for(var i=0;i<amount.length;i++)
	{
		if(i==0){
			if(amount[0].value > Number(openingBalc)){
				incCount = incCount + 1;
			}else{
				decCount = decCount + 1;
			}
		}else{		
			if(amount[i].value > amount[i-1].value){
				incCount = incCount + 1;
			}else{
				decCount = decCount + 1;
			}
		}
	}
	
	if(incCount > 2){
		alert("Employee can not increment the subscription more than two times in a financial year");
		return false;
	}
	else if(decCount > 1){
		alert("Employee can not decrement the subscription more than one times in a financial year");
		return false;
	}
	
	return true;
}


function maxInstallmentNo()
{
	var maxChalln =0;
	var maxVoucher =0;
	var maxInstlmnt;	
		
	for(var i=0;i<Row_ID_RA_CUR;i++)
	{	
		try{			
			if(document.getElementById("radioVoucher"+i).checked){				
				if(maxVoucher < Number(document.getElementById("txtInstlmntVchr"+i).value)){
					maxVoucher = Number(document.getElementById("txtInstlmntVchr"+i).value);
				}
			}else{				
				if(maxChalln < Number(document.getElementById("txtInstlmntChallanTo"+i).value)){
					maxChalln = Number(document.getElementById("txtInstlmntChallanTo"+i).value);
				}
			}
		}catch(e){
			
		}
	}
	
	if(maxChalln > maxVoucher)
		maxInstlmnt = maxChalln;
	else
		maxInstlmnt = maxVoucher;
	
	return maxInstlmnt;
}

function calculateOutstandingAmount()
{
	var sum=0;
	var sancAmount = document.getElementById("txtSancAmountCur").value;
	var outstandingAmount = "";
	
	if(sancAmount > 0)
	{
		for(var i=0;i<Row_ID_RA_CUR;i++)
		{	
			try{			
				sum += Number(document.getElementById("txtInstAmountRACur"+i).value); 			
			}catch(e){
				
			}
		}
		
		outstandingAmount = Number(sancAmount) - Number(sum);
	}
	
	return outstandingAmount;
}

function chkSanctionDate(obj)
{
	var Date1 = obj.value;
	la_Date1 = Date1.split("/");
    var day=parseFloat(la_Date1[0]);
    var month=parseFloat(la_Date1[1]);
    month = Number(month)-1;
    var year=parseFloat(la_Date1[2]);	    
	
	var startDate = new Date();
	startDate.setFullYear(2011, 03, 01);
	var sancDate = new Date();
	sancDate.setFullYear(year, month, day);
	
	if(sancDate >= startDate){
		alert("Sanction Date should be less than 01/04/2011");
		obj.value = "";
		obj.focus();
		return false;
	}
	
	return true;
}
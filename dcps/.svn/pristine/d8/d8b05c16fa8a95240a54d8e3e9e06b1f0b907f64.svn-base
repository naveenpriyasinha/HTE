var rowId;
var TotalRecordCA = document.getElementById("hidTotalRecordCA").value;
if(TotalRecordCA !=""){
	rowId = Number(TotalRecordCA)+1;
}else{
	rowId = 1;
}
function addRowForRecoveredIns()
{
	var row = document.getElementById("txtRecoverInsCA").value;
	if(row > 0)
	{
		for(var i=0;i<Number(row);i++)
		{
			addRowCA();
		}
	}
}
function addRowCA()
{	
	var table=document.getElementById("tblComputerAdvance");
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
   	var Cell11 = newRow.insertCell(10);
   	Cell11.className = "tds";
   	Cell11.align="center";
	var Cell12 = newRow.insertCell(11);
   	Cell12.className = "tds";
   	Cell12.align="center";	  	
   	
	Cell1.innerHTML = Number(rowId);
	Cell2.innerHTML = '<input type="radio" name="radioVoucherChallan'+rowId+'" id="radioVoucher'+rowId+'" value="V" onclick="whichRow(this,1,'+rowId+');" size="7"/>Voucher  <input type="radio" name="radioVoucherChallan'+rowId+'" id="radioChallan'+rowId+'" value="C" onclick="whichRow(this,2,'+rowId+');" size="7" />Challan';
	Cell3.innerHTML = '';
	Cell4.innerHTML = '<select name="cmbMonthCA" id="cmbMonthCA'+rowId+'"> '+LISTMONTHS+' </select>';
   	Cell5.innerHTML = '<select name="cmbYearCA" id="cmbYearCA'+rowId+'"> '+LISTYEARS+' </select>';
	Cell6.innerHTML = '<input type="text" name="txtInstAmountCA" id="txtInstAmountCA'+rowId+'" size="10" onkeypress="digitFormat(this);"  style="text-align: right"/>';
	Cell7.innerHTML = '<input type="text" name="txtInterestInstAmount" id="txtInterestInstAmount'+rowId+'" size="8" readonly="readonly" onkeypress="amountFormat(this);"  maxlength="4" style="text-align: right"/>';
	Cell8.innerHTML = '<input type="text" name="txtAmtToBeRecoveredCA" id="txtAmtToBeRecoveredCA'+rowId+'" size="10" onkeypress="digitFormat(this);"  style="text-align: right"/>';   	
   	Cell9.innerHTML = '<input type="text" name="txtTreasuryNameCA" id="txtTreasuryNameCA'+rowId+'" size="30"/>';
   	Cell10.innerHTML = '<input type="text" name="txtVoucherNoCA" id="txtVoucherNoCA'+rowId+'" size="15"/>';
   	Cell11.innerHTML = '<input type="text" name="txtVoucherDateCA" id="txtVoucherDateCA'+rowId+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/> <img id="imgVoucher'+rowId+'" src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtVoucherDateCA'+rowId+'\', 375, 570);" />';
   	Cell12.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblComputerAdvance\')" /> ';
   	
   	rowId++;
}

var rowIdHBA;
var TotalRecordHBA = document.getElementById("hidTotalRecordHBA").value;
if(TotalRecordHBA !=""){
	rowIdHBA = Number(TotalRecordHBA)+1;
}else{
	rowIdHBA = 1;
}
function addRowForPrincipalRecoveredInsHBA()
{
	var row = document.getElementById("txtRecoverPrincInsHBA").value;
	if(row > 0)
	{
		for(var i=0;i<Number(row);i++)
		{
			addRowHBA(1);
		}
	}
}
function addRowForInterestRecoveredInsHBA()
{
	var row = document.getElementById("txtRecoverInterestInsHBA").value; 
	if(row > 0)
	{
		for(var i=0;i<Number(row);i++)
		{
			addRowHBA(2);
		}
	}
}
function addRowHBA(flag)
{	
	var table=document.getElementById("tblHouseAdvance");
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
   	var Cell11 = newRow.insertCell(10);
   	Cell11.className = "tds";
   	Cell11.align="center";
	var Cell12 = newRow.insertCell(11);
   	Cell12.className = "tds";
   	Cell12.align="center";	  	
   	
	Cell1.innerHTML = Number(rowIdHBA);
	Cell2.innerHTML = '<input type="radio" name="radioVoucherChallanHBA'+rowIdHBA+'" id="radioVoucherHBA'+rowIdHBA+'" value="V" onclick="whichRowHBA(this,1,'+rowIdHBA+');" size="5"/>Voucher  <input type="radio" name="radioVoucherChallanHBA'+rowIdHBA+'" id="radioChallanHBA'+rowIdHBA+'" value="C" onclick="whichRowHBA(this,2,'+rowIdHBA+');" size="5" />Challan';
	Cell3.innerHTML = '';
	Cell4.innerHTML = '<select name="cmbMonthHBA" id="cmbMonthHBA'+rowIdHBA+'"> '+LISTMONTHS+' </select>';
   	Cell5.innerHTML = '<select name="cmbYearHBA" id="cmbYearHBA'+rowIdHBA+'"> '+LISTYEARS+' </select>';
   	if(flag==1){
   		Cell6.innerHTML = '<input type="text" name="txtPrinInstAmountHBA" id="txtPrinInstAmountHBA'+rowIdHBA+'" size="10" onkeypress="digitFormat(this);"  style="text-align: right"/>';
   		Cell7.innerHTML = '<input type="text" name="txtInterestInstAmountHBA" id="txtInterestInstAmountHBA'+rowIdHBA+'" size="10" onkeypress="amountFormat(this);" readonly="readonly" maxlength="4" style="text-align: right"/>';
   	}else if(flag==2){
   		Cell6.innerHTML = '<input type="text" name="txtPrinInstAmountHBA" id="txtPrinInstAmountHBA'+rowIdHBA+'" size="10" onkeypress="digitFormat(this);" readonly="readonly" style="text-align: right"/>';
   		Cell7.innerHTML = '<input type="text" name="txtInterestInstAmountHBA" id="txtInterestInstAmountHBA'+rowIdHBA+'" size="10" onkeypress="amountFormat(this);" maxlength="4" style="text-align: right"/>';
   	}	
	Cell8.innerHTML = '<input type="text" name="txtAmtToBeRecoveredHBA" id="txtAmtToBeRecoveredHBA'+rowIdHBA+'" size="10" onkeypress="digitFormat(this);"  style="text-align: right"/>';   	
   	Cell9.innerHTML = '<input type="text" name="txtTreasuryNameHBA" id="txtTreasuryNameHBA'+rowIdHBA+'" size="30"/>';
   	Cell10.innerHTML = '<input type="text" name="txtVoucherNoHBA" id="txtVoucherNoHBA'+rowIdHBA+'" size="15"/>';
   	Cell11.innerHTML = '<input type="text" name="txtVoucherDateHBA" id="txtVoucherDateHBA'+rowIdHBA+'" maxlength="10" size="9" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/> <img id="imgVoucher'+rowIdHBA+'" src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtVoucherDateHBA'+rowIdHBA+'\', 375, 570);" />';
   	Cell12.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblHouseAdvance\')" /> ';
   	
   	rowIdHBA++;
}
var rowIdMCA;
var TotalRecordMCA = document.getElementById("hidTotalRecordMCA").value;
if(TotalRecordMCA !=""){
	rowIdMCA = Number(TotalRecordMCA)+1;
}else{
	rowIdMCA = 1;
}
function addRowForPrincipalRecoveredInsMCA()
{
	
	var row = document.getElementById("txtRecoverPrincInsMCA").value;	
	if(row > 0)
	{
		for(var i=0;i<Number(row);i++)
		{
			addRowMCA(1);
		}
	}
}
function addRowForInterestRecoveredInsMCA()
{
	var row = document.getElementById("txtRecoverInterestInsMCA").value;
	if(row > 0)
	{
		for(var i=0;i<Number(row);i++)
		{
			addRowMCA(2);
		}
	}
}
function addRowMCA(flag)
{	
	var table=document.getElementById("tblMotorAdvance");
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
   	Cell9.align="center";
   	var Cell11 = newRow.insertCell(10);
   	Cell11.className = "tds";
   	Cell11.align="center";
	var Cell12 = newRow.insertCell(11);
   	Cell12.className = "tds";
   	Cell12.align="center";	  	
   	
	Cell1.innerHTML = Number(rowIdMCA);
	Cell2.innerHTML = '<input type="radio" name="radioVoucherChallanMCA'+rowIdMCA+'" id="radioVoucherMCA'+rowIdMCA+'" value="V" onclick="whichRowMCA(this,1,'+rowIdMCA+');" size="5"/>Voucher  <input type="radio" name="radioVoucherChallanMCA'+rowIdMCA+'" id="radioChallanMCA'+rowIdMCA+'" value="C" onclick="whichRowMCA(this,2,'+rowIdMCA+');" size="5" />Challan';
	Cell3.innerHTML = '';
	Cell4.innerHTML = '<select name="cmbMonthMCA" id="cmbMonthMCA'+rowIdMCA+'"> '+LISTMONTHS+' </select>';
   	Cell5.innerHTML = '<select name="cmbYearMCA" id="cmbYearMCA'+rowIdMCA+'"> '+LISTYEARS+' </select>';
	if(flag==1){
   		Cell6.innerHTML = '<input type="text" name="txtPrinInstAmountMCA" id="txtPrinInstAmountMCA'+rowIdMCA+'" size="9" onkeypress="digitFormat(this);"  style="text-align: right"/>';
   		Cell7.innerHTML = '<input type="text" name="txtInterestInstAmountMCA" id="txtInterestInstAmountMCA'+rowIdMCA+'" size="9" onkeypress="amountFormat(this);" readonly="readonly" maxlength="4" style="text-align: right"/>';
   	}else if(flag==2){
   		Cell6.innerHTML = '<input type="text" name="txtPrinInstAmountMCA" id="txtPrinInstAmountMCA'+rowIdMCA+'" size="9" onkeypress="digitFormat(this);" readonly="readonly" style="text-align: right"/>';
   		Cell7.innerHTML = '<input type="text" name="txtInterestInstAmountMCA" id="txtInterestInstAmountMCA'+rowIdMCA+'" size="9" onkeypress="amountFormat(this);" maxlength="4" style="text-align: right"/>';
   	}	
	Cell8.innerHTML = '<input type="text" name="txtAmtToBeRecoveredMCA" id="txtAmtToBeRecoveredMCA'+rowIdMCA+'" size="10" onkeypress="digitFormat(this);"  style="text-align: right"/>';   	
   	Cell9.innerHTML = '<input type="text" name="txtTreasuryNameMCA" id="txtTreasuryNameMCA'+rowIdMCA+'" size="30"/>';
   	Cell10.innerHTML = '<input type="text" name="txtVoucherNoMCA" id="txtVoucherNoMCA'+rowIdMCA+'" size="15"/>';
   	Cell11.innerHTML = '<input type="text" name="txtVoucherDateMCA" id="txtVoucherDateMCA'+rowIdMCA+'" maxlength="10" size="9" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/> <img id="imgVoucher'+rowIdMCA+'" src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtVoucherDateMCA'+rowIdMCA+'\', 375, 570);" />';
   	Cell12.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblMotorAdvance\')" /> ';
   	
   	rowIdMCA++;
}
function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
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
function whichRow(obj,voucherOrChallan,elementRowId){ 
	var par=obj.parentNode; 
	while(par.nodeName.toLowerCase()!='tr'){ 
		par=par.parentNode; 
	}	
	if(voucherOrChallan=="1"){
		enableDisableVchr(par.rowIndex,elementRowId);
	}else{
		enableDisableChallan(par.rowIndex,elementRowId);
	}
	
} 
function whichRowHBA(obj,voucherOrChallan,elementRowId){ 
	var par=obj.parentNode; 
	while(par.nodeName.toLowerCase()!='tr'){ 
		par=par.parentNode; 
	}	
	if(voucherOrChallan=="1"){
		enableDisableVchrHBA(par.rowIndex,elementRowId);
	}else{
		enableDisableChallanHBA(par.rowIndex,elementRowId);
	}
	
} 
function whichRowMCA(obj,voucherOrChallan,elementRowId){ 
	var par=obj.parentNode; 
	while(par.nodeName.toLowerCase()!='tr'){ 
		par=par.parentNode; 
	}	
	if(voucherOrChallan=="1"){
		enableDisableVchrMCA(par.rowIndex,elementRowId);
	}else{
		enableDisableChallanMCA(par.rowIndex,elementRowId);
	}
	
} 
function enableDisableVchr(row,elementRowId)
{		
	document.getElementById("tblComputerAdvance").rows[row].cells[2].innerHTML = '<input type="text" id="txtInstlmntVchr'+elementRowId+'" name="txtInstlmntVchr" size=5 onkeypress="digitFormat(this);" onblur="validateInstallment('+elementRowId+')">';
}
function enableDisableChallan(row,elementRowId)
{	
	document.getElementById("tblComputerAdvance").rows[row].cells[2].innerHTML = 'From:<input type="text" id="txtInstlmntChallanFrom'+elementRowId+'" name="txtInstlmntChallanFrom'+elementRowId+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment('+elementRowId+')"/>To:<input type="text" id="txtInstlmntChallanTo'+elementRowId+'" name="txtInstlmntChallanTo'+elementRowId+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment('+elementRowId+','+row+')"/>';		
}
function enableDisableVchrHBA(row,elementRowId)
{	
	document.getElementById("tblHouseAdvance").rows[row].cells[2].innerHTML = '<input type="text" id="txtInstlmntVchrHBA'+elementRowId+'" name="txtInstlmntVchrHBA" size=5 onkeypress="digitFormat(this);" onblur="validateInstallmentHBA('+elementRowId+')">';
}
function enableDisableChallanHBA(row,elementRowId)
{	
	document.getElementById("tblHouseAdvance").rows[row].cells[2].innerHTML = 'From:<input type="text" id="txtInstlmntChallanFromHBA'+elementRowId+'" name="txtInstlmntChallanFromHBA'+row+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallmentHBA('+elementRowId+')"/>To:<input type="text" id="txtInstlmntChallanToHBA'+elementRowId+'" name="txtInstlmntChallanToHBA'+elementRowId+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallmentHBA('+elementRowId+')"/>';		
}
function enableDisableVchrMCA(row,elementRowId)
{	
	document.getElementById("tblMotorAdvance").rows[row].cells[2].innerHTML = '<input type="text" id="txtInstlmntVchrMCA'+elementRowId+'" name="txtInstlmntVchrMCA" size=5 onkeypress="digitFormat(this);" onblur="validateInstallmentMCA('+elementRowId+')">';
}
function enableDisableChallanMCA(row,elementRowId)
{	
	document.getElementById("tblMotorAdvance").rows[row].cells[2].innerHTML = 'From:<input type="text" id="txtInstlmntChallanFromMCA'+elementRowId+'" name="txtInstlmntChallanFromMCA'+elementRowId+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallmentMCA('+elementRowId+')"/>To:<input type="text" id="txtInstlmntChallanToMCA'+elementRowId+'" name="txtInstlmntChallanToMCA'+elementRowId+'" size=2 onkeypress="digitFormat(this);" onblur="validateInstallmentMCA('+elementRowId+')"/>';		
}
function validateInstallment(elementRowId)
{
	var instlmnt;
	var prevElementId;
	var instlmntPrev;
	try{
		if(elementRowId != 0){
			prevElementId = Number(elementRowId) - 1;
		}
		if(document.getElementById("radioVoucher"+elementRowId).checked){
			type = "Voucher";
		}else{
			type = "Challan";
		}
		
		if(type=="Voucher"){
			instlmnt = document.getElementById("txtInstlmntVchr"+elementRowId).value;
				if(instlmnt > 0){				
					if(document.getElementById("radioVoucher"+prevElementId).checked){				
						instlmntPrev = document.getElementById("txtInstlmntVchr"+prevElementId).value;
					
						if(Number(instlmntPrev) > 0){
							if(Number(instlmntPrev)>=Number(instlmnt)){
								alert("Installment No. are not in sequence");
								document.getElementById("txtInstlmntVchr"+elementRowId).value= "";
								document.getElementById("txtInstlmntVchr"+elementRowId).focus();
								return;
							}
						}else{
							alert("Previous Installment No. can not be blank");						
							document.getElementById("txtInstlmntVchr"+prevElementId).focus();
							return;
						}
					}
					else{
						to_prev = document.getElementById("txtInstlmntChallanTo"+prevElementId).value;
						
						if(Number(to_prev) > 0)
						{
							if(Number(to_prev)>=Number(instlmnt)){
								alert("Installment No. are not in sequence");
								document.getElementById("txtInstlmntVchr"+elementRowId).value= "";
								document.getElementById("txtInstlmntVchr"+elementRowId).focus();
								return;
							}
						}
						else
						{
							alert("Previous Installment No. can not be blank");						
							document.getElementById("txtInstlmntChallanTo"+prevElementId).focus();
							return;
						}
					}
				
				}
			}else if(type=="Challan"){
				
				from = document.getElementById("txtInstlmntChallanFrom"+elementRowId).value;
				to = document.getElementById("txtInstlmntChallanTo"+elementRowId).value;
				if(from > 0){
					if(Number(to)>0){
						if(Number(from) > Number(to))
						{
							alert("Installment From can not be greater than Installment To");
							document.getElementById("txtInstlmntChallanTo"+elementRowId).value = "";
							document.getElementById("txtInstlmntChallanTo"+elementRowId).focus();
							return;
						}
					}	
					if(document.getElementById("radioVoucher"+prevElementId).checked){
						instlmnt_prev = document.getElementById("txtInstlmntVchr"+prevElementId).value;
							if(Number(instlmnt_prev) > 0){
								if(Number(instlmnt_prev)>=Number(from)){
									alert("Installment No. are not in sequence");
									document.getElementById("txtInstlmntChallanFrom"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanTo"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanFrom"+elementRowId).focus();
									return;
								}
							}else{
									alert("Previous Installment No. can not be blank");						
									document.getElementById("txtInstlmntVchr"+prevElementId).focus();
									return;
								}
					}else{
						to_prev = document.getElementById("txtInstlmntChallanTo"+prevElementId).value;
							if(Number(to_prev) > 0){
								if(Number(to_prev)>=Number(from)){
									alert("Installment No. are not in sequence");
									document.getElementById("txtInstlmntChallanFrom"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanTo"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanFrom"+elementRowId).focus();
									return;
								}
							}else{
								alert("Previous Installment No. can not be blank");						
								document.getElementById("txtInstlmntChallanTo"+prevElementId).focus();
								return;
							}
						}
					}		
				}
	}catch(e){
		
	}
	
}
function validateInstallmentHBA(elementRowId)
{
	var instlmnt;
	var prevElementId;
	var instlmntPrev;
	try{
		if(elementRowId != 0){
			prevElementId = Number(elementRowId) - 1;
		}
		if(document.getElementById("radioVoucherHBA"+elementRowId).checked){
			type = "Voucher";
		}else{
			type = "Challan";
		}
		
		if(type=="Voucher"){
			instlmnt = document.getElementById("txtInstlmntVchrHBA"+elementRowId).value;
				if(instlmnt > 0){				
					if(document.getElementById("radioVoucherHBA"+prevElementId).checked){				
						instlmntPrev = document.getElementById("txtInstlmntVchrHBA"+prevElementId).value;
					
						if(Number(instlmntPrev) > 0){
							if(Number(instlmntPrev)>=Number(instlmnt)){
								alert("Installment No. are not in sequence");
								document.getElementById("txtInstlmntVchrHBA"+elementRowId).value= "";
								document.getElementById("txtInstlmntVchrHBA"+elementRowId).focus();
								return;
							}
						}else{
							alert("Previous Installment No. can not be blank");						
							document.getElementById("txtInstlmntVchrHBA"+prevElementId).focus();
							return;
						}
					}
					else{
						to_prev = document.getElementById("txtInstlmntChallanToHBA"+prevElementId).value;
						
						if(Number(to_prev) > 0)
						{
							if(Number(to_prev)>=Number(instlmnt)){
								alert("Installment No. are not in sequence");
								document.getElementById("txtInstlmntVchrHBA"+elementRowId).value= "";
								document.getElementById("txtInstlmntVchrHBA"+elementRowId).focus();
								return;
							}
						}
						else
						{
							alert("Previous Installment No. can not be blank");						
							document.getElementById("txtInstlmntChallanToHBA"+prevElementId).focus();
							return;
						}
					}
				
				}
			}else if(type=="Challan"){
				
				from = document.getElementById("txtInstlmntChallanFromHBA"+elementRowId).value;
				to = document.getElementById("txtInstlmntChallanToHBA"+elementRowId).value;
				if(from > 0){
					if(Number(to)>0){
						if(Number(from) > Number(to))
						{
							alert("Installment From can not be greater than Installment To");
							document.getElementById("txtInstlmntChallanToHBA"+elementRowId).value = "";
							document.getElementById("txtInstlmntChallanToHBA"+elementRowId).focus();
							return;
						}
					}	
					if(document.getElementById("radioVoucherHBA"+prevElementId).checked){
						instlmnt_prev = document.getElementById("txtInstlmntVchrHBA"+prevElementId).value;
							if(Number(instlmnt_prev) > 0){
								if(Number(instlmnt_prev)>=Number(from)){
									alert("Installment No. are not in sequence");
									document.getElementById("txtInstlmntChallanFromHBA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanToHBA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanFromHBA"+elementRowId).focus();
									return;
								}
							}else{
									alert("Previous Installment No. can not be blank");						
									document.getElementById("txtInstlmntVchrHBA"+prevElementId).focus();
									return;
								}
					}else{
						to_prev = document.getElementById("txtInstlmntChallanToHBA"+prevElementId).value;
							if(Number(to_prev) > 0){
								if(Number(to_prev)>=Number(from)){
									alert("Installment No. are not in sequence");
									document.getElementById("txtInstlmntChallanFromHBA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanToHBA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanFromHBA"+elementRowId).focus();
									return;
								}
							}else{
								alert("Previous Installment No. can not be blank");						
								document.getElementById("txtInstlmntChallanToHBA"+prevElementId).focus();
								return;
							}
						}
					}		
				}
	}catch(e){
		
	}
	
}
function validateInstallmentMCA(elementRowId)
{
	var instlmnt;
	var prevElementId;
	var instlmntPrev;
	try{
		if(elementRowId != 0){
			prevElementId = Number(elementRowId) - 1;
		}
		if(document.getElementById("radioVoucherMCA"+elementRowId).checked){
			type = "Voucher";
		}else{
			type = "Challan";
		}
		
		if(type=="Voucher"){
			instlmnt = document.getElementById("txtInstlmntVchrMCA"+elementRowId).value;
				if(instlmnt > 0){				
					if(document.getElementById("radioVoucherMCA"+prevElementId).checked){				
						instlmntPrev = document.getElementById("txtInstlmntVchrMCA"+prevElementId).value;
					
						if(Number(instlmntPrev) > 0){
							if(Number(instlmntPrev)>=Number(instlmnt)){
								alert("Installment No. are not in sequence");
								document.getElementById("txtInstlmntVchrMCA"+elementRowId).value= "";
								document.getElementById("txtInstlmntVchrMCA"+elementRowId).focus();
								return;
							}
						}else{
							alert("Previous Installment No. can not be blank");						
							document.getElementById("txtInstlmntVchrMCA"+prevElementId).focus();
							return;
						}
					}
					else{
						to_prev = document.getElementById("txtInstlmntChallanToMCA"+prevElementId).value;
						
						if(Number(to_prev) > 0)
						{
							if(Number(to_prev)>=Number(instlmnt)){
								alert("Installment No. are not in sequence");
								document.getElementById("txtInstlmntVchrMCA"+elementRowId).value= "";
								document.getElementById("txtInstlmntVchrMCA"+elementRowId).focus();
								return;
							}
						}
						else
						{
							alert("Previous Installment No. can not be blank");						
							document.getElementById("txtInstlmntChallanToMCA"+prevElementId).focus();
							return;
						}
					}
				
				}
			}else if(type=="Challan"){
				
				from = document.getElementById("txtInstlmntChallanFromMCA"+elementRowId).value;
				to = document.getElementById("txtInstlmntChallanToMCA"+elementRowId).value;
				if(from > 0){
					if(Number(to)>0){
						if(Number(from) > Number(to))
						{
							alert("Installment From can not be greater than Installment To");
							document.getElementById("txtInstlmntChallanToMCA"+elementRowId).value = "";
							document.getElementById("txtInstlmntChallanToMCA"+elementRowId).focus();
							return;
						}
					}	
					if(document.getElementById("radioVoucherMCA"+prevElementId).checked){
						instlmnt_prev = document.getElementById("txtInstlmntVchrMCA"+prevElementId).value;
							if(Number(instlmnt_prev) > 0){
								if(Number(instlmnt_prev)>=Number(from)){
									alert("Installment No. are not in sequence");
									document.getElementById("txtInstlmntChallanFromMCA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanToMCA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanFromMCA"+elementRowId).focus();
									return;
								}
							}else{
									alert("Previous Installment No. can not be blank");						
									document.getElementById("txtInstlmntVchrMCA"+prevElementId).focus();
									return;
								}
					}else{
						to_prev = document.getElementById("txtInstlmntChallanToMCA"+prevElementId).value;
							if(Number(to_prev) > 0){
								if(Number(to_prev)>=Number(from)){
									alert("Installment No. are not in sequence");
									document.getElementById("txtInstlmntChallanFromMCA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanToMCA"+elementRowId).value= "";
									document.getElementById("txtInstlmntChallanFromMCA"+elementRowId).focus();
									return;
								}
							}else{
								alert("Previous Installment No. can not be blank");						
								document.getElementById("txtInstlmntChallanToMCA"+prevElementId).focus();
								return;
							}
						}
					}		
				}
	}catch(e){
		
	}
	
}
function saveForm(){
	if(!validateForm() || !validateComputerAdvance() || !validateHouseAdvance() || !validateMotorAdvance())
	{
		return;
	}
	RecoveredInsCA = new Object();	
	RecoveredInsCA = getNoOfRecoveredInsCA();
	document.getElementById("hidTotalRecoveredCA").value = RecoveredInsCA.insNo;	
	
	if(Number(document.getElementById("hidTotalRecoveredCA").value)>Number(document.getElementById("txtTotalInsCA").value)){
		goToTab(0);
		alert('Total No. of Recovered Installment is Greater than Total No. of Installment');
		document.getElementById("txtTotalInsCA").focus();
		return;
	}
	
	RecoveredInsHBA = new Object();	
	RecoveredInsHBA = getNoOfRecoveredInsHBA();
	document.getElementById("hidTotalPrinRecoveredHBA").value = RecoveredInsHBA.insPrinNo;
	document.getElementById("hidTotalIntRecoveredHBA").value = RecoveredInsHBA.insInterestNo;
	
	if(Number(document.getElementById("hidTotalPrinRecoveredHBA").value)>Number(document.getElementById("txtTotalPrincInsHBA").value)){
		goToTab(1);
		alert('Total No. of Recovered Installment is Greater than Total No. of Principal Installment');
		document.getElementById("txtTotalPrincInsHBA").focus();
		return;
	}
	if(Number(document.getElementById("hidTotalIntRecoveredHBA").value)>Number(document.getElementById("txtTotalInterestInsHBA").value)){
		goToTab(1);
		alert('Total No. of Recovered Installment is Greater than Total No. of Interest Installment');
		document.getElementById("txtTotalInterestInsHBA").focus();
		return;
	}
	
	
	RecoveredInsMCA = new Object();	
	RecoveredInsMCA = getNoOfRecoveredInsMCA();
	document.getElementById("hidTotalPrinRecoveredMCA").value = RecoveredInsMCA.insPrinNo;
	document.getElementById("hidTotalIntRecoveredMCA").value = RecoveredInsMCA.insInterestNo;
	
	if(Number(document.getElementById("hidTotalPrinRecoveredMCA").value)>Number(document.getElementById("txtTotalPrincInsMCA").value)){
		goToTab(2);
		alert('Total No. of Recovered Principal Installment is Greater than Total No. of Principal Installment');
		document.getElementById("txtTotalPrincInsMCA").focus();
		return;
	}
	if(Number(document.getElementById("hidTotalIntRecoveredMCA").value)>Number(document.getElementById("txtTotalInterestInsMCA").value)){
		goToTab(2);
		alert('Total No. of Recovered Interest Installment is Greater than Total No. of Interest Installment');
		document.getElementById("txtTotalInterestInsMCA").focus();
		return;
	}
	
	var table=document.getElementById("tblComputerAdvance");
	var rowCount=table.rows.length;
	var tableHBA=document.getElementById("tblHouseAdvance");
	var rowCountHBA=tableHBA.rows.length;
	var tableMCA=document.getElementById("tblMotorAdvance");
	var rowCountMCA=tableMCA.rows.length;
	var uri="ifms.htm?actionFlag=saveLNADataEntryForm";
	var url = "&TotalRecord="+rowCount+"&rowId="+rowId+"&TotalRecordHBA="+rowCountHBA+"&rowIdHBA="+rowIdHBA+"&TotalRecordMCA="+rowCountMCA+"&rowIdMCA="+rowIdMCA+runForm(0);
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getSaveRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function forwardForm(){
	if(!validateForm() || !validateComputerAdvance() || !validateHouseAdvance() || !validateMotorAdvance())
	{
		return;
	}
	RecoveredInsCA = new Object();	
	RecoveredInsCA = getNoOfRecoveredInsCA();
	document.getElementById("hidTotalRecoveredCA").value = RecoveredInsCA.insNo;	
	
	if(Number(document.getElementById("hidTotalRecoveredCA").value)>Number(document.getElementById("txtTotalInsCA").value)){
		goToTab(0);
		alert('Total No. of Recovered Installment is Greater than Total No. of Installment');
		document.getElementById("txtTotalInsCA").focus();
		return;
	}
	
	RecoveredInsHBA = new Object();	
	RecoveredInsHBA = getNoOfRecoveredInsHBA();
	document.getElementById("hidTotalPrinRecoveredHBA").value = RecoveredInsHBA.insPrinNo;
	document.getElementById("hidTotalIntRecoveredHBA").value = RecoveredInsHBA.insInterestNo;
	
	if(Number(document.getElementById("hidTotalPrinRecoveredHBA").value)>Number(document.getElementById("txtTotalPrincInsHBA").value)){
		goToTab(1);
		alert('Total No. of Recovered Installment is Greater than Total No. of Principal Installment');
		document.getElementById("txtTotalPrincInsHBA").focus();
		return;
	}
	if(Number(document.getElementById("hidTotalIntRecoveredHBA").value)>Number(document.getElementById("txtTotalInterestInsHBA").value)){
		goToTab(1);
		alert('Total No. of Recovered Installment is Greater than Total No. of Interest Installment');
		document.getElementById("txtTotalInterestInsHBA").focus();
		return;
	}
	
	
	RecoveredInsMCA = new Object();	
	RecoveredInsMCA = getNoOfRecoveredInsMCA();
	document.getElementById("hidTotalPrinRecoveredMCA").value = RecoveredInsMCA.insPrinNo;
	document.getElementById("hidTotalIntRecoveredMCA").value = RecoveredInsMCA.insInterestNo;
	
	if(Number(document.getElementById("hidTotalPrinRecoveredMCA").value)>Number(document.getElementById("txtTotalPrincInsMCA").value)){
		goToTab(2);
		alert('Total No. of Recovered Principal Installment is Greater than Total No. of Principal Installment');
		document.getElementById("txtTotalPrincInsMCA").focus();
		return;
	}
	if(Number(document.getElementById("hidTotalIntRecoveredMCA").value)>Number(document.getElementById("txtTotalInterestInsMCA").value)){
		goToTab(2);
		alert('Total No. of Recovered Interest Installment is Greater than Total No. of Interest Installment');
		document.getElementById("txtTotalInterestInsMCA").focus();
		return;
	}
	
	var table=document.getElementById("tblComputerAdvance");
	var rowCount=table.rows.length;
	var tableHBA=document.getElementById("tblHouseAdvance");
	var rowCountHBA=tableHBA.rows.length;
	var tableMCA=document.getElementById("tblMotorAdvance");
	var rowCountMCA=tableMCA.rows.length;
	var uri="ifms.htm?actionFlag=forwardLNADataEntryForm";
	var url = "&TotalRecord="+rowCount+"&rowId="+rowId+"&TotalRecordHBA="+rowCountHBA+"&rowIdHBA="+rowIdHBA+"&TotalRecordMCA="+rowCountMCA+"&rowIdMCA="+rowIdMCA+runForm(0);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getForwarsRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function approveForm(){
	var hidCompId=document.getElementById("hidCompId").value;
	var hidHouseId=document.getElementById("hidHouseId").value;
	var hidMotorId=document.getElementById("hidMotorId").value;
	var TrnEmpLoanPk=document.getElementById("hidTrnEmpLoanPk").value;
	var txtEmployeeCode=document.getElementById("txtEmployeeCode").value;
	var txtHODRemarksCA=document.getElementById("txtHODRemarksCA").value;
	var txtHODRemarksHBA=document.getElementById("txtHODRemarksHBA").value;
	var txtHODRemarksMCA=document.getElementById("txtHODRemarksMCA").value;
	
	var uri="ifms.htm?actionFlag=approveDataEntryForm";
	var url ="&hidCompId="+hidCompId+"&hidHouseId="+hidHouseId+"&hidMotorId="+hidMotorId+"&TrnEmpLoanPk="+TrnEmpLoanPk+
				"&txtEmployeeCode="+txtEmployeeCode+"&txtHODRemarksCA="+txtHODRemarksCA+
					"&txtHODRemarksHBA="+txtHODRemarksHBA+"&txtHODRemarksMCA"+txtHODRemarksMCA;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getApproveRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getApproveRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblSaveFlag=="true") {		
		alert('Request has been Approved successfully');		
		self.location.href="ifms.htm?actionFlag=loadDraftDataEntry&userType=HOD";
	}
}
function getSaveRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblSaveFlag=="true") {		
		alert('Request saved successfully');		
		self.location.href="ifms.htm?actionFlag=loadDraftDataEntry&userType=HODASST";
	}
}
function getForwarsRequestMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblSaveFlag=="true") {		
		alert('Request Forwared successfully');		
		self.location.href="ifms.htm?actionFlag=loadDraftDataEntry&userType=HODASST";
	}
}
function getHomePage(){
	self.location.href = "ifms.htm?actionFlag=getHomePage";
}
function displayDisbursment(){
	var houseSubType = document.getElementById("cmbHouseSubType").value;
	if(houseSubType == 800038 || houseSubType == 800058){
		document.getElementById('trDisbursement').style.display = '';
	}else{
		document.getElementById("txtNoOfDisburseInst").value = '';
		document.getElementById('trDisbursement').style.display = 'none';
		document.getElementById("txtReleaseDate1").value = '';
		document.getElementById("txtReleaseDate2").value = '';
		document.getElementById("txtReleaseDate3").value = '';
		document.getElementById("txtReleaseDate4").value = '';
		document.getElementById('trSanc1').style.display = 'none';
		document.getElementById('trSanc2').style.display = 'none';
		document.getElementById("trSanc3").style.display = 'none';	
		document.getElementById('trSanc4').style.display = 'none';
		document.getElementById('trSanc5').style.display = 'none';
		document.getElementById('trSanc6').style.display = 'none';
		document.getElementById("trSanc7").style.display = 'none';	
		document.getElementById("trSanc8").style.display = 'none';	
	}
}
function displayReleaseDate(){
	var noOfDisbursement = document.getElementById("txtNoOfDisburseInst").value;
	if(noOfDisbursement == 1){
		document.getElementById("txtReleaseDate2").value = '';
		document.getElementById("txtReleaseDate3").value = '';
		document.getElementById("txtReleaseDate4").value = '';
		document.getElementById('trSanc1').style.display = '';
		document.getElementById('trSanc2').style.display = '';
		document.getElementById("trSanc3").style.display = 'none';	
		document.getElementById('trSanc4').style.display = 'none';
		document.getElementById('trSanc5').style.display = 'none';
		document.getElementById('trSanc6').style.display = 'none';
		document.getElementById("trSanc7").style.display = 'none';	
		document.getElementById("trSanc8").style.display = 'none';	
	}else if(noOfDisbursement == 2){
		document.getElementById("txtReleaseDate3").value = '';
		document.getElementById("txtReleaseDate4").value = '';
		document.getElementById('trSanc1').style.display = '';	
		document.getElementById("trSanc2").style.display = '';
		document.getElementById('trSanc3').style.display = '';
		document.getElementById('trSanc4').style.display = '';
		document.getElementById('trSanc5').style.display = 'none';
		document.getElementById('trSanc6').style.display = 'none';
		document.getElementById("trSanc7").style.display = 'none';	
		document.getElementById("trSanc8").style.display = 'none';	
	}else if(noOfDisbursement == 3){
		document.getElementById("txtReleaseDate4").value = '';
		document.getElementById('trSanc1').style.display = '';	
		document.getElementById("trSanc2").style.display = '';	
		document.getElementById("trSanc3").style.display = '';	
		document.getElementById('trSanc4').style.display = '';
		document.getElementById('trSanc5').style.display = '';
		document.getElementById('trSanc6').style.display = '';
		document.getElementById("trSanc7").style.display = 'none';	
		document.getElementById("trSanc8").style.display = 'none';	
	}else if(noOfDisbursement == 4){		
		document.getElementById('trSanc1').style.display = '';	
		document.getElementById("trSanc2").style.display = '';	
		document.getElementById("trSanc3").style.display = '';	
		document.getElementById('trSanc4').style.display = '';
		document.getElementById('trSanc5').style.display = '';
		document.getElementById('trSanc6').style.display = '';
		document.getElementById("trSanc7").style.display = '';	
		document.getElementById("trSanc8").style.display = '';		
	}
}
/*function disablePrinAndInterestAmount(elementRowId){
	
	var prinAmt = document.getElementById("txtPrinInstAmountHBA"+elementRowId).value;
	var interestAmt = document.getElementById("txtInterestInstAmountHBA"+elementRowId).value;
	if(prinAmt != ''){
		document.getElementById("txtInterestInstAmountHBA"+elementRowId).readOnly = true;	
	}else if(interestAmt != ''){
		document.getElementById("txtPrinInstAmountHBA"+elementRowId).readOnly = true;	
	}else if(interestAmt == '' && prinAmt == ''){
		document.getElementById("txtInterestInstAmountHBA"+elementRowId).readOnly = false;
		document.getElementById("txtPrinInstAmountHBA"+elementRowId).readOnly = false;
	}	
}*/

function validateDisbursement(){
	var houseSubType = document.getElementById("cmbHouseSubType").value;
	var noOfDisbursement = document.getElementById("txtNoOfDisburseInst").value;
	if(noOfDisbursement != ''){
		if(houseSubType == 800038){
			if(noOfDisbursement <= 0){
				alert('No of Disbursement is greater than Zero');
				document.getElementById("txtNoOfDisburseInst").value = '';
				document.getElementById("txtNoOfDisburseInst").focus();
			}
			if(noOfDisbursement > 3){
				alert('No of Disbursement is less than or equal to Three');
				document.getElementById("txtNoOfDisburseInst").value = '';
				document.getElementById("txtNoOfDisburseInst").focus();
			}
		}else if(houseSubType == 800058){
			if(noOfDisbursement <= 0){
				alert('No of Disbursement is greater than Zero');
				document.getElementById("txtNoOfDisburseInst").value = '';
				document.getElementById("txtNoOfDisburseInst").focus();
			}
			if(noOfDisbursement > 4){
				alert('No of Disbursement is less than or equal to Four');
				document.getElementById("txtNoOfDisburseInst").value = '';
				document.getElementById("txtNoOfDisburseInst").focus();
			}
		}
	}
}
function validateForm(){
	goToTab(0);
	if (!chkEmpty(document.getElementById("txtEmployeeCode"), "Employee Code")){
		return false;		
	}	
	var sancAmountCA = document.getElementById("txtSancAmountCA").value;
	var sancAmountHBA = document.getElementById("txtSancAmountHBA").value;
	var sancAmountMCA = document.getElementById("txtSancAmountMCA").value;
	if(sancAmountCA ==''){		
		if(sancAmountHBA == ''){			
			if(sancAmountMCA == ''){
				alert("Please Enter Details for One Advance");
				document.getElementById("txtSancAmountCA").focus();
				return false;
			}
		}
	}
	return true;
}
function validateComputerAdvance(){
	var SancAmount = document.getElementById("txtSancAmountCA").value;
	if(SancAmount!=''){
		goToTab(0);
		if (!chkEmpty(document.getElementById("txtSanctionedDateCA"), "Sanctioned Date")
				|| !chkEmpty(document.getElementById("cmbComputerSubType"), "Sub Type")
				|| !chkEmpty(document.getElementById("txtInterestRateCA"), "Interest Rate")
				|| !chkEmpty(document.getElementById("txtOrderNoCA"), "Order No.")
				|| !chkEmpty(document.getElementById("txtOrderDateCA"), "Order Date")
				|| !chkEmpty(document.getElementById("txtTotalInsCA"), "Total No. of Installment")
				|| !chkEmpty(document.getElementById("txtRecoverInsCA"), "Installments Recovered"))
		{
			return false;
		}
	
	for(var i = 0; i<rowId; i++){
			
			try
			{	
				if(!(document.getElementById('radioVoucher'+i).checked))
				{
					if(!(document.getElementById('radioChallan'+i).checked))
					{
						alert("Please select voucher or Challan");
						document.getElementById('radioVoucher'+i).focus();
						return false;
					}						
				}			
				if(document.getElementById('radioVoucher'+i).checked)
				{
					if(document.getElementById("txtInstlmntVchr"+i).value == "")
					{
						alert("Please Enter Voucher No.");
						document.getElementById("txtInstlmntVchr"+i).focus();
						return false;
					}					
				}else if(document.getElementById('radioChallan'+i).checked)
				{
					if(document.getElementById("txtInstlmntChallanFrom"+i).value == "")
					{
						alert("Please Enter Challan From No.");
						document.getElementById("txtInstlmntChallanFrom"+i).focus();
						return false;
					}
					else if(document.getElementById("txtInstlmntChallanTo"+i).value == "")
					{
						alert("Please Enter Challan To No.");
						document.getElementById("txtInstlmntChallanTo"+i).focus();
						return false;
					}						
					
				}
				if(document.getElementById("txtInstAmountCA"+i).value == "")
				{
					alert("Please Enter Installment Amount");
					document.getElementById("txtInstAmountCA"+i).focus();
					return false;
				}
				if(document.getElementById("txtAmtToBeRecoveredCA"+i).value == "")
				{
					alert("Please Enter Amount to be Recovered");
					document.getElementById("txtAmtToBeRecoveredCA"+i).focus();
					return false;
				}
				if(document.getElementById("txtVoucherNoCA"+i).value == "")
				{
					alert("Please Enter Voucher/Challan No.");
					document.getElementById("txtVoucherNoCA"+i).focus();
					return false;
				}
				if(document.getElementById("txtVoucherDateCA"+i).value == "")
				{
					alert("Please Enter Voucher/Challan Date");
					document.getElementById("txtVoucherDateCA"+i).focus();
					return false;
				}																				

			}
			catch(e)
			{
				
			}	
	}
	}
	return true;
}
function validateHouseAdvance(){
	var SancAmount = document.getElementById("txtSancAmountHBA").value;
	if(SancAmount!=''){
		goToTab(1);
		if (!chkEmpty(document.getElementById("txtSanctionedDateHBA"), "Sanctioned Date")
				|| !chkEmpty(document.getElementById("cmbHouseSubType"), "Sub Type")
				|| !chkEmpty(document.getElementById("txtInterestRateHBA"), "Interest Rate")
				|| !chkEmpty(document.getElementById("txtOrderNoHBA"), "Order No.")
				|| !chkEmpty(document.getElementById("txtOrderDateHBA"), "Order Date")
				|| !chkEmpty(document.getElementById("txtTotalPrincAmtHBA"), "Total Principal Amount")
				|| !chkEmpty(document.getElementById("txtTotalInterestAmtHBA"), "Total Interest Amount")
				|| !chkEmpty(document.getElementById("txtTotalPrincInsHBA"), "Total No. of Principal Installment")
				|| !chkEmpty(document.getElementById("txtTotalInterestInsHBA"), "Total No. of Interest Installment")
				|| !chkEmpty(document.getElementById("txtRecoverPrincInsHBA"), "Principal Installments Recovered")
				|| !chkEmpty(document.getElementById("txtRecoverInterestInsHBA"), "Interest Installments Recovered"))
		{
			return false;
		}
	
	var HouseSubType = document.getElementById("cmbHouseSubType").value;
	if(HouseSubType == 800038 || HouseSubType == 800058){
		if (!chkEmpty(document.getElementById("txtNoOfDisburseInst"), "No Of Disbursment Installment"))
		{
			return false;			
		}			
	}
	var NoOfDisburseInst = document.getElementById("txtNoOfDisburseInst").value;
	if(NoOfDisburseInst !=''){
		if(NoOfDisburseInst == 1){
			if (!chkEmpty(document.getElementById("txtReleaseDate1"), "Sanctioned Date"))
			{
				return false;			
			}	
		}
		if(NoOfDisburseInst == 2){
			if (!chkEmpty(document.getElementById("txtReleaseDate2"), "Sanctioned Date"))
			{
				return false;			
			}	
		}
		if(NoOfDisburseInst == 3){
			if (!chkEmpty(document.getElementById("txtReleaseDate3"), "Sanctioned Date"))
			{
				return false;			
			}
		}
		if(NoOfDisburseInst == 4){
			if (!chkEmpty(document.getElementById("txtReleaseDate4"), "Sanctioned Date"))
			{
				return false;			
			}
		}
	}		
		for(var i = 0; i<rowIdHBA; i++)
		{			
			try
			{	
				if(!(document.getElementById('radioVoucherHBA'+i).checked))
				{
					if(!(document.getElementById('radioChallanHBA'+i).checked))
					{
						alert("Please select voucher or Challan");
						document.getElementById('radioVoucherHBA'+i).focus();
						return false;
					}						
				}			
				if(document.getElementById('radioVoucherHBA'+i).checked)
				{
					if(document.getElementById("txtInstlmntVchrHBA"+i).value == "")
					{
						alert("Please Enter Voucher No.");
						document.getElementById("txtInstlmntVchrHBA"+i).focus();
						return false;
					}					
				}else if(document.getElementById('radioChallanHBA'+i).checked)
				{
					if(document.getElementById("txtInstlmntChallanFromHBA"+i).value == "")
					{
						alert("Please Enter Challan From No.");
						document.getElementById("txtInstlmntChallanFromHBA"+i).focus();
						return false;
					}
					else if(document.getElementById("txtInstlmntChallanToHBA"+i).value == "")
					{
						alert("Please Enter Challan To No.");
						document.getElementById("txtInstlmntChallanToHBA"+i).focus();
						return false;
					}						
					
				}
				
				if(document.getElementById("txtPrinInstAmountHBA"+i).readOnly){
					if(document.getElementById("txtInterestInstAmountHBA"+i).value == "")
					{
						alert("Please Enter Interest Installment Amount");
						document.getElementById("txtInterestInstAmountHBA"+i).focus();
						return false;
					}
				}
				if(document.getElementById("txtInterestInstAmountHBA"+i).readOnly){
					if(document.getElementById("txtPrinInstAmountHBA"+i).value == "")
					{
						alert("Please Enter Principal Installment Amount");
						document.getElementById("txtPrinInstAmountHBA"+i).focus();
						return false;
					}
				}
				if(document.getElementById("txtAmtToBeRecoveredHBA"+i).value == "")
				{
					alert("Please Enter Amount to be Recovered");
					document.getElementById("txtAmtToBeRecoveredHBA"+i).focus();
					return false;
				}
				if(document.getElementById("txtVoucherNoHBA"+i).value == "")
				{
					alert("Please Enter Voucher/Challan No.");
					document.getElementById("txtVoucherNoHBA"+i).focus();
					return false;
				}
				if(document.getElementById("txtVoucherDateHBA"+i).value == "")
				{
					alert("Please Enter Voucher/Challan Date");
					document.getElementById("txtVoucherDateHBA"+i).focus();
					return false;
				}																				
	
			}
			catch(e)
			{
				
			}		
		}
	}
	return true;
}
function validateMotorAdvance(){
	var SancAmount = document.getElementById("txtSancAmountMCA").value;
	if(SancAmount!=''){
		goToTab(2);
		if (!chkEmpty(document.getElementById("txtSanctionedDateMCA"), "Sanctioned Date")
				|| !chkEmpty(document.getElementById("cmbMotorSubType"), "Sub Type")
				|| !chkEmpty(document.getElementById("txtInterestRateMCA"), "Interest Rate")
				|| !chkEmpty(document.getElementById("txtOrderNoMCA"), "Order No.")
				|| !chkEmpty(document.getElementById("txtOrderDateMCA"), "Order Date")
				|| !chkEmpty(document.getElementById("txtTotalPrincAmtMCA"), "Total Principal Amount")
				|| !chkEmpty(document.getElementById("txtTotalInterestAmtMCA"), "Total Interest Amount")
				|| !chkEmpty(document.getElementById("txtTotalPrincInsMCA"), "Total No. of Principal Installment")
				|| !chkEmpty(document.getElementById("txtTotalInterestInsMCA"), "Total No. of Interest Installment")
				|| !chkEmpty(document.getElementById("txtRecoverPrincInsMCA"), "Principal Installments Recovered")
				|| !chkEmpty(document.getElementById("txtRecoverInterestInsMCA"), "Interest Installments Recovered"))
		{
			return false;
		}	
		for(var i = 0; i<rowIdMCA; i++)
		{
			try
			{	
				if(!(document.getElementById('radioVoucherMCA'+i).checked))
				{
					if(!(document.getElementById('radioChallanMCA'+i).checked))
					{
						alert("Please select voucher or Challan");
						document.getElementById('radioVoucherMCA'+i).focus();
						return false;
					}						
				}			
				if(document.getElementById('radioVoucherMCA'+i).checked)
				{
					if(document.getElementById("txtInstlmntVchrMCA"+i).value == "")
					{
						alert("Please Enter Voucher No.");
						document.getElementById("txtInstlmntVchrMCA"+i).focus();
						return false;
					}					
				}else if(document.getElementById('radioChallanMCA'+i).checked)
				{
					if(document.getElementById("txtInstlmntChallanFromMCA"+i).value == "")
					{
						alert("Please Enter Challan From No.");
						document.getElementById("txtInstlmntChallanFromMCA"+i).focus();
						return false;
					}
					else if(document.getElementById("txtInstlmntChallanToMCA"+i).value == "")
					{
						alert("Please Enter Challan To No.");
						document.getElementById("txtInstlmntChallanToMCA"+i).focus();
						return false;
					}						
					
				}
				
				if(document.getElementById("txtPrinInstAmountMCA"+i).readOnly){
					if(document.getElementById("txtInterestInstAmountMCA"+i).value == "")
					{
						alert("Please Enter Interest Installment Amount");
						document.getElementById("txtInterestInstAmountMCA"+i).focus();
						return false;
					}
				}
				if(document.getElementById("txtInterestInstAmountMCA"+i).readOnly){
					if(document.getElementById("txtPrinInstAmountMCA"+i).value == "")
					{
						alert("Please Enter Principal Installment Amount");
						document.getElementById("txtPrinInstAmountMCA"+i).focus();
						return false;
					}
				}
				if(document.getElementById("txtAmtToBeRecoveredMCA"+i).value == "")
				{
					alert("Please Enter Amount to be Recovered");
					document.getElementById("txtAmtToBeRecoveredMCA"+i).focus();
					return false;
				}
				if(document.getElementById("txtVoucherNoMCA"+i).value == "")
				{
					alert("Please Enter Voucher/Challan No.");
					document.getElementById("txtVoucherNoMCA"+i).focus();
					return false;
				}
				if(document.getElementById("txtVoucherDateMCA"+i).value == "")
				{
					alert("Please Enter Voucher/Challan Date");
					document.getElementById("txtVoucherDateMCA"+i).focus();
					return false;
				}																				
	
			}
			catch(e)
			{
				
			}		
		}
	}
	return true;
}
function getNoOfRecoveredInsCA(){
	
	var maxVoucherIns = 0;
	var maxChallanIns = 0;	
	var maxNoOfRecoveredIns = 0;
	
	for(var i=0 ; i<Number(rowId);i++){
		try{
			if(document.getElementById("radioVoucher"+i).checked){
				if(!document.getElementById("txtInstAmountCA"+i).readOnly){
					if(maxVoucherIns < Number(document.getElementById("txtInstlmntVchr"+i).value)){
						maxVoucherIns = document.getElementById("txtInstlmntVchr"+i).value;
					}
				}				
			}else{
				if(!document.getElementById("txtInstAmountCA"+i).readOnly){
					if(maxChallanIns < Number(document.getElementById("txtInstlmntChallanTo"+i).value)){
						maxChallanIns = document.getElementById("txtInstlmntChallanTo"+i).value;
					}
				}
			}
		}catch(e){
			
		}						
	}	
	if(Number(maxVoucherIns)<Number(maxChallanIns))
		maxNoOfRecoveredIns = maxChallanIns
	else
	maxNoOfRecoveredIns = maxVoucherIns
	
	RecoveredIns = new Object();
	RecoveredIns.insNo = maxNoOfRecoveredIns;		

	return RecoveredIns;
}
function getNoOfRecoveredInsHBA(){
		
	var maxVoucherPrincipalIns = 0;
	var maxVoucherInterestIns = 0;
	var maxChallanPrincipalIns = 0;
	var maxChallanInterestIns = 0;
	var maxNoOfPrinRecoveredIns = 0;
	var maxNoOfInterestRecoveredIns = 0;
	
	for(var i=0 ; i<Number(rowIdHBA);i++){
		try{
			if(document.getElementById("radioVoucherHBA"+i).checked){
				if(!document.getElementById("txtPrinInstAmountHBA"+i).readOnly){
					if(maxVoucherPrincipalIns < Number(document.getElementById("txtInstlmntVchrHBA"+i).value)){
						maxVoucherPrincipalIns = document.getElementById("txtInstlmntVchrHBA"+i).value;
					}
				}else{
					if(maxVoucherInterestIns < Number(document.getElementById("txtInstlmntVchrHBA"+i).value)){
						maxVoucherInterestIns = document.getElementById("txtInstlmntVchrHBA"+i).value;
					}
				}
			}else{
				if(!document.getElementById("txtPrinInstAmountHBA"+i).readOnly){
					if(maxChallanPrincipalIns < Number(document.getElementById("txtInstlmntChallanToHBA"+i).value)){
						maxChallanPrincipalIns = document.getElementById("txtInstlmntChallanToHBA"+i).value;
					}
				}else{
					if(maxChallanInterestIns < Number(document.getElementById("txtInstlmntChallanToHBA"+i).value)){
						maxChallanInterestIns = document.getElementById("txtInstlmntChallanToHBA"+i).value;
					}
				}
			}
		}catch(e){
			
		}						
	}	
	if(Number(maxVoucherPrincipalIns)<Number(maxChallanPrincipalIns))
		maxNoOfPrinRecoveredIns = maxChallanPrincipalIns
	else
		maxNoOfPrinRecoveredIns = maxVoucherPrincipalIns
		
	if(Number(maxVoucherInterestIns)<Number(maxChallanInterestIns))
		maxNoOfInterestRecoveredIns = maxChallanInterestIns
	else
		maxNoOfInterestRecoveredIns = maxVoucherInterestIns
	
	RecoveredIns = new Object();
	RecoveredIns.insPrinNo = maxNoOfPrinRecoveredIns;
	RecoveredIns.insInterestNo = maxNoOfInterestRecoveredIns;

	return RecoveredIns;
}
function getNoOfRecoveredInsMCA(){
	
	var maxVoucherPrincipalIns = 0;
	var maxVoucherInterestIns = 0;
	var maxChallanPrincipalIns = 0;
	var maxChallanInterestIns = 0;
	var maxNoOfPrinRecoveredIns = 0;
	var maxNoOfInterestRecoveredIns = 0;
	
	for(var i=0 ; i<Number(rowIdMCA);i++){
		try{
			if(document.getElementById("radioVoucherMCA"+i).checked){
				if(!document.getElementById("txtPrinInstAmountMCA"+i).readOnly){
					if(maxVoucherPrincipalIns < Number(document.getElementById("txtInstlmntVchrMCA"+i).value)){
						maxVoucherPrincipalIns = document.getElementById("txtInstlmntVchrMCA"+i).value;
					}
				}else{
					if(maxVoucherInterestIns < Number(document.getElementById("txtInstlmntVchrMCA"+i).value)){
						maxVoucherInterestIns = document.getElementById("txtInstlmntVchrMCA"+i).value;
					}
				}
			}else{
				if(!document.getElementById("txtPrinInstAmountMCA"+i).readOnly){
					if(maxChallanPrincipalIns < Number(document.getElementById("txtInstlmntChallanToMCA"+i).value)){
						maxChallanPrincipalIns = document.getElementById("txtInstlmntChallanToMCA"+i).value;
					}
				}else{
					if(maxChallanInterestIns < Number(document.getElementById("txtInstlmntChallanToMCA"+i).value)){
						maxChallanInterestIns = document.getElementById("txtInstlmntChallanToMCA"+i).value;
					}
				}
			}
		}catch(e){
			
		}						
	}	
	if(Number(maxVoucherPrincipalIns)<Number(maxChallanPrincipalIns))
		maxNoOfPrinRecoveredIns = maxChallanPrincipalIns
	else
		maxNoOfPrinRecoveredIns = maxVoucherPrincipalIns
		
	if(Number(maxVoucherInterestIns)<Number(maxChallanInterestIns))
		maxNoOfInterestRecoveredIns = maxChallanInterestIns
	else
		maxNoOfInterestRecoveredIns = maxVoucherInterestIns
	
	RecoveredIns = new Object();
	RecoveredIns.insPrinNo = maxNoOfPrinRecoveredIns;
	RecoveredIns.insInterestNo = maxNoOfInterestRecoveredIns;

	return RecoveredIns;
}
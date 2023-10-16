var EvtgridCount = 0;
var SergridCount = 0;
var qulfSerCount = 0;
var provisionalCount = 0;
function eventDtlsTableAddRow()
{
		var payComm = document.getElementById("cmbPayComsn").value;
		
     	var rowCnt = document.getElementById("hidEventGridSize").value;
		var newRow =  document.getElementById("tblEventDtls").insertRow(document.getElementById("tblEventDtls").rows.length);	
		var Cell1 = newRow.insertCell(-1);
		Cell1.className = "tds";
	   	Cell1.align="center";
   		var Cell2 = newRow.insertCell(-1);
   		Cell2.className = "tds";
	   	Cell2.align="center";
   		var Cell3 = newRow.insertCell(-1);
   		Cell3.className = "tds";
	   	Cell3.align="center";
   		var Cell4 = newRow.insertCell(-1);
   		Cell4.className = "tds";
	   	Cell4.align="center";
   		var Cell5 = newRow.insertCell(-1);
   		Cell5.className = "tds";
	   	Cell5.align="center";
   		var Cell6 = newRow.insertCell(-1);
   		Cell6.className = "tds";
	   	Cell6.align="center";
   		var Cell7 = newRow.insertCell(-1);
   		Cell7.className = "tds";
	   	Cell7.align="center";
  		
   		EvtgridCount = parseInt(rowCnt);
   		

   		Cell1.innerHTML = '<select name="cmbEvent" id="cmbEvent'+Number(rowCnt)+'" onchange="EventDtls(this,'+rowCnt+')"><option value="-1">--Select--</option>' +LISTEVENTS +'</select>';
   		if(payComm == '5THPAYCOMSN'){
   			Cell2.innerHTML = '<select name="cmbPayScaleEvent" id="cmbPayScaleEvent'+Number(rowCnt)+'"><option value="-1">--Select--</option>' +LIST5THPAYSCALE +'</select>';
   		}else{
   			Cell2.innerHTML = '<select name="cmbPayScaleEvent" id="cmbPayScaleEvent'+Number(rowCnt)+'"><option value="-1">--Select--</option>' +LIST6THPAYSCALE +'</select>';
   		}
   		Cell3.innerHTML = '<input type="text" name="txtBasic" id="txtBasic'+Number(rowCnt)+'" size="20" onkeypress="numberFormat(event);"   style="width:60px" />';
   		Cell4.innerHTML = '<input type="text" name="txtEventDP" id="txtEventDP'+Number(rowCnt)+'" size="20" maxlength="7" onkeypress="numberFormat(event);"   style="width:60px" />';
   		Cell5.innerHTML = '<input type="text" name="txtDateOfEventFrom"  id="txtDateOfEventFrom'+Number(rowCnt)+'" style="width:90px"  onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfStartingService,this,\'From date should be greater than Date Of Joining Government Service\',\'<\');validateEventFromDate('+Number(rowCnt)+');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtDateOfEventFrom'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
   		Cell6.innerHTML = '<input type="text" name="txtDateOfEventTo"  id="txtDateOfEventTo'+Number(rowCnt)+'" style="width:90px"  onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);compareDates(this,txtDateOfRetiremt,\'To date should be less than Retirement date\',\'<\');validateEventFromDate('+Number(rowCnt)+');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  style="width:16px" onClick="window_open(event,\'txtDateOfEventTo'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
   		Cell7.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblEventDtls")\'/><input type="hidden" id="pk'+Number(rowCnt)+'" name="pk'+Number(rowCnt)+'">';
   		document.getElementById("hidEventGridSize").value = Number(rowCnt)+1;
	   	   		
}
/*function window_open(val,x,y,afterDateSelect,dateInputParams){
    var newWindow;
 
    if(afterDateSelect == undefined) {
		afterDateSelect = '';
	}
	glbAfterDateSelect = afterDateSelect;
   
    var urlstring = "common/calendarDppf.jsp?requestSent=" +val;
    
    dateChkInputs = dateInputParams;
    var X = window.event.screenX  - window.event.offsetX;
    var Y = window.event.screenY  + (20 - window.event.offsetY);	    
  
    var urlstyle = 'height=230,width=315,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+Y+',left='+X;
	
	newWindow = displayModalDialog(urlstring,'Calendar',urlstyle);
}*/

function serBrkTableAddRow()
{
	var rowCnt = document.getElementById("hidSrvcBrkGridSize").value;
	var newRow =  document.getElementById("tblServBrkDtls").insertRow(document.getElementById("tblServBrkDtls").rows.length);	
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
	Cell4.className = "tds";
   	Cell4.align="center"; 
		
		SergridCount = parseInt(rowCnt);
		Cell1.innerHTML = '<select name="cmbTypeofBrk" id="cmbTypeofBrk'+Number(rowCnt)+'" onchange="showSrvcBrkOtherReason('+Number(rowCnt)+')"><option value="-1">--Select--</option>'+SRVCBRKTYPE+'</select><div style="display: none;" id="divSrvBreakReason'+Number(rowCnt)+'">	Reason :<input type="text" name="txtSrvBreakOtherReason" id="txtSrvBreakOtherReason'+Number(rowCnt)+'"/></div>';
		Cell2.innerHTML = '<input type="text" name="txtDateOfBrkFrom"  id="txtDateOfBrkFrom'+Number(rowCnt)+'" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfStartingService,this,\'From date should be greater than Date Of Joining Government Service\',\'<\');validateWithFromDate('+Number(rowCnt)+');calTotalSrvcBrk('+Number(rowCnt)+');setTotalDaysOfSrvcBrk(\'txtDateOfBrkFrom\',\'txtDateOfBrkTo\');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtDateOfBrkFrom'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
		Cell3.innerHTML = '<input type="text" name="txtDateOfBrkTo"  id="txtDateOfBrkTo'+Number(rowCnt)+'" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);compareDates(this,txtDateOfRetiremt,\'To date should be less than Retirement date\',\'<\');validateWithFromDate('+Number(rowCnt)+');calTotalSrvcBrk('+Number(rowCnt)+');setTotalDaysOfSrvcBrk(\'txtDateOfBrkFrom\',\'txtDateOfBrkTo\');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtDateOfBrkTo'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
		Cell4.innerHTML = '<input type="text" name="txtRemarks" id="txtRemarks'+Number(rowCnt)+'" size="40"  style="width:60px" />';
		Cell5.innerHTML = '<input type="hidden" id="hidDays'+Number(rowCnt)+'" name="hidDays'+Number(rowCnt)+'"><img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick="calTotalSrvcBrk('+Number(rowCnt)+');RemoveTableRow(this,\'tblServBrkDtls\');adjustTotalDays('+Number(rowCnt)+');setTotalDaysOfSrvcBrk(\'txtDateOfBrkFrom\',\'txtDateOfBrkTo\');calQualifyingSrvc();"/><input type="hidden" id="Spk'+Number(rowCnt)+'" name="Spk'+Number(rowCnt)+'">';
		
		document.getElementById("hidSrvcBrkGridSize").value = Number(rowCnt)+1;
}

function qylServTableAddRow()
{
	var rowCnt = document.getElementById("hidQlySrvcGridSize").value;
	var newRow =  document.getElementById("tblQylServDtls").insertRow(document.getElementById("tblQylServDtls").rows.length);	
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
	Cell4.className = "tds";
   	Cell4.align="center"; 
   	var Cell6 = newRow.insertCell(5);
	Cell5.className = "tds";
   	Cell5.align="center"; 
		
		SergridCount = parseInt(rowCnt);
		Cell1.innerHTML = '<input type="text" name="grNo" id="grNo'+Number(rowCnt)+'" size="60"  style="width:80px"/></div>';
		Cell2.innerHTML = '<input type="text" name="grDate"  id="grDate'+Number(rowCnt)+'" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'grDate'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
		Cell3.innerHTML = '<input type="text" name="txtDateOfQlyFrom"  id="txtDateOfQlyFrom'+Number(rowCnt)+'" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);calTotalQlyServ('+Number(rowCnt)+');setTotalDaysOfQlyServ(\'txtDateOfQlyFrom\',\'txtDateOfQlyTo\');validateQlyFromDate('+Number(rowCnt)+');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtDateOfQlyFrom'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
		Cell4.innerHTML = '<input type="text" name="txtDateOfQlyTo"  id="txtDateOfQlyTo'+Number(rowCnt)+'" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);calTotalQlyServ('+Number(rowCnt)+');setTotalDaysOfQlyServ(\'txtDateOfQlyFrom\',\'txtDateOfQlyTo\');validateQlyFromDate('+Number(rowCnt)+');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtDateOfQlyTo'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
		Cell5.innerHTML = '<input type="text" name="txtQlyRemarks" id="txtQlyRemarks'+Number(rowCnt)+'" size="40"  style="width:60px" />';
		Cell6.innerHTML = '<input type="hidden" id="hidQylDays'+Number(rowCnt)+'" name="hidQylDays'+Number(rowCnt)+'"><img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick="calTotalQlyServ('+Number(rowCnt)+');RemoveTableRow(this,\'tblQylServDtls\');adjustTotalQlyDays('+Number(rowCnt)+');setTotalDaysOfQlyServ(\'txtDateOfQlyFrom\',\'txtDateOfQlyTo\');calQualifyingSrvc();"/><input type="hidden" id="Spk'+Number(rowCnt)+'" name="Spk'+Number(rowCnt)+'">';
		
		document.getElementById("hidQlySrvcGridSize").value = Number(rowCnt)+1;
}


function provisionalPaidTableAddRow()
{
	
	var ddoOffc=document.getElementById("txtDdoOfficeName").value;
	var Row_ID_Prov = document.getElementById("hidPrvsnlPnsnGridSize").value;
	var srNo = document.getElementById("tblPrvsnlPnsnDtls").rows.length;
	var newRow =  document.getElementById("tblPrvsnlPnsnDtls").insertRow(document.getElementById("tblPrvsnlPnsnDtls").rows.length);	
	

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
   /*	var Cell9 = newRow.insertCell(8);
	Cell9.className = "tds";
   	Cell9.align="center";
   	var Cell10 = newRow.insertCell(9);
   	Cell10.className = "tds";
   	Cell10.align="center";*/
		
   		provisionalCount = parseInt(Row_ID_Prov);
   		//alert(provisionalCount);
   		if(ddoOffc=='sanjay thane office'){
   		if(provisionalCount<=6){
   		Cell1.innerHTML = '<input type="hidden" name="hdnProvPnsnDtlsId" id="hdnProvPnsnDtlsId'+Row_ID_Prov+'"/><select name="cmbSancAuthName" id="cmbSancAuthName'+Row_ID_Prov+'" onblur="validateAuthorityName();"><option value="-1">--Select--</option>'+LISTSANAUTHO+'</select>';
   	   	Cell2.innerHTML = '<input type="text" name="txtSancAuthNoPro" id="txtSancAuthNoPro'+Row_ID_Prov+'"  onfocus="onFocus(this)" size="10"  onblur="onBlur(this);" maxlength="20"/>';
   		Cell3.innerHTML = '<input type="text" name="txtSanctionedDate" id="txtSanctionedDate'+Row_ID_Prov+'" maxlength="10" size="10"  onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtSanctionedDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
   		/*Cell4.innerHTML = '<input type="text" name="txtApplnFromDate" id="txtApplnFromDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);validateAddFromAndToDate('+Row_ID_Prov+');"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtApplnFromDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
   		Cell5.innerHTML = '<input type="text" name="txtApplnToDate" id="txtApplnToDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);validateAddFromAndToDate('+Row_ID_Prov+');"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtApplnToDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
   		*/Cell4.innerHTML = '<select name="cmbBillType" id="cmbBillType'+Row_ID_Prov+'" ><option value="-1">--Select--</option>'+LISTBILLTYPE+'</select>';
   		Cell5.innerHTML = '<input type="text" name="txtPaidAmount" id="txtPaidAmount'+Row_ID_Prov+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   		Cell6.innerHTML = '<input type="text" name="txtProVoucherNo" id="txtProVoucherNo'+Row_ID_Prov+'" size="10"  maxlength="20"/>';      	
   	   	Cell7.innerHTML = '<input type="text" name="txtProVoucherDate" id="txtProVoucherDate'+Row_ID_Prov+'" maxlength="10" size="10"  onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtProVoucherDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';  
   	  	Cell8.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblPrvsnlPnsnDtls\')" /> ';
   		document.getElementById("hidPrvsnlPnsnGridSize").value = Number(Row_ID_Prov)+1;  		
   		}
   		else
   			{return false;
   			}
   		}
   		else{
   			Cell1.innerHTML = '<input type="hidden" name="hdnProvPnsnDtlsId" id="hdnProvPnsnDtlsId'+Row_ID_Prov+'"/><select name="cmbSancAuthName" id="cmbSancAuthName'+Row_ID_Prov+'" onblur="validateAuthorityName();"><option value="-1">--Select--</option>'+LISTSANAUTHO+'</select>';
   	   	   	Cell2.innerHTML = '<input type="text" name="txtSancAuthNoPro" id="txtSancAuthNoPro'+Row_ID_Prov+'"  onfocus="onFocus(this)" size="10"  onblur="onBlur(this);" maxlength="20"/>';
   	   		Cell3.innerHTML = '<input type="text" name="txtSanctionedDate" id="txtSanctionedDate'+Row_ID_Prov+'" maxlength="10" size="10"  onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtSanctionedDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
   	   		/*Cell4.innerHTML = '<input type="text" name="txtApplnFromDate" id="txtApplnFromDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);validateAddFromAndToDate('+Row_ID_Prov+');"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtApplnFromDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
   	   		Cell5.innerHTML = '<input type="text" name="txtApplnToDate" id="txtApplnToDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);validateAddFromAndToDate('+Row_ID_Prov+');"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtApplnToDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
   	   		*/Cell4.innerHTML = '<select name="cmbBillType" id="cmbBillType'+Row_ID_Prov+'" ><option value="-1">--Select--</option>'+LISTBILLTYPE+'</select>';
   	   		Cell5.innerHTML = '<input type="text" name="txtPaidAmount" id="txtPaidAmount'+Row_ID_Prov+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	   		Cell6.innerHTML = '<input type="text" name="txtProVoucherNo" id="txtProVoucherNo'+Row_ID_Prov+'" size="10"  maxlength="20"/>';      	
   	   	   	Cell7.innerHTML = '<input type="text" name="txtProVoucherDate" id="txtProVoucherDate'+Row_ID_Prov+'" maxlength="10" size="10"  onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtProVoucherDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';  
   	   	  	Cell8.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblPrvsnlPnsnDtls\')" /> ';
   	   		document.getElementById("hidPrvsnlPnsnGridSize").value = Number(Row_ID_Prov)+1;  		
   	   		
   			
   		}
}
function foreignServTableAddRow()
{
	var rowCnt = document.getElementById("hidForeignServicesGridSize").value;
	var srNo = document.getElementById("tblForeignServDtls").rows.length;
	var newRow =  document.getElementById("tblForeignServDtls").insertRow(document.getElementById("tblForeignServDtls").rows.length);	

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
   		
	provisionalCount = parseInt(rowCnt);
	Cell1.innerHTML = srNo;
	Cell2.innerHTML = '<input type="text" name="txtFromDateForeignServ" id="txtFromDateForeignServ'+Number(rowCnt)+'" size="10" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"	onKeyPress="numberFormat(event);" onfocus="onFocus(this)"	onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" width="20" onClick="window_open(event,\'txtFromDateForeignServ'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
	Cell3.innerHTML = '<input type="text" name="txtToDateForeignServ" id="txtToDateForeignServ'+Number(rowCnt)+'" size="10" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"	onKeyPress="numberFormat(event);" onfocus="onFocus(this)"	onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" width="20" onClick="window_open(event,\'txtToDateForeignServ'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
	Cell4.innerHTML = '<input type="text" name="txtTotalAmt" id="txtTotalAmt'+Number(rowCnt)+'" onfocus="onFocus(this)" size="10" maxlength="10" onkeypress="digitFormat(this);" onblur="onBlur(this);" />';		
	Cell5.innerHTML = '<input type="text" name="txtPaidAmt" id="txtPaidAmt'+Number(rowCnt)+'" onfocus="onFocus(this)" size="10" maxlength="10" onkeypress="digitFormat(this);" onblur="onBlur(this);" />';
	Cell6.innerHTML = '<input type="text" name="txtChallanNo" id="txtChallanNo'+Number(rowCnt)+'" onfocus="onFocus(this)" size="13" maxlength="10" onblur="onBlur(this);" />';
	Cell7.innerHTML = '<input type="text" name="txtChallanDate" id="txtChallanDate'+Number(rowCnt)+'" size="10" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"	onKeyPress="numberFormat(event);" onfocus="onFocus(this)"	onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" width="20" onClick="window_open(event,\'txtChallanDate'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
	Cell8.innerHTML = '<input type="text" name="txtDeptOffName" id="txtDeptOffName'+Number(rowCnt)+'" onfocus="onFocus(this)" size="30" maxlength="10" onblur="onBlur(this);" />';
	Cell9.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick="RemoveTableRow(this,\'tblForeignServDtls\');"/>';
	
	document.getElementById("hidForeignServicesGridSize").value = Number(rowCnt)+1;
}
function calTotal()
{
	
	var TotalAmt=0;
	var Count = document.getElementById("hidCalcGridSize").value;
	
	var table = document.getElementById("tblAvgPayCalc");
	var length = table.rows.length;
    length=length-1;
  
  try
	{
		for(var i=0;i<=Count;i++)
		{
			try
			{
				var amount = document.getElementById("txtTotal"+i).value;
				
				
				TotalAmt=Number(TotalAmt)+Number(amount);
			}
			catch(ex)
			{
			}
		}
		document.getElementById("txtGrandTotal").value=TotalAmt;
		document.getElementById("txtAvgPay").value=Math.ceil(TotalAmt/length);

	}
	catch(ex)
	{
	}
	
}

//added by aditya
function calAvgTotal()
{
	
	var TotalAmt=0;
	var Count = document.getElementById("hidCalcGridSize").value;
	
	var table = document.getElementById("tblAvgPayCalc");
	var length = table.rows.length;
    length=length-1;
  
	var BasicPay=document.getElementById("txtBasicPay").value;
	
	
	//var payCommission= document.getElementById("cmbPayComsn").value;
	var index = document.PensionInwardForm.cmbPayScale.selectedIndex ;
	var payScale = document.PensionInwardForm.cmbPayScale[index].text ;
    try
	{
		for(var i=0;i<=Count;i++)
		{
			try
			{
				
				
				document.getElementById("txtAvgPayBasic"+i).value=BasicPay;
				
				if(document.PensionInwardForm.cmbPayScale != "-1")
				{
					if(payScale != "" && payScale != -1)
						{
							var tempArray = payScale.split("(");
							var gradePay = tempArray[1].split(")")[0].trim(); 
							document.getElementById("txtAvgPayDP"+i).value = gradePay;
							
						}
				}
				
				var basic=document.getElementById("txtAvgPayBasic"+i).value;
				
				var dp=document.getElementById("txtAvgPayDP"+i).value;
				
				var npa =0;
				//if(document.getElementById("chkBoxNPA").checked == true)
				//{
					npa=document.getElementById("txtNPA"+i).value;
				//}
				document.getElementById("txtTotal"+i).value=Number(basic)+Number(dp)+Number(npa);
			
				var amount = document.getElementById("txtTotal"+i).value;
				TotalAmt=Number(TotalAmt)+Number(amount);
			}
			catch(ex)
			{
			}
		}
		document.getElementById("txtGrandTotal").value=TotalAmt;
		document.getElementById("txtAvgPay").value=Math.ceil(TotalAmt/length);

	}
	catch(ex)
	{
	}
}
//added by aditya

function avgPayCalcTableAddRow()
{
	var emolumentFromDt = document.getElementById("txtEmolumentFromDate").value;
	var rowCnt = document.getElementById("hidCalcGridSize").value;
	var length = document.getElementById("tblAvgPayCalc").rows.length;
	var newRow =  document.getElementById("tblAvgPayCalc").insertRow(document.getElementById("tblAvgPayCalc").rows.length);	
	var Cell1 = newRow.insertCell(0);
	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
	Cell2.className = "tds";
   	Cell2.align="center";
/*	var Cell2 = newRow.insertCell(1);
	Cell2.className = "tds";
   	Cell2.align="center"; */
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
	/*var Cell7 = newRow.insertCell(5);
	Cell7.className = "tds";
   	Cell7.align="center";*/
   	
   	Cell1.innerHTML = length;
   	Cell2.innerHTML = '<input type="text" name="txtPeriodFromDate"  id="txtPeriodFromDate'+Number(rowCnt)+'" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);chkPeriodFromDateWithEmolumentFromDate();chkPeriodFromDateWithEmolumentToDate('+Number(rowCnt)+');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtPeriodFromDate'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
  //Cell2.innerHTML = '<input type="text" name="txtPeriodToDate"  id="txtPeriodToDate'+Number(rowCnt)+'" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtPeriodToDate'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
	Cell3.innerHTML = '<input type="text" name="txtAvgPayBasic" id="txtAvgPayBasic'+Number(rowCnt)+'" onkeypress="amountFormat(this);" onblur="validateBasicPay(this);setTotal('+Number(rowCnt)+');setValidAmountFormat(this);calTotal();"   style="text-align: right;width:90px" />';
	Cell4.innerHTML = '<input type="text" name="txtAvgPayDP" id="txtAvgPayDP'+Number(rowCnt)+'"onkeypress="amountFormat(this);" onblur="setTotal('+Number(rowCnt)+');setValidAmountFormat(this);calTotal();"    style="text-align: right;width:90px"/>';
	if(document.getElementById("chkBoxNPA").checked == true)
	{
		Cell5.innerHTML = '<input type="text"  name="txtNPA" id="txtNPA'+Number(rowCnt)+'" onkeypress="amountFormat(this);" onblur="setTotal('+Number(rowCnt)+');setValidAmountFormat(this);calTotal();"  style="text-align: right;width:90px" />';
	}
	else
	{
		Cell5.innerHTML = '<input type="text" readOnly="readOnly" name="txtNPA" id="txtNPA'+Number(rowCnt)+'" onkeypress="amountFormat(this);" onblur="setTotal('+Number(rowCnt)+');setValidAmountFormat(this);calTotal();"  style="text-align: right;width:90px" />';
	}
	Cell6.innerHTML = '<input type="text" name="txtTotal" id="txtTotal'+Number(rowCnt)+'" onkeypress="amountFormat(this);" onBlur="setValidAmountFormat(this)" onblur="onBlur(this);"  style="text-align: right;width:90px" readonly="readonly" /><input type="hidden" id="Spk'+Number(rowCnt)+'" name="Spk'+Number(rowCnt)+'">';
	/*if(document.getElementById("tblAvgPayCalc").rows.length == 2)
	{
		Cell7.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' /><input type="hidden" id="Spk'+Number(rowCnt)+'" name="Spk'+Number(rowCnt)+'">';
	}
	else
	{
		Cell7.innerHTML = '<input type="hidden" id="Spk'+Number(rowCnt)+'" name="Spk'+Number(rowCnt)+'">';
	}*/
	
	document.getElementById("hidCalcGridSize").value = Number(rowCnt)+1;
}
function chkPeriodFromDateWithEmolumentToDate(count)
{
	var emolumentToDt = document.getElementById("txtEmolumentToDate").value;
	var emolumentToDtArr = emolumentToDt.split("/");
	var periodFromDt = document.getElementById("txtPeriodFromDate"+count).value;
	var periodFromDtArr = periodFromDt.split("/");
	
	//check with previous values of period from date
	var fromDateArr  = document.getElementsByName("txtPeriodFromDate");
	if(fromDateArr.length > 1)
	{
		var tempDateObj;
		for(var i=0;i<fromDateArr.length;i++)
		{
			if(i==(fromDateArr.length-2))
			{
				tempDateObj = fromDateArr[i];
				break;
			}
		}
		if(Number(count) > 0)
		{			
		if(compareTwoDates(document.getElementById("txtPeriodFromDate"+count),fromDateArr[Number(count - 1)],"<") == true || compareTwoDates(document.getElementById("txtPeriodFromDate"+count),fromDateArr[Number(count - 1)],"=") == true)
		{
			alert('Please enter from date which is greater than previous from date.');
	    	document.getElementById("txtPeriodFromDate"+count).value="";
	    	document.getElementById("txtPeriodFromDate"+count).focus();
	    	return;
		}
		}
	}
	
    var emolumentToDate = new Date();
    emolumentToDate.setFullYear(emolumentToDtArr[2], emolumentToDtArr[1], emolumentToDtArr[0]);
    var periodFromDate = new Date();
    periodFromDate.setFullYear(periodFromDtArr[2], periodFromDtArr[1], periodFromDtArr[0]);
    
  //check : wether period from date is start with 1 or not
    if(periodFromDt != ""){
    if(periodFromDtArr[0] != "01")
	{
    	alert('Please enter from date which starts with 1st.');
    	document.getElementById("txtPeriodFromDate"+count).value="";
    	return;
    	
	}
    if(periodFromDate > emolumentToDate)
    {
    	alert('You cannot enter from date which is greater than emolument to date.');
    	document.getElementById("txtPeriodFromDate"+count).value="";
    	return;
    }
    }
}
function chkPeriodFromDateWithEmolumentFromDate()
{
	var emolumentFromDt = document.getElementById("txtEmolumentFromDate").value;
	var emolumentFromDtArr = emolumentFromDt.split("/");
	var periodFromDt = document.getElementById("txtPeriodFromDate0").value;
	var periodFromDtArr = periodFromDt.split("/");
    var emolumentFromDate = new Date();
    emolumentFromDate.setFullYear(emolumentFromDtArr[2], emolumentFromDtArr[1], emolumentFromDtArr[0]);
    var periodFromDate = new Date();
    periodFromDate.setFullYear(periodFromDtArr[2], periodFromDtArr[1], periodFromDtArr[0]);
    if(periodFromDate > emolumentFromDate)
    {
    	alert('You cannot enter from date which is greater than emolument from date.');
    	document.getElementById("txtPeriodFromDate0").value="";
    	document.getElementById("txtPeriodFromDate0").focus();
    	return;
    }
}
function adjustCalTotal(count)
{
	var tempSum = Number(document.getElementById("txtAvgPayBasic"+count).value) + Number(document.getElementById("txtAvgPayDP"+count).value) + Number(document.getElementById("txtNPA"+count).value);
	document.getElementById("txtGrandTotal").value =  Number(document.getElementById("txtGrandTotal").value) - tempSum;
	var tempVal = Number(document.getElementById("tblAvgPayCalc").rows.length) - 2;
	document.getElementById("txtAvgPay").value = Number(document.getElementById("txtGrandTotal").value)/ Number(tempVal);
	
}
function setTotal(Count)
{
	
	var basic=document.getElementById("txtAvgPayBasic"+Count).value;
	var dp=document.getElementById("txtAvgPayDP"+Count).value;
	var npa=document.getElementById("txtNPA"+Count).value;
	//Added by shraddha for 7th Pay comm
	var PayComsn = document.getElementById("cmbPayComsn").value;
	
	
	//Added by shraddha on 09-08-2016
	var tot=Number(basic)+Number(dp)+Number(npa);
	var totalAmt=Number(basic)+Number(dp);
	
	if(tot > 85000 && PayComsn != '7THPAYCOMSN'){
	
	alert ("Sum of Pay In Payband ,GP/DP and NPA cannot be greater than 85000. Kindly enter NPA amount less than 35% of Payband+GP/DP");
	npa=85000-totalAmt;
	//alert(npa);
	document.getElementById("txtNPA"+Count).value=npa;	
	document.getElementById("txtTotal"+Count).value=Number(basic)+Number(dp)+Number(npa);	
	return false;
	}
	else if (tot==85000 && PayComsn != '7THPAYCOMSN'){
		
		document.getElementById("txtTotal"+Count).value=Number(basic)+Number(dp)+Number(npa);		
		
	}
	
	else {
	if(validateNpa(Count)==true){
		
	try{
		 basic=document.getElementById("txtAvgPayBasic"+Count).value;
		 dp=document.getElementById("txtAvgPayDP"+Count).value;
		
		 npa=document.getElementById("txtNPA"+Count).value;
		//alert("npa************"+npa);
		//Added by shraddha on 09-08-2016
		 tot=Number(basic)+Number(dp)+Number(npa);
		
		/*if(tot > 85000){
			alert("Sum of Pay In Payband ,GP/DP and NPA cannot be greater than 85000"  );
			document.getElementById("txtTotal"+Count).value=85000;	
			return false;
		}
	
		else {*/
			document.getElementById("txtTotal"+Count).value=Number(basic)+Number(dp)+Number(npa);	
		//}
	}
	catch(e){
	}
	}
	}

	//alert( npa);
	//alert(document.getElementById("txtTotal"+Count).value);
return true;
}//else return false;
	//}
function validPensionCasePayService()
{
	
		if(IsEmptyFun("cmbPayScale",LASTPAYSCALE,'1')==false)
		{
			return false;
		}
		/*if(IsEmptyFun("txtDP",DP,'1')==false)
		{
			return false;
		}
		if(IsEmptyFun("txtGradePay",GRADEPAY,'1')==false)
		{
			return false;
		}*/
		if(IsEmptyFun("txtBasicPay",BASICPAY,'1')==false)
		{
			return false;
		}		
				
		 var eventCntLength=document.getElementById("hidEventGridSize").value;
		
		 if(eventCntLength>1)
		 {
			for(var rowEventCnt=0;rowEventCnt<Number(eventCntLength);rowEventCnt++)
			{		
				try
				{	
					
					if(IsEmptyFun("cmbEvent"+rowEventCnt,EVENT,'1')==false)
					{
						return false;
					}
					else if(IsEmptyFun("cmbPayScaleEvent"+rowEventCnt,PAYSCALE,'1')==false)
					{
						return false;
					}					
					else if(IsEmptyFun("txtBasic"+rowEventCnt,BASIC,'1')==false)
					{
						return false;
					}
					else if(IsEmptyFun("txtDateOfEventFrom"+rowEventCnt,FROM,'1')==false)
					{
						return false;
					}
				else if(document.getElementById("cmbEvent"+rowEventCnt).value==10040)
					{
						if(IsEmptyFun("txtReason"+rowEventCnt,'Reason required','1')==false)
						{
							return false;
						}
						
					}
					else if(IsEmptyFun("txtDateOfEventTo"+rowEventCnt,TO,'1')==false)
					{
						return false;
					}
					 
				}
				catch(ex)
				{
					
				}
			}
		 }
		 validateAuthorityName();
		 var Row_ID_Prov = document.getElementById("hidPrvsnlPnsnGridSize").value;
			
			for(var i=0;i<Number(Row_ID_Prov);i++){
				try{				
					if(IsEmptyFun("cmbSancAuthName"+i,"Please Select Sanctioning Authority Name",'1')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtSancAuthNoPro"+i,"Please enter Sanctioning Authority No",'1')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtSanctionedDate"+i,"Please enter Sanctioned Date",'1')==false)
					{
						return false;
					}
					/*if(IsEmptyFun("txtApplnFromDate"+i,"Please enter Application From Date",'1')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtApplnToDate"+i,"Please enter Application To Date",'1')==false)
					{
						return false;
					}*/
					if(IsEmptyFun("cmbBillType"+i,"Please Select Bill Type",'1')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtPaidAmount"+i,"Please enter Amount",'1')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtProVoucherNo"+i,"Please enter Voucher No",'1')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtProVoucherDate"+i,"Please enter Voucher Date",'1')==false)
					{
						return false;
					}			
				}catch(e){
				}
			}
		 
		 var serBrkCntLength=document.getElementById("hidSrvcBrkGridSize").value;
		 var brkType;
		 if(serBrkCntLength>=1)
		 {
			 //alert("Inside if");
			 
			for(var rowServCnt=0;rowServCnt<Number(serBrkCntLength);rowServCnt++)
			{		
				try
				{				 
					if(document.getElementById("cmbTypeofBrk"+rowServCnt).value == '-1'){
						alert("Please select service Break Type in Qualifying Services");
						return false;
						}
					
					
					if(document.getElementById("txtDateOfBrkFrom"+rowServCnt).value == ''){
						alert("Please Enter Service Break From Date in Qualifying Services");
						return false;
						}
					
					if(document.getElementById("txtDateOfBrkTo"+rowServCnt).value == ''){
						alert("Please Enter Service Break To Date in Qualifying Services");
						return false;
						}
					
					
					brkType = document.getElementById("cmbTypeofBrk"+rowServCnt).value;
					if(brkType == '10036'){
						
						if(document.getElementById("txtSrvBreakOtherReason"+rowServCnt).value == ''){
							alert("Please Enter Reason in Qualifying Services");
							return false;
							}
					}
					if(compareTwoDates(document.getElementById("txtDateOfStartingService"),document.getElementById("txtDateOfBrkFrom"+rowServCnt),"<") == false){
						alert('From date should be greater than Date Of Joining Government Service');
						document.getElementById("txtDateOfBrkFrom"+rowServCnt).value = "";
						document.getElementById("txtDateOfBrkFrom"+rowServCnt).focus();
						return false;
					}
					if(compareTwoDates(document.getElementById("txtDateOfBrkTo"+rowServCnt),document.getElementById("txtDateOfRetiremt"),"<") == false){
						alert('To date should be less than Retirement date');
						document.getElementById("txtDateOfBrkTo"+rowServCnt).value = "";
						document.getElementById("txtDateOfBrkTo"+rowServCnt).focus();
						return false;
					}
				}
				catch(ex)
				{
					
				}
			}
		 }
		 
		 
		 var qlyServCntLength=document.getElementById("hidQlySrvcGridSize").value;
		// alert("qlyServCntLength****"+qlyServCntLength);
		 if(qlyServCntLength>=1)
		 {
			// alert("Inside if");
			 
			for(var rowQlyServCnt=0;rowQlyServCnt<Number(qlyServCntLength);rowQlyServCnt++)
			{		
				try
				{		
				
					if(document.getElementById("grNo"+rowQlyServCnt).value == ''){
					alert("Please Enter GR No.");
					return false;
					}
					if(document.getElementById("grDate"+rowQlyServCnt).value == ''){
						alert("Please Enter GR Date in Additional Qualifying Services");
						return false;
						}
					if(document.getElementById("txtDateOfQlyFrom"+rowQlyServCnt).value == ''){
						alert("Please Enter Qualifying Service From Date in Additional Qualifying Services");
						return false;
						}
					if(document.getElementById("txtDateOfQlyTo"+rowQlyServCnt).value == ''){
						alert("Please Enter Qualifying Service To Date in Additional Qualifying Services");
						return false;
						}
					if(document.getElementById("txtQlyRemarks"+rowQlyServCnt).value == ''){
						alert("Please Enter Remarks in Additional Qualifying Services");
						return false;
						}
				}
				catch(ex)
				{
					
				}
			}
		 }
		 
		 
	
		 
		 var CalcGridLength=document.getElementById("hidCalcGridSize").value;
		 var DOR = document.getElementById("txtDateOfRetiremt").value;
		 var DPConsideringDate = "01/08/2004";
		 var payComm = document.getElementById("cmbPayComsn").value;
		 //Added by shraddha on 22-3-2016
		 var flagPen=document.getElementById("flagPen").value;
		 var pensionCaseType=document.getElementById("pensionCaseType").value;
		 
		 //alert("CalcGridLength*****"+CalcGridLength);
		 if(CalcGridLength>=1)
		 {
			 for(var rowpayCnt=0;rowpayCnt<Number(CalcGridLength);rowpayCnt++)
			 {		
				 //alert("Inside for");
				 try
				 {		 
					 
					// if(IsEmptyFun("txtPeriodFromDate"+rowServCnt,FROM,'1')==false)
						 if(document.getElementById("txtPeriodFromDate"+rowpayCnt).value == '')
					 {
						 alert("Please enter Pay Details");
						 document.getElementById("txtPeriodFromDate"+rowpayCnt).focus();
						 return false;
					 }
				 
					 else if(IsEmptyFun("txtAvgPayBasic"+rowpayCnt,BASICPAY,'1')==false)
					 {
						 return false;
						 
					 }		
				 return true;
				 }
				 catch(ex)
				 {

				 }
			 }
		 }
		 
		 if(IsEmptyFun("txtBasicPay",BASICPAY,'1')==false)
			{
				return false;
			}
		 else{
			 
			 return true;
			 
		 }
}

function EventDtls(obj,rowCnt){
	var event=obj.value;
	
	if(event==10040){
		
		document.getElementById("txtDateOfEventTo"+rowCnt).style.display="inline";
		document.getElementById("imgDateOfEventTo"+rowCnt).style.display="inline";
		
	}
	else
	{
		document.getElementById("txtDateOfEventTo"+rowCnt).style.display="inline";
		document.getElementById("imgDateOfEventTo"+rowCnt).style.display="inline";
			
	}

}

function validateWithFromDate(Count)
{
	//alert("count "+Count);
	var fromDate = document.getElementById("txtDateOfBrkFrom"+Count).value;
	var toDate = document.getElementById("txtDateOfBrkTo"+Count).value;
	if(fromDate != "" )
	{
		var fromDateArr = fromDate.split("/");
		var fromDateDay = fromDateArr[0];
		var fromDateMonth = fromDateArr[1];
		var fromDateYear = fromDateArr[2];
		
		var toDateArr = toDate.split("/");
		var toDateDay = toDateArr[0];
		var toDateMonth = toDateArr[1];
		var toDateYear = toDateArr[2];
		
		if(Number(toDateYear) < Number(fromDateYear))
		{
			alert('Non qualifying service to Date should be greater than Non qualifying service from Date.');
			document.getElementById("txtDateOfBrkTo"+Count).value ="";
			return;
			
		}
		else
		{
			if (Number(toDateYear) == Number(fromDateYear))
			{// alert("Number(toDateYear)"+Number(toDateYear));
				if(Number(fromDateMonth) > Number(toDateMonth))
				{
					//alert("Number(toDateMonth)"+Number(toDateMonth));
					alert('Non qualifying service to Date should be greater than Non qualifying service from Date.');
					document.getElementById("txtDateOfBrkTo"+Count).value ="";
					return;
				}
				else
				{
					if(Number(toDateMonth) == Number(fromDateMonth))
					{
					//	alert(" else Number(fromDateMonth)"+Number(fromDateMonth));
						if(Number(fromDateDay) > Number(toDateDay))
						{
							//alert(" else Number(toDateDay)"+Number(toDateDay));
							alert('Non qualifying service to Date should be greater than Non qualifying service from Date.');
							document.getElementById("txtDateOfBrkTo"+Count).value ="";
							return;
						}
						/*if(Number(fromDateDay) == Number(toDateDay))
						{
							alert('Non qualifying service to Date should be greater than Non qualifying service from Date.');
							document.getElementById("txtDateOfBrkTo"+Count).value ="";
							return;
						}*/
					}
				}
			}
		}
	}
}

function validateQlyFromDate(Count)
{
	//alert("count "+Count);
	var fromDate = document.getElementById("txtDateOfQlyFrom"+Count).value;
	var toDate = document.getElementById("txtDateOfQlyTo"+Count).value;
	if(fromDate != "" )
	{
		var fromDateArr = fromDate.split("/");
		var fromDateDay = fromDateArr[0];
		var fromDateMonth = fromDateArr[1];
		var fromDateYear = fromDateArr[2];
		
		var toDateArr = toDate.split("/");
		var toDateDay = toDateArr[0];
		var toDateMonth = toDateArr[1];
		var toDateYear = toDateArr[2];
		
		if(Number(toDateYear) < Number(fromDateYear))
		{
			alert('Qualifying service To Date should be greater than Qualifying service From Date.');
			document.getElementById("txtDateOfQlyTo"+Count).value ="";
			return;
			
		}
		else
		{
			if (Number(toDateYear) == Number(fromDateYear))
			{// alert("Number(toDateYear)"+Number(toDateYear));
				if(Number(fromDateMonth) > Number(toDateMonth))
				{
					//alert("Number(toDateMonth)"+Number(toDateMonth));
					alert('Qualifying service To Date should be greater than Qualifying service From Date.');
					document.getElementById("txtDateOfQlyTo"+Count).value ="";
					return;
				}
				else
				{
					if(Number(toDateMonth) == Number(fromDateMonth))
					{
					//	alert(" else Number(fromDateMonth)"+Number(fromDateMonth));
						if(Number(fromDateDay) > Number(toDateDay))
						{
							//alert(" else Number(toDateDay)"+Number(toDateDay));
							alert('Qualifying service To Date should be greater than Qualifying service From Date.');
							document.getElementById("txtDateOfQlyTo"+Count).value ="";
							return;
						}
						/*if(Number(fromDateDay) == Number(toDateDay))
						{
							alert('Non qualifying service to Date should be greater than Non qualifying service from Date.');
							document.getElementById("txtDateOfBrkTo"+Count).value ="";
							return;
						}*/
					}
				}
			}
		}
	}
}



function calTotalSrvcBrk(count)
{
	try{
		var brkFrom = document.getElementById("txtDateOfBrkFrom"+count).value;
		var brkTo = document.getElementById("txtDateOfBrkTo"+count).value;
		var dayDiff = 0;
		if(brkFrom == "" || brkTo == "")
		{
			return 0;
		}
		var brkFromDateArr = brkFrom.split("/");
		var brkFromDateDay = brkFromDateArr[0];
		var brkFromDateMonth = brkFromDateArr[1];
		var brkFromDateYear = brkFromDateArr[2];
		
		var brkToDateArr = brkTo.split("/");
		var brkToDate = new Date(brkToDateArr[1] +"/"+brkToDateArr[0]+"/"+ brkToDateArr[2]);
		brkToDate.setDate(brkToDate.getDate()+1);
		
		var brkToDateDay = brkToDate.getDate();
		var brkToDateMonth = brkToDate.getMonth()+1;
		var brkToDateYear = brkToDate.getFullYear();
		
		if(Number(brkFromDateYear) > Number(brkToDateYear))
		{
			alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
			document.getElementById("txtDateOfBrkTo"+count).value ="";
			document.getElementById("txtDateOfBrkTo"+count).focus();
		}
		else
		{
			if(Number(brkFromDateYear) == Number(brkToDateYear)) 
			{
				if(Number(brkFromDateMonth) > Number(brkToDateMonth))
				{
					alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
					document.getElementById("txtDateOfBrkTo"+count).value ="";
					document.getElementById("txtDateOfBrkTo"+count).focus();
				}
				else
				{
					if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
					{
						if(Number(brkFromDateDay) > Number(brkToDateDay))
						{
							alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
							document.getElementById("txtDateOfBrkTo"+count).value ="";
							document.getElementById("txtDateOfBrkTo"+count).focus();
						}
						else
						{
							if(Number(brkFromDateDay) ==  Number(brkToDateDay))
							{
								dayDiff = 0;
								monthDiff = 0;
								yearDiff = 0;
							}
							else // brkFromDateDay is less than brkToDateDay
							{
								dayDiff = Number(brkToDateDay) - Number(brkFromDateDay);
								monthDiff = 0;
								yearDiff = 0;
							}
						}
					}
					else // brkFromDateMonth is less than brkToDateMonth
					{
						monthDiff = Number(brkToDateMonth) - Number(brkFromDateMonth);
						yearDiff = 0;
						if(Number(brkToDateDay) == Number(brkFromDateDay))
						{
							dayDiff = 30 * monthDiff;
						}
						else
						{
							dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay));
						}
					}
				}
			}
			else // brkFromDateYear is less than brkToDateYear
			{
				yearDiff = Number(brkToDateYear) - Number(brkFromDateYear);
				if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
				{
					monthDiff = 12 * yearDiff;
					if(Number(brkFromDateDay) ==  Number(brkToDateDay))
					{
						dayDiff =  (yearDiff * 360);
					}
					else
					{
						if(Number(brkToDateDay) > Number(brkFromDateDay))
						{
							dayDiff = (Number(brkToDateDay)-Number(brkFromDateDay)) + (360 * (yearDiff))   ;
						}
						else//brkToDateDay is less than brkFromDateDay
						{
							dayDiff = (360 * (yearDiff)) - (Number(brkFromDateDay)-Number(brkToDateDay));
						}
					}
				}
				else 
				{
					if(Number(brkToDateMonth) > Number(brkFromDateMonth))
					{
						monthDiff = (12 * yearDiff) +  (Number(brkToDateMonth) - Number(brkFromDateMonth));
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)));
						}
						else
						{
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
							}
							else//brkToDateDay is less than brkFromDateDay
							{
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
							}
						}
						
					}
					else //brkToDateMonth is less than brkFromDateMonth
					{
						monthDiff = (12 * yearDiff) -  (Number(brkFromDateMonth) - Number(brkToDateMonth));
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)));
						}
						else
						{	
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								dayDiff = ((yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;								
								//dayDiff = ((yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
							}
							else//brkToDateDay is less than brkFromDateDay
							{	
								dayDiff = ((yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
								//dayDiff = ((yearDiff * 360) - (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
							}
						}
					}
				}
			}
		}
		document.getElementById("hidDays"+count).value = dayDiff; 
	}catch(e){
		
	}
	
}


function calTotalQlyServ(count)
{
	
	//alert()
	
	try{
		var brkFrom = document.getElementById("txtDateOfQlyFrom"+count).value;
		var brkTo = document.getElementById("txtDateOfQlyTo"+count).value;
		var dayDiff = 0;
		if(brkFrom == "" || brkTo == "")
		{
			return 0;
		}
		var brkFromDateArr = brkFrom.split("/");
		var brkFromDateDay = brkFromDateArr[0];
		var brkFromDateMonth = brkFromDateArr[1];
		var brkFromDateYear = brkFromDateArr[2];
		
		var brkToDateArr = brkTo.split("/");
		var brkToDate = new Date(brkToDateArr[1] +"/"+brkToDateArr[0]+"/"+ brkToDateArr[2]);
		brkToDate.setDate(brkToDate.getDate()+1);
		
		var brkToDateDay = brkToDate.getDate();
		var brkToDateMonth = brkToDate.getMonth()+1;
		var brkToDateYear = brkToDate.getFullYear();
		
		if(Number(brkFromDateYear) > Number(brkToDateYear))
		{
			alert('Qualifying service to date should be greater than or equal to Qualifying service from date.');
			document.getElementById("txtDateOfQlyTo"+count).value ="";
			document.getElementById("txtDateOfQlyTo"+count).focus();
		}
		else
		{
			if(Number(brkFromDateYear) == Number(brkToDateYear)) 
			{
				if(Number(brkFromDateMonth) > Number(brkToDateMonth))
				{
					alert('Qualifying service to date should be greater than or equal to Qualifying service from date.');
					document.getElementById("txtDateOfQlyTo"+count).value ="";
					document.getElementById("txtDateOfQlyTo"+count).focus();
				}
				else
				{
					if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
					{
						if(Number(brkFromDateDay) > Number(brkToDateDay))
						{
							alert('Qualifying service to date should be greater than or equal to Qualifying service from date.');
							document.getElementById("txtDateOfQlyTo"+count).value ="";
							document.getElementById("txtDateOfQlyTo"+count).focus();
						}
						else
						{
							if(Number(brkFromDateDay) ==  Number(brkToDateDay))
							{
								dayDiff = 0;
								monthDiff = 0;
								yearDiff = 0;
							}
							else // brkFromDateDay is less than brkToDateDay
							{
								dayDiff = Number(brkToDateDay) - Number(brkFromDateDay);
								monthDiff = 0;
								yearDiff = 0;
							}
						}
					}
					else // brkFromDateMonth is less than brkToDateMonth
					{
						monthDiff = Number(brkToDateMonth) - Number(brkFromDateMonth);
						yearDiff = 0;
						if(Number(brkToDateDay) == Number(brkFromDateDay))
						{
							dayDiff = 30 * monthDiff;
						}
						else
						{
							dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay));
						}
					}
				}
			}
			else // brkFromDateYear is less than brkToDateYear
			{
				yearDiff = Number(brkToDateYear) - Number(brkFromDateYear);
				if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
				{
					monthDiff = 12 * yearDiff;
					if(Number(brkFromDateDay) ==  Number(brkToDateDay))
					{
						dayDiff =  (yearDiff * 360);
					}
					else
					{
						if(Number(brkToDateDay) > Number(brkFromDateDay))
						{
							dayDiff = (Number(brkToDateDay)-Number(brkFromDateDay)) + (360 * (yearDiff))   ;
						}
						else//brkToDateDay is less than brkFromDateDay
						{
							dayDiff = (360 * (yearDiff)) - (Number(brkFromDateDay)-Number(brkToDateDay));
						}
					}
				}
				else 
				{
					if(Number(brkToDateMonth) > Number(brkFromDateMonth))
					{
						monthDiff = (12 * yearDiff) +  (Number(brkToDateMonth) - Number(brkFromDateMonth));
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)));
						}
						else
						{
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
							}
							else//brkToDateDay is less than brkFromDateDay
							{
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
							}
						}
						
					}
					else //brkToDateMonth is less than brkFromDateMonth
					{
						monthDiff = (12 * yearDiff) -  (Number(brkFromDateMonth) - Number(brkToDateMonth));
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)));
						}
						else
						{	
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								dayDiff = ((yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;								
								//dayDiff = ((yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
							}
							else//brkToDateDay is less than brkFromDateDay
							{	
								dayDiff = ((yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
								//dayDiff = ((yearDiff * 360) - (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
							}
						}
					}
				}
			}
		}
		document.getElementById("hidQylDays"+count).value = dayDiff; 
	}catch(e){
		
	}
	
}


function adjustTotalDays(count)
{
	if(document.getElementById('tblServBrkDtls').rows.length == 1)
	{
		document.getElementById("txtTotSerBreak").value = Number("0.00");
		document.getElementById("txtNonQualifyingServ").value = Number("0.00");
		
		document.getElementById("txtTotSerBreakYear").value=0;
		document.getElementById("txtTotSerBreakMonth").value=0;
		document.getElementById("txtTotSerBreakDay").value=0;
		
		document.getElementById("txtNonQualifyingServYear").value=0;
		document.getElementById("txtNonQualifyingServMonth").value=0;
		document.getElementById("txtNonQualifyingServDay").value=0;
		
	}else if(document.getElementById("txtTotSerBreak").value != "")
	{	
		try{
			document.getElementById("txtTotSerBreak").value = Number(document.getElementById("txtTotSerBreak").value)-  Number(document.getElementById("hidDays"+count).value);
			document.getElementById("txtNonQualifyingServ").value = Number(document.getElementById("txtNonQualifyingServ").value)-  Number(document.getElementById("hidDays"+count).value);
		}catch(e){
		}
	}
}

function adjustTotalQlyDays(count)
{
	if(document.getElementById('tblQylServDtls').rows.length == 1)
	{
		document.getElementById("txtTotQlyServ").value = Number("0.00");
		document.getElementById("txtQlyService").value = Number("0.00");
		
		document.getElementById("txtQualiServYear").value=0;
		document.getElementById("txtQualiServMonth").value=0;
		document.getElementById("txtQualiServDay").value=0;
		
		document.getElementById("txtQualiServYear").value=0;
		document.getElementById("txtQualiServMonth").value=0;
		document.getElementById("txtQualiServDay").value=0;
		
	}else if(document.getElementById("txtTotQlyServ").value != "")
	{	
		try{
			document.getElementById("txtTotQlyServ").value = Number(document.getElementById("txtTotQlyServ").value)+  Number(document.getElementById("hidQylDays"+count).value);
			document.getElementById("txtQlyService").value = Number(document.getElementById("txtQlyService").value)+  Number(document.getElementById("hidQylDays"+count).value);
		}catch(e){
		}
	}
}

function setTotalDaysOfSrvcBrk(FieldName1,FieldName2)
{
	if(document.getElementById('tblServBrkDtls').rows.length == 1)
	{
		document.getElementById("txtTotSerBreak").value = Number("0.00");
		document.getElementById("txtNonQualifyingServ").value = Number("0.00");
	}
	var rowCount=Number(document.getElementById("tblServBrkDtls").rows.length);
	var total=0;
	/*if(document.getElementById("txtDateOfRetiremt").value == "" )
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
	}*/
	if(rowCount >1){
		for(var cnt=0;cnt<Number(rowCount+1);cnt++)
		{
			try{
				if(document.getElementById(FieldName1+cnt)!=null && document.getElementById(FieldName1+cnt).value!='' && document.getElementById(FieldName2+cnt)!=null && document.getElementById(FieldName2+cnt).value!='')
				{
					calTotalSrvcBrk(cnt);
					total=total+Number(document.getElementById("hidDays"+cnt).value);
					document.getElementById("txtTotSerBreak").value=total;
					document.getElementById("txtNonQualifyingServ").value=total;
					
					calQualifyingSrvc();
					
					var TotalBrkYear = Math.floor(Number((total/360)));
					var TotalBrkMonth = Math.floor(Number((total%360)/30));
					var TotalBrkDays = (total%360)%30;
					
					document.getElementById("hidTotalBrkyear").value=TotalBrkYear;
					document.getElementById("hidTotalBrkMonth").value=TotalBrkMonth;
					document.getElementById("hidTotalBrkDays").value=TotalBrkDays;
					
					document.getElementById("txtTotSerBreakYear").value=TotalBrkYear;
					document.getElementById("txtTotSerBreakMonth").value=TotalBrkMonth;
					document.getElementById("txtTotSerBreakDay").value=TotalBrkDays;
					
					document.getElementById("txtNonQualifyingServYear").value=TotalBrkYear;
					document.getElementById("txtNonQualifyingServMonth").value=TotalBrkMonth;
					document.getElementById("txtNonQualifyingServDay").value=TotalBrkDays;
				}
			}catch(e){
			}
		}
	}else{
		document.getElementById("hidTotalBrkyear").value="0";
		document.getElementById("hidTotalBrkMonth").value="0";
		document.getElementById("hidTotalBrkDays").value="0";
		
		document.getElementById("txtTotSerBreakYear").value=0;
		document.getElementById("txtTotSerBreakMonth").value=0;
		document.getElementById("txtTotSerBreakDay").value=0;
		
		document.getElementById("txtNonQualifyingServYear").value=0;
		document.getElementById("txtNonQualifyingServMonth").value=0;
		document.getElementById("txtNonQualifyingServDay").value=0;
	}
}

function setTotalDaysOfQlyServ(FieldName1,FieldName2)
{
	if(document.getElementById('tblQylServDtls').rows.length == 1)
	{
		document.getElementById("txtTotQlyServ").value = Number("0.00");
		document.getElementById("txtQlyService").value = Number("0.00");
	}
	var rowCount=Number(document.getElementById("tblQylServDtls").rows.length);
	var total=0;
	
	if(rowCount >1){
		for(var cnt=0;cnt<Number(rowCount+1);cnt++)
		{
			try{
				if(document.getElementById(FieldName1+cnt)!=null && document.getElementById(FieldName1+cnt).value!='' && document.getElementById(FieldName2+cnt)!=null && document.getElementById(FieldName2+cnt).value!='')
				{
					calTotalQlyServ(cnt);
					total=total+Number(document.getElementById("hidQylDays"+cnt).value);
					//alert("total^^^^^"+total);
					//alert("txtTotQlyServ^^^^^"+document.getElementById("txtTotQlyServ").value);
					document.getElementById("txtTotQlyServ").value=total;
					document.getElementById("txtQlyService").value=total;
					
					calQualifyingSrvc();
					
					var TotalQlyYear = Math.floor(Number((total/360)));
					var TotalQlyMonth = Math.floor(Number((total%360)/30));
					var TotalQlyDays = (total%360)%30;
					
					document.getElementById("hidTotalServyear").value=TotalQlyYear;
					document.getElementById("hidTotalServMonth").value=TotalQlyMonth;
					document.getElementById("hidTotalServDays").value=TotalQlyDays;
					
					document.getElementById("txtTotQlyServYear").value=TotalQlyYear;
					document.getElementById("txtTotQlyServMonth").value=TotalQlyMonth;
					document.getElementById("txtTotQlyServDay").value=TotalQlyDays;
					
					document.getElementById("txtQualiServYear").value=TotalQlyYear;
					document.getElementById("txtQualiServMonth").value=TotalQlyMonth;
					document.getElementById("txtQualiServDay").value=TotalQlyDays;
				}
			}catch(e){
			}
		}
	}else{
		document.getElementById("hidTotalServyear").value="0";
		document.getElementById("hidTotalServMonth").value="0";
		document.getElementById("hidTotalServDays").value="0";
		
		document.getElementById("txtTotQlyServYear").value=0;
		document.getElementById("txtTotQlyServMonth").value=0;
		document.getElementById("txtTotQlyServDay").value=0;
		
		document.getElementById("txtQualiServYear").value=0;
		document.getElementById("txtQualiServMonth").value=0;
		document.getElementById("txtQualiServDay").value=0;
	}
}


function calQualifyingSrvc()
{
	calActualServiceDays();
	document.getElementById("txtQualifyingServ").value = (Number(document.getElementById("txtActualSer").value) - Number(document.getElementById("txtNonQualifyingServ").value) )+ Number(document.getElementById("txtQlyService").value);
	//document.getElementById("txtQualifyingServ").value = Number(document.getElementById("txtActualSer").value) + Number(document.getElementById("txtQlyService").value); 
	
	var totalQualifyingServ =document.getElementById("txtQualifyingServ").value; 
	//alert("totalQualifyingServ%%%%"+totalQualifyingServ);
	
	var TotalBrkYear = Math.floor(Number((totalQualifyingServ/360)));
	var TotalBrkMonth = Math.floor(Number((totalQualifyingServ%360)/30));
	var TotalBrkDays = (totalQualifyingServ%360)%30;
		
	document.getElementById("txtQualifyingServYear").value=TotalBrkYear;
	document.getElementById("txtQualifyingServMonth").value=TotalBrkMonth;
	document.getElementById("txtQualifyingServDay").value=TotalBrkDays;
}
function GetScalePostfromDesg()
{
	  var payScalelength=document.getElementById("cmbPayScale").length;
	  for(i=1;i<payScalelength;i++)
	  {
		  var lgth = document.getElementById("cmbPayScale").options.length -1;				  
		  document.getElementById("cmbPayScale").options[lgth] = null;
	  }
	  var commissionId;
	  var payCommission= document.getElementById("cmbPayComsn").value;
	 
	  
	  if(payCommission == '5THPAYCOMSN'){
		  commissionId = 700015;
		  //Changed by shraddha for Padmanabhan
	  }else if (payCommission == 'PadmanabhanComm'){
		  commissionId = 700339;
	  }
	  else if (payCommission == 'ShettyCommission'){
		  commissionId = 700345;
	  }
	  
	  else{
		  commissionId = 700016;
	  }
	  var url = './hrms.htm?actionFlag=GetScalefromDesignation';
	  var uri = '&commissionId='+commissionId+'&ifAjax=TRUE';		  
	  url = url + uri;
	  
	  var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
						GetScalesPostsfromDesg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function GetScalesPostsfromDesg(myAjax){	
	
	  var XMLDoc =  myAjax.responseXML.documentElement;	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('scale-mapping');
	  var scale = document.getElementById("cmbPayScale");
	  for ( var i = 0 ; i < XmlHiddenValues.length ; i++ )
	  {
		   var val=XmlHiddenValues[i].childNodes[0].firstChild.nodeValue;
		   var text =XmlHiddenValues[i].childNodes[1].firstChild.nodeValue; 
		   var theOption = new Option;
		   theOption.value=val;
		   theOption.text=text;			
		   scale.options[i+1]=theOption;
      }
}
function validateBasicPay(obj){
	
	var payCommission= document.getElementById("cmbPayComsn").value;
	var index = document.PensionInwardForm.cmbPayScale.selectedIndex ;
	var payScale = document.PensionInwardForm.cmbPayScale[index].text ;
	var basicPay = obj.value;
	var payArray;
	var count=0;
	
	if((basicPay != null && basicPay != "" && basicPay != "0.00" && basicPay != "0") && (document.getElementById("cmbPayComsn").value != null && document.getElementById("cmbPayComsn").value != -1) )
	{	
		if(payCommission == '6THPAYCOMSN'){
			var tempArray = payScale.split("(");
			payArray = tempArray[0].split("-");
			
			if(parseInt(basicPay)< parseInt(payArray[0]) || parseInt(basicPay) >parseInt(payArray[1])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				 obj.value = '';
				 obj.focus();
				return;
			}
			
		}else if(payCommission == '5THPAYCOMSN'){
			
			payArray = payScale.split("-");
		
			var temp;
			for(k=0;k<payArray.length;k++){
				temp = payArray[k];
				payArray[k] = temp.trim();
			}
			for(var j=0;j<payArray.length;j++){
				if(payArray[j] == 'EB')
				{
					payArray.splice(j,1); 
				}
			}
			if(basicPay == payArray[payArray.length - 1]){
				return;
			}
			for(var i=0;i<payArray.length;i+=2){
		
				if(i != 0){
					if(parseInt(basicPay)>parseInt(payArray[i])){
						count = i;
						continue;
					}
					
					else{
						var start = payArray[i-2];
						var variance = payArray[i-1];
	
						if((basicPay-start)%variance != 0){
							alert("The Basic Pay is not in accordance with the Pay Scale selected");
							 obj.value = '';
							 obj.focus();
						}
						return;
					}
				}
				else{
					if(parseInt(basicPay)<parseInt(payArray[i])){

						alert("The Basic Pay is not in accordance with the Pay Scale selected");
						 obj.value = '';
						 obj.focus();
						return;
					}
				}
				count = i;
			}
			if(count!=0 && parseInt(basicPay)>parseInt(payArray[count])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				 obj.value = '';
				 obj.focus();
				return;
			}
		}
	}
	
}
function popUpGradePayAndDP(){
	var payCommission= document.getElementById("cmbPayComsn").value;
	var index = document.PensionInwardForm.cmbPayScale.selectedIndex ;
	var payScale = document.PensionInwardForm.cmbPayScale[index].text ;
	var basicPay = document.getElementById("txtBasicPay").value;
	
	var DOR = document.getElementById("txtDateOfRetiremt").value;
	var DPConsideringDate = "01/08/2004";
	if(payCommission == '6THPAYCOMSN')
	{
		if(document.PensionInwardForm.cmbPayScale != "-1")
			{
				if(payScale != "" && payScale != -1)
					{
						var tempArray = payScale.split("(");
						var gradePay = tempArray[1].split(")")[0].trim(); 
						document.PensionInwardForm.txtGradePay.value = gradePay;
						//edited by aditya
						//document.PensionInwardForm.txtBasicPay.value=basicPay-gradePay;
						//edited by aditya
						document.PensionInwardForm.txtDP.value = "";
					}
			}
		
	}
	else if(payCommission == 'PadmanabhanComm')
	{
		if(document.PensionInwardForm.cmbPayScale != "-1")
			{
				if(payScale != "" && payScale != -1)
					{
						var tempArray = payScale.split("(");
						var gradePay = tempArray[1].split(")")[0].trim(); 
						document.PensionInwardForm.txtGradePay.value = gradePay;
						//edited by aditya
						//document.PensionInwardForm.txtBasicPay.value=basicPay-gradePay;
						//edited by aditya
						document.PensionInwardForm.txtDP.value = "";
					}
			}
		
	}
	
	else if(payCommission == 'ShettyCommission')
	{
		if(document.PensionInwardForm.cmbPayScale != "-1")
			{
				if(payScale != "" && payScale != -1)
					{
						var tempArray = payScale.split("(");
						var gradePay = tempArray[1].split(")")[0].trim(); 
						document.PensionInwardForm.txtGradePay.value = gradePay;
						//edited by aditya
						//document.PensionInwardForm.txtBasicPay.value=basicPay-gradePay;
						//edited by aditya
						document.PensionInwardForm.txtDP.value = "";
					}
			}
		
	}
	
	else
	{
		if(basicPay != "" && compareDatesWithoutAlert(DOR,DPConsideringDate,"<"))
			document.PensionInwardForm.txtDP.value = (Number(basicPay)/2);
		
		document.PensionInwardForm.txtGradePay.value = "";
	}
}
function setNPAAllow(){
	var CalcGridSize = document.getElementById("hidCalcGridSize").value;
	if(document.getElementById("chkBoxNPA").checked == true){
		
		for(var i=0;i<Number(CalcGridSize);i++){
			try{
				document.getElementById("txtNPA"+i).readOnly=false;
			}catch(e){
			}
		}				
	}else{
		for(var i=0;i<Number(CalcGridSize);i++){
			try{
				document.getElementById("txtNPA"+i).value = 0;
				document.getElementById("txtNPA"+i).readOnly=true;
			}catch(e){
			}
		}	
	}
}
function showSrvcBrkOtherReason(rowNo){
	var srvBrkReason = document.getElementById("cmbTypeofBrk"+rowNo).value;
	
	if(srvBrkReason == '10036'){
		document.getElementById("divSrvBreakReason"+rowNo).style.display='';
	}else{
		document.getElementById("txtSrvBreakOtherReason"+rowNo).value='';
		document.getElementById("divSrvBreakReason"+rowNo).style.display='none';
	}

}
function enableForeignServices(){
	var radioForeign = document.PensionInwardForm.radioServRenderForeign;
	var radioValue;
	for(var i=0; i < radioForeign.length; i++){		
		if(radioForeign[i].checked) {
			radioValue = radioForeign[i].value;
		}
	}
	if(radioValue == 'N'){
		document.getElementById("ForeignServAddRow").disabled=true;
	}else{
		
		if(document.getElementById("tblForeignServDtls").rows.length>1)
		{
			alert('You cannot select this option because Foreign Services Details are present.');
			document.getElementById("radioServRenderForeignYes").checked=false;
			document.getElementById("radioServRenderForeignNo").checked="checked";
			return;
		}
		else
		{
			document.getElementById("ForeignServAddRow").disabled=false;
		}	
	}
}
function enableGratuityPaid (){
	var radioPrvsnlGrtyPaid = document.PensionInwardForm.radioPrvsnlGrtyPaid;
	var radioValue;
	for(var i=0; i < radioPrvsnlGrtyPaid.length; i++){		
		if(radioPrvsnlGrtyPaid[i].checked) {
			radioValue = radioPrvsnlGrtyPaid[i].value;
		}
	}
	if(radioValue == 'Y'){
		document.getElementById("txtPnsnGrtyAmount").readOnly = false;
		document.getElementById("txtOrderNo").readOnly = false;
		document.getElementById("txtOrderDate").readOnly = false;
		document.getElementById("txtvoucherNo").readOnly = false;
		document.getElementById("txtvoucherDate").readOnly = false;
		
		
	}else{
		document.getElementById("txtPnsnGrtyAmount").value='';
		document.getElementById("txtPnsnGrtyAmount").readOnly = true;
		document.getElementById("txtOrderNo").value='';
		document.getElementById("txtOrderNo").readOnly = true;
		document.getElementById("txtOrderDate").value='';
		document.getElementById("txtOrderDate").readOnly = true;
		document.getElementById("txtvoucherNo").value='';
		document.getElementById("txtvoucherNo").readOnly = true;
		document.getElementById("txtvoucherDate").value='';
		document.getElementById("txtvoucherDate").readOnly = true;
	}
}
function enableProvisionalPensionDetails(){
	var radioPrvsnlPaid = document.PensionInwardForm.radioPrvsnlPnsnPaid;
	var radioValue;
	for(var i=0; i < radioPrvsnlPaid.length; i++){		
		if(radioPrvsnlPaid[i].checked) {
			radioValue = radioPrvsnlPaid[i].value;
		}
	}
	if(radioValue == 'Y'){
		document.getElementById("txtFromDatePrvsnlPnsn").readOnly = false;		
		document.getElementById("txtToDatePrvsnlPnsn").readOnly = false;
		document.getElementById("txtSancAuthNo").readOnly = false;
		document.getElementById("txtSancAuthName").readOnly = false;
		document.getElementById("imgFromDate").style.display='';
		document.getElementById("imgToDate").style.display='';
		document.getElementById("PrvsnlPnsnAddRow").disabled=false;
	}else{
		
		if(document.getElementById("tblPrvsnlPnsnDtls").rows.length>1)
		{
			alert('You cannot select this option because Provisional Pension Details are present.');
			document.getElementById("radioPrvsnlPnsnPaidNo").checked=false;
			document.getElementById("radioPrvsnlPnsnPaidYes").checked="checked";
			return;
		}
		else
		{
			document.getElementById("txtFromDatePrvsnlPnsn").value='';
			document.getElementById("txtFromDatePrvsnlPnsn").readOnly = true;
			document.getElementById("txtToDatePrvsnlPnsn").value='';
			document.getElementById("txtToDatePrvsnlPnsn").readOnly = true;
			document.getElementById("txtSancAuthNo").value='';
			document.getElementById("txtSancAuthNo").readOnly = true;
			document.getElementById("txtSancAuthName").value='';
			document.getElementById("txtSancAuthName").readOnly = true;
			document.getElementById("imgFromDate").style.display='none';
			document.getElementById("imgToDate").style.display='none';
			document.getElementById("PrvsnlPnsnAddRow").disabled=true;
		}
		
		
	}
}
function setQualifyingService(){
	var actualServ = document.getElementById("txtActualSer").value;
	var qulifyingServ = document.getElementById("txtQualifyingServ").value;
	var totalServBrk = document.getElementById("txtTotSerBreak").value;
	
	var actualYear = Math.floor(Number(actualServ/360));
	var actualMonth = Math.floor(Number((actualServ%360)/30));
	var actualDays = (actualServ%360)%30;
	
	var qulifyingYear = Math.floor(Number(qulifyingServ/360));
	var qulifyingMonth = Math.floor(Number((qulifyingServ%360)/30));
	var qulifyingDays = (qulifyingServ%360)%30;
	
	var ServBrkYear = Math.floor(Number(totalServBrk/360));
	var ServBrkMonth = Math.floor(Number((totalServBrk%360)/30));
	var ServBrkDays = (totalServBrk%360)%30;
	
	document.getElementById("txtActualSerYear").value=actualYear;
	document.getElementById("txtActualSerMonth").value=actualMonth;
	document.getElementById("txtActualSerDay").value=actualDays;
	
	document.getElementById("txtQualifyingServYear").value=qulifyingYear;
	document.getElementById("txtQualifyingServMonth").value=qulifyingMonth;
	document.getElementById("txtQualifyingServDay").value=qulifyingDays;
	
	document.getElementById("txtTotSerBreakYear").value=ServBrkYear;
	document.getElementById("txtTotSerBreakMonth").value=ServBrkMonth;
	document.getElementById("txtTotSerBreakDay").value=ServBrkDays;
	
	document.getElementById("txtNonQualifyingServYear").value=ServBrkYear;
	document.getElementById("txtNonQualifyingServMonth").value=ServBrkMonth;
	document.getElementById("txtNonQualifyingServDay").value=ServBrkDays;
}
function showNPA(){
	var pensionerType = document.getElementById("cmbPnsnCatg").value;
	if(pensionerType == 'Medical Officer')
	{
		document.getElementById("npaAllow").style.display="";
		document.getElementById("txtNPA").readOnly=false;
	}else{
		document.getElementById("txtNPA").readOnly=true;
		document.getElementById("chkBoxNPA").checked == false;
		document.getElementById("npaAllow").style.display="none";
		var CalcGridSize = document.getElementById("hidCalcGridSize").value;
		for(var i=0;i<Number(CalcGridSize);i++){
			try{
				document.getElementById("txtNPA"+i).value = 0;
				document.getElementById("txtNPA"+i).readOnly=true;
				setTotal(i);
				calTotal();
			}catch(e){
			}
		}
	}	
}
function disableDP(){

	var DOR = document.getElementById("txtDateOfRetiremt").value;
	var DPConsideringDate = "01/08/2004";
	var payComm = document.getElementById("cmbPayComsn").value;
	var CalcGridSize = document.getElementById("hidCalcGridSize").value;
	if(payComm == '5THPAYCOMSN'){		
		if(compareDatesWithoutAlert(DOR,DPConsideringDate,"<")){
			
			for(var i=0;i<Number(CalcGridSize);i++){
				try{
					document.getElementById("txtAvgPayDP"+i).readOnly=false;
				}catch(e){
				}
			}				
		}else{
			for(var i=0;i<Number(CalcGridSize);i++){
				try{
					document.getElementById("txtAvgPayDP"+i).value = 0;
					document.getElementById("txtAvgPayDP"+i).readOnly=true;
				}catch(e){
				}
			}	
		}
	}else{
		
		for(var i=0;i<Number(CalcGridSize);i++){
			try{
				document.getElementById("txtAvgPayDP"+i).readOnly=false;
			}catch(e){
			}
		}
	}
}
function validateAuthorityName(){
	var Row_ID_Prov = document.getElementById("hidPrvsnlPnsnGridSize").value;
	var flag = 0;
	var temp = '';
	for(var i=0;i<Number(Row_ID_Prov);i++){
		try{
			var athorityName = document.getElementById("cmbSancAuthName"+i).value;		
			if(athorityName == "O"){
				flag++;
				temp = i;
			}	
		}catch(e){
		}
	}
	if(Number(flag)>6){
		//changed by shraddha
		alert("Only six time authority name is DDO's office name ");	
		document.getElementById("cmbSancAuthName"+temp).value = "-1";
		document.getElementById("cmbSancAuthName"+temp).focus();
		return false;
	}
}
function validateAddFromAndToDate(rowId){
	var appFromDate = document.getElementById("txtApplnFromDate"+rowId).value;	
	var appToDate = document.getElementById("txtApplnToDate"+rowId).value;
	
	if(appFromDate != "" && appToDate != ""){
		var appFromArrDate=appFromDate.split("/");							
		var Fday=parseInt(appFromArrDate[0],10);
		var Fmo=(parseInt(appFromArrDate[1],10)-1);
		var Fyr=parseInt(appFromArrDate[2]);	
		
		var appToArrDate=appToDate.split("/");							
		var Tday=parseInt(appToArrDate[0],10);
		var Tmo=(parseInt(appToArrDate[1],10)-1);
		var Tyr=parseInt(appToArrDate[2]);
		
		var fromDate = new Date(Fyr,Fmo,Fday);
		var toDate = new Date(Tyr,Tmo,Tday);
		
		if (fromDate>toDate)
		{
			alert('Application From Date should be greater than Application to Date');
			document.getElementById("txtApplnToDate"+rowId).value = "";
			document.getElementById("txtApplnToDate"+rowId).focus();
	        return false;
		}
		
		fromDate.add(6).month();
		if (fromDate<toDate)
		{
			alert('Date difference should be less than or equal to six months');
			document.getElementById("txtApplnToDate"+rowId).value = "";
			document.getElementById("txtApplnToDate"+rowId).focus();
	        return false;
		}
	}
	
}


//---------added by shraddha for NPA Validation-----------------
function validateNpa(Count){
	
	var  npa=document.getElementById("txtNPA"+Count).value;
	//var limit = 15000;
	var basic=document.getElementById("txtAvgPayBasic"+Count).value;
	var dp=document.getElementById("txtAvgPayDP"+Count).value;
	var tot = Number(basic)+ Number(dp); 
	//Added by shraddha for 7th pay comm
	var PayComsn = document.getElementById("cmbPayComsn").value;
	var validNpa = Math.round(Math.round(35 * tot)/100);
	
	
	if(npa != null && npa != "" && npa != "0.00" && npa != "0")
			{
	//if(npa!=validNpa && npa < limit){
		if(npa!=validNpa){
		alert("NPA Amount should be 35% of Basic + GP/DP" );
		document.getElementById("txtNPA"+Count).value=0;
		setTotal(Count);
		return false;
	}

	
	/* if(npa > limit && validNpa > limit){
		
		alert("NPA Amount exceeds the limit. It should be less than 15000");
		document.getElementById("txtNPA"+Count).value=15000;
		setTotal(Count);
		return false;
	}*/
			}
	 return true;
	
	
	
	
	
}

function validateEventFromDate(Count)
{
	//alert("count "+Count);
	var fromDate = document.getElementById("txtDateOfEventFrom"+Count).value;
	var toDate = document.getElementById("txtDateOfEventTo"+Count).value;
	if(fromDate != "" )
	{
		var fromDateArr = fromDate.split("/");
		var fromDateDay = fromDateArr[0];
		var fromDateMonth = fromDateArr[1];
		var fromDateYear = fromDateArr[2];
		
		var toDateArr = toDate.split("/");
		var toDateDay = toDateArr[0];
		var toDateMonth = toDateArr[1];
		var toDateYear = toDateArr[2];
		
		if(Number(toDateYear) < Number(fromDateYear))
		{
			alert('Event To Date should be greater than Event From Date.');
			document.getElementById("txtDateOfEventTo"+Count).value ="";
			return;
			
		}
		else
		{
			if (Number(toDateYear) == Number(fromDateYear))
			{// alert("Number(toDateYear)"+Number(toDateYear));
				if(Number(fromDateMonth) > Number(toDateMonth))
				{
					//alert("Number(toDateMonth)"+Number(toDateMonth));
					alert('Event To Date should be greater than Event From Date.');
					document.getElementById("txtDateOfEventTo"+Count).value ="";
					return;
				}
				else
				{
					if(Number(toDateMonth) == Number(fromDateMonth))
					{
					//	alert(" else Number(fromDateMonth)"+Number(fromDateMonth));
						if(Number(fromDateDay) > Number(toDateDay))
						{
							//alert(" else Number(toDateDay)"+Number(toDateDay));
							alert('Event To Date should be greater than Event From Date.');
							document.getElementById("txtDateOfEventTo"+Count).value ="";
							return;
						}
						/*if(Number(fromDateDay) == Number(toDateDay))
						{
							alert('Non qualifying service to Date should be greater than Non qualifying service from Date.');
							document.getElementById("txtDateOfBrkTo"+Count).value ="";
							return;
						}*/
					}
				}
			}
		}
	}
}


function get7PayBasic(){
	
	
	//alert("hiii");
	
	  var payCommission= document.getElementById("cmbPayComsn").value;
	  var sevarthId=document.getElementById("txtSevaarthId").value;
	//alert("payComm*****"+payCommission);
	//alert("sevarthId*****"+sevarthId);
	
	if(payCommission=='7THPAYCOMSN'){
		
		var uri = "ifms.htm?actionFlag=checkPayComm&payCommission="+payCommission+"&sevarthId="+sevarthId;
		 // alert(uri);
		 
		  myAjax = new Ajax.Request(uri,
					{
				        method: 'post',
				        asynchronous: false,
				        parameters:uri,
				        onSuccess: function(myAjax) {get7PayBasicAjax(myAjax);},
				        onFailure: function(){ alert('Something went wrong...')} 
					});
	}
	 
	
	
}


function get7PayBasicAjax(myAjax){
	
	var XMLDoc = myAjax.responseXML.documentElement;

	if(XMLDoc != null)
	{				
		var XmlHiddenValues1 = XMLDoc.getElementsByTagName('FLAG');
		var flag = XmlHiddenValues1[0].childNodes[0].nodeValue;
		var payComm=new Array();
		var status= flag.split('~');
		var XmlHiddenValues2 = XMLDoc.getElementsByTagName('AMOUNT');
		var basicAmt = XmlHiddenValues2[0].childNodes[0].nodeValue;
	
		//alert("flag:::::"+flag);
		//alert("basicAmt:::::"+basicAmt);
		//alert("status[0]:::::"+status[0]);
		//alert("status[1]:::::"+status[1]);
		
		if(status[0]=='invalid'){
			
			alert ("Kindly update your Pay Commission details in Sevaarth first");
			document.getElementById("cmbPayComsn").value =status[1];
	
	
	}
		
		if(status[0]=='valid'){
			
			document.getElementById("txtBasicPay").value =basicAmt;

		
}
		
	}
	
}
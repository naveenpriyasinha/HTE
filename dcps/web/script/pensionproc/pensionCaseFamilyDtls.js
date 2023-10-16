var rowFCnt=0;
var rowNCnt=0;

function familyDtlsTableAddRow()
{
	/*
	 * 	
	Row_ID_Family = document.getElementById("hidFamilyGridSize").value;
	//var newRow =  document.all("tblPnsnrFamilyDtls").insertRow();	
	
	var table=document.getElementById("tblPnsnrFamilyDtls");
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
   	   	   
   	Cell1.innerHTML = '<input type="hidden" name="hdnFamilyMemberId" id="hdnFamilyMemberId'+Row_ID_Family+'"/><input type="text" name="txtFMName" id="txtFMName'+Row_ID_Family+'" size="20" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)"/>';
	Cell2.innerHTML = '<select name="cmbRelation" id="cmbRelation'+Row_ID_Family+'"  > <option value="-1">--Select--</option>'+ LISTRELATIONSHIP +'</select>';      	
   	Cell3.innerHTML = '<input type="text" name="txtPercentage" id="txtPercentage'+Row_ID_Family+'" size="15" maxlength="6" onfocus="onFocus(this)" onblur="onBlur(this);chkDigitAmnt(this);if(chkForsum100(this)!=false && totalPercentage(this,\'txtPercentage\',\'Total Percentage cannot be greater than 100\',\'hidFamilyGridSize\')!=false){};" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell4.innerHTML = '<input type="text" name="txtFMDateOfBirth" id="txtFMDateOfBirth'+Row_ID_Family+'"style="width:90px" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFMDateOfBirth'+Row_ID_Family+'\', 375, 570, \'\', \'\', '+Row_ID_Family+');" />';   
   	Cell5.innerHTML = '<input type="checkbox" id="chkMinor'+Row_ID_Family+'" name="chkMinor" value="Y" />';
   	Cell6.innerHTML = '<input type="checkbox" id="chkMarried'+Row_ID_Family+'" name="chkMarried" value="Y" />';
   	Cell7.innerHTML = '<input type="text" name="txtFMSalary" id="txtFMSalary'+Row_ID_Family+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell8.innerHTML = '<select name="cmbPhyHandicap" id="cmbPhyHandicap'+Row_ID_Family+'"  style="width: 100%"> <option value="-1">--Select--</option><option value="Y">Yes</option><option value="N">No</option></select>';
   	Cell9.innerHTML = '<input type="text" name="txtFMGuardianName" id="txtFMGuardianName'+Row_ID_Family+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)"/>';
   	Cell10.innerHTML = '<input type="text" name="txtFMDateOfDeath" id="txtFMDateOfDeath'+Row_ID_Family+'" style="width:90px" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFMDateOfDeath'+Row_ID_Family+'\', 375, 570, \'\', \'\', '+Row_ID_Family+');" />';
   	Cell11.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblPnsnrFamilyDtls\')" /> ';
   	document.getElementById("hidFamilyGridSize").value = Number(Row_ID_Family)+1;  	

	 */
		rowFCnt = document.getElementById("hidFamilyGridSize").value;			
		var table=document.getElementById("tblFamilyDtls");
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
	   	
	   	//Changed by shraddha
	   	var Cell9 = newRow.insertCell(8);
   		Cell9.className = "tds";
	   	Cell9.align="center";
	   	
	   	//---------------------------
	    	/*var Cell10 = newRow.insertCell(9);
   		Cell10.className = "tds";
	   	Cell10.align="center";
		var Cell11 = newRow.insertCell(10);
   		Cell11.className = "tds";
	   	Cell11.align="center";*/
	   
		
		Cell1.innerHTML = '<input type="hidden" name="hdnFamilyMemberId" id="hdnFamilyMemberId'+Number(rowFCnt)+'" /><input type="text" name="txtNameOfFamilyMember" style="text-transform: uppercase" maxlength="99" id="txtNameOfFamilyMember'+Number(rowFCnt)+'" onblur="onBlur(this);isName(this,\'Plz enter valid Name\');"    size="30"  />';
		Cell2.innerHTML = '<select name="cmbFmlyMemRelation" id="cmbFmlyMemRelation'+Number(rowFCnt)+'"><option value="-1">--Select--</option>' +LISTRELATION +'</select>';
		Cell3.innerHTML = '<select name="cmbPhyHandicap" id="cmbPhyHandicap'+rowFCnt+'"  style="width: 100%" onchange=enableGuardianName('+rowFCnt+')> <option value="-1">--Select--</option><option value="Y">Yes</option><option value="N" selected="selected">No</option></select>';
		Cell4.innerHTML = '<input type="text" name="txtFmlyMemDateOfBirth"  id="txtFmlyMemDateOfBirth'+Number(rowFCnt)+'" onblur="onBlur(this);chkValidDate(this);greaterThanCurrDateValidation(this,\'Date of Birth cannot be greater than Current Date\');getFamilyMemAge(this,'+Number(rowFCnt)+');"  onkeypress="digitFormat(this);dateFormat(this);"   maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtFmlyMemDateOfBirth'+Number(rowFCnt)+'\', 375, 570, \'\', \'\', '+Number(rowFCnt)+');"/>';		
		//changed by shraddha-----------
		Cell5.innerHTML = '<input type="checkbox" id="chkFamlyPen'+rowFCnt+'" name="chkFamlyPen" value="Y" />';
	  //-----------------------------------
		
		/*	Cell5.innerHTML = '<input type="checkbox" id="FmlyNomineecheckbox'+rowFCnt+'" name="FmlyNomineecheckbox" value="Y" />';	   	
	   	Cell6.innerHTML = '<input type="text" name="txtPercentage" id="txtPercentage'+rowFCnt+'" size="5" maxlength="5" onfocus="onFocus(this)" onblur="onBlur(this);chkDigitAmnt(this);if(totalPercentage(this,\'txtPercentage\',\'Total Percentage cannot be greater than 100\',\'hidFamilyGridSize\')!=false){};" onkeypress="amountFormat(this);"  style="text-align: right"/>';
	   	*/Cell6.innerHTML = '<input type="checkbox" id="chkMinor'+rowFCnt+'" name="chkMinor" value="Y" onclick="enableGuardianName('+rowFCnt+')" />';
	   	Cell7.innerHTML = '<input type="checkbox" id="chkMarried'+rowFCnt+'" name="chkMarried" value="Y" />';
	   	//Cell7.innerHTML = '<input type="text" name="txtFMSalary" id="txtFMSalary'+rowFCnt+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
	   	
	  
	   	Cell8.innerHTML = '<input type="text" name="txtFMGuardianName" id="txtFMGuardianName'+rowFCnt+'" size="30" onfocus="onFocus(this)"  readonly="readonly" onblur="onBlur(this);" onkeypress="upperCase(event)"/>';
	   //Cell10.innerHTML = '<input type="text" name="txtFMDateOfDeath" id="txtFMDateOfDeath'+rowFCnt+'" style="width:90px" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFMDateOfDeath'+rowFCnt+'\', 375, 570, \'\', \'\', '+rowFCnt+');" />';
		Cell9.innerHTML = '<img name="Image" id="Image'+Number(rowFCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblFamilyDtls")\'/><input type="hidden" id="Spk'+Number(rowFCnt)+'" name="Spk">';
		

		document.getElementById("hidFamilyGridSize").value = Number(rowFCnt)+1;
		
		
}

function nomineeDtlsTableAddRow()
{
	var table=document.getElementById("tblNomineeDtls");
	var rowNCnt=table.rows.length;
	var newRow = table.insertRow(rowNCnt);  
	
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
	/*var Cell6 = newRow.insertCell(5);
	Cell6.className = "tds";
   	Cell6.align="center";
	var Cell7 = newRow.insertCell(6);
	Cell7.className = "tds";
   	Cell7.align="center";
   	var Cell8 = newRow.insertCell(7);
	Cell8.className = "tds";
   	Cell8.align="center";*/

	Cell1.innerHTML = '<input type="hidden" name="hdnNomineeMemberId" id="hdnNomineeMemberId'+Number(rowFCnt)+'" /><input type="text" name="txtNameOfNominee" style="text-transform: uppercase" id="txtNameOfNominee'+Number(rowNCnt)+'" onblur="onBlur(this);isName(this,\'Plz enter valid Name\');" size="25" />';
	Cell2.innerHTML = '<select name="cmbNomineeMemRelation" id="cmbNomineeMemRelation'+Number(rowNCnt)+'"><option value="-1">--Select--</option>' +LISTRELATION +'</select>';
	Cell3.innerHTML = '<input type="text" name="txtNomPercentage" id="txtNomPercentage'+rowNCnt+'" maxlength="3" size="5" onfocus="onFocus(this)"  onblur="onBlur(this);if(isPercentage(this,\'Value cannot be grater than 100.\',\'100\')!=false && totalPercentage(this,\'txtNomPercentage\',\'Total Percentage cannot be greater than 100\',\'hidNomGridSize\')!=false){}" onkeypress="amountFormat(this);"  style="text-align: right" />';      	
Cell4.innerHTML = '<input type="text" name="txtNameOfAltrNominee" style="text-transform: uppercase" id="txtNameOfAltrNominee'+Number(rowNCnt)+'" onblur="onBlur(this);isName(this,\'Plz enter valid Name\');" size="25" />';
   	/*Cell5.innerHTML = '<select name="cmbNomBank" id="cmbNomBank'+rowNCnt+'"  style="width: 100%" onchange="getNomBranchNameFromBankCode(this)"> <option value="-1">--Select--</option>'+LISTBANKS+'</select>';   
   	Cell6.innerHTML = '<select name="cmbNomBankBranch" id="cmbNomBankBranch'+rowNCnt+'"  style="width: 100%" onchange="getNomAudNameFromBranchCode(this);"> <option value="-1">--Select--</option></select>';
   	Cell7.innerHTML = '<input type="text" name="txtNomAccountNo" id="txtNomAccountNo'+rowNCnt+'" size="25" maxlength="20"/>';
	*/Cell5.innerHTML = '<img name="Image" id="Image'+Number(rowNCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblNomineeDtls")\'/><input type="hidden" id="Spk'+Number(rowNCnt)+'" name="Spk">';
	
	document.getElementById("hidNomGridSize").value = Number(rowNCnt)+1;
	
}


function viewOrAddNewNomAddr(rowCnt)
{
	
	
	if(document.getElementById('radioIsNomAddrSameY'+rowCnt).checked)
	{
		
		document.getElementById("lnkAddNewNomAddr"+rowCnt).style.display="none";
		document.getElementById("lnkViewNomAddr"+rowCnt).style.display="inline";
		document.getElementById("hidNomState"+rowCnt).value=document.getElementById("cmbPrState").value;
		document.getElementById("hidNomDistrict"+rowCnt).value=document.getElementById("cmbPrTownCityDist").value;
		document.getElementById("hidNomArea"+rowCnt).value=document.getElementById("txtPrAreaLocality").value;
		document.getElementById("hidNomRoad"+rowCnt).value=document.getElementById("txtPrRoadPostOff").value;
		document.getElementById("hidNomFlat"+rowCnt).value=document.getElementById("txtPrFlatDoorBlk").value;
		document.getElementById("hidNomPinCode"+rowCnt).value=document.getElementById("txtPrPincode").value;
		document.getElementById("hidNomLandLineNo"+rowCnt).value=document.getElementById("txtLandlineNo").value;
		document.getElementById("hidNomMobileNo"+rowCnt).value=document.getElementById("txtMobileNo").value;
		document.getElementById("hidNomEmailId"+rowCnt).value=document.getElementById("txtEmailId").value;
		
	}
	else
	{
		document.getElementById("lnkViewNomAddr"+rowCnt).style.display="none";
		document.getElementById("lnkAddNewNomAddr"+rowCnt).style.display="inline";
		document.getElementById("hidNomState"+rowCnt).value="";
		document.getElementById("hidNomDistrict"+rowCnt).value="";
		document.getElementById("hidNomArea"+rowCnt).value="";
		document.getElementById("hidNomRoad"+rowCnt).value="";
		document.getElementById("hidNomFlat"+rowCnt).value="";
		document.getElementById("hidNomPinCode"+rowCnt).value="";
		document.getElementById("hidNomLandLineNo"+rowCnt).value="";
		document.getElementById("hidNomMobileNo"+rowCnt).value="";
		document.getElementById("hidNomEmailId"+rowCnt).value="";
	}
	
}

function viewOrAddNewFmlyAddr(rowCnt)
{
		
	if(document.getElementById('radioIsFmlyAddrSameY'+rowCnt).checked)
	{
		
		document.getElementById("lnkViewFmlyAddr"+rowCnt).style.display="inline";
		document.getElementById("lnkAddNewFmlyAddr"+rowCnt).style.display="none";
		document.getElementById("hidFmlyMemState"+rowCnt).value=document.getElementById("cmbPrState").value;
		document.getElementById("hidFmlyMemDistrict"+rowCnt).value=document.getElementById("cmbPrTownCityDist").value;
		document.getElementById("hidFmlyMemArea"+rowCnt).value=document.getElementById("txtPrAreaLocality").value;
		document.getElementById("hidFmlyMemRoad"+rowCnt).value=document.getElementById("txtPrRoadPostOff").value;
		document.getElementById("hidFmlyMemFlat"+rowCnt).value=document.getElementById("txtPrFlatDoorBlk").value;
		document.getElementById("hidFmlyMemPinCode"+rowCnt).value=document.getElementById("txtPrPincode").value;
		document.getElementById("hidFmlyMemLandLineNo"+rowCnt).value=document.getElementById("txtLandlineNo").value;
		document.getElementById("hidFmlyMemMobileNo"+rowCnt).value=document.getElementById("txtMobileNo").value;
		document.getElementById("hidFmlyMemEmailId"+rowCnt).value=document.getElementById("txtEmailId").value;
	}
	else
	{
		
		document.getElementById("lnkViewFmlyAddr"+rowCnt).style.display="none";
		document.getElementById("lnkAddNewFmlyAddr"+rowCnt).style.display="inline";
		document.getElementById("hidFmlyMemState"+rowCnt).value="";
		document.getElementById("hidFmlyMemDistrict"+rowCnt).value="";
		document.getElementById("hidFmlyMemArea"+rowCnt).value="";
		document.getElementById("hidFmlyMemRoad"+rowCnt).value="";
		document.getElementById("hidFmlyMemFlat"+rowCnt).value="";
		document.getElementById("hidFmlyMemPinCode"+rowCnt).value="";
		document.getElementById("hidFmlyMemLandLineNo"+rowCnt).value="";
		document.getElementById("hidFmlyMemMobileNo"+rowCnt).value="";
		document.getElementById("hidFmlyMemEmailId"+rowCnt).value="";
	}
	
}

function popupForViewOrAddAddr(rowCnt,address,mode)
{     

	
   var requestURL= '<%=request.getRequestURL() %>';
   var urlstyle = 'height=400,width=580,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top=50,left=200';
   var state;
   var district;
   var area;
   var road;
   var flat;
   var pincode;
   var landLineNo;
   var mobileNo;
   var emailId;
   if(address=='Family')
   {
	    state=document.getElementById("hidFmlyMemState"+rowCnt).value;
	    district=document.getElementById("hidFmlyMemDistrict"+rowCnt).value;
	    area=document.getElementById("hidFmlyMemArea"+rowCnt).value;
	    road=document.getElementById("hidFmlyMemRoad"+rowCnt).value;
	    flat=document.getElementById("hidFmlyMemFlat"+rowCnt).value;
	    pincode=document.getElementById("hidFmlyMemPinCode"+rowCnt).value;
	    landLineNo=document.getElementById("hidFmlyMemLandLineNo"+rowCnt).value;
	    mobileNo=document.getElementById("hidFmlyMemMobileNo"+rowCnt).value;
	    emailId=document.getElementById("hidFmlyMemEmailId"+rowCnt).value;
   }
   if(address=='Nominee')
   {
	    state=document.getElementById("hidNomState"+rowCnt).value;
	    district=document.getElementById("hidNomDistrict"+rowCnt).value;
	    area=document.getElementById("hidNomArea"+rowCnt).value;
	    road=document.getElementById("hidNomRoad"+rowCnt).value;
	    flat=document.getElementById("hidNomFlat"+rowCnt).value;
	    pincode=document.getElementById("hidNomPinCode"+rowCnt).value;
	    landLineNo=document.getElementById("hidNomLandLineNo"+rowCnt).value;
	    mobileNo=document.getElementById("hidNomMobileNo"+rowCnt).value;
	    emailId=document.getElementById("hidNomEmailId"+rowCnt).value;
   }
   var uri = "ifms.htm?actionFlag=loadViewOrAddAddressPopup&state="
		+ state + "&district=" + district
		+ "&area=" + area + "&road=" + road + "&flat="
		+ flat + "&pincode=" + pincode +"&landLineNo=" + landLineNo + "&mobileNo="
		+ mobileNo +"&emailId=" + emailId+"&Mode="+ mode+"&RowId=" + rowCnt +"&address=" + address;
   
   var actWindowObj = window.open(uri, "Show", urlstyle);        
      
}



function saveAddress(address)
{
	   
	   var rowId=document.getElementById("hidNumOfRows").value;
	   var familyMemOrNom=document.getElementById("hidNomOrFamily").value;
	  
	   if(familyMemOrNom=='Family')
	   {
		   window.opener.document.getElementById('hidFmlyMemState'+rowId).value=document.getElementById("cmbAddrState").value;
		   window.opener.document.getElementById('hidFmlyMemDistrict'+rowId).value=document.getElementById("cmbAddrTownCityDist").value;
		   window.opener.document.getElementById('hidFmlyMemArea'+rowId).value=document.getElementById("txtAddrAreaLocality").value;
		   window.opener.document.getElementById('hidFmlyMemRoad'+rowId).value=document.getElementById("txtAddrRoadPostOff").value;
		   window.opener.document.getElementById('hidFmlyMemFlat'+rowId).value=document.getElementById("txtAddrFlatDoorBlk").value;
		   window.opener.document.getElementById('hidFmlyMemPinCode'+rowId).value=document.getElementById("txtAddrPincode").value;
		   window.opener.document.getElementById('hidFmlyMemLandLineNo'+rowId).value=document.getElementById("txtAddrLandlineNo").value;
		   window.opener.document.getElementById('hidFmlyMemMobileNo'+rowId).value=document.getElementById("txtAddrMobileNo").value;
		   window.opener.document.getElementById('hidFmlyMemEmailId'+rowId).value=document.getElementById("txtAddrEmailId").value;
		  
		   
	   }
	   if(familyMemOrNom=='Nominee')
	   {
		   window.opener.document.getElementById('hidNomState'+rowId).value=document.getElementById("cmbAddrState").value;
		   window.opener.document.getElementById('hidNomDistrict'+rowId).value=document.getElementById("cmbAddrTownCityDist").value;
		   window.opener.document.getElementById('hidNomArea'+rowId).value=document.getElementById("txtAddrAreaLocality").value;
		   window.opener.document.getElementById('hidNomRoad'+rowId).value=document.getElementById("txtAddrRoadPostOff").value;
		   window.opener.document.getElementById('hidNomFlat'+rowId).value=document.getElementById("txtAddrFlatDoorBlk").value;
		   window.opener.document.getElementById('hidNomPinCode'+rowId).value=document.getElementById("txtAddrPincode").value;
		   window.opener.document.getElementById('hidNomLandLineNo'+rowId).value=document.getElementById("txtAddrLandlineNo").value;
		   window.opener.document.getElementById('hidNomMobileNo'+rowId).value=document.getElementById("txtAddrMobileNo").value;
		   window.opener.document.getElementById('hidNomEmailId'+rowId).value=document.getElementById("txtAddrEmailId").value;
		  
	   }
	  	alert("Address Saved Successfully.");  
}


function getFamilyMemAge(obj,cnt)
{
	var age;
	
	age=ageFromDateOfBirth(obj.value);
	//document.getElementById("txtFmlyMemAge"+cnt).value=age;
}

function getNomineeAge(obj,cnt)
{
	var age;

	age=ageFromDateOfBirth(obj.value);
	document.getElementById("txtNomAge"+cnt).value=age;
}

function ageFromDateOfBirth(birthDate)
{
	var splitDate=birthDate.split("/");							
	var bday=parseInt(splitDate[0],10);
	var bmo=(parseInt(splitDate[1],10)-1);
	var byr=parseInt(splitDate[2]);
	var age;
	var now = new Date();		
	tday=now.getUTCDate();
	tmo=(now.getUTCMonth());
	tyr=(now.getUTCFullYear());
	if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr;}
	else  {age=byr+1;}
	if(isNaN(tyr-age)==true)
	{
		return 0;
	}
	else if((tyr-age)>150 || (tyr-age)<=-1)			
	{			
		return "N.A.";
	}
	else
	{	
		return tyr-age;	}
}


function populateValues(obj,rowCnt)   
{           
	var nomineeName=document.getElementById("txtNameOfNominee"+rowCnt).value;
	var nomineeRelation=obj.value;
	var arrName = new Array();        
	arrName = document.getElementsByName("txtNameOfFamilyMember");               
	               
	for(var i = 0; i < arrName.length; i++)       
	{            
		var fmlyMemName = document.getElementsByName("txtNameOfFamilyMember").item(i); 
		var fmlyMemRelation = document.getElementsByName("cmbFmlyMemRelation").item(i); 
		
		if(fmlyMemName.value.toLowerCase().trim() ==nomineeName.toLowerCase().trim() && fmlyMemRelation.value==nomineeRelation)
		{
		
			document.getElementById('radioIsNomAddrSameY'+rowCnt).checked=true;
			document.getElementById('lnkViewNomAddr'+rowCnt).style.display="inline";
		    document.getElementById('txtNomDateOfBirth'+rowCnt).value=document.getElementsByName("txtFmlyMemDateOfBirth").item(i).value;
			document.getElementById('txtNomAge'+rowCnt).value=document.getElementsByName("txtFmlyMemAge").item(i).value;
			document.getElementById('hidNomState'+rowCnt).value=document.getElementsByName("hidFmlyMemState").item(i).value;
			document.getElementById('hidNomDistrict'+rowCnt).value=document.getElementsByName("hidFmlyMemDistrict").item(i).value;
			document.getElementById('hidNomArea'+rowCnt).value=document.getElementsByName("hidFmlyMemArea").item(i).value;
			document.getElementById('hidNomRoad'+rowCnt).value=document.getElementsByName("hidFmlyMemRoad").item(i).value;
			document.getElementById('hidNomFlat'+rowCnt).value=document.getElementsByName("hidFmlyMemFlat").item(i).value;
			document.getElementById('hidNomPinCode'+rowCnt).value=document.getElementsByName("hidFmlyMemPinCode").item(i).value;
			document.getElementById('hidNomLandLineNo'+rowCnt).value=document.getElementsByName("hidFmlyMemLandLineNo").item(i).value;
			document.getElementById('hidNomMobileNo'+rowCnt).value=document.getElementsByName("hidFmlyMemMobileNo").item(i).value;
			document.getElementById('hidNomEmailId'+rowCnt).value=document.getElementsByName("hidFmlyMemEmailId").item(i).value;
		}
		    
	}   
}

function totalNomPercentage(field,fieldName,alrtStr)
{
	
	if(field.value!='')
	{
		var rowCount=Number(document.getElementById("hidNomGridSize").value);
		var total=0;
		for(var cnt=0;cnt<(rowCount+1);cnt++)
		{
			if(document.getElementById(fieldName+cnt)!=null && document.getElementById(fieldName+cnt).value!='')
			{
				total=total+Number(document.getElementById(fieldName+cnt).value);
			}
		}
		if(Number(total) > 100)
		{
			alert(alrtStr);
			field.value='';
			field.focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	return false;
}


function getFmlyAddrFlagValues()
{
	var arrFmlyAddrFlag = new Array();
	var rowCnt=0;
	var addrFlag = document.getElementsByClassName("Family");
	var cnt=0;
	for(var i=0;i<addrFlag.length;i++)
	{
		if(document.getElementById(addrFlag[i].id).checked)
		{
		    rowCnt = addrFlag[i].id.substring(20, addrFlag[i].id.length);
		   
			if(document.getElementById('radioIsFmlyAddrSameY'+rowCnt).checked)
				arrFmlyAddrFlag[cnt]='Y';
			if(document.getElementById('radioIsFmlyAddrSameN'+rowCnt).checked)
				arrFmlyAddrFlag[cnt]='N';
			cnt++;
		}
 
	}
	document.getElementById("hidFmlyAddrFlag").value=arrFmlyAddrFlag.join(',');

}

function getNomAddrFlagValues()
{
	var arrNomAddrFlag = new Array();
	var rowCnt=0;
	var addrFlag = document.getElementsByClassName("Nominee");
	var cnt=0;
	for(var i=0;i<addrFlag.length;i++)
	{
		if(document.getElementById(addrFlag[i].id).checked)
		{
		    rowCnt = addrFlag[i].id.substring(19, addrFlag[i].id.length);
		   
			if(document.getElementById('radioIsNomAddrSameY'+rowCnt).checked)
				arrNomAddrFlag[cnt]='Y';
			if(document.getElementById('radioIsNomAddrSameN'+rowCnt).checked)
				arrNomAddrFlag[cnt]='N';
			cnt++;
		}
  
	}
	document.getElementById("hidNomAddrFlag").value=arrNomAddrFlag.join(',');

}

function validPensionCaseFamilyDtls()
{
	 var fmlyCntLength=document.getElementById("hidFamilyGridSize").value;
	 if(fmlyCntLength>=1)
	 {
		for(var rowFmlyCnt=0;rowFmlyCnt<Number(fmlyCntLength);rowFmlyCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtNameOfFamilyMember"+rowFmlyCnt,NAMEOFFAMILYMEMBER,'3')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbFmlyMemRelation"+rowFmlyCnt,RELATION,'3')==false)
				{
					return false;
				}
				/*else if(IsEmptyFun("txtPercentage"+rowFmlyCnt,AMTIFSHAREORGRTY,'3')==false)
				{
					return false;
				}*/
				else if(IsEmptyFun("txtFmlyMemDateOfBirth"+rowFmlyCnt,DATEOFBIRTH,'3')==false)
				{
					return false;
				}
				
				
								 
			}
			catch(ex)
			{
				
			}
		}
		 var noOfFamilyMem =0;
		 var eleFmlyMem ="";
		
			
			var chkFamilyMember = document.getElementsByName("chkFamlyPen");
			
			for(var k=0;k<chkFamilyMember.length;k++)
			{
				//alert("Inside for");
				if(chkFamilyMember[k].checked)
				{
					//alert("Inside if 1");
					noOfFamilyMem = noOfFamilyMem +1;
				}
				eleFmlyMem =  chkFamilyMember[k].id;
			}
			if(noOfFamilyMem > 1 || noOfFamilyMem == 0)
			{
				alert("Please select one family member as a family pensioner.");
				goToFieldTab(eleFmlyMem,2);
				return false;
			}
	 }
	 
	
	 
	 
	/* var nomCntLength=document.getElementById("hidNomGridSize").value;
	 if(nomCntLength>=1)
	 {
		for(var rowNomCnt=0;rowNomCnt<Number(nomCntLength);rowNomCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtNameOfNominee"+rowNomCnt,NAMEOFNOMINEE,'3')==false)
				{
					return false;
				}
				if(IsEmptyFun("cmbNomineeMemRelation"+rowNomCnt,RELATION,'3')==false)
				{
					return false;
				}
				
				if(document.getElementById("cmbNomBank"+rowNomCnt).value != "-1" || document.getElementById("cmbNomBankBranch"+rowNomCnt).value != "-1"){
					if(IsEmptyFun("cmbNomBank"+rowNomCnt,BANKNAME,'3')==false)
					{
						return false;
					}
					if(IsEmptyFun("cmbNomBankBranch"+rowNomCnt,BRANCHNAME,'3')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtNomAccountNo"+rowNomCnt,ACCNO,'3')==false)
					{
						return false;
					}
				}
			}
			catch(ex)
			{
				
			}
		}
	 }*/
}

function validateAddress(address)
{
	if(document.getElementById("cmbAddrState").value==-1)
	{
		alert(STATE);
		document.getElementById("cmbAddrState").focus();
		return false;
	}
	if(document.getElementById("cmbAddrTownCityDist").value==-1)
	{
		alert(TOWNCITYDIST);
		document.getElementById("cmbAddrTownCityDist").focus();
		return false;
	}
	if(isEmpty("txtAddrPincode",PINCODE)==false)
	{
		document.getElementById("txtAddrPincode").focus();
		return false;
	}
	saveAddress(address);	
}
function setBranchCode(rowNum)
{
	if(document.getElementById("cmbNomBankBranch"+rowNum).value == "-1")
	{
		document.getElementById("txtNomBankBranchCode"+rowNum).value="";
	}
	else
	{
		document.getElementById("txtNomBankBranchCode"+rowNum).value=document.getElementById("cmbNomBankBranch"+rowNum).value;
	}
}
function getNomBranchNameFromBankCodeUsingAJAX(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(10);
	var bankCode=document.getElementById(elementId).value;
	
	uri="ifms.htm?actionFlag=getBrnchNms&bankCode="+bankCode;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&bankCode="+bankCode,
		        onSuccess: function(myAjax) {
					getDataStateChangedForNomBranchName(myAjax,rowNum);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForNomBranchName(myAjax,rowNum)
{
			XMLDoc =  myAjax.responseXML.documentElement;
			var resultElementId="cmbNomBankBranch"+rowNum;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('BranchCode');
			var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchName');
		   	if(XmlHiddenValues.length >0)
			{
		   			var theOption = new Option;
		   			theOption.value = "-1";
		   			theOption.text ="--Select--";
		   			document.getElementById(resultElementId).options[0]=theOption;
			 		document.getElementById(resultElementId).options[0].selected="selected";
			 		for( var i=0; i<XmlHiddenValues.length ;i++) {
			 		theOption = new Option;	
					theOption.value = XmlHiddenValues[i].childNodes[0].nodeValue;
					theOption.text = XmlHiddenValues1[i].childNodes[0].nodeValue;
					document.getElementById(resultElementId).options[i+1] = theOption;
			}
		}
		else
		{
			 		alert("This bank does not belong to your location.");
			 		document.getElementById(resultElementId).options.length = 0;
			 		theOption = new Option;
					theOption.value = "-1";
					theOption.text ="--Select--";
					document.getElementById(resultElementId).options[0]=theOption;
			 		document.getElementById(resultElementId).options[0].selected="selected";
			 		document.getElementById(resultElementId).options[0].disabled=true;
			 		document.getElementById("txtNomBankBranchCode"+rowNum).value="";
		}
}
function getNomBranchNameFromBankCode(object)
{
	getNomBranchNameFromBankCodeUsingAJAX(object);

}
function getNomBnkBrnchNameFrmBnkCodeUsingAJAX(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(20);
	var branchCode=document.getElementById(elementId).value;
	var resultElementBankCode="cmbNomBank"+rowNum;
	var resultElementBranchName="cmbNomBankBranch"+rowNum;
	uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
	if(document.getElementById(elementId).value != "")
	{
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&branchCode="+branchCode,
			        onSuccess: function(myAjax) {
						getDataStateChangedForBankBranchName(myAjax,resultElementBankCode,resultElementBranchName,branchCode,elementId);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		
		document.getElementById(resultElementBankCode).disabled=false;
		document.getElementById(resultElementBranchName).disabled=false;
	}
}
function getDataStateChangedForBankBranchName(myAjax,resultElementBankCode,resultElementBranchName,branchCode,elementId)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	if(XmlHiddenValues[0].childNodes.length != 0)
	{
	var branchName=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	
	var bankCode=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	var bankName=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	var auditorName=XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;

	document.getElementById(resultElementBankCode).options[0].value=bankCode;
	document.getElementById(resultElementBankCode).options[0].text=bankName;
	document.getElementById(resultElementBankCode).options[0].selected="selected";
	document.getElementById(resultElementBankCode).disabled=true;
	
	var theOption = new Option;
	theOption.value = "-1";
	theOption.text ="--Select--";
	document.getElementById(resultElementBranchName).options[0]=theOption;
		document.getElementById(resultElementBranchName).options[0].selected="selected";
	document.getElementById(resultElementBranchName).options[0].value=branchCode;
	document.getElementById(resultElementBranchName).options[0].text=branchName;
	//document.getElementById(resultElementBranchName).options[0].selected="selected";
	document.getElementById(resultElementBranchName).disabled=true;
	
	}
	else
	{
		alert("Please Enter Correct Branch Code.");
		document.getElementById(elementId).value="";
		 theOption = new Option;
		theOption.value = "-1";
		theOption.text ="--Select--";
		document.getElementById(resultElementBankCode).options[0]=theOption;
 		document.getElementById(resultElementBankCode).options[0].selected="selected";
 		document.getElementById(resultElementBankCode).disabled=false;
		theOption = new Option;
		theOption.value = "-1";
		theOption.text ="--Select--";
 		document.getElementById(resultElementBranchName).options.length = 0;
 		document.getElementById(resultElementBranchName).options[0]=theOption;
 		document.getElementById(resultElementBranchName).options[0].selected="selected";
 		document.getElementById(resultElementBranchName).disabled=false;
				
	}
}
function getNomBnkBrnchNameFrmBnkCode(object){
	getNomBnkBrnchNameFrmBnkCodeUsingAJAX(object);
}

function totalPercentage(field,fieldName,alrtStr,hiddenField)
{

	if(field.value!='')
	{
		var rowCount=Number(document.getElementById(hiddenField).value);
		var total=0;
		for(var cnt=0;cnt<(rowCount+1);cnt++)
		{
			if(document.getElementById(fieldName+cnt)!=null && document.getElementById(fieldName+cnt).value!='')
			{
				total=total+Number(document.getElementById(fieldName+cnt).value);
			}
		}
		if(Number(total) > 100)
		{
			alert(alrtStr);
			goToFieldTab("",3);
			field.value='';
			field.focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	return false;
}

function chkDigitAmnt(lThis)
{
	if(lThis.value > 100)
	{
		alert("Percentage must not be greater than 100");
		goToFieldTab(lThis.id,3);
		lThis.value = "";
	}
}

function chkForsum100(lThis)
{
	if(Number(lThis.value) != 100 && Number(lThis.value)>0 )
	{
		alert("Family Member Percentage Must be 100 or 0");		
		goToFieldTab(lThis.id,3);
		lThis.value = "";
	}
}

function IsEmptyPercentage(varStr,alrtStr,tabNo)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if(element == "" || element.length == 0)
	{
		alert(alrtStr);
		goToFieldTab(varStr,tabNo);
		return false;
	}
	return true;
}
function setBranchCode(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(16);

	var branchCode=document.getElementById(elementId).value;

	if(branchCode != -1)
	{
		document.getElementById("txtNomBankBranchCode"+rowNum).value = branchCode;
//		uri="ifms.htm?actionFlag=getAudiNms&branchCode="+branchCode;
//		var myAjax = new Ajax.Request(uri,
//			       {
//			        method: 'post',
//			        asynchronous: false,
//			        parameters: "&branchCode="+branchCode,
//			        onSuccess: function(myAjax) {
//			getDataStateChangedForNomAudName(myAjax,rowNum);
//					},
//			        onFailure: function(){ alert('Something went wrong...')} 
//			          } );
	}
	else{
		document.getElementById("txtNomBankBranchCode"+rowNum).value = "";
		
	}
}
function getDataStateChangedForNomAudName(myAjax,rowNum)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var auditorName="";
	
	if(XmlHiddenValues[0].childNodes[0].childNodes.length > 0)
	{
		auditorName=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	}
	if(auditorName != "")
	{
		
	}
	else{
		alert("No auditor is mapped with selected bank branch.Please select other bank branch.");
		document.getElementById("txtNomBankBranchCode"+rowNum).value = "";
		document.getElementById("cmbNomBankBranch"+rowNum).options[0].value="-1";
		document.getElementById("cmbNomBankBranch"+rowNum).options[0].selected="selected";
	}
}
function getNomAudNameFromBranchCode(object)
{
	setBranchCode(object);

}


function enableGuardianName(rowNum)
{
	
	var phyHandicap = document.getElementById("cmbPhyHandicap"+rowNum).value;
	if(phyHandicap == 'Y' || document.getElementById("chkMinor"+rowNum).checked==true){
		document.getElementById("txtFMGuardianName"+rowNum).readOnly=false;
	}else{
		document.getElementById("txtFMGuardianName"+rowNum).value='';
		document.getElementById("txtFMGuardianName"+rowNum).readOnly=true;
	}
}

	
	
function enableFamilyDetails(){
	
	
	var radioFamilyDtls = document.PensionInwardForm.radioFamilyPen;
	var radioValue;
	for(var i=0; i < radioFamilyDtls.length; i++){		
		if(radioFamilyDtls[i].checked) {
			radioValue = radioFamilyDtls[i].value;
		}
	}
	
	if(radioValue == 'Y'){
		
		
		document.getElementById("familyDtlsAddRow").disabled=false;
	}else{
		
		
		if(document.getElementById("tblFamilyDtls").rows.length>1)
		{
		
			alert('First kindly delete all the Family details and then select No option');
			document.getElementById("radioFamilyPenNo").checked=false;
			document.getElementById("radioFamilyPenYes").checked="checked";
			return;
		}
		else
		{
			document.getElementById("familyDtlsAddRow").disabled=true;
		}
		
		
	}
}
	
	
	
	
	
	
	

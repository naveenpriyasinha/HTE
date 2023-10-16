var EvtgridCount = 0;
var SergridCount = 0;
var qulfSerCount=0;
function CertiDtlsTableAddRow()
{
	    if(validPensionCaseCheckList()==true)
	    {
     	var rowCnt = document.getElementById("hidGridSize").value;
     	var newRow =  document.getElementById("tblCertDtls").insertRow(document.getElementById("tblCertDtls").rows.length);	
		var Cell1 = newRow.insertCell(-1);
		Cell1.className = "tds";
	   	Cell1.align="center";
   		var Cell2 = newRow.insertCell(-1);
   		Cell2.className = "tds";
	   	Cell2.align="center";
   		/*var Cell3 = newRow.insertCell(-1);
   		Cell3.className = "tds";
	   	Cell3.align="center";*/
   		/*var Cell4 = newRow.insertCell(-1);
   		Cell4.className = "tds";
	   	Cell4.align="center";*/
   		var Cell5 = newRow.insertCell(-1);
   		Cell5.className = "tds";
	   	Cell5.align="center";
   		var Cell6 = newRow.insertCell(-1);
   		Cell6.className = "tds";
	   	Cell6.align="center";
   	//	var Cell7 = newRow.insertCell(-1);
   		
   		
  		EvtgridCount = parseInt(rowCnt);
   	
   		Cell1.innerHTML = '<input type="text" class="'+Number(rowCnt)+'" name="txtCert" id="txtCert'+Number(rowCnt)+'" value="'+document.getElementById('cmbCertificate').options[document.getElementById('cmbCertificate').selectedIndex].text+'"  size="20"   readonly="readonly" style="width:60px" />';
   		Cell2.innerHTML = '<input type="text" class="'+Number(rowCnt)+'" name="txtOfficeName" id="txtOfficeName'+Number(rowCnt)+'" value="'+document.getElementById('txtCheckListOffName').value+'" style="width:90px"   readonly="readonly"  style="width:60px" />';
   		//Cell3.innerHTML = '<input type="text" class="'+Number(rowCnt)+'"    name="txtDateOfFrom" value="'+document.getElementById('txtFromDate').value+'" style="width:90px" onblur=\"validateDate(this);\" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" id="txtDateOfFrom'+Number(rowCnt)+'"  readonly="readonly" /> ';  		
   		//Cell4.innerHTML = '<input type="text" class="'+Number(rowCnt)+'"   name="txtDateOfTo" value="'+document.getElementById('txtToDate').value+'" style="width:90px" onblur=\"validateDate(this);\" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" id="txtDateOfTo'+Number(rowCnt)+'"   readonly="readonly"  /> ' ; 
   		Cell5.innerHTML = '<img name="Image'+Number(rowCnt)+'" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblCertDtls")\'/><input type="hidden" class="'+Number(rowCnt)+'" id="pk'+Number(rowCnt)+'" name="pk'+Number(rowCnt)+'">';
   		Cell6.innerHTML = '<input type="button"  id="btnUpdate'+Number(rowCnt)+'"  name="btnUpdate'+Number(rowCnt)+'" value="Update" onClick=\'UpdateTableRow("'+Number(rowCnt)+'")\' />'+
   						  '<input type="hidden" class="'+Number(rowCnt)+'" size="20"   name="cmbCertId" value="'+document.getElementById('cmbCertificate').value+'"  id="cmbCertId'+Number(rowCnt)+'" /> '+
   		                  '<input type="hidden" class="'+Number(rowCnt)+'" size="20"   name="cmbOfficeId" value="'+document.getElementById('txtCheckListOffName').value+'"  id="cmbOfficeId'+Number(rowCnt)+'" /> '+
   		                  '<input type="hidden" class="'+Number(rowCnt)+'" id="count'+Number(rowCnt)+'" name="count'+Number(rowCnt)+'" value="'+Number(rowCnt)+'">';
   		document.getElementById("hidGridSize").value = Number(rowCnt)+1;
   		resetCheckListFields();
	    }
}


function UpdateTableRow(clsName)
{
	
	var updateDtl = document.getElementsByClassName(clsName);
	/*document.getElementById("txtFromDate").value = updateDtl[2].value;
	document.getElementById("txtToDate").value=updateDtl[3].value;*/
	document.getElementById("cmbCertificate").value=updateDtl[3].value;
    document.getElementById("txtCheckListOffName").value= updateDtl[4].value;
    document.getElementById("rowCount").value= updateDtl[5].value;
	
}

function UpdateTableDetail()
{
	var varCnt1 = document.getElementById("rowCount").value;
	var updtDtl = document.getElementsByClassName(varCnt1);
	updtDtl[0].value = document.getElementById('cmbCertificate').options[document.getElementById('cmbCertificate').selectedIndex].text;
	updtDtl[1].value =document.getElementById('txtCheckListOffName').value;
	document.getElementById("cmbCertificate").value= "-1";
    document.getElementById("txtCheckListOffName").value= "";
	/*updtDtl[2].value = document.getElementById("txtFromDate").value;
	updtDtl[3].value = document.getElementById("txtToDate").value;*/
	
}

function validPensionCaseCheckList()
{
	if(IsEmptyFun("cmbCertificate",CERTIFICATE,'5')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtCheckListOffName",OFFICE,'5')==false)
	{
		return false;
	}
	
	/*if(IsEmptyFun("txtFromDate",CERTFROMDATE,'5')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtToDate",CERTTODATE,'5')==false)
	{
		return false;
	}*/
	return true;
}

function resetCheckListFields()
{
	document.getElementById("cmbCertificate").value=-1;
	document.getElementById("txtCheckListOffName").value="";
	/*document.getElementById("txtFromDate").value="";
	document.getElementById("txtToDate").value="";*/
}
function NoDENoDuesPWD(cmbCertificate) 
{
	document.getElementById('descscan').readOnly=true;
	if(cmbCertificate.options[cmbCertificate.selectedIndex].text=='NO DE')
	{
		document.getElementById('descscan').value='NO DE Certificate';
	}
	else if(cmbCertificate.options[cmbCertificate.selectedIndex].text=='NO DUE')
	{
		document.getElementById('descscan').value='No Due Certificate';
	}
	else if(cmbCertificate.options[cmbCertificate.selectedIndex].text=='PWD')
	{
		document.getElementById('descscan').value='PWD Certificate';
	}
	else
	{
		document.getElementById('descscan').value='';
	}
}


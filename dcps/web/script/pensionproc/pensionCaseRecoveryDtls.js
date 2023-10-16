var EvtgridCount = 0;
var SergridCount = 0;
var qulfSerCount=0;

function advDtlsAddRow()
{
	var rowCnt = document.getElementById("hidAdvDtlGridSize").value;
	var newRow =  document.getElementById("tblTypeAdvDtls").insertRow(document.getElementById("tblTypeAdvDtls").rows.length);
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
		
		
	Cell1.innerHTML = '<select name="cmbTypeOfAdv" id="cmbTypeOfAdv'+Number(rowCnt)+'"><option value="-1">--Select--</option>' +ADVNCETYPELIST+ '</select>';
	Cell2.innerHTML = '<input type="text" name="txtAdvAmount" maxlength="7" id="txtAdvAmount'+Number(rowCnt)+'" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);calAdvanceAmtTotal(this,\'txtAdvAmount\')" style="width:90px;text-align: right" />';
	Cell3.innerHTML = '<input type="text" name="txtAdvSchemeCode" id="txtAdvSchemeCode'+Number(rowCnt)+'" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" /><a href="#" id="txtAdvSchemeCode'+Number(rowCnt)+'" onclick="showSchemeCodePopup(this);"><img src="images/search.gif" /></a>';
	Cell4.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick="adjustTotalAdvanceAmnt('+Number(rowCnt)+');RemoveTableRow(this,\'tblTypeAdvDtls\');"/>';
	document.getElementById("hidAdvDtlGridSize").value = Number(rowCnt)+1;
}
function adjustTotalAdvanceAmnt(count)
{
	if(document.getElementById("tblTypeAdvDtls").rows.length>1)
	{
		var tempAmnt = Number(document.getElementById("txtAdvAmount"+count).value);
		document.getElementById("txtTotalAdvanceBalAmt").value =  Number(document.getElementById("txtTotalAdvanceBalAmt").value) - tempAmnt;
	}
	else
	{
		document.getElementById("txtTotalAdvanceBalAmt").value=0;
	}
}
function natureDtlsAddRow()
{
	var rowCnt = document.getElementById("hidNatureGridSize").value;
	var newRow =  document.getElementById("tblNatureDtls").insertRow(document.getElementById("tblNatureDtls").rows.length);	
	var Cell1 = newRow.insertCell(0);
	Cell1.className = "tds";
	Cell1.align="center";
	var Cell2 = newRow.insertCell(1);
	Cell2.className = "tds";
	Cell2.align="center";
	var Cell3 = newRow.insertCell(2);
	Cell3.className = "tds";
	Cell3.align="center";
		
	Cell1.innerHTML = '<input type="text" name="cmbNature" id="cmbNature'+Number(rowCnt)+'" onblur="onBlur(this);" style="width:90px;text-align: left" ></input>';
	Cell2.innerHTML = '<input type="text" name="txtNatrAmount" id="txtNatrAmount'+Number(rowCnt)+'" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);calAssessedDueTotal(this,\'txtNatrAmount\')"    style="width:90px;text-align: right" />';
	Cell3.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick="adjustTotalAssessDuesAmnt('+Number(rowCnt)+');RemoveTableRow(this,\'tblNatureDtls\');"/>';
	document.getElementById("hidNatureGridSize").value = Number(rowCnt)+1;
}
function adjustTotalAssessDuesAmnt(count)
{
	if(document.getElementById("tblNatureDtls").rows.length>1)
	{
		var tempAmnt = Number(document.getElementById("txtNatrAmount"+count).value);
		document.getElementById("txtTotalAssessDues").value =  Number(document.getElementById("txtTotalAssessDues").value) - tempAmnt;
	}
	else
	{
		document.getElementById("txtTotalAssessDues").value=0;
	}
}
function validPensionCaseRecovery()
{
	
	/*if(IsEmptyFun("txtOverPayAllow",OVERPAYALLOW,'4')==false)
	{
		return false;
	}
	
	if(IsEmptyFun("txtArrearAmnt",ARREAROFLICFEE,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtGovtAcmdtnRetAmnt",AMTOFLICFEE,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtGratuityAmnt",AMTGRTY,'4')==false)
	{
		return false;
	}*/
	/*if(IsEmptyFun("txtDateOfMedCert",DTOFMEDCERT,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDateOfLodgFir",DTOFLODGFIR,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtNoOfCertificate",NUMOFCERT,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtPoliceStnName",POLICESTNNAME,'4')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDateOfCert",DATEOFCERT,'4')==false)
	{
		return false;
	}*/
	
	 var advDtlCntLength=document.getElementById("hidAdvDtlGridSize").value;
	 if(advDtlCntLength>=1)
	 {
		for(var rowAdvCnt=0;rowAdvCnt<Number(advDtlCntLength);rowAdvCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("cmbTypeOfAdv"+rowAdvCnt,TYPEOFADVANCE,'4')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAdvAmount"+rowAdvCnt,ADVANCEAMT,'4')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAdvSchemeCode"+rowAdvCnt,SCHEMECODE,'4')==false)
				{
					return false;
				}					
				 
			}
			catch(ex)
			{
				
			}
		}
	 }
	/* var natCntLength=document.getElementById("hidNatureGridSize").value;
	 if(natCntLength>1)
	 {
		for(var rowNatCnt=1;rowNatCnt<Number(natCntLength);rowNatCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("cmbNature"+rowNatCnt,NATURE,'4')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtNatrAmount"+rowNatCnt,NATUREAMT,'4')==false)
				{
					return false;
				}
				 
			}
			catch(ex)
			{
				
			}
		}
	 }*/
	return true;
}

function calAssessedDueTotal(field,fieldName)
{
	try
	{
		if(field.value!='')
		{
			var rowCount=Number(document.getElementById("hidNatureGridSize").value);
			var total=0;
			for(var cnt=0;cnt<(rowCount+1);cnt++)
			{
				if(document.getElementById(fieldName+cnt)!=null && document.getElementById(fieldName+cnt).value!='')
				{
					if(Number(document.getElementById(fieldName+cnt).value) <=0)
					{
						alert('Invalid amount.');
						document.getElementById(fieldName+cnt).value="";
						document.getElementById(fieldName+cnt).focus();
						return true;
					}
					total=total+Number(document.getElementById(fieldName+cnt).value);
					document.getElementById("txtTotalAssessDues").value=total;
					
				}
			}
			if(total == 0)
			{
				alert('Invalid amount.');
				document.getElementById("txtTotalAssessDues").value="";
				return true;
			}
		}
	}catch(ex)
	{
		
	}
	return false;
}

function calAdvanceAmtTotal(field,fieldName)
{
	try
	{
		if(field.value!='')
		{
			var rowCount=Number(document.getElementById("hidAdvDtlGridSize").value);
			var total=0;
			for(var cnt=0;cnt<(rowCount+1);cnt++)
			{
				if(document.getElementById(fieldName+cnt)!=null && document.getElementById(fieldName+cnt).value!='')
				{
					if(Number(document.getElementById(fieldName+cnt).value) <=0)
					{
						alert('Invalid amount.');
						document.getElementById(fieldName+cnt).value="";
						document.getElementById(fieldName+cnt).focus();
						return true;
					}
					total=total+Number(document.getElementById(fieldName+cnt).value);
					document.getElementById("txtTotalAdvanceBalAmt").value=total;
					
				}
			}
			if(total == 0)
			{
				alert('Invalid amount.');
				document.getElementById("txtTotalAdvanceBalAmt").value="";
				return true;
			}
			
		}
	}catch(ex)
	{
		
	}
	return false;
}

if(document.getElementById("btnSaveForward")!=null)
{
	if(document.getElementById("cmbForwardTo").value==-1){
		document.getElementById("btnSaveForward").disabled=true;	
		if(document.getElementById("btnSave")!=null)
		  document.getElementById("btnSave").disabled=false;
	}
	else{
		if(document.getElementById("btnSave")!=null)
		  document.getElementById("btnSave").disabled=true;
		document.getElementById("btnSaveForward").disabled=false;	
	}
}
function addAdvanceDtl(obj)
{
	if(obj.value=='Y')
	{
		document.getElementById("btnAdvDtlsAddRow").disabled=false;
	}
	else
	{
		if(document.getElementById("tblTypeAdvDtls").rows.length>1)
		{
			alert('You cannot select this option because advance balance details are present.');
			document.getElementById(obj.id).checked=false;
			document.getElementById("radioBalOfAdvY").checked="checked";
			return;
		}
		else
		{
			document.getElementById("txtTotalAdvanceBalAmt").value=0;
			document.getElementById("btnAdvDtlsAddRow").disabled=true;
		}
	}
}
function addAssessedDues(obj)
{
	if(obj.value=='Y')
	{
		document.getElementById("btnAssessedDuesAddRow").disabled=false;
	}
	else
	{
		if(document.getElementById("tblNatureDtls").rows.length>1)
		{
			alert('You cannot select this option because assessed dues and nature thereof details are present.');
			document.getElementById(obj.id).checked=false;
			document.getElementById("radioOtherAssessDueY").checked="checked";
			return;
		}
		else
		{
			document.getElementById("txtTotalAssessDues").value=0;
			document.getElementById("btnAssessedDuesAddRow").disabled=true;
		}
	}
}

function validateSchemeCode(object)
{
	var schemeCode=object.value;
	var url;
	if(schemeCode != "")
	{
		url="ifms.htm?actionFlag=validateSchemeCode&SchemeCode="+schemeCode+"&SchemeType=R";
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
function showSchemeCodePopup(object)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var url='ifms.htm?actionFlag=loadSchemeCodePopUp&schemeType=R&elementId='+object.id;
	var urlstyle = "height=500,width=1000,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "DCRGHistory", urlstyle);
}
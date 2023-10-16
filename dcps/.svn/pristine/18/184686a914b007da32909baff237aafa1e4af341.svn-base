var Row_ID_Family=0;
var Row_ID_Nom=0;
function familyDtlsTableAddRow()
{	
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
   	   	   
   	Cell1.innerHTML = '<input type="hidden" name="hdnFamilyMemberId" id="hdnFamilyMemberId'+Row_ID_Family+'"/><input type="text" name="txtFMName" id="txtFMName'+Row_ID_Family+'" size="20" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" maxlength="100"/>';
	Cell2.innerHTML = '<select name="cmbRelation" id="cmbRelation'+Row_ID_Family+'"  > <option value="-1">--Select--</option>'+ LISTRELATIONSHIP +'</select>';      	
   	Cell3.innerHTML = '<input type="text" name="txtPercentage" id="txtPercentage'+Row_ID_Family+'" size="15" maxlength="6" onfocus="onFocus(this)" onblur="onBlur(this);chkDigitAmnt(this);if(chkForsum100(this)!=false && totalPercentage(this,\'txtPercentage\',\'Total Percentage cannot be greater than 100\',\'hidFamilyGridSize\')!=false){};" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell4.innerHTML = '<input type="text" name="txtFMDateOfBirth" id="txtFMDateOfBirth'+Row_ID_Family+'"style="width:90px" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFMDateOfBirth'+Row_ID_Family+'\', 375, 570, \'\', \'\', '+Row_ID_Family+');" />';   
   	Cell5.innerHTML = '<input type="checkbox" id="chkMinor'+Row_ID_Family+'" name="chkMinor" value="Y" />';
   	Cell6.innerHTML = '<input type="checkbox" id="chkMarried'+Row_ID_Family+'" name="chkMarried" value="Y" />';
   	Cell7.innerHTML = '<input type="text" name="txtFMSalary" id="txtFMSalary'+Row_ID_Family+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell8.innerHTML = '<select name="cmbPhyHandicap" id="cmbPhyHandicap'+Row_ID_Family+'"  style="width: 100%"> <option value="-1">--Select--</option><option value="Y">Yes</option><option value="N">No</option></select>';
   	Cell9.innerHTML = '<input type="text" name="txtFMGuardianName" id="txtFMGuardianName'+Row_ID_Family+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" maxlength="100"/>';
   	Cell10.innerHTML = '<input type="text" name="txtFMDateOfDeath" id="txtFMDateOfDeath'+Row_ID_Family+'" style="width:90px" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFMDateOfDeath'+Row_ID_Family+'\', 375, 570, \'\', \'\', '+Row_ID_Family+');" />';
   	Cell11.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblPnsnrFamilyDtls\')" /> ';
   	document.getElementById("hidFamilyGridSize").value = Number(Row_ID_Family)+1;  	
}

function nomineeDtlsTableAddRow()
{	
	Row_ID_Nom = document.getElementById("hidNomGridSize").value;
	// newRow =  document.all("tblNomineeDtls").insertRow();	
	
	var table=document.getElementById("tblNomineeDtls");
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
   	
   	Cell1.innerHTML = '<input type="hidden" name="hdnNomineeId" id="hdnNomineeId'+Row_ID_Nom+'"/><input type="text" name="txtNomineeName" id="txtNomineeName'+Row_ID_Nom+'"  size="20" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" maxlength="50"/>';
	Cell2.innerHTML = '<input type="text" name="txtNomPercentage" id="txtNomPercentage'+Row_ID_Nom+'" maxlength="6" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);if(isPercentage(this,\'Value cannot be grater than 100.\',\'100\')!=false && totalPercentage(this,\'txtNomPercentage\',\'Total Percentage cannot be greater than 100\',\'hidNomGridSize\')!=false){}" onkeypress="amountFormat(this);"  style="text-align: right" />';      	
   	Cell3.innerHTML = '<input type="text" name="txtNomBankBranchCode" id="txtNomBankBranchCode'+Row_ID_Nom+'" size="15" onblur="getNomBnkBrnchNameFrmBnkCode(this)" onKeyPress="numberFormat(this)"/>';
   	Cell4.innerHTML = '<select name="cmbNomBank" id="cmbNomBank'+Row_ID_Nom+'"  style="width: 100%" onchange="getNomBranchNameFromBankCode(this)"> <option value="-1">--Select--</option>'+LISTBANKS+'</select>';   
   	Cell5.innerHTML = '<select name="cmbNomBankBranch" id="cmbNomBankBranch'+Row_ID_Nom+'"  style="width: 100%" onchange="getNomAudNameFromBranchCode(this);"> <option value="-1">--Select--</option></select>';
   	Cell6.innerHTML = '<input type="text" name="txtNomAccountNo" id="txtNomAccountNo'+Row_ID_Nom+'" size="25" maxlength="20"/>';
   	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblNomineeDtls\')" /> ';   	
	document.getElementById("hidNomGridSize").value = Number(Row_ID_Nom)+1;  	
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
//	var elementId=object.id;
//	var rowNum=elementId.substring(10);
//	var bankCode=document.getElementById(elementId).value;
//	
//	xmlHttp=GetXmlHttpObject();
//
//	if (xmlHttp==null)
//	{
//		alert ("Your browser does not support AJAX!");
//		return;
//	} 
//
//	uri="ifms.htm?actionFlag=getBrnchNms&bankCode="+bankCode;
//	
//	xmlHttp.onreadystatechange=function getDataStateChangedForBranchName(){
//		
//		if (xmlHttp.readyState==4)
//		{ 
//			if(xmlHttp.status==200)
//			{
//				
//				XMLDoc = xmlHttp.responseXML.documentElement;
//				var resultElementId="cmbNomBankBranch"+rowNum;
//				var XmlHiddenValues = XMLDoc.getElementsByTagName('BranchCode');
//				var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchName');
//			   	if(XmlHiddenValues.length >0)
//    			{
//			   			var theOption = new Option;
//			   			theOption.value = "-1";
//			   			theOption.text ="--Select--";
//			   			document.getElementById(resultElementId).options[0]=theOption;
//				 		document.getElementById(resultElementId).options[0].selected="selected";
//				 		for( var i=1; i<XmlHiddenValues.length ;i++) {
//				 		theOption = new Option;	
//						theOption.value = XmlHiddenValues[i].text;
//						theOption.text = XmlHiddenValues1[i].text;
//						document.getElementById(resultElementId).options[i] = theOption;
//				}
//    		}
//			else
//			{
//				 		alert("This bank does not belong to your location.");
//				 		document.getElementById(resultElementId).options.length = 0;
//				 		theOption = new Option;
//						theOption.value = "-1";
//						theOption.text ="--Select--";
//						document.getElementById(resultElementId).options[0]=theOption;
//				 		document.getElementById(resultElementId).options[0].selected="selected";
//				 		document.getElementById(resultElementId).options[0].disabled=true;
//				 		document.getElementById("txtNomBankBranchCode"+rowNum).value="";
//			}
//	      }
//		}
//	};
//	xmlHttp.open("POST",uri,false);
//	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	xmlHttp.send(uri);

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
//	var elementId=object.id;
//	var rowNum=elementId.substring(20);
//	var branchCode=document.getElementById(elementId).value;
//	var resultElementBankCode="cmbNomBank"+rowNum;
//	var resultElementBranchName="cmbNomBankBranch"+rowNum;
//	
//	if(document.getElementById(elementId).value != "")
//	{
//	
//	xmlHttp=GetXmlHttpObject();
//
//	if (xmlHttp==null)
//	{
//		alert ("Your browser does not support AJAX!");
//		return;
//	} 
//
//	uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
//	
//	
//	
//	xmlHttp.onreadystatechange=function getDataStateChangedForBnkBrnchAudiName()
//	{
//		if (xmlHttp.readyState==4)
//		{ 
//			if(xmlHttp.status==200)
//			{
//				XMLDoc = xmlHttp.responseXML.documentElement;
//				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//				if(XmlHiddenValues[0].childNodes.length != 0)
//				{
//				var branchName=XmlHiddenValues[0].childNodes[0].text;
//				
//				var bankCode=XmlHiddenValues[0].childNodes[1].text;
//				var bankName=XmlHiddenValues[0].childNodes[2].text;
//				var auditorName=XmlHiddenValues[0].childNodes[3].text;
//			
//				document.getElementById(resultElementBankCode).options[0].value=bankCode;
//				document.getElementById(resultElementBankCode).options[0].text=bankName;
//				document.getElementById(resultElementBankCode).options[0].selected="selected";
//				document.getElementById(resultElementBankCode).disabled=true;
//				
//				var theOption = new Option;
//				theOption.value = "-1";
//				theOption.text ="--Select--";
//				document.getElementById(resultElementBranchName).options[0]=theOption;
//		 		document.getElementById(resultElementBranchName).options[0].selected="selected";
//				document.getElementById(resultElementBranchName).options[0].value=branchCode;
//				document.getElementById(resultElementBranchName).options[0].text=branchName;
//				//document.getElementById(resultElementBranchName).options[0].selected="selected";
//				document.getElementById(resultElementBranchName).disabled=true;
//				
//				}
//				else
//				{
//					alert("Please Enter Correct Branch Code.");
//					document.getElementById(elementId).value="";
//					var theOption = new Option;
//					theOption.value = "-1";
//					theOption.text ="--Select--";
//					document.getElementById(resultElementBankCode).options[0]=theOption;
//			 		document.getElementById(resultElementBankCode).options[0].selected="selected";
//			 		document.getElementById(resultElementBankCode).disabled=false;
//					theOption = new Option;
//					theOption.value = "-1";
//					theOption.text ="--Select--";
//			 		document.getElementById(resultElementBranchName).options.length = 0;
//			 		document.getElementById(resultElementBranchName).options[0]=theOption;
//			 		document.getElementById(resultElementBranchName).options[0].selected="selected";
//			 		document.getElementById(resultElementBranchName).disabled=false;
//							
//				}
//			}
//		}
//	};
//	xmlHttp.open("POST",uri,false);
//	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	xmlHttp.send(uri);
//	}
//	else
//	{
//		
//		document.getElementById(resultElementBankCode).disabled=false;
//		document.getElementById(resultElementBranchName).disabled=false;
//	}
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
		goToFieldTab(lThis.id,2);
		lThis.value = "";
	}
}

function chkForsum100(lThis)
{
	if(Number(lThis.value) != 100 && Number(lThis.value)>0 )
	{
		alert("Family Member Percentage Must be 100 or 0");
		lThis.value = "";
		goToFieldTab(lThis.id,2);
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

function validateFamilyDtls()
{
	 var familyDtlCntLength=document.getElementById("hidFamilyGridSize").value;
	 var currentDt=document.getElementById("hdnCurrDate").value;
	 if(document.getElementById('tblPnsnrFamilyDtls').rows.length > 1)
	 {
		for(var rowfmlyCnt=0;rowfmlyCnt<Number(familyDtlCntLength);rowfmlyCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtFMName"+rowfmlyCnt,FULLNAMEFAMILY,'2')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbRelation"+rowfmlyCnt,RELATIONSHIP,'2')==false)
				{
					return false;
				}
				else if(IsEmptyPercentage("txtPercentage"+rowfmlyCnt,PERCENTAGE,'2')==false)
				{
					return false;
				}
//				else if(IsEmptyFun("txtFMDateOfBirth"+rowfmlyCnt,DATEOFBIRTH,'2')==false)
//				{
//					return false;
//				}
				var fmDob = document.getElementById("txtFMDateOfBirth"+rowfmlyCnt).value;
				if(fmDob.length > 0 && currentDt.length >0)
				{
					if(fmDob != currentDt)
					{
						if(compareDate(fmDob,currentDt) == false)
						{
							alert(FMDOBCURDT);
							document.getElementById("txtFMDateOfBirth"+rowfmlyCnt).value="";
							goToFieldTab("txtFMDateOfBirth"+rowfmlyCnt,2);
							return false;
						}
					}
				}
//				else if(IsEmptyFun("cmbPhyHandicap"+rowfmlyCnt,PHYSICALHAND,'2')==false)
//				{
//					return false;
//				}
				else if(document.getElementById("chkMinor"+rowfmlyCnt).checked)
				{
					if(IsEmptyFun("txtFMGuardianName"+rowfmlyCnt,GUARDIANNAME,'2')==false)
					{
					return false;
					}
				}
				 
			}
			catch(ex)
			{
				
			}
		}
	 }
	 var nomDtlCntLength=document.getElementById("hidNomGridSize").value;
	 if(document.getElementById('tblNomineeDtls').rows.length > 1)
	 {
		for(var rowNomCnt=0;rowNomCnt<Number(nomDtlCntLength);rowNomCnt++)
		{		
			try
			{				 
				
				var bankCode=document.getElementById("cmbNomBank"+rowNomCnt).value;
				var accountNo=document.getElementById("txtNomAccountNo"+rowNomCnt).value;
				if(document.getElementById("cmbNomBank"+rowNomCnt).value == "-1" && accountNo.length > 0)
				{
					if(IsEmptyFun("cmbNomBank"+rowNomCnt,BANKNAME,'2')==false)
					{
						return false;
					}
				}
				if(bankCode != '-1' || accountNo.length > 0)
				{
					if(IsEmptyFun("txtNomineeName"+rowNomCnt,NAME,'2')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtNomPercentage"+rowNomCnt,PERCENTAGE,'2')==false)
					{
						return false;
					}
					if(IsEmptyFun("cmbNomBankBranch"+rowNomCnt,BRANCHNAME,'2')==false)
					{
						return false;
					}
					if(IsEmptyFun("txtNomAccountNo"+rowNomCnt,ACCOUNTNO,'2')==false)
					{
						return false;
					}
				}
				 
			}
			catch(ex)
			{
				
			}
		}
	 }
}
function getNomAudNameFromBranchCodeUsingAJAX(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(16);

	var branchCode=document.getElementById(elementId).value;

	if(branchCode != -1)
	{
		document.getElementById("txtNomBankBranchCode"+rowNum).value = branchCode;
		uri="ifms.htm?actionFlag=getAudiNms&branchCode="+branchCode;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&branchCode="+branchCode,
			        onSuccess: function(myAjax) {
			getDataStateChangedForNomAudName(myAjax,rowNum);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
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
	//var auditorPostId = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
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
	getNomAudNameFromBranchCodeUsingAJAX(object);
//	var elementId=object.id;
//	var rowNum=elementId.substring(16);
//
//	var branchCode=document.getElementById(elementId).value;
//
//	if(branchCode != -1)
//	{
//		document.getElementById("txtNomBankBranchCode"+rowNum).value = branchCode;
//		xmlHttp=GetXmlHttpObject();
//	
//		if (xmlHttp==null)
//		{
//			alert ("Your browser does not support AJAX!");
//			return;
//		} 
//	
//		uri="ifms.htm?actionFlag=getAudiNms&branchCode="+branchCode;
//			
//		xmlHttp.onreadystatechange=function getDataStateChangedForAudiName()
//		{
//			if (xmlHttp.readyState==4)
//			{ 
//				if(xmlHttp.status==200)
//				{
//					XMLDoc = xmlHttp.responseXML.documentElement;
//					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//					var auditorName=XmlHiddenValues[0].childNodes[0].text;
//					var auditorPostId = XmlHiddenValues[0].childNodes[1].text;
//					if(auditorName != "")
//					{
//						
//					}
//					else{
//						alert("No auditor is mapped with selected bank branch.Please select other bank branch.");
//						document.getElementById("txtNomBankBranchCode"+rowNum).value = "";
//						document.getElementById("cmbNomBankBranch"+rowNum).options[0].value="-1";
//						document.getElementById("cmbNomBankBranch"+rowNum).options[0].selected="selected";
//					}
//				}
//			}
//		};
//		xmlHttp.open("POST",uri,false);
//		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		xmlHttp.send(uri);
//	}
//	else{
//		document.getElementById("txtNomBankBranchCode"+rowNum).value = "";
//		
//	}
}
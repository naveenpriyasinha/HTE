var globalFlagType="I";

function checkGujaratiDropdownValue(object)
{
	if (object != null && object != undefined)
	{
		if (object.selectedIndex < 0)
			object.options[0].selected = true;
	}
}
function getCheckedRadioValue(radioName)
{
		var radioValue = "";
		
		objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
		
		for (iter=0;iter<objRadio.length;iter++)
		{
			if (objRadio[iter].checked)
			{
				radioValue = objRadio[iter].value;
				break;			
			}
		}
			
		return 	radioValue;
}


function fillState()
{
		
		clearComboBox('state_Eng');
		clearComboBox('district_Eng');
		clearComboBox('vidhanSabha_Eng');
		clearComboBox('taluka_Eng');
		
		if(document.getElementById('multiLang').value == 'Y'){
		
			clearComboBox('state_Guj');
			clearComboBox('district_Guj');
			clearComboBox('vidhanSabha_Guj');
			clearComboBox('taluka_Guj');
		}		
		
		
			
		/*try
		{ 
			xmlHttp=new XMLHttpRequest(); 
		}
		catch (e)
		{ // Internet Explorer 
			try
			{
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			catch (e)
			{
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
				}
				catch (e)
				{							
					alert('Ajax Not Supported'); 
					return false; 
				}
			} 
		} */
		
		if(document.getElementById('multiLang').value == 'Y'){
			if(document.getElementById('langId').value == '1')
				var selCountryCode = document.getElementById("country_Eng").value;
			else if(document.getElementById('langId').value == '2')
				var selCountryCode = document.getElementById("country_Guj").value;
		}
		else{
		
			var selCountryCode = document.getElementById("country_Eng").value;
		}
		var url = "hrms.htm?actionFlag=getStateValues&selCountryCode="+selCountryCode;
		
		/*xmlHttp.open("POST", encodeURI(url) , false); 
		xmlHttp.onreadystatechange = getStateComboValues; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); */
		
		new Ajax.Request(encodeURI(url),
		{
			method: 'POST',
			onSuccess: getStateComboValues,
		    onFailure: alertOnFailureForMstScr,
		    asynchronous: false
		} );
	}





function getStateComboValues(resXmlHttp)
{		
	var encXML=resXmlHttp.responseText;

	if(encXML!="error")
	{
		var stateList=encXML.split("$");
		
		for (var i=0; i < stateList.length;++i){
			
			var keyValPair=stateList[i].split("/");
			
			if(keyValPair[2] == 1){
				
				document.getElementById('state_Eng').options[document.getElementById('state_Eng').options.length] = new Option(keyValPair[1],keyValPair[0]); 
			}
			else{
			
				if(document.getElementById('multiLang').value == 'Y'){
					document.getElementById('state_Guj').options[document.getElementById('state_Guj').options.length] = new Option(keyValPair[1],keyValPair[0]); 
				}
			}
		}
		
	}else
	{
		clearComboBox('state_Eng');
		clearComboBox('district_Eng');
		clearComboBox('vidhanSabha_Eng');
		clearComboBox('taluka_Eng');
		
		if(document.getElementById('multiLang').value == 'Y'){
	
			clearComboBox('state_Guj');
			clearComboBox('district_Guj');
			clearComboBox('vidhanSabha_Guj');
			clearComboBox('taluka_Guj');
		}		
	
	}	
}




function fillDistrict()
{		
		
		clearComboBox('district_Eng');
		clearComboBox('vidhanSabha_Eng');
		clearComboBox('taluka_Eng');
		
		if(document.getElementById('multiLang').value == 'Y'){
		
			clearComboBox('district_Guj');
			clearComboBox('vidhanSabha_Guj');
			clearComboBox('taluka_Guj');
		
		}		
		
		
		
				
		/*try
		{ 
			xmlHttp=new XMLHttpRequest(); 
		}
		catch (e)
		{ // Internet Explorer 
			try
			{
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			catch (e)
			{
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
				}
				catch (e)
				{							
					alert('Ajax Not Supported'); 
					return false; 
				}
			} 
		} */
		
		if(document.getElementById('multiLang').value == 'Y'){
			if(document.getElementById('langId').value == '1')
				var selStateCode = document.getElementById("state_Eng").value;
			else if(document.getElementById('langId').value == '2')
				var selStateCode = document.getElementById("state_Guj").value;
		}
		else{
		
			var selStateCode = document.getElementById("state_Eng").value;
		}
		
		var url = "hrms.htm?actionFlag=getDistrictValues&selStateCode="+selStateCode+"&currDistrictCode="+currDistrictCode;
		//var url = "hrms.htm?actionFlag=getDistrictValues&selStateCode="+selStateCode;
		/*xmlHttp.open("POST", encodeURI(url) , false); 
		xmlHttp.onreadystatechange = getDistrictComboValues; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); */
		
		new Ajax.Request(encodeURI(url),
		{
			method: 'POST',
			onSuccess: getDistrictComboValues,
		    onFailure: alertOnFailureForMstScr,
		    asynchronous: false
		} );
}

function getDistrictComboValues(resXmlHttp)
{	
		var encXML=resXmlHttp.responseText;
		if(encXML!="error")
		{
			
			var districtList=encXML.split("$");
			
			for (var i=0; i < districtList.length;++i){
				
				var keyValPair=districtList[i].split("/");
				if(keyValPair[2] == 1){
					
					document.getElementById('district_Eng').options[document.getElementById('district_Eng').options.length] = new Option(keyValPair[1],keyValPair[0]); 
				}
				else{
				
					if(document.getElementById('multiLang').value == 'Y'){
						
						document.getElementById('district_Guj').options[document.getElementById('district_Guj').options.length] = new Option(keyValPair[1],keyValPair[0]); 
					}
				}
			}
			
		}else
		{
			clearComboBox('district_Eng');
			clearComboBox('vidhanSabha_Eng');
			clearComboBox('taluka_Eng');
		
			if(document.getElementById('multiLang').value == 'Y'){
		
				clearComboBox('district_Guj');
				clearComboBox('vidhanSabha_Guj');
				clearComboBox('taluka_Guj');
			}	
		}	
}


function fillVidhanSabha()
{
		clearComboBox('vidhanSabha_Eng');
		
		if(document.getElementById('multiLang').value == 'Y'){
		
			clearComboBox('vidhanSabha_Guj');
			
		}				
		
		/*try
		{ 
			xmlHttp=new XMLHttpRequest(); 
		}
		catch (e)
		{ // Internet Explorer 
			try
			{
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			catch (e)
			{
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
				}
				catch (e)
				{							
					alert('Ajax Not Supported'); 
					return false; 
				}
			} 
		} */
		
		if(document.getElementById('multiLang').value == 'Y'){
			if(document.getElementById('langId').value == '1')
				var selDistrictCode = document.getElementById("district_Eng").value;
			else if(document.getElementById('langId').value == '2')
				var selDistrictCode = document.getElementById("district_Guj").value;
		}
		else{
		
			var selDistrictCode = document.getElementById("district_Eng").value;
		}
		var url = "hrms.htm?actionFlag=getVidhanSabhaValues&selDistrictCode="+selDistrictCode;
		
		/*xmlHttp.open("POST", encodeURI(url) , false); 
		xmlHttp.onreadystatechange = getVSabhaComboValues; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); */
		
		new Ajax.Request(encodeURI(url),
		{
			method: 'POST',
			onSuccess: getVSabhaComboValues,
		    onFailure: alertOnFailureForMstScr,
		    asynchronous: false
		} );
}

function getVSabhaComboValues(resXmlHttp)
{		
	var encXML=resXmlHttp.responseText;
	
	if(encXML!="error")
	{
		
		var vSabhaList=encXML.split("$");
		
		for (var i=0; i < vSabhaList.length;++i){
			
			var keyValPair=vSabhaList[i].split("/");
			
			if(keyValPair[2] == 1){
				
				document.getElementById('vidhanSabha_Eng').options[document.getElementById('vidhanSabha_Eng').options.length] = new Option(keyValPair[1],keyValPair[0]); 
			}
			else{
			
				if(document.getElementById('multiLang').value == 'Y'){
					document.getElementById('vidhanSabha_Guj').options[document.getElementById('vidhanSabha_Guj').options.length] = new Option(keyValPair[1],keyValPair[0]); 
				}
			}
		}
		
	}else
	{
		var vidhanSabha_Eng = document.getElementById('vidhanSabha_Eng');
		for(var i=1;i<vidhanSabha_Eng.options.length;i++)
		{
			vidhanSabha_Eng.remove(1);
		}
		
		if(document.getElementById('multiLang').value == 'Y'){
			var vidhanSabha_Guj = document.getElementById('vidhanSabha_Guj');
		
			for(var i=1;i<vidhanSabha_Guj.options.length;i++)
			{
				vidhanSabha_Guj.remove(1);
			}
		}
	}	
}


/*Taluka Combo Code*/

function fillTaluka()
{		
		clearComboBox('taluka_Eng');
		
		if(document.getElementById('multiLang').value == 'Y'){
		
			clearComboBox('taluka_Guj');
			
		}	
		
		
		/*try
		{ 
			xmlHttp=new XMLHttpRequest(); 
		}
		catch (e)
		{ // Internet Explorer 
			try
			{
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			catch (e)
			{
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
				}
				catch (e)
				{							
					alert('Ajax Not Supported'); 
					return false; 
				}
			} 
		} */
		
		if(document.getElementById('multiLang').value == 'Y'){
			if(document.getElementById('langId').value == '1')
				var selDistrictCode = document.getElementById("district_Eng").value;
			else if(document.getElementById('langId').value == '2')
				var selDistrictCode = document.getElementById("district_Guj").value;
		}
		else{
		
			var selDistrictCode = document.getElementById("district_Eng").value;
		}
		var url = "hrms.htm?actionFlag=getTalukaValues&selDistrictCode="+selDistrictCode+"&currTalukaCode="+currTalukaCode;
		
		/*xmlHttp.open("POST", encodeURI(url) , false); 
		xmlHttp.onreadystatechange = getTalukaComboValues; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); */
		
		new Ajax.Request(encodeURI(url),
		{
			method: 'POST',
			onSuccess: getTalukaComboValues,
		    onFailure: alertOnFailureForMstScr,
		    asynchronous: false
		} );
}

function getTalukaComboValues(resXmlHttp)
{	
	var encXML=resXmlHttp.responseText;
	if(encXML!="error")
	{
		var talukaList=encXML.split("$");
		
		for (var i=0; i < talukaList.length;++i){
			
			var keyValPair=talukaList[i].split("/");
			
			if(keyValPair[2] == 1){
				
				document.getElementById('taluka_Eng').options[document.getElementById('taluka_Eng').options.length] = new Option(keyValPair[1],keyValPair[0]); 
			}
			else{
				if(document.getElementById('multiLang').value == 'Y'){
					
					document.getElementById('taluka_Guj').options[document.getElementById('taluka_Guj').options.length] = new Option(keyValPair[1],keyValPair[0]); 
				}
			}
		}
	}
}


/*End Taluka Combo Code*/


function clearComboBox(comboBoxId){
	
	var comboBoxObj = document.getElementById(comboBoxId);
	
	if(comboBoxObj !=null){
		for(var i=(comboBoxObj.options.length-1); i>0; i--){
	
			comboBoxObj.remove(i);
		
		}

	}
}

function closeForm(actionFlag){
	showProgressbar();
	document.forms[0].action='hdiits.htm?actionFlag='+actionFlag;
	document.forms[0].submit();
}

function selectAllClearAll(object)
{
	var chkObject = document.forms[0].deletedata;
	
	if (chkObject.length)
	{
		for (var iIter=0;iIter<chkObject.length;iIter++)
		{
			chkObject[iIter].checked = object.checked;
		}
	}
	else
	{
		chkObject.checked = object.checked;
	}
}

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/, ''); };

function generateShortName(fullName, targetId)
{
	if(globalFlagType != "E")
	{
		var shortName = new String("");
	
		fullName = new String(fullName.value).trim();
	
		var arrName = fullName.split(" ");
	
		if (arrName.length > 1)
		{
			for (var iIter=0;iIter<arrName.length;iIter++)
			{
				namePart = new String(arrName[iIter].trim());
	
				if (namePart.length > 0)	
				{
					shortName = shortName + namePart.charAt(0);
				}
			}
		}
		else
		{
			if (fullName.length > 3)
			{
				shortName = fullName.substring(0,3);
			}
			else
			{
				shortName = fullName;
			}
		}
	
		document.getElementById(targetId).value = shortName.toUpperCase();
	}
}	

function generatePostName()
{
	var shortPostName = new String("");
	
	var dsgnText = document.getElementById('designationCmb').options[document.getElementById('designationCmb').selectedIndex].text;
	var dsgnValue = document.getElementById('designationCmb').value;
	
	var locValue = document.getElementById('locationCmb').value;
	
	var objPostName = document.getElementById('postNameTxt');
	
	if (dsgnText != null && dsgnText != '' && locValue != null && locValue != '' && dsgnValue != null && dsgnValue != '' && locValue != dispMsg)
	{
		shortPostName = dsgnText + "," + locValue;
	}
	
	objPostName.value = shortPostName;
	
	generateShortName(objPostName,'postShrtNametxt');
	
}

function generateInitCapName(objName)
{
	if(globalFlagType != "E")
	{
		var initCapFullName = new String();
		var objName = document.getElementById(objName);
		var fullName = new String(objName.value).trim();
		
		var arrName = fullName.split(" ");
		
		if (arrName.length > 1)
		{
			for (var iIter=0;iIter<arrName.length;iIter++)
			{
				namePart = new String(arrName[iIter].trim());
				if (namePart.length > 0) 
				{
					initCapFullName = initCapFullName + namePart.charAt(0).toUpperCase() + (namePart.length > 1?namePart.substring(1):"")+" ";
				}
			}
		}
		else if (fullName.length > 0)
		{
		 initCapFullName = fullName .charAt(0).toUpperCase() + (fullName.length > 1?fullName.substring(1):"");
		}
		if(arrName.length > 1)
		{
			initCapFullName = initCapFullName.substring(0, initCapFullName.length - 1);
		}
		objName.value = initCapFullName;
	}
}

function processResponseForValidatingUniqueNm(submitActionFlag,alertArray,formName)
{
	var tempAlert1, tempAlert2;
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);  
			var validateValue = XMLDoc.getElementsByTagName('nameFlag');
			var validateLang= XMLDoc.getElementsByTagName('langFlag');
			var validateFlag=validateValue[0].childNodes[0].text;
			
			if(validateFlag =='true')
			{
				formName.action=submitActionFlag;
				formName.submit();
			}
			else
			{
				var langFlag=validateLang[0].childNodes[0].text;
				hideProgressbar();
				
				if(langFlag =='gu')
				{
					tempAlert1=alertArray[3];
					tempAlert2=alertArray[2];
				}
				else if(langFlag =='en')
				{
					tempAlert1=alertArray[5];
					tempAlert2=alertArray[4];
				}
				else if(langFlag == 'both')
				{
					tempAlert1=alertArray[7];
					tempAlert2=alertArray[6];
				}
				var nameExistAlert;
				if('${MULTI_LANG}' == 'Y' || multiLang=='true')
				{
					nameExistAlert=alertArray[0]+' '+tempAlert1+' '+alertArray[1]+' '+tempAlert2;
				}
				else
				{
					nameExistAlert=alertArray[0]+' '+alertArray[1];
				}
				alert(nameExistAlert);
				return false;
			}
		}
	}
}

function checkSpecialCharacter(e)
{
	e = e ? e : window.event;
	
	// a-z = 97-122, A-Z = 65-90, 0-9 = 48-57, & = 38, ' = 39, / = 47, , = 44, . = 46, ( = 40, ) = 41, @ = 64, _ = 95,whitespace=32, - = 45 
	var varKeyCode = 0;
	
	if (window.event)
		varKeyCode = e.keyCode;
	else
		varKeyCode = e.which;
	
	if(varKeyCode != 45 && varKeyCode != 32 && varKeyCode != 44 && varKeyCode != 95 && (varKeyCode<38 || varKeyCode>41) && (varKeyCode<46 || varKeyCode>57) && (varKeyCode<64 || varKeyCode>90) && (varKeyCode<97 || varKeyCode>122) && varKeyCode != 0 && varKeyCode != 8)
    {
    	//window.event.keyCode= 0;
    	return false;
    }
	else
	{
		return true;
	}
}

function closeWindow()
{
	showProgressbar();
	document.forms[0].action="hdiits.htm?actionFlag=getHdiitsHomePage";
	document.forms[0].submit();
}
function alertOnFailureForMstScr()
{
	hideProgressbar();
	alert('Something went wrong...');	
}
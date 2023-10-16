function showSchemeDesc(obj)
{
	var lStrSchemeCode = obj.value;
	var params = "schemeCode="+lStrSchemeCode;
	url = "ifms.htm?actionFlag=getSchemeNameBySchCode";  
	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: params,
		        onSuccess: function(myAjax) {
							responseShowSchemeDesc(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
}
function responseShowSchemeDesc(myAjax)
{
	var XMLDoc =  myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{
		var schemeDesc = XMLDoc.getElementsByTagName('SCHEMENAME');
		if(schemeDesc[0] != null)
		{
			document.getElementById("schemeDesc").value =schemeDesc[0].childNodes[0].nodeValue;
		}
		else
		{
			document.getElementById("schemeDesc").value ="";
		}
		 
	}
	else
	{
		alert("Some problem occurred on getting scheme description");
	}
	
}
function generateMonthlyPnsnBill()
{
	var lObjSchemeCode = document.getElementById("cmbSchemeCode");
	var lObjMonth = document.getElementById("cmbForMonth");
	var lObjYear = document.getElementById("cmbForYear");
	var lObjPayMode = document.getElementById("payMode");
	if(validateGenerateMonthlyPnsnBill(lObjSchemeCode,lObjMonth,lObjYear,lObjPayMode))
	{
		showProgressbar();
		var params = "schemeCode="+lObjSchemeCode.value+"&month="+lObjMonth.value+"&year="+lObjYear.value+"&payMode="+lObjPayMode.value;
		url = "ifms.htm?actionFlag=genMonthlyPnsnBill";  
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters: params,
			        onSuccess: function(myAjax) {
								responseGenerateMonthlyPnsnBill(myAjax,lObjSchemeCode,lObjMonth,lObjYear);
					},
			        onFailure: function(){ alert('Something went wrong...');hideProgressbar();} 
			          } );
	}
}
function responseGenerateMonthlyPnsnBill(myAjax,lObjSchemeCode,lObjMonth,lObjYear)
{
	var XMLDoc =  myAjax.responseXML.documentElement;
	var lSchemeCode = lObjSchemeCode.value;
	var lMonth =lObjMonth.options[lObjMonth.selectedIndex].text;
	var lYear = lObjYear.options[lObjYear.selectedIndex].text;
	var lGenStatus = "";
	if(XMLDoc != null)
	{
		hideProgressbar();
		var lArrGenStatus = XMLDoc.getElementsByTagName('STATUS');
		if(lArrGenStatus[0] != null)
		{
			lGenStatus = lArrGenStatus[0].childNodes[0].nodeValue;
			if(lGenStatus == "SUCCESS")
			{
				alert("Monthly pension bill of scheme code "+lSchemeCode+" for  "+lMonth+"-"+lYear+" generated successfully.");
			}
			else if(lGenStatus == "NOBRANCH")
			{
				alert("No approved change statement of any bank branch exists for schemecode "+lSchemeCode+" for "+lMonth+"-"+lYear);
			}
			else if(lGenStatus == "NONEWBRANCH")
			{
				alert("Montlhy pension bill for schemecode "+lSchemeCode+" for "+lMonth+"-"+lYear+" is already generated.No new change statement is approved for schemecode "+lSchemeCode+" after generation of bill.");
			}
		}
		else
		{
			alert("Some Problem occured during generation of monthly pension bill.Please try again later.");
		}
	}
}
function validateGenerateMonthlyPnsnBill(lObjSchemeCode,lObjMonth,lObjYear,lObjPayMode)
{
	if(lObjSchemeCode.value == "" || lObjSchemeCode.value == "-1")
	{
		alert(ALRTSCHEMECODE);
		lObjSchemeCode.focus();
		return false;
	}
	else if(lObjMonth.value == "" || lObjMonth.value == "-1")
	{
		alert(ALRTMONTH);
		lObjMonth.focus();
		return false;
	}
	else if(lObjYear.value == "" || lObjYear.value == "-1")
	{
		alert(ALRTYEAR);
		lObjYear.focus();
		return false;
	}
	else if(lObjPayMode.value == "" || lObjPayMode.value == "-1")
	{
		alert(ALRTPAYMODE);
		lObjPayMode.focus();
		return false;
	}
	return true;
}

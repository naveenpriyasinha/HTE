<%@ page import="com.tcs.sgv.common.utils.StringUtility,com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst,com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst,com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg,java.util.*"%>
<html>
<head>
<%
try 
{
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page" />
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"></fmt:message>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="script/dcps/ddoScheme.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="actionList" value="${resValue.schedulerList}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="applyCmbVal" value="${resValue.applyCmbValue}"></c:set>
<!--<c:set var="deptList" value="${resultObj.resultValue.deptList}"></c:set>

-->

<!-- started By roshan kumar-->
<c:set var="talukaList" value="${resValue.talukaList}" ></c:set>
<c:set var="talukaId" value="${resValue.talukaId}" ></c:set>
<c:set var="ddoSelected" value="${resValue.ddoSelected}" ></c:set>
<c:set var="displayAddNewEntry" value="${resValue.displayAddNewEntry}"></c:set>
<!-- started By roshan kumar-->

<c:set var="officeList" value="${resultObj.resultValue.officeList}"></c:set>
<c:set var="flag" value="${resultObj.resultValue.flag}"></c:set>

<c:set var="allowList" value="${resultObj.resultValue.allowList}"></c:set>
<c:set var="deducActionList" value="${resultObj.resultValue.deducActionList}"></c:set>
<c:set var="DeductionSize" value="${resultObj.resultValue.DeductionSize}"></c:set>
<c:set var="AllowSize" value="${resultObj.resultValue.AllowSize}"></c:set>
<c:set var="msg" value="${resultObj.resultValue.msg}"></c:set>
<c:set var="firstTime" value="${resultObj.resultValue.firstTime}"></c:set>
<c:set var="DeptlocId" value="${resultObj.resultValue.DeptlocId}"></c:set>
<c:set var="DeptId" value="${resultObj.resultValue.DeptId}"></c:set>
<c:set var="WEFDate" value="${resultObj.resultValue.WEFDATE}"></c:set>
<fmt:formatDate value="${resultObj.resultValue.WEFDATE}" pattern="dd/MM/yyyy" dateStyle="medium" var="WEFDateJs" />
<c:set var="LoanAdvListSize" value="${resultObj.resultValue.LoanAdvListSize}"></c:set>
<c:set var="LoanAdvList" value="${resultObj.resultValue.LoanAdvList}"></c:set>
<c:set var="remarks" value="${resultObj.resultValue.remarks}"></c:set>
<c:set var="prevId" value="-2" />
<c:set var="NonGovDeducList" value="${resultObj.resultValue.NonGovDeducList}"></c:set>
<c:set var="NonGovDeducSize" value="${resultObj.resultValue.NonGovDeducSize}"></c:set>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript">
function getCheckedDataOnchange()
{
	xmlHttp=GetXmlHttpObject();
	if(xmlHttp==null)
  	{
		alert ("Your browser does not support AJAX!");
	  	return;
	}
	var url; 
	var urlVariable='';
	url= urlVariable+'&LocationCode='+${DeptlocId}; 
	var actionf="AllowDeductIDFromDept";

	urlVariable='./ifms.htm?actionFlag='+actionf;
	url=urlVariable+url; 		 
	
	xmlHttp.onreadystatechange=ForCheckingValueOnchange;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);	
}

function showAllDed() {
	
	var cmbDept = document.getElementById('cmbDept').value;
	//alert("cmbDept**********"+cmbDept);
	if(cmbDept == -1){
		//alert('Please select office name.');
		self.location.href = "hrms.htm?actionFlag=DeptCompMpg";
		//document.getElementById('cmbDept').focus;
		//document.getElementById('flag').value = 'no';
		
	}
	//var WEFDATE = document.getElementById('WEFDATE').value;
	//var Remarks = document.getElementById('Remarks').value;
	else
	{
		showProgressbar("Please wait...");
	self.location.href = "hrms.htm?actionFlag=DeptCompMpg&flag=yes&cmbDept="+cmbDept;//+"&WEFDATE="+WEFDATE+"&Remarks="+Remarks;
	}
}

function ForCheckingValueOnchange()
{
	if (xmlHttp.readyState==complete_state)
	{
		var XMLDoc=xmlHttp.responseXML.documentElement;
        var AllowChecked = XMLDoc.getElementsByTagName('AllowHead');
        var DeductChecked = XMLDoc.getElementsByTagName('DeductHead');
        var Loanchecked =XMLDoc.getElementsByTagName('LoanHead');
		var allowLength = AllowChecked[0].childNodes.length;
		var deducLength = DeductChecked[0].childNodes.length;
		var LoanLength = Loanchecked[0].childNodes.length;
		var allowsizeinget = document.getElementById('hdnAllowSize').value;
		var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
		var nonGovSizeinget = document.getElementById('hdnNonGovDeducSize').value;
		Deductsizeinget = parseInt(Deductsizeinget)+parseInt(nonGovSizeinget);
		var Loansizeinget = document.getElementById('hdnLoanAdvListSize').value;
        for(var i=0;i<allowLength;i++)
        {
			for(var j=1;j<=allowsizeinget;j++)
			{
				var id = document.getElementById('tax_name2'+j).value;
				if(id == AllowChecked[0].childNodes[i].firstChild.nodeValue )
					{
						document.getElementById('selcheckBoxAllow'+j).checked = true;
					}
			}
        }
        for(var i=0;i<deducLength;i++)
        {
			for(var j=1;j<=Deductsizeinget;j++)
			{
				var id = document.getElementById('tax_name1'+j).value;
				if(id == DeductChecked[0].childNodes[i].firstChild.nodeValue )
				{
					document.getElementById('selcheckBoxDedu'+j).checked = true;
				}
			}
        }
        for(var i=0;i<LoanLength;i++)
        {
			for(var j=1;j<=Loansizeinget;j++)
			{
				var id = document.getElementById('tax_name3'+j).value;
				if(id == Loanchecked[0].childNodes[i].firstChild.nodeValue )
				{
					document.getElementById('selcheckBoxLoan'+j).checked = true;
				}
			}
        }
	}
}

function vallidateWEF()
{
	//compareEffectDateWithPreviousWEF();
	var currentDate = document.getElementById('currentDate').value ;
	var date1=document.getElementById('WEFDATE').value;
	var Currsplitval = currentDate.split("/");
	var currDate=Currsplitval[0];
	var CurrMonth = Currsplitval[1];
	var CurrYear = Currsplitval[2];
	
	var WEFsplitval = date1.split("/");
	var WEFDate =  WEFsplitval[0];
	var WEFMonth =  WEFsplitval[1];
	var WEFYear = WEFsplitval[2];

	if(document.getElementById('WEFDATE').value != "")
	{
		if(WEFYear>CurrYear)
		{
			alert("Please select appropriate year.");
			document.forms[0].WEFDATE.value="";
			document.forms[0].WEFDATE.focus();
			return false;
		}
					if(WEFYear==CurrYear)
					{
						if(WEFMonth<=CurrMonth)
						{
							if(WEFDate>currDate)
							{
					alert('Please select a date less than current date');
					document.forms[0].WEFDATE.value="";
					document.forms[0].WEFDATE.focus();
					return false;
				}
				
			}
			else
			{
				alert('please Select a Date less than current Date');
				document.forms[0].WEFDATE.value="";
				document.forms[0].WEFDATE.focus();
				return false;
			}
		}
}
		
				
}

function compareEffectDateWithPreviousWEF()
{
	var currDate=document.getElementById('WEFDATE').value;
	var diff = compareDate("${WEFDateJs}",currDate);
	if(document.getElementById('WEFDATE').value != "")
	{
		if(diff<0)
		{
	    	alert("Effect Date must be Greater then Previous Mapped Date "+"${WEFDateJs}");
	    	document.DeptCompMPG.WEFDATE.value='';
	    	document.DeptCompMPG.WEFDATE.focus();
    		return false;
		}
	}
}
	
function getAllowDeductdata()
{
	getAllowData();
	getDeductData();
	getLoanData();
	var index = document.DeptCompMPG.applyCmb.selectedIndex;
	var applyCmbVal = document.DeptCompMPG.applyCmb.options[index].value;
	if(document.getElementById("Remarks").value== "" ) 
	{
		alert('Please Enter Remarks');
		return;
	}
	
	var answer = confirm ("Do you want to submit?");
	if (answer)
	{
		document.DeptCompMPG.formSubmitButtonTemp.disabled = true;
		showProgressbar("Please wait...");		
		document.DeptCompMPG.action='ifms.htm?actionFlag=InsertDeptCompMpg&applyCmbValue='+applyCmbVal;
		document.DeptCompMPG.formSubmitButtonTemp.disabled = true;
		document.DeptCompMPG.submit();		
		showProgressbar("Please wait...");
	}
	
	document.DeptCompMPG.formSubmitButtonTemp.disabled = false;
	
}

function getAllowData()
{
	var allowsizeinget = document.getElementById('hdnAllowSize').value;
	var cheked;
	var allow="";
	var allowForAllEmp = "";
	var index = document.DeptCompMPG.applyCmb.selectedIndex;
	var applyCmbVal = document.DeptCompMPG.applyCmb.options[index].value;
	var checkedvalueofAllow=1;
	for(var i=1;i<=allowsizeinget;i++)
	{
		cheked = 'selcheckBoxAllow'+i;
		if(applyCmbVal == 2)
		{
			if(document.getElementById(cheked).checked || document.getElementById(cheked).disabled)
			{
				if(document.getElementById(cheked).checked)
				{
					allowForAllEmp+= document.getElementById('tax_name2'+i).value+",";
					allow+=document.getElementById('tax_name2'+i).value+",";
				}
				else
				{
					allow+=document.getElementById('tax_name2'+i).value+",";
				}
				checkedvalueofAllow++;
			}
		}
		else
		{
			if(document.getElementById(cheked).checked)
			{
				if(i<(allowsizeinget))
				{
					allow+=document.getElementById('tax_name2'+i).value+",";
				}
				else
				{
					allow+=document.getElementById('tax_name2'+i).value;
				}
				
				checkedvalueofAllow++;
			}
		}
	}
	document.getElementById('hdnAllowList').value = allow;	
	document.getElementById('hdnallowForAllEmp').value = allowForAllEmp;
	document.getElementById('hdncheckedvalueofAllow').value = (checkedvalueofAllow-1);
}

function getDeductData()
{
	var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
	var nonGovSizeinget = document.getElementById('hdnNonGovDeducSize').value;
	Deductsizeinget = parseInt(Deductsizeinget)+parseInt(nonGovSizeinget);
	var index = document.DeptCompMPG.applyCmb.selectedIndex;
	var applyCmbVal = document.DeptCompMPG.applyCmb.options[index].value;
	var cheked;
	var deduct="";
	var deductForAllEmp = "";
	var checkedvalueofdeduct=1;
	for(var i=1;i<=Deductsizeinget;i++)
	{
		cheked = 'selcheckBoxDedu'+i;
		if(applyCmbVal == 2)
		{
			if(document.getElementById(cheked).checked || document.getElementById(cheked).disabled)
			{
				if(document.getElementById(cheked).checked)
				{
					deductForAllEmp+= document.getElementById('tax_name1'+i).value+",";
					deduct+=document.getElementById('tax_name1'+i).value+",";
				}
				else
				{
					deduct+=document.getElementById('tax_name1'+i).value+",";
				}
				checkedvalueofdeduct++;
			}
		}
		else
		{
			if(document.getElementById(cheked).checked)
			{
				if(i<(Deductsizeinget))
				{
					deduct+=document.getElementById('tax_name1'+i).value+",";
				}
				else
				{
					deduct+=document.getElementById('tax_name1'+i).value;
				}
				checkedvalueofdeduct++;
			}
		}
	}
	document.getElementById('hdnDeductList').value = deduct;
	document.getElementById('hdncheckedvalueofdeduct').value = (checkedvalueofdeduct-1);
	document.getElementById('hdndeductForAllEmp').value = deductForAllEmp;
}

function getLoanData()
{
	var LoanAdvSize = document.getElementById('srNoLoanAdv').value;
	var cheked;
	var LoanValue="";
	var checkedvalueofLoan=1;
	for(var i=1;i<LoanAdvSize;i++)
	{
		cheked = 'selcheckBoxLoan'+i;
		if(document.getElementById(cheked).checked)
		{
			if(i<(LoanAdvSize))
			{
				LoanValue+=document.getElementById('tax_name3'+i).value+",";
			}
			else
			{
				LoanValue+=document.getElementById('tax_name3'+i).value;
			}
			
			checkedvalueofLoan++;
		}
	}
	document.getElementById('hdnLoanList').value = LoanValue;	
	document.getElementById('hdncheckedSizeofLoan').value = (checkedvalueofLoan-1);
}
function getCheckedData()
{
	forclearvalueofchecked();
	xmlHttp=GetXmlHttpObject();
	if(xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	}
	var url; 
	var urlVariable='';
	url= urlVariable+'&LocationCode='+ document.forms[0].cmbDept.value; 
	var actionf="AllowDeductIDFromDept";

	urlVariable='./ifms.htm?actionFlag='+actionf;
	url=urlVariable+url; 		 
    		  		  
	xmlHttp.onreadystatechange=ForCheckingValue;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);	
}

function ForCheckingValue()
{
	if (xmlHttp.readyState==complete_state)
	{ 	
		var XMLDoc=xmlHttp.responseXML.documentElement;
        var AllowChecked = XMLDoc.getElementsByTagName('AllowHead');
        var DeductChecked = XMLDoc.getElementsByTagName('DeductHead');
        var Loanchecked =XMLDoc.getElementsByTagName('LoanHead');
		var allowLength = AllowChecked[0].childNodes.length;
		var deducLength = DeductChecked[0].childNodes.length;
		var LoanLength = Loanchecked[0].childNodes.length;
		var allowsizeinget = document.getElementById('hdnAllowSize').value;
		var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
		var nonGovSizeinget = document.getElementById('hdnNonGovDeducSize').value;
		Deductsizeinget = parseInt(Deductsizeinget)+parseInt(nonGovSizeinget);
		var Loansizeinget = document.getElementById('hdnLoanAdvListSize').value;
        for(var i=0;i<allowLength;i++)
        {
			for(var j=1;j<=allowsizeinget;j++)
			{
				var id = document.getElementById('tax_name2'+j).value;
				if(id == AllowChecked[0].childNodes[i].text )
					{
						document.getElementById('selcheckBoxAllow'+j).checked = true;
					}
			}
        }
        for(var i=0;i<deducLength;i++)
        {
			for(var j=1;j<=Deductsizeinget;j++)
			{
				var id = document.getElementById('tax_name1'+j).value;
				if(id == DeductChecked[0].childNodes[i].text )
				{
					document.getElementById('selcheckBoxDedu'+j).checked = true;
				}
			}
        }
        for(var i=0;i<LoanLength;i++)
        {
			for(var j=1;j<=Loansizeinget;j++)
			{
				var id = document.getElementById('tax_name3'+j).value;
				if(id == Loanchecked[0].childNodes[i].text )
				{
					document.getElementById('selcheckBoxLoan'+j).checked = true;
				}
			}
        }
	 } 
}

function forclearvalueofchecked()
{
	var allowsizeinget = document.getElementById('hdnAllowSize').value;
	var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
	var nonGovSizeinget = document.getElementById('hdnNonGovDeducSize').value;
	Deductsizeinget = parseInt(Deductsizeinget)+parseInt(nonGovSizeinget);
	var Loansizeinget = document.getElementById('hdnLoanAdvListSize').value;
	if(document.forms[0].cmbDept.value==-1)
	{
		for(var j=1;j<=allowsizeinget;j++)
			{
				document.getElementById('selcheckBoxAllow'+j).checked = false;
			}
		for(var j=1;j<=Deductsizeinget;j++)
			{
				document.getElementById('selcheckBoxDedu'+j).checked = false;
			}
		for(var j=1;j<=Loansizeinget;j++)
		{
			document.getElementById('selcheckBoxLoan'+j).checked = false;
		}
	}
}

function OnlyAplha(control)
{   
	var e1=  control.value;
	var iChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`&_$;@*%~{}<>^'|%+#() ";
	var value="";
	var valid=true;
	for (var i=0; i<control.value.length; i++) 
	{              
	   if (iChars.indexOf(control.value.charAt(i))!=-1) 
	   {
	      value=value+control.value.charAt(i);
	   }               
	   else
	      valid=false;
	}     
	
	if(!valid)
	{
	    alert('Numbers and special characters are not allowed');
	    control.value="";
	    control.focus();
	    return false;
	}
	return true;
}

function onlyAlphaWithSpecialChar(control) 
{                
	var iChars = "QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq0123456789&/\,.-_`&_$;@*%~{}<>^'|%+#()  ";
	var value="";
	var valid=true;
	
	for (var i=0; i<control.value.length;i++) 
	 {  
	 if (iChars.indexOf(control.value.charAt(i))!=-1) 
	   {
	  re = /['QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq']/;
	  
	   if((!re.test(control.value)) ) {
	     alert("Remarks must contain at least one alphabet!");
	     control.focus();
	     return false;
	   }
	   
	      value=value+control.value.charAt(i);
	   }               
	   else
	   {                 
	      valid=false;
	   }
	}                   
	if(!valid)
	{              
	     control.value="";
	    alert('Number and Special characters are not allowed.');             
	    control.value="";
	    control.focus();
	    return false;
	}
	return true;              
}

function selDeselectAllowances(obj)
{		
	var selAllChkFlg=obj.checked;
	if(selAllChkFlg == true)
	{
		for (var j=1; j<="${AllowSize}"; j++)
	    {	
			document.getElementById('selcheckBoxAllow'+j).checked= true;
	    //	document.DeptCompMPG.elements('selcheckBoxAllow'+j).checked=true;
	    }
	}
	else
	{
		for (var j=1; j<="${AllowSize}"; j++)
	    {
			document.getElementById('selcheckBoxAllow'+j).checked= false;
	  		//document.DeptCompMPG.elements('selcheckBoxAllow'+j).checked=false;
		}
	}
}

function selDeselectDeduction(obj)
{		
	var selAllChkFlg=obj.checked;
	var deducsize = "${DeductionSize}";
	var nongovsize = "${NonGovDeducSize}";
	var totalDeduction = parseInt(deducsize)+parseInt(nongovsize);
	if(selAllChkFlg == true)
	{
		for (var j=1; j<=totalDeduction; j++)
	    {
		    if(nongovsize != 0)
		    {	
		    	document.getElementById('selcheckBoxDedu'+j).checked= true;
	    		//document.DeptCompMPG.elements('selcheckBoxDedu'+j).checked=true;
	    		j++;
	    		nongovsize--;
		    }
		    else
		    {
		    	document.getElementById('selcheckBoxDedu'+j).checked= true;
		    	//document.DeptCompMPG.elements('selcheckBoxDedu'+j).checked=true;
		    }
	    }
	}
	else
	{
		
		for (var j=1; j<=totalDeduction; j++)
	    {	
			if(nongovsize != 0)
		    {	
				document.getElementById('selcheckBoxDedu'+j).checked= false;
	    		//document.DeptCompMPG.elements('selcheckBoxDedu'+j).checked=false;
	    		j++;
	    		nongovsize--;
		    }
		    else
		    {
		    	document.getElementById('selcheckBoxDedu'+j).checked= false;
		    	//document.DeptCompMPG.elements('selcheckBoxDedu'+j).checked=false;
		    }
	    	
		}
	}
}

function selDeselectNonGovDeduction(obj)
{
	var selAllChkFlg=obj.checked;
	var deducsize = "${DeductionSize}";
	var nongovsize = "${NonGovDeducSize}";
	var totalDeduction = parseInt(deducsize)+parseInt(nongovsize);
	if(selAllChkFlg == true)
	{
		for (var j=1; j<=totalDeduction; j++)
	    {
		    if(nongovsize != 0)
		    {	
		    	j++;
	    		nongovsize--;
	    		document.getElementById('selcheckBoxDedu'+j).checked= true;
	    		//document.DeptCompMPG.elements('selcheckBoxDedu'+j).checked=true;
		    }
	    }
	}
	else
	{
		
		for (var j=1; j<=totalDeduction; j++)
	    {	
			if(nongovsize != 0)
		    {	
		    	j++;
	    		nongovsize--;
	    		document.getElementById('selcheckBoxDedu'+j).checked= false;
	    		//document.DeptCompMPG.elements('selcheckBoxDedu'+j).checked= false;
		    }
		}
	}
}

function selDeselectLaonAdv(obj)
{		
	var selAllChkFlg=obj.checked;
	if(selAllChkFlg == true)
	{
		for (var j=1; j<="${LoanAdvListSize}"; j++)
	    {	
			document.getElementById('selcheckBoxLoan'+j).checked= true;
			//document.DeptCompMPG.elements('selcheckBoxLoan'+j).checked=true;
	    }
	}
	else
	{
		for (var j=1; j<="${LoanAdvListSize}"; j++)
	    {	
			
			document.getElementById('selcheckBoxLoan'+j).checked=false;
	    }
	}
}

function deselectAllCheckbox()
{
	var index = document.DeptCompMPG.applyCmb.selectedIndex;
	var applyCmbVal = document.DeptCompMPG.applyCmb.options[index].value;
	if(applyCmbVal == 1)
	{
		url = './hrms.htm?actionFlag=DeptCompMpg&applyCmbValue='+applyCmbVal;
		document.DeptCompMPG.action = url;
		document.DeptCompMPG.submit();
		showProgressbar("Please wait...");
	}
	else if(applyCmbVal == 2)
	{
		var allowsizeinget = document.getElementById('hdnAllowSize').value;
		var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
		var nonGovSizeinget = document.getElementById('hdnNonGovDeducSize').value;
		Deductsizeinget = parseInt(Deductsizeinget)+parseInt(nonGovSizeinget);
		var Loansizeinget = document.getElementById('hdnLoanAdvListSize').value;
		for(var i=1;i<=allowsizeinget;i++)
		{
			cheked = 'selcheckBoxAllow'+i;
			if(document.getElementById(cheked).checked)
			{
				document.getElementById(cheked).checked = "";
				document.getElementById(cheked).disabled = "true";
			}
		}
		for(var j=1;j<=Deductsizeinget;j++)
		{
			cheked = 'selcheckBoxDedu'+j;
			if(document.getElementById(cheked).checked)
			{
				document.getElementById(cheked).disabled = "true";
				document.getElementById(cheked).checked = "";
			}
		}
		document.getElementById("applyCmb").disabled = true;
	}
}

function loadValue()
{
	//alert('${DeptlocId}')
	if("${applyCmbVal}"!=null && "${applyCmbVal}"!='-1'  )
	{
		document.getElementById("applyCmb").value = '${applyCmbVal}';
		document.getElementById("applyCmb").disabled = true;
	}

	

	
}

function checkApplyCmb(TagId)
{
	var ans = "${firstTime}";
	/*if(ans == 'false')
	{
		alert("Please open this screen again.");
		if(document.getElementById(TagId).checked)
		{
			document.getElementById(TagId).checked = '';
		}
		else
		{
			document.getElementById(TagId).checked = 'checked';
		}
		return false;
	}*/
	if(document.getElementById("applyCmb").value == -1)
	{
		alert("Please select apply to option");
		document.getElementById("applyCmb").focus();
		if(document.getElementById(TagId).checked)
		{
			document.getElementById(TagId).checked = '';
		}
		else
		{
			document.getElementById(TagId).checked = 'checked';
		}
		return false;
	}
}
</script>
<fmt:formatDate value="${resultObj.resultValue.current_date}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDateJs" />
<script>
if("${msg}"!='')
{
	showProgressbar("Please wait...");
	alert('${msg}');
}
</script>

<hdiits:form  name="DeptCompMPG" validate="true" method="POST" action="" encType="multipart/form-data" onload="onloadfunction();">


	<%--Added by roshan --%>
	<fieldset class="tabstyle"><legend> <b>Filter Institute</b> </legend>
<table align="center">
<tr>
<td><c:out value="Taluka"></c:out></td>

<td><select name="cmbTaluka"
			id="cmbTaluka" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="----Select----"></c:out></option>

			<c:forEach var="talukaList" items="${talukaList}">
			<c:choose> 
			<c:when test="${talukaId==talukaList[0]}">
							<option value="${talukaList[0]}" title="${talukaList[1]}" selected="selected">
						<c:out value="${talukaList[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${talukaList[0]}"/>" title="${talukaList[1]}">
						<c:out value="${talukaList[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
		</select></td>


<td><c:out value=" DDO Code/Office Name"></c:out></td>
<td>
<c:choose>
<c:when test="${ddoSelected!=null}">
<!--<hdiits:text id = "ddoCode" name="ddoCode" captionid="${ddoSelected}" validation="" maxlength="200" size="30"
			 default=""/>
-->
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" value="${ddoSelected}" size="30"/>
</c:when>
<c:otherwise>
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" size="30"/>
</c:otherwise>
</c:choose>			 
</td>


		</tr>
	<tr>	
<td colspan="4" align="center"><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
		value="Filter" onclick="filterInstituteforDept();"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>


<!--
end roshan
	-->
	
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="DeptComMpg.DeptCompMpg" bundle="${commonLables}" /></b></a></li>
	</ul>
	</div>
	<br />
	<br />

	<div id="tcontent1" style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin"><br />
	<TABLE align="center" width="80%" border="0">
		<tr>
			<!--<td width="10%"></td>
			-->
			<TD align="left" class="Label" width="20%"><b><fmt:message key="DeptComMpg.DeptName" bundle="${commonLables}" /></b></TD>
			<td width="10%"><b>:</b></td>
			<TD width="40%" ><hdiits:select style="width:90%" name="cmbDept"  id="cmbDept" size="1" sort="false" caption="Department" default="${DeptId}" captionid="Dept" validation="sel.isrequired" mandatory="true" onchange="showAllDed();">
				<hdiits:option value="-1">---------------------------Select-------------------------</hdiits:option>
				<!--<c:forEach items="${deptList}" var="dept">
					<hdiits:option value="${dept.locationCode}">${dept.locName} </hdiits:option>
				</c:forEach>
				
				-->
				<c:forEach items="${officeList}" var="office">
					<hdiits:option value="${office[0]}">(${office[2]})${office[1]} </hdiits:option>
				</c:forEach>
				
			</hdiits:select></TD>
			<td width="30%"></td>
		</tr>

	<c:if test="${flag eq 'yes'}">
		<TR>
			<!--<td width="5%"></td>
			--><TD align="left" class="Label" width="15%"><b><hdiits:caption captionid="EmpCompMpg.Date" bundle="${commonLables}" /></b></td>
			<td width="10%"><b>:</b></td>


			<td width="30%" class="fieldLabel"><b> <!--<fmt:formatDate var="WEFDate1" value="${WEFDate}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />
			--><hdiits:dateTime name="WEFDATE" captionid="EmpCompMpg.Date" bundle="${commonLables}" mandatory="true" validation="txt.isdt,txt.isrequired" minvalue="" onblur="vallidateWEF()" default="${WEFDate}"></hdiits:dateTime></b></td>

			<td width="30%">
		</TR>

		<TR>
			<!--<td width="5%"></td>
			--><TD align="left" class="Label" width="15%"><hdiits:caption captionid="eis.Remarks" bundle="${commonLables}" style="font-style:bold" /></td>
			<td width="10%"><b>:</b></td>
			<td width="40%"><hdiits:textarea rows="3" cols="30" name="Remarks" id="Remarks" captionid="eis.Remarks" bundle="${commonLables}" mandatory="true" onblur="" default="${remarks}"></hdiits:textarea></td>
			<td width="30%">
		</TR>
		</c:if>
		
		<tr id="ApplyTo" style="display: none">
			<td width="10%"></td>
			<TD align="left" class="Label" width="20%">Apply To</TD>
			<td width="10%"><b>:</b></td>
			<TD width="30%"><hdiits:select style="width:90%" name="applyCmb" id="applyCmb" size="1" default="-1" sort="false"  mandatory="true" onchange="deselectAllCheckbox();">
				<hdiits:option value="1">Dept. Only </hdiits:option>
				<hdiits:option value="2">All Employee </hdiits:option>
			</hdiits:select></TD>
			<td width="30%"></td>
		</tr>
		
		
	</TABLE>
		<!--<TABLE align="center" width="80%" border="0">
		<label style="color:red;width=30%;align=left;">Important Notice - If you select "All Employees" option, the component will get mapped to all employees in your office. Please be careful while using this option.</label>
		</TABLE>-->
		
	<br />
	<br />
	
<c:if test="${flag eq 'yes'}">
	<table width="80%" border=1 rules="groups" id="deduction" align="center" bordercolor="#9F5C04" style="border-style: solid; border-width: thin">
		<thead>
		
			<tr bgcolor="#9F5C04">
				<td width="25%" align="left"><b><font color="white"><c:if test="${AllowSize ne 0}">
					<hdiits:checkbox name='selectAllowanceChkBox' id='selectAllowanceChkBox' value="" onclick="selDeselectAllowances(this)" />
					<b>Allowances</b>
				</c:if> </font></b></td>
				<td width="25%" align="left"><b><font color="white"><c:if test="${DeductionSize ne 0}">
					<hdiits:checkbox name='selectDeductionChkBox' id='selectDeductionChkBox' value="" onclick="selDeselectDeduction(this)" />
					<b>Deductions</b>
				</c:if> </font></b></td>
				<td width="25%" align="left"><b><font color="white"><c:if test="${NonGovDeducSize ne 0}">
					<hdiits:checkbox name='selectDeductionChkBox' id='selectDeductionChkBox' value="" onclick="selDeselectNonGovDeduction(this)" />
					<b>Non Gov Deduction</b>
				</c:if> </font></b></td>
				<td width="25%" align="left"><b><font color="white"><c:if test="${LoanAndAdvList ne 0}">
					<hdiits:checkbox name='selectLoanAdvChkBox' id='selectLoanAdvChkBox' value="" onclick="selDeselectLaonAdv(this)" />
					<b>Loans And Advances</b>
				</c:if> </font></b></td>
				
			</tr>
		</thead>
		<tr></tr>

		<c:choose>
			<c:when test="${DeductionSize+NonGovDeducSize ge AllowSize}">
				<c:set var="count" value="${DeductionSize+NonGovDeducSize}">
				</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="count" value="${AllowSize}"></c:set>
			</c:otherwise>
		</c:choose>

		<c:set var="allowccode" value="${0}" />
		<c:set var="deduccode" value="${0}" />
		<c:set var="srNoDeduction" value="1"></c:set>
		<c:set var="srNoNonGov" value="1"></c:set>
		<c:set var="srNoAllown" value="1"></c:set>
		<c:set var="srNoLoanAdv" value="1"></c:set>

		<% 
				 	int count =Integer.parseInt(pageContext.getAttribute("count").toString()) ;
				 	int allowCount =Integer.parseInt( pageContext.getAttribute("AllowSize").toString());
				 	int deducCount = Integer.parseInt(pageContext.getAttribute("DeductionSize").toString());
				 	int NonGovCount = Integer.parseInt(pageContext.getAttribute("NonGovDeducSize").toString());
				 	int LoanCount =Integer.parseInt( pageContext.getAttribute("LoanAdvListSize").toString());
			
				 	List<HrPayAllowTypeMst> allowList =  (List<HrPayAllowTypeMst> )pageContext.getAttribute("allowList") ;
				 	List<HrPayDeducTypeMst> deducList =  (List<HrPayDeducTypeMst> )pageContext.getAttribute("deducActionList") ;
				 	List<HrPayDeducTypeMst> nonGovList =  (List<HrPayDeducTypeMst> )pageContext.getAttribute("NonGovDeducList") ;
				 	List<HrPayEdpCompoMpg> LoanList =  (List<HrPayEdpCompoMpg> )pageContext.getAttribute("LoanAdvList") ;
				 	for(int i=0;i<count;i++)
				 	{
				 		if(i<allowCount)
				 		{
				 			//System.out.println("allowList "+allowList.size());
				 			//System.out.println("deducList "+deducList.size());
				 			HrPayAllowTypeMst allowType = (HrPayAllowTypeMst)allowList.get(i);
				 			pageContext.setAttribute("allowType",allowType);
				 
				 %>

		<TR>

			<TD width="25%" align="left" ><hdiits:hidden default="${allowType.allowCode}" name="tax_name2${srNoAllown}" id="tax_name2${srNoAllown}" /> <c:choose>
				<c:when test="${allowType.allowCode eq 145 or allowType.allowCode eq 9}">
					<hdiits:checkbox id="selcheckBoxAllow${srNoAllown}" name="selcheckBoxAllow${srNoAllown}" readonly="" default="" value="1" onclick="checkApplyCmb(this.id);" />
				</c:when>
				<c:otherwise>
					<hdiits:checkbox id="selcheckBoxAllow${srNoAllown}" name="selcheckBoxAllow${srNoAllown}" value="" onclick="checkApplyCmb(this.id);" />
				</c:otherwise>
			</c:choose> <b><c:out value="${allowType.allowDisplayName}" /></b> <span id="span${allowType.allowCode}"></span> <c:set var="allowccode" value="${allowccode+1}" /> <c:set var="srNoAllown" value="${srNoAllown + 1}"></c:set></TD>

			<!-- 			<td><c:out value="selcheckBoxAllow${srNoAllown}" /></td> -->

			<%}
				 		
				 		if(i<deducCount)
				 		{
				 			
				 			HrPayDeducTypeMst  deducType = (HrPayDeducTypeMst)deducList.get(i);
				 			pageContext.setAttribute("deducType",deducType);
				 		%>
			<%--<c:choose>
								<c:when test="${allowType.payCommissionId eq deducType.payCommissionId}">
									 --%>
			<TD width="25%" align="left">
				<hdiits:hidden default="${deducType.deducCode}" name="tax_name1${srNoDeduction}" id="tax_name1${srNoDeduction}" /> 
				<c:choose>
					<c:when test="${deducType.deducCode eq 32 or deducType.deducCode eq 35}">
						<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}" name="selcheckBoxDedu${srNoDeduction}" readonly="" default="" value="1" onclick="checkApplyCmb(this.id);"/>
					</c:when>
					<c:otherwise>
						<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}" name="selcheckBoxDedu${srNoDeduction}" value="" onclick="checkApplyCmb(this.id);"/>
					</c:otherwise>
				</c:choose> 
				<span id="span${deducType.deducCode}"></span> 
				<b><c:out value="${deducType.deducDisplayName}" /></b> 
				<c:set var="deduccode" value="${deduccode+1}" /> 
				<c:set var="srNoDeduction" value="${srNoDeduction + 1}"></c:set>
			</TD>


			<%	 
				}
				if(i<NonGovCount)
				{
					HrPayDeducTypeMst  nonGovDeducType = (HrPayDeducTypeMst)nonGovList.get(i);
		 			pageContext.setAttribute("nonGovDeducType",nonGovDeducType);
		 	%>
<!--		 			<td width="5%" align="left"></td>-->
					<TD width="25%" align="left">
						<hdiits:hidden default="${nonGovDeducType.deducCode}" name="tax_name1${srNoDeduction}" id="tax_name1${srNoDeduction}" /> 
						<c:choose>
							<c:when test="${nonGovDeducType.deducCode eq 32 or nonGovDeducType.deducCode eq 35}">
								<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}" name="selcheckBoxDedu${srNoDeduction}" readonly="" default="" value="1" onclick="checkApplyCmb(this.id);"/>
							</c:when>
							<c:otherwise>
								<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}" name="selcheckBoxDedu${srNoDeduction}" value="" onclick="checkApplyCmb(this.id);"/>
							</c:otherwise>
						</c:choose> 
						<span id="span${nonGovDeducType.deducCode}"></span> 
						<b><c:out value="${nonGovDeducType.deducDisplayName}" /></b> 
						<c:set var="deduccode" value="${deduccode+1}" /> 
						<c:set var="srNoDeduction" value="${srNoDeduction + 1}"></c:set>
					</TD>
		 	<%
				}
				else
				{
					%>
		 			<TD width="25%" align="left"></TD>
		 			<%
				}
				if(i<LoanCount)
				{
				
					HrPayEdpCompoMpg LoanType = (HrPayEdpCompoMpg)LoanList.get(i);
			 		pageContext.setAttribute("LoanAndAdvList",LoanType);
				
				%>

			<TD width="25%" align="left" ><hdiits:hidden default="${LoanAndAdvList.typeId}" name="tax_name3${srNoLoanAdv}" id="tax_name3${srNoLoanAdv}" /> <hdiits:checkbox id="selcheckBoxLoan${srNoLoanAdv}" name="selcheckBoxLoan${srNoLoanAdv}" value="" onclick="checkApplyCmb(this.id);"/> <b><c:out value="${LoanAndAdvList.rltBillTypeEdp.edpDesc}" /></b> <span id="span${LoanAndAdvList.typeId}"></span> <c:set var="srNoLoanAdv" value="${srNoLoanAdv + 1}"></c:set></TD>

			<%	 
				 		}%>


		</TR>


		<c:set var="prevId" value="${allowType.payCommissionId}"></c:set>
		<%	} %>
		<hdiits:hidden default="${srNoDeduction}" name="srNoDeduction" id="srNoDeduction" />
		<hdiits:hidden default="${srNoAllown}" name="srNoAllown" id="srNoAllown" />
		<hdiits:hidden default="${srNoLoanAdv}" name="srNoLoanAdv" id="srNoLoanAdv" />
	</table>



</c:if>	
	</div>
	<c:if test="${flag eq 'yes'}">
	<div><jsp:include page="../../core/PayTabnavigation.jsp" /></div>
</c:if>
	<hdiits:jsField jsFunction="getAllowDeductdata()" name="chkBoxAllow" />


	<hdiits:hidden default="${DeductionSize}" id="hdnDeductionSize" name="hdnDeductionSize"></hdiits:hidden>
	<hdiits:hidden default="${AllowSize}" id="hdnAllowSize" name="hdnAllowSize"></hdiits:hidden>
	<hdiits:hidden default="${LoanAdvListSize}" id="hdnLoanAdvListSize" name="hdnLoanAdvListSize"></hdiits:hidden>
	<hdiits:hidden default="${NonGovDeducSize}" id="hdnNonGovDeducSize" name="hdnNonGovDeducSize"></hdiits:hidden>
	<hdiits:hidden id="hdnAllowList" name="hdnAllowList"></hdiits:hidden>
	<hdiits:hidden id="hdnallowForAllEmp" name="hdnallowForAllEmp"></hdiits:hidden>
	<hdiits:hidden id="hdnDeductList" name="hdnDeductList"></hdiits:hidden>
	<hdiits:hidden id="hdndeductForAllEmp" name="hdndeductForAllEmp"></hdiits:hidden>
	<hdiits:hidden id="hdncheckedvalueofAllow" name="hdncheckedvalueofAllow"></hdiits:hidden>
	<hdiits:hidden id="hdncheckedvalueofdeduct" name="hdncheckedvalueofdeduct"></hdiits:hidden>


	<hdiits:hidden id="hdnLoanList" name="hdnLoanList"></hdiits:hidden>
	<hdiits:hidden id="hdncheckedSizeofLoan" name="hdncheckedSizeofLoan"></hdiits:hidden>

	<hdiits:hidden default="getHomePage" name="givenurl" />
	<hdiits:hidden name="currentDate" id="currentDate" default="${currentDateJs}" />

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

	<script type="text/javascript"> 
	initializetabcontent("maintab");
	</script>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script type="text/javascript">
	getCheckedDataOnchange();
	loadValue();
</script>
</html>
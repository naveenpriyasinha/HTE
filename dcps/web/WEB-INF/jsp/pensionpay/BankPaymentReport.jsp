<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionConstants" var="pensionCnst" scope="request" />


<script type="text/javascript">
MONTHLY='<fmt:message key="RECOVERY.MONTHLY" bundle="${pensionCnst}"></fmt:message>';
PENSION='<fmt:message key="RECOVERY.PENSION" bundle="${pensionCnst}"></fmt:message>';
DCRG='<fmt:message key="RECOVERY.DCRG" bundle="${pensionCnst}"></fmt:message>';
CVP='<fmt:message key="RECOVERY.CVP" bundle="${pensionCnst}"></fmt:message>';
SUPP='<fmt:message key="RECOVERY.SUPP" bundle="${pensionCnst}"></fmt:message>';
</script>

<script type="text/javascript"  src="script/common/common.js"></script>
<script>
function showAllPrint()
{
	var audBankFlag;
	if(validateBankPaymentReportData())
	{
		var url="ifms.htm?actionFlag=getBankPaymentReport";
		if(document.getElementById("radioAuditorBank1").checked)
		{
			 url=url+"&AudBankFlag=Auditor";
		}
		else if(document.getElementById("radioAuditorBank2").checked)
		{
			url=url+"&AudBankFlag=Bank";
		}
		else if(document.getElementById("radioAuditorBank3").checked)
		{
			url=url+"&AudBankFlag=Branch";
		}
	
		url=url+"&ForMonth="+document.getElementById("cmbBillMonth").value+"&ForYear="+document.getElementById("cmbBillYear").value
				+"&BankCode="+document.getElementById("cmbBank").value+"&BranchCode="+document.getElementById("cmbBranch").value
				+"&AudPostId="+document.getElementById("cmbAuditor").value+"&PaymentMode="+document.getElementById("cmbPaymentMode").value
				+"&ExportTo="+document.getElementById("cmbExportTo").value+"&fromDate="+document.getElementById("txtFromDate").value
				+"&toDate="+document.getElementById("txtToDate").value+"&billType="+document.getElementById("cmbBillType").value
				+"&schemeCode="+document.getElementById("cmbSchemeCode").value+"&orderBy="+document.getElementById("cmbOrderBy").value;
		var newWindow;
		var height = screen.height - 100;
		var width = screen.width;
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "BankPaymentRpt", urlstyle);
		
		//document.PrintBankPmntRprtForm.action=url;
		//document.PrintBankPmntRprtForm.submit();
		
	}
}

function showAuditorBankDtls(obj)
{
	if(obj.value == 'A')
	{
		document.getElementById("trAudName").style.display='inline';
		document.getElementById("tdBanklbl").style.display='none';
		document.getElementById("tdBank").style.display='none';  
		document.getElementById("tdBranchlbl").style.display='none';
		document.getElementById("tdBranch").style.display='none';
	}
	else if(obj.value =='B')
	{
		document.getElementById("trAudName").style.display='none';
		document.getElementById("tdBanklbl").style.display='inline';
		document.getElementById("tdBank").style.display='inline';  
		document.getElementById("tdBranchlbl").style.display='none';
		document.getElementById("tdBranch").style.display='none';
	}
	else if(obj.value =='Br')
	{
		document.getElementById("trAudName").style.display='none';
		document.getElementById("tdBanklbl").style.display='inline';
		document.getElementById("tdBank").style.display='inline';  
		document.getElementById("tdBranchlbl").style.display='inline';
		document.getElementById("tdBranch").style.display='inline';
	}
}

function validateBankPaymentReportData()
{
	var billType = document.getElementById("cmbBillType").value;
	if(!document.getElementById("radioAuditorBank1").checked && !document.getElementById("radioAuditorBank2").checked && !document.getElementById("radioAuditorBank3").checked)
	{
		alert("Please Select either Auditorwise or Bank/Branchwise.");
		return false;
	}
	if(IsEmptyFun('cmbPaymentMode','Please Select Mode of payment.')==false)
	{
		document.getElementById("cmbPaymentMode").focus();
		return false;
	}  
	if(IsEmptyFun('cmbBillType','Please Select Bill Type.')==false)
	{
		document.getElementById("cmbBillType").focus();
		return false;
	}
	if(billType == MONTHLY)
	{
		/*
		if(IsEmptyFun('cmbSchemeCode','Please Select Scheme code.')==false)
		{
			document.getElementById("cmbSchemeCode").focus();
			return false;
		}*/
		if(IsEmptyFun('cmbBillMonth','Please Select Bill Month.')==false)
		{
			document.getElementById("cmbBillMonth").focus();
			return false;
		}
		if(IsEmptyFun('cmbBillYear','Please Select Bill Year.')==false)
		{
			document.getElementById("cmbBillYear").focus();
			return false;
		}
		
	}
	if(billType == SUPP || billType == CVP || billType == PENSION || billType == DCRG)
	{
		if(IsEmptyFun('txtFromDate','Please Enter From Date.')==false)
		{
			document.getElementById("txtFromDate").focus();
			return false;
		}
		else if(IsEmptyFun('txtToDate','Please Enter To Date.')==false)
		{
			document.getElementById("txtToDate").focus();
			return false;
		}
	}
	
	if(document.getElementById("radioAuditorBank1").checked)
	{
		if(IsEmptyFun('cmbAuditor','Please Select Auditor Name.')==false)
		{
			document.getElementById("cmbAuditor").focus();
			return false;
		}
	}
	else if(document.getElementById("radioAuditorBank2").checked || document.getElementById("radioAuditorBank3").checked)
	{
		if(IsEmptyFun('cmbBank','Please Select Bank Name.')==false)
		{
			document.getElementById("cmbBank").focus();
			return false;
		}
		
	}
	if(document.getElementById("radioAuditorBank3").checked)
	{
		if(IsEmptyFun('cmbBranch','Please Select Branch Name.')==false)
		{
			document.getElementById("cmbBranch").focus();
			return false;
		}
	}
	return true;
}
function onChangeBillType()
{
	var billType = document.getElementById("cmbBillType").value;
	if(billType == MONTHLY)
	{
		document.getElementById("tdLblMonthYear").style.display = "inline";
		document.getElementById("tdMonthYear").style.display = "inline";
		document.getElementById("trFromToDt").style.display = "none";
		//document.getElementById("tdLblSchemeCode").style.display = "inline";
		//document.getElementById("tdSchemeCode").style.display = "inline";
	}
	else if(billType == SUPP || billType == CVP || billType == PENSION || billType == DCRG)
	{
		document.getElementById("tdLblMonthYear").style.display = "none";
		document.getElementById("tdMonthYear").style.display = "none";
		document.getElementById("trFromToDt").style.display = "inline";
		//document.getElementById("tdLblSchemeCode").style.display = "none";
		//document.getElementById("tdSchemeCode").style.display = "none";
	}
	else 
	{
		document.getElementById("tdLblMonthYear").style.display = "none";
		document.getElementById("tdMonthYear").style.display = "none";
		document.getElementById("trFromToDt").style.display = "none";
		//document.getElementById("tdLblSchemeCode").style.display = "none";
		//document.getElementById("tdSchemeCode").style.display = "none";
	}
	
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="PrintBankPmntRprtForm" id="PrintBankPmntRprtForm"	method="POST"  encType="multipart/form-data" validate="true">
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.BANKPAYMENTRPT" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="100%" align='center'>
	<tr>
		<td>
			<input type="radio" id="radioAuditorBank1" name="radioAuditorBank" value="A" onclick="showAuditorBankDtls(this);"/>
			<fmt:message key="PPMT.AUDITORWISE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="radio" id="radioAuditorBank2" name="radioAuditorBank" value="B" onclick="showAuditorBankDtls(this);"/>
			<fmt:message key="PPMT.BANKWISE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="radio" id="radioAuditorBank3" name="radioAuditorBank" value="Br" onclick="showAuditorBankDtls(this);"/>
			<fmt:message key="PPMT.BANKBRANCHWISE" bundle="${pensionLabels}"></fmt:message>
		</td>
		
	</tr>
	<tr>
	<td>
			<fmt:message key="PPMT.MODEOFPMNT" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td>
		<select id="cmbPaymentMode" name="cmbPaymentMode" >
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<option value="<fmt:message key="PPMT.PAYMODEECS" bundle="${pensionConstants}"/>" ><fmt:message key="PAYMODE.ECS" bundle="${pensionLabels}"></fmt:message></option>
				<option value="<fmt:message key="PPMT.PAYMODECHQ" bundle="${pensionConstants}"/>" ><fmt:message key="PAYMODE.CHEQUE" bundle="${pensionLabels}"></fmt:message></option>
				<option value="All" ><fmt:message key="PAYMODE.ALL" bundle="${pensionLabels}"></fmt:message></option>
				
		</select>
	</td>
	<td>
		   <fmt:message key="PPMT.BILLTYPE" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td>
		<select id="cmbBillType" name="cmbBillType" onchange="onChangeBillType()">
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<option value="<fmt:message key="RECOVERY.MONTHLY" bundle="${pensionCnst}"/>" ><fmt:message key="RECOVERY.MONTHLY" bundle="${pensionCnst}"></fmt:message></option>
				<option value="<fmt:message key="RECOVERY.PENSION" bundle="${pensionCnst}"/>" ><fmt:message key="RECOVERY.PENSION" bundle="${pensionCnst}"></fmt:message></option>
				<option value="<fmt:message key="RECOVERY.DCRG" bundle="${pensionCnst}"/>" ><fmt:message key="RECOVERY.DCRG" bundle="${pensionCnst}"></fmt:message></option>
				<option value="<fmt:message key="RECOVERY.CVP" bundle="${pensionCnst}"/>" ><fmt:message key="RECOVERY.CVP" bundle="${pensionCnst}"></fmt:message></option>
				<option value="<fmt:message key="RECOVERY.SUPP" bundle="${pensionCnst}"/>" ><fmt:message key="RECOVERY.SUPP" bundle="${pensionCnst}"></fmt:message></option>
				
		</select>
	</td>
	<td id = "tdLblSchemeCode" style = "display: none">
		   <fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td id = "tdSchemeCode" style = "display: none">
		<select id="cmbSchemeCode" name="cmbSchemeCode" >
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="schemeCode" items="${resValue.SchemeCodeList}"> 
				  <option value="${schemeCode}">${schemeCode}</option>
				</c:forEach>
		</select>
	</td>
	</tr>
	<tr id="trFromToDt" style="display: none">
	<td>
			<fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td>
		<input type="text" id="txtFromDate" name="txtFromDate" 
	         onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="26"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	        <img  src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtFromDate",375,570)' style="cursor: pointer;" />	
	</td>
	<td>
		   <fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td>
		<input type="text" id="txtToDate" name="txtToDate" 
	         onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="26"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	        <img  src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtToDate",375,570)' style="cursor: pointer;" />	
	</td>
	</tr>
	<tr>
	<td id="tdLblMonthYear" style = "display: none">
	        <fmt:message key="PPMT.FORMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td id="tdMonthYear" style = "display: none">
			<select name="cmbBillMonth" id="cmbBillMonth" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="month" items="${resValue.MonthList}">
					<option value='${month.id}'>
						<c:out value="${month.desc}"></c:out>
					</option>
					</c:forEach>
    		</select>
    		<select name="cmbBillYear" id="cmbBillYear" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="year" items="${resValue.YearList}">
					<option value='${year.desc}'>
						<c:out value="${year.desc}"></c:out>
					</option>
					</c:forEach>
    		</select>
			
	</td>
	<td>
	   Output
	</td>
	<td>
		<select id="cmbExportTo" name="cmbExportTo" >
			   	<option value="<fmt:message key="OUTPUT.SCREEN" bundle="${pensionConstants}"/>" ><fmt:message key="OUTPUT.SCREEN" bundle="${pensionLabels}"></fmt:message></option>
				<option value="<fmt:message key="OUTPUT.TEXTFILE" bundle="${pensionConstants}"/>" ><fmt:message key="OUTPUT.TEXTFILE" bundle="${pensionLabels}"></fmt:message></option>
				<option value="<fmt:message key="OUTPUT.EXCELFILE" bundle="${pensionConstants}"/>" ><fmt:message key="OUTPUT.EXCELFILE" bundle="${pensionLabels}"></fmt:message></option>	
								
		</select>
	</td>
	</tr>
	<tr id="trAudName" style="display:none">
		<td>
	        <fmt:message key="PPMT.AUDITORNAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
		   <select name="cmbAuditor" id="cmbAuditor" style="width: 77%;" tabindex="7">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="vo" items="${resValue.AuditorName}">
					<option value="${vo[1]}"> ${vo[0]} </option>
			</c:forEach>
			</select>
		</td>
		<td>
		</td>
	</tr>
	<tr >
		<td id="tdBanklbl" style="display:none">
	        <fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td id="tdBank" style="display:none">
		   <select name="cmbBank" id="cmbBank" style="width: 77%;" tabindex="7" >
			<c:forEach var="bank" items="${resValue.BankName}">
					<option value='${bank.id}'>
						<c:out value="${bank.desc}"></c:out>
					</option>
			</c:forEach>
			</select>
		</td>
		<td id="tdBranchlbl" style="display:none">
	        <fmt:message key="PPMT.BANKBRANCH" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td id="tdBranch" style="display:none">
		   <select name="cmbBranch" id="cmbBranch"  tabindex="7" >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			</select>
		</td>
	</tr>
	<tr>
		<td>
	        <fmt:message key="PPMT.ORDERBY" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
		   <select name="cmbOrderBy" id="cmbOrderBy" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<option value="<fmt:message key="ORDERBY.VOLUMEPAGENO" bundle="${pensionConstants}"/>" ><fmt:message key="PPMT.ORDERVOLUMEPAGE" bundle="${pensionLabels}"></fmt:message></option>
				<option value="<fmt:message key="ORDERBY.ACCOUNTNO" bundle="${pensionConstants}"/>" ><fmt:message key="PPMT.ORDERACCOUNTNO" bundle="${pensionLabels}"></fmt:message></option>
		   </select>
		</td>
		
	</tr>
</table>
</fieldset>
<table align='center'>
<tr>
<td>
<hdiits:button id="btnPrint" name="btnPrint" type="button" captionid="PPMT.PRINT" bundle="${pensionLabels}" onclick="showAllPrint();" />&nbsp;&nbsp;&nbsp;
<hdiits:button id="btnClose" name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>
</hdiits:form>

<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getAuditorBranchCode"
	parameters="AuditorBankCode={cmbBank}"
	source="cmbBank"
	target="cmbBranch">
</ajax:select>
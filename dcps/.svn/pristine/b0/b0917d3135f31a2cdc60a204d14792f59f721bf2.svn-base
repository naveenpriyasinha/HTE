<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />
<script type="text/javascript"  src="script/common/common.js"></script>
<script>
function showAllPrint()
{
	if(validateMonthlyBillReportData())
	{
		var url="ifms.htm?actionFlag=getPrintAllBill&sortBy="+document.getElementById("cmbOrderBy").value;
		if(document.getElementById("radioAuditorBank1").checked)
		{
			 url=url+"&billFlag=A&auditorPostID="+document.getElementById("cmbAuditorName").value;
			
			
		}else if(document.getElementById("radioAuditorBank2").checked)
		{
			var branchCodeString=getBranchCodeString();
			url=url+'&billFlag=B&bankCode='+document.getElementById("cmbBank").value+'&branchCodeString='+branchCodeString;
		}
	
		url=url+"&forMonth="+document.getElementById("cmbBillMonth").value+"&forYear="+document.getElementById("cmbBillYear").value;
		//var newWindow;
		//var height = screen.height - 100;
		//var width = screen.width;
		//var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		
		document.PrintAllBillForm.action=url;
		document.PrintAllBillForm.submit();
		//newWindow = window.open(url, "PensionerDetails", urlstyle);
	}
}
function getBranchCodeString()
{
	var InvForm = document.forms.PrintAllBillForm;
	var branchCodeString="";
	var flag=0;

	for (var x=0;x<InvForm.cmbBranch.length;x++)         
	 {          
		 
		    
		 if (InvForm.cmbBranch[x].selected)             
			{
			if(flag==0)
			{
				branchCodeString=branchCodeString+InvForm.cmbBranch[x].value;
				flag=1;
			}
			else
			{	
			 	branchCodeString = branchCodeString + "~" + InvForm.cmbBranch[x].value;
			}  
			            
		 }          
	}

	return branchCodeString;
}
function showAuditorBankDtls(obj)
{
	if(obj.value == 'A')
	{
		document.getElementById("trAudName").style.display='inline';
		document.getElementById("trBankBranch").style.display='none';
	}
	else if(obj.value =='B')
	{
		document.getElementById("trAudName").style.display='none';
		document.getElementById("trBankBranch").style.display='inline';
	}
	else
	{
		document.getElementById("trAudName").style.display='none';
		document.getElementById("trBankBranch").style.display='none';
	}
}

function validateMonthlyBillReportData()
{
	if(!document.getElementById("radioAuditorBank1").checked && !document.getElementById("radioAuditorBank2").checked)
	{
		alert("Please Select either Auditorwise or Bank/Branchwise.");
		return false;
	}
	if(IsEmptyFun('cmbBillMonth','Please Select Bill Month.')==false)
	{
		document.getElementById("cmbBillMonth").focus();
		return false;
	}
	else if(IsEmptyFun('cmbBillYear','Please Select Bill Year.')==false)
	{
		document.getElementById("cmbBillYear").focus();
		return false;
	}
	if(document.getElementById("radioAuditorBank1").checked)
	{
		if(IsEmptyFun('cmbAuditorName','Please Select Auditor Name.')==false)
		{
			document.getElementById("cmbAuditorName").focus();
			return false;
		}
	}
	else if(document.getElementById("radioAuditorBank2").checked)
	{
		if(IsEmptyFun('cmbBank','Please Select Bank Name.')==false)
		{
			document.getElementById("cmbBank").focus();
			return false;
		}
		else if(IsEmptyFun('cmbBranch','Please Select Branch Name.')==false)
		{
			document.getElementById("cmbBranch").focus();
			return false;
		}
	}
	return true;
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="PrintAllBillForm" id="PrintAllBillForm"	method="POST"  encType="multipart/form-data" validate="true">
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.MONTHLYBILLREPORT" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="70%" align='center'>
	<tr>
		<td>
			<input type="radio" id="radioAuditorBank1" name="radioAuditorBank" value="A" onclick="showAuditorBankDtls(this);"/>
			<fmt:message key="PPMT.AUDITORWISE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="radio" id="radioAuditorBank2" name="radioAuditorBank" value="B" onclick="showAuditorBankDtls(this);"/>
			<fmt:message key="PPMT.BANKBRANCHWISE" bundle="${pensionLabels}"></fmt:message>
		</td>
	</tr>
	<tr></tr>
	<tr></tr>
	<tr>
	<td>
	        <fmt:message key="PPMT.FORMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td>
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
	</tr>
	<tr id="trAudName" style="display:none">
		<td>
	        <fmt:message key="PPMT.AUDITORNAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
		   <select name="cmbAuditorName" id="cmbAuditorName" style="width: 77%;" tabindex="7">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="vo" items="${resValue.AuditorName}">
					<option value="${vo[1]}"> ${vo[0]} </option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr id="trBankBranch" style="display:none">
		<td>
	        <fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
		   <select name="cmbBank" id="cmbBank" style="width: 77%;" tabindex="7" >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="bank" items="${resValue.BankName}">
					<option value='${bank.id}'>
						<c:out value="${bank.desc}"></c:out>
					</option>
			</c:forEach>
			</select>
		</td>
		<td>
	        <fmt:message key="PPMT.BANKBRANCH" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
		   <select name="cmbBranch" id="cmbBranch"  tabindex="7" multiple="multiple">
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
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchListFromBankCode" source="cmbBank" target="cmbBranch" parameters="bankCode={cmbBank}" ></ajax:select>
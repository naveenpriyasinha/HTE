<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
function monthlyPenReport(){
	var monthId = document.getElementById("cmbMonth").value;
	var yearId = document.getElementById("cmbYear").value;
	var locCode = document.getElementById("cmbTreasuryName").value;
	var voucherNo = document.getElementById("cmbVoucherNo").value;

	if(monthId =="-1"){
		alert("Select Month Name");
		document.getElementById("cmbMonth").focus();
		return false;			
	}
	if(yearId =="-1"){
		alert("Select Year");
		document.getElementById("cmbYear").focus();
		return false;			
	}
	if(locCode =="-1"){
		alert("Select Treasury Name");
		document.getElementById("cmbTreasuryName").focus();
		return false;			
	}
	if(voucherNo =="-1"){
		alert("Select Voucher No");
		document.getElementById("cmbVoucherNo").focus();
		return false;			
	}	
	
	var url = "ifms.htm?actionFlag=loadMonthlyPensionReportAG&elementId=365549";
	document.MonthlyPenReportParaPage.action = url ;
	document.MonthlyPenReportParaPage.submit();	
}

function removeVoucherNo(){
	
	 var voucherNo=document.getElementById("cmbVoucherNo").length;	 
	 for(var i=0;i<Number(voucherNo);i++)
	 {
		  var lgth = document.getElementById("cmbVoucherNo").options.length -1;				  
		  document.getElementById("cmbVoucherNo").options[lgth] = null;
	 }
	 var theOption = new Option;
	 theOption.value="-1";
	 theOption.text="-- Select --";			
	 document.getElementById("cmbVoucherNo").options[0]=theOption;
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<hdiits:form name="MonthlyPenReportParaPage" id="MonthlyPenReportParaPage" encType="multipart/form-data" validate="true" method="post">

<fieldset class="tabstyle"><legend><fmt:message key="PPMT.MONTHLYPENREPORTAG" bundle="${pensionLabels}"/></legend>
<table width="70%">
<tr>
	<td>
		<table width="100%">
			<tr>
					<td width="15%"><fmt:message key="PPMT.MONTH" bundle="${pensionLabels}"/></td>
					<td width="40%">
					<select name="cmbMonth" id="cmbMonth" style="width:200px">
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="Month" items="${resValue.MonthList}">
							<c:choose>
								<c:when test="${resValue.MonthId == Month.id}">
									<option value="${Month.id}" selected="selected">
									<c:out value="${Month.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${Month.id}"><c:out value="${Month.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>										
					</select><label class="mandatoryindicator">*</label>		
					</td>			
					<td width="20%"><fmt:message key="PPMT.YEAR" bundle="${pensionLabels}"/></td>
					<td width="30%">
					<select name="cmbYear" id="cmbYear" style="width:200px">
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="Year" items="${resValue.YearList}">															
							<c:choose>
								<c:when test="${resValue.YearId == Year.id}">
									<option value="${Year.id}" selected="selected">
									<c:out value="${Year.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${Year.id}"><c:out value="${Year.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>	
					</select><label class="mandatoryindicator">*</label>
					</td>
			</tr>
			<tr>
					<td width="15%"><fmt:message key="PPMT.TREASURYNAME" bundle="${pensionLabels}"/></td>
					<td width="40%">
					<select name="cmbTreasuryName" id="cmbTreasuryName" style="width:350px">
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="TreasuryName" items="${resValue.TreasuryNameList}">															
							<c:choose>
								<c:when test="${resValue.LocCode == TreasuryName.id}">
									<option value="${TreasuryName.id}" selected="selected">
									<c:out value="${TreasuryName.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${TreasuryName.id}"><c:out value="${TreasuryName.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>	
					</select><label class="mandatoryindicator">*</label>
					</td>
					<td width="20%"><fmt:message key="PPMT.VOUCHERNO" bundle="${pensionLabels}"/></td>
					<td width="30%">
					<select name="cmbVoucherNo" id="cmbVoucherNo" style="width:200px">
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="VoucherNo" items="${resValue.VoucherNoList}">															
							<c:choose>
								<c:when test="${resValue.VoucherNo == VoucherNo.id}">
									<option value="${VoucherNo.id}" selected="selected">
									<c:out value="${VoucherNo.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${VoucherNo.id}"><c:out value="${VoucherNo.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>	
					</select>
					<label class="mandatoryindicator">*</label>
					</td>
			</tr>
		</table>
		<br>
		<CENTER>
		<hdiits:button type="button" captionid="PPMT.GENERATEREPORT" style="width:15%" bundle="${pensionLabels}" id="btnReport" name="btnReport" onclick="monthlyPenReport();"/>
		<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';"/>
		</CENTER>
	</td>
</tr>
</table>
</fieldset>
<br>
<br>
<c:if test="${resValue.MonthId != '' && resValue.MonthId != null}">
<fieldset class="tabstyle"><legend><fmt:message key="PPMT.VIEWMONTHLYPENREPORT" bundle="${pensionLabels}"/></legend>
<table width="40%" border="2">
	<tr>
			<td width="15%"><fmt:message key="PPMT.MONTHLYDTLSREPORT" bundle="${pensionLabels}"/></td>
			<td width="15%">
			<a href = "ifms.htm?actionFlag=generateMonthlyPenReport&monthId=${resValue.MonthId}&yearId=${resValue.YearId}&locCode=${resValue.LocCode}&voucherNo=${resValue.VoucherNo}&criteria=1">
			<fmt:message key="PPMT.VIEW" bundle="${pensionLabels}"/></a>
			</td>
	</tr>
	<tr>
			<td width="15%"><fmt:message key="PPMT.MONTHLYRECOVERYREPORT" bundle="${pensionLabels}"/></td>
			<td width="15%">
			<a href = "ifms.htm?actionFlag=generateMonthlyPenReport&monthId=${resValue.MonthId}&yearId=${resValue.YearId}&locCode=${resValue.LocCode}&voucherNo=${resValue.VoucherNo}&criteria=2">
			<fmt:message key="PPMT.VIEW" bundle="${pensionLabels}"/></a>
			</td>
	</tr>
	<tr>
			<td width="15%"><fmt:message key="PPMT.MONTHLYALLOCATIONREPORT" bundle="${pensionLabels}"/></td>
			<td width="15%">
			<a href = "ifms.htm?actionFlag=generateMonthlyPenReport&monthId=${resValue.MonthId}&yearId=${resValue.YearId}&locCode=${resValue.LocCode}&voucherNo=${resValue.VoucherNo}&criteria=3">
			<fmt:message key="PPMT.VIEW" bundle="${pensionLabels}"/></a>
			</td>
	</tr>	
</table>
</fieldset>
</c:if>
</hdiits:form>
<ajax:select 
baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getVoucherNo" 
source="cmbTreasuryName" 
target="cmbVoucherNo" 
parameters="MonthId={cmbMonth},YearId={cmbYear},TreasuryCode={cmbTreasuryName}"
preFunction="removeVoucherNo" />
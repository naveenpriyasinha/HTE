<% try{%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources/pensionpay/PensionCaseConstants" var="pensionConst" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts" var="pensionAlerts" scope="request" />
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<script type="text/javascript">
ALRTSCHEMECODE='<fmt:message key="PPMT.SCHEMECODE" bundle="${pensionAlerts}"></fmt:message>';
ALRTMONTH='<fmt:message key="PPMT.YEAR" bundle="${pensionAlerts}"></fmt:message>';
ALRTYEAR='<fmt:message key="PPMT.MONTH" bundle="${pensionAlerts}"></fmt:message>';
ALRTPAYMODE='<fmt:message key="PPMT.PAYMODE" bundle="${pensionAlerts}"></fmt:message>';
</script>
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
<script type="text/javascript"  src="script/pensionpay/GenerateMonthlyPensionBill.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<hdiits:form name="BankGroupMapping" method="post" validate="">
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="MONTHLYBILL.HDNG" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<table>
		<tr>
			<td width="20%">
				<fmt:message key="MONTHLYBILL.SCHEMECODE" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="20%">
				<select name="cmbSchemeCode" id="cmbSchemeCode"  tabindex="2" onchange="showSchemeDesc(this)">
					<option value="-1"><fmt:message key="CMN.SELECT" bundle="${pensionLabels}"></fmt:message></option>
					<c:forEach var="schemeCode" items="${resValue.lLstSchemeCode}">																		
						<option value="${schemeCode}"> <c:out value="${schemeCode}"></c:out> </option>
					</c:forEach>
				</select>
			</td>
			<td width="20%">
				<fmt:message key="MONTHLYBILL.SCHEMEDESC" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="40%">
				<input type="text" id="schemeDesc" name = "schemeDesc" style="width:500px;" disabled="disabled"/> 
			</td>
		</tr>
		<tr>
			<td width="20%">
				<fmt:message key="MNTH.YEAR" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="20%">
				<select name="cmbForYear" id="cmbForYear"  tabindex="2" >
					<c:forEach var="SgvcFinYearMstVO" items="${resValue.SgvcFinYearMstVOArray}">																		
						<c:if test="${SgvcFinYearMstVO.finYearCode == resValue.CurrentYear }">
							<option value="${SgvcFinYearMstVO.finYearCode}" selected="selected" > <c:out value="${SgvcFinYearMstVO.finYearCode}"></c:out> </option>
						</c:if>
						<c:if test="${SgvcFinYearMstVO.finYearCode != resValue.CurrentYear }">
							<option value="${SgvcFinYearMstVO.finYearCode}"> <c:out value="${SgvcFinYearMstVO.finYearCode}"></c:out> </option>
						</c:if> 																		 
					</c:forEach>
				</select>
			</td>
			<td width="20%">
				<fmt:message key="MNTH.MONTH" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="40%">
				<select name="cmbForMonth" id="cmbForMonth"  tabindex="1" >
					<c:forEach var="SgvaMonthMstVO" items="${resValue.SgvaMonthMstVOArray}">												 					
						<c:if test="${SgvaMonthMstVO.monthNo == resValue.CurrentMonth }">
							<option value="${SgvaMonthMstVO.monthNo}" selected="selected" > <c:out value="${SgvaMonthMstVO.monthName}"> </c:out> </option>
						</c:if>
						<c:if test="${SgvaMonthMstVO.monthNo != resValue.CurrentMonth }">
							<option value="${SgvaMonthMstVO.monthNo}" > <c:out value="${SgvaMonthMstVO.monthName}"> </c:out> </option> 
						</c:if>  
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%">
				<fmt:message key="MONTHLYBILL.PAYMODE" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="20%">
				<select id="payMode" name="payMode">
					<option value="-1"><fmt:message key="CMN.SELECT" bundle="${pensionLabels}"></fmt:message></option>
					<option value='<fmt:message key="PPMT.PAYMODEECS" bundle="${pensionConst}"></fmt:message>'><fmt:message key="MONTHLYBILL.PAYMODE.ECS" bundle="${pensionLabels}"></fmt:message></option>
					<option value='<fmt:message key="PPMT.PAYMODECHQ" bundle="${pensionConst}"></fmt:message>'><fmt:message key="MONTHLYBILL.PAYMODE.CHQ" bundle="${pensionLabels}"></fmt:message></option>
				</select>
			</td>
			<td width="20%"></td>
			<td width="40%"></td>
		</tr>
	</table>
</fieldset>
<br/>
<div style="text-align:center">
	<hdiits:button name="btnGenPnsnBill" id="btnGenPnsnBill" type="button"  captionid="MONTHLYBILL.BTN" bundle="${pensionLabels}" onclick="generateMonthlyPnsnBill()" style="width:300px;"/>
	<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</div>
</hdiits:form>	
<% }catch(Exception e)
{
	e.printStackTrace();
}%>
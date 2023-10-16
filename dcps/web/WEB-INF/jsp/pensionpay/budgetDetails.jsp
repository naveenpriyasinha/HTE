<%try { %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message key="PPMT.BUDGETDETAILS"
		bundle="${pensionLabels}"></fmt:message></b> </legend> 

<table align="center" width="98%" border="0" class="Label" >
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.DEMAND" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtDemand"  size="20"  name="txtDemand" value="${resValue.demandNo}" disabled="true" value=""/>
			</td>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.MAJORHEAD" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtMajorHead"  size="20"  name="txtMajorHead" value="${resValue.majorHead}" disabled="true" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.SUBMAJORHEAD" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtSubMajorHead"  size="20"  name="txtSubMajorHead" value="${resValue.subMajorHead}" disabled="true"/>
			</td>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.MINORHEAD" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtMinorHead"  size="20"  name="txtMinorHead" value="${resValue.minorHead}" disabled="true" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.SUBMINORHEAD" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtSubHead"  size="20"  name="txtSubHead" value="${resValue.subHead}" disabled="true"/>
			</td>
		</tr>	
</table>		
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message key="PPMT.LISTOFCHEQUES"
		bundle="${pensionLabels}"></fmt:message></b> </legend>
		
	<table align="center" width="98%" border="0">
	
	<tr class="datatableheader" style="width: 90px">
		<td width="10%" class="HLabel"><fmt:message key="PPMT.CHEQUETYPE"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.BENNAME"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.ACCNO"
			bundle="${pensionLabels}"></fmt:message></td>	
		<td width="10%" class="HLabel"><fmt:message key="PPMT.IFSCCODE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.CHEQUEAMNT"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>	
	</tr>		
	<c:forEach  begin='1' end='1' varStatus="Counter">
		<tr>
			<td class="tds" align="center">
				<select name="cmbChequeType" id="cmbChequeType">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<option value="PC"><fmt:message key="PC" /></option>
				</select>
			</td>
			<td class="tds" align="center">
				<input type="text" id="txtBenName" name="txtBenName" disabled="true" value="${resValue.firstName}"/>
			</td>
			<td class="tds" align="center">
				<input type="text" id="txtAccountNo" name="txtAccountNo" disabled="true" value="${resValue.accountNo}" />
			</td>
			<td class="tds" align="center">
				<input type="text" id="txtIFSCCode" name="txtIFSCCode" disabled="true"/>
			</td>
			<td class="tds" align="center">
				<input type="text" id="txtChequeAmnt" name="txtChequeAmnt" disabled="true"/>
			</td>
		</tr>
	</c:forEach>
	</table>		
</fieldset>		
<% } catch(Exception e){
e.printStackTrace();
}%>
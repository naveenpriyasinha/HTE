<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  

<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionAlerts" var="PensionAlerts" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionToolTip" var="pensionToolTip" scope="request"/>



<%@page import="java.util.Calendar"%>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/pensionpay/Monthly.js"> </script>
<script  type="text/javascript"  src="script/common/behaviour.js"></script>
<script  type="text/javascript"  src="script/pensionpay/behaviourMonthlyBill.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript">
	keyPreview =1;
	var BANKCODE = '<fmt:message key="MNTH.BANKCODE" bundle="${PensionAlerts}"></fmt:message>';
	var BANK = '<fmt:message key="MNTH.BANK" bundle="${PensionAlerts}"></fmt:message>';
	var BRANCH = '<fmt:message key="MNTH.BRANCH" bundle="${PensionAlerts}"></fmt:message>';
	var HEADCODE = '<fmt:message key="MNTH.HEADCODE" bundle="${PensionAlerts}"></fmt:message>';
	var SCHEME = '<fmt:message key="MNTH.SCHEME" bundle="${PensionAlerts}"></fmt:message>';
	var BILLGEN = '<fmt:message key="MNTH.BILLGEN" bundle="${PensionAlerts}"></fmt:message>';
	var AL_AJAX='<fmt:message key="PEN.AJAX" bundle="${PensionAlerts}"></fmt:message>';	
	var BILLVARGEN = '<fmt:message key="RPT.MNTHVAR" bundle="${PensionAlerts}"></fmt:message>';
</script>


<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<%@page	import="com.tcs.sgv.common.valueobject.SgvaMonthMst"%>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="lLstBankBrnchGroup" value="${resValue.lLstBankBrnchGroup}"></c:set>
<fmt:message key="BTN.RESET" bundle="${pensionLabels}" var="BTN_RESET"/>
<c:set var="CurDate" value="${resValue.CurDate}" />
<hdiits:form name="monthlyPension" validate="false" method="post" >
<hdiits:hidden name="CurrentMonth" id="CurrentMonth" default="${resValue.CurrentMonth}"/>
<hdiits:hidden name="CurrentYear" id="CurrentYear" default="${resValue.CurrentYear}"/>

<center>
<fieldset class="tabstyle" width="80%">
	<br>
	<legend id="headingMsg" class="Label">	
		<b>
			<fmt:message key="BTN.GENCHNGSTMNT" bundle="${pensionLabels}"></fmt:message>
		</b>
    </legend>
	<br>
	<table width="100%">
		<tr>
			<td width="20%"><fmt:message key="PPMT.GENCHANGSTMNTBY" bundle="${pensionLabels}"></fmt:message></td>
			<td width="80%" style="text-align: left;">
				<input type = "radio"  id = "branchWise" name="genChangStmntBy" value="branchWise" onclick="genChangStmntBySelection(this)" checked="checked"/><fmt:message key="GENCHANGSTMNT.BRANCHWISE" bundle="${pensionLabels}"></fmt:message>&nbsp&nbsp&nbsp
				<input type = "radio"  id = "grpWise" name="genChangStmntBy" value="grpWise" onclick="genChangStmntBySelection(this)" /> <fmt:message key="GENCHANGSTMNT.GRPWISE" bundle="${pensionLabels}"></fmt:message>
			</td>
		</tr>
	</table>
	<br/>
	<table align="center"  width="80%" border="0" align="center" class="Label" >
		<hdiits:tr>
			<hdiits:td align="left" width="10%"><fmt:message key="MNTH.FORMONTH" bundle="${pensionLabels}"></fmt:message></hdiits:td>
			<hdiits:td align="left" width="20%">
				<select name="cmbForMonth" id="cmbForMonth"  tabindex="1" title='<fmt:message key="MNTH.MONTH" bundle="${pensionToolTip}"></fmt:message>'>
					<c:forEach var="SgvaMonthMstVO" items="${resValue.SgvaMonthMstVOArray}">												 					
						<c:if test="${SgvaMonthMstVO.monthNo == resValue.CurrentMonth }">
							<option value="${SgvaMonthMstVO.monthNo}" selected="selected" > <c:out value="${SgvaMonthMstVO.monthName}"> </c:out> </option>
						</c:if>
						<c:if test="${SgvaMonthMstVO.monthNo != resValue.CurrentMonth }">
							<option value="${SgvaMonthMstVO.monthNo}" > <c:out value="${SgvaMonthMstVO.monthName}"> </c:out> </option> 
						</c:if>  
					</c:forEach>
				</select>
			
				<select name="cmbForYear" id="cmbForYear"  tabindex="2" title='<fmt:message key="MNTH.YEAR" bundle="${pensionToolTip}"></fmt:message>'>
					<c:forEach var="SgvcFinYearMstVO" items="${resValue.SgvcFinYearMstVOArray}">																		
						<c:if test="${SgvcFinYearMstVO.finYearCode == resValue.CurrentYear }">
							<option value="${SgvcFinYearMstVO.finYearCode}" selected="selected" > <c:out value="${SgvcFinYearMstVO.finYearCode}"></c:out> </option>
						</c:if>
						<c:if test="${SgvcFinYearMstVO.finYearCode != resValue.CurrentYear }">
							<option value="${SgvcFinYearMstVO.finYearCode}"> <c:out value="${SgvcFinYearMstVO.finYearCode}"></c:out> </option>
						</c:if> 																		 
					</c:forEach>
				</select>
				<label class="mandatoryindicator">*</label>
			</hdiits:td>
			<hdiits:td align="left" width="10%"></hdiits:td>
			<hdiits:td align="left" width="25%"></hdiits:td>
		    <hdiits:td align="left" width="10%"></hdiits:td>
		    <hdiits:td align="left" width="25%"></hdiits:td>
		</hdiits:tr>
		<hdiits:tr></hdiits:tr>
		<hdiits:tr id="bnkBrnch">
			<hdiits:td align="left">Branch Code</hdiits:td>
			<hdiits:td align="left"> 
					<input type="text" id="txtbranchCode" name="txtbranchCode" size="10" tabindex="6" onblur="getBranchByBranchCodeMnthly()" title="<fmt:message key="MNTH.BRANCHCODE" bundle="${pensionToolTip}"></fmt:message>"/> 
			</hdiits:td>
			<hdiits:td align="left"><fmt:message key="MNTH.BANK" bundle="${pensionLabels}"></fmt:message>
			</hdiits:td>
			<hdiits:td align="left" >
				<select name="cmbBank" id="cmbBank" tabindex="6" size="5" title='<fmt:message key="MNTH.BANK" bundle="${pensionToolTip}"></fmt:message>' >
					<c:forEach var="ListAuditorBankCode" items="${resValue.ListAuditorBankCode}" varStatus="i">
						<option value='${ListAuditorBankCode.id}'>
							<c:out value="${ListAuditorBankCode.desc}"></c:out>									
						</option>
						<script>
							bankCode['${i.index}'] = '${ListAuditorBankCode.id}';
						</script>
					</c:forEach>
					
				</select>
				<label class="mandatoryindicator">*</label>
			</hdiits:td>
			<hdiits:td align="left"><fmt:message key="MNTH.BRANCH" bundle="${pensionLabels}"></fmt:message></hdiits:td>
			<hdiits:td align="left">
				<select name="cmbBranch" id="cmbBranch" tabindex="7" size="5" style="width=25"	 title='<fmt:message key="MNTH.BRANCH" bundle="${pensionToolTip}"></fmt:message>' >
					<option value="-1">--Select--</option>
				</select>
				<label class="mandatoryindicator">*</label>
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr id="bnkBranchGrp" >
			<hdiits:td align="left" width="10%"><fmt:message key="GENCHANGSTMNT.BNKBRANCHGRP" bundle="${pensionLabels}"></fmt:message></hdiits:td>
			<hdiits:td align="left" width="20%">
				<select id="cmbGrpName" name="cmbGrpName" onChange="getBankBranchGrpDtls(this)">
					<option value="-1">---Select---</option>
					<c:forEach var="grpName" items = "${lLstBankBrnchGroup}">
						<option value="${grpName.id}"><c:out value="${grpName.desc}"></c:out></option>
					</c:forEach>
				</select>
				<label class="mandatoryindicator">*</label>
			</hdiits:td>
			<hdiits:td align="left" width="10%"></hdiits:td>
			<hdiits:td align="left" width="25%"></hdiits:td>
		    <hdiits:td align="left" width="10%"></hdiits:td>
		    <hdiits:td align="left" width="25%"></hdiits:td>
		</hdiits:tr>
	</table>
	<br>
	<div align="left" style="height:100px;"><font color="Red" size="3" style="font-family: monospace;" >
	<i><b>
	Note: Before generating Change Statement, please get all the modifed cases approved from Treasury Officer.<br/>
	System will allow generation of change statement for previous and future month  for testing purpose only.<br/>
	In actual system it will allow generation of change statement for current month only.
	</b></i></font><div>
</fieldset>
</center>
<script>
	document.getElementById("cmbForMonth").focus();
	document.getElementById("bnkBranchGrp").style.display = "none";
</script>
<br>
<br>
<div align="center">
		<input style="width:200px;" type="button" class="buttontag"  name="txtGenChngStmnt" id="GenChngStmnt"  value="<fmt:message key="BTN.GENCHNGSTMNT" bundle="${pensionLabels}" ></fmt:message>" tabindex="7" onclick="validateChngStmnt()"  />	
		<input type="button" class="buttontag"  name="txtCancel" type="button" value="<fmt:message key="MNTH.CLOSE" bundle="${pensionLabels}" ></fmt:message>" tabindex="9" onclick="javascript:pageClose()" title="<fmt:message key="PNSN.Close" bundle="${pensionToolTip}"></fmt:message>" />
</div>

<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getAuditorBranchCode"
	parameters="AuditorBankCode={cmbBank}"
	source="cmbBank"
	target="cmbBranch"
	postFunction="disableBranchCode"></ajax:select>

</hdiits:form>





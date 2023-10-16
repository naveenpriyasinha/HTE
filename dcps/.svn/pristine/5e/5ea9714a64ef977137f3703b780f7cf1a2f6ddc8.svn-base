<% try{%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionConstants" var="pensionConstant" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionCaseLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstBank" value="${resValue.lLstBank}"></c:set>
<c:set var="lLstBankGroup" value="${resValue.lLstBankGroup}"></c:set>

<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/pensionpay/bankGroupMapping.js"></script>
<hdiits:form name="BankGroupMapping" method="post" validate="">
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.BANKGRPMPG" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	
	
	
	<br/>
	<table>
		<tr>
			<td width="10%"><fmt:message key="BANKGRP.ADDUPDATE" bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%">
				<input type = "radio"  id = "addGrp" name="actionGrp" value="Add" onclick="showAddUpdtContent(this)" checked="checked"/> <fmt:message key="BANKGRP.ADD" bundle="${pensionLabels}"></fmt:message>&nbsp&nbsp&nbsp
				<input type = "radio"  id = "updateGrp" name="actionGrp" value="Update" onclick="showAddUpdtContent(this)"/><fmt:message key="BANKGRP.UPDATE" bundle="${pensionLabels}"></fmt:message>
				<input type = "radio"  id = "deleteGrp" name="actionGrp" value="Delete" onclick="showAddUpdtContent(this)"/><fmt:message key="BANKGRP.DELETE" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="10%">
			<td width="40%"></td>
		</tr>
			<tr>
				<td width="10%"><fmt:message key="BANKGRP.GROUPNAME" bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%">
				<input type="text" id="txtGrpName" name="txtGrpName" />
				<select id="cmbGrpName" name="cmbGrpName" style="display:none;" onchange="showBankGroupDtls(this);">
					<option value="-1">---Select---</option>
					<c:forEach var="grpName" items = "${lLstBankGroup}">
						<option value="${grpName.id}"><c:out value="${grpName.desc}"></c:out></option>
					</c:forEach>
				</select>
			</td>
			<td width="10%"></td>
			<td width="40%"></td>
		</tr>
		<tr id="trBankBranchDtls">
			<td width="10%"><fmt:message key="BANKGRP.BANKNAME" bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%">
				<select id="cmbBankName" name="cmbBankName">
					<c:forEach var="bankName" items = "${lLstBank}">
						<option value="${bankName.id}"><c:out value="${bankName.desc}"></c:out></option>
					</c:forEach>
				</select>
			</td>
			<td width="10%"><fmt:message key="BANKGRP.BRNCHNAME" bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%">
				<select id="cmbBranchName" name="cmbBranchName">
						<option value="-1">----Select---</option>
				</select>&nbsp&nbsp&nbsp&nbsp
				<hdiits:button name="btnAdd" id="btnAdd" type="button"  captionid="BANKGRP.ADD" bundle="${pensionLabels}" onclick="addBranchToGroup()"/>
			</td>
		</tr>
	</table>
	<br/>
	<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
	<table id="tblBankGrpDtls" align="left" width="95%" cellspacing="0" border="1">
	   		<tr class="datatableheader" >						   
				   <td width="5%" class="datatableheader"><fmt:message key="BANKGRP.SRNO" bundle="${pensionLabels}"></fmt:message></td>
				   <td width="45%" class="datatableheader"><fmt:message key="BANKGRP.BANKNAME" bundle="${pensionLabels}" ></fmt:message></td>	
				   <td width="45%" class="datatableheader"><fmt:message key="BANKGRP.BRNCHNAME" bundle="${pensionLabels}"></fmt:message></td>						   
				   <td id="deleteRow" width="5%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
		    </tr>
	</table>
	<input type="hidden" id="hidBankGrpGridSize" value="1" />		
	</div>
</fieldset>
<br/>
<center>
<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveBankGroupDtls();"/>
<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</center>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getAuditorBranchCode"
	parameters="AuditorBankCode={cmbBankName}"
	source="cmbBankName"
	target="cmbBranchName">
</ajax:select>
</hdiits:form>
<% }catch(Exception e)
{
	e.printStackTrace();
}%>
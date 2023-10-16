<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request" />

<script language="javascript" src="script/lna/loansEmpSearch.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>

<hdiits:form name="EmployeeSearchForm" id="EmployeeSearchForm"	encType="multipart/form-data" validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<table cellpadding="10"></table>
<input type="hidden"  name='txtUserType' id="txtUserType" value="${resValue.userType}" />
<input type="hidden"  name='txtAlert' id="txtAlert" value="${resValue.alertMessage}" />
<input type="hidden"  name='hidSevaarthId' id="hidSevaarthId" value="${resValue.SevaarthId}" />
<input type="hidden"  name='hidEmpName' id="hidEmpName" value="${resValue.EmpName}" />
<input type="hidden"  name='hidReqType' id="hidReqType" value="${resValue.requestType}" />

	<fieldset class="tabstyle"><legend><fmt:message key="CMN.SEARCHEMP" bundle="${lnaLabels}"></fmt:message></legend>

		<table border="0" width="70%" align="center" cellpadding="4" cellspacing="4">
			<tr align="center">
				<td width="25%" align="left" ><fmt:message key="CMN.SEVAARTHID" bundle="${lnaLabels}" /></td>
				<td width="50%" align="left"><input type="text"	id="txtSevaarthId" style="text-transform: uppercase" size="30"	name="txtSevaarthId"/>
				<script	type="text/javascript" language="JavaScript">
						document.forms[0].txtSevaarthId.focus();
				</script></td>
			</tr>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"	bundle="${lnaLabels}" /></td>
			<td width="50%" align="left"><input type="text"	id="txtEmployeeName" size="30" onblur="isName(this,'This field should not contain any special characters or digits')"	name="txtEmployeeName" style="text-transform: uppercase" />
				<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
		</tr>
		<tr>
				<td colspan="2" align="center"><label style="color: red"><fmt:message key="MSG.SEARCH" bundle="${lnaLabels}" /></label></td>
		</tr>
		<tr>
			<td width="25%">
				<fmt:message key="CMN.REQTYPE" bundle="${lnaLabels}"></fmt:message>
			</td>
			<td width="40%">
			<select name="cmbLoanType" id="cmbLoanType" style="width:230px">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="LoanType" items="${resValue.lstLoanType}">
					<option value="${LoanType.lookupId}">
						<c:out value="${LoanType.lookupDesc}"/>
					</option>						
				</c:forEach>
			</select>	
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			<table border="0" width="50%" align="center">
				<tr>
					<td align="left"><hdiits:button name="btnSubmitData" id="btnSubmitData" type="button" captionid="BTN.SUBMIT" bundle="${lnaLabels}" onclick="submitSearchDetails();" /></td>
					<td align="left"><hdiits:button name="btnClearAllFields" id="btnClearAllFields" type="button" captionid="BTN.CLEAR"	bundle="${lnaLabels}" onclick="clearAllfields();" /></td>
					<td align="left"><hdiits:button name="btnClose" id="btnClose" type="button" captionid="BTN.CLOSE"	bundle="${lnaLabels}" onclick="getHomePage();" /></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	</fieldset>
	
</hdiits:form>
<c:if test="${resValue.userType=='DEO'}">
<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName" baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteLNA&userType=DEO"
	parameters="searchKey={txtEmployeeName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
</c:if>
<c:if test="${resValue.userType=='HODASST2'}">
<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName" baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteLNA&userType=HODASST2"
	parameters="searchKey={txtEmployeeName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" postFunction="setFocusOnRequestType"/>
</c:if>
<script>
    init();
 </script>
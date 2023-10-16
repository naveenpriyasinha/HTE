<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/dcpsTreasury.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="AdminDept" value="${resValue.listAdminDept}"></c:set>
<c:set var="Districts" value="${resValue.lLstDistricts}"></c:set>
<c:set var="DummyOfficeVO" value="${resValue.lObjMstDummyOffice}"></c:set>
<c:set var="OfficeId" value="${resValue.OfficeId}"></c:set>

<hdiits:form name="dummyOfficeInfo" encType="multipart/form-data" validate="true" method="post">
	<fieldset class="tabstyle"><legend> <b><fmt:message key="CMN.OFFICEDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
		<table width="100%" align="center" cellpadding="4" cellspacing="4" >
		<tr>
		 	<td width="15%" align="left"><fmt:message key="CMN.OFFICECODE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name="txtOffCode" id="txtOffCode" size="30" readonly="readonly" value="${OfficeId}" /></td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PARENTFIELDDEPT" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
				<select name="listAdminDept" id="listAdminDept" style="width: 65%;"  >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="AdminDept" items="${AdminDept}">
							<c:choose>
								<c:when test="${DummyOfficeVO.adminDept == AdminDept.id}">
									<option value="${AdminDept.id}" selected="selected"><c:out value="${AdminDept.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${AdminDept.id}" ><c:out value="${AdminDept.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.OFFICENAME" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtOffName" size="30" name="txtOffName" value="${DummyOfficeVO.dummyOfficeName}"/></td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.ADDRESS1" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtOffAddr1" size="30" name="txtOffAddr1" value="${DummyOfficeVO.offAddr1}"/>
			<td width="15%" align="left"><fmt:message key="CMN.DISTRICT" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
				<select name="cmbDistrict" id="cmbDistrict" style="width: 65%">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="District" items="${Districts}">
					<c:choose>
						<c:when test="${DummyOfficeVO.district==District.id}">
							<option value="${District.id}" selected="selected"><c:out
								value="${District.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${District.id}"><c:out
								value="${District.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.ADDRESS2" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtOffAddr2" size="30" name="txtOffAddr2" value="${DummyOfficeVO.offAddr2}"/></td>
			<td width="15%" align="left"><fmt:message key="CMN.TALUKA" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
				<select name="cmbTaluka" id="cmbTaluka" style="width: 65%" disabled="disabled">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="Taluka" items="">
					<c:choose>
						<c:when test="${DummyOfficeVO.taluka==Taluka.lookupDesc}">
							<option value="${Taluka.lookupDesc}"><c:out
							value="${Taluka.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${Taluka.lookupDesc}"><c:out
							value="${Taluka.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.VILLAGE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtVillage" size="30" name="txtVillage" value="${DummyOfficeVO.village}" /></td>
			<td width="15%" align="left"><fmt:message key="CMN.TOWN" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
				<select name="cmbTown" id="cmbTown" style="width: 65%" disabled="disabled">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="Town" items="">
						<option value="${Town.lookupDesc}"><c:out
							value="${Town.lookupDesc}"></c:out></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
		 	<td width="15%" align="left"><fmt:message key="CMN.PIN" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name="txtOffPin" id="txtOffPin" value="${DummyOfficeVO.pinCode}" size="30" /></td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.TELNO(1)" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtTelno1" size="30" name="txtTelno1" value="${DummyOfficeVO.telNo1}" /></td>
			<td width="15%" align="left"><fmt:message key="CMN.TELNO(2)" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtTelno2" size="30" name="txtTelno2" value="${DummyOfficeVO.telNo2}" /></td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.FAX" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtFax" size="30" name="txtFax"  value="${DummyOfficeVO.faxNo}" /></td>
			<td width="15%" align="left"><fmt:message key="CMN.EMAILFOROFFICIALCMTN" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtEmail" size="30" name="txtEmail" value="${DummyOfficeVO.emailAddr}"/></td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.STATUS" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
			<c:choose>
				<c:when test="${DummyOfficeVO == null}">
							<input type="radio" id="radioStatus" name="radioStatus" value="Y" checked="checked" />
							<fmt:message key="CMN.ACTIVE"	bundle="${dcpsLables}"></fmt:message>
							<input type="radio" id="radioStatus" name="radioStatus" value="N" />
						 	<fmt:message key="CMN.INACTIVE"	bundle="${dcpsLables}"></fmt:message>	
				</c:when>
				<c:otherwise>
						<c:if test="${DummyOfficeVO.statusFlag == 'Y'}">
							<input type="radio" id="radioStatus" name="radioStatus" value="Y" checked="checked" />
							<fmt:message key="CMN.ACTIVE"	bundle="${dcpsLables}"></fmt:message>
							<input type="radio" id="radioStatus" name="radioStatus" value="N" />
						 	<fmt:message key="CMN.INACTIVE"	bundle="${dcpsLables}"></fmt:message>	
						</c:if>
						<c:if test="${DummyOfficeVO.statusFlag == 'N'}">
							<input type="radio" id="radioStatus" name="radioStatus" value="Y" />
							<fmt:message key="CMN.ACTIVE"	bundle="${dcpsLables}"></fmt:message>
							<input type="radio" id="radioStatus" name="radioStatus" value="N"  checked="checked"/>
						 	<fmt:message key="CMN.INACTIVE"	bundle="${dcpsLables}"></fmt:message>	
						</c:if>
				</c:otherwise>
			</c:choose>			
			</td>	
		</tr>
		</table>
	</fieldset>
	<div>&nbsp;</div>
	<div>&nbsp;</div>
	<div align="center" id="saveDummyOffice">
	<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK"  bundle="${dcpsLables}" onclick="goBack();" />
	<c:choose>
		<c:when test="${resValue.lObjMstDummyOffice == null}">
			<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE"  bundle="${dcpsLables}" onclick="saveDummyOfficeInfo(1);" />
		</c:when>
		<c:otherwise>
			<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE"  bundle="${dcpsLables}" onclick="saveDummyOfficeInfo(2);" />
		</c:otherwise>
	</c:choose>
	</div>
	
</hdiits:form>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/ChangeParentDept.js"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request" />

<hdiits:form name="changeParentDept" encType="multipart/form-data" validate="true" method="post">

<input type="hidden" id="hidSelectedTreasuryCode" value="${resValue.treasuryCode}"/>
<input type="hidden" id="hidSelectedDDOCode" value="${resValue.ddoCode}"/>


<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.SELECTOPTION" bundle="${dcpsLabels}"></fmt:message></b></legend>

	<table align="center" width="40%" >
		<tr>
			<td><fmt:message key="CMN.TREASURY"
				bundle="${dcpsLabels}"></fmt:message></td>
			<td><select name="cmbTreasuryCode" id="cmbTreasuryCode" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					         				<c:forEach var="Treasury" items="${resValue.TreasuryList}" >
					         					<c:choose>
					         						<c:when test="${resValue.treasuryCode==Treasury.id}">
					         							<option value="${Treasury.id}" selected="selected"><c:out value="${Treasury.desc}"></c:out></option>
					         						</c:when>
						         					<c:otherwise>
						         						<option value="${Treasury.id}"><c:out value="${Treasury.desc}"></c:out></option>
						         					</c:otherwise>
					         					</c:choose>
					         				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><fmt:message key="CMN.DDONAME"
				bundle="${dcpsLabels}"></fmt:message></td>
			<td><select name="cmbDdoCode" id="cmbDdoCode" STYLE="width: 100%" onchange="loadBelowDetailsForChangeParent();" >
				<c:if test="${resValue.DdoList==null}">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				</c:if>
					         				<c:forEach var="DdoCodes" items="${resValue.DdoList}" >
					         					<c:choose>
					         						<c:when test="${resValue.ddoCode==DdoCodes.id}">
					         							<option value="${DdoCodes.id}" selected="selected"><c:out value="${DdoCodes.desc}"></c:out></option>
					         						</c:when>
					         						<c:otherwise>
														<option value="${DdoCodes.id}"><c:out value="${DdoCodes.desc}"></c:out></option>				         						
					         						</c:otherwise>
					         					</c:choose>
					         				</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr><td></td></tr>
	</table>
	
	<table align="center" width="70%">
		<tr>
			<td align="right" ><fmt:message key="CMN.FORDDO" bundle="${dcpsLabels}"></fmt:message></td>
			<td align="left" >
				<c:choose>
					<c:when test="${resValue.DDOChecked == 'Y'}">
						<input type="radio"	id="radioChangePFDOptionDDO" name="radioChangePFDOption" value="DDO" onclick="loadBelowDetailsForChangeParent();" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input type="radio"	id="radioChangePFDOptionDDO" name="radioChangePFDOption" value="DDO" onclick="loadBelowDetailsForChangeParent();" />
					</c:otherwise>
				</c:choose>
			</td>
			
			<td align="right"><fmt:message key="CMN.FOREMPLOYEE" bundle="${dcpsLabels}"></fmt:message></td>
			<td align="left">
				<c:choose>
					<c:when test="${resValue.EMPChecked == 'Y'}">
						<input type="radio"	id="radioChangePFDOptionEMP" name="radioChangePFDOption" value="EMP" onclick="loadBelowDetailsForChangeParent();" checked="checked" />
					</c:when>
					<c:otherwise>
						<input type="radio"	id="radioChangePFDOptionEMP" name="radioChangePFDOption" value="EMP" onclick="loadBelowDetailsForChangeParent();" />		
					</c:otherwise>
				</c:choose>
			</td>
			
			<td align="right"><fmt:message key="CMN.CHANGEDDOCODE" bundle="${dcpsLabels}"></fmt:message></td>
			<td align="left">
				<c:choose>
					<c:when test="${resValue.DDOCODEChecked == 'Y'}">
						<input type="radio"	id="radioChangePFDOptionDDOCODE" name="radioChangePFDOption" value="DDOCODE" onclick="loadBelowDetailsForChangeParent();" checked="checked"  />
					</c:when>
					<c:otherwise>
						<input type="radio"	id="radioChangePFDOptionDDOCODE" name="radioChangePFDOption" value="DDOCODE" onclick="loadBelowDetailsForChangeParent();" />			
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>

</fieldset>

<c:choose>
	<c:when test="${resValue.DDOChecked == 'Y'}">
		<jsp:include page="/WEB-INF/jsp/dcps/ChangeParentDeptDDO.jsp" />
	</c:when>
	<c:when test="${resValue.EMPChecked == 'Y'}">
		<jsp:include page="/WEB-INF/jsp/dcps/ChangeParentDeptEmpList.jsp" />
	</c:when>
	<c:when test="${resValue.DDOCODEChecked == 'Y'}">
		<jsp:include page="/WEB-INF/jsp/dcps/ChangeParentDeptDDOCode.jsp" />
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>

</hdiits:form>

<ajax:select source="cmbTreasuryCode" target="cmbDdoCode"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOsForTreasury"
	parameters="cmbTreasuryCode={cmbTreasuryCode}"
	postFunction="clearBelowDetails"
	>
</ajax:select>
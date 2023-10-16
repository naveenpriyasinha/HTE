<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>

<%--added by vaibhav tyagi: start --%>
<script type="text/javascript">

function filterByDDOCode(){
	var ddoCode= document.getElementById("cmbAsstDDO").value;
	var url;
		url="ifms.htm?actionFlag=viewFormsForwardedByAsst&User=ZPDDO&ddoCode="+ddoCode;
		document.DCPSForwardedFormsList.action= url;
		document.DCPSForwardedFormsList.submit();
}

</script>
<%--added by vaibhav tyagi: end --%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="empList" value="${resValue.empList}"></c:set>
<%--added by vaibhav tyagi: start --%>
<c:set var="asstDDO" value="${resValue.asstDDO}"></c:set>
<c:set var="ddoSelected" value="${resValue.ddoSelected}"></c:set>
<%--added by vaibhav tyagi: end --%>

<hdiits:form name="DCPSForwardedFormsList" id="DCPSForwardedFormsList" encType="multipart/form-data"
	validate="true" method="post">
	<input type="hidden" id="User" name="User" value="${resValue.User}"/>
	<input type="hidden" id="Use" name="Use" value="${resValue.Use}"/>
<div align="center">

<%--added by vaibhav tyagi: start --%>
<fieldset class="tabstyle"><legend> <b>Filter By DDO</b> </legend>
<table>
<tr>
<td><c:out value="DDO Code"></c:out></td>
<td><select name="cmbAsstDDO"
			id="cmbAsstDDO" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="asstDDOList" items="${resValue.asstDDO}">
			<c:choose> 				
			
				
					<c:when test="${ddoSelected == asstDDOList[0]}">
						<option value="${asstDDOList[0]}" selected="selected" title="${asstDDOList[1]}(${asstDDOList[0]})"><c:out
							value="${asstDDOList[1]} (${asstDDOList[0]})"></c:out></option>
					</c:when>
					<c:otherwise>
						<option title="${asstDDOList[1]}(${asstDDOList[0]})" value="${asstDDOList[0]}"><c:out value="${asstDDOList[1]}(${asstDDOList[0]})"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> </td>
<td><input id="btnFilter" class="buttontag" type="button" align="center" size="5" maxlength="5"
		value="Filter" onclick="filterByDDOCode();"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>
<%--added by vaibhav tyagi: end --%>

<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.AllEmployeeDetails" bundle="${dcpsLables}"></fmt:message></b> </legend>
	<display:table list="${empList}" id="vo" requestURI="" export="" style="width:50%"
		pagesize="5">

		<display:setProperty name="paging.banner.placement" value="bottom" />

		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[2]}"
			var="birthDate" />

		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:left" sortable="true" titleKey="CMN.EMPLOYEENAME">
			<a href=#
				onclick="popUpDcpsEmpData('${vo[0]}','${resValue.EditForm}','${vo[3]}');"><c:out
				value="${vo[1]}" /></a>
		</display:column>

		<display:column headerClass="datatableheader"
			style="text-align:left" class="oddcentre" sortable="true"
			titleKey="CMN.DOB">
			<c:out value="${birthDate}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"
					style="text-align:left" sortable="true" titleKey="CMN.GENDER">
					<c:out value="${vo[6]}"></c:out>
		</display:column>

		<display:column headerClass="datatableheader" class="oddcentre"
					style="text-align:left" sortable="true"
					titleKey="CMN.DESIGNATION">
					<c:out value="${vo[5]}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"
					style="text-align:left" sortable="true"
					titleKey="CMN.DDOCODE">
					<c:out value="${vo[7]}"></c:out>
		</display:column>
			
	</display:table>
</fieldset>
</div>
</hdiits:form>
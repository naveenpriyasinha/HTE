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

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.ALLEMPSUNDERDDO" bundle="${dcpsLables}"></fmt:message></b></legend>
</br>
<div style="width:100%;overflow:auto;height:300px;" >
<display:table list="${resValue.empList}" size="5"  id="vo" pagesize="5" cellpadding="5" style="width:40%" requestURI="" >

	<display:setProperty name="paging.banner.placement" value="bottom" />	
	<display:column titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader" sortable="true" style="text-align: left;">
		<a href=# onclick="displayPFDForEmp(${vo[0]});"><c:out value="${vo[2]}"></c:out></a>
	</display:column>
	
	<display:column style="text-align: left;" class="tablecelltext" titleKey="CMN.DCPSACNO" headerClass="datatableheader" sortable="true" >
		<c:out value="${vo[1]}"></c:out>
	</display:column>
</display:table>
</div>
</fieldset>



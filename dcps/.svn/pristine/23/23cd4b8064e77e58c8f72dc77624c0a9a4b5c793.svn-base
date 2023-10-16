
<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/quarter/Quarteraddress.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>

<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables"
	var="QtrLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="xmlList" value="${resValue.xmlList}"></c:set>
<c:set var="QtrMstList" value="${resValue.QtrMstList}"></c:set>
<script type="text/javascript">
 	function getQuarterDtls(form)
	{
    	document.getElementById('quarterArrayHdn').value=form.value;
    	document.getElementById('radioId').value=form.id;
	}
 	


</script>

<hdiits:form name="quartrAlloc" validate="true" action="" method="">

	<c:set var="i" value="0" />
	<display:table list="${resValue.QtrMstList}" id="row" requestURI=""
		pagesize="10" export="false" style="width:95%" offset="1">

		<display:setProperty name="paging.banner.placement" value="bottom" />

		<display:column class="tablecelltext" title=""
			headerClass="datatableheader" style="text-align: center">
			<hdiits:radio id="check${i}" name="check"
				value="${row.quarterId}~${row.hrQuaterTypeMst.quaType}~${row.quarterName}"
				onclick="getQuarterDtls(this)" />
		</display:column>

		<display:column class="tablecelltext" titleKey="HRMS.TypeofQua"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">${row.hrQuaterTypeMst.quaType}</display:column>

		<display:column class="tablecelltext" titleKey="HRMS.QtrName"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">${row.quarterName}</display:column>


		<c:set var="i" value="${i+1}" />

		<display:footer media="html"></display:footer>


	</display:table>
	<input name="totalRow" id="totalRow" type=hidden />
	<script>
 
document.getElementById('totalRow').value='${i}';
 
</script>

</hdiits:form>
<hdiits:hidden name="quarterArrayHdn" id="quarterArrayHdn" default="" />
<hdiits:hidden name="radioId" id="radioId" />
<hdiits:hidden name="totalRow" id="totalRow" />

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

<% 
try
{
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="xmlList" value="${resValue.xmlList}"></c:set>
<c:set var="EmpVOLst" value="${resValue.EmpVOLst}"></c:set>
<c:set var="Employee" value="${resValue.Employee}"></c:set>

<script type="text/javascript">
	function getUserDtls(form)
	{
    	 document.getElementById('userArrayHdn').value=form.value;
    	 document.getElementById('userRadioId').value=form.id;
	}
</script>
<body style="overflow-x:hidden;">
<hdiits:form name="quartrAlloc" validate="true" action="" method="">

	<c:set var="i" value="0" />
	<display:table list="${resValue.Employee}" id="row" requestURI="" pagesize="10"  export="false" style="width:95%" offset="1" >
				<c:out value="${row.empId}"></c:out>
				
		<display:setProperty name="paging.banner.placement" value="bottom"/>
	
		<display:column class="tablecelltext" title="" headerClass="datatableheader" style="text-align: center" >
			<hdiits:radio id="check${row.userId}" name="check" value="${row.empfName} ${row.empmName} ${row.emplName}~${row.userId}~${row.dsgnName}"  onclick="getUserDtls(this);" />
		</display:column>
				
		<display:column class="tablecelltext" titleKey="name" headerClass="datatableheader" style="text-align: center" sortable="true" >${row.empfName} ${row.empmName} ${row.emplName}</display:column>
				
		<display:column class="tablecelltext" titleKey="designation" headerClass="datatableheader" style="text-align: center" sortable="true">${row.dsgnName}</display:column>
				
		<display:column class="tablecelltext" titleKey="post" headerClass="datatableheader" style="text-align: center" sortable="true">${row.postName}</display:column>
	
		<display:column class="tablecelltext" titleKey="department" headerClass="datatableheader" style="text-align: center" sortable="true">${row.depName}</display:column>
				
	<c:set var="i" value="${i+1}" />
				
	<display:footer media="html"></display:footer>		
				
	</display:table>

  
<hdiits:hidden name="userArrayHdn" id="userArrayHdn" default=""/>
<hdiits:hidden name="userArrayHdn" id="userRadioId" default=""/>
<hdiits:hidden name="rowId" id="rowId"/>

</hdiits:form>
</body>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationForm.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="empList" value="${resValue.empList}"></c:set>

<script>
function printEmpDetailReportHyperLinked(EmpId)
{
	url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
	window_new_update(url);
}
function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
</script>

<hdiits:form name="DCPSApprovedFormsList" id="DCPSApprovedFormsList" encType="multipart/form-data"
	validate="true" method="post">
<div align="center">

<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.APPROVEDFORMS" bundle="${dcpsLables}"></fmt:message></b> </legend>
	<display:table list="${empList}" id="vo" requestURI="" export="" style="width:100%"
		pagesize="15">

		<display:setProperty name="paging.banner.placement" value="bottom" />

		<display:column headerClass="datatableheader" 
			style="text-align:left;" sortable="true" titleKey="CMN.EMPLOYEENAME">
			<a href=#
				onclick="printEmpDetailReportHyperLinked('${vo[0]}');"><c:out
				value="${vo[1]}" /></a>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" sortable="true"  titleKey="CMN.DCPSID" >		
				<c:out value="${vo[2]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" sortable="true"  titleKey="CMN.SEVARTHID" >		
				<c:out value="${vo[7]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" sortable="true"  titleKey="CMN.EMPOFFICE" >		
				<c:out value="${vo[4]}"></c:out> 
		</display:column>

		<display:column headerClass="datatableheader" 
					style="text-align:left" sortable="true"
					titleKey="CMN.DESIGNATION">
					<c:out value="${vo[3]}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" 
					style="text-align:left" sortable="true" titleKey="CMN.GENDER">
					<c:out value="${vo[5]}"></c:out>
		</display:column>
		
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[6]}"
			var="birthDate" />
		<display:column headerClass="datatableheader"
			style="text-align:left" sortable="true"
			titleKey="CMN.DOB">
			<c:out value="${birthDate}"></c:out>
		</display:column>
			
	</display:table>
</fieldset>
<br/>
<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>
</div>

</hdiits:form>
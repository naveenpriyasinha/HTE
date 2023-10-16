<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript">
function GoBack()
{
	self.location.href = 'ifms.htm?actionFlag=validateLogin';
}
function printEmpDetailReport(EmpId)
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

<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.FORMSTATUSOFALLEMPLOYEES" bundle="${dcpsLables}"></fmt:message></b> </legend>
<div align="center">
<display:table list="${resValue.AllFormsList}" size="10"  id="vo" pagesize="15" cellpadding="5" style="width:100%" requestURI="" >

						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true" value="${vo_rowNum}" ></display:column>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader"  sortable="true" ><a href = # onclick="printEmpDetailReport('${vo[1]}');"><c:out value="${vo[2]}"/></a></display:column>
						
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.EMPDESIG" headerClass="datatableheader" value="${vo[6]}" sortable="true" ></display:column>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.EMPOFFICE" headerClass="datatableheader" value="${vo[7]}" sortable="true" ></display:column>
						
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.GENDER" headerClass="datatableheader" value="${vo[8]}" sortable="true" ></display:column>
						
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[9]}" var="birthDate"/>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.DOB" headerClass="datatableheader" value="${birthDate}" sortable="true" ></display:column>
						
						<c:if test="${vo[4] == -1}">
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.STATUS" headerClass="datatableheader"  sortable="true" value="Rejected"></display:column>
						</c:if>
						
						<c:if test="${vo[4] == 0 && vo[3] == 0 && vo[5] == 1}">
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.STATUS" headerClass="datatableheader"  sortable="true" value="Pending with Treasury Assistant"></display:column>
						</c:if>
						
						<c:if test="${vo[4] == 0 && vo[3] == 1 && vo[5] == 1}">
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.STATUS" headerClass="datatableheader"  sortable="true" value="Pending with Treasury Officer"></display:column>
						</c:if>
						
						<c:if test="${vo[4] == 0 && vo[3] == null && vo[5] == 0}">
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.STATUS" headerClass="datatableheader"  sortable="true" value="Draft(with DDO Assistant)"></display:column>
						</c:if>
						
						<c:if test="${vo[4] == 0 && vo[3] == null && vo[5] == 1}">
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.STATUS" headerClass="datatableheader"  sortable="true" value="Pending with DDO"></display:column>
						</c:if>
</display:table>
</div>
<br/>
</fieldset>

<br/>
<div align="center">
<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="GoBack();"/>
</div>


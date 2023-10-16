<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<script type="text/javascript" src="script/common/common.js"></script>

<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="FrmDataEntryVerify" id="FrmDataEntryVerify" encType="multipart/form-data" validate="true" method="post" >

<div style="float: inherit; border:0px; background-color: transparent;width:50%; height:400px;  ">
<c:if test="${resValue.UserType == 'HOD'}">	
	<fieldset class="tabstyle"><legend><fmt:message key="CMN.REQFORAPPRVAL" bundle="${lnaLabels}"></fmt:message></legend>	
</c:if>
<c:if test="${resValue.UserType == 'HODASST'}">	
	<fieldset class="tabstyle"><legend><fmt:message key="CMN.DRAFTREQST" bundle="${lnaLabels}"></fmt:message></legend>	
</c:if>
	    <display:table list="${resValue.DraftReq}"  id="vo" requestURI="" export="" style="width:100%"  pagesize="10">	
	
			<display:setProperty name="paging.banner.placement" value="bottom" />
			
			<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" titleKey="CMN.EMPNAME" >
				<c:if test="${resValue.UserType == 'HOD'}">			
					<a href = "ifms.htm?actionFlag=loadLNADataEntryCase&sevaarthId=${vo.sevaarthId}&compAdvance=${vo.compAdvance}&houseAdvance=${vo.houseAdvance}&motorAdvance=${vo.motorAdvance}&pkValue=${vo.empLoanDtlsId}&userType=HOD&elementId=800042"><c:out value="${vo.empName}" /></a></c:if>
				<c:if test="${resValue.UserType == 'HODASST'}">			
					<a href = "ifms.htm?actionFlag=loadLNADataEntryCase&sevaarthId=${vo.sevaarthId}&compAdvance=${vo.compAdvance}&houseAdvance=${vo.houseAdvance}&motorAdvance=${vo.motorAdvance}&pkValue=${vo.empLoanDtlsId}&userType=HODASST&elementId=800042"><c:out value="${vo.empName}" /></a></c:if>	
			</display:column>
			<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" titleKey="CMN.EMPLOYEECODE" >		
					<c:out value="${vo.sevaarthId}" />
			</display:column>
		</display:table>
		<center>
			<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.CLOSE" bundle="${lnaLabels}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';"/>
		</center>	
	</fieldset>	
</div>			

</hdiits:form>
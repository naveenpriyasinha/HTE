<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="FrmDataEntryVerify" id="FrmDataEntryVerify" encType="multipart/form-data" validate="true" method="post" >

<div style="float: inherit; border:0px; background-color: transparent;width:50%; height:400px;  ">
<c:if test="${resValue.userType == 'DDO'}">	
<fieldset class="tabstyle"><legend><fmt:message key="CMN.REQFORAPPRVAL" bundle="${gpfLabels}"></fmt:message></legend>	
</c:if>
<c:if test="${resValue.userType == 'DEO'}">	
<fieldset class="tabstyle"><legend><fmt:message key="CMN.DRAFTREQST" bundle="${gpfLabels}"></fmt:message></legend>	
</c:if>
    <display:table list="${resValue.EmpList}"  id="vo" requestURI="" export="" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" titleKey="CMN.EMPNAME" >
			<c:if test="${resValue.userType == 'DDO'}">			
				<a href = "ifms.htm?actionFlag=loadDataEntryCase&sevaarthId=${vo[1]}&name=${vo[0]}&montlySub=${vo[3]}&openingBalc=${vo[2]}&deoRemark=${vo[4]}&userType=DDO&trnAccPk=${vo[5]}"><c:out value="${vo[0]}" /></a></c:if>
			<c:if test="${resValue.userType == 'DEO'}">			
				<a href = "ifms.htm?actionFlag=loadDataEntryCase&sevaarthId=${vo[1]}&name=${vo[0]}&montlySub=${vo[3]}&openingBalc=${vo[2]}&deoRemark=${vo[4]}&userType=DEO"><c:out value="${vo[0]}" /></a></c:if>	
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" titleKey="CMN.EMPLOYEECODE" >		
				<c:out value="${vo[1]}" />
		</display:column>
	</display:table>
	<center>
		<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${gpfLabels}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';"/>
	</center>	
</fieldset>	
</div>			

</hdiits:form>
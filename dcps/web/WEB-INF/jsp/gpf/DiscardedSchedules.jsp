<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<script>
function ReturnLoginPage()
{
	self.location.href = "ifms.htm?actionFlag=validateLogin";
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="DiscardedSchedules" value="${resValue.lLstDiscardedSchedule}"/>
<fieldset class="tabstyle"><legend><fmt:message key="CMN.DISCRDSCH" bundle="${gpfLabels}"></fmt:message></legend>
<display:table list="${DiscardedSchedules}" pagesize="10"  id="vo" requestURI=""cellpadding="4" style="width:100%" >
	<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.GROUP" headerClass="datatableheader" value="${vo[0]}">
		</display:column>
		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.BILLGRP" headerClass="datatableheader" value="${vo[4]}">
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.MONTH" headerClass="datatableheader" value="${vo[1]}"> 
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.YEAR" headerClass="datatableheader" value="${vo[2]}"> 
		</display:column>

		<display:column style="text-align: left;width:120px" class="oddcentre" titleKey="CMN.TOTALSUB" headerClass="datatableheader" value="${vo[3]}"> 
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.STATUS" headerClass="datatableheader" value="Discarded">
		</display:column>				
</display:table>
</fieldset>
<br>
<center><hdiits:button type="button" captionid="BTN.OK" bundle="${gpfLabels}" id="btnOk" name="btnOk" onclick="ReturnLoginPage();"></hdiits:button></center>


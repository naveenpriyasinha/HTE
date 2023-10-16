<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="AdvanceDetail" value="${resValue.AdvanceDetail}"/>
<fieldset ><legend><fmt:message key="CMN.ADVANCEHISTORY" bundle="${gpfLabels}"></fmt:message></legend>
<display:table list="${AdvanceDetail}" size="${totalRecords}" pagesize="10"  id="vo" requestURI=""cellpadding="4" style="width:100%" >
	<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.TIDNO" headerClass="datatableheader" value="${vo[0]}"></display:column>
		<fmt:formatDate value="${vo[1]}" var="transDate" pattern="dd/MM/yyyy"/>	
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.TRNSDATE" headerClass="datatableheader" value="${transDate}"></display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.REQUESTAMOUNT" headerClass="datatableheader" value="${vo[2]}"></display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.SANCAMT" headerClass="datatableheader" value="${vo[3]}"></display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.TOTALSANCINSTALL" headerClass="datatableheader" value="${vo[4]}"></display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.INSTALLAMTPN" headerClass="datatableheader" value="${vo[5]}"></display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.OUTSTANDINGINSTALLAMT" headerClass="datatableheader" value="${vo[6]}"></display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.SANCAUTHORITYNAME" headerClass="datatableheader" value="${vo[7]}"></display:column>
		<fmt:formatDate value="${vo[8]}" var="sancDate" pattern="dd/MM/yyyy"/>	
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.SANCDATE" headerClass="datatableheader" value="${sancDate}"></display:column>		
		
</display:table>
</fieldset>
<center><hdiits:button type="button" captionid="BTN.OK" bundle="${gpfLabels}" id="btnOk" name="btnOk" onclick="window.close();"></hdiits:button></center>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="RejectedList" value="${resValue.lLstRejectedReq}"></c:set>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />

<hdiits:form name="frmGPFRejectedReq" encType="multipart/form-data"  validate="true" method="post">
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.REJECTEDREQ" bundle="${gpfLables}" ></fmt:message></legend>
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; height:300px;  ">
	
    <display:table list="${RejectedList}"  id="vo" requestURI="" export="" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" titleKey="CMN.SUBSCRIBER" >		
				<c:out value="${vo[0]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.TID" >		
				<c:out value="${vo[1]}" />
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.REQTYPE" >		
				<c:if test="${vo[5] == 'CS'}"><c:out value="Change Subscription"></c:out></c:if>
				<c:if test="${vo[5] == 'RA'}"><c:out value="Refundable Advance"></c:out></c:if>
			    <c:if test="${vo[5] == 'NRA'}"><c:out value="Non-Refundable Advance"></c:out></c:if> 		
			    <c:if test="${vo[5] == 'FW'}"><c:out value="Final Withdrawal"></c:out></c:if> 	
		</display:column>		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.SEVAARTHID" >		
				<c:out value="${vo[2]}"></c:out>
		</display:column>

		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.GPFACCNO" >    			
    			<c:out value="${vo[3]}"></c:out> 
		</display:column>
      	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="appDate"/>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.APPDATE"  >		
				<c:out value="${appDate}"></c:out> 
		</display:column>	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.VERIFIERREMARKS"  >		
				<c:out value="${vo[7]}"></c:out> 
		</display:column>	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.HOREMARKS"  >		
				<c:out value="${vo[8]}"></c:out> 
		</display:column>
			
	</display:table>
	</div>
</fieldset>
<br>
</hdiits:form>
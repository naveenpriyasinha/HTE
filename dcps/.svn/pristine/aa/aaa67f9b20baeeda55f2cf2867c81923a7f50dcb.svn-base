<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EmpLoanStatus" value="${resValue.EmpLoanStatus}" />
<script type="text/javascript" src="script/common/common.js"></script>
<hdiits:form name="FrmLoanStatus" id="FrmLoanStatus" encType="multipart/form-data" validate="true" method="post" >

<fieldset class="tabstyle">
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; height:400px;  ">

    <display:table list="${EmpLoanStatus}"  id="vo" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.SUBSCRIBER" >		
				<c:out value="${vo[0]}" />
		</display:column>
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[1]}" var="appDate"/>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.APPDATE" >
				<c:out value="${appDate}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.SEVAARTHID" >		
				<c:out value="${vo[2]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.REQTYPE" >		
				<c:if test="${vo[3] == '800028'}"><c:out value="Computer Advance"></c:out></c:if>
				<c:if test="${vo[3] == '800029'}"><c:out value="House Building Advance"></c:out></c:if>
				<c:if test="${vo[3] == '800030'}"><c:out value="Vehicle Advance"></c:out></c:if> 		
 		</display:column>
 		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.SUBTYPE" >		
				<c:out value="${vo[4]}"></c:out>				 		
 		</display:column>
 		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.STATUS" >		
				<c:if test="${vo[5] == 'D'}"><c:out value="Pending with Assistant HOD"></c:out></c:if>
				<c:if test="${vo[5] == 'F'}"><c:out value="Pending with HOD"></c:out></c:if>
				<c:if test="${vo[5] == 'A'}"><c:out value="Approved"></c:out></c:if> 						 		
				<c:if test="${vo[5] == 'R'}"><c:out value="Rejected"></c:out></c:if>
 		</display:column>
 	</display:table>
	</div>
</fieldset>
</hdiits:form>	
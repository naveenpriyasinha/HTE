<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<script type="text/javascript">
function AddNewRowForTier(Emp_id , desigName)
{
	var url = "ifms.htm?actionFlag=loadDCPSTierI&TierDraft="+1+"&Designation="+desigName+"&Emp_Id="+Emp_id;
	self.location.href = url;
}
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseList" value="${resValue.CaseList}" />
<c:set var="UserList" value="${resValue.UserList}"/>
<br></br>
<hdiits:form name="DDOTierFormList" id="DDOTierFormList" encType="multipart/form-data" validate="true" method="post" >

<fieldset class="tabstyle">
<legend id="headingMsg" align="top">
		<b style="text-align: center"> Details Of Tier Contribution Forms</b>
	</legend> 

	<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; height:400px;  ">
	
	<c:if test="${(resValue.Criteria == 1)}">
	
	 <display:table list="${caseList}"  id="vo"   requestURI="" export="" style="width:100%"  pagesize="5">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri" id="chkbxFormVeri" value="${vo[0]}"/>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.EMPSRNO" >		
			<c:out value="${vo[0]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.SAVEDON" >
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[1]}" var="FormSavedOn"/>		
			<input type="text" name="txtFormSavedOn" id="txtFormSavedOn" value="${FormSavedOn}" readonly="readonly"/>		
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.DESIGNATION" >		
			<a href=# onclick="AddNewRowForTier('${vo[0]}','${vo[2]}');"><c:out value="${vo[2]}"></c:out></a>
		</display:column>
	
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.TIER"  >	
			<c:out value="${vo[3]}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.FROMDATECNTRBTN" >
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="CntrbtnFromDate"/>		
			<input type="text" name="txtCntrbtnFromDate" id="txtCntrbtnFromDate" value="${CntrbtnFromDate}" readonly="readonly" />
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.TODATECNTRBTN" >
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[5]}" var="CntrbtnToDate"/>		
			<input type="text" name="txtCntrbtnToDate" id="txtCntrbtnToDate" value="${CntrbtnToDate}" readonly="readonly" />		
		</display:column>
	</display:table>
	
	</c:if>
	
	
	
	
	
	</div>
</fieldset>
</hdiits:form>	
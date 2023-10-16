<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels"
	scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts"
	scope="request" />

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js">
	
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"
	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dcps/srkaMasters.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/dcpsDDO.js"></script>
<style>
input[type="button" i]:disabled {
    pointer-events: none;
    opacity: 0.6;
}
</style>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.lStrInstitutionName}"></c:set>


<hdiits:form name="frmUpdateInstitutionName" id="DDOEmpSelect"
	encType="multipart/form-data" validate="true" method="post">
	<input type="hidden" name="ddoCode" id="ddoCode" value="${DDOCODE}">
	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message
		key="CMN.UPDATEINSTITUIONNAME" bundle="${dcpsLabels}"></fmt:message></b>
	</legend>
	<table>
		
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.DDOCODE"
				bundle="${dcpsLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="ddoCodeForSearch" size="30" 
				name="ddoCodeForSearch" />
		   </td>
		</tr>
		<tr>
			<td colspan="2" align="center"><label style="color: red"><fmt:message
				key="MSG.SEARCHFORINSTITUTION" bundle="${dcpsLabels}" /></label></td>
		</tr>
	</table>
	<div style="width: 50; text-align: center; align: center"><hdiits:button
		name="btnSearch" style="align:center" type="button"
		captionid="CMN.SEARCH" bundle="${dcpsLabels}"
		onclick="showAllData();" /> 
	</fieldset>
	<br></br>

	<c:if test="${VOList != null}">

		<fieldset style="width: 100%" class="tabstyle"><legend
			id="headingMsg"> <b><fmt:message key="CMN.UPDATENAME"
			bundle="${dcpsLabels}"></fmt:message></b> </legend> <c:set
		var="hdnCounter" value="0" />

		<div class="scrollablediv"
			style="width: 100%; overflow: auto; height: 200px;"><display:table
			list="${VOList}" id="vo" cellpadding="5" style="width:100%"
			requestURI="">

			<display:column titleKey="CMN.DDOCODE"
				headerClass="datatableheader" sortable="true"
				style="width:20%;text-align: left;">
				<c:out value="${vo[0]}"></c:out>			
							<input type="hidden" id="orgDdoCode"
				name="orgDdoCode" value="${vo[0]}">
					<c:set var="hdnCounter" value="${hdnCounter+1}" />
			</display:column>

			<display:column headerClass="datatableheader"
				style="text-align:left;width: 40%" class="oddcentre" sortable="true"
				titleKey="CMN.ORGINSNAME">
				<c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="hdnOrgName" name="hdnOrgName" value="${vo[1]}" />
			</display:column>
			
			<display:column headerClass="datatableheader"
				style="text-align:left;width: 40%" class="oddcentre" sortable="true"
				titleKey="CMN.ORGINSUPDATIONNAME">
				<input type="text" id="OrgUpdatedName" name="hdnOrgUpdatedName" size="70"/>
			</display:column>
			

			<display:setProperty name="paging.banner.placement" value="bottom" />

		</display:table></div>

		</fieldset>
		<input type="hidden" id="hdnCounter" name="hdnCounter"
		value="${hdnCounter}" />
		

		<br>

		<div style="width: 50; text-align: center; align: center"><hdiits:button
			name="btnSelect" type="button" classcss="bigbutton"
			id="btnDDOEmpSelect" captionid="BTN.UPDATE"
			bundle="${dcpsLabels}" onclick="updateOrgInstitutionName();" /></div>

	</c:if>

	<input type="hidden" name="hidSearchFromDDOSelection"
		id="hidSearchFromDDOSelection" value="searchFromDDOSelection" />

</hdiits:form>




<script>
	

	function showAllData() {
		//alert("hiii");
		var ddoCode=document.getElementById("ddoCodeForSearch").value;
		//alert("ddoCode ="+ddoCode);
		var url = "ifms.htm?actionFlag=getInstitutionName&DDOCodeForname="+ddoCode;
		//alert("url = "+url);
		showProgressbar('Please Wait<br>Your request is in progress...');
		document.frmUpdateInstitutionName.action = url;
		enableAjaxSubmit(true);
		document.frmUpdateInstitutionName.submit();
	}

	

	
	function updateOrgInstitutionName(){
		//alert("HIii");
		var orgDdoCode="";
		var orgInstName="";
	    orgDdoCode = document.getElementById("orgDdoCode").value ;
	    orgInstName = document.getElementById("OrgUpdatedName").value ;
	    
	    if(orgInstName==""){
	    	alert("Please enter organization Name..");
	    }else{
		//alert("orgDdoCode :"+orgDdoCode+"orgInstName :"+orgInstName);
		var url;
		url="./hrms.htm?actionFlag=updateInstitutionName&orgDdoCode="+orgDdoCode+"&orgInstName="+orgInstName;
		document.frmUpdateInstitutionName.action= url;
		document.frmUpdateInstitutionName.submit();
	}
	}
	
</script>
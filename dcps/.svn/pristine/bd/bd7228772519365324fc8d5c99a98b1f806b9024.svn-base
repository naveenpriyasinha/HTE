<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script type="text/javascript">
function searchReq()
{
	var name = document.getElementById("txtEmpName").value;
	var date = document.getElementById("txtDate").value;
	var userType = document.getElementById("hidUserType").value;
	
	var criteria;
	var url;
	
	if (document.getElementById("txtEmpName").value =="" && document.getElementById("txtDate").value =="")
	{
		alert("Please Select Name or Enter Date");
		return false;
	}
	else
	{
		if (document.getElementById("txtDate").value =="")
		{
			criteria = "name";
			url = "&name="+name+"&criteria="+criteria;
		}
		else if (document.getElementById("txtEmpName").value =="")
		{
			criteria = "date";
			url = "&date="+date+"&criteria="+criteria;
		}
		else
		{
			criteria = "both";
			url = "&name="+name+"&date="+date+"&criteria="+criteria;			
		}
	}

	url = url+"&userType="+userType;
	var uri = 'ifms.htm?actionFlag=loadGPFRequestList'+url;
	document.FrmGPFWorklist.action = uri ;
	enableAjaxSubmit(true);
	document.FrmGPFWorklist.submit();
}

function getAllRecords()
{
	var userType = document.getElementById("hidUserType").value;
	
	var uri = 'ifms.htm?actionFlag=loadGPFRequestList&userType='+userType;
	document.FrmGPFWorklist.action = uri ;
	enableAjaxSubmit(true);
	document.FrmGPFWorklist.submit();
}
</script>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseList" value="${resValue.CaseList}" />
<c:set var="UserList" value="${resValue.UserList}"/>
<c:set var="UserType" value="${resValue.UserType}"/>
<c:set var="requestType" value="${resValue.requestType}"/>

<hdiits:form name="FrmGPFWorklist" id="FrmGPFWorklist" encType="multipart/form-data" validate="true" method="post" >

<input type="hidden" id="hidUserType" name="hidUserType" value="${resValue.UserType}"/>
<table width="100%">
<tr>
<td>
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.SEARCHREQ" bundle="${gpfLabels}" ></fmt:message></legend>
<table width="100%">
	<tr>
		<td width="20%">
			<b><fmt:message key="CMN.SEARCHREQBY" bundle="${gpfLabels}"></fmt:message></b>
		</td>
		<td width="20%">
			<fmt:message key="CMN.NAME" bundle="${gpfLabels}"></fmt:message>
			 <input type="text" id="txtEmpName" name="txtEmpName" />
			 <span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
		</td>
		<td width="20%">
			<fmt:message key="CMN.DATE" bundle="${gpfLabels}"></fmt:message>
			 <input type="text" id="txtDate" name="txtDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/>
		    <img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtDate",375,570)' style="cursor: pointer;"  />
		</td>
	
		<td width="10%">
			<hdiits:button type="button" captionid="BTN.SEARCH" bundle="${gpfLabels}" id="btnGo" name="btnGo" onclick="searchReq();" ></hdiits:button>
		</td>
		
		<td width="30%">
			<hdiits:button type="button" captionid="BTN.GETALL" bundle="${gpfLabels}" id="btnGetAll" name="btnGetAll" onclick="getAllRecords();" ></hdiits:button>
		</td>		
		
	</tr>	
</table>
</fieldset>
</td>
</tr>

<tr>
<td>
<table width="100%">
<tr>
<td>
<fieldset class="tabstyle">
	<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; height:400px;  ">
	
    <display:table list="${caseList}"  id="vo" requestURI="" export="" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" titleKey="CMN.SUBSCRIBER" >		
				<c:out value="${vo[0]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.TID" >		
				<a href = "ifms.htm?actionFlag=loadGPFRequestProcess&txtSevaarthId=${vo[2]}&criteria=1&requestType=${vo[5]}&userType=${UserType}&TrnsId=${vo[1]}&pkValue=${vo[6]}"><c:out value="${vo[1]}" /></a>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.REQTYPE" >		
				<c:if test="${vo[5] == 'CS'}"><c:out value="Change Subscription"></c:out></c:if>
				<c:if test="${vo[5] == 'RA'}"><c:out value="Refundable Advance"></c:out></c:if>
			    <c:if test="${vo[5] == 'NRA'}"><c:out value="Non-Refundable Advance"></c:out></c:if> 		
			    <c:if test="${vo[5] == 'FW'}"><c:out value="Final Payment"></c:out></c:if> 	
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
			
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.INWARDDATE"  >		
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy h:mm a" value="${vo[7]}" />
		</display:column>
			
	</display:table>
	<c:if test="${caseList != null && caseList[0] != null}">
	<table>
	<tr>
		<td colspan="2" align="center"><label style="color: red"><fmt:message key="MSG.WORKLIST" bundle="${gpfLabels}" /></label></td>
	</tr>
	</table>
	</c:if>
	</div>

</fieldset>
</td>
</tr>
</table>

</td>
</tr>
</table>
</hdiits:form>	

<c:if test="${resValue.UserType == 'DEOAPP'}">
<ajax:autocomplete source="txtEmpName" target="txtEmpName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoComplete&userType=DEOAPP"
	parameters="searchKey={txtEmpName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
</c:if>
<c:if test="${resValue.UserType == 'HO'}">
<ajax:autocomplete source="txtEmpName" target="txtEmpName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoComplete&userType=HO"
	parameters="searchKey={txtEmpName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
</c:if>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DraftList" value="${resValue.lLstDraftReq}"></c:set>

<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/lna/loansEmpSearch.js"></script>
<script type="text/javascript">
function searchReq()
{
	var name = document.getElementById("txtEmpName").value;
	var date = document.getElementById("txtDate").value;
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

	var uri = 'ifms.htm?actionFlag=LNAloadDraftRequestList&elementId=800029'+url;
	document.frmLNADraftReq.action = uri ;
	document.frmLNADraftReq.submit();
}

function getAllRecords()
{
	var uri = 'ifms.htm?actionFlag=LNAloadDraftRequestList&elementId=800029';
	document.frmLNADraftReq.action = uri ;
	document.frmLNADraftReq.submit();
}
</script>


<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request" />

<hdiits:form name="frmLNADraftReq" encType="multipart/form-data"  validate="true" method="post">
<table width="100%">
<tr>
<td>
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.SEARCHREQ" bundle="${lnaLabels}" ></fmt:message></legend>
<table width="100%">
<tr>
		<td width="20%">
			<b><fmt:message key="CMN.SEARCHREQBY" bundle="${lnaLabels}"></fmt:message></b>
		</td>
		<td width="20%">
			<fmt:message key="CMN.NAME" bundle="${lnaLabels}"></fmt:message>
			 <input type="text" id="txtEmpName" name="txtEmpName" style="text-transform: uppercase" onblur="isName(this,'This field should not contain any special characters or digits')" />
			 <span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
		</td>
		<td width="20%">
			<fmt:message key="CMN.DATE" bundle="${lnaLabels}"></fmt:message>
			 <input type="text" id="txtDate" name="txtDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/>
		    <img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtDate",375,570)' style="cursor: pointer;"  />
		</td>
	
		<td width="10%">
			<hdiits:button type="button" captionid="BTN.SEARCH" bundle="${lnaLabels}" id="btnSearch" name="btnSearch" onclick="searchReq();" ></hdiits:button>
		</td>
		
		<td width="10%">
			<hdiits:button type="button" captionid="BTN.GETALL" bundle="${lnaLabels}" id="btnGetAll" name="btnGetAll" onclick="getAllRecords();" ></hdiits:button>
		</td>
		<td width="10%">
			<center><hdiits:button type="button" captionid="BTN.CLOSE" bundle="${lnaLabels}" id="btnClose" name="btnClose" onclick="getHomePage();"></hdiits:button></center>
		</td>		
</table>
</fieldset>
</td>
</tr>
<tr>
<td>
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.DRAFTREQ" bundle="${lnaLabels}" ></fmt:message></legend>
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; height:300px;  ">
	
    <display:table list="${DraftList}"  id="vo" style="width:100%"  pagesize="10" requestURI="">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" titleKey="CMN.SUBSCRIBER" >		
				<a href = "ifms.htm?actionFlag=loadLoanAdvanceRequest&txtSevaarthId=${vo[1]}&criteria=1&requestType=${vo[2]}&userType=HODASST2&pkValue=${vo[6]}&elementId=800029">
				<c:out value="${vo[0]}"></c:out></a>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" titleKey="CMN.SEVAARTHID" >		
				<c:out value="${vo[1]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" titleKey="CMN.REQTYPE" >		
				<c:if test="${vo[2] == '800028'}"><c:out value="Computer Advance"></c:out></c:if>
				<c:if test="${vo[2] == '800029'}"><c:out value="House Building Advance"></c:out></c:if>
				<c:if test="${vo[2] == '800030'}"><c:out value="Vehicle Advance"></c:out></c:if> 	
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.SUBTYPE" >		
				<c:out value="${vo[3]}"></c:out>				 		
 		</display:column>
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="appDate"/>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" titleKey="CMN.APPDATE"  >		
				<c:out value="${appDate}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" titleKey="CMN.STATUS" >
			<c:choose>
			<c:when test="${vo[5] == 'D'}"><c:out value="Draft"></c:out></c:when>		
			<c:when test="${vo[5] == 'R'}"><c:out value="Rejected"></c:out></c:when>
			</c:choose>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" titleKey="CMN.HODREMARKS"  >		
				<c:out value="${vo[7]}"></c:out> 
		</display:column>	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" titleKey="CMN.SAVEDON"  >	
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy h:mm a" value="${vo[8]}" />		
		</display:column>			
	</display:table>
	<c:if test="${DraftList != null && DraftList[0] != null}">
	<table>
	<tr>
		<td colspan="2" align="center"><label style="color: red"><fmt:message key="MSG.DRAFTREQ" bundle="${lnaLabels}" /></label></td>
	</tr>
	</table>
	</c:if>
	</div>
</fieldset>
</td>
</tr>
</table>
<br>
</hdiits:form>
<ajax:autocomplete source="txtEmpName" target="txtEmpName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteLNA&userType=HODASST2"
	parameters="searchKey={txtEmpName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
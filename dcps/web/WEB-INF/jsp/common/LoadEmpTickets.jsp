<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.tcs.sgv.common.constant.Constants"%>


<script type="text/javascript"
	src="script/common/IFMSCommonFunctions.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="details" value="${resValue.details}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="lStrUser" value="${resValue.lStrUser}"></c:set>
<c:set var="pendingDays" value="${resValue.pendingDays}"></c:set>
<c:set var="lLstStatus" value="${resValue.lLstStatus}"></c:set>
<c:set var="fromDate" value="${resValue.fromDate}"></c:set>
<c:set var="toDate" value="${resValue.toDate}"></c:set>
<c:set var="filterStatus" value="${resValue.filterStatus}"></c:set>


<script>
function addNew(flag){

	
	var User = document.getElementById("hdnUser").value;
	var url="ifms.htm?actionFlag=addNewTicket&flag="+flag+"&lStrUser="+User;
	self.location.href = url;
	
}
</script>

<script type="text/javascript">

function filter(){

	//alert('In filter');
	var status= document.getElementById("filterByStatus").value;
	var fromDate= document.getElementById("fromDate").value;
	var toDate= document.getElementById("toDate").value;
	var url;

	if(status!='' && status!='-1' ){
		url="ifms.htm?actionFlag=loadPRTracker&Flag=R&status="+status+"&fromDate="+fromDate+"&toDate="+toDate;
		document.EmpForm.action= url;
		document.EmpForm.submit();
	}
	else{
		url="ifms.htm?actionFlag=loadPRTracker&Flag=R";
		document.EmpForm.action= url;
		document.EmpForm.submit();
	}
	
}
</script>

<script>
function exportExcel(){


	var status= document.getElementById("filterByStatus").value;
	var fromDate= document.getElementById("fromDate").value;
	var toDate= document.getElementById("toDate").value;
	//url="ifms.htm?actionFlag=generateExcelForTicketDetails&Flag=R&status="+status+"&fromDate="+fromDate+"&toDate="+toDate;
	url="ifms.htm?actionFlag=generateExcelForTicketDetails&Flag=R";
	document.EmpForm.action= url;
	document.EmpForm.submit();
	
}

</script>
<!-- Added by pratik for scroll issue on zoom 31-07-23-->
<style>
.scrollablediv {
    width: auto !important;
}
</style>
<!-- End by pratik -->
<input type="hidden" id="hdnUser" value="${resValue.lStrUser}">
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels"
	scope="request" />
<hdiits:form name="EmpForm" id="EmpForm" encType="multipart/form-data"
	method="post" validate="">
	
	<c:choose>
		<c:when test="${lStrUser == 'R'}">
			<div align="center">
			<fieldset class="tabstyle"><legend> <b>Filter By</b> </legend>
			<table>
			<tr>
			<td><c:out value="Status"></c:out></td>
			<td><select name="filterByStatus"
					id="filterByStatus" style="width: 85%,display: inline;">
					<option title="Select" value="-1"><c:out value="Select"></c:out></option>
		
					<c:forEach var="lLstStatus" items="${resValue.lLstStatus}">
						<c:choose>
									<c:when test="${resValue.filterStatus == lLstStatus.lookupId}">
										<option value="${lLstStatus.lookupId}" selected="selected"><c:out value="${lLstStatus.lookupDesc}"></c:out></option>
									</c:when>
									<c:otherwise>
										<option value="${lLstStatus.lookupId}"><c:out value="${lLstStatus.lookupDesc}"></c:out></option>
									</c:otherwise>
						</c:choose>
					</c:forEach>
				</select> </td>
				
				<td><c:out value="From Date"></c:out></td>
				<td><input type="text" id="fromDate" name="fromDate" value="${resValue.fromDate}"  onkeypress="digitFormat(this);dateFormat(this);" ><img src='images/CalendarImages/ico-calendar.gif' width='20'
						onClick='window_open("fromDate",375,570)'	style="cursor: pointer;">
				
				</td>
				
				<td><c:out value="To Date"></c:out></td>
				<td><input type="text" id="toDate" name="toDate" value="${resValue.toDate}" onkeypress="digitFormat(this);dateFormat(this);" ><img src='images/CalendarImages/ico-calendar.gif' width='20'
						onClick='window_open("toDate",375,570)'	style="cursor: pointer;">
				
				</td>
				
				<td><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
				value="Filter" onclick="filter();"
				name="btnFilter" style="width: 120px;" /></td>
			
			
			
			</tr>
			
			</table>
			
			</fieldset>
			</div>
		</c:when>
	</c:choose>
	
	<fieldset class="tabstyle" ><br>
	<div class="scrollablediv" align="center" style="height: auto;">
	<display:table list="${details}" id="vo" requestURI="" pagesize="80" sort="external"
		 style="width:90%"  defaultsort="3" defaultorder="descending" cellpadding="5" >

		
					<display:column titleKey="CMN.TICKETID" headerClass="datatableheader" sortable="false" class="oddcentre"
					style="width:15%">
					
					
					<a style="align:center" href='ifms.htm?actionFlag=addNewTicket&flag=Update&ticketId=${vo[0]}&lStrUser=${lStrUser}&filterStatus=${filterStatus}'>HTE${vo[0]}</a>
				
					</display:column>
			

			<display:column titleKey="CMN.TICKETITLE" headerClass="datatableheader" sortable="false" class="oddcentre"
							style="width:15%;text-align:left"><c:out value="${vo[1]}"></c:out>
			</display:column>
			
			<display:column titleKey="CMN.TICKETDESC" headerClass="datatableheader" sortable="false" class="oddcentre"
					style="width:15%;text-align:left">${vo[2]}
			</display:column>
			
			
			
			<display:column titleKey="CMN.TICKETSTATUS"	headerClass="datatableheader" sortable="false" class="oddcentre"
					style="width:15%;text-align:left">${vo[3]}
			</display:column>
			
		<%-- 	<display:column titleKey="CMN.TICKETREMARKS" headerClass="datatableheader" sortable="false" class="oddcentre"
					style="width:15%;text-align:left">${vo[7]}
			</display:column>--%>
			
			<%-- <display:column titleKey="CMN.TICKETPRIORITY" headerClass="datatableheader" sortable="false" class="oddcentre"
					style="width:15%;text-align:left">${vo[5]}
			</display:column> --%>
			
	<c:choose>
		<c:when test="${lStrUser == 'R'}">
		
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"	value="${vo[4]}" var="createDate" /> 
			<display:column style="width:15%;text-align:center" titleKey="CMN.TICKETCREATEDATE"
					headerClass="datatableheader" class="oddcentre">
					<c:out value="${createDate}"></c:out>
			</display:column>
			
	
 			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"	value="${vo[7]}" var="resolvedDate" /> 
	    <display:column titleKey="CMN.TICKETPENDINGFROM"	headerClass="datatableheader" sortable="false" class="oddcentre"
					style="width:15%;text-align:left">
					<c:choose>
						<c:when test="${vo[3] == 'Closed' || vo[3] == 'Hard Closed'}">
							<c:out value="Closed on ${resolvedDate}"></c:out>
						</c:when>
						
						<c:otherwise>
							<c:out value="${vo[5]} day/s"></c:out>
						</c:otherwise>
					</c:choose>
        </display:column> 
        
 
	</c:when>
	</c:choose>
	</display:table></div>
	
	
	<table align="center">
	<c:choose>
		<c:when test="${lStrUser != 'R'}">
		<tr>
					<td><input type="button" name="addNewEntry" class="bigbutton"
					id="addNewEntry" type="button" value="Add New Ticket"
					onclick="addNew('Add');" size="35" /></td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
					<td><input type="button" name="exportToExcel" class="bigbutton"
					id="exportToExcel" type="button" value="Export to excel"
					onclick="exportExcel();" size="35" /></td>
		</tr>
		</c:otherwise>
		</c:choose>
	</table>
	
	
	</fieldset>
</hdiits:form>
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>	
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>

<!-- resource Bundle  -->
<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.ess.UserPost" var="userPostMapping" scope="request" />
<!-- resource Bundle  -->

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="empArrarMpgDataList" value="${resValue.empArrarMpgDataList}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />

<script type="text/javascript"><!--
function mapEmployeeWithArrear()
{
	document.viewEmpArrearBillMpg.action="hrms.htm?actionFlag=fillMapEmpWithArrearScreen";
	document.viewEmpArrearBillMpg.submit();
}
function updateEmployeeArrear(salRevId,billSubheadId,orderFrmDate,orderEndDate)
{	
	var url="hrms.htm?actionFlag=getEmpArrearCmpAmtData&salRevId="+salRevId+"&billSubheadId="+billSubheadId+"&orderFrmDate="+orderFrmDate+"&orderEndDate="+orderEndDate;
	window.open(url,'EditEmployeeArrearDetails',' top=20, left=20, resizable=yes, titlebar=yes, menubar=yes, scrollbars=yes, toolbar=yes, status=yes');
	 
}
function deleteEmployeeArrear(salRevId,billSubheadId)
{
	if(confirm('Do you want to delete these order details?'))
	{
		document.viewEmpArrearBillMpg.action="hrms.htm?actionFlag=deleteEmployeeArrearDtls&salRevId="+salRevId+"&billSubheadId="+billSubheadId;
		document.viewEmpArrearBillMpg.submit();
	}
}
--></script>

<hdiits:form name="viewEmpArrearBillMpg" validate="true" method="POST" action=""  encType="multipart/form-data">
<link rel='stylesheet' href='<c:url value="/themes/hdiits/tabcontent.css"/>' type='text/css' />
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>View Employee and Arrear Bill Mapping</b></a></li>
	</ul>
	</div>

<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
		
		<table  align="center" cellspacing="2" cellpadding="2" border="0" width="100%">
			<tr>
				<td align="left" colspan="4">
					<a id="linkToEmpArrearMpg" href="javascript:mapEmployeeWithArrear()">Map Employees With Arrear</a>
				</td>    	
	       </tr>
	       <tr >
	       		<td colspan="4"></td>	       	
	       <tr>
	       <tr>
       		<td colspan="4">
       		<display:table name="${empArrarMpgDataList}" requestURI="" pagesize="20" id="row" export="false" style="width:100%">
				<fmt:formatDate var="salRevOrdDate" pattern="dd/MM/yyyy" value="${row[8]}"/>
				<display:column class="tablecelltext" title="Salary Revision Order No. " headerClass="datatableheader" value="${row[1]}(${salRevOrdDate})" />			
					<fmt:formatDate var="effFromDate" pattern="dd/MM/yyyy" value="${row[4]}"/>
				<display:column class="tablecelltext" title="Eff From Date" style="text-align:center" value="${effFromDate}" headerClass="datatableheader" />
					<fmt:formatDate var="effToDate" pattern="dd/MM/yyyy" value="${row[5]}"/>
				<display:column class="tablecelltext" title="Eff To Date" style="text-align:center" value="${effToDate}" headerClass="datatableheader" />
					<fmt:formatDate var="payoutDate" pattern="dd/MM/yyyy" value="${row[6]}"/>
				<display:column class="tablecelltext" title="Payout Date" style="text-align:center" value="${payoutDate}" headerClass="datatableheader" />
				<display:column class="tablecelltext" title="Reason" style="text-align:center" value="${row[7]}" headerClass="datatableheader" />
				<display:column class="tablecelltext" title="Bill No" style="text-align:center" value="${row[3]}" headerClass="datatableheader" />
					<fmt:formatDate var="effFromDate" pattern="dd/MM/yyyy" value="${row[4]}"/>
				<display:column class="tablecelltext" title="Edit Arrear Details" style="text-align:center" headerClass="datatableheader" >					
					<a id="linkToUpdEmpArrearMpg'${row[0]}','${row[2]}'" href="javaScript:updateEmployeeArrear('${row[0]}','${row[2]}','${effFromDate}','${effToDate}')">Update</a>
				</display:column>
				<display:column class="tablecelltext" title="Delete Arrear Details" style="text-align:center" headerClass="datatableheader" >
					<a id="linkToDelEmpArrearMpg'${row[0]}','${row[2]}'" href="javaScript:deleteEmployeeArrear('${row[0]}','${row[2]}')">Delete</a>
				</display:column>			
				
			</display:table>
	       	</td>
	       </tr>
	       
		</table>		
	</div>
	<script type="text/javascript">
	<!--
		initializetabcontent("maintab");
		if('${msg}'!=null && '${msg}'!='')
		{
			alert('${msg}' );			
			document.viewEmpArrearBillMpg.action="hrms.htm?actionFlag=getEmpArrearBillMpgList";
			document.viewEmpArrearBillMpg.submit();
		}	
	-->
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="" />
</div>
</hdiits:form>
<%
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
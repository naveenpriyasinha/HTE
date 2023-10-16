<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<link rel="stylesheet"
	href="<c:url value="/common/css/tabcontent.css" />" />

<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"
	scope="request" />
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>

<c:set var="hrDeputreqmtDtl" value="${resultValue.hrDeputreqmtDtlList}">
</c:set>
<style type="text/css">

/* All form elements are within the definition list for this example */
.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}

</style>
<script>
function Closebt()
{	
	method="POST";
	document.frmBroadcast.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmBroadcast.submit();
}
</script>
<hdiits:form name="frmBroadcast" validate="true" method="POST">
<!-- 

	<table align="center" width="100%">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="deputationRequestRecv" bundle="${comLable}" ></fmt:message>


			</u></strong> </font></td>
		</tr>
		</table> -->
		<hdiits:fieldGroup titleCaptionId="deputationRequestRecv" bundle="${comLable}"  collapseOnLoad="true">
		<c:if test="${not empty resultValue.hrDeputreqmtDtlList }">	
		
<table width="100%">
		<tr>
			<td>
				
<c:set var="i" value="0" /> <% int a=0; %>
	
		
		<display:table list="${resultValue.hrDeputreqmtDtlList}" id="row" requestURI="" pagesize="10"  style="width:100%" offset="1" >
		
		
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		<display:column class="tablecell"  	titleKey="SrNo" headerClass="tablerow" value="<%=a=a+1 %>" style="text-align: center" >
		</display:column>

		<display:column class="tablecell" titleKey="fileId" 
			headerClass="tablerow" style="text-align: center"
			>
         <a href="./hrms.htm?actionFlag=loadBCData&fileId=${row.fileId}">${row.fileId}  </a>
					
		</display:column>
		
		<display:column class="tablecell" titleKey="requiredForLocation" 
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.locName}
		</display:column>
		
		<display:column class="tablecell" titleKey="reqforpost"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.postName}
		</display:column>
		<display:column class="tablecell" titleKey="noofperson"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.noOfPerson}
		</display:column>

		<display:column class="tablecell" titleKey="startDate"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.startDate}
		</display:column>
		<display:column class="tablecell" titleKey="endDate"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.endDate}
		</display:column>
		<c:set var="i" value="${i+1}" />
		
		<display:footer media="html"></display:footer>		
		
	</display:table>

		</td>
		</tr>
</table>
<table width="100%" align="center" >
	
		<tr>
		<td align="center">
		<hdiits:button name="close" type="button" captionid="Dep.Close" bundle="${comLable}" onclick="Closebt()"/>
		</td>
		
		</tr>

</table>
</c:if>
<c:if test="${empty resultValue.hrDeputreqmtDtlList }">	
	<br/>
<br/>
<br/>
	<br/>
<br/>
<br/>

<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<tr>
		<td align="center">
		<hdiits:caption captionid="Dep.norecfnd" bundle="${comLable}"/>
		</td>
	</tr>
		<tr>
		<td align="center">
		<hdiits:button name="close" type="button" captionid="Dep.Close" bundle="${comLable}" onclick="Closebt()"/>
		</td>
		
		</tr>

</table>
</c:if>
</hdiits:fieldGroup>
</hdiits:form>





<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
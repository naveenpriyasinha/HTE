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
<c:set var="dataList" value="${resValue.dataList}" ></c:set>

<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />

<script type="text/javascript"><!--

function closeWindow()
{	
	var uri='';
	var actionf="getEmpArrearBillMpgList";
	uri='./hrms.htm?actionFlag='+actionf;
		
	
	
	document.viewEmpArrearExcelData.action=uri;
	document.viewEmpArrearExcelData.submit();	
	
	
}
--></script>

<hdiits:form name="viewEmpArrearExcelData" validate="true" method="POST" action=""  encType="multipart/form-data">

<link rel='stylesheet' href='<c:url value="/themes/hdiits/tabcontent.css"/>' type='text/css' />
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>View Employee and Arrear Bill Mapping</b></a></li>
	</ul>
	</div>

<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
	<hdiits:fieldGroup titleCaptionId="pay.empCmpAmtList" bundle="${commonLables}" > 
		<table align="center" cellspacing="2" cellpadding="2" border="0" width="100%">	
		
		<c:if test="${dataList ne null }">			
		<display:table name="${dataList}" requestURI="" id="row" pagesize="20" export="true" style="width:100%" >			
			<display:setProperty name="export.excel.class" value="org.displaytag.export.ExcelView" />
		<display:column title="Sr. No" value="${row[0]}" class="tablecelltext"  headerClass="datatableheader"/>			
		<display:column title="Post Id" value="${row[1]}" class="tablecelltext"  headerClass="datatableheader"/>
		<display:column title="PSR No" value="${row[2]}" class="tablecelltext"  headerClass="datatableheader"/>
		<display:column title="Emp Name" value="${row[3]}" class="tablecelltext"  headerClass="datatableheader"/>
		<display:column title="Dsgn" value="${row[4]}" class="tablecelltext"  headerClass="datatableheader"/>
		<display:column title="Basic" value="${row[5]}" class="tablecelltext"  headerClass="datatableheader"/>					
		</display:table>
		</c:if>
		</table>
		<table align="center">
			<tr >			
	         <td align="left" colspan="1">
	             <hdiits:button name="closeButton" type="button" captionid="CLOSE" bundle="${empEditListCommonLables}" onclick="closeWindow()"/>
	         </td>
	       </tr>
		</table>
	</hdiits:fieldGroup>
	</div>
	<script type="text/javascript">
	<!--
		initializetabcontent("maintab");
		if('${msg}'!=null && '${msg}'!='')
		{
			alert('${msg}' );
			window.close();
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
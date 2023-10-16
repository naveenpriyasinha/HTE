
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<html>

  
<head>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/script/leave/DateDifference.js"/>"></script> 

<fmt:setBundle basename="resources.hr.addPay.AdditionalPayLabels" var="AddPay" scope="request"/>

</head>
<body>

<script LANGUAGE="JavaScript">
function closePage()
{
	document.AdditionalPayReports.action="hdiits.htm?actionFlag=getHomePage";
 	document.AdditionalPayReports.submit();
}
</script>

<hdiits:form name="AdditionalPayReports" validate="true" method="POST"  action="" encType="text/form-data"> 

 	
<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<fmt:message key="AddPay.AddPayReports" bundle="${AddPay}"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
		
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<br>
	<br>
	<br>
	<table width="100%" align="center">
 <TR bgcolor="#386CB7" >
<td width="100%" align="center" height="20px"><b><u><FONT color="WHITE"><fmt:message key="AddPay.AddPayReports" bundle="${AddPay}"/></FONT></u>
</b></td> </TR> </table>
<br>

<hdiits:table id="AddPayReports" align="center" width="30%" bordercolor="Black">
<hdiits:tr>
<hdiits:td align="center">
<hdiits:a id="approvedReport" captionid="AddPay.addApprovalReport" bundle="${AddPay}" href="hrms.htm?actionFlag=reportService&reportCode=300201&action=generateReport&dynamicReport=false" > </hdiits:a>
</hdiits:td>
</hdiits:tr>

<hdiits:tr>
<hdiits:td align="center">
<hdiits:a  id="pendingReport" captionid="AddPay.addPendingReport" bundle="${AddPay}" href="hrms.htm?actionFlag=reportService&reportCode=300202&action=generateReport&dynamicReport=false" > </hdiits:a>
</hdiits:td>
</hdiits:tr>

<hdiits:tr>
<hdiits:td align="center">
<hdiits:a id="AllRequestReport" captionid="AddPayaddRequestAllReport" bundle="${AddPay}" href="hrms.htm?actionFlag=reportService&reportCode=300203&action=generateReport&dynamicReport=false" > </hdiits:a>
</hdiits:td>
</hdiits:tr>

<hdiits:tr>
<hdiits:td>
</hdiits:td>
</hdiits:tr>

<hdiits:tr>
<hdiits:td align="center">
<b><hdiits:button name="frmClose" type="button" captionid="AddPay.close" bundle="${AddPay}" onclick="closePage();"/></b>
</hdiits:td>
</hdiits:tr>
</hdiits:table>

	</div>

	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>



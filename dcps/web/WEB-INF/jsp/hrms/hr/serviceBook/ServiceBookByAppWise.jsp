<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.ServiceBook.ServiceBook"
	var="serviceBookLabel" scope="request" />
<html>
<head>
<fmt:setBundle basename="resources.hr.ServiceBook.ServiceBook" var="serviceBookLabel" scope="request" />
<script type="text/javascript"
	src="script/hrms/hr/serviceBook/serviceBook.js"></script>
<script type="text/javascript"
	src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>

<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/tagLibValidation.js"/>"></script>

<script type="text/javascript">


function generateReport(){

var parameterList = new Array();
var report_Code = document.frmServiceBook.Application.value;
//parameterList[0] = document.frmServiceBook.Order.value;
//parameterList[1] = document.frmServiceBook.fromdate.value;
//parameterList[2] = document.frmServiceBook.todate.value;

/*if(report_Code==0){

	alert('<fmt:message key="ServiceBook.alert.selectApp" />');
	return false;
}

if(parameterList[0]==0){

	alert('<fmt:message key="ServiceBook.alert.selectOrder" />');
	return false;
}

if(parameterList[1]==''){

	alert('<fmt:message key="ServiceBook.alert.selectFrmDt" />');
	return false;
}

if(parameterList[2]==''){

	alert('<fmt:message key="ServiceBook.alert.selecttoDt" />');
	return false;
}*/
//document.frmServiceBook.action = "hdiits.htm?actionFlag=getServiceReports&reportCode="+report_Code;

//document.frmServiceBook.action = "hdiits.htm?actionFlag=reportService&reportCode="+report_Code+"&action=generateReport&FromParaPage=FALSE";
//document.frmServiceBook.submit();

top.frames['Report'].location = "hdiits.htm?actionFlag=getServiceReports&reportCode="+report_Code;

}


function hideHeader(){

var objm =window.frames['Report'].document.getElementById("nav");
	
	if(objm!=null)
	{
		window.frames['Report'].document.getElementById("nav").style.display='none';		
		window.frames['Report'].document.getElementById("header").style.display='none';
	}

}

</script>



</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="serviceBookList" value="${resValue.serviceBookList}"></c:set>



<hdiits:fieldGroup titleCaption="Generate Report Application Wise" >


<table align="center">
	<tr>
	</tr>
	<tr align="center">
		<td><b><fmt:message key="ServiceBook.AppName" /></b>
		<hdiits:select sort="true" name="Application" size="1" caption="drop_down" mandatory="true" onchange="generateReport()">
				<hdiits:option value="0"><fmt:message key="ServiceBook.Select" bundle="${serviceBookLabel}"/></hdiits:option>
					<c:forEach var="serviceBookList" items="${serviceBookList}">
						<hdiits:option value="${serviceBookList.lookupId}" >${serviceBookList.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
		
</table>	
<br>
<br>
<br>
<br>
<table align="center">
	<tr>
		<td><iframe class="navbar" scrolling="auto" name="Report" onload="hideHeader()" id="Report" src="" frameborder="0" frameborder="0" width="925px" height="400px" align="center" >
		 </iframe>
		</td>
	</tr>
</table>
</hdiits:fieldGroup>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


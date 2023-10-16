<%
try {
%>
<%@page buffer="256kb" autoFlush="true"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/statusbar.js"/>"></script>	
<script type="text/javascript"	src="<c:url value="/script/common/base64.js"/>"></script>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="showEmpdtl" value="${resultValue.showEmpdtl}">
</c:set>
<c:set var="lstOfApplication" value="${resultValue.lstOfApplication}">
</c:set>
<c:set var="forUsrId" value="${resultValue.forUsrId}">
</c:set>
<c:set var="EmpName" value="${resultValue.EmpName}">
</c:set>

<fmt:setBundle basename="resources.common.onBehalfAdmin.onBehalfAdminLables"
	var="commonLables1" scope="request" />


<script type="text/javascript">
function SearchEmp()
{
	//var href='hdiits.htm?actionFlag=allData';
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
	window.open(href,'chield','width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}
var empArray = new Array();
var records=new Array();
var tempUserID=0;
var k=0;
var uniqueUserId = new Array();
function copyUserId(tempUserID)
{
	uniqueUserId[k]=tempUserID;
	k++;
	
}
function empSearch(from,a)		
{	
	var userid;
	for(var i=0; i<from.length; i++)
	{
	empArray[i] = from[i].split("~"); 
	records=empArray[i];
	tempUserID=records[4];
	copyUserId(tempUserID);
	}
	for(var j=0; j<from.length; j++)
	{
		var single = empArray[j];
		userid = single[2];
	}
	document.getElementById('UserId').value=userid;
	var urlstyle="hrms.htm?actionFlag=onBehalfAdmin&userId="+userid;
	document.onBehalf.action=urlstyle;
	document.onBehalf.submit();
	
	
}
function submitApplication(){
	var urlstyle=document.getElementById('HrmsAppName').value;
	document.getElementById('HrmsAppName').value="0";
	//alert(urlstyle);
	urlstyle=urlstyle+"&beHalfUserId="+document.getElementById('UserId').value;
	//alert('   '+urlstyle);
	document.onBehalf.action= urlstyle;
	
	document.onBehalf.submit();
}
</script>

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="HRMS.OnBehalf" bundle="${commonLables1}" /></b></a></li>
	</ul>
	</div>
	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent">
		<hdiits:form name="onBehalf" validate="true" method="POST" encType="multipart/form-data">
		
		<hdiits:hidden name="UserId" id="UserId" default="${forUsrId}"/>
		<br><br>
		
		<table align="center">
			<tr>
				<td align="center"><b><fmt:message key="HRMS.Search" bundle="${commonLables1}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
				<td><hdiits:search name="txtEmployeeName" url="hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false" size="45"/></td>
			</tr>
		</table>
		<br>
		
		<table id="onBehalfAdmin" width="100%" align="center" >
			<c:if test="${showEmpdtl=='yes'}">
			<tr>
				<td>
					<%@ include file='/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp'%>		
				</td>
			</tr>
			
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			
			<tr align="center" >
				<td align="center">
				<b><fmt:message key="HRMS.SelectApp" bundle="${commonLables1}" /></b>&nbsp;:&nbsp;
				    <hdiits:select name="HrmsAppName" id="HrmsAppName" onchange="submitApplication()">
				    	<hdiits:option value="-1"> <hdiits:caption captionid="HRMS.Select"  bundle="${commonLables1}" captionLang="single"/></hdiits:option>
				    	<c:forEach var="lstOfApp" items="${lstOfApplication}">
				    	<hdiits:option value="${lstOfApp.elementUrl}"><c:out value="${lstOfApp.elementName}"/></hdiits:option>
				    	</c:forEach>
					</hdiits:select>
				</td>
			</tr>
			</c:if>
			<c:if test="${showEmpdtl ne 'yes'}">
			<tr align="center" >
				<td align="center">
					<font color="red"><b><fmt:message key="HRMS.SearchEmp" bundle="${commonLables1}"></fmt:message></b></font>
				</td>
			</tr>
			</c:if>
			
		</table>
		<script type="text/javascript">
			initializetabcontent("maintab")
		</script>
		</hdiits:form>
		<script>
			document.onBehalf.name_txtEmployeeName.value='${EmpName}';
			document.onBehalf.name_txtEmployeeName.readOnly=true;
		</script>
	</div>
	</div>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%>



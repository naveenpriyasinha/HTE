
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="EmpDetail" value="${resValue.SEARCH_EMPLOYEE_BY_ALL}"></c:set>
<c:set var="EmpDetail1" value="${resValue.EmpDetail1}"></c:set>
<c:set var="LeaveStatus" value="${resValue.LeaveStatus}"></c:set>
<c:set var="AddReqId" value="${resValue.AddReqId}"></c:set>

<fmt:setBundle basename="resources.hr.addPay.AdditionalPayAlertMessage" var="alertLables" scope="request" />

<html>
<script type="text/javascript">

function show_popup(filenum)
{

	alert('<fmt:message  bundle="${alertLables}" key="FileNumber"/>'+filenum+ ' ' +'<fmt:message  bundle="${alertLables}" key="AllReadySent"/>');

}

function getDate1()
   {
   		var dt= new Date();
   		var dd1=dt.getDate();
   		var mm1=dt.getMonth()+1;
   		var yy1=dt.getYear();
   		var dt1=dd1+'/'+mm1+'/'+yy1;
		document.getElementById("date1").innerHTML=dt1;
   }
   
function ValidSubmit()
{
	
     var ddt=document.getElementById('frmDate').value;
      if(ddt== -1)
      {
      	alert('<fmt:message  bundle="${alertLables}" key="selectFromDate"/>');      
      }
      else
      {
      	document.AdditionalPay.action = "hrms.htm?actionFlag=getLeaveId";
               document.AdditionalPay.submit(); 
      }
} 

	function closePage()
{
	document.AdditionalPay.action="hdiits.htm?actionFlag=getHomePage";
 	document.AdditionalPay.submit();
}
  </script> 
  
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

<hdiits:form name="AdditionalPay" validate="true" method="POST"  action="" encType="text/form-data"> 

 	
<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="AddPay.AddPay" bundle="${AddPay}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
		<hdiits:hidden name="actionFlag" default="getLeaveId" />
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<br>
<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp">
<jsp:param name="empImage" value="N" /></jsp:include>

<hdiits:fieldGroup titleCaptionId="AddPay.AddPayDetail" bundle="${AddPay}" id="addPayEmpDtls">
<table id="EmpDetail" align="center" style="width: 100%">
<tr></tr>
<tr>
<td width="25%"><b><hdiits:caption captionid="AddPay.AppDate" bundle="${AddPay}"/></b>&nbsp;&nbsp;</td>
<td id="date1" width="25%">
<script>getDate1();</script>
</td>

<td width="25%">
<b><hdiits:caption captionid="AddPay.FrmDate" bundle="${AddPay}"/></b>&nbsp;&nbsp;</td>
<td width="25%">
<hdiits:select captionid="AddPay.FrmDate" bundle="${AddPay}" id="frmDate" name="FromDate" size="1"  mandatory="true" validation="sel.isrequired" onchange="javascript:ValidSubmit()">
<hdiits:option value="-1" selected="true" ><fmt:message key="AddPay.Select" bundle="${AddPay}"/></hdiits:option>
<c:forEach var="PostCategory" items="${EmpDetail1}">
  	<hdiits:option value="${PostCategory.hrInchargeDtl.reqId}" >
	 <fmt:formatDate value="${PostCategory.startDate}" pattern="dd/MM/yyyy" />
	</hdiits:option>
</c:forEach>

</hdiits:select>
	
</td>
</tr>
<tr></tr>
</table>
</hdiits:fieldGroup>
<table width="100%">
<tr>
<td width="25%"></td>
<td width="25%"></td>
<td  width="25%">
<b><hdiits:button name="frmClose" type="button" captionid="AddPay.close" bundle="${AddPay}" onclick="closePage();"/></b>
</td>
<td width="25%"></td>
</tr>
<tr></tr>
<tr>
<td width="25%" align="center">
<c:set var="i" value="1"></c:set>
<c:if test="${LeaveStatus==i}">

<script>show_popup(${AddReqId});</script></c:if>
</td>
</tr>

</table>
			
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



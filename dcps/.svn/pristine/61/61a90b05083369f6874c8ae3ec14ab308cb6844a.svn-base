
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

<fmt:setBundle basename="resources.hr.AdditionalPayLabels" var="AddPay" scope="request"/>

</head>
<body>

<hdiits:form name="AdditionalPay" validate="true" method="POST"  action="" encType="text/form-data"> 

	<br>

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



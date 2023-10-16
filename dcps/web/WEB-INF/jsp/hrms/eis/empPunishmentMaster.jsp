<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="rltBillList" value="${resValue.rltBillList}" ></c:set>
 <script><!--
function chkFunc()
{
	
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	document.punishment.empName.value=empId;
	var retValue=true;
	if(empId=="")
	{
		alert("Please search the employee first");
		retValue=false;
		
	}
	
	return retValue;
}




function beforeSubmit()
{
				
		
		
		var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.punishment.empName.value=empName;
		
		document.punishment.action="./hrms.htm?actionFlag=insertPunishmentData&edit=N";
		document.punishment.submit();
		
		
}

function chkDateCompare()
{
  
    var sysdate= new Date();
   
    if(document.getElementById("startDate").value!='' )
    { 
    	
    	var dateday=sysdate.getDate();
    	var datemonth = sysdate.getMonth()+1;
    	var dateYear= sysdate.getFullYear();
    	
    	if(datemonth<10)
    	{
    		datemonth="0"+datemonth;
    	}
    	
    	var dateString = dateday + "/" + datemonth+ "/" +dateYear;
    	
    var diff = compareDate(document.getElementById("startDate").value,dateString);
    if(diff<0)
    {
    	alert("Recovery Date must be greater than or equal to Current Date");
    	document.getElementById("startDate").value='';    
    	document.getElementById("startDate").focus();	
    	return false;
    }
    }
    return;
}

function validateForm()
{
if(chkFunc()==true)
return true
else
return false

}

function calculateAmt()
{
	var miscInst = document.punishment.installment.value;
	if(miscInst!=null && miscInst!='')
	{
		if(miscInst==0)
		{
			alert('Installment No must be greater than Zero');
			document.punishment.installment.value='';
			document.punishment.installment.focus();
			return false;
		}
		var totAmt=document.punishment.amount.value;
		var installmentNo = document.punishment.installment.value;
		var installmentAmount = totAmt/installmentNo;
		document.punishment.installmentAmt.value=installmentAmount;
	}
}

function cmpDate()
{
	 var diff = compareDate(document.punishment.startDate.value,document.punishment.endDate.value);   

	 if(document.punishment.endDate.value!=null && document.punishment.endDate.value!='')
	 {
	 
	 	var MonthDiff=	datediff(document.punishment.startDate.value,document.punishment.endDate.value);
	 	if(diff < 0  || MonthDiff==-1)
  	 	{
   			alert("To Date must be greater than From Date");
	   		document.punishment.endDate.value='';
   			return false;
  	 	}
	  	
  	 }

}

function chkRecoveredAmt()
{
	var totAmt = document.punishment.amount.value;
	var recvAmt = document.punishment.recoveredAmt.value;
	
	if(recvAmt!=null && recvAmt!='')
	{
		if(eval(recvAmt) > eval(totAmt))
		{
			alert('Recovered Amount must not greater than Total Amount');
			document.punishment.recoveredAmt.value='';
			return false;
		}
	}
}

function chkRecoveredInst()
{
	var totInst = document.punishment.installment.value;
	var recvInst = document.punishment.recoveredInst.value;
	
	if(recvInst!=null && recvInst!='')
	{
		if(eval(recvInst) > eval(totInst))
		{
			alert('Recovered Installment must not greater than Total Installment');
			document.punishment.recoveredInst.value='';
			return false;
		}
	}
}

function chkInstallmentAmt()
{
	if(chkFunc()==true)
	{
		var totAmt = document.punishment.amount.value;
	    var instAmt = document.punishment.installmentAmt.value;
	    if(eval(instAmt) > eval(totAmt))
	    {
	    	alert('Installment amount must not greater than Total Amount');
	    	document.punishment.installmentAmt.value='';
	    	return false;
	    }
	    else
	    	return true;
	}
}
--></script>

<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<hdiits:form name="punishment" validate="true" method="POST"
	action="javascript:beforeSubmit()" encType="multipart/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="PMT.Punishment" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "65%" align= "center"><br>
		
		
	<TD class="fieldLabel" colspan="2">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpSearch"/>
						<jsp:param name="formName" value="punishment"/>
						<jsp:param name="functionName" value="chkFunc"/>
					</jsp:include>
		 </td>
		 <td>
         <hdiits:hidden id="empName" name = "empName" default="" />
	    </td>
	    
	   	   
	    <tr>
			<td><b><hdiits:caption captionid="PMT.reason" bundle="${commonLables}"/></b></td>
			<td><hdiits:textarea cols="50" rows="3" name="reason" default="" captionid="PMT.reason" bundle="${commonLables}"  validation="txt.isrequired" onblur=""   mandatory="true" maxlength="500"/> </td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="PMT.startDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="PMT.startDate" bundle="${commonLables}" name="startDate"  mandatory="true" onblur="chkDateCompare();" validation="txt.isrequired,txt.isdt" /></TD>	
		</tr>
		
		<tr>
			<td><b><hdiits:caption captionid="PMT.endDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="PMT.endDate" bundle="${commonLables}" name="endDate"  mandatory="" onblur="cmpDate();" validation="txt.isdt" /></TD>	
		</tr>
		
	     <tr>
	     <td colspan="4"><b>Order Upload:-</b>
					   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="orderId" />
            	    		<jsp:param name="formName" value="punishment" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="multiple" value="N" />  
				    		<jsp:param name="mandatory" value="Y"/>              
	    				</jsp:include>
	</td></tr>	
	</table>
 	</div>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/> 
 	 	<hdiits:hidden default="getPunishmentData" name="givenurl"/>
<hdiits:jsField  name="validate" jsFunction="validateForm()" />	
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getPunishmentData";
			document.punishment.action=url;
			document.punishment.submit();
		}
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


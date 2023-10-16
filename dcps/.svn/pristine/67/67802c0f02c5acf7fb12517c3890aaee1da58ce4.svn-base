  <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>  
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	 <script type="text/javascript" src="script/leave/DateDifference.js"></script> 
 <script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
	  <script type="text/javascript" src="<c:url value="/script/leave/DateVal.js"/>"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
		<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="common/script/tabcontent.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.gratuityLeaveencashment.gratuityLeaveencashment" var="commonLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="list" value="${resValue.leaveEncash}"></c:set>

<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="userId" value="${resValue.userId}"/>
<script language="javascript">

	function validateFloat(txtUserComputed){
	if(isNaN(txtUserComputed.value)){
		alert('<fmt:message key="HRMS.entValidNo" />');
		document.forms[0].usercompamt.value='';
		document.forms[0].usercompamt.focus();
		return false;
	}
	else{
		return true;	
	}
}
function goBack()
	{
		history.go(-1);
	}

function SubmitForm()
{
	document.leaveEncashmentDisplay.submit();

}
	function showLeaveComputedAmt(re)
	{
		if(re.value==1)
		{
			document.getElementById('HdSysLeaveCompAmt').style.display='';
			document.getElementById('SysLeaveCompAmt').style.display='';
			document.getElementById('HdUserLeaveCompAmt').style.display='none';
			document.getElementById('UserLeaveCompAmt').style.display='none';
			document.getElementById('usercompamt').value='';
		}
		else if(re.value==2)
		{
			document.getElementById('HdSysLeaveCompAmt').style.display='';
			document.getElementById('SysLeaveCompAmt').style.display='';
			document.getElementById('HdUserLeaveCompAmt').style.display='';
			document.getElementById('UserLeaveCompAmt').style.display='';
		}
	}

</script>

<hdiits:form name="leaveEncashmentDisplay"  validate="true" method="POST"  encType="multipart/form-data" action="./hrms.htm?actionFlag=updateleavestatus" >
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>

<%@ include file="retiredtl.jsp"%>
<table class="tabtable">

<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.LeavEncashmentAmt"/></u></strong>
			</font>
		</td>
	</tr>

		<tr >
		<td>
		<strong>
		<fmt:message key="Gra.optSelected"/>
		</strong>
		</td>
		<td align="left" colspan="4" >
		
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio"  value="1" tabindex="1" onclick="showLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.SysCalAmt" /></b>
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" value="2" tabindex="2" onclick="showLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.UserCalAmt"  /></b>
		
		</td>	
	
	</tr>
	
	
	
	
	<tr bgcolor="#386CB7" id="HdSysLeaveCompAmt">
		<td class="fieldLabel" colspan="5">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.syscomputed"/></u></strong>
			</font>
		</td>
	</tr>
		
	<tr id="SysLeaveCompAmt">
		<td>
			<b><fmt:message key="HRMS.sysenteredamt" />:</b>
		</td>
		<td>
      		<hdiits:text style="background-color: lightblue;" default="${list.sysCompAmt}" captionid ="HRMS.syscompamt" name="syscompamt"   id="syscompamt" mandatory="true" readonly="true" />
		</td>
	</tr>
	
	<tr bgcolor="#386CB7" id="HdUserLeaveCompAmt" style="display:none">
		<td class="fieldLabel" colspan="5">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.usercomputed"/></u></strong>
			</font>
		</td>
	</tr>
	
	<tr style="display:none" id="UserLeaveCompAmt">
		<td>
			<b><fmt:message key="HRMS.userenteredamt" />:</b>
		</td>
		<td>
      		<hdiits:number default="${list.userCompAmt}"  captionid="HRMS.usercompamt" name="usercompamt"  tabindex="3" id="usercompamt" mandatory="true" maxlength="10" floatAllowed="true" />
		</td>
	</tr>
	
	<tr>
		<td>
			<b><fmt:message key="HRMS.remarks" />:</b>
		</td>
		<td>
      		<hdiits:textarea default="${list.remarks}" captionid ="HRMS.remarks" name="remarks" tabindex="4" id="remarks" mandatory="true" maxlength="2000"/>
		</td>
	</tr>
	
	
	<tr>
 		<td colspan ="4" align= "center">
			<hdiits:hidden  name="leaveEncashstatus" caption="status" default="0" />
		<!--  
			<input type="button" name="approve" value="Approve" onclick="document.leaveEncashmentDisplay.leaveEncashstatus.value=1;SubmitForm();"/>
			<input type="button" name="reject" value="Reject" onclick="document.leaveEncashmentDisplay.leaveEncashstatus.value=2;SubmitForm();"/>
			<input type="button" name="forward" value="Forward" onclick="SubmitForm();" />
			<input type="button" name="closepage" value="Close" onclick="goBack();"/>
		-->

<c:set var="reportId" value="0"></c:set>
			<c:if test="${langId=='1'}">
			<c:set var="reportId" value="300025"></c:set>
			<c:set var="reportIdForCancelEl" value="300027"></c:set>
		</c:if>
		
		
			<c:if test="${langId=='2'}">
			<c:set var="reportId" value="300026"></c:set>
			<c:set var="reportIdForCancelEl" value="300028"></c:set>
		</c:if>
		
			<a href="#" tabindex="5" onclick="window.open('hrms.htm?actionFlag=reportService&reportCode=${reportId}&action=generateReport&dynamicReport=false&elementId=300044&userId=${userId}');" >View pendingLeave  |</a>
			<a href="#" tabindex="6" onclick="window.open('hrms.htm?actionFlag=reportService&reportCode=${reportIdForCancelEl}&action=generateReport&dynamicReport=false&elementId=300044&userId=${userId}');" >View pending cancelledLeave</a>


			<hdiits:hidden  name="leave_id" caption="status" default="${list.gratuityEncashId}" />
		</td>
	 </tr>	
	
</table>
<hdiits:jsField name="validateData" jsFunction="validateFloat(document.forms[0].usercompamt)"/>

<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp"/>       
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
 </hdiits:form>

  <%
}catch(Exception e){
	e.printStackTrace();
}

%>
<script>
if("${list.isSysComputed}"==1){
document.forms[0].showLeaveAmt[0].status=true;
showLeaveComputedAmt(document.forms[0].showLeaveAmt[0]);
}
else{
document.forms[0].showLeaveAmt[1].status=true;
showLeaveComputedAmt(document.forms[0].showLeaveAmt[1]);
}
</script>
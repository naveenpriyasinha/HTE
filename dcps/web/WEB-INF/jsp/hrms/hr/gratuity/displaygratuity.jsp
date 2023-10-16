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
<fmt:setBundle basename="resources.hr.gratuity.gratuity" var="commonLables" scope="request"/>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="list" value="${resValue.gratuity}"></c:set>

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
	document.gratuityDisplay.submit();

}

function showLeaveComputedAmt(re)
	{
		if(re.value==1)
		{
			document.getElementById('HdSysLeaveCompAmt').style.display='';
			document.getElementById('SysLeaveCompAmt').style.display='';
			document.getElementById('HdUserLeaveCompAmt').style.display='none';
			document.getElementById('UserLeaveCompAmt').style.display='none';
			document.getElementById('usercompamt').value='0';
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

<hdiits:form name="gratuityDisplay"  validate="true" method="POST"  encType="multipart/form-data" action="./hrms.htm?actionFlag=updateGratuityStatus" >
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>


<br>

<%@ include file="retiredtl.jsp"%> 

<table class="tabtable">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.GratuityAmt"/></u></strong>
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
		
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" tabindex="1" value="1" onclick="showLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.SysCalAmt" /></b>
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" tabindex="2" value="2" onclick="showLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.UserCalAmt"  /></b>
		
		</td>	
	
	</tr>
	
	<tr bgcolor="#386CB7" id="HdSysLeaveCompAmt">
		<td class="fieldLabel" colspan="5" align="center">
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
      		<hdiits:text style="background-color: lightblue;" default="${list.sysCompAmt}" captionid ="HRMS.syscompamt" name="syscompamt" id="syscompamt" mandatory="true" readonly="true"/>
		</td>
	</tr>
	
	<tr bgcolor="#386CB7" id="HdUserLeaveCompAmt" style="display:none">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.usercomputed"/></u></strong>
			</font>
		</td>
	</tr>
	
	<tr id="UserLeaveCompAmt">
		<td>
			<b><fmt:message key="HRMS.userenteredamt" />:</b>
		</td>
		<td>
      		<hdiits:number default="${list.userCompAmt}" captionid ="HRMS.usercompamt" tabindex="3" name="usercompamt" id="usercompamt" mandatory="false" maxlength="10"/>
		</td>
	</tr>
	
	<tr>
		<td>
			<b><fmt:message key="HRMS.remarks" />:</b>
		</td>
		<td>
      		<hdiits:textarea default="${list.remarks}" captionid ="HRMS.remarks" tabindex="4" name="remarks" id="remarks" mandatory="true" maxlength="2000"/>
		</td>
	</tr>
	
	<tr>
 		
    	<td>
      		<hdiits:hidden  name="status" caption="status" default="0" />
   		</td> 
   </tr>
	
	<tr>
 		<td colspan ="4" align= "center">
			<hdiits:hidden  name="gratuitystatus" caption="status" default="0" />
	<!--	
			<input type="button" name="approve" value="Approve" onclick="document.gratuityDisplay.gratuitystatus.value=1;SubmitForm();"/>
			<input type="button" name="reject" value="Reject" onclick="document.gratuityDisplay.gratuitystatus.value=2;SubmitForm();"/>
			<input type="button" name="forward" value="Forward" onclick="SubmitForm();" />
			<input type="button" name="closepage" value="Close" onclick="goBack();"/>
	-->
			<hdiits:hidden  name="gra_id" caption="status" default="${list.gratuityEncashId}" />
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
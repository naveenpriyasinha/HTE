<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/GraLeaveEnCommon.js"/>"></script>
	
<fmt:setBundle basename="resources.hr.gratuity.gratuity" var="commonLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="list" value="${resValue.gratuity}"></c:set>

<script language="javascript">
	var reFlag=0;
	var subFlag=1;
	var alertGraNLeaveEN=new Array();
	alertGraNLeaveEN[0]='<fmt:message key="HRMS.entValidNo" bundle="${commonLables}"/>';
	alertGraNLeaveEN[1]='<fmt:message key="HRMS.enterUseramt" bundle="${commonLables}"/>';
	alertGraNLeaveEN[2]='<fmt:message key="HRMS.enterRemarks" bundle="${commonLables}"/>';
</script>

<hdiits:form name="gratuityDisplay"  validate="true" method="POST"  encType="multipart/form-data" action="./hrms.htm?actionFlag=updateGratuityStatus" >
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>


<br>

<%@ include file="retiredtl.jsp"%> 
<hdiits:fieldGroup bundle="${commonLables}"  titleCaptionId="HRMS.GratuityAmt" id="GratuityAmt" expandable="true">
<table class="tabtable">
	<tr >
		<td width="40%">
		<strong>
		<hdiits:caption bundle="${commonLables}" captionid="HRMS.optSelected" />
		</strong>
		</td>
		<td align="left" colspan="4" >
			<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" tabindex="1" value="1" onclick="showGraLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.SysCalAmt" /></b>
			<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" tabindex="2" value="2" onclick="showGraLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.UserCalAmt"  /></b>
		</td>	
	</tr>
</table>
</hdiits:fieldGroup>

<div id="HdUserLeaveCompAmt" style="display:none">
<hdiits:fieldGroup bundle="${commonLables}" expandable="true"  id="userComp" titleCaptionId="HRMS.usercomputed">	
<table class="tabtable">
	<tr id="UserLeaveCompAmt">
		<td>
			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.userenteredamt" />:</b>
		</td>
		<td>
      		<hdiits:number default="${list.userCompAmt}" captionid ="HRMS.usercompamt" tabindex="3" name="usercompamt" id="usercompamt" mandatory="true" maxlength="10"/>
		</td>
	</tr>
</table>
</hdiits:fieldGroup>
</div>	

<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="HdSysLeaveCompAmt" titleCaptionId="HRMS.syscomputed">
	<table class="tabtable">

	<tr id="SysLeaveCompAmt">
		<td>
			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.sysenteredamt" />:</b>
		</td>
		<td>
      		<hdiits:text style="background-color: lightblue;" default="${list.sysCompAmt}" captionid ="HRMS.syscompamt" name="syscompamt" id="syscompamt" mandatory="true" readonly="true"/>
		</td>
	</tr>
	</table>

<table>
	<tr>
		<td>
			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" />:</b>
		</td>
		<td>
      		<hdiits:textarea default="${list.remarks}" captionid ="HRMS.remarks" tabindex="4" name="remarks" id="remarks" mandatory="true" maxlength="2000"/>
		</td>
	</tr>
</table>
</hdiits:fieldGroup>

<table class="tabtable">
	<tr>
 		
    	<td>
      		<hdiits:hidden  name="status" caption="status" default="0" />
   		</td> 
   </tr>
	
	<tr>
 		<td colspan ="4" align= "center">
			<hdiits:hidden  name="gratuitystatus" caption="status" default="0" />
	
			<hdiits:hidden  name="gra_id" caption="status" default="${list.gratuityEncashId}" />
		</td>
	 </tr>	
	
</table>
<hdiits:jsField name="validateData" jsFunction="validateUserComputed(document.forms[0].usercompamt,document.forms[0].remarks)"/>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
 </hdiits:form>

  <%
}catch(Exception e){
	e.printStackTrace();
}

%>

<script>

if("${list.isSysComputed}"==1){
document.forms[0].showLeaveAmt[0].status=true;
showGraLeaveComputedAmt(document.forms[0].showLeaveAmt[0]);
}
else{
document.forms[0].showLeaveAmt[1].status=true;
showGraLeaveComputedAmt(document.forms[0].showLeaveAmt[1]);
}
</script>
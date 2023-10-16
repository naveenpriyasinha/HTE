<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="script/leave/DateDifference.js"></script>
	<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/GroupInsurance.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RetirementBenefitsCommon.js"/>"></script>
<fmt:setBundle basename="resources.hr.groupInsurance.groupInsurance" var="constants" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="userId" value="${resValue.userId}"/>
<c:set var="EmpDetVO" value="${resValue.EmpDet}"/>
<c:set var="groupList" value="${resValue.GroupList}"/>
<c:set var="currentDate" value="${resValue.currentDate}" />


<script language="javascript">
	var alertGroupIns=new Array();
	alertGroupIns[0]='<fmt:message key="HRMS.amtreq" bundle="${constants}"/>';
	alertGroupIns[1]='<fmt:message key="HRMS.fromdatereq" />';
	alertGroupIns[2]='<fmt:message key="HRMS.todatereq" />';
	alertGroupIns[3]='<fmt:message key="HRMS.sysdatecomp"/>';
	alertGroupIns[4]='<fmt:message key="HRMS.grpreq" />';
	alertGroupIns[5]='<fmt:message key="HRMS.grpmismatch" />';
	alertGroupIns[6]='<fmt:message key="HRMS.lesdate" />';
	alertGroupIns[7]='<fmt:message key="HRMS.dateinvalid" />';
	alertGroupIns[8]='<fmt:message key="HRMS.grpreq" bundle="${constants}"/>';
	alertGroupIns[9]='<fmt:message key="HRMS.amtinvalid" bundle="${constants}"/>';
	alertGroupIns[10]='<fmt:message key="HRMS.amtreq" bundle="${constants}"/>';
	alertGroupIns[11]='<fmt:message key="HRMS.numExcLim" bundle="${constants}"/>';
</script>
<hdiits:form name="groupInsurance" validate="true" action="./hrms.htm?actionFlag=groupInsurance" method="post">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>

<hdiits:fieldGroup bundle="${constants}"  expandable="true" id="Grpdtls" titleCaptionId="HRMS.grpdtls">
<table class="tabtable" border=0 id="getGroupDetails">

		<tr>
	
		<td align="left" colspan="6" ><font color="#8D8D92">
			&nbsp&nbsp<b><hdiits:caption bundle="${constants}" captionid="HRMS.note" /></b>
		</font></td>
	</tr>
		
		<c:set var="counter" value="0"/>
		<c:set var="grpDates" value=""/>
		
		<c:forEach var="group" begin="1" end="4">

  <tr>

		<td align="center" width="13%"><b><hdiits:caption bundle="${constants}" captionid="HRMS.group" /></b></td>
	
    <td>	
	 <hdiits:select name="grp_${counter}" size="1" id="grp_${counter}" caption="group" mandatory="false" sort="false" > 
               <hdiits:option value="0"><fmt:message bundle="${constants}" key="HRMS.select" /></hdiits:option>
               <c:forEach var="subGroup" items="${groupList}">
               <hdiits:option  value="${subGroup.lookupId}_${subGroup.orderNo}">${subGroup.lookupDesc}</hdiits:option>
               </c:forEach>
	</hdiits:select>	
	</td>
	<td>
	<b><hdiits:caption bundle="${constants}" captionid="HRMS.frmdt" /></b></td>
		<td><hdiits:dateTime   validation="txt.isrequired" name="from_${counter}" captionid="HRMS.frmdt"  bundle="${constants}" onblur="" afterDateSelect="changeColor(document.forms[0].from_${counter});"/>
		<c:set var="grpDates" value="from_${counter},${grpDates}" />
		<script>
		FromDateArr['${counter}']=document.forms[0].from_${counter};
		fromDateStr+="from_${counter},";
		
		document.forms[0].from_${counter}.readOnly=true;
		</script>
	</td>
	
	
	<td>
	<b><hdiits:caption bundle="${constants}" captionid="HRMS.todt" /></b></td>
		<td><hdiits:dateTime  validation="txt.isrequired" name="to_${counter}"  captionid="HRMS.todt" bundle="${constants}" onblur=""  afterDateSelect="changeColor(document.forms[0].to_${counter});"/>
		<c:set var="grpDates" value="to_${counter},${grpDates}"/>
		<script>
		toDateArr['${counter}']=document.forms[0].to_${counter};
		toDateStr+="to_${counter},";
		document.forms[0].to_${counter}.readOnly=true;
		</script>
	</td>
	<td align="left">
		<a href="#" tabindex="5" onclick="rowReset(${counter});" ><fmt:message bundle="${constants}" key="HRMS.reset"/></a>
	</td>
	
  </tr>
		<c:set var="counter" value="${counter+1}"/>

</c:forEach>
</table>
</hdiits:fieldGroup>
<table class="tabtable" width="100%">
  <tr align="centre">
	 <td align="center" colspan="8">
	  <hdiits:button type="button" captionid="HRMS.calculate" bundle="${constants}" id="calculate"  name="calculate" onclick="setTable(document.getElementById('GrpDetails'),'${counter}')" />
	  								<hdiits:button type="button" name="back"  value="Back" captionid="HRMS.back" tabindex="6" bundle="${commonLables}" onclick="goBackToGroupIns();"/>
	  <hdiits:button type="button" name="close" captionid="HRMS.close"  bundle="${constants}" onclick="goToHomePage();"/>

	 		<hdiits:resetbutton  title="Reset" value="Reset" name="Reset" type="button" tabindex="12"/>
	  <script>
	  var resetButton='<fmt:message key="HRMS.reset"/>';
		document.forms[0].Reset.value=resetButton;
	  </script>
	

	 </td>
  </tr>			
</table>

<div id="calDiv" style="display:none">			
<hdiits:fieldGroup bundle="${constants}"  expandable="true" titleCaptionId="HRMS.caldtls" id="calculations">
<table class="tabtable" id="calcDetail">
  <tbody id="calDetHead" >
		
	<tr id="caldtls"  >
		<td align="center" ><b><hdiits:caption bundle="${constants}" captionid="HRMS.group" /></b></td>
		<td align="center" ><b><hdiits:caption bundle="${constants}" captionid="HRMS.frmdt" /></b></td>
		<td align="center" ><b><hdiits:caption bundle="${constants}" captionid="HRMS.todt" /></b></td>
		<td align="center" ><b><hdiits:caption bundle="${constants}" captionid="HRMS.amt" /></b></td>
		<td align="center" ><b><hdiits:caption bundle="${constants}" captionid="HRMS.multifctr" /></b></td>
		<td align="center" ><b><hdiits:caption bundle="${constants}" captionid="HRMS.totamt" /></b></td>
	</tr>

  </tbody>

	<tbody id="GrpDetails">
	</tbody>
	
  <tbody>	
			<tr id="totamt">
		      <td colspan="4">
		      &nbsp;
		      </td>
		      <td  align="center">
		      
		      	<b>
		      		<hdiits:caption captionid="HRMS.grosamt" bundle="${constants}" id="totalamt" />:
		      	</b>
		      </td>
		      
		      
		      <td align="right">
		      		<hdiits:text readonly="true" captionid ="HRMS.grosamt" style="background-color:lightblue;text-align: right" name="grossAmt" id="grsamt" mandatory="false" validation="txt.isrequired"/>

				<hdiits:hidden caption="userid" name="userId" default="${userId}"/>
				<hdiits:hidden  name="status" caption="status" default="0" />
				<hdiits:hidden  name="appliedGroups" caption="appliedGroups" default="0" />

		      </td>  
		    </tr>
	</tbody>
</table>		
</hdiits:fieldGroup>
<table class="tabclass" width="100%">
		<tr>
			<td align="center" id="btn" colspan="8">
				<hdiits:button id="subButton" type="button" readonly="true" name="Submit" captionid="HRMS.submit" bundle="${constants}" onclick="SubmitRequest();" />
			</td>
		</tr>
</table>

</div>
<c:if test="${EmpDetVO.empName==''} ||  ${ EmpDetVO.designation==''} || ${EmpDetVO.salary=='0' } || ${EmpDetVO.doj==''} || ${EmpDetVO.dor==''}">
			<script>
				document.forms[0].Submit.disabled=true;
			</script>
		</c:if>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="${grpDates}"/>

</hdiits:form>
 
  <%
}
   	catch(Exception e)
   	{
		e.printStackTrace();
	}

%>
 
   		  
	 
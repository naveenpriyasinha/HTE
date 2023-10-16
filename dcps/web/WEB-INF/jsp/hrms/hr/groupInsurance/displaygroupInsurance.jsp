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
<c:set var="gp" value="${resValue.hrEmpGroupinsurance}"></c:set>


<script language="javascript">
function SubmitForm()
{
	document.groupInsuranceDisplay.submit();

}

</script>

<hdiits:form name="groupInsuranceDisplay"  validate="true" method="POST"  encType="multipart/form-data" >
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>

<table class="tabtable" border="1">

<tr bgcolor="#386CB7" id="Grpdtls" >
		 <td class="fieldLabel" colspan="8" align="center" >
		 <font color="#ffffff">
	<strong><u><fmt:message key="HRMS.grpdtls" /></u></strong>
	     </font>
		</td>
	</tr>

	
	<tr >
		<td bgcolor="lightblue" width="15%">
			<b><fmt:message key="HRMS.group" /></b>
		</td>

		<td bgcolor="lightblue" width="15%">
			<b><fmt:message key="HRMS.frmdt" /></b>
		</td>
		<td bgcolor="lightblue" width="15%">
			<b><fmt:message key="HRMS.todt" /></b>
		</td>
	
	<td bgcolor="lightblue" width="15%">
			<b><fmt:message key="HRMS.amt" /></b>
		</td>
	
		<td bgcolor="lightblue" width="15%">
			<b><fmt:message key="HRMS.multifctr" /></b>
		</td>
			<td bgcolor="lightblue" width="15%">
			<b><fmt:message key="HRMS.totamt" /></b>
			</td>
		</tr>
		
		
		<tr>
			<c:set var="counter" value="0"/>
			
			
				
					<c:forEach var="subGroup" items="${gp.hrGroupinsuranceDtlTxns}">	
						
						<tr>
							<td>
							${subGroup.cmnLookupMst.lookupDesc}
      						</td>
	
							<td>
							<fmt:formatDate value="${subGroup.fromDate}" pattern="dd/MM/yyyy" var="fromdate"/>
							${fromdate}
							</td>
	
							<td>
							<fmt:formatDate value="${subGroup.toDate}" pattern="dd/MM/yyyy" var="todate"/>
							${todate}
							</td>
							
							<td>
							<c:out value="${subGroup.amount}"/>
    						</td>
							
							<td>
							<c:out value="${subGroup.multiplyingFactor}"/>
    						</td>
						
							<td>
							<c:out value="${subGroup.totalAmt}"/>
    						</td>
						</tr>
						<c:set var="counter" value="${counter+1}"/>
					</c:forEach>
						<tr><td></td><td></td>
						<td>
						</td>
						<td>
						</td>
						
							<td align="right" bgcolor="lightblue">
								<b><fmt:message key="HRMS.grosamt" />:</b>
							</td>
							<td>
							<c:out value="${gp.grossAmt}"/>
	      					
	      					<hdiits:hidden  name="grpinsstatus" caption="status" default="0" />
	<hdiits:hidden  name="grpins_id" caption="status" default="${gp.grpId}" />
	      					
							</td>
						</tr>

	
<!--
	<tr>
 		<td colspan ="4" align= "center">
		 
			<input type="button" name="approve" value="Approve" onclick="document.groupInsuranceDisplay.grpinsstatus.value=1;SubmitForm();"/>
			<input type="button" name="reject" value="Reject" onclick="document.groupInsuranceDisplay.grpinsstatus.value=2;SubmitForm();"/>
			<input type="button" name="forward" value="Forward" onclick="SubmitForm();" />
			<input type="button" name="closepage" value="Close"/>
			
		
		</td> 
		<td>
	
		</td>
	 </tr>	-->
	
</table>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
 </hdiits:form>

  <%
}catch(Exception e){
	e.printStackTrace();
}

%>
	
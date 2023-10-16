<%try{ %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/hrms/ess/reimb/hr_remb_validations.js"></script>

<fmt:setBundle basename="resources.ess.reimb.reimbLables" var="enLables" scope="request"/>
<html>
<body>

<tr bgcolor="#386CB7">
		<td class="fieldLabel" align="center" colspan="9">
		<font color="#ffffff"><strong><u>					
		<fmt:message bundle="${enLables}" key="RM.electricity"/><fmt:message bundle="${enLables}" key="RM.Details"/>
		</u></strong></font></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.Fromdate" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="frmDate"  captionid="RM.Fromdate" bundle="${enLables}"/></td>
	
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.Todate" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="toDate"  captionid="RM.Todate" bundle="${enLables}"/></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.billDueDt" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="billDueDt" caption="RM.billDueDt" bundle="${enLables}"/></td>
	
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.meterNo" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="meterNo" captionid="RM.meterNo" bundle="${enLables}" mandatory="true" validation="txt.isrequired"/></td>
</tr>



<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.noOfUnit" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="noOfUnit" captionid="RM.noOfUnit" bundle="${enLables}" mandatory="true" validation="txt.isnumber,txt.isrequired" /></td>
	<td class="fieldLabel" width="25%"></td>
	<td class="fieldLabel" width="25%"></td>
</tr>








<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</body>
</html>

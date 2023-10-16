<%try{ %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/hrms/ess/reimb/hr_remb_validations.js"></script>

<fmt:setBundle basename="resources.ess.reimb.reimbLables" var="enLables" scope="request"/>
<c:set var="resultObj" value="${result}" ></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="contactTypeList" value="${resValue.contactTypeList}"></c:set>
<html>
<body>


<tr bgcolor="#386CB7">
		<td class="fieldLabel" align="center" colspan="9">
		<font color="#ffffff"><strong><u>					
		<fmt:message bundle="${enLables}" key="RM.telephone"/><fmt:message bundle="${enLables}" key="RM.Details"/>
		</u></strong></font></td>
</tr>
<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.contactType" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%">
		<hdiits:select name="contactType" id="contactType" captionid="RM.contactType" sort="false" bundle="${enLables}" mandatory="true" validation="sel.isrequired">
			<hdiits:option value="Select"><fmt:message bundle="${enLables}" key="RM.Select"/></hdiits:option>
			<c:forEach items="${contactTypeList}" var="contactTypes">
				<hdiits:option value="${contactTypes.lookupName}">${contactTypes.lookupDesc}</hdiits:option>
			</c:forEach>
		</hdiits:select>
	</td>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.contactNo" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="contactNo" captionid="RM.contactNo" bundle="${enLables}" mandatory="true" validation="txt.isnumber,txt.isrequired" /></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.billDueDt" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="billDueDt" caption="RM.billDueDt" bundle="${enLables}" /></td>
	
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.noOfCall" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="noOfCall" captionid="RM.noOfCall" bundle="${enLables}" mandatory="true" validation="txt.isnumber,txt.isrequired"/></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.Fromdate" bundle="${enLables}" /></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="frmDate"  captionid="RM.Fromdate" bundle="${enLables}" /></td>
	
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.Todate" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="toDate"  captionid="RM.Todate" bundle="${enLables}"/></td>
</tr>





<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</body>
</html>

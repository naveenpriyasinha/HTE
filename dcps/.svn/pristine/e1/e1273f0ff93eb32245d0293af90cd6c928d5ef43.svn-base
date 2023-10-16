<%
try {
%>
<c:set var="branch" value="${resValue.branch_name}"></c:set>
<c:set var="designations" value="${resValue.designations}"></c:set>
<c:set var="department" value="${resValue.department}"></c:set>
<c:set var="objHrEmpSusp" value="${resValue.objHrEmpSusp}"></c:set>
<table width="100%" id="SuspensionTab1" style="display:none">
	<tr>
		<td width=25%><hdiits:caption captionid="dept.inqCaseNo" bundle="${deptLables}"/> </td>
		<td width=25%><hdiits:number name="inq_text" onblur="restrictSplChar(this);" maxlength="15"></hdiits:number></td>
	    <td width=25%><hdiits:caption captionid="dept.appDt" bundle="${deptLables}"/> </td>
	    <td width=25%><hdiits:dateTime captionid="dept.appDt" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00" name="appdate" mandatory="true"/></td>
	</tr>
	
	
	<tr>
		<td width=25%><hdiits:caption captionid="dept.prelimReqd" bundle="${deptLables}"/></td>
		<td width=25%>
			<hdiits:radio name="isPrelimReq" value="Y" mandatory="false" bundle="${deptLables}" captionid="dept.y"/>
			<hdiits:radio name="isPrelimReq" value="N" mandatory="false" bundle="${deptLables}" captionid="dept.n"/>
		</td>
		
	</tr>
	<tr>
		<td width=25%><hdiits:caption captionid="dept.deptName" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:text name="departmentalname" onblur="restrictSplChar(this);" maxlength="20"></hdiits:text></td>
		<td width=25%><hdiits:caption captionid="dept.remarks" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:textarea name="remarks" onblur="restrictSplChar(this);" maxlength="100" rows="3" cols="25"></hdiits:textarea></td>
	</tr>
	<tr>
		<td width=25%><hdiits:caption captionid="dept.suspFromDt" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:dateTime captionid="dept.suspFromDt" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00" name="suspensFromdate" onblur="dateFromToValidateSusp();" mandatory="true"/></td>
	
		<td width=25%><hdiits:caption captionid="dept.suspToDt" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:dateTime captionid="dept.suspToDt" bundle="${deptLables}" name="suspensTodate" maxvalue="31/12/2099 00:00:00" onblur="dateFromToValidateSusp();" mandatory="true"/></td>
		
	</tr>
</table>

<hdiits:fieldGroup titleCaptionId="dept.suspOrderDtls" bundle="${deptLables}" id="SuspensionTab2">


<table width="100%"  id="SuspensionTab3" style="display:none">
	<tr>
		<td width=25%><hdiits:caption captionid="dept.nextIncDt" bundle="${deptLables}"/></td>
		<td width=25%>
			<hdiits:dateTime captionid="dept.nextIncDt" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00" name="incdate"/>
		</td>
	   <td width=25%><hdiits:caption captionid="dept.dept" bundle="${deptLables}"/>  
	   </td>
	   <td width=25%>
	   <hdiits:select name="depName" id="depName"  sort="false" mandatory="true" >
					<option value="Select" ><fmt:message key="dept.select"/></option>
						<c:forEach var="name" items="${department}">
		
						<c:choose>
							 <c:when test="${name.departmentId == objHrEmpSusp.orgDepartmentMst.departmentId}">
								<option value="${name.departmentId}" selected="selected">${name.depName}
    						 </c:when>
	   			
							 <c:otherwise>
								<option value="${name.departmentId}">${name.depName}
								</option>
	   						 </c:otherwise>
	   					</c:choose>
	    		</c:forEach>
			</hdiits:select>
		</td>
	</tr>
	
	<tr>
		<td width=25%> <hdiits:caption captionid="dept.div" bundle="${deptLables}"/> </td>
		<td width=25%><hdiits:text name="division" mandatory="true" onblur="restrictSplChar(this);" maxlength="20"></hdiits:text></td>
		<td width=25%> <hdiits:caption captionid="dept.branch" bundle="${deptLables}"/> </td>
		<td width=25%>
		   <hdiits:select name="branchSusp"  mandatory="true"  sort="false" >
					<option value="Select" ><fmt:message key="dept.select"/></option>
						<c:forEach var="name" items="${branch}">
		
						<c:choose>
						 <c:when test="${name.branchId == objHrEmpSusp.cmnBranchMst.branchId}">
						 <option value="${name.branchId}"  selected="selected">${name.branchName}</option>
    					 </c:when>
	   			
						 <c:otherwise>
						<option value="${name.branchId}">${name.branchName}</option>
	   					 </c:otherwise>
	   					</c:choose>
	    											
						</c:forEach>
			</hdiits:select>
		</td>
	</tr>

	<tr>
		<td width=25%><hdiits:caption captionid="dept.suspOrderNo" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:number name="suspOrderNo" onblur="checkIsNumber(this)" maxlength="15"></hdiits:number></td>
		<td width=25%><hdiits:caption captionid="dept.suspOrderDt" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:dateTime captionid="dept.suspOrderDt" bundle="${deptLables}" name="orderdate" maxvalue="31/12/2099 00:00:00"/></td>
	</tr>

	<tr>
		<td width=25%><hdiits:caption captionid="dept.susOrderDtls" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:textarea name="suspdetails" onblur="restrictSplChar(this);" maxlength="200" rows="3" cols="25"></hdiits:textarea></td>
		<td width=25%><hdiits:caption captionid="dept.suspAll" bundle="${deptLables}"/></td>
		<td width=25%><hdiits:number name="suspallAllow" onblur="checkIsNumber(this);"  maxlength="8" size="15" mandatory="true"></hdiits:number></td>
	</tr>
</table>
</hdiits:fieldGroup>

<script>
hideSuspDtls();
defaultSuspValues();
makeSuspensionDtReadOnly();
	
	function defaultSuspValues()
	{
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSusp.nextIncrementDate}" var="incdate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSusp.suspenFromDate}" var="fromdate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSusp.suspenToDate}" var="todate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSusp.suspOrderDt}" var="suspOrderdate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSusp.appDate}" var="suspAppDate"/>
	
	document.inquiryCaseTracking.suspallAllow.value="${objHrEmpSusp.suspenAllowance}";
	document.inquiryCaseTracking.inq_text.value="${objHrEmpSusp.enqCaseNo}";
	document.inquiryCaseTracking.suspdetails.value="${objHrEmpSusp.suspensionDtl}";
	document.inquiryCaseTracking.suspensFromdate.value="${fromdate}";
	document.inquiryCaseTracking.suspensTodate.value="${todate}";
	document.inquiryCaseTracking.suspOrderNo.value="${objHrEmpSusp.suspenOrderNo}";
	document.inquiryCaseTracking.division.value="${objHrEmpSusp.division}";
	document.inquiryCaseTracking.remarks.value="${objHrEmpSusp.remarks}";
	document.inquiryCaseTracking.departmentalname.value="${objHrEmpSusp.departmentalName}";
	document.inquiryCaseTracking.incdate.value="${incdate}";
	document.inquiryCaseTracking.appdate.value="${suspAppDate}";
	document.inquiryCaseTracking.orderdate.value="${suspOrderdate}";
	
	if("${objHrEmpSusp.isPrelimReq}"=='Y')
		{
		document.inquiryCaseTracking.isPrelimReq[0].checked=true;
		}
	else if ("${objHrEmpSusp.isPrelimReq}"=='N')
		{
		document.inquiryCaseTracking.isPrelimReq[1].checked=true;
		}
	}
	

	
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
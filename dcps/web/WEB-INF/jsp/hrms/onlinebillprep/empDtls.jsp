<%@ include file="../../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript">
		var EMP_TXTEMPNAME = "<fmt:message key="EMP.TXTEMPNAME" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var EMP_TXTEMPDESG = "<fmt:message key="EMP.TXTEMPDESG" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var EMP_TXTESTB = "<fmt:message key="EMP.TXTESTB" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var EMP_CMBMONTH = "<fmt:message key="EMP.CMBMONTH" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var EMP_CMBEMPTYPE = "<fmt:message key="EMP.CMBEMPTYPE" bundle="${onlinebillprepAlerts}"></fmt:message>";
		
		function validateEmpDtls()
		{
			return isValidateEmpDtls();
		}
	</script>	
</head>

	<c:if test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
		<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
		<c:set var="varEmpType" scope="page" value="${resValue.Selected_EmpType.Value}"></c:set> 		
	</c:if>
	<c:if test="${resValue.EditBill != null && resValue.EditBill == 'Y'}">
		<c:set var="varEmpType" scope="page" value="${resValue.Selected_EmpType.Value}"></c:set> 
	</c:if>
			
	<c:choose>
		<c:when test="${resValue.TrnBillRegister != null}">
			<c:set var="varEmpName" scope="page" value="${resValue.EmpDtlsList[0].empName}"></c:set> 
			<c:set var="varEmpDesgnEn" scope="page" value="${resValue.EmpDtlsList[0].empDesgn}"></c:set> 
			<c:set var="varEmpEstblsmnt" scope="page" value="${resValue.EmpDtlsList[0].deptName}"></c:set> 
		</c:when>
		<c:otherwise>
			<c:set var="varEmpName" scope="page" value="${resValue.AprvRqst.empNameEn}"></c:set> 
			<c:set var="varEmpDesgnEn" scope="page" value="${resValue.AprvRqst.empDesgnEn}"></c:set> 
			<c:set var="varEmpEstblsmnt" scope="page" value="${resValue.DeptVO.deptName}"></c:set> 
			<c:set var="varEmpType" scope="page" value="${resValue.EmpTypeSelectedByUser[0]}"></c:set> 
		</c:otherwise>
	</c:choose>	
		
	<table align="center" width="90%" class="groupTable">
		<hdiits:tr>
			<td align="left" class="Label">
				<br /> <fmt:message key="EMP.NAME" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left">
				<br /> :&nbsp;&nbsp;<input type="text" name="txtEmpName" onblur="setIncumbentName()" ${varDisabled} value="${varEmpName}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
									<input type="hidden" name="digi8_0_EMP_NAME"/>	
			</hdiits:td>
			<td align="left" class="Label">
				<fmt:message key="EMP.DESIGNATION" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left">
				:&nbsp;&nbsp;<input type="text" name="txtEmpDsg" ${varDisabled} value="${varEmpDesgnEn}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
							 <input type="hidden" name="digi8_0_EMP_DESGN" />	
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="left" class="Label">
				<fmt:message key="EMP.ESTABLISHMENT" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left">
				:&nbsp;&nbsp;<input type="text" name="txtEmpEstblsmnt" onblur="setEstablishment()" ${varDisabled} value="${varEmpEstblsmnt}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
							<input type="hidden" name="digi8_0_DEPT_NAME"/>
			</hdiits:td>
			<td align="left" class="Label">
				<fmt:message key="EMP.MONTH" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left">
				 :&nbsp;&nbsp;<select name="cmbForMonth" style="width:155px" ${varDisabled}>
				 	<option value="-1"> --Select-- </option>
					<c:forEach var="CommVO" items="${resValue.MonthList}">						
					  	<c:if test="${CommVO.commonId == resValue.EmpDtlsList[0].monthCode}">
							<option selected="selected" value="${CommVO.commonId}"> <c:out value="${CommVO.commonDesc}"></c:out> </option>
						</c:if>
						<c:if test="${CommVO.commonId != resValue.EmpDtlsList[0].monthCode}">
							<option value="${CommVO.commonId}"> <c:out value="${CommVO.commonDesc}"></c:out> </option>
						</c:if>
					</c:forEach>
				 </select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				 <input type="hidden" name="digi8_0_MONTH_CODE" />
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="left" class="Label">
				<fmt:message key="CMN.EMP.TYPE" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left">
				:&nbsp;&nbsp;<select name="cmbEmpType" style="width:155px">
			 		<option value="-1"> --Select-- </option>
		 			<c:forEach var="empType" items="${resValue.EmpType}" varStatus="Counter">
				    	<c:if test="${varEmpType == empType.lookupDesc}">
        					<option value="${empType.lookupName}" selected="selected"> <c:out value="${empType.lookupDesc}"></c:out> </option>
						</c:if>
						<c:if test="${varEmpType != empType.lookupDesc}">
							<option value="${empType.lookupName}"> <c:out value="${empType.lookupDesc}"></c:out> </option>
			        	</c:if>
					</c:forEach>
				 </select>
			</hdiits:td>
			<hdiits:td></hdiits:td>
			<hdiits:td></hdiits:td>
		</hdiits:tr>
	</table>	
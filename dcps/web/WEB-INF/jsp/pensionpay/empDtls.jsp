<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>
	
<c:set var="varReadonly" scope="page" value="disabled='disabled'"></c:set>
<c:if test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varEmpType" scope="page" value="${resValue.Selected_EmpType.Value}"></c:set> 		
</c:if>
<c:if test="${resValue.EditBill != null && resValue.EditBill == 'Y'}">
	<c:set var="varEmpType" scope="page" value="${resValue.Selected_EmpType.Value}"></c:set> 
</c:if>

<c:set var="varEmpName" scope="page" value="${resValue.EmpName}"></c:set> 
<c:set var="varEmpDesgnEn" scope="page" value="${resValue.EmpDesgn}"></c:set> 
<c:set var="varEmpEstblsmnt" scope="page" value="${resValue.DeptName}"></c:set>

<c:if test="${resValue.EmpDtlsList != null}">
	<c:set var="varEmpName" scope="page" value="${resValue.EmpDtlsList[0].empName}"></c:set> 
	<c:set var="varEmpDesgnEn" scope="page" value="${resValue.EmpDtlsList[0].empDesgn}"></c:set> 
	<c:set var="varEmpEstblsmnt" scope="page" value="${resValue.EmpDtlsList[0].deptName}"></c:set> 
	<c:set var="varEmpMonthCode" scope="page" value="${resValue.EmpDtlsList[0].monthCode}"></c:set>
</c:if>

<c:if test="${resValue.EmpDtlsVO != null}">
	<c:set var="varEmpName" scope="page" value="${resValue.EmpDtlsVO.empName}"></c:set> 
	<c:set var="varEmpDesgnEn" scope="page" value="${resValue.EmpDtlsVO.empDesgn}"></c:set> 
	<c:set var="varEmpEstblsmnt" scope="page" value="${resValue.EmpDtlsVO.deptName}"></c:set> 
	<c:set var="varEmpMonthCode" scope="page" value="${resValue.EmpDtlsVO.monthCode}"></c:set>
</c:if>

<input type="hidden" name="hidPnsnDDOCode" value="${resValue.DDOCode}" />
	
<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> <fmt:message key="EMP.HEADING" bundle="${onlinebillprepLabels}"></fmt:message> </b> </legend>
	<table align="center" width="98%" >
		<hdiits:tr>
			<td align="left" class="Label" width="20%">
				<fmt:message key="EMP.NAME" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left" width="25%">
				<input type="text" name="txtEmpName" tabindex="4" size="22" ${varReadonly} value="${varEmpName}" />
			</hdiits:td>
			<td align="left" class="Label" width="20%">
				<fmt:message key="EMP.DESIGNATION" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left" width="25%">
				<input type="text" name="txtEmpDsg" tabindex="5" ${varReadonly} value="${varEmpDesgnEn}" />
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="left" class="Label">
				<fmt:message key="EMP.ESTABLISHMENT" bundle="${onlinebillprepLabels}"></fmt:message>
			</td>
			<hdiits:td align="left">
				<textarea name="txtEmpEstblsmnt"  rows="3" cols="19" ${varReadonly} ${varDisabled}><c:out value="${varEmpEstblsmnt}"></c:out> </textarea>
			</hdiits:td>
			<td align="left">
				<table>
					<tr>
						<td align="left" class="Label">
							<fmt:message key="CMN.EMP.TYPE" bundle="${onlinebillprepLabels}"></fmt:message>
						</td>
					</tr>
					<tr>
						<td align="left" class="Label">
							<c:if test="${resValue.subjectId == 9 || resValue.subjectId == 44}" >
							<fmt:message key="EMP.MONTH" bundle="${onlinebillprepLabels}"></fmt:message>
							</c:if>
						</td>
					</tr>
				</table>	
			</td>	
			<td align="left">
				<table>
					<tr>
						<hdiits:td align="left">
							<!-- 
							<select name="cmbEmpType" style="width:155px" tabindex="7" ${varDisabled}>
						 		<option value="-1"> --Select-- </option>
					 			<c:forEach var="empType" items="${resValue.EmpType}" varStatus="Counter">
							    	<c:if test="${varEmpType == empType.lookupShortName}">
			        					<option value="${empType.lookupShortName}" selected="selected"> <c:out value="${empType.lookupDesc}"></c:out> </option>
									</c:if>
									<c:if test="${varEmpType != empType.lookupShortName}">
										<option value="${empType.lookupShortName}"> <c:out value="${empType.lookupDesc}"></c:out> </option>
						        	</c:if>
								</c:forEach>
							 </select>
							  -->
						</hdiits:td>
					</tr>
					<tr>
						<hdiits:td align="left">
							<c:if test="${resValue.subjectId == 9 || resValue.subjectId == 44}" >
							 <select name="cmbForMonth" style="width:155px" tabindex="8" ${varDisabled}>
							 	<option value="-1"> --Select-- </option>
								<c:forEach var="CommVO" items="${resValue.MonthList}">						
								  	<c:if test="${CommVO.commonId == varEmpMonthCode}">
										<option selected="selected" value="${CommVO.commonId}"> <c:out value="${CommVO.commonDesc}"></c:out> </option>
									</c:if>
									<c:if test="${CommVO.commonId != varEmpMonthCode}">
										<option value="${CommVO.commonId}"> <c:out value="${CommVO.commonDesc}"></c:out> </option>
									</c:if>
								</c:forEach>
							</select>
							</c:if>
						</hdiits:td>
					</tr>
				</table>
			</td>	
		</hdiits:tr>
	</table>	
</fieldset>
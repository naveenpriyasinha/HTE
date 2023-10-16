<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="empInfo" value="${resValue.empInfo}"></c:set>
		<hdiits:fieldGroup collapseOnLoad="false" id="acr_file_field1" titleCaptionId="HR.ACR.EmpInfo" bundle="${commonLables}">					 				
			<table id="empTable" width="100%">
				<tr>
					<td width="25%">
						<b><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/>:</b>
					</td>
					<td width="25%">
						<c:out value="${empInfo.name}"/>
					</td>								
					<td width="25%">
						<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/>:</b>
					</td>
					<td width="25%">
						<c:out value="${empInfo.dsgnName}"/>
					</td>					
				</tr>
				<tr>
					<td width="25%">
						<b><hdiits:caption captionid="HR.ACR.DateofJoining" bundle="${commonLables}"/>:</b>
					</td>
					<td width="25%">
						<c:out value="${empInfo.joiningDate}"/>
					</td>									
					<td width="25%">
						<b><hdiits:caption captionid="HR.ACR.DateofRetirement" bundle="${commonLables}"/>:</b>
					</td>
					<td width="25%">
						<c:out value="${empInfo.retirementDate}"/>
					</td>					
				</tr>
				<tr>
					<td width="25%">
						<b><hdiits:caption captionid="HR.ACR.Basic" bundle="${commonLables}"/>:</b>
					</td>
					<td width="25%">
						<c:out value="${empInfo.basic}"/>
					</td>
					<td width="25%">
						<b><hdiits:caption captionid="HR.ACR.Address" bundle="${commonLables}"/>:</b>
					</td>
					<td width="25%">
						<c:out value="${empInfo.city}"/>
					</td>
				</tr>
				<tr colspan="3">
					<td width="25%">
						<b><hdiits:caption captionid="HR.ACR.EmployeeNo" bundle="${commonLables}"/>:</b>
					</td>
					<td width="25%">
						<c:out value="${empInfo.empNo}"/>
					</td>	
					<td width="25%"></td>
					<td width="25%"></td>					
				</tr>
			</table>
		</hdiits:fieldGroup>

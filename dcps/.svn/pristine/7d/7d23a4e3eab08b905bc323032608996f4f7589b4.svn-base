<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="resources.eis.empPreEmplLables" var="professionalTabcommonLables" scope="request"/>
<fmt:setBundle basename="resources.hr.posting.postingLabels" var="EmpAdditionalChargesLables" scope="request" />
<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="empPayfixationLables" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables" var="empSearchLables" scope="request" />

<script type="text/javascript">

		function openAppWindow(actionFlag)//IFMS
		{
				var userId = document.getElementById("userId").value;
				var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId ;
				//objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
				window.location=href;
		}
		
</script>

<c:set value="${fn:startsWith(strAppType,'PROF-')}" var="tabCompare"/>
	
	<ul id="maintab" class="shadetabs">
	
		<c:if test="${tabCompare eq true}">
				<li class="selected" style="width: 170px;">
					<c:choose>
						<c:when test="${strAppType eq 'PROF-PRE-EMPLOYEMENT'}">
							<a href="#"  style="width: 160px;" rel="tcontent1">
						</c:when>	
						<c:otherwise>		
							<a href="#"  style="width: 160px;" onclick="javascript:openAppWindow('getEmpPreEmplDtls&workFlowEnabled=false')">
						</c:otherwise>	
					</c:choose>	
						<b>	<fmt:message key="EIS.EmplProfDtls" bundle="${professionalTabcommonLables}"></fmt:message></b>
					</a>
				</li>
				<li class="selected" style="width: 280px;">
					<c:choose>
						<c:when test="${strAppType eq 'PROF-SERVICE-EXAM'}">
							<a href="#" style="width: 270px;" rel="tcontent1">
						</c:when>	
						<c:otherwise>	
							<a href="#" style="width: 270px;" onclick="javascript:openAppWindow('getEmpExamDetails&workFlowEnabled=false')" >
						</c:otherwise>	
					</c:choose>		
						<b><fmt:message key="EIS.ExamnatnDetl" bundle="${professionalTabcommonLables}"/></b>
					</a>
				</li>
		</c:if>		
		
		<c:if test="${tabCompare eq false}">
			<c:choose>
				<c:when test="${selectedUserId eq '' or selectedUserId eq '0'}">
					<ul id="maintab" class="shadetabs">
						<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="EIS.NewUserCreation" bundle="${empSearchLables}"/></a></li>
					</ul>
				</c:when>	
				<c:otherwise>	
					<li class="selected">
						<c:choose>
							<c:when test="${strAppType eq 'SRVC-JOINING'}">
								<a href="#" rel="tcontent1">
							</c:when>	
							<c:otherwise>	
								<a href="#" onclick="javascript:openAppWindow('showEmpBasicInfo&workFlowEnabled=false')" >
							</c:otherwise>	
						</c:choose>	
						<b><fmt:message key="Join_Details" bundle="${empSearchLables}"/></b>
						</a>
					</li>
					<li class="selected" style="width: 250px;">
						<c:choose>
							<c:when test="${strAppType eq 'SRVC-POSTING'}">
								<a href="#" style="width: 240px;" rel="tcontent1">
							</c:when>	
							<c:otherwise>	
								<a href="#" style="width: 240px;" onclick="javascript:openAppWindow('getEmpPrePstngDtls&workFlowEnabled=false')" target="_self">
							</c:otherwise>	
						</c:choose>
							<b><fmt:message	key="EIS.PrsntEmplmntPosngDtls" bundle="${EmpAdditionalChargesLables}" /></b>
						</a>
					</li>
					<li class="selected">
						<c:choose>
							<c:when test="${strAppType eq 'SRVC-ADDCHARGE'}">
								<a href="#" rel="tcontent1">
							</c:when>	
							<c:otherwise>	
								<a href="#" onclick="javascript:openAppWindow('getEmpAdditnalChrgDtls&workFlowEnabled=false')" >
							</c:otherwise>	
						</c:choose>
							<b><fmt:message key="EIS.AdditionalCharge" bundle="${empSearchLables}"/></b>
						</a>
					</li>
					<li class="selected">
						<c:choose>
							<c:when test="${strAppType eq 'SRVC-PAYFIX'}">
								<a href="#" rel="tcontent1">
							</c:when>	
							<c:otherwise>	
								<a href="#" onclick="javascript:openAppWindow('showEmpPayfixation&workFlowEnabled=false')">
							</c:otherwise>	
						</c:choose>
							<b><fmt:message key="EMP_PAYFIXATION" bundle="${empPayfixationLables}"/></b>
						</a>
					</li>
				</c:otherwise>	
			</c:choose>			
		</c:if>
	</ul>

<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.foreigntourtravel.foreigntourtravel" var="commonLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="trueReadOnly" value='readonly="true"' />
<c:set var="expIncuredByList" value="${resValue.expIncuredByList}" />
<c:set var="conveyanceModeList" value="${resValue.conveyanceModeList}" />
<c:set var="countryList" value="${resValue.countryList}" />
<c:set var="userId" value="${resValue.userId}" />
<c:set var="currentDate" value="${resValue.currentDate}" />
<c:set var="dAForCountry" value="${resValue.dAForCountry}" />
<c:set var="dAForCountryPerDay" value="${resValue.dAForCountryPerDay}" />
<c:set var="country" value="${resValue.country}" />
<c:set var="countryId" value="${resValue.countryId}" />
<c:set var="expIncurredBy" value="${resValue.expIncurredBy}" />
<c:set var="expIncurredById" value="${resValue.expIncurredById}" />
<c:set var="conveyanceMode" value="${resValue.conveyanceMode}" />
<c:set var="conveyanceModeId" value="${resValue.conveyanceModeId}" />

<c:set var="hrFtourReqDtl" value="${resValue.hrFtourReqDtl}" />
<c:set var="dateDiff" value="${resValue.dateDiff}" />
<c:set var="totDailyAll" value="${resValue.totDailyAll}" />
<c:set var="rateOfDollar" value="${resValue.rateOfDollar}" />

<c:set var="accUserInfo" value="${resValue.memDtlList}" />



<hdiits:form name="foreignTourtravel" validate="true" id="foreignTourtravel"
	action="./hrms.htm?actionFlag=updateFttReq" method="post">

	<br>

	<table class="tabtable" name="travelRequest" id="travelRequest">
<hdiits:hidden name="fttstatus" id="fttstatus" caption="status" default="0" />

<hdiits:hidden name="fileId" id="fileId" caption="status" default="${hrFtourReqDtl.tourId}" />

		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center" width="25%"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.tourdtl" /></u></strong> </font></td>
		</tr>

		<tr>
			<td width="25%"><b><fmt:message key="HRMS.tourname" />&nbsp;:</b></td>

			<td width="25%">${hrFtourReqDtl.tourName}</td>

			<td width="25%"><b><fmt:message key="HRMS.Reffileno" />&nbsp;:</b>
			</td>

			<td width="25%">
			<c:if test="${empty hrFtourReqDtl.refFileNo}">
			----------
			</c:if>
			<c:if test="${not empty hrFtourReqDtl.refFileNo}">
			${hrFtourReqDtl.refFileNo}
			</c:if>
			</td>
		</tr>

		<tr>
			<td width="25%"><b><fmt:message key="HRMS.purpose" /></b></td>

			<td width="25%"><hdiits:textarea mandatory="true" rows="2"
				cols="30" name="purpose" tabindex="3" id="purpose" readonly="true"
				captionid="HRMS.purpose" maxlength="2000" bundle="${commonLables}" default="${hrFtourReqDtl.purpose}"/></td>

			<td><b><fmt:message key="HRMS.sendorgdtl" />&nbsp;:</b></td>

			<td>
			<c:if test="${empty hrFtourReqDtl.sendOrgDtl}">
			----------
			</c:if>
			<c:if test="${not empty hrFtourReqDtl.sendOrgDtl}">
			${hrFtourReqDtl.sendOrgDtl}
			</c:if>
			</td>
		</tr>

		<tr>
			<td align="left" width="13%"><b><fmt:message
				key="HRMS.expenditure" /></b><hdiits:hidden name="expIncurredById" id="expIncurredById" default="${expIncurredById}"/></td>

			<td><hdiits:hidden name="expenditure" default="${expIncurredById}"	/>
				${expIncurredBy}
			</td>
		</tr>

		
		<tr>
		
		
			<td><b><fmt:message key="HRMS.embassy" /></b></td>
				<c:if test="${hrFtourReqDtl.indEmbCareFlag =='0'}">
				<td><b><fmt:message key="HRMS.no" /></b></td>
		  </c:if>
	  		<c:if test="${hrFtourReqDtl.indEmbCareFlag =='1'}">
	  			<td>
				<b><fmt:message key="HRMS.yes" />
				</td>
	  		</c:if>
		
		</tr>

	  
<tr>
			<td><b><fmt:message key="HRMS.invitation" /></b></td>
			
			<c:if test="${hrFtourReqDtl.invRecFlag =='0'}">
	 		<td>
			<b><fmt:message key="HRMS.no" /></b>
			</td>
	  </c:if>
	  		<c:if test="${hrFtourReqDtl.invRecFlag =='1'}">
	 		<td>
	  		<b><fmt:message key="HRMS.yes" /></b>
			
			</td>
	  </c:if>
			
		</tr>

		<tr>
			<td><b><fmt:message key="HRMS.visited" /></b></td>
			<c:if test="${hrFtourReqDtl.visitedPlaceFlag =='0'}">
			 <td>
			 	<b><fmt:message key="HRMS.no" /></b>
			 </td>
		  </c:if>
	  <c:if test="${hrFtourReqDtl.visitedPlaceFlag =='1'}">
	  	<td>
			<b><fmt:message	key="HRMS.yes" /></b>
		</td>
	  </c:if>
			
		</tr>

		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.journeydtl" /></u></strong> </font></td>
		</tr>

		<tr>
			<td align="left" width="13%"><b><fmt:message
				key="HRMS.country" /></b><hdiits:hidden name="countryId" id="countryId" default="${countryId}"/>
				</td>

			<td>
			<hdiits:hidden name="country" id="country" default="${countryId}" />
			${country}
			</td>

			<td><b><fmt:message key="HRMS.city" />&nbsp;:</b></td>

			<td>
			<c:if test="${empty hrFtourReqDtl.city}">
			----------
			</c:if>
			<c:if test="${not empty hrFtourReqDtl.city}">
			${hrFtourReqDtl.city}
			</c:if>
			</td>
		</tr>

		<tr>
			<td><b><fmt:message key="HRMS.dateofdep" /></b></td>
			<td><hdiits:hidden name="dateFlag" id="dateFlag" />
			
			<fmt:formatDate value="${hrFtourReqDtl.dateOfDep}" pattern="dd/MM/yyyy" var="dateOfDep"/>
			${dateOfDep}
			
			 </td>

			<td><b><fmt:message key="HRMS.retdt" /></b></td>
			<td>
			
			<fmt:formatDate value="${hrFtourReqDtl.dateOfRet}" pattern="dd/MM/yyyy" var="dateOfRet"/>
			${dateOfRet}
			</td>
		</tr>

		<tr>
			<td><b><fmt:message key="HRMS.staydur" />$nbsp;:</b></td>
			<td>${dateDiff}</td>

			<td align="left" width="13%">
			<b><fmt:message	key="HRMS.convmode" /></b><hdiits:hidden name="conveyanceModeId" id="conveyanceModeId" default="${conveyanceModeId}"/></td>

			<td>
			<hdiits:hidden name="convMode" id="convMode" default="${conveyanceModeId}" />
			${conveyanceMode}
			</td>
		</tr>

	<tr>
			<td><b><fmt:message	key="HRMS.totdailyallInDollar" />:</b></td>
			<td>${dAForCountryPerDay}</td>
		</tr>





		<tr>
	<td><b><fmt:message key="HRMS.totdailyall" />&nbsp;:</b></td>
	<td>${dAForCountry}
		</td>	
			
			<td><b><fmt:message	key="HRMS.entall" />&nbsp;:</b></td>

			<td>
			<c:if test="${empty hrFtourReqDtl.entAll}">
			----------
			</c:if>
			<c:if test="${not empty hrFtourReqDtl.entAll}">
			${hrFtourReqDtl.entAll/rateOfDollar}
			</c:if>
			</td>
			</tr>

		<tr>
			<td><b><fmt:message	key="HRMS.rateofdol" />&nbsp;:</b></td>
			<td>
			
			${rateOfDollar}</td>
		</tr>

		<tr>
			<td><b><fmt:message	key="HRMS.totdailyallrs" />&nbsp;:</b></td>
			<td>${hrFtourReqDtl.dailyAll}</td>

			<td><b><fmt:message	key="HRMS.entallrs" />&nbsp;:</b></td>
			<td>
			<c:if test="${empty hrFtourReqDtl.entAll}">
			----------
			</c:if>
			<c:if test="${not empty hrFtourReqDtl.entAll}">
			${hrFtourReqDtl.entAll}
			</c:if>
			</td>
		</tr>

		<c:if test="${not empty hrFtourReqDtl.hrForeigntourmemTxns}">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.membdtl" /></u></strong> </font></td>
		</tr>
		
		<tr id="travelTable" colspan="8">
			<td colspan="4" align="center">
			
			<table id="travelTableData" border="1"
				cellpadding="0" cellspacing="0" BGCOLOR="WHITE" width="80%" align="center">
				<tr colspan="4">
					
					<td align="center" width="8%"><b><fmt:message
						key="HRMS.srno" /></b></td>
					<td align="center" width="15%"><b><fmt:message
						key="HRMS.name" /></b></td>
					<td align="center" width="15%"><b><fmt:message
						key="HRMS.dept" /></b></td>
					<td align="center" width="15%"><b><fmt:message
						key="HRMS.desig" /></b></td>
					
				</tr>
				<c:set var="srNo" value="1" />
				
				<c:forEach var="accUserInfo" items="${accUserInfo}">
				<tr>
				
					<td>
						${srNo}
					</td>
					
					<td>
						${accUserInfo.empName}
					</td>
					
					<td>
						 ${accUserInfo.departmentName} 
					</td>
					
					<td>
						${accUserInfo.designationName}
					</td>
					
				</tr>
				<c:set var="srNo" value="${srNo+1}" />
				</c:forEach>
				</table>
				
			</td>
		</tr>
		</c:if>
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff"> 
			<strong><u><fmt:message	key="HRMS.addremark" /></u></strong> </font></td>
		</tr>

		<tr>
		
			<c:if test="${hrFtourReqDtl.visitedPlaceFlag =='1'}">
			<td id="prevDtlBlack" ><b><fmt:message key="HRMS.prevdtl"></fmt:message></b></td>
			<td style="display:" id="prevDtlTD"><hdiits:textarea mandatory="false" rows="2" cols="30" name="prevDtl" tabindex="7" id="prevDtl" 
				caption="prevdtl" readonly="true" default="${hrFtourReqDtl.dtlsPrevVisit}" /></td>
			</c:if>
			
			
			<td><b><fmt:message key="HRMS.reason"></fmt:message></b></td>
			<hdiits:hidden name="userId" default="${userId}" />
			
			<td><hdiits:textarea mandatory="true" rows="2" cols="30"
				name="reason" tabindex="23" id="reason" validation="txt.isrequired"
				captionid="HRMS.reason" bundle="${commonLables}" readonly="true" default="${hrFtourReqDtl.whyVisitInevitable}"/></td>
					
				
		</tr>
</table>


<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
						
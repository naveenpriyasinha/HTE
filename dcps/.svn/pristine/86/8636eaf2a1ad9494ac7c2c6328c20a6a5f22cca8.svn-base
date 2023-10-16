<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
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
<fmt:setBundle basename="resources.ess.foreigntourtravel.foreigntourtravel" var="commonLables" scope="request" />

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
<c:set var="reportView" value="${resValue.reportView}" />


<hdiits:form name="foreignTourtravel" validate="true" id="foreignTourtravel"
	 method="post">

<hdiits:fieldGroup bundle="${commonLables}"  id="TourDetails" expandable="true" titleCaptionId="HRMS.tourdtl">
<table class="tabtable" name="travelRequest" id="travelRequest">
<hdiits:hidden name="fttstatus" id="fttstatus" caption="status" default="0"/>

<hdiits:hidden name="fileId" id="fileId" caption="status" default="${hrFtourReqDtl.tourId}" />

		<tr>
			<td width="25%"><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.tourname" />&nbsp;:</b></td>

			<td width="25%">${hrFtourReqDtl.tourName}</td>

			<td width="25%"><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.Reffileno" />&nbsp;:</b>
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
			<td width="25%"><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.purpose" /></b></td>

			<td width="25%"><hdiits:textarea mandatory="true" rows="2"
				cols="30" name="purpose" tabindex="3" id="purpose" readonly="true"
				captionid="HRMS.purpose" maxlength="2000" bundle="${commonLables}" default="${hrFtourReqDtl.purpose}"/></td>

			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.sendorgdtl" />&nbsp;:</b></td>

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
			<td align="left" width="13%"><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.expenditure" /></b><hdiits:hidden name="expIncurredById" id="expIncurredById" default="${expIncurredById}"/></td>

			<td><hdiits:hidden name="expenditure" default="${expIncurredById}"	/>
				${expIncurredBy}
			</td>
		</tr>

		
		<tr>
		
		
			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.embassy" /></b></td>
				<c:if test="${hrFtourReqDtl.indEmbCareFlag =='0'}">
				<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.no" /></b></td>
		  </c:if>
	  		<c:if test="${hrFtourReqDtl.indEmbCareFlag =='1'}">
	  			<td>
				<b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.yes" />
				</b></td>
	  		</c:if>
		
		</tr>

	  
<tr>
			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.invitation" /></b></td>
			
			<c:if test="${hrFtourReqDtl.invRecFlag =='0'}">
	 		<td>
			<b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.no" /></b>
			</td>
	  </c:if>
	  		<c:if test="${hrFtourReqDtl.invRecFlag =='1'}">
	 		<td>
	  		<b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.yes" /></b>
			
			</td>
	  </c:if>
			
		</tr>

		<tr>
			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.visited" /></b></td>
			<c:if test="${hrFtourReqDtl.visitedPlaceFlag =='0'}">
			 <td>
			 	<b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.no" /></b>
			 </td>
		  </c:if>
	  <c:if test="${hrFtourReqDtl.visitedPlaceFlag =='1'}">
	  	<td>
			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.yes" /></b>
		</td>
	  </c:if>
			
		</tr>
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${commonLables}"  id="JourneyDetails" expandable="true" titleCaptionId="HRMS.journeydtl">
<table class="tabtable">
		<tr>
			<td align="left" width="13%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.country" /></b><hdiits:hidden name="countryId" id="countryId" default="${countryId}"/>
				</td>

			<td>
			<hdiits:hidden name="country" id="country" default="${countryId}" />
			${country}
			</td>

			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.city" />&nbsp;:</b></td>

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
			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.dateofdep" /></b></td>
			<td><hdiits:hidden name="dateFlag" id="dateFlag" />
			
			<fmt:formatDate value="${hrFtourReqDtl.dateOfDep}" pattern="dd/MM/yyyy" var="dateOfDep"/>
			${dateOfDep}
			
			 </td>

			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.retdt" /></b></td>
			<td>
			
			<fmt:formatDate value="${hrFtourReqDtl.dateOfRet}" pattern="dd/MM/yyyy" var="dateOfRet"/>
			${dateOfRet}
			</td>
		</tr>

		<tr>
			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.staydur" />&nbsp;:</b></td>
			<td>${dateDiff}</td>

			<td align="left" width="13%">
			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.convmode" /></b><hdiits:hidden name="conveyanceModeId" id="conveyanceModeId" default="${conveyanceModeId}"/></td>

			<td>
			<hdiits:hidden name="convMode" id="convMode" default="${conveyanceModeId}" />
			${conveyanceMode}
			</td>
		</tr>

	<tr>
			<td><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.totdailyallInRs" />:</b></td>
			<td>${dAForCountryPerDay}</td>
		</tr>





		<tr>
	<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.totdailyallrs" />&nbsp;:</b></td>
	<td>${dAForCountry}
		</td>	
			</tr>

		<!--<tr>
			<td><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.rateofdol" />&nbsp;:</b></td>
			<td>
			
			${rateOfDollar}</td>
		</tr>

		-->
		<tr>
			<!--<td><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.totdailyallrs" />&nbsp;:</b></td>
			<td>${hrFtourReqDtl.dailyAll}</td>

			--><td><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.entallrs" />&nbsp;:</b></td>
			<td>
			<c:if test="${empty hrFtourReqDtl.entAll}">
			----------
			</c:if>
			<c:if test="${not empty hrFtourReqDtl.entAll}">
			${hrFtourReqDtl.entAll}
			</c:if>
			</td>
		</tr>

</table>
</hdiits:fieldGroup>

		<c:if test="${not empty hrFtourReqDtl.hrForeigntourmemTxns}">

<hdiits:fieldGroup bundle="${commonLables}"  id="MemberDetails" expandable="true" titleCaptionId="HRMS.membdtl">
<table class="tabtable">
	
		<tr id="travelTable" colspan="8">
			<td colspan="4" align="center">
			
			<table id="travelTableData" style="border-collapse: collapse;" borderColor="BLACK"  border=1
				cellpadding="0" cellspacing="0" BGCOLOR="WHITE" width="80%" align="center">
				<tr colspan="4">
					
					<td align="center" width="8%" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.srno" /></b></td>
					<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.name" /></b></td>
					<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.dept" /></b></td>
					<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.desig" /></b></td>
					
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
	</table>
	</hdiits:fieldGroup>
</c:if>

<hdiits:fieldGroup bundle="${commonLables}"  id="OtherDetails" expandable="true" titleCaptionId="HRMS.addremark">
<table class="tabtable">

		<tr>
		
			<c:if test="${hrFtourReqDtl.visitedPlaceFlag =='1'}">
			<td id="prevDtlBlack" ><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.prevdtl"/></b></td>
			<td style="display:" id="prevDtlTD"><hdiits:textarea mandatory="false" rows="2" cols="30" name="prevDtl" tabindex="7" id="prevDtl" 
				caption="prevdtl" readonly="true" default="${hrFtourReqDtl.dtlsPrevVisit}" /></td>
			</c:if>
			
			
			<td><b><hdiits:caption bundle="${commonLables}" captionid ="HRMS.reason"/></b></td>
			<hdiits:hidden name="userId" default="${userId}" />
			
			<td><hdiits:textarea mandatory="true" rows="2" cols="30"
				name="reason" tabindex="23" id="reason" validation="txt.isrequired"
				captionid="HRMS.reason" bundle="${commonLables}" readonly="true" default="${hrFtourReqDtl.whyVisitInevitable}"/></td>
					
				
		</tr>
	
</table>
</hdiits:fieldGroup>

<table class="tabtable" width="100%">
	<c:if test="${reportView}">
			<tr align="center">
			<td>
				<hdiits:button type="button" id="Close"
					name="Close" captionid="HRMS.close" bundle="${commonLables}"
					onclick="window.close()" />
			</td>
			</tr>
			
		</c:if>
</table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
						
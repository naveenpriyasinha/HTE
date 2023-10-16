
<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/leave/DateVal.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/foreignToursTravel/fttcommom.js"/>"></script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle
	basename="resources.ess.foreigntourtravel.foreigntourtravel"
	var="commonLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="EmpDetVO" value="${resValue.EmpDet}" />
<c:set var="hrFtourReqDtl" value="${resValue.hrFtourReqDtl}" />
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

<c:set var="accUserInfo" value="${resValue.memDtlList}" />
<script>

var alertMsgReq = new Array();
var diffFlag="disFTT";
alertMsgReq[0]='<fmt:message key="HRMS.entValidNo" />';
alertMsgReq[1]='<fmt:message  bundle="${commonLables}" key="HRMS.detDateGtSysDate"/>';
alertMsgReq[2]='<fmt:message  bundle="${commonLables}" key="HRMS.retDtLessSysDt"/>';
alertMsgReq[3]='<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDate"/>';
alertMsgReq[4]='<fmt:message key="HRMS.To" bundle="${commonLables}"/>';
alertMsgReq[5]='<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessSysDate"/>';
alertMsgReq[6]='<fmt:message key="HRMS.numExcLim" bundle="${commonLables}"/>';
</script>



<hdiits:form name="returnFTTReq" id="returnFTTReq" validate="true"
	method="post">
	<hdiits:hidden name="goBackFlag" id="goBackFlag" />
	<hdiits:hidden name="currentDate" id="currentDate"
		default="${currentDate}" />
	<br>

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"
			id="tcontenthplnk1"><b> <hdiits:caption
			captionid="HRMS.tourReqDtl" bundle="${commonLables}"></hdiits:caption>
		</b></a></li>
		<li class="selected"><a href="#" id="tcontenthplnk2"
			rel="tcontent2"><b> <hdiits:caption
			captionid="HRMS.tourReturnDtl" bundle="${commonLables}"></hdiits:caption>
		</b></a></li>
	</ul>
	</div>

	<div id="FttRequest" name="FttRequest">

	<div id="tcontent1" class="tabcontent" tabno="0">

<hdiits:fieldGroup bundle="${commonLables}" expandable="true" id="TourDetailsGrp" titleCaptionId="HRMS.tourdtl">
	<table class="tabtable" name="travelRequest" id="travelRequest">

		<hdiits:hidden name="userId" id="userId" />
		<hdiits:hidden name="tourId" id="tourId"
			default="${hrFtourReqDtl.tourId}" />
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.tourname"
				bundle="${commonLables}" />:</b></td>

			<td width="25%">${hrFtourReqDtl.tourName}</td>

			<td width="25%"><b><hdiits:caption
				captionid="HRMS.Reffileno" bundle="${commonLables}" />&nbsp;:</b></td>

			<td width="25%">${hrFtourReqDtl.refFileNo}</td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.purpose"
				bundle="${commonLables}" /></b></td>

			<td width="25%"><hdiits:textarea rows="2" cols="30"
				name="purpose" tabindex="7" id="purpose"
				default="${hrFtourReqDtl.purpose}" readonly="true" /></td>

			<td><b><hdiits:caption captionid="HRMS.sendorgdtl"
				bundle="${commonLables}" />&nbsp;:</b></td>

			<td>${hrFtourReqDtl.sendOrgDtl}</td>
		<tr>
			<td align="left" width="13%"><b><hdiits:caption
				captionid="HRMS.expenditure" bundle="${commonLables}" /></b></td>

			<td>${expIncurredBy}</td>

			<td align="left"><b><hdiits:caption
				captionid="HRMS.entallrs" bundle="${commonLables}" />&nbsp;:</b></td>

			<td><hdiits:number captionid="HRMS.entallrs" name="entAllRs"
				id="entAllRs" default="${hrFtourReqDtl.entAll}"
				onblur="calTotalDailyAllInRs();" /></td>
			<!--
  	  
  	  <td>
	  	<b><hdiits:caption captionid="HRMS.entall" bundle="${commonLables}"/>&nbsp;:</b>	
  	</td>
    <td>
    	<c:set var="diff" value="${hrFtourReqDtl.entAll/rateOfDollar}"></c:set>
    	<hdiits:number captionid ="HRMS.entall" name="entAll" id="entAll" default="${diff}" onblur="calTotalDailyAllInRs();"/>
  	</td>  
      
  	-->
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="HRMS.embassy"
				bundle="${commonLables}" /></b></td>

			<td><c:if test="${hrFtourReqDtl.indEmbCareFlag =='0'}">
				<hdiits:caption captionid="HRMS.no" bundle="${commonLables}" />

			</c:if> <c:if test="${hrFtourReqDtl.indEmbCareFlag =='1'}">
				<hdiits:caption captionid="HRMS.yes" bundle="${commonLables}" />
			</c:if></td>

			<td><b><hdiits:caption captionid="HRMS.visited"
				bundle="${commonLables}" /></b></td>

			<td><c:if test="${hrFtourReqDtl.visitedPlaceFlag =='0'}">
				<hdiits:caption captionid="HRMS.no" bundle="${commonLables}" />
			</c:if> <c:if test="${hrFtourReqDtl.visitedPlaceFlag =='1'}">
				<hdiits:caption captionid="HRMS.yes" bundle="${commonLables}" />
			</c:if></td>

		</tr>

</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${commonLables}" id="JourneyDtlGrp" expandable="true" titleCaptionId="HRMS.journeydtl" >
<table class="tabtable" width="100%">
	
		<tr>
			<td align="left" width="13%"><b><hdiits:caption
				captionid="HRMS.country" bundle="${commonLables}" /></b></td>

			<td>${country}</td>

			<td><b><hdiits:caption captionid="HRMS.city"
				bundle="${commonLables}" />&nbsp;:</b></td>

			<td>${hrFtourReqDtl.city}</td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.dateofdep"
				bundle="${commonLables}" /></b></td>

			<td><hdiits:dateTime name="dateOfDep" captionid="HRMS.dateofdep"
				bundle="${commonLables}" validation="txt.isrequired"
				mandatory="true" default="${hrFtourReqDtl.dateOfDep} 00:00:00"
				maxvalue="31/12/9999" disabled="true" /></td>

			<td><b><hdiits:caption captionid="HRMS.retdt"
				bundle="${commonLables}" /></b></td>

			<td><hdiits:dateTime name="retDate" captionid="HRMS.retdt"
				bundle="${commonLables}" validation="txt.isrequired"
				mandatory="true" default="${hrFtourReqDtl.dateOfRet} 00:00:00"
				onblur="validateFTTData();" maxvalue="31/12/9999" /></td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.staydur"
				bundle="${commonLables}" />&nbsp;:</b></td>

			<td><hdiits:number captionid="HRMS.staydur" name="stayDur"
				id="stayDur" default="${hrFtourReqDtl.stayDur}" readonly="true" /></td>

			<td align="left" width="13%"><b><hdiits:caption
				captionid="HRMS.convmode" bundle="${commonLables}" /></b></td>

			<td>${conveyanceMode}</td>

		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.totdailyallInRs"
				bundle="${commonLables}" />:</b></td>
			<td><hdiits:number captionid="HRMS.totdailyallInRs"
				readonly="false" floatAllowed="true" validation="txt.isrequired"
				bundle="${foreignTourLables}" name="disTotalDailyAllInRs"
				maxlength="10" id="disTotalDailyAllInRs" tabindex="16"
				mandatory="true" size="2" onblur="validateFTTData();"
				default="${dAForCountryPerDay}" /></td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.totdailyallrs"
				bundle="${commonLables}" />&nbsp;:</b></td>
			<td><hdiits:number captionid="HRMS.totdailyall" readonly="true"
				floatAllowed="true" default="${dAForCountryPerDay}*${dateDiff}"
				name="disTotalDailyAll" id="disTotalDailyAll" mandatory="false"
				size="5" style="background-color:lightblue" /> <hdiits:number
				captionid="HRMS.totdailyall" readonly="true"
				style="background-color:lightblue" name="totalDailyAllRs"
				id="totalDailyAllRs" mandatory="false" size="8"
				default="${dAForCountry}" /></td>

		</tr>
		<!--<tr>
	<td>
		<b><hdiits:caption captionid="HRMS.rateofdol" bundle="${commonLables}"/>&nbsp;:</b>
	</td>
    
    <td>
    	<hdiits:number captionid ="HRMS.rateofdol" mandatory="true" floatAllowed="true" name="rateOfDollar" id="rateOfDollar" size="5" default="${rateOfDollar}" onblur="calTotalDailyAllInRs();"/> 		
  		<hdiits:button id="convert" type="button" name="convert" captionid="HRMS.convert" bundle="${commonLables}" onclick="calTotalDailyAllInRs();" />
  	</td>
  </tr>
  
  
  <tr>
  -->

		<!-- <td> 	<b><hdiits:caption captionid="HRMS.totdailyallrs" bundle="${commonLables}"/>&nbsp;:</b>
	</td>
    
  	-->
</table>
</hdiits:fieldGroup>

		<c:if test="${not empty hrFtourReqDtl.hrForeigntourmemTxns}">
		
	<hdiits:fieldGroup bundle="${commonLables}" expandable="true" collapseOnLoad="true" titleCaptionId="HRMS.membdtl" id="MembDetails">
	<table class="tabtable" width="100%">
			
			<tr id="travelTable" style="" colspan="8" align="center">
				<td colspan="4" align="center">

				<table id="travelTableData" style="border-collapse: collapse; "
					borderColor="BLACK" border=1 cellpadding="0" cellspacing="0"
					BGCOLOR="WHITE" width="80%" align="center">
					<tr colspan="4">
						<td align="center" width="13%" bgcolor="#C9DFFF"><b><hdiits:caption
							captionid="HRMS.srno" bundle="${commonLables}" /></b></td>
						<td align="center" width="13%" bgcolor="#C9DFFF"><b><hdiits:caption
							captionid="HRMS.name" bundle="${commonLables}" /></b></td>
						<td align="center" width="10%" bgcolor="#C9DFFF"><b><hdiits:caption
							captionid="HRMS.desig" bundle="${commonLables}" /></b></td>
						<td align="center" width="10%" bgcolor="#C9DFFF"><b><hdiits:caption
							captionid="HRMS.dept" bundle="${commonLables}" /></b></td>
					</tr>

					<c:set var="srNo" value="1" />

					<c:forEach var="accUserInfo" items="${accUserInfo}">
						<tr>

							<td>${srNo}</td>

							<td>${accUserInfo.empName}</td>

							<td>${accUserInfo.departmentName}</td>

							<td>${accUserInfo.designationName}</td>

						</tr>
						<c:set var="srNo" value="${srNo+1}" />
					</c:forEach>
				</table>

				</td>
			</tr>
		</table>
		</hdiits:fieldGroup>
		</c:if>
		
	<hdiits:fieldGroup bundle="${commonLables}" expandable="true" titleCaptionId="HRMS.addremark" id="AddRemarksGrp">
	<table class="tabtable" width="100%">

		<tr>
			<td><b><hdiits:caption captionid="HRMS.retdtl"
				bundle="${commonLables}" /> :</b></td>
			<td><hdiits:button type="button" name="returnDetails"
				id="returnDetails" onclick="openAnnexurePage();"
				captionid="HRMS.clickhere" bundle="${commonLables}" /></td>
		</tr>
	</table>
</hdiits:fieldGroup>
<table class="tabtable" width="100%">
		<tr>

			<td colspan="4" align="center"><hdiits:button type="button"
				name="Save" id="Save" captionid="HRMS.save" bundle="${commonLables}"
				onclick="save();" /> <hdiits:button type="button" id="Close"
				name="Close" captionid="HRMS.close" bundle="${commonLables}"
				onclick="document.getElementById('goBackFlag').value=1;goBackRetDtl();" />
			</td>
		</tr>
</table>
	</div>


	<div id="tcontent2" class="tabcontent" tabno="1"><%@ include
		file="/WEB-INF/jsp/hrms/ess/foreignTourTravel/annexture.jsp"%>


	</div>


	</div>

	<script type="text/javascript">

initializetabcontent("maintab");
</script>


	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="retDate,dateOfDep" />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

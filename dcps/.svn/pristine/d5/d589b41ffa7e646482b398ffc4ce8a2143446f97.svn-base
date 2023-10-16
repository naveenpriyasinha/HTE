<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/leave/DateVal.js"/>"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/foreignToursTravel/fttcommom.js"/>"></script>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.ess.foreigntourtravel.foreigntourtravel" var="foreignTourLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="expIncuredByList" value="${resValue.expIncuredByList}" />
<c:set var="conveyanceModeList" value="${resValue.conveyanceModeList}" />
<c:set var="countryList" value="${resValue.countryList}" />
<c:set var="userId" value="${resValue.userId}" />
<c:set var="currentDate" value="${resValue.currentDate}" />
<c:set var="dAForCountry" value="${resValue.dAForCountry}" />

<style type="text/css">    
            .pg-normal {
                color: black;
                font-weight: normal;
                text-decoration: none;    
                cursor: pointer;    
            }
            .pg-selected {
                color: black;
                font-weight: bold;        
                text-decoration: underline;
                cursor: pointer;
            }
</style>
<script>
obj=this;

var alertMsgReq = new Array();
var diffFlag="reqFTT";
alertMsgReq[0]='<fmt:message key="HRMS.entValidNo" />';
alertMsgReq[1]='<fmt:message  bundle="${foreignTourLables}" key="HRMS.detDateGtSysDate"/>';
alertMsgReq[2]='<fmt:message  bundle="${foreignTourLables}" key="HRMS.retDateLessDepDate"/>';
alertMsgReq[3]='<fmt:message  bundle="${foreignTourLables}" key="HRMS.retDateLessDepDate"/>';
alertMsgReq[4]='<fmt:message key="HRMS.To" bundle="${foreignTourLables}"/>';
alertMsgReq[5]='<fmt:message key="HRMS.numExcLim" bundle="${foreignTourLables}"/>';
</script>


<hdiits:form name="foreignTourtravel" validate="true"
	action="./hrms.htm?actionFlag=submitFTTReq" method="post">
	<hdiits:hidden name="currentDate" id="currentDate" default="${currentDate}"/>
	<br>

<hdiits:fieldGroup bundle="${foreignTourLables}"  expandable="true" mandatory="true" id="TourDetails" titleCaptionId="HRMS.tourdtl">
	<table class="tabtable" name="travelRequest" id="travelRequest">

		<hdiits:hidden name="nextId" id="nextId"/>

		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.tourname" bundle="${foreignTourLables}" />:</b></td>

			<td width="25%"><hdiits:text captionid="HRMS.tourname"
				name="tourName" id="tourName" mandatory="true"
				validation="txt.isrequired" bundle="${foreignTourLables}" tabindex="1" maxlength="200"/></td>

			<td width="25%"><b><hdiits:caption captionid="HRMS.Reffileno" bundle="${foreignTourLables}" />&nbsp;:</b>
			</td>

			<td width="25%"><hdiits:text captionid="HRMS.Reffileno"
				name="refFileNo" id="refFileNo" tabindex="2" maxlength="50"/></td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.purpose" bundle="${foreignTourLables}" /></b></td>

			<td width="25%"><hdiits:textarea mandatory="true" rows="2"
				cols="30" name="purpose" tabindex="3" id="purpose"
				validation="txt.isrequired" captionid="HRMS.purpose" maxlength="2000" bundle="${foreignTourLables}" /></td>

			<td><b><hdiits:caption captionid="HRMS.sendorgdtl" bundle="${foreignTourLables}" />&nbsp;:</b></td>

			<td><hdiits:text captionid="HRMS.sendorgdtl" name="sendOrgDtl"
				id="sendOrgDtl" tabindex="4" bundle="${foreignTourLables}" maxlength="100"/></td>
		</tr>

		<tr>
			<td align="left" width="13%"><b><hdiits:caption 
				bundle="${foreignTourLables}" captionid="HRMS.expenditure" /></b></td>

			<td><hdiits:select name="expenditure" size="1" id="expenditure"
				captionid="HRMS.expenditure" mandatory="true" validation="sel.isrequired" onchange="checkOrg(this);" bundle="${foreignTourLables}" tabindex="5">
				<hdiits:option value="0"><fmt:message key="HRMS.select" bundle="${foreignTourLables}"/></hdiits:option>
				<c:forEach var="expIncuredBy" items="${expIncuredByList}">
					<hdiits:option value="${expIncuredBy.lookupId}">
								${expIncuredBy.lookupDesc}
							</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>


		<tr>
			<td><b><hdiits:caption captionid="HRMS.embassy" bundle="${foreignTourLables}" /></b></td>

			<td><hdiits:radio name="embassy" id="embassy_yes" value="1"
				mandatory="false" tabindex="6" captionid="HRMS.yes" bundle="${foreignTourLables}"  />
				 <hdiits:radio	name="embassy" id="embassy_no" value="0" default="0"
				mandatory="false" tabindex="7" captionid="HRMS.no" bundle="${foreignTourLables}"/>
			</td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.invitation" bundle="${foreignTourLables}" /></b></td>
			<td><hdiits:radio name="invitation" id="invitation_yes"
				value="1" mandatory="false" tabindex="8" bundle="${foreignTourLables}" captionid="HRMS.yes"/>
				<hdiits:radio name="invitation" id="invitation_no" value="0"
				default="0" mandatory="false" tabindex="9" captionid="HRMS.no" bundle="${foreignTourLables}"/>
			</td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.visited" bundle="${foreignTourLables}" /></b></td>

			<td><hdiits:radio name="visited" id="visited_yes" value="1" 
				mandatory="false" onclick="checkPrevVisit(this);" tabindex="10" captionid="HRMS.yes" bundle="${foreignTourLables}" />
				 <hdiits:radio name="visited" id="visited_no" value="0"  mandatory="false"
				onclick="checkPrevVisit(this);" default="0" tabindex="11" captionid="HRMS.no" bundle="${foreignTourLables}"/>
			</td>
		</tr>


</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${foreignTourLables}"  expandable="true" mandatory="true" id="JourneyDetails" titleCaptionId="HRMS.journeydtl">
<table class="tabtable">

		<tr>
			<td align="left" width="13%"><b><hdiits:caption 
				bundle="${foreignTourLables}" captionid="HRMS.country" /></b></td>

			<td><hdiits:select name="country" size="1" id="country" captionid="HRMS.country" validation="sel.isrequired" mandatory="true" bundle="${foreignTourLables}" tabindex="12">
				<hdiits:option value="0"><fmt:message key="HRMS.select" bundle="${foreignTourLables}"/></hdiits:option>
				<c:forEach var="country" items="${countryList}">
					<hdiits:option value="${country.countryCode}">
								${country.countryName}
							</hdiits:option>
				</c:forEach>
			</hdiits:select></td>

			<td><b><hdiits:caption 
				bundle="${foreignTourLables}" captionid="HRMS.city" />&nbsp;:</b></td>

			<td><hdiits:text captionid="HRMS.city" name="city" id="city" tabindex="13" maxlength="200"/></td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.dateofdep" bundle="${foreignTourLables}" /></b></td>

			<td><hdiits:hidden name="dateFlag" id="dateFlag" /> 
			<hdiits:dateTime name="dateOfDep" captionid="HRMS.dateofdep" validation="txt.isrequired" maxvalue="31/12/9999"  bundle="${foreignTourLables}"
				afterDateSelect="" onblur="validateFTTData();" mandatory="true" tabindex="14"/></td>

			<td><b><hdiits:caption captionid="HRMS.retdt" bundle="${foreignTourLables}" /></b></td>

			<td><hdiits:dateTime name="retDate" captionid="HRMS.retdt" validation="txt.isrequired" maxvalue="31/12/9999"  mandatory="true" bundle="${foreignTourLables}"
				afterDateSelect="" onblur="validateFTTData();" tabindex="15"/></td>
		</tr>

		<tr>
			<td><b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.totdailyallInRs" />:</b></td>
			<td><hdiits:number captionid="HRMS.totdailyallInRs" readonly="false" floatAllowed="true" validation="txt.isrequired" bundle="${foreignTourLables}"
				name="disTotalDailyAllInRs" maxlength="10" id="disTotalDailyAllInRs" tabindex="16" mandatory="true" size="12" onblur="validateFTTData();"/>
			</td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="HRMS.staydur" bundle="${foreignTourLables}" />&nbsp;:</b></td>

			<td><hdiits:text captionid="HRMS.staydur" size="5" name="stayDur" readonly="true"
				id="stayDur"/></td>

			<td align="left" width="13%">
			<b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.convmode" /></b></td>

			<td><hdiits:select name="convMode" size="1" id="convMode"
				captionid="HRMS.convmode" validation="sel.isrequired" mandatory="true" bundle="${foreignTourLables}" tabindex="17">
				<hdiits:option value="0"><fmt:message key="HRMS.select" bundle="${foreignTourLables}"/></hdiits:option>
				<c:forEach var="conveyanceMode" items="${conveyanceModeList}">
					<hdiits:option value="${conveyanceMode.lookupId}">
								${conveyanceMode.lookupDesc}
							</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		
		
		
		
		
		<tr>
			<td><b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.totdailyallrs" />:</b></td>

			<td><hdiits:number captionid="HRMS.totdailyallrs" readonly="true" floatAllowed="true"
				name="disTotalDailyAll" id="disTotalDailyAll" mandatory="false" size="20" style="background-color:lightblue" />
				=&nbsp;&nbsp;
				<hdiits:number captionid="HRMS.totdailyallrs" readonly="true" maxvalue="99999999.99"
				name="totalDailyAllRs" id="totalDailyAllRs" mandatory="false" size="15" style="background-color:lightblue"/>
			</td>

			<td><b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.entallrs" />&nbsp;:</b></td>

			<td><hdiits:number captionid="HRMS.entallrs" name="entAllRs" onblur="calTotalDailyAllInRs();"
				id="entAllRs" mandatory="false" tabindex="18" bundle="${foreignTourLables}" maxlength="10"/></td>
		</tr><!--

		<tr>
			<td><b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.rateofdol" />&nbsp;:</b></td>

			<td><hdiits:number captionid="HRMS.rateofdol" onblur="calTotalDailyAllInRs();"
				name="rateOfDollar" id="rateOfDollar" tabindex="19" floatAllowed="true" default="45.0" maxlength="5" /> <hdiits:button
				id="convert" type="button" tabindex="20"  name="convert" captionid="HRMS.convert"
				bundle="${foreignTourLables}" onclick="calTotalDailyAllInRs();"/></td>
		</tr>

		--><tr>
			<!--<td><b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.totdailyallrs" />&nbsp;:</b></td>

			
			-->
			<!--<td><b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.entallrs" />&nbsp;:</b></td>

			--></tr>

</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${foreignTourLables}"  expandable="true" mandatory="false" id="MemberDetails" titleCaptionId="HRMS.membdtl">
<table class="tabtable">

		<tr>
			<td><b><hdiits:caption bundle="${foreignTourLables}" captionid ="HRMS.memb" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><hdiits:button id="add" type="button" name="add" tabindex="21"
				captionid="HRMS.add" bundle="${foreignTourLables}" onclick="SearchEmp();" /></td>
		</tr>


		<tr id="travelTable" colspan="8" style="display:none">
			<td colspan="4" align="center">
			<table id="travelTableData" style="border-collapse: collapse;" borderColor="BLACK"  border=1
				cellpadding="0" cellspacing="0" BGCOLOR="#A9A9A9" width="80%" align="center">
				<tr colspan="4">
					<td align="center" width="8%" bgcolor="#C9DFFF"><b><hdiits:checkbox value="0" name="MemSel" id="MemSel" onclick="memberSelect();" /></b></td>
					<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption 
						bundle="${foreignTourLables}" captionid="HRMS.name" /></b></td>
					<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption 
						bundle="${foreignTourLables}" captionid="HRMS.dept" /></b></td>
					<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption 
						bundle="${foreignTourLables}" captionid="HRMS.desig" /></b></td>
					
				</tr>

			</table>
			<div id="pageNavPosition"></div>
			</td>
		</tr>

		<tr style="display:none" id="deleteMem">
			<td class="fieldLabel" colspan="5" align="center" >
			<hdiits:button type="button" captionid="HRMS.delete" bundle="${foreignTourLables}" name="deleteTourMem" id="deleteTourMem"
					onclick="deleteMember();" />
				
			</td>
		</tr>
		
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${foreignTourLables}"  expandable="true" mandatory="true" id="RemarksDetails" titleCaptionId="HRMS.addremark">
<table class="tabtable">

		<tr>
			<td id="prevDtlBlack" style="display:none" ><b><hdiits:caption captionid="HRMS.prevdtl" bundle="${foreignTourLables}" /></b></td>
				<td style="display:none" id="prevDtlTD"><hdiits:textarea mandatory="false" rows="2" cols="30"
				name="prevDtl" tabindex="22" id="prevDtl"
				captionid="HRMS.prevdtl"  bundle="${foreignTourLables}" /></td>

			<td><b><hdiits:caption captionid="HRMS.reason" bundle="${foreignTourLables}" /></b></td>
			<hdiits:hidden name="userId" default="2" />
			<td><hdiits:textarea mandatory="true" rows="2" cols="30"
				name="reason" tabindex="23" id="reason" validation="txt.isrequired"
				captionid="HRMS.reason" bundle="${foreignTourLables}" /></td>
		</tr>
</table>
</hdiits:fieldGroup>
	<table class="tabtable">
		<tr>
			<td align="center" colspan="4"><hdiits:formSubmitButton id="fwd"
				type="button" name="fwd" tabindex="24" captionid="HRMS.fwd"
				bundle="${foreignTourLables}" /> <hdiits:resetbutton title="Reset" value="Reset" name="Reset" type="button" tabindex="25"/>
					<script>
						  var resetButton='<fmt:message key="HRMS.reset" />';
					      document.forms[0].Reset.value=resetButton;
				    </script>
				 <hdiits:button	type="button" id="Close" name="Close" tabindex="26" captionid="HRMS.close"
				bundle="${foreignTourLables}" onclick="goToHomePage();" /></td>
		</tr>

	</table>


	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

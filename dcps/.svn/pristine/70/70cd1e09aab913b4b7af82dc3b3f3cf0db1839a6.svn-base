<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript" src="script/common/commonfunctions.js">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
 

<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<fmt:setBundle basename="resources.ess.ltc.AlertMessages"
	var="alertLables" scope="request" />

<fmt:setBundle basename="resources.ess.ltc.LtcCaption" var="ltcCaption"
	scope="request" />

<fmt:setBundle basename="resources.ess.ltc.Constants" var="constants"
	scope="request" />
<fmt:message var="LTC1" bundle="${constants}" key="LTC1" />
<fmt:message var="LTC2" bundle="${constants}" key="LTC2" />
<fmt:message var="LTC3" bundle="${constants}" key="LTC3" />
<fmt:message var="LTC4" bundle="${constants}" key="LTC4" />
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/ltc/ltc.js"/>"></script>
<style type="text/css">

/* All form elements are within the definition list for this example */
.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}
</style>
<script language="javascript">

	var ltcAlertMsg = new Array();
	
	ltcAlertMsg[0] = '<fmt:message bundle="${alertLables}" key="HR.AJAX_BROWSER"/>';
	ltcAlertMsg[1] = '<fmt:message bundle="${alertLables}" key="HR.SELECT"/>';
	ltcAlertMsg[2] = '<fmt:message bundle="${alertLables}" key="HR.CANNOT_DELETE"/>'; //You cannot delete this row
	ltcAlertMsg[3] = '<fmt:message bundle="${alertLables}" key="HR.ADVANCE_ENTITLED"/>'; //Advance amount should not be more than entitled amount
	ltcAlertMsg[4] = '<fmt:message bundle="${alertLables}" key="HR.BackDatedAdv"/>'; //Sorry you cannot apply LTC Request for dates less than todays date.
	ltcAlertMsg[5] = '<fmt:message bundle="${alertLables}" key="HR.SELECT_ELDATES"/>'; //Please select the Approved Leave for which LTC is required
	ltcAlertMsg[6] = '<fmt:message bundle="${alertLables}" key="HR.FROM_DATE"/>'; //Please enter the Date of Journey 
	ltcAlertMsg[7] = '<fmt:message bundle="${alertLables}" key="HR.TO_DATE"/>'; //Please enter the Date of return Journey 
	ltcAlertMsg[8] = '<fmt:message bundle="${alertLables}" key="HR.LTC_PLACE_NAME"/>'; //Please select the Visiting Place Name
	ltcAlertMsg[9] = '<fmt:message bundle="${alertLables}" key="HR.PLACE_NAME"/>'; //Please enter the Place Name
	ltcAlertMsg[10] = '<fmt:message bundle="${alertLables}" key="HR.SELECT_MODE"/>'; //Please select the Mode of Transport
	ltcAlertMsg[11] = '<fmt:message bundle="${alertLables}" key="HR.BLOCK_YEAR"/>'; //Please select the Block
	ltcAlertMsg[12] = '<fmt:message bundle="${alertLables}" key="HR.LeaveAl"/>'; //Sorry Backdated leave is Not Accepted 
	ltcAlertMsg[13] = '<fmt:message bundle="${alertLables}" key="HR.MIN_FARE"/>'; //Please enter the minimum fare per ticket
	ltcAlertMsg[14] = '<fmt:message bundle="${alertLables}" key="HR.TOTAL_NUM"/>'; //Please enter the number of persons traveling
	ltcAlertMsg[15] = '<fmt:message bundle="${alertLables}" key="HR.ADV_AMT"/>'; //Please enter the advance Amount
	ltcAlertMsg[16] = '<fmt:message bundle="${alertLables}" key="HR.VDate"/>'; //To date must occur after the from date.
	ltcAlertMsg[17] = '<fmt:message bundle="${alertLables}" key="HR.AdvApproveDt"/>'; //Please select the Advance Approved Dates
	ltcAlertMsg[18] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_DEPR"/>'; //Please enter the Departure place name in row
	ltcAlertMsg[19] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_DEPR_DATE"/>'; //Please enter the From date in row 
	ltcAlertMsg[20] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_ARR"/>'; //Please enter the Arrival place name in row
	ltcAlertMsg[21] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_ARR_DATE"/>'; //Please enter the To date in row
	ltcAlertMsg[22] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_DIST"/>'; //Please enter the Distance in row
	ltcAlertMsg[23] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_TRAVEL_MODE"/>'; //Please select the Mode of Transport in row 
	ltcAlertMsg[24] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_CLASS"/>'; //Please select the Class of Travel in row 
	ltcAlertMsg[25] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_FARES_PAID"/>'; //Please enter the fare Paid in row
	ltcAlertMsg[26] = '<fmt:message bundle="${alertLables}" key="HR.REIMB_REMARKS"/>'; //Please enter the remarks in row 
	ltcAlertMsg[27] = '<fmt:message bundle="${alertLables}" key="HR.selctDpn"/>'; //Please select Dependent Details
	ltcAlertMsg[28] = '<fmt:message bundle="${alertLables}" key="HR.DEPENDENT_CHECK"/>'; //Do you want to select any family member?
	ltcAlertMsg[29] = '<fmt:message bundle="${alertLables}" key="HR.SubmitFinal"/>'; //Are you sure you want to submit?
	
/*AJAX function to get add and delete travel details table */	
function addRows() {
var tb = document.getElementById('reimbTableData');
var tableLength = tb.rows.length;
var nextId = tableLength-1;
var row = tb.insertRow(tableLength);
//1st cell
  var cell1 = row.insertCell(0);
  cell1.align="center";
  cell1.innerHTML+="<input type='text' mandatory='false'  name='departure"+nextId+"' tabindex='8' id='departure"+nextId+"' caption='Departure'/><br>";
  cell1.innerHTML+="<input type='text' name='fromdate"+nextId+"' onkeypress='return checkDateNumber();'  class='texttag' onBlur='return checkDate('fromdate"+nextId+"','Please enter valid $CPTN','From Date','0','31/12/2099','Please enter $CPTN less than 31/12/2099')' maxlength=10 size=10 readonly='true' /><img style='cursor:hand' id='img_fromdate"+nextId+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif  width=20 onClick=window_open('fromdate"+nextId+"',375,570,'','fromdate"+nextId+",Please~enter~valid~$CPTN,Departure~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";


//2nd cell
  var cell2 = row.insertCell(1);
  cell2.align="center";
  cell2.innerHTML+="<input type='text' mandatory='false'  name='arrival"+nextId+"' tabindex='8' id='' caption='Arrival'/><br>";
  cell2.innerHTML+="<input type='text'  name='todate"+nextId+"'  onkeypress='return checkDateNumber()'  class='texttag' onBlur='return checkDate('fromdate"+nextId+"','Please enter valid $CPTN','To Date','0','31/12/2099','Please enter $CPTN less than 31/12/2099')' maxlength=10 size=10 readonly='true' /><img style='cursor:hand' id='img_todate"+nextId+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('todate"+nextId+"',375,570,'','fromdate"+nextId+",Please~enter~valid~$CPTN,Arrival~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
//3rd cell
  var cell3 = row.insertCell(2);
  cell3.align="center";
  cell3.innerHTML+="<input type='text' mandatory='false'  name='distance"+nextId+"' tabindex='8' size='3' maxlength='7' id='' caption='Distance' onkeypress='return checkNumberOnly(true)'  class='texttag mandatorycontrol'/>";  
//4th cell
  var cell4 = row.insertCell(3);
  cell4.align="center";
  var str="<select name='travelMode1"+nextId+"'  id='travelMode1"+nextId+"'  size='1' caption='drop_down'  mandatory='true' onchange='popupClass("+nextId+");'> <option><fmt:message  bundle='${alertLables}' key='HR.SELECT'/></option>";
  for(i=1;i<document.frmltcapply.travelMode.options.length;i++){
   str+="<option value='"+document.frmltcapply.travelMode.options[i].value+"'>"+document.frmltcapply.travelMode.options[i].innerHTML+"</option>";
  }
  str+="</select>";
  cell4.innerHTML=str;
//5th cell
  var cell5 = row.insertCell(4);
  cell5.align="center";
  var str="<select name='class"+nextId+"'  id='class"+nextId+"'  size='1' caption='drop_down'   mandatory='true' onchange=''> <option><fmt:message  bundle='${alertLables}' key='HR.SELECT'/></option>";
  str+="<option value=''></option>";
  str+="</select>";
  cell5.innerHTML=str;
//6th cell
  var cell6 = row.insertCell(5);
  cell6.align="center";
  cell6.innerHTML+="<input type='text' mandatory='false'  name='totalFares"+nextId+"' tabindex='8' size='1'  maxlength='6' id=''  caption='Total Fares' onkeypress='return checkNumberOnly(true)'  class='texttag mandatorycontrol' />";    
//7th cell
  var cell7 = row.insertCell(6);
  cell7.align="center";
  cell7.innerHTML+="<input type='text' onblur='getTotalFareReimb();' mandatory='false'  name='farePaid"+nextId+"' tabindex='8' size='4' id=''  caption='Fare Paid' onkeypress='return checkNumberOnly(true)'  class='texttag mandatorycontrol'/>";    
//8th cell
  var cell8 = row.insertCell(7);
  cell8.align="center";
  cell8.innerHTML+="<input type='text' mandatory='false'  name='remarks"+nextId+"' tabindex='8' id=''  caption='Remarks'/>";            
}



		








</script>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="VisitingPlaceList" value="${resultValue.VisitingPlaceList}">
</c:set>
<c:set var="TravelModeList" value="${resultValue.TravelModeList}">
</c:set>
<c:set var="leaveList" value="${resultValue.leaveList}">
</c:set>
<c:set var="approvedLeaveList" value="${resultValue.approvedLeaveList}">
</c:set>
<c:set var="familyList" value="${resultValue.familyList}">
</c:set>
<c:set var="ltcBlocksList" value="${resultValue.ltcBlocksList}">
</c:set>
<c:set var="LtcMsg" value="${resultValue.LtcMsg}">
</c:set>
<c:set var="dateto" value="${resultValue.dateto}">
</c:set>
<c:set var="empSalary" value="${resultValue.empSalary}">
</c:set>
<c:set var="lstLtcDtl" value="${resultValue.lstLtcDtl}">
</c:set>




<hdiits:form name="frmltcapply" validate="true" method="post" action=""	encType="multipart/form-data">
	
	<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="HRMS.LTCHeader" bundle="${ltcCaption}" captionLang="single"/></a></li>
	</ul>
	</div>

	<div class="tabcontentstyle" style="height: 100%; width: 100%" >
	<div id="tcontent1" class="tabcontent" tabno="0" style="height: 100%; width: 100%">
	
	<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp">
		<jsp:param name="empImage" value="N" />
	</jsp:include>
	
	<input type="hidden" name="ltcDtlId" id="ltcDtlId">
	<input type="hidden" name="salary" id="EmpSalary" value="${empSalary}">
	<input type="hidden" name="salary" id="EmpSalary" value="${EmpDetVO.salary}">
	
	<input type="hidden" name="sysDt" id="sysDt" value="${dateto}">
	
	
	
	<c:if test="${empty familyList}">
	<br>
	<br>
	<center>
	<fieldset  style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; clear: center;">
	
	<table class="tabtable" align="center" >
	
	
	<tr align="center">
		
	
	<td align="center">
	
	
<c:if test="${empty familyList}">
		<font color="red">
		<b><fmt:message key="HRMS.msg1" bundle="${ltcCaption}" /></b> 
		</font><br>
			
</c:if>	
	
	</td>
	
	
	
	</table>
	</fieldset>
	</center>
	</c:if>
	
	
	<hdiits:fieldGroup  id="ltcDetails" titleCaptionId="HRMS.LTCDetails" bundle="${ltcCaption}" collapseOnLoad="false">
	
	<table class="tabtable" width="100%">


		
		<tr>
			
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.LTCReqType"
				bundle="${ltcCaption}" /></b></td>
				
				<td width="30%"><hdiits:radio name="requestType" value="1" mandatory="false"
				default="1" onclick="showbutton(this);" captionid="HRMS.LTCHeader" bundle="${ltcCaption}"/> 
				<hdiits:radio
				name="requestType" value="0" mandatory="false"
				onclick="showbutton(this);" captionid="HRMS.Reimbursement" bundle="${ltcCaption}"/></td>
				<c:if test="${empty approvedLeaveList}">
				<td width="25%" id="mst1" style="">
				<font color="red"><b><fmt:message key="HR.ReLtcMsg" bundle="${alertLables}" /></b></font>
				</td>
				</c:if>
				<td>
				</td>
				
			
			</tr>
			<tr>
				<td>
				<hdiits:caption captionid="HRMS.ruleType" bundle="${ltcCaption}" />
				</td>
				<td><hdiits:radio name="ruleType" value="0" mandatory="true" default="0"  captionid="HRMS.stateGovt" bundle="${ltcCaption}" /> 
				<hdiits:radio name="ruleType" value="1" mandatory="true" captionid="HRMS.centralGovt" bundle="${ltcCaption}"/>
				</td>
			</tr>
<c:if test="${empty approvedLeaveList}">
					<script type="text/javascript">
						
						document.frmltcapply.requestType[1].disabled=true;
						
					</script>

			</c:if>


		<!-- Advance Taken  -->

		<tr id="familyDatesTitle"style="display:none">
			
			<td width="25%" style="display:"><b><hdiits:caption
				captionid="HRMS.AdvDates" bundle="${ltcCaption}" /></b></td>
			<td width="25%" id="familyDatesCombo" style="display: "><hdiits:select
				sort="false" name="familyDates" size="1" caption="drop_down" captionid="HR.SELECT"
				mandatory="true" onchange="popupDates();">
				<hdiits:option value="0">
					<fmt:message key="HR.SELECT" bundle="${alertLables}" />
				</hdiits:option>

				<c:forEach var="lod" items="${approvedLeaveList}">
					<hdiits:option
						value="${lod.fromDate}~${lod.toDate}~${lod.hrEssLeaveMainTxn.leaveid}~${lod.ltcRqstId}">
						<fmt:formatDate type="date" pattern="dd/MM/yyyy"
							value="${lod.fromDate}"></fmt:formatDate> to <fmt:formatDate
							type="date" pattern="dd/MM/yyyy" value="${lod.toDate}"></fmt:formatDate>
					</hdiits:option>
				</c:forEach>

			</hdiits:select></td>
		</tr>
		
		
		<!-- Journey by-->
		<tr id="journeyBy">
		<td width="25%"><b><hdiits:caption captionid="HRMS.Journey"
				bundle="${ltcCaption}" /></b></td>
			<td><hdiits:radio name="journey" value="1" mandatory="false"
				default="1" onclick="showfamily(this);" captionid="HRMS.Self" bundle="${ltcCaption}"/><br>
				<hdiits:radio
				name="journey" value="0" mandatory="false"
				onclick="showfamily(this);" captionid="HRMS.FamilyOnly"
				bundle="${ltcCaption}"/>
				<c:if test="${empty familyList}">
					<script type="text/javascript">
						
						document.frmltcapply.journey[1].disabled=true;
						
					</script>

			</c:if></td>
		
		<td>
		</td>
		
		<td>
		</td>
		
		
		</tr>
		
		
		
		
		<!-- Approved Earned Leave  -->

		<tr id="family" style="display:">
			<td width="25%"><b><hdiits:caption captionid="HRMS.ELDates"
				bundle="${ltcCaption}" /></b></td>
			<td width="25%"><hdiits:select sort="false" name="elDates" size="1"
				caption="drop_down" captionid="HR.SELECT" mandatory="true" onchange="leaveEL();" >

				<hdiits:option value="0">
					<fmt:message  key="HR.SELECT" bundle="${alertLables}" />
				</hdiits:option>
				<c:forEach var="elDates" items="${leaveList}">

					<c:forEach var="lod" items="${elDates.hrEssLeaveOtherDtls}">
						<c:if test="${lod.sanctionFromdate!=null}">
							<hdiits:option
								value="${lod.sanctionFromdate}~${lod.sanctionTodate}~${elDates.leaveid}">
								<fmt:formatDate type="date" pattern="dd/MM/yyyy"
									value="${lod.sanctionFromdate}"></fmt:formatDate> to <fmt:formatDate
									type="date" pattern="dd/MM/yyyy" value="${lod.sanctionTodate}"></fmt:formatDate>


							</hdiits:option>
						</c:if>
					</c:forEach>
				</c:forEach>

			</hdiits:select>
			
			
			</td>
         
		</tr>
		
		<tr id="elValid" style="display:none">
		<td>
		</td>
		<td >
		<b></b><font color="red"><hdiits:caption  bundle="${alertLables}" captionid="HR.LeaveAl"/> </font></b>
		</td>
		</tr>
	<tr align="center" >
		
	
	<td align="center" id="leaveMsg" style="display:none" >
			
			<font color="red">
				<fmt:message key="HRMS.msg2" bundle="${ltcCaption}" /></font>
			
	
	</td>
	
	
	</tr>
		
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.DateOfJourney"
				bundle="${ltcCaption}" /></b></td>
			<td width="25%"><hdiits:dateTime name="fromdate" caption="From Date"
				mandatory="true" captionid="HRMS.DateOfJourney"
				bundle="${ltcCaption}" maxvalue="01/01/2099"></hdiits:dateTime></td>
			<td width="25%"><hdiits:caption captionid="HRMS.DateOfReturnJourney"
				bundle="${ltcCaption}" /></td>
			<td width="25%"><hdiits:dateTime name="todate" caption=" To Date"
				mandatory="true" captionid="HRMS.DateOfReturnJourney"
				bundle="${ltcCaption}" maxvalue="01/01/2099" /></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.VisitingPlace"
				bundle="${ltcCaption}" /></b></td>
			<td width="25%"><hdiits:select sort="false" name="visitingPlace" size="1"
				id="visitingPlace" caption="Visting Place" mandatory="true"
				onchange="popupPlace();">
				<hdiits:option value="0">
					<fmt:message key="HR.SELECT" bundle="${alertLables}" />
				</hdiits:option>
				<c:forEach var="visitingPlace" items="${VisitingPlaceList}">
					<hdiits:option value="${visitingPlace.lookupName}">
				${visitingPlace.lookupDesc}
				</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			<td><b><hdiits:caption captionid="HRMS.PlaceName"
				bundle="${ltcCaption}" /></b></td>
			<td><hdiits:text mandatory="true" name="placeName" tabindex="8"
				id="placeName" validation="txt.isrequired" caption="Place Name" /></td>
		</tr>
		<tr>
			<td id="transMode" style="display"><b><hdiits:caption
				captionid="HRMS.TransportMode" bundle="${ltcCaption}" /></b></td>
			<td id="transModeList" style="display"><hdiits:select
				sort="false" name="travelMode" size="1" caption="drop_down"
				mandatory="true" onchange="">
				<hdiits:option value="0">
					<fmt:message key="HR.SELECT" bundle="${alertLables}" />
				</hdiits:option>
				<c:forEach var="travelMode" items="${TravelModeList}">
					<hdiits:option value="${travelMode.lookupName}">
				${travelMode.lookupDesc}
				</hdiits:option>
				</c:forEach>
			</hdiits:select></td>


			<td><b><hdiits:caption captionid="HRMS.Block" bundle="${ltcCaption}" /></b></td>
			<td><hdiits:select sort="false" name="block" size="1" id="block"
				caption="Block" mandatory="true">
				<hdiits:option value="0">
					<fmt:message key="HR.SELECT" bundle="${alertLables}" />
				</hdiits:option>
				<c:forEach var="block" items="${ltcBlocksList}">
					<hdiits:option value="">
					</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		<tr id="MessageText1" style=" ">
			<td><b><hdiits:caption captionid="HRMS.Entitled"
				bundle="${ltcCaption}" /></b></td>
			<td ><b><font color="green">
			<!--<c:out	value="${LtcMsg}"></c:out>-->
			<c:if
					test="${LtcMsg=='Second Sleeper'}">
					<fmt:message bundle="${alertLables}" key="HRMS.SecondSleeper" />
				</c:if> <c:if
					test="${LtcMsg=='First class/II AC III-Tier Sleeper/AC Chair Car'}">
					<fmt:message bundle="${alertLables}" key="HRMS.ChairCar" />
				</c:if> <c:if test="${LtcMsg=='II AC 2-Tier Sleeper'}">
					<fmt:message bundle="${alertLables}" key="HRMS.TwoTierSleeper" />
				</c:if> <c:if test="${LtcMsg=='AC First Class/Air Allowance'}">
					<fmt:message bundle="${alertLables}" key="HRMS.FirstClass" />
				</c:if>
				<c:if test="${LtcMsg=='Air Allowance'}">
					<fmt:message bundle="${alertLables}" key="HRMS.FirstClass" />
				</c:if>
			
			
			</font></b></td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="HRMS.SpouseEmployed"
				bundle="${ltcCaption}" /></b></td>
			<td><hdiits:radio captionid="HR.YES" name="spouseEmployed"
				value="1" mandatory="false" onclick="spouse(this);"
				bundle="${alertLables}" /> <hdiits:radio captionid="HR.NO"
				name="spouseEmployed" value="0" mandatory="false" default="0"
				onclick="spouse(this);" bundle="${alertLables}" /></td>
			<td width="25%" id="numFares" style="display"><b><hdiits:caption
				captionid="HRMS.NoOfFares" bundle="${ltcCaption}" /></b></td>
			<td width="25%" id="numFaresTxt" style="display"><font color="blue"><input
				name="NoOfFares" id="NoOfFares" value="1"
				style="border-width:0;border:0; " readonly="readonly" /> </font></td>
		</tr>
		<tr id="spouse" style="display:none">
			<td><b><hdiits:caption captionid="HRMS.SpouseEntitled"
				bundle="${ltcCaption}" /></b></td>
			<td><hdiits:radio captionid="HR.YES" name="spouseEntitled"
				value="1" mandatory="false" onclick="noc(this);"
				bundle="${alertLables}" /> <hdiits:radio captionid="HR.NO"
				name="spouseEntitled" value="0" mandatory="false" default="0"
				onclick="noc(this);" bundle="${alertLables}" /></td>
			<td id="noc" style="display:none"><hdiits:caption
				captionid="HRMS.NOC" bundle="${ltcCaption}" /></td>
			<td id="noc1" style="display:none"><font color="red"> <hdiits:caption
				captionid="HRMS.NOCA" bundle="${ltcCaption}"/></font></td>

		</tr>
		<tr id="MessageText" style="display:none">

			<td><b><hdiits:caption captionid="HRMS.Entitled"
				bundle="${ltcCaption}" /></b></td>
			<td ><b><font color="red">
			<!--<c:out	value="${LtcMsg}"></c:out>	-->
			<!--pankaj test	-->
			<c:if
					test="${LtcMsg=='Second Sleeper'}">
					<fmt:message bundle="${alertLables}" key="HRMS.SecondSleeper" />
				</c:if> <c:if
					test="${LtcMsg=='First class/II AC III-Tier Sleeper/AC Chair Car'}">
					<fmt:message bundle="${alertLables}" key="HRMS.ChairCar" />
				</c:if> <c:if test="${LtcMsg=='II AC 2-Tier Sleeper'}">
					<fmt:message bundle="${alertLables}" key="HRMS.TwoTierSleeper" />
				</c:if> <c:if test="${LtcMsg=='AC First Class/Air Allowance'}">
					<fmt:message bundle="${alertLables}" key="HRMS.FirstClass" />
				</c:if>
				
				
				</font></b></td>
		</tr>


	
	<table id="reimbTable" style="display:none;" width="100%" align="center">
		<tr >
			<td >
			<br>

			<fieldset style="border-color:black; background-color:Whitesmoke;border-collapse:collapse; " >
			<legend><hdiits:caption captionid="HRMS.Detl" bundle="${ltcCaption}" /></legend> 
			<br>
			
			<table id="reimbTableData" style="display:none" border="1" width="95%"
				cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
				<tr style="background-color:${tdBGColor}">
					<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="HRMS.Departure" bundle="${ltcCaption}" /></b></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.Arrival" bundle="${ltcCaption}" /></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.DistanceInKm" bundle="${ltcCaption}" /></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.TransportMode" bundle="${ltcCaption}" /></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.ClassofAcc" bundle="${ltcCaption}" /></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.NoOfFares" bundle="${ltcCaption}" /></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.FaresPaid" bundle="${ltcCaption}" /></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.Remarks" bundle="${ltcCaption}" /></td>
				</tr>
				<tr align="center">



					<td align="center"><hdiits:text mandatory="false"
						name="departure0" tabindex="8" id="departure0" caption="Departure" /><br>
					<hdiits:dateTime name="fromdate0" caption="dateTime"
						mandatory="false" captionid="HRMS.Departure"
						bundle="${ltcCaption}" maxvalue="01/01/2099"></hdiits:dateTime></td>
					<td align="center"><hdiits:text mandatory="false" name="arrival0"
						tabindex="8" id="arrival0" caption="Arrival" /><br>
					<hdiits:dateTime name="todate0" caption="dateTime"
						mandatory="false" captionid="HRMS.Arrival" bundle="${ltcCaption}"
						maxvalue="01/01/2099"></hdiits:dateTime></td>
					<td align="center"><hdiits:number name="distance0"
						mandatory="false" validation="txt.isnumber" caption="Distance"
						id="distance0" tabindex="8" size="3" maxlength="7" /></td>
					<td align="center"><hdiits:select sort="false" tabindex="8"
						name="travelMode10" id='travelMode10' size="1" caption="drop_down"
						mandatory="false" onchange="popupClass('0');" >
						<hdiits:option value="0">
							<fmt:message key="HR.SELECT" bundle="${alertLables}" />
						</hdiits:option>
						<c:forEach var="travelMode" items="${TravelModeList}">
							<hdiits:option value="${travelMode.lookupName}">
				${travelMode.lookupDesc}
				</hdiits:option>
						</c:forEach>
					</hdiits:select></td>
					<td align="center"><hdiits:select sort="false" name="class0"
						tabindex="8" id='class0' size="1" caption="drop_down"
						mandatory="false" onchange="">
						<hdiits:option value="0">
							<fmt:message key="HR.SELECT" bundle="${alertLables}" />
						</hdiits:option>
						<c:forEach var="class" items="">
							<hdiits:option value="">

							</hdiits:option>
						</c:forEach>
					</hdiits:select></td>
					<td align="center"><hdiits:number name="totalFares0"
						mandatory="false" validation="txt.isnumber" caption="Total Fares"
						id="totalFares0" size="1" tabindex="8" maxlength="6" /></td>
					<td align="center"><hdiits:number name="farePaid0"
						onblur="getTotalFareReimb();" mandatory="false"
						validation="txt.isnumber" size="4" caption="Fare Paid" id="farePaid0"
						tabindex="8" maxlength="7" /></td>
					<td align="center"><hdiits:text mandatory="false" name="remarks0"
						tabindex="8" id="remarks0" caption="Remarks" /></td>
				</tr>



			</table>
			<br>
			<br>
		<table>	
		<tr id="reimbButtons" style="display:none" >
			<td id="addButton" style="display:none"><hdiits:button
				type="button" name="add" captionid="HR.ADDROW"
				bundle="${alertLables}" onclick="addRows();" /></td>
			<td id="delButton" style="display:none"><hdiits:button
				type="button" name="delete" captionid="HR.DELROW"
				bundle="${alertLables}" onclick="deleteRows();" /></td>
		</tr>
		</table>
		
</fieldset>
</td>
</tr>
</table>
<table class="tabtable" id="advanceRadio">
<tr>
<td width="25%">
<hdiits:caption	captionid="HRMS.Advance" bundle="${ltcCaption}" />
</td>
<td width="25%" >
<hdiits:radio captionid="HR.YES" name="advanceYes" id="advYes"
				value="1" mandatory="false" onclick="advanceTable();"
				bundle="${alertLables}" />
<hdiits:radio captionid="HR.NO" id="advNo"
				name="advanceYes" value="0" mandatory="false" default="0"
				onclick="advanceTable();" bundle="${alertLables}" />

</td>
<td width="25%">

</td >
<td width="25%">
</td>


</tr>
</table>


<table width="100%" class="tabtable" id="advanceTable" style="display:">
		
		<tr>
			<td id="minFare1" style="display: none;"><b><hdiits:caption
				captionid="HRMS.ReMinFare" bundle="${ltcCaption}" /></b></td>
			<td id="minFare2" style="display: none;"><hdiits:number name="MinFare"
				mandatory="true" validation="txt.isnumber" caption="No of Fares"
				id="MinFare" maxlength="6" onblur="getTotalFare();" /></td>
			<td width="25%" style="display: none;" id="totalFare1"><b><hdiits:caption captionid="HRMS.TotalFare"
				bundle="${ltcCaption}" /></b></td>
			<td width="25%" style="display: none;" id="totalFare0">
			<hdiits:text readonly="true" mandatory="false"
				name="totalFare" tabindex="8" id="" caption="Total Fare"
				style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" /></td>
		</tr>
		<tr id="advAmt" style="display: none;" >			
			<td width="25%"><b><hdiits:caption captionid="HRMS.EntitledAmount"
				bundle="${ltcCaption}" /></b></td>
			<td width="25%"><hdiits:text readonly="true" mandatory="false"
				name="entitledAmount" tabindex="8" id="" caption="Entitled Amount"
				style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" /></td>
			<td width="25%"><b><hdiits:caption captionid="HRMS.AdvanceAmount"
				bundle="${ltcCaption}" /></b>
				</td>
			<td><hdiits:number name="advanceAmount" mandatory="true"
				validation="txt.isnumber" caption="Advance Amount"
				id="advanceAmount" onblur="validateAdvAmt();" maxlength="7" /></td>
		</tr>
		<tr id="atchTicket" style="display:none">
			
		</tr>
</table>
	<table width="100%" class="tabtable">	
		<tr id="apprAdvAmt" style="display:none">
			<td width="25%"><hdiits:caption captionid="HRMS.ApprAdvanceAmount"
				bundle="${ltcCaption}" /></td>
			<td width="25%"><hdiits:text mandatory="false" name="apprAdvanceAmount"
				tabindex="8" id="apprAdvanceAmount" readonly="true"
				caption="Approved Advance Amount"
				style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" /></td>
			<td width="25%"><hdiits:caption captionid="HRMS.BalanceAmount"
				bundle="${ltcCaption}" /></td>
			<td width="25%"><hdiits:text mandatory="false" name="balanceAmt"
				tabindex="8" id="balanceAmt" readonly="true"
				caption="Balance Amount" /></td>

		</tr>
       <tr id="refund" style="display:none ">
       <td >
       </td>
        <td>
       <font color="red" style="font-weight: bold">
       <fmt:message	key="HR.SecnAmount" bundle="${alertLables}" />
       </font>  
       </td>
       </tr>

</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup  id="DependentDetails" titleCaptionId="HRMS.DependentDetails" bundle="${ltcCaption}" collapseOnLoad="false">			
			<table border="1" cellpadding="3" cellspacing="0" BGCOLOR="WHITE"
				class="TableBorderLTRBN" id="depndentTable" width="100%" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black">

				<tr style="background-color:${tdBGColor} ">
					<td width="10%" bgcolor="${tdBGColor}"> <hdiits:checkbox value="0" readonly="false"
						name="dpndtMain" onclick="select_this(this);" id="dpndtMain" /></td>
					<td width="10%" align="center" ><hdiits:caption
						captionid="HRMS.SlNo" bundle="${ltcCaption}" /></td>
					<td width="30%" align="center" ><hdiits:caption
						captionid="HRMS.Name" bundle="${ltcCaption}" /></td>
					<td width="10%" align="center" ><hdiits:caption
						captionid="HRMS.Age" bundle="${ltcCaption}" /></td>
					<td align="center" ><hdiits:caption
						captionid="HRMS.Relationship" bundle="${ltcCaption}" /></td>
				</tr>
				<c:set var="i" value="1"></c:set>
				<c:forEach var="familyData" items="${familyList}">
					<tr>
						<td><hdiits:checkbox value="${familyData.memberId}"
							name="dpndt" onclick="numberOffare(this);" /></td>
						<td align="center" ><c:out value="${i}"></c:out></td>
						<td align="center" ><c:out
							value="${familyData.fmFirstName}"></c:out> <c:out
							value="${familyData.fmMiddleName}"></c:out> <c:out
							value="${familyData.fmLastName}"></c:out></td>
						<td align="center" ><script>var v1=Age("${familyData.fmDateOfBirth}");
						document.write(v1);
					</script></td>
						<td align="center" ><c:out
							value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>
						</td>
						<input type="hidden" name="dpndtId" value="${familyData.memberId}">
						<c:set var="i" value="${i+1}"></c:set>
					</tr>

				</c:forEach>
				<tr align="center" id="noRecords" style="display:none;">
					<td colspan="5" align="center" >
						    <hdiits:caption captionid="HRMS.NoRecords" bundle="${ltcCaption}" />
					</td>
				</tr>
			</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup id="pastLtcDetails" titleCaptionId="HRMS.pastLtcDtl" bundle="${ltcCaption}" collapseOnLoad="true">
	
<c:set var="num" value="1" />	
	<center>	
			<display:table list="${lstLtcDtl}" id="row" requestURI="" pagesize="10"  style="width:80%" offset="1" export="false">
				<display:setProperty name="paging.banner.placement" value="bottom"/>
							
				<display:column class="tablecell" titleKey="HRMS.srNo" headerClass="tablerow" style="text-align: center" sortable="false" >${num}</display:column>
				
				<display:column class="tablecell" titleKey="HRMS.constituentYr"  headerClass="tablerow" style="text-align: center" sortable="true" >${row.hrEssLtcblockMst.fromYear} - ${row.hrEssLtcblockMst.toYear}</display:column>
				
				<display:column  class="tablecell" titleKey="HRMS.takenRule" headerClass="tablerow" style="text-align: center;" sortable="false" >
					<c:if test="${row.ltcRuleType == 0}">
						<fmt:message key="HRMS.stateGovt" bundle="${ltcCaption}" />
					</c:if>	
					<c:if test="${row.ltcRuleType == 1}">
						<fmt:message key="HRMS.centralGovt" bundle="${ltcCaption}" />
					</c:if>	
				</display:column>
				<display:footer  media="html" ></display:footer>					
				<c:set var="num" value="${num+1}" />
			</display:table>
		</center>
				
</hdiits:fieldGroup>				
			
		
			<c:if test="${empty familyList}">
				<script type="text/javascript">
			document.getElementById("noRecords").style.display="";
						
					</script>

			</c:if>
			
			
		<input type="hidden" name="familysize" value="${i-1}">
		
		<table width="100%">
		<tr id="nocAtch" style="display:none;" >
			<td> <jsp:include
				page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="SpouseAttachment" />
				<jsp:param name="formName" value="frmltcapply" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />
			</jsp:include> <!-- For attachment : End--></td>
		</tr>
		<tr>
			<td><br></td>
		</tr>
		<tr align="center">
			
			<td align="center" colspan="5"><CENTER><hdiits:button type="button" name="Submit"
				captionid="HR.Submit" bundle="${alertLables}"
				onclick="submitRequest();" id="Submit" />&nbsp;&nbsp; <hdiits:button
				type="button" name="Close" bundle="${alertLables}"
				captionid="HR.Close" onclick="Close1();" /></CENTER></td>
			
		</tr>

	</table>

	<input type="hidden" name="basicSal" value="${EmpDetVO.salary}">

	</div>
	</div>
	<!-- Hidden Parameters -->
	
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script type="text/javascript">		
		initializetabcontent("maintab");
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

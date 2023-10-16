
<%
try {
%>
<%@page buffer="256kb" autoFlush="true"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/statusbar.js"/>"></script>	
<script type="text/javascript"
	src="script/hrms/ess/travel/travelCommonScripts.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
	
</script>


<!-- For Xml File List Return By VoGen -->

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="journeyRowList" value="${resultValue.journeyRowList}">
</c:set>
<c:set var="rmbsCmbList" value="${resultValue.rmbsCmbList}">
</c:set>
<c:set var="NoOfRow" value="${fn:length(journeyRowList)}">
</c:set>
<c:set var="payMode" value="${resultValue.payMode}">
</c:set>
<c:set var="payModeId" value="${resultValue.payModeId}">
</c:set>
<c:set var="tourPurpose" value="${resultValue.tourPurpose}">
</c:set>

<c:set var="tourId" value="${resultValue.tourId}">
</c:set>
<c:set var="rmbsFlag" value="${resultValue.rmbsFlag}">
</c:set>
<c:set var="parentReqId" value="${resultValue.parentReqId}">
</c:set>
<c:set var="travelExpList" value="${resultValue.travelExpList}">
</c:set>
<c:set var="requestedAmt" value="${resultValue.requestedAmt}">
</c:set>


<script language="JavaScript1.2">

function disabletext(e){
return false
}

function reEnable(){
return true
}

//if the browser is IE4+
document.onselectstart=new Function ("return false")

//if the browser is NS6
if (window.sidebar){
document.onmousedown=disabletext
document.onclick=reEnable
}


</script>


<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.travel.caption" var="caption"
	scope="request" />

<hdiits:form name="frm1" validate="true" method="POST"
	encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="HRMS.PreSanctionRmbs" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

	<br>

	<hdiits:fieldGroup titleCaptionId="HRMS.Trip_Details" bundle="${caption}" expandable="false"/>
	
	<br>
	<span id="errorId"> </span> 
	<!--  Starting main Table -->
	
	<div id="mainTable" >
	<c:if test="${empty rmbsCmbList}">
	<center>
			<br>
		
		<br>
		<br>
		<br>
		<br>
		<font color="RED">
		<center><font color="RED"><hdiits:caption captionid="HRMS.NoRecord" bundle="${caption}" ></hdiits:caption> </font></center>
		<br><br>
		<br>
		<hdiits:button type="button" name="closeBt" value="Close"
			id="closeBt" captionid="HRMS.Close" bundle="${caption}" onclick="closewindow(this)" />
	</center>
	</c:if>
	<c:if test="${not empty rmbsCmbList}">
	<center>
		<table>
			<tr>
				<td><b><fmt:message key="HRMS.TourName"
						bundle="${caption}" /></b>
				</td>
				<td>
					<hdiits:select name="tourId" size="1" default="${tourId}"
						caption="tourId" id="tourId" onchange="addPreRmbsTourData(this)"
						tabindex="2" style="">
						<hdiits:option value="-1">------Select -----</hdiits:option>
						<c:forEach var="rmbsCmbList" items="${rmbsCmbList}">
							<hdiits:option value="${rmbsCmbList.tourId}">
									${rmbsCmbList.tourName}
							</hdiits:option>
						</c:forEach>
					</hdiits:select>
				</td>
			</tr>
		</table>
	</center>
	
	<c:if test="${not empty journeyRowList}">
	<center >
		<table>
		<tr>
			<td>
			<font color="BLACK"><hdiits:caption captionid="HRMS.Purpose"  bundle="${caption}" /></font>&nbsp;:&nbsp; </td>
			<td>
					<hdiits:textarea rows="3" cols="21" name="purpose" id="purpose" default="${tourPurpose}" ></hdiits:textarea>
			</td>
		</tr>
		</table>
	</center>
	<br>
	</div>
	<hdiits:text style="display:none" name="ref_file_no" tabindex="2"
		id="ref_file_no" caption="ref. file no." default="12345" />
<hdiits:fieldGroup titleCaptionId="HRMS.Journey_Details" bundle="${caption}" expandable="false"/>
	
	<div id="mainDiv" style="width: 100%;height: 100%;overflow-x:scroll;overflow-y:hidden">
 	
	
	
	<!-- Main Table -->
	<table id='detailsTable' border="1" borderColor="black" cellpadding="1" cellspacing="1"  style="border-collapse: collapse;background-color: #F0F4FB">
		<!-- Start: Heading -->
		<tr height="25%" bgcolor="#C9DFFF">
			<td rowspan="3" colspan="1" width="1%"></td>
			<td rowspan="1" colspan="2" align="center"><hdiits:caption
				captionid="HRMS.Departure" bundle="${caption}" /></td>
			<td rowspan="1" colspan="2" align="center"><hdiits:caption
				captionid="HRMS.Arrival" bundle="${caption}" /></td>
			<td rowspan="3" colspan="1" height="25%" width="5%"><hdiits:caption
				captionid="HRMS.Conveyance_Mode" bundle="${caption}" /></td>
			<td rowspan="3" colspan="2" height="25%" align="center"><hdiits:caption
				captionid="HRMS.Sub_Items" bundle="${caption}" /></td>
			<td rowspan="3" colspan="1" height="25%" width="1%"><hdiits:caption
				captionid="HRMS.Distance" bundle="${caption}" /></td>
			<td rowspan="3" colspan="1" height="25%" width="5%"><hdiits:caption
				captionid="HRMS.Purose_Of_Stay" bundle="${caption}" /></td>
			<td rowspan="3" colspan="1" height="25%" width="5%"><hdiits:caption
				captionid="HRMS.Accomodation" bundle="${caption}" /></td>
			<td rowspan="3" colspan="1" height="25%" width="2%"><hdiits:caption
				captionid="HRMS.Total_Cost" bundle="${caption}" /></td>
			<td rowspan="3" colspan="1" height="25%" width="10%"><hdiits:caption
				captionid="HRMS.Remark" bundle="${caption}" /></td>
		</tr>
		<tr bgcolor="#C9DFFF">
			<td rowspan="1" colspan="2" width="10%"><hdiits:caption
				captionid="HRMS.Place" bundle="${caption}" /></td>
			<td rowspan="1" colspan="2" width="10%"><hdiits:caption
				captionid="HRMS.Place" bundle="${caption}" /></td>
		</tr>
		<tr bgcolor="#C9DFFF">

			<td rowspan="1" colspan="1" width="10%"><hdiits:caption
				captionid="HRMS.Date" bundle="${caption}" /></td>
			<td rowspan="1" colspan="1" width="10%"><hdiits:caption
				captionid="HRMS.Time" bundle="${caption}" /></td>
			<td rowspan="1" colspan="1" width="10%"><hdiits:caption
				captionid="HRMS.Date" bundle="${caption}" /></td>
			<td rowspan="1" colspan="1" width="10%"><hdiits:caption
				captionid="HRMS.Time" bundle="${caption}" /></td>
		</tr>
		<!-- First Row -->
		 
		 <c:forEach items="${journeyRowList}" var="journeyRow">
		 <tr height="25%" class="odd">
			 <td rowspan="2" colspan="1" width="1%"><hdiits:checkbox
				name="srno${journeyRow.srNo}" id="srno${journeyRow.srNo}" value="chk" readonly="${journeyRow.chkStatus}" />
			</td>
			
			<td rowspan="1" colspan="2" align="left">
				<hdiits:select	name="depart_plc${journeyRow.srNo}" size="1" caption="departure_place" tabindex="3"
					validation="sel.isrequired" id="depart_plc${journeyRow.srNo}" mandatory="true" default="${journeyRow.departCity}"
					onchange="setArrival(this)">
					<hdiits:option value="321611">
						<hdiits:caption captionid="HRMS.Select" bundle="${caption}" />
					</hdiits:option>
					<c:forEach var="city" items="${journeyRow.cityList}">
						<hdiits:option value="${city.cityId}">
								${city.cityName}
							</hdiits:option>
					</c:forEach>
				</hdiits:select>
					
			</td>
			<td rowspan="1" colspan="2" align="left">
				<hdiits:select
					name="arr_plc${journeyRow.srNo}" size="1" caption="arrival_place" 
					validation="sel.isrequired"  id="arr_plc${journeyRow.srNo}" mandatory="true"
					onchange="setDeparture(this)" tabindex="2" default="${journeyRow.arrivalCity}" >
					<hdiits:option value="321611"><hdiits:caption captionid="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="city" items="${journeyRow.cityList}">
						<hdiits:option value="${city.cityId}">
								${city.cityName}
							</hdiits:option>
					</c:forEach>
					</hdiits:select>
			 
			</td>
			<td rowspan="2" colspan="1" height="25%" width="5%">
	
			<hdiits:select 	name="con_mod${journeyRow.srNo}" default="${journeyRow.convyanceMode}" id="con_mod${journeyRow.srNo}" caption="con_mode0" mandatory="true" 
				validation="sel.isrequired" sort="false" onchange="hide(this)">
				<c:forEach var="ConvyanceModevar" items="${journeyRow.convyanceList}">
					<hdiits:option value="${ConvyanceModevar.lookupId}">${ConvyanceModevar.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select>
			</td>
				
		
			
			<td rowspan="2" colspan="1" height="25%" align="center"
				style="display:none" id="bus${journeyRow.srNo}">${journeyRow.airList[0].lookupDesc} <hdiits:radio
				name="airmod${journeyRow.srNo}" id="airmod${journeyRow.srNo}" value="${journeyRow.airList[0].lookupId}" title="Business"
				onclick="setAirmodeval(this)" />
			</td>
			<td rowspan="2" colspan="1" height="25%" align="center"
				style="display:none" id="eco${journeyRow.srNo}">${journeyRow.airList[1].lookupDesc} <hdiits:radio
				name="airmod${journeyRow.srNo}" id="airmod${journeyRow.srNo}" value="${journeyRow.airList[1].lookupId}" title="Business"
				onclick="setAirmodeval(this)" />
			</td>
		
			
			<td rowspan="1" colspan="1" height="25%" align="center"
				style="display: none" id="own${journeyRow.srNo}"><label><hdiits:caption captionid="HRMS.Owned" bundle="${caption}"></hdiits:caption> </label>
			</td>
			<td rowspan="1" colspan="1" height="25%" align="center"
				style="display: none" id="veh${journeyRow.srNo}"><label><hdiits:caption captionid="HRMS.Vehicle_Type" bundle="${caption}"></hdiits:caption></label>
			</td>
			
			
			
			<td rowspan="1" colspan="1" height="25%" align="center" id="trn${journeyRow.srNo}">
				<label style="border-spacing:em;"><hdiits:caption captionid="HRMS.Train" bundle="${caption}"></hdiits:caption></label>
			</td>
			<td rowspan="1" colspan="1" height="25%" align="center" id="class${journeyRow.srNo}">
				<label><hdiits:caption captionid="HRMS.Class" bundle="${caption}"></hdiits:caption></label>
			</td>
			<td rowspan="2" id="dummyCol${journeyRow.srNo}" colspan="1" height="25%" width="1%" style="text-align: center;">
				<hdiits:number size="3" name="dis${journeyRow.srNo}" classcss="mandatorycontrol" default="${journeyRow.distance}" id="dis${journeyRow.srNo}" style="display: none;text-align: right" />
				<label id="dummyDis${journeyRow.srNo}"  style="text-align: center">0</label>
			</td>
			<td rowspan="2" colspan="1" height="25%" width="5%">
			<hdiits:select readonly="${journeyRow.purposeStatus}"
					name="purpose${journeyRow.srNo}" classcss="mandatorycontrol" id="purpose${journeyRow.srNo}" caption="purposeofstay${journeyRow.srNo}"
					mandatory="false" default="${journeyRow.stayPurpose}" validation="sel.isrequired">
					<hdiits:option value="321611" ><hdiits:caption captionid="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="Purpose_of_StayListvar"
						items="${journeyRow.stayPurposeList}">
						<hdiits:option value="${Purpose_of_StayListvar.lookupId}">
						${Purpose_of_StayListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select><label class='mandatoryindicator'>*</label>
			</td>
			<td rowspan="2" colspan="1" height="25%" width="5%">
			<hdiits:select name="accom${journeyRow.srNo}" readonly="${journeyRow.acomStatus}" classcss="mandatorycontrol" id="accom${journeyRow.srNo}" caption="accomodation${journeyRow.srNo}" onchange="showAccomodationImage(this)"  mandatory="false"
					validation="sel.isrequired" sort="false" default="${journeyRow.accomType}" >
					<c:forEach var="AccomodationListvar" items="${journeyRow.accomList}">
						<hdiits:option value="${AccomodationListvar.lookupId}">
						${AccomodationListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select><label class='mandatoryindicator'>*</label>
				<img src="images/Details.gif" alt="Click Here To Add Accomodation Details" id="accomImg${journeyRow.srNo}" name="accomImg${journeyRow.srNo}" onclick="viewAccomodationDtl(this)" style="display: none">
				<hdiits:hidden name="accomRowXML${journeyRow.srNo}" id="accomRowXML${journeyRow.srNo}" default="${journeyRow.xmlFilePath}" />
				
			</td>
			<td rowspan="2" colspan="1" height="25%" width="2%">
				<hdiits:number size="2" name="cost${journeyRow.srNo}" id="cost${journeyRow.srNo}" default="${journeyRow.cost}" maxlength="10" style="text-align: right" />
			</td>
			<td rowspan="2" colspan="1" height="25%" width="10%">
				<hdiits:textarea name="remark${journeyRow.srNo}" id="remark${journeyRow.srNo}" cols="21" rows="3" default="${journeyRow.remark}"> </hdiits:textarea>
			</td>
		 </tr>
		 <tr class="odd">
			
			
			
			<fmt:formatDate value="${depart_dt}" var="depart_dtfmt" pattern="dd/MM/yyyy"/>
			<td rowspan="1" colspan="1" width="10%"><hdiits:dateTime
				name="depart_dt${journeyRow.srNo}" classcss="mandatorycontrol" captionid="HRMS.DummyDeprDt" bundle="${caption}"
				maxvalue="31/12/2099" onblur="checkDates(this)" 	mandatory="true" />
				<script>
				 	var index="${journeyRow.srNo}";
				 	document.getElementById("depart_dt"+index).value="${journeyRow.departDate}";
				 </script>
				
			</td>
			
			<td rowspan="1" colspan="1" width="10%"><hdiits:text size="1"
				name="depart_tm${journeyRow.srNo}" classcss="mandatorycontrol" default="${journeyRow.departTime}" id="depart_tm${journeyRow.srNo}"  onblur="timeCheck(this);checkDates(this)"
				mandatory="false" maxlength="5" /><label class='mandatoryindicator'>*</label>
			</td>
			
			<td rowspan="1" colspan="1" width="10%"><hdiits:dateTime
				name="arr_dt${journeyRow.srNo}" classcss="mandatorycontrol" captionid="HRMS.DummyDeprDt" bundle="${caption}"
				 maxvalue="31/12/2099" onblur="fillDepartDtTime(this);checkDates(this)" mandatory="true"  />
				 <script>
				 	document.getElementById("arr_dt"+index).value="${journeyRow.arrivaldate}";
				 </script>
			</td>
			<td rowspan="1" colspan="1" width="10%"><hdiits:text
				name="arr_tm${journeyRow.srNo}" classcss="mandatorycontrol" mandatory="false" id="arr_tm${journeyRow.srNo}" size="1" maxlength="5" default="${journeyRow.arrivalTime}"
				onblur="fillDepartDtTime(this);timeCheck(this);checkDates(this)" /><label class='mandatoryindicator'>*</label>
			</td>
			
			<td rowspan="1" colspan="1" height="25%" align="center" id="trncol${journeyRow.srNo}" >
				<hdiits:select name="sub_train${journeyRow.srNo}" classcss="mandatorycontrol"  id="sub_train${journeyRow.srNo}" caption="train1"
					mandatory="false" validation="sel.isrequired" default="${journeyRow.trainName}"
					sort="false">
					<hdiits:option value='321611'><hdiits:caption captionid="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="TrainListvar" items="${journeyRow.trainList}">
						<hdiits:option value="${TrainListvar.lookupId}">
						${TrainListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> 
				<hdiits:select name="sub_own${journeyRow.srNo}" classcss="mandatorycontrol" id="sub_own${journeyRow.srNo}" caption="train${journeyRow.srNo}"
					mandatory="false" validation="sel.isrequired" style="display:none" default="${journeyRow.ownedBy}"
					onchange="enabletype(this)">
					<hdiits:option value='321611'><hdiits:caption captionid="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="owned_byvar" items="${journeyRow.ownedByList}">
						<hdiits:option value="${owned_byvar.lookupId}">
							${owned_byvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select><label class='mandatoryindicator'>*</label>
			</td>
			<td rowspan="1" colspan="1" height="25%" align="center" id="clscol${journeyRow.srNo}">
				<hdiits:select name="sub_class${journeyRow.srNo}" classcss="mandatorycontrol" id="sub_class${journeyRow.srNo}" caption="class1 "
					mandatory="false" validation="sel.isrequired" default="${journeyRow.trnclass}"
					sort="false">
					<hdiits:option value='321611'><hdiits:caption captionid="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="ClassListvar" items="${journeyRow.trnClassList}">
						<hdiits:option value="${ClassListvar.lookupId}"> 
						${ClassListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select>
				<hdiits:select name="sub_veh${journeyRow.srNo}" classcss="mandatorycontrol" id="sub_veh${journeyRow.srNo}" caption="class${journeyRow.srNo} "
					mandatory="false" validation="sel.isrequired" style="display:none" default="${journeyRow.vehicleType}"
					readonly="true"> 
					<hdiits:option value='321611'><hdiits:caption captionid="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="veh_typevar" items="${journeyRow.vehicleTypeList}">
						<hdiits:option value="${veh_typevar.lookupId}">
							${veh_typevar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select><label class='mandatoryindicator'>*</label>
			</td>
		
		</tr>
		 </c:forEach>
	</table>
	</div>
	
	<center>
	<table id="btTable">
		<hdiits:button type="button" name="addButton" value="Add Another Row"
			id="addButton" captionid="HRMS.Add_Another_Row" bundle="${caption}" onclick="addrow(this)" />
		<c:if test="${NoOfRow gt 2}">
		
		<hdiits:button type="button" name="123"
			value="Delete Journey" id="123" captionid="HRMS.DeleteRow" bundle="${caption}"
			onclick="deleteJourneyRow(this)"  />
		</c:if>
		<br>
		<br>
	</table>
	</center>
	<center>
		<table id="advanceDtl" style="">
			
			
			<tr>
				<td>
					<hdiits:caption	captionid="HRMS.RequestedAmt" bundle="${caption}" /> 	
				</td>
				<td>
					<hdiits:number name="requestedAmt" id="requestedAmt" default="${requestedAmt}"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:caption	captionid="HRMS.PayMod" bundle="${caption}" /> 	
				</td>
				<td>
					<hdiits:select name="modOfPay" id="modOfPay" default="${payModeId}">
						<hdiits:option  value="321611"><hdiits:caption captionid="HRMS.Select" bundle="${caption}" /></hdiits:option>
						<c:forEach var="payMD" items="${payMode}">
							<hdiits:option value="${payMD.lookupId}">${payMD.lookupDesc}</hdiits:option>
						</c:forEach>					
					</hdiits:select>
				</td>
			</tr>
		</table>
	</center>
	<center>
	<br>
	<center>

	<table>
	 <c:forEach items="${travelExpList}" var="ExpList">
	
		<tr>
		<hdiits:hidden name="${ExpList.cmnLookupMst.lookupName}Id" default="${ExpList.cmnLookupMst.lookupId}"/>	 
			<td>
				<c:out value="${ExpList.cmnLookupMst.lookupDesc}"/>
			</td>
			<td>
				<hdiits:number name="${ExpList.cmnLookupMst.lookupName}" default="${ExpList.charge}" />
			</td>
			<td>
				Remark				
			</td>
			<td>
				<hdiits:text name="${ExpList.cmnLookupMst.lookupName}Rmk" default="${ExpList.remark}" />
			</td>
		</tr>
	
	</c:forEach>	
	</table> 
	</center>
	<br>
	<br>
	<table border="1" id="attachtb" style="">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="TravelAttachment" />
				<jsp:param name="formName" value="frm1" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="multiple" value="N" />
				<jsp:param name="rowNumber" value="1" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
			</jsp:include></td>
		</tr>
	</table>
	</center>
	<br>
	 
	
	<br>
	<br>
	<center>
	<table id="cntButton">
		<hdiits:button type="button" name="saveButton" value="Submit Request"
			id="SaveButton" captionid="HRMS.Submit"  bundle="${caption}" onclick="submitRmbsRequest(this)" />
		<hdiits:button type="button" name="closeButton" value="Close"
			id="closeButton" captionid="HRMS.Close" bundle="${caption}" onclick="closewindow(this)" />

	</table>
	</center>
	</c:if>
	<br>
	<br>
	</c:if>
	</div>
	</div>


	<!-- Hidden Fields -->
	<hdiits:hidden id="journeySize" name="journeySize" default="${fn:length(journeyRowList)}"/>
		<hdiits:hidden id="chkBoxArray" name="chkBoxArray" />
		<hdiits:hidden id="rmbsFlag" name="rmbsFlag" default="${rmbsFlag}"/>
		<hdiits:hidden id="parentReqId" name="parentReqId" default="${parentReqId}"/>
	<!-- End Hidden Field -->
	<script type="text/javascript">	
		if('${advFlag}'=='true'){
	 		document.getElementById('advanceDtl').style.display="";
			document.getElementById('attachtb').style.display="";
			var advTakenRB=document.getElementsByName('AdvTaken');
			advTakenRB[0].checked="true";
	 	}
		for(var i=0;i<document.getElementById('journeySize').value;i++){
		
			if((document.getElementById('accom'+i).value=='352002')||(document.getElementById('accom'+i).value=='352004')){
				document.getElementById('accomImg'+i).style.display="";
			}else{
				document.getElementById('accomImg'+i).style.display="none";
			}
			
		
			var mod=document.getElementById('con_mod'+i).value;
			var idx=i;
			var own_nm="own"+idx;
			var bus_nm="bus"+idx;
			var eco_nm="eco"+idx;
			var veh_nm="veh"+idx;
			var trn_nm="trn"+idx;
			var class_nm="class"+idx;
			var trncol_nm="trncol"+idx;
			var clscol_nm="clscol"+idx;
			
	 
			var sub_train_nm="sub_train"+idx;
			var sub_class_nm="sub_class"+idx;
			var sub_own_nm="sub_own"+idx;
			var sub_veh_nm="sub_veh"+idx;
			var airmod_nm="airmod"+idx;
			
			var own=document.getElementById(own_nm);
			var bus=document.getElementById(bus_nm);
			var eco=document.getElementById(eco_nm);
			var veh=document.getElementById(veh_nm);
			var trn=document.getElementById(trn_nm);
			var cls = document.getElementById(class_nm);
			var trncol=document.getElementById(trncol_nm);
			var clscol=document.getElementById(clscol_nm);
			
		 
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			var sub_own=document.getElementById(sub_own_nm);
			var sub_veh=document.getElementById(sub_veh_nm);	
			var airmod=document.getElementById(airmod_nm);
			
			if(mod=="321594" || mod=="321598")
			{
				document.getElementById('dis'+idx).value="0";
				document.getElementById('dis'+idx).style.display="none";
				document.getElementById('dummyDis'+idx).style.display="";
				
				bus.style.display="";
				eco.style.display="";
	
				own.style.display="none";
				veh.style.display="none";
	
				trn.style.display="none";
				cls.style.display="none";
			
				trncol.style.display="none";
				clscol.style.display="none";
				
			}else if(mod=='321595'|| mod=="321599")
			{
				document.getElementById('dis'+idx).value="0";
				document.getElementById('dis'+idx).style.display="none";
				document.getElementById('dummyDis'+idx).style.display="";
				
				sub_train.disabled="";
				sub_class.disabled="";
				own.style.display="none";
				bus.style.display="none";
				eco.style.display="none";
				veh.style.display="none";
				trn.style.display="";
				cls.style.display="";
				trncol.style.display="";
				clscol.style.display="";
		 
				sub_train.style.display="";
				sub_class.style.display="";
				sub_own.style.display="none";
				sub_veh.style.display="none";
			}else if (mod=='321600' || mod=="321596")
			{
				 
				document.getElementById('dis'+idx).style.display="";
				document.getElementById('dummyDis'+idx).style.display="none";
				
				own.style.display="";
				bus.style.display="none";
				eco.style.display="none";
				veh.style.display="";
				trn.style.display="none";
				cls.style.display="none";
				trncol.style.display="";
				clscol.style.display="";
	 
		 
				sub_train.style.display="none";
				sub_class.style.display="none";
				sub_own.style.display="";
				sub_veh.style.display="";
			}
		}	
		initializetabcontent("maintab");
	</script>

</hdiits:form>




<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

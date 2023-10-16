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

<c:set var="NoOfRow" value="${fn:length(journeyRowList)}">
</c:set>
<c:set var="receivableAmt" value="${resultValue.grandTotal}">
</c:set>
<c:set var="advFlag" value="${resultValue.advFlag}">
</c:set>
<c:set var="xmlPath" value="${resultValue.xmlPath}">
</c:set>
<c:set var="payMode" value="${resultValue.payMode}">
</c:set>
<c:set var="tourPurpose" value="${resultValue.tourPurpose}">
</c:set>
<c:set var="tourDtlVO" value="${resultValue.tourDtlVO}">
</c:set>
<c:set var="travelMstVO" value="${resultValue.travelMstVO}">
</c:set>

<c:set var="cancleMessage" value="${resultValue.cancleMessage}">
</c:set>
<c:set var="reasonCancellation" value="${resultValue.reasonCancellation}">
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
			key="HRMS.PreSanctionReq" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	
	<c:if test="${reasonCancellation ne null }">
		<hdiits:textarea  name="reason" id="reason" default="${reasonCancellation}"> </hdiits:textarea>
	</c:if>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	
	<span id="errorId"> </span> 
	<!--  Starting main Table -->
	<div id="mainTable">
	<center>
	<hdiits:fieldGroup titleCaptionId="HRMS.Trip_Details" bundle="${caption}" expandable="false">
		<table>
		<tr>
			<td>
				<font color="BLACK"><hdiits:caption captionid="HRMS.TourName"  bundle="${caption}" /></font>&nbsp;:&nbsp; </td>
			<td>
					
					<hdiits:text name="tourName" id="tourName" default="${tourDtlVO.tourName}" readonly="true" />
			</td>
		</tr>
		<tr>		
			<td>
		
			<font color="BLACK"><hdiits:caption captionid="HRMS.Purpose"  bundle="${caption}"  /></font>&nbsp;:&nbsp; </td>
			<td>
					<hdiits:textarea rows="3" cols="21" name="purpose" id="purpose"  readonly="true" default="${tourDtlVO.purpose}" ></hdiits:textarea>
			</td>
		</tr>
		</table>
	</hdiits:fieldGroup>
	</center>
 
	</div>
	<strong><font color="RED"><c:out value="${cancleMessage}"> </c:out></font></strong>
	<hdiits:text style="display:none" name="ref_file_no" tabindex="2"
		id="ref_file_no" caption="ref. file no." default="12345" />
	<hdiits:fieldGroup titleCaptionId="HRMS.Journey_Details" bundle="${caption}" expandable="false"></hdiits:fieldGroup>
	<div id="mainDiv" style="width: 100%;height: 100%;overflow-x:scroll;overflow-y:hidden">
	<!-- Main Table -->
	<table id='detailsTable' border="1"  cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;background-color: #F0F4FB">
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
		 <tr height="25%">
			 <td rowspan="2" colspan="1" width="1%"><hdiits:checkbox
				name="srno${journeyRow.srNo}" id="srno${journeyRow.srNo}" value="chk" readonly="true" />
			</td>
			
			<td rowspan="1" colspan="2" align="left">
				<hdiits:select	name="depart_plc${journeyRow.srNo}" size="1" caption="departure_place" tabindex="3" readonly="${journeyRow.viewFlag}"
					validation="sel.isrequired" id="depart_plc${journeyRow.srNo}" mandatory="true" default="${journeyRow.departCity}"
					onchange="">
					<hdiits:option value="321611">
						<fmt:message key="HRMS.Select" bundle="${caption}" />
					</hdiits:option>
					<c:forEach var="city" items="${journeyRow.cityList}">
						<hdiits:option value="${city.cityId}">
								${city.cityName}
							</hdiits:option>
					</c:forEach>
				</hdiits:select>
					
			</td>
			<td rowspan="1" colspan="2" align="left">
				<hdiits:select readonly="${journeyRow.viewFlag}"
					name="arr_plc${journeyRow.srNo}" size="1" caption="arrival_place" 
					validation="sel.isrequired"  id="arr_plc${journeyRow.srNo}" mandatory="true"
					onchange="" tabindex="2" default="${journeyRow.arrivalCity}" >
					<hdiits:option value="321611"><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="city" items="${journeyRow.cityList}">
						<hdiits:option value="${city.cityId}">
								${city.cityName}
							</hdiits:option>
					</c:forEach>
					</hdiits:select>
			 
			</td>
			<td rowspan="2" colspan="1" height="25%" width="5%">
	
			<hdiits:select readonly="${journeyRow.viewFlag}" 	name="con_mod${journeyRow.srNo}" default="${journeyRow.convyanceMode}" id="con_mod${journeyRow.srNo}" caption="con_mode0" mandatory="true" 
				validation="sel.isrequired" sort="false" onchange="hide(this)">
				<c:forEach var="ConvyanceModevar" items="${journeyRow.convyanceList}">
					<hdiits:option value="${ConvyanceModevar.lookupId}">${ConvyanceModevar.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select>
			</td>
				
		
			
			<td nowrap="nowrap" rowspan="2" colspan="1" height="25%" align="center"
				style="display:none" id="bus${journeyRow.srNo}"><hdiits:radio
				name="airmod${journeyRow.srNo}" id="airmod${journeyRow.srNo}" captionid="HRMS.Business" bundle="${caption}" value="${journeyRow.airList[0].lookupId}"  title="Business"
				onclick="setAirmodeval(this)"  />
			</td>
			<td rowspan="2" nowrap="nowrap" colspan="1" height="25%" align="center"
				style="display:none" id="eco${journeyRow.srNo}"> <hdiits:radio
				name="airmod${journeyRow.srNo}" id="airmod${journeyRow.srNo}" value="${journeyRow.airList[1].lookupId}" captionid="HRMS.Economic" bundle='${caption}' title="Business" onclick="setAirmodeval(this)" />
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
			<hdiits:select readonly="${journeyRow.viewFlag}"
					name="purpose${journeyRow.srNo}" classcss="mandatorycontrol" id="purpose${journeyRow.srNo}" caption="purposeofstay${journeyRow.srNo}"
					mandatory="false" default="${journeyRow.stayPurpose}" validation="sel.isrequired">
					<hdiits:option value="321611" ><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="Purpose_of_StayListvar"
						items="${journeyRow.stayPurposeList}">
						<hdiits:option value="${Purpose_of_StayListvar.lookupId}">
						${Purpose_of_StayListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select><label class='mandatoryindicator'>*</label>
			</td>
			<td rowspan="2" colspan="1" nowrap="nowrap" height="25%" width="5%">
			<hdiits:select  name="accom${journeyRow.srNo}" readonly="${journeyRow.viewFlag}" classcss="mandatorycontrol" id="accom${journeyRow.srNo}" caption="accomodation${journeyRow.srNo}"   mandatory="false"
					validation="sel.isrequired" sort="false" default="${journeyRow.accomType}" >
					<hdiits:option value="321611" ><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="AccomodationListvar" items="${journeyRow.accomList}">
						<hdiits:option value="${AccomodationListvar.lookupId}">
						${AccomodationListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select><label class='mandatoryindicator'>*</label>
				
			</td>
			<td rowspan="2" colspan="1" height="25%" width="2%">
				<hdiits:number size="2" name="cost${journeyRow.srNo}"  readonly="${journeyRow.viewFlag}"  id="cost${journeyRow.srNo}" default="${journeyRow.cost}" maxlength="10" style="text-align: right" />
			</td>
			<td rowspan="2" colspan="1" height="25%" width="10%">
				<hdiits:textarea name="remark${journeyRow.srNo}" readonly="${journeyRow.viewFlag}" id="remark${journeyRow.srNo}" cols="21" rows="3" default="${journeyRow.remark}"> </hdiits:textarea>
			</td>
		 </tr>
		 <tr>
			
			
			
			<fmt:formatDate value="${depart_dt}" var="depart_dtfmt" pattern="dd/MM/yyyy"/>
			<td rowspan="1" colspan="1" width="10%"><hdiits:dateTime disabled="${journeyRow.viewFlag}" 
				name="depart_dt${journeyRow.srNo}" classcss="mandatorycontrol" captionid="HRMS.DummyDeprDt" bundle="${caption}"
				maxvalue="31/12/2099" 	mandatory="true" />
				<script>
				 	var index="${journeyRow.srNo}";
				 	document.getElementById("depart_dt"+index).value="${journeyRow.departDate}";
				 </script>
				
			</td>
			
			<td rowspan="1" colspan="1" width="10%"><hdiits:text size="1"
				name="depart_tm${journeyRow.srNo}" classcss="mandatorycontrol"  disable="${journeyRow.viewFlag}" default="${journeyRow.departTime}" id="depart_tm${journeyRow.srNo}" 
				mandatory="false" maxlength="5" /><label class='mandatoryindicator'>*</label>
			</td>
			
			<td rowspan="1" colspan="1" width="10%"><hdiits:dateTime disabled="${journeyRow.viewFlag}"
				name="arr_dt${journeyRow.srNo}" classcss="mandatorycontrol" captionid="HRMS.DummyDeprDt" bundle="${caption}"
				 maxvalue="31/12/2099" mandatory="true"  />
				 <script>
				 	document.getElementById("arr_dt"+index).value="${journeyRow.arrivaldate}";
				 </script>
			</td>
			<td rowspan="1" colspan="1" width="10%"><hdiits:text
				name="arr_tm${journeyRow.srNo}"  readonly="${journeyRow.viewFlag}" classcss="mandatorycontrol" mandatory="false" id="arr_tm${journeyRow.srNo}" size="1" maxlength="5" default="${journeyRow.arrivalTime}"
				 /><label class='mandatoryindicator'>*</label>
			</td>
			
			<td rowspan="1" colspan="1" height="25%" align="center" id="trncol${journeyRow.srNo}" >
				<hdiits:select name="sub_train${journeyRow.srNo}" classcss="mandatorycontrol"  readonly="${journeyRow.viewFlag}" id="sub_train${journeyRow.srNo}" caption="train1"
					mandatory="false" validation="sel.isrequired" default="${journeyRow.trainName}"
					sort="false">
					<hdiits:option value='321611'><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="TrainListvar" items="${journeyRow.trainList}">
						<hdiits:option value="${TrainListvar.lookupId}">
						${TrainListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> 
				<hdiits:select name="sub_own${journeyRow.srNo}" readonly="${journeyRow.viewFlag}" classcss="mandatorycontrol" id="sub_own${journeyRow.srNo}" caption="train${journeyRow.srNo}"
					mandatory="false" validation="sel.isrequired" style="display:none" default="${journeyRow.ownedBy}"
					onchange="enabletype(this)">
					<hdiits:option value='321611'><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="owned_byvar" items="${journeyRow.ownedByList}">
						<hdiits:option value="${owned_byvar.lookupId}">
							${owned_byvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select><label class='mandatoryindicator'>*</label>
			</td>
			<td rowspan="1" colspan="1" height="25%" align="center" id="clscol${journeyRow.srNo}">
				<hdiits:select name="sub_class${journeyRow.srNo}"  readonly="${journeyRow.viewFlag}" classcss="mandatorycontrol" id="sub_class${journeyRow.srNo}" caption="class1 "
					mandatory="false" validation="sel.isrequired" default="${journeyRow.trnclass}"
					sort="false">
					<hdiits:option value='321611'><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
					<c:forEach var="ClassListvar" items="${journeyRow.trnClassList}">
						<hdiits:option value="${ClassListvar.lookupId}"> 
						${ClassListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select>
				<hdiits:select name="sub_veh${journeyRow.srNo}" classcss="mandatorycontrol" id="sub_veh${journeyRow.srNo}" caption="class${journeyRow.srNo} "
					mandatory="false" validation="sel.isrequired" style="display:none" readonly="${journeyRow.viewFlag}" default="${journeyRow.vehicleType}" > 
					<hdiits:option value='321611'><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
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
	<br>
	</div>
		<c:if  test="${travelMstVO.advTaken eq 'Y' }">
		
		<center>
		<br>
		<br>
		<hdiits:fieldGroup titleCaptionId="HRMS.AdvDtl" bundle="${caption}" expandable="false" id="advanceDtl" style="width:50%;">
		<table>
			
			<tr>
				<td align="right">
					 <hdiits:caption captionid="HRMS.AligibleAmt" bundle="${caption}" />
				</td>
				<td>
					:
				</td>
				<td align="justify">
				
					<c:out value="${travelMstVO.permissibleAmt}"></c:out> <a href="#" onclick="showTADACalculation(this)"><font color="RED"><hdiits:caption captionid="HRMS.showDtl" bundle="${caption}" /></font> </a>
				</td>
			</tr>
			<tr>
				<td align="right">
					<hdiits:caption	captionid="HRMS.RequestedAmt" bundle="${caption}" /> 	
				</td>
				<td>
					:
				</td>
				<td align="left">
					<c:out value="${travelMstVO.requestedAmt}"></c:out>
				</td>
			</tr>
			<tr>
				<td align="right">
					<hdiits:caption	captionid="HRMS.PayMod" bundle="${caption}" /> 	
				</td>
				<td>
					:
				</td>
				<td align="left">
					<hdiits:select name="modOfPay" id="modOfPay"  default="${travelMstVO.cmnLookupMst.lookupId}" readonly="true">
						<hdiits:option  value="321611"><fmt:message key="HRMS.Select" bundle="${caption}" /></hdiits:option>
						<c:forEach var="payMD" items="${payMode}">
							<hdiits:option value="${payMD.lookupId}">${payMD.lookupName}</hdiits:option>
						</c:forEach>					
					</hdiits:select>
				</td>
			</tr>
			
		</table>
		</hdiits:fieldGroup>
	</center>
	</c:if>
	<center>
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
	 
	
	</div>
	</div>
	

	<!-- Hidden Fields -->
	<hdiits:hidden id="journeySize" name="journeySize" default="${fn:length(journeyRowList)}"/>
		<hdiits:hidden id="chkBoxArray" name="chkBoxArray" />
		<hdiits:hidden id="xmlFilePath" default="${xmlPath}" name="xmlFilePath"/>
		<hdiits:hidden id="advFlag" name="advFlag" default="${advFlag}"/>
		<hdiits:hidden id="tourId" name="tourId" default="${tourDtlVO.tourId}"/>
			<hdiits:validate controlNames="test"
		locale='<%=(String)session.getAttribute("locale")%>' />
		
	<!-- End Hidden Field -->
	<script type="text/javascript">	
		hideProgressbar();
		
	 	if('${advFlag}'=='true'){
	 		document.getElementById('advanceDtl').style.display="";
			document.getElementById('attachtb').style.display="";
			var advTakenRB=document.getElementsByName('AdvTaken');
			advTakenRB[0].checked="true";
	 	}
		for(var i=0;i<document.getElementById('journeySize').value;i++){
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
		document.body.style.scrollbarBaseColor='#F0F4FB';
	</script>

</hdiits:form>




<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

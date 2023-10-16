
<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables" scope="request" />

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RtaReimbDisReq.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="amtDue" value="${resValue.amtDue}"></c:set>
<c:set var="advDtlParentObj" value="${resValue.hrRtaReqDtl1}"></c:set>
<c:set var="advDtlObj" value="${resValue.hrRtaReqDtl}"></c:set>
<c:set var="fileNo" value="${resValue.fileNo}"></c:set>
<c:set var="reimbRemarks" value="${resValue.reimbReqRemarks}"></c:set>
<c:set var="lArrObjTravel" value="${resValue.HrTravelDtls}"></c:set>
<c:set var="XmlFileTravel" value="${resValue.XmlFileTravel}"></c:set>
<c:set var="lArrObjTransport" value="${resValue.HrTransportDtls}"></c:set>
<c:set var="XmlFileTransport" value="${resValue.XmlFileTransport}"></c:set>
<c:set var="reportView" value="${resValue.reportView}"></c:set>

<script>
	var alertDisReimbMsg=new Array();
	alertDisReimbMsg[0]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>';
	alertDisReimbMsg[1]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>';
	alertDisReimbMsg[2]="Your browser does not support AJAX!";
</script>

<hdiits:form name="viewRtaReq" validate="true" action="./hrms.htm?actionFlag=updateRtaStatus" method="post"
	encType="multipart/form-data">
	
	
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<br/>
<c:if test="${advDtlObj.isAdvReimb=='2'}">

		<hdiits:hidden name="fileId" caption="status"
			default="${advDtlObj.rtaId}" />
		<hdiits:hidden name="isAdvReimb" id="isAdvReimb"
			default="${advDtlObj.isAdvReimb}" />

	<c:if test="${not empty lArrObjTravel }">
			
	<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="TravelGrp" titleCaptionId="HRMS.travelDtl">

			<table id="rtaTravelTable" name="rtaTravelTable" class="tabtable"
				style="border-collapse: collapse;" borderColor="BLACK"  border="1" align="center">
			<tr>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DeparturePlace" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DepartureDate" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ArrivalPlace" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ArrivalDate" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DistanceInKm" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.TransportMode" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ClassofAcc" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.NoOfFares" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.FaresPaid" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.total" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.attachment" /></b></td>
			</tr>

			</table>
			<table name="travelRequest" id="travelRequest" class="tabtable"
				border="1" width="100%">
				<tr>
					<td class="fieldLabel" colspan="8"><jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="attachmentBiometric" />
						<jsp:param name="formName" value="viewRtaReq" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />
						<jsp:param name="multiple" value="Y" />
						<jsp:param name="rowNumber" value="1" />
					</jsp:include></td>
				</tr>
			</table>

			<c:forEach items="${lArrObjTravel}" var="HrTravelTuples"
				varStatus="x">
				<c:set var="curXMLTravelFileName" value="${XmlFileTravel[x.index]}"></c:set>
				<c:set var="attachmentId"
					value="${HrTravelTuples.cmnAttachmentMst.attachmentId}"></c:set>
				<c:set var="travelMode10"
					value="${HrTravelTuples.cmnLookupMstByModeOfTrans.lookupDesc}"></c:set>
				<c:set var="class10"
					value="${HrTravelTuples.cmnLookupMstByClassOfAccomod.lookupDesc}"></c:set>
				<c:set var="departurePlace" value="${HrTravelTuples.departurePlace}" />
				<fmt:formatDate value="${HrTravelTuples.departureDate}"
					pattern="dd/MM/yyyy" var="fromDate" />
				<c:set var="departureDate" value="${fromDate}" />
				<c:set var="arrivedPlace" value="${HrTravelTuples.arrivedPlace}" />
				<fmt:formatDate value="${HrTravelTuples.arrivedDate}"
					pattern="dd/MM/yyyy" var="todate" />
				<c:set var="arrivedDate" value="${todate}" />
				<c:set var="distanceInKm" value="${HrTravelTuples.distanceInKm}" />
				<c:set var="noOfPersonTravelling"
					value="${HrTravelTuples.noOfPersonTravelling}" />
				<c:set var="faresPaid" value="${HrTravelTuples.faresPaid}" />
				<c:set var="total" value="${HrTravelTuples.total}" />
				<c:set var="remarks" value="${HrTravelTuples.remarks}" />

				<script type="text/javascript">
		
			var xmlTravelFileName = '${curXMLTravelFileName}';
			
			var displayFieldATravel  = new Array('${departurePlace}','${departureDate}','${arrivedPlace}',
			'${arrivedDate}','${distanceInKm}','${travelMode10}','${class10}','${noOfPersonTravelling}',
			'${faresPaid}','${total}','${remarks}');
			addDBDataInTableAttachmentRta('rtaTravelTable','encXML',displayFieldATravel,xmlTravelFileName,
			'${attachmentId}','viewRtaReq','attachmentBiometric','', '','viewRecord');					
			</script>
			</c:forEach>

			<BR>

	<script>
		document.getElementById('target_uploadattachmentBiometric').style.display='none';
		document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';
	</script>
	</hdiits:fieldGroup>
	<br/>
</c:if>



		<c:if test="${not empty lArrObjTransport}">
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="TransportGrp" titleCaptionId="HRMS.transportdetail">
				
			<table id="rtaTransportTable" name="rtaTransportTable"
				class="tabtable" style="border-collapse: collapse; " borderColor="BLACK"  border="1" align="center">
				


				<tr>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DeparturePlace" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DepartureDate" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ArrivalPlace" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ArrivalDate" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DistanceInKm" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.TransportMode" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ClassofAcc" /></b></td>


					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.total" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.attachment" /></b></td>
					

				</tr>

			</table>



			<table name="transportRequest" id="transportRequest" class="tabtable"
				border="1">
				<tr>
					<td class="fieldLabel" colspan="8"><jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="attachmentBiometricT" />
						<jsp:param name="formName" value="viewRtaReq" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />
						<jsp:param name="multiple" value="Y" />
						<jsp:param name="rowNumber" value="1" />
					</jsp:include></td>
				</tr>
			</table>

			<c:forEach items="${lArrObjTransport}" var="HrTransportTuples"
				varStatus="x">
				<c:set var="curXMLTransportFileName"
					value="${XmlFileTransport[x.index]}"></c:set>
				<c:set var="attachmentIdT"
					value="${HrTransportTuples.cmnAttachmentMst.attachmentId}"></c:set>
				<c:set var="travelModeT10"
					value="${HrTransportTuples.cmnLookupMstByModeOfTrans.lookupDesc}"></c:set>
				<c:set var="classT10"
					value="${HrTransportTuples.cmnLookupMstByClassOfAccomod.lookupDesc}"></c:set>
				<c:set var="departurePlaceT"
					value="${HrTransportTuples.departurePlace}" />
				<fmt:formatDate value="${HrTransportTuples.departureDate}"
					pattern="dd/MM/yyyy" var="fromDate" />
				<c:set var="departureDateT" value="${fromDate}" />
				<c:set var="arrivalPlaceT" value="${HrTransportTuples.arrivalPlace}" />
				<fmt:formatDate value="${HrTransportTuples.arrivalDate}"
					pattern="dd/MM/yyyy" var="todate" />
				<c:set var="arrivalDateT" value="${todate}" />
				<c:set var="distanceInKmT" value="${HrTransportTuples.distanceInKm}" />
				<c:set var="totalT" value="${HrTransportTuples.total}" />


				<script type="text/javascript">
		
			var xmlTransportFileName = '${curXMLTransportFileName}';
			
			var displayFieldATransport  = new Array('${departurePlaceT}','${departureDateT}','${arrivalPlaceT}',
			'${arrivalDateT}','${distanceInKmT}','${travelModeT10}','${classT10}','${totalT}');
			addDBDataInTableAttachmentRta('rtaTransportTable','encXMLT',displayFieldATransport,xmlTransportFileName,
			'${attachmentIdT}','viewRtaReq','attachmentBiometricT','', '','viewRecordT');					
			</script>
			</c:forEach>

			<script>
				document.getElementById('target_uploadattachmentBiometricT').style.display='none';
				document.getElementById('formTable1attachmentBiometricT').firstChild.firstChild.style.display='none';
			</script>
	</hdiits:fieldGroup>
	<br/>
</c:if>

<c:if test="${not empty reimbRemarks}">
<hdiits:fieldGroup bundle="${commonLables}"  expandable="false" collapseOnLoad="false" titleCaptionId="HRMS.Notification" id="rtaReimbDetails">
<table name="reimbReqDtl" id="reimbReqDtl" class="tabtable" border="0" width="100%">
	<tr>
 		<td width="30%">
			<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.reimbReqNum"/></b> :
		</td>
		<td width="20%"> 
			<b><i>${fileNo}</i></b>
		</td>
		<td width="20%" align="center">
			<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.remarks"/></b>
		</td>
		<td width="30%">
			<hdiits:textarea mandatory="false" rows="2" cols="17" readonly="true" name="reimbRemarks" id="reimbRemarks" default="${reimbRemarks}" caption="reimbRemarks"  />
		</td>
	</tr>
</table>
</hdiits:fieldGroup>
<br/>
</c:if>


		<hdiits:hidden name="status" id="status"
			default="${advDtlObj.status}" />
			
<hdiits:fieldGroup bundle="${commonLables}"  id="RtaAdvDetails" expandable="true" titleCaptionId="HRMS.rtaAdvReqDtl">
		<table name="travelReimbDtl" id="travelReimbDtl" class="tabtable"
			border="0" width="100%">
			<tr>
				<c:if test="${not empty advDtlParentObj}">
					<c:if test="${advDtlParentObj.status==1}">
						<td align="center"><hdiits:caption bundle="${commonLables}" captionid="HRMS.advTaken" /></td>

						
					</c:if>
					<c:if test="${advDtlParentObj.status!=1}">
						<td align="center"><hdiits:caption bundle="${commonLables}" captionid="HRMS.advReq" /></td>

					
					</c:if>
					<td><hdiits:number mandatory="true" floatAllowed="true" name="advTaken" tabindex="1"
						id="advTaken" caption="advTaken" size="5"
						default="${advDtlParentObj.totalPayableAmt}" readonly="true" style="background-color: lightblue;" />
					</td>
					<td align="right"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b></td>
					<td align="center"><hdiits:hidden name="isAdvReimb" id="isAdvReimb"
						default="${advDtlObj.isAdvReimb}" /> <hdiits:hidden
						name="parentId" id="parentId" default="${advDtlObj.rtaId}" /> <hdiits:textarea
						mandatory="false" rows="5" cols="40" tabindex="2" 
						default="${advDtlParentObj.remarks}" name="remarks2"
						id="remarks2" caption="remarks" readonly="true" maxlength="2000" /></td>

				</c:if>

				<c:if test="${empty advDtlParentObj}">
					<td align="center"><hdiits:caption bundle="${commonLables}" captionid="HRMS.advTaken" /></td>

					<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="3"
						name="advTaken" id="advTaken" caption="advTaken" size="5"
						default="${advDtlObj.totalPayableAmt}" readonly="true" style="background-color: lightblue;" /></td>
				</c:if>



			</tr>
</table>
</hdiits:fieldGroup>
<br/>

<hdiits:fieldGroup bundle="${commonLables}"  id="ReimbAmtDetail" titleCaptionId="HRMS.reimbAmtDtl" expandable="true">
<table class="tabtable">
			<tr>
				<td align="center"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.grandTotal" /></b></td>
				<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="4"
					name="grandTotal" id="grandTotal" caption="grandTotal" size="5"
					default="${advDtlObj.grantTotalFaresTrans}" readonly="true" style="background-color: lightblue;" /></td>
				<td align="right" style="display:none"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b></td>
				<td><hdiits:hidden name="remarks" id="remarks"
					caption="remarks" default="${advDtlObj.remarks}" /></td>
			</tr>
			<tr>
				<td align="center"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.amtDue" /></b></td>
				<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="5"
					name="amtDue" id="amtDue" caption="amtDue" size="5"
					default="${amtDue}" readonly="true" style="background-color: lightblue;" /></td>
			</tr>
			<tr>
				<td align="center"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.sanctionAmt" /></b></td>
				<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="6"
					name="sanctionAmt" id="sanctionAmt" caption="sanctionAmt" size="5" onblur="validateSanctionedAmt();"
					default="${advDtlObj.sanctionedAmt}" maxlength="10"/></td>
			</tr>

</table>
</hdiits:fieldGroup>
<br/>
</c:if>
	<hdiits:jsField name="testSantAmt" jsFunction="validateSanctionedAmt()"/>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script>

</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

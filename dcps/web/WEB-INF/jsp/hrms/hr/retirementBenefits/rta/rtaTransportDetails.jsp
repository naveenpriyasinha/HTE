<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="travelModeListT" value="${resValue.travelModeListT}"></c:set>


<script>
var totalT=0;
var alertTransportMsg=new Array();
alertTransportMsg[0]='<fmt:message  bundle="${commonLables}" key="HRMS.departurePlaceTReq"/>';
alertTransportMsg[1]='<fmt:message  bundle="${commonLables}" key="HRMS.departureDateTReq"/>';
alertTransportMsg[2]='<fmt:message  bundle="${commonLables}" key="HRMS.arrivedPlaceTReq"/>';
alertTransportMsg[3]='<fmt:message  bundle="${commonLables}" key="HRMS.arrivedDateTReq"/>';
alertTransportMsg[4]='<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmTReq"/>';
alertTransportMsg[5]='<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmTReqFormat"/>';
alertTransportMsg[6]='<fmt:message  bundle="${commonLables}" key="HRMS.travelModeT10Req"/>';
alertTransportMsg[7]='<fmt:message  bundle="${commonLables}" key="HRMS.classT0Req"/>';
alertTransportMsg[8]='<fmt:message  bundle="${commonLables}" key="HRMS.totalTReq"/>';
alertTransportMsg[9]='<fmt:message  bundle="${commonLables}" key="HRMS.totalTReqFormat"/>';
alertTransportMsg[10]='<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDateT"/>';
alertTransportMsg[11]='<fmt:message  bundle="${commonLables}" key="HR.AJAX_BROWSER"/>';
alertTransportMsg[12]='<fmt:message key="HRMS.select" />';

</script>

<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="rtaTransDetails" titleCaptionId="HRMS.transportReq">
<table name="transportRequest" id="transportRequest" class="tabtable" border="1">

				<tr colspan="8">
					<td align="center" ><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.Departure" /></b></td>
					<td align="center" ><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.Arrival" /></b></td>
					<td align="center" ><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DistanceInKm" /></b></td>
					<td align="center" ><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.TransportMode" /></b></td>
					<td align="center" ><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.total" /></b></td>
				</tr>
				
					<hdiits:hidden name="transportDetailId" id="transportDetailId" default="0"></hdiits:hidden>
					
					<tr colspan="8">
		
					<td width="13%" align="center"><hdiits:text mandatory="true"
						name="departurePlaceT" tabindex="15" id="departurePlaceT" caption="Departure" validation="txt.isrequired" maxlength="2000"/><br>
					   <hdiits:caption bundle="${commonLables}" captionid="HRMS.dateFormat" /> 
					
					<hdiits:dateTime name="departureDateT" tabindex="16" captionid="HRMS.Arrival" bundle="${commonLables}" 
						mandatory="true" onblur="checkDateT();" ></hdiits:dateTime></td>
					<hdiits:hidden name="departuretDateT1" id="departureDateT1" default="0" ></hdiits:hidden>
					<td width="13%" align="center"><hdiits:text mandatory="true" name="arrivalPlaceT" validation="txt.isrequired"
						tabindex="17" id="arrivalPlaceT" caption="Arrival" maxlength="2000"/><br>
					   <hdiits:caption bundle="${commonLables}" captionid="HRMS.dateFormat" /> 
					<hdiits:dateTime name="arrivalDateT" captionid="HRMS.Arrival" bundle="${commonLables}" tabindex="18"
						mandatory="true" onblur="checkDateT();" ></hdiits:dateTime></td>
					<hdiits:hidden name="arrivalDateT1" id="arrivalDateT1" default="0" ></hdiits:hidden>
					<td width="5%" align="center"><hdiits:number mandatory="true" floatAllowed="true"
						name="distanceInKmT"  id="distanceInKmT" maxlength="10" caption="Distance" size="5" tabindex="19" /></td>
					<td width="10%" align="center"><hdiits:select name="travelModeT10" id="travelModeT10" tabindex="20"
					onchange="popupClassT('0');">
					<hdiits:option value="0"><fmt:message bundle="${commonLables}" key="HRMS.select" /></hdiits:option>
						<c:forEach var="travelModeT" items="${travelModeListT}" >
							<hdiits:option value="${travelModeT.lookupName}">
								${travelModeT.lookupDesc}
							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					<hdiits:select sort="false" name="classT0"
						tabindex="21" id='classT0' size="1" caption="drop_down"
						mandatory="false" onchange="" >
						<hdiits:option value="0"><fmt:message bundle="${commonLables}" key="HRMS.select" /></hdiits:option>
						<c:forEach var="class" items="">
							<hdiits:option value="">

							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					</td>
					
					
					<td width="10%" align="center"><hdiits:number name="totalT" id="totalT" mandatory="true" floatAllowed="true" tabindex="22" maxlength="10"
					/></td>
				</tr>
				<tr>
			<td class="fieldLabel" colspan="8">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="attachmentBiometricT" />
					<jsp:param name="formName" value="rtaSubmit" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="Attachment" />			
					<jsp:param name="multiple" value="Y" />
					<jsp:param name="rowNumber" value="1" />
				</jsp:include>				
			</td>
		</tr>
				<tr>
			<td align="center" id="addButton" colspan="8"><hdiits:button type="button" tabindex="23" name="AddT" id="AddT" captionid="HRMS.add" 
			        bundle="${commonLables}" onclick="startupAjaxFormValidationT('AddT');" />
					&nbsp;<hdiits:button type="button" name="UpdateT" id="UpdateT" tabindex="24" readonly="true" captionid="HRMS.update"
					bundle="${commonLables}" onclick="startupAjaxFormValidationT('UpdateT');" />
					</td>
		</tr>
				
			
		
	
		</table>	

		
		<table id="rtaTransportTable" name="rtaTransportTable" class="tabtable" style="border-collapse: collapse; " borderColor="BLACK"  border=1 align="center">
		<tr>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DeparturePlace" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DepartureDate" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ArrivalPlace" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ArrivalDate" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DistanceInKm" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.TransportMode" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ClassofAcc" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.total" /></b></td>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.action" /></b></td>
			
			
		</tr>
	</table>	
	
	<table id="rtaTransportTotaltable" name="rtaTransportTotaltable" class="tabtable" border="0" align="center">
		
		<tr align="center">
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td align="right" ><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.total" /></b></td>
						<td align="left" >
							<hdiits:number name="totalTransport" tabindex="25" floatAllowed="true" style="background-color: lightblue;" readonly="true" id="totalTransport" mandatory="true" default="0.0" />
						</td>
						
		
		
	</table>
	
</hdiits:fieldGroup>
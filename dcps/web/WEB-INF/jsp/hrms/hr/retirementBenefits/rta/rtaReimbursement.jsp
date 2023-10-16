<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="travelModeList" value="${resValue.travelModeList}"></c:set>

<script>

var total=0;
var alertTravelMsg=new Array();
alertTravelMsg[0]='<fmt:message  bundle="${commonLables}" key="HRMS.departurePlaceReq"/>';
alertTravelMsg[1]='<fmt:message  bundle="${commonLables}" key="HRMS.departureDateReq"/>';
alertTravelMsg[2]='<fmt:message  bundle="${commonLables}" key="HRMS.arrivedPlaceReq"/>';
alertTravelMsg[3]='<fmt:message  bundle="${commonLables}" key="HRMS.arrivedDateReq"/>';
alertTravelMsg[4]='<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmReq"/>';
alertTravelMsg[5]='<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmReqFormat"/>';
alertTravelMsg[6]='<fmt:message  bundle="${commonLables}" key="HRMS.travelMode10Req"/>';
alertTravelMsg[7]='<fmt:message  bundle="${commonLables}" key="HRMS.class0Req"/>';
alertTravelMsg[8]='<fmt:message  bundle="${commonLables}" key="HRMS.noOfPersonTravellingReq"/>';
alertTravelMsg[9]='<fmt:message  bundle="${commonLables}" key="HRMS.noOfPersonTravellingReqFormat"/>';
alertTravelMsg[10]='<fmt:message  bundle="${commonLables}" key="HRMS.faresPaidReq"/>';
alertTravelMsg[11]='<fmt:message  bundle="${commonLables}" key="HRMS.faresPaidReqFormat"/>';
alertTravelMsg[12]='<fmt:message  bundle="${commonLables}" key="HRMS.remarksReq"/>';
alertTravelMsg[13]='<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDate"/>';
alertTravelMsg[14]='<fmt:message  bundle="${commonLables}" key="HR.AJAX_BROWSER"/>';

	
</script>
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="rtaReimbDetails" titleCaptionId="HRMS.travelreq">
	<table name="travelRequest" id="travelRequest" class="tabtable" border="1" width="100%">
			<tr>
					<td align="center" width="13%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.Departure" /></b></td>
					<td align="center" width="13%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.Arrival" /></b></td>
					<td align="center" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.DistanceInKm" /></b></td>
					<td align="center" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.TransportMode" /></b></td>
					<td align="center" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.ClassofAcc" /></b></td>
					<td align="center" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.NoOfFares" /></b></td>
					<td align="center" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.FaresPaid" /></b></td>
					
			</tr>

					<!-- Declaration of travelReqId  -->					
					<hdiits:hidden name="travelReqqId" id="travelReqqId" default="0"></hdiits:hidden>
					<hdiits:hidden name="rtaId" id="rtaId" default="0" ></hdiits:hidden>
			
				<tr colspan="8">
		
					<td width="13%" align="center"><hdiits:text mandatory="true" validation="txt.isrequired" 
						name="departurePlace" tabindex="1" id="departurePlace"  caption="Departure" /><br>
					<hdiits:dateTime name="departureDate" captionid="HRMS.Departure" bundle="${commonLables}" tabindex="2"
						mandatory="true" onblur="checkDepArrDate();" ></hdiits:dateTime>
						<b> <hdiits:caption bundle="${commonLables}" captionid="HRMS.dateFormat" /> </b>
						</td>
					
					<hdiits:hidden name="departureDate1" id="departureDate1" default="0" ></hdiits:hidden>
					<td width="13%" align="center"><hdiits:text  mandatory="true" name="arrivedPlace" validation="txt.isrequired"
						tabindex="3" id="arrivedPlace" caption="Arrival" /><br>
					<hdiits:dateTime name="arrivedDate"  captionid="HRMS.Arrival" bundle="${commonLables}" tabindex="4"
						mandatory="true"  onblur="checkDepArrDate();" ></hdiits:dateTime>
											  <b><hdiits:caption bundle="${commonLables}" captionid="HRMS.dateFormat" /> </b>
						</td>

					<hdiits:hidden name="arrivedDate1" id="arrivedDate1" default="0" ></hdiits:hidden>
					<td width="5%" align="center"><hdiits:number  mandatory="true"  maxlength="6"  floatAllowed="true"
						name="distanceInKm" id="distanceInKm" caption="Distance" size="5" tabindex="5" /></td>
					<td width="10%" align="center"><hdiits:select name="travelMode10" id="travelMode10" tabindex="6"
					 onchange="popupClass('0');">
					<hdiits:option value="0"><fmt:message bundle="${commonLables}" key="HRMS.select"/></hdiits:option>
						<c:forEach var="travelMode" items="${travelModeList}">
							<hdiits:option value="${travelMode.lookupName}">
								${travelMode.lookupDesc}
							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					</td>
					<td width="10%" align="center"><hdiits:select sort="false" name="class0"
						tabindex="7" id='class0' size="1" caption="drop_down" 
						mandatory="true" onchange="">
						<hdiits:option value="0"><fmt:message bundle="${commonLables}" key="HRMS.select"/></hdiits:option>
						<c:forEach var="class" items="">
							<hdiits:option value="">

							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					</td>
					<td width="10%" align="center">
					<hdiits:number name="noOfPersonTravelling"  id="noOfPersonTravelling"  maxlength="4" mandatory="true" 
					 size="5" onblur="calTravelTotal();" floatAllowed="false" tabindex="8"/></td>
					<td width="10%" align="center"><hdiits:number name="faresPaid" id="faresPaid" tabindex="9" maxlength="10" mandatory="true" 
					 size="5" onblur="calTravelTotal();" floatAllowed="true" /></td>			
					
					
				</tr>	
				<tr>
					
					<td align="center" colspan="5"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b></td>
						
					<td align="center" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.total" /></b></td>	
					<td align="center"><hdiits:number floatAllowed="true" name="total" id="total" mandatory="true" style="background-color: lightblue;" readonly="true"
					tabindex="10" size="6" /></td>
						
				</tr>
				<tr>
					<td colspan="5" align="center"><hdiits:textarea name="remarks" id="remarks" mandatory="true" cols="60"
					tabindex="11"  /></td>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>
		
		<tr>
			<td class="fieldLabel" colspan="8">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="attachmentBiometric" />
					<jsp:param name="formName" value="rtaSubmit" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="Attachment" />			
					<jsp:param name="multiple" value="Y" />
					<jsp:param name="rowNumber" value="1" />
				</jsp:include>				
			</td>
		</tr>
		<tr>
			<td align="center" id="addButton" colspan="8"><hdiits:button 
					type="button" name="Add" id="Add" captionid="HRMS.add" tabindex="12"  bundle="${commonLables}" onclick="startupAjaxFormValidation('Add');" />
					&nbsp;<hdiits:button	type="button" name="Update" tabindex="13" id="Update" readonly="true" captionid="HRMS.update" 
			        bundle="${commonLables}" onclick="startupAjaxFormValidation('Update');" />
					</td>
					
		</tr>
		
	</table>

		
		<table id="rtaTable" name="rtaTable" class="tabtable" style="border-collapse: collapse;" borderColor="BLACK"  border=1 align="center">
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
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b>
			<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.action" /></b></td>
					
			
		</tr>
	</table>	
	
	<table id="rtaTravelTotaltable" name="rtaTravelTotaltable" class="tabtable" border="0" align="center">
		
		<tr align="center">
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td align="right" ><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.total" /></b></td>
						<td align="left" >
							<hdiits:number name="totalTravel" tabindex="14" floatAllowed="true" style="background-color: lightblue;" id="totalTravel" readonly="true" mandatory="true" default="0.0" />
							
						</td>
						
		
		
	</table>
</hdiits:fieldGroup>
	
	
	




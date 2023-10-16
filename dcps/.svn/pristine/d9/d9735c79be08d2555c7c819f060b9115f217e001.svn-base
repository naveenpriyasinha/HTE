
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript"
	src="script/hrms/ess/travel/travelCommonScripts.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="advMstCmbList" value="${resultValue.advMstCmbList}">
</c:set>
<c:set var="journeyDtl" value="${resultValue.journeyDtl}">
</c:set>
<c:set var="tourId" value="${resultValue.tourId}">
</c:set>
<c:set var="payModList" value="${resultValue.payMode}">
</c:set>
<c:set var="payModeId" value="${resultValue.payModeId}">
</c:set>

<c:set var="receivableAmt" value="${resultValue.grandTotal}">
</c:set>
<c:set var="maxAdvAmt" value="${resultValue.maxAdvAmt}">
</c:set>
<fmt:setBundle basename="resources.ess.travel.caption"
	var="caption" scope="request" />
<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
 
<script>
var travelAlertMsg=new Array();
	travelAlertMsg[0]="<fmt:message bundle='${alertLables}' key='HRMS.AdvReq'/>";
	travelAlertMsg[1]="<fmt:message bundle='${alertLables}' key='HRMS.ModeOfPay'/>";
	
</script>


<hdiits:form name="TravelAdv" validate="true" method="POST"
	encType="multipart/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="TRAVEL.ADVANCE" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<hdiits:fieldGroup titleCaptionId="TRAVEL.ADVANCE" bundle="${caption}" expandable="false"/>
	
	<c:if
		test="${empty advMstCmbList}">
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<font color="RED">
		<center><font color="RED"><fmt:message key="HRMS.NoRecord" bundle="${caption}" ></fmt:message> </font></center>
		
	</c:if>
	<c:if test="${not empty advMstCmbList}">
            	
				<table width="100%">
				<tr align="center" style="width: 50%">
					<td align="right"><b><fmt:message key="HRMS.TourName"
						bundle="${caption}" /></b></td>
						<td align="left" width="50%"> 
					
					<hdiits:select name="tourId" size="1" default="${tourId}"
						caption="tourId" id="tourId" onchange="addTourData(this)"
						tabindex="2" style="">
						<hdiits:option value="-1">------Select -----</hdiits:option>
						<c:forEach var="hrTourDtl" items="${advMstCmbList}">
							<hdiits:option value="${hrTourDtl.tourId}">
									${hrTourDtl.tourName}
							</hdiits:option>
						</c:forEach>
					</hdiits:select></td>
				</tr>
				</table>

				</c:if>
				
				<c:if test="${not empty journeyDtl}">
				<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
				<tr align="center">
					<td colspan="2">
					<table id='tripdtls' border="1" cellpadding="0" width="68%"
						cellspacing="0" BGCOLOR="WHITE"
						style="background: #eeeeee;padding: 2px;">
						<tr class="datatableheader">
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Departure_Place" bundle="${caption}" /> </td>
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Departure_Date" bundle="${caption}" /></td>
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Arrival_Place" bundle="${caption}" /></td>
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Arrival_Date" bundle="${caption}" /></td>
						</tr>
						<c:forEach items="${journeyDtl}" var="journeyDtlVO">
						<tr>
							
							<td style="width: 25%" colspan="1">${journeyDtlVO.cmnCityMstByTravelFromPlace.cityName}</td>
							<td style="width: 25%" colspan="1">
								<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss"	value="${journeyDtlVO.travelFromDate}" />
								<fmt:formatDate value="${bday}"  pattern="dd-MM-yyyy"/>
							</td>
							<td style="width: 25%" colspan="1">${journeyDtlVO.cmnCityMstByTravelToPlace.cityName}</td>
							<td style="width: 25%" colspan="1">
								<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss"	value="${journeyDtlVO.travelToDate}" />
								<fmt:formatDate value="${bday}"  pattern="dd-MM-yyyy"/>
							</td>
						</tr>
						</c:forEach>
					</table>
					
					</td>
				</tr>
				
				<tr>
					<td align="right"><b> <hdiits:caption captionid="RECEIVABLE.AALOW" bundle="${caption}" /></b></td>
					<td align="left"><font color="RED"> <label
						id="totalAmt" onclick="showTADACalculation(this)"></label>${receivableAmt}</font>
						<hdiits:hidden name="receivableAmt" id="receivableAmt" default="${receivableAmt}"/>
						<a href="#" onclick="showTADACalculation(this)">show Details </a></td>
				</tr>
				<tr>
					<td align="right"><b>  <hdiits:caption captionid="ADVANCE.AMT" bundle="${caption}" /></b></td>
					<td align="left"><hdiits:text name="advAmt" id="advAmt" default="${maxAdvAmt}"/></td>
				</tr>
				<tr>
					<td align="right"><b> <hdiits:caption captionid="MODE.PAYMENT" bundle="${caption}" /></b></td>
					<td align="left"><hdiits:select name="modOfPay" id="modOfPay" default="${payModeId}">
						<hdiits:option value="-1">------Select---------</hdiits:option>
						<c:forEach var="payMod" items="${payModList}">
							<hdiits:option value="${payMod.lookupId}">
							${payMod.lookupDesc}
					</hdiits:option>
						</c:forEach>
					</hdiits:select></td>
				</tr>

				<tr>
					<td align="right"><b><hdiits:caption captionid="HRMS.Remark" bundle="${caption}" /></b></td>
					<td align="left"><hdiits:textarea name="remark" id="remark"
						rows="5" /></td>
				</tr>
				<tr>
					<td align="right"><hdiits:button type="button" name="submitBt"
						value="Submit" captionid="HRMS.Submit"  bundle="${caption}" onclick="submitAdvanceDtls(this)" /></td>
					<td align="left"><hdiits:button type="button" name="Close"
						value="close" captionid="HRMS.Close" bundle="${caption}" onclick="closeAdvanceWindow(this)" /></td>
				</tr>
				</table>
				</c:if>
			 
			 
		
	</div>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
	
		</script>

 
	<hdiits:hidden name="xmlKey" />
	<hdiits:hidden name="perAmt" />

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>







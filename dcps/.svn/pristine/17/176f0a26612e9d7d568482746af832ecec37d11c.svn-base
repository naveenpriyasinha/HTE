
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

<c:set var="journeyDtl" value="${resultValue.journeyDtl}">
</c:set>
<c:set var="tourId" value="${resultValue.tourId}">
</c:set>
<c:set var="payModList" value="${resultValue.payMode}">
</c:set>
<c:set var="receivableAmt" value="${resultValue.grandTotal}">
</c:set>
<c:set var="maxAdvAmt" value="${resultValue.maxAdvAmt}">
</c:set>
<c:set var="tourDtlVO" value="${resultValue.tourDtlVO}">
</c:set>
<c:set var="travelMstVO" value="${resultValue.travelMstVO}">
</c:set>
<c:set var="cancleMessage" value="${resultValue.cancleMessage}">
</c:set>



<fmt:setBundle basename="resources.ess.travel.caption"
	var="caption" scope="request" />
<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
 



<hdiits:form name="TravelAdv" validate="true" method="POST" action="hdiits.htm?actionFlag=saveTravelInboxData"
	encType="multipart/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="TRAVEL.ADVANCE" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">
	

	 
			<center><strong><font color="RED"><c:out value="${cancleMessage}"></c:out> </font></strong></center>
			<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
			<hdiits:fieldGroup titleCaptionId="TRAVEL.ADVANCE" bundle="${caption}" expandable="false"/>
			<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
				

				<tr>
					<td align="right"><b>
					<fmt:message key="HRMS.TourName"  bundle="${caption}" />
					</b></td>
					<td align="left">
						${tourDtlVO.tourName}
					</td>
				</tr>

				<c:if test="${not empty journeyDtl}">
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
				</c:if>
				<tr>
					<td align="right"><b> <hdiits:caption captionid="RECEIVABLE.AALOW" bundle="${caption}" /></b></td>
					<td align="left"><font color="RED"> <label id="totalAmt" onclick="showTADACalculation(this)"></label>${receivableAmt}</font>
						<hdiits:hidden name="receivableAmt" id="receivableAmt" default="${receivableAmt}"/>
						<a href="#" onclick="showTADACalculation(this)">show Details </a></td>
				</tr>
				<tr>
					<td align="right"><b>  <hdiits:caption captionid="ADVANCE.AMT" bundle="${caption}" /></b></td>
					<td align="left"><hdiits:number readonly="true" size="5" name="advAmt" id="advAmt" default="${maxAdvAmt}"/></td>
				</tr>
				<tr>
					<td align="right"><b> <hdiits:caption captionid="MODE.PAYMENT" bundle="${caption}" /></b></td>
					<td align="left"><hdiits:select name="paymod" readonly="true" id="paymod" default="${travelMstVO.cmnLookupMst.lookupId}">
						<hdiits:option value="-1">------Select---------</hdiits:option>
						<c:forEach var="payMod" items="${payModList}">
							<hdiits:option value="${payMod.lookupId}">
							${payMod.lookupDesc}
			</hdiits:option>
						</c:forEach>
					</hdiits:select></td>
				</tr>
				<tr>
					<td align="right"><b>  <hdiits:caption captionid="HRMS.ApprovedAmount" bundle="${caption}" /></b></td>
					<td align="left"><hdiits:number  name="aproveAmt" size="5" id="aproveAmt"/></td>
				</tr>
				<tr>
					<td align="right"><b><hdiits:caption captionid="HRMS.Remark" bundle="${caption}" /></b></td>
					<td align="left"><hdiits:textarea name="remark" id="remark" 
						rows="5" /></td>
				</tr>
				 

			</table>
 
	</div>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
	
		</script>

	<hdiits:hidden name="reqType" id="reqType" default="A"/>
	<hdiits:hidden name="tourId" id="tourId" default="${tourId}"/>
	<hdiits:hidden name="xmlKey" />
	<hdiits:hidden name="perAmt" />
	
	<hdiits:validate controlNames="test"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>







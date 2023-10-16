
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
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="basicSal" value="${resultValue.basicSal}">
</c:set>
<c:set var="DAList" value="${resultValue.DAList}">
</c:set>
<c:set var="hrTravelDtlsSet" value="${resultValue.hrTravelDtlsSet}">
</c:set>
<c:set var="accomodationDtls" value="${resultValue.accomodationDtls}">
</c:set>
<c:set var="otherRate" value="${resultValue.otherRate}">
</c:set>

<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.travel.caption" var="caption"
	scope="request" />

<script type="text/javascript">
//global Variableza
var TA=0.0;
var othrRt=0.0;
var ordRt=0.0;
var totTA=0.0;
function closeWindows()
{
	window.close();
}
</script>

<hdiits:form name="frm1" validate="true" method="POST"
	encType="multipart/form-data">
	<br>
	<br>
	<table >
		<tr>
			<td>
				<strong>
					<hdiits:caption captionid="HRMS.BasicSal" bundle="${caption}" />: ${basicSal}
				</strong>
				
			</td>
		</tr>
	</table>
	<table width="100%">

		<tr>
		<td>
		<c:if test="${not empty accomodationDtls}">
		<table id="acomDtls" border="1" cellpadding="0" width="100%"
									cellspacing="0" BGCOLOR="WHITE"
									style="background: #eeeeee;padding: 2px">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" align="center" colspan="13"><font color="#ffffff">
				<strong>Accomodation Details</strong> </font></td>
			</tr>
			<tr class="datatableheader">
				<td colspan="1"><hdiits:caption captionid="HRMS.SRNO" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.FromDate" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.FromTime" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.ToDate" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.ToTime" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.Accomodation" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.LodingClaim" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.LodingCharge" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.FreeBording" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.FreeLodging" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.CktHouseStay" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.CktHouseStay" bundle="${caption}" /></td>
				<td colspan="1"><hdiits:caption captionid="HRMS.Description" bundle="${caption}" /></td>
			</tr>	
			<c:forEach var="accomDtls" items="${accomodationDtls}"> 
			<tr>
				<td colspan="1">Sr No.</td>
				<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss"
							value="${accomDtls.accomFromDate}" />
				<td colspan="1">
					<fmt:formatDate value="${bday}"  pattern="dd-MM-yyyy"/>
				</td>
				<td colspan="1"><fmt:formatDate value="${bday}"  pattern="HH:mm"/> </td>
				<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss"
							value="${accomDtls.accomToDate}" />
				<td colspan="1">
					<fmt:formatDate value="${bday}"  pattern="dd-MM-yyyy"/>
				</td>
				<td colspan="1"><fmt:formatDate value="${bday}"  pattern="HH:mm"/> </td>
				<td colspan="1">${accomDtls.acomodationType}</td>
				<td colspan="1">${accomDtls.hotelCharge}</td>
				<td colspan="1">${accomDtls.govLodging}</td>
				<td colspan="1">${accomDtls.govBording}</td>
				<td colspan="1">${accomDtls.hotelCharge}</td>
				<td colspan="1">${accomDtls.cktHouseClaimed}</td>
				<td colspan="1">${accomDtls.cktHousetCharge}</td>
				<td colspan="1">${accomDtls.description}</td>
				
			</tr>
			</c:forEach>
		</table>
		</c:if>
		<td>
		</tr>
		<tr>
		</tr>
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" align="center"><font color="#ffffff">
			
			<strong><fmt:message key="HRMS.TACalulation" bundle="${caption}" /></strong> </font></td>
		</tr>
		<tr>

			<table border="1" width="90%" align="center" >
				<tr height="25%" class="datatableheader">
					<td colspan="3" rowspan="1"><strong><hdiits:caption captionid="HRMS.Departure" bundle="${caption}" /></strong></td>
					<td colspan="3" rowspan="1"><strong><hdiits:caption captionid="HRMS.Arrival" bundle="${caption}" /></strong></td>
					<td rowspan="2"><strong><hdiits:caption captionid="HRMS.Conveyance_Mode" bundle="${caption}" /></strong></td>
					<td rowspan="2"><strong><hdiits:caption captionid="HRMS.Sub_Items" bundle="${caption}" /></strong></td>
					<td rowspan="2"><strong><hdiits:caption captionid="HRMS.Purose_Of_Stay" bundle="${caption}" /></strong></td>
					<td rowspan="2"><strong><hdiits:caption captionid="HRMS.Mileage" bundle="${caption}" /></strong></td>
					<td rowspan="2"><strong><hdiits:caption captionid="HRMS.Distance" bundle="${caption}" /></strong></td>
					<td rowspan="2"><strong><hdiits:caption captionid="HRMS.ApprovedAllowance" bundle="${caption}" /></strong></td>
				</tr>
				<tr class="datatableheader">
					<td><hdiits:caption captionid="HRMS.Place" bundle="${caption}" /></td>
					<td><hdiits:caption captionid="HRMS.Date" bundle="${caption}" /></td>
					<td><hdiits:caption captionid="HRMS.Time" bundle="${caption}" /></td>
					<td><hdiits:caption captionid="HRMS.Place" bundle="${caption}" /></td>
					<td><hdiits:caption captionid="HRMS.Date" bundle="${caption}" /></td>
					<td><hdiits:caption captionid="HRMS.Time" bundle="${caption}" /></td>
				</tr>
				<c:forEach var="hrTravelDtlsSet" items="${hrTravelDtlsSet}">
					<tr>
						<td align="left">${hrTravelDtlsSet.travelFromPlace.cityName}</td>
						<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss"
							value="${hrTravelDtlsSet.travelFromDate}" />
						<td align="left"><fmt:formatDate value="${bday}"
							pattern="dd/MM/yyyy" /></td>
						<td align="left"><fmt:formatDate value="${bday}"
							pattern="HH:MM" /></td>
						<td align="left">${hrTravelDtlsSet.travelToPlace.cityName}</td>
						<fmt:parseDate var="bday1" pattern="yyyy-MM-dd HH:mm:ss"
							value="${hrTravelDtlsSet.travelToDate}" />
						<td align="left"><fmt:formatDate value="${bday1}"
							pattern="dd/MM/yyyy" /></td>
						<td align="left"><fmt:formatDate value="${bday1}"
							pattern="HH:MM" /></td>
						<td align="left">${hrTravelDtlsSet.cmnLookupMstByTravelMode.lookupDesc}&nbsp;</td>
						<td align="left">${hrTravelDtlsSet.subItem}&nbsp;</td>
						<td align="left">${hrTravelDtlsSet.cmnLookupMstByPurposeOfStay.lookupDesc}&nbsp;</td>
						<td align="left">${hrTravelDtlsSet.mileage}&nbsp;</td>
						<td align="left">${hrTravelDtlsSet.distance}&nbsp;</td>
						<td align="left">${hrTravelDtlsSet.costIncurred}&nbsp;</td>
					</tr>
					<script>
		var tmptotTA="${hrTravelDtlsSet.costIncurred}";
		totTA=totTA+tmptotTA*1.0;
	</script>
				</c:forEach>
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1" align="left"><strong>Total TA </strong></td>
					<td colspan="1" align="left"><strong><label
						id="TAAmt"></label></strong></td>
				</tr>

			</table>
		</tr>
	</table>
	<br>
	<br>
	<table>
		<tr>
			<td>
				<hdiits:caption captionid="HRMS.OtherRate" bundle="${caption}" />:${otherRate}
			</td>
		</tr>
	</table>
	<br>
	<br>
	<table width="100%">

		<tr bgcolor="#386CB7">
			<td class="fieldLabel" align="center"><font color="#ffffff">
			<strong><fmt:message key="HRMS.DACalulation" bundle="${caption}" /></strong> </font></td>
		</tr>
		<tr>
		
			<table align="center" width="100%" border="1">

				<tr height="25%" class="datatableheader" style="border: ">
					<td colspan="1" rowspan="2"><hdiits:caption captionid="HRMS.Date" bundle="${caption}" /></td>
					<td colspan="1" rowspan="2"><hdiits:caption captionid="HRMS.Place" bundle="${caption}" /></td>
					<td colspan="1" rowspan="2"><hdiits:caption captionid="HRMS.DARate" bundle="${caption}" /></td>
					<td colspan="1" rowspan="2"><hdiits:caption captionid="HRMS.Accomodation" bundle="${caption}" /></td>
					<td colspan="2" rowspan="1"><hdiits:caption captionid="HRMS.OtherRate" bundle="${caption}" /></td>
					<td colspan="2" rowspan="1"><hdiits:caption captionid="HRMS.OrdinaryRate" bundle="${caption}" /></td>
					<td colspan="1" rowspan="2"><hdiits:caption captionid="HRMS.DA" bundle="${caption}" /></td>
				</tr>
				<tr class="datatableheader">
					<td colspan="1" rowspan="1"><hdiits:caption captionid="HRMS.Percentage" bundle="${caption}" /></td>
					<td colspan="1" rowspan="1"><hdiits:caption captionid="HRMS.INR" bundle="${caption}" /></td>

					<td colspan="1" rowspan="1"><hdiits:caption captionid="HRMS.Percentage" bundle="${caption}" /></td>
					<td colspan="1" rowspan="1"><hdiits:caption captionid="HRMS.INR" bundle="${caption}" /></td>
					
				</tr>
				<c:forEach var="DADtlsList" items="${DAList}">
					<tr>
						<td colspan="1" align="left">${DADtlsList.fromDate}</td>
						<td align="left">${DADtlsList.place}-${DADtlsList.cityclass}</td>
						<td align="left">${DADtlsList.DA}</td>
						<td align="left">${DADtlsList.accomodationType}&nbsp;</td>
						<td align="left">${DADtlsList.ordinaryRateInPer}</td>
						<td align="left">${DADtlsList.ordinaryRs}</td>
						<td align="left">${DADtlsList.otherRateInPer}</td>
						<td align="left">${DADtlsList.otherRs}</td>
						<td align="left">${DADtlsList.totalDA}</td>
					</tr>
					<script>
	var tmpTA="${DADtlsList.totalDA}";
	TA=TA+tmpTA*1.0;	
	var tmpothrRt="${DADtlsList.otherRs}";
	othrRt=othrRt+tmpothrRt*1.0;
	var tmpordRt="${DADtlsList.ordinaryRs}";
	ordRt=ordRt+tmpordRt*1.0;
</script>
				</c:forEach>
				 
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><font color=""#386CB7"><strong><hdiits:caption captionid="HRMS.TotalDA" bundle="${caption}" /></strong></font></td>
					<td colspan="1" align="left"><font color=""#386CB7"><strong><label
						id="ordRslb"></label></strong></font></td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1" align="left"><font color=""#386CB7"><strong><label
						id="othrRslb"></label></strong></font></td>
					<td colspan="1" align="left"><font color=""#386CB7"><strong><label
						id="totDAlb"></label></strong></font></td>
				</tr>
			</table>
			<script>
	document.getElementById('totDAlb').innerHTML=TA;
	document.getElementById('othrRslb').innerHTML=othrRt;
	document.getElementById('ordRslb').innerHTML=ordRt;
	document.getElementById('TAAmt').innerHTML=totTA;
	var totalAmt=TA+totTA;
</script>
			<br>
			<br>
			<table border="0" width="10%" align="center">
				<tr>
					<strong>Grand Total:<label id="grdTot"></label> </strong>
				</tr>
				<script>
	document.getElementById('grdTot').innerHTML=totalAmt;
</script>
				<br>
				<tr>
					<td><hdiits:button type="button" name="Print" value="Print" onclick="window.print()" /></td>
					<td><hdiits:button type="button" name="Close" value="Close"
						onclick="closeWindows()" /></td>
				</tr>

			</table>
			</tr>
	</table>

			</hdiits:form>
			<%
					} catch (Exception e) {
					e.printStackTrace();
				}
			%>
		
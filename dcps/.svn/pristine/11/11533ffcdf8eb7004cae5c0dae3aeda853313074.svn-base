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
<c:set var="hrTravelMstList" value="${resultValue.hrTravelMstList}">
</c:set>
<c:set var="travelDtlsList" value="${resultValue.travelDtlsList}">
</c:set>
<c:set var="advreqlist" value="${resultValue.advreqlist}">
</c:set>
<c:set var="payModList" value="${resultValue.payMod}">
</c:set>
<c:set var="travelReqId" value="${resultValue.travelReqId}">
</c:set>
<c:set var="requsetId" value="${resultValue.requsetId}">
</c:set>


<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.travel.caption" var="caption"
	scope="request" />
	


<script type="text/javascript">
//Global Variable
var amtVar=0;
function showTACalculation(form)
{
	//alert(form.title);
	
	var urlstyle="hdiits.htm?actionFlag=getTACalculation&tourId="+form.title;
	window.open(urlstyle,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
}
function forwardAdvReq()
{
	var urlstyle="hdiits.htm?actionFlag=forwardAdvReq";
	document.TravelAdv.action=urlstyle;
	document.TravelAdv.submit();
}
function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.TravelAdv.action=urlstyle;
		document.TravelAdv.submit();
	}
function approveAdvReq()
{
	
	var aproveAmt=document.getElementById('aprAmt').value;
	if(aproveAmt=="")
	{
		 alert("<fmt:message key="HRMS.provideapproveamt" bundle="${alertLables}" />");    
	
		return;
	}
	var totalAmt=document.getElementById('totalAmt').innerHTML;	
	var recAmt=totalAmt*0.80;
	if(aproveAmt>recAmt)
	{
		 alert("<fmt:message key="HRMS.amtnotgtthanreceivable" bundle="${alertLables}" />");    
		 return;
	}
	
	var urlstyle="hrms.htm?actionFlag=approveAdvReq";
	document.TravelAdv.action=urlstyle;
	document.TravelAdv.submit();
}
</script>
<br>
<br>

<hdiits:form name="TravelAdv" validate="true" method="POST"
			encType="multipart/form-data">
		
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
	<tr bgcolor="#386CB7" height="20" align="center">
		<td class="fieldLabel" colspan="4"><font color="#ffffff">
			<u><fmt:message key="TRAVEL.ADVANCE" bundle="${caption}" /></u>
			</font>
		</td>
	
	</tr>

	<tr>
		<td align="right"><b><fmt:message key="TOUR.ID"
								bundle="${caption}" /></b>
		</td>
		<td align="left">
			<label id="reqId"> </label>
		</td>
	</tr>
	
<tr align="center">
<td colspan="2">


<table id='tripdtls' border="1" cellpadding="0" width="68%"
							cellspacing="0" BGCOLOR="WHITE"
							style="background: #eeeeee;padding: 2px">
							<tr class="datatableheader">
								<td style="width: 45%"><hdiits:caption captionid="HRMS.Departure_Place" bundle="${caption}" /></td>
								<td style="width: 15%"><hdiits:caption captionid="HRMS.Departure_Date" bundle="${caption}" /></td>
								<td style="width: 45%"><hdiits:caption captionid="HRMS.Arrival_Place" bundle="${caption}" /></td>
								<td style="width: 15%"><hdiits:caption captionid="HRMS.Arrival_Date" bundle="${caption}" /></td>
							</tr>
		   					<c:forEach var="travelDtlsList" items="${travelDtlsList}">
		   					<tr>
								<td style="width: 45%">${travelDtlsList.travelFromPlace.cityName}</td>
								<td style="width: 15%">${travelDtlsList.travelFromDate}</td>
								<td style="width: 45%">${travelDtlsList.travelToPlace.cityName}</td>
								<td style="width: 15%">${travelDtlsList.travelToDate}</td>
							</tr>
		   					</c:forEach>
</table>
</td>
</tr>
<tr>
	<td align="right"><b><hdiits:caption captionid="RECEIVABLE.AALOW" bundle="${caption}" /></b></td>
	<td align="left"><label id="totalAmt" onclick="showTACalculation(this)"></label> </td>
</tr>
<tr>
	<td align="right"><b><hdiits:caption captionid="HRMS.RequestedAmount" bundle="${caption}" /></b></td>
	<td align="left"><hdiits:text name="advAmt" id="advAmt" /> </td>
</tr>
<tr>
	<td align="right"><b><hdiits:caption captionid="HRMS.ApprovedAmount" bundle="${caption}" /></b></td>
	<td align="left"><hdiits:text name="aprAmt" id="aprAmt" /> </td>
</tr>	

<tr>
	<td align="right"><b> <hdiits:caption captionid="MODE.PAYMENT" bundle="${caption}" /></b></td>
	<td align="left">
		<hdiits:select name="paymod" id="paymod" > 
			<hdiits:option value="-1">------Select---------</hdiits:option>
			<c:forEach var="payMod" items="${payModList}">
			<hdiits:option value="${payMod.lookupId}">
							${payMod.lookupDesc}
			</hdiits:option>
		</c:forEach>
		</hdiits:select> 
	</td>
</tr>
<tr>
<tr>
<tr>
	<td align="right"><b><hdiits:caption captionid="HRMS.Remark" bundle="${caption}" /></b></td>
	<td align="left"><hdiits:textarea name="remark" id="remark" rows="5" /> </td>
</tr>
<tr style="display: none">
	<td align="right"><input type="button" name="approveBt" value="Approve" onclick="approveAdvReq()"></td>
	<td align="left"><input type="button" name="forwardBt" value="Forward" onclick="forwardAdvReq()"><input type="button" name="Close" value="close" onclick="closewindow()"> </td>
</tr>

</table>

		<hdiits:hidden name="xmlKey"  />
		<hdiits:hidden name="requsetId"  />
		
</hdiits:form>
<script>
var totalAmt=0;
</script>
<c:forEach var="HrTravelMstList" items="${hrTravelMstList}">
<script>
	
	var tmpAmt=0;
	tmpAmt="${HrTravelMstList.totalAmount}";	
	totalAmt=totalAmt+tmpAmt*1;
</script>
</c:forEach>
<c:forEach var="HrTravelMstList" items="${hrTravelMstList}">
		<script>
			document.getElementById('reqId').innerHTML="${HrTravelMstList.tourName}";
			document.getElementById('totalAmt').title="${HrTravelMstList.tourId}";
		</script>
</c:forEach>
<c:forEach var="advreqlist" items="${advreqlist}">
<script type="text/javascript">
	document.getElementById('advAmt').value="${advreqlist.userAmt}";
	document.getElementById('remark').value="${advreqlist.remark}";
	document.getElementById('remark').disabled="true";
	document.getElementById('paymod').value="${advreqlist.paymentMod}";
	document.getElementById('paymod').disabled="true";
	document.getElementById('advAmt').disabled="true";
	document.getElementById('totalAmt').innerHTML="${advreqlist.permissibleAmt}";
	document.getElementById('reqId').disabled="true";
	document.getElementById('requsetId').value="${requsetId}";
</script>

</c:forEach>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>








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
<c:set var="cnt" value="1"></c:set>
<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.travel.caption" var="caption"
	scope="request" />

<script type="text/javascript">
//global Variableza
var TA=0.0;
var othrRt=0.00;
var ordRt=0.00;
var totTA=0.00;
function reCalculateDA(form){
	 var tmpVal=form.value;
	 var tmpArray=new Array();
	 tmpArray=tmpVal.split('.');
	 if(tmpArray.length>2)
	 {
	 	alert('Please enter Valid Number');
	 	form.value="0.00";
	 	
	 }
	 if(tmpArray.length<2)
	 {
	 
	 	var strVal=form.value.toString()+'.00';
	 	form.value=strVal;
	 }
	 
	 
	var tb=document.getElementById('taTable');
	var tbLength=tb.rows.length;
	 
	//Other Rate Calculation
	var otherRate=parseFloat(document.getElementById('ohterRate').value);
	var cnt=1;
	tbLength=tbLength*1-1;
	for(var i=2;i<tbLength;i++)
	{
		document.getElementById('ordRsRate'+cnt).value=((parseFloat(document.getElementById('ordPerRate'+cnt).value)*otherRate))/100.00;
		tmpVal=document.getElementById('ordRsRate'+cnt).value;
		tmpArray=tmpVal.split('.');
	 	if(tmpArray.length<2)
		{
	 
		 	var strVal=tmpVal.toString()+'.00';
	 		document.getElementById('ordRsRate'+cnt).value=strVal;
		 }
		cnt++;
	}
	//Ordinary Rate Calculation
	cnt=1;
	for(var i=2;i<tbLength;i++)
	{
		document.getElementById('othrRsRate'+cnt).value=((parseFloat(document.getElementById('othrPerRate'+cnt).value)*document.getElementById('DARate'+cnt).value))/100.00;
		tmpVal=document.getElementById('othrRsRate'+cnt).value;
		tmpArray=tmpVal.split('.');
	 	if(tmpArray.length<2)
		{
	 
		 	var strVal=tmpVal.toString()+'.00';
	 		document.getElementById('othrRsRate'+cnt).value=strVal;
		 }
		cnt++;
	}
	//Reset Value of DA
	cnt=1;
	for(var i=2;i<tbLength;i++)
	{
		tmpVal=document.getElementById('DARate'+cnt).value;
		tmpArray=tmpVal.split('.');
	 	if(tmpArray.length<2)
		{
		 	var strVal=tmpVal.toString()+'.00';
	 		document.getElementById('DARate'+cnt).value=strVal;
		 }
		cnt++;
	}
	//Total DA Calculation
	cnt=1;
	for(var i=2;i<tbLength;i++)
	{
		document.getElementById('totalDA'+cnt).value=parseFloat(document.getElementById('othrRsRate'+cnt).value)+parseFloat(document.getElementById('ordRsRate'+cnt).value);
		tmpVal=document.getElementById('totalDA'+cnt).value;
		tmpArray=tmpVal.split('.');
	 	if(tmpArray.length<2)
		{
	 
		 	var strVal=tmpVal.toString()+'.00';
	 		document.getElementById('totalDA'+cnt).value=strVal;
		 }
		cnt++;
	}
	//Grand TA Calulation
	var totalDA=0;
	cnt=1;
	for(var i=2;i<tbLength;i++)
	{
		totalDA=parseFloat(document.getElementById('totalDA'+cnt).value)+totalDA;
		 
		cnt++;	
	}
	document.getElementById('totDAlb').value=totalDA;
	tmpVal=document.getElementById('totDAlb').value;
	tmpArray=tmpVal.split('.');
	if(tmpArray.length<2)
	{
	 
	 	var strVal=tmpVal.toString()+'.00';
		document.getElementById('totDAlb').value=strVal;
	 }
	//Grand Other Rate
	var totalOthr=0;
	cnt=1;
	for(var i=2;i<tbLength;i++)
	{
		totalOthr=parseFloat(document.getElementById('othrRsRate'+cnt).value)+totalOthr;
		cnt++;	
	}
	document.getElementById('totalOthr').value=totalOthr;
	tmpVal=document.getElementById('totalOthr').value;
	tmpArray=tmpVal.split('.');
	if(tmpArray.length<2)
	{
	 
	 	var strVal=tmpVal.toString()+'.00';
		document.getElementById('totalOthr').value=strVal;
	 }
	//Grand Ordinary Rate
	var totalOrdr=0;
	cnt=1;
	for(var i=2;i<tbLength;i++)
	{
		totalOrdr=parseFloat(document.getElementById('ordRsRate'+cnt).value)+totalOrdr;
		cnt++;	
	}
	
	document.getElementById('totalOrdr').value=totalOrdr;
	tmpVal=document.getElementById('totalOrdr').value;
	tmpArray=tmpVal.split('.');
	if(tmpArray.length<2)
	{
	 
	 	var strVal=tmpVal.toString()+'.00';
		document.getElementById('totalOrdr').value=strVal;
	 }
	//Grand Total Value
	document.getElementById('grdTot').value=parseFloat(document.getElementById('totDAlb').value)+parseFloat(document.getElementById('TAAmt').value);
	tmpVal=document.getElementById('grdTot').value;
	tmpArray=tmpVal.split('.');
	if(tmpArray.length<2)
	{
	 
	 	var strVal=tmpVal.toString()+'.00';
		document.getElementById('grdTot').value=strVal;
	 }
}
function closeWindows()
{
	window.close();
}
</script>

<hdiits:form name="frm1" validate="true" method="POST"
	encType="multipart/form-data">
	<br>
	<br>
	<table>
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
						<td align="left" style="text-align: righ">${hrTravelDtlsSet.mileage}&nbsp;</td>
						<td align="left" style="text-align: righ">${hrTravelDtlsSet.distance}&nbsp;</td>
						<td align="left" style="text-align: righ">${hrTravelDtlsSet.costIncurred}&nbsp;</td>
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
					<td colspan="1" align="left"><strong>
					<hdiits:number   style="border: none;text-align: right" size="5" readonly="true" id="TAAmt" name="TAAmt"  />
					</strong></td>
				</tr>

			</table>
		</tr>
	</table>
	<br>
	<br>
	<table>
		<tr>
			<td>
				<hdiits:caption captionid="HRMS.OtherRate" bundle="${caption}" />:<hdiits:number name="ohterRate" id="ohterRate"  default="${otherRate}" style="border: none;background-color:#eeddcc;text-align: right" size="5" onblur="reCalculateDA(this)"  />
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
		
			<table align="center" width="100%" border="1" id="taTable">

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
						<td colspan="1" align="right">${DADtlsList.fromDate}</td>
						<td align="right">${DADtlsList.place}-${DADtlsList.cityclass}</td>
						<td align="right"><hdiits:number default="${DADtlsList.DA}" style="border: none;background-color:#eeddcc;text-align: right" size="5" onblur="reCalculateDA(this)"  id="DARate${cnt}" name="DARate${cnt}" /> </td>
						<td align="right">${DADtlsList.accomodationType}&nbsp;</td>
						<td align="right"><hdiits:number default="${DADtlsList.ordinaryRateInPer}"  style="border: none;text-align: right"  readonly="true" size="5"  id="ordPerRate${cnt}" name="ordPerRate${cnt}" /> </td>
						<td align="right"><hdiits:number default="${DADtlsList.ordinaryRs}" style="border: none;text-align: right" size="5" readonly="true" id="ordRsRate${cnt}" name="ordRsRate${cnt}"  /></td>
						<td align="right"><hdiits:number default="${DADtlsList.otherRateInPer}" style="border: none;text-align: right" size="5" readonly="true" id="othrPerRate${cnt}" name="othrPerRate${cnt}"  /></td>
						<td align="right"><hdiits:number default="${DADtlsList.otherRs}" style="border: none;text-align: right" size="5" readonly="true" id="othrRsRate${cnt}" name="othrRsRate${cnt}"  /></td>
						<td align="right"><hdiits:number default="${DADtlsList.totalDA}" style="border: none;text-align: right" size="5" readonly="true" id="totalDA${cnt}" name="totalDA${cnt}"  /></td>
					</tr>
					<script>
	var tmpTA="${DADtlsList.totalDA}";
	TA=TA+tmpTA*1.00;	
	var tmpothrRt="${DADtlsList.otherRs}";
	othrRt=othrRt+tmpothrRt*1.00;
	var tmpordRt="${DADtlsList.ordinaryRs}";
	ordRt=ordRt+tmpordRt*1.00;
</script>
				<c:set var="cnt" value="${cnt+1}"></c:set>
				</c:forEach>
				 
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><font color=""#386CB7"><strong><hdiits:caption captionid="HRMS.TotalDA" bundle="${caption}" /></strong></font></td>
					<td colspan="1" align="left"><font color=""#386CB7">
					<strong>
					<hdiits:number   style="border: none" size="5" id="totalOrdr" name="totalOrdr"  />
					</strong></font></td>
					<td colspan="1">&nbsp;</td>
					<td colspan="1" align="left"><font color=""#386CB7"><strong>
					<hdiits:number   style="border: none;text-align: right" size="5" id="totalOthr" name="totalOthr"  />
					</strong></font></td>
					<td colspan="1" align="left"><font color=""#386CB7"><strong>
					<hdiits:number   style="border: none;text-align: right" size="5" id="totDAlb" name="totDAlb"  />
					</strong></font></td>
				</tr>
			</table>
			<script>
	document.getElementById('totDAlb').value=TA;
	document.getElementById('totalOthr').value=othrRt;
	document.getElementById('totalOrdr').value=ordRt;
	document.getElementById('TAAmt').value=totTA;
	var totalAmt=TA+totTA;
</script>
			<br>
			<br>
			<table border="0" width="10%" align="center">
				<tr align="center">
					<td align="right">
						<hdiits:caption captionid="HRMS.GrandTotal" bundle="${caption}" />:
					</td>
					<td align="left">	
						<hdiits:number   style="border: none;text-align: right" readonly="true" size="5" id="grdTot" name="grdTot"  />  
					</td>
				</tr>
				<script>
	document.getElementById('grdTot').value=totalAmt;
</script>
				<br>
				<tr>
					<td align="right"><hdiits:button type="button" name="Print" value="Print" onclick="window.print()" /></td>
					<td align="left"><hdiits:button type="button" name="Close" value="Close"
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
		
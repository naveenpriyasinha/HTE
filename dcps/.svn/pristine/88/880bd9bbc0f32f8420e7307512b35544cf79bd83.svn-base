<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.*"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="trnTablRqst" value="${resultObj.resultValue.TrnTablRqst}"></c:set>
<c:set var="trnTarqstHdr" value="${resultObj.resultValue.TrnTarqstHdr}"></c:set>
<c:set var="trnTarqstExpsumm" value="${resultObj.resultValue.TrnTarqstExpsumm}"></c:set>

<c:set var="resValueDtls" value="${resultObj.resultValue.BillDtls}"></c:set>
<c:set var="trnTablHdr" value="${resValueDtls.TrnTablHdr}"></c:set>
<c:set var="trnTablAmtDtls" value="${resValueDtls.TrnTablAmtDtls}"></c:set>
<c:set var="trnTablTrvlDtlsLst" value="${resValueDtls.TrnTablTrvlDtlsLst}"></c:set>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.onlinebillprep.TALabels" var="OnlineTaBillLabels" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.TAAlerts" var="OnlineTaBillAlerts" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillprepAlerts" scope="application"/>


<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>


<head>
	<script type="text/javascript" src="script/hrms/onlinebillprep/TABill.js"> </script>
	<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		 	 
	<script type="text/javascript">
		
		var TA_ALRT_VALIDHOUR = "<fmt:message key='TA.ALRT.VALIDHOUR' bundle='${OnlineTaBillAlerts}' />";
		var TA_ALRT_PAYNOTNULL = "<fmt:message key='TA.ALRT.PAYNOTNULL' bundle='${OnlineTaBillAlerts}' />";
		var TA_ALRT_NETCLAIMNOTNULL = "<fmt:message key='TA.ALRT.NETCLAIMNOTNULL' bundle='${OnlineTaBillAlerts}' />";
		var TA_ALRT_PASSEDRSNOTNULL = "<fmt:message key='TA.ALRT.PASSEDRSNOTNULL' bundle='${OnlineTaBillAlerts}' />";
		var TA_ALRT_SANCTIONAMTNOTNULL = "<fmt:message key='TA.ALRT.SANCTIONAMTNOTNULL' bundle='${OnlineTaBillAlerts}' />";
		var TA_ALRT_CROSSTOTALNOTNULL = "<fmt:message key='TA.ALRT.CROSSTOTALNOTNULL' bundle='${OnlineTaBillAlerts}' />";
		
		var minYear=1900;
		var maxYear=2500;
		var SRCH_DTFORMAT = "<fmt:message key="SRCH.DTFORMAT" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_VALMNTH = "<fmt:message key="SRCH.VALMNTH" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_VALDAY = "<fmt:message key="SRCH.VALDAY" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_VALDIGIT = "<fmt:message key="SRCH.VALDIGIT" bundle="${onlinebillprepAlerts}"></fmt:message>"+minYear+"<fmt:message key="SRCH.AND" bundle="${onlinebillprepAlerts}"></fmt:message>"+maxYear;
		var SRCH_VALDT = "<fmt:message key="SRCH.VALDT" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_PROYR = "<fmt:message key="SRCH.PROYR" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_PROMNTH = "<fmt:message key="SRCH.PROMNTH" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_PRODAY = "<fmt:message key="SRCH.PRODAY" bundle="${onlinebillprepAlerts}"></fmt:message>";
		
						
		function isValidBillData()
		{
			return  validateTABillData();			
		}
   </script>
   
   <style>
   	.tabstyle 
 	{
		border-width: 5px 1px 1px 1px; 
		border-color: #2065c0;
		border-style: solid ;
	}
	
	legend 
	{
		padding-left:5px;
		padding-right:5px;
		font-weight:normal; 
		font-family:verdana;
			
		border-width: 0px 0px 1px 0px; 
		border-color: #2065c0;
		border-style: solid ;
	}
   </style>
   
</head>
<c:if test="${resValue.isNewFromRejected != 'Y'}">
	<hdiits:hidden name="hidTrnTablHdrId" id="hidTrnTablHdrId" default="${trnTablHdr.trnTablHdrId}"/> 
</c:if>

<c:choose>

	<c:when test="${resValue.EditBill != null && resValue.EditBill == 'Y'}">


		<c:set var="sno" value="0" />
		<c:set var="totalFare" value="0" />
		
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b>Employee Pay Detail</b></legend>
				
		<table width="65%" align="left" class="Label">
			<hdiits:tr>

				<hdiits:td width="25%" align="left">
			    	   <fmt:message key="TA.PAY" bundle="${OnlineTaBillLabels}"/>
				</hdiits:td>

				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.empPay}" name="empPay" id="empPay" maxlength="10" onkeypress="amountFormat(this)" class="texttag mandatorycontrol" />&nbsp;&nbsp;<font color="red">*</font>
					<input type="hidden" name="digi6_0_EMP_PAY" />
				</hdiits:td>

				<hdiits:td width="18%" align="left">

				</hdiits:td>

				<hdiits:td width="35%" align="left">
					       <fmt:message key="TA.PTA" bundle="${OnlineTaBillLabels}" />
				 </hdiits:td>

				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.empPta}" name="empPta" id="empPta" maxlength="10" onkeypress="amountFormat(this)" />
					<input type="hidden" name="digi6_0_EMP_PTA" />
				</hdiits:td>
				
			</hdiits:tr>

			<hdiits:tr>

				<hdiits:td width="25%" align="left"> 
			   		<fmt:message key="TA.CA" bundle="${OnlineTaBillLabels}" />
			   	</hdiits:td>

				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.empCa}" name="empCa" id="empCa" maxlength="10" onkeypress="amountFormat(this)" />
					<input type="hidden" name="digi6_0_EMP_CA" />
				</hdiits:td>
				
				<hdiits:td width="18%" align="left">

				</hdiits:td>
				
				<hdiits:td width="35%" align="left">
			    	<fmt:message key="TA.HEADQUARTER" bundle="${OnlineTaBillLabels}" /> 
	    		</hdiits:td>
				
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.headQtr}" name="headQtr" id="headQtr" maxlength="20" />
					<input type="hidden" name="digi6_0_HEAD_QTR" />
				</hdiits:td>
				
			</hdiits:tr>

		</table>
		
		</fieldset>
		
		<br><br>
				
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b><fmt:message key="TA.TRVBILLHEADING" bundle="${OnlineTaBillLabels}" /></b></legend>
		
		<table border="0" cellspacing="2" cellpadding="3" align="center" width="100%">
			<tr>
				<td align="center">&nbsp;</td>
			</tr>
			
			<tr>
				<td align="center">&nbsp;</td>
			</tr>
		</table>

		<table align="left">
			<hdiits:tr>
				<hdiits:td>
					<hdiits:button name="butAddRowOne" type="button" captionid="TA.BUTTON.ADDROW" bundle="${OnlineTaBillLabels}" onclick="addRow(),getGrossRailwayFareAmount(),getTotalDaclimed(),getGrossTrlTotal()" />
				</hdiits:td>
			</hdiits:tr>
		</table>
		
		<br>
		<br>

		<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:960px; height:150px; overflow: scroll; overflow-x:scroll; overflow-y:scroll;  " >

			<table id="ParentTable" border="0" cellspacing="1" cellpadding="5" width="1500" class="HLabel">
	
				<tr border="5" class="TableHeaderBG">
	
					<td width="9%"><b><fmt:message key="TA.DEPARTURESTATION" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6.5%"><b><fmt:message key="TA.DEPARTUREDATE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="4%"><b><fmt:message key="TA.DEPARTUREHOUR" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="8%"><b><fmt:message key="TA.ARRIVALSTATION" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="7.8%"><b><fmt:message key="TA.ARRIVALDATE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="4.5%"><b><fmt:message key="TA.ARRIVALHOUR" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5.8%"><b><fmt:message key="TA.TYPEOFJOURNEY" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.RAILWAYCLASS" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.RAILWAYFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.RAILWAYAMOUNT" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.ORDINARYFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.OTHERFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.DAILYALLOWANCE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.NOOFDAYS" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.PURPOSEOFJOURNEY" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.TOTAL" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="15%"><b><fmt:message key="TA.REMARKS" bundle="${OnlineTaBillLabels}" /></b></td>
		
				</tr>
			</table>

			<table id="TABill" border="0" cellspacing="0" cellpadding="1" width="100%" class="Label">
					
				<c:forEach var="trnTablTrvlDtlsLst"	items="${resValueDtls.TrnTablTrvlDtlsLst}" varStatus="No">
	
					<hdiits:hidden name="hidTrnTablTrvlDtlsId" id="hidTrnTablTrvlDtlsId" default="${trnTablTrvlDtlsLst.trnTablTrvlDtlsId}" />
	
					<hdiits:tr>
	
						<hdiits:td width="2%">
							<c:set var="sno" value="${sno+1}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.depStation}"	name="depStation" size="15" id="theValue" maxlength="20" />
							<input type="hidden" name="digi4_${sno-1}_DEP_STATION" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<fmt:formatDate var="TrvlDtlsDepDate" dateStyle="full" pattern="dd/MM/yyyy" value="${trnTablTrvlDtlsLst.depDate}" />
							<input type="text" value="${TrvlDtlsDepDate}" name="depDate[<c:out value="${sno}"></c:out>]" id="depDate[<c:out value="${sno}"></c:out>]" size="7" id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src=images/CalendarImages/ico-calendar.gif   width=20 onclick='window_open("depDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.depHour}" name="depHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)" />
							<input type="hidden" name="digi4_${sno-1}_DEP_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.arrStation}" name="arrStation" size="15" id="theValue" maxlength="20" />
							<input type="hidden" name="digi4_${sno-1}_ARR_STATION" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<fmt:formatDate var="TrvlDtlsArrDate" dateStyle="full" pattern="dd/MM/yyyy" value="${trnTablTrvlDtlsLst.arrDate}" />
							<input type="text" value="${TrvlDtlsArrDate}" name="arrDate[<c:out value="${sno}"></c:out>]" id="arrDate[<c:out value="${sno}"></c:out>]" size="7" id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src=images/CalendarImages/ico-calendar.gif   width=20 onclick='window_open("depDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.arrHour}" name="arrHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)"/>
							<input type="hidden" name="digi4_${sno-1}_ARR_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.trvlMode}" name="trvlMode" size="7" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_${sno-1}_TRVL_MODE" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.fareClass}"	name="fareClass" size="7" id="theValue" maxlength="20" />
							<input type="hidden" name="digi4_${sno-1}_FARE_CLASS" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.fareNumber}" name="fareNumber" size="7" id="theValue" maxlength="10" onkeypress="digitFormat()" />
							<input type="hidden" name="digi4_${sno-1}_FARE_NUMBER" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="${trnTablTrvlDtlsLst.fareAmt}" name="fareAmt" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)"	onblur="getGrossRailwayFareAmount()" />
							<c:set var="totalFare" value="${trnTablTrvlDtlsLst.fareAmt+totalFare}"></c:set>
							<input type="hidden" name="digi4_${sno-1}_FARE_AMT" /> 
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.admsblOrdRate}"	name="admsblOrdRate" size="7" id="theValue" maxlength="10"	onkeypress="amountFormat(this)" />
							<input type="hidden" name="digi4_${sno-1}_ADMSBL_ORD_RATE" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.admsblOthRate}"	name="admsblOthRate" size="7" id="theValue" maxlength="10"	onkeypress="amountFormat(this)" />
							<input type="hidden" name="digi4_${sno-1}_ADMSBL_OTH_RATE" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.admsblDa}" name="admsblDa" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getTotalDaclimed()" />
							<c:set var="admslDa" value="${trnTablTrvlDtlsLst.admsblDa}"></c:set>
							<input type="hidden" name="digi4_${sno-1}_ADMSBL_DA" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.numOfDaysClmed}" name="numOfDaysClmed" size="7" id="theValue" maxlength="10" onkeypress="digitFormat()" onblur="getTotalDaclimed()"/>
							<c:set var="numOfDaysClmed"	value="${trnTablTrvlDtlsLst.numOfDaysClmed}"></c:set>
							<c:set var="totalDaClmed" value="${totalDaClmed + (admslDa * numOfDaysClmed)}"></c:set>
							<input type="hidden" name="digi4_${sno-1}_NUM_OF_DAYS_CLMED" />
						</hdiits:td>
	
						<hdiits:td width="9%">
							<input type="text" value="${trnTablTrvlDtlsLst.purpose}" name="purpose" size="10" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_${sno-1}_PURPOSE" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="${trnTablTrvlDtlsLst.totalAmt}" name="totalAmt" size="8" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossTrlTotal()" />							
							<c:set var="daGrossttl" value="${daGrossttl + trnTablTrvlDtlsLst.totalAmt}"></c:set>
							<input type="hidden" name="digi4_${sno-1}_TOTAL_AMT" />
						</hdiits:td>
	
						<hdiits:td width="5%">
							<input type="text" value="${trnTablTrvlDtlsLst.remarks}" name="remarks" size="12" id="theValue" maxlength="30"/>
							<input type="hidden" name="digi4_${sno-1}_REMARKS" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<c:if test='${sno > 1}'>
								<img src="images/CalendarImages/DeleteIcon.gif"	onclick="delRow(this),getGrossRailwayFareAmount(),getTotalDaclimed(),getGrossTrlTotal()" />
							</c:if>
						</hdiits:td>
	
					</hdiits:tr>
	
				</c:forEach>
				<hdiits:hidden name="sno" id="sno" default="${sno}" />
	
				<c:if test="${sno == 0}">
					<hdiits:tr>
	
						<hdiits:td width="2%">
							<c:set var="sno" value="${sno+1}"></c:set>
							
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="depStation" size="15"	id="theValue" maxlength="20" />
							<input type="hidden" name="digi4_0_DEP_STATION" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="depDate[<c:out value="${sno}"></c:out>]" id="depDate[<c:out value="${sno}"></c:out>]" size="7" id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src=images/CalendarImages/ico-calendar.gif   width=20 onclick='window_open("depDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
	
						<hdiits:td width="4%">
							<input type="text" value="" name="depHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)"/>
							<input type="hidden" name="digi4_0_DEP_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="arrStation" size="15"	id="theValue" maxlength="20"/>
							 <input type="hidden" name="digi4_0_ARR_STATION" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="arrDate[<c:out value="${sno}"></c:out>]" id="arrDate[<c:out value="${sno}"></c:out>]" size="7" id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src=images/CalendarImages/ico-calendar.gif   width=20 onclick='window_open("arrDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="arrHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)"/>
							<input type="hidden" name="digi4_0_ARR_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="trvlMode" size="7" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_0_TRVL_MODE" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="fareClass" size="7" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_0_FARE_CLASS" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="fareNumber" size="7" id="theValue" maxlength="10" onkeypress="digitFormat()" />
							<input type="hidden" name="digi4_0FARE_NUMBER" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="" name="fareAmt" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossRailwayFareAmount()" />
							<input type="hidden" name="digi4_0_FARE_AMT" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="admsblOrdRate" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" />
							<input type="hidden" name="digi4_0_ADMSBL_ORD_RATE" /> 
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="admsblOthRate" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)"	 />
							<input type="hidden" name="digi4_0_ADMSBL_OTH_RATE" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="admsblDa" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getTotalDaclimed()" />
							<input type="hidden" name="digi4_0}_ADMSBL_DA" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="numOfDaysClmed" size="7" id="theValue" maxlength="10" onkeypress="digitFormat()" onblur="getTotalDaclimed()"/>
							 <input type="hidden" name="digi4_0_NUM_OF_DAYS_CLMED" />
						</hdiits:td>
	
						<hdiits:td width="9%">
							<input type="text" value="" name="purpose" size="10" id="theValue" maxlength="20" />
							<input type="hidden" name="digi4_0_PURPOSE" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="" name="totalAmt" size="10" id="theValue" maxlength="8" onkeypress="amountFormat(this)" onblur="getGrossTrlTotal()" />
							<input type="hidden" name="digi4_0}_TOTAL_AMT" />
						</hdiits:td>
	
						<hdiits:td width="5%">
							<input type="text" value="" name="remarks" size="12" id="theValue" maxlength="30"/>
							<input type="hidden" name="digi4_0_REMARKS" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							
						</hdiits:td>
	
					</hdiits:tr>
					
				</c:if>
	
			</table>
		</div>

		<table border="0" cellspacing="2" cellpadding="3" align="center" width="100%">

			<tr>				
				<table border="0" cellspacing="2" cellpadding="3" align="right" width="20%">
					<tr>
						<td align="left"><b><fmt:message key="TA.TA" bundle="${OnlineTaBillLabels}" /></b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlta"><c:out value="${totalFare}" default="0"></c:out></label></b></td>
					</tr>
					
					<tr>
						<td align="left"><b><fmt:message key="TA.DA" bundle="${OnlineTaBillLabels}" /> </b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlda"><c:out value="${daGrossttl}" default="0"></c:out></label></b></td>
					</tr>
					
					<tr>
						<td align="left"><b><fmt:message key="TA.GROSSTOTAL" bundle="${OnlineTaBillLabels}" /> </b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlgross"><c:out value="${totalFare + daGrossttl}" default="0"></c:out></label></b></td>
					</tr>
				</table>			
			</tr>

			<tr>
				<td align="center" >&nbsp;</td>
			</tr>

			<tr class="TableHeaderBG" width="100%">
				<td align="center" width="100%" class="HLabel" >
					
				</td>
			</tr>

		</table>
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b><fmt:message key="TA.TRVAMTHEADING" bundle="${OnlineTaBillLabels}" /></b></legend>
		
		 
		<table border="0" cellspacing="1" cellpadding="2" width="85%" align="center" class="Label" >
			<hdiits:hidden name="hidTrnTablAmtDtlsId" id="hidTrnTablAmtDtlsId" default="${trnTablAmtDtls.trnTablAmtDtlsId}" />

			<tr>
				<td height="15" >&nbsp;</td>
			</tr>

			<tr class="datatableheader"  style="font-size: 12px;">
				<td align="center" width="50%" colspan="3" class="HLabel"><b><fmt:message key="TA.EXPENSE" bundle="${OnlineTaBillLabels}" /></b></td>				
			</tr>
				
			<tr >
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.RAILNSTEAMERFARE" bundle="${OnlineTaBillLabels}" /> </td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="rlwStmFare" id="rlwStmFare"	value="${totalFare}" onkeypress="amountFormat(this)" onblur="getGrossAmtTotal()" />
					<input type="hidden" name="digi5_0_RLW_STM_FARE" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
						<fmt:message key="TA.ROADMILEPERPAISA_1" bundle="${OnlineTaBillLabels}" /> 
						<input type="text" name="rdTrvlChrgsPs"	value="${trnTablAmtDtls.rdChrgsPsperkm}" size="2" maxlength="10" onkeypress="amountFormat(this)"> 
						
						<fmt:message key="TA.ROADMILEPERPAISA_2" bundle="${OnlineTaBillLabels}" />
						<input name="rdTrvlChrgsKm" value="1" type="text" size="2" onkeypress="amountFormat(this)">&nbsp;km
				</td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="rdTrvlChrgs" id="rdTrvlChrgs" value="${trnTablAmtDtls.rdTrlFare}" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossAmtTotal()" />
								  <input type="hidden" name="digi5_0_RD_TRL_FARE" />	
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.DAYOFCLAIMED" bundle="${OnlineTaBillLabels}" /> </td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="daysClaimed" id="daysClaimed" value="${totalDaClmed}" maxlength="10" onkeypress="digitFormat()" onblur="getGrossAmtTotal()" />
					<input type="hidden" name="digi5_0_TTL_DA_CLMED" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.INCLEXPENSES" bundle="${OnlineTaBillLabels}" /> </td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="incExp" id="incExp" value="${trnTablAmtDtls.incdntlExp}" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossAmtTotal()" />
					<input type="hidden" name="digi5_0_INCDNTL_EXP" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
					<b><fmt:message key="TA.GROSSTOTAL" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" id="grossTot" name="grossTotal" value="${trnTablAmtDtls.grossTotal}" maxlength="10" onkeypress="amountFormat(this)" onblur="getNetClaimedAmt()" />&nbsp;&nbsp;<font color="red">*</font>
					<input type="hidden" name="digi5_0_GROSS_TOTAL" />
				</td>
			</tr>
			
			<tr class="datatableheader" style="font-size: 12px;">
				<td align="center" width="4%" colspan="3" class="HLabel">
					<b><fmt:message key="TA.DEDUCTIONS" bundle="${OnlineTaBillLabels}" /></b></td>
			</tr>


			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.AMTOFTAADVANCE" bundle="${OnlineTaBillLabels}" /> : </td>
				<td align="left" width="55%" class="odd"> 
					: &nbsp;&nbsp;<input type="text" name="taAdv" value="${trnTablAmtDtls.dedTaAdv}" maxlength="10" onkeypress="amountFormat(this)" onblur="setDedTotal()" />
					<input type="hidden" name="digi5_0_DED_TA_ADV" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> 
						<fmt:message key="TA.PTAFORDAYSGCSR_1" bundle="${OnlineTaBillLabels}" /> 
						<input type="text" size="2"	name="ptaDays" value="${trnTablAmtDtls.dedPtaDays}" maxlength="10"	onkeypress="digitFormat()">
						<input type="hidden" name="digi5_0_DED_PTA_DAYS" />
						<fmt:message key="TA.PTAFORDAYSGCSR_2" bundle="${OnlineTaBillLabels}" />
				</td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="pta" value="${trnTablAmtDtls.dedPta}" maxlength="10" onkeypress="amountFormat(this)" onblur="setDedTotal()" />
					<input type="hidden" name="digi5_0_DED_PTA" />
				</td>
			</tr>
			
			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
					<b><fmt:message key="TA.DEDTOT" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" id="dedTotal" name="dedTotal" value="${trnTablAmtDtls.dedTaAdv + trnTablAmtDtls.dedPta}" maxlength="10" onkeypress="amountFormat(this)" onblur="getNetClaimedAmt()" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="3"> &nbsp </td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="right" width="40%" class="odd">
					<b><fmt:message key="TA.NETCLAIMED" bundle="${OnlineTaBillLabels}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" id="netAmt" name="netAmt" value="${trnTablAmtDtls.netClaimed}" maxlength="10" onkeypress="amountFormat(this)" onblur="setPassedForRs()" />&nbsp;&nbsp;<font color="red">*</font>
					<input type="hidden" name="digi5_0_NET_CLAIMED" />
				</td>
			</tr>
			
			<tr>
				<td colspan="3"> &nbsp </td>
			</tr>

			<tr >
				<td align="left" width="4%"></td>
				<td align="left" width="40%">
					<b> <fmt:message key="TA.PASSFORRS" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%">
					: &nbsp;&nbsp;<input type="text" name="passedAmt" value="${trnTablAmtDtls.billPassedAmt}" maxlength="10" readonly="readonly" onkeyup="amtInWord()" onkeypress="amountFormat(this)" class="texttag mandatorycontrol" />&nbsp;&nbsp;<font color="red">*</font>
								  <input type="hidden" name="digi5_0_BILL_PASSED_AMT" />	
				</td>
			</tr>

			<tr>
				<td align="left" width="4%"></td>
				<td align="left" width="40%">
					<b><fmt:message key="TA.PASSEDFORINWORD" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%">
					: &nbsp;&nbsp;<b><label id="amtInWord"></label></b>
					<script type="text/javascript">
						amtInWord();					
					</script>
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" ></td>
				<td align="left" width="40%">
					<fmt:message key="TA.DATE" bundle="${OnlineTaBillLabels}" /> :
				  	<fmt:formatDate var="TrvlDtlsBillPassedDate" dateStyle="full"	pattern="dd/MM/yyyy" value="${trnTablAmtDtls.billPassedDate}" />
					<input type="calender" name="txtFrmDt"	value="${TrvlDtlsBillPassedDate}" maxlength="10" size="10" readonly="true" />
					<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtFrmDt",375,570)'>
				</td>
				<td align="left" width="55%"></td>
			</tr>


			<tr>
				<td align="left" width="4%"></td>
				<td width="60%" align="left">
				</td>
				<td align="right">
					<b><fmt:message key="TA.HEADOFFICE" bundle="${OnlineTaBillLabels}" /></b>
				</td>
			</tr>

			<tr>
				<td align="left" width="4%"></td>
				<td width="60%" align="left">
				</td>
				<td align="right">
					<b><fmt:message key="TA.CNTRLOFFICE" bundle="${OnlineTaBillLabels}" /></b>
					<br>
					<br>
				</td>
			</tr>

		</table>

	</c:when>


	<c:when test="${resValue.EditBill != null && resValue.EditBill == 'N'}">


		<c:set var="sno" value="0" />
		<c:set var="totalFare" value="0" />
		<br>
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b>Emplyee Pay Detail</b></legend>
		

		<table width="65%" align="left" class="Label">
			<hdiits:tr>
				<hdiits:td width="25%" align="left"> 
					<fmt:message key="TA.PAY" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.empPay}" name="empPay" id="empPay" disabled="true"  onkeypress="amountFormat(this)" class="texttag mandatorycontrol" />&nbsp;&nbsp;<font color="red">*</font>
				</hdiits:td>
				<hdiits:td width="18%" align="left">

				</hdiits:td>
				<hdiits:td width="35%" align="left">
					<fmt:message key="TA.PTA" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.empPta}" name="empPta" id="empPta" disabled="true" onkeypress="amountFormat(this)" />
				</hdiits:td>
			</hdiits:tr>

			<hdiits:tr>

				<hdiits:td width="25%" align="left">
					<fmt:message key="TA.CA" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.empCa}" name="empCa" id="empCa" disabled="true" onkeypress="amountFormat(this)" />
				</hdiits:td>
				<hdiits:td width="18%" align="left">

				</hdiits:td>
				<hdiits:td width="35%" align="left">
					<fmt:message key="TA.HEADQUARTER" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTablHdr.headQtr}" name="headQtr" id="headQtr" disabled="true" />
				</hdiits:td>
				
			</hdiits:tr>

		</table>
		
		</fieldset>

		<br><br>
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b><fmt:message key="TA.TRVBILLHEADING" bundle="${OnlineTaBillLabels}" /></b></legend>
		
		
		<table border="0" cellspacing="2" cellpadding="3" align="center" width="100%">
			<tr>
				<td align="center">&nbsp;</td>
			</tr>
		</table>

		<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:960px; height:150px; overflow: scroll; overflow-x:scroll; overflow-y:scroll;  ">

			<table id="ParentTable" border="0" cellspacing="1" cellpadding="5" width="1500" class="Label">
	
				<tr border="5" class="TableHeaderBG">
	
					<td width="8%"><b><fmt:message key="TA.DEPARTURESTATION" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.DEPARTUREDATE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="4%"><b><fmt:message key="TA.DEPARTUREHOUR" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="8%"><b><fmt:message key="TA.ARRIVALSTATION" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.ARRIVALDATE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="4%"><b><fmt:message key="TA.ARRIVALHOUR" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.TYPEOFJOURNEY" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.RAILWAYCLASS" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.RAILWAYFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.RAILWAYAMOUNT" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.ORDINARYFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.OTHERFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.DAILYALLOWANCE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.NOOFDAYS" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.PURPOSEOFJOURNEY" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.TOTAL" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="10%"><b><fmt:message key="TA.REMARKS" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="4%"></td>
	
				</tr>
			</table>
	
			<table id="TABill" border="0" cellspacing="0" cellpadding="1" width="100%" class="Label">
	
				<c:forEach var="trnTablTrvlDtlsLst" items="${resValueDtls.TrnTablTrvlDtlsLst}" varStatus="No">
	
					<hdiits:hidden name="hidTrnTablTrvlDtlsId" id="hidTrnTablTrvlDtlsId" default="${trnTablTrvlDtlsLst.trnTablTrvlDtlsId}" />
	
					<hdiits:tr>
	
						<hdiits:td width="3%">
							<c:set var="sno" value="${sno+1}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.depStation}"	name="depStation" size="15" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<fmt:formatDate var="TrvlDtlsDepDate" dateStyle="full" pattern="dd/MM/yyyy" value="${trnTablTrvlDtlsLst.depDate}" />
							<input type="text" value="${TrvlDtlsDepDate}" name="depDate" size="7" id="theValue" disabled="true" />
						</hdiits:td>
	
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.depHour}" name="depHour" size="4" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.arrStation}" name="arrStation" size="15" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<fmt:formatDate var="TrvlDtlsArrDate" dateStyle="full" pattern="dd/MM/yyyy" value="${trnTablTrvlDtlsLst.arrDate}" />
							<input type="text" value="${TrvlDtlsArrDate}" name="arrDate" size="7" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.arrHour}" name="arrHour" size="4" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.trvlMode}" name="trvlMode" size="7" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.fareClass}"	name="fareClass" size="7" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.fareNumber}" name="fareNumber" size="7" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="${trnTablTrvlDtlsLst.fareAmt}" name="fareAmt" size="7" id="theValue" disabled="true"	onblur="getGrossRailwayFareAmount()" />
							<c:set var="totalFare" value="${trnTablTrvlDtlsLst.fareAmt+totalFare}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.admsblOrdRate}"	name="admsblOrdRate" size="7" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.admsblOthRate}"	name="admsblOthRate" size="7" id="theValue" disabled="true"	/>
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablTrvlDtlsLst.admsblDa}" name="admsblDa" size="7" id="theValue" disabled="true" onblur="getTotalDaclimed()" />
							<c:set var="admslDa" value="${trnTablTrvlDtlsLst.admsblDa}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablTrvlDtlsLst.numOfDaysClmed}" name="numOfDaysClmed" size="7" id="theValue" disabled="true" onblur="getTotalDaclimed()"/>
							<c:set var="numOfDaysClmed"	value="${trnTablTrvlDtlsLst.numOfDaysClmed}"></c:set>
							<c:set var="totalDaClmed" value="${totalDaClmed + (admslDa * numOfDaysClmed)}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="9%">
							<input type="text" value="${trnTablTrvlDtlsLst.purpose}" name="purpose" size="10" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="${trnTablTrvlDtlsLst.totalAmt}" name="totalAmt" size="8" id="theValue" disabled="true" onblur="getGrossTrlTotal()" />
							<c:set var="daGrossttl" value="${daGrossttl + trnTablTrvlDtlsLst.totalAmt}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="5%">
							<input type="text" value="${trnTablTrvlDtlsLst.remarks}" name="remarks" size="18" id="theValue" disabled="true" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							
						</hdiits:td>
	
					</hdiits:tr>
	
				</c:forEach>
				<hdiits:hidden name="sno" id="sno" default="${sno}" />
	
			</table>

		</div>

		<table border="0" cellspacing="2" cellpadding="3" align="center" width="100%">

			<tr>				
				<table border="0" cellspacing="2" cellpadding="3" align="right" width="20%">
					<tr>
						<td align="left"><b><fmt:message key="TA.TA" bundle="${OnlineTaBillLabels}" /></b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlta"><c:out value="${totalFare}" default="0"></c:out></label></b></td>
					</tr>
					
					<tr>
						<td align="left"><b><fmt:message key="TA.DA" bundle="${OnlineTaBillLabels}" /> </b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlda"><c:out value="${daGrossttl}" default="0"></c:out></label></b></td>
					</tr>
					
					<tr>
						<td align="left"><b><fmt:message key="TA.GROSSTOTAL" bundle="${OnlineTaBillLabels}" /> </b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlgross"><c:out value="${totalFare + daGrossttl}" default="0"></c:out></label></b></td>
					</tr>
				</table>			
			</tr>

			<tr>
				<td align="center" >&nbsp;</td>
			</tr>

		</table>
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b><fmt:message key="TA.TRVAMTHEADING" bundle="${OnlineTaBillLabels}" /></b></legend>
		

		<table border="0" cellspacing="1" cellpadding="2" width="85%" align="center" class="Label">

			<hdiits:hidden name="hidTrnTablAmtDtlsId" id="hidTrnTablAmtDtlsId" default="${trnTablAmtDtls.trnTablAmtDtlsId}" />
				
			<tr >
				<td height="15"></td>				
			</tr>
			
			<tr class="datatableheader"  style="font-size: 12px;">
				<td align="center" width="50%" colspan="3" class="HLabel"><b><fmt:message key="TA.EXPENSE" bundle="${OnlineTaBillLabels}" /></b></td>				
			</tr>
				
			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.RAILNSTEAMERFARE" bundle="${OnlineTaBillLabels}" /></td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="rlwStmFare" id="rlwStmFare"	value="${totalFare}" disabled="true" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> 
					<fmt:message key="TA.ROADMILEPERPAISA_1" bundle="${OnlineTaBillLabels}" /> 
					<input type="text" name="rdTrvlChrgsPs"	value="${trnTablAmtDtls.rdChrgsPsperkm}" disabled="true" size="2">
					<fmt:message key="TA.ROADMILEPERPAISA_2" bundle="${OnlineTaBillLabels}" />
					<input name="rdTrvlChrgsKm" value="1" type="text" disabled="true"	size="2">&nbsp;km
				</td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="rdTrvlChrgs" id="rdTrvlChrgs" value="${trnTablAmtDtls.rdTrlFare}" disabled="true" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.DAYOFCLAIMED" bundle="${OnlineTaBillLabels}" /> </td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="daysClaimed" id="daysClaimed" value="${totalDaClmed}" disabled="true" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.INCLEXPENSES" bundle="${OnlineTaBillLabels}" /> </td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="incExp" id="incExp" value="${trnTablAmtDtls.incdntlExp}" disabled="true" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
					<b><fmt:message key="TA.GROSSTOTAL" bundle="${OnlineTaBillLabels}" /></b> </td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" id="grossTot" name="grossTotal" value="${trnTablAmtDtls.grossTotal}" disabled="true" />&nbsp;&nbsp;<font color="red">*</font>
				</td>
			</tr>

			<tr class="datatableheader"  style="font-size: 12px;">
				<td align="center" width="4%" colspan="3" >
					<b><fmt:message key="TA.DEDUCTIONS" bundle="${OnlineTaBillLabels}" /></b></td>
			</tr>


			<tr>
				<td align="left" width="4%" class="odd" ></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.AMTOFTAADVANCE" bundle="${OnlineTaBillLabels}" /></td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="taAdv" value="${trnTablAmtDtls.dedTaAdv}" disabled="true" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
						 <fmt:message key="TA.PTAFORDAYSGCSR_1" bundle="${OnlineTaBillLabels}" /> 
						 <input type="text" size="2" name="ptaDays" value="${trnTablAmtDtls.dedPtaDays}" disabled="true">
						 <fmt:message key="TA.PTAFORDAYSGCSR_2" bundle="${OnlineTaBillLabels}" />
				</td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="pta" value="${trnTablAmtDtls.dedPta}" disabled="true" />
				</td>
			</tr>
			
			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
					<b><fmt:message key="TA.DEDTOT" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="dedTotal" value="${trnTablAmtDtls.dedTaAdv + trnTablAmtDtls.dedPta}" onkeypress="amountFormat(this)" disabled="true" />
				</td>
			</tr>
			
			<tr>
				<td colspan="3"> &nbsp </td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="right" width="40%" class="odd">
					<b><fmt:message key="TA.NETCLAIMED" bundle="${OnlineTaBillLabels}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="netAmt"  value="${trnTablAmtDtls.netClaimed}" disabled="true" />&nbsp;&nbsp;<font color="red">*</font>
				</td>
			</tr>
			
			<tr>
				<td colspan="3"> &nbsp </td>
			</tr>

			<tr>
				<td align="left" width="4%" ></td>
				<td align="left" width="40%">
					<b> <fmt:message key="TA.PASSFORRS" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%">
					: &nbsp;&nbsp;<input type="text" name="passedAmt"	value="${trnTablAmtDtls.billPassedAmt}" disabled="true"	class="texttag mandatorycontrol" />&nbsp;&nbsp;<font color="red">*</font>
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" ></td>
				<td align="left" width="40%">
					<b> <fmt:message key="TA.PASSEDFORINWORD" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%">
					<input type="hidden" id="amt" value="521" />
					: &nbsp;&nbsp;<b><label id="amtInWord"></label></b>
					<script type="text/javascript">
						amtInWord();					
					</script>
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" ></td>
				<td align="left" width="40%">
					<fmt:message key="TA.DATE" bundle="${OnlineTaBillLabels}" /> :
					<fmt:formatDate var="TrvlDtlsBillPassedDate" dateStyle="full" pattern="dd/MM/yyyy" value="${trnTablAmtDtls.billPassedDate}" />
					<input type="calender" name="txtFrmDt" value="${TrvAmtDtlsBillPassedDate}" maxlength="10" size="10"	disabled="true" />
				</td>
				<td align="left" width="55%"></td>
			</tr>

			<tr>
				<td align="left" width="4%"></td>
				<td width="60%" align="right">
				</td>
				<td align="right"><b><fmt:message key="TA.HEADOFFICE" bundle="${OnlineTaBillLabels}" /></b></td>
			</tr>

			<tr>
				<td align="left" width="4%"></td>
				<td width="60%" align="right"></td>
				<td align="right">
					<b><fmt:message key="TA.CNTRLOFFICE" bundle="${OnlineTaBillLabels}" /></b>
					<br>
					<br>
				</td>
			</tr>

		</table>
		
		</fieldset>

	</c:when>

	<c:otherwise>

		<c:set var="sno" value="0" />
		<c:set var="totalFare" value="0" />
		<br>
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b>Employee Pay Detail</b></legend>
		
		<table width="80%" align="left" class="Label">

			<hdiits:tr>
				<hdiits:td width="25%" align="left" >
					<fmt:message key="TA.PAY" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTarqstHdr.empPay}" name="empPay" id="empPay" maxlength="10" onkeypress="amountFormat(this)" class="texttag mandatorycontrol" />&nbsp;&nbsp;<font color="red">*</font>
					<input type="hidden" name="digi6_0_EMP_PAY" />
				</hdiits:td>
				<hdiits:td width="18%" align="left">

				</hdiits:td>
				<hdiits:td width="35%" align="left">
					<fmt:message key="TA.PTA" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTarqstHdr.empPta}" name="empPta" id="empPta" maxlength="10" onkeypress="amountFormat(this)" />
					<input type="hidden" name="digi6_0_EMP_PTA" />
				</hdiits:td>
			</hdiits:tr>

			<hdiits:tr>

				<hdiits:td width="25%" align="left">
					<fmt:message key="TA.CA" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTarqstHdr.empCa}" name="empCa" id="empCa" maxlength="10" onkeypress="amountFormat(this)" />
					<input type="hidden" name="digi6_0_EMP_CA" />
				</hdiits:td>
				<hdiits:td width="18%" align="left">

				</hdiits:td>
				<hdiits:td width="35%" align="left">
					<fmt:message key="TA.HEADQUARTER" bundle="${OnlineTaBillLabels}" />
				</hdiits:td>
				<hdiits:td width="25%" align="left">
					<input type="text" value="${trnTarqstHdr.headQtrEn}" name="headQtr" id="headQtr" maxlength="20"/>
					<input type="hidden" name="digi6_0_HEAD_QTR"/>
				</hdiits:td>
				
			</hdiits:tr>

		</table>
		
		</fieldset>
		
		<br><br>
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b><fmt:message key="TA.TRVBILLHEADING" bundle="${OnlineTaBillLabels}" /></b></legend>
		
		<table border="0" cellspacing="2" cellpadding="3" align="center" width="100%">
						
			<tr>
				<td align="center">&nbsp;</td>
			</tr>
		</table>

		<table align="left">
			<hdiits:tr>
				<hdiits:td>
					<hdiits:button name="butAddRowOne" type="button" value=" Add Row "
						onclick="addRow(),getGrossRailwayFareAmount(),getTotalDaclimed(),getGrossTrlTotal()" />
				</hdiits:td>
			</hdiits:tr>
		</table>
		<br>
		<br>

		<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:970px; height:150px; overflow: scroll; overflow-x:scroll; overflow-y:scroll;  ">

			<table id="ParentTable" border="0" cellspacing="1" cellpadding="5" width="1500" class="Label">
	
				<tr border="5" class="TableHeaderBG">
	
					<td width="9%"><b><fmt:message key="TA.DEPARTURESTATION" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6.5%"><b><fmt:message key="TA.DEPARTUREDATE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="4%"><b><fmt:message key="TA.DEPARTUREHOUR" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="8%"><b><fmt:message key="TA.ARRIVALSTATION" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="7.8%"><b><fmt:message key="TA.ARRIVALDATE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="4.5%"><b><fmt:message key="TA.ARRIVALHOUR" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5.8%"><b><fmt:message key="TA.TYPEOFJOURNEY" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.RAILWAYCLASS" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.RAILWAYFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.RAILWAYAMOUNT" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.ORDINARYFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.OTHERFARES" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.DAILYALLOWANCE" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.NOOFDAYS" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="6%"><b><fmt:message key="TA.PURPOSEOFJOURNEY" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="5%"><b><fmt:message key="TA.TOTAL" bundle="${OnlineTaBillLabels}" /></b></td>
	
					<td width="15%"><b><fmt:message key="TA.REMARKS" bundle="${OnlineTaBillLabels}" /></b></td>
		
				</tr>
			</table>
	
			<table id="TABill" border="0" cellspacing="0" cellpadding="1" width="100%" class="Label">
				
				<c:forEach var="trnTablRqst" items="${resValue.TrnTablRqst}" varStatus="No">
	
					<hdiits:tr>
	
						<hdiits:td width="2%">
							<c:set var="sno" value="${sno+1}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablRqst.depStationEn}" name="depStation" size="15" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_${sno-1}_DEP_STATION" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<fmt:formatDate var="TrvlRqstDepDate" dateStyle="full" pattern="dd/MM/yyyy" value="${trnTablRqst.depDate}" />
							<input type="text" value="${TrvRqstDepDate}" name="depDate[<c:out value="${sno}"></c:out>]" id="depDate[<c:out value="${sno}"></c:out>]" size="7" id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src="images/CalendarImages/ico-calendar.gif"   width=20 onclick='window_open("depDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablRqst.depHour}" name="depHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)"/>
							<input type="hidden" name="digi4_${sno-1}_DEP_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablRqst.arrStationEn}"	name="arrStation" size="15" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_${sno-1}_ARR_STATION" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<fmt:formatDate var="TrvlRqstArrDate" dateStyle="full" pattern="dd/MM/yyyy" value="${trnTablRqst.arrDate}" />
							<input type="text" value="${TrvlRqstArrDate}" name="arrDate[<c:out value="${sno}"></c:out>]" id="arrDate[<c:out value="${sno}"></c:out>]" size="7" id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src="images/CalendarImages/ico-calendar.gif"   width=20 onclick='window_open("arrDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablRqst.arrHour}" name="arrHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)"/>
							 <input type="hidden" name="digi4_${sno-1}_ARR_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablRqst.trvlModeEn}" name="trvlMode" size="7" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_${sno-1}_TRVL_MODE" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablRqst.fareClass}" name="fareClass" size="7" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_${sno-1}_FARE_CLASS" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablRqst.fareNumber}" name="fareNumber" size="7" id="theValue" maxlength="10" onkeypress="digitFormat()" />
							<input type="hidden" name="digi4_${sno-1}_FARE_NUMBER" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="${trnTablRqst.fareAmt}" name="fareAmt" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossRailwayFareAmount()" />
							<c:set var="totalFare" value="${trnTablRqst.fareAmt+totalFare}"></c:set>
							<input type="hidden" name="digi4_${sno-1}_FARE_AMT" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablRqst.admsblOrdRate}" name="admsblOrdRate" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" />
							<input type="hidden" name="digi4_${sno-1}_ADMSBL_ORD_RATE" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablRqst.admsblOthRate}" name="admsblOthRate" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" />
							<input type="hidden" name="digi4_${sno-1}_ADMSBL_OTH_RATE" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="${trnTablRqst.admsblDa}"	name="admsblDa" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getTotalDaclimed()" />
							<c:set var="admslDa" value="${trnTablRqst.admsblDa}"></c:set>
							<input type="hidden" name="digi4_${sno-1}_ADMSBL_DA" /> 
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="${trnTablRqst.numOfDaysClmed}" name="numOfDaysClmed" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getTotalDaclimed()" />
							<c:set var="numOfDaysClmed" value="${trnTablRqst.numOfDaysClmed}"></c:set>
							<c:set var="totalDaClmed" value="${totalDaClmed + (admslDa * numOfDaysClmed)}"></c:set>
							<input type="hidden" name="digi4_${sno-1}_NUM_OF_DAYS_CLMED" />
						</hdiits:td>
	
						<hdiits:td width="9%">
							<input type="text" value="${trnTablRqst.purpose}" name="purpose" size="10" id="theValue" maxlength="20" />
							<input type="hidden" name="digi4_${sno-1}_PURPOSE" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="${trnTablRqst.totalAmt}"	name="totalAmt" size="8" id="theValue" maxlength="10"	onkeypress="amountFormat(this)" onblur="getGrossTrlTotal()" />
							<c:set var="daGrossttl" value="${daGrossttl + trnTablRqst.totalAmt}"></c:set>
							 <input type="hidden" name="digi4_${sno-1}_TOTAL_AMT" />
						</hdiits:td>
	
						<hdiits:td width="5%">
							<input type="text" value="${trnTablRqst.remarks}" name="remarks" size="12" id="theValue" maxlength="30"/>
							<input type="hidden" name="digi4_${sno-1}_REMARKS" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<c:if test='${sno > 1}'>
								<img src="images/CalendarImages/DeleteIcon.gif"	onclick="delRow(this),getGrossRailwayFareAmount(),getTotalDaclimed(),getGrossTrlTotal()" />
							</c:if>
						</hdiits:td>
	
					</hdiits:tr>
	
				</c:forEach>
				<hdiits:hidden name="sno" id="sno" default="${sno}" />
	
				<c:if test="${sno == 0}">
					<hdiits:tr>
	
						<hdiits:td width="2%">
							<c:set var="sno" value="${sno+1}"></c:set>
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="depStation" size="15"	id="theValue" maxlength="20"/>
						<input type="hidden" name="digi6_0_BILL_PASSED_AMT" /></hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="depDate[<c:out value="${sno}"></c:out>]" id="depDate[<c:out value="${sno}"></c:out>]" size="7"	id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src="images/CalendarImages/ico-calendar.gif"   width=20 onclick='window_open("depDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
	
						<hdiits:td width="4%">
							<input type="text" value="" name="depHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)"/>
							<input type="hidden" name="digi4_0_DEP_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="arrStation" size="15"	id="theValue" maxlength="20" />
							<input type="hidden" name="digi4_0_ARR_STATION" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="arrDate[<c:out value="${sno}"></c:out>]" id="arrDate[<c:out value="${sno}"></c:out>]" size="7" id="theValue" maxlength="10" onblur="checkIsDate(this),checkDepArrDate(<c:out value="${sno}"></c:out>)"  /><img src="images/CalendarImages/ico-calendar.gif"   width=20 onclick='window_open("arrDate[<c:out value="${sno}"></c:out>]",375,300)' >
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="arrHour" size="4" id="theValue" maxlength="5" onkeypress="digitFormat()" onblur="HourFormat(this)"/>
							<input type="hidden" name="digi4_0_ARR_HOUR" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="trvlMode" size="7" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_0_TRVL_MODE" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="fareClass" size="7" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_0_FARE_CLASS" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="fareNumber" size="7" id="theValue" maxlength="10" onkeypress="digitFormat()" />
							<input type="hidden" name="digi4_0_FARE_NUMBER" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="" name="fareAmt" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossRailwayFareAmount()" />
							<input type="hidden" name="digi4_0_FARE_AMT" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="admsblOrdRate" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" />
							<input type="hidden" name="digi4_0_ADMSBL_ORD_RATE" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="admsblOthRate" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" />
							<input type="hidden" name="digi4_0_ADMSBL_OTH_RATE" />
						</hdiits:td>
	
						<hdiits:td width="6%">
							<input type="text" value="" name="admsblDa" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getTotalDaclimed()" />
							<input type="hidden" name="digi4_0_ADMSBL_DA" />
						</hdiits:td>
	
						<hdiits:td width="4%">
							<input type="text" value="" name="numOfDaysClmed" size="7" id="theValue" maxlength="10" onkeypress="amountFormat(this)" onblur="getTotalDaclimed()" />
							<input type="hidden" name="digi4_0_NUM_OF_DAYS_CLMED" />
						</hdiits:td>
	
						<hdiits:td width="9%">
							<input type="text" value="" name="purpose" size="10" id="theValue" maxlength="20"/>
							<input type="hidden" name="digi4_0_PURPOSE" />
						</hdiits:td>
	
						<hdiits:td width="8%">
							<input type="text" value="" name="totalAmt" size="8" id="theValue" maxlength="10" onkeypress="amountFormat(this)"	onblur="getGrossTrlTotal()" />
							<input type="hidden" name="digi4_0_TOTAL_AMT" />
						</hdiits:td>
	
						<hdiits:td width="5%">
							<input type="text" value="" name="remarks" size="12" id="theValue" maxlength="30"/>
							<input type="hidden" name="digi4_0_REMARKS" /> 
						</hdiits:td>
	
						<hdiits:td width="4%">
	
						</hdiits:td>
	
					</hdiits:tr>
	
				</c:if>
	
			</table>

		</div>

		<table border="0" cellspacing="2" cellpadding="3" align="center" width="100%">

			<tr>				
				<table border="0" cellspacing="2" cellpadding="3" align="right" width="20%">
					<tr>
						<td align="left"><b><fmt:message key="TA.TA" bundle="${OnlineTaBillLabels}" /></b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlta"><c:out value="${totalFare}" default="0"></c:out></label></b></td>
					</tr>
					
					<tr>
						<td align="left"><b><fmt:message key="TA.DA" bundle="${OnlineTaBillLabels}" /> </b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlda"><c:out value="${daGrossttl}" default="0"></c:out></label></b></td>
					</tr>
					
					<tr>
						<td align="left"><b><fmt:message key="TA.GROSSTOTAL" bundle="${OnlineTaBillLabels}" /> </b></td>
						<td align="left"><b> : &nbsp;&nbsp;</b>
						<td align="right" width="40%"><b><label id="lttlgross"><c:out value="${totalFare + daGrossttl}" default="0"></c:out></label></b></td>
					</tr>
				</table>			
			</tr>

			<tr>
				<td align="center" >&nbsp;</td>
			</tr>

		</table>	
		
		
		<fieldset  style = "width:100%" class="tabstyle">
		<legend  id="headingMsg"><b><fmt:message key="TA.TRVAMTHEADING" bundle="${OnlineTaBillLabels}" /></b></legend>

		<table border="0" cellspacing="1" cellpadding="2" width="85%" align="center" class="Label">
		
			<tr>
				<td height="15"></td>				
			</tr>

			<tr class="datatableheader"  style="font-size: 12px;">
				<td align="center" width="50%" colspan="3"><b><fmt:message key="TA.EXPENSE" bundle="${OnlineTaBillLabels}" /></b></td>				
			</tr>
			
			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.RAILNSTEAMERFARE" bundle="${OnlineTaBillLabels}" /></td>
				<td align="left" width="40%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="rlwStmFare" id="rlwStmFare"	value="${totalFare}" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossAmtTotal()" />
					<input type="hidden" name="digi5_0_RLW_STM_FARE" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
					<fmt:message key="TA.ROADMILEPERPAISA_1" bundle="${OnlineTaBillLabels}" />
					<input type="text" name="rdTrvlChrgsPs"	size="2" maxlength="10">
					<fmt:message key="TA.ROADMILEPERPAISA_2" bundle="${OnlineTaBillLabels}" />
					<input name="rdTrvlChrgsKm"	type="text" size="2" maxlength="10" onkeypress="amountFormat(this)">&nbsp;km
				</td>
				<td align="left" width="40%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="rdTrvlChrgs" id="rdTrvlChrgs" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossAmtTotal()" />
					<input type="hidden" name="digi5_0_RD_TRL_FARE" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.DAYOFCLAIMED" bundle="${OnlineTaBillLabels}" /> </td>
				<td align="left" width="40%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="daysClaimed" id="daysClaimed" value="${totalDaClmed}" maxlength="10" onkeypress="digitFormat()" onblur="getGrossAmtTotal()" />
					 <input type="hidden" name="digi5_0_TTL_DA_CLMED" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.INCLEXPENSES" bundle="${OnlineTaBillLabels}" /> </td>
				<td align="left" width="40%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="incExp" id="incExp" value="${trnTarqstExpsumm.incdntlExp}" maxlength="10" onkeypress="amountFormat(this)" onblur="getGrossAmtTotal()" />
					<input type="hidden" name="digi5_0_INCDNTL_EXP" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd" >
					<b><fmt:message key="TA.GROSSTOTAL" bundle="${OnlineTaBillLabels}" /></b>
				</td>
				<td align="left" width="40%" class="odd" >
					: &nbsp;&nbsp;<input type="text" id="grossTot" name="grossTotal" value="${trnTarqstExpsumm.grossTtl}" maxlength="10" onkeypress="amountFormat(this)" onblur="getNetClaimedAmt()" />&nbsp;&nbsp;<font color="red">*</font>
					<input type="hidden" name="digi5_0_GROSS_TOTAL" />
				</td>
			</tr>

			<tr class="datatableheader"  style="font-size: 12px;"> 
				<td align="center" width="4%" colspan="3">
					<b><fmt:message key="TA.DEDUCTIONS" bundle="${OnlineTaBillLabels}" /></b>
				</td>
				
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd"> <fmt:message key="TA.AMTOFTAADVANCE" bundle="${OnlineTaBillLabels}" />:
				</td>
				<td align="left" width="40%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="taAdv"	value="${trnTarqstExpsumm.dedTaAdv}" maxlength="10" onkeypress="amountFormat(this)" onblur="setDedTotal(),getNetClaimedAmt()" />
					<input type="hidden" name="digi5_0_DED_TA_ADV" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
					<fmt:message key="TA.PTAFORDAYSGCSR_1" bundle="${OnlineTaBillLabels}" /> 
					<input type="text" size="2"	name="ptaDays" value="${trnTarqstExpsumm.dedPtaDays}" maxlength="10" onkeypress="digitFormat()"> 
					<input type="hidden" name="digi5_0_DED_PTA_DAYS" />
					<fmt:message key="TA.PTAFORDAYSGCSR_2" bundle="${OnlineTaBillLabels}" />
  				</td>
				<td align="left" width="40%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="pta" value="${trnTarqstExpsumm.dedPta}" maxlength="10" onkeypress="amountFormat(this)" onblur="setDedTotal(),getNetClaimedAmt()" />
					<input type="hidden" name="digi5_0_DED_PTA" />
				</td>
			</tr>
			
			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="left" width="40%" class="odd">
					<b><fmt:message key="TA.DEDTOT" bundle="${OnlineTaBillLabels}" /></b></td>
				<td align="left" width="55%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="dedTotal" value="${trnTarqstExpsumm.dedTaAdv + trnTarqstExpsumm.dedPta}" maxlength="10" onkeypress="amountFormat(this)" onblur="getNetClaimedAmt()" readonly="true"/>
					
				</td>
			</tr>
			
			<tr>
				<td colspan="3"> &nbsp </td>
			</tr>

			<tr>
				<td align="left" width="4%" class="odd"></td>
				<td align="right" width="40%" class="odd">
					<b><fmt:message key="TA.NETCLAIMED" bundle="${OnlineTaBillLabels}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td align="left" width="40%" class="odd">
					: &nbsp;&nbsp;<input type="text" name="netAmt" id="netAmt" value="${trnTarqstExpsumm.netClaimed}" maxlength="10" onkeypress="amountFormat(this)" onblur="setPassedForRs()" />&nbsp;&nbsp;<font color="red">*</font>
					<input type="hidden" name="digi5_0_NET_CLAIMED" />
					
				</td>
			</tr>
			
			<tr>
				<td colspan="3"> &nbsp </td>
			</tr>

			<tr>
				<td align="left" width="4%" ></td>
				<td align="left" width="40%">
					<b> <fmt:message key="TA.PASSFORRS" bundle="${OnlineTaBillLabels}" /></b>
				</td>
				<td align="left" width="40%">
					: &nbsp;&nbsp;<input id="passedAmt" type="text" name="passedAmt" value="" maxlength="10"	onkeyup="amtInWord()" onkeypress="amountFormat(this)" class="texttag mandatorycontrol" />&nbsp;&nbsp;<font color="red">*</font>
					<input type="hidden" name="digi5_0_BILL_PASSED_AMT" />
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" ></td>
				<td align="left" width="40%">
					<b> <fmt:message key="TA.PASSEDFORINWORD" bundle="${OnlineTaBillLabels}" /></b>
				</td>
				<td align="left" width="40%">
					: &nbsp;&nbsp;<b><label id="amtInWord"></label></b>
				</td>
			</tr>

			<tr>
				<td align="left" width="4%" ></td>
				<td align="left" width="40%">
					<fmt:message key="TA.DATE" bundle="${OnlineTaBillLabels}" /> : 	
			    	<input type="text" name="txtFrmDt" value="" maxlength="10" size="10" readonly="true" />
					<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtFrmDt",375,570)'>
				</td>
				<td align="left" width="40%">
					
				</td>
			</tr>

			<tr>
				<td align="left" width="4%"></td>
				<td width="60%" align="right">
				</td>
				<td align="right">
					<b><fmt:message key="TA.HEADOFFICE" bundle="${OnlineTaBillLabels}" /></b>
				</td>
			</tr>

			<tr>
				<td align="left" width="4%"></td>
				<td width="60%" align="right">
				</td>
				<td align="right">
					<b><fmt:message key="TA.CNTRLOFFICE" bundle="${OnlineTaBillLabels}" /></b>
					<br>
					<br>
				</td>
			</tr>

		</table>
		
		</fieldset>

	</c:otherwise>
</c:choose>


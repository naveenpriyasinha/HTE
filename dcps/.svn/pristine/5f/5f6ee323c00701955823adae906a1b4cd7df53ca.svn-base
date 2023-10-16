<%try{ %>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>

<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lObjTrnPnsnprocPnsnrpayVO" value="${resValue.lObjTrnPnsnprocPnsnrpayVO}"></c:set>
<c:set var="lObjTrnPnsnprocPnsnrservcbreakVO" value="${resValue.lObjTrnPnsnprocPnsnrservcbreakVO}"></c:set>
<c:set var="lObjTrnPnsnprocEventdtlsVO" value="${resValue.lObjTrnPnsnprocEventdtlsVO}"></c:set>
<c:set var="lLstTrnPnsnprocEventdtls" value="${resValue.lLstTrnPnsnprocEventdtls}"></c:set>
<c:set var="lLstTrnPnsnprocPnsnrservcbreak" value="${resValue.lLstTrnPnsnprocPnsnrservcbreak}"></c:set>
<c:set var="lObjTrnPnsnProcInwardPensionVO" value="${resValue.lObjTrnPnsnProcInwardPensionVO}"></c:set>  
<c:set var="lObjTrnPnsnProcPnsnrDtlsVO" value="${resValue.lObjTrnPnsnProcPnsnrDtlsVO}"></c:set>
<c:set var="lLstTrnPnsnProcQlyServ" value="${resValue.lLstTrnPnsnProcQlyServ}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>



<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCasePayServiceDtls.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript">
LISTEVENTS='';
LIST5THPAYSCALE='';
LIST6THPAYSCALE='';
SRVCBRKTYPE='';
MONTH='';
YEAR='';
LISTSANAUTHO='';
LISTPADMAPAYSCALE='';
LISTSHETTYPAYSCALE='';
LASTPAYSCALE ='<fmt:message key="PPROC.LASTPAYSCALE" bundle="${pensionAlerts}"></fmt:message>';
DP ='<fmt:message key="PPROC.DP" bundle="${pensionAlerts}"></fmt:message>';
GRADEPAY ='<fmt:message key="PPROC.GRADEPAY" bundle="${pensionAlerts}"></fmt:message>';
BASICPAY ='<fmt:message key="PPROC.BASICPAY" bundle="${pensionAlerts}"></fmt:message>';
EVENT ='<fmt:message key="PPROC.EVENT" bundle="${pensionAlerts}"></fmt:message>';
PAYSCALE ='<fmt:message key="PPROC.PAYSCALE" bundle="${pensionAlerts}"></fmt:message>';
BASIC ='<fmt:message key="PPROC.BASIC" bundle="${pensionAlerts}"></fmt:message>';
FROM ='<fmt:message key="PPROC.FROM" bundle="${pensionAlerts}"></fmt:message>';
TO ='<fmt:message key="PPROC.TO" bundle="${pensionAlerts}"></fmt:message>';
TYPEOFBREAK ='<fmt:message key="PPROC.TYPEOFBREAK" bundle="${pensionAlerts}"></fmt:message>';
BREAKREASON ='<fmt:message key="PPROC.BREAKREASON" bundle="${pensionAlerts}"></fmt:message>';
LISTBILLTYPE='<option value="P"><fmt:message key="PPROC.PENSION" bundle="${pensionLabels}"/></option>'
	+'<option value="G"><fmt:message key="PPROC.GRATUITY" bundle="${pensionLabels}"/></option>';
LISTCHRGVOTD='<option value="C"><fmt:message key="PPROC.CHARGED" bundle="${pensionConstants}"/></option>'
		+'<option value="V"><fmt:message key="PPROC.VOTED" bundle="${pensionConstants}"/></option>';
LISTSANAUTHO='<option value="O">${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}</option>'						   						
		+'<option value="M" ><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>'
			+'<option value="N"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>';		
</script>

<c:forEach var="EventList" items="${resValue.lLstEvents}" >
	<script> LISTEVENTS += '<option value="${EventList.lookupId}"> ${EventList.lookupName}</option>';</script>
</c:forEach>
<c:forEach var="PayScale" items="${resValue.PayScaleList}" >
	<script> LIST5THPAYSCALE += '<option value="${PayScale.id}"> ${PayScale.desc}</option>';</script>
</c:forEach>
<c:forEach var="PayScale6th" items="${resValue.PayScaleList6th}" >
	<script> LIST6THPAYSCALE += '<option value="${PayScale6th.id}"> ${PayScale6th.desc}</option>';</script>
</c:forEach>

<c:forEach var="PayScaleListPadma" items="${resValue.PayScaleListPadma}" >
	<script> LISTPADMAPAYSCALE += '<option value="${PayScaleListPadma.id}"> ${PayScaleListPadma.desc}</option>';</script>
</c:forEach>
<c:forEach var="lLstShettyPayScale" items="${resValue.lLstShettyPayScale}" >
	<script> LISTSHETTYPAYSCALE += '<option value="${lLstShettyPayScale.id}"> ${lLstShettyPayScale.desc}</option>';</script>
</c:forEach>

<c:forEach var="SrvcBrkType" items="${resValue.lLstSrvcBrkType}" >
	<script> SRVCBRKTYPE += '<option value="${SrvcBrkType.lookupId}" title="${SrvcBrkType.lookupName}"> ${SrvcBrkType.lookupName}</option>';</script>
</c:forEach>
<c:forEach var="Years" items="${resValue.lLstYears}" >
	<script> YEAR += '<option value="${Years.desc}" > ${Years.desc}</option>';</script>
</c:forEach>
<c:forEach var="Months" items="${resValue.lLstMonths}" >
	<script> MONTH += '<option value="${Months.id}" > ${Months.desc}</option>';</script>
</c:forEach>

<input type="hidden" name="eventId" id="eventId" value=""/>
<input type="hidden" name="TypeofBrk" id="TypeofBrk" value=""/>
<input type="hidden" name="hidTotalBrkyear" id="hidTotalBrkyear" value="" />
<input type="hidden" name="hidTotalBrkMonth" id="hidTotalBrkMonth" value="" />
<input type="hidden" name="hidTotalBrkDays" id="hidTotalBrkDays" value="" />


<input type="hidden" name="hidTotalServyear" id="hidTotalServyear" value="" />
<input type="hidden" name="hidTotalServMonth" id="hidTotalServMonth" value="" />
<input type="hidden" name="hidTotalServDays" id="hidTotalServDays" value="" />



<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PAYDTLS"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	
<table width="100%" align="center">

	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.LASTPAYSCALE"
			bundle="${pensionLabels}"></fmt:message>
			 </td>
		<td width="30%" align="left">			
			<select name="cmbPayScale" id="cmbPayScale" style="width:200px" onchange="validateBasicPay(txtBasicPay);popUpGradePayAndDP();">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="PayScaleList" items="${resValue.PayScaleList}">				
					<c:choose>
						<c:when test="${lObjTrnPnsnprocPnsnrpayVO.lastPayScale == PayScaleList.id}">
							<option value="${PayScaleList.id}" selected="selected"><c:out
								value="${PayScaleList.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${PayScaleList.id}">
								<c:out 	value="${PayScaleList.desc}"/>
							</option>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label></td>
			
			
			<td width="20%" align="left"><fmt:message key="PPROC.GRADEPAY"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text"
			name='txtGradePay' id="txtGradePay"  onKeyPress="amountFormat(this,event)" maxlength="50" value="${lObjTrnPnsnprocPnsnrpayVO.gradePay}" style="text-align: right"
			/></td>
	</tr>
	<tr>
		
		<td width="20%" align="left">Non Practicing Allowance (NPA)</td>
		<td width="30%" align="left"><input type="text" name='txtNPAAmt1' id="txtNPAAmt1"  maxlength="50"  onKeyPress="amountFormat(this,event)" style="text-align: right" value="${lObjTrnPnsnprocPnsnrpayVO.npaAmount}"/></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.BASICPAY"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text"
			name='txtBasicPay' id="txtBasicPay"   style="text-align: right" maxlength="7" 
			onKeyPress="amountFormat(this,event)" onfocus="onFocus(this)"
			onblur="validateBasicPay(this);popUpGradePayAndDP();onBlur(this);"  value="${lObjTrnPnsnprocPnsnrpayVO.basicPay}" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label></td>
		<td width="20%" align="left"><fmt:message key="PPROC.DP1"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" name='txtDP' onKeyPress="amountFormat(this,event)"
			id="txtDP" style="text-align: right"  maxlength="7" value = "${lObjTrnPnsnprocPnsnrpayVO.dpRate}" /></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message
			key="PPROC.PRVSNLGRTYPAID" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
		<c:choose>
		<c:when test="${lObjTrnPnsnprocPnsnrpayVO.prvsnlGratuityFlag == 89}">
		<input type="radio"
			id="radioPrvsnlGrtyPaid" name="radioPrvsnlGrtyPaid" value="Y" maxlength="3"
			onclick="enableGratuityPaid()" checked="checked" /> <fmt:message key="PPROC.YES" bundle="${pensionLabels}" ></fmt:message>

		<input type="radio" id="radioPrvsnlGrtyPaid" maxlength="3"
			name="radioPrvsnlGrtyPaid" value="N" onclick="enableGratuityPaid()" /> <fmt:message
			key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			<c:set var="varReadonlyGrtyPaid" value=""></c:set>
		</c:when>
		<c:otherwise>
		<input type="radio" maxlength="3"
			id="radioPrvsnlGrtyPaid" name="radioPrvsnlGrtyPaid" value="Y"
			onclick="enableGratuityPaid()" /> <fmt:message key="PPROC.YES" bundle="${pensionLabels}" ></fmt:message>

		<input type="radio" id="radioPrvsnlGrtyPaid" maxlength="3"
			name="radioPrvsnlGrtyPaid" value="N" onclick="enableGratuityPaid()" checked="checked"/> <fmt:message
			key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			<c:set var="varReadonlyGrtyPaid" value="readonly"></c:set>
		</c:otherwise>
		</c:choose>
		</td>
		<td width="20%" align="left"><fmt:message key="PPROC.AMOUNT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" ${varReadonlyGrtyPaid}
			name='txtPnsnGrtyAmount' id="txtPnsnGrtyAmount" maxlength="7"
			style="text-align: right" onKeyPress="amountFormat(this,event)"
			onfocus="onFocus(this)" onblur="onBlur(this);"  value="${lObjTrnPnsnprocPnsnrpayVO.pnsnCntrbtnAmount}" />
		</td>
	</tr>
	<tr>
	<td width="20%" align="left">Order No.</td>
		<td width="30%" align="left"><input type="text" ${varReadonlyGrtyPaid}
			name='txtOrderNo' id="txtOrderNo" 
			style="text-align: right" 
			onfocus="onFocus(this)" onblur="onBlur(this);"  value="" />
		</td>
		<td width="20%" align="left">Order Date</td>
		<td width="30%" align="left"><input type="text" ${varReadonlyGrtyPaid}
			name='txtOrderDate' id="txtOrderDate" 
			style="text-align: right" onkeypress="digitFormat(this);dateFormat(this);"
			onfocus="onFocus(this)" onblur="onBlur(this);"  value="" />
		
		</td>
		</tr>
		<tr>
	<td width="20%" align="left"><fmt:message key="PPROC.VOUCHERNO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" ${varReadonlyGrtyPaid}
			name='txtvoucherNo' id="txtvoucherNo" 
			style="text-align: right" 
			onfocus="onFocus(this)" onblur="onBlur(this);"  value="" />
		</td>
		<td width="20%" align="left"><fmt:message key="PPROC.VOUCHERDATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" ${varReadonlyGrtyPaid}
			name='txtvoucherDate' id="txtvoucherDate" onkeypress="digitFormat(this);dateFormat(this);"
			style="text-align: right" 
			onfocus="onFocus(this)" onblur="onBlur(this);"  value="" />
			
		</td>
		</tr>
	
	</table>
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend><b> <fmt:message key="PPROC.FOREIGNSERVDTL" bundle="${pensionLabels}"></fmt:message></b></legend>
<input type="hidden" name="hidForeignServicesGridSize" id="hidForeignServicesGridSize" value="1" />
<table width="50%">
	<tr>
		<td width="10%" align="left"><fmt:message key="PPROC.SERVRENDERFOREIGNSERV" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="10%" align="left">
		<c:choose>
		<c:when test="${lObjTrnPnsnprocPnsnrpayVO.srvcRenderdFlag == 78}">
		<input type="radio"
			id="radioServRenderForeignYes" name="radioServRenderForeign" value="Y" maxlength="3"
			onclick="enableForeignServices()"  /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message>
		<input type="radio" id="radioServRenderForeignNo" maxlength="3"
			name="radioServRenderForeign" value="N" onclick="enableForeignServices()" /> <fmt:message
			key="PPROC.NO" bundle="${pensionLabels}">
			</fmt:message>
			<c:set var="varReadonlyForeign" value=""></c:set>
			<c:set var="varDisableForeign" value=""></c:set>
			<c:set var="varReadOnlyForeign" value="false"></c:set>
		</c:when>
		<c:otherwise>
		<input type="radio"
			id="radioServRenderForeignYes" name="radioServRenderForeign" value="Y" maxlength="3"
			onclick="enableForeignServices()" /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message>

		<input type="radio" id="radioServRenderForeignNo" maxlength="3"
			name="radioServRenderForeign" value="N" onclick="enableForeignServices()"  /> <fmt:message
			key="PPROC.NO" bundle="${pensionLabels}">
			</fmt:message>
			<c:set var="varReadonlyForeign" value="readonly"></c:set>
			<c:set var="varDisableForeign" value="display: none;"></c:set>
			<c:set var="varReadOnlyForeign" value="true"></c:set>
		</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td>
		</td>
		<td>
			<select name="cmbRecoveryType" id="cmbRecoveryType" style="width:200px" onchange="">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:choose>
				<c:when test="${lObjTrnPnsnprocPnsnrpayVO.foreignPayInfo == 'PC'}">
					<option value="PC" selected="selected"><fmt:message	key="PPROC.PENCON" bundle="${pensionLabels}"/></option>
					<option value="LS"><fmt:message	key="PPROC.LEAVESALARY" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:otherwise>
				<c:choose>
					<c:when test="${lObjTrnPnsnprocPnsnrpayVO.foreignPayInfo == 'LS'}">
						<option value="PC"><fmt:message	key="PPROC.PENCON" bundle="${pensionLabels}"/></option>
						<option value="LS" selected="selected"><fmt:message	key="PPROC.LEAVESALARY" bundle="${pensionLabels}"/></option>
					</c:when>
					<c:otherwise>
						<option value="PC"><fmt:message	key="PPROC.PENCON" bundle="${pensionLabels}"/></option>
						<option value="LS"><fmt:message	key="PPROC.LEAVESALARY" bundle="${pensionLabels}"/></option>
					</c:otherwise>
				</c:choose>
				</c:otherwise>
				</c:choose>				
			</select>
		</td>
	</tr>
</table>

<hdiits:button	name="ForeignServAddRow" id="ForeignServAddRow" readonly="${varReadOnlyForeign}" type="button" captionid="PPROC.ADDROW"	bundle="${pensionLabels}" onclick="foreignServTableAddRow();" />
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">	
<table width="95%" id="tblForeignServDtls" border="1" >	
	<tr class="datatableheader">
		<td width="5%" class="HLabel" align="center"><fmt:message key="PPROC.SRNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="12%" class="HLabel" align="center"><fmt:message key="PPROC.DURATION" bundle="${pensionLabels}"></fmt:message> 
							<fmt:message key="PPROC.FROM"	bundle="${pensionLabels}"></fmt:message> </td>
		<td width="13%" class="HLabel" align="center"><fmt:message key="PPROC.DURATION" bundle="${pensionLabels}"></fmt:message> 
							<fmt:message key="PPROC.TO"	bundle="${pensionLabels}"></fmt:message> </td>
		<td width="10%" class="HLabel" align="center"><fmt:message key="PPROC.TOTALAMT"	bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="HLabel" align="center"><fmt:message key="PPROC.PAIDAMT"	bundle="${pensionLabels}"></fmt:message></td>
		<td width="13%" class="HLabel" align="center"><fmt:message key="PPROC.CHALLANNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="12%" class="HLabel" align="center"><fmt:message key="PPROC.CHALLANDATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="23%" class="HLabel" align="center"><fmt:message key="PPROC.DEPOFFNAME"	bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="HLabel" align="center"><fmt:message	key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>								
	</tr>	
	<c:choose>
		<c:when test="${resValue.lLstTrnPnsnprocForeignServ !=null}">
			<c:forEach var="ForeignServ" items="${resValue.lLstTrnPnsnprocForeignServ}" varStatus="Counter">
			<tr>
					<td class="tds" align="center">
						${Counter.index + 1}						
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtFromDateForeignServ" id="txtFromDateForeignServ${Counter.index}" size='10' ${varReadonlyForeign}
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
					onblur="onBlur(this);chkValidDate(this);" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${ForeignServ.fromDate}"/>"  /> <img
					src='images/CalendarImages/ico-calendar.gif' width='20' id="imgFromDatePnsnGrty"
					onClick='window_open(event,"txtFromDatePnsnGrty${Counter.index}",375,570)'
					style="cursor: pointer;${varDisableForeign}" />
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtToDateForeignServ" id="txtToDateForeignServ${Counter.index}" size='10' ${varReadonlyForeign}
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"	onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
					onblur="onBlur(this);chkValidDate(this);" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${ForeignServ.toDate}"/>"  /> <img
					src='images/CalendarImages/ico-calendar.gif' width='20' id="imgToDatePnsnGrty"
					onClick='window_open(event,"txtToDatePnsnGrty${Counter.index}",375,570)'
					style="cursor: pointer;${varDisableForeign}" />
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtTotalAmt" id="txtTotalAmt${Counter.index}" onfocus="onFocus(this)" value="${ForeignServ.totalAmt}" ${varReadonlyForeign}
					size="10" maxlength="10" onkeypress="digitFormat(this);" onblur="onBlur(this);" />
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtPaidAmt" id="txtPaidAmt${Counter.index}" onfocus="onFocus(this)" size="10" value="${ForeignServ.paidAmt}" ${varReadonlyForeign}
					 maxlength="10" onkeypress="digitFormat(this);" onblur="onBlur(this);" />
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtChallanNo" id="txtChallanNo${Counter.index}"  ${varReadonlyForeign} value="${ForeignServ.challanNo}"
					onfocus="onFocus(this)" size="13" maxlength="20" onblur="onBlur(this);" />
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtChallanDate" id="txtChallanDate${Counter.index}" size="10" maxlength="10" ${varReadonlyForeign}
					onkeypress="digitFormat(this);dateFormat(this);" onKeyPress="numberFormat(event);" onfocus="onFocus(this)"
					value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${ForeignServ.challanDate}"/>" onblur="onBlur(this);chkValidDate(this);"/>
					<img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgFromDatePnsnGrty"
					onClick='window_open(event,"txtChallanDate${Counter.index}",375,570)' style="cursor: pointer;${varDisableForeign}" />
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtDeptOffName" id="txtDeptOffName${Counter.index}" onfocus="onFocus(this)" ${varReadonlyForeign}
				 	value="${ForeignServ.deptOffName}" size="30" maxlength="50"  onblur="onBlur(this);" />
					</td>
					<td class="tds" align="center">
					<img name="Image"  id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblForeignServDtls')" /> 
					</td>
				</tr>
				<script>
						document.getElementById("hidForeignServicesGridSize").value=Number('${Counter.index}') + 1;
				</script>			
			</c:forEach>
		</c:when>
	</c:choose>
	</table>
</div>		
</fieldset>

<fieldset style="width: 100%" class="tabstyle"><legend><b> <fmt:message key="PRVSNLPAYDTLS" bundle="${pensionLabels}"></fmt:message></b></legend>
<input type="hidden" name="hidPrvsnlPnsnGridSize" id="hidPrvsnlPnsnGridSize" value="1" />
<table>
	<tr>
		<td width="20%" align="left"><fmt:message
			key="PPROC.PRVSNLPNSNPAID" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">		
		<c:choose>
		<c:when test="${lObjTrnPnsnprocPnsnrpayVO.prvsnlPnsnpaidFlag == 89}">
			<input type="radio"	id="radioPrvsnlPnsnPaidYes" name="radioPrvsnlPnsnPaid" value="Y"	onclick="enableProvisionalPensionDetails()" checked="checked"/>
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}" ></fmt:message>
			<input type="radio" id="radioPrvsnlPnsnPaidNo" name="radioPrvsnlPnsnPaid" value="N" onclick="enableProvisionalPensionDetails()" />
		 	<fmt:message	key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		 	<c:set var="varReadOnlyPro" value=""></c:set>		 
		 	<c:set var="varDisplayPro" value=""></c:set>	
		 	<c:set var="varReadOnlyProButton" value="false"></c:set>	 	
		</c:when>
		<c:otherwise>
			<input type="radio"  id="radioPrvsnlPnsnPaidYes" name="radioPrvsnlPnsnPaid" value="Y" onclick="enableProvisionalPensionDetails()" />
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioPrvsnlPnsnPaidNo" name="radioPrvsnlPnsnPaid" value="N" onclick="enableProvisionalPensionDetails()" checked="checked" />
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			<c:set var="varReadOnlyPro" value="readonly"></c:set>			
			<c:set var="varDisplayPro" value="display: none;"></c:set>
			<c:set var="varReadOnlyProButton" value="true"></c:set>			
		</c:otherwise>
		</c:choose>
		</td>
		<td width="20%" align="left"></td>
		<td width="30%" align="left"></td>
	</tr>
</table>
<table style="display: none">
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.FROM"	bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">		
			<input type="text"	name="txtFromDatePrvsnlPnsn" ${varReadOnlyPro} id="txtFromDatePrvsnlPnsn" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnprocPnsnrpayVO.prvsnlPnsnpaidFromDate}"/>"   maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);" onKeyPress="numberFormat(event)" onfocus="onFocus(this)"	onblur="onBlur(this);chkValidDate(this);" />
			<img src='images/CalendarImages/ico-calendar.gif' id="imgFromDate" width='20' onClick='window_open(event,"txtFromDatePrvsnlPnsn",375,570)'
				style="cursor: pointer;${varDisplayPro}" />
			</td>
		<td width="20%" align="left"><fmt:message key="PPROC.TO" bundle="${pensionLabels}"></fmt:message>		</td>
		<td width="30%" align="left">
			<input type="text"	name="txtToDatePrvsnlPnsn" ${varReadOnlyPro} id="txtToDatePrvsnlPnsn" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnprocPnsnrpayVO.prvsnlPnsnpaidToDate}"/>"   maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);" onKeyPress="numberFormat(event)" onfocus="onFocus(this)"	onblur="onBlur(this);chkValidDate(this);" />
			<img src='images/CalendarImages/ico-calendar.gif'  id="imgToDate"  width='20' onClick='window_open(event,"txtToDatePrvsnlPnsn",375,570)'
				style="cursor: pointer;${varDisplayPro}" />
		</td>		
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.SANCTIONAUTHNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text"	name='txtSancAuthNo' id="txtSancAuthNo" ${varReadOnlyPro} value="${lObjTrnPnsnprocPnsnrpayVO.prvsnlPnsnpaidAuthNo}"
			onfocus="onFocus(this)"	onblur="onBlur(this);" />
		</td>
		<td width="20%" align="left"><fmt:message key="PPROC.SANCTIONAUTHNAME"	bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text"	name='txtSancAuthName' ${varReadOnlyPro} style="text-transform: uppercase;" id="txtSancAuthName" value="${lObjTrnPnsnprocPnsnrpayVO.prvsnlPnsnpaidAuthName}"
			onfocus="onFocus(this)"	onblur="onBlur(this);isName(this,'Please enter valid Name');" />
		</td>
	</tr>
</table>
<br>
<hdiits:button	name="PrvsnlPnsnAddRow" id="PrvsnlPnsnAddRow" readonly="${varReadOnlyProButton}" type="button" captionid="PPROC.ADDROW"	bundle="${pensionLabels}" onclick="provisionalPaidTableAddRow();" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:button	name="PrvsnlPnsnReport" id="PrvsnlPnsnReport" type="button" captionid="PPROC.REPORT" bundle="${pensionLabels}" onclick="calDayMonthYear()" />
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 99%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">	
<table width="100%" id="tblPrvsnlPnsnDtls" border="1" >	
	<tr class="datatableheader">
		<td width="15%" class="datatableheader"><fmt:message key="PPROC.SANCTIONAUTHNAME" bundle="${pensionLabels}" ></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>	
			<td width="10%" class="datatableheader"><fmt:message key="PPROC.SANCTIONAUTHNO" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="11%" class="datatableheader"><fmt:message key="PPROC.SANCTIONDATE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			
			<%--<td width="11%" class="datatableheader"><fmt:message key="PPROC.APPLFROMDATE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="11%" class="datatableheader"><fmt:message key="PPROC.APPLTODATE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			--%><td width="10%" class="datatableheader"><fmt:message key="PPROC.BILLTYPE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="5%" class="datatableheader"><fmt:message key="PPROC.AMOUNT" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="10%" class="datatableheader"><fmt:message key="PPROC.VOUCHERNO" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="11%" class="datatableheader"><fmt:message key="PPROC.VOUCHERDATE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>			
			<td width="1%" class="datatableheader"><fmt:message key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>						
	</tr>	
<c:choose>
		<c:when test="${resValue.lLstTrnPnsnprocProvisionalPaid !=null}">
			<c:forEach var="ProvisionalPaid" items="${resValue.lLstTrnPnsnprocProvisionalPaid}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
							<select name="cmbSancAuthName" id="cmbSancAuthName${Counter.index}" onblur="validateAuthorityName();">
								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>															            
						        <c:choose>
			    				<c:when test="${ProvisionalPaid.provPensionSanctionAuthority == 'M'}">
			    					<option value="O">${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}</option>
			    					<option value="M" selected="selected"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
			   						<option value="N"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>			    					
			    				</c:when>
			    				<c:when test="${ProvisionalPaid.provPensionSanctionAuthority == 'N'}">			    		
			    					<option value="O">${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}</option>						   						
			   						<option value="M" ><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
			   						<option value="N"selected="selected"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>			    					
			    				</c:when>
			    				<c:when test="${ProvisionalPaid.provPensionSanctionAuthority == 'O'}">			    		
			    					<option value="O" selected="selected">${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}</option>						   						
			   						<option value="M" ><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
			   						<option value="N"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>			    					
			    				</c:when>			    					
			    				<c:otherwise>
			    					<option value="O">${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}</option>
			    					<option value="M"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
			   						<option value="N"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>
			    				</c:otherwise>
			    				</c:choose>					        
							</select>		
									
							<input type="hidden" name="hdnProvPnsnDtlsId" id="hdnProvPnsnDtlsId${Counter.index}" value="${ProvisionalPaid.provisionalPaidId}"/>
						</td>						
						<td class="tds" align="center">
							<input type="text" name="txtSancAuthNoPro" id="txtSancAuthNoPro${Counter.index}" size="10" value="${ProvisionalPaid.provPensionAuthorityNo}"
									onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="20" />					
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtSanctionedDate" id="txtSanctionedDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${ProvisionalPaid.provPensionAuthorityDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtSanctionedDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
						</td>
						
						<%--<td class="tds" align="center">
							<input type="text" name="txtApplnFromDate" id="txtApplnFromDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${ProvisionalPaid.appFromDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);validateAddFromAndToDate(${Counter.index})"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtApplnFromDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtApplnToDate" id="txtApplnToDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${ProvisionalPaid.appToDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);validateAddFromAndToDate(${Counter.index})"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtApplnToDate${Counter.index}",375,570)'style="cursor: pointer;"/>
						</td>
						--%>
						<td class="tds" align="center">
							<select name="cmbBillType" id="cmbBillType${Counter.index}" >
								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>															            
						        <c:choose>
			    				<c:when test="${ProvisionalPaid.billType == 'P'}">
			    					<option value="P" selected="selected"><fmt:message key="PPROC.PENSION" bundle="${pensionLabels}"/></option>
			   						<option value="G"><fmt:message key="PPROC.GRATUITY" bundle="${pensionLabels}"/></option>			    					
			    				</c:when>
			    				<c:when test="${ProvisionalPaid.billType == 'G'}">			    								   						
			   						<option value="P" ><fmt:message key="PPROC.PENSION" bundle="${pensionLabels}"/></option>
			   						<option value="G"selected="selected"><fmt:message key="PPROC.GRATUITY" bundle="${pensionLabels}"/></option>			    					
			    				</c:when>	
			    				<c:otherwise>
			    					<option value="P"><fmt:message key="PPROC.PENSION" bundle="${pensionLabels}"/></option>
			   						<option value="G"><fmt:message key="PPROC.GRATUITY" bundle="${pensionLabels}"/></option>
			    				</c:otherwise>
			    				</c:choose>					        
							</select>
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtPaidAmount" id="txtPaidAmount${Counter.index}" size="10" value="${ProvisionalPaid.basicPensionAmount}"
							onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>					
						</td>
						
						<td class="tds" align="center">							
							<input type="text" name="txtProVoucherNo" id="txtProVoucherNo${Counter.index}" size="10" value="${ProvisionalPaid.voucherNo}" maxlength="20"/>					
						</td>
					
						<td class="tds" align="center">							
							<input type="text" name="txtProVoucherDate" id="txtProVoucherDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${ProvisionalPaid.voucherDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtProVoucherDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
						</td>							
						<td class="tds" align="center">
							<img name="Image"  id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblPrvsnlPnsnDtls')" />
						</td>
				</tr>
				<script>
						document.getElementById("hidPrvsnlPnsnGridSize").value=Number('${Counter.index}') + 1;
				</script>
			</c:forEach>
		</c:when>
	</c:choose>
	</table>
	</div>
	<table>
	<tr style="display: none;">
	<td width="20%" align="left"><fmt:message
			key="PPROC.PNSN/GRTYCONTRNPAID" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		<c:choose>
		<c:when test="${lObjTrnPnsnprocPnsnrpayVO.prvsnlGratuityFlag == 89}">
			<input type="radio" id="radioPnsnGrtyPaid" name="radioPnsnGrtyPaid" maxlength="3" value="Y" onclick="" checked="checked" />
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioPnsnGrtyPaid" name="radioPnsnGrtyPaid" maxlength="3" value="N" onclick="" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:otherwise>
			<input type="radio" id="radioPnsnGrtyPaid" name="radioPnsnGrtyPaid" value="Y" onclick=""  maxlength="3" />
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioPnsnGrtyPaid" name="radioPnsnGrtyPaid" value="N" onclick="" checked="checked"  maxlength="3" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:otherwise>
		</c:choose>
		
		</td>
		<td width="20%" align="left"></td>
		<td width="30%" align="left"></td>
	</tr>
	<tr style="display: none;">
		<td width="20%" align="left"><fmt:message key="PPROC.DATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text"
			name="txtDateOfPnsnGrtyPaid" id="txtDateOfPnsnGrtyPaid"
			maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
			onKeyPress="numberFormat(event);" onfocus="onFocus(this)"
			onblur="onBlur(this);chkValidDate(this);" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnprocPnsnrpayVO.prvsnlGratuityDate}"/>"  /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open(event,"txtDateOfPnsnGrtyPaid",375,570)'
			style="cursor: pointer;" /></td>
		<td width="20%" align="left"><fmt:message key="PPROC.VOUCHERNO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text"
			name='txtPrvsnlGrtyVoucherNo' id="txtPrvsnlGrtyVoucherNo" value="${lObjTrnPnsnprocPnsnrpayVO.prvsnlGratuityVoucherno}" maxlength="5"
			onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
			onblur="onBlur(this);" style="text-align: right" />
		</td>
		
	</tr>
	<tr style="display: none;">
		<td width="20%" align="left"><fmt:message key="PPROC.AMOUNT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text"
			name='txtPrvsnlGrtyAmount' id="txtPrvsnlGrtyAmount" maxlength="7"
			style="text-align: right" onKeyPress="amountFormat(this,event)"
			onfocus="onFocus(this)" onblur="onBlur(this);" value="${lObjTrnPnsnprocPnsnrpayVO.prvsnlGratuityAmount}"  />
		</td>
		<td width="20%" align="left"></td>
		<td width="30%" align="left"></td>
	</tr>
</table>	
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="EVENTDTLS"
	bundle="${pensionLabels}"></fmt:message></b></legend> <input type="hidden"
	name="hidEventGridSize" id="hidEventGridSize" value="1" /> <hdiits:button
	name="EventDtlsAddRow" type="button" captionid="PPROC.ADDROW"
	bundle="${pensionLabels}" onclick="eventDtlsTableAddRow();" />
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblEventDtls" align="left" width="90%" cellspacing="0"
	border="1">

	<tr class="datatableheader" style="width: 90px">
		<td width="15%" class="HLabel"><fmt:message key="PPROC.EVENT"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPROC.PAYSCALE"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPROC.BASIC"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPROC.DP"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel"><fmt:message key="PPROC.FROM"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel"><fmt:message key="PPROC.TO"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<c:choose>
					<c:when test="${resValue.lLstTrnPnsnprocEventdtls !=null}">
						<c:forEach var="EventDtlsVO" items="${resValue.lLstTrnPnsnprocEventdtls}" varStatus="Counter">
						<tr>
							<td class="tds" align="center">
							<select name="cmbEvent" id="cmbEvent${Counter.index}" onchange="EventDtls(this,'${Counter.index}')">
								<option value="-1">--Select--</option>
								<c:forEach var="EventList" items="${resValue.lLstEvents}">
									<c:choose>
												<c:when test="${EventList.lookupId == EventDtlsVO.eventId}">
													<option selected="selected" value='${EventList.lookupId}'>
													<c:out value="${EventList.lookupName}"></c:out></option>
												</c:when>
												<c:otherwise>
													<option value='${EventList.lookupId}'> <c:out value="${EventList.lookupName}"></c:out></option>
												</c:otherwise>
											</c:choose>
								</c:forEach>
								</select>
							</td>
							<td class="tds" align="center">
								<select name="cmbPayScaleEvent" id="cmbPayScaleEvent${Counter.index}"><option value="-1">--Select--</option>
								<c:forEach var="PayScale" items="${resValue.PayScaleList}" >
									<c:choose>
												<c:when test="${PayScale.id == EventDtlsVO.payScaleId}">
													<option selected="selected" value='${PayScale.id}'>
													<c:out value="${PayScale.desc}"></c:out></option>
												</c:when>
												<c:otherwise>
													<option value='${PayScale.id}'> <c:out value="${PayScale.desc}"></c:out></option>
												</c:otherwise>
											</c:choose>
								</c:forEach>
								</select>
							</td>
							<td class="tds" align="center">
								<input type="text" name="txtBasic" maxlength="7" id="txtBasic${Counter.index}" size="20" onkeypress="numberFormat(event);"   style="width:60px"  value="${EventDtlsVO.basic}" >
							</td>
							<td class="tds" align="center">
								<input type="text" name="txtEventDP" maxlength="7" id="txtEventDP${Counter.index}" size="20" onkeypress="numberFormat(event);"   style="width:60px" value="${EventDtlsVO.dp}">		
							</td>
						
						<td class="tds" align="center">
								<input type="text" name="txtDateOfEventFrom" id="txtDateOfEventFrom${Counter.index}"  style="width:90px"  
								 onblur="chkValidDate(this);compareDates(txtDateOfStartingService,this,'From date should be greater than Date Of Joining Government Service','<');"   maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${EventDtlsVO.fromDate}"/>" />
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px" onClick='window_open(event,"txtDateOfEventFrom${Counter.index}",375,570)' style="cursor: pointer;" />			
							</td>
							<td class="tds" align="center">
								<input type="text" name="txtDateOfEventTo" id="txtDateOfEventTo${Counter.index}" maxlength="10" onkeypress="numberFormat(event);dateFormat(this);"  onblur="chkValidDate(this);compareDates(this,txtDateOfRetiremt,'To date should be less than Retirement date','<');validateWithFromDate('${Counter.index}');"  style="width:90px"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${EventDtlsVO.toDate}"/>">
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px"  onClick='window_open(event,"txtDateOfEventTo${Counter.index}",375,570)' style="cursor: pointer;" />
							</td>
							
							<td class="tds" align="center">
								<img name="Image"  id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblEventDtls')" /> <input type="hidden" id="pk${Counter.index}" name="pk${Counter.index}" value="${EventDtlsVO.pnsnrEventdtlId}"/>
							</td>
						</tr>
					<script>
						document.getElementById("hidEventGridSize").value=Number('${Counter.index}') + 1;
						
					</script>
					</c:forEach>
					</c:when>
					
				</c:choose>
</table>
</div>
</fieldset>

<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="NONQUALFYINGSER"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	<input type="hidden" name="hidSrvcBrkGridSize" id="hidSrvcBrkGridSize" value="0" />  
	<hdiits:button
	name="ServBrkDtlsAddRow" type="button" captionid="PPROC.ADDROW"
	bundle="${pensionLabels}" onclick="serBrkTableAddRow();" />
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblServBrkDtls" align="left" width="90%" cellspacing="0"
	border="1">

	<tr class="datatableheader" style="width: 90px">
		<td width="20%" class="HLabel"><fmt:message key="PPROC.TYPEOFBREAK"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel"><fmt:message key="PPROC.EFFECTFROMDATE"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel"><fmt:message key="PPROC.TO"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPROC.REMARKS"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="5%" class="HLabel"><fmt:message
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>			
	
	</tr>
	
	<c:choose>
					<c:when test="${resValue.lLstTrnPnsnprocPnsnrservcbreak !=null}">
						<c:forEach var="SrvcBrkVO" items="${resValue.lLstTrnPnsnprocPnsnrservcbreak}" varStatus="Counter">
						<tr>
						<td class="tds" align="center">
								<select name="cmbTypeofBrk" id="cmbTypeofBrk${Counter.index}" onchange="showSrvcBrkOtherReason(${Counter.index})" ><option value="-1">--Select--</option>
								<c:forEach var="SrvcBrkType" items="${resValue.lLstSrvcBrkType}" >
									<c:choose>
												<c:when test="${SrvcBrkType.lookupId == SrvcBrkVO.serviceBreaktypeLookupId}">
													<option selected="selected" title="${SrvcBrkType.lookupDesc}" value='${SrvcBrkType.lookupId}'>
													<c:out value="${SrvcBrkType.lookupDesc}"></c:out></option>
												</c:when>
												<c:otherwise>
													<option value='${SrvcBrkType.lookupId}' title="${SrvcBrkType.lookupDesc}"> <c:out value="${SrvcBrkType.lookupDesc}"></c:out></option>
												</c:otherwise>
											</c:choose>
								</c:forEach>
								</select>
								<c:choose>
									<c:when test="${SrvcBrkVO.serviceBreaktypeLookupId == 10036}">
										<c:set var="varDisplayNone" value=""></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="varDisplayNone" value="style='display: none;'"></c:set>
									</c:otherwise>
								</c:choose>
								<div ${varDisplayNone} id="divSrvBreakReason${Counter.index}">	
								<fmt:message key="PPROC.REASON" bundle="${pensionLabels}"></fmt:message><input type="text" name="txtSrvBreakOtherReason" id="txtSrvBreakOtherReason${Counter.index}" value="${SrvcBrkVO.srvcBrkOtherReason}"/>
								</div>
							</td>

							<td class="tds" align="center">
								<input type="text" name="txtDateOfBrkFrom" id="txtDateOfBrkFrom${Counter.index}" onblur="chkValidDate(this);compareDates(txtDateOfStartingService,this,'From date should be greater than Date Of Joining Government Service','<');validateWithFromDate('${Counter.index}');calTotalSrvcBrk('${Counter.index}');setTotalDaysOfSrvcBrk('txtDateOfBrkFrom','txtDateOfBrkTo');" onkeypress="digitFormat(this);dateFormat(this);"   style="width:90px"  maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${SrvcBrkVO.breakFromDate}"/>" />
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px" onClick='window_open(event,"txtDateOfBrkFrom${Counter.index}",375,570)' style="cursor: pointer;" />			
							</td>



							<td class="tds" align="center">
								<input type="text" name="txtDateOfBrkTo" id="txtDateOfBrkTo${Counter.index}"  onblur="chkValidDate(this);compareDates(this,txtDateOfRetiremt,'To date should be less than Retirement date','<');validateWithFromDate('${Counter.index}');calTotalSrvcBrk('${Counter.index}');setTotalDaysOfSrvcBrk('txtDateOfBrkFrom','txtDateOfBrkTo');" onkeypress="digitFormat(this);dateFormat(this);"   style="width:90px"  maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${SrvcBrkVO.breakToDate}"/>">
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px" onClick='window_open(event,"txtDateOfBrkTo${Counter.index}",375,570)' style="cursor: pointer;" />
							</td>

								<td class="tds" align="center" width="5%">

									
									<input type="text" name="txtRemarks" id="txtRemarks${Counter.index}" value="${SrvcBrkVO.srvcBrkRemarks}"> 
									
							</td>




							<td class="tds" align="center">
								<input type="hidden" id="hidDays${Counter.index}" name="hidDays${Counter.index}"  />
								<input type="hidden" id="hidDiffDay${Counter.index}" name="hidDiffDay'${Counter.index}">
								<input type="hidden" id="hidMonths${Counter.index}" name="hidMonths${Counter.index}">
								<input type="hidden" id="hidYear${Counter.index}" name="hidYear${Counter.index}">
								
								<input type="hidden" id="pk${Counter.index}" name="pk${Counter.index}" value="${SrvcBrkVO.pnsnrServcBreakId}"/>
								<img name="Image"  id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblServBrkDtls');adjustTotalDays('${Counter.index}');setTotalDaysOfSrvcBrk('txtDateOfBrkFrom','txtDateOfBrkTo');calQualifyingSrvc();" />
							</td>

	
						</tr>
					<script>
						document.getElementById("hidSrvcBrkGridSize").value=Number('${Counter.index}') + 1;
					</script>
					</c:forEach>
					</c:when>
					
				</c:choose>
	
</table>
</div>



<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPROC.ADDQLY"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	<input type="hidden" name="hidQlySrvcGridSize" id="hidQlySrvcGridSize" value="0" />  
	<hdiits:button
	name="QlyServDtlsAddRow" type="button" captionid="PPROC.ADDROW"
	bundle="${pensionLabels}" onclick="qylServTableAddRow();" />
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblQylServDtls" align="left" width="90%" cellspacing="0"
	border="1">

	<tr class="datatableheader" style="width: 90px">
		<td width="20%" class="HLabel"><fmt:message key="PPROC.GR"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
			<td width="20%" class="HLabel"><fmt:message key="PPROC.GRDATE"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel"><fmt:message key="PPROC.EFFECTFROMDATE"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel"><fmt:message key="PPROC.TO"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPROC.REMARKS"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="5%" class="HLabel"><fmt:message
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>			
	
	</tr>
	
	<c:choose>
					<c:when test="${resValue.lLstTrnPnsnProcQlyServ !=null}">
						<c:forEach var="QlyServVO" items="${resValue.lLstTrnPnsnProcQlyServ}" varStatus="Counter">
						<tr>
						<td class="tds" align="center">
								<input type="text" name="grNo" id="grNo${Counter.index}" value="${QlyServVO.grNo}"> 
							</td>
							
							<td class="tds" align="center">
								<input type="text" name="grDate" id="grDate${Counter.index}" onblur="chkValidDate(this);" onkeypress="digitFormat(this);dateFormat(this);"   style="width:90px"  maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${QlyServVO.grDate}"/>" />
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px" onClick='window_open(event,"txtDateOfBrkFrom${Counter.index}",375,570)' style="cursor: pointer;" />			
							</td>
							
							<td class="tds" align="center">
								<input type="text" name="txtDateOfQlyFrom" id="txtDateOfQlyFrom${Counter.index}" onblur="chkValidDate(this);calTotalQlyServ('${Counter.index}');setTotalDaysOfQlyServ('txtDateOfQlyFrom','txtDateOfQlyTo');validateQlyFromDate('${Counter.index}');" onkeypress="digitFormat(this);dateFormat(this);"   style="width:90px"  maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${QlyServVO.qlyServFromDate}"/>" />
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px" onClick='window_open(event,"txtDateOfQlyFrom${Counter.index}",375,570)' style="cursor: pointer;" />			
							</td>

							<td class="tds" align="center">
								<input type="text" name="txtDateOfQlyTo" id="txtDateOfQlyTo${Counter.index}"  onblur="chkValidDate(this);calTotalQlyServ('${Counter.index}');setTotalDaysOfQlyServ('txtDateOfQlyFrom','txtDateOfQlyTo');validateQlyFromDate('${Counter.index}');" onkeypress="digitFormat(this);dateFormat(this);"   style="width:90px"  maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${QlyServVO.qlyServToDate}"/>">
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px" onClick='window_open(event,"txtDateOfQlyTo${Counter.index}",375,570)' style="cursor: pointer;" />
							</td>
								<td class="tds" align="center" width="5%">

									<input type="text" name="txtQlyRemarks" id="txtQlyRemarks${Counter.index}" value="${QlyServVO.qlyServRemarks}"> 
							</td>

							<td class="tds" align="center">
								<input type="hidden" id="hidQylDays${Counter.index}" name="hidQylDays${Counter.index}"  />
								<input type="hidden" id="hidQylDiffDay${Counter.index}" name="hidQylDiffDay'${Counter.index}">
								<input type="hidden" id="hidQylMonths${Counter.index}" name="hidQylMonths${Counter.index}">
								<input type="hidden" id="hidQylYear${Counter.index}" name="hidQylYear${Counter.index}">
								
								<input type="hidden" id="pk${Counter.index}" name="pk${Counter.index}" value="${SrvcBrkVO.pnsnrQlyServId}"/>
								<img name="Image"  id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblQylServDtls');adjustTotalDays('${Counter.index}');setTotalDaysOfQlyServ('txtDateOfQlyFrom','txtDateOfQlyTo');calQualifyingSrvc();" />
							</td>

	
						</tr>
					<script>
						document.getElementById("hidQlySrvcGridSize").value=Number('${Counter.index}') + 1;
					</script>
					</c:forEach>
					</c:when>
					
				</c:choose>
	
</table>
</div>

<table  align="left">
	<tr>
		<td   align="left"><fmt:message key="PPROC.TOTSERBREAK"
			bundle="${pensionLabels}"></fmt:message></td>
		<td  align="left"><input type="hidden"
			name='txtTotSerBreak' id="txtTotSerBreak" style="text-align: right"
			onKeyPress="amountFormat(this,event)" onfocus="onFocus(this)" readOnly="readOnly"
			onblur="onBlur(this);calQualifyingSrvc();" value="${lObjTrnPnsnprocPnsnrpayVO.totalServiceBreak}"/>
			Year<input type="text" id="txtTotSerBreakYear" name="txtTotSerBreakYear" value="${lObjTrnPnsnprocPnsnrpayVO.brkYear}" size="5" readonly="readonly"/>
			Month<input type="text" id="txtTotSerBreakMonth" name="txtTotSerBreakMonth" value="${lObjTrnPnsnprocPnsnrpayVO.brkMonth}" size="5" readonly="readonly"/>
			Day<input type="text" id="txtTotSerBreakDay" name="txtTotSerBreakDay" value="${lObjTrnPnsnprocPnsnrpayVO.brkDay}" size="5" readonly="readonly"/>
		</td>
		<td  align="left"><fmt:message key="PPROC.ADDQLY"
			bundle="${pensionLabels}"></fmt:message></td>
			<td   align="left"><input type="hidden"
			name='txtTotQlyServ' id="txtTotQlyServ" style="text-align: right"
			onKeyPress="amountFormat(this,event)" onfocus="onFocus(this)" readOnly="readOnly"
			onblur="onBlur(this);calQualifyingSrvc();" value="${lObjTrnPnsnprocPnsnrpayVO.totalQlyService}"/>
			Year<input type="text" id="txtTotQlyServYear" name="txtTotQlyServYear" value="${lObjTrnPnsnprocPnsnrpayVO.qlyYear}" size="5" readonly="readonly"/>
			Month<input type="text" id="txtTotQlyServMonth" name="txtTotQlyServMonth" value="${lObjTrnPnsnprocPnsnrpayVO.qlyMonth}" size="5" readonly="readonly"/>
			Day<input type="text" id="txtTotQlyServDay" name="txtTotQlyServDay" value="${lObjTrnPnsnprocPnsnrpayVO.qlyDay}" size="5" readonly="readonly"/>
		</td>
		<td  >
						
		</td>
	
	</tr>
	<tr>
		<td  align="left"><fmt:message
			key="PPROC.QUALIFYINGSERV" bundle="${pensionLabels}"></fmt:message></td>
		<td  align="left"><fmt:message key="PPROC.ACTUALSER"
			bundle="${pensionLabels}"></fmt:message></td>
		<td  align="left"><fmt:message key="PPROC.MINUS"
			bundle="${pensionLabels}"></fmt:message></td>
		<td  align="left"><fmt:message
			key="PPROC.NONQULFYINGSERV" bundle="${pensionLabels}"></fmt:message></td>
			<td  align="left"><fmt:message key="PPROC.PLUS"
			bundle="${pensionLabels}"></fmt:message></td>
		<td  align="left"><fmt:message
			key="PPROC.ADDQLY" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<tr style="display: none;">
		<td  align="left"><input type="text"
			name='txtQualifyingServ' id="txtQualifyingServ" onKeyPress="amountFormat(this,event)" readOnly="readOnly"
			style="text-align: right"  value="${lObjTrnPnsnprocPnsnrpayVO.qualifyingService}"  /></td>
		<td  align="left"><input type="text" onKeyPress="amountFormat(this,event)"
			name='txtActualSer' id="txtActualSer" style="text-align: right" readOnly="readOnly"
			value="${lObjTrnPnsnprocPnsnrpayVO.actualService}"/>
			</td>
		<td  align="left">&nbsp;<strong>-</strong>&nbsp;</td>
		<td  align="left"><input type="text" onKeyPress="amountFormat(this,event)"
			name='txtNonQualifyingServ' id="txtNonQualifyingServ" readOnly="readOnly"
			style="text-align: right"  value="${lObjTrnPnsnprocPnsnrpayVO.nonQualifyingService}" /></td>
			
			
			<td  align="left">&nbsp;<strong>+</strong>&nbsp;</td>
		<td  align="left"><input type="text" onKeyPress="amountFormat(this,event)"
			name='txtQlyService' id="txtQlyService" readOnly="readOnly"
			style="text-align: right"  value="${lObjTrnPnsnprocPnsnrpayVO.qlyService}" /></td>
			
	</tr>
	<tr>
		<td  align="left">
		Year<input type="text" id="txtQualifyingServYear" name="txtQualifyingServYear" value="${lObjTrnPnsnprocPnsnrpayVO.qulifyYear}" size="5" readonly="readonly"/>
		Month<input type="text" id="txtQualifyingServMonth" name="txtQualifyingServMonth" value="${lObjTrnPnsnprocPnsnrpayVO.qulifyMonth}" size="2" readonly="readonly"/>
		Day<input type="text" id="txtQualifyingServDay" name="txtQualifyingServDay" value="${lObjTrnPnsnprocPnsnrpayVO.qulifyDay}" size="2" readonly="readonly"/>
		</td>
		<td  align="left">&nbsp;<strong><font size="3">=</font><strong></td>
		<td  align="left">
		Year<input type="text" id="txtActualSerYear" name="txtActualSerYear" value="${lObjTrnPnsnprocPnsnrpayVO.actualYear}" size="5" readonly="readonly"/>
		Month<input type="text" id="txtActualSerMonth" name="txtActualSerMonth" value="${lObjTrnPnsnprocPnsnrpayVO.actualMonth}" size="2" readonly="readonly"/>
		Day<input type="text" id="txtActualSerDay" name="txtActualSerDay" value="${lObjTrnPnsnprocPnsnrpayVO.actualDay}" size="2" readonly="readonly"/>		
		</td>
		<td align="left">&nbsp;<strong><font size="5">-</font><strong>&nbsp;</td>
		<td  align="left">
		Year<input type="text" id="txtNonQualifyingServYear" name="txtNonQualifyingServYear" value="${lObjTrnPnsnprocPnsnrpayVO.brkYear}" size="5" readonly="readonly"/>
		Month<input type="text" id="txtNonQualifyingServMonth" name="txtNonQualifyingServMonth" value="${lObjTrnPnsnprocPnsnrpayVO.brkMonth}" size="2" readonly="readonly"/>
		Day<input type="text" id="txtNonQualifyingServDay" name="txtNonQualifyingServDay" value="${lObjTrnPnsnprocPnsnrpayVO.brkDay}" size="2" readonly="readonly"/>	
		</td>
		
		<td  align="left">&nbsp;<strong><font size="3">+</font><strong>&nbsp;</td>
		<td align="left">
		Year<input type="text" id="txtQualiServYear" name="txtQualiServYear" value="${lObjTrnPnsnprocPnsnrpayVO.qlyYear}" size="5" readonly="readonly"/>
		Month<input type="text" id="txtQualiServMonth" name="txtQualiServMonth" value="${lObjTrnPnsnprocPnsnrpayVO.qlyMonth}" size="2" readonly="readonly"/>
		Day<input type="text" id="txtQualiServDay" name="txtQualiServDay" value="${lObjTrnPnsnprocPnsnrpayVO.qlyDay}" size="2" readonly="readonly"/>	
		</td>
	</tr>
</table>
</fieldset>
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message
	key="PPROC.AVGPAYCALCULATOR" bundle="${pensionLabels}"></fmt:message></b></legend> <input
	type="hidden" name="hidCalcGridSize" id="hidCalcGridSize" value="0" />
<table>
<tr>
	<td style="display: none;">
	<hdiits:button name="AvgPayCalcAddRow" type="button" captionid="PPROC.ADDROW" bundle="${pensionLabels}"	onclick="avgPayCalcTableAddRow();" />
	</td>
	<td>
	<fmt:message key="PPROC.FROM" bundle="${pensionLabels}"></fmt:message>
	<input type="text" name="txtEmolumentFromDate" id="txtEmolumentFromDate" size="20" readOnly="readOnly" />&nbsp;&nbsp;
	</td>
	<td>
	<fmt:message key="PPROC.TO" bundle="${pensionLabels}"></fmt:message>
	<input type="text" name="txtEmolumentToDate" id="txtEmolumentToDate" size="20" readOnly="readOnly" />&nbsp;&nbsp;
	</td>
	<td>	
	<c:choose>
		<c:when test="${lObjTrnPnsnProcInwardPensionVO.pensionerType == 'Medical Officer'}">				
				<c:set value="display: inline;" var="varDisplayNone" ></c:set>
		</c:when>
		<c:otherwise>				
				<c:set value="display: none;" var="varDisplayNone" ></c:set>
		</c:otherwise>	
	</c:choose>	
	<div id="npaAllow" style="${varDisplayNone}">
		<fmt:message key="PROC.NPAALLOWED" bundle="${pensionLabels}"></fmt:message>&nbsp;&nbsp;
		<input type="checkbox" name="chkBoxNPA" id="chkBoxNPA" onclick="setNPAAllow();"/>
	</div>
	</td>
</tr>
	</table>
	
	
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 60%; height: 175px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblAvgPayCalc" align="left" width="95%" cellspacing="0"
	border="1">

	<tr class="datatableheader" style="width: 90px">
	<td width="10%" class="HLabel" ><fmt:message
			key="PPROC.SRNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="HLabel"><fmt:message key="PPROC.FROM"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message
			key="PPROC.PAYBAND" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message
			key="PPROC.DP" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message
			key="PPROC.NPA" bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="HLabel" ><fmt:message
			key="PPROC.TOTAL" bundle="${pensionLabels}"></fmt:message></td>
		
	</tr>
	
	
	<c:choose>
					<c:when test="${resValue.lLstTrnPnsnprocAvgPayCalcVO != null }">
						<c:forEach var="AvgPayCalVO" items="${resValue.lLstTrnPnsnprocAvgPayCalcVO}" varStatus="Counter">
						<tr>
							<td class="tds" align="center">
								${Counter.index + 1}			
							</td>
							<td class="tds" align="center">
								<input type="text" name="txtPeriodFromDate" id="txtPeriodFromDate${Counter.index}" size="20" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);chkPeriodFromDateWithEmolumentToDate('${Counter.index}');chkPeriodFromDateWithEmolumentFromDate();"  maxlength="10"  style="width:90px"  maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${AvgPayCalVO.fromDate}"/>" />
								<img src='images/CalendarImages/ico-calendar.gif' style="width:16px" onClick='window_open(event,"txtPeriodFromDate${Counter.index}",375,570)' style="cursor: pointer;"/>			
							</td>
							<c:choose>
							<c:when test="${flag=='DDO'}">
							<td class="tds" align="center">
							 	<input type="text" name="txtAvgPayBasic" maxlength="7" id="txtAvgPayBasic${Counter.index}"  size="30"   value="${AvgPayCalVO.basic}" onkeypress="amountFormat(this,event);" onblur="validateBasicPay(this);setTotal(${Counter.index});setValidAmountFormat(this);calTotal();"   style="text-align: right;width:90px">		
							</td>
							</c:when>
							<c:otherwise>
							<td class="tds" align="center">
							 	<input type="text" name="txtAvgPayBasic" maxlength="7" id="txtAvgPayBasic${Counter.index}"  size="30"   value="${AvgPayCalVO.basic-AvgPayCalVO.dp}" onkeypress="amountFormat(this,event);" onblur="validateBasicPay(this);setTotal(${Counter.index});setValidAmountFormat(this);calTotal();"   style="text-align: right;width:90px">		
							</td>
							</c:otherwise>
							</c:choose>
							<td class="tds" align="center">
							 	<input type="text" name="txtAvgPayDP" maxlength="7" id="txtAvgPayDP${Counter.index}" size="30"   value="${AvgPayCalVO.dp}" onkeypress="amountFormat(this,event);" onblur="setTotal(${Counter.index});setValidAmountFormat(this);calTotal();"   style="text-align: right;width:90px">		
							</td>
							<td class="tds" align="center">
							 	<input type="text" name="txtNPA" id="txtNPA${Counter.index}" maxlength="7" size="30"   value="${AvgPayCalVO.npa}" onkeypress="amountFormat(this,event);" onblur="setTotal(${Counter.index});setValidAmountFormat(this);calTotal();"   style="text-align: right;width:90px">		
							</td>
							<td class="tds" align="center">
							 	<input type="text" name="txtTotal" id="txtTotal${Counter.index}" maxlength="7" size="30" readonly="readonly"  value="${AvgPayCalVO.total}" onkeypress="amountFormat(this,event);" onBlur="calTotal();setValidAmountFormat(this)"   style="text-align: right;width:90px">		
							</td>
						</tr>
					<script>
						document.getElementById("hidCalcGridSize").value=Number('${Counter.index}') + 1;
					</script>
					</c:forEach>
					</c:when>
					
				</c:choose>
</table>
</div>
<table width="80%">
	<tr>
		<td width="20%" align="right"></td>
		<td width="20%" align="right" class="datatableheader"><fmt:message
			key="PPROC.GRANDTOTAL" bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="right"><input type="text" onkeypress="amountFormat(this,event)"
			name="txtGrandTotal" id="txtGrandTotal" maxlength="7"  size="20" value="${lObjTrnPnsnprocPnsnrpayVO.grandTotal}" /></td>

		<td width="35%" align="right">
	</tr>
	<tr>
		<td width="20%" align="right"></td>
		<td width="20%" align="right" class="datatableheader"><fmt:message
			key="PPROC.AVGPAY" bundle="${pensionLabels}">
		</fmt:message></td> 
		<td width="20%" align="right"><input type="text" name="txtAvgPay" maxlength="7" onkeypress="amountFormat(this,event)"
			id="txtAvgPay" size="20" value="${lObjTrnPnsnprocPnsnrpayVO.averagePay}" /></td>
		<td width="35%" align="right"></td>
	</tr>
</table>

</fieldset>


<script>
setEmolumnetToDate(1);
var CalcGridSize =  document.getElementById("hidCalcGridSize").value;

for(var i=0;i<10 - Number(CalcGridSize);i++){
	avgPayCalcTableAddRow();
}

disableDP();
popUpGradePayAndDP();
calActualServiceDays();
setTotalDaysOfSrvcBrk('txtDateOfBrkFrom','txtDateOfBrkTo');
calTotal();
//added by aditya
/* var flag=document.getElementById("flag").value;

if(flag=="false"){
	calAvgTotal();
}
else{
	calTotal();
} */
//added by aditya 

</script>
<script>




</script>
	<%}catch(Exception e){
e.printStackTrace();
}%>					
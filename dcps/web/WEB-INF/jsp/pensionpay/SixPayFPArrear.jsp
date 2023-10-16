<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>


<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/SixPayFPArrear.js"></script>


<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="Revision" value="${resValue.Revision}" />
<c:set var="PnsnrCode" value="${resValue.PnsnrCode}" />
<c:set var="ListOfPnsnrCodes" value="${resValue.lLstPnsnrCode}" />
<c:set var="TotalNoOfPnsnrCode" value="${resValue.TotalNoOfPnsnrCode}" />

<c:set var="ListOfMonthDesc" value="${resValue.ListOfMonthDesc}" />
<c:set var="ListOfMonthId" value="${resValue.ListOfMonthId}" />
<c:set var="TotalNoOfMonths" value="${resValue.TotalNoOfMonths}" />
<c:set var="ListOfYearDesc" value="${resValue.ListOfYearDesc}" />
<c:set var="TotalNoOfYears" value="${resValue.TotalNoOfYears}" />
<script type="text/javascript">
var LISTMONTHS='';
var LISTYEARS='';
</script>
<c:forEach var="MonthList" items="${resValue.lLstMonths}" >
	<script> LISTMONTHS += '<option value="${MonthList.id}"> ${MonthList.desc}</option>';</script>
</c:forEach>
<c:forEach var="YearList" items="${resValue.lLstYears}" >
	<script> LISTYEARS += '<option value="${YearList.desc}"> ${YearList.desc}</option>';</script>
</c:forEach>

<hdiits:form name="SixPayArrear" id="SixPayArrear" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" name="txtPnsnrCode" id="txtPnsnrCode" value="${PnsnrCode}"></input>
<input type="hidden" name="txtCaseStatus" id="txtCaseStatus" value=""></input>
<input type="hidden" name="listOfPnsnrCode" id="listOfPnsnrCode" value="${ListOfPnsnrCodes}"></input>
<input type="hidden" name="totalNoOfPnsnrCode" id="totalNoOfPnsnrCode" value="${TotalNoOfPnsnrCode}"></input>

<input type="hidden" name="listOfMonthDesc" id="listOfMonthDesc" value="${ListOfMonthDesc}"></input>
<input type="hidden" name="listOfMonthId" id="listOfMonthId" value="${ListOfMonthId}"></input>
<input type="hidden" name="totalNoOfMonths" id="totalNoOfMonths" value="${TotalNoOfMonths}"></input>

<input type="hidden" name="listOfYearDesc" id="listOfYearDesc" value="${ListOfYearDesc}"></input>
<input type="hidden" name="totalNoOfYears" id="totalNoOfYears" value="${TotalNoOfYears}"></input>
<input type="hidden" name="hidTotAmt" id="hidTotAmt"></input>
<input type="hidden" name="hdnIsDtlsExist" id="hdnIsDtlsExist" value="N"/>
<c:choose>
<c:when test="${Revision == 'Y'}">
	<table id="ppoDtls" align="Center">
	<tr>
		<td>
			<fmt:message key="SIXPAYARR.TOTAMT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="text" name="txtTotalAmt" id="txtTotalAmt" value="" size="15" style="text-align: right" onkeypress="digitFormat(this)" onblur="getInstallmentFromTotalAmnt();"/>
		</td>
		<td>
		
		</td>
		<td>
		
		</td>
	</tr>
	</table>
	<table id="arrearDtls" border="1" width="75%" align="center">
	<tr style="width: 100%" class="datatableheader">
		<td width="20%" class="datatableheader"><fmt:message
			key="SIXPAYARR.INSTALLNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="50%" class="datatableheader"><fmt:message
			key="SIXPAYARR.PAYIN" bundle="${pensionLabels}"></fmt:message></td>
		<td width="%" class="datatableheader"><fmt:message
			key="SIXPAYARR.INSTALLAMT" bundle="${pensionLabels}"></fmt:message></td>
		<td width="%" class="datatableheader"><fmt:message
			key="CMN.REMARKS" bundle="${pensionLabels}"></fmt:message></td>
		
	</tr>
	<c:forEach  begin='1' end='5' varStatus="Counter">
	<tr>
		<td class="tds" align="center">${Counter.index}</td>
		<td class="tds" align="center">
		<select name="cmbPayinMonth" id="cmbPayinMonth${Counter.index}" disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="MonthList" items="${resValue.lLstMonths}">
	         <option value="${MonthList.id}">
					<c:out value="${MonthList.desc}"></c:out>									
				</option>
			</c:forEach>
		</select>
		<select name="cmbPayinYear" id="cmbPayinYear${Counter.index}"  disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="YearList" items="${resValue.lLstYears}">
	         <option value="${YearList.desc}">
					<c:out value="${YearList.desc}"></c:out>									
				</option>
			</c:forEach>
		</select>
		</td>
		<td class="tds" align="center">
			<input type="text" name="txtInstallmentAmt" id="txtInstallmentAmt${Counter.index}" onblur="setValidAmountFormat(this)" onkeypress="amountFormat(this);"  style="text-align: right" size="15" value="" readonly="readonly" >
		</td>
		<td class="tds" align="center">
			<input type="text" name="txtRemarks" id="txtRemarks${Counter.index}"  size="30" >
		</td>
	</tr>
		
	
	</c:forEach>
</table>
<div align="center">
<hdiits:button name="btnSave" id="btnSave" type="button"  classcss="bigbutton" captionid="SIXPAYARR.SAVE" bundle="${pensionLabels}" onclick="saveRevisedSixPayArrears();"/>
<hdiits:button name="btnClose" id="btnClose" type="button"  classcss="bigbutton" captionid="SIXPAYARR.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</div>
<script>
for(var i=0;i<5;i++)
{
	document.getElementById("cmbPayinMonth"+(i+1)).options[0].value = window.opener.document.getElementById("cmbPayinMonth"+(i+1)).options[0].value;
	document.getElementById("cmbPayinMonth"+(i+1)).options[0].text = window.opener.document.getElementById("cmbPayinMonth"+(i+1)).options[0].text;
	
	document.getElementById("cmbPayinYear"+(i+1)).options[0].value = window.opener.document.getElementById("cmbPayinYear"+(i+1)).options[0].value;
	document.getElementById("cmbPayinYear"+(i+1)).options[0].text = window.opener.document.getElementById("cmbPayinYear"+(i+1)).options[0].text;
	
	document.getElementById("txtInstallmentAmt"+(i+1)).value = window.opener.document.getElementById("txtInstallmentAmt"+(i+1)).value;
	document.getElementById("cmbPayinYear"+(i+1)).style.width = "100px";
	document.getElementById("cmbPayinMonth"+(i+1)).style.width = "100px";
	if(window.opener.document.getElementById("cmbPayinYear"+(i+1)).disabled == true)
	{
		document.getElementById("cmbPayinYear"+(i+1)).disabled = true;
		document.getElementById("cmbPayinMonth"+(i+1)).disabled = true;
	}

} 
document.getElementById("hidTotAmt").value = window.opener.document.getElementById("txtTotAmt").value;
</script>
</c:when>
<c:otherwise>
<fieldset style="width:100%"   class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="SIXPAYARR.ARREARDTLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
<div style="width:100%;overflow:auto;height:100%;" >

<table id="ppoDtls" width="60%">
	<tr>
		<td>
			<fmt:message key="SIXPAYARR.PPONO" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="text" name="txtPpoNo" id="txtPpoNo" value=""  style="display: ;text-transform: uppercase;" onblur="getPPODetails();" size="15" />
		</td>
		<td>
			<fmt:message key="SIXPAYARR.PPONAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="text" name="txtPpoName" id="txtPpoName" readonly="readonly" value="" size="50" />
		</td>
	</tr>
	<tr>
		<td>
			<fmt:message key="SIXPAYARR.TOTAMT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="text" name="txtTotAmt" id="txtTotAmt" value="" size="15" style="text-align: right"  onkeypress="digitFormat(this)" onblur="getInstallmentFromTotAmnt();"/>
		</td>
		<td>
			<fmt:message key="SIXPAYARR.FMLYPNSRNAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
			<input type="text" name="txtFmlyPnsrName" id="txtFmlyPnsrName" readonly="readonly" value="" size="50" />
		</td>
	</tr>
</table>

<table id="arrearDtls" border="1" width="55%" align="center">
	<tr style="width: 100%" class="datatableheader">
		<td width="20%" class="datatableheader"><fmt:message
			key="SIXPAYARR.INSTALLNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="50%" class="datatableheader"><fmt:message
			key="SIXPAYARR.PAYIN" bundle="${pensionLabels}"></fmt:message></td>
		<td width="%" class="datatableheader"><fmt:message
			key="SIXPAYARR.INSTALLAMT" bundle="${pensionLabels}"></fmt:message></td>
		
	</tr>
	<c:forEach  begin='1' end='5' varStatus="Counter">
	<tr>
		<td class="tds" align="center">${Counter.index}</td>
		<td class="tds" align="center">
		<select name="cmbPayinMonth" id="cmbPayinMonth${Counter.index}"  disabled="disabled" >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="MonthList" items="${resValue.lLstMonths}">
	         <option value="${MonthList.id}">
					<c:out value="${MonthList.desc}"></c:out>									
				</option>
			</c:forEach>
		</select>
		<select name="cmbPayinYear" id="cmbPayinYear${Counter.index}"  disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="YearList" items="${resValue.lLstYears}">
	         <option value="${YearList.desc}">
					<c:out value="${YearList.desc}"></c:out>									
				</option>
			</c:forEach>
		</select>
		</td>
		<td class="tds" align="center">
			<input type="text" name="txtInstallmentAmt" id="txtInstallmentAmt${Counter.index}" onblur="setValidAmountFormat(this)" onkeypress="amountFormat(this);"  style="text-align: right" size="15" value="" readonly="readonly" ></td>
	</tr>
	</c:forEach>
</table>
</div>
<br/>
<div style="text-align: center;">
<hdiits:button name="btnRevision" id="btnRevision" type="button" classcss="bigbutton" captionid="SIXPAYARR.REVISION" bundle="${pensionLabels}" onclick="revisionOfPPOCase();"/>
<hdiits:button name="btnHistory" id="btnHistory" type="button" classcss="bigbutton" captionid="SIXPAYARR.HISTORY" bundle="${pensionLabels}" onclick="revisedHistory();"/>
</div>
</fieldset>
<br/>
<fieldset class="tabstyle" style="width: 100%"><legend>
<b> <fmt:message key="SIXPAYARR.ARREARCASHPAYMENT" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<hdiits:button name="btnAdd" id="btnAdd" type="button"  captionid="SIXPAYARR.ADDROW" bundle="${pensionLabels}" onclick="addCashArrearPayment()"/>
	<table id="tblCashPaymentDtls" border="1">
	<tr style="width: 100%" class="datatableheader">
		<td width="30%" class="datatableheader"><fmt:message key="SIXPAYARR.PAYIN" bundle="${pensionLabels}"></fmt:message></td>
		<td width="40%" class="datatableheader"><fmt:message key="SIXPAYARR.AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
		<td width="25%" class="datatableheader"><fmt:message key="SIXPAYARR.PAID" bundle="${pensionLabels}"></fmt:message></td>	
		<td width="5%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	</table>
	<input type="hidden" id="hidCashArrearCount" value="0" />	
</fieldset>
<br/>
<div align="center">
<hdiits:button name="btnSave" id="btnSave" type="button"  classcss="bigbutton" captionid="SIXPAYARR.SAVE" bundle="${pensionLabels}" onclick="saveSixPayArrears();"/>
<hdiits:button name="btnClose" id="btnClose" type="button"  classcss="bigbutton" captionid="SIXPAYARR.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</div>
</c:otherwise>
</c:choose>



</hdiits:form>


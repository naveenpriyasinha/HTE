<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseAuthorityDtls.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstTrnPnsnprocAuthorityDtls" value="${resValue.lLstTrnPnsnprocAuthorityDtls}"></c:set>
<c:set var="lObjTrnPnsnprocAgDtlsVO" value="${resValue.lObjPnsnprocAgDtlsVO}"></c:set>
<script type="text/javascript">
ORDERTYPE = '</option><option value="PPO"> PPO </option><option value="CPO"> CPO </option><option value="GPO"> GPO </option>';
</script>
<input type="hidden" name="hidAuthoGridSize" id="hidAuthoGridSize" value="0" />
<fieldset style="width: 100%" class="tabstyle"><legend id="headingMsg"><b> <fmt:message key="PPROC.ATHODTLS" bundle="${pensionLabels}"></fmt:message></b></legend>
<br>
<table width="100%">
	<tr>	
		<td>
			AG Master Salutation
		</td>
		<td>
			<input type="text" id="txtAgSalutation" name="txtAgSalutation" value="${lObjTrnPnsnprocAgDtlsVO.salutation}" />
		</td>	
		<td>
			AG Master Category/Series
		</td>
		<td>
			<input type="text" id="txtAgCategory" name="txtAgCategory" value="${lObjTrnPnsnprocAgDtlsVO.category}" />
		</td>
	</tr>	
	<tr>
		<td>
			AG Master Religion
		</td>
		<td>
			<input type="text" id="txtAgReligion" name="txtAgReligion" value="${lObjTrnPnsnprocAgDtlsVO.religion}" />
		</td>
		<td>
			AG Master Group Class
		</td>
		<td>
			<input type="text" id="txtAgGroup" name="txtAgGroup" value="${lObjTrnPnsnprocAgDtlsVO.groupClass}" />
		</td>
	</tr>	
</table>
<br>
<hdiits:button	name="authoDtlsAddRow" type="button" captionid="PPROC.ADDROW"	bundle="${pensionLabels}" onclick="advAthoDtlsAddRow()" /> 
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 200px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblAuthoTypeAdvDtls" align="left" width="95%" cellspacing="0"
	border="1">

	<tr class="datatableheader" style="width: 100%">
		<td width="10%" class="HLabel" >Order Type<label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel" >Order No.<label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel">Order Date<label
			class="mandatoryindicator">*</label></td>
		<td width="15%" class="HLabel" >Pensioner Name<label
			class="mandatoryindicator">*</label></td>
		<td width="7%" class="HLabel" >Basic Amount<label
			class="mandatoryindicator">*</label></td>
		<td width="7%" class="HLabel" >Enhanced Family Pension Amount<label
			class="mandatoryindicator">*</label></td>
		<td width="7%" class="HLabel" >Family Pension Amount<label
			class="mandatoryindicator">*</label></td>					
		<td width="1%" class="HLabel"><fmt:message 
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	
		<c:choose>
		<c:when test="${lLstTrnPnsnprocAuthorityDtls !=null}">

			<c:forEach var="authoDtlsVO" items="${lLstTrnPnsnprocAuthorityDtls}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
						<input type="hidden" name="hdnAuthorityId" id="hdnAuthorityId${Counter.index}" value="${authoDtlsVO.authorityDtlsId}">					
						<select name="cmbTypeOfOrder" id="cmbTypeOfOrder${Counter.index}">					
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							<c:if test="${authoDtlsVO.flag == 'PPO'}">
								<option value="PPO" selected="selected"> PPO </option>
								<option value="CPO"> CPO </option>
								<option value="GPO"> GPO </option>
							</c:if>
							<c:if test="${authoDtlsVO.flag == 'CPO'}">
								<option value="PPO"> PPO </option>
								<option value="CPO" selected="selected"> CPO </option>
								<option value="GPO"> GPO </option>
							</c:if>
							<c:if test="${authoDtlsVO.flag == 'GPO'}">
								<option value="PPO"> PPO </option>
								<option value="CPO"> CPO </option>
								<option value="GPO" selected="selected"> GPO </option>
							</c:if>				
						</select>
					</td>
					<td class="tds" align="center">
						<input type="text" name="txtAuthoOrderNo" maxlength="100" value="${authoDtlsVO.orderNo}" id="txtAuthoOrderNo${Counter.index}" onblur="onBlur(this);" />
					</td>
					<td class="tds" align="center">					
						<input type="text" name="txtAuthoOrderDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${authoDtlsVO.orderDate}"/>"  id="txtAuthoOrderDate${Counter.index}" onblur="onBlur(this);chkValidDate(this);"  onkeypress="digitFormat(this);dateFormat(this);"   maxlength="10"  size="10" value=""/>
						<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtAuthoOrderDate${Counter.index}",375,570)'style="cursor: pointer;" />
					</td>
					<td class="tds" align="center">
						<input type="text" name="txtAuthoPnsnrName" value="${authoDtlsVO.pnsnrName}" maxlength="100" id="txtAuthoPnsnrName${Counter.index}" onblur="onBlur(this);" />
					</td>
					<td class="tds" align="center">
						<input type="text" name="txtAuthoBasicAmt" value="${authoDtlsVO.basicAmt}" maxlength="7" id="txtAuthoBasicAmt${Counter.index}" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);"    style="width:90px;text-align: right" />
					</td>
					<td class="tds" align="center">
						<input type="text" name="txtAuthoEFP" value="${authoDtlsVO.efp}" maxlength="7" id="txtAuthoEFP${Counter.index}" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);"    style="width:90px;text-align: right" />
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtAuthoFP" value="${authoDtlsVO.fp}" maxlength="7" id="txtAuthoFP${Counter.index}" onkeypress="numberFormat(event);amountFormat(this,event);" onblur="onBlur(this);"    style="width:90px;text-align: right" />
					</td>
					<td class="tds" align="center">					
						<img name="Image" id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"	onclick="RemoveTableRow(this,'tblAuthoTypeAdvDtls')" />
					</td>
				</tr>
				<script>
						document.getElementById("hidAuthoGridSize").value=Number('${Counter.index}') + 1;
					</script>
			</c:forEach>
		</c:when>
	</c:choose>
</table>
</div>
</fieldset>
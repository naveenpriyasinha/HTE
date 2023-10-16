<%try{%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>

<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionproc.PensionCaseConstants" var="pensionConstants" scope="request" />
<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseRecoveryDtls.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lObjTrnPnsnProcRecoveryVO" value="${resValue.lObjTrnPnsnProcRecoveryVO}"></c:set>
<c:set var="lLstTrnPnsnProcAdvnceBal" value="${resValue.lLstTrnPnsnProcAdvnceBal}"></c:set>
<c:set var="lObjTrnPnsnProcAssesdDuesVO" value="${resValue.lObjTrnPnsnProcAssesdDuesVO}"></c:set>
<c:set var="lObjTrnPnsnProcAdvnceBalVO" value="${resValue.lObjTrnPnsnProcAdvnceBalVO}"></c:set>
<c:set var="lLstTrnPnsnProcAssesdDues" value="${resValue.lLstTrnPnsnProcAssesdDues}"></c:set>

<script type="text/javascript">
ADVNCETYPELIST='';
ASSESDTYPELSIT='';
OVERPAYALLOW ='<fmt:message key="PPROC.OVERPAYANDALLOW" bundle="${pensionAlerts}"></fmt:message>';
TYPEOFADVANCE ='<fmt:message key="PPROC.TYPEOFADVANCE" bundle="${pensionAlerts}"></fmt:message>';
ADVANCEAMT ='<fmt:message key="PPROC.ADVANCEAMT" bundle="${pensionAlerts}"></fmt:message>';
ARREAROFLICFEE ='<fmt:message key="PPROC.ARREAROFLICFEE" bundle="${pensionAlerts}"></fmt:message>';
AMTOFLICFEE='<fmt:message key="PPROC.AMTOFLICFEE" bundle="${pensionAlerts}"></fmt:message>';
NATURE ='<fmt:message key="PPROC.NATURE" bundle="${pensionAlerts}"></fmt:message>';
NATUREAMT ='<fmt:message key="PPROC.NATUREAMT" bundle="${pensionAlerts}"></fmt:message>';
AMTGRTY ='<fmt:message key="PPROC.AMTGRTY" bundle="${pensionAlerts}"></fmt:message>';
DTOFMEDCERT ='<fmt:message key="PPROC.DTOFMEDCERT" bundle="${pensionAlerts}"></fmt:message>';
DTOFLODGFIR ='<fmt:message key="PPROC.DTOFLODGFIR" bundle="${pensionAlerts}"></fmt:message>';
NUMOFCERT ='<fmt:message key="PPROC.NUMOFCERT" bundle="${pensionAlerts}"></fmt:message>';
POLICESTNNAME ='<fmt:message key="PPROC.POLICESTNNAME" bundle="${pensionAlerts}"></fmt:message>';
DATEOFCERT ='<fmt:message key="PPROC.DATEOFCERT" bundle="${pensionAlerts}"></fmt:message>';
SCHEMECODE ='<fmt:message key="PPROC.SCHEMECODE" bundle="${pensionAlerts}"></fmt:message>';
</script>

	<script> 
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.GOVACC" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.GOVACC" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.GOVACC" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.HBABAL" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.HBABAL" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.HBABAL" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.HBAINT" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.HBAINT" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.HBAINT" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.CONVYBAL" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.CONVYBAL" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.CONVYBAL" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.CONVYINT" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.CONVYINT" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.CONVYINT" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.OVERPAYALLW" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.OVERPAYALLW" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.OVERPAYALLW" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.INCTAX" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.INCTAX" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.INCTAX" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.DUES134" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.DUES134" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.DUES134" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.PFREC" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.PFREC" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.PFREC" bundle="${pensionLabels}"/></option>';
		ADVNCETYPELIST += '<option value="<fmt:message key="PPROC.OTHER" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.OTHER" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.OTHER" bundle="${pensionLabels}"/></option>';
	
	</script>


<c:forEach var="AssesdTypeList" items="${resValue.lLstAssesdTypeList}" >
	<script> ASSESDTYPELSIT += '<option value="${AssesdTypeList.lookupId}"> ${AssesdTypeList.lookupName} </option>';</script>
</c:forEach>

<table width="95%" align="center">

	<tr>
		<td width="30%" align="left"><U><fmt:message key="PPROC.ITEMS"
			bundle="${pensionLabels}"></fmt:message></U></td>
		<td width="30%" align="left"><U><fmt:message
			key="PPROC.AMTINRS" bundle="${pensionLabels}"></fmt:message></U></td>
	</tr>
	<tr>
		<td width="30%" align="left"><fmt:message key="PPROC.BALOFADVANCE"
			bundle="${pensionLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:choose>
		<c:when test="${lObjTrnPnsnProcRecoveryVO.advanceFlag == 'Y'}">
			<input type="radio" id="radioBalOfAdvY" name="radioBalOfAdv" maxlength ="1" value="Y" onclick="addAdvanceDtl(this)" checked="checked" /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioBalOfAdvN" name="radioBalOfAdv" maxlength ="1" value="N" onclick="addAdvanceDtl(this)" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:otherwise>
			<input type="radio" id="radioBalOfAdvY" name="radioBalOfAdv" value="Y" maxlength ="1" onclick="addAdvanceDtl(this)" /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioBalOfAdvN" name="radioBalOfAdv" value="N" maxlength ="1" onclick="addAdvanceDtl(this)" checked="checked" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:otherwise>
		
		</c:choose>
		</td>	
		<td>
		<input type="text"
			name='txtTotalAdvanceBalAmt' maxlength ="7" id="txtTotalAdvanceBalAmt"  value="${lObjTrnPnsnProcRecoveryVO.totalAdvanceAmount}" style="text-align: right" readonly="readonly"/>
		</td>	
	</tr>

</table>
<br/><br/>

<div style="padding-left: 150px">
<hdiits:button name="btnAdvDtlsAddRow" type="button" captionid="PPROC.ADDROW"
	bundle="${pensionLabels}" onclick="advDtlsAddRow();" readonly="true"/>
 <input
	type="hidden" name="hidAdvDtlGridSize" id="hidAdvDtlGridSize" value="0" />
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 95%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">

<table id="tblTypeAdvDtls" align="left" width="100%" border="1">
	<tr class="datatableheader" style="width: 98px">
		<td width="20%" class="datatableheader" align="left"><fmt:message
			key="PPROC.TYPEOFADVANCE" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="datatableheader" align="left"><fmt:message
			key="PPROC.AMOUNT" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="15%" class="datatableheader"><fmt:message key="PPROC.SCHEMECODE" bundle="${pensionLabels}"></fmt:message></td>	
		<td width="5%" class="datatableheader" align="left"><fmt:message
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<c:if test="${lObjTrnPnsnProcRecoveryVO.advanceFlag == 'Y'}">
	
				<script type="text/javascript">
				
					document.getElementById("btnAdvDtlsAddRow").disabled=false;
				
				</script>
	</c:if>
	<c:choose>
					<c:when test="${resValue.lLstTrnPnsnProcAdvnceBal !=null}">
						<c:forEach var="AdvnceBalVO" items="${resValue.lLstTrnPnsnProcAdvnceBal}" varStatus="Counter">
						<tr>
						<td class="tds" align="center">
								<select name="cmbTypeOfAdv" id="cmbTypeOfAdv${Counter.index}"><option value="-1">--Select--</option>
									<option value="<fmt:message key="PPROC.GOVACC" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.GOVACC" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.GOVACC" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.HBABAL" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.HBABAL" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.HBABAL" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.HBAINT" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.HBAINT" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.HBAINT" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.CONVYBAL" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.CONVYBAL" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.CONVYBAL" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.CONVYINT" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.CONVYINT" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.CONVYINT" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.OVERPAYALLW" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.OVERPAYALLW" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.OVERPAYALLW" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.INCTAX" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.INCTAX" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.INCTAX" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.DUES134" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.DUES134" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.DUES134" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.PFREC" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.PFREC" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.PFREC" bundle="${pensionLabels}"/></option>
									<option value="<fmt:message key="PPROC.OTHER" bundle="${pensionConstants}"/>" title="<fmt:message key="PPROC.OTHER" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.OTHER" bundle="${pensionLabels}"/></option>
									<option value="2" title="2">2</option>
								</select>
								<script>
								var lStrAdvType = "${AdvnceBalVO.advanceBalTypeId}";
								var lArrOpts = document.getElementById("cmbTypeOfAdv${Counter.index}").options;
								var advTypeFound = "N";	//Flag to set select as selected if no match found.
								for(var cnt = 0; cnt < lArrOpts.length ; cnt++)
								{
									if(document.getElementById("cmbTypeOfAdv${Counter.index}").options[cnt].value == lStrAdvType)
									{
										document.getElementById("cmbTypeOfAdv${Counter.index}").options[cnt].selected = "selected";
										advTypeFound = "Y";
									}
								}
								if(advTypeFound == "N")
								{
									document.getElementById("cmbTypeOfAdv${Counter.index}").options[0].selected = "selected";
								}
								</script>
							</td>
							<td class="tds" align="center">
								<input type="text" name="txtAdvAmount" maxlength ="7" id="txtAdvAmount${Counter.index}"  onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this)" style="width:90px;text-align: right" value="${AdvnceBalVO.advanceAmnt}">		
							</td>
							<td class="tds" align="center">
								<input type="text" name="txtAdvSchemeCode" id="txtAdvSchemeCode${Counter.index}" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" value="${AdvnceBalVO.advanceSchemeCode}" size="12"/>
								<a href="#" id="txtAdvSchemeCode${Counter.index}" onclick="showSchemeCodePopup(this);"><img src="images/search.gif" /></a>
							</td>
							<td class="tds" align="center">
								<img name="Image"  id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblTypeAdvDtls')" />
							</td>
						</tr>
					<script>
						document.getElementById("hidAdvDtlGridSize").value=Number('${Counter.index}') + 1;
					</script>
					</c:forEach>
					</c:when>
					
				</c:choose>
	
</table>
</div>
</div>
<br/><br/>
<!--<table width="95%" align="center">
	<tr>
		<td width="30%" align="left"><fmt:message
			key="PPROC.OVERPAYANDALLOW" bundle="${pensionLabels}"></fmt:message></td>
		
		<td width="30%" align="left"><input type="text" maxlength ="7"
			name='txtOverPayAllow' id="txtOverPayAllow"  value="${lObjTrnPnsnProcRecoveryVO.overPaymentAmnt}" style="text-align: right"
			onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this)" /></td>
	</tr>
	<tr>
		<td width="30%" align="left"><fmt:message
			key="PPROC.ARREAROFLICFEE" bundle="${pensionLabels}"></fmt:message></td>
		
		<td width="30%" align="left"><input type="text"  maxlength ="7"
			name='txtArrearAmnt' id="txtArrearAmnt" value="${lObjTrnPnsnProcRecoveryVO.arrearsAmnt}" style="text-align: right"
			onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this)" /></td>
	</tr>
	<tr>
		<td width="30%" align="left"><fmt:message key="PPROC.AMTOFLICFEE"
			bundle="${pensionLabels}"></fmt:message></td>
		
		<td width="30%" align="left"><input type="text" maxlength ="7"
			name='txtGovtAcmdtnRetAmnt' id="txtGovtAcmdtnRetAmnt" value="${lObjTrnPnsnProcRecoveryVO.govtAcmdtnRetAmnt}" style="text-align: right"
			onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this)" /></td>
	</tr>

	<tr>
		<td width="30%" align="left"><fmt:message
			key="PPROC.OTHERASSESSDUE" bundle="${pensionLabels}"></fmt:message>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<!--<c:choose>
		<c:when test="${lObjTrnPnsnProcRecoveryVO.assessedDuesdtlFlag == 'Y'}">
			<input type="radio" id="radioOtherAssessDueY" name="radioOtherAssessDue" value="Y" onclick="addAssessedDues(this)" checked="checked"  maxlength ="3" /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioOtherAssessDueN" name="radioOtherAssessDue" value="N" onclick="addAssessedDues(this)" maxlength ="3" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:otherwise>
			<input type="radio" id="radioOtherAssessDueY" name="radioOtherAssessDue" value="Y" onclick="addAssessedDues(this)" maxlength ="3" /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioOtherAssessDueN" name="radioOtherAssessDue" value="N" onclick="addAssessedDues(this)"  checked="checked" maxlength ="3" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:otherwise>
		</c:choose>
		</td>
		<td width="30%" align="left"><input type="text" maxlength ="7"
			name='txtTotalAssessDues' id="txtTotalAssessDues"  value="${lObjTrnPnsnProcRecoveryVO.totalAssessedDues}"
			style="text-align: right" onKeyPress="numberFormat(event)" readonly="readonly"/></td>
	</tr>
</table>

<br/><br/>

<div style="padding-left: 150px">
<hdiits:button	name="btnAssessedDuesAddRow" type="button" captionid="PPROC.ADDROW"
	bundle="${pensionLabels}" onclick="natureDtlsAddRow();" readonly="true"/> 
<input	type="hidden" name="hidNatureGridSize" id="hidNatureGridSize" value="0" />
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 30%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblNatureDtls" align="left" width="90%" cellspacing="0"
	border="1">
	<tr class="datatableheader" style="width: 50px">
		<td width="20%" class="HLabel" align="left"><fmt:message
			key="PPROC.NATURE" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel" align="left"><fmt:message
			key="PPROC.AMOUNT" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="5%" class="HLabel" align="left"><fmt:message
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<c:if test="${lObjTrnPnsnProcRecoveryVO.assessedDuesdtlFlag == 'Y'}">
	
				<script type="text/javascript">
				
				   document.getElementById("btnAssessedDuesAddRow").disabled=false;
				
				</script>
	</c:if>
	
	<c:choose>
	
					<c:when test="${resValue.lLstTrnPnsnProcAssesdDues !=null}">
					<c:forEach var="AssesdDuesVO" items="${resValue.lLstTrnPnsnProcAssesdDues}" varStatus="Counter">
						<tr>
							<td class="tds" align="center">
									<input type="text" name="cmbNature" id="cmbNature${Counter.index}" onkeypress="amountFormat(this,event);"  style="width:90px;text-align: left" value="${AssesdDuesVO.assesdDuesDtlTypeId}">		
							</td>
							
							<td class="tds" align="center">
									<input type="text" name="txtNatrAmount" maxlength ="7" id="txtNatrAmount${Counter.index}" onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this)"  style="width:90px;text-align: right" value="${AssesdDuesVO.assesdDuesDtlAmnt}">		
							</td>
							<td class="tds" align="center">
									<img name="Image"  id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblNatureDtls')" /> 
							</td>
						</tr>
					<script>
						document.getElementById("hidNatureGridSize").value=Number('${Counter.index}') + 1;
					</script>
					</c:forEach>
					</c:when>
				</c:choose>
	
	
	
</table>
</div>
</div>
--><table width="95%" align="center">
	<!--<tr>
		<td width="30%" align="left"><fmt:message key="PPROC.AMTGRTY"
			bundle="${pensionLabels}"></fmt:message></td>
		
		<td width="30%" align="left"><input type="text" name='txtGratuityAmnt' maxlength ="7"
			id="txtGratuityAmnt" style="text-align: right" value="${lObjTrnPnsnProcRecoveryVO.gratuityAmnt}"
			onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this)" /></td>
	</tr>
	--><!-- <tr>
		<td width="30%" align="left"><fmt:message key="PPROC.DEPTENQRY"
			bundle="${pensionLabels}"></fmt:message></td>
		
		<td width="30%" align="left"></td>
	</tr>
	<tr>
		<td width="30%" align="left" style="padding-left: 60px"><fmt:message
			key="PPROC.PROPOSED" bundle="${pensionLabels}"></fmt:message>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:choose>
			<c:when test="${lObjTrnPnsnProcRecoveryVO.enquiryProposedFlag == 'Y'}">
				<input type="radio" id="radioProposed" name="radioProposed" value="Y" onclick=""  checked="checked"/> 
				<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
				<input type="radio" id="radioProposed" name="radioProposed" value="N" onclick="" /> 
				<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			</c:when>
			<c:otherwise>
				<input type="radio" id="radioProposed" name="radioProposed" value="Y" onclick="" /> 
				<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
				<input type="radio" id="radioProposed" name="radioProposed" value="N" onclick="" checked="checked" /> 
				<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			</c:otherwise>
		</c:choose>
			
		</td>
		<td width="30%" align="left"></td>
	</tr>
	<tr>
		<td width="30%" align="left" style="padding-left: 60px"><fmt:message
			key="PPROC.PENDING" bundle="${pensionLabels}"></fmt:message>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:choose>
			<c:when test="${lObjTrnPnsnProcRecoveryVO.enquiryPendingFlag == 'Y'}">
				<input type="radio" id="radioPending" name="radioPending" value="Y" onclick=""  checked="checked" /> 
				<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
				<input type="radio" id="radioPending" name="radioPending" value="N" onclick="" /> 
				<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			</c:when>
			<c:otherwise>
				<input type="radio" id="radioPending" name="radioPending" value="Y" onclick="" /> 
				<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
				<input type="radio" id="radioPending" name="radioPending" value="N" onclick=""  checked="checked" /> 
				<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			</c:otherwise>
		</c:choose>
			
		</td>
		<td width="30%" align="left"></td>
	</tr>-->

	
</table>

<%}catch(Exception e){
e.printStackTrace();
}%>
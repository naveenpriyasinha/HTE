<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	
<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/TransferPPO.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TreasuryList" value="${resValue.lLstTreasury}"></c:set>
<c:set var="lLstPnsnrCodes" value="${resValue.lLstPnsnrCodes}" />
<hdiits:form name="frmTransferToOtherTreasury" id="frmTransferToOtherTreasury" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" name="txtLocCode" id="txtLocCode" value=""></input>
<input type="hidden" name="txtPnsnrCode" id="txtPnsnrCode" value=""></input>
<input type="hidden" name="txtCaseStatus" id="txtCaseStatus" value=""></input>
<input type="hidden" name="hdnTransferDtlsId" id="hdnTransferDtlsId" value=""></input>
<input type="hidden" name="hidPpoNo" id="hidPpoNo" value=""></input>
<input type="hidden" name="hidListOfPnsnrCode"  id="hidListOfPnsnrCode" value="${lLstPnsnrCodes}" ></input>
<input type="hidden" name="hidCurrAGCircleCode" id="hidCurrAGCircleCode" value=""></input>
<input type="hidden" name="hidAgFlag" id="hidAgFlag"></input>
<input type="hidden" name="hidAgNewCircleCode" id="hidAgNewCircleCode"></input>
<table width="60%" align="center">
<tr>
<td>
<fieldset class="tabstyle"><legend><fmt:message key="PPMT.TRANSFERTOOTHERTREASURY" bundle="${pensionLabels}"></fmt:message> </legend>
<table width="100%" align="center" cellpadding="2">
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.REQID" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    <c:choose>
	    <c:when test="${resValue.RoleId == '365451'}">
	    	<input type="text" id="txtRequestId" name="txtRequestId" onblur="getTransferDtlsFromReqId();"/>
	    </c:when>
	    <c:otherwise>
	    	<input type="text" id="txtRequestId" name="txtRequestId" readOnly="readOnly"/>
	    </c:otherwise>
	    </c:choose>
	    </td>
	    <td width="5%">
	    <td width="15%"><fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message>	</td>
	    <c:choose>
	    <c:when test="${resValue.RoleId == '365451'}">
	    	<td width="30%"><input type="text" id="txtPpoNo" name="txtPpoNo" readOnly="readOnly" /></td>
	    </c:when>
	    <c:otherwise>
	    <td width="30%"><input type="text"  size="20%"  name="txtPpoNo" id="txtPpoNo"  onblur="getNameAndTrsryFromPPONo();" /><label class="mandatoryindicator">*</label></td>
	    </c:otherwise>
	    </c:choose>
	        
	    </td>
	    </tr>
	    <tr>
		<td width="15%"><fmt:message key="PPMT.NAME" bundle="${pensionLabels}" ></fmt:message>	
		</td>
		<td width="30%"> <input type="text"  size="50%" name="txtName" id="txtName" style="text-align: left" onKeyPress=""  readonly="readonly" />
		</td>
		<td width="5%">
		<td width="15%"><fmt:message key="PPMT.OLDTREASURY" bundle="${pensionLabels}"></fmt:message>	
		</td>
		<td width="30%"> <input type="text"  size="20%" name="txtOldTreasury" id="txtOldTreasury" style="text-align: left" onKeyPress=""  readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td width="15%"></td>
		<c:choose>
	    <c:when test="${resValue.RoleId == '365451'}"> <!-- ATO -->
	    	<td width="30%">
				    <!-- <input type="radio" id="RadioButtonInAg" name="RadioButtonAg" value="WithinAg"  onclick="checkedRadioVal(this);" disabled="disabled"/>
				     <fmt:message key="PPMT.WITHINAG" bundle="${pensionLabels}"></fmt:message>&nbsp;
				     <input type="radio" id="RadioButtonOutAg" name="RadioButtonAg" value="OutsideAg" onclick="checkedRadioVal(this);" disabled="disabled"/>
				     <fmt:message key="PPMT.OUTSIDEAG" bundle="${pensionLabels}"></fmt:message>&nbsp;-->
				     <fmt:message key="PPMT.OTHERSTATE" bundle="${pensionLabels}"></fmt:message>
				     <input type="radio" id="RadioButtonStateYes" name="RadioButtonAg" value="OtherStateYes" onclick="checkedRadioVal(this);">Yes</input>
				     <input type="radio" id="RadioButtonStateNo" name="RadioButtonAg" value="OtherStateNo" onclick="checkedRadioVal(this);" checked="checked">No</input>
		</td>
	    </c:when>
	    <c:otherwise>
		<td width="30%">
				    <!--  <input type="radio" id="RadioButtonAg" name="RadioButtonAg" value="WithinAg"  checked="checked" onclick="checkedRadioVal(this);" disabled="disabled"/>
				     <fmt:message key="PPMT.WITHINAG" bundle="${pensionLabels}"></fmt:message>&nbsp;
				     <input type="radio" id="RadioButtonAg" name="RadioButtonAg" value="OutsideAg" onclick="checkedRadioVal(this);" disabled="disabled"/>
				     <fmt:message key="PPMT.OUTSIDEAG" bundle="${pensionLabels}"></fmt:message>&nbsp;
				     <input type="radio" id="RadioButtonState" name="RadioButtonAg" value="OtherState" onclick="checkedRadioVal(this);" />
				     <fmt:message key="PPMT.OTHERSTATE" bundle="${pensionLabels}"></fmt:message> -->
				     <fmt:message key="PPMT.OTHERSTATE" bundle="${pensionLabels}"></fmt:message>&nbsp&nbsp
				     <input type="radio" id="RadioButtonStateYes" name="RadioButtonAg" value="OtherStateYes" onclick="checkedRadioVal(this);">Yes</input>&nbsp
				     <input type="radio" id="RadioButtonStateNo" name="RadioButtonAg" value="OtherStateNo" onclick="checkedRadioVal(this);" checked="checked">No</input>
		</td>
		 </c:otherwise>
	    </c:choose>
		<td width="5%">
		</td>
		<td width="15%">
			<label id="lblOtherStateName" style="display:none;"><fmt:message key="PPMT.OTHERSTATENAME" bundle="${pensionLabels}"></fmt:message>&nbsp&nbsp</label>
		</td>
		<td width="30%">
			<input type="text"  size="15" name="txtOtherState" id="txtOtherState" style="text-align: left" style="display:none;" />
		</td>
	</tr>
	
	<tr>
	
		<td width="15%"><fmt:message key="PPMT.NEWTREASURY" bundle="${pensionLabels}"></fmt:message>	
		</td>
		<td width="30%">
		<c:choose>
	    <c:when test="${resValue.RoleId == '365451'}"> <!-- ATO -->
			<select name="cmbNewTreasury" id="cmbNewTreasury" style="width:100%" onfocus="onFocus(this)" onblur="onBlur(this);" onchange="showAGCircle(this);" disabled="disabled">
		</c:when>
		<c:otherwise>
			<select name="cmbNewTreasury" id="cmbNewTreasury" style="width:100%" onfocus="onFocus(this)" onblur="onBlur(this);" onchange="showAGCircle(this);" >
		</c:otherwise>
		</c:choose>
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 <c:forEach var="TreasuryList" items="${TreasuryList}">
						<option value="${TreasuryList.id}"><c:out
							value="${TreasuryList.desc}"></c:out></option>
				</c:forEach>
			</select>
		</td>
		<td width="5%"></td>
		<td width="15%">
			<label id="lblAGCircle"><fmt:message key="PPMT.AGCIRCLE" bundle="${pensionLabels}"></fmt:message>&nbsp&nbsp</label>
		</td>
		<td width="30%">
			<input type="text"  size="15" name="txtAGCircleName" id="txtAGCircleName" style="display:inline;" disabled="disabled" />
			<input type="hidden" name="txtAGCircleCode" id="txtAGCircleCode"  />
		</td>
		</tr>
</table>
</fieldset>
<table align="center">
<tr>
	<td align="center">
	<c:choose>
	    <c:when test="${resValue.RoleId == '365451'}"> <!-- ATO -->
			<hdiits:button type="button" captionid="PPMT.SUBMIT" bundle="${pensionLabels}" id="btnSubmit" name="btnSubmit" onclick="transferPPOCase(2);"/>
			<hdiits:button type="button" captionid="PPMT.RQSTREJECT" bundle="${pensionLabels}" id="btnReject" name="btnReject" onclick="transferPPOCase(4);"/>
		 </c:when>
	    <c:otherwise>
	    	<hdiits:button id="btnSave" name="btnSave" type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="transferPPOCase(1);"  />
	    </c:otherwise>
	</c:choose>
	</td>
	<td  align="center">
		<hdiits:button name="btnGenerateLatter" type="button" captionid="PPMT.GENERATELETT" bundle="${pensionLabels}" onclick="generateTransferLetter();" classcss="bigbutton"   />
	</td>
	<td  align="center">		
		<hdiits:button type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" id="btnClose" name="btnClose" onclick="winCls();" />
	</td>
</tr>
</table>

</td>
</tr>
</table>
</hdiits:form>
	
	
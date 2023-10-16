<%
try {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/pensionpay/OverPaymentRecovery.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="HeadCodeList" value="${resValue.lLstHeadCodes}" />
<c:set var="RoleName" value="${resValue.lStrRoleName}" />
<c:set var="lLstPnsnrCodes" value="${resValue.lLstPnsnrCodes}" />
<c:set var="lLstStatus" value="${resValue.lLstStatus}" />

<hdiits:form name="frmOverPmntRecovery" id="frmOverPmntRecovery"  validate="true" method="post">
<input type="hidden" name="hidPnsnrCode"  id="hidPnsnrCode"  ></input>
<input type="hidden" name="hidCommensionDate"  id="hidCommensionDate"  ></input>
<!-- <input type="hidden" name="hidListOfPnsnrCode"  id="hidListOfPnsnrCode" value="${lLstPnsnrCodes}" ></input>
<input type="hidden" name="hidListOfStatus"  id="hidListOfStatus" value="${lLstStatus}" ></input>-->
<fieldset style="width:100%"  class="tabstyle">

	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.OVERPAYMENTRCVRY" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<div>&nbsp;</div>
<table width="100%" align="center">
	<tr>
	    <td width="10%">
	       <fmt:message key="PPMT.REQID" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
		    <c:choose>
		    <c:when test="${RoleName == 'ATO'}">
		    	<input type="text" id="txtRequestId" name="txtRequestId" onblur="getRecoveryDtlsFromReqId();"/>
		    </c:when>
		    <c:otherwise>
		    	<input type="text" id="txtRequestId" name="txtRequestId" readOnly="readOnly"/>
		    </c:otherwise>
		    </c:choose>
	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	       <fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
		    <c:choose>
		    <c:when test="${RoleName == 'ATO'}">
		    	<input type="text" id="txtPpoNo" name="txtPpoNo" readOnly="readOnly" />
		    </c:when>
		    <c:otherwise>
		    	<input type="text" id="txtPpoNo" name="txtPpoNo" onblur="getPPONameFromPPONo();" />
		    </c:otherwise>
		    </c:choose>
	    </td>
	</tr>
	<c:if test="${RoleName == 'ATO'}">
		<tr>
		    <td width="10%">
		       <fmt:message key="PPMT.PAYORDERNO" bundle="${pensionLabels}"></fmt:message>
		    </td>
		    <td width="30%">
				<input type="text" id="txtPayOrderNo" name="txtPayOrderNo" maxlength="30" />
		    </td>
		    <td width="10%"></td>
		     <td width="10%">
		       <fmt:message key="PPMT.PAYORDERDATE" bundle="${pensionLabels}"></fmt:message>
		    </td>
		    <td width="30%">
		    <input type="text" id="txtPayOrderDate" name="txtPayOrderDate" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);" maxlength="10"/>
		    <img src='images/CalendarImages/ico-calendar.gif'
						        onClick='window_open(event,"txtPayOrderDate",375,570)'style="cursor: pointer;" />
						        
		    </td>
		</tr>
		<tr>
		    <td width="10%">
		       <fmt:message key="PPMT.CHALLANNO" bundle="${pensionLabels}"></fmt:message>
		    </td>
		    <td width="30%">
		 	  	<input type="text" id="txtChallanNo" name="txtChallanNo" maxlength="20"/>
		    </td>
		    <td width="10%"></td>
		     <td width="10%">
		       <fmt:message key="PPMT.CHALLANDATE" bundle="${pensionLabels}"></fmt:message>
		    </td>
		    <td width="30%">
		      <input type="text" id="txtChallanDate" name="txtChallanDate" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);" maxlength="10"/>
		      <img src='images/CalendarImages/ico-calendar.gif'
						        onClick='window_open(event,"txtChallanDate",375,570)'style="cursor: pointer;" />
		    </td>
		</tr>
	</c:if>
	<tr>	 
		 <td width="10%">
	       <fmt:message key="PPMT.PENIONERNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="30%">
		   <c:choose>
		    <c:when test="${RoleName == 'ATO'}">
		    	<input type="text" id="txtPensrName" name="txtPensrName"  readOnly="readOnly" />
		    </c:when>
		    <c:otherwise>
		    	<input type="text" id="txtPensrName" name="txtPensrName"/>
		    </c:otherwise>
		    </c:choose>
		</td>
		 <td width="10%"></td>
		 <td width="10%">
	       <fmt:message key="PPMT.DOE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="30%">
		  <c:choose>
		    <c:when test="${RoleName == 'ATO'}">
		    	<input type="text" id="txtDateOfExpry" name="txtDateOfExpry" readOnly="readOnly"  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"/>
		    </c:when>
	    	<c:otherwise>
		    	<input type="text" id="txtDateOfExpry" name="txtDateOfExpry" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);" maxlength="10"  />
			     <img src='images/CalendarImages/ico-calendar.gif'
						   onClick='window_open(event,"txtDateOfExpry",375,570)'style="cursor: pointer;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
				<hdiits:button name="btnCalculate" type="button" captionid="PPMT.CALCULATE" bundle="${pensionLabels}" onclick="getTotalRecoveryAmnt();"  />		      
	   		 </c:otherwise>
	    </c:choose>
		 <!-- chkValidDate(this); -->
		 </td>           
	</tr>
	<tr>	 
		 <td width="10%">
	       <fmt:message key="PPMT.TOTRECOVERYAMT" bundle="${pensionLabels}"></fmt:message>
	   	 </td>
		 <td width="30%">
		 	<c:choose>
		    <c:when test="${RoleName == 'ATO'}">
		    	<input type="text" id="txtTotalRecvAmnt" name="txtTotalRecvAmnt" readOnly="readOnly" />
		    </c:when>
	    	<c:otherwise>
	    		<input type="text" id="txtTotalRecvAmnt" name="txtTotalRecvAmnt"/>
	    	</c:otherwise>
	   		 </c:choose>
		</td>
		 <td width="10%"></td>
			 <td width="10%">
		       <fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message>
		    </td>
			 <td width="30%">
		  	<c:choose>
			    <c:when test="${RoleName == 'ATO'}">
			    	<input type="text" id="txtSchemeCode" name="txtSchemeCode" readOnly="readOnly" />
			    </c:when>
			    <c:otherwise>
			    	<input type="text" id="txtSchemeCode" name="txtSchemeCode" value="0071018201" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" />
			    	<a href="#"  onclick="showSchemeCodePopup('txtSchemeCode');"><img src="images/search.gif"/></a>
			    </c:otherwise>
	   		 </c:choose>
		 </td>         
	</tr>
	<tr>	 
		<c:if test="${RoleName != 'ATO'}">
		 <td width="10%" style="display:none;">
			<fmt:message key="PPMT.CURRHEADCODE" bundle="${pensionLabels}"></fmt:message>
		</td>
	    </c:if>
	    <c:if test="${RoleName != 'ATO'}">
		 <td width="30%" style="display:none;">
			<input type="text" id="txtCurrHeadCode" name="txtCurrHeadCode" readOnly="readOnly"/>	
		</td>
		</c:if>
		<c:if test="${RoleName != 'ATO'}"><td width="20%"></c:if>
		 <td width="10%" style="display:none;">
	       <fmt:message key="PPMT.NEWHEADCODE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td style="display:none;">
		 	<c:choose>
		    <c:when test="${RoleName == 'ATO'}">
		    	<input type="text" id="txtCurrHeadCode" name="txtCurrHeadCode" readOnly="readOnly"/>
		    </c:when>
		    <c:otherwise>
		    	<select name="cmbNewHeadCode" id="cmbNewHeadCode" style="width:60%;" value="">
				<c:forEach var="ListOfHeadCode" items="${HeadCodeList}">
	        		<option value="${ListOfHeadCode.id}">
						<c:out value="${ListOfHeadCode.desc}"></c:out>									
					</option>
				</c:forEach>
			</select>
		    </c:otherwise>
	    	</c:choose>
		</td>
		<c:if test="${RoleName == 'ATO'}">
			<td width="10%">
		     	 <fmt:message key="PPMT.PAYORDERAMNT" bundle="${pensionLabels}"></fmt:message>
		    </td>
			 <td width="30%">
			 	<input type="text" id="txtPayOrderAmnt" name="txtPayOrderAmnt" />
			 </td>
			  <td width="10%"></td>
			   <td width="30%"></td>
		 </c:if>
	</tr>
	<c:if test="${RoleName == 'ATO'}">
		<tr>
		    <td width="10%">
		       <fmt:message key="PPMT.AMNTRECOVERED" bundle="${pensionLabels}"></fmt:message>
		    </td>
		    <td width="30%">
				<input type="text" id="txtAmntRecovered" name="txtAmntRecovered"/>
		    </td>
		    <td width="10%"></td>
		     <td width="10%">
		    </td>
		    <td width="30%">
		    </td>
		</tr>
	</c:if>
</table>
<div>&nbsp;</div>	
</fieldset>
<div>&nbsp;</div>
<table width="100%">
	<tr>
		<td width="30%"></td>
		<td width="60%">
			<c:if test="${RoleName != 'ATO'}">
				<hdiits:button id="btnSave" name="btnSave" type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveOverPmntRecovery();"  />
			</c:if>
			<c:if test="${RoleName == 'ATO'}">
				<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="PPMT.APPROVE" bundle="${pensionLabels}" onclick="approveOverPmntRecovery('A');"  />
				<hdiits:button name="btnReject" id="btnReject" type="button" captionid="PPMT.REJECT" bundle="${pensionLabels}" onclick="approveOverPmntRecovery('R');"  />
			</c:if>
		<hdiits:button name="btnGenerateLatter" type="button" captionid="PPMT.GENERATELETT" bundle="${pensionLabels}" onclick="generateRecoveryLtr();" classcss="bigbutton"   />
		<hdiits:button name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"  />
		</td>			
		<td width="20%"></td>
	</tr>
</table>
</hdiits:form>
<%

} catch (Exception e) {
	e.printStackTrace();
}


%>
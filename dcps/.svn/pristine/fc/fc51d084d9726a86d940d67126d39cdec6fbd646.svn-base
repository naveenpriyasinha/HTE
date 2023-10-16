<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US" var="pensionLabels" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/pensionpay/TransferPPO.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TrnfrDtls_Id" value="${resValue.TrnfrDtlsId}"></c:set>
<c:set var="FlagVal" value="${resValue.FlagVal}"></c:set>
<c:set var="lObjTrnPensionTransferDtlsVO" value="${resValue.lObjTrnPensionTransferDtlsVO}"></c:set>



<hdiits:form name="remarksInfo" encType="multipart/form-data" id="remarksInfo"
	validate="true" method="post">
	<input type="hidden" name="trnsfrDtlId" id="trnsfrDtlId" value="${TrnfrDtls_Id}"></input>
	<input type="hidden" name="flagVal" id="flagVal" value="${FlagVal}"></input>
<table>
<tr align="center"> 
		   
		<td width="30%">
				<fmt:message key="PPMT.REMARKS" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td>
		<c:if test="${lObjTrnPensionTransferDtlsVO == null}">
		<textarea  id="txtRemarks" name="txtRemarks"  style="height: 40px;width:500px"></textarea>
		</c:if>
		<c:if test="${lObjTrnPensionTransferDtlsVO != null}">
		<textarea  id="txtRemarks" name="txtRemarks"  style="height: 40px;width:500px" readonly="readonly">${lObjTrnPensionTransferDtlsVO.remarks}</textarea>
		</c:if>
		</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		</table>

<div align="center">

		<hdiits:button name="btnSave" type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveRemarks();" />
		<hdiits:button name="btnCancel" type="button" captionid="PPMT.CANCEL" bundle="${pensionLabels}" onclick="closeRemarksWindow(1);" />

</div>

</hdiits:form>



  
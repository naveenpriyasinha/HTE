<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.pensionpay.PensionConstants" var="pensionBillConst" scope="request"/>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillprepAlerts" scope="request"/>
<fmt:setBundle basename="resources.onlinebillprep.CommonLabels" var="onlinebillprepLabels" scope="request"/>
<fmt:setBundle basename="resources.billproc.billproc" var="billprocLabels" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.OnlineBillConstants" var="OnlineBillConstants" scope="application"/>
<fmt:setBundle basename="resources.pensionpay.PensionAlerts" var="PensionAlerts" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionConstant" var="pensionConst" scope="request"/>

<script type="text/javascript">
	var CRTBILL_BILLBALANCE = "<fmt:message key='CRTBILL.BILLBALANCE' bundle='${onlinebillprepAlerts}' />";
	var CRTBILL_SAVEBILLRQST = "<fmt:message key='CRTBILL.SAVEBILLRQST' bundle='${onlinebillprepAlerts}' />";
	var CRTBILL_SIGNEDRQST = "<fmt:message key='CRTBILL.SIGNEDRQST' bundle='${onlinebillprepAlerts}' />";
	var PEN_NEGAMNT = "<fmt:message key='PEN.NEGAMNT' bundle='${PensionAlerts}' />";
	var PEN_TOKREQ = "<fmt:message key='PEN.TOKREQ' bundle='${PensionAlerts}' />";
	var VALID_TOK = "<fmt:message key='CMN.VALIDTOK' bundle='${PensionAlerts}' />";
	var BILL_NT_SVD = "<fmt:message key='CMN.BILLNTSVD' bundle='${PensionAlerts}' />";	
	var BILL_NT_GEN='<fmt:message key="CMN.BILLNOTGEN" bundle="${PensionAlerts}"></fmt:message>';
</script>
<script>
function printBillData()
{
	var pnsnType = document.getElementById("PensionType").value;
	if(pnsnType == 'DCRG')
	{
		printDcrgBill();
	}
	if(pnsnType == 'CVP')
	{
		printCvpBill();
	}
	if(pnsnType == 'Monthly')
	{
		printMonthlyBill();
	}
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ShowBillVO" value="${resValue.ShowBillVO}" scope="request"></c:set>
<c:set var="currRole" value="${resValue.currRole}"></c:set>
<c:set var="billFlag" value="${resValue.billFlag}"></c:set>
<c:set var="viewFlag" value="${resValue.ViewFlag}"></c:set>
<c:set var="SubjectId" value="${resValue.subjectId}"></c:set>
<c:set var="TempVal" value="${resValue.TempVal}"></c:set>

<fmt:message key='CONST.Y' var="Yflag"  bundle='${OnlineBillConstants}' />
<fmt:message key='CONST.N' var="Nflag" bundle='${OnlineBillConstants}' />
<fmt:message key='STATUS.BillCreated' var="crdtFlag" bundle='${OnlineBillConstants}' />
<fmt:message key='STATUS.BillApproved' var="billAprd" bundle='${OnlineBillConstants}' />
<fmt:message key='STATUS.BillDiscarded' var="discardFlag" bundle='${OnlineBillConstants}' />
<fmt:message key='STATUS.BillSentToTO' var="sentToTOflag" bundle='${OnlineBillConstants}' />
<fmt:message key='STATUS.BillApprovedByAuditor' var="billAprdAud" bundle='${OnlineBillConstants}' />
<fmt:message key='STATUS.BillRejected' var="billRjctd" bundle='${OnlineBillConstants}' />
<fmt:message key='STATUS.BillInwarded' var="billInwd" bundle='${OnlineBillConstants}' />
<fmt:message key='CONST.YES' var="YESflag" bundle='${OnlineBillConstants}' />
<fmt:message key='CONST.No' var="NOflag" bundle='${OnlineBillConstants}' />
<fmt:message key='COB.BUTTON.SIGSUBMIT' var="signSubmit" bundle='${onlinebillprepLabels}' />
<fmt:message key='BUDGT.TYPECODE' var="budgtTypeCode" bundle='${pensionBillConst}' />
<head>
	<title>Create Pension Bills</title>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
	<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
	<script type="text/javascript" src="script/common/commonfunctions.js"></script>
	<script type="text/javascript" src="script/common/common.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
	<script type="text/javascript" src="script/pensionpay/pensionBills.js"> </script>
	<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>	
	<input type="hidden" name="SubjectId" id="SubjectId" value="${resValue.subjectId}"/>
	<input type="hidden" name="subId" id="subId" value="${resValue.SubId}"/>
	<input type="hidden" name="ppoNo" id="ppoNo" value="${resValue.PPONUM}"/>
	<input type="hidden" name="validNomineeFlag" id="validNomineeFlag" value="${resValue.ValidNomineeFlag}"/>
	<input type="hidden" name="billFlag" id="billFlag" value="${resValue.billFlag}"/>

	
	<script type="text/javascript">
		keyPreview = 1;
	</script>
	<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"> </script>
	<script type="text/javascript" src="script/pensionpay/Common.js"> </script>
</head>

<hdiits:form method="post" name="frmCreateOnlineBill"  validate="true" encType="multipart/form-data" action="ifms.htm?actionFlag=createOnlineBill&action=save">
	<input type="hidden" name="arrearDtls" id = "arrearDtls" value="${resValue.arrearDtls}"/>
	<input type="hidden" value="${currRole}" id="hdCurrRole" value="hdCurrRole"/>
	<input type="hidden" id="hidTempVal" name="hidTempVal" value="${TempVal}" />
	<fmt:message key="DigiEnabled" var="DigFlag" bundle="${OnlineBillConstants}" />
	<hdiits:hidden name="hidBillTypeId" id="hidBillTypeId" default="${resValue.BillTypeId}"/>
	<hdiits:hidden name="hidPensionType" id="hidPensionType" default="${resValue.PensionType}"/>
	<hdiits:hidden name="userAction" id="userAction" default="save"/>	
	<hdiits:hidden name="isNewFromRejected" id="isNewFromRejected" default="${resValue.isNewFromRejected}"/>	
		
	<hdiits:hidden name="hidBillNo" id="hidBillNo" default="${resValue.TrnBillRegister.billNo}" />
	
	<hdiits:hidden name="hidChangeRqstId" id="hidChangeRqstId" default="${resValue.ChangeRqstId}" />
		<input name="hidBillCntrlNo" id="hidBillCntrlNo" type="hidden" value="${resValue.TrnBillRegister.billCntrlNo}" />
	<c:if test="${resValue.lObjTrnBillMvmnt.billMvmtId != null}">
		<hdiits:hidden name="hidBillMvmntId" default="${resValue.lObjTrnBillMvmnt.billMvmtId}" />
		<c:set var="lStrCurrRemarks" value="${resValue.currRemarks}"></c:set>	
	</c:if>

	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
	<div id="progressImage" style="display:none"></div>
	

 	 <table align="center" width="100%">			
			<tr>
				<td>
					<c:if test="${resValue.PagePath != null}">
						<jsp:include page="${resValue.PagePath}"></jsp:include>
					</c:if>
				</td>	
			</tr>
	</table>
	
   	<table align="center" width="100%">			
			<tr>
				<td>
					<jsp:include page="/WEB-INF/jsp/pensionpay/budDtls.jsp" />
					<span id="partySpan" >
						<jsp:include page="/WEB-INF/jsp/pensionpay/partyInfo.jsp" />						
					</span> 
				
				
					<fieldset style="width: 100%" class="tabstyle" id="fsRemarkDtls">
					
					<legend id="headingMsg">
					<b><fmt:message key="CMN.REMARKS_DETAILS" bundle="${pensionLabels}"></fmt:message></b></legend>
					<table>
					<tr>
					<td class="Label" align="left" width="10%">&nbsp;&nbsp; <fmt:message key="CMN.REMARKS" bundle="${pensionLabels}"></fmt:message></td>
					<td colspan="2" align="left">&nbsp; 
					<textarea name="txtareaRemarks" id="id_txtareaRemarks" cols="65" rows="3" dir="ltr" onkeypress="isValidRemText(this)" onfocus="onFocus(this)" onblur="onBlur(this)"></textarea>
					
					<c:if test="${(currRole== '365450' or currRole=='365451' or currRole == '365461' or currRole == '365462') and !(resValue.TrnBillRegister.billNo =='' or resValue.TrnBillRegister.billNo == null)}">
					<td>
						<hdiits:button name="btnViewRemarks" type="button" captionid="PPMT.VIEWREMARKS" bundle="${pensionLabels}" onclick="viewRemarks();" />
					</td>
					</c:if>
					
						</tr>
						<tr><td><div id="arrearDtlContainer">
					</div></td></tr>
						</table>
						
					</fieldset>
					
				</td>
				
			</tr>
		</table>
		<c:if test="${viewFlag == 'Y'}">
		<script>
			document.getElementById("fsRemarkDtls").style.display="none";
		</script>
		</c:if>
	<br />
	
	<div align="center">
		<c:choose>
			
			<c:when test="${currRole== '365451'}">
				<hdiits:button type="button" name="btnApprove" id="btnApprove"  captionid="CMN.APPROVE" bundle="${onlinebillprepLabels}" onclick="saveData()"/>
				<hdiits:button type="button" name="btnReject" id="btnReject"  captionid="CMN.REJECT" bundle="${onlinebillprepLabels}" onclick="rejectBill()"/>
			</c:when>	
			<c:when test="${currRole == '365461'}">
				<hdiits:button type="button" name="btnReject" id="btnReject"  captionid="CMN.REJECT" bundle="${onlinebillprepLabels}" onclick="rejectBill()"/>
			</c:when>
					
			<c:when test="${!(billFlag == 'AR' || billFlag == 'DI' || viewFlag == 'Y' || billFlag == 'MPG')}">
				<hdiits:button type="button" name="btnSave" id="btnSave"  captionid="COB.BUTTON.SAVE" bundle="${onlinebillprepLabels}" onclick="saveData()"/>
			</c:when>
		</c:choose>
		<hdiits:button name="btnViewOuterBill" id ="btnViewOuterBill" type="button" captionid="BTN.VIEWOUTERBILL" bundle="${pensionLabels}" onclick="viewOuterPensionBill();"  style="display: inline;"/>
		<hdiits:button name="btnViewInnerBill" id ="btnViewInnerBill" type="button" captionid="BTN.VIEWINNERBILL" bundle="${pensionLabels}" onclick="viewInnerPensionBill();"  style="display: inline;"/>
		<hdiits:button name="btnViewBill" id ="btnViewBill" type="button" captionid="CMN.VIEWBILL" bundle="${onlinebillprepLabels}" onclick="printBillData();"  style="display: inline;"/>
		<script>
		{
			var tempVal = document.getElementById("hidTempVal").value;
			var subId = document.getElementById("SubjectId").value;
			if(tempVal == 'BeforeSave')
			{
				document.getElementById("btnViewBill").style.display='none';
				document.getElementById("btnViewOuterBill").style.display='none';
				document.getElementById("btnViewInnerBill").style.display='none';
			}
			else
			{
				if(subId != '9' && subId != '45')
				{
					document.getElementById("btnViewBill").style.display='inline';
					document.getElementById("btnViewOuterBill").style.display='none';
					document.getElementById("btnViewInnerBill").style.display='none';
				}
				else if(subId == 9)
				{
					document.getElementById("btnViewBill").style.display='none';
					document.getElementById("btnViewOuterBill").style.display='inline';
					document.getElementById("btnViewInnerBill").style.display='inline';
				}
				else
				{
					document.getElementById("btnViewBill").style.display='none';
					document.getElementById("btnViewOuterBill").style.display='none';
					document.getElementById("btnViewInnerBill").style.display='none';
				}
				
			}
		}
		</script>
		
	<hdiits:button type="button" name="btnClose" captionid="COB.BUTTON.CLOSE" bundle="${onlinebillprepLabels}" onclick="winCls();"/>
	</div>
	<%@ include file="/WEB-INF/jsp/core/footer.jsp" %>
	<script>
	var arrearDtlsVal = document.getElementById("arrearDtls").value;
	arrearDtlsVal = arrearDtlsVal.replace(new RegExp("~","g"),">");
	document.getElementById("arrearDtlContainer").innerHTML = arrearDtlsVal;
	</script>	
</hdiits:form>
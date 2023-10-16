<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.pension.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pension.PensionConstants" var="PensionConstants" scope="request"/>
<fmt:setBundle basename="resources.pension.PensionAlerts" var="PensionAlerts" scope="request"/>
<fmt:setBundle basename="resources.pension.PensionToolTip" var="pensionToolTip" scope="request"/>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script  type="text/javascript"  src="script/common/tagLibValidation.js"></script>
<script  type="text/javascript"  src="script/pension/Common.js"></script>
<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script  type="text/javascript"  src="script/pension/PensionCase.js"></script>
<script  type="text/javascript"  src="script/common/behaviour.js"></script>
<script  type="text/javascript"  src="script/pension/behaviourPensioncaseRules.js"></script>
<script  type="text/javascript"  src="script/pension/SupplementaryBill.js"></script>
<link rel="stylesheet" href="<c:url value="/themes/${themename}/pensionCommonStyles.css"/>" type="text/css" />
<script type="text/javascript">
var saveFlag = 0;
</script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<style type="text/css">
	.tabstyle {
		border-width: 5px 1px 1px 1px;
		border-color: #2065c0;
		border-style: solid;
		padding-top:5px;
		padding-bottom:5px;}
		legend {

		padding-left:5px;
		padding-right:5px;
		font-weight:bold;
		font-family:sans-serif;
		font-color:#2065c0;

		border-width: 0px 0px 1px 0px;
		border-color: #2065c0;
		border-style: solid
		}
		table.PopUp {
			width: 500px;
			height: 50px;
			border-collapse: border-collapse;
			border: 2px solid #000000;
			background-color: #ffffff;
		}
		table.PopUp td {
			border: 1px solid #000000;
		}
		#colorPalette {
			position: absolute;
		}
</style>

<script type="text/javascript">

	function setPensionerName()
	{
		document.getElementById("pName").innerHTML = document.getElementById("prefixName").value+' '+document.getElementById("txtFirstName").value +'  '+document.getElementById("txtMiddleName").value+'  '+document.getElementById("txtLastName").value ;
	}
	function saveData()
	{
		if(saveFlag == 1)
		{
			saveData123();
		}
	}
	function saveAndNew()
	{
		if(saveFlag == 1)
		{
			saveData123();
			flagForSaveAndNew = true;
		}
	}
	function getMasterScreen()
	{
		document.forms[0].action = "ifms.htm?viewName=adminPensionCase";
		document.forms[0].submit();
	}
	function getElement(aID){
	var rv = (document.getElementById) ? document.getElementById(aID) :
	document.all[aID];
	return rv;
	}
	function togglePopUp() {
		e = getElement('PopUp');
		//e.style.visibility = e.style.visibility=='hidden'?'':'hidden';
		e.style.display = 'inline';
		//disable();
		disableCase();
		document.getElementById("txtFinalPPONo").disabled = false;
		document.getElementById("txtFinalPPONo").focus();
		//goToFieldTab("finalPPONo",4);
	}
	function windoCls()
	{
		var posx = 0;
		var posy = 0;
		if (!e) var e = window.event;
		if (e.pageX || e.pageY)
		{
			posx = e.pageX;
		    posy = e.pageY;
		 }
		 else if (e.clientX || e.clientY)
		 {
		 	if(e.clientY <0 || e.clientX <0)
		 	{
		 		winCls();
		 	}
		 }
	}
</script>
<c:choose>

	<c:when test="${resValue.TrnPensionRqstHdrVO.approveStatus == 'BILLCRTD' || resValue.CaseDisable == 'Y' || resValue.TrnPensionRqstHdrVO.approveStatus == 'STBILLCRTD'}">
		<c:if test="${resValue.AUDITFlag != 'Y'}">
			 <c:set var="read" value="disabled" />
		</c:if>
	</c:when>
	<c:otherwise>
		<c:set var="read" value="" />
	</c:otherwise>
</c:choose>
<c:if test="${resValue.TrnPensionRqstHdrVO.approveStatus == 'APPROVED' && resValue.AUDITFlag != 'Y' }">
	 <c:set var="read" value="disabled" />
</c:if>
<jsp:include  page="/WEB-INF/jsp/pension/PensionCaseAlerts.jsp" />
<input type="hidden" name="auditFlag" id="auditFlag" value="${resValue.AUDITFlag}"/>
<input type="hidden" name="hidDcrg" id="hidDcrg" value="${resValue.DCRG}" />
<input type="hidden" name="hidPension" id="hidPension" value="${resValue.Pension}" />
<input type="hidden" name="hidCVP" id="hidCVP" value="${resValue.CVP }" />
<input type="hidden" id="hidCurDate" name="hidCurDate" value="${resValue.lDtCurDate}"/>
<input type="hidden" id="hidCaseDisable" name="hidCaseDisable" value="${resValue.CaseDisable}"/>
<c:if test="${resValue.AUDITFlag == 'Y'}">
	<input type="hidden" name="hidAprvdDAPrcnt" id="hidAprvdDAPrcnt" value="${resValue.TrnPensionRqstHdrVO.daPercent}" >
	<input type="hidden" name="hidAprvdCvpAmt" id="hidAprvdCvpAmt"  value="${resValue.TrnPensionRqstHdrVO.cvpAmount}">
	<input type="hidden" name="hidAprvdDcrgAmt" id="hidAprvdDcrgAmt" value="${resValue.TrnPensionRqstHdrVO.dcrgAmount}">
	<input type="hidden" name="hidChngdCvpAmt" id="hidChngdCvpAmt" value="${resValue.ChangedRqstHdr.cvpAmount}">
	<input type=hidden name="hidChngdDAPrcnt" id="hidChngdDAPrcnt" value="${resValue.ChangedRqstHdr.daPercent}">
	<input type="hidden" name="hidChngdDCRGAmt" id="hidChngdDCRGAmt" value="${resValue.ChangedRqstHdr.dcrgAmount}">
	<input type="hidden" name="hidDiffDcrgAmt" id="hidDiffDcrgAmt"  />
</c:if>
<input type="hidden" name="hidCaseTypeFlag" id="hidCaseTypeFlag" value="${resValue.TrnPensionRqstHdrVO.typeFlag}" />

<c:set var="mstPensionerHdrVO" value="${resValue.MstPensionerHdrVO}" />
<c:set var="currDate" value="${resValue.lDtCurDate}"></c:set>
<c:choose>
	<c:when test="${resValue.TrnPensionRqstHdrVO.typeFlag != null }">
		<c:set var="inWardStatus" value="${resValue.TrnPensionRqstHdrVO.typeFlag}"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="inWardStatus" value="${resValue.InwardStatus}"></c:set>
	</c:otherwise>
</c:choose>
<input type="hidden" name="hidInwardStauts" id="hidInwardStauts" value="${inWardStatus}" />

<legend id="headingMsg" class="formtitle" >
<center><b><fmt:message key="PNSN.InWARD" bundle="${pensionLabels}"></fmt:message></b></center>
</legend>
<body onunload="windoCls();">
<hdiits:form name="inwdPension" id="inwdPension" encType="multipart/form-data" validate="true" method="POST">


<c:if test="${resValue.AUDITFlag != 'Y'}">
	<input type='hidden' name="hidSplFpPensionFlag" id="hidSplFpPensionFlag" />
	<input type='hidden' name="hidSplFpPCVPFlag" id="hidSplFpCVPFlag" />
	<input type='hidden' name="hidSplFpDCRGFlag" id="hidSplFpDCRGFlag" />
	<input type='hidden' name="hidSplFpPensionStatusFlag" id="hidSplFpPensionStatusFlag" />
</c:if>
<c:if test="${resValue.AUDITFlag == 'Y'}">
	<input type="hidden" id="hidRevisedStatus" name="hidRevisedStatus" value="N"/>
	<input type="hidden" name="hidLPCDesc" id="hidLPCDesc"/>
	<input type="hidden" name="hidForm22Desc" id="hidForm22Desc"/>
	<input type="hidden" name="hidHODDesc" id="hidHODDesc"/>
	<input type="hidden" name="hidDeptDesc" id="hidDeptDesc"/>
	<input type="hidden" name="hidDesigDesc" id="hidDesigDesc"/>
	<input type="hidden" name="hidBranchDesc" id="hidBranchDesc"/>
	<input type="hidden" name="hidBankDesc" id="hidBankDesc"/>
	<input type="hidden" name="hidDistDesc" id="hidDistDesc"/>
	<input type="hidden" name="hidStateDesc" id="hidStateDesc"/>
</c:if>
<input type="hidden" id="hidCaseForPSBAdmin" name="hidCaseForPSBAdmin" value="${resValue.isSpecialForPSBAdmin}"/>
<input type="hidden" name="hidIsConvertSave" id="hidIsConvertSave" value="${resValue.isConvertSave}">
<input type="hidden" name="hidCaseStatus" id="hidCaseStatus" value="${resValue.TrnPensionRqstHdrVO.caseStatus}">
<input type="hidden" id="hidCaseFromLevel" name="hidCaseFromLevel" value="${resValue.CaseFromLevel}"/>

<c:forEach var="TrnPensionRqstTrackingVO" items="${resValue.TrnPensionRqstTrackingVOArray}" varStatus="PNo">
	<input type="hidden" name="hidChangeItem" value="${TrnPensionRqstTrackingVO.changeItem}">
	<input type="hidden" name="hidItemFlag" value="${TrnPensionRqstTrackingVO.itemFlag}">
</c:forEach>

<div id="RqstChkList" style="display: none">
	<c:if test="${resValue.AUDITFlag == 'Y' }">
		<jsp:include page="/WEB-INF/jsp/pension/GetSavedRequestForUpdate.jsp" />
	</c:if>
</div>

<script>
	var changeItem = document.getElementsByName('hidChangeItem');
	var itemFlag = document.getElementsByName('hidItemFlag');
	for(var i=0;i<changeItem.length;i++)
	{
		if(itemFlag[i].value == 'P')
		{
			if(changeItem[i].value == '${resValue.TrnPensionRqstHdrVO.pensionerCode}')
			{
				document.getElementById('RqstChkList').style.display = 'inline';
			}
		}
		else
		{
			if(changeItem[i].value == '${resValue.MstPensionerDtlsVO.branchCode}')
			{
				document.getElementById('RqstChkList').style.display = 'inline';
			}
		}
	}
</script>

	<div id="tabmenu">
        	 <ul id="maintab" class="shadetabs" >
	     	<li class="selected">
		    	<a href="#" rel="tcontent1" >
		  			<fmt:message key="CMN.PENSIONDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	       	<li>
		        <a href="#" rel="tcontent2">
					<fmt:message key="CMN.PENSIONERDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	        <li>
		        <a href="#" rel="tcontent3">
					<fmt:message key="CUT.REMARKS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>

	        <c:if test="${resValue.TrnPensionRqstHdrVO.approveStatus == 'APPROVED' || resValue.TrnPensionRqstHdrVO.typeFlag == 'C' || resValue.TrnPensionRqstHdrVO.typeFlag == 'S'}">
	        	<li>
			        <a href="#" rel="tcontent4">
						<fmt:message key="PNSN.CaseHistory" bundle="${pensionLabels}"></fmt:message>
			        </a>
		        </li>
	        </c:if>
         </ul>
	</div>
	<div class="tabcontentstyle">

	<!-- ------------------Pension Details--------------- -->
		<div id="tcontent1" class="tabcontent" align="left" >
			<jsp:include page="/WEB-INF/jsp/pension/pensionDtls.jsp" />
		</div>

	<!-- ------------------Pensioner Details--------------- -->
		<div id="tcontent2" class="tabcontent" align="left">
			<jsp:include page="/WEB-INF/jsp/pension/pensionerDtls.jsp" />
		</div>

	<!-- ------------------ Remaks --------------- -->
		<div id="tcontent3" class="tabcontent" align="left">
			<jsp:include page="/WEB-INF/jsp/pension/caseRemarks.jsp" />
		</div>
	<!-- ------------------Pension Details--------------- -->
		<div id="tcontent4" class="tabcontent" align="left" >
			<jsp:include page="/WEB-INF/jsp/pension/pensionCaseHst.jsp" />
		</div>
	</div>
	<div  id="PopUp" style="display: none">
			<table class='PopUp'>
		   		<tr>
					<td > <font color="red"><b> Plese Enter Final PPO Number</b> </font> </td>
				    <td ><Input type="text" name="txtFinalPPONo" id="txtFinalPPONo" onkeypress="upperCaseNumberPPo(event)" ></td>
				 </tr>
			  </table>
			</div>
			<div align="center">
	<table >
	<c:if test="${resValue.isSpecialForPSBAdmin != 'Y'}">
		<tr>
			<td align="center">
				<c:if test="${resValue.insert == 'insert'}">
				  				<input type="button" name="btnSave" value="<fmt:message key="RECO.SAVE" bundle="${pensionLabels}"></fmt:message>"  class="buttontag" onclick="saveData()" title="<fmt:message key="CMN.SAVE" bundle="${pensionToolTip}"></fmt:message>"/>
								<input type="button" name="btnSaveAndNew" id="btnSaveAndNew" value="<fmt:message key='CASE.SAVEANDNEW' bundle='${pensionLabels}'></fmt:message>"  class="buttontag" onclick="saveAndNew()" />
								<script>
									saveFlag = 1;
								</script>
				</c:if>
				<c:if test="${resValue.insert == 'update' && read != 'disabled'}">
								<input type="button" name="btnUpdate" value="Update" ${read}  class="buttontag" onclick="saveData()" title="<fmt:message key="CMN.UPDATE" bundle="${pensionToolTip}"></fmt:message>"/>
								<script>
									saveFlag = 1;
								</script>
				</c:if>
					<c:if test="${inWardStatus == 'P' && resValue.AUDITFlag != 'Y'}">
								<input type="button" style="width:150px" name="btnConvert" value="<fmt:message key="PNSN.CNVRTNRML" bundle="${pensionLabels}"></fmt:message> "  class="buttontag" onclick="togglePopUp()"/>
							</c:if>
						<c:if test="${resValue.TrnPensionRqstHdrVO != null && resValue.TrnPensionRqstHdrVO.caseStatus != 'REJECT' && resValue.TrnPensionRqstHdrVO.currCaseStatus >= 5 && resValue.CaseDisable != 'Y' && resValue.TrnPensionRqstHdrVO.caseForMronly != 'Y' && resValue.DistributeCase != 'Y' && resValue.currCaseStatus != 75 }">
							<input type="button" class="buttontag" name="btnPnsn" accesskey="P" value="<fmt:message key="CMN.PNSNBILLTYPE" bundle="${pensionLabels}"></fmt:message>" onclick="generateBill1('PENSION')"/>
							<input type="button" class="buttontag" name="btnDCRG" accesskey="R" value="<fmt:message key="CMN.DCRGBILLTYPE" bundle="${pensionLabels}"></fmt:message>" onclick="generateBill1('DCRG')"/>
							<input type="button" class="buttontag" name="btnCVP" accesskey="C" value="<fmt:message key="CMN.CVPBILLTYPE" bundle="${pensionLabels}"></fmt:message>" onclick="generateBill1('CVP')"/>
							<c:if test="${resValue.SubTreasuryFlag != true && resValue.TrnPensionRqstHdrVO.approveStatus != 'STOOUTWRD' && resValue.TrnPensionRqstHdrVO.approveStatus != 'STBILLCRTD' && resValue.TrnPensionRqstHdrVO.approveStatus != 'STOREJECT'}">
								<c:if test="${resValue.TrnPensionRqstHdrVO.currCaseStatus == '5' || resValue.TrnPensionRqstHdrVO.currCaseStatus == '75' || resValue.TrnPensionRqstHdrVO.currCaseStatus == '60' }">
									<hdiits:button type="button" name="SuplyBill" id="SuplyBill" value="Supplementary Bill" onclick="openSuplyBillPopup()" style="width:200Px" />
								</c:if>
							</c:if>
							<c:if test="${resValue.TrnPensionRqstHdrVO.approveStatus == 'APPROVED'}">
								<hdiits:button type="button" name="viewPayableBill" id="viewPayableBill" value="View Payable Bill Details" onclick="getMonthlyPayableBillDtls()" style="width:200Px" />
							</c:if>
						</c:if>
						<c:if test="${resValue.insert == 'update' && read != 'disabled' && resValue.TrnPensionRqstHdrVO.caseForMronly != 'Y'}">
							<hdiits:button type="button" name="Arr" id="Arr" value="Revised Arrear Calculation" onclick="openRevArrear()" style="width:200Px" />
						</c:if>
						<input type="button" name="btnClose" value="<fmt:message key="PNSN.CLOSE" bundle="${pensionLabels}"></fmt:message>" class="buttontag" onclick="winCls();" title="<fmt:message key="PNSN.Close" bundle="${pensionToolTip}"></fmt:message>" />
					</td>
		</tr>
	</c:if>
	<c:if test="${resValue.isSpecialForPSBAdmin == 'Y'}">
	<tr>
		<td>
			<input type="button" style="width: 140px" name="btnSaveAndNew" id="btnSaveAndNew" value="ApproveAndForward"  class="buttontag" onclick="saveData123()" />
		</td>
		<td>
			<input type="button" name="btnClose" value="<fmt:message key="PNSN.CLOSE" bundle="${pensionLabels}"></fmt:message>" class="buttontag" onclick="getMasterScreen()" title="<fmt:message key="PNSN.Close" bundle="${pensionToolTip}"></fmt:message>" />
		</td>
	</tr>
	</c:if>
	</table>
</div>
</hdiits:form>
</body>
<%@ include file="/WEB-INF/jsp/pension/curntMonthPopUp.jsp"%>
<script type="text/javascript">
	initializetabcontent("maintab");
	getADPAmountByAjax();
	if("${resValue.TrnPensionRqstHdrVO.omrType}".length  > 0 )
	{
		document.getElementById("cmbOmrType").value = "${resValue.TrnPensionRqstHdrVO.omrType}";
	}

	if("${resValue.TrnPensionRqstHdrVO.caseForMronly}".length  > 0 || document.getElementById("hidCaseId").value.length > 0 )
	{
		//document.getElementById("CmbMrCase").disabled = true; 
		if("${resValue.TrnPensionRqstHdrVO.caseForMronly}".length  > 0 )
		document.getElementById("CmbMrCase").value = "${resValue.TrnPensionRqstHdrVO.caseForMronly}";
	}
	//disableForMROnly();
	if(document.getElementById("hidCaseId").value.length > 0)
	{
		document.getElementById("CmbpensionClass").disabled =  "disabled"	;
		if("${read}" == "")
		{
			goToFieldTab("SanAuthLeterNum",0);
		}
	}
	else
	{
		if("${read}" == "")
		{
			goToFieldTab("CmbpensionClass",0);
		}
	}
	if(document.getElementById("hidCaseId").value.length > 0)
	{
		document.getElementById("CmbpensionClass").disabled =  "disabled";
		if("${read}" == "")
		{
			goToFieldTab("SanAuthLeterNum",0);
		}
	}
	else
	{
		if("${read}" == "")
		{
			goToFieldTab("CmbpensionClass",0);
		}
	}
	changeMandtry();
	//document.getElementById("txtPpoNo").disabled =  "disabled";
	//document.getElementById("sancAuthCmb").disabled =  "disabled";
	if("${resValue.AUDITFlag}" != "Y")
	{
		setDCRGButton('${resValue.TrnPensionRqstHdrVO.dcrgPaidFlag}');
	    setCVPBUtton('${resValue.TrnPensionRqstHdrVO.cvpPaidFlag}');
	   // setPesnionBillButton();
	}
	if("${resValue.AUDITFlag}" == "Y")
	{
		if("${resValue.CaseFromLevel}" == "50")
		{
			if(document.getElementById("btnDCRG"))
			{
				if(document.getElementById("btnDCRG").style.display != 'none')
				{
					document.getElementById("btnDCRG").style.display = 'inline';
				}
			}
			if(document.getElementById("btnCVP"))
			{
				if(document.getElementById("btnCVP").style.display != 'none')
				{
					document.getElementById("btnCVP").style.display = 'inline';
				}
			}
			if(document.getElementById("btnPnsn"))
			{
				if(document.getElementById("btnPnsn").style.display != 'none')
				{
					document.getElementById("btnPnsn").style.display = 'inline';
				}
			}
		}
		else
		{
			if(document.getElementById("btnDCRG"))
			{
				document.getElementById("btnDCRG").style.display = 'none';
			}
			if(document.getElementById("btnCVP"))
			{
				document.getElementById("btnCVP").style.display = 'none';
			}
			if(document.getElementById("btnPnsn"))
			{
				document.getElementById("btnPnsn").style.display = 'none';
			}
		}
	}
	
</script>
<c:if test="${resValue.isSpecialForPSBAdmin == 'Y'}">
	<script type="text/javascript">
		document.getElementById("txtPpoNo").disabled =  "";
	</script>
</c:if>

<script type="text/javascript">
checkTIRate();
function checkTIRate()
{
	var ppoNo = document.getElementById("txtPpoNo").value;
	checkTIMasterRateByAJAX(ppoNo);	
}
function checkTIMasterRateByAJAX(ppoNo)
{
	req = createXMLHttpRequest();
	if(req != null)
	{
		var rop = "";
		var x = document.getElementsByName("ropChkBox");
		for(var i=0;i<x.length;i++)
		{
			if(x[i].checked == true)
			{
				if(x[i].value == '1986')
				{	
					rop = '1986';
				}
				if(x[i].value == '1996')
				{	
					rop = '1996';
				}
				if(x[i].value == '2006')
				{	
					rop = '2006';
				}
			}
		}
		var hdCd = document.getElementById("cmbHeadCode").value;
		var dpMerged = "";
		
		if(document.getElementById("radiosplCaseY").checked == true)
		{
			dpMerged = 'Y';
		}
		if(document.getElementById("radiosplCaseN").checked == true)
		{
			dpMerged = 'N';
		}
		
		var	baseUrl = "ifms.htm?actionFlag=checkTIMasterRateByAJAX&PPONO="+ppoNo+"&ROP="+rop+"&HeadCode="+hdCd+"&DPMerged="+dpMerged;
	
		req.open("post", baseUrl, true); 
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.onreadystatechange = responseTIMasterRateInfo; 
		req.send(baseUrl);	
	}
}
function responseTIMasterRateInfo()
{
	if(req.readyState==complete_state)
	{ 
		var XMLDoc = req.responseXML.documentElement;
		var XMLEntries = XMLDoc.getElementsByTagName("TIMASTERRATE");
		var newFRMonValue = "";
		var newTOMonValue = "";
		
		if(XMLEntries[0].childNodes[0].text != null)
		{
			if(XMLEntries[0].childNodes[0].text != 'null')	
			{
				document.getElementById("txtTiPrct").value = XMLEntries[0].childNodes[0].text;
			}
		}
	}
}
</script>





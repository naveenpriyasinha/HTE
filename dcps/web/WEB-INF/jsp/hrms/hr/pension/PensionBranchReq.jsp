<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.pension.Pension" var="pensionBranchReqLabel" scope="request" />
<html>
<head>
<title>Human Resource Management System</title>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>

<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript">
var arrSendMe = new Array();
var win;
var arrSendMeCount=0;
function sendTenMonthData(tenMon,tenYear,tenSal)
{
	var str = tenMon+"~"+tenYear+"~"+tenSal;	
	arrSendMe[arrSendMeCount]=str;		
	arrSendMeCount = arrSendMeCount+1;
}
function getLastMonthSalary()
{
	document.getElementById('displaySalaryArr').value=arrSendMe;
	win=window.open("hrms.htm?viewName=PensionEmpSalaryDtl","SalaryDetails","width=530,height=500,toolbar=no,status=yes,menubar=no,resizable=no,top=100,left=100,dependent=yes");				
	if (win.opener == null) {win.opener = self;	}	
	win.focus();	
}
function setChildFocus()
{
	try {
	if(document.getElementById('totalServiceScreenOnFlag').value=='true')
	{
		win.focus();
	}}catch(ex){document.getElementById('totalServiceScreenOnFlag').value=='false';}
}
function getServiceDetails()
{
	if(document.getElementById('totalServiceScreenOnFlag').value=='false')
	{	
		var reqId = document.getElementById('reqId').value;			
		win=window.open("hrms.htm?actionFlag=getTotalPensionServDtls&reqId="+reqId,"ServiceDetails","width=850,height=600,scrollbars=yes,toolbar=no,status=yes,menubar=no,resizable=yes,top=20,left=100,dependent=yes");				
		if (win.opener == null) {win.opener = self;	}	
		if(window.complete_state){win.focus();}
	}
}
function checkForDot(fieldValue)
{
	var valueFlag=true,dotCount=0;
	for(var i=0;i<fieldValue.length;i++)
	{
		var keyId = fieldValue.charCodeAt(i);									
		if(keyId==46) {dotCount=parseInt(dotCount)+1;}
	}
	if(dotCount>=2){valueFlag=false;}
	return valueFlag;
}
function callLastJSFunction()
{
	var strCalMon,calMonFlag=true,strCompAmt,compAmtFlag=true;
	strCalMon = document.getElementById('calMonPension').value;
	strCompAmt = document.getElementById('comPenAmt').value; 
	if(strCalMon!='')
		calMonFlag = checkForDot(strCalMon);		
	if(strCompAmt!='')
		compAmtFlag = checkForDot(strCompAmt);
	if(calMonFlag==true && compAmtFlag==true)
	{
		return true;
	}
	else if(calMonFlag==false)
	{
		document.getElementById('calMonPension').value=='';
		alert('<fmt:message  bundle="${pensionBranchReqLabel}" key="Pension.calMonFlagAlt" />');
	}
	else if(compAmtFlag==false)
	{
		document.getElementById('comPenAmt').value=='';
		alert('<fmt:message  bundle="${pensionBranchReqLabel}" key="Pension.compAmtFlagAlt" />');
	}
	return false;	
}
function validatePensionForm(flag)
{			
	var validData = validateForm_frmPenBranchReq();
	if( validData==true)
	{		
		var strCalMon,calMonFlag=true,strCompAmt,compAmtFlag=true;
		strCalMon = document.getElementById('calMonPension').value;
		strCompAmt = document.getElementById('comPenAmt').value; 
		if(strCalMon!='')
			calMonFlag = checkForDot(strCalMon);		
		if(strCompAmt!='')
			compAmtFlag = checkForDot(strCompAmt);
		if(calMonFlag==true && compAmtFlag==true)
		{			
			var callMe=true;
			if(flag=='fwd')
			{
				callMe = confirm('<fmt:message  bundle="${pensionBranchReqLabel}" key="Pension.FwdData" />');
			}
			else
			{
				callMe = confirm('<fmt:message  bundle="${pensionBranchReqLabel}" key="Pension.SubmitData" />');
			}
			if(callMe==true)
			{
				startProcess();
				if(flag=='fwd')
				{
					document.getElementById('fwd').value='forward';
				}
				if(flag=='rej')
				{
					document.getElementById('fwd').value='reject';
				}		
				window.setTimeout('onSubmitClick()',1000);
			}
		}
		else
		{
			if(calMonFlag==false){alert('<fmt:message  bundle="${pensionBranchReqLabel}" key="Pension.calMonFlagAlt" />');}
			else if(compAmtFlag==false){alert('<fmt:message  bundle="${pensionBranchReqLabel}" key="Pension.compAmtFlagAlt" />');}
		}
	}
}
function onSubmitClick()
{			
	document.frmPenBranchReq.action="hrms.htm?actionFlag=SubmitBranchPensionData";
	document.frmPenBranchReq.submit();
	endProcess();
}
function closeChild()
{
	try {
		win.close();
	}catch(ex){}
}
</script>

</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="comboRetirementType" value="${resValue.pensionRetType}"></c:set>
<c:set var="orgEmpMst" value="${resValue.orgEmpMst}"></c:set>
<c:set var="FamilyArrObj" value="${resValue.FamilyArrObj}"></c:set>
<c:set var="bankDtls" value="${resValue.bankDtls}"></c:set>
<c:set var="branchDtls" value="${resValue.branchDtls}"></c:set>
<c:set var="pensionAddforComm" value="${resValue.pensionAddforComm}"></c:set>
<c:set var="officeAdd" value="${resValue.officeAdd}"></c:set>
<c:set var="userBankData" value="${resValue.userBankData}"></c:set>
<c:set var="orgEmpMst" value="${resValue.orgEmpMst}"></c:set>
<c:set var="actionType" value="${resValue.actionType}"></c:set>
<body onunload="closeChild();" onclick="setChildFocus();" >
<hdiits:form name="frmPenBranchReq"  action="hrms.htm?actionFlag=SubmitBranchPensionData" validate="true" method="post" encType="multipart/form-data">	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="Pension.EmpAppName" bundle="${pensionBranchReqLabel}"/>
		</b></a></li>
		<li class="selected"><a href="#" rel="tcontent2"><b>
			<fmt:message key="Pension.branchAppName" bundle="${pensionBranchReqLabel}"/>
		</b></a></li>
	</ul>
	</div>	
	<div id="PensionBranchReq" name="PensionBranchReq">		
		<div id="tcontent1" class="tabcontent" tabno="0">	
			<jsp:include page="PensionBranchEmpAndFamilyDtls.jsp" />
			<BR>
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field1" titleCaptionId="Pension.bankDtls" bundle="${pensionBranchReqLabel}">		
				<table class="tabtable">			
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.bnkName" bundle="${pensionBranchReqLabel}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><c:out value="${resValue.bankName}"></c:out></td>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.branch" bundle="${pensionBranchReqLabel}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><c:out value="${resValue.branchName}"></c:out></td>				
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.accNum" bundle="${pensionBranchReqLabel}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><c:out value="${resValue.bankAcctNo}"></c:out></td>
				</tr>
				</table>
			</hdiits:fieldGroup>
			<BR>
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field2" titleCaptionId="Pension.Declaration" bundle="${pensionBranchReqLabel}">		
				<table class="tabtable">					
					<tr><td>
						<jsp:include page="../../common/declaration.jsp" />
					</td></tr>	
				</table>
			</hdiits:fieldGroup>
		<BR>		
		<table class="tabtable">					
			<tr>
				<td>
					<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="attachmentBiometric" />
						<jsp:param name="formName" value="frmPenBranchReq" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />			
						<jsp:param name="multiple" value="N" />
					</jsp:include>
				</td>
			</tr>
		</table>
		</div>		
		<div id="tcontent2" class="tabcontent" tabno="1">
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field3" titleCaptionId="Pension.EmpData" bundle="${pensionBranchReqLabel}">					
				<table class="tabtable">					
					<tr>
						<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Name" bundle="${pensionBranchReqLabel}"></hdiits:caption></b></td>
						<td class="fieldLabel" width="25%">
							<c:out value="${orgEmpMst.empFname}"></c:out>
							<c:out value="${orgEmpMst.empMname}"></c:out>
							<c:out value="${orgEmpMst.empLname}"></c:out>
						</td>
						<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Designation" bundle="${pensionBranchReqLabel}"></hdiits:caption></b></td>
						<td class="fieldLabel" width="25%"><c:out value="${resValue.designationName}"></c:out></td>
					</tr>
					<tr>
						<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.DOB" bundle="${pensionBranchReqLabel}"></hdiits:caption>(DD/MM/YYYY)</b></td>
						<td class="fieldLabel" width="25%">
							<fmt:formatDate value="${orgEmpMst.empDob}" pattern="dd/MM/yyyy" var="dob"/>
							<c:out value="${dob}"></c:out>
						</td>
						<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.DOJ" bundle="${pensionBranchReqLabel}"></hdiits:caption>(DD/MM/YYYY)</b></td>
						<td class="fieldLabel" width="25%">
							<fmt:formatDate value="${orgEmpMst.empDoj}" pattern="dd/MM/yyyy" var="doj"/>
							<c:out value="${doj}"></c:out>
						</td>					
					</tr>
				</table>
			</hdiits:fieldGroup>
			<BR>
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field4" style="display:none" titleCaptionId="Pension.LinkOther" bundle="${pensionBranchReqLabel}">		
				<table class="tabtable" style="display:none">					
					<tr bgcolor="">
						<td class="fieldLabel" colspan="5"><font color="#ffffff">
						<strong><u><a target="_blank" href="#" title="Open ServiceBook"><fmt:message
							key="Pension.ServBook" bundle="${pensionBranchReqLabel}"/></a></u></strong></font></td>
					</tr>
					<tr>
						
					</tr>
				</table>
			</hdiits:fieldGroup>
			<BR>
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field5" titleCaptionId="Pension.RetirementDtls" bundle="${pensionBranchReqLabel}">		
				<table class="tabtable">
					<tr>
						<td width="25%"><b><hdiits:caption captionid="Pension.DateOfRetire" bundle="${pensionBranchReqLabel}"/></b></td>
						<td width="25%"><fmt:formatDate value="${resValue.dor}" pattern="dd/MM/yyyy" var="dor"/>
							<c:out value="${dor}"></c:out>						
						</td>
						<td width="25%"><b><hdiits:caption captionid="Pension.TypeOfRetire" bundle="${pensionBranchReqLabel}"/></b></td>
						<td width="25%"> 
							<c:out value="${resValue.TypeOfRetirement}"></c:out>
						</td>					
					</tr>
				</table>
			</hdiits:fieldGroup>
			<BR>
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field6" titleCaptionId="Pension.PensionDtlsSrvc" bundle="${pensionBranchReqLabel}">		
				<table class="tabtable">				
					<tr>
						<td class="fieldLabel" width="25%">
							<b><hdiits:caption captionid="Pension.totalSrvc" bundle="${pensionBranchReqLabel}"></hdiits:caption></b>
						</td>
						<td class="fieldLabel" width="25%">
							<hdiits:text id="pen_total_Srvc_Year" default="${resValue.Final_Year}" size="4" name="pen_total_Srvc_Year" readonly="true" mandatory="true" validation="txt.isrequired" captionid="Pension.YEAR" bundle="${pensionBranchReqLabel}"/><b><hdiits:caption captionid="Pension.YEAR" bundle="${pensionBranchReqLabel}"/></b>
						</td>
						<td class="fieldLabel" width="25%"><hdiits:text size="2" default="${resValue.Final_Month}" id="pen_total_Srvc_Month" name="pen_total_Srvc_Month" validation="txt.isrequired" readonly="true" mandatory="true" captionid="Pension.MONTH" bundle="${pensionBranchReqLabel}"/><b><hdiits:caption captionid="Pension.MONTH" bundle="${pensionBranchReqLabel}"/></b></td>
						<td class="fieldLabel" width="25%"><hdiits:text size="2" default="${resValue.Final_Day}" id="pen_total_Srvc_Day" name="pen_total_Srvc_Day" readonly="true" validation="txt.isrequired" mandatory="true" captionid="Pension.DAY" bundle="${pensionBranchReqLabel}"/><b><hdiits:caption captionid="Pension.DAY" bundle="${pensionBranchReqLabel}"/></b></td>
					</tr>
					<tr>
						<td colspan="5">
							<center>
								<hdiits:button type="button" name="btnServiceDtld" captionid="Button.ServDtls" bundle="${pensionBranchReqLabel}" onclick="getServiceDetails();"/>
							</center>
						</td>
					</tr>
				</table>
			</hdiits:fieldGroup>
			<BR>
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field7" titleCaptionId="Pension.PensionSalaryDtls" bundle="${pensionBranchReqLabel}">		
				<table class="tabtable">					
					<tr>
						<td class="fieldLabel" width="25%">
							<b><hdiits:caption captionid="Pension.tenMonSalary" bundle="${pensionBranchReqLabel}"/></b>
						</td>
						<td class="fieldLabel" width="25%">
							<c:out value="${resValue.tenMonValue}"></c:out>
						</td>
						<td class="fieldLabel" width="25%">
							<b><hdiits:caption captionid="Pension.MonthAvg" bundle="${pensionBranchReqLabel}"/></b>
						</td>
						<td class="fieldLabel" width="25%">
							<c:out value="${resValue.monValue}"></c:out>
						</td>
					</tr>
					<tr>
						<td  colspan="5">
						<center>
							<hdiits:button type="button" name="btnSalaryDtls" captionid="Button.SalDtls" bundle="${pensionBranchReqLabel}" onclick="getLastMonthSalary();"/>
						</center>
						</td>
					</tr>
				</table>
			</hdiits:fieldGroup>
			<BR>
			<hdiits:fieldGroup collapseOnLoad="false" id="branch_req_field8" titleCaptionId="Pension.PensionCalcu" bundle="${pensionBranchReqLabel}">					
				<table width="100%">					
					<tr>
						<td class="fieldLabel">
							<b><hdiits:caption captionid="Pension.CalMonPension" bundle="${pensionBranchReqLabel}"/></b>
						</td>
						<td class="fieldLabel">
							<hdiits:number name="calMonPension" id="calMonPension" maxlength="13" mandatory="true" validation="txt.isrequired"  captionid="Pension.CalMonPension" bundle="${pensionBranchReqLabel}" default="${resValue.CalculatedPension}"/>
						</td>
						<td class="fieldLabel"><b><hdiits:caption captionid="Pension.CompPensionAmt" bundle="${pensionBranchReqLabel}"/></b></td>
						<td class="fieldLabel"><hdiits:number name="comPenAmt" maxlength="13" id="comPenAmt" mandatory="true" validation="txt.isrequired"  captionid="Pension.CompPensionAmt" bundle="${pensionBranchReqLabel}" default="${resValue.ComputedPension}"/></td>
					</tr>
					<tr>
						<td>
							<b><hdiits:caption captionid="Pension.Explanation" bundle="${pensionBranchReqLabel}"/></b>
						</td>
						<td colspan="3">
							<hdiits:textarea id="explanationBox" cols="60" maxlength="1700" rows="3" name="explanationBox" validation="txt.isrequired" captionid="Pension.Explanation" bundle="${pensionBranchReqLabel}" mandatory="true" default="${resValue.Explanation}"></hdiits:textarea>
						</td>
					</tr>
				</table>	
			</hdiits:fieldGroup>			
				
			<hdiits:hidden name="totalServiceScreenOnFlag" id="totalServiceScreenOnFlag" default="false"/>						
			<hdiits:hidden name="displaySalaryArr" id="displaySalaryArr"/>			
			<hdiits:hidden name="reqId" id="reqId" default="${resValue.reqId}"/>
			<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${resValue.reqId}"/>						
			<hdiits:hidden name="actionType" id="actionType" default="${actionType}"/>						
			<hdiits:hidden name="monAvg" id="monAvg" default="${resValue.monValue}"/>												
			<hdiits:hidden name="fwd" id="fwd" default=""/>			
		</div>		
	</div>	
	<c:choose>
		<c:when test="${actionType == 'C'}">			
			<script type="text/javascript">
				document.getElementById('explanationBox').readOnly=true;
				document.getElementById('calMonPension').readOnly=true;
				document.getElementById('comPenAmt').readOnly=true;
			</script>
		</c:when>
		<c:otherwise>		
			<c:choose>			
					<c:when test="${actionType != 'P'}">
						<center>
							<BR>
							<b><hdiits:caption captionid="Pension.ReqAppMsg" bundle="${pensionBranchReqLabel}"/></b>
							<BR><BR>					
						</center>
						<script type="text/javascript">
							document.getElementById('explanationBox').readOnly=true;
							document.getElementById('calMonPension').readOnly=true;
							document.getElementById('comPenAmt').readOnly=true;
						</script>
					</c:when>
					<c:otherwise>
						<center>
							<jsp:include page="../../../core/tabnavigation.jsp" />
						</center>
					</c:otherwise>
			</c:choose>	
		</c:otherwise>			
	</c:choose>
	<hdiits:jsField jsFunction="callLastJSFunction()" name="callLastJSFunction" />
	<hdiits:validate locale="${locale}" controlNames="" />
	<c:set var="tenMonSalData"  value="${resValue.tenMonthSalaryVOlist}"></c:set>
	<c:if test="${not empty tenMonSalData}">
		<c:forEach items="${tenMonSalData}" var="PayHstSalObj">
		<script type="text/javascript">	
			sendTenMonthData('${PayHstSalObj.month}','${PayHstSalObj.year}','${PayHstSalObj.netTotal}');
		</script>
		</c:forEach>
	</c:if>
</hdiits:form>			
	<script type="text/javascript">			
		initializetabcontent("maintab")		
		document.getElementById('target_uploadattachmentBiometric').style.display='none';
		document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';		
	</script>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
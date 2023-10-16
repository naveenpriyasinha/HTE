<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="country" value="${resultValue.visitingCountry}"></c:set>
<c:set var="prevNOCList" value="${resultValue.prevNOCList}"></c:set>
<c:set var="nocFVisitJournyObj" value="${resultValue.nocFVisitJournyObj}"></c:set>
<c:set var="nocFVisitExpenses" value="${nocFVisitJournyObj.hrEssFvisitExpenseDtls}"></c:set>
<c:set var="applicantUserId" value="${resultValue.applicantUserId}"></c:set>
<c:set var="objCmnContactMst" value="${resultValue.objCmnContactMst}"></c:set>
<c:set var="residencePhone" value="${objCmnContactMst.residencePhone}"></c:set>
<c:set var="cmnLookupMst" value="${resultValue.cmnLookupMst}"></c:set> <!-- added for NOC CR --> 

<c:forEach var="nocFVisitExpense" items="${nocFVisitExpenses}">
	<c:set var="expBearerType" value="${nocFVisitExpense.hrEssExpbearerDtl.cmnLookupMst.lookupName}"></c:set>	
	<c:set var="selectedOrg" value="${nocFVisitExpense.hrEssExpbearerDtl.cmnOrganizationMst.organizationId}"></c:set>
	<c:set var="selectedPerson" value="${nocFVisitExpense.hrEssExpbearerDtl.cmnPersonMst.personId}"></c:set>	
	<c:set var="bankDtls" value="${nocFVisitExpense.hrEssExpbearerDtl.bearerBankDtlId}"></c:set> <!-- added for NOC CR --> 	
	<c:set var="offDtlsRadio" value="${nocFVisitExpense.hasOfficialDealing}"></c:set>
	<c:set var="offDtlsStr" value="${nocFVisitExpense.officialDealDetails}"></c:set>
	
</c:forEach>

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request" />
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request"/>

<html>
<head>
<script type="text/javascript" src="/script/common/address.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/Expenditure.js"/>"></script>
<script type="text/javascript">		
		
	function closePage()
	{
		self.close();
	}
	
</script>

</head>
<body>

<hdiits:form name="frmNocForeignVisit" validate="true" method="POST"  action="./hrms.htm?actionFlag=getNocForeign"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
			<fmt:message key="NOC.NocForeignHeader" bundle="${NOCLables}"/></a></li>
	</ul>
</div>
	<div class="tabcontentstyle" style="height: 100%">
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
		
<hdiits:fieldGroup titleCaptionId="NOC.SecTripDetails" bundle="${NOCLables}">	
<!--  <table bgcolor="#386CB7" align="center"  width="100%">

	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message bundle="${NOCLables}" key="NOC.SecTripDetails"></fmt:message></u></strong></font></td>
    </tr> 
</table>
-->
<br>

<table width="100%" border="0">
	<tr>
		<td width="25%"><hdiits:caption captionid="NOC.StartDate" bundle="${NOCLables}"/></td>
		<td width="25%"><fmt:formatDate var="fromDate"  pattern="dd/MM/yyyy" value="${nocFVisitJournyObj.fromDate}" type="date"/>
  			<hdiits:text name="dtJStartDate" id="dtJStartDate" default="${fromDate}" readonly="true"/></td>
		<td width="25%"><hdiits:caption captionid="NOC.EndDate" bundle="${NOCLables}"/></td>
		<td width="25%"><fmt:formatDate var="toDate"  pattern="dd/MM/yyyy" value="${nocFVisitJournyObj.toDate}" type="date"/>
  			<hdiits:text name="dtJEndDate" id="dtJEndDate" default="${toDate}" readonly="true"/></td>
	</tr>
	<tr>
		<td width="25%"><hdiits:caption captionid="NOC.FromPlace" bundle="${NOCLables}"/></td>
		<td width="25%"><hdiits:text name="selFromPlace" id="selFromPlace" captionid="NOC.NocVisitCountry" bundle="${NOCLables}" readonly="true" default="${nocFVisitJournyObj.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}"/> </td>
		<td width="25%"><hdiits:caption captionid="NOC.ToPlace" bundle="${NOCLables}"/></td>
		<td width="25%"><hdiits:text name="selToPlace" id="selToPlace" captionid="NOC.NocVisitCountry" bundle="${NOCLables}" readonly="true" default="${nocFVisitJournyObj.cmnCountryMstByCmnCtryMstToCtryIdFk.countryName}"/> </td>		
	</tr>
</table>

<table width="100%">
	<tr>
      <th class="fieldLabel"><u><hdiits:caption captionid="NOC.SecContact" bundle="${NOCLables}"/></u></th>
    </tr> 
   	<tr>
		<td width="25%"><hdiits:caption captionid="NOC.PhoneRes" bundle="${NOCLables}"/></td>
		<td width="25%"><hdiits:text name="numPhoneResCtry" id="numPhoneResCtry" size="2" default="--" readonly="true" style="text-align: right"/> - <hdiits:text name="numPhoneResArea" id="numPhoneResArea" size="2" default="--" readonly="true" style="text-align: right"/> - <hdiits:text name="numPhoneRes" id="numPhoneRes" size="2" default="--" readonly="true" style="text-align: right"/></td>
		<td width="25%"><hdiits:caption captionid="NOC.PhoneOff" bundle="${NOCLables}"/></td>
		<td width="25%"><hdiits:text name="numPhoneOffCtry" id="numPhoneOffCtry" size="2" default="--" readonly="true" style="text-align: right"/> - <hdiits:text name="numPhoneOffArea" id="numPhoneOffArea" size="2" default="--" readonly="true" style="text-align: right"/> - <hdiits:text name="numPhoneOff" id="numPhoneOff" size="2" default="--" readonly="true" style="text-align: right"/></td>
	</tr>
	<tr>
		<td><hdiits:caption captionid="NOC.Mobile" bundle="${NOCLables}"/></td>
		<td><hdiits:text name="numMobile" id="numMobile" default="--" readonly="true" style="text-align: right"/></td>
		<td><hdiits:caption captionid="NOC.Fax" bundle="${NOCLables}"/></td>
		<td><hdiits:text name="numFax" id="numFax" default="--" readonly="true" style="text-align: right"/></td>
	</tr>
	<tr>
		<td><hdiits:caption captionid="NOC.Email" bundle="${NOCLables}"/></td>
		<td><hdiits:text name="txtEmail" id="txtEmail" maxlength="20" default="${objCmnContactMst.email}" readonly="true"/></td>	
	</tr>
</table>

<table width="100%">	
	<tr>
		<td>	
			<jsp:include page="../../../common/viewAddress.jsp">
				<jsp:param name="addrName" value="journeyContactAddress" />
			</jsp:include>
		</td>
	</tr>
</table>

<br>

<table  width="100%">
	<tr>     
      <th class="fieldLabel"><u><hdiits:caption captionid="NOC.SecExpenditureDetails" bundle="${NOCLables}"/></u></th>
    </tr> 
</table>

<%@ include file="Expenditure.jsp"%>
<br>	
<br>
<hr>

<table align="center"  width="100%">
	<tr align="center">
       <td><center><hdiits:button type="button" name="btnClose" captionid="NOC.Close" bundle="${NOCLables}" onclick="closePage();"/>
		    </center>
	  </td>
    </tr> 
</table>
</hdiits:fieldGroup>

</div>

	</div>
		
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />	
	
</hdiits:form>

<script>

initializetabcontent("maintab");
document.getElementById("displayAmtLabel").innerText = '<fmt:message bundle="${NOCLables}" key="NOC.Expense"/>';
document.getElementById("numCategorisedAmount").value=decimalPoint("${nocFVisitJournyObj.approxExpenditureNoc}");

document.getElementById("selBearer").value="${expBearerType}";
	
	if (document.getElementById("selBearer").value == 'nocExpOrg') 
	{
		showExpenditureName(document.getElementById("selBearer"),true,"${applicantUserId}","${selectedOrg}");
	}	
	else if(document.getElementById("selBearer").value == 'nocExpOther')
	{
		showExpenditureName(document.getElementById("selBearer"),true,"${applicantUserId}","${selectedPerson}");
	}
	else if(document.getElementById("selBearer").value == 'nocExpSelf')
	{
		/* added for NOC CR--starts */
		document.getElementById("displaySelbearerbank").style.display=''; // added for NOC CR
		document.getElementById("displayBankDtls").style.display = '';			
		document.frmNocForeignVisit.selBearerBank.readOnly=true;			
		// document.getElementById("selBearerBank").value='${bankDtls.hrEisBankMst.bankName}';
		// document.getElementById("txtBranch").value='${bankDtls.hrEisBranchMst.branchName}';
		// document.getElementById("txtAcctType").value='${cmnLookupMst.lookupDesc}';	
		// document.getElementById("txtAcctNo").value='${bankDtls.bankAcctNo}';	
		
		str='${bankDtls.hrEisBankMst.bankName}';
		if(str==null || str=='' || str=='null')
		{
			document.getElementById("selBearerBank").value= "--";					
		}
		else
		{
			document.getElementById("selBearerBank").value=str;
		}
			
		
		str='${bankDtls.hrEisBranchMst.branchName}';
		
		if(str==null || str=='' || str=='null')
		{
			document.getElementById("txtBranch").value= "--";
		}
		else
		{
			document.getElementById("txtBranch").value=str;						
		}
		
		var bankAcctType = '${cmnLookupMst.lookupDesc}';	
		if(bankAcctType==null || bankAcctType=='' || bankAcctType=='null')
		{
			document.getElementById('txtAcctType').value="--";
		}	
		else
		{
			document.getElementById("txtAcctType").value=bankAcctType;
		}
		// document.getElementById("txtAcctType").value='to do';	
		str='${bankDtls.bankAcctNo}';		
		if(str==null || str=='' || str=='null')
		{
			document.getElementById("txtAcctNo").value="--";
		}
		else
		{
			document.getElementById("txtAcctNo").value= str;
		}
			
		document.getElementById("displayBankDtls").style.display = '';
		/* added for NOC CR--ends */
	}
	
	 
	if (document.getElementById("selBearer").value == 'nocExpOrg' ||  document.getElementById("selBearer").value == 'nocExpOther') 
	{
		var  radioValue='${offDtlsRadio}'
		var   strofficeDtls  ='${offDtlsStr}';
		var  checkVal = new Array();
		checkVal= document.frmNocForeignVisit.rdoOfficialDealing
		if(checkVal[0].value==radioValue)
		{
			document.getElementById("DealDetail1").style.display='';
			document.getElementById("DealDetail2").style.display='';
			checkVal[0].checked=true;
			checkVal[0].disabled=true;
			checkVal[1].disabled=true;
			
			var txtArea=document.getElementById('txtaDealDetail')
			txtArea.value=strofficeDtls;
			txtArea.disabled=true;
		}
		else
		{
			checkVal[1].checked=true;
			checkVal[1].disabled=true;
			checkVal[0].disabled=true;
			document.frmNocForeignVisit.txtaDealDetail.disable=true;
		
		}
	}	
	var rPhone = "${residencePhone}";
	if(rPhone != null && rPhone != 0)
	{
		document.getElementById('numPhoneResCtry').value = rPhone.substring(0,3);
		document.getElementById('numPhoneResArea').value = rPhone.substring(3,6);
		document.getElementById('numPhoneRes').value = rPhone.substring(6,rPhone.length);
	}
	var offPhone = "${objCmnContactMst.officePhone}";
	if(offPhone != null && offPhone != 0)
	{
		document.getElementById('numPhoneOffCtry').value = offPhone.substring(0,3);
		document.getElementById('numPhoneOffArea').value = offPhone.substring(3,6);
		document.getElementById('numPhoneOff').value = offPhone.substring(6,offPhone.length);				
	}
	if("${objCmnContactMst.mobile}" != null && "${objCmnContactMst.mobile}" != 0)
		document.getElementById('numMobile').value = "${objCmnContactMst.mobile}";
	if("${objCmnContactMst.fax}" != null && "${objCmnContactMst.fax}" != 0)
		document.getElementById('numFax').value = "${objCmnContactMst.fax}";	
	makeReadOnly('journeyContactAddress');	
	document.frmNocForeignVisit.selBearer.disabled=true;	
	document.frmNocForeignVisit.selBearerName.disabled=true;
	
	
</script>
 
<%
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
%>
		
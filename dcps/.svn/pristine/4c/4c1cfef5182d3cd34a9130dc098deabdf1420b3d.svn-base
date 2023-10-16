<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.pension.Pension" var="pensionMsg" scope="request" />
<html>
<head>
<title>Human Resource Management System</title>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/pension/Pension.js"/>"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript">	
	function showHideBankDtls(radBtn)
	{
		radBtn.status=true;
		var radValue = radBtn.value;
		if(radValue==1)
		{
			document.getElementById('otrBankDtls').style.display='none';
			document.getElementById('currBankDtls').style.display='';			
		}
		else if(radValue==0)
		{
			document.getElementById('otrBankDtls').style.display='';	
			document.getElementById('currBankDtls').style.display='none';				
		}
		else {return;}
	}
	function validatePensionForm()
	{
		var validateArr = new Array('typeOfRet','addForComm');
		validateArr = validateArr.concat(decValidationArr);
		if(document.getElementById('addForComm').value=='PE_new_Add')
		{
			var addressArr = addressParameters('communicationAddress','Permanent Address');
			validateArr = validateArr.concat(addressArr);
		}
		var validData = validateSpecificFormFields(validateArr);
		if(validData==true)
		{
			if(document.frmEmpPension.radBank[1].status==true)
			{
				if(document.getElementById('bankCmbName').value=='Select')
				{
					alert('<fmt:message  bundle="${pensionMsg}" key="Pension.bankNameReq" />');
					document.getElementById('bankCmbName').focus();
					validData=false;
				}
				if(document.getElementById('branchCmbName').value=='Select' && document.getElementById('branchCmbName').length>1 && validData==true)
				{
					alert('<fmt:message  bundle="${pensionMsg}" key="Pension.branchNameReq" />');
					document.getElementById('branchCmbName').focus();
					validData=false;
				}
				if(document.getElementById('otherAccNum').value=='' && validData==true)
				{
					alert('<fmt:message  bundle="${pensionMsg}" key="Pension.accNameReq" />');
					document.getElementById('otherAccNum').focus();
					validData=false;
				}
			}			
		}
		if( validData==true )
		{			
			if(confirm('<fmt:message  bundle="${pensionMsg}" key="Pension.SubmitData" />')==true)
			{
				startProcess();
				window.setTimeout('onSubmitClick()',1000);
			}
		}
	}
	function onSubmitClick()
	{				
		document.frmEmpPension.action="hrms.htm?actionFlag=SubmitEmpPensionData";
		document.frmEmpPension.submit();		
	}	
</script>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="userBankData" value="${resValue.userBankData}"></c:set>
<c:set var="bankDtls" value="${resValue.bankDtls}"></c:set>
<c:set var="branchDtls" value="${resValue.branchDtls}"></c:set>
<c:set var="comboRetirementType" value="${resValue.pensionRetType}"></c:set>
<body>
<hdiits:form name="frmEmpPension" action="hdiits.htm" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<fmt:message key="Pension.AppName" bundle="${pensionMsg}" />
		</b></a></li>
	</ul>
	</div>
	<div id="PensionDtls" name="PensionDtls">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<jsp:include page="PensionEmpAndFamilyDtls.jsp" />
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_req_field1" titleCaptionId="Pension.PensionDtls" bundle="${pensionMsg}">		
		<table class="tabtable">		
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.TypeOfRetire" bundle="${pensionMsg}"></hdiits:caption></b></td>
				<td class="fieldLabel" width="25%">
					<hdiits:select captionid="Pension.TypeOfRetire" bundle="${pensionMsg}" id="typeOfRet" name="typeOfRet" mandatory="true" validation="sel.isrequired"> 
						<hdiits:option value="Select">--<fmt:message key="Pension.Select" bundle="${pensionEmpFamilyLabel}" />--</hdiits:option>
						<c:forEach var="typeOfRet" items="${comboRetirementType}">
	    					<option value="<c:out value="${typeOfRet.lookupName}"/>">
							<c:out value="${typeOfRet.lookupDesc}"/></option>						
						</c:forEach>
					</hdiits:select>
				</td>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.DateOfRetire" bundle="${pensionMsg}"></hdiits:caption>(DD/MM/YYYY)</b></td>
				<td class="fieldLabel" width="25%"><fmt:formatDate value="${resValue.dor}" pattern="dd/MM/yyyy" var="dor"/>
					<c:out value="${dor}"></c:out></td>
			</tr>			
		</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_req_field2" titleCaptionId="Pension.bankDtls" bundle="${pensionMsg}">		
		<table class="tabtable">		
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.SelectBankDtls" bundle="${pensionMsg}"></hdiits:caption></b></td>
				<td class="fieldLabel" width="70%"><b><hdiits:radio value="1" name="radBank" captionid="Pension.currRType" bundle="${pensionMsg}" default="1" onfocus="showHideBankDtls(this);" mandatory="true" validation="sel.isradio"/>&nbsp;&nbsp;
						<hdiits:radio value="0" name="radBank" captionid="Pension.othrRType" bundle="${pensionMsg}" onfocus="showHideBankDtls(this);" validation="sel.isradio" mandatory="true"/>
					</b></td>
				<td class="fieldLabel" width="25%"></td>			
			</tr>
		</table>	
		<table class="tabtable" id="otrBankDtls" style="display:none">		
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.bnkName" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%">
						<hdiits:select captionid="Pension.bnkName" onchange="getAllBranchDtl(this);" bundle="${pensionMsg}" id="bankCmbName" name="bankCmbName" mandatory="true"> 
						<hdiits:option value="Select">--<fmt:message key="Pension.Select" bundle="${pensionEmpFamilyLabel}" />--</hdiits:option>
						<c:forEach var="bankDtls" items="${bankDtls}">
	    					<option value="<c:out value="${bankDtls.bankId}"/>">
							<c:out value="${bankDtls.bankName}"/></option>						
						</c:forEach>
						</hdiits:select>
					</td>				
					<td class="fieldLabel" style="display:none" width="25%"><b><hdiits:caption captionid="Pension.bnkName" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" style="display:none" width="25%">
						<hdiits:text name="bankOther" id="bankOther" mandatory="true" captionid="Pension.bnkName" bundle="${pensionMsg}"></hdiits:text>					
					</td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.branch" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><hdiits:select captionid="Pension.branch" bundle="${pensionMsg}" id="branchCmbName" name="branchCmbName" mandatory="true"> 
						<hdiits:option value="Select">--<fmt:message key="Pension.Select" bundle="${pensionEmpFamilyLabel}" />--</hdiits:option>					
						</hdiits:select>
					</td>
					<td class="fieldLabel" width="25%" style="display:none"><b><hdiits:caption captionid="Pension.branch" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel"  width="25%" style="display:none"><hdiits:text name="branchOther" id="branchOther" mandatory="true" captionid="Pension.branch" bundle="${pensionMsg}"></hdiits:text></td>				
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.accNum" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="otherAccNum" id="otherAccNum" maxlength="20" mandatory="true" captionid="Pension.accNum" bundle="${pensionMsg}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"></td>
					<td class="fieldLabel" width="25%"></td>				
				</tr>
		</table>			
		<table class="tabtable" id="currBankDtls">
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.bnkName" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><c:out value="${userBankData.hrEisBankMst.bankName}"></c:out></td>				
					<td class="fieldLabel" width="25%" style="display:none"><b><hdiits:caption captionid="Pension.bnkName" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%" style="display:none"></td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.branch" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><c:out value="${userBankData.hrEisBranchMst.branchName}"></c:out></td>				
					<td class="fieldLabel" width="25%" style="display:none"><b><hdiits:caption captionid="Pension.branch" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%" style="display:none"></td>				
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.accNum" bundle="${pensionMsg}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="25%"><c:out value="${userBankData.bankAcctNo}"></c:out></td>
					<td class="fieldLabel" width="25%"></td>
					<td class="fieldLabel"width="25%"></td>				
				</tr>
		</table>
	</hdiits:fieldGroup>	
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_req_field3" titleCaptionId="Pension.Declaration" bundle="${pensionMsg}">		
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
					<jsp:param name="formName" value="frmEmpPension" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="Attachment" />			
					<jsp:param name="multiple" value="N" />
					<jsp:param name="removeAttachmentFromDB" value="Y" />
				</jsp:include>
			</td>
		</tr>
	</table>
	<BR>
	<table align="center">
		<tr><td align="center" class="fieldLabel">		
		<hdiits:button type="button" name="btnSubmit" captionid="Button.Submit" bundle="${pensionMsg}" id="btnSubmit" onclick="validatePensionForm();"></hdiits:button>&nbsp;&nbsp;
		<hdiits:button type="button" name="btnClose" captionid="Button.Close" bundle="${pensionMsg}" id="btnClose" onclick="gotoHomePage('frmEmpPension');"></hdiits:button>
		</td></tr>
	</table>
	</div>
	</div>
	<hdiits:hidden id="PermanentAdd" name="PermanentAdd" default="${resValue.permentAdd}"/>
	<hdiits:hidden id="PermanentAddId" name="PermanentAddId" default="${resValue.permentAddId}"/>
	<hdiits:hidden id="PresentAdd" name="PresentAdd" default="${resValue.presentAdd}"/>
	<hdiits:hidden id="PresentAddId" name="PresentAddId" default="${resValue.presentAddId}"/>
	<hdiits:validate locale="${locale}" controlNames="" />
	<script type="text/javascript">	
		initializetabcontent("maintab")
	</script>
</hdiits:form>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
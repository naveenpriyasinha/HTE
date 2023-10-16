<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<fmt:setBundle basename="resources.eis.empMiscellaneousLables" var="MislnusDtlsLables" scope="request" />

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EmpMiscellenousDtl.js"/>"></script>

<script type="text/javascript">
	var miscellenousAlertMsgArr = new Array();
	miscellenousAlertMsgArr[0]='<fmt:message bundle="${MislnusDtlsLables}" key="EIS.MonthsAlert"/>';
	miscellenousAlertMsgArr[1]='<fmt:message bundle="${MislnusDtlsLables}" key="EIS.DateAlert"/>';
	miscellenousAlertMsgArr[2]='<fmt:message bundle="${MislnusDtlsLables}" key="EIS.SalaryAlert"/>';
	miscellenousAlertMsgArr[3]='<fmt:message bundle="${MislnusDtlsLables}" key="EIS.row"/> ';
	miscellenousAlertMsgArr[4]=' :: <fmt:message bundle="${MislnusDtlsLables}" key="EIS.PsprtDtAlert"/>';
	miscellenousAlertMsgArr[5]=" <fmt:message bundle="${MislnusDtlsLables}" key="EIS.DurYrs"/> ";
	miscellenousAlertMsgArr[6]=" <fmt:message bundle="${MislnusDtlsLables}" key="EIS.DurMonth"/>";
	miscellenousAlertMsgArr[7]='<fmt:message bundle="${MislnusDtlsLables}" key="EIS.Salary"/>';
	miscellenousAlertMsgArr[8]='<fmt:message bundle="${MislnusDtlsLables}" key="EIS.licenesDateAlert"/>';
	miscellenousAlertMsgArr[9]=' :: <fmt:message bundle="${MislnusDtlsLables}" key="EIS.licenseDtAlert"/>';
</script>


<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="xmlFileNm" value="${resultValue.xmlFileNm}" />
<c:set var="InsuranceVOList" value="${resultValue.InsuranceVOList}" />
<c:set var="xmlFileNmPassport" value="${resultValue.xmlFileNmPassport}" />
<c:set var="PassportVOList" value="${resultValue.PassportVOList}" />
<c:set var="xmlFileNmLicense" value="${resultValue.xmlFileNmLicense}" />
<c:set var="licenseVOList" value="${resultValue.licenseVOList}" />
<c:set var="xmlFileNmBank" value="${resultValue.xmlFileNmBank}" />
<c:set var="BankVOList" value="${resultValue.BankVOList}" />
<c:set var="MiscellenousInfo" value="${resultValue.arMiscellenousInfo}"></c:set>
<c:set var="BankNameInfo" value="${resultValue.arBankNameInfo}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="PanNo" value="${resultValue.PanNo}" />
<c:set var="acType" value="${resultValue.acType}" />
<c:set var="selectedUserId" value="${resultValue.selectedUserId}"></c:set>
<c:set var="workFlowEnabled" value="${resultValue.blnWorkFlowEnabled}"></c:set>
<c:set var="empFlag" value="${resultValue.empFlag}"></c:set>
<c:set var="lstLicenseType" value="${resultValue.lstLicenseType}"></c:set>

<script>
	var workFlowEnabled = '${workFlowEnabled}'; //IFMS
</script>

<hdiits:form name="EmpMislenousDtls" validate="true" method="POST">


	<!-- start of employee bank Details -->
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	
		<c:if test="${workFlowEnabled eq 'true'}">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="EIS.EmpMislDtl" bundle="${MislnusDtlsLables}"/></b></a></li>
		</c:if>
		
		<c:if test="${workFlowEnabled eq 'false'}">
			<li class="selected"><a href="#" onclick="javascript:openAppWindow('addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false')">
				<b> <fmt:message key="EIS.PersonalDtls" bundle="${MislnusDtlsLables}"/> </b> </a></li>
	
				<li class="selected">
					<a href="#" onclick="javascript:openAppWindow('EducationDtls&flag=getComboDetails&workFlowEnabled=false')">
						<b>
							<fmt:message key="eis.quali_dtls" bundle="${MislnusDtlsLables}"></fmt:message>
						</b>
					</a>
				</li>
				
				<li class="selected" style="width: 152px;">
						<a href="#" style="width: 142px;" onclick="javascript:openAppWindow('CoCurricularDetails&flag=getComboDetails&workFlowEnabled=false')" >
						<b>
							<fmt:message key="eis.extraCoCurricularDtls" bundle="${MislnusDtlsLables}"></fmt:message>
						</b>
					</a>
				</li>
				
				<li class="selected">
					<a href="#" onclick="javascript:openAppWindow('FamilyDetails&flag=getComboDetails&workFlowEnabled=false')">
						<b>
							<fmt:message key="eis.FamilyDtls" bundle="${MislnusDtlsLables}"/>
						</b>
					</a>
				</li>
				
				<li class="selected">
					<a href="#" onclick="javascript:openAppWindow('NomineeDetails&flag=getComboDetails&workFlowEnabled=false')" >
						<b>
							<fmt:message key="eis.nominee_dtls" bundle="${MislnusDtlsLables}" />
						</b>
					</a>
				</li>
				
				<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="EIS.EmpMislDtl" bundle="${MislnusDtlsLables}"/></b></a></li>
		</c:if>
		
	</ul>
	</div>

	<div id="education" name="education">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<table class="tabtable" width="100%" style="display:none" id="errorMessage">
		<tr>
			<td width="35%"></td>
			<td class="fieldLabel" width="40%" colspan="4">
				<hdiits:text name="errorBox" id="errorBox" readonly="readonly" style="color: #E41B17;width:100%;border:0; bgcolor: #FFFFFF; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #FFFFFF;"></hdiits:text>
			</td>
			<td width="25%"></td>
		</tr>
	</table>
	<%@ include file="empInfo/EmployeeInfo.jsp"%>
	
	<hdiits:fieldGroup id="bankDtlsFieldGroupId" titleCaptionId="EIS.BankDtls" bundle="${MislnusDtlsLables}" collapseOnLoad="false">
	<table class="tabtable" width="100%">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="EIS.BankDtls" bundle="${MislnusDtlsLables}" /></u></strong> </font></td>
		</tr>-->
		<tr>
			<td  width="25%"><b><hdiits:caption captionid="EIS.BankName"
				bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:select captionid="EIS.BankName"
				bundle="${MislnusDtlsLables}" name="drodnBankName" sort="true" caption="Bank_Name" validation="sel.isrequired" mandatory="true"
				onchange="showBranchName(this)">
				<hdiits:option value="0">
					<fmt:message key="EIS.Select" bundle="${MislnusDtlsLables}" />
				</hdiits:option>
				<c:forEach var="BankNameInfoVar" items="${BankNameInfo}">
					<hdiits:option value="${BankNameInfoVar.bankTypeCode}">${BankNameInfoVar.bankName}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.Branch" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:select id="BranchName" captionid="EIS.Branch" bundle="${MislnusDtlsLables}" name="drodnBranchName" sort="true"	caption="Branch" validation="sel.isrequired" mandatory="true">
				<hdiits:option value="0"><fmt:message key="EIS.Select" bundle="${MislnusDtlsLables}" />
				</hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.AccNo" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.AccNo" bundle="${MislnusDtlsLables}" name="txtAccNo" caption="Acc_No" onkeypress="return checkDecimalNumber()" validation="txt.isrequired,txt.isnumber" mandatory="true" maxlength="16" style="text-align: right"/></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.TypeOfAcc" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:select captionid="EIS.TypeOfAcc" 
				bundle="${MislnusDtlsLables}" name="drodnTypeOfAcc" sort="false"
				caption="TypeOf_Acc" validation="sel.isrequired" mandatory="true" >
				<hdiits:option value="0">
					<fmt:message key="EIS.Select" bundle="${MislnusDtlsLables}" />
				</hdiits:option>
				<c:forEach var="MiscellenousInfoVar" items="${MiscellenousInfo}">
					<hdiits:option value="${MiscellenousInfoVar.lookupName}">${MiscellenousInfoVar.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		
	     
		<tr>
			<td align="center" colspan="4"><hdiits:button
				name="btnBnkDtlAdd" type="button" captionid="EIS.Add"
						bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdateBankDtls('Add')"></hdiits:button> <hdiits:button
				name="btnBnkDtlUpdate" type="button" captionid="EIS.Update"
						bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdateBankDtls('Update')" style="display:NONE"></hdiits:button> <hdiits:button
				name="btnBnkDtlReset" type="button" captionid="EIS.Reset"
						bundle="${MislnusDtlsLables}"
				onclick="javascript:addOrUpdateBankDtls('Reset')"></hdiits:button> 
			</td>
		</tr>
	</table>
	
	<br>
	
	
	<table id='txnAddBank' name="EmpMisBank" border="1" borderColor="black"
		align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.BankName" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.Branch" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.AccNo" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.TypeOfAcc" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.edtdel" bundle="${MislnusDtlsLables}" /></label></b></td>
		</tr>
	</table>
	
	 <table id="bankNoRecords" align="center">
		 <c:if test="${empty BankVOList}">
	        	<tr></tr><tr></tr>
	        	<tr>
	        		<td align="center">
			        	<b><hdiits:caption captionid="eis.no_app_req" bundle="${MislnusDtlsLables}" /></b>				
			        </td>
			    </tr>
		 </c:if>
	</table>    	
		
	 <c:forEach items="${BankVOList}" var="BankVO" varStatus="x">
		<c:set var="currentXMLFileB" value="${xmlFileNmBank[x.index]}"></c:set>

		<c:set var="BankName" value="${BankVO.hrEisBankMst.bankName}" />
		<c:set var="Branch" value="${BankVO.hrEisBranchMst.branchName}" />
		<c:set var="AccNo" value="${BankVO.bankAcctNo}" />
		<c:set var="AccTypeLong" value="${BankVO.bankAcctType}" />
		<c:set var="AccType" value="${acType}"/>
		<script type="text/javascript">
			var xmlFileNameB = '${currentXMLFileB}';
			var displayFieldArray1  = new Array('${BankName}','${Branch}','${AccNo}','${AccType[AccTypeLong].lookupDesc}');
			addDBDataInTable('txnAddBank','encXMLBank',displayFieldArray1,xmlFileNameB, 'editEmpMiscelBankRecord','deleteDBEmpMiscelBankRecord');
		</script>

	</c:forEach> 
	</hdiits:fieldGroup>
	<!-- start of employee insurance Details -->
	<br>
	
	<hdiits:fieldGroup id="insuranceDtlsFieldGroupId" titleCaptionId="EIS.GrpInsurancePolcyDtls" bundle="${MislnusDtlsLables}" collapseOnLoad="true">
	<table class="tabtable" width="100%">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="EIS.GrpInsurancePolcyDtls" bundle="${MislnusDtlsLables}" /></u></strong>
			</font></td>
		</tr>-->
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.PolcyNo" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.PolcyNo" bundle="${MislnusDtlsLables}" name="txtPolcyNo" caption="Polcy_No" validation="txt.isrequired,txt.isnumber" onkeypress="return checkDecimalNumber()"  mandatory="true" maxlength="16" style="text-align: right"/></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.NameOfPolcy" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.NameOfPolcy" bundle="${MislnusDtlsLables}" name="txtNameOfPolcy" caption="NameOf_Polcy" validation="txt.isrequired"  mandatory="true" maxlength="90"/></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.CmpName" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.CmpName" bundle="${MislnusDtlsLables}" name="txtCmpName" caption="Cmp_Name" validation="txt.isrequired" mandatory="true" maxlength="90"/></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.DtOfPolcy" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.DtOfPolcy" bundle="${MislnusDtlsLables}" name="dtDtOfPolcy" caption="DtOf_Polcy" validation="txt.isdt,txt.isrequired" mandatory="true" /></td>

		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.Duration" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><table><tr>
					<td><hdiits:text name="txtDurationYrs" caption="Dura_tion" size="1" validation="txt.isnumber" onkeypress="return checkDecimalNumber()" maxlength="3" style="text-align: right"/></td>
					<td><b><hdiits:caption captionid="EIS.DurYrs" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
					<td><hdiits:text name="txtDurationMonths" caption="Dura_tion" size="1" onblur="monthComparisonForInsurance()" validation="txt.isnumber" onkeypress="return checkDecimalNumber()" maxlength="2" style="text-align: right"/></td>
				 	<td><b><hdiits:caption captionid="EIS.DurMonth" bundle="${MislnusDtlsLables}" ></hdiits:caption></b></td>
			</tr></table></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.InsuredAmt" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text name="txtInsuredAmt" captionid="EIS.InsuredAmt" bundle="${MislnusDtlsLables}" validation="txt.isnumber" onkeypress="return checkDecimalNumber()"  maxlength="10" style="text-align: right"/></td>
		</tr>
		<tr>
			<td align="center" colspan="4">
				<hdiits:button name="btnInsuranceDtlAdd" type="button" captionid="EIS.Add" bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdateInsuranceDtls('Add')"></hdiits:button>
				<hdiits:button name="btnInsuranceDtlUpdate" type="button" captionid="EIS.Update" bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdateInsuranceDtls('Update')" style="display:NONE"></hdiits:button>
				<hdiits:button name="btnInsuranceDtlReset" type="button" captionid="EIS.Reset" bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdateInsuranceDtls('Reset')"></hdiits:button>
			</td>			
		</tr>
	</table>
	<br>
	
	<table id='txnAddInsurance' name="EmpMisInsurance" border="1"
		borderColor="black" align="center" width="100%" cellpadding="1"
		cellspacing="1"
		style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.PolcyNo" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.NameOfPolcy" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.CmpName" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.DtOfPolcy" bundle="${MislnusDtlsLables}" /></label></b></td>
     		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.Duration" bundle="${MislnusDtlsLables}" /></label></b></td>	
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.InsuredAmt" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.edtdel" bundle="${MislnusDtlsLables}" /></label></b></td>
		</tr>
	</table>
	
	<br>
	<table id="insuranceNoRecords" align="center">
		<c:if test="${empty InsuranceVOList}">
				<tr></tr>
				<tr></tr>
				<tr>
					<td align="center"><b><hdiits:caption captionid="eis.no_app_req" bundle="${MislnusDtlsLables}" /></b></td>
				</tr>
		</c:if>
	</table>

	<c:forEach items="${InsuranceVOList}" var="InsuranceVO" varStatus="x">
		<c:set var="currentXMLFileI" value="${xmlFileNm[x.index]}"></c:set>

		<c:set var="PolicyNo" value="${InsuranceVO.policyNumber}" />
		<c:set var="NameOfPolcy" value="${InsuranceVO.nameOfPolicy}" />
		<c:set var="CompanyName" value="${InsuranceVO.insrcCompanyName}" />
		<fmt:formatDate var="DatetOfPolicy" pattern="dd/MM/yyyy"
			value="${InsuranceVO.dateOfPolicy}" type="date" />
		<c:set var="DatetOfPolicy" value="${DatetOfPolicy}" />
		<c:set var="DurationYears" value="${InsuranceVO.durationYear}" />
		<c:set var="DurationMonth" value="${InsuranceVO.durationMonth}" />
		<c:set var="InsuredAmtount" value="${InsuranceVO.insuredAmount}" />

		<script type="text/javascript">
				var xmlFileNameI = '${currentXMLFileI}';
				var duration = '${DurationYears}'+ " <fmt:message bundle="${MislnusDtlsLables}" key="EIS.DurYrs"/> " +'${DurationMonth}'+" <fmt:message bundle="${MislnusDtlsLables}" key="EIS.DurMonth"/>";
				var displayFieldArray1 = new Array('${PolicyNo}','${NameOfPolcy}','${CompanyName}','${DatetOfPolicy}',duration,'${InsuredAmtount}');
				addDBDataInTable('txnAddInsurance','encXMLInsurance',displayFieldArray1,xmlFileNameI, 'editEmpMiscelInsuranceRecord','deleteDBEmpMiscelInsuranceRecord');
		</script>


	</c:forEach>
	</hdiits:fieldGroup>
	 <!-- end of employee insurance Details --> 
	 
	 <!-- start of employee pan card Details -->
	
	<br>
	<hdiits:fieldGroup id="panDtlsFieldGroupId" titleCaptionId="EIS.PanDtls" bundle="${MislnusDtlsLables}" collapseOnLoad="true">
	<table class="tabtable"  width="100%">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="EIS.PanDtls" bundle="${MislnusDtlsLables}" /></u></strong> </font></td>
		</tr>-->
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="EIS.PanNo" bundle="${MislnusDtlsLables}"></hdiits:caption></b>
			</td>
			<td width="25%">
				<hdiits:text captionid="EIS.PanNo" bundle="${MislnusDtlsLables}" name="txtPanNo" id="txtPanNo" caption="Pan_No" onkeypress="return checkNumNCharNumber()" default='${PanNo}' maxlength="16"/>
			</td>
			<td></td>
			<td></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	<br>
	<!-- end of employee pan card Details --> 
	
	<!-- start of employee passport Details -->

<hdiits:fieldGroup id="passportDtlsFieldGroupId" titleCaptionId="EIS.PasprtDtls" bundle="${MislnusDtlsLables}" collapseOnLoad="true">

	<table class="tabtable" width="100%">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="EIS.PasprtDtls" bundle="${MislnusDtlsLables}" /></u></strong> </font></td>
		</tr>-->
		
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.PasprtNo" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.PasprtNo" bundle="${MislnusDtlsLables}" name="txtPasprtNo" id="txtPasprtNo" caption="Pasprt_No" validation="txt.isrequired" onkeypress="return checkNumNCharNumber()" mandatory="true" maxlength="16"/></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.PasprtIsueDt" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.PasprtIsueDt" bundle="${MislnusDtlsLables}" name="dtPasprtIsueDt" caption="Pasprt_IsueDt" onblur="dateComparisonForPassport()" validation="txt.isdt,txt.isrequired" mandatory="true" /></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.PasprtExpDt" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.PasprtExpDt" bundle="${MislnusDtlsLables}" name="dtPasprtExpDt" caption="Pasprt_ExpDt" onblur="dateComparisonForPassport()" validation="txt.isdt,txt.isrequired" mandatory="true" maxvalue="31/12/2099"/></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.PlaceOfIssue"
				bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text name="txtPlaceOfIssue" caption="PlaceOf_Issue" maxlength="45"/></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.IsungAuth"
				bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text name="txtIsungAuth" caption="Isung_Auth" maxlength="45"/></td>
		</tr>
		
		<tr>
			<td align="center" colspan="4">
			<hdiits:button	name="btnPassportDtlAdd" type="button" captionid="EIS.Add"
						bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdatePassportDtls('Add')"></hdiits:button>
			<hdiits:button name="btnPassportDtlUpdate" type="button"
				captionid="EIS.Update"
						bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdatePassportDtls('Update')" style="display:NONE"></hdiits:button>
			<hdiits:button name="btnPassportDtlReset" type="button" captionid="EIS.Reset"
						bundle="${MislnusDtlsLables}"
				onclick="javascript:addOrUpdatePassportDtls('Reset')"></hdiits:button>
			
		</tr>
	</table>
	
		<br>
	
			
	<table id='txnAddPassport' name="EmpMisPassport" border="1"
		borderColor="black" align="center" width="100%" cellpadding="1"
		cellspacing="1"
		style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label>
			<hdiits:caption captionid="EIS.PasprtNo"
				bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.PasprtIsueDt" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.PasprtExpDt" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.PlaceOfIssue" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.IsungAuth" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.edtdel" bundle="${MislnusDtlsLables}" /></label></b></td>
		</tr>
	</table>
	<br>
	
	<table id="passportNoRecords" align="center">
		<c:if test="${empty PassportVOList}">
				<tr></tr><tr></tr>
				<tr>
					<td align="center"><b><hdiits:caption captionid="eis.no_app_req"	bundle="${MislnusDtlsLables}" /></b></td>
				</tr>
		</c:if>
	</table>
	
	<c:forEach items="${PassportVOList}" var="PassportVO" varStatus="x">
		<c:set var="currentXMLFileP" value="${xmlFileNmPassport[x.index]}"></c:set>
		<c:set var="PassportNo" value="${PassportVO.proofNum}" />
		<fmt:formatDate var="IssueDate" pattern="dd/MM/yyyy" value="${PassportVO.issueDate}" type="date" />
				<c:set var="IssueDate" value="${IssueDate}" />	
		<fmt:formatDate var="ExpiryDate" pattern="dd/MM/yyyy" value="${PassportVO.expiryDate}" type="date" />	
				<c:set var="ExpiryDate" value="${ExpiryDate}" />
		<c:set var="IssuePlace" value="${PassportVO.issuePlace}" />
		<c:set var="IssueAuthority" value="${PassportVO.issueAuthority}" />
		
			<script type="text/javascript">
				var issuePlace='${IssuePlace}';
				var issueAuthority='${IssueAuthority}';
				if(issuePlace=='' || issuePlace==null){issuePlace='-';}
				if(issueAuthority=='' || issueAuthority==null){issueAuthority='-';}
				var xmlFileNameP = '${currentXMLFileP}';
				var displayFieldArrayP  = new Array('${PassportNo}','${IssueDate}','${ExpiryDate}',issuePlace,issueAuthority);
				addDBDataInTable('txnAddPassport','encXMLPassport',displayFieldArrayP,xmlFileNameP, 'editEmpMiscelPassportRecord','deleteDBEmpMiscelPassportRecord');
			</script>
	</c:forEach> 
</hdiits:fieldGroup>
	<br>
		
	
	<hdiits:fieldGroup id="licenseDtlsFieldGroupId" titleCaptionId="EIS.licenseDtls" bundle="${MislnusDtlsLables}" collapseOnLoad="true">

	<table class="tabtable" width="100%">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.licenseNo" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.licenseNo" bundle="${MislnusDtlsLables}" name="txtLicenseNo" id="txtLicenseNo" validation="txt.isrequired" onkeypress="return checkNumNCharNumber()" mandatory="true" maxlength="16"/></td>

			<td  width="25%"><b><hdiits:caption captionid="EIS.licenseType" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%">
				<hdiits:select captionid="EIS.licenseType"	bundle="${MislnusDtlsLables}" name="drodnLicenseType" sort="false" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${MislnusDtlsLables}" />
					</hdiits:option>
					<c:forEach var="lstLicenseTypeVar" items="${lstLicenseType}">
						<hdiits:option value="${lstLicenseTypeVar.lookupName}">${lstLicenseTypeVar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.licenseIsueDt" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.licenseIsueDt" bundle="${MislnusDtlsLables}" name="dtLicenseIsueDt" caption="License_IsueDt" onblur="dateComparisonForLicense()" validation="txt.isdt,txt.isrequired" mandatory="true" /></td>
			<td width="25%"><b><hdiits:caption captionid="EIS.licenseExpDt" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.licenseExpDt" bundle="${MislnusDtlsLables}" name="dtLicenseExpDt" caption="License_ExpDt" onblur="dateComparisonForLicense()" validation="txt.isdt,txt.isrequired" mandatory="true" maxvalue="31/12/2099"/></td>
		</tr>
		
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.PlaceOfIssue"	bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text name="txtPlaceOfIssueLicense" caption="PlaceOf_Issue_License" maxlength="45"/></td>
			
			<td width="25%"><b><hdiits:caption captionid="EIS.IsungAuth" bundle="${MislnusDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text name="txtLicenseIsungAuth" caption="Isung_Auth_License" maxlength="45"/></td>
		</tr>
		
		<tr>
			<td align="center" colspan="4">
			<hdiits:button	name="btnLicenseDtlAdd" type="button" captionid="EIS.Add" bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdateLicenseDtls('Add')"></hdiits:button>
			<hdiits:button name="btnLicenseDtlUpdate" type="button" captionid="EIS.Update"	bundle="${MislnusDtlsLables}" onclick="javascript:addOrUpdateLicenseDtls('Update')" style="display:NONE"></hdiits:button>
			<hdiits:button name="btnLicenseDtlReset" type="button" captionid="EIS.Reset" bundle="${MislnusDtlsLables}"	onclick="javascript:addOrUpdateLicenseDtls('Reset')"></hdiits:button>
			
		</tr>
		
	</table>
	<br>
			
	<table id='txnAddLicense' border="1" borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="EIS.licenseNo" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.licenseType" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.licenseIsueDt" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.licenseExpDt" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.PlaceOfIssue" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.IsungAuth" bundle="${MislnusDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.edtdel" bundle="${MislnusDtlsLables}" /></label></b></td>
		</tr>
	</table>
	<br>
	
	<table id="licenseNoRecords" align="center">
		<c:if test="${empty licenseVOList}">
				<tr></tr><tr></tr>
				<tr>
					<td align="center"><b><hdiits:caption captionid="eis.no_app_req" bundle="${MislnusDtlsLables}" /></b></td>
				</tr>
		</c:if>
	</table>
	
	<c:forEach items="${licenseVOList}" var="licenseVO" varStatus="x">
		<c:set var="currentXMLFileLicense" value="${xmlFileNmLicense[x.index]}"></c:set>
		<c:set var="licenseNo" value="${licenseVO.proofNum}" />
		<fmt:formatDate var="IssueDate" pattern="dd/MM/yyyy" value="${licenseVO.issueDate}" type="date" />
				<c:set var="IssueDate" value="${IssueDate}" />	
		<fmt:formatDate var="ExpiryDate" pattern="dd/MM/yyyy" value="${licenseVO.expiryDate}" type="date" />	
				<c:set var="ExpiryDate" value="${ExpiryDate}" />
		<c:set var="IssuePlace" value="${licenseVO.issuePlace}" />
		<c:set var="IssueAuthority" value="${licenseVO.issueAuthority}" />
		<c:set var="LicenseType" value="${licenseVO.cmnLookupMstByLicenseType.lookupDesc}" />
		
			<script type="text/javascript">
				var issuePlace='${IssuePlace}';
				var issueAuthority='${IssueAuthority}';
				if(issuePlace=='' || issuePlace==null){issuePlace='-';}
				if(issueAuthority=='' || issueAuthority==null){issueAuthority='-';}
				var xmlFileNameLicense = '${currentXMLFileLicense}';
				var displayFieldArrayLicense  = new Array('${licenseNo}','${LicenseType}','${IssueDate}','${ExpiryDate}',issuePlace,issueAuthority);
				addDBDataInTable('txnAddLicense','encXMLLicense',displayFieldArrayLicense,xmlFileNameLicense, 'editEmpMiscelLicenseRecord','deleteDBEmpMiscelLicenseRecord');
			</script>
	</c:forEach>
</hdiits:fieldGroup>

	
	<br>
	<table align="center">
		<hdiits:button name="btnInsuranceDtlSubmitInDB" type="button" captionid="EIS.Submit" bundle="${MislnusDtlsLables}" onclick="submitInDb()"></hdiits:button>
		<hdiits:button name="btnInsuranceDtlCancelInDB" type="button" captionid="EIS.Cancel" bundle="${MislnusDtlsLables}" onclick="closeWindow()"></hdiits:button>
	</table>
	
	<!-- end of employee passport Details -->
	</div>
	</div>
	</div>
	
	<hdiits:validate locale="${locale}" controlNames="drodnBankName,drodnBranchName,txtAccNo,drodnTypeOfAcc,txtPolcyNo,txtNameOfPolcy,txtCmpName,dtDtOfPolcy,txtDurationYrs,txtDurationMonths,txtInsuredAmt,txtPasprtNo,dtPasprtIsueDt,dtPasprtExpDt"/> 
	
	<script type="text/javascript">
		initializetabcontent("maintab");
		document.EmpMislenousDtls.dtDtOfPolcy.readOnly = true;
		document.EmpMislenousDtls.dtPasprtIsueDt.readOnly = true;
		document.EmpMislenousDtls.dtPasprtExpDt.readOnly = true;
		
		document.EmpMislenousDtls.dtLicenseIsueDt.readOnly = true;
		document.EmpMislenousDtls.dtLicenseExpDt.readOnly = true;
		
		var rrr='${acType}';
		
		//changed by sandip - start
		var empFlag='${empFlag}';
		var str='<fmt:message bundle="${MislnusDtlsLables}" key="EIS.empMstAlert"/>';
		if(!empFlag ||empFlag =='')
		{
			document.getElementById('errorMessage').style.display='';				
			document.getElementById('errorBox').value=str;
			document.EmpMislenousDtls.btnInsuranceDtlSubmitInDB.disabled=true;	
		}
		var ObjPanNo = document.getElementById('txtPanNo');
		ObjPanNo.onkeyup=function(e)
		{
			document.getElementById('txtPanNo').value = document.getElementById('txtPanNo').value.toUpperCase();
		}
		var ObjPassportNo = document.getElementById('txtPasprtNo');
		ObjPassportNo.onkeyup=function(e)
		{
			document.getElementById('txtPasprtNo').value = document.getElementById('txtPasprtNo').value.toUpperCase();
		}

		var ObjLicenseNo = document.getElementById('txtLicenseNo');
		ObjLicenseNo.onkeyup=function(e)
		{
			document.getElementById('txtLicenseNo').value = document.getElementById('txtLicenseNo').value.toUpperCase();
		}
		//changed by sandip - end
	</script>

	<hdiits:hidden name="hdnInsuranceId" id="hdnInsuranceId" />
	<hdiits:hidden name="hdnBankId" id="hdnBankId" />
	<hdiits:hidden name="hdnPassportId" id="hdnPassportId" />
	<hdiits:hidden name="hdnLicenseId" id="hdnLicenseId" />
	<hdiits:hidden name="hdnDuration" id="hdnDuration" />
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"/>
	
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

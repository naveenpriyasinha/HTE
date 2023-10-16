
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="expense" value="${resultValue.nocExp}"></c:set>
<c:set var="occupation" value="${resultValue.Occupation}"></c:set>
<c:set var="isInboxExpJsp" value="${resultValue.isInboxExpJsp}"></c:set> <!-- added for NOC CR 13 may-->
<c:set var="arSalutation" value="${resultValue.arSalutation}"></c:set> <!-- added for NOC CR 27 may-->
<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request"/>
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request"/>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/Expenditure.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/SearchCountry.js"/>"></script>		
<script language="javascript">
 var expenditureAlert = new Array();
 expenditureAlert[0]='<fmt:message bundle="${NOCAlerts}" key="NOC.AjaxAlert"/>';
 expenditureAlert[1]='<fmt:message bundle="${NOCAlerts}" key="NOC.OrgExists"/>';
 expenditureAlert[2]='<fmt:message bundle="${NOCAlerts}" key="NOC.VAddress"/>';
 expenditureAlert[3]='<fmt:message bundle="${NOCLables}" key="NOC.AddNew"/>';
 var inboxPage ='${isInboxExpJsp}';
  	
  	
 	var BankDtls;
	function getSelectedBankDtl(selectedBankDtls)
	{
	     
		BankDtls = selectedBankDtls.split("°");
		document.getElementById("selBearerBank").value = BankDtls[0]; // hrEisBankDtlId
		
		if(inboxPage== 'false')
		{
			document.getElementById("name_selBearerBank").value = BankDtls[1]; // bank name	
		}
		 
		document.getElementById("txtBranch").value = BankDtls[2]; // branch
		document.getElementById("txtAcctType").value = BankDtls[3]; // acct type
		document.getElementById("txtAcctNo").value = BankDtls[4]; // acct no.
		document.getElementById("displayBankDtls").style.display = '';
	}
  	
  	
</script>	
<c:if test="${isInboxExpJsp eq false}">
	<table width="100%" id="displayExpenditureType">	
		<tr>	
			<td width="20%"><hdiits:caption captionid="NOC.HowBearExpense" bundle="${NOCLables}"/></td>		
			<td width="15%"><hdiits:select name="selBearer" id="selBearer" captionid="NOC.HowBearExpense" bundle="${NOCLables}" size="1" sort="false" onchange="showExpenditureName(this, false, 1, 0)"  validation="sel.isrequired"  mandatory="true">
				<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>
						<c:forEach var="expenseValue" items="${expense}">
							<hdiits:option value="${expenseValue.lookupName}">${expenseValue.lookupDesc}</hdiits:option>
						</c:forEach>
				</hdiits:select>
			</td>				
			<td width="18%">
				<div id="displayExpenditureName" style="display:none"> 
					<hdiits:select name="selBearerName"  id="selBearerName" captionid="NOC.HowBearExpense" bundle="${NOCLables}" size="1" onchange="addExpenditure()" validation="sel.isrequired" mandatory="true">
						<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>					
					</hdiits:select>
				<hdiits:hidden id="txtExpBearerPk" name="txtExpBearerPk" caption="ExpBearerPk" />
				</div> 
			</td> 		
			<td width="20%" ><b><label class=captionTag ><div id="displayAmtLabel"><hdiits:caption captionid="NOC.Amount" bundle="${NOCLables}"/></div></label></b></td>
			<td ><div id="displayAmt"><hdiits:text name="numCategorisedAmount" id="numCategorisedAmount" captionid="NOC.Amount" bundle="${NOCLables}" validation="txt.isrequired"  mandatory="true" maxlength="10"  onkeypress="return checkNumberForOnlyOneDot(this.value)" style="text-align: right" onblur="fixDecimalPt();"/></div></td>			
		</tr>
	</table>
	<!-- added for NOC CR(starts) -->
	<table width="100%" id="displaySelBearerBank" style="display:none">
		<tr >
			<td width="18%"><hdiits:caption captionid="NOC.SelBank" bundle="${NOCLables}"/></td>
			<td width="33%"><hdiits:search name="selBearerBank" id="selBearerBank" url="hrms.htm?actionFlag=searchBearerBank" captionid="NOC.SelBank" bundle="${NOCLables}" readonly="true" /></td>
			<td width="20%"></td>
			<td width="19%"></td>
		</tr>
	</table>
</c:if>
<c:if test="${isInboxExpJsp eq true}">
	<table width="100%" id="displayExpenditureType">	
		<tr>	
			<td width="20%"><hdiits:caption captionid="NOC.HowBearExpense" bundle="${NOCLables}"/></td>		
			<td width="15%"><hdiits:select name="selBearer" id="selBearer" captionid="NOC.HowBearExpense" bundle="${NOCLables}" size="1" sort="false" onchange="showExpenditureName(this, false, 1, 0)">
				<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>
						<c:forEach var="expenseValue" items="${expense}">
							<hdiits:option value="${expenseValue.lookupName}">${expenseValue.lookupDesc}</hdiits:option>
						</c:forEach>
				</hdiits:select>
			</td>				
			<td width="18%">
				<div id="displayExpenditureName" style="display:none"> 
					<hdiits:select name="selBearerName"  id="selBearerName" captionid="NOC.HowBearExpense" bundle="${NOCLables}" size="1" onchange="addExpenditure()">
						<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>					
					</hdiits:select>
				<hdiits:hidden id="txtExpBearerPk" name="txtExpBearerPk" caption="ExpBearerPk" />
				</div> 
			</td> 		
			<td width="20%" ><b><label class=captionTag ><div id="displayAmtLabel"><hdiits:caption captionid="NOC.Amount" bundle="${NOCLables}"/></div></label></b></td>
			<td ><div id="displayAmt"><hdiits:text name="numCategorisedAmount" id="numCategorisedAmount" captionid="NOC.Amount" bundle="${NOCLables}" maxlength="10"  onkeypress="return checkNumberForOnlyOneDot(this.value)" style="text-align: right" onblur="fixDecimalPt();"/></div></td>			
		</tr>
	</table>
	<!-- added for NOC CR(starts) -->
	<table width="100%" id="displaySelBearerBank" style="display:none">
		<tr >
			<td width="18%"><hdiits:caption captionid="NOC.SelBank" bundle="${NOCLables}"/></td>
			<td width="33%"><hdiits:text name="selBearerBank" id="selBearerBank" captionid="NOC.SelBank" bundle="${NOCLables}" readonly="true"/></td>
			<td width="20%"></td>
			<td width="19%"></td>
		</tr>
	</table>
</c:if>
<div id="displayBankDtls" style="display:none">

	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.BranchName" bundle="${NOCLables}"/></td>
			<td width="33%"><hdiits:text name="txtBranch" id="txtBranch" captionid="NOC.BranchName" bundle="${NOCLables}" readonly="true"/></td>			
			<td width="20%"><hdiits:caption captionid="NOC.AcctType" bundle="${NOCLables}"/></td>
			<td><hdiits:text name="txtAcctType" id="txtAcctType" captionid="NOC.AcctType" bundle="${NOCLables}" readonly="true"/></td>
		</tr>
	</table>
	
	<table width="100%">
		<tr >
			<td width="20%"><hdiits:caption captionid="NOC.AcctNo" bundle="${NOCLables}"/></td>
			<td width="33%"><hdiits:text name="txtAcctNo" id="txtAcctNo" captionid="NOC.AcctNo" bundle="${NOCLables}" readonly="true" style="text-align: right"/></td>
			<td></td>
			<td></td>
		</tr>
	</table>

</div>

<!-- added for NOC CR(ends) -->
<fieldset style="background: #E3E3E3;" id="displayBearerDetails" style="display:NONE">

	<legend><hdiits:caption captionid="NOC.ExpeBearer" bundle="${NOCLables}"/></legend>
	
	<table width="100%">
		<tr>
			<td>
		
			<table width="100%" id="displayPersonExpenditure">
				<tr>
					<td width="20%"><hdiits:caption captionid="NOC.Salutation" bundle="${NOCLables}"/></td>
					<td width="20%"><hdiits:select name="selSalutation"  id="selSalutation" captionid="NOC.Salutation" bundle="${NOCLables}" size="1" sort="false" validation="sel.isrequired" mandatory="true">
										<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>
												<c:forEach var="salutationValue" items="${arSalutation}">
													<hdiits:option value="${salutationValue.lookupName}">${salutationValue.lookupDesc}</hdiits:option>
												</c:forEach>
									</hdiits:select>
					</td>
				</tr>
			       	<tr>
						<td width="20%"><hdiits:caption captionid="NOC.FName" bundle="${NOCLables}"/></td>
						<td width="20%"><hdiits:text id="txtBearerFName" name="txtBearerFName" captionid="NOC.FName" bundle="${NOCLables}" maxlength="20" validation="txt.isrequired,txt.isname" mandatory="true"/></td>
						<td  width="20%"><hdiits:caption captionid="NOC.MName" bundle="${NOCLables}"/></td>
						<td><hdiits:text id="txtBearerMName" name="txtBearerMName" maxlength="20" captionid="NOC.MName" bundle="${NOCLables}" validation="txt.isname"/></td>
					</tr>
					<tr>	
						<td><hdiits:caption captionid="NOC.LName" bundle="${NOCLables}"/></td>
						<td><hdiits:text id="txtBearerLName" name="txtBearerLName" maxlength="20" captionid="NOC.LName" bundle="${NOCLables}" validation="txt.isrequired,txt.isname" mandatory="true"/></td>
					</tr>
		   </table>
		   
		   <table width="100%" id="displayOrgExpenditure">
		   		<tr>
					<td width="20%"><hdiits:caption captionid="NOC.OrgName" bundle="${NOCLables}"/></td>
					<td width="25%"><hdiits:text id="txtBearerOrgName" name="txtBearerOrgName" captionid="NOC.OrgName" bundle="${NOCLables}" validation="txt.isrequired" mandatory="true" maxlength="50"/></td>
					<td></td>
					<td></td>
				</tr>	
		   </table>	
		   	   
		   <table width="80%" id="displayBearerAddrDetails">
				<tr>
					<td>	
					<jsp:include page="../../../common/address.jsp">
						<jsp:param name="addrName" value="expBearerAddress" />
						<jsp:param name="addressTitle" value="" />
						<jsp:param name="addrLookupName" value="Permanent Address" />
						<jsp:param name="mandatory" value="Y" />						
					</jsp:include>
					</td>
				</tr>
			</table>
						
			<table width="100%" id="displayBearerOtherDetails1" >
				<tr>		
					<td width="18"><hdiits:caption captionid="NOC.Occupation" bundle="${NOCLables}"/></td>
					<td width="20%"><hdiits:select name="selOccupation"  id="selOccupation" captionid="NOC.Occupation" bundle="${NOCLables}" size="1" sort="false">
										<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>
												<c:forEach var="occupationValue" items="${occupation}">
													<hdiits:option value="${occupationValue.lookupName}">${occupationValue.lookupDesc}</hdiits:option>
												</c:forEach>
									</hdiits:select>
					</td>
					<td width="20%"><hdiits:caption captionid="NOC.Known" bundle="${NOCLables}"/></td>
					<td><hdiits:dateTime  name="dtKnownSince" captionid="NOC.Known" bundle="${NOCLables}"/></td>				
				</tr>
				<tr><td width="20%"></td><td width="20%"></td><td width="20%"></td><td></td></tr>
				
			</table>
			
			
		</td>
		</tr>
		<tr>
			<td>
				<table align="center">	
					<tr>
						<td>
							<hdiits:button type="button" name="Save" captionid="NOC.SaveDetails" bundle="${NOCLables}" onclick="saveBearerInfo()"/>
			        	</td>
			        </tr>
				</table>
			</td>
		</tr>
		
	</table>
	<br>	
</fieldset>	
<table width="100%" id="displayBearerOfficeDeling" style="display: none;" >
				<tr>
					<td width="20%"><hdiits:caption captionid="NOC.OfficialDealing" bundle="${NOCLables}"/>
					<td width="20%"><hdiits:radio name="rdoOfficialDealing" value="Y" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showDealDetail();"/>
						<hdiits:radio name="rdoOfficialDealing" value="N" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hideDealDetail();"/></td>
					<td width="19%"><div id="DealDetail1" style="display:none"><hdiits:caption captionid="NOC.DealDetail" bundle="${NOCLables}"/></div></td>
					<td><div id="DealDetail2" style="display:none"><hdiits:textarea id="txtaDealDetail" name="txtaDealDetail" caption="txtaDealDetail" rows="3" cols="30" maxlength="200"/></div></td>
				</tr>
			</table>	
<hdiits:validate locale="${locale}" controlNames="txtBearerFName,txtBearerLName,txtBearerOrgName" />


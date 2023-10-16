
<%@ include file="../../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>
<%
System.out.println("In budDtls.jsp");
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
		.tabstyle 
		{
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid;
		}
		legend 
		{
			padding-left:5px;
			padding-right:5px;
			font-weight:normal; 
			font-family:verdana;
				
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid;
		}	
	</style>
	<script type="text/javascript">
	onerror=handleErr
		function handleErr()
		{
		//Handle the error here
		window.status="Done";
		return true;
		}
		var lookupArray = new Array();
		var glDDOExpAmt = 0.0;
		var NetTotal = 0.0;
		
		var BUD_CMBBUDTYPE = "<fmt:message key="BUD.CMBBUDTYPE" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTDMNDNO = "<fmt:message key="BUD.TXTDMNDNO" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTMAJHD = "<fmt:message key="BUD.TXTMAJHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTSUBMAJHD = "<fmt:message key="BUD.TXTSUBMAJHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTMINHD = "<fmt:message key="BUD.TXTMINHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTSUBHD = "<fmt:message key="BUD.TXTSUBHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTDTLHD = "<fmt:message key="BUD.TXTDTLHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBCLSEXP = "<fmt:message key="BUD.CMBCLSEXP" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBFUND = "<fmt:message key="BUD.CMBFUND" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTSCHNO = "<fmt:message key="BUD.TXTSCHNO" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTHDCHRG = "<fmt:message key="BUD.TXTHDCHRG" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBCATEGORY = "<fmt:message key="BUD.CMBCATEGORY" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBBILLCODE = "<fmt:message key="BUD.CMBBILLCODE" bundle="${onlinebillprepAlerts}"></fmt:message>";
		
		function isValidateGrantDtls()
		{
			return validateGrantDtls();
		}
		function validateBudDtls()
		{
			return  isValidateBudDtls();
		}
		function window_new_open()
		{
			if(document.getElementById("cmbBudType").value != "-1")
			{
				var newWindow = null;
		    	var x = 150;
		    	var y = 150;
		    	var urlstring = "ifms.htm?actionFlag=getGrantHeadsForDDO&cmbBudType=" + document.getElementById("cmbBudType").value;
		    	var urlstyle = "height=300,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=" + x + ",left=" + y;
		    	newWindow = window.open(urlstring, "frmDdoHeadDtlsPopup", urlstyle);
		    }
		    else
		    {
		    	alert(BUD_CMBBUDTYPE);
		    }
		}
		
		
	</script>
</head>
	
	<c:if test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
		<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	</c:if>
	<c:if test="${resValue.EditBill != null}">
		<c:set var="billCatDisabled" scope="page" value="disabled='disabled'"></c:set>
	</c:if>
			
	<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> Budget Details </b> </legend>
		<table align="center" width="100%">
			<hdiits:tr>	
				<td colspan="4">
					<br />
				</td>
			</hdiits:tr>
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="BUD.CLSEXP" bundle="${onlinebillprepLabels}"></fmt:message>
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<select name="cmbClsOfExp" style="width:155px" ${varDisabled} ${billCatDisabled}>
						<option value="-1"> --Select-- </option>
						 <c:forEach var="ClassOfExp" items="${resValue.ClassOfExpCombo}">
						  <c:if test="${ClassOfExp.lookupName =='Charged'}">
								<option value="${ClassOfExp.lookupName}" selected="true"> <c:out value="${ClassOfExp.lookupDesc}"></c:out> </option>
							</c:if>
						 
							 <c:if test="${resValue.Selected_ExpCls != null && ClassOfExp.lookupName == resValue.Selected_ExpCls.Value}">
								<option value="${ClassOfExp.lookupName}" selected="true"> <c:out value="${ClassOfExp.lookupDesc}"></c:out> </option>
							</c:if>
							<c:if test="${ClassOfExp.lookupName != resValue.Selected_ExpCls.Value}">
								<option value="${ClassOfExp.lookupName}"> <c:out value="${ClassOfExp.lookupDesc}"></c:out> </option>
							</c:if>
						</c:forEach>					
					</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					<input type="hidden" name="digi2_0_CLS_EXP" />
				</hdiits:td>
				<td align="left" class="Label">
					<fmt:message key="BUD.FUND" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<select name="cmbFund" style="width:155px" ${varDisabled}  ${billCatDisabled}>
						<option value="-1"> --Select-- </option>
						<c:forEach var="fund" items="${resValue.FundCombo}">
							<c:if test="${fund.lookupName == '4'}">
								<option value="${fund.lookupName}" selected="true"> <c:out value="${fund.lookupDesc}"></c:out> </option>
							</c:if>	
						
							<c:if test="${resValue.Selected_Fund != null && fund.lookupName == resValue.Selected_Fund.Value}">
								<option value="${fund.lookupName}" selected="true"> <c:out value="${fund.lookupDesc}"></c:out> </option>
							</c:if>	
							<c:if test="${fund.lookupName != resValue.Selected_Fund.Value}">
								<option value="${fund.lookupName}"> <c:out value="${fund.lookupDesc}"></c:out> </option>
							</c:if>	
						</c:forEach>
					</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					<input type="hidden" name="digi2_0_FUND" />
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="BUD.TYPE.CODE" bundle="${onlinebillprepLabels}"></fmt:message>
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<select id="cmbBudType" name="cmbBudType"   onchange="setText(this)"  ${varDisabled}  ${billCatDisabled}>
							<option value="-1"> --Select-- </option>
							<script type="text/javascript">
								lookupArray[0] = "--Select--";
							</script>	
							<c:forEach var="BudType" items="${resValue.BudTypeCombo}" varStatus="No">
								<c:choose>
								<c:when test="${BudType.lookupName == 'State_Nonplan'}">
										<option selected="true" value="${BudType.lookupName}"> <c:out value="${BudType.lookupShortName}"></c:out> </option>								
								</c:when>
									<c:when test="${resValue.Selected_BudType != null && BudType.lookupName == resValue.Selected_BudType.Value}">
										<option selected="selected" value="${BudType.lookupName}"> <c:out value="${BudType.lookupShortName}"></c:out> </option>								
									</c:when>								
									<c:otherwise>
										<option value="${BudType.lookupName}"> <c:out value="${BudType.lookupShortName}"></c:out> </option>
									</c:otherwise>
								</c:choose>
								<script type="text/javascript">
									lookupArray[${No.count}] = "${BudType.lookupDesc}";
								</script>							
							</c:forEach>													
					</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					<input type="hidden" name="digi2_0_BUD_TYPE" />
				</hdiits:td>
				<td align="left" class="Label">
					<fmt:message key="BUD.TYPE.DESC" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">			
					:&nbsp;<c:if test="${resValue.Selected_BudType == null}">
								<textarea name="txtBudDesc" rows="5" cols="22" readonly="readonly" ${varDisabled}><c:out value="--Select--"></c:out> </textarea>
							</c:if>	
							<c:if test="${resValue.Selected_BudType != null}">
								<textarea name="txtBudDesc"  rows="5" cols="22" readonly="readonly" ${varDisabled}><c:out value="${resValue.Selected_BudType.Desc}"></c:out> </textarea>
							</c:if>	
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="center" colspan="4" class="Label">
					<c:choose>
						<c:when test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
						</c:when>
						<c:otherwise>
							<b> <fmt:message key="BUD.DDO" bundle="${onlinebillprepLabels}"></fmt:message> </b>
							<!-- comment added by Ankit Bhatt for Removing Image Icon for DDO -->
							<!-- &nbsp;&nbsp;<img src="images/icon_search.gif" onclick="window_new_open()" /> -->
							<!-- comment Ended by Ankit Bhatt -->
						</c:otherwise>
					</c:choose>
				</td>
			</hdiits:tr>
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="BUD.SCHNO" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">
				<!-- Changed by Ankit bhatt for Payroll -->
				<%--
						:&nbsp;&nbsp;<input type="text" id="txtSchemeNo"  ${billCatDisabled} name="txtSchemeNo" maxlength="6" ${varDisabled} onkeypress="NumberFormet()" onblur="getValidHeadCode(this, 6),getDDOHeadBySchemeNo()" value="${resValue.TrnBillBudheadDtls.schemeNo}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				                 <input type="hidden" name="digi2_0_SCHEME_NO" />
				   --%>
				   	:&nbsp;&nbsp;<input type="text" id="txtSchemeNo"  ${billCatDisabled} name="txtSchemeNo" maxlength="6" 
				   	${varDisabled} onkeypress="NumberFormet()" onblur="getValidHeadCode(this, 6),getDDOHeadBySchemeNo()" 
				   	value="0000000" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				                 <input type="hidden" name="digi2_0_SCHEME_NO" />
				</hdiits:td>
				<td align="left" class="Label">
					<fmt:message key="CMN.DEMAND" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">
					 :&nbsp;&nbsp;<input type="text" id="Demand" name="txtDmd" maxlength="3" ${varDisabled}  ${billCatDisabled} onblur="getValidHeadCode(this, 3)" onkeypress="NumberFormet()" onchange="cleanHeadDtls(this)" value="${resValue.TrnBillBudheadDtls.dmndNo}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
								   <input type="hidden" name="digi2_0_DMND_NO" />
								   <input type="hidden" name="digi3_0_DEMAND_CODE" />
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="CMN.MJRHD" bundle="${onlinebillprepLabels}"></fmt:message>
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<input type="text" id="MjHd" name="txtMjrHd" maxlength="4" ${varDisabled}  ${billCatDisabled} onblur="getValidHeadCode(this, 4)" onkeypress="NumberFormet()" onchange="cleanHeadDtls(this)" value="${resValue.TrnBillBudheadDtls.budMjrHd}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
								  <input type="hidden" name="digi2_0_BUD_MJR_HD" />
								  <input type="hidden" name="digi3_0_BUDMJR_HD" />
				</hdiits:td>
				<td align="left" class="Label">
					<fmt:message key="CMN.SUBMJRHD" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<input type="text" id="SubMjHd" name="txtSbMjrHd" maxlength="2" ${varDisabled}  ${billCatDisabled} onblur="getValidHeadCode(this, 2)" onkeypress="NumberFormet()" onchange="cleanHeadDtls(this)" value="${resValue.TrnBillBudheadDtls.budSubmjrHd}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					 			 <input type="hidden" name="digi2_0_BUD_SUBMJR_HD" />
				</hdiits:td>
			</hdiits:tr>	
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="CMN.MINHD" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<input type="text" id="MnrHd" name="txtMnrHd" maxlength="3" ${varDisabled}  ${billCatDisabled} onblur="getValidHeadCode(this, 3)" onkeypress="NumberFormet()" onchange="cleanHeadDtls(this)" value="${resValue.TrnBillBudheadDtls.budMinHd}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
								 <input type="hidden" name="digi2_0_BUD_MIN_HD"/>
				</hdiits:td>
				<td align="left" class="Label">
					<fmt:message key="CMN.SUBHD" bundle="${onlinebillprepLabels}"></fmt:message> 
			 	</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<input type="text" id="SubHd" name="txtSbHd" maxlength="2" ${varDisabled}  ${billCatDisabled} onblur="getValidHeadCode(this, 2)" onkeypress="NumberFormet()" onchange="cleanHeadDtls(this)" value="${resValue.TrnBillBudheadDtls.budSubHd}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					  			 <input type="hidden" name="digi2_0_BUD_SUB_HD"/>
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="BUD.DTLSHD" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<input type="text" id="DtlHd" name="txtDtldHd" maxlength="2" ${varDisabled}  ${billCatDisabled} onblur="getValidHeadCode(this, 2),getSchemeNoByDDOGrantHead()" onkeypress="NumberFormet()" value="${resValue.TrnBillBudheadDtls.budDtlHd}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					  			 <input type="hidden" name="digi2_0_BUD_DTL_HD"/>
				</hdiits:td>
				<td align="left" class="Label">
					<fmt:message key="BUD.HDCHRG" bundle="${onlinebillprepLabels}"></fmt:message>
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;<input type="text" id="HdChrgble" name="txtHdChrgble" ${varDisabled}  ${billCatDisabled} onfocus="getHdChrgble()" onkeypress="NumberFormet()" value="${resValue.TrnBillBudheadDtls.headChrg}" class="texttag mandatorycontrol" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					 			<input type="hidden" name="digi2_0_HEAD_CHRG"/>	
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="CNTR.EXEMPTED" bundle="${billprocLabels}"></fmt:message>?
				</td>
				<hdiits:td align="left">
					:&nbsp;&nbsp;
					<c:choose>
						<c:when test="${resValue.EditBill != null}">
							<c:choose>
								<c:when test="${resValue.TrnBillRegister != null && resValue.TrnBillRegister.exempted == 'Y'}">
									<input type="radio" name="radExempted"  checked="checked" value="Y" ${varDisabled} onclick="showExempt(this)" />Yes
									<input type="radio" name="radExempted" value="N" ${varDisabled} onclick="showExempt(this)" />No
								</c:when>
								<c:otherwise>
									<input type="radio" name="radExempted" value="Y" ${varDisabled} onclick="showExempt(this)" />Yes
									<input type="radio" name="radExempted" value="N" ${varDisabled} onclick="showExempt(this)" checked="checked" />No
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<input type="radio" name="radExempted" value="Y" ${varDisabled} onclick="showExempt(this)" />Yes
							<input type="radio" name="radExempted" value="N" ${varDisabled} onclick="showExempt(this)" checked="checked" />No
						</c:otherwise>
					</c:choose>
				</hdiits:td>
				<td align="left" class="Label">
					<fmt:message key="BUD.CATEGORY" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">								 
					:&nbsp;&nbsp;<select name="cmbBillType" id="id_cmbBillType" style="width:155px" ${billCatDisabled}  onchange="showChallan()">
						<option value="-1"> --Select-- </option>
			 			<c:forEach var="BillType" items="${resValue.BillType}">
			 			<c:if test="${BillType.lookupName == 'Regular'}">
								<option selected="true" value="${BillType.lookupName}" > <c:out value="${BillType.lookupDesc}"></c:out> </option>
						</c:if>
							<c:if test="${resValue.Selected_BillType != null && BillType.lookupName == resValue.Selected_BillType.Value}">
								<option value="${BillType.lookupName}" selected="true"> <c:out value="${BillType.lookupDesc}"></c:out> </option>
							</c:if>
							<c:if test="${BillType.lookupName != resValue.Selected_BillType.Value}">
								<option value="${BillType.lookupName}"> <c:out value="${BillType.lookupDesc}"></c:out> </option>
							</c:if>
						</c:forEach>
						<script>showChallan();</script>
					</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					<input type="hidden" name="digi3_0_TC_BILL" />
				</hdiits:td>
			</hdiits:tr>
			<tr id="tblExempted">
				<td align="left" class="Label">
					<fmt:message key="CMN.BILL_CODE" bundle="${billprocLabels}"></fmt:message>
				</td>
				<td align="left" class="Label" colspan="3">
					:&nbsp;&nbsp;<select name="cmbBillCode" style="width:61%" ${varDisabled}>
						<option value="-1">--Select--</option>													
						<c:forEach var="billcode" items="${resValue.BillCodeList}">
							<c:if test="${billcode.lookupShortName == resValue.TrnBillRegister.billCode}">
								<option selected="selected" value="${billcode.lookupShortName}">
									<c:out value="${billcode.lookupShortName}"></c:out> - <c:out value="${billcode.lookupDesc}"></c:out>
								</option>
							</c:if>
							<c:if test="${billcode.lookupShortName != resValue.TrnBillRegister.billCode}">
								<option value="${billcode.lookupShortName}">
									<c:out value="${billcode.lookupShortName}"></c:out> - <c:out value="${billcode.lookupDesc}"></c:out>
								</option>
							</c:if>
					 	</c:forEach>
					</select>
				</td>
			</tr>
			<hdiiits:tr>
				<td align="left" class="Label">
					<fmt:message key="BUD.PREVBILLNO" bundle="${onlinebillprepLabels}"></fmt:message>
			    </td>
				<td align="left">
					:&nbsp;&nbsp;<input type="text"  id="oldBillCntrlNo" name="oldBillCntrlNo" class="texttag" value="${resValue.oldBillCntrlNo}"/>
					<input type="hidden"  name="prevBillNo" value="${resValue.prevBillNo}"/>
				</td>
			</hdiiits:tr>
			<c:choose>
				<c:when test="${resValue.TrnBillRegister != null && resValue.TrnBillRegister.exempted == 'Y'}">
				</c:when>
				<c:otherwise>
					<script> document.getElementById("tblExempted").style.display = "none"; </script>
				</c:otherwise>
			</c:choose>
		</table>
	</fieldset>
	<br />
		<jsp:include page="/WEB-INF/jsp/hrms/onlinebillprep/EDPDtls.jsp" />
	<br />
	<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> Grant Details </b> </legend>
		<table align="center" width="100%">
			<hdiits:tr>
				<td width="30%" align="left" class="Label">	
					<br/> <b> <fmt:message key="BUD.GROSS.TOTAL" bundle="${onlinebillprepLabels}"></fmt:message> </b>
				</td>
				<td align="left" id="GrossTotal" class="Label">	
					<br /> <b> : <script> document.write(expenditure); </script> </b>
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" class="Label">	
					<b> <fmt:message key="BUD.RECOVERY" bundle="${onlinebillprepLabels}"></fmt:message> </b>
				</td>
				<td align="left" id="Recovery" class="Label">	
					<b> : <script> document.write(recovery); </script> </b> 
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" class="Label">	
					<b> <fmt:message key="BUD.NET.TOTAL" bundle="${onlinebillprepLabels}"></fmt:message> </b>
					<input type="hidden" id="hidNetTotal" name="txtNetTotal" /> 
				</td>
				<td align="left" id="NetTotal" class="Label">	
					<b> : <script> document.write(${resValue.TrnBillRegister.billNetAmount}); </script> </b> 
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" class="Label">	
					<br /> <b> <fmt:message key="BUD.AMT.WORD" bundle="${onlinebillprepLabels}"></fmt:message> </b>
				</td>
				<td align="left" id="AmtInRs" class="Label">	
					<br /> <b> : <script> document.write(getAmountInWords(expenditure - recovery)); </script> </b> 
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" class="Label">	
					<br />
				</td>
				<td align="right" class="Label">	
					<br />
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" class="Label">	
					<b> <fmt:message key="BUD.APPROPRIATION" bundle="${onlinebillprepLabels}"></fmt:message> </b>
					<input type="hidden" id="hidGrantAmt" name="txtGrantAmt" />
				</td>
				<td align="left" id="Appropriation" class="Label">	
					<b> : <script> document.write(${resValue.TrnBillRegister.grantAmount}); </script> </b> 				
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" class="Label">	
					<b> <fmt:message key="BUD.EXP" bundle="${onlinebillprepLabels}"></fmt:message> </b>
				</td>
				<td align="left" id="DDOExpAmt" class="Label">	
					<b> : <script> document.write(${resValue.TotalExpenditure}); </script> </b> 				
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" class="Label">	
					<br /> <b> <fmt:message key="BUD.BALANCE" bundle="${onlinebillprepLabels}"></fmt:message> </b>
				</td>
				<td align="left" id="Balance" class="Label">	
					<b> : <script> document.write(${resValue.AvailableBalance}); </script> </b> 				
				</td>
			</hdiits:tr>
		</table>
	</fieldset>
	<c:if test="${resValue.EditBill == null}">
		<script type="text/javascript">
			document.getElementById("GrossTotal").innerHTML = "<b> :  </b>";
			document.getElementById("Recovery").innerHTML = "<b> :  </b>";
			document.getElementById("NetTotal").innerHTML = "<b> :  </b>";
			document.getElementById("AmtInRs").innerHTML = "<b> :  </b>";
			document.getElementById("Appropriation").innerHTML = "<b> :  </b>";
			document.getElementById("DDOExpAmt").innerHTML = "<b> :  </b>";
			document.getElementById("Balance").innerHTML = "<b> :  </b>";
		</script>
	</c:if>
	
<script language="Javascript" type="text/javascript"> 
	
	//	AJEX SCRIPT FOR TO GET GRANT DETAILS.
	var req = null; 
		
	function createXMLHttpRequest() 
	{ 
		var ua; 
			
		if(window.XMLHttpRequest) 
		{ 
			ua = new XMLHttpRequest(); 
		} 
		else if(window.ActiveXObject) 
		{ 
			ua = new ActiveXObject("Microsoft.XMLHTTP"); 
		} 

		return ua; 
	} 
    
 	function getDDOGrantAndExpenditure() 
	{ 
		if(isValidateGrantDtls())
		{
			req = createXMLHttpRequest();
			if(req != null)
			{
				var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=getGrantAmountForDDO";
				baseUrl += "&cmbBudType=" + document.getElementById("cmbBudType").value;		
				baseUrl += "&txtDmd=" + document.getElementById("Demand").value;
				baseUrl += "&txtMjrHd=" + document.getElementById("MjHd").value;
				baseUrl += "&txtSbMjrHd=" + document.getElementById("SubMjHd").value;
				baseUrl += "&txtMnrHd=" + document.getElementById("MnrHd").value;
				baseUrl += "&txtSbHd=" + document.getElementById("SubHd").value;
				baseUrl += "&txtDtldHd=" + document.getElementById("DtlHd").value; 
				
				req.open("post", baseUrl, true); 
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = responseDDOGrantAndExpenditure; 
				req.send(baseUrl);
			}
			else
			{
				alert ("Your browser does not support AJAX!");
			} 
		} 
		else
		{
			document.getElementById("DtlHd").value = "";
			document.getElementById("HdChrgble").value = "";
		}	
	} 
  
	function responseDDOGrantAndExpenditure() 
	{ 
		if(req.readyState == 4)
		{ 
			if(req.status == 200)
			{
				hide_img();
				enable_div();
				hideProgressbar();
				enable();
			
				var XMLDoc = req.responseXML.documentElement;
				var XMLEntries = XMLDoc.getElementsByTagName("Grant");	
					
				if(XMLEntries[0].childNodes[2].text == "true")
				{
					var TableCal = document.getElementById("Appropriation");
	    			if(TableCal != null)
	    			{
	    				TableCal.innerHTML = "<b> : " + XMLEntries[0].childNodes[0].text + " </b> ";
		    			document.getElementById("hidGrantAmt").value = XMLEntries[0].childNodes[0].text; 
	    			}

	    			glDDOGrantAmt = parseFloat(XMLEntries[0].childNodes[1].text);	    			
	    			document.getElementById("HdChrgble").focus();
	    		}
	    		else
	    		{
	    			alert("<fmt:message key="BUD.HDSTR" bundle="${onlinebillprepAlerts}"></fmt:message>");
	    			var TableCal = document.getElementById("Appropriation");
	    			if(TableCal != null)
	    			{
	    				TableCal.innerHTML = "<b> : </b> ";
	    				document.getElementById("hidGrantAmt").value = "";
	    			}
	    			
	    			document.getElementById("txtSchemeNo").value = "";
	    			document.getElementById("Demand").value = "";
					document.getElementById("MjHd").value = "";
					document.getElementById("SubMjHd").value = "";
					document.getElementById("MnrHd").value = ""
					document.getElementById("SubHd").value = ""
					document.getElementById("DtlHd").value = "";
					document.getElementById("HdChrgble").value = "";
					document.getElementById("Demand").focus();
	    		}	    		
			}						
		}			
	} 	
	
	function getDDOHeadBySchemeNo()
	{
		req = createXMLHttpRequest();
		
		disable();
		showProgressbar();
		
		var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOHeadBySchemeNo";
		baseUrl += "&schemeNo=" + document.getElementById("txtSchemeNo").value;
		
		req.open("post", baseUrl, false); 
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.onreadystatechange = responseDDOHeadBySchemeNo; 
		req.send(baseUrl);
	}
	function responseDDOHeadBySchemeNo()
	{
		if(req.readyState == 4)
		{ 
			if(req.status == 200)
			{
				hide_img();
				enable_div();
				hideProgressbar();
				enable();
				
				var XMLDoc = req.responseXML.documentElement;
				var XMLEntries = XMLDoc.getElementsByTagName("DDOGrantHead");
				
				if(!(XMLEntries[0].childNodes[0].text == ""))
				{
					document.getElementById("Demand").value = XMLEntries[0].childNodes[0].text;
					document.getElementById("MjHd").value = XMLEntries[0].childNodes[1].text;
					document.getElementById("SubMjHd").value = XMLEntries[0].childNodes[2].text;
					document.getElementById("MnrHd").value = XMLEntries[0].childNodes[3].text;
					document.getElementById("SubHd").value = XMLEntries[0].childNodes[4].text;
					document.getElementById("DtlHd").value = "";
					document.getElementById("HdChrgble").value = "";
					document.getElementById("Demand").focus();
				}
			}
		}		
	}
	function getSchemeNoByDDOGrantHead()
	{
		disable();
		showProgressbar();
		
		req = createXMLHttpRequest();
				
		var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=getSchemeNoByDDOGrantHead";
		baseUrl += "&demandCode=" + document.getElementById("Demand").value;
		baseUrl += "&majorHead=" + document.getElementById("MjHd").value;
		baseUrl += "&subMajorHead=" + document.getElementById("SubMjHd").value;
		baseUrl += "&minorHead=" + document.getElementById("MnrHd").value;
		baseUrl += "&subHead=" + document.getElementById("SubHd").value;
		
		req.open("post", baseUrl, false); 
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.onreadystatechange = responseSchemeNoByDDOGrantHead; 
		req.send(baseUrl);
		
	}
	function responseSchemeNoByDDOGrantHead()
	{
		if(req.readyState == 4)
		{ 
			if(req.status == 200)
			{
				hide_img();
				enable_div();
				hideProgressbar();
				enable();
				
				var XMLDoc = req.responseXML.documentElement;
				var XMLEntries = XMLDoc.getElementsByTagName("DDOGrantHead");
				
				if(!(XMLEntries[0].childNodes[0].text == "null" || XMLEntries[0].childNodes[0].text == ""))
				{
					document.getElementById("txtSchemeNo").value = XMLEntries[0].childNodes[0].text;
				}
				
				getDDOGrantAndExpenditure();
			}
		}		
	}
</script> 

	<script type="text/javascript">		
		initializetabcontent("maintab");
	</script>	
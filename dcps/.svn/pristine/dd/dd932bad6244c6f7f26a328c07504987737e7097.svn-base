<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.pensionpay.PensionAlerts" var="PensionAlerts" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionToolTip" var="pensionToolTip" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"	var="pensionLabels1" scope="request" />



<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script  type="text/javascript"  src="script/pensionpay/DAArrearsCalculation.js"></script>

<script type="text/javascript">
LISTDAIN='<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>'
	+'<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>';
PAYCOMMISSIONTYPE='<option value="<fmt:message key="PPMT.DA1986" bundle="${pensionConstants}"/>"><fmt:message key="DAARREARS.4PC" bundle="${pensionConstants}"/></option>'
	+'<option value="<fmt:message key="PPMT.DA1996DPMERGED" bundle="${pensionConstants}"/>"><fmt:message key="DAARREARS.5PC" bundle="${pensionConstants}"/></option>'
	+'<option value="<fmt:message key="PPMT.DA2006" bundle="${pensionConstants}"/>"><fmt:message key="DAARREARS.6PC" bundle="${pensionConstants}"/></option>';
</script>

<hdiits:form name="DAArrrearRecoveryCalculation" validate="true"  method="post" >
	
<hdiits:hidden name="hidPensnReqId" id="hidPensnReqId" />
<hdiits:hidden name="hidPnsnrCode" id="hidPnsnrCode"/>
<hdiits:hidden name="hidppono" id="hidppono"/>
<hdiits:hidden name="hidheadcode" id="hidheadcode"  />
<hdiits:hidden name="hidlastBillMonth" id="hidlastBillMonth"/>
<hdiits:hidden name="hidFamMemDOB" id="hidFamMemDOB"/>
<hdiits:hidden name="hidFamMemDOD" id="hidFamMemDOD" />
<hdiits:hidden name="hidPnsnrDOB" id="hidPnsnrDOB" />
<hdiits:hidden name="hidPnsnrDOR" id="hidPnsnrDOR" />
<hdiits:hidden name="hidPnsnrDOD" id="hidPnsnrDOD" />
<hdiits:hidden name="supplyFlg" id="supplyFlg" />

<hdiits:hidden name="hidFP1Date" id="hidFP1Date"/>
<hdiits:hidden name="hidFP2Date" id="hidFP2Date" />
<hdiits:hidden name="hidNewFP1Basic" id="hidNewFP1Basic" />
<hdiits:hidden name="hidNewFP2Basic" id="hidNewFP2Basic"/>
<hdiits:hidden name="hidCommensionDate" id="hidCommensionDate" />
<hdiits:hidden name="hidEndDate" id="hidEndDate"/>
<hdiits:hidden name="hidBankName" id="hidBankName"/>
<hdiits:hidden name="hidBranchName" id="hidBranchName"/>
<hdiits:hidden name="hidAccountNo" id="hidAccountNo"/>
<hdiits:hidden name="hidCurrentDt" id="hidCurrentDt"/>
<input type="hidden" name="hidBillFlag" id="hidBillFlag" value="${resValue.BillFlag}"/>



	<fieldset style="width:100%" class="tabstyle" >
		<legend ><fmt:message key="REVARREAR.HDG" bundle="${pensionLabels}"></fmt:message></legend>
		
		<table width="100%">			
			<tr>
				<td align="left" ><fmt:message key="REVARREAR.PPONO" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtPPONO"  size="20"  name="txtPPONO"  style="display: ;text-transform: uppercase;"  onblur="validatePPONumber();" /><label class="mandatoryindicator">*</label>
				</td>
				<td align="left" ><fmt:message key="REVARREAR.DOB" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtDOB"  size="20"  name="txtDOB" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="left" ><fmt:message key="REVARREAR.PENNAME" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtPensionerName"  size="30"  name="txtPensionerName" disabled="disabled"/>
					<input type="hidden" id="hdnPensionerName" name="hdnPensionerName"/>
				</td>
				<td align="left" ><fmt:message key="REVARREAR.DOR" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtDOR"  size="20"  name="txtDOR" disabled="disabled"/>
					
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td height="15px"></td>
			</tr>
		</table>
		
		 
		<fieldset style="width:100%" class="tabstyle" >
			<legend id="headingMsg"><b>Pension Basic Details</b></legend>
			<input type="hidden" name="hidPnsnBasicGridSize" id="hidPnsnBasicGridSize" value="1">
			<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:85%; height:120px; overflow: scroll; overflow-x:scroll; overflow-y:scroll; ">	
			<table align="left" id="BasicPnsnDtl" name="BasicPnsnDtl" border="1" bordercolor="#C9DFFF" width="90%">
				<tr>
					<td align="left" colspan="7">
						<input type="button" value="Add Row" name="AddRowBasic" id="basicPnsnAddrow"  onclick="addRowPnsnBasicDtls()" title='<fmt:message key="RECO.ADDROW" bundle="${pensionToolTip}"></fmt:message>' class="bigbutton">								    
					</td>
				</tr>
				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="DAARRE.PC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="DAARRE.BASIC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="DAARRE.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="DAARRE.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="DAARRE.OLDDARATE" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="DAARRE.NEWDARATE" bundle="${pensionLabels}"></fmt:message></b></td>
					<td width="2%" style="background-color: white;"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
					

				</tr>				
				<tr>
					<td align="center">
						<select name="cmbPayCommissionType"  id="cmbPayCommissionType" style="width:90%" >
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
							<option value="<fmt:message key="PPMT.DA1986" bundle="${pensionConstants}"/>"><fmt:message key="DAARREARS.4PC" bundle="${pensionConstants}"/></option>
							<option value="<fmt:message key="PPMT.DA1996DPMERGED" bundle="${pensionConstants}"/>"><fmt:message key="DAARREARS.5PC" bundle="${pensionConstants}"/></option>
							<option value="<fmt:message key="PPMT.DA2006" bundle="${pensionConstants}"/>"><fmt:message key="DAARREARS.6PC" bundle="${pensionConstants}"/></option>
																	
						</select><label class="mandatoryindicator">*</label>
					</td>
					<td align="center"><input type="text" name="txtBasic" id="txtBasic" size="16"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right" /><label class="mandatoryindicator">*</label></td>					
					<td align="center"><input type="text" name="txtDARateEffFrom" id="txtDARateEffFrom0" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(this,txtDARateEffTo0,'Effective To Date Should be greater than Effective From Date','<');"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtDARateEffFrom",375,570);' style="cursor: pointer;"  />
										<label class="mandatoryindicator">*</label>
									</td>
					<td align="center"><input type="text" name="txtDARateEffTo" id="txtDARateEffTo0" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDARateEffFrom0,this,'Effective To Date Should be greater than Effective From Date','<');" />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtDARateEffTo",375,570)'	style="cursor: pointer;"/>
										<label class="mandatoryindicator">*</label>
					</td>
					<td align="center">
						<select name="cmbOldDARate"  id="cmbOldDARate0" style="width:90%">
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
						</select><label class="mandatoryindicator">*</label>
					</td>
					<td align="center">						
						<select name="cmbNewDARate"  id="cmbNewDARate0" style="width:90%">
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>																	
						</select><label class="mandatoryindicator">*</label>
					</td>
					<td></td>
				</tr>
			</table>
			</div>
		</fieldset>
		<table>
			<tr>
				<td height="15px"></td>
			</tr>
		</table>
		<input type="hidden" name="hidReEmpDtlsGridSize" id="hidReEmpDtlsGridSize" value="1">			
		<fieldset style="width:100%" class="tabstyle" >
			<legend  id="headingMsg">Re-Employed Detail</legend>	

			<table align="left" id="ReEmp" name="ReEmp" border="1" bordercolor="#C9DFFF" width="70%">			

				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="PPMT.DAINPNSNSAL" bundle="${pensionLabels1}"></fmt:message></b></td>
				</tr>
				<tr>
			    	<td align="center"><input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom0"  onkeypress="digitFormat(this);dateFormat(this);" readonly="readonly" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />										
					</td>
					<td align="center"><input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo0"  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />				
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldReEmpEffTo0",375,570)'	style="cursor: pointer;"/>
										<label class="mandatoryindicator">*</label>																
					</td>					
					<td align="center" class="HLabel" width="35%"><select style="width: 50%" name="cmbDAInPnsnSal" id="cmbDAInPnsnSal0" onfocus="onFocus(this)" onblur="onBlur(this);">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
						<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
						<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
					</select><label class="mandatoryindicator">*</label>
					</td>									
				</tr>
			</table>
		</fieldset>
	<br>
</fieldset>
	<center>
		<hdiits:button name="btnCalculateArrear" type="button" id="btnCalculateArrear" value='Calculate' onclick="calculateDAArrear()" />
		
		<hdiits:button name="btnClose" value="Close" type="button" onclick="winCls();"/>
	</center>	

		
		
				


</hdiits:form>
<ajax:select 
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDaRateFromPayCom" 
	source="cmbPayCommissionType"
	target="cmbNewDARate0"
	eventType="change"
	parameters="PayCommissionType={cmbPayCommissionType}" >
</ajax:select>

<ajax:select 
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDaRateFromPayCom" 
	source="cmbPayCommissionType"
	target="cmbOldDARate0"
	eventType="change"
	parameters="PayCommissionType={cmbPayCommissionType}" >
</ajax:select>

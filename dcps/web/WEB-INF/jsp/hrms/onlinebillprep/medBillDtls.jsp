<%@ include file="../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.onlinebillprep.MedBillLabels" var="MedBillLabels" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.MedBillAlerts" var="MedBillAlerts" scope="application"/>

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>
<c:set var="MedBillList" scope="request" value="${resValue.BillData.ResultList}"></c:set>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<style>
		.tabstyle 
		{
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid ;
		}
	
		.legend 
		{
			padding-left:5px;
			padding-right:5px;
			font-weight:normal; 
			font-family:verdana;
				
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid ;
		}	
	</style>
	
	<script type="text/javascript">
		var MED_TXTPTNTNAME = "<fmt:message key="MED.TXTPTNTNAME" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_TXTRLT = "<fmt:message key="MED.TXTRLT" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_TXTINCNAME = "<fmt:message key="MED.TXTINCNAME" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_TXTTRMTPERIOD = "<fmt:message key="MED.TXTTRMTPERIOD" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_TXTCLAIMAMT = "<fmt:message key="MED.TXTCLAIMAMT" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_TXTSECESTB = "<fmt:message key="MED.TXTSECESTB" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_TXTCLAIMPERIOD = "<fmt:message key="MED.TXTCLAIMPERIOD" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_TXTAMT = "<fmt:message key="MED.TXTAMT" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_GRSAMTANDCLMAMT = "<fmt:message key="MED.GRSAMTANDCLMAMT" bundle="${MedBillAlerts}"></fmt:message>";
		var MED_NETAMTANDBILLPASSRS = "<fmt:message key="MED.NETAMTANDBILLPASSRS" bundle="${MedBillAlerts}"></fmt:message>";
		
		function isValidBillData()
		{
			return  validateMedBillData();			
		}
   </script>
   <script type="text/javascript" src="script/onlinebillprep/MedBill.js"> </script>
</head>
	
	<c:if test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
		<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	</c:if>
	
	<hdiits:hidden name="hidTrnMedBlHdrId" id="hidTrnMedBlHdrId" default="${resValue.BillDtls.TrnMedblHdr.trnMedblHdrId}"/>	
	<table align="center" width="100%">
		<hdiits:td colspan="7" align="left">
			<br />
			<c:if test="${resValue.EditBill != 'N'}">
				<hdiits:button name="butAddRowOne" type="button" value="Add Row"  onclick="Table_One_AddRow()" />
			</c:if>
		</hdiits:td>
	</table>
	<fieldset class="tabstyle">
	<legend  id="headingMsg"><b>Patient Details</b></legend>
		<table id="TableOne" align="center" width="100%" class="groupTable">
			<tr class="datatableheader">
				<td align="center" class="HLabel">
					<b> <fmt:message key="MED.PATIENT.NAME" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>  </b>
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="MED.RELATION" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="MED.INCUMBENT.NAME" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="MED.PERIOD" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="MED.CLAIM.AMT" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="BILL.REMARKS" bundle="${onlinebillprepLabels}"></fmt:message> </b>
				</td>
				<td align="left" class="HLabel">
				</td>
			</tr>
			<c:choose>
				<c:when test="${resValue.EditBill != null}">
					<c:forEach var="MedblDtlsVO" items="${resValue.BillDtls.TrnMedblDtls}" varStatus="BillDtlCounter">	
						<script> ++Row_One; </script>
						<hdiits:tr>
							<hdiits:hidden name="hidTrnMedblDtlsId"></hdiits:hidden>
							<hdiits:td align="center">
								<input type="text" name="txtPatientName" ${varDisabled} class="texttag" value="${MedblDtlsVO.ptntName}" />
								<input type="hidden" name="digi4_0_PTNT_NAME"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtRelation"  ${varDisabled} class="texttag" value="${MedblDtlsVO.rltnshp}" />
								<input type="hidden" name="digi4_0_RLTNSHP"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtIncumbentOne" readonly="true"  ${varDisabled} class="texttag" value="${resValue.EmpDtlsList[0].empName}" />
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtTreatmentTime"  ${varDisabled} class="texttag" value="${MedblDtlsVO.trtmtTime}" />
								<input type="hidden" name="digi4_0_TRTMT_TIME"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" id="AmtOne" name="txtAmtOne" ${varDisabled} onblur="setValidAmountFormat(this)" onkeypress="amountFormat(this)" class="texttag" value="${MedblDtlsVO.claimAmt}" />
								<input type="hidden" name="digi4_0_CLAIM_AMT"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtRemarks"  ${varDisabled} class="texttag" value="${MedblDtlsVO.remarks}" />
								<input type="hidden" name="digi4_0_REMARKS"/>
							</hdiits:td>
							<hdiits:td align="left">					
							</hdiits:td>
						</hdiits:tr>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="MedBill" items="${MedBillList}" varStatus="BillDtlCounter">	
						<script> ++Row_One; </script>
						<hdiits:tr>
							<hdiits:hidden name="hidTrnMedblDtlsId"></hdiits:hidden>
							<hdiits:td align="center">
								<input type="text" name="txtPatientName" ${varDisabled} class="texttag" value="${MedBill.ptntNameEn}" />
								<input type="hidden" name="digi4_0_PTNT_NAME"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtRelation"  ${varDisabled} class="texttag" value="${MedBill.rltEn}" />
								<input type="hidden" name="digi4_0_RLTNSHP"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtIncumbentOne" readonly="true"  ${varDisabled} class="texttag" value="${resValue.AprvRqst.empNameEn}" />
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtTreatmentTime" ${varDisabled} class="texttag" value="${MedBill.trtmntFrmDt}" />
								<input type="hidden" name="digi4_0_TRTMT_TIME"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" id="AmtOne" name="txtAmtOne" ${varDisabled} onblur="setValidAmountFormat(this)" onkeypress="amountFormat(this)" class="texttag" value="${MedBill.snctndAmt}" />
								<input type="hidden" name="digi4_0_CLAIM_AMT"/>
							</hdiits:td>
							<hdiits:td align="center">
								<input type="text" name="txtRemarks"  ${varDisabled} class="texttag" />
								<input type="hidden" name="digi4_0_REMARKS"/>
							</hdiits:td>
							<hdiits:td align="left">					
							</hdiits:td>
						</hdiits:tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</fieldset>	
	<br /><br />
	<p align="left" class="Label"> <b> 
		<fmt:message key="MED.CERTIFICATE" bundle="${MedBillLabels}"></fmt:message>
		<br /><br /> 
		<fmt:message key="MED.DETAILS" bundle="${MedBillLabels}"></fmt:message>
	</b> </p>
	<br />
	<div align="left">
		<c:if test="${resValue.EditBill != 'N'}">
			<hdiits:button name="txtAddRowTwo" type="button" value="Add Row" onclick="Table_Two_AddRow(),getGrossTotal()"/>
		</c:if>
	</div>
	<table>
		<tr>
			<td width="60%">
				<fieldset class="tabstyle">
				<legend  id="headingMsg"><b>Medical Bill Details</b></legend>
					<table id="TableTwo" align="left" class="TableBorderLTRBN">
						<hdiits:tr>
							<hdiits:td align="left" colspan="5">
							</hdiits:td>
						</hdiits:tr>
						<tr class="datatableheader">
							<td align="center" class="HLabel">
								<b> <fmt:message key="MED.SECTION.ESTABLISHMENT" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
							</td>
							<td align="center" class="HLabel">
								<b> <fmt:message key="MED.INCUMBENT.NAME" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
							</td>
							<td align="center" class="HLabel">
								<b> <fmt:message key="MED.CLAIM.PERIOD" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
							</td>
							<td align="center" class="HLabel">
								<b> <fmt:message key="MED.AMOUNT" bundle="${MedBillLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label> </b>
							</td>
							<td align="left" class="HLabel">
							</td>
						</tr>
						<c:choose>
							<c:when test="${resValue.EditBill != null}">
								<c:forEach var="MedblAprvdDtls" varStatus="BillAprvdDtlCounter" items="${resValue.BillDtls.TrnMedblAprvdDtls}">
									<script> ++Row_Two; </script>
									<hdiits:tr>		
										<hdiits:td align="center">
											<input type="text" name="txtEstablishmentTwo" ${varDisabled} value="${resValue.EmpDtlsList[0].deptName}" readonly class="texttag" />
											<input type="hidden" name="digi5_0_SCTN_ESTBLSHMNT">
										</hdiits:td>
										<hdiits:td align="center">
											<input type="text" name="txtIncumbentTwo" ${varDisabled} value="${resValue.EmpDtlsList[0].empName}" class="texttag" readonly="true" />
										</hdiits:td>
										<hdiits:td align="center">
											<input type="text" name="txtClaimPeriod"  ${varDisabled} class="texttag" value="${MedblAprvdDtls.trtmtTime}"/>
											<input type="hidden" name="digi5_0_TRTMT_TIME" />
										</hdiits:td>
										<hdiits:td align="center">
											<input type="text" id="AmtTwo" name="txtAmtTwo" ${varDisabled} onkeypress="NumberFormet()" onblur="getGrossTotal()" class="texttag" value="${MedblAprvdDtls.claimAmt}" /> 
											<input type="hidden" name="digi5_0_CLAIM_AMT" />
										</hdiits:td>			
										<hdiits:td align="left">
											<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveMedTableRow(this, 'TableTwo'),getGrossTotal()" />
										</hdiits:td>
									</hdiits:tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<script> ++Row_Two; </script>
								<hdiits:tr>
									<hdiits:hidden name="hidTrnMedblAprvdDtlsId"/>		
									<hdiits:td align="center">
										<input type="text" name="txtEstablishmentTwo"  ${varDisabled} class="texttag" value="${resValue.DeptVO.deptName}" readonly="true" />
										<input type="hidden" name="digi5_0_SCTN_ESTBLSHMNT" />
									</hdiits:td>
									<hdiits:td align="center">
										<input type="text" name="txtIncumbentTwo" ${varDisabled} class="texttag" value="${resValue.AprvRqst.empNameEn}"  readonly="true" />
									</hdiits:td>
									<hdiits:td align="center">
										<input type="text"  name="txtClaimPeriod" ${varDisabled} class="texttag" />
										<input type="hidden" name="digi5_0_TRTMT_TIME" />
									</hdiits:td>
									<hdiits:td align="center">
										<input type="text" id="AmtTwo" name="txtAmtTwo" ${varDisabled} onkeypress="NumberFormet()" onblur="getGrossTotal()" class="texttag" /> 
										<input type="hidden" name="digi5_0_CLAIM_AMT" />
									</hdiits:td>
									<hdiits:td align="left">
										<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveMedTableRow(this,'TableTwo'),getGrossTotal()" />
									</hdiits:td>			
								</hdiits:tr>
							</c:otherwise>
						</c:choose>		
						<hdiits:tr>		
							<hdiits:td align="center">
							</hdiits:td>
							<hdiits:td align="center">
							</hdiits:td>
							<td align="left" class="HLabel">
								<b> <fmt:message key="BUD.GROSS.TOTAL" bundle="${onlinebillprepLabels}" /> </b>
							</td>
							<hdiits:td align="left" id="MedBillGrossTotal">
								<b> </b>
							</hdiits:td>
							<hdiits:td align="center">
							</hdiits:td>			
						</hdiits:tr>
											
					</table>
				</fieldset>
			</td>
			<td width="0%">
			</td>
		</tr>
	</table>
	<div align="left">
	<table>
		<hdiits:tr>		
				<td align="right" class="Label">
					<fmt:message key="MED.DATE" bundle="${MedBillLabels}" /> :
				</td>
				<hdiits:td align="left">
					<hdiits:dateTime name="txtBillPassedDate"/>
				</hdiits:td>
				<hdiits:td align="center">
				</hdiits:td>
				<hdiits:td align="center">
				</hdiits:td>
				<hdiits:td align="center">
				</hdiits:td>			
			</hdiits:tr>
			<hdiits:tr>		
				<td align="right" class="Label">
					<fmt:message key="BILL.PASSED.RS" bundle="${onlinebillprepLabels}" /> :
				</td>
				<hdiits:td align="left">
					<input type="text" name="txtBillPassedAmt" onkeypress="NumberFormet()" onblur="getGrossTotal()" readonly="readonly" class="texttag" ${varDisabled} value="${resValue.BillDtls.TrnMedblHdr.billPassedAmt}" />
					<input type="hidden" name="digi6_0_BILL_PASSED_AMT" />
				</hdiits:td>
				<hdiits:td align="center">
				</hdiits:td>
				<hdiits:td align="center">
				</hdiits:td>
				<hdiits:td align="center">
				</hdiits:td>			
			</hdiits:tr>
			<hdiits:tr>		
				<td align="left" class="Label">
					PASSED for Rs(in words)
				</td>
				<hdiits:td align="left" id="BillPassAmtInWord" colspan="3">
					<b> <script> document.write(getAmountInWords(${resValue.BillDtls.TrnMedblHdr.billPassedAmt})); </script> </b>
				</hdiits:td>
		</hdiits:tr>
</table>
</div>
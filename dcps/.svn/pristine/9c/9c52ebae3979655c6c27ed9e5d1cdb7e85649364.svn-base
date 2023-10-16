<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="HouseDtls" value="${resValue.lLstHouseDtls}" />
<c:set var="VocherDtlsHBA" value="${resValue.lLstVocherDtlsHBA}" />
<c:set var="UserType" value="${resValue.UserType}"></c:set>
<c:if test="${UserType == 'HOD'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>	
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
</c:if>	

<c:if test="${HouseDtls[14] == null}">
		<c:set var="varDisplayNoneDis1" value="style='display:none'"></c:set>
</c:if>
<c:if test="${HouseDtls[15] == null}">
		<c:set var="varDisplayNoneDis2" value="style='display:none'"></c:set>
</c:if>
<c:if test="${HouseDtls[16] == null}">
	<c:set var="varDisplayNoneDis3" value="style='display:none'"></c:set>
</c:if>
<c:if test="${HouseDtls[17] == null}">
		<c:set var="varDisplayNoneDis4" value="style='display:none'"></c:set>
</c:if>
<c:if test="${HouseDtls[14] != null}">
		<c:set var="varNoOfDisInst" value="1"></c:set>
</c:if>
<c:if test="${HouseDtls[15] != null}">
		<c:set var="varNoOfDisInst" value="2"></c:set>
</c:if>
<c:if test="${HouseDtls[16] != null}">
	<c:set var="varNoOfDisInst" value="3"></c:set>
</c:if>
<c:if test="${HouseDtls[17] != null}">
		<c:set var="varNoOfDisInst" value="4"></c:set>
</c:if>
<c:choose>
<c:when test="${HouseDtls[2] == 800038 || HouseDtls[2] == 800058}">
		<c:set var="varDisplayNoneNoOfDisInt" value=""></c:set>
</c:when>
<c:otherwise>
	<c:set var="varDisplayNoneNoOfDisInt" value="style='display:none'"></c:set>
</c:otherwise>
</c:choose>

<input type="hidden" id="hidHouseId" name="hidHouseId" value="${HouseDtls[13]}"/>
<input type="hidden" id="txtTransactionIdHBA" name="txtTransactionIdHBA" value="${HouseDtls[11]}"/>
<input type="hidden" id="hidTotalPrinRecoveredHBA" name="hidTotalPrinRecoveredHBA" value="" />
<input type="hidden" id="hidTotalIntRecoveredHBA" name="hidTotalIntRecoveredHBA" value="" />

<table width="80%">
	<tr>
		<td width="15%"><fmt:message key="CMN.SANCAMT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtSancAmountHBA"  name="txtSancAmountHBA" onkeypress="digitFormat(this);" value="${HouseDtls[0]}" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>	
		<td width="15%"><fmt:message key="CMN.SANCDATE"	bundle="${lnaLabels}" /></td>
		<td width="20%">
			<fmt:formatDate value="${HouseDtls[1]}" pattern="dd/MM/yyyy" var="sanctionedDate"/>
			<input type="text" name="txtSanctionedDateHBA" id="txtSanctionedDateHBA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "${sanctionedDate}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtSanctionedDateHBA",375,570)' ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.SUBTYPE" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%">
			<select name="cmbHouseSubType" id="cmbHouseSubType" style="width:170px" onchange="displayDisbursment();" ${varDisabled}>
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="LoanSubType" items="${resValue.lstHouseSubType}">
					<c:choose>
						<c:when test="${HouseDtls[2] == LoanSubType.lookupId}">
							<option value="${LoanSubType.lookupId}" selected="selected"><c:out
								value="${LoanSubType.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${LoanSubType.lookupId}"><c:out
								value="${LoanSubType.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</select><label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.INTERESTRATE"	bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtInterestRateHBA" name="txtInterestRateHBA" value="${HouseDtls[3]}"  onkeypress="amountFormat(this);"style="text-align: right;" ${varDisabled} />
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.ORDERNO" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtOrderNoHBA"  name="txtOrderNoHBA" value="${HouseDtls[4]}" ${varDisabled}/>			
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>		
		<td width="15%"><fmt:message key="CMN.ORDERDATE" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<fmt:formatDate value="${HouseDtls[5]}" pattern="dd/MM/yyyy" var="orderDate"/>
			<input type="text" id="txtOrderDateHBA" name="txtOrderDateHBA"maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" value="${orderDate}" onBlur="validateDate(this);" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtOrderDateHBA",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.TOTALPRINCAMT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalPrincAmtHBA" name="txtTotalPrincAmtHBA" onkeypress="digitFormat(this);" value="${HouseDtls[0]}" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.TOTALINTERESTAMT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalInterestAmtHBA" name="txtTotalInterestAmtHBA" onkeypress="digitFormat(this);" value="${HouseDtls[6]}" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>	
	<tr>
		<td width="15%"><fmt:message key="CMN.TOTALPRINCINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalPrincInsHBA" name="txtTotalPrincInsHBA" onkeypress="digitFormat(this);" value="${HouseDtls[7]}" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.TOTALINTERESTINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalInterestInsHBA" name="txtTotalInterestInsHBA" onkeypress="digitFormat(this);" value="${HouseDtls[8]}" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.RECOVEREDPRINCINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<c:if test="${HouseDtls[7] !=null}">
				<input type="text"	id="txtRecoverPrincInsHBA" name="txtRecoverPrincInsHBA" onkeypress="digitFormat(this);" value="${HouseDtls[7] - HouseDtls[9]}" onblur="addRowForPrincipalRecoveredInsHBA();" style="text-align: right;" ${varDisabled}/>
			</c:if>
			<c:if test="${HouseDtls[7] ==null}">
				<input type="text"	id="txtRecoverPrincInsHBA" name="txtRecoverPrincInsHBA" onkeypress="digitFormat(this);" value="" onblur="addRowForPrincipalRecoveredInsHBA();" style="text-align: right;" ${varDisabled}/>
			</c:if>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.RECOVEREDINTERESTINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<c:if test="${HouseDtls[8] !=null}">
				<input type="text"	id="txtRecoverInterestInsHBA" name="txtRecoverInterestInsHBA" onkeypress="digitFormat(this);" value="${HouseDtls[8] - HouseDtls[10]}" onblur="addRowForInterestRecoveredInsHBA();" style="text-align: right;" ${varDisabled}/>
			</c:if>
			<c:if test="${HouseDtls[8] ==null}">
				<input type="text"	id="txtRecoverInterestInsHBA" name="txtRecoverInterestInsHBA" onkeypress="digitFormat(this);" value="" onblur="addRowForInterestRecoveredInsHBA();" style="text-align: right;" ${varDisabled}/>
			</c:if>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr id="trDisbursement" ${varDisplayNoneNoOfDisInt}>
		<td width="15%"><fmt:message key="CMN.NOOFDISBRMNTINSTLMNT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtNoOfDisburseInst" name="txtNoOfDisburseInst" onkeypress="digitFormat(this);" value="${varNoOfDisInst}" onblur="validateDisbursement();displayReleaseDate();" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>			
	</tr>
	<tr>		
		<td width="10%" id="trSanc1" ${varDisplayNoneDis1}><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>		
		<td width="20%" id="trSanc2" ${varDisplayNoneDis1}>		
			<fmt:formatDate value="${HouseDtls[14]}" pattern="dd/MM/yyyy" var="ReleaseDate1"/>	
			<input type="text" name="txtReleaseDate1" id="txtReleaseDate1" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "${ReleaseDate1}"  ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate1",375,570)' ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>		
		<td width="10%" id="trSanc3" ${varDisplayNoneDis2}><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
		<td width="20%" id="trSanc4" ${varDisplayNoneDis2}>			
			<fmt:formatDate value="${HouseDtls[15]}" pattern="dd/MM/yyyy" var="ReleaseDate2"/>
			<input type="text" name="txtReleaseDate2" id="txtReleaseDate2" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "${ReleaseDate2}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate2",375,570)' ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>		
	</tr>
	<tr>
		<td width="10%" id="trSanc5" ${varDisplayNoneDis3}><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
		<td width="20%" id="trSanc6" ${varDisplayNoneDis3}>		
			<fmt:formatDate value="${HouseDtls[16]}" pattern="dd/MM/yyyy" var="ReleaseDate3"/>		
			<input type="text" name="txtReleaseDate3" id="txtReleaseDate3" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "${ReleaseDate3}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate3",375,570)' ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="10%" id="trSanc7" ${varDisplayNoneDis4}><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
		<td width="20%" id="trSanc8" ${varDisplayNoneDis4}>	
			<fmt:formatDate value="${HouseDtls[17]}" pattern="dd/MM/yyyy" var="ReleaseDate4"/>		
			<input type="text" name="txtReleaseDate4" id="txtReleaseDate4" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "${ReleaseDate4}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate4",375,570)' ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>				
	</tr>
</table>
<br>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 300px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblHouseAdvance" border="1" width="100%">
	   <tr  class="TableHeaderBG" >
			<th width="3%" class="datatableheader" rowspan="2" colspan="1">
				<fmt:message key="CMN.SRNO" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="13%" class="datatableheader" rowspan="2"	colspan="1">
				<fmt:message key="CMN.VCHRCHALLAN" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="11%" class="datatableheader" rowspan="2"colspan="1">
				<fmt:message key="CMN.INSTALLMENTNO" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="7%" class="datatableheader" rowspan="2" colspan="1">
				<fmt:message key="CMN.MONTH" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="5%" class="datatableheader" rowspan="2" colspan="1">
				<fmt:message key="CMN.YEAR" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="15%" class="datatableheader" rowspan="1"	colspan="2">
				<fmt:message key="CMN.RECOVEREDAMOUNT" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="10%" class="datatableheader" rowspan="2"	colspan="1">
				<fmt:message key="CMN.AMTTOBERECOVERED" bundle="${lnaLabels}" ></fmt:message>
			</th>			
			<th width="20%" class="datatableheader" rowspan="2"	colspan="1">
				<fmt:message key="CMN.NAMEOFTREASURY" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="7%" class="datatableheader" rowspan="2"	colspan="1">
				<fmt:message key="CMN.VCHRCHLLNNO" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="10%" class="datatableheader" rowspan="2"	colspan="1">
				<fmt:message key="CMN.VCHRCHLLNDATE" bundle="${lnaLabels}" ></fmt:message>
			</th>			
			<th width="2%" class="datatableheader" rowspan="2" colspan="1">
				<b><fmt:message key="CMN.DELETE" bundle="${lnaLabels}" /></b>
			</th>
		</tr>
		<tr  class="TableHeaderBG" >
			<th width="7%" class="datatableheader" rowspan="1"	colspan="1">
				<fmt:message key="CMN.PRINCIPAL" bundle="${lnaLabels}" ></fmt:message>
			</th>
			<th width="7%" class="datatableheader" rowspan="1"colspan="1">
				<fmt:message key="CMN.INTEREST" bundle="${lnaLabels}" ></fmt:message>
			</th>
		</tr>
		<c:choose>

			<c:when test="${VocherDtlsHBA != null}">
	
				<c:forEach var="SchDtls" items="${VocherDtlsHBA}" varStatus="Counter">					
				
				  <c:if test="${SchDtls[0] == 'V'}">
					<tr>
						<td class="tds" align="center">
							${Counter.index+1}
							<input type="hidden" id="hidSchdlDtlsPkHBA${Counter.index}" name="hidSchdlDtlsPkHBA" value="${SchDtls[8]}" />
						</td>
						<td class="tds" align="center">	
							<input type="radio" name="radioVoucherChallanHBA${Counter.index}" id="radioVoucherHBA${Counter.index}" value="V" size="5" checked="checked" disabled="disabled"/>Voucher<input type="radio" name="radioVoucherChallanHBA${Counter.index}" id="radioChallanHBA${Counter.index}" value="C" onclick="enableDisableVchrChallan(${Counter.index});" size="5" disabled="disabled" />Challan
						</td>	
						<td class="tds" align="center">
							<input type="text" id="txtInstlmntVchrHBA${Counter.index}" name="txtInstlmntVchrHBA" size=5 onkeypress="digitFormat(this);" value="${SchDtls[1]}" onblur="validateInstallment(${Counter.index})" ${varDisabled} style="text-align: right;" />
						</td>
						<td class="tds" align="center">
							<select name="cmbMonthHBA" id="cmbMonthHBA${Counter.index}" ${varDisabled}>
								<c:forEach var="Mon" items="${resValue.lLstMonths}">
									<c:choose>
										<c:when test="${SchDtls[2] == Mon.id}">
											<option value="${Mon.id}" selected="selected">
											<c:out value="${Mon.desc}"></c:out>
											</option>
										</c:when>
										<c:otherwise>
												<option value="${Mon.id}">
											<c:out value="${Mon.desc}"></c:out></option>
										</c:otherwise>
									</c:choose>
									</c:forEach>
							</select>					   		
					   	</td>
					   	<td class="tds" align="center">	
					   		<select name="cmbYearHBA" id="cmbYearHBA${Counter.index}" ${varDisabled}>
					   			<c:forEach var="Year" items="${resValue.lLstYears}">
									<c:choose>
										<c:when test="${SchDtls[3] == Year.id}">
											<option value="${Year.id}" selected="selected">
											<c:out value="${Year.desc}"></c:out>
											</option>
										</c:when>
										<c:otherwise>
												<option value="${Year.id}">
											<c:out value="${Year.desc}"></c:out></option>
										</c:otherwise>
									</c:choose>
									</c:forEach>
					   		</select>
					   	</td>
					   	<c:if test="${SchDtls[9]=='P'}">
							<td class="tds" align="center">	 
								<input type="text" name="txtPrinInstAmountHBA" id="txtPrinInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[4]}"  style="text-align: right" ${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountHBA" id="txtInterestInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>
						<c:if test="${SchDtls[9]=='I'}">
							<td class="tds" align="center">	 
								<input type="text" name="txtPrinInstAmountHBA" id="txtPrinInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right" ${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountHBA" id="txtInterestInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[4]}"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>
								   	
					   	<td class="tds" align="center">
					   		<input type="text" name="txtAmtToBeRecoveredHBA" id="txtAmtToBeRecoveredHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"value="${SchDtls[5]}"  style="text-align: right" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtTreasuryNameHBA" id="txtTreasuryNameHBA${Counter.index}" size="30" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVoucherNoHBA" id="txtVoucherNoHBA${Counter.index}" value="${SchDtls[6]}" size="15" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<fmt:formatDate value="${SchDtls[7]}" pattern="dd/MM/yyyy" var="voucherDate"/>
					   		<input type="text" name="txtVoucherDateHBA" id="txtVoucherDateHBA${Counter.index}" ${varDisabled} maxlength="10" value="${voucherDate}" size="9" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/><img id="imgVoucher" ${varImageDisabled} src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,'txtVoucherDateHBA${Counter.index}', 375, 570);" />	
					   	</td>
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblHouseAdvance')" ${varImageDisabled}/>
						</td>		   						   		
					</tr>
				  </c:if>				  
				  <c:if test="${SchDtls[0] == 'C'}">
				  <tr>
						<td class="tds" align="center">
							${Counter.index+1}
							<input type="hidden" id="hidSchdlDtlsPkHBA${Counter.index}" name="hidSchdlDtlsPkHBA" value="${SchDtls[6]}" />
						</td>
						<td class="tds" align="center">	
							<input type="radio" name="radioVoucherChallanHBA${Counter.index}" id="radioVoucherHBA${Counter.index}" value="V" onclick="enableDisableVchrChallan(${Counter.index});" size="5" disabled="disabled"/>Voucher<input type="radio" name="radioVoucherChallanHBA${Counter.index}" id="radioChallanHBA${Counter.index}" value="C" onclick="enableDisableVchrChallan(${Counter.index});" size="5" checked="checked" disabled="disabled" />Challan
						</td>	
						<td class="tds" align="center">
							From:<input type="text" id="txtInstlmntChallanFromHBA${Counter.index}" value="${SchDtls[1]}" ${varDisabled} name="txtInstlmntChallanFromHBA${Counter.index}" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment(${Counter.index})"/>To:<input type="text" id="txtInstlmntChallanToHBA${Counter.index}" value="${SchDtls[2]}" ${varDisabled} name="txtInstlmntChallanToHBA${Counter.index}" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment(${Counter.index})"/>
						</td>
						<td class="tds" align="center">
							<select name="cmbMonthHBA" id="cmbMonthHBA${Counter.index}" >
								<c:forEach var="Mon" items="${resValue.lLstMonths}">
								<c:choose>
										<c:when test="${SchDtls[9] == Mon.id}">
											<option value="${Mon.id}" selected="selected">
											<c:out value="${Mon.desc}"></c:out>
											</option>
										</c:when>
										<c:otherwise>
												<option value="${Mon.id}">
											<c:out value="${Mon.desc}"></c:out></option>
										</c:otherwise>
									</c:choose>								
								</c:forEach>
							</select>					   		
					   	</td>
					   	<td class="tds" align="center">	
					   		<select name="cmbYearHBA" id="cmbYearHBA${Counter.index}" >
					   			<c:forEach var="Year" items="${resValue.lLstYears}">
									<c:choose>
										<c:when test="${SchDtls[10] == Year.id}">
											<option value="${Year.id}" selected="selected">
											<c:out value="${Year.desc}"></c:out>
											</option>
										</c:when>
										<c:otherwise>
												<option value="${Year.id}">
											<c:out value="${Year.desc}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
					   		</select>
					   	</td>
							<c:if test="${SchDtls[8]=='P'}">
							<td class="tds" align="center">	 
								<input type="text" name="txtPrinInstAmountHBA" id="txtPrinInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[3]}"  style="text-align: right" ${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountHBA" id="txtInterestInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>
						<c:if test="${SchDtls[8]=='I'}">
							<td class="tds" align="center">	 
								<input type="text" name="txtPrinInstAmountHBA" id="txtPrinInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right"${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountHBA" id="txtInterestInstAmountHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[3]}"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>				  
					   	<td class="tds" align="center">
					   		<input type="text" name="txtAmtToBeRecoveredHBA" id="txtAmtToBeRecoveredHBA${Counter.index}" size="10" onkeypress="digitFormat(this);"value="${SchDtls[7]}"  style="text-align: right" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtTreasuryNameHBA" id="txtTreasuryNameHBA${Counter.index}" size="30" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVoucherNoHBA" id="txtVoucherNoHBA${Counter.index}" value="${SchDtls[4]}" size="15" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<fmt:formatDate value="${SchDtls[5]}" pattern="dd/MM/yyyy" var="voucherDate"/>
					   		<input type="text" name="txtVoucherDateHBA" id="txtVoucherDateHBA${Counter.index}" ${varDisabled} maxlength="10" value="${voucherDate}" size="9" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/><img id="imgVoucher" ${varImageDisabled} src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,'txtVoucherDateHBA${Counter.index}', 375, 570);" />
					   	</td>
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblHouseAdvance')" ${varImageDisabled}/>
						</td>		   						   		
					</tr>
				    
				  </c:if>	
				</c:forEach>
			</c:when>
		</c:choose>
</table>
</div>

<table width="100%">
<tr>
	<td width="20%">
		<fmt:message key="CMN.HODASSTREMARKS" bundle="${lnaLabels}"></fmt:message>
	</td>
	<td width="80%" >
		<input type="text" id="txtUserRemarksHBA" name="txtUserRemarksHBA" size="90" value="${HouseDtls[12]}" ${varDisabled}/>
	</td>
</tr>
<c:if test="${UserType == 'HOD'}">
<tr>
	<td width="20%">
		<fmt:message key="CMN.HODREMARKS" bundle="${lnaLabels}"></fmt:message>
	</td>
	<td width="80%" >
		<input type="text" id="txtHODRemarksHBA" name="txtHODRemarksHBA" size="90" value=""/>
	</td>
</tr>
</c:if>
</table>
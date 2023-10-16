<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="MotorDtls" value="${resValue.lLstMotorDtls}" />
<c:set var="VocherDtlsMCA" value="${resValue.lLstVocherDtlsMCA}" />

<c:set var="UserType" value="${resValue.UserType}"></c:set>
<c:if test="${UserType == 'HOD'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>	
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
</c:if>	


<input type="hidden" id="hidMotorId" name="hidMotorId" value="${MotorDtls[13]}"/>
<input type="hidden" id="txtTransactionIdMCA" name="txtTransactionIdMCA" value="${MotorDtls[11]}"/>
<input type="hidden" id="hidTotalPrinRecoveredMCA" name="hidTotalPrinRecoveredMCA" value="" />
<input type="hidden" id="hidTotalIntRecoveredMCA" name="hidTotalIntRecoveredMCA" value="" />

<table width="80%">
	<tr>
		<td width="15%"><fmt:message key="CMN.SANCAMT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtSancAmountMCA"  name="txtSancAmountMCA" style="text-align: right;" onkeypress="digitFormat(this);" value="${MotorDtls[0]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>	
		<td width="15%"><fmt:message key="CMN.SANCDATE"	bundle="${lnaLabels}" /></td>
		<td width="20%">
			<fmt:formatDate value="${MotorDtls[1]}" pattern="dd/MM/yyyy" var="sanctionedDate"/>
			<input type="text" name="txtSanctionedDateMCA" id="txtSanctionedDateMCA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "${sanctionedDate}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtSanctionedDateMCA",375,570)' ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.SUBTYPE" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%">
			<select name="cmbMotorSubType" id="cmbMotorSubType" style="width:170px" ${varDisabled}>
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="LoanSubType" items="${resValue.lstVehicleSubType}">
					<c:choose>
						<c:when test="${MotorDtls[2] == LoanSubType.lookupId}">
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
			<input type="text"	id="txtInterestRateMCA" name="txtInterestRateMCA"  style="text-align: right;" onkeypress="amountFormat(this);" value="${MotorDtls[3]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.ORDERNO" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtOrderNoMCA"  name="txtOrderNoMCA" value="${MotorDtls[4]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.ORDERDATE" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<fmt:formatDate value="${MotorDtls[5]}" pattern="dd/MM/yyyy" var="orderDate"/>
			<input type="text" id="txtOrderDateMCA" name="txtOrderDateMCA"maxlength="10" value="${orderDate}" onkeypress="digitFormat(this);dateFormat(this);"onBlur="validateDate(this);" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtOrderDateMCA",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.TOTALPRINCAMT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalPrincAmtMCA" name="txtTotalPrincAmtMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value="${MotorDtls[0]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.TOTALINTERESTAMT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalInterestAmtMCA" name="txtTotalInterestAmtMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value="${MotorDtls[6]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.TOTALPRINCINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalPrincInsMCA" name="txtTotalPrincInsMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value="${MotorDtls[7]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.TOTALINTERESTINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalInterestInsMCA" name="txtTotalInterestInsMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value="${MotorDtls[8]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.RECOVEREDPRINCINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<c:if test="${MotorDtls[7] !=null}">
				<input type="text"	id="txtRecoverPrincInsMCA" name="txtRecoverPrincInsMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value="${MotorDtls[7] - MotorDtls[9]}" onblur="addRowForPrincipalRecoveredInsMCA();" ${varDisabled}/>
			</c:if>
			<c:if test="${MotorDtls[7] ==null}">
				<input type="text"	id="txtRecoverPrincInsMCA" name="txtRecoverPrincInsMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value="" onblur="addRowForPrincipalRecoveredInsMCA();" ${varDisabled}/>
			</c:if>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.RECOVEREDINTERESTINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<c:if test="${MotorDtls[8] !=null}">
				<input type="text"	id="txtRecoverInterestInsMCA" name="txtRecoverInterestInsMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value="${MotorDtls[8] - MotorDtls[10]}"  onblur="addRowForInterestRecoveredInsMCA();" ${varDisabled}/>
			</c:if>
			<c:if test="${MotorDtls[8] ==null}">
				<input type="text"	id="txtRecoverInterestInsMCA" name="txtRecoverInterestInsMCA"  style="text-align: right;" onkeypress="digitFormat(this);" value=""  onblur="addRowForInterestRecoveredInsMCA();" ${varDisabled}/>
			</c:if>
			
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
</table>
<br>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 300px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblMotorAdvance" border="1" width="100%">
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

			<c:when test="${VocherDtlsMCA != null}">
	
				<c:forEach var="SchDtls" items="${VocherDtlsMCA}" varStatus="Counter">					
				
				  <c:if test="${SchDtls[0] == 'V'}">
					<tr>
						<td class="tds" align="center">
							${Counter.index+1}
							<input type="hidden" id="hidSchdlDtlsPkMCA${Counter.index}" name="hidSchdlDtlsPkMCA" value="${SchDtls[8]}" />
						</td>
						<td class="tds" align="center">	
							<input type="radio" name="radioVoucherChallanMCA${Counter.index}" id="radioVoucherMCA${Counter.index}" value="V" size="5" checked="checked" disabled="disabled"/>Voucher<input type="radio" name="radioVoucherChallanMCA${Counter.index}" id="radioChallanMCA${Counter.index}" value="C" onclick="enableDisableVchrChallan(${Counter.index});" size="5" disabled="disabled" />Challan
						</td>	
						<td class="tds" align="center">
							<input type="text" id="txtInstlmntVchrMCA${Counter.index}" name="txtInstlmntVchrMCA" size=5 onkeypress="digitFormat(this);" value="${SchDtls[1]}" onblur="validateInstallment(${Counter.index})" ${varDisabled} style="text-align: right;" />
						</td>
						<td class="tds" align="center">
							<select name="cmbMonthMCA" id="cmbMonthMCA${Counter.index}" ${varDisabled}>
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
					   		<select name="cmbYearMCA" id="cmbYearMCA${Counter.index}" ${varDisabled}>
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
								<input type="text" name="txtPrinInstAmountMCA" id="txtPrinInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[4]}"  style="text-align: right" ${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountMCA" id="txtInterestInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>
						<c:if test="${SchDtls[9]=='I'}">
							<td class="tds" align="center">	 
								<input type="text" name="txtPrinInstAmountMCA" id="txtPrinInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right" ${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountMCA" id="txtInterestInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[4]}"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>
								   	
					   	<td class="tds" align="center">
					   		<input type="text" name="txtAmtToBeRecoveredMCA" id="txtAmtToBeRecoveredMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"value="${SchDtls[5]}"  style="text-align: right" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtTreasuryNameMCA" id="txtTreasuryNameMCA${Counter.index}" size="30" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVoucherNoMCA" id="txtVoucherNoMCA${Counter.index}" value="${SchDtls[6]}" size="15" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<fmt:formatDate value="${SchDtls[7]}" pattern="dd/MM/yyyy" var="voucherDate"/>
					   		<input type="text" name="txtVoucherDateMCA" id="txtVoucherDateMCA${Counter.index}" maxlength="10" value="${voucherDate}" ${varDisabled} size="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/><img id="imgVoucher" ${varImageDisabled} src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,'txtVoucherDateMCA${Counter.index}', 375, 570);" />	
					   	</td>
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblMotorAdvance')" ${varImageDisabled}/>
						</td>		   						   		
					</tr>
				  </c:if>				  
				  <c:if test="${SchDtls[0] == 'C'}">
				  <tr>
						<td class="tds" align="center">
							${Counter.index+1}
							<input type="hidden" id="hidSchdlDtlsPkMCA${Counter.index}" name="hidSchdlDtlsPkMCA" value="${SchDtls[6]}" />
						</td>
						<td class="tds" align="center">	
							<input type="radio" name="radioVoucherChallanMCA${Counter.index}" id="radioVoucherMCA${Counter.index}" value="V" onclick="enableDisableVchrChallan(${Counter.index});" size="5" disabled="disabled"/>Voucher<input type="radio" name="radioVoucherChallanMCA${Counter.index}" id="radioChallanMCA${Counter.index}" value="C" onclick="enableDisableVchrChallan(${Counter.index});" size="5" checked="checked" disabled="disabled" />Challan
						</td>	
						<td class="tds" align="center">
							From:<input type="text" id="txtInstlmntChallanFromMCA${Counter.index}" value="${SchDtls[1]}" ${varDisabled} name="txtInstlmntChallanFromMCA${Counter.index}" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment(${Counter.index})"/>To:<input type="text" ${varDisabled} id="txtInstlmntChallanToMCA${Counter.index}" value="${SchDtls[2]}" name="txtInstlmntChallanToMCA${Counter.index}" size=2 onkeypress="digitFormat(this);" onblur="validateInstallment(${Counter.index})"/>
						</td>
						<td class="tds" align="center">
							<select name="cmbMonthMCA" id="cmbMonthMCA${Counter.index}" >
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
					   		<select name="cmbYearMCA" id="cmbYearMCA${Counter.index}" >
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
								<input type="text" name="txtPrinInstAmountMCA" id="txtPrinInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[3]}"  style="text-align: right" ${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountMCA" id="txtInterestInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>
						<c:if test="${SchDtls[8]=='I'}">
							<td class="tds" align="center">	 
								<input type="text" name="txtPrinInstAmountMCA" id="txtPrinInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  readonly="readonly"  style="text-align: right" ${varDisabled}/>
							</td>
							<td class="tds" align="center">	 
								<input type="text" name="txtInterestInstAmountMCA" id="txtInterestInstAmountMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[3]}"  style="text-align: right" ${varDisabled}/>
							</td>			
						</c:if>				  
					   	<td class="tds" align="center">
					   		<input type="text" name="txtAmtToBeRecoveredMCA" id="txtAmtToBeRecoveredMCA${Counter.index}" size="10" onkeypress="digitFormat(this);"value="${SchDtls[7]}"  style="text-align: right" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtTreasuryNameMCA" id="txtTreasuryNameMCA${Counter.index}" size="30" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVoucherNoMCA" id="txtVoucherNoMCA${Counter.index}" value="${SchDtls[4]}" size="15" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<fmt:formatDate value="${SchDtls[5]}" pattern="dd/MM/yyyy" var="voucherDate"/>
					   		<input type="text" name="txtVoucherDateMCA" id="txtVoucherDateMCA${Counter.index}" maxlength="10" ${varDisabled} value="${voucherDate}" size="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/><img id="imgVoucher" ${varImageDisabled} src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,'txtVoucherDateMCA${Counter.index}', 375, 570);" />
					   	</td>
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblMotorAdvance')" ${varImageDisabled}/>
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
		<input type="text" id="txtUserRemarksMCA" name="txtUserRemarksMCA" size="90" value="${MotorDtls[12]}" ${varDisabled}/>
	</td>
</tr>
<c:if test="${UserType == 'HOD'}">
<tr>
	<td width="20%">
		<fmt:message key="CMN.HODREMARKS" bundle="${lnaLabels}"></fmt:message>
	</td>
	<td width="80%" >
		<input type="text" id="txtHODRemarksMCA" name="txtHODRemarksMCA" size="90" value=""/>
	</td>
</tr>
</c:if>
</table>
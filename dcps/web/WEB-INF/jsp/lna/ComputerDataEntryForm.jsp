<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/lna/loanDataEntryForm.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="CompDtls" value="${resValue.lLstCompDtls}" />
<c:set var="VocherDtlsCA" value="${resValue.lLstVocherDtlsCA}" />
<c:set var="UserType" value="${resValue.UserType}"></c:set>
<c:if test="${UserType == 'HOD'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>	
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
</c:if>	
<input type="hidden" id="hidCompId" name="hidCompId" value="${CompDtls[10]}"/>
<input type="hidden" id="txtTransactionIdCA" name="txtTransactionIdCA" value="${CompDtls[8]}"/>
<input type="hidden" id="hidTrnEmpLoanPk" name="hidTrnEmpLoanPk" value="${resValue.TrnEmpLoanPk}" />
<input type="hidden" id="hidTotalRecoveredCA" name="hidTotalRecoveredCA" value="" />																									
																																																			
<table width="80%">
	<tr>
		<td width="15%"><fmt:message key="CMN.SANCAMT" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtSancAmountCA"  name="txtSancAmountCA" onkeypress="digitFormat(this);" value="${CompDtls[0]}" style="text-align: right;" ${varDisabled}/>		
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>	
		</td>		
		<td width="15%"><fmt:message key="CMN.SANCDATE"	bundle="${lnaLabels}" /></td>
		<td width="20%">
			<fmt:formatDate value="${CompDtls[1]}" pattern="dd/MM/yyyy" var="sanctionedDate"/>
			<input type="text" name="txtSanctionedDateCA" id="txtSanctionedDateCA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "${sanctionedDate}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtSanctionedDateCA",375,570)' ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.SUBTYPE" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%">
			<select name="cmbComputerSubType" id="cmbComputerSubType" style="width:170px" ${varDisabled}>
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="LoanSubType" items="${resValue.lstComputerSubType}">
					<c:choose>
						<c:when test="${CompDtls[2] == LoanSubType.lookupId}">
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
			<input type="text"	id="txtInterestRateCA" name="txtInterestRateCA"  onkeypress="amountFormat(this);" value="${CompDtls[3]}" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.ORDERNO" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtOrderNoCA"  name="txtOrderNoCA" value="${CompDtls[4]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.ORDERDATE" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<fmt:formatDate value="${CompDtls[5]}" pattern="dd/MM/yyyy" var="orderDate"/>
			<input type="text" id="txtOrderDateCA" name="txtOrderDateCA"maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"onBlur="validateDate(this);" value="${orderDate}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtOrderDateCA",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>
	<tr>
		<td width="15%"><fmt:message key="CMN.TOTALINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtTotalInsCA" name="txtTotalInsCA" onkeypress="digitFormat(this);" value="${CompDtls[6]}" style="text-align: right;" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>			
		</td>		
		<td width="15%"><fmt:message key="CMN.RECOVEREDINS" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<c:if test="${CompDtls[6] != null}">
				<input type="text"	id="txtRecoverInsCA" name="txtRecoverInsCA" onkeypress="digitFormat(this);" value="${CompDtls[6] - CompDtls[7]}" style="text-align: right" onblur="addRowForRecoveredIns();" ${varDisabled}/>
			</c:if>
			<c:if test="${CompDtls[6] == null}">
				<input type="text"	id="txtRecoverInsCA" name="txtRecoverInsCA" onkeypress="digitFormat(this);" value="" style="text-align: right" onblur="addRowForRecoveredIns();" ${varDisabled}/>
			</c:if>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>	
	</tr>	
</table>

<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 300px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblComputerAdvance" border="1" width="100%">
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

			<c:when test="${VocherDtlsCA != null}">
	
				<c:forEach var="SchDtls" items="${VocherDtlsCA}" varStatus="Counter">					
				
				  <c:if test="${SchDtls[0] == 'V'}">
					<tr>
						<td class="tds" align="center">
							${Counter.index+1}							
							<input type="hidden" id="hidSchdlDtlsPk${Counter.index}" name="hidSchdlDtlsPk" value="${SchDtls[8]}" />
						</td>
						<td class="tds" align="center">							
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioVoucher${Counter.index}" value="V" onclick="enableDisableVchrChallan(${Counter.index});" size="5" checked="checked" disabled="disabled"/>Voucher
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioChallan${Counter.index}" value="C" onclick="enableDisableVchrChallan(${Counter.index});" size="5" disabled="disabled" />Challan
						</td>	
						<td class="tds" align="center">
							<input type="text" id="txtInstlmntVchr${Counter.index}" name="txtInstlmntVchr" size=5 onkeypress="digitFormat(this);" value="${SchDtls[1]}" onblur="validateInstallment(${Counter.index})" ${varDisabled} style="text-align: right;" />
						</td>
						<td class="tds" align="center">
							<select name="cmbMonthCA" id="cmbMonthCA${Counter.index}" ${varDisabled}>
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
					   		<select name="cmbYearCA" id="cmbYearCA${Counter.index}" ${varDisabled}>
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
						<td class="tds" align="center">	 
							<input type="text" name="txtInstAmountCA" id="txtInstAmountCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[4]}"  style="text-align: right" ${varDisabled}/>
						</td>
						<td class="tds" align="center">	 
							<input type="text" name="txtInterestInstAmount" id="txtInterestInstAmount${Counter.index}" size="7" onkeypress="digitFormat(this);"  value=""  style="text-align: right" readonly="readonly"/>
						</td>					   	
					   	<td class="tds" align="center">
					   		<input type="text" name="txtAmtToBeRecoveredCA" id="txtAmtToBeRecoveredCA${Counter.index}" size="10" onkeypress="digitFormat(this);"value="${SchDtls[5]}"  style="text-align: right" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtTreasuryNameCA" id="txtTreasuryNameCA${Counter.index}" size="30" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVoucherNoCA" id="txtVoucherNoCA${Counter.index}" value="${SchDtls[6]}" size="15" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<fmt:formatDate value="${SchDtls[7]}" pattern="dd/MM/yyyy" var="voucherDate"/>
					   		<input type="text" name="txtVoucherDateCA" id="txtVoucherDateCA${Counter.index}" maxlength="10" value="${voucherDate}" size="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" ${varDisabled}/> 
					   		<img id="imgVoucher" src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,'txtVoucherDateCA${Counter.index}', 375, 570);" ${varImageDisabled}/>	
					   	</td>
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblComputerAdvance')" ${varImageDisabled}/>
						</td>		   						   		
					</tr>
				  </c:if>				  
				  <c:if test="${SchDtls[0] == 'C'}">
				  <tr>
						<td class="tds" align="center">
							${Counter.index+1}							
							<input type="hidden" id="hidSchdlDtlsPk${Counter.index}" name="hidSchdlDtlsPk" value="${SchDtls[6]}" />
						</td>
						<td class="tds" align="center">
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioVoucher${Counter.index}" value="V" size="5" disabled="disabled"/>Voucher
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioChallan${Counter.index}" value="C" size="5" checked="checked" disabled="disabled" />Challan														
						</td>	
						<td class="tds" align="center">
							From:<input type="text" id="txtInstlmntChallanFrom${Counter.index}" value="${SchDtls[1]}" name="txtInstlmntChallanFrom${Counter.index}" size="2" onkeypress="digitFormat(this);" ${varDisabled} onblur="validateInstallment(${Counter.index})"/>To:<input type="text" id="txtInstlmntChallanTo${Counter.index}" value="${SchDtls[2]}" name="txtInstlmntChallanTo${Counter.index}" size="2" ${varDisabled} onkeypress="digitFormat(this);" onblur="validateInstallment(${Counter.index})"/>
						</td>
						<td class="tds" align="center">
							<select name="cmbMonthCA" id="cmbMonthCA${Counter.index}" >
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
					   		<select name="cmbYearCA" id="cmbYearCA${Counter.index}" >
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
						<td class="tds" align="center">	 
							<input type="text" name="txtInstAmountCA" id="txtInstAmountCA${Counter.index}" size="10" onkeypress="digitFormat(this);"  value="${SchDtls[3]}"  style="text-align: right" ${varDisabled}/>
						</td>
						<td class="tds" align="center">	 
							<input type="text" name="txtInterestInstAmount" id="txtInterestInstAmount${Counter.index}" size="7" onkeypress="digitFormat(this);"  value=""  style="text-align: right"readonly="readonly"/>
						</td>					  
					   	<td class="tds" align="center">
					   		<input type="text" name="txtAmtToBeRecoveredCA" id="txtAmtToBeRecoveredCA${Counter.index}" size="10" onkeypress="digitFormat(this);"value="${SchDtls[7]}"  style="text-align: right" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtTreasuryNameCA" id="txtTreasuryNameCA${Counter.index}" size="30" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVoucherNoCA" id="txtVoucherNoCA${Counter.index}" value="${SchDtls[4]}" size="15" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">
					   		<fmt:formatDate value="${SchDtls[5]}" pattern="dd/MM/yyyy" var="voucherDate"/>
					   		<input type="text" name="txtVoucherDateCA" id="txtVoucherDateCA${Counter.index}" maxlength="10" value="${voucherDate}" size="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" ${varDisabled}/> 
					   		<img id="imgVoucher" src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,'txtVoucherDateCA${Counter.index}', 375, 570);" ${varImageDisabled}/>	
					   	</td>
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblComputerAdvance')" ${varImageDisabled}/>
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
		<input type="text" id="txtUserRemarksCA" name="txtUserRemarksCA" size="90" value="${CompDtls[9]}" ${varDisabled}/>
	</td>
</tr>
<c:if test="${UserType == 'HOD'}">
<tr>
	<td width="20%">
		<fmt:message key="CMN.HODREMARKS" bundle="${lnaLabels}"></fmt:message>
	</td>
	<td width="80%" >
		<input type="text" id="txtHODRemarksCA" name="txtHODRemarksCA" size="90" value=""/>
	</td>
</tr>
</c:if>
</table>
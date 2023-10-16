<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>

<script type="text/javascript" src="script/gpf/dataEntryForm.js"></script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="ObjCur" value="${resValue.ojbCur}" />

<c:if test="${resValue.userType == 'DDO'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
</c:if>

<input type="hidden" id="hidRADtlsCurPk" name="hidRADtlsCurPk" value="${ObjCur[10]}" />

<table width="100%">
	<tr>
		<td width="15%">
			<fmt:message key="CMN.PURPOSECATEGORY" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="20%">			
			<select name="cmbPurposeCategoryCur" id="cmbPurposeCategoryCur" onChange="validateCurAdvance();" style="width:230px" ${varDisabled} >
				<option value="-1" selected="selected"><fmt:message	key="COMMON.DROPDOWN.SELECT"/></option>
			  	<c:forEach var="Purpose" items="${resValue.lstPurposeCatRA}">
				<c:choose>
					<c:when test="${ObjCur[9] == Purpose.lookupId}">
						<option value="${Purpose.lookupId}" selected="selected">
						<c:out value="${Purpose.lookupDesc}"></c:out>
						</option>
					</c:when>
					<c:otherwise>
							<option value="${Purpose.lookupId}">
						<c:out value="${Purpose.lookupDesc}"></c:out></option>
					</c:otherwise>
				</c:choose>
				</c:forEach>				
			</select><label class="mandatoryindicator" ${varImageDisabled}>*</label>			 						
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.SANCAMT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text"  size="20%" name="txtSancAmountCur" id="txtSancAmountCur" value ="${ObjCur[1]}" maxlength="10" onkeypress="amountFormat(this);" style="text-align: right" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.SANCDATE" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<fmt:formatDate value="${ObjCur[6]}" pattern="dd/MM/yyyy" var="SancDate"/>
			<input type="text" name="txtSancDateCur" id="txtSancDateCur" value ="${SancDate}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtSancDateCur",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.VOUCHERNO" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text"  size="20%" name="txtVchrNoCurRA" id="txtVchrNoCurRA" value ="${ObjCur[7]}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.VOUCHERDATE" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<fmt:formatDate value="${ObjCur[8]}" pattern="dd/MM/yyyy" var="VchrDate"/>
			<input type="text" name="txtVchrDateCurRA" id="txtVchrDateCurRA" value ="${VchrDate}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtVchrDateCurRA",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">																					
			<fmt:message key="CMN.TOTALNOINSTALL" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtNoOfInstlmntCur" name="txtNoOfInstlmntCur" value ="${ObjCur[2]}" onblur="validateTotalNoOfInstallmentCur();" ${varDisabled} />
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.INSTALLAMTPN" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtInstlmntAmtPerMonCur" name="txtInstlmntAmtPerMonCur" value ="${ObjCur[3]}" readonly />
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.ODDINSTALLMENT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtFirstOddInstlmntCur" name="txtFirstOddInstlmntCur" value ="${ObjCur[5]}" readonly />
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.NOOFRECINSTLMNT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtRecoveredInstlmntCur" name="txtRecoveredInstlmntCur" onblur="addRowRecInstlmnt();" value ="${ObjCur[2] - ObjCur[4]}" ${varDisabled} />
		</td>
	</tr>
	
</table>
<br><br>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 200px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblRACurDtls" border="1" width="98%">
	   <tr class="datatableheader" >
			<td width="1%" class="datatableheader"><fmt:message key="CMN.SRNO" bundle="${gpfLabels}" ></fmt:message></td>
			<td width="10%" class="datatableheader"><fmt:message key="CMN.VCHRCHALLAN" bundle="${gpfLabels}" ></fmt:message></td>
			<td width="7%" class="datatableheader"><fmt:message key="CMN.INSTALLMENTNO" bundle="${gpfLabels}" ></fmt:message></td>
			<td width="7%" class="datatableheader"><fmt:message key="CMN.MONTH" bundle="${gpfLabels}" ></fmt:message></td>
			<td width="5%" class="datatableheader"><fmt:message key="CMN.YEAR" bundle="${gpfLabels}" ></fmt:message></td>
			<td width="9%" class="datatableheader"><fmt:message key="CMN.INSTALLMENTAMOUNT" bundle="${gpfLabels}" ></fmt:message></td>
			<td width="12%" class="datatableheader"><fmt:message key="CMN.VCHRCHLLNNO" bundle="${gpfLabels}" ></fmt:message></td>
			<td width="12%" class="datatableheader"><fmt:message key="CMN.VCHRCHLLNDATE" bundle="${gpfLabels}" ></fmt:message></td>										
			<td width="1%" class="datatableheader"><fmt:message key="CMN.DELETE" bundle="${gpfLabels}"></fmt:message></td>						   						   
		</tr>
		
		<c:choose>

			<c:when test="${resValue.ScheduleDtls != null}">
	
				<c:forEach var="SchDtls" items="${resValue.ScheduleDtls}" varStatus="Counter">					
				
				  <c:if test="${SchDtls[0] == 'Voucher'}">
					<tr>
						<td class="tds" align="center">
							<input type="label" id="lblSrNoRaCur${Counter.index}" name="lblSrNoRaCur" value="${Counter.index+1}" style="text-align: center" size="3" readonly/>
							<input type="hidden" id="hidSchdlDtlsPk${Counter.index}" name="hidSchdlDtlsPk" value="${SchDtls[7]}" />
						</td>
						<td class="tds" align="center">	
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioVoucher${Counter.index}" value="V" onclick="enableDisableVchrChallan(${Counter.index});" size="7" checked="checked" disabled/>Voucher
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioChallan${Counter.index}" value="C" onclick="enableDisableVchrChallan(${Counter.index});" size="7" disabled/>Challan
						</td>	
						<td class="tds" align="center">
							<input type="text" id="txtInstlmntVchr${Counter.index}" name="txtInstlmntVchr" size=5 onkeypress="digitFormat(this);" value="${SchDtls[6]}" onblur="validateInstallment('+row+')" ${varDisabled} />
						</td>
						<td class="tds" align="center">
							<select name="cmbMonthCur" id="cmbMonthCur${Counter.index}" ${varDisabled} style="width: 100px">
								<option value="-1" selected="selected"><fmt:message	key="COMMON.DROPDOWN.SELECT"/></option>
								  	<c:forEach var="Mon" items="${resValue.lLstMonths}">
									<c:choose>
										<c:when test="${SchDtls[8] == Mon.id}">
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
					   		<select name="cmbYearCur" id="cmbYearCur${Counter.index}" ${varDisabled} style="width: 65px">
					   			<option value="-1" selected="selected"><fmt:message	key="COMMON.DROPDOWN.SELECT"/></option>
								  	<c:forEach var="Year" items="${resValue.lLstYears}">
									<c:choose>
										<c:when test="${SchDtls[9] == Year.id}">
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
							<input type="text" name="txtInstAmountRACur" id="txtInstAmountRACur${Counter.index}" size="10" value="${SchDtls[5]}" onfocus="onFocus(this)"  onblur="onBlur(this)setValidAmountFormat(this)" onkeypress="amountFormat(this)"  style="text-align: right" ${varDisabled}/>
						</td>	
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVchrChallnNoRACur" id="txtVchrChallnNoRACur${Counter.index}" size="10" value="${SchDtls[3]}" onfocus="onFocus(this)"  onblur="onBlur(this)" ${varDisabled}/>
					   	</td>	
					   	<td class="tds" align="center">
					   		<fmt:formatDate value="${SchDtls[4]}" pattern="dd/MM/yyyy" var="RASchVchrDate"/>
					   		<input type="text" name="txtVchrChallnDateRACur" id="txtVchrChallnDateRACur${Counter.index}" maxlength="10" value="${RASchVchrDate}" size="10" onkeypress="digitFormat(this)dateFormat(this)" onblur="onBlur(this)chkValidDate(this)" ${varDisabled}/>
					   		<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtVchrChallnDateRACur${Counter.index}",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
					   	</td>			
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblRACurDtls')" ${varImageDisabled}/>
						</td>		   						   		
					</tr>
				  </c:if>
				  
				  <c:if test="${SchDtls[0] == 'Challan'}">
				    <tr>
						<td class="tds" align="center">
							<input type="label" id="lblSrNoRaCur${Counter.index}" name="lblSrNoRaCur" value="${Counter.index+1}" style="text-align: center" size="3" readonly/>
							<input type="hidden" id="hidSchdlDtlsPk${Counter.index}" name="hidSchdlDtlsPk" value="${SchDtls[6]}" />
						</td>
						<td class="tds" align="center">	
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioVoucher${Counter.index}" value="V" onclick="enableDisableVchrChallan(${Counter.index});" size="7" disabled/>Voucher
							<input type="radio" name="radioVoucherChallan${Counter.index}" id="radioChallan${Counter.index}" value="C" onclick="enableDisableVchrChallan(${Counter.index});" size="7" checked="checked" disabled/>Challan
						</td>	
						<td class="tds" align="center" width="12%">
							From:<input type="text" id="txtInstlmntChallanFrom${Counter.index}" name="txtInstlmntChallanFrom${Counter.index}" size=5 onkeypress="digitFormat(this);" value="${SchDtls[4]}" onblur="validateInstallment(${Counter.index})" ${varDisabled} />
							To:<input type="text" id="txtInstlmntChallanTo${Counter.index}" name="txtInstlmntChallanTo${Counter.index}" size=5 onkeypress="digitFormat(this);" value="${SchDtls[5]}" onblur="validateInstallment(${Counter.index})" ${varDisabled} />
						</td>
						<td class="tds" align="center">
					   		
					   	</td>
					   	<td class="tds" align="center">	
					   		
					   	</td>
						<td class="tds" align="center">	 
							<input type="text" name="txtInstAmountRACur" id="txtInstAmountRACur${Counter.index}" size="10" value="${SchDtls[3]}" onfocus="onFocus(this)"  onblur="onBlur(this)setValidAmountFormat(this)" onkeypress="amountFormat(this)"  style="text-align: right" ${varDisabled}/>
						</td>
					   	<td class="tds" align="center">
					   		<input type="text" name="txtVchrChallnNoRACur" id="txtVchrChallnNoRACur${Counter.index}" size="10" value="${SchDtls[1]}" onfocus="onFocus(this)"  onblur="onBlur(this)" ${varDisabled}/>
					   	</td>
					   	<td class="tds" align="center">	
					   		<fmt:formatDate value="${SchDtls[2]}" pattern="dd/MM/yyyy" var="RASchChlnDate"/>
					   		<input type="text" name="txtVchrChallnDateRACur" id="txtVchrChallnDateRACur${Counter.index}" maxlength="10" value="${RASchChlnDate}" size="10" onkeypress="digitFormat(this)dateFormat(this)" onblur="onBlur(this)chkValidDate(this)" ${varDisabled}/>
					   		<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtVchrChallnDateRACur${Counter.index}",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
					   	</td>	
					   	<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblRACurDtls')" ${varImageDisabled}/>
						</td>					   	
					</tr>
				  </c:if>	
				</c:forEach>
			</c:when>
		</c:choose>
		
</table>


</div>
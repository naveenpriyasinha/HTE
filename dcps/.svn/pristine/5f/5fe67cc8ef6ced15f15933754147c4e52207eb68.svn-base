
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<script type="text/JavaScript" src="script/gpf/gpfChangeSubscription.js"></script>

<script type="text/javascript" >
	LISTMONTH = '';
</script>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />
<input type="hidden" name="hidTableRowId" id="hidTableRowId" value="1" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empVO" value="${resValue.GPFEmpVO}" />
<c:set var="TransactionID" value="${resValue.TransactionID}" />
<c:set var="currentDate" value="${resValue.lDtCurrDate}" />
<c:set var="changeSubList" value="${resValue.changeSubList}" />
<c:set var="LastSchedule" value="${resValue.LastSchedule}" />

<c:if test="${resValue.userType == 'DEOAPP' || resValue.userType == 'HO'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
	<c:set var="varButtonDisabled" value="display:none"></c:set>
</c:if>
<c:if test="${resValue.userType == 'HO'}">
	<c:set var="varRemarksDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabledHO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == 'DEOAPP' }">
	<c:set var="varDisabledForDEOAPP" value="disabled='disabled'"></c:set>
</c:if>
<br />
<c:forEach var="month" items="${resValue.MonthList}">
	<script> LISTMONTH += '<option value="${month.id}"> ${month.desc}</option>';</script>
</c:forEach>
<input type="hidden" name="hidChangeSubID" id="hidChangeSubID" value="${changeSubList[3]}" />

<input type="hidden" name="hidLastScheduleMonth" id="hidLastScheduleMonth" value="${LastSchedule}" />
<input type="hidden" name="hidLastScheduleYear" id="hidLastScheduleYear" value="" />

		<table id="table1" width="90%" cellspacing="4" cellpadding="4">
			<tr>
				<td width="20%" align="left"><fmt:message key="CMN.SYSENTRYDATE" bundle="${gpfLables}"></fmt:message></td>
				<td width="30%" align="left">
				
				<input type="text" size="20%" name='txtSysEntryDate' id="txtSysEntryDate"	value="${currentDate}" readonly="readonly" />
				<c:choose>
				<c:when test="${changeSubList[0]!=null}">
					<input type="hidden" size="20%" name='txtTID' id="txtTID"	value="${changeSubList[0]}" readonly="readonly" ${varDisabled} />
				</c:when>
				<c:otherwise>
					<input type="hidden" size="20%" name='txtTID' id="txtTID"	value="${TransactionID}" readonly="readonly" ${varDisabled} />
				</c:otherwise>
				</c:choose>
				</td>
				<td width="20%" align="left"><fmt:message key="CMN.PHYAPPDATE" bundle="${gpfLables}"></fmt:message>
				<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${gpfLabels}" ></fmt:message></font></td>
				<td width="30%" align="left">
				<fmt:formatDate value="${changeSubList[4]}" pattern="dd/MM/yyyy" var="phyAppReceivedDate" />
					<input type="text" name="txtPhyAppReceivedDate" id="txtPhyAppReceivedDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 
							onBlur="validateDate(this);previousDateValidation(this);"
							value="${phyAppReceivedDate}" ${varDisabled} />
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtPhyAppReceivedDate",375,570)' style="cursor: pointer;" ${varImageDisabled}/>
				<label class="mandatoryindicator" ${varImageDisabled}>*</label>
				</td>
			</tr>
			
			<tr>
				<td width="20%" align="left"><fmt:message key="CMN.CURRSUBSCRIPTION" bundle="${gpfLables}"></fmt:message></td>
				<td width="30%" align="left">
				<input type="text" size="20%"name='txtCurrSubscription' id="txtCurrSubscription" value="${empVO[5]}" readonly="readonly"/>
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br />
		<table width="60%" border="1" cellspacing="0" id="tblChangeSub" align="center">
			<tr>
				<th width="8%" align="center"><fmt:message key="CMN.EFD"	bundle="${gpfLables}"></fmt:message><label class="mandatoryindicator" ${varImageDisabled}>*</label></th>
				<th width="10%" align="center"><fmt:message key="CMN.NEWSUBSCRIPTION" bundle="${gpfLables}"></fmt:message><label class="mandatoryindicator" ${varImageDisabled}>*</label></th>
			</tr>
			<tr>
				<td width="8%" align="center">
				<select name="cmblstEffectFromMonth" id="cmblstEffectFromMonth" style="width:120px" ${varDisabled} onchange="chckMonth();compareMonth(cmblstEffectFromMonth0,this,'Applicable month should be greater than Effective month ')">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="month" items="${resValue.MonthList}">
				<c:choose>
					<c:when test="${changeSubList[2] == month.id}">
						<option value="${month.id}" selected="selected">
						<c:out value="${month.desc}"></c:out>
						</option>
					</c:when>
					<c:otherwise>
							<option value="${month.id}">
								<c:out value="${month.desc}"></c:out>
							</option>
					</c:otherwise>
				</c:choose>
				</c:forEach>
				</select>
				</td>
				<td width="10%" align="center">
					<input type="text" name="txtNewSubscription" id="txtNewSubscription" onkeypress="amountFormat(this)" onblur="validateNewSubsAmount();" style="text-align: right" value="${changeSubList[1]}" ${varDisabledForDEOAPP} />
				</td>
								
			</tr>
		</table>
	
<br />
<c:if test="${resValue.userType != 'DEO' && resValue.requestType == 'CS'}">
<table>
<tr>
<td width="20%"><fmt:message key="CMN.VERIFIERREMARKS" bundle="${gpfLabels}"></fmt:message></td>
<td width="60%"><textarea NAME="txtApproverRemarks" id="txtApproverRemarks" ROWS="2" cols="60" ${varRemarksDisabled}>${changeSubList[5]}</textarea>
</td>
<td width="10%"></td>
</tr>
</table>
</c:if>
</br>
	<c:if test="${resValue.userType == 'HO' && resValue.requestType == 'CS'}">	
	<table>	
			<tr>
				<td width="20%"><fmt:message key="CMN.APPREMARKS" bundle="${gpfLabels}"></fmt:message></td>
				<td colspan="3" width="60%"><textarea NAME="txtHORemarks" id="txtHORemarks" ROWS="2" cols="60"></textarea>
					</td>
				<td width="10%"></td>					
			</tr>
			</table>
	</c:if>
		
<br />

<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request"/>
<script type="text/javascript" src="script/pensionpay/searchPensionCase.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="curRole" value="${resValue.lStrToRole}"/>
<c:set var="showCaseFor" value="${resValue.showCaseFor}"/>
<c:set var="lStrSearchCrt" value="${resValue.lStrSearchCrt}"/>
<c:set var="lStrSearchVal" value="${resValue.lStrSearchVal}"/>
<c:set var="elementCode" value="${resValue.elementCode}"/>
<script>
lSearchCrt = "${lStrSearchCrt}";
lSearchVal = "${lStrSearchVal}";
</script>
<div style="float:right;position:relative;right:50px;">
<table>
	<tr>
		<td><fmt:message key="PPMT.SEARCH" bundle="${pensionLabels}"></fmt:message></td>
		<td>
			<select name="cmbSearchCrt" id="cmbSearchCrt" onchange="showSearchCrtVal()" >
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="prh.ppo_no" ><fmt:message key="SEARCH.PPONO" bundle="${pensionLabels}"></fmt:message></option>
				<option value="mph.first_name"><fmt:message key="SEARCH.PNSRNAME" bundle="${pensionLabels}"></fmt:message></option>
				<!-- For Receive Online Cases -->
				<c:if test="${showCaseFor == 1}">
					<option value="mpd.registration_no"><fmt:message key="SEARCH.REGNO" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.PPO_REG_DATE"><fmt:message key="SEARCH.PPORCPTDT" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.PPO_INWARD_DATE"><fmt:message key="SEARCH.FILEUPLDDT" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.bank_code"><fmt:message key="SEARCH.BANKNAME" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.branch_code"><fmt:message key="SEARCH.BRANCHCODE" bundle="${pensionLabels}"></fmt:message></option>
					<option value="audi.post_id"><fmt:message key="SEARCH.AUDINAME" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.type_flag"><fmt:message key="SEARCH.CASETYPE" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.inward_mode"><fmt:message key="SEARCH.RCVMODE" bundle="${pensionLabels}"></fmt:message></option>
				</c:if>
				<!-- For Identification  -->
				<c:if test="${showCaseFor == 5 or showCaseFor == 6}">
					<option value="mph.date_of_retirement"><fmt:message key="SEARCH.DOR" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.commencement_date"><fmt:message key="SEARCH.PNSNSTRTDT" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.bank_code"><fmt:message key="SEARCH.BANKNAME" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.branch_code"><fmt:message key="SEARCH.BRANCHCODE" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.acount_no"><fmt:message key="SEARCH.ACNO" bundle="${pensionLabels}"></fmt:message></option>
					<option value="audi.post_id"><fmt:message key="SEARCH.AUDINAME" bundle="${pensionLabels}"></fmt:message></option>
					<option value="sch.call_date"><fmt:message key="SEARCH.CALLDATE" bundle="${pensionLabels}"></fmt:message></option>
					<option value="sch.schedule_status"><fmt:message key="SEARCH.IDENTSTATUS" bundle="${pensionLabels}"></fmt:message></option>
				</c:if>
				<!-- For View Identification Schedule-->
				<c:if test="${(showCaseFor == 6)}">
					<!-- <option value="sch.call_date"><fmt:message key="SEARCH.CALLDATE" bundle="${pensionLabels}"></fmt:message></option>
					<option value="sch.schedule_status"><fmt:message key="SEARCH.IDENTSTATUS" bundle="${pensionLabels}"></fmt:message></option> -->
				</c:if>	
				<!-- For First Pension Bill -->
				<c:if test="${showCaseFor == 10}">
					<option value="mph.date_of_retirement"><fmt:message key="SEARCH.DOR" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.commencement_date"><fmt:message key="SEARCH.PNSNSTRTDT" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.bank_code"><fmt:message key="SEARCH.BANKNAME" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.branch_code"><fmt:message key="SEARCH.BRANCHCODE" bundle="${pensionLabels}"></fmt:message></option>
				</c:if>	
				<!-- For Record Room and View Modified Cases-->
				<c:if test="${(showCaseFor == 15) || (showCaseFor == 20)}">
					<option value="prh.case_Status"><fmt:message key="SEARCH.CASESTATUS" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.case_owner"><fmt:message key="SEARCH.AUDINAME" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.bank_code"><fmt:message key="SEARCH.BANKNAME" bundle="${pensionLabels}"></fmt:message></option>
					<option value="mpd.branch_code"><fmt:message key="SEARCH.BRANCHCODE" bundle="${pensionLabels}"></fmt:message></option>
				</c:if>	
				<!-- For Draft Cases and View New Cases -->
				<c:if test="${(showCaseFor == 30) || (showCaseFor == 35)}">
					<option value="prh.commencement_date"><fmt:message key="SEARCH.PNSNSTRTDT" bundle="${pensionLabels}"></fmt:message></option>
					<option value="prh.case_owner"><fmt:message key="SEARCH.AUDINAME" bundle="${pensionLabels}"></fmt:message></option>
				</c:if>	
			</select>
		</td>
		<td id="txtSearch1" class="searchClass">
			<input id="txtSearch" type="text" onfocus="changeOnFocus(this)"  name="txtSearch"  size="15" value= "${lStrSearchVal}" />
			<div id="dtpicker" style="display:none"><img src="images/CalendarImages/ico-calendar.gif"  width="20" onClick="window_open('txtSearch',375,570,'','',0)" ></div>
		</td>
		<!-- case type combo -->
		<td id="txtSearch2" style="display:none" class="searchClass" >
			<select name="cmbSearch"  id="cmbSearch">
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='R'> <fmt:message key="TYPE.REGULAR" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='C'> <fmt:message key="TYPE.REVISED" bundle="${pensionLabels}"></fmt:message> </option>
			</select>
		</td>	
		
		<td id="txtSearch3" style="display:none" class="searchClass">
			<select name="cmbSearch" id="cmbSearch" >
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<c:forEach var="Auditor" items="${resValue.lLstAuditorDtls}">
					<option value='${Auditor.id}'>${Auditor.desc}</option>
				</c:forEach>
			</select>
		</td>
		<!-- inward mode combo -->
		<td id="txtSearch4" style="display:none" class="searchClass">
			<select name="cmbSearch"  id="cmbSearch">
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='<fmt:message key="INWDMODE.ONLINE" bundle="${pensionConstants}"></fmt:message>'> <fmt:message key="INWDMODE.ONLINE" bundle="${pensionConstants}"></fmt:message></option>
				<option value='<fmt:message key="INWDMODE.PHYSICAL" bundle="${pensionConstants}"></fmt:message>'> <fmt:message key="INWDMODE.PHYSICAL" bundle="${pensionConstants}"></fmt:message></option>
			</select>
		</td>
		<!-- bank name combo -->
		<td id="txtSearch5" style="display:none" class="searchClass">
			<select name="cmbSearch" id="cmbSearch" >
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<c:forEach var="Bank" items="${resValue.BankList}">
					<option value='${Bank.id}'>${Bank.desc}</option>
				</c:forEach>
			</select>
		</td>
		<!-- identification status combo -->
		<td id="txtSearch6" style="display:none" class="searchClass">
			<select name="cmbSearch" id="cmbSearch" >
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='<fmt:message key="IDENT.NOTSCHEDULED" bundle="${pensionConstants}"></fmt:message>'><fmt:message key="IDENT.NOTSCHEDULED" bundle="${pensionConstants}"></fmt:message> </option>
				<option value='<fmt:message key="IDENT.AWAITED" bundle="${pensionConstants}"></fmt:message>'> <fmt:message key="IDENT.AWAITED" bundle="${pensionConstants}"></fmt:message> </option>
				<option value=' <fmt:message key="IDENT.REMINDER" bundle="${pensionConstants}"></fmt:message> '> <fmt:message key="IDENT.REMINDER" bundle="${pensionConstants}"></fmt:message> </option>
			</select>
		</td>
		<!-- case status combo -->
		<td id="txtSearch7" style="display:none" class="searchClass">
			<select name="cmbSearch" id="cmbSearch" >
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='<fmt:message key="STATFLG.APPROVED" bundle="${pensionConstants}"></fmt:message>'> <fmt:message key="STATFLG.APPROVED" bundle="${pensionConstants}"></fmt:message> </option>
				<c:if test="${(showCaseFor == 15)}"> 
					<option value='<fmt:message key="STATFLG.MODIFIEDBYAUDITOR" bundle="${pensionConstants}"></fmt:message>'> <fmt:message key="STATFLG.MODIFIED" bundle="${pensionConstants}"></fmt:message> </option>
				</c:if>
				<c:if test="${(showCaseFor == 20)}"> 
					<option value='<fmt:message key="STATFLG.MODIFIED" bundle="${pensionConstants}"></fmt:message>'> <fmt:message key="STATFLG.MODIFIED" bundle="${pensionConstants}"></fmt:message> </option>
				</c:if>
				<option value='<fmt:message key="STATFLG.REJECTED" bundle="${pensionConstants}"></fmt:message>'> <fmt:message key="STATFLG.REJECTED" bundle="${pensionConstants}"></fmt:message> </option>
			</select>
		</td>
		<td>
			<a href="#" onclick="searchCases(${showCaseFor},${elementCode});"><img src="images/search.gif" align="right"/></a>
		</td>		
	</tr>
</table>
</div>	
<script>
//----Setting default value of search criteria starts <<<<<<<
var lObjOptions = document.getElementById("cmbSearchCrt").options;
var isCrt = false;
var isCombo = false;
for(var indx=0;indx<lObjOptions.length;indx++)
{
	if(lObjOptions[indx].value == lSearchCrt)
	{
		isCrt = true;
		lObjOptions[indx].selected = "selected";
	}
}
document.getElementById("cmbSearchCrt").value = lSearchCrt;
//----Setting default value of search criteria ends >>>>>>>

//----Setting default value of search value starts <<<<<<<
var lObjSelect = document.getElementsByName("cmbSearch");
showSearchCrtVal();
if(!isCrt)
{
	document.getElementById("cmbSearchCrt").options[0].selected = "selected";
}

if(lSearchCrt == '')
{
	document.getElementById("cmbSearchCrt").options[1].selected = "selected";
}
for(var indxSlct = 0;indxSlct < lObjSelect.length ;indxSlct++ )
{
	var lObjOpts = lObjSelect[indxSlct].options;
	for(var indxOpts = 0; indxOpts < lObjOpts.length;indxOpts++)
	{
		if(lObjOpts[indxOpts].value == lSearchVal)
		{
			isCombo = true;
			lObjOpts[indxOpts].selected = "selected";
		}
	}
}
if(!isCombo)
{
	document.getElementById("txtSearch").value = lSearchVal;
}
//----Setting default value of search value ends >>>>>>>

</script>
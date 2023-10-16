<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request"/>
<script type="text/javascript" src="script/pensionpay/searchChangeStmnt.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="curRole" value="${resValue.lStrToRole}"/>
<c:set var="showWLFor" value="${resValue.showWLFor}"/>
<c:set var="lStrSearchCrt" value="${resValue.lStrSearchCrt}"/>
<c:set var="lStrSearchVal" value="${resValue.lStrSearchVal}"/>
<c:set var="lStrSearchBankCode" value="${resValue.lStrSearchBankCode}"/>
<c:set var="lStrSearchBranchCode" value="${resValue.lStrSearchBranchCode}"/>
<c:set var="lStrSearchYearCode" value="${resValue.lStrSearchYearCode}"/>
<c:set var="lStrSearchMonthId" value="${resValue.lStrSearchMonthId}"/>
<script>
lSearchCrt = "${lStrSearchCrt}";
lSearchVal = "${lStrSearchVal}";
lSearchBankCode = "${lStrSearchBankCode}";
lSearchBranchCode = "${lStrSearchBranchCode}";
lSearchYearCode = "${lStrSearchYearCode}";
lSearchMonthId = "${lStrSearchMonthId}";
</script>
<div style="float:right;position:relative;right:50px;">
<table>
	<tr>
		<td><fmt:message key="CMN.SEARCH" bundle="${pensionLabels}"></fmt:message></td>
		<td>
			<select name="cmbSearchCrt" id="cmbSearchCrt" onchange="showSearchCrtVal()">
				<option value="-1" selected="selected"> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="mcr.bank_code"><fmt:message key="SEARCH.BANKNAME" bundle="${pensionLabels}"></fmt:message></option>
				<option value="mcr.branch_code"><fmt:message key="SEARCH.BRANCHCODE" bundle="${pensionLabels}"></fmt:message></option>
				<option value="mcr.upto_date"><fmt:message key="SEARCH.UPTODATE" bundle="${pensionLabels}"></fmt:message></option>
				<option value="mcr.for_month"><fmt:message key="SEARCH.FORMONTHYEAR" bundle="${pensionLabels}"></fmt:message></option>
				<c:choose>
					<c:when test="${showWLFor == 'Ato'}">
						<option value="mcr.created_post_id"><fmt:message key="SEARCH.AUDIORNAME" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:otherwise>
						<option value="mcr.status"><fmt:message key="SEARCH.CHNGSTMNTSTATUS" bundle="${pensionLabels}"></fmt:message></option>
					</c:otherwise>
				</c:choose>
			</select>
		</td>
		<!-- bank name combo -->
		<td id="txtSearch1"  class="searchClass">
			<select name="cmbBank" id="cmbBank" class="cmbSearch">
				<c:forEach var="Bank" items="${resValue.BankList}">
					<option value='${Bank.id}'>${Bank.desc}</option>
				</c:forEach>
			</select>
			<select name="cmbBranch" id="cmbBranch" class="cmbSearch">
				<c:choose>
					<c:when test="${resValue.BranchList != null}">
						<c:forEach var="branchDtls" items="${resValue.BranchList}">
							<c:choose>
							<c:when test="${branchDtls.id == lSearchBranchCode}">
								<option value='${branchDtls.id}' selected="selected">${branchDtls.desc}</option>
							</c:when>
							<c:otherwise>
								<option value='${branchDtls.id}'>${branchDtls.desc}</option>
							</c:otherwise>
							</c:choose>						
						</c:forEach>
					</c:when>	
					<c:otherwise>
							<option value="-1">--Select--</option>
					</c:otherwise>
				</c:choose>
			</select>
		</td>
		<td id="txtSearch2" class="searchClass" style="display:none">
			<input id="txtSearch" type="text" onfocus="changeOnFocus(this)"  name="txtSearch"  size="15" value= "${lStrSearchVal}" />
			<div id="dtpicker" style="display:none"><img src="images/CalendarImages/ico-calendar.gif"  width="20" onClick="window_open('txtSearch',375,570,'','',0)" ></div>
		</td>
		<!-- case type combo -->
		<td id="txtSearch3" style="display:none" class="searchClass" >
			<select name="cmbSearch" class="cmbSearch" id="cmbSearch">
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='Pending Approval'> <fmt:message key="SEARCHSTATUS.PENDINGAPPROVAL" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='Approved'> <fmt:message key="SEARCHSTATUS.CHNGSTMNTAPPROVED" bundle="${pensionLabels}"></fmt:message> </option>
				<option value='Rejected'> <fmt:message key="SEARCHSTATUS.REJECTED" bundle="${pensionLabels}"></fmt:message> </option>
			</select>
		</td>	
		<td id="txtSearch4" style="display:none" class="searchClass">
			<select name="cmbSearch" class="cmbSearch" id="cmbYear">
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<c:forEach var="YearDtl" items="${resValue.lLstYear}">
					<option value='${YearDtl.id}'>${YearDtl.desc}</option>
				</c:forEach>
			</select>
			<select name="cmbSearch" class="cmbSearch" id="cmbMonth">
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<c:forEach var="MonthDtl" items="${resValue.lLstMonth}">
					<option value='${MonthDtl.id}'>${MonthDtl.desc}</option>
				</c:forEach>
			</select>
		</td>
		<td id="txtSearch5" style="display:none" class="searchClass">
			<select name="cmbSearch" id="cmbSearch" class="cmbSearch">
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<c:forEach var="Auditor" items="${resValue.ListAuditors}">
					<option value='${Auditor[1]}'>${Auditor[0]}</option>
				</c:forEach>
			</select>
		</td>
		<td>
			<a href="#" onclick="searchCases();"><img src="images/search.gif" align="right"/></a>
		</td>		
	</tr>
</table>
<input type="hidden" id="hdnSearchCrt" name="hdnSearchCrt"/>
<input type="hidden" id="hdnSearchVal" name="hdnSearchVal"/>
<input type="hidden" id="hdnBankCode"  name="hdnBankCode"/>
<input type="hidden" id="hdnBranchCode"  name="hdnBranchCode"/>
<input type="hidden" id="hdnYearCode"  name="hdnYearCode"/>
<input type="hidden" id="hdnMonthId"  name="hdnMonthId"/>
<input type="hidden" id="showWLFor" name="showWLFor" value="${showWLFor}" />
</div>	
<c:choose>
	<c:when test="${resValue.showWLFor == 'Ato' }">
	<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchListFromBankCode"
		parameters="bankCode={cmbBank}"
		source="cmbBank"
		target="cmbBranch">
	</ajax:select>
	</c:when>
	<c:otherwise>
		<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getAuditorBranchCode"
		parameters="AuditorBankCode={cmbBank}"
		source="cmbBank"
		target="cmbBranch">
	</ajax:select>
	</c:otherwise>
</c:choose>
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
var lObjSelect = document.getElementsByClassName("cmbSearch");
showSearchCrtVal();
if(!isCrt)
{
	document.getElementById("cmbSearchCrt").options[0].selected = "selected";
}
if(lSearchCrt == "mcr.bank_code")
{
	document.getElementById("cmbBank").value = lSearchBankCode;
	document.getElementById("cmbBranch").value = lSearchBranchCode;
	isCombo = true;
}
else if(lSearchCrt == "mcr.for_month")
{
	document.getElementById("cmbYear").value = lSearchYearCode;
	document.getElementById("cmbMonth").value = lSearchMonthId;
	isCombo = true;
}
else
{
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
}	
if(!isCombo)
{
	document.getElementById("txtSearch").value = lSearchVal;
}
//----Setting default value of search value ends >>>>>>>

</script>
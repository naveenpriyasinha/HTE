<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionCaseLabels" scope="request"/>


<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/SixPayFPArrear.js"></script>


<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="NoOfInstallment" value="${resValue.lLstInstallments}" />
<c:set var="NoOfHeadCode" value="${resValue.lLstHeadCodes}" />
<c:set var="NoOfMonth" value="${resValue.lLstMonths}" />
<c:set var="NoOfYear" value="${resValue.lLstYears}" />
<c:set var="NoOfHeadCodes" value="${resValue.NoOfHeadCodes}" />
<c:set var="CurrMonth" value="${resValue.lStrCurrMonth}" />
<c:set var="CurrYear" value="${resValue.lStrCurrYear}" />
<c:set var="counter" value="0"></c:set>
<c:set var="Index" value="1"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="SixPayFPArrearsList" value="${resValue.lLstSixPayFPArrears}"></c:set>
<c:set var="lLstStateDept" value="${resValue.lLstStateDept}" />
<c:set var="arrearConfigBy" value="${resValue.arrearConfigBy}" />

<hdiits:form name="SixPayArrearConfig" id="SixPayArrearConfig" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" name="noOfHeadCodes"  id="noOfHeadCodes" value="${NoOfHeadCodes}" ></input>
<input type="hidden" name="hidCurrMonth"  id="hidCurrMonth" value="${CurrMonth}" ></input>
<input type="hidden" name="hidCurrYear"  id="hidCurrYear" value="${CurrYear}" ></input>
<input type="hidden" name="hidSixPayFPArrearsList"  id="hidSixPayFPArrearsList" value="${SixPayFPArrearsList}" ></input>

<div style="width:100%;">
<fieldset style="width:100%"   class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="SIXPAYARR.ARREARDTLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<div style="width: 100%">
	<table width="40%" align="left">
		<tr>
			<td>
				<b><fmt:message key="SIXPAYARR.INSTALLNO" bundle="${pensionLabels}"></fmt:message></b>
			</td>
			<td>
				<select name="cmbInstallments" id="cmbInstallments" value="${resValue.installmentNo}">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="NoOfInstallment" items="${NoOfInstallment}">
					<c:choose>
						<c:when test="${resValue.installmentNo == NoOfInstallment.lookupDesc}">
							<option value="${NoOfInstallment.lookupDesc}" selected="selected">
								<c:out value="${NoOfInstallment.lookupDesc}"></c:out>									
							</option>
						</c:when>
						<c:otherwise>
						<option value="${NoOfInstallment.lookupDesc}">
							<c:out value="${NoOfInstallment.lookupDesc}"></c:out>									
						</option>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</select><label class="mandatoryindicator">*</label>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td>
				<b><fmt:message key="SIXPAYARR.ARREARBY" bundle="${pensionLabels}"></fmt:message></b>
			</td>
			<td>
				<input type="radio" value="S" id="stateDeptWise" name="sixPayArrearBy" onclick="showHideArrearOpts(this)">State/Dept Wise</input>
				<input type="radio" value="P" id="ppoWise" name="sixPayArrearBy" onclick="showHideArrearOpts(this)">PPO Wise</input>
			</td>
		</tr>
		<tr id="arrearByState">
			<td>
				<b><fmt:message key="PPMT.STATEDEPT" bundle="${pensionCaseLabels}"></fmt:message></b>
			</td>
			<td>
			<select name="cmbStateCode" id="cmbStateCode" multiple="multiple" style="width:100%;height: 100px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
				<c:forEach var="lLstStateDept" items="${lLstStateDept}">
	        		<option value="${lLstStateDept.id}">
						<c:out value="${lLstStateDept.desc}"></c:out>									
					</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr id="arrearByPPO">
			<td>
				<b><fmt:message key="PPMT.PPONUMBER" bundle="${pensionCaseLabels}"></fmt:message></b>
			</td>
			<td>
				<input type="text" id="txtPPONo" name ="txtPPONo" value="${resValue.ppoNo}" /> 
				 <hdiits:button type="button" captionid="CMN.SEARCH" bundle="${pensionLabels}" id="btnSearch" name="btnSearch" onclick="getSixPayFPArrears();"/>
			</td>
		</tr>
	</table>
</div>
<br/>
<div style="width:100%;">
	<c:choose>
	<c:when test="${SixPayFPArrearsList != null}">
		<display:table list="${SixPayFPArrearsList}" id="vo" cellpadding="5" style="width:100%" pagesize="100" export="false" partialList="true"  offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending"  >				
					
			<display:setProperty name="paging.banner.placement" value="bottom" />
					
			<!--<display:column title="<input name='chkDisplaySelect' type='checkbox' onclick='checkUncheckAllDisplay(this);'/>" headerClass="datatableheader" style="width:2%"  class="oddcentre" >
				<input type="checkbox" align="middle" name="checkPPO" id="checkPPO${Index}"  value="${vo[0]}" />
			</display:column>-->	
					
			<display:column titleKey="PPMT.STATEDEPT" headerClass="datatableheader" class="oddcentre" sortable="true" style="width:10%;text-align:center">
				<label id="lblHeadCode${Index}">${vo[1]}</label>
				<input type="hidden" name="arrearId" id="arrearId" value="${vo[0]}"/>
			</display:column>
					
			<display:column titleKey="SIXPAYARR.LEDGERNO" headerClass="datatableheader" class="oddcentre" sortable="true" style="width:10%;text-align:center" value="${vo[2]}">
				<label id="lblLedgerNo${Index}">${vo[2]}</label>
			</display:column>	
					
			<display:column titleKey="SIXPAYARR.PAGENO" headerClass="datatableheader" class="oddcentre" sortable="true" style="width:10%;text-align:center" value="${vo[3]}">
				<label id="lblPageNo${Index}">${vo[3]}</label>
			</display:column>	
						
			<display:column titleKey="SIXPAYARR.PPONO" headerClass="datatableheader" style="width:10%;text-align:left" sortable="true"  class="oddcentre" >
				<label id="lblPPONo${Index}">${vo[4]}</label>
			</display:column>
			
			<display:column titleKey="SIXPAYARR.PPONAME" headerClass="datatableheader" style="width:10%;text-align:left" sortable="true"  class="oddcentre" >
				<label id="lblPPOName${Index}">${vo[5]}</label>
			</display:column>
				
			<display:column titleKey="MNTH.BANK" headerClass="datatableheader"  style="width:20%;text-align:left" sortable="true" class="oddcentre">
				<label id="lblBank${Index}">${vo[6]}</label>
			</display:column>
			
			<display:column titleKey="MNTH.BRANCH" headerClass="datatableheader" style="width:10%;text-align:left" sortable="true" class="oddcentre">
				<label id="lblBranch${Index}">${vo[7]}</label>
			</display:column>
			
			<c:set var="Index" value="${Index+1}"> </c:set>
		
		</display:table>
		<input type="hidden" id="totalCount" name="totalCount" value="${Index}" />
	</c:when>
	<c:otherwise>
		<div id="erroMsg"><c:out value="${resValue.noValidPpoMsg}"></c:out></div>
	</c:otherwise>
	</c:choose>
	
</div>
<br/>
<div align="center" style="width: 100%">
	<b><fmt:message key="ADM.PAYIN" bundle="${pensionLabels}"></fmt:message></b>&nbsp;&nbsp;&nbsp;
	<select name="cmbPayinMonth" id="cmbPayinMonth" >
	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		<c:forEach var="NoOfMonth" items="${NoOfMonth}">
			<c:choose>
			<c:when test="${NoOfMonth.id == CurrMonth}">
				<option value="${NoOfMonth.id}" selected="selected">
					<c:out value="${NoOfMonth.desc}"></c:out>
				</option>
			</c:when>
			<c:otherwise>
				<option value="${NoOfMonth.id}">
					<c:out value="${NoOfMonth.desc}"></c:out>									
				</option>
			</c:otherwise>
			</c:choose>
        </c:forEach>
	</select>
	<select name="cmbPayinYear" id="cmbPayinYear" >
	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		<c:forEach var="NoOfYear" items="${NoOfYear}">
		<c:choose>
			<c:when test="${NoOfYear.desc == CurrYear}">
				<option value="${NoOfYear.desc}" selected="selected">
					<c:out value="${NoOfYear.desc}"></c:out>
				</option>
			</c:when>
			<c:otherwise>
				<option value="${NoOfYear.desc}">
					<c:out value="${NoOfYear.desc}"></c:out>									
				</option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
	</select>
</div>
</fieldset>
</div>
<table align="center">
<tr>
		
		<td  align="center">
			<hdiits:button type="button" captionid="SIXPAYARR.SAVE" bundle="${pensionLabels}" id="btnSave" name="btnSave" onclick="saveSixPayFPArrearConfig();"/>
		</td>
		
		<td  align="center">		
			<hdiits:button type="button" captionid="SIXPAYARR.CLOSE" bundle="${pensionLabels}" id="btnClose" name="btnClose" onclick="winCls();" />
		</td>
		
	</tr>
</table>
</hdiits:form>
<script type="text/javascript">
var arrearConfigBy = "${arrearConfigBy}";
if(arrearConfigBy == "P")
{
	document.getElementById("ppoWise").checked = "checked";
	document.getElementById("arrearByState").style.display = "none";
	document.getElementById("arrearByPPO").style.display = "block";
}
else
{
	document.getElementById("stateDeptWise").checked = "checked";
	document.getElementById("arrearByState").style.display = "block";
	document.getElementById("arrearByPPO").style.display = "none";
}
</script>	
	
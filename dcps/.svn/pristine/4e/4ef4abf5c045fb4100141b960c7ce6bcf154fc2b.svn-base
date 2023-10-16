<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
	
<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/TransferPPO.js"></script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="RejectedTransferCaseList" value="${resValue.lLstRejectedTransferCase}"></c:set>
<c:set var="BankNames" value="${resValue.lLstBankNames}"></c:set>
<c:set var="BankBranchNames" value="${resValue.lLstBankBranch}"></c:set>
<c:set var="AuditorNames" value="${resValue.lLstAuditorNames}"></c:set>
<c:set var="Index" value="1"></c:set>
<c:set var="counter" value="1"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="TypeOfSearch" value="${resValue.lLstTypeOfSearch}"></c:set>
<c:set var="TreasuryList" value="${resValue.lLstTreasury}"></c:set>
<c:set var="TransferCase" value="${resValue.lStrTransferCase}"></c:set>
<c:set var="ListOfBrnchCode" value="${resValue.lLstBrnchCodes}"></c:set>



<hdiits:form name="frmRejectedCase"  method="post" validate="">
<input type="hidden" name="hidTransferCase" id="hidTransferCase" value="${TransferCase}"></input>
<input type="hidden" name="hidListOfBrnchCode" id="hidListOfBrnchCode" value="${ListOfBrnchCode}"></input>
<input type="hidden" name="hidString" id="hidString" value="Reject"></input>
<fieldset class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.REJTRANSFERCASES" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
		<div style="float:right;position:relative;right:50px;">
	<table >
	<tr>
		<td  ><fmt:message key="PPMT.SEARCH" bundle="${pensionLabels}"></fmt:message></td>
		<td>
		<select name="cmbSearchBy" id="cmbSearchBy"  onchange="showSearchByVal();">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="TypeOfSearch" items="${TypeOfSearch}" >
					<c:choose>
					<c:when test="${TypeOfSearch.lookupDesc == 'PPO No'}">
						<option value="${TypeOfSearch.lookupId}" selected="selected"><c:out value="${TypeOfSearch.lookupDesc}"></c:out> </option>
					</c:when>
					<c:otherwise>
						<option value="${TypeOfSearch.lookupId}"><c:out value="${TypeOfSearch.lookupDesc}"></c:out> </option>
					</c:otherwise>
					</c:choose>
						
					</c:forEach>
			</select>
		
		<div id="txtSearchVal" style="display:inline">
			<input type="text" id="txtSearchValue" size="20" name="txtSearchValue" value=""/>
		
		</div>
		<div id="TreasuryName" style="display:none" >
		
		  
			 <select name="cmbNewTreasury" id="cmbNewTreasury"  onfocus="onFocus(this)" onblur="onBlur(this);">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 <c:forEach var="TreasuryList" items="${TreasuryList}">
						<option value="${TreasuryList.id}"><c:out
							value="${TreasuryList.desc}"></c:out></option>
				</c:forEach>
			</select>
			</div>
		
		<script>showSearchByVal()</script>	
		
		</td>
		<td >
		<a href="#" onclick="searchTransferCases();"><img src="images/search.gif" align="right"/></a>
		</td>	
		
	</tr>
	
</table>
	
	</div>
	
	<div>&nbsp;</div><div>&nbsp;</div>
<div style="width:100%;overflow:auto;height:100%;" >
				
<display:table list="${RejectedTransferCaseList}" id="vo" cellpadding="5" style="width:100%" requestURI="" pagesize="100" export="false" partialList="true"  offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending"  >

			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>"
			headerClass="datatableheader" style="width:2%"  class="oddcentre" >
			<input type="checkbox" align="middle" name="SelectAll"
				id="checkbox${vo_rowNum}"  value="${vo[0]}" />
	</display:column>	
			
	<display:column titleKey="PPMT.PPONO" headerClass="datatableheader" class="oddcentre" 
			sortable="true" style="width:10%;text-align:left" >
			<label id="lblOldPpoNo${vo_rowNum}">${vo[1]}</label>			
	</display:column>
					
	<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader" class="oddcentre"
			sortable="true" style="width:10%;text-align:left" value="${vo[2]}">
	</display:column>	
			
	<display:column titleKey="PPMT.OLDTREASURY" headerClass="datatableheader" class="oddcentre"
			sortable="true" style="width:10%;text-align:left" value="${vo[3]}">
	</display:column>	
				
	<display:column titleKey="PPMT.BRANCHCODE" headerClass="datatableheader" style="width:10%;text-align:center" sortable="true"  class="oddcentre" >
		<input type="text" id="txtBranchCode${vo_rowNum}" name="txtBranchCode"  onblur="validateBrnchCode(this);getAuditorNameFromBranchCode(this);"/>
	</display:column>
		
		
		
		
	<display:column titleKey="PPMT.BANK" headerClass="datatableheader"  style="width:20%;text-align:center" sortable="true" class="oddcentre">
		<select name="cmbBankName" id="cmbBankName${vo_rowNum}" style="width:90%" onchange="getNomBranchNameFromBankCode(this);"  >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="BankNames" items="${resValue.lLstBankNames}">
						<option value="${BankNames.id}"><c:out
							value="${BankNames.desc}"></c:out></option>
					</c:forEach>
		  </select>
	</display:column>
	
	<display:column titleKey="PPMT.BRANCH" headerClass="datatableheader" style="width:10%;text-align:center" sortable="true" class="oddcentre">
		<select name="cmbTargetBranchName" id="cmbTargetBranchName${vo_rowNum}"  onchange="getBranchCode(this);" >
		 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option> 
		 		<c:forEach var="branchList" items="${BankBranchNames}">
					<option value="${branchList.id}">
							<c:out value="${branchList.desc}"></c:out>
					</option>	
				</c:forEach>		
		</select>
	</display:column>
	
	<display:column titleKey="PPMT.ACCOUNTNO" headerClass="datatableheader" style="width:10%;text-align:center" sortable="true" class="oddcentre">
		<input type="text" id="txtAccNo${vo_rowNum}" name="txtAccNo" value="" />
	</display:column>
	
	
	<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"  style="width:10%;text-align:center"  class="oddcentre"> 
		<select name="cmbAuditorName" id="cmbAuditorName${vo_rowNum}"   onfocus="onFocus(this)" onblur="onBlur(this);" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="AuditorNames" items="${resValue.lLstAuditorNames}">
						<option value="${AuditorNames.id}"><c:out
							value="${AuditorNames.desc}"></c:out></option>
					</c:forEach>
		  </select>
	</display:column>
	
	<display:column titleKey="PPMT.REMARKS" headerClass="datatableheader" style="width:10%;text-align:center"  class="oddcentre" >
		<a href=# onclick="viewRemarks('${vo[0]}');">View</a>
		
	</display:column>
	<c:set var="counter" value="${counter+1}"></c:set>
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}" />
</div>
</fieldset>
<table align="center">
<tr>
		
		<td  align="center">
			<hdiits:button type="button" captionid="PPMT.MOVETORECORDROOM" style="width:100%" bundle="${pensionLabels}" id="btnSubmit" name="btnSubmit" onclick="changeInPPOCase(3);"/>
		</td>
		
		<td  align="center">
			<hdiits:button type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" id="btnClear" name="btnClear" onclick="winCls();" />
		</td>
		
	</tr>
</table>

</hdiits:form>
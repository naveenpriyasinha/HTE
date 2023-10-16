<%try{ %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>


<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script  type="text/javascript"  src="script/pensionpay/Common.js"></script>
<script  type="text/javascript"  src="script/pensionpay/SavedPensionBills.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/common/common.js"></script>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionCaseLabels" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="currRole" value="${resValue.currRole}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="billStatus" value="${resValue.currBillStatus}"></c:set>
<c:set var="billFlag" value="${resValue.billFlag}"></c:set> 
<c:set var="searchStr" value="${resValue.searchStr}"></c:set>
<c:set var="searchValue" value="${resValue.searchValue}"></c:set>
<c:set var="chequeList" value="${resValue.lLstCheque}"></c:set>

<input type="hidden" value="${currRole}" name="currRole" id="currRole"/>
<input type="hidden" value="${billFlag}" name="billFlag" id="billFlag"/>
<input type="hidden" value="${billStatus}" name="billStatus" id="billStatus"/>
<input type="hidden" value="" name="txtEcsCode" id="txtEcsCode"/>
<input type="hidden" value="${resValue.searchStr}" name="hdSearchStr"/>
<input type="hidden" value="${resValue.searchValue}" name="hdSearchValue"/>
<script>


</script>
<hdiits:form name="frmDraftBills"  method="post" validate="">
<c:if test="${currRole!='365463'}">	
<table width="100%">
	<tr>
		<td width="53%">
		</td>
		<td width="8%" align="left">
			<b> <fmt:message key="PPMT.SEARCHBILL" bundle="${pensionLabels}"></fmt:message> </b>
		</td>
		<td width="*" >
			<select name="cmbSearch" id="cmbSearch" onchange="getSerchField();blankSearchField();">
			
					<option value="-1"><fmt:message key="TNFR.SELECT" bundle="${pensionLabels}"></fmt:message></option>
					<option value="reg.ppoNo" selected="selected"> <fmt:message key="PPO.NO" bundle="${pensionLabels}"></fmt:message> </option>
					<option value="rlt.partyName"> <fmt:message key="CMN.PARTYNAME" bundle="${pensionLabels}"></fmt:message> </option>
					<option value="reg.billCntrlNo"> <fmt:message key="CMN.BILLCNTR" bundle="${pensionLabels}"></fmt:message> </option>
					
						<option value="tbr.subject_id"> <fmt:message key="CMN.BILLTYPE" bundle="${pensionLabels}"></fmt:message> </option>
						<option value="reg.billNetAmount"> <fmt:message key="PEN.NETAMOUNT" bundle="${pensionLabels}"></fmt:message> </option>
						<option value="tbr.bill_Date"> <fmt:message key="CMN.BILLDATE" bundle="${pensionLabels}"></fmt:message> </option>
						<option value="tbr.tc_bill"> <fmt:message key="CMN.BILLCTGRY" bundle="${pensionLabels}"></fmt:message> </option>
						<option value="our.post_id"> <fmt:message key="CMN.AUDNAME" bundle="${pensionLabels}"></fmt:message> </option>
					
				</select>
		</td>
		<td>
			<input id="txtSearch" type="text" onblur="changeOnBlur(this),setDate()" onfocus="changeOnFocus(this)" onkeydown="searchByEnter(event)" name="txtSearch" size="15" />
			<div id="dtpicker" style="display:none"> <img src="images/CalendarImages/ico-calendar.gif"  width="20" onClick="window_open('txtSearch',375,570)" ></div>
			<select name="CmbSerchAuditots" style="display: none" onkeydown="searchByEnter(event)">
				<option value="-1"><fmt:message key="TNFR.SELECT" bundle="${pensionLabels}"></fmt:message></option>
				<c:forEach var="vo" items="${resValue.ListAuditors}">
					<option value="${vo[1]}"> ${vo[0]} </option>
				</c:forEach>
			</select>
			<select name="CmbBillType" style="display: none" onkeydown="searchByEnter(event)">
				    <option value="-1" ><fmt:message key="TNFR.SELECT" bundle="${pensionLabels}"></fmt:message></option>
					<option value="11"> <fmt:message key="CMN.CVPBILLTYPE" bundle="${pensionLabels}"></fmt:message> </option>
					<option value="10"> <fmt:message key="CMN.DCRGBILLTYPE" bundle="${pensionLabels}"></fmt:message> </option>
					<option value="9"> <fmt:message key="CMN.PNSNBILLTYPE" bundle="${pensionLabels}"></fmt:message> </option>
					<option value="45"> <fmt:message key="CMN.SUPPBILLTYPE" bundle="${pensionLabels}"></fmt:message> </option>
					<option value="44"> <fmt:message key="CMN.MONTHLYBILL" bundle="${pensionLabels}"></fmt:message> </option>
			</select>
			<select name="CmbBillCtgry" style="display: none;" onkeydown="searchByEnter(event)">
				<option value="-1" > <fmt:message key="TNFR.SELECT" bundle="${pensionLabels}"></fmt:message></option>
				<option value="First Pay"> <fmt:message key="CMN.FIRSTPAY" bundle="${pensionLabels}"></fmt:message>   </option>
				<option value="Monthly"> <fmt:message key="CMN.MONTHLY" bundle="${pensionLabels}"></fmt:message>  </option>
			</select>
		</td>
		
				<c:if test="${searchStr ne null}">
					<script>
					
						document.getElementById("cmbSearch").value = document.getElementById("hdSearchStr").value;
						
						getSerchField();
						
					</script>
					</c:if>
				
		<td width="2%">
			<a href="#" onclick="javascript:searching();"><img src="images/search.gif" align="right"/></a>
		</td>
	</tr>
</table>
</c:if>
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
	<c:if test="${currRole == '365450' and billFlag== 'D'}">
			<b><fmt:message key="PPMT.DRAFTBILLS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365451'}">
			<b><fmt:message key="PPMT.VIEWBILLS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>		
	<c:if test="${currRole == '365450' and billFlag == 'A'}">
			<b><fmt:message key="PPMT.APPREJBILLS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365461'}">
			<b><fmt:message key="PPMT.VIEWAPPBILLS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365462' and billFlag=='E'}">
			<b><fmt:message key="PPMT.GENECS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365462' and billFlag=='C'}">
			<b><fmt:message key="PPMT.ASSIGNCHEQUE" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365462' and billFlag=='S'}">
			<b><fmt:message key="PPMT.CASHPAYMENT" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365463' and billFlag=='C'}">
			<b><fmt:message key="PPMT.ASSIGNEDCHEQUE" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365463' and billFlag=='S'}">
			<b><fmt:message key="PPMT.CASHPAYMENTBILLS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365450' and billFlag=='AR'}">
			<b><fmt:message key="PPMT.ARCHEIVEBILLS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${currRole == '365450' and billFlag=='DI'}">
			<b><fmt:message key="PPMT.DISCARDEDBILLS" bundle="${pensionCaseLabels}"></fmt:message></b>
	</c:if>
	</legend>
	
			
<div style="width:100%;overflow:auto;height:100%;" >

<display:table list="${resValue.savedPensionBillsList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="6" defaultorder="descending" cellpadding="5" >	

	
	<c:if test="${(currRole!='365463' and billFlag !='DI' and billFlag != 'AR') and !(currRole=='365451')}">				
	<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%" >
			<input type="checkbox" align="middle" name="chkbxDraftBills"
				id="chkbxDraftBills"
				 value="${vo.billNo}_${vo_rowNum}" />
		
	</display:column>	
	</c:if>
	<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:left" >
			<c:choose>
				<c:when test="${vo.currBillStatus == 70}">
					<b> <font color="red"> <c:out value="${vo.ppoNo}"></c:out>
					</font> </b>
				</c:when>
				<c:otherwise>
					<c:out value="${vo.ppoNo}"></c:out>
				</c:otherwise>
			</c:choose>
	</display:column>
	<display:column titleKey="PPMT.BILLCONTROLNO" headerClass="datatableheader"
			style="width:10%;text-align:left" sortable="true">
		<a href="#" onclick="javascript:showPensionBills('${vo.billNo}','${vo.subjectId}','${vo.ppoNo}');">${vo.billCntrlNo}</a> 
	</display:column>	
		
	<display:column titleKey="PPMT.BENNAME" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:left" >
			<c:choose>
				<c:when test="${vo.currBillStatus == 70}">
				<c:forEach var="PartyName" items="${vo.partyNames}">
						
						<b> <font color="red"><c:out value="${PartyName}" /><br></font> </b>
						 
					    </c:forEach>
					
					
				</c:when>
				<c:otherwise>
							<c:forEach var="PartyName" items="${vo.partyNames}" varStatus="par">
						
							<c:out value="${PartyName}" /><br>
						<input type="hidden" value="${PartyName}" name ="hdPartyName_${vo_rowNum}" id="hdPartyName_${vo_rowNum}_${par.index}"/>
					    </c:forEach>
				
				</c:otherwise>
			
			</c:choose>
			 
	</display:column>	
	<c:if test="${currRole!='365463'}">			
	<display:column titleKey="PPMT.BILLTYPE" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:left" >
			<c:choose>
				<c:when test="${vo.currBillStatus == 70}">
					<b> <font color="red"> 
					<c:choose>
							<c:when test="${vo.subjectId=='9'}">
								<c:out value="Pension"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='10'}">
								<c:out value="DCRG"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='11'}">
								<c:out value="CVP"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='44'}">
								<c:out value="Monthly"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='45'}">
								<c:out value="Supplementary"></c:out>
							</c:when>
					</c:choose>
					</font> </b>
				</c:when>
				<c:otherwise>
					<c:choose>
							<c:when test="${vo.subjectId=='9'}">
								<c:out value="Pension"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='10'}">
								<c:out value="DCRG"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='11'}">
								<c:out value="CVP"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='44'}">
								<c:out value="Monthly"></c:out>
							</c:when>
							<c:when test="${vo.subjectId=='45'}">
								<c:out value="Supplementary"></c:out>
							</c:when>
					</c:choose>
					
				</c:otherwise>
			</c:choose>
			
	</display:column>
	</c:if>
	<c:if test="${currRole!='365463'}">	
	<display:column titleKey="PPMT.BILLDATE" headerClass="datatableheader" 
			style="width:10%;text-align:center" sortable="true">
			<c:choose>
				<c:when test="${vo.currBillStatus == 70}">
					<b> <font color="red"> <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.billDate}"/>
					</font> </b>
				</c:when>
				<c:otherwise>
					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.billDate}"/>
				</c:otherwise>
			</c:choose>
			
	</display:column>			
	</c:if>
	<c:if test="${currRole!='365463'}">		
	<display:column titleKey="PPMT.NETAMOUNT" headerClass="datatableheader"
			style="width:10%;text-align:right" sortable="true" >
			<c:choose>
				<c:when test="${vo.currBillStatus == 70}">
					<b> <font color="red"> <label id="lblBillNetAmount${vo_rowNum}">${vo.billNetAmt}</label>
					</font> </b>
				</c:when>
				<c:otherwise>
					<label id="lblBillNetAmount${vo_rowNum}">${vo.billNetAmt}</label>
				</c:otherwise>
			</c:choose>
			
				</display:column>
	</c:if>
	<c:if test="${currRole!='365463'}">				
	<display:column titleKey="PPMT.BILLCATEGORY" headerClass="datatableheader"
			style="width:10%;text-align:left" sortable="true">
			<c:choose>
				<c:when test="${vo.currBillStatus == 70}">
					<b> <font color="red"> <c:out value="${vo.billCtgry}"></c:out>
					</font> </b>
				</c:when>
				<c:otherwise>
					<c:out value="${vo.billCtgry}"></c:out>
				</c:otherwise>
			</c:choose>
			
	</display:column>
	</c:if>
	<c:if test="${currRole!='365463'}">	
	<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"
			style="width:10%;text-align:left" sortable="true">
			<c:choose>
				<c:when test="${vo.currBillStatus == 70}">
					<b> <font color="red"> <c:out value="${vo.audtrName}"></c:out>
					</font> </b>
				</c:when>
				<c:otherwise>
					<c:out value="${vo.audtrName}"></c:out>
				</c:otherwise>
			</c:choose>
			
	</display:column>
	</c:if>
	<c:if test="${(currRole == '365462' and billFlag=='C') or (currRole=='365463' and billFlag=='C')}">
	
		<display:column titleKey="PPMT.CHEQUENO" headerClass="datatableheader"
			 style="width:10%;text-align:left" >
							<c:choose>
							<c:when test="${currRole == '365463'}">
									<c:forEach var="ChequeNos" items="${vo.chequeNos}">
										<c:out value="${ChequeNos}"/> </br>
									</c:forEach>
							</c:when>
							<c:otherwise>
									<c:forEach var="PartyName" items="${vo.partyNames}" varStatus="pn">
										<input type="text" value="" name="txtChequeNo_${vo_rowNum}" id="txtChequeNo_${vo_rowNum}_${pn.index}" size="7" onKeyPress="numberFormat(this)" /></br>
										<c:set value="${pn.index}" var="size"/>
									</c:forEach>
							</c:otherwise>
							</c:choose>
							
			<input type="hidden" id="hdSize_${vo_rowNum}" value="${size}"/> 	
		</display:column>
		<display:column titleKey="PPMT.CHEQUEAMNT" headerClass="datatableheader"
			 style="width:10%;text-align:right" >
							<c:forEach var="CheqAmt" items="${vo.chequeAmounts}" varStatus="ca">
								<c:out value="${CheqAmt}"/><br>
								<input type="hidden" name="hdChequeAmt_${vo_rowNum}" id="hdChequeAmt_${vo_rowNum}_${ca.index}" value="${CheqAmt}"/>
							</c:forEach>
				
		</display:column>
		<display:column titleKey="PPMT.CHEQUEDATE" headerClass="datatableheader"
			style="width:15%;text-align:center" >
			<c:choose>
			<c:when test="${vo.fromDt != null}">
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.fromDt}"/>
			</c:when>
			
			<c:otherwise>
				<input type="hidden" name="currdate" id="currdate" value='<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.currdate}"/>'/>
				<input type="text" name="txtChqDate_${vo_rowNum}" id="txtChqDate_${vo_rowNum}" size="12" value=""onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(document.getElementById('currdate'),this,'Cheque Date should be greater than current date.','<');">&nbsp;
				<img src='images/CalendarImages/ico-calendar.gif'onClick='window_open("txtChqDate_${vo_rowNum}",375,570)'style="cursor: pointer;" />
			</c:otherwise>
			</c:choose>
		</display:column>
	</c:if>
	<c:if test="${(currRole == '365462' and billFlag=='S')or (currRole=='365463' and  billFlag=='S')}">
		<display:column titleKey="PPMT.AMOUNT" headerClass="datatableheader"
			 style="width:10%;text-align:right" >
							<c:forEach var="CashAmt" items="${vo.chequeAmounts}" varStatus="ca">
								<c:out value="${CashAmt}"/><br>
								<input type="hidden" name="hdCashAmt_${vo_rowNum}" id="hdCashAmt_${vo_rowNum}_${ca.index}" value="${CashAmt}"/>
							</c:forEach>
			<input type="hidden" id="hdSize_${vo_rowNum}" value="${size}"/> 
		</display:column>
		<display:column titleKey="PPMT.PAYMENTDATE" headerClass="datatableheader"
			style="width:15%;text-align:center" >
			<c:choose>
			<c:when test="${vo.fromDt != null}">
				
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.fromDt}"/>
			</c:when>
			
			<c:otherwise>
				<input type="hidden" name="currdate" id="currdate" value='<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.currdate}"/>'/>
				<input type="text" name="txtPayDate_${vo_rowNum}" id="txtPayDate_${vo_rowNum}" size="12" value=""onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(document.getElementById('currdate'),this,'Payment Date should be greater than current date.','<');">&nbsp;
				<img src='images/CalendarImages/ico-calendar.gif'onClick='window_open("txtPayDate_${vo_rowNum}",375,570)'style="cursor: pointer;" />
			</c:otherwise>
			</c:choose>
		</display:column>
	</c:if>
	<c:if test="${currRole == '365450' and billFlag == 'A'}">
		
			<display:column titleKey="PPMT.STATUS" headerClass="datatableheader"
			style="width:10%;text-align:left" sortable="true">
			
			<input type="hidden" value="${vo.currBillStatus}" name="hdCurrBillStatus_${vo_rowNum}" id="hdCurrBillStatus_${vo_rowNum}"/>
			<c:choose>
			<c:when test="${vo.currBillStatus == 20}">
				<fmt:message key="PPMT.APPROVED" bundle="${pensionCaseLabels}"></fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="PPMT.REJECTED" bundle="${pensionCaseLabels}"></fmt:message>
			</c:otherwise>
			</c:choose>
			</display:column>
		
	</c:if>
	
</display:table>
</div>
</fieldset>
	

</hdiits:form>
<div align="center">
<c:if test="${currRole == '365461' or (currRole == '365450' and !(billFlag=='AR' or billFlag=='DI'))}">
	<c:if test="${currRole == '365450' and billFlag == 'A'}">
		<hdiits:button type="button" name="btnForward" style="width:150px;" captionid="PPMT.FORWARDTOAUDITATO"  bundle="${pensionCaseLabels}" onclick="forwardApproveBills('A');"  />
	</c:if>
	<c:if test="${currRole == '365450' and billFlag == 'D'}">
		<hdiits:button type="button" name="btnForward" style="width:150px;" captionid="PPMT.FORWARDTOATO"  bundle="${pensionCaseLabels}" onclick="forwardApproveBills('A');"  />
	</c:if>
	<c:if test="${currRole == '365461' or (billFlag=='')}">
		<!--<hdiits:button type="button" name="btnForward" style="width:200px" captionid="PPMT.FORWARDTOPMNTUSER"  bundle="${pensionCaseLabels}" onclick="forwardApproveBills('A');" />-->
		<hdiits:button type="button" name="btnForwardForBillVoucherMpg" style="width:300px" captionid="PPMT.FORWARDFORBILLVOUCHMPG"  bundle="${pensionCaseLabels}" onclick="forwardApproveBills('A');" />
	</c:if>	
	
</c:if>		
<c:if test="${currRole == '365451'}">
	<hdiits:button type="button" name="btnApprove" captionid="PPMT.APPROVE"  bundle="${pensionCaseLabels}" onclick="forwardApproveBills('A');" classcss="bigbutton" style="display:none" />
</c:if>	
<c:if test="${(currRole == '365451') or (currRole == '365461')}">				
	<hdiits:button type="button" name="btnReject" captionid="PPMT.REJECT"  bundle="${pensionCaseLabels}"  onclick="forwardApproveBills('R');" style="display:none" />		
</c:if>	
<c:if test="${currRole == '365450' and billFlag=='A'}">
	<hdiits:button type="button" name="btnArchBills" captionid="PPMT.ARCHBILL"  bundle="${pensionCaseLabels}"  classcss="bigbutton" onclick="forwardApproveBills('AR')" />			
	<hdiits:button type="button" name="btnViewArchBills" captionid="PPMT.VIEWARCHBILL"  bundle="${pensionCaseLabels}" classcss="bigbutton" onclick="viewArcheiveBill();"/>		
</c:if>
<c:if test="${currRole == '365462' and billFlag=='E'}">
	<hdiits:button type="button" name="genECS" captionid="PPMT.GENECS"  bundle="${pensionCaseLabels}" onclick="generateECS()" classcss="bigbutton"  />	
	<hdiits:button type="button" name="return" captionid="PPMT.RETURN"  bundle="${pensionCaseLabels}" onclick="forwardApproveBills('R');" />		
</c:if>		
<c:if test="${currRole == '365462' and billFlag=='C'}">
	<hdiits:button type="button" name="saveCheque" captionid="PPMT.SAVE"  bundle="${pensionCaseLabels}" onclick="assignChequeNo();"  />	
</c:if>	
<c:if test="${currRole == '365462' and billFlag=='S'}">
	<hdiits:button type="button" name="saveCash" captionid="PPMT.SAVE"  bundle="${pensionCaseLabels}" onclick="saveCashPayment();"  />	
</c:if>
<c:if test="${currRole == '365450' and billFlag=='D'}">
	<hdiits:button type="button" name="btnDiscard" captionid="PPMT.DISCARD"  bundle="${pensionCaseLabels}" onclick="forwardApproveBills('R')" classcss="bigbutton"  />			
</c:if>									
	<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionCaseLabels}"  onclick="winCls();"/>
</div>
<script>


</script>
<%}catch(Exception ex){
	ex.printStackTrace();
}
	%>
	


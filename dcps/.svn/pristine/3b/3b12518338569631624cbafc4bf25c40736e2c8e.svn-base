
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.tcs.sgv.common.constant.Constants"%>


<script type="text/javascript"  src="script/common/common.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/pensionpay/LifeCertificate.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts" var="pensionAlerts" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.LifeCertificateList}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>

<script type="text/javascript">
RECEIVEDDATE='<fmt:message key="PPMT.RECEIVEDDATE" bundle="${pensionAlerts}"></fmt:message>';

</script>
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.SEARCH" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="100%">
	<tr>
	<td width="20%"></td>
		<td  width="5%"><fmt:message key="PPMT.SEARCH" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="60%">
			<select name="cmbSearchCrt" id="cmbSearchCrt" onchange="search();">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:choose>
					<c:when test="${resValue.SearchCrt == 'prh.ppo_no'}">
						<option value="prh.ppo_no" selected="selected"><fmt:message key="SEARCH.PPONO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.ledger_page_no"><fmt:message key="SEARCH.LEDGERPAGENO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.branch_code"><fmt:message key="SEARCH.BANKBRANCH" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.account_no"><fmt:message key="SEARCH.ACNO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.life_cert_flag"><fmt:message key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:when test="${resValue.SearchCrt == 'prh.ledger_page_no'}">
						<option value="prh.ppo_no"><fmt:message key="SEARCH.PPONO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.ledger_page_no" selected="selected"><fmt:message key="SEARCH.LEDGERPAGENO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.branch_code"><fmt:message key="SEARCH.BANKBRANCH" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.account_no"><fmt:message key="SEARCH.ACNO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.life_cert_flag"><fmt:message key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:when test="${resValue.SearchCrt == 'mpd.branch_code'}">
						<option value="prh.ppo_no"><fmt:message key="SEARCH.PPONO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.ledger_page_no"><fmt:message key="SEARCH.LEDGERPAGENO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.branch_code" selected="selected"><fmt:message key="SEARCH.BANKBRANCH" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.account_no"><fmt:message key="SEARCH.ACNO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.life_cert_flag"><fmt:message key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:when test="${resValue.SearchCrt == 'mpd.account_no'}">
						<option value="prh.ppo_no"><fmt:message key="SEARCH.PPONO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.ledger_page_no"><fmt:message key="SEARCH.LEDGERPAGENO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.branch_code"><fmt:message key="SEARCH.BANKBRANCH" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.account_no" selected="selected"><fmt:message key="SEARCH.ACNO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.life_cert_flag"><fmt:message key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:when test="${resValue.SearchCrt == 'prh.life_cert_flag'}">
						<option value="prh.ppo_no"><fmt:message key="SEARCH.PPONO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.ledger_page_no"><fmt:message key="SEARCH.LEDGERPAGENO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.branch_code"><fmt:message key="SEARCH.BANKBRANCH" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.account_no"><fmt:message key="SEARCH.ACNO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.life_cert_flag"  selected="selected"><fmt:message key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:otherwise>
						<option value="prh.ppo_no" selected="selected"><fmt:message key="SEARCH.PPONO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.ledger_page_no"><fmt:message key="SEARCH.LEDGERPAGENO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.branch_code"><fmt:message key="SEARCH.BANKBRANCH" bundle="${pensionLabels}"></fmt:message></option>
						<option value="mpd.account_no"><fmt:message key="SEARCH.ACNO" bundle="${pensionLabels}"></fmt:message></option>
						<option value="prh.life_cert_flag"><fmt:message key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message></option>
					</c:otherwise>
				</c:choose>
				
			</select>&nbsp;&nbsp;
			
			<fmt:message key="PPMT.SEARCHTYPE" bundle="${pensionLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="cmbSearchType" id="cmbSearchType" disabled="disabled">
				<c:choose>
					<c:when test="${resValue.SearchType == 'Normal'}">
						<option value="<fmt:message key="PPMT.NORMAL" bundle="${pensionLabels}"></fmt:message>" selected="selected"><fmt:message key="PPMT.NORMAL" bundle="${pensionLabels}"></fmt:message></option>
						<option value="<fmt:message key="PPMT.ONWARD" bundle="${pensionLabels}"></fmt:message>"><fmt:message key="PPMT.ONWARD" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:when test="${resValue.SearchType == 'Onward'}">
						<option value="<fmt:message key="PPMT.NORMAL" bundle="${pensionLabels}"></fmt:message>"><fmt:message key="PPMT.NORMAL" bundle="${pensionLabels}"></fmt:message></option>
						<option value="<fmt:message key="PPMT.ONWARD" bundle="${pensionLabels}"></fmt:message>"  selected="selected"><fmt:message key="PPMT.ONWARD" bundle="${pensionLabels}"></fmt:message></option>
					</c:when>
					<c:otherwise>
						<option value="<fmt:message key="PPMT.NORMAL" bundle="${pensionLabels}"></fmt:message>"><fmt:message key="PPMT.NORMAL" bundle="${pensionLabels}"></fmt:message></option>
						<option value="<fmt:message key="PPMT.ONWARD" bundle="${pensionLabels}"></fmt:message>"><fmt:message key="PPMT.ONWARD" bundle="${pensionLabels}"></fmt:message></option>
					</c:otherwise>
				</c:choose>
				
		    </select>
		    <input type="text" id="txtSeachValue" id="txtSearchValue" value="${resValue.SearchVal}" size='15'/>
		    <select name="cmbSearchLifeCertFlag" id="cmbSearchLifeCertFlag" onfocus="onFocus(this)" onblur="onBlur(this)" style="display:none">
	  			 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
	  			 <c:choose>
	  			 <c:when test="${resValue.LifeCertFlag == 'Y'}" >
	  			 	<option value="Y" selected="selected">Yes</option>
  					<option value="N">No</option>
	  			 </c:when>
	  			 <c:when test="${resValue.LifeCertFlag == 'N'}" >
	  			 	<option value="Y">Yes</option>
  					<option value="N" selected="selected">No</option>
	  			 </c:when>
	  			 <c:otherwise>
	  			 	<option value="Y">Yes</option>
  					<option value="N">No</option>
	  			 </c:otherwise>
	  			 </c:choose>
					
  			</select>
		    <select name="cmbBankName" id="cmbBankName"   style="width: 30%;display: none"  >
	      	    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			    <c:forEach var="bank" items="${resValue.lLstBanks}">
			    <c:choose>
							<c:when test="${bank.id == resValue.BankCode}">
								<option selected="true" value='${bank.id}'>
									<c:out value="${bank.desc}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value='${bank.id}'>
									<c:out value="${bank.desc}"></c:out>
								</option>
							</c:otherwise>
				</c:choose>
	
				</c:forEach>
			    
		    </select>
		     <select name="cmbBankBranch" id="cmbBankBranch"   style="width: 30%;display: none" >
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option> 
					<c:forEach var="branchList" items="${resValue.lLstBankBranch}">
					<c:choose>
							<c:when test="${branchList.id == resValue.BranchCode}">
								<option selected="true" value='${branchList.id}'>
									<c:out value="${branchList.desc}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value="${branchList.id}">
								<c:out value="${branchList.desc}"></c:out>
							    </option>	
							</c:otherwise>
				    </c:choose>
								
					</c:forEach>		
					
	    </select>
		    <input type="text" id="txtPageNo" id="txtPageNo" value="${resValue.PageNo}" style="display: none" size='5' maxlength="6"/>
		    
		    <a href="#" onclick="showList();"><img src="images/search.gif" /></a>
		</td>
	</tr>
</table>
</fieldset>
<br/>
<table width="100%">
	<tr align="right">
		<td width="20%" align="right"></td>
		<td  width="10%"  align="right">
		
		</td>
		<td align="right">
		<fmt:message key="PPMT.RECEIVEDDATE" bundle="${pensionLabels}"></fmt:message>
		<input type="text" id="txtReceivedDate" name="txtReceivedDate" value ="${resValue.CurrentDate}"
			  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  tabindex="10"/>
	 	<img src='images/CalendarImages/ico-calendar.gif'
			onClick='window_open("txtReceivedDate",375,570)' style="cursor: pointer;" />	
	
		<fmt:message key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message>
	
			<select name="cmbLifeCertFlag"	id="cmbLifeCertFlag" onfocus="onFocus(this)" onblur="onBlur(this)" >
	  			 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
	  			 <option value="Y">Yes</option>
  				 <option value="N">No</option>
  			</select>
  			<hdiits:button type="button" captionid="PPMT.UPDATESELD" bundle="${pensionLabels}" id="btnUpdateSelected" name="btnUpdateSelected" onclick="updateAllPPO();" classcss="bigbutton"/>
		</td>
		
	</tr>
	
</table>

<c:if test="${resValue.SearchCrt == 'prh.ppo_no'}">
				<script>
					document.getElementById("cmbSearchType").value="Normal";
					document.getElementById("cmbSearchType").disabled=true;
					document.getElementById("txtPageNo").style.display="none";
				</script>
</c:if>
<c:if test="${resValue.SearchCrt  == 'prh.ledger_page_no'}">
				<script>
					document.getElementById("cmbSearchType").disabled=false;
					document.getElementById("txtPageNo").style.display="inline";
				</script>
</c:if>
<c:if test="${resValue.SearchCrt == 'mpd.branch_code'}">
				<script>
					document.getElementById("cmbSearchType").value="Normal";
					document.getElementById("cmbSearchType").disabled=true;
					document.getElementById("txtSeachValue").style.display="none";
					document.getElementById("cmbBankName").style.display="inline";
					document.getElementById("cmbBankBranch").style.display="inline";
				</script>
</c:if>
<c:if test="${resValue.SearchCrt == 'prh.life_cert_flag'}">
				<script>
				document.getElementById("cmbSearchType").value="Normal";
				document.getElementById("cmbSearchType").disabled=true;
				document.getElementById("txtSeachValue").style.display="none";
				document.getElementById("txtSeachValue").value="";
				document.getElementById("txtPageNo").value="";
				document.getElementById("txtPageNo").style.display="none";
				document.getElementById("cmbBankName").style.display="none";
				document.getElementById("cmbBankBranch").style.display="none";
				document.getElementById("cmbSearchLifeCertFlag").style.display="inline";
				</script>
</c:if>					
<hdiits:form name="frmLifeCerificate" id="frmLifeCerificate" method="post" validate="">
<fieldset class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.LIFECERTI" bundle="${pensionLabels}"></fmt:message></b>
</legend>

<div  class="scrollablediv" >
<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	

					
					
	<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%" >
			<input type="checkbox" style="text-align:center" name="chkbxPensionerCode"
				id="chkbxPensionerCode_${vo_rowNum}"
				onclick="" value="${vo.pensionerCode}" />
			<input type="hidden" name="hdnPensionRqstId" id="hdnPensionRqstId${vo_rowNum}" value="${vo.pensionRqstId}" />
	</display:column>	
	
	<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >${vo.ppoNo}

	</display:column>
	
	
	<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="false" style="width:15%;text-align:left" >
			<c:choose>
			<c:when test="${vo.aliveFlag == 'Y'}">
			${vo.name}
			</c:when>
			<c:otherwise>
			${vo.familyName}
			</c:otherwise>
			</c:choose>
			
			
	</display:column>
	
	<display:column titleKey="PPMT.VOLPAGE" headerClass="datatableheader"
			sortable="false" style="width:7%;text-align:left" >
		${vo.ledgerNo}/${vo.pageNo}	
	</display:column>
	
	<display:column titleKey="PPMT.BANKNAME" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >
			${vo.bankName}	
	</display:column>
	
	<display:column titleKey="PPMT.BANKBRNNAME" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >
			${vo.branchName}	
	</display:column>
	
	<display:column titleKey="PPMT.ACCOUNTNO" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left">${vo.accountNo}
			
	</display:column>
	<display:column titleKey="PPMT.RECEIVEDDATE" headerClass="datatableheader"
			sortable="false" style="width:8%;text-align:center">
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.receivedDate}"/>
			
	</display:column>
	<display:column titleKey="PPMT.LIFECERTFLAG" headerClass="datatableheader"
			sortable="false" style="width:3%;text-align:center">
			${vo.lifeCertFlag}
					
	</display:column>
	<display:column titleKey="PPMT.PAYINMONTHYEAR" headerClass="datatableheader"
			sortable="false" style="width:20%;text-align:center">
			<select name="cmbPayInMonth" id="cmbPayInMonth${vo_rowNum}" >
			   <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			   <c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(vo.payMonthYear,4,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(vo.payMonthYear,5,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${MonthList.id}">
								<c:out value="${MonthList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	          
	           </c:forEach>		
		   </select>
					
		   <select name="cmbPayInYear" id="cmbPayInYear${vo_rowNum}" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(vo.payMonthYear,0,4)}">
				              <option selected="selected" value="${YearList.desc}"><c:out value="${YearList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${YearList.desc}">
								<c:out value="${YearList.desc}"></c:out>									
						 </option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
	           
		   </select>
					
	</display:column>
	<display:column titleKey="PPMT.ARREARAMOUNT" headerClass="datatableheader"
			sortable="false" style="width:1%;text-align:center">
			<input type ="text" name = "txtArrearAmt" id="txtArrearAmt${vo_rowNum}" value="${vo.arrearAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" size="10" />
					
	</display:column>
	<display:column titleKey="BTN.ARREAR" headerClass="datatableheader"
			sortable="false" style="width:50%;text-align:center">
		<input name="btnCalcArrear" id="btnCalcArrear_${vo_rowNum}" title="Calculate Arrear" value="Calculate Arrear" type="button" style="width: 100%" class="buttontag" onclick="javascript:calculateArrear('${vo.ppoNo}',this);" />
	</display:column>
</display:table>


</div>
</fieldset>

<table align="center">
<tr>
		
		<td  align="center">
			<hdiits:button type="button" captionid="PPMT.PRINTALL" style="width:100%" bundle="${pensionLabels}" id="btnPrintAll" name="btnPrintAll" onclick="viewLifeCertificate()" classcss="bigbutton"/>
		</td>
		
		<td  align="center">
			<hdiits:button type="button" captionid="PPMT.PRINTSELECTED" bundle="${pensionLabels}" id="btnPrintSelected" name="btnPrintSelected" onclick="printSelectedLifeCertificates()" classcss="bigbutton"/>
		</td>
		<td  align="center">
			<hdiits:button type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" id="btnSave" name="btnSave" onclick="saveArrearPaymentDtls()"/>
		</td>
		<td align="center">
			<hdiits:button id="btnClose" name="btnClose"  type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
		</td>
		
	</tr>
</table>

</hdiits:form>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchListFromBankCode" source="cmbBankName" target="cmbBankBranch" parameters="bankCode={cmbBankName}" ></ajax:select>
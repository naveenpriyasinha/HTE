<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
 
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/loan/loan.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="loanTypeList" value="${resultValue.loanTypeList}"></c:set>
<c:set var="customComplst" value="${resultValue.customComplst}"></c:set>
<c:set var="loanType" value="${resultValue.loanType}"></c:set>
<c:set var="hrEssLoanMstObj" value="${resultValue.hrEssLoanMstObj}"></c:set>
<c:set var="hbaTypeList" value="${resultValue.hbaTypeList}"></c:set>
<c:set var="hbatype_val" value="${resultValue.hbatype_val}"></c:set>
<c:set var="loanMstList" value="${resultValue.loanMstList}"></c:set>

<fmt:setBundle basename="resources.ess.loan.loanCaptions"	var="loanCaptions" scope="request" />
<fmt:setBundle basename="resources.ess.loan.loanAlertMessages"	var="loanAlertLables" scope="request" />
 
 <script>
 	var advAmt='${hrEssLoanMstObj.advAmt}';
 	var messageArray= new Array();
 	var appStatus='${hrEssLoanMstObj.approveFlag}';
 	messageArray[0] = "<fmt:message bundle="${loanAlertLables}" key='loan.selAmtSanc'/>";
	messageArray[1] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidAmtSanc'/>";
	
 </script>

<hdiits:form name="loanReqPage" validate="true" method="POST" encType="multipart/form-data" action="hrms.htm?actionFlag=loanSaveInboxPage">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="loan.loanAndAdvance" bundle="${loanCaptions}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	
	
	<c:if test="${hrEssLoanMstObj.approveFlag=='C'}">
		<hdiits:caption bundle="${loanCaptions}" captionid="loan.loanCancel"/>
	</c:if>
	
	<hdiits:fieldGroup bundle="${loanCaptions}" id="loanDtlFldGrp" titleCaptionId="loan.details" expandable="false">	 
	<table width=100%>
		<tr>
			<td width=25%>
				<hdiits:caption captionid="loan.loanType" bundle="${loanCaptions}" />
			</td>
			<td width=25%>
				<hdiits:select  readonly="true" name="loanType" id="loanType" mandatory="true"  
					onchange="changeLoadType(this)" default="${hrEssLoanMstObj.cmnLookupMst.lookupName}" sort="false">
 		
					<c:forEach var="loanTypeList" items="${loanTypeList}">
						<hdiits:option value="${loanTypeList.lookupName}">
								${loanTypeList.lookupDesc}
							</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td width=25% id="hbaLabId">
				<hdiits:caption captionid="loan.advanceForHBA" bundle="${loanCaptions}" />
			</td>
			
			<td width=25% id="hbaTypeId">
				<hdiits:select	name="hbaType"  readonly="true" id="hbaType" sort="false" mandatory="true" onchange="changeLoadTypeForHBA()" default="${hbatype_val}">
					<hdiits:option value="-1"> 
						<fmt:message bundle="${loanCaptions}" key="loan.select"/>
					</hdiits:option> 		
					<c:forEach var="hbaTypeList" items="${hbaTypeList}">
						<hdiits:option value="${hbaTypeList.lookupName}" >
								${hbaTypeList.lookupDesc}
							</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td width=25% id="staticDummy1"></td>
			<td width=25% id="staticDummy2"></td>
		</tr>
	</table>	
	</hdiits:fieldGroup>
	
	<c:if test="${not empty customComplst}">
	<hdiits:fieldGroup bundle="${loanCaptions}" id="loanAttrDtlFldGrp" titleCaptionId="loan.Advdetails" expandable="false">
	<table width=100%>
		<c:forEach items="${customComplst}" var="custom"> 
			
			<c:if test="${custom.startTr=='T'}">
				<tr>
			</c:if>

			<td width=25% style="display:${custom.display}" id="lab${custom.attrKey}" nowrap="nowrap">
						
			<c:if test="${custom.attrType=='Label'}">
				<hdiits:caption  bundle="${loanCaptions}" captionid="${custom.attrKey}"  id="cap${custom.attrKey}"/> 
			</c:if>
			
			<c:if test="${custom.attrType=='Combo'}">
				<c:if test="${custom.cmnLookupMst.lookupId!=300452}">
					<hdiits:select  readonly="${custom.readonly}" name="${custom.attrName}" id="${custom.attrName}" mandatory="${custom.mandatory}" onchange="${custom.action}" default="${custom.valueStr}">
						<c:forEach var="childLookup" items="${custom.childLookupLst}">
							<hdiits:option value="${childLookup.lookupName}">
									${childLookup.lookupDesc}
								</hdiits:option>
						</c:forEach>
					</hdiits:select>
					</c:if>
	
					<c:if test="${custom.cmnLookupMst.lookupId==300452}">
						<hdiits:select  readonly="${custom.readonly}" name="${custom.attrName}" id="${custom.attrName}" onchange="${custom.action}" default="${custom.valueStr}" sort="false">
						<c:forEach var="childLookup" items="${custom.cmnHolidayMstLst}">
							<fmt:formatDate pattern="dd/MM/yyyy" value="${childLookup.hldyDt}" var="holidayDt"/>
							<option  value="${childLookup.srNo}"  title="${holidayDt}">
									${childLookup.hldyOccsn}
							</option>
						</c:forEach>
					</hdiits:select>
				</c:if>
			
			</c:if>
			
			<c:if test="${custom.attrType=='Number'}">
				<hdiits:number readonly="${custom.readonly}" name="${custom.attrName}" id="${custom.attrName}" default="${custom.valueLong}" style="display:${custom.display}" mandatory="${custom.mandatory}"/>
			</c:if>
			
			<c:if test="${custom.attrType=='DateTime'}">
			<hdiits:text readonly="${custom.readonly}" name="${custom.attrName}"  captionid="loan.festDt" bundle="${loanCaptions}" default="${custom.valueStr}"  mandatory="${custom.mandatory}" />
			</c:if>
			
			<c:if test="${custom.attrType=='Text'}">
				<hdiits:text maxlength="${custom.maxlength}" name="${custom.attrName}" id="${custom.attrName}" style="display:${custom.display}" default="${custom.valueStr}" mandatory="${custom.mandatory}" readonly="${custom.readonly}"/> 
			</c:if>
			
			<c:if test="${custom.attrType=='TextArea'}">
				<hdiits:textarea  rows="3" cols="25" readonly="${custom.readonly}" name="${custom.attrName}" id="${custom.attrName}" default="${custom.valueStr}" style="display:${custom.display}" /> 
			</c:if>

			<c:if test="${custom.attrType=='Radio'}">
				<input type="radio" value="Y" name="${custom.attrName}"  disabled="disabled" readonly="readonly"><fmt:message bundle="${loanCaptions}" key="${custom.attrKey}"/>
				<input type="radio" value="N" name="${custom.attrName}"  disabled="disabled" readonly="readonly"><fmt:message bundle="${loanCaptions}" key="${custom.parentLookupName}"/>
				<script>
					var radioArray=document.getElementsByName("${custom.attrName}");
					if("${custom.valueStr}" =="Y"){
						radioArray[0].checked="true";
					}else{
						radioArray[1].checked="true";
					}	
				</script>
			</c:if>

			
			</td>
		

			<c:if test="${custom.dummyTd!=0}">
				<c:forEach begin="1" end="${custom.dummyTd}" var="dum">
					<td width=25% id="dummy${dum}"></td>
				</c:forEach>
			</c:if>
			
			<c:if test="${custom.endTr=='T'}">
				</tr>
			</c:if>
			
		</c:forEach>
	</table>
	</hdiits:fieldGroup>
	</c:if>	 
		
	<hdiits:fieldGroup bundle="${loanCaptions}"  titleCaptionId="loan.Advdetails" expandable="false">

	<table width=50%>
		<td width=25%>
		<hdiits:caption captionid="loan.amtSanc" bundle="${loanCaptions}"/>
		</td>

		<td width=25%>
		<hdiits:number name="amtSancTb" bundle="${loanCaptions}" id="amtSancTb" onblur="roundNo(this,0)" mandatory="true" captionid="loan.amtSanc" maxlength="20" default="${hrEssLoanMstObj.amtSanc}"/>
		</td>
	</table>
	</hdiits:fieldGroup>
		
	<hdiits:fieldGroup bundle="${loanCaptions}" id="loanAdvDtlFldGrp" titleCaptionId="loan.Advdetails" expandable="false">
		<table width=100%>
			<tr>
				<td width=25%>	
					<hdiits:caption captionid="loan.applyDt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>	
					<fmt:formatDate pattern="dd/MM/yyyy" value="${hrEssLoanMstObj.appDt}" var="appDate"/>
					<hdiits:text readonly="true" name="applyDt" bundle="${loanCaptions}" caption="loan.applyDt" default="${appDate}"/>
				</td>

				<td width=25%>	
				</td>
				
				<td width=25%>	
				</td>
			</tr>
			
			<tr>
				<td width=25%>	
					<hdiits:caption captionid="loan.eligAmt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>	
					<hdiits:number readonly="true" name="eligibleAmtTxtNo" id="eligibleAmtTxtNo"  default="${hrEssLoanMstObj.eligibleAmt}"/>
				</td>

				<td width=25%>	
					<hdiits:caption captionid="loan.advAmt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>
					<hdiits:number readonly="true" name="advAmtTxtNo" id="AdvAmtTxtNo" mandatory="true"  default="${hrEssLoanMstObj.advAmt}"/>	
				</td>
			</tr>
			
			<tr>
				<td width=25%>	
					<hdiits:caption captionid="loan.installAdv" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>	
					<hdiits:number readonly="true" name="installAdvTxtNo" id="installAdvTxtNo" mandatory="true"  default="${hrEssLoanMstObj.installAdv}"/>
				</td>

				<td width=25%>	
					<hdiits:caption captionid="loan.interestRt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>
					<hdiits:number readonly="true" name="interestRtTxtNo" id="interestRtTxtNo" default="${hrEssLoanMstObj.interestRate}"/>	
				</td>
			</tr>
			
			<tr>
				<td width=25% style="display:none">	
					<hdiits:caption captionid="loan.installInterest" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25% style="display:none">	
					<hdiits:number  readonly="true" name="installInterestTxtNo" id="installInterestTxtNo" mandatory="true" default="${hrEssLoanMstObj.installInterest}"/>
				</td>

				<td width=25%>	
					<hdiits:caption captionid="loan.remarks" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>
					<hdiits:textarea  rows="3" cols="25" readonly="true" name="remarksTxtAr" id="remarksTxtAr" bundle="${loanCaptions}" 					  captionid="loan.remarks"  default="${hrEssLoanMstObj.remarks}">
					</hdiits:textarea>	
				</td>
			</tr>
		</table>
	</hdiits:fieldGroup>

<table width="100%" align="center">	
	<tr align="center">
		<td align="center">
			<a onclick="openEMICalc()" href="#"><fmt:message key="loan.emiCalClk" bundle="${loanCaptions}" /></a>	
		</td>
	</tr>
</table>
<!-- For attachment : Start-->	
<table align="center" width="100%">
	<tr align="center">
		<td align="center">
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="loanAttachment" />
			<jsp:param name="formName"  value="loanReqPage" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N"/>    
			</jsp:include>
		</td>
	</tr>
</table>
<!-- For attachment : End--> 


<hdiits:fieldGroup bundle="${loanCaptions}" titleCaptionId="loan.prevAdvDtl" expandable="true" id="prevLoanDtl">
	<c:set var="srNo" value="${1}" />
	<display:table list="${loanMstList}" id="prevLoanLst" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1">	
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.SrNo" headerClass="datatableheader" sortable="true" >${srNo}</display:column>
			<c:set var="srNo" value="${srNo+1}" />
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.loanType" headerClass="datatableheader" sortable="true" >${prevLoanLst.cmnLookupMst.lookupDesc}</display:column>	    
			<fmt:formatDate pattern="dd/MM/yyyy" value="${prevLoanLst.createdDate}" var="createDt"/>
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.applDt" headerClass="datatableheader" sortable="true" >${createDt}</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.reqAmt" headerClass="datatableheader" sortable="true" >${prevLoanLst.advAmt}</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.sancAmt" headerClass="datatableheader" sortable="true" >
				
				<c:if test="${prevLoanLst.approveFlag!='Y'}">
					N/A
				</c:if>
				<c:if test="${prevLoanLst.approveFlag=='Y'}">
					${prevLoanLst.amtSanc}
				</c:if>
				
			
			</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.totAmtRepaid" headerClass="datatableheader" sortable="true" >N/A</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.amtDue" headerClass="datatableheader" sortable="true" >N/A</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.dtLastInst" headerClass="datatableheader" sortable="true" >N/A</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.appStatus" headerClass="datatableheader" sortable="true" >
				
				<c:if test="${prevLoanLst.approveFlag=='P'}">
				<fmt:message key="loan.pending" bundle="${loanCaptions}"></fmt:message>
				</c:if>
				
				<c:if test="${prevLoanLst.approveFlag=='Y'}">
				<fmt:message key="loan.approve" bundle="${loanCaptions}"></fmt:message>
				</c:if>
				
				<c:if test="${prevLoanLst.approveFlag=='N'}">
				<fmt:message key="loan.reject" bundle="${loanCaptions}"></fmt:message>
				</c:if>
				
				<c:if test="${prevLoanLst.approveFlag=='C'}">
				<fmt:message key="loan.cancel" bundle="${loanCaptions}"></fmt:message>
				</c:if>
			</display:column>	    					
																		
		<display:footer media="html"></display:footer>		
	</display:table>			
</hdiits:fieldGroup>	

<hdiits:jsField jsFunction="frmValidateInbox()" name="frmValidateInbox"/>

	</div>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
	</script>
<!--Hidden Fields-->
<hdiits:hidden name="corrId" id="corrId" default="${hrEssLoanMstObj.corrId}"/>

<hdiits:validate controlNames="" locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


<script>
callOnLoadInbox();
</script>


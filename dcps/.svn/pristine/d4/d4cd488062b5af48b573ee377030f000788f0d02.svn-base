<%
try {
%>

<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/address.js"/>"></script>	
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>	
<script type="text/javascript" src="<c:url value="script/hrms/ess/reimb/hr_remb_validations.js"/>"></script>	

<fmt:setBundle basename="resources.ess.reimb.reimbLables" var="enLables" scope="request"/>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="hrReimbursementList" value="${resValue.hrReimbursementList}"></c:set>
<c:set var="billType" value="${resValue.billType}"></c:set>

<c:set var="intiatorFlag" value="${resValue.intiatorFlag}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<hdiits:form name="inboxReimburse" validate="true" action="./hrms.htm?actionFlag=saveBillCorrespondance" encType="multipart/form-data" method="POST">

<table width="100%" >
	<tr>
		<td>
			<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
		</td>
	</tr>
	</table>
		
<hdiits:fieldGroup bundle="${enLables}" titleCaptionId="RM.BillDetails" id="officeDetailsHeader">
		 <table width="100%" id="officeDetails" align="center" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="datatable"  >
			<tr class="datatableheader" style="background-color:${tdBGColor}" align="center" >
				<td><hdiits:caption captionid="RM.SrNo" bundle="${enLables}" /></td>
				<td><hdiits:caption captionid="RM.Rtype" bundle="${enLables}" /></td>
				<td><hdiits:caption captionid="RM.BillNo" bundle="${enLables}" /></td>
				<td><hdiits:caption captionid="RM.BillDate" bundle="${enLables}" /></td>
				<td><hdiits:caption captionid="RM.Amt" bundle="${enLables}"/></td>
				
				<c:if test="${intiatorFlag eq 'office'}">
				<td><hdiits:caption captionid="RM.Location" bundle="${enLables}"/></td>
				</c:if>
				
				<c:if test="${billType eq 'Telephone_Bill' or billType eq 'Electricity_Bill' or billType eq 'NewsPaper_Bill'}">
				
					<td><hdiits:caption captionid="RM.Fromdate" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.Todate" bundle="${enLables}" /></td>
				
				</c:if>
				
				<c:if test="${billType eq 'Telephone_Bill'}">
					
					<td><hdiits:caption captionid="RM.contactType" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.contactNo" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.noOfCall" bundle="${enLables}"/></td>
				
				</c:if>
				
				<c:if test="${billType eq 'Telephone_Bill' or billType eq 'Electricity_Bill'}">
					
					<td><hdiits:caption captionid="RM.billDueDt" bundle="${enLables}"/></td>
				
				</c:if>
				
				<c:if test="${billType eq 'NewsPaper_Bill'}">
					
					<td><hdiits:caption captionid="RM.agencyName" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.scrapItem" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.admAmt" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.newsPaper" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.magazine" bundle="${enLables}" /></td>
				</c:if>
				
				<c:if test="${billType eq 'Electricity_Bill'}">
					
					<td><hdiits:caption captionid="RM.meterNo" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.noOfUnit" bundle="${enLables}"/></td>
				
				</c:if>
				
				<td><hdiits:caption captionid="RM.Remarks" bundle="${enLables}"/></td>
				<td><hdiits:caption captionid="RM.status" bundle="${enLables}"/></td>
				<td><hdiits:caption captionid="RM.action" bundle="${enLables}"/></td>
			</tr>
			
			<c:set var="count" value="1"></c:set>
			<c:forEach items="${hrReimbursementList}" var="hrEssReimbMst">
			<tr>
				<td><c:out value="${count}"></c:out></td>
				<td><c:out value="${hrEssReimbMst.cmnLookupMstByReimbType.lookupDesc}"></c:out></td>
				<td><c:out value="${hrEssReimbMst.billNo}"></c:out></td>
				<fmt:formatDate value="${hrEssReimbMst.billDate}" pattern="dd/MM/yyyy" var="billDate"/>
				<td><c:out value="${billDate}"></c:out></td>
				<td><c:out value="${hrEssReimbMst.billAmount}"></c:out></td>
				
				<c:if test="${hrEssReimbMst.intiateFlag eq 'office'}">
				<td><c:out value="${hrEssReimbMst.cmnLocationMst.locName}"></c:out></td>
				</c:if>
				
			<c:if test="${hrEssReimbMst.cmnLookupMstByReimbType.lookupName eq 'Telephone_Bill' or hrEssReimbMst.cmnLookupMstByReimbType.lookupName eq 'Electricity_Bill' or hrEssReimbMst.cmnLookupMstByReimbType.lookupName eq 'NewsPaper_Bill'}">
				
				
				<fmt:formatDate value="${hrEssReimbMst.fromDate}" pattern="dd/MM/yyyy" var="fromDate" />
				<td><c:out value="${fromDate}"></c:out></td>
				<fmt:formatDate value="${hrEssReimbMst.toDate}" pattern="dd/MM/yyyy" var="toDate"/>
				<td><c:out value="${toDate}"></c:out></td>
				
				<c:if test="${hrEssReimbMst.cmnLookupMstByReimbType.lookupName eq 'Telephone_Bill'}">
					
					<c:forEach items="${hrEssReimbMst.hrEssTeleReimbDtls}" var="hrEssTeleReimbDtl">
					<td><c:out value="${hrEssTeleReimbDtl.cmnLookupMst.lookupDesc}"></c:out></td>
					<td><c:out value="${hrEssTeleReimbDtl.contactNo}"></c:out></td>
					<td><c:out value="${hrEssTeleReimbDtl.noOfCall}"></c:out></td>
					<fmt:formatDate value="${hrEssTeleReimbDtl.billDueDate}" pattern="dd/MM/yyyy" var="billDueDate"/>
					<td><c:out value="${billDueDate}"></c:out></td>
					</c:forEach>

				</c:if>
				
				<c:if test="${hrEssReimbMst.cmnLookupMstByReimbType.lookupName eq 'NewsPaper_Bill'}">
					
					<c:forEach items="${hrEssReimbMst.hrEssNewsReimbDtls}" var="hrEssNewsReimbDtl">
					<td><c:out value="${hrEssNewsReimbDtl.hrEssRembAgencyMst.agencyName}" default="-"></c:out></td>
					<td><c:out value="${hrEssNewsReimbDtl.scrapItem}"></c:out></td>
					<td><c:out value="${hrEssNewsReimbDtl.admAmount}"></c:out></td>
					<td><c:out value="${hrEssNewsReimbDtl.newspaperNm}" default="-"></c:out></td>
					<td><c:out value="${hrEssNewsReimbDtl.magazineNm}" default="-"></c:out></td>
					</c:forEach>

				</c:if>
				
				<c:if test="${hrEssReimbMst.cmnLookupMstByReimbType.lookupName eq 'Electricity_Bill'}">
					
					<c:forEach items="${hrEssReimbMst.hrEssElectReimbDtls}" var="hrEssElectReimbDtl">
					<fmt:formatDate value="${hrEssElectReimbDtl.billDueDate}" pattern="dd/MM/yyyy" var="billDueDate"/>
					<td><c:out value="${billDueDate}"></c:out></td>
					<td><c:out value="${hrEssElectReimbDtl.meterNo}"></c:out></td>
					<td><c:out value="${hrEssElectReimbDtl.noOfUnits}"></c:out></td>	
					</c:forEach>
					
				</c:if>
			</c:if>	
				<td><c:out value="${hrEssReimbMst.remarks}"></c:out></td>
				<c:if test="${hrEssReimbMst.cmnLookupMstByBillApproveFlag.lookupName eq 'Bill_Reject'}">
				<td><font color="Red"><b><c:out value="${hrEssReimbMst.cmnLookupMstByBillApproveFlag.lookupDesc}"></c:out></b></font></td>
				</c:if>
				
				<c:if test="${hrEssReimbMst.cmnLookupMstByBillApproveFlag.lookupName eq 'Bill_Approve'}">
				<td><font color="Blue"><b><c:out value="${hrEssReimbMst.cmnLookupMstByBillApproveFlag.lookupDesc}"></c:out></b></font></td>
				</c:if>
				
				<c:if test="${hrEssReimbMst.cmnLookupMstByBillApproveFlag.lookupName eq 'Bill_Pending'}">
				<td><font color="Orange"><b><c:out value="${hrEssReimbMst.cmnLookupMstByBillApproveFlag.lookupDesc}"></c:out></b></font></td>
				</c:if>
				<td>
					<hdiits:radio name="billApprovalFlag${count}" id="Bill_Approve${count}"  value="Approve" captionid="RM.approve" bundle="${enLables}" /><br>
					<hdiits:radio name="billApprovalFlag${count}" id="Bill_Pending${count}" value="Pending" captionid="RM.pending" bundle="${enLables}"/><br>
					<hdiits:radio name="billApprovalFlag${count}" id="Bill_Reject${count}" value="Reject" captionid="RM.reject" bundle="${enLables}"/>
					<hdiits:hidden name="reqId${count}" id="reqId${count}" default="${hrEssReimbMst.reqId}"/>
				</td>
			</tr>
			
			<script type="text/javascript">

			var flagId = '${hrEssReimbMst.cmnLookupMstByBillApproveFlag.lookupName}'+'${count}';
			document.getElementById(flagId).checked = 'true';
			
			</script>
			
			<c:set var="count" value="${count+1}"></c:set>
			</c:forEach>
			
		</table>
</hdiits:fieldGroup>

<br>
<br>

<table width="100%">
	<td class="fieldLabel" width="100%">
		    <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
    	   	<jsp:param name="attachmentName" value="bill" />
         	<jsp:param name="formName" value="inboxReimburse" />
	       	<jsp:param name="attachmentType" value="Document" />
		   	<jsp:param name="multiple" value="N" />  
		   	<jsp:param name="readOnly" value="Y"/>
		   </jsp:include>
    </td>

</table>	
<hdiits:hidden name="recordCount" id="recordCount" default="0"/>	
<script>

document.getElementById('recordCount').value = '${count}';

</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
			
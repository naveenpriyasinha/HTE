<% 
try
{
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" 	var="QtrLables" scope="request" />
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/vacatingQuarter.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EmpDetVO" value="${resValue.EmpDet}" />
<c:set var="EmpDetailsVO" value="${resValue.EmpDetailsVO}" />
<c:set var="PrevFlag" value="${resValue.PrevFlag}"></c:set>
<c:set var="PrevQtrList" value="${resValue.PrevQtrList}"></c:set>
<c:set var="QtrDtls" value="${resValue.QtrDtls}"></c:set>
<c:set var="AllocationEndDt" value="${resValue.AllocationEndDt}"></c:set>
<c:set var="reqId" value="${resValue.reqId}"></c:set>
<c:set var="approvedFlag" value="${resValue.approvedFlag}"></c:set>
<c:set var="apprPrevQtrFlag" value="${resValue.apprPrevQtrFlag}"></c:set>
<c:set var="prevQtrListForApp" value="${resValue.prevQtrListForApp}"></c:set>
<c:set var="attchFlag" value="${resValue.attchFlag}"></c:set>
<c:set var="strQtrAllocationDate" value="${resValue.strQtrAllocationDate}"></c:set>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables"
	var="QtrLables" scope="request" />
<script type="text/javascript" language="JavaScript">
var endDate ='${AllocationEndDt}';
var startDate ='${strQtrAllocationDate}';

var vacateQuarterArray = new Array();
vacateQuarterArray[0]='<fmt:message key="HRMS.dateValidation" bundle="${QtrLables}" />';


</script>
<hdiits:form name="form1" validate="true" action="hrms.htm?actionFlag=forwardVacateQtrReq" method="post" >


	<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>       
	<br>
	
<hdiits:fieldGroup titleCaptionId= "HRMS.PreviousAllotedQuarters" bundle="${QtrLables}" id="PrevAllocQuarter">
<!--  
	<table width="100%">
		<tbody>
  			<tr bgColor="#386cb7">
    			<td align="center" class="fieldLabel" colSpan="5"><FONT color="#ffffff" ><strong><u>
    				<fmt:message key="HRMS.PreviousAllotedQuarters" bundle="${QtrLables}"/>
      				</u></strong></font>
      			</td>
     		</tr>
     	</tbody>
	</table>
	
	 -->

	<table class="tabtable" id="table123"  border="1" style="border-collapse: collapse;" borderColor="BLACK">
		<tbody>
			<c:if test="${approvedFlag == 0}">
	  			<c:if test="${PrevFlag == 0}">
					<tr>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}"/></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QtrType" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QuarterName" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" /></b></td>
					</tr>
				
					<c:forEach var="preQtrList" items="${PrevQtrList}">
						<tr>
							<td class="fieldLabel" align="center"><c:out value="${preQtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}"/></td>
							<td class="fieldLabel" align="center"><c:out value="${preQtrList.hrEssQtrMstQtrId.namePoliceLine}"/></td>
							<td class="fieldLabel" align="center"><c:out value="${preQtrList.hrQuaterTypeMst.quaType}"/></td>
							<td class="fieldLabel" align="center"><c:out value="${preQtrList.quarterName}"/></td>
							<td class="fieldLabel" align="center"><fmt:formatDate value="${preQtrList.allocationStartDate}" pattern="dd-MM-yyyy"/></td>
							<td class="fieldLabel" align="center"><fmt:formatDate value="${preQtrList.allocationEndDate}" pattern="dd-MM-yyyy"/></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${PrevFlag == 1}">
		   		<tr>
		   			<td align="center">
		   				<font color="black"><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}"/></b></font>
		   			</td>
		   		</tr>
		  		</c:if>
			</c:if>
			<c:if test="${approvedFlag == 1}">
				<c:if test="${apprPrevQtrFlag == 1}">
					<tr>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}"/></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QtrType" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QuarterName" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></td>
						<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" /></b></td>
					</tr>
					<c:forEach var="preQtrList" items="${prevQtrListForApp}">
					<tr>
						<td class="fieldLabel" align="center"><c:out value="${preQtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}"/></td>
						<td class="fieldLabel" align="center"><c:out value="${preQtrList.hrEssQtrMstQtrId.namePoliceLine}"/></td>
						<td class="fieldLabel" align="center"><c:out value="${preQtrList.hrQuaterTypeMst.quaType}"/></td>
						<td class="fieldLabel" align="center"><c:out value="${preQtrList.quarterName}"/></td>
						<td class="fieldLabel" align="center"><fmt:formatDate value="${preQtrList.allocationStartDate}" pattern="dd-MM-yyyy"/></td>
						<td class="fieldLabel" align="center"><fmt:formatDate value="${preQtrList.allocationEndDate}" pattern="dd-MM-yyyy"/></td>
					</tr>
				</c:forEach>
			</c:if>
					
			<c:if test="${apprPrevQtrFlag == 0}">
			   <tr>
		   			<td align="center">
		   				<font color="red"><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}"/></b></font>
		   			</td>
		   		</tr>
			</c:if>
		</c:if>
	</tbody>
	
	</table>
	</hdiits:fieldGroup>
	<br>
<hdiits:fieldGroup titleCaptionId= "HRMS.AppDetails" bundle="${QtrLables}" id="PrevAllocQuarter">
	<!--  <table width="100%">
		<tbody>
  			<tr bgcolor="#386cb7" >
    			<td align="center" class="fieldLabel" colSpan="5"><font color="#ffffff"><strong><u><fmt:message key="HRMS.AppDetails" bundle="${QtrLables}"/> 	</u></strong></font></td>
      		</tr>
      	</tbody>
	</table>-->
	
	<br>

<table width="100%" align="center" >
	<tr>
		<td width="16%"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></td>
		<td width="16%"><c:out value="${QtrDtls.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}"/></td>
		<td width="16%"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></td>
		<td width="16%"><c:out value="${QtrDtls.hrEssQtrMstQtrId.namePoliceLine}"/></td>
	</tr>
	
	<tr>	
		<td width="16%"><b><hdiits:caption captionid="HRMS.QtrType" bundle="${QtrLables}" /></b></td>
		<td width="16%"><c:out value="${QtrDtls.hrQuaterTypeMst.quaType}"/></td>
		<td width="16%"><b><hdiits:caption captionid="HRMS.NameofQuarter" bundle="${QtrLables}" /></b></td>
		<td width="16%"><c:out value="${QtrDtls.quarterName}"/></td>
	</tr>
	
	<tr>
		<td width="16%"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></td>
		<td width="16%"><fmt:formatDate value="${QtrDtls.allocationStartDate}" pattern="dd/MM/yyyy"/></td>
		<td></td>
		<td></td>
	</tr>
	
	</table>

	<br>

	<table border='0' width="100%">
			<tr>
				<td>
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp" flush="true">
						<jsp:param name="attachmentName" value="BillAttachment" />
						<jsp:param name="formName" value="form1" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />    
                        </jsp:include>
                        <script>
							document.getElementById('target_uploadBillAttachment').style.display='none';
							document.getElementById('formTable1BillAttachment').firstChild.firstChild.style.display='none';
						</script> 
					<!-- For attachment : End-->
				</td>
			</tr>
	</table>
<br>
	<table width="100%">
			<tr>
			<td width="25%">
				<b><hdiits:caption captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" /></b>
			</td>
			<td width="25%">	
			 <c:if test="${approvedFlag == 0}">
				<hdiits:dateTime  validation="txt.isrequired" name="TentetiveEndDate" mandatory="true" caption="TentetiveEndDate"  afterDateSelect="verifyDate()" captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" maxvalue="31/12/9999" ></hdiits:dateTime>
			</c:if>
			<c:if test="${approvedFlag == 1}">
				<c:out value="${resValue.AllocationEndDt}" />
			</c:if>	
			</td>
			<td width="25%"></td>
			<td width="25%"></td>
			</tr>
			
	</table>
	</hdiits:fieldGroup>
	<c:if test="${approvedFlag == 0}">
		 <table id="tabnavigationTbl" style="display:none">
		 <tr><td>
			<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" >
				<jsp:param name="disableReset" value="true"/> 
			</jsp:include></td></tr>
		</table>	
			<script type="text/javascript">
			    //initializetabcontent("maintab");
		    </script>
			<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
		 
		<script>
			loadData();
		</script>
		</c:if>
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${resValue.reqId}"/>		
 
</hdiits:form>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%>
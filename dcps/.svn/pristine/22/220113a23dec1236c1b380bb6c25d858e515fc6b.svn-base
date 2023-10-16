<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<fmt:setBundle basename="resources.dept.deptLables" var="commonLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>




<table width=100% id="suspReviewTab0" >
	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="4" align="center">
		<font color="#ffffff">
		<strong><u><fmt:message key="dept.suspReview"/></u></strong>
		</font>
		</td>
	</tr>
</table>

<table width="100%" id="suspReviewTab1">
	<tr>
		<td>
			<fmt:message key="dept.suspDt"/>
		</td>
	
		<td>
		<hdiits:dateTime name="suspensionDt" captionid="dept.suspOrderDt"  bundle="${commonLables}" />
		</td>

		<td>
			<fmt:message key="dept.suspReviewDt"/>
		</td>
	
		<td>
		<hdiits:dateTime name="reviewDt" captionid="dept.suspOrderDt"  bundle="${commonLables}" />
		</td>

	</tr>
	
	<tr>
		<td>
			<fmt:message key="dept.suspLastReviewDt"/>
		</td>
	
		<td>
		<hdiits:dateTime name="lastReviewDt" captionid="dept.suspOrderDt"  bundle="${commonLables}" />
		</td>

		<td>
			<fmt:message key="dept.reasonNoReview"/>
		</td>
	
		<td>
		<hdiits:textarea name="reasonNotReview"/>
		</td>

	</tr>
	
	<tr>
		<td>
			<fmt:message key="dept.susp1Year"/>
		</td>
	
		<td>
		<hdiits:dateTime name="oneYearSusp" captionid="dept.suspOrderDt"  bundle="${commonLables}"  />
		</td>

		<td>
			<fmt:message key="dept.susp2Year"/>
		</td>
	
		<td>
		<hdiits:dateTime name="twoYearSusp" captionid="dept.suspOrderDt"  bundle="${commonLables}"  />
		</td>

	</tr>
	
	<tr>
		<td>
			<fmt:message key="dept.suspVCAdvice"/>
		</td>
	
		<td>
		<hdiits:radio name="suspendedVCRadio" value="y"/><fmt:message key="dept.y"/>
		<hdiits:radio name="suspendedVCRadio" value="n"/><fmt:message key="dept.n"/>
		</td>

		<td>
			<fmt:message key="dept.suspAllow"/>
		</td>
	
		<td>
		<hdiits:number name="suspAllowance" maxlength="8" size="20"/>
		</td>

	</tr>
	<tr>
		<td>
			<fmt:message key="dept.suspheadQuater"/>
		</td>
	
		<td>
		<hdiits:text name="headQuater"/>
		</td>

		<td>
			<fmt:message key="dept.suspPvtBusiness"/>
		</td>
	
		<td>
		<hdiits:radio name="pvtBusinessRadio" value="y"/><fmt:message key="dept.y"/>
		<hdiits:radio name="pvtBusinessRadio" value="n"/><fmt:message key="dept.n"/>
		</td>

	</tr>
	<tr>
		<td>
			<fmt:message key="dept.suspNonEmp"/>
		</td>
	
		<td>
		<hdiits:radio name="nonEmpRadio" value="y"/><fmt:message key="dept.y"/>
		<hdiits:radio name="nonEmpRadio" value="n"/><fmt:message key="dept.n"/>
		
		</td>

		<td>
			<fmt:message key="dept.suspFitReinstmt"/>
		</td>
	
		<td>
		<hdiits:radio name="reinstatementRadio" value="y"/><fmt:message key="dept.y"/>
		<hdiits:radio name="reinstatementRadio" value="n"/><fmt:message key="dept.n"/>
		</td>

	</tr>
	
	<tr>
		<td>
			<fmt:message key="dept.suspBriefDtls"/>
		</td>
	
		<td>
		<hdiits:textarea name="briefDetails"  />
		</td>
	</tr>
	
	<tr>
		<td>
			<fmt:message key="dept.suspReinstmtDt"/>
		</td>
	
		<td>
		<hdiits:dateTime name="reinstatementDt" captionid="dept.suspOrderDt"  bundle="${commonLables}"  />
		</td>

	</tr>
	
</table>





	
<script>
/*hideSuspReview();
defaultSuspReviewValues();
makeSuspReviewDtReadOnly();*/


function hideSuspReview()
{
	document.getElementById("suspReviewTab0").style.display='none';
	document.getElementById("suspReviewTab1").style.display='none';
}

function displaySuspReview()
{
	document.getElementById("suspReviewTab0").style.display='';
	document.getElementById("suspReviewTab1").style.display='';
}
	
function defaultSuspReviewValues()
{
	
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.reinstatementDate}" var="reinstatementDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.twoYearCompltDate}" var="twoYearCompltDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.oneYearCompltDate}" var="oneYearCompltDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.dueReviewDate}" var="dueReviewDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.lastReviewDate}" var="lastReviewDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.reviewDate}" var="reviewDate"/>
	
	document.inquiryCaseTracking.reinstatementDt.value="${reinstatementDate}";
	document.inquiryCaseTracking.twoYearSusp.value="${twoYearCompltDate}";
	document.inquiryCaseTracking.oneYearSusp.value="${oneYearCompltDate}";
	document.inquiryCaseTracking.reviewDt.value="${dueReviewDate}";
	document.inquiryCaseTracking.lastReviewDt.value="${lastReviewDate}";
	//document.inquiryCaseTracking.reviewDt.value="${reviewDate}";

	if("${objHrEmpSuspReview.isSuspenVc}"=='y')
	{
	document.inquiryCaseTracking.suspendedVCRadio[0].checked=true;
	}
	else if ("${objHrEmpSuspReview.isSuspenVc}"=='n')
	{
	document.inquiryCaseTracking.suspendedVCRadio[1].checked=true;
	}
	
	if("${objHrEmpSuspReview.isPrivateBusiness}"=='y')
	{
	document.inquiryCaseTracking.pvtBusinessRadio[0].checked=true;
	}
	else if ("${objHrEmpSuspReview.isPrivateBusiness}"=='n')
	{
	document.inquiryCaseTracking.pvtBusinessRadio[1].checked=true;
	}
	
	if("${objHrEmpSuspReview.isFitReinstatement}"=='y')
	{
	document.inquiryCaseTracking.reinstatementRadio[0].checked=true;
	}
	else if ("${objHrEmpSuspReview.isFitReinstatement}"=='n')
	{
	document.inquiryCaseTracking.reinstatementRadio[1].checked=true;
	}
	
	if("${objHrEmpSuspReview.isNonEmpReceived}"=='y')
	{
	document.inquiryCaseTracking.nonEmpRadio[0].checked=true;
	}
	else if ("${objHrEmpSuspReview.isNonEmpReceived}"=='n')
	{
	document.inquiryCaseTracking.nonEmpRadio[1].checked=true;
	}

}
	function makeSuspReviewDtReadOnly()
	{
	document.inquiryCaseTracking.reinstatementDt.readOnly=true;
	document.inquiryCaseTracking.twoYearSusp.readOnly=true;
	document.inquiryCaseTracking.oneYearSusp.readOnly=true;
	document.inquiryCaseTracking.reviewDt.readOnly=true;
	document.inquiryCaseTracking.lastReviewDt.readOnly=true;
	document.inquiryCaseTracking.reviewDt.readOnly=true;
	}
	
</script>	

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
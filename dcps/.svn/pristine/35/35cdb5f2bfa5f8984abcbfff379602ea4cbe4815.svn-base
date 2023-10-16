<%
try {
%>

<c:set var="objHrEmpSuspReview" value="${resValue.objHrEmpSuspReview}"></c:set>

<hdiits:fieldGroup titleCaptionId="dept.suspReview" bundle="${deptLables}" id="suspReviewTab0">

<table width="100%" id="suspReviewTab1" style="display:none">
	<tr>
		<td>
			<hdiits:caption captionid="dept.suspDt" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:dateTime name="suspensionDt" captionid="dept.suspDt" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00"/>
		</td>
		<td>
			<hdiits:caption captionid="dept.suspLastReviewDt" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:dateTime name="lastReviewDt" captionid="dept.suspLastReviewDt" onblur="dueSuspDtDefault(this);" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00"/>
		</td>
		

	</tr>
	
	<tr>
		

		<td>
			<hdiits:caption captionid="dept.reasonNoReview" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:textarea name="reasonNotReview" default="${objHrEmpSuspReview.notReviewReason}" onblur="restrictSplChar(this);" maxlength="100"  rows="3" cols="25"/>
		</td>
		<td>
			<hdiits:caption captionid="dept.suspReviewDt" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:dateTime name="reviewDt" captionid="dept.suspReviewDt" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00" disabled="true"/>
		</td>
	</tr>
	
	<tr>
		<td>
			<hdiits:caption captionid="dept.susp1Year" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:dateTime name="oneYearSusp" captionid="dept.susp1Year" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00"/>
		</td>

		<td>
			<hdiits:caption captionid="dept.susp2Year" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:dateTime name="twoYearSusp" captionid="dept.susp2Year" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00"/>
		</td>

	</tr>
	
	<tr>
		<td>
			<fmt:message key="dept.suspVCAdvice"/>
		</td>
	
		<td>
		<hdiits:radio name="suspendedVCRadio" value="y" bundle="${deptLables}" captionid="dept.y"/>
		<hdiits:radio name="suspendedVCRadio" value="n" bundle="${deptLables}" captionid="dept.n"/>
		</td>

		<td>
			<hdiits:caption captionid="dept.suspAllow" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:number name="suspAllowance" default="${objHrEmpSuspReview.suspenAllowRate}" onblur="checkIsNumber(this)"  maxlength="8" size="20"/>
		</td>

	</tr>
	<tr>
		<td>
			<hdiits:caption captionid="dept.suspheadQuater" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:text name="headQuater" default="${objHrEmpSuspReview.headQuarter}" onblur="restrictSplChar(this);" maxlength="20"/>
		</td>

		<td>
			<hdiits:caption captionid="dept.suspPvtBusiness" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:radio name="pvtBusinessRadio" value="y" bundle="${deptLables}" captionid="dept.y"/>
		<hdiits:radio name="pvtBusinessRadio" value="n" bundle="${deptLables}" captionid="dept.n"/>
		</td>

	</tr>
	<tr>
		<td>
			<hdiits:caption captionid="dept.suspNonEmp" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:radio name="nonEmpRadio" value="y" bundle="${deptLables}" captionid="dept.y"/>
		<hdiits:radio name="nonEmpRadio" value="n" bundle="${deptLables}" captionid="dept.n"/>
		
		</td>

		<td>
			<hdiits:caption captionid="dept.suspFitReinstmt" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:radio name="reinstatementRadio" value="y" bundle="${deptLables}" captionid="dept.y"/>
		<hdiits:radio name="reinstatementRadio" value="n" bundle="${deptLables}" captionid="dept.n"/>
		</td>

	</tr>
	
	<tr>
		<td>
			<hdiits:caption captionid="dept.suspBriefDtls" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:textarea name="briefDetails"  default="${objHrEmpSuspReview.caseDetail}" onblur="restrictSplChar(this);" maxlength="150"  rows="3" cols="25"/>
		</td>
	</tr>
	
	<tr>
		<td>
			<hdiits:caption captionid="dept.suspReinstmtDt" bundle="${deptLables}" />
		</td>
	
		<td>
		<hdiits:dateTime name="reinstatementDt" captionid="dept.suspReinstmtDt" bundle="${deptLables}" maxvalue="31/12/2099 00:00:00"/>
		</td>

	</tr>
	
</table>

</hdiits:fieldGroup>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
	
<script>

<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.reinstatementDate}" var="reinstatementDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.twoYearCompltDate}" var="twoYearCompltDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.oneYearCompltDate}" var="oneYearCompltDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.dueReviewDate}" var="dueReviewDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.lastReviewDate}" var="lastReviewDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrEmpSuspReview.reviewDate}" var="suspensionDt"/>
	
	
hideSuspReview();
defaultSuspReviewValues();
makeSuspReviewDtReadOnly();

var suspReviewSuspVC="${objHrEmpSuspReview.isSuspenVc}";
var suspReviewPrivBusi="${objHrEmpSuspReview.isPrivateBusiness}";
var suspReviewFit="${objHrEmpSuspReview.isFitReinstatement}";
var suspReviewNonEmp="${objHrEmpSuspReview.isNonEmpReceived}";

function defaultSuspReviewValues()
{
	
	
	document.inquiryCaseTracking.reinstatementDt.value="${reinstatementDate}";
	document.inquiryCaseTracking.twoYearSusp.value="${twoYearCompltDate}";
	document.inquiryCaseTracking.oneYearSusp.value="${oneYearCompltDate}";
	document.inquiryCaseTracking.reviewDt.value="${dueReviewDate}";
	document.inquiryCaseTracking.lastReviewDt.value="${lastReviewDate}";
	document.inquiryCaseTracking.suspensionDt.value="${suspensionDt}";

	if(suspReviewSuspVC=='y')
	{
	document.inquiryCaseTracking.suspendedVCRadio[0].checked=true;
	}
	else if (suspReviewSuspVC=='n')
	{
	document.inquiryCaseTracking.suspendedVCRadio[1].checked=true;
	}
	
	if(suspReviewPrivBusi=='y')
	{
	document.inquiryCaseTracking.pvtBusinessRadio[0].checked=true;
	}
	else if (suspReviewPrivBusi=='n')
	{
	document.inquiryCaseTracking.pvtBusinessRadio[1].checked=true;
	}
	
	if(suspReviewFit=='y')
	{
	document.inquiryCaseTracking.reinstatementRadio[0].checked=true;
	}
	else if (suspReviewFit=='n')
	{
	document.inquiryCaseTracking.reinstatementRadio[1].checked=true;
	}
	
	if(suspReviewNonEmp=='y')
	{
	document.inquiryCaseTracking.nonEmpRadio[0].checked=true;
	}
	else if (suspReviewNonEmp=='n')
	{
	document.inquiryCaseTracking.nonEmpRadio[1].checked=true;
	}

}
	
	
</script>	

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
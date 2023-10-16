
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
	
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="date" value="${resultValue.currdate}" />
<c:set var="doj" value="${resultValue.dateOfJoin}" />
<c:set var="resigfunc" value="${resultValue.Request_type}"></c:set>
<c:set var="Requesttype" value="${resultValue.RequestType}" />
<c:set var="Reqtype" value="${resultValue.Reqtype}" />
<c:set var="servicePeriod" value="${resultValue.servicePeriod}" />
<c:set var="fileId" value="${resultValue.fileId}"></c:set>
<c:set var="serviceWithLeaves" value="${resultValue.serviceWithLeaves}" />
<c:set var="validDate" value="${resultValue.validResigDate}" />
<c:set var="status" value="${resultValue.status}"></c:set>
<c:set var="sub_inbox" value="${resultValue.sub_inbox}"></c:set>
<c:set var="hrEssLoanMstList" value="${resultValue.hrEssLoanMstList}"></c:set>
<c:set var="loanAdvListSize" value="${resultValue.loanAdvListSize}"></c:set>
<c:set var="srvcFlag" value="${resultValue.srvcFlag}" />
<c:set var="empClass" value="${resultValue.empClass}" />

<c:set var="mapOfLeaves" value="${resultValue.mapOfLeaves}" />	
<fmt:setBundle basename="resources.ess.resignation.ResignationLabels"
	var="commonLables1" scope="request" />
	<fmt:setBundle basename="resources.ess.resignation.CessationAlertLables"
	var="alertLables" scope="request" />
	<script type="text/javascript"  src="<c:url  value="/script/hrms/ess/cessation/cessation.js"/>"></script>
<script>
function submitForm_formSubmitButtonr()
{
	var validData=true;
	if ( document.forms.CessationDet.Cessation.value == "Select")
    {
       alert('<fmt:message  bundle="${alertLables}" key="RequestType"/>');
        document.forms.CessationDet.Cessation.focus();
        validData = false;
        return;
    }
   
    if (""==document.forms.CessationDet.Date.value.length)
	{
		alert('<fmt:message  bundle="${alertLables}" key="DateIsReq"/>');
		validData=false;
		return;
	}
	if(document.getElementById("srvdate").innerHTML!="")
	{
		var lCurrDat=document.getElementById("srvdate").innerHTML;
		var lReqDat=document.forms.CessationDet.Date.value;
		lCurrDat1=lCurrDat.split("/");
		startDate = new Date(lCurrDat1[2],lCurrDat1[1]-1,lCurrDat1[0]); 
		lstartDate = new Date(startDate.valueOf()); 
		lReqDat2=lReqDat.split("/");
		endDate = new Date(lReqDat2[2],lReqDat2[1]-1,lReqDat2[0]);
		lendDate = new Date(endDate.valueOf()); 

		if(lendDate<lstartDate)
		{
			alert('<fmt:message bundle="${alertLables}" key="DateNotValid"/>');
			validData=false;
			return;
		}
	}

	if(document.getElementById("validDate").innerHTML!="")
	{	
		var validReqDate=document.getElementById("validDate").innerHTML;
		validReqDate1=validReqDate.split("/");
		newDate = new Date(validReqDate1[2],validReqDate1[1]-1,validReqDate1[0]);
		lnewDate=new Date(newDate.valueOf()); 

		if(lendDate>lnewDate){
		alert('<fmt:message bundle="${alertLables}" key="dateMoreThanValidDate"/>'+" "+validReqDate);
			validData=false;
			return;
		}
	}
	
	if (""==document.forms.CessationDet.reason.value.length)
	{
		alert('<fmt:message  bundle="${alertLables}" key="ReasonReq"/>');
		validData=false;
		return;
	}	
	var reason=document.forms.CessationDet.reason.value;
	var reasonLength=document.forms.CessationDet.reason.value.length;
	var i=0;
	while ((i < reasonLength) && (reason.charAt(i) != "&" && reason.charAt(i)!= "~" && reason.charAt(i)!=  "^" && reason.charAt(i)!= "#" && reason.charAt(i)!= "`" && reason.charAt(i)!="*" &&  reason.charAt(i)!= "'" && reason.charAt(i)!="|" && reason.charAt(i)!= "$" ))
	{
		i++
	}

	if ((i >= reasonLength) || (reason.charAt(i) != "&" && reason.charAt(i)!="|" && reason.charAt(i)!= "~" && reason.charAt(i)!=  "^" && reason.charAt(i)!= "#" && reason.charAt(i)!= "`" && reason.charAt(i)!= "'"  && reason.charAt(i)!="*" && reason.charAt(i)!= "$" ))
	{
		validData=true;
	}
	else
	{
		alert('<fmt:message  bundle="${alertLables}" key="reasonNotValidEntry"/>');
		validData=false;
		return;
	}

	if (i <= 500)
	{
		validData=true;
	}
	else
	{
		alert('<fmt:message  bundle="${alertLables}" key="lengthOfReasonNotPermissible"/>');
		validData=false;
		return;
	}

	if ( ( document.forms.CessationDet.txtYes[0].checked == false )
    && ( document.forms.CessationDet.txtYes[1].checked == false ) )
    {
       alert('<fmt:message  bundle="${alertLables}" key="SubmissionNDC"/>');
        validData = false;
        return;
    }

	if(document.forms.CessationDet.txtYes[0].checked == true){
		validData=mandatory_AttachmentResignation('ResignationName');
		//alert('validDAta while NDC Attachment >>> '+validData);
		if(validData==false){
			alert('<fmt:message  bundle="${alertLables}" key="attachNDCLetter"/>');
			return;
		}
	}

	if ( ( document.forms.CessationDet.txtYesPension[0].checked == false )
    && ( document.forms.CessationDet.txtYesPension[1].checked == false ) )
    {
       	alert('<fmt:message  bundle="${alertLables}" key="SubmissionPensionLetter"/>');
        validData = false;
        return;
    }

	if(document.forms.CessationDet.txtYesPension[0].checked == true){
		validData=mandatory_AttachmentResignation('PensionPfAttach');
		//alert('validDAta while Pension Attachment >>> '+validData);
		if(validData==false){
			alert('<fmt:message  bundle="${alertLables}" key="attachPensionLetter"/>');
			return;
		}
	}

	if(document.forms.CessationDet.txtYesPension[0].checked == true){
		if((document.forms.CessationDet.radioEligibleVRS[0].checked == false )
		&& ( document.forms.CessationDet.radioEligibleVRS[1].checked == false ) )
		{
			alert('<fmt:message  bundle="${alertLables}" key="SubmissionPensionLetter"/>');
	        validData = false;
	        return;
		}
	}

	if( validData==true  )
	{
		if(confirm('<fmt:message  bundle="${alertLables}" key="confirmSubmit"/>'))
		{
			window.document.CessationDet.submit();
			submitBut=document.getElementById("Submit");
			submitBut.disabled=true;
			return;
		}
		//endProcess();
	}
}

function closeWindow()
{
	var urlstyle="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	document.CessationDet.action=urlstyle;
	document.CessationDet.submit();
}
</script>	

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
		captionid="HRMS.CessationDetails" bundle="${commonLables1}" captionLang="single"/></b></a></li>
</ul>
</div>
<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
	<tr>
		<td><hdiits:form name="CessationDet" validate="true"
			method="POST" encType="multipart/form-data"
			action="./hrms.htm?actionFlag=submitReqData">

<hdiits:hidden name="hiddenField" id="hiddenField" default="AAAAAA"/>
<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}"/>
<hdiits:hidden name="status" id="status" default="${status}"/>
<hdiits:hidden name="eol" id="eol" default="${mapOfLeaves.eol}"/>
<hdiits:hidden name="lwp" id="lwp" default="${mapOfLeaves.lwp}"/>
<hdiits:hidden name="suspension_duration" id="suspension_duration" default="${mapOfLeaves.suspensiondur}"/>


			<div id="resig" name="resig">
			<table class="tabtable" width="100%" height="100%" bgcolor="white">

			<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp">
			<jsp:param name="empImage" value="N" /></jsp:include>

				<tr height="3">
					<td class="fieldLabel" colspan="4">
						<font color="#ffffff">
							<b><hdiits:fieldGroup titleCaptionId="HRMS.Request" bundle="${commonLables1}" expandable="true"></hdiits:fieldGroup> </b>
						</font>
					</td>
				</tr>


				<tr>
					<td colspan="1">
					<table border="0" width="100%">
						<tr>
							<td align="left"><hdiits:caption captionid="HRMS.Type" bundle="${commonLables1}"/></td>
							<td><hdiits:select name="Cessation" 
								bundle="${commonLables1}" captionid="HRMS.Type"
								validation="txt.isrequired" id="Cessation" mandatory="true"
								sort="false" onchange="showVRSAttachment()">

								<hdiits:option value="Select">
									<fmt:message key="HRMS.Select"
										bundle="${commonLables1}" />
								</hdiits:option>

								<c:forEach var="name1" items="${resigfunc}">

									<option value="<c:out value="${name1.lookupId}"/>"><c:out
										value="${name1.lookupDesc}" /></option>
								</c:forEach>
							</hdiits:select></td>
							<td align="left"><hdiits:caption captionid="HRMS.Date" bundle="${commonLables1}"/></td>
							<td align="left" id="srvdate"><fmt:formatDate 
								value="${sub_inbox.applyingDate}" 
								pattern="dd/MM/yyyy" /></td>
							<td id="dateOfJoin" style="display:none"><fmt:formatDate
								value="${doj}" pattern="dd/MM/yyyy" /></td>
								<td id="validDate" style="display:none"><fmt:formatDate
								value="${validDate}" pattern="dd/MM/yyyy" /></td>

						</tr>

						<tr>

								
							<td align="left"><hdiits:caption captionid="HRMS.RequestDate" bundle="${commonLables1}"/></td>
								<c:if test="${sub_inbox.reqDateOfCessation eq null}">
							<td align="left" id="ReqResig"><hdiits:dateTime name="Date" default="${sub_inbox.reqDateOfCessation}" maxvalue="31/12/2099" captionid="HRMS.RequestDate" bundle="${commonLables1}"  mandatory="true"  /></td>
							</c:if>
							<c:if test="${sub_inbox.reqDateOfCessation ne null}">
							<td align="left" id="reqDateOfCessation"><fmt:formatDate 
								value="${sub_inbox.reqDateOfCessation}" 
								pattern="dd/MM/yyyy" /></td>
							</c:if>
							<td align="left"><hdiits:caption captionid="HRMS.Reasons" bundle="${commonLables1}"/></td>
							<td><hdiits:textarea captionid="HRMS.Reasons" default="${sub_inbox.reason}" rows="30"  cols="40" 
								 name="reason" id="reason" bundle="${commonLables1}"
								 mandatory="true"  />
							</td>

						</tr>
						
						<tr height="3">
							<td class="fieldLabel" colspan="4">
								<font color="#ffffff">
									<b><hdiits:fieldGroup titleCaptionId="HRMS.ServiceDetails" bundle="${commonLables1}" expandable="true"></hdiits:fieldGroup> </b>
								</font>
							</td>
						</tr>
						

						<tr>
							<td align="left"> <hdiits:caption captionid="HRMS.SuspenDuration" bundle="${commonLables1}"/></td>
								<td align="left" width="25%"><c:out value="${mapOfLeaves.suspensiondur}"></c:out></td>
							

							<td align="left"><hdiits:caption captionid="HRMS.Eol" bundle="${commonLables1}"/></td>
								<td align="left" width="25%"><c:out value="${mapOfLeaves.eol}"></c:out></td>
						

						</tr>


						<tr>
							<td align="left"><hdiits:caption captionid="HRMS.Lwp" bundle="${commonLables1}"/></td>
								<td align="left" width="25%"><c:out value="${mapOfLeaves.lwp}"></c:out></td>
							
							<td align="left"><hdiits:caption captionid="HRMS.YearService" bundle="${commonLables1}"/></td>
							<td><hdiits:text name="yearOfService1" id="yearOfService1" readonly="true" default="${servicePeriod}"
								maxlength="40" size="30"
								style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
							<hdiits:hidden name="yearOfService" /></td>


						</tr>
						<tr>

							<td align="left"><hdiits:caption captionid="HRMS.SerMinusLeave" bundle="${commonLables1}"/></td>
							<td><hdiits:text name="yearOfService2" readonly="true" default="${serviceWithLeaves}"
								maxlength="40" size="30"
								style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />

							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				
				<tr height="3">
					<td class="fieldLabel" colspan="4">
						<font color="#ffffff">
							<b><hdiits:fieldGroup titleCaptionId="HRMS.NDCDetail" bundle="${commonLables1}" expandable="true"></hdiits:fieldGroup> </b>
						</font>
					</td>
				</tr>


				<tr>
					<td colspan="1">
					<table>
						<tr>

							<td align="left"><hdiits:caption captionid="HRMS.Submission" bundle="${commonLables1}"/></td>
							<td id="abc"><hdiits:radio name="txtYes" id="radioNDCYes"
								validation="sel.isradio" value="Y" captionid="rad"
								onclick="showAttach()" /><fmt:message key="HRMS.Yes"
								bundle="${commonLables1}" /> <hdiits:radio name="txtYes" id="radioNDCNo"
								validation="sel.isradio" value="N" captionid="rad"
								onclick="hideAttach()" /><fmt:message key="HRMS.No"
								bundle="${commonLables1}" /></td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td align="left">
					<table width="100%">
						<tr id="attach" style="display:none">
							<td colspan="100%">
							<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
							<jsp:param name="attachmentName" value="ResignationName" />
							<jsp:param name="formName" value="CessationDet" />
							<jsp:param name="attachmentType" value="Document" />
							<jsp:param name="attachmentTitle" value="NDC Attachment" />
							<jsp:param name="multiple" value="N" />
							</jsp:include>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<c:if test="${empClass eq 'Y'}">
				<tr>
					<td colspan="1">
					<table>
						<tr id="pensionLetterrow" style="display:none">
							<td align="left"><hdiits:caption captionid="HRMS.PensionLetter" bundle="${commonLables1}"/></td>
							<td><hdiits:radio name="txtYesPension" id="radioPensionYes" validation="sel.isradio" value="Y" captionid="rad" onclick="showPensionAttach()" />
								<fmt:message key="HRMS.Yes" bundle="${commonLables1}" /> 
								<hdiits:radio name="txtYesPension" id="radioPensionNo" validation="sel.isradio" value="N" captionid="rad" onclick="hidePensionAttach()" />
								<fmt:message key="HRMS.No" bundle="${commonLables1}" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td align="left">
					<table width="100%">
						<tr id="pensionattach" style="display:none">
							<td colspan="100%">
							<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
							<jsp:param name="attachmentName" value="PensionPfAttach" />
							<jsp:param name="formName" value="CessationDet" />
							<jsp:param name="attachmentType" value="Document" />
							<jsp:param name="attachmentTitle" value="Pension & Provident Fund Attachment" />
							<jsp:param name="multiple" value="N" />
							</jsp:include>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="1">
					<table>
						<tr id="EligibleVRSRow" style="display:none">
							<td align="left"><hdiits:caption captionid="HRMS.EligibleVRS" bundle="${commonLables1}"/></td>
							<td><hdiits:radio name="radioEligibleVRS" id="radioEligibleVRSYes" validation="sel.isradio" value="Y" captionid="rad"/>
								<fmt:message key="HRMS.Yes" bundle="${commonLables1}" /> 
								<hdiits:radio name="radioEligibleVRS" id="radioEligibleVRSNo" validation="sel.isradio" value="N" captionid="rad"/>
								<fmt:message key="HRMS.No" bundle="${commonLables1}" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				</c:if>
				
				<tr></tr>
				<tr></tr>
				
				<tr>
					<td colspan="1">
					<b>
					<hdiits:fieldGroup titleCaptionId="HRMS.Verification" bundle="${commonLables1}" expandable="true" id="verificationFieldGroup" collapseOnLoad="true">
					<table border="1" width="100%" bordercolor="black" style="border-collapse: collapse" align="center"  >
						<tr bgcolor="#386CB7">
							<td align="left" colspan="5">
								<font color="#ffffff">
									<center><b><hdiits:caption captionid="HRMS.DepartmentalInquiry" bundle="${commonLables1}"/></b></center>
								</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">
							<td align="left" ><hdiits:caption captionid="HRMS.NoDepartment" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.Punishment" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.StartDateDept" bundle="${commonLables1}"/></td>
							<td align="left" colspan="2" ><hdiits:caption captionid="HRMS.Details" bundle="${commonLables1}"/></td>
						</tr>

						<tr>
							<td align="center"  ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							<td align="center"  ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							<td align="center" colspan="2" ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>
						
						<tr bgcolor="#386CB7">
							<td align="left" colspan="5">
								<font color="#ffffff">
									<center><b><hdiits:caption captionid="HRMS.LoanAdvances" bundle="${commonLables1}"/></b></center>
								</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">
							<td align="left" ><hdiits:caption captionid="HRMS.LoanType" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.AppliedDate" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.ApprovedDate" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.AmtSanc" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.AmtPaidBack" bundle="${commonLables1}"/></td>
						</tr>

					
						<c:if test="${loanAdvListSize lt '1'}">
						<tr>
							<td align="left"   colspan="5"><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>
						</c:if>	

						<c:forEach var="ItrhrEssLoanMstList" items="${hrEssLoanMstList}">
						<tr>
							<td align="center"  >
								<c:if test="${ItrhrEssLoanMstList.cmnLookupMst eq null}">
									<c:out  value="N/A"></c:out>
								</c:if>
							
								<c:if test="${ItrhrEssLoanMstList.cmnLookupMst ne null}">
									<c:out  value="${ItrhrEssLoanMstList.cmnLookupMst.lookupDesc}"></c:out>
								</c:if>
							</td>
							
							<td align="center" >
								<c:if test="${ItrhrEssLoanMstList.createdDate ne null }">
									<fmt:formatDate value="${ItrhrEssLoanMstList.createdDate}" pattern="dd/MM/yyyy" />
								</c:if>
								<c:if test="${ItrhrEssLoanMstList.createdDate eq null }">
									<c:out value="N/A"></c:out>
								</c:if>
							</td>
							
							<td align="center" >
								<c:if test="${ItrhrEssLoanMstList.appDt ne null }">
									<fmt:formatDate value="${ItrhrEssLoanMstList.appDt}" pattern="dd/MM/yyyy" />
								</c:if>
								<c:if test="${ItrhrEssLoanMstList.appDt eq null }">
									<c:out value="N/A"></c:out>
								</c:if>
									
							</td>
								
							<td align="center" >
								<c:if test="${ItrhrEssLoanMstList.amtSanc ne ''}">
									<c:out value="${ItrhrEssLoanMstList.amtSanc}"></c:out>
								</c:if>
								
								<c:if test="${ItrhrEssLoanMstList.amtSanc eq '' or ItrhrEssLoanMstList.amtSanc eq null}">
									<c:out value="N/A"></c:out>
								</c:if>
							</td>
							
							<td align="center" >
								<c:if test="${ItrhrEssLoanMstList.eligibleAmt ne '' }">
									<c:out value="N/A"></c:out>
								</c:if>
								<c:if test="${ItrhrEssLoanMstList.eligibleAmt eq '' or ItrhrEssLoanMstList.eligibleAmt eq null}">
									<c:out value="N/A"></c:out>
								</c:if>
							</td>

						</tr>
						</c:forEach>
					</table>
					</hdiits:fieldGroup>
					</b>
					</td>
				</tr>
			</table>
			</div>
			<br><br>
			
			<table align="center" width="100%">
				<tr>
					<td align="center">
					<c:if test="${Reqtype eq 'inboxRequest'}">
						<c:if test="${srvcFlag ne 'Y'}">
							<hdiits:button name="Submit" id="Submit" type="button" onclick="submitForm_formSubmitButtonr()" value="Submit" captionid="HRMS.SUBMIT" bundle="${commonLables1}"/>
							<hdiits:button name="Close" type="button" onclick="closeWindow()" value="Close" id="Reset" captionid="HRMS.CLOSE" bundle="${commonLables1}"/>
						</c:if>
					</c:if>
					</td>
				</tr>
			</table>
		
		<script type="text/javascript">
			calYearOfService();
			initializetabcontent("maintab")
		</script>
		<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
		<hdiits:hidden name="empClass" id="empClass" default="${empClass}"/> 
		</hdiits:form>
		
		<c:if test="${Requesttype ne null}">
			<script>
				var reqTyp=document.getElementById("Cessation");
				reqTyp.options[reqTyp.selectedIndex].text="${Requesttype}";
				reqTyp.selected="true";
				reqTyp.disabled="true";
			</script>
		</c:if>
		
		<c:if test="${sub_inbox.reason ne null}">
			<script>
				document.getElementById("reason").readOnly="true";
			</script>
		</c:if>
		
		<c:if test="${sub_inbox.submissionNdc eq 'Y'}">
			<script>
				document.getElementById("radioNDCYes").checked="true";
				document.getElementById("radioNDCYes").disabled="true"
			</script>
		</c:if>
		
		<c:if test="${sub_inbox.submissionNdc eq 'N'}">
			<script>
				document.getElementById("radioNDCNo").checked="true";
				document.getElementById("radioNDCNo").disabled="true"
			</script>
		</c:if>
		
		<c:if test="${sub_inbox.submissionNdc ne null}">
			<script>
				document.getElementById("attach").style.display="none";	
				document.getElementById("radioNDCYes").onclick="false";
				document.getElementById("radioNDCNo").onclick="false";
			</script>
		</c:if>
		<c:if test="${sub_inbox.cmnAttachmentMst.attachmentId ne null}">
			<script>
				document.getElementById('target_uploadResignationName').style.display='none';
				document.getElementById('formTable1ResignationName').firstChild.firstChild.style.display='none';
				document.getElementById("attach").style.display="";
			</script>
		</c:if>
		<c:if test="${empClass eq 'Y'}">
		<script>
			document.forms.CessationDet.radioPensionNo.checked = true;
			document.forms.CessationDet.radioEligibleVRSYes.checked = true;
		</script>
		</c:if>
		
		</td>
	</tr>
</table>
</div>
</div>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>





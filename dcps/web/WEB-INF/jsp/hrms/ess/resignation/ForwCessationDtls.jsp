<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<link rel="stylesheet" type="text/css" href="common/css/tabcontent.css"/>
<script type="text/javascript" src="script/common/validation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>


<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="data" value="${resultValue.sub_inbox}" />
<c:set var="Requesttype" value="${resultValue.RequestType}" />
<c:set var="Id" value="${resultValue.reqid}" />
<c:set var="dateOfResig" value="${resultValue.DateOfResig}" />
<c:set var="servicePed" value="${resultValue.servicePeriod}" />
<c:set var="empInfo" value="${resultValue.map}" />
<c:set var="postname" value="${resultValue.map1}" />
<c:set var="fileId" value="${resultValue.fileId}"></c:set>
<c:set var="status" value="${resultValue.status}"></c:set>
<c:set var="var" value="${resultValue.var}"></c:set>
<c:set var="serverDate" value="${resultValue.serverDate}"></c:set>
<c:set var="noticePeriod" value="${resultValue.noticePeriod}"></c:set>
<c:set var="relDate2" value="${resultValue.relDate2}"></c:set>
<c:set var="ResignationName" value="${resultValue.ResignationName}"></c:set>
<c:set var="PensionPfAttach" value="${resultValue.PensionPfAttach}"></c:set>
<c:set var="mapOfLeaves" value="${resultValue.mapOfLeaves}"></c:set>
<c:set var="cancelFlag" value="${resultValue.cancelflag}"></c:set>
<c:set var="hrEssLoanMstList" value="${resultValue.hrEssLoanMstList}"></c:set>
<c:set var="loanAdvListSize" value="${resultValue.loanAdvListSize}"></c:set>

<fmt:setBundle basename="resources.ess.resignation.ResignationLabels" var="commonLables1" scope="request" />
<fmt:setBundle basename="resources.ess.resignation.CessationAlertLables" var="alertLables" scope="request" />
<script type="text/javascript">

function calReleaseDate()
{
	if(document.getElementById("days_waived").value!="")
	{
		if(document.getElementById("notice_period").value!="")
		{
			if(parseInt(document.getElementById("days_waived").value) > parseInt(document.getElementById("notice_period").value))
			{
				alert('<fmt:message  bundle="${alertLables}" key="EnterValiedWaivedOffDays"/>');
				document.getElementById("days_waived").value="";
				document.getElementById("days_waived").focus();
				return;
			}
		}
	}

	var daysWaived=0;
	var noticePd=0;
	if(document.getElementById("days_waived").value!="")
	{
		if(document.getElementById("days_waived").value.charAt(0)!=".")
		{
			 daysWaived =document.getElementById("days_waived").value;
		}
	}
	
	var applnDate =document.getElementById("applnDate").innerHTML;
	applnDate1=applnDate.split("/");
	if(document.getElementById("notice_period").value!="")
	{
		 noticePd =document.getElementById("notice_period").value;
	}
	
	var myDate=new Date();
	myDate.setFullYear(applnDate1[2],applnDate1[1]-1,applnDate1[0]);
	
	var myDated=new Date();
	var totalDays=parseInt(noticePd)-parseInt(daysWaived);
	var updatedDate=myDate.getDate()+parseInt(totalDays);
	
	myDated.setDate(updatedDate);

	var year=myDated.getFullYear();
	var month=myDated.getMonth()-0;
	var mon=parseInt(month)+parseInt(1);
	var days=myDated.getDate()-0;
	var releaseDate=parseInt(days)+"/"+parseInt(mon)+"/"+year;
	document.getElementById("ReleaseDate").value=releaseDate;
}

function showAttach()
{
		document.getElementById("day_amt").style.display="";
}

function hideAttach()
{
			
	document.getElementById("day_amt").style.display="none";
	var noticePd=0;
	var applnDate =document.getElementById("applnDate").innerHTML;
	applnDate1=applnDate.split("/");
	
	if(document.getElementById("notice_period").value!="")
	{
		 noticePd =document.getElementById("notice_period").value;
	}
	
	var myDate=new Date();
	myDate.setFullYear(applnDate1[2],applnDate1[1]-1,applnDate1[0]);
	var myDated=new Date();
	var totalDays=parseInt(noticePd);
	var updatedDate=myDate.getDate()+parseInt(totalDays);
	myDated.setDate(updatedDate);
	var year=myDated.getFullYear();
	var month=myDated.getMonth();
	var mon=parseInt(month)+parseInt(1);
	var days=myDated.getDate();
	var releaseDate=days+"/"+mon+"/"+year;
	document.getElementById("ReleaseDate").value=releaseDate;
	document.getElementById("days_waived").value="";
	document.getElementById("due_amount").value="";
}

function submitFormresignation2()
{
	if(parent.document.getElementById('reject').value == "no")
	{
		var validData=true;

		if ((document.forms.resignation2.txtYes[0].checked == false ) && ( document.forms.resignation2.txtYes[1].checked == false))
	    {
	        alert ( '<fmt:message  bundle="${alertLables}" key="IsWaivedNoticePed"/>' );
	        validData = false;
	        return;
	    }
	    
	    if (document.forms.resignation2.txtYes[0].checked==true)
		{
			if(""==document.forms.resignation2.days_waived.value.length||0==document.forms.resignation2.days_waived.value)
			{
				alert('<fmt:message  bundle="${alertLables}" key="NoDaysWaived"/>');
				validData = false;
		        return;
			}
			var noticePd =document.getElementById("notice_period").value;
			var DaysWaived=document.forms.resignation2.days_waived.value;
		
			if(parseInt(DaysWaived)>parseInt(noticePd))
			{
				alert('<fmt:message  bundle="${alertLables}" key="noOfDaysWaivedInvalid"/>');
				validData = false;
			    return;
			}
			var DaysWaivedLength=document.forms.resignation2.days_waived.value.length;
			var i=0;
			
			while ((i < DaysWaivedLength) && (DaysWaived.charAt(i) != "." && DaysWaived.charAt(i) != ":"))
			{
				i++
			}
			
			if ((i >= DaysWaivedLength) || (DaysWaived.charAt(i) != "." && DaysWaived.charAt(i) != ":"))
			{
				validData=true;
			}
			else
			{
				alert('<fmt:message  bundle="${alertLables}" key="NoDaysWaivedNotValidEntry"/>');
				validData=false;
				return;
			}	
			
			if(""==document.forms.resignation2.due_amount.value.length||0==document.forms.resignation2.due_amount.value)
			{
				alert('<fmt:message  bundle="${alertLables}" key="DuaAmount"/>');
				validData = false;
		        return;
			}
			
			var DueAmount=document.forms.resignation2.due_amount.value;
			var DueAmountLength=document.forms.resignation2.due_amount.value.length;
			var i=0;
			
			while ((i < DueAmountLength) && (DueAmount.charAt(i) != "." && DueAmount.charAt(i) != ":"))
			{
				i++
			}
			
			if ((i >= DueAmountLength) || (DueAmount.charAt(i) != "." && DueAmount.charAt(i) != ":"))
			{
				validData=true;
			}
			else
			{
				alert('<fmt:message  bundle="${alertLables}" key="DueAmtNotValidEntry"/>');
				validData=false;
				return;
			}	
		}	
		return true;
	}
	return true;
}

function resetForm()
{
  	if(confirm('<fmt:message  bundle="${alertLables}" key="ConfirmReset"/>') == true)
 	{
  		document.forms[0].reset();
 	}
}
	  
</script>

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>
				<hdiits:caption captionid="HRMS.CessationDetails" bundle="${commonLables1}" captionLang="single"/></b></a>
			</li>
		</ul>
	</div>
	
	<c:if test="${cancelFlag ne null}">
		<c:if test="${cancelFlag eq 'Y'}">
			<font color="red"><strong><center><fmt:message key="HRMS.RequestCancel" bundle="${commonLables1}"></fmt:message></center></strong></font>
		</c:if>
	</c:if>
	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
		<tr>
			<td>
				<hdiits:form name="resignation2" validate="true" method="POST" encType="multipart/form-data" action="./hrms.htm?actionFlag=forwardReqData">
				<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}"/>
				<hdiits:hidden name="status" id="status" default="${status}"/>
				<hdiits:hidden name="hiddenPara2" id="hiddenPara2" default="B"/>
				<hdiits:hidden name="hideVar" id="hideVar" default="${resultValue.hidden}"/>
				

				<div id="leave" name="leave">
				<table bgcolor="WHITE" class="tabtable" width="100%" height="100%">
				<br>
					
					<tr  height="3">
						<td class="fieldLabel" colspan="4">
							<font color="#ffffff">
								<hdiits:fieldGroup titleCaptionId="HRMS.EmpDetails" bundle="${commonLables1}" expandable="true"></hdiits:fieldGroup>
							</font>
						</td>
					</tr>

					<tr>
						<td colspan="1">
						<table border="0" width="100%">
							<tr >
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.EmpName" bundle="${commonLables1}" /> </td>
								<td align="left" width="25%"><c:out value="${empInfo.name}"></c:out></td>
	
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.EmpPost" bundle="${commonLables1}"/></td>

								<td align="left" width="25%"><c:out value="${postname.postNames}"></c:out></td>
							</tr>
							
							<tr>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.EmpDesig" bundle="${commonLables1}"/>  
								</td>
								
								<td align="left" width="25%"><c:out value="${postname.desigName}"></c:out>
								</td>
								
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.EmpDeptt" bundle="${commonLables1}"/> 
								</td>
								<td align="left" width="25%"><c:out value="${postname.departmentName}"></c:out>
								</td>
							</tr>
							
							<tr>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.EmpDoj" bundle="${commonLables1}"/> 
								</td>
								
								<td align="left" width="25%"><fmt:formatDate
									value="${empInfo.dateOfJoin}" pattern="dd/MM/yyyy" />
								</td>
	
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.EmpDor" bundle="${commonLables1}"/> 
								</td>
								
								<td align="left" width="25%"><fmt:formatDate
									value="${empInfo.dateOfExp}" pattern="dd/MM/yyyy" />
								</td>
								
								<td align="left" width="25%" id="appDate" style="display:none"><fmt:formatDate
									value="${resultValue.serverDate}" pattern="dd/MM/yyyy" />
								</td>
							</tr>
	
	
								
							<tr  height="3">
								<td class="fieldLabel" colspan="4">
									<font color="#ffffff">
										<hdiits:fieldGroup titleCaptionId="HRMS.Request" bundle="${commonLables1}" expandable="true"></hdiits:fieldGroup>
									</font>
								</td>
							</tr>
							
							<tr>
	
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.Type" bundle="${commonLables1}"/> 
								</td>
								
								<td align="left" width="25%"><c:out value="${Requesttype}"></c:out></td>
	
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.Date" bundle="${commonLables1}"/> 
								</td>
	
								<td align="left" width="25%" id="applnDate"><fmt:formatDate
									value="${data.applyingDate}" pattern="dd/MM/yyyy" />
								</td>
							</tr>
							
							<tr>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.Reasons" bundle="${commonLables1}"/>
								</td>
								
								<td><hdiits:textarea name="reason" id="reason" cols="40"  rows="10" readonly="true" default="${data.reason}"/></td>
								
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.RequestDate" bundle="${commonLables1}"/>
								</td>
								
								<td align="left"><fmt:formatDate
									value="${data.reqDateOfCessation}" pattern="dd/MM/yyyy" />
								</td>
							</tr>

							<tr  height="3" >
								<td class="fieldLabel" colspan="4" width="100%">
									<font color="#ffffff">
										<hdiits:fieldGroup titleCaptionId="HRMS.ServiceDetails" bundle="${commonLables1}" expandable="true"></hdiits:fieldGroup>
									</font>
								</td>
							</tr>
	
							<tr>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.SuspenDuration" bundle="${commonLables1}"/> 
								</td>
								
								<td align="left" width="25%"><c:out value="${mapOfLeaves.suspensiondur}"></c:out>
								</td>
	
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.Eol" bundle="${commonLables1}"/> 
								</td>
								
								<td align="left" width="25%"><c:out value="${mapOfLeaves.eol}"></c:out></td>
							</tr>
	
							<tr>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.Lwp" bundle="${commonLables1}"/> </td>
								<td align="left" width="25%"><c:out value="${mapOfLeaves.lwp}"></c:out></td>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.YearService" bundle="${commonLables1}"/></td>
								<td><c:out value="${servicePed}"></c:out></td>
							</tr>
						</table>
						</td>
					</tr>
					
					<tr>
						<td>
						<hdiits:fieldGroup  titleCaptionId="HRMS.AttachmentDetails" bundle="${commonLables1}" expandable="true" id="attachmentFieldGroup" collapseOnLoad="true">
						<table width="100%" align="center">
							<tr id="attach" >
								<td colspan="100%">
									<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
										<jsp:param name="attachmentName" value="ResignationName" />
										<jsp:param name="formName" value="resignation2" />
										<jsp:param name="attachmentType" value="Document" />
										<jsp:param name="attachmentTitle" value="NDC Attachment" />
										<jsp:param name="multiple" value="N" />
									</jsp:include>
								</td>
							</tr>
							
							<tr id="pensionattach" >
								<td colspan="100%">
									<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
										<jsp:param name="attachmentName" value="PensionPfAttach" />
										<jsp:param name="formName" value="resignation2" />
										<jsp:param name="attachmentType" value="Document" />
										<jsp:param name="attachmentTitle" value="Pension & Provident Fund Attachment" />
										<jsp:param name="multiple" value="N" />
									</jsp:include>
								</td>
							</tr>
						</table>
						</hdiits:fieldGroup>
						</td>
					</tr>
						
					<tr>
						<td>
						<table width="100%">
							<tr height="3">
								<td class="fieldLabel"  colspan="100%">
									<font color="#ffffff">
										<hdiits:fieldGroup titleCaptionId="HRMS.NoticePeriodDetails" bundle="${commonLables1}" expandable="true"></hdiits:fieldGroup>
									</font>	
								</td>
							</tr>

							<tr>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.NoticePeriod" bundle="${commonLables1}"/></td>
								<td><c:out value="${noticePeriod}"></c:out></td>
								<td align="left" width="25%"><hdiits:text name="notice_period" id="notice_period" style="display:none" maxlength="30" readonly="true" default="${noticePeriod}" /></td>
								<td align="left" width="25%"><hdiits:caption captionid="HRMS.DateResignation" bundle="${commonLables1}"/></td>
								<td align="left" width="25%"><fmt:formatDate value="${dateOfResig}" pattern="dd/MM/yyyy" /></td>
								<td style="display:none"><c:out value="${Id}"></c:out></td>
							</tr>
						</table>
						</td>
					</tr>
						
					<tr>
						<td>
						<table width="100%">
							<tr>
								<td>
									<hdiits:caption captionid="HRMS.WaivedNoticePeriod" bundle="${commonLables1}"/><label class='mandatoryindicator'>*</label>
								</td>

								<td width="25%">
									<hdiits:radio name="txtYes" id="txtYes" value="Y" errCaption="Radion Button 1" captionid="HRMS.Yes" bundle="${commonLables1}" onclick="showAttach()"/>
									<hdiits:radio name="txtYes" id="txtNO"  value="N" errCaption="Radion Button 1" captionid="HRMS.No" bundle="${commonLables1}" onclick="hideAttach()" />
								</td>
								<td width="25%"></td>
								<td width="25%"></td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td>
						<table border="0" width="100%">
							<tr id="day_amt" width="100%">
								<td width="25%" ><hdiits:caption captionid="HRMS.DaysWaived" bundle="${commonLables1}"/> </td>
								<td width="25%">
									<hdiits:number name="days_waived" mandatory="true" onblur="calReleaseDate()" id="days_waived" captionid="HRMS.DaysWaived" bundle="${commonLables1}" maxlength="5" />
								</td>

								<td width="25%"><hdiits:caption captionid="HRMS.DueAmount1" bundle="${commonLables1}"/></td>
								<td width="25%">
									<hdiits:number name="due_amount" mandatory="true" id="due_amount" captionid="HRMS.DueAmount1" bundle="${commonLables1}" maxlength="10" />
								</td>
							</tr>
							
							<tr width="100%" id="relDate">
								<td width="25%"><hdiits:caption captionid="HRMS.ReleaseDate" bundle="${commonLables1}"/>
								</td>
								
								<td width="25%">
									<hdiits:text name="ReleaseDate" readonly="true" id="ReleaseDateId" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" bundle="${commonLables1}" captionid="HRMS.ReleaseDate" caption="green" title="Date" />
								</td>
								
								<td width="25%"></td>
								<td width="25%"></td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td colspan="1">
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
								<td align="left"  ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
								<td align="left"  ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
								<td align="left" ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
								<td align="left" colspan="2" ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							</tr>

						<tr bgcolor="#386CB7">
							<td align="left" colspan="5" >
								<font color="#ffffff">
									<center><b><hdiits:caption captionid="HRMS.DueAmount" bundle="${commonLables1}"/></b></center>
								</font>
							</td>
						</tr>
							
						<tr bgcolor="#C6DEFF">
							<td align="left" >
								<hdiits:caption captionid="HRMS.DateAmount" bundle="${commonLables1}"/>
							</td>

							<td align="left" >
								<hdiits:caption captionid="HRMS.TotalAmount" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" >
								<hdiits:caption captionid="HRMS.LoanPaid" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" colspan="2" >
								<hdiits:caption captionid="HRMS.LoanDue" bundle="${commonLables1}"/>
							</td>
						</tr>
						
						<tr>
							<td align="left">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
							
							<td align="left">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
							
							<td align="left">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" colspan="2">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
						</tr>
						
						<tr bgcolor="#386CB7">
							<td align="left" colspan="5">
								<font color="#ffffff">
									<center> <b><hdiits:caption captionid="HRMS.CourtCase" bundle="${commonLables1}"/></b></center>
								</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">

							<td align="left" >
								<hdiits:caption captionid="HRMS.NoCourtCase" bundle="${commonLables1}"/>
							</td>

							<td align="left" >
								<hdiits:caption captionid="HRMS.Punishment" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" >	
								<hdiits:caption captionid="HRMS.StartDateCourt" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" colspan="2" >
								<hdiits:caption captionid="HRMS.Details" bundle="${commonLables1}"/>
							</td>
						</tr>
						
						<tr>
							<td align="left"><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							<td align="left"><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							<td align="left"><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							<td align="left" colspan="2" ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>
						
						<tr bgcolor="#386CB7">
							<td align="left" colspan="5">
								<font color="#ffffff"><center> <b>
									<hdiits:caption captionid="HRMS.Inventory" bundle="${commonLables1}"/></b></center>
								</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">
							<td align="left" >
								<hdiits:caption captionid="HRMS.AssetName" bundle="${commonLables1}"/>
							</td>

							<td align="left" >
								<hdiits:caption captionid="HRMS.AssetCode" bundle="${commonLables1}"/>
							</td>
								
							<td align="left" >
								<hdiits:caption captionid="HRMS.QuantityAssets" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" >
								<hdiits:caption captionid="HRMS.Vom" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" >
								<hdiits:caption captionid="HRMS.Remarks" bundle="${commonLables1}"/>
							</td>
						</tr>
						
						<tr>
							<td align="left">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
							
							<td align="left">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" >
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
							
							<td align="left" >
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
							
							<td align="left">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
							</td>
						</tr>

						<tr>
							<td align="left" colspan="5" style="display:none"><b>
								<hdiits:caption captionid="HRMS.AttachmentCertificate" bundle="${commonLables1}"/></b>
							</td>
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
					</td>
				</tr>
			</table>
			</div>
			
			<script type="text/javascript">
			
				{
					document.getElementById('target_uploadResignationName').style.display='none';
					document.getElementById('formTable1ResignationName').firstChild.firstChild.style.display='none';
					document.getElementById('target_uploadPensionPfAttach').style.display='none';
					document.getElementById('formTable1PensionPfAttach').firstChild.firstChild.style.display='none';
				}
				//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
				initializetabcontent("maintab")
			</script>
	
			<hdiits:jsField jsFunction="submitFormresignation2()" name="submitFormresignation2"/>
			<jsp:include page="../../../core/tabnavigation.jsp" />
			<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
			</hdiits:form>
		 
			 <c:if test="${data.waivedNoticePeriodFlag eq 'Y' }">
			  	<script>
					var flagYes =document.getElementById("txtYes");
					flagYes.value="${data.waivedNoticePeriodFlag}";
					var dueAmt =document.getElementById("due_amount");
					dueAmt.value="${data.dueAmount}";
					var days =document.getElementById("days_waived");
					days.value="${data.daysWaivedOff}";
					flagYes.checked="true";
				</script>
			</c:if> 
			
			<c:if test="${data.waivedNoticePeriodFlag eq 'N' }">
				<script>
					var flagNo =document.getElementById("txtNO");
					flagNo.value="${data.waivedNoticePeriodFlag}";
					flagNo.checked="true";
					document.getElementById("day_amt").style.display="none";
				</script>
			</c:if> 
			
			<c:if test="${data.releaseDate ne null }">
				<script>
					var daterel=document.getElementById("ReleaseDate");
					daterel.value="${relDate2}";
				</script>
			</c:if>
			
			<c:if test="${data.waivedNoticePeriodFlag eq 'Y' and data.reqApprovalFlag eq 'Y'}">
				<script>
					var flagYes =document.getElementById("txtYes");
					flagYes.value="${data.waivedNoticePeriodFlag}";
					var dueAmt =document.getElementById("due_amount");
					dueAmt.value="${data.dueAmount}";
					var days =document.getElementById("days_waived");
					days.value="${data.daysWaivedOff}";
					flagYes.checked="true";
				</script>
			</c:if> 
			
			<c:if test="${data.waivedNoticePeriodFlag eq 'N' and data.reqApprovalFlag eq 'Y'}">
				<script>
					var flagNo =document.getElementById("txtNO");
					flagNo.value="${data.waivedNoticePeriodFlag}";
					flagNo.checked="true";
					document.getElementById("day_amt").style.display="none";
				</script>
			</c:if> 
			
			<c:if test="${data.releaseDate ne null and data.reqApprovalFlag eq 'Y'}">
				<script>
					document.getElementById("ReleaseDate").value="${relDate2}";
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





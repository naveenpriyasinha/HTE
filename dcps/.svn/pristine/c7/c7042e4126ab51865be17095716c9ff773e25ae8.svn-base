
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
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>



<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />

<c:set var="GenEmpInfo" value="${resultValue.GenEmpInfo}" />
<c:set var="revId" value="${resultValue.revId}" />
<c:set var="reqdata" value="${resultValue.reqData}" />
<c:set var="fileId" value="${resultValue.fileId}" />
<c:set var="show" value="${resultValue.show}" />
<c:set var="show2" value="${resultValue.show2}" />
<c:set var="reviewAtt" value="${resultValue.reviewAtt}" />
<c:set var="empDeptDtlList" value="${resultValue.empDeptDtlList}" />
<c:set var="lstdptSize" value="${resultValue.lstdptSize}" />
<c:set var="str" value="${resultValue.str}" />
<c:set var="AdvAttatchment" value="${resultValue.AdvAttatchment}" />
<c:set var="stringAddress" value="${resultValue.stringAddress}" />
<c:set var="interviewDate" value="${resultValue.interviewDate}" />
<fmt:setBundle basename="resources.hr.review.MedicalReviewLabels"
	var="commonLables1" scope="request" />

<fmt:setBundle basename="resources.hr.review.MedicalReviewAlertLabels"
	var="alertLables" scope="request" />
<script type="text/javascript">



function show()
	{
		
		
		document.getElementById("conclusion").style.display="";
		document.getElementById("job_ret").style.display="";
	}

function hide()
	{
		
		
		document.getElementById("conclusion").style.display="none";
		document.getElementById("job_ret").style.display="none";
		
	}
	function showDate()
	{
		
		
		document.getElementById("dateTi").style.display="";
		
	}

	function hideDate()
	{
		document.getElementById("dateTi").style.display="none";
	}
	
	function validate_form()
	{
	
		if(parent.document.getElementById('reject').value == "no")
		{
				var validData=true;
		  <c:if test="${reqdata.isMedCheckReq eq null}">
		   if(document.forms.medicalRev.Checkup[0].checked == false && 
	   				document.forms.medicalRev.Checkup[1].checked == false)
	   				{
	   				alert('<fmt:message  bundle="${alertLables}" key="isMedCheckRequired"/>');
	   				validData=false;
	   				return;
	   				}
	   				if (document.forms.medicalRev.Checkup[1].checked == true)
	   				{
	   				if(document.forms.medicalRev.conc[0].checked == false && 
	   				document.forms.medicalRev.conc[1].checked == false)
	   				{
	   				alert('<fmt:message  bundle="${alertLables}" key="CheckConclusion"/>');
	   				validData=false;
	   				return;
	   				}
	   				}
	   				if (document.forms.medicalRev.Checkup[0].checked == true)
	   				{
	   				document.forms.medicalRev.conc[0].checked=false;
	   				document.forms.medicalRev.conc[1].checked=false;
	   				}
	   				
	   				 if(document.forms.medicalRev.verification[0].checked == false && 
	   				document.forms.medicalRev.verification[1].checked == false)
	   				{
	   				alert('<fmt:message  bundle="${alertLables}" key="isVerificationRequired"/>');
	   				validData=false;
	   				return;
	   				}
	   				if (document.forms.medicalRev.verification[0].checked == true)
	   				{
	   				if(""==document.forms.medicalRev.Date.value.length)
	   				{
	   				alert('<fmt:message  bundle="${alertLables}" key="dateForInterviewRequired"/>');
	   				validData=false;
	   				return;
	   				}
	   				}	   				
	   				</c:if>
	   				
	   				
	   				<c:if test="${reqdata.isMedCheckReq eq 'Y' and show2 ne null and reqdata.cmnAttachmentMst.attachmentId eq null}">
	  		
 				  var formTableReview=document.getElementById("myTablereviewAtt");
  		 if(formTableReview.rows.length==1)
			{
				alert('<fmt:message  bundle="${alertLables}" key="AttachmentIsreq"/>');
					validData = false;
       					 return;
			}
			if(formTableReview.rows.length>2)
			{
				alert('<fmt:message  bundle="${alertLables}" key="onlyOneAttachment"/>');
					validData = false;
       					 return;
			}
			</c:if>
			
			
			<c:if test="${reqdata.cmnAttachmentMst.attachmentId ne null and show2 eq null and reqdata.conclusion eq null}">
     if(document.forms.medicalRev.conc[0].checked == false && 
	   				document.forms.medicalRev.conc[1].checked == false)
	   				{
	   				alert('<fmt:message  bundle="${alertLables}" key="CheckConclusion"/>');
	   				validData=false;
	   				return;
	   				}
	   				</c:if>
	   				
	   				if (validData==true)
	   				{
	   				return true;
     		
	   				}
	   				
     		return true;
		
		}
   		return true;
   }
   
     		
    
	  
</script>

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
		captionid="EMPLOYEE.REVIEW" bundle="${commonLables1}" captionLang="single"/></b></a></li>
</ul>
</div>
<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent">
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
	<tr>
		<td>

<hdiits:form name="medicalRev" validate="true" method="POST" encType="multipart/form-data" action = "hrms.htm?actionFlag=returnReviewRequest">


	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}"/>
	<hdiits:hidden name="trk" id="trk" default="${reqdata.trk}"/>
	<hdiits:hidden name="str" id="str" default="${str}"/>
			
	<div id="leave">
	<table  class="tabtable" >

		<tr>
		<td colspan="1">
			<table width="100%" height="50%">
				<tr >
					<td colspan="4"><font> <strong><u>
					 <hdiits:fieldGroup titleCaptionId="EMP.DETAILS" bundle="${commonLables1}"></hdiits:fieldGroup>
					</u></strong> </font></td>
				</tr>
			</table>
		</td>
		</tr>

		<tr>
		<td colspan="1">
			<table border="0" width="100%" height="25%">
				<tr>
					<td align="left"><hdiits:caption captionid="HRMS.EmpName" bundle="${commonLables1}"/></td>
					<td align="left"><c:out value="${GenEmpInfo.name}"></c:out></td>
					<td align="left"><hdiits:caption captionid="HRMS.Address" bundle="${commonLables1}"/></td>
					<td align="left"  >
						<c:if test="${stringAddress ne null}">
						<c:out  value="${stringAddress}"></c:out></c:if>
						
						<c:if test="${stringAddress eq null or stringAddress eq ''}">
						<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
						
						</c:if>
					</td>
				</tr>
				<tr>
					<td align="left"><hdiits:caption captionid="HRMS.EmpDesig" bundle="${commonLables1}"/></td>
					<td align="left"  >
						<c:if test="${GenEmpInfo.designation ne null}">
							<c:out  value="${GenEmpInfo.designation}"></c:out>
						</c:if>
						<c:if test="${GenEmpInfo.designation eq null}">
							<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
						</c:if>
					</td>
				
					<td align="left"><hdiits:caption captionid="HRMS.BasicSalary" bundle="${commonLables1}"/></td>
					<td align="left"  >
						<c:if test="${GenEmpInfo.salary ne null}">
							<c:out  value="${GenEmpInfo.salary}"></c:out>
						</c:if>
						<c:if test="${GenEmpInfo.salary eq null}">
							<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
						</c:if>
					</td>
				<tr>
					<td align="left"><hdiits:caption captionid="HRMS.EmpDoj" bundle="${commonLables1}"/></td>
					<td align="left"  >
						<c:if test="${GenEmpInfo.dateOfJoin ne null}">
							<fmt:formatDate	value="${GenEmpInfo.dateOfJoin}" pattern="dd/MM/yyyy" />
						</c:if>
						
						<c:if test="${GenEmpInfo.dateOfJoin eq null}">
							<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
						</c:if>
					</td>
				
					<td align="left"><hdiits:caption captionid="HRMS.EmpDor" bundle="${commonLables1}"/></td>
					<td align="left" >
						<c:if test="${GenEmpInfo.dateOfExp ne null}">
							<fmt:formatDate	value="${GenEmpInfo.dateOfExp}" pattern="dd/MM/yyyy" />
						</c:if>
						<c:if test="${GenEmpInfo.dateOfExp eq null}">
							<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
						</c:if>
					</td>
				</tr>
			</table>
		</td>
		</tr>
		
		<tr>
		<td colspan="1">
		
			<table width="100%" height="10%">
				<tr>
				<td colspan="1">
					<table width="100%" height="50%">
						<tr >
							<td colspan="4"><font> <strong><u>
							 <hdiits:fieldGroup titleCaptionId="VERIFICATION.DETAIL" bundle="${commonLables1}"></hdiits:fieldGroup>
							</u></strong> </font></td>
						</tr>
					</table>
				</td>
				</tr>
				
				<tr>
				<td colspan="1">
					<table  width="100%" align="center"  >
						<tr>
							<td align="left" colspan="5">
							<font >
								 <hdiits:fieldGroup titleCaptionId="HRMS.DepartmentalInquiry" bundle="${commonLables1}"></hdiits:fieldGroup>
							</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">
							<td align="left" ><hdiits:caption captionid="HRMS.NoDepartment" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.Punishment" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.StartDateDept" bundle="${commonLables1}"/></td>
							<td align="left" colspan="2" ><hdiits:caption captionid="HRMS.Details" bundle="${commonLables1}"/></td>
						</tr>
						
						<c:if test="${lstdptSize eq 'Y'}">
						<tr>
							<td align="left" colspan="5"  ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>	
						</c:if>	
							
						<c:forEach var="ItrList" items="${empDeptDtlList}">
						<tr id="empDeptDtl">
						<td align="center"  >
							<c:if test="${ItrList.departmentalInqNo ne null}">
								<c:out  value="${ItrList.departmentalInqNo}"></c:out>
							</c:if>
							<c:if test="${ ItrList.departmentalInqNo eq null}">
								<c:out value="N/A"></c:out>
							</c:if>
						</td>
								
						<td align="center" >
							<c:if test="${ItrList.punishment!=''}">
								<c:out value="${ItrList.punishment}"></c:out>
							</c:if>
							<c:if test="${ItrList.punishment=='' or ItrList.punishment eq null }">
								<c:out value="N/A"></c:out>
							</c:if>
						</td>
								
						<td align="center" >
							<c:if test="${ItrList.deptStartDate ne null }">
								<fmt:formatDate	value="${ItrList.deptStartDate}" pattern="dd/MM/yyyy" />
							</c:if>
									
							<c:if test="${ItrList.deptStartDate eq null }">
								<c:out value="N/A"></c:out>
							</c:if>
						</td>
							
						<td align="center"  colspan="2">
							<c:if test="${ItrList.details ne null }">
								<c:out value="${ItrList.details}"></c:out>
							</c:if>
							<c:if test="${ItrList.details eq null }">
								<c:out value="N/A"></c:out>
							</c:if>
						</td>
						</tr>
						</c:forEach>
							
						<tr>
							<td align="left" colspan="5">
							<font >
								 <hdiits:fieldGroup titleCaptionId="HRMS.DueAmount" bundle="${commonLables1}"></hdiits:fieldGroup>
							</font>
							</td>
						</tr>
						
						<tr bgcolor="#C6DEFF">
							<td align="left" ><hdiits:caption captionid="HRMS.DateAmount" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.TotalAmount" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.LoanPaid" bundle="${commonLables1}"/></td>
							<td align="left" colspan="2" ><hdiits:caption captionid="HRMS.LoanDue" bundle="${commonLables1}"/></td>
						</tr>
						<tr>
							<td align="left" colspan="5"  ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>

						<tr>
							<td align="left" colspan="5">
							<font>
								 <hdiits:fieldGroup titleCaptionId="HRMS.CourtCase" bundle="${commonLables1}"></hdiits:fieldGroup>
							</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">
							<td align="left" ><hdiits:caption captionid="HRMS.NoCourtCase" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.Punishment" bundle="${commonLables1}"/></td>
							<td align="left"><hdiits:caption captionid="HRMS.StartDateCourt" bundle="${commonLables1}"/></td>
							<td align="left" colspan="2" ><hdiits:caption captionid="HRMS.Details" bundle="${commonLables1}"/></td>
						</tr>
						
						<tr>
							<td align="left" colspan="5"><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>

						<tr>
							<td align="left" colspan="5">
							<font>
								 <hdiits:fieldGroup titleCaptionId="HRMS.Inventory" bundle="${commonLables1}"></hdiits:fieldGroup>
							</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">
							<td align="left" ><hdiits:caption captionid="HRMS.AssetName" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.NoCourtCase" bundle="${commonLables1}"/></td>
							<td align="left"><hdiits:caption captionid="HRMS.AssetCode" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.QuantityAssets" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.Remarks" bundle="${commonLables1}"/></td>
						</tr>
						<tr>
							<td align="left"  colspan="5" ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>
						<tr>
							<td align="left" colspan="5" style="display:none"><b><hdiits:caption captionid="HRMS.AttachmentCertificate" bundle="${commonLables1}"/></b></td>
						</tr>
					</table>
				</td>
				</tr>

				<tr>
				<td colspan="1">
					<table width="100%" height="10%">
					<tr bgcolor="#386CB7">
						<td class="acd" colspan="4">
							<font color="#ffffff"></font>
						</td>
					</tr>
					</table>
				</td>
				</tr>
		
				
				<tr>
				<td colspan="1">
					<table width="100%" height="25%">
						<tr id="radiobtn" >
						<td align="left" width="25%"><hdiits:caption captionid="MEDICAL.CHECKUP" bundle="${commonLables1}"/><label class='mandatoryindicator'>*</label></td>
						<c:if test="${reqdata.isMedCheckReq eq null}">
							<td id="checkup" width="25%">
								<hdiits:radio name="Checkup" id="CheckUpYes" value="Y" onclick="hide()" captionid="HRMS.Yes" bundle="${commonLables1}" />
			         			<hdiits:radio name="Checkup" id="CheckUpNo" value="N" onclick="show()"  captionid="HRMS.No" bundle="${commonLables1}"/>
							</td>
						</c:if>
							
						<c:if test="${reqdata.isMedCheckReq eq 'Y'}">
							<td align="left" id="isMedPopulatedYes"><hdiits:caption captionid="HRMS.Yes" bundle="${commonLables1}"/></td>
						</c:if>
						<c:if test="${reqdata.isMedCheckReq eq 'N'}">
							<td align="left" id="isMedPopulatedYes"><hdiits:caption captionid="HRMS.No" bundle="${commonLables1}"/></td>
						</c:if>
						</tr>
					</table>
				</td>
				</tr>
		
				<tr>
				<td colspan="1">
					<table width="100%" height="10%">
					<tr  id="conclusion" style="display:none">
						<td colspan="4" >
							<hdiits:fieldGroup titleCaptionId="CONCLUSION" bundle="${commonLables1}"></hdiits:fieldGroup>
						</td>
					</tr>
					</table>
				</td>
				</tr>
		
		
				<tr id="job_ret" style="display:none">
				<td colspan="1">
				<table height="25%" width="100%">
		 			<c:if test="${reqdata.conclusion eq null}">
						<tr>
							<td width="25%"><fmt:message key="SUITABLE.JOB" bundle="${commonLables1}"/></td>
							<td width="25%">
								<hdiits:radio name="conc"  id="continue" value="C" errCaption="Check BOX 1" captionid="chk_box" />
							</td>
						</tr>
						<tr>
							<td width="25%"><fmt:message key="PREMATURE.RETIREMENT" bundle="${commonLables1}"/></td>
						  	<td width="25%">
								<hdiits:radio name="conc"  id="retire" value="R" errCaption="Check BOX 1" captionid="chk_box" />
							</td>
						</tr>
					</c:if>
						   
				   	<c:if test="${reqdata.conclusion eq 'R'}">
					   <tr>
							<td align="left" id="conPopulated"><hdiits:caption captionid="PREMATURE.RETIREMENT" bundle="${commonLables1}"/></td>
					   </tr>
				  	</c:if>
						   
				   	<c:if test="${reqdata.conclusion eq 'C'}">
					   <tr>
							<td align="left" id="retPopulated"><hdiits:caption captionid="SUITABLE.JOB" bundle="${commonLables1}"/></td>
				   	   </tr>
				   	</c:if>
	     		</table>
				</td>
				</tr>
				
				<tr>
				<td colspan="1">
				<table width="100%" height="25%">
					<c:if test="${reqdata.isMedCheckReq eq null}">
						<tr id="interview" >
							<td align="left" width="25%" >
								<hdiits:caption captionid="INVITE.VERIFICATION" bundle="${commonLables1}"/>
							</td>
							<td id="verificationInterview" width="25%">
								<hdiits:radio name="verification" id="verificationYes" onclick="showDate()" value="Y" captionid="HRMS.Yes" bundle="${commonLables1}"/>
         						<hdiits:radio name="verification" id="verificationNo" onclick="hideDate()" value="N" captionid="HRMS.No" bundle="${commonLables1}"/>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${reqdata.isMedCheckReq eq null}">
					<tr id="dateTi">
						<td align="left"><hdiits:caption captionid="HRMS.interviewDate" bundle="${commonLables1}"/></td>
						<td align="left" id="interviewDate"><hdiits:dateTime name="Date" maxvalue="31/12/2099" captionid="HRMS.interviewDate" bundle="${commonLables1}"  /></td>
					</tr>
					</c:if>
					
					<c:if test="${interviewDate ne null}">
					<tr>
						<td align="left" width="25%"><hdiits:caption captionid="HRMS.interviewDate" bundle="${commonLables1}"/></td>
						<td align="left" id="interDate" ><fmt:formatDate value="${interviewDate}" pattern="dd/MM/yyyy" /></td>
					</tr>
					</c:if>
				</table>
				</td>
				</tr>
			</table>
		<tr>
		<td>
			<table width="100%">
				<tr id="attach" style="display:none">
					<td colspan="100%">
					<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="reviewAtt" />
					<jsp:param name="formName" value="medicalRev" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="multiple" value="N" />
					</jsp:include>
					</td>
				</tr>
			</table>
		</td>
		</tr>

	</div>
	<script type="text/javascript">
	
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
	
	
	<hdiits:jsField jsFunction='validate_form()' name="validate_form"/>
	<jsp:include  page="../../../core/tabnavigation.jsp"  />
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/> 


</hdiits:form>




<c:if test="${reqdata.isMedCheckReq  eq null }">

	<script>

	var conclsn=document.getElementById("conclusion");
	conclsn.style.display="";
	var RetirOrCon=document.getElementById("job_ret");
  
	RetirOrCon.style.display="";
	
	</script>
</c:if>

	
<c:if test="${show ne null  and reqdata.isMedCheckReq ne null and reqdata.cmnAttachmentMst.attachmentId ne null }">

	<script>
	

	var RetirOrCon=document.getElementById("job_ret");
	var conc=document.getElementById("conclusion");
	var attach=document.getElementById("attach");
	conc.style.display="";
	RetirOrCon.style.display="";

	
	</script>
</c:if>



<c:if test="${reqdata.isMedCheckReq ne null and reqdata.cmnAttachmentMst.attachmentId eq null }">

	<script>
	

	var concln=document.getElementById("conclusion");
  	RetirOrCon=document.getElementById("job_ret");
	concln.style.display="none";
	RetirOrCon.style.display="none"
	
	</script>
</c:if>

<c:if test="${(reqdata.trk eq 'forw2'or reqdata.trk eq 'app')   and reqdata.cmnAttachmentMst.attachmentId ne null}">
<script>
document.getElementById('target_uploadreviewAtt').style.display='none';
document.getElementById('formTable1reviewAtt').firstChild.firstChild.style.display='none';
</script>
</c:if>

<c:if test="${(show2 ne null and reqdata.isMedCheckReq eq 'Y')or reqdata.cmnAttachmentMst.attachmentId ne null}">

	<script>

  
   var  attachment=document.getElementById("attach");

	attachment.style.display="";
	
	
	</script>
</c:if>

<c:if test="${(show ne null or show2 ne null)and reqdata.cmnAttachmentMst.attachmentId ne null}">

	<script>
	

   
   var  radiobtn=document.getElementById("radiobtn");

	radiobtn.style.display="none";
	
	
	</script>
</c:if>

</td>
	</tr>
</table>
</div>
</div>
<script type="text/javascript">
	initializetabcontent("maintab")
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>





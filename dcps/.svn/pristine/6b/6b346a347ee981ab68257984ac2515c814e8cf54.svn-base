
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
<c:set var="fileId" value="${resultValue.fileId}" />
<c:set var="empDeptDtlList" value="${resultValue.empDeptDtlList}" />
<c:set var="lstdptSize" value="${resultValue.lstdptSize}" />
<c:set var="generalInfo" value="${resultValue.empGenInfo}"></c:set>
<c:set var="stringAddress" value="${resultValue.stringAddress}"></c:set>

<fmt:setBundle basename="resources.hr.review.MedicalReviewLabels"
	var="commonLables1" scope="request" />






<script type="text/javascript">



function onSubmit(){
window.document.MedicalRev2.submit();
 var submit=document.getElementById("submitForm");
     		submit.disabled=true;
}
function closeWindow()
			  {
			  	
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.MedicalRev2.action=urlstyle;
		document.MedicalRev2.submit();
			  				  	
			  } 
  
</script>

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
		captionid="EMPLOYEE.REVIEW" bundle="${commonLables1}" captionLang="single"/></b></a></li>
</ul>
</div>
<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
	<tr>
		<td><hdiits:form name="MedicalRev2" validate="true" method="POST"
			encType="multipart/form-data" action = "hrms.htm?actionFlag=forwardReviewReq">
			
			
			<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}"/>
			<hdiits:hidden name="hiddenPara2" id="hiddenPara2" default="t"/>
			<div id="leave" name="leave">
			<table width="100%">

				<tr>
					<td colspan="1">
					<table width="100%" height="50%">
						<tr >
							<td colspan="4"><font> <strong><u>
							 <hdiits:fieldGroup titleCaptionId="HRMS.EmpDetails" bundle="${commonLables1}"></hdiits:fieldGroup>
							</u></strong> </font></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="1">
					<table border="0" width="100%" height="25%">
						<tr>
							<td align="left"><hdiits:caption captionid="HRMS.EmpName" bundle="${commonLables1}"/> </td>
							<td align="left"><c:out value="${generalInfo.name}"></c:out></td>


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
						
						
							<td align="left"> <hdiits:caption captionid="HRMS.EmpDesig" bundle="${commonLables1}"/></td>
								
								<td align="left"  >
								<c:if test="${generalInfo.designation ne null}">
								<c:out  value="${generalInfo.designation}"></c:out></c:if>
								
								<c:if test="${generalInfo.designation eq null}">
									<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
								</c:if>
							</td>
							

							<td align="left"><hdiits:caption captionid="HRMS.BasicSalary" bundle="${commonLables1}"/></td>
								
								<td align="left"  >
								<c:if test="${generalInfo.salary ne null}">
								<c:out  value="${generalInfo.salary}"></c:out></c:if>
								
								<c:if test="${generalInfo.salary eq null}">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
								
								</c:if></td>
							
							
							<td align="left"  style="display:none"><hdiits:text name="userId" 

default="${generalInfo.userId}" />
					</td>
						</tr>
						<tr>
							<td align="left"><hdiits:caption captionid="HRMS.EmpDoj" bundle="${commonLables1}"/>
							</td>
								
								<td align="left"  >
								<c:if test="${generalInfo.dateOfJoin ne null}">
								<fmt:formatDate
								value="${generalInfo.dateOfJoin}" pattern="dd/MM/yyyy" /></c:if>
								
								<c:if test="${ generalInfo.dateOfJoin eq null}">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
								
								</c:if></td>
							

							<td align="left"><hdiits:caption captionid="HRMS.EmpDor" bundle="${commonLables1}"/></td>
								
								
								<td align="left"  >
								<c:if test="${generalInfo.dateOfExp ne null}">
								<fmt:formatDate
								value="${generalInfo.dateOfExp}" pattern="dd/MM/yyyy" /></c:if>
								
								<c:if test="${ generalInfo.dateOfExp eq null}">
								<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
								</c:if></td>
							
								
						</tr>


					</table>
					</td>
				</tr>
				<tr>
					<td colspan="1">
					<table width="100%" height="10%">

						<tr>
							<td colspan="4">
								<font> 
									<hdiits:fieldGroup titleCaptionId="VERIFICATION.DETAIL" bundle="${commonLables1}"></hdiits:fieldGroup>
								</font>
							</td>
						</tr></table></td></tr>
						<tr>
					<td colspan="1">
					<table  width="100%" align="center"  >
						<tr>
							<td align="left" colspan="5">
								<font>
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
								<td align="left"   colspan="5"><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
							</tr>	
						</c:if>		
						<c:forEach var="ItrList" items="${empDeptDtlList}">
							
							<tr id="empDeptDtl">
							<td align="center"  >
								<c:if test="${ItrList.departmentalInqNo ne null}">
								<c:out  value="${ItrList.departmentalInqNo}"></c:out></c:if>
								
								<c:if test="${ ItrList.departmentalInqNo eq null}">
								<c:out value="N/A"></c:out>
								</c:if></td>
								
								<td align="center" >
								<c:if test="${ItrList.punishment!=''}">
								<c:out value="${ItrList.punishment}"></c:out></c:if>

								
								<c:if test="${ItrList.punishment=='' or ItrList.punishment eq null }">
							<c:out value="N/A"></c:out></c:if></td>
								
								<td align="center" >
								
								
								<c:if test="${ItrList.deptStartDate ne null }">
								<fmt:formatDate 
									value="${ItrList.deptStartDate}" pattern="dd/MM/yyyy" /></c:if>
									
									
								<c:if test="${ItrList.deptStartDate eq null }">
								<c:out value="N/A"></c:out></c:if>
									
									</td>
								
								
							
								<td align="center"  colspan="2">
								<c:if test="${ItrList.details ne null }">
								<c:out   value="${ItrList.details}"></c:out></c:if>
								
								<c:if test="${ItrList.details eq null }">
								<c:out value="N/A"></c:out></c:if>
								</td>
								
								
								</tr>
							

							
							
						</c:forEach>
							
						<tr>
							<td align="left" colspan="5">
								<font>
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
							<td align="left"  colspan="5"  ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
						</tr>



						<tr>
							<td align="left" colspan="5">
								<font>
									<hdiits:fieldGroup titleCaptionId="HRMS.CourtCase" bundle="${commonLables1}"></hdiits:fieldGroup>
								</font>
							</td>
						</tr>

						<tr bgcolor="#C6DEFF">
							<td align="left"><hdiits:caption captionid="HRMS.NoCourtCase" bundle="${commonLables1}"/></td>
							<td align="left"><hdiits:caption captionid="HRMS.Punishment" bundle="${commonLables1}"/></td>
							<td align="left"><hdiits:caption captionid="HRMS.StartDateCourt" bundle="${commonLables1}"/></td>
							<td align="left" colspan="2" ><hdiits:caption captionid="HRMS.Details" bundle="${commonLables1}"/></td>
						</tr>
						<tr>
							<td align="left"  colspan="5" ><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></td>
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

							<td align="left" ><hdiits:caption captionid="HRMS.AssetCode" bundle="${commonLables1}"/></td>
							<td align="left"><hdiits:caption captionid="HRMS.QuantityAssets" bundle="${commonLables1}"/></td>
							<td align="left" ><hdiits:caption captionid="HRMS.Vom" bundle="${commonLables1}"/></td>
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
							<td class="acd" colspan="4"><font color="#ffffff"> </font></td>
						</tr>
					</table>
					</td>
				</tr>



			</table>



			</div>
			<br><br>
			<c:if test="${fileId eq null}">
			<table align="center" width="100%">
			<tr>
			<td align="center">
			<hdiits:button name="Submit" type="button" caption="Submit" id="submitForm"
				onclick="onSubmit()" />
			<hdiits:button value="Close" name="Close" type="button"
				onclick="closeWindow()" /></td></tr></table></c:if>
			<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
			<hdiits:validate controlNames="tesxt"
				locale='<%=(String)session.getAttribute("locale")%>' />


		</hdiits:form></td>
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





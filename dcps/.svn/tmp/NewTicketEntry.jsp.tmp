<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstScreen" value="${resValue.lLstScreen}"></c:set>
<c:set var="lLstStatus" value="${resValue.lLstStatus}"></c:set>
<%--<c:set var="lLstPriority" value="${resValue.lLstPriority}"></c:set>--%>
<c:set var="lLstSeverity" value="${resValue.lLstSeverity}"></c:set>
<c:set var="lLstRootCause" value="${resValue.lLstRootCause}"></c:set>
<c:set var="orgTicketMst" value="${resValue.orgTicketMst}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="filterStatus" value="${resValue.filterStatus}"></c:set>
<c:set var="lStrUser" value="${resValue.lStrUser}"></c:set>
<c:set var="strRemarks" value="${resValue.strRemarks}"></c:set>


<%--for file upload --%>  
<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page"
		value="style='display: none;' "></c:set>
</c:if>

<input type="hidden" name="hidPhotoUrl" id="hidPhotoUrl">
<input type="hidden" name="hidSignUrl" id="hidSignUrl">
<input type="hidden" name="attachmentName1" id="attachmentName1">

<c:if test="${resValue.flag == 'Update'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varReadonly" scope="page" value="readonly='readonly'"></c:set>
</c:if>
<style type="text/css">
fieldset.tabstyle input:not([type='button']), fieldset.tabstyle select, fieldset.tabstyle textarea {
    width: 75% !important;
}
</style>
<script type='text/javascript'>
document.getElementById("CharCountLabel1").innerHTML ="<font color='red'>Characters left: 20</font>";
function CharacterCount(TextArea,FieldToCount,maxCount)
{
	var txt = document.getElementById(TextArea).value;
	//alert(txt); 
	if((txt.length < (maxCount+1)) )
	{
		document.getElementById(FieldToCount).innerHTML = "<font color='red'>Characters left: "+(maxCount - txt.length)+"</font>";
		
	}
	else
	{
		document.getElementById(TextArea).value=txt.substring(0,maxCount);
	}
}
 

</script>
<script>
function validateContactNo(){

	contactNo = document.getElementById("ticketContactNo").value;
	if(contactNo.length!='10'){

		alert('Mobile number should be of 10 digits.');
		document.getElementById("ticketContactNo").value='';
		return false;
	}
	if(contactNo.charAt(0)!='7' && contactNo.charAt(0)!='8' && contactNo.charAt(0)!='9'){
		alert('Please enter a valid Mobile Number');
		document.getElementById("ticketContactNo").value='';
		return false;
		
	}
	else
		return true;
}
</script>
<script>
function submitNewEntry(){

	//alert("Inside Submit");
	if(document.getElementById("ticketTitle").value=="" || document.getElementById("ticketTitle").value==null)
	{
		alert("Ticket Title cannot be empty");
		return false;
	}
	if(document.getElementById("selScreen").value=="")
	{
		alert("Please fill Screen details");
		return false;
	}
	if(document.getElementById("ticketDesc").value=="" || document.getElementById("ticketDesc").value==null)
	{
		alert("Ticket Description cannot be empty");
		return false;
	}
	if(document.getElementById("ticketCred").value=="" || document.getElementById("ticketCred").value==null)
	{
		alert("Credentials cannot be empty");
		return false;
	}
	if(document.getElementById("ticketContactNo").value=="" || document.getElementById("ticketContactNo").value==null)
	{
		alert("Contact No cannot be empty");
		return false;
	}
	
	if(document.getElementById("ticketEmailId").value=="" || document.getElementById("ticketEmailId").value==null)
	{
		alert("Email Id cannot be empty");
		document.getElementById("ticketEmailId").focus();
		return false;
	}
	
	var User = document.getElementById("hdnUser").value;					
	var url="ifms.htm?actionFlag=saveNewEntry&flag=Add&lStrUser="+User;
	//alert('URL is: '+url);
	
	document.TicketForm.action = url;
	document.TicketForm.submit();
	
	//showProgressbar('Please wait,Your Request is in progress');
	alert('Your Ticket is submitted');
	///self.location.href = "ifms.htm?actionFlag=loadPRTracker&Flag=U";

	

}

function backToLoad(){

	var User = document.getElementById("hdnUser").value;
	var s = document.getElementById("filterStatus").value;
	//alert(s);
	if(User=='U')
	{
		var a = "ifms.htm?actionFlag=loadPRTracker&Flag=U";
		self.location.href = a;
	}
	else
	{
		var s = document.getElementById("filterStatus").value;
		//alert(s);
		if(s!=-1)
		{
		var b = "ifms.htm?actionFlag=loadPRTracker&Flag="+User+"&status="+s;
		self.location.href = b;
		}
		else
		{
			var c = "ifms.htm?actionFlag=loadPRTracker&Flag="+User;
			self.location.href = c;
		}
	}
}
</script>
<script>
function updateEntry(flagC){

	//alert(flagC);
	var tid;
	var answer="";
	tid=${orgTicketMst.ticketId};
	var User = document.getElementById("hdnUser").value;
	var url;
	//alert(User);
	var remarks = document.getElementById("ticketRemarks").value;
	//alert(remarks);
	if(User == 'U')
	{
		//alert("hello:1");
		var status=${orgTicketMst.status};
		//var priority=${orgTicketMst.priority};
		var severity=${orgTicketMst.severity};
		var rootCause=${orgTicketMst.rootCause};
		if(flagC == 'C'){
		answer = confirm ("Are you sure you want to close this Ticket?");
		if (answer){
		//url="ifms.htm?actionFlag=saveNewEntry&flag=Update&ticketId="+tid+"&lStrUser="+User+"&selStatus="+status+" &selPriority="+priority+"  &ticketRemarks="+remarks+"&selSeverity="+severity+"&selRootCause="+rootCause+"&flagC="+flagC;
		url="ifms.htm?actionFlag=saveNewEntry&flag=Update&ticketId="+tid+"&lStrUser="+User+"&selStatus="+status+" &ticketRemarks="+remarks+"&selSeverity="+severity+"&selRootCause="+rootCause+"&flagC="+flagC;
		document.forms[0].action = url;      
		document.forms[0].submit();
		showProgressbar('Please wait,Your Request is in progress');
		alert('Ticket is Closed');
		
		/* var a = "ifms.htm?actionFlag=loadPRTracker&Flag="+User;
		self.location.href = a; */
		}
		}
		else
		{
			//url="ifms.htm?actionFlag=saveNewEntry&flag=Update&ticketId="+tid+"&lStrUser="+User+"&selStatus="+status+" &selPriority="+priority+" &ticketRemarks="+remarks+"&selSeverity="+severity+"&selRootCause="+rootCause+"&flagC="+flagC;
			url="ifms.htm?actionFlag=saveNewEntry&flag=Update&ticketId="+tid+"&lStrUser="+User+"&selStatus="+status+" &ticketRemarks="+remarks+"&selSeverity="+severity+"&selRootCause="+rootCause+"&flagC="+flagC;
			document.forms[0].action = url;
			document.forms[0].submit();
			showProgressbar('Please wait,Your Request is in progress');
			alert('Ticket is Updated');
			
			/* var a = "ifms.htm?actionFlag=loadPRTracker&Flag="+User;
			self.location.href = a; */
		}
	}
	else
	{
		//alert("hello:1");
		var saveStatus = document.getElementById("selStatus").value;
		//var savePriority = document.getElementById("selPriority").value;
		var saveSeverity = document.getElementById("selSeverity").value;
		var saveRootCause = document.getElementById("selRootCause").value;
		//var filterStatus = document.getElementById("filterStatus").value;
		//url="ifms.htm?actionFlag=saveNewEntry&flag=Update&ticketId="+tid+"&lStrUser="+User+"&selStatus="+saveStatus+" &selPriority="+savePriority+"&ticketRemarks="+remarks+"&selSeverity="+saveSeverity+"&selRootCause="+saveRootCause;
		url="ifms.htm?actionFlag=saveNewEntry&flag=Update&ticketId="+tid+"&lStrUser="+User+"&selStatus="+saveStatus+"  &ticketRemarks="+remarks+"&selSeverity="+saveSeverity+"&selRootCause="+saveRootCause;
		document.forms[0].method = 'post';
		document.forms[0].action = url;
		document.forms[0].submit();
		showProgressbar('Please wait,Your Request is in progress');
		alert('Ticket is Updated');
		
		/* var b = "ifms.htm?actionFlag=loadPRTracker&Flag="+User+"&status="+filterStatus;
		self.location.href = b; */
		}
	
	
	
}

</script>
<hdiits:form name="TicketForm" id="TicketForm"
	encType="multipart/form-data" validate="true" method="post">


	<fieldset class="tabstyle"><legend> <b>Raise
	New Ticket</b> </legend>
	<div>
	<table width="80%" border="0">
		<tr>
			<td width="5%" align="left"><fmt:message key="CMN.TICKETID"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="ticketId"
				name="ticketId" size="40"
				${varDisabled} value="${orgTicketMst.ticketId}" readonly="readonly"></td>
		</tr>
		<tr>
			<td width="5%" align="left"><fmt:message key="CMN.TICKETITLE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="ticketTitle"
				name="ticketTitle" size="40"
				${varDisabled} value="${orgTicketMst.ticketTitle}"></td>


			<td width="5%" align="left"><fmt:message key="CMN.TICKETSCREEN"
				bundle="${dcpsLables}"></fmt:message></td>


			<td align="left" width="20%"><select name="selScreen"
				id="selScreen" ${varDisabled} style="width: 305px">
				<option value="">-------Selected--------</option>
				<c:forEach items="${lLstScreen}" var="lLstScreen">

					<c:choose>
						<c:when test="${orgTicketMst!=null}">
							<c:choose>
								<c:when test="${orgTicketMst.screenId == lLstScreen[0]}">
									<option value="${lLstScreen[0]}" selected="selected"><c:out
										value="${lLstScreen[1]}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${lLstScreen[0]}"><c:out
										value="${lLstScreen[1]}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<option value="${lLstScreen[0]}"><c:out
								value="${lLstScreen[1]}"></c:out></option>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</select></td>

		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>


		<tr>

			<td width="5%" align="left"><fmt:message key="CMN.TICKETDESC"
				bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" width="20%"><textarea rows="5" cols="35"
				name="ticketDesc" id="ticketDesc"
				onkeyup="CharacterCount('ticketDesc','CharCountLabel2',700)"${varReadonly} ><c:out
				value="${orgTicketMst.ticketDesc}"></c:out></textarea></td>


			<td width="5%" align="left"><fmt:message
				key="CMN.USERIDPASSWORD" bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" width="20%"><textarea rows="5" cols="35"
				name="ticketCred" id="ticketCred"
				onkeyup="CharacterCount('ticketCred','CharCountLabel1',200)"${varDisabled}><c:out
				value="${orgTicketMst.credentials}"></c:out></textarea></td>


		</tr>



		<tr>
			<td colspan="2" valign="bottom" align="center">
			<div id='CharCountLabel2'></div>
			</td>

			<td colspan="2" valign="bottom" align="center">
			<div id='CharCountLabel1'></div>
			</td>

		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td width="5%" align="left"><fmt:message key="CMN.CONTACTNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" width="20%"><input type="text"
				id="ticketContactNo" name="ticketContactNo" size="40" maxlength="10"
				onkeypress="digitFormat(this);" onblur="validateContactNo();"
				value="${orgTicketMst.contactNo}"${varDisabled}></td>


			<td width="5%" align="left"><fmt:message key="CMN.EMAILID"
				bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" width="20%"><input type="text"
				id="ticketEmailId" name="ticketEmailId" size="40"
				onblur="validateEmailID(ticketEmailId,'Please Enter Valid Email');"
				value="${orgTicketMst.emailId}"${varDisabled}></td>

		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>

		<%-- 	<tr>
			<td width="30%" align="left"><fmt:message key="CMN.USERNAME"
					bundle="${dcpsLables}"></fmt:message></td>
			<td align="left"><input type = "text" id="ticketUserName" name="ticketUserName" size="28" value="${orgTicketMst.userName}" ${varDisabled}></td>
			
		</tr>
		
		<tr><td>&nbsp;</td></tr>--%>

		<tr>
			<td width="5%" align="left"><fmt:message
				key="CMN.TICKETCOMMENTS" bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" style="text-align: justify;"><textarea
				rows="8" cols="35" name="ticketComments" id="ticketComments"
				readonly="readonly"><c:out value="${strRemarks}"></c:out></textarea>
			</td>

			<td width="5%" align="left"><fmt:message key="CMN.TICKETREMARKS"
				bundle="${dcpsLables}"></fmt:message></td>
			<td align="left"><textarea rows="8" cols="35"
				name="ticketRemarks" id="ticketRemarks"></textarea></td>

		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<c:choose>
			<c:when test="${resValue.lStrUser == 'R'}">

				<tr>
					<td width="5%" align="left"><fmt:message
						key="CMN.TICKETSEVERITY" bundle="${dcpsLables}"></fmt:message></td>
					<td align="left"><select name="selSeverity" id="selSeverity"
						style="width: 305px">
						<option value="">-------Selected--------</option>
						<c:forEach items="${lLstSeverity}" var="lLstSeverity">

							<c:choose>
								<c:when test="${orgTicketMst!=null}">
									<c:choose>
										<c:when
											test="${orgTicketMst.severity == lLstSeverity.lookupId}">
											<option value="${lLstSeverity.lookupId}" selected="selected"><c:out
												value="${lLstSeverity.lookupDesc}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${lLstSeverity.lookupId}"><c:out
												value="${lLstSeverity.lookupDesc}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<option value="${lLstSeverity.lookupId}"><c:out
										value="${lLstSeverity.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>


						</c:forEach>
					</select></td>

				    <%-- <td width="5%" align="left"><fmt:message
						key="CMN.TICKETPRIORITY" bundle="${dcpsLables}"></fmt:message> </td> --%>
					<%-- <td align="left"><select name="selPriority" id="selPriority"
						style="width: 305px">
						<option value="">------- Selected --------</option>
						<c:forEach items="${lLstPriority}" var="lLstPriority">

							<c:choose>
								<c:when test="${orgTicketMst!=null}">
									<c:choose>
										<c:when
											test="${orgTicketMst.priority == lLstPriority.lookupId}">
											<option value="${lLstPriority.lookupId}" selected="selected"><c:out
												value="${lLstPriority.lookupDesc}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${lLstPriority.lookupId}"><c:out
												value="${lLstPriority.lookupDesc}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<option value="${lLstPriority.lookupId}"><c:out
										value="${lLstPriority.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select></td> --%>
				</tr>

				<tr>
					<td width="5%" align="left"><fmt:message
						key="CMN.TICKETSTATUS" bundle="${dcpsLables}"></fmt:message></td>
					<td align="left"><select name="selStatus" id="selStatus"
						style="width: 305px">
						<option value="">-------Selected--------</option>
						<c:forEach items="${lLstStatus}" var="lLstStatus">

							<c:choose>
								<c:when test="${orgTicketMst!=null}">
									<c:choose>
										<c:when test="${orgTicketMst.status == lLstStatus.lookupId}">
											<option value="${lLstStatus.lookupId}" selected="selected"><c:out
												value="${lLstStatus.lookupDesc}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${lLstStatus.lookupId}"><c:out
												value="${lLstStatus.lookupDesc}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<option value="${lLstStatus.lookupId}"><c:out
										value="${lLstStatus.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>


						</c:forEach>
					</select></td>

					<td width="5%" align="left"><fmt:message key="CMN.TICKETRCA"
						bundle="${dcpsLables}"></fmt:message></td>
					<td align="left"><select name="selRootCause" id="selRootCause"
						style="width: 305px">
						<option value="">-------Selected--------</option>
						<c:forEach items="${lLstRootCause}" var="lLstRootCause">

							<c:choose>
								<c:when test="${orgTicketMst!=null}">
									<c:choose>
										<c:when
											test="${orgTicketMst.rootCause == lLstRootCause.lookupId}">
											<option value="${lLstRootCause.lookupId}" selected="selected"><c:out
												value="${lLstRootCause.lookupDesc}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${lLstRootCause.lookupId}"><c:out
												value="${lLstRootCause.lookupDesc}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<option value="${lLstRootCause.lookupId}"><c:out
										value="${lLstRootCause.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

			</c:when>
		</c:choose>
		
	<%-- 	<tr>
			<td>&nbsp;</td>
		</tr>--%>
		<!--  --><div>
		<table width="100%">

			<tr>
				<td>

				<fieldset class="tabstyle" style="width: 100%"><jsp:include
					page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="File" />
					<jsp:param name="formName" value="TicketForm" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="File Upload" />
					<jsp:param name="multiple" value="N" />
					<jsp:param name="removeAttachmentFromDB" value="Y" />
				</jsp:include></fieldset>
				
			
				
				
				</td>
			</tr>
		</table>
		</div>

		<tr>
			<td>&nbsp;</td>
		</tr>
		

		<c:choose>
			<c:when test="${resValue.flag == 'Add' && resValue.lStrUser == 'U'}">
			<div align="center"><tr>
					<td colspan="2" align="right"><input type="button"
						name="submitEntryButton" class="bigbutton" id="submitEntryButton"
						value="Submit Entry" onclick="submitNewEntry(this);" /></td>
					<td align="center"><input type="button" name="backA"
						class="bigbutton" id="backA" value="Back" onclick="backToLoad();"
						size="65" /></td>

				</tr>
			</c:when>
			<c:otherwise>
				<div align="center"><tr>
					<td colspan="2" align="right"><input type="button"
						name="updateTicket" class="bigbutton" id="updateTicket"
						type="button" value="Update" onclick="updateEntry('A');" size="65" /></td>
					<td align="center"><input type="button" name="backU"
						class="bigbutton" id="backU" type="button" value="Back"
						onclick="backToLoad();" size="65" /></td>
					<c:if test="${resValue.lStrUser == 'U'}">
						<td><input type="button" name="closeTicket" class="bigbutton"
							id="closeTicket" type="button" value="Close Ticket"
							onclick="updateEntry('C');" size="65" /></td>
					</c:if>

				</tr></div>
			</c:otherwise>
		</c:choose>

	</table>
	</div>
	</fieldset>

	<input type="hidden" id="hdnUser" value="${resValue.lStrUser}">


</hdiits:form>

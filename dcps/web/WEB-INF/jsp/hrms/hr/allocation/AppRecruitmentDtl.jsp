<%
try {
	
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 




<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="newlist" value="${resValue.newlist}"> </c:set>
<c:set var="gender" value="${resValue.gender}"> </c:set>
<c:set var="education" value="${resValue.education}"> </c:set>
<c:set var="postdetails" value="${resValue.postdetails}"> </c:set>

<c:set var="candidatespecificdtls" value="${resValue.recruit}"> </c:set>
<c:set var="salutation" value="${resValue.salutation}"> </c:set>
<c:set var="appflag" value="${resValue.appflag}"> </c:set>
<c:set var="postname" value="${resValue.postname}"> </c:set>
<c:set var="doj" value="${resValue.doj}"> </c:set>

<html>

<head>


<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype.js"></script>
<script type="text/javascript" src="script/common/ajaxtags.js"></script>

<script type="text/javascript" src="script/common/address.js"></script>


<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>


<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="script/common/attachment.js"></script>

</head>


<hdiits:form name="Allocation" validate="true" method="POST"  action="hdiits.htm" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Recruitment.details" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
	
	<div id="tcontent1" class="tabcontent" tabno="0">	
	
<hdiits:fieldGroup titleCaptionId="Allocation.personal" bundle="${AllocLab}" expandable="true" mandatory="true" > 
   
	
	<table width="100%" >
	<tr>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.empprefix" bundle="${AllocLab}"/></b></td>
	<td width="25%">${salutation}</td>
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
	<tr>
	<td width="25%"> </td><td width="25%"><b><hdiits:caption captionid="Allocation.fstname" bundle="${AllocLab}"/></b></td><td width="25%"><b><hdiits:caption captionid="Allocation.mdlname" bundle="${AllocLab}"/></b></td><td width="25%"><b><hdiits:caption captionid="Allocation.lstname" bundle="${AllocLab}"/></b></td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.namecandidate" bundle="${AllocLab}"/><hdiits:caption captionid="Allocation.ineng" bundle="${AllocLab}"/></b></td><td width="25%">${newlist.cmnPersonMst.firstName} </td><td width="25%">${newlist.cmnPersonMst.middleName} </td><td width="25%">${newlist.cmnPersonMst.lastName} </td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.namecandidate" bundle="${AllocLab}"/>
	<hdiits:caption captionid="Allocation.ingu" bundle="${AllocLab}" /></b></td>
	<td width="25%">${candidatespecificdtls.gujFirstName} </td>
	<td width="25%">${candidatespecificdtls.gujMiddleName}  </td>
	<td width="25%">${candidatespecificdtls.gujLastName}</td>
	</tr>
	

	
	
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
	<td width="25%"><fmt:formatDate  value="${newlist.cmnPersonMst.dob}" pattern="dd/MM/yyyy" /></td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.age" bundle="${AllocLab}"/></b></td>
	<td width="25%"><label id="age_value" ></label></td>
	</tr>
	
	
	<tr>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.category" bundle="${AllocLab}"/></b></td>
	<td width="25%">${newlist.cmnPersonMst.category}</td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.gender" bundle="${AllocLab}"/></b></td>
	<td width="25%">${gender} </td>
	</tr>
	
	
	<tr>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
	<td width="25%">${education}</td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.dateofjoin" bundle="${AllocLab}"/></b></td>
	<td width="25%"><fmt:formatDate value="${doj}" pattern="dd/MM/yyyy" /></td>
	</tr>
	</table>
	</hdiits:fieldGroup>
	<table width="100%">
	<tr>
	
				<td class="fieldLabel" colspan="3">
	<jsp:include page="/WEB-INF/jsp/common/viewAddress.jsp">
				
						<jsp:param name="addrName" value="PersonAddress" />
						<jsp:param name="addressTitle" value="Contact Address" />
												
					</jsp:include>
					</td>
				
</tr>
		</table>
		
	
<table width="100%">
	<tr>
	
				<td class="fieldLabel" colspan="3">
	<jsp:include page="/WEB-INF/jsp/common/viewAddress.jsp">
						<jsp:param name="addrName" value="BirthAddress" />
						<jsp:param name="addressTitle" value="Birth Address" />
											
					</jsp:include>
					</td>
				
</tr>
	</table>
<hdiits:fieldGroup titleCaptionId="Allocation.contact" bundle="${AllocLab}" expandable="true" mandatory="true" > 	
	
	<table width="100%">
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.phoneno" bundle="${AllocLab}"/></b></td>
	<td width="25%">${newlist.phoneNo} </td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.email" bundle="${AllocLab}"/></b></td>
	<td width="25%">${newlist.email}  </td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.postname" bundle="${AllocLab}"/></b></td>
	<td width="25%" id="allocatedpost">
	${postname}
	</td>
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
	</table>
	</hdiits:fieldGroup>
	
	<table border="1" id="attachtb" width="100%">
		<tr>
			<td colspan="4"  width="100%"><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="Recruitment123" />
				<jsp:param name="formName" value="Allocation" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Recruitment" />
				<jsp:param name="multiple" value="N" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
			</jsp:include></td>
		</tr>
	</table>
		
	</div>	
	<table align="center" >
	<tr align="center">
	
	<td align="center">
	<hdiits:button name="clsbtn" type="button"  captionid="Allocation.Close" bundle="${AllocLab}" onclick="Closebt()"/>
	</td>
	</tr>
	</table>
	<script >


	

		 
			var bdate='${newlist.cmnPersonMst.dob}';
			var birthDate=bdate.split(" ");
		
			var splitDate=birthDate[0].split("-");							
			var bday=parseInt(splitDate[2],10);
			var bmo=(parseInt(splitDate[1])-1,10);
			var byr=parseInt(splitDate[0]);
			var age;
			var now = new Date();		
			
			tday=now.getUTCDate();
			tmo=(now.getUTCMonth());
			tyr=(now.getUTCFullYear());
			if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr}				
			else  {age=byr+1}
			
			
			
			 document.getElementById('age_value').innerHTML=tyr-age;
			 
		
			 </script>
			 <script >
			
			
			 
			
			 
			 
			
			
				document.getElementById('target_uploadRecruitment123').style.display='none';
				document.getElementById('formTable1Recruitment123').firstChild.firstChild.style.display='none';
			
			 
			
			 
			  </script>
			
	
			
	
 	</div>
 	
	
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>	

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</html>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>

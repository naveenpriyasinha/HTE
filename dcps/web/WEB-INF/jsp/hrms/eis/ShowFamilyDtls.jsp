<%try { %>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.empFamilyLables" var="familyLables" scope="request"/>
<html>
<head>
<script type="text/javascript" src="script/hrms/eis/eprofile.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/FamilyDtls.js"/>"></script>

<title>Human Resource Management System</title>
<script type="text/javascript">		
		var navDisplay = true,disabledFlag=false;
		var flgaMidName=true,flgaOccu=true,flagQualification=true,flagEmploymentStatus=true,flagCountry=true;
		var arr = new Array();
</script>
</head>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="lArrObj" value="${resValue.FamilyDtls}"></c:set>
<c:set var="RelationlookUpList" value="${resValue.Relation}"> </c:set>	
<c:set var="EmpStalookUpList" value="${resValue.EmploymentStatus}"></c:set>
<c:set var="GenderlookUpList" value="${resValue.Gender}"> </c:set>
<c:set var="MaritallookUpList" value="${resValue.Marital}"> </c:set>
<c:set var="FmQualookUpList" value="${resValue.FmQualookUpList}"> </c:set>
<c:set var="FmOccupationObj" value="${resValue.Occupation}"></c:set>
<c:set var="FmDeadOrAlive" value="${resValue.DeadOrAlive}"></c:set>
<c:set var="FamilyArrObj" value="${resValue.MstFamilyDtls}"></c:set>
<c:set var="cmnCountryMstList" value="${resValue.cmnCountryMstList}"></c:set>
<c:set var="cmnCountryMstListHst" value="${resValue.cmnCountryMstList}"></c:set>
<c:set var="countMe" value="1"></c:set>
<body>
<hdiits:form name="showFamilyDtls" validate="true" method="POST" action="javascript:submitHiddenData();" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.FamilyDtls" bundle="${familyLables}"></fmt:message></b></a></li>
	</ul>
</div>
<div id="family" name="family">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<%@ include file="empInfo/EmployeeInfo.jsp"%>	
	
	<hdiits:fieldGroup titleCaptionId="eis.cur_req" bundle="${familyLables}">
	<!--  <table class="tabtable">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
		<strong><u><fmt:message key="eis.cur_req" bundle="${familyLables}"/></u></strong> </font></td>		
	</tr>		
	</table> -->		
	<br>			
	<c:if test="${empty lArrObj}">							
		<center><b><hdiits:caption captionid="eis.no_pen_req" bundle="${familyLables}" /></b></center>
	</c:if>	
	<c:if test="${not empty lArrObj}">
	<table id="FamilyDataTable" class="tabtable" name= "FamilyDataTable"  border="1" BGCOLOR="WHITE" align="center" width="100%" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;">													
		<tr>				
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.srno" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="15%"><b><hdiits:caption captionid="eis.NAME" bundle="${familyLables}"/></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.GENDER" bundle="${familyLables}" /></b></td>				
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Relation" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.status" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="10%"><b><hdiits:caption captionid="eis.DATE_OF_BIRTH" bundle="${familyLables}" /></b> (DD/MM/YYYY)</td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Employment_Status" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.occupation" bundle="${familyLables}" /></b></td>								
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Marital_Status" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Qualification" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Nationality" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.req_for" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Links" bundle="${familyLables}"></hdiits:caption></b></td>			
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.actions" bundle="${familyLables}"></hdiits:caption></b></td>
		</tr>
			<c:set var="j" value="1"></c:set>		
			<c:set var= "FamilyObj" value="${HrEisEmpFamilyDtlsTrn}"></c:set>			
			<c:forEach var="FamilyObj" items="${lArrObj}">								
			<tr id="${FamilyObj.id.reqId}">						
			<td><c:out value="${j}"/></td>
			<td>
				<script>
					var reqFullName="${FamilyObj.fmFirstName}"+" "+"${FamilyObj.fmMiddleName}"+" "+"${FamilyObj.fmLastName}";					
					document.write(reqFullName);
				</script>				
			</td>
			
			<td>
			<c:forEach var="genderobj" items="${GenderlookUpList}">	  
			<c:set var="GenderObjId" value="${FamilyObj.cmnLookupMstByFmGender.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${genderobj.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${genderobj.lookupDesc}"></c:set>	
		    <c:if test="${GenderObjId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
			</c:if>	
			</c:forEach>
			</td>
			
			<td>						
			<c:forEach var="relationobj" items="${RelationlookUpList}">	 
			<c:set var="RelationObjId" value="${FamilyObj.cmnLookupMstByFmRelation.lookupName}"></c:set>						
		    <c:set var="cmnLookupId" value="${relationobj.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${relationobj.lookupDesc}"></c:set>	
		    <c:if test="${RelationObjId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
			</c:if>	
			</c:forEach>			
			</td>
			
			<td>
			<c:forEach var="deadOrAlive" items="${FmDeadOrAlive}">
			<c:set var="deadOrAliveId" value="${FamilyObj.cmnLookupMstByFmDeadOrAlive.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${deadOrAlive.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${deadOrAlive.lookupDesc}"></c:set>	
		    <c:if test="${deadOrAliveId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
			</c:if>	
			</c:forEach> 
			</td>
			
			<td>
				<c:set var="dob"value="${FamilyObj.fmDateOfBirth}"></c:set>
				<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${dob}"></fmt:formatDate>
			</td>
			
			<td>			
			<c:forEach var="empstaobj" items="${EmpStalookUpList}">	  
			<c:set var="EmpStaObjId" value="${FamilyObj.cmnLookupMstByFmEmploymentStatus.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${empstaobj.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${empstaobj.lookupDesc}"></c:set>	
		    <c:if test="${EmpStaObjId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
		    	<script>setFlags('EmploymentStatus');</script>
			</c:if>	
			</c:forEach>	
			<script>
					if(flagEmploymentStatus==true){document.write("-");}
			</script>		
			</td>
													
			<td>	
				<c:set var="FmOccuObjName" value="${FamilyObj.cmnLookupMstByFmOccupation.lookupName}"></c:set>													
				<c:set var="FmOccuObjId" value="${FamilyObj.cmnLookupMstByFmOccupation.lookupDesc}"></c:set>
				<c:if test="${not empty FamilyObj.cmnLookupMstByFmOccupation}">
					<c:if test="${FmOccuObjName ne 'SELECT'}">
						<c:out value="${FmOccuObjId}"/>
					</c:if>
					<c:if test="${FmOccuObjName eq 'SELECT'}">
						-
					</c:if>
				</c:if>
			</td>
			
			<td>
			<c:forEach var="maritalobj" items="${MaritallookUpList}">	  
			<c:set var="EmpMarObjId" value="${FamilyObj.cmnLookupMstByFmMaritalStatus.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${maritalobj.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${maritalobj.lookupDesc}"></c:set>	
		    <c:if test="${EmpMarObjId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
			</c:if>	
			</c:forEach>
			</td>
			<td>				
				<c:forEach var="fmquaobj" items="${FmQualookUpList}">	  
					<c:set var="FmQuaObjId" value="${FamilyObj.cmnLookupMstByFmQualification.lookupName}"></c:set>
				    <c:set var="cmnLookupId" value="${fmquaobj.lookupName}" ></c:set>	
				    <c:set var="cmnLookupName" value="${fmquaobj.lookupDesc}"></c:set>	
				    <c:if test="${FmQuaObjId == cmnLookupId}">		    
					    <script>
							var qualification="${cmnLookupName}";					
							var subQualification="${FamilyObj.cmnLookupMstBySubQualification.lookupDesc}";
							var discipline="${FamilyObj.cmnLookupMstByDiscipline.lookupDesc}";
							var disciplineLookupName="${FamilyObj.cmnLookupMstByDiscipline.lookupName}";
							var subQualLookupName="${FamilyObj.cmnLookupMstBySubQualification.lookupName}";
							if(subQualLookupName=='' || subQualLookupName==null || subQualLookupName=='null' || subQualLookupName=='SELECT')
							{
								subQualification='-';
							}
							if(disciplineLookupName=='' || disciplineLookupName==null || disciplineLookupName=='null' || disciplineLookupName=='SELECT')
							{
								discipline='-';
							}
							var fullQualification=qualification+"("+subQualification+"/"+discipline+")";			
							document.write(fullQualification);
							setFlags('Qualification');
						</script>
					</c:if>	
				</c:forEach>	
				<script>
					if(flagQualification==true){document.write("-");}
				</script>											
			</td>
			
			<td>	
			<c:forEach var="cmnCountryMstList" items="${cmnCountryMstList}">	  
			<c:set var="FmCountryObjCode" value="${FamilyObj.cmnCountryMstByFmNationality.countryCode}"></c:set>
		    <c:set var="countryCode1" value="${cmnCountryMstList.countryCode}" ></c:set>	
		    <c:set var="countryName" value="${cmnCountryMstList.countryName}"></c:set>	
		    <c:if test="${FmCountryObjCode == countryCode1}">		    
		    	<c:out value="${countryName}"/>
		    	<script>setFlags('Country');</script>
			</c:if>	
			</c:forEach>
			<script>
					if(flagCountry==true){document.write("-");}
			</script>													
			</td>		
			
			<td>			
				<c:set var="reqFlag" value="${FamilyObj.requestFlag}" ></c:set>	
			    <c:if test="${reqFlag eq 'A'}">		    
			    	<fmt:message key="eis.new_add" bundle="${familyLables}" />	
				</c:if>
				<c:if test="${reqFlag eq 'D'}">		    
			    	<fmt:message key="eis.Deletion" bundle="${familyLables}" />			
				</c:if>
				<c:if test="${reqFlag eq 'U'}">		    
			    	<fmt:message key="eis.updation" bundle="${familyLables}" />	
				</c:if>	
			</td>	
			<c:set var="attachmentId" value="${FamilyObj.cmnAttachmentMst.attachmentId}"></c:set>
			<c:choose>			
			<c:when test="${attachmentId eq 0}">				
				<td><b><fmt:message key="eis.NoAttachmnt" bundle="${familyLables}"/></b></td>
			</c:when>
			<c:otherwise>
				<c:if test="${empty attachmentId}"><td><b><fmt:message key="eis.NoAttachmnt" bundle="${familyLables}"/></b></td></c:if>
				<c:if test="${not empty attachmentId}">
					<td id="ShowAttach${countMe}"><a href=javascript:void('Attachment') onclick=javascript:eprofileShowAttachment('${FamilyObj.cmnAttachmentMst.attachmentId}','attachmentBiometric','showFamilyDtls','HideAttach${countMe}','ShowAttach${countMe}','attachmentTable'),showFamilyAttachmentFieldGroup()><fmt:message key="eis.ShowAttachmnt" bundle="${familyLables}"/></a></td>
					<td id="HideAttach${countMe}" style="display:none">
						<a href=javascript:void('Attachment') onclick=javascript:eprofileHideAttachment('ShowAttach${countMe}','HideAttach${countMe}')><fmt:message key="eis.HideAttachmnt" bundle="${familyLables}"/></a>
					</td>
				</c:if>
			</c:otherwise>
			</c:choose>	
			<td>			
				<c:if test="${FamilyObj.checkStatus eq 'Y'}">		    
		    		<input type="checkbox" name="chk" id ="chk" value="${FamilyObj.id.memberId}" checked disabled="disabled"/>				
				</c:if>
				<c:if test="${FamilyObj.checkStatus eq 'N'}">	    
		    		<input type="checkbox" name="chk" id ="chk" value="${FamilyObj.id.memberId}" disabled="disabled"/>				
				</c:if>
			</td>
		</tr>	
		<script>
			callMe('${FamilyObj.actionFlag}');		
			resetMyAllFlag();
		</script>
		<c:set var="j" value="${j+1}"></c:set>
		</c:forEach>		
	</table>
	</c:if>
	<BR>
	<table align="center" id="msg_Id" style="display:none">
		<tr>
		<td align="center">
			<b><hdiits:caption captionid="eis.RequestApprove" bundle="${familyLables}" /></b>
		</td></tr>
	</table>
	</hdiits:fieldGroup>
	
	<BR>
	<hdiits:fieldGroup id="showApprovedRequestFieldGroupId" titleCaptionId="eis.app_req" bundle="${familyLables}" collapseOnLoad="true">
	<!--<table class="tabtable">
		<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
		<strong><u><fmt:message key="eis.app_req" bundle="${familyLables}"/></u></strong> </font></td>				
		</tr>
	</table> -->
	<BR>	
		<c:if test="${not empty FamilyArrObj}">			
		<table id="FamilyApproveDataTable" class="tabtable" name= "FamilyApproveDataTable" border="1"  BGCOLOR="WHITE" align="center" width="100%" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;">													
			<tr>				
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.srno" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="15%"><b><hdiits:caption captionid="eis.NAME" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.GENDER" bundle="${familyLables}" /></b></td>				
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Relation" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="10%"><b><hdiits:caption captionid="eis.DATE_OF_BIRTH" bundle="${familyLables}" /></b> (DD/MM/YYYY)</td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Employment_Status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.occupation" bundle="${familyLables}" /></b></td>								
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Marital_Status" bundle="${familyLables}" /></b></td>				
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Qualification" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Nationality" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Links" bundle="${familyLables}"></hdiits:caption></b></td>			
			</tr>	
			<c:set var="j" value="1"></c:set>		
			<c:set var= "objFamily" value="${HrEisEmpFamilyDtls}"></c:set>
 			
			<c:forEach var="i1" items="${FamilyArrObj}">			
			<c:set var="objFamily" value="${i1}"></c:set>
			<tr>			
			
			<td><c:out value="${j}"/></td>
			<td>
				<script>
					var appFullName="${objFamily.fmFirstName}"+" "+"${objFamily.fmMiddleName}"+" "+"${objFamily.fmLastName}";					
					document.write(appFullName);
				</script>				
			</td>
			
			<td>
			<c:forEach var="genderobj" items="${GenderlookUpList}">	  
			<c:set var="GenderObjId" value="${objFamily.cmnLookupMstByFmGender.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${genderobj.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${genderobj.lookupDesc}"></c:set>	
		    <c:if test="${GenderObjId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
			</c:if>	
			</c:forEach>
			</td>
			<td>			
			<c:forEach var="relationobj" items="${RelationlookUpList}">	  
			<c:set var="RelationObjId" value="${objFamily.cmnLookupMstByFmRelation.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${relationobj.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${relationobj.lookupDesc}"></c:set>	
		    <c:if test="${RelationObjId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
			</c:if>	
			</c:forEach>			
			</td>			
			
			<td>
			<c:forEach var="deadOrAlive" items="${FmDeadOrAlive}">
			<c:set var="deadOrAliveId" value="${objFamily.cmnLookupMstByFmDeadOrAlive.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${deadOrAlive.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${deadOrAlive.lookupDesc}"></c:set>	
		    <c:if test="${deadOrAliveId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
			</c:if>	
			</c:forEach> 			
			</td>			
			<td>
				<c:set var="dob"value="${objFamily.fmDateOfBirth}"></c:set>
				<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${dob}"></fmt:formatDate>
			</td>
			
			<td>			
			<c:forEach var="empstaobj" items="${EmpStalookUpList}">	  
			<c:set var="EmpStaObjId" value="${objFamily.cmnLookupMstByFmEmploymentStatus.lookupName}"></c:set>
		    <c:set var="cmnLookupId" value="${empstaobj.lookupName}" ></c:set>	
		    <c:set var="cmnLookupName" value="${empstaobj.lookupDesc}"></c:set>	
		    <c:if test="${EmpStaObjId == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
		    	<script>setFlags('EmploymentStatus');</script>
			</c:if>	
			</c:forEach>	
			<script>
					if(flagEmploymentStatus==true){document.write("-");}
			</script>	
			</td>
			
			<td>														
				<c:set var="FmOccuObjName" value="${objFamily.cmnLookupMstByFmOccupation.lookupName}"></c:set>
				<c:set var="FmOccuObjId" value="${objFamily.cmnLookupMstByFmOccupation.lookupDesc}"></c:set>
		    	<c:if test="${not empty objFamily.cmnLookupMstByFmOccupation}">
					<c:if test="${FmOccuObjName ne 'SELECT'}">
						<c:out value="${FmOccuObjId}"/>
					</c:if>
					<c:if test="${FmOccuObjName eq 'SELECT'}">
						-
					</c:if>
				</c:if>
				<c:if test="${empty objFamily.cmnLookupMstByFmOccupation}">
					-
				</c:if>
			</td>		
			
			<td>
				<c:forEach var="maritalobj" items="${MaritallookUpList}">	  
					<c:set var="EmpMarObjId" value="${objFamily.cmnLookupMstByFmMaritalStatus.lookupName}"></c:set>
				    <c:set var="cmnLookupId" value="${maritalobj.lookupName}" ></c:set>	
				    <c:set var="cmnLookupName" value="${maritalobj.lookupDesc}"></c:set>	
				    <c:if test="${EmpMarObjId == cmnLookupId}">		    
				    	<c:out value="${cmnLookupName}"/>
					</c:if>	
				</c:forEach>
			</td>							
			
			<td>
				<c:forEach var="fmquaobj" items="${FmQualookUpList}">	  
					<c:set var="FmQuaObjId" value="${objFamily.cmnLookupMstByFmQualification.lookupName}"></c:set>
				    <c:set var="cmnLookupId" value="${fmquaobj.lookupName}" ></c:set>	
				    <c:set var="cmnLookupName" value="${fmquaobj.lookupDesc}"></c:set>	
				    <c:if test="${FmQuaObjId == cmnLookupId}">		    
				  		<script>
					  		var qualification="${cmnLookupName}";					
							var subQualification="${objFamily.cmnLookupMstBySubQualification.lookupDesc}";
							var discipline="${objFamily.cmnLookupMstByDiscipline.lookupDesc}";
							var subQualLookupName="${objFamily.cmnLookupMstBySubQualification.lookupName}";
							var disciplineLookupName="${objFamily.cmnLookupMstByDiscipline.lookupName}";
							if(subQualLookupName=='' || subQualLookupName==null || subQualLookupName=='null' || subQualLookupName=='SELECT')
							{
								subQualification='-';
							}
							if(disciplineLookupName=='' || disciplineLookupName==null || disciplineLookupName=='null' || disciplineLookupName=='SELECT')
							{
								discipline='-';
							}
							var fullQualification=qualification+"("+subQualification+"/"+discipline+")";			
							document.write(fullQualification);
							setFlags('Qualification');
				  		</script>
					</c:if>
					<c:set var="countMe" value="${countMe+1}"></c:set>			
				</c:forEach>	
				<script>
					if(flagQualification==true){document.write("-");}
				</script>																
			</td>		
			
			<td>	
			<c:forEach var="cmnCountryMstList" items="${cmnCountryMstListHst}">	  
			<c:set var="FmCountryObjCode" value="${objFamily.cmnCountryMstByFmNationality.countryCode}"></c:set>
		    <c:set var="countryCode" value="${cmnCountryMstList.countryCode}" ></c:set>	
		    <c:set var="countryName" value="${cmnCountryMstList.countryName}"></c:set>	
		    <c:if test="${FmCountryObjCode == countryCode}">		    
		    	<c:out value="${countryName}"/>
		    	<script>setFlags('Country');</script>
			</c:if>	
			</c:forEach>
			<script>
					if(flagCountry==true){document.write("-");}
			</script>													
			</td>	
			
			<c:set var="attachmentId" value="${objFamily.cmnAttachmentMst.attachmentId}"></c:set>
			
			<c:choose>			
			<c:when test="${attachmentId eq 0}">				
				<td><b><fmt:message key="eis.NoAttachmnt" bundle="${familyLables}"/></b></td>
			</c:when>
			<c:otherwise>
				<c:if test="${empty attachmentId}"><td><b><fmt:message key="eis.NoAttachmnt" bundle="${familyLables}"/></b></td></c:if>
				<c:if test="${not empty attachmentId}">
					<td id="ShowAttach${countMe}">
						<a href=javascript:void('Attachment') onclick=javascript:eprofileShowAttachment('${objFamily.cmnAttachmentMst.attachmentId}','attachmentBiometric','showFamilyDtls',"HideAttach${countMe}","ShowAttach${countMe}",'attachmentTable'),showFamilyAttachmentFieldGroup()><fmt:message key="eis.ShowAttachmnt" bundle="${familyLables}"/></a>
					</td>
					<td id="HideAttach${countMe}" style="display:none">
						<a href=javascript:void('Attachment') onclick=javascript:eprofileHideAttachment("ShowAttach${countMe}",'HideAttach${countMe}')><fmt:message key="eis.HideAttachmnt" bundle="${familyLables}"/></a>
					</td>
				</c:if>
			</c:otherwise>
			</c:choose>	
																					
			</tr>
			<c:set var="j" value="${j+1}"></c:set>	
			<c:set var="countMe" value="${countMe+1}"></c:set>
			<script>
				resetMyAllFlag();
			</script>	
			</c:forEach>							
		</table>		
		</c:if>
		<BR>	
        <c:if test="${empty FamilyArrObj}">
        		<center><b><hdiits:caption captionid="eis.no_app_req" bundle="${familyLables}" /></b></center>				
		</c:if>
		</hdiits:fieldGroup>
		<br>
		<hdiits:fieldGroup id="showAttachmentRequestFieldGroupId" titleCaptionId="eis.attchment_data" bundle="${familyLables}" collapseOnLoad="true">
		<!-- <table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="eis.attchment_data" bundle="${familyLables}"/></u></strong> </font></td>				
		</tr>
		</table>
		<BR>-->
		<table class="tabtable" align="center" id="attachmentTable" style="display:none">
		<tr>
			<hdiits:fmtMessage key="eis.attachment_Details" bundle="${familyLables}" var="showFamilyAttachmentTitle" ></hdiits:fmtMessage>
			<td class="fieldLabel">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="attachmentBiometric" />
					<jsp:param name="formName" value="showFamilyDtls" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="${showFamilyAttachmentTitle}" />			
					<jsp:param name="multiple" value="N" />
				</jsp:include>				
			</td>
		</tr>
		</table>
		</hdiits:fieldGroup>
		<table id="tabNavigation" style="display:none" >
		<tr><td>
			<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" >
				<jsp:param name="disableReset" value="true"/> 
			</jsp:include>
		</td></tr>
		</table>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
				
	</div>
	</div>
	<hdiits:hidden name="approveDtls" id="approveDtls" />
	</hdiits:form>
	<script type="text/javascript">
	onLoadApproveFamilyDtls();	
	initializetabcontent("maintab")
	</script>	
</body>
</html>		
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
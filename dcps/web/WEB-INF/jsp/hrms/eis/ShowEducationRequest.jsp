<%try { %>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.empEducationLabels" var="showEduReqLables" scope="request"/>
<html>
<head>
<script type="text/javascript" src="script/hrms/eis/eprofile.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EducationDtls.js"/>"></script>
<title>Human Resource Management System</title>
<script type="text/javascript">		
	var countMe=1,v=0,disabledFlag=true;
	var arr = new Array();
	var flgaDiscipline=true,flgaCategory=true;
	var v1=0;
</script>
</head>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="lArrObj" value="${resValue.PendingDtls}"></c:set>
<c:set var="lListQualObj" value="${resValue.Qualifications}"></c:set>	
<c:set var="lListDiscipline" value="${resValue.Discipline}"></c:set>
<c:set var="lListSubQual" value="${resValue.SubQualifications}"></c:set>	
<c:set var="lListUnit" value="${resValue.Unit}"></c:set>
<c:set var="approvedData" value="${resValue.ApprovedData}"></c:set>
<c:set var="lListcourseCategoryObj" value="${resValue.courseCategory}"></c:set>
<c:set var="countMe" value="1"></c:set>
<body>
<hdiits:form name="showEdu" validate="true" action="javascript:submitHiddenData();" method="POST" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.quali_dtls" bundle="${showEduReqLables}" /></b></a></li>
		</ul>
	</div>
	<div id="nominee" name="nominee">		
	<div id="tcontent1" class="tabcontent" tabno="0">
	<%@ include file="empInfo/EmployeeInfo.jsp"%>		
	
	<hdiits:fieldGroup titleCaptionId="eis.cur_req" bundle="${showEduReqLables}">
	<!-- <table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="eis.cur_req" bundle="${showEduReqLables}"/></u></strong> </font></td>				
		</tr>
	</table>-->
	<c:if test="${not empty lArrObj}">
	<table class="tabtable" border="1">			
	<tr></tr>
	</table>			
		<BR>
		<table align="center"  border="1"  BGCOLOR="WHITE" width="100%" class="tabtable" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;">					
		<tr>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.srno" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.qualification" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.subqualification" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.discipline" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.courseCategory" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.uni_ins_board" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.year_of_pass" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.unitMarks" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.req_for" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Links" bundle="${showEduReqLables}"></hdiits:caption></b></td>			
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.actions" bundle="${showEduReqLables}"></hdiits:caption></b></td>
		</tr>		
		<c:forEach var="i1" items="${lArrObj}">			
			<tr>					
			<td>				
				<script>v= increment_showRequest();document.write(v);</script>
			</td>													
			
			<c:forEach var="qualification" items="${lListQualObj}">	    																		   
		    <c:set var="cmnLookupId" value="${qualification.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${qualification.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstByQualificationId.lookupName == cmnLookupId}">		    
		    <td>
		    	<c:out value="${cmnLookupName}"/>
		    </td>			
			</c:if>
			</c:forEach>
			
			<c:forEach var="subqualification" items="${lListSubQual}">	    																   		    
		    <c:set var="cmnLookupId" value="${subqualification.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${subqualification.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstBySubQualificationId.lookupName == cmnLookupId}">		    
		    <td>
		    	<c:out value="${cmnLookupName}"/>
		    </td>			
			</c:if>
			</c:forEach>					    								
			
			<td>
			<c:forEach var="discipline" items="${lListDiscipline}">	    																   		    
		    <c:set var="cmnLookupId" value="${discipline.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${discipline.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstByDicipline.lookupName == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
		    	<script>setFlags('Discipline');</script>
			</c:if>
			</c:forEach>
			<script>
				if(flgaDiscipline==true){document.write("-");}
			</script>
			</td>	
			
			<td>
			<c:forEach var="category" items="${lListcourseCategoryObj}">	    																   		    
		    <c:set var="cmnLookupId" value="${category.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${category.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstByCourseCategory.lookupName == cmnLookupId}">		    
		    	<c:out value="${cmnLookupName}"/>
		    	<script>setFlags('Category');</script>
			</c:if>
			</c:forEach>
			<script>
				if(flgaCategory==true){document.write("-");}
			</script>
			</td>
			
			<td><c:out value="${i1.uniInstituteBoard}"/></td>
			
	    	<td><c:out value="${i1.yearOfPassing}"/></td>
		    
			
			<c:forEach var="unit" items="${lListUnit}">	    																   
				<c:set var="UnitId" value="${i1.cmnLookupMstByUnitsOfMarks.lookupName}" > </c:set>
			   	<c:set var="cmnLookupId" value="${unit.lookupName}" > </c:set>	
			    <c:set var="cmnLookupName" value="${unit.lookupDesc}" > </c:set>	
				<c:if test="${UnitId == cmnLookupId}">		    		    
				    <c:set var="reqUnitOfMarks" value="${cmnLookupName}"/>		    
				</c:if>
			</c:forEach>		
			
			<td>
				<script>
						var unitMarks="${i1.marksScored}"+" "+"${reqUnitOfMarks}";					
						document.write(unitMarks);
				</script>	
			</td>
			
			<td>
				<c:set var="reqFlag" value="${i1.requestFlag}" ></c:set>	
			    <c:if test="${reqFlag eq 'A'}">		    
			    	<fmt:message key="eis.new_add" bundle="${showEduReqLables}" />					
				</c:if>
				<c:if test="${reqFlag eq 'D'}">		    
			    	<fmt:message key="eis.delete" bundle="${showEduReqLables}" />		
				</c:if>
				<c:if test="${reqFlag eq 'U'}">		    
			    	<fmt:message key="eis.update" bundle="${showEduReqLables}" />			
				</c:if>				
			</td>
			<c:set var="attachmentId" value="${i1.cmnAttachmentMst.attachmentId}"></c:set>
			
			<c:choose>			
			<c:when test="${attachmentId eq 0}">				
				<td><b><fmt:message key="eis.NoAttachmnt" bundle="${showEduReqLables}"/></b></td>
			</c:when>
			<c:otherwise>
				<c:if test="${empty attachmentId}"><td><b><fmt:message key="eis.NoAttachmnt" bundle="${showEduReqLables}"/></b></td></c:if>
				<c:if test="${not empty attachmentId}">
					<td id="ShowAttach${countMe}"><a href=javascript:void('Attachment') onclick=javascript:eprofileShowAttachment('${i1.cmnAttachmentMst.attachmentId}','attachmentBiometric','showEdu','HideAttach${countMe}','ShowAttach${countMe}','attachmentTable'),showEducationAttachmentFieldGroup()><fmt:message key="eis.ShowAttachmnt" bundle="${showEduReqLables}"/></a></td>
					<td id="HideAttach${countMe}" style="display:none">
						<a href=javascript:void('Attachment') onclick=javascript:eprofileHideAttachment('ShowAttach${countMe}','HideAttach${countMe}')><fmt:message key="eis.HideAttachmnt" bundle="${showEduReqLables}"/></a>
					</td>
				</c:if>
			</c:otherwise>
			</c:choose>			
										
			<td>
				<c:if test="${i1.checkStatus eq 'Y'}">		    
		    		<input type="checkbox" name="chk" id ="chk" value="${i1.id.srNo}" checked disabled="disabled"/>				
				</c:if>
				<c:if test="${i1.checkStatus eq 'N'}">	    
		    		<input type="checkbox" name="chk" id ="chk" value="${i1.id.srNo}" disabled="disabled"/>				
				</c:if>
			</td>							
			
		</tr>	
		<script>
			resetMyAllFlag();
			callMe('${i1.actionFlag}');	
		</script>	
		<c:set var="countMe" value="${countMe+1}"></c:set>			
		</c:forEach>				
		</table>
		<BR>
		
		<table align="center" id="msg_Id" style="display:none">
			<tr>
			<td align="center">
				<b><hdiits:caption captionid="eis.RequestApprove" bundle="${showEduReqLables}" /></b>
			</td></tr>
		</table>				
		</c:if>
				
		<c:if test="${empty lArrObj}">							
			<center><b><hdiits:caption captionid="eis.no_pen_req" bundle="${showEduReqLables}"/></b></center>
		</c:if>			
		</hdiits:fieldGroup>
		<br>
		
		<hdiits:fieldGroup id="showApprovedRequestFieldGroupId" titleCaptionId="eis.app_req" bundle="${showEduReqLables}" collapseOnLoad="true">
	<!--<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="eis.app_req" bundle="${showEduReqLables}"/></u></strong> </font></td>				
		</tr>
		</table>
		<BR>	-->	
			
		<c:if test="${not empty approvedData}">		
		<table border="1"  BGCOLOR="WHITE" class="tabtable" align="center" width="100%"  borderColor="black" style="border-collapse: collapse;">	
		<tr>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption captionid="eis.srno" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption captionid="eis.qualification" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption captionid="eis.subqualification" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption captionid="eis.discipline" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption captionid="eis.courseCategory" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption captionid="eis.uni_ins_board" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption captionid="eis.year_of_pass" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel"><b><hdiits:caption	captionid="eis.unitMarks" bundle="${showEduReqLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" class="fieldLabel" ><b><hdiits:caption captionid="eis.Links" bundle="${showEduReqLables}"></hdiits:caption></b></td>			
		</tr>
		<c:forEach var="i1" items="${approvedData}">
		<c:set var="countMe" value="${countMe+1}"></c:set>		
		<tr id="app"+countMe>					
			<td>				
				<script>v= increment_showApprove();document.write(v);</script>
			</td>													
			<c:forEach var="qualification" items="${lListQualObj}">	    																   
		    <c:set var="cmnLookupId" value="${qualification.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${qualification.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstByQualificationId.lookupName == cmnLookupId}">		    
		    <td>
		    	<c:out value="${cmnLookupName}"/>
		    </td>			
			</c:if>
			</c:forEach>
			
			<c:forEach var="subqualification" items="${lListSubQual}">	    																   		    
		    <c:set var="cmnLookupId" value="${subqualification.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${subqualification.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstBySubQualificationId.lookupName == cmnLookupId}">		    
		    <td>
		    	<c:out value="${cmnLookupName}"/>
		    </td>			
			</c:if>
			</c:forEach>					    								
	
			<td>
			<c:forEach var="discipline" items="${lListDiscipline}">	    																   		    
		    <c:set var="cmnLookupId" value="${discipline.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${discipline.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstByDicipline.lookupName == cmnLookupId}">		    		    
		    	<c:out value="${cmnLookupName}"/>
		    	<script>setFlags('Discipline');</script>			
			</c:if>
			</c:forEach>
			<script>
				if(flgaDiscipline==true){document.write("-");}
			</script>
			</td>
			
			<td>
			<c:forEach var="category" items="${lListcourseCategoryObj}">	    																   		    
		    <c:set var="cmnLookupId" value="${category.lookupName}" > </c:set>	
		    <c:set var="cmnLookupName" value="${category.lookupDesc}" > </c:set>	
		    <c:if test="${i1.cmnLookupMstByCourseCategory.lookupName == cmnLookupId}">		    		   
		    	<c:out value="${cmnLookupName}"/>
		    	<script>setFlags('Category');</script>
			</c:if>
			</c:forEach>
			<script>
				if(flgaCategory==true){document.write("-");}
			</script>
			</td>	 
			
			<td><c:out value="${i1.uniInstituteBoard}"/></td>
			
			<td><c:out value="${i1.yearOfPassing}"/></td>
									
			<c:forEach var="unit" items="${lListUnit}">	    																   
			    <c:set var="UnitId" value="${i1.cmnLookupMstByUnitsOfMarks.lookupName}" > </c:set>
			    <c:set var="cmnLookupId" value="${unit.lookupName}" > </c:set>	
			    <c:set var="cmnLookupName" value="${unit.lookupDesc}" > </c:set>	
			    <c:if test="${UnitId == cmnLookupId}">		    
			    	 <c:set var="approveUnitOfMarks" value="${cmnLookupName}"/>	
				</c:if>
			</c:forEach>	
			
			<td>
				<script>
						var unitMarks="${i1.marksScored}"+" "+"${approveUnitOfMarks}";					
						document.write(unitMarks);
				</script>	
			</td>	
			
			<c:set var="attachmentId" value="${i1.cmnAttachmentMst.attachmentId}"></c:set>
			
			<c:choose>			
			<c:when test="${attachmentId eq 0}">				
				<td><b><fmt:message key="eis.NoAttachmnt" bundle="${showEduReqLables}"/></b></td>
			</c:when>
			<c:otherwise>
				<c:if test="${empty attachmentId}"><td><b><fmt:message key="eis.NoAttachmnt" bundle="${showEduReqLables}"/></b></td></c:if>
				<c:if test="${not empty attachmentId}">
					<td id="ShowAttach${countMe}">
						<a href=javascript:void('Attachment') onclick=javascript:eprofileShowAttachment('${i1.cmnAttachmentMst.attachmentId}','attachmentBiometric','showEdu',"HideAttach${countMe}","ShowAttach${countMe}",'attachmentTable'),showEducationAttachmentFieldGroup()><fmt:message key="eis.ShowAttachmnt" bundle="${showEduReqLables}"/></a>
					</td>
					<td id="HideAttach${countMe}" style="display:none">
						<a href=javascript:void('Attachment') onclick=javascript:eprofileHideAttachment("ShowAttach${countMe}",'HideAttach${countMe}')><fmt:message key="eis.HideAttachmnt" bundle="${showEduReqLables}"/></a>
					</td>
				</c:if>
			</c:otherwise>
			</c:choose>									
		</tr>	
		<script>
			resetMyAllFlag();
		</script>			
		</c:forEach>						
		</table>
		</c:if>
	
		<c:if test="${empty approvedData}">
			<center><b><hdiits:caption captionid="eis.no_app_req" bundle="${showEduReqLables}"/></b></center>			
		</c:if>
		</hdiits:fieldGroup>
		<BR>
		
		<hdiits:fieldGroup id="showAttachmentRequestFieldGroupId" titleCaptionId="eis.attchment_data" bundle="${showEduReqLables}" collapseOnLoad="true">
<!-- 	<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="eis.attchment_data" bundle="${showEduReqLables}"/></u></strong> </font></td>				
		</tr>
		</table>
		<BR>-->
		<table class="tabtable" align="center" id="attachmentTable">
		<tr>
		<hdiits:fmtMessage key="eis.attachment_Details" bundle="${showEduReqLables}" var="showEduAttachmentTitle" ></hdiits:fmtMessage>
			<td class="fieldLabel">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="attachmentBiometric" />
					<jsp:param name="formName" value="showEdu" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="${showEduAttachmentTitle}" />				
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
		<hdiits:hidden name="approveDtls" id="approveDtls" /><!--  sunil -->
		<script type="text/javascript">
		onLoadEducationDtlsData();				
		initializetabcontent("maintab")
		</script>
	</hdiits:form>
</body>
</html>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
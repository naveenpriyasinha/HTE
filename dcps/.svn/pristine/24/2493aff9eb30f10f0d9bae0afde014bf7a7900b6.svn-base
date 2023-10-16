<%try { %>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.empNomineeLables" var="nomineeLables" scope="request"/>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/NomineeDtls.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="RelationlookUpList" value="${resValue.Relation}"></c:set>
<c:set var="pendingData" value="${resValue.pendingLstObj}"></c:set>
<c:set var="purposeLst" value="${resValue.purposeLst}"></c:set>
<c:set var="approvedData" value="${resValue.ApprovedData}"></c:set>

<hdiits:form name="showNomineeDtls" validate="true" action="javascript:submitHiddenData();"  method="POST" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.nomn_dtls" bundle="${nomineeLables}" /></b></a></li>
	</ul>
</div>
<div id="family" name="family">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<%@ include file="empInfo/EmployeeInfo.jsp"%>	
		
<!--	<table class="tabtable">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
		<strong><u><fmt:message key="eis.cur_req" bundle="${nomineeLables}"/></u></strong> </font></td>		
	</tr>			
	</table> -->		
	
	<hdiits:fieldGroup titleCaptionId="eis.cur_req" bundle="${nomineeLables}">
		<BR>
		<c:if test="${empty pendingData}">							
			<center><b><hdiits:caption captionid="eis.no_pen_req" bundle="${nomineeLables}" /></b></center>
		</c:if>	
		<c:if test="${not empty pendingData}">
			<table name="pendingDataTable" class="tabtable" id="pendingDataTable" border="1" cellpadding="1" cellspacing="1" style="border-collapse: collapse;" bordercolor="black" BGCOLOR="WHITE" class="TableBorderLTRBN"  align="center" width="100%">
				<tr>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.nominee_name" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.relation_nominee" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}" /></b> (DD/MM/YYYY)</td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.age" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.minor" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.share" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.req_for" bundle="${nomineeLables}" /></b></td>
					<td calss="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.nominee_purpose" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.actions" bundle="${nomineeLables}" /></b></td>
				</tr>
				<c:set var="j" value="1"></c:set>		
				<c:set var= "NomineeObj" value="${HrEisEmpNomineeDtlsTrn}"></c:set>
				<c:forEach var="i1" items="${pendingData}">			
				<c:set var="NomineeObj" value="${i1}"></c:set>			
				<tr>			
				
					<td><c:out value="${NomineeObj.nomnName}"></c:out></td>				
					
					<c:set var="RelationObjId" value="${NomineeObj.cmnLookupMstByNomnRelation.lookupName}"></c:set>			
					<c:forEach var="relationobj" items="${RelationlookUpList}">	  		
				    <c:set var="cmnLookupId" value="${relationobj.lookupName}" ></c:set>	
				    <c:set var="cmnLookupName" value="${relationobj.lookupDesc}"></c:set>	
				    <c:if test="${RelationObjId == cmnLookupId}">		    
				    <td>
				    	<c:out value="${cmnLookupName}"/>
				    </td>			
					</c:if>	
					</c:forEach>
								
					<td>
						<c:set var="dob"value="${NomineeObj.nomnDob}"></c:set>
						<fmt:formatDate var="dateOfBirth" type="date" pattern="dd/MM/yyyy" value="${dob}"></fmt:formatDate>
						<c:out value="${dateOfBirth}"></c:out>
					</td>
					
					<td>
						<script>
							var v_age=countAge('${dob}'); 
							document.write(v_age);
						</script>					
					</td>
					<td>
						<c:out value="${NomineeObj.nomnMinor}"></c:out>
					</td>	
					
					<td>
						<c:out value="${NomineeObj.nomnSharePercent}"></c:out>
					</td>						
					<td>							
						<c:set var="reqFlag" value="${NomineeObj.requestFlag}" ></c:set>	
					    <c:if test="${reqFlag eq 'A'}">			    	
					    	<fmt:message key="eis.new_add" bundle="${nomineeLables}" />
						</c:if>
						<c:if test="${reqFlag eq 'D'}">		    		    
					    	<fmt:message key="eis.Deletion" bundle="${nomineeLables}" />			
						</c:if>
						<c:if test="${reqFlag eq 'U'}">		        
					    	<fmt:message key="eis.updation" bundle="${nomineeLables}" />
						</c:if>	
					</td>
					<c:forEach var="purposeLstObj" items="${purposeLst}">	  
						<c:set var="purposeLstObjId" value="${NomineeObj.cmnLookupMstByNomnBenefitTypeId.lookupName}"></c:set>
					    <c:set var="cmnLookupId" value="${purposeLstObj.lookupName}" ></c:set>	
					    <c:set var="cmnLookupName" value="${purposeLstObj.lookupName}"></c:set>	
					    <c:if test="${purposeLstObjId == cmnLookupId}">		    
						    <td>
						    	<c:out value="${cmnLookupName}"/>
						    </td>			
						</c:if>	
					</c:forEach>	
					<td>			
						<c:if test="${NomineeObj.checkStatus eq 'Y'}">		    
				    		<input type="checkbox" name="chk" id ="chk" value="${NomineeObj.id.memberId}" checked disabled="disabled"/>
						</c:if>
						<c:if test="${NomineeObj.checkStatus eq 'N'}">	    
				    		<input type="checkbox" name="chk" id ="chk" value="${NomineeObj.id.memberId}" disabled="disabled"/>				
						</c:if>
					</td>
					</tr>
					<script>
						callMe('${NomineeObj.actionFlag}');		
					</script>
					<c:set var="j" value="${j+1}"></c:set>			
					</c:forEach> 
			</table>	
		</c:if>
	<BR>
	<table align="center" id="msg_Id" style="display:none">
        <tr>
			<td class="fieldLabel" colspan="4"><hdiits:caption captionid="eis.RequestApprove" bundle="${nomineeLables}" /></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	
	<hdiits:fieldGroup id="showApprovedFieldGroupId" titleCaptionId="eis.app_req" bundle="${nomineeLables}" collapseOnLoad="true">
	<!--<table class="tabtable">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message key="eis.app_req" bundle="${nomineeLables}"/></u></strong> </font></td>				
			</tr>
		</table>-->
		<c:if test="${empty approvedData}">
				<center><b><hdiits:caption captionid="eis.no_app_req" bundle="${nomineeLables}" /></b></center>
		</c:if>
		<BR>
		<c:if test="${not empty approvedData}">
			<table name="nomineeApproveDataTable" id="nomineeApproveDataTable" align="center" border="1" cellpadding="1" cellspacing="1" bordercolor="black" style="border-collapse: collapse; BGCOLOR="WHITE" class="tabtable" class="TableBorderLTRBN"  width="100%">
				<tr>				
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.nominee_name" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.relation_nominee" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}" /></b> (DD/MM/YYYY)</td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.age" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.minor" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="eis.share" bundle="${nomineeLables}" /></b></td>				
				</tr>	
				<c:set var="j" value="1"></c:set>		
				<c:set var= "NomineeObj" value="${HrEisEmpNomineeDtls}"></c:set>
				<c:forEach var="i1" items="${approvedData}">			
				<c:set var="NomineeObj" value="${i1}"></c:set>			
				<tr>				
				
					<td><c:out value="${NomineeObj.nomnName}"></c:out></td>				
					
					<c:set var="RelationObjId" value="${NomineeObj.cmnLookupMstByNomnRelation.lookupName}"></c:set>				
					<c:forEach var="relationobj" items="${RelationlookUpList}">	  		
				    <c:set var="cmnLookupId" value="${relationobj.lookupName}" ></c:set>	
				    <c:set var="cmnLookupName" value="${relationobj.lookupDesc}"></c:set>	
				    <c:if test="${RelationObjId == cmnLookupId}">		    
				    <td>
				    	<c:out value="${cmnLookupName}"/>
				    </td>			
					</c:if>	
					</c:forEach>
								
					<td>
						<c:set var="dob"value="${NomineeObj.nomnDob}"></c:set>
						<fmt:formatDate var="dateOfBirth" type="date" pattern="dd/MM/yyyy" value="${dob}"></fmt:formatDate>
						<c:out value="${dateOfBirth}"></c:out>
					</td>
					
					<td>
						<script>
							var v_age=countAge('${dateOfBirth}');
							document.write(v_age);
						</script>					
					</td>
					<td>
						<c:out value="${NomineeObj.nomnMinor}"></c:out>
					</td>	
					
					<td>
						<c:out value="${NomineeObj.nomnSharePercent}"></c:out>
					</td>	
																								
	 			</tr>
	 			<c:set var="j" value="${j+1}"></c:set> 		
	 			</c:forEach> 			
			</table>
		</c:if>			
	</hdiits:fieldGroup>
	
	<table id="tabNavigation" style="display:none" >
		<tr>
			<td>
				<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" >
					<jsp:param name="disableReset"  value="true"/> 
				</jsp:include>
			</td>
		</tr>
	</table>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>	
</div>
</div>
	<hdiits:hidden name="approveDtls" id="approveDtls" />

	<script type="text/javascript">
		disabledMe();
		initializetabcontent("maintab")
	</script>	
</hdiits:form>	
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
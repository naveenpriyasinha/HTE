<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="allotList" value="${resultMap.allotList}"></c:set>
<c:set var="trainingMst" value="${resultMap.trainingMst}"></c:set>
<c:set var="scheduleDtl" value="${resultMap.scheduleDtl}"></c:set>
<c:set var="trngMode" value="${resultMap.trngMode}"></c:set>
<c:set var="rmks" value="${scheduleDtl.trngScheduleRemarks}"/>	
<c:set var="m_AttachDisp" value="${resultMap.m_AttachDisp}"/>	
<c:set var="locati" value="${resultMap.trnglocation}"/>	

<fmt:setBundle basename="resources.trng.SelectedEmpAdd" var="SelectedEmpAdd" scope="request" />
<fmt:setBundle basename="resources.trng.AllotmentLables" var="allotLables" scope="request" />
<fmt:setBundle basename="resources.trng.TrainingMstLables"	var="trangLables" scope="request" />
<fmt:setBundle basename="resources.trng.trschLables" var="trschLables"	scope="request" /> 
<fmt:message bundle="${allotLables}" key="TR.AGTO" var="AGTO"/>
<hdiits:form name="frmcorrnomi" validate="">
	 <table>
	 
	 	<tr>
	 			<td  width="25%"></td>
				<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.ORDERNO"   bundle="${trschLables}"/></td>
	 			<td class="fieldLabel" width="25%"><c:out value="${scheduleDtl.orderNo}"></c:out></td>				 
	 			<td  width="25%"></td>
		</tr>	 	 
	 <tr></tr>
	 <tr></tr>
	 	<tr>
	 		
	 		<td  width="25%"></td>	 
		 	<td class="datatableheader" width="25%"><hdiits:caption	captionid="TR.trainingName"  bundle="${SelectedEmpAdd}"/></td>
		 	<td class="fieldLabel" width="25%"><c:out value="${trainingMst.trainingName}"></c:out></td>
		 	<td  width="25%"></td>
	 	 </tr>	
	 <tr></tr>
	 <tr></tr>
	 	<tr>
	 		
	 		<td  width="25%"></td>	 	 	
	 	 	<td class="datatableheader"><hdiits:caption	captionid="TR.TrainingType"  bundle="${trangLables}"/></td>
		 	<td class="fieldLabel" width="25%"><c:out value="${trainingMst.cmnLookupMstTrainingTypeLookupId.lookupDesc}"></c:out></td>	 				 	
		 	<td  width="25%"></td>
		 	<td></td>
	 	</tr>
	  <tr></tr>
	  <tr></tr>
	 	<tr>
	 		
	 		 <td  width="25%"></td>
	 		<td class="datatableheader" width="25%"><hdiits:caption	captionid="TR.organiser"   bundle="${SelectedEmpAdd}"/></td>
	 		<td class="fieldLabel" width="25%"><c:out value="${scheduleDtl.organizer}"></c:out></td>
	 		<td  width="25%"></td>
	 	</tr>	
 	 <tr></tr>
     <tr></tr>
	 	<tr>	
	 		
	 		<td  width="25%"></td>
	 		<td class="datatableheader" width="25%"><hdiits:caption	captionid="TR.DIRECTOR"   bundle="${trschLables}"/></td>
	 		<td class="fieldLabel" width="25%"><c:out value="${scheduleDtl.director}"></c:out></td>
	 		<td  width="25%"></td>
		</tr>
	  <tr></tr>
	  <tr></tr>
	  	<tr>
 	 		
 	 		<td  width="25%"></td>
 	 		<td class="datatableheader" width="25%"><hdiits:caption	captionid="TR.TRNGMODE"   bundle="${trschLables}"/></td>
	 		<td class="fieldLabel" width="25%"><c:out value="${trngMode}"></c:out></td>
	 		<td  width="25%"></td>
	 	</tr>
	  <tr></tr>
	  <tr></tr>
	  <tr>
	 			<td  width="25%"></td>
				<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.AGTAG"   bundle="${allotLables}"/></td>
	 			<td class="fieldLabel" width="25%"><c:out value="${trainingMst.minage}-">${AGTO}</c:out><c:out value="${trainingMst.maxage} "></c:out></td>				 
	 			<td  width="25%"></td>
		</tr>	 	 
	 <tr></tr>
	 <tr></tr>
	 	<tr>
	 		
	 		<td  width="25%"></td>	 
		 	<td class="datatableheader" width="25%"><hdiits:caption	captionid="TR.ElegiCriteria"  bundle="${trangLables}"/></td>
		 	<td class="fieldLabel" width="25%"><c:out value="${trainingMst.eligibilityCriteria}"></c:out></td>
		 	<td  width="25%"></td>
	 	 </tr>
	 <tr></tr>
	 <tr></tr>
	  	<tr>		
	 		
 	 		<td  width="25%"></td>
	  		<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.ExternalFlag" bundle="${trangLables}" /></td>
			<td class="fieldLabel" width="25%"><c:out value="${trainingMst.isExternal}"></c:out></td>	
			<td  width="25%"></td>
				
				
 	 	</tr>
	  <tr></tr>	
	  <tr></tr>
			  <tr>
			 		
			 		<td  width="25%"></td>
			 		<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.batchNo"   bundle="${SelectedEmpAdd}"/></td>
			 		<td class="fieldLabel" width="25%"><c:out value="${scheduleDtl.batchNo}"></c:out></td>	 	
			 		<td  width="25%"></td>
			  </tr>
	  <tr></tr>	
	  <tr></tr>
	   			<tr>
			 		
			 		 <td  width="25%"></td>
			 		<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.batchsize"   bundle="${SelectedEmpAdd}"/></td>
			 		<td class="fieldLabel" width="25%"><c:out value="${scheduleDtl.batchSize}"></c:out></td>
			 		<td  width="25%"></td>
			 	</tr>
	 <tr></tr>
	 <tr></tr>	  
		<tr>
						
				<td  width="25%"></td>
				<td class="datatableheader"><hdiits:caption captionid="TR.NoOfSeats"   bundle="${allotLables}"/></td>
	 			<td class="fieldLabel" width="25%"><c:out value="${allotList.noOfSeat}"></c:out></td>
	 			<td  width="25%"></td>
	 </tr>
	 <tr></tr>
	 <tr></tr>	 
		 <tr>
						
				 	<td  width="25%"></td>		
 				<td class="datatableheader" width="25%"><hdiits:caption	captionid="TR.SCHRCVDATE"   bundle="${trschLables}"/></td>
 				<fmt:formatDate value="${scheduleDtl.schReceivedDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="rDate"/> 
		 		<td class="fieldLabel" width="25%"><c:out value="${rDate}"></c:out></td> 
		 		<td  width="25%"></td>
		</tr>
	<tr></tr>
	<tr></tr>	 
	 	<tr>
	 		
	 		<td  width="25%"></td>
	 		<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.startdt"   bundle="${SelectedEmpAdd}"/></td> 
	 		<fmt:formatDate value="${scheduleDtl.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="stDt"/>
	 		<td class="fieldLabel" width="25%"><c:out value="${stDt}"></c:out></td>
	 		<td  width="25%"></td>
		</tr>
	<tr></tr>
	<tr></tr>	 
	 	<tr>
	 	
	 		<td  width="25%"></td>	
	 	 	<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.enddt"  bundle="${SelectedEmpAdd}"/></td>
	 		<fmt:formatDate value="${scheduleDtl.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="endDate"/>
	 		<td class="fieldLabel" width="25%"><c:out value="${endDate}"></c:out></td>
	 		<td  width="25%"></td>
	 	</tr>
	 <tr></tr>
	 <tr></tr>
	 	<tr>
	 	
	 		<td  width="25%"></td>	
	 	 	<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.NMLST"  bundle="${trschLables}"/></td>
	 		<fmt:formatDate value="${scheduleDtl.nomRecLastDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="nomRecLastDate"/>
	 		<td class="fieldLabel" width="25%"><c:out value="${nomRecLastDate}"></c:out></td>
	 		<td  width="25%"></td>
	 	</tr>
	 <tr></tr>
	 <tr></tr>
	 	<tr>
					
			
			  <td  width="25%"></td>
				<c:if test="${rmks ne null}">
				 		<td class="datatableheader" width="25%"><hdiits:caption captionid="TR.REMARKS"   bundle="${trschLables}"/></td>
	 					<td class="fieldLabel" width="25%"><c:out value="${scheduleDtl.trngScheduleRemarks}"></c:out></td>	 	 			
				</c:if>
				<td  width="25%"></td>
				 			 
		</tr>
 	 <tr></tr>
	 <tr></tr>
	 	<tr>
			 	<td  width="25%"></td>
				 <td class="datatableheader" width="25%"><hdiits:caption captionid="TR.LOC"   bundle="${allotLables}"/></td>
	 			 <td class="fieldLabel" width="25%"><c:out value="${locati}"></c:out></td>	 	 			
				<td  width="25%"></td>
				 			 
		</tr>
	 	
 	 <tr></tr>
	 <tr></tr>
	  
	  	<c:if test="${m_AttachDisp eq 'Y'}">
	  
	 <tr>
	 
		<td width="25%"></td>
		<td colspan="2" width="50%">
				 <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
		                <jsp:param name="attachmentName" value="attachPrerequisites" />
		                <jsp:param name="formName" value="frmcorrnomi" />
		                <jsp:param name="attachmentType" value="Document" />
		                <jsp:param name="readOnly" value="Y"/>
                 </jsp:include>
		</td>	
		<td width="25%"></td>
	
	 </tr>
	 
	 </c:if>
	
</hdiits:form>

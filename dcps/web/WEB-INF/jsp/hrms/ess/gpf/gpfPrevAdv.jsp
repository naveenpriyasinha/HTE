 <hdiits:fieldGroup titleCaptionId="GPF.advHistory"  bundle="${gpfLables}" id="prevAdvFldGrp"  collapseOnLoad="true"> 

<table id="prevAdv"  width="100%" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse">
<tr>
	<th> 
	<hdiits:caption captionid="GPF.appDate" bundle="${gpfLables}"/>
	</th>
	<th> 
	<hdiits:caption captionid="GPF.purpose" bundle="${gpfLables}"/>
	</th> 
	<th> 
	<hdiits:caption captionid="GPF.purpDet" bundle="${gpfLables}"/>
	</th> 
	<th> 
	<hdiits:caption captionid="GPF.advamt" bundle="${gpfLables}"/>
	(<fmt:message key="GPF.INR"/>)</th>
	<th> 
	<hdiits:caption captionid="GPF.AmtSanc" bundle="${gpfLables}"/>
	(<fmt:message key="GPF.INR"/>)</th> 
	<th> 
	<hdiits:caption captionid="GPF.noOfIns" bundle="${gpfLables}"/>
	</th>
	<th> 
	<hdiits:caption captionid="GPF.splAdv" bundle="${gpfLables}"/>
	 </th>
	<th> 
	<hdiits:caption captionid="GPF.csApp" bundle="${gpfLables}"/>
	</th>
	<th> 
	<hdiits:caption captionid="GPF.agApp" bundle="${gpfLables}"/>
	</th>	
</tr>

<c:forEach var="name" items="${advHistory}">
<tr>
	<td>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${name.appDt}" var="date"/>
		${date}
	</td>
	<td>
		${name.cmnLookupMstByPurpose.lookupDesc}
	</td>
	<td>
	<script> 
		 if ("${name.cmnLookupMstByPurpose.lookupName}"=="gpf.Other") {document.write('${name.others}');}
		 else if("${name.cmnLookupMstByPurposeId.lookupDesc}"=="${name.cmnLookupMstByPurpose.lookupDesc}") document.write('-');
		else {document.write('${name.cmnLookupMstByPurposeId.lookupDesc}');}
	</script>
		
	</td>
	<td>
		<script>document.write(decimalPoint(${name.advAmt}))</script>
	</td>
	<td>
		<script>document.write(decimalPoint(${name.advSanctioned}))</script>
	</td>
	<td>
		${name.noOfInstl}
	</td>
	
	<td>
	<c:if test="${name.specialAdvance=='Y'}">
		<hdiits:caption captionid="GPF.yes" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${name.specialAdvance=='N'}">
		<hdiits:caption captionid="GPF.no" bundle="${gpfLables}"/>
		</c:if>
	</td>
	
	<td>
		<c:if test="${name.approveIn=='P'}">
		<hdiits:caption captionid="GPF.pending" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${name.approveIn=='Y'}">
			<hdiits:caption captionid="GPF.yes" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${name.approveIn=='N'}">
		<hdiits:caption captionid="GPF.no" bundle="${gpfLables}"/>
		</c:if>
		
		<c:if test="${name.approveAg=='C'}">
		<hdiits:caption captionid="GPF.cancel" bundle="${gpfLables}"/>
		</c:if>
		
		<c:if test="${name.approveAg=='R'}">
		<hdiits:caption captionid="GPF.reject" bundle="${gpfLables}"/>
		</c:if>
		
	</td>
	<td>
		<c:if test="${name.approveAg=='P'}">
		<hdiits:caption captionid="GPF.pending" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${name.approveAg=='Y'}">
		<hdiits:caption captionid="GPF.yes" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${name.approveAg=='N'}">
		<hdiits:caption captionid="GPF.no" bundle="${gpfLables}"/>
		</c:if>
		
		<c:if test="${name.approveAg=='C'}">
		<hdiits:caption captionid="GPF.cancel" bundle="${gpfLables}"/>
		</c:if>
		
		<c:if test="${name.approveAg=='R'}">
		<hdiits:caption captionid="GPF.reject" bundle="${gpfLables}"/>
		</c:if>
	</td>
</tr>
</c:forEach>
</table>

</hdiits:fieldGroup>
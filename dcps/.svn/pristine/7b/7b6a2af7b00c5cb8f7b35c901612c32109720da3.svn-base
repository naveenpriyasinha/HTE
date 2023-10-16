 <hdiits:fieldGroup titleCaptionId="GPF.withHst"  bundle="${gpfLables}" id="prevWithFldGrp"  collapseOnLoad="true"> 

<table id="prevAdv"  width="100%" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse" >
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
	<hdiits:caption captionid="GPF.WithType" bundle="${gpfLables}"/>
	</th>
	<th>
	<hdiits:caption captionid="GPF.AmtWithn" bundle="${gpfLables}"/>
	(<fmt:message key="GPF.INR"/>)</th>
	<th> 
	<hdiits:caption captionid="GPF.AmtSanc" bundle="${gpfLables}"/>
	(<fmt:message key="GPF.INR"/>)</th>
	<th> 
	<hdiits:caption captionid="GPF.splCase" bundle="${gpfLables}"/>
	</th> 
	<th style="display: none;"> 
	<hdiits:caption captionid="GPF.accOfficer" bundle="${gpfLables}"/>
	</th> 
	<th> 
	<hdiits:caption captionid="GPF.csApp" bundle="${gpfLables}"/>
	</th>
	<th> 
	<hdiits:caption captionid="GPF.agApp" bundle="${gpfLables}"/>
	</th>	
</tr>

<c:forEach var="name" items="${withHistory}">
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
	if("${name.cmnLookupMstByPurpose.lookupName}"=="gpf.Others_w")	document.write('${name.other}');
	else if("${name.cmnLookupMstByPurposeId.lookupDesc}"=="") document.write('-');
	
	</script>
		${name.cmnLookupMstByPurposeId.lookupDesc}
	</td>
	<td>
			<c:if test="${name.withdrawalType=='Part'}">
  				<hdiits:caption captionid="GPF.part" bundle="${gpfLables}"/>
  			</c:if>
  			<c:if test="${name.withdrawalType=='Final'}">
  				<hdiits:caption captionid="GPF.final" bundle="${gpfLables}"/>
  			</c:if>
	</td>
	
	<td>
		<script>document.write(decimalPoint(${name.amtWithdrawn}))</script>
	</td>
	<td>
		<script>document.write(decimalPoint(${name.amountSanctioned}))</script>
	</td>
	<td>
		<c:if test="${name.special=='Y'}">
		<hdiits:caption captionid="GPF.yes" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${name.special=='N'}">
		<hdiits:caption captionid="GPF.no" bundle="${gpfLables}"/>
		</c:if>
	</td>
	<td style="display: none;">
		${name.accountOfficer}
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
		<c:if test="${name.approveIn=='C'}">
		<hdiits:caption captionid="GPF.cancel" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${name.approveIn=='R'}">
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
<c:set var="majorPunish" value="${resValue.majorPunish}" > </c:set>	
<c:set var="majorPunishType" value="${resValue.majorPunishType}" > </c:set>	


<hdiits:fieldGroup titleCaptionId="dept.generalDtls" bundle="${deptLables}" id="majorPunish3">


<table width="100%" style="display:none">
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.nameDelinq" bundle="${deptLables}"/>
		</td>

		<td width="25%">
			<hdiits:text name="nameDelinq" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>


		<td width="25%">
			<hdiits:caption captionid="dept.sourceInq" bundle="${deptLables}"/>
		</td>


		<td width="25%">
			<hdiits:text name="sourceInq" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>

	</tr>
	
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.inqReportSubDt" bundle="${deptLables}"/>
		</td>

		<td width="25%">
			<hdiits:dateTime name="subInqReportDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.inqReportSubDt" bundle="${deptLables}"/>
		</td>


		<td width="25%">
			<hdiits:caption captionid="dept.decisionTakenDt" bundle="${deptLables}"/>
		</td>


		<td width="25%">
			<hdiits:dateTime name="decisionDate"  maxvalue="31/12/2099 00:00:00" captionid="dept.decisionTakenDt" bundle="${deptLables}"/>
		</td>

	</tr>
	
</table>
</hdiits:fieldGroup>

<table width="100%" border="1" id="txnAdd2" style="display:none">
<tr>
	<td align="center"> <hdiits:caption captionid="dept.chargeNo" bundle="${deptLables}"/> </td>
	<td align="center"> <hdiits:caption captionid="dept.repFind" bundle="${deptLables}"/></td>
	<td align="center"> <hdiits:caption captionid="dept.inqRepDtls" bundle="${deptLables}"/></td>
	<td align="center"> <hdiits:caption captionid="dept.discAuthorityFind" bundle="${deptLables}"/></td>
</tr>

<tr>
	<td align="center" id="chargeNo2"></td>
	<td align="center" id="repFind2"></td>
	<td align="center" id="inqRepDtls2"></td>
	<td align="center" id="discAuthorityFind2"></td>	
</tr>

</table>


<hdiits:fieldGroup titleCaptionId="dept.tmajorPunishmnt" bundle="${deptLables}" id="majorPunish1">
<table width="100%">
<tr>
	
		<td> <hdiits:caption captionid="dept.punish" bundle="${deptLables}"/> </td>
		
		<td>
		
		  <hdiits:select name="punishMajorPunish" sort="false" >	
 				
			<option id="a" value="Select"><hdiits:caption captionid="dept.select" bundle="${deptLables}"/></option>
	
				<c:forEach var="name" items="${majorPunish}">	
				
				<c:choose>
						 <c:when test="${name.lookupName == majorDtls.punishmentType.lookupName}">
						 <option  name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
		    			 <c:out value="${name.lookupDesc}"/></option>
		    			 </c:when>
		   			
			   			 <c:otherwise>
    		   			<option id="opt" name="opt" value="<c:out value="${name.lookupName}"/>"  >
    					<c:out value="${name.lookupDesc}"/></option>
    					</c:otherwise>
    			</c:choose>
				</c:forEach>
	
 			</hdiits:select>
		</td>
		
		<td width="25%"> </td>
		<td width="25%"> </td>
	</tr>
	
	<tr>	
		<td width="25%"><hdiits:caption captionid="dept.amt" bundle="${deptLables}"/></td>
		<td width="25%"> <hdiits:number name="amtMajorPunish" default="${majorDtls.amount}" onblur="checkIsNumber(this)" maxlength="8" size="20"/> </td>
		<td width="25%">  <hdiits:caption captionid="dept.period" bundle="${deptLables}"/>  :   <hdiits:caption captionid="dept.yrs" bundle="${deptLables}"/></td>
		<td width="25%"> <hdiits:number name="yrsMajorPunish" default="${majorDtls.period}" onblur="checkIsNumber(this)" maxlength="2" size="20"/> </td>
	</tr>
	
	<tr>
		<td width="25%"> </td>
		<td width="25%"> </td>
		<td width="25%"> <hdiits:caption captionid="dept.punish" bundle="${deptLables}"/><hdiits:caption captionid="dept.mnths" bundle="${deptLables}"/></td>
		<td width="25%"> <hdiits:number name="mnthsMajorPunish" default="${majorDtls.months}" onblur="checkIsNumber(this);validateMonths(this);" maxlength="2" size="20"/></td>
	</tr>
	
	
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="dept.dltsPunish" bundle="${deptLables}" id="majorPunish2">

<table width="100%" >

<tr>
		<td width=25%>
			<hdiits:caption captionid="dept.dltsPunish" bundle="${deptLables}"/>
		</td>
		
		<td width=25%>
			<hdiits:textarea name="detailsPunish" default="${majorDtls.punishmentDetail}" onblur="restrictSplChar(this);"  maxlength="105"  rows="3" cols="25">
			</hdiits:textarea>
		</td>
		
		<td width=25%>
			<hdiits:caption captionid="dept.decisionDt" bundle="${deptLables}"/>
		</td>
		
		<td width=25%>
			<hdiits:dateTime name="decisionFinalDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.decisionDt" bundle="${deptLables}"/>
		</td>
	</tr>
	
	<tr>
		<td>	<hdiits:caption captionid="dept.finalDecisionDt" bundle="${deptLables}"/></td>
		<td>	<hdiits:dateTime name="issueFinalDecDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.finalDecisionDt" bundle="${deptLables}"/> </td>
	
		<td>	<hdiits:caption captionid="dept.punishmntType" bundle="${deptLables}"/></td>
		<td>	
			<hdiits:select name="typePunishMajor" sort="false">	
				<option value="Select" ><fmt:message key="dept.select"/></option>
					<c:forEach var="name" items="${majorPunishType}">	
						<c:choose>
						<c:when test="${name.lookupName == majorDtls.typeMajor.lookupName}">
						 <option value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
    					 <c:out value="${name.lookupDesc}"/></option>
    					 </c:when>
	   			
						 <c:otherwise>
						<option value="${name.lookupName}">
						 <c:out value="${name.lookupDesc}"/></option>	
	   					 </c:otherwise>
	   					</c:choose>
	    											
						</c:forEach>
	     		</hdiits:select>
		</td>
	</tr>
	
	<tr>
		<td>	<hdiits:caption captionid="dept.showCauseNoticeDt" bundle="${deptLables}"/></td>
		<td><hdiits:dateTime name="showCauseDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.showCauseNoticeDt" bundle="${deptLables}"/>	</td>
	
		<td>	<hdiits:caption captionid="dept.showCauseReplyDt" bundle="${deptLables}"/></td>
		<td>	<hdiits:dateTime name="showCauseReplyDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.showCauseReplyDt" bundle="${deptLables}"/></td>
	</tr>
	
	<tr>
		<td>	<hdiits:caption captionid="dept.showCauseExtension" bundle="${deptLables}"/></td>
		<td>
				<hdiits:radio name="extensionShowCause" value="Y" onclick="extensionStatus();" bundle="${deptLables}" captionid="dept.y" />
				<hdiits:radio name="extensionShowCause" value="N" onclick="extensionStatus();" bundle="${deptLables}" captionid="dept.n" />
		</td>
	</tr>
	
	
	<tr id="extension" style="display: none">			
		<td>	<hdiits:caption captionid="dept.frmDt" bundle="${deptLables}"/></td>
		<td>	<hdiits:dateTime name="frmDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.frmDt" bundle="${deptLables}" onblur="dateFromToValidate();"/> </td>
		<td>	<hdiits:caption captionid="dept.toDt" bundle="${deptLables}"/></td>
		<td>	<hdiits:dateTime name="toDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.toDt" bundle="${deptLables}" onblur="dateFromToValidate();"/> </td>
	</tr>

	<tr>
		<td> <hdiits:caption captionid="dept.oralHearingSought" bundle="${deptLables}"/></td>
		<td> 
				<hdiits:radio name="oralHearingSought" value="Y" onclick="oralStatus();" bundle="${deptLables}" captionid="dept.y" />
				<hdiits:radio name="oralHearingSought" value="N" onclick="oralStatus();" bundle="${deptLables}" captionid="dept.n" />
		</td>
	</tr>
	
		
	<tr id="oralHearing" style="display: none">
		<td> <hdiits:caption captionid="dept.oralHearingDt" bundle="${deptLables}"/></td>
		<td> <hdiits:dateTime name="oralHearingDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.oralHearingDt" bundle="${deptLables}"/> </td>			
	</tr>
</table>

</hdiits:fieldGroup>

<script>
hideMajorPunish();
setDefaultMajor();
makeMajorDtReadOnly();

function setDefaultMajor()
{
	<fmt:formatDate pattern="dd/MM/yyyy" value="${majorDtls.oralHearingDate}" var="oralHearingDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${majorDtls.extToDate}" var="extToDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${majorDtls.extFromDate}" var="extFromDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${majorDtls.replyShowCaseDate}" var="replyShowCaseDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${majorDtls.showCaseNoticeDate}" var="showCaseNoticeDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${majorDtls.issueFinalDecisionDate}" var="issueFinalDecisionDate"/>
	
	document.inquiryCaseTracking.oralHearingDt.value="${oralHearingDate}";
	document.inquiryCaseTracking.toDt.value="${extToDate}";
	document.inquiryCaseTracking.frmDt.value="${extFromDate}";
	document.inquiryCaseTracking.showCauseReplyDt.value="${replyShowCaseDate}";
	document.inquiryCaseTracking.showCauseDt.value="${showCaseNoticeDate}";
	document.inquiryCaseTracking.decisionFinalDt.value="${issueFinalDecisionDate}";
	
	if("${majorDtls.isExtShowCaseNotice}"=="Y")
	{
		document.inquiryCaseTracking.extensionShowCause[0].checked=true;
		document.getElementById("extension").style.display='';	
	}
	else if("${majorDtls.isExtShowCaseNotice}"=="N")
	{
		document.inquiryCaseTracking.extensionShowCause[1].checked=true;
	}
	
	if("${majorDtls.isOralHearing}"=="Y")
	{
		document.inquiryCaseTracking.oralHearingSought[0].checked=true;
		document.getElementById("oralHearing").style.display='';
	}
	else if("${majorDtls.isOralHearing}"=="N")
	{
		document.inquiryCaseTracking.oralHearingSought[1].checked=true;
	}
	
	if("${objHrDeptEnqDtl.rule.lookupName}"=='dept_rule11')
	{
		displayMajorPunish();
	}
}


</script>


<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		//initializetabcontent("maintab")
</script>
		

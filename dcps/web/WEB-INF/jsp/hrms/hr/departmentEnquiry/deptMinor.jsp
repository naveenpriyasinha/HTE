
<c:set var="minorPunish" value="${resValue.minorPunish}" > </c:set>	

<table width="100%" id="minorPunish3" style="display:none">

<tr bgcolor="#386CB7">
	<td class="fieldLabel" colspan="4" align="center">
	<font color="#ffffff">
		<strong><u><hdiits:caption captionid="dept.generalDtls" bundle="${deptLables}"/></u></strong>
	</font>
	</td>
</tr>

</table>

<table width="100%" style="display:none">
	<tr>
		
		<td width="25%">
			<hdiits:text name="nameDelinquent" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>


	
		<td width="25%">
			<hdiits:text name="sourceInquiry" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>

	</tr>
	
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.inqReportSubDt" bundle="${deptLables}"/>
		</td>

		<td width="25%">
			<hdiits:dateTime name="subInquiryReportDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.inqReportSubDt" bundle="${deptLables}"/>
		</td>


		<td width="25%">
			<hdiits:caption captionid="dept.decisionTakenDt" bundle="${deptLables}"/>
		</td>


		<td width="25%">
			<hdiits:dateTime name="decisionDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.decisionTakenDt" bundle="${deptLables}"/>
		</td>

	</tr>
</table>

<table width="100%" border="1" id="txnAdd3" style="display:none">
<tr>
	<td align="center"> <hdiits:caption captionid="dept.chargeNo" bundle="${deptLables}"/> </td>
	<td align="center"> <hdiits:caption captionid="dept.repFind" bundle="${deptLables}"/></td>
	<td align="center"> <hdiits:caption captionid="dept.inqRepDtls" bundle="${deptLables}"/></td>
	<td align="center"> <hdiits:caption captionid="dept.discAuthorityFind" bundle="${deptLables}"/></td>
</tr>

<tr>
	<td align="center"  id="chargeNo" ></td>
	<td align="center" id="repFind"></td>
	<td align="center" id="inqRepDtls"></td>
	<td align="center" id="discAuthorityFind"></td>	
</tr>
</table>

<hdiits:fieldGroup titleCaptionId="dept.minorPunishmnt" bundle="${deptLables}" id="minorPunish1">
	
<table width="100%">
<tr>
		<td width=25%>	<hdiits:caption captionid="dept.punish" bundle="${deptLables}"/> </td>
		<td width=25%> 
			<hdiits:select name="minorPunish" id="minorPunish" sort="false" >
					<option value="Select" ><fmt:message key="dept.select"/></option>
						<c:forEach var="minorPunish" items="${minorPunish}">	
		
						<c:choose>
						 <c:when test="${minorPunish.lookupName == minorDtls.punishType.lookupName}">
						 <option value="<c:out value="${minorPunish.lookupName}"/>"  selected="selected"> 
    					 <c:out value="${minorPunish.lookupDesc}"/></option>
    					 </c:when>
	   			
						 <c:otherwise>
						 <option value="${minorPunish.lookupName}">
						 <c:out value="${minorPunish.lookupDesc}"/></option>	
	   					 </c:otherwise>
	   					</c:choose>
	    											
						</c:forEach>
	     		</hdiits:select>
		</td>
		<td width="25%"> </td>
		<td width="25%"> </td>
	</tr>
	
	<tr>
		<td width=25%><hdiits:caption captionid="dept.inc" bundle="${deptLables}"/>	</td>
		<td width=25%> <hdiits:number name="incMinorPunish" default="${minorDtls.increments}" onblur="checkIsNumber(this)"  maxlength="3" size="20"/>	</td>
		<td width="25%"> <hdiits:caption captionid="dept.yrs" bundle="${deptLables}"/> </td>
		<td width="25%"> <hdiits:number name="yrsMinorPunish" default="${minorDtls.years}" onblur="checkIsNumber(this)" maxlength="2" size="20"/> </td>
	</tr>
	
	<tr>
		<td width=25%><hdiits:caption captionid="dept.mnths" bundle="${deptLables}"/>	</td>
		<td width=25%> <hdiits:number name="mntsMinorPunish" default="${minorDtls.months}" onblur="checkIsNumber(this);validateMonths(this);" maxlength="2" size="20"/>	</td>
		<td width="25%"><hdiits:caption captionid="dept.futureEff" bundle="${deptLables}"/></td>
		<td width="25%"> 
			<hdiits:radio name="futureEffMinor" value="With" bundle="${deptLables}" captionid="dept.with"/>
			<hdiits:radio name="futureEffMinor" value="Without" bundle="${deptLables}" captionid="dept.without"/>
		</td>
	</tr>
	
	<tr>
		<td width=25%><hdiits:caption captionid="dept.amtInstall" bundle="${deptLables}"/></td>
		<td width=25%> <hdiits:number name="amtInstalllMinorPunish" default="${minorDtls.installmentAmount}" onblur="checkIsNumber(this)" maxlength="3" size="20"/>	</td>
		<td width="25%"> <hdiits:caption captionid="dept.noInstall" bundle="${deptLables}"/></td>
		<td width="25%"> <hdiits:number name="noInstallMinorPunish" default="${minorDtls.installmentNo}" onblur="checkIsNumber(this)" maxlength="3" size="20"/> </td>
	</tr>
</table>
</hdiits:fieldGroup>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
		
<script>
hideMinorPunish();
defaultMinorDisplay();

var futureEff="${minorDtls.futureEffects}";
var ruleName="${objHrDeptEnqDtl.rule.lookupName}";

</script>

<%
try {
%>

<c:set var="objHrDeptEnqDtl" value="${resValue.objHrDeptEnqDtl}" > </c:set>	 
<c:set var="deptRule" value="${resValue.deptRule}" > </c:set>	
<c:set var="sourceOfEnquiry" value="${resValue.sourceOfEnquiry}"></c:set>
<c:set var="minorDtls" value="${resValue.minorDtls}" > </c:set>	
<c:set var="majorDtls" value="${resValue.majorDtls}" > </c:set>	
<c:set var="objHrDeptenqoffCmnContact" value="${resValue.objHrDeptenqoffCmnContact}" > </c:set>	
<c:set var="objHrDeptenqoffCmnPerson" value="${resValue.objHrDeptenqoffCmnPerson}" > </c:set>	
<c:set var="objHrDeptpresenoffCmnContact" value="${resValue.objHrDeptpresenoffCmnContact}" > </c:set>	
<c:set var="objHrDeptpresenoffCmnPerson" value="${resValue.objHrDeptpresenoffCmnPerson}" > </c:set>	
<c:set var="objHrDeptpresidoffCmnContact" value="${resValue.objHrDeptpresidoffCmnContact}" > </c:set>	
<c:set var="objHrDeptpresidoffCmnPerson" value="${resValue.objHrDeptpresidoffCmnPerson}" > </c:set>	


<table width="100%" id="deptTab1" style="display:none">
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.inqCaseNo" bundle="${deptLables}"/>
		</td>
	
		<td width="25%">
			<hdiits:number name="inquiryCaseNo" default="${objHrDeptEnqDtl.inqCaseNo}" onblur="checkIsNumber(this)"  maxlength="15"/>
		</td>
	
		<td width="25%">
			<hdiits:caption captionid="dept.sourceInq" bundle="${deptLables}"/>
		</td>
	
	
		<td>	
		<hdiits:select name="source" id="source" sort="false">
			<option id="a" value="Select"><fmt:message key="dept.select"/></option>
			<c:set var="lookup" value="${objHrDeptEnqDtl.sourceEnquiry.lookupName}"> </c:set>
				<c:forEach var="name" items="${sourceOfEnquiry}">						
    			<c:choose>
					<c:when test="${name.lookupName == lookup}">
				 <option value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
    			 <c:out value="${name.lookupDesc}"/></option>
				</c:when>
						
				<c:otherwise>
    			<option id="opt" name="opt" value="<c:out value="${name.lookupName}"/>"  >
    			<c:out value="${name.lookupDesc}"/>
	    		</c:otherwise>
						
				</c:choose>		
				</c:forEach>
		</hdiits:select>	
		</td>
	</tr>

	<tr>
		<td width="25%">
		<hdiits:caption captionid="dept.inqFileNo" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="inquiryFileNo" default="${objHrDeptEnqDtl.inqFileNo}" onblur="checkIsNumber(this)"  maxlength="15"/>
		</td>
		
		<td width="25%">
		<hdiits:caption captionid="dept.deptName" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:text name="deptName" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15" default="${objHrDeptEnqDtl.department}"/>
		</td>
	</tr>
</table>

<hdiits:fieldGroup titleCaptionId="dept.inqOffDtls" bundle="${deptLables}" id="deptTab2">

<table width="50%" id="deptTab3" style="display:none"> 
	<tr>
		<td align="left" width="25%"><hdiits:caption captionid="dept.officerInDept" bundle="${deptLables}"/> </td>
		<td width="25%">
		<hdiits:radio name="flagEnqOfficer" value="Y"  bundle="${deptLables}" captionid="dept.y" onclick="showEnqOfficerUserId()" />
		<hdiits:radio name="flagEnqOfficer"	value="N"  bundle="${deptLables}" captionid="dept.n" onclick="showEnqOfficerDtl()" />
		</td>
	</tr>
</table>
						
<table id="enqOfficerDtl" width="100%" style="display:none">

	<tr>
		<td width=25%>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.lastName" bundle="${deptLables}"/>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.firstName" bundle="${deptLables}"/>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.midName" bundle="${deptLables}"/>	</td>
		
	</tr>
						
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.inqOffName" bundle="${deptLables}"/>
		</td>
		<td width="25%">
			<hdiits:text name="inqOffNameLastDept" default="${objHrDeptenqoffCmnPerson.lastName}" mandatory="true"  onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width="25%">
			<hdiits:text name="inqOffNameDept" default="${objHrDeptenqoffCmnPerson.firstName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
		<td width="25%">
			<hdiits:text name="inqOffNameMidDept" default="${objHrDeptenqoffCmnPerson.middleName}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
	</tr>
	
	<tr>
		<td width=25%>	</td>

		<td width=25%>	<hdiits:caption captionid="dept.office" bundle="${deptLables}"/>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.residence" bundle="${deptLables}"/></td>
		<td width=25%>	<hdiits:caption captionid="dept.mobile" bundle="${deptLables}"/>	</td>
	</tr>
	
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.phone" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="phoneOff" default="${objHrDeptenqoffCmnContact.officePhone}" mandatory="true" onblur="checkIsNumber(this);checkPhoneLength(this);"  maxlength="15" size="20"/>
		</td>
		
		<td width="25%">
			 <hdiits:number name="phoneRes" default="${objHrDeptenqoffCmnContact.residencePhone}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="phoneMobDept" default="${objHrDeptenqoffCmnContact.mobile}" onblur="checkIsNumber(this);checkMobileLength(this);" maxlength="10" size="20"/>
		</td>
		
	</tr>
	
		<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.designation" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:text name="inqOffDesigDept"  default="${objHrDeptEnqDtl.inqOffDsgn}" onblur="restrictSplChar(this);"  maxlength="15"/>
		</td>
		<td width="25%">
			<hdiits:caption captionid="dept.AppointDt" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:dateTime name="appInquiryOffDate" captionid="dept.AppointDt"  maxvalue="31/12/2099 00:00:00" bundle="${deptLables}"/>
		</td>
	</tr>
</table>
	
<table id="EnqOfficerUserId"  width="100%" style="display:none">
						
	<tr>
		<td width="25%" >
			<hdiits:caption captionid="dept.inqOffUserId" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="inqOffUserId" id="user_id3" default="${objHrDeptEnqDtl.orgUserMstByEnqOfficerUserId.userId}" onblur="checkIsNumber(this)"  readonly="true" mandatory="true"/>
			<hdiits:image id="Search3" source="images/search_icon.gif" onclick="SearchEmp(this);"> </hdiits:image>
		</td>

		<td width="25%"></td>
		
		<td width="25%"></td>
		
	</tr>
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="dept.presentOffDtls" bundle="${deptLables}" id="presenOfficerDtlLab">

<table width="50%"  id="deptTab4" style="display:none">
	<tr>
		<td align="left" width="25%"><hdiits:caption captionid="dept.officerInDept" bundle="${deptLables}"/> </td>
		<td  width="25%">
		<hdiits:radio name="flagPresenOfficer" validation="sel.isradio" value="Y" bundle="${deptLables}" captionid="dept.y" onclick="showPresenOfficerUserId()" />
		<hdiits:radio name="flagPresenOfficer" validation="sel.isradio" value="N" bundle="${deptLables}" captionid="dept.n" onclick="showPresenOfficerDtl()" />
		</td>
	</tr>
</table>
	
<table width="100%" id="presenOfficerDtl" style="display:none">

	<tr>
		<td width=25%>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.lastName" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.firstName" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.midName" bundle="${deptLables}"/>
		</td>
		
	</tr>
	

	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.Name" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:text name="presentOffLastName" default="${objHrDeptpresenoffCmnPerson.lastName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width="25%">
			<hdiits:text name="presentOffName" default="${objHrDeptpresenoffCmnPerson.firstName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
		<td width="25%">
			<hdiits:text name="presentOffMidName" default="${objHrDeptpresenoffCmnPerson.middleName}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
	</tr>

	<tr>
	
		<td width=25%>	</td>

		<td width=25%>	<hdiits:caption captionid="dept.office" bundle="${deptLables}"/>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.residence" bundle="${deptLables}"/></td>
		<td width=25%>	<hdiits:caption captionid="dept.mobile" bundle="${deptLables}"/>	</td>
	</tr>

	<tr>
		<td  width="25%">
			<hdiits:caption captionid="dept.presOffPhone" bundle="${deptLables}"/>
		</td>
		
		<td  width="25%">
			<hdiits:number name="phoneNoPresentOff" default="${objHrDeptpresenoffCmnContact.officePhone}" mandatory="true" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="phoneNoPresentRes" default="${objHrDeptpresenoffCmnContact.residencePhone}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		
		
		<td width="25%">
			<hdiits:number name="phoneNoPresentMob"  default="${objHrDeptpresenoffCmnContact.mobile}" onblur="checkIsNumber(this);checkMobileLength(this);" maxlength="10" size="20"/>
		</td>
	</tr>

	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.designation" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:text name="presentOffDesig" default="${objHrDeptEnqDtl.presenOffDsgn}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>

		<td  width="25%">
			<hdiits:caption captionid="dept.AppointDt" bundle="${deptLables}"/>
		</td>
		
		<td  width="25%">
			<hdiits:dateTime name="appPresentOffDt" captionid="dept.AppointDt"  maxvalue="31/12/2099 00:00:00" bundle="${deptLables}"/>
		</td>
	</tr>

		
		
</table>
		
<table width="100%" id="presenOfficerUserId" style="display:none">
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.presntOffUserId" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="presentOffUserId"  id="user_id4" default="${objHrDeptEnqDtl.orgUserMstByPresenOfficerUserId.userId}" onblur="checkIsNumber(this)"  mandatory="true" readonly="true"/>
			<hdiits:image id="Search4" source="images/search_icon.gif" onclick="SearchEmp(this);"> </hdiits:image>
		</td>

		<td width="25%">
		</td>		
		<td width="25%"></td>
	</tr>	
</table>
	</hdiits:fieldGroup>	

<hdiits:fieldGroup titleCaptionId="dept.presidOffDtls" bundle="${deptLables}" id="deptTab6">

<table width="50%"  id="deptTab7" style="display:none">
	<tr>
		<td align="left" width="25%"><hdiits:caption captionid="dept.officerInDept" bundle="${deptLables}"/> </td>
		<td  width="25%" >
			<hdiits:radio name="flagPresidOfficer" validation="sel.isradio" value="Y" bundle="${deptLables}" captionid="dept.y" onclick="showPresidOfficerUserId()" />
			<hdiits:radio name="flagPresidOfficer" validation="sel.isradio" value="N" bundle="${deptLables}" captionid="dept.n" onclick="showPresidOfficerDtl()" />
			
		</td>
								
	</tr>
</table>

<table width="100%" id="presidOfficerDtl" style="display:none">

	<tr>
		<td width=25%>
		</td>

		<td width=25%>
		<hdiits:caption captionid="dept.firstName" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.midName" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.lastName" bundle="${deptLables}"/>
		</td>
	</tr>

	<tr>
		<td width=25%><hdiits:caption captionid="dept.Name" bundle="${deptLables}"/></td>
		<td width=25%> <hdiits:text name="presOffLastName" default="${objHrDeptpresidoffCmnPerson.lastName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/> </td>	
		<td width=25%> <hdiits:text name="presOffName" default="${objHrDeptpresidoffCmnPerson.firstName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/> </td>
		<td width=25%> <hdiits:text name="presOffMidName" default="${objHrDeptpresidoffCmnPerson.middleName}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/> </td>
	</tr>
	
	<tr>
		<td width=25%>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.office" bundle="${deptLables}"/>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.residence" bundle="${deptLables}"/></td>
		<td width=25%>	<hdiits:caption captionid="dept.mobile" bundle="${deptLables}"/>	</td>
	</tr>

	<tr>
		<td  width="25%">
			<hdiits:caption captionid="dept.presOffPhone" bundle="${deptLables}"/>
		</td>
		
		<td  width="25%">
			<hdiits:number name="phoneNoPresOff" default="${objHrDeptpresidoffCmnContact.officePhone}" mandatory="true" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="phoneNoPresRes" default="${objHrDeptpresidoffCmnContact.residencePhone}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		
		
		<td width="25%">
			<hdiits:number name="phoneNoPresMob" default="${objHrDeptpresidoffCmnContact.mobile}" onblur="checkIsNumber(this);checkMobileLength(this);" maxlength="10" size="20"/>
		</td>
	</tr>
	
	<tr>
		<td width=25%><hdiits:caption captionid="dept.designation" bundle="${deptLables}"/> </td>
		<td width=25%> <hdiits:text name="designation" default="${objHrDeptEnqDtl.presidOffDsgn}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/> </td>
		<td width=25%> <hdiits:caption captionid="dept.AppointDt" bundle="${deptLables}"/></td>
		<td width=25%> <hdiits:dateTime name="presOffAppDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.AppointDt" bundle="${deptLables}"/> </td>
	</tr>
</table>
	
<table width="100%" id="presidOfficerUserId" style="display:none">
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.presOffUserId" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:number name="presidOffUserId" id="user_id5" default="${objHrDeptEnqDtl.orgUserMstByPresidOfficerUserId.userId}" onblur="checkIsNumber(this)"  readonly="true"  mandatory="true"/>
			<hdiits:image id="Search5" source="images/search_icon.gif" onclick="SearchEmp(this);"> </hdiits:image>
			
		</td>
	
	
		<td width="25%">
		</td>
				
		<td width="25%"></td>
	</tr>
</table>

<table width="100%"  id="deptTab5" style="display:none">
	<tr>
		<td width="25%">
			<hdiits:caption captionid="dept.caseHandedOverDt" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:dateTime name="caseHandedOverDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.caseHandedOverDt" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:caption captionid="dept.inqReportSubDt" bundle="${deptLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:dateTime name="inquiryReportSubDt"  maxvalue="31/12/2099 00:00:00" captionid="dept.inqReportSubDt" bundle="${deptLables}"/>
		</td>
	</tr>
</table>

</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="dept.decisionDtls" bundle="${deptLables}" id="deptTab9">

<table width="50%"  id="deptTab8" style="display:none">
		<tr>
			<td align="left" width="25%"><hdiits:caption captionid="dept.IsDecisionTaken" bundle="${deptLables}"/>  </td>
			<td  width="25%" >
			<hdiits:radio name="decisionFlag" value="Y" bundle="${deptLables}" captionid="dept.y"	onclick="showRuleDtl()" />
			<hdiits:radio name="decisionFlag" value="N" bundle="${deptLables}" captionid="dept.n" onclick="hideRuleDtl()" />
			</td>
		</tr>
</table>


<table width="50%" id="decision" style="display:none">
		<tr>
			<td width="25%"><hdiits:caption captionid="dept.ruleUnder" bundle="${deptLables}"/></td>
			<td width="25%">						
				<hdiits:select name="deptRule" id="deptRule" sort="false" onchange="displayOffence()">
					<option value="Select" >----<fmt:message key="dept.select"/>----</option>
					<c:forEach var="deptRule" items="${deptRule}">	
		
					<c:choose>
					 <c:when test="${deptRule.lookupName == objHrDeptEnqDtl.rule.lookupName}">
						 <option value="<c:out value="${deptRule.lookupName}"/>"  selected="selected"> 
    					 <c:out value="${deptRule.lookupDesc}"/></option>
    				 </c:when>
	   			
					 <c:otherwise>
						 <option value="${deptRule.lookupName}">
						 <c:out value="${deptRule.lookupDesc}"/></option>	
	   				 </c:otherwise>
	   				 </c:choose>
	    											
						</c:forEach>
	     		</hdiits:select>
	     		
				</td>
			</tr>
</table>
</hdiits:fieldGroup>

<table id="minor" width="100%" style="display:none">
	<tr>
		<td>	
		<%@ include  file="../departmentEnquiry/deptMinor.jsp"%>
		</td>
	</tr>
</table>


<table id="major" width="100%" style="display:none">
	<tr>
		<td>	
			<%@ include  file="../departmentEnquiry/deptMajor.jsp"%>
		</td>
	</tr>
</table>

<hdiits:hidden name="decisionFlagHid" default="${objHrDeptEnqDtl.isDecisionTaken}"/>	



<script>
hideAllDept();
makeDeptDateReadOnly();
defaultDeptDisplay();	


	function defaultDeptDisplay()
	{
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrDeptEnqDtl.inqOffAppDate}" var="appInquiryOffDate"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrDeptEnqDtl.presenOffAppDate}" var="appPresentOffDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrDeptEnqDtl.caseHandedOverDate}" var="caseHandedOverDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrDeptEnqDtl.reportSubDate}" var="inquiryReportSubDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrDeptEnqDtl.presidOffAppDate}" var="presOffAppDt"/>
		
	document.inquiryCaseTracking.appInquiryOffDate.value="${appInquiryOffDate}";
	document.inquiryCaseTracking.appPresentOffDt.value="${appPresentOffDt}";
	document.inquiryCaseTracking.caseHandedOverDt.value="${caseHandedOverDt}";
	document.inquiryCaseTracking.inquiryReportSubDt.value="${inquiryReportSubDt}";
	document.inquiryCaseTracking.presOffAppDt.value="${presOffAppDt}";	
	
	if("${objHrDeptEnqDtl.orgUserMstByEnqOfficerUserId.userId}"!='')
		{
		document.inquiryCaseTracking.flagEnqOfficer[0].checked=true;
		}
	else if("${objHrDeptEnqDtl.orgUserMstByEnqOfficerUserId.userId}"=='')
		{
		document.inquiryCaseTracking.flagEnqOfficer[1].checked=true;	
		}
	
	if("${objHrDeptEnqDtl.orgUserMstByPresenOfficerUserId.userId}"!='')
		{
		document.inquiryCaseTracking.flagPresenOfficer[0].checked=true;	
		}
	else if("${objHrDeptEnqDtl.orgUserMstByPresenOfficerUserId.userId}"=='')
		{
		document.inquiryCaseTracking.flagPresenOfficer[1].checked=true;	
		}	
		
	if("${objHrDeptEnqDtl.orgUserMstByPresidOfficerUserId.userId}"!='')
		{
		document.inquiryCaseTracking.flagPresidOfficer[0].checked=true;	
		}
	else if("${objHrDeptEnqDtl.orgUserMstByPresidOfficerUserId.userId}"=='')
		{
		document.inquiryCaseTracking.flagPresidOfficer[1].checked=true;	
		}	
		
	if("${objHrDeptEnqDtl.isDecisionTaken}"=='Y')
		{
		document.inquiryCaseTracking.decisionFlag[0].checked=true;	

		}
	else if("${objHrDeptEnqDtl.isDecisionTaken}"=='N')
		{
		document.inquiryCaseTracking.decisionFlag[1].checked=true;	
		}	
	}
	
		
	function displayAllDeptFrmDB()
	{
	if("${objHrDeptEnqDtl.orgUserMstByEnqOfficerUserId.userId}"!='')
		{
		document.getElementById("EnqOfficerUserId").style.display="";
		}
	else 
		{
		document.getElementById("enqOfficerDtl").style.display="";
		}
	
	if("${objHrDeptEnqDtl.orgUserMstByPresenOfficerUserId.userId}"!='')
		{
		document.getElementById("presenOfficerUserId").style.display="";
		}
	else 
		{
		document.getElementById("presenOfficerDtl").style.display="";
		}	
		
	if("${objHrDeptEnqDtl.orgUserMstByPresidOfficerUserId.userId}"!='')
		{
		document.getElementById("presidOfficerUserId").style.display="";
		}
	else 
		{
		document.getElementById("presidOfficerDtl").style.display="";
		}
			
	if("${ empty minorDtls}"=="false")
		{
		document.getElementById("minor").style.display='';
		}
		
	if("${ empty majorDtls}"=="false")
		{
		document.getElementById("major").style.display='';
		}
	
	if("${objHrDeptEnqDtl.isDecisionTaken}"=='Y')
		{
		showRuleDtl();
		displayOffence();
		}
	}
</script>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
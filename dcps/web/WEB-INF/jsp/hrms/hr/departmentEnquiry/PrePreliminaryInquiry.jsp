<%
try {
%>

<c:set var="hrPrePrelimEnqObj" value="${resValue.prePrelimEnq}"></c:set>
<c:set var="sourceOfEnquiry" value="${resValue.sourceOfEnquiry}"></c:set>
<c:set var="objCmnPersonMst" value="${resValue.objCmnPersonMst}"></c:set>
<c:set var="objCmnPersonMstEnqOff" value="${resValue.objCmnPersonMstEnqOff}"></c:set>
<c:set var="objPrPrelimEnqDtl" value="${resValue.objPrPrelimEnqDtl}"></c:set>
<c:set var="branch" value="${resValue.branch_name}"></c:set>
<c:set var="department" value="${resValue.department}"></c:set>
<c:set var="voToXmllstObj" value="${resValue.voToXmllstObj}"></c:set>

<script>

setDefaultDate();

function setDefaultDate()
{
<fmt:formatDate pattern="dd/MM/yyyy" value="${objPrPrelimEnqDtl.applyingDate}" var="appDt"/>
<fmt:formatDate pattern="dd/MM/yyyy" value="${objPrPrelimEnqDtl.eventDate}" var="eventDate"/>
}

function addRecordAllegDB()
	{
	if("${enqDtl[0].prePrelimStatus}"=='P' && enqFlag=='file')
		{
		
		var rowNo= addDataInTableAttachment('txnAdd', 'encXML', displayFieldArray,   '', '','viewRecordAlleg');				
		}
	}
</script>

<hdiits:fieldGroup titleCaptionId="dept.generalDtls" bundle="${deptLables}" id="prePrelim1">


 	
<table  width="100%">	
	<tr><td>
	<hdiits:hidden name="checkAlleg" id="checkAlleg" default="0"/>
	</td></tr>
	<tr>
		<td width=25%>
		<hdiits:caption captionid="dept.causeInquiry" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:select name="sourceInquiryPrePre" id="sourceInquiryPrePre" mandatory="true" sort="false">
			<option id="a" value="Select"><fmt:message key="dept.select"/></option>
			<c:set var="lookup" value="${objPrPrelimEnqDtl.cmnLookupMst.lookupName}"> </c:set>
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

		<td width=25%></td>
	
		<td width=25%></td>

	</tr>

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
		<td width=25%>
		<hdiits:caption captionid="dept.appName" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:text name="lastName"  default="${objCmnPersonMst.lastName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width=25%>
		<hdiits:text name="firstName" default="${objCmnPersonMst.firstName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width=25%>
		<hdiits:text name="middleName" default="${objCmnPersonMst.middleName}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
	</tr>

</table>


<table  width="92%" id="prePrelimAdd" style="display:none">
<tr>
<td>
<jsp:include page="/WEB-INF/jsp/common/address.jsp" >
<jsp:param name="addrName" value="deptPrePrilimDtls" />
<jsp:param name="addressTitle" value="Address" />
<jsp:param name="addrLookupName" value="Permanent Address" />
<jsp:param name="mandatory" value="Y" />						
</jsp:include>
</td>
</tr>
</table>

<table width=100% id="prePrelim2" style="display:none">	
	<tr>

		<td width=25%>
		<hdiits:caption captionid="dept.phone" bundle="${deptLables}"/>
		</td>
	
		<td width=25%>
		<hdiits:number name="phone"  default="${objPrPrelimEnqDtl.cmnContactMst.officePhone}" onblur="checkIsNumber(this);checkPhoneLength(this);"  maxlength="15"/>
		</td>
		
		<td width=25%>
		<hdiits:caption captionid="dept.fax" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:number name="fax"  default="${objPrPrelimEnqDtl.cmnContactMst.fax}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15"/>
		</td>
	</tr>
	
	<tr>
		<td width=25%>
		<hdiits:caption captionid="dept.dept" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:select name="prePrelimDept"   id="prePrelimDept"	sort="false" mandatory="true" >
					<option value="Select" ><fmt:message key="dept.select"/></option>
						<c:forEach var="department" items="${department}">
		
						<c:choose>
							 <c:when test="${department.departmentId == objPrPrelimEnqDtl.departmentId.departmentId}">
								<option value="${department.departmentId}" selected="selected">${department.depName}
    						 </c:when>
	   			
							 <c:otherwise>
								<option value="${department.departmentId}">${department.depName}
								</option>
	   						 </c:otherwise>
	   					</c:choose>
	    		</c:forEach>
			</hdiits:select>
		
		</td>

		<td width=25%>
		<hdiits:caption captionid="dept.email" bundle="${deptLables}"/>
		</td>
	
		<td width=25%>
		<hdiits:text name="email" default="${objPrPrelimEnqDtl.cmnContactMst.email}" onblur="echeck(this);"   maxlength="50"/>
		</td>
		
	
	</tr>
	
	<tr>
		<td width=25%>
		<hdiits:caption captionid="dept.div" bundle="${deptLables}"/>
		</td>
	
		<td width=25%>
		<hdiits:text name="div" default="${objPrPrelimEnqDtl.division}" onblur="restrictSplChar(this);"  maxlength="15"/>
		</td>

		<td width=25%>
		<hdiits:caption captionid="dept.branch" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:select name="prePrelimBranch" id="prePrelimBranch" sort="false" mandatory="true" >
					<option value="Select" ><fmt:message key="dept.select"/></option>
						<c:forEach var="branch" items="${branch}">
		
						<c:choose>
						 <c:when test="${branch.branchId == objPrPrelimEnqDtl.cmnBranchMst.branchId}">
						 <option value="${branch.branchId}"  selected="selected">${branch.branchName}</option>
    					 </c:when>
	   			
						 <c:otherwise>
						<option value="${branch.branchId}">${branch.branchName}</option>
	   					 </c:otherwise>
	   					</c:choose>
	    											
						</c:forEach>
			</hdiits:select>
		</td>

	</tr>
	
	<tr>
	

		<td width=25%>
		<hdiits:caption captionid="dept.appDt" bundle="${deptLables}"/>
		</td>
	
		<td width=25%>
		<hdiits:dateTime name="appDate" maxvalue="31/12/2099 00:00:00" captionid="dept.appDt" bundle="${deptLables}" mandatory="true" />
		</td>
		<script>
		
		document.inquiryCaseTracking.appDate.value="${appDt}";
		</script>
		<td width=25%>
		<hdiits:caption captionid="dept.eventDt" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:dateTime name="eventDt" maxvalue="31/12/2099 00:00:00" captionid="dept.eventDt" bundle="${deptLables}" />
		</td>
		<script>
		document.inquiryCaseTracking.eventDt.value="${eventDate}";
		</script>
	</tr>
	

	</table>
	<hdiits:fieldGroup titleCaptionId="dept.inqOffDtls" bundle="${deptLables}" id="inqOffDtlFldGrp">
	
	<table width=100%>
	<tr>
		<td width=25%>
		<hdiits:caption captionid="dept.enqOffInSameDept" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:radio name="radioenqOff" value="y" onclick="enqOffDisp();" bundle="${deptLables}" captionid="dept.y" />
		<hdiits:radio name="radioenqOff" value="n" onclick="enqOffDisp();" bundle="${deptLables}" captionid="dept.n" />
		</td>
		
		<td width=25%>
		</td>
		
		<td width=25%>
		</td>
	</tr>
	
	<tr id="name1Input" style="display:none">
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
	

	
	<tr id="name2Input" style="display:none">
		<td width=25%>
		<hdiits:caption captionid="dept.Name" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		
		<hdiits:text name="InqlastName" default="${objCmnPersonMstEnqOff.lastName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width=25%>
		<hdiits:text name="InqfirstName" default="${objCmnPersonMstEnqOff.firstName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width=25%>
		<hdiits:text name="InqmiddleName" default="${objCmnPersonMstEnqOff.middleName}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
	</tr>
	
	<tr id="phone1Input" style="display:none">
	<td width=25%>
		
		</td>

		<td width=25%>
		<hdiits:caption captionid="dept.office" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.residence" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.mobile" bundle="${deptLables}"/>
		</td>
	</tr>
	
	<tr id="phone2Input" style="display:none">
	<td width=25%>
		<hdiits:caption captionid="dept.phone" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:number name="enqOffPhone" default="${objPrPrelimEnqDtl.cmnContactMstEnqOff.officePhone}" mandatory="true" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15"  size="20"/>
		</td>
		<td width=25%>
		<hdiits:number name="enqOffPhoneResi" default="${objPrPrelimEnqDtl.cmnContactMstEnqOff.residencePhone}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		<td width=25%>
		<hdiits:number name="enqOffPhoneMobile" default="${objPrPrelimEnqDtl.cmnContactMstEnqOff.mobile}" onblur="checkIsNumber(this);checkMobileLength(this);" maxlength="10" size="20"/>
		</td>
	</tr>

	<tr id="fax1Input" style="display:none">
		<td width=25%>
			<hdiits:caption captionid="dept.fax" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:number name="Inqfax" default="${objPrPrelimEnqDtl.cmnContactMstEnqOff.fax}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15"  size="20"/>
		</td>
	</tr>
	
	<tr id="userIdInput" style="display:none">
		<td width=25%>
		<hdiits:caption captionid="dept.userIdEnqOff" bundle="${deptLables}"/>
		</td>
	
		<td width=25%>
		<hdiits:number name="userIdEnqOff" id="user_id2" mandatory="true" default="${objPrPrelimEnqDtl.orgUserMstByEnqUserId.userId}"  onblur="checkIsNumber(this)" readonly="true"/>
		<hdiits:image id="Search2" source="images/search_icon.gif" onclick="SearchEmp(this);"> </hdiits:image>
		</td>

		<td width=25%>
		</td>
		
		<td width=25%>
		</td>
	</tr>
 </table>	
</hdiits:fieldGroup>
</hdiits:fieldGroup>

 <table width=100% id="allegLabel" style="display:none">	
	<tr style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<td width=100%>
			<b><hdiits:caption captionid="dept.dtlsAllegatn"  bundle="${deptLables}"/><label class="mandatoryindicator">*</label></b>
    	<a href="javascript:void(0);" onclick="dispAlleg();"><img id="expandTicketAlleg" src="images/expand.gif" /></a>
		<a href="javascript:void(0);" onclick="hideAlleg();"><img id="collapseTicketAlleg" src="images/collapse.gif" style="display:none"/></a>
		</td>
    </tr>
    
</table>




<table width=100% id="prePrelim3"  style="display:none">
		<tr><td><br></td></tr>
		<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="4" align="center">
			<font color="#ffffff">
			<strong><u><hdiits:caption captionid="dept.dtlsAllegatn" bundle="${deptLables}"/></u></strong>
			</font>
		</td>
	</tr>
</table>

<table  width=100% id="prePrelim4" style="display:none" align="center">	
	
	<tr>
		<td width=25%>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.lastName" bundle="${deptLables}"/>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.firstName" bundle="${deptLables}"/>	</td>
		<td width=25%>	<hdiits:caption captionid="dept.midName" bundle="${deptLables}"/>	</td>
		
	</tr>
	
	<tr>				
		<td width=25%><hdiits:caption captionid="dept.witnessName" bundle="${deptLables}"/>	</td>
		
		<td width=25% >
		<hdiits:text name="nameWitness" onblur="restrictSplChar(this);onlyChars(this);" maxlength="15" mandatory="true"/>
		</td>
		
		<td width=25% >
		<hdiits:text name="firstnameWitness" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15" mandatory="true"/>
		</td>
		
		<td width=25% >
		<hdiits:text name="midnameWitness" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
				
	</tr>

	<tr>
		
		<td width=25% >
			<hdiits:caption captionid="dept.dtlsAllegatn" bundle="${deptLables}"/>
		</td>
		
		<td width=25% >
			<hdiits:textarea name="dtlAllegation" onblur="restrictSplChar(this);"  maxlength="150" rows="3" cols="25"> </hdiits:textarea>
		</td>	
			
		<td width=25%  >
			<hdiits:caption captionid="dept.witnessStmt" bundle="${deptLables}"/>
		</td>
		
		<td width=25% >
			<hdiits:radio name="stmtReq" value="y" bundle="${deptLables}" captionid="dept.y" />
			<hdiits:radio name="stmtReq" value="n" bundle="${deptLables}" captionid="dept.n" />
			<hdiits:hidden name="stmtReqHid"/>
		</td>	
		
	</tr>
	
	<tr>
		<td>
			<hdiits:caption captionid="dept.preprelimFrmDate" bundle="${deptLables}"/>:
		</td>	
		<td>
		<hdiits:dateTime name="frmAllegDt" maxvalue="31/12/2099 00:00:00" captionid="dept.appDt" bundle="${deptLables}" onblur="chkDateInput();" />
		
		</td>	
		<td>
			<hdiits:caption captionid="dept.preprelimToDate" bundle="${deptLables}"/>:
		</td>
		<td>
		<hdiits:dateTime name="toAllegDt" maxvalue="31/12/2099 00:00:00" captionid="dept.appDt" bundle="${deptLables}"  onblur="chkDateInput();"/>
		
		</td>
	</tr>
	
	<tr>
		
		<td width=25%>
			<hdiits:caption captionid="dept.periodEvent" bundle="${deptLables}"/>
		</td> 
		
		<td width=25%>
		<hdiits:number name="period" onblur="restrictSplChar(this);"  maxlength="6" readonly="readonly" />
		</td>
		
		<td width=25%>
			<hdiits:caption captionid="dept.docEvidence" bundle="${deptLables}"/>
		</td>
		
		<td width=25% >
			<hdiits:radio name="docEvidence" value="y" bundle="${deptLables}" captionid="dept.y" />
			<hdiits:radio name="docEvidence" value="n" bundle="${deptLables}" captionid="dept.n" />
			<hdiits:hidden name="docEvidenceHid"/>
		</td>
	</tr>
	
</table>

<table id="attachTab" style="display:none" width=100%>
	<tr>
		<td>
			<!-- For attachment : Start-->	
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="AllegAttatchment" />
			<jsp:param name="formName"  value="inquiryCaseTracking" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="Y" />
			<jsp:param name="rowNumber" value="1" />
			<jsp:param name="removeAttachmentFromDB" value="Y" />              
			</jsp:include>
		</td>
	</tr>
</table>



<table width=100% id="prePrelim5" style="display:none">
	<tr align="center">
		<td align="center">
		
		<hdiits:button type="button" name="btnAdd" value="Add" onclick="nameInput();"/>
		<hdiits:button type="button" name="btnUpdate" value="Update" onclick="nameUpdate();" style="display:none"/>
		</td>
	</tr>
</table>

<table id="txnAdd" border="1" align="center" width="100%" style="display:none"> 
	<tr>
			
    		<td class="fieldLabel" width="20%" align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"><hdiits:caption captionid="dept.det" bundle="${deptLables}"/></td>
    		<td class="fieldLabel" width="20%" align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"><hdiits:caption captionid="dept.Name" bundle="${deptLables}"/></td>
    		<td class="fieldLabel" width="20%" align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"><hdiits:caption captionid="dept.period" bundle="${deptLables}"/></td>
    		<td class="fieldLabel" width="10%" align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"><hdiits:caption captionid="dept.stmtReq" bundle="${deptLables}"/> </td>
			<td class="fieldLabel" width="10%" align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"><hdiits:caption captionid="dept.doc" bundle="${deptLables}"/> </td>
    		<td class="fieldLabel" width="20%" align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"><hdiits:caption captionid="dept.action" bundle="${deptLables}"/></td>  				
    </tr>

	
</table> 
<c:forEach var="alleg" items="${allegDtls}" varStatus="x">
	<c:set var="curXMLFileName" value="${voToXmllstObj[x.index]}"></c:set>
		
	<script>
	var xmlFileName = '${curXMLFileName}';
	var attachmentId="${alleg.cmnAttachmentMst.attachmentId}";
	var witStmt="";
	var docEvi="";
	if('${alleg.isWitStmntRecvd}'=='n')
	{
	witStmt="No";
	}
	else if('${alleg.isWitStmntRecvd}'=='y')
	{
	witStmt="Yes";
	}
	if('${alleg.isDocEvidFound}'=='n')
	{
	docEvi="No";
	}
	else if('${alleg.isDocEvidFound}'=='y')
	{
	docEvi="Yes";
	}
	var displayFieldArray = new Array('${alleg.allegationDetails}', '${alleg.cmnPersonMst.firstName}','${alleg.eventPeriod}', witStmt, docEvi);	
	if("${enqDtl[0].prePrelimStatus}"=='P' &&  "${enqDtl[0].flag}"=='file')
	{
	addDBDataInTableAttachment('txnAdd','encXML',displayFieldArray,xmlFileName,attachmentId,'', '','viewRecordAlleg');
	}
	else if("${enqDtl[0].prePrelimStatus}"=='P' &&  "${enqDtl[0].flag}"=='req')
	{
	addDBDataInTableAttachment('txnAdd','encXML',displayFieldArray,xmlFileName,attachmentId,'editRecordAlleg', 'deleteAllegRecord','');	
	}
	
	</script>
	</c:forEach>	

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
</script>

<script>
 

 function displayAll()
	{
		dispAlleg2();
		if("${objPrPrelimEnqDtl.orgUserMstByEnqUserId.userId}"!='')
		{ 
		  displayAllNoUserId();
		}
		
		if("${objPrPrelimEnqDtl.orgUserMstByEnqUserId.userId}"=='')
		{
		document.inquiryCaseTracking.radioenqOff[1].checked=true;
		enqOffDisp();
		}
	}

function dispAlleg()
	{
	allegDisplayPart();
	
		if("${enqDtl[0].prePrelimStatus}"=='' || "${enqDtl[0].flag=='req'}")
			{
			document.getElementById('prePrelim5').style.display=''; 
		   	document.getElementById('attachTab').style.display=''; 
			}
		else
			{
			document.getElementById('prePrelim5').style.display='none';   
			}
		
		if(document.inquiryCaseTracking.checkAlleg.value>0 || "${enqDtl[0].prePrelimStatus}"!='')
		{
		document.getElementById('txnAdd').style.display=''; 
		document.getElementById('attachTab').style.display=''; 
		}
	hideAddBtn();
	}
	
function hideAddBtn()
{
if("${enqDtl[0].prePrelimStatus}"=='P' && "${enqDtl[0].flag=='req'}")
			{
			document.getElementById('btnAdd').style.display='none';
			}  	
}

</script>

<script>
 hideAll();

 prePrelimDateReadonly();
  if("${enqDtl[0].prePrelimStatus}"!='')
		{
		document.getElementById('attachTab').style.display='none';
		}
	 
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/hr/pension/Pension.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/address.js"/>"></script>

<fmt:setBundle basename="resources.hr.pension.Pension" var="pensionEmpFamilyLabel" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="orgEmpMst" value="${resValue.orgEmpMst}"></c:set>
<c:set var="FamilyArrObj" value="${resValue.FamilyArrObj}"></c:set>
<c:set var="pensionAddforComm" value="${resValue.pensionAddforComm}"></c:set>
<c:set var="officeAdd" value="${resValue.officeAdd}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
	<hdiits:fieldGroup collapseOnLoad="false" id="branch_family_field1" titleCaptionId="Pension.EmpAppName" bundle="${pensionEmpFamilyLabel}">		
	<table class="tabtable">		
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Name" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<c:out value="${orgEmpMst.empFname}"></c:out>
				<c:out value="${orgEmpMst.empMname}"></c:out>
				<c:out value="${orgEmpMst.empLname}"></c:out>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Designation" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><c:out value="${resValue.designationName}"></c:out></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.OfficeAdd" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><c:out value="${officeAdd}"></c:out></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Department" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><c:out value="${resValue.departmentName}"></c:out></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Account" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><c:out value="${resValue.accName}"></c:out></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Class" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><c:out value="${resValue.gradeName}"></c:out></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.DOB" bundle="${pensionEmpFamilyLabel}"></hdiits:caption>(DD/MM/YYYY)</b></td>
			<td class="fieldLabel" width="25%">
				<fmt:formatDate value="${orgEmpMst.empDob}" pattern="dd/MM/yyyy" var="dob"/>
				<c:out value="${dob}"></c:out>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.Age" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<fmt:formatDate value="${orgEmpMst.empDob}" pattern="dd/MM/yyyy" var="dob"/>	
				<script>
					var age= Age('${dob}');			
					document.write(age);
				</script>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.DOJ" bundle="${pensionEmpFamilyLabel}"></hdiits:caption>(DD/MM/YYYY)</b></td>
			<td class="fieldLabel" width="25%">
				<fmt:formatDate value="${orgEmpMst.empDoj}" pattern="dd/MM/yyyy" var="doj"/>
				<c:out value="${doj}"></c:out>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.DateOfRetire" bundle="${pensionEmpFamilyLabel}"></hdiits:caption>(DD/MM/YYYY)</b></td>
			<td class="fieldLabel" width="25%">
				<fmt:formatDate value="${resValue.dor}" pattern="dd/MM/yyyy" var="dor"/>
				<c:out value="${dor}"></c:out>
			</td>
		</tr>
		<c:if test="${flag ne 'Branch'}"></c:if>				
		<c:if test="${flag eq 'Branch'}">
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.TypeOfRetire" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
				<td class="fieldLabel" width="25%">
					<c:out value="${resValue.TypeOfRetirement}"></c:out>
				</td>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="Pension.AddComm" bundle="${pensionEmpFamilyLabel}"></hdiits:caption></b></td>
				<td class="fieldLabel" width="25%">
					<hdiits:textarea name="cmmAddTextArea" id="cmmAddTextArea" readonly="true" default="${resValue.addressForComm}"></hdiits:textarea>
				</td>				
			</tr>
		</c:if>
	</table>
	<BR>
	<table id="addressTable" style="display:none" class="tabtable">
	<tr>
		<td class="fieldLabel" width="25%"></td>
		<td class="fieldLabel" width="50%" colspan="2">		
		</td>
		<td class="fieldLabel" width="25%"></td>
	</tr>
	</table>
	</hdiits:fieldGroup>			
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="branch_family_field2" titleCaptionId="Pension.FamilyDtls" bundle="${pensionEmpFamilyLabel}">		
	<BR>
	<table id="FamilyDataTable" name="FamilyDataTable" align="center"  style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="85%">
		<tr bgcolor="#C9DFFF">
			<td align="center" class="fieldLabel"><b><hdiits:caption captionid="Pension.Srno" bundle="${pensionEmpFamilyLabel}" /></b></td>
			<td align="center" class="fieldLabel"><b><hdiits:caption captionid="Pension.FmName" bundle="${pensionEmpFamilyLabel}" /></b></td>
			<td align="center" class="fieldLabel"><b><hdiits:caption captionid="Pension.DOB" bundle="${pensionEmpFamilyLabel}" /></b>(DD/MM/YYYY)</td>
			<td align="center" class="fieldLabel"><b><hdiits:caption captionid="Pension.Age" bundle="${pensionEmpFamilyLabel}" /></b></td>				
			<td align="center" class="fieldLabel"><b><hdiits:caption captionid="Pension.Gender" bundle="${pensionEmpFamilyLabel}" /></b></td>							
			<td align="center" class="fieldLabel"><b><hdiits:caption captionid="Pension.RelWithEmp" bundle="${pensionEmpFamilyLabel}" /></b></td>
			<td align="center" class="fieldLabel"><b><hdiits:caption captionid="Pension.MartStat" bundle="${pensionEmpFamilyLabel}" /></b></td>
			<td align="center" class="fieldLabel" style="display:none"></td>
		</tr>
		<c:if test="${empty FamilyArrObj}">
		<tr><td colspan="10" align="center">
	       	<b><fmt:message key="Pension.no_record" bundle="${pensionEmpFamilyLabel}" /></b>	       	
        </td></tr>
		</c:if>		
	</table>
		<c:if test="${not empty FamilyArrObj}">			
			<c:set var="yes" value="Yes"></c:set>
			<c:set var="no" value="No"></c:set>	
			<c:set var="delete" value="delete"></c:set>
			<c:set var="srNo" value="1"></c:set>
			<c:forEach items="${FamilyArrObj}" var="familyTuples" varStatus="x">
				
				<c:set var="cmnGender" value="${familyTuples.cmnLookupMstByFmGender.lookupDesc}" />
				<c:set var="cmnDeadOrAlive" value="${familyTuples.cmnLookupMstByFmDeadOrAlive.lookupDesc}" />
				<c:set var="cmnRelation" value="${familyTuples.cmnLookupMstByFmRelation.lookupDesc}" />
				<c:set var="cmnMarital" value="${familyTuples.cmnLookupMstByFmMaritalStatus.lookupDesc}" />				
				
				<c:set var="fmFirstName" value="${familyTuples.fmFirstName}" />
				<c:set var="fmMiddleName" value="${familyTuples.fmMiddleName}" />	
				<c:set var="fmLastName" value="${familyTuples.fmLastName}" />
				<c:set var="fmDOB" value="${familyTuples.fmDateOfBirth}" />
				<fmt:formatDate value="${fmDOB}" pattern="dd/MM/yyyy" var="dob"/>																
				<script type="text/javascript">					
						var xmlFileName = '';			
																
						var gender = '${cmnGender}';
						var relation = '${cmnRelation}';
						var marital='${cmnMarital}';
						var age = Age('${dob}');
						var name = '${fmFirstName}'+'${fmMiddleName}'+ '${fmLastName}';
						if(gender.search(/select/i)!=-1){gender='-';}
						if(relation.search(/select/i)!=-1){relation='-';}												
						if(marital.search(/select/i)!=-1){marital='-';}						
						
						var displayFieldA  = new Array('${srNo}',name,'${dob}',age,gender,relation,marital);					
						addDBDataInTable('FamilyDataTable','encXML',displayFieldA,xmlFileName,'', '','');	
				</script>	
				<c:set var="srNo" value="${srNo+1}"></c:set>
			</c:forEach>
		</c:if>
		</hdiits:fieldGroup>			
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
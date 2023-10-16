<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.ess.OrgDeptMstLabels" var="CommonLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="activeFlag" value="${resValue.activeFlag}"></c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="parentDepartment" value="${resValue.parentDepartment}"></c:set>
<c:set var="parentDepartment_gu" value="${resValue.parentDepartment_gu}"></c:set>
<c:set var="parentorgDeptMst" value="${resValue.parentorgDeptMst}"></c:set>
<c:set var="orgDepartmentMst" value="${resValue.orgDepartmentMst}"></c:set>
<c:set var="orgDepartmentMst_gu" value="${resValue.orgDepartmentMst_gu}"></c:set>

<html>
<head>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/ess/orgDeptmstAdmin.js"></script>

</head>

<body>
	<hdiits:form name="frmAdminCrtDept" action="hrms.htm?actionFlag=SubmitAdminDeptData" method="post" validate="true" encType="multipart/form-data">
		<div id="tabmenu">
		<ul id="maintab" class="shadetabs" >
	
		<c:if test="${langId == 1}">
			<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
				<hdiits:caption captionid="DEPT.Eng"  bundle="${CommonLables}" captionLang="single"/>  
			</b></a></li>
			<li class="selected" id="guj" style="display:block"><a href="#" rel="tcontent2" onfocus="copyEngToGuj()"><b>
				<hdiits:caption captionid="DEPT.Guj" bundle="${CommonLables}" captionLang="single"/> 
			</b></a></li>
		</c:if>
		<c:if test="${langId == 2}">
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent1"><b>
				<hdiits:caption captionid="DEPT.Guj" bundle="${CommonLables}" captionLang="single"/> 
			</b></a></li>
			<li class="selected" id="eng"><a href="#" rel="tcontent2" onfocus="copyGujToEng()"><b>
				<hdiits:caption captionid="DEPT.Eng" bundle="${CommonLables}" captionLang="single"/> 
			</b></a></li>
		</c:if>
	</ul>
	</div>
	
	<div id="createDept" name="createDept" class="tabcontentstyle" >
		<c:if test="${langId == 1}">
			<div id="tcontent1" class="tabcontent" tabno="0">
		</c:if>
		<c:if test="${langId == 2}">
			<div id="tcontent2" class="tabcontent" tabno="1">
		</c:if>
		
		<table width="100%" id="newDept_gu">
			<tr width="100%" align="center"><td><b><fmt:message key="DEPT.NewDept" bundle="${CommonLables}"></fmt:message></b></td></tr>
		</table>
		<table width="100%" id="editDept_gu" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message key="DEPT.EditDept" bundle="${CommonLables}"></fmt:message></b></td></tr>
		</table>
		
		<table class="tabtable">				
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="DEPT.deptName" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtdeptName" id="txtdeptName" mandatory="true" captionid="DEPT.deptName" bundle="${CommonLables}" validation="txt.isrequired" maxlength="25"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.deptShortName" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtdeptShrtName" id="txtdeptShrtName" captionid="DEPT.deptShortName" bundle="${CommonLables}" mandatory="true" validation="txt.isrequired" maxlength="15"></hdiits:text></td>
			</tr>
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.parentDept" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="cmbParentDepartment" id="cmbParentDepartment" captionid="DEPT.parentDept" bundle="${CommonLables}" sort="false">
						<hdiits:option value=""><fmt:message key="DEPT.SELECT" bundle="${CommonLables}"></fmt:message></hdiits:option>
						<c:forEach items="${parentDepartment}" var="department">
							<option value="<c:out value="${department.depCode}"/>"><c:out value="${department.depName}" /></option>
						</c:forEach>
					</hdiits:select>
				</td>	
			</tr>
			
			<tr>
				<fmt:formatDate value="${orgDepartmentMst.startDate}" pattern="dd/MM/yyyy" var="startDate"/>
				<fmt:formatDate value="${orgDepartmentMst.endDate}" pattern="dd/MM/yyyy" var="endDate"/>
				<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.StartDate" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dtstartDate" captionid="DEPT.StartDate" bundle="${CommonLables}" mandatory="true" maxvalue="31/12/2099" afterDateSelect="compareStartDateToEndDate()"  validation="txt.isdt,txt.isrequired"></hdiits:dateTime></td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.EndDate" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dtendDate" captionid="DEPT.EndDate" bundle="${CommonLables}" maxvalue="31/12/2099" afterDateSelect="compareStartDateToEndDate()"></hdiits:dateTime></td>
			</tr>	
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.Status" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel" >
					<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
						<hdiits:radio name="rdoActiveFlag" value="${StatusFlagValue.lookupId}" />${StatusFlagValue.lookupName}<b><label class="mandatoryindicator">*</label></b><br>										
					</c:forEach>
				</td>
				<td class="fieldLabel"></td>				
				<td class="fieldLabel"></td>
			</tr>	
		</table>
	</div>
		
	<c:if test="${langId == 1}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
		
			<table width="100%" id="newDept">
				<tr width="100%" align="center"><td><b><fmt:message key="DEPT.NewDept" bundle="${CommonLables}"></fmt:message></b></td></tr>
			</table>
			
			<table width="100%" id="editDept" style="display:none">
				<tr width="100%" align="center"><td><b><fmt:message key="DEPT.EditDept" bundle="${CommonLables}"></fmt:message></b></td></tr>
			</table>
			
			<table class="tabtable">
				<tr><td class="fieldLabel"><b><hdiits:caption captionid="DEPT.deptName" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel"><hdiits:text name="txtdeptName_gu" id="txtdeptName_gu" mandatory="true" captionid="DEPT.deptName" bundle="${CommonLables}" validation="txt.isrequired" maxlength="25"></hdiits:text></td>
					<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.deptShortName" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel"><hdiits:text name="txtdeptShrtName_gu" id="txtdeptShrtName_gu" captionid="DEPT.deptShortName" bundle="${CommonLables}" mandatory="true" validation="txt.isrequired" maxlength="15"></hdiits:text></td>
				</tr>
				
				<tr>
					<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.parentDept" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel">
						<hdiits:select name="cmbParentDepartment_gu" id="cmbParentDepartment_gu" captionid="DEPT.parentDept" bundle="${CommonLables}" sort="false">
							<hdiits:option value=""><fmt:message key="DEPT.SELECT_GU" bundle="${CommonLables}"></fmt:message></hdiits:option>
							<c:forEach items="${parentDepartment_gu}" var="department_gu">
								<option value="<c:out value="${department_gu.depCode}"/>"><c:out value="${department_gu.depName}" /></option>
							</c:forEach>			
						</hdiits:select>
					</td>	
				</tr>
				
				<tr>
					<fmt:formatDate value="${orgDepartmentMst_gu.startDate}" pattern="dd/MM/yyyy" var="startDate_gu"/>
					<fmt:formatDate value="${orgDepartmentMst_gu.endDate}" pattern="dd/MM/yyyy" var="endDate_gu"/>	
					<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.StartDate" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel"><hdiits:dateTime name="dtstartDate_gu" captionid="DEPT.StartDate" bundle="${CommonLables}" afterDateSelect="compareStartDateToEndDateGu()" maxvalue="31/12/2099" validation="txt.isdt,txt.isrequired" mandatory="true"></hdiits:dateTime></td>	
					<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.EndDate" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel"><hdiits:dateTime name="dtendDate_gu" captionid="DEPT.EndDate" bundle="${CommonLables}" afterDateSelect="compareStartDateToEndDateGu()" maxvalue="31/12/2099"></hdiits:dateTime></td>
					
				</tr>	
			
				<tr>
					<td class="fieldLabel"><b><hdiits:caption captionid="DEPT.Status" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel" >
						<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
							<hdiits:radio name="rdoActiveFlag_gu" value="${StatusFlagValue.lookupId}" />${StatusFlagValue.lookupName}<b><label class="mandatoryindicator">*</label></b><br>										
						</c:forEach>
					</td>
					<td class="fieldLabel"></td>				
					<td class="fieldLabel"></td>
				</tr>
			
			</table>
			
		</div>
	
		<hdiits:hidden name="flag" id="flag" default="${flag}"/>
		<hdiits:hidden name="deptCode" id="deptCode" default=""/>	
		</div>
		
		<table align="center">	
			<tr>
				<td align="center">
					<c:if test="${flag ne'edit'}">
						<br><hdiits:button name="Submit" type="button" captionid="DEPT.SUBMIT" bundle="${CommonLables}" onclick="submit_frmAdminCrtDept()"></hdiits:button>
					</c:if>
					<c:if test="${flag eq 'edit'}">
						<br><hdiits:button name="Update" type="button" captionid="DEPT.UPDATE" bundle="${CommonLables}" onclick="submit_frmAdminCrtDept()"></hdiits:button>
					</c:if>
				</td>
				<td align="center">
					<br><hdiits:button name="reset" type="button" captionid="DEPT.RESET" bundle="${CommonLables}" onclick="resetData()"></hdiits:button>
				</td>
				<td align="center">
					<br><hdiits:button name="Close" type="button" captionid="DEPT.CLOSE" bundle="${CommonLables}" onclick="closeWindow()"></hdiits:button>
				</td>
			</tr>
		</table>
	
	<hdiits:hidden name="langId" id="langId" default="${langId}"></hdiits:hidden>
	<hdiits:hidden name="multiLangHdn" id="multiLangHdn" default="${multiLang}"></hdiits:hidden>		
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	
	<script type="text/javascript">
		initializetabcontent("maintab")	
	</script>
	</hdiits:form>
</body>
<script type="text/javascript">
	var validateDatealert = "<fmt:message bundle='${CommonLables}' key='DEPT.VALIDDATE'/>";
	var selectRadioalert = "<fmt:message bundle='${CommonLables}' key='DEPT.SELECTRADIO'/>";
	var selectParentDeptalert = "<fmt:message bundle='${CommonLables}' key='DEPT.SELECTPARENTDEP'/>";
	
	
	document.frmAdminCrtDept.dtstartDate.readOnly = true;
	document.frmAdminCrtDept.dtendDate.readOnly = true;
	document.frmAdminCrtDept.dtstartDate_gu.readOnly = true;
	document.frmAdminCrtDept.dtendDate_gu.readOnly = true;

	//showdate();
	var multiLang = '${multiLang}';
	
	if('${multiLang}'=='false' || '${multiLang}'==false)
	{	
		document.getElementById("guj").style.display='none';
		document.getElementById("eng").childNodes[0].childNodes[0].innerHTML = "Department Master";
	}

	if('${flag}'=='edit'){
		
		document.getElementById('deptCode').value = '${orgDepartmentMst.depCode}';
				
		document.getElementById('newDept').style.display = 'none';	
		document.getElementById('newDept_gu').style.display = 'none';	
		document.getElementById('editDept').style.display = '';	
		document.getElementById('editDept_gu').style.display = '';	
			
		document.getElementById('txtdeptName').value='${orgDepartmentMst.depName}';
		document.getElementById('txtdeptName_gu').value='${orgDepartmentMst_gu.depName}';
		
		document.getElementById('txtdeptShrtName').value='${orgDepartmentMst.depShortName}';			
		document.getElementById('txtdeptShrtName_gu').value='${orgDepartmentMst_gu.depShortName}';	
		
		document.getElementById('cmbParentDepartment').value='${parentorgDeptMst.depCode}';
		document.getElementById('cmbParentDepartment_gu').value='${parentorgDeptMst.depCode}';
		
		var stausFlagVal = '${orgDepartmentMst.activateFlag}';
		if(stausFlagVal == 1) // active
		{
			document.frmAdminCrtDept.rdoActiveFlag[0].checked = true;
			document.frmAdminCrtDept.rdoActiveFlag_gu[0].checked = true;
		}
		if(stausFlagVal == 2) // deactive
		{
			document.frmAdminCrtDept.rdoActiveFlag[1].checked = true;
			document.frmAdminCrtDept.rdoActiveFlag_gu[1].checked = true;
		}
		if(stausFlagVal == 3) // delete
		{
			document.frmAdminCrtDept.rdoActiveFlag[2].checked = true;
			document.frmAdminCrtDept.rdoActiveFlag_gu[2].checked = true;
		}
		
		document.frmAdminCrtDept.dtstartDate.value='${startDate}';
		document.frmAdminCrtDept.dtendDate.value='${endDate}';
		//guj
		document.frmAdminCrtDept.dtstartDate_gu.value='${startDate_gu}';
		document.frmAdminCrtDept.dtendDate_gu.value='${endDate_gu}';
		
	}
	else{
		showdate();
		document.frmAdminCrtDept.rdoActiveFlag[0].checked = true;
		document.frmAdminCrtDept.rdoActiveFlag_gu[0].checked = true;
	}

</script>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>
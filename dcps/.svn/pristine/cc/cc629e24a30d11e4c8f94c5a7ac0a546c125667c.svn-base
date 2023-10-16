<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.ess.OrgGradeMstLabels" var="CommonLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="activeFlag" value="${resValue.activeFlag}"></c:set>
<c:set var="gradeNameFlag" value="${resValue.gradeNameFlag}"></c:set>
<c:set var="gradeNameIdentifier" value="${resValue.gradeNameIdentifier}"></c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="gradeName_en" value="${resValue.gradeName_en}"></c:set>
<c:set var="gradeName_gu" value="${resValue.gradeName_gu}"></c:set>
<c:set var="gradeDesc_en" value="${resValue.gradeDesc_en}"></c:set>
<c:set var="gradeDesc_gu" value="${resValue.gradeDesc_gu}"></c:set>
<c:set var="orgGradeMst" value="${resValue.orgGradeMst}"></c:set>
<c:set var="orgGradeMst_gu" value="${resValue.orgGradeMst_gu}"></c:set>

<html>
<head>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/ess/orgGrademstAdmin.js"></script>

<script>

var flag ='${flag}';
</script>

</head>

<body>
	<hdiits:form name="frmAdminCrtGradeMst" action="hrms.htm?actionFlag=SubmitAdminOrgGradeMstData" method="post" validate="true" encType="multipart/form-data">
		<div id="tabmenu">
		<ul id="maintab" class="shadetabs" >
	
		<c:if test="${langId == 1}">
			<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
				<hdiits:caption captionid="GRADE.Eng"  bundle="${CommonLables}" captionLang="single"/>  
			</b></a></li>
			<li class="selected" id="guj" style="display:block"><a href="#" rel="tcontent2" onfocus="copyEngToGuj()"><b>
				<hdiits:caption captionid="GRADE.Guj" bundle="${CommonLables}" captionLang="single"/> 
			</b></a></li>
		</c:if>
		<c:if test="${langId == 2}">
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent1"><b>
				<hdiits:caption captionid="GRADE.Guj" bundle="${CommonLables}" captionLang="single"/> 
			</b></a></li>
			<li class="selected" id="eng"><a href="#" rel="tcontent2" onfocus="copyGujToEng()"><b>
				<hdiits:caption captionid="GRADE.Eng" bundle="${CommonLables}" captionLang="single"/> 
			</b></a></li>
		</c:if>
	</ul>
		
	</div>
	
	<div id="createGrade" name="createGrade" class="tabcontentstyle" >
		<c:if test="${langId == 1}">
			<div id="tcontent1" class="tabcontent" tabno="0">
		</c:if>
		<c:if test="${langId == 2}">
			<div id="tcontent2" class="tabcontent" tabno="1">
		</c:if>
		
		<br>
		
		<table width="100%" id="newGrade_gu">
			<tr width="100%" align="center"><td><b><fmt:message key="GRADE.NewGrade" bundle="${CommonLables}"></fmt:message></b></td></tr>
		</table>
		<table width="100%" id="editGrade_gu" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message key="GRADE.EditGrade" bundle="${CommonLables}"></fmt:message></b></td></tr>
		</table>
		
		<br>

		<c:if test="${gradeNameFlag eq 'true'}">
			<c:if test="${gradeNameIdentifier eq 'engGradeFlag'}">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<center><font color="red"><b><fmt:message key="GRADE.DiffEngGradeName" bundle="${CommonLables}"></fmt:message> </b></font> </center>
			</c:if>
			<c:if test="${gradeNameIdentifier eq 'gujGradeFlag'}">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<center><font color="red"><b><fmt:message key="GRADE.DiffGujGradeName" bundle="${CommonLables}"></fmt:message> </b></font> </center>
			</c:if>
		</c:if>

		<br>

		<table class="tabtable">				
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="GRADE.gradeName" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtgradeName" id="txtgradeName" mandatory="true" captionid="GRADE.gradeName" bundle="${CommonLables}" validation="txt.isrequired" maxlength="130"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="GRADE.gradDesc" bundle="${CommonLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtgradeDesc" id="txtgradeDesc" captionid="GRADE.gradDesc" bundle="${CommonLables}" mandatory="true" validation="txt.isrequired" maxlength="350"></hdiits:text></td>
			</tr>
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="GRADE.Status" bundle="${CommonLables}"/></b></td>
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
		
			<table width="100%" id="newGrade">
			<br>
			<br>
			<br>
				<tr width="100%" align="center"><td><b><fmt:message key="GRADE.NewGrade" bundle="${CommonLables}"></fmt:message></b></td></tr>
			</table>
			
			<table width="100%" id="editGrade" style="display:none">
				<tr width="100%" align="center"><td><b><fmt:message key="GRADE.EditGrade" bundle="${CommonLables}"></fmt:message></b></td></tr>
			</table>
			
			<table class="tabtable">
				<tr><td class="fieldLabel"><b><hdiits:caption captionid="GRADE.gradeName" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel"><hdiits:text name="txtgradeName_gu" id="txtgradeName_gu" mandatory="true" captionid="GRADE.gradeName" bundle="${CommonLables}" validation="txt.isrequired" maxlength="130"></hdiits:text></td>
					<td class="fieldLabel"><b><hdiits:caption captionid="GRADE.gradDesc" bundle="${CommonLables}"/></b></td>
					<td class="fieldLabel"><hdiits:text name="txtgradeDesc_gu" id="txtgradeDesc_gu" captionid="GRADE.gradDesc" bundle="${CommonLables}" mandatory="true" validation="txt.isrequired" maxlength="350"></hdiits:text></td>
				</tr>
				<tr>
					<td class="fieldLabel"><b><hdiits:caption captionid="GRADE.Status" bundle="${CommonLables}"/></b></td>
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
		
		<table align="center">	
			<tr>
				<td align="center">
					<c:if test="${flag ne'edit'}">
						<br><hdiits:button name="Submit" type="button" captionid="GRADE.SUBMIT" bundle="${CommonLables}" onclick="submit_frmAdminCrtGrade()"></hdiits:button>
					</c:if>
					<c:if test="${flag eq 'edit'}">
						<br><hdiits:button name="Update" type="button" captionid="GRADE.UPDATE" bundle="${CommonLables}" onclick="submit_frmAdminCrtGrade()"></hdiits:button>
					</c:if>
				</td>
				<td align="center">
					<br><hdiits:button name="reset" type="button" captionid="GRADE.RESET" bundle="${CommonLables}" onclick="resetData()"></hdiits:button>
				</td>
				<td align="center">
					<br><hdiits:button name="Close" type="button" captionid="GRADE.CLOSE" bundle="${CommonLables}" onclick="closeWindow()"></hdiits:button>
				</td>
			</tr>
		</table>
	
		<hdiits:hidden name="flag" id="flag" default="${flag}"/>
		<hdiits:hidden name="gradeCode" id="gradeCode" default=""/>	
		</div>
	
	<hdiits:hidden name="langId" id="langId" default="${langId}"></hdiits:hidden>
	<hdiits:hidden name="multiLangHdn" id="multiLangHdn" default="${multiLang}"></hdiits:hidden>		
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	
	<script type="text/javascript">
		initializetabcontent("maintab")	
	</script>
	</hdiits:form>
</body>
<script type="text/javascript">
	var selectRadioalert = "<fmt:message bundle='${CommonLables}' key='GRADE.SELECTRADIO'/>";
	var gradeNameAlert ="<fmt:message bundle='${CommonLables}' key='GRADE.SubmitGradeName'/>";
	
	//showdate();
	var multiLang = '${multiLang}';
	
	if('${multiLang}'=='false' || '${multiLang}'==false)
	{	
		document.getElementById("guj").style.display='none';
		document.getElementById("eng").childNodes[0].childNodes[0].innerHTML = "Grade Creation";
	}

	/*alert('Falg '+'${flag}');
	alert('grade identifier ' +'${gradeNameIdentifier}');
	
	alert('grade gradeName_en ' +'${gradeName_en}');
	alert('grade gradeName_gu ' +'${gradeName_gu}');*/
	document.getElementById('gradeCode').value = '${orgGradeMst.gradeCode}';
	//alert('grade code >>> '+'${orgGradeMst.gradeCode}');
	
	if('${flag}'=='edit'){
		
		if('${gradeNameFlag}' == 'false' ){
			//	document.getElementById('gradeCode').value = '${orgGradeMst.gradeCode}';
				
				document.getElementById('newGrade').style.display = 'none';	
				document.getElementById('newGrade_gu').style.display = 'none';	
				document.getElementById('editGrade').style.display = '';	
				document.getElementById('editGrade_gu').style.display = '';	
					
				document.getElementById('txtgradeName').value='${orgGradeMst.gradeName}';
				document.getElementById('txtgradeName_gu').value='${orgGradeMst_gu.gradeName}';
				
				document.getElementById('txtgradeDesc').value='${orgGradeMst.gradeDesc}';			
				document.getElementById('txtgradeDesc_gu').value='${orgGradeMst_gu.gradeDesc}';	
				
		}else{
		
			document.getElementById('newGrade').style.display = 'none';	
			document.getElementById('newGrade_gu').style.display = 'none';	
			document.getElementById('editGrade').style.display = '';	
			document.getElementById('editGrade_gu').style.display = '';	
		
			document.getElementById('txtgradeName').value='${gradeName_en}';
			document.getElementById('txtgradeName_gu').value='${gradeName_gu}';
			
			document.getElementById('txtgradeDesc').value='${gradeDesc_en}';			
			document.getElementById('txtgradeDesc_gu').value='${gradeDesc_gu}';	
		}
		var stausFlagVal = '${orgGradeMst.activateFlag}';
		if(stausFlagVal == 1) // active
		{
			document.frmAdminCrtGradeMst.rdoActiveFlag[0].checked = true;
			document.frmAdminCrtGradeMst.rdoActiveFlag_gu[0].checked = true;
		}
		if(stausFlagVal == 2) // deactive
		{
			document.frmAdminCrtGradeMst.rdoActiveFlag[1].checked = true;
			document.frmAdminCrtGradeMst.rdoActiveFlag_gu[1].checked = true;
		}
		if(stausFlagVal == 3) // delete
		{
			document.frmAdminCrtGradeMst.rdoActiveFlag[2].checked = true;
			document.frmAdminCrtGradeMst.rdoActiveFlag_gu[2].checked = true;
		}
		
	}else{
	
		document.getElementById('txtgradeName').value='${gradeName_en}';
		document.getElementById('txtgradeName_gu').value='${gradeName_gu}';
		
		document.getElementById('txtgradeDesc').value='${gradeDesc_en}';			
		document.getElementById('txtgradeDesc_gu').value='${gradeDesc_gu}';	
	
		document.frmAdminCrtGradeMst.rdoActiveFlag[0].checked = true;
		document.frmAdminCrtGradeMst.rdoActiveFlag_gu[0].checked = true;
	}

</script>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>
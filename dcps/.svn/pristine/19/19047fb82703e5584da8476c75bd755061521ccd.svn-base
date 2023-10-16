<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.common.MstScrLables" var="MstScrLables" scope="request" />
<fmt:setBundle basename="resources.common.BranchMasterLables" var="BranchMasterLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="branchMst" value="${resValue.branchMst_en}"></c:set>
<c:set var="branchMst_gu" value="${resValue.branchMst_gu}"></c:set>
<c:set var="branchCode" value="${resValue.branchCode}"></c:set>
<c:set var="startDateStr" value="${resValue.startDateStr}"></c:set>
<c:set var="enableDispNm" value="${resValue.enableDispNm}"></c:set>
<html>
<head>

<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript" src="script/common/cmnBranchAdmin.js"></script>

<script type="text/javascript">
var langId='${langId}';
var multiLang='${multiLang}';
var enableDispNm='${enableDispNm}';
var branchArray;
var createBranchAlertArray=new Array();
createBranchAlertArray[0]="<fmt:message bundle='${MstScrLables}' key='MstScr.activateFlag'/>";
createBranchAlertArray[1]="<fmt:message bundle='${BranchMasterLables}' key='BRANCH.branchEngGujNm'/>"
var alertArray=new Array();
alertArray[0]="<fmt:message bundle='${BranchMasterLables}' key='BRANCH.BranchName'/>";
alertArray[1]="<fmt:message bundle='${MstScrLables}' key='MstScr.AlreadyExist'/>";
alertArray[2]="<fmt:message bundle='${MstScrLables}' key='MstScr.inGuj'/>";
alertArray[3]="<fmt:message bundle='${MstScrLables}' key='MstScr.Guj'/>";
alertArray[4]="<fmt:message bundle='${MstScrLables}' key='MstScr.inEng'/>";
alertArray[5]="<fmt:message bundle='${MstScrLables}' key='MstScr.Eng'/>";
alertArray[6]="<fmt:message bundle='${MstScrLables}' key='MstScr.inEngGuj'/>";
alertArray[7]="<fmt:message bundle='${MstScrLables}' key='MstScr.EngGuj'/>";
alertArray[8]="<fmt:message bundle='${BranchMasterLables}' key='BRANCH.DispName'/>";
function resetAllData()
{
	if('${flag}'=='edit')
	{	
		globalFlagType = "E";	
		document.getElementById('newBranch').style.display = 'none';	
		document.getElementById('newBranch_gu').style.display = 'none';
				
		document.getElementById('txtBranchName').value='${branchMst.branchName}';
		generateInitCapName('txtBranchName');
		document.getElementById('txtBranchName_gu').value='${branchMst_gu.branchName}';
		if(enableDispNm=='true')
		{
			document.getElementById('txtDisplayName').value='${branchMst.displayName}';
			generateInitCapName('txtDisplayName');
			document.getElementById('txtDisplayName_gu').value='${branchMst_gu.displayName}';
		}
		document.getElementById('txtBranchDesc').value='${branchMst.branchDesc}';	
		generateInitCapName('txtBranchDesc');		
		document.getElementById('txtBranchDesc_gu').value='${branchMst_gu.branchDesc}';
		
		var startDate='${branchMst.startDate}';
		var endDate='${branchMst.endDate}';
		
		var dateArray=getDateAndTimeFromDateObj(startDate);
		document.getElementById('branchStartDate').value=dateArray[0];
		document.getElementById('branchStartDate_gu').value=dateArray[0];
		
		dateArray=getDateAndTimeFromDateObj(endDate);
		document.getElementById('branchEndDate').value=dateArray[0];
		document.getElementById('branchEndDate_gu').value=dateArray[0];
		
		var stausFlagVal = '${branchMst.activateFlag}'; 			
		if(stausFlagVal == 1) // active
		{
			document.frmAdminCrtBranch.rdoActiveFlag[0].checked = true;
			document.frmAdminCrtBranch.rdoActiveFlag_gu[0].checked = true;
		}
		if(stausFlagVal == 2) // deactive
		{
			document.frmAdminCrtBranch.rdoActiveFlag[1].checked = true;
			document.frmAdminCrtBranch.rdoActiveFlag_gu[1].checked = true;
		}
		if(stausFlagVal == 3) // delete
		{
			document.frmAdminCrtBranch.rdoActiveFlag[2].checked = true;
			document.frmAdminCrtBranch.rdoActiveFlag_gu[2].checked = true;	
		}
		
		/* added by divya -- ends */	
	}else{
		if('${multiLang}'!='false' || '${multiLang}'!=false)
		{
			document.getElementById('branchStartDate').value='${startDateStr}';
			document.getElementById('branchStartDate_gu').value='${startDateStr}';
			
			document.frmAdminCrtBranch.rdoActiveFlag[0].checked = true;
			document.frmAdminCrtBranch.rdoActiveFlag_gu[0].checked = true;	
		}else
		{
			document.getElementById('branchStartDate').value='${startDateStr}';
			document.frmAdminCrtBranch.rdoActiveFlag[0].checked = true;
		}
		resetData();
	}
}
function saveData()
{
	submit_frmAdminCrtBranch();
}
</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<hdiits:form name="frmAdminCrtBranch" action="hrms.htm?actionFlag=SubmitAdminBranchData" method="post" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" >
		<c:if test="${langId == 1}">
			<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
				<fmt:message key="MstScr.English" bundle="${MstScrLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent2" onfocus="copyEngToGuj()"><b>
				<fmt:message key="MstScr.Gujarati" bundle="${MstScrLables}"></fmt:message>
			</b></a></li>
		</c:if>
		<c:if test="${langId == 2}">
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent1"><b>
				<fmt:message key="MstScr.Gujarati" bundle="${MstScrLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="eng"><a href="#" rel="tcontent2" onfocus="copyGujToEng()"><b>
				<fmt:message key="MstScr.English" bundle="${MstScrLables}"></fmt:message>
			</b></a></li>
		</c:if>
	</ul>
	</div>			
	<hdiits:hidden name="enableDispNm" id="enableDispNm" default="${enableDispNm}"></hdiits:hidden>
	<div id="createBranch" name="createBranch" class="tabcontentstyle" >
	<c:if test="${langId == 1}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
		<table width="100%" id="newBranch">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${BranchMasterLables}' key='BRANCH.newBranchEng'/></b></td></tr>
		</table>
		<table width="100%" id="editBranch" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${BranchMasterLables}' key='BRANCH.editBranchEng'/></b></td></tr>
		</table>
		<table class="tabtable">				
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.BranchName" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtBranchName" id="txtBranchName" mandatory="true" captionid="BRANCH.BranchName" bundle="${BranchMasterLables}" validation="txt.isrequired" maxlength="45" onblur="generateInitCapName('txtBranchName')"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.BranchDesc" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:textarea name="txtBranchDesc" id="txtBranchDesc" captionid="BRANCH.BranchDesc" bundle="${BranchMasterLables}" mandatory="true"  validation="txt.isrequired" maxlength="200" onblur="generateInitCapName('txtBranchDesc')" cols="28"></hdiits:textarea></td>
			</tr>
			<tr style="display: none"><td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.startDate" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="branchStartDate" captionid="BRANCH.startDate" bundle="${BranchMasterLables}"  disabled="${langId == '1' ? false : true}" mandatory="true" validation="txt.isdt,txt.isrequired"   maxvalue="31/12/2099"></hdiits:dateTime></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.endDate" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="branchEndDate" captionid="BRANCH.endDate" bundle="${BranchMasterLables}" disabled="${langId == '1' ? false : true}"  validation="txt.isdt"  maxvalue="31/12/2099"></hdiits:dateTime></td>
			</tr>
			<tr>
				<c:if test="${enableDispNm eq true}">
					<td class="fieldLabel">
						<b><hdiits:caption captionid="BRANCH.DispName" bundle="${BranchMasterLables}"/></b>
					</td>
					<td class="fieldLabel">
						<hdiits:text name="txtDisplayName" id="txtDisplayName" captionid="BRANCH.DispName" bundle="${BranchMasterLables}" mandatory="true" validation="txt.isrequired" maxlength="40" onblur="generateInitCapName('txtDisplayName')"></hdiits:text></td>
					</td>
				</c:if>
				<td class="fieldLabel">
				<b><hdiits:caption captionid="MstScr.activateFlag" bundle="${MstScrLables}"/></b></td>
				<td class="fieldLabel" ><c:forEach var="StatusFlagValue" items="${arStatusFlag}">
					<hdiits:radio name="rdoActiveFlag" value="${StatusFlagValue.lookupId}" mandatory="true"/>${StatusFlagValue.lookupName}<br>										
					</c:forEach>
				</td>
			</tr>
		</table>		
	</div>
	<c:if test="${langId == 1}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
		<table width="100%" id="newBranch_gu">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${BranchMasterLables}' key='BRANCH.newBranchGuj'/></b></td></tr>
		</table>
		<table width="100%" id="editBranch_gu" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${BranchMasterLables}' key='BRANCH.editBranchGuj'/></b></td></tr>
		</table>	
		<table class="tabtable">
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.BranchName" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtBranchName_gu" id="txtBranchName_gu" captionid="BRANCH.BranchName" bundle="${BranchMasterLables}" mandatory="true" validation="txt.isrequired" maxlength="45"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.BranchDesc" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:textarea name="txtBranchDesc_gu" id="txtBranchDesc_gu" captionid="BRANCH.BranchDesc" bundle="${BranchMasterLables}" mandatory="true" validation="txt.isrequired" maxlength="200" cols="28"></hdiits:textarea></td>				
			</tr>
			<tr style="display: none"><td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.startDate" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="branchStartDate_gu" captionid="BRANCH.startDate" bundle="${BranchMasterLables}" afterDateSelect="compareStartDateToEndDateGu();" disabled="${langId == '1' ? true : false}" mandatory="true" validation="txt.isdt,txt.isrequired"  maxvalue="31/12/2099"></hdiits:dateTime></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="BRANCH.endDate" bundle="${BranchMasterLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="branchEndDate_gu" captionid="BRANCH.endDate" bundle="${BranchMasterLables}" afterDateSelect="compareStartDateToEndDateGu();" disabled="${langId == '1' ? true : false}" validation="txt.isdt"  maxvalue="31/12/2099"></hdiits:dateTime></td>
			</tr>
			<tr>
				<c:if test="${enableDispNm eq true}">
					<td class="fieldLabel">
						<b><hdiits:caption captionid="BRANCH.DispName" bundle="${BranchMasterLables}"/></b>
					</td>
					<td class="fieldLabel">
						<hdiits:text name="txtDisplayName_gu" id="txtDisplayName_gu" captionid="BRANCH.DispName" bundle="${BranchMasterLables}" mandatory="true" validation="txt.isrequired" maxlength="40"></hdiits:text></td>
					</td>
				</c:if>
				<td class="fieldLabel" >
				<b><hdiits:caption captionid="MstScr.activateFlag" bundle="${MstScrLables}"/></b></td>
				<td class="fieldLabel">
					<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
						<hdiits:radio name="rdoActiveFlag_gu" value="${StatusFlagValue.lookupId}" mandatory="true"/>${StatusFlagValue.lookupName}<br>										
					</c:forEach>
				</td>
			</tr>
		</table>
			
	</div>
		
	</div>
	<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${flag ne'edit'}">
					<br><hdiits:button name="Submit" type="button" captionid="MstScr.submit" bundle="${MstScrLables}" onclick="submit_frmAdminCrtBranch()"></hdiits:button>
				</c:if>
				<c:if test="${flag eq 'edit'}">
					<br><hdiits:button name="Update" type="button" captionid="MstScr.update" bundle="${MstScrLables}" onclick="submit_frmAdminCrtBranch()"></hdiits:button>
				</c:if>
			</td>
			<td align="center">
				<br><hdiits:button name="reset" type="button" captionid="MstScr.reset" bundle="${MstScrLables}" onclick="resetAllData()"></hdiits:button>
			</td>
			<td align="center">
				<br><hdiits:button captionid="MstScr.close" bundle="${MstScrLables}" onclick="closeForm('showAdminBranchDtl')" name="btnClose" type="button"></hdiits:button>
			</td>
		</tr>
	</table>
<hdiits:hidden name="langId" id="langId" default="${langId}"></hdiits:hidden>
<hdiits:hidden name="flag" id="flag" default="${flag}"/>	
<hdiits:hidden name="multiLang" id="multiLang" default="${multiLang}"></hdiits:hidden>	
<hdiits:hidden name="branchCode" id="branchCode" default="${branchCode}"></hdiits:hidden>	
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
 
<script type="text/javascript">
	initializetabcontent("maintab")	
</script>
</hdiits:form>
</body>
<script type="text/javascript">
	
	if('${multiLang}'=='false' || '${multiLang}'==false)
	{
		
		document.getElementById("guj").style.display='none';
		document.getElementById("eng").childNodes[0].childNodes[0].innerHTML = "Branch Creation";
	}
	document.getElementById("flag").value='${flag}';	

	resetAllData();
	
	if(langId=='1')
	{
		document.getElementById("txtBranchName").focus();
	}
	else if(langId=='2')
	{
		document.getElementById("txtBranchName_gu").focus();	
	}
</script>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>			
<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.ess.UserDsgnLables" var="userDsgnLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>

<c:set var="designationMst" value="${resValue.designationMst_en}"></c:set>
<c:set var="designationMst_gu" value="${resValue.designationMst_gu}"></c:set>
<c:set var="dsgnCode" value="${resValue.dsgnCode}"></c:set>
<c:set var="startDateStr" value="${resValue.startDateStr}"></c:set>



<html>
<head>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script>

	var branchCombo = -1;

	function copyEngToGuj()
	{
		 
			 
		var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
		if(stausFlagVal == 1) // active
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[0].checked = true;
		if(stausFlagVal == 2) // deactive
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[1].checked = true;
		if(stausFlagVal == 3) // delete
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[2].checked = true;
			
		document.frmAdminCrtDsgn.rdoActiveFlag_gu[0].disabled = true;
		document.frmAdminCrtDsgn.rdoActiveFlag_gu[1].disabled = true;
		document.frmAdminCrtDsgn.rdoActiveFlag_gu[2].disabled = true;		
		
		document.getElementById('dsgnLevel_gu').value = document.getElementById('dsgnLevel').value;
		document.frmAdminCrtDsgn.dsgnLevel_gu.disabled = true;
		
		
		document.getElementById('dsgnStartDate_gu').value = document.getElementById('dsgnStartDate').value;
		document.frmAdminCrtDsgn.dsgnStartDate_gu.disabled = true;
		 
		
		
		document.getElementById('dsgnEndDate_gu').value = document.getElementById('dsgnEndDate').value;
		document.frmAdminCrtDsgn.dsgnEndDate_gu.disabled = true;
	 
		
	}
	
	function copyGujToEng()
	{
		 
		var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
		if(stausFlagVal == 1) // active
			document.frmAdminCrtDsgn.rdoActiveFlag[0].checked = true;
		if(stausFlagVal == 2) // deactive
			document.frmAdminCrtDsgn.rdoActiveFlag[1].checked = true;
		if(stausFlagVal == 3) // delete
			document.frmAdminCrtDsgn.rdoActiveFlag[2].checked = true;	
					
		document.frmAdminCrtDsgn.rdoActiveFlag[0].disabled = true;
		document.frmAdminCrtDsgn.rdoActiveFlag[1].disabled = true;
		document.frmAdminCrtDsgn.rdoActiveFlag[2].disabled = true;	
					
		
		document.getElementById('dsgnLevel').value = document.getElementById('dsgnLevel_gu').value;
		document.frmAdminCrtDsgn.dsgnLevel.disabled = true;
		
		
		document.getElementById('dsgnStartDate').value = document.getElementById('dsgnStartDate_gu').value;
		document.frmAdminCrtDsgn.dsgnStartDate.disabled = true;
		 
		
		document.getElementById('dsgnEndDate').value = document.getElementById('dsgnEndDate_gu').value;
		document.frmAdminCrtDsgn.dsgnEndDate.disabled = true;
		 
		
	}
	
	function getCheckedRadioValue(radioName)
	{
		var radioValue = "";
		
		objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
		
		for (iter=0;iter<objRadio.length;iter++)
		{
			if (objRadio[iter].checked)
			{
				radioValue = objRadio[iter].value;
				break;			
			}
		}
			
		return 	radioValue;
	}
	
	function submit_frmAdminCrtDsgn()
	{
		 
		if('${langId}' == '1')
		{
			copyEngToGuj();
			 		
			var fieldArrayEng1 = new Array('dsgnNameTxt', 'dsgnShrtNametxt','dsgnEndDate','dsgnStartDate'); // changed by divya
			var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
			
			if(statusValidationEng1)
			{				
				var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
				
				if(stausFlagVal == "")
				{
					alert("<fmt:message bundle='${userDsgnLables}' key='ORG.selActivateFlag'/>");
					return false;
				}
				else if('${multiLang}'=='false' || '${multiLang}'==false)
				{
					document.frmAdminCrtDsgn.action="hrms.htm?actionFlag=SubmitAdminDsgnData";
					document.frmAdminCrtDsgn.submit();
				}
				else
				{
					var fieldArrayGuj1 = new Array('dsgnLevel','dsgnNameTxt_gu', 'dsgnShrtNametxt_gu'); // 'locationCmb_gu', 'designationCmb_gu'); // changed by divya
					var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
					if(statusValidationGuj1)
					{
							document.frmAdminCrtDsgn.action="hrms.htm?actionFlag=SubmitAdminDsgnData";
							document.frmAdminCrtDsgn.submit();
						
					}						
				}					
			}
				
		 
		}else{
			copyGujToEng();
			var fieldArrayEng1 = new Array('dsgnNameTxt_gu', 'dsgnShrtNametxt_gu','dsgnEndDate_gu','dsgnStartDate_gu'); // changed by divya
			var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
			
			if(statusValidationEng1)
			{				
				var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
				
				if(stausFlagVal == "")
				{
					alert("<fmt:message bundle='${userDsgnLables}' key='ORG.selActivateFlag'/>");
					return false;
				}
				else if('${multiLang}'=='false' || '${multiLang}'==false)
				{
					document.frmAdminCrtDsgn.action="hrms.htm?actionFlag=SubmitAdminDsgnData";
					document.frmAdminCrtDsgn.submit();
				}
				else
				{
					var fieldArrayGuj1 = new Array('dsgnLevel_gu','dsgnNameTxt', 'dsgnShrtNametxt'); // 'locationCmb_gu', 'designationCmb_gu'); // changed by divya
					var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
					if(statusValidationGuj1)
					{
							document.frmAdminCrtDsgn.action="hrms.htm?actionFlag=SubmitAdminDsgnData";
							document.frmAdminCrtDsgn.submit();
						
					}						
				}					
			}
		}
		
	}
	
	
	
	
	function resetData() // added by divya
	{
		document.frmAdminCrtDsgn.dsgnNameTxt.value = '';
		document.frmAdminCrtDsgn.dsgnShrtNametxt.value = '';
		
		var rdoLength = document.frmAdminCrtDsgn.rdoActiveFlag.length;	
		for(var i = 0; i < rdoLength; i++)
		{
			document.frmAdminCrtDsgn.rdoActiveFlag[i].checked = false;
		}
		
		document.frmAdminCrtDsgn.dsgnNameTxt_gu.value = '';
		document.frmAdminCrtDsgn.dsgnShrtNametxt_gu.value = '';

		document.frmAdminCrtDsgn.dsgnLevel_gu.value = '';
		document.frmAdminCrtDsgn.dsgnLevel.value = '';

		document.frmAdminCrtDsgn.dsgnStartDate.value = '';
		document.frmAdminCrtDsgn.dsgnStartDate_gu.value = '';
		
		document.frmAdminCrtDsgn.dsgnEndDate.value = '';
		document.frmAdminCrtDsgn.dsgnEndDate_gu.value = '';
				
	
		var rdoLength = document.frmAdminCrtDsgn.rdoActiveFlag_gu.length;	
		for(var i = 0; i < rdoLength; i++)
		{
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[i].checked = false;
		}
	}
	
	function closeWindow()
	{
		document.frmAdminCrtDsgn.action = "hrms.htm?actionFlag=showAdminDsgnDtl";
	   	document.frmAdminCrtDsgn.submit();
	}
	
	function compareStartDateToEndDate()
	{		
	
		if(document.getElementById("dsgnStartDate").value!="" && document.getElementById("dsgnEndDate").value!="")
		{			
			var lFrmDate=document.getElementById("dsgnStartDate").value;							
			var lToDate=document.getElementById("dsgnEndDate").value;					
			if(compareDate(lFrmDate,lToDate) < 0 )
			{
				alert("<fmt:message bundle='${userDsgnLables}' key='ORG.validEndDate'/>");
				document.getElementById('dsgnEndDate').value='';				
			} 
		}
	}
	
	function compareStartDateToEndDateGu()
	{	
		if(document.getElementById("dsgnStartDate_gu").value!="" && document.getElementById("dsgnEndDate_gu").value!="")
		{			
			var lFrmDate=document.getElementById("dsgnStartDate_gu").value;							
			var lToDate=document.getElementById("dsgnEndDate_gu").value;					
			if(compareDate(lFrmDate,lToDate) < 0 )
			{
				alert("<fmt:message bundle='${userDsgnLables}' key='ORG.validEndDate'/>");
				document.getElementById('dsgnEndDate_gu').value='';				
			} 
		}
	}
</script>
</head>
<body>
<hdiits:form name="frmAdminCrtDsgn" action="hrms.htm?actionFlag=SubmitAdminDsgnData" method="post" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" >
	
		<c:if test="${langId == 1}">
			<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
				<fmt:message key="ORG.english" bundle="${userDsgnLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent2" onfocus="copyEngToGuj()"><b>
				<fmt:message key="ORG.gujarati" bundle="${userDsgnLables}"></fmt:message>
			</b></a></li>
		</c:if>
		<c:if test="${langId == 2}">
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent1"><b>
				<fmt:message key="ORG.gujarati" bundle="${userDsgnLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="eng"><a href="#" rel="tcontent2" onfocus="copyGujToEng()"><b>
				<fmt:message key="ORG.english" bundle="${userDsgnLables}"></fmt:message>
			</b></a></li>
		</c:if>
	
	</ul>
	</div>			
	<div id="createPost" name="createPost" class="tabcontentstyle" >
	<c:if test="${langId == 1}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
		<table width="100%" id="newDsgn_gu">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${userDsgnLables}' key='ORG.newDsgn'/></b></td></tr>
		</table>
		<table width="100%" id="editPost_gu" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${userDsgnLables}' key='Org.editDsgn'/></b></td></tr>
		</table>
		<table class="tabtable">				
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="ORG.dsgnName" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="dsgnNameTxt" id="dsgnNameTxt" mandatory="true" captionid="ORG.dsgnName" bundle="${userDsgnLables}" validation="txt.isrequired" maxlength="25"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="ORG.dsgnShortName" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="dsgnShrtNametxt" id="dsgnShortName" captionid="ORG.dsgnShortName" bundle="${userDsgnLables}" mandatory="true"  validation="txt.isrequired" maxlength="13"></hdiits:text></td>
			</tr>
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="ORG.startDate" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dsgnStartDate" captionid="ORG.startDate" bundle="${userDsgnLables}" afterDateSelect="compareStartDateToEndDate();" disabled="${langId == '1' ? false : true}" mandatory="true" validation="txt.isdt,txt.isrequired"   maxvalue="31/12/2099"></hdiits:dateTime></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="ORG.endDate" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dsgnEndDate" captionid="ORG.endDate" bundle="${userDsgnLables}" afterDateSelect="compareStartDateToEndDate();" disabled="${langId == '1' ? false : true}"  validation="txt.isdt"  maxvalue="31/12/2099"></hdiits:dateTime></td>
			</tr>
			<tr>
				<td class="fieldLabel">
				<b><hdiits:caption captionid="Org.activeFlag" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel" ><c:forEach var="StatusFlagValue" items="${arStatusFlag}">
					<hdiits:radio name="rdoActiveFlag" value="${StatusFlagValue.lookupId}" />${StatusFlagValue.lookupName}<b><label class="mandatoryindicator">*</label></b><br>										
					</c:forEach>
				</td>
				<td class="fieldLabel" style="display: none;"><b><hdiits:caption captionid="ORG.dsgnLevel" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel" style="display: none;"><hdiits:number name="dsgnLevel" id="dsgnLevel" maxlength="10" captionid="ORG.dsgnLevel" bundle="${userDsgnLables}" mandatory="true" style="text-align: right" validation="txt.isnumber,txt.isrequired" default="0"/>
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
		<table width="100%" id="newDsgn">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${userDsgnLables}' key='ORG.newDsgn'/></b></td></tr>
		</table>
		<table width="100%" id="editPost" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${userDsgnLables}' key='Org.editDsgn'/></b></td></tr>
		</table>	
		<table class="tabtable">
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="ORG.dsgnName" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="dsgnNameTxt_gu" id="dsgnNameTxt_gu" captionid="ORG.dsgnName" bundle="${userDsgnLables}" mandatory="true" validation="txt.isrequired" maxlength="25"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="ORG.dsgnShortName" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="dsgnShrtNametxt_gu" id="dsgnShrtNametxt_gu" captionid="ORG.dsgnShortName" bundle="${userDsgnLables}" mandatory="true" validation="txt.isrequired" maxlength="13"></hdiits:text></td>				
			</tr>
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="ORG.startDate" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dsgnStartDate_gu" captionid="ORG.startDate" bundle="${userDsgnLables}" afterDateSelect="compareStartDateToEndDateGu();" disabled="${langId == '1' ? true : false}" mandatory="true" validation="txt.isdt,txt.isrequired"  maxvalue="31/12/2099"></hdiits:dateTime></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="ORG.endDate" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dsgnEndDate_gu" captionid="ORG.endDate" bundle="${userDsgnLables}" afterDateSelect="compareStartDateToEndDateGu();" disabled="${langId == '1' ? true : false}" validation="txt.isdt"  maxvalue="31/12/2099"></hdiits:dateTime></td>
			</tr>
			<tr>
				<td class="fieldLabel" >
				<b><hdiits:caption captionid="Org.activeFlag" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel">
					<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
						<hdiits:radio name="rdoActiveFlag_gu" value="${StatusFlagValue.lookupId}" />${StatusFlagValue.lookupName}<b><label class="mandatoryindicator">*</label></b><br>										
					</c:forEach>
				</td>
				<td class="fieldLabel" style="display: none;"><b><hdiits:caption captionid="ORG.dsgnLevel" bundle="${userDsgnLables}"/></b></td>
				<td class="fieldLabel" style="display: none;"><hdiits:number name="dsgnLevel_gu" id="dsgnLevel_gu" maxlength="10"  captionid="ORG.dsgnLevel" bundle="${userDsgnLables}" style="text-align: right" mandatory="true" validation="txt.isnumber,txt.isrequired" default="0"/>
				</td>
			</tr>
			<!-- added by divya -- ends -->						
		</table>
			
	</div>
	<hdiits:hidden name="flag" id="flag" default="${flag}"/>
	<hdiits:hidden name="postId" id="postId" default=""/>	
		
	</div>
	<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${flag ne'edit'}">
					<br><hdiits:button name="Submit" type="button" captionid="ORG.submit" bundle="${userDsgnLables}" onclick="submit_frmAdminCrtDsgn()"></hdiits:button>
				</c:if>
				<c:if test="${flag eq 'edit'}">
					<br><hdiits:button name="Update" type="button" captionid="ORG.update" bundle="${userDsgnLables}" onclick="submit_frmAdminCrtDsgn()"></hdiits:button>
				</c:if>
			</td>
			<td align="center">
				<br><hdiits:button name="reset" type="button" captionid="ORG.reset" bundle="${userDsgnLables}" onclick="resetData()"></hdiits:button>
			</td>
			<td align="center">
				<br><hdiits:button name="Close" type="button" captionid="ORG.close" bundle="${userDsgnLables}" onclick="closeWindow()"></hdiits:button>
			</td>
		</tr>
	</table>
<hdiits:hidden name="langId" id="langId" default="${langId}"></hdiits:hidden>
<hdiits:hidden name="multiLangHdn" id="multiLangHdn" default="${multiLang}"></hdiits:hidden>	
<hdiits:hidden name="dsgnCode" id="dsgnCode" default="${dsgnCode}"></hdiits:hidden>	
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
		document.getElementById("eng").childNodes[0].childNodes[0].innerHTML = "Designation Creation";
	}
	
	document.getElementById("flag").value='${flag}';	

	document.frmAdminCrtDsgn.dsgnStartDate.readOnly = true;
	document.frmAdminCrtDsgn.dsgnStartDate_gu.readOnly = true;
	document.frmAdminCrtDsgn.dsgnEndDate.readOnly = true;
	document.frmAdminCrtDsgn.dsgnEndDate_gu.readOnly = true;
	if('${flag}'=='edit')
	{		
				
		document.getElementById('newDsgn').style.display = 'none';	
		document.getElementById('newDsgn_gu').style.display = 'none';
			
				
		document.getElementById('dsgnNameTxt').value='${designationMst.dsgnName}';
		document.getElementById('dsgnNameTxt_gu').value='${designationMst_gu.dsgnName}';
		
		document.getElementById('dsgnShrtNametxt').value='${designationMst.dsgnShrtName}';			
		document.getElementById('dsgnShrtNametxt_gu').value='${designationMst_gu.dsgnShrtName}';
		
		document.getElementById('dsgnLevel').value='${designationMst.dsgnLevel}';			
		document.getElementById('dsgnLevel_gu').value='${designationMst_gu.dsgnLevel}';
		
		var startDate='${designationMst.startDate}';
		var endDate='${designationMst.endDate}';
		
		var dateArray=getDateAndTimeFromDateObj(startDate);
		
		document.getElementById('dsgnStartDate').value=dateArray[0];
		document.getElementById('dsgnStartDate_gu').value=dateArray[0];
		
		dateArray=getDateAndTimeFromDateObj(endDate);
		
		document.getElementById('dsgnEndDate').value=dateArray[0];
		document.getElementById('dsgnEndDate_gu').value=dateArray[0];
		
		var stausFlagVal = '${designationMst.activateFlag}'; 			
		if(stausFlagVal == 1) // active
		{
			document.frmAdminCrtDsgn.rdoActiveFlag[0].checked = true;
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[0].checked = true;
		}
		if(stausFlagVal == 2) // deactive
		{
			document.frmAdminCrtDsgn.rdoActiveFlag[1].checked = true;
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[1].checked = true;
		}
		if(stausFlagVal == 3) // delete
		{
			document.frmAdminCrtDsgn.rdoActiveFlag[2].checked = true;
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[2].checked = true;	
		}
		
		/* added by divya -- ends */	
	}else{
		if('${multiLang}'!='false' || '${multiLang}'!=false)
		{
			document.getElementById('dsgnStartDate').value='${startDateStr}';
			document.getElementById('dsgnStartDate_gu').value='${startDateStr}';
			
			document.frmAdminCrtDsgn.rdoActiveFlag[0].checked = true;
			document.frmAdminCrtDsgn.rdoActiveFlag_gu[0].checked = true;	
		}else
		{
			document.getElementById('dsgnStartDate').value='${startDateStr}';
			document.frmAdminCrtDsgn.rdoActiveFlag[0].checked = true;
		}
	}
</script>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>			
<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="cmnCountryMstList_Eng" value="${resValue.cmnCountryMstList_Eng}"> </c:set>
<c:set var="cmnCountryMstList_Guj" value="${resValue.cmnCountryMstList_Guj}"> </c:set>
<c:set var="flag" value="${resValue.flag}"> </c:set>
<c:set var="MULTI_LANG" value="${resValue.MULTI_LANG}"> </c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<c:set var="saveFlag" value="${resValue.saveFlag}"></c:set>
<c:set var="countryCode" value="${resValue.countryCode}"></c:set>
<c:set var="stateCode" value="${resValue.stateCode}"></c:set>
<c:set var="activateFlag" value="${resValue.activateFlag}"></c:set>
<c:set var="districtCode" value="${resValue.districtCode}"></c:set>
<c:set var="talukaCode" value="${resValue.talukaCode}"></c:set>
<c:set var="villageNameEng" value="${resValue.villageNameEng}"></c:set>
<c:set var="villageNameGuj" value="${resValue.villageNameGuj}"></c:set>
<c:set var="villageId" value="${resValue.villageId}"></c:set>
<c:set var="defaultCountryCode" value="${resValue.defaultCountryCode}"></c:set>
<c:set var="villageCode" value="${resValue.villageCode}"></c:set>
<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

<html>
<head>	
<script type="text/javascript">
var currDistrictCode="";
var currTalukaCode="";
//added by uzma
var villageArray;
var alertArray=new Array();
alertArray[0]="<fmt:message bundle='${mstScrLables}' key='MstScr.villageName'/>";
alertArray[1]="<fmt:message bundle='${mstScrLables}' key='MstScr.AlreadyExist'/>";
alertArray[2]="<fmt:message bundle='${mstScrLables}' key='MstScr.inGuj'/>";
alertArray[3]="<fmt:message bundle='${mstScrLables}' key='MstScr.Guj'/>";
alertArray[4]="<fmt:message bundle='${mstScrLables}' key='MstScr.inEng'/>";
alertArray[5]="<fmt:message bundle='${mstScrLables}' key='MstScr.Eng'/>";
alertArray[6]="<fmt:message bundle='${mstScrLables}' key='MstScr.inEngGuj'/>";
alertArray[7]="<fmt:message bundle='${mstScrLables}' key='MstScr.EngGuj'/>";

function copyEngToGuj()
{
	document.getElementById('country_Guj').value = document.getElementById('country_Eng').value;
	document.getElementById('country_Guj').disabled = true;
	document.getElementById('state_Guj').value = document.getElementById('state_Eng').value;
	document.getElementById('district_Guj').value = document.getElementById('district_Eng').value;
	document.getElementById('taluka_Guj').value = document.getElementById('taluka_Eng').value;
	document.getElementById('state_Guj').disabled = true;
	document.getElementById('district_Guj').disabled = true;
	document.getElementById('taluka_Guj').disabled = true;
	checkGujaratiDropdownValue(document.getElementById('country_Guj'));
	checkGujaratiDropdownValue(document.getElementById('state_Guj'));
	checkGujaratiDropdownValue(document.getElementById('district_Guj'));
	checkGujaratiDropdownValue(document.getElementById('taluka_Guj'));
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlagEng");
	
	if(stausFlagVal == "ACTIVE STATUS") // active
			document.createVillage.rdoActiveFlagGuj[0].checked = true;
	if(stausFlagVal == "DEACTIVE STATUS") // deactive
			document.createVillage.rdoActiveFlagGuj[1].checked = true;
	if(stausFlagVal == "DELETE") // delete
			document.createVillage.rdoActiveFlagGuj[2].checked = true;
	
	document.createVillage.rdoActiveFlagGuj[0].disabled = true;
	document.createVillage.rdoActiveFlagGuj[1].disabled = true;
	document.createVillage.rdoActiveFlagGuj[2].disabled = true;
}
	

function copyGujToEng()
{	
	document.getElementById('country_Eng').value = document.getElementById('country_Guj').value;
	document.getElementById('country_Eng').disabled = true;
	document.getElementById('state_Eng').value = document.getElementById('state_Guj').value ;
	document.getElementById('state_Eng').disabled = true;	
	document.getElementById('district_Eng').value = document.getElementById('district_Guj').value;
	document.getElementById('district_Eng').disabled = true;
	document.getElementById('taluka_Eng').value = document.getElementById('taluka_Guj').value;
	document.getElementById('taluka_Eng').disabled = true;
	checkGujaratiDropdownValue(document.getElementById('country_Eng'));//added by uzma
	checkGujaratiDropdownValue(document.getElementById('state_Eng'));//added by uzma
	checkGujaratiDropdownValue(document.getElementById('district_Eng'));//added by uzma
	checkGujaratiDropdownValue(document.getElementById('taluka_Eng'));//added by uzma
		
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlagGuj");
	if(stausFlagVal == "ACTIVE STATUS") // active
			document.createVillage.rdoActiveFlagEng[0].checked = true;
	if(stausFlagVal == "DEACTIVE STATUS") // deactive
			document.createVillage.rdoActiveFlagEng[1].checked = true;
	if(stausFlagVal == "DELETE") // delete
			document.createVillage.rdoActiveFlagEng[2].checked = true;
	
	document.createVillage.rdoActiveFlagEng[0].disabled = true;
	document.createVillage.rdoActiveFlagEng[1].disabled = true;
	document.createVillage.rdoActiveFlagEng[2].disabled = true;
}




function submitForm(){

	if(document.getElementById('villageName_Eng').value == ''){
		
		selectRequiredTab('villageName_Eng');
		alert("<fmt:message key="MstScr.villageNameEng" bundle='${mstScrLables}' />");
		document.getElementById('villageName_Eng').focus();
		
		return;
	}
	//change by uzma
	if(document.getElementById('state_Eng').value == 'select'){
		
		selectRequiredTab('state_Eng');
		alert("<fmt:message key="MstScr.dstStateEng" bundle='${mstScrLables}' />");
		document.getElementById('state_Eng').focus();
		return;
	}

	if(document.getElementById('district_Eng').value == 'select'){
		
		selectRequiredTab('district_Eng');
		alert("<fmt:message key="MstScr.districtNameEng" bundle='${mstScrLables}' />");
		document.getElementById('district_Eng').focus();
		return;
		
	}
	
	if(document.getElementById('taluka_Eng').value == 'select'){
		
		selectRequiredTab('taluka_Eng');
		alert("<fmt:message key="MstScr.talukaNameEng" bundle='${mstScrLables}' />");
		document.getElementById('taluka_Eng').focus();
		return;
		
	}
	
	var activeFlagEng = getCheckedRadioValue("rdoActiveFlagEng");
	
	if(activeFlagEng==''){
	
		selectRequiredTab('rdoActiveFlagEng');
		alert("<fmt:message key="MstScr.actFlagEng" bundle='${mstScrLables}' />");
		return;
	}
	
	if('${langId}' == 1 && '${MULTI_LANG}' == 'Y'){
	copyEngToGuj();	
	}
	else if('${langId}' == 2 && '${MULTI_LANG}' == 'Y'){
	
	copyGujToEng();
	}
	
	if('${MULTI_LANG}' == 'Y'){
	
		if(document.getElementById('villageName_Guj').value == ''){
		
			selectRequiredTab('villageName_Guj');
			alert("<fmt:message key="MstScr.villageNameEng" bundle='${mstScrLables}' />");
			document.getElementById('villageName_Guj').focus();
			return;
		
		}
	
		if(document.getElementById('state_Guj').value == 'select'){
	
			selectRequiredTab('state_Guj');
			alert("<fmt:message key="MstScr.dstStateEng" bundle='${mstScrLables}' />");
			//document.getElementById('state_Guj').focus();
			return;

		}
		
		var activeFlagGuj = getCheckedRadioValue("rdoActiveFlagGuj");
	
		if(activeFlagGuj==''){
	
			selectRequiredTab('rdoActiveFlagGuj');
			alert("<fmt:message key="MstScr.actFlagEng" bundle='${mstScrLables}' />");
			return;
		}
		
		if(document.getElementById('district_Guj').value == 'select'){
	
			selectRequiredTab('district_Guj');
			alert("<fmt:message key="MstScr.districtNameEng" bundle='${mstScrLables}' />");
			//document.getElementById('district_Guj').focus();
			return;
		
		}
	
		if(document.getElementById('taluka_Guj').value == 'select'){
		
		selectRequiredTab('taluka_Guj');
		alert("<fmt:message key="MstScr.talukaNameEng" bundle='${mstScrLables}' />");
		//document.getElementById('taluka_Guj').focus();
		return;
		
		}
		
	}

	if('${langId}' == 2){
		
		document.getElementById('country_Eng').disabled = false;
		document.getElementById('state_Eng').disabled = false;
		document.getElementById('district_Eng').disabled = false;
		document.getElementById('taluka_Eng').disabled = false;
		document.createVillage.rdoActiveFlagEng[0].disabled = false;
		document.createVillage.rdoActiveFlagEng[1].disabled = false;
		document.createVillage.rdoActiveFlagEng[2].disabled = false;
	
	}
	processSubmit();//added by uzma
}


function resetData(){

	document.getElementById('villageName_Eng').value = '';
	document.getElementById('country_Eng').value = '${defaultCountryCode}';
	document.createVillage.rdoActiveFlagEng[0].checked = true;

	if('${MULTI_LANG}' == 'Y'){

		document.getElementById('villageName_Guj').value = '';
		document.getElementById('country_Guj').value = '${defaultCountryCode}';
		document.createVillage.rdoActiveFlagGuj[0].checked = true;
	}

	if('${flag}' == 'edit' || '${saveFlag}' == 'true')
	{
		document.getElementById('country_Eng').value = '${countryCode}';
		if('${MULTI_LANG}' == 'Y'){
			document.getElementById('country_Guj').value = '${countryCode}';
		}
	}
	fillState();
	if('${flag}' == 'edit' || '${saveFlag}' == 'true')
	{	
		currDistrictCode='${districtCode}';
		currTalukaCode='${talukaCode}';
		document.getElementById('villageName_Eng').value= '${villageNameEng}';
		document.getElementById('state_Eng').value = '${stateCode}';
		
		if('${MULTI_LANG}' == 'Y'){
		
			document.getElementById('state_Guj').value = '${stateCode}';
		}
		
		fillDistrict();
		document.getElementById('district_Eng').value = '${districtCode}';
		
		if('${MULTI_LANG}' == 'Y'){
		
			document.getElementById('district_Guj').value = '${districtCode}';
		}
		
		
		fillTaluka();
		document.getElementById('taluka_Eng').value = '${talukaCode}';
		currDistrictCode="";
		currTalukaCode="";
		
		if('${MULTI_LANG}' == 'Y'){
		
			document.getElementById('taluka_Guj').value = '${talukaCode}';
		}
		
		
		if('${activateFlag}' == "ACTIVE STATUS") // active
			document.createVillage.rdoActiveFlagEng[0].checked = true;
		if('${activateFlag}' == "DEACTIVE STATUS") // deactive
			document.createVillage.rdoActiveFlagEng[1].checked = true;
		if('${activateFlag}' == "DELETE") // delete
			document.createVillage.rdoActiveFlagEng[2].checked = true;
		
		
		if('${MULTI_LANG}' == 'Y'){
			
			if('${activateFlag}' == "ACTIVE STATUS"){ // active
				document.createVillage.rdoActiveFlagGuj[0].checked = true;
			}
			if('${activateFlag}' == "DEACTIVE STATUS"){ // deactive
				document.createVillage.rdoActiveFlagGuj[1].checked = true;
			}
			if('${activateFlag}' == "DELETE"){ // delete
				document.createVillage.rdoActiveFlagGuj[2].checked = true;
			}
			
			
			document.getElementById('villageName_Guj').value= '${villageNameGuj}';
			
			document.getElementById('country_Guj').value = '${countryCode}';
			
			
		}
		
		document.getElementById('multiLang').value = '${MULTI_LANG}';
		
	}
	if('${flag}' == 'edit')
	{
		globalFlagType = "E";
		document.getElementById('submitButton').style.display = 'none';
		document.getElementById('updateButton').style.display = '';
		document.getElementById('villageId').value = '${villageId}';
		document.getElementById('flag').value = '${flag}';
	}
	generateInitCapName('villageName_Eng');
}
//added by uzma
function compareVillageNames()
{
	if('${MULTI_LANG}'=='Y')
	{
		var villageName=document.getElementById('villageName_Eng').value;
		var villageName_gu=document.getElementById('villageName_Guj').value;

		if(villageName.toLowerCase() == villageName_gu.toLowerCase())
		{
			alert("<fmt:message bundle='${mstScrLables}' key='MstScr.villageEngGujNm'/>");
			return false;
		}
	}
	return true;
}
function createArrayToPassValue()
{ 
	var flag = document.getElementById('flag').value;
	var langID = document.getElementById('langId').value;
	var multiLangVal = document.getElementById('multiLang').value;

	if(flag=='create' && multiLangVal == 'N')
	{
		villageArray =new Array('flag','villageName_Eng','multiLang');
	}
	else if(flag=='create' && multiLangVal == 'Y')
	{
		villageArray = new Array('flag','villageName_Eng','villageName_Guj','multiLang');
	}
	else if(flag=='edit' && multiLangVal == 'N')
	{
		villageArray = new Array('flag','villageCode','villageName_Eng','multiLang');
	}
	else if(flag=='edit' && multiLangVal == 'Y')	
	{
		villageArray = new Array('flag','villageCode','villageName_Eng','villageName_Guj','multiLang');
	}
}
function processSubmit()
{
	if(compareVillageNames())
	{
		showProgressbar();
		createArrayToPassValue();
		addOrUpdateRecord("processResponseForValidateVillageNm","validateVillageName",villageArray,false);
	}
}
function processResponseForValidateVillageNm()
{
	var submitActionFlag='hdiits.htm?actionFlag=setCmnVillageMstData';
	var formName=document.createVillage;
	processResponseForValidatingUniqueNm(submitActionFlag,alertArray,formName);
}
function saveData()
{
	submitForm();
}

</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<hdiits:form name="createVillage" action="" method="post" validate="true" encType="multipart/form-data" >
	
	<c:if test="${MULTI_LANG eq 'Y'}">	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		
		<c:if test="${langId == 1}">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<b><fmt:message bundle="${mstScrLables}" key="MstScr.English"></fmt:message></b>
			</a>
		</li>	
		<li class="selected">
			<a href="#" rel="tcontent2" onfocus="copyEngToGuj()">
				<b><fmt:message bundle="${mstScrLables}" key="MstScr.Gujarati"></fmt:message></b>
			</a>
		</li>
		</c:if>
		<c:if test="${langId == 2}">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<b><fmt:message bundle="${mstScrLables}" key="MstScr.Gujarati"></fmt:message></b>
			</a>
		</li>	
		<li class="selected">
			<a href="#" rel="tcontent2" onfocus="copyGujToEng()">
				<b><fmt:message bundle="${mstScrLables}" key="MstScr.English"></fmt:message></b>
			</a>
		</li>		
		</c:if>
				
	</ul>
	
	</div>
	</c:if>
	<c:if test="${MULTI_LANG eq 'N'}">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	
	<li class="selected">
			<a href="#" rel="tcontent1">
				<b>Village Creation</b>
			</a>
		</li>
	</ul>
	
	</div>	
	</c:if>
	
	<div class="tabcontentstyle" style="height: 100%">
	
	<c:if test="${MULTI_LANG eq 'Y'}">	
		
		<c:if test="${langId == 1}">
			<div id="tcontent1" class="tabcontent" tabno="0">
		</c:if>
	
		<c:if test="${langId == 2}">
			<div id="tcontent2" class="tabcontent" tabno="1">
		</c:if>
	</c:if>
	
	<c:if test="${MULTI_LANG eq 'N'}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
	
	<br>
	<c:if test="${saveFlag eq true}">
		<center><b><font color="red"><fmt:message key="MstScr.saveMsgEng" bundle="${mstScrLables}"></fmt:message></font></b></center><br>
	</c:if>
	
	<c:if test="${flag eq 'create'}">	
	<center><b><fmt:message key="MstScr.newVillageEng" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<c:if test="${flag eq 'edit'}">	
	<center><b><fmt:message key="MstScr.editVillageEng" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<br>
	<table align="center" id="newVillage_Eng" width="100%">
	<tr>
	<td width="25%"><hdiits:caption captionid="MstScr.villageName" bundle="${mstScrLables}" /></td>
	<td  width="25%"><hdiits:text name="villageName_Eng" id="villageName_Eng" mandatory="true" captionid="MstScr.villageName" bundle="${mstScrLables}" maxlength="45" onblur="generateInitCapName('villageName_Eng');"/></td>
	<td width="25%"><hdiits:caption captionid="MstScr.countryName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="country_Eng" mandatory="true" onchange="fillState()" captionid="MstScr.countryName" bundle="${mstScrLables}" sort="false">
		<c:forEach items="${cmnCountryMstList_Eng}" var="countryList">
						<option value="<c:out value="${countryList.countryCode}"/>">
						<c:out value="${countryList.countryName}" /></option>
		</c:forEach>	
		</hdiits:select>
	</td>
	</tr>
	<tr>
	
	<td width="25%"><hdiits:caption captionid="MstScr.stateName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="state_Eng" id="state_Eng" mandatory="true" onchange="fillDistrict()" sort="false" captionid="MstScr.stateName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectEng" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	<td width="25%"><hdiits:caption captionid="MstScr.districtName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="district_Eng" id="district_Eng" mandatory="true" onchange="fillTaluka()" sort="false" captionid="MstScr.districtName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectEng" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	</tr>
	<tr>
	
	<td width="25%"><hdiits:caption captionid="MstScr.talukaName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="taluka_Eng" id="taluka_Eng" mandatory="true" sort="false" captionid="MstScr.talukaName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectEng" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	<td width="25%"><hdiits:caption captionid="MstScr.activateFlag" bundle="${mstScrLables}" /></td>
	<td width="25%">
				<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
					<hdiits:radio name="rdoActiveFlagEng" value="${StatusFlagValue.lookupName}" mandatory="true" />${StatusFlagValue.lookupName}<br>										
				</c:forEach>
	</td>
	</tr>
	</table>
	</div>
	<c:if test="${MULTI_LANG eq 'Y'}">
		<c:if test="${langId == 1}">
			<div id="tcontent2" class="tabcontent" tabno="1">
		</c:if>
	
		<c:if test="${langId == 2}">
			<div id="tcontent1" class="tabcontent" tabno="0">
		</c:if>
	<br>
	<c:if test="${saveFlag eq true}">
		<center><b><font color="red"><fmt:message key="MstScr.saveMsgGuj" bundle="${mstScrLables}"></fmt:message></font></b></center><br>
	</c:if>
	<c:if test="${flag eq 'create'}">	
	<center><b><fmt:message key="MstScr.newVillageGuj" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<c:if test="${flag eq 'edit'}">	
	<center><b><fmt:message key="MstScr.editVillageGuj" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<br>
	<table align="center" id="newVillage_Guj" width="100%">
	<tr>
	<td width="25%"><hdiits:caption captionid="MstScr.villageName" bundle="${mstScrLables}" /></td>
	<td width="25%"><hdiits:text name="villageName_Guj" id="villageName_Guj" mandatory="true" captionid="MstScr.villageName" bundle="${mstScrLables}" maxlength="45"/></td>
	<td width="25%"><hdiits:caption captionid="MstScr.countryName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="country_Guj" mandatory="true" onchange="fillState()" captionid="MstScr.countryName" bundle="${mstScrLables}" sort="false">
		<c:forEach items="${cmnCountryMstList_Guj}" var="countryList">
						<option value="<c:out value="${countryList.countryCode}"/>">
						<c:out value="${countryList.countryName}" /></option>
		</c:forEach>	
		</hdiits:select>
	</td>
	</tr>
	<tr>
	
	<td width="25%"><hdiits:caption captionid="MstScr.stateName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="state_Guj" mandatory="true" onchange="fillDistrict()" sort="false" captionid="MstScr.stateName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectGuj" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	<td width="25%"><hdiits:caption captionid="MstScr.districtName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="district_Guj" id="district_Guj" mandatory="true" onchange="fillTaluka()" sort="false" captionid="MstScr.districtName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectGuj" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	</tr>
	<tr>
	<td width="25%"><hdiits:caption captionid="MstScr.talukaName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="taluka_Guj" id="taluka_Guj" mandatory="true" sort="false" captionid="MstScr.talukaName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectEng" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	<td width="25%"><hdiits:caption captionid="MstScr.activateFlag" bundle="${mstScrLables}" /></td>
	<td width="25%">
				<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
					<hdiits:radio name="rdoActiveFlagGuj" value="${StatusFlagValue.lookupName}" mandatory="true"/>${StatusFlagValue.lookupName}<br>										
				</c:forEach>
	</td>
	</tr>
	</table>
	
	<br>
	
	
	
	</div>
	</c:if>
	<center><hdiits:button name="submitButton" id="submitButton" type="button" captionid="MstScr.submit" bundle="${mstScrLables}" onclick="submitForm()"/>
	<hdiits:button name="updateButton" id="updateButton" type="button" captionid="MstScr.update" bundle="${mstScrLables}" onclick="submitForm()" style="display:none"/>
	<hdiits:button name="resetButton" id="resetButton" type="button" captionid="MstScr.reset" bundle="${mstScrLables}" onclick="resetData()" />
	<hdiits:button name="closeButton" id="closeButton" captionid="MstScr.close" type="button" bundle="${mstScrLables}"  onclick="closeForm('getCmnVillageMstList')"/></center>
	<hdiits:hidden name="flag" id="flag" default="create"/>
	<hdiits:hidden name="villageId" id="villageId" default="0"/>
	<hdiits:hidden name="multiLang" id="multiLang" default="${MULTI_LANG}"/>
	<hdiits:hidden name="langId" id="langId" default="${langId}"/>
	<hdiits:hidden name="villageCode" id="villageCode" default="${villageCode}"/>
	</div>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript">
	initializetabcontent("maintab")	

document.createVillage.rdoActiveFlagEng[0].checked= true;
if('${MULTI_LANG}' == 'Y'){
document.createVillage.rdoActiveFlagGuj[0].checked = true;
}
resetData();
if('${langId}' == 1)
{
	document.getElementById('villageName_Eng').focus();
}
else if('${langId}' == 2)
{
	document.getElementById('villageName_Guj').focus();
}
</script>

</hdiits:form>
</body>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>		


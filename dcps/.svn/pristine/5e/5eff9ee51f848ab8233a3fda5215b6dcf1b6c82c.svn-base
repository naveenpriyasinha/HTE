<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="districtType_Eng" value="${resValue.districtType_Eng}"> </c:set>
<c:set var="districtType_Guj" value="${resValue.districtType_Guj}"> </c:set>

<c:set var="cmnCountryMstList_Eng" value="${resValue.cmnCountryMstList_Eng}"> </c:set>
<c:set var="cmnCountryMstList_Guj" value="${resValue.cmnCountryMstList_Guj}"> </c:set>
<c:set var="defaultCountryCode" value="${resValue.defaultCountryCode}"></c:set>
<c:set var="flag" value="${resValue.flag}"> </c:set>
<c:set var="districtCode" value="${resValue.districtCode}"></c:set>
<c:set var="MULTI_LANG" value="${resValue.MULTI_LANG}"> </c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

<html>
<head>	
<script type="text/javascript">
//added by uzma
var districtArray;
var alertArray=new Array();
alertArray[0]="<fmt:message bundle='${mstScrLables}' key='MstScr.districtName'/>";
alertArray[1]="<fmt:message bundle='${mstScrLables}' key='MstScr.AlreadyExist'/>";
alertArray[2]="<fmt:message bundle='${mstScrLables}' key='MstScr.inGuj'/>";
alertArray[3]="<fmt:message bundle='${mstScrLables}' key='MstScr.Guj'/>";
alertArray[4]="<fmt:message bundle='${mstScrLables}' key='MstScr.inEng'/>";
alertArray[5]="<fmt:message bundle='${mstScrLables}' key='MstScr.Eng'/>";
alertArray[6]="<fmt:message bundle='${mstScrLables}' key='MstScr.inEngGuj'/>";
alertArray[7]="<fmt:message bundle='${mstScrLables}' key='MstScr.EngGuj'/>";

function copyEngToGuj()
{
	document.getElementById('districtType_Guj').value = document.getElementById('districtType_Eng').value;
	checkGujaratiDropdownValue(document.getElementById("districtType_Guj"));//added by uzma
	document.getElementById('districtType_Guj').disabled = true;
	document.getElementById('country_Guj').value = document.getElementById('country_Eng').value;
	checkGujaratiDropdownValue(document.getElementById("country_Guj"));//added by uzma
	document.getElementById('country_Guj').disabled = true;
	document.getElementById('state_Guj').value = document.getElementById('state_Eng').value;
	checkGujaratiDropdownValue(document.getElementById("state_Guj"));//added by uzma
	document.getElementById('state_Guj').disabled = true;
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlagEng");
	
	if(stausFlagVal == "ACTIVE STATUS") // active
			document.createDistrict.rdoActiveFlagGuj[0].checked = true;
	if(stausFlagVal == "DEACTIVE STATUS") // deactive
			document.createDistrict.rdoActiveFlagGuj[1].checked = true;
	if(stausFlagVal == "DELETE") // delete
			document.createDistrict.rdoActiveFlagGuj[2].checked = true;
	
	document.createDistrict.rdoActiveFlagGuj[0].disabled = true;
	document.createDistrict.rdoActiveFlagGuj[1].disabled = true;
	document.createDistrict.rdoActiveFlagGuj[2].disabled = true;
}
	

function copyGujToEng()
{	
	document.getElementById('districtType_Eng').value =	document.getElementById('districtType_Guj').value;
	checkGujaratiDropdownValue(document.getElementById("districtType_Eng"));//added by uzma
	document.getElementById('districtType_Eng').disabled = true;
	document.getElementById('country_Eng').value = document.getElementById('country_Guj').value;
	checkGujaratiDropdownValue(document.getElementById("country_Eng"));//added by uzma
	document.getElementById('country_Eng').disabled = true;
	document.getElementById('state_Eng').value = document.getElementById('state_Guj').value ;
	checkGujaratiDropdownValue(document.getElementById("state_Eng"));//added by uzma
	document.getElementById('state_Eng').disabled = true;	
	
	var stausFlagVal = getCheckedRadioValue("rdoActiveFlagGuj");
	if(stausFlagVal == "ACTIVE STATUS") // active
			document.createDistrict.rdoActiveFlagEng[0].checked = true;
	if(stausFlagVal == "DEACTIVE STATUS") // deactive
			document.createDistrict.rdoActiveFlagEng[1].checked = true;
	if(stausFlagVal == "DELETE") // delete
			document.createDistrict.rdoActiveFlagEng[2].checked = true;
	
	document.createDistrict.rdoActiveFlagEng[0].disabled = true;
	document.createDistrict.rdoActiveFlagEng[1].disabled = true;
	document.createDistrict.rdoActiveFlagEng[2].disabled = true;
}


function submitForm(){

	if(document.getElementById('districtName_Eng').value == ''){
		
		selectRequiredTab('districtName_Eng');
		alert("<fmt:message key="MstScr.dstNameEng" bundle='${mstScrLables}' />");
		document.getElementById('districtName_Eng').focus();
		return;
	}
	//changed by uzma
	if(document.getElementById('state_Eng').value == 'select'){
		
		selectRequiredTab('state_Eng');
		alert("<fmt:message key="MstScr.dstStateEng" bundle='${mstScrLables}' />");
		document.getElementById('state_Eng').focus();
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
	
		if(document.getElementById('districtName_Guj').value == ''){
	
			selectRequiredTab('districtName_Guj');
			alert("<fmt:message key="MstScr.dstNameEng" bundle='${mstScrLables}' />");
			document.getElementById('districtName_Guj').focus();
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
		
	
	
	}

	if('${langId}' == 2){
		
		document.getElementById('districtType_Eng').disabled = false;
		document.getElementById('country_Eng').disabled = false;
		document.getElementById('state_Eng').disabled = false;	
		document.createDistrict.rdoActiveFlagEng[0].disabled = false;
		document.createDistrict.rdoActiveFlagEng[1].disabled = false;
		document.createDistrict.rdoActiveFlagEng[2].disabled = false;
	
	}
	processSubmit();//added by uzma
}
//added by uzma
function compareDistrictNames()
{
	if('${MULTI_LANG}'=='Y')
	{
		var districtName=document.getElementById('districtName_Eng').value;
		var districtName_gu=document.getElementById('districtName_Guj').value;

		if(districtName.toLowerCase() == districtName_gu.toLowerCase())
		{
			alert("<fmt:message bundle='${mstScrLables}' key='MstScr.districtEngGujNm'/>");
			return false;
		}
	}
	return true;
}
function createArrayToPassValue()
{
	 
	var flag=document.getElementById('flag').value;
	var langID = document.getElementById('langId').value;
	var multiLangVal = document.getElementById('multiLang').value;

	if(flag=='create' && multiLangVal == 'N')
	{
		districtArray =new Array('flag','districtName_Eng','multiLang');
	}
	else if(flag=='create' && multiLangVal == 'Y')
	{
		districtArray = new Array('flag','districtName_Eng','districtName_Guj','multiLang');
	}
	else if(flag=='edit' && multiLangVal == 'N')
	{
		districtArray = new Array('flag','districtCode','districtName_Eng','multiLang');
	}
	else if(flag=='edit' && multiLangVal == 'Y')	
	{
		districtArray = new Array('flag','districtCode','districtName_Eng','districtName_Guj','multiLang');
	}
}
function processSubmit()
{
	if(compareDistrictNames())
	{
		showProgressbar();
		createArrayToPassValue();
		addOrUpdateRecord("processResponseForValidateDistNm","validateDistName",districtArray,false);
	}
}
function processResponseForValidateDistNm()
{
	var submitActionFlag='hdiits.htm?actionFlag=setCmnDistrictMstData';
	var formName=document.createDistrict;
	processResponseForValidatingUniqueNm(submitActionFlag,alertArray,formName);
}
function saveData()
{
	submitForm();
}

</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<hdiits:form name="createDistrict" action="" method="post" validate="true" encType="multipart/form-data" >
	
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
				<b>Gujarati</b>
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
				<b>District Creation</b>
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
	<c:if test="${flag eq 'create'}">	
	<center><b><fmt:message key="MstScr.newDistrictEng" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<c:if test="${flag eq 'edit'}">	
	<center><b><fmt:message key="MstScr.editDistrictEng" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<br>
	<table align="center" id="newDistrict_Eng" width="100%">
	<tr>
	<td width="25%"><hdiits:caption captionid="MstScr.districtName" bundle="${mstScrLables}" /></td>
	<td width="25%"><hdiits:text name="districtName_Eng" id="districtName_Eng" mandatory="true" captionid="MstScr.districtName" bundle="${mstScrLables}" maxlength="45" onblur="generateInitCapName('districtName_Eng')"/></td>
	 
	
	<td width="25%"><hdiits:caption captionid="MstScr.districtType" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="districtType_Eng" id="districtType_Eng" mandatory="true" captionid="MstScr.districtType" bundle="${mstScrLables}" sort="false">
		<c:forEach items="${districtType_Eng}" var="districtType">
						<option value="<c:out value="${districtType.lookupName}"/>">
						<c:out value="${districtType.lookupDesc}" /></option>
		</c:forEach>	
		</hdiits:select>
	</td>
	</tr>
 
	<tr>
	<td width="25%"><hdiits:caption captionid="MstScr.countryName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="country_Eng" mandatory="true" onchange="fillState()" captionid="MstScr.countryName" bundle="${mstScrLables}" sort="false">
		<c:forEach items="${cmnCountryMstList_Eng}" var="countryList">
						<option value="<c:out value="${countryList.countryCode}"/>">
						<c:out value="${countryList.countryName}" /></option>
		</c:forEach>	
		</hdiits:select>
	</td>
	 
	
	<td width="25%"><hdiits:caption captionid="MstScr.stateName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="state_Eng" id="state_Eng" mandatory="true" sort="false" captionid="MstScr.stateName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectEng" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	</tr>
	 
	<tr>
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
	<c:if test="${flag eq 'create'}">	
	<center><b><fmt:message key="MstScr.newDistrictGuj" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<c:if test="${flag eq 'edit'}">	
	<center><b><fmt:message key="MstScr.editDistrictGuj" bundle="${mstScrLables}"/></b></center>
	</c:if>
	<br>
	<table align="center" id="newDistrict_Guj" width="100%">
	<tr>
	<td width="25%"><hdiits:caption captionid="MstScr.districtName" bundle="${mstScrLables}" /></td><td width="25%"><hdiits:text name="districtName_Guj" id="districtName_Guj" mandatory="true" captionid="MstScr.districtName" bundle="${mstScrLables}" maxlength="45"/></td>
	 
	
	<td width="25%"><hdiits:caption captionid="MstScr.districtType" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="districtType_Guj" id="districtType_Guj" mandatory="true" captionid="MstScr.districtType" bundle="${mstScrLables}" sort="false">
		<c:forEach items="${districtType_Guj}" var="districtType">
						<option value="<c:out value="${districtType.lookupName}"/>">
						<c:out value="${districtType.lookupDesc}" /></option>
		</c:forEach>	
		</hdiits:select>
	</td>
	</tr>
 
	<tr>
	<td width="25%"><hdiits:caption captionid="MstScr.countryName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="country_Guj" mandatory="true" onchange="fillState()" captionid="MstScr.countryName" bundle="${mstScrLables}" sort="false">
		<c:forEach items="${cmnCountryMstList_Guj}" var="countryList">
						<option value="<c:out value="${countryList.countryCode}"/>">
						<c:out value="${countryList.countryName}" /></option>
		</c:forEach>	
		</hdiits:select>
	</td>
 
	
	<td width="25%"><hdiits:caption captionid="MstScr.stateName" bundle="${mstScrLables}" /></td>
	<td width="25%">
		<hdiits:select name="state_Guj" mandatory="true" sort="false" captionid="MstScr.stateName" bundle="${mstScrLables}">
		<hdiits:option value="select"><fmt:message key="MstScr.selectGuj" bundle="${mstScrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	</tr>
	 
	<tr>
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
	<center><hdiits:button name="submitButton" type="button" captionid="MstScr.submit" bundle="${mstScrLables}" onclick="submitForm()"/>
	<hdiits:button name="updateButton" type="button" captionid="MstScr.update" bundle="${mstScrLables}" onclick="submitForm()" style="display:none"/>
	<hdiits:button name="resetButton" id="resetButton" type="button" captionid="MstScr.reset" bundle="${mstScrLables}" onclick="resetData()" />
	<hdiits:button name="closeButton" captionid="MstScr.close" type="button" bundle="${mstScrLables}"  onclick="closeForm('getCmnDistrictMstList')"/></center>
	<hdiits:hidden name="flag" id="flag" default="create"/>
	<hdiits:hidden name="districtId" id="districtId" default="0"/>
	<hdiits:hidden name="multiLang" id="multiLang" default="${MULTI_LANG}"/>
	<hdiits:hidden name="langId" id="langId" default="${langId}"/>
	<hdiits:hidden name="districtCode" id="districtCode" default="${districtCode}"/>
	</div>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript">
	initializetabcontent("maintab")	
</script>

<c:if test="${flag eq 'edit'}">
	
	<c:set var="districtNameEng" value="${resValue.districtNameEng}"></c:set>
	<c:if test="${MULTI_LANG eq 'Y'}">
	<c:set var="districtNameGuj" value="${resValue.districtNameGuj}"></c:set>
	</c:if>
	<c:set var="districtType" value="${resValue.districtType}"></c:set>
	<c:set var="countryCode" value="${resValue.countryCode}"></c:set>
	<c:set var="stateCode" value="${resValue.stateCode}"></c:set>
	<c:set var="activateFlag" value="${resValue.activateFlag}"></c:set>
	<c:set var="districtId" value="${resValue.districtId}"></c:set>

</c:if>

<script type="text/javascript">
function resetData(){

	document.getElementById('districtName_Eng').value = '';
	document.getElementById('country_Eng').value = '${defaultCountryCode}';
	document.createDistrict.rdoActiveFlagEng[0].checked = true;

	if('${MULTI_LANG}' == 'Y'){

		document.getElementById('districtName_Guj').value = '';
		document.getElementById('country_Guj').value = '${defaultCountryCode}';
		document.createDistrict.rdoActiveFlagGuj[0].checked = true;
	}

	
	if('${flag}' == 'edit'){
		globalFlagType = "E";
		document.getElementById('country_Eng').value = '${countryCode}';
		if('${MULTI_LANG}' == 'Y'){
			document.getElementById('country_Guj').value = '${countryCode}';
		}
	}
	fillState();
	if('${flag}' == 'edit'){

		document.getElementById('districtName_Eng').value= '${districtNameEng}';
		document.getElementById('districtType_Eng').value = '${districtType}';
		
		document.getElementById('state_Eng').value = '${stateCode}';
		
		
		if('${activateFlag}' == "ACTIVE STATUS") // active
			document.createDistrict.rdoActiveFlagEng[0].checked = true;
		if('${activateFlag}' == "DEACTIVE STATUS") // deactive
			document.createDistrict.rdoActiveFlagEng[1].checked = true;
		if('${activateFlag}' == "DELETE") // delete
			document.createDistrict.rdoActiveFlagEng[2].checked = true;
		
		
		if('${MULTI_LANG}' == 'Y'){
			
			if('${activateFlag}' == "ACTIVE STATUS"){ // active
				document.createDistrict.rdoActiveFlagGuj[0].checked = true;
			}
			if('${activateFlag}' == "DEACTIVE STATUS"){ // deactive
				document.createDistrict.rdoActiveFlagGuj[1].checked = true;
			}
			if('${activateFlag}' == "DELETE"){ // delete
				document.createDistrict.rdoActiveFlagGuj[2].checked = true;
			}
			document.getElementById('districtName_Guj').value= '${districtNameGuj}';
			document.getElementById('districtType_Guj').value = '${districtType}';
			document.getElementById('state_Guj').value = '${stateCode}';
			document.getElementById('country_Guj').value = '${countryCode}';
		}
		generateInitCapName('districtName_Eng');
		document.getElementById('updateButton').style.display = '';
		document.getElementById('submitButton').style.display = 'none';
		document.getElementById('districtId').value = '${districtId}';
		document.getElementById('flag').value = '${flag}';
		document.getElementById('multiLang').value = '${MULTI_LANG}';
		
	}
		
	
		
}

</script>
<script type="text/javascript">

document.createDistrict.rdoActiveFlagEng[0].checked= true;
if('${MULTI_LANG}' == 'Y'){
document.createDistrict.rdoActiveFlagGuj[0].checked = true;
}
resetData();
if('${langId}' == 1)
{
	document.getElementById('districtName_Eng').focus();
}
else if('${langId}' == 2)
{
	document.getElementById('districtName_Guj').focus();
}

</script>




</hdiits:form>
</body>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>		


<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.common.CmnLocationMst" var="cmnLocationMstLables" scope="request" />
<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />
<fmt:setBundle basename="resources.MstScrConstants" var="mstScrConstants" scope="request" />
<fmt:setBundle basename="resources.PrjSpc_MstScrLabels" var="PrjctSpecificLabels" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="bundleName" value="${cmnLocationMstLables}" ></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="activeFlag" value="${resValue.activeFlag}"></c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="department" value="${resValue.Department}"></c:set>
<c:set var="department_gu" value="${resValue.Department_gu}"></c:set>

<c:set var="orgDepartment" value="${resValue.orgDepartment}"></c:set>
<c:set var="orgDepartment_gu" value="${resValue.orgDepartment_gu}"></c:set>

<c:set var="parentcmnLocationMst" value="${resValue.parentcmnLocationMst}"></c:set>
<c:set var="parentcmnLocationMst_gu" value="${resValue.parentcmnLocationMst_gu}"></c:set>
<c:set var="cmnLocationMst" value="${resValue.cmnLocationMst}"></c:set>
<c:set var="cmnLocationMst_gu" value="${resValue.cmnLocationMst_gu}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="locType" value="${resValue.locType}"></c:set>
<c:set var="locType_gu" value="${resValue.locType_gu}"></c:set>

<c:set var="cmnStateMstList" value="${resValue.cmnStateMstList}"></c:set>
<c:set var="cmnStateMstList_gu" value="${resValue.cmnStateMstList_gu}"></c:set>

<c:set var="cmnStateMst" value="${resValue.cmnStateMst}"></c:set>
<c:set var="cmnDistrictMst" value="${resValue.cmnDistrictMst}"></c:set>
<c:set var="cmnTalukaMst" value="${resValue.cmnTalukaMst}"></c:set>
<c:set var="cmnCityMst" value="${resValue.cmnCityMst}"></c:set>
<c:set var="cmnContactMst" value="${resValue.cmnContactMst}"></c:set>

<fmt:formatDate value="${cmnLocationMst.startDate}" pattern="dd/MM/yyyy" var="startDate"/>
<fmt:formatDate value="${cmnLocationMst.endDate}" pattern="dd/MM/yyyy" var="endDate"/>
<fmt:formatDate value="${cmnLocationMst_gu.startDate}" pattern="dd/MM/yyyy" var="startDate_gu"/>
<fmt:formatDate value="${cmnLocationMst_gu.endDate}" pattern="dd/MM/yyyy" var="endDate_gu"/>	

<fmt:message key='SHOW_LABELS_FRM_PRJ_SPC_LBS' bundle='${mstScrConstants}' var="labelsFrmMstConstant"/>

<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/cmnLocationAdmin.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

<script type="text/javascript">
var gujaratStateCode='1';
var multiLang = '${multiLang}';

var locationArray = new Array();
var locationAlertArray = new Array();
locationAlertArray[0]='<fmt:message  bundle="${cmnLocationMstLables}" key="LOC.VALIDOFFICECODE"/>';
locationAlertArray[1]="<fmt:message bundle='${mstScrLables}' key='MstScr.inEng'/>";
locationAlertArray[2]="<fmt:message bundle='${mstScrLables}' key='MstScr.Eng'/>"
locationAlertArray[3]="<fmt:message bundle='${mstScrLables}' key='MstScr.inGuj'/>";
locationAlertArray[4]="<fmt:message bundle='${mstScrLables}' key='MstScr.Guj'/>";
locationAlertArray[5]="<fmt:message bundle='${mstScrLables}' key='MstScr.inEngGuj'/>";
locationAlertArray[6]="<fmt:message bundle='${mstScrLables}' key='MstScr.EngGuj'/>";
locationAlertArray[7]="<fmt:message bundle='${cmnLocationMstLables}' key='LOC.locationName'/>";
locationAlertArray[8]="<fmt:message bundle='${mstScrLables}' key='MstScr.AlreadyExist'/>";
locationAlertArray[9]="<fmt:message bundle='${mstScrLables}' key='MstScr.PinCodeAlert'/>";
locationAlertArray[10]="<fmt:message bundle='${cmnLocationMstLables}' key='LOC.locationEngGujNm'/>";
locationAlertArray[11]="<fmt:message key='SEARCH_MSG' bundle='${cmnLocationMstLables}'/>";
function resetAllData()
{
	if('${multiLang}'=='false' || '${multiLang}'==false)
	{	
		document.getElementById("guj").style.display='none';
		document.getElementById("eng").childNodes[0].childNodes[0].innerHTML = "Location Creation";
	}
	if('${flag}'=='edit')
	{
		globalFlagType = "E";
		if('${cmnLocationMst.locAddr1}'!=null && '${cmnLocationMst.locAddr1}' != "")
		{			
			document.getElementById('txtAddressOne').value = '${cmnLocationMst.locAddr1}';
			generateInitCapName('txtAddressOne');
		}
		if('${cmnLocationMst_gu.locAddr1}'!=null && '${cmnLocationMst_gu.locAddr1}' != "")
		{			
			document.getElementById('txtAddressOne_gu').value = '${cmnLocationMst_gu.locAddr1}';
		}
		
		if('${cmnLocationMst.locAddr2}'!=null && '${cmnLocationMst.locAddr2}' != "")
		{			
			document.getElementById('txtAddressTwo').value = '${cmnLocationMst.locAddr2}';
			generateInitCapName('txtAddressTwo');
		}
		if('${cmnLocationMst_gu.locAddr2}'!=null && '${cmnLocationMst_gu.locAddr2}' != "")
		{			
			document.getElementById('txtAddressTwo_gu').value = '${cmnLocationMst_gu.locAddr2}';
		}
		if('${cmnStateMst.stateCode}'!=null && '${cmnStateMst.stateCode}' != "")
		{		
			document.getElementById('cmbState').value = '${cmnStateMst.stateCode}';
		}
		if('${cmnStateMst.stateCode}'!=null && '${cmnStateMst.stateCode}' != "")
		{			
			document.getElementById('cmbState_gu').value = '${cmnStateMst.stateCode}';
		}
		
		
		//if('${cmnDistrictMst.districtCode}'!=null && '${cmnDistrictMst.districtCode}' != "")
		{	
			districtCombo='${cmnDistrictMst.districtCode}' ;
			addOrUpdateRecordforLocation('addDistrictCombo','getDistrictforLocationAdmin&districtCode='+districtCombo,new Array('cmbState'),1);
		}
		//if('${cmnDistrictMst.districtCode}'!=null && '${cmnDistrictMst.districtCode}' != "")
		{			
			districtCombo_gu='${cmnDistrictMst.districtCode}';
			addOrUpdateRecordforLocation('addDistrictCombo','getDistrictforLocationAdmin&districtCode='+districtCombo_gu,new Array('cmbState_gu'),2);
		}
		
		//if('${cmnTalukaMst.talukaCode}'!=null && '${cmnTalukaMst.talukaCode}' != "")
		{			
			talCombo='${cmnTalukaMst.talukaCode}';
			addOrUpdateRecordforLocation('addTalukaCombo','getTalukaforLocationAdmin&talukaCode='+talCombo,new Array('cmbDistrict'),1);
		}
		//if('${cmnTalukaMst.talukaCode}'!=null && '${cmnTalukaMst.talukaCode}' != "")
		{			
			talCombo_gu='${cmnTalukaMst.talukaCode}';
			addOrUpdateRecordforLocation('addTalukaCombo','getTalukaforLocationAdmin&talukaCode='+talCombo_gu,new Array('cmbDistrict_gu'),2);	
		}
		
		//if('${cmnCityMst.cityCode}'!=null && '${cmnCityMst.cityCode}' != "")
		{			
			cCombo='${cmnCityMst.cityCode}';
			addOrUpdateRecordforLocation('addCityCombo','getCityforLocationAdmin&cityCode='+cCombo,new Array('cmbDistrict'),1);
		}
		//if('${cmnCityMst.cityCode}'!=null && '${cmnCityMst.cityCode}' != "")
		{			
			cCombo_gu='${cmnCityMst.cityCode}';
			addOrUpdateRecordforLocation('addCityCombo','getCityforLocationAdmin&cityCode='+cCombo_gu,new Array('cmbDistrict_gu'),2);
		}
				
		document.getElementById('locationCode').value = '${cmnLocationMst.locationCode}';
				
		document.getElementById('newLocation').style.display = 'none';	
		document.getElementById('newLocation_gu').style.display = 'none';	
		document.getElementById('editLocation').style.display = '';	
		document.getElementById('editLocation_gu').style.display = '';	
			
		document.getElementById('txtlocName').value='${cmnLocationMst.locName}';
		generateInitCapName('txtlocName');
		document.getElementById('txtlocName_gu').value='${cmnLocationMst_gu.locName}';
		
		document.getElementById('txtLocShrtName').value='${cmnLocationMst.locShortName}';	
		generateInitCapName('txtLocShrtName');		
		document.getElementById('txtLocShrtName_gu').value='${cmnLocationMst_gu.locShortName}';	
		
		document.getElementById('cmbDepartment').value='${orgDepartment.depCode}';
		document.getElementById('cmbDepartment_gu').value='${orgDepartment_gu.depCode}';
		
		document.getElementById('locationCodeHdn').value = '${parentcmnLocationMst.locationCode}';
		document.getElementById('cmbParentLoc').value='${parentcmnLocationMst.locName}';
		document.getElementById('cmbParentLoc_gu').value='${parentcmnLocationMst_gu.locName}';
		
		document.getElementById('txtPincode').value='${cmnLocationMst.locPin}'; 
		document.getElementById('txtPincode_gu').value='${cmnLocationMst_gu.locPin}'; 
		
		document.getElementById('cmbLocationType').value='${cmnLocationMst.cmnLookupMst.lookupName}';
		document.getElementById('cmbLocationType_gu').value='${cmnLocationMst_gu.cmnLookupMst.lookupName}';

		setOtherLocTypeForUpdate(document.getElementById('cmbLocationType'));
		setOtherLocTypeForUpdate(document.getElementById('cmbLocationType_gu'));	

		document.getElementById('phoneNumber').value='${cmnContactMst.mobile}'; 
		document.getElementById('phoneNumber_gu').value='${cmnContactMst.mobile}';

		document.getElementById('faxNumber').value='${cmnContactMst.fax}'; 
		document.getElementById('faxNumber_gu').value='${cmnContactMst.fax}';

		document.getElementById('emailId').value='${cmnContactMst.email}'; 
		document.getElementById('emailId_gu').value='${cmnContactMst.email}';
		
		var stausFlagVal = '${cmnLocationMst.activateFlag}';
		if(stausFlagVal == 1) // active
		{
			document.frmAdminCrtLocation.rdoActiveFlag[0].checked = true;
			document.frmAdminCrtLocation.rdoActiveFlag_gu[0].checked = true;
		}
		if(stausFlagVal == 2) // deactive
		{
			document.frmAdminCrtLocation.rdoActiveFlag[1].checked = true;
			document.frmAdminCrtLocation.rdoActiveFlag_gu[1].checked = true;
		}
		if(stausFlagVal == 3) // delete
		{
			document.frmAdminCrtLocation.rdoActiveFlag[2].checked = true;
			document.frmAdminCrtLocation.rdoActiveFlag_gu[2].checked = true;
		}
		
		document.frmAdminCrtLocation.dtstartDate.value='${startDate}';
		document.frmAdminCrtLocation.dtendDate.value='${endDate}';
		
		document.frmAdminCrtLocation.dtstartDate_gu.value='${startDate_gu}';
		document.frmAdminCrtLocation.dtendDate_gu.value='${endDate_gu}';

		document.getElementById('officeCode').value='${cmnLocationMst.officeCode}';
		document.getElementById('officeCode_gu').value='${cmnLocationMst_gu.officeCode}';
		
	}
	else{
		resetData();
	}
}
function saveData()
{
	submit_frmAdminCrtLocation();
}
</script>
</head>

<body onkeypress="return checkSpecialCharacter(event);">
	<hdiits:form name="frmAdminCrtLocation" action="hrms.htm?actionFlag=SubmitAdminLocationMstData" method="post" validate="true" encType="multipart/form-data">
		<div id="tabmenu">
		<ul id="maintab" class="shadetabs" >
			<c:if test="${langId == 1}">
				<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
					<hdiits:caption captionid="LOC.Eng"  bundle="${cmnLocationMstLables}" captionLang="single"/>  
				</b></a></li>
				<li class="selected" id="guj" style="display:block"><a href="#" rel="tcontent2" onfocus="copyEngToGuj()"><b>
					<hdiits:caption captionid="LOC.Guj" bundle="${cmnLocationMstLables}" captionLang="single"/> 
				</b></a></li>
			</c:if>
			<c:if test="${langId == 2}">
				<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent1"><b>
					<hdiits:caption captionid="LOC.Guj" bundle="${cmnLocationMstLables}" captionLang="single"/> 
				</b></a></li>
				<li class="selected" id="eng"><a href="#" rel="tcontent2" onfocus="copyGujToEng()"><b>
					<hdiits:caption captionid="LOC.Eng" bundle="${cmnLocationMstLables}" captionLang="single"/> 
				</b></a></li>
			</c:if>
	</ul>
	</div>
	
	<div id="createLocation" name="createLocation" class="tabcontentstyle" >
		<c:if test="${langId == 1}">
			<div id="tcontent1" class="tabcontent" tabno="0">
		</c:if>
		<c:if test="${langId == 2}">
			<div id="tcontent2" class="tabcontent" tabno="1">
		</c:if>
		
		<table width="100%" id="newLocation_gu">
			<tr width="100%" align="center"><td><b><fmt:message key="LOC.NewLocation" bundle="${cmnLocationMstLables}" ></fmt:message></b></td></tr>
		</table>
		<table width="100%" id="editLocation_gu" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message key="LOC.EditLocation" bundle="${cmnLocationMstLables}"></fmt:message></b></td></tr>
		</table>
		
		<table class="tabtable">	
		<c:choose>
			<c:when test="${labelsFrmMstConstant == 'Y' or labelsFrmMstConstant == 'y'}">
				<c:set var="labelSrc" value="${PrjctSpecificLabels}"/>
			</c:when>
			<c:otherwise>	
				<c:set var="labelSrc" value="${cmnLocationMstLables}"/>
			</c:otherwise>
		</c:choose>	
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.locationName" bundle="${labelSrc}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtlocName" id="txtlocName" mandatory="true" captionid="LOC.locationName" bundle="${labelSrc}" validation="txt.isrequired" maxlength="95" onblur="generateShortName(this,'txtLocShrtName');generateInitCapName('txtlocName')"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.locationShortName" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="txtLocShrtName" id="txtLocShrtName" captionid="LOC.locationShortName" bundle="${cmnLocationMstLables}" mandatory="true" validation="txt.isrequired" maxlength="25" onblur="generateInitCapName('txtLocShrtName')"></hdiits:text></td>
			</tr>
			
			<tr>
				
			<td class="fieldLabel"><b><hdiits:caption captionid="LOC.Department" bundle="${labelSrc}"/></b></td>
			<td class="fieldLabel">
				<hdiits:select name="cmbDepartment" id="cmbDepartment" captionid="LOC.Department" bundle="${labelSrc}" mandatory="true" sort="false" validation="sel.isrequired">
					<hdiits:option value="0"><fmt:message key="LOC.SELECT" bundle="${cmnLocationMstLables}"></fmt:message></hdiits:option>
					<c:forEach items="${department}" var="department">
						<option value="<c:out value="${department.depCode}"/>"><c:out value="${department.depName}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.parentLocation" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:text id="cmbParentLoc" captionid="LOC.parentLocation" bundle="${cmnLocationMstLables}" name="cmbParentLoc" onblur="setBlankInLocationCode();displayText();setOriginalWindowName();" onfocus="removeText();setAjaxWindowName();" style="background-image: url('./images/search_autoCompelete.gif'); background-repeat: no-repeat; background-position: right"/>
					<span id="indicatorRegion" style="display:none;">
						<img src="./images/busy-indicator.gif"/>
					</span>
				</td>
			</tr>
			
			<tr>			
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.AddressOne" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:textarea name="txtAddressOne" id="txtAddressOne" captionid="LOC.AddressOne" bundle="${cmnLocationMstLables}" maxlength="45" onblur="generateInitCapName('txtAddressOne')" cols="28"></hdiits:textarea></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.AddressTwo" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:textarea name="txtAddressTwo" id="txtAddressTwo" captionid="LOC.AddressTwo" bundle="${cmnLocationMstLables}" maxlength="45" onblur="generateInitCapName('txtAddressTwo')" cols="28"></hdiits:textarea></td>
			</tr>
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.State" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="cmbState" id="cmbState" captionid="LOC.State" bundle="${cmnLocationMstLables}" onchange="getDistrictCombo()" sort="false" mandatory="true" validation="sel.isrequired">
						<hdiits:option value=""><fmt:message key="LOC.SELECT" bundle="${cmnLocationMstLables}"></fmt:message></hdiits:option>
						<c:forEach items="${cmnStateMstList}" var="cmnStateMstList">
							<option value="<c:out value="${cmnStateMstList.stateCode}"/>"><c:out value="${cmnStateMstList.stateName}" /></option>
						</c:forEach>
					</hdiits:select>
				</td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.District" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="cmbDistrict" id="cmbDistrict" captionid="LOC.District" bundle="${cmnLocationMstLables}" onchange="getTalukaCityCombo()" sort="false" mandatory="true" validation="sel.isrequired">
						<hdiits:option value=""><fmt:message key="LOC.SELECT" bundle="${cmnLocationMstLables}"></fmt:message></hdiits:option>
					</hdiits:select>
				</td>
			</tr>
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.Taluka" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="cmbTaluka" id="cmbTaluka" captionid="LOC.Taluka" bundle="${cmnLocationMstLables}" sort="false">
						<hdiits:option value=""><fmt:message key="LOC.SELECT" bundle="${cmnLocationMstLables}"></fmt:message></hdiits:option>
					</hdiits:select>
				</td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.City" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="cmbCity" id="cmbCity" captionid="LOC.City" bundle="${cmnLocationMstLables}" sort="false">
						<hdiits:option value=""><fmt:message key="LOC.SELECT" bundle="${cmnLocationMstLables}"></fmt:message></hdiits:option>
					</hdiits:select>
				</td>
			</tr>
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.Pincode" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:number maxlength="6" name="txtPincode" captionid="LOC.Pincode" bundle="${cmnLocationMstLables}" validation="txt.isrequired,txt.isnumber" style="text-align: right"  mandatory="true" floatAllowed="false"/> </td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.offCode" bundle="${cmnLocationMstLables}"/></b></td>				
				<td class="fieldLabel"><hdiits:number name="officeCode" id="officeCode"  captionid="LOC.offCode" bundle="${cmnLocationMstLables}" maxlength="3"  validation="txt.isnumber"  style="text-align: right"   ></hdiits:number></td>
			</tr>
					
			<tr style="display:none">
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.StartDate" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dtstartDate" captionid="LOC.StartDate" bundle="${cmnLocationMstLables}" mandatory="true" maxvalue="31/12/2099" afterDateSelect="compareStartDateToEndDate()"  validation="txt.isdt,txt.isrequired"></hdiits:dateTime></td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.EndDate" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dtendDate" captionid="LOC.EndDate" bundle="${cmnLocationMstLables}" maxvalue="31/12/2099" afterDateSelect="compareStartDateToEndDate()"></hdiits:dateTime></td>
			</tr>
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="MstScr.PhoneNo" bundle="${mstScrLables}"/></b></td>
				<td class="fieldLabel"><hdiits:number name="phoneNumber" id="phoneNumber" captionid="MstScr.PhoneNo" bundle="${mstScrLables}" maxlength="11" validation="txt.isnumber" style="text-align: right"></hdiits:number></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="MstScr.FaxNo" bundle="${mstScrLables}"/></b></td>
				<td class="fieldLabel"><hdiits:number name="faxNumber" id="faxNumber" captionid="MstScr.FaxNo" bundle="${mstScrLables}" maxlength="11" validation="txt.isnumber" style="text-align: right"></hdiits:number></td>
			</tr>
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="MstScr.EmailId" bundle="${mstScrLables}"/></b></td>
				<td><hdiits:text name="emailId" id="emailId" captionid="MstScr.EmailId" bundle="${mstScrLables}" validation="txt.email" maxlength="145"></hdiits:text></td>
				<td class="fieldLabel" id="locTypeCaptionTdId" style="display: none;"><b><hdiits:caption captionid="LOC.LocationType" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel" id="locTypeComboTdId" style="display: none;">
					<hdiits:select name="cmbLocationType" id="cmbLocationType" captionid="LOC.LocationType" bundle="${cmnLocationMstLables}" validation="sel.isrequired" sort="false"  mandatory="true">
						<hdiits:option value="0"><fmt:message key="LOC.SELECT" bundle="${cmnLocationMstLables}"></fmt:message></hdiits:option>
						<c:forEach items="${locType}" var="locType">
							<option value="<c:out value="${locType.lookupName}"/>"><c:out value="${locType.lookupDesc}" /></option>
						</c:forEach>	
					</hdiits:select>
				</td>
			</tr>		
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.activeFlag" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel" >
					<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
						<hdiits:radio name="rdoActiveFlag" value="${StatusFlagValue.lookupId}" mandatory="true" />${StatusFlagValue.lookupName}<br>										
					</c:forEach>
				</td>
			</tr>	
		</table>
		
		<c:if test="${langId == 1}">
			<%@ include file="AdminLocBranchMapping.jsp"%>
		</c:if>	
	</div>
		
	<c:if test="${langId == 1}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
		
			<table width="100%" id="newLocation">
				<tr width="100%" align="center"><td><b><fmt:message key="LOC.NewLocation" bundle="${cmnLocationMstLables}"></fmt:message></b></td></tr>
			</table>
			
			<table width="100%" id="editLocation" style="display:none">
				<tr width="100%" align="center"><td><b><fmt:message key="LOC.EditLocation" bundle="${cmnLocationMstLables}"></fmt:message></b></td></tr>
			</table>
			
			<table class="tabtable">
				<tr>
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.locationName" bundle="${labelSrc}"/></b></td>
					<td class="fieldLabel"><hdiits:text name="txtlocName_gu" id="txtlocName_gu" mandatory="true" captionid="LOC.locationName" bundle="${labelSrc}" validation="txt.isrequired" maxlength="95"></hdiits:text></td>
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.locationShortName" bundle="${cmnLocationMstLables}"/></b></td>
					<td class="fieldLabel"><hdiits:text name="txtLocShrtName_gu" id="txtLocShrtName_gu" captionid="LOC.locationShortName" bundle="${cmnLocationMstLables}" mandatory="true" validation="txt.isrequired" maxlength="25"></hdiits:text></td>
				</tr>
				
				<tr>
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.Department" bundle="${labelSrc}"/></b></td>
					<td class="fieldLabel">
						<hdiits:select name="cmbDepartment_gu" id="cmbDepartment_gu" captionid="LOC.Department" bundle="${labelSrc}" mandatory="true" sort="false" validation="sel.isrequired">
							<hdiits:option value="0"><fmt:message key="LOC.SELECT_GU" bundle="${cmnLocationMstLables}"></fmt:message></hdiits:option>
						<c:forEach items="${department_gu}" var="department_gu">
								<option value="<c:out value="${department_gu.depCode}"/>"><c:out value="${department_gu.depName}" /></option>
							</c:forEach>			
						</hdiits:select>
					</td>
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.parentLocation" bundle="${cmnLocationMstLables}"/></b></td>
					<td class="fieldLabel"><hdiits:text id="cmbParentLoc_gu" name="cmbParentLoc_gu"  captionid="LOC.parentLocation" bundle="${cmnLocationMstLables}" onblur="setBlankInLocationCode();displayText();setOriginalWindowName();" onfocus="removeText();setAjaxWindowName();" style="background-image: url('./images/search_autoCompelete.gif'); background-repeat: no-repeat; background-position: right"/>
						<span id="indicatorRegion" style="display:none;">
							<img src="./images/busy-indicator.gif"/>
						</span>
					</td>
				</tr>
				
				<tr>			
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.AddressOne" bundle="${cmnLocationMstLables}"/></b></td>
					<td class="fieldLabel"><hdiits:textarea name="txtAddressOne_gu" id="txtAddressOne_gu" captionid="LOC.AddressOne" bundle="${cmnLocationMstLables}" maxlength="45" cols="28"></hdiits:textarea></td>
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.AddressTwo" bundle="${cmnLocationMstLables}"/></b></td>
					<td class="fieldLabel"><hdiits:textarea name="txtAddressTwo_gu" id="txtAddressTwo_gu" captionid="LOC.AddressTwo" bundle="${cmnLocationMstLables}" maxlength="45" cols="28"></hdiits:textarea></td>
				</tr>
				
				<tr>
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.State" bundle="${cmnLocationMstLables}"/></b></td>
					<td class="fieldLabel">
						<hdiits:select name="cmbState_gu" id="cmbState_gu" captionid="LOC.State" bundle="${cmnLocationMstLables}" onchange="getDistrictCombo()" sort="false" mandatory="true" validation="sel.isrequired">
							<hdiits:option value=""><fmt:message key="LOC.SELECT_GU" bundle="${cmnLocationMstLables}"/></hdiits:option>
							<c:forEach items="${cmnStateMstList_gu}" var="cmnStateMstList_gu">
								<option value="<c:out value="${cmnStateMstList_gu.stateCode}"/>"><c:out value="${cmnStateMstList_gu.stateName}" /></option>
							</c:forEach>
						</hdiits:select>
					</td>	
					<td class="fieldLabel"><b><hdiits:caption captionid="LOC.District" bundle="${cmnLocationMstLables}"/></b></td>
					<td class="fieldLabel">
						<hdiits:select name="cmbDistrict_gu" id="cmbDistrict_gu" captionid="LOC.District" bundle="${cmnLocationMstLables}" onchange="getTalukaCityCombo()" sort="false" mandatory="true" validation="sel.isrequired">
							<hdiits:option value=""><fmt:message key="LOC.SELECT_GU" bundle="${cmnLocationMstLables}"/></hdiits:option>
						</hdiits:select>
					</td>
				</tr>
				
				<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.Taluka" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="cmbTaluka_gu" id="cmbTaluka_gu" captionid="LOC.Taluka" bundle="${cmnLocationMstLables}" sort="true">
						<hdiits:option value=""><fmt:message key="LOC.SELECT_GU" bundle="${cmnLocationMstLables}"/></hdiits:option>
					</hdiits:select>
				</td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.City" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="cmbCity_gu" id="cmbCity_gu" captionid="LOC.City" bundle="${cmnLocationMstLables}" sort="true">
						<hdiits:option value=""><fmt:message key="LOC.SELECT_GU" bundle="${cmnLocationMstLables}"/></hdiits:option>
					</hdiits:select>
				</td>
			</tr>
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.Pincode" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:number maxlength="6" name="txtPincode_gu" captionid="LOC.Pincode" bundle="${cmnLocationMstLables}" id="txtPincode_gu" mandatory="true" validation="txt.isrequired,txt.isnumber" style="text-align: right" floatAllowed="false"/> </td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.offCode" bundle="${cmnLocationMstLables}"/></b></td>				
				<td class="fieldLabel"><hdiits:number name="officeCode_gu" id="officeCode_gu" captionid="LOC.offCode" bundle="${cmnLocationMstLables}" maxlength="3" validation="txt.isnumber" style="text-align: right"></hdiits:number></td>
			</tr>
					
			<tr style="display:none">
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.StartDate" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dtstartDate_gu" captionid="LOC.StartDate" bundle="${cmnLocationMstLables}" afterDateSelect="compareStartDateToEndDateGu()" maxvalue="31/12/2099" validation="txt.isdt,txt.isrequired" mandatory="true"></hdiits:dateTime></td>	
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.EndDate" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="dtendDate_gu" captionid="LOC.EndDate" bundle="${cmnLocationMstLables}" afterDateSelect="compareStartDateToEndDateGu()" maxvalue="31/12/2099"></hdiits:dateTime></td>
			</tr>	
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="MstScr.PhoneNo" bundle="${mstScrLables}"/></b></td>
				<td class="fieldLabel"><hdiits:number name="phoneNumber_gu" id="phoneNumber_gu" captionid="MstScr.PhoneNo" bundle="${mstScrLables}" maxlength="11" validation="txt.isnumber" style="text-align: right"></hdiits:number></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="MstScr.FaxNo" bundle="${mstScrLables}"/></b></td>
				<td class="fieldLabel"><hdiits:number name="faxNumber_gu" id="faxNumber_gu" captionid="MstScr.FaxNo" bundle="${mstScrLables}" maxlength="11" validation="txt.isnumber" style="text-align: right"></hdiits:number></td>
			</tr>
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="MstScr.EmailId" bundle="${mstScrLables}" /></b></td>
				<td><hdiits:text name="emailId_gu" id="emailId_gu" captionid="MstScr.EmailId" bundle="${mstScrLables}" validation="txt.email" maxlength="145"></hdiits:text></td>
				<td class="fieldLabel" id="locTypeCaptionTdId_gu" style="display: none;"><b><hdiits:caption captionid="LOC.LocationType" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel" id="locTypeComboTdId_gu" style="display: none;">
					<hdiits:select name="cmbLocationType_gu" id="cmbLocationType_gu" captionid="LOC.LocationType" bundle="${cmnLocationMstLables}" sort="false" validation="sel.isrequired" mandatory="true">
						<hdiits:option value="0"><fmt:message key="LOC.SELECT_GU" bundle="${cmnLocationMstLables}"/></hdiits:option>
						<c:forEach items="${locType_gu}" var="locType_gu">
							<option value="<c:out value="${locType_gu.lookupName}"/>"><c:out value="${locType_gu.lookupDesc}" /></option>
						</c:forEach>
					</hdiits:select>
				</td>
			</tr>
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="LOC.activeFlag" bundle="${cmnLocationMstLables}"/></b></td>
				<td class="fieldLabel" >
					<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
						<hdiits:radio name="rdoActiveFlag_gu" value="${StatusFlagValue.lookupId}" mandatory="true" />${StatusFlagValue.lookupName}<br>										
					</c:forEach>
				</td>
			</tr>
			
			</table>
		
			<c:if test="${langId == 2}">
				<%@ include file="AdminLocBranchMapping.jsp"%>
			</c:if>
		</div>
		
		<hdiits:hidden name="flag" id="flag" default="${flag}"/>
		<hdiits:hidden name="locationCode" id="locationCode" default=""/>	
		</div>
		
		<table align="center">	
			<tr>
				<td align="center">
					<c:if test="${flag ne'edit'}">
						<br><hdiits:button name="Submit" type="button" captionid="LOC.SUBMIT" bundle="${cmnLocationMstLables}" onclick="submit_frmAdminCrtLocation()"></hdiits:button>
					</c:if>
					<c:if test="${flag eq 'edit'}">
						<br><hdiits:button name="Update" type="button" captionid="LOC.UPDATE" bundle="${cmnLocationMstLables}" onclick="submit_frmAdminCrtLocation()"></hdiits:button>
					</c:if>
				</td>
				<td align="center">
					<br><hdiits:button name="reset" type="button" captionid="LOC.RESET" bundle="${cmnLocationMstLables}" onclick="resetAllData();resetbrnchLocMapping()"></hdiits:button>
				</td>
				<td align="center">
					<br><hdiits:button captionid="MstScr.close" bundle="${mstScrLables}" onclick="closeCurrWindow()" name="btnClose" type="button"/>
				</td>
			</tr>
		</table>
	
	<hdiits:hidden name="langId" id="langId" default="${langId}"></hdiits:hidden>
	<hdiits:hidden name="multiLangHdn" id="multiLangHdn" default="${multiLang}"></hdiits:hidden>	
	<hdiits:hidden name="locationCodeHdn"/>	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	
	<script type="text/javascript">
		initializetabcontent("maintab")	
	</script>
	</hdiits:form>
</body>
<script type="text/javascript">
	var validateDatealert = "<fmt:message bundle='${cmnLocationMstLables}' key='LOC.VALIDDATE'/>";
	var selectRadioalert = "<fmt:message bundle='${cmnLocationMstLables}' key='LOC.SELECTRADIO'/>";
	var selectDeptalert = "<fmt:message bundle='${cmnLocationMstLables}' key='LOC.SELECTDEPT'/>";
	var selectParentLocalert = "<fmt:message bundle='${cmnLocationMstLables}' key='LOC.SELECTPARENTLOC'/>";
	var selLocTypealert = "<fmt:message bundle='${cmnLocationMstLables}' key='LOC.SELECTLOCTYPE'/>";
	var invalidparentloc="<fmt:message key="LOC.INVALIDPARENT" bundle='${cmnLocationMstLables}' />";
	var showLocType = "<fmt:message key='IS_SHOW_LOC_TYPE_COMBO' bundle='${mstScrConstants}' />";
	var langId='${langId}';
	
	if(showLocType=='Y' || showLocType=='y')
	{
		document.getElementById("locTypeCaptionTdId").style.display='';
		document.getElementById("locTypeComboTdId").style.display='';
		document.getElementById("locTypeCaptionTdId_gu").style.display='';
		document.getElementById("locTypeComboTdId_gu").style.display='';
	}

	resetAllData();
	addOrUpdateRecord('getAsgnAndUnasgnBrnch','getAsgnAndUnasgnBrnchAction',new Array('locationCode'));
	//added by uzma
	displayText();
	if(langId=='1')
	{
		document.getElementById("txtlocName").focus();
	}
	else if(langId=='2')
	{
		document.getElementById("txtlocName_gu").focus();	
	}
</script>
</html>
<ajax:autocomplete
    source="cmbParentLoc"
    target="locationCodeHdn"
    baseUrl="hrms.htm?actionFlag=getPostLocationList"
    parameters="locationName={cmbParentLoc},currentLocationCode={locationCode}"
    className="autocomplete"
    minimumCharacters="3"
	appendSeparator="false"
    preFunction="setBlankValue"
    indicator="indicatorRegion"
    />
    
    <ajax:autocomplete
    source="cmbParentLoc_gu"
    target="locationCodeHdn"
    baseUrl="hrms.htm?actionFlag=getPostLocationList"
    parameters="locationName={cmbParentLoc_gu},currentLocationCode={locationCode}"
    className="autocomplete"
    minimumCharacters="3"
    appendSeparator="false"
    preFunction="setBlankValue"
    indicator="indicatorRegion"
    />
<%
}catch(Exception ex){ex.printStackTrace();}
%>
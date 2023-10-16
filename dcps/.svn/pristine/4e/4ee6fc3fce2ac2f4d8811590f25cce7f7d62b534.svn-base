<%
 try {
 %>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/gnl/CourtCases/courtCase.js"/>"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/validation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseId" value="${resValue.caseId}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="caseSubTypeLst" value="${resValue.caseSubTypeLst}"></c:set>
<c:set var="caseTypeLst" value="${resValue.caseTypeLst}"></c:set>
<c:set var="subMatterLst" value="${resValue.subMatterLst}"></c:set>
<c:set var="stageLst" value="${resValue.stageLst}"></c:set>
<c:set var="caseResultLst" value="${resValue.caseResultLst}"></c:set>
<c:set var="pttnLst" value="${resValue.pttnLst}"></c:set>
<c:set var="courtLst" value="${resValue.courtLst}"></c:set>
<c:set var="districtLst" value="${resValue.districtLst}"></c:set>
<c:set var="typeLst" value="${resValue.typeList}"></c:set>
<c:set var="caseDtlsList" value="${resValue.caseDtlsList}"></c:set>
<c:set var="displayFlag" value="${resValue.displayFlag}"></c:set>

<fmt:setBundle basename="resources.gnl.CourtCases.CCLables" var="ccLables" scope="request" />
<fmt:setBundle basename="resources.gnl.CourtCases.CCAlertLables" var="ccAlertLables" scope="request" />

<script type="text/javascript">
var addressAlertMsg=new Array();
addressAlertMsg[0]='<fmt:message bundle="${ccAlertLables}" key="HRMS.DistrictAlert"/>';
addressAlertMsg[1]='<fmt:message bundle="${ccAlertLables}" key="HRMS.TalukaAlert"/>';
addressAlertMsg[2]='<fmt:message bundle="${ccAlertLables}" key="HRMS.VillageAlert"/>';
addressAlertMsg[3]='<fmt:message bundle="${ccAlertLables}" key="HRMS.AreaAlert"/>';
addressAlertMsg[4]='<fmt:message bundle="${ccAlertLables}" key="HRMS.CityAlert"/>';
addressAlertMsg[5]='<fmt:message bundle="${ccAlertLables}" key="HRMS.OtherAreaAlert"/>';
addressAlertMsg[6]='<fmt:message bundle="${ccAlertLables}" key="HRMS.OtherCityAlert"/>';
addressAlertMsg[7]='<fmt:message bundle="${ccAlertLables}" key="HRMS.OtherVilaageAlert"/>';
addressAlertMsg[8]='<fmt:message bundle="${ccAlertLables}" key="HRMS.AddressAlert"/>';
addressAlertMsg[10]='<fmt:message bundle="${ccAlertLables}" key="HRMS.CountryAlert"/>';
addressAlertMsg[11]='<fmt:message bundle="${ccAlertLables}" key="HRMS.StateAlert"/>';

var ccAlertMsg=new Array();
ccAlertMsg[0]='<fmt:message bundle="${ccAlertLables}" key="HRMS.DOH"/>';
ccAlertMsg[1]='<fmt:message bundle="${ccAlertLables}" key="HRMS.attndName"/>';
ccAlertMsg[2]='<fmt:message bundle="${ccAlertLables}" key="HRMS.hrngDtls"/>';
ccAlertMsg[3]='<fmt:message bundle="${ccAlertLables}" key="HRMS.rmrks"/>';
ccAlertMsg[4]='<fmt:message bundle="${ccAlertLables}" key="HRMS.apprvlAppl"/>';
ccAlertMsg[5]='<fmt:message bundle="${ccAlertLables}" key="HRMS.PttnrName"/>';
ccAlertMsg[6]='<fmt:message bundle="${ccAlertLables}" key="HRMS.AgeLimit"/>';
ccAlertMsg[7]='<fmt:message bundle="${ccAlertLables}" key="HRMS.eMailFormat"/>';
ccAlertMsg[8]='<fmt:message bundle="${ccAlertLables}" key="HRMS.LwyrName"/>';
ccAlertMsg[9]='<fmt:message bundle="${ccLables}" key="HRMS.Yes"/>';
ccAlertMsg[10]='<fmt:message bundle="${ccLables}" key="HRMS.No"/>'
ccAlertMsg[11]='<fmt:message bundle="${ccLables}" key="HRMS.CRT.party"/>';
ccAlertMsg[12]='<fmt:message bundle="${ccLables}" key="HRMS.CRT.govt"/>';
ccAlertMsg[13]='<fmt:message bundle="${ccAlertLables}" key="HRMS.caseNo"/>';
ccAlertMsg[14]='<fmt:message bundle="${ccAlertLables}" key="HRMS.breafFactsCase"/>';
ccAlertMsg[15]='<fmt:message bundle="${ccAlertLables}" key="HRMS.provisionLaw"/>';
ccAlertMsg[16]='<fmt:message bundle="${ccAlertLables}" key="HRMS.remandedText"/>';
ccAlertMsg[17]='<fmt:message bundle="${ccAlertLables}" key="HRMS.dateOfFllng"/>';
ccAlertMsg[18]='<fmt:message bundle="${ccAlertLables}" key="HRMS.court"/>';
ccAlertMsg[19]='<fmt:message bundle="${ccAlertLables}" key="HRMS.caseType"/>';
ccAlertMsg[20]='<fmt:message bundle="${ccAlertLables}" key="HRMS.caseSubType"/>';
ccAlertMsg[21]='<fmt:message bundle="${ccAlertLables}" key="HRMS.caseResult"/>';
ccAlertMsg[22]='<fmt:message bundle="${ccAlertLables}" key="HRMS.stage"/>';
ccAlertMsg[23]='<fmt:message bundle="${ccAlertLables}" key="HRMS.subMatter"/>';
ccAlertMsg[24]='<fmt:message bundle="${ccAlertLables}" key="HRMS.RspndntName"/>';

var count=0;

function selectJsp(jspName,submitFlag)
{
   	if(submitFlag == '0')
   	{
   		deleteExistingTable(jspName);
	   	if(jspName == 'pttnrDtls' )
	   	{
	   	
	   		document.cmnCourt.prsnTypePttnr[0].click();
			document.getElementById('displayFlag').value=2;
	   	}
	   	if(jspName == 'lwyrDtls' )
	   	{
			document.getElementById('displayFlag').value=1;
	   	}
	   	if(jspName == 'rspndntDtls' )
	   	{
	   		document.cmnCourt.prsnTypeRspndnt[0].click();
			document.getElementById('displayFlag').value=3;   		
	   	}
	   	if(jspName=='hrngDtls')
	   	{
			document.getElementById('displayFlag').value=5;
	   	}
	   	if(jspName=='applDtls')
	   	{
			document.getElementById('displayFlag').value=4;
	   	}
		if(jspName=='caseDtls')
		{
			document.getElementById('displayFlag').value=6;
		}
	   	if(count!=0)
		{
			var saveDtls=confirm('<fmt:message bundle="${ccAlertLables}" key="HRMS.SaveChanges"/>');
			if(saveDtls)
			{
				submitForm(${fileId},${caseId});
			}
			else
			{
				if(jspName == 'pttnrDtls' )
			   	{
					document.getElementById('petitionerLink').click();
			   	}
			   	if(jspName == 'lwyrDtls' )
			   	{
					document.getElementById('lawyerLink').click();
					setFocusSelection('prsnNameLwyr');
					
			   	}
			   	if(jspName == 'rspndntDtls' )
			   	{
					document.getElementById('respondentLink').click();
			   	}
			   	if(jspName=='hrngDtls')
			   	{
					document.getElementById('hearingLink').click();
					setFocusSelection('DOH');
			   	}
			   	if(jspName=='applDtls')
			   	{
					document.getElementById('appealLink').click();
					document.getElementById('applFieldBy').focus();
			   	}
				if(jspName=='caseDtls')
				{
					document.getElementById('caseLink').click();
					document.getElementById('initBy').focus()
					if(document.getElementById('CaseID').value!="0")
					{
						var caseNo1='${caseDtlsList[0].caseNo}';
						var district1='${caseDtlsList[0].district}';
						var briefFactsCase1='${caseDtlsList[0].briefFactsCase}';
				   		var isStayGenerated1='${caseDtlsList[0].isStayGenerated}';
				   		var isExhibit1='${caseDtlsList[0].isExhibit}';
				   		var isInstitutionSuit1='${caseDtlsList[0].isInstitutionSuit}';
				   		var remandedText1='${caseDtlsList[0].remandedText}';
				   		var dateOfFiling1='${caseDtlsList[0].dateOfFiling}';
					   	var reasonsActionsTaken1='${caseDtlsList[0].reasonsActionsTaken}';
				   		var receiveDate1='${caseDtlsList[0].receiveDate}';
				   		var provisionLaw1='${caseDtlsList[0].provisionLaw}';
						
						var cmnCourtMst='${caseDtlsList[0].cmnCourtMst.courtId}';
						var cmnLookupMstByPetitionType1='${caseDtlsList[0].cmnLookupMstByPetitionType.lookupId}';
						var	cmnLookupMstByCaseType1='${caseDtlsList[0].cmnLookupMstByCaseType.lookupId}';
						var cmnLookupMstByCaseSubType1='${caseDtlsList[0].cmnLookupMstByCaseSubType.lookupId}';
						
						var cmnLookupMstByInitiatedBy1='${caseDtlsList[0].cmnLookupMstByInitiatedBy.lookupId}';
						var cmnLookupMstByCaseResult1='${caseDtlsList[0].cmnLookupMstByCaseResult.lookupId}';			
						var cmnLookupMstByStage1='${caseDtlsList[0].cmnLookupMstByStage.lookupId}';
						var cmnLookupMstBySubjectMatter1='${caseDtlsList[0].cmnLookupMstBySubjectMatter.lookupId}';
						
			   			var dateOfFilingArr;
							
		  				if(dateOfFiling1 != null)
		  				{
							dateOfFilingArr = getDateAndTimeFromDateObj(dateOfFiling1);
				  		}
			   			var receiveDateArr;
							
		  				if(receiveDate1 != null)
		  				{
							receiveDateArr = getDateAndTimeFromDateObj(receiveDate1);
				  		}
				   		document.getElementById('initBy').value=cmnLookupMstByInitiatedBy1;
					    document.getElementById('caseNo').value=caseNo1;
					    document.getElementById('court').value=cmnCourtMst;
					    document.getElementById('district').value=district1;
					    document.getElementById('caseType').value=cmnLookupMstByCaseType1;
					    document.getElementById('caseSubType').value=cmnLookupMstByCaseSubType1;
				//	    document.getElementById('govtPleader').value=;
				//	    document.getElementById('nameAct').value=;
					    document.getElementById('breafFactsCase').value=briefFactsCase1;
					    document.getElementById('stayGranted').value=isStayGenerated1;
					    document.getElementById('provisionLaw').value=provisionLaw1;
					    document.getElementById('exhibitGranted').value=isExhibit1;
					    document.getElementById('pttnType').value=cmnLookupMstByPetitionType1;
					    document.getElementById('subMatter').value=cmnLookupMstBySubjectMatter1;
					    document.getElementById('caseResult').value=cmnLookupMstByCaseResult1;		    
					    document.getElementById('instituteSuit').value=isInstitutionSuit1;
					    document.getElementById('receiveDate').value=receiveDateArr[0];
					    document.getElementById('remandedText').value=remandedText1;
					    document.getElementById('stage').value=cmnLookupMstByStage1;
					    document.getElementById('dateOfFllng').value=dateOfFilingArr[0];
					    document.getElementById('resonsActionTaken').value=reasonsActionsTaken1;
					}
				}
				closeAddress('AddressLwyr');
				closeAddress('AddressPttnr');
				closeAddress('AddressRspndnt');
			   	document.getElementById('actionflag').value=jspName;
		   		getPageDetails(${caseId});
			}
		}
		else
		{
			if(jspName == 'pttnrDtls' )
		   	{
				document.getElementById('petitionerLink').click();
				setFocusSelection('prsnNamePttnr');
		   	}
		   	if(jspName == 'lwyrDtls' )
		   	{
				document.getElementById('lawyerLink').click();
				setFocusSelection('prsnNameLwyr');
		   	}
		   	if(jspName == 'rspndntDtls' )
		   	{
				document.getElementById('respondentLink').click();   		
				setFocusSelection('prsnNameRspndnt');
		   	}
		   	if(jspName=='hrngDtls')
		   	{
				document.getElementById('hearingLink').click();
				setFocusSelection('DOH');
		   	}
		   	if(jspName=='applDtls')
		   	{
				document.getElementById('appealLink').click();
				document.getElementById('applFieldBy').focus();				
//				setFocusSelection('applFieldBy');
		   	}
			if(jspName=='caseDtls')
			{
		
				document.getElementById('caseLink').click();
				document.getElementById('initBy').focus();
//				setFocusSelection('initBy');
				if(document.getElementById('CaseID').value!="0")
				{
					var caseNo1='${caseDtlsList[0].caseNo}';
					var district1='${caseDtlsList[0].district}';
					var briefFactsCase1='${caseDtlsList[0].briefFactsCase}';
			   		var isStayGenerated1='${caseDtlsList[0].isStayGenerated}';
			   		var isExhibit1='${caseDtlsList[0].isExhibit}';
			   		var isInstitutionSuit1='${caseDtlsList[0].isInstitutionSuit}';
			   		var remandedText1='${caseDtlsList[0].remandedText}';
			   		var dateOfFiling1='${caseDtlsList[0].dateOfFiling}';
				   	var reasonsActionsTaken1='${caseDtlsList[0].reasonsActionsTaken}';
			   		var receiveDate1='${caseDtlsList[0].receiveDate}';
			   		var provisionLaw1='${caseDtlsList[0].provisionLaw}';
					
					var cmnCourtMst='${caseDtlsList[0].cmnCourtMst.courtId}';
					var cmnLookupMstByPetitionType1='${caseDtlsList[0].cmnLookupMstByPetitionType.lookupId}';
					var	cmnLookupMstByCaseType1='${caseDtlsList[0].cmnLookupMstByCaseType.lookupId}';
					var cmnLookupMstByCaseSubType1='${caseDtlsList[0].cmnLookupMstByCaseSubType.lookupId}';
					
					var cmnLookupMstByInitiatedBy1='${caseDtlsList[0].cmnLookupMstByInitiatedBy.lookupId}';
					var cmnLookupMstByCaseResult1='${caseDtlsList[0].cmnLookupMstByCaseResult.lookupId}';			
					var cmnLookupMstByStage1='${caseDtlsList[0].cmnLookupMstByStage.lookupId}';
					var cmnLookupMstBySubjectMatter1='${caseDtlsList[0].cmnLookupMstBySubjectMatter.lookupId}';
					
		   			var dateOfFilingArr;
						
		  				if(dateOfFiling1 != null)
		  				{
							dateOfFilingArr = getDateAndTimeFromDateObj(dateOfFiling1);
				  		}
		   			var receiveDateArr;
						
		  				if(receiveDate1 != null)
		  				{
							receiveDateArr = getDateAndTimeFromDateObj(receiveDate1);
				  		}
			   		document.getElementById('initBy').value=cmnLookupMstByInitiatedBy1;
				    document.getElementById('caseNo').value=caseNo1;
				    document.getElementById('court').value=cmnCourtMst;
				    document.getElementById('district').value=district1;
				    document.getElementById('caseType').value=cmnLookupMstByCaseType1;
				    document.getElementById('caseSubType').value=cmnLookupMstByCaseSubType1;
			//	    document.getElementById('govtPleader').value=;
			//	    document.getElementById('nameAct').value=;
				    document.getElementById('breafFactsCase').value=briefFactsCase1;
				    document.getElementById('stayGranted').value=isStayGenerated1;
				    document.getElementById('provisionLaw').value=provisionLaw1;
				    document.getElementById('exhibitGranted').value=isExhibit1;
				    document.getElementById('pttnType').value=cmnLookupMstByPetitionType1;
				    document.getElementById('subMatter').value=cmnLookupMstBySubjectMatter1;
				    document.getElementById('caseResult').value=cmnLookupMstByCaseResult1;		    
				    document.getElementById('instituteSuit').value=isInstitutionSuit1;
				    document.getElementById('receiveDate').value=receiveDateArr[0];
				    document.getElementById('remandedText').value=remandedText1;
				    document.getElementById('stage').value=cmnLookupMstByStage1;
				    document.getElementById('dateOfFllng').value=dateOfFilingArr[0];
				    document.getElementById('resonsActionTaken').value=reasonsActionsTaken1;
				}
			}
			closeAddress('AddressLwyr');
			closeAddress('AddressPttnr');
			closeAddress('AddressRspndnt');
		   	document.getElementById('actionflag').value=jspName;
		   	getPageDetails(${caseId});
		}
	}
	else
	{
		document.getElementById('actionflag').value=jspName;
		submitForm(${fileId},${caseId});
	}
	count++;
   	
}
//select JSP
//PAge Details
//PAge Details
//multiple add
//addHEaringData
//addData
//addRecord
//edit and populate function
//update and delete functions
//submitform function
//check mandatory
//check address Functions
//getCaseSubType
//select Component and EmpSearch function
//get and process EmpData
//get and process Address
//show address Dtl
//make enable disable
</script>
<hdiits:form name="cmnCourt"  validate="true" method="POST" encType="multipart/form-data" action="javascript:submitForm(${fileId},${caseId});">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">

	<c:if test="${caseId==0}">
		<li class="selected"><a href="#" rel="caseDetails"><b><fmt:message key="HRMS.CRT.caseDetails" bundle="${ccLables}"/></b></a></li>
	</c:if>
	<c:if test="${caseId!=0}">
		<li class="selected" id="caseLink"><a href="#"  rel="caseDetails" onfocus="selectJsp('caseDtls','0');"><b><fmt:message key="HRMS.CRT.caseDetails" bundle="${ccLables}" /></b></a></li>
		<li id="hearingLink"><a href="#"  rel="hearingDetails" onfocus="selectJsp('hrngDtls','0');"><b><fmt:message key="HRMS.CRT.hrngDetalis" bundle="${ccLables}"/></b></a></li>
		<li id="petitionerLink"><a href="#"  rel="petitionerDetails" onfocus="selectJsp('pttnrDtls','0');"><b><fmt:message key="HRMS.CRT.pttnrDetails" bundle="${ccLables}"/></b></a></li>
		<li id="lawyerLink"><a href="#"  rel="lawyerDetails" onfocus="selectJsp('lwyrDtls','0');"><b><fmt:message key="HRMS.CRT.lwyrDetails" bundle="${ccLables}"/></b></a></li>
		<li id="respondentLink"><a href="#"  rel="respondentDetails" onfocus="selectJsp('rspndntDtls','0');"><b><fmt:message key="HRMS.CRT.rspndntDetails" bundle="${ccLables}"/></b></a></li>
		<li id="appealLink"><a href="#"  rel="appealDetails" onfocus="selectJsp('applDtls','0');"><b><fmt:message key="HRMS.CRT.applDetails" bundle="${ccLables}"/></b></a></li>
	</c:if>
	</ul>
</div>
<div id="caseDetails" class="tabcontent" tabno="0">
<hdiits:fieldGroup bundle="${ccLables}" expandable="false" id="caseDtlFldGrp" titleCaptionId="HRMS.CRT.caseDetails">
<table class="tabtable" align="left" width="100%">
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.initBy"  bundle="${ccLables}" /></b>
    </td>
    <td>	
    	<hdiits:select name="initBy" size ="1" captionid="drop_down" sort="false" mandatory="true">
      <c:forEach var="type" items="${typeLst}">
				<hdiits:option value="${type.lookupId}">
				${type.lookupShortName}</hdiits:option>
				</c:forEach>
		</hdiits:select>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.caseNo"  bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:text name="caseNo" caption="Case Number"  mandatory="true" maxlength="40" />
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.court" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="court" size ="1" captionid="drop_down" mandatory="true">
        <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <c:forEach var="court" items="${courtLst}">
				<hdiits:option value="${court.courtId}">
				${court.courtName}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.District" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="district" size ="1" captionid="drop_down">
        <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <c:forEach var="district" items="${districtLst}">
				<hdiits:option value="${district.districtId}">
				${district.districtName}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.caseType" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="caseType" size ="1" onchange="getCaseSubType(this);" captionid="drop_down" mandatory="true" >
       	<hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <c:forEach var="caseType" items="${caseTypeLst}">
        	
				<hdiits:option value="${caseType.lookupId}">
				${caseType.lookupDesc}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.caseSubType" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="caseSubType" size ="1" captionid="drop_down" mandatory="true" >
      <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <c:forEach var="caseSubType" items="${caseSubTypeLst}">
        	
				<hdiits:option value="${caseSubType.lookupId}">
				${caseSubType.lookupDesc}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.govtPleader" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="govtPleader" size ="1" captionid="drop_down">
        <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <!--<c:forEach var="govtPleader" items="${govtPleaderLst}">
				<hdiits:option value="${govtPleader.lookupId}">
				${govtPleader.lookupDesc}</hdiits:option>
				</c:forEach>
      --></hdiits:select>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.nameAct" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="nameAct" size ="1" captionid="drop_down">
        <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <!--<c:forEach var="nameAct" items="${nameActLst}">
				<hdiits:option value="${nameAct.lookupId}">
				${nameAct.lookupDesc}</hdiits:option>
				</c:forEach>
      --></hdiits:select>
    </td>
  </tr>
  <tr>
    <td rowspan="2">
      <b><hdiits:caption captionid="HRMS.CRT.breafFactsCase" bundle="${ccLables}"/></b>
    </td>
    <td rowspan="2">
      <hdiits:textarea rows="3" cols="40" name="breafFactsCase" mandatory="true" />
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.stayGranted" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="stayGranted" size ="1" captionid="drop_down">
        <hdiits:option value="1" >
          <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
        </hdiits:option>
        <hdiits:option value="2" >
          <fmt:message key="HRMS.No" bundle="${ccLables}"/>
        </hdiits:option>
      </hdiits:select>
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.provisionLaw" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:text name="provisionLaw" caption="Provision Of Law" mandatory="true" maxlength="40" />
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.exhibitGranted" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="exhibitGranted" size ="1" captionid="drop_down">
        <hdiits:option value="1" >
          <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
        </hdiits:option>
        <hdiits:option value="2" >
          <fmt:message key="HRMS.No" bundle="${ccLables}"/>
        </hdiits:option>
      </hdiits:select>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.pttnType" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="pttnType" size ="1" captionid="drop_down">
      	<hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>

        <c:forEach var="pttnType" items="${pttnLst}">
				<hdiits:option value="${pttnType.lookupId}">
				${pttnType.lookupDesc}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.subMatter" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="subMatter" size ="1" captionid="drop_down" mandatory="true" >
        <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <c:forEach var="subMatter" items="${subMatterLst}">
				<hdiits:option value="${subMatter.lookupId}">
				${subMatter.lookupDesc}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.instituteSuit" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="instituteSuit" size ="1" captionid="drop_down">
        <hdiits:option value="1" >
          <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
        </hdiits:option>
        <hdiits:option value="2" >
          <fmt:message key="HRMS.No" bundle="${ccLables}"/>
        </hdiits:option>
      </hdiits:select>
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.receiveDate" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:dateTime captionid="HRMS.CRT.receiveDate" bundle="${ccLables}" name="receiveDate" maxvalue="31/12/2099"/>    
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.remandedText" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:text name="remandedText" caption="Provision Of Law" mandatory="true" 
      	maxlength="40" />
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.stage" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="stage" size="1" sort="false" captionid="drop_down" mandatory="true" >
        <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <c:forEach var="stage" items="${stageLst}">
				<hdiits:option value="${stage.lookupId}">
				${stage.lookupDesc}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.dateOfFllng" bundle="${ccLables}"/></b>
    </td>
    <td>
    	<hdiits:dateTime captionid="HRMS.CRT.dateOfFllng" bundle="${ccLables}" mandatory="true" name="dateOfFllng" maxvalue="31/12/2099"/>
    </td>
  </tr>
  <tr>
  	<td>
      <b><hdiits:caption captionid="HRMS.CRT.caseResult" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:select name="caseResult" size ="1" captionid="drop_down" mandatory="true" >
        <hdiits:option value="0" >
          <fmt:message key="HRMS.CRT.SELECT" bundle="${ccLables}"/>
        </hdiits:option>
        <c:forEach var="caseResult" items="${caseResultLst}">
				<hdiits:option value="${caseResult.lookupId}">
				${caseResult.lookupDesc}</hdiits:option>
				</c:forEach>
      </hdiits:select>
    </td>
  	<td >
      <b><hdiits:caption captionid="HRMS.CRT.resonsActionTaken" bundle="${ccLables}"/></b>
    </td>
    <td >
      <hdiits:textarea rows="3" cols="40" name="resonsActionTaken"/>
    </td>	
  </tr>
  <tr>
  <td colspan="4">
  <center>
  		<hdiits:button name="saveCase" type="button" captionid="HRMS.CRT.saveCase" bundle="${ccLables}" onclick="selectJsp('caseDtls','1');"/>
  </center>
  </td>
  </tr>
  <tr>
  	<td colspan="4">
  		<table id="txnAddCase" align="center" style="border-collapse: collapse;display:none;" borderColor="BLACK"  border="1" width="75%"> 
		<tr bgcolor="#C9DFFF">
			<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.srNo"  bundle="${ccLables}" /></b></td>
	    	<td class="fieldLabel" align="center"> <b><hdiits:caption captionid="HRMS.CRT.stage"  bundle="${ccLables}" /></b></td>
    		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.stageDt"  bundle="${ccLables}"/></b></td>
	    </tr>
	</table>
  	</td>
  </tr>
</table>
</hdiits:fieldGroup>
</div>
<div id="hearingDetails" class="tabcontent" tabno="1">
<table class="tabtable" align="left" width="100%">
<tr>
<td>
	<jsp:include page="//WEB-INF/jsp/hrms/gnl/courtCases/hrngDtls.jsp" />
</td>
</tr>
<tr>
<td>
	<center>
		<hdiits:button name="addHearing" type="button" value="Add" onclick="addHearingData(${caseId},${fileId});"/>
		<hdiits:button name="updateHearing" type="button" style="display:none" value="Update" onclick="updateHearingData();"/>
	</center>
</td>
</tr>
<tr>
<td>	
    <table id="txnAddHrng" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border="1"  width="98%"> 
	<tr bgcolor="#C9DFFF">
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.DOH" bundle="${ccLables}" /></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.gov_p_crt" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.attndName" bundle="${ccLables}"/></b></td>  
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.typeNotice" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.advctGen" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.govAdvct" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.addAdvctGen" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.partyAdvct" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.hrngDetalis" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.nxtHDate" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.memAttnd" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.rqrAttnd" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center">Edit/Delete</td>
    </tr>
	</table>       
</td>
</tr>
</table>
</div>
<div id="petitionerDetails" class="tabcontent" tabno="2">
<table class="tabtable" align="left" width="100%">
<tr>
<td>
	<hdiits:fmtMessage bundle="${ccLables}" key="HRMS.CRT.petitionerAddress" var="petitionerAddress"/>
	<jsp:include page="//WEB-INF/jsp/hrms/gnl/courtCases/prsnDtls.jsp">
		<jsp:param name="prsnType" value="Pttnr" />
        <jsp:param name="title" value="HRMS.CRT.pttnrDetails" />
        <jsp:param name="dispFlag" value=""/>
        <jsp:param name="addressName" value="${petitionerAddress}"/>
	</jsp:include>
</td>
</tr>
<tr>
<td>
	<center>
		<hdiits:button name="addPetitioner" type="button" value="Add" onclick="addPetitionerData(${caseId},${fileId});"/>
		<hdiits:button name="updatePetitioner" style="display:none" type="button" value="Update" onclick="updatePetitionerData();"/>
	</center>
</td>
</tr>
<tr>
<td>
	<table id="txnAddPttnr" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border="1"  width="98%"> 
	<tr bgcolor="#C9DFFF">
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.prsnName"  bundle="${ccLables}" /></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.FName"  bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.age" bundle="${ccLables}"/></b></td>  
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.desig" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.mob" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.eMail" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.offNo" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.fax" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.Address" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center">Edit/Delete</td>
    </tr>
	</table>
</td>
</tr>
</table>
</div>
<div id="lawyerDetails" class="tabcontent" tabno="3">
<table class="tabtable" align="left" width="100%">
<tr>
<td>
	<hdiits:fmtMessage bundle="${ccLables}" key="HRMS.CRT.lawyerAddress" var="lawyerAddress" />
	<jsp:include page="//WEB-INF/jsp/hrms/gnl/courtCases/prsnDtls.jsp">
    	<jsp:param name="prsnType" value="Lwyr" />
        <jsp:param name="title" value="HRMS.CRT.lwyrDetails" />
        <jsp:param name="dispFlag" value=""/>
        <jsp:param name="addressName" value="${lawyerAddress}"/>
	</jsp:include>
</td>
</tr>
<tr>
<td>
<center>
		<hdiits:button name="addLawyer" type="button" value="Add" onclick="addLawyerData();"/>
		<hdiits:button name="updateLawyer" type="button" style="display:none" value="Update" onclick="updateLawyerData();"/>
	</center>
</td>
</tr>
<tr>
<td>
	<table id="txnAddLwyr" style="display:none" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border="1"  width="98%"> 
	<tr bgcolor="#C9DFFF">
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.prsnName"  bundle="${ccLables}" /></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.FName"  bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.age" bundle="${ccLables}"/></b></td>  
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.desig" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.mob" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.eMail" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.offNo" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.fax" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.Address" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center">Edit/Delete</td>
    </tr>
	</table>
</td>
</tr>

</table>
</div>
<div id="respondentDetails" class="tabcontent" tabno="4">
<table class="tabtable" align="left" width="100%">
<tr>
<td>

	<hdiits:fmtMessage bundle="${ccLables}" key="HRMS.CRT.respondentAddress" var="respondentAddress"/>
	<jsp:include page="//WEB-INF/jsp/hrms/gnl/courtCases/prsnDtls.jsp">
    	<jsp:param name="prsnType" value="Rspndnt" />
        <jsp:param name="title" value="HRMS.CRT.rspndntDetails" />
        <jsp:param name="dispFlag" value=""/>
        <jsp:param name="addressName" value="${respondentAddress}"/>
	</jsp:include>
</td>
</tr>
<tr>
<td>
<center>
		<hdiits:button name="addRespondent" type="button" value="Add" onclick="addRespondentData();"/>
		<hdiits:button name="updateRespondent" type="button" style="display:none" value="Update" onclick="updateRespondentData();"/>
</center>
</td>
</tr>
<tr>
<td>
	<table id="txnAddRspndnt" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border="1"  width="98%"> 
	<tr bgcolor="#C9DFFF">
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.prsnName"  bundle="${ccLables}" /></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.FName"  bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.age" bundle="${ccLables}"/></b></td>  
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.desig" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.mob" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.eMail" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.offNo" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.fax" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.Address" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center">Edit/Delete</td>
    </tr>
	</table>
	</td>
	</tr>

</table>
</div>
<div id="appealDetails" class="tabcontent" tabno="5">
<table class="tabtable" align="left" width="100%">
<tr>
<td>
	<jsp:include page="//WEB-INF/jsp/hrms/gnl/courtCases/applDtls.jsp"/>
</td>
</tr>
<tr>
<td>
	<table id="txnAddAppl" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border="1"  width="98%"> 
		<tr bgcolor="#C9DFFF">
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.applFieldBy" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.dtOfFlngAppl" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.rmrks" bundle="${ccLables}"/></b></td>  
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.apprvlAppl" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.instByLglDpt" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.dlyCondnd" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.smmrRejct" bundle="${ccLables}"/></b> </td>
    	<td class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.CRT.applAdmtd" bundle="${ccLables}"/></b></td>
    	<td class="fieldLabel" align="center">Edit/Delete</td>
    	</tr>
	</table>
	</td>
	</tr>
<tr>
<td>
<center>
		<hdiits:button name="addAppeal" type="button" value="Add" onclick="addAppealData(${caseId},${fileId});"/>
		<hdiits:button name="updateAppeal" type="button" style="display:none" value="Update" onclick="updateAppealData();"/>
	</center>
</td>
</tr>
</table>
</div>
        <input type="hidden" name="actionflag" value="" />
        <input type="hidden" name="personFlag" value="" />
        <input type="hidden" name="mappingId" value="" />
		<input type="hidden" name="addressId" value="" />
        <input type="hidden" name="displayFlag" value="" />
        <input type="hidden" name="CaseID" value="" /> 
        <input type="hidden" name="HearingID" value="" /> 
        <input type="hidden" name="fileId" value="" />
        <input type="hidden" name="AppealID" value="" />
        <input type="hidden" name="addressString" value="" />
        <input type="hidden" name="wffield_hidden" id="wffield_hidden"/>
  <hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<script type="text/javascript">
	initializetabcontent("maintab")
</script>

<script type="text/javascript">
	initPage(${fileId},${caseId},${displayFlag});
</script>

<%
 } catch(Exception e) {
	e.printStackTrace(); 
 }
 %>
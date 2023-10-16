<%try { %>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="a3" scope="request"/>
<html>
<head>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
  

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/ess/asset/assetAddress.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript">		

function forward()
{	
	/*method="POST";
	document.UpdatedPurchaseSaleDetails.action="./hrms.htm?actionFlag=AssetReqForward";
	document.UpdatedPurchaseSaleDetails.submit();*/
}

function Approve()
{	
	/*method="POST";
	document.UpdatedPurchaseSaleDetails.action="./hrms.htm?actionFlag=AssetReqApprove";
	document.UpdatedPurchaseSaleDetails.submit();*/
}

function AssetReject()
{
	
	method="POST";
	document.UpdatedPurchaseSaleDetails.action="./hrms.htm?actionFlag=AssetReqReject";
	document.UpdatedPurchaseSaleDetails.submit();

}



</script>
</head>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="assetType" value="${resValue.assetType}"></c:set>
<c:set var="otherAsset" value="${resValue.otherAsset}"></c:set>
<c:set var="assetName" value="${resValue.assetName}"></c:set>
<c:set var="assetArea" value="${resValue.assetArea}"></c:set>
<c:set var="surwayNo" value="${resValue.surwayNo}"></c:set>

<c:set var="registrationNo" value="${resValue.registrationNo}"></c:set>
<c:set var="TransationDate" value="${resValue.TransationDate}"></c:set>
<c:set var="transactionPrice" value="${resValue.transactionPrice}"></c:set>
<c:set var="transactionType" value="${resValue.transactionType}"></c:set>
<c:set var="partyType" value="${resValue.partyType}"></c:set>
<c:set var="isRelationWithParty" value="${resValue.isRelationWithParty}"></c:set>
<c:set var="familyRelation" value="${resValue.familyRelation}"></c:set>
<c:set var="FamilyName" value="${resValue.FamilyName}"></c:set>
<c:set var="organizationName" value="${resValue.organizationName}"></c:set>
<c:set var="personName" value="${resValue.personName}"></c:set>
<c:set var="ownerType" value="${resValue.ownerType}"></c:set>
<c:set var="isRelationWithOwner" value="${resValue.isRelationWithOwner}"></c:set>
<c:set var="ownerFamilyRelation" value="${resValue.ownerFamilyRelation}"></c:set>
<c:set var="ownerFamilyName" value="${resValue.ownerFamilyName}"></c:set>
<c:set var="ownerName" value="${resValue.ownerName}"></c:set>
<c:set var="reqId" value="${resValue.reqId}"></c:set>
<c:set var="assetId" value="${resValue.assetId}"></c:set>
<c:set var="status" value="${resValue.status}"></c:set>
<c:set var="appFlag" value="${resValue.appFlag}"></c:set>
<body>
<hdiits:form name="UpdatedPurchaseSaleDetails" validate="true" method="POST" encType="multipart/form-data"  action="">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="PER_PUR_SALE" bundle="${a3}"/></b></a></li>
	</ul>
</div>
<div id="asset" name="asset">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<br>

<c:if test="${status == 'A'}">
<table>
<tr>
<TD class=fieldLabel  align="left"><b><FONT color="RED"><fmt:message key="NOTE_REQ_NO" bundle="${a3}"/>${reqId} <fmt:message key="NOTE_APPROVE" bundle="${a3}"/> ${assetId}</FONT></b></TD>
</tr>
</table>
</c:if>	

<c:if test="${status == 'R'}">
<table>
<tr>
<TD class=fieldLabel  align="center"><b><FONT color="RED"><fmt:message key="NOTE_REJECT" bundle="${a3}"/> ${reqId} <fmt:message key="IS_REJECTED" bundle="${a3}"/> </FONT></b></TD>
</tr>
</table>
</c:if>
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup bundle="${a3}" expandable="true" id="assetDtlFldGrp" titleCaptionId="ASSET_DTLS">
<TABLE class=tabtable>
<TR>
<td class=fieldLabel width="25%"><hdiits:caption captionid="MOV_OR_NOT" bundle="${a3}"/></td>
<td  width="25%" id="assetType"><c:out value="${assetType.lookupDesc}"/></td>

	
<td class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${a3}"/></td>
<c:choose>
<c:when test="${assetName.lookupName == 'Other_fixed'}">
<td  width="25%" id="otherAsset"><c:out value="${otherAsset}"/></td>	
</c:when>
<c:when test="${assetName.lookupName == 'Other_movable'}">
<td  width="25%" id="otherAsset"><c:out value="${otherAsset}"/></td>	
</c:when>
<c:otherwise>
<td  width="25%" id="assetName"><c:out value="${assetName.lookupDesc}"/></td>	
</c:otherwise>
</c:choose>
</TR>

<c:if test="${assetName.lookupName == 'Car_asset'}">
		<tr>
		<td class=fieldLabel width="25%"><b><hdiits:caption captionid="REGI_NO" bundle="${a3}"/></b></td>
	    <td  width="25%" id="registrationNo"><c:out value="${registrationNo}"/></td>
		</tr>
		</c:if>
		<c:if test="${assetName.lookupName == 'Bike_asset'}">
		<tr>
		<td class=fieldLabel width="25%"><b><hdiits:caption captionid="REGI_NO" bundle="${a3}"/></b></td>
	    <td  width="25%" id="registrationNo"><c:out value="${registrationNo}"/></td>
		</tr>
		</c:if>
		<c:if test="${assetName.lookupName == 'Scooter_asset'}">
		<tr>
		<td class=fieldLabel width="25%"><b><hdiits:caption captionid="REGI_NO" bundle="${a3}"/></b></td>
	    <td  width="25%" id="registrationNo"><c:out value="${registrationNo}"/></td>
		</tr>
</c:if>
<c:if test="${assetName.lookupName == 'Land_asset'}">
<tr>
<td  width="25%" id="assetAreaTD">
<hdiits:caption captionid="ASSET_AREA" bundle="${a3}"/></td><td><c:out value="${assetArea}"/>

</td>
<td  width="25%" id="surwayNoTD">
<hdiits:caption captionid="ASSET_SURWAY" bundle="${a3}"/></td><td><c:out value="${surwayNo}"/>
</td>
</tr>
</c:if>
<c:if test="${assetType.lookupName == 'Fixed_asset'}">	
	<TABLE class=tabtable width="100%" readonly="true">
		<TR><TD>
			<hdiits:fmtMessage key="ADD_OF_ASSET"  bundle="${a3}" var="AssetAddress" />
			<jsp:include page="/WEB-INF/jsp/common/address.jsp">
							<jsp:param name="addrName" value="Address"/>
							<jsp:param name="addressTitle" value="${AssetAddress}"/>
							<jsp:param name="addrLookupName" value="Permanent Address"/>
							<jsp:param name="mandatory" value="Y" />						
		</jsp:include>	
		<script>
				makeEnableDisable("Address",0);
				</script>
			</TD>
	  </TR>
	</TABLE>
</c:if>
</TABLE>	
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${a3}" id="tranDtlFldGrp" expandable="true" titleCaptionId="ASSET_DTLS">
<TABLE class=tabtable>
<tr>
<td class=fieldLabel width="25%"><b><hdiits:caption captionid="PUR_SOLD" bundle="${a3}"/></b></td>
<td  width="25%" id="transactionType"><c:out value="${transactionType.lookupDesc}"/></td>
</tr>
<TR>   
<td class=fieldLabel width="25%"><b><hdiits:caption captionid="TRANXN_DATE" bundle="${a3}"/></b></td>
<td  width="25%" id="TransationDate"><fmt:formatDate value="${TransationDate}"/></td>

<td class=fieldLabel width="25%"><b><hdiits:caption captionid="TRANXN_PRICE" bundle="${a3}"/></b></td>
<td  width="25%" id="transactionPrice"><c:out value="${transactionPrice}"/></td>
</TR>
</TABLE>		
</hdiits:fieldGroup>


<hdiits:fieldGroup bundle="${a3}" expandable="true" id="partyDtlFldGrp" titleCaptionId="PARTY_DTLS"> 
<TABLE class=tabtable>
<TR>
<td class=fieldLabel width="25%"><hdiits:caption captionid="TYPE_OF_PARTY" bundle="${a3}"/></td>
<td  width="25%" id="partyType"><c:out value="${partyType.lookupDesc}"/></td>

<c:if test="${partyType.lookupName == 'Person'}">
<td class=fieldLabel width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${a3}"/></td>
<td  width="25%" id="isRelationWithParty">
<c:if test="${isRelationWithParty == 'Y'}">
<fmt:message bundle="${a3}" key="YES"></fmt:message></c:if>
<c:if test="${isRelationWithParty == 'N'}">
<fmt:message bundle="${a3}" key="NO"></fmt:message></c:if>
</td></TR>
</c:if>

<c:if test="${partyType.lookupName == 'Organization'}">
<td class=fieldLabel width="25%"><hdiits:caption captionid="COMPANY_NAME" bundle="${a3}"/></td>
<td  width="25%" id="organizationName"><c:out value="${organizationName}"/></td></tr>

<tr>
<td class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_PARTY" bundle="${a3}"/></td>
<td  width="25%" id="personName"><c:out value="${personName}"/></td>
</tr>
</TR>
</c:if>

<c:if test="${isRelationWithParty == 'Y'}">
<TR>
<td class=fieldLabel width="25%"><hdiits:caption captionid="RELATION_TYPE" bundle="${a3}"/></td>
<td  width="25%" id="familyRelation"><c:out value="${familyRelation}"/></td>

<td class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_PARTY" bundle="${a3}"/></td>
<td  width="25%" id="FamilyName"><c:out value="${FamilyName}"/></td>
</TR>
</c:if>

<c:if test="${partyType.lookupName == 'Person'}">
<c:if test="${isRelationWithParty == 'N'}">
<tr>
<td class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_PARTY" bundle="${a3}"/></td>
<td  width="25%" id="personName"><c:out value="${personName}"/></td>
</tr>
</c:if>
</c:if>
</TABLE>

<TABLE class=tabtable width="100%" readonly="true">
		<TR><TD>
		<hdiits:fmtMessage key="PARTY_ADDRESS"  bundle="${a3}" var="PartyAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="GuardianAddress"/>
						<jsp:param name="addressTitle" value="${PartyAddress}"/>
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y" />						
				</jsp:include>
				<script>
				makeEnableDisable("GuardianAddress",0);
				</script>
				</td>	
  
</tr>
</TABLE>
</hdiits:fieldGroup>

<c:if test="${transactionType.lookupName == 'Purchase_asset'}">
<hdiits:fieldGroup bundle="${a3}" expandable="true" id="ownrDtlFldGrp" titleCaptionId="OWNER_DTLS">


<TABLE class=tabtable>
<TR>
<td class=fieldLabel width="25%"><hdiits:caption captionid="OWNER" bundle="${a3}"/></td>
<td id="ownerType"><c:out value="${ownerType.lookupDesc}"/></td>


<c:if test="${ownerType.lookupName == 'Asset_other'}">
<td class=fieldLabel width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${a3}"/></td>
<td  width="25%" id="isRelationWithOwner">
<c:if test="${isRelationWithOwner == 'Y'}">
<fmt:message bundle="${a3}" key="YES"></fmt:message></c:if>
<c:if test="${isRelationWithOwner == 'N'}">
<fmt:message bundle="${a3}" key="NO"></fmt:message></c:if></td>
</c:if>
</TR>

<c:if test="${isRelationWithOwner == 'Y'}">
<tr>
<td class=fieldLabel width="25%"><hdiits:caption captionid="RELATION" bundle="${a3}"/></td>
<td  width="25%" id="ownerFamilyRelation"><c:out value="${ownerFamilyRelation}"/></td>

<td class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_OWNER" bundle="${a3}"/></td>
<td  width="25%" id="ownerFamilyName"><c:out value="${ownerFamilyName}"/></td>
</tr>
</c:if>

<c:if test="${isRelationWithOwner == 'N'}">
<tr>
<td class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_PARTY" bundle="${a3}"/></td>
<td  width="25%" id="ownerName"><c:out value="${ownerName}"/></td>
</tr>
</c:if>
<c:if test="${ownerType.lookupName == 'Asset_other'}">
<TABLE class=tabtable>
	<TR>
  <td>
  		<hdiits:fmtMessage key="OWNER_ADDRESS"  bundle="${a3}" var="OwnerAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="OwnerJoiningAddress"/>
						<jsp:param name="addressTitle" value="${OwnerAddress}"/>
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y" />						
				</jsp:include>
				<script>
				makeEnableDisable("OwnerJoiningAddress",0);
				</script>
				</td>	
  
 </TR>
</TABLE> 
</c:if>

</TABLE>
</hdiits:fieldGroup>
</c:if>

<c:if test="${status == 'N'}">    
<TABLE class=tabtable>
<tr>
<td align="center" style="Display:none">
<hdiits:button name="btnforward" type="button" onclick="forward()" value="Forward" />
<hdiits:button name="Submit" type="button" caption="Approve" onclick="Approve()"  />
<hdiits:button name="Reject" type="button" caption="Reject" onclick="AssetReject()"  />
</td>
</tr>
</TABLE>
</c:if>

<hdiits:hidden name="RequestId" id="RequestId"/>
<hdiits:hidden name="RequestedUser" id="RequestedUser"/>
<hdiits:hidden name="RequestedDesignation" id="RequestedDesignation"/>
<hdiits:hidden name="PostId" id="PostId"/>
<hdiits:hidden name="assetId" id="assetId"/>
<hdiits:hidden name="applicationFlag" id="applicationFlag"/>
<script>
	var RequestId=document.getElementById('RequestId');
	RequestId.value="${reqId}";
	var RequestedUser=document.getElementById('RequestedUser');
	RequestedUser.value="${map.name}";
	var RequestedDesignation=document.getElementById('RequestedDesignation');
	RequestedDesignation.value="${map.designation}";
	var PostId=document.getElementById('PostId');
	PostId.value="${map.postId}";
	var assetId=document.getElementById('assetId');
	assetId.value="${assetId}";
	var applicationFlag=document.getElementById('applicationFlag');
	applicationFlag.value="${appFlag}";
</script>	
	
	</div>
	</div>
	
	<script type="text/javascript">
	initializetabcontent("maintab")
	</script>	
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />	
	
	</hdiits:form>
</body>
</html>		

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
		
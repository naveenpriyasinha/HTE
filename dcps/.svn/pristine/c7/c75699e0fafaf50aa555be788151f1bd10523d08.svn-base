<%try { %>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="a3" scope="request"/>
<html>
<head>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 


<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript">		

function forward()
{	alert("forward");
	/*method="POST";
	document.PurchaseSaleDetails.action="./hrms.htm?actionFlag=AssetReqForward";
	document.PurchaseSaleDetails.submit();*/
}

function Approve()
{
	/*method="POST";
	document.PurchaseSaleDetails.action="./hrms.htm?actionFlag=AssetReqApprove";
	document.PurchaseSaleDetails.submit();*/
}

function AssetReject()
{
	
	method="POST";
	document.PurchaseSaleDetails.action="./hrms.htm?actionFlag=AssetReqReject";
	document.PurchaseSaleDetails.submit();

}

</script>
</head>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="reqId" value="${resValue.reqId}"></c:set>
<c:set var="assetId" value="${resValue.assetId}"></c:set>
<c:set var="reqType" value="${resValue.reqType}"></c:set>
<c:set var="transactionType" value="${resValue.transactionType}"></c:set>
<c:set var="assetType" value="${resValue.assetType}"></c:set>
<c:set var="assetName" value="${resValue.assetName}"></c:set>
<c:set var="otherAsset" value="${resValue.otherAsset}"></c:set>
<c:set var="assetArea" value="${resValue.assetArea}"></c:set>
<c:set var="surwayNo" value="${resValue.surwayNo}"></c:set>
<c:set var="registrationNo" value="${resValue.registrationNo}"></c:set>
<c:set var="TransationDate" value="${resValue.TransationDate}"></c:set>
<c:set var="transactionPrice" value="${resValue.transactionPrice}"></c:set>
<c:set var="marketPrice" value="${resValue.marketPrice}"></c:set>
<c:set var="totalAssetPaymentData" value="${resValue.totalAssetPaymentData}"></c:set>
<c:set var="ModeOfPurchase" value="${resValue.ModeOfPurchase}"></c:set>
<c:set var="otherMode" value="${resValue.otherMode}"></c:set>
<c:set var="MediumOfDeal" value="${resValue.MediumOfDeal}"></c:set>
<c:set var="otherMedium" value="${resValue.otherMedium}"></c:set>
<c:set var="billNo" value="${resValue.billNo}"></c:set>
<c:set var="billDate" value="${resValue.billDate}"></c:set>
<c:set var="BillAttachment" value="${resValue.BillAttachment}"></c:set>
<c:set var="partyType" value="${resValue.partyType}"></c:set>
<c:set var="isRelationWithParty" value="${resValue.isRelationWithParty}"></c:set>
<c:set var="familyRelation" value="${resValue.familyRelation}"></c:set>
<c:set var="FamilyName" value="${resValue.FamilyName}"></c:set>
<c:set var="isFutureRelation" value="${resValue.isFutureRelation}"></c:set>
<c:set var="detailWorkRelation" value="${resValue.detailWorkRelation}"></c:set>
<c:set var="remarks" value="${resValue.remarks}"></c:set>
<c:set var="organizationName" value="${resValue.organizationName}"></c:set>
<c:set var="personName" value="${resValue.personName}"></c:set>
<c:set var="status" value="${resValue.status}"></c:set>
<c:set var="ownerType" value="${resValue.ownerType}"></c:set>
<c:set var="isRelationWithOwner" value="${resValue.isRelationWithOwner}"></c:set>
<c:set var="ownerFamilyRelation" value="${resValue.ownerFamilyRelation}"></c:set>
<c:set var="ownerFamilyName" value="${resValue.ownerFamilyName}"></c:set>
<c:set var="ownerName" value="${resValue.ownerName}"></c:set>
<c:set var="sellDate" value="${resValue.sellDate}"></c:set>
<c:set var="sellPrice" value="${resValue.sellPrice}"></c:set>
<c:set var="isInformed" value="${resValue.isInformed}"></c:set>
<c:set var="AssetAttachment" value="${resValue.AssetAttachment}"></c:set>
<c:set var="reasonNotInform" value="${resValue.reasonNotInform}"></c:set>
<c:set var="isGiftPermision" value="${resValue.isGiftPermision}"></c:set>
<c:set var="giftReason" value="${resValue.giftReason}"></c:set>
<c:set var="appFlag" value="${resValue.appFlag}"></c:set>
<c:set var="cancleFlag" value="${resValue.cancleFlag}"></c:set>


<body>
<hdiits:form name="PurchaseSaleDetails" validate="true" method="POST" encType="multipart/form-data" action="">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<c:if test="${appFlag == 'purchaseSaleDetails'}">
		<li class="selected">
		<a href="#" rel="tcontent1">
			<b><fmt:message key="PER_PUR_SALE" bundle="${a3}"/></b>
		</a>
		</li>
		</c:if>
		<c:if test="${appFlag == 'adminScreenDetails'}">
		<li class="selected">
		<a href="#" rel="tcontent1">
			<b><fmt:message key="ASSET_ADMIN_SCREEN" bundle="${a3}"/></b>
			</a>
			</li>
		</c:if>
	</ul>
</div>
<div id="asset" name="asset">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<BR>
		
<c:if test="${cancleFlag == 'N'}">
<c:if test="${status == 'A'}">
<table>
<tr>
<TD class=fieldLabel  align="left"><b><FONT color="RED"><fmt:message key="NOTE_ASSET_REQ" bundle="${a3}"/> ${reqId} <fmt:message key="NOTE_APPROVE" bundle="${a3}"/> ${assetId}</FONT></b></TD>
</tr>
<c:if test="${reqType.lookupName == 'Permission_asset'}">
<tr>
<TD class=fieldLabel  align="left"><b><FONT color="RED"><fmt:message key="NOTE_EDIT" bundle="${a3}"/> </FONT></b></TD>
</tr>
</c:if>
</table>
</c:if>	
</c:if>

<c:if test="${cancleFlag == 'N'}">
<c:if test="${status == 'R'}">
<TD class=fieldLabel  align="center"><b><FONT color="RED"><fmt:message key="NOTE_REJECT" bundle="${a3}"/>${reqId}<fmt:message key="IS_REJECTED" bundle="${a3}"/></FONT></b></TD>
</c:if>
</c:if>
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup bundle="${a3}" expandable="true" id="assetReqFldGrp" titleCaptionId="ASSET_REQ">
<TABLE class=tabtable>
<TR>   
<td class=fieldLabel width="25%"><b><hdiits:caption captionid="REQ_TYPE" bundle="${a3}"/></b></td>
<td  width="25%" id="reqtype"><c:out value="${reqType.lookupDesc}"/></td>

<td class=fieldLabel width="25%"><b><hdiits:caption captionid="PUR_SOLD" bundle="${a3}"/></b></td>
<td  width="25%" id="transactionType"><c:out value="${transactionType.lookupDesc}"/></td>
</TR>

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
<c:if test="${assetName.lookupName == 'Land_asset'}">
</tr>
<tr>
<td  width="25%" id="assetAreaTD">
<hdiits:caption captionid="ASSET_AREA" bundle="${a3}"/></td><td><c:out value="${assetArea}"/>

</td>
<td  width="25%" id="surwayNoTD">
<hdiits:caption captionid="ASSET_SURWAY" bundle="${a3}"/></td><td><c:out value="${surwayNo}"/>
</td>
</c:if>	
</c:otherwise>
</c:choose>
</TR>

<c:if test="${reqType.lookupName == 'Intimation_asset'}">	
	<c:if test="${transactionType.lookupName == 'Purchase_asset'}">
		
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
	</c:if>
</c:if>
<c:if test="${transactionType.lookupName == 'sale_asset'}">
		
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
	</c:if>
</TABLE>

<c:if test="${assetType.lookupName == 'Fixed_asset'}">	
	<TABLE class=tabtable width="100%" readonly="true">
		<TR><TD>
			<hdiits:fmtMessage key="ADD_OF_ASSET"  bundle="${a3}" var="AssetAddress" />
			<jsp:include page="/WEB-INF/jsp/common/viewAddress.jsp">
				<jsp:param name="addrName" value="Address" />
				<jsp:param name="addressTitle" value="${AssetAddress}"/>
			</jsp:include>
			</TD>
	  </TR>
	</TABLE>
</c:if>
</hdiits:fieldGroup>
<hdiits:fieldGroup bundle="${a3}" expandable="true" id="tranDtls" titleCaptionId="TRAN_DTLS">
<TABLE class=tabtable>

<c:if test="${transactionType.lookupName == 'Purchase_asset'}">
	<TR>   
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${a3}"/></b></td>
	<td  width="25%" id="TransationDate"><fmt:formatDate value="${TransationDate}"/></td>
	
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="APP_PRICE" bundle="${a3}"/></b></td>
	<td  width="25%" id="transactionPrice"><c:out value="${transactionPrice}"/></td>
	</TR>
</c:if>
</table>
<c:if test="${transactionType.lookupName == 'Purchase_asset'}">
<table id="addSrcOfPaymentTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="25%"><b><hdiits:caption captionid="PAYMENT_PLACE" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="25%"><b><hdiits:caption captionid="PRICE_FROM_PERTICULAR_SRC" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="50%"><b><hdiits:caption captionid="SAVING_SRC" bundle="${a3}" /></b></td>
</tr>
<c:forEach items="${totalAssetPaymentData}" var="totalAssetPaymentData" varStatus="x">
<tr>
<td><c:out value="${totalAssetPaymentData.cmnLookupMst.lookupDesc}"/></td>
<td><c:out value="${totalAssetPaymentData.investedPrice}"/></td>
<td><c:out value="${totalAssetPaymentData.descOfPayment}"/></td>
</tr>
</c:forEach>
</table>
</c:if>
<c:if test="${transactionType.lookupName == 'sale_asset'}">
<table class=tabtable>
<TR>   
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="SELL_DATE" bundle="${a3}"/></b></td>
	<td  width="25%" id="SellDate"><fmt:formatDate value="${sellDate}"/></td>
	
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="SELL_PRICE" bundle="${a3}"/></b></td>
	<td  width="25%" id="SellPrice"><c:out value="${sellPrice}"/></td>
	</TR>
	
	<tr>
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="PERMISSION_TAKEN" bundle="${a3}"/></b></td>
	<td  width="25%" id="permissionTaken">
	<c:if test="${isInformed == 'Y'}">
		<fmt:message bundle="${a3}" key="YES"></fmt:message>
	</c:if>
	<c:if test="${isInformed == 'N'}">
		<fmt:message bundle="${a3}" key="NO"></fmt:message>
	</c:if></td>
	
	<c:if test="${isInformed == 'N'}">
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="RSN_PERMISSION" bundle="${a3}"/></b></td>
	<td  width="25%" id="rsnForNoPermission"><c:out value="${reasonNotInform}"/></td>
	</c:if>
	</tr>
	
	<c:if test="${isInformed == 'Y'}">
	<tr>
	 <TD class=fieldLabel width="30%"><hdiits:caption captionid="ATTACH" bundle="${a3}"/></td>
   </tr>
   <tr>	
	<td class=fieldLabel width="25%" colspan="4">
		<table border='0' width="100%">
			<tr>
				<td>
					
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="AssetAttachment" />
						<jsp:param name="formName" value="PurchaseSaleDetails" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />    
                        </jsp:include>
					<!-- For attachment : End-->
					<script>
				document.getElementById('target_uploadAssetAttachment').style.display='none';
				document.getElementById('formTable1AssetAttachment').firstChild.firstChild.style.display='none';
			</script> 
				</td>
			</tr>
		</table>
	</td> 
	</tr>
	</c:if>
</table>
</c:if>
<table class=tabtable>
<tr>
<td class=fieldLabel width="25%"><b><hdiits:caption captionid="PUR_SELL_RCV" bundle="${a3}"/></b></td>
<c:choose>
<c:when test="${ModeOfPurchase.lookupName == 'Other_mode'}">
<td  width="25%" id="otherMode"><c:out value="${otherMode}"/></td>	
</c:when>
<c:otherwise>
<td  width="25%" id="ModeOfPurchase"><c:out value="${ModeOfPurchase.lookupDesc}"/></td>	
</c:otherwise>
</c:choose>

<td class=fieldLabel width="25%"><b><hdiits:caption captionid="MEDIUM_OF_DEAL" bundle="${a3}"/></b></td>
<c:choose>
<c:when test="${MediumOfDeal.lookupName == 'Other_medium'}">
<td  width="25%" id="otherMedium"><c:out value="${otherMedium}"/></td>	
</c:when>
<c:otherwise>
<td  width="25%" id="MediumOfDeal"><c:out value="${MediumOfDeal.lookupDesc}"/></td>	
</c:otherwise>
</c:choose>
</tr>
</TABLE>

<c:if test="${reqType.lookupName == 'Intimation_asset'}">	
	<c:if test="${ModeOfPurchase.lookupName == 'Gift'}">
	<TABLE class=tabtable>
	<tr>
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="PERMISSION_OBTAINED" bundle="${a3}"/></b></td>
	<td  width="25%" id="isGiftPermision">
	<c:if test="${isGiftPermision == 'Y'}">
		<fmt:message bundle="${a3}" key="YES"></fmt:message>
	</c:if>
	<c:if test="${isGiftPermision == 'N'}">
		<fmt:message bundle="${a3}" key="NO"></fmt:message>
	</c:if></td>
	
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="ASN_GIFT" bundle="${a3}"/></b></td>
	<td  width="25%" id="giftReason"><fmt:formatDate value="${giftReason}"  /></td>
	</tr>
	</TABLE>
	</c:if>
</c:if>	
</hdiits:fieldGroup>
<c:if test="${reqType.lookupName == 'Intimation_asset'}">	
	<c:if test="${transactionType.lookupName == 'Purchase_asset'}">
	<hdiits:fieldGroup bundle="${a3}" expandable="true" id="billDtlFldGrp" titleCaptionId="BILL_DTLS">
	
	<TABLE class=tabtable>
	<tr>
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="BILL_NO" bundle="${a3}"/></b></td>
	<td  width="25%" id="billNo"><c:out value="${billNo}"/></td>
	
	<td class=fieldLabel width="25%"><b><hdiits:caption captionid="BILL_DATE" bundle="${a3}"/></b></td>
	<td  width="25%" id="billDate"><fmt:formatDate value="${billDate}"  /></td>
	</tr>
	</TABLE>
	<TABLE class=tabtable>
  <TR>  
    <TD class=fieldLabel width="30%" id="attachbill"><hdiits:caption captionid="BILL_ATTACHMENT" bundle="${a3}"/></td>
   </tr>
   <tr>	
	<td class=fieldLabel width="25%" id="attachbill1"  colspan="4">
		<table border='0' width="100%">
			<tr>
				<td>
					<!-- (Example-Scan and Attach Medical Certificate In Case Any) -->
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="BillAttachment" />
						<jsp:param name="formName" value="PurchaseSaleDetails" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />    
                        </jsp:include>
					<!-- For attachment : End-->
					<script>
				document.getElementById('target_uploadBillAttachment').style.display='none';
				document.getElementById('formTable1BillAttachment').firstChild.firstChild.style.display='none';
			</script> 
				</td>
			</tr>
			</table>
			</td> 
		</tr>     
	</TABLE>
	</hdiits:fieldGroup>
	</c:if>	
</c:if>	


<hdiits:fieldGroup bundle="${a3}" expandable="true" id="partyDtlFldGrp" titleCaptionId="PARTY_DTLS">
<TABLE class=tabtable>
<TR>
<td class=fieldLabel width="25%"><hdiits:caption captionid="TYPE_OF_PARTY" bundle="${a3}"/></td>
<td  width="25%" id="partyType"><c:out value="${partyType.lookupDesc}"/></td>

<c:if test="${partyType.lookupName == 'Person'}">
<td class=fieldLabel width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${a3}"/></td>
<td  width="25%" id="isRelationWithParty">
	<c:if test="${isRelationWithParty == 'Y'}">
		<fmt:message bundle="${a3}" key="YES"></fmt:message>
	</c:if>
	<c:if test="${isRelationWithParty == 'N'}">
		<fmt:message bundle="${a3}" key="NO"></fmt:message>
	</c:if></td></TR>
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

<TABLE class=tabtable width="100%">
		<TR><TD>
		<hdiits:fmtMessage key="PARTY_ADDRESS"  bundle="${a3}" var="AddressOfParty"/>
		<jsp:include page="/WEB-INF/jsp/common/viewAddress.jsp">
				<jsp:param name="addrName" value="GuardianAddress" />
				<jsp:param name="addressTitle" value="${AddressOfParty}"/>
			</jsp:include></td>	
  
</tr>
</TABLE>

<TABLE class=tabtable>  
  <TR>
  <td class=fieldLabel width="25%"><hdiits:caption captionid="PRO_RELATION_FUTURE" bundle="${a3}"/></td>
  <td  width="25%" id="isFutureRelation">
  <c:if test="${isFutureRelation == 'Y'}">
		<fmt:message bundle="${a3}" key="YES"></fmt:message>
	</c:if>
	<c:if test="${isFutureRelation == 'N'}">
		<fmt:message bundle="${a3}" key="NO"></fmt:message>
	</c:if></td>
  
  <c:if test="${isFutureRelation == 'Y'}">
  <td class=fieldLabel width="25%"><hdiits:caption captionid="PER_DTLS" bundle="${a3}"/></td>
  <td  width="25%" id="detailWorkRelation"><c:out value="${detailWorkRelation}"/></td>
  </c:if>
  </TR>
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
		<fmt:message bundle="${a3}" key="YES"></fmt:message>
	</c:if>
	<c:if test="${isRelationWithOwner == 'N'}">
		<fmt:message bundle="${a3}" key="NO"></fmt:message>
	</c:if></td>
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
  		<hdiits:fmtMessage key="OWNER_ADDRESS"  bundle="${a3}" var="OwnerAddress"/>
  		<jsp:include page="/WEB-INF/jsp/common/viewAddress.jsp">
				<jsp:param name="addrName" value="OwnerJoiningAddress" />
				<jsp:param name="addressTitle" value="${OwnerAddress}"/>
			</jsp:include>
		</td>	
  
 </TR>
</TABLE> 
</c:if>

</TABLE>
</hdiits:fieldGroup>
</c:if>


<TABLE class=tabtable>
<tr>

	<TD class=fieldLabel width="8%"><hdiits:caption captionid="REMARKS" bundle="${a3}"/></td>
	<td  width="25%" id="remarks"><c:out value="${remarks}"/></td>
</tr> 
</table>

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
<hdiits:hidden name="wfcorrsId_hidden" id="wfcorrsId_hidden" default="${resValue.corr_id}"/>
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

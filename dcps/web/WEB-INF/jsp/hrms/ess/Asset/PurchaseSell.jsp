<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<fmt:setBundle basename="resources.ess.asset.AssetAlertsMsg" var="as1" scope="request"/>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="al" scope="request"/> 
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/ess/asset/assetPermission.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/ess/asset/assetAddress.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetValidation.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetAdminScreen.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetPurchaseSellPayment.js"></script>

<c:set var="attachments" value=""/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="FixedList" value="${resultValue.AssetFixed}"></c:set>		
<c:set var="MovableList" value="${resultValue.AssetMovable}"></c:set>
<c:set var="ModeOfPaymentList" value="${resultValue.ModeOfPayment}"></c:set>
<c:set var="ModeofpurchaseList" value="${resultValue.Modeofpurchase}"></c:set>
<c:set var="Transaction_typeList" value="${resultValue.Transaction_type}"></c:set>
<c:set var="MediumofdealList" value="${resultValue.Mediumofdeal}"></c:set>
<c:set var="TypeofassetList" value="${resultValue.Typeofasset}"></c:set>
<c:set var="RequesttypeList" value="${resultValue.Request_type}"></c:set>
<c:set var="PartyTypeList" value="${resultValue.Party_Type}"></c:set>
<c:set var="purchasedList" value="${resultValue.purchasedList}"></c:set>
<c:set var="relationComboValue" value="${resultValue.relationComboValue}"></c:set>
<c:set var="familyList" value="${resultValue.familyList}"></c:set>
<c:set var="assetIdList" value="${resultValue.assetIdList}"></c:set>
<c:set var="assetOwnerIdList" value="${resultValue.Asset_ownerDtls}"></c:set>
<c:set var="saleAssetComboValue" value="${resultValue.saleAssetComboValue}"></c:set>
<c:set var="flag" value="${resultValue.flag}"></c:set>
<c:set var="adminTranxnTypeList" value="${resultValue.admin_tranxn_type}"></c:set>
<c:set var="totalEditData" value="${resultValue.totalEditData}"></c:set>
<c:set var="editStatus" value="${resultValue.editStatus}"></c:set>
<c:set var="applicationFlag" value="${resultValue.applicationFlag}"></c:set>
<c:set var="maxPriceLimit" value="${resultValue.maxPriceLimit}"></c:set>
<c:set var="NoFamilyDtls" value="${resultValue.NoFamilyDtls}"></c:set>
<c:set var="beHalfUserId" value="${resultValue.beHalfUserId}"></c:set>

<script language="javascript">
var addressAlertMsg=new Array();
addressAlertMsg[0]='<fmt:message bundle="${as1}" key="ASSET.DistrictAlert"/>';
addressAlertMsg[1]='<fmt:message bundle="${as1}" key="ASSET.TalukaAlert"/>';
addressAlertMsg[2]='<fmt:message bundle="${as1}" key="ASSET.VillageAlert"/>';
addressAlertMsg[3]='<fmt:message bundle="${as1}" key="ASSET.AreaAlert"/>';
addressAlertMsg[4]='<fmt:message bundle="${as1}" key="ASSET.CityAlert"/>';
addressAlertMsg[5]='<fmt:message bundle="${as1}" key="ASSET.OtherAreaAlert"/>';
addressAlertMsg[6]='<fmt:message bundle="${as1}" key="ASSET.OtherCityAlert"/>';
addressAlertMsg[7]='<fmt:message bundle="${as1}" key="ASSET.OtherVilaageAlert"/>';
addressAlertMsg[8]='<fmt:message bundle="${as1}" key="ASSET.AddressAlert"/>';
addressAlertMsg[10]='<fmt:message bundle="${as1}" key="ASSET.CountryAlert"/>';
addressAlertMsg[11]='<fmt:message bundle="${as1}" key="ASSET.StateAlert"/>';



var purchaseSellAlertMsg=new Array();
purchaseSellAlertMsg[0]='<fmt:message  bundle="${as1}" key="ASSET.SELECT_SRC_OF_PAYMENT"/>';
purchaseSellAlertMsg[1]='<fmt:message  bundle="${as1}" key="ASSET.DESC_OF_OTHER_SRC"/>';
purchaseSellAlertMsg[2]='<fmt:message  bundle="${as1}" key="ASSET.ADD_OF_ASSET"/>';
purchaseSellAlertMsg[3]='<fmt:message  bundle="${as1}" key="ASSET.PURCHASED_DATE"/>';
purchaseSellAlertMsg[4]='<fmt:message  bundle="${as1}" key="ASSET.PURCHASED_DATE_GRATER"/>';
purchaseSellAlertMsg[5]='<fmt:message  bundle="${as1}" key="ASSET.PLEASE_ADD_RECORD"/>';
purchaseSellAlertMsg[6]='<fmt:message  bundle="${as1}" key="ASSET.REGI_NO"/>';
purchaseSellAlertMsg[7]='<fmt:message  bundle="${as1}" key="ASSET.SELL_DATE_LESS"/>';
purchaseSellAlertMsg[8]='<fmt:message  bundle="${as1}" key="ASSET.SELL_DATE_GREATER"/>';
purchaseSellAlertMsg[9]='<fmt:message  bundle="${as1}" key="ASSET.ADD_OF_PARTY"/>';
purchaseSellAlertMsg[10]='<fmt:message  bundle="${as1}" key="ASSET.ADD_OF_OWNER"/>';
purchaseSellAlertMsg[11]='<fmt:message  bundle="${as1}" key="ASSET.TRANXN_DATE_LESS"/>';
purchaseSellAlertMsg[12]='<fmt:message  bundle="${as1}" key="ASSET.VALID_DATE"/>';
purchaseSellAlertMsg[13]='<fmt:message  bundle="${as1}" key="ASSET.TRANXN_DATE"/>';
purchaseSellAlertMsg[14]='<fmt:message  bundle="${as1}" key="ASSET.VALID_PRICE"/>';
purchaseSellAlertMsg[15]='<fmt:message  bundle="${as1}" key="ASSET.TRANXN_PRICE"/>';
purchaseSellAlertMsg[16]='<fmt:message  bundle="${as1}" key="ASSET.IS_EDIT_PARTY_DTLS"/>';
purchaseSellAlertMsg[17]='<fmt:message  bundle="${as1}" key="ASSET.TYPE_OF_PARTY"/>';
purchaseSellAlertMsg[18]='<fmt:message  bundle="${as1}" key="ASSET.ANY_RELATION"/>';
purchaseSellAlertMsg[19]='<fmt:message  bundle="${as1}" key="ASSET.SPECIFY_RELATION"/>';
purchaseSellAlertMsg[20]='<fmt:message  bundle="${as1}" key="ASSET.COMPANY_NAME"/>';
purchaseSellAlertMsg[21]='<fmt:message  bundle="${as1}" key="ASSET.PARTY_NAME"/>';
purchaseSellAlertMsg[22]='<fmt:message  bundle="${as1}" key="ASSET.IS_EDIT_OWNER_DTLS"/>';
purchaseSellAlertMsg[23]='<fmt:message  bundle="${as1}" key="ASSET.OWNER"/>';
purchaseSellAlertMsg[24]='<fmt:message  bundle="${as1}" key="ASSET.OWNER_NAME"/>';
purchaseSellAlertMsg[25]='<fmt:message  bundle="${as1}" key="ASSET.DO_WANT_UPDATE"/>';
purchaseSellAlertMsg[26]='<fmt:message  bundle="${as1}" key="ASSET.UPDATE_SUCESSFULLY"/>';
purchaseSellAlertMsg[27]='<fmt:message  bundle="${as1}" key="ASSET.ASSET_AREA"/>';
purchaseSellAlertMsg[28]='<fmt:message  bundle="${as1}" key="ASSET.ASSET_SURWAY"/>';
	
function submitreq()
{
if("${flag}" == 'assetDeclaration')
{
	var limitPrice = "${maxPriceLimit}";
	var minvalue = 1;
	if(document.form1.purchasesale.value == 'Purchase_asset')
	{
		
		if(parseInt(document.form1.approxprice.value) > limitPrice || parseInt(document.form1.approxprice.value) < 1)
		{
			alert('<fmt:message  bundle="${as1}" key="ASSET.PURCHASED_PRICE_LIMIT"/>'+" " +limitPrice);
			document.form1.approxprice.focus();
			return false;
		}
	
	}
	if(document.form1.purchasesale.value == 'sale_asset')
	{
		
		if(parseInt(document.form1.sellingprice.value) > limitPrice || parseInt(document.form1.sellingprice.value) < 1)
		{
			alert('<fmt:message  bundle="${as1}" key="ASSET.SELL_PRICE_LIMIT"/>'+" " +limitPrice);
			document.form1.sellingprice.focus();
			return false;
		}
	}
}
	startProcess();
	window.setTimeout('submitForm_Submit("form1")',1000);

}	 	
function submitAllDtls()
{

	if(validatePurchaseSell())
	{
		var agree=confirm('<fmt:message  bundle="${as1}" key="ASSET.DO_WANT_SUBMIT"/>');
     	if (agree)
     	{
			var urlstyle="hdiits.htm?actionFlag=insertDetails"
			document.form1.action=urlstyle;
			document.form1.submit();
		}	
	}
	else
	{
		return;
	}	
}

function AddSrcForPayment()
{	
	if(paymentValidation())
	{
		addDataRelatedToPayment();
	}	
}	
function UpdateSrcForPayment()
{
	if(paymentValidation())
	{
		updateDataRelatedToPayment();
	}
}	
		
</script>

<hdiits:form name="form1" validate="true" method="post" encType="multipart/form-data"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<c:if test="${flag == 'assetPermission'}">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="PER_PUR_SALE" bundle="${al}"/></b></a></li>
		<li class="selected"><a href="#" rel="tcontent2"><b><fmt:message key="EDIT_DATA" bundle="${al}"/></b></a></li>
	</c:if>	
	<c:if test="${flag == 'assetDeclaration'}">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL" bundle="${al}"/></b></a></li>
	</c:if>
	<c:if test="${flag == 'assetAdminScreen'}">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="PER_PUR_SALE" bundle="${al}"/></b></a></li>
	</c:if>	
	</ul>
</div>
	 
	
	
	<div id="tcontent1" class="tabcontent" tabno="0">


<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup bundle="${al}" expandable="true" id="assetReqFldGrp" titleCaptionId="ASSET_REQ">
 <!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="ASSET_REQ" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
  </TABLE>-->
  <TABLE class=tabtable>
  <TR>
    <TD class=fieldLabel width="25%"><hdiits:caption captionid="REQ_TYPE" bundle="${al}"/></TD>
   
	<TD class=fieldLabel width="25%">
 	<c:forEach var="RequesttypeListLoc" items="${RequesttypeList}"> 					
					<hdiits:radio name="Purpose"  value= "${RequesttypeListLoc.lookupName}" captionid="REQ_TYPE" errCaption="Request type"  mandatory="true" validation="sel.isradio" onclick="reqTypeFieldsDisplay()"/>
					<c:out value="${RequesttypeListLoc.lookupDesc}"/>
					</c:forEach>
					<script>
				
				document.form1.Purpose[0].checked = true;
				</script>
				
				</td>	 
	
	
  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="PUR_SOLD" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%"><hdiits:select name="purchasesale" sort="false" size="1" captionid="PUR_SOLD" bundle="${al}" tabindex="6" mandatory="true"  validation="sel.isrequired"  onchange="billdetails(this),transactiondetails(this,'${flag}','${saleAssetComboValue}')">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="Transaction_typeListLoc" items="${Transaction_typeList}">
		<hdiits:option value="${Transaction_typeListLoc.lookupName}">${Transaction_typeListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TR>
 </TABLE>


<c:if test="${flag == 'assetDeclaration'}">

				<script>
				document.form1.Purpose[0].disabled = true;
				document.form1.Purpose[1].disabled = true;
				</script>
				
</c:if>		
 
 	
 <TABLE class=tabtable style="DISPLAY: none" id="assetDetails">
  <TBODY>
  <TR bgColor=#386cb7>
    <TD class=fieldLabel align=middle colSpan=5><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    
    <TR>
	<td class=fieldLabel width="25%"><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}"/></td>
	<TD><hdiits:select  id="assettype"  name="assettype" sort="false" size="1" condition="valPurchseAsset()" captionid="MOV_OR_NOT" bundle="${al}" tabindex="4" mandatory="true" validation="sel.isrequired" onchange="typeasset(this,'purchaseSellDtls')">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="TypeofassetListLoc" items="${TypeofassetList}">
		<hdiits:option  value="${TypeofassetListLoc.lookupName}">${TypeofassetListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select></td>
	
	
	</TR>
 </table> 

 
    
<TABLE class=tabtable style="DISPLAY: none" id="movable">
  <tr>
 
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}"/></td>
	<td><hdiits:select name="property"  sort="false" size="1"  tabindex="7" mandatory="true" condition="valAssetMovable()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="sel.isrequired" onchange="selectno(this),selectother1(this)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="MovableListLoc" items="${MovableList}">
		<hdiits:option  value="${MovableListLoc.lookupName}">${MovableListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select></td>
	<TD class=fieldLabel id=Name_other1 style="DISPLAY: none" width="60%"><hdiits:text name="other_movable" mandatory="true" condition="valOtherMovableAsset()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="txt.isrequired,txt.isname" /></TD>
	
	<TD class=fieldLabel id=Registrationno style="DISPLAY: none" width="25%"><hdiits:caption captionid="REGI_NO" bundle="${al}"/></TD>
	<TD class=fieldLabel id=Registrationno1 style="DISPLAY: none" width="25%"><hdiits:text name="registration" id="registration" mandatory="true" condition="valRegNo()" captionid="REGI_NO" bundle="${al}" validation="txt.isrequired"/></TD>

	
  
</tr>
</TABLE>	

<TABLE class=tabtable style="DISPLAY: none" id="fixed">
   <tr>
	<TD class=fieldLabel width="25%" align="left"><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}"/></td>
	<td><hdiits:select name="asset" size="1" sort="false"  tabindex="4" mandatory="true" condition="valAssetFixed()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="sel.isrequired"  onchange="selectother(this),selectLandAsset(this)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	
	<c:forEach var="FixedListLoc" items="${FixedList}">
		<hdiits:option value="${FixedListLoc.lookupName}">${FixedListLoc.lookupDesc}</hdiits:option>
	</c:forEach>	
	</hdiits:select>
	</td>
	<TD class=fieldLabel id=Name_other style="DISPLAY: none" width="60%"><hdiits:text  name="other_fixed"  mandatory="true" condition="valOtherAsset()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="txt.isrequired,txt.isname" /></TD>
	</tr>
	<tr id="assetAreaTD" style="display:none">
	<TD class=fieldLabel style="" >
	
	<hdiits:caption captionid="ASSET_AREA" bundle="${al}"/>
	</td>
	<td>
	<hdiits:number name="asset_area" mandatory="true" condition="valAssetArea()" captionid="ASSET_AREA" bundle="${al}" validation="txt.isrequired"/>
	</TD>
	<TD class=fieldLabel id=surwayNoTD style="" >
	<hdiits:caption captionid="ASSET_SURWAY" bundle="${al}"/>
	</td>
	<td>
	<hdiits:text name="surway_no" mandatory="true" condition="valSurwayNo()" captionid="ASSET_SURWAY" bundle="${al}" validation="txt.isrequired" />
	</TD>
	
	</TR>
	</TABLE>
	
<TABLE class=tabtable style="DISPLAY: none" id="addressofasset">
	<TR><TD>
		<hdiits:fmtMessage key="ADD_OF_ASSET"  bundle="${al}" var="AssetAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="Address"/>
						<jsp:param name="addressTitle"  value="${AssetAddress}" />
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y" />						
	</jsp:include>	</TD>
  </TR>
</TABLE>
<div id="saleLable" style="display:none">
<hdiits:fieldGroup bundle="${al}" expandable="false" id="assetDtlsFldGrp" titleCaptionId="ASSET_DTLS">
<!--<TABLE class=tabtable style="DISPLAY: none" id="saleLable">
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="ASSET_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>-->
<TABLE class=tabtable style="DISPLAY: none" id="saleProperty">

<tr>
    <TD class=fieldLabel  width="25%"><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}"/></td>
    <TD class=fieldLabel  width="77%"><hdiits:select name="saleassetname"  sort="false" mandatory="true" condition="valAssetSellData()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="sel.isrequired"  onchange="selectPurchasedData(this,'${purchasedList}')">
   	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="resValue3" items="${saleAssetComboValue}">
		<hdiits:option value="${resValue3}">${resValue3}</hdiits:option>
	</c:forEach>
	</hdiits:select>
</tr>

</TABLE>

<TABLE class=tabtable style="DISPLAY: none" id="saleNoDtls">
<tr>
<td class=fieldLabel  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="ANY_PUR_ASSET_NOT_FOUND" bundle="${al}"></b></fmt:message></FONT></b></td>
</tr>
</TABLE>

<TABLE class=tabtable  style="DISPLAY: none" id="saleMovableDtls">
  <TR>
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="saleAssetType" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
    
    <TD class=fieldLabel  width="25%"><hdiits:caption captionid="SELL_REGI_NO" bundle="${al}"/></TD>
    <TD class=fieldLabel  width="25%"><hdiits:text name="sellRegistrationNo" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
	
  </TR>
</TABLE>

<TABLE class=tabtable  style="DISPLAY: none" id="saleFixedDtls">
  <TR>
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="saleFixedAssetType" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
    
    <TD class=fieldLabel width="25%"><hdiits:caption captionid="ASSET_ADDRESS" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%"><hdiits:textarea name="saleFixedAssetAddress" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
	
  </TR>
</TABLE>

<TABLE class=tabtable  style="DISPLAY: none" id="tranxnDtls">
  <tr>
 	<TD class=fieldLabel width="25%"><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="dateOfTanxn" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
    
    <TD class=fieldLabel width="25%"><hdiits:caption captionid="TRANSACTION_PRICE" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="tranxnPrice" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
	
  </tr>
</TABLE> 
</hdiits:fieldGroup>
</div>
</hdiits:fieldGroup>
<hdiits:fieldGroup bundle="${al}" expandable="true" id="tranDtlsFldGrp" titleCaptionId="TRAN_DTLS">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="TRAN_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>
--><TABLE class=tabtable  style="DISPLAY: none" id="purchase"  onkeypress="">  
	<tr>  
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:dateTime  name="date_of_trans" caption="dateTime1" maxvalue="31/12/9999" mandatory="true" condition="valDateOfTranxn()" captionid="DATE_OF_TRANS" bundle="${al}" validation="txt.isdt,txt.isrequired" ></hdiits:dateTime></td>      
	
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="APP_PRICE" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:number name="approxprice"  mandatory="true" maxlength="20" condition="valTranxnPrice()" captionid="APP_PRICE" bundle="${al}" validation="txt.isrequired" />
	<c:if test="${flag == 'assetDeclaration'}">
	<br><fmt:message key="MAX_LIMIT" bundle="${al}"></fmt:message>${maxPriceLimit})
	</c:if>
	</td>
  </TR>
  
  <TR>
	<TD class=fieldLabel sort="false" width="25%"><hdiits:caption captionid="PAYMENT_PLACE" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:select name="paymentplace" size="1" sort="false" tabindex="2" mandatory="true"   onfocus="" onchange="">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="ModeOfPaymentLoc" items="${ModeOfPaymentList}">
		<hdiits:option value="${ModeOfPaymentLoc.lookupName}">${ModeOfPaymentLoc.lookupDesc}</hdiits:option>
	</c:forEach>
    </hdiits:select></td>   
   <TD class=fieldLabel width="25%"><hdiits:caption captionid="PRICE_FROM_PERTICULAR_SRC" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:number name="PriceFromPerticularSrc"  maxlength="20"/>
 </TR>
 
    <tr>
    <TD><hdiits:caption captionid="SAVING_SRC" bundle="${al}"/></td>
	<td><hdiits:textarea name="savingsrc" maxlength="200" mandatory="true"  cols="25" rows="3"></hdiits:textarea>
	</td>
   </TR>    
</TABLE> 



<TABLE class=tabtable style="DISPLAY: none" id="addSrcButton">
<tr>
	<td align="center">
	<hdiits:button  type="button" name="Add"  captionid="ASSET_ADD" bundle="${al}" onclick="AddSrcForPayment()"/>
	
	</td></tr> 
</table>

<TABLE class=tabtable style="DISPLAY: none" id="updateSrcButton">
<tr>
	<td align="center">
	<hdiits:button  type="button" name="UpdateSrc"  captionid="ASSET_UPDATE" bundle="${al}" onclick="UpdateSrcForPayment()"/>
	
	</td></tr> 
</table>

<table id="addSrcOfPaymentTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<br></br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="20%"><b><hdiits:caption captionid="PAYMENT_PLACE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="20%"><b><hdiits:caption captionid="PRICE_FROM_PERTICULAR_SRC" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="40%"><b><hdiits:caption captionid="SAVING_SRC" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="ACTION" bundle="${al}" /></b></td>
</tr>
</table>
 
<!--  
<TABLE class=tabtable style="DISPLAY: none" id="saleTableLable">
	<TBODY>
	<TR>
	<TD class=fieldLabel align=left colSpan=5 ><b><fmt:message key="CHOOSE_ASSET" bundle="${al}"/></b> 
	</TD></TR>
	</TBODY>
</TABLE>
<table id="saleDataTable" name="saleDataTable"  align="center" border="1" style="display:none" width="100%">
			<tr>
				<td class="fieldLabel" width="3%"></td>
				<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
				<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}"/></b></td>
				<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
				<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="TRANSACTION_PRICE" bundle="${al}" /></b></td>
				<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
			</tr>
		</table>
-->



<TABLE class=tabtable  style="DISPLAY: none" id="sale">
  
  <TR>
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="SELL_DATE" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:dateTime  name="sellingdate" maxvalue="31/12/9999" mandatory="true" condition="valAssetsellDate()" captionid="SELL_DATE" bundle="${al}" validation="txt.isdt,txt.isrequired"></hdiits:dateTime></td>
	
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="SELL_PRICE" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:number name="sellingprice" mandatory="true" maxlength="20" condition="valAssetsellPrice()" captionid="SELL_PRICE" bundle="${al}" validation="txt.isrequired"/>
  	<c:if test="${flag == 'assetDeclaration'}">
	<br><fmt:message key="MAX_LIMIT" bundle="${al}"></fmt:message>${maxPriceLimit})
	</c:if>
	</td>
  </TR>
  
  <TR>
    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PERMISSION_TAKEN" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%"><hdiits:radio name="permissiontaken" value="Y" mandatory="true" condition="valSellPermissionTaken()" validation="sel.isradio" errCaption="Informed/taken permission at the time of purchase" captionid="YES" bundle="${al}"  onclick="permissionyes()"/>
    <hdiits:radio name="permissiontaken" value="N" mandatory="true" validation="sel.isradio" captionid="NO" bundle="${al}" onclick="permissionno()"/></TD>
    
    <TD class=fieldLabel width="25%" id="textarea" style="DISPLAY: none"><hdiits:caption captionid="RSN_PERMISSION" bundle="${al}"/></td>
	<td class=fieldLabel width="25%" id="textarea1" style="DISPLAY: none"><hdiits:textarea name="rsnpermission" mandatory="true" condition="valRsnForNoPermision()" captionid="RSN_PERMISSION" bundle="${al}" validation="txt.isrequired"></hdiits:textarea></td>
  </TR>
</TABLE> 

<!--  
<TABLE class=tabtable>
<tr>
	<TD class=fieldLabel width="25%" id="other" style="DISPLAY: none"><b><fmt:message key="SAVING_SRC" bundle="${al}"></b></fmt:message></td>
	<td id="other1" style="DISPLAY: none"><hdiits:textarea name="savingsrc1" mandatory="true" captionid="SAVING_SRC" bundle="${al}" ></hdiits:textarea>
	</td>
</TR>
</TABLE>    -->
<TABLE class=tabtable>
<tr>
    <TD class=fieldLabel sort="false" width="25%"><hdiits:caption captionid="PUR_SELL_RCV" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:select name="pursell" sort="false" size="1" captionid="PUR_SELL_RCV" bundle="${al}" tabindex="6" mandatory="true" validation="sel.isrequired"  onchange="selectpursell(this),selectothermode(this)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="ModeofpurchaseLoc" items="${ModeofpurchaseList}">
		<hdiits:option value="${ModeofpurchaseLoc.lookupName}">${ModeofpurchaseLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	<br>
	<hdiits:text name="other_mode" id="Name_other2" style="DISPLAY: none"  condition="valOtherModeOfPurchase()" captionid="PUR_SELL_RCV" bundle="${al}" validation="txt.isrequired,txt.isname"  /></td>
	
	<TD class=fieldLabel sort="false" width="25%"><hdiits:caption captionid="MEDIUM_OF_DEAL" bundle="${al}"/></td>
	<td class=fieldLabel width="25%"><hdiits:select name="mediumofdeal" sort="false" size="1" mandatory="true" captionid="MEDIUM_OF_DEAL" bundle="${al}" tabindex="6" validation="sel.isrequired"  onchange="selectothermedium(this)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="MediumofdealLoc" items="${MediumofdealList}">
		<hdiits:option value="${MediumofdealLoc.lookupName}">${MediumofdealLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	<br>
	<hdiits:text name="other_medium" id="Name_other3" style="DISPLAY: none"  condition="valOtherMediumOfDeal()" captionid="MEDIUM_OF_DEAL" bundle="${al}" validation="txt.isrequired,txt.isname"/></td>
	
	
    
</TR>
</TABLE>
<TABLE class=tabtable>
<tr>
	
    <TD class=fieldLabel width="25%" id="permission3" style="DISPLAY: none"><hdiits:caption captionid="PERMISSION_OBTAINED" bundle="${al}"/></TD>
    <TD id="permission4" style="DISPLAY: none"><hdiits:radio name="permissionobtained" value="Y" mandatory="true" condition="valGiftPermissionTaken()" validation="sel.isradio" errCaption="If the property is a gift then permission has been obtained under Guj. state service rules 471 & rule 13" captionid="YES" bundle="${al}" onclick="permissionobtainedyes()"/>
    <hdiits:radio name="permissionobtained" value="N" mandatory="true" validation="sel.isradio" captionid="NO" bundle="${al}" onclick="permissionobtainedno()"/></TD>
    
    <TD class=fieldLabel width="25%" id="permission1" style="DISPLAY: none"><hdiits:caption captionid="ASN_GIFT" bundle="${al}"/></td>
	<td class=fieldLabel width="25%" id="permission2" style="DISPLAY: none"><hdiits:textarea name="rsnpermissiongift" mandatory="true" condition="valRsnForNoGiftPermision()" captionid="ASN_GIFT" bundle="${al}" validation="txt.isrequired"></hdiits:textarea></td>
  
</TR>
</TABLE> 
 
<TABLE class=tabtable  style="DISPLAY: none" id="saleattachment">
  <TR>  
    <TD class=fieldLabel width="30%" id="attach" style="DISPLAY: none"><hdiits:caption captionid="ATTACH" bundle="${al}"/></td>
   </tr>
   <tr>	
	<td class=fieldLabel width="25%" id="attach1" style="DISPLAY: none" colspan="4">
		<table border='0' width="100%">
			<tr>
				<td>
					
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="AssetAttachment" />
						<jsp:param name="formName" value="form1" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />    
						</jsp:include>
					<!-- For attachment : End-->
				</td>
			</tr>
		</table>
	</td> 
	</tr>     
</TABLE>
<div id="billdtls" style="display:none">
<hdiits:fieldGroup bundle="${al}" expandable="false" id="billDtlsFldGrp" titleCaptionId="BILL_DTLS">
<!--<TABLE class=tabtable style="DISPLAY: none" id="billdtls">
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="BILL_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>

--><TABLE class=tabtable style="DISPLAY: none" id="billdetails">
  <TBODY>
  <TR>
  <TD class="fieldLabel" width="25%"><hdiits:caption captionid="BILL_NO" bundle="${al}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="billno" mandatory="true"  condition="valBillNo()" captionid="BILL_NO" bundle="${al}" validation="txt.isrequired"/></td>
	
 <TD class="fieldLabel" width="25%"><hdiits:caption captionid="BILL_DATE" bundle="${al}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime  name="billdate" caption="dateTime"  mandatory="true"  condition="valBillDate()" captionid="BILL_DATE" bundle="${al}" validation="txt.isdt,txt.isrequired" ></hdiits:dateTime></td>
  
  </TR>
  </TBODY>
</TABLE> 

<TABLE class="tabtable"  style="DISPLAY: none" id="billattachment">
  <TR>  
    <TD class="fieldLabel" width="30%" id="attachbill" style="DISPLAY: none"><hdiits:caption captionid="BILL_ATTACHMENT" bundle="${al}"/></td>
   </tr>
   <tr>	
	<td class="fieldLabel" width="25%" id="attachbill1" style="DISPLAY: none" colspan="4">
		<table border='0' width="100%">
			<tr>
				<td>
					
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="BillAttachment" />
						<jsp:param name="formName" value="form1" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />    
                        </jsp:include>
					<!-- For attachment : End-->
				</td>
			</tr>
		</table>
	</td> 
	</tr>     
</TABLE>
</hdiits:fieldGroup>
</div>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${al}" expandable="true" id="partyDtlsFldGrp" titleCaptionId="PARTY_DTLS">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="PARTY_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>

--><TABLE class=tabtable>
  <TBODY>
  <TR> 	
	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="TYPE_OF_PARTY" bundle="${al}"/></TD>
    <TD><hdiits:select name="TypeOfParty" sort="false" captionid="TYPE_OF_PARTY" bundle="${al}" mandatory="true" validation="sel.isrequired" onfocus="" onchange="partyname(this)">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="PartyTypeListLoc" items="${PartyTypeList}">
		<hdiits:option value="${PartyTypeListLoc.lookupName}">${PartyTypeListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
	 </TR>
  </TBODY>
</TABLE>

<TABLE class="tabtable" style="DISPLAY: none" id="partydetails">
  <TBODY>
  <TR>	
  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${al}"/></TD>
  	<TD><hdiits:radio name="relationemp"  value="Y" mandatory="true" condition="valPartyRelation()" validation="sel.isradio" errCaption="Any relation with employee" captionid="YES" bundle="${al}" onclick="relationwithemp()"/>
    <hdiits:radio name="relationemp" value="N" mandatory="true" validation="sel.isradio" captionid="NO" bundle="${al}" onclick="relationwithempno()"/></TD>
    
    <TD class="fieldLabel" width="25%" id="relation" style="DISPLAY: none"><hdiits:caption captionid="SPECIFY_DTLS" bundle="${al}"/></TD>
    <TD class="fieldLabel" width="25%" id="relation1" style="display:none"><hdiits:select name="specifydtls"  sort="false" captionid="SPECIFY_DTLS" bundle="${al}" mandatory="true" condition="valPartySpecificRelation()" validation="sel.isrequired"  onchange="findNameFromRelation(this,1,'${familyList}')">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="resValue1" items="${relationComboValue}">
		<hdiits:option value="${resValue1}">${resValue1}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
    
  </TR>
  
 </TBODY>
</TABLE> 
<TABLE class="tabtable" style="DISPLAY: none" id="NoFamilyDtl">
<c:if test="${NoFamilyDtls == 'NoRecordFound'}">
  <tr align="center">
  <td class="fieldLabel"  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <tr align="center">
  <td class="fieldLabel"  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="IF_FILL_FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <script>
  	document.form1.specifydtls.disabled=true;
  </script>
  </c:if>
</TABLE>    
<TABLE class="tabtable" style="DISPLAY: none" id="companydetails">
  <TBODY>
  <TR>
   	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="COMPANY_NAME" bundle="${al}"/></TD>
   	<td><hdiits:text name="companyname"  size="50%" mandatory="true" condition="valCompanyName()" captionid="COMPANY_NAME" bundle="${al}" validation="txt.isrequired" /></td>
  </TR>
  </TBODY>
</TABLE>

<TABLE class="tabtable">
  <TBODY> 
  <TR>
    <TD class="fieldLabel" width="25%"><b><hdiits:caption captionid="NAME_OF_PARTY" bundle="${al}"></hdiits:caption></b></TD>
    <TD class="fieldLabel" width="25%"><hdiits:text name="firstname"  captionid="NAME_OF_PARTY" bundle="${al}" mandatory="true" validation="txt.isrequired,txt.isname" onclick=""/><br><fmt:message key="FIRST_NAME"  bundle="${al}"></fmt:message></td>
    <TD class="fieldLabel" width="25%"><hdiits:text name="middlename" captionid="NAME_OF_PARTY" bundle="${al}" validation="txt.isname"/><br><fmt:message key="MIDDLE_NAME" bundle="${al}"></fmt:message></td>
    <TD class="fieldLabel" width="25%"><hdiits:text name="lastname"captionid="NAME_OF_PARTY" bundle="${al}"  validation="txt.isrequired,txt.isname" mandatory="true"  onclick=""/><br><fmt:message key="LAST_NAME" bundle="${al}"></fmt:message></td>
  </TR>
  </TBODY>
</TABLE> 
<table width="100%">
<tr>
  <td>
  		<hdiits:fmtMessage key="PARTY_ADDRESS"  bundle="${al}" var="PartyAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="GuardianAddress"/>
						<jsp:param name="addressTitle" value="${PartyAddress}"/>
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y"/>						
				</jsp:include></td>	
</tr>
</table>  
 
<TABLE class="tabtable" onclick="">  
  <TR>
    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PRO_RELATION_FUTURE" bundle="${al}"/></TD>
    <TD><hdiits:radio name="futurerelation" value="Y" mandatory="true" validation="sel.isradio" errCaption="Whether any professional relationship or any possibility of having such relationships in near future with the party"  captionid="YES" bundle="${al}"  onclick="futurerelationyes()"/>
    <hdiits:radio name="futurerelation" value="N" mandatory="true" validation="sel.isradio" captionid="NO" bundle="${al}" onclick="futurerelationno()"/>
    
    <TD class="fieldLabel" width="25%" id="workdetail" style="DISPLAY: none"><hdiits:caption captionid="PER_DTLS" bundle="${al}"/></td>
	<td class="fieldLabel" width="25%" id="workdetail1" style="DISPLAY: none"><hdiits:textarea name="personaldtls" mandatory="true" condition="valfuturerelation()" captionid="PER_DTLS" bundle="${al}" validation="txt.isrequired"></hdiits:textarea></TD>
  </TR>
</TABLE>  
<br>


<div id="ownerLable" style="display:none">
<hdiits:fieldGroup bundle="${al}" expandable="true" id="ownrDtlsFldGrp" titleCaptionId="OWNER_DTLS">
<!--<TABLE class=tabtable style="DISPLAY: none" id="ownerLable">
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="OWNER_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>-->
<TABLE class="tabtable" style="DISPLAY: none" id="typeOfOwner">
  <TBODY>
  <TR> 
  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="OWNER" bundle="${al}"/></TD>
    <TD><hdiits:select name="TypeOfOwner" sort="false" mandatory="true" condition="valOwnerDtls()" captionid="OWNER" bundle="${al}"  validation="sel.isrequired"  onchange="ownername(this)">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
    <c:forEach var="assetOwnerIdListLoc" items="${assetOwnerIdList}">
	<hdiits:option value="${assetOwnerIdListLoc.lookupName}">${assetOwnerIdListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
	 </TR>
  </TBODY>
</TABLE>
  
<TABLE class="tabtable" style="DISPLAY: none" id="ownerdetails">
  <TBODY>
  <TR>	
  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${al}"/></TD>
  	<TD><hdiits:radio name="relationempwithowner" value="Y" mandatory="true" condition="valOwnerName()" validation="sel.isradio" errCaption="Any relation with employee" captionid="YES" bundle="${al}" onclick="relationempwithowneryes()"/>
    <hdiits:radio name="relationempwithowner" value="N" mandatory="true" validation="sel.isradio" captionid="NO" bundle="${al}" onclick="relationempwithownerno()"/></TD>
    
    <TD class="fieldLabel" width="25%" id="ownerrelation" style="DISPLAY: none"><hdiits:caption captionid="SPECIFY_DTLS" bundle="${al}"/></TD>
    <TD class="fieldLabel" width="25%" id="ownerrelation1" style="display:none"><hdiits:select name="ownerrelation"  sort="false"  captionid="SPECIFY_DTLS" bundle="${al}" mandatory="true" condition="valOwnerSpecificRelation()" validation="sel.isrequired" onchange="findNameFromRelation(this,2,'${familyList}')">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="resValue1" items="${relationComboValue}">
		<hdiits:option value="${resValue1}">${resValue1}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
    
  </TR>
 </TBODY>
</TABLE> 
<TABLE class="tabtable" style="DISPLAY: none" id="NoFamilyDtlForOwner">
<c:if test="${NoFamilyDtls == 'NoRecordFound'}">
  <tr align="center">
  <td class="fieldLabel"  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <tr align="center">
  <td class="fieldLabel"  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="IF_FILL_FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <script>
  	document.form1.ownerrelation.disabled=true;
  </script>
  </c:if>
</TABLE>    

<TABLE class="tabtable" style="DISPLAY: none" id="ownerName">
  <TBODY> 
  <TR>
    <TD class="fieldLabel" width="25%"><b><hdiits:caption captionid="NAME_OF_OWNER" bundle="${al}"></hdiits:caption></b></TD>
    <TD class="fieldLabel" width="25%"><hdiits:text name="firstname1" captionid="NAME_OF_OWNER" bundle="${al}" mandatory="true" condition="valOwnerName()" validation="txt.isrequired,txt.isname" onclick=""/><br><fmt:message key="FIRST_NAME"  bundle="${al}"></fmt:message></td>
    <TD class="fieldLabel" width="25%"><hdiits:text name="middlename1" captionid="NAME_OF_OWNER" bundle="${al}" validation="txt.isname" onclick=""/><br><fmt:message key="MIDDLE_NAME" bundle="${al}"></fmt:message></td>
    <TD class="fieldLabel" width="25%"><hdiits:text name="lastname1" captionid="NAME_OF_OWNER" bundle="${al}" mandatory="true" condition="valOwnerName()" validation="txt.isrequired,txt.isname" onclick=""/><br><fmt:message key="LAST_NAME" bundle="${al}"></fmt:message></td>
  </TR>
  </TBODY>
</TABLE> 

<TABLE class=tabtable style="DISPLAY: none" id="OwnerAddress">
	<TR>
  <td>
  		<hdiits:fmtMessage key="OWNER_ADDRESS"  bundle="${al}" var="OwnerAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="OwnerJoiningAddress"/>
						<jsp:param name="addressTitle" value="${OwnerAddress}"/>
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y" />						
				</jsp:include>
				</td>	
  
 </TR>
</TABLE>  

</hdiits:fieldGroup>
</div>

<TABLE class="tabtable">

<tr>

	<TD class="fieldLabel" width="8%"><hdiits:caption captionid="REMARKS" bundle="${al}"/></td>
	<td  width="25%"><hdiits:textarea name="remarks" cols="50" rows="3"  onclick=""></hdiits:textarea></TD>
</tr> 
</table>
</hdiits:fieldGroup>
<TABLE class="tabtable" style="" id="submitClose">


<tr>
	<td align="center">
	<hdiits:button name="Submit" type="button" captionid="ASSET_SUBMIT" bundle="${al}" onclick="submitreq()"/>
	<hdiits:button type="button" name="Close"  captionid="ASSET_CLOSE" bundle="${al}" onclick="closewindow()"/>
	</td></tr> 
</table>


<!-- 
<c:if test="${flag == 'assetAdminScreen'}">

<TABLE class=tabtable style="" id="submitClose">
<br>
<tr>
	<td align="center">
	
	<hdiits:formSubmitButton name="Submit1" type="button" caption="Add" />
	</td></tr> 
</table>
<br>
<br>
<table id="adminAddTable" style="display:none" align="center" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" class="TableBorderLTRBN" width="95%"> 
<tr>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="PUR_SOLD" bundle="${al}" /></b></td>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}" /></b></td>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="TRANXN_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="TRANXN_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="ACTION" bundle="${al}" /></b></td>
</tr>
</table>  

</c:if>
 -->
</div>

<div id="tcontent2" class="tabcontent" tabno="1" style="">


<br>
<br>
<c:if test="${not empty totalEditData}">		
<table id="editDataTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 

<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="PUR_SOLD" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="TRANXN_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="TRANXN_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ACTION" bundle="${al}" /></b></td>
</tr>

<c:forEach items="${totalEditData}" var="totalEditData" varStatus="x">
<tr id='a${totalEditData.rowId}'>
<td><c:out value="${totalEditData.assetId}"/></td>
<td><c:out value="${totalEditData.transactionType}"/></td>
<td><c:out value="${totalEditData.assetType}"/></td>
<td><c:out value="${totalEditData.assetName}"/></td>
<td><c:out value="${totalEditData.assetAddress}"/></td>
<td><c:out value="${totalEditData.registrationNo}"/></td>
<td><c:out value="${totalEditData.transactionDate}"/></td>
<td><c:out value="${totalEditData.transactionPrice}"/></td>
<td><hdiits:a href="javascript:editRequestedData('${totalEditData.assetId}','a${totalEditData.rowId}')" bundle="${al}" captionid="EDIT"></hdiits:a> / <hdiits:a href="javascript:deleteRequestedData('${totalEditData.assetId}','a${totalEditData.rowId}')" bundle="${al}" captionid="DELETE"></hdiits:a></td>
</tr>

</c:forEach>
</table>  
</c:if>
<c:if test="${editStatus == 'NoEditRecord'}">
<table id="noEditDataTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="PUR_SOLD" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="TRANXN_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="TRANXN_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ACTION" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="9"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>
</c:if>
<br>
<br>
<br>

<TABLE class="tabtable" style="display:none" id="LineForEdit">
<TBODY>
  <TR bgColor=#386cb7>
    <TD class="fieldLabel" align="center" colSpan=5 width="100%"><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    <tr></tr>
	<tr></tr>
 </TBODY>
</TABLE>
<div id="LableForEdit" style="display:none">
<hdiits:fieldGroup bundle="${al}" expandable="true" id="asserReqEdtFldGrp" titleCaptionId="ASSET_REQ">
<!--<TABLE class="tabtable" style="display:none" id="LableForEdit">
	<TBODY>
	
	<TR bgColor=#386cb7>
	<TD class="fieldLabel" align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="ASSET_REQ" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>-->
<TABLE class="tabtable" style="display:none" id="DataForEdit">
<TR>   
<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="REQ_TYPE" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:text name="editRequestType"/></td>

<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="PUR_SOLD" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:text name="editTranxnType"/></td>

</TR>

<TR>
<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:text name="editAssetType"/></td>

<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:text name="editAssetName"/></td>
</TR>
<TR style="display:none" id="editLandFields">
<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="ASSET_AREA" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:number name="editAssetArea" mandatory="true"/></td>

<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="ASSET_SURWAY" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:text name="editSurwayNo" mandatory="true"/></td>
</TR>
<tr style="display:none" id="editRegNo">
<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="REGI_NO" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:text name="editRegiNo" mandatory="true"/></td>
</tr>
<c:if test=""></c:if>
</TABLE>
<TABLE class="tabtable" style="display:none" id="editAssetAddress" align="center">
<TR><TD>
			<hdiits:fmtMessage key="ADD_OF_ASSET"  bundle="${al}" var="EditAssetAddress" />
			<jsp:include page="/WEB-INF/jsp/common/address.jsp">
							<jsp:param name="addrName" value="AssetAddress"/>
							<jsp:param name="addressTitle" value="${EditAssetAddress}"/>
							<jsp:param name="addrLookupName" value="Permanent Address"/>
							<jsp:param name="mandatory" value="Y" />						
		</jsp:include>	
		
			</TD>
	  </TR>

</table>
</hdiits:fieldGroup>
</div>
<div id="tranxnLable" style="display:none">
<hdiits:fieldGroup bundle="${al}" id="tranDtlEdtFldGrp" expandable="true" titleCaptionId="TRAN_DTLS">
<!--<TABLE class="tabtable"  style="display:none" id="tranxnLable">
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="TRAN_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>
--><TABLE class="tabtable" style="display:none" id="TranxnDataForEdit">
<TR>   
<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TRANXN_DATE" bundle="${al}"/></b><br><fmt:message key="DATE_FORMATE" bundle="${al}"></fmt:message></td>
<td  width="25%"><hdiits:dateTime name="editTranxnDate" mandatory="true" captionid="TRANXN_DATE" bundle="${al}" validation="txt.isdt"/></td>

<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TRANXN_PRICE" bundle="${al}"/></b></td>
<td  width="25%"><hdiits:number name="editTranxnPrice" mandatory="true"/></td>
</TR>
</TABLE>
</hdiits:fieldGroup>
</div>
<div id="editPartyLable" style="display:none">
<hdiits:fieldGroup bundle="${al}" expandable="true" id="partyDtlFldGrp" titleCaptionId="PARTY_DTLS">
<!--<TABLE class="tabtable" style="display:none" id="editPartyLable">
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="PARTY_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>



-->
<TABLE class="tabtable" style="display:none" id="changePartyDtls">
<tr>
<TD width="25%"><hdiits:caption captionid="CHNGE_PARTY_DTLS" bundle="${al}"/></TD>
  	<TD width="40%"><hdiits:radio name="changePartyAdd" value="Y" captionid="YES" bundle="${al}" mandatory="true" onclick="changePartyDtlsYes()"/>
    <hdiits:radio name="changePartyAdd" value="N" mandatory="true" bundle="${al}" captionid="NO" onclick="changePartyDtlsNo()"/></TD>
    <td width="*" ></td>
</tr>
</TABLE>

<TABLE class="tabtable" style="display:none" id="changeTypeOfParty">
  <TBODY> 
  <tr>
  <TD class="fieldLabel" width="25%"><hdiits:caption captionid="TYPE_OF_PARTY" bundle="${al}"/></TD>
    <TD><hdiits:select name="editTypeOfParty" sort="false" mandatory="true"  onchange="editPartyname(this)">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="PartyTypeListLoc" items="${PartyTypeList}">
		<hdiits:option value="${PartyTypeListLoc.lookupName}">${PartyTypeListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
	</tr>
</table>
<TABLE class="tabtable" style="display:none" id="changePartydetails">
  
  <TR>	
  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${al}"/></TD>
  	<TD><hdiits:radio name="editRelationemp2"  value="Y" mandatory="true" captionid="YES" bundle="${al}"  onclick="editRelationwithemp()"/>
    <hdiits:radio name="editRelationemp2"  value="N" mandatory="true" captionid="NO" bundle="${al}" onclick="editRelationwithempno()"/></TD>
    
    <TD class="fieldLabel" width="25%" id="relation4" style="display:none"><hdiits:caption captionid="SPECIFY_DTLS" bundle="${al}"/></TD>
    <TD class="fieldLabel" width="25%" style="display:none" id="relation5"><hdiits:select name="editSpecifydtls"   mandatory="true" sort="false" onchange="findNameFromRelation(this,3,'${familyList}')">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="resValue1" items="${relationComboValue}">
		<hdiits:option value="${resValue1}">${resValue1}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
    
  </TR>
 
</TABLE> 
<!--  
<TABLE class=tabtable style="DISPLAY: none" id="EditNoFamilyDtl">
<c:if test="${NoFamilyDtls == 'NoRecordFound'}">
  <tr align="center">
  <td class=fieldLabel  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <tr align="center">
  <td class=fieldLabel  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="IF_FILL_FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <script>
  	document.form1.editSpecifydtls.disabled=true;
  </script>
  </c:if>
</TABLE>    -->
<TABLE class=tabtable style="display:none" id="changeCompanydetails">
  <TBODY>
  <TR>
   	<TD class=fieldLabel width="25%"><hdiits:caption captionid="COMPANY_NAME" bundle="${al}"/></TD>
   	<td><hdiits:text name="editCompanyname1" size="50%" mandatory="true"  /></td>
  </TR>
  </TBODY>
</TABLE>
<TABLE class=tabtable style="display:none" id="changePartyName">
  <TBODY> 
  <TR>
    <TD class=fieldLabel width="25%"><b><hdiits:caption captionid="NAME_OF_PARTY" bundle="${al}"></hdiits:caption></b></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="editfirstname" mandatory="true" onclick=""/><br><fmt:message key="FIRST_NAME"  bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="editmiddlename" onclick=""/><br><fmt:message key="MIDDLE_NAME" bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="editlastname" mandatory="true" onclick=""/><br><fmt:message key="LAST_NAME" bundle="${al}"></fmt:message></td>
  </TR>
  </TBODY>
</TABLE> 
<table class=tabtable style="display:none" id="changePartyAddress" align="center">
<tr>
  <td>
  		<hdiits:fmtMessage key="PARTY_ADDRESS"  bundle="${al}" var="EditPartyAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="EditPartyAddress"/>
						<jsp:param name="addressTitle" value="${EditPartyAddress}"/>
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y"/>						
				</jsp:include></td>	</tr>
  </table>
</hdiits:fieldGroup>
</div>
<div id="editOwnerLable" style="display:none">
<hdiits:fieldGroup bundle="${al}" expandable="true" id="ownrDtlFlgGrp" titleCaptionId="OWNER_DTLS">
<!--<TABLE class=tabtable style="display:none" id="editOwnerLable">
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="OWNER_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>  
  

--><TABLE class=tabtable style="display:none" id="changeOwnerDtls">
<tr>
<TD class=fieldLabel width="25%"><hdiits:caption captionid="CHANGE_OWNER_DTLS" bundle="${al}"/></TD>
  	<TD><hdiits:radio name="changeOwnerAdd" value="Y" mandatory="true" captionid="YES" bundle="${al}" onclick="changeOwnerDtlsYes()"/>
    <hdiits:radio name="changeOwnerAdd" value="N" mandatory="true" captionid="NO" bundle="${al}" onclick="changeOwnerDtlsNo()"/></b></TD>
</tr>
</TABLE> 
  
<TABLE class=tabtable style="display:none" id="editTypeOfOwner">
  <TBODY>
  <TR> 
  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="OWNER" bundle="${al}"/></TD>
    <TD><hdiits:select name="editAssetTypeOfOwner" sort="false" mandatory="true" onchange="editOwnername(this)">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
    <c:forEach var="assetOwnerIdListLoc" items="${assetOwnerIdList}">
	<hdiits:option value="${assetOwnerIdListLoc.lookupName}">${assetOwnerIdListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
	 </TR>
  </TBODY>
</TABLE>


 
<TABLE class=tabtable style="display:none" id="editownerdetails">
  <TBODY>
  <TR>	
  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${al}"/></TD>
  	<TD><hdiits:radio name="editrelationempwithowner2" value="Y" mandatory="true" captionid="YES" bundle="${al}" onclick="editRelationempwithowneryes()"  />
    <hdiits:radio name="editrelationempwithowner2" value="N" mandatory="true" captionid="NO" bundle="${al}" onclick="editRelationempwithownerno()" /></TD>
    
    <TD class=fieldLabel width="25%" id="ownerrelation4" style="DISPLAY: none"><hdiits:caption captionid="SPECIFY_DTLS" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%" style="DISPLAY: none" id="ownerrelation5"><hdiits:select name="editownerrelation2"  sort="false" mandatory="true"  captionid="SPECIFY_DTLS" bundle="${al}" onchange="findNameFromRelation(this,4,'${familyList}')" >
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="resValue1" items="${relationComboValue}">
		<hdiits:option value="${resValue1}">${resValue1}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
    
  </TR>
 </TBODY>
</TABLE> 
<!--  
<TABLE class=tabtable style="DISPLAY: none" id="EditNoFamilyOwnerDtl">
<c:if test="${NoFamilyDtls == 'NoRecordFound'}">
  <tr align="center">
  <td class=fieldLabel  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <tr align="center">
  <td class=fieldLabel  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="IF_FILL_FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <script>
  	document.form1.editownerrelation2.disabled=true;
  </script>
  </c:if>
</TABLE>   -->

<TABLE class=tabtable style="display:none" id="chngeownerName">
  <TBODY> 
  <TR>
    <TD class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_OWNER" bundle="${al}"></hdiits:caption></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="editFirstname1" mandatory="true" /><br><fmt:message key="FIRST_NAME"  bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="editMiddlename1" onclick=""/><br><fmt:message key="MIDDLE_NAME" bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="editLastname1" mandatory="true"  onclick=""/><br><fmt:message key="LAST_NAME" bundle="${al}"></fmt:message></td>
  </TR>
  </TBODY>
</TABLE> 

<TABLE class=tabtable style="display:none" id="chngeOwnerAddress">
	<TR>
  <td>
  		<hdiits:fmtMessage key="OWNER_ADDRESS"  bundle="${al}" var="EditOwnerAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="changedOwnerAddress"/>
						<jsp:param name="addressTitle" value="${EditOwnerAddress}"/>
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y" />						
				</jsp:include>
				</td>	
  
 </TR>
</TABLE>  
</hdiits:fieldGroup>  
</div> 
<TABLE class=tabtable style="display:none" id="saveEditData">
<br>
<tr>
	<td align="center">
	<hdiits:button name="Update"  type="button" captionid="ASSET_UPDATE" bundle="${al}"  onclick="updatePermissionEditData()"/>
	<hdiits:button type="button" name="Close1"  captionid="ASSET_CLOSE" bundle="${al}" onclick="closewindow()"/>
	
	</td></tr> 
</table>
</div>
<hdiits:hidden name="assetId" id="assetId"/>
<hdiits:hidden name="editAssetId" id="editAssetId"/>
<hdiits:hidden name="applicationFlag" id="applicationFlag"/>
<hdiits:hidden name="beHalfUserId" id="beHalfUserId" default="${beHalfUserId}"/>
<hdiits:hidden name="editTranxnSrNo" id="editTranxnSrNo"/>
<hdiits:hidden name="editPermissionSrNo" id="editPermissionSrNo"/>
<hdiits:hidden name="editPartyAddressId" id="editPartyAddressId"/>
<hdiits:hidden name="editAssetAddressId" id="editAssetAddressId"/>
<hdiits:hidden name="editOwnerSrNo" id="editOwnerSrNo"/>
<hdiits:hidden name="editOwnerAddressId" id="editOwnerAddressId"/>
<hdiits:hidden name="tranxnTypeForEditOwnerDtls" id="tranxnTypeForEditOwnerDtls"/>
<hdiits:hidden name="editPermissionTableRowId" id="editPermissionTableRowId"/>
<hdiits:hidden name="Add" id="Add"/>

		<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
		
		<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />	

</hdiits:form>


<%}catch(Exception e)
  {
  e.printStackTrace();
  }
%>
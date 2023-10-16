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
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetValidation.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetAdminScreen.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetIncomeDeclaration.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="systemYear" value="${resultValue.systemYear}"></c:set>	
<c:set var="thirdPrvYear" value="${resultValue.thirdPrvYear}"></c:set>	
<c:set var="secondPrvYear" value="${resultValue.secondPrvYear}"></c:set>	
<c:set var="firstPrvYear" value="${resultValue.firstPrvYear}"></c:set>	
<c:set var="assetPurchaseDataForIncome" value="${resultValue.assetPurchaseDataForIncome}"></c:set>	
<c:set var="assetSellDataForIncome" value="${resultValue.assetSellDataForIncome}"></c:set>			
<c:set var="editStatus" value="${resultValue.editStatus}"></c:set>		
<c:set var="assetDataForIncomeAccordingToYear" value="${resultValue.assetDataForIncomeAccordingToYear}"></c:set>
<c:set var="statusForUser" value="${resultValue.statusForUser}"></c:set>
<script language="javascript">

function firstPrvYearsData(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	
	if(id == "V")
	{
		var firstPrvYear = "${firstPrvYear}";
		//alert(firstPrvYear);
		document.form1.firstPrvYear.value = "^";
		document.form1.hiddenFirstPrvYear.value = firstPrvYear;
		getAllDataAccordingToYear(firstPrvYear);
	}
	if(id == "^")
	{ 
		document.getElementById('firstPrvYearDataTable').style.display="none";
		document.getElementById('noRecordForFirstPrvYear').style.display="none";
		document.form1.firstPrvYear.value = "V";
		document.form1.hiddenFirstPrvYear.value = firstPrvYear;
	}
}

function secondPrvYearsData(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	
	if(id == "V")
	{
		var secondPrvYear = "${secondPrvYear}";
		document.form1.secondPrvYear.value = "^";
		document.form1.hiddenSecondPrvYear.value = secondPrvYear;
		getAllDataAccordingToYear(secondPrvYear);
	}
	if(id == "^")
	{
		document.getElementById('secondPrvYearDataTable').style.display="none";
		document.getElementById('noRecordForSecondPrvYear').style.display="none";
		document.form1.secondPrvYear.value = "V";
		document.form1.hiddenSecondPrvYear.value = secondPrvYear;
		
	}
}

function thirdPrvYearsData(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	
	if(id == "V")
	{
		var thirdPrvYear = "${thirdPrvYear}";
		document.form1.thirdPrvYear.value = "^";
		document.form1.hiddenThirdPrvYear.value = thirdPrvYear;
		getAllDataAccordingToYear(thirdPrvYear);
	}
	if(id == "^")
	{
		document.getElementById('thirdPrvYearDataTable').style.display="none";
		document.getElementById('noRecordForThirdPrvYear').style.display="none";
		document.form1.thirdPrvYear.value = "V";
		document.form1.hiddenThirdPrvYear.value = thirdPrvYear;
	}
}

function addSelectedIncomeData()
{
document.form1.declaredYear.value = "${systemYear}";
var stId1;
	var num = document.getElementById('incomeDataTable').rows.length;
	
	if(num == 1)
	{
		alert("No records to ADD");
		return;
	}
	var no = 0;
    var radio=document.forms[0].radioChoose;
    if(num > 0)
    {
		for(var i=0;i<radio.length;i++)
		{
			if(radio[i].checked == false)
				{
					no = no+1;
				}
						
		}
	}
	
	/*if(no == (num-1))
	{
		alert('<fmt:message  bundle="${as1}" key="ASSET.CHOOSE_RECORD"/>');
		return;
	}*/
	//alert(document.form1.checkRadio1.value);
	if(document.form1.checkRadio1.value == '')
	{	
		
		alert('<fmt:message  bundle="${as1}" key="ASSET.CHOOSE_RECORD"/>');
		return;
	} 
    else 
    {   
	/*	var radio=document.forms[0].radioChoose;
		//alert(radio);
		//alert(radio.length);
		for(var i=0;i<radio.length;i++)
		{
			if(radio[i].checked)
			{
				stId1=radio[i].value;
				//alert(stId1);
			}
		}*/
	//	var data=stId1.split("/");
		stId1 = document.form1.checkRadio1.value;
		var data=stId1.split("/");
		document.form1.assetRowId.value = data[0];
		document.form1.assetId.value = data[1];
		document.form1.assetType.value = data[2];
		document.form1.assetName.value = data[3];
		document.form1.ownerName.value = data[4];
		document.form1.assetAddress.value = data[5];
		document.form1.tranxnDate.value = data[6];
		document.form1.tranxnPrice.value = data[7];
	
	}
startProcess();
window.setTimeout('submitForm_Submit("form1")',1000);

}
function submitForm_Submit(formNameValue,data)
{
	var validData = validateForm_form1();
	if( validData==true )
	{
		document.form1.checkRadio1.value = '';
		//window.document.forms[formNameValue].submit();
		addAssetDataForIncome();
		
	}

endProcess();
}

function submitIncomeDtls()
{

	if(document.form1.marketPrice.value != "" || document.form1.annualIncome.value != "")
	{
		alert('<fmt:message  bundle="${as1}" key="ASSET.PLEASE_ADD_RECORD"/>');
		return;
	}
	var num = document.getElementById('incomeDataTable').rows.length;
	if(num == 1)
	{
		if(document.form1.annualIncomeOfEmployee.value =="" || document.form1.annualIncomeOfEmployee.value == null)
		{
			alert('<fmt:message  bundle="${as1}" key="ASSET.ANNUAL_INCOME_OF_EMPLOYEE"/>');
			document.form1.annualIncomeOfEmployee.focus();
			return;
		}
	
		var agree=confirm('<fmt:message  bundle="${as1}" key="ASSET.DO_WANT_SUBMIT"/>');
     	if (agree)
     	{
			var urlstyle="hdiits.htm?actionFlag=insertAssetIncomeDeclarationDtls";
			document.form1.action=urlstyle;
			document.form1.submit();
		}	
	}
	else
	{
		alert('<fmt:message  bundle="${as1}" key="ASSET.INCOME_ALL_DATA_CHECK"/>');
	}
}

function checkRadioForValidation(l)
{
	var id = l.value;
	document.form1.checkRadio1.value = id;
	//alert("onclick"+document.form1.checkRadio1.value);
}
function displayStatus(status)
{
	if(status == 'N'){
		status= '<fmt:message key="PENDING" bundle="${al}"/>';
	}
	else if(status == 'A'){
		status= '<fmt:message key="APPROVED" bundle="${al}"/>';
	}
	else if(status == 'R'){
		status= '<fmt:message key="REJECTED" bundle="${al}"/>';
	}
	return status;
}
</script>

<hdiits:form name="form1" validate="true" method="post" encType="multipart/form-data"  action=""> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="INCOME_DECL" bundle="${al}"/>${systemYear}</b></a></li>
	<li><a href="#" rel="tcontent2"><b><fmt:message key="PREV_YEAR_DECL" bundle="${al}"/></b></a></li>
	</ul>
</div>
<div id="tcontent1" class="tabcontent" tabno="0">



<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>




<br>
<hdiits:fieldGroup bundle="${al}" expandable="false" id="incmDclrnFldGrp" titleCaptionId="INCOME_DECL_SYSTEMYEAR">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="INCOME_DECL_SYSTEMYEAR" bundle="${al}"/> ${systemYear}<fmt:message key="SPACE" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>

--><br>
<c:if test="${editStatus == 'RecordFound'}">	
<c:if test="${statusForUser == 'NotDeclared'}">		
<table class=tabtable>
<tr>
<TD class=fieldLabel width="30%"><hdiits:caption captionid="ANNUAL_INCOME_OF_EMPLOYEE" bundle="${al}"/></td>
<td  width="69%"><hdiits:number name="annualIncomeOfEmployee" maxlength="20" size="35%" mandatory="true"/></td>
</tr>
</table>

<table id="incomeDataTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SELECT" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="14%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="14%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="28%"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="14%"><b><hdiits:caption captionid="OWNER" bundle="${al}" /></b></td>
</tr>	

<c:forEach items="${assetPurchaseDataForIncome}" var="assetPurchaseDataForIncome" varStatus="x">
<tr id='a${assetPurchaseDataForIncome.rowId}'>
<td>${assetPurchaseDataForIncome.rowId}<hdiits:radio name="radioChoose" value="a${assetPurchaseDataForIncome.rowId}/${assetPurchaseDataForIncome.assetId}/${assetPurchaseDataForIncome.assetType}/${assetPurchaseDataForIncome.assetName}/${assetPurchaseDataForIncome.firstName}/${assetPurchaseDataForIncome.assetAddress}/${assetPurchaseDataForIncome.transactionDate}/${assetPurchaseDataForIncome.transactionPrice}" onclick="checkRadioForValidation(this)"/></td>
<td><c:out value="${assetPurchaseDataForIncome.assetId}"/></td>
<td><c:out value="${assetPurchaseDataForIncome.assetName}"/></td>
<td width="20%"><c:out value="${assetPurchaseDataForIncome.assetAddress}"/></td>
<td><c:out value="${assetPurchaseDataForIncome.transactionDate}"/></td>
<td><c:out value="${assetPurchaseDataForIncome.transactionPrice}"/></td>
<td><c:out value="${assetPurchaseDataForIncome.firstName}"/></td>
</tr>
</c:forEach>

<c:forEach items="${assetSellDataForIncome}" var="assetSellDataForIncome" varStatus="x">
<tr id='a${assetSellDataForIncome.rowId}'>
<td>${assetSellDataForIncome.rowId}<hdiits:radio name="radioChoose" value="a${assetSellDataForIncome.rowId}/${assetSellDataForIncome.assetId}/${assetSellDataForIncome.assetType}/${assetSellDataForIncome.assetName}/${assetSellDataForIncome.firstName}/${assetPurchaseDataForIncome.assetAddress}/${assetPurchaseDataForIncome.transactionDate}/${assetPurchaseDataForIncome.transactionPrice}" onclick="checkRadioForValidation(this)"/></td>
<td><c:out value="${assetSellDataForIncome.assetId}"/></td>
<td><c:out value="${assetSellDataForIncome.assetName}"/></td>
<td width="20%"><c:out value="${assetSellDataForIncome.assetAddress}"/></td>
<td><c:out value="${assetSellDataForIncome.transactionDate}"/></td>
<td><c:out value="${assetSellDataForIncome.transactionPrice}"/></td>
<td><c:out value="${assetSellDataForIncome.firstName}"/></td>
</tr>
</c:forEach>
</table>

<table class=tabtable style="" id="incomeData">
<br>
<tr>
	<TD class=fieldLabel width="30%"><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}"/></td>
	<td><hdiits:number name="marketPrice"  mandatory="true" maxlength="20" size="35%" captionid="APP_MARKET_PRICE" bundle="${al}" validation="txt.isrequired" /></td>
</tr>

<tr>
	<TD class=fieldLabel width="30%"><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}"/></td>
	<td><hdiits:number name="annualIncome" maxlength="20" size="35%" mandatory="true"  captionid="ANNUAL_INCOME" bundle="${al}" validation="txt.isrequired" /></td>
</tr>
<tr>

	<TD class=fieldLabel width="8%"><hdiits:caption captionid="REMARKS" bundle="${al}"/></td>
	<td><hdiits:textarea name="remarks" cols="50" rows="3" ></hdiits:textarea></TD>
</tr> 
</table>

<br>
<TABLE class=tabtable style="" id="incomeAddButton">
<tr>
	<td align="center">
	<hdiits:button  type="button" name="Add" captionid="ASSET_ADD" bundle="${al}" onclick="addSelectedIncomeData()"  />
	
	</td></tr> 
</table>

<TABLE class=tabtable style="display:none" id="incomeUpdateButton">
<br>
<tr>
	<td align="center">
	<hdiits:button name="Update"  type="button" captionid="ASSET_UPDATE" bundle="${al}"  onclick="updateIncomeData()"/>
	
	</td></tr> 
</table>
<br>
<table id="selectedIncomeDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="OWNER" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="20%"><b><hdiits:caption captionid="REMARKS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="ACTION" bundle="${al}" /></b></td>
</tr>
</table>
<br>
<TABLE class=tabtable style="display:none" id="incomeSubmitClose">
<tr>
	<td align="center">
	<hdiits:button name="Submit" type="button" captionid="ASSET_SUBMIT" bundle="${al}" onclick="submitIncomeDtls()"/>
	<hdiits:button type="button" name="Close"  captionid="ASSET_CLOSE" bundle="${al}" onclick="closewindow()"/>
	</td></tr> 
</table>
</c:if>
</c:if>

<c:if test="${statusForUser == 'NotDeclared'}">
<c:if test="${editStatus == 'NoRecordFound'}">
<table id="" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="OWNER" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="7"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>
</c:if>
</c:if>
<c:if test="${statusForUser == 'AllReadyDeclared'}">
<table align="center">
	<TBODY>
	 <TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    <tr><TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_INCOME_REQ" bundle="${al}"/> ${systemYear} <fmt:message key="SUBMITTED_ALLREADY" bundle="${al}"/></b></TD></tr>
    <TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
   </TBODY>
</table> 	
</c:if>
</hdiits:fieldGroup>
</div>

<div id="tcontent2" class="tabcontent" tabno="1">
<hdiits:fieldGroup bundle="${al}" expandable="false" id="prvYrFldGrp" titleCaptionId="PREV_YEAR_DECL">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="PREV_YEAR_DECL" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>
--><table class=tabtable >
<tr>
<td align="left" width="20%"><hdiits:button  type="button" name="thirdPrvYear" value="V" onclick="thirdPrvYearsData(this)" />
<b><fmt:message key="YEAR_DECL" bundle="${al}"/> ${thirdPrvYear}</b></td>
</tr> 
</table>
<br>
<table id="thirdPrvYearDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
</table>

<table id="noRecordForThirdPrvYear" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="8"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>

<table class=tabtable >
<tr>
<td align="left" width="20%"><hdiits:button  type="button" name="secondPrvYear" value="V" onclick="secondPrvYearsData(this)" />
<b><fmt:message key="YEAR_DECL" bundle="${al}"/> ${secondPrvYear}</b></td>
</tr> 
</table>

<br>
<table id="secondPrvYearDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
</table>

<table id="noRecordForSecondPrvYear" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="8"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>

<table class=tabtable >
<tr>
<td align="left" width="20%"><hdiits:button  type="button" name="firstPrvYear" value="V" onclick="firstPrvYearsData(this)" />
<b><fmt:message key="YEAR_DECL" bundle="${al}"/> ${firstPrvYear}</b></td>
</tr> 
</table>

<br>
<table id="firstPrvYearDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
</table>
<table id="noRecordForFirstPrvYear" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="16%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="8"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>
</hdiits:fieldGroup>
</div>
<hdiits:hidden name="assetRowId" id="assetRowId"/>
<hdiits:hidden name="assetId" id="assetId"/>
<hdiits:hidden name="assetType" id="assetType"/>
<hdiits:hidden name="assetName" id="assetName"/>
<hdiits:hidden name="ownerName" id="ownerName"/>
<hdiits:hidden name="assetAddress" id="assetAddress"/>
<hdiits:hidden name="tranxnDate" id="tranxnDate"/>
<hdiits:hidden name="tranxnPrice" id="tranxnPrice"/>
<hdiits:hidden name="declaredYear" id="declaredYear"/>
<hdiits:hidden name="hiddenFirstPrvYear" id="hiddenFirstPrvYear"/>
<hdiits:hidden name="hiddenSecondPrvYear" id="hiddenSecondPrvYear"/>
<hdiits:hidden name="hiddenThirdPrvYear" id="hiddenThirdPrvYear"/>
<hdiits:hidden name="checkRadio1" id="checkRadio1"/>


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
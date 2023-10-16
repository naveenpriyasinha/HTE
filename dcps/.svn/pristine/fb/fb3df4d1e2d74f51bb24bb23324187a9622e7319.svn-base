<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<script>
function loadPasBranchList()
{
	
	document.frmBankBranchMpg.action = "ifms.htm?actionFlag=getPasBranchListFromBankCode&bankCode="+document.getElementById("cmbPasBankName").value;
	document.frmBankBranchMpg.submit();
	
		
}
function IsEmptyFun(varStr,alrtStr)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element.length == 0 || element == "-1" || element == "0.00")
	{
		alert(alrtStr);
		document.getElementById(varStr).focus();
		return false;
	}
	return true;
}
function validateBankBranchDtls()
{
	
	if(IsEmptyFun("cmbPasBankName", 'Please Select PAS Bank Name.')==false)
	{
		document.getElementById("cmbPasBankName").focus();
		return false;
	}
	if(IsEmptyFun("cmbDistrict", 'Please Select District.')==false)
	{
		document.getElementById("cmbDistrict").focus();
		return false;
	}
	if(IsEmptyFun("cmbBankName", 'Please Select Bank.')==false)
	{
		document.getElementById("cmbBankName").focus();
		return false;
	}
	if(IsEmptyFun("cmbBranchName", 'Please Select Branch.')==false)
	{
		document.getElementById("cmbBranchName").focus();
		return false;
	}
	return true;
}
function mapBankBranch(object)
{
	if(validateBankBranchDtls())
	{
		var elementId=object.id;
		var rowId = elementId.substring(6);
		var ifmsBankCode = document.getElementById("cmbBankName").value;
		var ifmsBranchCode = document.getElementById("cmbBranchName").value;
		var pasBankCode = document.getElementById("cmbPasBankName").value;
		var pasBranchCode = document.getElementById("hdnPasBranchCode"+rowId).value;
		var url="";
		url='ifms.htm?actionFlag=mapBankBranch&ifmsBankCode='+ifmsBankCode+'&ifmsBranchCode='+ifmsBranchCode+'&pasBankCode='+pasBankCode+'&pasBranchCode='+pasBranchCode+'&rowId='+rowId;
		
		if(document.getElementById("chkMapBank").checked)
		{
		   url=url+"&bankMpgFlag=Y";	
		}
		else
		{
			url=url+"&bankMpgFlag=";
		}
		
		mapBankBranchUsingAjax(url);
	}
}

function mapBankBranchUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
					mapBankBranchOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );

}
function mapBankBranchOnStateChanged(myAjax)
{
	   var XMLDoc =  myAjax.responseXML.documentElement;
	   
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	   alert(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue);
	   var rowId = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	   document.getElementById("lblBranchName"+rowId).innerHTML=document.getElementById("cmbBranchName").options[document.getElementById("cmbBranchName").selectedIndex].text;
	 
}
function resetMapBank()
{
	document.getElementById("chkMapBank").checked=false;
}
function checkMapBank()
{
	if(document.getElementById("cmbPasBankName").value != '-1')
	{
		document.getElementById("chkMapBank").checked=true;
	}
}
function getBankBranchFrmBankCode(object)
{
	var elementId=object.id;
	var districtName = document.getElementById("cmbDistrict").value;
	var bankCode=document.getElementById("cmbBankName").value;

	uri="ifms.htm?actionFlag=getBranchsFromBankCode";
	if(document.getElementById(elementId).value != "-1")
	{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&districtName="+districtName+"&bankCode="+bankCode,  
		        onSuccess: function(myAjax) {
		        	getBranchDtlsFromAjax(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
	}
	
}
function getBranchDtlsFromAjax(myAjax){
	
	XMLDoc =  myAjax.responseXML.documentElement;
	
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	XMLDoc = myAjax.responseXML.documentElement;
	
	document.getElementById("cmbBranchName").innerHTML = "";
	
	 var XmlHiddenValuesCode = XMLDoc.getElementsByTagName('BranchCode');
	 var XmlHiddenValuesName = XMLDoc.getElementsByTagName('BranchName');
	 var XmlHiddenValuesAddr = XMLDoc.getElementsByTagName('BranchAddrMicrCode');
	 
	 	if(XmlHiddenValuesCode.length >0)
	 	{
	 		var theOption = new Option;
   			theOption.value = "-1";
   			theOption.text ="--Select--";
   			document.getElementById("cmbBranchName").options[0]=theOption;
	 		document.getElementById("cmbBranchName").options[0].selected="selected";
	 		
	 		for( var i=0; i<XmlHiddenValuesCode.length ;i++) {
	 			theOption = new Option;
	 			theOption.value = XmlHiddenValuesCode[i].childNodes[0].nodeValue;
	 			theOption.text = XmlHiddenValuesName[i].childNodes[0].nodeValue;
	 			theOption.title = XmlHiddenValuesAddr[i].childNodes[0].nodeValue;
	 			document.getElementById("cmbBranchName").options[i+1] = theOption;
		 	}
	 	}
	
}
</script>

<hdiits:form name="frmBankBranchMpg" id="frmBankBranchMpg" method="post" validate="">
<fieldset class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.BANKBRANCHMPG" bundle="${pensionLabels}"></fmt:message></b>
</legend>

<table width="100%" align="center" >
<tr>
<td>
<fmt:message key="PPMT.PASBANKNAME" bundle="${pensionLabels}"></fmt:message>
</td>
<td>
<select name="cmbPasBankName" id="cmbPasBankName" onfocus="onFocus(this)" onblur="onBlur(this);"  onchange="loadPasBranchList();">
		<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		  <c:forEach var="bank" items="${resValue.lLstPasBank}">
		  <c:choose>
		  		<c:when test="${bank.id == resValue.PasBankCode}">
			  		<option value='${bank.id}' selected="selected">
					<c:out value="${bank.desc}"></c:out>
					</option>
		  		</c:when>
		  		<c:otherwise>
			  		<option value='${bank.id}'>
					<c:out value="${bank.desc}"></c:out>
					</option>
		  		</c:otherwise>
		  </c:choose>
			
		 </c:forEach>
</select>
<fmt:message key="PPMT.MAPBANK" bundle="${pensionLabels}"></fmt:message>
<input type="checkbox" name="chkMapBank" id="chkMapBank" value="Y">
</td>
</tr>
<tr>
<td>
<b><fmt:message key="PPMT.RBIBRANCH" bundle="${pensionLabels}"></fmt:message></b>
</td>
</tr>
<tr>

<td>
<fmt:message key="PPMT.CITY" bundle="${pensionLabels}"></fmt:message>
</td>
<td>
<select name="cmbDistrict" id="cmbDistrict" onfocus="onFocus(this)" onblur="onBlur(this);" >
		<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		  <c:forEach var="district" items="${resValue.lLstDistrict}">
		   	<option value='${district}'>
				<c:out value="${district}"></c:out>
			</option>
		</c:forEach>
</select>
<fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
<select name="cmbBankName" id="cmbBankName" onfocus="onFocus(this)" onblur="onBlur(this);" onchange="getBankBranchFrmBankCode(this);" >
		<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		  <c:forEach var="bank" items="${resValue.lLstBank}">
		   	<option value='${bank.id}'>
				<c:out value="${bank.desc}"></c:out>
			</option>
		</c:forEach>
</select>
<fmt:message key="PPMT.BANKBRNNAME" bundle="${pensionLabels}"></fmt:message>
<select name="cmbBranchName" id="cmbBranchName" onfocus="onFocus(this)" onblur="onBlur(this);" >
		<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		 
</select>

</td>
</tr>
</table>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 50%; height: 400px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table border="1" id="tblPasBranch">

<tr>
<td>
<b><fmt:message key="PPMT.PASBRANCHNAME" bundle="${pensionLabels}"></fmt:message></b>
</td>
<td>
</td>
<td>
<b><fmt:message key="PPMT.RBIBRANCHNAME" bundle="${pensionLabels}"></fmt:message></b>
</td>
</tr>
<tr>
<c:forEach var="branch"	items="${resValue.lLstPasBranch}" varStatus="Counter">
<tr>
<td>
<input type="hidden" name="hdnPasBranchCode" id="hdnPasBranchCode${Counter.index}" value="${branch[0]}"/>
${branch[1]}

</td>
<td>
<hdiits:button id="btnMap${Counter.index}" name="btnMap${Counter.index}" type="button" captionid="PPMT.MAP" bundle="${pensionLabels}" onclick="mapBankBranch(this);resetMapBank();" />
</td>
<td>
<label id="lblBranchName${Counter.index}">${branch[2]}</label>
</td>
</tr>
</c:forEach>

</tr>

</table>
<script>
if(document.getElementById("tblPasBranch").rows.length >1)
{
	checkMapBank();
}
</script>
</div>
</fieldset>
<table align="center">
<tr>
		<td align="center">
			<hdiits:button id="btnClose" name="btnClose"  type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
		</td>
		
	</tr>
</table>
</hdiits:form>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBankListFromDistrict" source="cmbDistrict" target="cmbBankName" parameters="districtName={cmbDistrict}" ></ajax:select>

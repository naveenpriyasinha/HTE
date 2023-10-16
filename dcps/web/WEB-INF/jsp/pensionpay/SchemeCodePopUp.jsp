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
<c:set var="VOList" value="${resValue.lLstMstSchemeVO}" />
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>

<script>
function getSchemeCode(obj)
{
	
	document.getElementById("hdnSchemeCode").value = obj.value;
}

function setSchemeCode()
{
	var elementId=document.getElementById("hdnElementId").value;
	window.opener.document.getElementById(elementId).value=document.getElementById("hdnSchemeCode").value;
	window.close();
}
function showSchemeCodeList() 
{
	showProgressbar();
	document.frmSchemeCode.action = 'ifms.htm?actionFlag=loadSchemeCodePopUp&schemeType='+document.getElementById("hdnSchemeType").value+'&schemeCode='+document.getElementById("txtSchemecode").value+'&elementId='+document.getElementById("hdnElementId").value;
	document.frmSchemeCode.method = "post";
	document.frmSchemeCode.submit();
	hideProgressbar();

}
</script>

<div>
<hdiits:form name="frmSchemeCode" id="frmSchemeCode" method="post" validate="">
<table align="center">
<tr align="center">
<td align="center">
<fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message>
<input type="text" name="txtSchemecode" id="txtSchemecode" onfocus="onFocus(this)"  onblur="onBlur(this);" value="${resValue.SchemeCode}">
<input type="hidden" name="hdnSchemeType" id="hdnSchemeType" value="${resValue.SchemeType}">
<a href="#" onclick="showSchemeCodeList();"><img src="images/search.gif" /></a>
</td>
</tr>
</table>

<fieldset  style="width:98%"  class="tabstyle">
	<legend	id="headingMsg">
	<b><fmt:message key="PPMT.SCHEMELIST" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<input type="hidden" id="hdnElementId" name="hdnElementId" value="${resValue.ElementId}"/>
<input type="hidden" id="hdnSchemeCode" name="hdnSchemeCode" />
<display:table list="${VOList}" id="vo" style="width:90%" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" > 
						
		<display:column
			headerClass="datatableheader" style="width:1%">
			<input type="radio" align="middle" name="rdnSchemeCode"
				id="rdnSchemeCode${vo_rowNum}"	value="${vo.schemeCode}" onclick="getSchemeCode(this);"/>
		</display:column>
		<display:column titleKey="PPMT.SCHEMECODE"
			headerClass="datatableheader" style="width:10%">
			${vo.schemeCode}
		</display:column>
			<display:column titleKey="PPMT.SCHEMENAME"
			headerClass="datatableheader" style="width:30%">
			${vo.schemeName}
		</display:column>
</display:table>
</fieldset>
</hdiits:form>
</div>
<div style="text-align:center;">
	
	<hdiits:button type="button" name="forward" captionid="PPMT.SUBMIT"  bundle="${pensionLabels}" onclick="setSchemeCode();"/>

	<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="winCls();"/>
</div>	
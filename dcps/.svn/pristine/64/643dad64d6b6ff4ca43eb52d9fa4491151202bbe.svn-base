<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="errorMsg" value="${resultValue.errorMsg}"/>
<c:set var="app" value="${resultValue.app}"/>


<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables" scope="request"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<script>
function goBack(){
if('${app}'=='rtaAdv'){
document.forms[0].action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
document.forms[0].submit();
}
else if('${app}'=='rtaReimb')
{
document.forms[0].action="hdiits.htm?actionFlag=getEmpSearchSelData&code=RTA_RI&multiple=false";
document.forms[0].submit();
}
}
</script>

<hdiits:form name="Errorfrm" validate="true"  method="post"	encType="multipart/form-data">
<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<c:forEach var="errorMsg" items="${errorMsg}">
		<tr>
		<td align="left">
		<b><font color="red" >*</font></b>
		<font size="1"> <b><hdiits:caption captionid="HRMS.${errorMsg}" bundle="${commonLables}" /></b></font>
		</td>
		</tr>
</c:forEach>
</table>
<p align="center">
		<hdiits:button  type="button" name="Close" captionid="HRMS.close"	bundle="${commonLables}" onclick="goBack();"/></p>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

		





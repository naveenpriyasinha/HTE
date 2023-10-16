<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="errorMsg" value="${resultValue.errorMsg}">
</c:set>

<fmt:setBundle basename="resources.hr.gratuity.gratuity" var="commonLables" scope="request"/>
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

<hdiits:form name="Errorfrm" validate="true" action="hdiits.htm?actionFlag=getEmpSearchSelData&code=GA&multiple=false" method="post"	encType="multipart/form-data">

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
		<hdiits:formSubmitButton  type="button" name="Close" captionid="HRMS.close"	bundle="${commonLables}" /></p>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

		





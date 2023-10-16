<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript">
function submitMe()
{	
	document.frmMessage.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	document.frmMessage.submit();
}
</script>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="errorMsg" value="${resultValue.msg}"></c:set>
<c:set var="myMsg" value="${resultValue.myMsg}">
</c:set>
<fmt:setBundle basename="resources.ess.leave.AlertMessages" var="commonLables" scope="request" />
<fmt:setBundle basename="resources.hrms.HrmsCommonMessages" var="msgBundle" scope="request" />
<hdiits:form name="frmMessage"  validate="false"	action="" method="post" encType="multipart/form-data">
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
<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<c:forEach var="errorMsg" items="${errorMsg}">
		<tr>
		<td align="left">
		<b><font color="red" >*</font></b>
		<font size="1"> <b>		
			<c:if test="${empty myMsg or myMsg eq ''}">							
				<fmt:message key="HRMS.${errorMsg}" bundle="${commonLables}"/>
			</c:if><c:if test="${not empty myMsg and myMsg ne ''}">							
				<fmt:message key="HRMS.${myMsg}" bundle="${commonLables}"/>
			</c:if>
			</b></font>
		</td>		
		</tr>					
</c:forEach>
</table>
<center>
<hdiits:button type="button" name="formSubmit" value="Submit" onclick="submitMe()"	captionid="HRMS.OK" tabindex="1" bundle="${msgBundle}"/>
</center>
</hdiits:form>			
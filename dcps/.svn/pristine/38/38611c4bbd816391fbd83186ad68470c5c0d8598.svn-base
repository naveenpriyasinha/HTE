<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="message" value="${resultValue.msg}">
</c:set>
<c:set var="id" value="${resultValue.appReqId}">
</c:set>
<c:set var="status" value="${resultValue.status}">
</c:set>

<c:set var="LeaveType" value="${resultValue.leaveType}">
</c:set>

<fmt:setBundle basename="resources.hrms.HrmsCommonMessages"
	var="msgBundle" scope="request" />
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
<hdiits:form name="frmMessage"  validate="false"	action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322" method="post" encType="multipart/form-data">

<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<tr>
		<td align="right">
		<b><font color="red">*</font></b>
		<b><fmt:message key="HRMS.REQUESTNO" bundle="${msgBundle}"/> :</b>
		</td>
		<td align="left">${id}
		</td>

	</tr>
		<tr>
		<td align="right">
		<b><font color="red">*</font></b>
		<b><fmt:message key="HRMS.TYPE" bundle="${msgBundle}"/> :</b>
		</td>
		<td align="left"><fmt:message key="HRMS.${message}" bundle="${msgBundle}"/>
		</td>
		</tr>
		<tr align="center">
		<td align="right">
		<b><font color="red">*</font><fmt:message key="HRMS.APPROVER" bundle="${msgBundle}"/> :</b>	
			</td>
		<td  align="left" >
		 ${resultValue.fwdTo}
		</td>
		<tr align="center">
		<td align="right">
		<b><font color="red">*</font><fmt:message key="HRMS.STATUS" bundle="${msgBundle}"/> :</b>	
			</td>
		<td  align="left" >
		 <fmt:message key="HRMS.${status}" bundle="${msgBundle}"/>
		</td>
	</tr>

</table>
<center>
<hdiits:formSubmitButton type="button" name="formSubmit" value="Submit"	captionid="HRMS.OK" tabindex="3" bundle="${msgBundle}"/>

</center>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>





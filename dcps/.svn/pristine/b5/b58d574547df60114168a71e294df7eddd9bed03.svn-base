<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script>

//function to be called on press of close
function closeButtonHandler()
{
	document.SelfAppraisalSubmitted.method="POST";
	document.SelfAppraisalSubmitted.action="./hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";	
	document.SelfAppraisalSubmitted.submit();
	
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="message" value="${resultValue.msg}"></c:set>
<c:set var="id" value="${resultValue.appReqId}"></c:set>
<c:set var="status" value="${resultValue.status}"></c:set>
<fmt:setBundle basename="resources.hrms.HrmsCommonMessages"	var="msgBundle" scope="request" />
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">
<hdiits:form name="SelfAppraisalSubmitted" method="POST" validate="true"  encType="multipart/form-data" >			
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
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
		<td align="left"><fmt:message key="HR.ACR.SelfAppraisal" bundle="${commonLables}"/>
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
<br><br>
	<table id="submit"  align="center">
	<tr colspan="1">
		<td>
			<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="Close" type="button" onclick="closeButtonHandler();"/>
		</td>
	</tr>
	</table>
</hdiits:form>		
	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>
	<hdiits:validate locale="${locale}" controlNames="" /></div>
</div>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	
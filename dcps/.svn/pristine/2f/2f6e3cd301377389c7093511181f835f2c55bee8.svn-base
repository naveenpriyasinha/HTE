<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript">
function validation()
{
	str =document.getElementById("year").value;
	if(str=='Select'){		
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectYear"/>');
		return;
	}
	else
	{
		showProgressbar("Please Wait...");
		document.AcrSelectYear.method="POST";
		document.AcrSelectYear.action="./hrms.htm?actionFlag=ACRHiGroupMstPage";	
		document.AcrSelectYear.submit();	
	}
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<hdiits:form name="AcrSelectYear" method="POST" validate="true"  encType="multipart/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">		
		<br><br><br><br>
		<table class="tabtable" align="center">
			<tr>
				<td  width="25%"></td>
				<td  width="25%">
					<b><hdiits:caption captionid="HR.ACR.Year" bundle="${commonLables}"/></b>
				</td>
				<td  width="25%">
					<hdiits:select name="year" id="year" sort="false">
						<option value="Select"><hdiits:caption captionid="HR.ACR.Select" bundle="${commonLables}" /></option>
						<option value="<c:out value="${year-1}" />" selected="selected"><c:out value="${year-1} - ${year}" /></option>					
						<option value="<c:out value="${year}" />" ><c:out value="${year} - ${year+1}" /></option>
					</hdiits:select>		
				</td>
				<td  width="25%"></td>	
			</tr>					
		</table>
		<br><br>		
		<table align="center" width="100%">
			<tr >
				<td colspan="10" align="center">
					<hdiits:button captionid="HR.ACR.Submit" bundle="${commonLables}" name="Submit" type="button"  onclick="validation();"/>											
				</td>						
			</tr>			
		</table>
	</div>
</div>
</hdiits:form>		
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
<hdiits:validate locale="${locale}" controlNames="" />
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	
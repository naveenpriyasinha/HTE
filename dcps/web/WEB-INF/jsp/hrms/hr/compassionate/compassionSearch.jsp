
<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/hrms/hr/compassion/compassion.js"></script>

<fmt:setBundle basename="resources.hr.compassion.Labels" var="CapLabels" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>


<body>


<hdiits:form name="compSearch" validate="true" method="POST"  encType="text/form-data"> 

 	
<div id="tabmenu" >
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="comp.compassion" bundle="${CapLabels}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<br>
	
	<table id="" width="50%" align="center">
	<tr align="center">
		<input type="hidden" name="userID" id="userID">
		<td align="left"><b><hdiits:caption captionid="comp.empName" bundle="${CapLabels}"></hdiits:caption></b>
		</td>
		<td align="left">
		<hdiits:text name="empName" id="empName" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
		<img src="images/search_icon.gif" onclick="SearchEmp();" style="cursor:hand">
		</td>
	</tr>
	</table>
<center> <hdiits:button name="frmAddRecord" id="frmAddRecord"  type="button" captionid="comp.search" bundle="${CapLabels}" onclick="submitSearch();" />
	</center>		

	</div>

	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />	
	
</hdiits:form>

</body>

</html>






<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

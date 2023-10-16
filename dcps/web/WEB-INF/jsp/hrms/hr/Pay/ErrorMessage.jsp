<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<fmt:setBundle basename="resources.hr.addPay.AdditionalPayLabels" var="AddPay" scope="request"/>

<hdiits:form name="AdditionalPay" validate="true" method="POST"   encType="multipart/form-data"> 

 	
<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="AddPay.EmpDetail" bundle="${AddPay}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
		
		<hdiits:hidden name="actionFlag" default="insertAdditionPayDetail" />
		
		<div id="tcontent1" class="tabcontent" tabno="0" >

	<br>
	
<br>

<table width="100%" align="center">
 <TR bgcolor="#386CB7" >
<td width="100%" align="center" height="20px"><b><u><FONT color="WHITE"><hdiits:caption captionid="AddPay.errorMessage" bundle="${AddPay}"/></FONT></u>
</b></td> </TR> </table>

</div>
	

	
	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
	

</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
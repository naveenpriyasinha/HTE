<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabels" scope="request" />
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<html>
<script type="text/javascript">
function setFlag()
{
	opener.document.promotionPersonData.openChild.value = 'false';
	window.close();
}
</script>
<body onunload="setFlag();">
<hdiits:form name="promotionRemarks" validate="true" method="POST" 
	action="" encType="multipart/form-data">
<div id="tabmenu">

	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="Promotion.readRemarks" bundle="${promotionLabels}" /></a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<center>
			<hdiits:textarea name="txtData" cols="70" rows="10" caption="Remarks" id="txtData" readonly="true">
			</hdiits:textarea>
			<BR>
			<BR>
			<hdiits:button name="btnSave" type="button" caption="Close" value="Close" onclick="setFlag();"/>
		</center>	
	</div>
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")		
		document.getElementById('txtData').value=opener.document.promotionPersonData.textData.value;		
</script>
<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
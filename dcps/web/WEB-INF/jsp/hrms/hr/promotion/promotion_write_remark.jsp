<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabels" scope="request" />
<html>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript">
function saveData()
{
	opener.document.promotionInit.openChild.value = 'false';
	opener.document.promotionInit.textData.value=document.getElementById('txtData').value;	
	window.opener.SetData();
	window.close();	
}
function setFlag()
{
	opener.document.promotionInit.openChild.value = 'false';
}
</script>
<body onunload="setFlag();">
<hdiits:form name="promotionRemarks" validate="true" method="POST" 
	action="" encType="multipart/form-data">
<div id="tabmenu">

	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="Promotion.withRemarks" bundle="${promotionLabels}" /></a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<center>
			<hdiits:textarea name="txtData" cols="70" rows="10" caption="Remarks" id="txtData">
			</hdiits:textarea>
			<BR>
			<BR>
			<hdiits:button name="btnSave" type="button"  value="Save" onclick="saveData()"/>
		</center>	
	</div>
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")		
		document.getElementById('txtData').value=opener.document.promotionInit.textData.value;		
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


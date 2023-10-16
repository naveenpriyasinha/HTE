<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabels" scope="request" />

<hdiits:form name="promotionInit" validate="true" method="POST" 
	action="" encType="multipart/form-data">
<div id="tabmenu">

	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="Promotion.Msg" bundle="${promotionLabels}" /></a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<BR>
	<BR>
	<BR>
	<BR>
	<BR>
	<center>
	<hr width="70%">
		<b><c:out value="${resValue.msg}"></c:out></b>
	<hr width="70%">
	</center>
	</div>
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.		
		initializetabcontent("maintab")
</script>


<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
		
</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


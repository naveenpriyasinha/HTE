<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/xmldom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>



<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>




<hdiits:form name="frmBF" validate="true" method="POST" 
	 encType="multipart/form-data" >


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="GPF"/></a></li>
	</ul>
</div>

	 
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">

<table>
<tr>
	<td>
		UnExpected Error.
	</td>
</tr>

</table>
  
 </div>
 </div>

<script type="text/javascript">
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
	initializetabcontent("maintab")
</script>

<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>


<%
} catch (Exception e) 
	{
	e.printStackTrace();
	}
%>


<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>


<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set> 
<c:set var="accExists" value="${resValue.accExists}"></c:set> 
<c:set var="newAccountCrt" value="${resValue.newAccountCrt}"></c:set>
<c:set var="transferId" value="${resValue.transferId}"></c:set>
<c:set var="promoId" value="${resValue.promoId}"></c:set>
<c:set var="recruitId" value="${resValue.recruitId}"></c:set>

<hdiits:form name="frmGpfNoAcc" validate="true" method="POST" encType="text/form-data" >

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected">
<a href="#" rel="tcontent1"><b>
<fmt:message key="GPF"/>
</b></a></li>

</ul>
</div>

<div class="tabcontentstyle">


<div id="tcontent1" class="tabcontent" tabno="0"> 
<Br><br><br><br>

<table width="100%" align="center">


<tr><th align="center">
<c:if test="${accExists=='N'}">
 <br>
 <hdiits:caption captionid="GPF.noacc" bundle="${gpfLables}"/>
<a href="hrms.htm?actionFlag=viewNewAccReqPage">
<fmt:message key="GPF.clickForNewacc" bundle="${gpfLables}"/>
</a>
</c:if>

<c:if test="${accExists=='Y' and transferId==0 and promoId==0 and recruitId==0}">
<hdiits:caption captionid="GPF.accNoExists" bundle="${gpfLables}"/>
<br>
<hdiits:caption captionid="GPF.condnForNewAcc" bundle="${gpfLables}"/>
</c:if>

<c:if test="${newAccountCrt=='N'}">
	<hdiits:caption captionid="GPF.crtNewAccForTransfer" bundle="${gpfLables}"/>
	<br>
	<a href="hrms.htm?actionFlag=viewNewAccReqPage&flag=1">
	<fmt:message key="GPF.clickForNewacc" bundle="${gpfLables}"/>
	</a>
</c:if>
</th></tr>
<tr></tr>


</table>

</div> 
</div>
<script type="text/javascript">

initializetabcontent("maintab")
</script>
<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

<%
} catch (Exception e) {
e.printStackTrace();
}
%>


<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="script/hrms/ess/travel/travelCommonScripts.js"></script>
 
 
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="advMstCmbList" value="${resultValue.advMstCmbList}">
</c:set>
<c:set var="journeyDtl" value="${resultValue.journeyDtl}">
</c:set>
<c:set var="tourId" value="${resultValue.tourId}">
</c:set>
<c:set var="payModList" value="${resultValue.payMode}">
</c:set>
<c:set var="receivableAmt" value="${resultValue.grandTotal}">
</c:set>
<c:set var="maxAdvAmt" value="${resultValue.maxAdvAmt}">
</c:set>
<fmt:setBundle basename="resources.ess.travel.caption"
	var="caption" scope="request" />
<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />




<hdiits:form name="travelRmbs" validate="true" method="POST"
	encType="multipart/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="HRMS.Reimbursment" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<br>
	<br>
	<br>
	<center>
		<table>
			<tr>
				<td align="left">
				<b>
					<hdiits:radio name="pre" value="-1" onclick="showPreSanctionReqPage(this)" bundle="${caption}" captionid="HRMS.PreSanctionRmbs"/>
				
				</td>
			</tr>	
			<tr>
				<td align="left">
				<b>
					<hdiits:radio name="pre" value="-1" onclick="showPostFactoReqPage(this)" bundle="${caption}" captionid="HRMS.PostFactoRmbs"/>			

				</td>
			</tr>
		</table>
	</center>
	</div>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
	
		</script>
	
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>







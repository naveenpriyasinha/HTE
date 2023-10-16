
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>">
</script>

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
	
<fmt:setBundle basename="resources.hr.transfer.transferLabels"  var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>


<c:set var="decisionTable" value="${resValue.decisionTable}"></c:set>

  <style type="text/css">
	#footer {display:none}
</style>

<script type="text/javascript">




</script>
<hdiits:form name="listforTransReqByEmp" validate="true" method="post" action="" encType="text/form-data">


	
	
	
	<table width="100%" id="ListofEmpForTransReq" name="ListofEmpForTransReq" border="1" style="display:none">
		<tr class="datatableheader">

			
			<td ><hdiits:caption captionid="trn.locto"
				bundle="${transferLables}"></hdiits:caption></td>
			<td ><hdiits:caption captionid="trn.locType"
				bundle="${transferLables}"></hdiits:caption></td>
			<td ><hdiits:caption captionid="trn.prty"
				bundle="${transferLables}"></hdiits:caption></td>
				
			<td ><hdiits:caption captionid="trn.Decision"
				bundle="${transferLables}"></hdiits:caption></td>
			<td ><hdiits:caption captionid="trn.appDate"
				bundle="${transferLables}"/></td>
			<td ><hdiits:caption captionid="trn.Remark"
				bundle="${transferLables}"/></td>	
		</tr>
</table>


	

<c:if test="${not empty decisionTable}">
	 
		<c:forEach items="${decisionTable}" var="decisionTable" >
			
			<script type="text/javascript">

		document.getElementById('ListofEmpForTransReq').style.display='';
	
		var trow=document.getElementById('ListofEmpForTransReq').insertRow();
		trow.align="center";
	 
		trow.insertCell(0).innerHTML = "${decisionTable.locName}" ;
		 
		trow.insertCell(1).innerHTML = "${decisionTable.depName}" ;	
		 
		trow.insertCell(2).innerHTML = "${decisionTable.priority}" ;
		trow.insertCell(3).innerHTML = "${decisionTable.decision}";	
		 
		trow.insertCell(4).innerHTML = "${decisionTable.rsnForTrans}";
	 
		
		 
		trow.insertCell(5).innerHTML = "${decisionTable.remark}" ;							
	   										
	   									
</script>
		</c:forEach>

	</c:if>

	
	
	
	
	

	
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

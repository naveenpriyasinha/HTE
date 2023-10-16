
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<fmt:setBundle basename="resources.hr.transfer.transferLabels"
	var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>


<c:set var="EmpDetVO" value="${resValue.tranEmpDtl}" />
<c:set var="fileid" value="${resValue.fileId}" />


<hdiits:form name="formOnRequestIntimation" validate="true"
	method="post" action="./hrms.htm?" encType="text/form-data">


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption captionid="trn.header" bundle="${transferLables}" captionLang="single"/>
		 </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>


	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" /> <hdiits:hidden
		name="fileId" id="fileId" default="${fileid}" />
	<table width="100%">
		<tr>
			<td><b> <hdiits:caption captionid="trn.empNm"
				bundle="${transferLables}" /></b></td>
			<td><c:out value="${EmpDetVO.empfName}"></c:out></td>
			</tr>
		<tr>
			<td><b><hdiits:caption captionid="trn.designation"
				bundle="${transferLables}" /></b></td>
			<td><c:out value="${EmpDetVO.dsgnName}"></c:out></td>
			<td><b> <hdiits:caption captionid="trn.trnLoc"
				bundle="${transferLables}" /> </b></td>
			<td><c:out value="${EmpDetVO.city}"></c:out></td>
		</tr>
		<tr>
			<td><b> <hdiits:caption captionid="trn.salary"
				bundle="${transferLables}" /></b></td>
			<td><c:out value="${EmpDetVO.salary}"></c:out></td>

		</tr>
		<tr>
			<td><b> <hdiits:caption captionid="trn.dtJoin"
				bundle="${transferLables}" /></b></td>
			<td><fmt:formatDate value="${EmpDetVO.datej}"
				pattern="dd/MM/yyyy" /></td>
			<td><b> <hdiits:caption captionid="trn.dtRetire"
				bundle="${transferLables}" /></b></td>
			<td><fmt:formatDate value="${EmpDetVO.dateR}"
				pattern="dd/MM/yyyy" /></td>
		</tr>


	</table>
<hdiits:fieldGroup titleCaptionId="trn.prvPstDt" bundle="${transferLables}" expandable="true" > 

	<br>
	

	<table width="100%" border="1">
		<tr class="datatableheader">
			<td width="20%"><hdiits:caption captionid="trn.pstNm"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.offce"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.policestation"
				bundle="${transferLables}"></hdiits:caption></td>

			<td width="20%"><hdiits:caption captionid="trn.frmDt"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.toDt"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.period"
				bundle="${transferLables}"></hdiits:caption></td>
		</tr>
	</table>

</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="trn.totTrs" bundle="${transferLables}" expandable="true"  > 
	<br>
	

	<table width="100%" border="1">
		<tr class="datatableheader">
			<td width="20%"><hdiits:caption captionid="trn.frmplce"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.toplce"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.frmDt"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.toDt"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.rsn"
				bundle="${transferLables}"></hdiits:caption></td>
		</tr>
	</table>

</hdiits:fieldGroup>


	</div>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab")
		
		
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

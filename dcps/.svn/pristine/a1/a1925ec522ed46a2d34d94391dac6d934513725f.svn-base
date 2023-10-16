<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels"
	var="PensionLabels" scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="flag" value="${resValue.flag}" />
<c:set var="status" value="${resValue.status}" />
<c:set var="sevaarthId" value="${resValue.sevaarthId}" />
<c:set var="name" value="${resValue.name}" />
<c:set var="pnsrDtls" value="${resValue.pnsrDtls}" />
<c:set var="sevID" value="${resValue.sevID}" />
<c:set var="chckSevarth" value="${resValue.chckSevarth}" />

<script type="text/javascript">
	function SubmitData() {

		var sevaarthID = "";
		
		sevaarthID = document.getElementById("txtSevaarth").value;
		if(document.getElementById("txtSevaarth").value==""){
		alert("Please enter the Sevaarth Id");
		}
		else{
		var url = "ifms.htm?actionFlag=getPensionerCaseDtls&sevaarthID="
				+ sevaarthID;
		document.loadForm.action = url;
		document.loadForm.submit();
		showProgressbar("Please wait...");

		
		}
	}

	function PreparePensionCase() {

		var sevaarthID = "";
		
		sevaarthID = document.getElementById("sevaarthID").value;
		
		var url = "ifms.htm?actionFlag=checkPensionCaseStatus&sevaarthID="
				+ sevaarthID;
		document.loadRequestForm.action = url;
		document.loadRequestForm.submit();
		showProgressbar("Please wait...");
	}
	
function showPensionCaseReport(sevaarthID) {

		
		var url = "ifms.htm?actionFlag=viewPensionCaseReport&sevaarthID=" + sevaarthID;
		window.open(url);
	}
	
	</script>
<hdiits:form name="loadForm" validate="true" method="post" action=" ">
<c:if test="${chckSevarth eq 'Invalid'}">
		<script>
				alert("Invalid Sevaarth Id");
			</script>
		</c:if>
<c:if test="${chckSevarth eq 'inavalidLocDdo'}">
		<script>
				alert("Sevarth Id does not belong to logged in DDO");
			</script>
		</c:if>
		<c:if test="${chckSevarth eq 'noCase'}">
		<script>
				alert("Case is not prepared for this Sevarth Id");
			</script>
		</c:if>

	<fieldset class="tabstyle">
		<legend>
			<fmt:message key="PPROC.SEARCH" bundle="${PensionLabels}"></fmt:message>
		</legend>
		<table width="50%" align="center">
			<tr>
				<td width="25%"><fmt:message key="PPMT.SEVAARTH"
						bundle="${PensionLabels}"></fmt:message></td>

				<td width="25%"><input type="text" id="txtSevaarth" size="30"
					name="txtSevaarth" style="text-transform: uppercase;"
					value="${pnsrDtls[1]}" /><label class="mandatoryindicator">*</label></td>
					
			</tr>
		
			<tr>
			
			</tr>
		</table>
	<center>
		<hdiits:button name="btnSubmitData" id="btnSubmitData" type="button"
			captionid="PPROC.SEARCH" bundle="${PensionLabels}"
			onclick="SubmitData();" />
	
	</center>
	</fieldset>
</hdiits:form>

<c:if test="${status eq 'yes'}">
	<hdiits:form name="loadRequestForm" validate="true" method="post"
		action=" ">

		<c:if test="${flag eq 'NA'}">

			<script>
				alert("First Approve the Pension Case.");
			</script>
		</c:if>
		
		
		<fieldset class="tabstyle">
			<legend>
				<fmt:message key="PPMT.REQ" bundle="${PensionLabels}"></fmt:message>
			</legend>
			<input type="hidden" id="sevaarthID" name="sevaarthID"
				value="${sevaarthId}"> <input type="hidden" id="EmpName"
				name="EmpName" value="${name}">
			<table align="center" width="100%" border="1px #000000"
				style="border: none; border-collapse: collapse;" cellspacing="1"
				cellpadding="1">
				<tr>
					<td><fmt:message key="PPMT.EMPNAME" bundle="${PensionLabels}"></fmt:message>
					</td>
					<td><fmt:message key="PPMT.SEVAARTH" bundle="${PensionLabels}"></fmt:message>
					</td>
					<td><fmt:message key="PPMT.CURPOST" bundle="${PensionLabels}"></fmt:message>
					</td>
					<td><fmt:message key="PPROC.DATEOFBIRTH"
							bundle="${PensionLabels}"></fmt:message></td>
					<td><fmt:message key="PPMT.SERVENDDATE"
							bundle="${PensionLabels}"></fmt:message></td>
				</tr>
				<tr>
					<td>${pnsrDtls[0]}</td>
					<td>${pnsrDtls[1]}</td>
					<td>${pnsrDtls[2]}</td>
					<td>${pnsrDtls[3]}</td>
					<td>${pnsrDtls[4]}</td>
				</tr>
			</table>
			<br></br>
			<center>
				<hdiits:button name="btnCaseData" id="btnCaseData" type="button" 
					captionid="BTN.CASE" bundle="${PensionLabels}"
					onclick="PreparePensionCase();" style="width:200px;"/>
			</center>
			<br></br>
		</fieldset>



		<c:if test="${flag==true}">
		
			<fieldset class="tabstyle">
				<legend>
					<fmt:message key="PPMT.FORMS" bundle="${PensionLabels}"></fmt:message>
				</legend>
				<input type="hidden" id="sevaarthID" name="sevaarthID"
					value="${sevID}">
					
					
				<table align="center" width="100%" cellspacing="5" cellpadding="1">
					<tr>
						<td><a href="#" onclick="javascript:showPensionCaseReport('${sevID}');">Pension Case Form</a>
						</td>
					</tr>
</table>
</fieldset>
</c:if>
</hdiits:form>
</c:if>

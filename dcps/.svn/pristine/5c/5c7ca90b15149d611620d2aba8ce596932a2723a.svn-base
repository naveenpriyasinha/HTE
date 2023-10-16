
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>



<script type="text/javascript" src="script/common/tagLibValidation.js"></script>



<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"
	scope="request" />

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="hrDeputreqmtDtl" value="${resultValue.hrDeputreqmtDtl}">
</c:set>
<c:set var="cmnLocationMst" value="${resultValue.cmnLocationMst}">
</c:set>
<c:set var="orgGradeMst" value="${resultValue.orgGradeMst}">
</c:set>
<c:set var="orgPostDetailsRlt" value="${resultValue.orgPostDetailsRlt}">
</c:set>
<c:set var="orgPostMst" value="${resultValue.orgPostMst}">
</c:set>
<c:set var="setOfEligiblity" value="${resultValue.setOfEligiblity}">
</c:set>
<c:set var="corr_Id" value="${resultValue.corr_Id}">
</c:set>
<c:set var="hrDeputEmpCorrSet" value="${resultValue.hrDeputEmpCorrSet}">
</c:set>
<script type="text/javascript">
function aggmtSubmit()
{

		document.EmpCorrsForm.action='./hrms.htm?actionFlag=getAgreeEmpMsg&corr_Id='+${corr_Id};
			document.EmpCorrsForm.submit();	
			
}

</script>

<hdiits:form name="EmpCorrsForm" validate="true" method="POST">

	
			<hdiits:fieldGroup titleCaptionId="depDetails" bundle="${comLable}" collapseOnLoad="true">
	<table align="center" width="100%">
		
		<tr>
			<td>

			<table width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption
						captionid="deputedplace" bundle="${comLable}" /></b></td>
					<td width="25%">${cmnLocationMst.locName}</td>
					<td width="25%"></td>
					<td width="25%">${rdiopinon}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table align="center" width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="address"
						bundle="${comLable}" /></b></td>
					<td width="25%">${cmnLocationMst.locAddr1}</td>
					<td width="25%"><b><hdiits:caption captionid="noofperson"
						bundle="${comLable}" /></b></td>

					<td width="25%">${hrDeputreqmtDtl.noOfPersons}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table align="center" width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="reqforclass"
						bundle="${comLable}" /></b></td>
					<td width="25%">${orgGradeMst.gradeName}</td>
					<td width="25%"><b><hdiits:caption captionid="reqforpost"
						bundle="${comLable}" /></b></td>

					<td width="25%">${orgPostDetailsRlt.postName}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="fromdate"
						bundle="${comLable}" /></b></td>
					<td width="25%"><fmt:formatDate
						value="${hrDeputreqmtDtl.deputfromDate}" pattern="dd/MM/yyyy" /></td>
					<td width="25%"><b><hdiits:caption captionid="todate"
						bundle="${comLable}" /></b></td>

					<td width="25%"><fmt:formatDate
						value="${hrDeputreqmtDtl.deputtoDate}" pattern="dd/MM/yyyy" /></td>
				</tr>
			</table>
			</td>
			
		</tr>
		
	</table>
	</hdiits:fieldGroup>
	
			<hdiits:fieldGroup titleCaptionId="uMustopinion" bundle="${comLable}" collapseOnLoad="true">
			<table width="100%">
			<tr>
			<td>
	<c:if test="${hrDeputEmpCorrSet.isAccepted eq \"Y\"}">
<fieldset>
	<legend style="font-size: larger" ><b><hdiits:caption captionid="enterOpMsg"
						bundle="${comLable}" /></b></legend>
<table width="100%">
<tr>
<td colspan="5"><font size="2">you have selected for this post please select a your opinion</font>
</td>
</tr>

<tr>
<td width="30%" align="right"><input type="radio" name="rdiopinon" value="agree"  id="rdiopinon" checked="checked"/>
	<font size="2"> <strong>Agree</strong> </font> </td>
<td width="30%"><input type="radio" name="rdiopinon" value="disagree" id="rdiopinon" /><font size="2"> <strong>Disagree</strong> </font> </td> 
</tr>
<tr>
<td align="center" colspan="50" width="40%"><hdiits:button name="btnopinion"  type="button" caption="submit" onclick="aggmtSubmit();"/></td>

</tr>
</table>
</fieldset>
</c:if>
	<c:if test="${hrDeputEmpCorrSet.isAccepted  ne \"Y\"}">
<fieldset>
	<legend style="font-size: larger" ><b><hdiits:caption captionid="youropinion"
						bundle="${comLable}" /></b>
</legend>
<table width="100%">
<tr>
<td colspan="5"><font size="2">you have selected for this post  </font>
</td>


</tr>
<tr>
<td colspan="5"><font size="2">your opinion is</font>
</td>
</tr>
<tr>
<c:if test="${hrDeputEmpCorrSet.isAccepted  eq \"Agree\"}">
<td width="30%" align="center"><input type="radio" name="rdiopinon" value="agree"  id="rdiopinon" checked="checked" disabled="disabled"/>
	<font size="2"> <strong>Agree</strong> </font>
	<input type="radio" name="rdiopinon" value="disagree" id="rdiopinon" disabled="disabled"/><font size="2"> <strong>Disagree</strong> </font>
	 </td>
</c:if>
	<c:if test="${hrDeputEmpCorrSet.isAccepted  eq \"DisAgree\"}">
<td width="30%" align="center">
<input type="radio" name="rdiopinon" value="agree"  id="rdiopinon" disabled="disabled" />
	<font size="2"> <strong>Agree</strong> </font>
<input type="radio" name="rdiopinon" value="disagree" id="rdiopinon" checked="checked" disabled="disabled"/><font size="2"> <strong>Disagree</strong> </font> </td> 
</c:if>
</tr>
</table>
</fieldset>

	</c:if>
	</td>
	</tr>
	</table>
	</hdiits:fieldGroup>
</hdiits:form>




<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

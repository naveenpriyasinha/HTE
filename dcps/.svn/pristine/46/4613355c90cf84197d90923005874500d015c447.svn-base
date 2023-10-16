
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
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>
<script type="text/javascript">
function backToLst()
{
				document.DeputViewdtl.action='./hrms.htm?actionFlag=deputRequest';
				document.DeputViewdtl.submit();
}
</script>

<hdiits:form name="DeputViewdtl" validate="true" method="POST">

	<hdiits:hidden name="BranchHeadPostID" id="BranchHeadPostID" />
	<hdiits:hidden name="fileId"
		default="${hrDeputreqmtDtl.deputationreqmtId}" />
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
		<hdiits:fieldGroup titleCaptionId="deputationRequest" bundle="${comLable}" collapseOnLoad="true">
	<table align="center" width="100%">
		

		<tr>
			<td>

			<table width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption
						captionid="deputedplace" bundle="${comLable}" /></b></td>
					<td width="25%">${cmnLocationMst.locName}</td>
					<td width="25%"></td>
					<td width="25%"></td>
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

				<tr>
			<td>
				<hdiits:fieldGroup titleCaptionId="eligibilitycriteria" bundle="${comLable}"   collapseOnLoad="true">
			<table align="center" width="80%" border="1" id="criteria" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}"  borderColor="black" class="tabtable">
				<tr style="background-color:${tdBGColor}">
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="attribute" bundle="${comLable}" /></b></td>
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="condition" bundle="${comLable}" /></b></td>
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="description" bundle="${comLable}" /></b></td>
				</tr>
				<c:forEach var="eligiblity" items="${setOfEligiblity}">
					<tr>
						<td align="left" colspan="50">
						${eligiblity.cmnLookupMstByAttributeId.lookupDesc}</td>
						<td align="left" colspan="50">
						${eligiblity.cmnLookupMstByAttributeConditionId.lookupDesc}</td>
						<td align="left" colspan="50">${eligiblity.attributeValue}</td>
					</tr>
				</c:forEach>
			</table>
			</hdiits:fieldGroup>
			</td>
		</tr>

		<tr>
			<td>
			<table align="center" width="100%" id="buttonTbl"
				style="display: none;">
				<tr>

					<td align="center" colspan="50"><hdiits:button name="Dep.back"
						captionid="Dep.back" bundle="${comLable}" type="button"
						onclick="backToLst();" /></td>

				</tr>


			</table>
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

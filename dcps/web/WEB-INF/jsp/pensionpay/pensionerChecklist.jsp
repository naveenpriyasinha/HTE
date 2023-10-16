<%try{%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="pnrsCode" value="${resValue.pensionerCode}"></c:set>

<script type="text/javascript" src="script/pensionpay/schedulecases.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>

<hdiits:form name="PnsrCheckList" method="post" validate="">
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPMT.PNSRCHEKLST"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<table align="center">
<tr>
<td>
</td>
<td>
	<label><b> <fmt:message key="PPMT.DOCNAME" bundle="${pensionLabels}"></fmt:message></b></label>
</td>
<c:forEach var="checkListVO" items="${resValue.lLstAllChecklistVO}">
		<tr>
		<td>
		<c:set var="checkFlag" value="N" />
		<c:forEach var="selectedCheckListVO" items="${resValue.lLstSelectedCheckList}">
			<c:if test="${checkFlag == 'N' and selectedCheckListVO.docId == checkListVO.docId }">
				<c:set var="checkFlag" value="Y" />
			</c:if>
		</c:forEach>
		<c:choose>
		<c:when test="${checkFlag == 'Y'}">
			<input type="checkbox" align="middle" name="chkbxChecklistId" id="chkbxChecklistId_${checkListVO.docId}" onclick="" value="${checkListVO.docId}" checked="checked" />
		</c:when>
		<c:otherwise>
			<input type="checkbox" align="middle" name="chkbxChecklistId" id="chkbxChecklistId_${checkListVO.docId}" onclick="" value="${checkListVO.docId}"  />
		</c:otherwise>
		</c:choose>
		</td>
		<td>
			<label>${checkListVO.docDesc}</label>	
		</td>
		</tr>
</c:forEach>
</table>
</fieldset>
<div align="center">
	<c:choose>
	<c:when test="${resValue.lStrShowReadOnly == 'Y'}">
		<hdiits:button name="btnSave"	type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveCheckListDtls('${pnrsCode}');"  readonly = "true" /> &nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:button name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" readonly = "true" />
	</c:when>
	<c:otherwise>
		<hdiits:button name="btnSave"	type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveCheckListDtls('${pnrsCode}');" /> &nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:button name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
	</c:otherwise>
	</c:choose>
</div>			
</hdiits:form>
<script>
	var isReadOnly = "${resValue.lStrShowReadOnly}";
	if(isReadOnly == "Y")
	{
		var lArrChkBox = document.getElementsByName("chkbxChecklistId");
		for(var cnt = 0; cnt <lArrChkBox.length;cnt++)
		{
			lArrChkBox[cnt].disabled = true;
		}
	}
</script>
<% }catch(Exception e){
e.printStackTrace();
}%>
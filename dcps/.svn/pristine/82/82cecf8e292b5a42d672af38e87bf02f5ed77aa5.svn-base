<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />


<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ListOfRemarks" value="${resValue.lLstRemarks}"></c:set>
<c:set var="counter" value="1"></c:set>

<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b>Pension Case Remarks</b></legend> 
	
	<hdiits:form name="frmPnsnCaseRemarksInfo"
	id="frmPnsnCaseRemarksInfo" validate="true"
	method="post">

	<table width="100%" border="1">
	<tr>
	<th width="5%" align="center">Sr.No.</th>
	<th width="20%" align="center">User Name</th>
	<th width="20%" align="center">User Role</th>
	<th width="10%" align="center">Order No.</th>
	<th width="20%" align="center">Order Date</th>
	<th width="25%" align="center">Remarks</th>
	<th width="20%" align="center">Remarks Date</th>
	
	</tr>
		<c:if test="${ListOfRemarks != null}">
			<c:forEach var="Remarks" items="${ListOfRemarks}">
				<tr width="30%" align="center">
					<td align="left"><label>${counter}</label></td>					
					<td align="left"><label>${Remarks[0]}</label></td>
					<td align="left"><label>${Remarks[1]}</label></td>
					<td align="left"><label>${Remarks[2]}</label></td>
					<td align="left"><label><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${Remarks[3]}"/></label></td>
					<td align="left"><label>${Remarks[4]}</label></td>
					<td align="left"><label><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${Remarks[5]}"/></label></td>
					
				
					<c:set var="counter" value="${counter+1}"></c:set>
				</tr>
				
			</c:forEach>
		</c:if>
	</table>
	<br></br>
	<center>
	<hdiits:button type="button" name="btnClose" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="self.close();"/>
	</center>
		<br></br>
</hdiits:form>

</fieldset>


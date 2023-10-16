<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>


<script type="text/javascript" src="script/pensionpay/PensionConfig.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TotalUsers" value="${resValue.lLstTotalUsers}"></c:set>
<c:set var="counter" value="1"></c:set>

<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b>User Remarks</b></legend> 
	
	<hdiits:form name="auditorMappingInfo"
	id="auditorMappingInfo" validate="true"
	method="post">

	<table width="100%" border="1">
	<tr>
	<th width="10%" align="left">Sr.No.</th>
	<th width="20%" align="left">User Name</th>
	<th width="20%" align="left">User Role</th>
	<th width="50%" align="left">Remarks</th>
	</tr>
		<c:if test="${TotalUsers != null}">
			<c:forEach var="UserNames" items="${TotalUsers}">
				<tr width="30%" align="center">
					<td align="left"><strong><label>${counter}</label></strong></td>					

					<td align="left"><strong><label>${UserNames[0]}</label></strong></td>
					
					
					
					
					<td align="left"><strong><label>${UserNames[1]}</label></strong></td>
				
					
					<c:choose>
						<c:when test="${UserNames[2] == null || UserNames[2] == ''}">
							<td align="left"><strong><label>No
							</label></strong></td>
						
						</c:when>
						<c:otherwise>
							<td align="left"><strong><label>${UserNames[2]}</label></strong></td>
							
						</c:otherwise>
					</c:choose>
					<c:set var="left" value="${counter+1}"></c:set>
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


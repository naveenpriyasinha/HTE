<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.ResourceBundle"%>

<fmt:setBundle basename="resources.billproc.billproc" var="billprocLabels" scope="request"/>
<script type="text/javascript" src="script/billproc/validation.js"></script>

<hdiits:form name="sendToForm" validate="true" method="post">
	<c:set var="resultObj" value="${result}">
	</c:set>
	<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
	<c:set var="otherUserList" value="${resValue.OtherUserList}"></c:set>
	<%
			ResourceBundle buttonBundle = ResourceBundle
			.getBundle("resources/billproc/billproc");
%>
<p align="left" style="padding-left: 40px">
	<table>
		<tr class="datatableheader">
			<td><fmt:message key="COMMON.SENDTOOTHER" bundle="${billprocLabels}"></fmt:message>
			</td>
		</tr>
		<tr>
			<td><hdiits:select sort="false" name="sendToCmb" id="sendToCmb" size="10">
				<c:choose>
					<c:when test="${otherUserList != null}">
						<c:forEach var="otherUserVO" items="${otherUserList}">
							<option
								value="<c:out value="${otherUserVO[0]}~${otherUserVO[2]}~${otherUserVO[3]}" />"
								selected="true"><c:out value="${otherUserVO[1]}" /></option>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<option value="">-- No Users Found --</option>
					</c:otherwise>
				</c:choose>
			</hdiits:select></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><hdiits:button name="sendToFwd" type="button"
				value='<%=buttonBundle.getString("COMMON.FORWARD")%>'
				onclick="forwardToOther()" />
				<input type="button" class="buttontag" name="CloseBtn" value='<%=buttonBundle.getString("CHQCOMMON.CLOSE")%>' onclick="javascript:window.close();"></td>
		</tr>
	</table>
	</p>
</hdiits:form>
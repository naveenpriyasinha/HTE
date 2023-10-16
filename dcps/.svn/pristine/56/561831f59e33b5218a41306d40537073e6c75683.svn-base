 <%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
   
<fmt:setBundle basename="resources.billproc.billproc" var="billprocLabels" scope="request"/>


<hdiits:table align="center" width="90%">
			<hdiits:tr>
				<hdiits:td width="60%">
				</hdiits:td>
				<hdiits:td width="8%" align="right">
					Search :
				</hdiits:td>
				<hdiits:td width="20%">
					<hdiits:text name="txtSearch" size="15"/>
				</hdiits:td>
					<hdiits:td width="*">
							<hdiits:select name="cmbSearch">
							<hdiits:option value="0">-----Select-----</hdiits:option>
							<hdiits:option value="1">
								<fmt:message key="CMN.BILL_NO" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
							<hdiits:option value="2">
								<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
							<hdiits:option value="3">
								<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
							<hdiits:option value="4">
								<fmt:message key="CMN.CARDEX_NO" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
							<hdiits:option value="5">
								<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
							<hdiits:option value="6">
								<fmt:message key="CHQ.INWARDED_DATE" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
							<hdiits:option value="7">
								<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
							<hdiits:option value="8">
								<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
							</hdiits:option>
						</hdiits:select>
					</hdiits:td>
					<hdiits:td width="2%">
						<img src="common/images/search.gif" align="right" height="16" width="16" />
					</hdiits:td>
				</hdiits:tr>
			</hdiits:table>
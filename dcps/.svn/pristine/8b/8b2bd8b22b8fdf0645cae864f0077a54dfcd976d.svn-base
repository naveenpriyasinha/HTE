<%try{ %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/hrms/ess/reimb/hr_remb_validations.js"></script>

<fmt:setBundle basename="resources.ess.reimb.reimbLables" var="enLables" scope="request"/>
<c:set var="resultObj" value="${result}" ></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="propertyTypeList" value="${resValue.propertyTypeList}"></c:set>

<fmt:formatDate var="getPresentYear" pattern="yyyy" value="<%=new java.util.Date() %>" />

<html>
<body>


<tr bgcolor="#386CB7">
		<td class="fieldLabel" align="center" colspan="9">
		<font color="#ffffff"><strong><u>					
		<fmt:message bundle="${enLables}" key="RM.property"/><fmt:message bundle="${enLables}" key="RM.Details"/>
		</u></strong></font></td>
</tr>
<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.nameOfProperty" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="nameOfProperty" captionid="RM.nameOfProperty" bundle="${enLables}" mandatory="true" validation="txt.isrequired" />
		
	</td>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.ownerOfProperty" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="ownerOfProperty" captionid="RM.ownerOfProperty" bundle="${enLables}" mandatory="true" validation="txt.isrequired" /></td>
</tr>

<tr>
	<td width="20%" colspan="4">
						<jsp:include page="/WEB-INF/jsp/common/address.jsp">
            	    	<jsp:param name="addrName" value="Reimburse" />
                		<jsp:param name="addressTitle" value="Property Address" />
	                	<jsp:param name="addrLookupName" value="Present Address" />
				    	<jsp:param name="mandatory" value="Yes"/>              
    					</jsp:include>
	</td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.typeOfProperty" bundle="${enLables}" /></td>
	<td class="fieldLabel" width="25%">
	<hdiits:select name="typeOfProperty" id="typeOfProperty" captionid="RM.typeOfProperty" sort="false" bundle="${enLables}" mandatory="true" validation="sel.isrequired">
			<hdiits:option value="Select"><fmt:message bundle="${enLables}" key="RM.Select"/></hdiits:option>
			<c:forEach items="${propertyTypeList}" var="propertyTypes">
				<hdiits:option value="${propertyTypes.lookupName}">${propertyTypes.lookupDesc}</hdiits:option>
			</c:forEach>
	</hdiits:select>
	</td>
	<td class="fieldLabel" width="25%"></td>
	<td class="fieldLabel" width="25%"></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.taxPaidOfYear" bundle="${enLables}" /></td>
	<td class="fieldLabel" width="25%">
		<hdiits:select name="taxPaidOfYear" id="taxPaidOfYear" captionid="RM.taxPaidOfYear" sort="false" bundle="${enLables}" mandatory="true" validation="sel.isrequired">
			<hdiits:option value="Select"><fmt:message bundle="${enLables}" key="RM.Select"/></hdiits:option>
			<c:forEach var="year" begin="${getPresentYear - 2}" end="${getPresentYear + 2}">
				<hdiits:option value="${year}">${year}</hdiits:option>
			</c:forEach>
		</hdiits:select>
	</td>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.taxInvoiceNo" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="taxInvoiceNo" captionid="RM.taxInvoiceNo" bundle="${enLables}" mandatory="true" validation="txt.isrequired" /></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.dueDtOfPay" bundle="${enLables}" /></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="dueDtOfPay"  captionid="RM.dueDtOfPay" bundle="${enLables}"  /></td>
	
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.actualDtOfPay" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="actualDtOfPay"  captionid="RM.actualDtOfPay" bundle="${enLables}"/></td>
</tr>


<tr>
	<td class="fieldLabel" width="25%">
		<hdiits:caption captionid="RM.taxAmount" bundle="${enLables}" /><br>
		<font size="1px"><fmt:message bundle="${enLables}" key="RM.paidBeforeDueDt"></fmt:message></font>
	</td>
	<td class="fieldLabel" width="25%"><hdiits:text name="taxAmountbeforDueDt"  captionid="RM.taxAmount" bundle="${enLables}" mandatory="true" validation="txt.isnumber,txt.isrequired" /></td>
	
	<td class="fieldLabel" width="25%">
		<hdiits:caption captionid="RM.taxAmount" bundle="${enLables}"/><br>
		<font size="1px"><fmt:message bundle="${enLables}" key="RM.paidAfterDueDt"></fmt:message></font>
	</td>
	<td class="fieldLabel" width="25%"><hdiits:text name="taxAmountafterDueDt"  captionid="RM.taxAmount" bundle="${enLables}" mandatory="true" validation="txt.isnumber,txt.isrequired" /></td>
</tr>



<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</body>
</html>

  <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.gratuityLeaveencashment.gratuityLeaveencashment" var="commonLables" scope="request"/>
<fmt:setBundle basename="resources.hr.groupInsurance.groupInsurance" var="constants" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="gp" value="${resValue.hrEmpGroupinsurance}"></c:set>


<hdiits:form name="groupInsuranceDisplay"  validate="true" method="POST"  encType="multipart/form-data" >
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>

<hdiits:fieldGroup bundle="${constants}"  expandable="true" id="Grpdtls" collapseOnLoad="false" titleCaptionId="HRMS.grpdtls">
<table class="tabtable" style="border-collapse: collapse; " borderColor="BLACK"  border=1>

	<tr >
		<td bgcolor="#C9DFFF" width="15%">
			<b><hdiits:caption bundle="${constants}" captionid="HRMS.group" /></b>
		</td>

		<td bgcolor="#C9DFFF" width="15%">
			<b><hdiits:caption bundle="${constants}" captionid="HRMS.frmdt" /></b>
		</td>
		<td bgcolor="#C9DFFF" width="15%">
			<b><hdiits:caption bundle="${constants}" captionid="HRMS.todt" /></b>
		</td>
	
	<td bgcolor="#C9DFFF" width="15%">
			<b><hdiits:caption bundle="${constants}" captionid="HRMS.amt" /></b>
		</td>
	
		<td bgcolor="#C9DFFF" width="15%">
			<b><hdiits:caption bundle="${constants}" captionid="HRMS.multifctr" /></b>
		</td>
			<td bgcolor="#C9DFFF" width="15%">
			<b><hdiits:caption bundle="${constants}" captionid="HRMS.totamt" /></b>
			</td>
		</tr>
		
		
		<tr>
			<c:set var="counter" value="0"/>
			
			
				
					<c:forEach var="subGroup" items="${gp.hrGroupinsuranceDtlTxns}">	
						
						<tr>
							<td>
							${subGroup.cmnLookupMst.lookupDesc}
      						</td>
	
							<td>
							<fmt:formatDate value="${subGroup.fromDate}" pattern="dd/MM/yyyy" var="fromdate"/>
							${fromdate}
							</td>
	
							<td>
							<fmt:formatDate value="${subGroup.toDate}" pattern="dd/MM/yyyy" var="todate"/>
							${todate}
							</td>
							
							<td>
							<c:out value="${subGroup.amount}"/>
    						</td>
							
							<td>
							<c:out value="${subGroup.multiplyingFactor}"/>
    						</td>
						
							<td>
							<c:out value="${subGroup.totalAmt}"/>
    						</td>
						</tr>
						<c:set var="counter" value="${counter+1}"/>
					</c:forEach>
	<tr>
			<td></td><td></td><td></td><td></td>
						
			<td align="right" bgcolor="#C9DFFF">
				<b><hdiits:caption bundle="${constants}" captionid="HRMS.grosamt" />:</b>
			</td>
			
			<td>
			<c:out value="${gp.grossAmt}"/>
			 <hdiits:hidden  name="grpinsstatus" caption="status" default="0" />
			 <hdiits:hidden  name="grpins_id" caption="status" default="${gp.grpId}" />
			</td>
	</tr>
	
</table>
</hdiits:fieldGroup>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
 </hdiits:form>

  <%
}catch(Exception e){
	e.printStackTrace();
}

%>
	
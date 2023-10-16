<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />
<fmt:setBundle basename="resources.eis.eis_Constants" var="empQuaterDtlsLabels" scope="page"/>
<hdiits:form name="form1" validate="true" method="POST" action="">
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
	<!-- GPF Details Started -->
	
	<tr>
			<td   colspan="9" >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpLoanSearch"/>
						<jsp:param name="formName" value="frmBF"/>
						<jsp:param name="functionName" value="chkValue"/>
					</jsp:include>
			</td>
			
			
		</tr>
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="empName" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="empName" maxlength="20"   size="22" />
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">
					<b><fmt:message key="designation" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:text name="designation" maxlength="20"   size="22" />
			</td>			
		</tr>
		<tr>
		<td></td>
		</tr>
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="typeOfGovAcco" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:radio captionid="rentFree"  caption="rentFree" bundle="${empQuaterDtlsLabels}" value="TRUE" default="TRUE" name="typeOfGovAcco" id="typeOfGovAcco" />
					<hdiits:radio captionid="rented"  caption="rented" bundle="${empQuaterDtlsLabels}" value="FALSE" default="TRUE" name="typeOfGovAcco" id="typeOfGovAcco" />
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
					<b><fmt:message key="quaterAllot" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:radio captionid="self"  caption="self" bundle="${empQuaterDtlsLabels}" value="TRUE" default="TRUE" name="quaterAllot" id="quaterAllot" />
					<hdiits:radio captionid="spouseRltOther"  caption="spouseRltOther" bundle="${empQuaterDtlsLabels}" value="FALSE" default="TRUE" name="quaterAllot" id="quaterAllot" />
			</td>			
		</tr>

	</table>
	
	<br>

 <hdiits:fieldGroup titleCaptionId="location" bundle="${empQuaterDtlsLabels}" >
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
	<!-- GPF Details Started -->
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="appFlatHouseNo" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="appFlatHouseNo" maxlength="20"   size="22" />
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">
					<b><fmt:message key="townVillage" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:text name="townVillage" maxlength="20"   size="22" />
			</td>			
		</tr>
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="buildingNo" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="buildingNo" id="buildingNo" maxlength="13"   /> 
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
					<b><fmt:message key="taluka" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:text name="taluka" id="taluka" maxlength="13"/>	
			</td>			
		</tr>
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="socBuildBunglowName" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="socBuildBunglowName" id="socBuildBunglowName" maxlength="13"   /> 
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
					<b><fmt:message key="district" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:text name="district" id="district" maxlength="13"/>	
			</td>			
		</tr>
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="locality" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="locality" id="locality" maxlength="13"   /> 
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
					<b><fmt:message key="pin" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:text name="pin" id="pin" maxlength="13"/>	
			</td>			
		</tr>
		
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="22%" align="left">
					
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
					<b><fmt:message key="state" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:select id="cmbState" name="cmbState" style="width:160px;">
							<hdiits:option value="Select"  selected="true">Select</hdiits:option>
							<hdiits:option value="0"  selected="true"></hdiits:option>
							<hdiits:option value="1"  selected="true"></hdiits:option>
							<hdiits:option value="2"  selected="true"></hdiits:option>
							<hdiits:option value="3"  selected="true"></hdiits:option>
							
					</hdiits:select>	
			</td>			
		</tr>
	
		
	</table>
</hdiits:fieldGroup>
 <br>
 <br>
 

 
 
 
 <hdiits:fieldGroup titleCaptionId="rentDtls" bundle="${empQuaterDtlsLabels}" >
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
	<!-- GPF Details Started -->
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="custodian" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:select id="cmbCustodian" name="cmbCustodian" style="width:160px;">
							<hdiits:option value="Select"  selected="true">Select</hdiits:option>
							<hdiits:option value="0"  selected="true"></hdiits:option>
							<hdiits:option value="1"  selected="true"></hdiits:option>
							<hdiits:option value="2"  selected="true"></hdiits:option>
							<hdiits:option value="3"  selected="true"></hdiits:option>
					</hdiits:select></td>
		
			<td width="4%" align="center"></td>
			<td width="22%" align="left">
					<b><fmt:message key="rent" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:text name="rent" maxlength="20"   size="22" />
			</td>			
		</tr>
		
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="are" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="area" id="area" maxlength="13"   /> 
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
					<b><fmt:message key="garageOtherCharges" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left">
					<hdiits:text name="garageOtherCharges" id="garageOtherCharges" maxlength="13"/>	
			</td>			
		</tr>
		<tr>			
			<td width="22%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="serviceCharges" bundle="${empQuaterDtlsLabels}"/></b>
			</td>			
			<td width="22%" align="left" colspan="2">
					<hdiits:text name="serviceCharges" id="serviceCharges" maxlength="13" />
			</td>	
			<td width="22%" align="left"></td>			
			<td width="22%" align="left"></td>		
		</tr>
		
	</table>
</hdiits:fieldGroup>

<br>
		
				
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="checkIfVacated" bundle="${empQuaterDtlsLabels}"/></b>			
	
				<hdiits:checkbox name="checkIfVacated" value=""  id="checkIfVacated"/>
	
		
<br>
<br>

 
 
 <hdiits:fieldGroup titleCaptionId="vacateOthrDtls" bundle="${empQuaterDtlsLabels}" >
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
	<!-- GPF Details Started -->
		
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="vacatLtrNo" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="vacatLtrNo" maxlength="20"   size="22" />
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="ltrDate" bundle="${empQuaterDtlsLabels}"/></b></td>			
			<td width="22%" align="left">	<fmt:formatDate var="stDate" value="${startDate}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />
        	<hdiits:dateTime caption="effctivedate"  name="effctivedate" default="${otherList.commissionAcceptanceDate}"  captionid="effctivedate" bundle="${commonLables}"  validation="txt.isdt"/>
			</td>			
		</tr>

		
			<tr>		
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="dateVacated" bundle="${empQuaterDtlsLabels}"/></b>
			</td>
			<td width="22%" align="left">
			<fmt:formatDate var="sDate" value="${startDate}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />        	
        	<hdiits:dateTime caption="effctivedate"  name="effctivedate1" default="${otherList.commissionAcceptanceDate}"  captionid="effctivedate" bundle="${commonLables}"  validation="txt.isdt"/>
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
				
			</td>			
			<td width="22%" align="left">	
			</td>			
		</tr>
		
	<!-- GPF Details Ended -->
</table>
</hdiits:fieldGroup>
 <br>
 <br>
<center>

<hdiits:button type="button" name="new" bundle="${empQuaterDtlsLabels}" captionid="new" style="height: 25px; width: 100px" />
<hdiits:button type="button" name="edit" bundle="${empQuaterDtlsLabels}" captionid="edit" style="height: 25px; width: 100px"/>
<hdiits:button type="button" name="save" bundle="${empQuaterDtlsLabels}" captionid="save" style="height: 25px; width: 100px"/>
<hdiits:button type="button" name="close" bundle="${empQuaterDtlsLabels}" captionid="close" style="height: 25px; width: 100px"/>
<hdiits:button type="button" name="revert" bundle="${empQuaterDtlsLabels}" captionid="revert" style="height: 25px; width: 100px"/>

 
 </center>



</hdiits:form>
<%
		} catch (Exception e) {
			e.printStackTrace();

	}
%>



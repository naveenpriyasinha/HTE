<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<!-- resource Bundle  -->



<fmt:setBundle basename="resources.CommonLables_en_US" var="commonLables" scope="page"/>


<!-- resource Bundle  -->



<hdiits:form name="joining after transfer/repatriation" validate="true" method="POST" action="">	
		<br>
		<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="EMP_NAME" bundle="${commonLables}"/></b></td>
		<br>
		<br>
		
<hdiits:fieldGroup titleCaptionId="RELIEVING_DETAILS" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<tr>
			<td width="26%" align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TRANSFER_ORDER_NO" bundle="${commonLables}" /></b></td>				
			<td><hdiits:text name="TRANSFER_ORDER_NO"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="ORDER_DATE" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="ORDER_DATE"  /></td>		
			<td width="22%" align="left"></td>		
			
		</tr>
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="RELIVED_FROM_OFFICE" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="RELIVED_FROM_OFFICE"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TRANSFER_MODE" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="TRANSFER_MODE"  /></td>		
			<td width="22%" align="left"></td>			
		</tr>
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="RELIVED_BY" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="RELIVED_BY"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="RELIVED_ON" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="RELIVED_ON"  /></td>		
			<td width="22%" align="left"></td>			
		</tr>	
	</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="JOINING_DETAILS" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<tr>
			<td width="26%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="JOINING_ON_ACCOUNT_OF" bundle="${commonLables}"/></b></td>				
			<td> &nbsp;&nbsp;<hdiits:select name="cmbJoining"  >
				<hdiits:option value="Select" >-------------Select-------------</hdiits:option></hdiits:select></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>						
			<td width="22%" align="left"></td>	
		    
		</tr>
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="JOINING_ORDER_NO" bundle="${commonLables}"/></b></td>				
			<td>&nbsp;&nbsp;<hdiits:text name="JOINING_ORDER_NO"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="ORDER_DATE_2" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="ORDER_DATE_2"  /></td>		
			<td width="22%" align="left"></td>			
		</tr>
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="OFFICE" bundle="${commonLables}"/></b></td>				
			<td>&nbsp;&nbsp;<hdiits:select name="cmbOffice"  >
				<hdiits:option value="Select">-------------Select-------------</hdiits:option></hdiits:select></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="DATE_OF_JOINING" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="DATE_OF_JOINING"  /></td>		
			<td width="22%" align="left"><hdiits:radio caption='BN'  bundle="${commonLables}" name="BN" value="" ></hdiits:radio>
				<hdiits:radio caption='AN' bundle="${commonLables}" name="AN" value=""></hdiits:radio></td>			
		</tr>	
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="OFFICE_CITY_CLASS" bundle="${commonLables}"/></b></td>				
			<td>&nbsp;&nbsp;<hdiits:select name="cmbOfficeCity"  >
				<hdiits:option value="Select">-------------Select-------------</hdiits:option></hdiits:select></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="DESIGNATION" bundle="${commonLables}"/></b></td>
			<td><hdiits:select name="cmbDesignation"  >
				<hdiits:option value="Select">-------------Select-------------</hdiits:option></hdiits:select></td>	
			<td width="22%" align="left"></td>			
		</tr>	
	</table>
</hdiits:fieldGroup>
</hdiits:form>

<%
}catch (Exception e) {
		e.printStackTrace();
	}
%>
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<!-- resource Bundle  -->



<fmt:setBundle basename="resources.CommonLables_en_US" var="commonLables" scope="page"/>


<!-- resource Bundle  -->



<hdiits:form name="Promotion and Transfer" validate="true" method="POST" action="">	
		<br>
		<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="EMP_NAME" bundle="${commonLables}"/></b></td>
		<br>
		<br>
		
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<tr>
		   <td><b>Employee &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>sachin M Mane</td>
		   <td width="22%" align="left"></td>
		   <td width="4%" align="center"></td>
		   
		   <td><b>CurrentDesignation :</b> Naik  </td>	
		</tr>	
		<tr>
		   <td><b>ParentFieldDept :</b>Directorate of sports And Youth Service </td>
		  	
		</tr>
		<tr>
		   <td><b>CurentCadre&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b> JuniorClerk</td>
		 </tr>
	
	</table>	
	<center>
	
	 <td width="22%" align="left"><b><hdiits:radio caption='PROMOTION'  bundle="${commonLables}" name="BN" value="" ></hdiits:radio>
				<hdiits:radio caption='REVERSION' bundle="${commonLables}" name="BN" value=""></hdiits:radio></td>	
				
				</center>	
	 <table>
	 
	    
	 
	 </table>
	
<hdiits:fieldGroup titleCaptionId="PROMOTION_ORDER_DETAILS" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<tr>
			<td width="10%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="PROMOTION_ORDER_NO" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="PROMOTION_ORDER_NO"  /></td>
			
			<td width="31%" align="left"></td>
			<td width="4%" align="center"></td>
			
			<td width="18%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="DATE" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="DATE"  /></td>	
				
			<td width="41%" align="left"></td>
			<td width="4%" align="center"></td>	
		</tr>
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="PROMOTION_WEF_DATE" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="PROMOTION_WEF_DATE"  /></td>
			
			<td width="22%" align="left"></td>
		
			<td width="4%" align="center"></td>
				
			
		</tr>
		
		<tr>
			
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			
			<td width="22%" align="left"></td>			
		</tr>	
	</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="JOINING_DETAILS" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="JOINING_ORDER_NO" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="JOINING_ORDER_NO"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="ORDER_DATE_2" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="ORDER_DATE_2"  /></td>		
			<td width="22%" align="left"></td>			
		</tr>
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="DATE_OF_JOINING" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="DATE_OF_JOINING"  /></td>
				
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			
			<td width="22%" align="left"><hdiits:radio caption='BN'  bundle="${commonLables}" name="BN" value="" ></hdiits:radio>
				<hdiits:radio caption='AN' bundle="${commonLables}" name="BN" value=""></hdiits:radio></td>			
		</tr>	
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="SELECT_CADRE" bundle="${commonLables}"/></b></td>				
			<td><hdiits:select name="cmbOfficeCity"  >
				<hdiits:option value="Select">-------------------Select-------------------</hdiits:option></hdiits:select></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
		    <td width="22%" align="left"></td>			
		</tr>	
		
		<tr>
		
		<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="REAMARK(IF_ANY)" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="REAMARK(IF_ANY)"  /></td>
		
				
		</tr>
		
		<table>
		
		<tr>
		<td width="90%" align="left"> <font color="#ff0000"><strong>Note </strong>:Please note that Changes will be effective only after verification&nbsp;by DDO.On verification,the Employee will be detached from current post automatically&nbsp; and new post should be assigned through &quot; Employee &gt;&gt;Map Employee with Post&quot;.</font></td>
		</tr>
		
		
		
		</table>
		
		
		
		
	</table>
	
	
</hdiits:fieldGroup>
</hdiits:form>

<%
}catch (Exception e) {
		e.printStackTrace();
	}
%>
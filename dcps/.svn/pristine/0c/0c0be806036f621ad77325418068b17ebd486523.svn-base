<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<!-- resource Bundle  -->



<fmt:setBundle basename="resources.CommonLables_en_US" var="commonLables" scope="page"/>


<!-- resource Bundle  -->



<hdiits:form name="ddooffice" validate="true" method="POST" action="">	
		<br>
		<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="NAME_OF_OFFICE" bundle="${commonLables}"/></b></td>
		<td ><hdiits:text name="NAME_OF_OFFICE"  /></td>
		<br>
		<br>
		
<hdiits:fieldGroup titleCaptionId="ADDRESS_DETAILS" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="STATE" bundle="${commonLables}"/></b></td>				
			<td><hdiits:select name="cmbState"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>
			
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="DISTRICT" bundle="${commonLables}"/></b></td>
			<td><hdiits:select name="cmbDISTRICT"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>	
			<td width="22%" align="left"></td>			
		</tr>
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TALUKA" bundle="${commonLables}"/></b></td>				
			<td><hdiits:select name="cmbTaluka"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>
			
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TOWN" bundle="${commonLables}"/></b></td>
			<td><hdiits:select name="cmbTown"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>	
			<td width="22%" align="left"></td>			
		</tr>
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="VILLAGE" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="VILLAGE"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>	
			<td width="22%" align="left"></td>			
		</tr>	
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="ADDRESS" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="ADDRESS"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>	
			<td width="22%" align="left"></td>			
		</tr>	
		
		<tr>
			<td width="22%" align="left"></td>				
			<td><hdiits:text name="ADDRESS1"  /></td>
			<td width="22%" align="left"></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>	
			<td width="22%" align="left"></td>			
		</tr>	
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="PIN" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="PIN"  /></td>
			
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="OFFICE_CITY_CLASS" bundle="${commonLables}"/></b></td>
			<td><hdiits:select name="cmbOfficeCity"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>	
			<td width="22%" align="left"></td>			
		</tr>
		
	</table>
</hdiits:fieldGroup>

<!-- 
<hdiits:fieldGroup titleCaptionId="CONTACT_DETAILS" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="STATE" bundle="${commonLables}"/></b></td>				
			<td><hdiits:select name="cmbState1"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>
			<td width="4%" align="center"></td>
			<td width="4%" align="left"></td>
			<td width="17%" align="left"><b><fmt:message key="DISTRICT" bundle="${commonLables}"/></b></td>
			<td><hdiits:select name="cmbDISTRICT1"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>	
			<td width="22%" align="left"></td>			
		</tr>
		
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TALUKA" bundle="${commonLables}"/></b></td>				
			<td><hdiits:select name="cmbTaluka1"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>
			
			<td width="4%" align="center"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TOWN" bundle="${commonLables}"/></b></td>
			<td><hdiits:select name="cmbTown1"  >
				<hdiits:option value="Select" >-------------------Select-------------------</hdiits:option></hdiits:select></td>	
			<td width="22%" align="left"></td>			
		</tr>
		
		</table>
</hdiits:fieldGroup>-->




<hdiits:fieldGroup titleCaptionId="CONTACT_DETAILS" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<tr>
			<td width="18%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TELE_NO1" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="TELE_NO1"  /></td>
			<td width="4%" align="center"></td>
			<td width="4%" align="left"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TELE_NO2" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="TELE_NO2"  /></td>		
		</tr>
			
		<tr>
			<td width="18%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="FAX" bundle="${commonLables}"/></b></td>				
			<td><hdiits:text name="FAX"  /></td>
			<td width="4%" align="center"></td>
			<td width="4%" align="left"></td>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="EMAIL" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="EMAIL"  /></td>		
		</tr>
		
			
	</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="WHETHER_OFFICE" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		
		<tr>
			<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="TRIBAL_AREA" bundle="${commonLables}"/></b></td>				
			<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:radio caption='YES'  bundle="${commonLables}" name="YES" value="" ></hdiits:radio>
				<hdiits:radio caption='NO' bundle="${commonLables}" name="YES" value=""></hdiits:radio></td>		
	
				
			<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="HILLY_AREA" bundle="${commonLables}"/></b></td>				
			<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:radio caption='YES'  bundle="${commonLables}" name="YES1" value="" ></hdiits:radio>
				<hdiits:radio caption='NO' bundle="${commonLables}" name="YES1" value=""></hdiits:radio></td>		
			
			<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="NEX_AREA" bundle="${commonLables}"/></b></td>				
			<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:radio caption='YES'  bundle="${commonLables}" name="YES2" value="" ></hdiits:radio>
				<hdiits:radio caption='NO' bundle="${commonLables}" name="YES2" value=""></hdiits:radio></td>				
		</tr>	
			
	</table>
</hdiits:fieldGroup>
</hdiits:form>

<%
}catch (Exception e) {
		e.printStackTrace();
	}
%>
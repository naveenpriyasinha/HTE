<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
<hdiits:form name="PensionerDtls" encType="multipart/form-data" validate="true" method="post">
	
<table width="85%" align="center">

		<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.FNAME"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtFname'
				id="txtFname" style="text-align: left" /></td>

			<td width="20%"></td>
			<td width="20%" align="left"><fmt:message key="PPMT.CATEGORY" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbCategory"
			id="cmbCategory" onfocus="" onblur="">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		    </select></td>

		</tr>

		<tr>
			<td width="20%" align="left"><fmt:message
				key="PPMT.FATHERHUSBANDNAME" bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name='txtFatherHusband' id="txtFatherHusband"
				style="text-align: left" /></td>
			<td width="20%"></td>
			<td width="20%" align="left"><fmt:message key="PPMT.LSTOFFSERVED"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name='txtLstOffServed' id="txtLstOffServed" style="text-align: left" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.DATEOFBIRTH"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name="txtBirthDate" id="txtBirthDate" maxlength="10"
				onkeypress="" onBlur="" value="" /> 
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDate",375,570)'
				style="cursor: pointer;" }/></td>
			<td width="20%"></td>
			<td width="20%" align="left"><fmt:message key="PPMT.HEIGHT"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtHeight'
				id="txtHeight" style="text-align: left" /></td>
		</tr>

		<tr>
			<td width="20%" align="left"><fmt:message
				key="PPMT.DATEOFRETIREMENT" bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name='txtDateofRetirement' id="txtDateofRetirement"
				style="text-align: left" />
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtDateofRetirement",375,570)'
				style="cursor: pointer;" }/></td>
			<td width="20%"></td>
			<td width="20%" align="left"><fmt:message
				key="PPMT.IDENTIFICATIONMARK" bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name='txtIdentificationMark' id="txtIdentificationMark"
				style="text-align: left" /></td>
		</tr>
		

</table>


<table width="100%"  cellpadding="2px" cellspacing="2px">		
       <tr>
			<td width="30%" align="center">
			<fieldset class="tabstyle" style="width:50%"><legend>
			<b> <fmt:message key="PPMT.GENDER" bundle="${pensionLabels}"></fmt:message></b>

			</legend>
			<table align="left">
				<tr>
					<td width="50%" align="left"><input type="radio"
						name="radioGender" id="radioGender" value="M"><fmt:message key="PPMT.MALE"
						bundle="${pensionLabels}"></fmt:message><br>
					<input type="radio" name="radioGender" id="radioGender" value="F"><fmt:message
						key="PPMT.FEMALE" bundle="${pensionLabels}"></fmt:message></td>
				</tr>
			</table>
			</fieldset>
			</td>
			<td width="5%"></td>
			
			<td width="30%" align="center">
			<fieldset class="tabstyle" style="width:50%"><legend> <b> <fmt:message
				key="PPMT.MARITALSTATUS" bundle="${pensionLabels}"></fmt:message></b></legend>
			<table align="left">
				<tr>
					<td width="50%" align="left"><input type="radio"
						name="radioMaritalStatus" id="radioMaritalStatus" value="M"><fmt:message
						key="PPMT.SINGLE" bundle="${pensionLabels}"></fmt:message><br>
					<input type="radio" name="radioGender" id="radioMaritalStatus" value="F"><fmt:message
						key="PPMT.MARRIED" bundle="${pensionLabels}"></fmt:message></td>

				</tr>
			</table>
			</fieldset>
			</td>

			<td width="5%"></td>

			<td width="30%" align="center">
			<fieldset class="tabstyle" style="width:50%"><legend> <b> <fmt:message
				key="PPMT.MENTALLYRETARDED" bundle="${pensionLabels}">

			</fmt:message></b> </legend>
			<table align="left">
				<tr>
					<td width="50%" align="left"><input type="radio" id="radioRetarded"
						name="radioRetarded" value="M"><fmt:message key="PPMT.YES"
						bundle="${pensionLabels}"></fmt:message><br>
					<input type="radio" name="radioRetarded" value="F"><fmt:message
						key="PPMT.NO" bundle="${pensionLabels}"></fmt:message></td>

				</tr>
			</table>
			</fieldset>
			</td>
	</tr>
	</table>
	
	
	<table  width="100%" >	
	<tr>
	<td width="35%" align="center">
		<fieldset class="tabstyle" style="width:80%"><legend> <b>		
			<fmt:message key="PPMT.GUARDIANDETAILS" bundle="${pensionLabels}"></fmt:message></b> </legend>
				<table width="100%" align="left" >
	
					<tr>
						<td width="20%" align="left"><fmt:message
						key="PPMT.FNAME" bundle="${pensionLabels}"></fmt:message></td>
						<td width="20%" align="left"><input type="text"
						name='txtGuardianName' id="txtGuardianName"
						style="text-align: left" /></td>
						
					</tr>
					
					<tr>
						<td width="20%" align="left"><fmt:message
						key="PPMT.FATHERHUSBANDNAME" bundle="${pensionLabels}"></fmt:message></td>
						<td width="20%" align="left"><input type="text"
						name='txtGuardianFatherHusbName' id="txtGuardianFatherHusbName"
						style="text-align: left" /></td>
					</tr>
		
					<tr>
						<td width="20%" align="left"><fmt:message
						key="PPMT.RELATIONSHIP" bundle="${pensionLabels}"></fmt:message></td>
						<td width="20%" align="left"><input type="text"
						name='txtRelationship' id="txtRelationship"
						style="text-align: left" /></td>
					</tr>
		
					<tr>
						<td width="20%" align="left"><fmt:message
						key="PPMT.ADDRESS" bundle="${pensionLabels}"></fmt:message></td>
						<td width="20%" align="left"><input type="text"
						name='txtAddress' id="txtAddress"
						style="text-align: left" /></td>
					</tr>
	 		</table> 
	

		 </fieldset>
		 
	</td>
	<td width="20%"></td>
	</tr>
</table>

<table id="tblAdd"  width="100%" >
	<tr>
		<td width="35%" align="center">
			<fieldset class="tabstyle" style="width:80%"><legend> <b>
				<fmt:message key="PPMT.ADDRESS" bundle="${pensionLabels}"></fmt:message></b> </legend>
		
					<table id="tblAddress" width="100%" align="left" cellpadding="2px" cellspacing="2px">
		
					<tr>
						
						<td width="15%" align="left"><fmt:message
						key="PPMT.FLATDOORBLOCKNO" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtFlatDoorBlock' id="txtFlatDoorBlock"
						style="text-align: left" /></td>
						<td width="10%"></td>
		
						<td width="15%" align="left"><fmt:message
						key="PPMT.PINCODE" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtPinCode' id="txtPinCode"
						style="text-align: left" /></td>
					
						
					</tr>
		
					<tr>
					    <td width="15%" align="left"><fmt:message
						key="PPMT.ROADPOSTOFFICE" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtRoadPostOffice' id="txtRoadPostOffice"
						style="text-align: left" /></td>
						<td width="10%"></td>
		
						<td width="15%" align="left"><fmt:message
						key="PPMT.LANDLINENUMBER" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtLandlineNo' id="txtLandlineNo"
						style="text-align: left" /></td>
					</tr>
		
		
					<tr>
						<td width="15%" align="left"><fmt:message
						key="PPMT.AREALOCALITY" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtAreaLocality' id="txtAreaLocality"
						style="text-align: left" /></td>
						<td width="10%"></td>
		
						<td width="15%" align="left"><fmt:message
						key="PPMT.MOBILENUMBER" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtMobileNo' id="txtMobileNo"
						style="text-align: left" /></td>
					
					</tr>
		
				<tr>
			
					<td width="15%" align="left"><fmt:message
						key="PPMT.TOWNCITYDISTRICT" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtTownCityDistrict' id="txtTownCityDistrict"
						style="text-align: left" /></td>
						<td width="10%"></td>
		
						<td width="15%" align="left"><fmt:message
						key="PPMT.EMAILID" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" align="left"><input type="text"
						name='txtEmailId' id="txtEmailId"
						style="text-align: left" /></td>
						
			</tr>
		
	</table>
</fieldset>
</td>
<td width="20%"></td>
</tr>
</table>

</hdiits:form>

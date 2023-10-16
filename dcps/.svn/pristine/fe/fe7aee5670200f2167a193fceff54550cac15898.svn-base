<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/NewRegistrationForm.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="employeeID" value="${resValue.EmployeeID}"></c:set>
<c:set var="address1" value="${resValue.address1}"></c:set>
<c:set var="address2" value="${resValue.address2}"></c:set>
<c:set var="NomineesList" value="${resValue.NomineesList}"></c:set>
<c:set var="lIntTotalNominees" value="${resValue.lIntTotalNominees}"></c:set>
<c:set var="listRelationship" value="${resValue.listRelationship}"></c:set>

<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varReadOnly" scope="page" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page" value="style='display: none;' "></c:set>
</c:if>

<script>
function hideRow() {  

	serialNo = serialNo - 1;
	var current = window.event.srcElement;
	var TD = current.parentElement;
	var TR = TD.parentElement;
	var lArrAllTds = TR.childNodes;
	
	var currentShare = lArrAllTds[3].childNodes[0].value;
	totalNomineeShareAdded = totalNomineeShareAdded - Number(currentShare) ;
	while ( (current = current.parentElement)  && current.tagName !="TR");
      current.parentElement.removeChild(current);
	
	var relationToBeRemoved =  lArrAllTds[4].childNodes[0].value;
	if(relationToBeRemoved.trim() == 'Mother')
	{
		motherAdded = false;
	}
	if(relationToBeRemoved.trim() == 'Father')
	{
		fatherAdded = false;
	}
	if(relationToBeRemoved.trim() == 'Spouse')
	{
		spouseAdded = false;
	}
}  

</script>
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.NOMINEEDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
	<table width="100%" align="center" cellpadding="4" cellspacing="4" border="0">
		<tr>
				<td width="15%" align="left"><fmt:message key="CMN.NOMNAME"
				bundle="${dcpsLables}"></fmt:message></td>
			    <td width="35%" align="left"><input type="text"
				id="txtNomineeName" size="48" onkeypress="nameFormat(this);" maxlength = "99"
				name="txtNomineeName" style="text-transform: uppercase" onblur="isName(txtNomineeName,'This field should not contain any special characters or digits.')" 
				value="" ${varReadOnly} />
				</td>
				
				<td width="15%" align="left" ><fmt:message key="CMN.ADDRESS"
				bundle="${dcpsLables}"></fmt:message></td>
				<td width="35%"> 
					<input type="text" size="48" name='txtNomAddress1' id="txtNomAddress1" style="text-transform: uppercase" value="" ${varReadOnly} />
					
					
				</td>	
				</tr>
	
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.DOB"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left" style="font-size:smaller"><input type="hidden"
				name="currDate" id="currDate" value="${resValue.lDtCurDate}" /> <input
				type="text" name="txtBirthDateOfNominee" id="txtBirthDateOfNominee" size="20"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtBirthDateOfNominee); compareDates(this,currDate,'Date of Birth should be less than current date.','<');" value="" ${varReadOnly}/> 
				<img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDateOfNominee",375,570)'
				style="cursor: pointer;" } ${varImageDisabled}/>
				<fmt:message key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message></td>
		</tr>
		<tr>
			
		
			<td width="15%" align="left"><fmt:message key="CMN.RELATIONSHIP"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><select name="cmbRelationship"
				id="cmbRelationship" style="width: 360px" onChange="" ${varDisabled}>
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="searchLst" items="${resValue.listRelationship}">
					<option value="${searchLst.lookupDesc}"><c:out
						value="${searchLst.lookupDesc}"></c:out></option>
				</c:forEach>
			</select>
			</td>
			
			<td width="15%" align="left"><fmt:message key="CMN.PERCENTSHARE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text"
				id="txtPercentShare" size="48" name="txtPercentShare" value=""
				onkeypress="isPercentage(this,'not a valid Percent Share',100)" onblur="chckPercentShare(txtPercentShare);" ${varReadOnly}/>
				</td>
		</tr>
	</table>
	<table width="100%" align="center" height="10%" cellpadding="0"
		cellspacing="0">
		<tr></tr>
		<tr></tr>
		<tr>
			<td width="100%" align="center" ${varImageDisabled}> <hdiits:button
				name="btnAdd"  id="btnAdd" type="button" captionid="BTN.ADD"
				bundle="${dcpsLables}" onclick="addRow();"/> </td>
				<td><input type="hidden"
				name="empID" id="empID" value="${resValue.EmployeeID}"/></td>
		</tr>
		
	</table>
	<table>
		<tr>&nbsp</tr>
		<tr>&nbsp</tr>
	</table>
	<div style="padding-left:200px;" >
	<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:85%; height:120px; overflow: scroll; overflow-x:scroll; overflow-y:scroll; ">
	<table id="displayTableForNomineeDtls" align="center" width="95%" >
	
	<tr>
		<td  align="center"><b>Sr.No</b></td>
		<td  align="center"><b>Nominee Name</b></td>
		<td  align="center"><b>Date Of Birth</b></td>
		<td  align="center"><b>%Share</b></td>
		<td  align="center"><b>Relationship</b></td>
		<td  align="center" ${varImageDisabled}><b>Delete Nominee</b></td>
	</tr>
		<c:choose>
					<c:when test="${resValue.NomineesList !=null}">
						
						<c:forEach var="NomineeVO" items="${resValue.NomineesList}" varStatus="Counter">
						<tr>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="5" style="text-align: center" name="txtNomineeSerialNoValue" id="txtNomineeSerialNoValue" class='${Counter.index}' value="${Counter.index+1}" readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="20" style="text-align: center" name="txtNameValue" id="txtNameValue" class='${Counter.index}' value="${NomineeVO.name}"  readonly="readonly"/>
						</td>
						
						<input type="hidden"  name="txtNomineeId" id="txtNomineeID" class='${Counter.index}' value="${NomineeVO.dcpsEmpNmnId}" readonly="readonly"/>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="20" style="text-align: center" name="txtDateOfBirthValue"  class='${Counter.index}' id="txtDateOfBirthValue"  onkeypress="numberFormat(this);digitFormat(this);dateFormat(this);"   maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${NomineeVO.dob}"/>" readonly="readonly"/>
						</td>
						
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="5" style="text-align: center" name="txtPercentShareValue" id="txtPercentShareValue" class='serialNo' value="${NomineeVO.share}"  readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
						<input type="text" size="10" style="border: none;" style="text-align: center" name="txtRelationshipValue${Counter.index}" id="txtRelationshipValue${Counter.index}"  class='${Counter.index}' size="20" onkeypress="numberFormat(this);"   style="width:60px" value="${NomineeVO.rlt}" readonly="readonly"/>
						</td>
						
						<td class="tds" align="center"  ${varImageDisabled }>
						<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRow();hideRow();">
						<input type="hidden" name="txtNomName" class="${Counter.index+1}"  value="${NomineeVO.name}"/>
						<input type="hidden" name="txtNomAddr1" class="${Counter.index+1}"  value="${NomineeVO.address1}"/>
						<input type="hidden" name="txtNomDOB" class="${Counter.index+1}"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${NomineeVO.dob}"/>"/>
						<input type="hidden" name="txtNomPerShare" class="${Counter.index+1}"  value="${NomineeVO.share}"/>
						<input type="hidden" name="txtNomRelationship" class="${Counter.index+1}"  value="${NomineeVO.rlt}"/>
						</td>
						</tr>
						
					<script>
					//	document.getElementById("hidGridSize").value=Number('${Counter.index}') + 1;
						serialNo=Number('${Counter.index}') + 2;
					</script>
					</c:forEach>
					</c:when>
					
		</c:choose> 		
		

	</table>
	</div>
	</div>
	<br/>
	
	</fieldset>
	
	

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>

<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseFamilyDtls.js"></script>

<script type="text/javascript">

EMAILID = '<fmt:message key="PPROC.EMAILID" bundle="${pensionAlerts}"></fmt:message>';
STATE ='<fmt:message key="PPROC.STATE" bundle="${pensionAlerts}"></fmt:message>';
TOWNCITYDIST ='<fmt:message key="PPROC.TOWNCITYDIST" bundle="${pensionAlerts}"></fmt:message>';
PINCODE ='<fmt:message key="PPROC.PINCODE" bundle="${pensionAlerts}"></fmt:message>';
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<input type="hidden" name="hidNumOfRows" id="hidNumOfRows" value="${resValue.lStrRowId}" />
<input type="hidden" name="hidNomOrFamily" id="hidNomOrFamily" value="${resValue.lStrAddress}" />

<fieldset style="width:90%;height: 70%"  class="tabstyle">
<legend id="headingMsg"><b>
<fmt:message key="ADDRESS" bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="100%" align="center">
	<tr>
         <td width="30%" align="left">
				<fmt:message key="PPROC.FLATDOORBLOCK" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   <input type="text" name='txtAddrFlatDoorBlk' id="txtAddrFlatDoorBlk" value="${resValue.lStrFlat}"  />
			</td>  
		</tr> 
		<tr>
	 
         <td width="30%" align="left">
				<fmt:message key="PPROC.ROADPOSTOFF" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			    <input type="text" name='txtAddrRoadPostOff' id="txtAddrRoadPostOff" value="${resValue.lStrRoad}"  />
			</td>  
		</tr>
		<tr>
		<td width="30%" align="left">
				<fmt:message key="PPROC.AREALOCLITY" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   
			   <input type="text" name='txtAddrAreaLocality' id="txtAddrAreaLocality" value="${resValue.lStrArea}"  />
			</td>  
		</tr>  
	<tr>
			<td width="30%" align="left">
			<fmt:message key="PPROC.STATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			<select name="cmbAddrState" id="cmbAddrState"  style="width:70%"  >
			
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:forEach var="State" items="${resValue.lLstState}">
					 <c:choose>
				         	<c:when test="${State.id == resValue.lStrState}">
				         	<option selected="true" value="${State.id}">
								<c:out value="${State.desc}"></c:out>
							</option>
							</c:when>	
							<c:otherwise>
									<option value="${State.id}">
							<c:out value="${State.desc}"></c:out>									
							</option>
							</c:otherwise>	
							</c:choose>	
						
					</c:forEach>
			</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
		</td>
		</tr>
		<tr>
         <td width="30%" align="left">
				<fmt:message key="PPROC.TOWNCITYDIST" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   <select name="cmbAddrTownCityDist" id="cmbAddrTownCityDist" style="width:60%"   >
				        
				         <c:forEach var="District" items="${resValue.lLstDistricts}">
				         <c:choose>
				         	<c:when test="${District.id == resValue.lStrDistrict}">
				         	<option selected="true" value="${District.id}">
								<c:out value="${District.desc}"></c:out>
							</option>
							</c:when>	
							<c:otherwise>
									<option value="${District.id}">
										<c:out value="${District.desc}"></c:out>
									</option>
							</c:otherwise>	
							</c:choose>				
						</c:forEach>
				      </select>
				      
			</td>  
		</tr> 
		<tr>
		<td width="30%" align="left">
				<fmt:message key="PPROC.PINCODE" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtAddrPincode' id="txtAddrPincode" maxlength="6" style="text-align: right" onKeyPress="numberFormat(event)" value="${resValue.lStrPincode}" />
		</td>
		</tr>
		<tr>
		<td width="30%" align="left">
				<fmt:message key="PPROC.LANDLINENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtAddrLandlineNo' id="txtAddrLandlineNo" style="text-align: right" onKeyPress="numberFormat(event)" value="${resValue.lStrLandLineNo}" />
		</td>
		</tr>
		<tr>
		<td width="30%" align="left">
				<fmt:message key="PPROC.MOBILENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtAddrMobileNo' id="txtAddrMobileNo" onblur="chckLength(this);" style="text-align: right" onKeyPress="numberFormat(event)"  maxlength="10" value="${resValue.lStrMobileNo}"/>
		</td>
		</tr>
		<tr>
		<td width="30%" align="left">
				<fmt:message key="PPROC.EMAILID" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtAddrEmailId' id="txtAddrEmailId" value="${resValue.lStrEmailId}" size="25" onblur="validateEmailID(txtAddrEmailId,EMAILID);"/>
		</td>
		</tr>
		
		
	</table>
	</fieldset>
	<table width="100%" align="center">
	<tr>

	<td align="center">
	<c:if test="${resValue.lStrMode == 'New'}">
	<c:if test="${resValue.lStrAddress == 'Family'}">
	<hdiits:button name="btnFmlyDtlSave"	type="button" captionid="PPROC.SAVE" bundle="${pensionLabels}" onclick="return validateAddress('Family')" />
	</c:if>
	<c:if test="${resValue.lStrAddress == 'Nominee'}">
	<hdiits:button name="btnNomDtlSave"	type="button" captionid="PPROC.SAVE" bundle="${pensionLabels}" onclick="return validateAddress('Nominee')" />
	</c:if>
	</c:if>
	
	<hdiits:button name="btnClose"	type="button" captionid="PPROC.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
	</td>
	</tr>
	
	
	</table>
	
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=loadDistricts" source="cmbAddrState" target="cmbAddrTownCityDist" parameters="state={cmbAddrState}"></ajax:select>
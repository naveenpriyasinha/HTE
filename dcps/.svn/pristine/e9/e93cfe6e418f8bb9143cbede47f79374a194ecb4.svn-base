<%try{ %>


<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseFamilyDtls.js"></script>
<script type="text/javascript"  src="script/common/pramukhlib.js"></script>
<script type="text/javascript">

CASETYPE='<fmt:message key="PPROC.CASETYPE" bundle="${pensionAlerts}"></fmt:message>';
PAYCOMSN='<fmt:message key="PPROC.PAYCOMSN" bundle="${pensionAlerts}"></fmt:message>';
CLSOFPNSN = '<fmt:message key="PPROC.CLSOFPNSN" bundle="${pensionAlerts}"></fmt:message>';
SEVAARTHID = '<fmt:message key="PPROC.SEVAARTHID" bundle="${pensionAlerts}"></fmt:message>';
PNSNCATGRY = '<fmt:message key="PPROC.PNSNCATGRY" bundle="${pensionAlerts}"></fmt:message>';
DDOCODE = '<fmt:message key="PPROC.DDOCODE" bundle="${pensionAlerts}"></fmt:message>';
NAME = '<fmt:message key="PPROC.NAME" bundle="${pensionAlerts}"></fmt:message>';
DATEOFRETMNT= '<fmt:message key="PPROC.DTOFRETMNT" bundle="${pensionAlerts}"></fmt:message>';
DATEOFBIRTH = '<fmt:message key="PPROC.DTOFBIRTH" bundle="${pensionAlerts}"></fmt:message>';
DATEOFSTARTSERV = '<fmt:message key="PPROC.DTOFSTARTSERV" bundle="${pensionAlerts}"></fmt:message>';
HEIGHTFEET = '<fmt:message key="PPROC.HEIGHTFEET" bundle="${pensionAlerts}"></fmt:message>';
HEIGHTINCH = '<fmt:message key="PPROC.HEIGHTINCH" bundle="${pensionAlerts}"></fmt:message>';
GROUP = '<fmt:message key="PPROC.GROUP" bundle="${pensionAlerts}"></fmt:message>';
DESIGNATION = '<fmt:message key="PPROC.DESIGNATION" bundle="${pensionAlerts}"></fmt:message>';
EMAILID = '<fmt:message key="PPROC.EMAILID" bundle="${pensionAlerts}"></fmt:message>';
DEPARTMENT = '<fmt:message key="PPROC.DEPARTMENT" bundle="${pensionAlerts}"></fmt:message>';
UID = '<fmt:message key="PPROC.UID" bundle="${pensionAlerts}"></fmt:message>';
PRTOWNCITYDIST = '<fmt:message key="PPROC.PRTOWNCITYDIST" bundle="${pensionAlerts}"></fmt:message>';
PRSTATE = '<fmt:message key="PPROC.PRSTATE" bundle="${pensionAlerts}"></fmt:message>';
AGOFFICE = '<fmt:message key="PPROC.AGOFFICE" bundle="${pensionAlerts}"></fmt:message>';
TRSRYPNS = '<fmt:message key="PPROC.TRSRYPNS" bundle="${pensionAlerts}"></fmt:message>';
AGOFFICEPAY = '<fmt:message key="PPROC.AGOFFICEPAY" bundle="${pensionAlerts}"></fmt:message>';
TRSRYPNSPAY = '<fmt:message key="PPROC.TRSRYPNSPAY" bundle="${pensionAlerts}"></fmt:message>';
DTOFEXPIRY = '<fmt:message key="PPROC.DTOFEXPIRY" bundle="${pensionAlerts}"></fmt:message>';
</script>

<script>
function setBranch(){
	//alert("Inside branch");
	document.getElementById("cmbBankName").value="-1";	
document.getElementById("cmbTargetBranchName").value="-1";


}

</script>




<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TypeOfCase" value="${resValue.lstTypeOfCase}"></c:set>
<c:set var="lObjTrnPnsnProcInwardPensionVO" value="${resValue.lObjTrnPnsnProcInwardPensionVO}"></c:set>
<c:set var="lObjTrnPnsnProcPnsnrDtlsVO" value="${resValue.lObjTrnPnsnProcPnsnrDtlsVO}"></c:set>
<c:set var="treasuryLoc" value="${resValue.treasuryLoc}"></c:set>

<c:choose>
<c:when test="${lObjTrnPnsnProcInwardPensionVO.pensionType != 'FAMILYPNSN'}">
		<c:set value="readonly='readonly'" var="varReadOnly" ></c:set>
		<c:set value="display: none;" var="varDisplayNone" ></c:set>
		<c:set value="disabled='disabled'" var="varDisabled" ></c:set>
	
</c:when>
		
<c:otherwise>
		<c:set value="readonly='readonly'" var="varReadOnlyFamily" ></c:set>
		<c:set value="" var="varDisabled" ></c:set>
		<c:set value="display: none;" var="varDisplayNoneFamily" ></c:set>
</c:otherwise>	
</c:choose>
<input type="hidden" name="inwardPensionId" id="inwardPensionId" value="${lObjTrnPnsnProcInwardPensionVO.inwardPensionId}"/>

<input type="hidden" name="hdnCurDate" id="hdnCurDate" value="${resValue.lDtCurDate}" />
<input type="hidden" name="hidTotalyear" id="hidTotalyear" value="" />
<input type="hidden" name="hidTotalMonth" id="hidTotalMonth" value="" />
<input type="hidden" name="hidTotalDays" id="hidTotalDays" value="" />
<input type="hidden" name="hdnTrsryFlag" id="hdnTrsryFlag" value="" />
<fieldset style="width:100%" class="tabstyle">
<legend id="headingMsg"><b>
<fmt:message key="PENSIONERDTLS" bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="97%" align="center">
	   <tr>
	   <td width="20%" align="left">
				<fmt:message key="PPROC.NAME" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			  <input type="text" id="txtPnsnrName" readonly="readonly" style="text-transform: uppercase" size="40"  maxlength="99" name="txtPnsnrName"  value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrName}" onfocus="onFocus(this)"  onblur="onBlur(this);isName(this,'Please enter valid Name');"/>
			  <label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
			<td width="20%" align="left">
				<fmt:message key="PPROC.NAMEINMARATHI" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			  <input type="text" id="txtPnsnrNameInMarathi" readonly="readonly" style="text-transform: uppercase" size="20" maxlength="99" name="txtPnsnrNameInMarathi" onfocus="onFocus(this)"  onblur="onBlur(this)" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrNameInMarathi}"/>
			  <script language="javascript" type="text/javascript">
					
						pph = new PramukhPhoneticHandler();
						pph.convertToIndicIME("txtPnsnrNameInMarathi", document.getElementById('txtNameInDevanagari'),'devanagari');
					
					</script>
			</td>
		</tr>
		<tr>
		<td width="20%" align="left" style="text-align: left">
				<fmt:message key="PPROC.GENDER" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		<c:choose>
		<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.genderFlag == 70}">
		     <input type="radio" id="radioMaleFemale" name="radioMaleFemale" value="M" maxlength="1" />
			 <fmt:message key="PPROC.MALE"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioMaleFemale" name="radioMaleFemale" value="F" checked="checked" maxlength="1" />
			 <fmt:message key="PPROC.FEMALE"	bundle="${pensionLabels}"></fmt:message>
			 
			  <input type="radio" id="radioMaleFemale" name="radioMaleFemale" value="T" maxlength="1" />
			 <fmt:message key="PPROC.TRANSGENDER"	bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.genderFlag == 84}">
		     <input type="radio" id="radioMaleFemale" name="radioMaleFemale"  value="M"  maxlength="1" />
			 <fmt:message key="PPROC.MALE"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioMaleFemale" name="radioMaleFemale" value="F" maxlength="1"  />
			 <fmt:message key="PPROC.FEMALE"	bundle="${pensionLabels}"></fmt:message>
			 
			  <input type="radio" id="radioMaleFemale" name="radioMaleFemale" value="T" maxlength="1" checked="checked" />
			 <fmt:message key="PPROC.TRANSGENDER"	bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:otherwise>
		<input type="radio" id="radioMaleFemale" name="radioMaleFemale" maxlength="1" value="M" checked="checked" />
			 <fmt:message key="PPROC.MALE"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioMaleFemale" name="radioMaleFemale" maxlength="1" value="F"   />
			 <fmt:message key="PPROC.FEMALE"	bundle="${pensionLabels}"></fmt:message>
			 
			  <input type="radio" id="radioMaleFemale" name="radioMaleFemale"  maxlength="1" value="T"  />
			 <fmt:message key="PPROC.TRANSGENDER"	bundle="${pensionLabels}"></fmt:message>
		</c:otherwise>
		</c:choose>
		    
		</td>
		<td width="20%" align="left">
				<fmt:message key="PPROC.HEIGHT" bundle="${pensionLabels}"></fmt:message>
				
		</td>
		<td>
		<input type="text" id="txtHeight" style="width: 40px"  name="txtHeight" maxlength="2"  value="${lObjTrnPnsnProcPnsnrDtlsVO.heightFeet}" onKeyPress="numberFormat(event)" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		  	  <fmt:message key="PPROC.FEET" bundle="${pensionLabels}"></fmt:message>
		  	  <input type="text" id="txtInches" style="width: 40px"   name="txtInches" maxlength="2"  value="${lObjTrnPnsnProcPnsnrDtlsVO.heightInches}" onKeyPress="numberFormat(event)" onfocus="onFocus(this)"  onblur="onBlur(this)" />
		  	  <fmt:message key="PPROC.INCHES" bundle="${pensionLabels}"></fmt:message>		  	  
	    </td>
		</tr>
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.DATEOFBIRTH" bundle="${pensionLabels}"></fmt:message>
				
		</td>
		<td width="30%" align="left">
			   <input type="text" name="txtDateOfBirth" id="txtDateOfBirth" readonly="readonly" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.birthDate}" />" 
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
																														
					onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);greaterThanCurrDateValidation(this,'Date of Birth cannot be greater than Current Date');setRetirementDate(this);setFP1AndFp2Date(1);setEmolumnetToDate(2);compareDOBAndDoj();calActualServiceDays();"/>
						
					<label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>
			
		<td width="20%" align="left">
				<fmt:message key="PPROC.DATESTARTINGSERVICE" bundle="${pensionLabels}"></fmt:message>
				
		</td>
		<td width="30%" align="left">
			   <input type="text" name="txtDateOfStartingService" readonly="readonly" id="txtDateOfStartingService" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.joiningDate}" />"
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);calActualServiceDays();compareDOBAndDoj();validPensionType();"/>
			
					
				<label id="mandtryFinal" class="mandatoryindicator">*</label>			
<input type="hidden" name="hdnStartingServiceDate" id="hdnStartingServiceDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.joiningDate}"/>"/>
				<c:choose>
			<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.bnOrAnJoin == 'AN'}">
		    	 <input type="radio" id="radioJoinBn" name="radioJoinBnOrAn"  value="BN" onclick="bnOrAnValueJoin('BN')" />
				 <fmt:message key="PPROC.BN"	bundle="${pensionLabels}" ></fmt:message>
			
				 <input type="radio" id="radioJoinAn" name="radioJoinBnOrAn" value="AN" checked="checked" onclick="bnOrAnValueJoin('AN')"/>
				 <fmt:message key="PPROC.AN"	bundle="${pensionLabels}"></fmt:message>
			</c:when>
			<c:otherwise>
				<input type="radio" id="radioJoinBn" name="radioJoinBnOrAn"  value="BN" checked="checked" onclick="bnOrAnValueJoin('BN')" />
				 <fmt:message key="PPROC.BN"	bundle="${pensionLabels}"></fmt:message>
			
				 <input type="radio" id="radioJoinAn" name="radioJoinBnOrAn" value="AN" onclick="bnOrAnValueJoin('AN')" />
				 <fmt:message key="PPROC.AN"	bundle="${pensionLabels}"></fmt:message>
			</c:otherwise>
			</c:choose>
			<input type="hidden" id="bnOrAnJoin" name="bnOrAnJoin" >
		</td>	
			
		
		</tr>
		<tr>
			<td width="20%" align="left">
				<fmt:message key="PPROC.DATEOFRETIREMENT" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			<c:choose>
			<c:when test="${lObjTrnPnsnProcInwardPensionVO.pensionType == 'VOLUNTARY64' || lObjTrnPnsnProcInwardPensionVO.pensionType == 'VOLUNTARY65' || lObjTrnPnsnProcInwardPensionVO.pensionType == 'COMPULSORY' 
			|| lObjTrnPnsnProcInwardPensionVO.pensionType == 'RETIRING104' || lObjTrnPnsnProcInwardPensionVO.pensionType == 'RETIRING105' || lObjTrnPnsnProcInwardPensionVO.pensionType == 'COMPASSIONATE'  || lObjTrnPnsnProcInwardPensionVO.pensionType == 'COMPENSATION' || lObjTrnPnsnProcInwardPensionVO.pensionType == 'GALLANTRY'}">
			   <input type="text" name="txtDateOfRetiremt" id="txtDateOfRetiremt" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.retirementDate}" />"  
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" ${varReadOnlyFamily}
					onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);setFP1AndFp2Date(1);setEmolumnetToDate(2);setCommencementDate();calActualServiceDays();validPensionType();disableDP();"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgDtOfRetierment"
					onClick='window_open(event,"txtDateOfRetiremt",375,570)'
					style="cursor: pointer;${varDisplayNoneFamily}" />
					<label id="imgMandtryDtOfRetierment" style="${varDisplayNoneFamily}" class="mandatoryindicator">*</label>
			</c:when>
			<c:otherwise>
			<input readonly="readonly" type="text" name="txtDateOfRetiremt" id="txtDateOfRetiremt" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.retirementDate}" />"  
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" ${varReadOnlyFamily}
					onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);setFP1AndFp2Date(1);setEmolumnetToDate(2);setCommencementDate();calActualServiceDays();validPensionType();disableDP();"/>
				
					<label id="imgMandtryDtOfRetierment" style="${varDisplayNoneFamily}" class="mandatoryindicator">*</label>
			
			</c:otherwise>
			</c:choose>
				
			<c:choose>
			<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.bnOrAn == 'AN'}">
		    	 <input type="radio" id="radioBn" name="radioBnOrAn"  value="BN" onclick="bnOrAnValue('BN')" />
				 <fmt:message key="PPROC.BN"	bundle="${pensionLabels}" ></fmt:message>
			
				 <input type="radio" id="radioAn" name="radioBnOrAn" value="AN" checked="checked" onclick="bnOrAnValue('AN')"/>
				 <fmt:message key="PPROC.AN"	bundle="${pensionLabels}"></fmt:message>
			</c:when>
			<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.bnOrAn == 'BN'}">
				<input type="radio" id="radioBn" name="radioBnOrAn"  value="BN" checked="checked" onclick="bnOrAnValue('BN')" />
				 <fmt:message key="PPROC.BN"	bundle="${pensionLabels}"></fmt:message>
			
				 <input type="radio" id="radioAn" name="radioBnOrAn" value="AN" onclick="bnOrAnValue('AN')" />
				 <fmt:message key="PPROC.AN"	bundle="${pensionLabels}"></fmt:message>
		</c:when>			
			<c:otherwise>
		    	 <input type="radio" id="radioBn" name="radioBnOrAn"  value="BN" onclick="bnOrAnValue('BN')" />
				 <fmt:message key="PPROC.BN"	bundle="${pensionLabels}" ></fmt:message>
			
				 <input type="radio" id="radioAn" name="radioBnOrAn" value="AN" checked="checked" onclick="bnOrAnValue('AN')"/>
				 <fmt:message key="PPROC.AN"	bundle="${pensionLabels}"></fmt:message>
			</c:otherwise>
			</c:choose>
			<input type="hidden" id="bnOrAn" name="bnOrAn" >
			<input type="hidden" id="bnOrAnNew" name="bnOrAnNew" value="${lObjTrnPnsnProcPnsnrDtlsVO.bnOrAn}" >
			</td>
			
		</tr>
		<tr>
			<td width="20%" align="left">
				<fmt:message key="PPROC.DATEOFCOMMENCEMENT" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
				 <input type="text" name="txtDateOfCommencement" id="txtDateOfCommencement" readonly="readonly" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcInwardPensionVO.commensionDate}" />"  
						maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
						onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfRetiremt,this,'Date of Commencement should be greater than Date of Retirement.','<');validateCommencementDate();"/>
					
						<input type="hidden" name="hdnCommencementDate" id="hdnCommencementDate"/>
						<label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
			
			<td width="20%" align="left">
				<fmt:message key="PPROC.EFPYEAR" bundle="${pensionLabels}"></fmt:message>
			</td>
		    <td width="30%" align="left">		    
		    <c:choose>
		    <c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.efpYear == 89}">
		    	 <input type="radio" id="radioEfpYes" name="radioEfp"  value="Y"  checked="checked" ${varDisabled}/>
				 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
			
				 <input type="radio" id="radioEfpNo" name="radioEfp" value="N" ${varDisabled}/>
			 	<fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message> 
			</c:when>
			<c:otherwise>
			<c:if test="${lObjTrnPnsnProcPnsnrDtlsVO.efpYear == 78}">
				 <input type="radio" id="radioEfpYes" name="radioEfp"  value="Y" ${varDisabled} />
				 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
			
				 <input type="radio" id="radioEfpNo" name="radioEfp" value="N" checked="checked" ${varDisabled}/>
			 	<fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>		
			</c:if>		
			<c:if test="${lObjTrnPnsnProcPnsnrDtlsVO.efpYear == '' || lObjTrnPnsnProcPnsnrDtlsVO.efpYear == null}">
				 <input type="radio" id="radioEfpYes" name="radioEfp"  value="Y" ${varDisabled} />
				 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
			
				 <input type="radio" id="radioEfpNo" name="radioEfp" value="N" ${varDisabled}/>
			 	<fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>
			 </c:if> 
			</c:otherwise>			
			</c:choose>    
			</td>
		<tr>
			<td width="20%" align="left">
				<fmt:message key="PPROC.DATEOFEXPIRY" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">					
			   <input type="text" name="txtDateOfExpiry" id="txtDateOfExpiry" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.deathDate}" />"  
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" ${varReadOnly} 
					onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);setRetirementDate(this);setFP1AndFp2Date(2);setEmolumnetToDate(2);setCommencementDate();"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgDtOfExpiry" 
					onClick='window_open(event,"txtDateOfExpiry",375,570)'
					style="cursor: pointer;${varDisplayNone}"  />
			</td>
			<td width="20%" align="left">
				<fmt:message key="PPROC.DESIGNATION" bundle="${pensionLabels}"></fmt:message>
			</td>
		    <td width="30%" align="left">
		   
		    <select name="txtDesignation" id="txtDesignation" style="width:250px;" onfocus="onFocus(this)" onblur="onBlur(this);">
				     <c:if test="${lObjTrnPnsnProcPnsnrDtlsVO.designation == null }">
				     	    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				    </c:if>
				    <option value="-1" ><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				      <c:forEach var="desigList" items="${resValue.lLstDesignation}">
				        <c:choose>
				   	     <c:when test="${desigList.desc == lObjTrnPnsnProcPnsnrDtlsVO.designation}">
				    	     <option selected="selected" value="${desigList.desc}">
								<c:out value="${desigList.desc}"></c:out>									
							</option>
				         </c:when>
				         <c:otherwise>
				         <c:out value="${desigList.desc}"></c:out>	
				         <option value="${desigList.desc}">
								<c:out value="${desigList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
							
						</c:forEach>
			  </select>
		   
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
	    	<span id="roleIndicatorRegion" style="display: none"> <img	src="./images/busy-indicator.gif" /></span> 
		</td>
		</tr>
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.DATEOFCONFIRMATION" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
				 <input type="text" name="txtDateOfConfirmation" id="txtDateOfConfirmation" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.dateOfConfirmation}" />"  
						maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
						onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);validateDOC();"/>
					<img src='images/CalendarImages/ico-calendar.gif' width='20'
						onClick='window_open(event,"txtDateOfConfirmation",375,570)'
						style="cursor: pointer;" />
						<input type="hidden" name="hdnConfirmationDate" id="hdnConfirmationDate"/>
						<label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
		
		</tr>
		

		<tr>
	    <td width="20%">
	       <fmt:message key="PPROC.UIDNO" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="30%">
	       <input type="text" id="txtUidNo1" name="txtUidNo1" value="${fn:substring(lObjTrnPnsnProcPnsnrDtlsVO.uId,0,4)}" size="4" onKeyPress="numberFormat(this)" onKeyUp="return autoTab(this, 4, event);"  onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="32" maxlength="4"/>
	       <input type="text" id="txtUidNo2" name="txtUidNo2" value="${fn:substring(lObjTrnPnsnProcPnsnrDtlsVO.uId,4,8)}" size="4" onKeyPress="numberFormat(this)" onKeyUp="return autoTab(this, 4, event);"  onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="33" maxlength="4"/>
	       <input type="text" id="txtUidNo3" name="txtUidNo3" value="${fn:substring(lObjTrnPnsnProcPnsnrDtlsVO.uId,8,12)}" size="4" onKeyPress="numberFormat(this)"  onKeyUp="return autoTab(this, 4, event);" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="34" maxlength="4"/>	  	     
	    </td>
	    <td width="20%">
	       <fmt:message key="PPROC.EIDNO" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="30%">
	       <input type="text" id="txtEID" name="txtEID" value="${lObjTrnPnsnProcPnsnrDtlsVO.eId}" size="25" onfocus="onFocus(this)" onblur="onBlur(this);"  tabindex="35"  style="display: ;text-transform: uppercase;" maxlength="20"/>	       
	    </td>
   
	</tr>		
		<tr>
		<td width="20%">
	       <fmt:message key="PPROC.PANNO" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="30%">
	       <input type="text" id="txtPANNo" name="txtPANNo" value="${lObjTrnPnsnProcPnsnrDtlsVO.panNo}" onKeyPress="" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="" maxlength=""/>	       	  	     
	    </td>
		<td width="20%" align="left" >
				<fmt:message key="PPROC.PERSONALREMARKS" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		 <textarea   id="txtPersRemarks" name="txtPersRemarks" style="height: 20px" >${lObjTrnPnsnProcPnsnrDtlsVO.remarks}</textarea>
		</td>
		
		</tr>
</table>
<fieldset style="width:100%" class="tabstyle">
<legend id="headingMsg"><b>
<fmt:message key="PPROC.OFFICEADDRESS" bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="97%" align="center">
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.ADMINDEPT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">		
			   <select name="cmbHeadOfOff" id="cmbHeadOfOff" disabled="disabled" style="width:90%" onfocus="onFocus(this)" onblur="onBlur(this);" >
			   	   <c:if test="${lObjTrnPnsnProcPnsnrDtlsVO.hooId == null }">
				     	    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				   </c:if>
				   <c:forEach var="adminList" items="${resValue.lLstHOOFrmDept}">
				        <c:choose>
				   	     <c:when test="${adminList.id == lObjTrnPnsnProcPnsnrDtlsVO.hooId}">
				    	     <option selected="selected" value="${adminList.id}">
								<c:out value="${adminList.desc}"></c:out>									
							</option>
				         </c:when>
				         <c:otherwise>
				         <c:out value="${adminList.desc}"></c:out>	
				         <option value="${adminList.id}">
								<c:out value="${adminList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
							
						</c:forEach>				        
			 </select> <label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>	
		<td width="20%" align="left">
				<fmt:message key="PPROC.FIELDDEPT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			  <select name="cmbFieldDept" id="cmbFieldDept" disabled="disabled" style="width:90%" onfocus="onFocus(this)" onblur="onBlur(this);" >
			   		   <c:if test="${lObjTrnPnsnProcPnsnrDtlsVO.departmentId == null }">
				     	    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				   		</c:if>
				       <c:forEach var="dept" items="${resValue.lLstDepartment}">
				       <c:choose>
				       <c:when test="${dept.id == lObjTrnPnsnProcPnsnrDtlsVO.departmentId}">
				       <option selected="selected" value="${dept.id}">
								<c:out value="${dept.desc}"></c:out>
							</option>
				       </c:when>
				       <c:otherwise>
				       <option  value="${dept.id}">
								<c:out value="${dept.desc}"></c:out>
							</option>
				       </c:otherwise>
				       </c:choose>
							
					</c:forEach>
			   </select>
			   <label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>		
		</tr>
	
	  <tr>
	  <td width="20%" align="left">
				<fmt:message key="PPROC.FLATDOORBLOCK" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			     <input type="text" name='txtOffFlatDoorBlk' readonly="readonly" maxlength="30" id="txtOffFlatDoorBlk" value="${lObjTrnPnsnProcPnsnrDtlsVO.officeFlat}" onfocus="onFocus(this)" onblur="onBlur(this);"/>
			  
			</td>  
         
			<td width="20%" align="left">
				<fmt:message key="PPROC.LANDLINENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		   <td width="30%" align="left">
			 <input type="text" name='txtOffLandlineNo' id="txtOffLandlineNo" readonly="readonly" maxlength="10" value="${lObjTrnPnsnProcPnsnrDtlsVO.officeLandLineNo}" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);"/>
		    </td> 
		</tr>  
		<tr>
         <td width="20%" align="left">
				<fmt:message key="PPROC.ROADPOSTOFF" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   <input type="text" name='txtOffRoadPostOff' maxlength="50" readonly="readonly" id="txtOffRoadPostOff" value="${lObjTrnPnsnProcPnsnrDtlsVO.officeRoad}" onfocus="onFocus(this)" onblur="onBlur(this);"/>
			   
			</td> 
			<td width="20%" align="left">
				<fmt:message key="PPROC.MOBILENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		   <td width="30%" align="left">
			 <input type="text" name='txtOffMobileNo' id="txtOffMobileNo" readonly="readonly" maxlength="10" value="${lObjTrnPnsnProcPnsnrDtlsVO.officeMobileNo}" style="text-align: right" maxlength="10" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chckLength(this);"/>
		    </td> 
		</tr>
		<tr>
		   <td width="20%" align="left">
				<fmt:message key="PPROC.AREALOCLITY" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			<input type="text" name='txtOffAreaLocality' id="txtOffAreaLocality" readonly="readonly" maxlength="30" value="${lObjTrnPnsnProcPnsnrDtlsVO.officeArea}" onfocus="onFocus(this)" onblur="onBlur(this);"/>
			   
			</td> 
			<td width="20%" align="left">
				<fmt:message key="PPROC.PINCODE" bundle="${pensionLabels}"></fmt:message>
			</td>
		   <td width="30%" align="left">
			 <input type="text" name='txtOffPincode' id="txtOffPincode" readonly="readonly" value="${lObjTrnPnsnProcPnsnrDtlsVO.officePincode}" maxlength="6" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);"/>
		  <label id="mandtryFinal" class="mandatoryindicator">*</label>
		   </td>
			
		</tr> 
		<tr>
			<td width="20%" align="left">
			<fmt:message key="PPROC.STATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			<select name="cmbOffState" id="cmbOffState"  tabindex="8" onfocus="onFocus(this)" onblur="onBlur(this);" style="width:70%" ${disabled}>
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:forEach var="state" items="${resValue.lLstState}">
					<c:choose>
					<c:when test="${state.id == lObjTrnPnsnProcPnsnrDtlsVO.officeStateCode}">
					<option selected="selected" value="${state.id}">
							<c:out value="${state.desc}"></c:out>									
					</option>
					</c:when>
					<c:otherwise>
					<option value="${state.id}">
							<c:out value="${state.desc}"></c:out>									
						</option>
					</c:otherwise>
					</c:choose>
						
					</c:forEach>	
			</select>		 
		</td>
		<td width="20%" align="left">
				<fmt:message key="PPROC.TOWNCITYDIST" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			
	        <select name="cmbOffTownCityDist" id="cmbOffTownCityDist" style="width:60%" onfocus="onFocus(this)" onblur="onBlur(this);" >
				        
				         <c:choose>
						   <c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.officeStateCode != null}">
     						<c:forEach var="OffDistrictList" items="${resValue.lLstOffDistricts}">
							       <c:choose>
									<c:when test="${OffDistrictList.id == lObjTrnPnsnProcPnsnrDtlsVO.officeDistCode}">
											<option value="${OffDistrictList.id}" selected="selected">
											<c:out value="${OffDistrictList.desc}"></c:out>									
											</option>
								   </c:when>
							       <c:otherwise>
										<option value="${OffDistrictList.id}">
										<c:out value="${OffDistrictList.desc}"></c:out>									
								 		</option>
								   </c:otherwise>
						          </c:choose>
							</c:forEach>		
					       </c:when>
					    <c:otherwise>
							<c:forEach var="District" items="${resValue.lLstDistricts}">
							<option value="${District.id}">
								<c:out value="${District.desc}"></c:out>
							</option>	
							</c:forEach>		
						</c:otherwise>
						</c:choose>
					
		      </select>		      
    		</td>  
		</tr> 
		<tr>
			<td width="20%" align="left">
				<fmt:message key="PPROC.EMAILID" bundle="${pensionLabels}"></fmt:message>
			</td>
		    <td width="30%" align="left">
			 <input type="text" name='txtOffEmailId' id="txtOffEmailId" maxlength="50" value="${lObjTrnPnsnProcPnsnrDtlsVO.officeEmailAddr}" onfocus="onFocus(this)" onblur="onBlur(this);validateEmailID(txtOffEmailId,EMAILID)"/>
		    </td> 
		</tr>
     </table>
	</fieldset>


<fieldset class="tabstyle" style="width:100%">
<legend id="headingMsg"><b>
<fmt:message key="PRESENTADDR" bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="97%" align="center">
	<tr>
         <td width="20%" align="left">
				<fmt:message key="PPROC.FLATDOORBLOCK" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   <input type="text" name='txtPrFlatDoorBlk' maxlength="30" id="txtPrFlatDoorBlk"  value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrFlat}" onfocus="onFocus(this)" onblur="onBlur(this);"/>
			   
			</td>  
			<td width="20%" align="left">
				<fmt:message key="PPROC.ROADPOSTOFF" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			    <input type="text" name='txtPrRoadPostOff' maxlength="50" id="txtPrRoadPostOff"  value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRoad}" onfocus="onFocus(this)" onblur="onBlur(this);"/>
			</td>
		</tr> 
	  <tr>
	  	<td width="20%" align="left">
				<fmt:message key="PPROC.AREALOCLITY" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   
			   <input type="text" name='txtPrAreaLocality' maxlength="30" id="txtPrAreaLocality" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrArea}"  onfocus="onFocus(this)" onblur="onBlur(this);"/>
			</td>
				<td width="20%" align="left">
			<fmt:message key="PPROC.STATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
				<select name="cmbPrState" id="cmbPrState"  tabindex="8"  style="width:70%"  >
			
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:forEach var="state" items="${resValue.lLstState}">
					<c:choose>
					<c:when test="${state.id == lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrStateCode}">
					<option value="${state.id}" selected="selected">
							<c:out value="${state.desc}"></c:out>									
					</option>
					</c:when>
					<c:otherwise>
					<option value="${state.id}">
							<c:out value="${state.desc}"></c:out>									
						</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>	
				</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>		
		</td>
		</tr>
		<tr>
        <td width="20%" align="left">
				<fmt:message key="PPROC.TOWNCITYDIST" bundle="${pensionLabels}"></fmt:message>
		 </td>
			<td width="30%" align="left">
             <select name="cmbPrTownCityDist" id="cmbPrTownCityDist" style="width:60%" onfocus="onFocus(this)" onblur="onBlur(this);"  >
				        <c:choose>
							<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrStateCode != null}">
     						<c:forEach var="PrDistrictList" items="${resValue.lLstPrDistricts}">
							       <c:choose>
									<c:when test="${PrDistrictList.id == lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrDistCode}">
											<option value="${PrDistrictList.id}" selected="selected">
											<c:out value="${PrDistrictList.desc}"></c:out>									
											</option>
								   </c:when>
							    <c:otherwise>
										<option value="${PrDistrictList.id}">
										<c:out value="${PrDistrictList.desc}"></c:out>									
										</option>
								</c:otherwise>
						       </c:choose>
							</c:forEach>		
					        </c:when>
					    <c:otherwise>
							<c:forEach var="District" items="${resValue.lLstDistricts}">
							<option value="${District.id}">
								<c:out value="${District.desc}"></c:out>
							</option>	
							</c:forEach>		
						</c:otherwise>
						</c:choose>
			   </select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
			</td> 
			<td width="20%" align="left">
				<fmt:message key="PPROC.PINCODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			 <input type="text" name='txtPrPincode' id="txtPrPincode"  value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrPincode}" maxlength="6" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);"/>
		<label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td> 
		</tr>
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.LANDLINENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtLandlineNo' id="txtLandlineNo" maxlength="10" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrLandlineNo}" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);"/>
		</td>
		<td width="20%" align="left">
				<fmt:message key="PPROC.MOBILENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtMobileNo' id="txtMobileNo" maxlength="10" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrMobileNo}" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chckLength(this);" maxlength="10"/>
		</td>
		</tr>
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.EMAILID" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			 <input type="text" name='txtEmailId' id="txtEmailId"  maxlength="50" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrEmailAddr}" onfocus="onFocus(this)" onblur="onBlur(this);validateEmailID(txtEmailId,EMAILID);"/>
		</td>	
		</tr>
		
		<tr>		
		<td width="20%" align="left">
				<fmt:message key="PPROC.ISADDRAFTERRETSAME" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td>			
		<c:choose>
		<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetFlag == 78}">
		<input type="radio" id="radioIsAddrAfterRet" name="radioIsAddrAfterRet" value="Y" maxlength="3" onclick="addrAfterRetmnt(this)" />
			 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioIsAddrAfterRet" name="radioIsAddrAfterRet" value="N" maxlength="3" onclick="addrAfterRetmnt(this)" checked="checked"  />
			 <fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>
			
		</c:when>
		<c:otherwise>
		<input type="radio" id="radioIsAddrAfterRet" name="radioIsAddrAfterRet" maxlength="3" value="Y" onclick="addrAfterRetmnt(this)"  checked="checked"  />
			 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioIsAddrAfterRet" name="radioIsAddrAfterRet" value="N"  maxlength="3" onclick="addrAfterRetmnt(this)"/>
			 <fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>
			
		</c:otherwise>
		</c:choose>
			 
		</td>
		</tr>
	</table>
	</fieldset>
<fieldset style="display: none" class="tabstyle" id="fsAddrAfterRetmnt" style="width:100%">
<legend id="headingMsg"><b>
<fmt:message key="ADDRAFTERRETIRMNT" bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="97%" align="center">
		
		<tr>
         <td width="20%" align="left">
				<fmt:message key="PPROC.FLATDOORBLOCK" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			       <input type="text" name='txtARFlatDoorBlk' maxlength="30" id="txtARFlatDoorBlk" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetFlat}" onfocus="onFocus(this)" onblur="onBlur(this);"/>
			   
			</td>  
			<td width="20%" align="left">
				<fmt:message key="PPROC.ROADPOSTOFF" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			    <input type="text" name='txtARRoadPost' id="txtARRoadPost"  maxlength="50" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetRoad}" onfocus="onFocus(this)" onblur="onBlur(this);"/>
			   
			</td>  
		</tr>
		<tr>
         <td width="20%" align="left">
				<fmt:message key="PPROC.AREALOCLITY" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			<input type="text" name='txtARAreaLocality' id="txtArAreaLocality" maxlength="30" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetArea}"  onfocus="onFocus(this)" onblur="onBlur(this);"/>
			   
			</td>
				<td width="20%" align="left">
			<fmt:message key="PPROC.STATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			<select name="cmbARState" id="cmbARState"  tabindex="8" onfocus="onFocus(this)" onblur="onBlur(this);" style="width:70%" ${disabled}>
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:forEach var="state" items="${resValue.lLstState}">
					<c:choose>
					  <c:when test="${state.id == lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetStateCode}">
					   <option value="${state.id}" selected="selected">
							<c:out value="${state.desc}"></c:out>									
					   </option>
					  </c:when>
					<c:otherwise>
					<option value="${state.id}">
							<c:out value="${state.desc}"></c:out>									
					</option>
					</c:otherwise>
					</c:choose>
						
					</c:forEach>
			</select>
		</td>  
		</tr>
	  <tr>
         <td width="20%" align="left">
				<fmt:message key="PPROC.TOWNCITYDIST" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			     <select name="cmbARTownCityDist" id="cmbARTownCityDist" style="width:60%" onfocus="onFocus(this)" onblur="onBlur(this);" >
				        
				         <c:choose>
							<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetDistCode != null}">
     						<c:forEach var="ArDistrictList" items="${resValue.lLstArDistricts}">
							       <c:choose>
									<c:when test="${ArDistrictList.id == lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetDistCode}">
											<option value="${ArDistrictList.id}" selected="selected">
											<c:out value="${ArDistrictList.desc}"></c:out>									
											</option>
								   </c:when>
							    <c:otherwise>
										<option value="${ArDistrictList.id}">
										<c:out value="${ArDistrictList.desc}"></c:out>									
										</option>
								</c:otherwise>
						       </c:choose>
							</c:forEach>		
					        </c:when>
					      <c:otherwise>
							<c:forEach var="District" items="${resValue.lLstDistricts}">
							<option value="${District.id}">
								<c:out value="${District.desc}"></c:out>
							</option>	
							</c:forEach>		
						  </c:otherwise>
						</c:choose>
			     </select>
    		</td>  
    		<td width="20%" align="left">
				<fmt:message key="PPROC.PINCODE" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtARPincode' id="txtARPincode"  value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetPincode}" maxlength="6" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);" />
		</td>
		</tr> 
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.LANDLINENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtARLandlineNo' id="txtARLandlineNo" maxlength="10" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetLandlineNo}" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);"/>
		</td>
		<td width="20%" align="left">
				<fmt:message key="PPROC.MOBILENO" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
			 <input type="text" name='txtARMobileNo' id="txtARMobileNo" maxlength="10" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetMobileNo}" style="text-align: right" onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chckLength(this);" maxlength="10"/>
		</td>
		</tr>		
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.EMAILID" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			 <input type="text" name='txtAREmailId' id="txtAREmailId"  maxlength="50" value="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetEmailAddr}" onfocus="onFocus(this)" onblur="onBlur(this);validateEmailID(txtAREmailId,EMAILID);"/>
		</td>
		</tr>		
		
	</table>
	</fieldset>	
	<c:if test="${lObjTrnPnsnProcPnsnrDtlsVO.pnsnrAddrRetFlag == 78}">
	
				<script type="text/javascript">
				
					document.getElementById('fsAddrAfterRetmnt').style.display='inline';
					enableEFPYear();
				</script>
	</c:if>
</fieldset>
<fieldset style="width:100%;" class="tabstyle">
<legend id="headingMsg"><b>
<fmt:message key="OFFICEDTLS" bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="50%" align="left">
		<tr style="display: none;">
			<td width="20%" align="left">
				<fmt:message key="PPROC.CHANGETREASURY" bundle="${pensionLabels}"></fmt:message>
			</td>
		<td width="30%" align="left">
		<c:choose>
		<c:when test="${lObjTrnPnsnProcPnsnrDtlsVO.changeTrsryFlag == 89}">
		 <input type="radio" id="radioIsTreasuryChangeYes" maxlength="3" name="radioIsTreasuryChange" value="Y" onclick="trsryChange(this);" checked="checked" />
			 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioIsTreasuryChangeNo" maxlength="3" name="radioIsTreasuryChange" value="N" onclick="trsryChange(this);" />
			 <fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:otherwise>
		 <input type="radio" id="radioIsTreasuryChangeYes" maxlength="3" name="radioIsTreasuryChange" value="Y" onclick="trsryChange(this);"  />
			 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioIsTreasuryChangeNo" maxlength="3" name="radioIsTreasuryChange" value="N" onclick="trsryChange(this);" checked="checked" />
			 <fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>
		</c:otherwise>
		</c:choose>
			
		</td>
		<td width="20%" align="left"></td>
		<td width="30%" align="left"></td>
		</tr>
		<tr style="display: none;">
		 <td width="20%" align="left"><fmt:message key="PPROC.AGOFFICE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left"><select name="cmbAgOfficeForPension"
			id="cmbAgOfficeForPension" style="width: 77%"  tabindex="5" >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			
			<c:choose>
				<c:when test="${lObjTrnPnsnProcInwardPensionVO.agOfficePension == 'AG Mumbai'}">
					<option value="<fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:when test="${lObjTrnPnsnProcInwardPensionVO.agOfficePension == 'AG Nagpur'}">
					<option value="<fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:otherwise>
					<option value="<fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>
				</c:otherwise>
			</c:choose>
						
		    </select><label id="mandtryFinal" class="mandatoryindicator">*</label>
		    </td>
		    <td width="20%" align="left" style="display: none;"><fmt:message key="PPROC.AGOFFICEAFTRPAY" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left" style="display: none;"><select name="cmbAgOffice"
			id="cmbAgOffice" style="width: 77%;"  tabindex="5" disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			
			<c:choose>
				<c:when test="${lObjTrnPnsnProcInwardPensionVO.agOfficeAftrFirstPay == 'AG Mumbai'}">
					<option value="<fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:when test="${lObjTrnPnsnProcInwardPensionVO.agOfficeAftrFirstPay == 'AG Nagpur'}">
					<option value="<fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:otherwise>
					<option value="<fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/>"><fmt:message key="PPROC.AGNAGPUR" bundle="${pensionLabels}"/></option>
				</c:otherwise>
			</c:choose>
						
		    </select></td>
		 
		</tr>
		<tr>
	<!--  
		-->	
		    
		</tr>
		
<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.TREASURYFORPENSION" bundle="${pensionLabels}"></fmt:message>
		</td>
				<td width="30%" align="left"><select name="cmbTreasuryForPension" id="cmbTreasuryForPension" style="width:77%" onfocus="onFocus(this)" onblur="onBlur(this);" onchange="setBranch();" >
				         <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"  /></option>
				          <c:forEach var="TreasuryForPension" items="${resValue.lLstTreasury}">
				            <c:choose>
				              <c:when test="${TreasuryForPension.id == lObjTrnPnsnProcInwardPensionVO.trsryIdPension }">
				              <option selected="selected" value="${TreasuryForPension.id}">
						  	    <c:out value="${TreasuryForPension.desc}"></c:out>
						      </option>
				          </c:when>
				          <c:otherwise>
				          <option  value="${TreasuryForPension.id}">
							<c:out value="${TreasuryForPension.desc}"></c:out>
					      </option>
				          </c:otherwise>
				          </c:choose>
						
					</c:forEach>
			   </select><label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>	
		<td width="20%" align="left" style="display: none;">
				<fmt:message key="PPROC.TREASURY" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left" style="display: none;"> <select name="cmbTreasury" id="cmbTreasury" style="width:77%;" onfocus="onFocus(this)" onblur="onBlur(this);" disabled="disabled">
				         <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				          <c:forEach var="Treasury" items="${resValue.lLstTreasury}">
				            <c:choose>
				              <c:when test="${Treasury.id == lObjTrnPnsnProcInwardPensionVO.trsryIdAftrFirstPay}">
				              <option selected="selected" value="${Treasury.id}">
						  	    <c:out value="${Treasury.desc}"></c:out>
						      </option>
				          </c:when>
				          <c:otherwise>
				          <option  value="${Treasury.id}">
							<c:out value="${Treasury.desc}"></c:out>
					      </option>
				          </c:otherwise>
				          </c:choose>
						
					</c:forEach>
			   </select>
		</td>	
	</tr>
</table>
	

</fieldset>
<fieldset style="width:100%" class="tabstyle">
<legend id="headingMsg"><b>
<fmt:message key="BANKDETAILS" bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="97%" align="center">
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.BANK" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		<select name="cmbBankName" id="cmbBankName" style="width:250px;" onfocus="onFocus(this)" onblur="onBlur(this);">
				    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				      <c:forEach var="BankNames" items="${resValue.lLstBankNames}">
				       <c:choose>
				         <c:when test="${BankNames.id == lObjTrnPnsnProcPnsnrDtlsVO.bankName}">
				         <option selected="selected" value="${BankNames.id}"><c:out
												value="${BankNames.desc}" />
					   	 </option>
				         </c:when>
				         <c:otherwise>
				         	<option value="${BankNames.id}"><c:out value="${BankNames.desc}"/></option>
				         </c:otherwise>
				       </c:choose>
				      </c:forEach>
</select>			  
		</td>
		<td width="20%" align="left">
				<fmt:message key="PPROC.BRANCH" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		<select name="cmbTargetBranchName" id="cmbTargetBranchName" style="width:250px;" onchange="getIfscCodeFromBrachCode()" onfocus="onFocus(this)">
				    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				      <c:forEach var="branchList" items="${resValue.lLstBankBranch}">
				       <c:choose>
				         <c:when test="${branchList.id == lObjTrnPnsnProcPnsnrDtlsVO.bankBranchName}">
				         <option selected="selected" value="${branchList.id}"><c:out
												value="${branchList.desc}" />
					   	 </option>
				         </c:when>
				         <c:otherwise>
				         	<option value="${branchList.id}"><c:out value="${branchList.desc}"/></option>
				         </c:otherwise>
				       </c:choose>
				      </c:forEach>
			   </select>

		</td>
		</tr>	
		<tr>
		<td width="20%" align="left">
				<fmt:message key="PPROC.IFSCCODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			   <input type="text" id="txtIFSCCode" maxlength="20" value="${lObjTrnPnsnProcPnsnrDtlsVO.bankIfscCode}" readonly="readonly" style="text-transform: uppercase" size="20"  name="txtIFSCCode"  onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		</td>
		<td width="20%" align="left">
				<fmt:message key="PPROC.BANKADDRESS" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">			   
			  <textarea  id="txtBankAddress" name="txtBankAddress"  style="height: 40px">${lObjTrnPnsnProcPnsnrDtlsVO.bankAddress}</textarea>
		</td>	
		</tr>
		<tr>
			<td width="20%" align="left">
				<fmt:message key="PPROC.ACCOUNTNO" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
			   <input type="text" id="txtActNo" maxlength="20" value="${lObjTrnPnsnProcPnsnrDtlsVO.bankAccountNo}" style="text-transform: uppercase" size="20"  name="txtActNo"  onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		
		</td>
		</tr>
	</table>
	
</fieldset>
<ajax:autocomplete source="txtDesignation" target="txtDesignation"
	baseUrl="ifms.htm?actionFlag=getDesgListForPensionCase"
	parameters="searchKey={txtDesignation}" className="autocomplete"
	minimumCharacters="3" indicator="roleIndicatorRegion" />
<jsp:include page="/WEB-INF/jsp/pensionproc/pensionerPhotoSignAttachment.jsp" />

<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getHOOFromDepartment" source="cmbHeadOfOff" target="cmbFieldDept" parameters="department={cmbHeadOfOff}" ></ajax:select>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=loadDistricts" source="cmbPrState" target="cmbPrTownCityDist" parameters="state={cmbPrState}" ></ajax:select>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=loadDistricts" source="cmbARState" target="cmbARTownCityDist" parameters="state={cmbARState}"></ajax:select>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=loadDistricts" source="cmbOffState" target="cmbOffTownCityDist" parameters="state={cmbOffState}"></ajax:select>
<ajax:select source="cmbBankName" 
		target="cmbTargetBranchName" 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchesOfBank"
		eventType="change" 
		parameters="bank={cmbBankName},treasuryCode={cmbTreasuryForPension} ">
</ajax:select>


<script>
//getNextDate();
</script>
 
<%}catch(Exception e){
e.printStackTrace();
}%>

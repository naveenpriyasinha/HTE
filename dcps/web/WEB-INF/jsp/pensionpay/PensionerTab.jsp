<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript"  src="script/common/pramukhlib.js"></script>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"	var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="trnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}"></c:set>
<c:set var="mstPensionerHdrVO" value="${resValue.MstPensionerHdrVO}"></c:set>
<c:set var="mstPensionerDtlsVO" value="${resValue.MstPensionerDtlsVO}"></c:set>
<c:set var="read" value="disabled" />

<script type="text/javascript">
LISTDAIN='<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>'
	+'<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}" />" selected="selected"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>';
</script>

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.PERSONALDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
 <table width="100%">	
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.FULLNAMEPNSNR" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrName" name="txtPnsnrName" value="${mstPensionerHdrVO.firstName}"   onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" tabindex="11" size="40" maxlength="100"/>
	       <label id="mandtryFinal" class="mandatoryindicator">*</label>
	    </td>
	    <td>
	    <fmt:message key="PPMT.FHNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td>
	    <input type="text" id="txtFatherOrHusbandName" name="txtFatherOrHusbandName" value="${mstPensionerHdrVO.pnsnrFatherName}" onkeypress="upperCase(event)" onfocus="onFocus(this)"  onblur="onBlur(this);" tabindex="12" size="35" maxlength="70"/>
	    </td>
	 </tr>  
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.NAMEINMARATHI" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrNameInMarathi" name="txtPnsnrNameInMarathi" value="${mstPensionerHdrVO.pnsnrNameInMarathi}" onfocus="onFocus(this)"  onblur="onBlur(this);" tabindex="13" size="40" maxlength="70"/>
	       <script language="javascript" type="text/javascript">
					
						pph = new PramukhPhoneticHandler();
						pph.convertToIndicIME("txtPnsnrNameInMarathi", document.getElementById('txtPnsnrNameInMarathi'),'devanagari');
		   </script>
	       
	    </td>
	    
	    <td width="15%">
	       <fmt:message key="PPMT.GENDER" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    	<c:choose>
	    	<c:when test="${mstPensionerHdrVO.gender == 'T'}">
	        <fmt:message key="PPMT.MALE" bundle="${pensionLabels}" ></fmt:message>
			<input type="radio" id="radioGenderM" name="radioGender" value="M" tabindex="14"/>
			<fmt:message key="PPMT.FEMALE" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderF" name="radioGender" value="F" />
			<fmt:message key="PPMT.TRANSGENDER" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderT" name="radioGender" value="T" checked="checked" onclick="confirm('Are you sure?');"/>
			</c:when>
			<c:when test="${mstPensionerHdrVO.gender == 'F'}">
	        <fmt:message key="PPMT.MALE" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderM" name="radioGender" value="M" tabindex="15"/>
			<fmt:message key="PPMT.FEMALE" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderF" name="radioGender" value="F" checked="checked"/>
			<fmt:message key="PPMT.TRANSGENDER" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderT" name="radioGender" value="T" onclick="confirm('Are you sure?');" />
			</c:when>
			<c:otherwise>
	         <fmt:message key="PPMT.MALE" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderM" name="radioGender" value="M" checked="checked" tabindex="16"/>
			<fmt:message key="PPMT.FEMALE" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderF" name="radioGender" value="F" />
			<fmt:message key="PPMT.TRANSGENDER" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioGenderT" name="radioGender" value="T" onclick="confirm('Are you sure?');"/>
			</c:otherwise>
			
			</c:choose>
	    </td>
	    
	</tr>
			
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.PENSNRTYPE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <select name="cmbPensionerType" id="cmbPensionerType"   style="width: 37%" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="17" onchange="onChangePnsnrType();">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="classType" items="${resValue.lLstClassType}">
							<c:choose>
								<c:when test="${classType.lookupShortName == mstPensionerHdrVO.classType}">
									<option selected="selected" value='${classType.lookupShortName}'>
										<c:out value="${classType.lookupDesc}"></c:out>
									</option>
								</c:when>
								<c:otherwise>
									<option value='${classType.lookupShortName}'>
										<c:out value="${classType.lookupDesc}"></c:out>									
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
			
		    </select>       
		    <input type="text" name="txtOtherPnsnrType" id="txtOtherPnsnrType" value="${mstPensionerHdrVO.otherPnsnrType}" style="display:none" maxlength="25">
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.DOB" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" name="txtDateOfBirth" id="txtDateOfBirth" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${mstPensionerHdrVO.dateOfBirth}" />"
	       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);setRetirementDate(this);" tabindex="18"/>
	       <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtDateOfBirth",375,570)'style="cursor: pointer;" ${disabled}/>
			<label id="mandtryFinal" class="mandatoryindicator">*</label>					        
	    </td>
	    <c:if test="${mstPensionerHdrVO.classType == 'Others'}">
	    <script>
	    	document.getElementById("txtOtherPnsnrType").style.display="inline";
	    </script>
	    </c:if>
	</tr>
	
	<tr>
	 <td width="15%">
	       <fmt:message key="PPMT.DOJAVAILABLE" bundle="${pensionLabels}"></fmt:message>
	 </td>	
	 <td width="35%">
	      <select name="cmbDojAvailable" id="cmbDojAvailable" onfocus="onFocus(this)" onblur="onBlur(this);onBlurDojAvailable();" onchange="onChangeDojAvaliable();"  style="width: 37%" tabindex="19">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
				<c:choose>
					<c:when test="${mstPensionerHdrVO.dojAvailable == 'Y'}">
					<option value="Y" selected="selected"><fmt:message key="DOJ.AVAILABLE" bundle="${pensionConstants}"/></option>
					<option value="N"><fmt:message key="DOJ.NOTAVAILABLE" bundle="${pensionConstants}"/></option>
					</c:when>
					<c:when test="${mstPensionerHdrVO.dojAvailable == 'N'}">
					<option value="Y"><fmt:message key="DOJ.AVAILABLE" bundle="${pensionConstants}"/></option>
					<option value="N" selected="selected"><fmt:message key="DOJ.NOTAVAILABLE" bundle="${pensionConstants}"/></option>
					</c:when>
					<c:otherwise>
					<option value="Y"><fmt:message key="DOJ.AVAILABLE" bundle="${pensionConstants}"/></option>
					<option value="N"><fmt:message key="DOJ.NOTAVAILABLE" bundle="${pensionConstants}"/></option>
					</c:otherwise>
				</c:choose>
		  </select>
		  <label id="mandtryFinal" class="mandatoryindicator">*</label>	
	    </td>
	   <td width="15%">
	       <fmt:message key="PPMT.DOJ" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtDateOfJoining" name="txtDateOfJoining" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${mstPensionerHdrVO.dateOfJoin}" />"
	       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" disabled="disabled"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfBirth,this,JOININGBIRTHDT,'<');compareDates(txtDateOfBirth,this,JOININGBIRTHDT,'=');chkDateDiffInYear(txtDateOfBirth,this);onBlurDoj();" tabindex="20"/>
	       <img id="imgDateOfJoining" src='images/CalendarImages/ico-calendar.gif' disabled="disabled"
					        onClick='window_open("txtDateOfJoining",375,570)'style="cursor: pointer;" ${disabled}/>
				        
	    </td>
	    
	    <c:if test="${mstPensionerHdrVO.dojAvailable == 'Y'}">
	    <script>
	      document.getElementById("txtDateOfJoining").disabled=false;
	      document.getElementById("imgDateOfJoining").disabled=false;
	    </script>
	    </c:if>
	    
	</tr>
	
	<tr>
	<td width="15%">
	       <fmt:message key="PPMT.DOR" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtDateOfRetirement" name="txtDateOfRetirement" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${mstPensionerHdrVO.dateOfRetirement}" />"
	       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="21"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfJoining,this,RETJOINDT,'<');compareDates(txtDateOfJoining,this,RETJOINDT,'=');getNextDate();onChangeROPType();getHeadCodeDesc();calculateDPAndDAAmounts();calcReducedPension();"/>
	       <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtDateOfRetirement",375,570)'style="cursor: pointer;" ${disabled}/>
	    <label id="mandtryFinal" class="mandatoryindicator">*</label>
	    </td>
	<td width="15%">
	       <fmt:message key="PPMT.COMMENCEMENT" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtDateOfCommencement" name="txtDateOfCommencement" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.commensionDate}" />"
	       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="22"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfRetirement,this,CMNDATEGTRETDATE,'<');validateCommencementDate();"/>
	       <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtDateOfCommencement",375,570)'style="cursor: pointer;" ${disabled}/>
		<input type="hidden" name="hdnCommencementDate" id="hdnCommencementDate"/>
	    <label id="mandtryFinal" class="mandatoryindicator">*</label>
	    </td>
	</tr>
	<tr>
	<td width="15%">
	       <fmt:message key="PPMT.ALIVE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <select name="cmbAlive" id="cmbAlive" style="width: 37%" onchange="onChangeAlive();" tabindex="23">
	       <c:choose>
	       <c:when test="${mstPensionerHdrVO.aliveFlag == 'Y'}">
	       		<option value="Y" selected="selected"><fmt:message key="PPMT.YES" bundle="${pensionLabels}" /></option>
	       		<option value="N"><fmt:message key="PPMT.NO" bundle="${pensionLabels}"/></option>
	       </c:when>
	       <c:when test="${mstPensionerHdrVO.aliveFlag == 'N'}">
	       		  <option value="Y" ><fmt:message key="PPMT.YES" bundle="${pensionLabels}" /></option>
	     		  <option value="N" selected="selected"><fmt:message key="PPMT.NO" bundle="${pensionLabels}"/></option>
		   </c:when>
		   <c:otherwise>
		  		 <option value="Y" selected="selected"><fmt:message key="PPMT.YES" bundle="${pensionLabels}" /></option>
		     	 <option value="N"><fmt:message key="PPMT.NO" bundle="${pensionLabels}"/></option>
		   </c:otherwise>	
		   </c:choose>
		   </select>
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
	    </td>
	<td width="15%">
	       <fmt:message key="PPMT.DOE" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtDateofExpiry" name="txtDateofExpiry" disabled="disabled" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${mstPensionerHdrVO.dateOfDeath}" />"
	       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="24"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfRetirement,this,EXPIRYDATELTRETDATE,'<');onBlurDateofExpiry();"/>
	       <img id="imgDateofExpiry" ${read}  src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtDateofExpiry",375,570)'style="cursor: pointer;" />	    
	    </td>
	   
		    	    
	</tr>
	 <c:if test="${trnPensionRqstHdrVO.pensionType == 'Family Pension'}">
		<script type="text/javascript">
			document.getElementById("cmbAlive").disabled=true;
			document.getElementById("txtDateofExpiry").disabled=false;  
			document.getElementById("imgDateofExpiry").disabled=false;
   		 </script>
     </c:if>
	<tr>
	   <td width="15%">
	       <fmt:message key="PPMT.PPOENDDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtPpoEndDate" name="txtPpoEndDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.endDate}" />"
		           onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="25"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfCommencement,this,PPOENDDTGRTCOMDT,'<');compareDates(txtDateOfCommencement,this,PPOENDDTGRTCOMDT,'=');"/>
		      <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtPpoEndDate",375,570)'style="cursor: pointer;" />		
		 </td> 
	   <td width="15%">
	       <fmt:message key="PPMT.HEIGHT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	     	<select id="cmbCmsFtInch" name="cmbCmsFtInch" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="26" onchange="onChangeHeight();">
	         <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
	         <c:choose>
	         	<c:when test="${resValue.HeightIn == 'Cms'}">
	         		<option value="<fmt:message key="HEIGHT.CMS" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="HEIGHT.CMS" bundle="${pensionConstants}"/></option>
	         		<option value="<fmt:message key="HEIGHT.FTINCHES" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.FTINCHES" bundle="${pensionConstants}"/></option>
	         	</c:when>
	         	<c:when test="${resValue.HeightIn == 'Feet/Inches'}">
	         		<option value="<fmt:message key="HEIGHT.CMS" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.CMS" bundle="${pensionConstants}"/></option>
	         		<option value="<fmt:message key="HEIGHT.FTINCHES" bundle="${pensionConstants}"/>"  selected="selected"><fmt:message key="HEIGHT.FTINCHES" bundle="${pensionConstants}"/></option>
	         	</c:when>
	         	<c:otherwise>
	         		<option value="<fmt:message key="HEIGHT.CMS" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.CMS" bundle="${pensionConstants}"/></option>
	         		<option value="<fmt:message key="HEIGHT.FTINCHES" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.FTINCHES" bundle="${pensionConstants}"/></option>
	         	</c:otherwise>
	         </c:choose>
	         
	        </select>
	       	
	        <select id="txtHeightInCms" name="txtHeightInCms" value="${resValue.HeightInCms}" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="27" style="display:none;width: 80px">
	       	 <c:forEach begin="1" end="225" varStatus="Counter">
	       	 <c:choose>
		       	 <c:when test="${Counter.index == resValue.HeightInCms}">
		       	     <option value="${Counter.index}" selected="selected">${Counter.index}</option>
		       	 </c:when>
		       	 <c:otherwise>
		       		 <option value="${Counter.index}">${Counter.index}</option> 
		       	 </c:otherwise>
	       	 </c:choose>
         	 </c:forEach>
			</select>	
	        
	        <select id="cmbHeightInFt" name="cmbHeightInFt" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="28" style="width: 15%" style="display:none">
	         <option value="<fmt:message key="HEIGHT.1FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.1FT" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.2FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.2FT" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.3FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.3FT" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.4FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.4FT" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.5FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.5FT" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.6FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.6FT" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.7FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.7FT" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.8FT" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.8FT" bundle="${pensionConstants}"/></option>
	        </select>
	        <select id="cmbHeightInInch" name="cmbHeightInInch" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="29" style="width: 15%" style="display:none">
	         <option value="<fmt:message key="HEIGHT.0INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.0INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.1INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.1INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.2INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.2INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.3INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.3INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.4INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.4INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.5INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.5INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.6INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.6INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.7INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.7INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.8INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.8INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.9INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.9INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.10INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.10INCH" bundle="${pensionConstants}"/></option>
	         <option value="<fmt:message key="HEIGHT.11INCH" bundle="${pensionConstants}"/>"><fmt:message key="HEIGHT.11INCH" bundle="${pensionConstants}"/></option>
	        </select> 
	    </td>
	   
	    
	    
	</tr>
<script>
onChangeHeight();
var lStrHeightInFt = "${resValue.HeightInFt}";
var lArrOpts = document.getElementById("cmbHeightInFt").options;
var heightInFt = "N";	//Flag to set select as selected if no match found.
for(var cnt = 0; cnt < lArrOpts.length ; cnt++)
{
	document.getElementById("cmbHeightInFt").value = lStrHeightInFt;
	if(document.getElementById("cmbHeightInFt").options[cnt].value == lStrHeightInFt)
	{
		document.getElementById("cmbHeightInFt").options[cnt].selected = "selected";
		heightInFt = "Y";
	}
}
if(heightInFt == "N")
{
	document.getElementById("cmbHeightInFt").options[0].selected = "selected";
}

var lStrHeightInInch = "${resValue.HeightInInch}";
var lArrOptns = document.getElementById("cmbHeightInInch").options;
var heightInInch = "N";	//Flag to set select as selected if no match found.
for(cnt = 0; cnt < lArrOptns.length ; cnt++)
{
	document.getElementById("cmbHeightInInch").value = lStrHeightInInch;
	if(document.getElementById("cmbHeightInInch").options[cnt].value == lStrHeightInInch)
	{
		document.getElementById("cmbHeightInInch").options[cnt].selected = "selected";
		heightInInch = "Y";
	}
}
if(heightInInch == "N")
{
	document.getElementById("cmbHeightInInch").options[0].selected = "selected";
}
</script>
	
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.IDMARK" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtIdentityMark" name="txtIdentityMark" value="${mstPensionerHdrVO.identityMark}" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="30" maxlength="40"/>	  	     
	    </td>
	    <td width="15%">
	    
	       <fmt:message key="PPMT.PANNO" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtPanNo" name="txtPanNo" value="${mstPensionerHdrVO.panNo}" onfocus="onFocus(this)" onblur="onBlur(this);validatePanNumber(this);" tabindex="31"  style="display: ;text-transform: uppercase;" maxlength="10"/>	       
	      
	    </td>
	   
	   
	</tr>
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.UIDNO" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtUidNo1" name="txtUidNo1" value="${fn:substring(mstPensionerHdrVO.uidNo,0,4)}" size="4" onKeyPress="numberFormat(this)" onKeyUp="return autoTab(this, 4, event);"  onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="32" maxlength="4"/>
	       <input type="text" id="txtUidNo2" name="txtUidNo2" value="${fn:substring(mstPensionerHdrVO.uidNo,4,8)}" size="4" onKeyPress="numberFormat(this)" onKeyUp="return autoTab(this, 4, event);"  onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="33" maxlength="4"/>
	       <input type="text" id="txtUidNo3" name="txtUidNo3" value="${fn:substring(mstPensionerHdrVO.uidNo,8,12)}" size="4" onKeyPress="numberFormat(this)"  onKeyUp="return autoTab(this, 4, event);" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="34" maxlength="4"/>	  	     
	    </td>
	    <td width="15%">
	    
	       <fmt:message key="PPMT.EIDNO" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtEidNo" name="txtEidNo" value="${mstPensionerHdrVO.eidNo}" size="25" onfocus="onFocus(this)" onblur="onBlur(this);"  tabindex="35"  style="display: ;text-transform: uppercase;" maxlength="20"/>	       
	    </td>
   
	</tr>
	
	<tr>
	   <td width="15%">
	       <fmt:message key="PPMT.DESIGNATION" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	    	<input type="text" name="txtDesignation" id="txtDesignation" value="${mstPensionerHdrVO.designation}" style="width:70%" onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="36"/>
	    	<span id="roleIndicatorRegion" style="display: none"> <img	src="./images/busy-indicator.gif" /></span> 
	       <!--   <select name="cmbDesignation" id="cmbDesignation" style="width:70%" onfocus="onFocus(this)" onblur="onBlur(this);"  tabindex="27">
				         <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         <c:forEach var="designation" items="${resValue.lLstDesignation}">
				         <c:choose>
				            <c:when test="${designation.id == mstPensionerHdrVO.designation}">
				         <option selected="selected" value="${designation.id}"><c:out value="${designation.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${designation.id}">
								<c:out value="${designation.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>			
		   </select>-->	
			       
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
	    </td>
	   <td width="15%">
	       <fmt:message key="PPMT.RETOFFICE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtRetiringOffice" name="txtRetiringOffice" value="${mstPensionerHdrVO.employmentOffice}" onfocus="onFocus(this)"  onblur="onBlur(this);" tabindex="37" style="width:70%" maxlength="150"/>
	        <span id="roleIndicatorRegion" style="display: none"> <img	src="./images/busy-indicator.gif" /></span> 
	      <!--  <textarea id="txtRetiringOffice" name="txtRetiringOffice" rows="3" style="width: 35%" onkeypress="maxSizeReached(this,100,'txtRetiringOffice')" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="30">${mstPensionerHdrVO.employmentOffice}</textarea>-->
	    </td>
		
	</tr>
	<tr>
	<td width="15%">
	       <fmt:message key="PPMT.RETDEPT" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	    <select name="cmbRetiringDepartment" id="cmbRetiringDepartment" style="width:70%" onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="38">
			   		   <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				       <c:forEach var="department" items="${resValue.lLstDepartment}">
				       <c:choose>
				       <c:when test="${department.id == mstPensionerHdrVO.retiringDepartment}">
				       <option selected="selected" value="${department.id}" title="${department.desc}">
								<c:out value="${department.desc}"></c:out>
							</option>
				       </c:when>
				       <c:otherwise>
				       <option  value="${department.id}" title="${department.desc}">
								<c:out value="${department.desc}"></c:out>
							</option>
				       </c:otherwise>
				       </c:choose>
							
					</c:forEach>
			   </select>
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.PENEMAILID" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrEmailId" name="txtPnsnrEmailId" value="${mstPensionerHdrVO.pnsnrEmailId}" onfocus="onFocus(this)"  onblur="onBlur(this);validateEmailID(txtPnsnrEmailId,'Plz Enter Valid Email Id.')" tabindex="39" maxlength="30"/>	       
	    </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.RESADDR1" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtPnsnrAddr1" name="txtPnsnrAddr1" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="40"  value="${mstPensionerHdrVO.pnsnrAddr1}" maxlength="150" size="40"/>
	                 
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.RESADDR1INMARATHI" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtPnsnrAddr1InMarathi" name="txtPnsnrAddr1InMarathi" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="40"  value="${mstPensionerHdrVO.pnsnrAddr1Marathi}" maxlength="150" size="40"/>
	       <script language="javascript" type="text/javascript">
					
						pph = new PramukhPhoneticHandler();
						pph.convertToIndicIME("txtPnsnrAddr1InMarathi", document.getElementById('txtPnsnrAddr1InMarathi'),'devanagari');
		   </script>          
	    </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.RESADDR2" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrAddr2" name="txtPnsnrAddr2" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="41"  value="${mstPensionerHdrVO.pnsnrAddr2}" maxlength="150" size="50"/>
	            
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.RESADDR2INMARATHI" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrAddr2InMarathi" name="txtPnsnrAddr2InMarathi" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="41"  value="${mstPensionerHdrVO.pnsnrAddr2Marathi}" maxlength="150" size="50"/>
	       <script language="javascript" type="text/javascript">
					
						pph = new PramukhPhoneticHandler();
						pph.convertToIndicIME("txtPnsnrAddr2InMarathi", document.getElementById('txtPnsnrAddr2InMarathi'),'devanagari');
		   </script>    
	    </td>
	   
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.TOWNVILLAGE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrAddrTown" name="txtPnsnrAddrTown" value="${mstPensionerHdrVO.pnsnrAddrTown}" onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="42" maxlength="70"/>
	      
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.TOWNVILLAGEINMARATHI" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrAddrTownMarathi" name="txtPnsnrAddrTownMarathi" value="${mstPensionerHdrVO.pnsnrAddrTownMarathi}" onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="42" maxlength="70"/>
	       <script language="javascript" type="text/javascript">
					
						pph = new PramukhPhoneticHandler();
						pph.convertToIndicIME("txtPnsnrAddrTownMarathi", document.getElementById('txtPnsnrAddrTownMarathi'),'devanagari');
		   </script>
	    </td>
	       	    
	</tr>
	<tr>
	     <td width="15%">
	       <fmt:message key="PPMT.PNSNRADDRSTATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    <select name="cmbPnsnrAddrState" id="cmbPnsnrAddrState" onfocus="onFocus(this)" onblur="onBlur(this);" style="width:50%" ${disabled} tabindex="44">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:forEach var="state" items="${resValue.lLstState}">
					<c:choose>
					<c:when test="${state.id == mstPensionerHdrVO.stateCode}">
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
			 </select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
	    	<script>
				if(document.getElementById("cmbPnsnrAddrState").value == "-1")
				{
					document.getElementById("cmbPnsnrAddrState").value="15";
				}
			</script>	
	    </td>
	     <td width="15%">
	       <fmt:message key="PPMT.LANDLINENO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtLandLineNo" name="txtLandLineNo" value="${mstPensionerHdrVO.teleNo}" maxlength="11" onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="43" />
	    </td>
	  
	</tr>
	<!--
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.PNSNRADDRLOCALITY" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtPnsnrAddrLocality" name="txtPnsnrAddrLocality" value="${mstPensionerHdrVO.pnsnrAddrLocality}"  onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="35"/>
	    </td>
	</tr>
	--><tr>
	    <td width="15%">
	       <fmt:message key="PPMT.PNSNRADDRDIST" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    	<select name="cmbPnsnrAddrDist" id="cmbPnsnrAddrDist"   style="width: 37%" tabindex="46">
		    		<c:choose>
							<c:when test="${mstPensionerHdrVO.districtCode != null}">
     						<c:forEach var="DistrictList" items="${resValue.lLstPnsnrDistricts}">
							       <c:choose>
									<c:when test="${DistrictList.id == mstPensionerHdrVO.districtCode}">
											<option value="${DistrictList.id}" selected="selected">
											<c:out value="${DistrictList.desc}"></c:out>									
											</option>
								   </c:when>
							    <c:otherwise>
										<option value="${DistrictList.id}">
										<c:out value="${DistrictList.desc}"></c:out>									
										</option>
								</c:otherwise>
						       </c:choose>
							</c:forEach>		
					        </c:when>
					    <c:otherwise>
							<c:forEach var="DistrictList" items="${resValue.lLstDistricts}">
							<option value="${DistrictList.id}">
								<c:out value="${DistrictList.desc}"></c:out>
							</option>	
							</c:forEach>		
						</c:otherwise>
						</c:choose>
					
		    </select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
	    </td>
	      <td width="15%">
	    
	       <fmt:message key="PPMT.MOBILENO" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" id="txtMobileNo" name="txtMobileNo" value="${mstPensionerHdrVO.moblileNo}" onKeyPress="numberFormat(this)" maxlength="11" onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkLength(this);" tabindex="45"/>	       
	    </td>
	    </tr>
	    <tr>
	    <td width="15%">
	       <fmt:message key="PPMT.PINCODE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    	 <input type="text" id="txtPnsnrAddrPincode" name="txtPnsnrAddrPincode" value="${mstPensionerHdrVO.pinCode}" onKeyPress="numberFormat(this)"  onfocus="onFocus(this)" onblur="onBlur(this);" maxlength="6" tabindex="47"/>
	    </td>
	</tr>
	
	
 </table>
</fieldset> 
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.GUARDIANDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
 <table width="100%">
 		<tr>
		<td width="15%">
	       <fmt:message key="PPMT.ISADDRESSAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		  <c:choose>
	     <c:when test="${mstPensionerHdrVO.isAddrSameFlag == 'Y'}" >
	    	 <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioIsAddrSameY" name="radioIsAddrSame" value="Y" onclick="populateGuardianAddr(this);" tabindex="48" checked="checked"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioIsAddrSameN" name="radioIsAddrSame" value="N" onclick="populateGuardianAddr(this);"/>
		</c:when>
		<c:when test="${mstPensionerHdrVO.isAddrSameFlag =='N'}" >
			 <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioIsAddrSameY" name="radioIsAddrSame" value="Y" onclick="populateGuardianAddr(this);" tabindex="48"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioIsAddrSameN" name="radioIsAddrSame" value="N" onclick="populateGuardianAddr(this);" checked="checked"/>
		</c:when>
		<c:otherwise>
			 <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioIsAddrSameY" name="radioIsAddrSame" value="Y" onclick="populateGuardianAddr(this);" tabindex="48"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioIsAddrSameN" name="radioIsAddrSame" value="N" onclick="populateGuardianAddr(this);"/>
		</c:otherwise>
		</c:choose>
		    
		 </td>
		 </tr>
     <tr>
 		<td width="15%">
	       <fmt:message key="PPMT.NAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    <input type="text" id="txtGuardianName" name="txtGuardianName" value="${mstPensionerHdrVO.guardianName}" onkeypress="upperCase(event)"  onfocus="onFocus(this)"  onblur="onBlur(this);" tabindex="49" maxlength="70"/>
	    </td>
	    <td width="15%">
	      <fmt:message key="PPMT.FHNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	     <input type="text" id="txtGuardianFHName" name="txtGuardianFHName" value="${mstPensionerHdrVO.guardianFatherName}" onkeypress="upperCase(event)"  onfocus="onFocus(this)"  onblur="onBlur(this);" tabindex="50" maxlength="70"/>
	    </td>
	    </tr>
	    <tr>
	    <td width="15%">
	       <fmt:message key="PPMT.RESADDR1" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <input type="text" name="txtGuardianAddr1" id="txtGuardianAddr1" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="51" value="${mstPensionerHdrVO.guardianAddr1}" maxlength="150"/>
	      	      	       
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.RELATION" bundle="${pensionLabels}"></fmt:message>
	    </td>	        
	    <td width="35%">
	       <select name="cmbGuardianRelation" id="cmbGuardianRelation"   style="width: 37%" tabindex="52" >
		    	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
		    	<c:forEach var="relation" items="${resValue.lLstRelation}">
					<c:choose>
								<c:when test="${relation.lookupShortName == mstPensionerHdrVO.guardianRelation}">
									<option selected="selected" value='${relation.lookupShortName}'>
										<c:out value="${relation.lookupDesc}"></c:out>
									</option>
								</c:when>
								<c:otherwise>
									<option value='${relation.lookupShortName}'>
										<c:out value="${relation.lookupDesc}"></c:out>									
									</option>
								</c:otherwise>
							</c:choose>
						
					</c:forEach>
		   </select>	       
	    </td>
	    </tr>
	    <tr>
	    <td width="15%">
	       <fmt:message key="PPMT.RESADDR2" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtGuardianAddr2" name="txtGuardianAddr2" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="53" value="${mstPensionerHdrVO.guardianAddr2}" maxlength="150"/>
	    </td>
	     <td width="15%">
	       <fmt:message key="PPMT.TOWNVILLAGE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtGuardianAddrTown" name="txtGuardianAddrTown" value="${mstPensionerHdrVO.guardianAddrTown}" onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="54" maxlength="70"/>
	      
	    </td>
	    </tr>
	    <!--
	    <tr>
	    <td width="15%">
	       <fmt:message key="PPMT.PNSNRADDRLOCALITY" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <input type="text" id="txtGuardianAddrLocality" name="txtGuardianAddrLocality" value="${mstPensionerHdrVO.guardianAddrLocality}"  onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="46"/>
	    </td>
	</tr>
	--><tr>
	    <td width="15%">
	       <fmt:message key="PPMT.PNSNRADDRSTATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    <select name="cmbGuardianAddrState" id="cmbGuardianAddrState" onfocus="onFocus(this)" onblur="onBlur(this);" style="width:50%" tabindex="55">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:forEach var="state" items="${resValue.lLstState}">
					<c:choose>
					<c:when test="${state.id == mstPensionerHdrVO.guardianAddrState}">
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
	     <td width="15%">
	       <fmt:message key="PPMT.PNSNRADDRDIST" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    	<select name="cmbGuardianAddrDist" id="cmbGuardianAddrDist"   style="width: 37%" tabindex="56">
		    	   	<c:choose>
							<c:when test="${mstPensionerHdrVO.guardianAddrDistrict != null}">
     						<c:forEach var="DistrictList" items="${resValue.lLstGuardianDistricts}">
							       <c:choose>
									<c:when test="${DistrictList.id == mstPensionerHdrVO.guardianAddrDistrict}">
											<option value="${DistrictList.id}" selected="selected">
											<c:out value="${DistrictList.desc}"></c:out>									
											</option>
								   </c:when>
							    <c:otherwise>
										<option value="${DistrictList.id}">
										<c:out value="${DistrictList.desc}"></c:out>									
										</option>
								</c:otherwise>
						       </c:choose>
							</c:forEach>		
					        </c:when>
					    <c:otherwise>
							<c:forEach var="DistrictList" items="${resValue.lLstDistricts}">
							<option value="${DistrictList.id}">
								<c:out value="${DistrictList.desc}"></c:out>
							</option>	
							</c:forEach>		
						</c:otherwise>
				</c:choose>
		     </select>
	    </td>
	</tr>
	
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.PINCODE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    	 <input type="text" id="txtGuardianAddrPincode" name="txtGuardianAddrPincode" value="${mstPensionerHdrVO.guardianAddrPinCode}" onKeyPress="numberFormat(this)"  onfocus="onFocus(this)" onblur="onBlur(this);" maxlength="6" tabindex="57"/>
	    </td>
	</tr>
 </table>
 </fieldset>
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.REEMPDTLSFORCALC" bundle="${pensionLabels}"></fmt:message></b> </legend>	
	 &nbsp;<fmt:message key="PPMT.REEMPLT" bundle="${pensionLabels}"></fmt:message>
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <c:choose>
	     <c:when test="${trnPensionRqstHdrVO.reEmploymentFlag == 'Y'}" >
	    	 <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioReEmploymentY" name="radioReEmployment" value="Y"  checked="checked" onclick="enableReEmplDtls(this);" tabindex="58"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioReEmploymentN" name="radioReEmployment" value="N" onclick="enableReEmplDtls(this);"/>
		</c:when>
		<c:when test="${trnPensionRqstHdrVO.reEmploymentFlag =='N'}" >
			 <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioReEmploymentY" name="radioReEmployment" value="Y" onclick="enableReEmplDtls(this);" tabindex="58"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioReEmploymentN" name="radioReEmployment" value="N" checked="checked" onclick="enableReEmplDtls(this);"/>
		</c:when>
		<c:otherwise>
			 <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioReEmploymentY" name="radioReEmployment" value="Y" onclick="enableReEmplDtls(this);" tabindex="58"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioReEmploymentN" name="radioReEmployment" value="N" onclick="enableReEmplDtls(this);"  checked="checked"/>
		</c:otherwise>
		</c:choose>
 <table width="90%">
     
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="15%">
	       <input type="text" id="txtReEmpFromDate" name="txtReEmpFromDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.reEmploymentFromDate}" />"
	       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="59" size='15' disabled="disabled"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfCommencement,this,REEMPFROMCOMNTDT,'<');"/>
	       <img id="imgReEmpFromDate"   src='images/CalendarImages/ico-calendar.gif' disabled="disabled"
					        onClick='window_open("txtReEmpFromDate",375,570)'style="cursor: pointer;" />	  
	   
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="15%">
	        <input type="text" id="txtReEmpToDate" name="txtReEmpToDate"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.reEmploymentToDate}" />"
	       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="60" size='15' disabled="disabled"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtReEmpFromDate,this,REEMPFROMTODATE,'<');"/>
	       <img id="imgReEmpToDate"   src='images/CalendarImages/ico-calendar.gif' disabled="disabled"
					        onClick='window_open("txtReEmpToDate",375,570)'style="cursor: pointer;" />	
	    </td>
	    <td width="15%">
	      <fmt:message key="PPMT.DAINPNSNSAL" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="15%">
	      <select name="cmbDaInPnsnSalary" id="cmbDaInPnsnSalary" onfocus="onFocus(this)" onblur="onBlur(this);getFocus();" style="width:50%" disabled="disabled" tabindex="61" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
				<c:choose>
					<c:when test="${trnPensionRqstHdrVO.daInPensionSalary == 'Pension'}">
					<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
					<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
					</c:when>
					<c:when test="${trnPensionRqstHdrVO.daInPensionSalary == 'Salary'}">
					<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
					<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
					</c:when>
					<c:otherwise>
					<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
					<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
					</c:otherwise>
				</c:choose>
			
				
		  </select>
	    </td>
	</tr>
	
	
 </table>
 </fieldset>
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.REEMPDTLSFORINFO" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 70%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
 <input type="hidden" name="hidReEmpDtlsGridSize" id="hidReEmpDtlsGridSize" value="0">
<hdiits:button name="ReempDtls" type="button" captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="reEmploymentDtlsTableAddRow();"  />
<table width="95%" id="tblReEmpDtls">
	     <tr class="datatableheader"> 
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}" ></fmt:message></td>	
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message key="PPMT.DAINPNSNSAL" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message key="PPMT.EMPLOYEEORDERNO" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message key="PPMT.EMPLOYEEORDERDT" bundle="${pensionLabels}"></fmt:message></td>
						<td width="10%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
		 </tr>		
	<c:choose>
		<c:when test="${resValue.HstReEmploymentDtls !=null}">
		 <c:forEach var="HstReEmploymentDtlsVO"
				items="${resValue.HstReEmploymentDtls}" varStatus="Counter">
				 <tr>
					
					<td class="tds" align="center">
					<input type="hidden" name="hdnReEmpltId" id="hdnReEmpltId${Counter.index}" value="${HstReEmploymentDtlsVO.reEmploymentId}"/>
					<input type="text" name="txtReEmpltFromDate" id="txtReEmpltFromDate${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${HstReEmploymentDtlsVO.fromDate}" />"
	       				onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="15"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtReEmpltFromDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtReEmpltToDate" id="txtReEmpltToDate${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${HstReEmploymentDtlsVO.toDate}" />"
	       				onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="15"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtReEmpltToDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
					</td>
					<td class="tds" align="center">
						<select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal${Counter.index}" >
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>   
							<c:choose>
							<c:when test="${HstReEmploymentDtlsVO.daInPensionSalary == 'Pension'}">
							<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
							<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
							</c:when>
							<c:when test="${HstReEmploymentDtlsVO.daInPensionSalary == 'Salary'}">
							<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
							<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
							</c:when>
							<c:otherwise>
							<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
							<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
							</c:otherwise>
							</c:choose>       
				    		
						</select>
					</td>
					<td class="tds" align="center">
					    <input type="text" name="txtEmployeeOrderNo" id="txtEmployeeOrderNo${Counter.index}" onfocus="onFocus(this)" onblur="onBlur(this);" value="${HstReEmploymentDtlsVO.employeeOrderNo}" size="12" maxlength="30">
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtEmployeeOrderDate" id="txtEmployeeOrderDate${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${HstReEmploymentDtlsVO.employeeOrderDate}" />"
	       				onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="15"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtEmployeeOrderDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
					</td>
					<td class="tds" align="center"><img name="Image"
						id="Image"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblReEmpDtls');"/>
					</td>
			</tr>	
			<script>
				    
					document.getElementById("hidReEmpDtlsGridSize").value = Number('${Counter.index}') + 1;
					
			</script>
		</c:forEach>
		</c:when>
		</c:choose>	
</table>
</div>
</fieldset>
 <c:if test="${mstPensionerHdrVO.isAddrSameFlag == 'Y'}">
  <script>
		document.getElementById("txtGuardianAddr1").disabled=true;
		document.getElementById("txtGuardianAddr2").disabled=true;
		document.getElementById("txtGuardianAddrTown").disabled=true;
		document.getElementById("cmbGuardianAddrState").disabled=true;
		document.getElementById("cmbGuardianAddrDist").disabled=true;
		document.getElementById("txtGuardianAddrPincode").disabled=true;
  </script>
 </c:if>
 <c:if test="${mstPensionerHdrVO.aliveFlag == 'N' }">
 <script>
 document.getElementById("txtDateofExpiry").disabled=false;  
 document.getElementById("imgDateofExpiry").disabled=false;
 </script>
 </c:if> 
 <c:if test="${trnPensionRqstHdrVO.reEmploymentFlag == 'Y'}" >
 <script>
 	document.getElementById("txtReEmpFromDate").disabled=false;
 	document.getElementById("imgReEmpFromDate").disabled=false;
 	document.getElementById("txtReEmpToDate").disabled=false;
 	document.getElementById("imgReEmpToDate").disabled=false;
 	document.getElementById("cmbDaInPnsnSalary").disabled=false;
 </script>
 </c:if>


<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDistrictsFrmState" source="cmbPnsnrAddrState" target="cmbPnsnrAddrDist" parameters="state={cmbPnsnrAddrState}" ></ajax:select>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDistrictsFrmState" source="cmbGuardianAddrState" target="cmbGuardianAddrDist" parameters="state={cmbGuardianAddrState}" ></ajax:select>

<ajax:autocomplete source="txtRetiringOffice" target="txtRetiringOffice"
	baseUrl="ifms.htm?actionFlag=getOfficeListForAutoComplete"
	parameters="searchKey={txtRetiringOffice}" className="autocomplete"
	minimumCharacters="3" indicator="roleIndicatorRegion" />
	
<ajax:autocomplete source="txtDesignation" target="txtDesignation"
	baseUrl="ifms.htm?actionFlag=getDesgListForAutoComplete"
	parameters="searchKey={txtDesignation}" className="autocomplete"
	minimumCharacters="3" indicator="roleIndicatorRegion" />


<jsp:include page="/WEB-INF/jsp/pensionpay/pensionerPhotoSignAttachment.jsp" />

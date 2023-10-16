<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/leave/DateVal.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.foreigntourtravel.foreigntourtravel" var="commonLables" scope="request" />


<script>

function validateFloat(component){
	if(isNaN(component.value)){
		alert('<fmt:message key="HRMS.entValidNo" />');
		component.focus();
		return false;
	}
	else{
		return true;	
	}
}

function checkText(e,isNumberAlso)
{
     var key;
     var keychar;
       
     if (e)
         key = e.keyCode;
     else if (e)
         key = e.which;
     else
         return true;
         
     keychar = String.fromCharCode(key);
   
//   var iChars = "`[]&_-=$#:;,@!*?%~(){}<>/^\\\'|\"+";
     var iChars = "`[]&_=$#;@!*?%~(){}<>^\\\|\"+";	// does not have "/" ":" "," and "'"
     
     if ( iChars.indexOf(keychar) != -1 )
     {
         return false;
     }
     
     var inChars = "0123456789";
     
     if( isNumberAlso && inChars.indexOf(keychar) != -1 )
     {
         return false;
     }
     
     return true;       
}

</script>


<table class="tabtable" name="travelRequest" id="travelRequest"  width="100%">


<tr bgcolor="#386CB7">



		<td class="fieldLabel" colspan="4" align="center" width="25%" >
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.annexure"/></u></strong>
			</font>
		</td>
	</tr>

<tr>	
<td width="25%" ><b><fmt:message key="HRMS.NAME"/></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="NAME"  id="NAME" 
                                      readonly="true" default="${EmpDetVO.empName}" maxlength="100" />
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX2I"/></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX2I" tabindex="1" id="ANX2I" maxlength="100"
                                      captionid="HRMS.ANX2I"  bundle="${commonLables}" onkeypress="return checkText(event, false)" />
                                    </td>
                            </tr>

<tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX2II"/></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX2II" tabindex="2" id="ANX2II" maxlength="100"
                                     captionid="HRMS.ANX2II"  bundle="${commonLables}" onkeypress="return checkText(event, false)"/>
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX3"/></td>
	<td width="25%">
	<fmt:formatDate value="${hrFtourReqDtl.dateOfDep}"  pattern="dd/MM/yyyy" var="dateOfDeparture" />
	<fmt:formatDate value="${hrFtourReqDtl.dateOfRet}" pattern="dd/MM/yyyy" var="dateOfReturn"/>
	<hdiits:text mandatory="false"  name="ANX3" id="ANX3" readonly="true" maxlength="100"
                                     captionid="HRMS.ANX3"  bundle="${commonLables}" default="${dateOfDeparture} to ${dateOfReturn}"/>
                                    </td>
                                    </tr>
                                    
<tr>
 <td width="25%"><b><fmt:message key="HRMS.ANX4I"/></td>
<td width="25%"> 
<hdiits:text mandatory="false"  name="ANX4I" tabindex="3" id="ANX4I" maxlength="100"
                                     captionid="HRMS.ANX4I"  bundle="${commonLables}" onkeypress="return checkText(event, false)" />
                                    </td> 
 <td width="25%"><b><fmt:message key="HRMS.ANX4II"/></td>
<td width="25%">
<hdiits:text mandatory="false"  name="ANX4II" tabindex="4" id="ANX4II" maxlength="90"
                                      captionid="HRMS.ANX4II"  bundle="${commonLables}" size="6" onkeypress="return checkText(event, false)"/>
<hdiits:dateTime  name="ANX4IID" captionid="HRMS.ANX4II" tabindex="5" bundle="${commonLables}"/>  

</td>                                                                    
</tr> 

<tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX5" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX5" size="1" id="ANX5" tabindex="6" captionid="HRMS.ANX5" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			  
			</hdiits:select>
		</td>
<td width="25%"><b><fmt:message key="HRMS.ANX6"/></b></td>
	<td width="25%">
	<hdiits:textarea captionid="HRMS.ANX6" mandatory="true" rows="2" cols="30" name="ANX6" tabindex="7" id="ANX6"  default="${hrFtourReqDtl.purpose}"/>
	</td>
	</tr>
	
	<tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX7I"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="telecode" tabindex="8" id="ANX7I" floatAllowed="true" maxlength="90"
                                     captionid="HRMS.ANX7I"  bundle="${commonLables}" onblur="validateFloat(this);" />
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX7II"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX7II" tabindex="9" id="ANX7II" floatAllowed="true" maxlength="90"
                                     captionid="HRMS.ANX7II"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
</tr>
                                    
 <tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX8"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX8" tabindex="10" id="ANX8" floatAllowed="true" maxlength="90"
                                    captionid="HRMS.ANX8"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX9"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX9" tabindex="11" id="ANX9" floatAllowed="true" maxlength="90"
                                    captionid="HRMS.ANX9"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
 </tr> 
 
 <tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX10I"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX10I" tabindex="12" id="ANX10I" maxlength="90"
                                    captionid="HRMS.ANX10I"  bundle="${commonLables}" onkeypress="return checkText(event, false)"/>
                                    </td>
 <td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX11" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX11" size="1" id="ANX11" tabindex="13" captionid="HRMS.ANX11" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>
		</tr> 
		
		<tr>
 <td width="25%"><b><fmt:message key="HRMS.ANX12I"/></b></td>
<td width="25%">
<hdiits:text mandatory="false"  name="ANX12I" tabindex="14" id="ANX12I" maxlength="90"
                                    captionid="HRMS.ANX12I"  bundle="${commonLables}" onkeypress="return checkText(event, false)" />
                                    </td> 
 <td width="25%"><b><fmt:message key="HRMS.ANX12II"/></b></td>
<td width="25%">
<hdiits:text mandatory="false"  name="ANX12II" tabindex="15" id="ANX12II" maxlength="90"
                                    captionid="HRMS.ANX12II"  bundle="${commonLables}" size="6" onkeypress="return checkText(event, false)"/>
<hdiits:dateTime  name="ANX12IID" captionid="HRMS.ANX12II" tabindex="16" bundle="${commonLables}"/> 
                                   
</td>                                                                    
</tr> 

<tr>
<td width="25%"><b><fmt:message key="HRMS.ANX12III"/></b></td>
<td width="25%">
<hdiits:text mandatory="false"  name="ANX12III" tabindex="17" id="ANX12III" maxlength="90"
                                    captionid="HRMS.ANX12III"  bundle="${commonLables}" size="6" onkeypress="return checkText(event, false)"/>
<hdiits:dateTime  name="ANX12IIID" captionid="HRMS.ANX12III" tabindex="18" bundle="${commonLables}"/>                                    
</td>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX12IV" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX12IV" size="1" id="ANX12IV" tabindex="19" captionid="HRMS.ANX12IV" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                                                                    
</tr>

<tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX13I" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX13I" size="1" id="ANX13I" tabindex="20" captionid="HRMS.ANX13I" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX13II" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX13II" size="1" id="ANX13II" tabindex="21" captionid="HRMS.ANX13II" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr>	

<tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX13III" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX13III" size="1" id="ANX13III" tabindex="22" captionid="HRMS.ANX13III" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX13IV" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX13IV" size="1" id="ANX13IV" tabindex="23" captionid="HRMS.ANX13IV" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr>

<tr>	
<td width="25%"> <b><fmt:message key="HRMS.ANX13V"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX13V" tabindex="24" id="ANX13V" maxlength="90"
                                    captionid="HRMS.ANX13V"  bundle="${commonLables}" onkeypress="return checkText(event, false)"/>
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX13VI"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX13VI" tabindex="25" id="ANX13VI" maxlength="400" 
                                   captionid="HRMS.ANX13VI"  bundle="${commonLables}" onkeypress="return checkText(event, false)"/>
                                    </td>
                                    </tr>
                                    
<tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX13VII"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX13VII" tabindex="26" id="ANX13VII" floatAllowed="true" maxlength="90"
                                    captionid="HRMS.ANX13VII"  bundle="${commonLables}" default="${dAForCountryPerDay}" onblur="validateFloat(this);"/>
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX13VIII"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX13VIII" tabindex="27" id="ANX13VIII" floatAllowed="true" maxlength="90"
                                     captionid="HRMS.ANX13VIII"  bundle="${commonLables}" onblur="validateFloat(this);" />
                                    </td>
                                    </tr> 
 
 <tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX14I" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX14I" size="1" id="ANX14I" tabindex="28" captionid="HRMS.ANX14I" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX14II" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select  name="ANX14II" size="1" id="ANX14II" tabindex="29" captionid="HRMS.ANX14II" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr>

<tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX14III" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX14III" size="1" id="ANX14III" tabindex="30" captionid="HRMS.ANX14III" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX15" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX15" size="1" id="ANX15" tabindex="31" captionid="HRMS.ANX15" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr>

<tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX16" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX16" size="1" id="ANX16" tabindex="32" captionid="HRMS.ANX16" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX17" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX17" size="1" id="ANX17" tabindex="33" captionid="HRMS.ANX17" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr>                                   
 
 <tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX18" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX18" size="1" id="ANX18" tabindex="34" captionid="HRMS.ANX18" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX19" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX19" size="1" id="ANX19" tabindex="35" captionid="HRMS.ANX19" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr> 

<tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX20I"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX20I" tabindex="36" id="ANX20I" floatAllowed="true" maxlength="90"
                                    captionid="HRMS.ANX20I"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX20II"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX20II" tabindex="37" id="ANX20II" floatAllowed="true" maxlength="90"
                                     captionid="HRMS.ANX20II"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
                                    </tr>  
                                    
 <tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX21" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX21" size="1" id="ANX21" tabindex="38" captionid="HRMS.ANX21" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX22I" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX22I" size="1" id="ANX22I" tabindex="39" captionid="HRMS.ANX22I" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr> 

 <tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX22II" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX22II" size="1" id="ANX22II" tabindex="40" captionid="HRMS.ANX22II" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>
		<td width="25%"><b><fmt:message key="HRMS.ANX23"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX23" tabindex="41" id="ANX23" floatAllowed="true" maxlength="90"
                                     captionid="HRMS.ANX23"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
                                    </tr> 
                                    
 <tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX24" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX24" size="1" id="ANX24" tabindex="42" captionid="HRMS.ANX24" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>
<td width="25%"><b><fmt:message key="HRMS.ANX24IA"/></b></td>
<td width="25%">
<hdiits:dateTime  name="ANX24IA" captionid="HRMS.ANX24IA" bundle="${commonLables}" tabindex="43"/>                                    
</td>                                                                    
</tr> 

<tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX24IB"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX24IB" tabindex="44" id="ANX24IB" maxlength="90"
                                     captionid="HRMS.ANX24IB"  bundle="${commonLables}" onkeypress="return checkText(event, false)"/>
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX24IC"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX24IC" tabindex="45" id="ANX24IC" floatAllowed="true" maxlength="90"
                                     captionid="HRMS.ANX24IC"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
                                    </tr>  
                                    
<tr>	
<td width="25%"><b><fmt:message key="HRMS.ANX24ID"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX24ID" tabindex="46" id="ANX24ID" floatAllowed="true" maxlength="90"
                                      captionid="HRMS.ANX24ID"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
<td width="25%"><b><fmt:message key="HRMS.ANX24II"/></b></td>
	<td width="25%">
	<hdiits:number mandatory="false"  name="ANX24II" tabindex="47" id="ANX24II" floatAllowed="true" maxlength="90"
                                      captionid="HRMS.ANX24II"  bundle="${commonLables}" onblur="validateFloat(this);"/>
                                    </td>
                                    </tr> 
                                    
 <tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX24III" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX24III" size="1" id="ANX24III" tabindex="48" captionid="HRMS.ANX24III" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>
		<td width="25%"><b><fmt:message key="HRMS.ANX24IV"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX24IV" tabindex="49" id="ANX24IV" maxlength="90"
                                     captionid="HRMS.ANX24IV"  bundle="${commonLables}" onkeypress="return checkText(event, false)"/>
                                    </td>
                                    </tr> 
                                    
 <tr>
<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX24V" /></b>
  	</td>
<td width="25%">	
	 		<hdiits:select name="ANX24V" size="1" id="ANX24V" tabindex="50" captionid="HRMS.ANX24V" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
		<td align="left" width="25%">
  		<b><fmt:message key="HRMS.ANX24VI" /></b>
  	</td>
<td width="25%"> 	
	 		<hdiits:select name="ANX24VI" size="1" id="ANX24VI" tabindex="51" captionid="HRMS.ANX24VI" mandatory="false">
			  <hdiits:option value="2"><fmt:message key="HRMS.select" /></hdiits:option>
			  <hdiits:option value="1"><fmt:message key="HRMS.yes" /></hdiits:option>
			  <hdiits:option value="0"><fmt:message key="HRMS.no" /></hdiits:option>
			</hdiits:select>
		</td>                
</tr>           
<tr>
	
	<td colspan="4" align="center">
		<hdiits:button type="button" name="update" tabindex="52" id="update" captionid="HRMS.update" bundle="${commonLables}" onclick="chechAnnextureDtl();"/>
		<hdiits:button type="button" id="Closey" name="Closey" tabindex="53" captionid="HRMS.close" bundle="${commonLables}" onclick="document.getElementById('goBackFlag').value=2;goBack();" />
	</td>
</tr>                                                                                                 		                                                                                                                               	                                                                 
</table>



<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
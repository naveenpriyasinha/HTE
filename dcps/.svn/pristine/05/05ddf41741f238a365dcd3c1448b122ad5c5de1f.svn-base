<%try { %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:requestEncoding value="UTF-8" />

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/dcpsDdovalidation.js"></script>
<script>
function pinCodeValidation()
{
	var pinCode=document.getElementById("txtPin").value;
	if(pinCode.charAt(0) != '4')
	{
		alert('Please enter valid Pin code.');
		document.getElementById("txtPin").value='';
		//document.getElementById("txtPin").focus();
		return false;
	}
	var district=document.getElementById("cmbDist").value;
	var uri = 'ifms.htm?actionFlag=validatePinCode';
	var url = 'pinCode='+pinCode+'&district='+district;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					checkPinCode(myAjax,pinCode);
					
				},
		        onFailure: function()
		        			{ 
							alert('Something went wrong...');
						} 
		          } 
	);
}
function checkPinCode(myAjax,pinCode){
	
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(checkFlag == 'correct')
	{
		//alert('all ok');
	}
	else if (checkFlag == 'wrong')
	{
		alert('Entered pin code is wrong. Please enter correct pin code.');
		document.getElementById("txtPin").value='';
		return false;
	}
	return false;
}


function validateTeleNumber()
{
	var mobNo=document.getElementById("txtTelNo1").value.trim();
	//alert(mobNo);
	//alert(mobNo.charAt(0));
	//alert(mobNo.length);
	if(Number(mobNo)==0){

		alert('All the digit of Telephone number can not be zero.');
		document.getElementById("txtTelNo1").value='';
		return false;
	}
	
	if(mobNo.length!='11'){

		alert('Telephone number should be of 11 digits.');
		document.getElementById("txtTelNo1").value='';
		return false;
	}
}


function validateMobileNumber(){

	var mobNo=document.getElementById("txtTelNo2").value.trim();
	//alert(mobNo);
	//alert(mobNo.charAt(0));
	//alert(mobNo.length);
	if(mobNo.length!='10'){

		alert('Mobile number should be of 10 digits.');
		document.getElementById("txtTelNo2").value='';
		return false;
	}
	if(mobNo.charAt(0)!='7' && mobNo.charAt(0)!='8' && mobNo.charAt(0)!='9'){
		alert('Please enter a valid Mobile Number');
		document.getElementById("txtTelNo2").value='';
		return false;
		}
	} 
function displayCityClass1()
{
	//alert('inside displayCityClass');
	var city=document.getElementById("cmbcity").value;
	//alert('dist: '+dist);
	

	var uri = 'ifms.htm?actionFlag=displayCityClass';
	var url = 'city='+city;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				displayCityClass(myAjax,city);
					
				},
		        onFailure: function()
		        			{ 
							alert('Something went wrong...');
						} 
		          } 
	);
}
function displayCityClass(myAjax,city){
	
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var cityClass = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(cityClass == 'Not provided')
	{
		alert('No entry for selected city.');
		document.getElementById("cmbOfficeCityClass").value='';
		return false;
	}
	else
	{
		document.getElementById("cmbOfficeCityClass").value=cityClass;
		return false;
	}
	return false;
}
</script>
<style>
select, input,textarea {
    width: 250px !important;
}
</style>
<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmDCPSDdoOffice" encType="multipart/form-data"
	validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="officeList" value="${resValue.OfficeList}" />
<c:set var="DdoOfficeVO" value="${resValue.DdoOfficeVO}"/>
<c:set var="DdoOfficeOrNot" value="${resValue.DdoOfficeOrNot}"/>

<%--//addedby samadhan--%>
<c:set var="cityClassValue" value="${DdoOfficeVO.dcpsDdoOfficeCityClass}"></c:set>
<input type="hidden"  name="txtDdoCode" id="txtDdoCode" style="text-align: left" value="${resValue.DDOCODE}"/>

<table id="tbl1" width="100%" align="center">	

<tr>
	
		<td>
		<fieldset class="tabstyle"><legend><fmt:message key="CMN.OFFICEDETAILS" bundle="${DCPSLables}"></fmt:message></legend>
		<table width="100%" align="center" cellpadding="4" cellspacing="4">
		<tr>
		<td  align="left" >
			<fmt:message key="CMN.NAMEOFOFFICE" bundle="${DCPSLables}"></fmt:message>
			
		</td>
		
		<td align="left">
		<c:choose>
				<c:when test="${DdoOfficeVO == null}">
						<c:choose>
								<c:when test="${DdoOfficeOrNot == 'Yes'}">
									 <input type="text"  size="100%" name='txtNameOfOffice' id="txtNameOfOffice" style="text-align: left" disabled="disabled" value="${resValue.DefaultOffice}" />
								</c:when>
								<c:otherwise>
		 <input type="text"  size="100%" name='txtNameOfOffice' id="txtNameOfOffice" style="text-align: left" disabled="disabled" value="${DdoOfficeVO.dcpsDdoOfficeName}" /><label class="mandatoryindicator">*</label>	
		
		 
		  </c:otherwise>
						</c:choose>
						
						</c:when>
						<c:otherwise>
		 <input type="text"  size="100%" name='txtNameOfOffice' id="txtNameOfOffice" style="text-align: left" disabled="disabled" value="${DdoOfficeVO.dcpsDdoOfficeName}" />
		
		 <label class="mandatoryindicator">*</label>	
		  </c:otherwise>
		 </c:choose>
		</td>
		
	
		<!-- 
		<td width="10%" align="left" >
			Government Sanction Order
		</td>
									
		<td width="30%" align="left">
			<input type="text" size="20%" name='txtGov' id="txtGov" maxlength="50" disabled="disabled" style="text-align: left"  />
		</td>	
		
		
		<td width="10%" align="left" >
			Date 
		</td>
									
		<td width="20%" align="left">
			<input type="text" size="20%" name='txtDate' id="txtDate" maxlength="50" disabled="disabled" style="text-align: left" />
		</td>
		
									
			 -->						
									
									
		
		
		</tr>
		</table>
		
		</fieldset>
		</td>
</tr>

<tr>
		<td>
								
		     <fieldset class="tabstyle"><legend> 
	                  <fmt:message key="CMN.ADDRESSDETAILS" bundle="${DCPSLables}"></fmt:message> </legend>
	                  	   <table width="100%" align="center" cellspacing="4" cellpadding="4"  >
	                  	       <tr>
	                  	   		<td width="15%" align="left" >
	                  	   		<fmt:message key="CMN.STATE" bundle="${DCPSLables}"></fmt:message>
	                  	   		
									</td>
									
	                  	   			<td width="35%" align="left" >
			 						<select name="cmbState" id="cmbState" style="width:360px" disabled="disabled" >
				         			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         				<c:forEach var="stateName" items="${resValue.STATENAMES}" >
				         					
				         					<c:choose>
				         						<c:when test="${DdoOfficeVO!=null}">
				         							<c:choose>
				         							<c:when test="${stateName.id==DdoOfficeVO.dcpsDdoOfficeState}">
				         								<option value="${stateName.id}" selected="selected">${stateName.desc}</option>
				         							</c:when>
				         							<c:otherwise>
				         								<option value="${stateName.id}">${stateName.desc}</option>
				         							</c:otherwise>
				         							</c:choose>
				         						</c:when>
				         					
				         						<c:otherwise>
				         						<c:choose>
				         							<c:when test="${stateName.id==15}">
				         								<option value="${stateName.id}" selected="selected">${stateName.desc}</option>
				         							</c:when>
				         							<c:otherwise>
				         								<option value="${stateName.id}">${stateName.desc}</option>
				         							</c:otherwise>
				         							</c:choose>
				         						</c:otherwise>
				         					</c:choose>
												
										</c:forEach>
				         	 		</select>
				         	 		
									</td>
									
									<td width="15%" align="left">
										<fmt:message key="CMN.DISTRICT" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									

									<td width="35%" align="left" >
			 						<select name="cmbDist" id="cmbDist"  disabled="disabled" style="width:360px" >
				         			
				         			
				         			<c:if test="${resValue.lLstDistricts!=null}">
				         			
				         				<c:forEach var="districtName" items="${resValue.lLstDistricts}" >
				         				<c:choose>
				         						<c:when test="${DdoOfficeVO!=null}">
				         							<c:choose>
				         							<c:when test="${districtName.id==DdoOfficeVO.dcpsDdoOfficeDistrict}">
				         								<option value="${districtName.id}" selected="selected">${districtName.desc}</option>
				         							</c:when>
				         							<c:otherwise>
				         								<option value="${districtName.id}">${districtName.desc}</option>
				         							</c:otherwise>
				         							</c:choose>
				         						</c:when>
				         					
				         						<c:otherwise>
				         							<option value="${districtName.id}">${districtName.desc}</option>
				         						</c:otherwise>
				         				</c:choose>
											
										</c:forEach>
				         			</c:if>
				         			</select>
				         			<label class="mandatoryindicator">*</label>	
									</td>
																									
	                  	   			
	                  	   		</tr>
								<tr>
	                  	   			<td width="15%" align="left" >
		                  	   			<fmt:message key="CMN.TALUKA" bundle="${DCPSLables}"></fmt:message>
		                  	   			
									</td>
									
	                  	   			<td width="35%" align="left">
			 						<select name="cmbTaluka" id="cmbTaluka"  style="width:360px" disabled="disabled" onchange="">
				         			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
										
									<c:if test="${resValue.lLstTalukas!=null}">
									
									
				         				<c:forEach var="talukaName" items="${resValue.lLstTalukas}" >
					         				<c:choose>
					         						<c:when test="${DdoOfficeVO!=null}">
					         								<c:choose>
							         							<c:when test="${talukaName.id==DdoOfficeVO.dcpsDdoOfficeTaluka}">
							         								<option value="${talukaName.id}" selected="selected">${talukaName.desc}</option>
							         							</c:when>
						         								<c:otherwise>
						         									<option value="${talukaName.id}">${talukaName.desc}</option>
						         								</c:otherwise>
						         							</c:choose>
					         						</c:when>
					         					
					         						<c:otherwise>
					         							<option value="${talukaName.id}">${talukaName.desc}</option>
					         						</c:otherwise>
					         				</c:choose>
											
										</c:forEach>
				         			</c:if>
				         			</select>
									</td>
									<%--
									<td width="15%" align="left" >
										<fmt:message key="CMN.TOWN" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td width="35%" align="left">
										<input type="text" size="48" name='txtTown' id="txtTown" maxlength="50"  disabled="disabled" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeTown}"  onblur="isName(txtTown,'This field should not contain any special characters or digits.')"  />
									</td>					
	                  	   			--%>
	                  	   		</tr>	
	                  	   		
	                  	   		<tr>
	                  	   		
	                  	   		<td align="left" width="15%">
		                  	   			City
		                  	   			
									</td >
									
	                  	   			<td align="left" width="35%">
			 						<select name="cmbcity" id="cmbcity" disabled="disabled" onchange="" style="width: 49%" onblur="displayCityClass1();">
				         			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
										
									<c:if test="${resValue.lLstcity!=null}">
									
									
				         				<c:forEach var="cityName" items="${resValue.lLstcity}" >
					         				<c:choose>
					         						<c:when test="${DdoOfficeVO!=null}">
					         								<c:choose>
							         							<c:when test="${cityName[0]==DdoOfficeVO.dcpsDdoOfficeTown}">
							         								<option value="${cityName[0]}" selected="selected">${cityName[1]}</option>
							         							</c:when>
						         								<c:otherwise>
						         									<option value="${cityName[0]}">${cityName[1]}</option>
						         								</c:otherwise>
						         							</c:choose>
					         						</c:when>
					         					
					         						<c:otherwise>
					         							<option value="${cityName[0]}">${cityName[1]}</option>
					         						</c:otherwise>
					         				</c:choose>
											
										</c:forEach>
				         			</c:if>
				         			</select>
									<label class="mandatoryindicator">*</label>	
									</td>
									
	                  	   			<td width="15%" align="left" >
	                  	   				<fmt:message key="CMN.VILLAGE" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="35%"> 
									<input type="text" size="48" name='txtVillage' id="txtVillage" maxlength="50"  disabled="disabled" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeVillage}" onblur="isName(txtVillage,'This field should not contain any special characters or digits.')"  />
									</td>								
									
	                  	   		
	                  	   						
	                  	   										
								</tr>
										
	                  	   		<tr>
	                  	   		<td width="15%" align="left" >
	                  	   				<fmt:message key="CMN.ADDRESS" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="35%"> 
									<textarea name='txtAddress1' id="txtAddress1" disabled="disabled" rows="5" cols="42" >${DdoOfficeVO.dcpsDdoOfficeAddress1}</textarea>
									<label class="mandatoryindicator">*</label>	
									</td>		
									
	                  	   			<td width="15%" align="left" >
	                  	   				<fmt:message key="CMN.PIN" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="35%"> 
									<input type="text" size="48" name='txtPin' id="txtPin" maxlength="6" disabled="disabled" style="text-align: left" onkeypress="digitFormat(this);" onblur="chckPin(this);pinCodeValidation();"  value="${DdoOfficeVO.dcpsDdoOfficePin}" />
									<label class="mandatoryindicator">*</label>	
									</td>
								</tr>
								
								<tr>						
									<td width="15%" align="left" >
										<fmt:message key="CMN.OFFICECITYCLASS" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td align="left" width="35%">
									<%--commented by samadhan to diaplay text box instead drop down
			 						<select  name="cmbOfficeCityClass" id="cmbOfficeCityClass"  disabled="disabled" style="width: 50%">
				         			<option value="-1"><fmt:message
										key="COMMON.DROPDOWN.SELECT" /></option>
									<c:forEach var="officeClassList" items="${resValue.OFFICECLASSLIST}">
											<c:choose>
					         						<c:when test="${DdoOfficeVO!=null}">
					         								<c:choose>
							         							<c:when test="${officeClassList.lookupDesc==DdoOfficeVO.dcpsDdoOfficeCityClass}">
							         								<option value="${officeClassList.lookupDesc}" selected="selected">${officeClassList.lookupDesc}</option>
							         							</c:when>
						         								<c:otherwise>
						         									<option value="${officeClassList.lookupDesc}">${officeClassList.lookupDesc}</option>
						         								</c:otherwise>
						         							</c:choose>
					         						</c:when>
					         					
					         						<c:otherwise>
					         							<option value="${officeClassList.lookupDesc}">${officeClassList.lookupDesc}</option>
					         						</c:otherwise>
					         				</c:choose>
									</c:forEach>
									</select>
									--%>
									<%--added by samadhan--%>
									<input type="text" name="cmbOfficeCityClass" size="50" id="cmbOfficeCityClass" disabled="disabled" value="${cityClassValue}" readonly="readonly">
									<label class="mandatoryindicator">*</label>	
									</td>									
							
	                  	   		</tr>
	                  	   		<tr>
	                  	   			<td width="15%" align="left" >
	                  	   				<fmt:message key="CMN.Dice" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="35%"> 
									<input type="text" size="48" name='txtdiceCode' id="txtdiceCode" maxlength="11"  disabled="disabled" style="text-align: left" onkeypress="digitFormat(this);" disabled="disabled" value="${resValue.DDOCODE}" />
									
									
									
									<!--<label class="mandatoryindicator">*</label>	
									-->
									
									</td>
									
									
									<td width="15%" align="left" >
										<fmt:message key="CMN.GRANT" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td width="35%" align="left">
										<%--<input type="text" size="48" name='txtGrant' id="txtGrant" maxlength="50" disabled="disabled" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeGrant}"    onkeypress="digitFormat(this);"  />--%>
										<select  name="txtGrant" id="txtGrant"  disabled="disabled" style="width: 50%">
				         					
				         					
				         					
				         					
				         					<c:choose>
					         						<c:when test="${DdoOfficeVO!=null}">
					         								<c:choose>
							         							<c:when test="${DdoOfficeVO.dcpsDdoOfficeGrant==90}">
							         								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							         								<option value="90" selected="selected">90%</option>
							         								<option value="100">100%</option>
							         							</c:when>
							         							<c:when test="${DdoOfficeVO.dcpsDdoOfficeGrant==100}">
							         								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							         								<option value="90">90%</option>
							         								<option value="100" selected="selected">100%</option>
							         							</c:when>
						         								<c:otherwise>
						         									<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						         									<option value="90">90%</option>
							         								<option value="100">100%</option>
						         								</c:otherwise>
						         							</c:choose>
					         						</c:when>
					         					
					         						<c:otherwise>
					         							<option value="${officeClassList.lookupDesc}">${officeClassList.lookupDesc}</option>
					         						</c:otherwise>
					         				</c:choose>
				         				</select>
									</td>		
								</tr>
								
								
								
	                  	   </table>
	       
	  		 </fieldset>
		</td>
</tr>
<tr>
	<td width="100%" valign="top">
		     <fieldset class="tabstyle"><legend> 
	                  <fmt:message key="CMN.CONTACTDETAILS" bundle="${DCPSLables}"></fmt:message> </legend>
	                  	   <table width="100%" align="center" cellpadding="4" cellspacing="4">	
	                  	   <tr>
	                  	   			<td width="15%" align="left" >
	                  	   				<fmt:message key="CMN.TELNO(1)" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="35%"> <input type="text" size="48" name='txtTelNo1' id="txtTelNo1" maxlength="11"  disabled="disabled" style="text-align: left" onkeypress="digitFormat(this);" onblur="validateTeleNumber();" value="${DdoOfficeVO.dcpsDdoOfficeTelNo1}"/>
									</td>
														
									<td width="15%" align="left" ><fmt:message key="CMN.TELNO(2)MOBILE" bundle="${DCPSLables}"></fmt:message>	
									</td>							
								
									<td width="35%"> <input type="text" size="48" name='txtTelNo2' id="txtTelNo2" maxlength="10"  disabled="disabled" style="text-align: left" onkeypress="digitFormat(this);" onblur="validateMobileNumber();"  value="${DdoOfficeVO.dcpsDdoOfficeTelNo2}" /><label class="mandatoryindicator">*</label>	
									</td>
									
	                  	   </tr>
	                  	   							
							<tr>
									<td colspan = "4" style="font-size : smaller;font-style:italic">
										<fmt:message key="MSG.TEL" bundle="${DCPSLables}"/>
									</td>
							</tr>		
						                  	   
	                  	   
	                  	   
	                  	   <tr>
	                  	   			<td width="15%" align="left" ><fmt:message key="CMN.FAX" bundle="${DCPSLables}"></fmt:message>	
									</td>
									
									<td width="35%"> <input type="text" size="48" name='txtMobileNo' id="txtMobileNo" maxlength="15" disabled="disabled" style="text-align: left" onkeypress="digitFormat(this);" onblur="" value="${DdoOfficeVO.dcpsDdoOfficeFax}" />
									</td>
														
									<td width="15%" align="left">
										<fmt:message key="CMN.EMAIL" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td width="35%"> <input type="text" size="48" name='txtEmail' id="txtEmail" maxlength="254" disabled="disabled" style="text-align: left"  onblur="validateEmailID(txtEmail,'Please Enter Valid Email');" value="${DdoOfficeVO.dcpsDdoOfficeEmail}"/>
									</td>
									
	                  	   </tr>
	                  	   </table>
	             
	             </fieldset>
</tr>



<tr >
	<td  valign="top" align = "center">
		    <!--<legend> 
	                  <fmt:message key="CMN.BLANK" bundle="${DCPSLables}"></fmt:message> </legend>
	                  	   --><table width="100%" cellpadding="4" cellspacing="4">
	                  	   <tr style="display:none">
	                  	 		<td  align="left"  width="12%">
	                  	 			<fmt:message key="CMN.TRIBLEAREA" bundle="${DCPSLables}"></fmt:message>
	                  	 			
								</td>								
								<td width="12%" align="left">
							
								
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'Yes' || DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'Y'}">
									<input type="radio" id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="Yes" checked/>
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio" id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="No" />
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
									
								</c:if >
								
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'No' || DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'N'  || DdoOfficeVO == null || DdoOfficeVO.dcpsDdoOfficeTribalFlag == null}">
									<input type="radio" id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="Yes" />
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio" id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="No" checked/>
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if>
								
								
								  <label class="mandatoryindicator">*</label>	
								</td>
								<td width="6%">
								</td>
								
								<td width = "10%"  align="left" >
									<fmt:message key="CMN.HILLYAREA" bundle="${DCPSLables}"></fmt:message>
									
								</td>								
								<td width="12%" align="left">
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeHillyFlag == 'Yes' || DdoOfficeVO.dcpsDdoOfficeHillyFlag == 'Y'}">
									<input type="radio" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="Yes" checked/>
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="No" />
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if >
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeHillyFlag == 'No' || DdoOfficeVO.dcpsDdoOfficeHillyFlag == 'N'  ||  DdoOfficeVO == null || DdoOfficeVO.dcpsDdoOfficeHillyFlag == null}">
									<input type="radio" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="Yes" />
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="No" checked/>
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if>
								<label class="mandatoryindicator">*</label>	
								</td>
								<td width="6%"></td>
								<td width = "10%" align="left">
									<fmt:message key="CMN.NAXALITEAREA" bundle="${DCPSLables}"></fmt:message>
								
								</td>								
								<td width="12%" align="left">
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == 'Yes' || DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == 'Y'}">
									<input type="radio" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="Yes" checked/>
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="No" />
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if >
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == 'No' || DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == 'N' || DdoOfficeVO == null || DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == null}">
									<input type="radio" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="Yes" />
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="No" checked/>
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if>
									<label class="mandatoryindicator">*</label>	
								</td>																	
	                  	   </tr>
	                  	   
	                  	   </table>
	          <br/>       	 
	         
	          
	          
	          
	          <table width="20%" align="center" height="10%" cellpadding="0" cellspacing="0">
					<tr></tr>
					<tr></tr>
					<tr>
						<td width="20%" align="center">
						  <c:choose>
			          		<c:when test="${DdoOfficeOrNot =='Yes'}">
			          			<hdiits:button type="button" captionid="BTN.UpdateDdoOffice" bundle="${DCPSLables}" id="update" name="update" onclick="UpdateOfficeData('${DdoOfficeVO.dcpsDdoOfficeIdPk}');"></hdiits:button>
			          		</c:when>
			          		
			          		<c:otherwise>
			          			<hdiits:button type="button" value="Save & Forward To Reporting DDO" id="save" name="save" onclick="UpdateOfficeData('${DdoOfficeVO.dcpsDdoOfficeIdPk}');" style="width:100%"></hdiits:button>
			          		</c:otherwise>
	          			 </c:choose>	
						
						</td>
						
						
						<td width="50%" align="center">
							<hdiits:button name="btnedit" id="btnedit" type="button" captionid="BTN.EDIT" bundle="${DCPSLables}" onclick="EditDDOValidation();" />
							
							
						</td>
		
						<td width="50%" align="center">
							<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="BTN.BACK" bundle="${DCPSLables}" onclick="ReturnPage();"/>
						</td>
						
						
						
						
						
					</tr>	
				</table>
	        
	          </td>	
</tr>

</table>
</hdiits:form>


<ajax:select 
		source="cmbState" 
		target="cmbDist" 
		eventType="change"
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popDistNms" 
		parameters="cmbState={cmbState}">
</ajax:select>

<ajax:select 
		source="cmbDist" 
		target="cmbTaluka" 
		eventType="change"
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popTalukaNms" 
		parameters="cmbDist={cmbDist}">
</ajax:select>


<ajax:select 
		source="cmbDist" 
		target="cmbcity" 
		eventType="change"
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=populateCityUsingAjax" 
		parameters="cmbDist={cmbDist}">
</ajax:select>

<% } catch(Exception e){
e.printStackTrace();
}%>
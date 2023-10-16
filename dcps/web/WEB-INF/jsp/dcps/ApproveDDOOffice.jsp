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

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmDCPSDdoOffice" encType="multipart/form-data"
	validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="officeList" value="${resValue.OfficeList}" />
<c:set var="DdoOfficeVO" value="${resValue.DdoOfficeVO}"/>
<c:set var="DdoOfficeOrNot" value="${resValue.DdoOfficeOrNot}"/>
<c:set var="ApproveDDOOfficeList" value="${resValue.ApproveDDOOfficeList}"/>
<c:set var="officeId" value="${resValue.officeId}"/>
<c:set var="ApproveFlag" value="${resValue.ApproveFlag}"/>

 <!-- started By roshan kumar-->
<c:set var="talukaList" value="${resValue.talukaList}" ></c:set>
<c:set var="talukaId" value="${resValue.talukaId}" ></c:set>
<c:set var="ddoSelected" value="${resValue.ddoSelected}" ></c:set>
<script type="text/javascript" src="script/dcps/ddoScheme.js"></script>
<!-- end By roshan kumar-->

	<%--Added by roshan --%>
	<fieldset class="tabstyle"><legend> <b>Filter Institute</b> </legend>
<table align="center">
<tr>
<td><c:out value="Taluka"></c:out></td>

<td><select name="cmbTaluka"
			id="cmbTaluka" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="talukaList" items="${talukaList}">
			<c:choose> 
			<c:when test="${talukaId==talukaList[0]}">
							<option value="${talukaList[0]}" title="${talukaList[1]}" selected="selected">
						<c:out value="${talukaList[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${talukaList[0]}"/>" title="${talukaList[1]}">
						<c:out value="${talukaList[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
		</select></td>


<td><c:out value=" DDO Code/Office Name"></c:out></td>
<td>
<c:choose>
<c:when test="${ddoSelected!=null}">
<!--<hdiits:text id = "ddoCode" name="ddoCode" captionid="${ddoSelected}" validation="" maxlength="200" size="30"
			 default=""/>
-->
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" value="${ddoSelected}" size="30"/>
</c:when>
<c:otherwise>
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" size="30"/>
</c:otherwise>
</c:choose>			 
</td>


		</tr>
	<tr>	
<td colspan="4" align="center"><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
		value="Filter" onclick="filterInstituteForApprove();"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>
<%--Added by roshan --%>


<input type="hidden"  name="txtDdoCode" id="txtDdoCode" style="text-align: left" value="${resValue.DDOCODE}"/>
<fieldset class="tabstyle"><legend><fmt:message key="CMN.OFFICESADDED" bundle="${DCPSLables}"></fmt:message> </legend>
<table align="center" style="width: 100%">
<tr>
<td align="center" >
<display:table list="${ApproveDDOOfficeList}"  id="vo"  requestURI="" export="" style="width:50%;"  pagesize="5" >	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.OFFICENAME" >
		<c:choose>
					<c:when test="${vo[4] eq 1}" >
						<a href=# onclick="ApproveDataBlock(this);"><c:out value="${vo[1]}"></c:out></a>
					</c:when>
					<c:otherwise>
						<a href=# onclick="popUpDDOOfficeDetailsapprove('${vo[0]}');"><c:out value="${vo[1]}"></c:out></a>
					</c:otherwise>
				</c:choose>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DDOCODE" >
    			<c:out value="${vo[5]}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.DDOOFFICE" >		
				<c:choose>
					<c:when test="${vo[1] == 'Y' || vo[1] == 'Yes' ||  vo[1] == 'YES'}" >
						<c:out value="Main Office"/>
					</c:when>
					<c:otherwise>
						<c:out value="Sub Office"/>
					</c:otherwise>
				</c:choose>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.OFFADDRESS" >    
    			<c:out value="${vo[2]} ${vo[3]}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.STATUS" >    
    			<div id="status${vo[0]}">
    	
    			<c:if test="${vo[4] eq 0  }"><c:out value="Pending"></c:out></c:if>
    			<c:if test="${vo[4] eq 1 }"><c:out value="Approved"></c:out></c:if>
    			
    			</div>
    				
		</display:column>
</display:table>
</td>
</tr>
</table>	
	
</fieldset>
<br/>
 
 
<table id="tbl1" width="100%" align="center">	
<tr>
	
		<td>
				
		<fieldset class="tabstyle"><legend><fmt:message key="CMN.OFFICEDETAILS" bundle="${DCPSLables}"></fmt:message></legend>
		<table width="100%" align="center" cellpadding="4" cellspacing="4">
		<tr>
		<td width="10%" align="left" >
			<fmt:message key="CMN.NAMEOFOFFICE" bundle="${DCPSLables}"></fmt:message>
			
		</td>
		
		<td width="30%" align="left">
		<c:choose>
				<c:when test="${DdoOfficeVO == null}">
						<c:choose>
								<c:when test="${DdoOfficeOrNot == 'Yes'}">
									 <input type="text"  size="60%" name='txtNameOfOffice' id="txtNameOfOffice" style="text-align: left"  value="${resValue.DefaultOffice}" />
								</c:when>
							<c:otherwise>
		 <input type="text"  size="40%" name='txtNameOfOffice' id="txtNameOfOffice" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeName}" />
		
		 <label class="mandatoryindicator">*</label>	
		 </c:otherwise>
							</c:choose>
							</c:when>
							<c:otherwise>
		 <input type="text"  size="40%" name='txtNameOfOffice' id="txtNameOfOffice" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeName}" />
		
		 <label class="mandatoryindicator">*</label>	
		  </c:otherwise>
		 </c:choose>
		</td>
		
		
		<%--
		<td width="10%" align="left">
			<fmt:message key="CMN.DDO(Y/N)"	bundle="${DCPSLables}"></fmt:message>
			
		</td>
		
		<td width="30%" align="left">
		<c:choose>
				<c:when test="${DdoOfficeVO == null}">
						<c:choose>
								<c:when test="${DdoOfficeOrNot == 'Yes'}">
									<input type="radio"  id="radioButtonDDO" name="radioButtonDDO" value="Yes" checked/>
									<fmt:message key="CMN.MAINOFFICE" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio" id="radioButtonDDO" name="radioButtonDDO" value="No"  />
									<fmt:message key="CMN.SUBORDINATEOFFICE" bundle="${DCPSLables}" ></fmt:message>
								</c:when>
								<c:otherwise>
									<input type="radio"  readonly="readonly" id="radioButtonDDO" name="radioButtonDDO" value="Yes" />
									<fmt:message key="CMN.MAINOFFICE" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio"  id="radioButtonDDO" name="radioButtonDDO" value="No" checked/>
									<fmt:message key="CMN.SUBORDINATEOFFICE" bundle="${DCPSLables}" ></fmt:message>
								</c:otherwise>
						</c:choose>
				</c:when>
				<c:otherwise>
						<c:choose>
								<c:when test="${DdoOfficeVO.dcpsDdoOfficeDdoFlag == 'Yes'}">
										<input type="radio"  id="radioButtonDDO" name="radioButtonDDO" value="Yes" checked/>
										<fmt:message key="CMN.MAINOFFICE" bundle="${DCPSLables}" ></fmt:message>
										<input type="radio"  id="radioButtonDDO" name="radioButtonDDO" value="No" />
										<fmt:message key="CMN.SUBORDINATEOFFICE" bundle="${DCPSLables}" ></fmt:message>
								</c:when>
								<c:otherwise>
										<input type="radio"  id="radioButtonDDO" name="radioButtonDDO" value="Yes" />
										<fmt:message key="CMN.MAINOFFICE" bundle="${DCPSLables}" ></fmt:message>
										<input type="radio"  id="radioButtonDDO" name="radioButtonDDO" value="No" checked/>
										<fmt:message key="CMN.SUBORDINATEOFFICE" bundle="${DCPSLables}" ></fmt:message>
								</c:otherwise>
						</c:choose>
				</c:otherwise>
			</c:choose>
	
		</td> --%>
		
		
		
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
	                  	   		<td width="10%" align="left" >
	                  	   		<fmt:message key="CMN.STATE" bundle="${DCPSLables}"></fmt:message>
	                  	   		
									</td>
									
	                  	   			<td width="30%" align="left" >
			 						<select name="cmbState"  id="cmbState" style="width:70%"  >
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
									
									<td width="10%" align="left">
										<fmt:message key="CMN.DISTRICT" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									

									<td width="30%" align="left" >
			 						<select  name="cmbDist" id="cmbDist" style="width:70%">
				         			
				         			
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
	                  	   			<td width="10%" align="left" >
		                  	   			<fmt:message key="CMN.TALUKA" bundle="${DCPSLables}"></fmt:message>
		                  	   			
									</td>
									
	                  	   			<td width="30%" align="left">
			 						<select name="cmbTaluka"  id="cmbTaluka" style="width:70%" onchange="">
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
									
									<td width="10%" align="left" >
										<fmt:message key="CMN.TOWN" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td width="30%" align="left">
										<input type="text" size="40%" r name='txtTown' id="txtTown" maxlength="50" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeTown}" onblur="isName(txtTown,'This field should not contain any special characters or digits.')"  />
									</td>					
	                  	   			
	                  	   		</tr>	
	                  	   		
	                  	   		<tr>
	                  	   			<td width="10%" align="left" >
	                  	   				<fmt:message key="CMN.VILLAGE" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="30%"> 
									<input type="text" size="40%" name='txtVillage' id="txtVillage" maxlength="50" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeVillage}" onblur="isName(txtVillage,'This field should not contain any special characters or digits.')"  />
									</td>								
									
	                  	   		
	                  	   			<td width="10%" align="left" >
	                  	   				<fmt:message key="CMN.ADDRESS" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="30%" rowspan="2"> 
									<textarea name='txtAddress1' id="txtAddress1" rows="5" cols="30" >${DdoOfficeVO.dcpsDdoOfficeAddress1}</textarea>
									<label class="mandatoryindicator">*</label>	
									</td>					
	                  	   										
								</tr>
										
	                  	   		<tr>
	                  	   			<td width="10%" align="left" >
	                  	   				<fmt:message key="CMN.PIN" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="30%"> 
									<input type="text" size="30%" name='txtPin' id="txtPin" maxlength="6" style="text-align: left" onkeypress="digitFormat(this);" onblur="chckPin(this)" value="${DdoOfficeVO.dcpsDdoOfficePin}" />
									<label class="mandatoryindicator">*</label>	
									</td>
								</tr>
								<tr>						
									<td width="10%" align="left" >
										<fmt:message key="CMN.OFFICECITYCLASS" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td width="30%" align="left">
			 						<select name="cmbOfficeCityClass"  id="cmbOfficeCityClass" style="width:70%" >
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
									<label class="mandatoryindicator">*</label>	
									</td>						
							
	                  	   		</tr>
	                  	   		<tr>
	                  	   			<td width="10%" align="left" >
	                  	   				<fmt:message key="CMN.Dice" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="30%"> 
									<input type="text" size="30%" readonly="readonly" name='txtdiceCode' id="txtdiceCode" maxlength="11" style="text-align: left" value="${DdoOfficeVO.dcpsDdoCode}" onkeypress="digitFormat(this);" />
									<label class="mandatoryindicator">*</label>	
									</td>
									
									<td width="10%" align="left" >
										<fmt:message key="CMN.GRANT" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td width="30%" align="left">
										<input type="text" size="40%" name='txtGrant' id="txtGrant" maxlength="50" style="text-align: left" value="${DdoOfficeVO.dcpsDdoOfficeGrant}"  />
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
	                  	   			<td width="10%" align="left" >
	                  	   				<fmt:message key="CMN.TELNO(1)" bundle="${DCPSLables}"></fmt:message>
	                  	   				
									</td>
									
									<td width="30%"> <input type="text" size="30%" name='txtTelNo1' id="txtTelNo1" maxlength="15" style="text-align: left" onkeypress="digitFormat(this);" onblur="" value="${DdoOfficeVO.dcpsDdoOfficeTelNo1}"/>
									</td>
														
									<td width="10%" align="left" ><fmt:message key="CMN.TELNO(2)MOBILE" bundle="${DCPSLables}"></fmt:message>	
									</td>							
								
									<td width="30%"> <input type="text" size="30%" name='txtTelNo2' id="txtTelNo2" maxlength="15" readonly="readonly" style="text-align: left" onkeypress="digitFormat(this);" onblur=""  value="${DdoOfficeVO.dcpsDdoOfficeTelNo2}" />
									</td>
									
	                  	   </tr>
	                  	   							
							<tr>
									<td colspan = "4" style="font-size : smaller;font-style:italic">
										<fmt:message key="MSG.TEL" bundle="${DCPSLables}"/>
									</td>
							</tr>		
						                  	   
	                  	   
	                  	   
	                  	   <tr>
	                  	   			<td width="10%" align="left" ><fmt:message key="CMN.FAX" bundle="${DCPSLables}"></fmt:message>	
									</td>
									
									<td width="30%"> <input type="text" size="30%" name='txtMobileNo' id="txtMobileNo" maxlength="15" style="text-align: left"  onkeypress="digitFormat(this);" onblur="" value="${DdoOfficeVO.dcpsDdoOfficeFax}" />
									</td>
														
									<td width="10%" align="left">
										<fmt:message key="CMN.EMAIL" bundle="${DCPSLables}"></fmt:message>
										
									</td>
									
									<td width="30%"> <input type="text" size="30%" name='txtEmail' id="txtEmail" maxlength="254" style="text-align: left"  onblur="validateEmailID(txtEmail,'Please Enter Valid Email');" value="${DdoOfficeVO.dcpsDdoOfficeEmail}"/>
									</td>
									
	                  	   </tr>
	                  	   </table>
	             
	             </fieldset>
</tr>
<tr>
	<td  valign="top" align = "center">
		     <fieldset class="tabstyle"><legend> 
	                  <fmt:message key="CMN.WHETHEROFFICEIN" bundle="${DCPSLables}"></fmt:message> </legend>
	                  	   <table width="100%" cellpadding="4" cellspacing="4">
	                  	   <tr >
	                  	 		<td  align="left"  width="12%">
	                  	 			<fmt:message key="CMN.TRIBLEAREA" bundle="${DCPSLables}"></fmt:message>
	                  	 			
								</td>								
								<td width="12%" align="left">
							
								
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'Yes' || DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'Y'}">
									<input type="radio"   readonly="readonly" id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="Yes" checked/>
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio"    id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="No" />
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
									
								</c:if >
								
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'No' || DdoOfficeVO.dcpsDdoOfficeTribalFlag == 'N'  || DdoOfficeVO == null || DdoOfficeVO.dcpsDdoOfficeTribalFlag == null}">
									<input type="radio"   readonly="readonly" id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="Yes" />
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio"   id="RadioButtonTriableArea" name="RadioButtonTriableArea" value="No" checked/>
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
									<input type="radio"   readonly="readonly" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="Yes" checked/>
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio"   readonly="readonly" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="No" />
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if >
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeHillyFlag == 'No' || DdoOfficeVO.dcpsDdoOfficeHillyFlag == 'N'  ||  DdoOfficeVO == null || DdoOfficeVO.dcpsDdoOfficeHillyFlag == null}">
									<input type="radio"   readonly="readonly" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="Yes" />
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio"   readonly="readonly" id="RadioButtonHillyArea" name="RadioButtonHillyArea" value="No" checked/>
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
									<input type="radio"   readonly="readonly" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="Yes" checked/>
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio"   readonly="readonly" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="No" />
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if >
								<c:if test="${DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == 'No' || DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == 'N' || DdoOfficeVO == null || DdoOfficeVO.dcpsDdoOfficeNaxaliteAreaFlag == null}">
									<input type="radio"  readonly="readonly" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="Yes" />
									<fmt:message key="CMN.YES" bundle="${DCPSLables}" ></fmt:message>
									<input type="radio"   readonly="readonly" id="RadioButtonNaxaliteArea" name="RadioButtonNaxaliteArea" value="No" checked/>
									<fmt:message key="CMN.NO" bundle="${DCPSLables}" ></fmt:message>
								</c:if>
									<label class="mandatoryindicator">*</label>	
								</td>																	
	                  	   </tr>
	                  	   </table>
	          <br/>       	 
	          </fieldset><br/>
	          
	          <table width="20%" align="center" height="10%" cellpadding="0" cellspacing="0">
					<tr id="reasonForRejection" style="display: none;">
					<td>Reason For Rejection:</td><td><input type="text" id = "txtReasonForRejection" name="txtReasonForRejection" maxlength="200" value="${ddoSelected}" size="30"/></td>
				</tr>
					<tr></tr>
					<tr>
						<td width="50%" align="center">
						        <input type="hidden" value="${officeId}" name="officeId" id="officeId">
								<hdiits:button type="button" captionid="BTN.APPROVE" bundle="${DCPSLables}" id="approve" name="approve" onclick="ApproveDDOOfficeData();"></hdiits:button>
			          	</td>
			          	<td width="50%" align="center">
							<hdiits:button name="btnRjct" id="btnRjct" type="button"  caption="Reject" onclick="RejectDDOOfficeData();"/>
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
<%--//added by samadhan--%>
<script>
function RejectDDOOfficeData()
{
	document.getElementById("reasonForRejection").style.display='';
	var rejectionReason=document.getElementById("txtReasonForRejection").value;
	if(rejectionReason == '')
	{
		alert('Please enter reason for rejection.');
		return false;
	}
	ddoOfficeId = document.getElementById("officeId").value;
	ddoOfficeName= document.getElementById("txtNameOfOffice").value;
	var mobileNo=document.getElementById("txtTelNo2").value;
	
	self.location.href="ifms.htm?actionFlag=SaveRejectDdoOffice&ddoOfficeId="+ddoOfficeId+"&ddoOfficeName="+ddoOfficeName+"&mobileNo="+mobileNo+"&rejectionReason="+rejectionReason;
	alert("Office Data Rejected.");
	showProgressbar("Please wait<br>While Your Request is in Progress ...");
}
</script>
<%--//added by samadhan--%>
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
<% } catch(Exception e){
e.printStackTrace();
}%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/dppf/Common_1.0.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsSrka.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="cadreList" value="${resValue.cadreList}"></c:set>

<hdiits:form name="dcpsCadreInfo" id="dcpsCadreInfo" encType="multipart/form-data" validate="true" method="post">
<table width="100%">
<tr>
<td>

    <fieldset class="tabstyle"><legend> 
	             <fmt:message key="CMN.CADREMASTER" bundle="${dcpsLabels}"></fmt:message> </legend>
	             <table width="100%"  cellpadding = "4" cellspacing = "4">
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.FIELDDEPARTMENT"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    
				         <td  width="30%">
				             <select name="cmbFieldDepartment" id="cmbFieldDepartment" onchange="getCadresForTheFieldDept();">				
			 					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			 						<c:forEach var="FieldDeptList" items="${resValue.FieldDeptList}">		
			 							<c:choose>
			 								<c:when test="${FieldDeptList.id == resValue.CurrFieldDeptId}">			
												<option value="${FieldDeptList.id}" selected="selected">
													<c:out value="${FieldDeptList.desc}"></c:out>
												</option>
											</c:when>
											<c:otherwise>
												<option value="${FieldDeptList.id}">
													<c:out value="${FieldDeptList.desc}"></c:out>
												</option>
											</c:otherwise>
										</c:choose>
			 						</c:forEach>
		   					 </select>
		   					 <label class="mandatoryindicator">*</label>
				         </td>        
				         <td width="50%">
				         </td>       			                       
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.GROUP"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    
				         <td  width="30%">
				             <select name="cmbGroup" id="cmbGroup" >				
			 					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 						<c:forEach var="Group" items="${resValue.Group}">					
										<option value="${Group.lookupId}">
											<c:out value="${Group.lookupDesc}"></c:out>					
										</option>
			 						</c:forEach>
		   					 </select>
		   					 <label class="mandatoryindicator">*</label>
				         </td>        
				         <td width="50%">
				         </td>       			                       
                    </tr>
                          
                     <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.CADRECODE"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    				         
				         
				         <td width="30%"> <input type="text" name='txtCadreCode' id="txtCadreCode" style="text-align: left" value="${resValue.cadreCode}"  />
				         <label class="mandatoryindicator">*</label>
						 </td>				                
				         
				         <td width="50%">
				         </td>       			                       
                    </tr>                  
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.CADREDESCRIPTION"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    				         
				         
				         <td width="30%"> <input type="text" name='txtCadreDesc' id="txtCadreDesc" maxlength="100" style="text-align: left" size="40" />
				         <label class="mandatoryindicator">*</label>
						 </td>				                
				         
				         <td width="50%">
				         </td>       			                       
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.WHETHERMINISTERIAL"
				                         bundle="${dcpsLabels}"></fmt:message>                        
                        </td>
                        <td width="80%">
                        	  <input type="radio" id="radioMinisterial" name="radioMinisterial" value="Y"/>
				              <fmt:message key="CMN.YES" bundle="${dcpsLabels}"></fmt:message>
				              <input type="radio" id="radioMinisterial" name="radioMinisterial" value="N" checked="checked"/>
				              <fmt:message key="CMN.NO" bundle="${dcpsLabels}"></fmt:message> 
				              <label class="mandatoryindicator">*</label>
						</td>				                
				         				        
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.SUPERANNUATIONAGE"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    				         
				         
				         <td width="30%"> <input type="text" name='txtSuperAnnuAge' id="txtSuperAnnuAge" maxlength="2" style="text-align: left" />
				         <label class="mandatoryindicator">*</label>
						 </td>				                
				         
				         <td width="50%">
				         </td>       			                       
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.CADRECONTROLLEDBYOWNDEPARTMENT"
				                         bundle="${dcpsLabels}"></fmt:message>                        
                        </td>
                        <td width="80%">
                        	  <input type="radio" id="radioCadreControlDept" name="radioCadreControlDept" value="Y" checked="checked" onclick="hideRadio(this);"/>
				              <fmt:message key="CMN.YES" bundle="${dcpsLabels}"></fmt:message>
				              <input type="radio" id="radioCadreControlDept" name="radioCadreControlDept" value="N" onclick="hideRadio(this);"/>
				              <fmt:message key="CMN.NO" bundle="${dcpsLabels}"></fmt:message>
				              <label class="mandatoryindicator">*</label> 				              				             
						</td>				                
				         				        
                    </tr>
                    
                    <tr id="T1" style="display:none" >
                        <td width="20%" align="left"><fmt:message key="CMN.IFNOCONTROLLEDBYFIELDDEPARTMENT"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    
				         <td  width="30%">
				            <select name="cmbCntrlFieldDept" id="cmbCntrlFieldDept" style="width: 40%">				
			 				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 						<c:forEach var="FieldDeptList" items="${resValue.FieldDeptList}">					
										<option value="${FieldDeptList.id}">
											<c:out value="${FieldDeptList.desc}"></c:out>					
										</option>
			 						</c:forEach>
		   					 </select>
		   					 <label class="mandatoryindicator">*</label>
				         </td>        
				         <td width="50%">
				         </td>       
                    </tr>    
                                                   
                 </table>   
                                                
   </fieldset>              
</td>
</tr>

<tr>
<td align="center">
<br/>
<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" 
		               			bundle="${dcpsLabels}" onclick="saveCadreData();" />     
</td>
</tr>
</table>
<br/>
<fieldset class="tabstyle"><legend>Post Added</legend>
<br/>

<c:if test="${fn:length(cadreList)>0}">
<display:table list="${cadreList}" id="vo" cellpadding="4" style="width:90%" pagesize="10" requestURI="">    			      			        			      			      			  		         		         				
	              	 
					              <display:setProperty name="paging.banner.placement" value="bottom" />
					              	
					              <display:column style="text-align:left" title= "Post Code" headerClass="datatableheader"
					               value="${vo[0]}">			      
						          </display:column>
					              	
					              <display:column  style="text-align:left" title= "Post Description" headerClass="datatableheader"
					               value="${vo[1]}">			      
						          </display:column>	
						          
						           <display:column style="text-align: left;" title="Field Department" headerClass="datatableheader"
						            value="${vo[2]}" > 	
						                   	          
					              </display:column>
						          
					              <display:column style="text-align: left;" title="Group Code" headerClass="datatableheader"
						            value="${vo[3]}" > 	
						                   	          
					              </display:column>
					              	          		         
					              	              	              
					              <display:column style="text-align: left;"  title="Ministerial" headerClass="datatableheader">
					              
					              <c:if test="${vo[4]=='Y'}">
					              	<c:out value= 'Yes'/>
					              </c:if>
					              
					              <c:if test="${vo[4]=='N'}">
					              	<c:out value= 'No'/>
					              </c:if>
						            		           	          
					              </display:column>
					              
					              <display:column style="text-align: left;"  title="Super Annuation Age" headerClass="datatableheader"
				 								   value="${vo[5]}"> 		           	          
					              </display:column>
					              					                          	              	            
</display:table>
</c:if>
<c:if test="${fn:length(cadreList)==0}">
<table id="vo" style="width:60%" cellpadding="4" class="datatable">
<thead>
<tr>
<th class="datatableheader">Post Code</th>
<th class="datatableheader">Post Description</th>
<th class="datatableheader">Field Department</th>
<th class="datatableheader">Group Code</th>
<th class="datatableheader">Ministerial</th>
<th class="datatableheader">Super Annuation Age</th></tr></thead>
<tbody>
<tr>
<td colspan = "6">No Records Found to Display</td>
</tr>
</tbody>
</table>
</c:if>

<br/>


</fieldset>
</hdiits:form>
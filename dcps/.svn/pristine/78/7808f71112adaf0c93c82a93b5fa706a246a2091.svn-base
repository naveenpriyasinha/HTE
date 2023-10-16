<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript">
function hide(object)
{
	var radioval=object.value;
	alert('radioval is ' + radioval);
	   if(radioval == "Y")
	   {
		   document.getElementById("T1").style.display="none"; 		   
	   }
	   else if(radioval == "N")
	   {		   			     	
		   document.getElementById("T1").style.display ="block";     
	   }
}


function saveData()
{
	alert('inside saveDesigInfo()');
	var uri;
	
    //if(validDesigInfo() == true)
	{
			   
			 
			   uri = "ifms.htm?actionFlag=InsertCadre";
			   saveCadreDetailsUsingAjx(uri);
			  // resetAllFieldsAfterSavePressed();
		       
    } 
	
}


function saveCadreDetailsUsingAjx(uri)
{

   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
	   alert ("Your browser does not support AJAX!");
	   return;
   }  
   
   var url = runForm(0); 
   url = uri + url; 
   alert('url is ' + url);
   xmlHttp.onreadystatechange=cadreCaseStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(url);
   
}





function cadreCaseStateChanged() 
{ 


	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("Cadre Details saved successfully.");
			self.location.href="ifms.htm?actionFlag=loadCadreInfo";
		}
	}
}

</script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="cadreList" value="${resValue.cadreList}"></c:set>

<hdiits:form name="dcpsCadreInfo" id="dcpsCadreInfo" encType="multipart/form-data" validate="true" method="post">
<table width="100%">
<tr>
<td>

    <fieldset class="tabstyle"><legend> 
	             <fmt:message key="CMN.CADREMASTER" bundle="${dcpsLabels}"></fmt:message> </legend>
	             <table width="100%">
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.FIELDDEPARTMENT"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    
				         <td  width="30%">
				             <select name="cmbFieldDepartment" id="cmbFieldDepartment" style="width: 40%">				
			 					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 						<c:forEach var="ParentDeptList" items="${resValue.ParentDeptList}">					
										<option value="${ParentDeptList.lookupId}">
											<c:out value="${ParentDeptList.lookupDesc}"></c:out>					
										</option>
			 						</c:forEach>
		   					 </select>
				         </td>        
				         <td width="50%">
				         </td>       			                       
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.GROUP"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    
				         <td  width="30%">
				             <select name="cmbGroup" id="cmbGroup" style="width: 40%">				
			 					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 						<c:forEach var="Group" items="${resValue.Group}">					
										<option value="${Group.lookupId}">
											<c:out value="${Group.lookupDesc}"></c:out>					
										</option>
			 						</c:forEach>
		   					 </select>
				         </td>        
				         <td width="50%">
				         </td>       			                       
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.CADRECODE"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    				         
				         
				         <td width="30%"> <input type="text" name='txtCadreCode' id="txtCadreCode" style="text-align: left"  />
						 </td>				                
				         
				         <td width="50%">
				         </td>       			                       
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.CADREDESCRIPTION"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    				         
				         
				         <td width="30%"> <input type="text" name='txtCadreDesc' id="txtCadreDesc" style="text-align: left" size="40" />
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
				              
				              <hdiits:button name="btnReOrganizeHierarchy" id="btnReOrganizeHierarchy" type="button" captionid="CMN.REORGCADREHIR" 
		               			bundle="${dcpsLabels}" onclick="" style="width:20%"/>
						</td>				                
				         				        
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.SUPERANNUATIONAGE"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    				         
				         
				         <td width="30%"> <input type="text" name='txtSuperAnnuAge' id="txtSuperAnnuAge" style="text-align: left" />
						 </td>				                
				         
				         <td width="50%">
				         </td>       			                       
                    </tr>
                    
                    <tr>
                        <td width="20%" align="left"><fmt:message key="CMN.CADRECONTROLLEDBYOWNDEPARTMENT"
				                         bundle="${dcpsLabels}"></fmt:message>                        
                        </td>
                        <td width="80%">
                        	  <input type="radio" id="radioCadreControlDept" name="radioCadreControlDept" value="Y" checked="checked" onclick="hide(this);"/>
				              <fmt:message key="CMN.YES" bundle="${dcpsLabels}"></fmt:message>
				              <input type="radio" id="radioCadreControlDept" name="radioCadreControlDept" value="N" onclick="hide(this)"/>
				              <fmt:message key="CMN.NO" bundle="${dcpsLabels}"></fmt:message> 				              				             
						</td>				                
				         				        
                    </tr>
                    
                    <tr id="T1" style="display:none" >
                        <td width="20%" align="left"><fmt:message key="CMN.IFNOCONTROLLEDBYFIELDDEPARTMENT"
				                         bundle="${dcpsLabels}"></fmt:message>
				         </td>    
				         <td  width="30%">
				             <select name="cmbGroup" id="cmbGroup" style="width: 40%">				
			 				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 						<c:forEach var="Group" items="${resValue.Group}">					
										<option value="${Group.lookupDesc}">
											<c:out value="${Group.lookupDesc}"></c:out>					
										</option>
			 						</c:forEach>
		   					 </select>
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
<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" 
		               			bundle="${dcpsLabels}" onclick="saveData();" style="width:15%"/>     
</td>
</tr>
</table>

<display:table list="${cadreList}" id="vo" cellpadding="4" style="width:100%" pagesize="10">    			      			        			      			      			  		         		         				
	              	 
					              <display:setProperty name="paging.banner.placement" value="bottom" />
					              <display:column style="text-align: center;" class="tablecelltext" title="Group Code" headerClass="datatableheader"
						            value="${vo.groupId}" > 	
						                      	          
					              </display:column>
					              	
					              <display:column class="tablecelltext" style="text-align:center" title= "Cadre Code" headerClass="datatableheader"
					               value="${vo.cadreCode}">			      
						          </display:column>
					              	
					              <display:column class="tablecelltext" style="text-align:center" title= "Cadre Description" headerClass="datatableheader"
					               value="${vo.cadreName}">			      
						          </display:column>		          		         
					              	              	              
					              <display:column style="text-align: center;" class="tablecelltext" title="Ministerial???" headerClass="datatableheader"
						           value="${vo.ministerialFlag}"> 		           	          
					              </display:column>
					              
					              <display:column style="text-align: center;" class="tablecelltext" title="Super Annuation Age" headerClass="datatableheader"
				 								   value="${vo.superAntunAge}"> 		           	          
					              </display:column>
					              					                          	              	            
</display:table>
</hdiits:form>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dppf/Common_1.0.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"  src="script/dcps/sixPCArrearsYearly.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lListEmp" value="${resValue.lListEmp}"></c:set>
<c:set var="Index" value="1"> </c:set>
<c:set var="UpperUserList" value="${resValue.UserList}"></c:set>
<c:set var="StatusFlag" value="${resValue.StatusFlag}"></c:set>
<c:set var="counter" value="1"></c:set>
<c:set var="StatusFlag" value="${resValue.StatusFlag}"></c:set>
<c:set var="UserType" value="${resValue.UserType}"></c:set>
<c:set var="CmbSearchBy" value="${resValue.lStrCmbSearchBy}"></c:set>
<c:set var="SearchVal" value="${resValue.lStrSearchVal}"></c:set>
<c:set var="CmbCaseStatus" value="${resValue.lStrCmbCaseStatus}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>

<hdiits:form name="DCPSSixPCArrearsEntryForm" id="DCPSSixPCArrearsEntryForm" encType="multipart/form-data" validate="true" method="post" >
	<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UpperUserList[0]}"/>
	<input type="hidden" id="hidSchedule" name="hidSchedule" value="${resValue.Schedule}"/>
	<table width="100%">
	   <tr>
		  <td></td>
   		  <td>
 				<fieldset style="width: 100%" class="tabstyle">
					<legend><b>Select Year</b></legend>
   					<table align="center" width="80%" >
   					
   						<tr align="center">
   						
					       <td align="left"><fmt:message key="CMN.FINANCIALYEAR"
									bundle="${dcpsLables}"></fmt:message>
						   </td>
						   
						   <td align="left" >
						       <select name="cmbFinancialYear" id="cmbFinancialYear" >				
								 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>  
								 <c:forEach var="yearVar" items="${resValue.lListYears}">					
										<c:choose>
											<c:when test="${resValue.selectedYearId == yearVar.id}">
												<option value="${yearVar.id}" selected="selected" title="${yearVar.desc}">
													<c:out value="${yearVar.desc}"></c:out>
												</option>
											</c:when>
											<c:otherwise>
												<option value="${yearVar.id}">
													<c:out value="${yearVar.desc}"></c:out>					
												</option>
											</c:otherwise>						
										</c:choose>
										
								 </c:forEach>
							   </select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
						   </td>
						   
						 
						  <c:if test="${UserType == 'TO'}">
								   <td align="left""><fmt:message key="CMN.DDOName" bundle="${dcpsLables}"></fmt:message></td>
							  						<td align="center">
												  	 <select name="cmbDDO" id="cmbDDO" onchange="" style="width:400px">
												  	 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>  	
												  	 <c:forEach var="DDO" items="${resValue.lLstAllDDOs}">	
												  	 	<c:choose>
												  	 		<c:when test="${resValue.selectedDDO == DDO.id}">
												  	 			<option value="${DDO.id}" title="${DDO.desc}"  selected="selected" ><c:out value="${DDO.desc}"></c:out></option>
												  	 		</c:when>
												  	 		<c:otherwise>
												  	 			<option value="${DDO.id}" title="${DDO.desc}" ><c:out value="${DDO.desc}"></c:out></option>
												  	 		</c:otherwise>
												  	 	</c:choose>				
													 </c:forEach>
													 </select>
								   </td>
					  	  </c:if>
					  	   <td align="left" >
					  	   <c:if test="${UserType == 'DDO' && (StatusFlag == 'F' || StatusFlag == 'R' )}">
					  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
						               bundle="${dcpsLables}" onclick="displayDataForGivenYearDDO();" style="size:25"/>
						   </c:if>
						    <c:if test="${UserType == 'TO' &&  (StatusFlag == 'F' || StatusFlag == 'A')}">
					  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
						               bundle="${dcpsLables}" onclick="displayDataForGivenYearTO();" style="size:25"/>
						   </c:if>
						   <c:if test="${UserType == 'DDOAsst' && (StatusFlag == 'D' || StatusFlag == 'R')}">
					  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
						               bundle="${dcpsLables}" onclick="displayDataForGivenYear();" style="size:25"/>
						   </c:if>
						   
						   <hdiits:button name="btnBackWithGo" id="btnBackWithGo" type="button"  captionid="BTN.BACK"
									   bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>  
						   </td>
   						</tr>
   					</table>
			   <br></br>
	   		</fieldset>
   			</td>
   			<td></td>
   		</tr>
   		
   	   <c:if test="${lListEmp!=null}">
	   <tr>
	   		<td></td>
	   		<td>
	   		
	   	   </td>
	   	   <td></td>
	   </tr>
	   </c:if>
	   
	   <tr><td></td></tr>
	   <tr><td></td></tr>
	   
	   <tr>
		   <td></td>
		   <td>
		   <c:if test="${lListEmp!=null}">
			<fieldset style="width: 100%" class="tabstyle">
				<legend id="headerMsg">
				 <fmt:message key="CMN.MSGINYEARLY"
						bundle="${dcpsLables}"></fmt:message>
				</legend>
				<table width="100%" align="center">
				<tr><td></td></tr>
				<c:if test="${resValue.Schedule == 'hiding' }">
				<tr align="center"><td>
			 	  <fmt:message key="CMN.DESIGNATION"
						bundle="${dcpsLables}"></fmt:message>
						&nbsp;&nbsp;&nbsp;
					   <select name="cmbDesignation" id="cmbDesignation" ${displayDesig} style="width:200px" >
							<option value="All Designations"><fmt:message
												key="CMN.ALLDESIGNATIONS" /></option>
							<c:forEach var="designationVar" items="${resValue.lLstDesignation}">
												<c:choose>
													<c:when test="${resValue.DesignationId == designationVar.id}">
																<option value="${designationVar.id}" selected="selected"><c:out value="${designationVar.desc}"></c:out></option>
													</c:when>
													<c:otherwise>
																<option value="${designationVar.id}"><c:out value="${designationVar.desc}"></c:out></option>	
													</c:otherwise>
												</c:choose>
							</c:forEach>
						</select>
						<c:if test="${UserType == 'DDOAsst' && (StatusFlag == 'D' || StatusFlag == 'R')}">
						<hdiits:button name="btnSearch" type="button" captionid="BTN.SEARCH" bundle="${dcpsLables}" onclick="viewArrearsCaseListYearly();" />
						</c:if>
						<c:if test="${UserType == 'DDO' && (StatusFlag == 'F' || StatusFlag == 'R')}">
						<hdiits:button name="btnSearch" type="button" captionid="BTN.SEARCH" bundle="${dcpsLables}" onclick="viewArrearsCaseListYearlyDDO();" />
						</c:if>
						<c:if test="${UserType == 'TO' && (StatusFlag == 'F' || StatusFlag == 'A' )}">
						<hdiits:button name="btnSearch" type="button" captionid="BTN.SEARCH" bundle="${dcpsLables}" onclick="viewArrearsCaseListYearlyTO();" />
						</c:if>
				</td></tr>
				</c:if>
					<tr><td></td></tr>
					<tr><td></td></tr>
					<tr>
						<td>
						<div style="width:100%;overflow:auto;" >
						<c:choose>
							<c:when test = "${resValue.Schedule == 'no' }">
								<display:table list="${resValue.lListEmp}" id="vo" cellpadding="4" requestURI=""  style="width:70%" pagesize="100" export="false" offset="1" 
    						excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="ascending" >    	
    								      			        			      			      			  		         		         				
			              <display:setProperty name="paging.banner.placement" value="bottom" />
			                <display:column  headerClass="datatableheader" class="oddcentre"  style="text-align:center;width:10%" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
					      <input type="checkbox" id="checkbox${counter}" name="checkbox" value="${vo[1]}" />
				         
				          <input type ="hidden" name="hidName" id="hidName${counter}" value="${vo[1]}"></input>
				          <input type ="hidden" name="hidCaseStatus" id="hidCaseStatus${counter}" value="${vo[2]}"></input>
				          
						  </display:column>	
			              	
			              <display:column  style="text-align:left" title= "Schedule Id" headerClass="datatableheader" class="oddcentre" sortable="true">
			               <a href = "#" onclick = "displayDataForSchedule('${vo[1]}')">
			               		<c:out value = "${vo[1]}"/>
			               </a>
				          </display:column>		
				          
				          <display:column  style="text-align:left" title= "Financial Year" headerClass="datatableheader" class="oddcentre" sortable="true" 
			               value="${resValue.FinYearDesc}">			      
				          </display:column>
			              
			               <display:column  style="text-align:left" title= "DDO Code" headerClass="datatableheader" class="oddcentre" sortable="true" 
			               value="${resValue.DDOCODE} - ${resValue.DDONAME}">			      
				          </display:column>
				          
				          
						  
						  <c:set var="counter" value="${counter+1}"></c:set>              	         
			              </display:table>
							
							</c:when>
							
							<c:otherwise>
						
							<display:table list="${resValue.lListEmp}" id="vo" cellpadding="4" requestURI=""  style="width:100%" pagesize="100" export="false" offset="1" 
    						excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="ascending" >    	
    								      			        			      			      			  		         		         				
			              <display:setProperty name="paging.banner.placement" value="bottom" />
			              <display:column style="text-align: center;"  title="Sr.No" headerClass="datatableheader" class="oddcentre" sortable="true" 
				            value="${Index}" > 	
				           <c:set var="Index" value="${Index+1}"> </c:set>	           	          
			              </display:column>
			              	
			              <display:column  style="text-align:left" title= "Financial Year" headerClass="datatableheader" class="oddcentre" sortable="true" 
			               value="${vo[1]}">			      
				          </display:column>
			              	
			              <display:column  style="text-align:left;width:170px" title= "Employee Pension ID" headerClass="datatableheader" class="oddcentre" sortable="true" 
			               value="${vo[2]}">
				          </display:column>		          		         
			              	              	              
			              <display:column style="text-align: left;width:200px"  title="Employee Name" headerClass="datatableheader" class="oddcentre" sortable="true" 
				           value="${vo[3]}"> 		           	          
			              </display:column>
			              
			              <display:column style="text-align:left;"  title="Total Arrear Amount" headerClass="datatableheader" class="oddcentre" sortable="true" 
				          value="${vo[4]}"> 		           	          
			              </display:column>
			              
			              <display:column style="text-align: left;"  title="Paid Arrear Amount till Date" headerClass="datatableheader" class="oddcentre" sortable="true" 
			               value="${vo[5]}">
			              </display:column>
			              
				          <display:column  headerClass="datatableheader" class="oddcentre"  style="text-align:right" title= "Total Amount to be deposited" sortable="true" >
			                  <input type="text" id="amount${counter}" name="amount${counter}" value="${vo[6]}" style="text-align: right;width: 185px" align="right"  readonly="readonly" /> 	      
				          </display:column>
				          
				          <display:column headerClass="datatableheader" class="oddcentre"  style="text-align: left;"  title="SixthPC ArrearsYearly Status" sortable="true"  >
			               
			               		<c:if test="${vo[11] == 'D'}"><c:out value="Draft"></c:out></c:if>
			               		<c:if test="${vo[11] == 'F'}"><c:out value="Pending for Approval"></c:out></c:if>
			               		<c:if test="${vo[11] == 'R'}"><c:out value="Rejected"></c:out></c:if> 		
			               		<c:if test="${vo[11] == 'A'}"><c:out value="Approved"></c:out></c:if> 		 
				               		           	          
			              </display:column>
			              
			              <c:if test="${UserType == 'DDOAsst' && (StatusFlag == 'D' || StatusFlag == 'R')}">
			              <display:column  headerClass="datatableheader" class="oddcentre"  style="text-align:left" title= "Remarks(If Any)" sortable="true" >
			                  <input type="text" id="remarks${counter}" name="remarks" value="${vo[12]}"  readonly="readonly" /> 	      
				          </display:column>
				          </c:if>
				         <c:choose>
			            <c:when test="${UserType == 'TO'}">
			             <display:column  headerClass="datatableheader" class="oddcentre"  style="display:none"  >
					      	<input type="checkbox" id="checkbox${counter}" name="checkbox" value="${vo[7]}"  />
					      	 <input type="hidden" name="dcpsempId${counter}" id="dcpsempId${counter}" value="${vo[8]}">
				 		  <input type="hidden" name="dcpsId${counter}" id="dcpsId${counter}" value="${vo[2]}">
				 		  <input type="hidden" name="hidTotalAmount" id="hidTotalAmount${counter}" value="${vo[4]}" ></input>
				 		  <input type="hidden" name="hidAmountPaid" id="hidAmountPaid${counter}" value="${vo[5]}" ></input>
				 		  <input type="hidden" name="dcpsSixPcId${counter}" id="dcpsSixPcId${counter}" value="${vo[10]}">
				 		   <input type ="hidden" name="hidName" id="hidName${counter}" value="${vo[3]}"></input>
				          <input type ="hidden" name="hidCaseStatus" id="hidCaseStatus${counter}" value="${vo[11]}"></input>
						  </display:column>
					      </c:when>
					      <c:otherwise>
				          
				          <display:column  headerClass="datatableheader" class="oddcentre"  style="text-align:center" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
					      
					      
					      	<input type="checkbox" id="checkbox${counter}" name="checkbox" value="${vo[7]}" />
					 
				          <input type="hidden" name="dcpsempId${counter}" id="dcpsempId${counter}" value="${vo[8]}">
				 		  <input type="hidden" name="dcpsId${counter}" id="dcpsId${counter}" value="${vo[2]}">
				 		  <input type="hidden" name="hidTotalAmount" id="hidTotalAmount${counter}" value="${vo[4]}" ></input>
				 		  <input type="hidden" name="hidAmountPaid" id="hidAmountPaid${counter}" value="${vo[5]}" ></input>
				 		  <input type="hidden" name="dcpsSixPcId${counter}" id="dcpsSixPcId${counter}" value="${vo[10]}">
				 		   <input type ="hidden" name="hidName" id="hidName${counter}" value="${vo[3]}"></input>
				          <input type ="hidden" name="hidCaseStatus" id="hidCaseStatus${counter}" value="${vo[11]}"></input>
						  </display:column>	
						       </c:otherwise></c:choose>
						  
						  <c:set var="counter" value="${counter+1}"></c:set>              	              	              	            
     					</display:table>
					 	</c:otherwise></c:choose>
    					</div>
     					</td>
     				</tr>
     			</table>
      		</fieldset>
			<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
     		
     	</c:if> 
     	</td> 	
     	<td></td>
     	</tr>
		<tr>
		<td></td>
		<td align="center">
		
		<c:if test="${totalRecords!=0 && lListEmp!=null}"> 
		<c:if test="${UserType == 'DDOAsst' && (StatusFlag == 'D' || StatusFlag == 'R')}">
				<!--
				<td align="center" id="AddArrearsBtn">
				<hdiits:button name="btnAddArrear" id="btnAddArrear" type="button" captionid="BTN.ADDARREARSTOEMPLOYEESACCOUNT" 
						               bundle="${dcpsLables}" onclick="addYearlyArrears(1);" style="width:100%;display:none" />
				</td>
				-->
				
				<div align="center">
						<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
									   bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>  
						<hdiits:button name="btnForwardToDdo" id="btnForwardToDdo" type="button" captionid="BTN.FORWARD" 
						               bundle="${dcpsLables}" onclick="FrwrdDCPSArrearsYearly();"/>
				</div>
						
		</c:if>
		
		<c:if test="${UserType == 'DDO' && (StatusFlag == 'F' || StatusFlag == 'R')}">
						<table align="center" height="10%">  
							<tr> 
								<td width="20%" align="center" >
										<fmt:message key="CMN.REMARKS" bundle="${dcpsLables}"></fmt:message>
								</td>
								<td>
								<input  type="text"  id="txtRemarks" name="txtRemarks" size="100" value="" />
								</td>
							</tr>
					    </table> 
					    
		    <div>&nbsp;</div>
	        <div>&nbsp;</div>
	        <div>&nbsp;</div>
		      
				 <div align="center" id="SixpcArrearsYearly">
			
				 		<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
									   bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>     
						<hdiits:button name="btnForwardToDdo" id="btnForwardToDdo" type="button" captionid="BTN.FORWARD" 
						               bundle="${dcpsLables}" onclick="approveDCPSArrearsYearlyDDO();"  />
						<hdiits:button name="btnRejectToDdoAsst" id="btnRejectToDdoAsst" type="button" captionid="BTN.REJECT" 
						               bundle="${dcpsLables}" onclick="rejectDCPSArrearsYearlyDDO();" />
				</div>
		</c:if>
				
		<c:if test="${UserType == 'TO' && (StatusFlag == 'F' || StatusFlag == 'A' )}">
						<table align="center" height="10%">  
									<tr> 
										<td width="20%" align="center" >
												<fmt:message key="CMN.REMARKS" bundle="${dcpsLables}"></fmt:message>
										</td>
										<td>
											<input  type="text"  id="txtRemarks" name="txtRemarks" size="100" value="" />
												 
										</td>
									</tr>
						    </table> 
		    <div>&nbsp;</div>
		    <div>&nbsp;</div>
		    <div>&nbsp;</div>
		    <div align="center" id="SixpcArrearsYearly">
		    			
		    			<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
									   bundle="${dcpsLables}" onclick="ReturnForArrearsYearlyInTOLogin();"/>     
						<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.APPROVE" 
						               bundle="${dcpsLables}" onclick="approveDCPSArrearsYearlyTO();" />
						<hdiits:button name="btnRejectToDdo" id="btnRejectToDdo" type="button" captionid="BTN.REJECT" 
						               bundle="${dcpsLables}" onclick="rejectDCPSArrearsYearlyTO();" />
		</div>
					
				<!--<td>	<hdiits:button name="btnApproveAll" id="btnApproveAll" type="button" captionid="BTN.APPROVEALL" 
						               bundle="${dcpsLables}" onclick="approveDCPSArrearsYearlyTO();checkUncheckAll(this,'checkbox');" style="width:100%"/>
				</td>
				-->
			
		</c:if>
		</c:if>	
		</td>
		<td></td>
		</tr>

</table>	    
	
</hdiits:form>
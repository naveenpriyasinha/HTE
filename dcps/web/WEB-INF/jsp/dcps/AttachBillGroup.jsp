<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/attachBillGroup.js"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="empListForBG" value="${resValue.empListForBG}"></c:set>
<c:set var="billgroupList" value="${resValue.billgroupList}"></c:set>
<c:set var="showgroupList" value="${resValue.showgroupList}"></c:set>
<c:set var="MstEmpObjList" value="${resValue.MstEmpObjList}"></c:set>
<c:set var="typeOfOperation" value="${resValue.typeOfOperation}"></c:set>
<c:set var="attachDetachFlag" value="${resValue.attachDetachFlag}"></c:set>



<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<hdiits:form name="frmDCPSAttachBill" action="" id="frmDCPSAttachBill" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.BGDETAILS" bundle="${dcpsLables}"></fmt:message></b>
</legend>
<c:set var="totalEmplyeesAttached" value="0"></c:set>
<div style="padding-left:175px">
<table width="70%" align="center" >
		<tr>
			<td align="left" style="width:20%"><fmt:message key="CMN.BillGroup"
							bundle="${dcpsLables}"></fmt:message>
							</td>
			<td align="left" style="width:80%">
								<select name="cmbBillGroup" id="cmbBillGroup"  onChange="" style="width:80%">
													<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
														<c:forEach var="billgroup" items="${resValue.billgroupList}" >
																			<c:choose>
																					<c:when test="${resValue.billGroupIdPassed == billgroup[1]}">
																					<option value="${billgroup[1]}" selected="selected" ><c:out 
																												value="${billgroup[0]} - ${billgroup[2]}"></c:out></option>
																					</c:when>
																					<c:otherwise>
																					<option value="${billgroup[1]}" title="${billgroup[2]}"><c:out 
																												value="${billgroup[0]} - ${billgroup[2]}"></c:out></option>
																					</c:otherwise>
																			</c:choose>
																			
														</c:forEach>
								</select>
								<label class="mandatoryindicator">*</label>	
			</td>
		</tr>
		<tr/>
		<tr/>
		
		<tr>
			<td align="left" style="width:20%" ><fmt:message key="CMN.TYPEOFOPERATION"
							bundle="${dcpsLables}"></fmt:message>			
			</td>
			
			<td align="left" style="width:80%">
				
				<select name="cmbTypeofAttachDetach" id="cmbTypeofAttachDetach"  onChange="" style="width:80%" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:choose>
						<c:when test="${resValue.typeOfOperation == 1}">
							<option value="1" selected="selected">Attach Detach Employee</option>
							<option value="2">Attach Detach Vacant Post</option>
						</c:when>
						<c:when test="${resValue.typeOfOperation == 2}">
							<option value="1">Attach Detach Employee</option>
							<option value="2" selected="selected">Attach Detach Vacant Post</option>
						</c:when>
						<c:otherwise>
							<option value="1">Attach Detach Employee</option>
							<option value="2">Attach Detach Vacant Post</option>
						</c:otherwise>
					</c:choose>
				</select>
				
				<label class="mandatoryindicator">*</label>	
			</td>
			
		</tr>

		
		
</table>
</div>
<br/>
<div align="center">
			<hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" bundle="${dcpsLables}" onclick="displayListsAfterValidation()" />
			<hdiits:button	name="btnBackWithGo" id="btnBackWithGo" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnLoginPage();" />
</div>

<br/>
</fieldset>

<br/>
<c:if test="${(resValue.billGroupIdPassed != null)}">
	<c:if test="${(resValue.typeOfOperation == 1)}">
			<fieldset class="tabstyle">
			<legend>
				<b><fmt:message key="CMN.ATTACHDETACHEMPLOYEE" bundle="${dcpsLables}"></fmt:message></b>
			</legend>
			<br/>
			<br/>
			<br/>
			<table>
			<tr>
			</tr>
			</table>
			
				 <input type="hidden" name="counterEmp" id="counterEmp" value="0"/>
				 <input type="hidden" name="counterEmpBG" id="counterEmpBG" value="0"/>
			<table width="100%" >     
			     <tr>
			         <td width="40%" valign="top">
			         <c:choose>
			         	<c:when test="${empList != null}">
			         		 <div style="width:100%;overflow:auto;height:400px;" >
			             		<display:table list="${empList}" id="tableEmp" requestURI=""  export="false" style="width:100%" partialList="true" 
									 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="1" defaultorder="ascending" cellpadding="5" >
									 
							 		<display:setProperty name="paging.banner.placement" value="bottom"/>		      			        			      			  
				    			    <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
							        <input type="checkbox" name="GroupCheck" id="GroupCheck${tableEmp_rowNum}" value="${tableEmp.dcpsEmpId}" />
							        <script>
												document.getElementById("counterEmp").value=Number(document.getElementById("counterEmp").value) + 1;
									</script>
						            </display:column>		          
						         
						            <display:column style="text-align: left;" class="tablecelltext" title="Employees (All)" headerClass="datatableheader"
						            sortable="true"> 		
						            <label id="empName${tableEmp_rowNum}"><b>${tableEmp.name}</b></label>      
					                </display:column>
			            		 </display:table>	
			            	 </div>      
			         	</c:when>
			         	<c:otherwise>
			         				<table id="tableEmp" title="" border="2" bordercolor="gray" cellpadding="3"  width="85%" >
			             					<tr>
			             						<td width="20%" align="center" ><input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/></td>
			             						<td width="80%" align="center" style="color: rgb(200,100,20);font-size: small;font-style: normal" ><b><u>Employees (All)</u></b></td>
			             					</tr>
			             			</table>
			         	</c:otherwise>
			         </c:choose>
			         	       	
			         </td>                  
			        
			         <td width="20%" align="center">
			         		<table>
			         		<tr>
			         			<td>
			         			<hdiits:button type="button" captionid="BTN.ATTACH" bundle="${dcpsLables}" id="btnAttach" name="btnAttach" onclick="validateBeforeAttach()" ></hdiits:button>
			         			</td>
			         		</tr>
			         		<tr><td>&nbsp;</td></tr>
			         		<tr><td>&nbsp;</td></tr>
			         		<tr>
			         			<td>
			         			<hdiits:button type="button" captionid="BTN.DETACH" bundle="${dcpsLables}" id="btnDetach" name="btnDetach" onclick="validateBeforeDetach()" ></hdiits:button>
			         			</td>
			         		</tr>
			         		</table>
			         </td>
			        		
			         <td width="40%" valign="top">
			        	<c:choose>
				        	 <c:when test="${empListForBG != null}">
				        	 	     <div style="width:100%;overflow:auto;height:400px;" >
						         	 <display:table list="${empListForBG}"  id="tableEmpBG"  requestURI="" export="" style="width:100%,border: thin;"   cellpadding="5" >
									 <display:setProperty name="paging.banner.placement" value="bottom"/>		      			        			      			  
						    			  <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAllBG' onclick='checkUncheckAllBG(this)'/>" >
									      <input type="checkbox" name="GroupCheckBG" id="GroupCheckBG${tableEmpBG_rowNum}" value="${tableEmpBG.dcpsEmpId}" />
									       <script>
														document.getElementById("counterEmpBG").value=Number(document.getElementById("counterEmpBG").value) + 1;
										   </script>
								          </display:column>		          
								         
								          <display:column style="text-align: left;" class="tablecelltext" title="Employees (${resValue.BillGroupDtls})" headerClass="datatableheader"
								           sortable="true" > 		
								          <label id="empNameBG${tableEmpBG_rowNum}"><b>${tableEmpBG.name}</b></label>
							              </display:column>
						             </display:table>
						             </div>
				             </c:when>
			            	 <c:otherwise>
			             	 <c:set var="counter" value="1" ></c:set>
			             			<table id="tableEmpBG" title="" border="2" bordercolor="gray" cellpadding="3"  width="85%" >
			             					<tr>
			             						<td width="20%" align="center" ><input type='checkbox' name='SelectAllBG' onclick='checkUncheckAllBG(this)'/></td>
			             						<td width="80%" align="center" style="color: rgb(200,100,20);font-size: small;font-style: normal" ><b><u>Employees (${resValue.BillGroupDtls})</u></b></td>
			             					</tr>
			             			</table>
			            	 </c:otherwise>
			             </c:choose>
			         </td>
			    </tr>
			</table>
			<br/>
			
			<table width="100%">
			<tr>
			<td width="50%"><input type="hidden" id="dcpsEmpIdstoBeDetached" name="dcpsEmpIdstoBeDetached" value="" size="100" ></td>
			<td width="50%"><input type="hidden" id="dcpsEmpIdstoBeAttached" name="dcpsEmpIdstoBeAttached" value="" size="100" ></td>
			</tr>
			</table>
			<br/>
			
			<div align="center">
						<hdiits:button	name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="AttachAndDetachEmp();" />
						<hdiits:button	name="btnBack" id="btnBack" type="button"	captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnLoginPage();" />
			</div>
			
			</fieldset>
	</c:if>
</c:if>
<c:if test="${resValue.billGroupIdPassed != null}">
	<c:if test="${(resValue.typeOfOperation == 2)}">
		<fieldset class="tabstyle">
		<legend>
			<b><fmt:message key="CMN.ATTACHDETACHPOST" bundle="${dcpsLables}"></fmt:message></b>
		</legend>
		<br/>
		<br/>
		<table>
		<tr>
		</tr>
		</table>
		
			 <input type="hidden" name="counterEmp" id="counterEmp" value="0"/>
			 <input type="hidden" name="counterEmpBG" id="counterEmpBG" value="0"/>
		<table width="100%" >     
		     <tr>
		         <td width="40%" valign="top">
		         <c:choose>
		         	<c:when test="${empList != null}">
		         		 <div style="width:100%;overflow:auto;height:400px;" >
		             		<display:table list="${empList}" id="tableEmp" requestURI=""  export="false" style="width:100%" partialList="true" 
								 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="1" defaultorder="ascending" cellpadding="5" >
								 
						 		<display:setProperty name="paging.banner.placement" value="bottom"/>		      			        			      			  
			    			    <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
						        <input type="checkbox" name="GroupCheck" id="GroupCheck${tableEmp_rowNum}" value="${tableEmp.postId}" />
						        <script>
											document.getElementById("counterEmp").value=Number(document.getElementById("counterEmp").value) + 1;
								</script>
					            </display:column>		          
					         
					            <display:column style="text-align: left;" class="tablecelltext" title="POSTS (All)" headerClass="datatableheader"
					            sortable="true"> 		
					            <label style="text-transform: uppercase" id="empName${tableEmp_rowNum}"><b>${tableEmp.postName}</b></label>      
				                </display:column>
		            		 </display:table>	
		            	 </div>      
		         	</c:when>
		         	<c:otherwise>
		         				<table id="tableEmp" title="" border="2" bordercolor="gray" cellpadding="3"  width="85%" >
		             					<tr>
		             						<td width="20%" align="center" ><input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/></td>
		             						<td width="80%" align="center" style="color: rgb(200,100,20);font-size: small;font-style: normal" ><b><u>Posts (All)</u></b></td>
		             					</tr>
		             			</table>
		         	</c:otherwise>
		         </c:choose>
		         	       	
		         </td>                  
		        
		         <td width="20%" align="center">
		         		<table>
		         		<tr>
		         			<td>
		         			<hdiits:button type="button" captionid="BTN.ATTACH" bundle="${dcpsLables}" id="btnAttach" name="btnAttach" onclick="validateBeforeAttach()" ></hdiits:button>
		         			</td>
		         		</tr>
		         		<tr><td>&nbsp;</td></tr>
		         		<tr><td>&nbsp;</td></tr>
		         		<tr>
		         			<td>
		         			<hdiits:button type="button" captionid="BTN.DETACH" bundle="${dcpsLables}" id="btnDetach" name="btnDetach" onclick="validateBeforeDetach()" ></hdiits:button>
		         			</td>
		         		</tr>
		         		</table>
		         </td>
		        		
		         <td width="40%" valign="top">
		        	<c:choose>
			        	 <c:when test="${empListForBG != null}">
			        	 	     <div style="width:100%;overflow:auto;height:400px;" >
					         	 <display:table list="${empListForBG}"  id="tableEmpBG"  requestURI="" export="" style="width:100%,border: thin;"   cellpadding="5" >
								 <display:setProperty name="paging.banner.placement" value="bottom"/>		      			        			      			  
					    			  <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAllBG' onclick='checkUncheckAllBG(this)'/>" >
								      <input  type="checkbox" name="GroupCheckBG" id="GroupCheckBG${tableEmpBG_rowNum}" value="${tableEmpBG.postId}" />
								       <script>
													document.getElementById("counterEmpBG").value=Number(document.getElementById("counterEmpBG").value) + 1;
									   </script>
							          </display:column>		          
							         
							          <display:column style="text-align: left;" class="tablecelltext" title="POSTS (${resValue.BillGroupDtls})" headerClass="datatableheader"
							           sortable="true" > 		
							          <label style="text-transform: uppercase" id="empNameBG${tableEmpBG_rowNum}"><b>${tableEmpBG.postName}</b></label>
							          
						              </display:column>
					             </display:table>
					             </div>
			             </c:when>
		            	 <c:otherwise>
		             	 <c:set var="counter" value="1" ></c:set>
		             			<table id="tableEmpBG" title="" border="2" bordercolor="gray" cellpadding="3"  width="85%" >
		             					<tr>
		             						<td width="20%" align="center" ><input type='checkbox' name='SelectAllBG' onclick='checkUncheckAllBG(this)'/></td>
		             						<td width="80%" align="center" style="color: rgb(200,100,20);font-size: small;font-style: normal" ><b><u>Posts (${resValue.BillGroupDtls})</u></b></td>
		             					</tr>
		             			</table>
		            	 </c:otherwise>
		             </c:choose>
		         </td>
		    </tr>
		</table>
		<br/>
		
		<table width="100%">
		<tr>
		<td width="50%"><input type="hidden" id="dcpsEmpIdstoBeDetached" name="dcpsEmpIdstoBeDetached" value="" size="100" ></td>
		<td width="50%"><input type="hidden" id="dcpsEmpIdstoBeAttached" name="dcpsEmpIdstoBeAttached" value="" size="100" ></td>
		</tr>
		</table>
		<br/>
		
		<div align="center">
					<hdiits:button	name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="AttachAndDetachEmp();" />
					<hdiits:button	name="btnBack" id="btnBack" type="button"	captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnLoginPage();" />
		</div>
		
		</fieldset>
	</c:if>
</c:if>
<input type = "hidden" name = "actionFlag"  id = "actionFlag">
<input type = "hidden" name = "elementId"  id = "elementId">
<input type = "hidden" name = "billGroupId"  id = "billGroupId">
<input type = "hidden" name = "typeOfAttachDetach"  id = "typeOfAttachDetach">
<input type = "hidden" id="hdnAttachDetachFlag" name="hdnAttachDetachFlag" value="${resValue.attachDetachFlag}" >
<input type = "hidden" id="payMonth" name="payMonth" value="${resValue.payMonth}" >
<input type = "hidden" id="payYear" name="payYear" value="${resValue.payYear}" >
</hdiits:form>
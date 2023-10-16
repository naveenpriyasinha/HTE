<%@ include file="../core/include.jsp"%>
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
<script type="text/javascript"  src="script/dcps/sixPCArrears.js"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="ArrearsDateLimit" value="${resValue.ArrearsDateLimit}"/>
<c:set var="Index" value="1"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="UpperUserList" value="${resValue.UserList}"></c:set>
<c:set var="StatusFlag" value="${resValue.StatusFlag}"></c:set>
<c:set var="CmbSearchBy" value="${resValue.lStrCmbSearchBy}"></c:set>
<c:set var="SearchVal" value="${resValue.lStrSearchVal}"></c:set>
<c:set var="CmbCaseStatus" value="${resValue.lStrCmbCaseStatus}"></c:set>
<c:set var="counter" value="1"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<hdiits:form name="frm6PcArrearEntry"  id="frm6PcArrearEntry" encType="multipart/form-data" validate="true" method="post" >
<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UpperUserList[0]}"/>
<input type ="hidden" name="hidStatus" id="hidStatus" value="${StatusFlag}"></input>




<fieldset style="width: 100%" class="tabstyle">
	<legend id="headerMsg">
		<b>  6th PC Arrear Amount Deposition To DCPS Contribution Entry Form </b>
	</legend> 
	<div>&nbsp;</div>
	<c:choose>

<c:when test="${totalRecords != 0}">


<div align="center">
<fmt:message key="CMN.DESIGNATION"
			bundle="${dcpsLables}"></fmt:message>
		<select name="cmbDesignation" id="cmbDesignation" ${displayDesig}>
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
		<c:if test="${StatusFlag == 'D'  || StatusFlag == 'R'}">
		<hdiits:button name="btnSearch" type="button" captionid="BTN.SEARCH" style="display:inline" bundle="${dcpsLables}" onclick="viewArrearsCaseList();" />
		</c:if>
		<c:if test="${StatusFlag == 'F' || StatusFlag == 'A'}">
		<hdiits:button name="btnSearch" type="button" style="display:inline" id="searchBtnId" captionid="BTN.SEARCH" bundle="${dcpsLables}" onclick="viewArrearsCaseListDDO();" />
		</c:if>
</div>

<br/>
<br/>


	<div style="width:100%;overflow:auto;" >
	<display:table list="${empList}" id="vo" cellpadding="4" requestURI="" style="width:100%" pagesize="100" export="false" offset="1" 
		excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="1" defaultorder="ascending" >    		
	<display:setProperty name="paging.banner.placement" value="bottom" />		      			        			      			      			  		         		         				
	              	 
	              <display:column style="text-align: left;" class="oddcentre" title="Sr.No" headerClass="datatableheader"  sortable="true" 
		            value="${Index}" > 	
		           <c:set var="Index" value="${Index+1}"> </c:set>	           	          
	              </display:column>
	              	
	              <display:column  style="text-align:left" title= "Employee Pension ID" headerClass="datatableheader" class="oddcentre" sortable="true" 
	               value="${vo[1]}">		
		          </display:column>		          		         
	              	              	              
	              <display:column style="text-align: left;width:200px"  title="Employee Name" headerClass="datatableheader" class="oddcentre" sortable="true" 
		           value="${vo[2]}"> 		           	          
	              </display:column>
	              
	              <display:column style="text-align: left;"  title="From Date" headerClass="datatableheader" class="oddcentre" sortable="true" >
	              
	             	  <c:choose>
							<c:when test="${vo[8]!=null}">
								<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[8]}" var="fromArrearsDate"/>
								<input type="text" name="fromDate${counter}" id="fromDate${counter}" value="${fromArrearsDate}"	 style="text-align: left" readonly="readonly" />
							</c:when>
							
							<c:otherwise>
						              <c:choose>
						              	<c:when test="${vo[7]<ArrearsDateLimit}"> 
						              		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.ArrearsDateLimit}" var="fromArrearsDate"/>
							              			<input type="text" name="fromDate${counter}" id="fromDate${counter}" value="${fromArrearsDate}"	 style="text-align: left" readonly="readonly" />
										</c:when>
										<c:otherwise>
											<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[7]}" var="fromArrearsDate"/>
							              			<input type="text" name="fromDate${counter}" id="fromDate${counter}" value="${fromArrearsDate}"	style="text-align: left" readonly="readonly" />
										</c:otherwise> 	
									  </c:choose>		
					 	    </c:otherwise>
					  </c:choose>	 
	              					           	          
	              </display:column>
	              
	              <display:column style="text-align: left;"  title="To Date" headerClass="datatableheader" class="oddcentre" sortable="true" >
	              
			                <c:choose>
								<c:when test="${vo[9]!=null}">
									<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[9]}" var="ToDate"/>
								</c:when>
								<c:otherwise>
									<c:set var="ToDate" value="31/03/2009"></c:set>
								</c:otherwise>
							</c:choose>	 	
						
		           			<input type="text" name="toDate${counter}" id="toDate${counter}" value="${ToDate}" style="text-align: left"  readonly="readonly" />
	              </display:column>
	              
	              <display:column  style="text-align: right;"  title="Total Amount to be deposited" headerClass="datatableheader" class="oddcentre" sortable="true" >
	              	<c:choose>
	              		<c:when test="${resValue.UserType == 'DDO'}">
		                	<input type="text" id="amount${counter}" align="right" name="amount${counter}" value="${vo[3]}" style="text-align: right" readonly="readonly" />
		                </c:when>
		                <c:otherwise>
		                	<input type="text" id="amount${counter}" align="right" name="amount${counter}" value="${vo[3]}" style="text-align: right"   />
		                </c:otherwise> 
		            </c:choose>		           	          
	              </display:column>
	              
	               <display:column style="text-align:left;"  title="SixthPC Arrears Status" headerClass="datatableheader" class="oddcentre" sortable="true" value="${vo[10]}">
	               		<%-- <c:if test="${vo[5] == 'D'}"><c:out value="Draft"></c:out></c:if>
	               		<c:if test="${vo[5] == 'F'}"><c:out value="Pending for Approval"></c:out></c:if>
	               		<c:if test="${vo[5] == 'R'}"><c:out value="Rejected"></c:out></c:if> 		
	               		<c:if test="${vo[5] == 'A'}"><c:out value="Approved"></c:out></c:if>  --%>		 		           	          
	              </display:column>
	              
	              <c:if test="${StatusFlag == 'D' || StatusFlag == 'R'}">
	               <display:column  style="text-align: left;"  title="Remarks(If Any)" headerClass="datatableheader" class="oddcentre" sortable="true"  >
		                <input type="text" id="remarks${counter}" name="remarks" value="${vo[6]}"  readonly="readonly"/> 		           	          
	              </display:column>
	              </c:if>
	              
	              <c:choose>
	              	<c:when test="${StatusFlag == 'D' || StatusFlag == 'R'}">
	              			 <display:column  style="text-align:center;display:none" class="oddcentre" title="<input name='chkSelect' style='display:none' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>" headerClass="datatableheader">
					              <c:choose>
					              	<c:when test="${vo[5] == 'A'}">
					              		<input type="checkbox" id="checkbox${counter}" name="SelectAll" value="${vo[4]}" disabled="disabled" />
					              	</c:when>
					              	<c:otherwise>
					              		<input type="checkbox" id="checkbox${counter}" name="SelectAll" value="${vo[4]}"/>
					              	</c:otherwise>
					              </c:choose>
									  <input type ="hidden" name="hidName" id="hidName${counter}" value="${vo[2]}"></input>
							          <input type ="hidden" name="hidCaseStatus" id="hidCaseStatus${counter}" value="${vo[5]}"></input>
							          <input type="hidden" name="hidDcpsEmpId" id="hidDcpsEmpId${counter}" value="${vo[0]}"></input>
							          <input type="hidden" name="hidTotalAmount" id="hidTotalAmount${counter}" value="${vo[3]}"></input>	
		         			 </display:column>
	              	</c:when>
	              	<c:otherwise>
	              			 <display:column  style="text-align:center" class="oddcentre" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>" headerClass="datatableheader">
					              <c:choose>
					              	<c:when test="${vo[5] == 'A'}">
					              		<input type="checkbox" id="checkbox${counter}" name="SelectAll" value="${vo[4]}" disabled="disabled" />
					              	</c:when>
					              	<c:otherwise>
					              		<input type="checkbox" id="checkbox${counter}" name="SelectAll" value="${vo[4]}"/>
					              	</c:otherwise>
					              </c:choose>
					              	  <input type ="hidden" name="hidName" id="hidName${counter}" value="${vo[2]}"></input>
							          <input type ="hidden" name="hidCaseStatus" id="hidCaseStatus${counter}" value="${vo[5]}"></input>
							          <input type="hidden" name="hidDcpsEmpId" id="hidDcpsEmpId${counter}" value="${vo[0]}"></input>
							          <input type="hidden" name="hidTotalAmount" id="hidTotalAmount${counter}" value="${vo[3]}"></input>	
		         			 </display:column>
	              	</c:otherwise>
	              </c:choose>
		          <c:set var="counter" value="${counter+1}"></c:set>
</display:table>
</div>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}" /> 	
<br/>

<table align="center">
<tr>
<td align="right">
<c:if test="${totalRecords != 0}">
<c:if test="${StatusFlag == 'D'  || StatusFlag == 'R'}">
	<div align="center" id="sixPCArrears">
		<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
						 bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>
		<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" 
		               bundle="${dcpsLables}" onclick="saveData();"  />
		<hdiits:button name="btnForwardToDdo" id="btnForwardToDdo" type="button" captionid="BTN.FORWARD" 
		               bundle="${dcpsLables}"   onclick="saveAndfrwrdData();"/>
	</div>
</c:if>
<c:if test="${StatusFlag == 'F' || StatusFlag == 'A'}">
	
	 <table align="left" height="10%">  
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
		    <div align="center" id="sixPCArrears">  
		    
		<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
						 bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>        
		<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.APPROVE" 
		               bundle="${dcpsLables}" onclick="approveDCPSArrearsCase();" />
		<hdiits:button name="btnReject" id="btnReject" type="button" captionid="BTN.REJECT" 
		               bundle="${dcpsLables}"  onclick="rejectDCPSArrearsCase();"/>
	
	</div>		         
		       
		               
</c:if>
</c:if>
</td>
</tr>
</table>

</c:when>
<c:otherwise>
<b>There is currently no request in your worklist.</b><div>&nbsp;</div>
<div>&nbsp;</div>
<div align="center">
<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
						 bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>  
</div>
</c:otherwise></c:choose>
</fieldset>

</hdiits:form>	     
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/offlineEntryCorrection.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<hdiits:form name="DCPSOfflineEntryCorrectionForm" id="DCPSOfflineEntryCorrectionForm" encType="multipart/form-data"
	validate="true" method="post" >
	
	<fieldset class="tabstyle">
		
		<table align="center" width="85%">
					<tr>
								<td width="20%" style="padding-left: 100px"><fmt:message
										key="CMN.TreasuryName" bundle="${dcpsLables}"></fmt:message>
										</td>
								<td width="30%">
								   <select name="cmbTreasuryCode" id="cmbTreasuryCode" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
																					<option value="1001" selected="selected"><c:out 
																							value="TreasuryName"></c:out></option>
										
									    </select>
								  		<label class="mandatoryindicator">*</label>
								</td>
								<td width="20%" style="padding-left: 75px"><fmt:message
										key="CMN.DDOName" bundle="${dcpsLables}"></fmt:message>
										</td>
								<td width="30%">
										<select name="cmbDDOCode" id="cmbDDOCode" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
																					<option value="DDO1234567" selected="selected" ><c:out 
																							value="DDOName"></c:out></option>
										
									    </select>
									    <label class="mandatoryindicator">*</label>
								</td>			
					</tr>
					
					<tr>
								<td width="20%" style="padding-left: 100px" ><fmt:message
										key="CMN.BillGroup" bundle="${dcpsLables}"></fmt:message>
										</td>
								<td width="30%">
										<select name="cmbBillGroup" id="cmbBillGroup" style="width:240px"  onChange="getSchemeforBillGroup();" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
											<c:forEach var="billGroup" items="${resValue.BILLGROUPS}" >
															<c:choose>
																<c:when test="${resValue.lLongbillGroupId == billGroup.id}">
																					<option value="${billGroup.id}" selected="selected"><c:out 
																							value="${billGroup.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${billGroup.id}"><c:out 
																							value="${billGroup.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
									    </select>
									    <label class="mandatoryindicator">*</label>
								</td>	
								
								<td width="20%" style="padding-left: 75px" ><fmt:message
										key="CMN.Scheme" bundle="${dcpsLables}"></fmt:message>
										</td>
								<td width="30%"><input type="text" name="txtSchemeName"  id="txtSchemeName" value="${resValue.schemename}" size="31"  />
								<input type="hidden" name="schemeCode"  id="schemeCode" value="${resValue.schemeCode}" />
								<label class="mandatoryindicator">*</label>
								</td>
				   </tr>
					
					<tr>
								<td width="20%" style="padding-left: 100px" ><fmt:message
										key="CMN.Month" bundle="${dcpsLables}"></fmt:message>
										</td>
								
								<td width="30%">
										<select name="cmbMonth" id="cmbMonth" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
											<c:forEach var="month" items="${resValue.MONTHS}" >
															<c:choose>
																<c:when test="${resValue.monthId == month.id}">
																					<option value="${month.id}" selected="selected"><c:out 
																							value="${month.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${month.id}"><c:out 
																							value="${month.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
									    </select>
									    <label class="mandatoryindicator">*</label>
								</td>	
								<td width="20%" style="padding-left: 75px"><fmt:message
										key="CMN.Year" bundle="${dcpsLables}"></fmt:message>
										</td>
							
								<td width="30%">
										<select name="cmbYear" id="cmbYear" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
											<c:forEach var="year" items="${resValue.YEARS}" >
															<c:choose>
																				<c:when test="${resValue.yearId == year.id}">
																					<option value="${year.id}" selected="selected"><c:out 
																							value="${year.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${year.id}"><c:out 
																							value="${year.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
									    </select>
									    <label class="mandatoryindicator">*</label>
								</td>
					</tr>
					
					<tr>
								<td width="20%" id="go" align="center" style="display:block;" colspan="4" ><hdiits:button
															name="btnGo" id="btnGo" type="button"
															captionid="BTN.GO" bundle="${dcpsLables}"
															onclick="getEmpListforContri();" />
								<input type="hidden" id="ddoCode" value="${resValue.DDOCODE}"/>
								</td>
								<td width="20%"></td>
								<td width="30%"></td>
								<td width="30%"></td>
					</tr>
	    </table>		
		</fieldset>
		
		<br/>
		<br/>
		
	<c:if test="${empList!=null}">
	
	<fieldset class="tabstyle">
	<br/>
	<script type="text/javascript">
		LISTPAYCOMMISSIONS='';
		LISTTYPESOFPAYMENT='';
		</script>
		
		<c:forEach var="EventList" items="${resValue.listPayCommission}" >
		<script> LISTPAYCOMMISSIONS += "<option value='${EventList.lookupId}'> ${EventList.lookupDesc}</option>";</script>
		</c:forEach>
		
		<c:forEach var="EventList" items="${resValue.listTypeOfPayment}" >
		<script> LISTTYPESOFPAYMENT += "<option value='${EventList.lookupId}'> ${EventList.lookupDesc}</option>";</script>
		</c:forEach>
		
		
	<div style="width:100%;overflow:auto;height:100%;"  align="center">
		<display:table list="${empList}"  id="contributionTable"   requestURI="" export="" style="width:20%"  pagesize="5">
		<display:setProperty name="paging.banner.placement" value="none" />		
		
				<display:column headerClass="datatableheader" style="text-align:center;border: none;"   sortable="true" titleKey="CMN.NAME" >
				
						<a href=# onclick="AddNewRowContri('${contributionTable[3]}');"><c:out value="${contributionTable[2]}"></c:out></a>
						<input type="hidden" name="dcpsempId" id="dcpsempId${contributionTable_rowNum}" value="${contributionTable[0]}"/>
						<input type="hidden" name="dcpsContributionId" id="dcpsContributionId${contributionTable_rowNum}" value="${contributionTable[3]}"/>
						
				</display:column>
				
				
				<display:column headerClass="datatableheader"  style="text-align:center;border: none;"   sortable="true" titleKey="CMN.DCPSID" >
				<label><c:out value="${contributionTable[1]}"></c:out></label>
				</display:column>
				
		</display:table>
		
	</div>
	</fieldset>
	
	<br/>
	<br/>
	
	</c:if>
	
	<div id="contributionAddRowTableDiv" style="display:none">
	
	<fieldset class="tabstyle">
	
	<div style="width:100%;overflow:auto;height:100%;" >
	<display:table list="" id="contributionAddRowTable" requestURI="" export="" style="width:100%,border: thin;" pagesize="5">
	<display:setProperty name="paging.banner.placement" value="bottom" />		
	
			<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;border: none;"   sortable="true" titleKey="CMN.NAME" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;border: none;"   sortable="true" titleKey="CMN.DCPSID" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.CONTRISTARTDATE" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;" sortable="true"  titleKey="CMN.CONTRIENDDATE" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.PAYCOMIMISSION" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.PAYMENTTYPE" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.BASIC" >
			</display:column>
						
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.DP" >  
		    </display:column>
						
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.DA" > 
			</display:column>
					    
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.CONTRIBUTION" >
		    </display:column> 
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  title="<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
			</display:column>
	
	</display:table>
	</div>
	</fieldset>
	
	</div>
	
	<br/>
	<br/>
	
	<div id="saveAndDelete" style="display:none">
	
	<fieldset class="tabstyle">
	<table>
			<tr>
			<td width="30%">
			<input type="radio" id="voucherOrChallan" name="voucherOrChallan" value="Voucher" checked/>
			<fmt:message key="CMN.VOUCHER"
						bundle="${dcpsLables}"></fmt:message> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" id="voucherOrChallan" name="voucherOrChallan" value="Challan" />
			<fmt:message key="CMN.CHALLAN"
						bundle="${dcpsLables}"></fmt:message> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="40%">&nbsp;</td>
			<td width="30%">&nbsp;</td>
			</tr>
			
			<tr>
			<td width="30%">
			<fmt:message key="CMN.VCNO"
						bundle="${dcpsLables}"></fmt:message>
			
			<input type="text" id="txtVCNO"
				size="30" name="txtVCNO" value="" />
			<label class="mandatoryindicator">*</label> 
			</td>
			
			<td width="40%" align="center">
			<fmt:message key="CMN.VCDATE"
						bundle="${dcpsLables}"></fmt:message>
			<input type="text" name="txtVCDate" id="txtVCDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
						onBlur="validateDate(this);" value="" /> 
						<img src='images/CalendarImages/ico-calendar.gif' 
						onClick='window_open("txtVCDate",375,570)'
						style="cursor: pointer;" />
			<label class="mandatoryindicator">*</label> 
			</td>
			
			<td width="30%" align="right">
			<fmt:message key="CMN.BILLNO"
						bundle="${dcpsLables}"></fmt:message>
			<input type="text" id="txtBILLNO"
				size="30" name="txtBILLNO" value="" />
			<label class="mandatoryindicator">*</label> 
			</td>
			</tr>
	</table>
	
	</fieldset>
	<br/>
	<br/>
	 	 <table width="100%">
         	<tr>
		        <td width="33%" id="forwardBtn" align="right"  style="display:block;"><hdiits:button
																	name="btnForwardData" id="btnForwardData" type="button"	style="width:30%" 
																	captionid="BTN.FORWARD" bundle="${dcpsLables}"
																	onclick="forwardContributionData()" />
		        </td>
		        <td width="33%" id="saveBtn" align="center" style="display:block;"><hdiits:button
																	name="btnSaveData" id="btnSaveData" type="button"	style="width:30%" 
																	captionid="BTN.SAVE" bundle="${dcpsLables}"
																	onclick="saveContributionData()" />
										</td>
				<td width="33%" id="deleteEmpBtn" align="left" style="display:block;">
																<hdiits:button
																	name="btnDeleteEmp" id="btnDeleteEmp" type="button"  style="width:30%"  
																	captionid="BTN.DELETEEMP" bundle="${dcpsLables}"
																	onclick="deleteContributionData()" />
				</td>
        	</tr>
        </table>
   </div>
	
</hdiits:form>
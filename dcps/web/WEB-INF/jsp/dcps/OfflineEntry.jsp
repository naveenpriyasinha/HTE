<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<script language="JavaScript" src="script/dcps/offlineEntry.js"></script>
<!-- Added by pratik for scroll issue on zoom 31-07-23-->
<style>
/* tr#delayedMonthAndYearCombos {
    display: contents !important;
} */
</style>
<!-- End by pratik -->
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<c:set var="voucherDtlsVO" value="${resValue.lObjMstDcpsContriVoucherDtls}"/>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="VoucherForPrvsMonthExistsOrNot" value="${resValue.VoucherForPrvsMonthExistsOrNot}"/>
<c:set var="typeOfPaymentMaster" value="${resValue.typeOfPaymentMaster }"/>

<c:set var="User" value="${resValue.lStrUser}"/>
<c:set var="Use" value="${resValue.lStrUse}"/>

<c:set var="level2DD0" value="${resValue.level2DD0}"/>
<c:set var="selectedLevel2" value="${resValue.selectedLevel2}"/>
<c:choose>
	<c:when test="${typeOfPaymentMaster == '700047' && ((User == 'DDOAsst' || User == 'ATO') && (Use == 'ViewAll'))}">
			<c:set var="varComboForDelayedType" scope="page" value="style='display:contents'"></c:set> <!-- added display contents For UI issues by Pratik 01-08-23 -->
	</c:when>
	<c:otherwise>
		<c:set var="varComboForDelayedType" scope="page" value="style='display:none'"></c:set>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${User != 'TO' && (!(User == 'DDO' && (Use == 'ViewApproved' || Use == 'ViewReverted' ))) && (resValue.lLongbillGroupId != '9999999') && (!((User == 'DDOAsst' || User == 'ATO') && Use == 'ViewRejected')) && (!(User == 'DDO' && Use == 'ViewForwarded')) }">
	</c:when>
	<c:otherwise>
		<c:set var="varTypeOfPaymentMasterDivDisplay" scope="page" value="style='display:none'"></c:set>
	</c:otherwise>
</c:choose>

<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varButtonDisabled" scope="page" value="style='visibility:hidden'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varReadOnly" scope="page" value="readOnly='readOnly'"></c:set>
	<c:set var="varColumnDisabled" scope="page" value="none"></c:set>
</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>



<c:choose>
	<c:when test="${( (resValue.ContriThruChallanOrNot=='Yes' && User == 'DDOAsst')  || (User == 'TO' && voucherDtlsVO.voucherStatus != 1) )}">
	</c:when>
	<c:otherwise>
		<c:set var="varReadOnlyForVoucherChallan" scope="page" value="readOnly='readOnly'"></c:set>
		<c:set var="varDisplayCalForVoucherChallan" scope="page"  value="style='display:none'"></c:set>
	</c:otherwise>
</c:choose>

<hdiits:form name="DCPSOfflineEntryForm" id="DCPSOfflineEntryForm" encType="multipart/form-data"
	validate="true" method="post" >
	
		<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.INPUTDETAILSFORCONTRI" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
		<input type = "hidden" name = "savedFlag" id = "savedFlag" value = "${resValue.savedFlag }" />
		<input type = "hidden" name = "RLTreasuryCode" id = "RLTreasuryCode" value = "${resValue.TreasuryCode }" />
		<input type = "hidden" name = "RLUser" id = "RLUser" value = "${resValue.RLUser }" />
		<input type = "hidden" name = "RLUse" id = "RLUse" value = "${resValue.RLUse }" />
		<input type = "hidden" name = "RLelementId" id = "RLelementId" value = "${resValue.elementId }" />
		<input type = "hidden" name = "RLType" id = "RLType" value = "${resValue.RLType}" />
		<input type = "hidden" name = "RLDDOCode" id = "RLDDOCode" value = "${resValue.cmbDDOCode }" />
		<input type = "hidden" name = "RLBillGroup" id = "RLBillGroup" value = "${resValue.cmbBillGroup }" />
		<input type = "hidden" name = "RLSchemeName" id = "RLSchemeName" value = "${resValue.schemeName }" />
		<input type = "hidden" name = "RLSchemeCode" id = "RLSchemeCode" value = "${resValue.schemeCode }" />
		<input type = "hidden" name = "RLSubSchemeName" id = "RLSubSchemeName" value = "${resValue.subSchemeName }" />
		<input type = "hidden" name = "RLSubSchemeCode" id = "RLSubSchemeCode" value = "${resValue.subSchemeCode }" />
		<input type = "hidden" name = "RLMonth" id = "RLMonth" value = "${resValue.cmbMonth }" />
		<input type = "hidden" name = "RLYear" id = "RLYear" value = "${resValue.cmbYear }" />
		<input type = "hidden" name = "successFlag" id = "successFlag" value = "${resValue.successFlag }" />
		
		<input type = "hidden" name = "RLTypeOfPaymentMaster" id = "RLTypeOfPaymentMaster" value = "${resValue.typeOfPaymentMaster }"/>
		
		<input type = "hidden" name = "RLDelayedMonth" id = "RLDelayedMonth" value = "${resValue.delayedMonth }"/>
		<input type = "hidden" name = "RLDelayedYear" id = "RLDelayedYear" value = "${resValue.delayedYear }"/>
		
		
		<script>
		var savedFlag = document.getElementById("savedFlag").value.trim();
		var RLTreasuryCode = document.getElementById("RLTreasuryCode").value.trim();
		var RLUser = document.getElementById("RLUser").value.trim();
		var RLUse = document.getElementById("RLUse").value.trim();
		var RLelementId = document.getElementById("RLelementId").value.trim();
		var RLType = document.getElementById("RLType").value.trim();
		var RLDDOCode = document.getElementById("RLDDOCode").value.trim();
		var RLBillGroup = document.getElementById("RLBillGroup").value.trim();
		var RLSchemeName = document.getElementById("RLSchemeName").value.trim();
		var RLSchemeCode = document.getElementById("RLSchemeCode").value.trim();
		var RLMonth = document.getElementById("RLMonth").value.trim();
		var RLYear = document.getElementById("RLYear").value.trim();
		var successFlag = document.getElementById("successFlag").value.trim();
		var RLTypeOfPaymentMaster = document.getElementById("RLTypeOfPaymentMaster").value.trim();
		var RLDelayedMonth = document.getElementById("RLDelayedMonth").value.trim();
		var RLDelayedYear = document.getElementById("RLDelayedYear").value.trim();
		
		var url = "ifms.htm?actionFlag=loadOfflineDCPSForm&treasuryCode="+RLTreasuryCode+"&User="+RLUser+"&Use="+RLUse+"&elementId="+RLelementId+"&Type="+RLType
		+"&cmbDDOCode="+RLDDOCode+"&cmbBillGroup="+RLBillGroup
		+"&cmbMonth="+RLMonth+"&cmbYear="+RLYear+"&schemeName="+RLSchemeName+"&typeOfPaymentMaster="+RLTypeOfPaymentMaster+"&schemeCode="+RLSchemeCode;
		
		if(RLTypeOfPaymentMaster == '700047')
			{
				url = url + "&cmbDelayedMonth="+RLDelayedMonth + "&cmbDelayedYear="+RLDelayedYear;
			}
		
		if(successFlag == 'true')
			{
				if(savedFlag==1)
				{
					if(RLUse == 'ViewReverted')
					{
						alert('Records are reverted and saved Successfully');
						self.location.href="ifms.htm?actionFlag=revertAcceptedContri";
					}
					else
					{
						alert("Records Saved Successfully");
						self.location.href = url;
					}
				}
		
				if(savedFlag==2)
				{
					alert("Records have been forwarded");
					self.location.href = url;
				}
			}


//added by roshan
		function generateLevel1DDo(){
		
		var level2DDO = document.getElementById("cmbLevelDDOCode").value.trim();
		
		var cmbTreasuryCode=document.getElementById("cmbTreasuryCode").value.trim();
		
		var url;

		url="./hrms.htm?actionFlag=loadOfflineDCPSForm&User=TO&Use=ViewForwarded&level2DDO="+level2DDO+"&treasuryCode="+cmbTreasuryCode;
		document.DCPSOfflineEntryForm.action= url;
		document.DCPSOfflineEntryForm.submit();
		showProgressbar("Please wait<br>While Level 1 ddo list are getting populated ...");
			}
		</script>
		<input type="hidden" name = "hidElementId" id="hidElementId" value="${resValue.hidElementId}"/> 
		
		<c:if test="${resValue.lStrUser == 'DDOAsst'}">
			<table align="center" width="100%">
			<tr>
				<td style="font-style:italic;color: red ;padding-left: 1%" colspan="5" >
				<fmt:message key="CMN.NOTEFORNEWDCPSSYSTEM" bundle="${dcpsLables}"></fmt:message></td>
			</tr>	
			<tr>
				<td style="font-style:italic;color: red ;padding-left: 1%" colspan="5" ><fmt:message key="CMN.NOTEFORSAVECAREFULLY" bundle="${dcpsLables}"></fmt:message></td>
			</tr>	
			</table>
		</c:if>
		
		<table align="center" width="100%" >
					<tr>
								<td width="15%" style="padding-left:5%;" ><fmt:message
										key="CMN.TreasuryName" bundle="${dcpsLables}"></fmt:message>
								</td>
						<c:choose>
						<c:when test="${resValue.TypeOfContribution == 'Offline'}">
								<td width="35%">
								    <select name="cmbTreasuryCode" id="cmbTreasuryCode" style="width:95%;"  onChange="" >
									
																					<option value="${resValue.treasuryCode}" selected="selected" title="${resValue.treasuryName}"><c:out 
																							value="${resValue.treasuryName}"></c:out></option>
																
									</select>
								  	<label class="mandatoryindicator">*</label>
								</td>
						
						</c:when>
						<c:otherwise>
								<td width="35%">
								   		<select name="cmbTreasuryCode" id="cmbTreasuryCode" style="width:95%;"  onChange="" disabled="disabled" >
											<c:forEach var="Treasuries" items="${resValue.TREASURIES}" >
																					<option value="${Treasuries.id}" title="${Treasuries.desc}" ><c:out 
																							value="${Treasuries.desc}"></c:out></option>
																
											</c:forEach>
									    </select>
								  		<label class="mandatoryindicator">*</label>
								</td>
						</c:otherwise>
						</c:choose>
						
						
						
						
						</tr>
						<tr>
						
				 
						
						
						
						
						
						
						
						
						
								<td width="15%" style="padding-left: 5%" ><fmt:message
										key="CMN.DDOName" bundle="${dcpsLables}"></fmt:message>
								</td>
										
						<c:choose>
						<c:when test="${resValue.TypeOfContribution == 'Offline'}">
								<td width="35%">
										<select name="cmbDDOCode" id="cmbDDOCode" style="width:95%;"  onChange="" >
																					<option value="${resValue.DDoCode}" selected="selected" title="${resValue.DdoName}"><c:out 
																							value="${resValue.DdoName}"></c:out></option>
									    </select>
										<label class="mandatoryindicator">*</label>
								</td>	
						</c:when>
						<c:otherwise>
								<td width="35%">
									<c:choose>
										<c:when test="${resValue.lStrUser == 'DDOAsst' || resValue.lStrUser == 'DDO'}">
											<select name="cmbDDOCode" id="cmbDDOCode" style="width:95%;"  onChange="" disabled="disabled" >
											<c:forEach var="DDOName" items="${resValue.DDONAMES}" >
												<option value="${DDOName.id}" title="${DDOName.desc}"><c:out 
																							value="${DDOName.desc}"></c:out></option>
											</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select name="cmbDDOCode" id="cmbDDOCode" style="width:95%;"  onChange="" >
											<c:forEach var="DDOName" items="${resValue.DDONAMES}" >
															<c:choose>
																<c:when test="${resValue.lStrDDOCode == DDOName.id}">
																					<option value="${DDOName.id}" selected="selected" title="${DDOName.desc}"><c:out 
																							value="(${DDOName.id})  ${DDOName.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${DDOName.id}" title="${DDOName.desc}"><c:out 
																							value="(${DDOName.id}) ${DDOName.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
											</select>
										</c:otherwise>
									</c:choose>
									<label class="mandatoryindicator">*</label>
								</td>	
						</c:otherwise>
						</c:choose>		
					</tr>
					
					<tr>
								<td width="15%" style="padding-left: 5%" ><fmt:message
										key="CMN.BillGroup" bundle="${dcpsLables}"></fmt:message>
								</td>
								<c:choose>
									<c:when test="${resValue.TypeOfContribution == 'Offline'}">
											<td width="35%">
													<select name="cmbBillGroup" id="cmbBillGroup" style="width:95%;"  onChange="getSchemeforBillGroup();">
																								<option value="${resValue.BillGroupId}" selected="selected" title="${resValue.BillGroup}"><c:out 
																										value="${resValue.BillGroup}"></c:out></option>
												    </select>
												    <label class="mandatoryindicator">*</label>
											</td>
									</c:when>
									<c:otherwise>
											<td width="35%">
													<select name="cmbBillGroup" id="cmbBillGroup" style="width:95%;"  onChange="getSchemeforBillGroup();">
													
														<c:choose>
														<c:when test="${resValue.lStrDDOCode != null}">
															
															<c:forEach var="BillGroupList" items="${resValue.BillGroupList}" >
																		<c:choose>
																			<c:when test="${resValue.lLongbillGroupId == BillGroupList.id}">
																								<option value="${BillGroupList.id}" selected="selected" title="${BillGroupList.desc}"><c:out 
																										value="${BillGroupList.desc}"></c:out></option>
																			</c:when>
																			<c:otherwise>
																								<option value="${BillGroupList.id}" title="${BillGroupList.desc}"><c:out 
																										value="${BillGroupList.desc}"></c:out></option>
																			</c:otherwise>						
																	    </c:choose>
														</c:forEach>
														</c:when>
														<c:otherwise>
																<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
														</c:otherwise>
														</c:choose>
														
												    </select>
												    <label class="mandatoryindicator">*</label>
											</td>
									</c:otherwise>
									
								</c:choose>	
								
								
										<td width="15%" style="padding-left: 5%" ><fmt:message
												key="CMN.Scheme" bundle="${dcpsLables}"></fmt:message>
										</td>
								<c:choose>	
									<c:when test="${resValue.TypeOfContribution == 'Offline'}">
										<td width="35%" ><input type="text" name="txtSchemeName"  id="txtSchemeName" value="${resValue.schemename}" style="width: 93%" readonly="readonly" />
										
										<label class="mandatoryindicator">*</label>
										<input type="hidden" name="schemeCode"  id="schemeCode" value="${resValue.SchemeCode}"  />
										</td>
									</c:when>
									<c:otherwise>
										<td width="35%" ><input type="text" name="txtSchemeName"  id="txtSchemeName" value="${resValue.schemename}"  style="width: 93%" readonly="readonly" />
										
										<label class="mandatoryindicator">*</label>
										<input type="hidden" name="schemeCode"  id="schemeCode" value="${resValue.schemeCode}"  />
										</td>
									</c:otherwise>
								</c:choose>	
				   </tr>
					
					  <tr>
				   <td width="15%" style="padding-left: 5%" ><fmt:message
												key="CMN.SubScheme" bundle="${dcpsLables}"></fmt:message>
										</td>
								<c:choose>	
									<c:when test="${resValue.TypeOfContribution == 'Offline'}">
										<td width="35%" ><input type="text" name="txtSubSchemeName"  id="txtSubSchemeName" value="${resValue.subSchemename}" style="width: 93%" readonly="readonly" />
										
										<label class="mandatoryindicator">*</label>
										<input type="hidden" name="subSchemeCode"  id="subSchemeCode" value="${resValue.subSchemeCode}"  />
										</td>
									</c:when>
									<c:otherwise>
										<td width="35%" ><input type="text" name="txtSubSchemeName"  id="txtSubSchemeName" value="${resValue.subSchemename}"  style="width: 93%" readonly="readonly" />
										
										<label class="mandatoryindicator">*</label>
										<input type="hidden" name="subSchemeCode"  id="subSchemeCode" value="${resValue.subSchemeCode}"  />
										</td>
									</c:otherwise>
								</c:choose>	
				   </tr>
					
					
				   <tr>
								<td width="15%" style="padding-left: 5%" ><fmt:message
										key="CMN.Month" bundle="${dcpsLables}"></fmt:message>
								</td>
								
								<c:choose>	
									<c:when test="${resValue.TypeOfContribution == 'Offline'}">
										<td width="35%">
												<select name="cmbMonth" id="cmbMonth" style="width:30%;"  onChange="selectCorrectFinYear();" >
																							<option value="${resValue.monthId}" selected="selected"><c:out 
																									value="${resValue.Month}"></c:out></option>
											    </select>
											    <label class="mandatoryindicator">*</label>
										</td>	
									</c:when>
									<c:otherwise>
										<td width="35%">
												<select name="cmbMonth" id="cmbMonth" style="width:30%;"  onChange="selectCorrectFinYear();" >
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
									</c:otherwise>
								</c:choose>
								<td width="15%" style="padding-left: 5%" ><fmt:message
										key="CMN.Year" bundle="${dcpsLables}"></fmt:message>
								</td>
							
								<c:choose>	
									<c:when test="${resValue.TypeOfContribution == 'Offline'}">
										<td width="35%" >
												<select name="cmbYear" id="cmbYear" style="width:30%;"  onChange="" >
																							<option value="${resValue.yearId}" selected="selected"><c:out 
																									value="${resValue.Year}"></c:out></option>
											    </select>
											    <label class="mandatoryindicator">*</label>
										</td>
									</c:when>
									<c:otherwise>
										<td width="35%" >
												<select name="cmbYear" id="cmbYear" style="width:30%;"  onChange="" >
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
									</c:otherwise>
								</c:choose>
					</tr>
				
					
		</table>
					
		<div id="paymentMasterDivDisplay" ${varTypeOfPaymentMasterDivDisplay}>
				<table align="center" width="100%" >
				
					<tr id="typeOfPaymentMasterRow"  >
						<td width="15%" style="padding-left: 5%" ><fmt:message
										key="CMN.PAYMENTTYPE" bundle="${dcpsLables}"></fmt:message>
						</td>
						<td width="35%" >
					  				<select name="cmbTypeOfPaymentMaster" id="cmbTypeOfPaymentMaster" style="width: 40%;"  onchange="addMonthYearComboForDelayed();">
											<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
											      <c:forEach var="typeOfPaymentMaster" items="${resValue.listTypeOfPayment}">
											      			<c:choose>
											      				<c:when test="${typeOfPaymentMaster.lookupId == resValue.typeOfPaymentMaster}">
																	<option value="${typeOfPaymentMaster.lookupId}" selected="selected"><c:out value="${typeOfPaymentMaster.lookupDesc}"></c:out></option>
																</c:when>
																<c:otherwise>
																	<option value="${typeOfPaymentMaster.lookupId}"><c:out value="${typeOfPaymentMaster.lookupDesc}"></c:out></option>
																</c:otherwise>
															</c:choose>
											      </c:forEach>
									</select>
						</td>
						<td width="15%"></td>
						<td width="35%"></td>
					</tr>
					
					<tr ${varComboForDelayedType} id="delayedMonthAndYearCombos">
							<td width="15%" style="padding-left: 5%" ><fmt:message
									key="CMN.DelayedMonth" bundle="${dcpsLables}"></fmt:message>
							</td>
							<td width="35%"  >
									<select name="cmbDelayedMonth" id="cmbDelayedMonth"   onChange="" >
									<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
										<c:forEach var="month" items="${resValue.MONTHS}" >
														<c:choose>
															<c:when test="${resValue.delayedmonthId == month.id}">
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
							
							<td  width="15%" style="padding-left: 5%" >
							<fmt:message
									key="CMN.DelayedYear" bundle="${dcpsLables}"></fmt:message>
							</td>	
							<td width="35%"  >
											<select name="cmbDelayedYear" id="cmbDelayedYear"  onChange="" >
											<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
												<c:forEach var="year" items="${resValue.DELAYEDYEARS}" >
																<c:choose>
																					<c:when test="${resValue.delayedyearId == year.id}">
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
				</table>
		</div>
	   <br/>
	   
	   <table align="center">
					<tr>
								<td id="go" align="center" style="padding-left: 50px" >
								
								<c:if test="${resValue.lStrUse != 'ViewReverted'}">
									<hdiits:button
																name="btnGo" id="btnGo" type="button"
																captionid="BTN.GO" bundle="${dcpsLables}"
																onclick="getEmpListAfterValidation();"  />
									<hdiits:button name="btnBackWithGo" id="btnBackWithGo" type="button"  captionid="BTN.BACK"
										   bundle="${dcpsLables}" onclick="ReturnLoginPage();" />
								</c:if>  	
														
								<input type="hidden" id="User" name = "User" value="${resValue.lStrUser}"/>
								<input type="hidden" id="Use" name = "Use" value="${resValue.lStrUse}"/>
								<c:if test="${resValue.ContriType!=null}">
									<input type="hidden" name = "Type" id="Type" value="${resValue.ContriType}"/>
								</c:if>
								<c:if test="${resValue.ContriType==null}">
									<input type="hidden" id="Type" value=""/>
								</c:if>
								</td>	
					</tr>
					<tr><td style="color: red;font-family: bold; font-size: large;font-weight:bold;">NOTE : If you don't need NPS employer contribution in paybill then make this value as '0' in DCPS Delayed, DCPS DA Arrear and DCPS PAY Arrear.</td></tr>
	    </table>
	    
	    </br>	
	   
	   <c:choose>
			<c:when test="${resValue.lStrUser == 'DDOAsst' && resValue.typeOfPaymentMaster == '700048'}">
				<c:set var="varDivForDAArrearType" scope="page" value="style='display:inline'"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="varDivForDAArrearType" scope="page" value="style='display:none'"></c:set>
			</c:otherwise>
	   </c:choose>
	   
			<div id="DAArrearsDatesDivDisplay" ${varDivForDAArrearType}>
				<table align="center" width="88%">
					<tr>
						<td width="20%" style="padding-left: 1%" ><fmt:message
									key="CMN.DAARREARSTARTDATE" bundle="${dcpsLables}"></fmt:message>
						</td>
						<td width="20%" style="padding-left: 1%" >
								<input type="text" style="width: 50%" name="txtDAArrearStartDate" id="txtDAArrearStartDate" value="" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtDAArrearStartDate",375,570)'
											style="cursor: pointer;" ${varImageDisabled}/>
						</td>
						<td width="20%" style="padding-left: 1%" ><fmt:message
									key="CMN.DAARREARENDDATE" bundle="${dcpsLables}"></fmt:message>
						</td>
						<td width="20%" style="padding-left: 1%" >
							<input type="text" style="width: 50%" name="txtDAArrearEndDate" id="txtDAArrearEndDate"  value="" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtDAArrearEndDate",375,570)'
											style="cursor: pointer;" ${varImageDisabled}/>
						</td>
						<td width="15%" style="padding-left: 1%" >
							<hdiits:button	 name="btnDAArrearSetDates" type="button" id="btnDAArrearSetDates" classcss="bigbutton" captionid="BTN.SETDAARREARDATESONLY"
								   bundle="${dcpsLables}" onclick="setDAArrearDates();" />
						</td>
					</tr>
					<tr>
						<td style="font-style:italic;color: red ;padding-left: 1%" colspan="5" ><fmt:message key="CMN.NOTEFORDAARREARDATES" bundle="${dcpsLables}"></fmt:message></td>
					</tr>			
				</table>
			</div>
			
			
		<c:choose>
			<c:when test="${resValue.lStrUser == 'DDOAsst' && resValue.typeOfPaymentMaster == '700049'}">
				<c:set var="varDivForPayArrearType" scope="page" value="style='display:inline'"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="varDivForPayArrearType" scope="page" value="style='display:none'"></c:set>
			</c:otherwise>
	   </c:choose>
	   
			<div id="PayArrearsDatesDivDisplay" ${varDivForPayArrearType}>
				<table align="center" width="88%">
					<tr>
						<td width="20%" style="padding-left: 1%" ><fmt:message
									key="CMN.PAYARREARSTARTDATE" bundle="${dcpsLables}"></fmt:message>
						</td>
						<td width="20%" style="padding-left: 1%" >
								<input type="text" style="width: 50%" name="txtPayArrearStartDate" id="txtPayArrearStartDate" value="" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtPayArrearStartDate",375,570)'
											style="cursor: pointer;" ${varImageDisabled}/>
						</td>
						<td width="20%" style="padding-left: 1%" ><fmt:message
									key="CMN.PAYARREARENDDATE" bundle="${dcpsLables}"></fmt:message>
						</td>
						<td width="20%" style="padding-left: 1%" >
							<input type="text" style="width: 50%" name="txtPayArrearEndDate" id="txtPayArrearEndDate"  value="" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtPayArrearEndDate",375,570)'
											style="cursor: pointer;" ${varImageDisabled}/>
						</td>
						<td width="15%" style="padding-left: 1%" >
							<hdiits:button	 name="btnPayArrearSetDates" type="button" id="btnPayArrearSetDates" classcss="bigbutton" captionid="BTN.SETPAYARREARDATESONLY"
								   bundle="${dcpsLables}" onclick="setPayArrearDates();" />
						</td>
					</tr>
					<tr>
						<td style="font-style:italic;color: red ;padding-left: 1%" colspan="5" ><fmt:message key="CMN.NOTEFORPAYARREARDATES" bundle="${dcpsLables}"></fmt:message></td>
					</tr>			
				</table>
			</div>	
	   
	   <input type="hidden" id="hidBGIdForContriThruChallan" name="hidBGIdForContriThruChallan" value="${resValue.hidBGIdForContriThruChallan}" />
	   <input type="hidden" id="hidContriThruChallanOrNot" name="hidContriThruChallanOrNot" value="${resValue.ContriThruChallanOrNot}" />
	   
	  	
		</fieldset>
		
		<br/>
		<br/>
		
		<c:choose>
		
			<c:when test="${(resValue.lStrUser == 'DDOAsst' ||  resValue.lStrUser == 'ATO')  && resValue.contributionsForPrvsMonth == 'YES' && resValue.voucherDtlsForPrvsMonth == 'NO' && Use != 'ViewRejected' && Use != 'ViewReverted' && Use != 'ViewApproved' }">
			
				<input type="hidden" id="hidTreasuryCode" value="${resValue.treasuryCode}" />
				<input type="hidden" id="hidBillGroup" value="${resValue.lLongbillGroupId}" />
				<input type="hidden" id="hidSchemeName" value="${resValue.schemename}" />
				<input type="hidden" id="hidSchemeCode" value="${resValue.schemeCode}" />
				<input type="hidden" id="hidPrvsMonthId" value="${resValue.previousMonthId}" />
				<input type="hidden" id="hidPrvsYearId" value="${resValue.previousYearId}" />
				<input type="hidden" id="hidDdoCode" value="${resValue.lStrDDOCode}" />
				<input type="hidden" id="hidType" value="${resValue.ContriType}" />
				<input type="hidden" id="hidUser" value="${resValue.lStrUser}" />
				<input type="hidden" id="hidUse" value="${resValue.lStrUse}" />
				
				<script>
				
					alert("Previous month's voucher is not entered. Please enter previous month's voucher details now.");
					
					var hidTreasuryCode = document.getElementById("hidTreasuryCode").value;
					var hidBillGroup = document.getElementById("hidBillGroup").value;
					var hidSchemeName =  document.getElementById("hidSchemeName").value;
					var hidSchemeCode =  document.getElementById("hidSchemeCode").value;
					var hidPrvsMonthId =  document.getElementById("hidPrvsMonthId").value;
					var hidPrvsYearId  = document.getElementById("hidPrvsYearId").value;
					var hidDdoCode =  document.getElementById("hidDdoCode").value;
					var hidType = document.getElementById("hidType").value;
					var hidUser = document.getElementById("hidUser").value;
					var hidUse = document.getElementById("hidUse").value;
					
					self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+hidDdoCode+"&cmbBillGroup="+hidBillGroup+"&Type="+hidType
					+"&cmbMonth="+hidPrvsMonthId+"&cmbYear="+hidPrvsYearId+"&schemeName="+hidSchemeName+"&treasuryCode="+hidTreasuryCode+"&schemeCode="+hidSchemeCode+"&User="+hidUser+"&Use="+hidUse ;
					
				</script>
			</c:when>
			
			<c:otherwise>
		
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
				
				<c:choose>
					<c:when test="${empList!=null || resValue.ContriThruChallanOrNot == 'Yes'}" >
						<c:set var="showList" value="Yes" ></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="showList" value="No" ></c:set>
					</c:otherwise>
				</c:choose>
				
				<input type="hidden" name="hdnGoPressed" id="hdnGoPressed" value="${resValue.GoPressed}"/>
				
				<c:choose>
			
					<c:when test="${showList == 'No'}">
						<c:if test="${resValue.GoPressed == 'Y'}">
							<br>
								<c:choose>
									<c:when test="${voucherDtlsVO.voucherStatus == 5}">
										<c:out value="Contributions are with DDO Approver."></c:out>
									</c:when>
									<c:when test="${voucherDtlsVO.voucherStatus == 3}">
										<c:if test="${resValue.lStrUser == 'DDOAsst' || resValue.lStrUser == 'DDO'}">
											<c:out value="Contributions are with Treasury."></c:out>
										</c:if>
									</c:when>
									<c:when test="${voucherDtlsVO.voucherStatus == 1}">
										<c:out value="Contributions are approved by Treasury."></c:out>
									</c:when>
									<c:when test="${voucherDtlsVO.voucherStatus == -3}">
										<c:out value="Contributions are rejected and are with DDO Assistant."></c:out>
									</c:when>
									<c:otherwise>
										<c:out value="No Records Found"></c:out>
									</c:otherwise>
								</c:choose>
						</c:if>
					</c:when>
					<c:otherwise>
					<fieldset class="tabstyle"><legend> <b><fmt:message
						key="CMN.OUTPUTDETAILSFORCONTRI" bundle="${dcpsLables}"></fmt:message></b> </legend>
						<c:choose>
							<c:when test="${resValue.ContriThruChallanOrNot == 'Yes' && empList==null && (resValue.lStrUser == 'DDOAsst' || resValue.lStrUser == 'ATO')}">
								<c:set var="counterforContriThruChallan" value="1" ></c:set>
								<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
								<table border="2" bordercolor="orange" id="vo" width="100%" >
									<tr>
										<td style="text-align:center;width: 15%" >
											<b><u>Name</u></b>
										</td>
										<td style="text-align:center;width: 16%" >
											<b><u>DCPS ID</u></b>
										</td>
										<td style="text-align:center;width: 14%">
											<b><u>Contribution Start Date</u></b>
										</td>
										<td style="text-align:center;width: 14%" >
											<b><u>Contribution End Date</u></b>
										</td>
										<td style="text-align:center;width: 9%"  >
											<b><u>Pay Commission</u></b>
										</td>
										<td style="text-align:center;width: 11%" >
											<b><u>Payment Type</u></b>
										</td>
										<td style="text-align:center;width: 8%">
											<b><u>Basic</u></b>
										</td>
										<td style="text-align:center;width: 7%">
											<b><u>DP</u></b>
										</td>
										<td style="text-align:center;width: 7%">
											<b><u>DA</u></b>
										</td>
										<td style="text-align:center;width: 7%">
											<b><u>Contribution</u></b>
										</td>
										<td style="text-align:center;width: 5%">
											<b><u>Add Row</u></b>
										</td>
										<td style="text-align:center;width: 5%">
											<b><u>Delete Row</u></b>
										</td>
										<td style="display: none" >
										</td>
									</tr>
									<tr>
										<td>
											<input type="text" name="name" id="name${counterforContriThruChallan}" style="width:95%" readOnly="readOnly" />
										</td>
										<td>
											<input type="text" style="width:95%" name="dcpsId${counterforContriThruChallan}" id="dcpsId${counterforContriThruChallan}" value=""  onblur="getDtlsForContriThruChallan('${counterforContriThruChallan}')" />
										</td>
										<td>
											<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.FirstDate}" var="empStartDate"/>
											<input type="text" style="width: 75%" name="txtStartDate${counterforContriThruChallan}" id="txtStartDate${counterforContriThruChallan}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);compareDates('${counterforContriThruChallan}');" value="${empStartDate}" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtStartDate${counterforContriThruChallan}",375,570)'
											style="cursor: pointer;" ${varImageDisabled}/>
										</td>
										<td> 
											<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.LastDate}" var="empEndDate"/>
											<input type="text" style="width: 75%"  name="txtEndDate${counterforContriThruChallan}" id="txtEndDate${counterforContriThruChallan}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);compareDates('${counterforContriThruChallan}');changeContriForSelectedPrd('${counterforContriThruChallan}');" value="${empEndDate}"/>
											 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtEndDate${counterforContriThruChallan}",375,570)'
											style="cursor: pointer;" ${varImageDisabled}/>
											<input type="hidden" id="contriDoneForThisPrd${counterforContriThruChallan}" name="contriDoneForThisPrd${counterforContriThruChallan}" value="NO"/>
										</td>
										<td> 
											<select name="cmbPayCommission${counterforContriThruChallan}" id="cmbPayCommission${counterforContriThruChallan}" onchange="changeContriForSelectedPrd('${counterforContriThruChallan}');" style="width: 95%"  >
														<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
														      <c:forEach var="PayCommission" items="${resValue.listPayCommission}">
																				<option value="${PayCommission.lookupId}" ><c:out value="${PayCommission.lookupDesc}" ></c:out></option>
														      </c:forEach>
											</select>
										</td>
										<td>
											<select name="cmbTypeOfPayment${counterforContriThruChallan}" id="cmbTypeOfPayment${counterforContriThruChallan}" style="width: 95%" onchange="changeEditabilityOfFields('${counterforContriThruChallan}');compareDates('${counterforContriThruChallan}');">
															<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
															      <c:forEach var="typeOfPayment" items="${resValue.listTypeOfPayment}">
																				<option value="${typeOfPayment.lookupId}"><c:out value="${typeOfPayment.lookupDesc}"></c:out></option>
															      </c:forEach>
											</select>
										</td>
										<td>
											 <input type="text" id="basic${counterforContriThruChallan}" name="basic${counterforContriThruChallan}" value=""  onchange="changeDpDaAndContri('${counterforContriThruChallan}');" style="width: 95%"  />
										</td>
										<td>
											 <input type="text" id="DP${counterforContriThruChallan}" name="DP${counterforContriThruChallan}" value="" onchange="changeAndContri('${counterforContriThruChallan}');changeDAForSelectedPrd('${counterforContriThruChallan}');" style="width: 95%" />
										</td>
										<td>
											 <input type="text" id="DA${counterforContriThruChallan}" name="DA${counterforContriThruChallan}" value="" onchange="changeAndContri('${counterforContriThruChallan}');" style="width: 95%" />
										</td>
										<td>
											 <input type="text" id="contribution${counterforContriThruChallan}" name="contribution${counterforContriThruChallan}" value="" style="width: 95%" />
										</td>
										<td>
											<c:if test="${resValue.EditForm != null && resValue.EditForm == 'Y'}">    
								  			 	<a href=# onclick="AddNewRowContriForContriThruChallan('${counterforContriThruChallan}');"> <label id="AddNewRowContri">Add</label></a>
								  			</c:if>
										</td>
										<td>
											<c:if test="${resValue.EditForm != null && resValue.EditForm == 'Y'}">    
								  			 	<a href=# onclick="DeleteRowContri('${counterforContriThruChallan}');"> <label id="DeleteRowContri">Delete</label></a>
								  			</c:if>
										</td>
										<td style="display: none" >
											<input type="hidden" name="dcpsempId${counterforContriThruChallan}" id="dcpsempId${counterforContriThruChallan}" value=""/>
											<input type="hidden" name="hidBasic${counterforContriThruChallan}" id="hidBasic${counterforContriThruChallan}" value="0"/>
											<input type="hidden" name="hidDP${counterforContriThruChallan}" id="hidDP${counterforContriThruChallan}" value="0"/>
											<input type="hidden" name="hidDA${counterforContriThruChallan}" id="hidDA${counterforContriThruChallan}" value="0"/>
											<input type="hidden" name="hidContribution${counterforContriThruChallan}" id="hidContribution${counterforContriThruChallan}" value="0"/>
											<input type="checkbox" id="checkbox${counterforContriThruChallan}" name="checkbox${counterforContriThruChallan}" value="0"  checked="checked" style="display: none"/>
											<input type="hidden" name="daRate${counterforContriThruChallan}" id="daRate${counterforContriThruChallan}"  value=""/>
											<input type="hidden" id="previousStartDate" value="${resValue.DelFirstDate}" />
											<input type="hidden" id="previousEndDate" value="${resValue.DelLastDate}" /> 
											<input type="hidden" name="hidEmpStartDate${counterforContriThruChallan}" id="hidEmpStartDate${counterforContriThruChallan}" value="${empStartDate}"/>
											<input type="hidden" name="hidEmpEndDate${counterforContriThruChallan}" id="hidEmpEndDate${counterforContriThruChallan}" value="${empEndDate}"/>
											
										 	<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[11]}" var="joiningDate"/>
											<input type="hidden" name="hidJoiningDate${counterforContriThruChallan}" id="hidJoiningDate${counterforContriThruChallan}" value="${joiningDate}"/>
											
											<script>
														document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
										 	</script>
										 	<c:set var="counterforContriThruChallan" value="${counterforContriThruChallan+1}" ></c:set>
										</td>
									</tr>
								</table>
								
							</c:when>
							
							<c:otherwise>
						
						<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
						
						<div style="width:100%;overflow:auto;" >
						<c:set var="varHideBtns" scope="page" value="1"/>
						 
						<display:table list="${empList}" id="vo" requestURI="" pagesize="500"  export="false" style="width:100%" partialList="true" 
								 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="1" defaultorder="ascending" cellpadding="5" >	
						
		
						<display:setProperty name="paging.banner.placement" value="bottom" />		
						
					
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:12%"   sortable="true" titleKey="CMN.NAMEWOCOLON" >
										<b><label id="name${vo_rowNum}">${vo[2]}</label></b>
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:11%" sortable="true" titleKey="CMN.DCPSID" >
										<label id="dcpsId${vo_rowNum}">${vo[1]}</label>
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:14%"  sortable="true"  titleKey="CMN.CONTRISTARTDATE"  >
								
									<c:choose>
										<c:when test="${vo[15] == '' || vo[15] == null}">
											<c:choose>
												<c:when test="${(resValue.lStrUser == 'DDOAsst' ||  resValue.lStrUser == 'ATO') && (resValue.typeOfPaymentMaster == '700048' || resValue.typeOfPaymentMaster == '700049') }">
													<c:set var="empStartDate" value=""/> 
												</c:when>
												<c:when test="${(resValue.lStrUser == 'DDOAsst' ||  resValue.lStrUser == 'ATO') && (resValue.typeOfPaymentMaster == '700047') }">
													<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.FirstDateDelayed}" var="empStartDate"/>
												</c:when>
												<c:otherwise>
													<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.FirstDate}" var="empStartDate"/>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[15]}" var="empStartDate"/>
										</c:otherwise>
									</c:choose>
									
										<input type="text" style="width: 70%;"  name="txtStartDate${vo_rowNum}"   id="txtStartDate${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
										 onBlur="validateDate(this);compareDates('${vo_rowNum}');changeDAForSelectedPrd('${vo_rowNum}');changeContriForSelectedPrd('${vo_rowNum}');" value="${empStartDate}"  ${varReadOnly} /> 
										<img src='images/CalendarImages/ico-calendar.gif' 
										onClick='window_open("txtStartDate${vo_rowNum}",375,570)'
										style="cursor: pointer;" ${varImageDisabled}/>
										
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:14%" sortable="true"  titleKey="CMN.CONTRIENDDATE" >
								
									<c:choose>
										<c:when test="${vo[16] == '' || vo[16] == null}">
											<c:choose>
												<c:when test="${(resValue.lStrUser == 'DDOAsst' ||  resValue.lStrUser == 'ATO') && (resValue.typeOfPaymentMaster == '700048' || resValue.typeOfPaymentMaster == '700049') }">
													<c:set var="empEndDate" value=""/>
												</c:when>
												<c:when test="${(resValue.lStrUser == 'DDOAsst' ||  resValue.lStrUser == 'ATO') && (resValue.typeOfPaymentMaster == '700047') }">
													<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.LastDateDelayed}" var="empEndDate"/>
												</c:when>
												<c:otherwise>
													<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.LastDate}" var="empEndDate"/>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[16]}" var="empEndDate"/>
										</c:otherwise>
									</c:choose>
									
										<input type="text" style="width: 70%;"  name="txtEndDate${vo_rowNum}" id="txtEndDate${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
										onBlur="validateDate(this);compareDates('${vo_rowNum}');changeContriForSelectedPrd('${vo_rowNum}');" value="${empEndDate}" ${varReadOnly}/>
 										<img src='images/CalendarImages/ico-calendar.gif' 
										onClick='window_open("txtEndDate${vo_rowNum}",375,570)'
										style="cursor: pointer;" ${varImageDisabled}/>  
										
										<input type="hidden" id="contriDoneForThisPrd${vo_rowNum}" name="contriDoneForThisPrd${vo_rowNum}" value="NO"/>
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:10%"  sortable="true"  titleKey="CMN.PAYCOMIMISSION" >   
										   			<select name="cmbPayCommission${vo_rowNum}" id="cmbPayCommission${vo_rowNum}" style="width: 95%;"  ${varDisabled} onchange="changeDPEditability('${vo_rowNum}');changeDAForSelectedPrd('${vo_rowNum}');changeContriForSelectedPrd('${vo_rowNum}');"  >
														<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
														      <c:forEach var="PayCommission" items="${resValue.listPayCommission}">
								           		     						<c:choose>
																				<c:when test="${vo[3] == PayCommission.lookupId}">
																				<option value="${PayCommission.lookupId}" selected="selected" title="${PayCommission.lookupDesc}"  ><c:out value="${PayCommission.lookupDesc}" ></c:out></option>
																				</c:when>
																				<c:otherwise>
																				<option value="${PayCommission.lookupId}" title="${PayCommission.lookupDesc}" ><c:out value="${PayCommission.lookupDesc}" ></c:out></option>
																				</c:otherwise>
																			</c:choose>
														      </c:forEach>
												     </select>
								</display:column>		
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:13%"  sortable="true"  titleKey="CMN.PAYMENTTYPE" >  
										  
													  <select name="cmbTypeOfPayment${vo_rowNum}" id="cmbTypeOfPayment${vo_rowNum}" style="width: 95%;"  ${varDisabled} onchange="changeEditabilityOfFields('${vo_rowNum}');compareDates('${vo_rowNum}');">
															<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
															      <c:forEach var="typeOfPayment" items="${resValue.listTypeOfPayment}">
															      			<c:choose>
															      				<c:when test="${vo[6]==typeOfPayment.lookupId}">
																					<option value="${typeOfPayment.lookupId}" selected="selected"><c:out value="${typeOfPayment.lookupDesc}"></c:out></option>
																				</c:when>
																				<c:otherwise>
																					<option value="${typeOfPayment.lookupId}"><c:out value="${typeOfPayment.lookupDesc}"></c:out></option>
																				</c:otherwise>
																			</c:choose>
															      </c:forEach>
													  </select>
								</display:column>		 
								
										  
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:10%"  sortable="true"  titleKey="CMN.BASICWOCOLON" >
								 
										      		 <c:if test = "${vo[6] == '700046' || vo[6] == '700047'}">
										      		 		<c:choose>
										      		 			<c:when test="${vo[4] == null || vo[4] == ''  }">
										      		 				<input type="text"  id="basic${vo_rowNum}" name="basic${vo_rowNum}" value="0"   onchange="changeDpDaAndContri('${vo_rowNum}');" style="width: 95%;"   />
										      		 			</c:when> 
										      		 			<c:otherwise>
										      		 				<input type="text"  id="basic${vo_rowNum}" name="basic${vo_rowNum}" value="${vo[4]}"   onchange="changeDpDaAndContri('${vo_rowNum}');" style="width: 95%;"   />
										      		 			</c:otherwise>
										      		 		</c:choose>
										      		  </c:if>
										      		  
										      		    <c:if test = "${vo[6] == '700048'}"> 
										      		 	     <c:choose>
										      		 			<c:when test="${vo[4] == null || vo[4] == ''  }">
										      		 				<input type="text"  id="basic${vo_rowNum}" name="basic${vo_rowNum}" value="0"  ${readonly} onchange="changeDpDaAndContri('${vo_rowNum}');" style="width: 95%;"   />
										      		 			</c:when>
										      		 			<c:otherwise>
										      		 				<input type="text"  id="basic${vo_rowNum}" name="basic${vo_rowNum}" value="${vo[4]}"  ${readonly}  onchange="changeDpDaAndContri('${vo_rowNum}');" style="width: 95%;"   />
										      		 			</c:otherwise>
										      		 		</c:choose>
										      		  </c:if>
										      		  
										      		  <c:if test = "${vo[6] == '700049'}">
										      		 	     <c:choose>
										      		 			<c:when test="${vo[4] == null || vo[4] == ''  }">
										      		 				<input type="text"  id="basic${vo_rowNum}" name="basic${vo_rowNum}" value="0"    onchange="changeDpDaAndContri('${vo_rowNum}');" style="width: 95%;"   />
										      		 			</c:when>
										      		 			<c:otherwise>
										      		 				<input type="text"  id="basic${vo_rowNum}" name="basic${vo_rowNum}" value="${vo[4]}"  onchange="changeDpDaAndContri('${vo_rowNum}');" style="width: 95%;"   />
										      		 			</c:otherwise>
										      		 		</c:choose>
										      		  </c:if>
										      		
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:8%"  sortable="true"  titleKey="CMN.DPWOCOLON" >  
										  		 
										  			  <c:if test = "${vo[6] == '700046' || vo[6] == '700047' || vo[6] == '700049'}">
										      		 	      		  <input type="text" style="width: 95%;" id="DP${vo_rowNum}" name="DP${vo_rowNum}" value="${vo[17]}"  onchange="changeAndContri('${vo_rowNum}');changeDAForSelectedPrd('${vo_rowNum}');"  style="width: 95%;"/>
										      		  </c:if>
										      		  <c:if test = "${vo[6] == '700048'}">
										      		 	      		  <input type="text" style="width: 95%;"  id="DP${vo_rowNum}" name="DP${vo_rowNum}" value="${vo[17]}"    onchange="changeAndContri('${vo_rowNum}'); changeDAForSelectedPrd('${vo_rowNum}');" style="width: 95%;"/>
										      		  </c:if>
										      		  
										      <input type="hidden" id="hidTypeOfPayment" name="hidTypeOfPayment" value="${vo[6] }"/>
										      		  
										      		  <script>
										      		 var typeOfPayment = document.getElementById("hidTypeOfPayment").value.trim();
										      		 var User = document.getElementById("User").value.trim();
										      		 
										      		  if(document.getElementById("cmbPayCommission${vo_rowNum}").value.trim() == '700016')
										      			  {
										      			  		document.getElementById("DP${vo_rowNum}").readOnly = true;
										      			  }
										      		  else if(User == 'DDO' || User == 'TO')
										      			  {
										      				document.getElementById("DP${vo_rowNum}").readOnly = true;
										      			  }
										      		  else
										      			  {
										      				document.getElementById("DP${vo_rowNum}").readOnly = false;
										      			  }
										      		  </script>
										      		  
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:8%"  sortable="true"  titleKey="CMN.DA" > 
										            
										              <c:if test = "${vo[6] == '700046' || vo[6] == '700047' || vo[6] == '700048' }">
										      		 	      		   <input type="text" style="width: 95%;"  id="DA${vo_rowNum}" name="DA${vo_rowNum}" value="${vo[18]}" onchange="changeAndContri('${vo_rowNum}');"  ${varReadOnly}/>
										      		  </c:if>
										      		  <c:if test = "${vo[6] == '700049' }">  
										      		 	      		   <input type="text" style="width: 95%;"  id="DA${vo_rowNum}" name="DA${vo_rowNum}" value="${vo[12]}" onchange="changeAndContri('${vo_rowNum}');"  />
										      		  </c:if>
										      		
							    </display:column>
							    
							    <display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:9%"  sortable="true"  titleKey="CMN.CONTRIBUTION" >
								<input type="text" style="width: 95%;"  id="contribution${vo_rowNum}" name="contribution${vo_rowNum}" value="${vo[19]}" ${varReadOnly} />
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:9%"  sortable="true"  titleKey="CMN.NPS_CONTRIBUTION_EMPR" >
								<input type="text" style="width: 95%;"  id="contributionNps${vo_rowNum}" name="contributionNps${vo_rowNum}" value="${vo[21]}" readOnly="readOnly" />
								</display:column>  		
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;width:6%"  sortable="true"  titleKey="CMN.ADDROW" media="${varColumnDisabled}">
										<c:if test="${resValue.EditForm != null && resValue.EditForm == 'Y'}">    
								  			 <a href=# onclick="AddNewRowContri('${vo_rowNum}');">Add</a>
								  		</c:if>
								</display:column>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;width:6%"  sortable="true"  titleKey="CMN.DELETEROW" media="${varColumnDisabled}">
										<c:if test="${resValue.EditForm != null && resValue.EditForm == 'Y'}">    
								  			 <a href=# onclick="DeleteRowContri('${vo_rowNum}');">Delete</a>
								  		</c:if>
								</display:column>
								 
								<c:if test="${resValue.lStrUser == 'TO' || resValue.lStrUser == 'DDOAsst' || (resValue.lStrUser == 'DDO' && resValue.lStrUse == 'ViewApproved')}">
									<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:8%"   sortable="true" titleKey="CMN.STATUS" >
									 
									<c:choose>
										<c:when test="${vo[10] == 1 && resValue.lStrUser == 'TO'}">
											 <label id="status${vo_rowNum}" style="color: green;"><center>Approved</center></label>
										</c:when>
										<c:when test="${vo[10] == 3 && resValue.lStrUser == 'TO'}">
											 <label id="status${vo_rowNum}" style="color: black;" ><center>Not approved</center> </label>
										</c:when>
										<c:when test="${vo[10] == 0 && resValue.lStrUser == 'DDOAsst'}">
											 <label id="status${vo_rowNum}" style="color: green;" ><center>Saved</center> </label>
										</c:when>
										<c:when test="${vo[10] == null && resValue.lStrUser == 'DDOAsst'}">
											 <label id="status${vo_rowNum}" style="color: black;" ><center>Not Saved</center> </label>
										</c:when>
										<c:when test="${vo[10] == 1 && resValue.lStrUser == 'DDO'}">
											 <label id="status${vo_rowNum}" style="color: green;" ><center>Approved by Treasury</center> </label>
										</c:when>
										<c:when test="${vo[10] == 3 && resValue.lStrUser == 'DDO'}">
											 <label id="status${vo_rowNum}" style="color: black;" ><center>Pending with Treasury</center> </label>
										</c:when>
									</c:choose>
									<input type="hidden" name="approveStatus${vo_rowNum}" id="approveStatus${vo_rowNum}" value="${vo[10]}"/>
									</display:column>
								</c:if>
								
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;display:none;width:0%"  sortable="true" >
											 		  <c:choose>
											  				<c:when test="${resValue.TypeOfContribution == 'Offline'}">
											  		  			  <input type="checkbox" id="checkbox${vo_rowNum}" name="checkbox" value="0"  checked="checked" style="display: none" />
											  		  		</c:when>
											  		  		<c:otherwise>
											  		  			  <input type="checkbox" id="checkbox${vo_rowNum}" name="checkbox${vo_rowNum}" value="${vo[5]}"  checked="checked" style="display: none"/>
											  		  			  <input type="hidden" name="daRate" id="daRate${vo_rowNum}"  value="${vo[20]}"/> ${vo[9]} ${vo[17]}${vo[18]}${vo[19]}${vo[20]}
											  		  		</c:otherwise>
													  </c:choose>	  
													  
													  <input type="hidden" name="dcpsempId${vo_rowNum}" id="dcpsempId${vo_rowNum}" value="${vo[0]}"/>
													  <input type="hidden" name="hidBasic" id="hidBasic${vo_rowNum}" value="${vo[4]}"/>
													  <input type="hidden" name="hidDP" id="hidDP${vo_rowNum}" value="${vo[17]}"/>
													  <input type="hidden" name="hidDA" id="hidDA${vo_rowNum}" value="${vo[18]}"/>
													  <input type="hidden" name="hidContribution" id="hidContribution${vo_rowNum}" value="${vo[19]}"/>
													  <input type="hidden" name="hidContributionNps" id="hidContributionNps${vo_rowNum}" value="${vo[21]}"/>
													  
													  <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.FirstDate}" var="monthStartDate"/>
													  <input type="hidden" name="hidEmpStartDate" id="hidEmpStartDate${vo_rowNum}" value="${monthStartDate}"/>
													  
													  <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.LastDate}" var="monthEndDate"/>
													  <input type="hidden" name="hidEmpEndDate" id="hidEmpEndDate${vo_rowNum}" value="${monthEndDate}"/>
													  
													  <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[11]}" var="joiningDate"/>
													  <input type="hidden" name="hidJoiningDate" id="hidJoiningDate${vo_rowNum}" value="${joiningDate}"/>
													  
												      <script>
														document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
										 			  </script>
										 			  
										 			  <c:if test="${vo[10] != 1}">
										 			  		<c:set var="varHideBtns" scope="page" value="0"/>
										 			  </c:if>
													
								</display:column> 	
								
						</display:table>
						</div>
						  
						<input type="hidden" id="previousStartDate" value="${resValue.DelFirstDate}" />
						<input type="hidden" id="previousEndDate" value="${resValue.DelLastDate}" />  
						
						</c:otherwise>
						</c:choose>   
						
						<br/>
						<br/>
						<br/>
						
						<fieldset class="tabstyle"><legend><b>
						
						<c:choose>
									<c:when test="${resValue.ContriThruChallanOrNot=='Yes'}" >
										<fmt:message 
												key="CMN.CHALLANDTLS" bundle="${dcpsLables}"></fmt:message>
									</c:when>
									<c:otherwise>
										<fmt:message 
												key="CMN.VOUCHERDTLS" bundle="${dcpsLables}"></fmt:message>
									</c:otherwise>
						</c:choose>
								
						</b></legend>
						<table align="left" width="50%">
							<tr>
								<td width="50%">
								<c:choose>
									<c:when test="${resValue.ContriThruChallanOrNot=='Yes'}" >
										<fmt:message key="CMN.CHALLANNO"
													bundle="${dcpsLables}"></fmt:message>
									</c:when>
									<c:otherwise>
										<fmt:message key="CMN.VOUCHERNO"
													bundle="${dcpsLables}"></fmt:message>
									</c:otherwise>
								</c:choose>
								<input type="text" id="txtVoucherNo"
									style="width: 70%" name="txtVoucherNo" value="${voucherDtlsVO.voucherNo}" ${varReadOnlyForVoucherChallan } />
									
								<c:if test="${resValue.ContriThruChallanOrNot=='Yes'}">
										<label class="mandatoryindicator"}>*</label>					
								</c:if>
								
								</td>
								
								<td width="50%">
								<c:choose>
									<c:when test="${resValue.ContriThruChallanOrNot=='Yes'}" >
										<fmt:message key="CMN.CHALLANDATE"
													bundle="${dcpsLables}"></fmt:message>
									</c:when>
									<c:otherwise>
										<fmt:message key="CMN.VOUCHERDATE"
													bundle="${dcpsLables}"></fmt:message>
									</c:otherwise>
								</c:choose>
								<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${voucherDtlsVO.voucherDate}" var="voucherDate"/>
								<input type="text" name="txtVoucherDate" id="txtVoucherDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" value="${voucherDate}" style="width: 60%" ${varReadOnlyForVoucherChallan} />
								<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtVoucherDate",375,570)'
											style="cursor: pointer;" ${varDisplayCalForVoucherChallan } /> 
											
								<c:if test="${resValue.ContriThruChallanOrNot=='Yes'}">
										<label class="mandatoryindicator"}>*</label>					
								</c:if>
								
								</td>
							</tr>
						</table>
						
						<input type="hidden" id="hidContriVoucherId" name="hidContriVoucherId" value="${voucherDtlsVO.dcpsContriVoucherDtlsId}"/>
						
						<c:if test="${!((resValue.lStrUser == 'ATO' || resValue.lStrUser == 'DDOAsst') && resValue.lStrUse == 'ViewAll')}">
							<fmt:message key="CMN.REMARKSFORREJECTION"
												bundle="${dcpsLables}"></fmt:message>
							<input type="text" id="txtRemarksForRejection"
										style="width: 30%;" name="txtRemarksForRejection" value="${voucherDtlsVO.remarksForRejection}" />
						</c:if>
						</fieldset>
						<br/>
						<br/>
						
						<c:if test="${resValue.UserList!=null}">
										<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>
						</c:if>
						<c:if test="${resValue.UserList==null}">
										<input type="hidden" id="ForwardToPost" name="ForwardToPost" value=""/>
						</c:if>
						
						<div align="center">
							<c:if test="${resValue.lStrUser == 'DDOAsst'}">
								<c:if test="${resValue.lBlFlagBillGenerated == 'true'}">
										<table align="center" width="100%">
											<tr>
												<td style="font-style:italic;color: red ;padding-left: 1%" colspan="5" ><fmt:message key="CMN.NOTEFORBILLALREADYGENERATED" bundle="${dcpsLables}"></fmt:message></td>
											</tr>	
										</table>
								</c:if>
							</c:if>
						</div>
						
						<div align="center">
						 
				        <c:if test="${resValue.lStrUser == 'ATO' || resValue.lStrUser == 'DDOAsst'}">
				       
				        		<hdiits:button  name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
									  												bundle="${dcpsLables}" onclick="ReturnLoginPage();"/> 
								<c:if test="${resValue.ContriThruChallanOrNot != 'Yes'}">  
								
									<c:choose>
										<c:when test="${resValue.lBlFlagBillGenerated == 'true'}">
							        			<hdiits:button	name="btnSaveData" id="btnSaveData" type="button"  readonly="true" 
																						captionid="BTN.SAVE" bundle="${dcpsLables}" 
																						onclick="saveContributionData(1)" />
										</c:when>
										<c:otherwise>
												<hdiits:button	name="btnSaveData" id="btnSaveData" type="button"
																						captionid="BTN.SAVE" bundle="${dcpsLables}" 
																						onclick="saveContributionData(1)" />
										</c:otherwise>
									</c:choose>
									
								</c:if>
								<hdiits:button	name="btnForward" id="btnForward" type="button" 
																					captionid="BTN.FORWARD" bundle="${dcpsLables}"
																					onclick="forwardContributionData()" style="display:none"/>
						</c:if>
						
											
						<c:if test="${(resValue.lStrUser == 'TO' || resValue.lStrUser == 'DDO') && resValue.lStrUse != 'ViewApproved' && resValue.lStrUse != 'ViewReverted'}">
						
								<hdiits:button  name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
									  												bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>   
									  				
								<c:if test="${resValue.lStrUser == 'TO' && varHideBtns != 1 }">
								
									<c:choose>
										<c:when test="${voucherDtlsVO != null}">
											<c:choose>
												<c:when test="${voucherDtlsVO.voucherNo == null || voucherDtlsVO.voucherDate == null}"  >
													<hdiits:button	name="btnApprove" id="btnApprove" type="button" readonly="true"  
																										captionid="BTN.APPROVE" bundle="${dcpsLables}" 
																										onclick="approveContributionData()" />
												</c:when>
												<c:otherwise>
													<hdiits:button	name="btnApprove" id="btnApprove" type="button"  
																										captionid="BTN.APPROVE" bundle="${dcpsLables}" 
																										onclick="approveContributionData()" />
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
												<hdiits:button	name="btnApprove" id="btnApprove" type="button" readonly="true"  
																										captionid="BTN.APPROVE" bundle="${dcpsLables}" 
																										onclick="approveContributionData()" />
										</c:otherwise>
									</c:choose>
									
								</c:if>
								
								<c:if test="${resValue.lStrUser == 'DDO'}">
								<hdiits:button  name="btnForward" id="btnForward" type="button"    
																					captionid="BTN.FORWARD" bundle="${dcpsLables}"
																					onclick="approveContributionData()" />
								</c:if>
							
								<input type="hidden" id="rejectToPost" name="rejectToPost" value="${UserList[0]}"/>
								
								<c:choose>
									<c:when test="${resValue.lStrUser == 'TO' && varHideBtns == 1}">
									
									</c:when>
									<c:otherwise>
										<hdiits:button	name="btnReject" id="btnReject" type="button"   
																						captionid="BTN.REJECT" bundle="${dcpsLables}"
																						onclick="rejectContributionData()" style="display:none"  />
									</c:otherwise>
						     	</c:choose>
						</c:if>
						
						<c:if test="${resValue.lStrUser == 'DDO' && resValue.lStrUse == 'ViewApproved'}">
						
								<hdiits:button  name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
										  											bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>   
								<hdiits:button  name="btnSaveVoucherData" id="btnSaveVoucherData" type="button"
																					captionid="BTN.SAVE" bundle="${dcpsLables}" 
																					onclick="saveVoucherDetails()" style="display:none"  />
							
						</c:if>
						
						<c:if test="${resValue.lStrUser == 'TO' && resValue.lStrUse == 'ViewReverted'}">
						
								<hdiits:button  name="btnSaveData" id="btnSaveData" type="button"
																					captionid="BTN.SAVE" bundle="${dcpsLables}"  
																					onclick="saveContributionData(1)" />
								<hdiits:button  name="btnBackRevert" id="btnBackRevert" type="button"
																					captionid="BTN.BACK" bundle="${dcpsLables}" 
																					onclick="goBackToRevertRequestList();"/>
						
						</c:if>
						
						</div>
						
						</fieldset>
				
				</c:otherwise>
				</c:choose>
				
			</c:otherwise>
		</c:choose>
		
		
		
		<c:choose>
			<c:when test="${resValue.ContriThruChallanOrNot == 'Yes' && empList==null && (resValue.lStrUser == 'DDOAsst' || resValue.lStrUser == 'ATO')}">
				<input type = "hidden" name = "txtTotalRecords" id ="txtTotalRecords" value = "1" />
			</c:when>
			<c:otherwise>
				<input type = "hidden" name = "txtTotalRecords" id ="txtTotalRecords" value = "${totalRecords }" />
			</c:otherwise>
		</c:choose>
		
		<input type = "hidden" name = "User" id ="User" value = "${resValue.lStrUser }" />  
</hdiits:form>

<ajax:select source="cmbDDOCode" target="cmbBillGroup"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBillGroupsForDdo"
	parameters="cmbDDOCode={cmbDDOCode},User={User},Use={Use}"
	preFunction="showProgressBarWhileBillsLoad"
	postFunction="hideProgressBarAfterBillsLoad"
	>
</ajax:select>

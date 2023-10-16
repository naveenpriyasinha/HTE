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
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsTreasury.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationForm.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="queryString" value="${resValue.QueryString}"></c:set>
<c:set var="DeputnList" value="${resValue.EMPDEPUTNLIST}"></c:set>


<hdiits:form name="frmEmpDeputn" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" name="hidString" id="hidString" value="${queryString}"></input>
<fieldset class="tabstyle">
<c:choose>
<c:when test="${queryString ==  'Attach'}">
<legend><b><fmt:message key="CMN.ATTACHDEPEMP" bundle="${dcpsLabels}"></fmt:message> </b></legend>
</c:when>
<c:otherwise>
<legend><b><fmt:message key="CMN.DETACHDEPEMP" bundle="${dcpsLabels}"></fmt:message> </b></legend>
</c:otherwise>
</c:choose>

<table id="tbl1" width="100%" align="left">
<tr>
	<td width="100%" valign="top">
			
					  	<table>
								<tr align="center">
									<td width="25%" align="left" ><fmt:message
										key="CMN.SEVARTHID" bundle="${dcpsLabels}" /></td>
									<td width="50%" align="left"><input type="text"
										id="txtSevaarthId" style="text-transform: uppercase" size="30"
										name="txtSevaarthId"/></td>
								</tr>
								<tr align="center">
									<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
										bundle="${dcpsLabels}" /></td>
									<td width="50%" align="left"><input type="text"
										id="txtEmployeeName" size="30" style="text-transform: uppercase"
										name="txtEmployeeName" />
									<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><label style="color: red"><fmt:message
										key="MSG.SEARCH" bundle="${dcpsLabels}" /></label></td>
								</tr>
						</table>
						
								<div style="width:50;text-align: center;align:center">
								<hdiits:button type="button" captionid="CMN.SEARCH" 
								bundle="${dcpsLabels}" id="btnSearch" name="btnSearch" onclick="showEmpDeputationList();"/>
								<hdiits:button type="button" captionid="BTN.DISPLAYALL" 
								bundle="${dcpsLabels}" id="btnDisplayAll" name="btnDisplayAll" onclick="displayAllDeputedEmpList();"/>
								</div>
	</td>
</tr>
</table>
</fieldset>

<table width="100%">
<tr>
<td>
<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
<div style="width:100%;overflow:auto;height:auto" >
<display:table list="${DeputnList}" size="10" id="vo" pagesize="100"
					cellpadding="5" style="width:98%" requestURI="">	
					
					<display:column  headerClass="datatableheader"  class="oddcentre" style="text-align:center;width:5%;"  title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>">
						<input type="checkbox" name="chkbxDeputnId" id="chkbxDeputnId${vo_rowNum}"
						onclick="" value="${vo[0]}" />
						<input type="hidden" name="txtDeptnId"  id="txtDeptnId${vo_rowNum}" value="${vo[4]}"></input>
					</display:column>
				
					<display:column style="width:15%;text-align: left;"
						class="tablecelltext" titleKey="CMN.OFFICENAME"
						headerClass="datatableheader" sortable="true" >
					<c:choose>
						<c:when test="${queryString ==  'Attach'}">
							<select name="cmbOfficeCode" id="cmbOfficeCode${vo_rowNum}" style="width:90%" onChange="">
								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							<c:forEach var="OfficeList" items="${resValue.OFFICELIST}">			
								<c:choose>
									<c:when test="${OfficeList.id == vo[3]}">
										<option value="${OfficeList.id}" selected="selected"><c:out value="${OfficeList.desc}"></c:out></option>
									</c:when>
									<c:otherwise>
										<option value="${OfficeList.id}"><c:out value="${OfficeList.desc}"></c:out></option>	
									</c:otherwise>
								</c:choose>	
				 			</c:forEach>
							</select>
						</c:when>
						<c:otherwise>
							<select name="cmbOfficeCode" id="cmbOfficeCode${vo_rowNum}" style="width:90%" onChange="">
								<c:forEach var="OfficeList" items="${resValue.OFFICELIST}">
									<c:if test="${OfficeList.id == vo[3]}">					
										<option value="${OfficeList.id}"><c:out value="${OfficeList.desc}"></c:out></option>
									</c:if>
					 			</c:forEach>
				 			</select>
						</c:otherwise>
					</c:choose>
					
						
					</display:column>
			    
				   	<display:column style="width:5%;text-align: left;" class="tablecelltext"
					titleKey="CMN.PENSIONNUMBER" headerClass="datatableheader"
					sortable="true" value="${vo[1]}">				
				    </display:column>
				    
				    <display:column style="text-align: left;width:15%" class="tablecelltext"
					titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader"
					sortable="true" value="${vo[2]}">				
				    </display:column>
				    <c:if test="${queryString ==  'Attach'}">
				    	<display:column style="text-align:left;width:10%;" class="tablecelltext" titleKey="CMN.ATTACHDATE" headerClass="datatableheader" sortable="true" >
				    	
					    	<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[5]}" var="varAttachDate"/>
								<input type="text" size="10" name="txtDateOfAttach${vo_rowNum}" id="txtDateOfAttach${vo_rowNum}" 
								 	onkeypress="digitFormat(this);dateFormat(this);" onfocus="" onblur="validateDate(this);chkDtAfterDeselctn('${vo_rowNum}');" value="${varAttachDate}"/>
								<img src='images/CalendarImages/ico-calendar.gif' width='20'
									onClick='window_open("txtDateOfAttach${vo_rowNum}",375,570)'
									style="cursor: pointer;" ${disabled}/>
									
							<c:choose>
								<c:when test="${vo[8] != null}">
									<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[8]}" var="hidDeselectionDate"/>
									<input type="hidden" id="hidDeselectionDate${vo_rowNum}" value="${hidDeselectionDate}"/>
								</c:when>
								<c:otherwise>
									<input type="hidden" id="hidDeselectionDate${vo_rowNum}" value=""/>
								</c:otherwise>
							</c:choose>
						</display:column>
				    </c:if>
				     <c:if test="${queryString ==  'Detach'}">
				    	<display:column style="text-align:left;width:17%;" class="tablecelltext" titleKey="CMN.DETACHDATE" headerClass="datatableheader" sortable="true" >
				    	
				    	<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[5]}" var="varDetachDate"/>
							<input type="text" name="txtDateOfDetach${vo_rowNum}" id="txtDateOfDetach${vo_rowNum}" 
							 	onkeypress="digitFormat(this);dateFormat(this);" onfocus="" onblur="validateDate(this);chkDtAfterAttachment('${vo_rowNum}');" value="${varDetachDate}"/>
							<img src='images/CalendarImages/ico-calendar.gif' width='20'
								onClick='window_open("txtDateOfDetach${vo_rowNum}",375,570)'
								style="cursor: pointer;" ${disabled}/>
								
							<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[8]}" var="hidAttachDate"/>
							<input type="hidden" id="hidAttachDate${vo_rowNum}" value="${hidAttachDate}"/>
						</display:column>
				    </c:if>
				   
					<display:column style="text-align: left;width:20%"
							class="tablecelltext" titleKey="CMN.REASON"
							headerClass="datatableheader" sortable="true" >
							<select name="cmbReason" id="cmbReason${vo_rowNum}" style="width:100%" onChange="">
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
								<c:forEach var="reasonList" items="${resValue.REASONLIST}">
									<c:choose>
										<c:when test="${reasonList.lookupId == vo[6]}">
											<option value="${reasonList.lookupId}" selected="selected"><c:out value="${reasonList.lookupDesc}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${reasonList.lookupId}"><c:out value="${reasonList.lookupDesc}"></c:out></option>										
										</c:otherwise>
									</c:choose>
							</c:forEach>
							</select>
					</display:column>
				    
				    <display:column style="text-align: left;width:15%" class="tablecelltext"
					titleKey="CMN.REMARKS" headerClass="datatableheader"
					sortable="true">	
						<input type="text" name="txtRemarks" size="30" id="txtRemarks${vo_rowNum}" value="${vo[7]}" ></input>
						 
						<script>
								document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
						</script>
						
				    </display:column>
	   
</display:table>
</div>
</td>
</tr>
</table>
<br/>
<c:choose>
<c:when test="${queryString ==  'Attach'}">
<div style="width:50;text-align: center;align:center">
		<hdiits:button  name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
							bundle="${dcpsLabels}" onclick="ReturnLoginPage();"/> 
		<hdiits:button type="button" captionid="BTN.ATTACH" 
							bundle="${dcpsLabels}" id="btnAttach" name="btnAttach" onclick="dcpsEmpAttach();"/>
								</div>
</c:when>								
<c:otherwise>
<div style="width:50;text-align: center;align:center">
		<hdiits:button  name="btnBack2" id="btnBack" type="button"  captionid="BTN.BACK"
							bundle="${dcpsLabels}" onclick="ReturnLoginPage();"/> 
		<hdiits:button type="button" captionid="BTN.DETACH" 
							bundle="${dcpsLabels}" id="btnDetach" name="btnDetach" onclick="dcpsEmpDetach();"/>
								</div>
</c:otherwise>
</c:choose>

</hdiits:form>

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />

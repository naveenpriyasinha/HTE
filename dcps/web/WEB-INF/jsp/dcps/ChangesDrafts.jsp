<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<%--Added by roshan--%>
<c:set var="talukaList" value="${resValue.talukaList}" ></c:set>
<c:set var="talukaId" value="${resValue.talukaId}" ></c:set>
<c:set var="ddoSelected" value="${resValue.ddoSelected}" ></c:set>

<script type="text/javascript">

function popUpDcpsChangesDraft(changesId,changesTypeValue,empIdValue)
{
	var dcpsChangesId = changesId ;
	var changesType = changesTypeValue;
	var designationDraft = document.getElementById("cmbDesignation").value;
	var EmpId = empIdValue;
	var UserType = document.getElementById("User").value;
	if(changesType == "PersonalDetails")
	{	
		actionFlag = "changesPersonalDetails";
	}
	
	if(changesType == "OfficeDetails")
	{	
		actionFlag = "changesOfficeDetails";
	}
	
	if(changesType == "OtherDetails")
	{	
		actionFlag = "changesOtherDetails";
	}
	
	if(changesType == "NomineeDetails")
	{	
		actionFlag = "changesNomineeDetails";
	}
	
	if(changesType == "PhotoAndSignDetails")
	{	
		actionFlag = "changesPhotoAndSignatureDetails";
	}
	var url = "ifms.htm?actionFlag="+actionFlag+"&EmpId="+EmpId+"&UserType="+UserType+"&designationDraft="+designationDraft+"&changesType="+changesType+"&dcpsChangesId="+dcpsChangesId;
	self.location.href=url;
}

function getDraftList()
{	
	
	var ddoCode= document.getElementById("ddoCode").value;
	var taluka= document.getElementById("cmbTaluka").value;
	var DesignationId = document.getElementById("cmbDesignation").value;
	var User =document.getElementById("User").value; 
	var hidElementId = document.getElementById("hidElementId").value.trim();
	document.getElementById("actionFlag").value = "loadChangesDrafts";
	document.getElementById("DesignationId").value = DesignationId;
	document.getElementById("elementId").value = hidElementId;
	document.getElementById("User").value = User;
	document.getElementById("taluka").value = taluka;
	document.getElementById("ddoCode").value = ddoCode;
	var url = "ifms.htm";
	//alert(url);

	document.DCPSChangesDraftForm.action = url ;
	document.DCPSChangesDraftForm.submit();
	//self.location.href = url;
}

</script>
<style>/*  added by Pratik 08-08-23 */
.changedraftTr input, .changedraftTr select {
	width: 150px !important;
}
</style>
<hdiits:form name="DCPSChangesDraftForm" id="DCPSForm" encType="multipart/form-data"
	validate="true" method="post">
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CHANGESDRFATSINPUTDTLS" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
<input type="hidden" id="hidElementId" value="${resValue.hidElementId}"/>

<table align="center" width="90%" border = "0" ><!-- width chage 50 to 90% by pratik 08-08-23   -->
		<tr class="changedraftTr"><!-- class added by Pratik 08-08-23  -->
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

				<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" value="${ddoSelected}" size="30"/>
			</c:when>
		<c:otherwise>
			<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" size="30"/>
		</c:otherwise>
	</c:choose>			 
		</td>
					<td><fmt:message key="CMN.DESIGNATION"
						bundle="${dcpsLables}"></fmt:message></td>
					<td   ><select name="cmbDesignation"
						id="cmbDesignation" onChange="" >
						<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
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
					
					<input type="hidden" id="User" name="User" value="${resValue.UserType}">
					</td>
		</tr>
		<tr>
		<td colspan="2"></td><!-- added by pratik 08-08-23 -->
		<td id="go"  align = "right" ><br/><hdiits:button
																	name="btnGo" id="btnGo" type="button"
																	captionid="BTN.GO" bundle="${dcpsLables}"
																	onclick="getDraftList();" />
		</td>	
					
		<td id="back" align = "left" ><br/><hdiits:button 
																	name="btnClose" id="btnClose" type="button" 
																	captionid="BTN.BACK" bundle="${dcpsLables}"
																	onclick="ReturnLoginPage();"/>
		</td>
					
		</tr>
</table></fieldset>
<br/>
<br/>


<c:if test="${resValue.ChangesDraftsList != null}">
<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CHANGESDRFATS" bundle="${dcpsLables}"></fmt:message></b> </legend>
	<div align="center">
	<display:table	list="${resValue.ChangesDraftsList}" id="vo" requestURI="" export="" style="width:50%"
			pagesize="10"  >
	<display:setProperty name="paging.banner.placement" value="bottom" />
	
			<display:column headerClass="datatableheader"
				style="text-align:left" class="oddcentre" sortable="true"
				titleKey="CMN.DRAFTSAVEDATE">
				<input type="text" name="draftSaveDate" value="${vo[0]}"/>
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre"
				style="text-align:left" sortable="true" titleKey="CMN.SEVARTHID" value="${vo[6]}">
			</display:column>
	
			<display:column headerClass="datatableheader" class="oddcentre"
				style="text-align:left" sortable="true" titleKey="CMN.EMPLOYEENAME">
				<a href=#
					onclick="popUpDcpsChangesDraft('${vo[5]}','${vo[4]}','${vo[3]}');"><c:out
					value="${vo[2]}" /></a>
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre"
				style="text-align:left" sortable="true" titleKey="CMN.EMPLOYEEID" value="${vo[3]}">
			</display:column>
	
			<display:column headerClass="datatableheader" class="oddcentre"
				style="text-align:left" sortable="true" titleKey="CMN.CHANGEDETAILS" value="${vo[4]}">
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre"
				style="text-align:left" sortable="true" titleKey="CMN.DDOCODE" value="${vo[7]}">
			</display:column>
			
	</display:table>
	</div>
</fieldset>	
</c:if>


<input type = "hidden" name = "actionFlag"  id = "actionFlag">
<input type = "hidden" name = "DesignationId"  id = "DesignationId">
<input type = "hidden" name = "elementId"  id = "elementId">
<input type = "hidden" name = "User"  id = "User">
<input type = "hidden" name = "taluka"  id = "taluka">
<input type = "hidden" name = "ddoCode"  id = "ddoCode">

</hdiits:form>
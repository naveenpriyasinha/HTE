<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="noOfFwdEmpConfig" value="${resValue.noOfFwdEmpConfig}"></c:set>

<script type="text/javascript" src="script/common/common.js"></script>
<script>
function generateExcel()
{
	//alert("generateExcel Calling");
    var url = "ifms.htm?actionFlag=generateExcelFwdEmp";
	document.frmFwdEmpStatistics.action = url ;
	document.frmFwdEmpStatistics.submit(); 
	
}
/*
function getEmpStatistics()
{	
	var Ddocode = document.getElementById("txtDdocode").value;
	if(Ddocode.trim() == "")
	{
		alert("Ddocode Cannot be Empty.");
		document.getElementById("txtDdocode").focus();		
		return false;
	}		
	else
	{
		var url = "ifms.htm?actionFlag=loadEmpDtlsDdoWise&Ddocode="+Ddocode+"&empStat=N&elementId=700215";
		document.frmEmpStatistics.action = url ;
		document.frmEmpStatistics.submit();
	}
		
}
function saveReport() 
{
	document.execCommand("SaveAs");
}
function printReport() 
{

	//document.getElementById('btnExporttoExcel').style.visibility = 'hidden'; // hide
	//document.getElementById('Back').style.visibility = 'hidden'; // hide   
	//document.getElementById('Save').style.visibility = 'hidden'; // hide   
	window.print();
	document.getElementById('Print').style.visibility = 'visible'; // show 
	//document.getElementById('Back').style.visibility = 'visible'; // show 
	//document.getElementById('Save').style.visibility = 'visible'; // show 

	
}*/
</script>
<hdiits:form name="frmFwdEmpStatistics" action="" id="frmEmpStatistics" encType="multipart/form-data" validate="true" method="post">
<!--<c:if test="${lFlag != 'Y'}">
<fieldset class="tabstyle">
<table width="70%" align="center" >
		<tr>
			<td><fmt:message key="DDOCODE" bundle="${commonLables}"></fmt:message>	</td>
			<td><input type="text" maxlength="10" id="txtDdocode" name="txtDdocode" onkeypress="digitFormat(this);" value="${resValue.Ddocode}"/>	<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
			<td><hdiits:button name="btnSubmit" id="btnSubmit" type="button" captionid="EIS.Submit" bundle="${commonLables}" onclick="getEmpStatistics()" /></td>
		</tr>
</table>
</fieldset>
</c:if>
	
--><%--<c:if test="${noOfFwdEmpConfig != null && noOfFwdEmpConfig[0] != null}">--%>
<fieldset class="tabstyle" ><legend>Forward Employee Statistics</legend>
	<div class="scrollablediv" >	
    <display:table list="${noOfFwdEmpConfig}"  id="emp" style="width:100%"  pagesize="500" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />		
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="UID No." >
		<c:if test="${emp[0] != null}">		
				<c:out value="${emp[0]}"></c:out>
		</c:if>
		<c:if test="${emp[0] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="EID No." >
		<c:if test="${emp[1] != null}">		
				<c:out value="${emp[1]}"></c:out>
		</c:if>
		<c:if test="${emp[1] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Employee Name" >
		<c:if test="${emp[2] != null}">		
				<c:out value="${emp[2]}"></c:out>
		</c:if>
		<c:if test="${emp[2] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Date Of Birth" >					
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[3]}" var="DOB"/>
				<c:if test="${emp[3] != null}">	
				<c:out value="${DOB}"></c:out>
				</c:if>
				<c:if test="${emp[3] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Gender" >					
				<c:if test="${emp[4] != null}">	
				<c:out value="${emp[4]}"></c:out>
				</c:if>
				<c:if test="${emp[4] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Date Of Joining"  >	
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[5]}" var="DOJ"/>	
			<c:if test="${emp[5] != null}">
			<c:out value="${DOJ}"></c:out>
			</c:if>		
			<c:if test="${emp[5] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Physically Challenged" >		
				<c:if test="${emp[6] != null}">
				<c:if test="${emp[6]=='FALSE'}">
				<c:out value="-"></c:out>
			</c:if> 
			<c:if test="${emp[6]=='TRUE'}">
				<c:out value="Yes"></c:out>
			</c:if> 			 	
			</c:if>
			<c:if test="${emp[6] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="PAN No." >					
				<c:if test="${emp[7] != null}">
				<c:out value="${emp[7]}"></c:out>
				</c:if>
				<c:if test="${emp[7] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="Cadre" >		
				<c:if test="${emp[8] != null}">
				<c:out value="${emp[8]}"></c:out>
				</c:if>	
				<c:if test="${emp[8] == null}">		
				<c:out value="-"></c:out>
		</c:if>			 		
 		</display:column>
 		
 		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Super Annuation Date"  >	
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[9]}" var="SAA"/>	
			<c:if test="${emp[9] != null}">
			<c:out value="${SAA}"></c:out>
			</c:if>		
			<c:if test="${emp[9] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Pay Commission" >					
				<c:if test="${emp[10] != null}">
				<c:out value="${emp[10]}"></c:out>
				</c:if>
				<c:if test="${emp[10] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Pay Scale Description"  >	
				<c:if test="${emp[11] != null}">
				<c:out value="${emp[11]}"></c:out>
				</c:if> 
				<c:if test="${emp[11] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Pay in Pay Band"  >	
				<c:if test="${emp[12] != null}">
				<c:out value="${emp[12]}"></c:out>
				</c:if> 
				<c:if test="${emp[12] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Grade Pay"  >	
				<c:if test="${emp[13] != null}">
				<c:out value="${emp[13]}"></c:out>
				</c:if> 
				<c:if test="${emp[13] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Basic Pay"  >		
				<c:if test="${emp[14] != null}">
				<c:out value="${emp[14]}"></c:out>
				</c:if> 
				<c:if test="${emp[14] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>		
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="Current Post" >		
				<c:if test="${emp[15] != null}">
				<c:out value="${emp[15]}"></c:out>
				</c:if>	
				<c:if test="${emp[15] == null}">		
				<c:out value="-"></c:out>
		</c:if>			 		
 		</display:column>
 		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Post Sanction Date"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[16]}" var="startDate"/>		
				<c:if test="${emp[16] != null}">
				<c:out value="${startDate}"></c:out>
				</c:if> 
				<c:if test="${emp[16] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Post End Date"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[17]}" var="endDate"/>		
				<c:if test="${emp[17] != null}">
				<c:out value="${endDate}"></c:out>
				</c:if> 
				<c:if test="${emp[17] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Current Institute"  >
				<c:if test="${emp[18] != null}">
				<c:out value="${emp[18]}"></c:out>
				</c:if>
				<c:if test="${emp[18] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Date Of Initial Appointment in Parent Institute"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[19]}" var="DIA"/>		
				<c:if test="${emp[19] != null}">
				<c:out value="${DIA}"></c:out>
				</c:if> 
				<c:if test="${emp[19] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Name Of Post/Designation at First Appointment"  >	
			<c:if test="${emp[20] != null}">
			<c:out value="${emp[20]}"></c:out>
			</c:if>		
			<c:if test="${emp[20] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Individual Approval Order No."  >	
			<c:if test="${emp[21] != null}">
			<c:out value="${emp[21]}"></c:out>
			</c:if>
			<c:if test="${emp[21] == null}">		
				<c:out value="-"></c:out>
		</c:if>		
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Individual Approval Order Date"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[22]}" var="IAD"/>		
				<c:if test="${emp[22] != null}">
				<c:out value="${IAD}"></c:out>
				</c:if> 
				<c:if test="${emp[22] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Employee Type"  >	
			<c:if test="${emp[23] != null}">
			<c:out value="${emp[23]}"></c:out>
			</c:if>		
			<c:if test="${emp[23] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Bank Name"  >		
				<c:if test="${emp[24] != null}">
				<c:out value="${emp[24]}"></c:out>
				</c:if> 
				<c:if test="${emp[24] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
			
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Branch Name"  >	
			<c:if test="${emp[25] != null}">
			<c:out value="${emp[25]}"></c:out>
			</c:if>		
			<c:if test="${emp[25] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
					
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Account No"  >	
			<c:if test="${emp[26] != null}">
			<c:out value="${emp[26]}"></c:out>
			</c:if>		
			<c:if test="${emp[26] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="IFS Code"  >	
			<c:if test="${emp[27] != null}">
			<c:out value="${emp[27]}"></c:out>
			</c:if>		
			<c:if test="${emp[27] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Pay Commision"  >	
			<c:if test="${emp[28] != null}">
			<c:out value="${emp[28]}"></c:out>
			</c:if>		
			<c:if test="${emp[28] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="PF A/C No"  >	
			<c:if test="${emp[29] != null}">
			<c:out value="${emp[29]}"></c:out>
			</c:if>		
			<c:if test="${emp[29] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="GIS Menbership Date"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[30]}" var="GIS"/>		
				<c:if test="${emp[30] != null}">
				<c:out value="${GIS}"></c:out>
				</c:if> 
				<c:if test="${emp[30] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Nominee Name"  >	
			<c:if test="${emp[31] != null}">
			<c:out value="${emp[31]}"></c:out>
			</c:if>		
			<c:if test="${emp[31] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Nominee Date Of Birth"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[32]}" var="NDOB"/>		
				<c:if test="${emp[32] != null}">
				<c:out value="${NDOB}"></c:out>
				</c:if> 
				<c:if test="${emp[32] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Nominee Relationship"  >	
			<c:if test="${emp[33] != null}">
			<c:out value="${emp[33]}"></c:out>
			</c:if>		
			<c:if test="${emp[33] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" title="Percentage Share"  >	
			<c:if test="${emp[34] != null}">
			<c:out value="${emp[34]}"></c:out>
			</c:if>		
			<c:if test="${emp[34] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		</display:column>
		</display:table>	
	</div>
</fieldset>
<%--</c:if>
--%><div align="center"><hdiits:button id="btnExporttoExcel" name="btnExporttoExcel" value="Export to Excel" classcss="bigbutton" type="button" onclick="generateExcel()"/></div>
<%--<hdiits:button id="btnprintReport" name="btnprintReport" value="Print Report" classcss="bigbutton" type="button" onclick="printReport()"/>
<hdiits:button id="btnsaveReport" name="btnsaveReport" value="Save Report" classcss="bigbutton" type="button" onclick="saveReport()"/>--%>
</hdiits:form>
<ajax:autocomplete source="txtDdocode" target="txtDdocode" baseUrl="ifms.htm?actionFlag=getDdoCodeForAutoComplete"
	parameters="searchKey={txtDdocode}" className="autocomplete" minimumCharacters="4" indicator="roleIndicatorRegion"/>	

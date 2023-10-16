<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsChanges.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/login/md5.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<%--Added for ckickjack test--%>
<style id="antiClickjack">body{display:none !important;}</style>


<script type="text/javascript">
if (self == top)
{
//alert("self is equal to top customUI")
var antiClickjack = document.getElementById("antiClickjack");
antiClickjack.parentNode.removeChild(antiClickjack);

}
else {

top.location = self.location;

}

</script>
<%--Added for ckickjack test--%>
<script type="text/javascript">

function filterByDDOCode(){
	var ddoCode= document.getElementById("cmbAsstDDO").value;
	var url;
		url="ifms.htm?actionFlag=viewFormsForwardedByAsst&User=ZPDDO&ddoCode="+ddoCode;
		document.DCPSForwardedFormsList.action= url;
		document.DCPSForwardedFormsList.submit();
}
function updateDetailsRateperhour(){
	var finalSelectedEmp = 0;
	var totalEmp = document.getElementById("hdnCounter").value ;
	//alert("I amin Script funvction"+totalEmp);
	var iValue=document.getElementById("valueI").value;
	var Empsize = document.getElementById("Empsize").value ;
	var totalSelectedEmp = 0;
	var empIds = "";
	var perBasic="";
	var newBasic="";
	var wEffectDate="";
	var k;
	if(Number(iValue)+Number(100)<=Number(totalEmp)){
		totalEmp=Number(100)+Number(iValue);
	
		}
	else
	{
		var cnt=(Number(Empsize)-Number(iValue));
		totalEmp=Number(iValue)+Number(cnt)+Number(1);
		}
	for(k=iValue;k<totalEmp;k++)
	{		
		if(document.getElementById("checkbox"+k).checked)
		{
			finalSelectedEmp = k ;	
			totalSelectedEmp++ ;
		}
	}
	
	if(finalSelectedEmp == 0)
		{
			alert('Please select at least one Employee');
			return false; 
		}	
	for(k=iValue;k<totalEmp;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{
			empIds = empIds + document.getElementById("empId"+k).value.trim() + "~" ;
			perBasic = perBasic + document.getElementById("per"+k).value.trim() + "~" ;
			
			wEffectDate=wEffectDate+document.getElementById("wef"+k).value.trim() + "~";
				var oldBasic=document.getElementById("basic"+k).value.trim();

				//alert("oldBasic is ~ "+oldBasic);
				var pBasic=document.getElementById("per"+k).value.trim();
				if(Number(pBasic) < 1 || Number(pBasic) >600) {
					alert("Please Enter Rate Per Hours in between 1 to 600");
					return false;
				}
				//alert("pBasic is ~ "+pBasic);
				var workinghrs=document.getElementById("workinghrs"+k).value.trim();
				if(Number(workinghrs) < 1 || Number(workinghrs) >  270){
					alert("Please Enter Working Hours in between 1 to 270");
					return false;
				}
				//alert("workinghrs is ~"+workinghrs);
				//var Nbasic=null;
				var nBasic=Number(workinghrs);
				/* nBasic=nBasic/100; */
				newBasic=nBasic + "~";
				var wef=document.getElementById("wef"+k).value.trim();
				var wefdate= new Array();
				wefdate=wef.split("/");
				if (Number(wefdate[0])<1)
				{
				alert("WEF date Can not be Blank.");
				return false;
				}
			
		}
	}
	
	//alert("selected Employee seperated by ~ is "+empIds);
	//alert("selected basic  seperated by ~ is "+perBasic);
	//alert("selected basic  seperated by ~ is "+newBasic);
	//alert("selected date  seperated by ~ is "+wEffectDate);
	
	var answer = confirm("Are you sure, you want to update the details?");
	if(answer){

		document.getElementById("actionFlag").value = "getEmployeeListofRateperHours";
		document.getElementById("empIds").value = empIds;
		document.getElementById("perBasic").value = perBasic;
		document.getElementById("newBasic").value = newBasic;
		document.getElementById("wEffectDate").value = wEffectDate;
	var url = "ifms.htm";
	
	url="./hrms.htm?actionFlag=getEmployeeListofRateperHours&empIds="+empIds+"&perBasic="+perBasic+"&newBasic="+newBasic+"&wEffectDate="+wEffectDate;
	document.DCPSForwardedFormsList.action= url;
	document.DCPSForwardedFormsList.submit();
	
	}

}
function onlyNumberKey(evt) { 
    
    // Only ASCII charactar in that range allowed 
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode 
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) 
        return false; 
    return true; 
} 
</script>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="ddoCode" value="${resValue.ddoCode}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="Empsize" value="${resValue.Empsize}"></c:set>
	<c:set var="hdnCounter" value="0"/>
<hdiits:form name="DCPSForwardedFormsList" id="DCPSForwardedFormsList" encType="multipart/form-data"
	validate="true" method="post">
	<input type="hidden" id="User" name="User" value="${resValue.User}"/>
	<input type="hidden" id="Use" name="Use" value="${resValue.Use}"/>
	
	
		<input type="hidden" id="level" name="level" value="${resValue.User}"/>
		<input type="hidden" id="genTokenNo" name="genTokenNo" value="${resValue.User}"/>
		<input type="hidden" id="genRandomNo" name="genRandomNo" value="${resValue.User}"/>
		<input type="hidden" id="Empsize" name="Empsize" value="${resValue.Empsize}"/>
<div align="center">
<fieldset class="tabstyle" ><legend>All Employee Details</legend>
		
	<br/>

	<div>	
	<% int srno=1; %>
    <display:table list="${empList}"  id="vo" style="width:100%"  pagesize="100" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		
		<display:column titleKey="CMN.CHKBXEMPSELECT"
			title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="text-align:center;"
			class="oddcentre" sortable="true">
			<input type="checkbox" align="left" name="checkbox"
				id="checkbox${vo_rowNum}" value="${vo[0]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Employee Name" >		
				<a href=#
				onclick="popUpDetails('${vo[0]}','${vo[1]}','${vo[0]}');"><c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="empId${vo_rowNum}" value="${vo[0]}"/>
		
				<input type="hidden" id="valueI" name="valueI" value="<%=srno %>">
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Panchayatrajsevaarth Id" >					
				<c:out value="${vo[2]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Basic Salary" >					
				<c:out value="${vo[3]}"></c:out>
				<input type="hidden" id="basic${vo_rowNum}" value="${vo[3]}"/><!--value="${vo[3]}changed by poonam -->
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Rate Per Hour" >	
				 <input type="text" id="per${vo_rowNum}" value="${vo[4]}" onkeypress="return onlyNumberKey(event)" /><label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
		<<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Working Hours" >	
				 <input type="text" id="workinghrs${vo_rowNum}" value="${vo[5]}" onkeypress="return onlyNumberKey(event)" /><label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
		<%-- <display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Working Hours" >	
				 <input type="text"  id ="whr" /><label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column> --%>
		
		
		<%-- <display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Working Hours" >	
				 <input type="text" id="fromDate${vo_rowNum}" value="${vo[5]}"/><label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column> --%>
		
		<%-- <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[8]}" var="toDate"/>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="To Date" >	
				 <input type="text" id="toDate${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(toDate${vo_rowNum})" value="${toDate}" /> --%>
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[6]}" var="wef"/>	   <!--Added by Tejashree  -->
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="With effect From Date" >	
				 <%-- <input type="text" id="wef${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(wef${vo_rowNum})"/> --%>
				 <%-- <label class="mandatoryindicator"${varLabelDisabled} >*</label> --%>
				 <input type="text" id="wef${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(wef${vo_rowNum})" value="${wef}"/>
				 <label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Revised Basic" >	
		<c:out value="${vo[7]}"></c:out>				
		</display:column>
			<c:set var="hdnCounter" value="${hdnCounter+1 }"/>	
 	<%srno=srno+1; %>
		</display:table>	
	
	</div>
</fieldset>
<input type="hidden" id="hdnCounter" name="hdnCounter" value="${ hdnCounter}"/>
	<div align="center">
<hdiits:button	name="BTN.UpdateBasic" id="btnUpdate" type="button"
											captionid="BTN.UpdateBasic" bundle="${dcpsLables}"
											onclick="updateDetailsRateperhour();" />
											</div>
</div>
<input type = "hidden" name = "actionFlag"  id = "actionFlag">
<input type = "hidden" name = "empIds"  id = "empIds">
<input type = "hidden" name = "perBasic"  id = "perBasic">
<input type = "hidden" name = "newBasic"  id = "newBasic">
<input type = "hidden" name = "wEffectDate"  id = "wEffectDate">

</hdiits:form>
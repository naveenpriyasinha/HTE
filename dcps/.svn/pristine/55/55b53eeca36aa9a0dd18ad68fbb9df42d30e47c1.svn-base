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

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript">

function updateDetails11(){
	
	var finalSelectedEmp = 0;
	var totalEmp = document.getElementById("hdnCounter").value ;
	//alert("hii");
	var totalSelectedEmp = 0;
	var empIds = "";
	var perBasic="";
	var newBasic="";
	var wEffectDate="";
	var k;
	for(k=1;k<=totalEmp;k++)
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
	
	for(k=1;k<=totalEmp;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{
			empIds = empIds + document.getElementById("empId"+k).value.trim() + "~" ;
			perBasic = perBasic + document.getElementById("per"+k).value.trim() + "~" ;
			wEffectDate=wEffectDate+document.getElementById("wef"+k).value.trim() + "~";
				var oldBasic=document.getElementById("basic"+k).value.trim();
				//alert(oldBasic);
				var pBasic=document.getElementById("per"+k).value.trim();
				//alert(pBasic);
				var Nbasic=null;
				nBasic=Number(oldBasic)*Number(pBasic);
				nBasic=nBasic/100;
				newBasic=newBasic+nBasic + "~";
				if(Number(pBasic)<1 || Number(pBasic)>100){
					alert("Please Enter percentage of basic in between 0 to 100");
					return false;
				}

				var wef=document.getElementById("wef"+k).value.trim();
				var wefdate= new Array();
				wefdate=wef.split("/");
				//alert(wefdate[0]);
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
	
	document.getElementById("empIds").value=empIds;
	document.getElementById("perBasic").value=perBasic;
	document.getElementById("newBasic").value=newBasic;
	document.getElementById("wEffectDate").value=wEffectDate;
	document.getElementById("actionFlag").value='getEmployeeListForBasicUpdate';
	var answer = confirm("Are you sure, you want to update the details?");
	if(answer){
	var url;
	//url="./hrms.htm?actionFlag=getEmployeeListForBasicUpdate&empIds="+empIds+"&perBasic="+perBasic+"&newBasic="+newBasic+"&wEffectDate="+wEffectDate;
	url="./hrms.htm";
	document.DCPSForwardedFormsList.action= url;
	document.DCPSForwardedFormsList.submit();
	}
}

function updateDetails(){

	//alert('hiiiii im inside');
	var finalOffice = 0;
	var totalOfc = document.getElementById("hdnCounter").value ;

	var totalCount = document.getElementById("counter").value ;
	
	var totalSelectedOffice = 0;
	var allowedOfc = "";
	var totalOffice = "";
	
	var checkedFlag;
	if(document.getElementById("increment").checked==true){	
		
		
		checkedFlag="increment";
	}	
	else {		
		checkedFlag="paybillgeneration";	
	}
	//alert(checkedFlag);
	var k;
	for(k=1;k<=totalOfc;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{
			finalOffice++;
			allowedOfc = allowedOfc + document.getElementById("distId"+k).value.trim() + "," ;
		}
	}
	if(Number(totalCount)<1 && Number(finalOffice)< 1){
		alert('Please select atleast one district.');
		return false;
			}
	//alert("allowedOfc"+allowedOfc);
	var quest;
	if(checkedFlag=="paybillgeneration"){
	if(Number(totalCount)>0 && Number(finalOffice)< 1){
		 quest="Are you sure you  want to allow/disallow the selected districts for Employee Configuration?  ";
		}
	else {
	 quest="Are you sure you  want to allow/disallow the selected districts for Employee Configuration?";
	}
	}else {
		if(Number(totalCount)>0 && Number(finalOffice)< 1){
			 quest="Are you sure you  want to save the changes?  ";
			}
		else {
		 quest="Are you sure you  want to save the changes?";
		}
	}
	var flag='1';
	var answer = confirm(quest);
	if(answer){
	var url;
	url="./hrms.htm?actionFlag=AllwIncremennt&flag="+flag+"&allowedDis="+allowedOfc+"&checkedFlag="+checkedFlag;
	document.DCPSForwardedFormsList.action= url;
	document.DCPSForwardedFormsList.submit();
	}
}

function getRegionDetails(){
	//alert('hiiii');
	var checkedFlag;
	if(document.getElementById("increment").checked==true){	
		//alert('hiiiiiiiii');
		
		checkedFlag="increment";
	}	
	else {		
		checkedFlag="paybillgeneration";
	}
	
	//checkedFlag = document.getElementById("restrict").value;
	//alert('checkedFlag'+checkedFlag);
		url="./hrms.htm?actionFlag=AllwIncremennt&checkedFlag="+checkedFlag;	
		document.DCPSForwardedFormsList.action= url;
		document.DCPSForwardedFormsList.submit();	
}


</script>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set> 

<c:set var="districtList" value="${resValue.districtList}"></c:set>
<c:set var="checkedFlag" value="${resValue.checkedFlag}"></c:set>

<hdiits:form name="DCPSForwardedFormsList" id="DCPSForwardedFormsList" encType="multipart/form-data"
	validate="true" method="post">
	<input type="hidden" id="User" name="User" value="${resValue.User}"/>
	<input type="hidden" id="Use" name="Use" value="${resValue.Use}"/>
<div align="center">
<br/>
<h3 align="left"><i><font color="red">Only selected District's DDO will be able to Configure Post/Employee.</font></i></h3><br/>
<table>
<tr>

<td>
<c:choose>
	<c:when test="${checkedFlag eq 'increment'}">		
		<input type="radio" name="restrict" id="increment" value="increment" onclick = "getRegionDetails()" checked="checked"/>Post Configuration								
	</c:when>
	<c:otherwise>	
		<input type="radio" name="restrict" id="increment" value="increment" onclick = "getRegionDetails()" />Post Configuration
	</c:otherwise>
</c:choose>
</td>

<td>
<c:choose>
	<c:when test="${checkedFlag eq 'paybillgeneration'}">		
		<input type="radio" name="restrict" id="paybillgeneration" value="paybillgeneration" onclick = "getRegionDetails()" checked="checked">Employee Configuration							
	</c:when>
	<c:otherwise>	
		<input type="radio" name="restrict" id="paybillgeneration" value="paybillgeneration" onclick = "getRegionDetails()">Employee Configuration
	</c:otherwise>
</c:choose>
</td>
</tr>
</table>
<fieldset class="tabstyle" ><legend>All District List</legend>

	<c:set var="hdnCounter" value="0"/>
	<c:set var="counter" value="0"/>
	<div class="scrollablediv">	
    <display:table list="${districtList}"  id="vo" style="width:40%"  pagesize="50" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		
		<display:column titleKey="CMN.CHKBXEMPSELECT" style="text-align:center;width:20%"
			title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader"
			class="oddcentre" sortable="true">
			
			<c:if test="${vo[2]==1}">
			<input type="checkbox" align="left" name="checkbox" id="checkbox${vo_rowNum}" checked="checked" value="${vo[0]}" />
			<c:set var="counter" value="${counter+1 }"/>
			</c:if>
			<c:if test="${vo[2]==0}">
			<input type="checkbox" align="left" name="checkbox"	id="checkbox${vo_rowNum}" value="${vo[0]}" />
			</c:if>
		</display:column>
		

		<display:column headerClass="datatableheader" style="text-align:center;width:40%" class="oddcentre" sortable="true" title="District Name" >					
				<c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="distId${vo_rowNum}" value="${vo[0]}"/>
				<c:set var="hdnCounter" value="${hdnCounter+1 }"/>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center;width:40%" class="oddcentre" sortable="true" title="Current Status" >					
		<c:if test="${vo[2]==1}">
		<c:choose>
	<c:when test="${checkedFlag eq 'increment'}">
			<label><font color="green">Allow Post Configuration</font></label>
			</c:when>
			<c:otherwise>	
		<font color="green">Allow Employee Configuration</font></label>
	</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${vo[2]==0}">
			<label><font color="red">Not Allowed</font></label>
		</c:if>		
		</display:column>

		</display:table>	
	</div>
</fieldset>
<input type="hidden" id="counter" name="counter" value="${ counter}"/>
<input type="hidden" id="hdnCounter" name="hdnCounter" value="${ hdnCounter}"/>
	<div align="center">
	<c:if test="${checkedFlag ne null}">
			<hdiits:button	name="BTN.SUBMIT" id="btnUpdate" type="button"
											captionid="BTN.SUBMIT" bundle="${dcpsLables}"
											onclick="updateDetails();" />
			</c:if>

											</div>
</div>

</hdiits:form>
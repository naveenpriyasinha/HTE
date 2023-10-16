<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>


<script type="text/javascript" src="script/common/common.js"></script>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<script language="JavaScript" src="script/dcps/dcpsChanges.js"></script>


<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript">

function filterByDDOCode(){
	var ddoCode= document.getElementById("cmbAsstDDO").value;
	var url;
		url="ifms.htm?actionFlag=viewFormsForwardedByAsst&User=ZPDDO&ddoCode="+ddoCode;
		document.DCPSForwardedFormsList.action= url;
		document.DCPSForwardedFormsList.submit();
}

function getBranchList(counter)
{
//alert("counter"+counter);
	var cmbBank  = document.getElementById("bankname"+counter).value;
	//alert("cmbBank"+cmbBank);
	if(cmbBank != -1)
	{
		var uri="ifms.htm?actionFlag=getBranchList&cmbBank="+cmbBank;
		
		xmlHttp=GetXmlHttpObject();

	   if (xmlHttp==null)
	   {
		   alert ("Your browser does not support AJAX!");
		   return;
	   }  
	   
	  // alert('comes before showProgressbar');
	   showProgressbar();
	   
	   xmlHttp.onreadystatechange=function()
	   {
		   if (xmlHttp.readyState==4)
			{ 
				if(xmlHttp.status==200)
				{
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
					document.getElementById('cmbBranch'+counter).options.length = 0;
					
					var optnStart = document.createElement("OPTION");
					optnStart.value = "-1";
					optnStart.text = "--Select--";
					document.getElementById("cmbBranch"+counter).options.add(optnStart);
					
					for(var j = 0;j<XmlHiddenValues.length;j++)
					{
						var branchName =  XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
						var branchCode =  XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
						var optn = document.createElement("OPTION");
						optn.value = branchCode;
						optn.text = branchName;
						document.getElementById("cmbBranch"+counter).options.add(optn);
					}
					
				}
			}
	   };
	   xmlHttp.open("POST",uri,true);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(uri);
	}
}

function updateDetails(){
	
	var finalSelectedEmp = 0;
	var totalEmp = document.getElementById("hdnCounter").value ;
	//alert("hii"+totalEmp);
	var totalSelectedEmp = 0;
	var empIds = "";
	var bankId="";
	var branchId="";
	var acntNo="";
	
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
			bankId = bankId + document.getElementById("bankname"+k).value.trim() + "~" ;
			branchId = branchId + document.getElementById("cmbBranch"+k).value.trim() + "~" ;
			acntNo = acntNo + document.getElementById("account"+k).value.trim() + "~" ;
				var bank=document.getElementById("bankname"+k).value.trim();
				//alert(bank);
				var branch=document.getElementById("cmbBranch"+k).value.trim();
			//	alert(branch);
				//if(bank=-1){
					//alert("Bank and Branch Field are Mandatory.");
					//return false;
				//}
				//if(branch=-1){
					//alert("Bank and Branch Field are Mandatory.");
					//return false;
				//}
		}
	}
	
	//alert("selected Employee seperated by ~ is "+empIds);
	//alert("selected bankId  seperated by ~ is "+bankId);
	//alert("selected branchId  seperated by ~ is "+branchId);
	
	var answer = confirm("Are you sure, you want to update the details?");
	if(answer){
	var url;
	url="./hrms.htm?actionFlag=getEmployeeListForBankDetailsUpdate&empIds="+empIds+"&bankId="+bankId+"&branchId="+branchId+"&acntNo="+acntNo;
	document.DCPSForwardedFormsList.action= url;
	document.DCPSForwardedFormsList.submit();
	}
}


function populateIfsCode(counter){
	var ifsCode = document.getElementById("cmbBranch"+counter);
	var ifs = ifsCode.options[ifsCode.selectedIndex].text;
	//alert(ifs);
	var ifsId = new Array();
	ifsId = ifs.split("-");
    document.getElementById("ifs"+counter).value=''+ifsId[ifsId.length-1];
}


</script>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="bankList" value="${resValue.BANKNAMES}"></c:set>
<hdiits:form name="DCPSForwardedFormsList" id="DCPSForwardedFormsList" encType="multipart/form-data"
	validate="true" method="post">
	<input type="hidden" id="User" name="User" value="${resValue.User}"/>
	<input type="hidden" id="Use" name="Use" value="${resValue.Use}"/>
<div align="center">
<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.AllEmployeeDetails" bundle="${dcpsLables}"></fmt:message></b> </legend>
	
	<br/>
	<c:set var="hdnCounter" value="0"/>
	<div class="scrollablediv">
	<display:table list="${empList}" id="vo" requestURI="" export="" pagesize="500">

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column titleKey="CMN.CHKBXEMPSELECT"
			title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="text-align:center;width:5%"
			class="oddcentre" sortable="true">
			<input type="checkbox" align="left" name="checkbox"
				id="checkbox${vo_rowNum}" value="${vo[0]}" />
		</display:column>
	
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[2]}"
			var="birthDate" />

		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:left;width:15%" sortable="true" titleKey="CMN.EMPLOYEENAME">
			<a href=#
				onclick="popUpDcpsEmpDataForBankUpdate('${vo[0]}');"><c:out
				value="${vo[1]}" /></a>
				<input type="hidden" id="empId${vo_rowNum}" value="${vo[0]}"/>
				<c:set var="hdnCounter" value="${hdnCounter+1 }"/>
		</display:column>
	
	<display:column headerClass="datatableheader" class="oddcentre"
					style="text-align:left;width:5%" sortable="true" 
					titleKey="CMN.CURRENTSTATUS">
					<c:if test="${vo[8]==0}">
			  		<label>In Draft</label>
			  		</c:if>
			  		<c:if test="${vo[8]==-1}">
			  		<label>Rejected</label>
			  		</c:if>
					<c:if test="${vo[8]==2}">
			  		<label>Forwarded to 2nd Level</label>
			  		</c:if>
			  		<c:if test="${vo[8]==3}">
			  		<label>Forwarded to 3rd Level</label>
			  		</c:if>
			  		<c:if test="${vo[8]==4}">
			  		<label>Forwarded to 4th Level</label>
			  		</c:if>
			  		<c:if test="${vo[8]==10}">
			  		<label>Approved</label>
			  		</c:if>
					
		</display:column>
		<display:column headerClass="datatableheader"
			style="text-align:left;width:8%" class="oddcentre" sortable="true"
			titleKey="CMN.DOB">
			<c:out value="${birthDate}"></c:out>
		</display:column>
		
	<display:column headerClass="datatableheader"
			style="text-align:left;width:40%" class="oddcentre" sortable="true" 
			titleKey="CMN.BANKNAME" >
			<select name="bankname" id="bankname${vo_rowNum}" style="width:100%" onChange="getBranchList('${vo_rowNum}');">
								<option value="-1">
								<fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
									<c:forEach var="bankList" items="${resValue.BANKNAMES}">
										<option value="${bankList[0]}" title="${bankList[1]}"><c:out value="${bankList[1]}"></c:out></option>
								</c:forEach>
								</select>
								<label class="mandatoryindicator"${varLabelDisabled} >*</label>
			</display:column>
		
		<display:column headerClass="datatableheader"
			style="text-align:left;width:45%" class="oddcentre" sortable="true"
			titleKey="CMN.BRANCHNAME">
				<select name="cmbBranch" id="cmbBranch${vo_rowNum}" style="width: 100%"
					onChange="populateIfsCode('${vo_rowNum}');">
					<option value="-1"><fmt:message
						key="COMMON.DROPDOWN.SELECT" /></option>
				</select>
				<label class="mandatoryindicator"${varLabelDisabled} >*</label>
			</display:column>
		
		<display:column headerClass="datatableheader"
			style="text-align:left;width:15%" class="oddcentre" sortable="true" 
			titleKey="CMN.IFSCODE">
			 <input type="text" id="ifs${vo_rowNum}" value="" readonly="readonly"/>
			<label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>	
			
		<display:column headerClass="datatableheader"
			style="text-align:left;width:18%" class="oddcentre" sortable="true" 
			titleKey="CMN.ACNT">
			 <input type="text" id="account${vo_rowNum}" value="${vo[9]}"/>
			<label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
	
	</display:table>
	</div>
</fieldset>
<input type="hidden" id="hdnCounter" name="hdnCounter" value="${ hdnCounter}"/>
</div>
	<div align="center">
<hdiits:button	name="BTN.UpdateBasic" id="btnUpdate" type="button"
											captionid="BTN.UPDATEBANKBRANCH" bundle="${dcpsLables}"
											onclick="updateDetails();" />
											</div>

</hdiits:form>
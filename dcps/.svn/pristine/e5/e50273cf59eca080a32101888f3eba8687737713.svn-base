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



function saveData(){

	//alert('In saveData');
	var finalSelectedEmp = 0;
	var totalEmp = document.getElementById("hdnCounter").value ;
	//alert("I amin Script funvction"+totalEmp);
	var iValue=document.getElementById("valueI").value;
	//alert('Value of serial number'+iValue);
	var Empsize = document.getElementById("Empsize").value ;

	//var totalEmp=Number(20);
	//alert('Empsize is'+Empsize);
	//alert("hii");
	var totalSelectedEmp = 0;
	var empIds = "";
	var empContri="";
	var employerContri="";
	//alert("Value of employeeList is"+Empsize);
	var empInterest="";
    var employerInterest="";
    var total="";

	
	var k;
	

	for(k=iValue;k<totalEmp;k++)
	{

		//alert('Inside the for'+document.getElementById("checkbox"+k).checked);
		
		if(document.getElementById("checkbox"+k).checked)
		{
			//alert('inside the if');
			finalSelectedEmp = k ;	
			totalSelectedEmp++ ;
		//alert('Value of k'+k);
		//break;
		}
		//alert('Checking the value of k'+k);
	}
	
	if(finalSelectedEmp == 0)
		{
			alert('Please select at least one Employee');
			return false; 
		}
	//alert('Inside the secondf for');	
	for(k=iValue;k<totalEmp;k++)
	{
		//alert('Inside the secondf for');
		if(document.getElementById("checkbox"+k).checked)
		{
			empIds = empIds + document.getElementById("empIds"+k).value.trim() + "~" ;
			empContri = empContri + document.getElementById("empContri"+k).value.trim() + "~" ;
			employerContri=employerContri+document.getElementById("employerContri"+k).value.trim() + "~";
			empInterest=empInterest+document.getElementById("empInterest"+k).value.trim() + "~";
			employerInterest=employerInterest+document.getElementById("employerInterest"+k).value.trim() + "~";
			total=total+document.getElementById("total"+k).value.trim() + "~";
				
				
			
		}
	}
	

	var answer = confirm("Are you sure, you want to save the details?");
	if(answer){

		document.getElementById("actionFlag").value = "LegacyDataEntry";
		document.getElementById("empIds").value = empIds;
		//alert("empIds"+empIds);
		document.getElementById("empContri").value = empContri;
		document.getElementById("employerContri").value = employerContri;
		document.getElementById("empInterest").value = empInterest;
		document.getElementById("employerInterest").value = employerInterest;
		document.getElementById("total").value = total;
	var url = "ifms.htm";
	
	url="./hrms.htm?actionFlag=LegacyDataEntry&empIds="+empIds+"&empContri="+empContri+"&employerContri="+employerContri+"&empInterest="+empInterest+"&employerInterest="+employerInterest+"&total="+total;
	document.DCPSForwardedFormsList.action= url;
	document.DCPSForwardedFormsList.submit();
	
	}

}



</script>


<script>
function sync1()
{
	//alert("hii");
	var iValue=document.getElementById("valueI").value;
	var totalEmp = document.getElementById("hdnCounter").value ;
	var k;
	for(k=iValue;k<totalEmp;k++)
	{
  var empContri = document.getElementById("empContri"+k);
  var employerContri = document.getElementById("employerContri"+k);
  employerContri.value = empContri.value;

	}
	}
</script>
<script>
function sync2()
{
	//alert("hii");
	var iValue=document.getElementById("valueI").value;
	var totalEmp = document.getElementById("hdnCounter").value ;
	var k;
	for(k=iValue;k<totalEmp;k++)
	{
	
  var empInterest = document.getElementById("empInterest"+k);
  var employerInterest = document.getElementById("employerInterest"+k);
  employerInterest.value = empInterest.value;

	}
}
</script>


<script>
function sum()
{
	//alert("hii");
	var iValue=document.getElementById("valueI").value;
	var totalEmp = document.getElementById("hdnCounter").value ;
	var k;
	for(k=iValue;k<totalEmp;k++)
	{
	
	var empContri = Number(document.getElementById("empContri"+k).value);
  var employerContri = Number(document.getElementById("employerContri"+k).value);
  var empInterest = Number(document.getElementById("empInterest"+k).value);
  var employerInterest = Number(document.getElementById("employerInterest"+k).value);
  
  var total = empContri + employerContri + empInterest + employerInterest;
  if(isNaN(total))
  {
	  total="";
	  }
 
	 
  document.getElementById("total"+k).value=total;
  
	}
  
}
</script>


<script type="text/javascript">
        var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        function IsNumeric(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
           // document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
    </script>


<script>


function clear_form_elements(ele) {

    tags = ele.getElementsByTagName('input');
    for(i = 0; i < tags.length; i++) {
        switch(tags[i].type) {
            case 'password':
            case 'text':
                tags[i].value = '';
                break;
            case 'checkbox':
            case 'radio':
                tags[i].checked = false;
                break;
        }
    }
   
    tags = ele.getElementsByTagName('select');
    for(i = 0; i < tags.length; i++) {
        if(tags[i].type == 'select-one') {
            tags[i].selectedIndex = 0;
        }
        else {
            for(j = 0; j < tags[i].options.length; j++) {
                tags[i].options[j].selected = false;
            }
        }
    }

    tags = ele.getElementsByTagName('textarea');
    for(i = 0; i < tags.length; i++) {
        tags[i].value = '';
    }
   
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
	<c:set var="hdnCounter" value="1"/>
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
				<input type="hidden" id="empIds${vo_rowNum}" value="${vo[0]}"/>
		
				<input type="hidden" id="valueI" name="valueI" value="<%=srno %>">
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Sevaarth Id" >					
				<c:out value="${vo[2]}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Employee Contribution" >	
				 <input type="text" id="empContri${vo_rowNum}" name="contriF${vo_rowNum}" value="${vo[3]}" onblur="sync1();sum();" onkeypress="return IsNumeric(event);"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Employer Contribution" >	
				 <input type="text" id="employerContri${vo_rowNum}" name="contriS${vo_rowNum}" value="${vo[4]}" disabled="disabled" maxlength="10" />
				 <label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
		
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Employee Interest" >	
				 <input type="text" id="empInterest${vo_rowNum}" name="interestF${vo_rowNum}" value="${vo[5]}" maxlength="10"  onblur="sync2();sum();" onkeypress="return IsNumeric(event);" />
				 <label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
		
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Employer Interest" >	
				 <input type="text" id="employerInterest${vo_rowNum}" name="interestS${vo_rowNum}" value="${vo[6]}" disabled="disabled" maxlength="10"  />
				 <label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
		
		
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Total" >	
				 <input type="text" id="total${vo_rowNum}" name="total${vo_rowNum}" value="${vo[7]}" maxlength="10" disabled="disabled" />
				 <label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</display:column>
		
		
		
		
			<c:set var="hdnCounter" value="${hdnCounter+1 }"/>	
 	<%srno=srno+1; %>
		</display:table>	
	
	</div>
</fieldset>
<input type="hidden" id="hdnCounter" name="hdnCounter" value="${hdnCounter}"/>
	<div align="center">
<hdiits:button	name="BTN.SAVE" id="btnSave" type="button"
											captionid="BTN.SAVE" bundle="${dcpsLables}"
											 onclick="saveData();"/>
								 							 
			<hdiits:button	name="BTN.RESET" id="btnreset" type="button"
											captionid="BTN.RESET" bundle="${dcpsLables}"
											onclick="clear_form_elements(this.form);"/>								
								
											</div>
</div>
<input type = "hidden" name = "actionFlag"  id = "actionFlag">
<input type = "hidden" name = "empIds"  id = "empIds">
<input type = "hidden" name = "empContri"  id = "empContri">
<input type = "hidden" name = "employerContri"  id = "employerContri">
<input type = "hidden" name = "empInterest"  id = "empInterest">
<input type = "hidden" name = "employerInterest"  id = "employerInterest">
<input type = "hidden" name = "total"  id = "total">

</hdiits:form>
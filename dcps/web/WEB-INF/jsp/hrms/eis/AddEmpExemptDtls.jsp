<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="lstExemptTypes" value="${resValue.lstExemptTypes}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<script>
function checkAvailability()
{
	empId = document.getElementById("Employee_ID_exemptSearch").value;
	if(empId!='') {
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		}
		var url = "hrms.htm?actionFlag=checkEmpAvailability&empId="+empId;  	
   var available=0;	
    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var empIdMapping = XMLDocForAjax.getElementsByTagName('empIdMapping');	
				var flag="true";
							
				if(empIdMapping.length != 0) {		
						if(empIdMapping[0].childNodes[0].text==flag){
							available=1;				
							//return true;
						}	
						else {
						    //alert("Record no found");
						    
							//return false;	
						}
				}				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	if(available==1)
		return true;
	else
		return false;	
	}


function addRecord(){	
 
    var empId=document.getElementById("Employee_ID_exemptSearch").value;
    if(empId==''){
    	alert("Select Employee Information");
    	return false;
   	}
    else {
        var flag=checkAvailability();
        
    }    
    if(flag!=true){
    	alert("Selected Employee is not in Payroll ");
        return false;
    } 
//    alert("ururu");
	var empId=document.getElementById("Employee_ID_exemptSearch").value;
    var empName=document.getElementById("Employee_Name_exemptSearch").value;
   
    if(document.frmEmpExemptDtls.exemptType.value == -1){
    	alert("Select the Exemption Type");
    	return false;
    }
    var exemptAmount=document.frmEmpExemptDtls.exemptAmount.value;	
    if(exemptAmount=='') {
		alert("Please Enter Exemption Amount");
		document.frmEmpExemptDtls.exemptAmount.focus();
		return false;
	}
	
	if(exemptAmount<0){
		alert("Exemption Amount must be positive");
		return false;
	}
	var resultCode="true";	
	var strValidChars = "0123456789.-";
	var strChar;
	for (i = 0; i < exemptAmount.length && resultCode == "true"; i++)  {      
      strChar = exemptAmount.charAt(i);      
      if (strValidChars.indexOf(strChar) == -1)  {
         resultCode = false;
         }
      }
      //alert("Result Code is:-"+resultCode);
    if(resultCode != "true"){  
    	alert("Enter Numer Only");
    	return false;
    }    
     
    
    var count = parseInt(document.frmEmpExemptDtls.recCounter.value); // Counter for Controls.
    //alert("Counter is:-"+count);
    //document.frmEmpExemptDtls.recCounter.value=parseInt(document.frmEmpExemptDtls.recCounter.value)+1;
	var trow=document.getElementById('tblEmpExemptDtls').insertRow();
	trow.align="center";

	//var empName=document.frmEmpExemptDtls.empId.options(frmEmpExemptDtls.empId.selectedIndex).text;
	//var empId=document.frmEmpExemptDtls.empId.value;

	var exemptionType=document.frmEmpExemptDtls.exemptType.options(frmEmpExemptDtls.exemptType.selectedIndex).text;
	var exemptTypeId=document.frmEmpExemptDtls.exemptType.value;	
		
	// Inserting Cells
	
	var tdEmployeeName=trow.insertCell(0);
	var tdExemptType=trow.insertCell(1);
	var tdAmount=trow.insertCell(2);
	var isDelete=trow.insertCell(3);
	// This will put dynamically Controls.
	
	tdEmployeeName.innerHTML='<input type="text" style="width:100%" name="empName'+count+'" value="'+empName+'" readonly="true"><input type="hidden" name="empId'+count+'" value="'+empId+'">';
	tdExemptType.innerHTML='<input type="text" style="width:100%" name="exemptionType'+count+'" value="'+exemptionType+'" readonly="true"><input type="hidden" name="exemptTypeId'+count+'" value="'+exemptTypeId+'">';
	tdAmount.innerHTML='<input type="text" style="width:100%" name="exemptionAmount'+count+'" value="'+exemptAmount+'">';
	isDelete.innerHTML='<input type="button" name="delete'+count+'" style="width:100%" value="Delete" onclick="return deleteRecord(this)">'
	//alert("Count:-"+count);
	document.frmEmpExemptDtls.recCounter.value=parseInt(document.frmEmpExemptDtls.recCounter.value)+1;
	//alert("counter is:-"+document.frmEmpExemptDtls.recCounter.value);
	//alert("Counter ius:-"+document.frmEmpExemptDtls.recCounter.value);
	return true;
}

function deleteRecord(currentRecord){
    var recCount=parseInt(document.frmEmpExemptDtls.recCounter.value);   
   document.getElementById('tblEmpExemptDtls').deleteRow(currentRecord.parentNode.parentNode.rowIndex);
    recCount=recCount-1;
    document.frmEmpExemptDtls.recCounter.value = recCount;    
     for(var i=1; i<=recCount; i++)
          {
            var cell1 = document.getElementById('tblEmpExemptDtls').cells(((i*4)+0));
            //alert("Name is:-"+cell1.children[0].name);
            var cell2 = document.getElementById('tblEmpExemptDtls').cells(((i*4)+1));
            var cell3 = document.getElementById('tblEmpExemptDtls').cells(((i*4)+2));
            var cell4 = document.getElementById('tblEmpExemptDtls').cells(((i*4)+3));
                
            cell1.children[0].name = "empName"+(i-1);            
            cell1.children[1].name = "empId"+(i-1);
            cell2.children[0].name = "exemptionType"+(i-1);
            cell2.children[1].name = "exemptTypeId"+(i-1);
            cell3.children[0].name = "exemptionAmount"+(i-1);
            cell4.children[0].name = "delete"+(i-1);            
          }
}

</script>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>

<hdiits:form name="frmEmpExemptDtls" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertUpdateEmpExemptData&edit=N" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="empExemptAdd" bundle="${enLables}"></hdiits:caption></b></a></li>
		</ul>
	</div>
	<div class="exhalftabcontentstyle">
	
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	    <br>
	    <table align="center">
	    <tr>
		<TD class="fieldLabel" colspan="2">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="exemptSearch"/>
						<jsp:param name="formName" value="frmEmpExemptDtls"/>
					</jsp:include>
		 </td>
		 <td>
        <hdiits:hidden id="empId" name = "empId"  />
	    </td>
	    </tr>
	    </table>
	    <br>
	    <table align="center">
		<tr>
			<td width="30%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="exemptType" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td width="20%"><hdiits:select name="exemptType"
			    captionid="exemptType"
			    mandatory="true"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    <c:forEach items="${lstExemptTypes}" var="exemptTypes">
			    	<hdiits:option value="${exemptTypes.itexemptTypeId}">${exemptTypes.itexemptName}</hdiits:option>
			    </c:forEach>
			    </hdiits:select></td>
			    <td width="30%"> </td>
			</TR>
			<tr> </tr> <tr>    
			    <td width="30%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="NGD.amount" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number name="exemptAmount"  style="text-align:right" default="" size="25" caption="NGD.amount" maxlength="100" validation="txt.isrequired,txt.isnumber" mandatory="true"/></td>
			<td width="30%"><hdiits:hidden name="recCounter" captionid="recCounter" default="0"></hdiits:hidden></td>
		</tr>
		<tr> </tr> <tr> </tr>
		 
		</table>
 	</div>  
 	<br>
	<jsp:include page="../../core/AddNavigationPage.jsp" />

	
	<br><font size="2"><b>
	<table id="tblEmpExemptDtls" border="1" bgcolor="ffffff" bordercolor="aaaaaa" align="center">
 			<tr>
 				
 				<td width="20%" align="center">Employee Name</td>
 				<td width="15%" align="center">Type</td>
 				<td width="10%" align="center">Amount</td> 								
 				<td width="10%" align="center"></td> 				
 			</tr>
 		</table>         
	
 	</font>
 	<br>
 <fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="request"/>	
 	<jsp:include page="../../core/PayTabnavigation.jsp" />
 	</div>
 		
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmpExemptData";
			document.frmEmpExemptDtls.action=url;
			document.frmEmpExemptDtls.submit();
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@page import="java.util.ResourceBundle"%>

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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="empList" value="${resValue.empList}"> </c:set>
<c:set var="nonGovDeducTypes" value="${resValue.nonGovDeducTypes}" > </c:set>
<c:set var="nonGovDeducPeriods" value="${resValue.nonGovDeducPeriods}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="Name" value="${resValue.empName}" > </c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<fmt:setBundle basename="resources.eis.eis_Constants" var="enConstants" scope="request"/>
<fmt:message var="p1" key="POR" bundle="${enConstants}" scope="request" />


<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}" ></c:set>

<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="contractEmpType" key="contract" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${commonLable}" scope="request"> </fmt:message>

<script>
//alert("in master...........");
var count=0;

function resetFormm() {
	 if(confirm("All values will be reseted please confirm !") == true){
	 	resetFields();
     }
}			  	

function chkValue1()
{

    var empId="";

	if(document.getElementById("Employee_ID_NonGovEmpSearch").value==document.getElementById("Employee_Name_NonGovEmpSearch").value)
    {

       empId=document.getElementById("Employee_ID_NonGovEmpSearch").value;
    }
   else
   {

		
	empId=document.getElementById("Employee_Name_NonGovEmpSearch").value;
   }

   document.getElementById("Employee_ID_NonGovEmpSearch").value=empId;



   
	empId=document.getElementById("Employee_ID_NonGovEmpSearch").value;

	
	if(empId=="")
	{
		alert("Please search the employee first");
		document.frmBF.deductionType.value = -1;
	}
	else
	{
		
		document.getElementById("Employee_ID_EmpSearch").value=empId;
		document.getElementById("Emp_allow").value='y';
		
		addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch','Emp_allow'));
		
		document.getElementById("Employee_ID_EmpSearch").value='';
		document.getElementById("Emp_allow").value='';
		return true;
	}	
}

function ChkEmp()
{
	var empId=document.getElementById("Employee_ID_NonGovEmpSearch").value;
	var retValue=true;
	if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
				var empMapping = XMLDocForAjax.getElementsByTagName('emp-mapping');	
					
					if(empMapping.length != 0) {	
							var emp = empMapping[0].childNodes[0].text;		
							//added by samir for contractual and fixe scale employee
							var emptype = empMapping[0].childNodes[1].text;		

							if(emptype=="${contractEmpType}" || emptype=="${fixedEmpType}")
							{
							clearEmployee("NonGovEmpSearch");
							document.frmBF.deductionType.value = -1;
							alert("Not Accessible For Fixed and Contractual Employee!!");
							return;
							}
							//end
								if(emp<0)
								{
									var res=confirm("The Employee information is not entered in the system.\n Want To enter Information.");
									resetFields();
									if(res){
							
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')

										var url ="./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y&newEntryEmpId=${empId}"
										else

										var url = "./hrms.htm?actionFlag=newEmpData";
										document.frmBF.action=url;
										document.frmBF.submit();
											retValue=true;
							       		 return true; 
										}
										else
										{
											retValue=false;
							        		return false; 
										}
									
								}
								else if(emp=="n")
								{
									var res=confirm("The Employee Basic Detail is not entered in the System.\n Want To enter Information?");
									resetFields();
									
									if(res){
							

										if( "${empAllRec}"!=null && "${empAllRec}"=='true')

										var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else

											var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId=0&newEntryEmpId=0";
										document.frmBF.action=url;
										document.frmBF.submit();
											retValue=true;
							       		 return true; 
										}
										else
										{
											retValue=false;
							        		return false; 
										} 
						     	}  
								else
								{
							     	retValue=true;
							     	return true; 
							     }
						     	
							}
								
			}
		}
}



function validateForm()
{	
	//document.getElementById("recCounter").value=count;
//alert(document.getElementById("Employee_ID_NonGovEmpSearch").value);

	return true;
}


var empId;
var empname;
function chkKey(e){	
	if(e.keyCode=="13") {
		return false;
	}
	else {		
		return true;
	}
}


//varun sharma
function addNonGovDeducDataToTable()
{
	//alert("=========>addNonGovDeducDataToTable ");
	if(document.getElementById("Employee_ID_NonGovEmpSearch").value != "" && document.getElementById("Employee_ID_NonGovEmpSearch").value != null){
		
		var nonGovDataForVOGEN = new Array('Employee_ID_NonGovEmpSearch','deductionType','deducAmount','startDate','endDate');
		addOrUpdateRecord('addRecord', 'multipleData', nonGovDataForVOGEN,false);

		
	}
	else{
		alert("Employee id not found, ID is:" +document.getElementById("Employee_ID_NonGovEmpSearch").value);
	}
	
}//end method: addNonGovDeducDataToTable()



function addRecord(){
 
	//alert("in add record ..");
	if(document.getElementById("Employee_ID_NonGovEmpSearch").value == ""){
	    alert("Select the Employee");
    	return false;
    }
    if(document.frmBF.deductionType.value == -1){
    	alert("Select the Deduction Type");
    	document.frmBF.deductionType.focus();
    	return false;
    }
    var deducAmount=document.frmBF.deducAmount.value;	
	if(deducAmount=='') {
		alert("Please Enter Deduction Amount");
		document.frmBF.deducAmount.focus();
		return false;
	}
    
    if(document.frmBF.startDate.value == ''){
    	alert("Enter Start Date");
    	document.frmBF.startDate.focus();
    	return false;
    }

    if(document.frmBF.endDate.value != "")
    var diff = compareDate(document.frmBF.startDate.value,document.frmBF.endDate.value);
    if(diff<0){
    	alert("End Date must be Greater then Start Date");
    	document.frmBF.endDate.value=''; 
    	document.frmBF.endDate.focus();   	
    	return false;
    }
    
	if(deducAmount<0){
		alert("Deduction Amount must be positive");
		return false;
	}

	var resultCode=true;	
	var strValidChars = "0123456789";
	
	for (i = 0; i < deducAmount.length && resultCode == true; i++)
      {
      strChar = deducAmount.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
         resultCode = false;
        
         }
      }
      
    if(resultCode != true){  
    	alert("Enter Numer Only");
    	document.frmBF.deducAmount.value='';
    	document.frmBF.deducAmount.focus();
    	return false;
    } 

 	if (xmlHttp.readyState == 4)
	{ 	
 		//alert("before addDataInTable.......... ");
 		//alert("emp ID :: "+document.getElementById("Employee_ID_NonGovEmpSearch").value);
 		//alert("Emp Name :: "+document.getElementById("Employee_Name_NonGovEmpSearch").value);


		var nonGovDataForTableDisplay = new Array('Employee_Name_NonGovEmpSearch','deductionType','deducAmount','startDate','endDate');

		//signature: function addDataInTable(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName)
		var rowId = addDataInTableNon('tblNonGovDeduc','encXML', nonGovDataForTableDisplay,'','deleteRecord');
		
		/* reset():
				- to clear the fields after records have been inserted into dynamic table
				- to avoid user from adding same record, by mistake.
		*/
		resetFields();

	 return true;
	 
	}
}

function addDataInTableNon(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName,rowCounter) {
	//alert('displayFieldArray:' + displayFieldArray.length);
	
	//alert("in eis deleteMethodName "+deleteMethodName+"::editMethodName::"+editMethodName+"::"+viewMethodName);
	
	if(deleteMethodName == undefined) {
		deleteMethodName = '';
	}
	if(editMethodName == undefined) {
		editMethodName = '';
	}
	if(viewMethodName == undefined) {
		viewMethodName = '';
	}
	
	document.getElementById(tableId).style.display='';
	var trow=document.getElementById(tableId).insertRow();
	counter=rowCounter;
	//trow.id = 'row' + counter;
	trow.id = 'row' + hiddenField + counter;
	
	//trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + counter + "' value='" + xmlHttp.responseText + "'/>";				
	trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
	trow.cells[0].style.display = 'none';
	
	var len = displayFieldArray.length;			
	for(var i = 0; i < len; i++) {
		var field = document.getElementById(displayFieldArray[i]);
		//alert(field.type);
		if(field.type == 'select-one') {
			trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;	
		}		
		else if(field.type == 'radio')
		{
			var radio=document.getElementsByName(displayFieldArray[i]);
			for(var j = 0; j < radio.length; j++)
			{
				if(radio[j].checked)
				{
					trow.insertCell(i+1).innerHTML = radio[j].value;
				}
			}
				
		}		
		else {
			trow.insertCell(i+1).innerHTML = field.value;	
		}
	}
		
	if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>Edit</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";			
	
	else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>Edit</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";										
											 
	else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>Edit</a>";
											 
	else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>View</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";			
	
	else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";
		
	else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
		
	else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>View</a>";
		
	counter++;	
}
function deleteRecord(rowId) 
{
	var delCap = "";
	var delNtAlllowed = "";
	try
	{
		delCap = cmnLblArray[2];
		delNtAlllowed = cmnLblArray[3];
	}
	catch (e)
	{
		delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
		delCap = "Are you sure you want to delete this record ?";
	}
	
	if(updateRow != null)
	{
		alert (delNtAlllowed);
		return false;
	}
	var answer = confirm(delCap);
	if(answer)
	{	
		var delRow = document.getElementById(rowId);
		//alert(delRow.rowIndex);
		var lenVar=delRow.parentNode.parentNode;
		delRow.parentNode.deleteRow(delRow.rowIndex);
		var tldLenVar=lenVar.rows.length;
		if(tldLenVar==1)
		{
			lenVar.style.display='none';
		}
	}
//	alert("ANSWER :"+answer);
	return answer;
}	

function resetFields(){

	document.frmBF.deductionType.value=-1;
	document.frmBF.startDate.value='';
	document.frmBF.endDate.value='';
	document.frmBF.deducAmount.value='';
	document.frmBF.accno.value='';
	
}


function chkValue()
{
	 empId=document.getElementById("Employee_ID_NonGovEmpSearch").value;	 
	if(empId=="")
	{
		alert("Please search the employee first");
	}
	else
	{
			    empId=document.getElementById("Employee_ID_NonGovEmpSearch").value;
  		        empName=document.getElementById("Employee_Name_NonGovEmpSearch").value;
	}

	
}

function dateDifferent()
{
if(document.frmBF.deductionType.value=="${p1}")
{
var t1=document.frmBF.startDate.value;
var t2=document.frmBF.endDate.value;
var _Diff=0;
   		//Total time for one day
        var one_day=1000*60*60*24; 

		//Here we need to split the inputed dates to convert them into standard format
        var x=t1.split("/");     
        var y=t2.split("/");

        //date format(Fullyear,month,date) 
        var date1=new Date(x[2],(x[1]-1),x[0]);
  
        var date2=new Date(y[2],(y[1]-1),y[0])
        var month1=x[1]-1;
        var month2=y[1]-1;
        
        //Calculate difference between the two dates, and convert to days               
        _Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day)); 
        
        if(_Diff<1095 && _Diff!=0)
        {
        document.frmBF.startDate.value='';
        document.frmBF.endDate.value='';
        alert('This Scheme is Minimun for 3 Years');
        document.frmBF.startDate.focus();
        }
        else if(_Diff>1826 && _Diff!=0)
        {
        document.frmBF.startDate.value='';
        document.frmBF.endDate.value='';
        alert('This Scheme is Maximum for 5 Years');
        document.frmBF.startDate.focus();
        }
		//_Diff gives the diffrence between the two dates.
}
}
function chkuniqueness()
{
	
	var gpfAcctNo=trim(document.frmBF.accno.value);
	var nonGovDeducType = document.getElementById('deductionType').value;

	var retValue=true;
	if(gpfAcctNo!="")
	{
		chkValue();
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
			try 
			{
		    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	} 
		    catch (e) 
		    {
			 	try 
			 	{
		        	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   	}
				catch (e) 
				{
					alert("Your browser does not support AJAX!");        
				    retValue=false;     
				}
			}
		}
	
		var url = "hrms.htm?actionFlag=getNonGovDeducData&gpfAcctNo="+gpfAcctNo+"&chk=1&deducType="+nonGovDeducType;
		
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var gpfNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
				
					if(loanAdvMapping.length != 0) {	
					
						gpfNo = loanAdvMapping[0].childNodes[0].text;	
						
						if(gpfNo==-1){
							alert("This Account No. is already entered in the system");
							document.frmBF.accno.value='';
							document.frmBF.accno.value.focus();
							
						}
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	}
}

function beforeSubmit()
{
		if("${empAllRec}"=='true')
			document.frmBF.action="./hrms.htm?actionFlag=insertUpdateNonGovDeductionMaster&edit=N&empAllRec=true";
		else
			document.frmBF.action="./hrms.htm?actionFlag=insertUpdateNonGovDeductionMaster&edit=N&empAllRec=false";
			document.frmBF.submit();
}



</script>



<hdiits:form name="frmBF" validate="true" method="POST" action="javascript:beforeSubmit()" encType="multipart/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="NGD.insertNonGovDeductMaster" bundle="${enLables}"/></a></li>
		</ul>
	</div>
	
	<br/>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<c:choose>
		<c:when test="${empAllRec!='true'}">
			<c:set value="display:show" var="displayStyle"/>
		</c:when>
		<c:otherwise>
			<c:set value="display:none" var="displayStyle"/>
				<hdiits:hidden name ="Employee_ID_NonGovEmpSearch" default="${empId}"/>
		</c:otherwise>
	</c:choose>
		<br/>
		
	<table  width="85%" align="center" border="1">	
		<tr align="center">
			<td align="center">
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="NonGovEmpSearch"/>
						<jsp:param name="formName" value="frmBF"/>
						<jsp:param name="functionName" value="chkValue1"/>
				</jsp:include>
			</td>
		 	<td align="center">
        		<hdiits:hidden id="empId" name = "empId"  />
       			<hdiits:hidden name="Employee_ID_EmpSearch"></hdiits:hidden>
				<hdiits:hidden name="Emp_allow"></hdiits:hidden>
	    	</td>
		</tr>
	</table>
		
		
	<table width="75%" align="center"><br>
		<tr>

			<td>&nbsp;</td>
			<td><font size="2"><b><hdiits:caption captionid="NGD.deductionType" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td>
				<hdiits:select 	name="deductionType" id="deductionType"
								caption="Serch Employee"
			    				captionid="NGD.deductionType"
			    				mandatory="true"
			    				onchange="chkValue1()">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    	<c:forEach items="${nonGovDeducTypes}" var="deducTypes">
			    		<hdiits:option value="${deducTypes.deducCode}">${deducTypes.deducDisplayName}</hdiits:option>
			    	</c:forEach>
			    </hdiits:select>
			</td>
			    <td>&nbsp;</td>
			<td align="left" width="20%" ><font size="2"><b><hdiits:caption captionid="NGD.amount" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td align="left" width="30%" ><hdiits:text name="deducAmount" style="text-align:right" default="" size="14" caption="Deduction Name"  maxlength="8" mandatory="true" /></td>
		</tr>
		<tr></tr>
		<tr>
		<tr><td></td>
			<td align="left" width="20%" > <font size="2"><b><hdiits:caption captionid="NGD.startDate" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td align="left" width="30%" > <hdiits:dateTime name="startDate" mandatory="true"  caption="NGD.startDate" bundle="${enLables}" validation="txt.isdt"  onblur="dateDifferent();" minvalue=""/> </td>
			<td></td>
			<td align="left" width="20%" > <font size="2"><b><hdiits:caption captionid="NGD.endDate" bundle="${enLables}" ></hdiits:caption> </b> </font></td>
			<td align="left" width="30%" > <hdiits:dateTime captionid="NGD.endDate"   bundle="${enLables}" name="endDate" validation="txt.isdt" onblur="dateDifferent();" minvalue=""/> </td>
		</tr>
		<tr>
		<!--<tr style= "display:none"><td></td>
			<td align="left" width="20%" ><font size="2"><b><hdiits:caption captionid="NGD.accNo" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td align="left" width="30%" ><hdiits:text name="accno" id="accno" captionid="NGD.accNo"  size="14"   maxlength="20"   bundle="${enLables}"  onblur="checkSplCharExceptArg(this,':/');chkuniqueness();"/></td>
		</tr>

		--></table>
 	 
 	


	<table id="btnAdd" style="" align="center">
		<tr>
			<td class="fieldLabel" align="center" colspan="4">
				<hdiits:button  name="add"  type="button"  caption="Add" onclick="addNonGovDeducDataToTable()"></hdiits:button>
			</td>
		</tr>
	</table>


		<table width="75%" id="tblNonGovDeduc" border="1" bgcolor="ffffff" bordercolor="aaaaaa" align="center">
 			<tr>
 				<td width="17%" align="center">Employee Name</td>
 				<td width="17%" align="center">Type</td>
 				<td width="17%" align="center">Amount</td>
 				<td width="17%" align="center">Start Date</td>
 				<td width="17%" align="center">End Date</td>
 				<td width="17%" align="center">Delete Record</td>
 			</tr>
 		</table>
	
 	</font>
 	<br>
 	 	<c:choose>
			<c:when test="${empAllRec eq true }">
				<hdiits:hidden default="getNonGovDeductionMaster&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" name="givenurl"/>
			</c:when>
			<c:otherwise>
					<hdiits:hidden default="getNonGovDeductionMaster" name="givenurl"/>
			</c:otherwise>
		</c:choose>
 	
 		<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
 		<fmt:setBundle basename="resources.eis.eisLables_en_US"   var="Lables" scope="request"/>	
 		</div>
 		<jsp:include page="../../core/PayTabnavigation.jsp" />
 	
 	
 		
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
   	    if(("${empId}"!=null && "${empId}"!=0) && "${empAllRec}"!=null && "${empAllRec}"=='true')
   	    {
   	      document.getElementById('searchEmpDiv').style.visibility = 'hidden';
		  document.getElementById("Employee_ID_NonGovEmpSearch").value = ${empId};

		}
		else
		{
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			if("${empId}"!=null && "${empId}"!='' && "${empAllRec}"=='added')
			{

			var url="./hrms.htm?actionFlag=getNonGovDeductionMaster&Employee_ID_NonGovEmpSearch=${empId}&empAllRec=Y";
			}
			else
			{

			var url="./hrms.htm?actionFlag=getNonGovDeductionMaster";
			}
			document.frmBF.action=url;
			document.frmBF.submit();
		}
		}
		if('${Name}'!= null && '${Name}'!= "")
		{	
				document.frmBF.Employee_srchNameText_NonGovEmpSearch.value='${Name}';
				GoToNewPageNonGovEmpSearch();
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
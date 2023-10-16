<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>


<script type="text/javascript"	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/leave/DateDifference.js"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/leavevalidation.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/DateVal.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/hrms/ess/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>


<script type="text/javascript"  src="common/script/tagLibValidation.js"></script>
<script type="text/javascript"  src="common/script/commonfunctions.js"></script>
<script type="text/javascript"	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"   	src="common/script/tagLibValidation.js"></script>
<script type="text/javascript"	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="empName" value="${resValue.empList}"> </c:set>
<c:set var="leaveList" value="${resValue.leaveList}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<c:set var="notAdminUser" value="${resValue.notAdminUser}" ></c:set>
<c:set var="loggedInUser" value="${resValue.loggedInUser}" ></c:set>
<c:set var="empname" value="${resValue.empName}"> </c:set>

<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}" ></c:set>
<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="contractEmpType" key="contract" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${commonLable}" scope="request"> </fmt:message>
<script>
var count=0;
function checkleavedate()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	var retValue=true;
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
		var leaveFromDate= document.empLeave.leaveFromDate.value;
		var leaveToDate= document.empLeave.leaveToDate.value;
				
		var url = "hrms.htm?actionFlag=checkleavedate&empId="+empId+"&leaveFromDate="+leaveFromDate+"&leaveToDate="+leaveToDate+"&empLeaveId=0";  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {	
						LoanNo = loanAdvMapping[0].childNodes[0].text;	
						
						if(LoanNo==-1)
							retValue=true;
						else
						    retValue=false; 	
					}
				}
			}
		}		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	
}

function chkFunc()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	var retValue=true;
	if(empId=="")
	{
		alert("Please search the employee first");
		retValue=false;
	}
	else
	{
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
		var url = "hrms.htm?actionFlag=getEmpLeaveData&empId="+empId+"&chk=1";  
		
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					if(loanAdvMapping.length != 0) {	
						LoanNo = loanAdvMapping[0].childNodes[0].text;
						document.getElementById("leaveType").disabled=false;
						
							var emptype = loanAdvMapping[0].childNodes[1].text;	
							/*if(emptype==300018)
							{
							 clearEmployee("EmpSearch");
							 document.empLeave.leaveFromDate.value='';
  							 self.focus;
							alert("Not Accessible For Contractual Employee!!");
							return false;
							}*/
							
							if(emptype=="${fixedEmpType}" || emptype=="${contractEmpType}")
							{
								document.empLeave.leaveType.value=1;//Hard-Coded for fix-pay and contractual employee only get CL
								document.getElementById("leaveType").disabled=true;
							}
						
							if(LoanNo==-1){
							var res = confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered into the system. \n Want to Enter the Infomation.");
							document.empLeave.leaveFromDate.value='';
							retValue=false;
							document.empLeave.reset();
							if(res){
							
							
							
										//alert("${empAllRec}");
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else
										//alert('in else'+ empId);
											var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId=0&newEntryEmpId=0";

						
							document.empLeave.action=url;
							document.empLeave.submit();
							}
							}
							if(LoanNo==0){
							var res = confirm("The basic information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered into the system. \n Want to Enter the Infomation.");
							document.empLeave.leaveFromDate.value='';
							retValue=false;
							document.empLeave.reset();
							if(res){
							
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y&newEntryEmpId=${empId}"
										else
										//alert('in else'+ empId);
										var url = "./hrms.htm?actionFlag=newEmpData";
							document.empLeave.action=url;
							document.empLeave.submit();
							}
						}
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return true;

	}
}

function cmpDate()
{
	 var diff = compareDate(document.empLeave.leaveFromDate.value,document.empLeave.leaveToDate.value);   
	 
	 if(document.empLeave.leaveToDate.value!=null&&document.empLeave.leaveToDate.value!='')
	 {
	 	if(diff < 0)
  	 	{
   			alert("To Date must be greater than From Date");
   			document.empLeave.leaveToDate.value='';
   			return false;
  	 	}
  	 	else
  	 	{
 			datediff(document.empLeave.leaveFromDate.value,document.empLeave.leaveToDate.value);
  		 }
  	 }
  	 }



function datediff(paramFirstDate,paramSecondDate)
{

	var DD = paramFirstDate.substr(0,2);
	var MM = paramFirstDate.substr(3,2)-1;
	var YY = paramFirstDate.substr(6,4);
	
	var dd = paramSecondDate.substr(0,2);
	var mm = paramSecondDate.substr(3,2)-1;
	var yy = paramSecondDate.substr(6,4);
	
		
	var monthdiff = eval(mm - MM);
	var diff = Date.UTC(yy,mm,dd) - Date.UTC(YY,MM,DD);
	//var diff = toDate.getTime() - fromDate.getTime();
	var day = (diff)/(24*60*60*1000);
	
	
		document.empLeave.leaveTaken.value=Math.round(day)+1;
	
}
function validateForm()
{	
    cmpDate();
    chkFunc();
	if(checkleavedate()!=true)
     {
   		alert("Leave Dates are overlapping");
   		document.empLeave.action = 'javascript:submit()';
  	 }
  	 else
	return true;
}
function beforeSubmit()
{
		
		var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.empLeave.empName.value=empName;
		if( "${empAllRec}"!=null && "${empAllRec}"=='true')
		{
		document.empLeave.action="./hrms.htm?actionFlag=insertEmpLeaveDtls&edit=N&empAllRec=true";
		}
		else
		{
		document.empLeave.action="./hrms.htm?actionFlag=insertEmpLeaveDtls&edit=N&empAllRec=false"
		}
		document.empLeave.submit();
}

function setbalance()
{
if(document.empLeave.leaveType.value==14)
{
document.empLeave.availBal.value=0;
document.empLeave.availBal.disabled=true;
}
else
{
document.empLeave.availBal.disabled=false;
}
}



function addRecord()
{
	if(document.getElementById("Employee_ID_EmpSearch").value == "")
	{
	    alert("Please select the employee");
    	return false;
    }
    
    if(document.empLeave.leaveFromDate.value == "")
	{
	    alert("Enter the from date");
	    document.empLeave.leaveFromDate.focus();
    	return false;
    }
    
    if(document.empLeave.leaveToDate.value == "")
	{
	    alert("Enter the to date");
	    document.empLeave.leaveToDate.focus();
    	return false;
    }
    
    if(document.getElementById("leaveType").value == "choose")
	{
	    alert("Please select the leave type");
	    document.empLeave.leaveType.focus();
    	return false;
    }
    
    if(document.empLeave.availBal.value == "")
	{
	    alert("Enter available balance");
	    document.empLeave.availBal.focus();
    	return false;
    }
    
    if(validateForm()==true)
    {
    
	var trow=document.getElementById('empLeaveTable').insertRow();
	trow.align="center";
	
	empId=document.getElementById("Employee_ID_EmpSearch").value;
    empName=document.getElementById("Employee_Name_EmpSearch").value;
    
    var fromDate = document.empLeave.leaveFromDate.value;
    var toDate = document.empLeave.leaveToDate.value;
    var leaveType = document.empLeave.leaveType.options(empLeave.leaveType.selectedIndex).text;
    var leaveTypeId = document.getElementById('leaveType').value;
    var availBal =  document.empLeave.availBal.value;
    var leaveTaken = document.empLeave.leaveTaken.value;

	//inserting cell
	var tdEmployeeName=trow.insertCell(0);
	var tdFromDate=trow.insertCell(1);    
	var tdToDate=trow.insertCell(2);
	var tdLeaveType=trow.insertCell(3);
	var tdAvailBal=trow.insertCell(4);
	var tdLeaveTaken=trow.insertCell(5);

	var isDelete=trow.insertCell(6);

    tdEmployeeName.innerHTML='<input type="text" style="width:100%" name="empName'+count+'" value="'+empName+'" readonly="true"><input type="hidden" name="empId'+count+'" value="'+empId+'">';
    tdFromDate.innerHTML='<input type="text" style="width:100%" name="fromDate'+count+'" value="'+fromDate+'" readonly="true"><input type="hidden" name="fromDate'+count+'" value="'+fromDate+'">';
    tdToDate.innerHTML='<input type="text" style="width:100%" name="toDate'+count+'" value="'+toDate+'" readonly="true"><input type="hidden" name="toDate'+count+'" value="'+toDate+'">';
    tdLeaveType.innerHTML='<input type="text" style="width:100%" name="leaveType'+count+'" value="'+leaveType+'" readonly="true"><input type="hidden" name="leaveTypeId'+count+'" value="'+leaveTypeId+'">';
    tdAvailBal.innerHTML='<input type="text" style="width:100%" name="availBal'+count+'" value="'+availBal+'" readonly="true"><input type="hidden" name="availBal'+count+'" value="'+availBal+'">';
    tdLeaveTaken.innerHTML='<input type="text" style="width:100%" name="leaveTaken'+count+'" value="'+leaveTaken+'" readonly="true"><input type="hidden" name="leaveTaken'+count+'" value="'+leaveTaken+'">';
    isDelete.innerHTML='<input type="button" name="delete'+count+'" style="width:100%" value="Delete" onclick="return deleteRecord(this)">'
    
    count=count+1;
	document.empLeave.recCounter.value=count;
	resetFields();
	return true;
	}
	else
		return false;
}

function resetFields()
{
	document.empLeave.leaveFromDate.value="";
	document.empLeave.leaveToDate.value="";
	document.empLeave.leaveType.value="choose";
	document.empLeave.availBal.value="";
	document.empLeave.leaveTaken.value="";
}

function deleteRecord(currentRecord){
	//alert('Will be deleted');
    //var recCount=parseInt(document.frmBF.recCounter.value);
    count=count-1;
    //alert('Requirement count is ' + reqcount);
    //alert("Value:-"+currentRecord.parentNode.parentNode.rowIndex);
   document.getElementById('empLeaveTable').deleteRow(currentRecord.parentNode.parentNode.rowIndex);
    //recCount=recCount-1;
    //document.frmBF.recCounter.value = recCount;
    //alert("The Record Count is:-"+recCount);
    //alert('Req count after delete ' + reqcount);
    document.empLeave.recCounter.value = count
     for(var i=1; i<=count; i++)
          {
            var cell1 = document.getElementById('empLeaveTable').cells(((i*5)+0));
            var cell2 = document.getElementById('empLeaveTable').cells(((i*5)+1));
            var cell3 = document.getElementById('empLeaveTable').cells(((i*5)+2));
            var cell4 = document.getElementById('empLeaveTable').cells(((i*5)+3));
            var cell5 = document.getElementById('empLeaveTable').cells(((i*5)+4));
            var cell6 = document.getElementById('empLeaveTable').cells(((i*5)+5));
             
            cell1.children[0].name = "empName"+(i-1);
            cell1.children[1].name = "empId"+(i-1);
            cell2.children[0].name = "fromDate"+(i-1);
            cell3.children[0].name = "toDate"+(i-1);
            cell4.children[0].name = "leaveType"+(i-1);
            cell4.children[1].name = "leaveTypeId"+(i-1);
            cell5.children[0].name = "availBal"+(i-1);
            cell6.children[0].name = "leaveTaken"+(i-1);
            cell7.children[0].name = "delete"+(i-1);
           
          }
}


function checkForm()
{

	document.getElementById("recCounter").value=count;
	//if(document.getElementById("recCounter").value<=0)
	if(count<=0)
	{
	alert("Please add employee");
	return false;
	}	
	else 
		return true;
	
}

function callAction()
{

var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.empLeave.empName.value=empName;
		if( "${empAllRec}"!=null && "${empAllRec}"=='true')
		{
		document.empLeave.action="./hrms.htm?actionFlag=multipleAddLeave&edit=N&empAllRec=true&empId=${empId}";
		}
		else
		{
		document.empLeave.action="./hrms.htm?actionFlag=multipleAddLeave&edit=N&empAllRec=false"
		}
		document.empLeave.submit();
		
	
}
</script>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<hdiits:form name="empLeave" validate="true" method="POST"
	action="javascript:callAction()" encType="text/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="LEV.master" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	<c:choose>
	
	<c:when test="${empAllRec!='true' and notAdminUser ne 1}">
	<c:set value="display:show" var="displayStyle"/>
		</c:when>
		<c:when test="${notAdminUser eq 1}">
			<c:set value="display:none" var="displayStyle"/>
			<hdiits:hidden name ="Employee_ID_EmpSearch" default="${loggedInUser}"/>
		</c:when>
		<c:otherwise>
			<c:set value="display:none" var="displayStyle"/>
			<hdiits:hidden name ="Employee_ID_EmpSearch" default="${empId}"/>
			
		</c:otherwise>
		</c:choose>
		
	<table  width="85%" align="center" style="${displayStyle}">	<tr>
		<TD class="fieldLabel" colspan="2">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpSearch"/>
						<jsp:param name="formName" value="empLeave"/>
						<jsp:param name="functionName" value="chkFunc"/>
					</jsp:include>
		 </td>
		 
		 <td>
		 
         <hdiits:hidden id="empName" name = "empName" default="" />
	    </td>
	    </table>

	    <table width= "65%" align= "center"><br>
	    <tr>
			<td><b><hdiits:caption captionid="LEV.fromDate" bundle="${commonLables}"/></td>
			<td>
			<hdiits:dateTime name="leaveFromDate" captionid="LEV.fromDate"
				bundle="${commonLables}" mandatory="true" 
				 tabindex="5"></hdiits:dateTime></td>
			
			
		</tr> 
		
		
		<tr>
			<td><b><hdiits:caption captionid="LEV.toDate" bundle="${commonLables}"/></td>
			<td>
			
			<hdiits:dateTime name="leaveToDate" captionid="LEV.toDate"
				bundle="${commonLables}" mandatory="true" 
				 tabindex="5"></hdiits:dateTime>
			
			</TD>	
		</tr> 
	    

	    
	    <tr>
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="LEV.type" bundle="${commonLables}"></hdiits:caption>
		</TD>
		
		
		<b><td><hdiits:select  mandatory="true" name="leaveType" size="1" onchange="setbalance()"
                       id="leaveType" captionid="LEV.type" bundle="${commonLables}"   
                       >
                        
        <hdiits:option value="choose">--Select-----</hdiits:option>
         
         <c:forEach var="leaveType" items="${leaveList}">
         <hdiits:option value="${leaveType.leavecode}"><c:out value="${leaveType.leaveTypeName}"> </c:out></hdiits:option>
         </c:forEach>
		 
		 </hdiits:select > 
        </td>
	    </tr>
	    
	   
	   <tr>
			<td><b><hdiits:caption captionid="LEV.availBal" bundle="${commonLables}"/></td>
			<td><hdiits:number mandatory="true"  name="availBal" default="" captionid="LEV.availBal" bundle="${commonLables}"  validation="txt.isnumber"  maxlength="3"   size="22" onblur="cmpDate()"/> </td>
	    </tr>
	    
	    
	    <tr>
			<td><b><hdiits:caption captionid="LEV.taken" bundle="${commonLables}"/></td>
			<td><hdiits:number name="leaveTaken" default="" captionid="LEV.taken" bundle="${commonLables}"  readonly="true" validation="txt.isnumber"  maxlength="10"   size="22"/> </td>
	    </tr>
	    
	    
	     
	</table>
 	</div>
 	<jsp:include page="../../core/AddNavigationPage.jsp" />
 	<br><font size="2"><b>
	<table width="75%" id="empLeaveTable" border="1" bgcolor="ffffff" bordercolor="aaaaaa" align="center">

 			<tr>
 				
 				<td width="17%" align="center">Employee Name</td>
 				<td width="17%" align="center">From Date</td>
 				<td width="17%" align="center">To Date</td>
 				<td width="17%" align="center">Leave Type</td>
 				<td width="17%" align="center">Available Balance</td>
 				<td width="17%" align="center">Leave Taken</td>

 				<td align="center"><hdiits:hidden name="recCounter" id="recCounter" default="0"/> </td>
 			</tr>
 		</table>         
	
 	</font>
 	 	<c:choose>
  	<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getEmpLeaveData&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getEmpLeaveData" name="givenurl"/>
</c:otherwise>
</c:choose>
	<hdiits:jsField  name="validate" jsFunction="checkForm()" /> 

<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")

		if("${empname}"!=null&&"${empname}"!='')
		{
				document.empLeave.Employee_srchNameText_EmpSearch.value="${empname}";
			    GoToNewPageEmpSearch();
		}
		
		if('${empId}'!=null && '${empId}'!=0  && "${empAllRec}"!=null && "${empAllRec}"=='true')
   	    {
  // 	      alert("inside if of Loan");
   	      document.getElementById('searchEmpDiv').style.visibility = 'hidden';
		  document.getElementById('Employee_ID_EmpSearch').value = '${empId}';
		//  alert(' ' + document.getElementById("Employee_ID_EmpOtherDtls").value);

	
		}
		else
		{
		if("${msg}"!=null&&"${msg}"!='')
		{
					alert("${msg}");
					if("${empId}"!=null && "${empId}"!='' && "${empAllRec}"=='added')
					{
	
					var url="./hrms.htm?actionFlag=getEmpLeaveData&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y";
					
					}
					else 
					{
	
					var url="./hrms.htm?actionFlag=getEmpLeaveData";
					}
			document.empLeave.action=url;
			document.empLeave.submit();
		}
		}
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
		
</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


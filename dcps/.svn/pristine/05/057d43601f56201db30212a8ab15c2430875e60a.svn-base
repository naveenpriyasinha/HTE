<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript">

function openEmpFormHyperLinked(empId,dcpsId,empName,dob)
{
	var url = "ifms.htm?actionFlag=popUpEmpDtls&empId="+empId+"&User=DDO";
	url = url + "&FromSearchEmp=Yes";
	self.location.href=url;
}

function ChangePayDetails()
{
	var noOfEmployeesSelected = 0;
	var flag = 0;
	var totalSelectedEmployees=document.getElementById("totalCount").value;

	for(var i=0;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("chkbxFormVeri"+i).checked == true)
			{
				flag = 1;	
				noOfEmployeesSelected++ ; 
			}
	}
	
	if(flag == 1)
	{
		if(noOfEmployeesSelected == 1)
		{
			for(i=0;i<totalSelectedEmployees;i++)
			{
				if(document.getElementById("chkbxFormVeri"+i).checked == true)
					{
						var pfdChange = document.getElementById("hidPfdChanged"+i).value;
						var EmpId = document.getElementById("chkbxFormVeri"+i).value;
						
							var hidDcpsId = document.getElementById("hidEmpDcpsId"+i).value;
							var hidEmpName = document.getElementById("hidEmpName"+i).value;
							var hidBirthDate = document.getElementById("hidBirthDate"+i).value;
							
							var hidSevarthId = document.getElementById("hidSevarthId"+i).value;
							var hidName = document.getElementById("hidName"+i).value;
							
							self.location.href ="ifms.htm?actionFlag=getDcpsPayDetails&EmpId="+EmpId+"&hidDcpsId="+hidDcpsId+"&hidEmpName="+hidEmpName+"&hidBirthDate="+hidBirthDate+"&hidSevarthId="+hidSevarthId+"&hidName="+hidName;
					}
			}
		}
		else
		{
			alert('Please select only one form');
		}
	}
	else
	{
		alert("Please select a form");
	}
}

function changeOfficeDetails()
{
	var noOfEmployeesSelected = 0;
	var flag = 0;
	var totalSelectedEmployees=document.getElementById("totalCount").value;

	for(var i=0;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("chkbxFormVeri"+i).checked == true)
			{
				flag = 1;	
				noOfEmployeesSelected++ ; 
			}
	}

	if(flag == 1)
	{
		if(noOfEmployeesSelected == 1)
		{
			for(i=0;i<totalSelectedEmployees;i++)
			{
				if(document.getElementById("chkbxFormVeri"+i).checked == true)
					{
						var pfdChange = document.getElementById("hidPfdChanged"+i).value;
						var EmpId = document.getElementById("chkbxFormVeri"+i).value;
						if(pfdChange==0)
						{
							alert("The Parent Field Department of this employee is not changed. Please get it changed by the System Admin to make changes in the office details");
						}
						else
						{
							var hidDcpsId = document.getElementById("hidEmpDcpsId"+i).value;
							var hidEmpName = document.getElementById("hidEmpName"+i).value;
							var hidBirthDate = document.getElementById("hidBirthDate"+i).value;
							
							var hidSevarthId = document.getElementById("hidSevarthId"+i).value;
							var hidName = document.getElementById("hidName"+i).value;

							self.location.href ="ifms.htm?actionFlag=changesOfficeDetails&EmpId="+EmpId+"&UserType=DDOAsst&changesType=700041"+"&hidDcpsId="+hidDcpsId+"&hidEmpName="+hidEmpName+"&hidBirthDate="+hidBirthDate+"&hidSevarthId="+hidSevarthId+"&hidName="+hidName;
						}
					}
			}
		}
		else
		{
			alert('Please select only one form');
		}
	}
	else
	{
		alert("Please select a form");
	}
}

function goBackToSearch()
{
	self.location.href = "ifms.htm?viewName=DCPSEmpSearch&elementId=700162" ;
}
function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}

function approveRequest()
{
	if(document.getElementById("btnApprove") != null)
	{
		document.getElementById("btnApprove").disabled = true;
	}
	showProgressbar();
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var Emp_Id=" ";
	var DOJ=" ";
	var PayCmsn=" ";
	var selectedFlag=false;
	var noOfEmployeesSelected = 0;
	
	for(var i=0;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("chkbxFormVeri"+i).checked == true)
			{
				
				Emp_Id = Emp_Id + document.getElementById("chkbxFormVeri"+i).value + "~";
				DOJ = DOJ +  document.getElementById("DOJ"+i).value + "~";
				PayCmsn = PayCmsn +  document.getElementById("PayCmsn"+i).value + "~";	
				flag = 1;	
				noOfEmployeesSelected++ ; 
			}
	}
	
	if(flag == 1)
	{
		if(noOfEmployeesSelected == 1)
		{
			ApproveRequestUsingAjax("ifms.htm?actionFlag=approveReq&Emp_Id="+Emp_Id+"&DOJ="+DOJ+"&PayCmsn="+PayCmsn);
		}
		else
		{
			alert('Please select only one form to approve');
			if(document.getElementById("btnApprove") != null)
			{
				document.getElementById("btnApprove").disabled = false;
			}
			hideProgressbar();
		}
	}
	else
	{
		alert("Please select a form to Approve");
		if(document.getElementById("btnApprove") != null)
		{
			document.getElementById("btnApprove").disabled = false;
		}
		hideProgressbar();
	}

}
function forwardRequest()
{
		var ForwardToPost = document.forms[0].ForwardToPost.value;
		var cmb=document.forms[0].chkbxFormVeri;
		var flag=0;
	    var Emp_Id=" ";

		if(cmb.checked == true)
		{
			flag = 1;
			Emp_Id = cmb.value;
		}
		else
		{	
			alert("Please select only one form for approval");
		}
		
		if(flag ==1)
		{
			
			ForwardRequestUsingAjax("ifms.htm?actionFlag=dcpsFwdReq&Emp_Id="+Emp_Id+"&ForwardToPost="+ForwardToPost);
		}
		else
		{
			alert("Please select a form to forward");
		}
}
function forwardRequestToTreasury(phyFlag)
{
		var ForwardToPost = document.forms[0].ForwardToPost.value;
		var cmb=document.forms[0].chkbxFormVeri;
		var flag=0;
	    var Emp_Id=" ";
	    
		alert(phyFlag);
		if(cmb.checked == true)
		{
			flag = 1;
			Emp_Id = cmb.value;
		}
		else
		{	
			var selectedFlag=false;
			
			if(cmb!=null )
			{
				if(cmb.length != null)
				{
					
					for(i=0;i<cmb.length;i++)
					{
						
						if(cmb[i].checked == true)
						{
							flag = 1;
							Emp_Id += cmb[i].value + "~";
						}
					}
				}
			}
		}
		
		
		if(flag ==1)
		{
			uri = 	"ifms.htm?actionFlag=FwdReqTreasury&Emp_Id="+Emp_Id+"&ForwardToPost="+ForwardToPost+"&phyFlag="+phyFlag;
			
			ForwardRequestUsingAjax(uri);
		}
		else
		{
			alert("Please select a form to forward");
		}
}
function rejectRequest()
{
	var cmb=document.forms[0].chkbxFormVeri;
	var flag=0;
    var Emp_Id=" ";

	if(cmb.checked == true)
	{
		flag = 1;
		Emp_Id = cmb.value;
	}
	else
	{	

		var selectedFlag=false;
		
		if(cmb!=null )
		{
			if(cmb.length != null)
			{
				
				for(i=0;i<cmb.length;i++)
				{
					
					if(cmb[i].checked == true)
					{
						flag = 1;
						Emp_Id += cmb[i].value + "~";
					}
				}
			}
		}
	}
	
	if(flag ==1)
	{
		uri = 	"ifms.htm?actionFlag=rejectRequest&Emp_Id="+Emp_Id;
		
		RejectRequestUsingAjax(uri);
	}
	else
	{
		alert("Please select a form to Reject");
	}
}
function ForwardRequestUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	xmlHttp.onreadystatechange=forwardDataStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}
function forwardDataStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("Registration form is forwarded successfully");
			self.location.reload();
		
		}
	}
}

function RejectRequestUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	xmlHttp.onreadystatechange=rejectDataStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function rejectDataStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("Registration form is sent back to DDO assistant.");
			self.location.reload();
		}
	}
}

function rejectRequestTreasury()
{
	if(document.getElementById("btnReject") != null)
	{
		document.getElementById("btnReject").disabled = true;
	}
	var cmb=document.forms[0].chkbxFormVeri;
	var flag=0;
    var Emp_Id=" ";

	if(cmb.checked == true)
	{
		flag = 1;
		Emp_Id = cmb.value;
	}
	else
	{	
		var selectedFlag=false;
		
		if(cmb!=null )
		{
			if(cmb.length != null)
			{
				
				for(i=0;i<cmb.length;i++)
				{
					
					if(cmb[i].checked == true)
					{
						flag = 1;
						Emp_Id += cmb[i].value + "~";
					}
				}
			}
		}
	}
	
	if(flag ==1)
	{
		uri = 	"ifms.htm?actionFlag=RejReqTreasury&Emp_Id="+Emp_Id+"&rejectionFrom=TO";
		
		RejectRequestUsingAjax(uri);
	}
	else
	{
		alert("Please select a form to Reject");
		if(document.getElementById("btnReject") != null)
		{
			document.getElementById("btnReject").disabled = false;
		}
	}
}
function ApproveRequestUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	xmlHttp.onreadystatechange=ApprovedDataStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}
function ApprovedDataStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var successFlag = XmlHiddenValues[0].childNodes[0].text;
			var dcps_Id= XmlHiddenValues[0].childNodes[1].text;
			var empId= XmlHiddenValues[0].childNodes[2].text;
			var billGroupSuccessFlag = XmlHiddenValues[0].childNodes[3].text;
			
			if(successFlag)
			{
				var answer;
				if(!billGroupSuccessFlag)
					{
						answer = confirm("DCPS ID "+dcps_Id+" Registered Successfully.Employee is not attached to any billgroup.Do you want to print Acknowledgement?");
					}
				else
					{
						answer = confirm("DCPS ID "+dcps_Id+" Registered Successfully. Do you want to print Acknowledgement?");
					}
				if(answer)
				{
					self.location.reload();
					printAcknowledgementReport(empId);
				}
				else
				{
					self.location.reload();
				}
				hideProgressbar();
			}
		}
	}
}
function viewData(EmpId)
{
	var cmb=document.forms[0].chkbxFormVeri;
    var flag=0;
	
    if(cmb.checked == true)
	{
		flag = 1;
		Emp_Id = cmb.value;
	}
	else
	{

	
		if(cmb!=null )
		{
			if(cmb.length != null)
			{
				for(i=0;i<cmb.length;i++)
				{
					if(cmb[i].checked == true)
					{
						flag++;
						Emp_Id += cmb[i].value + "~";
					}
				}
			}
		}
	}
	
	
	if(flag == 0)
	{
		alert("Please select a form to view");
	}
	else if (flag > 1)
	{
		alert("Please select only one form to view");
	}
	else if(flag==1)
	{
		url = "ifms.htm?actionFlag=showUpdteForm&Draft=2&Emp_Id="+EmpId;
		
		window_new_update(url);
	}
}
function viewDraftData(EmpId)
{
	var cmb=document.forms[0].chkbxFormVeri;
    var flag=0;
	
    if(cmb.checked == true)
	{
		flag = 1;
		Emp_Id = cmb.value;
	}
	else
	{
		if(cmb!=null )
		{
			if(cmb.length != null)
			{
				for(i=0;i<cmb.length;i++)
				{
					if(cmb[i].checked == true)
					{
						flag++;
						Emp_Id += cmb[i].value + "~";
					}
				}
			}
		}
	}
	
	
	if(flag == 0)
	{
		alert("Please select a form to view");
	}
	else if (flag > 1)
	{
		alert("Please select only one form to view");
	}
	else if(flag==1)
	{
		url = "ifms.htm?actionFlag=showUpdteForm&Draft=1&Emp_Id="+EmpId;
		
		window_new_update(url);
	}
}
function printAcknowledgementReport(EmpId)
{
	url = "ifms.htm?actionFlag=reportService&reportCode=700007&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
	//alert(url);
	window_new_update(url);
}
function printEmpDetailReportHyperLinked(EmpId)
{
	url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
	window_new_update(url);
}
function printEmpDetailReport(EmpId)
{
	var flag=0;
	var noOfEmployeesSelected = 0;

	if(EmpId==0){

		var totalSelectedEmployees= document.getElementById("totalCount").value;
		//var totalEmployees = document.getElementById("vo").rows.length ;
		var selectedEmployee=0;

		for(var k=0;k<totalSelectedEmployees;k++)
		{
			if(document.getElementById("chkbxFormVeri"+k).checked)
			{  	
				selectedEmployee= document.getElementById("chkbxFormVeri"+k).value;
				flag = 1 ;
				noOfEmployeesSelected++ ; 
			}
		}
		
		if(selectedEmployee == 0)
		{
			alert('Please select one form');
			return false ;
		}

		if(noOfEmployeesSelected > 1)
		{
			alert('Please select only one form to print');
			return false;
		}
		
		EmpId=selectedEmployee;
	}

	if(flag==1)
	{
		url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
		window_new_update(url);
	}
	else
	{
		alert('Please select a form to print');
	}	
}
function submitSearchDetails(criteriavalue)
{
	var Criteria = criteriavalue ;
	var empId = document.getElementById("EmployeeId").value ;
		
	var empName = document.forms[0].EmployeeName.value;
	var empDob = document.forms[0].txtBirthDate.value;
	alert(Criteria);
	alert(empId);
	alert(empName);
	alert(empDob);
	url = 'ifms.htm?actionFlag=srchEmp&empId='+empId+
	'&empName='+empName+'&empDob='+empDob+
	'&Criteria='+Criteria;

	document.forms[0].action=url;	
	document.forms[0].submit();	
}
function goBackToChange()
{
	self.location.href="ifms.htm?actionFlag=loadChangesForm&Type=Office&User=DDOAsst&elementId=700163";
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseList" value="${resValue.CaseList}" />
<c:set var="UserList" value="${resValue.UserList}"/>
<c:set var="Type" value="${resValue.Type}"/>
<c:set var="counter" value="0"></c:set>

<hdiits:form name="DDOFormList" id="DDOFormList" encType="multipart/form-data" validate="true" method="post" >

<div style="display: none">
<fieldset class="tabstyle">
<table width="25%" align="right" cellpadding="4" cellspacing="4">
	
	<tr>
	<td width="15%" align="left"><fmt:message key="CMN.EmployeeId"
				bundle="${dcpsLabels}" />
	</td>
	<td width="20%" align="left"><input type="text"
				id="EmployeeId" style="text-transform: uppercase" size="30"
				name="EmployeeId" />
	</td>
	</tr>
	
	<tr>
	<td width="15%" align="left"><fmt:message key="CMN.EmployeeName"
				bundle="${dcpsLabels}" />
	</td>
	<td width="20%" align="left"><input type="text"
				id="EmployeeName" style="text-transform: uppercase" size="30"
				name="EmployeeName" />
	</td>
	</tr>

	<tr>
	
	<td width="10%" align="left"><fmt:message key="CMN.DOB"
				bundle="${dcpsLabels}" /></td>
	<td width="" align="left"><input type="hidden" name="currDate1"
				id="currDate1" value="${resValue.lDtCurDate}" /> <input type="text"
				name="txtBirthDate" id="txtBirthDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtBirthDate);compareDates(this,currDate1,'Date of Birth should be less than current date.','<');"/> 
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDate",375,570)'
				style="cursor: pointer;" }/></td>
	
	
	</tr>
	
	<tr></tr>
	<tr></tr>
	
	<tr>
						<td width="100%" align="center"><hdiits:button
							name="btnSubmitData" id="btnSubmitData" type="button"
							captionid="BTN.SEARCH" bundle="${dcpsLabels}"
							onclick="submitSearchDetails('${resValue.Criteria}');" /> </td>
	</tr>
	</table>
</fieldset>
</div>
<fieldset class="tabstyle">
	<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; ">
	<c:if test="${(resValue.Criteria == 2) or (resValue.Criteria == 3) or (resValue.Criteria == 4) or (resValue.Criteria == 6) or (resValue.Criteria == 7)}">
	<br/>
	&nbsp;&nbsp;<label style="color:red"><fmt:message key="MSG.FORMRCVDTO" bundle="${dcpsLabels}"></fmt:message></label>
	<br/>
	<br/>
	
	<div class="scrollablediv"  style="width:100%;overflow:auto;height: 300px ;" >
	
    <display:table list="${caseList}"  id="vo" requestURI="" export="" style="width:100%" >	

	<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
		
			<input type="checkbox" name="chkbxFormVeri" id="chkbxFormVeri${counter}" value="${vo[0]}"/>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.EMPSRNO" >
		<c:choose>
				<c:when test="${vo[13] > 8}">		
					<font style="color: red;"><c:out value="${vo_rowNum}"></c:out></font> 
				</c:when>
				<c:otherwise>
					<c:out value="${vo_rowNum}"></c:out> 
				</c:otherwise>
		</c:choose>
		</display:column>
		
		<c:if test="${(resValue.Criteria == 2) or (resValue.Criteria == 3)or (resValue.Criteria == 6) or (resValue.Criteria == 7)}">
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<a href=# onclick="window_new_update('ifms.htm?actionFlag=showUpdteForm&Draft=2&Emp_Id=${vo[0]}');"><c:out value="${vo[1]} ${vo[2]} ${vo[3]}" /></a>
		</display:column>
		</c:if>
		
		<c:if test="${(resValue.Criteria == 4)}">
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" sortable="true"  titleKey="CMN.EMPLOYEENAME" >
			<c:choose>
				<c:when test="${vo[13] > 8}">
						<a href = # onclick="printEmpDetailReportHyperLinked('${vo[0]}');" style="color: red;"><c:out value="${vo[1]}"/></a>
				</c:when>
				<c:otherwise>
						<a href = # onclick="printEmpDetailReportHyperLinked('${vo[0]}');"><c:out value="${vo[1]}"/></a>
				</c:otherwise>
			</c:choose>
		</display:column>
		</c:if>
		
		<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.DDOCODE" headerClass="datatableheader"  sortable="true" >
		<c:choose>
				<c:when test="${vo[13] > 8}">
					<font style="color: red;"><c:out value="${vo[9]}    (${vo[10]}) "/></font>
				</c:when>
				<c:otherwise>
					<c:out value="${vo[9]}    (${vo[10]}) "/>
				</c:otherwise>
		</c:choose>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" sortable="true"  titleKey="CMN.GENDERWOCOLON" >    			
      			<input type="hidden" value="${vo[3]}" />
      			
      		<c:choose>
					<c:when test="${vo[3] == 'M'}">
						<c:set var="genderInFull" value="Male"></c:set>
					</c:when>
					<c:when test="${vo[3] == 'F'}">
						<c:set var="genderInFull" value="Female"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="genderInFull" value="Transgender"></c:set>
					</c:otherwise>
    		</c:choose>
      			
      	<c:choose>
				<c:when test="${vo[13] > 8}">
    				<font style="color: red;"><c:out value="${genderInFull}"></c:out></font>
    			</c:when>
    			<c:otherwise>
    				<c:out value="${genderInFull}"></c:out>
    			</c:otherwise>
    	</c:choose>
    	
		</display:column>
      	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="birthDate"/>
		<c:choose>
				<c:when test="${vo[13] > 8}">	
					<font style="color: red;"><c:out value="${birthDate}"></c:out></font>
				</c:when>
				<c:otherwise>
					<c:out value="${birthDate}"></c:out>
				</c:otherwise>
		</c:choose>
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.EMPOFFICE" >
		<c:choose>
				<c:when test="${vo[13] > 8}">		
					<font style="color: red;"><c:out value="${vo[5]}"></c:out></font>
				</c:when>
				<c:otherwise>
					<c:out value="${vo[5]}"></c:out>
				</c:otherwise>
		</c:choose>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.EMPDESIG" >
		<c:choose>
				<c:when test="${vo[13] > 8}">			
					<font style="color: red;"><c:out value="${vo[6]}"></c:out></font>
				</c:when>
				<c:otherwise>
					<c:out value="${vo[6]}"></c:out>
				</c:otherwise>
		</c:choose> 
				<input type="hidden" name="DOJ" id="DOJ${counter}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${vo[7]}" />"></input>
				<input type="hidden" name="PayCmsn" id="PayCmsn${counter}" value="${vo[8]}"></input>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.ONLINEFORMRCVDDATE" >
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy hh:mm:ss" value="${vo[11]}" var="onlineFormRcvdDate"/>
		<c:choose>
				<c:when test="${vo[13] > 8}">		
					<label id="OnlineFormRcvdDate" style="color:red"><c:out value="${onlineFormRcvdDate}"></c:out></label>
				</c:when>
				<c:otherwise>
					<label id="OnlineFormRcvdDate" ><c:out value="${onlineFormRcvdDate}"></c:out></label>
				</c:otherwise>
		</c:choose>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.PHYSICALFORMRCVDDATE" >
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy hh:mm:ss" value="${vo[12]}" var="phyFormRcvdDate"/>
		<c:choose>
				<c:when test="${vo[13] > 8}">			
					<label id="PhysicalFormRcvdDate" style="color:red"><c:out value="${phyFormRcvdDate}"></c:out></label>
				</c:when>
				<c:otherwise>
					<label id="PhysicalFormRcvdDate"><c:out value="${phyFormRcvdDate}"></c:out></label>
				</c:otherwise>
		</c:choose>
		</display:column>
		
		<c:set var="counter" value="${counter+1}"></c:set>  	
		
	</display:table>
	<input type="hidden" id="totalCount" name="totalCount" value="${counter}" /> 
	</div>
	
	</c:if>
	
	
	
	<c:if test="${(resValue.Criteria == 1)}">
	 <display:table list="${caseList}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri" id="chkbxFormVeri" value="${vo[0]}"/>
		
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.EMPSRNO" >		
				<c:out value="${vo[0]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<a href=# onclick="window_new_update('ifms.htm?actionFlag=showUpdteForm&Draft=1&Emp_Id=${vo[0]}');"><c:out value="${vo[1]} ${vo[2]} ${vo[3]}" /></a>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.GENDER" >		
				<c:out value="${vo[5]}"></c:out> 
		</display:column>
	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="birthDate"/>
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >		
				<c:out value="${birthDate}"></c:out> 
		</display:column>	
		
		
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[6]}" var="joiningDate"/>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.DOJ" >		
				<c:out value="${joiningDate}"></c:out> 
		</display:column>
		
	</display:table>
	
	</c:if>
	
	<c:if test="${(resValue.Criteria == 5)}">
	<c:set var="counterForSearch" value="0" ></c:set>
    <display:table list="${caseList}"  id="vo"   requestURI="" export="" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri${counterForSearch}" id="chkbxFormVeri${counterForSearch}" value="${vo[0]}"/>
		</display:column>
	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<a href = # onclick="openEmpFormHyperLinked('${vo[0]}','${resValue.lStrDcpsId}','${resValue.lStrEmpName}','${resValue.lStrEmpDOB}');"><c:out value="${vo[1]}" /></a>
		</display:column>
		
		<c:choose>
			<c:when test="${vo[2] != null && vo[2] != ''}">
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DCPSID" >		
						<c:out value="${vo[2]}" />
				</display:column>
			</c:when>
			<c:when test="${vo[8] != null && vo[8] != ''}">
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.SEVARTHID" >		
						<c:out value="${vo[8]}" />
				</display:column>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
<%-- 		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.GENDER" >
			<c:choose>
				<c:when test="${vo[3] == 'M'}">
					<c:out value="Male"></c:out>
				</c:when>
				<c:when test="${vo[3] == 'F'}">
					<c:out value="Female"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="Transgender"></c:out>
				</c:otherwise>
    		</c:choose>
		</display:column>
 --%>      	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="birthDate"/>
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >		
				<c:out value="${birthDate}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPOFFICE" >		
				<c:out value="${vo[5]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPDESIG" >		
				<c:out value="${vo[6]}"></c:out>
				
				<input type="hidden" name="hidPfdChanged${counterForSearch}" id="hidPfdChanged${counterForSearch}" value="${vo[7]}"/>
				<input type="hidden" name="hidEmpDcpsId${counterForSearch}" id="hidEmpDcpsId${counterForSearch}" value="${resValue.lStrDcpsId}"/>
				<input type="hidden" name="hidEmpName${counterForSearch}" id="hidEmpName${counterForSearch}" value="${resValue.lStrEmpName}"/>
				<input type="hidden" name="hidBirthDate${counterForSearch}" id="hidBirthDate${counterForSearch}" value="${resValue.lStrEmpDOB}"/>
				<input type="hidden" name="hidSevarthId${counterForSearch}" id="hidSevarthId${counterForSearch}" value="${resValue.lStrSevarthId}"/>
				<input type="hidden" name="hidName${counterForSearch}" id="hidName${counterForSearch}" value="${resValue.lStrName}"/>
				
				<c:set var="counterForSearch" value="${counterForSearch+1}"></c:set> 
		</display:column>

	</display:table>
	
		<input type="hidden" id="totalCount" name="totalCount" value="${counterForSearch}" /> 
	</c:if>
	
	
	</div>
	<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="100%" align="center">
		<c:if test="${resValue.CaseList[0] != null}">
		<c:if test="${resValue.Criteria == 1}">
		
			<hdiits:button name="btnView" id="btnView" type="button"  captionid="BTN.VIEW" bundle="${dcpsLabels}" onclick="viewDraftData('${vo[0]}')"/>
		</c:if>
		
		<c:if test="${(resValue.Criteria == 2) or (resValue.Criteria == 6)}">
		
			<hdiits:button name="btnView" id="btnView" type="button"  captionid="BTN.VIEW" bundle="${dcpsLabels}" onclick="viewData('${vo[0]}')"/>
			<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.FORWARD" bundle="${dcpsLabels}"  onclick="forwardRequest();" />
			<hdiits:button name="btnPrint" id="btnPrint" type="button"  captionid="BTN.PRINT" bundle="${dcpsLabels}" onclick="printEmpDetailReport('${vo[0]}');"/>
		
		</c:if>
		
		<c:if test="${(resValue.Criteria == 3) or (resValue.Criteria == 7) }">
		
			<hdiits:button name="btnView" id="btnView" type="button"  captionid="BTN.VIEW" bundle="${dcpsLabels}" onclick="viewData('${vo[0]}');"/>
			
			<c:if test="${(resValue.Criteria == 3)}">
			<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.FORWARD" bundle="${dcpsLabels}"  onclick="forwardRequestToTreasury(0);" />
			</c:if>
			
			<c:if test="${(resValue.Criteria == 7)}">
			<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.FORWARD" bundle="${dcpsLabels}"  onclick="forwardRequestToTreasury(1);" />
			</c:if>
			
			<hdiits:button name="btnReject" id="btnReject" type="button"  captionid="BTN.REJECT" bundle="${dcpsLabels}" onclick="rejectRequest()"/>
			<hdiits:button name="btnPrint" id="btnPrint" type="button"  captionid="BTN.PRINT" bundle="${dcpsLabels}" onclick="printEmpDetailReport('${vo[0]}');"/>
		
		</c:if>
		
		<c:if test="${resValue.Criteria == 4}">
		
			<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" 	bundle="${dcpsLabels}" onclick="ReturnLoginPage();"/>
			<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.APPROVE" bundle="${dcpsLabels}"  onclick="approveRequest();" />
			<hdiits:button name="btnReject" id="btnReject" type="button"  captionid="BTN.REJECT" bundle="${dcpsLabels}" onclick="rejectRequestTreasury()"/>
			<hdiits:button name="btnPrint" id="btnPrint" type="button"  captionid="BTN.PRINT" bundle="${dcpsLabels}" onclick="printEmpDetailReport(0);"/>
			
		
		</c:if>
		</c:if>
		
		<c:if test="${resValue.Criteria == 5}">
			<c:if test="${resValue.CaseList[0] != null}">
				<hdiits:button name="btnPrint" id="btnPrint" type="button"  captionid="BTN.PRINT" bundle="${dcpsLabels}" onclick="printEmpDetailReport(0);"/>
			</c:if>
			
			<!--<hdiits:button name="btnChangeOfficeDetails" id="btnChangeOfficeDetails" type="button" classcss="bigbutton"  captionid="BTN.CHANGEOFFICEDETAILS" bundle="${dcpsLabels}" onclick="changeOfficeDetails();"/>-->
			<hdiits:button name="btnChangePayDetails" id="btnChangePayDetails" type="button" classcss="bigbutton"  captionid="BTN.CHANGEPAYDETAILS" bundle="${dcpsLabels}" onclick="ChangePayDetails();"/>
			<c:choose>
				<c:when test = "${Type == 'Office' }">
					<hdiits:button name="btnBack1" id="btnBack1" type="button"  captionid="BTN.BACK" bundle="${dcpsLabels}" onclick="goBackToChange();"/></c:when>
				<c:otherwise>
			<hdiits:button name="btnBack1" id="btnBack1" type="button"  captionid="BTN.BACK" bundle="${dcpsLabels}" onclick="goBackToSearch();"/>
			</c:otherwise>
		</c:choose>
		</c:if>
		
		</td>
	</tr>	
</table>
</fieldset>
</hdiits:form>	
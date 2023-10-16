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
		
		ApproveRequestUsingAjax("ifms.htm?actionFlag=approveReq&Emp_Id="+Emp_Id);
	}
	else
	{
		alert("Please select a form to Approve");
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
		
		ForwardRequestUsingAjax(uri);
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
function rejectRequestTreasury()
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
		uri = 	"ifms.htm?actionFlag=RejReqTreasury&Emp_Id="+Emp_Id;
		
		ForwardRequestUsingAjax(uri);
	}
	else
	{
		alert("Please select a form to Reject");
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

			var dcps_Id= XmlHiddenValues[0].childNodes[1].text;
			alert(dcps_Id);

			var empId= XmlHiddenValues[0].childNodes[2].text;
			var answer = confirm("DCPS ID "+dcps_Id+" Registered Successfully. Do you want to print Acknowledgement?")		
			if(answer)
			{
				printAcknowledgementReport(empId)
			}
			else
			{
				self.location.reload();
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
	
	url = "ifms.htm?actionFlag=reportService&reportCode=700007&action=generateReport&empid="+EmpId;
	window_new_update(url);
}
function printEmpDetailReport(EmpId)
{
	
	url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+EmpId;
	window_new_update(url);
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
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseList" value="${resValue.CaseList}" />
<c:set var="UserList" value="${resValue.UserList}"/>

<hdiits:form name="DDOFormList" id="DDOFormList" encType="multipart/form-data" validate="true" method="post" >


<fieldset class="tabstyle">
	<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; height:400px;  ">
	
	<c:if test="${(resValue.Criteria == 2) or (resValue.Criteria == 3) or (resValue.Criteria == 4) or (resValue.Criteria == 6) or (resValue.Criteria == 7)}">
	
    <display:table list="${caseList}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri" id="chkbxFormVeri" value="${vo[0]}"/>
		
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.EMPSRNO" >		
				<c:out value="${vo[0]}"></c:out> 
		</display:column>
		
		<c:if test="${(resValue.Criteria == 2) or (resValue.Criteria == 3)or (resValue.Criteria == 6) or (resValue.Criteria == 7)}">
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<a href=# onclick="window_new_update('ifms.htm?actionFlag=showUpdteForm&Draft=2&Emp_Id=${vo[0]}');"><c:out value="${vo[1]} ${vo[2]} ${vo[3]}" /></a>
		</display:column>
		</c:if>
		
		<c:if test="${(resValue.Criteria == 4)}">
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<a href = # onclick="printEmpDetailReport('${vo[0]}');"><c:out value="${vo[1]}" /></a>
		</display:column>
		</c:if>
		
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.GENDER" >    			
      			<input type="hidden" value="${vo[2]}" />
    			<c:out value="${vo[2]}"></c:out> 
		</display:column>
      	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}" var="birthDate"/>
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >		
				<c:out value="${birthDate}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPOFFICE" >		
				<c:out value="${vo[4]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPDESIG" >		
				<c:out value="${vo[5]}"></c:out> 
		</display:column>
			
			
	</display:table>
	
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
	
    <display:table list="${caseList}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri" id="chkbxFormVeri" value="${vo[0]}"/>
		
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.EMPSRNO" >		
				<c:out value="${vo[0]}"></c:out> 
		</display:column>
		
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<c:out value="${vo[1]} ${vo[2]} ${vo[3]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DCPSID" >		
				<c:out value="${vo[4]}" />
		</display:column>
				
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.GENDER" >    			
      			<input type="hidden" value="${vo[5]}" />
    			<c:out value="${vo[5]}"></c:out> 
		</display:column>
      	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[6]}" var="birthDate"/>
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >		
				<c:out value="${birthDate}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPOFFICE" >		
				<c:out value="${vo[7]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPDESIG" >		
				<c:out value="${vo[8]}"></c:out> 
		</display:column>
			
			
	</display:table>
	</c:if>
	
	
	
	</div>
	<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="100%" align="center">
		
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
		
			
			<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.APPROVE" bundle="${dcpsLabels}"  onclick="approveRequest();" />
			<hdiits:button name="btnReject" id="btnReject" type="button"  captionid="BTN.REJECT" bundle="${dcpsLabels}" onclick="rejectRequestTreasury()"/>
			
		</c:if>
		
		<c:if test="${resValue.Criteria == 5}">
		
			<hdiits:button name="btnPrint" id="btnPrint" type="button"  captionid="BTN.PRINT" bundle="${dcpsLabels}" />
		
		</c:if>
		
		</td>
	</tr>	
</table>
</fieldset>
</hdiits:form>	
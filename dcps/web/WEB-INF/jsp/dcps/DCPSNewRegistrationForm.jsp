<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="parentDeptByDefault" value="${resValue.listParentDept[0]}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<c:set var="empList" value="${resValue.empList}"></c:set>

<script>
var defaultParentDpt = true;
var saveOrUpdateFlag =0;
var emp_Id="";
var nomineeSavedOrNot;

function rejectRequest(EmpId)
{
	var remarks = document.getElementById("txtApproverRemarks").value;
	if(chkEmpty(document.getElementById("txtApproverRemarks"),'Approver Remarks') == false)
	{
		return false;
	}
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	   {
			
	      return;
	   }  
		var url = "ifms.htm?actionFlag=rejectRequest&Emp_Id="+EmpId+"&remarks="+remarks;
	   //alert(url);
	   xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					//alert(XMLDoc)
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].text;
						//alert(test_Id);						
						if (test_Id) {
							
								alert('Form is successfully sent back to the assistant.');
								
								self.location.href="ifms.htm?actionFlag=loadDCPSForm&User=DDO";
						}
					}
				}
		};
	   xmlHttp.open("POST",url,false);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(url);

	
}


function saveAfterValidation()
{

//	if(ValidateData())
//		{
	SaveDataUsingAjax();
//		}
}

function changeSaveOrUpdateBtn()
{
		if(emp_Id=="")
		{
		document.getElementById("savebtn").style.display = "block" ;
		document.getElementById("updatebtn").style.display = "none" ;
		}
		else
		{
		document.getElementById("savebtn").style.display = "none" ;
		document.getElementById("updatebtn").style.display = "block" ;
		}
}

function popUpDcpsEmpData(dcpsEmpId,ddoFlag)
{
	var empId = dcpsEmpId ;
	
	if(ddoFlag=="N")
	{
		var User = "DDO";
	}
	else
	{
		var User = "Asst";
	}
	url="ifms.htm?actionFlag=popUpEmpDtls&empId="+empId+"&User="+User;
	self.location.href = url;
	
}

function SaveDataUsingAjax() {
	
	var txtParentFieldDept;
	
	xmlHttp=GetXmlHttpObject();

	if(defaultParentDpt==true)
	{
		txtParentFieldDept = document.getElementById("ParentFieldDept").innerHTML;
	}
	else
	{
	 	txtParentFieldDept = document.getElementById("listParentDept").value;
	}
	var saveOrUpdateFlag = 1;

	   if (xmlHttp==null)
	   {

	      return;
	   }  
	   var uri = 'ifms.htm?actionFlag=saveDCPSForm';
	   var url = runForm(0); 
	   url = uri + url;   
	   url = url + "&saveOrUpdateFlag="+saveOrUpdateFlag + "&txtParentFieldDept="+txtParentFieldDept;	
	   
	   xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].text;
						emp_Id=XmlHiddenValues[0].childNodes[1].text;
						
						if (test_Id) {
							nomineeSavedOrNot =  submitNomineeDtls(emp_Id);
	
								if(nomineeSavedOrNot==1)
								{
								alert('All the details saved successfully');
								}
								if(nomineeSavedOrNot==2 && serialNo==1)
								{
								alert('All the details saved successfully');
								}
								if(nomineeSavedOrNot==2 && serialNo>1)
								{
								alert('All the details except nominee details saved successfully');
								}
								var url = "ifms.htm?actionFlag=loadDCPSForm&User=Asst";
								self.location.href= url;
						}
					}
				}
		};
	   xmlHttp.open("POST",uri,false);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(url);
}

function UpdateDataUsingAjax() {

	
	EmpId = emp_Id;
	
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	var saveOrUpdateFlag = 2;
	var txtParentFieldDept;
	if(defaultParentDpt==true)
	{
		txtParentFieldDept = document.getElementById("ParentFieldDept").innerHTML;
	}
	else
	{
	 	txtParentFieldDept = document.getElementById("listParentDept").value;
	}
	   var uri = 'ifms.htm?actionFlag=updateDCPSForm';
	   var url = runForm(0); 
	   url = uri + url;   
	   url = url + "&saveOrUpdateFlag="+saveOrUpdateFlag + "&txtParentFieldDept="+txtParentFieldDept + "&empId=" + EmpId ;	
	
	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {

			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var test_Id = XmlHiddenValues[0].childNodes[0].text;
					emp_Id=XmlHiddenValues[0].childNodes[1].text;
				
					if (test_Id) {
						nomineeSavedOrNot =  submitNomineeDtls(emp_Id);
						if(nomineeSavedOrNot==1)
						{
						alert('All the details updated successfully');
						}
						if(nomineeSavedOrNot==2 && serialNo>1)
						{
						alert('All the details except Nominee details updated successfully');
						}
						if(nomineeSavedOrNot==2 && serialNo==1)
						{
						alert('All the details updated successfully');
						}

						self.location.href="ifms.htm?actionFlag=loadDCPSForm&User=Asst";
					}
			}
		}
	};
	
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
}

function updateDataUsingAJAXForUpdateTotally(empid)
{
	var EmpId=empid;
	var saveOrUpdateFlag = 2;
	SaveOrUpdateNominee = 2;
	
	
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	
	var txtParentFieldDept;
	if(defaultParentDpt==true)
	{
		txtParentFieldDept = document.getElementById("ParentFieldDept").innerHTML;
	}
	else
	{
	 	txtParentFieldDept = document.getElementById("listParentDept").value;
	}
	   var uri = 'ifms.htm?actionFlag=updateDCPSForm';
	   var url = runForm(0); 
	   url = uri + url;   
	   url = url + "&saveOrUpdateFlag="+saveOrUpdateFlag + "&txtParentFieldDept="+txtParentFieldDept + "&empId=" + EmpId ;	
	
	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {

			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var test_Id = XmlHiddenValues[0].childNodes[0].text;
					emp_Id=XmlHiddenValues[0].childNodes[1].text;
				
					if (test_Id) {
						nomineeSavedOrNot =  submitNomineeDtls(EmpId);
						if(nomineeSavedOrNot==1)
						{
						alert('All the details updated successfully');
						self.location.href='ifms.htm?actionFlag=loadDCPSForm';
						}
						if(nomineeSavedOrNot==2 && serialNo>1)
						{
						alert('All the details except Nominee details updated successfully');
						}
						if(nomineeSavedOrNot==2 && serialNo==1)
						{
						alert('All the details updated successfully');
						self.location.href='ifms.htm?actionFlag=loadDCPSForm';
						}
						self.location.href="ifms.htm?actionFlag=loadDCPSForm&User=Asst";
					}
			}
		}
	};
	
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
	
}

function ForwardRequest()
{
	var ForwardToPost =  document.getElementById("ForwardToPost").value;
	ForwardRequestUsingAjax("ifms.htm?actionFlag=dcpsFwdReq&Emp_Id="+emp_Id+"&ForwardToPost="+ForwardToPost);
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
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var test_Id = XmlHiddenValues[0].childNodes[0].text;
			if(test_Id)
			{
			alert("Registration form is forwarded successfully");
			}
			self.location.reload();
		}
	}
}

function ForwardRequestForUpdateTotally(empId)
{
	var emp_Id = empId ;
	var ForwardToPost =  document.getElementById("ForwardToPost").value;
	ForwardRequestUsingAjaxForUpdateTotally("ifms.htm?actionFlag=dcpsFwdReq&Emp_Id="+emp_Id+"&ForwardToPost="+ForwardToPost);
}

function ForwardRequestUsingAjaxForUpdateTotally(url)
{
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
	uri=url;
	xmlHttp.onreadystatechange=forwardDataStateChangedforUpdateTotally;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function forwardDataStateChangedforUpdateTotally() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var test_Id = XmlHiddenValues[0].childNodes[0].text;
			if(test_Id)
			{
			alert("Registration form is forwarded successfully");
			}
			
			var url = "ifms.htm?actionFlag=loadDCPSForm&User=Asst";
			//alert(url);
			self.location.href=url;
		}
	}
}

function ForwardRequestDDO(empId)
{
	var emp_Id = empId ;
	var ForwardToPost =  document.getElementById("ForwardToPost").value;
	ForwardRequestUsingAjaxDDO("ifms.htm?actionFlag=FwdReqTreasury&Emp_Id="+emp_Id+"&ForwardToPost="+ForwardToPost);
}

function ForwardRequestUsingAjaxDDO(url)
{
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
	uri=url;
	xmlHttp.onreadystatechange=forwardDataStateChangedDDO;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function forwardDataStateChangedDDO() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var test_Id = XmlHiddenValues[0].childNodes[0].text;
			if(test_Id)
			{
			alert("Form is forwarded successfully to Treasury");
			}
			
			var url = "ifms.htm?actionFlag=loadDCPSForm&User=DDO";
			//alert(url);
			self.location.href=url;
		}
	}
}

function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "NomineeDetails", urlstyle);
}

function hideUnhideParentList()
{
	if(document.DCPSForm.cbChangeParentDept.checked==true)
	{
		document.getElementById("ParentFieldDept").style.display="none" ;
		document.getElementById("listParentDept").style.display = "";
		defaultParentDpt=false;
	}	
	if(document.DCPSForm.cbChangeParentDept.checked==false)
	{
		document.getElementById("ParentFieldDept").style.display="" ;
		document.getElementById("listParentDept").style.display = "none";
	}
}

function populateGroup()
{
	//alert('inside populateGroup');
	var selectOption= document.getElementById('cmbCadre').value;
	var xmlHttp=null;
	try
	  {
	  xmlHttp= new XMLHttpRequest();
	  }
	catch (e)
	  {
	  try
	    {
	    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    }
	  catch (e)
	    {
	    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	  }
	
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	var uri = "ifms.htm?actionFlag=popGroup";
	url=uri+"&cmbCadre="+selectOption;
	
	xmlHttp.onreadystatechange  = function()
    { 
	   	if(xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status == 200) 
				{
					 XMLDoc = xmlHttp.responseXML.documentElement;
					 
					 if (XMLDoc != null) {
						 
						var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
						var group=XMLEntries[0].childNodes[0].text;
						//alert('Group-->'+group) ;
						document.getElementById('txtGroup').value=group;
				 		}
				}
		}
    };
    xmlHttp.open("POST",url,true);
	xmlHttp.send(url);
}

function getOfficeDetails()
{
	
	var officeId= document.getElementById('cmbCurrentOffice').value;
	var xmlHttp=null;
	try
	  {
	  xmlHttp= new XMLHttpRequest();
	  }
	catch (e)
	  {
	  try
	    {
	    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    }
	  catch (e)
	    {
	    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	  }
	
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	var uri = "ifms.htm?actionFlag=popOfficeDet";
	url=uri+"&officeId="+officeId;
	
	xmlHttp.onreadystatechange  = function()
    { 
	   	if(xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status == 200) 
				{
					 XMLDoc = xmlHttp.responseXML.documentElement;
					 
					 if (XMLDoc != null) {
						 
						var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
						
						var address1=XMLEntries[0].childNodes[0].text;
						

						var contact1=XMLEntries[0].childNodes[1].text;
						var contact2=XMLEntries[0].childNodes[2].text;
						var contact3=XMLEntries[0].childNodes[3].text;
						document.getElementById('txtOfficeAddress').value=address1;
						document.getElementById('txtOfficeContactNo1').value=contact1;
						document.getElementById('txtOfficeMobile').value=contact2;
						document.getElementById('txtOfficeContactNo2').value=contact3;

						document.getElementById('txtOfficeAddress').readOnly = true ;
						document.getElementById('txtOfficeContactNo1').readOnly = true ;
						document.getElementById('txtOfficeMobile').readOnly = true ;
						document.getElementById('txtOfficeContactNo2').readOnly = true ;
						
				 		}
				}
		}
    };
    xmlHttp.open("POST",url,true);

	xmlHttp.send(url);
	
}



function updateAfterValidation(){
//	if(ValidateData())
//    	{
			UpdateDataUsingAjax() ;
//    	}
}

function forwardRequestAfterValidation(){
//	if(ValidateData())
//	{
	if(emp_Id != "")
	{	
		updateAfterValidation();  
	}
	if(emp_Id == "")
	{	
			saveAfterValidation();
	}
	if(!(nomineeSavedOrNot==2 && serialNo>1))
	{
		ForwardRequest() ;
	}
	else
	{
		alert('Form is saved but not forwarded since nominee shares are wrong.Please enter correct nominee shares to forward the form.');
	}
//	}
}
function forwardRequestAfterValidationforUpdateTotally(empId)
{
	updateAfterValidationForUpdateTotally(empId);
	if(!(nomineeSavedOrNot==2 && serialNo>1))
	{
		ForwardRequestForUpdateTotally(empId) ;
	}
}
function updateAfterValidationForUpdateTotally(empid)
{
	//if(ValidateData())
	//{
		updateDataUsingAJAXForUpdateTotally(empid);
	//}
}
function backForNew()
{
	self.location.href = 'ifms.htm?actionFlag=validateLogin';
}

function backForUpdate()
{
	self.close();
}

function hidImg()
{
	newWindow1.close()
}
function showImg(lStr)
{
	document.getElementById("attachmentName1").value = lStr.name;
	var height = 600;
	var width = 600;
	var urlstring = "ifms.htm?viewName=DcpsPhoto";
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow1 = window.open(urlstring,"DcpsPhoto","'titlebar=no,directories=no,height=355,location=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=600,height=600,top=0,left=0","false");
	//globArray[9] = newWindow1; 
}

function popUpIFSCCode()
{

		xmlHttp = GetXmlHttpObject();
		
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		
		var cmbBranchName = document.getElementById("cmbBranchName").value;
		var uri="ifms.htm?actionFlag=displayIFSCCodeForBranch&cmbBranchName="+cmbBranchName;
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
				
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var IFSCCode = XmlHiddenValues[0].childNodes[0].text;
					if(IFSCCode != 'null')
						{
						document.getElementById("txtIFSCCode").value = IFSCCode ;
						}
				}
			}
		};

		//alert('uri'+uri);
		
		xmlHttp.open("POST", uri, false);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(uri);
}

</script>
<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>

</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page" value="style='display:none'"></c:set>

</c:if>

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data"
	validate="true" method="post" >
	
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.AllEmployeeDetails" bundle="${dcpsLables}"></fmt:message></b> </legend>
	
	    <display:table list="${empList}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="5">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[2]}" var="birthDate"/>
		
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<a href=# onclick="popUpDcpsEmpData('${vo[0]}','${resValue.EditForm}');"><c:out value="${vo[1]}" /></a>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >		
				<c:out value="${birthDate}"></c:out> 
		</display:column>	
		<c:choose>
		<c:when test="${resValue.EditForm != 'N'}">	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.StatusFlag" >		
				
				<c:choose>
															<c:when test="${vo[3] == 0}">
															<c:out value="Draft"></c:out>
															</c:when> 
															<c:otherwise>
															<c:out value="Rejected"></c:out>
															</c:otherwise>
														</c:choose>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.REMARKS" >    			
      		<c:out value="${vo[4]}"></c:out> 
		</display:column>
		</c:when>
		</c:choose>
		<c:choose>
		<c:when test="${resValue.EditForm == 'N'}">
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.GENDER" >    			
      		<c:out value="${vo[6]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DESIGNATION" >    			
      		<c:out value="${vo[5]}"></c:out> 
		</display:column>
		</c:when>
		</c:choose>
		</display:table>
	   
	</fieldset>
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption caption="Personal Details" captionid="Personal Details"/></a></li>
			<li><a href="#" rel="tcontent2"><hdiits:caption caption="Office Details" captionid="Office Details"/></a></li>
			<li><a href="#" rel="tcontent3"><hdiits:caption caption="Other Details" captionid="Other Details"/></a></li>
			<li><a href="#" rel="tcontent4"><hdiits:caption caption="Nominee Details" captionid="Nominee Details"/></a></li>
			<li><a href="#" rel="tcontent5"><hdiits:caption caption="Photo & Signature" captionid="Photo And Signature"/></a></li>
	</ul>
	</div>
	
	<div class="tabcontentstyle">

	<div id="tcontent1" class="tabcontent" tabno="0">
	
	
	
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.EMPDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
	<input type="hidden" id="txtDdoCode" name="txtDdoCode" value="${resValue.DDOCODE}"/>	
	<input type="hidden" id="txtUserType" name="txtUserType" value="${resValue.UserType }"/>
	<table width="100%" align="center" cellpadding="4" cellspacing="4" height="70%">
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.NAME"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtName" style="text-transform: uppercase" size="30"
				name="txtName" onblur="isName(txtName,'This field should not contain any special characters or digits.')" value="${EMPVO.name}" ${varDisabled} /></td>
		
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.GENDER"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<c:set var="Male" value="Male"></c:set>
			<c:set var="Female" value="Female"></c:set>
			
			<c:choose>
					<c:when test="${EMPVO!=null}">
							<c:choose>
							
											<c:when test="${EMPVO.gender == Male}">
											
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="Male" checked="checked" ${varDisabled}  />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="Female" ${varDisabled} />
											</td>
											</c:when>
											
											
											<c:otherwise>
											
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="Male" ${varDisabled} />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender" name="radioGender" value="Female" checked="checked" ${varDisabled} />
											</td>
											</c:otherwise>
							</c:choose>
						
					</c:when>
				
					<c:otherwise>
					
					<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="Male" checked="checked" ${varDisabled} />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="Female" ${varDisabled} />
					</td>
					</c:otherwise>
			
			</c:choose>
			
		</tr>	
		
		<tr>
			<td width="10%" align="left"><fmt:message key="CMN.DOB"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EMPVO.dob}" var="empBirthDate"/>
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EMPVO.doj}" var="empJoiningDate"/>
				
			<td width="" align="left"><input type="hidden" name="currDate1"
				id="currDate1" value="${resValue.lDtCurDate}" /> <input type="text"
				name="txtBirthDate" id="txtBirthDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtBirthDate);compareDates(this,currDate1,'Date of Birth should be less than current date.','<');" value="${empBirthDate}" ${varDisabled}  /> 
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDate",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /></td>
		
		
			<td width="15%" align="left"><fmt:message key="CMN.DOJ"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="hidden" name="joinDate"
				id="joinDate" value="${resValue.lDtJoiDtLimit}" /><input type="text"
				name="txtJoiningDate" id="txtJoiningDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtJoiningDate);
				compareDates(txtJoiningDate,this,'Date Of Joining should be greater than DOB!','<');
				compareDates(this,currDate1,'Date of Joining should be less than current date.','<');
				compareDates(joinDate,this,'Date of Joining should be greater than 01/01/2005.','<');"
				value="${empJoiningDate}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtJoiningDate", 375, 570)'
				style="cursor: pointer;" ${varImageDisabled}/></td>
		</tr>
		
		
		
		<tr>
		<td width="15%" align="left"><fmt:message key="CMN.DESIGNATION"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbDesignation"
				id="cmbDesignation" style="width: 66%" onChange="" ${varDisabled} >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="designationVar" items="${resValue.lLstDesignation}">
						<c:choose>
							<c:when test="${EMPVO!=null}">
										<c:choose>
											<c:when test="${EMPVO.designation == designationVar.id}">
											<option value="${designationVar.id}" selected="selected"><c:out value="${designationVar.desc}"></c:out></option>
											</c:when>
											<c:otherwise>
											<option value="${designationVar.id}"><c:out value="${designationVar.desc}"></c:out></option>
											</c:otherwise>
										</c:choose>
							</c:when>	
							<c:otherwise>
											<option value="${designationVar.id}"><c:out value="${designationVar.desc}"></c:out></option>
							</c:otherwise>
						</c:choose>	
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
		
		<td width="15%" align="left"><fmt:message key="CMN.PAYCOMMISSION"
				bundle="${dcpsLables}"></fmt:message></td>
		<td>
				<select name="cmbPayCommission"
				id="cmbPayCommission" style="width: 66%;"  ${varDisabled} >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="PayCommission" items="${resValue.listPayCommission}">
							       <c:choose>
										<c:when test="${EMPVO!=null}">
													<c:choose>
														<c:when test="${EMPVO.payCommission == PayCommission.lookupId}">
														<option value="${PayCommission.lookupId}" selected="selected"><c:out
						value="${PayCommission.lookupDesc}"></c:out></option>
														</c:when>
														<c:otherwise>
														<option value="${PayCommission.lookupId}"><c:out
						value="${PayCommission.lookupDesc}"></c:out></option>
														</c:otherwise>
													</c:choose>
										</c:when>	
										<c:otherwise>
										<option value="${PayCommission.lookupId}"><c:out
						value="${PayCommission.lookupDesc}"></c:out></option>
										</c:otherwise>
									</c:choose>	
					
				</c:forEach>
				</select>
		</td>	
			<td width="15%" align="left"><fmt:message key="CMN.PAYSCALE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtPayScale"
				size="30" name="txtPayScale" value="${EMPVO.payScale}" ${varDisabled}  /></td>
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message
				key="CMN.RESIDENTIALADDRESS" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtResidentialAddress" size="30" name="txtResidentialAddress"
				value="${EMPVO.address1}" ${varDisabled} /></td>
		</tr>
		
		<tr>
		<td width="15%" align="left"><fmt:message key="CMN.ADDRESS2"
				bundle="${dcpsLables}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" id="txtAddress2"
				size="30" name="txtAddress2" value="${EMPVO.address2}" ${varDisabled} /></td>
		</tr>
		
		<tr>
		
		<td width="15%" align="left"><fmt:message key="CMN.CONTACTTELNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtContactTelNo" size="30" name="txtContactTelNo" value="${EMPVO.cntctNo}" 
				onkeypress="digitFormat(this);"onblur="checkLength(txtContactTelNo,'Telephone number');" ${varDisabled} /></td>
				<td width="15%" align="left"><fmt:message key="CMN.CELLNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtCellNo"
				size="30" name="txtCellNo" onblur="checkLength(txtCellNo,'Cell Number');"
				onkeypress="digitFormat(this);" 
				value="${EMPVO.cellNo}" ${varDisabled} /></td>
		
		</tr>
		
	</table>
	</fieldset>
	</div>
	
	<div id="tcontent2" class="tabcontent" tabno="1" >
	
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CADREINPARENTDEPT" bundle="${dcpsLables}"></fmt:message></b> </legend>
	<table width="100%" align="center" cellpadding="4" cellspacing="4">
		<tr>
			<td width="15%" align="left"><fmt:message
				key="CMN.PARENTFIELDDEPT" bundle="${dcpsLables}"></fmt:message></td>
	
			<td>
			
			<c:if test="${EMPVO==null}">
			<label id="ParentFieldDept" ><c:out value="${parentDeptByDefault.lookupDesc}"></c:out></label>
			</c:if>
			
			<c:if test="${EMPVO!=null}">
			<label id="ParentFieldDept" ><c:out value="${EMPVO.parentDept}"></c:out></label>
			</c:if>
			
				<select name="listParentDept"
				id="listParentDept" style="width: 60%;display:none"  >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="ParentDept" items="${resValue.listParentDept}">
					<option value="${ParentDept.lookupDesc}"><c:out
						value="${ParentDept.lookupDesc}"></c:out></option>
				</c:forEach>
				</select>
			</td>	
				
			
		
			<td width="10%" align="left"><fmt:message
				key="CMN.CHANGEPARENTDEPT" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="checkbox"
				name="cbChangeParentDept" id="cbChangeParentDept" value="ChangeParentDept" onclick="hideUnhideParentList();" ${varDisabled} >
			</td>
		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.CADRE"
				bundle="${dcpsLables}"></fmt:message></td>
		
			<td width="20%" align="left"><select name="cmbCadre"
				id="cmbCadre" style="width: 60%" onChange="populateGroup();" ${varDisabled} >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="cadreName" items="${resValue.CADRELIST}">
				<c:choose>
				<c:when test="${EMPVO.cadre == cadreName}">
				<option value="${cadreName}" selected="selected"><c:out 
						value="${cadreName}"></c:out></option>
				</c:when>
				<c:otherwise>
				<option value="${cadreName}" ><c:out 
						value="${cadreName}"></c:out></option>
				</c:otherwise>
				</c:choose>
				</c:forEach>
				
			</select></td>
			<td width="15%" align="left"><fmt:message key="CMN.GROUP"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtGroup"
				size="30" name="txtGroup" value="${EMPVO.group}" readonly="readonly"/></td>
		</tr>
		<tr>
		<td width="15%" align="left"><fmt:message
				key="CMN.CURRENTOFFICE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
			<select name="cmbCurrentOffice"
				id="cmbCurrentOffice" style="width: 60%" onChange="getOfficeDetails();" ${varDisabled} >
				<c:forEach var="officeName" items="${resValue.OFFICELIST}" >
														<c:choose>
															<c:when test="${EMPVO.currOff == officeName.id}">
															<option value="${officeName.id}" selected="selected"><c:out 
																	value="${officeName.desc}"></c:out></option>
															</c:when>
															<c:otherwise>
															<option value="${officeName.id}" ><c:out 
																	value="${officeName.desc}"></c:out></option>
															</c:otherwise>
														</c:choose>
				</c:forEach>
			</select>
			</td>
		</tr>
		
		<tr>
		<td width="15%" align="left"><fmt:message
				key="CMN.OFFICEADDRESS" bundle="${dcpsLables}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" id="txtOfficeAddress"
				size="30" name="txtOfficeAddress" value="${EMPVO.offAddressDtls}" ${varDisabled} /></td>
		</tr>
		
		<tr>
		<td width="15%" align="left"><fmt:message
				key="DCPS.TELNO(1)" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeContactNo1" size="30" name="txtOfficeContactNo1" 
				onkeypress="digitFormat(this);" onblur="checkLength(txtOfficeContactNo1,'Office contact number');"
				value="${EMPVO.offCntctDtls1}" ${varDisabled} />
			</td>
			
		<td width="15%" align="left"><fmt:message
				key="DCPS.TELNO(2)MOBILE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeMobile" size="30" name="txtOfficeMobile" 
				onkeypress="digitFormat(this);" onblur="checkLength(txtOfficeMobile,'Office contact number');"
				value="${EMPVO.offCellNo}" ${varDisabled} />
			</td>
			
		
		</tr>
		
		<tr>
		<td width="15%" align="left"><fmt:message
				key="DCPS.FAX" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeContactNo2" size="30" name="txtOfficeContactNo2" 
				onkeypress="digitFormat(this);" onblur="checkLength(txtOfficeContactNo2,'Office contact number');"
				value="${EMPVO.offCntctDtls2}" ${varDisabled}  />
			</td>
		</tr>
		
		<tr>
		<td width="15%" align="left"><fmt:message
				key="CMN.REMARKS" bundle="${dcpsLables}"></fmt:message></td>
		<td width="40%" align="left"><input type="text" id="txtRemarks"
				size="30" name="txtRemarks" value="${EMPVO.remarks}" ${varDisabled} /></td>
		</tr>
	</table>
	</fieldset>
	</div>
	
	<div id="tcontent3" class="tabcontent" tabno="2" >
	
	<fieldset class="tabstyle">
	<table width="100%" align="center" cellpadding="4" cellspacing="4">
		<tr>
			<td width="15%" align="left"><fmt:message
				key="CMN.UIDNO" bundle="${dcpsLables}"></fmt:message></td>
			<td width="40%" align="left"><input type="text" id="txtUIDNo"
				size="30" name="txtUIDNo" value="${EMPVO.UIDNo}" ${varDisabled} /></td>
		
			<td width="15%" align="left"><fmt:message
				key="CMN.PANNO" bundle="${dcpsLables}"></fmt:message></td>
			<td width="40%" align="left"><input type="text" id="txtPANNo"
				size="30" name="txtPANNo" value="${EMPVO.PANNo}" ${varDisabled} /></td>	
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message
				key="CMN.BANKNAME" bundle="${dcpsLables}"></fmt:message></td>
			<td width="40%" align="left">
				<select name="cmbBankName" id="cmbBankName" style="width:240px"  onChange="" ${varDisabled} >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
					<c:forEach var="bankName" items="${resValue.BANKNAMES}" >
														<c:choose>
															<c:when test="${EMPVO.bankName == bankName.id}">
															<option value="${bankName.id}" selected="selected"><c:out 
																	value="${bankName.desc}"></c:out></option>
															</c:when>
															<c:otherwise>
															<option value="${bankName.id}" ><c:out 
																	value="${bankName.desc}"></c:out></option>
															</c:otherwise>
														</c:choose>
					</c:forEach>
			</select>
			</td>	
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message
				key="CMN.BRANCHNAME" bundle="${dcpsLables}"></fmt:message></td>
			<td width="40%" align="left">
				<select name="cmbBranchName" id="cmbBranchName" style="width:240px"  onChange="" ${varDisabled} onChange="popUpIFSCCode();" onblur="popUpIFSCCode();" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
				<c:if test="${EMPVO!=null}">
						<c:forEach var="branchName" items="${resValue.BRANCHNAMES}" >
														<c:choose>
															<c:when test="${EMPVO.branchName == branchName.id}">
															<option value="${branchName.id}" selected="selected"><c:out 
																	value="${branchName.desc}"></c:out></option>
															</c:when>
															<c:otherwise>
															<option value="${branchName.id}" ><c:out 
																	value="${branchName.desc}"></c:out></option>
															</c:otherwise>
														</c:choose>
					</c:forEach>
				</c:if>
			</select>
			</td>	
		</tr>
		
		<tr>	
		<td width="15%" align="left"><fmt:message key="CMN.IFSCCODE" bundle="${dcpsLables}"></fmt:message></td>
		<td align="left">
			<input type="text" id="txtIFSCCode" style="text-transform: uppercase" size="30"  name="txtIFSCCode" value="" />
		</td>
		</tr>
		
		
		<tr>
			<td width="15%" align="left"><fmt:message
				key="CMN.BANKACNO" bundle="${dcpsLables}"></fmt:message></td>
			<td width="40%" align="left"><input type="text" id="txtbankAccountNo"
				size="30" name="txtbankAccountNo" value="${EMPVO.bankAccountNo}" ${varDisabled} /></td>
		</tr>
	</table>
	</fieldset>
	
	</div>
	
	<div id="tcontent4" class="tabcontent" tabno="3" >
	<jsp:include
		page="/WEB-INF/jsp/dcps/dcpsnomineedtls.jsp" />
	</div>
	
	<div id="tcontent5" class="tabcontent" tabno="4" >

	<jsp:include page="/WEB-INF/jsp/dcps/pensionerPhotoSignAttachment.jsp" />
	
	</div>
	</div>
	<br/>
	<table  align="left" height="10%" cellpadding="0"
		cellspacing="0" border="0">
		<tr></tr>
		<tr></tr>
		<c:choose>
		<c:when test="${resValue.EditForm == 'N'}">
		<tr>
		<td>
			Approver Remarks
		</td>
		
			<td width="80%">
				<input  type="text" id="txtApproverRemarks" name="txtApproverRemarks" size="100" value="" />
			</td>
			
		</tr>
		</c:when>
			</c:choose>
		</table>
		<br/><br/>
		<table  align="right" height="10%" cellpadding="4"
		cellspacing="4" border="0">
		<c:choose>
				<c:when test="${resValue.EditForm != 'N'}">
		
		<c:choose>
				<c:when test="${EMPVO==null}">
							<tr>
								<td  id="savebtn" align="center" style="display:block;"><hdiits:button
									name="btnSaveData" id="btnSaveData" type="button"
									captionid="BTN.SAVE" bundle="${dcpsLables}"
									onclick="saveAfterValidation();" />
								</td>
									
								<td  align="center" id="updatebtn" style="display:none;">
								<hdiits:button
									name="btnUpdatedata" id="btnUpdatedata" type="button"
									captionid="BTN.UPDATEREGFORM" bundle="${dcpsLables}"
									onclick="updateAfterValidation();" />
								</td>
								
								<td>
								<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
								<hdiits:button name="BTN.FORWARD" id="btnForward" type="button"
									captionid="BTN.FORWARD" bundle="${dcpsLables}" onclick="forwardRequestAfterValidation();" />
								</td>
							</tr>
				</c:when>
				<c:otherwise>
				<tr>
				<td  align="center" id="updatebtn" >
								<hdiits:button
									name="btnUpdatedataForUpdateTotally" id="btnUpdatedataForUpdateTotally" type="button"
									captionid="BTN.UPDATE" bundle="${dcpsLables}"
									onclick="updateAfterValidationForUpdateTotally('${EMPVO.dcpsEmpId}');" />
								</td>
								
								<td>
								<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
								<hdiits:button name="BTN.FORWARD" id="btnForwardForUpdateTotally" type="button"
									captionid="BTN.FORWARD" bundle="${dcpsLables}" onclick="forwardRequestAfterValidationforUpdateTotally('${EMPVO.dcpsEmpId}');" />
								</td>
				</tr>
				</c:otherwise>
		</c:choose>
		</c:when>
		<c:otherwise>
		
			<tr>
				<td  align="center" id="updatebtn" >
								<hdiits:button
									name="btnUpdatedataForUpdateTotally" id="btnUpdatedataForUpdateTotally" type="button"
									captionid="BTN.SENTBACK" bundle="${dcpsLables}"
									onclick="rejectRequest('${EMPVO.dcpsEmpId}');" />
								</td>
								
								<td>
								<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
								<hdiits:button name="BTN.APPROVE" id="btnForwardForUpdateTotally" type="button"
									captionid="BTN.FORWARD" bundle="${dcpsLables}" onclick="ForwardRequestDDO('${EMPVO.dcpsEmpId}');" />
								</td>
				</tr>
		</c:otherwise>
		</c:choose>
					<tr>
					</tr>
	
	</table>
	
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<ajax:select 
		source="cmbBankName" 
		target="cmbBranchName" 
		eventType="change"
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popBrnchNms" 
		parameters="cmbBankName={cmbBankName}">
</ajax:select>

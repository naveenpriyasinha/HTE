<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVOMST" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjRltDcpsPayrollEmp}"></c:set>

<c:set var="EMPVO" value="${resValue.lObjHstDcpsOtherChanges}"></c:set>
<c:set var="dcpsOrGpf" value="${resValue.dcpsOrGpf}"></c:set>

<c:if test="${resValue.UserType == 'DDO'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>

<c:set var="CHANGESHISTORYVO" value="${resValue.lObjHstDcpsChanges}"></c:set>

<c:set var="UserList" value="${resValue.UserList}"/>
<script>
function showProgressbarForBankOtherDtls()
{
	document.getElementById("txtIFSCCode").value = "";
	showProgressbar();
}
function hideProgressbarForBankOtherDtls()
{
	hideProgressbar();
}
function chkEmptyAcMaintainedByForChanges()
{
	
	if(document.getElementById("radioDCPS").checked)
	{
		/*if(document.getElementById("dcpsAcntMntndBy").value == -1)
		{
			alert('Please Select DCPS Account Maintaining Authority');
			return false;
		}
		*/
		var dcpsAcntMntndBy = document.getElementById("dcpsAcntMntndBy").value.trim();
		if(dcpsAcntMntndBy ==  700175 || dcpsAcntMntndBy == 700176 || dcpsAcntMntndBy == 700177 || dcpsAcntMntndBy == 700178 || dcpsAcntMntndBy == 700179 )
			{
				if(document.getElementById("txtAcNoForNonSRKAEmp").value.trim() == "")
					{
						alert('Please enter Account No for Non SRKA employee');
						return false;
					}
			}
		if(dcpsAcntMntndBy == 700180)
			{
				if(document.getElementById("txtAcNoForNonSRKAEmp").value.trim() == "")
				{
					alert('Please enter Account No for Non SRKA employee');
					return false;
				}
				if(document.getElementById("txtAcNoMntndByOthers").value.trim() == "")
				{
					alert('Please enter Others (Ac Maintained By)');
					return false;
				}
			}
		
	}
	if(document.getElementById("radioGPF").checked)
	{
		var cmbAcMaintainedBy = document.getElementById('cmbAcMaintainedBy').value.trim();
		var cmbPFSeries = document.getElementById("cmbPFSeries").value.trim();
		if(cmbAcMaintainedBy == 700092 || cmbAcMaintainedBy == 700093)
			{
				if(cmbAcMaintainedBy != -1 && cmbPFSeries == -1)
					{
						alert('Please select the new PF series');
						return false;
					}
			}
		
		if(!checkEmptyForDeptSelectedInPF())
			{
				return false;
			}
		
	/*	if(document.getElementById('cmbAcMaintainedBy').value == -1)
		{
			alert('Please Select GPF Account Maintaining Authority');
			return false;
		}
		if(document.getElementById('txtPFSeriesDesc').value == "")
		{
			alert('Please Select GPF Series or Description');
			return false;
		}
		if(cmbAcMaintainedBy == 700092 || cmbAcMaintainedBy == 700093 || cmbAcMaintainedBy == 700094)
		{
			if(document.getElementById('txtPfAccountNo').value == "")
			{
				alert('Please Enter GPF Account No');
				return false;
			}
		}
		*/
	}
	return true;
}
function getChangedFieldsUrl()
{
	var urlConstructed ="" ;
	
	var cmbBankName1 = document.getElementById("cmbBankName1").value.trim();
	var cmbBranchName1 = document.getElementById("cmbBranchName1").value.trim();
	var txtbankAccountNo1 = document.getElementById("txtbankAccountNo1").value.trim();
	var txtIFSCCode1 = document.getElementById("txtIFSCCode1").value.trim();
	
	var cmbBankName = document.getElementById("cmbBankName").value.trim();
	var cmbBranchName = document.getElementById("cmbBranchName").value.trim();
	var txtbankAccountNo = document.getElementById("txtbankAccountNo").value.trim();
	var txtIFSCCode = document.getElementById("txtIFSCCode").value.trim();
	
	// For DCPS
	var dcpsAcntMntndBy1 = "";
	var dcpsAcntMntndBy = "";
	var txtAcNoForNonSRKAEmp1 = "";
	var txtAcNoMntndByOthers1 = "";
	var txtAcNoForNonSRKAEmp = "";
	var txtAcNoMntndByOthers = "";
	
	// For GPF
    var cmbAcMaintainedBy1 = "";
    var cmbAcMaintainedBy = "";
    var cmbPFSeries1 = "";
    var cmbPFSeries = "";
    var txtPFSeriesDesc1 = "";
    var txtPFSeriesDesc = "";
    var txtPfAccountNo1 = "";
    var txtPfAccountNo = "";
	
	var radioDCPSArr = document.DCPSForm.radioDCPS ;
	var radioDCPS;
	for (i=0; i<radioDCPSArr.length; i++)
	{
		  if (radioDCPSArr[i].checked == true)
		  {
			  radioDCPS = radioDCPSArr[i].value.trim() ;
		  }
	}
	
	if(radioDCPS == 'Y')
	{
		dcpsAcntMntndBy1 = document.getElementById("dcpsAcntMntndBy1").value.trim();
		dcpsAcntMntndBy = document.getElementById("dcpsAcntMntndBy").value.trim();
		
		if(document.getElementById("txtAcNoForNonSRKAEmp1") != null)
			{
				txtAcNoForNonSRKAEmp1 = document.getElementById("txtAcNoForNonSRKAEmp1").value.trim();
			}
		if(document.getElementById("txtAcNoForNonSRKAEmp") != null)
			{
				txtAcNoForNonSRKAEmp = document.getElementById("txtAcNoForNonSRKAEmp").value.trim();
			}
		if(document.getElementById("txtAcNoMntndByOthers1") != null)
			{
				txtAcNoMntndByOthers1 = document.getElementById("txtAcNoMntndByOthers1").value.trim();
			}
		if(document.getElementById("txtAcNoMntndByOthers") != null)
			{
				txtAcNoMntndByOthers = document.getElementById("txtAcNoMntndByOthers").value.trim();
			}
	}
	if(radioDCPS == 'N')
		{
			cmbAcMaintainedBy1 = document.getElementById("cmbAcMaintainedBy1").value.trim();
			cmbAcMaintainedBy = document.getElementById("cmbAcMaintainedBy").value.trim();
			cmbPFSeries1 = document.getElementById("cmbPFSeries1").value.trim();
			cmbPFSeries = document.getElementById("cmbPFSeries").value.trim();
			txtPFSeriesDesc1 = document.getElementById("txtPFSeriesDesc1").value.trim();
			txtPFSeriesDesc = document.getElementById("txtPFSeriesDesc").value.trim();
			txtPfAccountNo1 = document.getElementById("txtPfAccountNo1").value.trim();
			txtPfAccountNo = document.getElementById("txtPfAccountNo").value.trim();
		}
	
	urlConstructed = urlConstructed + "&change="+ "OtherDetails" + "&changeDetails="  ;

	var bankName='<fmt:message key="CMN.BANKNAME" bundle="${dcpsLables}"></fmt:message>';
	var branchName='<fmt:message key="CMN.BRANCHNAME" bundle="${dcpsLables}"></fmt:message>';
	var bankAcno ='<fmt:message key="CMN.BANKACNO" bundle="${dcpsLables}"></fmt:message>';
	var ifscCode='<fmt:message key="CMN.IFSCODE" bundle="${dcpsLables}"></fmt:message>';
	
	// DCPS
	var DCPSACNTMNTBY ='<fmt:message key="CMN.DCPSACNTMNTBY" bundle="${dcpsLables}"></fmt:message>';
	var ACCOUNTNOFORNONSRKAEMP ='<fmt:message key="CMN.ACCOUNTNOFORNONSRKAEMP" bundle="${dcpsLables}"></fmt:message>';
	var DCPSACNTMNTBYOTHERS ='<fmt:message key="CMN.DCPSACNTMNTBYOTHERS" bundle="${dcpsLables}"></fmt:message>';
	
	// GPF
	var ACMAINTENEDBY = '<fmt:message key="CMN.ACMAINTENEDBY" bundle="${dcpsLables}"></fmt:message>';
	var PFSERIES ='<fmt:message key="CMN.PFSERIES" bundle="${dcpsLables}"></fmt:message>';
	var PFSERIESDESC = '<fmt:message key="CMN.PFSERIESDESC" bundle="${dcpsLables}"></fmt:message>';
	var PFACNO = '<fmt:message key="CMN.PFACNO" bundle="${dcpsLables}"></fmt:message>';
	
	if(cmbBankName != cmbBankName1 && cmbBankName != -1)
	{
		urlConstructed = urlConstructed + bankName + "," + cmbBankName + "," + cmbBankName1 + "~" ;
	}
	if(cmbBranchName != cmbBranchName1 && cmbBranchName!=-1)
	{
		urlConstructed = urlConstructed + branchName + "," + cmbBranchName + "," + cmbBranchName1 + "~" ;
	}
	if(txtbankAccountNo != txtbankAccountNo1 && txtbankAccountNo!="")
	{
		urlConstructed = urlConstructed + bankAcno + "," + txtbankAccountNo + "," + txtbankAccountNo1 + "~" ;
	}
	if(txtIFSCCode != txtIFSCCode1 && txtIFSCCode!="")
	{
		urlConstructed = urlConstructed + ifscCode + "," +  txtIFSCCode + "," + txtIFSCCode1 + "~" ;
	}
	if(radioDCPS == 'Y')
	{
		if(dcpsAcntMntndBy != "" && dcpsAcntMntndBy != -1 && dcpsAcntMntndBy != dcpsAcntMntndBy1)
			{
				urlConstructed = urlConstructed + DCPSACNTMNTBY + "," + dcpsAcntMntndBy + "," + dcpsAcntMntndBy1 + "~" ;
			}
		if(txtAcNoForNonSRKAEmp != "")
			{
				urlConstructed = urlConstructed + ACCOUNTNOFORNONSRKAEMP + "," + txtAcNoForNonSRKAEmp + "," + txtAcNoForNonSRKAEmp1 + "~" ;
			}
		if(txtAcNoMntndByOthers != "")
			{
				urlConstructed = urlConstructed + DCPSACNTMNTBYOTHERS + "," + txtAcNoMntndByOthers + "," + txtAcNoMntndByOthers1 + "~" ;
			}
	}
	if(radioDCPS == 'N')
	{
		if(cmbAcMaintainedBy != "" && cmbAcMaintainedBy != -1)
		{
			urlConstructed = urlConstructed + ACMAINTENEDBY + "," + cmbAcMaintainedBy + "," + cmbAcMaintainedBy1 + "~" ;
		}
		if(cmbPFSeries != "" && cmbPFSeries != -1)
		{
			urlConstructed = urlConstructed + PFSERIES + "," + cmbPFSeries + "," + cmbPFSeries1 + "~" ;
		}
		if(txtPFSeriesDesc != "")
		{
			urlConstructed = urlConstructed + PFSERIESDESC + "," + txtPFSeriesDesc + "," + txtPFSeriesDesc1 + "~" ;
		}
		if(txtPfAccountNo != "")
		{
			urlConstructed = urlConstructed + PFACNO + "," + txtPfAccountNo + "," + txtPfAccountNo1 + "~" ;
		}
	}
	
	return urlConstructed ;

}
function approveOtherDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value.trim();
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
	var User =document.getElementById("User").value.trim() ; 
	 
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	{
	   hideProgressbar();
	   return;
	}

	var uri = 'ifms.htm?actionFlag=approveChangesByDDO';
	var url = uri + '&dcpsChangesId='+ dcpsChangesId ;
	
	xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].textContent;

						if (test_Id) {
							alert('The Changes are Approved.');
							self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
						}
					}
				}
		} ;

	 xmlHttp.open("POST",uri,false);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(url);				
}

function rejectOtherDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value.trim();
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
	var sentBackRemarks = document.getElementById("sentBackRemarks").value.trim();
	var User =document.getElementById("User").value.trim() ; 

	if(sentBackRemarks == "")
	{
		alert('Please Enter Remarks.');
		hideProgressbar();
		return false ;
	}
	 
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	{
		hideProgressbar();
	   return;
	}

	var uri = 'ifms.htm?actionFlag=rejectChangesToDDOAsst';
	var url = uri + '&dcpsChangesId='+ dcpsChangesId + '&sentBackRemarks='+sentBackRemarks ;
	//alert(url);
	xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].textContent;

						if (test_Id) {
							alert('The changes are rejected and sent back to DDO Assistant.');
							self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
						}
					}
				}
		} ;

	 xmlHttp.open("POST",uri,false);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(url);				
}

function validateOtherChangeDtls(flag)
{
	var changeFlag = false ;
	
	var cmbBankName1 = document.getElementById("cmbBankName1").value.trim();
	var cmbBranchName1 = document.getElementById("cmbBranchName1").value.trim();
	var txtbankAccountNo1 = document.getElementById("txtbankAccountNo1").value.trim();
	var txtIFSCCode1 = document.getElementById("txtIFSCCode1").value.trim();

	var cmbBankName = document.getElementById("cmbBankName").value.trim();
	var cmbBranchName = document.getElementById("cmbBranchName").value.trim();
	var txtbankAccountNo = document.getElementById("txtbankAccountNo").value.trim();
	var txtIFSCCode = document.getElementById("txtIFSCCode").value.trim();

	var txtAuthorityLetterNo = document.getElementById("txtAuthorityLetterNo").value.trim() ;
	var txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value.trim() ;

	if(txtIFSCCode1 != txtIFSCCode || txtbankAccountNo != txtbankAccountNo1 || cmbBankName1 != cmbBankName || cmbBranchName1!= cmbBranchName)
	{
		if(txtIFSCCode!="" || txtbankAccountNo!="" || cmbBankName!=-1 ||  cmbBranchName!=-1)
		{
			changeFlag = true ;
		}
	}
	
	if(!chkEmptyAcMaintainedByForChanges())
		{
			return false;
		}
	
	 // To check these fields are changed or not
	 var dcpsAcntMntndBy = "";
	 var txtAcNoForNonSRKAEmp = "";
	 var txtAcNoMntndByOthers = "";
	 var cmbAcMaintainedBy = "";
	 var cmbAcMaintainedBy = "";
	 var cmbPFSeries = "";
	 var txtPFSeriesDesc = "";
	 var txtPfAccountNo = "";
	 
	 if(document.getElementById("dcpsAcntMntndBy") != null)
		 {
	 		dcpsAcntMntndBy = document.getElementById("dcpsAcntMntndBy").value.trim();
		 }
	 if(document.getElementById("txtAcNoForNonSRKAEmp") != null)
		 {
			txtAcNoForNonSRKAEmp = document.getElementById("txtAcNoForNonSRKAEmp").value.trim();
		 }
	 if(document.getElementById("txtAcNoMntndByOthers") != null)
		 {
			txtAcNoMntndByOthers = document.getElementById("txtAcNoMntndByOthers").value.trim();
		 }
	 if(document.getElementById("cmbAcMaintainedBy") != null)
		 {
		 	cmbAcMaintainedBy = document.getElementById("cmbAcMaintainedBy").value.trim();		 
		 }
	 if(document.getElementById("cmbPFSeries") != null)
		 {
		 	cmbPFSeries = document.getElementById("cmbPFSeries").value.trim();		 	
		 }
	 if(document.getElementById("txtPFSeriesDesc") != null)
		 {
		 	txtPFSeriesDesc = document.getElementById("txtPFSeriesDesc").value.trim();		 	
		 }
	 if(document.getElementById("txtPfAccountNo") != null)
		 {
		 	txtPfAccountNo = document.getElementById("txtPfAccountNo").value.trim();
		 }
	 
		var radioDCPSArr = document.DCPSForm.radioDCPS ;
		var radioDCPS;
		for (i=0; i<radioDCPSArr.length; i++)
		{
			  if (radioDCPSArr[i].checked == true)
			  {
				  radioDCPS = radioDCPSArr[i].value.trim() ;
			  }
		}
	 
	if(radioDCPS == 'Y')
		{
			// Validations to be added
			if(dcpsAcntMntndBy != -1 || txtAcNoForNonSRKAEmp != "" || txtAcNoMntndByOthers != "")
				{
					changeFlag = true ;
				}
		}
	if(radioDCPS == 'N')
		{
			// Validations to be added
			if(cmbAcMaintainedBy != -1 || cmbPFSeries != -1 || txtPFSeriesDesc != "" || txtPfAccountNo != "" )
				{
					changeFlag = true ;
				}
		}

	if(!changeFlag)
	{
		alert('You have not changed any details.');
		return false ;
	}
	
	if(changeFlag && flag==2)
	{
		if(txtAuthorityLetterNo == "" || txtAuthorityLetterDate == "")
		{
			alert('Please fill the Authority Details.') ;
			return false;
		}
	}
		
	return true ;
}

function updateOrForwardOtherDetails(emp_id,flag)
{	
     	showProgressbar();
		if(!validateOtherChangeDtls(flag))
		{
			hideProgressbar();
			return false ;
		}
	
		var empId= emp_id;
		var saveOrForwardFlag=flag;
		var designationId = document.getElementById("lStrDesignation").value.trim();
		var typeOfChanges = document.getElementById("lStrChangesType").value.trim();
		
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
		   hideProgressbar();
		   return;
		}

		 var uri = 'ifms.htm?actionFlag=updateOtherDtls';
		 var url = runForm(0); 
		 var urlChangedFields = getChangedFieldsUrl();
		 url = uri + url + urlChangedFields; 
		 url = url + "&empId="+empId;	 
		 var dcpsHstChangesId = document.getElementById("dcpsHstChangesId").value.trim();
		 var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
		 url = url + "&dcpsHstChangesId="+dcpsHstChangesId; 
		 
		 xmlHttp.onreadystatechange = function()
			{
				if (xmlHttp.readyState == 4) {
					if (xmlHttp.status == 200) {
						hideProgressbar();
						XMLDoc = xmlHttp.responseXML.documentElement;
						var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
							var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
							var dcpsChangesId =  XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
							if (test_Id) {
								var User =document.getElementById("User").value.trim() ; 
								if(saveOrForwardFlag==1)
								{
									if(dcpsHstChangesId == "")
									{
										alert('Other Details have been successfully changed and saved.');
										self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
									}
									else
									{
										alert('Other Details have been successfully changed and saved.');
										self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
									}
								}
								if(saveOrForwardFlag==2)
								{
									var ForwardToPost =  document.getElementById("ForwardToPost").value.trim();
									var uriForward ;
										if(dcpsHstChangesId == "")
										{
										uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="+dcpsChangesId+"&ForwardToPost="+ForwardToPost;
										}
										else
										{
										uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="+dcpsHstChangesId+"&ForwardToPost="+ForwardToPost;
										}
									//alert(uriForward);
									xmlHttpNew=GetXmlHttpObject();
									
									if (xmlHttpNew==null)
									{
										alert ("Your browser does not support AJAX!");
										return;
									} 
									xmlHttpNew.onreadystatechange= function()
									{
										if (xmlHttpNew.readyState == 4) {
											if (xmlHttpNew.status == 200) {
												
												XMLDocNew = xmlHttpNew.responseXML.documentElement;
												var XmlHiddenValuesNew = XMLDocNew.getElementsByTagName('XMLDOC');
												var success_flag = XmlHiddenValuesNew[0].childNodes[0].firstChild.nodeValue;
												if(success_flag=='true')
												{
													if(dcpsHstChangesId == "")
													{
														alert("Changes are forwarded successfully");
														self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
													}
													else
													{
														alert("Changes are forwarded successfully");
														self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
													}
												}
											  }
											}
									};
									xmlHttpNew.open("POST",uriForward,false);
									xmlHttpNew.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
									xmlHttpNew.send(uriForward);
								}
								
							}
						}
					}
			};
		   xmlHttp.open("POST",uri,true);
		   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   xmlHttp.send(url);
}
</script>


<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.EXISTINGDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>

<br>		


<input type="hidden" id="txtDdoCode1" name="txtDdoCode1" value="${resValue.DDOCODE}"/>
<table width="100%" align="center" cellpadding="4" cellspacing="4">

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.BANKNAME"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbBankName1"
				id="cmbBankName1" style="width: 240px" onChange=""${varDisabled} disabled="disabled" >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="bankName" items="${resValue.BANKNAMES}">
					<c:choose>
						<c:when test="${EMPVOMST.bankName == bankName.id}">
							<option value="${bankName.id}" selected="selected"><c:out
								value="${bankName.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${bankName.id}"><c:out
								value="${bankName.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
		
			<td width="15%" align="left"><fmt:message key="CMN.BRANCHNAME"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbBranchName1"
				id="cmbBranchName1" style="width: 240px" ${varDisabled}  disabled="disabled">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:if test="${EMPVOMST!=null}">
					<c:forEach var="branchName" items="${resValue.BRANCHNAMESMST}">
						<c:choose>
							<c:when test="${EMPVOMST.branchName == branchName.id}">
								<option value="${branchName.id}" selected="selected"><c:out
									value="${branchName.desc}"></c:out></option>
							</c:when>
							<c:otherwise>
								<option value="${branchName.id}"><c:out
									value="${branchName.desc}"></c:out></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</select></td>
		</tr>

		<tr>
		
			<td width="15%" align="left"><fmt:message key="CMN.BANKACNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtbankAccountNo1" size="31" name="txtbankAccountNo1"
				value="${EMPVOMST.bankAccountNo}" ${varDisabled} disabled="disabled"/></td>
				
			<td width="15%" align="left"><fmt:message key="CMN.IFSCODE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtIFSCCode1"
				style="text-transform: uppercase" size="31" name="txtIFSCCode1"
				value="${EMPVOMST.IFSCCode}" ${varDisabled} disabled="disabled"/></td>
		</tr>
	</table>

<!--  Added by Vihan for Change PF details in Existing Details-->

	<table width="100%">
		<tr>
			<td width="15%" align="left">
				&nbsp;&nbsp;<fmt:message key="CMN.DCPS?" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left" style="padding-left: 5px" >
				<c:choose>
					<c:when test="${EMPVOMST != null}">
						<c:choose>
							<c:when test="${dcpsOrGpf == 'N'}">
								<input type="radio"	id="radioDCPS1" name="radioDCPS1" value="Y"  ${varDisabled} disabled="disabled" /> 
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioGPF1" name="radioDCPS1" value="N" ${varDisabled}  checked="checked" disabled="disabled" />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<c:set var="varDCPSDisabled" value="disabled='disabled'"/>
								<c:set var="varDcpsAsterisks" value="style='display: none'"/>
								<c:set var="varGpfAsterisks" value="style='display: inline'"/>
							</c:when>
							<c:otherwise>
								<input type="radio"	id="radioDCPS1" name="radioDCPS1" value="Y"  ${varDisabled} checked="checked" disabled="disabled"/> 
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioGPF1" name="radioDCPS1" value="N"  ${varDisabled} disabled="disabled"/>
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<c:set var="varPFDisabled" scope="page" value="disabled='disabled' readOnly='readOnly'"></c:set>
								<c:set var="varDcpsAsterisks" value="style='display: inline'"/>
								<c:set var="varGpfAsterisks" value="style='display: none'"/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
							<input type="radio"	id="radioDCPS1" name="radioDCPS1" value="Y"  ${varDisabled} checked="checked" disabled="disabled" /> 
							<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
							<input type="radio"	id="radioGPF1" name="radioGPF1" value="N"  ${varDisabled} disabled="disabled"/>
							<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
							<c:set var="varPFDisabled" scope="page" value="disabled='disabled' readOnly='readOnly'"></c:set>
							<c:set var="varGpfAsterisks" value="style='display: none'"/>
					</c:otherwise>
				</c:choose>
			</td>
			
			<td width="15%" align="left"><fmt:message
				key="CMN.DCPSACNTMNTBY" bundle="${dcpsLables}"></fmt:message></td>
	
			<td width="20%" align="left">
			<select name="dcpsAcntMntndBy1"
				id="dcpsAcntMntndBy1" style="width: 85%; display: inline;" ${varDisabled} ${varDCPSDisabled} disabled="disabled">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="acntMntndBy" items="${resValue.lLstPFAccntMntdByDCPS}">
					<c:choose>
						<c:when
							test="${EMPVOMST.acDcpsMaintainedBy==acntMntndBy.lookupId}">
							<option value="${acntMntndBy.lookupId}" selected="selected"><c:out
								value="${acntMntndBy.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${acntMntndBy.lookupId}" title="${acntMntndBy.lookupDesc}"><c:out
								value="${acntMntndBy.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			</td>
			
		</tr>
		
		<c:choose>
			<c:when test="${EMPVOMST == null || EMPVOMST.acDcpsMaintainedBy == null}">
				<c:set var="varAcNoDisplay" value="style='display: none'" ></c:set>
			</c:when>
			<c:otherwise>
					<c:choose>
						<c:when test="${EMPVOMST.acDcpsMaintainedBy != '700174'}">
							<c:set var="varAcNoDisplay" value="" ></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="varAcNoDisplay" value="style='display: none'" ></c:set>
						</c:otherwise>
					</c:choose>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${EMPVOMST.acDcpsMaintainedBy == '700180'}">
				<c:set var="varAcMntndByOthers" value="" ></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="varAcMntndByOthers" value="style='display: none'" ></c:set>
			</c:otherwise>
		</c:choose>
		
		<tr>
		
			<td width="15%" align="left" ${varAcNoDisplay} id="tdForAcno1" >
			&nbsp;
				<fmt:message key="CMN.ACCOUNTNOFORNONSRKAEMP" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left" ${varAcNoDisplay} id="tdForAcNoTxtBox1" >&nbsp;<input type="text"
				id="txtAcNoForNonSRKAEmp1" size="31" name="txtAcNoForNonSRKAEmp1"
				value="${EMPVOMST.acNonSRKAEmp}" ${varDisabled} disabled="disabled" />
			</td>
			<td width="15%" align="left" ${varAcMntndByOthers} id="tdForAcMntnOthers1"><fmt:message
				key="CMN.DCPSACNTMNTBYOTHERS" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left" ${varAcMntndByOthers}  id="tdForAcMntnOthersTxtBox1" ><input type="text"
				id="txtAcNoMntndByOthers1" size="31" name="txtAcNoMntndByOthers1"
				value="${EMPVOMST.acMntndByOthers}" ${varDisabled} disabled="disabled"/>
			</td>
		</tr>
		
	</table>
	</br>
	
		<table width="100%" align="center" cellpadding="4" cellspacing="4">
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.ACMAINTENEDBY" bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left">
					<select name="cmbAcMaintainedBy1"
						id="cmbAcMaintainedBy1" style="width: 60%" ${varDisabled} ${varPFDisabled} disabled="disabled" >
						<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="acMntndByVar" items="${resValue.lLstPFAccntMntdBy}" >
																<c:choose>
																	<c:when test="${EMPPAYROLLVO.acMaintainedBy == acMntndByVar.lookupId}">
																	<option value="${acMntndByVar.lookupId}" selected="selected"><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																	<option value="${acMntndByVar.lookupId}" ><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
						</c:forEach>
					</select>
					</td>
					
			</tr>
			
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.PFSERIES" bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left">
					<select name="cmbPFSeries1"
									id="cmbPFSeries1" ${varDisabled} ${varPFDisabled} ${varDisableForNonAG} disabled="disabled" > 
									<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
									<c:forEach var="pfSeriesVar" items="${resValue.lLstPFSeriesMst}" >
																			<c:choose>
																				<c:when test="${EMPPAYROLLVO.pfSeries == pfSeriesVar.lookupId}">
																				<option value="${pfSeriesVar.lookupId}" selected="selected"><c:out 
																						value="${pfSeriesVar.lookupDesc}"></c:out></option>
																				</c:when>
																				<c:otherwise>
																				<option value="${pfSeriesVar.lookupId}" ><c:out 
																						value="${pfSeriesVar.lookupDesc}"></c:out></option>
																				</c:otherwise>
																			</c:choose>
									</c:forEach>
					</select> 
					<input type="text" id="txtPFSeries1" size="28" name="txtPFSeries1" ${varPFDisabled} value="${EMPPAYROLLVO.pfSeries}" style="display: none" disabled="disabled" />
					</td>
					
					<c:choose>
						<c:when test="${EMPPAYROLLVO.acMaintainedBy == 700094}">
							<c:set var="grayOutSeriesDescForDeptSelected" value="style=''"  ></c:set>
							<c:set var="displayLabelOfSeriesDescForDeptSelected" value="style='display='inline'"  ></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="grayOutSeriesDescForDeptSelected" value="style='readOnly=readOnly'"></c:set>
							<c:set var="displayLabelOfSeriesDescForDeptSelected" value="style='display='none'"  ></c:set>
						</c:otherwise>
					</c:choose>
					
					<td width="15%" align="left"><fmt:message key="CMN.PFSERIESDESC"
				bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left"><input type="text" id="txtPFSeriesDesc1" disabled="disabled" 
				size="30" name="txtPFSeriesDesc1" value="${EMPPAYROLLVO.pfSeriesDesc}" ${varDisabled} maxlength="20" ${grayOutSeriesDescForDeptSelected} ${varPFDisabled} onkeypress="alphaFormat(this);"/>
				</td>
					
			</tr>
			
			<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PFACNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" disabled="disabled"
				id="txtPfAccountNo1" size="28" maxlength="8" name="txtPfAccountNo1"
				value="${EMPPAYROLLVO.pfAcNo}" onkeypress="digitFormat(this);" ${varPFDisabled} ${varDisabled} disabled="disabled"/>
				</td>
			</tr>
		</table>
	
<!--  Added overs by Vihan for Change PF Details In Existing Details -->	
	
</fieldset>
<br/>

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data"
	validate="true" method="post" >
<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CHANGEDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
<input type="hidden" id="txtDdoCode" name="txtDdoCode" value="${resValue.DDOCODE}"/>
<input type="hidden" id="lStrDesignation" name="lStrDesignation" value="${resValue.lStrDesignation}"/>
<input type="hidden" id="lStrChangesType" name="lStrChangesType" value="${resValue.lStrChangesType}"/>
	<table width="100%" align="center" cellpadding="4" cellspacing="4">

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.BANKNAME"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbBankName"
				id="cmbBankName" style="width: 240px" onChange=""${varDisabled} >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="bankName" items="${resValue.BANKNAMES}">
					<c:choose>
						<c:when test="${EMPVO.bankName == bankName.id}">
							<option value="${bankName.id}" selected="selected"><c:out
								value="${bankName.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${bankName.id}"><c:out
								value="${bankName.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
		
			<td width="15%" align="left"><fmt:message key="CMN.BRANCHNAME"
				bundle="${dcpsLables}"></fmt:message>Salle</td>
			<td width="20%" align="left"><select name="cmbBranchName"
				id="cmbBranchName" style="width: 240px" ${varDisabled} onChange="popUpIFSCCode();" >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:if test="${EMPVO!=null}">
					<c:forEach var="branchName" items="${resValue.BRANCHNAMES}">
						<c:choose>
							<c:when test="${EMPVO.branchName == branchName.id}">
								<option value="${branchName.id}" selected="selected"><c:out
									value="${branchName.desc}"></c:out></option>
							</c:when>
							<c:otherwise>
								<option value="${branchName.id}"><c:out
									value="${branchName.desc}"></c:out></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</select></td>
		</tr>

		<tr>
		
			<td width="15%" align="left"><fmt:message key="CMN.BANKACNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtbankAccountNo" size="31" name="txtbankAccountNo"
				value="${EMPVO.bankAccountNo}" ${varDisabled} /></td>
				
			<td width="15%" align="left"><fmt:message key="CMN.IFSCODE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtIFSCCode"
				style="text-transform: uppercase" size="31" name="txtIFSCCode" readonly="readonly"
				value="${EMPVO.IFSCCode}" ${varDisabled}/></td>
		</tr>
	</table>
	
	<!--  Added by Vihan for Change PF Details in Change Details  -->
	
	<table width="100%">
		<tr>
			<td width="15%" align="left">
				&nbsp;&nbsp;<fmt:message key="CMN.DCPS?" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left" style="padding-left: 5px" >
				<c:choose>
					<c:when test="${EMPVOMST != null}">
						<c:choose>
							<c:when test="${dcpsOrGpf == 'N'}">
								<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();changeDcpsOrGpfRadio();" ${varDisabled} disabled="disabled" /> 
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();changeDcpsOrGpfRadio();" ${varDisabled}  checked="checked" disabled="disabled"  />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<c:set var="varDCPSDisabled" value="disabled='disabled'"/>
								<c:set var="varDcpsAsterisks" value="style='display: none'"/>
								<c:set var="varGpfAsterisks" value="style='display: inline'"/>
							</c:when>
							<c:otherwise>
								<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();changeDcpsOrGpfRadio();" ${varDisabled} checked="checked" disabled="disabled"  /> 
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();changeDcpsOrGpfRadio();" ${varDisabled} disabled="disabled"  />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<c:set var="varPFDisabled" scope="page" value="disabled='disabled' readOnly='readOnly'"></c:set>
								<c:set var="varDcpsAsterisks" value="style='display: inline'"/>
								<c:set var="varGpfAsterisks" value="style='display: none'"/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
							<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();changeDcpsOrGpfRadio();" ${varDisabled} checked="checked" disabled="disabled"  /> 
							<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
							<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();changeDcpsOrGpfRadio();" ${varDisabled} disabled="disabled" />
							<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
							<c:set var="varPFDisabled" scope="page" value="disabled='disabled' readOnly='readOnly'"></c:set>
							<c:set var="varGpfAsterisks" value="style='display: none'"/>
					</c:otherwise>
				</c:choose>
			</td>
			
			<td width="15%" align="left"><fmt:message
				key="CMN.DCPSACNTMNTBY" bundle="${dcpsLables}"></fmt:message></td>
	
			<td width="20%" align="left">
			<select name="dcpsAcntMntndBy"
				id="dcpsAcntMntndBy" style="width: 85%; display: inline;" onchange="checkAcMntndBy();" ${varDisabled} ${varDCPSDisabled}>
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="acntMntndBy" items="${resValue.lLstPFAccntMntdByDCPS}">
					<c:choose>
						<c:when
							test="${EMPVO.acDcpsMaintainedBy==acntMntndBy.lookupId}">
							<option value="${acntMntndBy.lookupId}" selected="selected"><c:out
								value="${acntMntndBy.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${acntMntndBy.lookupId}" title="${acntMntndBy.lookupDesc}"><c:out
								value="${acntMntndBy.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<!-- <label class="mandatoryindicator" id="labelForDcpsAcMntndBy" ${varLabelDisabled} ${varDcpsAsterisks}>*</label>  -->
			</td>
		</tr>
		
		<c:choose>
			<c:when test="${EMPVO == null || EMPVO.acDcpsMaintainedBy == null}">
				<c:set var="varAcNoDisplay" value="style='display: none'" ></c:set>
			</c:when>
			<c:otherwise>
					<c:choose>
						<c:when test="${EMPVO.acDcpsMaintainedBy != '700174'}">
							<c:set var="varAcNoDisplay" value="" ></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="varAcNoDisplay" value="style='display: none'" ></c:set>
						</c:otherwise>
					</c:choose>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${EMPVO.acDcpsMaintainedBy == '700180'}">
				<c:set var="varAcMntndByOthers" value="" ></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="varAcMntndByOthers" value="style='display: none'" ></c:set>
			</c:otherwise>
		</c:choose>
		
		<tr>
		
			<td width="15%" align="left" ${varAcNoDisplay} id="tdForAcno" >
			&nbsp;
				<fmt:message key="CMN.ACCOUNTNOFORNONSRKAEMP" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left" ${varAcNoDisplay} id="tdForAcNoTxtBox" >&nbsp;<input type="text"
				id="txtAcNoForNonSRKAEmp" size="31" name="txtAcNoForNonSRKAEmp"
				value="${EMPVO.acNonSRKAEmp}" ${varDisabled}  />
				<label class="mandatoryindicator" ${varLabelDisabled} >*</label>
			</td>
			<td width="15%" align="left" ${varAcMntndByOthers} id="tdForAcMntnOthers" ><fmt:message
				key="CMN.DCPSACNTMNTBYOTHERS" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left" ${varAcMntndByOthers}  id="tdForAcMntnOthersTxtBox"><input type="text"
				id="txtAcNoMntndByOthers" size="31" name="txtAcNoMntndByOthers"
				value="${EMPVO.acMntndByOthers}" ${varDisabled} />
				<label class="mandatoryindicator" ${varLabelDisabled}>*</label>
			</td>
		</tr>
		
	</table>
	</br>
		<table width="100%" align="center" cellpadding="4" cellspacing="4">
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.ACMAINTENEDBY" bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left">
					<input type="hidden" id="txtGroup" size="30" name="txtGroup" value="${resValue.GroupName}" readonly="readonly" />
					<select name="cmbAcMaintainedBy"
						id="cmbAcMaintainedBy" style="width: 60%" onChange="checkGroupDtls();" ${varDisabled} ${varPFDisabled}  >
						<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="acMntndByVar" items="${resValue.lLstPFAccntMntdBy}" >
																<c:choose>
																	<c:when test="${EMPVO.acMaintainedBy == acMntndByVar.lookupId}">
																	<option value="${acMntndByVar.lookupId}" selected="selected"><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																	<option value="${acMntndByVar.lookupId}" ><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
						</c:forEach>
					</select>
					<!-- <label class="mandatoryindicator" id="labelForGPFAcmntndBy" ${varLabelDisabled} ${varGpfAsterisks}>*</label>  -->
					</td>
					
			</tr>
			
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.PFSERIES" bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left">
					<select name="cmbPFSeries"
									id="cmbPFSeries" onChange="getPFDesc();" ${varDisabled} ${varPFDisabled} ${varDisableForNonAG}> 
									<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
									<c:forEach var="pfSeriesVar" items="${resValue.lLstPFSeries}" >
																			<c:choose>
																				<c:when test="${EMPVO.pfSeries == pfSeriesVar.lookupId}">
																				<option value="${pfSeriesVar.lookupId}" selected="selected"><c:out 
																						value="${pfSeriesVar.lookupDesc}"></c:out></option>
																				</c:when>
																				<c:otherwise>
																				<option value="${pfSeriesVar.lookupId}" ><c:out 
																						value="${pfSeriesVar.lookupDesc}"></c:out></option>
																				</c:otherwise>
																			</c:choose>
									</c:forEach>
					</select> 
					<input type="text" id="txtPFSeries"	size="28" name="txtPFSeries" ${varPFDisabled} value="${EMPVO.pfSeries}" style="display: none" />
					<!-- <label class="mandatoryindicator" id="labelForGPFSeries" ${varLabelDisabled} ${varGpfAsterisks}>*</label>  -->
					</td>
					
					<c:choose>
						<c:when test="${EMPVO.acMaintainedBy == 700094}">
							<c:set var="grayOutSeriesDescForDeptSelected" value="style=''"  ></c:set>
							<c:set var="displayLabelOfSeriesDescForDeptSelected" value="style='display='inline'"  ></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="grayOutSeriesDescForDeptSelected" value="style='readOnly=readOnly'"></c:set>
							<c:set var="displayLabelOfSeriesDescForDeptSelected" value="style='display='none'"  ></c:set>
						</c:otherwise>
					</c:choose>
					
					<td width="15%" align="left"><fmt:message key="CMN.PFSERIESDESC"
				bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left"><input type="text" id="txtPFSeriesDesc"
				size="30" name="txtPFSeriesDesc" value="${EMPVO.pfSeriesDesc}" ${varDisabled} maxlength="20" ${grayOutSeriesDescForDeptSelected} ${varPFDisabled} onkeypress="alphaFormat(this);"/>
					<!-- <label class="mandatoryindicator" id="labelForGPFSeriesDesc" ${varLabelDisabled} ${displayLabelOfSeriesDescForDeptSelected}>*</label> -->
				</td>
					
			</tr>
			
			<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PFACNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtPfAccountNo" size="28" maxlength="8" name="txtPfAccountNo"
				value="${EMPVO.pfAcNo}" onkeypress="digitFormat(this);" ${varPFDisabled} ${varDisabled}/>
				<!-- <label class="mandatoryindicator"  id="labelForGPFAcNo" ${varLabelDisabled} ${varGpfAsterisks}>*</label> -->
				</td>
			</tr>
		</table>
	
	<!--  Added overs by Vihan for Change PF Details in Change Details  -->
	
</fieldset>

<br/>
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.AUTHORITYDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
		<table width="50%" align="left" cellpadding="4" cellspacing="4" >
		<tr>
			<td width="15%" align="left">Authority Letter No.</td>
			<td width="20%" align="left" colspan = "3"><input type="text"
				id="txtAuthorityLetterNo" style="text-transform: uppercase" size="30"
				name="txtAuthorityLetterNo" value="${CHANGESHISTORYVO.letterNo}" onblur="isIntegerOrNot(document.getElementById('txtAuthorityLetterNo'),'Authority Letter No should be Number Only')"/>
				<label class="mandatoryindicator">*</label></td>
		
		</tr>
		<tr>
			<td width="15%" align="left">Letter Date</td>
			<c:if test="${resValue.lObjHstDcpsChanges != null}">
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${CHANGESHISTORYVO.letterDate}" var="letterDate"/>
			</c:if>
			<td width="20%" align="left" colspan = "3"><input type="text"
				id="txtAuthorityLetterDate" style="text-transform: uppercase" size="30"
				onkeypress="digitFormat(this);dateFormat(this);"
				name="txtAuthorityLetterDate" value="${letterDate}" /><img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtAuthorityLetterDate", 375, 570)'
				style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label></td>
		
		</tr>
		
		</table>
		
		</fieldset>
		<br/>
		
		<input type="hidden" id="User" name="User" value="${resValue.UserType}">
		<c:if test="${resValue.UserType == 'DDO'}">
		<table width="100%">
			<tr>
				<td width="20%" align="left" style="padding-left: 5px"><fmt:message key="CMN.REMARKS"
					bundle="${dcpsLables}"></fmt:message></td>
				<td align="left" width="80%" style="padding-left: 23px">
					<input type="text" name="sentBackRemarks" id="sentBackRemarks" value="" size="100"  />
				</td>
			</tr>
		</table>
		</c:if>
		
		<div align="right">
						<c:choose>
							<c:when test="${resValue.dcpsChangesId != null}">
								<input type="hidden" id="dcpsHstChangesId" value="${resValue.dcpsChangesId}"/>
								<input type="hidden" id="lStrDesignationDraft" value="${resValue.lStrDesignationDraft}"/>
							</c:when>
							<c:otherwise>
								<input type="hidden" id="dcpsHstChangesId" value=""/>
								<input type="hidden" id="lStrDesignationDraft" value=""/>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${resValue.UserType == 'DDOAsst'}">
							<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnfromChanges();"/>
								<hdiits:button
									name="btnUpdatedataForUpdateTotally" id="btnUpdatedataForUpdateTotally" type="button"
									captionid="BTN.SAVEASDRAFT" bundle="${dcpsLables}"
									onclick="updateOrForwardOtherDetails('${EMPVOMST.dcpsEmpId}',1);" />
								
								<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
								<hdiits:button name="BTN.FORWARD" id="btnForwardForUpdateTotally" type="button"
									captionid="BTN.FORWARD" bundle="${dcpsLables}" onclick="updateOrForwardOtherDetails('${EMPVOMST.dcpsEmpId}',2);" />
							</c:when>
							<c:otherwise>
							<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnfromChanges();"/>
								<hdiits:button
									name="btnApprovePersonalDtls" id="btnApprovePersonalDtls" type="button"
									captionid="BTN.APPROVE" bundle="${dcpsLables}"
									onclick="approveOtherDetails();" />
								
								<hdiits:button name="BTN.REJECT" id="btnRejectPersonalDtls" type="button"
									captionid="BTN.REJECT" bundle="${dcpsLables}" onclick= "rejectOtherDetails();" />
							</c:otherwise>	
						</c:choose>
		</div>
		
</hdiits:form>

<ajax:select source="cmbBankName" target="cmbBranchName"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popBrnchNms"
	parameters="cmbBankName={cmbBankName}"
	preFunction="showProgressbarForBankOtherDtls"
	postFunction="hideProgressbarForBankOtherDtls"
	>
</ajax:select>	



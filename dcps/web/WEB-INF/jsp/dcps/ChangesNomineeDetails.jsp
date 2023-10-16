<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="NOMINEESLISTMST" value="${resValue.NomineesList}"></c:set>
<c:set var="EMPVOMST" value="${resValue.lObjEmpData}"></c:set>
<c:set var="NOMINEESLIST" value="${resValue.NomineesHstList}"></c:set>

<c:set var="CHANGESHISTORYVO" value="${resValue.lObjHstDcpsChanges}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>

<c:if test="${resValue.UserType == 'DDO'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>

<script type="text/javascript">
var serialNo=0;
var rowCount=0;
var addedOrNotFlag=1;
var tbody;
var SaveOrUpdateNominee=0;            // 1 for SAVE Nominee and for all other positive values, it is UPDATE Nominee.
var designationId;
var typeOfChanges;
var nomineeDtlsChangedOrNotFlag = false;
var saveOrForwardFlag;
var dcpsHstChangesId;
var designationIdDraft;
var totalNomineeShareAdded;
var motherAdded = false ;
var fatherAdded = false ;
var spouseAdded = false ;

function UpdateMstDetails(counter)
{
	var nomineeName = document.getElementById("txtNameValueMst"+counter).value ;
	var address = document.getElementById("txtAddress1ValueMst"+counter).value ;
	var dob = document.getElementById("txtDateOfBirthValueMst"+counter).value ;
	var share = document.getElementById("txtPercentShareValueMst"+counter).value ;
	var relationship = document.getElementById("txtRelationshipValueMst"+counter).value ;

	document.getElementById("txtNomineeName").value = nomineeName;
	document.getElementById("txtNomAddress1").value = address;
	document.getElementById("txtBirthDateOfNominee").value = dob;
	document.getElementById("txtPercentShare").value = share;
	document.getElementById("cmbRelationship").value = relationship;
}


function approveNomineeDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value;
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value;
	var User =document.getElementById("User").value ; 
	 
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
						var test_Id = XmlHiddenValues[0].childNodes[0].text;

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

function rejectNomineeDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value;
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value;
	var sentBackRemarks = document.getElementById("sentBackRemarks").value;
	var User =document.getElementById("User").value ; 
	 
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
						var test_Id = XmlHiddenValues[0].childNodes[0].text;

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
function addRow()
{
	if(document.getElementById("cmbRelationship").value.trim() == 'Mother')
  	{
  		if(motherAdded)
  		{
  			alert('Nominee as Mother is already added.');
  			return false;
  		}
  	}
  	if(document.getElementById("cmbRelationship").value.trim() == 'Father')
  	{
  		if(fatherAdded)
  		{
  			alert('Nominee as Father is already added.');
  			return false;
  		}
  	}
  	if(document.getElementById("cmbRelationship").value.trim() == 'Spouse')
  	{
  		if(spouseAdded)
  		{
  			alert('Nominee as Spouse is already added.');
  			return false;
  		}
  	}
  	
	nomineeDtlsChangedOrNotFlag = true ;
	serialNo ++ ;
	if(document.getElementById("txtPercentShare").value.trim() > 100)
	{
		alert('Percentage Share cannot be more than 100');
		addedOrNotFlag = 0;
		return false ;
	}
	
	if(document.getElementById("txtNomineeName").value.trim() == "")
	{
		alert('Please Enter Nominee Name');
		return false;
	}
	
	
	if(document.getElementById("txtNomAddress1").value.trim() == "")
	{
		alert('Please Enter Nominee Address1');
		return false;
	}
	
	if(document.getElementById("txtBirthDateOfNominee").value.trim() == "")
	{
		alert('Please Enter Nominee Date of Birth');
		return false;
	}
	
	if(document.getElementById("cmbRelationship").value.trim() == "")
	{
		alert('Please Enter Nominee Relationship');
		return false;
	}

	if(totalNomineeShareAdded + Number(document.getElementById("txtPercentShare").value) > 100)
	{
		alert('Total Nominee share exceeds 100.Please enter correct nominee share.');
		return false ;
	}
	
	totalNomineeShareAdded = totalNomineeShareAdded + Number(document.getElementById("txtPercentShare").value) ;

		tbody = document.getElementById('displayTableForNomineeDtls').getElementsByTagName('tbody')[0]; 
		var row = document.createElement('TR'); 
		
		rowCount++;

		var className=serialNo;

		var cell1=document.createElement('TD');
		cell1.align="center";
		var cell2=document.createElement('TD');
		var cell3=document.createElement('TD');
		var cell4=document.createElement('TD');
		var cell5=document.createElement('TD');

		cell1.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtNomineeSerialNoValue\" class='"+className+"' value='"+serialNo+"' readonly=\"readonly\" />";
		cell2.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtNameValue\" class='"+className+"' value='"+document.getElementById("txtNomineeName").value +"' readonly=\"readonly\" />";
		cell3.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtDateOfBirthValue\" class='"+className+"' value='"+document.getElementById("txtBirthDateOfNominee").value+"' readonly=\"readonly\"  />";
		cell4.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtPercentShareValue\" class='"+className+"' value='"+document.getElementById("txtPercentShare").value+"' readonly=\"readonly\"  />";
		cell5.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtRelationshipValue\" class='"+className+"' value='"+document.getElementById("cmbRelationship").value+"'  readonly=\"readonly\" />";
		cell1.align="center";
		cell1.className = "tds";
		cell2.align="center";
		cell2.className = "tds";
		cell3.align="center";
		cell3.className = "tds";
		cell4.align="center";
		cell4.className = "tds";
		cell5.align="center";
		cell5.className = "tds";
		
	  	var hiddenCell0=document.createElement('TD');		
		hiddenCell0.innerHTML = "<input type='hidden' name=\"txtNomSerialNo\" class='"+className+"' value='"+serialNo+"' />";
		var hiddenCell1=document.createElement('TD');		
		hiddenCell1.innerHTML = "<input type='hidden' name=\"txtNomName\" class='"+className+"' value='"+document.getElementById("txtNomineeName").value+"' />";
		var hiddenCell4=document.createElement('TD');		
		hiddenCell4.innerHTML = "<input type='hidden' name=\"txtNomAddr1\" class='"+className+"' value='"+document.getElementById("txtNomAddress1").value+"' />";
		var hiddenCell6=document.createElement('TD');		
		hiddenCell6.innerHTML = "<input type='hidden' name=\"txtNomDOB\" class='"+className+"' value='"+document.getElementById("txtBirthDateOfNominee").value+"' />";
		var hiddenCell7=document.createElement('TD');		
		hiddenCell7.innerHTML = "<input type='hidden' name=\"txtNomPerShare\" class='"+className+"' value='"+document.getElementById("txtPercentShare").value+"' />";
		var hiddenCell8=document.createElement('TD');		
		hiddenCell8.innerHTML = "<input type='hidden' name=\"txtNomRelationship\" class='"+className+"' value='"+document.getElementById("cmbRelationship").value+"' />";

		
		var cell7=document.createElement('TD');
		cell7.innerHTML="<img src=\"images/CalendarImages/DeleteIcon.gif\" onclick=\"deleteRow()\" />";
		cell7.align="center";
		cell7.className = "tds";
		
	  	var cell8=document.createElement('TD');
	  	cell8.innerHTML="<input type=\"hidden\" value='"+ rowCount +"'/>";

		if(document.getElementById("cmbRelationship").value.trim() == 'Mother')
	  	{
	  		motherAdded = true;
	  	}
	  	if(document.getElementById("cmbRelationship").value.trim() == 'Father')
	  	{
	  		fatherAdded = true;
	  	}
	  	if(document.getElementById("cmbRelationship").value.trim() == 'Spouse')
	  	{
	  		spouseAdded = true;
	  	}
		
		row.appendChild(cell1);
		row.appendChild(cell2);
		row.appendChild(cell3);
		row.appendChild(cell4);
		row.appendChild(cell5);
		row.appendChild(cell7);
		row.appendChild(cell8);

		
		row.appendChild(hiddenCell0);
		row.appendChild(hiddenCell1);
		row.appendChild(hiddenCell4);
		row.appendChild(hiddenCell6);
		row.appendChild(hiddenCell7);
		row.appendChild(hiddenCell8);
		
		tbody.appendChild(row); 

		resetFields();
}
function resetFields()
	{
	if(addedOrNotFlag)
		{
			document.getElementById("txtNomineeName").value="";
			document.getElementById("txtBirthDateOfNominee").value="";
			document.getElementById("txtPercentShare").value="";
			document.getElementById("cmbRelationship").value="";
			document.getElementById("txtNomAddress1").value="";
		}
	}


function deleteRow() {  

	serialNo = serialNo - 1;
	var current = window.event.srcElement;
	var TD = current.parentElement;
	var TR = TD.parentElement;
	var lArrAllTds = TR.childNodes;
	
	var currentShare = lArrAllTds[3].childNodes[0].value;
	totalNomineeShareAdded = totalNomineeShareAdded - Number(currentShare) ;
    while ( (current = current.parentElement)  && current.tagName !="TR");
         current.parentElement.removeChild(current);

    var relationToBeRemoved =  lArrAllTds[4].childNodes[0].value;
   	if(relationToBeRemoved.trim() == 'Mother')
   	{
   		motherAdded = false;
   	}
   	if(relationToBeRemoved.trim() == 'Father')
   	{
   		fatherAdded = false;
   	}
   	if(relationToBeRemoved.trim() == 'Spouse')
   	{
   		spouseAdded = false;
   	}
}  
function calculatePercentShare()
{
	var lTotalPercentShare=0;
	var lListPercentShare = document.getElementsByName("txtNomPerShare");

	var lListPercentShareLength = lListPercentShare.length;
	for(i=0;i<lListPercentShareLength;i++)
	{
		lTotalPercentShare = Number(lTotalPercentShare) + Number(lListPercentShare[i].value) ;
	}
	
	if(lTotalPercentShare != 100)
	{
		if(serialNo>=1)
		{
		alert("Percent Share total should be equal to 100.So Nominee Details will not be saved.");
		}
		return false;
	}
	else
	{
		return true;
	}
}
function submitNomineeDtls(empId,flag)
{
	showProgressbar();
	var emp_Id=empId;
	saveOrForwardFlag=flag;
	if(calculatePercentShare())
		{
			saveNomineeDtls(emp_Id,flag);
		}
	else
		{
			hideProgressbar();
		}
}

function saveNomineeDtls(emp_Id,flag)
{
	var empId=emp_Id;
	var lNomineeName="";
	var lAddress1="";
	var lDob="";
	var lPercentShare="";
	var lRelationship="";
	var lEmployeeId=document.getElementById("empID").value.trim();
	var txtDdoCode=document.getElementById("txtDdoCode").value.trim();
	designationId = document.getElementById("lStrDesignation").value.trim();
	typeOfChanges = document.getElementById("lStrChangesType").value.trim();
	dcpsHstChangesId = document.getElementById("dcpsHstChangesId").value.trim();
	designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();

	var txtAuthorityLetterNo = document.getElementById("txtAuthorityLetterNo").value.trim() ;
	var txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value.trim() ;

	/*		if(nomineeDtlsChangedOrNotFlag == false)
			{
				alert('You have not changed any details.');
				return false ;
			}
	*/

	if(flag==2)
		{
			if(txtAuthorityLetterNo == "" || txtAuthorityLetterDate=="")
			{
				alert('Please fill the Authority Details.');
				hideProgressbar();
				return false ;
			}
		}


	//Name
	var lListNomineeNames = document.getElementsByName("txtNomName");
	var lListNomineeNamesLength = lListNomineeNames.length;
	for(i=0;i<lListNomineeNamesLength;i++)
	{
		lNomineeName = lNomineeName + lListNomineeNames[i].value + "~";
	}
	
	//Address 1
	var lListAddress1 = document.getElementsByName("txtNomAddr1");
	var lListAddress1Length =lListAddress1.length;
	for(k=0;k<lListAddress1Length;k++)
	{
		lAddress1 = lAddress1 + lListAddress1[k].value + "~";
	}

	//DOB
	var lListDOB = document.getElementsByName("txtNomDOB");
	var lListDOBLength =lListDOB.length;
	for(k=0;k<lListDOBLength;k++)
	{
		lDob = lDob + lListDOB[k].value + "~";
	}
	//Percent Share
	var lListPercentShare = document.getElementsByName("txtNomPerShare");
	var lListPercentShareLength = lListPercentShare.length;
	for(j=0;j<lListPercentShareLength;j++)
	{
		lPercentShare = lPercentShare + lListPercentShare[j].value + "~";
	}
	
	//Relationship
	var lListRelationship = document.getElementsByName("txtNomRelationship");
	var lListRelationshipLength = lListRelationship.length;
	for(l=0;l<lListRelationshipLength;l++)
	{
		lRelationship = lRelationship + lListRelationship[l].value + "~";
	}

	var uri = "ifms.htm?actionFlag=updateNomineeDtls&lNomineeName="+ lNomineeName
				+ "&lAddress1=" + lAddress1 
	            + "&lDob=" + lDob 
			    + "&lPercentShare=" + lPercentShare 
			    + "&lRelationship=" + lRelationship 
			    + "&empId=" + empId 
			    + "&txtDdoCode="+txtDdoCode + "&txtAuthorityLetterNo=" + txtAuthorityLetterNo
			    + "&txtAuthorityLetterDate=" + txtAuthorityLetterDate
			    + "&change=" + "NomineeDetails";

	uri = uri + "&dcpsHstChangesId="+dcpsHstChangesId;

	SaveDataUsingAjaxForNominee(uri);
}

function SaveDataUsingAjaxForNominee(uri) {
	
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		hideProgressbar();
		return;
	}

	xmlHttp.onreadystatechange = saveDataStateChangedForNominee;
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}
function saveDataStateChangedForNominee() {

	if (xmlHttp.readyState == 4) {

		if (xmlHttp.status == 200) {
			
			hideProgressbar();
			XMLDoc = xmlHttp.responseXML.documentElement;
			
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var test_Id = XmlHiddenValues[0].childNodes[0].text;
			var dcpsChangesId =  XmlHiddenValues[0].childNodes[1].text;
			var User =document.getElementById("User").value ; 
			
			if (test_Id) {
				
				if(saveOrForwardFlag==1)
				{
					if(dcpsHstChangesId == "")
					{
						alert('Nominee Details have been successfully changed and saved.');
						self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
					}
					else
					{
						alert('Nominee Details have been successfully changed and saved.');
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
								var success_flag = XmlHiddenValuesNew[0].childNodes[0].text;
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
}

</script>
<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.EXISTINGDETAILS" bundle="${dcpsLables}"/></b> </legend>
		
		<c:choose>
		<c:when test="${NOMINEESLIST == null}">
			<input type="hidden" id="hidTotalPercentShare" value="0"/>
		</c:when>
		<c:otherwise>
			<input type="hidden" id="hidTotalPercentShare" value="100"/>
		</c:otherwise>
		</c:choose>
		

	<table>
		<tr>&nbsp</tr>
		<tr>&nbsp</tr>
	</table>
	<div style="padding-left:200px;" >
	<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:85%; height:120px; overflow: scroll; overflow-x:scroll; overflow-y:scroll; ">
	<table align="center" width="95%" >
	
	<tr>
		<td  align="center"><b>Sr.No</b></td>
		<td  align="center"><b>Nominee Name</b></td>
		<td  align="center"><b>Date Of Birth</b></td>
		<td  align="center"><b>%Share</b></td>
		<td  align="center"><b>Relationship</b></td>
		<td  align="center" style="display:none"><b>Address</b></td>
		<td  align="center" style="display:none"><b>Delete Nominee</b></td>
	</tr>
		<c:choose>
					<c:when test="${NOMINEESLISTMST !=null}">
						
						<c:forEach var="NomineeVO" items="${NOMINEESLISTMST}" varStatus="Counter">
						<tr>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="5" style="text-align: center" name="txtNomineeSerialNoValueMst" id="txtNomineeSerialNoValueMst${Counter.index+1}"  value="${Counter.index+1}" readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
					 	<input type="text" style="border: none;" size="20" style="text-align: center" name="txtNameValueMst" id="txtNameValueMst${Counter.index+1}"  value="${NomineeVO.name}"  readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="20" style="text-align: center" name="txtDateOfBirthValueMst"   id="txtDateOfBirthValueMst${Counter.index+1}"  onkeypress="numberFormat(this);digitFormat(this);dateFormat(this);"   maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${NomineeVO.dob}"/>" readonly="readonly"/>
						</td>
						
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="5" style="text-align: center" name="txtPercentShareValueMst" id="txtPercentShareValueMst${Counter.index+1}"  value="${NomineeVO.share}"  readonly="readonly"/>
						</td>
						
						
						<td class="tds" align="center">
						<input type="text" size="10" style="border: none;" style="text-align: center" name="txtRelationshipValueMst" id="txtRelationshipValueMst${Counter.index+1}"  size="20" onkeypress="numberFormat(this);"   style="width:60px" value="${NomineeVO.rlt}" readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;display: none" size="5" style="text-align: center" name="txtAddress1ValueMst" id="txtAddress1ValueMst${Counter.index+1}"  value="${NomineeVO.address1}"  readonly="readonly"/>
							<input type="hidden" name="txtNomNameMst" id="txtNomNameMst${Counter.index}" value="${NomineeVO.name}"/>
							<input type="hidden" name="txtNomAddr1Mst" id="txtNomAddr1Mst${Counter.index}" value="${NomineeVO.address1}"/>
							<input type="hidden" name="txtNomAddr2Mst"  id="txtNomAddr2Mst${Counter.index}" value="${NomineeVO.address2}"/>
							<input type="hidden" name="txtNomDOBMst" id="txtNomDOBMst${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${NomineeVO.dob}"/>"/>
							<input type="hidden" name="txtNomPerShareMst" id="txtNomPerShareMst${Counter.index}"  value="${NomineeVO.share}"/>
							<input type="hidden" name="txtNomRelationshipMst" id="txtNomRelationshipMst${Counter.index}" value="${NomineeVO.rlt}"/>
						</td>
						
						<td class="tds" align="center" ${varImageDisabled}>
						<a href="#" onclick="UpdateMstDetails('${Counter.index+1}')">Update</a>
						</td>
						
						</tr>
						
					<script>
					//	document.getElementById("hidGridSize").value=Number('${Counter.index}') + 1;
					//	serialNo=Number('${Counter.index}') + 2;
					</script>
					</c:forEach>
					</c:when>
					
		</c:choose> 		
		

	</table>
	
	</fieldset>
	
	<br/>
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CHANGEDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>	
	<input type="hidden" id="txtDdoCode" name="txtDdoCode" value="${resValue.DDOCODE}"/>
	<input type="hidden" id="lStrDesignation" name="lStrDesignation" value="${resValue.lStrDesignation}"/>
    <input type="hidden" id="lStrChangesType" name="lStrChangesType" value="${resValue.lStrChangesType}"/>
    
	<c:if test="${resValue.UserType == 'DDOAsst'}">
		<table width="100%" align="center" cellpadding="4" cellspacing="4">
		<tr>
				<td width="15%" align="left"><fmt:message key="CMN.NOMNAME"
				bundle="${dcpsLables}"></fmt:message></td>
			    <td width="20%" align="left"><input type="text"
				id="txtNomineeName" size="30"
				name="txtNomineeName" style="text-transform: uppercase" onblur="isName(txtNomineeName,'This field should not contain any special characters or digits.')" 
				value=""  /></td>
				
		</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.ADDRESS"
				bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%"> 
					<input type="text" name='txtNomAddress1' id="txtNomAddress1" value="" ${varReadOnly} />
					
					<label class="mandatoryindicator" ${varLabelDisabled}>*</label>
				</td>	
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.DOB"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="30%" align="left"><input type="hidden"
				name="currDate" id="currDate" value="${resValue.lDtCurDate}" />  <input
				type="text" name="txtBirthDateOfNominee" id="txtBirthDateOfNominee"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtBirthDateOfNominee); compareDates(this,currDate,'Date of Birth should be less than current date.','<');" value="" /> 
				<img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDateOfNominee",375,570)'
				style="cursor: pointer;" /></td>
			<td width="10%" align="left"><fmt:message key="CMN.PERCENTSHARE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="40%" align="left"><input type="text"
				id="txtPercentShare" size="20" name="txtPercentShare" value=""
				onkeypress="digitFormat(this);" onblur="chckPercentShare(txtPercentShare);" /></td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.RELATIONSHIP"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="30%" align="left"><select name="cmbRelationship"
				id="cmbRelationship" style="width: 60%" onChange="" >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="searchLst" items="${resValue.listRelationship}">
					<option value="${searchLst.lookupDesc}"><c:out
						value="${searchLst.lookupDesc}"></c:out></option>
				</c:forEach>
			</select></td>
		</tr>
	</table>
	<table width="100%" align="center" height="10%" cellpadding="0"
		cellspacing="0">
		<tr></tr>
		<tr></tr>
		<tr>
			<td width="100%" align="center" > <hdiits:button
				name="btnAdd"  id="btnAdd" type="button" captionid="BTN.ADD"
				bundle="${dcpsLables}" onclick="addRow();"/> </td>
				<td><input type="hidden"
				name="empID" id="empID" value="${resValue.EmployeeID}"/></td>
		</tr>
	</table>
	
	</c:if>
	
	<table>
		<tr>&nbsp</tr>
		<tr>&nbsp</tr>
	</table>
	<div style="padding-left:200px;" >
	<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:85%; height:120px; overflow: scroll; overflow-x:scroll; overflow-y:scroll; ">
	<table id="displayTableForNomineeDtls" align="center" width="95%" >
	
	<tr>
		<td  align="center"><b>Sr.No</b></td>
		<td  align="center"><b>Nominee Name</b></td>
		<td  align="center"><b>Date Of Birth</b></td>
		<td  align="center"><b>%Share</b></td>
		<td  align="center"><b>Relationship</b></td>
		<td  align="center" style="display:none"><b>Delete Nominee</b></td>
	</tr>
		<c:choose>
					<c:when test="${NOMINEESLIST !=null}">
						
						<c:forEach var="NomineeVO" items="${NOMINEESLIST}" varStatus="Counter">
						<tr>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="5" style="text-align: center" name="txtNomineeSerialNoValue" id="txtNomineeSerialNoValue" class='${Counter.index+1}' value="${Counter.index+1}" readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="20" style="text-align: center" name="txtNameValue" id="txtNameValue" class='${Counter.index+1}' value="${NomineeVO.name}"  readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="20" style="text-align: center" name="txtDateOfBirthValue"  class='${Counter.index+1}' id="txtDateOfBirthValue"  onkeypress="numberFormat(this);digitFormat(this);dateFormat(this);"   maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${NomineeVO.dob}"/>" readonly="readonly"/>
						</td>
						
						
						<td class="tds" align="center">
						<input type="text" style="border: none;" size="5" style="text-align: center" name="txtPercentShareValue" id="txtPercentShareValue" class='${Counter.index+1}' value="${NomineeVO.share}"  readonly="readonly"/>
						</td>
						
						<td class="tds" align="center">
						<input type="text" size="10" style="border: none;" style="text-align: center" name="txtRelationshipValue${Counter.index}" id="txtRelationshipValue${Counter.index}"  class='${Counter.index+1}' size="20" onkeypress="numberFormat(this);"   style="width:60px" value="${NomineeVO.rlt}" readonly="readonly"/>
						</td>
						
						<td class="tds" align="center"  ${varImageDisabled}>
						<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRow();">
						<input type="hidden" name="txtNomName" class="${Counter.index+1}"  value="${NomineeVO.name}"/>
						<input type="hidden" name="txtNomAddr1" class="${Counter.index+1}"  value="${NomineeVO.address1}"/>
						<input type="hidden" name="txtNomDOB" class="${Counter.index+1}"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${NomineeVO.dob}"/>"/>
						<input type="hidden" name="txtNomPerShare" class="${Counter.index+1}"  value="${NomineeVO.share}"/>
						<input type="hidden" name="txtNomRelationship" class="${Counter.index+1}"  value="${NomineeVO.rlt}"/>
						</td>
						</tr>
						
					<script>
					//	document.getElementById("hidGridSize").value=Number('${Counter.index}') + 1;
						serialNo=Number('${Counter.index}') + 1;
					</script>
					</c:forEach>
					</c:when>
					
		</c:choose> 		
		

	</table>
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
		<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${resValue.ForwardToPost}"/>
		
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
									onclick="submitNomineeDtls('${EMPVOMST.dcpsEmpId}',1);" />
								
								<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
								<hdiits:button name="BTN.FORWARD" id="btnForwardForUpdateTotally" type="button"
									captionid="BTN.FORWARD" bundle="${dcpsLables}" onclick="submitNomineeDtls('${EMPVOMST.dcpsEmpId}',2);" />
							</c:when>
							<c:otherwise>
								<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnfromChanges();"/>
								<hdiits:button
									name="btnApprovePersonalDtls" id="btnApprovePersonalDtls" type="button"
									captionid="BTN.APPROVE" bundle="${dcpsLables}"
									onclick="approveNomineeDetails();" />
								
								<hdiits:button name="BTN.REJECT" id="btnRejectPersonalDtls" type="button"
									captionid="BTN.REJECT" bundle="${dcpsLables}" onclick= "rejectNomineeDetails();" />
							</c:otherwise>
						</c:choose>
			</div>
			
<script>
totalNomineeShareAdded = Number(document.getElementById("hidTotalPercentShare").value);
</script>
			
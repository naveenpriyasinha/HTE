<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/NewRegistrationForm.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/login/md5.js"/>"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVOMST" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjRltDcpsPayrollEmp}"></c:set>

<c:set var="EMPVO" value="${resValue.lObjHstDcpsOtherChanges}"></c:set>

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

function popUpIFSCCode() {


	//alert("hi i m in populating ifsc code");
	if(document.getElementById("cmbBranchName").value == -1)
	{
		document.getElementById("txtIFSCCode").value = '';
	}

	var cmbBranchName = document.getElementById("cmbBranchName").value;
	var uri = "ifms.htm?actionFlag=displayIFSCCodeForBranch";
	var url = "cmbBranchName="+ cmbBranchName;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
				getDataStateChangedForPopIFSCode(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForPopIFSCode(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var IFSCCode = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (IFSCCode != 'null') {
		document.getElementById("txtIFSCCode").value = IFSCCode;
	}
}
function updateOrForwardBankDetails(emp_id,flag){
	showProgressbarForBankOtherDtls();
var bankId = document.getElementById("cmbBankName").value.trim();

var branchId = document.getElementById("cmbBranchName").value.trim();

var ifscCode= document.getElementById("txtbankAccountNo").value.trim();

var accountNo= document.getElementById("txtbankAccountNo").value.trim();

var genLevelId='100';
var genAction='updB';
var genRandomNo=Math.floor(Math.random()*10001);
var genTokenNo=genLevelId+genAction+genRandomNo;
var genFinalTokenNo = CryptoJS.MD5(genTokenNo);
var randChar = randomCharacter();
genFinalTokenNo=genFinalTokenNo+randChar;
//document.getElementById("genLevel").value=genLevelId;
//document.getElementById("genTokenNo").value=genTokenNo;
//document.getElementById("genRandomNo").value=genRandomNo;
//ended by kinjal for Token Number

if ((document.getElementById("cmbBankName").value == -1) || 
		(document.getElementById("cmbBranchName").value == -1) || (document.getElementById("txtbankAccountNo").value == ' ')){

	alert('Please fill all the details.');
}
else {
	var uri = "ifms.htm?actionFlag=bankUpdate";
	var url = "emp_id="+emp_id+"&bankId="+bankId+"&branchId="+branchId+"&ifscCode="+ifscCode+"&accountNo="+accountNo+"&genTokenNo="+genFinalTokenNo+"&genRandomNo="+genRandomNo;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForUpdateBank(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );

}
}
function getDataStateChangedForUpdateBank(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(successFlag)
	{
		hideProgressbarForBankOtherDtls();
		alert("Bank Details Updated Successfully");
		self.location.href="ifms.htm?actionFlag=getEmployeeListForBankDetailsUpdate";
	}
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

</fieldset>
<br/>

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data"
	validate="true" method="post" >
<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CHANGEDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
<%-- ADDED BY kinjal FOR IMPLEMENTING TOKEN NUmber --%>
<input type="hidden" name="genLevel" id="genLevel"/>
<input type="hidden" name="genTokenNo" id="genTokenNo"/>
<input type="hidden" name="genRandomNo" id="genRandomNo"/>
<%-- ENDED BY kinjal FOR IMPLEMENTING TOKEN number --%>
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
				bundle="${dcpsLables}"></fmt:message></td>
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
				/></td>
				
			<td width="15%" align="left"><fmt:message key="CMN.IFSCODE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtIFSCCode"
				style="text-transform: uppercase" size="31" name="txtIFSCCode" readonly="readonly"
				/></td>
		</tr>
	</table>
	
	<!--  Added overs by Vihan for Change PF Details in Change Details  -->
	
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
		
		<div align="center">
			
					<hdiits:button
					name="btnUpdatedataForUpdateTotally" id="btnUpdatedataForUpdateTotally" type="button"
					captionid="BTN.UPDATE" bundle="${dcpsLables}"
		onclick="updateOrForwardBankDetails('${EMPVOMST.dcpsEmpId}',1);" />
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


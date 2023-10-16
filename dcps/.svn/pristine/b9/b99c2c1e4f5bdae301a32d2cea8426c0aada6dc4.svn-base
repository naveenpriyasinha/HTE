<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DdoDetails" value="${resValue.DdoDetails}" />
<c:set var="instituteType" value="${resValue.instituteType}" />

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request"/>
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/ddoinformation.js"></script>
<style>
input[type="button" i]:disabled {
    pointer-events: none;
    opacity: 0.6;
}
</style>

<hdiits:form name="DDOInfo" id="DDOInfo" encType="multipart/form-data" validate="true" method="post"   >
<input type = "hidden" id = "cmbDdoCode" name="cmbDdoCode" value = "${resValue.cmbDdoCode }" />

<fieldset class="tabstyle" >
<legend>
	<b><fmt:message key="CMN.PARENTDEPARTMENT" bundle="${dcpsLables}"></fmt:message></b>
</legend>
<table width="100%"   align="center" cellpadding="4" cellspacing="4" >
	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.ADMINDEPT" bundle="${dcpsLables}"></fmt:message>
		
		</td>
		<td width="35%">		
			<input type="text" id="txtAdminDept" style="text-transform: uppercase;" size="48" maxlength="90" disabled="disabled"  name="txtAdminDept" value="${resValue.AdminDept }" />
			
		</td>
	
		<td align="left" width="15%" >
		<fmt:message key="CMN.FIELDHODDEPT" bundle="${dcpsLables}"></fmt:message>
		
		</td>
		<td width="35%">		
			<input type="text" id="txtFieldDept" style="text-transform: uppercase;" size="40%" maxlength="90" disabled="disabled"  name="txtFieldDept" value="${resValue.FieldDept}" /><!-- change size from 50% to 40% by Pratik 02-08-23 -->
			
		</td>
	</tr>
	
		<tr>
		
		<td id="instituteType1" width="15%" align="left">Institute Type </td>
		<td width="35%">
		<select name="instituteType" id="instituteType" style="width:360px" disabled="disabled">
				<option  value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option> 
				<c:forEach var="instituteType" items="${instituteType}">
				<c:choose>
         						<c:when test="${DdoDetails!=null}">
         							<c:choose>
         							<c:when test="${instituteType[0]==DdoDetails.instituteType}">
         								<option value="${instituteType[0]}" selected="selected" ><c:out value="${instituteType[1]}"/></option>
         							</c:when>
         							<c:otherwise>
         								<option value="${instituteType[0]}" title="${instituteType[0]}"><c:out value="${instituteType[1]}"/></option>
         							</c:otherwise>
         							</c:choose>
         						</c:when>
         					
         						<c:otherwise>
         								<option value="${instituteType[0]}" title="${instituteType[0]}"><c:out value="${instituteType[1]}"/></option>
         						</c:otherwise>
         		</c:choose>		
				</c:forEach>
		</select>
		</td>
		<td colspan="2" width="50%">&nbsp;
		</td>
		</tr>


</table>
</fieldset>
<br/>
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.SIGNINGAUTHO" bundle="${dcpsLables}"></fmt:message></b>
</legend>


<table width="100%" align="center" cellpadding="4" cellspacing="4">
	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.NAME" bundle="${dcpsLables}"></fmt:message>
		
		</td>
		<td  width="35%">
			<input type="text" id="txtDDOName" style="text-transform: uppercase" size="48" maxlength="99" name="txtDDOName" disabled="disabled"  value="${DdoDetails.ddoPersonalName}" onkeypress="nameFormat(this);"/>
			<label class="mandatoryindicator">*</label>
		</td>
	
		<td width="15%" align="left"><fmt:message key="CMN.DESIGNATION" bundle="${dcpsLables}"></fmt:message>
		
		</td>
		<td align="left" width="35%"> 		
			<select name="cmbDesignation" id="cmbDesignation" style="width:360px" disabled="disabled">
				<option  value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option> 
				<c:forEach var="desg" items="${resValue.lLstDesignation}">					
							<c:choose>
         						<c:when test="${DdoDetails!=null}">
         							<c:choose>
         							<c:when test="${desg.id==DdoDetails.designCode}">
         								<option value="${desg.id}" selected="selected" ><c:out value="${desg.desc}"/></option>
         							</c:when>
         							<c:otherwise>
         								<option value="${desg.id}" title="${desg.desc}"><c:out value="${desg.desc}"/></option>
         							</c:otherwise>
         							</c:choose>
         						</c:when>
         					
         						<c:otherwise>
         								<option value="${desg.id}" title="${desg.desc}"><c:out value="${desg.desc}"/></option>
         						</c:otherwise>
         					</c:choose>							
				</c:forEach>
			</select><label class="mandatoryindicator">*</label>
			
		</td>
	</tr>
	<tr ${varLabelDisabled}>
			<td colspan = "2" align="left" style="font-size : smaller;font-style:italic"><fmt:message key="MSG.DDONAM" bundle="${dcpsLables}"></fmt:message></td>
			<td colspan = "2" >&nbsp;</td>
	</tr>
	<tr>
		<td width="15%" align="left" class="ddoinfowdate"><%-- added ddoinfowdate class by pratik 02-08-23 --%><fmt:message key="CMN.WEFDATE" bundle="${dcpsLables}"></fmt:message>
		
		</td>
		<td align="left" width="35%">
			<input type="hidden" name="currDate" id="currDate" value="${resValue.lDtCurDate}"/>
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${DdoDetails.startDate}" var="wefDate"/>
			<input type="text" name="txtWEFDate" id="txtWEFDate" size="48" maxlength="10" disabled="disabled" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);compareDates(this,currDate,'WEFDate cannot be greater than current date','<');" value="${wefDate }" />
			<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtWEFDate",375,570)' style="cursor: pointer;" }/>
			<label class="mandatoryindicator">*</label>
			<fmt:message key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message>
		</td>
		<td width="15%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
	</tr>
	
	
	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.TANNO" bundle="${dcpsLables}"></fmt:message>
		
		</td>
		<td align="left" width="35%">
			<input type="text" id="txtTANNo" style="text-transform: uppercase" size="48"  maxlength="10" name="txtTANNo" disabled="disabled" value="${DdoDetails.tanNo}" onblur="tanNoValidation();" />
		</td>	
		
		<td width="15%" align="left"><fmt:message key="CMN.ITOWARDCIR" bundle="${dcpsLables}"></fmt:message>
		
		</td>
		<td align="left" width="35%">
			<input type="text" id="txtITWardCircle" style="text-transform: uppercase" size="48" maxlength="10" name="txtITWardCircle" disabled="disabled" value="${DdoDetails.itaWardNo}" />
		</td>
	</tr>	
	
	
</table>
</fieldset>
<br/>
<fieldset class="tabstyle">
	<legend>
		<b><fmt:message key="CMN.BANKDETAILS" bundle="${dcpsLables}"></fmt:message></b>
	</legend>
	<table width="100%" align="center" cellpadding="4" cellspacing="4">
	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.BANKNAME" bundle="${dcpsLables}"></fmt:message>
		
		<td width="35%" align="left">		
			<select name="cmbBankName" id="cmbBankName" style="width:360px" disabled="disabled" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
				
					<c:forEach var="bankName" items="${resValue.BANKNAMES}" >
						<c:choose>
         						<c:when test="${DdoDetails!=null}">
         							<c:choose>
         							<c:when test="${bankName.id==DdoDetails.bankName}">
         								<option value="${bankName.id}" selected="Selected"><c:out value="${bankName.desc}"/></option>
         							</c:when>
         							<c:otherwise>
         								<option value="${bankName.id}" title="${bankName.desc}"><c:out value="${bankName.desc}"/></option>
         							</c:otherwise>
         							</c:choose>
         						</c:when>
         					
         						<c:otherwise>
         								<option value="${bankName.id}" title="${bankName.desc}"><c:out value="${bankName.desc}"/></option>
         						</c:otherwise>
         					</c:choose>	
						
					</c:forEach>
				
			</select>
			<label class="mandatoryindicator">*</label></td>
			
		<td width="15%" align="left"><fmt:message key="CMN.BRANCHNAME" bundle="${dcpsLables}"></fmt:message>
		</td>
		<td align="left" width="35%">		
			<select name="cmbBranchName" id="cmbBranchName" style="width:360px"  onChange="popUpIFSCCodeForBsrCode();" onblur="popUpIFSCCodeForBsrCode();" disabled="disabled" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
				<c:forEach var="branchName" items="${resValue.BRANCHNAMES}" >
						<c:choose>
         						<c:when test="${DdoDetails!=null}">
         							<c:choose>
         							<c:when test="${branchName.id==DdoDetails.branchName}">
         								<option value="${branchName.id}" selected="Selected"><c:out value="${branchName.desc}"/></option>
         							</c:when>
         							<c:otherwise>
         								<option value="${branchName.id}" title="${branchName.desc}"><c:out value="${branchName.desc}"/></option>
         							</c:otherwise>
         							</c:choose>
         						</c:when>
         					
         						<c:otherwise>
         								<option value="${branchName.id}" title="${branchName.desc}"><c:out value="${branchName.desc}"/></option>
         						</c:otherwise>
         					</c:choose>	
						
					</c:forEach>
			</select>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	
	<tr>	
		<td width="15%" align="left"><fmt:message key="CMN.IFSCODE" bundle="${dcpsLables}"></fmt:message></td>
		<td align="left" width="35%">
			<input type="text" id="txtIFSCCode" style="text-transform: uppercase;" disabled="disabled" readonly="readonly" size="48" maxlength="20" name="txtIFSCCode" value="${DdoDetails.ifsCode }" />
		</td>
	
		<td width="15%" align="left"><fmt:message key="CNM.ACCNO" bundle="${dcpsLables}"></fmt:message>
		</td>
		<td align="left" width="35%">
			<input type="text" id="txtAccountNo" style="text-transform: uppercase" size="48" maxlength="20" name="txtAccountNo" onkeypress="digitFormat(this);" disabled="disabled" onblur="validateAccLength();checkAccountno();" value="${DdoDetails.accountNo }" />
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.REMARKS" bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" width="35%">
			<input type="text" id="txtRemarks" style="text-transform: uppercase" size="48" maxlength="100" name="txtRemarks" disabled="disabled" value="${DdoDetails.remarks }" />
			</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>	
 </table>	
</fieldset><br/>
<table width="20%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="50%" align="center">
			<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="SaveDataAfterValidation();"/>
		</td>
		
		<td width="50%" align="center">
			<hdiits:button name="btnedit" id="btnedit" type="button"  captionid="BTN.EDIT" bundle="${dcpsLables}" onclick="tan(); return EdisValidation(); "/>
		</td>
						
		<td width="50%" align="center">
			<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnPage();"/>
		</td>
	</tr>	
</table>
</hdiits:form>


<script>

var txtAdminDept = document.getElementById("txtAdminDept").value;
if(txtAdminDept.length == 0)
{
	alert("The details are not freezed by System Admin. Please get the data freezed by the System Admin to input the information.");
	self.location.href="ifms.htm?actionFlag=validateLogin";
}

function tan(){
	//alert("hiiii");
 var tan = document.getElementById("txtTANNo").value;
 //alert("tan = "+tan);
 if(tan==null || tan==""){
	 //alert("hiiii1");
	 document.getElementById("txtTANNo").disabled = false;
 }else{
	 //alert("hiiii2");
	 document.getElementById("txtTANNo").disabled = true;
 }
} 

function checkAccountno(){
	//alert("hiii");
	var accNo =  document.getElementById("txtbankAccountNo").value;
	alert("accNo = "+accNo.length);
	if(accNo<=0){
		alert("Please Enter Valid Account Number");
		document.getElementById("txtbankAccountNo").value = "";
		return false;
	}
	return true;
}


function validateAccLength()
{
	//alert('inside validateUIDUniqe');
	var accNo=document.getElementById("txtAccountNo").value;
	var bankId=document.getElementById("cmbBankName").value;
	var e = document.getElementById("cmbBankName");
	var bankName = e.options[e.selectedIndex].text;
	//alert('accNo '+accNo+'bankId '+bankId+'bankName '+bankName );
	if(Number(accNo)==0){
		alert('All digit of account number can not be zero.');
		document.getElementById("txtAccountNo").value='';
		return false;
	}
	if(bankId==-1)
	{
		alert('Please select Bank.');
		
		return false;
	}
	else if(accNo=="" || accNo==null)
	{
		alert('Please enter account number.');
		return false;
	}	
	else if(!accNo.match(/^\d+$/)){
		alert('Please enter only numbers in account number field.');
		document.getElementById("txtAccountNo").value='';
		return false;
	}
	else
	{
		
		
		var uri = 'ifms.htm?actionFlag=validateAccNo';
		var url = 'accNo='+accNo+'&bankId='+bankId;

		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						checkAccNo(myAjax,accNo,bankId,bankName);
						
					},
			        onFailure: function()
			        			{ 
	  						alert('Something went wrong...');
	  					} 
			          } 
	);
	}
}
function checkAccNo(myAjax,accNo,bankId,bankName){
	//alert("hiii checdksdsd");
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var accNoLength = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert("accNoLength from db: "+accNoLength);
	var lengthAcc=accNo.length;
	//alert('user account number length: '+lengthAcc);

	if(lengthAcc!=accNoLength)
	{
		alert('Entered account number length is either exceeding or less than the account number length for bank: '+bankName+'. The account number length for bank should be: '+accNoLength+'. Please enter correct account number.');
		//alert('Entered account number length is either exceeding or less than the account number length for bank: '+bankName+'. Please enter correct account number.');
		document.getElementById("txtAccountNo").value='';
		return false;
	}

	return false;
}
</script>
<ajax:select 
		source="cmbBankName" 
		target="cmbBranchName" 
		eventType="change"
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popBrnchNms&requestFor=bsrCodes"
		parameters="cmbBankName={cmbBankName}"
		postFunction="ClearIfsCode">
</ajax:select>
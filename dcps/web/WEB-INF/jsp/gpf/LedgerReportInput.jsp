<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>

<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script type="text/javascript">
var EmpName="";

function generateReport()
{
	var year = document.getElementById("cmbYear").value;	
	
	if(document.getElementById("cmbYear").value == -1)
	{
		alert("Please Select Financial Year");
		document.getElementById("cmbYear").focus();
		return false;
	}
	
	if((document.getElementById("txtEmployeeName").value != "") && (document.getElementById("txtGpfAccNo").value != ""))
	{
		var name = document.getElementById("txtEmployeeName").value;
		var gpfAccNo = document.getElementById("txtGpfAccNo").value;		
		var uri="ifms.htm?actionFlag=reportService&reportCode=800004&action=generateReport&name="+name+"&gpfAccountNo="+gpfAccNo+"&financialYear="+year+"&asPopup=TRUE";
	}
	else if(document.getElementById("txtEmployeeName").value != "")
    {
		var name = document.getElementById("txtEmployeeName").value;
		var uri="ifms.htm?actionFlag=reportService&reportCode=800004&action=generateReport&name="+name+"&financialYear="+year+"&asPopup=TRUE";		
    }
	else if(document.getElementById("txtGpfAccNo").value != "")
	{
		var gpfAccNo = document.getElementById("txtGpfAccNo").value;
		var uri="ifms.htm?actionFlag=reportService&reportCode=800004&action=generateReport&gpfAccountNo="+gpfAccNo+"&financialYear="+year+"&asPopup=TRUE";
	}	
	else{
		alert("Please enter Employee Name or GPF Acc No.");
		return false;
	}
		 
	window_new_update(uri);
}

function generateReportForUser()
{	
	var year = document.getElementById("cmbYear").value;
	
	var uri="ifms.htm?actionFlag=reportService&reportCode=800004&action=generateReport&name="+EmpName+"&financialYear="+year+"&asPopup=TRUE";
	window_new_update(uri);
}

function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "LedgerReport", urlstyle);
}


function getEmployeeName()
{	
	var uri = 'ifms.htm?actionFlag=loadLedgerUserDtls';
	var url = '';
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	getResponseEmpName(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function getResponseEmpName(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	EmpName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	generateReportForUser();
}
</script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="userType" value="${resValue.userType}"></c:set>

<hdiits:form name="frmLedger" id="frmLedger" validate="true">

<fieldset class="tabstyle"><legend><fmt:message key="CMN.REPORTDTLS" bundle="${gpfLabels}"></fmt:message></legend>
<table>
	<tr id="trEmpName">
		<td width="25%">
			<fmt:message key="CMN.EMPNAME" bundle="${gpfLabels}" />
		</td>
		<td width="25%">
			<input type="text" id="txtEmployeeName" size="30" name="txtEmployeeName" style="text-transform: uppercase"/>
			<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
		</td>
	</tr>
	<tr id="trGpfAccNo">
		<td width="25%">
			<fmt:message key="CMN.GPFACNO" bundle="${gpfLabels}" />
		</td>
		<td width="25%">
			<input type="text" id="txtGpfAccNo" size="30" name="txtGpfAccNo" />		
		</td>				
	</tr>
	<tr>
		<td width="25%">
			<fmt:message key="CMN.FINYEAR" bundle="${gpfLabels}" />
		</td>
		<td>
			<select name="cmbYear" id="cmbYear">
		 		<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="year" items="${resValue.lLstYears}">
						<option value="${year.id}"><c:out value="${year.desc}"></c:out></option>
					</c:forEach>
		   </select>
		   <label class="mandatoryindicator">*</label>
		</td>
	</tr>	
</table>
<center>
	<c:if test="${userType == 'DDO'}">
		<hdiits:button name="btnGenerateReport" id="btnGenerateReport" type="button" captionid="BTN.GENREPORT" bundle="${gpfLabels}" onclick="generateReport();" style="width: 10%"/>
	</c:if>
	<c:if test="${userType == 'user'}">
		<hdiits:button name="btnGenerateReportUsr" id="btnGenerateReportUsr" type="button" captionid="BTN.GENREPORT" bundle="${gpfLabels}" onclick="getEmployeeName();" style="width: 10%"/>
	</c:if>	
</center>
</fieldset>
</hdiits:form>

<c:if test="${userType == 'user'}">
<script>
	document.getElementById("trEmpName").style.display = 'none';
	document.getElementById("trGpfAccNo").style.display = 'none';
</script>
</c:if>

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoComplete&userType=DDO"
	parameters="searchKey={txtEmployeeName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
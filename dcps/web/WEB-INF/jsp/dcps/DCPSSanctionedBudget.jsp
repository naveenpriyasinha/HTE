<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>

<script>
var addedBudgetList = new Array(); 
var serialNo=0;
function AddBudgetData()
{


	if(!chkEmpty(document.getElementById('txtSanctionedDate'),'Sanctioned Date'))
	{
		return false; 
	}
	
	if(!chkEmpty(document.getElementById('cmbOrgType'),'Organization Type'))
	{
		return false;
	}
	if(!chkEmpty(document.getElementById('cmbFinYear'),'Financial Year'))
	{
		return false;
	}
	if(!chkEmpty(document.getElementById('txtSanctionedBudget'),'Sanctioned Budget'))
	{
		return false;
	}
	
	
	
	SaveDataUsingAjax();
	
}
function ReturnPage()
{
	self.location.href="ifms.htm?actionFlag=validateLogin";
}
function SaveBudgetData()
{
	SaveDataUsingAjax();
	return true;
}
function SaveDataUsingAjax()
{
	var data = addedBudgetList.join("~");

	var uri = "ifms.htm?actionFlag=saveSanctionedBudgetData";

	var url = runForm(0);
	var url = uri + url;
	

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForSaveSancBudget(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function dataStateChangedForSaveSancBudget(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries  = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XMLEntries[0].childNodes[0].firstChild.nodeValue;
	var finyear = XMLEntries[0].childNodes[1].firstChild.nodeValue;
	
	if(successFlag)
	{
		alert("Record Entered Successfully");
		self.location.href = "ifms.htm?actionFlag=loadSanctionedBudgetForm&elementId=700049&cmbFinyear="+finyear;
	}
}
function populateBudgetDataforFinyear(){
	
	if(document.getElementById("cmbFinYear").value != -1 )
	{
		showProgressbar();
		var cmbFinyear  = document.getElementById("cmbFinYear").value.trim();
		var cmbOrgType  = document.getElementById("cmbOrgType").value.trim();
		var txtSchemeCode  = document.getElementById("txtSchemeCode").value.trim();
		self.location.href = "ifms.htm?actionFlag=loadSanctionedBudgetForm&elementId=700049&cmbFinyear="+cmbFinyear + "&cmbOrgType=" + cmbOrgType + "&txtSchemeCode="+txtSchemeCode;		
	}
	else{
		
	}
}
function populateSchemeCode() {
	
	
	var selectOption = document.getElementById('cmbOrgType').value;

	if(selectOption != -1 )
		{
			var xmlHttp = null;
			try {
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
		
			if (xmlHttp == null) {
				alert("Your browser does not support AJAX!");
				return;
			}
		
			var uri = "ifms.htm?actionFlag=popSchemeCode";
			
			url = uri + "&cmbOrgType=" + selectOption ;
		
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4) {
					if (xmlHttp.status == 200) {
						XMLDoc = xmlHttp.responseXML.documentElement;
		
						if (XMLDoc != null) {
		
							var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
							var schemeCode = XMLEntries[0].childNodes[0].text;
						
							document.getElementById('txtSchemeCode').value = schemeCode;
						}
					}
				}
			};
			xmlHttp.open("POST", url, true);
			xmlHttp.send(url);
		}
	else{
		document.getElementById("txtSchemeCode").value="";
	}
}
</script>


<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmDCPSSanctionedBudget" encType="multipart/form-data"
	validate="true" method="post">
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="sanctionedBudgetList" value="${resValue.SancBudgetList}" />

<input type="hidden"  name='txtDdoCode' id="txtDdoCode" style="text-align: left" value="${resValue.DDOCODE}" />

<br/>

<fieldset class="tabstyle">

<table id="table1" width="70%" align="left" cellpadding = "2" cellspacing = "2">	
	<tr>
		
		<td>
		
			<input type="radio" id="radioButtonType" name="radioButtonType" size = "5%" value="Credit" checked="checked" />
			<fmt:message key="DCPS.CREDIT" bundle="${DCPSLables}"></fmt:message>
			<label >  </label>
			<input type="radio" id="radioButtonType" name="radioButtonType" size = "5%" value="Debit	" />
			<fmt:message key="DCPS.DEBIT" bundle="${DCPSLables}"></fmt:message>
			
		</td>
	</tr>
	<tr>
		
		<td width="10%" align="left" ><fmt:message key="DCPS.ORGTYPE" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<select name="cmbOrgType" id="cmbOrgType" style="width:50%" maxlength="100" onchange="populateSchemeCode();">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="orgName" items="${resValue.lLstDepartment}" >
				<c:choose>
					<c:when test="${resValue.orgType!=null}">
					<c:choose>
						<c:when test="${resValue.orgType == orgName.id}">
							<option value="${orgName.id}" selected="selected"><c:out value="${orgName.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${orgName.id}" ><c:out value="${orgName.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
					</c:when>	
				<c:otherwise>
					<option value="${orgName.id}" ><c:out value="${orgName.desc}"></c:out></option>
				</c:otherwise>
				</c:choose>	
									         					
				</c:forEach>
			</select>
		</td>
		
	</tr>
	<tr>
		
		<td width="10%" align="left" ><fmt:message key="DCPS.SCHEMECODE" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
		<c:choose>
			<c:when test="${resValue.schemeCode!=null}">
				<input type="text"  size="20%" name='txtSchemeCode' readOnly="readOnly" id="txtSchemeCode" maxlength="15" value="${resValue.schemeCode}" style="text-align: left" />
			</c:when>	
			<c:otherwise>
				<input type="text"  size="20%" name='txtSchemeCode' id="txtSchemeCode" readOnly="readOnly"  maxlength="15" style="text-align: left" />
			</c:otherwise>
		</c:choose>	
			
		</td>
		
	</tr>
	<tr>
		
		<td width="10%" align="left" ><fmt:message key="DCPS.FINYEAR" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<select name="cmbFinYear" id="cmbFinYear" style="width:20%" onchange="populateBudgetDataforFinyear();">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="finYear" items="${resValue.lLstFinYear}">
				<c:choose>
					<c:when test="${resValue.finYear!=null}">
					<c:choose>
						<c:when test="${resValue.finYear == finYear.id}">
							<option value="${finYear.id}" selected="selected"><c:out value="${finYear.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${finYear.id}" ><c:out value="${finYear.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
					</c:when>	
				<c:otherwise>
					<option value="${finYear.id}" ><c:out value="${finYear.desc}"></c:out></option>
				</c:otherwise>
				</c:choose>	
			</c:forEach>
			</select>
		</td>
		
	</tr>
	
	<tr>
		
		<td width="10%" align="left" ><fmt:message key="DCPS.SANCDATE" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text" name="txtSanctionedDate" id="txtSanctionedDate" maxlength="12" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtSanctionedDate",375,570)' style="cursor: pointer;"/>
		</td>
		
	</tr>
	<tr>
		
		<td width="10%" align="left" ><fmt:message key="DCPS.SANCBUDGET" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="20%" name='txtSanctionedBudget' id="txtSanctionedBudget" maxlength="10" style="text-align: right" />
		</td>
	</tr>

	<tr>
		<td width="10%" align="right"><hdiits:button type="button" captionid="BTN.SAVE" bundle="${DCPSLables}" id="add" name="add" onclick="AddBudgetData()"></hdiits:button></td>
		<td width="30%" align="left"><hdiits:button name="btnClose" id="btnClose" type="button"  captionid="BTN.BACK" bundle="${DCPSLables}" onclick="ReturnPage();"/></td>
	</tr>
	
</table>

</fieldset>
<br/>

<br/>
<fieldset class="tabstyle">
	<display:table list="${sanctionedBudgetList}"  id="vo"   requestURI="" export=""  pagesize="12" style="width:70%">	

		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="basic.empty.showtable" value="true" />
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:left;width:2%" sortable="true" titleKey="CMN.SRNO">
			<c:out value="${vo_rowNum}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:20%" sortable="true" titleKey="CMN.ORGTYPE"  >		
			<c:forEach var="dept" items="${resValue.lLstDepartment}">
				<c:choose>
					<c:when test="${vo[1]==dept.id}">
						<c:out value="${dept.desc}"></c:out>
					</c:when>
				</c:choose>
			</c:forEach>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:10%" sortable="true"  titleKey="CMN.FINANCIALYEAR" >    
    		<c:forEach var="year" items="${resValue.lLstFinYear}">
				<c:choose>
					<c:when test="${vo[2]==year.id}">
						<c:out value="${year.desc}"></c:out>
					</c:when>
				</c:choose>
			</c:forEach>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width:15%" sortable="true"  titleKey="CMN.SANCORDERDATE"  >    
    			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}" var="sancDate"/>
    			<c:out value="${sancDate}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:left;width:10%" sortable="true" titleKey="CMN.STATUS">
			<c:out value="${vo[5]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:left;width:15%" sortable="true" titleKey="CMN.AMOUNT">
			<c:out value="${vo[4]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:left;width:15%" sortable="true" titleKey="CMN.TOTALBUDGET">
			<c:out value="${vo[6]}"></c:out>
		</display:column>
		
	</display:table>
	
</fieldset>

</hdiits:form>

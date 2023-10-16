<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseList" value="${resValue.CaseList}" />
<c:set var="UserType" value="${resValue.UserType}"/>
<c:set var="counter" value="1"></c:set>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/lna/loansEmpSearch.js"></script>
<script type="text/javascript" >
function forwardRequestToHOD(){

	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	if(totalSelectedEmployees>1){
		var pkValue="";
		var url="";
		var loanType="";
		for(var i=1;i<totalSelectedEmployees;i++)
		{			
			if(document.getElementById("chkPhyCaseReceived"+i).checked == true)
			{	
				pkValue= pkValue + document.getElementById("chkPhyCaseReceived"+i).value + "~";
				loanType = loanType + document.getElementById("hidLoanType"+i).value + "~";
				flag=1;
			}
		}
		if(flag == 1){
			url = "&pkValue="+pkValue+"&loanType="+loanType+"&entryType=multiple";
		}else{
			alert("Please select an entry to Submit");
			return false;
		}
		var uri="ifms.htm?actionFlag=forwardRequestToHOD";
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
					getForwardAdvanceRequestMsgHOD(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
	
	
}
function getForwardAdvanceRequestMsgHOD(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag=="true") {
		alert('Request has been forwarded to Head Of Department');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800023&userType=HODASST";
	}

}
</script>
<hdiits:form name="FrmLNAWorklist" id="FrmLNAWorklist" encType="multipart/form-data" validate="true" method="post" >

<fieldset class="tabstyle"><legend><fmt:message key="CMN.APPROVALREQ" bundle="${lnaLabels}" ></fmt:message></legend>
	<div style="float: inherit; border:0px; background-color: transparent;width:100%; height:400px;  ">

    <display:table list="${caseList}"  id="vo" style="width:100%"  pagesize="10" requestURI="">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" sortable="true" titleKey="CMN.TID" >		
				<a href = "ifms.htm?actionFlag=loadLoanAdvanceRequest&txtSevaarthId=${vo[2]}&criteria=1&requestType=${vo[4]}&userType=${UserType}&pkValue=${vo[5]}&elementId=800024">
				<c:out value="${vo[0]}" /></a>
		</display:column>
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[1]}" var="appDate"/>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.APPDATE" >
				<c:out value="${appDate}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.SEVAARTHID" >		
				<c:out value="${vo[2]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" sortable="true" titleKey="CMN.SUBSCRIBER" >
				<a href = "ifms.htm?actionFlag=loadLoanAdvanceRequest&txtSevaarthId=${vo[2]}&criteria=1&requestType=${vo[4]}&userType=${UserType}&pkValue=${vo[5]}&elementId=800024">   			
	   			<c:out value="${vo[3]}"></c:out></a> 
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.REQTYPE" >		
				<c:if test="${vo[4] == '800028'}"><c:out value="Computer Advance"></c:out></c:if>
				<c:if test="${vo[4] == '800029'}"><c:out value="House Building Advance"></c:out></c:if>
				<c:if test="${vo[4] == '800030'}"><c:out value="Vehicle Advance"></c:out></c:if> 		
 		</display:column>
 		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" titleKey="CMN.SUBTYPE" >		
				<c:out value="${vo[6]}"></c:out>				 		
 		</display:column>
 		<c:if test="${UserType=='HODASST'}">
 		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center"  title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkPhyCaseReceived\");'/>" >		
				<input type = "checkbox" id ="chkPhyCaseReceived${counter}" name = "selectAll" value="${vo[5]}"/>
				<input type = "hidden" id ="hidLoanType${counter}" name = "hidLoanType" value="${vo[4]}"/>
				<c:set var="counter" value="${counter+1}"></c:set>					 		
 		</display:column>
 		</c:if>
 		
	</display:table>
	<table width="100%">
	<tr>
		<td colspan="2" align="left"><label style="color: red"><fmt:message key="MSG.WORKLIST" bundle="${lnaLabels}" /></label></td>
	</tr>
	<tr>
		<td align="center">
			<hdiits:button type="button" captionid="BTN.CLOSE" bundle="${lnaLabels}" id="btnClose" name="btnClose" onclick="getHomePage();"></hdiits:button>
		</td>
	</tr>
	</table>
	</div>
</fieldset>
<c:if test="${UserType=='HODASST'}">
<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
<center><hdiits:button type="button" captionid="BTN.SUBMIT" bundle="${lnaLabels}" id="btnSubmit" name="btnSubmit" onclick="forwardRequestToHOD();"></hdiits:button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick=""></hdiits:button></center>
</c:if>
</hdiits:form>	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script>

function ClearData(){
	document.getElementById("txtName").value='';
	document.getElementById("textarea1").value='';
}
function SubmitFeedback()
{
	var name=document.getElementById("txtName").value;
	var ddoCode=document.getElementById("txtDDOCode").value;
	var treasuryCode=document.getElementById("txtTreasuryCode").value;
	var deptName=document.getElementById("cmbDept").options[0].value;
	var comments=document.getElementById("textarea1").value;
	var txtFeedBackDate = document.getElementById("txtFeedBackDate").value;
	var txtOfficeEmailId = document.getElementById("txtOfficeEmailId").value;
	var txtOfficeContactNo = document.getElementById("txtOfficeContactNo").value;

	var uri = "ifms.htm?actionFlag=saveFeedback";
	var url = "name="+name+"&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode+"&deptName="+deptName+"&comments="+comments+"&txtFeedBackDate="+txtFeedBackDate
		+"&txtOfficeEmailId="+txtOfficeEmailId+"&txtOfficeContactNo="+txtOfficeContactNo;
	
	//alert(url);

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForSubmitFeedBackForm(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function dataStateChangedForSubmitFeedBackForm(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries  = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XMLEntries[0].childNodes[0].firstChild.nodeValue;
	if(successFlag)
	{
		alert("Your Feedback Submitted");
		self.location.reload(true);
	}
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="lLstDept" value="${resValue.lLstDept}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmFeedbackEntry" encType="multipart/form-data"
	validate="true" method="post">
	
<br/>

<fieldset class="tabstyle">
<legend> 
<b><fmt:message
	key="CMN.ISSUEDETAILS" bundle="${DCPSLables}"></fmt:message></b>
</legend> 

<table id="table1" width=80%" align="center" >	
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.NAME" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="30%" name='txtName' id="txtName" style="text-align: left" />
		</td>
		
	</tr>
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.DDO" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="70%" name='txtDDOName' id="txtDDOName" style="text-align: left" value="${resValue.DDOName}" readonly="readonly"/>
			<input type="hidden"  size="30%" name='txtDDOCode' id="txtDDOCode" style="text-align: left" value="${resValue.DDOCode}" />
		</td>
		
	</tr>
	
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.TREASURY" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="30%" name='txtTreasuryName' id="txtTreasuryName" style="text-align: left" value="${resValue.Treasury}" readonly="readonly"/>
			<input type="hidden"  size="30%" name='txtTreasuryCode' id="txtTreasuryCode" style="text-align: left" value="${resValue.TreasuryCode}" />
		</td>
		
	</tr>
	
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.DEPARTMENT" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<select name="cmbDept" id="cmbDept" style="width:50%" readonly="readonly">
				<c:forEach var="deptName" items="${lLstDept}" >
					<option value="${deptName.id}" selected="selected"><c:out value="${deptName.desc}"></c:out></option>					         					
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
	
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left"><fmt:message
				key="CMN.FEEDBACKDATE" bundle="${DCPSLables}"></fmt:message></td>
		
		<td colspan="2"  align="left"><input type="text"
			name="txtFeedBackDate" id="txtFeedBackDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);"
			onBlur="validateDate(txtFeedBackDate);"	/>
			<img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtFeedBackDate",375,570)'
			style="cursor: pointer;" />
		</td>
	</tr>
	
	<tr>
	<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left"><fmt:message key="CMN.OFFICEEMAILID"
				bundle="${DCPSLables}"></fmt:message></td>
		<td colspan="2"  align="left"><input type="text"
				name="txtOfficeEmailId" id="txtOfficeEmailId"
				size="44"/></td>
	</tr>
	
	<tr>
	<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left"><fmt:message key="CMN.OFFICECONTACTNO"
				bundle="${DCPSLables}"></fmt:message></td>
		<td colspan="2"  align="left"><input type="text"
				name="txtOfficeContactNo" id="txtOfficeContactNo" onkeypress="digitFormat(this);"
				size="30"/></td>
	</tr>
	
		
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.COMMENTS" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left" >
			<TEXTAREA NAME="textarea1" ROWS="5" style="width:50%"></TEXTAREA>
		</td>
	</tr>
</table>
</br>

</fieldset>

</br>
<center><hdiits:button type="button" captionid="BTN.SUBMIT" bundle="${DCPSLables}" id="btnSubmit" name="btnSubmit" onclick="SubmitFeedback();"></hdiits:button>
<hdiits:button type="button" captionid="BTN.CLEAR" bundle="${DCPSLables}" id="btnClear" name="btnClear" onclick="ClearData();"></hdiits:button></center>
</hdiits:form>
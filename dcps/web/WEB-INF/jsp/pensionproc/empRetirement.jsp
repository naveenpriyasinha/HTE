<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>
<script type="text/javascript" src="script/common/common.js"></script>

<script>
function submitDetails(){

	var fromDate=document.getElementById("txtFromDate").value;
	var toDate=document.getElementById("txtToDate").value;
	var designation=document.getElementById("txtDesignation").value;
	 url = "ifms.htm?actionFlag=reportService&reportCode=1000001&action=generateReport&DirectReport=TRUE&displayOK=FALSE&fromDate="+fromDate+"&toDate="+toDate+"&designation="+designation;


			document.EmpRetirementList.action = url ;
			document.EmpRetirementList.submit();
	
}

function compareDates(){

	var fromDate=document.getElementById("txtFromDate").value;
	var toDate=document.getElementById("txtToDate").value;
	var fromdateArray=fromDate.split("/");
	var toDateArray=toDate.split("/");

	if(Number(fromdateArray[2])>Number(toDateArray[2])){
		alert("From Date should be less than To Date");
		document.getElementById("txtToDate").value="";
		return false;
	}
	
	if(Number(fromdateArray[2])==Number(toDateArray[2])){
		if(Number(fromdateArray[1])>Number(toDateArray[1])){
			alert("From Date should be less than To Date");
			document.getElementById("txtToDate").value="";
			return false;
		}
		}

	if((Number(fromdateArray[2])==Number(toDateArray[2])) && (Number(fromdateArray[1])==Number(toDateArray[1]))){
	
	if(Number(fromdateArray[0])>Number(toDateArray[0])){
		alert("From Date should be less than To Date");
		document.getElementById("txtToDate").value="";
	return false;
	}
	}
	
}
</script>

<hdiits:form name="EmpRetirementList" id="EmpRetirementList" encType="multipart/form-data"
	validate="true" method="post">
 
<div align="center">

<br/>
<fieldset><legend>Employee Retirement Report</legend>
<table border="0" align="left" >
		
<tr>
			<td  width="5%" align="left" ><fmt:message key="PPROC.FFROMDATE" bundle="${pensionLabels}"></fmt:message>
				</td><td align="left"><input type="text"
				name="txtFromDate" id="txtFromDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtFromDate); "	 /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtFromDate", 375, 570)'
				style="cursor: pointer;"/>
 				<label class="mandatoryindicator">*</label> 
				</td>
				
				<td  width="5%" align="left" ><fmt:message key="PPROC.TTODATE" bundle="${pensionLabels}"></fmt:message>
				</td><td align="left">	<input type="text"
				name="txtToDate" id="txtToDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur=" compareDates();"	 /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtToDate", 375, 570)'
				style="cursor: pointer;" />
 				<label class="mandatoryindicator" >*</label> 
				</td>
		</tr>
		
		<tr align="center">
		<td  width="5%" align="left"><fmt:message key="PPROC.DESIGNATION"
										bundle="${pensionLabels}" />	</td><td align="left"><input type="text"
										id="txtDesignation" size="30" 
										name="txtDesignation" />
	</td>
			
			
	</tr>
	
	<tr>	<td align="left"><hdiits:button name="btnSubmitData" id="btnSubmitData" type="button" style="width: 200px"
	captionid="BTN.GENREPORT" bundle="${pensionLabels}" onclick="submitDetails();" /></td>
	</tr>
				
		
	</table>
</fieldset>
</div>
</hdiits:form>

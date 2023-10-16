<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>

<script type="text/javascript"  src="script/common/common.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	
	
	<script type="text/javascript"	>
	function selEmp(){

		//var sel=document.getElementsByTagName("chkbxSevaId").value;
		//alert(sel);


		var arrChkBox=document.getElementsByName("chkbxSevaId");
		var flag="";
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked==true)
				{
				//	alert("arrChkBox[i].value"+arrChkBox[i].value);
					flag=arrChkBox[i].value+"_"+flag;
				}
			}
		}
		//	alert("flag"+flag);
	document.getElementById("flag").value=flag;
if(flag!=""){
			document.getElementById("files").style.display='';
			
}
else 
	alert("Please select sevaarth Ids.");
			
		
	}


	function mainDtls(){

		
		 var sevaarthId=document.getElementById("flag").value;
		//alert("sevaarthId "+sevaarthId);

		var url="ifms.htm?actionFlag=excelForMainDtlsMultiple&sevaarthId="+sevaarthId;
		 //showProgressbar("Please wait...");

		alert("Please wait till your request is processed");
		
		window.location.href=url;
	}
	function flyDtls(){

		//alert("flyDtls Details");


		 var sevaarthId=document.getElementById("flag").value;
		//alert(agCircle+" "+sevaarthId);

		var url="ifms.htm?actionFlag=excelForFlyDtlsMultiple&sevaarthId="+sevaarthId;
		 //showProgressbar("Please wait...");

		alert("Please wait till your request is processed");
		
		window.location.href=url;
		
	}
	function nomiDtls(){

		//alert("nomiDtls Details");


		var sevaarthId=document.getElementById("flag").value;
		//alert(agCircle+" "+sevaarthId);

		var url="ifms.htm?actionFlag=excelForNomiDtlsMultiple&sevaarthId="+sevaarthId;
		 //showProgressbar("Please wait...");

		alert("Please wait till your request is processed");
		
		window.location.href=url;
		
	}
	function pnsnBrkDtls(){

		//alert("pnsnBrkDtls Details");


		 var sevaarthId=document.getElementById("flag").value;
		//alert(agCircle+" "+sevaarthId);

		var url="ifms.htm?actionFlag=excelForpnsnBrkDtlsMultiple&sevaarthId="+sevaarthId;
		 //showProgressbar("Please wait...");

		alert("Please wait till your request is processed");
		
		window.location.href=url;
		
	}
	function resetBut(){
		document.getElementById("files").style.display='none';
		document.getElementById("FROMDATE").value="";
		document.getElementById("TODATE").value="";
		document.getElementById("div1").style.display='none';
		}

	function SearchApprovedFiles(){
	var fromDate=document.getElementById("FROMDATE").value;
	var toDate=document.getElementById("TODATE").value;

//	alert("fromDate::"+fromDate);
	//alert("toDate::"+toDate);


	var url="ifms.htm?actionFlag=loadDwnldMultiFileForProcDateWise&fromDate="+fromDate+"&toDate="+toDate;
	 //showProgressbar("Please wait...");

	//alert("Please wait till your request is processed");
	
	window.location.href=url;

	

		}
	</script>
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.EmpList}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="fromDate" value="${resValue.fromDate}"></c:set>
<c:set var="toDate" value="${resValue.toDate}"></c:set>
<input type="hidden" id="flag" name="flag"/>
<hdiits:form name="frmEmpList" method="post" validate="">
<fieldset class="tabstyle" style="width:100%">
<legend	id="headingMsg">
			<b>Search</b>
</legend>

From Date: 	<input type="text" name="FROMDATE" id="FROMDATE"
				value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${fromDate}" />"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
				onblur="onBlur(this);chkValidDate(this);" /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open(event,"FROMDATE",375,570)'
				style="cursor: pointer;" />
				
To Date: 	<input type="text" name="TODATE" id="TODATE"
				value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${toDate}" />"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
				onblur="onBlur(this);chkValidDate(this);" /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open(event,"TODATE",375,570)'
				style="cursor: pointer;" />
				
<hdiits:button name="btnSearch" id="btnSearch" type="button"
				captionid="PPROC.SEARCH" bundle="${pensionLabels}"
				onclick="SearchApprovedFiles()" /> 
</fieldset>

<fieldset class="tabstyle" style="height:350px;width:100%">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.APPROVEDFILES" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<div align="center">
<div id="div1" class="scrollablediv" style="height:320px;width:60%;" >
<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%=Constants.PAGE_SIZE %>"  export="false" style="width:100%;height:40%;align:center;" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
				

	
<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%;text-align:center"  >
			<input type="checkbox" align="middle" name="chkbxSevaId" 
				id="chkbxSevaId" style="text-align:center"
				 value="${vo[1]}" />
		
	</display:column>

	<display:column titleKey="PPROC.INWARDNO" headerClass="datatableheader"
			sortable="false" style="width:5%;text-align:left" >${vo[2]}
	</display:column>
	<display:column titleKey="PPMT.SEVAARTHID" headerClass="datatableheader"
			sortable="false" style="width:5%;text-align:left" >${vo[1]}
	</display:column>
	<display:column titleKey="PPMT.NAMEOFEMPLOYEE" headerClass="datatableheader"
			sortable="false" style="width:5%;text-align:left" >${vo[3]}
	</display:column>

	

</display:table>
</div></div>
<div align='center'>
<hdiits:button name="btnSelect" type="button" captionid="PPMT.SELECT" bundle="${pensionLabels}"  
onclick="selEmp();" />
<hdiits:button name="btnReset" type="button" captionid="PPMT.RESET" bundle="${pensionLabels}"  
onclick="resetBut();" />
</div>

</fieldset>
<fieldset class="tabstyle" style="height:50%">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.DOWNLOAD" bundle="${pensionLabels}"></fmt:message></b>
</legend>

<div id="files" style="display: none;padding-left:200px;">
	<table border="0" width="80%" align="center" cellpadding="4" cellspacing="4">
	<tr><td><a href="#" onclick="javascript:mainDtls();"><B>1.MAIN DETAILS</B></a></td></tr>
	<tr><td><a href="#" onclick="javascript:flyDtls();"><B>2.FAMILY DETAILS</B></a></td></tr>
	<tr><td><a href="#" onclick="javascript:nomiDtls();"><B>3.NOMINEE DETAILS</B></a></td></tr>
	<tr><td><a href="#" onclick="javascript:pnsnBrkDtls();"><B>4.PENSION BREAK DETAILS</B></a></td></tr>
	
	
	</table>
	</div>
</fieldset>
</hdiits:form>
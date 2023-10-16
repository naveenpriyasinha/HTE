<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EmpDtls" value="${resValue.EmpDtls}"></c:set>
<c:set var="UserType" value="${resValue.UserType}"></c:set>

<c:if test="${UserType == 'HOD'}">
	<c:set var="varReadonly" value="readOnly='readOnly'"></c:set>
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
</c:if>	

<input type="hidden" id="hidTotalRecordCA" name="hidTotalRecordCA" value="${resValue.TotalRecordCA}" />
<input type="hidden" id="hidTotalRecordHBA" name="hidTotalRecordHBA" value="${resValue.TotalRecordHBA}" />
<input type="hidden" id="hidTotalRecordMCA" name="hidTotalRecordMCA" value="${resValue.TotalRecordMCA}" />

<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script>
var LISTMONTHS='';
var LISTYEARS='';
</script>
<script type="text/javascript">
function popUpEmpDtls(){
	
	var	empCode= document.getElementById("txtEmployeeCode").value;
	if(empCode=="" || empCode==null){
		return false;
	}
	var uri="ifms.htm?actionFlag=popUpEmpDtlsDataEntryForm";
	var url = "&EmpCode="+empCode;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getReqMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getReqMsg(myAjax)
{	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(lblSaveFlag=="Yes"){
		alert('For this Employee a request cannot be raised as one request of is in Process');
		document.getElementById("txtEmployeeCode").value="";
	}else if(lblSaveFlag=="true"){
		var lStrEmpName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		var lStrDsgnName = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
		var lStrOfficeName = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;		
		if(lStrEmpName!=null){
			document.getElementById("txtEmployeeName").value=lStrEmpName;
		}
		if(lStrDsgnName!=null){
			document.getElementById("txtDesignation").value=lStrDsgnName;
		}
		if(lStrOfficeName!=null){
			document.getElementById("txtOfficeName").value=lStrOfficeName;
		}		
	}else{
		alert('Invalid Employee Code');
		document.getElementById("txtEmployeeCode").value="";
		document.getElementById("txtEmployeeName").value="";
		document.getElementById("txtDesignation").value="";
		document.getElementById("txtOfficeName").value="";		
	}
}
</script>
<c:forEach var="MonthList" items="${resValue.lLstMonths}" >
	<script> LISTMONTHS += '<option value="${MonthList.id}"> ${MonthList.desc}</option>';</script>
</c:forEach>
<c:forEach var="YearList" items="${resValue.lLstYears}" >
	<script> LISTYEARS += '<option value="${YearList.id}"> ${YearList.desc}</option>';</script>
</c:forEach>

<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>

<hdiits:form name="FrmDataEntry" encType="multipart/form-data" validate="true" method="post">

<fieldset class="tabstyle"><legend><fmt:message key="CMN.EMPDETAILS" bundle="${lnaLabels}"></fmt:message></legend>
<table width="80%">
	<tr>
		<td width="20%"><fmt:message key="CMN.SEVAARTHID" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtEmployeeCode" style="text-transform: uppercase" name="txtEmployeeCode" value="${resValue.SevaarthId}" onblur="popUpEmpDtls();" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		
		<td width="10%"><fmt:message key="CMN.EMPNAME"	bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtEmployeeName" name="txtEmployeeName" value="${EmpDtls[0]}" size="40" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td width="20%"><fmt:message key="CMN.DESIGNATION"	bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtDesignation" name="txtDesignation" value="${EmpDtls[1]}" readonly="readonly"/>
		</td>
		<td width="10%"><fmt:message key="CMN.NAMEOFTHEOFFICE" bundle="${lnaLabels}" /></td>
		<td width="20%">
			<input type="text"	id="txtOfficeName" name="txtOfficeName"  value="${EmpDtls[2]}" size="40" readonly="readonly"/>			
		</td>		
	</tr>	
</table>
</fieldset>
<br>
<fieldset class="tabstyle">
<div id="tabmenu" >
     <ul id="maintab">
	     	<li class="selected">
		    	<a href="#" rel="tcontent1" >
		  			<fmt:message key="CMN.CA" bundle="${lnaLabels}"></fmt:message>
		        </a>
	        </li>
	       	<li>
		        <a href="#" rel="tcontent2">
					<fmt:message key="CMN.HBA" bundle="${lnaLabels}"></fmt:message>
		        </a>
	        </li>
	        <li>
		        <a href="#" rel="tcontent3">
					<fmt:message key="CMN.VA" bundle="${lnaLabels}"></fmt:message>
		        </a>
	        </li>
	 </ul>
</div>		   
<div class="tabcontentstyle">
	
		<div id="tcontent1" class="tabcontent" align="left">
			<jsp:include page="/WEB-INF/jsp/lna/ComputerDataEntryForm.jsp" />
		</div>	
		<div id="tcontent2" class="tabcontent" align="left">
			<jsp:include page="/WEB-INF/jsp/lna/HouseDataEntryForm.jsp" />
		</div>
		<div id="tcontent3" class="tabcontent" align="left">
			<jsp:include page="/WEB-INF/jsp/lna/VehicleDataEntryForm.jsp" />
		</div>
</div>		     
</fieldset>

<center>
<c:if test="${UserType == 'HODASST'}">
	<hdiits:button type="button" captionid="BTN.SAVE" bundle="${lnaLabels}" id="btnSave" name="btnSave" onclick="saveForm();"></hdiits:button>
	<hdiits:button type="button" captionid="BTN.FORWARD" bundle="${lnaLabels}" id="btnForward" name="btnForward" onclick="forwardForm();"></hdiits:button>
</c:if>
<c:if test="${UserType == 'HOD'}">
	<hdiits:button name="btnApproveData" id="btnApproveData" type="button" captionid="BTN.APPROVE" bundle="${lnaLabels}" onclick="approveForm();"/>	
</c:if>	
<hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="getHomePage();"></hdiits:button>
</center>		
</hdiits:form>

<script type="text/javascript">
		initializetabcontent("maintab");
</script>	
	
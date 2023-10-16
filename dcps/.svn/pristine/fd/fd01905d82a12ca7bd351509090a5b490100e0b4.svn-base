<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script language="javascript">
function generateSchedule()
{
	var billGroupId = document.getElementById("cmbBillGroup").value;
	var monthId = document.getElementById("cmbMonth").value;
	var yearId = document.getElementById("cmbYear").value;
	var selectedMonth = document.getElementById("cmbMonth").selectedIndex;
	var monthName = document.getElementById('cmbMonth').options[selectedMonth].text;
	var selectedYear = document.getElementById("cmbYear").selectedIndex;
	var yearCode = document.getElementById('cmbYear').options[selectedYear].text;
	if(monthId>=4)
		yearCode = yearCode.substring(0,4);
	else
		yearCode = yearCode.substring(5,9);
	url = "ifms.htm?actionFlag=reportService&reportCode=800001&action=generateReport&billGroupId="+billGroupId
	+"&monthId="+monthId+"&yearId="+yearId+"&monthName="+monthName+"&yearCode="+yearCode+"&asPopup=TRUE";
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "GenerateSchedule", urlstyle);
}
function updateMonthlyData(){
	var billGroupId = document.getElementById("cmbBillGroup").value;
	var monthId = document.getElementById("cmbMonth").value;
	var yearId = document.getElementById("cmbYear").value;

	if (!chkEmpty(document.getElementById("cmbBillGroup"), "Bill Group")
			|| !chkEmpty(document.getElementById("cmbMonth"),"Month")		
			|| !chkEmpty(document.getElementById("cmbYear"), "Year")) 
	{
		return false;
	}

	var uri = 'ifms.htm?actionFlag=generateGPFSchedule';
	var url = "&billGroupId="+billGroupId+"&monthId="+monthId+"&yearId="+yearId;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
		getUpdateMonthlyDataMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getUpdateMonthlyDataMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var strAlert = XmlHiddenValues[0].childNodes[1].text;

	if (lblSaveFlag) {
		if(strAlert == 'alreadyGenerated'){
			alert("Schedule already generated for the month!");
			return;
		}else if(strAlert == 'prePending'){
			alert("Previous month schedule is pending for the selected Bill Group");
			return;
		}else{
			generateSchedule();
		}
	}
}
</script>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<table width="80%" align="center"><tr><td>
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.GENERATESCHEDULE" bundle="${gpfLables}" ></fmt:message></legend>
</br>
<table width="80%" align="center" cellpadding="4" cellspacing="4">
       <tr>
           <td width="5%">
           <fmt:message key="CMN.BILLGRP" bundle="${gpfLables}"></fmt:message>
           </td>
           <td colspan="3">
           <select name="cmbBillGroup"	id="cmbBillGroup"  style="width:500px">
           <option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="billGroup" items="${resValue.lLstBillGroup}">
				<option value="${billGroup.id}"><c:out	value="${billGroup.desc}"></c:out></option>
			</c:forEach>
		   </select>
           </td>

       </tr>
       <tr>
           <td width="5%">
           <fmt:message key="CMN.GROUP" bundle="${gpfLables}"></fmt:message>
           </td>
           <td colspan="3">
           <input type="text" id="txtGroup"	name="txtGroup" value="D" readonly="readonly" size="5"/>
           </td>
       </tr>
       
       <tr>
           <td width="5%">
           <fmt:message key="CMN.MONTH" bundle="${gpfLables}"></fmt:message>
           </td>
           <td width="10%">
           <select name="cmbMonth" id="cmbMonth" >
			<c:forEach var="month" items="${resValue.lLstMonths}">
				<option value="${month.id}"><c:out	value="${month.desc}"></c:out></option>
			</c:forEach>
		   </select>
           </td>
           <td width="5%">
           <fmt:message key="CMN.YEAR" bundle="${gpfLables}"></fmt:message>
           </td>
           <td width="10%">
           <select name="cmbYear" id="cmbYear" style="width:100px">
			<c:forEach var="year" items="${resValue.lLstYears}">
				<option value="${year.id}"><c:out	value="${year.desc}"></c:out></option>
			</c:forEach>
		   </select>
           </td>
       </tr>
</table>
</br>
</fieldset></td></tr></table>
<table width="50%" align="center"><tr><td align="center"></br>
<hdiits:button type="button" captionid="CMN.GENERATESCHEDULE" bundle="${gpfLables}" id="btnGenerateSchedule" name="btnGenerateSchedule" onclick="updateMonthlyData();" style="width:180px"></hdiits:button>

</td></tr></table>
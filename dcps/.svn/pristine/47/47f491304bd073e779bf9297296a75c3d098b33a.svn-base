<%
	try {
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page session="true"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%><html>




<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="monthList" value="${resValue.monthList}"></c:set>
<c:set var="yearList" value="${resValue.yearList}"></c:set>
<c:set var="dayList" value="${resValue.dayList}"></c:set>
<c:set var="currMonth" value="${resValue.lIntMonth}"></c:set>
<c:set var="currYear" value="${resValue.lIntYear}"></c:set>
<c:set var="minuteList" value="${resValue.minuteList}"></c:set>
<c:set var="hourList" value="${resValue.hourList}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>


<head>
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="adminCreatePostLabel" scope="request" />
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script>
function displayElements(){
	var radioLen = document.resetScheduler.typeOfReset.length;

	for(var i=0;i<radioLen;i++){
		//alert('Inside for i'+i)
		if (document.resetScheduler.typeOfReset[i].checked) {
			chosen = document.resetScheduler.typeOfReset[i].value;
			break;
		}
	}
		
	if(chosen=="timerCounterReset"){
		document.getElementById('counterResetDiv').style.display='';
		document.getElementById('jobResetDiv').style.display='none';
	}
	if(chosen=="jobReset"){
		document.getElementById('jobResetDiv').style.display='';
		document.getElementById('counterResetDiv').style.display='none';
	}
	if(chosen=="bothReset"){
		document.getElementById('jobResetDiv').style.display='';
		document.getElementById('counterResetDiv').style.display='';
	}
}
function submitForm(){
	var chosen = displayElements();
	showProgressbar();
	document.resetScheduler.action="ifms.htm?actionFlag=resetScheduler&typeOfReset="+chosen;
	document.resetScheduler.submit();
}


</script>

</head>
<body>
<hdiits:form name="resetScheduler"
	action="hrms.htm?actionFlag=resetScheduler" validate="true"
	id="resetScheduler" method="post" encType="multipart/form-data">

	<fieldset class="tabstyle">
	<legend>
	Select Type Of Reset
	</legend>
	<div style="padding-left: 100px">
	<table>
	<tr>
	<td>
	<input type="radio"	name="typeOfReset" value="timerCounterReset" onclick="displayElements();" /><b>&nbsp;&nbsp;&nbsp;&nbsp;Reset Timer and Counter</b><br/>
	<input type="radio" name="typeOfReset" value="jobReset" onclick="displayElements();"  /><b>&nbsp;&nbsp;&nbsp;&nbsp;Reset Job</b> <br/>
	<input type="radio" name="typeOfReset" value="bothReset" onclick="displayElements();"  /><b>&nbsp;&nbsp;&nbsp;&nbsp;Reset Both</b><br/>
	</td>
	</table>
	</div>
	</fieldset>
	<div id="jobResetDiv" >
	<br/><br/><br/><br/>
	<fieldset class="tabstyle">
	<legend>
	Job Details Reset
	</legend>
	<div style="padding-left: 100px">
	<table>
	<tr>
	<td style="width:40%" align="left"><b>Month :</b>	</td>
	<td align="right" style="width:70%">
	<hdiits:select name="forMonth" size="" sort="false" id="forMonth"
		onchange="" default="${currMonth}">
		<hdiits:option value="-1"> --Select-- </hdiits:option>
		<c:forEach items="${monthList}" var="FromMonthList">
			<hdiits:option value="${FromMonthList.lookupShortName}"> ${FromMonthList.lookupDesc} </hdiits:option>
		</c:forEach>
	</hdiits:select>
	</td></tr>
	<tr>
	<td style="width:40%" align="left"><b>Year :</b>	</td>
	<td align="right" style="width:70%">
	<hdiits:select name="forYear" size="" sort="false"
		id="forYear" onchange="" default="${currYear}">
		<hdiits:option value="-1"> --Select-- </hdiits:option>
		<c:forEach items="${yearList}" var="FromyearList">
			<hdiits:option value="${FromyearList.lookupShortName}"> ${FromyearList.lookupDesc} </hdiits:option>
		</c:forEach>
	</hdiits:select>
	</td></tr>
	<tr>
	<td style="width:40%" align="left"><b>Day :</b>	</td>
	<td align="right" style="width:70%">
	<hdiits:select name="forDay" size="" sort="false"
		id="forDay" onchange="" >
		<hdiits:option value="-1"> --Select-- </hdiits:option>
		<c:forEach items="${dayList}" var="FromdayList">
			<hdiits:option value="${FromdayList}"> ${FromdayList} </hdiits:option>
		</c:forEach>
	</hdiits:select>
	</td></tr>
	</table>
	</div>
	</fieldset>
	</div>
	<br/><br/><br/><br/>
	<div id="counterResetDiv" >
	<fieldset class="tabstyle">
	<legend>
	Counter And Timer Reset
	</legend>
	<div style="padding-left: 100px">
	<table>
	<tr>
	<td style="width:40%" align="left" ><b>Hour :</b></td>
	<td align="left" style="width:70%">
	<hdiits:select name="forHour" size="" sort="false" id="forHour"
		onchange="" >
		<hdiits:option value="-1"> --Select-- </hdiits:option>
		<c:forEach items="${hourList}" var="FromHourList">
			<hdiits:option value="${FromHourList}"> ${FromHourList} </hdiits:option>
		</c:forEach>
	</hdiits:select>
	</td></tr>
	<tr>
	<td style="width:40%" align="left" align="left"><b>Minute :</b></td>
	<td align="left" style="width:70%">
	<hdiits:select name="forMinute" size="" sort="false" id="forMinute"
		onchange="" >
		<hdiits:option value="-1"> --Select-- </hdiits:option>
		<c:forEach items="${minuteList}" var="FromMinuteList">
			<hdiits:option value="${FromMinuteList}"> ${FromMinuteList} </hdiits:option>
		</c:forEach>
	</hdiits:select>
	</td></tr>
	<tr>
	<td style="width:40%" align="left"><b>Start Thread Count :</b></td>
	<td align="left" style="width:70%">
	<input type="text" name="startThread" id="startThread" value="" />
	</td></tr>
	<tr>
	<td style="width:40%" align="left" ><b>End Thread Count :</b></td>
	<td align="left" style="width:70%">
	<input type="text" name="endThread" id="endThread" value="" />
	</td></tr>
	<tr>
	<td style="width:40%" align="left" ><b>Counter:</b></td>
	<td align="left" style="width:70%">
	<input type="text" name="counterVal" id="counterVal" value="1" />
	</td></tr>
	</table>
	</div>
	</fieldset>
	</div>
	<div style="padding-left: 500px">
	<table width="100px"><tr><td align="center">
	<hdiits:button type="button" name="submitButton" id="submitButton" value="Reset Scheduler" style="width:150px" onclick="submitForm();" />
	</td></tr></table></div>

</hdiits:form>

<script type="text/javascript">
if("${msg}" != '' && "${msg}" != 'Successfully Updated-)"'){
	alert("${msg}");
	document.resetScheduler.action="ifms.htm?actionFlag=resetScheduler&typeOfReset=";
	document.resetScheduler.submit();
}
</script>
</body>




</html>
<%
	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>

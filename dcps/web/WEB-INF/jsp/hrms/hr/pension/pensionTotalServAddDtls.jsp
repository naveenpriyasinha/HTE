<%try { %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.pension.Pension" var="pensiontotalServLabel" scope="request" />
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script>
var glbAfterDateSelect='';
function savetotalServWindow()
{
	opener.document.frmPensionServDtls.pensionChildOpenFlag.value = 'false';	
	if(document.getElementById('fromDate').value=='')
	{
		alert("<fmt:message  bundle="${pensiontotalServLabel}" key="Pension.FromDateReq"/>");		
	}
	else if(document.getElementById('todate').value=='')
	{
		alert("<fmt:message  bundle="${pensiontotalServLabel}" key="Pension.ToDateReq"/>");
	}
	else
	{		
		xmlHttp=GetXmlHttpObject();
		
		var fieldArr=new Array('todate','fromDate');
		var reqBody = getRequestBody(fieldArr);
		var url='hdiits.htm?actionFlag=countPensionableServPeriod&' + reqBody;
		xmlHttp.onreadystatechange=CallparentFunction;	
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
		xmlHttp.send(null);
				
	}
}
function validateToAndFromDate()
{
	updateMyFocusFlag();
	var strDate1 = document.getElementById('fromDate').value;
	if(strDate1!='')
	{
		var strDate2 = document.getElementById('todate').value;			
		var bldatediff = false ;
		
		//Start date split to UK date format and add 31 days for maximum datediff 
		strDate1 = strDate1.split("/"); 
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 
		//alert ('end : '+endtime +'  start   : ' + starttime );
		if(endtime >= starttime) 
		{ 
			bldatediff = true 
		} 
		else
		{
			alert("<fmt:message  bundle="${pensiontotalServLabel}" key="Pension.ProperToDate"/>");
		}
	}
	else
	{
		alert("<fmt:message  bundle="${pensiontotalServLabel}" key="Pension.EnterFromDate"/>");
	}	
}
function CallparentFunction()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{ 
			opener.AddDatainTable(xmlHttp.responseText);
			window.close();
		}				
	}	
}
function GetFocus()
{	
	if(glbAfterDateSelect=='')
	{
		window.focus();
	}
}
function closetotalServWindow()
{
	opener.document.frmPensionServDtls.pensionChildOpenFlag.value = 'false';	
	opener.document.frmPensionServDtls.editFlag.value = 'false';
	window.close();
}
function updateMyFocusFlag()
{
	glbAfterDateSelect = '';
}
</script>
<BODY onBlur="GetFocus();" onunload="closetotalServWindow();">
<hdiits:form name="frmPensionTotalServDtls" validate="true">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<hdiits:caption captionid="Pension.totalSrvc" bundle="${pensiontotalServLabel}"></hdiits:caption>
		</b></a></li>
</ul>
</div>
<div id="PensionSalaryReq" name="PensionSalaryReq">
		<div id="tcontent1" class="tabcontent" tabno="0">
<table class="tabtable">
	<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="Pension.totalSrvc" bundle="${pensiontotalServLabel}"/></u></strong></font></td>
	</tr>
</table>

<BR>
<center>
	<hdiits:button name="btnSave" onclick="savetotalServWindow();" value="Save" type="button"/>
	<hdiits:button name="btnClose" onclick="closetotalServWindow();" value="Close" type="button"/>
</center>
</div>
</div>
<script type="text/javascript">	
	initializetabcontent("maintab")
	document.getElementById('todate').readOnly=true;
	document.getElementById('fromDate').readOnly=true;
	if(opener.document.frmPensionServDtls.editFlag.value=='true')
	{
		document.getElementById('todate').value = opener.document.frmPensionServDtls.toDate.value;
		document.getElementById('fromDate').value = opener.document.frmPensionServDtls.fromDate.value;
	}
</script>
</hdiits:form>
</BODY>
<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>
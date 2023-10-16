<%try { %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.pension.Pension" var="pensionSalaryLabel" scope="request" />
<script>

var nameMonthArr = new Array("January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December");
function closeSalaryWindow()
{	
	window.close();
}
</script>
<BODY onBlur="window.focus();" onunload="closeSalaryWindow();">
<hdiits:form name="pensionSalaryDtls" validate="true">
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="Pension.SalDtls" bundle="${pensionSalaryLabel}" />
		</b></a></li>
</ul>
</div>
<div id="PensionSalaryReq" name="PensionSalaryReq">
		<div id="tcontent1" class="tabcontent" tabno="0">
<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Pension.SalDtls" bundle="${pensionSalaryLabel}">			
<table class="tabtable" id="salaryTable"  style="border-collapse: collapse;" borderColor="BLACK"  border=1 align="center">
	<tr bgcolor="#C9DFFF">
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="Pension.MONTH" bundle="${pensionSalaryLabel}" /></b></td>
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="Pension.YEAR" bundle="${pensionSalaryLabel}" /></b></td>		
		<td class="fieldLabel" align="center"><b><hdiits:caption captionid="Pension.Salary" bundle="${pensionSalaryLabel}" />&nbsp;</b>(INR)</td>			
	</tr>
	<tr id="showMessage" style="display:none">
		<td class="fieldLabel" colspan="5" align="center"><b><fmt:message key="Pension.no_record" bundle="${pensionSalaryLabel}" /></b>
		</td>
	</tr>	
</table>				
	
	<script>

		var displayArrList = opener.document.frmPenBranchReq.displaySalaryArr.value;
		var displayArr = displayArrList.split(',');
		
		if(displayArrList.length==0)
		{
			document.getElementById('showMessage').style.display='';
		}
		else
		{
			for(var j=0;j<displayArr.length;j++)
			{
				var trow=document.getElementById('salaryTable').insertRow();					
				var dispFieldObj = displayArr[j].toString();
				var dispFieldA = dispFieldObj.split('~');			
				for(var i = 0; i < 3; i++) 
				{								
					if(i==0)
					{
						var x = parseInt(dispFieldA[i]);
						if(x>=1 && x<=12)
							trow.insertCell(i).innerHTML =  nameMonthArr[x-1];
						else
							trow.insertCell(i).innerHTML = dispFieldA[i];
					}
					else if (i==1)
					{
						var x = parseInt(dispFieldA[i]);
						trow.insertCell(i).innerHTML = x;
					}
					else
					{
						trow.insertCell(i).innerHTML = dispFieldA[i];
					}
				}
			}
		}
	</script>	
</hdiits:fieldGroup>	
<BR>
<center>
	<hdiits:button name="btnClose" onclick="closeSalaryWindow();" value="Close" type="button"/>
</center>
<hdiits:hidden name="tenMonthSalaryVOlist" id="tenMonthSalaryVOlist" />
</div>
</div>
<script type="text/javascript">	
	initializetabcontent("maintab")
</script>		
</hdiits:form>
</BODY>
<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>
<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request" />
<script type="text/javascript"  src="script/common/common.js"></script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="NewPensionReportForT" id="NewPensionReportForT"	method="POST"  encType="multipart/form-data" validate="true">
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="CMN.NPENACLST" bundle="${dcpsLabels}"></fmt:message></b> </legend>	
<table width="40%" align='center'>
	<tr>
		<td>
			<input type="radio" id="radioOptionSelection1" name="radioOptionSelection" value="L" onclick="showDDOWise(this);" />
			<fmt:message key="CMN.LSTNMSTARTINGWITH" bundle="${dcpsLabels}"></fmt:message>
			<div id="tdAtoz"  style="display:none">
					<input type="text" name="txtAtoZ"  id="txtAtoZ" size="1"  onkeypress="upperCase(event);" />
					<fmt:message key="CMN.ATOZ" bundle="${dcpsLabels}" ></fmt:message>
			</div>
		</td>
	</tr>
	<tr>	
		<td>
			<input type="radio" id="radioOptionSelection2" name="radioOptionSelection" value="A" onclick="showDDOWise(this);"/>
			<fmt:message key="CMN.ALLEMP" bundle="${dcpsLabels}"></fmt:message>
		</td>
	</tr>
	
	<tr>	
		<td>
			<input type="radio" id="radioOptionSelection3" name="radioOptionSelection" value="T" onclick="showDDOWise(this);"/>
			<fmt:message key="CMN.ALLDDO" bundle="${dcpsLabels}"></fmt:message>
		</td>
	</tr>
</table>
</fieldset>	
<table align='center'>
<tr>
<td>
<hdiits:button id="btnGenReport" name="btnGenReport" type="button" captionid="CMN.GENREP" bundle="${dcpsLabels}" onclick="generateReportForT();" classcss="bigbutton" />&nbsp;&nbsp;&nbsp;
<hdiits:button id="btnClose" name="btnClose" type="button" captionid="BTN.CLOSE" bundle="${dcpsLabels}" onclick="winCls();" />
</td>
</tr>
</table>
</hdiits:form>
<script>
function generateReportForT()
{

	if( validatePensionReport())
	{
	
			var	url = "ifms.htm?actionFlag=reportService&reportCode=700060&action=generateReport";
	
			if(document.getElementById("radioOptionSelection1").checked)
			{
				 url=url+"&optionSelected=L&letter="+document.getElementById("txtAtoZ").value;
				
				
			}
			else if(document.getElementById("radioOptionSelection2").checked)
			{
				
				url=url+'&optionSelected=A';
			}
			else if(document.getElementById("radioOptionSelection3").checked)
			{url=url+'&optionSelected=T';
			}//	var url="ifms.htm?actionFlag=getPrintAllBill";
			
		
			url=url+'&asPopup=TRUE';
			document.NewPensionReportForT.action=url;
			document.NewPensionReportForT.submit();
		
		
				}
		
	}
function validatePensionReport()
{
	
	 if(document.getElementById("radioOptionSelection1").checked)
		  {
		  
			if(document.getElementById("txtAtoZ").value == "")
				{
					alert("Please enter the first letter of the name.")
					document.getElementById("txtAtoZ").focus();
					return false;
				}
			if(!((document.getElementById("txtAtoZ").value).length==1))
				{
					alert("Please enter only single letter.");
					document.getElementById("txtAtoZ").value="";
					document.getElementById("txtAtoZ").focus();
					return false;
				}
	}
	 return true;
}
function upperCase(event)
{

	if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
	if(((window.event.keyCode >32 && window.event.keyCode <= 57 && window.event.keyCode != 46 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
	{
		window.event.keyCode = 0;
	}	
}
function showDDOWise(obj)
{
	
	
	if(obj.value == 'L')
	{
		document.getElementById("tdAtoz").style.display='inline';
	}
	else if(obj.value =='A')
	{
		document.getElementById("tdAtoz").style.display='none';
	}
	else if(obj.value =='T')
	{
		document.getElementById("tdAtoz").style.display='none';
	}
	
}
</script>

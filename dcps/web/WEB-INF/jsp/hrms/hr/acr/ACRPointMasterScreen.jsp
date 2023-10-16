<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request" />
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/hr/acr/acrHierarchyGrp.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="desgnListE" value="${resValue.desgnList}"></c:set>
<c:set var="locList" value="${resValue.locList}"></c:set>
<c:set var="reviewingOfficerList" value="${resValue.reviewingOfficerList}"></c:set>
<c:set var="reviewingOfficerListE" value="${resValue.reviewingOfficerList}"></c:set>
<c:set var="desgnList" value="${resValue.desgnList}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="yearE" value="${resValue.year}"></c:set>
<c:set var="contextPath" scope="request" />
<script>
var xmlHttp=null;
function disableMeAndGetPoint(dsgnCmb)
{
	showProgressbar("Please Wait... ");	
	var dsgnCode = dsgnCmb.value;	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) 
	{
	  hideProgressbar();
	  alert ("Your browser does not support AJAX!");
	  return;
	}			
	dsgnCmb.disabled=true;
	var url='hdiits.htm?actionFlag=getAllPointOnDsgnCode&dsgnCode='+dsgnCode;
	xmlHttp.open("POST",encodeURI(url),false);	
	xmlHttp.onreadystatechange = showAllPoints;
	xmlHttp.send(null);			
}
function showAllPoints()
{
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) 
	{
		hideProgressbar();
		var dscXML=xmlHttp.responseText;		
		try
		{
			var xmlDOM = top.frames['pointTable'].getDOMFromXML(dscXML);		
			var pointXML=xmlDOM.getElementsByTagName('POINT_XML');		
			var dsgnName=xmlDOM.getElementsByTagName('DSGNNAME');
			dsgnNameValue=dsgnName[0].childNodes[0].text;
			
			for ( var i = 0 ; i < pointXML.length ; i++ )
	   		{
				pointXMLValue=pointXML[i].childNodes[0].text;
				
				var year=xmlDOM.getElementsByTagName('YEAR');
				yearCount=year[i].childNodes[0].text;
				yearValue = yearCount;
				++yearCount;
				yearValue=yearValue+"-"+yearCount;
		
				var autho=xmlDOM.getElementsByTagName('AUTHO');
				authoValue=autho[i].childNodes[0].text;
				
				var pointEN=xmlDOM.getElementsByTagName('POINT_EN');
				pointENValue=pointEN[i].childNodes[0].text;		
						
				var pointGU=xmlDOM.getElementsByTagName('POINT_GU');
				pointGUValue=pointGU[i].childNodes[0].text;	
				
				var displayFieldArray = new Array(dsgnNameValue,authoValue,pointENValue,pointGUValue,yearValue);
				top.frames['pointTable'].addDBDataInTable('txnAdd', 'encXML',displayFieldArray,pointXMLValue,'editRecordAcr','deleteRec','');
				top.frames['pointTable'].recCountInc();
			}									
			document.getElementById('pointTableDiv').style.display='';
			document.getElementById('submitButton').disabled=false;
		}catch(e){}
	}
}
function refreshPage()
{
	if(confirm("<fmt:message  bundle="${commonLables1}" key="HR.ACR.RefPage"/>")==true)
	{
		window.location.reload(true); 	
	}
}
function validatePage()
{
	var year=document.getElementById("selectAuthority");
	if(document.getElementById("selectDesgn").value=="")
	{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');
		return false;
	}
	else if(document.getElementById("selectAuthority").value=="")
	{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');
		return false;
	}
	else if(document.getElementById("year").value=='-1')
	{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');
		return false;
	}
	return true;
}
function validation(buttonName)
{
		if(document.getElementById("selectDesgn").value=="")
		{
			alert('<fmt:message  bundle="${commonLables}" key="HR.ACR.selectDesgnMandatory"/>');
			try {
				document.getElementById("selectDesgn").focus();
			}catch(e){}
			return;
		}
		else if(document.forms[0].selectAuthority.value=='-1')
		{
			alert('<fmt:message  bundle="${commonLables}" key="HR.ACR.selectAuthorityMandatory"/>');
			document.forms[0].selectAuthority.focus();
			return;
		}
		else if(document.forms[0].year.value=='-1' && document.forms[0].oldYear.value==0)
		{
			alert('<fmt:message  bundle="${commonLables}" key="HR.ACR.yearMandatory"/>');
			return;
		}
		else if(document.getElementById("GoalEn").value=="")
		{
			alert('<fmt:message  bundle="${commonLables}" key="HR.ACR.goalEnMandatory"/>');
			document.getElementById("GoalEn").focus();
			return;
		}
		else if(document.getElementById("GoalGu").value=="")
		{
			alert('<fmt:message  bundle="${commonLables}" key="HR.ACR.goalGuMandatory"/>');
			document.getElementById("GoalGu").focus();
			return;
		}
		else if (isValidData(document.getElementById("GoalGu"))==false){document.getElementById("GoalGu").focus();}		
		else if (isValidData(document.getElementById("GoalEn"))==false){document.getElementById("GoalEn").focus();}		
		else
		{
			/*		
			var desgSelArray=getSelectedValues(document.getElementById('selectDesgn'));
			var authSelArray=getSelectedValues(document.getElementById('selectAuthority'));
			
		  	var desgSel='';
		  	var authSel='';
		  	
			for(i=0;i<desgSelArray.length;i++)
			{
				desgSel=desgSelArray[i]+"~"+desgSel;
			}
			for(i=0;i<authSelArray.length;i++)
			{
				authSel=authSelArray[i]+"~"+authSel;
			}
			*/			
			var displayFieldArray = new Array('selectDesgn','selectAuthority','GoalEn','GoalGu','year','updateCode','oldYear');
			if(buttonName == 'addAcr')
			{
				top.frames['pointTable'].addOrUpdateRecordAcr('addRecord', 'ACRAddMultiplePoints', displayFieldArray,false);
				document.getElementById('pointTableDiv').style.display='';
				document.getElementById('submitButton').disabled=false;
			}
			else if(buttonName == 'updateAcr')
			{
				top.frames['pointTable'].addOrUpdateRecordAcr('updateRecord', 'ACRAddMultiplePoints', displayFieldArray,false);
				document.getElementById('submitButton').disabled=false;
			}
		}
}
function isValidData(txtarea)
{	
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
	str1="`~!#$%^&*+|"
	for(i=0;i<len;i++)
	{		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SpecialCharsNotAllowed"/>');			
			return false;
		}		
	}
	return true;
}
function submitFullPage()
{
	/*
	if(validatePage())
	{
		top.frames['addExistingPointsDetails'].submitCheckedContent();
		top.frames['pointTable'].submitIFrame();
		document.action="./hrms.htm?actionFlag=submitAcrMasterPoints";
		document.AcrPointMst.submit();
	}
	else
	{
		return false;	
	}*/
	document.getElementById('submitButton').disabled=true;
	showProgressbar("Please Wait... ");	
	top.frames['addExistingPointsDetails'].submitCheckedContent();
	top.frames['pointTable'].submitIFrame();
	document.action="./hrms.htm?actionFlag=submitAcrMasterPoints";
	document.AcrPointMst.submit();	
}


</script>

<hdiits:form name="AcrPointMst" method="POST" validate="false" action="./hrms.htm?actionFlag=submitAcrMasterPoints" encType="multipart/form-data">
	<hdiits:hidden name="code" id="code" />	
	<hdiits:hidden name="oldYear" id="oldYear" default="0"/>	
	<hdiits:hidden name="deleteData" id="deleteData" default=""/>
	<hdiits:hidden name="updateCode" id="updateCode" default="0"/>
	<hdiits:hidden name="hiddenField" id="hiddenField" />
	<hdiits:hidden name="hiddenCheckBox" id="hiddenCheckBox" />
	<hdiits:hidden name="submitCounter" id="submitCounter" default="0"/>
	<hdiits:fieldGroup collapseOnLoad="false" id="acr_point_field1" titleCaptionId="HR.ACR.GroupDetails" bundle="${commonLables}">
	<table width="100%">		
		<tr align="center" colspan="4">

		</tr>

		<tr align="center" colspan="4">

		</tr>

		<tr align="center" colspan="4">

		</tr>

		<tr align="center" colspan="4">
			<td align="left" colspan="1"><b><fmt:message
				key="HR.ACR.Designation" bundle="${commonLables}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
			</td>
			<td align="left" colspan="1"><hdiits:select
				captionid="HR.ACR.Designation" bundle="${commonLables}" tabindex="1"
				name="selectDesgn" multiple="false" id="selectDesgn"  mandatory="true"
				sort="true" onchange="disableMeAndGetPoint(this);">				
				<option value=""><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
				<c:forEach var="desgnList" items="${desgnList}">
					<option value="<c:out value="${desgnList.dsgnCode}" />"><c:out
						value="${desgnList.dsgnName}" /></option>
				</c:forEach>				
			</hdiits:select></td>
			<td align="left" colspan="1"><b><fmt:message
				key="HR.ACR.Authority" bundle="${commonLables}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
			</td>
			<td align="left" colspan="1"><hdiits:select
				captionid="HR.ACR.Authority" bundle="${commonLables}" tabindex="2"
				name="selectAuthority" id="selectAuthority"
				mandatory="true" sort="true"
				multiple="false">
				<option value="-1"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
				<c:forEach var="reviewingOfficerList"
					items="${reviewingOfficerList}">
					<option value="<c:out value="${reviewingOfficerList.lookupId}" />">
					<c:out value="${reviewingOfficerList.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="1"><b><fmt:message
				key="HR.ACR.Year" bundle="${commonLables}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
			</td>
			<td id="yearTD" align="left" colspan="1"><hdiits:select
				captionid="HR.ACR.Year" bundle="${commonLables}" tabindex="3"
				name="year" id="year" mandatory="true"
				sort="true">
				<option value="-1"><fmt:message	key="HR.ACR.Select" bundle="${commonLables}" /></option>
				<script>
					addYearsInCmb('year','${year}');
				</script>
			</hdiits:select></td>
			<td id="oldYearTD" align="left" style="display:none" colspan="1">				
				<hdiits:text name="yearLabel" id="yearLabel" default="" readonly="true" style="background-color:lightblue"/>
			</td>
		</tr>
		
		<tr align="center" colspan="4">

		</tr>

		<tr align="center" colspan="4">

		</tr>
		</table>
		</hdiits:fieldGroup>
		<BR>
		<hdiits:fieldGroup collapseOnLoad="false" id="acr_point_field2" titleCaptionId="HR.ACR.SetPoints" bundle="${commonLables}">
			<table width="100%">		
			<tr align="center" colspan="4">
	
			</tr>
	
			<tr align="center" colspan="4">
	
			</tr>
	
			<tr width="100%" colspan="4">
				<td width="50%" colspan="2"><b><fmt:message
					key="HR.ACR.PointEnglish" bundle="${commonLables}" /><fmt:message
					key="HR.ACR.MaxLimit" bundle="${commonLables}" /></b> </td>
	
				<td width="50%" colspan="2"><b><fmt:message
					key="HR.ACR.PointGujarati" bundle="${commonLables}" /><fmt:message
					key="HR.ACR.MaxLimit" bundle="${commonLables}" /></b> </td>
			</tr>
			<tr width="100%" colspan="4">
	
				<td width="50%" colspan="2"><hdiits:textarea mandatory="true"
					cols="60" name="GoalEn" tabindex="5" id="GoalEn"
					captionid="HR.ACR.PointEnglish" bundle="${commonLables}"
					maxlength="4000" /></td>
	
				<td width="50%" colspan="2"><hdiits:textarea mandatory="true"
					cols="60" name="GoalGu" tabindex="6" id="GoalGu"
					captionid="HR.ACR.PointGujarati" bundle="${commonLables}"
					maxlength="4000" /></td>
			</tr>
			<tr align="center" colspan="4">
				<td align="center" id="tdbtnAdd" colspan="4" width="100%"><hdiits:button
					type="button" name="btnAdd" id="btnAdd" value="Add" tabindex="7"
					captionid="HR.ACR.Add" bundle="${commonLables}"
					onclick="validation('addAcr');" /> 
					<hdiits:button type="button"
					id="updateButton" name="btnUpdate" value="Update" readonly="true"
					captionid="HR.ACR.Update" bundle="${commonLables}"
					onclick="validation('updateAcr');" />
					<hdiits:button type="button"
					id="btnReset" name="btnReset" readonly="false"
					captionid="HR.ACR.Reset" bundle="${commonLables}"
					onclick="refreshPage();" />
				</td>
			</tr>
			<tr align="center" colspan="4">
			</tr>
			<tr align="center" colspan="4">
			</tr>
		</table>	
	<Div id="pointTableDiv" width="100%" style="display:none"><iframe
		name="pointTable" id="pointTable" src="hdiits.htm?viewName=pointTable"
		width="100%" height="30%" frameborder="1" scrolling="yes"
		style="overflow-x:hidden;overflow-y:scroll;"> </iframe></DIV>	
	</hdiits:fieldGroup>	
	<BR>
	<hdiits:fieldGroup collapseOnLoad="true" id="acr_point_field3"  titleCaptionId="HR.ACR.addExisting" bundle="${commonLables}">	
		<Div id="addExisting" width="100%"><%@ include file="/WEB-INF/jsp/hrms/hr/acr/addExisting.jsp"%></DIV>
	</hdiits:fieldGroup>
	<table width="100%">
		<tr align="center">
			<td class="fieldLabel" align="center" colspan="4" width="100%">
			<hdiits:button type="button" id="submitButton" name="submitButton"
				captionid="HR.ACR.Submit" bundle="${commonLables}" readonly="true"
				onclick="submitFullPage();" /></td>
		</tr>
	</table>	

</hdiits:form>
<hdiits:validate locale="${locale}" controlNames="" />


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

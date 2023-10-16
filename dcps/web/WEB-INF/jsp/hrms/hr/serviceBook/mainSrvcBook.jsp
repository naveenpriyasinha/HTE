<%try { %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.ServiceBook.ServiceBook" var="serviceBookLabel" scope="request" />
<html>
<head>

<script type="text/javascript" src="script/hrms/hr/serviceBook/serviceBook.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript">
</script>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="mpgSrNo" value="${resValue.mpgSrNo}"></c:set>
<c:set var="attchId" value="${resValue.attchId}"></c:set>
<c:set var="mpgSrNo1" value="${resValue.mpgSrNo1}"></c:set>
<c:set var="attchId1" value="${resValue.attchId1}"></c:set>
<c:set var="userId" value="${resValue.setuserId}"></c:set>
<c:set var="personData" value="${resValue.personData}"></c:set>
<c:set var="Nominee" value="${resValue.Nominee}"></c:set>
<c:set var="Family" value="${resValue.Family}"></c:set>
<c:set var="Lang" value="${resValue.Lang}"></c:set>
<c:set var="Education" value="${resValue.Education}"></c:set>
<c:set var="Birth" value="${resValue.Birth}"></c:set>
<c:set var="Native" value="${resValue.Native}"></c:set>
<c:set var="Present" value="${resValue.Present}"></c:set>
<c:set var="Emergency" value="${resValue.Emergency}"></c:set>
<c:set var="Permanent" value="${resValue.Permanent}"></c:set>
<c:set var="Phy" value="${resValue.Phy}"></c:set>
<BODY>
<hdiits:form name="mainSrvcBook" validate="true" action="hdiits.htm" encType="multipart/form-data" method="post">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<hdiits:caption captionid="SrvcBook.AppSrvcBook" bundle="${serviceBookLabel}" captionLang="single"></hdiits:caption>
		</b></a></li>
</ul>
</div>
<div id="ServiceBook" name="ServiceBook">
		<div id="tcontent1" class="tabcontent" tabno="0">
		<table class="tabtable">
			<tr bgcolor="#386CB7">
					<td class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
					<strong><u><fmt:message key="ServiceBook.EmpData" bundle="${serviceBookLabel}"></fmt:message></u></strong></font>
					</td>
			</tr>
			<tr>
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.EmpName" bundle="${serviceBookLabel}" /></b></td>
				<td class="fieldLabel" ><c:out value="${resValue.EmpName}"></c:out></td>
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.GpfBuckNo" bundle="${serviceBookLabel}" /></b></td>
				<td class="fieldLabel" ><c:out value="${resValue.GpfBuckNo}"></c:out></td>		
			</tr>
			<tr>
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.Desig" bundle="${serviceBookLabel}" /></b></td>
				<td class="fieldLabel" ><c:out value="${resValue.Desig}"></c:out></td>		
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.Depar" bundle="${serviceBookLabel}" /></b></td>		
				<td class="fieldLabel" ><c:out value="${resValue.Depar}"></c:out></td>		
			</tr>
			<tr>
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.OfficName" bundle="${serviceBookLabel}" /></b></td>
				<td class="fieldLabel" ><c:out value="${resValue.OfficName}"></c:out></td>		
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.Jurisdiction" bundle="${serviceBookLabel}" /></b></td>		
				<td class="fieldLabel" ><c:out value="${resValue.Jurisdiction}"></c:out></td>		
			</tr>	
			<tr>
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.EmpPhoto" bundle="${serviceBookLabel}" /></b></td>
				<td class="fieldLabel" ><img width="100px" height="100px" id="myImage" name="myImage" ondblclick=""></td>	
				<td class="fieldLabel" ><b><hdiits:caption captionid="ServiceBook.EmpThumb" bundle="${serviceBookLabel}" /></b></td>		
				<td class="fieldLabel" ><img width="100px"  height="100px" id="myImage_thumb" name="myImage_thumb" ondblclick=""></td>
			</tr>
		</table>
<!-- Main Personal Table Dtls --> 	
	<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Personal Details</u></strong></font>			
			<a href="javascript:void(0);" onclick="HidePersonalTab();"><img id="expandExpMainTable" src="images/expand.gif" /></a>
		</td></tr>					
	</table>		
<!-- Personal Dtls --> 		
	<table class="tabtable" id="personalTable">	
		<tr bgcolor="#386CB7">
		<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
		<strong><u>Personal Data</u></strong></font>			
		<a href="javascript:void(0);" onclick="expandCollapseExp('Personalfile');"><img id="expandExpPersonalfile" src="images/expand.gif" /></a>
		</td></tr>
	</table>						
	<span id="Personalfile">				
		${personData}
	</span>
<!-- Birth Place Dtls --> 		
	<table class="tabtable" id="birthTable">	
		<tr bgcolor="#386CB7">
		<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
		<strong><u>Birth Place Details</u></strong></font>			
		<a href="javascript:void(0);" onclick="expandCollapseExp('Birth');"><img id="expandExpBirth" src="images/expand.gif" /></a>
		</td></tr>
	</table>						
	<span id="Birth">
			${Birth}
	</span>
<!-- Native Place Dtls --> 		
	<table class="tabtable" id="NativeTable">	
		<tr bgcolor="#386CB7">
		<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
		<strong><u>Domicile Details</u></strong></font>			
		<a href="javascript:void(0);" onclick="expandCollapseExp('nativefile');"><img id="expandExpnativefile" src="images/expand.gif" /></a>
		</td></tr>
	</table>						
	<span id="nativefile">
			${Native}
	</span>		
<!-- Contact Dtls --> 	
	<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Contact Details</u></strong></font>			
			<a href="javascript:void(0);" onclick="HideContactTab();"><img id="expandExpConatactTable" src="images/expand.gif" /></a>
		</td></tr>					
	</table>
<!-- Address Present Dtls --> 		
	<table class="tabtable" id="PresentTable">
		<tr bgcolor="#386CB7">
			<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Address(Present)</u></strong></font>
			<a href="javascript:void(0);" onclick="expandCollapseExp('Present');"><img id="expandExpPresent" src="images/expand.gif" /></a>
		</td></tr>					
	</table>	
	<span id="Present">
				${Present}		
	</span>
<!-- Address Permanent Dtls --> 			
	<table class="tabtable" id="PermanentTable">
		<tr bgcolor="#386CB7">
			<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Address(Permanent)</u></strong></font>			
			<a href="javascript:void(0);" onclick="expandCollapseExp('Permanent');"><img id="expandExpPermanent" src="images/expand.gif" /></a>
		</td></tr>					
	</table>
	<span id="Permanent">
		${Permanent}
	</span>
<!-- Emergency Contact Dtls --> 			
	<table class="tabtable" id="EmergencyTable">
		<tr bgcolor="#386CB7">
			<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Emergency Contact Details</u></strong></font>			
			<a href="javascript:void(0);" onclick="expandCollapseExp('Emergency');"><img id="expandExpEmergency" src="images/expand.gif" /></a>
		</td></tr>					
	</table>
	<span id="Emergency">
		${Emergency}
	</span>
<!-- Physical Dtls --> 	
		<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Physical Details</u></strong></font>			
			<a href="javascript:void(0);" onclick="expandCollapseExp('Phylfile');"><img id="expandExpPhylfile" src="images/expand.gif" /></a>
		</td></tr>					
		</table>	
		<span id="Phylfile">
				${Phy}
		</span>		
<!-- Education Dtls --> 		
		<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Education Details</u></strong></font>			
			<a href="javascript:void(0);" onclick="HideEduTab();"><img id="expandExpEduTable" src="images/expand.gif" /></a>
		</td></tr>					
		</table>
<!-- Qualification Dtls --> 		
		<table class="tabtable" id="EduFileTable">
		<tr bgcolor="#386CB7">
			<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Qualification Details</u></strong></font>			
			<a href="javascript:void(0);" onclick="expandCollapseExp('EduFile');"><img id="expandExpEduFile" src="images/expand.gif" /></a>
		</td></tr>					
		</table>
		<span id="EduFile">
			${Education}
		</span>
<!-- Language Dtls --> 		
		<table class="tabtable" id="LangFileTable">
		<tr bgcolor="#386CB7" >
			<td align="center" class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Language Details</u></strong></font>			
			<a href="javascript:void(0);" onclick="expandCollapseExp('LangFile');"><img id="expandExpLangFile" src="images/expand.gif" /></a>
		</td></tr>					
		</table>
		<span id="LangFile">
			${Lang}
		</span>
<!-- Family Dtls --> 				
		<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Family Data</u></strong></font>			
			<a href="javascript:void(0);" onclick="expandCollapseExp('Fmfile');"><img id="expandExpFmfile" src="images/expand.gif" /></a>
		</td></tr>					
		</table>
		<span id="Fmfile">
			${Family}
		</span>
		
		<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" valign="middle" colspan="5" ><font color="#ffffff">
			<strong><u>Nominee Data</u></strong></font>			
			<a href="javascript:void(0);" onclick="expandCollapseExp('Nomineefile');"><img id="expandExpNomineefile" src="images/expand.gif" /></a>
		</td></tr>					
		</table>
		<span id="Nomineefile">
			${Nominee}
		</span>
</div>	
</div>
<script type="text/javascript">	
	if('${attchId}'!='')	
		document.getElementById('myImage').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${attchId}'+"&attachmentSerialNumber="+'${mpgSrNo}';
	if('${attchId1}'!='')			
		document.getElementById('myImage_thumb').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${attchId1}'+"&attachmentSerialNumber="+'${mpgSrNo1}';
	initializetabcontent("maintab");
	
	function expandCollapseExp(passObj)
	{
		if (document.getElementById("expandExp"+passObj) != null)
		{
			var imgSrcPath = new String(document.getElementById("expandExp"+passObj).src);
			
			if (imgSrcPath.indexOf("expand.gif") != -1)
			{
				document.getElementById("expandExp"+passObj).src = imgSrcPath.replace("expand.gif","collapse.gif");
				viewExpDetail(passObj);
			}
			else
			{
				document.getElementById("expandExp"+passObj).src = imgSrcPath.replace("collapse.gif","expand.gif");
				hideExpDetail(passObj);
			}
		}
	}
	function viewExpDetail(passObj)
	{
		document.getElementById(passObj).style.display='';
	}
	function hideExpDetail(passObj)
	{
		document.getElementById(passObj).style.display='none';
	}	
	document.getElementById('Fmfile').style.display='none';
	document.getElementById('Nomineefile').style.display='none';		
	document.getElementById('EduFile').style.display='none';
	document.getElementById('Personalfile').style.display='none';		
	document.getElementById('EduFileTable').style.display='none';	
	document.getElementById('LangFileTable').style.display='none';	
	document.getElementById('LangFile').style.display='none';
	document.getElementById('EmergencyTable').style.display='none';
	document.getElementById('PermanentTable').style.display='none';				
	document.getElementById('PresentTable').style.display='none';
	document.getElementById('Emergency').style.display='none';
	document.getElementById('Permanent').style.display='none';	
	document.getElementById('Present').style.display='none';
	document.getElementById('Phylfile').style.display='none';	
	document.getElementById('personalTable').style.display='none';
	document.getElementById('NativeTable').style.display='none';			
	document.getElementById('birthTable').style.display='none';
	document.getElementById('Personalfile').style.display='none';
	document.getElementById('Birth').style.display='none';	
	document.getElementById('nativefile').style.display='none';		
</script>
<hdiits:validate locale="${locale}" controlNames="" />
</hdiits:form>
</BODY>
</html>

<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>
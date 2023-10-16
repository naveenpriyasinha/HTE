<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>

<fmt:setBundle basename="resources.workflow.FMS_TEMPLables"	var="fmsTempLables" scope="request" />
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="fmsCategoryList" value="${resultMap.fmsCategoryList}"></c:set>

<hdiits:form name="frmFmsCommuniqueDtls" id="frmFmsCommuniqueDtlsid" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="FMS.STDCOMMDTLS" bundle="${fmsTempLables}" />
			</a>
		</li>
	</ul>
</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent">
	<br>
	<br>
		<b>
			<font style="font-size: 4;font-style: normal">
				<center>
					<hdiits:caption captionid="FMS.STDCOMMDTLS" bundle="${fmsTempLables}" />
				</center>
			</font>
		</b> 
		<br>
		<br>
		<hr>			
		<table align="center" border="0">				
			<tr>		
				<td align="center">
					<hdiits:caption captionid="FMS.NEWSTDCOMM" bundle="${fmsTempLables}"/>
				</td>		
				<td align="left">
					<hdiits:textarea name="txtCommDtls" captionid="FMS.NEWSTDCOMM" bundle="${fmsTempLables}" cols="50" rows="100"/>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>		
				<td>
					<hdiits:button type="button" name="btnAdd" captionid="FMS.ADD" bundle="${fmsTempLables}" onclick="addData()"/>
				</td>			
				<td>
					<hdiits:button type="button" name="btnCancel" captionid="FMS.CANCEL" bundle="${fmsTempLables}" onclick="clearAll()"/>
				</td>
			</tr>			
		</table>				
	</div>
</div>
<script language="javascript">	
function txtValidation()
{
	if(document.forms[0].txtCommDtls.value=="")
	{
		alert("Please Enter The Communique Details!!!");
		document.forms[0].txtCommDtls.focus();
		return false;
	}
	else
	{
		return true;
	}
}

function clearAll()
{	
	document.forms[0].txtCommDtls.value="";
	var winname="StandardCommuniqueDetails";
	win=window.open("${contextPath}/hdiits.htm?actionFlag=viewCommuniqueDtls", winname);
	win.focus();	
	self.close();		
}
function addData()
{
	if(txtValidation())
	{
		var url = "${contextPath}/hdiits.htm?actionFlag=insertCommuniqueDtls&txtCommuniqueDtls="+document.forms[0].txtCommDtls.value;	
		alert(url);
		document.forms[0].action=url;		
		document.forms[0].submit();		
	}
}
</script>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
</hdiits:form>
<%
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>

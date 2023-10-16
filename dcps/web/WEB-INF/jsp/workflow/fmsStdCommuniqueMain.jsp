<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
<c:set var="CommuniqueStdTempMstList" value="${resultMap.CommuniqueStdTempMstList}"></c:set>

<hdiits:form name="frmFmsStdCommunique" id="frmFmsStdCommuniqueid" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
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
	<div id="tcontent1" class="tabcontent"><br>
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
		<c:if test="${CommuniqueStdTempMstList ne null}">			
			<display:table list="${CommuniqueStdTempMstList}" id="CommuniqueStdTempMstList" style="width:100%" pagesize="20" requestURI="">				
				<display:column style="text-align:center" class="tablecelltext"  media="HTML">
					<hdiits:radio id="${CommuniqueStdTempMstList.srno}" value="${CommuniqueStdTempMstList.srno}" name="viewCommuniqueId" onclick="addStdCommuniqueDetail('${CommuniqueStdTempMstList.stdCommuniqueDtls}')"/>
				</display:column>			
				<display:column style="text-align:center" class="tablecelltext"  sortable="false" headerClass="datatableheader">
					
					<a href= "javascript:openDocument('${CommuniqueStdTempMstList.srno}')" >
					${CommuniqueStdTempMstList.stdCommuniqueDtls}
				</a>
				</display:column>  							
			</display:table> 			
		</c:if>			
						
		<br><br>	
		<table align="center" border="0">
			<tr>				
				<td>								
					<hdiits:button type="button" name="btnNew" captionid="FMS.NEW" bundle="${fmsTempLables}"  onclick="openCommunique()"/>
				</td>							
				<td>				
					<hdiits:button type="button" name="btnOk" captionid="FMS.OK" bundle="${fmsTempLables}" onclick="loadCommuniqueDetail()"/>				
				</td>
				<td>				
					<hdiits:button type="button" name="btnCancel" captionid="FMS.CANCEL" bundle="${fmsTempLables}" onclick="closeMe()"/>				
				</td>
			</tr>
		</table>				
	</div>
</div>
<script language="javascript">	
function openDocument(srno)
{	
	docWindow = window.open ("${contextPath}/hdiits.htm?actionFlag=openCommunique&srNo="+srno, "Document", "location=1,status=1,scrollbars=1,width=1020,height=650");
  	docWindow.moveTo(0,0);
  	self.close();
}
var communiqueDetail='';
function addStdCommuniqueDetail(commDetail)
{
	communiqueDetail = commDetail;
}

function loadCommuniqueDetail()
{
	
	if(communiqueDetail!='')
	{
		
		
	var converted_text = window.opener.replace(communiqueDetail,'\n',' ');
	
	if(window.opener.document.newcommuniquefrm)
	{
		window.opener.document.newcommuniquefrm.elements['rte1'].value=''
	}
	if(window.opener.document.replycommdtlfrm)
	{
		window.opener.document.replycommdtlfrm.elements['rte1'].value=''
	}
	
	window.opener.document.getElementById("communiquedetail").value=converted_text;
	
	var output = escape(converted_text);
	output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
	output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");
		
	window.opener.oRTE1.body.innerHTML = unescape(output);
		
	window.opener.document.getElementById('hdnrte1').value=window.opener.oRTE1.body.innerHTML ;
	
	window.close();
	}
	else
	{
		alert("Select One Communique Detail");
	}
}

function closeMe()
{
	window.close();
}

	
function openCommunique()
{
	var winname="NewStandardCommunique";
	win=window.open("${contextPath}/hdiits.htm?viewName=fms-CommuniqueDtls", winname);
	win.focus();	
	//self.close();
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

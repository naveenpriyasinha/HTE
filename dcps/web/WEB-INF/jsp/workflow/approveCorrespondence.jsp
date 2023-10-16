<%
try {


%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="HierarchyDetails" value="${resValue.HierarchyCustomList}"></c:set>
<c:set var="sendBackTo" value="${resValue.sendBackTo}"></c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>


<script>
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 
var cont='${resValue.ProceedFmsAction}';
var FmsAlertMsg='${resValue.FmsAlertMsg}'
if(cont=="No")
{
	if(FmsAlertMsg!="")
	{
		alert(FmsAlertMsg);
	}
	else
	{
		alert('<fmt:message key="WF.FmsActMsg" bundle="${fmsLables}"></fmt:message>');
	}
		
	window.close();
}
</script>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="ApproveFileForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >
<hdiits:hidden name="reject"  default="${resValue.rejectflag}"/>
<table>
	<tr>
		<td align="center">
			<hdiits:caption captionid="WF.SendFile" bundle="${fmsLables}"/>
		</td>
	</tr>
	<tr>
	<td>			
		<hdiits:caption captionid="WF.SendTo" bundle="${fmsLables}"/>
	</td>
	</tr>
		
			<tr>
				<td>
				<hdiits:radio name="radio" id="radio1" captionid="WF.KeepinSeenFolder" value="2"  default="2" validation="sel.isradio"  bundle="${fmsLables}" onclick="displayTable('2')"/>
				</td>
				
			</tr>
			
			<tr>
				<td>
				<CENTER><hdiits:radio name="radio" id="radio2" captionid="WF.SendtoSomeone" value="1"  bundle="${fmsLables}" onclick="displayTable('1')"/></CENTER>
				</td>
				
			</tr>
		
			
		
		<body>
				<display:table list="${HierarchyDetails}" pagesize="12" requestURI="" id="row"  style=" display:none;width:100%" >
					<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio"  name="postSelect"  value="${row.postId},${row.empName}"  onclick="showVal()"> </display:column>
					<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.postName}</display:column>
					<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
					<c:set var="counter" value="${counter+1}" />
					<c:set var="srNo" value="${srNo+1}" />
				</display:table>
		</body>
			<tr>
			</tr>
			<tr>
			</tr>		
			<tr><td>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<center><hdiits:button name="ok" captionid="WF.Ok" bundle="${fmsLables}" type="button" onclick="saveFile()"/></center>
			</td>
			</tr>					
		
		</table>
		</hdiits:form>
		<%
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		%>
		<script language="javascript">
		
		function displayTable(src)
		{
			if(document.getElementById("row")!=null)
			{
				if(src=='2')	{		
					document.getElementById("row").style.display="none";
					document.getElementById("ok").style.display='';
				}
				else{
					document.getElementById("row").style.display='';
					document.getElementById("ok").style.display="none";
				}
			}
		}
		 function selectAll()
				{
					var len = '${srNo}';
					for(var counter=0; counter<eval(len)-1; counter++)
					{
						document.forms[0].postSelect[counter].checked=true;
					}
				}
				
			function saveFile()
			{
			
				var element=document.getElementById('radio1');
				var msg;
				if(element.checked)
					msg='<fmt:message key="WF.MoveJob" bundle="${fmsLables}"></fmt:message>'
				else					
					msg='<fmt:message key="WF.FORWARDJOBTO" bundle="${fmsLables}"></fmt:message>'
				if(confirm(msg))
				{
					//window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=deactiveCorrespondece";
					window.opener.document.getElementById('wfAction').value='deactiveCorr';
					window.opener.document.getElementById("Converted_text").value = '';
					window.opener.submitForm('');								
					window.close();
					
				}
			}
				
		 
		  function deselectAll()
				{
					var len = '${srNo}';
					for(var counter=0; counter<eval(len)-1; counter++)
					{
						document.forms[0].postSelect[counter].checked=false;
					}
				}
			function showVal()
				{
					var radioObj = document.getElementsByName("postSelect");
					var radLength = document.getElementsByName("postSelect").length;
					for(var cnt=0;cnt<radLength;cnt++)
					{
						if(radioObj[cnt].checked == true)
						{
						
							
							window.opener.parent.document.forms[0].postId_Hidden.value =radioObj[cnt].value.substring(0,parseInt(radioObj[cnt].value.indexOf(",")));
							
							var empName=radioObj[cnt].value.substring(parseInt((radioObj[cnt].value.indexOf(","))+1),radioObj[cnt].value.length);
							
							if(confirm('<fmt:message key="WF.AppForMSg" bundle="${fmsLables}"></fmt:message>'))
							{
								window.opener.parent.document.forms[0].method="post";
								if(window.opener.parent.document.forms[0].DecisionFlag.value == 'corr')
								{
									window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence";
								}
								else if(window.opener.parent.document.forms[0].DecisionFlag.value == 'file')
								{
									window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1";
								}
								window.opener.document.getElementById('wfAction').value='approve';
								window.opener.document.getElementById("Converted_text").value = '';						
								window.opener.submitForm('');								
								window.close();
							}
							else
							{
								return false;
							}
						}
					}
				}
		 
		</script>
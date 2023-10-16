
<% try {  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="postName" value="${resValue.postName}"></c:set>
<c:set var="postId" value="${resValue.postId}"></c:set>
<c:set var="action" value="${resValue.action}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="sender" value="${resValue.sender}"></c:set>
<c:set var="ProceedFmsAction" value="${resValue.ProceedFmsAction}"></c:set>





<script type="text/javascript">
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 

var actionIntervalId;
function  performFmsAction(action)
{
	if(action=='forward')
	{
		window.opener.forward();
		window.clearInterval(actionIntervalId);
	}
	else if(action=='return')
	{
		window.opener.returndoc();
		window.clearInterval(actionIntervalId);
	
	}
	else if(action=='returndown')
	{
		window.opener.returndown();
		window.clearInterval(actionIntervalId);
	}
}

function addLoadEvent(abc) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
      window.onload = abc;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      abc();
    }
  }
}
function abc()
{
	callFunctionOnLoad()
}


try
{
var action;
var sender = '${sender}';

var cont='${resValue.ProceedFmsAction}';
var confirmmsg='${resValue.ConfirmMsg}';
var FmsAlertMsg='${resValue.FmsAlertMsg}'

}
catch(e)
{

}
 </script>
 
 


<script>

function callOnNo()
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

function callOnYes()
{
	if(!confirm(FmsAlertMsg))
	{
		self.close();
	}
	else
	{
		callRemainingFunction();
	}
	
}

function callFunctionOnLoad()
{
	if(cont=="No")
	{
		callOnNo()
	}
	else if(confirmmsg=="Yes")
	{
		callOnYes();
	} 
	else
	{
		
		callRemainingFunction();
	}	
}

</script>
<hdiits:form name="CorrespondenceMsg" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >





<table style="display: none" id="corrMvmntTable">
<tr>
<td>
<br><br>

<IMG src="themes/defaulttheme/images/workflow/pc2.png">
<br>


<b><label>${resValue.fromEmpName}</label></b>
</br>
<b><label>${resValue.fromDesgName}</label></b>


</td>
<td width="300" valign=middle>
<Marquee id="marq" loop=-1 scrollamount=5 direction=right>
<IMG align=middle src="themes/defaulttheme/images/workflow/file1.png" border="0">
</Marquee>
</td>
<td align=center>
<br><br>
<IMG src="themes/defaulttheme/images/workflow/pc2.png">
<br>

<b><label>${resValue.toEmpName}</label></b>
</br>
<b><label>${resValue.toDesgName}</label></b>
</td>
</tr>
</table>



</hdiits:form>
 
<script>
function callRemainingFunction()
{
	
		if ("${ProceedFmsAction}" == "Yes")
	 	{
		 	if("${postName}" == "noPost")
			{
				if ("${action}" == "forward")
				{
			 		alert('<fmt:message key="WF.NoPostForward" bundle="${fmsLables}"></fmt:message>');
			 	}
			 	if("${action}" == "return")
			 	{
					alert('<fmt:message key="WF.NoPostReturn" bundle="${fmsLables}"></fmt:message>');
				}
				if("${action}" == "returnDown")
				{
					alert('<fmt:message key="WF.NoPostReturn" bundle="${fmsLables}"></fmt:message>');
				}
	
			 	window.close();
			}
			if("${postName}" != "noPost")
			{
			if("${action}" == "forward")
			{
					if("${langId}" == "1")
					{

						if(!(confirm('<fmt:message key="WF.CorrForwardActMsg" bundle="${fmsLables}"></fmt:message><c:out value=" ${postName}?"></c:out>')))
						{
							window.close();
						}		
						else
						{
							document.getElementById('corrMvmntTable').style.display='';
							if(sender != ''){										
								window.opener.document.getElementById('forwardFromSupportHandler').value='yes';
								}
							window.opener.document.getElementById('postId_Hidden').value="${postId}";
							actionIntervalId=window.setInterval("performFmsAction('forward');",8000);
						}
					}
			}
			else if("${action}" == "return")
			{
				if("${langId}" == "1")
				{
					if(!(confirm('<fmt:message key="WF.CorrReturnActMsg" bundle="${fmsLables}"></fmt:message><c:out value=" ${postName}?"></c:out>')))
					{
							window.close();
					}		
					else
					{
					
						document.getElementById('corrMvmntTable').style.display='';
						window.opener.document.getElementById('postId_Hidden').value="${postId}";
						actionIntervalId=window.setInterval("performFmsAction('return');",8000);
										
						
					}
				}
			}
			else if("${action}" == "returnDown")
			{
				if("${langId}" == "1")
				{
					if(!(confirm('<fmt:message key="WF.CorrReturnActMsg" bundle="${fmsLables}"></fmt:message><c:out value=" ${postName}?"></c:out>')))
					{
							window.close();
					}		
					else
					{
						document.getElementById('corrMvmntTable').style.display='';
						window.opener.document.getElementById('postId_Hidden').value="${postId}";
						actionIntervalId=window.setInterval("performFmsAction('returndown');",8000);
					}
				}
			}
			if("${action}" == "forward")
			{
				if("${langId}" == "2")
				{
					if(!(confirm('<fmt:message key="WF.CommonActMsg" bundle="${fmsLables}"></fmt:message><c:out value=" ${postName}"></c:out><fmt:message key="WF.CorrForwardActMsg" bundle="${fmsLables}"></fmt:message>')))
					{
							window.close();
					}		
					else
					{
						
						document.getElementById('corrMvmntTable').style.display='';
						if(sender != ''){										
							window.opener.document.getElementById('forwardFromSupportHandler').value='yes';
							}
						window.opener.document.getElementById('postId_Hidden').value="${postId}";
						actionIntervalId=window.setInterval("performFmsAction('forward');",8000);
					}
				}
			}
			if("${action}" == "return")
			{
				if("${langId}" == "2")
				{
					if(!(confirm('<fmt:message key="WF.CommonActMsg" bundle="${fmsLables}"></fmt:message><c:out value=" ${postName}"></c:out><fmt:message key="WF.CorrReturnActMsg" bundle="${fmsLables}"></fmt:message>')))
					{
							window.close();
					}		
					else
					{
						document.getElementById('corrMvmntTable').style.display='';
						window.opener.document.getElementById('postId_Hidden').value="${postId}";
						actionIntervalId=window.setInterval("performFmsAction('return');",8000);
					}
					
				}
			}
			if("${action}" == "returnDown")
			{
				if("${langId}" == "2")
				{
					if(!(confirm('<fmt:message key="WF.CommonActMsg" bundle="${fmsLables}"></fmt:message><c:out value=" ${postName}"></c:out><fmt:message key="WF.CorrReturnActMsg" bundle="${fmsLables}"></fmt:message>')))
					{
							window.close();
					}		
					else
					{
						document.getElementById('corrMvmntTable').style.display='';
						window.opener.document.getElementById('postId_Hidden').value="${postId}";
						actionIntervalId=window.setInterval("performFmsAction('returndown');",8000);
					}
				}
			}
				
				
			}// end of if no post
	 	}// end of if yes
	
}
</script>

<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>

<script>
addLoadEvent(abc);
</script>

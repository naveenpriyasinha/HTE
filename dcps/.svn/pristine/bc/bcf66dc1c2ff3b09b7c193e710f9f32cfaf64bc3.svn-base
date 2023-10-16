<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="msgcode" value="${resValue.MESSAGECODE}"></c:set>
<c:set var="username" value="${resValue.UserName}"></c:set>
<c:set var="topost" value="${resValue.ToPost}"></c:set>
<c:set var="Action" value="${resValue.action}"></c:set>
<c:set var="winName" value="${resValue.winName}"></c:set>
<c:set var="fromCommonHomePage" value="${resValue.fromCommonHomePage}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="disposedFlag" value="${resValue.disposedFlag}"></c:set>
<c:set var="rejectflag" value="${resValue.rejectflag}"></c:set>
<c:set var="sendBackTo" value="${resValue.sendBackTo}"></c:set>
<script type="text/javascript">
window.moveTo(250,250);
window.resizeTo(500,300);

var wfaction='${Action}';

if(wfaction=="forward" || wfaction=="Return" || wfaction=="ReturnDown")
{

	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	var fileId="${resValue.fileId}";
	var  winobj=window.open('hdiits.htm?actionFlag=getNextNode&action=return&&fileId='+fileId,'wfaction_window_file',urlstyle);
	winobj.close();
}
</script>
<BR><BR><BR>
<hdiits:form name="getInbox" method="post" validate="true">
<hdiits:hidden name="fmsactionfrm"/>
<c:if test="${disposedFlag ne 'yes'}">
		<c:if test="${langId eq '1'}">
			<c:if test="${Action eq 'forward'}">
			<center><h3><fmt:message key="WF.FileForwardMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
					
			<c:if test="${Action eq 'Return'}">
			<center><h3><fmt:message key="WF.FileReturnMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'ReturnDown'}">
			<center><h3><fmt:message key="WF.FileReturnMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'Approve'}">
			
			
						<c:if test="${rejectflag eq 'yes'}">
								<center><h3><fmt:message key="WF.FileRejectMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
						</c:if>
						<c:if test="${rejectflag ne 'yes'}">
								<center><h3><fmt:message key="WF.FileApproveMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
						</c:if>		
			</c:if>
			
			<c:if test="${Action eq 'Hold'}">
			<center><h3><fmt:message key="WF.FileHoldMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'Release'}">
			<center><h3><fmt:message key="WF.FileReleaseMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'AssingToGroup'}">
			<center><h3><fmt:message key="WF.FileGroupMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
		</c:if>	
		<c:if test="${langId eq '2'}">
			<c:if test="${Action eq 'forward'}">
			<center><h3><c:out value="${username}"></c:out><fmt:message key="WF.FileForwardMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'Return'}">
			<center><h3><c:out value="${username}"></c:out><fmt:message key="WF.FileReturnMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'ReturnDown'}">
			<center><h3><c:out value="${username}"></c:out><fmt:message key="WF.FileReturnMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'Approve'}">
					<c:if test="${rejectflag eq 'yes'}">
								<center><h3><fmt:message key="WF.FileRejectMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
					</c:if>
					<c:if test="${rejectflag ne 'yes'}">
								<center><h3><c:out value="${username}"></c:out><fmt:message key="WF.FileApproveMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
					</c:if>			
			</c:if>
			
			<c:if test="${Action eq 'AssingToGroup'}">
			<center><h3><fmt:message key="WF.FileGroupMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
			
		</c:if>
</c:if>
<c:if test="${disposedFlag eq 'yes'}">
		<center><h3><fmt:message key="WF.FileDisposeMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
</c:if>			
		
<br><center><hdiits:button name="ActionButtonb" type="button" value="Ok" onclick="WorkflowAction()"/></center>
</hdiits:form>
<script>
function homepageajaxfunction()
{
		try
    	{   
    	// Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{    // Internet Explorer    
			try
      		{
      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
      		   
      		}
		    catch (e)
		    {
		          try
        		  {
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}
    
        
        var url = "${contextPath}/hdiits.htm?actionFlag=FMS_getCommHomePageList";
        
        xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					if(XMLDoc!=null)
					{
						
						var InboxList = XMLDoc.getElementsByTagName('InboxList');	

						var DocUr="";
						var DocName="";
						var TransactionNumber="";
						var FromPost = "";
						var RecievedDate="";
	           			for ( var i = 0 ; i < InboxList.length ; i++ )
	     				{
	     					DocUrl=InboxList[i].childNodes[0].text;
	     					DocName=InboxList[i].childNodes[1].text;
	     					TransactionNumber=InboxList[i].childNodes[2].text;
	     					FromPost = InboxList[i].childNodes[3].text;
	     					RecievedDate = InboxList[i].childNodes[4].text;
	     					var cnt=i+1;
	     					var docnameid="${sendBackTo}_docnameid"+cnt;
	     					var docurlid="${sendBackTo}_docurlid"+cnt;
	     					var strUrl = "<u><a href=\"javascript:openDocument('"+DocUrl+"')\" ><font color='red'>" + TransactionNumber + "</font></a></u>";
	     					var FromPostId="${sendBackTo}_FromPostId"+cnt;
	     					var RecievedDateId="${sendBackTo}_RecievedDateId"+cnt;
	     					
	     					if(window.opener.document.getElementById(docnameid) != null)
	     					{
	     						window.opener.document.getElementById(docnameid).innerHTML=DocName;
		     					window.opener.document.getElementById(docurlid).innerHTML=strUrl;
		     					window.opener.document.getElementById(FromPostId).innerHTML = FromPost;
		     					window.opener.document.getElementById(RecievedDateId).innerHTML = RecievedDate;
	     					}
	     					else
	     					{
		     					window.opener.document.getElementById(docnameid).innerHTML=DocName;
		     					window.opener.document.getElementById(docurlid).innerHTML=strUrl;
		     					window.opener.document.getElementById(FromPostId).innerHTML = FromPost;
		     					window.opener.document.getElementById(RecievedDateId).innerHTML = RecievedDate;
	     					}
	     					
					    }// end of for
					 }//end of if
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}

function WorkflowAction()
{
	//alert("${username} ${resValue.userNameForAlert}");
	//return;
	var fromCommonHomePage="${resValue.fromCommonHomePage }";

	if(fromCommonHomePage=="yes")
	{
		homepageajaxfunction()
	}
	else
	{
		var wfwinname='${winName}';
		if("Inbox" == "${sendBackTo}")
		{
			win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forWorkList&displayMsg=${resValue.userNameForAlert}",wfwinname);
		}
		else
		{
			win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forFileInbox&displayMsg=${resValue.userNameForAlert}",wfwinname);
		}
		
	}	
	//window.clearInterval(intervalid);
	self.close();		
}
var intervalid;
window.clearInterval(intervalid);
intervalid=window.setInterval("WorkflowAction();",4000);	
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
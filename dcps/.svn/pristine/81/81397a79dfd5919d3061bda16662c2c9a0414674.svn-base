<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="username" value="${resValue.UserName}"></c:set>
<c:set var="topost" value="${resValue.ToPost}"></c:set>
<c:set var="Action" value="${resValue.Action}"></c:set>
<c:set var="forwardempname" value="${resValue.forwardempname}"></c:set>
<c:set var="wfwindowname" value="${resValue.wfwindowname}"></c:set>
<c:set var="disposedFlag" value="${resValue.disposedFlag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="rejectflag" value="${resValue.rejectflag}"></c:set>
<c:set var="fromCommonHomePage" value="${resValue.fromCommonHomePage}"></c:set>
<c:set var="sendBackTo" value="${resValue.sendBackTo}"></c:set>
<c:set var="corrNo" value="${resValue.corrNo}"></c:set>
<c:set var="fromwithinHiearchy" value="${resValue.fromwithinHiearchy}"></c:set>



<script type="text/javascript">
window.moveTo(250,230);
window.resizeTo(600,300);

var wfaction='${Action}';
var fromwithinHiearchy='${fromwithinHiearchy}';
var corrId="${resValue.corrId}";

if(fromwithinHiearchy=="yes")
{

	window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&fordisposeFlag=yes&corrId='+corrId, "wfaction_window", urlStyle1);
	var urlStyle1 ='width=650,height=380,toolbar=no,menubar=no,scrollbars=yes,location=no,top=150,left=200';
	
	var  winobj1=window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&fordisposeFlag=yes&corrId='+corrId, "wfaction_window", urlStyle1);
	winobj1.close();
}
else
{

if(wfaction=="forward" || wfaction=="Return")
{

	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	
	var  winobj=window.open('hdiits.htm?actionFlag=getCorrNextNode&action=forward&isMark=no&corrId='+corrId+"&sendBackTo=${sendBackTo}", "wfaction_window", urlstyle);
	winobj.close();
}
}



</script>
<BR><BR><BR>


<hdiits:form name="getInbox" method="post" validate="true">

<c:if test="${disposedFlag ne 'yes'}">
		<c:if test="${langId eq '1'}">
			<c:if test="${Action eq 'forward'}">
			<center><h3> <fmt:message key="WF.Correspondence" bundle="${fmsLables}"></fmt:message><c:out value="${corrNo}"></c:out><fmt:message key="WF.CorrForwardMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'Return'}">
			<center><h3><fmt:message key="WF.Correspondence" bundle="${fmsLables}"></fmt:message><c:out value="${corrNo}"></c:out><fmt:message key="WF.CorrReturnMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'approve'}">
				<c:if test="${rejectflag eq 'yes'}">
					<center><h3><fmt:message key="WF.CorrRejectMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
				</c:if>
				<c:if test="${rejectflag ne 'yes'}">
					<center><h3><fmt:message key="WF.CorrApproveMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
				</c:if>
				
			</c:if>	
			
			<c:if test="${Action eq 'SendToMany'}">
				<center><h3><fmt:message key="WF.CorrSendToManyMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			</c:if>
				
		
			<c:if test="${Action eq 'approveforward'}">
			
				<c:if test="${rejectflag eq 'yes'}">
					<center><h3><fmt:message key="WF.CorrRejectMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out><fmt:message key="WF.CorrApproveForwardMsg" bundle="${fmsLables}"></fmt:message><c:out value="${forwardempname}"></c:out> </h3></center>
				</c:if>
				<c:if test="${rejectflag ne 'yes'}">
					<center><h3><fmt:message key="WF.CorrApproveMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out><fmt:message key="WF.CorrApproveForwardMsg" bundle="${fmsLables}"></fmt:message><c:out value="${forwardempname}"></c:out> </h3></center>
				</c:if>
			
			
			</c:if>
		</c:if>	
		<c:if test="${langId eq '2'}">
			<c:if test="${Action eq 'forward'}">
			<center><h3><c:out value="${username}"> </c:out><fmt:message key="WF.Correspondence" bundle="${fmsLables}"></fmt:message><c:out value="${corrNo}"></c:out><fmt:message key="WF.CorrForwardMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'Return'}">
			<center><h3><c:out value="${username}"></c:out><fmt:message key="WF.Correspondence" bundle="${fmsLables}"></fmt:message><c:out value="${corrNo}"></c:out><fmt:message key="WF.CorrReturnMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
			</c:if>
			
			<c:if test="${Action eq 'approve'}">
					<c:if test="${rejectflag eq 'yes'}">
					<center><h3><fmt:message key="WF.CorrRejectMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
					</c:if>
					<c:if test="${rejectflag ne 'yes'}">
					<center><h3><c:out value="${username}"></c:out><fmt:message key="WF.CorrApproveMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
					</c:if>
			
			
			</c:if>
			
			<c:if test="${Action eq 'approveforward'}">
					<c:if test="${rejectflag eq 'yes'}">
						<center><h3><fmt:message key="WF.CorrRejectMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
					</c:if>
					<c:if test="${rejectflag ne 'yes'}">
						<center><h3><c:out value="${username}"></c:out><fmt:message key="WF.CorrApproveMsg" bundle="${fmsLables}"></fmt:message><c:out value="${forwardempname}"></c:out><fmt:message key="WF.CorrApproveForwardMsg" bundle="${fmsLables}"></fmt:message></h3></center>
					</c:if>	
			</c:if>
		</c:if>
		
	
		
</c:if>
<c:if test="${disposedFlag eq 'yes'}">

		<center><h3><fmt:message key="WF.CorrDisposeMsg" bundle="${fmsLables}"></fmt:message> </h3></center>
</c:if>		
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="WorkflowAction()"/></center>

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
		     					/* window.opener.document.getElementById(docnameid).innerHTML=DocName;
		     					window.opener.document.getElementById(docurlid).innerHTML=strUrl;
		     					window.opener.document.getElementById(FromPostId).innerHTML = FromPost;
		     					window.opener.document.getElementById(RecievedDateId).innerHTML = RecievedDate; */
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
	var fromCommonHomePage="${resValue.fromCommonHomePage }";

	if(fromCommonHomePage=="yes")
	{
		homepageajaxfunction()
		window.clearInterval(intervalid);
	}
	else
	{
		var wfwinname='${wfwindowname}'
		
		//hdiits.htm?actionFlag=getFilteredfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox
		if("Inbox" == "${sendBackTo}")
		{
			win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forWorkList&displayMsg=${resValue.userNameForAlert}",wfwinname);
		}
		else
		{
			win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forCorrespondenceInbox&displayMsg=${resValue.userNameForAlert}",wfwinname);
		}
						
		
		win.focus();
		window.clearInterval(intervalid);
	}
	self.close();	
}

var intervalid;
window.clearInterval(intervalid);
intervalid=window.setInterval("WorkflowAction();",5000);	
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
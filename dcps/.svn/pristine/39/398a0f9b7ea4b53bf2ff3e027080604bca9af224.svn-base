<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="sendBackTo" value="${resultMap.sendBackTo}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="ReplicateCorr"  method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="fileId" default="${param.fileId}"/>


<h3 align="center">
	Correspondence replica with number '${resultMap.corrNo}' is sent successfully to '${resultMap.postIdToForward}'
</h3>
<br><br>
<center><hdiits:button name="bt_save" type="button" captionid="WF.Ok" bundle="${fmsLables}" onclick="showOutgoingCorr('${param.fileId}')"/></center>

<script type="text/javascript">
	function showOutgoingCorr(fileId)
	{
		var fromCommonHomePage="${resultMap.fromCommonHomePage }";
		if(fromCommonHomePage=="yes")
		{
			homepageajaxfunction()
		}
		else
		{
			
			var wfwinname='${wfwindowname}';
			
			//hdiits.htm?actionFlag=getFilteredfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox
			if("Inbox" == "${sendBackTo}")
			{
				win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forWorkList&displayMsg=${resultMap.userNameForAlert}",wfwinname);
			}
			else
			{
				win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forCorrespondenceInbox&displayMsg=${resultMap.userNameForAlert}",wfwinname);
			}
							
			win.resizeTo(screen.width,screen.height);
			win.focus();
			window.clearInterval(intervalid);
		}
		self.close();	
	}

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
		     					alert(" d " + docnameid);
		     					alert(parent.window.opener.document.getElementById(docnameid));
		     					alert(parent.document.getElementById(docnameid));
		     					alert(parent.parent.document.getElementById(docnameid));
		     					alert(window.opener.parent.document.getElementById(docnameid));
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
		
</script>

</hdiits:form>
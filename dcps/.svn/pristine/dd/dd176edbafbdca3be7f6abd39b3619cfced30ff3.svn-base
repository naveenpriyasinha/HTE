<%@ include file="../core/include.jsp"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<c:set var="Show" value="${param.Show}"></c:set>

<script type="text/javascript" language="javascript">
var allNotificationIds = new Array();
var TdToResize;
var DivToResize;

function changeCursor()
{
	TdToResize = document.getElementById('tree-div-td');
	if((window.event.clientX >= 170) && (window.event.clientX <= 180)) 
		TdToResize.style.cursor = "col-resize";
	else
		TdToResize.style.cursor = "default";

}

function StartSize(event){

	TdToResize = document.getElementById('tree-div-td');   
	DivToResize = document.getElementById('tree-div');
            
    document.attachEvent("onmousemove", SizeGo);
    document.attachEvent("onmouseup",   SizeStop);
    window.event.cancelBubble = true;
    window.event.returnValue = false;

}

function SizeGo(event) {
    var x, y;
    x = window.event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;
    y = window.event.clientY + document.documentElement.scrollTop + document.body.scrollTop;

    if(x  > 170)
    {
	    TdToResize.style.width =  x - 25;
	    DivToResize.style.width =  x - 25;
    }
    
	window.event.cancelBubble = true;
    window.event.returnValue = false;
}

function SizeStop(event) {
    document.detachEvent("onmousemove", SizeGo);
    document.detachEvent("onmouseup",   SizeStop);
}



</script>

<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>">
</script>	

<script type="text/javascript" language="javascript" src="<c:url value="/script/workflow/WorkFlowHomePage.js"/>">
</script>	

<!-- <style type="text/css">
	#nav {display:none}
</style> -->

<%

	ResultObject result=(ResultObject)request.getAttribute("result");
	Map resultMap=(Map)result.getResultValue();
	String setStringinSession = (String)resultMap.get("getTreeString");
//	String docType = (String) resultMap.get("docType");
	String whatModule = (String) resultMap.get("moduleName");
	String menuName = (String) resultMap.get("menuName");
	

	session.setAttribute("Tree1",setStringinSession);
%>
<table width="100%" id="table1" align="left" border="1" cellspacing="0" cellpadding="2">
	<tr>
		<td colspan="3" height="100%" valign="bottom">
			<table width="100%"  style="vertical-align: baseline;">
				<tr>
					<td  height="100%" valign="bottom" width="85%" colspan="3">
					<div id="tabmenu" style="vertical-align: baseline">
							<ul id="maintab">	
								<li id="tab1" onclick="changecolor(this)"><a id='tab1_Link' href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forWorkList" ><fmt:message key="WF.WorkList"  bundle="${fmsLables}"></fmt:message></a></li>
								<li id="tab2" onclick="changecolor(this)"><a id='tab2_Link' href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=RecordRoom&moduleName=RecordRoom&menuName=forRecPending" ><fmt:message key="WF.SecondTab"  bundle="${fmsLables}"></fmt:message></a></li>
								<li id="tab3" onclick="changecolor(this)"><a id='tab3_Link' href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=DashBoard&moduleName=DashBoard&menuName=forDashBoard" ><fmt:message key="WF.ThirdTab"  bundle="${fmsLables}"></fmt:message></a></li>
								<li id="tab4" onclick="changecolor(this)"><a id='tab4_Link' href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=RefDocs&moduleName=RefDocs&menuName=forRefDocs&fromHomePageOfRefDocs=true" ><fmt:message key="WF.ReferenceDocs"  bundle="${fmsLables}"></fmt:message></a></li>
								<li id="tab5" onclick="changecolor(this)"><a id='tab5_Link' href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WFReportMenu&moduleName=WFReportMenu&menuName=forWFReports" ><fmt:message key="WF.FifthTab"  bundle="${fmsLables}"></fmt:message></a></li>
								<li id="tab6" onclick="changecolor(this)" style="display:none"><a id='tab6_Link' href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WFCatMenu&moduleName=WFCatMenu&menuName=forWFCatMenu" >Category Menu</a></li>
							</ul>
					</div>
					</td>
					<td valign="bottom">
					<table align="right" width="100%"><tr><td><a href="hdiits.htm?viewName=wf-fmsFileSearch">Search File/Tappal</a>&nbsp;&nbsp;<a href="hdiits.htm?actionFlag=getHomePage">Back</a></td></tr></table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100%">
			<table width="100%">
				<tr>
					<td width="15%"> 
						<div id="tree-div" style="overflow:auto; height:300px;width:150px;border:1px solid #c3daf9;" > 
							<jsp:include page="/WEB-INF/jsp/workflow/TreeForMenu.jsp"/>	
						</div> 
					</td> 
					<td width="85%">
						<table width="100%" height="100%" cellspacing="0" cellpadding="2" border="1" style="border:1px solid #c3daf9;">
							<tr>
								<td> 
						<%
							if(whatModule.trim().equals("WorkList"))
							{
								if("forFileInbox".equals(menuName.trim()))
								{
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=getFilteredfWorkList&docType=3&moduleName=WorkList&menuName=forFileInbox" scrolling="yes" style="border: 1px solid black;"></iframe>
						<%
								}
								else if("forCorrespondenceInbox".equals(menuName.trim()))
								{
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=getFilteredfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox" scrolling="yes"></iframe>
						<%
								}
								else if("forWorkList".equals(menuName.trim()))
								{					
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' frameborder="0" src="hdiits.htm?actionFlag=getDocListOfWorkList&docType=-1&moduleName=WorkList&menuName=forWorkList" scrolling="yes" ></iframe>
						<%
								}
								else if("UnreadFile".equals(menuName.trim()))
								{					
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' frameborder="0" src="hdiits.htm?actionFlag=getFilteredfWorkList&docType=3&moduleName=WorkList&menuName=forFileInbox&unread=true" scrolling="yes" ></iframe>
						<%
								}
								else if("UnreadCorr".equals(menuName.trim()))
								{					
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' frameborder="0" src="hdiits.htm?actionFlag=getFilteredfWorkList&docType=2&moduleName=WorkList&menuName=forFileInbox&unread=true" scrolling="yes" ></iframe>
						<%
								}
								else if("UnreadOther".equals(menuName.trim()))
								{					
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' frameborder="0" src="hdiits.htm?actionFlag=getDocListOfWorkList&docType=1&moduleName=WorkList&menuName=forWorkList&unread=true" scrolling="yes" ></iframe>
						<%
								}
								else if("UnreadIntimation".equals(menuName.trim()))
								{					
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' frameborder="0" src="hdiits.htm?actionFlag=getDocListOfWorkList&docType=4&moduleName=WorkList&menuName=forWorkList&unread=true" scrolling="yes" ></iframe>
						<%
								}
								else if("forOutbox".equals(menuName.trim()))
								{
							%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=getSentDocListForWorkflow" scrolling="yes"></iframe>
							<%
								}
								else
								{
						%>
									<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=getSentDocListForWorkflow" scrolling="yes"></iframe>
						<%
								}
							}
							else if(whatModule.trim().equals("DashBoard"))
							{
						%>
								<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=showBarChart&docType=DashBoard&moduleName=DashBoard&menuName=forDashBoard" scrolling="yes"></iframe>
						<%
							}
							else if("RefDocs".equals(whatModule))
							{
						%>
								<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=FMS_viewAllRefDocsAttachment&departId=1&fromHomePage=true" scrolling="yes"></iframe>
						<%
							}
							else if ("WFReportMenu".equals(whatModule.trim()))
							{
						%>
								<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=InwardRegSrch" scrolling="yes"></iframe>
						<%	
							}
							
							else
							{
						%>
								<c:choose>
									<c:when test="${Show eq 'ShowApproveCorr'}">
											<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=LoadStatus&stage=fms_Pending&forCorr=true&moduleName=RecordRoom&menuName=forRecPending" scrolling="yes"></iframe>
									</c:when>
									<c:otherwise>																			   
											<iframe id="dataFrame" name="dataFrame" width="100%" onload='resize_iframe()' src="hdiits.htm?actionFlag=LoadStatus&stage=fms_Pending&moduleName=RecordRoom&menuName=forRecPending" scrolling="yes"></iframe>
									</c:otherwise>
								</c:choose>
						<%
							}
						%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<script>
	toCallOnLoadOfWorkFlowPage("<%=whatModule%>");
	var ifrm = document.getElementById('dataFrame');
	   
	var t;
	 Event.observe(ifrm, 'load', 
		function checkFrame() { 
	       var iframe = document.frames['dataFrame'];
	       
	        if(iframe == null) {}
	        else {
	            if(iframe.document == null)
	            {}

	            else
	            {
		            if(iframe.document.getElementById("nav") != null)
		            {
			            iframe.document.getElementById("nav").style.display="none";
			            iframe.document.getElementById("header").style.display="none";
			            iframe.document.getElementById("toolbar").style.display="none";
			            iframe.document.getElementById("footer").style.display="none";
		            }
		            if(iframe.document.getElementById("verticalnav") != null)
		            	iframe.document.getElementById("verticalnav").style.display="none";
		        
		            if(iframe.document.getElementById("rightInfo") != null)
		            	iframe.document.getElementById("rightInfo").style.display="none";

		            if(iframe.document.getElementById("currentApplication") != null)
		            	iframe.document.getElementById("currentApplication").style.display="none";
	
	                //clearTimeout(t);
	            }
	        }
	    });

	var mm = "<%=whatModule%>";
	
	if("WFReportMenu" == mm)
	{
		t = setTimeout("checkFrame()",1000); 
	}
	function forAlertToUserForWorkFlow()
	{
		alert(" You have Recieved a new File ");

		alert(document.getElementById("InboxDiv"));
		if(document.getElementById("InboxDiv") != null)
		{
			homepageajaxfunction("Inbox");
		}
		else if(document.getElementById("dataFrame") != null)
		{
			var dynFrameSrc = document.getElementById("dataFrame").src; 
			document.getElementById("dataFrame").src = dynFrameSrc;
		}
	}
</script>
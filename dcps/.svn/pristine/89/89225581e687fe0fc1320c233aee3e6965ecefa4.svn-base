<%
String lStrcontentUsingAjax = "";
String lStrdialogview = "";
if(request.getParameter("contentUsingAjax") != null)
	lStrcontentUsingAjax = (String)request.getParameter("contentUsingAjax");
boolean iscontentUsingAjax = lStrcontentUsingAjax.equalsIgnoreCase("true")?true:false;

if(request.getParameter("dialogview") != null)
	lStrdialogview = (String)request.getParameter("dialogview");
boolean isdialogview = lStrdialogview.equalsIgnoreCase("true")?true:false;
if(isdialogview && iscontentUsingAjax)
	iscontentUsingAjax = false;

iscontentUsingAjax = false;//this line to stop everything using ajax
if(!iscontentUsingAjax)
{
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Mimic Internet Explorer 7 -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" >
<%@ include file="include.jsp"%>
<%@page import="org.springframework.web.util.WebUtils" %>
<%@page import="org.springframework.web.servlet.theme.SessionThemeResolver" %>
<fmt:setBundle basename="resources.Constants" var="constant" scope="request" />


<%
String themen = (String)WebUtils.getSessionAttribute(request,SessionThemeResolver.THEME_SESSION_ATTRIBUTE_NAME);
if( themen == null )
{
    themen="ifmsblue";
}
%>
<c:set var="themename" scope="session"><%=themen%></c:set>
<c:set var="taglibImagePath" scope="request"><%="themes/" + themen + "/images/taglib"%></c:set>
<%-- <c:out value="${themename}"></c:out> --%>
<html>
<script type="text/javascript" src='<c:url value="/script/common/WebSecurity.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/tabcontent.js"/>'></script>		
<script type="text/javascript" src='<c:url value="/script/common/ajax_saveData.js"/>'></script>		
<script type="text/javascript" src='<c:url value="script/common/modalDialog.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/keyboardNav.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/prototype.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/ajaxtags_parser.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/scriptaculous.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/builder.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/effects.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/controls.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/dragdrop.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/ajaxtags.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/ajaxselectoptgrptag.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/ajaxtags_controls.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/JSOC.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/login/frmUtils_1.0.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/login/ContextMenu.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/login/statusbar.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/login/validation.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/login/keybrdShortcut.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/window.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/login/ajaxLoadContentSubmitPage_1.0.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/behaviour.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/CalendarPopup.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/cmbBoxFillUp.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/attachment.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/themes/${themename}/windowmodal.css"/>' type="text/css">
<%-- Added by Jignesh Sakhiya  --%>

<head>
<!--[if IE]>
<link rel="stylesheet" href='<c:url value="/themes/${themename}/mainNavigation.css"/>' type="text/css"> 
<![endif]-->

<!--[if !IE]><!-->
<link rel="stylesheet" href='<c:url value="/themes/${themename}/mainNavigationFirefox.css"/>' type="text/css">
<!--<![endif]-->
		
		<!--[if IE]>
			<link rel="stylesheet" href="<c:url value="/themes/${themename}/navigationSupport.css"/>">

			<script type="text/javascript">
				window.mlrunShim = true;
			</script>
		<![endif]-->
		<!--[if lte IE 6]>
			<script type="text/javascript">
				window.forCalendar = true;
			</script>
		<![endif]-->			
		
<script type="text/javascript" src='<c:url value="/script/login/mainNavJs.js"/>'></script>



<base target="_self"> 

<fmt:setBundle basename="${themename}" var="hdiits" scope="application"/>
<fmt:requestEncoding value="UTF-8"/>



<c:set var="tcss"><spring:theme code="themecss" /></c:set>
<link rel="stylesheet" href='<c:url value="${tcss}"/>' type="text/css" />

<c:choose><c:when test='${empty css}'>  
<c:set var="vcss">
				<spring:theme code="themecss" />
			</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="vcss">
				<spring:theme code="${css}" />
			</c:set>
			<link rel="stylesheet" href='<c:url value="${vcss}"/>' type="text/css" />
        </c:otherwise>
</c:choose>

<link rel="stylesheet" href='<c:url value="/themes/${themename}/IfmsBlueAll.css"/>' type="text/css" />		




<fmt:message key="projectpropertyfile" bundle="${hdiits}" var="projectTitleBundleBaseName"></fmt:message>
<fmt:setBundle basename="${projectTitleBundleBaseName}" var="projectTitleBundle" scope="request"/>
<title><fmt:message bundle="${projectTitleBundle}" key="PROJECTTITLE"></fmt:message></title>
<fmt:message bundle="${constant}" key="PROJECT_NAME" var="projectURL"></fmt:message>

<%  String siteURL = "${projectURL}"+request.getQueryString();  %>
<script type="text/javascript">

if (document.layers)
{
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown=clickNS4;
}
else if (document.all&&!document.getElementById)
{
	document.onmousedown=clickIE4;
}

var myclose = false;

if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 
function Close()
{
	//alert('close event.clientY : ' + event.clientY);
	if (event.clientY < 0 || window.event.altKey)
	{
		var wname = window.name;
		//alert('window.opener : ' + window.opener);
		//alert('!window.opener : ' + !window.opener);
		//alert('wname : ' + wname);
		//alert('wname != "" : ' + (wname != ""));
		if((wname != "" && wname.substring(0,4) == "IFMS"))
		//if(!window.opener)		
		{
			setTimeout('myclose=false',100);
			myclose=true;
		}
	}
}

function HandleOnClose()
{
	if (myclose==true)
	{
		//alert('in HandleOnClose');
		//var actionFlag = "removeSessionIdFromMap";
	    //var url = '${pageContext.request.contextPath}/ifms.htm?'+"actionFlag="+actionFlag;
	    var url = '${pageContext.request.contextPath}/'+"j_spring_security_logout";   
	    var myAjax = new Ajax.Request(url,
		{
					method: 'post',
					asynchronous: false,
					onFailure: function(){ alert('Something went wrong...') } 
		} );
	}
}

document.oncontextmenu=new Function("return false")

<%--
document.oncontextmenu=new Function("alert(message);return false")
--%>

</script>


<c:if test="${empty headerj}">
	<fmt:message key="headerj" bundle="${hdiits}" var="headerj"/>
</c:if>
<c:if test="${empty horizontalNav}">
	<fmt:message key="horizontalNav" bundle="${hdiits}" var="horizontalNav"/>
</c:if>
<c:if test="${empty currentApplication}">
	<fmt:message key="currentApplication" bundle="${hdiits}" var="currentApplication"/>
</c:if>
<c:if test="${empty content}">
	<fmt:message key="content" bundle="${hdiits}" var="content"/>
</c:if>
<c:if test="${empty footer}">
	<fmt:message key="footer" bundle="${hdiits}" var="footer"/>
</c:if>
<c:if test="${empty toolbar}">
	<fmt:message key="toolbar" bundle="${hdiits}" var="toolbar"/>
</c:if>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/${projectURL}" scope="request" />
<script type="text/javascript">
	provideProperHeight();			
</script>	
</head>
<body  onload="initCMenu();mladdevents();"  onKeyDown="onKeyDownEvent (event)" onbeforeunload="Close()" onunload="HandleOnClose()">

<%-- Added by Sandeep (218580) for the tooltip for change password alert --%>
<script type="text/javascript" src='<c:url value="/script/common/wz_tooltip.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/tip_balloon.js"/>'></script>

<div id='statusbar'><table><tr><td id='imgtd'>&nbsp;</td><td align='left' valign='middle' id='statuBarTd1'></td></tr></table></div>
<div id="fadeBackground" class="fade_background"></div>

<div id="zoomedImage" style="display:none;">
<table cellpadding="1" cellspacing="0" border="0" width="100%" height="100%">
<tr><td>
	<table border=0 width="100%" height="100%">
		<tr bgcolor="#ccddff">
			<td id="imgTitle" align=left style="border: solid 1px #888888;padding: 2px;">Image Title</td>
		</tr>
		<tr>
			<td height='100%' align=center valign=middle id="imageTd">
				<div>
					<img id="lightimg"  border=0 align=middle vspace=10 hspace=10 style="" src="themes/${themename}/images/ajax-loader4.gif" width="40" height="40">
				</div>
			</td>
			
		</tr>
		<tr>
			<td align=right>
				<a href = "javascript:void(0)" onclick = "javascript:hideDIV('zoomedImage');">Close</a>
			</td>
		</tr>
	</table>
	</td></tr>
</table>
</div>

<script type="text/javascript">
<!--
showProgressbar();
//-->
</script>


	<div id="pageContent">
		<div id="inContent">
		<c:choose>
		  	<c:when test="${css ne 'dialogview'}">
				<div id="header">
					<c:import url="${headerj}" />
				</div>			
				<div id="toolbar">
					<c:import url="${toolbar}" />
				</div>					
				<div id="nav">
					<c:import url="${horizontalNav}" />			
				</div>		
				<div id="currentApplication">
					<c:import url="${currentApplication}" />
				</div>
			</c:when>			
		</c:choose>		
			<div id="content" style="background: :#f0ebe8;">			
				<c:import url="${content}" />
			</div>
			
			<c:if test="${css ne 'dialogview'}">
				
				<div id="footer">
					<c:import url="${footer}" />
				</div>
			</c:if>
		</div>
	</div>

<script type="text/javascript">
<!--
hideProgressbar();
//-->
</script>
<input type="hidden" name="zoomWaitingImg" value="themes/${themename}/images/ajax-loader4.gif">
<jsp:include page="/WEB-INF/jsp/core/saveRequest.jsp" ></jsp:include>
<input type="hidden" name="windowLocationHref" id="windowLocationHref" value="">
<div id="tempcontent" style="display: none;"></div>
<script>
document.getElementById('windowLocationHref').value = window.location.href;
</script>
</body>
</html>
<%
} 
else 
{
String themen = (String)WebUtils.getSessionAttribute(request,SessionThemeResolver.THEME_SESSION_ATTRIBUTE_NAME);
if( themen == null )
{
    themen="ifmsblue";
}
%>
<c:set var="themename" scope="session"><%=themen%></c:set>
<c:set var="taglibImagePath" scope="request"><%="themes/" + themen + "/images/taglib"%></c:set>
<c:if test="${empty currentApplication}">
	<fmt:message key="currentApplication" bundle="${hdiits}" var="currentApplication"/>
</c:if>
<%-- here give all div's content with id ajaxResponse0,ajaxResponse1
and do not give any spaces at tag line because it will not match with 
calculation in prepareResponses
--%>
<div id="ajaxResponse0">
	<c:import url="${currentApplication}"/>
</div>
<div id="ajaxResponse1">
	<c:import url="${content}"/>
</div>
<%
}
%>
<div id="ajaxResponse2">
<script>
if(controlAjaxSubmit && pageSubmitFlagTemp)
{
	enableAjaxSubmit(true);
}
// Behaviour.apply();
//code is written in behaviour.js such that this function is called onload of page
</script>
</div>

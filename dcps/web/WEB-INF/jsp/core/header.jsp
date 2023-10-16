<%@ page language="java" session="true"%>
<%@ include file="include.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.tcs.sgv.acl.login.valueobject.LoginDetails"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="currLocale" value='<%=session.getAttribute("locale")%>' scope="session"></c:set>
<script type="text/javascript" src='<c:url value="/script/login/getLoginWindow.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/header.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/clock.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/commonfunctions.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/themes/${themename}/header3.css"/>' type="text/css"> 
<link rel="stylesheet" href='<c:url value="/themes/${themename}/hdrPopupMenu.css"/>' type="text/css"> 
		<!--[if IE]>
			<link rel="stylesheet" href="<c:url value="/themes/${themename}/hdrPopupMenuSupport.css"/>">		
		<![endif]-->
<!-- Added by pratik 02-08-23 -->
<style>
@media screen and (max-width:990px) {
	.psMaintable,.headmainimg {
		width: 100% !important
	}
	
}
</style>
<!-- End by pratik 02-08-23 -->
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultCode" value="${resultObj.resultCode}"></c:set>
<fmt:formatDate type="both" var="cdate" pattern="MMM dd, yyyy HH:mm:ss" value="${result.resultValue.currentDBDate}" />
<c:set var="loginUser"	value="${result.resultValue.baseLoginVO}"></c:set>
<c:set var="userId" value="${loginUser.user.userId}"></c:set>
<c:set var="loginUserMap"	value="${result.resultValue.baseLoginMap}"></c:set>
<c:set var="primarypost" value="${result.resultValue.primarypost}"></c:set>
<c:set var="postDtlsList" value="${result.resultValue.postDtlsList}"></c:set>
<c:set var="postDtlsListSize" value="${result.resultValue.postDtlsListSize}"></c:set>

<fmt:setBundle basename="resources.FullTextSearchLables" var="myLabels" scope="request"/>
<fmt:setBundle basename="resources.acl.acl" var="aclLabels" scope="request"/>
<fmt:message bundle="${myLabels}" key="MYAPP.CASE" var="case"/>
<fmt:message bundle="${myLabels}" key="MYAPP.PERSON" var="person"/>
<fmt:message bundle="${myLabels}" key="MYAPP.PROPERTY" var="property"/>
<fmt:message bundle="${aclLabels}" key="CHANGE_PWD_ALERT1" var="chngPwdAlert1" />
<fmt:message bundle="${aclLabels}" key="CHANGE_PWD_ALERT2" var="chngPwdAlert2" />
<fmt:setBundle basename="resources.Constants" var="ApplConstants" scope="request"/>
<fmt:setBundle basename="resources.hodlogos" var="hodlogos" scope="request"/>
<fmt:message bundle="${ApplConstants}" key="INDEXINGACTIVATE" var="INDEXINGACTIVATE"/>



<%
boolean showUserDtl = false;
boolean showToolBar = false;
String userDtlCls = "";
Object objLoginUser = pageContext.getAttribute("loginUser");
if(objLoginUser != null && (objLoginUser instanceof LoginDetails) )
{
	showUserDtl = true;
    showToolBar = true;
    userDtlCls = "user_dtl_tbl_td";
}
%>
<c:if test="${resultCode eq -1}">
   <%
   showUserDtl = false;
   showToolBar = false;
   %>
</c:if>

<table align="center" id="Table_01" class="psMaintable" width="990" border="0" cellpadding="0" cellspacing="0">
	<tr height="19" style="background: #633901 top repeat-x;">
		
		<!-- Welcome Bar -->
		<td valign="top">
			<img src="images/HomePageImages/FianlHomePG_1_01.jpg" width="13" height="19" alt=""></td>
		<td valign="top" width="441" height="19" style="background: #633901 url('images/HomePageImages/FianlHomePG_1_02.jpg') top repeat-x; font-family: verdana; font-size: 10pt;color: white;">
			<b>&nbsp;<fmt:message key="WELCOME" />&nbsp;${loginUser.employee.empPrefix}&nbsp;${loginUser.employee.empFname}&nbsp;${loginUser.employee.empMname}&nbsp;${loginUser.employee.empLname}</b>
			<%
				String strName = (String) session.getAttribute("name");
				pageContext.setAttribute("EmployeeName",strName);
				//out.println("Shri Police Inspector1");
			%>
			<span class="userdetails_desg" id="userdetails_desg">
				${loginUserMap.loggedinPostDetailsRlt.postName}, ${loginUser.location.locName}
			</span>
		</td>
		
		<!-- Home Page Icon -->
		<td valign="top">
			<img src="images/HomePageImages/FianlHomePG_1_03.jpg" width="56" height="19" alt=""></td>
		<td  valign="top" align="center" width="38" height="19" style="background-image: url('images/HomePageImages/FianlHomePG_1_04.jpg');background-repeat:no-repeat">
			<a href="ifms.htm?actionFlag=getHomePage" title="<fmt:message key="GO_TO_HOMEPAGE"/>" style="text-decoration: none; font-size: 8pt; color: black;">	
				<b id="home"><fmt:message key="HOMEPAGE_LABEL"/></b>
			</a>
		</td>
		
		<!-- Help Icon -->
		<td  valign="top">
			<img src="images/HomePageImages/FianlHomePG_1_05.jpg" width="48" height="19" alt=""></td>
		<td valign="top" align="center" width="32" height="19" style="background-image: url('images/HomePageImages/FianlHomePG_1_06.jpg');background-repeat:no-repeat;">
			<a href="#" title="<fmt:message key="OPEN_HELP"/>" style="text-decoration: none; font-size: 8pt; color: black;">	
				<b id="help"><fmt:message key="HELP_LABEL"/></b>
			</a>
		</td>
		
		<!-- Setting Menu -->
		<td  valign="top" height="19">
			<img src="images/HomePageImages/FianlHomePG_1_14.jpg" width="45" height="19" alt="" />
		</td>
		<td  valign="top" width="55" height="19" style="background-image: url('images/HomePageImages/FianlHomePG_1_10.jpg');background-repeat:no-repeat;">
			<div id="setting1" class="mlmenu horizontalToolbar themeclrHorizontalToolbar arrow inaccesible" style="width: 55px;">
			<c:if test="${postDtlsListSize eq 'allow'}">
				<ul>
					<li>
					<a href="#" title="<fmt:message key="SETTINGS_TOOLTIP"/>" style="text-decoration: none; font-size: 8pt; color: black;">	
						<b ><fmt:message key="SETTINGS_LABEL"/></b>
					</a>
						<ul>
							
								<li>
									<a href="#">SWITCH POST</a>
									<ul>
										<c:forEach items="${postDtlsList}" var="postDtl">											
											<c:if test="${postDtl.orgPostMst.postId eq primarypost.postId}">
												<li><a href="#"><font color="#FE2E2E"><b><i><c:out value="${postDtl.postName}"/></i></b></font></a></li>
											</c:if>
											<c:if test="${postDtl.orgPostMst.postId ne primarypost.postId}">
												<li><a href="hdiits.htm?actionFlag=getChangedPost&radUserPost=${postDtl.orgPostMst.postId}">
													<c:out value="${postDtl.postName}"/></a>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</li>
							
								<!-- <li><a href="#" ><fmt:message key="TOOLS_LABEL"/></a>
									<ul>
											<li><a href="#" id="test"><fmt:message key="THEMES"/></a>
												<ul>
													<li><a href="hdiits.htm?theme=defaulttheme&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="DEFAULT_THEME"/></a></li>
													<li><a href="hdiits.htm?theme=hdiits&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="BLUE_GRAY_THEME"/></a></li>
													<li><a href="hdiits.htm?theme=hdiitsorange&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="BLUE_ORANGE_THEME"/></a></li>
													<li><a href="hdiits.htm?theme=hdiitsbrown&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="BROWN_THEME"/></a></li>										
												</ul>
											</li>
											<li><a href="#" id="test"><fmt:message key="CHANGE_LOCALE"/></a>
												<ul>
													<c:choose>
											 			<c:when test="${currLocale eq 'en_US'}">
											 				<li>
												 				<a href="hdiits.htm?actionFlag=localeChange&locale=gu&localeChange=true" title="<fmt:message key="CHANGE_LOCALETO_GUJARATI"/>" onclick="javascript:showProgressbar();">	
																	<fmt:message key="CHANGE_LOCALETO_GUJARATI"/>	
																</a>
															</li>	
											 			</c:when>
											 			<c:when test="${currLocale eq 'gu'}">		
											 				<li>
												 				<a href="hdiits.htm?actionFlag=localeChange&locale=en_US&localeChange=true" title="<fmt:message key="CHANGE_LOCALETO_ENGLISH"/>" onclick="javascript:showProgressbar();">	
																	<fmt:message key="CHANGE_LOCALETO_ENGLISH"/>
																</a>
															</li>	
														</c:when>
											 		</c:choose>
												</ul>								
											</li>
									</ul>
								</li>-->							
						</ul>
					</li>	
				</ul>
				</c:if>
				<c:if test="${postDtlsListSize != 'allow'}">
					<a href="#" title="<fmt:message key="SETTINGS_TOOLTIP"/>" style="text-decoration: none; font-size: 8pt; color: black;">	
						<b ><fmt:message key="SETTINGS_LABEL"/></b>
					</a>
				</c:if>
			</div>
		</td>
		
		<!-- Change Password Icon -->
		<td valign="top">
			<img src="images/HomePageImages/FianlHomePG_1_07.jpg" width="45" height="19" alt="" />
		</td>
		<td valign="top" align="center" width="113" height="19" id="toolbarChngPwd" style="background-image: url('images/HomePageImages/FianlHomePG_1_08.jpg');background-repeat:no-repeat;" >
			<c:if test="${userId != 239998}">
			<a  href="ifms.htm?viewName=acl-changePassword"  title="<fmt:message key="CHANGE_PASSWORD"/>" style="text-decoration: none; font-size: 8pt; color: black;">
				<b><fmt:message key="CHANGE_PASSWORD_LABEL"/></b>
			</a>
			</c:if>
		</td>
		
		<!-- Logout Icon -->
		<td valign="top">
			<img src="images/HomePageImages/FianlHomePG_1_09.jpg" width="50" height="19" alt=""></td>
		<td valign="top" align="center" width="55" height="19" style="background-image: url('images/HomePageImages/FianlHomePG_1_10.jpg');background-repeat:no-repeat;">
			<a  href="<c:url value='j_spring_security_logout'/>" title="<fmt:message key="LOGOUT"/>" style="text-decoration: none; font-size: 8pt; color: black;">
				<b><fmt:message key="LOGOUT_LABEL"/></b>
			</a>
		</td>	
	</tr>
	
	<!-- Main Header -->
	<tr>
		<td colspan="12">
				<img src="images/HomePageImages/FianlHomePG_1_11.jpg" width="990" height="82" alt="" class="headmainimg"> <!-- headmainimg class added For UI issues by Pratik 01-08-23 --> </td>
	</tr>
		
</table>

<table cellpadding="0" cellspacing="0" width="100%" style="background: #EEDECC;">
	<tr height="7" >
		<td width="75%" align="left">			  		  
			  <!-- HTML codes by Quackit.com -->
<marquee behavior="scroll" direction="left" scrollamount="3" onmousedown="this.stop();" onmouseup="this.start();"><span style="font-size: 8pt; font-family:verdana; color:#613803;"> Press <b>F8</b> to Open Menu. Use <b>UP, DOWN, LEFT, RIGHT</b> Arrow keys to navigate. Use <b>Esc</b> to Close Menu</span></marquee>
		</td>
		<td width="20%" align="left">

			<c:choose>
				<c:when test="${empty loginUserMap.lastLoginDateTime}"> </c:when>
				<c:otherwise> <span style="font-size: 8pt;"><b>Last Login </b> ${loginUserMap.lastLoginDateTime} </span></c:otherwise>
			</c:choose>
		</td>	
		<td width="5%" align="left">		
		</td>
		
	</tr>	
</table>

<%
	ResultObject objRs=(ResultObject)request.getAttribute("result");
	Map mapResult=(Map)objRs.getResultValue();
	
	if(mapResult!=null)
   	{
		Object objChngPwdAlertMsg = mapResult.get("ChangePasswordAlert");		
  		if(objChngPwdAlertMsg != null)
  		{
  			String changPwd_days_left = (String) objChngPwdAlertMsg;
  			%>
				<script type="text/javascript">
					var chngPwdAlert = '${chngPwdAlert1}' 
										+ ' <%=changPwd_days_left%> '  
										+ '${chngPwdAlert2}' ;
					alert(chngPwdAlert);
					var toolTipX = findPosX(document.getElementById("toolbarChngPwd"))+17;
					var toolTipY = findPosY(document.getElementById("toolbarChngPwd"))+6;
			  		try{
	  					var tooltipImgPath = "themes/${themename}/images/tooltip/";
						document.getElementById("toolbarChngPwd").onmouseover = Tip(chngPwdAlert,FIX , [toolTipX,toolTipY] ,DELAY,5000 ,DURATION,15000, CLICKCLOSE, true, FADEIN,500, FADEOUT,500, BALLOON,true, BALLOONIMGPATH,tooltipImgPath);
				 	}catch(e){}
				</script>
			<%
  		}
   	}
%>

<script language="javascript">
function displayPRCRReport(){
	//var baseUrl = 'ifms.htm?actionFlag=reportService&reportCode=220100&action=parameterPage';		  
	//alert(baseUrl);
	//cw = window.open(baseUrl,"cw","width=1200,height=800,scrollbars=1,toolbar=0,menubar=0,resizable=1");
	//cw.moveTo(0,0);
	
	document.forms[0].action = 'ifms.htm?actionFlag=reportService&reportCode=220100&action=parameterPage';
	document.forms[0].submit();
}
</script>

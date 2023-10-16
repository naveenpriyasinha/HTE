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
<!-- link rel="stylesheet" href='<c:url value="/themes/${themename}/header3.css"/>' type="text/css"--> 
<link rel="stylesheet" href='<c:url value="/themes/${themename}/hdrPopupMenu.css"/>' type="text/css"> 
		<!--[if IE]>
			<link rel="stylesheet" href="<c:url value="/themes/${themename}/hdrPopupMenuSupport.css"/>">
			<script type="text/javascript">
				window.mlrunShim = true;
			</script>
		<![endif]-->
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultCode" value="${resultObj.resultCode}"></c:set>
<fmt:formatDate type="both" var="cdate" pattern="MMM dd, yyyy HH:mm:ss" value="${result.resultValue.currentDBDate}" />
<c:set var="loginUser"	value="${result.resultValue.baseLoginVO}"></c:set>
<c:set var="loginUserMap"	value="${result.resultValue.baseLoginMap}"></c:set>

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
<table cellspacing=0 cellpadding=0 border=0 id="mainTable" width="100%">
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
	<tr class="projecttitle_tr">
		<td align="center" valign="middle" class="projecttitle_td" style="padding: 0 3px 0 3px;border-left:solid 1px #a6aee9;border-bottom: 0px ridge #555c8e;" rowspan=1 width="5%">
			<img src="images/emblem-10.gif" align="middle" width="44" height="67"/>
		</td>
		<td width="5%" valign="middle" class="projecttitle_td" rowspan=1 style="border-right: groove 2px #878ccc;">
			<img src="<c:url value='/images/gujarat_state_and_districts-10.gif'/>" alt="logo" align="middle" width="70" height="55" />
		</td>
		<td width="60%" class="projecttitle_td" valign="middle" align="left" colspan="1" rowspan=1 >
			<div id="headertext">
				<font style="font-size: 8pt;"><fmt:message key="DEPARTMENT" />,&nbsp;<fmt:message key="STATE"/><br></font>
				<c:if test="${(not empty loginUserMap) and (not empty loginUserMap.departmentVO) }">
					<span style="border-bottom: groove 2px #878ccc;padding-bottom: 2px;" ><fmt:message key="PROJECT_TITLE" /></span>
					<span id="headertext" style="font-size: 14pt;">
					<font style="font-size: 10pt;"><br> ${loginUserMap.departmentVO.depName}</font>
					</span>
				</c:if>
			</div>
		</td>
		<td id ="td_userDetailsTable" colspan=2 valign="middle" class="<%=userDtlCls%>" width="30%">
		
		<% if(showUserDtl) {%>
		<table id="userDetailsTable" cellpadding="2" cellspacing="0" border="0" width="100%" height="100%">
		<tr>
			<td width="5px" class="userdetails_tl_td" rowspan=2>&nbsp;</td>
			<td colspan=2 nowrap="nowrap" align="right" >
			
			<span class="userdetails_name" id="userdetails_name">
			<fmt:message key="WELCOME"/><b>&nbsp;${loginUser.employee.empPrefix}&nbsp;${loginUser.employee.empFname}&nbsp;${loginUser.employee.empLname}</b>
			</span>
			<%
				String strName = (String) session.getAttribute("name");
				pageContext.setAttribute("EmployeeName",strName);
				//out.println("Shri Police Inspector1");
			%>
			<br>
			<span class="userdetails_desg" id="userdetails_desg">
				${loginUser.designation.dsgnName}, ${loginUser.location.locName}
			</span>
			
			<br>
			<span class="userdetails_login" id="userdetails_login">
			<fmt:message key="CURRENT_LOGIN" /> ${loginUserMap.currLoginDateTime},
			<fmt:message key="LAST_LOGIN" /> 
				<c:choose>
					<c:when test="${empty loginUserMap.lastLoginDateTime}"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp; </c:when>
					<c:otherwise>${loginUserMap.lastLoginDateTime}</c:otherwise>
				</c:choose>
			<br>
			</span>
			</td>
			<c:if test="${not empty loginUserMap }">
				<c:set var="deptCode" value="${loginUserMap.departmentVO.depCode}" ></c:set>
				<c:set var="deptCode" value="${fn:replace(deptCode,'-','_')}" ></c:set>
				<c:set var="deptCode" value="${fn:replace(deptCode,' ','_')}" ></c:set>
				<c:set var="deptCode" value="${fn:toUpperCase(deptCode)}" ></c:set>
				<c:choose>
					<c:when test="${empty deptCode}">
						<td rowspan=2><img src="images/hodlogos/default.gif" align="middle" height="60"/></td>
					</c:when>
					<c:otherwise>
						<fmt:message bundle="${hodlogos}" key="${deptCode}" var="imgHodLogo"/>
						<c:choose>
							<c:when test="${empty imgHodLogo or (fn:contains(imgHodLogo,'?')) }">
								<td rowspan=2><img src="images/hodlogos/default.gif" align="middle" height="60"/></td>
							</c:when>
							<c:otherwise>
								<td rowspan=2><img src="images/hodlogos/${imgHodLogo}" align="middle" height="60"/></td>
							</c:otherwise>
						</c:choose>					
					</c:otherwise>
				</c:choose>
			</c:if>
			
		</tr>
			<c:if test="${loginUserMap.locationVO.cmnLookupMst.lookupName =='Police Station'}">
			<tr>
				<td id="current_pso" colspan=2 nowrap="nowrap" align="right" valign="top" style="border-top: groove 2px #eee;" >
				<span class="userdetails_pso">
				<fmt:message key="CURRENT_PSO" /> ${loginUserMap.currPsoName}
						<c:choose>
						<c:when test="${loginUserMap.langId=='2'}">, ${loginUserMap.currPsoInTime}&nbsp;<fmt:message key="SINCE" /></c:when>
						<c:otherwise>,&nbsp;<fmt:message key="SINCE" />${loginUserMap.currPsoInTime}</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:if>
			</table>

			<%}%>
		
		</td>
	</tr>
	<tr>
	
	
			
	<td colspan=5 class="projecttitle_td1" style="padding:0;text-align:right;border-bottom:none;font-weight: normal;font-size: 13px;">
			<% if(showToolBar){ %>
			<table width="100%" cellpadding="0" cellspacing="0" border=0 id="toolBarTable" >
			<tr class="toolbar_tr">
			<td width="80%" id="dbdatetd" colspan="" style="border-left:solid 1px #a6aee9;"><p id="dbdate"></p></td>
			
			<c:if test="${loginUserMap.locationVO.cmnLookupMst.lookupName == 'Police Station' && INDEXINGACTIVATE eq 'Y'}">			
				<td id="hdrFtSearchTd">
					<jsp:include page="/WEB-INF/jsp/common/ftSearchToolbar.jsp">
						<jsp:param name="ftSearchOn" value="${case},case,CUifms.htm?actionFlag=ps-drillDown"/>
						<jsp:param name="ftSearchOn" value="${person},person,CUifms.htm?actionFlag=reportService&reportCode=2030&action=generateReport"/>
						<jsp:param name="ftSearchOn" value="${property},property,RC1320"/>						
					</jsp:include>				
				</td>
			</c:if>
			
			<td  id="toolbarSetting" style="text-align: left;padding: 0px;">	
      	    
			<div class="mlmenu horizontalToolbar themeclrHorizontalToolbar arrow inaccesible" style="width: 100px;">
					<ul>
							<li><a href="#" title="<fmt:message key="SETTINGS_TOOLTIP"/>" ><img src="themes/${themename}/images/icon_settings.GIF" align="middle"/><fmt:message key="SETTINGS_LABEL"/></a> 
								<ul style="display: none;">	
										<li>
											<%-- <a href="ifms.htm?actionFlag=firstTimeLogin&setQueFromToolBar=Y" onclick="javascript:showProgressbar();" ><fmt:message key="MENU_SET_SECRETQUE"/></a> --%>
											<a href="#"><fmt:message key="MENU_SET_SECRETQUE"/></a>
										</li>
															
										<li><a href="#" ><fmt:message key="TOOLS_LABEL"/></a>
											<ul>
													<li><a href="#" id="test"><fmt:message key="THEMES"/></a>
														<ul>
															<li><a href="ifms.htm?theme=defaulttheme&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="DEFAULT_THEME"/></a></li>
															<li><a href="ifms.htm?theme=hdiits&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="BLUE_GRAY_THEME"/></a></li>
															<li><a href="ifms.htm?theme=hdiitsorange&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="BLUE_ORANGE_THEME"/></a></li>
															<li><a href="ifms.htm?theme=hdiitsbrown&actionFlag=getHomePage" onclick="javascript:showProgressbar();"><fmt:message key="BROWN_THEME"/></a></li>										
														</ul>
													</li>
													<li><a href="#" id="test"><fmt:message key="CHANGE_LOCALE"/></a>
														<ul>
															<c:choose>
													 			<c:when test="${currLocale eq 'en_US'}">
													 				<li>
														 				<a href="ifms.htm?actionFlag=localeChange&locale=gu&localeChange=true" title="<fmt:message key="CHANGE_LOCALETO_GUJARATI"/>" onclick="javascript:showProgressbar();">	
																			<fmt:message key="CHANGE_LOCALETO_GUJARATI"/>	
																		</a>
																	</li>	
													 			</c:when>
													 			<c:when test="${currLocale eq 'gu'}">		
													 				<li>
														 				<a href="ifms.htm?actionFlag=localeChange&locale=en_US&localeChange=true" title="<fmt:message key="CHANGE_LOCALETO_ENGLISH"/>" onclick="javascript:showProgressbar();">	
																			<fmt:message key="CHANGE_LOCALETO_ENGLISH"/>
																		</a>
																	</li>	
																</c:when>
													 		</c:choose>
														</ul>								
													</li>
											</ul>
										</li>
								</ul>
							</li>	
					</ul>
				</div>
		</td>
		<td width="2%" id="toolbarHelp">
			<a href="#" title="<fmt:message key="OPEN_HELP"/>">	
				<img src="themes/${themename}/images/icon_help.gif"	align="middle" />
				<fmt:message key="HELP_LABEL"/>
			</a>			
		</td>
		<td width="2%" id="toolbarHome">
			<a id="homeId" href="ifms.htm?actionFlag=getHdiitsHomePage" title="<fmt:message key="GO_TO_HOMEPAGE"/>" onclick="javascript:showProgressbar();">	
				<img src="themes/${themename}/images/icon_home.gif" align="middle"/>
				<fmt:message key="HOMEPAGE_LABEL"/>
			</a>		
		</td>		
		<td width="2%" id="toolbarChngPwd">
			<a id="chngPassId" href="ifms.htm?viewName=acl-changePassword&fromToolbar=Y"  title="<fmt:message key="CHANGE_PASSWORD"/>" onclick="javascript:showProgressbar();">
				<img src="themes/${themename}/images/icon_chPassword.gif" align="middle"/>
				<fmt:message key="CHANGE_PASSWORD_LABEL"/>
			</a>
		</td>
		<td width="2%" id="toolbarLogout">
			<a href="javascript:closeTheApplication('${contextPath}')" title="<fmt:message key="LOGOUT"/>" >
				<img src="themes/${themename}/images/icon_logout.gif" align="middle"/>
				<fmt:message key="LOGOUT_LABEL"/>
			</a>
		</td>
		</tr>
		</table>
		<%}%>
		
		</td>
		</tr>
	</table>
	<c:if test="${resultCode ne -1}">
<script type="text/javascript">
if(document.getElementById("dbdate") != null)
{
	setCurrentDateTime('<c:out value="${cdate}"></c:out>');
}
</script>
</c:if>

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
					//alert(chngPwdAlert);
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
	
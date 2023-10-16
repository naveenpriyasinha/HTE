<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.acl.acl" var="accessControlLables" scope="request"/>
<head>
<script type="text/javascript" src='<c:url value="/script/login/getLoginWindow.js"/>'></script>

</head>						    
<center>
<table border=0 cellpspacing=0 cellpadding=2 width="400" height="400">
			<tr>
				<td valign="middle" align="center"></td>
					<td height="100%">
					<hdiits:form name="ePage" validate="false" method="POST">
					<hdiits:fieldGroup>
									<table border=0 width="100%" cellpadding="5"><tr><td valign="middle" align="center">
									&nbsp;
									</td>
									<td valign="middle" align="center">
									
									<font style="font-size: 14px; font-weight: normal;">
									<hdiits:caption captionid="pwdSuccess" bundle="${accessControlLables}" captionLang="single"/>
									</font>
									</td></tr>
									<tr>
									
									<td colspan=2 valign="middle" align="center">
									<h1><a href="<c:url value='j_spring_security_logout'/>" title="<fmt:message key="LOGOUT"/>" onclick="javascript:showProgressbar();" >
										<fmt:message key="LOGOUT_LABEL"/>
										</a>
										</h1>
									</td>
									
									
								</tr></table>
						</hdiits:fieldGroup>
						</hdiits:form>
<script type="text/javascript">
	var navDisplay = false;
	document.getElementById("home").style.display='none';
	document.getElementById("help").style.display='none';
	document.getElementById("setting1").style.display='none';
</script>
						
						
						</td>
						</tr>
					</table>
		</center>
					
















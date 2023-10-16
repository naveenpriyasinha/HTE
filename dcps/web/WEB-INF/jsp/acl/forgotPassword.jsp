<%try{ %>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src='<c:url value="script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/WebSecurity.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/modalDialog.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/login/statusbar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/prototype.js"/>'></script>
<script type="text/javascript" src="<c:url value="script/login/frmUtils.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/acl/forgotPassword.js"/>"></script>
<c:set var="resultObj" value="${result}"> </c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}" scope="request"> </c:set> 
<c:set var="show_menu" value="show_menu"> </c:set>
<STYLE type="text/css">		
#currentApplication{
	display:none; 
}
#nav,#verticalnav,#rightInfo{
	display:none;
} 
</STYLE>
<%
	String strLocale = (String) session.getAttribute("locale");
	if( strLocale == null )
	{
		strLocale = request.getParameter("locale");
		session.setAttribute("locale" , strLocale);
	}
%>
<fmt:setLocale value='<%=strLocale%>'/>
<fmt:setBundle basename="resources.acl.acl" var="aclLabels" scope="request"/>

<hdiits:form name="frmForgotPassword" method="POST" action="./hdiits.htm?actionFlag=forgotPassword" validate="true">  
<div id="tabmenu">
<ul id="maintab" class="shadetabs"> 
   <li class="selected"><a href="#" rel="tcontent1"> 
   <fmt:message bundle="${aclLabels}" key="tabForgotPwd"></fmt:message></a></li>
</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<fmt:message var="InvalidUserNameMsg" key="InvalidUserNameMsg"  bundle="${aclLabels}" ></fmt:message>
<hdiits:table>
	<hdiits:tr>	
		<hdiits:td colspan="2" ><hdiits:caption captionid="NOTE_VALID_USERNAME" bundle="${aclLabels}"/></hdiits:td>
	</hdiits:tr>
	<hdiits:tr><hdiits:td>&nbsp;</hdiits:td></hdiits:tr>
</hdiits:table>
<hdiits:table  width="100%" align="center" >
<%--
	<hdiits:tr>
		<hdiits:td width="150"><hdiits:caption captionid="GPFNumber" bundle="${aclLabels}"  /></hdiits:td>
		<hdiits:td ><hdiits:text id="GPFNumber" name="GPFNumber" mandatory="true" validation="txt.isrequired" captionid="GPFNumber" bundle="${aclLabels}"/></hdiits:td>
	</hdiits:tr>
--%>	
	<hdiits:tr>	
		<hdiits:td width="150">
		<hdiits:caption captionid="User_nameLbl" bundle="${aclLabels}"/></hdiits:td>
		<hdiits:td>
			<hdiits:text name="User_name" id="User_name" default="${resultMap.userName}" validation="txt.isrequired" mandatory="true" captionid="User_name" bundle="${aclLabels}" onblur="getUserIdFromUserName('${pageContext.request.contextPath}')" />
			<font id="errorUserMsgTd" style="color: red; vertical-align: middle;"></font>
		</hdiits:td>
	</hdiits:tr>
	<hdiits:tr id="Birth_Date_TR" >	
		<hdiits:td width="150"><hdiits:caption captionid="BirthDate" bundle="${aclLabels}"/></hdiits:td>
		<hdiits:td>
			<hdiits:dateTime name="Birth_Date" validation="txt.isrequired" mandatory="true" captionid="BirthDate" bundle="${aclLabels}" />
			<font style="color: red; vertical-align: middle;">${resultMap.errBirthDate}</font>
		</hdiits:td>
	</hdiits:tr>

	<input type="hidden" style="display: none;" name="txtSecretQue" value="${resultMap.SecretQue}" />
	<input type="hidden" name="SecretAnsFromDB" value="${resultMap.SecretAns}">
	
	<hdiits:tr id="SECRET_QUE_TR" >	
		<hdiits:td width="150"><hdiits:caption captionid="SECRET_QUE" bundle="${aclLabels}"/></hdiits:td>
		<hdiits:td>
					<input type="text" name="secret_que" value="${resultMap.SecretQue}" disabled="disabled" size="${fn:length(resultMap.SecretQue)}" />
		</hdiits:td>
	</hdiits:tr>
	<hdiits:tr id="SECRET_ANS_TR" >	
		<hdiits:td width="150"><hdiits:caption captionid="SECRET_ANS" bundle="${aclLabels}"/></hdiits:td>
		<hdiits:td>
			<hdiits:password id="txtSecretAns" type="txt-password" name="txtSecretAns" captionid="SECRET_ANS" bundle="${aclLabels}" mandatory="true" validation="txt.isrequired" />
			<font style="color: red; vertical-align: middle;">${resultMap.errSecret}</font>
		</hdiits:td>
	</hdiits:tr>
</hdiits:table>
	
<hdiits:table >
	<hdiits:tr id="Note_TR" >
		<hdiits:td colspan="2" >
		<BR>
				<B><hdiits:caption captionid="noteForgotPwd1" bundle="${aclLabels}"/><br><br>
				<hdiits:caption captionid="noteForgotPwd2" bundle="${aclLabels}"/></B>
		</hdiits:td>
	
	</hdiits:tr>
	<hdiits:tr>	
		<hdiits:td id="errorMsgTd" colspan="2">
		<br><br>
			<font style="color: red;">
	            <c:out value='${resultMap.msg}' />
	         </font>
	    </hdiits:td>
	</hdiits:tr>
</hdiits:table>	
</div>
				<fmt:message var="closeBtnValue" key="CancelBtn"  bundle="${aclLabels}" ></fmt:message>
				<jsp:include page="../core/tabnavigation.jsp">
				<jsp:param name="closeText" value="${closeBtnValue}"/>
				<jsp:param name="closeURL" value="login.jsp"/>
				</jsp:include>
</div>
<input type="hidden" name="updtPwd" value="Y" />
<input type="hidden" name="show_menu" value="Y" />
<input type="hidden" name="locale" value="<%= strLocale %>" />
<input type="hidden" name="userId" value="${resultMap.userId}" />
<input type="hidden" name="userName" value="${resultMap.userName}" />
<hdiits:validate locale='<%=strLocale%>' />
<script type="text/javascript">
var navDisplay = false;
initializetabcontent("maintab");
var InvalidUserNameMsg = '${InvalidUserNameMsg}';
var tempUserId = document.getElementById("userId").value;
if(tempUserId != "")
{
	showHideControls(1);
}
else
{
	showHideControls(0);
}
</script>
</hdiits:form>
<%}catch(Exception e)
	{
	e.printStackTrace();
}%>

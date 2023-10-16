<%try{ %>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<script type="text/javascript" src='<c:url value="script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/login/validation.js"/>'></script>
<fmt:setBundle basename="resources.acl.acl" var="aclLabels" scope="request"/>
<c:set var="resultObj" value="${result}"> </c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}" scope="request"> </c:set> 
<%
	Map resMap = (Map) request.getAttribute("resultMap");
	ArrayList secretQuesValList = null;
	ArrayList secretQuesCapList = null;
	if(resMap != null && !resMap.isEmpty())
	{
		secretQuesValList = (ArrayList) resMap.get("SecretQueVal");
		secretQuesCapList = (ArrayList) resMap.get("SecretQueCap");
	}
	String strEmpDob = (String) resMap.get("strEmpDob");
	String secretQueCodeFromDB = (String) resMap.get("secretQueCodeFromDB");
	String secretQueOtherFromDB = (String) resMap.get("secretQueOtherFromDB");
%>
<STYLE type="text/css">		
#currentApplication,#nav,#verticalnav,#rightInfo{
	display:none;
}
</STYLE>
<script type="text/javascript" >
function checkOtherSecretQue()
{
	var selObj = document.getElementById("selSecretQue");
	var val = selObj.options[selObj.selectedIndex].value;
	if(val == 'Secret_Other')
	{
		document.getElementById("tdSecretQueOther").style.display = '';
	}
	else
	{
		document.getElementById("tdSecretQueOther").style.display = 'none';
	}
	document.getElementById("txtSecretQueOther").value = '';
	document.getElementById("txtSecretAns").value = '';
	document.getElementById("txtSecretAnsConf").value = '';
}

function validateSecretAnswer()
{
	var ans1 = document.getElementById("txtSecretAns").value;
	var ans2 = document.getElementById("txtSecretAnsConf").value;
	if(ans1 != ans2)
	{
		alert('<fmt:message bundle="${aclLabels}" key="ALERT_ANS_CONF"/>');
		return false;
	}
	var selObj = document.getElementById("selSecretQue");
	var val = selObj.options[selObj.selectedIndex].value;
	if(val == 'Secret_Other')
	{
		var otherQue = trim( document.getElementById("txtSecretQueOther").value );
		if(otherQue == '')
		{
			alert('<fmt:message bundle="${aclLabels}" key="SECRET_QUE_OTHER"/>');
			return false;
		}
	}
	document.frmFirstTimeLogin.action = "./ifms.htm?actionFlag=firstTimeLogin";
	return true;
}
</script>
<hdiits:form name="frmFirstTimeLogin" method="POST"  validate="true">  
<div id="tabmenu">
<ul id="maintab" class="shadetabs"> 
   <li class="selected"><a href="#" rel="tcontent1"> 
   <fmt:message bundle="${aclLabels}" key="tabFirstTimeLogin"></fmt:message></a></li>
</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<TABLE class=tabtable width="100%" align="center" >
	<tr>
		<td width="150"><hdiits:caption captionid="userEmail" bundle="${aclLabels}"  /></td>
		<td ><hdiits:text name="userEmail" validation="txt.email" bundle="${aclLabels}" captionid="userEmail" /></td>
	</tr>	
	<tr>	
		<td width="150"><hdiits:caption captionid="BirthDate" bundle="${aclLabels}"/></td>
		<% if(strEmpDob != null && (!strEmpDob.trim().equals("")) ){ %>
				<td><hdiits:dateTime name="Birth_DateTemp" validation="txt.isrequired" mandatory="true" captionid="BirthDate" bundle="${aclLabels}" default="<%=strEmpDob%>" disabled="true" /></td>
				<td style="display: none;">
				<hdiits:dateTime  name="Birth_Date" validation="txt.isrequired" mandatory="true" captionid="BirthDate" bundle="${aclLabels}" default="<%=strEmpDob%>" disabled="true" />
				</td>
		<%}else{ %>
				<td><hdiits:dateTime name="Birth_Date" validation="txt.isrequired" mandatory="true" captionid="BirthDate" bundle="${aclLabels}" /></td>
		<%} %>
	</tr>
	<tr><td colspan="2">&nbsp;</td><tr>
</table>		

	<% if( secretQuesValList != null && secretQuesCapList != null && !secretQuesValList.isEmpty() && !secretQuesCapList.isEmpty()) { %>
		
		<hdiits:fieldGroup mandatory="true" id="fieldGroupSecretInfo" bundle="${aclLabels}" titleCaptionId="TITLE_SECRETEINFO" expandable="false">
		
		<table>
		<tr>
			<td colspan="2">
				<fmt:message bundle="${aclLabels}" key="NOTE_SECRET_INFO"/>
			<td>
		<tr>
		
		<tr>	
			<td width="150"><BR><hdiits:caption captionid="SECRET_QUE" bundle="${aclLabels}"/></td>
			<td>
					<hdiits:select id="selSecretQue" name="selSecretQue" captionid="SECRET_QUE" bundle="${aclLabels}" mandatory="true" validation="sel.isrequired" onchange="checkOtherSecretQue()" sort="false">
						<hdiits:option value="-1" > <fmt:message bundle="${aclLabels}" key="SEL_SECRET_QUE"/> </hdiits:option>
						<% for(int x=0;x<secretQuesValList.size();x++){ 
							
							String selValue = (String) secretQuesValList.get(x);
							if(secretQueCodeFromDB != null && !(secretQueCodeFromDB.trim().equals("")) && secretQueCodeFromDB.equals(selValue) )
								{
									%><hdiits:option value="<%=selValue%>" selected="true"> <%= (String) secretQuesCapList.get(x)%> </hdiits:option> <%
								}
								else
								{
									%><hdiits:option value="<%=selValue%>" selected="false" > <%= (String) secretQuesCapList.get(x)%> </hdiits:option> <%
								}
						} %>	
					</hdiits:select>
			</td>
		</tr>
		
		<tr id="tdSecretQueOther" style="display: none;">
			<td width="150"><hdiits:caption captionid="SECRET_QUE_OTHER" bundle="${aclLabels}"  /></td>
			<td><hdiits:text name="txtSecretQueOther" bundle="${aclLabels}" captionid="SECRET_QUE_OTHER" maxlength="100" /> &nbsp;&nbsp;<label class="mandatoryindicator">*</label></td>
		</tr>
		
		<tr>
			<td width="150"><hdiits:caption captionid="SECRET_ANS" bundle="${aclLabels}"  /></td>
			<td ><hdiits:password type="txt-password" name="txtSecretAns" validation="txt.isrequired"  bundle="${aclLabels}" mandatory="true" captionid="SECRET_ANS" size="30" maxlength="30"/></td>
		</tr>
		
		<tr>
			<td width="150"><hdiits:caption captionid="SECRET_ANS_CONF" bundle="${aclLabels}"  /></td>
			<td ><hdiits:password type="txt-password" name="txtSecretAnsConf" validation="txt.isrequired" bundle="${aclLabels}" mandatory="true" captionid="SECRET_ANS_CONF" size="30" maxlength="30" /></td>
		</tr>
		
		</table>
		
		</hdiits:fieldGroup>
		
		
	<% } %>
	
			
</div>

				<fmt:message var="closeBtnValue" key="CancelBtn"  bundle="${aclLabels}" ></fmt:message>
				<fmt:message var="SaveBtn" key="SaveBtn" bundle="${aclLabels}" ></fmt:message>	
			    <hdiits:jsField name="validateSecretAnswer" jsFunction="validateSecretAnswer()"/>
			
				<script type="text/javascript">var navDisplay = false;</script>
				<hdiits:hidden name="actionFlag" default="firstTimeLogin" />	
				
				<c:choose>
					<c:when test="${not empty resultMap.setQueFromToolBar}" >
						<jsp:include page="../core/tabnavigation.jsp">
							<jsp:param name="submitText" value="${SaveBtn}" />
							<jsp:param name="closeText" value="${closeBtnValue}"/>
							<jsp:param name="disableReset" value="true"/>
							<jsp:param name="closeURL" value="ifms.htm?actionFlag=getHomePage"/>
							</jsp:include>
					</c:when>
					<c:otherwise>
							<jsp:include page="../core/tabnavigation.jsp">
							<jsp:param name="submitText" value="${SaveBtn}" />
							</jsp:include>
					</c:otherwise>
				</c:choose>
</div>
<script type="text/javascript">
initializetabcontent("maintab")
</script>
<input type="hidden" name="updateFirstLoginData" value="Y" />
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script type="text/javascript">
document.frmFirstTimeLogin.action = "./ifms.htm?actionFlag=firstTimeLogin";

var navDisplay = false;
document.getElementById("home").style.display='none';
document.getElementById("help").style.display='none';
document.getElementById("setting1").style.display='none';

<%
	if(secretQueOtherFromDB != null && !(secretQueOtherFromDB.trim().equals("")) )
	{
		%>
			document.getElementById("tdSecretQueOther").style.display = '';
			document.getElementById("txtSecretQueOther").value = "<%=secretQueOtherFromDB%>" ;
		<%
	}
%>
</script>
<%}catch(Exception e)
	{
	e.printStackTrace();
}%>

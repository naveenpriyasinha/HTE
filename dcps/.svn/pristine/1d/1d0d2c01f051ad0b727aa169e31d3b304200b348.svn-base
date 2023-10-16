<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.acl.acl" var="aclLabels" scope="request"/>
<fmt:setBundle basename="resources.Constants" var="Constants" scope="request"/>
<%@ page language="java" import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map" %>
<script type="text/javascript" src='<c:url value="/script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/validation.js"/>'></script>
<link rel="stylesheet" href="<c:url value="/themes/${themename}/exprcpt.css"/>" type="text/css" />

<script type="text/javascript">

function checkCapLock(e,num)
{
 	var kc = e.keyCode?e.keyCode:e.which;
 	var sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false);
 
	var id = 'caps_on'+num;
	if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk))
	document.getElementById('caps_on'+num).style.visibility = 'visible';
    else
    document.getElementById('caps_on'+num).style.visibility = 'hidden';
}
function hideCapsOnMsg(n)
{	
	var i='caps_on'+n;
    document.getElementById('caps_on'+n).style.visibility = 'hidden';
}

function clearAllPasswords()
{
  document.forms[0].txtOldPassword.value = "";
  document.forms[0].txtNewPassword.value = "";
  document.forms[0].txtReEntNewPassword.value = "";  
  document.forms[0].txtOldPassword.focus();
}

function validateChangePassword()
{
  val = document.forms[0].txtOldPassword.value
  if(val == "")
  {
    alert('<fmt:message bundle="${aclLabels}" key="OldPwd"/>');
   
    clearAllPasswords();
    return false;
  }


  val = document.forms[0].txtNewPassword.value
  
  if(val == "" )
  {
    alert('<fmt:message bundle="${aclLabels}" key="NewPwd"/>');
   
    clearAllPasswords();
    return false;
  }
  
  if(document.frmChangePassword.txtNewPassword.value == document.forms[0].txtOldPassword.value)
  {
	alert('<fmt:message bundle="${aclLabels}" key="NewPwdOldPwd"/>');
   
    clearAllPasswords();
    return false;
  }
  
  ret1 = checkForNumber(val);
  if(!ret1)
  {
     alert('<fmt:message bundle="${aclLabels}" key="NewPwdNumeric"/>');
    
    clearAllPasswords();
    return false;		
  }
  ret2 = checkForSpecialChrs(val);
  if(!ret2)
  {
    alert('<fmt:message bundle="${aclLabels}" key="NewPwdSplChrs"/>');
    
    clearAllPasswords();
    return false;		
  }
  ret3 = checkForChrs(val);
  if(!ret3)
  {
   alert('<fmt:message bundle="${aclLabels}" key="NewPwdChrs"/>');
  
    clearAllPasswords();
    return false;		
  }
  val = document.forms[0].txtReEntNewPassword.value
  if(val == "")
  {
   alert('<fmt:message bundle="${aclLabels}" key="ConfirmPwd"/>');
  
    clearAllPasswords();
    return false;
  }


  if((document.frmChangePassword.txtNewPassword.value)!=(document.frmChangePassword.txtReEntNewPassword.value))
  {
  	alert('<fmt:message bundle="${aclLabels}" key="NewPwdMatch"/>');
    
    clearAllPasswords();
    return false;
  }
  else 
  {
    if(document.frmChangePassword.txtNewPassword.value.length < 8 || document.frmChangePassword.txtNewPassword.value.length > 16)
    {
    alert('<fmt:message bundle="${aclLabels}" key="PwdLengthVal"/>');
     
      clearAllPasswords();
      return false;
    }
  }
  document.frmChangePassword.action = "./ifms.htm?actionFlag=changePassword";
//  document.frmChangePassword.requestType.value = "ChangePwd";
 // document.frmChangePassword.submit();
  return true;
}
</script>
<STYLE type="text/css">		
#currentApplication{
	display:none;
}
#nav{
	display:none;
}
</STYLE>

<%
ResultObject result=(ResultObject)request.getAttribute("result");
Map resultMap=(Map)result.getResultValue();

boolean IsPasswdChanged = resultMap.get("IsPasswdChanged") != null ?
							Boolean.parseBoolean(resultMap.get("IsPasswdChanged").toString()) : true;
boolean newPasswdvalidate = resultMap.get("newPasswdvalidate") != null ?
Boolean.parseBoolean(resultMap.get("newPasswdvalidate").toString()) : false;
boolean minChangePwddays = resultMap.get("minChangePwddays") != null ?
		Boolean.parseBoolean(resultMap.get("minChangePwddays").toString()) : false;							
boolean commonpassword = resultMap.get("commonpassword") != null ?
	   Boolean.parseBoolean(resultMap.get("commonpassword").toString()) : false;
		if(!IsPasswdChanged && !newPasswdvalidate )
		{
		%>    
		      <script type="text/javascript">
		  			alert('<fmt:message bundle="${aclLabels}" key="CorrectOldPwd"/>');
		      </script>
		<%   
		}
if(newPasswdvalidate)
{
%>    
      <script type="text/javascript">
  			alert('<fmt:message bundle="${aclLabels}" key="pwrdRedundancycheck"/>');
      </script>
<%   
}

if(commonpassword)
{
%>    
      <script type="text/javascript">
  			alert('<fmt:message bundle="${aclLabels}" key="commonpassword"/>');
      </script>
<%   
}

if(resultMap.get("newDefaultPwd") != null)
{
%>    
      <script type="text/javascript">
  			alert('<fmt:message bundle="${aclLabels}" key="newDefaultPwd"/>');
      </script>
<%   
}

if(minChangePwddays)
{
%>    
      <script type="text/javascript">
  			alert('<fmt:message bundle="${aclLabels}" key="minchangepwddays"/><fmt:message bundle="${Constants}" key="MIN_CHANGE_PWD_DAYS"/><fmt:message bundle="${aclLabels}" key="minchangepwddays1"/>');  			
      </script>
<%   
}
%>
<hdiits:form name="frmChangePassword" method="POST"  validate="true">  
<div id="tabmenu"> <ul id="maintab" class="shadetabs"> 
						<li class="selected" >  <a href="#" rel="tcontent1"> <hdiits:caption captionid="ChangePwdTitle" bundle="${aclLabels}"/> </a> </li>
                   </ul>
</div>  
<div class="tabcontentstyle" > <div id="tcontent1" class="tabcontent"  tabno="0">        

<hdiits:table width="100%" align="center" >
<hdiits:tr>
<hdiits:td align="center"><span class="titles"><hdiits:caption captionid="ChangePwdTitle" bundle="${aclLabels}"/></span></hdiits:td>
</hdiits:tr>

<%if(resultMap.get("chngPwdRequired") != null){%>
<hdiits:tr>
<hdiits:td >
<fmt:message var="chngPwdMsg" key="CHANGE_PWD_MESSAGE"  bundle="${aclLabels}" ></fmt:message>
<font style="color: red;" ><c:out value="${chngPwdMsg}"/></font>
</hdiits:td>
</hdiits:tr>
<%}%>

<%if(resultMap.get("defaultPasswordLogin") != null){%>
<hdiits:tr>
<hdiits:td >
<fmt:message var="noteChngDefaultPwd" key="noteChngDefaultPwd"  bundle="${aclLabels}" ></fmt:message>
<font style="color: red;" ><c:out value="${noteChngDefaultPwd}"/></font>
</hdiits:td>

</hdiits:tr>
<%}%>

<hdiits:tr>
 <hdiits:td  colspan="2"><font color="#FF0000" face="verdana" size="2"> <hdiits:caption captionid="ChangePassword" bundle="${aclLabels}"/></hdiits:td>
 
</hdiits:tr>
<hdiits:tr>
 <hdiits:td  colspan="2"><font color="#FF0000" face="verdana" size="2"> <hdiits:caption captionid="ChangePassword1" bundle="${aclLabels}"/></hdiits:td>
 
</hdiits:tr>
<hdiits:tr>
 <hdiits:td  colspan="2"><font color="#FF0000" face="verdana" size="2"> <hdiits:caption captionid="ChangePassword2" bundle="${aclLabels}"/></hdiits:td>
 
</hdiits:tr>
<hdiits:tr>
 <hdiits:td colspan="2"><font color="#FF0000" face="verdana" size="2"> <hdiits:caption captionid="ChangePassword3" bundle="${aclLabels}"/></hdiits:td>
 
</hdiits:tr>
<hdiits:tr>
 <hdiits:td colspan="2"><font face="verdana" size="2">(4)The password needs to be changed every 30 days </hdiits:td>
 
</hdiits:tr>
<hdiits:tr>
 <hdiits:td colspan="2"><font face="verdana" size="2">(5)While changing the password please ensure that the new password is not similar to the last three passwords </hdiits:td>
 
</hdiits:tr>
<hdiits:tr>
 <hdiits:td colspan="2"><font face="verdana" size="2">(6)In case of loss of password please contact onsite coordinator </hdiits:td>
 
</hdiits:tr>

</hdiits:table> 

<hdiits:table width="100%" align="center">
<hdiits:tr>
 <hdiits:td width="150"> <span class="Label"><hdiits:caption captionid="Oldpassword" bundle="${aclLabels}"/></span> </hdiits:td><hdiits:td><hdiits:password type="txt-password" name="txtOldPassword"  validation="txt.isrequired"  caption="tt"  mandatory="true" onkeypress="javascript:checkCapLock(event,1);"></hdiits:password></hdiits:td>
 <div id="caps_on1" style="visibility:hidden;color:red;">Caps Lock is on.</div>
</hdiits:tr>
<hdiits:tr>
 <hdiits:td width="150"><span class="Label"> <hdiits:caption captionid="Newpassword" bundle="${aclLabels}"/> </span></hdiits:td><hdiits:td><hdiits:password  type="txt-password" name="txtNewPassword"  validation="txt.isrequired"  caption="tt"  mandatory="true" onkeypress="javascript:checkCapLock(event,2);"></hdiits:password></hdiits:td>
 <div id="caps_on2" style="visibility:hidden;color:red;">Caps Lock is on.</div>
</hdiits:tr>
<hdiits:tr>
 <hdiits:td width="150"> <span class="Label"><hdiits:caption captionid="Reenternewpwd" bundle="${aclLabels}"/>  </span></hdiits:td><hdiits:td><hdiits:password  type="txt-password" name="txtReEntNewPassword"  validation="txt.isrequired"  caption="tt"  mandatory="true" onkeypress="javascript:checkCapLock(event,3);"></hdiits:password></hdiits:td>
 <div id="caps_on3"  style="visibility:hidden;color:red;">Caps Lock is on.</div>
</hdiits:tr>
 <%--
<hdiits:tr>
 <hdiits:td /> 
 <hdiits:td>
	<hdiits:button captionid="SaveBtn"  name="btnSubmit" type="button" onclick="return validateChangePassword()" value="Save"/> &nbsp; &nbsp; &nbsp;
	<hdiits:resetbutton   name="btnReset" type="button" value="Reset" /></hdiits:td>
</hdiits:tr>
--%>
<input type="hidden" name="updatePwd" value="Y" />
</hdiits:table>          
 </div>
 
				<fmt:message var="closeBtnValue" key="CancelBtn"  bundle="${aclLabels}" ></fmt:message>
				<fmt:message var="SaveBtn" key="SaveBtn" bundle="${aclLabels}" ></fmt:message>	
                <hdiits:jsField name="validateChangePassword" jsFunction="validateChangePassword()"/>
				<script type="text/javascript">
				var navDisplay = false;
				document.getElementById("home").style.display='none';
				document.getElementById("help").style.display='none';
				document.getElementById("setting1").style.display='none';
				/*document.getElementById("toolbarSetting").style.display='none'; */
				</script>
				<c:choose>
					<c:when test="${not empty param.fromToolbar}" >
						<jsp:include page="../core/tabnavigation.jsp">
							<jsp:param name="submitText" value="${SaveBtn}" />
							<jsp:param name="closeText" value="${closeBtnValue}"/>
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
<script type="text/javascript">initializetabcontent("maintab")</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="txtOldPassword,txtNewPassword,txtReEntNewPassword"/> 
</hdiits:form> 		




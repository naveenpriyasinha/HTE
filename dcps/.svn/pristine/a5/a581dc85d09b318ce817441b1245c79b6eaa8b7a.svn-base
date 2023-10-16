<% 
try{
//By Keyur Patel - 202428	
%>


<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmScreenMst"%>
<%@page import="java.util.Set"%>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmDtlScreen"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="com.tcs.sgv.appgen.constant.Constant"%>
<script type="text/javascript" >
	var navDisplay = true;
</script>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="validation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>

<fmt:setBundle basename="resources.appgen.Labels" var="appgenLables" scope="request"/>
<fmt:setBundle basename="resources.CommonLables" var="adminLables" scope="request"/>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" ></c:set>
<c:set var="editStatus" value="${resValue.editStatus}"></c:set>


<script type="text/javascript">
//@author keyur patel(202428) 
function submitFunction()
{		

	if(validateForm_multiLangSelectScreen())
	{
		if(document.getElementById('isMultilingual').checked == true )
		{
			document.getElementById('multilingualStatus').value = "YES";
			//alert("Is multilingual ---> "+document.getElementById('multilingualStatus').value);
			document.forms[0].action='hdiits.htm?actionFlag=getScreenLanguages';						
		}
		else
		{
			document.getElementById('multilingualStatus').value = "NO";
			//alert("Is not multilingual ---> "+document.getElementById('multilingualStatus').value);
			document.forms[0].action='hdiits.htm?actionFlag=getScreenLanguages';
		}
		document.getElementById('toJSP').value = "appgen-detailScreenWizard";	
		document.forms[0].submit();			
	}
	else
	{
		return true;
	}	
	
}

function getScreenDetails()
{
	if(validateForm_multiLangSelectScreen())
	{
		document.forms[0].action='hdiits.htm?actionFlag=getScreenDetailsForEdit';
		document.forms[0].submit();
	}
	else
	{
		return true;
	}		
}

</script>

<c:choose>
	<c:when test="${editStatus eq null}">
	
		<c:set var="languageCount" value="${resValue.languageCount}"></c:set>
		<c:set var="allLanguagesList" value="${resValue.allLanguagesList}"></c:set>
			
		<hdiits:form name="multiLangSelectScreen" validate = "true" method ="post" action="./hdiits.htm">
			
			<c:forEach items="${allLanguagesList}" var="lang">
				<c:set var="defaultLangId" value="${lang.langId}"></c:set>
				<c:set var="defaultLangName" value="${lang.langName}"></c:set>
			</c:forEach>
			
			<div id="tabmenu">
				<ul id="maintab" class="shadetabs">
					<li class="selected">
						<a href="#" rel="tcontent1">${defaultLangName}</a>
					</li>			
				</ul>
			</div>
		
			<div class="tabcontentstyle">		
						
				<div id=tcontent1 class="tabcontent" tabno="1">
					<br/><br/>
					<center><h2><b><fmt:message key="ADD_SCREEN_WIZARD_${defaultLangId}"  bundle="${appgenLables}"/></b></h2></center>
								
					<br/><br/><br/><br/><br/>
					<table cellspacing="10" cellpadding="0" height="30%" width="70%" >
						<tr>
							<td><b><fmt:message key="SCREEN_NAME_${defaultLangId}"  bundle="${appgenLables}"/></b></td>
							<td><hdiits:text   tabindex="1" size="35" maxlength="60" name="screenName_default" validation="txt.isrequired"  mandatory="true" captionid="SCREEN_NAME_${defaultLangId}" bundle="${appgenLables}" /></td>
						</tr>
						
						<tr>
							<td>						
								<b><fmt:message key="IS_MULTILINGUAL_${defaultLangId}"  bundle="${appgenLables}"/></b>
								<hdiits:checkbox name="isMultilingual" value="YES" captionid="YES_${defaultLangId}" bundle="${appgenLables}" default="NO" attributesText="this"></hdiits:checkbox>
							</td>
						</tr>	
						
						<tr>
							<td>						
								<b><fmt:message key="IS_COPY_ALLVALUES_${defaultLangId}"  bundle="${appgenLables}"/></b>
								<hdiits:checkbox name="isCopyAllValues" value="Y" captionid="YES_${defaultLangId}" bundle="${appgenLables}" default="N" attributesText="this"></hdiits:checkbox>
							</td>
						</tr>								
						
					</table>
					
					<br/><br/><br/><br/><br/><br/>
					
					<table cellspacing="10" cellpadding="0" height="10%" width="100%">
						<tr>
							<td align="right" width="900">
								<hdiits:button value="Next" onclick="return submitFunction()" name="multiLangButton" type="button"/>
							</td>
						</tr>		
					</table>
											
				</div>
			</div>
		        
		    <script type="text/javascript">
		    	initializetabcontent("maintab")
		    </script>         
		    
		    <input type="hidden" value="NO" name="editStatus">
		    <input type="hidden" value="${defaultLangId}" name="defaultLangId"> 
		   	<input type="hidden" value="${defaultLangName}" name="defaultLangName">    	
		    <input type="hidden" name="multilingualStatus">
		    <input type="hidden" name="toJSP">
		
		    <hdiits:validate  locale='<%=(String)session.getAttribute("locale") %>' /> 
		</hdiits:form>
	
	</c:when>
	<c:otherwise>
		<c:set var="multilingualStatus" value="${resValue.multilingualStatus}"></c:set>
		<c:set var="editLanguageCount" value="${resValue.editLanguageCount}"></c:set>
		<c:set var="editDefaultLangId" value="${resValue.editDefaultLangId}"></c:set>
		<c:set var="editDefaultLangName" value="${resValue.editDefaultLangName}"></c:set>
		<c:set var="editAdmScreenMstVO" value="${resValue.AdmScreenMstVO}"></c:set>
		<c:set var="isCopyAllValues" value="${resValue.isCopyAllValues}"></c:set>			
		<hdiits:form name="multiLangSelectScreen" validate = "true" method ="post" action="./hdiits.htm">
		
			<div id="tabmenu">
				<ul id="maintab" class="shadetabs">
					<li class="selected">
						<a href="#" rel="tcontent1">${editDefaultLangName}</a>
					</li>			
				</ul>
			</div>	
			
			<div class="tabcontentstyle">		
						
				<div id=tcontent1 class="tabcontent" tabno="1">
					<br/><br/>
					<center><h2><b><fmt:message key="EDIT_SCREEN_WIZARD_${editDefaultLangId}"  bundle="${appgenLables}"/></b></h2></center>
								
					<br/><br/><br/><br/><br/>
					<table cellspacing="10" cellpadding="0" height="30%" width="70%" >
						<tr>
		 					<td><b><fmt:message key="SCREEN_NAME_${editDefaultLangId}"  bundle="${appgenLables}"/></b></td>
							<td><hdiits:text tabindex="1" size="35" maxlength="60" name="screenName_default"  default="${editAdmScreenMstVO.screenName}" mandatory="true" validation="txt.isrequired" captionid="SCREEN_NAME_${editDefaultLangId}" bundle="${appgenLables}"/></td>
						</tr>
						
						<tr>
							<td>
								<c:choose>
									<c:when test="${multilingualStatus eq 'YES'}">
											<b><fmt:message key="MULTILINGUAL_${editDefaultLangId}"  bundle="${appgenLables}"/></b>												
									</c:when>
									<c:otherwise>
											<b><fmt:message key="NONMULTILINGUAL_${editDefaultLangId}"  bundle="${appgenLables}"/></b>						
									</c:otherwise>
								</c:choose>					
							</td>
						</tr>
						<tr>
							<td>
								<c:choose>
									<c:when test="${multilingualStatus eq 'YES'}">
											<b><fmt:message key="IS_COPY_ALLVALUES_${editDefaultLangId}"  bundle="${appgenLables}"/></b>
											<c:choose>
											<c:when test="${isCopyAllValues eq 'Y'}">
												<hdiits:checkbox name="isCopyAllValues" value="Y" captionid="YES_${editDefaultLangId}" bundle="${appgenLables}" default="Y"></hdiits:checkbox>											
											</c:when>
											<c:otherwise>
												<hdiits:checkbox name="isCopyAllValues" value="Y" captionid="YES_${editDefaultLangId}" bundle="${appgenLables}" default="N"></hdiits:checkbox>											
											</c:otherwise>
											</c:choose>
									</c:when>									
								</c:choose>					
							</td>
						</tr>								
						
					</table>
					
					<br/><br/><br/><br/><br/><br/>
					
					<table cellspacing="10" cellpadding="0" height="10%" width="100%">
						<tr>
							<td align="right" width="900">
								<hdiits:button value="Next" onclick="getScreenDetails()" name="multiLangButton" type="button"/>
							</td>
						</tr>		
					</table>
											
				</div>
			</div>
		        
		    <script type="text/javascript">
		    	initializetabcontent("maintab")
		    </script>         
		    
			<input type="hidden" value="${editStatus}" name="editStatus">
		    <input type="hidden" value="${editAdmScreenMstVO.screenId}" name="editScreenId">
		    <input type="hidden" value="${editDefaultLangId}" name="editDefaultLangId"> 
		   	<input type="hidden" value="${editDefaultLangName}" name="editDefaultLangName">    	
		    <input type="hidden" value="${editLanguageCount}" name="editLanguageCount">
		    <input type="hidden" value="${multilingualStatus}" name="multilingualStatus">
		    
		    <hdiits:validate  locale='<%=(String)session.getAttribute("locale") %>' /> 
	    </hdiits:form>
	</c:otherwise>	
</c:choose>
		
	
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>	

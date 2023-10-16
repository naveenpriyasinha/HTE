<% 
//By Keyur Patel - 202428
try
{
%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmTableMst"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="validation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>

<script type="text/javascript" >
	var navDisplay = true;
	
	function submitFunction()
	{	
			if(validateForm_screenTableWizard())
			{
				document.forms[0].action='hdiits.htm?actionFlag=getTableDetails';
			  	document.forms[0].submit();		  									  				  	
			}
			else
			{
				return true;
			}		
	}

</script>

<fmt:setBundle basename="resources.CommonLables" var="adminLables" scope="request"/>
<fmt:setBundle basename="resources.appgen.Labels" var="appgenLables" scope="request"/>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" ></c:set>
<c:set var="editStatus" value='<%= StringUtility.getParameter("editStatus",request)%>'></c:set>

<c:choose>
<c:when test="${editStatus eq 'NO'}">
	
	<c:set var="defaultLangId" value='<%=StringUtility.getParameter("defaultLangId",request)%>'></c:set>
	<c:set var="defaultLangName" value='<%=StringUtility.getParameter("defaultLangName",request)%>'></c:set>
	<c:set var="allLanguagesList" value='<%=StringUtility.getParameter("allLanguagesList",request)%>'></c:set>
	<c:set var="isCopyAllValues" value='<%=StringUtility.getParameter("isCopyAllValues",request)%>'></c:set>
	
	<hdiits:form name="screenTableWizard" validate = "true" method ="post" action="hdiits.htm"> 
				
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
				<center><h2><b><fmt:message key="ADD_TABLE_WIZARD_${defaultLangId}"  bundle="${appgenLables}"/></b></h2></center>			
				<br/><br/><br/>
				
				
				<table cellspacing="10" cellpadding="0" height="30%" width="100%" >
					<tr>
						<td><b><fmt:message key="TABLE_NAME_${defaultLangId}"  bundle="${appgenLables}"/></b></td>
						<td><hdiits:text tabindex="1" size="35" maxlength="60" name="tableName" mandatory="true" validation="txt.isrequired" captionid="TABLE_NAME_${defaultLangId}" bundle="${appgenLables}"/></td>
					</tr>
					
					<tr>
						<td><b><fmt:message key="VO_CLASS_NAME_${defaultLangId}"  bundle="${appgenLables}"/></b></td>
						<td><hdiits:text tabindex="2" size="35" maxlength="90" name="voClassName" mandatory="true" validation="txt.isrequired" captionid="VO_CLASS_NAME_${defaultLangId}" bundle="${appgenLables}"/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="actionFlag" value="getTableDetails" > 		
						</td>
					</tr>							
				</table>
				
				<br/><br/><br/><br/><br/><br/><br/><br/>
				
				<table cellspacing="10" cellpadding="0" height="10%" width="100%">
					<tr>
						<td align="right" width="900">
							<hdiits:button name="submitButton" value="Next" onclick="submitFunction()" type="button"/>		
						</td>
					</tr>		
				</table>
				
				
				<c:set var="i" value="0"></c:set>	
				<%int i = 0; %>
				<c:forEach items="${allLanguagesList}" var="lang">
					<input type="hidden" value="<%=StringUtility.getParameter("screenCap_Lang_"+i,request)%>" name="screenCap_Lang_${i}">
					<input type="hidden" value="<%=StringUtility.getParameter("screenTitl_Lang_"+i,request)%>" name="screenTitl_Lang_${i}">
					<c:set var="i" value="${i+1}"></c:set>	
					<%i++;%>
				</c:forEach>
						
				<input type="hidden" value="${editStatus}" name="editStatus">
				
				<input type="hidden" value="<%=StringUtility.getParameter("screenName_default",request)%>" name="screenName_default">
				<input type="hidden" value="<%=StringUtility.getParameter("multilingualStatus",request)%>" name="multilingualStatus">
				<input type="hidden" value="<%=StringUtility.getParameter("languageCount",request)%>" name="languageCount">
				<input type="hidden" value="${defaultLangId}" name="defaultLangId">		
				<input type="hidden" value="${defaultLangName}" name="defaultLangName">
				<input type="hidden" value="${isCopyAllValues}" name="isCopyAllValues">		
			</div>	        
		</div>
	      	
	    
	    <script type="text/javascript">
	    	initializetabcontent("maintab")
	    </script>     		
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>	
	</hdiits:form>


</c:when>

<c:otherwise>
	
	
	<c:set var="admTableMstVO" value="${resValue.admTableMstVO}"></c:set>
	<c:set var="editDefaultLangId" value='<%=StringUtility.getParameter("editDefaultLangId",request)%>'></c:set>
	<c:set var="editDefaultLangName" value='<%=StringUtility.getParameter("editDefaultLangName",request)%>'></c:set>
	<c:set var="editAllLanguagesList" value='<%=StringUtility.getParameter("editAllLanguagesList",request)%>'></c:set> 
	<c:set var="editMultipleLang" value='<%=StringUtility.getParameter("editMultipleLang",request)%>'></c:set>
	<c:set var="isCopyAllValues" value='<%=StringUtility.getParameter("isCopyAllValues",request)%>'></c:set>
	
	<hdiits:form name="screenTableWizard" validate = "true" method ="post" action="hdiits.htm"> 				
					
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
				<center><h2><b><fmt:message key="ADD_TABLE_WIZARD_${editDefaultLangId}"  bundle="${appgenLables}"/></b></h2></center>			
				<br/><br/><br/>
				
				
				<table cellspacing="10" cellpadding="0" height="30%" width="100%" >
					<tr>
						<td><b><fmt:message key="TABLE_NAME_${editDefaultLangId}"  bundle="${appgenLables}"/></b></td>
						<td><hdiits:text tabindex="1" size="35" maxlength="60" name="tableName" default="${admTableMstVO.tableName}" mandatory="true" validation="txt.isrequired" captionid="TABLE_NAME_${editDefaultLangId}" bundle="${appgenLables}"/></td>
					</tr>
					
					<tr>
						<td><b><fmt:message key="VO_CLASS_NAME_${editDefaultLangId}"  bundle="${appgenLables}"/></b></td>
						<td><hdiits:text tabindex="2" size="35" maxlength="90" name="voClassName" default="${admTableMstVO.voClassName}" mandatory="true" validation="txt.isrequired" captionid="VO_CLASS_NAME_${editDefaultLangId}" bundle="${appgenLables}"/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="actionFlag" value="getTableDetails" > 		
						</td>
					</tr>							
				</table>
				
				<br/><br/><br/><br/><br/><br/><br/><br/>
				
				<table cellspacing="10" cellpadding="0" height="10%" width="100%">
					<tr>
						<td align="right" width="900">
							<hdiits:button name="submitButton" value="Next" onclick="submitFunction()" type="button"/>		
						</td>
					</tr>		
				</table>
				
				
				<c:set var="i" value="0"></c:set>	
				<%int i = 0; %>
				<c:forEach items="${editAllLanguagesList}" var="lang">
					<input type="hidden" value="<%=StringUtility.getParameter("screenDtlId_"+i,request)%>" name="screenDtlId_${i}">
					<input type="hidden" value="<%=StringUtility.getParameter("screenCap_Lang_"+i,request)%>" name="screenCap_Lang_${i}">
					<input type="hidden" value="<%=StringUtility.getParameter("screenTitl_Lang_"+i,request)%>" name="screenTitl_Lang_${i}">
					<c:set var="i" value="${i+1}"></c:set>	
					<%i++;%>
				</c:forEach>
				
				<input type="hidden" value="${editStatus}" name="editStatus">
				<input type="hidden" value="${admTableMstVO.tableId}" name="tableId">
				<input type="hidden" value="<%=StringUtility.getParameter("editScreenId",request)%>" name="editScreenId">
				<input type="hidden" value="<%=StringUtility.getParameter("screenName_default",request)%>" name="screenName_default">
				<input type="hidden" value="${editDefaultLangId}" name="defaultLangId">		
				<input type="hidden" value="${editDefaultLangName}" name="defaultLangName">
				<input type="hidden" value="<%=StringUtility.getParameter("editLanguageCount",request)%>" name="languageCount">
				<input type="hidden" value="<%=StringUtility.getParameter("multilingualStatus",request)%>" name="multilingualStatus">
				<input type="hidden" value="${editAllLanguagesList}" name="editAllLanguagesList">						
				<input type="hidden" value="${isCopyAllValues}" name="isCopyAllValues">				
			</div>	        
		</div>
	    
	    <script type="text/javascript">
	    	initializetabcontent("maintab")
	    </script>     		
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />	
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
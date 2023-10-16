<% 
//By Keyur Patel - 202428
try
{
%>

<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="validation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>


<script type="text/javascript">
	var navDisplay = true;

	function resetForm()
	{
		  	if(confirm("All values will be reseted please confirm !") == true)
		  	{
			  		document.forms[0].reset();
		  	}								  				  	
	}
	
	
	function submitFuncForAdd()
	{	
			if(validateForm_detailScreenWizard())
			{
				document.forms[0].action='hdiits.htm?actionFlag=getScreenLanguages';
		  		document.getElementById('toJSP').value = "appgen-screenTableWizard";
		  		document.forms[0].submit();		  									  				  	
			}
			else
			{
				return true;
			}	
	}
	function submitFuncForEdit()
	{	
			if(validateForm_detailScreenWizard())
			{
				document.forms[0].action='hdiits.htm?actionFlag=getTableDetailsForPerticularScreen';
			  	document.forms[0].submit();		  									  				  	
			}
			else
			{
				return true;
			}	
	}	
	
</script>

<fmt:setBundle basename="resources.appgen.Labels" var="appgenLables" scope="request"/>
<fmt:setBundle basename="resources.CommonLables" var="adminLables" scope="request"/>
<fmt:setLocale value="en_US"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>



<c:set var="editStatus" value='<%= StringUtility.getParameter("editStatus",request)%>'></c:set>

<c:set var="i" value="0"></c:set>

<c:choose>
<c:when test="${editStatus eq 'NO'}">

	<c:set var="languageCount" value="${resValue.languageCount}"></c:set>
	<c:set var="multilingualStatus" value="${resValue.multilingualStatus}"></c:set>
	<c:set var="allLanguagesList" value="${resValue.allLanguagesList}"></c:set>
	<c:set var="isCopyAllValues" value='<%=StringUtility.getParameter("isCopyAllValues",request)%>'></c:set>	
	<hdiits:form name="detailScreenWizard" validate ="true" method ="post" action="hdiits.htm">
		<div id="tabmenu">
			<ul id="maintab" class="shadetabs">			
				<c:set var="tabIter" value="1"></c:set>
					<c:forEach items="${allLanguagesList}" var="lang">				
						<li class="selected">											
							<a href="#" rel= "tcontent${tabIter}">${lang.langName}</a>
						</li>
					<c:set var="tabIter" value="${tabIter+1}"></c:set>	
				</c:forEach>									
			</ul>
		</div>
				
		<c:set var="i" value="0"></c:set>	
		<c:set var="tabIter" value="1"></c:set>
		<div class="tabcontentstyle">						
			
			<c:forEach items="${allLanguagesList}" var="lang">
				
				<div id="tcontent${tabIter}" class="tabcontent" tabno="${i}">
					
					
					<br/><br/>
					<center><h2><b><fmt:message key="DETAIL_SCREEN_WIZARD_${lang.langId}"  bundle="${appgenLables}"/> </b></h2></center>
								
					<br/><br/>
					<table cellspacing="10" cellpadding="0" height="50%" width="100%" >
											
						<tr>
							<td><b><fmt:message key="SCREEN_CAPTION_${lang.langId}"  bundle="${appgenLables}"/></b></td>																							
							<td><hdiits:text  tabindex="2" size="35" maxlength="60" name="screenCap_Lang_${i}" mandatory="true" validation="txt.isrequired" captionid="SCREEN_CAPTION_${lang.langId}" bundle="${appgenLables}"/></td>
						</tr>						
						
						<tr>
							<td><b><fmt:message key="SCREEN_TITLE_${lang.langId}"  bundle="${appgenLables}"/></b></td>
							<td><hdiits:text tabindex="3" size="35" maxlength="60" name="screenTitl_Lang_${i}" mandatory="true" validation="txt.isrequired" captionid="SCREEN_TITLE_${lang.langId}" bundle="${appgenLables}"/></td>
						</tr>
						
						
					</table>
					<br><br>
						
					<br/><br/><br/><br/>
					
					<!-- For showing buttons for the tab as per the tab sequence and number of tabs -->	
					<table>
						<tr>
							<td align="right" width="900">
								<hdiits:button name="resetButton_${i}" value="Reset" onclick="resetForm()" type="button"/>
								
								<c:choose>
									<c:when test="${languageCount eq 1}">
										<hdiits:button name="submitButton_${i}" value="Next" onclick="submitFuncForAdd()" type="button"/>								
									</c:when>
									<c:when test="${i+1 lt languageCount}">
										<c:choose>
											<c:when test="${i+1 eq 1}">
												<hdiits:button name="nextButton_${i}" value="Next" onclick="goToNextTab()" type="button"/>						           	
											</c:when>	
											<c:otherwise>
												<hdiits:button name="prevButton_${i}" value="Previous" onclick="goToPrevTab()" type="button" />
												<hdiits:button name="nextButton_${i}" value="Next" onclick="goToNextTab()" type="button"/>	    	
											</c:otherwise>
										</c:choose>							
									</c:when>
									<c:otherwise>
										<hdiits:button name="prevButton_${i}" value="Previous" onclick="goToPrevTab()" type="button"/>
										<hdiits:button name="submitButton_${i}" value="Next" onclick="submitFuncForAdd()" type="button"/>       		
									</c:otherwise>								
								</c:choose>
								
							</td>
						</tr>
					</table>
								
				</div>
				<c:set var="i" value="${i+1}"></c:set>	
				<c:set var="tabIter" value="${tabIter+1}"></c:set>
			
			</c:forEach>
						  		
		</div>
		
		<br/>
		<br/>
		<br/>	
		<script type="text/javascript">
    			initializetabcontent("maintab")
    	</script>  
    	
    	<input type="hidden" name="toJSP">
    	<input type="hidden" value="${editStatus}" name="editStatus">
    	<input type="hidden" value="<%=StringUtility.getParameter("screenName_default",request)%>" name="screenName_default">   
    	<input type="hidden" value="${multilingualStatus}" name="multilingualStatus">
    	<input type="hidden" value="${languageCount}" name="languageCount">
    	<input type="hidden" value="<%=StringUtility.getParameter("defaultLangId",request)%>" name="defaultLangId"> 
    	<input type="hidden" value="<%=StringUtility.getParameter("defaultLangName",request)%>" name="defaultLangName">    	
    	<input type="hidden" value="${allLanguagesList}" name="allLanguagesList">   
    	<input type="hidden" value="${isCopyAllValues}" name="isCopyAllValues">   	
    	
    	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />    	 	     
	</hdiits:form>
</c:when>

<c:otherwise>


	<c:set var="admDltScreenList" value="${resValue.admDltScreenList}"></c:set>
	<c:set var="editAllLanguagesList" value="${resValue.cmnLanguageMstList}"></c:set>
	<c:set var="editLanguageCount" value='<%=StringUtility.getParameter("editLanguageCount",request)%>'></c:set>
	<c:set var="isCopyAllValues" value='<%=StringUtility.getParameter("isCopyAllValues",request)%>'></c:set>	
	
	<hdiits:form name="detailScreenWizard" validate ="true" method ="post" action="hdiits.htm">
	
		<div id="tabmenu">
			<ul id="maintab" class="shadetabs">	
					<c:set var="tabIter" value="1"></c:set>		
					<c:forEach items="${editAllLanguagesList}" var="lang">				
						<li class="selected">
							<a href="#" rel= "tcontent${tabIter}" >${lang.langName}</a>
						</li>
						<c:set var="tabIter" value="${tabIter+1}"></c:set>	
					</c:forEach>									
			</ul>
		</div>
		
		<c:set var="i" value="0"></c:set>	
		<c:set var="tabIter" value="1"></c:set>		
		<div class="tabcontentstyle">						
		
			
			<c:forEach items="${admDltScreenList}" var="admDtlScreenVO">
				
				<div id="tcontent${tabIter}" class="tabcontent" tabno="${i}">
					<br/><br/>
					<center><h2><b><fmt:message key="DETAIL_SCREEN_WIZARD_${admDtlScreenVO.langId}"  bundle="${appgenLables}"/> </b></h2></center>
								
					<br/><br/>
					<table cellspacing="10" cellpadding="0" height="50%" width="100%" >
											
						<tr>
							<td><b><fmt:message key="SCREEN_CAPTION_${admDtlScreenVO.langId}"  bundle="${appgenLables}"/></b></td>																							
							<td><hdiits:text  tabindex="2" size="35" maxlength="60" name="screenCap_Lang_${i}" default="${admDtlScreenVO.admDtlscrCaption}" mandatory="true" validation="txt.isrequired" captionid="SCREEN_CAPTION_${admDtlScreenVO.langId}"  bundle="${appgenLables}"/></td>
						</tr>						
						
						<tr>
							<td><b><fmt:message key="SCREEN_TITLE_${admDtlScreenVO.langId}"  bundle="${appgenLables}"/></b></td>
							<td><hdiits:text tabindex="3" size="35" maxlength="60" name="screenTitl_Lang_${i}" default="${admDtlScreenVO.admDtlscrTitle}" mandatory="true" validation="txt.isrequired" captionid="SCREEN_TITLE_${admDtlScreenVO.langId}"  bundle="${appgenLables}"/></td>
						</tr>
						<input type="hidden" value="${admDtlScreenVO.admDtlscrId}" name="screenDtlId_${i}">
						
					</table>
					<br><br>
						
					<br/><br/><br/><br/>
					
					<!-- For showing buttons for the tab as per the tab sequence and number of tabs -->	
					<table>
						<tr>
							<td align="right" width="900">
								<hdiits:button name="resetButton_${i}" value="Reset" onclick="resetForm()" type="button"/>
								
								<c:choose>
									<c:when test="${editLanguageCount eq 1}">
										<hdiits:button name="submitButton_${i}" value="Next" onclick="submitFuncForEdit()" type="button"/>								
									</c:when>
									<c:when test="${i+1 lt editLanguageCount}">
										<c:choose>
											<c:when test="${i+1 eq 1}">
												<hdiits:button name="nextButton_${i}" value="Next" onclick="goToNextTab()" type="button"/>						           	
											</c:when>	
											<c:otherwise>
												<hdiits:button name="prevButton_${i}" value="Previous" onclick="goToPrevTab()" type="button"/>
												<hdiits:button name="nextButton_${i}" value="Next" onclick="goToNextTab()" type="button"/>						           	
											</c:otherwise>
										</c:choose>							
									</c:when>
									<c:otherwise>
										<hdiits:button name="prevButton_${i}" value="Previous" onclick="goToPrevTab()" type="button"/>
										<hdiits:button name="submitButton_${i}" value="Next" onclick="submitFuncForEdit()" type="button"/>       		
									</c:otherwise>								
								</c:choose>
								
							</td>
						</tr>
					</table>
	
				</div>
				<c:set var="i" value="${i+1}"></c:set>	
				<c:set var="tabIter" value="${tabIter+1}"></c:set>	
			</c:forEach>
						  		
		</div>

		<br/>
		<br/>
		<br/>	
		<script type="text/javascript">
    			initializetabcontent("maintab")
    	</script>  
    	
		<input type="hidden" value="${editStatus}" name="editStatus">
		
		   	
    	<input type="hidden" value="<%=StringUtility.getParameter("editScreenId",request)%>" name="editScreenId">
    	<input type="hidden" value="<%=StringUtility.getParameter("screenName_default",request)%>" name="screenName_default">   
		<input type="hidden" value="<%=StringUtility.getParameter("editDefaultLangId",request)%>" name="editDefaultLangId"> 
    	<input type="hidden" value="<%=StringUtility.getParameter("editDefaultLangName",request)%>" name="editDefaultLangName"> 
		<input type="hidden" value="<%=StringUtility.getParameter("multilingualStatus",request)%>" name="multilingualStatus"> 
		<input type="hidden" value="${editLanguageCount}" name="editLanguageCount">
    	<input type="hidden" value="${editAllLanguagesList}" name="editAllLanguagesList">
    	<input type="hidden" value="${isCopyAllValues}" name="isCopyAllValues">       	
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








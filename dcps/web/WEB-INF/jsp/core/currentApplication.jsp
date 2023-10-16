<%@ page import="com.tcs.sgv.core.valueobject.ResultObject" %>
<%@ page import="java.util.Map" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<c:set var="resultObj"	value="${result}"/>
<c:set var="resultMap" value="${resultObj.resultValue}"/>
<c:set var="backUrl" value="${resultMap.backUrl}"/>
<c:set var="appHierarchy" value="${resultMap.strAppHierarchy}"/>
<c:set var="caseNumber"	value="${sessionCaseNumber}"/>

<c:set var="backNextUtilReq" value='<%=session.getAttribute("backNextUtilReq")%>' scope="session"></c:set>

<fmt:setBundle basename="resources.core.lables" var="corelables" scope="request"/>
<fmt:setBundle basename="resources.core.screen_number_labels" var="screenlables" scope="request"/>

<c:set var="backButton" value="Back"/>
<% 
if(request.getSession( false ).getAttribute( "reportVO" + request.getParameter( "reportCode" ) )!=null){
	com.tcs.sgv.common.valuebeans.reports.ReportVO reportVO = (com.tcs.sgv.common.valuebeans.reports.ReportVO) request.getSession( false ).getAttribute( "reportVO" + request.getParameter( "reportCode" ) );
	if(reportVO!=null){

		if(reportVO.getBackUrl()!=null && reportVO.getBackUrl().length()!=0){
			pageContext.setAttribute("backUrl",reportVO.getBackUrl());		
		}
	}
}

%>

<script type="text/javascript" >

function showHideRightInfo()
{

	if( document.getElementById("rightInfo").style.display == 'none')
	{
		document.getElementById("rightClmnCollpsBtn").style.display = '';
		document.getElementById("rightClmnOpnClsBtn").style.display = 'none';
		//document.getElementById("menuFont").style.display = '';	
		document.getElementById("rightInfo").style.display = '';	
		//document.getElementById('rightClmnOpnClsBtn').style.background = "url(themes/${themename}/images/win-tool-collapse-012.png) no-repeat";
	}
	else
	{
		//document.getElementById("menuFont").style.display = '';	
		document.getElementById("rightInfo").style.display = 'none';	
		//document.getElementById('rightClmnOpnClsBtn').style.background = "url(themes/${themename}/images/win-tool-expand-011.png) no-repeat left";
		document.getElementById("rightClmnOpnClsBtn").style.display = '';
		document.getElementById("rightClmnCollpsBtn").style.display = 'none';
	}
}
</script>

 <table width="100%"  border="0" cellpadding="0" cellspacing="0">
     <tr>
    
     <c:if test="${not empty caseNumber}">
     	<c:set var="caseNumSeprator" value=":" />
     	<c:set var="defaultCaseLable" value="${sessionCaseNoLables}"/>
      </c:if>
   
     	
		<c:set var="widthVal" value="60"/>
     	<c:if test="${'Y' eq backNextUtilReq}">
     		<c:set var="widthVal" value="57"/>
     	</c:if>
     	<c:set var="module" value="${fn:split(appHierarchy,'>') }" />
     	<%
     		ResultObject resObj = (ResultObject)request.getAttribute("result");
     		
     		Map ResultMap = (Map)resObj.getResultValue();
     		
     		String ModName = null;
     		String FinalModName = null;
     		String PageName = null;
     		String NumberKey = null;
     		
     		if(ResultMap.containsKey("strAppHierarchy"))
     		{
	      	    String hier = ResultMap.get("strAppHierarchy").toString();
	      		
	      	    if(hier.length()!=0)
	      	    {
		      	    String module[] = hier.split(">");
		      	  
		      	 	ModName = module[1].substring(1,module[1].length()-2);
		      	 	FinalModName = ModName;
		      	    ModName = ModName.replaceAll(" ","");
		      	 	
		      	 	
		      	 	PageName = module[module.length-1].substring(1,module[module.length-1].length());
		      	 	
		      	 	PageName = PageName.replaceAll(" ","");
		      	 	
		      	 	ModName = ModName.toUpperCase();
		      	 	
		      	 	PageName = PageName.toUpperCase();
		      	 	
		      	 	ModName=ModName.concat(".");
		      	   	NumberKey = ModName.concat(PageName);
		      	 	
	      	    } 
    	 	}
     	%>
     	<c:set var="moduleArray" value="${fn:split(module[1],' ') }" />
     	 	
     	<c:set var="modulename" value="${module[1]}"/>
     	
	  
     	
     	<c:choose>
     	
     		<c:when test="${not empty appHierarchy}">
     			<td width=" id="currentApplication">
     			
	    			<b>Current Module : <%=FinalModName %></b>
	    			</td>
	    			<td><b>Screen Number : <fmt:message key="<%=NumberKey %>" bundle="${screenlables}"/></b></td>
	    			<td align = "right">
	    			<b>Current Path : <c:out value="${appHierarchy}"></c:out></b>
	    		</td>	
	    	</c:when>
	    	<c:otherwise>
     			<td id="currentApplication">
	    		</td>	
	    		<td width="${widthVal}%">
	    		</td>	
	    	</c:otherwise>
	    </c:choose>
     	
     	<c:if test="${backReq == 'true'}">
			<td width="1%" id="prevBtnId" style="cursor: hand;">
				<a href="${prevUrl}" title="Previous"><img src="themes/${themename}/images/back_icon.png" align="middle" /></a>
			</td>
		</c:if>	
    	<c:if test="${nextReq == 'true'}">
			<td width="1%" id="nextBtnId" style="cursor: hand;">
				<a href="${nextUrl}" title="Next"><img src="themes/${themename}/images/next_icon.png" align="middle" /></a>



			</td>
		</c:if>	
     </tr>
 </table>

<script language="javascript">
//var backurl='${backUrl}';
//var viewIndex = '${backUrl}';

//if(viewIndex == '')
//{
//	document.getElementById('backUrlRef').href = "#";
//	document.getElementById('backUrlRef').style.display= 'none';
//	//document.getElementById('backImg').style.display= 'none';
//}

function setBackUrl(urlParam)
{
//	document.getElementById('backUrlRef').href = urlParam;
//	document.getElementById('backUrlRef').style.display = '';
//	//document.getElementById('backImg').style.display= '';
}

</script>

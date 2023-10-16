<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.service.UserElement"%>
<%@page import=" org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>	
<%@page import="java.util.ArrayList"%>	

<fmt:setBundle basename="resources.trng.TrainingDetailsLoginPage" var="TrainingDetails" scope="request" />
<hdiits:form name="personDetail" validate="true" method="post"  >

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />	
<c:set var="scheduleLst" value="${resultMap.scheduleLst}"></c:set>
<c:set var="recordFound" value="${resultMap.recordFound}"></c:set>
<c:set var="userElementsForMenuOnPage" value="${resultMap.userElementsForMenuOnPage}"/>

<%Log logger = LogFactory.getLog(getClass());
ResultObject result=(ResultObject)request.getAttribute("result");
Map resultMap=(Map)result.getResultValue();
%>


<table width="100%">
	<tr >
	<td valign="top" width="60%">
			<%
				UserElements userElements = (UserElements)resultMap.get("homePageUserElements");
		 		if(userElements!=null)
	       		{
	       			//For managing user menu preferences		
			  		List divStatusLt = (List) resultMap.get("divStatusLt");	        			
		        			
			  		request.setAttribute("divStatusLt",divStatusLt);							  	    
			  	  	request.setAttribute("userElementsForMenuOnPage",userElements);
			%>					  		
		  		<jsp:include page="/WEB-INF/jsp/core/menuOnPage.jsp">					  			
		  			<jsp:param name="preferencesRequired"  value="true"/>	
		  			<jsp:param name="expandCollapseUtilReq" value="true"/>				  			
		  			<jsp:param name="initExpandCollpsStatus" value="none"/>
		  		</jsp:include>			  	    
			<%  	
       		}	
			else
			{
       			logger.debug("user element object is null for menu on page ");
			}
		 	%>	
	</td>
	
	<td valign="top" width="40%">
		<table class="homepagestatisticstable">
			<thead>
				<tr>
					<td>		
						<fmt:message key="TR.TRAINING_HEADER" bundle="${TrainingDetails}"></fmt:message>
					</td>
				</tr>
			</thead>	
			 <tbody>
			 <tr>
			 <td valign="top" width="30%">
				<div id="tabmenu">
				   <ul id="maintab" class="shadetabs"><li  class="selected">
				   		<a href="#"  rel="tcontent1">
				   			<hdiits:caption	captionid="TR.TRAININGDETAIL" bundle="${TrainingDetails}"  captionLang="single"/></a></li>				
				   </ul>
				</div>
				<div class="tabcontentstyle">  
				<div id="tcontent1" class="tabcontent" tabno="fmkghcf">
					
					
						 <!-- table for group and point -->
						<c:set var="count" value="1"/>
						<c:choose>
						<c:when test="${recordFound eq 'true'}">
							<display:table name="${scheduleLst}" id="row" requestURI="" export="false" decorator="" size="50%" >
							
								<display:setProperty name="paging.banner.all_items_found" value=""></display:setProperty>					   								
								<display:setProperty name="paging.banner.some_items_found" value=""></display:setProperty>
								<display:setProperty name="paging.banner.one_item_found" value=""></display:setProperty>	
								
								
								<fmt:formatDate value="${row.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="startDt" />
								<fmt:formatDate value="${row.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="endDt" />
								<c:set var="trainingName" value="${row.hrTrTrainingMst.trainingName}"></c:set>
								<c:set var="SchId" value="${row.trngScheduleId}"></c:set>
								<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" style="text-align: center;">
									<c:out value="${count}"></c:out>
								</display:column>
								<display:column class="tablecelltext" sortable="true" titleKey="TR.TRAININGNAME" headerClass="datatableheader" style="text-align: center;"> <a href="#"  onclick="timeTableReport('${SchId}')"> ${trainingName}  </a> </display:column>
								<display:column class="tablecelltext" value="${row.cmnLocationMstTrainingCenterId.locationCode}"  sortable="true" titleKey="TR.TRAININGLOCATION" headerClass="datatableheader" style="text-align: center;" ></display:column>
								<display:column class="tablecelltext" sortable="true" titleKey="TR.TRAININGTYPE" value="${row.cmnLookupMstTrainingModeLookupId.lookupName}" headerClass="datatableheader" style="text-align: center;"></display:column>
								<display:column class="tablecelltext" sortable="true" titleKey="TR.StartDt" headerClass="datatableheader" style="text-align: center;">${startDt}</display:column>
								<display:column class="tablecelltext" sortable="true" titleKey="TR.EndDt" headerClass="datatableheader" style="text-align: center;">${endDt}</display:column>
								<c:set var="count" value="${count + 1}" ></c:set>
							</display:table>
						</c:when>
						<c:otherwise>
							<fmt:message key="TR.RECORD_FOUND"/>	
						</c:otherwise>
						</c:choose>
					</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</td>
</tr>
</table>
<script type="text/javascript">
function timeTableReport(schId)
{
	var urlstyle = 'height=800,width=800,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes,top=50,left=200';
	displayModalDialog("hdiits.htm?actionFlag=reportService&reportCode=1800004&action=generateReport&asPopup=true&scheduleId="+schId+"&classNo=-1","Show",urlstyle);
	return true;
}
</script>
<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
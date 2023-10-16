 
<%
            try
            {
%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.intrsave.InterimSaveLables" var="intLables" scope="request" />

<script type="text/javascript"
	src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="draftList" value="${resultMap.draftList}" />


<hdiits:form name="frmSavedDrafts" validate="true">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a rel="tcontent1"> <hdiits:caption
			captionid="INT.TITLE" bundle="${intLables}" /> </a></li>
	</ul>
	</div>

	<hdiits:hidden name="srNo" />
	

	<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent" tabno="0">
	<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
		<display:table list="${draftList}" pagesize="12" requestURI=""
			defaultsort="1" sort="list" id="row" export="true" style="width:100%">

			<display:column property="createdDate"
				class="tablecelltext" titleKey="INT.DATE" sortable="true"
				headerClass="datatableheader" />

			<display:column property="description"
				class="tablecelltext" titleKey="INT.DESCRIPTION" sortable="true"
				headerClass="datatableheader" />

			
			<display:column class="tablecelltext" sortable="false"
				headerClass="datatableheader">
				<hdiits:a href="${row.url}&srNo=${row.srNo}" captionid="INT.VIEW" bundle="${intLables}"></hdiits:a>
				/
				<hdiits:a href="hdiits.htm?actionFlag=deleteSavedDraft&srNo=${row.srNo}" captionid="INT.DELETE" bundle="${intLables}"></hdiits:a>
			</display:column>
			<display:setProperty name="export.pdf" value="true" />
		</display:table>
	</div>
	
	
	
	</div>
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
%>






<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page contentType="text/html;charset=UTF-8"%>			
<%try{	%>
	<link rel="stylesheet" type="text/css" href="themes/hdiits/tabcontent.css"/>
	<script ="text/javascript" src="script/common/tabcontent.js"></script>
	<script type="text/javascript"></script>

<form name="frmcsearch" method="post" action="./hdiits.htm" method="post">
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="DocList" value="${resultValue.DocList}" > </c:set>
<%
int i = 1;
%>	 	  
  <display:table list="${DocList}" requestURI="hdiits.htm?actionFlag=getDocs"
   id="row" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />    
	<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><%= i %> </display:column>  	
    <display:column class="tablecelltext" titleKey="Document_Name" sortable="true" headerClass="datatableheader" > <a href="./hdiits.htm?actionFlag=getJobMst&actionName=getJobMst &jobRefId=${row.jobRefId}&lstActPostId=${row.lstActPostId}&editmode=y" > ${row.jobRefId}</a> </display:column>
    <display:column property="fromPost" class="tablecelltext" titleKey="Sent_Post" sortable="true" headerClass="datatableheader" />
    <% i++; %><!--
    <display:footer media="html">
  </display:footer>
  -->
  </display:table>  
	    </form>
<%}catch(Exception e){
	e.printStackTrace();
}%>      
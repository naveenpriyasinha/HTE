<%try{ %>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../core/include.jsp" %>

<script type="text/javascript" src="/script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="/scriptcommon/commonfunctions.js"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="updateFileList" value="${resValue.modList}"> </c:set>

${resValue.fileList}



<%int i=1; %>
<form method="POST" name="testing" action="./hdiits.htm">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">	  

 <div id="tcontent1" class="tabcontent">

     <display:table name="${resValue.modList}" pagesize="12" requestURI=""
  defaultsort="1" defaultorder="ascending" id="row" export="true" style="width:100%">
     


<display:setProperty name="fld1" value="11" ></display:setProperty>
 defaultsort="1" defaultorder="ascending" id="row" export="true" style="width:100%">

<display:column class="tablecelltext" titleKey='SR_NO' sortable="true" headerClass="datatableheader" ><%=i++%></display:column>
<display:column value="${row[0]}" class="tablecelltext" titleKey='MOD_NAME' sortable="true" headerClass="datatableheader" />
<display:column value="${row[1]}" class="tablecelltext" titleKey='CUR_VER' sortable="true" headerClass="datatableheader" />
  <display:setProperty name="export.pdf" value="true" />
</display:table>
<BR>
</div>
		<a href= "./hdiits.htm?viewName=mainopt"><fmt:message key="GO_TO_MAIN_MENU" /> <BR><BR>
  	    <a href= "./hdiits.htm?viewName=modversion">    <fmt:message key="SEARCH_AGAIN" /> <BR>


<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
</div>

<%}
catch(Exception e)
{
	e.printStackTrace();	
}%>	

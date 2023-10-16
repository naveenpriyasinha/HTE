<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>




<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="msg1" value="${resValue.msg}" ></c:set>
<c:set var="ViewList" value="${resValue.ViewList}"></c:set>
<c:set var="listSize" value="${resValue.listSize}"></c:set>  
<c:set var="passList" value="${resValue.passList}"></c:set>
<c:set var="BudSubHeadId" value="${resValue.BudSubHeadId}"></c:set>

<script>
if('${msg}'!=null && '${msg}'!='')
{
alert('${msg}');
}
</script>
<hdiits:form name="scheduler" validate="true" method="POST" action="./hrms.htm?actionFlag=schemeViewListOnTheScreen" encType="text/form-data">
 
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="SH.Scheme" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	
	
	<div class="halftabcontentstyle">	  
<div id="tcontent1" class="halftabcontent">
	  <br><br>
		 <a href= "./hrms.htm?actionFlag=schemeView">  Add new Entry </a>
		 <br> <br>  

<hdiits:hidden  default ="${BudSubHeadId}" name="txtBudSubHeadID" caption="Branch ID"  /> 
<c:if test="${listSize ne 0}">
	 <display:table name="${passList}" requestURI=""    pagesize="${pageSize}"   id="row" defaultorder="ascending" export="true" style="width:100%">
		 <display:column class="tablecelltext" title="Scheme Code" headerClass="datatableheader" style="text-align:center">	
			<a href = "./hrms.htm?actionFlag=updateListOnTheScreen&BudSubHeadId=${row.budSubId}&edit=Y" id="otherLink${row.budSubId}"> ${row.schemeCode}  </a>
		</display:column>	
	<display:column class="tablecelltext" title="Scheme Name" headerClass="datatableheader" value="${row.schemeName}"> </display:column>	
			  <display:column class="tablecelltext" title="Description" headerClass="datatableheader" value="${row.budName}"  > </display:column>				  			
		</display:table>	
		  	 
  	 </c:if>
  	 
	
	<br>
		 <a href= "./hrms.htm?actionFlag=schemeView">  Add new Entry </a>
	
	<script type="text/javascript">
		initializetabcontent("maintab");	 
	</script>

</div>
 
</hdiits:form>
<%
}
  	  catch(Exception e)
  	  {
  		  e.printStackTrace();
  	  }
%>


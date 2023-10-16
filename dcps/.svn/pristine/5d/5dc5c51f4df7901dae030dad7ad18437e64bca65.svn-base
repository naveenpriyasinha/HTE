<%
try{
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hrms/hr/rosterScript/transferEmpSearch.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="empList" value="${resultValue.empList}">
</c:set>
<c:set var="customVOList" value="${resultValue.customVOList}">
</c:set>


<fmt:setBundle basename="resources.search.search" var="comLable"
	scope="request" />

<script>
  	  
  </script>
<script><!--

--></script>


<hdiits:form name="searchEmployee" id="searchEmployee" validate="true"
	method="POST">
	<hdiits:hidden name="hidUserId" id="hidUserId" />
	<br>
	<br>
	<br>
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> Employee
		Search </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0"><br>

	<br>
	<br>
	<br>
	<table align="center" >
	
		
		


	<br>
	<br>
	<br>
	
	<tr>
	<td></td>
	</tr>
	
	
	<c:set var="i" value="0" /> <% int a=0; %>
	
		
		<display:table list="${customVOList}" id="row" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1" >
		
		
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		
		<display:column class="tablecelltext" title=" Select"
			headerClass="datatableheader" style="text-align: center" >
			<hdiits:checkbox id="check${i}" name="check" value="${row.userId}~${row.allocated}"  onclick="checkclick(this);" />
			<hdiits:hidden id="hidCatId${i}" name="hidCatId${i}" default="${row.catId}" />
		</display:column>
		
		<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=a=a+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" title=" Name"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.empName}
			
		</display:column>
		<display:column class="tablecelltext" title=" Date of Joining"
			headerClass="datatableheader" style="text-align: center"
			sortable="true"  >
			${row.joinDate}
			
		</display:column>
		<display:column class="tablecelltext" title=" Category"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.actualCat}
		</display:column>
		<c:set var="i" value="${i+1}" />
		
		<display:footer media="html"></display:footer>		
		
	</display:table>
	
	
	</table>
	</div>


	<div>
	<table align="center">
		<tr>
			<td><br>
			</td>
		</tr>
		<tr align="center">
			<td align="center"><hdiits:button name="Submit" type="button"
				value="Submit" tabindex="11" onclick="submmitReq()" /> <hdiits:resetbutton
				name="Reset" type="button" value="Reset" tabindex="12" /></td>
		</tr>
	</table>


	</div>
	</div>


	<jsp:include page="../common/include/tabnavigation.jsp" />
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

	
</hdiits:form>
<script>
	var id;
	for (var lCntr = 0; lCntr < document.searchEmployee.elements.length; lCntr++)
		{ 
		     if(document.searchEmployee.elements[lCntr].type == "checkbox")  
		     {
		        id=document.searchEmployee.elements[lCntr].id;
		       
		        var tmpVal=document.getElementById(id).value;
		         var chkBoxVal=new Array();
		         chkBoxVal=tmpVal.split("~");
		       
		        if(chkBoxVal[1]=='Y')
		        {
		        	document.getElementById(id).disabled="true";
		        }
		        
		      }
		}
</script>
<%
	}catch(Exception e){
	e.printStackTrace();
}
%>

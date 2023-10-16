
<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%try{	%>

	<script ="text/javascript" src="script/common/tabcontent.js"></script>
	<script type="text/javascript"></script>

<fmt:setBundle basename="resources.WFLables" var="commonLables" scope="request"/>
<style type="text/CSS">


#BOX { display: none; position: absolute; background: #ddd; color: #333; font-size: 1.2em; text-align: center; border: 1px solid #111; top: 70px; z-index: 100; }


</style>
<script language="javascript">
function openDocument(url)
{
try{
	 	docWindow = window.open (url,"Document","location=0,status=0,scrollbars=1");
 		docWindow.resizeTo( screen.availWidth, screen.availHeight );
		docWindow.moveTo(0,0); 
	}
	catch(e)
	{
	} 
}

/*function $(v) { 
alert(" 2");
alert(" dk " + document.getElementById(v));
try
{
	if(document.getElementById(v) != null)
		return(document.getElementById(v)); 
}
catch(e)
{
alert(" 2 " + e);
}
	}*/
	
/*function $S(v) {

try
{
 return($(v).style); 
}
catch(e)
{

}
 }*/
function agent(v) { 

try
{
	return(Math.max(navigator.userAgent.toLowerCase().indexOf(v),0)); 
}catch(e)
{

}
	}
	
function isset(v) {
return((typeof(v)=='undefined' || v.length==0)?false:true);
  }
function XYwin(v) {

var z=agent('msie')?Array(document.body.clientHeight,document.body.clientWidth):Array(window.innerHeight,window.innerWidth); return(isset(v)?z[v]:z);
  }

function TOG() 

{  	

	try
	{	

				document.getElementById('BOX').style.display='none'; 
		document.onclick=function(){}; 
	}
	catch(e)
	{
	}
}

function getMouseXY(e) 
{

try
{
   var posX = 0;
   var posY = 0;
   var e = (!e) ? window.event : e;
   if (e.pageX || e.pageY) {
      posX = e.pageX;
      posY = e.pageY;
   }
   else if (e.clientX || e.clientY) {
      if (document.body.scrollLeft || document.body.scrollTop) {
         posX = e.clientX + document.body.scrollLeft;
         posY = e.clientY + document.body.scrollTop;
      }
      else {
         posX = e.clientX + document.documentElement.scrollLeft;
         posY = e.clientY + document.documentElement.scrollTop;
      }
   }
   document.getElementById("BOX").style.left = posX-300;
   document.getElementById("BOX").style.top = posY;
}
catch(e)
{

}

}

function BOX(v,b) 
{ 

	//setTimeout("TOG()",100); 
	var objToUse = document.getElementById('BOX');
	try
	{
	objToUse.innerHTML=v; 
	objToUse.style.left=Math.round((XYwin(1)-b)/2)+'px'; 
	objToUse.style.width=b+'px'; 
	objToUse.style.display='block'; 
	getMouseXY(document.onmouseover);
	}
	catch(e)
	{
	alert(" d");
	alert(" erro " + e.message);	
	}
	
}




</script>

<hdiits:form name="frmcsearch" validate="true" method="post" action="./ifms.htm">
<hdiits:hidden name="actionFlag" default="getSentDocListForWorkflow" />

<div id="BOX" ></div>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="DocList" value="${resultValue.lstDocs}" > </c:set>
<c:set var="hideMenuLookupID" value="${resultMap.hideMenuLookupID}"></c:set>

<br>
	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
	</jsp:include>
	<jsp:include flush="true" page="../workflow/commonSearch.jsp">
		<jsp:param name="Subjects" value="1"/>
		<jsp:param name="FullName" value="${fullNameString}"/>
		<jsp:param name="allDates" value="${allDate}"/>
		<jsp:param name="subjectFilter" value="${subjectFilter}"/>
		<jsp:param name="recieveDateFrom" value="${recieveDateFrom}"/>
		<jsp:param name="recieveDateTo" value="${recieveDateTo}"/>
		<jsp:param name="finalRecDateFrom" value="${finalRecDateFrom}"/>
	</jsp:include>
<br>

<c:set var="serialNumber" value="${1}" />
  <display:table list="${DocList}" id="row" style="width:100%" pagesize="20" requestURI="" defaultsort="1"  defaultorder="ascending">
  <display:setProperty name="paging.banner.placement" value="bottom" />    
	<c:choose>
		<c:when test="${row.docType eq 'DOC'}">  
			<display:column class="tablecelltext" titleKey="Sr_No" headerClass="datatableheader" sortable="true" value="${serialNumber}" style="text-align: center"></display:column>
			<c:set var="serialNumber" value="${serialNumber+1}" />
		    <display:column class="tablecelltext" titleKey="Document_Name" sortable="true" headerClass="datatableheader"  >${row.docName}</display:column>
 <display:column class="tablecelltext" titleKey="Ref_Id" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><a href= "javascript:openDocument('${row.docUrl}')" >${row.transactionNumber}</a> </display:column> 
		   	<display:column class="tablecelltext" titleKey="Description"  sortable="false" headerClass="datatableheader" >${row.docDesc} </display:column>  	
		   	<fmt:formatDate value="${row.createdDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="stmtDate"/>
		   	<display:column class="tablecelltext" titleKey="WF.RECVDATE"  sortable="false" headerClass="datatableheader" >${stmtDate} </display:column>  	
		   	<display:column class="tablecelltext" titleKey="To_Usr"  sortable="false" headerClass="datatableheader" >${row.toPostId} </display:column>  	
		   	<display:column class="tablecelltext" titleKey="WF.OWNER"  sortable="false" headerClass="datatableheader" >${row.currentOwnerPostId} </display:column>  	
			<display:column class="tablecelltext" titleKey="Job_Status"  sortable="false" headerClass="datatableheader" >${row.jobStatus} </display:column>  	
	    </c:when>
	    <c:otherwise>
			<display:column class="tablecelltext" titleKey="Sr_No" headerClass="datatableheader" sortable="true" value="${serialNumber}" style="text-align: center"></display:column>
			<c:set var="serialNumber" value="${serialNumber+1}" />
		    <display:column class="tablecelltext" titleKey="Document_Name" sortable="true" headerClass="datatableheader"  ><b>${row.docName}</b></display:column>
 			<display:column class="tablecelltext" titleKey="Ref_Id" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><a href= "javascript:openDocument('${row.docUrl}')" >${row.transactionNumber}</a> </display:column> 
		   	<display:column class="tablecelltext" titleKey="Description"  sortable="false" headerClass="datatableheader" ><b>${row.docDesc}</b> </display:column>  	
			<fmt:formatDate value="${row.createdDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="stmtDate"/>
		   	<display:column class="tablecelltext" titleKey="WF.RECVDATE"  sortable="false" headerClass="datatableheader" >${stmtDate} </display:column>  	
		   	<display:column class="tablecelltext" titleKey="To_Usr"  sortable="false" headerClass="datatableheader" ><div onmouseover="BOX('${row.toPostId}','300'); return false;" onmouseout="TOG()"><a href="#" id="pname" >View All Names</a></div></display:column>  	
		   	<display:column class="tablecelltext" titleKey="WF.OWNER"  sortable="false" headerClass="datatableheader" >${row.currentOwnerPostId} </display:column> 
		    <display:column class="tablecelltext" titleKey="Job_Status"  sortable="false" headerClass="datatableheader" ><b>${row.jobStatus}</b> </display:column>  	
		</c:otherwise>
	</c:choose>   
    <!-- <display:footer media="html"> </display:footer>  -->
  </display:table>  
  
  
  <hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'></hdiits:validate>
    
	
	</hdiits:form>

<%}catch(Exception e){
	e.printStackTrace();
}%>
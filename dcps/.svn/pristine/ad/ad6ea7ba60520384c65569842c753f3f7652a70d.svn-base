<%@page import="java.util.List"%>
<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="billHeadMpg" value="${resultValue.billHeadMpg}" > </c:set>
<c:set var="postSearchFlag" value="${resultValue.postSearchFlag}" > </c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_OHPSearch").value;
	
	var url="./hrms.htm?actionFlag=getOrderHeadPostData";
	
	document.OrderHeadPostView.action=url;
	document.OrderHeadPostView.submit();
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_OHPSearch").value;
	if(empId=="")
	{
		alert("Please search the employee first");
		return false;
	}
	else
	{
		return true;
	}
	
}
function submitFormAuto()
{
	if(chkValue())
	{
		document.OrderHeadPostView.submit();
	}
	return true;
}

//Added By Varun
function GetPostfromBill()
{
		 clearPostList();
		
		  	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&billId='+ document.OrderHeadPostView.bill.value;
		  var actionf="GetPostfromBill";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url+"&editFlag=0"; 
		  xmlHttp.onreadystatechange=GetPostsfromBill;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function GetPostsfromBill()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var post = document.getElementById("post");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('post-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text =entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        try
   				        {      				    					
                            post.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       post.add(y); 
   			   	        }	
	                
	                 }
  }
}

function clearPostList()
{

	var v=document.getElementById("post").length;

	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("post").options.length -1;
			document.getElementById("post").options[lgth] = null;
	}		
}


function editData(){

    var ohpId=document.OrderHeadPostView.post.value;
	document.forms[0].action="./hrms.htm?actionFlag=OrderHeadPostMaster&edit=Y&OhpMapId="+ohpId;
	document.forms[0].submit();		

}

//Ended By Varun
</script>
</head>

<%
 List actionList = (List)pageContext.getAttribute("actionList");
 
 int size = actionList.size();

 pageContext.setAttribute("listSize",size);
 
%>

<body>
<hdiits:form method="POST" name="OrderHeadPostView" validate="true" action="./hrms.htm?actionFlag=getOrderHeadPostData">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OHP.OrderHeadPost" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">	  
	<div id="tcontent1" class="halftabcontent">
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" />  </h1> </div> <br>
			  	 <a href=  "./hrms.htm?actionFlag=OrderHeadPostMaster" id="insertOneLink">  Add new Entry </a> 
		
		<c:choose>
		<c:when test="${!postSearchFlag}">
		<table  width="85%" align="center" name="searchTable" id="searchTable">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="OHPSearch"/>
						<jsp:param name="formName" value="OrderHeadPostView"/>
						<jsp:param name="functionName" value="submitFormAuto"/>
					</jsp:include>
			</td>
		</tr>
		<tr>
		<td>
		 <a href="./hrms.htm?actionFlag=getOrderHeadPostData&postSearchFlag=y">Post Search</a>
		 </td>
		 </tr>
		<c:if test="${listSize ge 2}">
		<c:set value="display:none" var="displayStyle"/>
		</c:if>
		<tr style="${displayStyle}">
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="return chkValue()"/>	
				--><hdiits:button type="button" captionid="viewAll" bundle="${commonLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
		</tr>		
	</table>
	</c:when>
	<c:otherwise>
	<table  width="85%" align="center" name="searchTablePost" id="searchTablePost">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/hrms/eis/SearchOrderHeadPost.jsp">
				<jsp:param name="formName" value="OrderHeadPostView"/>
				<jsp:param name="searchAction" value="actionFlag=searchOrderHeadPost"/>
				<jsp:param name="backAction" value="actionFlag=getOrderHeadPostData&postSearchFlag=n"/>
				</jsp:include>
				</td>
				</tr>
				</table>
				</c:otherwise>
				</c:choose>

	
	<table  width="85%" title="Bill No. Wise Search"  border="3" bordercolor="navy" bgcolor="white" align="center" name="searchTable" id="searchTable">
	<!-- Added By Varun For Bill No wise post search Dt.05-08-2008 -->
		
		
<tr><td align="center" colspan="4"><font size="4" face="Times New Roman" color="navy"><b><u> Bill No. Wise Search</u></b></font></td></tr>	   
		<TR>

		<TD  align="left" class="Label" width="15%">
		 <b><hdiits:caption captionid="OHP.BillNo" bundle="${commonLables}"/></b></TD>
    	<TD	width ="25%"><hdiits:select style="width:30%"  name="bill" size="1" sort="false" caption="Bill No." captionid="bill" validation="sel.isrequired" mandatory="true" onchange="GetPostfromBill();" >
	    <hdiits:option value="">----Select----</hdiits:option>
        
		 <c:forEach var="billHeadMpg" items="${billHeadMpg}">
         <hdiits:option value="${billHeadMpg.billHeadId}"><c:out value="${billHeadMpg.billId}"> </c:out></hdiits:option>
         </c:forEach>
		 </hdiits:select > 
	   </TD>
	   
	   <TD align="left" width = "15%">
		  <b><hdiits:caption captionid="admin.PostName" bundle="${commonLables}"/></b></TD>
		<TD  width ="25%"><hdiits:select  style="width:70%"  mandatory="true" sort="true"  captionid="OHP.postname" bundle="${commonLables}" onchange="editData();" validation="sel.isrequired" id="post" name="post" size ="1"  >
			<hdiits:option value="">----Select----</hdiits:option>
        
		
		 </hdiits:select > 
			</TD>
			</TR>
			<%--<tr>
			<td align="center" colspan="4">
					<hdiits:button captionid="eis.edit" bundle="${commonLables}" onclick="editData()" name="cmdedit2" type="button"/>
				</td>
			</tr>--%>
		
		
		<!-- Ended By varun For Bill No wise post search Dt.05-08-2008 -->
	</table>
		
		 
	 <display:table name="${actionList}" requestURI=""   pagesize="${pageSize}" id="row" export="true" style="width:100%">
	 <c:set value="${row.orderHeadPostId}" var="otherIdForLink"/>	
       <display:column value="${row.empFName} ${row.empMName} ${row.empLName}" class="tablecelltext" title="Employee Name"  headerClass="datatableheader" />
		<display:column class="tablecelltext" title="Post Name" headerClass="datatableheader" > 
       <a href="./hrms.htm?actionFlag=OrderHeadPostMaster&OhpMapId=${row.orderHeadPostId}&edit=Y" id="otherLink${row.orderHeadPostId}">${row.post}</a> 	  </display:column>	
		 
	<display:column value="${row.orderName}" class="tablecelltext" title="Sanction Order No "  headerClass="datatableheader" style="text-align:left"/>   
	<display:column value="${row.subHeadDesc}" class="tablecelltext" title="Sub Head Description"  headerClass="datatableheader" />
	  
	  
		  	  <display:setProperty name="export.pdf" value="true" />
  		  </display:table>
  		  
  	 <c:choose>
  		  <%-- <c:when test="${listSize eq 1}">--%>
  		 
  		     <c:when test="${listSize eq 1 && empName != null && not empty empName}">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			 <c:otherwise>	
				  <c:choose>
		              <c:when test="${orderHeadPostId ne 0 && listSize eq 0}">
		                 <script>
			                document.getElementById("insertOneLink").click();
			             </script>
			         </c:when>
		             <c:otherwise>
			             <script>
                              //		document.getElementById("inserLink").click();
		                 </script>
		             </c:otherwise>
		          </c:choose>
			</c:otherwise>
		 </c:choose>
	  	 <br>
	  	 <a href=  "./hrms.htm?actionFlag=OrderHeadPostMaster" id="insertOneLink">  Add new Entry </a> 
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
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
</body>
</html>
<%@ include file="../include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<script type="text/javascript">
<!-- 
function insRow()
{
	var trow=document.getElementById('myTable').insertRow();
	var tdata1=trow.insertCell(0);
    var tdata2=trow.insertCell(1);
    var tdata3=trow.insertCell(2);
    var tdata4=trow.insertCell(3);    
	var temp;
	var val;
	var i;
	var counter = parseInt(document.actiondetails.count.value);
	counter=counter+1;
	
	document.actiondetails.count.value = counter;
	tdata1.innerHTML="Service to Execute("+counter+")";
	
	
	temp = "<select name='txtsrvc1' id='txt2'>";
	
	
	temp1 = document.actiondetails.txt1.length;
	val = document.actiondetails.txt1[0].value;

	for(i=0;i<temp1;i++)
	{
		val = document.actiondetails.txt1[i].value;
		var text1 = document.actiondetails.txt1[i].text;
		temp = temp +"<option value="+val+">"+text1+"</option>";
		
		
    }	
    temp = temp+"</select>";
    tdata2.innerHTML = temp;
    tdata3.innerHTML="Action Desc";
    tdata4.innerHTML="<INPUT type='text' name='actiondesc'>";
    //document.actiondetails.count.value= parseInt(document.actiondetails.count.value) +1;
}    
function delRow()
			{
				var trow=document.getElementById('myTable');
				var t=parseInt(document.actiondetails.count.value);
				if(t-1)
				{
					document.actiondetails.count.value= parseInt(document.actiondetails.count.value) - 1;
					trow.deleteRow(t);
				}
			}
//-->
</script>
<script type="text/javascript">
function validate_required(field,field1,alerttxt)
{
	with (field)
	{if (value==null||value=="")
	  	{	alert(alerttxt);return false  	}
	
	}
	with (field1)
	{if (value==null||value=="")
	  	{	alert(alerttxt);return false  	}
	else {return true}
	}
}
function validate_form(thisform)
{
	with (thisform)
	{
		if (validate_required(actionname,actiondesc,"One or More Textbox(s) Empty")==false)
		{
				actionname.focus();return false
				actiondesc.focus();return false
		}
	}
}
function equl(str1,str2)
{
var ans = NULL
if (str1.equals(str2))
	{
	ans="true"
	}
else
	{
	ans="false"
	}

}
</script>


<% try{ %>

	  		<c:set var="resultObj" value="${result}" > </c:set>		
	  		
	  		
	  		<% String str2 = (String) request.getParameter("editmode"); %>
				<% if (str2!=null && str2.equals("y")) {%> 		
					<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>			
				<c:set var="serviceList" value="${resValue.serviceList}"> </c:set>
					<c:set var="configList" value="${resValue.configurationList}"> </c:set>			
				<%}else{%>
					<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
					<c:set var="serviceList" value="${resValue.serviceList}"> </c:set>	
				<%}%>
				
					      
        <form name="actiondetails" method="POST" action="hdiits.htm" onsubmit="return validate_form(this)" method="post">
         
<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>
<div id="progressImage" style="display:none"></div>

         <div id="tabmenu">
          <ul id="maintab" class="shadetabs">
           <li class="selected" >
            <a href="#" rel="tcontent1">
             Action Configuration
            </a>
           </li>
          </ul>
          </div>  
         <div class="tabcontentstyle" >
          <div id="tcontent1" class="tabcontent">
           <table class="tabtable" id="myTable">
           
    <%int x = 0; %>       
    <% String str1 = (String) request.getParameter("editmode"); %>
	<% if (str1!=null && str1.equals("y")) { %>
	 <tr>
            <td>Action Name</td>
            <td><input type="text" name="actionname" readonly="true" value=<%=request.getParameter("actname")%>></td>     
			<TD width="20%">
			    <INPUT type=button value=Add onClick=insRow()>
			    <INPUT type=button value="Delete" onClick=delRow()>
			</TD>	
			<td><input type=button value="Save Data" onClick="insertdt()"></td>
	</tr>
    <c:forEach var="cnfg" items="${configList}">
		<tr>
		<% x = x+1; %>
             <td>Service to Execute</td>
             <td>
             <%if (x==1) {%>
	            <select name="txtsrvc1" id="txt1">
	          <% }else{%>
	            <select name="txtsrvc1" id="txt2">
	          <% }%>
    	        	<option value="ss">Select Service</option>
	       		    <c:forEach var="srvc" items="${serviceList}">
						<c:if test="${cnfg.frmServiceMst.serviceName eq (srvc.serviceName)}">
							<option value='<c:out value="${srvc.serviceId}"/>' selected="true">
							<c:out value="${srvc.serviceName}"/></option>
						</c:if>	
						<c:if test="${cnfg.frmServiceMst.serviceName ne (srvc.serviceName)}">
							<option value='<c:out value="${srvc.serviceId}"/>'>
							<c:out value="${srvc.serviceName}"/></option>
						</c:if>	
					</c:forEach>
			
	            </select>
	       
            </td>
            <td>Action Desc</td>
           
            <td><input type="text" name="actiondesc" id="0" value='<c:out value="${cnfg.actionDesc}"/>'></td>
            <td align="left" class="tabnavtd" id="tabnavtd"></td>
       </tr>
       

		<input type="hidden" name="actionid" value='<c:out value="${cnfg.actionId}"/>'>
		<input type="hidden" name="createddate" value='<c:out value="${cnfg.createdDate}"/>'>
		<input type="hidden" name="createdempid" value='<c:out value="${cnfg.createdEmpId}"/>'>

	</c:forEach>
	
	<input type="hidden" value=<%=x%> name="count">
	<%}else{ %>
	 <tr>
             <td>Action Name</td>
             <td><input type="text" name="actionname" value=<%=StringUtility.getParameter("actname",request)%>></td>     
             <TD width="20%">
    <INPUT type=button value=Add onClick=insRow()>
    <INPUT type=button value="Delete" onClick=delRow()>

   </TD>	
                    
           </tr>
           <%int y = 0; %>    
		 
		<tr>
		<% y = y+1; %>
             <td>Service to Execute</td>
             <td>
             <%if (y==1) {%>
	            <select name="txtsrvc1" id="txt1">
	          <% }else{%>
	            <select name="txtsrvc1" id="txt2">
	          <% }%>
    	        	<option value="ss">Select Service</option>
	       		    <c:forEach var="srvc" items="${serviceList}">
						<c:if test="${cnfg.frmServiceMst.serviceName eq (srvc.serviceName)}">
							<option value='<c:out value="${srvc.serviceId}"/>' selected="true">
							<c:out value="${srvc.serviceName}"/></option>
						</c:if>	
						<c:if test="${cnfg.frmServiceMst.serviceName ne (srvc.serviceName)}">
							<option value='<c:out value="${srvc.serviceId}"/>'>
							<c:out value="${srvc.serviceName}"/></option>
						</c:if>	
					</c:forEach>
			
	            </select>
	       
            </td>
            <td>Action Desc</td>
            <td><input type="text" name="actiondesc" id="0" value='<%=StringUtility.getParameter("actdesc",request)%>'></td>
              <td align="left" class="tabnavtd" id="tabnavtd"></td>
       </tr>
       <tr>
        <input type="hidden" value="1" name="count">
	<%}%>
	<td>Service Status</td>
 <% String actionstatus=StringUtility.getParameter("actstatus",request);%>
 <%if(actionstatus.equals("ACTV")){%>
 <td><input type="checkbox" name="actionstatus" checked="checked" value="ACTV" >Active</td>
 <%}else{%>
 <td><input type="checkbox" name="actionstatus"  value="ACTV">Active</td>
 <%}%>
 </tr>    
  
     
           
           
       </table>
            <% String str = (String) request.getParameter("editmode"); %>
				<% if (str!=null && str.equals("y")) { %> 
					<input type="hidden" name="actionFlag" value="editActionConfigDetails"> 
				<%}else{%>
					<input type="hidden" name="actionFlag" value="addActionDetails" > 		
				<%}%>            
            
            
             
           
         </div>
         <script type="text/javascript">
         	navDisplay = false;
         </script>
              <jsp:include page="../tabnavigation.jsp"/>     
         </div>
         <script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
        </form>
          <% }catch(Exception e)
  {
	 e.printStackTrace();
  }%>
     

<html>
 
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../core/include.jsp" %>

<script type="text/javascript" src="common/script/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/common/script/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="/common/script/ajax_sample.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>		
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
<c:set var="DocList" value="${resValue.DocList}"> </c:set>		  		
<c:set var="PostList" value="${resValue.PostList}"> </c:set>		  	
<script type="text/javascript">
	function createJob()
	{	
		document.getElementById('actionId').value = 1;	
	}
	
	function forward()
	{
		
		alert("File Has been Forwarded");
		document.getElementById('actionId').value = 2;	
		
		
	}
	
	function return_()
	{
		document.getElementById('actionId').value = 3;	
	}
	
	function split()
	{
		document.getElementById('actionId').value = 4;	
	}
	function TransferRole() 
			{
				var RoleEntries=document.getElementById("RoleEntries");
				for(var i=0;i<RoleEntries.length;i++)
				{
					
					if(RoleEntries.options[i].selected)
					{
						var option1=document.createElement('option');
						
						//y.text=x.options[i].text;
						option1=RoleEntries.options[i];
						//y.value=x.value;
						RoleEntries.remove(i);
						var MarkedRole=document.getElementById("MarkedRole");
						try
	   					{
			    			MarkedRole.add(option1,null); // standards compliant
	   					}
				 		catch(ex)
	   					{
			   				MarkedRole.add(option1); // IE only
	   					}
						i=i-1;
					}
				}
			}
			function ReTransferRole() 
			{
				var RoleEntries=document.getElementById("MarkedRole");
				for(var i=0;i<RoleEntries.length;i++)
				{
					
					if(RoleEntries.options[i].selected)
					{
						var option1=document.createElement('option');
						
						//y.text=x.options[i].text;
						option1=RoleEntries.options[i];
						//y.value=x.value;
						RoleEntries.remove(i);
						var MarkedRole=document.getElementById("RoleEntries");
						try
	   					{
			    			MarkedRole.add(option1,null); // standards compliant
	   					}
				 		catch(ex)
	   					{
			   				MarkedRole.add(option1); // IE only
	   					}
						i=i-1;
					}
				}
			}
			function showstuff(){
				
				document.getElementById('RoleDiv').style.visibility="visible";
				//document.getElementById('nolimit').style.display='';
			}
			function hidestuff(){
				document.getElementById('RoleDiv').style.visibility="hidden";
   			}
</script>

<form method="POST"  action="./hdiits.htm" onsubmit="return validate_form(this)" method="post">
	<input type="hidden" name="actionFlag" value="wfAction">
	<input type="hidden" name="actionId" value="1111">
	<input type="hidden" name="toLevelId" value="">
	<div>
	<% String lstreditMode = (String) request.getParameter("editmode"); %>
	<% if (lstreditMode!=null && lstreditMode.equals("y")) { %> 
	<c:forEach var="Doc" items="${DocList}">
	<table>
		<tr>
			<td>Fir ID:</td>
			<td><input type="text" name="FirId" value="${Doc.firId}"></td>
		</tr>
		<tr>
			<td>Fir Name:</td>
			<td><input type="text" name="FirName" value="${Doc.firName}"></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><input type="text" name="FirDesc" value="${Doc.firDesc}"></td>	
		</tr>
	</table>
	</c:forEach>
	<pre>
MARKED FILE    :<input type="radio" name="hierarchyFlag" value="1" checked="true" onclick="hidestuff()"/>NO<input type="radio" name="hierarchyFlag" value="2" onclick="showstuff()"/>YES<input type="radio" name="hierarchyFlag" value="3" onclick="showstuff()"/>ToPost
SPLIT FILE     :<input type="radio" name="SplitFlag" value="3" checked="true" onclick="hidestuff()"/>No<input type="radio" name="SplitFlag" value="4" onclick="showstuff()"/>YES
ALTERNATE FLOW :<input type="radio" name="AlternateFlow" value="1" checked="true"/>No<input type="radio" name="AlternateFlow" value="2"/>YES
	</pre>
	<br>
	<pre>
	<div id="RoleDiv" style=visibility:hidden>
	Available Roles				Marked Roles
	<table border="2">	   
		<tr> 
    	    <td>
    	    	<SELECT id="RoleEntries" NAME="RoleEntries" SIZE="5" MULTIPLE="MULTIPLE" style="width:250px;">
	    	  		<c:forEach var="Post" items="${PostList}">
	    	   	    <option  id='<c:out value="${Post.roleId}"/>' value='<c:out value="${Post.roleId},${Post.levelId}"/>' >
	    	   	    <c:out value="${Post.roleId}"/>	    <c:out value="${Post.levelId}"/>
					</option><br>
 	   		  		</c:forEach>
	    	  	</SELECT>
	        </td>
	        <td>
    		  	<img src="images/next.gif" alt="Next" onclick="TransferRole()"/><br><br>
    		  	<img src="images/previous.gif" alt="Next" onclick="ReTransferRole()"/>
    	    </td>		   
    	    <td>
				<select id="MarkedRole" name="MarkedRole" SIZE="5" MULTIPLE="MULTIPLE" style="width:250px;"></select>
    	  	</td>
 		</tr>
    </table>
    </pre>
    </div>
	<%} else {%>
	<table>
		<tr>
			<td>Fir ID:</td>
			<td><input type="text" name="FirId"></td>
		</tr>
		<tr>
			<td>Fir Name:</td>
			<td><input type="text" name="FirName"></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><input type="text" name="FirDesc"></td>	
		</tr>
		</table>

	<%} %>
	<table>
		 <tr>
		 
			<% if (lstreditMode!=null && lstreditMode.equals("y")) { %> 
				<td><input type="submit" value="Forward" onClick="forward()"></td>		 				
				<td><input type="submit" value="Return" onClick="return_()"></td>
				<td><input type="submit" value="Split" onClick="split()" ></td>
				<td><input type="submit" value="ToPost" onClick="toPost()"></td>
				
			<%}else{%>
				<td><input type="submit" value="Create Job" onClick="createJob()"> </td>
			<%}%>
		</tr>
	</table>
	<%out.println("</div>"); %>
<jsp:include page="../common/include/tabnavigation.jsp?"/>
</form>
</html>
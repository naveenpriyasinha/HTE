<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<fmt:setBundle basename="resources.acl.acl" var="AccessControlLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="postList" value="${resValue.postList}"></c:set>
<c:set var="roleList" value="${resValue.roleList}"></c:set>



<hdiits:form name="form1" method="post" validate="true" action="./hdiits.htm?actionFlag=showPostRoleReport">     
<body> 
	<div id="tabmenu">
	        	<ul id="maintab" class="shadetabs">
	        		<li class="selected"><a href="#" rel="tcontent1">
	           		<hdiits:caption captionid="Post_Role_Report_Title" bundle="${AccessControlLabels}"></hdiits:caption></a></li>   
	           	</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent">   
	
		<table  align="center">
		<tr>
			<td>
				<b><hdiits:caption captionid="Post_Name" bundle="${AccessControlLabels}" /></b>
		     </td>
			 <td class="fieldLabel" >
			    	
			    <hdiits:select captionid="Post_Name" bundle="${AccessControlLabels}" name="postCombo" sort="false" >
						<hdiits:option value="1">All</hdiits:option>		
						<c:forEach var="post" items="${postList}">
							<option value='<c:out value="${post.postId}"/>' >
								<c:out value="${post.postName}"/>
				    	</c:forEach>
				 </hdiits:select> 
			    	
			 </td>
				
		<tr>
		
		<tr>
			<td>
				<b><hdiits:caption captionid="Role_Name" bundle="${AccessControlLabels}" /></b>
		     </td>
			 <td class="fieldLabel" >
			    	
			    <hdiits:select captionid="Role_Name" bundle="${AccessControlLabels}" name="roleCombo" sort="false" >
						<hdiits:option value="1">All</hdiits:option>		
						<c:forEach var="mstRole" items="${roleList}">
							<option value='<c:out value="${mstRole.roleId}"/>' >
								<c:out value="${mstRole.roleName}"/>
				    	</c:forEach>
				 </hdiits:select> 
			    	
			 </td>
				
		<tr>
		
		<tr>
			
			 <td class="fieldLabel" >
			    	
			    <hdiits:submitbutton name="submit" type="button" value="Generate Report"/>
			    	
			 </td>
				
		
		</tr>
		</table>
	
	
	</div>
	</div>
</body>
<script type="text/javascript">initializetabcontent("maintab")</script>
</hdiits:form>
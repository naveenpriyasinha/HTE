<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<fmt:setBundle basename="resources.acl.acl" var="AccessControlLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="userList" value="${resValue.userList}"></c:set>
<c:set var="roleList" value="${resValue.roleList}"></c:set>

<script type="text/javascript" src='<c:url value="/script/login/validation.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/commonfunctions.js"/>'></script>

<hdiits:form name="form1" validate="true" method="post" action="./hdiits.htm?actionFlag=showUserRoleReport"> 
<body>
	<div id="tabmenu">
	        	<ul id="maintab" class="shadetabs">
	        		<li class="selected"><a href="#" rel="tcontent1">
	           		<hdiits:caption captionid="User_Role_Report_Title" bundle="${AccessControlLabels}"></hdiits:caption></a></li>   
	           	</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent">           
	
		<table  align="center">
		<tr>
			<td>
				<b><hdiits:caption captionid="User_name" bundle="${AccessControlLabels}" /></b>
		     </td>
			 <td class="fieldLabel" >
			    	
			   
			    	       <table id="userTableId" style="display:none"><hdiits:text name="UserName" captionid="User_name" bundle="${AccessControlLabels}" />
			    	       <hdiits:hidden name="userId" /></table>
	    	</td>    
	       	<td>
	             			<font color="Blue"><a href="./hdiits.htm?actionFlag=searchUser&searchCombo=All&delete=false" target="_blank">Get Users</a></font>
	      	</td>
				
		<tr>
		
		<tr>
			<td>
				<b><hdiits:caption captionid="Role_Name" bundle="${AccessControlLabels}" /></b>
		     </td>
			 	<td>
	               	    	<table id="roleTableId" style="display:none"><hdiits:text name="roleName" captionid="Role_Name" bundle="${AccessControlLabels}" />
	               	    	  <hdiits:hidden name="roleId" /></table>
           
	             </td>  
	             <td> 		
	             		<font color="Blue"><a href="./hdiits.htm?actionFlag=searchRole&searchCombo=All&delete=false" target="_blank">Get Roles</a></font>
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
<script type="text/javascript">initializetabcontent("maintab")</script>
<hdiits:validate locale="en_US" />
</body>
</hdiits:form>
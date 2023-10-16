<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
	
<fmt:setBundle basename="resources.acl.acl" var="AccessControlLabels" scope="request"/>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="roleList" value="${resValue.roleList}"></c:set>
<c:set var="elementTypeComboValues" value="${resValue.ElementTypeComboValues}"></c:set>
<c:set var="languageComboValues" value="${resValue.LanguageComboValues}"></c:set>
<c:set var="permissionComboValues" value="${resValue.PermissionComboValues}"></c:set>


<body> 
<hdiits:form name="form1" validate="true" method="post" action="./hdiits.htm" > 
<body>
<div id="tabmenu">
        	<ul id="maintab" class="shadetabs">
        		<li class="selected"><a href="#" rel="tcontent1">
           		<hdiits:caption captionid="Role_Elmnt_Rpt_Title" bundle="${AccessControlLabels}"></hdiits:caption></a></li>   
           	</ul>
        </div>
        <div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent">


<table id="table_main" align="left">
	
	<tr>
		<td>
            <a title='<fmt:message bundle="${AccessControlLabels}" key="Role_Name"/>'>             
            <hdiits:caption captionid="Role_Name" bundle="${AccessControlLabels}" /></a> 
	     </td>
		 <td class="fieldLabel" >
			<hdiits:select captionid="Role_Name" bundle="${AccessControlLabels}"  name="roleId" sort="false" >
						
						<c:forEach var="role" items="${roleList}">
							<option value='<c:out value="${role.roleId}"/>' >
								<c:out value="${role.roleName}"/>
				    	</c:forEach>
				    	<hdiits:option value="0">All</hdiits:option>				
			</hdiits:select> 
		</td>
	</tr>
	<tr>
		<td>
            <a title='<fmt:message bundle="${AccessControlLabels}" key="Element_Type"/>'>             
            <hdiits:caption captionid="Element_Type" bundle="${AccessControlLabels}" /></a> 
	     </td>
		<td class="fieldLabel" >
			<hdiits:select captionid="Element_Type" bundle="${AccessControlLabels}"  name="elementType" sort="false">		
						<c:forEach var="elementTypeComboValue" items="${elementTypeComboValues}">
							<option value='<c:out value="${elementTypeComboValue.lookupName}"/>' >
								<c:out value="${elementTypeComboValue.lookupName}"/>
				    	</c:forEach>				
			</hdiits:select> 
		</td>
	</tr>
	
	<tr>
		<td>
            <a title='<fmt:message bundle="${AccessControlLabels}" key="AcType_Permissions"/>'>             
            <hdiits:caption captionid="AcType_Permissions" bundle="${AccessControlLabels}" /></a> 
	     </td>
		<td class="fieldLabel">
			<hdiits:select captionid="AcType_Permissions" bundle="${AccessControlLabels}"  name="permission" sort="false">		
						<hdiits:option value="0">All</hdiits:option>		
						<c:forEach var="comboValues" items="${permissionComboValues}">
							<option value='<c:out value="${comboValues.lookupShortName}"/>' >
								<c:out value="${comboValues.lookupName}"/>
				    	</c:forEach>	
				    		
			</hdiits:select> 
		</td>
	</tr>
	
	<tr>
		<td>
            <a title='<fmt:message bundle="${AccessControlLabels}" key="Lang"/>'>             
            <hdiits:caption captionid="Lang" bundle="${AccessControlLabels}" /></a> 
	     </td>
		 <td class="fieldLabel">
		 	<hdiits:select captionid="Lang" bundle="${AccessControlLabels}"  name="langId" sort="true" >	
		 		
						<c:forEach var="comboValues" items="${languageComboValues}">
							<option value='<c:out value="${comboValues.langId}"/>' >
								<c:out value="${comboValues.langName}"/>
				    	</c:forEach>	
				    				
			</hdiits:select> 
		</td>
	</tr>

	<tr>
		<td></td>
		<td>
		<hdiits:hidden name="actionFlag" default="getRoleElementReport"/>
			<hdiits:submitbutton type="button"   name="submit" value="Generate Report"/>
			
		</td>
	</tr>

</table>

<br>
<br>
<br>

</div>
</div>
        
<input type="hidden" name="actionType" value="getElements">


<script type="text/javascript">initializetabcontent("maintab")</script>
<hdiits:validate locale="en_US" />
</body>
</hdiits:form>

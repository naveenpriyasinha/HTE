<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
	
<fmt:setBundle basename="resources.acl.acl" var="AccessControlLabels" scope="request"/>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="userList" value="${resValue.userList}"></c:set>
<c:set var="elementTypeComboValues" value="${resValue.ElementTypeComboValues}"></c:set>
<c:set var="languageComboValues" value="${resValue.LanguageComboValues}"></c:set>
<c:set var="permissionComboValues" value="${resValue.PermissionComboValues}"></c:set>




<body>
<hdiits:form name="form1" validate="true"  action="./hdiits.htm" method="post">
 <div id="tabmenu">
        	<ul id="maintab" class="shadetabs">
        		<li class="selected"><a href="#" rel="tcontent1">
           		<hdiits:caption captionid="User_Elmnt_Rpt_Title" bundle="${AccessControlLabels}"></hdiits:caption></a></li>   
           	</ul>
        </div>
        
        <div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent"> 
 <table id="table_main" align="left">  

<tr>
		<td>
            <a title='<fmt:message bundle="${AccessControlLabels}" key="Role_Name"/>'>             
            <hdiits:caption captionid="User_name" bundle="${AccessControlLabels}" /></a> 
	     </td>
		 <td class="fieldLabel" >
			<hdiits:select captionid="User_name" bundle="${AccessControlLabels}"  name="userId" sort="false">
					
						<c:forEach var="user" items="${userList}">
							<option value='<c:out value="${user.userId}"/>' >
								<c:out value="${user.userName}"/>
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
			<hdiits:select captionid="Element_Type" bundle="${AccessControlLabels}"  name="elementType" sort="false" >		
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
			<hdiits:select captionid="AcType_Permissions" bundle="${AccessControlLabels}"  name="permission" sort="false" >	
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
		 	<hdiits:select captionid="Lang" bundle="${AccessControlLabels}"  name="langId" sort="false">		
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
		
		<hdiits:hidden name="actionFlag" default="showUserElementReport"/>
			<hdiits:submitbutton type="button"   name="submit" value="Generate Report"/>
			
		</td>
	</tr>

</table>

<br>
<br>
<br>

</div>
        </div>
        <script type="text/javascript">initializetabcontent("maintab")</script>

<input type="hidden" name="actionType" value="getElements">


</hdiits:form>
</body>

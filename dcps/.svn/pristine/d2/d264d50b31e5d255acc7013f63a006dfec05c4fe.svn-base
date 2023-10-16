<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="resources.acl.acl" var="accessControlLables" scope="request"/>
		<c:set var="resultObj" value="${result}" > </c:set>	
		<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
		<c:set var="comboValues" value="${resultValue.comboValues}" > </c:set>

	<caption>
  	<font color="blue">
	</font>
	</caption>   
    <hdiits:form name="frmcsearch" validate="true" action="./hdiits.htm" >
   
    	<div id="tabmenu">
        	<ul id="maintab" class="shadetabs">
        		<li class="selected"><a href="#" rel="tcontent1">
           		<hdiits:caption captionid="RoleSearch" bundle="${accessControlLables}"></hdiits:caption></a></li>   
           	</ul>
        </div>
        <div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent">
		
			<table cellSpacing=1 cellPadding=5  width="55%" bgColor=#ffffff align="center">
			<tr>
    			<td class="fieldLabel">
    	
    			<b><hdiits:caption captionid="SearchBy" bundle="${accessControlLables}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			
	    	
	    				<hdiits:select captionid="SearchBy" bundle="${accessControlLables}" name="searchCombo" sort="false" onchange="displayTextBox()">
					 		<hdiits:option value="0">[Select]</hdiits:option>
					 		<c:forEach var="comboValues" items="${comboValues}">
								<option value='<c:out value="${comboValues.lookupShortName}"/>' selected="true">
								<c:out value="${comboValues.lookupName}"/>
						    </c:forEach>
		    			</hdiits:select> 
	    	
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			<table id="textBoxId" style="display:none">
		    			
		    				<td class="fieldLabel" >
		    				<b>	<hdiits:caption captionid="SearchValue" bundle="${accessControlLables}" /></b>&nbsp;&nbsp;
		    				
		    					<hdiits:text name="SearchValue" caption="SearchValue" size="30" maxlength="100" /> 
		    				</td>
		    				
		    		</table>
		    	</td>
		    </tr>
		    <tr>
	    		
	    		<td class="fieldLabel" align="center">
	    		
	    		   <hdiits:hidden name="actionFlag" default="searchUser" /> 	
	    		   <hdiits:hidden name="delete" default="true" /> 	
		    		<hdiits:submitbutton  name="Search" type="button" value="Search" onclick="return validate_form()"/>
		    	</td>
	    		
			</tr>
	
			</table>
			
	
		</div>
        </div>
        <script type="text/javascript">initializetabcontent("maintab")</script>
        <script language="javascript">
        function displayTextBox()
        {
        	var comboValue=document.frmcsearch.searchCombo.value;
        	
        	if(comboValue!=0 && comboValue!='All')
        	{
        		document.getElementById("textBoxId").style.display='';
        	} 
        	else
        	{
        		document.getElementById("textBoxId").style.display='none';
        	}
        	
        }
        
        function validate_form()
        {
        	var comboValue=document.frmcsearch.searchCombo.value;
        	if(comboValue==0)
        	{
        		
        		alert('<fmt:message bundle="${accessControlLables}" key="SelectOptionMsg"/>');
        		document.frmcsearch.searchCombo.focus();
        		return false;
        	}
        	else 
        	{
	        	 if(comboValue!='All' )
	        	 {
		        	 	if(document.frmcsearch.SearchValue.value=='')
		        	 	{
		        	 		alert('<fmt:message bundle="${accessControlLables}" key="SelectSearchValue"/>');
		        	 		document.frmcsearch.SearchValue.focus();
		        	 		return false;
		        	 	}

	        	 }
        	}
        }
        </script>
                <hdiits:validate locale="en_US" />
		</hdiits:form>
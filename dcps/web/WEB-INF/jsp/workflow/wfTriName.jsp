
<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables"	scope="request" />
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="wfTrilist"	value="${resultMap.wfTrilist}"></c:set> 
<c:set var="ModuleMstList"	value="${resultMap.ModuleMstList}"></c:set> 

<hdiits:form name="frmTriname" id="frmTriname" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="editTriCode" />
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.TRICODEDETAILS" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>
<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">

	<center><b><font size="2" face="arial"><fmt:message key="WF.TRINAMEHEADING"  bundle="${wfLables}"/></font></b></center>
	<br><br><br>
	<table align="center" id="table1" border="0"> 		
	<tr>
		<td style="border:none"><hdiits:caption captionid="WF.Module" bundle="${wfLables}" /></td>		
		<td align="left" style="border:none">
				<hdiits:select name="selModule" id="selModule" captionid="WF.Module" validation="sel.isrequired" bundle="${wfLables}" mandatory="true" onchange="getDocAjaxFunc()">
					<option value="0">Select</option>
					<c:forEach  items="${ModuleMstList}" var="wflookup">
							<option value='<c:out value="${wflookup.moduleId}"/>' selected="true">
								<c:out value="${wflookup.moduleName}" />
							</option>								
					</c:forEach>
				</hdiits:select>			
		 </td>	
	</tr>
	<tr>
		<td style="border:none"><hdiits:caption captionid="WF.DOCNAME" bundle="${wfLables}" /></td>		
		<td align="left" style="border:none">
				<hdiits:select name="selTriName" id="selTriName" captionid="WF.DOCNAME" validation="sel.isrequired" bundle="${wfLables}" mandatory="true" onchange="ajaxFunction()">
					<option value="0">Select</option>					
				</hdiits:select>			
		 </td>	
	</tr>	
	<tr>
		 <td style="border:none"><hdiits:caption captionid="WF.TRINAME" bundle="${wfLables}" /></td>	
		 <td align="left" style="border:none">
		     <hdiits:text name="txtTriName" captionid="WF.TRINAME" validation="txt.isname" bundle="${wfLables}" maxlength="3"/>
		 </td>		 
	</tr>	
	</table>
</div>
<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script language="javascript">
</script>	
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script type="text/javascript">
function deleteComboValues()
{	
	   var UserEntries=document.getElementById("selTriName");
	   for(var i=1;i<UserEntries.length;i++)
	   {
				UserEntries.remove(i);
				i = i - 1;
	   }
}
function getDocAjaxFunc()
{
		try
    	{   
    	    // Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{   
			// Internet Explorer    
			try
      		{      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
      		}
		    catch (e)
		    {
		          try
        		  {
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}
    	
     	var url = "${contextPath}/hdiits.htm?actionFlag=loadSubTricCode&moduleId="+document.forms[0].selModule.value; 
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{					
					deleteComboValues();
					var text;
	            	var z=document.getElementById("selTriName");								
					var XMLDoc=xmlHttp.responseXML.documentElement;																				
					var tableentries = XMLDoc.getElementsByTagName('IdMapped');	
					for ( var i = 0 ; i < tableentries.length ; i++ )
   					{	
   						  text=tableentries[i].childNodes[0].text;   
     				      value=tableentries[i].childNodes[1].text;			     				   
     				      value = value.replace(/andand/i,'&');			     				   
     				      var y=document.createElement('option');			     				   		 					
						  y.value=text;						
						  y.text=value;														
						  try
	   					  {
	    					z.add(y,null); 			    			
	   					  }
	 					  catch(ex)
	   					  {			   			 
	   			 			z.add(y); 
	   					  }		
					}           	  
				}				
			}
	    }					
   			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
			xmlHttp.send(encodeURIComponent(null));
			return true;
}

function ajaxFunction()
{
		try
    	{   
    	    // Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{   
			// Internet Explorer    
			try
      		{      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
      		}
		    catch (e)
		    {
		          try
        		  {
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}
    	
     	var url = "${contextPath}/hdiits.htm?actionFlag=viewTriName&docId="+document.forms[0].selTriName.value; 
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{					
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var tableentries = XMLDoc.getElementsByTagName('docIdMapped');		
					//alert("XMLDoc.childNodes.length::"+XMLDoc.childNodes.length);	
					//alert("tableentries.length::"+tableentries.length);		
					for ( var i = 0 ; i < tableentries.length ; i++ )
   					{	   		
   						docTriName = tableentries[i].childNodes[0].text;   						
   						document.forms[0].txtTriName.value=docTriName;   						
					}  
				}				
			}
	    }					
   			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
			xmlHttp.send(encodeURIComponent(null));
			return true;
}
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


<%
try {
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>

<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables"  scope="request" />
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="wfDocList"	value="${resultMap.wfDocList}"></c:set> 

<hdiits:form name="frmWfSubj" id="frmWfSubj" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="editDocDetails" />
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.HEADING" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>

<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">	
		
	<table align="center" border="1" class="tabtable">	
		<tr>
			<td align="center" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.DocId" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">
				<hdiits:select name="selDocId" id="selDocId" captionid="WF.DocId" validation="sel.isrequired" bundle="${wfLables}" mandatory="true" onchange="ajaxFunction()" style="width:150%">
					<option value="0">Select</option>
					<c:forEach  items="${wfDocList}" var="wflookup">
							<option value='<c:out value="${wflookup.docId}"/>' selected="true">
								<c:out value="${wflookup.docName}" />
							</option>								
					</c:forEach>
				</hdiits:select>			
		 	</td>	
		</tr>	
		<tr>
			<td align="center" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.DOCNAME" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">
		    	<hdiits:text name="txtDocName" captionid="WF.DOCNAME" validation="txt.isname" bundle="${wfLables}"/>
		    </td>			
			<td align="left" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.DESC" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">
		     	<hdiits:text name="txtDesc" captionid="WF.DESC" validation="txt.isname" bundle="${wfLables}"/>
			</td>	
		</tr>
		<tr>
			<td align="center" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.TRINAME" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">
		    	<hdiits:text name="txtTriname" captionid="WF.TRINAME" bundle="${wfLables}"/>
			</td>						
		</tr>
		
		
		<tr></tr>
		<tr></tr>		
		<tr>
			<td align="center" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.HIERARCHYTYPE" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">		    	
		    	<hdiits:select name="selHierarchyType"  id="selHierarchyType">
					<hdiits:option value="Select" selected="true"> Select </hdiits:option>
					<hdiits:option value="1"> Post Based </hdiits:option>
					<hdiits:option value="2"> Role Based </hdiits:option>
					<hdiits:option value="3"> Marked </hdiits:option>
				</hdiits:select>		    	
		    </td>		
		    			
			<td align="left" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.SUBJTYPE" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">		     	
		     	<hdiits:select  name="selSubjType" id="selSubjType"  >
					<hdiits:option value="Select" selected="true"> Select </hdiits:option>
					<hdiits:option value="1"> Common </hdiits:option>
					<hdiits:option value="2"> Subject Specific </hdiits:option>
					<hdiits:option value="3"> Branch Specific </hdiits:option>
					</hdiits:select>
			</td>	
		</tr>			
		<tr>
			<td align="center" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.SELFINITIATLIZED" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">		    	
		    	<hdiits:select  id="selSelfInit"  name="selSelfInit">
					<hdiits:option value="Select" selected="true"> Select </hdiits:option>
					<hdiits:option value="0"> No </hdiits:option>
					<hdiits:option value="1"> Yes </hdiits:option>
				</hdiits:select>
			</td>	
			<td align="left" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.RESTRICTED" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">		    			    	
				<hdiits:select name="selRestricted" id="selRestricted"  >
					<hdiits:option value="Select" selected="true"> Select </hdiits:option>
					<hdiits:option value="N"> No </hdiits:option>
					<hdiits:option value="Y"> Yes </hdiits:option>
				</hdiits:select>				
			</td>
		</tr>	
	</table>	
	</div>
<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<script type="text/javascript">
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
    	
     	var url = "${contextPath}/hdiits.htm?actionFlag=viewDocDetails&docId="+document.forms[0].selDocId.value; 
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{					
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var tableentries = XMLDoc.getElementsByTagName('docIdMapped');	
					for ( var i = 0 ; i < tableentries.length ; i++ )
   					{	   		
   						docName	= tableentries[i].childNodes[0].text; 
   						docDesc = tableentries[i].childNodes[1].text;  
   						docTriName = tableentries[i].childNodes[2].text; 
   						SelfInit = tableentries[i].childNodes[3].text;   						   
   						Restricted = tableentries[i].childNodes[4].text;   						 
   						hirType = tableentries[i].childNodes[5].text; 
   						subjType = tableentries[i].childNodes[6].text;  
   						
   						document.forms[0].txtDocName.value=docName; 
   						document.forms[0].txtDesc.value=docDesc;
   						document.forms[0].txtTriname.value=docTriName;   
   													   					
   						document.forms[0].selSelfInit.value=SelfInit;  
   						document.forms[0].selRestricted.value=Restricted;  
   						document.forms[0].selHierarchyType.value=hirType;
   						document.forms[0].selSubjType.value=subjType;     						
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

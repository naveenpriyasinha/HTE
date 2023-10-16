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
<fmt:message  key="WF.BRANCHHEADING" bundle="${wfLables}" var="heading"/>
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="wfLoclist"	value="${resultMap.wfLoclist}"></c:set> 
<c:set var="wfBranchlist"	value="${resultMap.wfBranchlist}"></c:set> 
<c:set var="wfDoclist"	value="${resultMap.wfDoclist}"></c:set>
<c:set value="${resultMap.msg}" var="msg"></c:set>
<hdiits:form name="frmBranch" id="frmBranch" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="insertBranchSubjDetails" />
<hdiits:hidden name="hiddenflag"/>
<hdiits:hidden name="hiddensrNo" default="0"/>
<hdiits:hidden name="hiddenText" default="0"/>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.BRANCHDETAILS" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>
<div class="tabcontentstyle">
<!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">		
	<center><b><font size="2" face="arial"><fmt:message key="WF.BRANCHHEADING"  bundle="${wfLables}"/></font></b></center>
	<br><br><br>
	<jsp:include page="/WEB-INF/jsp/common/SearchLocation.jsp">
		<jsp:param name="SearchLocation" value="branchLoc"/>
		<jsp:param name="mandatory" value="Yes"/>
		<jsp:param name="searchLocationTitle" value="Location Search"/>
	</jsp:include>
	<br>
	<center><hdiits:button name="button" type="button" value="Fetch Branches" onclick="ajaxFunctionToGetBranch()"/></center>
	<br>
	<table align="center" id="table1" border="1" width="100%"> 	
		 <tr> 
			 <td style="border: none"><hdiits:caption captionid="WF.BRANCH" bundle="${wfLables}" /></td>				
			 <td align="left" style="border: none" width="75%">
					<hdiits:select name="selBranchName" id="branchName"  captionid="WF.BRANCH" validation="sel.isrequired" bundle="${wfLables}" mandatory="true" onchange="ajaxFunctionOnBranchSel()">
					<option value="0">Select</option>
					</hdiits:select>			
			 </td>
		 </tr>		 		 
		 <tr>
		 <td style="border: none"><hdiits:caption captionid="WF.DOCNAME" bundle="${wfLables}" /></td>		
		 <td align="left" style="border: none">	
			<hdiits:select name="selDocName" id="docName" captionid="WF.DOCNAME" validation="sel.isrequired" bundle="${wfLables}" mandatory="true" >
				<option value="0">Select</option>
				<c:forEach  items="${wfDoclist}" var="wflookup1">
						<option value='<c:out value="${wflookup1.docId}"/>' selected="true">
							<c:out value="${wflookup1.docName}" />
						</option>								
				</c:forEach>
			</hdiits:select>			
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

function chkSelection()
{
	if(document.forms[0].selDocName.value!=0)
	{
		alert("Location Changed!!!");
		return false;
	}
	else
	{
		return true;
	}
}

function ajaxFunctionToGetBranch()
{
		var locIdForBranch = document.getElementById('LOCATION_ID_branchLoc').value;
		if(locIdForBranch == "")
		{			
			alert("<fmt:message key='WF.ENTERLOC' bundle='${wfLables}'/>");
			return false;
		}	
		else
		{
			
			document.forms[0].hiddenText.value=0;	
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
	    	var url = "${contextPath}/hdiits.htm?actionFlag=viewBranch&locId="+locIdForBranch; 	     	
	  	    xmlHttp.onreadystatechange = function()                                                                                                       
			{			
				if (xmlHttp.readyState == 4) 
				{     
					if (xmlHttp.status == 200) 
					{							
								//chkSelection();
								deleteComboValues();						
								var text;
				            	var z=document.getElementById("branchName");
						    	var XMLDoc=xmlHttp.responseXML.documentElement;	
						    	var tableentries = XMLDoc.getElementsByTagName('locIdMapped');
						    	
						    	alert("branchId::"+tableentries[0].childNodes[0].text);
						    	alert("branchName::"+tableentries[0].childNodes[1].text);
								for ( var i = 0 ; i < tableentries.length ; i++ )
			     				{
			     					text=tableentries[i].childNodes[0].text;   
			     				    value=tableentries[i].childNodes[1].text;			     				   
			     				    value = value.replace(/andand/i,'&');			     				   
			     				    var y=document.createElement('option');			     				   		 					
									y.value=text;		//Value
									y.text=value;		//DisplayText									
									try
			   						{
			    						z.add(y,null); 			    			
			   						}
			 						catch(ex)
			   						{			   			 
			   			 				z.add(y); 
			   						}		   					
			           			}		           	  
			           				//document.getElementById('LOCATION_ID_branchLoc').value="0";
					}				
				}
		    }					
	   			xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
				xmlHttp.send(encodeURIComponent(null));
				return true;
		}
}

function deleteComboValues()
{	
	   var UserEntries=document.getElementById("branchName");
	   for(var i=1;i<UserEntries.length;i++)
	   {
				UserEntries.remove(i);
				i = i - 1;
	   }
}
			


function ajaxFunctionOnBranchSel()
{
		var locIdForBranch = document.getElementById('LOCATION_ID_branchLoc').value;
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
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}
    	
    	var url = "${contextPath}/hdiits.htm?actionFlag=chkBranchId&locationId="+locIdForBranch+"&branchId="+document.forms[0].selBranchName.value; 
		alert("url On BranchSelection::"+url);		
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{					
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var tableentries = XMLDoc.getElementsByTagName('locationIdMapped');						
					var locId=document.getElementById('LOCATION_ID_branchLoc').value;
					var branchId=document.forms[0].selBranchName.value;
					var documentId=document.forms[0].selDocName.value;	
					Size=tableentries[0].childNodes[0].text;	
					alert("Size"+Size);											
					if(Size<1)
					{							
						for( var i = 0 ; i < tableentries.length ; i++ )
	   					{		
	   						docName=tableentries[i].childNodes[1].text;			
	   						flag = tableentries[i].childNodes[2].text; 
	   						lname=tableentries[i].childNodes[3].text;  
	   						lbranchname=tableentries[i].childNodes[4].text;  
						}							
					}
					else if(Size>=1)				
					{		
						for( var i = 0 ; i < tableentries.length ; i++ )
	   					{		
	   						docId=	tableentries[i].childNodes[1].text;			
	   						docName = tableentries[i].childNodes[2].text;  
							flag = tableentries[i].childNodes[3].text;  	
							srNo = tableentries[i].childNodes[4].text; 
							lname=tableentries[i].childNodes[5].text;  
							lbranchname=tableentries[i].childNodes[6].text;  
						}							
	   						 document.forms[0].hiddensrNo.value=srNo; 	
	   						 
	   						 if(flag=="true")
	  						 {
	   						 	alert("For Location="+lname+" And Branch=" + lbranchname +" Document==>"+ docName+ " Already Exists!!");
	   						 	document.forms[0].selDocName.value=docId;	   						 	
	   						 }
					}
   						     document.forms[0].hiddenflag.value=flag;     						   
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

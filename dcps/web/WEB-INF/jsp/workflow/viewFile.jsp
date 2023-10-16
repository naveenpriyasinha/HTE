
<% try {  %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hod/ps/common.js"/>"></script> 
<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>"></script>
 
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="orgPostDtlsRltList" value="${resValue.orgPostDtlsRltList}"></c:set>
<script>
</script>
<hdiits:form name="viewFileForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<table align="center" id="table1" class="tabtable">
	<tr>
		<td colspan="2" class="datatableheader"> Enter File Details </td>
	</tr>
	<tr>
		<td class="fieldLabel">
		<hdiits:caption captionid="WF.Post"  bundle="${fmsLables}"/>
		</td>
		<td class="fieldLabel">
		<hdiits:select name="Post" caption="Post" id="Post" onchange="ajaxFunctionForBranches()">
			<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
					<c:forEach items="${orgPostDtlsRltList}" var="postLookup">
						<option value='<c:out value="${postLookup.orgPostMst.postId}"/>'>
						<c:out value="${postLookup.postShortName}"/></option>
					</c:forEach>							
		</hdiits:select>
		</td>		
	</tr>		
	<tr>
		<td class="fieldLabel">		
			<hdiits:caption captionid="WF.Section"  bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
			<hdiits:select name="section" caption="Section" id="section" onchange="ajaxfunction()">
				<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
				<c:forEach var="Branch" items="${BranchList}">
						<option value='<c:out value="${Branch.branchCode}"/>'>
						<c:out value="${Branch.branchCode}"/></option>
				</c:forEach>
			</hdiits:select>		
		 </td>
	</tr>

	<tr>
		<td class="fieldLabel">
			<hdiits:caption captionid="WF.Subject"  bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
			<hdiits:select name="Subject" caption="WF.Subject" id="Subject"  bundle="${fmsLables}" onchange="ajaxfunctionForTriCode()">
				<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
			</hdiits:select>		
		</td>
	</tr>

	<tr>
		<td class="fieldLabel">
			<hdiits:caption captionid="WF.LetterNo"  bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
			<hdiits:text name="letterno" captionid="WF.LetterNo" id="letterno"/>
		</td>
	</tr>
			
	<tr>
		<td class="fieldLabel">
			<hdiits:caption captionid="WF.Description"   bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
			<hdiits:textarea name="Description" captionid="WF.Description" id="Description" bundle="${fmsLables}" maxlength="300" cols="40"></hdiits:textarea>
			<hdiits:a href="#" onclick="openCompDesc('Description','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a> 
		</td>
	</tr>
	<tr></tr>	
	<tr>
		<td align="center" colspan="2" class="fieldLabel">
				<hdiits:button name="Report" id="Close" caption="View Report" bundle="${fmsLables}" type="button" style="align:center" onclick="showReport()" />		
		</td>
	</tr>
	</table>	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>			
 </hdiits:form>
<script type="text/javascript">
function showReport()
{		
		showProgressbar();	
		//alert("docname::"+document.forms[0].Subject.options[document.forms[0].Subject.selectedIndex].text);		
		Post=document.forms[0].Post.value;
		section=document.forms[0].section.value;
		Subject=document.forms[0].Subject.value;
		letterno=document.forms[0].letterno.value;
		Description=document.forms[0].Description.value;
		document.forms[0].method='post';      
      	//document.forms[0].action='${contextPath}/hdiits.htm?actionFlag=reportService&reportCode=6000007&action=generateReport&PostId='+Post+'&Section='+section+'&docName='+Subject+'&Tricode='+letterno+'&FileDesc='+Description;      	
      	document.forms[0].action='${contextPath}/hdiits.htm?actionFlag=reportService&reportCode=6000051&action=generateReport&PostId='+Post+'&Section='+section+'&docName='+Subject+'&Tricode='+letterno+'&FileDesc='+Description;
       	document.forms[0].submit();
}

function ajaxFunctionForBranches()
{	
	if(document.forms[0].Post.value != '0') 
	{
		try
	   			{   
	   			xmlHttp=new XMLHttpRequest();    
	   			}
				catch (e)
				{// Internet Explorer    
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
			        		
			        		return false;        
			   				}
					}
				}
			var url = "${contextPath}/hdiits.htm?actionFlag=branchdetail&postId="+document.forms[0].Post.value;			  
		    xmlHttp.onreadystatechange = function()
		     {
				if (xmlHttp.readyState == 4) 
				{ 
					if (xmlHttp.status == 200) 
						{
				 	
							var XMLDoc=xmlHttp.responseXML.documentElement;
							
							var Branch = XMLDoc.getElementsByTagName('Branch');
							if(Branch.length==0)
							{
								alert("Branch is not configured for selected branch...");
								return;
							}
							var comboid = document.getElementById('section')
							
							
							if(comboid.length > 1)
					    		  {
					    		     clearList(comboid);
					    		  }
							
							for ( var i= 0 ; i < Branch.length ; i++ )
							{
								Subjectid = Branch[i].childNodes[0].text;							
		   						branchName = Branch[i].childNodes[1].text;													
		   						var element=document.createElement('option');   				
		     				
			     				element.text=branchName
			     				element.value=Subjectid		     				
			     				try
							    {
							    	comboid.add(element,null); // standards compliant
							    }
							    catch(ex)
							    {
							   		 comboid.add(element); // IE only
							    }										
							}  
					
						} 
				}
			}				
			xmlHttp.open("POST",encodeURI(url), false);
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		   	xmlHttp.send(encodeURIComponent(null));
			return true;
	 }
}


function ajaxfunction()
{
	if(document.forms[0].section.value != '0') 
	{
		try
	   			{   
	   			xmlHttp=new XMLHttpRequest();    
	   			}
				catch (e)
				{// Internet Explorer    
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
			        		
			        		return false;        
			   				}
					}
				}
			var postId = document.getElementById("Post").value;				
			var url = "${contextPath}/hdiits.htm?actionFlag=loadsectiondetail&postId="+postId+"&srNo="+document.forms[0].section.value;
					  
		    xmlHttp.onreadystatechange = function()
		     {
				if (xmlHttp.readyState == 4) 
				{ 
					if (xmlHttp.status == 200) 
						{
				 	
							try{
								var XMLDoc=xmlHttp.responseXML.documentElement;
								
								var Subject = XMLDoc.getElementsByTagName('Subject');
								if(Subject.length==0){
									alert("Subject is not configured for selected branch...");
									return;
								}
								var comboid = document.getElementById('Subject')
								
								
								if(comboid.length > 1)
						    		  {
						    		     clearList(comboid);
						    		  }
								
								for ( var i= 0 ; i < Subject.length ; i++ )
								{
									Subjectid = Subject[i].childNodes[0].text;							
			   						Subjectname = Subject[i].childNodes[1].text;	   						
			   						var element=document.createElement('option');   				
			     				
				     				element.text=Subjectname
				     				element.value=Subjectid		     				
				     				try
								    {
								    comboid.add(element,null); // standards compliant
								    }
								    catch(ex)
								    {
								    comboid.add(element); // IE only
								    }										
								}  
								}catch(e){
									
								}
						
						} 
				}
			}
				
		xmlHttp.open("POST",encodeURI(url), false);
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	   	xmlHttp.send(encodeURIComponent(null));
	   return true;
	 }

}


function ajaxfunctionForTriCode()
{
try
   			{   
   			xmlHttp=new XMLHttpRequest();    
   			}
			catch (e)
			{// Internet Explorer    
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
		        		
		        		return false;        
		   				}
				}
			}			
		var url = "${contextPath}/hdiits.htm?actionFlag=loadTriCode&docTriCode="+document.forms[0].Subject.value;		 
	    xmlHttp.onreadystatechange = function()
	     {
			if (xmlHttp.readyState == 4) 
			{ 
				if (xmlHttp.status == 200) 
					{
			 	
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var TriCode = XMLDoc.getElementsByTagName('TriCode');						
						if(TriCode.length==0){
							alert("No Tri Code Mapped");
							return;
						}
						
						var triCodeText = document.getElementById('letterno')
						for ( var i= 0 ; i < TriCode.length ; i++ )
						{								
							tricode = TriCode[i].text;		   								   					
		     				try
						    {						   
						    triCodeText.value= tricode  
						    }
						    catch(ex)
						    {
						   	triCodeText.value= tricode ;  // IE only
						    }										
						}  
				
					} 
			}
		}
			
	xmlHttp.open("POST",encodeURI(url), false);
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
   	xmlHttp.send(encodeURIComponent(null));
   return true;
}
</script>
<% }
catch(Exception ex)  
{
	ex.printStackTrace();	
}	
%>



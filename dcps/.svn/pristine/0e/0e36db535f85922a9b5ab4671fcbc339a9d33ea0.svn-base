
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
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="BranchList" value="${resValue.Branches}"></c:set>
<c:set var="UserList" value="${resValue.User}"></c:set>
<c:set var="subject" value="${resValue.subject}"></c:set>
<c:set var="sendBackTo" value="${resValue.sendBackTo}"></c:set>

<script>
var RestrictCreateFile='${resValue.RestrictCreateFile}';
if(RestrictCreateFile=="yes")
{
	if(!confirm('<fmt:message key="WF.NoCorrSelectCrtFile" bundle="${fmsLables}"></fmt:message>'+'\n'+'<fmt:message key="WF.DoYouProceed" bundle="${fmsLables}"></fmt:message>'))
	{
	
		window.close();
	}
	
	
	
}

var cont='${resValue.ProceedFmsAction}';
var FmsAlertMsg='${resValue.FmsAlertMsg}'
if(cont=="No")
{
	if(FmsAlertMsg!="")
	{
		alert(FmsAlertMsg);
	}
	else
	{
		alert('<fmt:message key="WF.FmsActMsg" bundle="${fmsLables}"></fmt:message>');
	}
		
	window.close();
}

</script>
<hdiits:form name="CreatefileForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="corrId" default="${resValue.corrId}" />

<hdiits:hidden name="createdFromInbox" default="${resValue.createdFromInbox}" />
<hdiits:hidden name="lstActPostIds" default="${resValue.LstActPostId}" />
<hdiits:hidden name="winName"/>


	
	<table align="center" id="table1" class="tabtable">
	<tr><td colspan="2" class="datatableheader"> Enter File Details </td></tr>
	<tr>
		<td class="fieldLabel">
		<hdiits:caption captionid="WF.Post"  bundle="${fmsLables}"/>
		</td>
		<td class="fieldLabel">
		<hdiits:select name="Post" caption="Post" id="Post" mandatory="true" validation="sel.isrequired"  onchange="ajaxFunctionForBranches()" onfocus="setDefault();">
			<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
					<c:forEach var="User" items="${UserList}">
						<option value='<c:out value="${User.orgPostMst.postId}"/>'>
						<c:out value="${User.postName}"/></option>
					</c:forEach>							
		</hdiits:select>
		</td>
		
	</tr>
	
	<c:if test="${subject eq 'Nill Subject' }">
			<tr>
			<td class="fieldLabel">
			
			<hdiits:caption captionid="WF.Section"  bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
			<hdiits:select name="section" caption="Section" id="section" mandatory="true" validation="sel.isrequired" onchange="ajaxfunction()">
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
			<hdiits:select name="Subject" caption="WF.Subject" id="Subject" mandatory="true" validation="sel.isrequired" bundle="${fmsLables}" onchange="ajaxfunctionForTriCode()">
			<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
			</hdiits:select>
			
			</td>
		</tr>
	</c:if>
	
	
	
	
		<c:if test="${empty resValue.corrId}">
	<tr>
		<td class="fieldLabel">
		
		<hdiits:caption captionid="WF.Section"  bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
		<hdiits:select name="section" caption="Section" id="section" mandatory="true" validation="sel.isrequired" onchange="ajaxfunction()">
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
		<hdiits:select name="Subject" caption="WF.Subject" id="Subject" mandatory="true" validation="sel.isrequired" bundle="${fmsLables}" onchange="ajaxfunctionForTriCode()">
		<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
		</hdiits:select>
		
		</td>
	</tr>

	<tr>
		<td class="fieldLabel">
		<hdiits:caption captionid="WF.LetterNo"  bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
		<hdiits:text name="letterno" captionid="WF.LetterNo" id="letterno"  mandatory="true"/>
		</td>
	</tr>

	<tr>
		<td class="fieldLabel">
		<hdiits:caption captionid="WF.SubCode"  bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
		<hdiits:select name="SubCode" caption="WF.SubCode" id="SubCode" mandatory="true" bundle="${fmsLables}"  validation="sel.isrequired"  onchange="checkOtherCaption()">
		<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
		<option value="0"><hdiits:caption captionid="WF.Other" bundle="${fmsLables}"/></option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
		<option value="13">13</option>
		<option value="14">14</option>
		<option value="15">15</option>
		<option value="16">16</option>
		<option value="17">17</option>
		<option value="18">18</option>				
		</hdiits:select>
			
		<hdiits:number  id="other" style="display:none"  name="Otherno" captionid="WF.Other" bundle="${fmsLables}" validation="txt.isrequired"/>	

		</td>
	</tr>
		</c:if>	
		<c:if test="${not empty resValue.corrId and subject ne 'Nill Subject'}">
		<tr>
		<td class="fieldLabel">
		<hdiits:caption captionid="WF.Subject"  bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
		<hdiits:text name="Subject" captionid="WF.Subject" id="Subject" size="40" readonly="true" default="${subject}"></hdiits:text>
		</td>
		</tr>
		</c:if>	
		<tr>
		<td class="fieldLabel">
		<hdiits:caption captionid="WF.Description"   bundle="${fmsLables}" />
		</td>
		<td class="fieldLabel">
		<hdiits:textarea name="Description" captionid="WF.Description" id="Description" bundle="${fmsLables}" validation="txt.isrequired"  mandatory="true"  maxlength="300" cols="40"></hdiits:textarea>
		</td></tr>
		<tr><td id="arr_date" colspan="2" class="fieldLabel">
		<!--<hdiits:caption captionid="WF.arrival"   bundle="${fmsLables}" />
		<hdiits:dateTime  name="letterDate" captionid="WF.arrival" bundle="${fmsLables}" validation="txt.isrequired" mandatory="true"></hdiits:dateTime>
		<hdiits:caption captionid="WF.Time"   bundle="${fmsLables}" />
		<hdiits:time name="time" caption="time" validation="txt.isrequired" mandatory="true"/>-->
		</td></tr>
		<tr><td colspan="2" class="fieldLabel">
		<hdiits:radio name="physical" id="electronic" captionid="WF.Electronic"  mandatory="true" validation="sel.isradio"   value="I"  default="I"    bundle="${fmsLables}" onclick="inserttext('2')"/>
		<!--<hdiits:radio name="physical" id="physical" captionid="WF.Physical"   validation="sel.isradio"   value="I"  bundle="${fmsLables}" onclick="inserttext('1')"/>-->
		</td></tr>
		<tr><td align="center" colspan="2" class="fieldLabel">
		<hdiits:button name="create" id="create" captionid="wf.create" bundle="${fmsLables}" type="button" onclick="validatecontrols()" />
		<hdiits:button name="Close" id="Close" captionid="WF.CLOSE" bundle="${fmsLables}" type="button" style="align:center" onclick="window.close()" />
		<hdiits:button name="resetb" captionid="WF.Reset" bundle="${fmsLables}" type="button" onclick="checkForReset()"/>
		
		</td></tr>

	</table>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>			
 </hdiits:form>
<script type="text/javascript">
function checkForReset() {

	document.forms[0].reset();
}
function setDefault()
{
	if(document.getElementById("corrId").value != '')
		return true;
	
	var comboid =document.getElementById('section');
	
	if(comboid.length > 1)
	 {
	     clearList(comboid);
	 }
	 
	 
	/*var element=document.createElement('option');   				
		     				
	element.text='select'
	element.value=-1		     				
	try
  	{
  		 comboid.add(element,null); // standards compliant
   	}
	 catch(ex)
	 {
	   comboid.add(element); // IE only
	 }*/
	 
	 
	var comboid =document.getElementById('Subject');
	if(comboid.length > 1)
	 {
	     clearList(comboid);
	 }
	 
	 
	/*var element=document.createElement('option');   				
		     				
	element.text='select'
	element.value=-1		     				
	try
  	{
  		 comboid.add(element,null); // standards compliant
   	}
	catch(ex)
	{
	   comboid.add(element); // IE only
	}
		*/					
}


function validatecontrols(buttonParam)
{
		   var controls=null;
		   var count=0;
		   var controlnames=new Array();
		   controlnames.push('Post');
		   controlnames.push('section');
		   controlnames.push('Subject');
		   controlnames.push('letterno');
		   controlnames.push('SubCode');		   
		   controlnames.push('Description');
		   var returnVal =  validateSpecificFormFields(controlnames);
		   if(returnVal==true)
		   		submitform();
}
function inserttext(src)
{	
	if(src=="1")		
		document.getElementById("arr_date").style.display="";		
	else		
		document.getElementById("arr_date").style.display="none";
			
}


function checkOtherCaption()
{
	if(document.forms[0].SubCode.value==1)
		document.getElementById("other").style.display="";	
	else		
		document.getElementById("other").style.display="none";	
}
function submitform()
{
	showProgressbar();
	document.getElementById("CreatefileForm").method="post";
	document.getElementById("CreatefileForm").action="${contextPath}/hdiits.htm?actionFlag=createFile&moduleName=WorkList&menuName=forWorkList&sendBackTo=${sendBackTo}";
	document.getElementById("CreatefileForm").submit();
}



function ajaxFunctionForBranches()
{
	
	var sub="${subject}";
	
	if((document.getElementById("corrId").value != '') && (sub !=  'Nill Subject'))
		return true;
	
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
			//alert(url);  
		    xmlHttp.onreadystatechange = function()
		     {
				if (xmlHttp.readyState == 4) 
				{ 
					if (xmlHttp.status == 200) 
						{
				 	
							var XMLDoc=xmlHttp.responseXML.documentElement;
							
							var Branch = XMLDoc.getElementsByTagName('Branch');
							if(Branch.length==0){
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
			
			//alert(url);  
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
						var FmsActionDetails = XMLDoc.getElementsByTagName('FmsActionDetails');
						var ProceedFmsAction=FmsActionDetails[0].childNodes[0].text;	
						var FmsAlertMsg=FmsActionDetails[0].childNodes[1].text;	
						
						

						if(ProceedFmsAction=='No')
						{
							if(FmsAlertMsg!="")
							{
								alert(FmsAlertMsg);
								window.close();
							}
							else
							{
								alert('<fmt:message key="WF.FmsActMsg" bundle="${fmsLables}"></fmt:message>');
								window.close();
							}		
						}
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

//document.getElementById("arr_date").style.display="none"
//document.getElementById("other").style.display="none"
//document.getElementById("winName").value=window.opener.parent.name;
try{
document.getElementById("winName").value=window.opener.parent.name;
}catch(e) {

document.getElementById("winName").value=window.parent.name;
}
</script>


<% }
catch(Exception ex)  {
	ex.printStackTrace();	
}	
%>



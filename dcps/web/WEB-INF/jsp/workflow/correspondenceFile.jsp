<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script  type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="BranchList" value="${resValue.Branches}"></c:set>
<c:set var="UserList" value="${resValue.User}"></c:set>

<hdiits:form name="CreatefileForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="fileId" default="100010301"/>
	<table align="center" border="1">
	<tr>
	<td><table align="center" id="table1">	
	<tr>
	<td>
		<hdiits:caption captionid="WF.Unit"  bundle="${fmsLables}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:select name="unit" caption="Unit" id="unit" mandatory="true">
			<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
					<option value='<c:out value="${UserList.postDetailId}"/>'>
					<c:out value="${UserList.postName}"/></option>				
		</hdiits:select>
		</td>		
		</tr>
		<tr>
		<td >
		<hdiits:caption captionid="WF.Section"  bundle="${fmsLables}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:select name="section" caption="Section" id="section" mandatory="true" onchange="ajaxfunction()">
		<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
			<c:forEach var="Branch" items="${BranchList}">
					<option value='<c:out value="${Branch.cmnBranchMst.branchId}"/>'>
					<c:out value="${Branch.cmnBranchMst.branchName}"/></option>
				</c:forEach>
		</hdiits:select>
		</td>
		</tr>

		<tr>
		<td >
		<hdiits:caption captionid="WF.Subject"  bundle="${fmsLables}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:select name="Subject" caption="WF.Subject" id="Subject" mandatory="true" bundle="${fmsLables}">
		<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
		</hdiits:select>
		</td>
		</tr>

		<tr><td>
		<hdiits:caption captionid="WF.LetterNo"  bundle="${fmsLables}" />&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:text name="letterno" captionid="WF.LetterNo" id="letterno"  mandatory="true"/>
		</td></tr>

		<tr><td>
		<hdiits:caption captionid="WF.SubCode"  bundle="${fmsLables}" />&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:select name="SubCode" caption="WF.SubCode" id="SubCode" mandatory="true" bundle="${fmsLables}">
		<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
		<option value="11">11</option>
		<option value="12">12</option>
		<option value="13">13</option>
		<option value="14">14</option>
		<option value="15">15</option>
		<option value="16">16</option>
		<option value="17">17</option>
		<option value="18">18</option>				
		</hdiits:select>

		<tr><td>
		<hdiits:caption captionid="WF.Description"   bundle="${fmsLables}" />
		<hdiits:textarea name="Description" captionid="WF.Description" id="Description" bundle="${fmsLables}"   mandatory="true"  maxlength="300"></hdiits:textarea>
		</td></tr>		
		
		<tr><td align="center">
		<hdiits:button name="create" id="create" caption="create" bundle="${fmsLables}" type="button" onclick="submitform()" />
		<hdiits:button name="cancel" id="cancel" caption="cancel" bundle="${fmsLables}" type="button" style="align:center"  />
		<hdiits:button name="reset" id="reset" caption="reset" bundle="${fmsLables}" type="button" style="align:right"/>
		</td></tr>

	</table>
	</td></tr></table>
 </hdiits:form>
<script type="text/javascript">

function submitform()
{
	document.getElementById("CreatefileForm").method="post";
	document.getElementById("CreatefileForm").action="hdiits.htm?viewName=wf-corrFileCreate";
	document.getElementById("CreatefileForm").submit();
}
function ajaxfunction()
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
		var url = "${contextPath}/hdiits.htm?actionFlag=loadsectiondetail&srNo="+document.forms[0].section.value;
		//alert(url);  
	    xmlHttp.onreadystatechange = function()
	     {
			if (xmlHttp.readyState == 4) 
			{ 
				if (xmlHttp.status == 200) 
					{
			 	
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var Subject = XMLDoc.getElementsByTagName('Subject');
						var comboid = document.getElementById('Subject')
						for ( var i= 0 ; i < Subject.length ; i++ )
						{
							Subjectid = Subject[i].childNodes[0].text;
							
	   						Subjectname = Subject[i].childNodes[1].text;
	   						//alert(Subjectname)	
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
				
					} 
			}
		}
			
	xmlHttp.open("POST",encodeURI(url), false);
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
   	xmlHttp.send(encodeURIComponent(null));
   return true;

}

</script>
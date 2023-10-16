
<%
try {
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.*"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="attachmentList" value="${resultMap.AttachmentNames}"></c:set>
<%
int counter = 0;
%>
<%
		ResultObject resObj;
		resObj = (ResultObject) request.getAttribute("result");
		Map resValueMap = (HashMap) resObj.getResultValue();
		List objUrlList = (List) resValueMap.get("AttachmentUrl");
		List attachments = (List) resValueMap.get("AttachmentNames");
%>
<script type="text/javascript">
/*function submitForm(src)
{
	if(src==document.draftForm.elements['commonradio'])
			document.draftForm.elements['type'].value="common";
	else
			document.draftForm.elements['type'].value="subject";
	document.getElementById("draftForm").method="post";
	document.getElementById("draftForm").action="${contextPath}/hdiits.htm?actionFlag=showTemplates";
	document.getElementById("draftForm").submit();
}*/
function submitform()
{
	/*document.getElementById("draftForm").method="post";
	document.getElementById("draftForm").action="${contextPath}/hdiits.htm?actionFlag=Inserttabdetail";
	document.getElementById("draftForm").submit();*/
	var selectedVal=document.forms[0].Attachments.value
	alert(selectedVal);
	var index=selectedVal.indexOf("attachmentSerialNumber") ;	
	var subString1=selectedVal.substr(index)
	var subString2=selectedVal.substr(0,index)
	subString2=subString2.concat("&");
	subString2=subString2.concat(subString1);
	alert(subString2)
	
	
	
	var index=subString2.indexOf("attachmentId") ;	
	var subString1=subString2.substr(index)
	var subString2=subString2.substr(0,index)
	subString2=subString2.concat("&");
	subString2=subString2.concat(subString1);
	alert(subString2)
	
	//var action="${contextPath}"+subString2
	//alert(action)
	document.getElementById("draftForm").method="post";
	document.getElementById("draftForm").action=subString2
	alert('submit');
	document.getElementById("draftForm").submit();
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
		var url = "${contextPath}/hdiits.htm?actionFlag=showTemplates"
		alert(url);  
	    xmlHttp.onreadystatechange = function()
	     {
			if (xmlHttp.readyState == 4) 
			{ 
				if (xmlHttp.status == 200) 
					{
			 			alert('in block');
			 			alert(xmlHttp.responseXML)
						var XMLDoc=xmlHttp.responseXML.documentElement;
						alert(XMLDoc)
						var Subject = XMLDoc.getElementsByTagName('AttachmentList');
						var comboid = document.getElementById('Attachments')
						for ( var i= 0 ; i < Subject.length ; i++ )
						{
							Subjectid = Subject[i].childNodes[0].text;							
	   						Subjectname = Subject[i].childNodes[1].text;	   						
	   						var element=document.createElement('option');   				
	     				
		     				element.text=Subjectid
		     				
		     				element.value=Subjectname	
		     							
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
<hdiits:form name="draftForm" method="POST" action="./hdiits.htm"
	encType="multipart/form-data" validate="true">
	<hdiits:hidden name="type" default="subject" />
	<hdiits:radio name="commonradio" id="radio" caption="Common" value="I"
		onclick="ajaxfunction()" />
	<hdiits:radio name="subjectradio" id="radio" caption="Subject"
		value="I" onclick="ajaxfunction()" />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<hdiits:select name="Attachments" captionid="Attachments"
		id="Attachment">
		<hdiits:option>Select</hdiits:option>
	</hdiits:select>
	<center><hdiits:button type="button" name="DownLoadButton"
		id="submitButtonid" caption="DownLoad" onclick="submitform();" /></center>
</hdiits:form>

<%
		} catch (Exception ex) {
		ex.printStackTrace();
	}
%>

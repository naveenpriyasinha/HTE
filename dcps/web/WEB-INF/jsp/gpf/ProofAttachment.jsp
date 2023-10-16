<%@ include file="../core/include.jsp" %> 
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<c:set var="resValue" value="${resultObj.resultValue}" />
<fmt:setBundle basename="resources.common.CommonLables" var="AttachmentLables" scope="request"/>
<fmt:setBundle basename="resources.Constants" var="ApplConstants" scope="request"/>
<fmt:message bundle="${ApplConstants}" key="BIO_ENROLL_ACTIVATE" var="BIO_ENROLL_ACTIVATE"/>

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="com.tcs.sgv.common.valueobject.CmnAttachmentMst"%>
<%@page import="java.util.Set"%>
<%@page import="com.tcs.sgv.common.valueobject.CmnAttachmentMpg"%>
<%@page import="java.util.Iterator"%>
<c:set var="formName" value='<%=request.getParameter("formName")%>'>
</c:set>
<script type="text/javascript"  src="<c:url value="/script/common/cmbBoxFillUp.js"/>">
</script><script type="text/javascript"  src="<c:url value="/script/common/attachment.js"/>">
</script>

<style type="text/css">
  .removeLink { 
  text-decoration: underline;  
  cursor: pointer;  
  color: #0000FF;
  }
</style>

<% 
	String attachmentNAME = (String)request.getParameter("attachmentName");
	if(attachmentNAME==null)
	{
		throw new JspException("ATTACHMENT NAME IS REQUIRED");
	}

	String formNAME = (String)request.getParameter("formName");
	if(formNAME==null)
	{
		throw new JspException("FORM NAME IS REQUIRED FOR ATTACHMENT="+attachmentNAME);
	}

	String attachmentTYPE = (String)request.getParameter("attachmentType");
	if(attachmentTYPE==null)
	{
		throw new JspException("ATTACHMENT TYPE IS REQUIRED FOR MULTIPLE ATTACHMENT="+attachmentNAME);
	}

	String multipleAdd = (String)request.getParameter("multiple");	
	String rowNumber = "";
	boolean multiple = false;
	if(multipleAdd!=null && multipleAdd.length()>0 && multipleAdd.equalsIgnoreCase("Y"))
	{
		multiple = true;
		rowNumber = (String)request.getParameter("rowNumber");
		if(rowNumber==null)
		{
			throw new JspException("ROW NUMBER ARGUMENT IS REQUIRED IN FOR ATTACHMENT=" + attachmentNAME);	
		}
	}
	boolean imgTypeAttachment = false;
	String documentType = (String)request.getParameter("documentType");
	if(documentType!=null && documentType.trim().length() > 1 && documentType.equals("IMAGE"))
	{
		imgTypeAttachment = true;
	}
	
	Locale locale = (Locale) session.getAttribute("localeObj");
	ResourceBundle constResourceBundle =  ResourceBundle.getBundle("resources/common/CommonLables",locale); 
	String attachmentTitle = request.getParameter("attachmentTitle");
	if(attachmentTitle==null || attachmentTitle.equals(""))
	{
		attachmentTitle = constResourceBundle.getString("ATTACHMENT_TITLE");
	}
	
	String addButtonFunction = (String)request.getParameter("addButtonFunction");
	String removeJSFunction = (String)request.getParameter("removeJSFunction");	
%>
<script type="text/javascript" language="JavaScript">
  addButtonFunction<%=request.getParameter("attachmentName")%> = true;
  removeJSFunction<%=request.getParameter("attachmentName")%> = true; 
</script>

<script type="text/javascript" language="JavaScript">
  updater<%=request.getParameter("attachmentName")%> = null;
  counter<%=request.getParameter("attachmentName")%>=0;
  requestObject<%=request.getParameter("attachmentName")%> = null;
  formNameGlobalVar<%=request.getParameter("attachmentName")%> = null;
  formAction<%=request.getParameter("attachmentName")%> = null;
  formTarget<%=request.getParameter("attachmentName")%> = null;
  formMethod<%=request.getParameter("attachmentName")%> = null;
  removeURL = null;
  attachmentEdit<%=request.getParameter("attachmentName")%> = true;
  dbRowCount<%=request.getParameter("attachmentName")%> = 0;

 rowCountForEidt<%=request.getParameter("attachmentName")%> = 0;

	/*Flag for Changed Row Number to use or Incremented Row Number to use*/
  rowNumberChanged<%=request.getParameter("attachmentName")%> = false;
 
	 // end of method ajaxfunction	

function returnLatestRowNumber<%=request.getParameter("attachmentName")%>(formName)
{
	// If Row Number is chagned then use local variable of attacment for rowNumber
	// Otherwise start with 1 if Incremanted Row Number is not exist.

	var rowNumber = 0;
	if(rowNumberChanged<%=request.getParameter("attachmentName")%>)
	{
		rowNumber = document.forms[formName].<%=request.getParameter("attachmentName")%>_rowNumber.value;
	}
	else
	{
		if(document.getElementById("Incremented_rowNumber")==null)
		{
			rowNumber = 1;		
		}
		else
		{
			rowNumber = document.getElementById("Incremented_rowNumber").value;
		}
	}
	return rowNumber;
}
function insRowForAdd<%=request.getParameter("attachmentName")%>(fileName,fileDescription,delRowNum,editFlag,viewURL)
{	
	counter<%=request.getParameter("attachmentName")%>++;
  	var x<%=request.getParameter("attachmentName")%>=document.getElementById('myTable<%=request.getParameter("attachmentName")%>').insertRow()
		  	
		  	
    var col1<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(0)
    var col2<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(1)
    var col3<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(2)

	if(!editFlag)
	{
	    document.forms[0].cnt<%=request.getParameter("attachmentName")%>.value = counter<%=request.getParameter("attachmentName")%>;
    }
	if(fileDescription != "")
	{
    	col1<%=request.getParameter("attachmentName")%>.innerHTML=fileDescription;
    }
    else
    {
	    var result = fileName.lastIndexOf(".");		    
	    var fileNameDesc = fileName.substring(0, result);    
    	col1<%=request.getParameter("attachmentName")%>.innerHTML = fileNameDesc;    	
    }
    
    col2<%=request.getParameter("attachmentName")%>.innerHTML=fileName;
    
    if(!editFlag)
	{	
    delRowNum = document.forms[0].<%=request.getParameter("attachmentName")%>_rowNumber.value;
	//alert('delRowNum'+delRowNum);
	
    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this,'+delRowNum+')" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a>'; 			
	}
	else
	{
		
		dbRowCount<%=request.getParameter("attachmentName")%> = dbRowCount<%=request.getParameter("attachmentName")%> + 1; 			
	}   
}
</script>

<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>

<script type="text/javascript" language="JavaScript"><!--

function ajaxfunction<%=request.getParameter("attachmentName")%>(rownum,removeUrl)
{
		try
		{  
		// Firefox, Opera 8.0+, Safari    

		xmlHttp<%=request.getParameter("attachmentName")%>=new XMLHttpRequest();    
		}
			catch (e<%=request.getParameter("attachmentName")%>)
			{    // Internet Explorer    
				try
			{

			    xmlHttp<%=request.getParameter("attachmentName")%>=new ActiveXObject("Msxml2.XMLHTTP");   

			}
			    catch (e<%=request.getParameter("attachmentName")%>)
			    {
				  try
				  {
					  xmlHttp<%=request.getParameter("attachmentName")%>=new ActiveXObject("Microsoft.XMLHTTP");        
				  }
				      catch (e<%=request.getParameter("attachmentName")%>)
				      {
					      alert("Your browser does not support AJAX!...<%=request.getParameter("attachmentName")%>");        
					      return false;        
				      }
				 }
		}
	
	var url<%=request.getParameter("attachmentName")%> = removeUrl + rownum;


	xmlHttp<%=request.getParameter("attachmentName")%>.onreadystatechange = function()
	{

		if (xmlHttp<%=request.getParameter("attachmentName")%>.readyState == 4) 
		{     
			if (xmlHttp<%=request.getParameter("attachmentName")%>.status == 200) 
			{
			
			
			}

		}
	 }
	xmlHttp<%=request.getParameter("attachmentName")%>.open("POST", encodeURI(url<%=request.getParameter("attachmentName")%>) , false);    
	xmlHttp<%=request.getParameter("attachmentName")%>.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	
	xmlHttp<%=request.getParameter("attachmentName")%>.send(encodeURIComponent(null));

}	 // end of method ajaxfunction		

function handleResponse<%=request.getParameter("attachmentName")%>() 
{
			if ((requestObject<%=request.getParameter("attachmentName")%>.readyState == 4) && (requestObject<%=request.getParameter("attachmentName")%>.status == 200)) 
			{				
					alert(requestObject<%=request.getParameter("attachmentName")%>.responseText);
			}
} // end of function handleResponse<%=request.getParameter("attachmentName")%>


function startStatusCheck<%=request.getParameter("attachmentName")%>()
{
	
	formName = "${formName}";
	
	var lastRow = document.getElementById('myTable<%=request.getParameter("attachmentName")%>').rows.length; 
		if(Number(lastRow)<=1)
		{
		
		 <%
		 if( addButtonFunction!=null && addButtonFunction.trim().length() > 1 ){
		 %>
		 if(!<%=addButtonFunction%>())
		 {
		 addButtonFunction<%=request.getParameter("attachmentName")%> = false;
		 }
		 else
		 {
		 addButtonFunction<%=request.getParameter("attachmentName")%> = true;
		 }
		 <%} %>

		if(addButtonFunction<%=request.getParameter("attachmentName")%>)
		{
		
  		formAction<%=request.getParameter("attachmentName")%> = window.document.forms[formName].action;  		
  		formTarget<%=request.getParameter("attachmentName")%> = window.document.forms[formName].target;
  		formMethod<%=request.getParameter("attachmentName")%> = window.document.forms[formName].method;
  		
  		
  		formNameGlobalVar<%=request.getParameter("attachmentName")%> = formName;
    	$('submitButton<%=request.getParameter("attachmentName")%>').disabled = true;
    	
    	try
    	{
			<%
			String attachmentSize = request.getParameter("attachmentSize");
			if(attachmentSize!=null && attachmentSize.trim().length() > 0){
				String attachmentSizeErrMsg = constResourceBundle.getString("ATTACHMENT_SIZE_ERROR");
				attachmentSizeErrMsg = attachmentSizeErrMsg.replace("$SIZE", attachmentSize);%>
				if(!attachmentSizeCheck('<%=request.getParameter("attachmentName")%>', '<%=attachmentSizeErrMsg%>', <%=attachmentSize%>))
				{	
					throw 'Attachment Size Exception';
				}
			<%}%>	

	  		var fileNameValue = document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.value;
	  		if(isFieldValueNull(fileNameValue) || fileNameValue=='' )
	  		{
	  			alert('<fmt:message bundle="${AttachmentLables}" key="VALID_FILE_NAME"/>');
	  			document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.focus();
	  			throw 'exception';    		
	  		}
	  		var result = fileNameValue.lastIndexOf(".");
		    var fileExtention = fileNameValue.substring(result+1, fileNameValue.length);
		    <%if(imgTypeAttachment){%>
			    if(fileExtention.toLowerCase()!='jpg' && fileExtention.toLowerCase()!='jpeg' && fileExtention.toLowerCase()!='bmp' && fileExtention.toLowerCase()!='gif')
			    {
					alert('AttachMent Type DoesNot allowed For Pensioner <%=request.getParameter("attachmentName")%> '+fileExtention);
					document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.focus();			    
		  			throw 'exception';		    	
			    }		    
		    <%}else{%>
			    if(fileExtention.toLowerCase()=='exe' || fileExtention.toLowerCase()=='com' || fileExtention.toLowerCase()=='bat' || fileExtention.toLowerCase()=='sys' || fileExtention.toLowerCase()=='pif' || fileExtention.toLowerCase()=='sys' || fileExtention.toLowerCase()=='sh' || fileExtention.toLowerCase()=='txt' )
			    {
					alert('AttachMent Type '+fileExtention.toUpperCase()+' Does Not allowed For <%=request.getParameter("attachmentTitle")%> ');
					document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.focus();			    
		  			throw 'exception';		    	
			    }
		    <%}%>
		        	
			updater<%=request.getParameter("attachmentName")%> = new Ajax.PeriodicalUpdater(
                                'status<%=request.getParameter("attachmentName")%>',
                                'Upload',
                                {asynchronous:true, frequency:1, method: 'get', parameters: 'c=status', onFailure: reportError<%=request.getParameter("attachmentName")%>});
                 
        	window.document.forms[formName].target="target_upload<%=request.getParameter("attachmentName")%>";
   
        	// form enctype is not supported here  ... it should be written within within the form tag
			// window.document.forms[formName].enctype="multipart/form-data";
			
			// the form method must be get here otherwise the form wont be submitted
 			window.document.forms[formName].method="post";
 			
 			<%if(multiple){%>
			var rowNumber = returnLatestRowNumber<%=request.getParameter("attachmentName")%>(formName); 
			//alert("rowNumber in JSP"+rowNumber);			
 			<%}else{%>
 			var rowNumber = '';
 			<%}%>
 			// START : CODE FOR GETTING VAUES OF FILE DESCRIPTION 
 			// ADDED BY : 202414  
 			//	alert ("OBJECT ");
 			var fileDescription = null ;
 			var obj = document.forms[formName].desc<%=request.getParameter("attachmentName")%>;
 			//	alert (obj);
 			//	alert (obj.type) ;
 			if (obj.type == 'text')
 			{
 				fileDescription = obj.value ;	
 			}
 			else 
 			{
 				var selIndex = obj.selectedIndex;
 				//	alert ("SELECTED INDEX :"+selIndex);
 				if (selIndex == 0 )
 				{
 					//	alert ('Please select file description ');
 					alert('<fmt:message bundle="${AttachmentLables}" key="ALE_FILE_DESCR_REQ"/>');
 					obj.focus();
 					throw 'File description combo is not selected ';
 				}
 				fileDescription = obj.options[selIndex].text  ;	
 			}
 			//	alert ("file description :"+fileDescription);
			// END : CODE FOR GETTING VAUES OF FILE DESCRIPTION 
			
// 			var fileDescription = document.forms[formName].desc<%=request.getParameter("attachmentName")%>.value;
 			
 			window.document.forms[formName].action="Upload?attachmentNameHidden=<%=request.getParameter("attachmentName")%>&fileDescription="+fileDescription+"&rowNumber="+rowNumber;
 			
        	window.document.forms[formName].submit();
        	
        	window.document.forms[formName].action = formAction<%=request.getParameter("attachmentName")%>;
        	window.document.forms[formName].target = formTarget<%=request.getParameter("attachmentName")%>;
        	window.document.forms[formName].method = formMethod<%=request.getParameter("attachmentName")%>;
        	
         }
		catch(e<%=request.getParameter("attachmentName")%>)
		{
			$('submitButton<%=request.getParameter("attachmentName")%>').disabled = false;
		}
		
		
		
    	return true;
    	}
    	else
    	{
    		return false;
    	}
   	}
   	else
   	{
   		alert("You Cant Add More Than One <%=request.getParameter("attachmentName")%>");
		return false;
   	}
}

function reportError<%=request.getParameter("attachmentName")%>(request)
{
    $('submitButton<%=request.getParameter("attachmentName")%>').disabled = false;

    $('status<%=request.getParameter("attachmentName")%>').innerHTML = '<div class="error"><b>Error communicating with server. Please try again.</b></div>';
}

function killUpdate<%=request.getParameter("attachmentName")%>(message)
{
    $('submitButton<%=request.getParameter("attachmentName")%>').disabled = false;

    updater<%=request.getParameter("attachmentName")%>.stop();
    if(message != '')
    {
      $('status<%=request.getParameter("attachmentName")%>').innerHTML = '<div class="error"><b>' + message + '</b></div>';
 	  remove('importFile<%=request.getParameter("attachmentName")%>');
 	  var obj = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>;
 	  // START : CODE FOR RESET FILE DESCRIPTION OBJECT
 	  // ADDED BY  : 202414
 	  if (obj.type == 'text')
 	  {
			document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value = '';		
 	  }
 	  else
 	  {
 	  		//	resetBioCmbBox (obj,'${param.bioDeviceType}') ;
 	  		obj.selectedIndex = 0 ;
 	  }
 	  // END : CODE FOR RESET FILE DESCRIPTION OBJECT
    }
    else
    {
     	 new Ajax.Updater('status<%=request.getParameter("attachmentName")%>',
                     'Upload',
                     {asynchronous:true, method: 'get', parameters: 'c=status', onFailure: reportError<%=request.getParameter("attachmentName")%>, onComplete: showResponse<%=request.getParameter("attachmentName")%>});
                     
    }
} // end of function killUpdate

function showResponse<%=request.getParameter("attachmentName")%>()
{
			var fileElementLocal = document.getElementById('importFile<%=request.getParameter("attachmentName")%>').value;
			if(fileElementLocal != '')
			{
	  			insRow<%=request.getParameter("attachmentName")%>();
  			}
  			
}
  
function insRow<%=request.getParameter("attachmentName")%>()
{	
		counter<%=request.getParameter("attachmentName")%>++;
	  	var x<%=request.getParameter("attachmentName")%>=document.getElementById('myTable<%=request.getParameter("attachmentName")%>').insertRow()
 		  	
 		  	
	    var col1<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(0);
	    var col2<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(1);
	    var col3<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(2);

	    document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].cnt<%=request.getParameter("attachmentName")%>.value = counter<%=request.getParameter("attachmentName")%>;

	    var fileName = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].importFile<%=request.getParameter("attachmentName")%>.value;
	    fileName = fileName.replace(/\\\\/g, '/') ;
	    var result = fileName.lastIndexOf("/");
	    fileName = fileName.substring(result+1, fileName.length);
	    
		if(document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value != "")
		{
			// 202414 
			var obj = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>;
 			if (obj.type == 'text')
 			{
 				fileDescription = obj.value ;	
 			}
 			else 
 			{
 				var selIndex = obj.selectedIndex;
 				// HERE NOT NEEDED TO CHECK DEFAULT SELECTION 
 				// alert ("SELECTED INDEX :"+selIndex);
 				fileDescription = obj.options[selIndex].text  ;	
 			}
 			//	alert ("file description :"+fileDescription);
			
			col1<%=request.getParameter("attachmentName")%>.innerHTML= fileDescription ;
//	    	col1<%=request.getParameter("attachmentName")%>.innerHTML=document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value;
	    }
	    else
	    {
		    result = fileName.lastIndexOf(".");
		    var fileNameDesc = fileName.substring(0, result);
	    	//col1<%=request.getParameter("attachmentName")%>.innerHTML = "<B>N.A</B>";
	    	col1<%=request.getParameter("attachmentName")%>.innerHTML = fileNameDesc;
	    }	    
	    
	    col2<%=request.getParameter("attachmentName")%>.innerHTML=fileName;
	    
	    <%if(multiple){%>	    
	    var rowNumber = returnLatestRowNumber<%=request.getParameter("attachmentName")%>(formNameGlobalVar<%=request.getParameter("attachmentName")%>); 
		//alert('DEL ROW NUM:'+rowNumber);	    
	    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this,'+rowNumber + ')" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a> / <a href="#" onClick="viewRow<%=request.getParameter("attachmentName")%>(this,'+ rowNumber +')">View+'rowNumber'+</a>'; 			
		<%}else{%>
		col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this)" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a>'; 			
		<%}%>

		var obj = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>
		if (obj.type == 'text')
		{
			document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value = '';		
		}
		else
		{
			//alert('resetBioCmbBox  called');
			obj.selectedIndex = 0 ;
			// resetBioCmbBox (obj ,'${param.bioDeviceType}')
		}
		
		viewRow<%=request.getParameter("attachmentName")%>(document.getElementById('myTable<%=request.getParameter("attachmentName")%>'))	
		remove('importFile<%=request.getParameter("attachmentName")%>');
} // end of function insRow
	function resetBioCmbBox (obj ,bioDeviceType)
	{
		//	alert ("bioDeviceType :"+bioDeviceType)	 ;
		obj.selectedIndex = 0 ;
	}
   
function delRow<%=request.getParameter("attachmentName")%>(rowNumber)
{
	var	src = document.getElementById("myTable<%=request.getParameter("attachmentName")%>");
	if(confirm('<fmt:message bundle="${AttachmentLables}" key="REMOVE_ALTER_MSG"/>'))
	{
	
		 <%
		 if( removeJSFunction!=null && removeJSFunction.trim().length() > 1 ){
		 %>
		 if(!<%=removeJSFunction%>(src))
		 {
		 removeJSFunction<%=request.getParameter("attachmentName")%> = false;
		 }
		 else
		 {
		 removeJSFunction<%=request.getParameter("attachmentName")%> = true;
		 }		 
		 <%} %>
		
		if(removeJSFunction<%=request.getParameter("attachmentName")%>)
		{ 
		try
		{
			counter<%=request.getParameter("attachmentName")%>--;
			var row<%=request.getParameter("attachmentName")%> = src.parentElement.parentElement;
			
			var rownum = row<%=request.getParameter("attachmentName")%>.rowIndex;
			if(rownum > 0)
			{
				rownum--;
			}
			
			<%if(multiple){%>
			
			// Row Count added for Attachment Multiple Add/Edit functionality 2007-10-11
			var rowCount = dbRowCount<%=request.getParameter("attachmentName")%>;
		
			ajaxfunction<%=request.getParameter("attachmentName")%>(rownum,'Upload?attachmentNameHidden=<%=request.getParameter("attachmentName")%>&rowNumber='+rowNumber+'&rowCount='+rowCount+'&removeElement=removeElementFromArrayList&elementNumber=');
			<%}else{%>
			
			rownum = rownum - rowCountForEidt<%=request.getParameter("attachmentName")%>;
		
			ajaxfunction<%=request.getParameter("attachmentName")%>(rownum,'Upload?attachmentNameHidden=<%=request.getParameter("attachmentName")%>&removeElement=removeElementFromArrayList&elementNumber=');
			<%}%>
			
			document.all("myTable<%=request.getParameter("attachmentName")%>").deleteRow(row<%=request.getParameter("attachmentName")%>.rowIndex);
			document.getElementById('prew<%=request.getParameter("attachmentName")%>').innerHTML = "";
		}
		catch(qq)
		{
				
		}
		}
	}	
				
} // end of function delRow
  
function viewRow<%=request.getParameter("attachmentName")%>(src,rowNumber)
{
	if(document.getElementById('prew<%=request.getParameter("attachmentName")%>'))
	{
		document.getElementById('prew<%=request.getParameter("attachmentName")%>').innerHTML = "";
	}
	var actionFlag<%=request.getParameter("attachmentName")%> = '';
	try
	{
		
		if(rowNumber == undefined) {
			rowNumber = '';
		}
	    var row<%=request.getParameter("attachmentName")%> = src.parentElement.parentElement;
		var rowIndex = row<%=request.getParameter("attachmentName")%>.rowIndex;
		// Row Count added for Attachment Multiple Add/Edit functionality 2007-10-11
		var rowCount = dbRowCount<%=request.getParameter("attachmentName")%>;
  		
	}
	catch(qq)
	{
		alert('error in view row ' + qq);
	}

	
	document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].action = formAction<%=request.getParameter("attachmentName")%>;	
	if(document.getElementsByName('actionFlag').length > 0 )
	{
		document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].actionFlag.value = actionFlag<%=request.getParameter("attachmentName")%>;  		
	}
} 
function insRowEdit<%=request.getParameter("attachmentName")%>()
{
		
		if(attachmentEdit<%=request.getParameter("attachmentName")%>)
		{	
		<%
		session.removeAttribute((String)request.getParameter("attachmentName")+"_EDITVO");
		ResultObject result=(ResultObject)request.getAttribute("result");
		if(result !=  null)
		{
		Map resultMap=(Map)result.getResultValue();
		CmnAttachmentMst cmnAttachmentMst =(CmnAttachmentMst)resultMap.get(request.getParameter("attachmentName"));
		if(cmnAttachmentMst != null)
		{
			long attachmentId = cmnAttachmentMst.getAttachmentId();
			Set<CmnAttachmentMpg> cmnAttachmentMpgs = cmnAttachmentMst.getCmnAttachmentMpgs();
			String attachmentDesc = "";
			String fileName = ""; 
			long attachmentSrNumber = 0;
			if(cmnAttachmentMpgs.size()>=1 || attachmentId > 1)
			{
				//System.out.println("editvo"+(String)request.getParameter("attachmentName")+"_EDITVO"+cmnAttachmentMst);
				session.setAttribute((String)request.getParameter("attachmentName")+"_EDITVO",cmnAttachmentMst);
			}
			
			Iterator<CmnAttachmentMpg> cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
			for(int j=0;j<cmnAttachmentMpgs.size();j++)
			{
			CmnAttachmentMpg cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
			attachmentDesc = cmnAttachmentMpg.getAttachmentDesc();
			fileName = cmnAttachmentMpg.getOrgFileName();
			attachmentSrNumber = cmnAttachmentMpg.getSrNo();
			%>
			formNameGlobalVar<%=request.getParameter("attachmentName")%> = <%="\""+request.getParameter("formName")+"\""%>
			
			
			
  			counter<%=request.getParameter("attachmentName")%>++;
  		  	var x<%=request.getParameter("attachmentName")%>=document.getElementById('myTable<%=request.getParameter("attachmentName")%>').insertRow()
  		  	
  		  	
		    var col1<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(0)
		    var col2<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(1)
		    var col3<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(2)
		 
		    document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].cnt<%=request.getParameter("attachmentName")%>.value = counter<%=request.getParameter("attachmentName")%>;

			<%if(attachmentDesc!=null && attachmentDesc.length()>1){%>		    
		    	col1<%=request.getParameter("attachmentName")%>.innerHTML=<%="\""+attachmentDesc+"\""%>;
		    <%}else{%>		    
		    	col1<%=request.getParameter("attachmentName")%>.innerHTML = "<B>N.A</B>";
		    <%}%>
		  
		  
		    col2<%=request.getParameter("attachmentName")%>.innerHTML=<%="\""+fileName+"\""%>;
		    
			<% String viewURL = "ifms.htm?actionFlag=viewAttachment&attachmentId="+attachmentId+"&attachmentSerialNumber="+attachmentSrNumber;%>			
		    <%String removeAttachment = (String) request.getParameter("removeAttachmentFromDB");%>
			<%if(removeAttachment!=null && removeAttachment.trim().length()>0 && removeAttachment.equalsIgnoreCase("Y")){%>		    
		    <% String removeURL = "Upload?attachmentNameHidden=" + request.getParameter("attachmentName") +"&attachmentMpgSrNo=" + cmnAttachmentMpg.getSrNo(); %>
		    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="removeRow<%=request.getParameter("attachmentName")%>(\'<%=removeURL%>\',this)" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a> ; 			

			<%}else{%>
		    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a href="<%=viewURL%>">View</a>';
		    <%}%>
		     			
			attachmentEdit<%=request.getParameter("attachmentName")%> = false;
			
			rowCountForEidt<%=request.getParameter("attachmentName")%> = rowCountForEidt<%=request.getParameter("attachmentName")%> + 1;
		<%
			}			
		}
		}
		%>
	}  		     			
}
function removeRow<%=request.getParameter("attachmentName")%>(removeURL,src)
{
	try
	{
		if(confirm('<fmt:message bundle="${AttachmentLables}" key="REMOVE_ALTER_MSG"/>'))
		{
			
			 <%
			 if( removeJSFunction!=null && removeJSFunction.trim().length() > 1 ){
			 %>
			 if(!<%=removeJSFunction%>(src))
			 {
			 removeJSFunction<%=request.getParameter("attachmentName")%> = false;
			 }
			 else
			 {
			 removeJSFunction<%=request.getParameter("attachmentName")%> = true;
			 }
			 <%} %>
			if(removeJSFunction<%=request.getParameter("attachmentName")%>)
			{	
			counter<%=request.getParameter("attachmentName")%>--;
			var row<%=request.getParameter("attachmentName")%> = src.parentElement.parentElement;
			ajaxfunctionRemoveURL<%=request.getParameter("attachmentName")%>(removeURL);
			var row<%=request.getParameter("attachmentName")%> = src.parentElement.parentElement;
			rowCountForEidt<%=request.getParameter("attachmentName")%> = rowCountForEidt<%=request.getParameter("attachmentName")%> - 1;
			document.all("myTable<%=request.getParameter("attachmentName")%>").deleteRow(row<%=request.getParameter("attachmentName")%>.rowIndex);
			}
			if(document.getElementById('prew<%=request.getParameter("attachmentName")%>'))
			{
					document.getElementById('prew<%=request.getParameter("attachmentName")%>').innerHTML = "";				
			}
		}
	}
	catch(qq)
	{
			alert('error in removeRow -->' + qq);
	}			
}
function ajaxfunctionRemoveURL<%=request.getParameter("attachmentName")%>(removeUrl)
{


		try
		{  

		xmlHttp<%=request.getParameter("attachmentName")%>=new XMLHttpRequest();    
		}
			catch (e<%=request.getParameter("attachmentName")%>)
			{    // Internet Explorer    
				try
			{

			    xmlHttp<%=request.getParameter("attachmentName")%>=new ActiveXObject("Msxml2.XMLHTTP");   

			}
			    catch (e<%=request.getParameter("attachmentName")%>)
			    {
				  try
				  {
				      //alert("here2");
					  xmlHttp<%=request.getParameter("attachmentName")%>=new ActiveXObject("Microsoft.XMLHTTP");        
				  }
				      catch (e<%=request.getParameter("attachmentName")%>)
				      {
					      alert("Your browser does not support AJAX!...<%=request.getParameter("attachmentName")%>");        
					      return false;        
				      }
				 }
		}
	
	var url<%=request.getParameter("attachmentName")%> = removeUrl;


	xmlHttp<%=request.getParameter("attachmentName")%>.onreadystatechange = function()
	{

		if (xmlHttp<%=request.getParameter("attachmentName")%>.readyState == 4) 
		{     
			if (xmlHttp<%=request.getParameter("attachmentName")%>.status == 200) 
			{       
			}

		}
	 }
	 
	xmlHttp<%=request.getParameter("attachmentName")%>.open("POST", encodeURI(url<%=request.getParameter("attachmentName")%>) , false);    
	xmlHttp<%=request.getParameter("attachmentName")%>.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	
	xmlHttp<%=request.getParameter("attachmentName")%>.send(encodeURIComponent(null));


}	
</script>

<%
String lStrcontentUsingAjax = "";
if(request.getParameter("contentUsingAjax") != null)
	lStrcontentUsingAjax = (String)request.getParameter("contentUsingAjax");
boolean iscontentUsingAjax = lStrcontentUsingAjax.equalsIgnoreCase("true")?true:false;
if(iscontentUsingAjax)
{
%>
<iframe id='target_upload<%=request.getParameter("attachmentName")%>' name='target_upload<%=request.getParameter("attachmentName")%>' src='' style='display: none'></iframe>
<script>
insRowEdit<%=request.getParameter("attachmentName")%>();
</script>
<%
}
else
{
%>
<iframe id='target_upload<%=request.getParameter("attachmentName")%>' name='target_upload<%=request.getParameter("attachmentName")%>' src='' style='display: none' onload='insRowEdit<%=request.getParameter("attachmentName")%>();' ></iframe>
<%	
}
%>

<%
boolean mandatoryFalg=false;
if(request.getParameter("mandatory")!=null && request.getParameter("mandatory").equalsIgnoreCase("Y"))
{
	mandatoryFalg = true;
}
%>
<fieldset style="width:100%"  ><legend><%=attachmentTitle%></legend>

<table  id="formTable1<%=request.getParameter("attachmentName")%>" border="0" align="left" width="100%" CELLSPACING="1" CELLPADDING="1">			
<%String readOnly = request.getParameter("readOnly");
if(readOnly!=null && readOnly.length()>0 && readOnly.equals("Y"))
{
%>
<tr style="display:none">
<%}else{%>
<tr>
<%}%>
<td>
<c:set var="attachmentNameValue" value='<%=request.getParameter("attachmentName")%>'></c:set>
<c:set var="attachmentTypeValue" value='<%=request.getParameter("attachmentType")%>'></c:set>
<hdiits:caption captionid="FILE_DESCRIPTION" bundle="${AttachmentLables}" />&nbsp;

<!--   
	// START : BIOMETRIC RELATED CODE  COMBO BOX 
	// ADDED BY : 202414
 -->
	<c:choose>
		<c:when test="${param.bioDeviceType ne null }" >
			<%
				String devType = request.getParameter("bioDeviceType");
				devType = devType.trim().toUpperCase();
				String lookUpName = "";
				if (devType.contains("IRIS"))
				{
				    lookUpName =  "IRIS_CAP_TYPE_BIO,";
				}
				if (devType.contains("MULTI_FINGER"))
				{
				    lookUpName = lookUpName +"MULTI_FINGER_CAP_TYPE_BIO,";
				}
				if (devType.contains("FACE"))
				{
				    lookUpName = lookUpName +"FACE_CAP_TYPE_BIO,";
				}
				if (devType.contains("SIDE_FACE"))
				{
				    lookUpName = lookUpName +"SIDE_FACE_CAP_TYPE_BIO";
				}								
				pageContext.setAttribute("BIOMETRIC_LOOKUPNAME", lookUpName);
			%>
			<hdiits:select name="desc${attachmentNameValue}" id="desc${attachmentNameValue}"
				lookupName="${BIOMETRIC_LOOKUPNAME}" captionid="ATTACH_FILE" bundle="${AttachmentLables}" >
				<hdiits:option value="-1">
					<fmt:message key="COMMON.DROPDOWN.SELECT" />
				</hdiits:option>
			</hdiits:select>
		</c:when>
		<c:otherwise>
			<hdiits:text name="desc${attachmentNameValue}" id="desc${attachmentNameValue}" maxlength="40" size="40" captionid="ATTACH_FILE" bundle="${AttachmentLables}" />	
		</c:otherwise>
	</c:choose>

<!--   
	// END  : BIOMETRIC RELATED CODE  COMBO BOX 
 -->

 <input type="hidden" name="cnt<%=request.getParameter("attachmentName")%>" value="0">&nbsp;
 </td>
 <td id="formTable1FILETD<%=request.getParameter("attachmentName")%>">
 <hdiits:caption captionid="ATTACH_FILE" bundle="${AttachmentLables}" />&nbsp;
 <hdiits:file id="importFile${attachmentNameValue}" type="txt-identityname" name="importFile${attachmentNameValue}" onkeypress="return checkValue();" /></td>
 <hdiits:hidden name="attachmentName" default="${attachmentNameValue}" />
 <hdiits:hidden name="attachmentType" default="${attachmentTypeValue}" />
 <c:set var="rowNumber" value="<%=rowNumber%>"></c:set>
 <hdiits:hidden name="${attachmentNameValue}_rowNumber" id="${attachmentNameValue}_rowNumber" default="${rowNumber}" />
 <td>
 <table align="CENTER"  > 
 <c:set var="submitName" value='<%=request.getParameter("attachmentName")%>'></c:set>
 <tr  >
 <td rowspan="2" >
 <hdiits:button type='button' name='submitButton${submitName}' id='submitButton${submitName}' caption='Upload'  onclick='startStatusCheck${submitName}()' style='width:100px;' />
 </td>
<!-- 
	// START : BIOMETRIC RELATED CODE 
	// ADDED BY : 202414
 --> 
 
<%

boolean flagForTr = false;
if(request.getParameter("isScannerEnable") != null && request.getParameter("isScannerEnable").equals("Y"))
{
    
%>
	<c:set var="multipleFlag" value="<%=multiple%>" ></c:set>
	<td>
  		<hdiits:button  type="button" name="scanbutton${submitName}" id="scanbutton${submitName}" captionid="SCAN_AND_ADD" bundle="${AttachmentLables}"  onclick="openScanAppletAttachmentName('${param.attachmentName}','${param.attachmentPrefix}','${multipleFlag}')" />
	</td>
	</TR>
<%
}
%>
<c:if test="${BIO_ENROLL_ACTIVATE eq 'Y'}"  >	
	<%
	if (request.getParameter("bioDeviceType") != null )
	{
	%>
	<%if (!flagForTr )
	{
	%>
		</tr>
	<%flagForTr = true;
	}
	%>
		<tr>
		<td>
		<hdiits:button type="button" name="bioFaceBtn${submitName}" id="scanbutton${submitName}" bundle="${AttachmentLables}" captionid="CAPTURE_AND_ADD"
	  	onclick="showBiometricCaptureWindow('${param.attachmentName}','${param.attachmentPrefix}','${param.bioDeviceType}')" />
	  	</td>
	  	</tr>
	<%
	}
	
	%>

<%
if (!flagForTr )
{
%>
</tr>
<%// END : BIEMOTRIC RELATED CODE 
}%>

</c:if>
<%

String errMsg = "";
if(request.getParameter("mandatory")!=null && request.getParameter("mandatory").equalsIgnoreCase("Y"))
{
	errMsg = attachmentTitle + " " + constResourceBundle.getString("MANDATORY");
%>
	 <c:set var="errMessage" value="<%=errMsg%>"></c:set>
	 <c:set var="attName" value='<%=request.getParameter("attachmentName")%>'></c:set>
	 <%if(request.getParameter("condition")!=null && request.getParameter("condition").length()>1){%>
	   	<c:set var="condition" value='<%=request.getParameter("condition")%>'></c:set>
	  	<hdiits:jsField jsFunction='mandatory_Attachment("${attName}", "${errMessage}")' name="${attName}" condition="${condition}" />
	 <%}else{ %>
	 	<hdiits:jsField jsFunction='mandatory_Attachment("${attName}", "${errMessage}")' name="${attName}" />
	 <%}%>
<%}%>
</tr>
</table>
</td>
</tr>
<tr>
<td colspan=3>
<%
if(readOnly!=null && readOnly.length()>0 && readOnly.equals("Y"))
{
%>
<div id="status<%=request.getParameter("attachmentName")%>" style="width: 100%;">
</div>
<%}else{%>
<div id="status<%=request.getParameter("attachmentName")%>" style="width: 100%;">
</div>
<%}%>

<br>      
<table id="myTable<%=request.getParameter("attachmentName")%>" class="datatable" >
    	<tr>
          <!-- 	<td><center>Sr No</center></td>     -->
           
        	<td class="datatableheader"><center><hdiits:caption captionid="DESCRIPTION" bundle="${AttachmentLables}" /></center></td>
    	    <td class="datatableheader"><center><hdiits:caption captionid="FILE_NAME" bundle="${AttachmentLables}" /></center></td>
    	    <td class="datatableheader"><center><hdiits:caption captionid="ACTIONS" bundle="${AttachmentLables}" /></center></td>
    	</tr>
</table>
</td>
</tr>
</table>
<br>
<c:set var="attachmentUniqueName" value='<%=request.getParameter("attachmentName")%>'></c:set>
<%errMsg = constResourceBundle.getString("ATTACHMENT_INSERT_ALERT");
  errMsg = errMsg.replace("$AttachmentTitle$",attachmentTitle);
%>
<c:set var="errMessage" value="<%=errMsg%>"></c:set>
<hdiits:jsField jsFunction='checkAttachmentValue("${attachmentUniqueName}", "${errMessage}")' name="checkAttachmentValue_${attachmentUniqueName}" />

</fieldset>
	<script type="text/javascript">
		window.FILL_COMBO_BOX_TAB_WISE = false;
		submitThisPageUsingAjax = false;
	</script>

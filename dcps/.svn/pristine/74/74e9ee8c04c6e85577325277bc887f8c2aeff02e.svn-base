<%@ include file="../core/include.jsp" %> 
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<fmt:setBundle basename="resources.common.CommonLables" var="AttachmentLables" scope="request"/>

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="com.tcs.sgv.common.valueobject.CmnAttachmentMst"%>
<%@page import="java.util.Set"%>
<%@page import="com.tcs.sgv.common.valueobject.CmnAttachmentMpg"%>
<%@page import="java.util.Iterator"%>
<c:set var="formName" value="${param.formName}">
</c:set>
<!-- <c:out value="${categoryType}"/>  -->


<style type="text/css">
  .prog-border {
  height: 8px;
  width: 200px;
  background: white;
  border: 1px solid #000;
  border-color: black;
  margin: 1;
  padding: 0;
  }

  .prog-bar {
  height: 6px;
  margin: 2px;
  padding: 0px;
  background: #3299CC;
  }
  
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
%>
<!-- This function is used for multiple ADD in one JSP and press EDIT button -->
<script type="text/javascript" language="JavaScript">
function mandatory_<%=request.getParameter("attachmentName")%>(errMassege)
{
	var returnValue = true;
	var attachmentTable = document.getElementById('myTable<%=request.getParameter("attachmentName")%>');
	var rowLength = attachmentTable.rows.length;
	
	if(rowLength<=1)
	{
		selectRequiredTab( 'desc<%=request.getParameter("attachmentName")%>' );
		document.forms[0].desc<%=request.getParameter("attachmentName")%>.focus();
		returnValue = false;
	}
	return returnValue;
}
function checkAttachmentValue_<%=request.getParameter("attachmentName")%>(errMassege)
{
	var attachmentDescription = document.getElementById('desc<%=request.getParameter("attachmentName")%>').value;
	var attachedFileName = document.getElementById('importFile<%=request.getParameter("attachmentName")%>').value;

	attachmentDescription = trimSentense(attachmentDescription);
	attachedFileName = trimSentense(attachedFileName);	
	if(attachmentDescription.length > 0 && attachedFileName.length > 0)
	{
		selectRequiredTab( 'desc<%=request.getParameter("attachmentName")%>' );
		document.forms[0].desc<%=request.getParameter("attachmentName")%>.focus();		
		return false;
	}
	else
	{
		return true;										
	}
}
function checkValue()
{
	return false;
}
// REMOVE FILE CONTROL NAME
function remove(control)
{    
	var who=document.getElementsByName(control)[0];    
	var who2= who.cloneNode(false);    
	who2.onchange= who.onchange;    
	who.parentNode.replaceChild(who2,who);
}
</script>


<script type="text/javascript" language="JavaScript">
 var updater<%=request.getParameter("attachmentName")%> = null;
 var counter<%=request.getParameter("attachmentName")%>=0;
 var requestObject<%=request.getParameter("attachmentName")%>;
 var formNameGlobalVar<%=request.getParameter("attachmentName")%>;
 var formAction<%=request.getParameter("attachmentName")%>;
 var formTarget<%=request.getParameter("attachmentName")%>;
 var formMethod<%=request.getParameter("attachmentName")%>;
 var removeURL = null;
 var attachmentEdit<%=request.getParameter("attachmentName")%> = true;
 var dbRowCount<%=request.getParameter("attachmentName")%> = 0;
 
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
			
			if(cmnAttachmentMpgs.size()>=1)
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
			
			<%/* REMOVE LINK IS NOW NOT NEEDED %>
		    <% String removeURL = "Upload?attachmentNameHidden=" + request.getParameter("attachmentName") +"&attachmentMpgSrNo=" + cmnAttachmentMpg.getSrNo(); %>
		    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="removeRow<%=request.getParameter("attachmentName")%>(\'<%=removeURL%>\',this)" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a>'; 			
			<%*/%>

			<% String viewURL = "hdiits.htm?actionFlag=viewAttachment&attachmentId="+attachmentId+"&attachmentSerialNumber="+attachmentSrNumber;%>			
		    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a href="<%=viewURL%>">View</a>';
		     			
			attachmentEdit<%=request.getParameter("attachmentName")%> = false;
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
		counter<%=request.getParameter("attachmentName")%>--;
		var row<%=request.getParameter("attachmentName")%> = src.parentElement.parentElement;
		
		//var rownum = row<%=request.getParameter("attachmentName")%>.rowIndex;
		//rownum--;
		ajaxfunctionRemoveURL<%=request.getParameter("attachmentName")%>(removeURL);
		
		document.all("myTable<%=request.getParameter("attachmentName")%>").deleteRow(row<%=request.getParameter("attachmentName")%>.rowIndex);
		
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


}	 // end of method ajaxfunction	
function removeRowFromTable<%=request.getParameter("attachmentName")%>(rowNum,formName)
{
  //document.forms[formName].desc<%=request.getParameter("attachmentName")%>.value = '';
  document.getElementById('desc<%=request.getParameter("attachmentName")%>').value = '';
  var tbl = document.getElementById('myTable<%=request.getParameter("attachmentName")%>');
  var lastRow = tbl.rows.length;
  while (lastRow > 1)
  { 
  tbl.deleteRow(lastRow - 1);
  lastRow = lastRow - 1;
  }
  
  if(rowNum>=0)
  {
  rowNum = rowNum + 1;
  if(formName!=null)
  {
 	//alert('removeRowFromTable:rowNum='+rowNum);	  
  	//document.forms[formName].<%=request.getParameter("attachmentName")%>_rowNumber.value = rowNum;
  	document.getElementById('<%=request.getParameter("attachmentName")%>_rowNumber').value = rowNum;
  }
  }
  
  dbRowCount<%=request.getParameter("attachmentName")%> = 0;
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
	    document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].cnt<%=request.getParameter("attachmentName")%>.value = counter<%=request.getParameter("attachmentName")%>;
    }
	if(fileDescription != "")
	{
    	col1<%=request.getParameter("attachmentName")%>.innerHTML=fileDescription;
    }
    else
    {
	    var result = fileName.lastIndexOf(".");		    
	    var fileNameDesc = fileName.substring(0, result);    
    	//col1<%=request.getParameter("attachmentName")%>.innerHTML = "<B>N.A</B>";
    	col1<%=request.getParameter("attachmentName")%>.innerHTML = fileNameDesc;    	
    }
    
    col2<%=request.getParameter("attachmentName")%>.innerHTML=fileName;
    
    if(!editFlag)
	{	
    delRowNum = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].<%=request.getParameter("attachmentName")%>_rowNumber.value;
	//alert('delRowNum'+delRowNum);
	
    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this,'+delRowNum+')" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a> / <a href="#" onClick="viewRow<%=request.getParameter("attachmentName")%>(this,'+ delRowNum +')">View</a>'; 			
	}
	else
	{
		col3<%=request.getParameter("attachmentName")%>.innerHTML='<a href="'+viewURL+ '">view</a>';
		dbRowCount<%=request.getParameter("attachmentName")%> = dbRowCount<%=request.getParameter("attachmentName")%> + 1; 			
	}   
}
</script>

<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>



<script type="text/javascript" language="JavaScript">


  
  //  reserve for xmlHttp request and response
  
function ajaxfunction<%=request.getParameter("attachmentName")%>(rownum,removeUrl)
{
		
	//	rownum--;
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


function startStatusCheck<%=request.getParameter("attachmentName")%>(formName)
{
		
  		formAction<%=request.getParameter("attachmentName")%> = window.document.forms[formName].action;  		
  		formTarget<%=request.getParameter("attachmentName")%> = window.document.forms[formName].target;
  		formMethod<%=request.getParameter("attachmentName")%> = window.document.forms[formName].method;
  		
  		
  		formNameGlobalVar<%=request.getParameter("attachmentName")%> = formName;
  		
    	$('submitButton<%=request.getParameter("attachmentName")%>').disabled = true;
    	
    	try
    	{
	  		var fileNameValue = document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.value;
	  		if(isFieldValueNull(fileNameValue))
	  		{
	  			alert('<fmt:message bundle="${AttachmentLables}" key="VALID_FILE_NAME"/>');
	  			document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.focus();
	  			throw 'exception';    		
	  		}
	  		var result = fileNameValue.lastIndexOf(".");
		    var fileExtention = fileNameValue.substring(result+1, fileNameValue.length);
		    //alert(fileExtention);
		    
		    <%if(imgTypeAttachment){%>
			    if(fileExtention.toLowerCase()!='jpg' && fileExtention.toLowerCase()!='jpeg' && fileExtention.toLowerCase()!='bmp' && fileExtention.toLowerCase()!='gif' && fileExtention.toLowerCase()!='png' && fileExtention.toLowerCase()!='tif' && fileExtention.toLowerCase()!='ppm')
			    {
					alert('<fmt:message bundle="${AttachmentLables}" key="VALID_IMG_FILE_EXTENSION"/>');
					document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.focus();			    
		  			throw 'exception';		    	
			    }		    
		    <%}else{%>
			    if(fileExtention.toLowerCase()=='exe' || fileExtention.toLowerCase()=='com' || fileExtention.toLowerCase()=='bat' || fileExtention.toLowerCase()=='sys' || fileExtention.toLowerCase()=='pif' || fileExtention.toLowerCase()=='sys' || fileExtention.toLowerCase()=='sh')
			    {
					alert('<fmt:message bundle="${AttachmentLables}" key="VALID_FILE_EXTENSION"/>');
					document.forms[formName].importFile<%=request.getParameter("attachmentName")%>.focus();			    
		  			throw 'exception';		    	
			    }
		    <%}%>
		        	
			updater<%=request.getParameter("attachmentName")%> = new Ajax.PeriodicalUpdater(
                                'status<%=request.getParameter("attachmentName")%>',
                                'Upload',
                                {asynchronous:true, frequency:1, method: 'get', parameters: 'c=status', onFailure: reportError<%=request.getParameter("attachmentName")%>});
                 
                 // target_upload${attachmentName}                               
        	window.document.forms[formName].target="target_upload<%=request.getParameter("attachmentName")%>";
   
        	// form enctype is not supported here  ... it should be written within within the form tag
			// window.document.forms[formName].enctype="multipart/form-data";
			
			// the form method must be get here otherwise the form wont be submitted
 			window.document.forms[formName].method="post";
 			
 			<%if(multiple){%>
 			var rowNumber = document.forms[formName].<%=request.getParameter("attachmentName")%>_rowNumber.value;
 			<%}else{%>
 			var rowNumber = '';
 			<%}%>
 			var fileDescription = document.forms[formName].desc<%=request.getParameter("attachmentName")%>.value;
 			
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
} // end of function startStatusCheck<%=request.getParameter("attachmentName")%>


function reportError<%=request.getParameter("attachmentName")%>(request)
{
    $('submitButton<%=request.getParameter("attachmentName")%>').disabled = false;

    $('status<%=request.getParameter("attachmentName")%>').innerHTML = '<div class="error"><b>Error communicating with server. Please try again.</b></div>';
} // end of function reportError<%=request.getParameter("attachmentName")%>

function killUpdate<%=request.getParameter("attachmentName")%>(message)
{
    $('submitButton<%=request.getParameter("attachmentName")%>').disabled = false;

    updater<%=request.getParameter("attachmentName")%>.stop();
    if(message != '')
    {
      $('status<%=request.getParameter("attachmentName")%>').innerHTML = '<div class="error"><b>' + message + '</b></div>';
 	  remove('importFile<%=request.getParameter("attachmentName")%>');
	  document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value = ''; 	        
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
			//alert(document.form[0].importFile<%=request.getParameter("attachmentName")%>.value);
			var fileElementLocal = document.getElementById('importFile<%=request.getParameter("attachmentName")%>').value;
		//	alert(fileElementLocal);
			if(fileElementLocal != '')
			{
	  			insRow<%=request.getParameter("attachmentName")%>();
  			}
  			
} // end of function showResponse<%=request.getParameter("attachmentName")%>
  
function insRow<%=request.getParameter("attachmentName")%>()
{	
		counter<%=request.getParameter("attachmentName")%>++;
	  	var x<%=request.getParameter("attachmentName")%>=document.getElementById('myTable<%=request.getParameter("attachmentName")%>').insertRow()
 		  	
 		  	
	    var col1<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(0);
	    var col2<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(1);
	    var col3<%=request.getParameter("attachmentName")%>=x<%=request.getParameter("attachmentName")%>.insertCell(2);

	    document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].cnt<%=request.getParameter("attachmentName")%>.value = counter<%=request.getParameter("attachmentName")%>;

	    var fileName = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].importFile<%=request.getParameter("attachmentName")%>.value;
	    var result = fileName.lastIndexOf("\\");
	    fileName = fileName.substring(result+1, fileName.length);
	    
		if(document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value != "")
		{
			
	    	col1<%=request.getParameter("attachmentName")%>.innerHTML=document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value;
	    }
	    else
	    {
		    result = fileName.lastIndexOf(".");		    
		    var fileNameDesc = fileName.substring(0, result);
	    	//col1<%=request.getParameter("attachmentName")%>.innerHTML = "<B>N.A</B>";
	    	col1<%=request.getParameter("attachmentName")%>.innerHTML = fileNameDesc;
	    }	    
	    
	    //col2<%=request.getParameter("attachmentName")%>.innerHTML=document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].importFile<%=request.getParameter("attachmentName")%>.value;
	    col2<%=request.getParameter("attachmentName")%>.innerHTML=fileName;
	   //alert("multiple is  "+'<%=multiple%>');
	    <%if(multiple){%>	    
	   // alert("this is   "+document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].<%=request.getParameter("attachmentName")%>_rowNumber.value);
	    var rowNumber = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].<%=request.getParameter("attachmentName")%>_rowNumber.value;
	   
	    col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this,'+rowNumber + ')" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a> / <a href="#" onClick="viewRow<%=request.getParameter("attachmentName")%>(this,'+ rowNumber +')">View</a>'; 			
		<%}else{%>
		
		//alert("nikunj+++"+'<%=request.getParameter("attachmentName")%>');
		col3<%=request.getParameter("attachmentName")%>.innerHTML='<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this,1)" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a> / <a href="#" onClick="viewRow<%=request.getParameter("attachmentName")%>(this)">View</a>'; 			
		<%}%>
		
		document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].desc<%=request.getParameter("attachmentName")%>.value = '';
		//document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].importFile<%=request.getParameter("attachmentName")%>.value = '';	    
		remove('importFile<%=request.getParameter("attachmentName")%>');
} // end of function insRow
   
function delRow<%=request.getParameter("attachmentName")%>(src,rowNumber)
{
	if(confirm('<fmt:message bundle="${AttachmentLables}" key="REMOVE_ALTER_MSG"/>'))
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
			ajaxfunction<%=request.getParameter("attachmentName")%>(rownum,'Upload?attachmentNameHidden=<%=request.getParameter("attachmentName")%>&removeElement=removeElementFromArrayList&elementNumber=');
			<%}%>
			
			document.all("myTable<%=request.getParameter("attachmentName")%>").deleteRow(row<%=request.getParameter("attachmentName")%>.rowIndex);
		}
		catch(qq)
		{
				alert('error in del row -->' + qq);
		}
	}	
				
} // end of function delRow
  
function viewRow<%=request.getParameter("attachmentName")%>(src,rowNumber)
{
	
	//alert("jaspal  "+window.document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].action);
	formAction<%=request.getParameter("attachmentName")%> = window.document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].action;
	//alert("jaspal "+formAction<%=request.getParameter("attachmentName")%>);
	var actionFlag<%=request.getParameter("attachmentName")%> = '';
	
	if(document.getElementsByName('actionFlag').length > 0 )
	{
		//alert("test inside actionFlag");
		actionFlag<%=request.getParameter("attachmentName")%> = document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].actionFlag.value;	
  		
  		document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].actionFlag.value='viewAttachmentFromClientPath';
	}
	try
	{
		if(rowNumber == undefined) {
			rowNumber = '';
		}
	    var row<%=request.getParameter("attachmentName")%> = src.parentElement.parentElement;
		
		var rowIndex = row<%=request.getParameter("attachmentName")%>.rowIndex;
		
		// Row Count added for Attachment Multiple Add/Edit functionality 2007-10-11
		var rowCount = dbRowCount<%=request.getParameter("attachmentName")%>;
		
		
  		var urlValue = "hdiits.htm?actionFlag=viewAttachmentFromClientPath&attachmentUniqeName=<%=request.getParameter("attachmentName")%>&rowNumber="+rowNumber+"&rowCount="+rowCount+"&rowIndex=";
		urlValue = urlValue + rowIndex;
		//alert(urlValue);
		
		document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].action = urlValue;
		//alert("kshfksahfkhsdakfhsakh"+document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].action);
  		document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].submit();
  		//alert("submitting properly");
	}
	catch(qq)
	{
		alert('error in view row -->' + qq);
	}
				
	document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].action = formAction<%=request.getParameter("attachmentName")%>;	
	if(document.getElementsByName('actionFlag').length > 0 )
	{
		document.forms[formNameGlobalVar<%=request.getParameter("attachmentName")%>].actionFlag.value = actionFlag<%=request.getParameter("attachmentName")%>;  		
	}
} 
  
  
</script>
<fieldset style="background: #eeeeee;padding: 2px;">
	<iframe id='target_upload<%=request.getParameter("attachmentName")%>' name='target_upload<%=request.getParameter("attachmentName")%>' src='' style='display: none' onload="insRowEdit<%=request.getParameter("attachmentName")%>()"></iframe>
	<%
	Locale locale = (Locale) session.getAttribute("localeObj");
	  ResourceBundle constResourceBundle =  ResourceBundle.getBundle("resources/common/CommonLables",locale);  
	  
	  String attachmentTitle = request.getParameter("attachmentTitle");
	  if(attachmentTitle==null || attachmentTitle.equals(""))
	  {
		  attachmentTitle = constResourceBundle.getString("ATTACHMENT_TITLE");
	  }
	%>

	<%
	if(request.getParameter("mandatory")!=null && request.getParameter("mandatory").equalsIgnoreCase("Y"))
	{
	%>
	<legend style="font-weight: bold;"><%=attachmentTitle%><label class="mandatoryindicatorlegend">*</label></legend>
	<%
	}else{
	%>
	<b><legend style="font-weight: bold;"><%=attachmentTitle%></legend></b>
	<%
	}
	%>
	
	<table  id="formTable1<%=request.getParameter("attachmentName")%>" border="1"  align="left" width="100%" CELLSPACING="1" CELLPADDING="1" >			
	<%String readOnly = request.getParameter("readOnly");
	if(readOnly!=null && readOnly.length()>0 && readOnly.equals("Y"))
	{
	%>	
	<tr style="display:none">
	<%}else{%>		
		<%}%>
		<tr>
		<td>
			
				<table align="center"  border="0" width="100%">
								
				<b>
				<c:set var="attachmentNameValue" value="${param.attachmentName}"></c:set>
				<c:set var="attachmentTypeValue" value="${param.attachmentType}"></c:set>
				
				<input type="text" style="display: none" name="desc${attachmentNameValue}" id="desc${attachmentNameValue}" maxlength="40" size="21" captionid="ATTACH_FILE" bundle="${AttachmentLables}" 		 />
				<input type="hidden" name="cnt${param.attachmentName}" value="0" >				
				</b>		
				
				<tr>
				<td align="left" valign="top">
				<b> 		 
	   				<hdiits:caption captionid="ATTACH_FILE" bundle="${AttachmentLables}" /> 
	   			</b>
	   			</td>	
	   					   			
	   			<td>		   						
				<input id="importFile<%=request.getParameter("attachmentName")%>" name="importFile<%=request.getParameter("attachmentName")%>" type="file" onkeypress="return checkValue();" size="10">
				<c:set var="submitName" value="${param.attachmentName}"></c:set>	
			<hdiits:button type="button"  name="submitButton${submitName}" id="submitButton${submitName}" caption="ADD" bundle="${AttachmentLables}"  onclick="startStatusCheck${submitName}('${formName}')" />
				</td>													   	   
				</tr>
				</table>
           	   
		 	   <hdiits:hidden name="attachmentName" default="${attachmentNameValue}" />
 			   <hdiits:hidden name="attachmentType" default="${attachmentTypeValue}" />
 			   <c:set var="rowNumber" value="${param.rowNumber}"></c:set>
	 	 	   <hdiits:hidden name="${attachmentNameValue}_rowNumber" id="${attachmentNameValue}_rowNumber" default="${rowNumber}" />
 	        
 	   
	
		<%
		if(readOnly!=null && readOnly.length()>0 && readOnly.equals("Y"))
		{
		%>
		<div style="display:none" id="status<%=request.getParameter("attachmentName")%>" style="width: 100%;"></div>
		<%}else{%>
		<div id="status<%=request.getParameter("attachmentName")%>" style="width: 100%;"></div>
		<%}%>
		
		
			
			
   
 		<%
			String errMsg = "";
			if(request.getParameter("mandatory")!=null && request.getParameter("mandatory").equalsIgnoreCase("Y"))
			{
				errMsg = attachmentTitle + " " + constResourceBundle.getString("MANDATORY");
		%>
	
		 <c:set var="errMessage" value="<%=errMsg%>"></c:set>
		 <c:set var="attName" value="${param.attachmentName}"></c:set>
		 <%if(request.getParameter("condition")!=null && request.getParameter("condition").length()>1){%>
		 <c:set var="condition" value="${param.condition}"></c:set>
	  	 <hdiits:jsField jsFunction='mandatory_${attName}("${errMessage}")' name="${attName}" condition="${condition}" />
	     <%}else{ %>
	 	 <hdiits:jsField jsFunction='mandatory_${attName}("${errMessage}")' name="${attName}" />
	     <%}%>
         <%}%>
		<!-- <input type="button" name="submitButton<%=request.getParameter("attachmentName")%>" id="submitButton<%=request.getParameter("attachmentName")%>" value="Add File" onClick="startStatusCheck<%=request.getParameter("attachmentName")%>('${formName}')">   -->
	
	   
	
	 		<table id="myTable<%=request.getParameter("attachmentName")%>" align="center"  width="100%">
    		<tr>         
        		<td class="datatableheader" align="right"><center><hdiits:caption captionid="DESCRIPTION" bundle="${AttachmentLables}" /></center></td>    	   	
    	    	<td class="datatableheader" align="right"><center><hdiits:caption captionid="FILE_NAME" bundle="${AttachmentLables}" /></center></td>    	    
    	    	<td class="datatableheader" align="right"><center><hdiits:caption captionid="ACTIONS" bundle="${AttachmentLables}" /></center></td>    	    
    		</tr>
			</table>	
	 	 </td>
		</tr>
		</table> 
</fieldset>

<br>
<c:set var="attachmentUniqueName" value="${param.attachmentName}"></c:set>
<%errMsg = constResourceBundle.getString("ATTACHMENT_INSERT_ALERT");
  errMsg = errMsg.replace("$AttachmentTitle$",attachmentTitle);
%>
<c:set var="errMessage" value="<%=errMsg%>"></c:set>
<hdiits:jsField jsFunction='checkAttachmentValue_${attachmentUniqueName}("${errMessage}")' name="checkAttachmentValue_${attachmentUniqueName}" />



<%
try{
	

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page autoFlush="true" %>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="script/tiny_mce/plugins/emotions/editor_plugin.js"></script>
<script type="text/javascript"src="script/common/base64.js"></script>

<fmt:setBundle basename="resources.workflow.FMSLables" var="wfLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="attachmentList" value="${resultMap.AttachmentNames}"></c:set>
<c:set var="AttachmentUrl" value="${resultMap.AttachmentUrl}"></c:set>
<c:set var="fmsFileTabDtls" value="${resultMap.fmsFileTabDtls}"></c:set>
<c:set var="corrId" value="${resultMap.corrId}"></c:set>

<c:set var="locId" value="${resultMap.locId}"></c:set>
<c:set var="branchId" value="${resultMap.branchId}"></c:set>
<c:set var="deptId" value="${resultMap.deptId}"></c:set>
<c:set var="ctgryId" value="${resultMap.ctgryId}"></c:set>

<c:set var="outBoxFlag" value="${resultMap.outBoxFlag}"></c:set>
<c:set var="context" scope="request">${resultMap.RequestURL}</c:set>
<c:set var="approved">${resultMap.fileApprove}</c:set>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript">

var path='${resultMap.RequestURL}';
var index=path.lastIndexOf('/');
var subPath=path.substring(0,index);
subPath='"'+subPath+'/dtds/specialEntities.dtd"';

tinyMCE.init({
	mode : "textareas",
	theme : "advanced",	
	plugins : "noneditable,table,insertdatetime,preview,print,pagebreak,advimagescale",	
	theme_advanced_layout_manager : "SimpleLayout",
	theme_advanced_buttons2 : "",
    theme_advanced_buttons3 : "",
    handle_event_callback : "myEvents",    	
    cleanup_on_startup: true,    
    advimagescale_noresize_all: false, 
	theme_advanced_buttons1_add : "fontselect,fontsizeselect,undo,redo,insertdate,inserttime,preview,forecolor,print,bullist,numlist,table,pagebreak",			
	theme_advanced_disable : "strikethrough,styleselect,removeformat,link,unlink,image,cleanup,help,code,removeformat,sub,sup,forecolor,backcolor,forecolorpicker,backcolorpicker,charmap,visualaid,anchor,newdocument,blockquote,separator,outdent,indent",	
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",	
	plugin_insertdate_dateFormat : "%Y-%m-%d",
	plugin_insertdate_timeFormat : "%H:%M:%S",	
	extended_valid_elements : "a[name|href|target|title|onclick],img[class|src|border|alt|title|hspace|vspace|width|height|align|name],hr[class|width|size|noshade],font[face|size|color|style],span[rowTypes|xpath|type|class|pos]",
	valid_child_elements : "table[tr|td|span|th]",
	convert_fonts_to_spans : false,	
	force_br_newlines : true,
	font_size_style_values : "xx-small,x-small,small,medium,large,x-large,xx-large",	
	content_css : "web/scripts/tiny_mce/themes/advanced/skins/default/content.css"	
});

function myEvents(e) {
	
	 //document.getElementById("info").innerHTML += e.type + " and " + e.target.tagName + "<br>";
	   
    if(e.type=="mousedown"){      
        var tagName=e.target.tagName;
        if(tagName=="IMG" || tagName=="TABLE" || tagName=="TR"){
        	alert("can not move");
        }
        	
    }
    
    if (e.type == "mouseup") {
        mySelection = tinyMCE.activeEditor.selection.getNode();
        
        if (mySelection.tagName == "IMG") {          
              storeOriginalDimensions(mySelection);
              setTimeout("fixSize(mySelection)",100);           
        }
        if (mySelection.tagName == "Table") {       
               storeOriginalDimensions(mySelection);
               setTimeout("fixSize(mySelection)",100);          
        }
    }
}
function addToDocument(imgId) {
    // this adds an image to the TinyMCE Editor
    tinyMCE.activeEditor.focus();
    imageUrl = "hdiits.htm?pid=" + imgId + "&width=150&height=150";
    var el = tinyMCE.activeEditor.dom.create('img', {src : imageUrl});
    tinyMCE.activeEditor.selection.setNode(el);
}
function storeOriginalDimensions(el) {
    // we store the original (or revised) default dimensions of all images that are clicked on in the editor
    var myId = findId(el.src);
    if (!imageHeights[myId]) imageHeights[myId] = new Array(el.width, el.height);
}
function fixSize(el) {
    // this changes image size after resizing (or simply clicking)
    if (el.tagName == "IMG") {
        var newWidth = el.width;
        var newHeight = el.height;
        var newDimensions = fixAspect(newWidth, newHeight, findId(el.src));
        var newUrl = el.src.replace(/width=[0-9]+/,"width="+newDimensions[0]);
        newUrl = newUrl.replace(/height=[0-9]+/,"height="+newDimensions[1]);
        el.src = newUrl;
        el.width = newDimensions[0];
        el.height = newDimensions[1];
    }
}
function fixAspect(w,h,pid) {
    // this adjusts width and height after resize to constrain to proportions if needed
    // it calculates change in height and width (dw, dh) and gives precedence to the dimension that changed the most
    if (imageHeights[pid]) {
        var orig = imageHeights[pid];
        var ow = orig[0];
        var oh = orig[1];
        var dw = Math.abs(ow-w);
        var dh = Math.abs(oh-h);
        var ratio = ow/oh;
        //document.getElementById("info").innerHTML += ow + "x" + oh + " was change to " + w + "x" + Math.round(w/ratio) + "with delta " + dw + "x" + dh + "<br>";
        imageHeights[pid] = new Array(w,h);
        if (dw && dh && w >= h) return new Array(w,Math.round(w/ratio));
        else if (dw && dh && w < h) return new Array(Math.round(h*ratio), h);
        else if (dw || dh) return new Array(w,h);
        else return new Array(w,h);
    }
    else return new Array(w,h); // just return what we got if there are no original dimensions
}
function findId(url) {
    // this just extracts XX from the image url
    // the url that is expected is showPhoto.php?pid=XX&width=yyy&height=zzz
    var myRegExp = /pid=([0-9]+)/;
    var result = myRegExp.exec(url);
    return result[1];
}

function prepareTemplate()
{
	//alert("in preapre template");
	var type=document.getElementById('cmbTemplate').value;
	//alert(type);
	
	document.getElementById('flagForSave').value='PENDING';
	if(type==-1)
	{
		alert("<fmt:message key="WF.SelectTemplate" bundle="${wfLables}"></fmt:message>");
		return;
	}
	showProgressbar();
	clearAllCheckBox();
	var xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) 
	{
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	var comOrSub=document.getElementById('ComOrSubjectDraftFlag').value;
	var url='hdiits.htm?actionFlag=FMS_prepareDraftTemplateForCorr&ComOrSubjectDraftFlag='+comOrSub+'&corrId=${corrId}&type='+type;
	xmlHttp.onreadystatechange=function()
	{
		if ((xmlHttp.readyState == 4) && (xmlHttp.status == 200))
	 	{
	 		
	 		document.getElementById('testArea').value=xmlHttp.responseText;
			var ed = tinyMCE.get('editor1');		
			
			ed.setContent(xmlHttp.responseText);
			
	 	}
	 	hideProgressbar();
	}
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);
	document.getElementById('draftCreateFlag').value='yes';
}
function saveChanges()
{
	var val=document.getElementById('flagForSave').value;
	
	if(val=='APPROVED'){
		alert('<fmt:message key="WF.CanNotSave" bundle="${wfLables}"></fmt:message>');		
		return ;
	}

	if(val=='DO NOT ISSUE'){
		alert('<fmt:message key="WF.CanNotSave" bundle="${wfLables}"></fmt:message>');		
		return ;
	}
	showProgressbar();
	
	
	if(document.getElementById('draftCreateFlag').value=='yes')	
		var reply =prompt('<fmt:message key="WF.FileName" bundle="${wfLables}"></fmt:message>','<fmt:message key="WF.Name" bundle="${wfLables}"></fmt:message>');
	
	
	if(document.getElementById("saveFlag").value=='no')
	{
		alert("<fmt:message key="WF.CanNotUpdate" bundle="${wfLables}"></fmt:message>");
		return;
	}
	var ed = tinyMCE.get('editor1');
	var htmlContent = ed.getContent();
	if(htmlContent=='')
	{
		hideProgressbar();
		alert('<fmt:message key="WF.PrepareDraft" bundle="${wfLables}"></fmt:message>');
		return;
	}
	
	var type=document.getElementById('cmbTemplate').value;
	
	htmlContent = '<?xml version="1.0" encoding="UTF-8"?><!DOCTYPE  document  PUBLIC  "specialEntities.dtd" '+subPath+'><html><body>'+ htmlContent + '</body></html>';
	
	
	var encodedHtml = encodeBase64(htmlContent);
	var xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) 
	{
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	
 	document.getElementById('testArea').value=htmlContent;

	var comOrSub=document.getElementById('ComOrSubjectDraftFlag').value;
	
	var url='hdiits.htm?actionFlag=FMS_saveDraftForCorr&ComOrSubjectDraftFlag='+comOrSub+'&corrId=${corrId}&draftName='+reply+'&draftApproveFlag='+document.getElementById("draftApproveFlag").value+'&attchmentId='+document.getElementById("attchmentId").value+'&tamplateId='+type+'&draftCreateFlag='+document.getElementById('draftCreateFlag').value;	
	var param = "encodedContent="+encodedHtml;
	//alert(url);
	showProgressbar();
	xmlHttp.onreadystatechange=function()
	{
		if ((xmlHttp.readyState == 4) && (xmlHttp.status == 200))
	 	{
	 		
			ed.setContent(xmlHttp.responseText);
			
			if(document.getElementById('draftCreateFlag').value=='yes')
				submitForm();			
		
			document.getElementById('draftCreateFlag').value='no';			
			
	 	}
	 	hideProgressbar();
	}
	xmlHttp.open("POST",url,true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	xmlHttp.send(param);	
	
}


//this function added by praveen (Start)

function getDraftForAttachment(loopCount,count,srno,status,refDocId,metadataflag)
{		
	 showProgressbar();
	document.getElementById('flagForSave').value=status;
	
	document.getElementById('refCtgry').value=refDocId;
		
	document.getElementById('draftRefDocFlag').value=metadataflag;	
	
	var ed = tinyMCE.get('editor1');
	var htmlContent = ed.getContent();			
	document.getElementById("attchmentId").value=loopCount;	
	if(status=='PENDING')
		name='approveRadio';
	else
		name='doNotIssueChkBox';
		
	//var id=name+count;	
	
	/*unCheckAllCheckBox('approveChkBox');	
	
	unCheckAllCheckBox('finalChkBox');*/
	if(status=='APPROVED'){
			clearAllRadio();
			unCheckAllCheckBox('doNotIssueChkBox');
			try{
				var id=name+count;
				document.getElementById(id).checked=true;
				document.getElementById("pkvalForDoNotIssue").value=srno;
			}catch(e){
				id="doNotISsueChkBox"+count;	
				document.getElementById(id).checked=true;
				document.getElementById("pkvalForDoNotIssue").value=srno	
			}
			
			
			
	}
	
	//alert('check box:::'+name);
	//alert('count check box:::'+count);
	if(status!='DO NOT ISSUE')
	{
		try{
			unCheckAllCheckBox('doNotIssueChkBox');
			clearAllRadio();
			var radioObj = document.getElementsByName(name);
			//alert(radioObj);
			radioObj[count].checked = true
			//alert(radioObj[count].checked);
			//document.getElementById(id).checked=true;
			//document.getElementById("pkval").value=srno;
			document.getElementById("saveFlag").value='yes';
		}catch(e)
		{	
			try{
				//id="doNotISsueChkBox"+count;	
				//document.getElementById(id).checked=true;
				//document.getElementById("pkvalForDoNotIssue").value=srno
				
				document.getElementById("saveFlag").value='no';
			}catch(e)
			{
				//alert('in catch block');
			}
		}
	}
		
	htmlContent = '<?xml version="1.0" encoding="UTF-8"?><!DOCTYPE  document  PUBLIC  "specialEntities.dtd" '+subPath+'><html><body>'+ htmlContent + '</body></html>';
	var encodedHtml = encodeBase64(htmlContent);
	var xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) 
	{
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
   
 	document.getElementById('testArea').value=htmlContent;	
	
	alert('<fmt:message key="WF.GetContent" bundle="${wfLables}"></fmt:message>'+srno);
	var url='hdiits.htm?actionFlag=FMS_viewDraftByAttachmentIdForCorr&corrId=${corrId}&attchmentId='+srno;
	var param = "encodedContent="+encodedHtml;
	//alert(url);
	xmlHttp.onreadystatechange=function()
	{
		if ((xmlHttp.readyState == 4) && (xmlHttp.status == 200))
	 	{
	 		//alert("i am back")
	 		//alert(xmlHttp.responseText);
	 		
			var ed = tinyMCE.get('editor1');
			
			ed.setContent(xmlHttp.responseText);
			hideProgressbar();			
	 	}
	 	
	}
	xmlHttp.open("POST",url,true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	xmlHttp.send(param);	
	document.getElementById('draftCreateFlag').value='no';
	
}

//this function added by praveen (end)
function unCheckAllCheckBox(chkObj)
{				
	var total = document.getElementsByName(chkObj).length;		
	for(var i=0;i<eval(total);i++)
	{									
		document.getElementsByName(chkObj)[i].checked=false;		
	}
	document.getElementById('pkvalForDoNotIssue').value='';
}


function loadXMLDoc(fname)
{
	var xmlDoc;
	// code for IE
	if (window.ActiveXObject)
	  {
	  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
	  }
	// code for Mozilla, Firefox, Opera, etc.
	else if (document.implementation 
	&& document.implementation.createDocument)
	  {
	  xmlDoc=document.implementation.createDocument("","",null);
	  }
	else
	  {
	  alert('Your browser cannot handle this script');
	  }
	xmlDoc.async=false;
	xmlDoc.load(fname);
	return(xmlDoc);
}

function displayResult(htmlContent)
{	//alert();
	xml=loadXMLDoc("a.xml");
	xsl=loadXMLDoc("caseincomplete.xsl");
	//alert(xsl);
	// code for IE
	if (window.ActiveXObject)
	  {
	  ex=xml.transformNode(xsl);
	  document.getElementById("testArea").innerHTML=ex;
	  }
	// code for Mozilla, Firefox, Opera, etc.
	else if (document.implementation 
	&& document.implementation.createDocument)
	  {
	  xsltProcessor=new XSLTProcessor();
	  xsltProcessor.importStylesheet(xsl);
	  resultDocument = xsltProcessor.transformToFragment(xml,document);
	  document.getElementById("").appendChild(resultDocument);
	  }
}



function ajaxfunction(src){			
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
		//var url = "${contextPath}/hdiits.htm?actionFlag=showTemplates"
		var url;	
		if(src=='common'){
			url = "${contextPath}/hdiits.htm?actionFlag=FMS_showDraft&templateType=common&corrId=${corrId}";
			document.getElementById('ComOrSubjectDraftFlag').value='common'
			}
		else{
			url = "${contextPath}/hdiits.htm?actionFlag=FMS_showDraft&templateType=subject&corrId=${corrId}";
			document.getElementById('ComOrSubjectDraftFlag').value='subject'
			}
			
	    xmlHttp.onreadystatechange = function()
	     {
			if (xmlHttp.readyState == 4) 
			{ 
				if (xmlHttp.status == 200) 
					{
			 			
			 			var XMLDoc=xmlHttp.responseXML.documentElement;					
						var Subject = XMLDoc.getElementsByTagName('AttachmentList');						
						var comboid = document.getElementById('cmbTemplate');				
											
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
				
					} 
			}
		}
			
	xmlHttp.open("POST",encodeURI(url), false);
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
   	xmlHttp.send(encodeURIComponent(null));
   return true;
}



function approveDraft(srNo,src,refDocId,metadataflag)
{	

		document.getElementById('refCtgry').value=refDocId;
		
		document.getElementById('draftRefDocFlag').value=metadataflag;	
		
		if(src.checked == true)
		{	
		
			//alert('in add');
			if(document.getElementById("pkval").value==''){
				//alert('does not contain anything');
				document.getElementById("pkval").value=srNo
				//alert('value is'+document.getElementById("pkval").value);
			}
			else{
				//alert('adding with comma');
				document.getElementById("pkval").value=document.getElementById("pkval").value+','+srNo;
				//alert('value is'+document.getElementById("pkval").value);
			}
				
			
		}else
		{
			//alert('in removing action');
			var value=document.getElementById("pkval").value;		
			var index=value.lastIndexOf(srNo);	
			//alert('index'+index);		
			value1=value.substring(0,index-1);
			//alert('value first'+value1);
			if(value1==''){
				var index2=value.indexOf(',');
				//alert('second index'+index2);
				
				if(index2==-1)	
					value2='';
				else
					value2=value.substring(index2+1,value.length);
					
				//alert('value2'+value2);
				}
			else{
					var index1=value.indexOf(',',index);
						if(index1==-1)
							value2='';
						else
							value2=value.substring(index1,value.length);	
				}
			
			value=value1+value2;
			//alert('pk val is '+value);
			document.getElementById("pkval").value=value;	
			//alert(document.getElementById("pkval").value);
			
		}	
		
}

function setDefaultValue(refDocId,metadataflag){	
	document.getElementById('refCtgry').value=refDocId;		
	document.getElementById('draftRefDocFlag').value=metadataflag;
	unCheckAllCheckBox('doNotIssueChkBox');	
}

function approveDraftAction()
{
	
	var radioObj = document.getElementsByName('approveRadio');
	var radLength = document.getElementsByName('approveRadio').length;
	var flag=true;
	//alert('in approveDraftAction');
	
	if(document.getElementById('draftRefDocFlag').value==1){
	//alert('in if condition');	
		var ed = tinyMCE.get('editor1');
		ed.setContent('');
		document.getElementById('testArea').value='';	
		
		for(var cnt=0;cnt<radLength && flag==true ;cnt++)
		{
			if(radioObj[cnt].checked == true)
			{
				flag=false;
				var id=radioObj[cnt].value;
				//alert('<fmt:message key="WF.ApproveContent" bundle="${wfLables}"></fmt:message>'+id);
				document.getElementById('pkval').value=id
				document.getElementById('draftApproveFlag').value='yes';
				var action="${contextPath}/hdiits.htm?actionFlag=approveDraftForCorr";	
				document.getElementById("filedraftForm").method="post";
				document.getElementById("filedraftForm").action=action;	
				document.getElementById("filedraftForm").submit();
				
			}
		}
		
		if(flag==true)
		{
			alert('please select atleast one draft to approve');
			return;
		}
	}else if(document.getElementById('draftRefDocFlag').value==0){
		///alert('in else condition');	
		
		for(var cnt=0;cnt<radLength && flag==true ;cnt++)
		{
			if(radioObj[cnt].checked == true)
			{
				flag=false;
				var id=radioObj[cnt].value;	
				
				//alert('id of approve::'+id);
				document.getElementById('draftApproveFlag').value='yes';
				var urlStyle ='width=600,height=750,toolbar=no,menubar=no,location=no,top=150,left=200'; 
				var ctgryId=document.getElementById('refCtgry').value;	
					
				var url='hdiits.htm?actionFlag=fms_checkMetaDataForCorr&fromdraftFlag=Y&pkval='+id+'&locCode='+'${locId}'+'&branch='+'${branchId}'+'&departmentId='+'${deptId}'+'&CategoryTemplateMpgCode='+ctgryId;
				//alert(url);
				window.open(url,'test',urlStyle);
				
			}
		}
		
		if(flag==true)
		{
			alert('please select atleast one draft to approve or this draft is approved');
			return;
		}
	
	}
	else{
	
		alert('this draft is approved');
		return;
	}
	
}


function submitForm()
{

	var action='${contextPath}/hdiits.htm?actionFlag=fetchCorrDraft&corrId=${corrId}&pkval=${corrId}';	
	
	
	
	var ed = tinyMCE.get('editor1');
	ed.setContent('');
	document.getElementById('testArea').value='';
	document.getElementById("filedraftForm").method="post";
	document.getElementById("filedraftForm").action=action	
	
	document.getElementById("filedraftForm").submit();
}



function doNotIssueDraft(srNo,src,refDocId){
		clearAllRadio();
		document.getElementById('refCtgry').value=refDocId;	
		if(src.checked == true)
		{			
			if(document.getElementById("pkvalForDoNotIssue").value=='')
				document.getElementById("pkvalForDoNotIssue").value=srNo
			else
				document.getElementById("pkvalForDoNotIssue").value=document.getElementById("pkvalForDoNotIssue").value+','+srNo;
		}else
		{			
			var value=document.getElementById("pkvalForDoNotIssue").value;				
			var index=value.lastIndexOf(srNo);		
			value1=value.substring(0,index-1);			
			if(value1==''){
			var index2=value.indexOf(',');
			//alert('second index'+index2);
			
			if(index2==-1)	
				value2='';
			else
				value2=value.substring(index2+1,value.length);
				
			//alert('value2'+value2);
			}
			else{					
					var index1=value.indexOf(',',index);
					if(index1==-1)
						value2='';
					else
						value2=value.substring(index1,value.length);					
				}
			
			value=value1+value2;
			
			document.getElementById("pkvalForDoNotIssue").value=value;		
		}	
}

/*function doNotIsuueDraftAction()
{
	var ed = tinyMCE.get('editor1');
	ed.setContent('');
	document.getElementById('testArea').value='';

	var radioObj = document.getElementsByName('doNotIssueRadio');
	var radLength = document.getElementsByName('doNotIssueRadio').length;
	var flag=true;
	for(var cnt=0;cnt<radLength && flag==true ;cnt++)
	{
		if(radioObj[cnt].checked == true)
		{
			flag=false;
			var id=radioObj[cnt].value;	
			alert('id of do not issue::'+id);
			document.getElementById('pkvalForDoNotIssue').value=id
			var action="${contextPath}/hdiits.htm?actionFlag=doNotIssueDraft";
			document.getElementById("filedraftForm").method="post";
			document.getElementById("filedraftForm").action=action;
			alert(action);
			document.getElementById("filedraftForm").submit();
			
		}
	}
	
	if(flag==true)
	{
		alert('please select atleast one draft to DONOT issue else this is pending draft then first approve it');
		return;
	}
		
}*/

function doNotIsuueDraftAction()
{

	var action="${contextPath}/hdiits.htm?actionFlag=doNotIssueDraftForCorr";
	var id=document.getElementById("pkvalForDoNotIssue").value;
	//alert('doNotIsuue the content of ::'+id);
	if(confirm("<fmt:message key="WF.SureDoNotIssue" bundle="${wfLables}"></fmt:message>"))
	{
		if(document.getElementById("pkvalForDoNotIssue").value=='')
		{
			alert('<fmt:message key="WF.SelectDoNotIssueDraft" bundle="${wfLables}"></fmt:message>');
			return;
		}
		
		
		
		var ed = tinyMCE.get('editor1');
		ed.setContent('');
		document.getElementById('testArea').value='';
		
		document.getElementById("filedraftForm").method="post";
		document.getElementById("filedraftForm").action=action;	
		document.getElementById("filedraftForm").submit();
	}
}

function EscapeChar(strInput)
{
  // replace special characters that will error out in xml
  strInput=strInput.replace(/&/g,"&");  //replace & with &
  strInput=strInput.replace(/</g,"<");	//replace < with <
  strInput=strInput.replace(/>/g,">");	//replace > with >
  strInput=strInput.replace(/"/g,"");	//replace " with 
  strInput=strInput.replace(/'/g,"&apos;");	//replace ' with &apos;
  
  return(strInput);
}
function clearAllRadio()
{
	var radioObj = document.getElementsByName('approveRadio');
	var radLength = document.getElementsByName('approveRadio').length;
	document.getElementById('pkval').value='';
	for(var cnt=0;cnt<radLength ;cnt++)
	{
		if(radioObj[cnt].checked == true)
		{
				radioObj[cnt].checked = false;
		}
	}

}
function chageRefDocTemplateDetails()
{
	var val=document.getElementById("pkvalForDoNotIssue").value;
	document.getElementById('fileTabDtlsIds').value=document.getElementById("pkvalForDoNotIssue").value;
	
	var action='${contextPath}/hdiits.htm?actionFlag=updateRefDocTemplateDetails&fromCorrDraftFlag=yes&fileTabDtlsIds='+val;
	alert('action'+action);
	var ed = tinyMCE.get('editor1');
	ed.setContent('');
	document.getElementById('testArea').value='';
	
	document.getElementById("filedraftForm").method="post";
	document.getElementById("filedraftForm").action=action;	
	document.getElementById("filedraftForm").submit();
}
--></script>
	<hdiits:form name="filedraftForm" validate="true" action="./hdiits.htm?insertAction" encType="multipart/form-data">
	<hdiits:hidden name="attachmentIdDraft"/>
	<hdiits:hidden name="corrId" default="${corrId}"/>
	<hdiits:hidden name="attchmentId" default="${resultMap.attachmentId}"/>
	<hdiits:hidden name="pkval"/>
	<hdiits:hidden name="fileTabDtlsIds"/>
	<hdiits:hidden name="pkvalForDoNotIssue"/>
	<hdiits:hidden name="draftCreateFlag"/>
	<hdiits:hidden name="saveFlag"/>
	<hdiits:hidden name="draftApproveFlag"/>
	<hdiits:hidden name="flagForSave" default="PENDING"/>
	<hdiits:hidden name="tabDtlId"/>
	<hdiits:hidden name="refCtgry"/>
	<hdiits:hidden name="draftRefDocFlag"/>
	<hdiits:hidden name="ComOrSubjectDraftFlag" default="subject"/>

	<fmt:message key="WF.Select" bundle="${wfLables}" var="select"></fmt:message>
	<fmt:message key="WF.PREPARETEMP" bundle="${wfLables}" var="prepare"></fmt:message>
	
	
	<table>
						<tr>					
							<td style="border:none" width="15%">
								<hdiits:radio name="radio" id="Subject" value="Subject" captionid="WF.RADIOSUBJECT" bundle="${wfLables}" default="Subject" onclick="ajaxfunction('subject')" />
							</td>					
							<td style="border:none" width="15%">	
								<hdiits:radio name="radio" id="Common" value="Common" captionid="WF.RADIOCOMMON" bundle="${wfLables}" onclick="ajaxfunction('common')" />
							</td>					
							<td></td><td></td>
							<td class="fieldLabel" width="25%">
								<hdiits:caption  captionid="WF.TEMPLATETYPE" bundle="${wfLables}"/>
							</td>
							<td  width="25%">
								<hdiits:select name="cmbTemplate" mandatory="true" validation="sel.isrequired" captionid="WF.TEMPTYPE" bundle="${memoLables}">
									<hdiits:option value="-1">${select}</hdiits:option>
										<c:forEach items="${resultMap.templateList}" var="template">
											<hdiits:option value="${template.srNo}">${template.templateType}</hdiits:option>
										</c:forEach>
								</hdiits:select>
							</td>		
							<td>
									<hdiits:text id="testArea" name="htmlContent" maxlength="2000" style="display:none"></hdiits:text>
							</td>
						</tr>
						
						
				
				</table>
				<c:if test="${not outBoxFlag}">
				
				<table width="100%">
						<tr>					
						<td width="50%">
							<table>
							<tr>
																	
							
							<td width="20%">						
								<hdiits:a href="javascript:prepareTemplate()" bundle="${wfLables}" captionid="WF.PREPARE" id="btnPrepare"></hdiits:a>
							</td>
							<td width="10%"></td>
							<td width="20%">
								<hdiits:a href="javascript:saveChanges()" bundle="${wfLables}" captionid="WF.SAVE" id="btnSave"></hdiits:a>
								<!--<hdiits:button name="btnSave" id="btnSave" type="button" captionid="WF.SAVE" bundle="${wfLables}" onclick="saveChanges()" />-->
							</td>
							<td width="5%"></td>		
							
							<td width="25%">						
								<hdiits:a href="javascript:approveDraftAction()" bundle="${wfLables}" captionid="WF.APPROVE" id="approveButton"></hdiits:a>
							</td>
							
							<td width="5%"></td>
											
							<td width="25%">						
								<hdiits:a href="javascript:referenceDoc()" bundle="${wfLables}" captionid="WF.METADATA" id="reference"></hdiits:a>
							</td>
							
						
							</tr>
							</table>						
						</td>
						
						<td width="50%" align="right"> 
						<table>
							<tr>
							<td width="20%">						
								<hdiits:a href="javascript:doNotIsuueDraftAction()" bundle="${wfLables}" captionid="WF.DONOTISSUE" id="doNotIssueButton"></hdiits:a>
							</td>																	
						
							<c:if test="${approved eq 'approved'}">
								<td width="30%">						
									<hdiits:a href="javascript:chageRefDocTemplateDetails()" bundle="${wfLables}" captionid="WF.PUBTOREFDOC" id="btPushToRefDoc"></hdiits:a>
								</td>	
							</c:if>
							<td width="20%">						
								<hdiits:a href="javascript:showHistory()" bundle="${wfLables}" captionid="WF.HISTORY" id="history"></hdiits:a>						
							</td>
													
							<td width="10%"></td>					
							<td width="20%">						
								<hdiits:a href="javascript:enableManualControl()" bundle="${wfLables}" captionid="WF.Manual" id="manual"></hdiits:a>						
							</td>		 
								<!-- 				
							<td width="15%">						
								<hdiits:a href="javascript:exportToPdf()" bundle="${wfLables}" caption="EXPORT TO PDF" id="pdf"></hdiits:a>
							</td>	
								 -->			
							
							</tr>			
						
						</table>
						</td>
					</tr>
					</table>
				</c:if>
				

		<c:set var="appCount" value="0"/>
		<c:set var="doNotIssueCount" value="0"/>
		
		<display:table list="${fmsFileTabDtls}" pagesize="12" requestURI="" id="row"  style="width:100%"  >
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML">
			
			
			<c:if test="${row.status eq 'PENDING'}">
				<c:if test="${resultMap.selectedSrNo eq row.srNo}">
					<input type="radio" name="approveRadio"  value="${row.srNo}" checked="checked" onclick="setDefaultValue('${row.refCtgryMpgId} ','${row.metaDataExist}')"/>					
					
					<script>
					
					document.getElementById('pkval').value='${row.srNo}';
					document.getElementById('flagForSave').value='${row.status}';
										
					document.getElementById('draftRefDocFlag').value='${row.metaDataExist}';
					document.getElementById('refCtgry').value='${row.refCtgryMpgId}';
					</script>
					
				</c:if>
				<c:if test="${resultMap.selectedSrNo ne row.srNo}">
					<input type="radio" name="approveRadio"  value="${row.srNo}" onclick="setDefaultValue('${row.refCtgryMpgId} ','${row.metaDataExist}')"/>
				</c:if>				
				
				
				</c:if>
				<c:if test="${row.status eq 'APPROVED'}">
				
					<c:if test="${resultMap.selectedSrNo eq row.srNo}">
						<hdiits:checkbox name="doNotIssueChkBox" id="doNotIssueChkBox${row.count}"  value="1" default="1" onclick="doNotIssueDraft('${row.srNo}',this,'${row.refCtgryMpgId}')"/>					
						
						<script>	
					document.getElementById('pkvalForDoNotIssue').value='${row.srNo}';				
					document.getElementById('flagForSave').value='${row.status}';
					///alert('${row.refCtgryMpgId}');
					document.getElementById('refCtgry').value='${row.refCtgryMpgId}';					
					</script>										
					</c:if>
					<c:if test="${resultMap.selectedSrNo ne row.srNo}">
						<hdiits:checkbox name="doNotIssueChkBox" id="doNotIssueChkBox${row.count}"  value="1"  onclick="doNotIssueDraft('${row.srNo}',this,'${row.refCtgryMpgId}')"/>					
					</c:if>											
					
				</c:if>
				
				<c:if test="${row.status eq 'DO NOT ISSUE'}">				
					<script>	
						document.getElementById('flagForSave').value='${row.status}';
					</script>	
				</c:if>
			
				
				</display:column>
			
			
			<c:if test="${row.status eq 'PENDING'}">
			<display:column  titleKey="WF.DraftName" headerClass="datatableheader"><a href="javascript:getDraftForAttachment(${row.tabRefId},${appCount},${row.srNo},'${row.status}','${row.refCtgryMpgId}','${row.metaDataExist}')" >${row.draftName} </a></display:column>
			<c:set var="appCount" value="${appCount+1}"></c:set>
			</c:if>
			
			<c:if test="${row.status eq 'APPROVED'}">
			<display:column  titleKey="WF.DraftName" headerClass="datatableheader"><a href="javascript:getDraftForAttachment(${row.tabRefId},${doNotIssueCount},${row.srNo},'${row.status}','${row.refCtgryMpgId}','${row.metaDataExist}')" >${row.draftName} </a></display:column>
			<c:set var="doNotIssueCount" value="${doNotIssueCount+1}"></c:set>
			</c:if>
			
			<c:if test="${row.status eq 'DO NOT ISSUE'}">
			<display:column  titleKey="WF.DraftName" headerClass="datatableheader"><a href="javascript:getDraftForAttachment(${row.tabRefId},${doNotIssueCount},${row.srNo},'${row.status}','${row.refCtgryMpgId}','${row.metaDataExist}')" >${row.draftName} </a></display:column>
			<c:set var="doNotIssueCount" value="${doNotIssueCount+1}"></c:set>
			</c:if>
			
			<display:column titleKey="WF.Date" headerClass="datatableheader">${row.date}</display:column>
			
			<display:column titleKey="WF.user" headerClass="datatableheader" >${row.userName}</display:column>			
			
			<display:column titleKey="WF.LstUpdatedUsr" headerClass="datatableheader" >${row.postName}</display:column>			
			
			<display:column titleKey="WF.Description" headerClass="datatableheader">${row.status}</display:column>
		
		</display:table>	
		
		<br>
		<br>
			<table width="100%" id="draftid">
				<tr>
					<td>
					<textarea style="width: 100%;" id="editor1" name="editor1" rows="25" cols="40" class="mceAdvanced">${resultMap.output}</textarea><br/>
					</td>
				</tr>
			</table>
			<center>
			<table  id="buttonTable">
			
			<tr>
			<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
			<c:if test="${not outBoxFlag}">
				<td><hdiits:button name="btnSav1e" id="btnSave1" type="button"
							captionid="WF.SAVE" bundle="${wfLables}" onclick="saveChanges()" />
				</td>
				</c:if>
				
			</tr>
			
			<tr>
			</tr>
			</table>
			</center>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>		
</hdiits:form>	


<%
}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>
<script>
	
		function enableManualControl()
		{			
			
			var radioObj = document.getElementsByName('approveRadio');
			var radLength = document.getElementsByName('approveRadio').length;
			var flag=true;
			for(var cnt=0;cnt<radLength && flag==true ;cnt++)
			{	
					if(radioObj[cnt].checked == true)
					{
						flag=false;
						var id=radioObj[cnt].value;
					}
			}
			
			if(flag==true)
			{
				alert('please select atleast one draft to approve');
				return;
			}
				
			//alert('id'+id);
			
			var ed = tinyMCE.get('editor1');
			var htmlContent = ed.getContent();
			htmlContent = '<?xml version="1.0" encoding="UTF-8"?><!DOCTYPE  document  PUBLIC  "specialEntities.dtd" '+subPath+'><html><body>'+ htmlContent + '</body></html>';
			var encodedHtml = encodeBase64(htmlContent);		
			
			var action='${contextPath}/hdiits.htm?actionFlag=FMS-givemanualcontrolsForCorr&attachmentId='+id+'&corrId=${corrId}';		
			
			var ed1 = tinyMCE.get('editor1');
			ed1.setContent('');
			
			document.getElementById('testArea').value=encodedHtml;
			
			document.getElementById("filedraftForm").method="post";
			document.getElementById("filedraftForm").action=action;	
			document.getElementById("filedraftForm").submit();	
		}
			
		function referenceDoc()
		{	
		var urlStyle ='width=600,height=750,toolbar=no,menubar=no,location=no,top=150,left=200'; 
		var ctgryId=document.getElementById('refCtgry').value;	
		///alert(document.getElementById('refCtgry').value);		
		var url='hdiits.htm?actionFlag=fms_displayRefDocs&fromdraftFlag=Y&locCode='+'${locId}'+'&branch='+'${branchId}'+'&departmentId='+'${deptId}'+'&CategoryTemplateMpgCode='+ctgryId;
		//alert(url);
		window.open(url,'test',urlStyle);
		}
	
		function showHistory(){		
			var count='${resultMap.selectedSrNo}';		
			if(count=='')
			{
				alert('Atleast one draft should be selected for history');
				return;
			}
			var corrId = document.getElementById('corrId').value;
			var urlStyle ='width=500,height=450,toolbar=no,menubar=no,location=no,top=150,left=200'; 
			
			var url='hdiits.htm?actionFlag=getHistoryDocumentForCorr&attachemntId='+document.getElementById('attchmentId').value+'&corrId='+corrId;
			window.open(url,'test',urlStyle);
		}
	
	function clearAllCheckBox()
	{
	
	
		try{
			
			if(document.forms[0].approveChkBox!=undefined)
			{				
				var arrLength = document.forms[0].approveChkBox.length;
								
				if(arrLength!=undefined){
					for(var len=0; len < eval(arrLength); len++)
					{		
						if(document.forms[0].approveChkBox[len].checked == true)
						{
							document.forms[0].approveChkBox[len].checked=false;			
						}
					} 
				}else{
				
					document.forms[0].approveChkBox0.checked=false;
				
				}
			}
		
			if(document.forms[0].doNotIssueChkBox!=undefined)
			{
				var arrLength = document.forms[0].doNotIssueChkBox.length;
				if(arrLength!=undefined){
					for(var len=0; len < eval(arrLength); len++)
					{		
						if(document.forms[0].doNotIssueChkBox[len].checked == true)
						{
							document.forms[0].doNotIssueChkBox[len].checked=false;			
						}
					} 
				}else{
					document.forms[0].doNotIssueChkBox0.checked=false;
				}
			}		
		}catch(e)
		{
			//alert(document.forms[0].approveChkBox0);
			
		}	
	}
</script>
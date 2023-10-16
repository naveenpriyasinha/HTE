<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<c:set var="length" value="1"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="size" value="${resValue.size}"></c:set>
<c:set var="expandFlag" value="${resValue.expandFlag}"></c:set>
<c:set var="fileUrl" value="${resValue.fileUrl}"></c:set>
<c:set var="pPost" value="${resValue.pPost}"></c:set>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript">
function deleteBranchComboValues()
{	
	   var UserEntries=document.getElementById("brnch");
	   for(var i=1;i<UserEntries.length;i++)
	   {
				UserEntries.remove(i);
				i = i - 1;
	   }
}
function loadBrnchCombo()
	{		
		var comboid = document.getElementById('brnch');		
		locObj = document.getElementById('LocCode');
		if(locObj.value!='')
		{
			locCode = locObj.value;
			//alert(locCode);
			try
		   	{   
		   	// Firefox, Opera 8.0+, Safari    
		   		xmlHttp=new XMLHttpRequest();    
		   	}
			catch (e)
			{    // Internet Explorer    
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
			var url = "hdiits.htm?actionFlag=getBranches&locCode="+locCode;			
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						deleteBranchComboValues();
						var XMLDoc=xmlHttp.responseXML.documentElement;						
						var Branch = XMLDoc.getElementsByTagName('locIdMapped');
						//alert(Category)
						if(Branch.length==0)
						{						
							alert("No Branches Found...");
							return;
						}					
						for (var i=0 ; i < Branch.length ; i++ )
						{
							bCode = Branch[i].childNodes[0].text;							
		  					bName = Branch[i].childNodes[1].text;	   						
		  					var element = document.createElement('option');   				
		     				element.text = bName;
		     				element.value = bCode;	
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
							xmlHttp.open("POST", encodeURI(url) , false);    
							xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
							xmlHttp.send(encodeURIComponent(null));
		}
}

function showDocument()
{		
		document.getElementById('viewTable').style.display = 'none'; 
		document.getElementById('viewLink').style.display = 'none';   
		document.getElementById('MsgTab').style.display = 'none';
		document.getElementById('viewCmbTab').style.display = 'none';
		
		deleteComboValues();
		var srno=0;
		locObj = document.getElementById('LocCode');
		locCode = locObj.value;								
		try
    	{   
    		// Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{    // Internet Explorer    
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
        var url = "${contextPath}/hdiits.htm?actionFlag=getDocument&llocode="+locCode+"&lbranchId="+document.forms[0].brnch.value;            
        xmlHttp.onreadystatechange = function()
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{	
			    	var XMLDoc=xmlHttp.responseXML.documentElement;	
					var tableentries = XMLDoc.getElementsByTagName('locationIdMapped');	
					var locId=document.getElementById('LocCode').value;
					var branchId=document.getElementById('brnch').value;									
					var Size=tableentries[0].childNodes[0].text;	
					
					var totalRows=document.getElementById("viewTable").rows.length;
					for(i=1;i<eval(totalRows);i++)
					{							
						var trow=document.getElementById('viewTable');      		
						trow.deleteRow(1);
					}	
					var totalRows1=document.getElementById("viewLink").rows.length;
					for(i=1;i<eval(totalRows1);i++)
					{							
						var trow=document.getElementById('viewLink');      		
						trow.deleteRow(1);
					}	
					var totalRows2=document.getElementById("MsgTab").rows.length;
					for(i=1;i<eval(totalRows2);i++)
					{							
						var trow=document.getElementById('MsgTab');      		
						trow.deleteRow(1);
					}	
					if(Size<=0)									
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
						var docIdEntries=XMLDoc.getElementsByTagName('docId');
						var docNameEntries=XMLDoc.getElementsByTagName('docName');						
						var flagEntries=XMLDoc.getElementsByTagName('lflag');
						var srNoEntries=XMLDoc.getElementsByTagName('srNo');
						var lnameEntries=XMLDoc.getElementsByTagName('lname');
						var lbranchnameEntries=XMLDoc.getElementsByTagName('lbranchname');	
						for(i = 0 ; i < docIdEntries.length ; i++ )
	   					{	   						   							
	   						document.getElementById('viewTable').style.display = '';   					
	   						DocId = docIdEntries[i].childNodes[0].text;
	   						DocName = docNameEntries[i].childNodes[0].text;	   					
	   						srno++;	
	 						var a=document.getElementById('viewTable').insertRow();
			     			var col1=a.insertCell(0);
							var col2=a.insertCell(1);	
							col1.align="left";
							col2.align="left";							
							col1.innerHTML=srno;
							col2.innerHTML=DocName;	 
							
							//Message to b displayed...
	 						docId=	docIdEntries[i].text;			
	   						docName = docNameEntries[i].text;  
							flag = flagEntries[i].text;  	
							srNo = srNoEntries[i].text; 
							lname=lnameEntries[i].text;  
							lbranchname=lbranchnameEntries[i].text;  
							document.forms[0].hiddenDocId.value=docId;
						 } 	
							 document.forms[0].hiddensrNo.value=srNo;							 					 
					}
					/*if(flag=="true")
					{
						alert("For Location="+lname+" And Branch=" + lbranchname +" Document==>"+ docName+ " Already Exists!!");
					}
					else 
					{
   						 alert("For Location="+lname+" And Branch=" + lbranchname + " No Document Mapped!!!");  						
   					}*/						
					document.getElementById('MsgTab').style.display='';
					var a=document.getElementById('MsgTab').insertRow();
	     			var col1=a.insertCell(0);	
	     			col1.align="center";	     			
	     			col1.innerHTML = "Subjects Mapped for Location "+ lname + " and Branch "+ lbranchname;
	     						
	     					  				
					document.forms[0].hiddenflag.value=flag;					  
					document.getElementById('viewLink').style.display = '';   
					var a=document.getElementById('viewLink').insertRow();
	     			var col1=a.insertCell(0);					
					col1.align="center";	
					col1.innerHTML = "<a href='#' style=\"cursor:hand\" onClick=\"ajaxFuncToMapDocument()\">Click Here to Map Subject For Selected Branch.</a>";					
				 }				
		  }
	  }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
		xmlHttp.send(encodeURIComponent(null));
		return true;
}
function deleteComboValues()
{	
	   var UserEntries=document.getElementById("Module");
	   for(var i=1;i<UserEntries.length;i++)
	   {
				UserEntries.remove(i);
				i = i - 1;
	   }
}
function ajaxFuncToMapDocument()
{			
	document.getElementById('viewCmbTab').style.display = '';  
	document.getElementById("Module").focus();
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
	
 	var url = "${contextPath}/hdiits.htm?actionFlag=loadModule"; 	
	xmlHttp.onreadystatechange = function()                                                                                                       
	{			
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{	
				deleteComboValues();
				var text;
            	var z=document.getElementById("Module");								
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
function deleteDocumentComboValues()
{	
	   var UserEntries=document.getElementById("selDocumentId");
	   for(var i=1;i<UserEntries.length;i++)
	   {
				UserEntries.remove(i);
				i = i - 1;
	   }
}
function loadDocument()
{

	locObj = document.getElementById('LocCode');
	locCode = locObj.value;
	branchid = document.getElementById('brnch').value;	
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
	
 	var url = "${contextPath}/hdiits.htm?actionFlag=loadDocList&lmoduleId="+document.forms[0].selModule.value+"&llocCode="+locCode+"&lbranchid="+branchid; 	
	xmlHttp.onreadystatechange = function()                                                                                                       
	{			
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{	
				deleteDocumentComboValues();
				var text;
            	var z=document.getElementById("selDocumentId");								
				var XMLDoc=xmlHttp.responseXML.documentElement;																				
				var tableentries = XMLDoc.getElementsByTagName('docMapped');	
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

function insertData()
{
	locObj = document.getElementById('LocCode');
	locCode = locObj.value;		
	var url = "${contextPath}/hdiits.htm?actionFlag=mapDocsWithbranch&lbranchId="+document.forms[0].brnch.value+
	"&ldocId="+document.forms[0].selDocument.value+"&lmoduleId="+document.forms[0].selModule.value+
	"&llocId="+locCode+"&lflag="+document.forms[0].hiddenflag.value+
	"&lsrNo="+document.forms[0].hiddensrNo.value;	
	document.forms[0].action=url;		
	document.forms[0].submit();	
}

function SrchReset()
{		
	document.forms[0].txtLocName.value = '';
	document.forms[0].selModule.value = '0';
	document.forms[0].selDocument.value = '0';		
	resetCombo(document.getElementById('brnch'));		
	document.getElementById('viewTable').style.display = 'none';	
	document.getElementById('viewLink').style.display = 'none';
	document.getElementById('MsgTab').style.display = 'none';	    	
}

function resetCombo(comboid)
{
	if(comboid.options.length>'0')
	{
		for(var i=comboid.options.length;i>0;i--)
		{
			comboid.remove(i);
		}
	}
	comboid.selectedIndex='0';
}
</script>

<fmt:setBundle basename="resources.workflow.workFlowLables" var="resourceLables" scope="request"/>
<hdiits:form name="wfLocBranchDocForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
<hdiits:hidden name="hiddLocId" default="0"/>
<hdiits:hidden name="hiddenDocId" default="0"/>	
<hdiits:hidden name="hiddenflag"/>
<hdiits:hidden name="hiddensrNo" default="0"/>
<br/>
<hdiits:fieldGroup id="fldgrp" titleCaption="Complete File/Correspondence Search" expandable="true" collapseOnLoad="${expandFlag}">
	<table class="tabtable">
			<tr>
				<td class="fieldLabel">
					Location:
				</td>	
				<td class="fieldLabel">
					<hdiits:text  id="txtLocName" name="txtLocName"  />
					<input type="hidden" name="LocCode" id="LocCode"/>
					<span id="indicatorRegion1" style="display:none;">
					<img src="./images/busy-indicator.gif"/>
					</span>
					<ajax:autocomplete
				    source="txtLocName"
				    target="LocCode"
				    baseUrl="hdiits.htm?actionFlag=wfloadLocation"
				    parameters="locName={txtLocName}"
				    className="autocomplete"
				    minimumCharacters="2"
				    indicator="indicatorRegion1"
				    postFunction="loadBrnchCombo"				   
				    />
				</td>
															
				<td class="fieldLabel">
					Branch:
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="brnch"  name="brnch" onchange="showDocument()">
					<hdiits:option value="Select"> Select </hdiits:option>
					</hdiits:select>
				</td>	
			</tr>			
		</table>
	
		<table class="tabtable">		
			<table id="viewLink" border="0" width="50%" style="display:none" align="left">
				<tr>					
				</tr>
			</table>						
		</table>
		
		<table class="tabtable" id="viewCmbTab" style="display:none"> 
			<tr>
				<td class="fieldLabel">Module:</td>	
				<td>
					<hdiits:select name="selModule" id="Module" onchange="loadDocument()">
						<hdiits:option value="0"> Select </hdiits:option>						
					</hdiits:select>			
				 </td>		
			</tr>			
			<tr>
				<td class="fieldLabel">Subject:</td>		
			 	 <td>
					<hdiits:select name="selDocument" id="selDocumentId">
						<hdiits:option value="0"> Select </hdiits:option>						
					</hdiits:select>			
				 </td>			 
			</tr>
		</table>						
		
	<br><br>
	<table border="0" width="90%" align="center" id="MsgTab" style="display:none">
		<tr>					
		</tr>		
	</table>
	<table id="viewTable" border="2" width="90%" style="display:none" align="center">
		<tr>
			<th class="datatableheader">SrNo</th>
			<th class="datatableheader">Subjects</th>
		</tr>
	</table>		
	
	<br><br>	
	<table><tr></tr></table>
	<table class="tabtable" align="center">
			<tr>		
			<td colspan="6" style="text-align: center">			
				<hdiits:button name="reset1" type="button" value="Reset" onclick="SrchReset()" />
				<hdiits:button name="submit1" type="button" value="Submit" onclick="insertData()" />
				<span id="indicatorRegion2" style="display: none;">
					<img src="./images/busy-indicator.gif"/>
				</span>
			</td>
			</tr>
	</table>	
</hdiits:fieldGroup>	
<br />
	<table id="titleTable" class="datatable" style="display: none;">		
	</table>
	<br />
	<table id="dataTable" class="datatable" style="display: none;" >
	<tr>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.SRNO" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.SUB" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.DESC" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.NUM" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.CREATOR" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.OWNER" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.DUEDATE" bundle="${resourceLables}" captionLang="en_US"/>
				</td >
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.PRIORITY" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.STATUS" bundle="${resourceLables}" captionLang="en_US"/>
				</td>
				<td class="datatableheader"  >
				<hdiits:caption captionid="WFS.STAGE" bundle="${resourceLables}" captionLang="en_US"/>
				</td>	
			</tr>
	</table>
	<br />
	
	<table id="pageTable" class="tabtable" style="display: none;">
		<tr><td style="text-align: center" class="datatableheader"></td></tr>
		<tr><td style="text-align: center" class="datatableheader"></td></tr>
	</table>
	
<div class="shadow" id="shadow" style="visibility: hidden">
	<div class="output" id="output" onclick="mouseClick()"></div>
</div>
</hdiits:form>	
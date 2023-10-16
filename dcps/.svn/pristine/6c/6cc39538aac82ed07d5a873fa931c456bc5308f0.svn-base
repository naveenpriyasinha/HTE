<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="LocationList" value="${resValue.cmnLocationMstList}"></c:set>
<c:set var="Message" value="${resValue.Message}"></c:set>
<c:set var="WfModuleList" value="${resValue.WfModuleList}"></c:set>
<!--<c:set var="subjectType" value="${resValue.subjectType}"></c:set>-->
<c:set var="Message" value="${resValue.Message}"></c:set>
<hdiits:form name="updatesubjectForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
<script language="javascript">
if(('${Message}'!='')&&('${Message}'!=null))
{
	alert('${Message}');
	var url1 ="hdiits.htm?actionFlag=fms-getDocsForModule"; 
	document.forms[0].action = url1;

	document.forms[0].submit();
}
</script>


<br />
<br />


<!--<hdiits:fieldGroup id="fldgrp2" titleCaption="Update Subject Entry">-->
<table class="tabtable">
			<tr>
				<td class="datatableheader" colspan="4" >
					Update Subject Details
				</td>
				</tr>
				<tr>
								<td class="fieldLabel1">
				Select Module:
				</td>
				<td class="fieldLabel1">
					<hdiits:select  id="module1"  name="module1" onchange="loadDocCombo(this)">
					<hdiits:option value="Select"> Select </hdiits:option>
					<c:forEach items="${WfModuleList}" var="WfModuleList">
					<hdiits:option value="${WfModuleList.moduleId}"> ${WfModuleList.moduleName}</hdiits:option>
					</c:forEach>
					</hdiits:select>
				</td>
				
				
					
					<td class="fieldLabel">
					Select Doc:
				</td>
				<td class="fieldLabel">
					<select  id="Doc"  name="Doc" onchange="getDetails()">
					<option value="Select"> Select </option>
					</select>
				</td>
		</tr>
		
			
			<tr>
				<td class="fieldLabel">
					Subject Name(English):
				</td>
				<td class="fieldLabel">
					<hdiits:text name="subNameEng" id="subNameEng" mandatory="true"/>
				</td>
			
				<td class="fieldLabel">
					Subject Name(Gujarati):
				</td>
				<td class="fieldLabel">
					<hdiits:text name="subNameGuj" id="subNameGuj" mandatory="true"/>
				</td>
			</tr>
			<tr>
				<td class="fieldLabel">
					Subject Description(English):
				</td>
				<td class="fieldLabel">
					<hdiits:text name="subDescEng" id="subDescEng" mandatory="true"/>
				</td>
			
				<td class="fieldLabel">
					Subject Description(Gujarati):
				</td>
				<td class="fieldLabel">
					<hdiits:text name="subDescGuj" id="subDescGuj" mandatory="true"/>
				</td>
			</tr>
			<tr>
				<td class="fieldLabel">
					Hierarchy Type:
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="hieTyp"  name="hieTyp" mandatory="true">
					<hdiits:option value="Select"> Select </hdiits:option>
					<hdiits:option value="1"> Post Based </hdiits:option>
					<hdiits:option value="2"> Role Based </hdiits:option>
					<hdiits:option value="3"> Marked </hdiits:option>
					</hdiits:select>
				</td>
			
				<td class="fieldLabel">
					Select Subject Type:
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="subTyp"  name="subTyp" mandatory="true">
					<hdiits:option value="Select"> Select </hdiits:option>
					<hdiits:option value="1"> Common </hdiits:option>
					<hdiits:option value="2"> Subject Specific </hdiits:option>
					<hdiits:option value="3"> Branch Specific </hdiits:option>
					</hdiits:select>
				</td>
			
				
			</tr>
			
			<tr>
				<td class="fieldLabel">
					Subject Tricode:
				</td>
				<td class="fieldLabel">
					<hdiits:text name="tricode" id="tricode" mandatory="true"/>
				</td>
			
				<td class="fieldLabel">
					Restriction created:
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="restrict"  name="restrict" mandatory="true">
					<hdiits:option value="Select"> Select </hdiits:option>
					<hdiits:option value="0"> No </hdiits:option>
					<hdiits:option value="1"> Yes </hdiits:option>
					</hdiits:select>
				</td>
			</tr>
			<tr>
				<td class="fieldLabel">
					Self initialized:
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="selfInit"  name="selfInit" mandatory="true">
					<hdiits:option value="Select"> Select </hdiits:option>
					<hdiits:option value="0"> No </hdiits:option>
					<hdiits:option value="1"> Yes </hdiits:option>
					</hdiits:select>
				</td>
				<td></td><td></td>
			</tr>
			
			
			<tr style="visibility: hidden">
			<td class="fieldLabel">
					Select Priority:
				</td>
				<td class="fieldLabel">
					<select  id="prior"  name="prior" >
					<option value="Select"> Select </option>
					
					<option value="1"> Immidiate </option>
					<option value="2"> Urgent </option>
					<option value="3"> Today </option>
					<option value="4"> Dateset </option>
					<option value="5" selected="selected"> Routine </option>
					</select>
				</td>
				
				<td class="fieldLabel">
					Select Workflow Type:
				</td>
				<td class="fieldLabel">
					<select  id="wfTyp"  name="wfTyp"  >
					<option value="Select"> Select </option>
					<option value="0" selected="selected"> Ordinary </option>
					<option value="1"> Alternate </option>
					</select>
				</td>
				</tr>
				
		<tr>
			<td colspan="4" style="text-align: center">
				<hdiits:button name="Update" type="button" value="Update" onclick="submitSubject()" />
			</td>
			</tr>
			</table>
			
			
<!--					</hdiits:fieldGroup>-->
					</hdiits:form>
					
<script type="text/javascript">		
var empIdLst = '';
var empIdArry='';
var updateFlg=0;
var finalIdArr = new Array();
var finalPostArr = new Array();
var finalDueDayArr = new Array();
var finalLevelArr = new Array();

function loadDocCombo(Obj)
	{
		var comboid = document.getElementById('Doc');

		var moduleIdCombo= document.getElementById('module1');
		comboid.selectedIndex='0';
		
		if(comboid.options.length>'0')
		{
			for(var i=comboid.options.length;i>0;i--)
			{comboid.remove(i);}
		}


			
		if(Obj.selectedIndex!='0')
		{
			
			 var Code = Obj[Obj.selectedIndex].value;
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
			var url;
			
			url = "hdiits.htm?actionFlag=getDocsForLocation&moduleId="+Code+"&flag=1";		
				
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						
						var Category = XMLDoc.getElementsByTagName('Doc');
						if(Category.length==0)
						{
						
							alert("No Docs found...");
							return;
						}
						if(comboid.options.length>'0')
						{
							for(var i=comboid.options.length;i>0;i--)
							{comboid.remove(i);}
						}
						
						for (var i=0 ; i < Category.length ; i++ )
						{
							DocId = Category[i].childNodes[0].text;							
		  					DocName = Category[i].childNodes[1].text;	   						
		  					var element = document.createElement('option');   				
		     				element.text = DocName;
		     				element.value = DocId;	
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


function getDetails()
{
	//alert('get details');
	var docObj=document.getElementById('Doc');
	var moduleIdCombo= document.getElementById('module1');
	if(docObj.selectedIndex!='0')
	{
		
		
		
		var moduleId= moduleIdCombo[moduleIdCombo.selectedIndex].value;
		var docId = docObj[docObj.selectedIndex].value;
		//alert("moduleId"+moduleId);
		//alert("docId"+docId);
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
		var url = "hdiits.htm?actionFlag=getDocDtlsForModuleId&moduleId="+moduleId+"&docId="+docId;	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					//alert('in change');
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Category = XMLDoc.getElementsByTagName('Doc');
					if(Category.length==0)
					{
					
						alert("No Docs found...");
						return;
					}
					for (var i=0 ; i < Category.length ; i++ )
					{
						subjectType=Category[i].childNodes[0].text;
						DocName = Category[i].childNodes[1].text;
						DocNameGuj=Category[i].childNodes[2].text;
						triCode=Category[i].childNodes[3].text;
						desc=Category[i].childNodes[4].text;
						descGuj=Category[i].childNodes[5].text;	
						hierarchyType=Category[i].childNodes[6].text;	
						restriction=Category[i].childNodes[7].text;   	
						selfInitiated=Category[i].childNodes[8].text;					
	  					//var element = document.createElement('option');   				
	     				//element.text = DocName;
	     				//element.value = DocId;	
	     			//	var id1= 
	     			
	     				document.getElementById('subDescEng').value=desc;
	     				document.getElementById('subNameEng').value=DocName;
	     				document.getElementById('subNameGuj').value=DocNameGuj;
	     				document.getElementById('subDescGuj').value=descGuj;
	     				
	     				document.getElementById('subTyp').value=subjectType;
	     				document.getElementById('tricode').value=triCode;
	     				document.getElementById('hieTyp').value=hierarchyType;
	     				document.getElementById('restrict').value=restriction;
	     				document.getElementById('selfInit').value=selfInitiated;
	     				
	     				
					}  
				}
			}
		}
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
}
}
function submitSubject()
{	
	
	var docObj=document.getElementById('Doc');
	var docId;
	if(docObj.selectedIndex!='0')
	{
		docId = docObj[docObj.selectedIndex].value;
	}
	if(document.getElementById('subNameEng').value=='')
	{
		alert('Please enter Subject name');
		document.getElementById('subNameEng').focus();
	}
	else if(document.getElementById('subNameGuj').value=='')
	{
		alert('Please enter Subject name');
		document.getElementById('subNameGuj').focus();
	}

	else if(document.getElementById('subDescEng').value=='')
	{
		alert('Please enter Subject description');
		document.getElementById('subDescEng').focus();
	}
	else if(document.getElementById('subDescGuj').value=='')
	{
		alert('Please enter Subject description');
		document.getElementById('subDescGuj').focus();
	}
	
	else if(document.getElementById('hieTyp').selectedIndex=='0')
	{
		alert('Please select hierarchy type for Subject');
		document.getElementById('hieTyp').focus();
	}
	
	else if(document.getElementById('subTyp').selectedIndex=='0')
	{
		alert('Please select subject type');
		document.getElementById('subTyp').focus();
	}
	
	else if(document.getElementById('tricode').value=='')
	{
		alert('Please enter tricode for Subject');
		document.getElementById('tricode').focus();
	}
	else if(document.getElementById('tricode').value.length!=3)
	{
		alert('Tricode must be of 3 letters');
		document.getElementById('tricode').focus();
	}
	else if(document.getElementById('restrict').selectedIndex=='0')
	{
		alert('Please specify whether restriction is created for Subject');
		document.getElementById('restrict').focus();
	}
	else if(document.getElementById('selfInit').selectedIndex=='0')
	{
		alert('Please specify whether subject is self initialized');
		document.getElementById('selfInit').focus();
	}
	
	else
	{
		//alert('in else');
		var url = "hdiits.htm?actionFlag=updateDocumentDetails&docId="+docId; 
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}

</script>




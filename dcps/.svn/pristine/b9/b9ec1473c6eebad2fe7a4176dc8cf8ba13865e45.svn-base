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
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>

<fmt:setBundle basename="resources.workflow.FMS_TEMPLables"	var="fmsTempLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="fmsAttribList" value="${resultMap.fmsAttribList}"></c:set>

<hdiits:form name="frmFmsTmpAttrib" id="frmFmsTmpAttrib" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1"> 
				<hdiits:caption	captionid="FMS.INSERT" bundle="${fmsTempLables}" /> 
			</a>
		</li>
		<li class="selected">
			<a href="#" rel="tcontent2" onfocus="show(2)"> 
				<hdiits:caption	captionid="FMS.VIEW" bundle="${fmsTempLables}" /> 
			</a>
		</li>
	</ul>
	</div>
	<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent"><br>
	<br>
	<b><font style="font-size: 4;font-style: normal">
		<center>
			<hdiits:caption captionid="FMS.INSERTDETAILS" bundle="${fmsTempLables}" />
		</center>
		</font></b> <br>
	<br>
	<hr>
	<table align="center" border="0" class="tabtable">	
			<tr>
				<td align="right" style="border:none">
					<hdiits:caption	captionid="FMS.ATTRIBUTENAME" bundle="${fmsTempLables}" />
				</td>
			</tr>
			<tr>
				<td align="center" style="border:none">
					<hdiits:caption	captionid="FMS.ENGLISH" bundle="${fmsTempLables}" />
				</td>
				<td align="left" style="border:none">
					<hdiits:text name="txtAttribNameEng" captionid="FMS.ENGATTRIBUTENAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
				</td>
				<td align="center" style="border:none">
					<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
				</td>
				<td align="left" style="border:none">
					<hdiits:text name="txtDescEng" captionid="FMS.ENGDESC" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
				</td>
			</tr>
			<tr>
				<td align="center" style="border:none">
					<hdiits:caption	captionid="FMS.GUJARATI" bundle="${fmsTempLables}" />
				</td>
				<td align="left" style="border:none">
					<hdiits:text name="txtAttribNameGuj" captionid="FMS.GUJATTRIBUTENAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
				</td>
				<td align="center" style="border:none">
					<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
				</td>
				<td align="left" style="border:none">
					<hdiits:text name="txtDescGuj" captionid="FMS.GUJDESC" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
				</td>
			</tr>
			
			
			
		<tr></tr>
		<tr></tr>		
		<tr></tr>	
		<tr></tr>	
		<tr>
			<td align="center" style="border:none">
				<hdiits:caption captionid="FMS.DATATYPE" bundle="${fmsTempLables}" />
			</td>
			<td align="left" style="border:none">
				<hdiits:select name="selDataType" id="selDataTypeid" captionid="FMS.DATATYPE" bundle="${fmsTempLables}" validation="sel.isrequired"	mandatory="true" onchange="chkDateTime()">
						<option value="Select">Select</option>
						<option value="Integer" selected="true"><c:out value="INTEGER" /></option>
						<option value="Varchar"><c:out value="VARCHAR" /></option>
						<option value="DateTime"><c:out value="DATETIME" /></option>
				</hdiits:select>
			</td>
			<td align="center" style="border:none;" id="captionlenid">
				<hdiits:caption	captionid="FMS.LENGTH" bundle="${fmsTempLables}" />
			</td>
			<td align="left" style="border:none;">
				<hdiits:number name="txtLength" id="txtlenid" captionid="FMS.LENGTH" bundle="${fmsTempLables}" maxlength="4" validation="txt.isnumber" />
			</td>
		</tr>
	</table>
	<table align="center" border="0">
		<tr>
			<td align="center">
			<center>				
				<hdiits:button type="button" name="btnSubmit" value="SUBMIT" onclick="enterData()"/>				
			</center>
			</td>
		</tr>
	</table>		
	
	
	<hr>	
</div>

	<!-- Code For The View JSP --> 	
	<div id="tcontent2" class="tabcontent"><br>
	<br>
	<b>
	<font style="font-size: 4;font-style: normal">
		<center>
			<hdiits:caption captionid="FMS.VIEWDETAILS" bundle="${fmsTempLables}" />
		</center>
	</font>
	</b> 
	<br>
	<br>
	<hr>
	<table align=center border="0" class="tabtable">
		<tr>
			<td align="right" style="border:none">
				<hdiits:caption captionid="FMS.ATTRIBUTENAME" bundle="${fmsTempLables}" />
			</td>
			<td align="left" style="border:none">
				<hdiits:select name="selAttribName" id="selAttribNameid" captionid="FMS.ATTRIBUTENAME" validation="sel.isrequired" bundle="${fmsTempLables}" mandatory="true" onchange="ajaxFunctionView()">
					<option value="0">Select</option>
					<c:forEach  items="${fmsAttribList}" var="fmslookup">
							<option value='<c:out value="${fmslookup.attributeId}"/>' selected="true">
								<c:out value="${fmslookup.attributeName}" />
							</option>								
					</c:forEach>
				</hdiits:select>			
		 	</td>	
		</tr>
		<tr>			
			<td align="right" style="border:none">
				<hdiits:caption captionid="FMS.DESC" bundle="${fmsTempLables}"/>
			</td>
			<td align="left" style="border:none">
				<hdiits:text name="txtAttribDesc" captionid="FMS.DESC" bundle="${fmsTempLables}"/>
		 	</td>	
		 </tr>		 	
		 <tr>		
			<td align="right" style="border:none">
				<hdiits:caption captionid="FMS.DATATYPE" bundle="${fmsTempLables}"/>
			</td>
			<td align="left" style="border:none;">
				<hdiits:text name="txtAttribDatatypeView" captionid="FMS.DATATYPE" bundle="${fmsTempLables}" onblur="chkDataType()"/>
		 	</td>	
		 </tr>
		 <tr>
		 	<td align="right" style="border:none;" id="captionlenviewid">
				<hdiits:caption	captionid="FMS.LENGTH" bundle="${fmsTempLables}"/>
			</td>
			<td align="left" style="border:none;">
				<hdiits:number name="txtLengthView" id="txtlenviewid" captionid="FMS.LENGTH" bundle="${fmsTempLables}" maxlength="4" validation="txt.isnumber" />
			</td>
		</tr>
	</table>
	<table align="center" border="0">
		<tr>
			<td align="center">
			<center>				
				<hdiits:button type="button" name="btnUpdate" value="UPDATE" onclick="Update()"/>				
			</center>
			</td>
		</tr>
	</table>		
	<hr>
	</div>	
		
</div>
<script language="javascript">
	function deleteComboValues()
	{	
	   var UserEntries=document.getElementById("selAttribNameid");
	   for(var i=1;i<UserEntries.length;i++)
	   {
				UserEntries.remove(i);
				i = i - 1;
	   }
	}
	
	function ajaxFunction()
	{
		//To Load The Attribute Name Combo In The View Tab:
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
     	var url = "${contextPath}/hdiits.htm?actionFlag=loadAttribName";
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{	
					deleteComboValues();
					var text;
	            	var z=document.getElementById("selAttribNameid");								
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
		
	function show(whatNumber)
	{		
		if(parseInt(whatNumber) == 2)
		{		
			ajaxFunction();
		}
	}
	
	function chkDataType()
	{	
		alert("chkDataType");
		alert("attributeDataType::"+document.forms[0].txtAttribDatatypeView.value);	
		if(document.forms[0].txtAttribDatatypeView.value != "")
		{
			if(document.forms[0].txtAttribDatatypeView.value == 'DateTime')
			{			
				alert("DATETIME");
				document.getElementById('txtlenviewid').style.display='none';	
				document.getElementById('captionlenviewid').style.display='none';	
			}
			else if(document.forms[0].txtAttribDatatypeView.value != 'DateTime')
			{		
				alert("NOT DATEIME");		
				document.getElementById('txtlenviewid').style.display='';	
				document.getElementById('captionlenviewid').style.display='';
			}
		}
	}
	
	function chkDateTime()
	{
		alert("Value=="+document.forms[0].selDataType.value);
		if(document.forms[0].selDataType.value =='DateTime')
		{				
				document.getElementById('txtlenid').style.display='none';	
				document.getElementById('captionlenid').style.display='none';		
		}
		else if(document.forms[0].selDataType.value!='DateTime')
		{				
				document.getElementById('txtlenid').style.display='';	
				document.getElementById('captionlenid').style.display='';
		}
	 }	
		
	function enterData()
	{
		if(document.forms[0].txtAttribNameEng.value == "")
		{
			if(confirm("English AttributeName Not Entered, Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtAttribNameEng.value="";
				alert("English AttributeName Not  Entered, So assigned "+document.forms[0].txtAttribNameGuj.value+"_Eng");			
				document.forms[0].txtAttribNameEng.value =document.forms[0].txtAttribNameGuj.value+"_Eng";
			}
			else
				return false;
		}
		else if(document.forms[0].txtAttribNameGuj.value == "")
		{
			if(confirm("Gujarati AttributeName Not Entered, Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtAttribNameGuj.value="";
				alert("Gujarati AttributeName Not Entered, So assigned "+document.forms[0].txtAttribNameEng.value+"_Guj");			
				document.forms[0].txtAttribNameGuj.value =document.forms[0].txtAttribNameEng.value+"_Guj";
			}			
			else
				return false;
		}	
					
		if(document.forms[0].txtDescEng.value == "" )	
		{
			if(confirm("English Description Not Entered , Do You Want To Assign The Default Value?")==true)
			{	
				 document.forms[0].txtDescEng.value="";
				 alert("English Description Not Entered, So assigned "+ document.forms[0].txtDescGuj.value+"_Eng");			
				 document.forms[0].txtDescEng.value=document.forms[0].txtDescGuj.value+"_Eng";
			}
			else
				return false;
		}
		else if(document.forms[0].txtDescGuj.value == "")
		{
			if(confirm("Gujarati Description Not Entered , Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtDescGuj.value="";
				alert("Gujarati Description Not  Entered, So assigned "+document.forms[0].txtDescEng.value+"_Guj");	
				document.forms[0].txtDescGuj.value=document.forms[0].txtDescEng.value+"_Guj";		
			}
			else
				return false;
		}		
		
		if(combofill())
		{
			if(lengthReqd())
			{		
				var url = "${contextPath}/hdiits.htm?actionFlag=insertTempAttribDetails&hiddselDataType="+document.forms[0].selDataType.value+"&hiddtxtLength="+document.forms[0].txtLength.value+"&hiddtxtAttribNameEng="+document.forms[0].txtAttribNameEng.value+"&hiddtxtAttribNameGuj="+document.forms[0].txtAttribNameGuj.value+"&hiddtxtDescEng="+document.forms[0].txtDescEng.value+"&hiddtxtDescGuj="+document.forms[0].txtDescGuj.value;
				alert(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
}
		
function combofill()
	{		
		if(document.forms[0].selDataType.value == 'Select')
		{			
			alert("Please Select The DataType!!");	
			return false;			
		}
		else
		{	
			return true;
		}
	}	
	
	
function lengthReqd()
{	
	if(document.forms[0].txtLength.value == "" && document.forms[0].selDataType.value !='DateTime' )
	{
		alert("Please Enter The Length!!");
		document.forms[0].txtLength.focus();		
		return false;	
	}	
	else		
	{
		return true;	
	}
}
	
	function ajaxFunctionView()
	{
		//To View The Details Of Selected Attribute Name:
		alert("In Ajax()");
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
    	
     	var url = "${contextPath}/hdiits.htm?actionFlag=viewTmpAttribDetails&attributeId="+document.forms[0].selAttribName.value; 
     	alert("url::"+url);
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{					
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var tableentries = XMLDoc.getElementsByTagName('attributeIdMapped');	
					alert("Length::"+tableentries.length);
					for ( var i = 0 ; i < tableentries.length ; i++ )
   					{	   		
   						attribDescription=tableentries[i].childNodes[0].text;   						
   						attribDataType=tableentries[i].childNodes[1].text;   	
   						alert("datatype::"+attribDataType);					
   						if(attribDataType!='DateTime')
   						{   							
   							attributeDataTypeLen=tableentries[i].childNodes[2].text;
   							document.forms[0].txtLengthView.value=attributeDataTypeLen;
   						}  
	  						document.forms[0].txtAttribDesc.value=attribDescription;  
	  						document.forms[0].txtAttribDatatypeView.value=attribDataType;   						
	  						chkDataType();	 
					}  
				}				
			}
	    }					
   			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
			xmlHttp.send(encodeURIComponent(null));
			return true;
	}
		
	function Update()
	{
     	var url = "${contextPath}/hdiits.htm?actionFlag=updateTmpAttribute&attributeDesc="+document.forms[0].txtAttribDesc.value+"&attributeDataType="+document.forms[0].txtAttribDatatypeView.value+"&attribId="+document.forms[0].selAttribName.value+"&attribdataTypeLen="+document.forms[0].txtLengthView.value; 
//		window.location=url;
		document.forms[0].action = url;
		document.forms[0].submit();
	}
</script>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>

	<hdiits:validate locale="<%=(String)session.getAttribute("locale")%>" />
</hdiits:form>
<%
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>

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
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="fmsCategoryList" value="${resultMap.fmsCategoryList}"></c:set>
<c:set var="fmsTempCategoryList" value="${resultMap.fmsTempCategoryList}"></c:set>
<c:set var="fmsCategoryParentIdList" value="${resultMap.fmsCategoryParentIdList}"></c:set>

<hdiits:form name="frmFmsTmpCatgry" id="frmFmsTmpCatgryid" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="hiddenCategoryId"/>
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1" onfocus="show(1)"> 
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
			<hdiits:caption captionid="FMS.INSERTCTGRYDETAILS" bundle="${fmsTempLables}" />
		</center>
		</font></b> 
	<br>
	<br>
	<hr>			
	<table align="center" border="0" class="tabtable">				
			<tr>
				<td align="center" style="border:none">
					<hdiits:caption	captionid="FMS.CATEGORYNAME" bundle="${fmsTempLables}" />
				</td>
			</tr>			
			<tr>
				<td align="center" style="border:none">
					<hdiits:caption	captionid="FMS.ENGLISH" bundle="${fmsTempLables}" />
				</td>
				<td align="left" style="border:none">
					<hdiits:text name="txtCategoryNameEng" captionid="FMS.ENGCATEGORYNAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
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
					<hdiits:text name="txtCategoryNameGuj" captionid="FMS.GUJCATEGORYNAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
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
	</table>
	<br>
	<table border="0" align="center" class="tabtable" width="100%">
		<tr>
			<td style="border: none" align="right" width="20%">
				<input type="checkbox" name="chkParentCategory" id="chkParentCategoryId" value="true"   captionid="CHKPARENTCATGRY" bundle="${fmsTempLables}" onclick="showCombo()"/>
			</td>
			<td style="border: none" align="left" >
				<hdiits:caption	captionid="FMS.CHKPARENTCATGRY" bundle="${fmsTempLables}" />
			</td>
			
			<td style="border:none" align="left">
				<hdiits:select name="selParentCategoryId" id="selParentCategoryid" captionid="FMS.PARENTCATEGORY" bundle="${fmsTempLables}" mandatory="true">
					<option value="0">Select</option>						
					<c:forEach  items="${fmsCategoryList}" var="fmslookup">
						<option value='<c:out value="${fmslookup.categoryId}"/>' selected="true">
							<c:out value="${fmslookup.categoryName}" />
						</option>								
					</c:forEach>						
				</hdiits:select>
			</td>		
		</tr>		
	</table>	
	<br><br>	
	<table align="center" border="0" class="tabtable">	
		<tr>	
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.TEMPNAME" bundle="${fmsTempLables}" />
			</td>	
		</tr>
		<tr>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.ENGLISH" bundle="${fmsTempLables}" />
			</td>	
			<td align="left" style="border:none">
				<hdiits:text name="txtTempEngName" captionid="FMS.ENGTEMPNAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
			</td>	
			<td align="left" style="border:none">
				<hdiits:text name="txtTempDescEng" captionid="FMS.ENGDESC" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>			
		</tr>	
		<tr>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.GUJARATI" bundle="${fmsTempLables}" />
			</td>		
			<td align="left" style="border:none">
				<hdiits:text name="txtTempGujName" captionid="FMS.GUJTEMPNAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
			</td>		
			<td align="left" style="border:none">
				<hdiits:text name="txtTempDescGuj" captionid="FMS.GUJDESC" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>
		</tr>						
	</table>	
	
	<br><br>	
	<fmt:message key="FMS.TEMPATTACH" bundle="${fmsTempLables}" var="attachmentTitle"></fmt:message>
	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
	    <jsp:param name="attachmentName" value="TempAttach" />
	    <jsp:param name="formName" value="frmFmsTmpCatgry" />
	    <jsp:param name="attachmentType" value="Document" />   
		<jsp:param name="attachmentTitle" value="test" />              
	</jsp:include> 	
	
	<br><br>	
	<table align="center" border="0">
		<tr>
			<td align="center">
			<center>				
				<hdiits:button type="button" name="btnSubmit" value="SUBMIT" onclick="sendData()"/>				
			</center>	
			</td>
			<td align="center">
			<center>
				<hdiits:button type="button" name="btnReset" value="RESET" onclick="clearAll()"/>		
			</center>
			</td>		
		</tr>
	</table>	
	<hr>		
</div>

<!--  ********************************************Code For The View JSP************************************************************ --> 	
	
	<div id="tcontent2" class="tabcontent"><br>
	<br>
	<b>
	<font style="font-size: 4;font-style: normal">
		<center>
			<hdiits:caption captionid="FMS.VIEWCTGRYDETAILS" bundle="${fmsTempLables}" />
		</center>
	</font>
	</b> 
	<br>
	<br>
	<hr>
	<table align=center border="0" class="tabtable">
		<tr>
			<td align="right" style="border:none">
				<hdiits:caption captionid="FMS.CATEGORYNAME" bundle="${fmsTempLables}" />
			</td>
			<td align="left" style="border:none">
				<hdiits:select name="selCategoryNameView" id="selCategoryNameViewid" captionid="FMS.CATEGORYNAME" validation="sel.isrequired" bundle="${fmsTempLables}" mandatory="true" onchange="ajaxFunctionView()">
					<option value="0">Select</option>
					<c:forEach  items="${fmsTempCategoryList}" var="fmsviewlookup">
							<option value='<c:out value="${fmsviewlookup.categoryId}"/>' selected="true">
								<c:out value="${fmsviewlookup.categoryName}" />
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
				<hdiits:text name="txtCatgryDescView" captionid="FMS.DESC" bundle="${fmsTempLables}"/>
		 	</td>	
		 </tr>
		 <tr>			
			<td align="right" style="border:none">
				<hdiits:caption captionid="FMS.PARENTCATEGORY" bundle="${fmsTempLables}"/>
			</td>
			<td align="left" style="border:none">
				<hdiits:text name="txtCatgryParentId" captionid="FMS.PARENTCATEGORY" bundle="${fmsTempLables}"/>
		 	</td>
		 	<td align="left" style="border:none">
				<hdiits:select name="selId" id="selviewid" captionid="FMS.PARENTCATEGORY" validation="sel.isrequired" bundle="${fmsTempLables}" mandatory="true" onchange="setParentId()">
					<option value="0">Select</option>
					<c:forEach  items="${fmsCategoryParentIdList}" var="fmsIdlookup">
							<option value='<c:out value="${fmsIdlookup.categoryId}"/>' selected="true">
								<c:out value="${fmsIdlookup.categoryName}" />
							</option>								
					</c:forEach>
				</hdiits:select>			
		 	</td>			 		
		 </tr>
	</table>
	<br>
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
	ajaxFunction();	
	function showCombo()
	{			
		if(document.forms[0].chkParentCategory.checked==true)
		{	
			document.getElementById('selParentCategoryid').style.display='none';						
		}	
		else if(document.forms[0].chkParentCategory.checked==false)
		{			
			document.getElementById('selParentCategoryid').style.display='';
			ajaxFunction();							
		}					
	}
	
	function deleteComboValues()
	{	  
		 var UserEntries=document.getElementById("selParentCategoryId");
	     for(var i=1; i<UserEntries.length;i++)
	     {
				UserEntries.remove(i);
				i = i - 1;
	     }
	}	
	function ajaxFunction()
	{	
		// To populate the combo for the category name on the insert tab::
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
     	var url = "${contextPath}/hdiits.htm?actionFlag=loadCategoryDetails";
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{	
					deleteComboValues();
					var text;
	            	var z=document.getElementById("selParentCategoryId");								
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
		
	function deleteViewComboValues()
	{	  
		 var UserEntries=document.getElementById("selCategoryNameViewid");
	     for(var i=1; i<UserEntries.length;i++)
	     {
				UserEntries.remove(i);
				i = i - 1;
	     }
	}			
	function ajaxFunctionViewLoad()
	{	
		//To load the combo for the category name to fetch the setails of which category:
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
     	var url = "${contextPath}/hdiits.htm?actionFlag=loadCategoryViewDetails";
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{	
					deleteViewComboValues();
					var text;
	            	var z=document.getElementById("selCategoryNameViewid");								
					var XMLDoc=xmlHttp.responseXML.documentElement;																				
					var tableentries = XMLDoc.getElementsByTagName('CategoryIdMapped');	
				
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
	
	function deleteComboValuesCategory()
	{	  
		 var UserEntries=document.getElementById("selviewid");
	     for(var i=1; i<UserEntries.length;i++)
	     {
				UserEntries.remove(i);
				i = i - 1;
	     }
	}
			
	function ajaxFunctionOnSelCategory()
	{		
		//To view the combo for parent category id:		
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
     	var url = "${contextPath}/hdiits.htm?actionFlag=selectParentId";
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{	
					deleteComboValuesCategory();
					var text;
	            	var z=document.getElementById("selviewid");								
					var XMLDoc=xmlHttp.responseXML.documentElement;																				
					var tableentries = XMLDoc.getElementsByTagName('CategoryParentIdMapped');	
				
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
			document.forms[0].txtCatgryDescView.value="";
			document.forms[0].txtCatgryParentId.value="";
			document.forms[0].selId.value="";
			ajaxFunctionOnSelCategory();
			ajaxFunctionViewLoad();
		}
	}
	
	function combofill()
	{	
		//alert(document.forms[0].chkParentCategory.checked);	
		if(document.forms[0].selParentCategoryId.value == 0 && document.forms[0].chkParentCategory.checked==false)
		{			
			alert("Please Select The Parent Category");	
			return false;			
		}
		else
		{	
			return true;
		}
	}
	
	function sendData()
	{			
		if(document.forms[0].txtCategoryNameEng.value == "")
		{
			if(confirm("English AttributeName Not Entered, Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtCategoryNameEng.value="";
				alert("English AttributeName Not  Entered, So assigned "+document.forms[0].txtCategoryNameGuj.value+"_Eng");			
				document.forms[0].txtCategoryNameEng.value =document.forms[0].txtCategoryNameGuj.value+"_Eng";
			}
			else
				return false;
		}
		else if(document.forms[0].txtCategoryNameGuj.value == "")
		{
			if(confirm("Gujarati AttributeName Not Entered, Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtCategoryNameGuj.value="";
				alert("Gujarati AttributeName Not Entered, So assigned "+document.forms[0].txtCategoryNameEng.value+"_Guj");			
				document.forms[0].txtCategoryNameGuj.value =document.forms[0].txtCategoryNameEng.value+"_Guj";
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
			
			document.forms[0].hiddenCategoryId.value=document.forms[0].selParentCategoryId.value;											     			
			if(combofill())
			{	
				var url = "${contextPath}/hdiits.htm?actionFlag=insertTempCategoryDetails&txtCategoryNameEng="+document.forms[0].txtCategoryNameEng.value+"&txtDescEng="+document.forms[0].txtDescEng.value+"&txtCategoryNameGuj="+document.forms[0].txtCategoryNameGuj.value+"&txtDescGuj="+document.forms[0].txtDescGuj.value+"&hiddencategoryId="+document.forms[0].hiddenCategoryId.value+"&txtTempEngName="+document.forms[0].txtTempEngName.value+"&txtTempDescEng="+document.forms[0].txtTempDescEng.value+"&txtTempGujName="+document.forms[0].txtTempGujName.value+"&txtTempDescGuj="+document.forms[0].txtTempDescGuj.value;
				alert(url);
				document.forms[0].action=url;		
				document.forms[0].submit();		
			}	
	}
	


	function ajaxFunctionView()
	{
		//To View The Details Of Selected Category Name:
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
    	
     	var url = "${contextPath}/hdiits.htm?actionFlag=viewCtgryDtls&CategoryId="+document.forms[0].selCategoryNameView.value; 
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{					
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var tableentries = XMLDoc.getElementsByTagName('categoryIdMapped');	
					for ( var i = 0 ; i < tableentries.length ; i++ )
   					{	   		
   						ParentCategoryId=tableentries[i].childNodes[0].text;
   						categoryDesc=tableentries[i].childNodes[1].text;
   						categoryParentName=tableentries[i].childNodes[2].text;   						
   						
   						document.forms[0].txtCatgryDescView.value=categoryDesc;     						
   						if(ParentCategoryId != -1)
   						{   							
   							document.forms[0].txtCatgryParentId.value=categoryParentName;
   						}
   						else
   						{
   							document.forms[0].txtCatgryParentId.value=categoryParentName;
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
	
	// To  set the category parent id text box with the value selected in the combo.
	function setParentId()
	{				
		document.forms[0].txtCatgryParentId.value="";
		var x=document.getElementById("selviewid")
		//alert("x::"+x.options[x.selectedIndex].text);
		//alert("x::"+x.options[x.selectedIndex].value);
		document.forms[0].txtCatgryParentId.value=x.options[x.selectedIndex].text;			
	}
	
	function Update()
	{
     	var url = "${contextPath}/hdiits.htm?actionFlag=updateTmpCtgryDtls&categoryDesc="+document.forms[0].txtCatgryDescView.value+"&categoryParentId="+document.forms[0].txtCatgryParentId.value+"&categoryId="+document.forms[0].selCategoryNameView.value; 
		alert("url::"+url);		
		document.forms[0].action=url;	
		document.forms[0].submit();
	}	
	
	function clearAll()
	{
		document.forms[0].txtCategoryNameEng.value="";
		document.forms[0].txtDescEng.value="";
		document.forms[0].txtCategoryNameGuj.value="";		
		document.forms[0].txtDescGuj.value="";
		document.forms[0].chkParentCategory.checked=false;
		document.forms[0].selParentCategoryId.value="0";
	}	
</script>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<%
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>

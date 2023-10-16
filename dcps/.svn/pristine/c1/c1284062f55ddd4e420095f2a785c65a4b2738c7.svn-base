<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>


<hdiits:form name="draftForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">

<br />
<br />


	<table class="tabtable">
		
			<tr>
				<td class="datatableheader" colspan="2" >
					Add Draft Details
				</td>
				
			</tr>
			<tr>
				<td class="fieldLabel">
					Subject Doc<input type="radio" name="subFilter" value="Subject" onclick="getSubFilter(this)" checked="checked" id="rad1"/> 
				</td>
				<td class="fieldLabel">
					Common Doc<input type="radio" name="subFilter" value="Common" onclick="getSubFilter(this)"/>
				</td>
			</tr>
			<tr id="docrow">
				<td class="fieldLabel">
					Select Doc Name
				</td>
				<td class="fieldLabel">
					<select id="doc" onchange="loadTempCombo(this)" name="doc">
					<option selected="selected"> Select </option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="fieldLabel" >
					Select Template
				</td>
				<td class="fieldLabel" >
					<select id="temp" name="temp" onchange="chkIfExits(this)">
					<option selected="selected"> Select </option>
					</select>
				</td>
				
			</tr>
		
		
	</table>
	
	<!-- <table align="center">
		<tr>
			<td>
				<hdiits:button name="add" type="button" value="Add" onclick="showDocTemplpate()" />
			</td>
		</tr>
	</table>
	 -->
	<br />

	<table align="center">
		<tr>
			<td>
				<hdiits:button name="submit1" type="button" value="Submit" onclick="submitDraft()" />
			</td>
		</tr>
	</table>
	

	
</hdiits:form>	
<script type="text/javascript">
	var subFlag = 0;
	var subFilter='Subject';
	loadDocCombo();
	function getSubFilter(radObj)
	{
		subFilter = radObj.value;
		if(subFilter=='Subject')
		{
			var comboid = document.getElementById('temp');
			if(comboid.options.length>'0')
			{
				for(var i=comboid.options.length;i>0;i--)
				{comboid.remove(i);}
			}
			document.getElementById('docrow').style.display = '';
			loadDocCombo();
		}	
		else
		{
			document.getElementById('docrow').style.display = 'none';
			loadTempCombo2();
		}
	}
	function loadDocCombo()
	{
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
	
		var url = "hdiits.htm?actionFlag=populateDocNames";
		
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
					var comboid = document.getElementById('doc');
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

	
	var docId = '';
	function loadTempCombo(docObj)
	{
		
		var comboid = document.getElementById('temp');
		if(comboid.options.length>'0')
		{
			for(var i=comboid.options.length;i>0;i--)
			{comboid.remove(i);}
		}

			
		if(docObj.selectedIndex!='0')
		{
			
			docId = docObj[docObj.selectedIndex].value;
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
		
			var url = "hdiits.htm?actionFlag=populateTempType&docId="+docId+"&subFilter="+subFilter;
		
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var Category = XMLDoc.getElementsByTagName('Template');
						if(Category.length==0)
						{
							alert("Template is not found...");
							return;
						}
						
						
						for (var i=0 ; i < Category.length ; i++ )
						{
							Srno = Category[i].childNodes[0].text;							
		  					TemplateType = Category[i].childNodes[1].text;	   						
		  					var element = document.createElement('option');   				
		     				element.text = TemplateType;
		     				element.value = Srno;	
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
	
	
	function loadTempCombo2()
	{
		
		var comboid = document.getElementById('temp');
		if(comboid.options.length>'0')
		{
			for(var i=comboid.options.length;i>0;i--)
			{comboid.remove(i);}
		}

			
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
		
			var url = "hdiits.htm?actionFlag=populateTempType&docId="+docId+"&subFilter="+subFilter;
		
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var Category = XMLDoc.getElementsByTagName('Template');
						if(Category.length==0)
						{
							alert("Template is not found...");
							return;
						}
						
						
						for (var i=0 ; i < Category.length ; i++ )
						{
							Srno = Category[i].childNodes[0].text;							
		  					TemplateType = Category[i].childNodes[1].text;	   						
		  					var element = document.createElement('option');   				
		     				element.text = TemplateType;
		     				element.value = Srno;	
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
	
	
	function chkIfExits(tempTypeObj)
	{
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
			var SrNo = tempTypeObj[tempTypeObj.selectedIndex].value;
			var url = "hdiits.htm?actionFlag=chkTemplateMap&SrNo="+SrNo+"&subFilter="+subFilter;
		
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var Message = XMLDoc.getElementsByTagName('Message');
						if(Message.length==0)
						{
							return;
						}
						
						else
						{
						
							var Display = Message[0].childNodes[0].text;	
							if(Display=="")
							{	
								subFlag = 0;
								return;
							}
							else
							{						
		  						alert(Display);
		  						subFlag = 1;
		  					}
						} 
					}
				}
		    }
			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
	}
	
	function submitDraft()
	{
		if(document.getElementById('rad1').checked==true)
		{
			if(document.getElementById('doc').selectedIndex == 0)
			{
				
					alert('Please select Doc Name...');
					return false;
				
			}
		}	
		if(document.getElementById('temp').selectedIndex == 0)
		{
			
				alert('Please select Template Type...');
				return false;
			
		}
		
		if(subFlag == 1)
		{
			
				alert('Template type is already mapped ');
				return false;
			
		}
		var tempType = document.getElementById('temp')[document.getElementById('temp').selectedIndex].text;
		var docName = '';
		if(document.getElementById('doc').selectedIndex > 0)
		docName = document.getElementById('doc')[document.getElementById('doc').selectedIndex].text;
		
		var url = "hdiits.htm?actionFlag=defineDraft&tempType="+tempType+"&docName="+docName+"&subFilter="+subFilter; 
		document.forms[0].action=url;
		document.forms[0].submit();
	}
	
/*	function chkMapping(doc, temp)
	{	
		var table = document.getElementById('DocTemplate');
		var rowsno = table.getElementsByTagName('tr').length;
		var chk =0;
		
		if(rowsno>1)
		{
			for(var i=0;i<rowsno;i++)
			{
				if((table.rows[i].cells[0].innerHTML==doc)&&(table.rows[i].cells[1].innerHTML==temp))
				{
					alert('The doc name is already mapped to this template type');
					chk=1;
				}
	
			}
		}
		return chk;
	}
	*/
	
	if(parent.counter==5)
	alert('Record Inserted Succesfully...');
</script>


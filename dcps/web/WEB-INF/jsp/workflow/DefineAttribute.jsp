<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript">

	var count=0;
	function submitMe()
	{
		var table = document.getElementById('addedAttribute');
		var rowsno = table.getElementsByTagName('tr').length;
		
		if(rowsno>1)
		{
		var url = "hdiits.htm?actionFlag=defineAttribute"; 
		/*	+ "&attribNameEng=" + document.getElementById('attribNameEng').value 
			+ "&attribNameGuj=" + document.getElementById('attribNameGuj').value 
			+ "&attribDescEng=" + document.getElementById('attribDescEng').value 
			+ "&attribDescGuj=" + document.getElementById('attribDescGuj').value 
			+ "&dataType=";
		if(dataType == 'Select')
			url += "";
		else
			url += dataType;
		if(!(dataType == 'DateTime' || dataType == 'Select'))
			url += "(" + document.getElementById('lengthDataType').value + ")";
*/
		
//		window.location=url;
		document.forms[0].action=url;
		document.forms[0].submit();
		}
		else
		alert('Please enter some values');
	}

	var dataType = "";
	function check(opt)
	{
	
		dataType = opt[opt.selectedIndex].text;
		if(opt.selectedIndex == 0 || opt.selectedIndex == 3)
		{
			document.getElementById('lengthDataType').value='';
			document.getElementById('lengthDataType').style.display = 'none';
		}
		else
			document.getElementById('lengthDataType').style.display = '';
	}
	
	function skipAtt()
	{
		var url = "hdiits.htm?viewName=wf-defineCategory";
		document.forms[0].action=url;
		document.forms[0].submit(); 
	}
</script>

<hdiits:form name="categoryForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">


<br />


		<table  id="DocTemplateReference"  style="display: none" class="datatable" > 
		<!--<tr >
			<td colspan="2"  class="datatableheader">Already Added Values for Draft</td>
			
		</tr>
		-->
		<tr>
			<td class="datatableheader">Doc Name</td>
			<td class="datatableheader">Template Type</td>
		</tr>
		</table>
	<br />
<br />
	<table  class="datatable">
		<tr>
			<td class="fieldLabel">
				Present Attributes
			</td>
			<td class="fieldLabel">
				<select id="PAtt" >
					<option selected="selected"> Select </option>
				</select>
			</td>
		</tr>
	</table>
	
<br />
<br />
	<table class="tabtable">
			<tr>
				<td class="datatableheader" colspan="4">
					Add Attribute Details
				</td>
				
			</tr>
			
		<tr>
			<td class="fieldLabel">
				Attribute Name(Eng) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="attribNameEng" name="attribNameEng" />
			</td>
			<td class="fieldLabel">
				Attribute Name(Guj)
			</td>
			<td class="fieldLabel">
				<hdiits:text id="attribNameGuj" name="attribNameGuj"/>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel">
				Attribute Description(Eng) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="attribDescEng" name="attribDescEng"/>
			</td>
			<td class="fieldLabel">
				Attribute Description(Guj) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="attribDescGuj" name="attribDescGuj"/>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel">
				Data Type 
			</td>
			<td class="fieldLabel">
				<select id="dataType" onchange="check(this)">
					<option selected="selected"> Select </option>
					<option> Integer </option>
					<option> VarChar </option>
					<option> DateTime </option>
				</select>
			</td>
			<td class="fieldLabel">
				Length 
			</td>
			<td class="fieldLabel">
				<hdiits:number id="lengthDataType" name="lengthDataType"/>
			</td>
		</tr>
	</table>
	<br />
	<table align="center">
		<tr>
			<td>
				<hdiits:button name="add" type="button" value="Add" onclick="showAddedAtt()" />
			</td>
		</tr>
	</table>
	<br />
	
	<table  id="addedAttribute" style="display: none" class="datatable"> 
		<tr >
			<td class="datatableheader">Attribute Name(Eng)</td>
			<td class="datatableheader">Attribute Name(Guj)</td>
			<td class="datatableheader">Attribute Desc(Eng)</td>
			<td class="datatableheader">Attribute Desc(Guj)</td>
			<td class="datatableheader">DataType</td>
			<td class="datatableheader">Length</td>
			<td class="datatableheader">Edit</td>
			<td class="datatableheader">Delete</td>
		</tr>
	</table>

	<table align="center">
		<tr>
			<td>
				<hdiits:button name="submit1" type="button" value="Submit" onclick="submitMe()" />
			</td>
			<td>
				<hdiits:button name="skip" type="button" value="Skip" onclick="skipAtt()" />
			</td>
		</tr>
	</table>
	
	
</hdiits:form>	
<script type="text/javascript">
	document.getElementById('lengthDataType').style.display = 'none';
	
	function showAddedAtt()
	{
		if(document.getElementById('attribNameEng').value == '')
		{
			if(document.getElementById('attribNameGuj').value != '')
				document.getElementById('attribNameEng').value = document.getElementById('attribNameGuj').value + '_Eng';
			else
			{
				alert('Please enter Attribute Name...');
				return false;
			}
		}	
		if(document.getElementById('attribNameGuj').value == '')
		{
			if(document.getElementById('attribNameEng').value != '')
				document.getElementById('attribNameGuj').value = document.getElementById('attribNameEng').value + '_Guj';
			else
			{
				alert('Please enter Attribute Name...');
				return false;
			}
		}	
		if(document.getElementById('attribDescEng').value == '')
		{
			if(document.getElementById('attribDescGuj').value != '')
				document.getElementById('attribDescEng').value = document.getElementById('attribDescGuj').value + '_Eng';
			else
			{
				alert('Please enter Attribute Description...');
				return false;
			}
		}	
		if(document.getElementById('attribDescGuj').value == '')
		{
			if(document.getElementById('attribDescEng').value != '')
				document.getElementById('attribDescGuj').value = document.getElementById('attribDescEng').value + '_Guj';
			else
			{
				alert('Please enter Attribute Description...');
				return false;
			}
		}
		if(document.getElementById('dataType').selectedIndex == 0)
		{
			alert('Please select data type...');
			return false;
		}
		else if(document.getElementById('dataType').selectedIndex != 3)
		{
			if(document.getElementById('lengthDataType').value == '')
			{
				alert('Please enter length of data type...');
				return false;
			}
		}
		
		
		var table = document.getElementById('addedAttribute');
		table.style.display='';
		var tbody = document.createElement('tbody');
		var tr = document.createElement('tr');
		tr.setAttribute("id",count);
		tbody.appendChild(tr);
		table.appendChild(tbody);
		
		var td1 = document.createElement('td');
		var td2 = document.createElement('td');
		var td3 = document.createElement('td');
		var td4 = document.createElement('td');
		var td5 = document.createElement('td');
		var td6 = document.createElement('td');
		var td7 = document.createElement('td');
		var td8 = document.createElement('td');
		
		var ne = document.getElementById('attribNameEng').value;
		var ng = document.getElementById('attribNameGuj').value;
		var de = document.getElementById('attribDescEng').value;
		var dg = document.getElementById('attribDescGuj').value;
		
		
		td1.innerHTML=ne+'<input type="hidden" name="nameEng" value=" ' + ne + ' " />';
		td2.innerHTML=ng+'<input type="hidden" name="nameGuj" value=" ' + ng + ' " />';
		td3.innerHTML=de+'<input type="hidden" name="descEng" value=" ' + de + ' " />';
		td4.innerHTML=dg+'<input type="hidden" name="descGuj" value=" ' + dg + ' " />';
		
		
		var index = document.getElementById('dataType').selectedIndex;
		var length = document.getElementById('lengthDataType').value;
		
		
		td5.innerHTML=dataType+'<input type="hidden" name="daTyp" value=" ' + dataType + ' " >';
		td6.innerHTML=length+'<input type="hidden" name="dtLen" value=" ' + length + ' " >';
		
		var editLink = "<a href='javascript:editInfo(" + index+ "," +count + ","+ "\"" + length + "\"" + "," + "\"" + ne + "\"" +" ," + "\"" + ng + "\"" +" ," + "\"" + de + "\"" +" ," + "\"" + dg + "\""+ ") " + "'>edit</a>" ;
		
		td7.innerHTML=editLink ;
		td8.innerHTML="<a href='javascript:deleteInfo("+count+")'>delete</a>";
		
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tr.appendChild(td7);
		tr.appendChild(td8);
		
		document.getElementById('attribNameEng').value='';
		document.getElementById('attribNameGuj').value='';
		document.getElementById('attribDescEng').value='';
		document.getElementById('attribDescGuj').value='';
		document.getElementById('lengthDataType').value='';
		document.getElementById('dataType').selectedIndex='0';
		count++;
	}
	
	function editInfo( index, rowId, length, ne, ng, de, dg)
	{
		document.getElementById('attribNameEng').value = ne;
		document.getElementById('attribNameGuj').value = ng;
		document.getElementById('attribDescEng').value = de;
		document.getElementById('attribDescGuj').value = dg;
		document.getElementById('dataType').selectedIndex = index;
		document.getElementById('lengthDataType').value = length;
		var table = document.getElementById('addedAttribute');
		table.deleteRow(document.getElementById(rowId).rowIndex);
		check(document.getElementById('dataType'));
	}
	
	function deleteInfo(ri)
	{
		var table = document.getElementById('addedAttribute');
		table.deleteRow(document.getElementById(ri).rowIndex);
	}
	
	function displayDocTempRefer()
	{
		<% if(session.getAttribute("docName")!=null)
			{
		%>
		var table = document.getElementById('DocTemplateReference');
		table.style.display='';
		var tbody = document.createElement('tbody');
		var tr = document.createElement('tr');
		var td1 = document.createElement('td');
		var td2 = document.createElement('td');
		
		td1.innerHTML='<%= session.getAttribute("docName")%>';
		td2.innerHTML='<%= session.getAttribute("temptype")%>';
		
		tr.appendChild(td1);
		tr.appendChild(td2);
		tbody.appendChild(tr);
		table.appendChild(tbody);
		<% } %>
	}	
	displayDocTempRefer();
	
	function loadComboAtt()
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
	
		var url = "hdiits.htm?actionFlag=populateMappingAttribute";
	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var Category = XMLDoc.getElementsByTagName('Attribute');
					if(Category.length==0)
					{
						alert("Attribute is not found...");
						return;
					}
					var comboid = document.getElementById('PAtt');
					
					for (var i=0 ; i < Category.length ; i++ )
					{
						AttributeId = Category[i].childNodes[0].text;							
	  					AttributeName = Category[i].childNodes[1].text;	   						
	  					var element = document.createElement('option');   				
	     				element.text = AttributeName;
	     				element.value = AttributeId;	
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

	loadComboAtt();
	
	
</script>
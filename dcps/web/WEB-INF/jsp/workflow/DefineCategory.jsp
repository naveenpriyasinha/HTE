<%@ include file="../core/include.jsp"%>
<%	try{ %>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<hdiits:form name="categoryForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
<br />

	<table  id="DocTemplateReference"  style="display: none"  class="datatable"> 
		<tr >
			<td class="datatableheader">Doc Name</td>
			<td class="datatableheader">Template Type</td>
		</tr>
		</table>
<br /><br />

		<table  id="addedAttributeReference" style="display: none"  class="datatable"> 
	
		<tr >
			<td class="datatableheader">Attribute Name(Eng)</td>
			<td class="datatableheader">Attribute Name(Guj)</td>
			<td class="datatableheader">Attribute Desc(Eng)</td>
			<td class="datatableheader">Attribute Desc(Guj)</td>
			<td class="datatableheader">DataType</td>
			<td class="datatableheader">Length</td>
			
		</tr>
</table>
<br /><br />
<table class="datatable">
		<tr>
			<td class="fieldLabel">
				Present Categories
			</td>
			<td class="fieldLabel">
				<select id="PCat" name="PCat" onchange="chkMultiple()">
					<option selected="selected"> Select </option>
	
				</select>
			</td>
		</tr>
	</table>
<br /><br />
	<table class="tabtable" >
		<tr><td class="datatableheader" colspan="4">Add Category Details</td></tr>
		<tr>
			<td class="fieldLabel">
				Category Name(Eng) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="categoryNameEng" name="categoryNameEng"/>
			</td>
				<td class="fieldLabel">
				Category Name(Guj) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="categoryNameGuj" name="categoryNameGuj"/>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel">
				Category Description(Eng) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="categoryDescEng" name="categoryDescEng"/>
			</td>
			<td class="fieldLabel">
				Category Description(Guj) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="categoryDescGuj" name="categoryDescGuj"/>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel">
				Select Parent Category
			</td>
			<td class="fieldLabel">
				<select id="Category" name="Category" onchange="hideParent(this)">
					<option> Select </option>
				</select>
			</td>
			<td class="fieldLabel">
				Is Parent Category
			</td>
			<td class="fieldLabel">
				<input type="checkbox" id="isParent" name="isParent" onclick="changeCombo(this)"/>
			</td>
		</tr>
	</table>
	<br />
	<table align="center">
		<tr>
			<td>
				<hdiits:button name="add" type="button" value="Add" onclick="showAddedAtt()"/>
			</td>
		</tr>
	</table>
	<br />
	<table align="center" id="addedCategory" style="display: none"  class="datatable"> 
		<tr >
			<td class="datatableheader">Category Name(Eng)</td>
			<td class="datatableheader">Category Name(Guj)</td>
			<td class="datatableheader">Category Desc(Eng)</td>
			<td class="datatableheader">Category Desc(Guj)</td>
			<td class="datatableheader">Parent Category</td>
			<td class="datatableheader">Edit</td>
			<td class="datatableheader">Delete</td>
		</tr>
	</table>
	
	<table align="center">
		<tr>
			<td>
				<hdiits:button name="submit1" type="button" value="Submit" onclick="submitMe()"/>
			</td>
			
		</tr>
	</table>


<script type="text/javascript">

	var count=0;
	function chkMultiple()
	{
		var table = document.getElementById('addedCategory');
		var rowsno = table.getElementsByTagName('tr').length;
		//alert(rowsno);
		if((rowsno==1))
			return true;
		else
		{
			alert('Multiple selection is not allowed');
			document.getElementById('PCat').selectedIndex=0;
		}
			
		
	}
	function hideParent(selobj)
	{
		if(selobj.selectedIndex!=0)
		document.getElementById('isParent').style.display='none';
		
		else
		document.getElementById('isParent').style.display='';
	}
	function loadCombo()
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
	
		var url = "hdiits.htm?actionFlag=populateParentCategory";
	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var Category = XMLDoc.getElementsByTagName('Category');
					if(Category.length==0)
					{
						alert("Parent Category is not found...");
						return;
					}
					var comboid = document.getElementById('Category');
					
					for (var i=0 ; i < Category.length ; i++ )
					{
						CategoryId = Category[i].childNodes[0].text;							
	  					CategoryName = Category[i].childNodes[1].text;	   						
	  					var element = document.createElement('option');   				
	     				element.text = CategoryName;
	     				element.value = CategoryId;	
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

	loadCombo();

	function submitMe()
	{
		var table = document.getElementById('addedCategory');
		var rowsno = table.getElementsByTagName('tr').length;
		
		if((rowsno>1)||(document.getElementById('PCat').selectedIndex > 0))
		{
		var url = "hdiits.htm?actionFlag=defineCategory"; 
			/*+ "&categoryNameEng=" + document.getElementById('categoryNameEng').value 
			+ "&categoryNameGuj=" + document.getElementById('categoryNameGuj').value 
			+ "&categoryDescEng=" + document.getElementById('categoryDescEng').value 
			+ "&categoryDescGuj=" + document.getElementById('categoryDescGuj').value
			+ "&parentCategory=";
		if(document.getElementById('isParent').checked)
			url += "-1";
		else
			url += document.getElementById('Category')[document.getElementById('Category').selectedIndex].value;

		alert(url);*/
//		window.location=url;
		document.forms[0].action=url;
		document.forms[0].submit();
		}
		else
		alert('Please enter some values');
	}
	
	function skipCat()
	{
			var url = "hdiits.htm?viewName=wf-defineTemplate"; 
			document.forms[0].action=url;
			document.forms[0].submit();
	}

	function changeCombo(opt)
	{
		
		if(opt.checked)
			document.getElementById('Category').style.display = 'none';
		else
			document.getElementById('Category').style.display = '';
	}
	
	function showAddedAtt()
	{
		if((count<1)&&(document.getElementById('PCat').selectedIndex==0))
		{
			if(document.getElementById('categoryNameEng').value == '')
			{
				if(document.getElementById('categoryNameGuj').value != '')
					document.getElementById('categoryNameEng').value = document.getElementById('categoryNameGuj').value + '_Eng';
				else
				{
					alert('Please enter Category Name...');
					return false;
				}
			}	
			if(document.getElementById('categoryNameGuj').value == '')
			{
				if(document.getElementById('categoryNameEng').value != '')
					document.getElementById('categoryNameGuj').value = document.getElementById('categoryNameEng').value + '_Guj';
				else
				{
					alert('Please enter Category Name...');
					return false;
				}
			}	
			if(document.getElementById('categoryDescEng').value == '')
			{
				if(document.getElementById('categoryDescGuj').value != '')
					document.getElementById('categoryDescEng').value = document.getElementById('categoryDescGuj').value + '_Eng';
				else
				{
					alert('Please enter Category Description...');
					return false;
				}
			}	
			if(document.getElementById('categoryDescGuj').value == '')
			{
				if(document.getElementById('categoryDescEng').value != '')
					document.getElementById('categoryDescGuj').value = document.getElementById('categoryDescEng').value + '_Guj';
				else
				{
					alert('Please enter Category Description...');
					return false;
				}
			}
			if(document.getElementById('isParent').checked == false)
			{
				if(document.getElementById('Category').selectedIndex == 0)
				{
					alert('Please enter Parent Category...');
					return false;
				}
			}
			
			
			
			/************************************************************************************************************/
			
			
			var table = document.getElementById('addedCategory');
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
			
			var ne = document.getElementById('categoryNameEng').value;
			var ng = document.getElementById('categoryNameGuj').value;
			var de = document.getElementById('categoryDescEng').value;
			var dg = document.getElementById('categoryDescGuj').value;
			
			td1.innerHTML=ne+'<input type="hidden" name="cnameEng" value=" ' + ne + ' " />';
			td2.innerHTML=ng+'<input type="hidden" name="cnameGuj" value=" ' + ng + ' " />';
			td3.innerHTML=de+'<input type="hidden" name="cdescEng" value=" ' + de + ' " />';
			td4.innerHTML=dg+'<input type="hidden" name="cdescGuj" value=" ' + dg + ' " />';
			
			var index = document.getElementById('Category').selectedIndex;
			var parent=false;
			var ispar=0;
			if((document.getElementById('isParent').checked))
			{	
				parent = true;
				ispar = -1;
			}
			
			else
			{
				parent = document.getElementById('Category')[document.getElementById('Category').selectedIndex].text;
				ispar = document.getElementById('Category')[document.getElementById('Category').selectedIndex].value;
			}
			
			
			td5.innerHTML=parent+'<input type="hidden" name="ispar" value=" ' + ispar + ' " >'+'<input type="hidden" name="parent" value=" ' + parent + ' " >';
			
			var editLink = "<a href='javascript:editInfo(" + index+ "," +count + ","+ "\"" + parent + "\"" + "," + "\"" + ne + "\"" +" ," + "\"" + ng + "\"" +" ," + "\"" + de + "\"" +" ," + "\"" + dg + "\""+ ") " + "'>edit</a>" ;
			
				
			td6.innerHTML=editLink ;
			td7.innerHTML="<a href='javascript:deleteInfo("+count+")'>delete</a>";
			
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			tr.appendChild(td4);
			tr.appendChild(td5);
			tr.appendChild(td6);
			tr.appendChild(td7);
		
			document.getElementById('categoryNameEng').value='';
			document.getElementById('categoryNameGuj').value='';
			document.getElementById('categoryDescEng').value='';
			document.getElementById('categoryDescGuj').value='';
			document.getElementById('isParent').checked = false;
			document.getElementById('isParent').style.display='';
			document.getElementById('Category').selectedIndex='0';
			document.getElementById('Category').style.display='';
			count++;	
		}
		else
		{
			alert('multiple add is not allowed');
		}
	}
	
	function editInfo( index, rowId, parent, ne, ng, de, dg)
	{
		
		document.getElementById('categoryNameEng').value = ne;
		document.getElementById('categoryNameGuj').value = ng;
		document.getElementById('categoryDescEng').value = de;
		document.getElementById('categoryDescGuj').value = dg;
		document.getElementById('Category').selectedIndex = index;
		if(index!=0)
			document.getElementById('isParent').checked = false;
		else
			document.getElementById('isParent').checked = true;
		var table = document.getElementById('addedCategory');
		table.deleteRow(document.getElementById(rowId).rowIndex);
		hideParent(document.getElementById('Category'));
		changeCombo(document.getElementById('isParent'));
		count--;
		
	}
	
	function deleteInfo(ri)
	{
		var table = document.getElementById('addedCategory');
		table.deleteRow(document.getElementById(ri).rowIndex);
		count--;
	}
	
	function displayAttributeRefer()
	{
		<%
		if(session.getAttribute("attributeNameEngArray")!=null)
		{
			String[] attNameEng =   (String[])session.getAttribute("attributeNameEngArray");
			String[] attNameGuj =   (String[])session.getAttribute("attributeNameGujArray");
			String[] attDescEng =   (String[])session.getAttribute("attributeDescEngArray");
			String[] attDescGug =   (String[])session.getAttribute("attributeDescGugArray");
			String[] datType =   (String[])session.getAttribute("dataTypeArray");
			String[] dtLength =   (String[])session.getAttribute("dtLength");
		%>
		var table = document.getElementById('addedAttributeReference');
		table.style.display='';
		<%
		for(int i=0; i<attNameEng.length; i++)
		{
			
		%>
	
		var tbody = document.createElement('tbody');
		var tr = document.createElement('tr');
		var td1 = document.createElement('td');
		var td2 = document.createElement('td');
		var td3 = document.createElement('td');
		var td4 = document.createElement('td');
		var td5 = document.createElement('td');
		var td6 = document.createElement('td');
		
		td1.innerHTML='<%= attNameEng[i] %>';
		td2.innerHTML='<%= attNameGuj[i] %>';
		td3.innerHTML='<%= attDescEng[i] %>';
		td4.innerHTML='<%= attDescGug[i] %>';
		td5.innerHTML='<%= datType[i] %>';
		td6.innerHTML='<%= dtLength[i] %>';
		
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tbody.appendChild(tr);
		table.appendChild(tbody);
		<% 
		}
		}
		%>
	}
	
	displayAttributeRefer();
	
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
	
	function loadComboCat()
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
	
		var url = "hdiits.htm?actionFlag=populateMappingCategory";
	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var Category = XMLDoc.getElementsByTagName('Category');
					if(Category.length==0)
					{
						alert("Category is not found...");
						return;
					}
					var comboid = document.getElementById('PCat');
					
					for (var i=0 ; i < Category.length ; i++ )
					{
						CategoryId = Category[i].childNodes[0].text;							
	  					CategoryName = Category[i].childNodes[1].text;	   						
	  					var element = document.createElement('option');   				
	     				element.text = CategoryName;
	     				element.value = CategoryId;	
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

	loadComboCat();
	
</script>
</hdiits:form>
<% 
}catch(Exception e)
{
	e.printStackTrace();	
}

%>

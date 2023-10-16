<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>


<hdiits:form name="mappingForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">

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

<table id="addedCategoryReference" style="display: none"   class="datatable"> 
		
		<tr >
			<td class="datatableheader">Category Name(Eng)</td>
			<td class="datatableheader">Category Name(Guj)</td>
			<td class="datatableheader">Category Desc(Eng)</td>
			<td class="datatableheader">Category Desc(Guj)</td>
			<td class="datatableheader">Parent Category</td>
		</tr>
	</table>
<br /><br />

<table id="addedTemplateReference"  style="display: none" class="datatable"> 
		
		<tr >
			<td class="datatableheader">Template Name(Eng)</td>
			<td class="datatableheader">Template Name(Guj)</td>
			<td class="datatableheader">Template Desc(Eng)</td>
			<td class="datatableheader">Template Desc(Guj)</td>
			<td class="datatableheader">Template Category</td>
		</tr>
	</table>
<br />
	<table  class="tabtable" >
		<tr><td colspan="4" class="datatableheader">Define Category-Attribute Mapping</td></tr>
			<tr>
				<td class="fieldLabel">
					Select Category
				</td>
				<td class="fieldLabel">
					<select id="cat" >
					<option > Select </option>
					</select>
				</td>
				<td class="fieldLabel">
					Select Attribute
				</td>
				<td class="fieldLabel">
					<select id="att" onchange="">
					<option selected="selected"> Select </option>
					</select>
				</td>
				
			</tr>
		
		<tr>
			<td class="fieldLabel">
				Mandatory
			</td>
			<td class="fieldLabel">
				<input type="checkbox" id="mandatory" name="mandatory" onclick=""/>
			</td>
			<td class="fieldLabel">
				Display Order
			</td>
			<td class="fieldLabel">
				<hdiits:number id="Order" name="Order"/>
			</td>
		</tr>
	
	</table>
	<table align="center">
		<tr>
			<td>
				<hdiits:button name="add" type="button" value="Add" onclick="showMappeddAtt()" />
			</td>
		</tr>
	</table>
	
	<table class="datatable" id="mappedAttribute" style="display: none" > 
		<tr >
			<td class="datatableheader">Category</td>
			<td class="datatableheader">Attribute</td>
			<td class="datatableheader">Mandatory</td>
			<td class="datatableheader">Order</td>
			<td class="datatableheader">Edit</td>
			<td class="datatableheader">Delete</td>
		</tr>
	</table>
	
	<table align="center" id="hiddenValues" style="display: none"> 
		<tr >
			<td>Category</td>
			<td>Attribute</td>
			<td>Mandatory</td>
			<td>Order</td>
		</tr>
	</table>

	<table align="center">
		<tr>
			<td>
				<hdiits:button name="submit1" type="button" value="Submit" onclick="submitMapFrm()" />
			</td>
			<td>
				<hdiits:button name="submit2" type="button" value="Skip And Submit" onclick="skipSubmitMapFrm()" />
			</td>
		</tr>
	</table>
</hdiits:form>	

<script type="text/javascript">

	var count = 0;
	function showMappeddAtt()
	{
		if(document.getElementById('cat').selectedIndex == 0)
		{
			
				alert('Please select Category Name...');
				return false;
			
		}	
		if(document.getElementById('att').selectedIndex == 0)
		{
			
				alert('Please select Attribute Name...');
				return false;
			
		}	
		if(document.getElementById('Order').value == '')
		{
				alert('Please enter Attribute Display Order...');
				return false;
			
		}	
		
		var table = document.getElementById('mappedAttribute');
		table.style.display='';
		
		
		var comboCatIndex = document.getElementById('cat').selectedIndex;
		var comboAttIndex = document.getElementById('att').selectedIndex;
		var cat = document.getElementById('cat')[comboCatIndex].text;
		var att = document.getElementById('att')[comboAttIndex].text;
		var catCode = document.getElementById('cat')[comboCatIndex].value;
		var attCode = document.getElementById('att')[comboAttIndex].value;
		var mand = document.getElementById('mandatory').checked;
		var ord = document.getElementById('Order').value;
		var mandatoryFlag = 'N';
		if(mand==true)
		mandatoryFlag = 'Y';
		
		
		var flag = chkMapping(cat, att, ord);
		
		if(flag==0) 
		{
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
	
			td1.innerHTML=cat;
			td2.innerHTML=att;
			td3.innerHTML=mand;
			td4.innerHTML=ord;
			
			var editLink = "<a href='javascript:editInfo(" +count + ","+ "\"" + comboCatIndex + "\"" + "," + "\"" + comboAttIndex + "\"" +" ," + "\"" + mand + "\"" +" ," + "\"" + ord + "\"" + ") " + "'>edit</a>" ;
				
			td5.innerHTML=editLink ;
			td6.innerHTML="<a href='javascript:deleteInfo("+count+")'>delete</a>";
		
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			tr.appendChild(td4);
			tr.appendChild(td5);
			tr.appendChild(td6);
			
			var htable = document.getElementById('hiddenValues');
			var htbody = document.createElement('tbody');
			var htr = document.createElement('tr');
			var htd1 = document.createElement('td');
			var htd2 = document.createElement('td');
			var htd3 = document.createElement('td');
			var htd4 = document.createElement('td');
			htd1.innerHTML='<input type="hidden" name="catCode" value=" ' + catCode + ' " />';
			htd2.innerHTML='<input type="hidden" name="attCode" value=" ' + attCode + ' " />';
			htd3.innerHTML='<input type="hidden" name="mand" value=" ' + mandatoryFlag + ' " />';
			htd4.innerHTML='<input type="hidden" name="ord" value=" ' + ord + ' " />';
			htr.appendChild(htd1);
			htr.appendChild(htd2);
			htr.appendChild(htd3);
			htr.appendChild(htd4);
			htbody.appendChild(htr);
			htable.appendChild(htbody);
			//document.getElementById('cat').selectedIndex='0';
			document.getElementById('att').selectedIndex='0';
			document.getElementById('mandatory').checked=false;
			document.getElementById('Order').value='';
		}	
		
	}
	
	function editInfo(rowId, comboCatIndex, comboAttIndex, mand, ord)
	{
		document.getElementById('cat').selectedIndex=comboCatIndex;
		document.getElementById('att').selectedIndex=comboAttIndex;
		document.getElementById('mandatory').checked=mand;
		document.getElementById('Order').value=ord;
		var table = document.getElementById('mappedAttribute');
		table.deleteRow(document.getElementById(rowId).rowIndex);
	}
	
	function deleteInfo(ri)
	{
		var table = document.getElementById('mappedAttribute');
		table.deleteRow(document.getElementById(ri).rowIndex);
	}
	
	function chkMapping(cat, att, ord)
	{	
		var table = document.getElementById('mappedAttribute');
		var rowsno = table.getElementsByTagName('tr').length;
		var chk =0;
		
		if(rowsno>1)
		{
			for(var i=0;i<rowsno;i++)
			{
				if((table.rows[i].cells[0].innerHTML==cat)&&(table.rows[i].cells[1].innerHTML==att))
				{
					alert('The attribute is already mapped to this category');
					document.getElementById('att').selectedIndex='0';
					chk=1;
				}

				if((table.rows[i].cells[0].innerHTML==cat)&&(table.rows[i].cells[3].innerHTML==ord))
				{
					alert('An attribute is already mapped at this order for given category');
					document.getElementById('Order').value='';
					chk=1;
				}	
			}
		}
		return chk;
	}
	
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
					var comboid = document.getElementById('cat');
					
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
					<% if((session.getAttribute("fmsTemplateCategoryDtlsEngLst")!=null)||(session.getAttribute("fmsTemplateCategoryDtlsGujLst")!=null))
					 {%>
					comboid.selectedIndex = comboid.options.length-1;  
					<%}else{%>
					comboid.selectedIndex = 0;
					<%}%>  
				}
			}
	    }
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}

	loadComboCat();
	
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
					var comboid = document.getElementById('att');
					
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
	
	function submitMapFrm()
	{
		var table = document.getElementById('mappedAttribute');
		var rowsno = table.getElementsByTagName('tr').length;
		
		if(rowsno>1)
		{
		var url = "hdiits.htm?actionFlag=CategoryAttributeMapping"; 
		document.forms[0].action=url;
		document.forms[0].submit();
		}
		else
		alert('Please enter some values ');
	}
	function skipSubmitMapFrm()
	{
		var url = "hdiits.htm?actionFlag=CategoryAttributeMapping&skipFlag=true"; 
		document.forms[0].action=url;
		document.forms[0].submit();
	}
	function displayCategoryRefer()
	{
		<%
		if(session.getAttribute("categoryNameEngArray")!=null)
		{
			String[] catNameEng =   (String[])session.getAttribute("categoryNameEngArray");
			String[] catNameGuj =   (String[])session.getAttribute("categoryNameGujArray");
			String[] catDescEng =   (String[])session.getAttribute("categoryDescEngArray");
			String[] catDescGug =   (String[])session.getAttribute("categoryDescGugArray");
			String[] parent =   (String[])session.getAttribute("parent");
		%>
		var table = document.getElementById('addedCategoryReference');
		table.style.display='';
		<%
		for(int i=0; i<catNameEng.length; i++)
		{
			
		%>
		var table = document.getElementById('addedCategoryReference');
		var tbody = document.createElement('tbody');
		var tr = document.createElement('tr');
		var td1 = document.createElement('td');
		var td2 = document.createElement('td');
		var td3 = document.createElement('td');
		var td4 = document.createElement('td');
		var td5 = document.createElement('td');
		
		td1.innerHTML='<%= catNameEng[i] %>';
		td2.innerHTML='<%= catNameGuj[i] %>';
		td3.innerHTML='<%= catDescEng[i] %>';
		td4.innerHTML='<%= catDescGug[i] %>';
		td5.innerHTML='<%= parent[i] %>';
		
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tbody.appendChild(tr);
		table.appendChild(tbody);
		<% 
		}
		}
		%>
	}
	
	displayCategoryRefer();
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
	
	function displayTemplateRefer()
	{
		<% if(session.getAttribute("TemplateNameEng")!=null)
			{
		%>
		var table = document.getElementById('addedTemplateReference');
		table.style.display='';
		var tbody = document.createElement('tbody');
		var tr = document.createElement('tr');
		var td1 = document.createElement('td');
		var td2 = document.createElement('td');
		var td3 = document.createElement('td');
		var td4 = document.createElement('td');
		var td5 = document.createElement('td');
		
		td1.innerHTML='<%= session.getAttribute("TemplateNameEng")%>';
		td2.innerHTML='<%= session.getAttribute("TemplateNameGuj")%>';
		td3.innerHTML='<%= session.getAttribute("TemplateDescEng")%>';
		td4.innerHTML='<%= session.getAttribute("TemplateDescGuj")%>';
		td5.innerHTML='<%= session.getAttribute("CategoryName")%>';
		
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		
		tbody.appendChild(tr);
		table.appendChild(tbody);
		<% } %>
	}	
	displayTemplateRefer();
	
	<%
			if(session.getAttribute("docName")!=null)
				session.removeAttribute("docName");
		
			if(session.getAttribute("temptype")!=null)
				session.removeAttribute("temptype");
			
			if(session.getAttribute("attributeNameEngArray")!=null)
				session.removeAttribute("attributeNameEngArray");
			
			if(session.getAttribute("attributeNameGujArray")!=null)
				session.removeAttribute("attributeNameGujArray");
			
			if(session.getAttribute("attributeDescEngArray")!=null)
				session.removeAttribute("attributeDescEngArray");
			
			if(session.getAttribute("attributeDescGugArray")!=null)
				session.removeAttribute("attributeDescGugArray");
			
			if(session.getAttribute("dataTypeArray")!=null)
				session.removeAttribute("dataTypeArray");
			
			if(session.getAttribute("categoryNameEngArray")!=null)
				session.removeAttribute("categoryNameEngArray");
			
			if(session.getAttribute("categoryNameGujArray")!=null)
				session.removeAttribute("categoryNameGujArray");
			
			if(session.getAttribute("categoryDescEngArray")!=null)
				session.removeAttribute("categoryDescEngArray");
			
			if(session.getAttribute("categoryDescGugArray")!=null)
				session.removeAttribute("categoryDescGugArray");
			
			if(session.getAttribute("parent")!=null)
				session.removeAttribute("parent");
			
			if(session.getAttribute("TemplateNameEng")!=null)
				session.removeAttribute("TemplateNameEng");
			
			if(session.getAttribute("TemplateNameGuj")!=null)
				session.removeAttribute("TemplateNameGuj");
			
			if(session.getAttribute("TemplateDescEng")!=null)
				session.removeAttribute("TemplateDescEng");
			
			if(session.getAttribute("TemplateDescGuj")!=null)
				session.removeAttribute("TemplateDescGuj");
			
			if(session.getAttribute("CategoryName")!=null)
				session.removeAttribute("CategoryName");
			
			if(session.getAttribute("dtLength")!=null)
				session.removeAttribute("dtLength");
		%>
</script>
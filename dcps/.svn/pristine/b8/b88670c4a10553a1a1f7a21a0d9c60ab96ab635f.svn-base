<%	try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"> </script>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<hdiits:form name="TemplateForm" validate="true"  encType="multipart/form-data" method="POST" action="./hdiits.htm">
<br />
<font color="blue" >In case of online draft, skip this page </font>
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

	<table class="tabtable">
		
			
		<tr><td colspan="4" class="datatableheader">Add Template Details</td></tr>
		<tr>
			<td class="fieldLabel">
				Template Name(Eng) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="TemplateNameEng" name="TemplateNameEng"/>
			</td>
			<td class="fieldLabel">
				Template Name(Guj) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="TemplateNameGuj" name="TemplateNameGuj"/>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel">
				Template Description(Eng) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="TemplateDescEng" name="TemplateDescEng"/>
			</td>
			<td class="fieldLabel">
				Template Description(Guj) 
			</td>
			<td class="fieldLabel">
				<hdiits:text id="TemplateDescGuj" name="TemplateDescGuj"/>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel">
				Template Category
			</td>
			<td class="fieldLabel">
				<select id="Template" name="Template">
					<option> Select </option>
				</select>
			</td>
			<td class="fieldLabel"></td>
			<td class="fieldLabel"></td>
		</tr>
	</table>
	<br />
	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
		<jsp:param name="attachmentName" value="TemplateAttach" />
		<jsp:param name="formName" value="TemplateForm" />
		<jsp:param name="attachmentType" value="Document" />   
        <jsp:param name="attachmentTitle" value="TemplateAttachment" />
	</jsp:include>
	
	
	
	
	<table align="center">
		<tr>
			<td>
				<hdiits:button name="submit1" type="button" value="Submit" onclick="submitMe()"/>
			</td>
			<td>
				<hdiits:button name="skip2" type="button" value="Skip" onclick="skipCat()" />
			</td>
		</tr>
	</table>
</hdiits:form>

<script type="text/javascript">
	function skipCat()
	{
			var url = "hdiits.htm?viewName=wf-defineCategoryAttributeMapping"; 
			document.forms[0].action=url;
			document.forms[0].submit();
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
	
		var url = "hdiits.htm?actionFlag=populateParentTemplate";
	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var Template = XMLDoc.getElementsByTagName('Category');
					if(Template.length==0)
					{
						alert("Template Category is not found...");
						return;
					}
					var comboid = document.getElementById('Template');
					
					for (var i=0 ; i < Template.length ; i++ )
					{
						CategoryId = Template[i].childNodes[0].text;							
	  					CategoryName = Template[i].childNodes[1].text;	   						
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
					
					comboid.selectedIndex = 1;  
				
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
		if(document.getElementById('TemplateNameEng').value == '')
		{
			if(document.getElementById('TemplateNameGuj').value != '')
				document.getElementById('TemplateNameEng').value = document.getElementById('TemplateNameGuj').value + '_Eng';
			else
			{
				alert('Please enter Template Name...');
				return false;
			}
		}	
		if(document.getElementById('TemplateNameGuj').value == '')
		{
			if(document.getElementById('TemplateNameEng').value != '')
				document.getElementById('TemplateNameGuj').value = document.getElementById('TemplateNameEng').value + '_Guj';
			else
			{
				alert('Please enter Template Name...');
				return false;
			}
		}	
		if(document.getElementById('TemplateDescEng').value == '')
		{
			if(document.getElementById('TemplateDescGuj').value != '')
				document.getElementById('TemplateDescEng').value = document.getElementById('TemplateDescGuj').value + '_Eng';
			else
			{
				alert('Please enter Template Description...');
				return false;
			}
		}	
		if(document.getElementById('TemplateDescGuj').value == '')
		{
			if(document.getElementById('TemplateDescEng').value != '')
				document.getElementById('TemplateDescGuj').value = document.getElementById('TemplateDescEng').value + '_Guj';
			else
			{
				alert('Please enter Template Description...');
				return false;
			}
		}
		if(document.getElementById('Template').selectedIndex == 0)
		{
			alert('Please enter Template Category...');
			return false;
		}
		
		var url = "${contextPath}/hdiits.htm?actionFlag=defineTemplate"
			+ "&TemplateNameEng=" + document.getElementById('TemplateNameEng').value 
			+ "&TemplateNameGuj=" + document.getElementById('TemplateNameGuj').value 
			+ "&TemplateDescEng=" + document.getElementById('TemplateDescEng').value 
			+ "&TemplateDescGuj=" + document.getElementById('TemplateDescGuj').value
			+ "&Category=" + document.getElementById('Template')[document.getElementById('Template').selectedIndex].text
			+ "&attachmentName=TemplateAttach"
			+ "&formName=TemplateForm"
			+ "&attachmentType=Document"
			+ "&attachmentTitle=TemplateAttachment"
			+ "&multiple=Y&rowNumber=1";
		
		document.forms[0].action=url;
		document.forms[0].submit();
	}

	function changeCombo(opt)
	{
		alert(opt.checked);
		if(opt.checked)
			document.getElementById('Template').style.display = 'none';
		else
			document.getElementById('Template').style.display = '';
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
	
</script>

<% 
}catch(Exception e)
{
	e.printStackTrace();	
}

%>

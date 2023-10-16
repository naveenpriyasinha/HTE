
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
<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables"	scope="request" />
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="wfRefList"	value="${resultMap.wfRefList}"></c:set> 

<hdiits:form name="frmWfview" id="frmWfview" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.HIRREFDETAILS" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>
<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">
	<br><br><br>
	<table align="center" id="table1" border="0"> 	
	<tr>		
		<td><hdiits:caption captionid="WF.HIRREFID" bundle="${wfLables}" /></td>		
		<td align="left">
				<hdiits:select name="selrefid" id="selrefid1" captionid="WF.HIRREFID" validation="sel.isrequired" bundle="${wfLables}" mandatory="true" onchange="ajaxFunction()" >
					<option value="0">Select</option>
					<c:forEach  items="${wfRefList}" var="wflookup">
							<option value='<c:out value="${wflookup.hierachyRefId}"/>' selected="true">
								<c:out value="${wflookup.referenceName}" />
							</option>								
					</c:forEach>
				</hdiits:select>			
		 </td>	
	</tr>	
	</table>	
	
	<br><br><br><br>
	<table   id="heading1" border="0" align="center" style="display:none">	
	<th>Post Based Hierarchy Records!!!</th>
	</table>
	<table   id="heading2" border="0" align="center" style="display:none">
	<th>Role Based Hierarchy Records!!!</th>		
	</table>	
	
	<br><br>
	<table   id="dispTable" border="2" width="50%" style="display:none" align="center">
		<th class="datatableheader">Id</th>
		<th class="datatableheader">LevelId</th>
		<th class="datatableheader">&nbsp;&nbsp;&nbsp;&nbsp;</th>
	</table>
	<hdiits:hidden name="SeqIdhiddentxt"/>
	<hdiits:hidden name="FlagHiddentxt"/>
</div>
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script type="text/javascript">  
function ajaxFunction()
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
        var url = "${contextPath}/hdiits.htm?actionFlag=viewHierarchyRef&refId="+document.forms[0].selrefid.value;        
        xmlHttp.onreadystatechange = function()
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;	
									
/******************************************  Code to delete table values  **********************************************/	
					
						document.getElementById('dispTable').style.display = '';   	
						var totalRows=document.getElementById("dispTable").rows.length;
						for(var i=1;i<eval(totalRows);i++)
						{							
							var trow=document.getElementById('dispTable');      		
							trow.deleteRow(1);
						}
						
/*******************************************	End of code to delete table values  *************************************/	
					  
					var tableentries1 = XMLDoc.getElementsByTagName('postIdMapped');
					var tableentries = XMLDoc.getElementsByTagName('roleIdMapped');						
					var tableentries2 = null;	
					var tflag = 0;					
					if(tableentries1 != null && tableentries1.length != 0)
					{		
							document.getElementById('heading1').style.display = '';
							document.getElementById('heading2').style.display = 'none';  									
							tableentries2 = tableentries1;
							tflag = 1;
					}
					else if(tableentries != null && tableentries.length != 0)
					{
							document.getElementById('heading1').style.display = 'none'; 
							document.getElementById('heading2').style.display = ''; 							
							tableentries2 = tableentries;
							tflag = 1;
					}	
					if(tflag == 1)
					{
						for ( var i = 0 ; i < tableentries2.length ; i++ )
	   					{	   						   							
	   						document.getElementById('dispTable').style.display = '';   					
	   						postName = tableentries2[i].childNodes[0].text;
	   						levelId = tableentries2[i].childNodes[1].text;	
	   						sequenceId = tableentries2[i].childNodes[2].text;	   						
	   						flagValue=tableentries2[i].childNodes[3].text;	
	   						document.forms[0].SeqIdhiddentxt.value=sequenceId;   	   						
	   						document.forms[0].FlagHiddentxt.value=flagValue;
	   						
	   						var a=document.getElementById('dispTable').insertRow();
			     			var col1=a.insertCell(0);
							var col2=a.insertCell(1);
							var col3=a.insertCell(2);
							col1.align="center";
							col2.align="center";
							col3.align="center";
							col1.innerHTML=postName;
							col2.innerHTML=levelId;
							var delString = "<a href='#' style=\"cursor:hand\" onClick=\"removeFunction(this," +sequenceId+ " )\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
							col3.innerHTML= delString;
						 }  
					}
					else
					{
							document.getElementById('heading1').style.display = 'none';
							document.getElementById('heading2').style.display = 'none';  
   							document.getElementById('dispTable').style.display = 'none';   	   							
   							alert("<fmt:message key='WF.CONFHIER' bundle='${wfLables}'/>");	   											    	
					}
				 }				
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
		xmlHttp.send(encodeURIComponent(null));
		return true;
}
 
function removeFunction(src,obj)
{
	if(confirm("Are You Sure You Want To Delete the Record?")==true)
	{
		var row = src.parentElement.parentElement;
		document.all("dispTable").deleteRow(row.rowIndex);
		deleteRowAjaxfunction(obj);
		
		var rowlen=document.getElementById('dispTable').rows.length;				
		if(rowlen==1)
		{
			document.getElementById('dispTable').style.display = 'none'; 
			document.getElementById('heading1').style.display = 'none';
			document.getElementById('heading2').style.display = 'none';
		}
	}			
}
	
function deleteRowAjaxfunction(obj)
{		
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
        var url = "${contextPath}/hdiits.htm?actionFlag=delHierarchyRef&seqId="+obj+"&flagVal="+document.forms[0].FlagHiddentxt.value;       
 	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{					
				}				
			}
   		 }		
   		 			
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
		xmlHttp.send(encodeURIComponent(null));
		return true;
}
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

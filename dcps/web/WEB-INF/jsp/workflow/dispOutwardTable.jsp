<%
try 
{
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

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="FileNum" value="${resultMap.FileNum}"></c:set>
<c:set var="CorrNum" value="${resultMap.CorrNum}"></c:set>

<hdiits:form name="frmdispOutwardTab" id="frmdispOutwardTabid" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="hiddFileNo" default="${FileNum}"/>
<hdiits:hidden name="hiddCorrNo" default="${CorrNum}"/>
<hdiits:hidden name="hiddOutwardId" default="0"/>
<hdiits:hidden name="hiddOutwardSubj" default="0"/>
<hdiits:hidden name="hiddOutwardRefNo" default="0"/>
	<table width="100%">
		<tr>
			<td class="datatableheader">
				<hdiits:caption	captionid="FMS.OUTWARD" bundle="${fmsLables}" />
			</td>
		</tr>
	</table>
	
	<br><br>	
	<table id="OutwardDetails" align="center" border="2" style="display:;"  width="70%">
	<tr>		
			<td align="center" class="datatableheader">SrNo</td>
			<td align="center" class="datatableheader">OutwardNo</td>
			<td align="center" class="datatableheader">Subject</td>	
			<td align="center" class="datatableheader">OutwardRefNo</td>	
	</tr>
	</table>	
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<script type="text/javascript">	
view();
var length=0;
var srno=0;
var outwardno=0;
function deleteallrows()
{
	var outwardtab = document.getElementById('OutwardDetails')
	var rows = outwardtab.rows.length; 
	for(var i=1;i<rows;i++)
	outwardtab.deleteRow(outwardtab.rowIndex);
} 

function view()
{	
		srno=0;			
		deleteallrows();		
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
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}     	  	    	

		

		if(document.forms[0].hiddFileNo.value!=0)
		{
    		var url = "${contextPath}/hdiits.htm?actionFlag=viewOutwardDtls&fileNo="+document.forms[0].hiddFileNo.value;
		}			    		   
		if(document.forms[0].hiddCorrNo.value!=0)
		{
			var url = "${contextPath}/hdiits.htm?actionFlag=viewOutwardDtls&CorrNo="+document.forms[0].hiddCorrNo.value;
		}
		
    	xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var tableentries = XMLDoc.getElementsByTagName('fileIdMapped');						
					length=tableentries.length;							
					if(length<=0)
					{
						alert("<fmt:message key='FMS.NOOUTWARD' bundle='${fmsLables}'/>");	
					}
					for (i = 0 ; i < tableentries.length ; i++ )
     				{ 		
  						outwardno=tableentries[i].childNodes[0].text;				
  						document.forms[0].hiddOutwardId.value=tableentries[i].childNodes[1].text;  	 
  						document.forms[0].hiddOutwardRefNo.value=tableentries[i].childNodes[3].text;  						
  						document.forms[0].hiddOutwardSubj.value=tableentries[i].childNodes[2].text; 
  						display();  									
     				}													
				}
	   	   }
	   	 }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
		xmlHttp.send(encodeURIComponent(null));
		return true;
}


function display()
{  						
		document.getElementById('OutwardDetails').style.display = '';	
		srno++;
		var b=document.getElementById('OutwardDetails').insertRow();		
	    var col1=b.insertCell(0);
		var col2=b.insertCell(1);	
		var col3=b.insertCell(2);
		var col4=b.insertCell(3);		
		col1.align="center";
		col2.align="center";						
		col3.align="center";
		col4.align="center";
	    col1.innerHTML=srno;		   
	    var show="<a href='#' style=\"cursor:hand\" onClick=\"displayDetails(" + document.forms[0].hiddOutwardId.value+ ")\">"+ outwardno +"</a>";																					   		    
		col2.innerHTML=show;
		col3.innerHTML=document.forms[0].hiddOutwardRefNo.value;
		col4.innerHTML=document.forms[0].hiddOutwardSubj.value;		
}

function displayDetails(outwardId)
{	
	var url = "${contextPath}/hdiits.htm?actionFlag=retrieveOutward&loutwardId="+outwardId;
	window.open(url, '', 'status=no,toolbar=no,scrollbars=yes,width=1500,height=700');	
}
</script>
<%
}
catch (Exception e) 
{
	e.printStackTrace();
}
%>

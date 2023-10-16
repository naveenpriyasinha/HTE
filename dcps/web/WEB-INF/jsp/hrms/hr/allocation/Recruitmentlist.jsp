<%
try {
	
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 




<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script> 

<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="newlist" value="${resValue.newlist}"> </c:set>



<html>

<script type="text/javascript">

var emparray = new Array();
var emparraychkbox=new Array();
var empcount = 0 ;
var msg='';
var status;
function checkclick(form){

if(form.checked == true)
{
emparray[empcount]=document.getElementById(form.id).value;
emparraychkbox[empcount]=form.id;
empcount++;

}else
{ 
  for(var i=0; i<emparray.length; i++)
  {  
  if(emparray[i]== document.getElementById(form.id).value){
  emparray.splice(i,1);
  emparraychkbox.splice(i,1);
    empcount--;
  }

  
  }

}


}
function clearf(form)
{


			var idf=form.id; 
			var href='./hrms.htm?actionFlag=getrecruitmentdetails&allocationid='+idf;
		
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
			
				
}
function ValidateandGenerateFile()
{
if(emparraychkbox.length==0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.onerecord"/>');
}
else
{

try{   
    			xmlHttp=new XMLHttpRequest();    			   
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      					 
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");                   	        
        		  		}
				      	catch (e)
				      	{			           	 			           	   	  
			            	  return false;        
			      		}
			 		}
			 					 
        	}        
			var url = "hrms.htm?actionFlag=RecruitmentValidation&emparray="+emparray;    
			xmlHttp.open("POST", encodeURI(url) , false);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));

if(status==1)
  {
  
                   for(var c=0; c<emparray.length; c++)
  					{  
					
 						emparray.splice(c,1);
  						emparraychkbox.splice(c,1);
   						empcount--;
   						
 					}
  
  }
}
}
function processResponse()
{

if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var xmlStr = xmlHttp.responseText;
					
			    	var XMLDoc=getDOMFromXML(xmlStr);
			    			    

			    	var ErrorMsg=XMLDoc.getElementsByTagName('ErrorMsg');
			    
			     msg=ErrorMsg[0].childNodes[0].text;
			     
			     if(msg.length!=0)
			     {
			      errorMsg(); 
			    }
			 
			}
		}
				
 		
}
function errorMsg()
{
  if(msg==1)
			    {
			 		 document.getElementById('btnSubmit').disabled='true';
			   		 	document.Allocation.action="hdiits.htm?actionFlag=createRecruitmentFile&emparray="+emparray;
						document.Allocation.submit();
			    }
			    else
			    {
			   		 document.Allocation.reset();
			    	document.getElementById('errorMessage').style.display='';
			    	document.getElementById('errorBox').value=msg;	
			    	
					status=1;
 					 for(var b=0; b<emparray.length; b++)
  					{  
					
 						emparray.splice(b,1);
  						emparraychkbox.splice(b,1);
   						empcount--;
   						
 					}
 					
 				}
}
function Closebt()
{	
	method="POST";
	document.Allocation.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.Allocation.submit();
}

</script>
</html>

<hdiits:form name="Allocation" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Recruitment.details" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
	
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<table width="100%">
			<tr style="display:none" id="errorMessage">
				<td class="fieldLabel" width="100%" >
					<textarea rows="5"  name="errorBox" id="errorBox" readonly="readonly" style="color: #E41B17;width:100%;border:0; bgcolor: #FFFFFF; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #FFFFFF;"></textarea>
				</td>
			</tr>
			</table>
			
			<c:if test="${not empty newlist}">
			<hdiits:fieldGroup titleCaptionId="Allocation.list" bundle="${AllocLab}" expandable="true" mandatory="true" > 
	
	
	
	<table id='allocate' border="1" cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE" >
			<tr class="datatableheader">
			<td  align="center" ></td>
				<td  align="center" ><b><hdiits:caption captionid="Allocation.name" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></b></td>
				
				<td align="center"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
				<td align="center"> <b><hdiits:caption captionid="Allocation.view" bundle="${AllocLab}"/></b> </td>
				
				</tr>
				
				<c:forEach var="newlist" items="${newlist}">
				<tr>
					<td align="center"><hdiits:checkbox name="check${newlist.allocid}" id="check${newlist.allocid}" value="${newlist.allocid}" onclick="checkclick(this);" /></td>
					<td align="center">${newlist.empName}</td>
					<td align="center">${newlist.post}</td>
					<td align="center"><fmt:formatDate value="${newlist.dob}" pattern="dd/MM/yyyy" /></td>
					<td align="center">${newlist.empFirstName}</td>
					<td align="center">${newlist.yearrecruit}</td>
					
					<td align="center">${newlist.jurisdiction}</td>
					<td align="center"><a href="#"  name="${newlist.allocid}" id="${newlist.allocid}" onclick="clearf(this)"><fmt:message key="Allocation.view" bundle="${AllocLab}"/> </a> </td>
				</tr>
				
			</c:forEach>
			</table>
			</hdiits:fieldGroup>
	</c:if>
  		
  		  
	</div>	
	
	<table align="center">
	<tr align="center">
	<td align="center">
	
	<hdiits:button name="btnSubmit" type="button" captionid="Allocation.Submit" bundle="${AllocLab}"  onclick="ValidateandGenerateFile()"/>
	</td>
	
		<td align="center">
		<hdiits:button name="close" type="button" captionid="Allocation.Close" bundle="${AllocLab}" onclick="Closebt()"/>
		</td>
		
		
		</table>
		
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>	

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>

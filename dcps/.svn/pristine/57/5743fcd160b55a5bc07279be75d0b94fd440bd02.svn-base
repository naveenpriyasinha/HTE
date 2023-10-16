<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
<script language="JavaScript1.2">

function disabletext(e){
return false
}

function reEnable(){
return true
}

//if the browser is IE4+
document.onselectstart=new Function ("return false")

//if the browser is NS6
if (window.sidebar){
document.onmousedown=disabletext
document.onclick=reEnable
}


</script>	
<fmt:setBundle basename="resources.common.cancellation.CommonCancellation" var="CmnCancel" scope="request"/>	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="i" value="1"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
      
     <script  type="text/javascript"> 
    var flag=0;  
function getDocId(cmb)
  {
			var id=cmb.value;	
		
			if(id=='') {return;}					
			
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
			var url = "hrms.htm?actionFlag=getCancelAppDtl&Requesttype="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = docIDResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}			

function docIDResponse(){
						
			if (xmlHttp.readyState == 4){
			     
				if (xmlHttp.status == 200){     

						var textId;												
		            	var z=document.getElementById('requestTypeCombo');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;	
				    					    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('ApplicationName');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('DocID'); 
				    	
				    	for (var i=z.length;i>=0;i--){	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}
	     				var a=document.createElement('option');	     		     							
						a.text='<fmt:message key="Can.select" bundle="${CmnCancel}" />';
						a.value="0";
						try
	   						{
	    						z.add(a,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(a);	   			 				 
	   						} 
						for ( var i = 0 ; i < SubQuaStr.length ; i++ ){
							     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     				    textId=SubCoCurrStr_ID[i].childNodes[0].text;
	     					var y=document.createElement('option');
	     					
		 					y.text=value;
							y.value=textId;

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
				else 
				{  			

				}
			}
}
     function Closebt()
{	
	method="POST";
	parent.window.document.Cancellation.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	parent.window.document.Cancellation.submit();
}
     </script> 
<hdiits:form name="Cancellation" validate="true" encType="text/form-data" method="POST" action="hrms.htm?actionFlag=applyCancellation">

<table align="center" id="cancelOptions" >
<tr>
<td><hdiits:caption captionid="Can.Status" bundle="${CmnCancel}"/></td><td></td>
<td colspan="3"><hdiits:select name="statusCombo" onchange="getDocId(this);" id="statusCombo" mandatory="true">
<hdiits:option value="0"><hdiits:caption captionid="Can.select" bundle="${CmnCancel}"/></hdiits:option>
				   <hdiits:option value="ApproveCancel"><hdiits:caption captionid="Can.approve" bundle="${CmnCancel}"/></hdiits:option>
				   <hdiits:option value="PendingCancel"><hdiits:caption captionid="Can.pending" bundle="${CmnCancel}"/></hdiits:option>
				   </hdiits:select>
			   </td>

</tr>
<tr>
<td><hdiits:caption captionid="Can.RequestType" bundle="${CmnCancel}"/></td><td></td>
<td colspan="3"><hdiits:select name="requestTypeCombo" id="requestTypeCombo" mandatory="true">
				   <hdiits:option value="0"><hdiits:caption captionid="Can.select" bundle="${CmnCancel}"/></hdiits:option>
				   </hdiits:select>
			   </td>

</tr>
<tr>
<td><font color="Green"><hdiits:caption captionid="Can.AppliedBtwDate" bundle="${CmnCancel}"/></font></td>
<td></td>
<td><hdiits:dateTime name="fromDate" captionid="Can.fromDate" bundle="${CmnCancel}" mandatory="true" validation="txt.isrequired" ></hdiits:dateTime></td>
<td></td> 
<td><hdiits:dateTime name="toDate" captionid="Can.toDate" bundle="${CmnCancel}" mandatory="true" validation="txt.isrequired" ></hdiits:dateTime></td>

</tr>
<tr>
<tr>
</tr>
<tr>
<td colspan="4" align="center"><hdiits:button type="button" name="submitButton" captionid="Can.Submit"  onclick="populateRecords()" bundle="${CmnCancel}" ></hdiits:button>
<hdiits:button name="close" type="button" captionid="Can.Close" bundle="${CmnCancel}" onclick="Closebt()" /></td>

</tr>
</table>
<script type="text/javascript">

function populateRecords(){
if(document.getElementById('statusCombo').value==0)
{
alert('<fmt:message bundle="${CmnCancel}" key="Canalrt.selstatus"/>');
return false;
}

else if(document.getElementById('requestTypeCombo').value==0)
{

alert('<fmt:message bundle="${CmnCancel}" key="Canalrt.selreqtype"/>');
return false;
}
else if(document.getElementById('fromDate').value==''){

alert('<fmt:message bundle="${CmnCancel}" key="Canalrt.selfrmDtae"/>');
return false;

}

else if(document.getElementById('toDate').value==''){

alert('<fmt:message bundle="${CmnCancel}" key="Canalrt.seltoDate"/>');
return false;
}
else if(compareDate(document.Cancellation.fromDate.value,document.Cancellation.toDate.value)<0)
{
alert('<fmt:message bundle="${CmnCancel}" key="Canalrt.cmpDate"/>');
return false;
}
else{
if(document.getElementById('statusCombo').value=='ApproveCancel')
{
flag=1;
	//document.getElementById('Reason').style.display='';
	
}
if(document.getElementById('statusCombo').value=='PendingCancel')
{
	flag=2;
	
	
	
	
}
//document.getElementById('Apply_can').style.display='';

var frmDate=document.getElementById('fromDate').value;
var todate=document.getElementById('toDate').value;
var reqType=document.getElementById('statusCombo').value;
var appname=document.getElementById('requestTypeCombo').value;
//document.getElementById('displayFrame').src = "hdiits.htm?actionFlag=getCancelRecord&statusCombo="+reqType+"&requestTypeCombo="+appname+"&fromDate="+frmDate+"&toDate="+todate;

top.frames['displayFrame'].location="hdiits.htm?actionFlag=getCancelRecord&statusCombo="+reqType+"&requestTypeCombo="+appname+"&fromDate="+frmDate+"&toDate="+todate;


}
}
</script>


</hdiits:form>







<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

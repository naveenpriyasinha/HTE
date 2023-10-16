<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="resources.ess.punishment.punishmentAlerts" var="ncform" scope="request"/>
<fmt:setBundle basename="resources.ess.punishment.punishment" var="commonform" scope="request"/>
	

<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="msg" value="${resValue.msg }"></c:set>
<c:set var="id" value="${resValue.id }"></c:set>
<c:set var="name" value="${resValue.forwardedTo }"></c:set>
<fmt:formatDate value="${resValue.currentDate}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="date" />
<fmt:formatDate value="${resValue.currentDate}" pattern="HH:mm"
	dateStyle="medium" var="time" />
<c:set var="date" value="${date}" ></c:set>
<c:set var="time" value="${time}" ></c:set>	

<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

	
<script>

	function SearchEmp(){
		var href='${rootUrl}'+'?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	}


var userId=new Number();
var empArray = new Array();

function empSearch(from){

	for(var i=0; i<from.length; i++){
		
		empArray[i] = from[i].split("~"); 
		
	}
	var single = empArray[0];
	

	userId=single[0];
	
	getUId(single[0]);
}
	function getUId(uid)
	{
			
			
			
		 try{   
		 		
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new 
                        ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
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
		//xmlHttp=new XMLHttpRequest(); 
		var url = "hrms.htm?actionFlag=GrvEmpSearchData&id="+uid;    
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = processResponse;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}		
	function processResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{          	
									
						var textId;						
		            	var w=document.getElementById('a');	
		            	var x=document.getElementById('b');
		            	var y=document.getElementById('c');
		            	var z=document.getElementById('d');	
		            	var zz=document.getElementById('e');	
		            	                       		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	
				    	var PSOName = XMLDoc.getElementsByTagName('PSOName');
				    	
				    	
				    	var district = XMLDoc.getElementsByTagName('district');
		    	        var PSName = XMLDoc.getElementsByTagName('PSName');
		    	        var GPFNo = XMLDoc.getElementsByTagName('GPFNo');
		    	        var desgn=	XMLDoc.getElementsByTagName('Desgn');
		    	    
		    	        w.value=PSOName;	 
		    	        x.value=district;
		    	        y.value=PSName;
		    	        z.value=GPFNo; 
		    	        zz.value= desgn; 	
		    	        		    	     		     							
						for ( var i = 0 ; i < PSOName.length ; i++ )
	     				{	     		     								
	     				    value=PSOName[i].childNodes[0].text;	
	     				    if(value==null)
	     				    	alert("PSO name null");     				    
	     					w.value=value;
	     					  
	     											     					
	           			}
	           			for ( var i = 0 ; i < district.length ; i++ )
	     				{	     		     								
	     				    value=district[i].childNodes[0].text;	
	     				    if(value==null)
	     				    	alert("district name null");        				    
	     					x.value=value;
	     					
	     											     					
	           			}
	           			for ( var i = 0 ; i < PSName.length ; i++ )
	     				{	     		     								
	     				    value=PSName[i].childNodes[0].text;	 
	     				    if(value==null)
	     				    	alert("PS name null");       				    
	     					y.value=value;
	     					
	     											     					
	           			}
	           			for ( var i = 0 ; i < GPFNo.length ; i++ )
	     				{	     		     								
	     				    value=GPFNo[i].childNodes[0].text;	
	     				    if(value==null)
	     				    	alert("GPFNo name null");        				    
	     					z.value=value;
	     					
	     											     					
	           			}
	           			for ( var i = 0 ; i < desgn.length ; i++ )
	     				{	     		     								
	     				    value=desgn[i].childNodes[0].text;	
	     				    if(value==null)
	     				    	alert("desgn name null");        				    
	     					zz.value=value;
	     					
	     											     					
	           			}
	           					
				}
				else 
				{  			
					alert("ERROR");					
				}
			}
}		
			function grievancetypeother(l)
			{
				 if(l.value == 'o')
						 {
							 	document.getElementById('other').style.display='';
							 
						 }
				 else
						 {
						 		document.getElementById('other').style.display='none';						 	
						 }
				
								
			}
			
			
			
			function disposalchange(l)
			{
				 if(l.value == 'Bond')
						 {
							 	document.getElementById('bond').style.display='';
							 	document.getElementById('SelfBond').style.display='none';
						 }
				 else  if(l.value == 'SelfBond')
				 		{
						 		document.getElementById('SelfBond').style.display='';
						 		document.getElementById('bond').style.display='none';
						 	
						 }
				 else
						 {
						 		document.getElementById('SelfBond').style.display='none';
						 		document.getElementById('bond').style.display='none';
						 	
						 }
				
								
			}
			
			
			
			
			function ch_disable()
			{
				
				document.frmFIR.occtimeto.disabled=true;
				document.frmFIR.occtimefrom.disabled=true;
				document.frmFIR.occdatefrom.disabled=true;
			}
			
			function goin(l)
			{	
				var id = l.value;
				if(id == 'yes7')
				{
					document.getElementById('yes7').style.display='';
					document.getElementById('no7').style.display='none';
				}
				else if(id == 'no7')
				{
					document.getElementById('yes7').style.display='none';
					document.getElementById('no7').style.display='';
				}
			}
			
			
			
			
			
			
			function addressc()
			{
				if(document.frmFIR.addressr[1].checked)
				{
					document.getElementById('address1').style.display='';
					document.getElementById('address2').style.display='none';
				}
				else
				{
					document.getElementById('address1').style.display='none';
					document.getElementById('address2').style.display='';
				}
			}
			function addressacc()
			{
				if(document.frmFIR.address_acc[1].checked)
				{
					document.getElementById('addressacc1').style.display='';
					document.getElementById('addressacc2').style.display='none';
				}
				else
				{
					document.getElementById('addressacc1').style.display='none';
					document.getElementById('addressacc2').style.display='';
				}
			}
			function addresscompl()
			{
				if(document.frmFIR.address_compl[1].checked)
				{
					document.getElementById('addresscompl1').style.display='';
					document.getElementById('addresscompl2').style.display='none';
				}
				else
				{
					document.getElementById('addresscompl1').style.display='none';
					document.getElementById('addresscompl2').style.display='';
				}
			}
			
				function rlimit1()
			{
				if(document.frmFIR.limit[0].checked)
				{
					document.getElementById('yeslimit').style.display='';
					document.getElementById('nolimit').style.display='none';
				}
				else
				{
					document.getElementById('yeslimit').style.display='none';
					document.getElementById('nolimit').style.display='';
				}
			}
			function select_show(l)
			{ 
				var id = l.value;
				if(id == '')
				{
					return;
				}
				if(id == 'id1')
				{
					document.getElementById('bbb').style.display='';
					//document.getElementById('aaa').style.display='none';					
					document.getElementById('id1').style.display='';
					document.getElementById('id2').style.display='none';
					document.getElementById('id3').style.display='none';
				}
				if(id == 'id2')
				{
				
					document.getElementById('id2').style.display='';
					document.getElementById('id1').style.display='none';
					document.getElementById('id3').style.display='none';					
				}
				if(id == 'id3')
				{
					//d/ocument.getElementById('aaa').style.display='';
					document.getElementById('bbb').style.display='none';
					document.getElementById('id3').style.display='';
					document.getElementById('id2').style.display='none';
					document.getElementById('id1').style.display='none';					
				}
			}
			function forudjj(u)
			{ 
				var id = u.value;
				if(id == '')
				{
					return;
				}
				if(id == 'ud')
				{
					
					document.getElementById('ud').style.display='';
					document.getElementById('no').style.display='none';
				}
				
			}
function property()
			{
				if(document.frmFIR.prop1[0].checked)
				{
					document.getElementById('property1').style.display='';
				}
				else
				{
					document.getElementById('property1').style.display='none';
					document.getElementById('propertytable').style.display='none';
					document.getElementById('del111').style.display='none';
				}
			}
			function showOccOther(l)
			{
				var val=l.value;
				if(val == 'o')
				{
					document.getElementById('occother').style.display='';
				}
				else
				{
					document.getElementById('occother').style.display='none';
				}
			}
			
			function showOccOther1(l)
			{
				var val=l.value;
				if(val == 'o')
				{
					document.getElementById('occother1').style.display='';
				}
				else
				{
					document.getElementById('occother1').style.display='none';
				}
			}
			function rlimit()
			{
				if(document.frmFIR.BPM[1].checked)
				{
					document.getElementById('nolimit').style.display='';
					document.getElementById('yesbpm').style.display='none';
					document.getElementById('nobpm').style.display='';
					document.getElementById('id1').style.display='';
					document.getElementById('id2').style.display='none';
					document.getElementById('id3').style.display='none';
				}
				else
				{
					document.getElementById('nolimit').style.display='none';
					document.getElementById('yesbpm').style.display='';
					document.getElementById('nobpm').style.display='none';
					document.getElementById('id3').style.display='';
					document.getElementById('id2').style.display='none';
					document.getElementById('id1').style.display='none';
				}
			}
			
			
			
			
</script>
<!--<script type="text/javascript"
	src="<c:url value="script/common/tabcontent.js"/>">
	</script>-->
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>


<hdiits:form name="frmNC" validate="true" id="form1" method="post" encType="multipart/form-data" ><!-- body part of any page start-->





<!-------------------------------------------------------- tb1 --------------------------------------------------->


<br>
<br>
<br>
<br>
<br>

<table height="100">
		<tr>
			<td rowspan="100" colspan="40"></td>
		</tr>
		<tr rowspan="30" colspan="40">
			<th rowspan="30" colspan="40"></th>
		</tr>
				<tr>
			<th rowspan="30" colspan="40"></th>
		</tr>
	</table>
	<hr align="center" width=" 50%">
	<table width="100%" align="center">
   
  
  <tr  ></tr>

    <tr><th align="center"><b><hdiits:caption captionid="PS.REQUESTMSG1" bundle="${commonform}"/><hdiits:caption captionid="PS.GREVN" caption="${id }"/>
							<hdiits:caption captionid="PS.REQUESTMSG2" bundle="${commonform}"/><hdiits:caption captionid="PS.GREVN" caption="${name }"/></b>
						</th></tr>
  <tr></tr>
  

	</table>
  <hr align="center" width="50%">
  
  
  
  				

			  


	  




<jsp:include page="../../core/payTabnavigation.jsp" />

</hdiits:form>
<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
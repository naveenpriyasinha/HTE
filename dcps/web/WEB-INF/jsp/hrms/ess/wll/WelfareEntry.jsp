<%
try {
%>

<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setBundle basename="resources.ess.wll.wll_AlertMessages" var="welfareLbl1" scope="request"/>
<fmt:setBundle basename="resources.ess.wll.wll" var="welfareLables" scope="request"/>

<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>

<script><!--




function getInfo(q)
{
	    //alert(q.value);
		if(q.value=='Select'){
		var z=document.getElementById('a');	
       	var x=document.getElementById('b');
       	var y=document.getElementById('c');
       	var r=document.getElementById('d');	
		z.value='';            	
		x.value='';
		y.value='';
		r.value='';		            	
		return;}
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
			var url = "hrms.htm?actionFlag=WelfareRequest&flag=f&id="+q.value;    
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
		            	var z=document.getElementById('a');	
		            	var x=document.getElementById('b');
		            	var y=document.getElementById('c');
		            	var r=document.getElementById('d');		                        		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	//alert(xmlStr);
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	//alert(XMLDoc);
				    	var SubQuaStr = XMLDoc.getElementsByTagName('ReqId');
				    	var Qua = XMLDoc.getElementsByTagName('ReqId1');
		    	        var SubQua = XMLDoc.getElementsByTagName('ReqId4');
		    	        var UnitInst = XMLDoc.getElementsByTagName('ReqId5');			    	 				    	     		     							
						for ( var i = 0 ; i < SubQuaStr.length ; i++ )
	     				{	     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     					z.value=value;
	     					  
	     											     					
	           			}
	           			for ( var i = 0 ; i < Qua.length ; i++ )
	     				{	     		     								
	     				    value=Qua[i].childNodes[0].text;	     				    
	     					x.value=value;
	     					
	     											     					
	           			}
	           			for ( var i = 0 ; i < SubQua.length ; i++ )
	     				{	     		     								
	     				    
	     				    
	     				    value=SubQua[i].childNodes[0].text;	
	     				    r.value=value; 
	     				    //alert(value);     				    
	     					if (value == "Assistance")
	     					{
	     					
	     					document.getElementById('Hide').style.display='none';
	     					document.getElementById('Installment').style.display='none';
	     					document.getElementById('c').style.display='none';
	     					document.getElementById('killme').style.display='none';
	     					document.getElementById('killme1').style.display='none';
	     					}
	     					else
	     					{
	     					document.getElementById('Hide').style.display='';
	     					document.getElementById('Installment').style.display='';
	     					document.getElementById('c').style.display='';
	     					document.getElementById('killme').style.display='';
	     					document.getElementById('killme1').style.display='';
	     					}
	     				 	 						     					
	           			}
	           			for ( var i = 0 ; i < UnitInst.length ; i++ )
	     				{	     		     								
	     				    value=UnitInst[i].childNodes[0].text;	     				    
	     					y.value=value;  						     					
	           			}
	           			
	           			
	           			
	           			
	           			
	           			
	           			// code to populate more fields
	           			
	        
		    	
		    	
		    	var removeRow = XMLDoc.getElementsByTagName('RemoveRow');
	           			
	           			
	           			
				}
				else 
				{  			
					alert("ERROR");					
				}
			}
}

function Age(birthDate)
		{					
			if(birthDate!=null)
			{	
				var splitDate=birthDate.split("-");				
				var bday=parseInt(splitDate[1]);
				var bmo=(parseInt(splitDate[2])-1);
				var byr=parseInt(splitDate[0]);				
				var byr;
				var age;
				var now = new Date();
				tday=now.getDate();
				tmo=(now.getMonth());
				tyr=(now.getFullYear());								
				if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr}				
				else  {age=byr+1}
				return (tyr-age);						
			}
		}


function checkval()
{
  var z=document.getElementById('Installment');
  
 /* if(z.value == 'null' || z.value =='')
  {
   
   alert('<fmt:message  bundle="${welfareLbl1}" key="HR.ESS.Installment"/>');
   
   //alert('Enter Installment amount'); 
   document.getElementById('Installment').select();
   document.getElementById('Installment').focus();
   return;
  }*/
  checknum();

}


function submit4()
{	
	document.welfareentry.method="POST";
	document.welfareentry.action="./hdiits.htm?actionFlag=getHomePage";
	//alert(document.welfareentry.action);
	document.welfareentry.submit();
}


function checknum()
{

var x=document.getElementById('b');

var amount=document.getElementById('Amount');

var Installment=document.getElementById('Installment');

if(eval(x.value) < eval(amount.value))
{
  
  alert('<fmt:message  bundle="${welfareLbl1}" key="HR.ESS.req_ent"/>');
  //alert('The required amount should be less than entitled amount');
  document.getElementById('Amount').select();
   document.getElementById('Amount').focus();
  return ; 
}
if(eval(amount.value) < eval(Installment.value))
{
   alert('<fmt:message  bundle="${welfareLbl1}" key="HR.ESS.ist_req"/>');
  //alert('The installment should be less than reqired amount');
  document.getElementById('Installment').select();
   document.getElementById('Installment').focus();
  return ;
}
}

function checknum1()
{

var x=document.getElementById('b');

var amount=document.getElementById('Amount');

var Installment=document.getElementById('Installment');

if(eval(x.value) < eval(amount.value))
{
  
  alert('<fmt:message  bundle="${welfareLbl1}" key="HR.ESS.req_ent"/>');
  //alert('The required amount should be less than entitled amount');
  document.getElementById('Amount').select();
   document.getElementById('Amount').focus();
  return ; 
}
if(eval(amount.value) < eval(Installment.value))
{
   alert('<fmt:message  bundle="${welfareLbl1}" key="HR.ESS.ist_req"/>');
  //alert('The installment should be less than reqired amount');
  document.getElementById('Installment').select();
   document.getElementById('Installment').focus();
  return ;
  
  
  
}

}





function show(){
document.getElementById("show1").style.display="none";
			
}
function show1(q1){
if(q1.value=='Select' || q1.value=='-1'){
show();
return;
}
else{
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
		var url = "hrms.htm?actionFlag=WelfareRequest&flag1=getData&id="+q1.value;    
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = processResponse1;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));

			document.getElementById("show1").style.display="";
	}
	function processResponse1()
	{
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{ 
	  				//var z1=document.getElementById('name1');	
	            	var x2=document.getElementById('relation1');
	            	var y3=document.getElementById('dob1');
	            	var r4=document.getElementById('address31');
	          		
	          		var xmlStr = xmlHttp.responseText;
				   // alert(xmlStr);
				    var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
	          		//var SubQuaStr1 = XMLDoc.getElementsByTagName('Req1Id1');
			    	var Qua1 = XMLDoc.getElementsByTagName('Req1Id5');
		   	        var SubQua1 = XMLDoc.getElementsByTagName('Req1Id6');
		   	        
		         			
		         		/*for ( var i = 0 ; i < SubQuaStr1.length ; i++ )
		   				{	     		     								
		   				    value=SubQuaStr1[i].childNodes[0].text;	     				    
		   					z1.value=value;  
		   											     					
		         		}*/
		         		for ( var i = 0 ; i < Qua1.length ; i++ )
		   				{	     		     								
		   				    value=Qua1[i].childNodes[0].text;	     				    
		   					x2.value=value;
		   					
		   											     					
		         		}
		         		for ( var i = 0 ; i < SubQua1.length ; i++ )
		   				{	     		     								
		   				    value=SubQua1[i].childNodes[0].text;	     				    
		   					y3.value=value;  						     					
		         		}
		         	  	
	         }	         
	     }
	}
}

function openEMICalc()
{	
	method="POST";
	
	
	url="./hrms.htm?viewName=EmiCalc";
	 window.open(url, "", "width=800,height=600,status=no,resizable=no,top=100,left=400");
	
	//document.welfareentry.action="./hrms.htm?viewName=EmiCalc";
	
	//alert(document.welfareentry.action);
	//document.welfareentry.submit();
}

function checkuser(flag)
{
var name = document.getElementById("abcd1");
var scheme = document.getElementById("abc");
var amount = document.getElementById("Amount");
var reason = document.getElementById("reason");
				if(flag==1)
				{
	
						 alert('<fmt:message  bundle="${welfareLables}" key="HR.ESS.CntForwardYourself"/>');
						return;
				}
				else if(eval(name.selectedIndex) == 0)
				{
				//alert("it is mandatory to select name");
 				 alert('<fmt:message  bundle="${welfareLables}" key="HR.ESS.Selname"/>');


				document.getElementById('abcd1').focus();
				
				}
				else if(eval(scheme.selectedIndex) == 0)
				{
				//alert("it is mandatory to select scheme");
 				 alert('<fmt:message  bundle="${welfareLables}" key="HR.ESS.Selsch"/>');


				document.getElementById('abc').focus();
				
				
				}
				else if((amount.value)=='')
				{
						//alert("it is mandatory to fill amount required");
 				 alert('<fmt:message  bundle="${welfareLables}" key="HR.ESS.FillAmt"/>');


				document.getElementById('Amount').focus();
				
				}
				else if((reason.value)=='')
				{
				//	alert("it is mandatory to fill the reason for amount required");
 				 alert('<fmt:message  bundle="${welfareLables}" key="HR.ESS.FillRsn"/>');


				document.getElementById('reason').focus();
				
			
				}
				else
				{
				
				document.welfareentry.method="POST";

				document.welfareentry.action="./hrms.htm?actionFlag=WelfareSubmit";
						document.welfareentry.submit();
				    		
	           				
	        
		    	}
		    	
			
}


function specialChar(txtarea)
{
	
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
	
	//add or remove characters into this string to be checked 
	str1="!@#$%^&*()<>?/~`-=+{}[]|\:;',."
	for(i=0;i<len;i++)
	{
		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
		
		
		//alert("Special characters are not allowed");
			alert('<fmt:message  bundle="${welfareLables}" key="HR.ESS.SpecialCharsNotAllowed"/>');
			txtarea.focus();
			return;
		}
		
	}

	return;
}






--></script> 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="DepDtsl" value="${resValue.DepDtsl}"></c:set>	
<c:set var="WELFARE_SCHEME" value="${resValue.welfareScheme}"> </c:set>
<c:set var="WELFARE_TYPE" value="${resValue.welfareType}"> </c:set>
<c:set var="WELFARE_DES" value="${resValue.welfareDes}"> </c:set>
<c:set var="WELFARE_CODE" value="${resValue.welfareCode}"> </c:set>
<c:set var="WELFARE_MAX_AMT" value="${resValue.welfareMaxAmt}"> </c:set>
<c:set var="WELFARE_RATE_INT" value="${resValue.welfareRateInt}"> </c:set>
<c:set var="WELFARE_ID" value="${resValue.welfareId}"> </c:set>
<c:set var="FamilyDtsl" value="${resValue.FamilyDtsl}"> </c:set>
<c:set var="forwardFlag" value="${resValue.forwardFlag}"> </c:set>




<hdiits:form  name="welfareentry" validate="true"   encType="multipart/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.EIS.Welfare" bundle="${welfareLables}" captionLang="single"/></b></a></li>
	</ul>
</div>
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">

<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>




<table class="tabtable">

	
	<tr>
		<td width="100%" align="center">
			<font>
				<hdiits:fieldGroup titleCaptionId="HR.EIS.DependentDetails" bundle="${welfareLables}" expandable="true"></hdiits:fieldGroup>
			</font>
		</td>
	</tr>
					<tr>
			<td colspan="4">
			<table  cellpadding="0" cellspacing="0" BGCOLOR="WHITE"
				class="TableBorderLTRBN" width="100%">
				<tr>
	<td width="25%"><b><hdiits:caption captionid="HRMS.Dep.Name" bundle="${welfareLables}"/>:</b></td>			
	<td>
  
    <hdiits:select captionid="HR.EIS.DepDetails" bundle="${welfareLables}" name="abcd1" id="abcd1" mandatory="true" validation="sel.isrequired" sort="true" onchange="show1(this);"> 
			<option value="Select"><hdiits:caption captionid="HRMS.ESS.Select" bundle="${welfareLables}" captionLang="single"/></option>
			<option value="-1"><hdiits:caption captionid="HRMS.ESS.Self" bundle="${welfareLables}" captionLang="single"/></option>			
			<c:forEach var="resValue121" items="${DepDtsl}">
				 				
	    		<option value="${resValue121[0]}"><c:out  value="${resValue121[1]}"></c:out></option>			
			</c:forEach>	
			</hdiits:select>	
</td>

                </tr>
                
         
	
	<hdiits:table id="show1" width="100%">
    <tr></tr>
		
	<hdiits:tr>
	<td width="25%"><b><hdiits:caption captionid="HRMS.Dep.Relation" bundle="${welfareLables}"/>:</b></td>
	<td width="25%"><hdiits:text name="relation1" id="relation1" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" /></td>
    <td width="25%"><b><hdiits:caption captionid="HRMS.Dep.DateOfBirth" bundle="${welfareLables}"/>:</b></td>
	<td width="25%"><hdiits:text name="dob1" id="dob1" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
       
		
		</hdiits:tr>
		<script>show();</script>
		</hdiits:table>
		      
                
<table class="tabtable">
<hdiits:hidden name="actionFlag" default="WelfareSubmit"/>
	<tr  align="center">
		<td  colspan="6">
			<font>
				<hdiits:fieldGroup titleCaptionId="HR.EIS.WelfareApp" bundle="${welfareLables}" expandable="true"></hdiits:fieldGroup>
			</font>
		</td>
	</tr>
<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.WelfareSch" bundle="${welfareLables}"/>:</b></td>
	<td>

    <hdiits:select captionid="HR.EIS.WelfareSch" bundle="${welfareLables}" name="abc" id="abc" mandatory="true" validation="sel.isrequired" sort="true" onchange="getInfo(this);"> 
			<option value="Select"><hdiits:caption captionid="HRMS.ESS.Select" bundle="${welfareLables}" captionLang="single"/></option>			
			<c:forEach var="resValue12" items="${resValue.actionList}">	    				
	    					<option value="<c:out value="${resValue12.welfareCode}" />"><c:out value="${resValue12.welfareScheme}" ></c:out></option>
			</c:forEach>	
			</hdiits:select>	
</td>


	<td width="25%"><b><hdiits:caption captionid="HR.EIS.AmountEnt" bundle="${welfareLables}"/>:</b></td>
	<td width="25%"><hdiits:text name="b" id="b" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
</tr>
<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.SchType" bundle="${welfareLables}"/>:</b></td>
	<td width="25%"><hdiits:text name="d" id="d" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" /></td>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.SchDes" bundle="${welfareLables}"/>:</b></td>
	<td width="25%"><hdiits:textarea name="a" id="a" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
</tr>
<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.AmountReq" bundle="${welfareLables}" />:</b></td>
	<td width="25%"><hdiits:number id ="Amount"  captionid="HR.EIS.AmountReq"  bundle="${welfareLables}" name="Amount"  mandatory="true" validation="txt.isrequired,txt.isnumber" onblur="checknum()"/></td>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.Reason" bundle="${welfareLables}"/>:</b></td>
	<td width="25%"><hdiits:textarea  captionid="HR.EIS.Reason" bundle="${welfareLables}" name="reason" id="reason" mandatory="true" validation="txt.isrequired"  onblur="specialChar(this);" /></td>
</tr>
<tr id="Hide">
	
	
    <td width="25%" id="killme"><b><hdiits:caption id ="" captionid="HR.EIS.Installment" bundle="${welfareLables}"/>:</b> </td>
	<td width="25%"><hdiits:number id ="Installment" captionid="HR.EIS.Installment" bundle="${welfareLables}"  name="Installment"  validation="txt.isnumber" onblur="checkval()"/></td>
    <td width="25%" id="killme1"><b><hdiits:caption id="" captionid="HR.EIS.RateofInt" bundle="${welfareLables}"/>:</b></td>
 	<td width="25%"><hdiits:text name="c" id="c" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" /></td>
</tr>

<tr>
<td width="25%">
</td>
<td width="25%" colspan ="2" align ="center"><a onclick="openEMICalc()" href="#"><hdiits:caption id ="" captionid="HR.EIS.EmiCal1" bundle="${welfareLables}"/></a></td>

<td width="25%"></td>
</tr>
<!--  <tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.docproof" bundle="${welfareLables}"/>:</b></td>
	<td width="25%"><input type="file" name="attachment"></td>
	<td width="25%"></td>
	<td width="25%"></td>
</tr> -->
<tr> <td colspan = "4">
 <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="welfareAttach" />
			<jsp:param name="formName" value="welfareentry" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />                
			</jsp:include></td> </tr>
			

 
 <table id="submit"  align="center">
 <tr colspan="2" >
							<td>
								<hdiits:button captionid="HR.EIS.Submit" bundle="${welfareLables}" name="AcceptChange" type="button"  onclick="checkuser(${forwardFlag});"/>
		
							</td>
							<td>
		
							<hdiits:button captionid="HR.EIS.Close" bundle="${welfareLables}" name="Close" type="button"  onclick="history.go(-1);"/>
							</td>
				</tr>
</table>
</table>
	

	 
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />


</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>



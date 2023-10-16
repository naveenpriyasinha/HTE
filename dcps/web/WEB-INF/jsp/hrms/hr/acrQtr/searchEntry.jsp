<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.qtr.qtr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
<script>

function SearchEmp(){
	var href='hdiits.htm?actionFlag=allData';
	window.open(href,'chield','width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}



var empArray = new Array();
var selectedUserId;

var flag1=new Number;
function empSearch(from){

	for(var i=0; i<from.length; i++){
		
		empArray[i] = from[i].split("~"); 
		
	}
	if(from.length>0)
	{
	var single = empArray[0];
	//alert(" this is user id "+single[0]);

	selectedUserId=single[2];
	getUId(single[2]);
	}
	
}

function getUId(selUserId)
{
	    //alert(q.value);
	    
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
        	
			var url = "hrms.htm?actionFlag=ACRQTREmpSearch&selUserId="+selUserId;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html;charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}	
	function processResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{         // 				alert("temp"+temp);
				
					
				
						var textId;					

	
		            	var w=document.getElementById("a");	
		            	var x=document.getElementById("b");
		            	var y=document.getElementById("c");
		            	var z=document.getElementById("d");	
		            	var zz=document.getElementById("e");	  
		            	
		            	                    		            			

            	
				    	var xmlStr = xmlHttp.responseText;
				    
				    	var XMLDoc=getDOMFromXML(xmlStr);  			
        			            	
				    	var U=XMLDoc.getElementsByTagName("flagSelf");
				    	var flagLevel=XMLDoc.getElementsByTagName("flagLevel");
				    	var flagLocation=XMLDoc.getElementsByTagName("flagLocation");
				  
				 
				 	if(eval(U[0].childNodes[0].text)==1)
					{
	
						 alert("<fmt:message key="QTR.SELFTEST" bundle="${commonLables1}" />");
							return;
					}
					
					
				 	else if(eval(flagLevel[0].childNodes[0].text)==0)
					{
	
						 alert("<fmt:message key="QTR.LEVELTEST" bundle="${commonLables1}" />");
							return;
					}
					
					else if(eval(flagLocation[0].childNodes[0].text)==0) // flag=0,then invalid
					{
	
						 alert("<fmt:message key="QTR.LOCATIONTEST" bundle="${commonLables1}" />");
							return;
					}
					
					
					else	// else --- self test
					{

			
				    	var PSOName = XMLDoc.getElementsByTagName("PSOName");
				    	
				    	
				    	var district = XMLDoc.getElementsByTagName("district");
		    	        var PSName = XMLDoc.getElementsByTagName("PSName");
		    	        var GPFNo = XMLDoc.getElementsByTagName('GPFNo');
		    	        var desgn=	XMLDoc.getElementsByTagName('Desgn');
		    	        w.value=PSOName;	 
		    	        x.value=district;
		    	        y.value=PSName;
		    	        z.value=GPFNo; 
		    	        zz.value= desgn; 		    	 			
						flag1=0;
	    	     		     							
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
	     			//	    alert("desgn "+value);
	     				    if(value==null)
	     				    	alert("desgn name null");        		

		    
	     					zz.value=value;
	     					
	     			
	           			}
	           			
	           		
	           			
	           			// code to populate more fields
	           		}	/// end of else --- self test 
	        
		    	
		    		
		    	var removeRow = XMLDoc.getElementsByTagName('RemoveRow');
	           			        			
				}
				else 
				{  			
					alert("ERROR");					
				}
			}
			
			
}

function getQtr(year,pstQtr,ySize)
{
	  flag1=0;
	    var qtr = document.getElementById('quarter');	
	   if((year.selectedIndex) == ySize)
	{
		if((qtr.selectedIndex)>= pstQtr)
		{
			qtr.selectedIndex=0;
			qtr.focus();	
		}
	}
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
        	
        	
        	selYear=year.value;
        	if(eval(year.selectedIndex)==0)
        	{
        	objQuarter =eval("document.getElementById('quarter')");
			objQuarter.disabled=true; 
        	alert("<fmt:message key="QTR.MAND_YEAR" bundle="${commonLables1}" />");        	
        	return;
        	}
        	else
        	{
        	objQuarter =eval("document.getElementById('quarter')");
			objQuarter.disabled=false; 
			var url = "hrms.htm?actionFlag=getQtrFromYear&selYear="+selYear;  
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse2;
			xmlHttp.setRequestHeader("Content-Type", "text/html;charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
			}
}	
	function processResponse2()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{         
						// 				alert("temp"+temp);
				
					
						
						var textId=document.getElementById('quarter');	
						
						while(textId.length>1)
						  {
						  textId.remove(textId.length-1);
						  }
						
						
																
						// textId.add(option,before);
						// textId.remove(1); 				
						
						
		            	var temp1=textId.options[1];	
		            	var temp2=textId.options[2];	
		            	var temp3=textId.options[3];	
		            	var temp4=textId.options[4];	
		            	
            	
				    	var xmlStr = xmlHttp.responseText;
				    
				    	var XMLDoc=getDOMFromXML(xmlStr);  			
        			 			    				    	
				    	
				    	var q1 = XMLDoc.getElementsByTagName("q1");
		    	        var q2 = XMLDoc.getElementsByTagName("q2");
		    	        var q3 = XMLDoc.getElementsByTagName("q3");
		    	        var q4=	XMLDoc.getElementsByTagName("q4");
		    	        
	    	     		     							
						for ( var i = 0 ; i < q1.length ; i++ )
	     				{	     		     				
	     				    text=q1[i].childNodes[0].text;	
	     				    if(text=="null")
	     				    {
	     				 		
	     				 	}
	     					else
	     					{
	     					var tempOption=document.createElement('option');
	     					tempOption.text=text;
	     					textId.add(tempOption);     					
	     					}
	     				         					
	     				
	           			}
	           			for ( var i = 0 ; i < q2.length ; i++ )
	     				{	     		     				
	     				    text=q2[i].childNodes[0].text;	
	     				 	if(text=="null")
	     				    {
	     				 		
	     				 	}
	     					else
	     					{
	     					var tempOption=document.createElement('option');
	     					tempOption.text=text;
	     					textId.add(tempOption);  
	     					}
	     				
	           			}
	           			for ( var i = 0 ; i < q3.length ; i++ )
	     				{	     		     				
	     				    text=q3[i].childNodes[0].text;	
	     				    if(text=="null")
	     				    {
	     				 		
	     				 	}
	     					else
	     					{
	     					var tempOption=document.createElement('option');
	     					tempOption.text=text;
	     					textId.add(tempOption);  
	     					}
	     				
	           			}
	           			for ( var i = 0 ; i < q4.length ; i++ )
	     				{	     		     				
	     				    text=q4[i].childNodes[0].text;	
	     				   	if(text=="null")
	     				    {
	     				 		
	     				 	}
	     					else
	     					{
	     					var tempOption=document.createElement('option');
	     					tempOption.text=text;
	     					textId.add(tempOption);  
	     					}
	     					
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

function checkYear(qtr,pstQtr,ySize)
{	
	var year = document.getElementById('year');	
	flag1=0;
	if(eval(qtr.selectedIndex) == 0 && eval(year.selectedIndex) == 0)
	{
	alert("<fmt:message key="QTR.MAND_YEAR" bundle="${commonLables1}" />");
	year.focus();		
	return;
	}
	
	if((year.selectedIndex) == ySize)
	{
		if((qtr.selectedIndex)> pstQtr)
		{
			alert("<fmt:message key="QTR.INVALID_QTR" bundle="${commonLables1}" />");
			qtr.selectedIndex=0;
			qtr.focus();	
			return;
		}
	}	
	
	if(eval(year.selectedIndex) == 0)
	{
	
	alert("<fmt:message key="QTR.INVALID_QTR" bundle="${commonLables1}" />"+"."+"<fmt:message key="QTR.MAND_YEAR" bundle="${commonLables1}" />");
	qtr.selectedIndex=0;
	year.focus();	
	}
	return;
	
}

function checkYearSelected()
{

var year = document.getElementById('year');	

	if(eval(year.selectedIndex) == 0)
	{
	
	alert("<fmt:message key="QTR.MAND_YEAR" bundle="${commonLables1}" />");
	year.focus();	
	}
	return;
}
function checkvalidate(pstQtr,ySize)
{	
	
		
	var row = document.getElementById('c');
	var year = document.getElementById('year');	
	var qtr = document.getElementById('quarter');	
	var txt = document.getElementById('a');
		
	
	if(eval(year.selectedIndex) == 0)
	{
	
	alert("<fmt:message key="QTR.MAND_YEAR" bundle="${commonLables1}" />");
	year.focus();	
	return;
	}
	else if(eval(qtr.selectedIndex) == 0)
	{
	
	alert("<fmt:message key="QTR.MAND_QTR" bundle="${commonLables1}" />");
	qtr.focus();	
	return;
	}
		
	else if((txt.value) == "" )
	{
	alert("<fmt:message key="QTR.MAND_EMP" bundle="${commonLables1}" />");
		
	return;
	}
	 
	else
	{
	submit1();
	}

}
function submit1(){

	var year = document.getElementById('year');	
	var qtr = document.getElementById('quarter');	

	if(flag1==0)
	{
	
	var url = "hrms.htm?actionFlag=getQtrDisplayEntry&selectedUserId="+selectedUserId+"&flag1="+flag1+"&quarter="+qtr.options[qtr.selectedIndex].text+"&year="+year.options[year.selectedIndex].text;   
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse1;
			xmlHttp.setRequestHeader("Content-Type", "text/html;charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
	
	}
	else 
	{
	document.acrQtrSearch.method="POST";
	document.acrQtrSearch.action="./hrms.htm?actionFlag=getQtrDisplayEntry&selectedUserId="+selectedUserId+"&flag1="+flag1; 
	showProgressbar('Initiating Request...<br>Please wait...');
	document.acrQtrSearch.submit();
	}
}

function processResponse1()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{        
					var xmlStr = xmlHttp.responseText;
				    
				    	var XMLDoc=getDOMFromXML(xmlStr);  			
        			            	
				    	var U=XMLDoc.getElementsByTagName("flag1");
				  
				    flag1=1;
				    
				    if(eval(U[0].childNodes[0].text)==0)
				    {
				    submit1();
				    }
				 	else if(eval(U[0].childNodes[0].text)==1)
					{
	
						 alert("<fmt:message key="QTR.EXIST" bundle="${commonLables1}" />");
						 
						 return;
					}	        			
				}
				else 
				{  			
					alert("ERROR");					
				}
			}
			
			
}

function submit4()
{
	//alert("inside submit function");
	document.acrQtrSearch.method="POST";
	document.acrQtrSearch.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	showProgressbar('Opening Home Page...<br>Please wait...');
	document.acrQtrSearch.submit();
}

function showCursorAsHand(elem)
{
	elem.style.cursor='hand';
}
</script>

	


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="qtrList" value="${resValue.qtrList}"></c:set>
<c:set var="yearList" value="${resValue.yearList}"></c:set>
<c:set var="pstQtr" value="${resValue.pstQtr}"></c:set>
<c:set var="ySize" value="${resValue.ySize}"></c:set>



<fmt:setBundle basename="resources.hr.qtr.qtr" var="commonLables" scope="request"/>



<hdiits:form name="acrQtrSearch" validate="true" method="post" encType="multipart/form-data" >
<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.QTR.QTR" bundle="${commonLables}" /></b></a></li>
	</ul>
	</div>
	<div>
 <div id="tcontent1" class="tabcontent" tabno="0">
<br>
<table class="tabtable">

	
 <tr colspan="4">
 		
     <td width="15%" align="left"><b><hdiits:caption captionid="HR.QTR.year" bundle="${commonLables}" id="year1" />:</b> </td>
    <td width="35%" align="left">
        <hdiits:select captionid="HR.QTR.year" bundle="${commonLables}" name="year" id="year" mandatory="true" validation="sel.isrequired" onchange="getQtr(this,${pstQtr},${ySize})" tabindex="1"> 
		<option value="-1"><fmt:message key="HRMS.QTR.Select" bundle="${commonLables}"/></option>
		<c:forEach var="resValue121" items="${yearList}">
		<option value="<c:out value="${resValue121.year}" />"><c:out value="${resValue121.year}" ></c:out></option>
		</c:forEach>		
	    </hdiits:select>	
     </td>
		
     
     <td width="15%" align="left"><b><hdiits:caption captionid="HR.QTR.quarter" bundle="${commonLables}" id="quarter1" />:</b> </td>
	<td width="35%" align="left">
  		<hdiits:select captionid="HR.QTR.quarter" bundle="${commonLables}" name="quarter" id="quarter" mandatory="true" validation="sel.isrequired" sort="false" onfocus="checkYearSelected();" onchange="checkYear(this,${pstQtr},${ySize});" tabindex="2"> 
		<option value="-1"><fmt:message key="HRMS.QTR.Select" bundle="${commonLables}"/></option>
		
		</hdiits:select>	
     </td>

 </tr>          

</table>
<br>
<TABLE>			    
				<TR>
				    <TD colspan="2" width="25%"><b><u><hdiits:caption captionid="FIR.TOWHOM" bundle="${commonLables}" /></u></b></TD>
				</TR>
				<tr></tr><tr></tr><tr></tr><tr>
			    <TR>
				    <TD  width="25%"><b><hdiits:caption captionid="FIR.NAMEOFPORAISINGGAR" bundle="${commonLables}" />:</b>
				    </TD>
				    <TD>
				    <hdiits:text name="witNameIo" readonly="true"id="a" onchange="setFlag()" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/>
				    &nbsp;
				    
					    <c:set var="tooltip" value="${FIR.NAMEOFPORAISINGGAR}" property="" />
					<hdiits:image id="img1" source="./images/search_icon.gif" bundle="${commonLables}" tooltip="Search Employee" onmouseover="showCursorAsHand(this)"  onclick="SearchEmp();" tabindex="3"></hdiits:image>
				    </TD>
				</TR> 
				
				 <TR>
				    <TD  width="25%"><b><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonLables}" />:</b>
				    </TD>
				    <TD>
				    <hdiits:text name="toDesgn" readonly="true"  id="e" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>
				    </TD>
				   </TR>
				
				 <TR>
			    	 <TD  width="25%"><b><hdiits:caption captionid="PS.DISHQ" bundle="${commonLables}" />:</b>
			 		 </TD>
				    <TD>
			 		 <hdiits:text name="textbuckno121" readonly="true" id="b" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>
			 		 </TD>
			  		 <TD  width="25%"><b><hdiits:caption captionid="PS.POLICE_STATION"  bundle="${commonLables}" />:</b>
			  		 </TD>
				    <TD>
			  		 <hdiits:text name="textFidrPS" readonly="true" id="c" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>
			  		 </TD>
			  	</TR>


    		     <tr> 
					 <TD width="25%"><b><hdiits:caption captionid="PS.GPFNO" bundle="${commonLables}" />:</b>
			    	 </TD>
				    <TD>
			    	 <hdiits:text name="textbuckno12" readonly="true" id="d" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>
			    	 </TD>
				</TR>
		
							  	
</table> 

<hdiits:hidden name="reportFlag" default="${0}"/>	


<script>
objQuarter =eval("document.getElementById('quarter')");
objQuarter.disabled=true; 
</script>
<br>
<table class="tabtable">
  <tr>
    <td colspan ="4" align= "center">		
   <hdiits:button captionid="HR.QTR.Submit" bundle="${commonLables}"  name="Submit" type="button" onclick="checkvalidate(${pstQtr},${ySize})" tabindex="4"/>
	<hdiits:button captionid="HR.QTR.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="submit4();" tabindex="5"/></td>
 </tr>
</table>

	</div>
</div>
	<hdiits:validate controlNames="" locale='${locale}' />

</hdiits:form>
 	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>
	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
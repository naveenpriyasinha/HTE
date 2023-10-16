<%
try {
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks" var="commonLables" scope="request"/>

<script>
	function SearchEmp(){
		var href='hdiits.htm?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	}

var userId=new Number();
userId=-1;
var empArray = new Array();

function empSearch(from){

	for(var i=0; i<from.length; i++){
		empArray[i] = from[i].split("~"); 
	}
	if(from.length>0)
	{
		var single = empArray[0];
		userId=single[2];
		getUId123(single[2]);
	}
}

function getUId123(uid)
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
			           	   	  alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.AJAX"/>');
			            	  return false;        
			      		}
			 		}			 
        	}	     
        	
			var url = "hrms.htm?actionFlag=SheetRemarksEmpSearchAjax&id="+uid;    
			
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse12;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}

function processResponse12()
{
	if (xmlHttp.readyState == 4) 
			{    
				if (xmlHttp.status == 200) 
				{       
						var xmlStr = xmlHttp.responseText;
				    	var XMLDoc=getDOMFromXML(xmlStr);  					    			    	
		            	var w=document.getElementById("PSOName");	
		            	var x=document.getElementById("District");
		            	var y=document.getElementById("PSName");
		            	var z=document.getElementById("GPFNo");	
		            	var zz=document.getElementById("Designation");	  
		
				    	var U=XMLDoc.getElementsByTagName("Flag");
				    	var M=XMLDoc.getElementsByTagName("Flag2");
				 
				 if(eval(U[0].childNodes[0].text)==1)
				{
						alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.OwnSR"/>');
						userId=-1;
						return;
				}

			// Post Level ID check - kshitij : Replace with dsgn Check
			/*
				else if(eval(M[0].childNodes[0].text)==1)
				{		 
				 
			 	alert("Employee having levelid less than 10 cannot fill sheet remarks");
			 	userId=-1;
			 	return;		 
				}
			*/	
				else
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
		    	        	    	 				    	     		     							
						for ( var i = 0 ; i < PSOName.length ; i++ )
	     				{	     		     								
	     				    value=PSOName[i].childNodes[0].text;	
	     				    if(value==null)
	     				    	alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.PSO"/>');     				    
	     					w.value=value;
	           			}
	           			
	           			for ( var i = 0 ; i < district.length ; i++ )
	     				{	     		     								
	     				    value=district[i].childNodes[0].text;	
	     				    if(value==null)
     				    	alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.dist"/>');        				    
	     					x.value=value;
	           			}

	           			for ( var i = 0 ; i < PSName.length ; i++ )
	     				{	     		     								
	     				    value=PSName[i].childNodes[0].text;	 
	     				    if(value==null)
     				    	alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.PS"/>');       				    
	     					y.value=value;
	           			}

	           			for ( var i = 0 ; i < GPFNo.length ; i++ )
	     				{	     		     								
	     				    value=GPFNo[i].childNodes[0].text;	
	     				    if(value==null)
     				    	alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.GPF"/>');       				    
	     					z.value=value;
	           			}

	           			for ( var i = 0 ; i < desgn.length ; i++ )
	     				{	     		     								
	     				    value=desgn[i].childNodes[0].text;
	     				    if(value==null)
     				    	alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.desg"/>');       				    
	     					zz.value=value;
	           			}
	           			
	           			// code to populate more fields
		  }  	
		    	var removeRow = XMLDoc.getElementsByTagName('RemoveRow');
	           			        			
				}
				else 
				{  			
					alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.ERROR"/>');				
				}
			}
}

function checkvalidate(){	
	var row = document.getElementById('PSOName');
	var year = document.getElementById('Year');	
	var selectedyear=eval(year.selectedIndex);
	if(eval(year.selectedIndex) == 0 )
	{
	alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.Yea"/>');
	setFocusSelection('Year');
    document.getElementById('Year').focus();
	return;
	}
	
	else if(userId==-1)
	{
	alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.emp"/>');	
	setFocusSelection('PSOName');
	 document.getElementById('PSOName').focus();
	return;
	}
	
	else
	{
	checkuser(selectedyear);
	}
}

function checkuser(sy)
{

		var url = "hrms.htm?actionFlag=SheetRemarksEmpSearchAjaxCheckUser&userId="+userId+"&Year="+sy;    
		xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse123;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}

function processResponse123()
{
	if (xmlHttp.readyState == 4) 
			{    
				if (xmlHttp.status == 200) 
				{       
						var xmlStr = xmlHttp.responseText;
				    	var XMLDoc=getDOMFromXML(xmlStr);  					    			    	
				    	var L=XMLDoc.getElementsByTagName("Flag1");
				 if(eval(L[0].childNodes[0].text)==1)
				{
						alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.AlreadyFilled"/>');
						return;
				}
				else
				    	submit1();
		    	
		    	var removeRow = XMLDoc.getElementsByTagName('RemoveRow');
				}
				else 
					alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.ERROR"/>');				
			}
}

function submit1()
{
	document.sheetremarkempsearch.method="POST";
	document.sheetremarkempsearch.action="./hrms.htm?actionFlag=SheetRemarks&userId="+userId;
	showProgressbar('Submitting Request...<br>Please wait...');
	document.sheetremarkempsearch.submit();
}

function submit4()
{
	document.sheetremarkempsearch.method="POST";
	document.sheetremarkempsearch.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	showProgressbar('Opening Home Page...<br>Please wait...');
	document.sheetremarkempsearch.submit();
}

//function to shown cursor as hand on mouse over event
function showCursorAsHand(elem)
{
	elem.style.cursor='hand';
}

</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="yearList" value="${resValue.yearList}"></c:set>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="HR.ESS.Sheet" bundle="${commonLables}" id="Sheet" />
		</b>
		</a>
		</li>
	</ul>
</div>

<hdiits:form name="sheetremarkempsearch" validate="true"   encType="multipart/form-data" >

<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">

<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="EmployeeDetails"  titleCaptionId="HR.ESS.EmployeeDetails">
<table class="tabtable">
      <tr colspan="4">
	      <td width="25%" ><hdiits:caption captionid="HR.ESS.Year" bundle="${commonLables}" id="Year" />: </td>
  	      <td>
        
       <hdiits:select captionid="HR.ESS.Year" bundle="${commonLables}" name="Year" id="Year" mandatory="true" validation="sel.isrequired" sort="true"> 
       				<option value="-1"><fmt:message key="HR.ESS.Select" bundle="${commonLables}"/></option>
       				<c:forEach var="year" items="${yearList}">
       			
       					<option value="<c:out value="${year.orderNo}"/>">
       					<c:out value="${year.lookupDesc}"/>
       					</option>
       				
       				</c:forEach>
       
       </hdiits:select>
       
       </td>
      
        </tr>
     	<tr colspan="3">
			<td width="25%">
				<hdiits:caption captionid="HR.ESS.PSOName" bundle="${commonLables}" id="PSOName" />:
			</td>
      		<td width="15%">
      			<hdiits:text maxlength="10" size="20" id="PSOName"  name="PSOName"  validation="txt.isrequired" caption="${labels}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"  />
      		</td>
      		<td>
	      	<hdiits:image id="img1" source="./images/search_icon.gif" tooltip="Search Officer" onmouseover="showCursorAsHand(this)" onclick="SearchEmp();"> </hdiits:image> 
	      	</td>	
      </tr>

      <tr colspan="2">
			<td>
				<hdiits:caption captionid="HR.ESS.District" bundle="${commonLables}" id="District" />:
			</td>
      		<td>
      			<hdiits:text maxlength="10" size="20" id="District"  name="District"  validation="txt.isrequired" caption="${labels}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"  />
      			
      		</td>
      	</tr>
      	
      	<tr colspan="2">
			<td>
				<hdiits:caption captionid="HR.ESS.PSName" bundle="${commonLables}" id="PSName" />:
			</td>
      		<td>
      			<hdiits:text maxlength="10" size="20" id="PSName"  name="PSName"  validation="txt.isrequired" caption="${labels}"   readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
      			
      		</td>
      	</tr>
      	
      	<tr colspan="2">
			<td>
				<hdiits:caption captionid="HR.ESS.GPFNo" bundle="${commonLables}" id="GPFNo" />:
			</td>
      		<td>
      			<hdiits:text maxlength="10" size="20" id="GPFNo"  name="GPFNo"  validation="txt.isrequired" caption="${labels}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"  />
      			
      		</td>
      	</tr>
      	
      	<tr colspan="2">
			<td>
				<hdiits:caption captionid="HR.ESS.Designation" bundle="${commonLables}" id="Designation" />:
			</td>
      		<td>
      			<hdiits:text maxlength="10" size="20" id="Designation"  name="Designation"  validation="txt.isrequired" caption="${labels}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"  />
      		</td>
      	</tr>
      
      </table>
</hdiits:fieldGroup>             

<table align="center">

	<tr colspan="2">
		<td>
			<hdiits:button captionid="HR.ESS.Submit" bundle="${commonLables}" name="Submit" type="button" onclick="checkvalidate();"/>
		</td>
	
		<td>
			<hdiits:submitbutton captionid="HR.ESS.Close" bundle="${commonLables}" name="Close" type="button"  onclick="submit4();"/>
		</td>
	</tr>
</table>
	
	 <hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>
	
	</div>
	</div>
	
</hdiits:form>
	
<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
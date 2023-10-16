<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="resources.ess.grv.grvAlerts" var="ncform" scope="request"/>
<fmt:setBundle basename="resources.ess.grv.grv" var="commonform" scope="request"/>
	

<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="grievance" value="${resValue.GrvTypeList}"></c:set>
<c:set var="userName" value="${resValue.userName}" ></c:set>
<c:set var="gfNoData" value="${resValue.gfNoData}" ></c:set>
<c:set var="district" value="${resValue.district}" ></c:set>
<c:set var="PSName" value="${resValue.PSName}" ></c:set>
<c:set var="UserID" value="${resValue.UserID}" ></c:set>
<fmt:formatDate value="${resValue.currentDate}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="date" />
<fmt:formatDate value="${resValue.currentDate}" pattern="HH:mm"
	dateStyle="medium" var="time" />
<c:set var="date" value="${date}" ></c:set>
<c:set var="time" value="${time}" ></c:set>	

<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"   
	src="script/common/tagLibValidation.js">
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

<script type="text/javascript"
	src="<c:url value="/script/common/WebSecurity.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/common/hrmsCommon.js"/>"></script>



<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>



	
<script>

	function SearchEmp(){
		openSearchWindow('./hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName=witNameIo','null',600,800);
	}


var userId=new Number();
var empArray = new Array();

function empSearch(from){
	
	for(var i=0; i<from.length; i++){
		
		empArray[i] = from[i].split("~"); 
		//alert(empArray[i]);
	}
	if(from.length>0)
	{
		var single = empArray[0];
	
		userId=single[2];
		var w=document.getElementById('name_a');	
    	var x=document.getElementById('b');
    	var y=document.getElementById('c');
    	var z=document.getElementById('d');	
    	var zz=document.getElementById('e');
		if(${UserID}!=userId)
		{
	    	w.value=single[1];
	    	y.value=single[9];
	    	zz.value=single[7];
		}
		getUId(single[2]);
	}
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
            	var w=document.getElementById('name_a');	
            	var x=document.getElementById('b');
            	var y=document.getElementById('c');
            	var z=document.getElementById('d');	
            	var zz=document.getElementById('e');	
            	                       		            			            	
		    	var xmlStr = xmlHttp.responseText;
		    	
		    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
		    	
		    	//var PSOName = XMLDoc.getElementsByTagName('PSOName');
		    	
		    	
		    	var district = XMLDoc.getElementsByTagName('district');
    	        //var PSName = XMLDoc.getElementsByTagName('PSName');
    	        var GPFNo = XMLDoc.getElementsByTagName('GPFNo');
    	        //var desgn=	XMLDoc.getElementsByTagName('Desgn');
    	        var Flag= XMLDoc.getElementsByTagName('Flag');
    	        if(eval(Flag[0].childNodes[0].text)==0)
    	        {
    	    
		    	        //w.value=PSOName;	 
		    	        x.value=district;
		    	        //y.value=PSName;
		    	        z.value=GPFNo; 
		    	        //alert(GPFNo.text);
		    	        //zz.value= desgn; 	
		    	        		    	     		     							
							for ( var i = 0 ; i < district.length ; i++ )
		    				{	     		     								
		    				    value=district[i].childNodes[0].text;	
		    				    if(value=="-")
		    				    	value="";        				    
		    					x.value=value;
		    					
		    											     					
		          			}
		          			for ( var i = 0 ; i < GPFNo.length ; i++ )
		    				{	     		     								
		    				    value=GPFNo[i].childNodes[0].text;	
		    				    if(value=="-")
		    				    	value="";
		    					z.value=value;
		    					
		    											     					
		          			}
		          			
		          			var tb=document.getElementById('userIdTable');
		          			var userIdHiddenControl=document.getElementById('userId');
		          			userIdHiddenControl.value=userId;
							var row = tb.insertRow(tb.rows.length);
						//1st cell
						 	//var cell3 = row.insertCell(0);
					// cell3.innerHTML+="<center><INPUT type='text'  STYLE='display:none;'  value='"+userId+"'' readonly name='userId' ></center>";

          			}
          			else
          			{
          				alert("<fmt:message key="PS.SELFUSERTEST" bundle="${commonform}" />");
          				return;
          			}
           					
			}
			else 
			{  			
				alert("ERROR");					
			}
		}
}		
			
			function formSubmit1()
			{
				document.frmNC.method="POST";
				
				document.frmNC.action="./hrms.htm?actionFlag=GrvSubmit&userId="+userId;
				document.frmNC.submit();
				//alert("Point 4");
			}
			function chkSpChars(control)
			{
				var iChars = "#^+=\\\;|\<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message key="PS.SPECIALCHARS" bundle="${commonform}"/>");
  							control.focus();
  							return false;
  						}
  					}
			}
			function validateGrvControls()
			{
				
				var grvTypeSelect=document.getElementById('grievancetype');
				var grvDtls=document.getElementById('grvDesc');
				var otherGrvType=document.getElementById('otherGrvType').value;
				var submitName=document.getElementById('name_a');
				
				if(eval(grvTypeSelect.selectedIndex)==0)
				{
					alert("<fmt:message bundle="${ncform}" key="GRV.GRVTYPE" />");
					//setFocusSelection('grievancetype');
					getFieldGroupObj(document.getElementById('grievancetype'));
					document.getElementById('grievancetype').focus();
					return false;
				}
				if(grvTypeSelect.value=="grv_other")
				{
					if(otherGrvType=="")
					{
						alert("<fmt:message bundle="${ncform}" key="GRV.GRVTYPE" />");
						setFocusSelection('otherGrvType');
//						document.getElementById('otherGrvType').focus();
						return false;
					}
				}
				if(grvDtls.value=="")
				{
					alert("<fmt:message bundle="${ncform}" key="GRV.GRVDTLS" />");
					setFocusSelection('grvDesc');
//					grvDtls.focus();
					return false;
				}
				
				if(submitName.value=="")
				{
					alert("<fmt:message bundle="${ncform}" key="GRV.SUBMTNAME" />");
					setFocusSelection('name_witNameIo');
					SearchEmp();
					return false;
				}
				
				
			}
			
			
			function close_redirect()
			{
	
				document.frmNC.method="POST";


				document.frmNC.action="./hdiits.htm?actionFlag=getHomePage";
				document.frmNC.submit();
			}
			function checkOther(option)
			{
				if(option.value=="grv_other")
				{
					document.getElementById('other').style.display="";
				}
				else
				{
					document.getElementById('other').style.display="none";
				}
				document.getElementById('otherGrvType').value="";
			}
</script>
<!--<script type="text/javascript"
	src="<c:url value="script/common/tabcontent.js"/>">
	</script>-->
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>






<hdiits:form name="frmNC" validate="true" id="form1" method="POST" onsubmit="return validateGrvControls()" action="./hrms.htm?actionFlag=GrvSubmit" encType="multipart/form-data" ><!-- body part of any page start-->
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="PS.GRIEVENSE" bundle="${commonform}"/>
		</b>
		</a>
		</li>
	</ul>
</div>


<div >
	 <div id="tcontent1" class="tabcontent" tabno="0">




<!-------------------------------------------------------- tb1 --------------------------------------------------->
<hdiits:fieldGroup bundle="${commonform}" captionLang="multi" expandable="true" id="rsngPersonDtl" titleCaptionId="PS.GRIV">


	 <TABLE class="tabtable">
  
   <!-- Table to display loggedin user details --> 
  
  				
			  	 <tr>
			  	 <TD  width="25%"><hdiits:caption captionid="FIR.NAMEOFPORAISINGGAR" bundle="${commonform}"/></TD>
			    <TD  width="25%"><hdiits:text name="textpersonname" default="${userName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			   
			 	  	<TD  width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			    	<TD  width="25%"><hdiits:text name="textFirPS" default="${PSName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
			  <TR>
			     <TD  width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"></hdiits:caption></TD>
					<TD width="25%"><hdiits:text name="textFirDist" default="${district}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			    
			    <TD  width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD  width="25%"><hdiits:text name="textbuckno" default="${gfNoData}" readonly="true"style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" ></hdiits:text></TD></TR>
			  <TR>
			    <TD   width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="Dateformat" default="${date}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  	<TD   width="25%"><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:text name="txtFirDt" default="${time}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			  </TR>
</TABLE>
</hdiits:fieldGroup>
<hdiits:fieldGroup bundle="${commonform}" captionLang="multi" expandable="true" id="grvDtlField" titleCaptionId="PS.GRIVDET">
<TABLE class=tabtable>		
<!-- table to display controls for Grievance details -->	

<TR>
					<TD width="25%"><hdiits:caption captionid="PS.GIEVANCETYPE" bundle="${commonform}"/></TD>
					<TD width="25%"> <hdiits:select name="grievancetype" id="grievancetype" sort="false" mandatory="true" onchange="checkOther(this);">
															<option value="Select">
															<fmt:message key="GRV.Select" bundle="${commonform}"/>
															</option>
															<c:forEach var="loop1" items="${grievance}">
															<option value="<c:out value="${loop1.lookupName}"/>"><c:out value="${loop1.lookupDesc}"/></option>
															</c:forEach>
															
													</hdiits:select>
												</td>
					<TD  style="display: none" id="other" width="80%">
					<hdiits:text name="otherGrvType" id="otherGrvType" maxlength="20" mandatory="true" size="20"/>				
					</td>
					<TD></td>					
				</tr>
				
				<TR>
					<td width="25%"><hdiits:caption captionid="PS.DES" bundle="${commonform}"/></td>
					<td colspan="3">
					<hdiits:textarea id="grvDesc" name="grvDesc" mandatory="true" rows="7" cols="100" maxlength="2000" onblur="chkSpChars(this)" ></hdiits:textarea>
					<hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Grievance','grvDesc','2000')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
					</td>
								
				</tr>
				 <tr> <td colspan = "4">
 			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="grievanceAttach" />
				<jsp:param name="formName" value="frmNC" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />                
			</jsp:include></td> </tr>
</TABLE>
</hdiits:fieldGroup>


 			



<hdiits:fieldGroup bundle="${commonform}" captionLang="multi" expandable="true" id="officerDtl" titleCaptionId="FIR.TOWHOM">
<TABLE class=tabtable>		
<!-- table to display details of selected user from the search window -->	    

			
				
			    <TR>
				    <TD   width="25%"><hdiits:caption captionid="FIR.NAMEOFPORAISINGGAR" bundle="${commonform}"/></TD>
				    <TD >
				    <hdiits:search name="witNameIo" id="a" url="./hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false" 
				    style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" mandatory="true" readonly="true"/>
				    <!--<hdiits:text  name="witNameIo" id="a" mandatory="true"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>&nbsp;&nbsp;
				    <hdiits:image source="./images/search_icon.gif" onclick="SearchEmp()" tooltip="Search Employee"/></TD>-->
				   
				</TR>
				
				 <TR>
				    <TD  width="25%"><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonform}"/></TD>
				    <TD><hdiits:text name="toDesgn" readonly="true" id="e"  style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>
				    </TD>
				   </TR>
				
				 <TR>
			  <TD   width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textbuckno121"  id="b" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  <TD   width="25%"><hdiits:caption captionid="PS.POLICE_STATION"  bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textFidrPS" id="c"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
    		
			   
			  
			  
			   <tr> 
			    <TD   width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="textbuckno12"  id="d"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				</TR>
				
				
				
					  	
</table>  
</hdiits:fieldGroup>
	<table align="center">
	<tr>
		<td >
			<hdiits:submitbutton name="submit1" type="button" value="Submit" captionid="PS.SUBMIT" bundle="${commonform}"   ></hdiits:submitbutton>
		</td>					  
		<td>
			<hdiits:button name="Closey1" type="button" captionid="PS.CLOSE" bundle="${commonform}"  onclick="close_redirect()"  />
		</td>
	</tr>
	</table>

<table id="userIdTable">
<tr>
<td>
</td>
</tr>
</table>
	  


<hdiits:text name="userId" style="display:none" id="userId"/>


</div>
	</div>
	

</hdiits:form>
<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
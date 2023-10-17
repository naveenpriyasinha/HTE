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
<c:set var="redrslList" value="${resValue.redrslList }"></c:set>
<c:set var="redrslUserDetails" value="${resValue.redrslUserDetails }"></c:set>
<c:set var="userName" value="${resValue.userName}" ></c:set>
<c:set var="gfNoData" value="${resValue.gfNoData}" ></c:set>
<c:set var="district" value="${resValue.district}" ></c:set>
<c:set var="PSName" value="${resValue.PSName}" ></c:set>
<fmt:formatDate value="${resValue.currentDate}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="date" />
<fmt:formatDate value="${resValue.currentDate}" pattern="HH:mm"
	dateStyle="medium" var="time" />
<c:set var="date" value="${date}" ></c:set>
<c:set var="time" value="${time}" ></c:set>
	
<script>


var userId=new Number();
var empArray = new Array();


		function SearchEmp(){
		userId=0;
		var href='hdiits.htm?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	}




function empSearch(from){

	for(var i=0; i<from.length; i++){
		
		empArray[i] = from[i].split("~"); 
		
	}
	
	if(from.length>0)
	{
		var single = empArray[0];
	

		userId=single[2];
	
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
		var url = "hrms.htm?actionFlag=PunishEmpSearchData&id="+uid;    
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
		    	    	var Flag= XMLDoc.getElementsByTagName('Flag');
		    	        var Flag_level= XMLDoc.getElementsByTagName('Flag_level');
		    	        
		    	      
		    	        if(eval(Flag[0].childNodes[0].text)==1)
		    	        {
		    	       	    alert("<fmt:message key="PS.SELFUSERTEST" bundle="${commonform}" />");
	           				return;
		    	        }
		    	        else if(eval(Flag_level[0].childNodes[0].text)==0)
		    	        {
		    	        	alert("<fmt:message key="PS.NOAUTHORITY" bundle="${commonform}" />");
		    	        	return;
		    	        }
		    	        else
		    	        {
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
	           			var tb=document.getElementById('userIdTable');
	           			var userIdHiddenControl=document.getElementById('userId');
	           			userIdHiddenControl.value=userId;
						var row = tb.insertRow(tb.rows.length);
						//1st cell
						 // var cell3 = row.insertCell(0);
	 					// cell3.innerHTML+="<center><INPUT type='text'  STYLE='display:none;'  value='"+userId+"'' readonly name='userId' ></center>";
	 
	           			}
	           			
	           						
				}
				else 
				{  			
					alert("ERROR");					
				}
			}
}		
		
		
		
		
		
		
		
			
			function meetingyes()
			{
					document.getElementById('meetyes').style.display='';
					document.getElementById('meetno').style.display='none';
			}
			function meetingno()
			{
					document.getElementById('meetyes').style.display='none';
					document.getElementById('meetno').style.display='';
					
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
			
			function checkdate(input)
			{
				var validformat=/^\d{2}\/\d{2}\/\d{4}$/ 
				var returnval=false
				if (!validformat.test(input.value))
				{
					alert("<fmt:message key="PS.VALIDDATE" bundle="${commonform}" />");
					input.focus();
					returnval=false;
				}
				else
				{ //Detailed check for valid date ranges
					var monthfield=input.value.split("/")[1];
					var dayfield=input.value.split("/")[0];
					var yearfield=input.value.split("/")[2];
					
					
					var Dateformat=document.getElementById('Dateformat');
					var currentday=Dateformat.value.split("/")[0];
					var currentmonth=Dateformat.value.split("/")[1];
					var currentyear=Dateformat.value.split("/")[2];
					var currentDateObj = new Date(currentyear, currentmonth-1, currentday);
					
					
					var dayobj = new Date(yearfield, monthfield-1, dayfield);
					if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
					{
						alert("<fmt:message key="PS.VALIDDATE" bundle="${commonform}" />");
						input.focus();
						returnval=false;
					}
					else
					{
						if(dayobj < currentDateObj)
						{
							alert("<fmt:message key="PS.PASTDATEVALIDATE" bundle="${commonform}" />");
							input.select();
							
							returnval=false;
						}
						else
							returnval=true;
					}
					
				}
				
				return returnval;
			}
			
			function validatePunish()
			{
				
				var iChars = "!#^=\\\'/{}|\:<>";
				var faulterEmp=document.getElementById('a');
				var punishmentDtls=document.getElementById('punishmentDtls');
				if(punishmentDtls.value=="")
				{
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
						punishmentDtls.focus();
						return false;
				}
				
				
				if(faulterEmp.value=="")
				{
						alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
						SearchEmp();
						return false;
				}
				if(document.getElementById('meetyes').style.display=='')
				{
				
					var meetYesPlace=document.getElementById('meetPlace');
				
					if(meetYesPlace.value=="")
					{
					
						alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
						meetYesPlace.focus();
						return false;
					}
					
					var meetYesDate=document.getElementById('meetDate');
					if(meetYesDate.value=="")
					{
					
						alert("<fmt:message bundle="${commonform}" key="PS.VALIDDATE" />");
						meetYesDate.focus();
						return false;
					}
					else 
					{
						if(checkdate(meetYesDate)==false)
						{
							meetYesDate.focus();
							return false;
						}
					
					}
					
					
				}
				
				else if(document.getElementById('meetno').style.display=='')
				{
					var meetNoRemarks=document.getElementById('Nam23e');
				
					if(meetNoRemarks.value=="")
					{
					
						alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
						meetNoRemarks.focus();
						return false;
					}
					
				}
				else
				{
					alert("<fmt:message bundle="${commonform}" key="PS.SETMEETSTATUS" />");
					document.getElementById('R1').focus();
					return false;
			
				}
				
			}
			
			function close_redirect()
			{
	
				document.frmNC.method="POST";


				document.frmNC.action="./hdiits.htm?actionFlag=getHomePage";
				document.frmNC.submit();
			}
			
</script>

<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>


<script type="text/javascript"
	src="<c:url value="/script/common/WebSecurity.js"/>"></script>


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="PS.KV" bundle="${commonform}"/>
		</b>
		</a>
		</li>
	</ul>
</div>


<div >
	 <div id="tcontent1"  tabno="0">


<hdiits:form name="frmNC" validate="true" method="POST" onsubmit="return validatePunish()" action="./hrms.htm?actionFlag=PunishSubmit" encType="multipart/form-data"><!-- body part of any page start-->


<TABLE class=tabtable>
  
  <tr bgcolor="#386CB7">
						<td colspan="6"><center>
						<font color="white">
						<b><u><hdiits:caption captionid="PS.PSNDET" bundle="${commonform}" /></u></b>
						</font>
						</center>
						</td>
					</tr>
  
  
			  	 <tr>
			  	  <TD   width="25%"><hdiits:caption captionid="FIR.NAMEOFPOGIVINPUNISHMENT" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="textpersonname" default="${userName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
				
			 	  <TD   width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			    	<TD   width="25%"><hdiits:text name="textFirPS" default="${PSName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
			  <TR>
			   <TD   width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"></hdiits:caption></TD>
					<TD width="25%"><hdiits:text name="textFirDist" default="${district}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" ></hdiits:text></TD>
			    				   
			    <TD   width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="textbuckno" default="${gfNoData}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
			  <TR>
			    <TD   width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="Dateformat" id="Dateformat" default="${date}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  	<TD   width="25%"><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:text name="txtFirDt" default="${time}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			  </TR>
</TABLE>

<hr>			  
<TABLE class=tabtable>		

		<tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><hdiits:caption captionid="PS.KDV" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>	
				
			  
				<TR>
				    <TD   width="25%"><hdiits:caption captionid="FIR.NAMEOFPO" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text  name="witNameIo" id="a" mandatory="true"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				    &nbsp;&nbsp;<td><hdiits:image source="./images/search_icon.gif" onclick="SearchEmp()" tooltip="Search Employee"/></TD>
				    
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
				
				<TD   width="25%""><hdiits:caption captionid="PS.KV" bundle="${commonform}"/></TD>
				<TD   width="25%"> <hdiits:textarea name="punishmentDtls" id="punishmentDtls" rows="4" cols="60" mandatory="true" onblur="chkSpChars(this)" maxlength="1000"></hdiits:textarea></td>
				</TR>
				
				
				
				
				
				 <TR>
				    <TD   width="25%"><hdiits:caption captionid="PS.EVIDANCE" bundle="${commonform}"/></TD>
				 </tr>
				    
				 <tr>
				 	<td colspan ="4" align= "center">  
				    <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="punishmentAttach" />
						<jsp:param name="formName" value="frmNC" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />                
					</jsp:include>
				    </TD>
			    </TR>
</TABLE>


<hr>
<TABLE class=tabtable >			    

				<TR>
					<TD   width="25%""><hdiits:caption captionid="PS.MEETK" bundle="${commonform}"/></TD>
					<TD   width="25%"> 
													<hdiits:radio name="R1" id="R1" value="yes" onclick="meetingyes()" mandatory="true"/><hdiits:caption captionid="MM.YES" bundle="${commonform}"/>
													 <hdiits:radio name="R1" id="R1" value="no" onclick="meetingno()" mandatory="true"/><hdiits:caption captionid="MM.NO" bundle="${commonform}"/>
										</td>
													
					<TD   width="25%"></td>
					<TD   width="25%"></td>					
				</tr>
</TABLE>

<TABLE class=tabtable id="meetno" style="display:none;" >
<tr>
	<TD  width="25%""><hdiits:caption captionid="PS.REMARKS" bundle="${commonform}"/></TD>
	<TD  width="25%"> <hdiits:textarea name="Nam23e" id="Nam23e" captionid="PS.GIEVANCEREDRESSAL" bundle="${commonform}" mandatory="true" maxlength="1000" onblur="chkSpChars(this)"/></TD>
	<TD  width="25%"></td>
	<TD  width="25%"></td>		
</tr>

</TABLE>

<script>

function HourChk()
{
	
	var hours=document.getElementById('grvTimeHour');
	if(hours.value=="")
	{
		alert("<fmt:message key="PS.VALIDHOURS" bundle="${commonform}"/>");
		hours.focus();
	}
	if(hours.value < 24 && hours.value >= 0)
	{}
	
	else 
	{
		alert("<fmt:message key="PS.VALIDHOURS" bundle="${commonform}"/>");
		hours.focus();
	}
}

function MinsChk()
{
	
	var mins=document.getElementById('grvTimeMins');
	if(mins.value=="")
	{
		alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
		mins.focus();
	}
	if(mins.value < 60 && mins.value >= 0)
	{}
	
	else
	{
		alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
		mins.focus();
	}
}



</script>

<TABLE class=tabtable id="meetyes" style="display:none;" >

			<TR>
			    <TD  width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD  width="25%"><hdiits:dateTime name="meetDate" captionid="PS.DATE" bundle="${commonform}" mandatory="true" maxvalue="31/12/2099"></hdiits:dateTime>
			  </TD>
			  	<TD width="25%"><hdiits:caption captionid="PS.TIMEOFMEETING1" bundle="${commonform}"/></TD>
			    <hdiits:td ><hdiits:caption captionid="PS.HOURS" bundle="${commonform}"/>&nbsp;&nbsp;
			  <hdiits:text name="grvTimeHour" id="grvTimeHour" maxlength="2" mandatory="true" onblur="javascript:HourChk()" default="00" size="2"></hdiits:text>&nbsp;&nbsp;
				<hdiits:caption captionid="PS.MINS" bundle="${commonform}"/>&nbsp;&nbsp;
			  	<hdiits:text name="grvTimeMins" id="grvTimeMins" maxlength="2" mandatory="true" onblur="javascript:MinsChk()" default="00" size="2"></hdiits:text></hdiits:td>
				<TD class=fieldLabel width="25%"></td>
					<TD class=fieldLabel width="25%"></td>	
					
			  </TR>	
			 <TR>
			    <TD   width="25%"><hdiits:caption captionid="PS.PLACE" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetPlace" id="meetPlace" mandatory="true" maxlength="1000" default="${redrslMeetPlace}" rows="4" cols="30" onblur="chkSpChars(this)"></hdiits:textarea></TD>
			    <TD   width="25%"><hdiits:caption captionid="PS.REMARK" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetRemarks" id="meetRemarks" default="${redrslMeetRemarks}" rows="4" cols="50" maxlength="1000" onblur="chkSpChars(this)"></hdiits:textarea></TD>
			    
			  </TR>
</TABLE>


		<table align="center">
	<tr>
		<td >
			<hdiits:submitbutton name="submit1" type="button" captionid="PS.SUBMIT" bundle="${commonform}"  value="Submit" ></hdiits:submitbutton>
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


<jsp:include page="../../core/payTabnavigation.jsp" />
</div>
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
</hdiits:form>
<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
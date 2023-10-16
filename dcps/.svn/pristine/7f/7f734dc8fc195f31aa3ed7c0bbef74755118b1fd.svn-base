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
<fmt:formatDate value="${resvalue.redrslMeetDateTime }" pattern="dd/MM/yyyy" 
	dateStyle="medium" var="meetDate" />
<fmt:formatDate value="${resvalue.redrslMeetDateTime }" pattern="HH" 
	dateStyle="medium" var="meetTimeHour" />
<fmt:formatDate value="${resvalue.redrslMeetDateTime }" pattern="mm" 
	dateStyle="medium" var="meetTimeMin" />
<c:set var="grvTpye" value="${resValue.grvTpye}"></c:set>
<c:set var="grvDesc" value="${resValue.grvDesc}"></c:set>
<c:set var="PSONameRedresee" value="${resValue.PSOName}"></c:set>
<c:set var="districtRedresee" value="${resValue.districtRedresee}"></c:set>
<c:set var="PSNameRedresee" value="${resValue.PSNameRedresee}"></c:set>
<c:set var="DesgnRedresee" value="${resValue.DesgnRedresee}"></c:set>
<c:set var="GPFNoRedresee" value="${resValue.GPFNoRedresee}"></c:set>
<c:set var="temp_pk" value="${resValue.temp_pk}"></c:set>

<c:set var="grvRaisedUserId" value="${resValue.grvRaisedUserId}"></c:set>
<c:set var="grvRaisedEmpId" value="${resValue.grvRaisedEmpId}"></c:set>


<c:set var="redrslMeetCalled" value="${resValue.redrslMeetCalled }"></c:set>
<c:set var="redrslMeetPlace" value="${resValue.redrslMeetPlace }"></c:set>
<c:set var="redrslMeetRemarks" value="${resValue.redrslMeetRemarks }"></c:set>
<c:set var="redrslSolved" value="${resValue.redrslSolved }"></c:set>
<c:set var="redrslDetails" value="${resValue. redrslDetails}"></c:set>
<c:set var="redrslFlag" value="${ resValue.redrslFlag}"></c:set>
<c:set var="grvTpye" value="${resValue.grvTpye}"></c:set>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/common/hrmsCommon.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
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


	userId=single[2];
	
	
	getUId(single[2]);
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
		    	     	var Flag= XMLDoc.getElementsByTagName('Flag');
		    	        
		    	        var grvRaisedUserId=document.getElementById('grvRaisedUserId');
		    	     
		    	        if(eval(Flag[0].childNodes[0].text)==0 && userId!=eval(grvRaisedUserId.value))
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
						var row = tb.insertRow(tb.rows.length);
						//1st cell
	 				 	var cell3 = row.insertCell(0);
	 					 cell3.innerHTML+="<center><INPUT type='text'  STYLE='display:none;'  value='"+userId+"'' readonly name='userId' ></center>";
	 
	           			}
	           			else if(userId==eval(grvRaisedUserId.value))
	           			{
	           				alert("You cannot forward to the user who raised this grievance until it is resolved");
	           			}
	           			else
	           				alert("<fmt:message key="PS.SELFUSERTEST" bundle="${commonform}" />");
	           						
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
					document.getElementById('noRemarks').style.display='none';					
			}
			function meetingno()
			{
					document.getElementById('meetyes').style.display='none';
                    document.getElementById('noRemarks').style.display='';					
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
			
			
			
			

			function formSubmit()
			{
				alert("Inside submit");
				document.frmNC.method="POST";
				
				document.frmNC.action="./hrms.htm?actionFlag=GrvRedrslSubmit&userId="+userId;
				document.frmNC.submit();
				//alert("Point 4");
			}
			
			
			
			


function display(redrslTable,img)
{	
  if(document.getElementById(redrslTable).style.display=="none")
  {
  
  document.getElementById(redrslTable).style.display="";
  document.getElementById(img).src="./images/redUp1.gif";
 
  }
  else
  {
 
  document.getElementById(redrslTable).style.display="none";
  document.getElementById(img).src="./images/greenDown.gif";
  
  } 
}

			function chkSpChars(control)
			{
				var iChars = "@#$%^&()+=[]\\\';/{}|\":<>";
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

function validateGrv()
{
	
			if(document.getElementById('meetyes').style.display=='')
			{
				
				var meetYesPlace=document.getElementById('meetPlace');
				
				if(meetYesPlace.value=="")
				{
					
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					meetYesPlace.focus();
					return false;
				}
				
				var meetYesDate=document.getElementById('meetdate');
				if(meetYesDate.value=="")
				{
					
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					meetYesDate.focus();
					return false;
				}
			}
			else if(document.getElementById('noRemarks').style.display=='')
			{
				var meeNoJustification=document.getElementById('Name12');
				if(meeNoJustification.value=="")
				{
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					meeNoJustification.focus();
					return false;
				}
				
			}
			else
			{
				alert("<fmt:message bundle="${commonform}" key="PS.SETMEETSTATUS" />");
				document.getElementById('R1').focus();
				return false;
			}
			
			
			
			
			
			if(document.getElementById('yesGriven').style.display=='' && document.getElementById('noGriven').style.display=='none')
			{
				
				var grvReslvdYesRemark=document.getElementById('Na56me');
				if(grvReslvdYesRemark.value=="")
				{
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					grvReslvdYesRemark.focus();
					return false;
				}
				
				
			}
			
			else if(document.getElementById('noGriven').style.display=='' && document.getElementById('yesGriven').style.display=='none')
			{
				
				var grvReslvdNoRemark=document.getElementById('Nam23e');
				if(grvReslvdNoRemark.value=="")
				{
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					grvReslvdNoRemark.focus();
					return false;
				}
				
				var submitEmp=document.getElementById('a');
				if(submitEmp.value=="")
				{
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					SearchEmp();
					return false;
				}
			}
			else
			{
				alert("<fmt:message bundle="${commonform}" key="PS.SETGRVREDRSLSTATUS" />");
				document.getElementById('R2').focus();
				return false;
			}
			return true;
			//alert("Brfore submit code");
			//document.frmNC.method="POST";
			
			//document.frmNC.action="./hrms.htm?actionFlag=GrvRedrslSubmit&userId="+userId;
			//alert("point1");
			//document.frmNC.submit();
			//alert("point 2");
}

			
</script>


<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>




<hdiits:form name="frmNC" validate="true" onsubmit="return validateGrv()" encType="multipart/form-data" action="./hrms.htm?actionFlag=GrvRedrslSubmit" method="POST">   <!-- body part of any page start-->

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="PS.RD" bundle="${commonform}"/>
		</b>
		</a>
		</li>
	</ul>
</div>


<div >
	 <div id="tcontent1" tabno="0">




<!-------------------------------------------------------- tb1 --------------------------------------------------->


  <table style="display:none">
  
  				
			  	 <tr>
			 	  <TD   width="25%"><hdiits:caption captionid="FIR.NAMEOFPORAISINGGAR" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="textpersonname" default="${userName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			   <TD   width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"></hdiits:caption></TD>
					<TD width="25%"><hdiits:text name="textFirDist" default="${district}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" ></hdiits:text></TD>
			    </TR>	
			    	<TR>
			  	<TD   width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			    	<TD   width="25%"><hdiits:text name="textFirPS" default="${PSName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  
			     <TD   width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="textbuckno" default="${gfNoData}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
			  <TR>
			    <TD   width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="Dateformat" default="${date}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  	<TD   width="25%"><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:text name="txtFirDt" default="${time}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			  </TR>
</TABLE>
	

<hdiits:fieldGroup bundle="${commonform}" captionLang="multi" expandable="true" id="grvncRsngDtl" titleCaptionId="PS.GRIV">
<TABLE class=tabtable>	
			 	  <TR>
				    <TD   width="25%"><hdiits:caption captionid="FIR.NAMEOFPO" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name ="PSOName" default="${PSONameRedresee}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></TD>
				     <TD   width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textbuckno121"  default="${districtRedresee}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				</TR>
				
             <TR>
			  <TD   width="25%"><hdiits:caption captionid="PS.POLICE_STATION"  bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textFidrPS" default="${PSNameRedresee}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  <TD   width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textbuckno12"  default="${GPFNoRedresee}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
    		   </tr>
			    <TR>
				    <TD   width="25%"><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonform}"/></TD>
				    <TD><hdiits:text name="DesgnRedresee" default="${DesgnRedresee}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></TD>
</TR>
</TABLE>				    
</hdiits:fieldGroup>			 	  
<hdiits:fieldGroup bundle="${commonform}" captionLang="multi" expandable="true" id="grvncDtlFld" titleCaptionId="PS.RD">
<TABLE class=tabtable>			 	  
			  <TR>
					<TD   width="25%" colspan="1"><hdiits:caption captionid="PS.GIEVANCETYPE" bundle="${commonform}"/></TD>
					<TD   width="25%"> <hdiits:text name ="grvTypeText" default="${grvTpye}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
					<TD   width="25%"><hdiits:caption captionid="PS.DES" bundle="${commonform}"/></TD>
					<TD   width="25%"> <hdiits:textarea name="grvDescRedresee" default="${grvDesc}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Grievance','grvDescRedresee','2000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></td>
				</TR>	 
			<tr><td colspan ="4" align= "center">
               <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="grievanceAttach" />
			<jsp:param name="formName" value="frmNC" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />  
			</jsp:include>
			</td>
			  			
				</tr>
				<!-- Script to deactivate the add attachment component -->
				<script>

				document.getElementById('target_uploadgrievanceAttach').style.display='none';
				document.getElementById('formTable1grievanceAttach').firstChild.firstChild.style.display='none';
	</script>
				
			    
				  
							  	
</TABLE>
</hdiits:fieldGroup>

<!-- division to display forwarded redrsl details -->
<c:set var="chkRedrsl" value="0"/>
<c:if test="${redrslFlag=='1' }">

<c:set var="i" value="1"/>
<c:forEach var="redrslUserDetails" items="${redrslUserDetails}">
<hdiits:fieldGroup bundle="${commonform}" captionLang="multi" expandable="true" id="officerTakenDtl${i}" titleCaptionId="PS.REDRSLDETAILS" collapseOnLoad="true">

	
		<table id="redrslTable${i }" >
	
		
		
		
		
		<TR>
					
				    <TD   width="25%"><hdiits:caption captionid="FIR.NAMEOFPO" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text id="PSONamer${i}" name="PSONamer${i}" default="${redrslUserDetails.redrslName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></TD>
				     <TD   width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textbuckno121r${i}"  default="${redrslUserDetails.district}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				</TR>
				
             <TR>
			  <TD   width="25%"><hdiits:caption captionid="PS.POLICE_STATION"  bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textFidrPSr${i}" default="${redrslUserDetails.PSName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  <TD   width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textbuckno12r${i}"  default="${redrslUserDetails.gpfNo}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
    		   </tr>
			    <TR>
				    <TD   width="25%"><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonform}"/></TD>
				    <TD><hdiits:text name="DesgnRedreseer${i}" default="${redrslUserDetails.desgn}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></TD>
				    
				  </TR>
				  
			<fmt:formatDate value="${redrslUserDetails.redrslMeetDateTime }" pattern="dd/MM/yyyy" 
					dateStyle="medium" var="meetDate1" />
			<fmt:formatDate value="${redrslUserDetails.redrslMeetDateTime }" pattern="HH:mm" 
					dateStyle="medium" var="meetTime1" />
			  
			<TR>
					<TD  width="25%"><b><hdiits:caption captionid="PS.MEETINGARRANGED" bundle="${commonform}"/></b></TD>
					
						<c:if test="${redrslUserDetails.redrslMeetCalled =='Y'}">
							<TD width="25%"><fmt:message key="MM.YES" bundle="${commonform}"/></TD>
							<td width="25%"><b><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></b><c:out value="  "/><c:out value="${meetDate1 }"/></td>
							<td width="25%"><b><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></b><c:out value="${meetTime1 }"/></td>
							</tr>
							<tr>
							
							<td width="25%"><b><hdiits:caption captionid="PS.PLACE" bundle="${commonform}"/></b></td><td width="25%"><c:out value="  "/><c:out value="${redrslUserDetails.redrslMeetPlace}"/></td>
		
							<TD width="25%"><b><hdiits:caption captionid="PS.REMARK" bundle="${commonform}"/></b></td>
							<td width="25%">
							<hdiits:textarea name="meetRemarks${i}" default="${redrslUserDetails.redrslMeetRemarks }"  readonly="true" style=""></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVREMARK','meetRemarks${i}','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></td>
		
							</c:if>
						<c:if test="${redrslUserDetails.redrslMeetCalled =='N'}">
							<TD width="25%"><fmt:message key="MM.NO" bundle="${commonform}"/></TD>
							
							<TD width="25%"><b><hdiits:caption captionid="PS.REMARKS" bundle="${commonform}"/></b></td>
							<td width="25%">
							<hdiits:textarea name="meetRemarks${i}" default="${redrslUserDetails.redrslMeetRemarks }"  readonly="true" style=""></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVJUST','meetRemarks${i}','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></td>
							<tr>
							<c:if test="${redrslUserDetails.redrslSolved =='Y'}">
							
							<TD width="25%"><b><hdiits:caption captionid="PS.ISGRESOL" bundle="${commonform}"/></b></TD>
								<td width="25%">
									<fmt:message key="MM.YES" bundle="${commonform}"/>
								</td>
								<td width="25%">
									<b><hdiits:caption captionid="PS.GIEVANCEREDRESSAL" bundle="${commonform}"/></b>
								</td>
								<td width="25%">
									<hdiits:textarea name="justResolve${i}" default="${redrslUserDetails.redrslDetails }"  readonly="true" style=""></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVREDRESSAL','justResolve${i}','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
								</td>
							</c:if>
							<c:if test="${redrslUserDetails.redrslSolved =='N'}">
							<TD width="25%"><b><hdiits:caption captionid="PS.ISGRESOL" bundle="${commonform}"/></b></TD>
								<td width="25%">
									<fmt:message key="MM.NO" bundle="${commonform}"/>
								</td>
								<td width="25%">
									<b><hdiits:caption captionid="PS.REMARKS" bundle="${commonform}"/></b>
								</td>
					
								<td width="25%">
									<hdiits:textarea name="justResolve${i}" default="${redrslUserDetails.redrslDetails }"  readonly="true" style=""></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVJUST','justResolve${i}','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
								</td>
							</c:if>
							</tr>
						</c:if>
						
					
					</TR>
					
					
					<TR>
					<c:if test="${redrslUserDetails.redrslDetails !=null && redrslUserDetails.redrslMeetCalled =='Y'}">
					<TD width="25%"><b><hdiits:caption captionid="PS.ISGRESOL" bundle="${commonform}"/></b></TD>
					<c:if test="${redrslUserDetails.redrslSolved =='Y'}">
						<td width="25%">
							<fmt:message key="MM.YES" bundle="${commonform}"/>
						</td>
						<td width="25%">
							<b><hdiits:caption captionid="PS.GIEVANCEREDRESSAL" bundle="${commonform}"/></b>
						</td>
						<td width="25%">
							<hdiits:textarea name="justResolve${i}" default="${redrslUserDetails.redrslDetails }"  readonly="true" style=""></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVREDRESSAL','justResolve${i}','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
						</td>
					</c:if>
					<c:if test="${redrslUserDetails.redrslSolved =='N'}">
						<td width="25%">
							<fmt:message key="MM.NO" bundle="${commonform}"/>
						</td>
						<td width="25%">
							<b><hdiits:caption captionid="PS.REMARKS" bundle="${commonform}"/></b>
						</td>
					
						<td width="25%">
							<hdiits:textarea name="justResolve${i}" default="${redrslUserDetails.redrslDetails }"  readonly="true" style=""></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVJUST','justResolve${i}','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
						</td>
					</c:if>
					</c:if>
					<c:if test="${redrslUserDetails.redrslDetails==null && redrslUserDetails.redrslMeetCalled =='Y'}">
					<td width="25%"><hdiits:caption captionid="PS.MEETINGHELD" bundle="${commonform}"/></td>
					<td width="25%"><fmt:message key="MM.NO" bundle="${commonform}"/></td>
					
					</c:if>
					</TR>
					
					
					
					
	</table>

</hdiits:fieldGroup>
<c:if test="${redrslUserDetails.redrslSolved =='N'}">

</c:if>
<c:if test="${redrslUserDetails.redrslSolved =='Y'}">

<c:set var="chkRedrsl" value="1"/>

<script>
	
	getFieldGroupObj(document.getElementById("PSONamer${i}"));
//	eval(formName + "PSONamer${i}").select();	
	//eval(formName + "PSONamer${i}").focus();
	</script>
</c:if>
	<c:set var="i" value="${i+1}"/>
</c:forEach>
</c:if>







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

	

<!-- ------------------------- -->
<script type="text/javascript">
function meetingyes1()
			{
					document.getElementById('yesGriven').style.display='';
					document.getElementById('noGriven').style.display='none';	
					document.getElementById('searchTable').style.display='none';				
			}
			function meetingno1()
			{
					document.getElementById('yesGriven').style.display='none';
                    document.getElementById('noGriven').style.display='';
                    document.getElementById('searchTable').style.display='';					
			}


</script>


	
<table align="center">
	<tr>
	
<td>
<hdiits:button captionid="PS.CLOSE" bundle="${commonform}" name="Close" type="button"  onclick="window.close()"/>
</td>
</tr>
</table>



<table id="userIdTable">
<tr>
<td>
</td>
</tr>
</table>
 <hdiits:hidden name="GrvMstPk" default="${temp_pk}"/>
 <hdiits:text style="display:none;" name="grvRaisedUserId"  default="${grvRaisedUserId }" id="grvRaisedUserId" />
 <hdiits:text style="display:none;" name="grvRaisedEmpId"  default="${grvRaisedEmpId }" id="grvRaisedEmpId" />
 


<jsp:include page="../../core/payTabnavigation.jsp" />
</div>
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
		<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />	
</hdiits:form>

<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
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

<fmt:formatDate value="${resValue.dateRaised}" pattern="dd/MM/yyyy"	dateStyle="medium" var="dateRaised"/>
<c:set var="dateRaisd" value="${dateRaised}"></c:set>


<c:set var="redrslResolvedflag" value="${resValue.redrslResolvedflag }"></c:set>
<c:set var="redrslMeetCalled" value="${resValue.redrslMeetCalled }"></c:set>
<c:set var="redrslMeetPlace" value="${resValue.redrslMeetPlace }"></c:set>
<c:set var="redrslMeetRemarks" value="${resValue.redrslMeetRemarks }"></c:set>
<c:set var="redrslSolved" value="${resValue.redrslSolved }"></c:set>
<c:set var="redrslDetails" value="${resValue.redrslDetails}"></c:set>
<c:set var="redrslFlag" value="${ resValue.redrslFlag}"></c:set>
<c:set var="grvTpye" value="${resValue.grvTpye}"></c:set>
<c:set var="sameUser" value="${resValue.sameUser}"></c:set>
<c:set var="hrEssGrvRedrslTxn" value="${resValue.hrEssGrvRedrslTxn}"></c:set>

<script type="text/javascript"  src="script/hrms/ess/grv/grvResValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/common/hrmsCommon.js"/>"></script>
<script><!--

		function SearchEmp(){
		var href='hdiits.htm?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	}


var userId=new Number();
var empArray = new Array();
var meetHeldFlag = new Number();
var grvResolvedFlag = new Number();
meetHeldFlag=-1;
grvResolvedFlag=-1;

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
	           			var userIdHiddenControl=document.getElementById('userId');
	           			userIdHiddenControl.value=userId;
						var row = tb.insertRow(tb.rows.length);
						//1st cell
	 				 	//var cell3 = row.insertCell(0);
	 					// cell3.innerHTML+="<center><INPUT type='text'  STYLE='display:none;'  value='"+userId+"'' readonly name='userId' ></center>";
	 
	           			}
	           			else if(userId==eval(grvRaisedUserId.value))
	           			{
	           				alert("<fmt:message key="PS.GRVUSERVALIDATE" bundle="${commonform}" />");
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
					
					document.getElementById('meetDate').value="";
					document.getElementById('grvTimeHour').value="00";
					document.getElementById('grvTimeMins').value="00";
					document.getElementById('meetPlace').value="";
					document.getElementById('meetRemarks').value="";
					document.getElementById('noRemarks').style.display='none';	
					document.getElementById('grvStatusRadio').style.display='none';
					document.getElementById('meetHeld').style.display='';	
					document.getElementById('yesGriven').style.display='none';
               		document.getElementById('noGriven').style.display='none';	
               		//document.getElementById('searchTable').style.display=''
               		document.getElementById('R2').checked=false;
               		document.getElementById('R5').checked=false;
               		grvResolvedFlag=-1;		
               		
               		if(meetHeldFlag==1)
               			document.getElementById('grvStatusRadio').style.display='';
               			
 
               		
			}
			function meetingno()
			{
				
					document.getElementById('meetyes').style.display='none';
                    document.getElementById('noRemarks').style.display='';	
                    document.getElementById('meetHeld').style.display='none';	
                    document.getElementById('grvStatusRadio').style.display='';
					document.getElementById('Nam23e').value="";	
					document.getElementById('Na56me').value="";
			}
			function checkDate()
			{
				var meetDate=document.frmNC.meetDate.value;
				var dt= new Date();
		   		var dd1=dt.getDate();
		   		dd1 = (dd1 > 0 && dd1 < 10)? ("0"+dd1) : dd1;
		   		var mm1=dt.getMonth()+1;
		   		mm1 = (mm1 > 0 && mm1 < 10)? ("0"+mm1) : mm1;
		   		var yy1=dt.getYear();
		   		var sysDate=dd1+'/'+mm1+'/'+yy1;
				if(compareDate(meetDate,sysDate) && document.frmNC.R3[0].checked==true)
				{
					alert("<fmt:message key="GRV.DATEGRET" bundle="${ncform}"/>");
		   			document.frmNC.meetDate.value = "";
		   			setFocusSelection('meetDate');
		   			document.frmNC.meetDate.focus();
		   			document.frmNC.R2[0].checked=false;
		   			document.frmNC.R2[1].checked=false;
		   			document.getElementById('grvStatusRadio').style.display='none';
		   			/*document.getElementById('Nam23e').style.display="none";	
					document.getElementById('Na56me').style.display="none";*/
		   			document.frmNC.R3[0].checked=false;
		   			return;
				}
				else if(!compareDate(meetDate,sysDate) && document.frmNC.R3[1].checked==true)
				{
					alert("<fmt:message key="GRV.DATELESS" bundle="${ncform}"/>");
					document.frmNC.meetDate.value = "";
					setFocusSelection('meetDate');
		   			document.frmNC.meetDate.focus();
		   			document.frmNC.R2[0].checked=false;
		   			document.frmNC.R2[1].checked=false;
		   			document.getElementById('grvStatusRadio').style.display='none';
		   			/*document.getElementById('Nam23e').style.display="none";	
					document.getElementById('Na56me').style.display="none";*/
		   			document.frmNC.R3[1].checked=false;
		   			return;
				}
			}
			function compareDate(date1,date2)
			{
				var date1Arr=date1.split('/');
				var date1Sys=new Date(date1Arr[1]+"/"+date1Arr[0]+"/"+date1Arr[2]);
				

				var date2Arr=date2.split("/");
				var date2Sys=new Date(date2Arr[1]+"/"+date2Arr[0]+"/"+date2Arr[2]);
				if(date1Sys>date2Sys)
				{
					return true;
				}
				else
				{
					return false;
				}
				
				/*var date1Arr=date1.split("/");
				var date2Arr=date2.split("/");
				if(date1Arr[2]>date2Arr[2])
				{
					return true;
				}
				else
				{
					if(date1Arr[1]>date2Arr[1])
					{
						return true;
					}
					else
					{
						if(date1Arr[0]>date2Arr[0])
						{
							return true;
						}
						else
						{
							return false;
						}
					}
				}*/
			}
			function meetingHeldYes()
			{
				var dt= new Date();
		   		var dd1=dt.getDate();
		   		dd1 = (dd1 > 0 && dd1 < 10)? ("0"+dd1) : dd1;
		   		var mm1=dt.getMonth()+1;
		   		mm1 = (mm1 > 0 && mm1 < 10)? ("0"+mm1) : mm1;
		   		var yy1=dt.getYear();
		   		var sysDate=dd1+'/'+mm1+'/'+yy1;
		   		
		   		var meetingDate = document.frmNC.meetDate.value;
		   		
		   		if(compareDate(meetingDate,sysDate))
		   		{
		   			alert("<fmt:message key="GRV.DATEGRET" bundle="${ncform}"/>");
		   			document.frmNC.meetDate.value = "";
		   			setFocusSelection('meetDate');
		   			document.frmNC.meetDate.focus();
		   			document.getElementById('grvStatusRadio').style.display='none';
		   			document.frmNC.R3[0].checked=false;
		   			return;
		   		}
				else
				{
					document.getElementById('grvStatusRadio').style.display='';
					if(grvResolvedFlag==1)
					{
						meetingyes1();
					}
					meetHeldFlag=1;
				}
			}

			function meetingHeldNo()
			{
				var dt= new Date();
		   		var dd1=dt.getDate();
		   		dd1 = (dd1 > 0 && dd1 < 10)? ("0"+dd1) : dd1;
		   		var mm1=dt.getMonth()+1;
		   		mm1 = (mm1 > 0 && mm1 < 10)? ("0"+mm1) : mm1;
		   		var yy1=dt.getYear();
		   		var sysDate=dd1+'/'+mm1+'/'+yy1;
		   		var meetingDate = document.frmNC.meetDate.value;
		   		if(!compareDate(meetingDate,sysDate))
		   		{
					alert("<fmt:message key="GRV.DATELESS" bundle="${ncform}"/>");
					document.frmNC.meetDate.value = "";
					setFocusSelection('meetDate');
		   			document.frmNC.meetDate.focus();
		   			document.getElementById('grvStatusRadio').style.display='none';
		   			document.frmNC.R3[1].checked=false;
		   			document.frmNC.R2[0].checked=false;
		   			document.frmNC.R2[1].checked=false;
		   			return;
				}
				else
				{
		   			document.getElementById('grvStatusRadio').style.display='none';
		   			document.frmNC.R2[0].checked=false;
		   			document.frmNC.R2[1].checked=false;
					//document.getElementById('searchTable').style.display='';
					document.getElementById('yesGriven').style.display='none';
	                document.getElementById('noGriven').style.display='none';
	                meetHeldFlag=0;
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
			
		
function display(redrslTable,img)
{	
		/*
		function to toggle tweety dependant details
		*/
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
				var iChars = "#^+=\\\;|\<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message key="PS.SPECIALCHARS" bundle="${commonform}"/>");
  							getFieldGroupObj(control);
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
					getFieldGroupObj(input);
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
						getFieldGroupObj(input);
						input.focus();
							
						returnval=false;
					}
					else
					{
						if(meetHeldFlag==0)
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
						else if(meetHeldFlag==1)
						{
							if(dayobj > currentDateObj)
							{
								alert("<fmt:message key="PS.FUTUREDATEVALIDATE" bundle="${commonform}" />");
								input.select();
								
								returnval=false;
							}
							else
								returnval=true;
						}
					}
				}
				
				return returnval;
			}

function validateGrv()
{
	
			if(document.getElementById('meetyes').style.display=='')
			{
				
				var meetYesPlace=document.getElementById('meetPlace');
				
				if(meetYesPlace.value=="")
				{
					
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					getFieldGroupObj(meetYesPlace);
					meetYesPlace.focus();
					return false;
				}
				
				if(meetHeldFlag==-1)
				{
					
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEMEETHELD" />");
					
					return false;
				}
				
				var meetYesDate=document.getElementById('meetdate');
				if(meetYesDate.value=="")
				{
					
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDDATE" />");
					getFieldGroupObj(meetYesDate);
					meetYesDate.focus();
					return false;
				}
				else if(meetYesDate.value!="")
				{
					
					if(checkdate(meetYesDate)!=true)
						return false;
					
				}
				var grvTimeMins = document.getElementById('grvTimeMins');
				var grvTimeHour = document.getElementById('grvTimeHour');
				
				if(grvTimeHour.value=="")
				{
					alert("<fmt:message key="PS.VALIDHOURS" bundle="${commonform}"/>");
					getFieldGroupObj(grvTimeHour);
					grvTimeHour.focus();
					return false;
				}
				else
				{
					
					if(grvTimeHour.value < 24 && grvTimeHour.value >= 0)
					{}
					
					else 
					{
						alert("<fmt:message key="PS.VALIDHOURS" bundle="${commonform}"/>");
						grvTimeHour.select();
						return false;
					}
				}
				if(grvTimeMins.value=="")
				{
					alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
					getFieldGroupObj(grvTimeMins);
					grvTimeMins.focus();
					return false;
				}
				else
				{
					if(grvTimeMins.value < 60 && grvTimeMins.value >= 0)
					{}
	
					else
					{
						alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
						grvTimeMins.select();
						return false;
					}
				}
				
			}
			else if(document.getElementById('noRemarks').style.display=='')
			{
				var meeNoJustification=document.getElementById('Name12');
				if(meeNoJustification.value=="")
				{
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					getFieldGroupObj(meeNoJustification);
					meeNoJustification.focus();
					return false;
				}
				
			}
			else
			{
				alert("<fmt:message bundle="${commonform}" key="PS.SETMEETSTATUS" />");
				getFieldGroupObj(document.getElementById('R1'));
				document.getElementById('R1').focus();
				return false;
			}
			
			
			
			
			
			if(document.getElementById('yesGriven').style.display=='' && document.getElementById('noGriven').style.display=='none')
			{
				
				var grvReslvdYesRemark=document.getElementById('Na56me');
				if(grvReslvdYesRemark.value=="")
				{
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					getFieldGroupObj(grvReslvdYesRemark);
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
					getFieldGroupObj(grvReslvdNoRemark);
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
			else if(meetHeldFlag==0)
				{
					var submitEmp=document.getElementById('a');
					if(submitEmp.value=="")
					{
						alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
						SearchEmp();
						return false;
					}
					return true;
				}
			else
			{
				
				alert("<fmt:message bundle="${commonform}" key="PS.SETGRVREDRSLSTATUS" />");
				getFieldGroupObj(document.getElementById('R2'));
				document.getElementById('R2').focus();
				return false;
			}
			return true;
			
}
function HourChk()
{
	
	var hours=document.getElementById('grvTimeHour');
	if(hours.value=="")
	{
		alert("<fmt:message key="PS.VALIDHOURS" bundle="${commonform}"/>");
		getFieldGroupObj(hours);
		hours.focus();
		return false;
	}
	if(hours.value < 24 && hours.value >= 0)
	{
		return true;
	}
	
	else 
	{
		alert("<fmt:message key="PS.VALIDHOURS" bundle="${commonform}"/>");
		getFieldGroupObj(hours);
		hours.focus();
		return false;
	}
}

function MinsChk()
{
	
	var mins=document.getElementById('grvTimeMins');
	if(mins.value=="")
	{
		alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
		getFieldGroupObj(mins);
		mins.focus();
		return false;
	}
	if(mins.value < 60 && mins.value >= 0)
	{
		return true;
	}
	
	else
	{
		alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
		getFieldGroupObj(mins);
		mins.focus();
		return false;
	}
}
			function close_redirect()
			{
	
				document.frmNC.method="POST";


				document.frmNC.action="./hdiits.htm?actionFlag=getDocListOfWorkList&elementId=300045";
				document.frmNC.submit();
			}
			function meetingyes1()
			{
					
					userId=0;
					grvResolvedFlag=1;
					document.getElementById('yesGriven').style.display='';
					document.getElementById('noGriven').style.display='none';	
					document.getElementById('Nam23e').value="";
					
					//document.getElementById('searchTable').style.display='none';
				//	document.getElementById('a').value="";	
		          //  document.getElementById('b').value="";
		          //  document.getElementById('c').value="";
		          //  document.getElementById('d').value="";	
		         //   document.getElementById('e').value="";					
			}
			function meetingno1()
			{
					grvResolvedFlag=0;
					document.getElementById('yesGriven').style.display='none';
                    document.getElementById('noGriven').style.display='';
                    document.getElementById('Na56me').value="";
                   // document.getElementById('searchTable').style.display='';					
			}


			
--></script>


<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>








<hdiits:form name="frmNC" validate="true"  encType="multipart/form-data" action="./hrms.htm?actionFlag=GrvRedrslSubmit" method="POST">   <!-- body part of any page start-->

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="PS.GIEVANCEREDRESSAL" bundle="${commonform}"/>
		</b>
		</a>
		</li>
	</ul>
</div>

<div >
	 <div id="tcontent1" tabno="0">



<!-------------------------------------------------------- tb1 --------------------------------------------------->
 <c:if test="${redrslResolvedflag == 0 }">
<TABLE class="tabtable">
  <tr>
  <td>
 	<hdiits:fieldGroup titleCaptionId="PS.PDET" id="loginUserDtl" bundle="${commonform}">
 	<TABLE width="100%"> 
 	
 	 <!-- Table to display loggedin user details --> 
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
			    <TD   width="25%"><hdiits:text name="Dateformat" id="Dateformat" default="${date}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  	<TD   width="25%"><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:text name="txtFirDt" default="${time}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			  </TR>
</TABLE>
</hdiits:fieldGroup>
</td>
</tr>
 </TABLE>
</c:if>





<TABLE class="tabtable">
			 	  
		<!-- Table to display details of the person who has raised grievance -->
		<tr>
		<td>
		<hdiits:fieldGroup titleCaptionId="PS.GRIV" bundle="${commonform}" id="grvncRsngDtl">
				<table width="100%"> 	  
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
				    <td width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></td>
				    <td><hdiits:text name="dateRaisd" default="${dateRaisd}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
				    </TR>
				    </table>
			</hdiits:fieldGroup>
			</td>
			</tr>
			
			 <tr>
			 <td>	  
			 <hdiits:fieldGroup titleCaptionId="PS.RD" bundle="${commonform}" id="grvDtl">
			  <table width="100%">
			  <TR>
					<TD   width="25%" colspan="1"><hdiits:caption captionid="PS.GIEVANCETYPE" bundle="${commonform}"/></TD>
					<TD   width="25%"> <hdiits:text name ="grvTypeText" default="${grvTpye}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/></td>
					
					<TD   width="25%"><hdiits:caption captionid="PS.DES" bundle="${commonform}"/></TD>
					<TD   width="25%"> <hdiits:textarea name="grvDescRedresee" default="${grvDesc}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Grievance','grvDescRedresee','2000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></td>
				</TR>	 
				
			<!-- Attachment obtained from the person who raised Grievance -->	
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
				</table>
				</hdiits:fieldGroup>
				</td>
				</tr>
</TABLE>

	

<!-- division to display forwarded(previous) redrsl details -->
<c:set var="chkRedrsl" value="0"/>
<c:if test="${redrslFlag=='1'}">

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
							<TD width="25%"><b><fmt:message key="MM.YES" bundle="${commonform}"/></b></TD>
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
	<script>
	
	</script>
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





<c:if test="${(redrslFlag=='0'  || redrslFlag=='1') && redrslResolvedflag == '0'}">
<hdiits:fieldGroup bundle="${commonform}" captionLang="multi" expandable="true" id="grvRdrslDtl" titleCaptionId="PS.GIEVANCEREDRESSAL">
<TABLE class="tabtable">	
							
				<TR>
					<TD   width="25%"><hdiits:caption captionid="PS.MEETINGARRANGED" bundle="${commonform}"/>&nbsp;&nbsp;<label class="mandatoryindicator">*</label></TD>
					<TD   width="25%"> 
						<hdiits:radio name="R1" id="R1" value="yes" onclick="meetingyes()" validation="sel.isradio" errCaption="Whether Person raising Grievance called for Meeting?" captionid="MM.YES" bundle="${commonform}"/>
					     <hdiits:radio name="R1" id="R1" value="no" onclick="meetingno()" validation="sel.isradio"  captionid="MM.NO" bundle="${commonform}"/>
					</td>
					
											
					<TD   width="25%"></td>
					<TD   width="25%"></td>					
				</tr>
	
</TABLE>





	

	
		<TABLE class="tabtable" id="meetyes" style="display:none;">
			<TR >
			    <TD   width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:dateTime name="meetDate" mandatory="true" onblur="checkDate();" condition="ifMeetingYes()" validation="txt.isdt,txt.isrequired" captionid="PS.DATE" bundle="${commonform}" maxvalue="31/12/2099"></hdiits:dateTime>
			  </TD>
			  	<TD ><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD ><hdiits:caption captionid="PS.HOURS" bundle="${commonform}"/></TD><td>
			    <hdiits:text name="grvTimeHour" id="grvTimeHour" maxlength="2" mandatory="true"  onblur="HourChk()" size="2"></hdiits:text>
				</td><td><hdiits:caption captionid="PS.MINS" bundle="${commonform}"/></td>
				<td>
			  	<hdiits:text name="grvTimeMins" id="grvTimeMins" maxlength="2" mandatory="true"  onblur="MinsChk()" size="2"></hdiits:text>
				   
			   </TD>
			   </TR>	
			 <TR >
			    <TD   width="25%" colspan="1"><hdiits:caption captionid="PS.PLACE" bundle="${commonform}"/></TD>
			    <TD width="25%" colspan="1"><hdiits:textarea name="meetPlace" id="meetPlace" mandatory="true" condition="ifMeetingYes()" validation="txt.isrequired" captionid="PS.PLACE" bundle="${commonform}" onblur="chkSpChars(this)" ></hdiits:textarea></TD>
			    <TD   width="25%" colspan="1"><hdiits:caption captionid="PS.REMARK" bundle="${commonform}"/></TD>
			    <TD width="25%" colspan="4"><hdiits:textarea name="meetRemarks"  id="meetRemarks" rows="4" cols="50" onblur="chkSpChars(this)"></hdiits:textarea></TD>
			    
			  </TR>
		</TABLE>
	

<TABLE class="tabtable" id="meetHeld" style="display:none;">	
				
				
				<TR>
					<TD   width="25%"><hdiits:caption captionid="PS.MEETINGHELD" bundle="${commonform}"/>&nbsp;&nbsp;<label class="mandatoryindicator">*</label></TD>
					<TD   width="25%"> 
						<hdiits:radio name="R3"  id="R3" value="yes" onclick="meetingHeldYes()" validation="sel.isradio" condition="ifMeetingYes()" errCaption="Is the meeting held?" captionid="MM.YES" bundle="${commonform}"/>
					     <hdiits:radio name="R3"  id="R4" value="no" onclick="meetingHeldNo()" validation="sel.isradio" captionid="MM.NO" bundle="${commonform}"/>
					</td>
					
											
					<TD   width="25%"></td>
					<TD   width="25%"></td>					
				</tr>
	
</TABLE>



<TABLE class="tabtable" id="noRemarks" style="display:none;">
				<TR>
					<TD   width="25%"><hdiits:caption captionid="PS.REMARKS" bundle="${commonform}"/></TD>
					<TD   width="25%"> <hdiits:textarea name="Name12" mandatory="true" validation="txt.isrequired" condition="ifMeetingNo()" rows="4" cols="50" captionid="PS.REMARKS" bundle="${commonform}" onblur="chkSpChars(this)"/><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVJUST','Name12','1000')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
					</TD>
					<TD   width="25%"></td>
					<TD   width="25%"></td>					
				</tr>
</TABLE>





<!-- ------------------------- -->

<c:if test="${(redrslFlag=='0'  || redrslFlag=='1') && redrslResolvedflag == '0'}">

<TABLE class="tabtable" id="grvStatusRadio" style="display:none;">	
			
			
				<TR>
					<TD   width="25%"><hdiits:caption captionid="PS.ISGRESOL" bundle="${commonform}"/>&nbsp;&nbsp;<label class="mandatoryindicator">*</label></TD>
					<TD   width="25%"> 
													<hdiits:radio name="R2" id="R2" value="yes" onclick="meetingyes1()" validation="sel.isradio" condition="ifGrvResolved()" errCaption="Whether Grievence been Resolved?" captionid="MM.YES" bundle="${commonform}"/>
													 <hdiits:radio name="R2" id="R5" value="no" onclick="meetingno1()" validation="sel.isradio" captionid="MM.NO" bundle="${commonform}"/>
										</td>
												
					<TD   width="25%"></td>
					<TD   width="25%"></td>					
				</tr>

</TABLE>




<!-- ------------------------- -->

<TABLE class="tabtable" id="yesGriven" style="display:none;" >
<TR>
	<TD   width="25%"><hdiits:caption captionid="PS.GIEVANCEREDRESSAL" bundle="${commonform}"/></TD>
	<TD   width="25%"> <hdiits:textarea name="Na56me" mandatory="true" rows="4" cols="50" validation="txt.isrequired" condition="yesGrvResolved()" captionid="PS.GIEVANCEREDRESSAL" bundle="${commonform}" onblur="chkSpChars(this)"/><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVREDRESSAL','Na56me','1000')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
	</TD>
	<TD   width="25%"></td>
	<TD   width="25%"></td>					
	</tr>
</table> 

<TABLE class="tabtable" id="noGriven" style="display:none;" >
<TR>
	<TD   width="25%"><hdiits:caption captionid="PS.REMARKS" bundle="${commonform}"/></TD>
	<TD   width="25%"> <hdiits:textarea name="Nam23e" mandatory="true" rows="4" cols="50" validation="txt.isrequired" condition="noGrvResolved()" captionid="PS.REMARKS" bundle="${commonform}" onblur="chkSpChars(this)"/><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.GRVJUST','Nam23e','1000')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
	</TD>
	<TD   width="25%"></td>
	<TD   width="25%"></td>					
	</tr>
</table>


<c:if test="${sameUser=='Yes'}">
<c:if test="${hrEssGrvRedrslTxn.grvMeetCalled =='Y'}">
<script>

document.frmNC.R1[0].checked = true;
document.getElementById('meetyes').style.display='';
document.getElementById('meetHeld').style.display='';	
var date="${hrEssGrvRedrslTxn.grvMeetDateTime}";
var receiveDateArr = getDateAndTimeFromDateObj(date);
document.frmNC.meetDate.value = receiveDateArr[0];
var hourTime = receiveDateArr[1].split(":");
var hour = hourTime[0];
var time = hourTime[1];
document.frmNC.grvTimeHour.value = hour;
document.frmNC.grvTimeMins.value = time;
document.frmNC.meetPlace.value="${hrEssGrvRedrslTxn.grvMeetPlace}";
document.frmNC.meetRemarks.value="${hrEssGrvRedrslTxn.grvMeetRemarks}";

var grvResolvedDtls="${hrEssGrvRedrslTxn.grvResolvedDtls}";

if(grvResolvedDtls != "")
{
	
	document.getElementById('grvStatusRadio').style.display='';
	document.frmNC.R3[0].checked = true;
	var grvResolved="${hrEssGrvRedrslTxn.grvResolved}";
	if(grvResolved=='Y')
	{
		document.getElementById('yesGriven').style.display='';
		document.frmNC.R2[0].checked = true;
		document.frmNC.Na56me.value="${hrEssGrvRedrslTxn.grvResolvedDtls}"
	}
	else if(grvResolved=='N')
	{
		document.getElementById('noGriven').style.display='';
		document.frmNC.R2[1].checked = true;
		document.frmNC.Nam23e.value="${hrEssGrvRedrslTxn.grvResolvedDtls}";
	}
}
else
{
	
	document.frmNC.R3[1].checked = true;
	
}
</script>
</c:if>

<c:if test="${hrEssGrvRedrslTxn.grvMeetCalled =='N'}">
<script>
document.frmNC.R1[1].checked = true;
document.getElementById('noRemarks').style.display='';	
document.frmNC.Name12.value="${hrEssGrvRedrslTxn.grvMeetRemarks}";
document.getElementById('grvStatusRadio').style.display='';	

var grvResolved="${hrEssGrvRedrslTxn.grvResolved}";

if(grvResolved=='Y')
{
	document.getElementById('yesGriven').style.display='';
	document.frmNC.R2[0].checked = true;
	document.frmNC.Na56me.value="${hrEssGrvRedrslTxn.grvResolvedDtls}"
}
else
{
	
	document.getElementById('noGriven').style.display='';
	document.frmNC.R2[1].checked = true;
	document.frmNC.Nam23e.value="${hrEssGrvRedrslTxn.grvResolvedDtls}"
}
</script>
</c:if>
</c:if>
</c:if>
</hdiits:fieldGroup>
</c:if>
<!-- 

<c:if test="${redrslFlag=='1' }">
<c:if test="${chkRedrsl =='0'}">
<TABLE class=tabtable id="searchTable">


		<tr bgcolor="#386CB7">
						<td   colspan="6"><center>
						<font color="#ffffff">
						<b><u><hdiits:caption captionid="FIR.TOWHOM" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>
				    <TD width="25%"><hdiits:caption captionid="FIR.NAMEOFPORAISINGGAR" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text  name="witNameIo" id="a"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>&nbsp;&nbsp;
				    <hdiits:image source="./images/search_icon.gif" onclick="SearchEmp()" tooltip="Search Employee"/></TD>
				    <TD>&nbsp;</TD>
				</TR>
				
				 <TR>
				    <TD class="fieldLabel1" width="25%"><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonform}"/></TD>
				    <TD><hdiits:text name="toDesgn" readonly="true" id="e"  style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>
				    </TD>
				   </TR>
				
				 <TR>
			  <TD   width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textbuckno121i"  id="b" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  <TD   width="25%"><hdiits:caption captionid="PS.POLICE_STATION"  bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textFidrPSi" id="c"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
    		
			   
			  
			  
			   <tr> 
			    <TD   width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="textbuckno12i"  id="d"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				</TR>
				
				<tr></tr>
				
</TABLE>
		
			  
	
<table align="center">
	<tr>
	<td >
		<hdiits:submitbutton name="submit" type="button" caption="Submit" value="Submit"  ></hdiits:submitbutton>
	</td>					  
		

<td>
<hdiits:button captionid="PS.CLOSE" bundle="${commonform}" name="Close" type="button"  onclick="window.close()"/>
</td>
</tr>
</table>
</c:if>
</c:if>


<c:if test="${redrslFlag=='0' }">
	<TABLE class=tabtable id="searchTable">


<tr bgcolor="#386CB7">
						<td   colspan="6"><center>
						<font color="#ffffff">
						<b><u><hdiits:caption captionid="FIR.TOWHOM" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>

			    <TR>
				    <TD width="25%"><hdiits:caption captionid="FIR.NAMEOFPORAISINGGAR" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text  name="witNameIo" id="a"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>&nbsp;&nbsp;
				   <hdiits:image source="./images/search_icon.gif" onclick="SearchEmp()"/></TD>
				    <TD>&nbsp;</TD>
				</TR>
				
				 <TR>
				    <TD class="fieldLabel1" width="25%"><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonform}"/></TD>
				    <TD><hdiits:text name="toDesgn" readonly="true" id="e"  style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text>
				    </TD>
				   </TR>
				
				 <TR>
			  <TD   width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textbuckno121i"  id="b" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  <TD   width="25%"><hdiits:caption captionid="PS.POLICE_STATION"  bundle="${commonform}"/></TD>
			  <TD   width="25%"><hdiits:text name="textFidrPSi" id="c"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
    		
			   
			  
			  
			   <tr> 
			    <TD   width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD   width="25%"><hdiits:text name="textbuckno12i"  id="d"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				</TR>
				
				<tr></tr>
				
</TABLE>
		
			  
	
<table align="center">
	<tr>
	<td>
		<hdiits:submitbutton name="submit" type="button" captionid="PS.SUBMIT" bundle="${commonform}" value="Submit"  ></hdiits:submitbutton>
	</td>					  
		

<td>
<hdiits:button captionid="PS.CLOSE" bundle="${commonform}" name="Close" type="button"  onclick="window.close()"/>
</td>
</tr>
</table>
</c:if>  -->

<table id="userIdTable">
<tr>
<td>
</td>
</tr>
</table>
 <hdiits:hidden name="GrvMstPk" default="${temp_pk}"/>
 <hdiits:text style="display:none;" name="grvRaisedUserId"  default="${grvRaisedUserId }" id="grvRaisedUserId" />
 <hdiits:text style="display:none;" name="grvRaisedEmpId"  default="${grvRaisedEmpId }" id="grvRaisedEmpId" />
 
<hdiits:text name="userId" style="display:none" id="userId"/>


</div>
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
		<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>	
</hdiits:form>

<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
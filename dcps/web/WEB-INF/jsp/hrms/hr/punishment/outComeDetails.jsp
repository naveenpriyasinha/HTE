<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/punishment/AddOfficerPunish.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<fmt:setBundle basename="resources.hr.punishment.punishmentAlerts" var="ncform" scope="request"/>
<fmt:setBundle basename="resources.hr.punishment.punishment" var="commonform" scope="request"/>
	
	
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="pk_value" value="${resValue.temp_pk }"></c:set>
<c:set var="userName" value="${resValue.PSOName}" ></c:set>
<c:set var="gfNoData" value="${resValue.GPFNo}" ></c:set>
<c:set var="district" value="${resValue.district}" ></c:set>
<c:set var="PSName" value="${resValue.PSName}" ></c:set>
<c:set var="Desgn" value="${ resValue.Desgn}"></c:set>
<c:set var="SavedOnceOrNot" value="${ resValue.SavedOnceOrNot}"></c:set>
<c:set var="PunishGivenOrNot" value="${ resValue.PunishGivenOrNot}"></c:set>
<fmt:formatDate value="${resValue.currentDate}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="date" />
<fmt:formatDate value="${resValue.currentDate}" pattern="HH:mm"
	dateStyle="medium" var="time" />
<c:set var="date" value="${date}" ></c:set>
<c:set var="time" value="${time}" ></c:set>	


<c:set var="PSONameFaulter" value="${resValue.PSONameFaulter}" ></c:set>
<c:set var="gfNoDataFaulter" value="${resValue.GPFNoFaulter}" ></c:set>
<c:set var="districtFaulter" value="${resValue.districtFaulter}" ></c:set>
<c:set var="PSNameFaulter" value="${resValue.PSNameFaulter}" ></c:set>
<c:set var="DesgnFaulter" value="${resValue.DesgnFaulter}" ></c:set>

<c:set var="punishmentDtls" value="${resValue.punishmentDtls}" ></c:set>
<c:set var="punishmentMeetCalled" value="${resValue.punishmentMeetCalled}" ></c:set>
<c:set var="punishmentMeetPlace" value="${resValue.punishmentMeetPlace}" ></c:set>
<c:set var="punishmentMeetDetails" value="${resValue.punishmentMeetDetails}" ></c:set>
<c:set var="defenceDtls" value="${resValue.defenceDtls}" ></c:set>
<c:set var="faulterUserId" value="${resValue.faulterUserId}" ></c:set>
<c:set var="PunishRecomndUserId" value="${resValue.PunishRecomndUserId}" ></c:set>
<fmt:formatDate value="${resValue.punishmentMeetDateTime}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="meetdate" />
<fmt:formatDate value="${resValue.punishmentMeetDateTime}" pattern="HH:mm"
	dateStyle="medium" var="meettime" />
<c:set var="meetdate" value="${meetdate}" ></c:set>
<c:set var="meettime" value="${meettime}" ></c:set>

<c:set var="outcomeDtls" value="${resValue.outcomeDtls}" ></c:set>
<c:set var="orderNo" value="${resValue.orderNo}" ></c:set>
<c:set var="officersPrsntDtls" value="${resValue.officersPrsntDtls}" ></c:set>
<fmt:formatDate value="${resValue.meetDateTime}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="outcm_meetdate" />
<fmt:formatDate value="${resValue.meetDateTime}" pattern="HH:mm"
	dateStyle="medium" var="outcm_meettime" />
<c:set var="outcmMeetDate" value="${outcm_meetdate}" ></c:set>
<c:set var="outcmMeetTime" value="${outcm_meettime}" ></c:set>

<script>


	
function SearchEmp()
{
	var href='hdiits.htm?actionFlag=allData';
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}

var empArray = new Array();
var emparray = new Array();
var empcount = 0 ;
var finalEmpArr = new Array();
var selectedEmp = new Array();
var searchEmpCounter = new Number;
var EmpAddFlag = 0;

function empSearch(from)
{
	
	for(var i=0; i<from.length; i++)
	{
	//alert(from[i]);
	empArray[i] = from[i].split("~"); 
	//alert(empArray[i]);
	}
	
	var ctr=selectedEmp.length+1;
	var tb = document.getElementById('EmpListDtlsTab');
	var tableLength = tb.rows.length;
	var nextId = tableLength;
	//alert("nextId"+nextId);
	var ExistUserId="";
	finalEmpArr=from;
	for(var k=1;k<nextId;k++)
	{
		if(document.getElementById('UserId'+k).value!=undefined)
		{
			ExistUserId=document.getElementById('UserId'+k).value;
		}
			
			for(var j=0; j<empArray.length; j++)
			{
				var single = empArray[j];
				var userid = single[4];
				if(ExistUserId==userid)
				{
					finalEmpArr=empArray.splice(j,1);
				}
			}	
			ExistUserId="";
		}
		
		for(var l=0; l<empArray.length; l++)
			{
				var single = empArray[l];
				var empId = single[0];
				var empName = single[1];
	
				var userid = single[4];
				var userName = single[5];
				var postid = single[6];
				var postName = single[7];
				var desgid = single[8];
				var desigName = single[7];
				var officeName=single[9];
				//alert("faulterUserId======"+"${faulterUserId}");
				//alert("userid====="+userid);
				if("${punishmentMeetCalled}" != 'N' )
				{
					document.getElementById('OfficerPrsName').value=userName;
					document.getElementById('OfficerPrsDesig').value=desigName;
					document.getElementById('OfficerPrsPS').value=officeName;
					addDataInTablePunish('EmpListDtlsTab', 'encXMLExistUser', new Array('OfficerPrsName','OfficerPrsDesig','OfficerPrsPS'),userid, '', 'deleteRecord', '') 
				}
				else if("${punishmentMeetCalled}"=='N' && "${faulterUserId}" != (userid))
				{
					document.getElementById('OfficerPrsName').value=userName;
					document.getElementById('OfficerPrsDesig').value=desigName;
					document.getElementById('OfficerPrsPS').value=officeName;
					addDataInTablePunish('EmpListDtlsTab', 'encXMLExistUser', new Array('OfficerPrsName','OfficerPrsDesig','OfficerPrsPS'),userid, '', 'deleteRecord', '') 
				}
				else
				{
					alert(empName+"<fmt:message key="PS.WASNOTCALLEDFORMEETING" bundle="${commonform}"/>");	
				}
				
			}
		
		
}				

			
			
			function meetingyes()
			{
					document.getElementById('meetyes').style.display='';
			}
			function meetingno()
			{
					document.getElementById('meetyes').style.display='none';
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
			
			
			function formSubmit1()
			{
				document.frmNC.method="POST";
				
				document.frmNC.action="./hrms.htm?actionFlag=OutcomeSubmit";
				document.frmNC.submit();
				//alert("Point 4");
			}
			
			function chkSpChars(control)
			{
				var iChars = "#^+=\\\;|\<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i))!= -1) 
  						{
  							alert("<fmt:message key="PS.SPECIALCHARS" bundle="${commonform}"/>");
  							control.focus();
  							return false;
  						}
  					}
			}
			
			function chkNumber(orderControl)
			{
				
				var nums = "0123456789" ;
				for (var i = 0; i < orderControl.value.length; i++)
  					{
  						if (nums.indexOf(orderControl.value.charAt(i)) == -1) 
  						{
  							//alert("<fmt:message key="PS.NUMBERS" bundle="${commonform}"/>");
  							orderControl.value="";
  							orderControl.select();
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
					
					var punishDate=document.getElementById('punishMeetDate');
					var punishDateObj=null;
					
					
					var dayobj = new Date(yearfield, monthfield-1, dayfield);
					if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
					{
						alert("<fmt:message key="PS.VALIDDATE" bundle="${commonform}" />");
						input.focus();
						returnval=false;
					}
					else
					{
						var punishMeetCalledFlag=document.getElementById('punishMeetCalledFlag');
						if(punishMeetCalledFlag.value=="Y")
						{
							
							
							
							
							var punishday=punishDate.value.split("/")[0];
							var punishmonth=punishDate.value.split("/")[1];
							var punishyear=punishDate.value.split("/")[2];
							var dateObj = new Date(punishyear, punishmonth-1, punishday);
							punishDateObj=dateObj;
						}
						if((dayobj < punishDateObj) || (dayobj > currentDateObj))	
						{
							if(dayobj < punishDateObj)
							{
								alert("<fmt:message key="PS.OUTCOMEDATEVALIDATE" bundle="${commonform}" />");
								input.focus();
								returnval=false;
							}
					
							if(dayobj > currentDateObj)
							{
								alert("<fmt:message key="PS.FUTUREDATEVALIDATE" bundle="${commonform}" />");
								input.focus();
								returnval=false;
							}
						}
						else
							returnval=true;
					}
					
						
				}
				
				return returnval;
			}
			
			function validateOutcome()
			{
				
				var decisionDtls=document.getElementById('decisionDtls');
				if(decisionDtls.value=="")
				{
						alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
						decisionDtls.focus();
						return false;
				}
				
				
				var prsntEmps=document.getElementById('EmpListDtlsTab');
				if(prsntEmps.rows.length==1)
				{
					alert("<fmt:message bundle="${commonform}" key="PS.OFFICERSPRSNTVALIDATE" />");
					SearchEmp();
					return false;
				}
				var outcmmeetDate=document.getElementById('outcmmeetDate');
				if(outcmmeetDate.value=="")
				{
						alert("<fmt:message bundle="${commonform}" key="PS.VALIDDATE" />");
						outcmmeetDate.focus();
						return false;
				}
				else if(checkdate(outcmmeetDate)==false)
				{
						
						return false;
					
				}
				
				return true;
			}
			
			function close_redirect()
			{
	
				document.frmNC.method="POST";


				document.frmNC.action="./hdiits.htm?actionFlag=getDocListOfWorkList&elementId=300045";
				document.frmNC.submit();
			}
			
var httpXML="";		
function LoadPersonInfo(PunishId)
{

	var PunishmentId=PunishId;
	
		try
		{   
    		httpXML=new XMLHttpRequest();    
		}
		catch (e)
		{    // Internet Explorer    
			try
			{
      			httpXML=new ActiveXObject("Msxml2.XMLHTTP");      
      		}
			catch (e)
			{
		  		try
		  		{
              		httpXML=new ActiveXObject("Microsoft.XMLHTTP");        
        		}
				catch (e)
				{
			    	alert("Your browser does not support AJAX!");        
			    	return false;        
				}
			}			 
     	}	
     	var SavdOnceOrNt="${SavedOnceOrNot}";
     	
		var url = "hrms.htm?actionFlag=getExistingPunishDtl&PunishmentId="+PunishmentId+"&SavedOnceOrNot="+SavdOnceOrNt;    
		httpXML.open("POST", encodeURI(url) , true);			
		httpXML.onreadystatechange = processResponse2;
		httpXML.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		httpXML.send(encodeURIComponent(null));
		
}
function processResponse2()
{
if (httpXML.readyState == 4) 
			{     
				if (httpXML.status == 200) 
				{       
					var xmlStr = httpXML.responseText;
					var XMLDoc=getDOMFromXML(xmlStr); 
					//alert(xmlStr);
					var PunishUserName=XMLDoc.getElementsByTagName('PunishUserName');
					var PunishUserDesig=XMLDoc.getElementsByTagName('PunishUserDesig');
					var PunishUserLoc=XMLDoc.getElementsByTagName('PunishUserLoc');
					var UserId=XMLDoc.getElementsByTagName('UserId');
					var XMLPath=XMLDoc.getElementsByTagName('XMLPath');
					var XMLPath1=XMLPath[0].childNodes[0].text;
					//alert("UserId.length="+UserId.length);
					if(UserId.length>0)
					{
						for(var i=0;i<UserId.length;i++)
						{
							var PunishUserName1=PunishUserName[i].childNodes[0].text;
							var PunishUserDesig1=PunishUserDesig[i].childNodes[0].text;
							var PunishUserLoc1=PunishUserLoc[i].childNodes[0].text;
							var UserId1=UserId[i].childNodes[0].text;
							
							
							if("${punishmentMeetCalled}"=='Y')
							{
								addDBDataInTablePunish('EmpListDtlsTab','encXMLExistUser',new Array(PunishUserName1,PunishUserDesig1,PunishUserLoc1),XMLPath1,UserId1,'','deleteRecord','');
							}
							else
							{
								
								if("${punishmentMeetCalled}" == 'N' && "${faulterUserId}" != (UserId1))
								{
									addDBDataInTablePunish('EmpListDtlsTab','encXMLExistUser',new Array(PunishUserName1,PunishUserDesig1,PunishUserLoc1),XMLPath1,UserId1,'','deleteRecord','');
								}
							}
						}
					}
				}
			}
}		
			
</script>
<script type="text/javascript"
	src="<c:url value="script/common/tabcontent.js"/>">
	</script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>

<script type="text/javascript" src="script/hrms/common/hrmsCommon.js"></script>




<hdiits:form name="frmNC" validate="true" onsubmit="return validateOutcome()" encType="multipart/form-data" method="post" action="./hrms.htm?actionFlag=OutcomeSubmit"><!-- body part of any page start-->

<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="PS.OUTCOME" bundle="${commonform}"/>
		</b>
		</a>
		</li>
	</ul>
</div>


<div >
	 <div id="tcontent1" tabno="0">
  <hdiits:fieldGroup titleCaptionId="PS.KD" bundle="${commonform}" id="PS.KDId" collapseOnLoad="false">	
<TABLE class=tabtable>
			
			<!-- <tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">Hiren2
						<b><u><fmt:message key="PS.KD" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>
   -->
			
			
			  	 <tr>
			  	 <TD class="fieldLabel" width="25%"><hdiits:caption captionid="FIR.NAMEOFPOGIVINPUNISHMENT" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="textpersonname" default="${PSONameFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text> </TD>
			   
			 	  <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			    	<TD class="fieldLabel" width="25%"><hdiits:text name="textFirPS" default="${PSNameFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD></TR>
			  <TR>
			     <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"></hdiits:caption></TD>
					<TD width="25%"><hdiits:text name="textFirDist" default="${districtFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; " ></hdiits:text></TD>
			    	
			    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="textbuckno" default="${gfNoDataFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px;"></hdiits:text></TD></TR>
			  <TR>
			    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="Dateformat" id="Dateformat" default="${date}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px;"></hdiits:text></TD>
			  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:text name="txtFirDt" default="${time}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px;"></hdiits:text> </TD>
			  </TR>


</TABLE>
</hdiits:fieldGroup>

<hr>			  
 <hdiits:fieldGroup titleCaptionId="PS.PDET" bundle="${commonform}" id="PS.PDETId" collapseOnLoad="false">	
<TABLE class=tabtable>	


				  
			 
				    
			  <TR>
				    <TD class=fieldLabel1 width="25%"><hdiits:caption captionid="FIR.NAMEOFPO" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textpersonname1" default="${userName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			      <TD class=fieldLabel width="25%"><hdiits:text name="textFirPS1" default="${PSName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD>
				
				    </tr>
				
				<tr>    
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			       <TD class=fieldLabel width="25%"><hdiits:text name="textFirDist1" default="${district}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px;" ></hdiits:text></TD>
			       <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
 		    <TD class=fieldLabel width="25%"><hdiits:text name="textbuckno1" default="${gfNoData}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD>
    		   
    		  
			       </tr>
				
				 <TR>
			  <TD class=fieldLabel1 width="25%"><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textpersonname2" default="${Desgn}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD>
				
			  <TD class=fieldLabel width="25%""><hdiits:caption captionid="PS.KV" bundle="${commonform}"/></TD>
					<TD class=fieldLabel width="25%"> <hdiits:textarea name="punishDtls" default="${punishmentDtls}"  readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; " rows="4" cols="34"></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Punishment','punishDtls','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></td>
			 </tr>
			 <TR>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.EVIDANCE" bundle="${commonform}"/></TD>
				 </TR>
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
			    <tr>
			    <td><b><hdiits:caption captionid="PS.MEETK" bundle="${commonform}"/></b></td>
			    
		<c:if test="${punishmentMeetCalled=='Y' }">		 
				<td><hdiits:caption captionid="MM.YES" bundle="${commonform}"/></td>
			    </tr>
			    <TR>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.DATEOFMEETING1" bundle="${commonform}"/></TD>
				    <TD class=fieldLabel width="25%"><hdiits:text name="textFirPS2" id="punishMeetDate" default="${meetdate}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; "></hdiits:text></TD>
				  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.TIMEOFMEETING1" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textFirPS3" default="${meettime}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; "></hdiits:text></TD>
			  </TR>		
			 <TR>
			    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.PLACE" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetPlace" default="${punishmentMeetPlace}" readonly="true" style="color: black; font-family: Verdana; font-size: 12px; " rows="4" cols="34"></hdiits:textarea></TD>
			    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.REMARK" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetDetails" default="${punishmentMeetDetails}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; " rows="4" cols="34"></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Punishment','meetDetails','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></TD>
			 
			  </TR>
		</c:if>
		<c:if test="${punishmentMeetCalled=='N' }">	
		<td><hdiits:caption captionid="MM.NO" bundle="${commonform}"/></td>
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.REMARK" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetDetails" default="${punishmentMeetDetails}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; " rows="4" cols="34"></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Punishment','meetDetails','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></TD>
			 
			   
		<tr>
		</tr>
		</c:if>
				

</TABLE>
</hdiits:fieldGroup>
<script>

				document.getElementById('target_uploadpunishmentAttach').style.display='none';
				document.getElementById('formTable1punishmentAttach').firstChild.firstChild.style.display='none';
	</script>
<hr>		
	 <hdiits:fieldGroup titleCaptionId="PS.DEFENSESTATEMENTS" bundle="${commonform}" id="PS.DEFENSESTATEMENTSId" collapseOnLoad="false">  
<TABLE class=tabtable>		

<!-- 
			<tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><fmt:message key="PS.DEFENSESTATEMENTS" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>		 	  
	 -->		  
			   <TR>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.DEFENSESTATEMENTS" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:textarea name="defStmt" default="${defenceDtls }" rows="4" cols="34" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; "></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Punishment','defStmt','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></TD>
				    <TD class=fieldLabel width="25%"></TD>
				    <TD width="25%"></TD>
			  </TR>
			  <TR>
				    <TD  width="25%"><hdiits:caption captionid="PS.ATTACH" bundle="${commonform}"/></TD>
			  </TR>
			  
			  <tr>
				 	<td colspan ="4" align= "center">  
				    <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="defenceAttach" />
						<jsp:param name="formName" value="frmNC" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />                
					</jsp:include>
				    </TD>
			    </TR>
</TABLE>
</hdiits:fieldGroup>
<script>

				document.getElementById('target_uploaddefenceAttach').style.display='none';
				document.getElementById('formTable1defenceAttach').firstChild.firstChild.style.display='none';
	</script>
	
<script>

function HourChk()
{
	
	var hours=document.getElementById('outcmTimeHour');
	/*if(hours.value=="")
	{
		alert("<fmt:message key="PS.VALIDHOURS" bundle="${commonform}"/>");
		hours.focus();
	}*/
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
	
	var mins=document.getElementById('outcmTimeMins');
	/*if(mins.value=="")
	{
		alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
		mins.focus();
	}*/
	if(mins.value < 60 && mins.value >= 0)
	{}
	
	else
	{
		alert("<fmt:message key="PS.VALIDMINS" bundle="${commonform}"/>");
		mins.focus();
	}
}


</script>

<hr>			  
<TABLE class=tabtable width="100%">			    
			    <TR>
			    	<TD class=fieldLabel><hdiits:caption captionid="PS.EXACTDATEOFMEETING1" bundle="${commonform}"/></TD>
				   
				    <TD ><hdiits:dateTime name="outcmmeetDate"  mandatory="true" captionid="PS.EXACTDATEOFMEETING1" bundle="${commonform}"validation="txt.isrequired"  ></hdiits:dateTime></TD>
				  	<TD class=fieldLabel><hdiits:caption captionid="PS.EXACTTIMEOFMEETING1" bundle="${commonform}"/></TD>
				    <TD class=fieldLabel><hdiits:caption captionid="PS.HOURS" bundle="${commonform}"/></TD>
			    <TD ><hdiits:text name="outcmTimeHour" id="outcmTimeHour" maxlength="2" mandatory="true" onblur="javascript:HourChk()"  size="2" validation="txt.isrequired" captionid="PS.HOURS" bundle="${commonform}"></hdiits:text></TD>
				<td class=fieldLabel><hdiits:caption captionid="PS.MINS" bundle="${commonform}"/></TD>
			  	<TD ><hdiits:text name="outcmTimeMins" id="outcmTimeMins" maxlength="2" mandatory="true" onblur="javascript:MinsChk()"  size="2" validation="txt.isrequired" captionid="PS.MINS" bundle="${commonform}"></hdiits:text></TD>
				
			  	 
			  </TR>
</TABLE>
<hr>
<hdiits:fieldGroup titleCaptionId="PS.PRESENTOFFICER" bundle="${commonform}" id="PS.PRESENTOFFICER" collapseOnLoad="false">  			  
<TABLE class=tabtable>

			  
				<tr>
					<td width="15%">
					</td>
					<td>
					<TABLE class=tabtable align="center" rules="all" id="EmpListDtlsTab" width="70%" border="1"  style="border-collapse: collapse;" borderColor="BLACK">
	<tr>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center">
			<center><b><fmt:message key="FIR.Name" bundle="${commonform}"/>&nbsp;</b></center>
		</td>
		
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center">
			<center><b><fmt:message key="FIR.DASIGNATION" bundle="${commonform}"/>&nbsp;</b></center>
		</td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center">
			<center><b><fmt:message key="PS.POLICE_STATION" bundle="${commonform}"/>&nbsp;</b></center>
		
		</td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center">
			<center><b>Action&nbsp;</b></center>
		
		</td>
	</tr>

</TABLE>
</td>
	<td width="15%">
					</td>
				</tr>
		
</TABLE>				
</hdiits:fieldGroup>		

		    
<table id="btnAdd1" style="display" align="center" >
 			<tr>
 			<TD class="fieldLabel" align="center" colspan="4"><hdiits:button  name="add1"  type="button" value="Add" onclick="SearchEmp()"></hdiits:button></td>
 			</tr>
 			</table>
<hr>	
<hdiits:fieldGroup titleCaptionId="PS.DECISION" bundle="${commonform}" id="PS.DECISION" collapseOnLoad="false">  		  
<TABLE class=tabtable>	

				
					
					<tr>
						<td><hdiits:caption captionid="PS.PunishGiven" bundle="${commonform}"/> <font color="red">*</font> </td>
						<td><hdiits:radio name="PunishYesOrNo" id="PunishYesOrNo" value="Yes" mandatory="true" validation="sel.isradio" errCaption="Whethere Punishment Given Or Not" captionid="PS.YES" bundle="${commonform}" default="${PunishGivenOrNot}"/>
						<hdiits:radio name="PunishYesOrNo" id="PunishYesOrNo" value="No" mandatory="true" validation="sel.isradio" errCaption="Whethere Punishment Given Or Not" captionid="PS.NO" bundle="${commonform}" default="${PunishGivenOrNot}" /></td>
					</tr>	  		
				
			  <TR>
					<TD class=fieldLabel width="25%""><hdiits:caption captionid="PS.DECISION" bundle="${commonform}"/></TD>
					<TD class=fieldLabel width="25%"><hdiits:textarea name="decisionDtls" id="decisionDtls" mandatory="true" rows="4" cols="34" onblur="chkSpChars(this)" validation="txt.isrequired" captionid="PS.DECISION" bundle="${commonform}" default="${outcomeDtls}"></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Punishment','decisionDtls','1000','')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></td>
					<TD class=fieldLabel width="25%"></td>
					<TD class=fieldLabel width="25%"></td>					
				</tr>	
				 <TR>
				    <TD class=fieldLabel1 width="25%"><hdiits:caption captionid="FIR.OUTCOMENO" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:number name="orderNo" default="${orderNo}"/></TD>
				    <TD></TD>
				    <TD>&nbsp;</TD>
				</TR>
			    
			    <TR>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.ATTACH" bundle="${commonform}"/></TD>
				    </TR>
				 
				<tr>
				 	<td colspan ="4" align= "center">  
				    <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="outcomeAttach" />
						<jsp:param name="formName" value="frmNC" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />                
					</jsp:include>
				    </TD>
			    </TR>
	
</TABLE>
</hdiits:fieldGroup>
	
		<hdiits:hidden name="SavedOnceFlag" default="${SavedOnceOrNot}"/>
	
	<hdiits:hidden name="pk_value" default="${pk_value }"/>
	<hdiits:text name="punishMeetCalledFlag" id="punishMeetCalledFlag" default="${punishmentMeetCalled }" style="display:none;"/>
		<table id="noOfEmp">
	</table>
	<script>
	if("${punishmentMeetCalled}"=='Y')
	{
		if("${SavedOnceOrNot}"=='SavedOnce')
		{
			var ExactMeetingDate="${outcmMeetDate}";
			document.getElementById('outcmmeetDate').value=ExactMeetingDate;
			var OutMeetTime="${outcmMeetTime}";
			var SeparMeetTime=OutMeetTime.split(":");
			document.getElementById('outcmTimeHour').value=SeparMeetTime[0];
			document.getElementById('outcmTimeMins').value=SeparMeetTime[1];
		}
		else
		{
			var ExactMeetingDate="${meetdate}";
			document.getElementById('outcmmeetDate').value=ExactMeetingDate;
			var PreDecidMeetTime="${meettime}";
			var SeparMeetTime=PreDecidMeetTime.split(":");
			document.getElementById('outcmTimeHour').value=SeparMeetTime[0];
			document.getElementById('outcmTimeMins').value=SeparMeetTime[1];
		}
	}
	LoadPersonInfo("${pk_value}");
	
	
	</script>
  	<table align="center">
	<tr>
		<td >
			<hdiits:submitbutton name="submit1"  captionid="PS.SUBMIT" bundle="${commonform}"  ></hdiits:submitbutton>
		</td>					  
		<td>
			<hdiits:button name="Closey1" type="button" captionid="PS.CLOSE" bundle="${commonform}" onclick="window.close()"  />
		</td>
	</tr>
	</table>	


</div>
	</div>

<hdiits:hidden name="OfficerPrsName" id="OfficerPrsName"/>
<hdiits:hidden name="OfficerPrsDesig" id="OfficerPrsDesig"/>
<hdiits:hidden name="OfficerPrsPS" id="OfficerPrsPS"/>
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
		<hdiits:validate controlNames="test"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
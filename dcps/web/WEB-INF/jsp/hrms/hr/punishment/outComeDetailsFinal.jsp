<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

<c:set var="outcomeDtls" value="${resValue.outcomeDtls}" ></c:set>
<c:set var="orderNo" value="${resValue.orderNo}" ></c:set>
<c:set var="officersPrsntDtls" value="${resValue.officersPrsntDtls}" ></c:set>
<fmt:formatDate value="${resValue.meetDateTime}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="outcm_meetdate" />
<fmt:formatDate value="${resValue.meetDateTime}" pattern="HH:mm"
	dateStyle="medium" var="outcm_meettime" />
<c:set var="outcmMeetDate" value="${outcm_meetdate}" ></c:set>
<c:set var="outcmMeetTime" value="${outcm_meettime}" ></c:set>
<c:set var="PunishGivenOrNot" value="${ resValue.PunishGivenOrNot}"></c:set>

<c:set var="punishmentDtls" value="${resValue.punishmentDtls}" ></c:set>
<c:set var="punishmentMeetCalled" value="${resValue.punishmentMeetCalled}" ></c:set>
<c:set var="punishmentMeetPlace" value="${resValue.punishmentMeetPlace}" ></c:set>
<c:set var="defenceDtls" value="${resValue.defenceDtls}" ></c:set>
<fmt:formatDate value="${resValue.punishmentMeetDateTime}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="meetdate" />
<fmt:formatDate value="${resValue.punishmentMeetDateTime}" pattern="HH:mm"
	dateStyle="medium" var="meettime" />
<c:set var="meetdate" value="${meetdate}" ></c:set>
<c:set var="meettime" value="${meettime}" ></c:set>


<script>


	
function SearchEmp()
{
	var href='${rootUrl}'+'?actionFlag=allData';
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}

var empArray = new Array();
var emparray = new Array();
var empcount = 0 ;
var finalEmpArr = new Array();


function empSearch(from)
{
	
	for(var i=0; i<from.length; i++)
	{
	//alert(from[i]);
	empArray[i] = from[i].split("~"); 
	//alert(empArray[i]);
	}
	var ctr=1;
	for(var j=0; j<from.length; j++)
	{
	var single = empArray[j];
	//alert(single[0]);
	
	//document.getElementById('empName').value = single[1];
	//document.getElementById('desigName').value = single[7];
	var empId = single[0];
	var empName = single[1];
	//var empMName = single[2];
	//var empLName = single[3];
	var userid = single[4];
	var userName = single[5];
	var postid = single[6];
	var postName = single[7];
	var desgid = single[8];
	var desigName = single[7];
	
	
	//
	var tb = document.getElementById('EmpListDtlsTab');
	var tableLength = tb.rows.length;
	var nextId = tableLength-1;
	
	//alert('nextId is'+nextId);
	var row = tb.insertRow(tableLength);
	//1st cell
	  var cell3 = row.insertCell(0);
	  cell3.innerHTML+="<center><INPUT type='text'  STYLE='background-color:lightblue' id='empName"+nextId+"' value='"+empName+"' readonly name='empName"+nextId+"'  size='20' maxlength=70></center><INPUT type='text' style='display:none;' value='"+userid+"' name='userId"+nextId+"'>";
	//2nd cell
	  var cell4 = row.insertCell(1);
	  cell4.innerHTML+="<center><INPUT TYPE='text' STYLE='background-color:lightblue' id='desigName"+nextId+"' value='"+desigName+"' readonly name='desigName"+nextId+"' size='20' maxlength=70></center>";
	 ctr++; 
	//
	//document.getElementById('empName').value = single[1];
	//document.getElementById('desigName').value = single[7];
	//alert(single[1]);
	}
	var hiddenField=document.getElementById('noOfEmp');
	var hiddenLength=hiddenField.rows.length;
	var hiddenRows=hiddenField.insertRow(hiddenLength);
	var cellHidden=hiddenRows.insertCell(0);
	cellHidden.innerHTML+="<INPUT type='text' style='display:none;' value='"+(ctr-1)+"' name='noOfUserId'>";
	
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
			
			
			function close_redirect()
			{
	
				document.frmNC.method="POST";


				document.frmNC.action="./hdiits.htm?actionFlag=getDocListOfWorkList&elementId=300045";
				document.frmNC.submit();
			}
			
</script>
<script type="text/javascript"
	src="<c:url value="script/common/tabcontent.js"/>">
	</script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>
<script type="text/javascript" src="script/hrms/common/hrmsCommon.js"></script>



<hdiits:form name="frmNC" validate="true" encType="multipart/form-data" method="post"><!-- body part of any page start-->

<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<fmt:message key="PS.OUTCOME" bundle="${commonform}"/>
		</b>
		</a>
		</li>
	</ul>
</div>

<div >
	 <div id="tcontent1" tabno="0">
 <hdiits:fieldGroup titleCaptionId="PS.KD" bundle="${commonform}" id="PS.KDId" collapseOnLoad="false">	
<TABLE class=tabtable>
			
			
			
			
			  	 <tr>
			  	 	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="FIR.NAMEOFPOGIVINPUNISHMENT" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="textpersonname" default="${PSONameFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px;"></hdiits:text> </TD>
			 	  
			    	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			    	<TD class="fieldLabel" width="25%"><hdiits:text name="textFirPS" default="${PSNameFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD></TR>
			  <TR>
			    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"></hdiits:caption></TD>
					<TD width="25%"><hdiits:text name="textFirDist" default="${districtFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; " ></hdiits:text></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="textbuckno" default="${gfNoDataFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD></TR>
			  <TR>
			    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="Dateformat" default="${date}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD>
			  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:text name="txtFirDt" default="${time}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text> </TD>
			  </TR>


</TABLE>
</hdiits:fieldGroup>
<hr>			  
 <hdiits:fieldGroup titleCaptionId="PS.PDET" bundle="${commonform}" id="PS.PDETId" collapseOnLoad="false">	
<TABLE class=tabtable>	

			<!-- <tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><fmt:message key="PS.PDET" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>		  
			   -->
				    
			  <TR>
				    <TD class=fieldLabel1 width="25%"><hdiits:caption captionid="FIR.NAMEOFPO" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textpersonname1" default="${userName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			      <TD class=fieldLabel width="25%"><hdiits:text name="textFirPS1" default="${PSName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text></TD>
				
				    </tr>
				
				<tr>    
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			       <TD class=fieldLabel width="25%"><hdiits:text name="textFirDist1" default="${district}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; " ></hdiits:text></TD>
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
				    <TD class=fieldLabel width="25%"><hdiits:text name="textFirPS2" default="${meetdate}" readonly="true" style="color: black; font-family: Verdana; font-size: 12px; "></hdiits:text></TD>
				  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.TIMEOFMEETING1" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textFirPS3" default="${meettime}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; "></hdiits:text></TD>
			  </TR>		
			 <TR>
			    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.PLACE" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetPlace" default="${punishmentMeetPlace}" readonly="true" style="color: black; font-size: 12px; " rows="4" cols="34"></hdiits:textarea></TD>
			    <TD class=fieldLabel width="25%"></TD>
			    <TD width="25%"></TD>
			  </TR>
		</c:if>
		<c:if test="${punishmentMeetCalled=='N' }">	
		<td><hdiits:caption captionid="MM.NO" bundle="${commonform}"/></td>
			   
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


				<!-- <tr bgcolor="#386CB7">
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
	
	var hours=document.getElementById('grvTimeHour');
	if(hours.value < 24 && hours.value >= 0)
	{}
	else
	{
		alert("enter Valid Hours");
		hours.focus();
	}
}

function MinsChk()
{
	
	var mins=document.getElementById('grvTimeMins');
	if(mins.value < 60 && mins.value >= 0)
	{}
	else
	{
		alert("enter Valid Minutes");
		mins.focus();
	}
}


</script>

<hr>	
		  
<TABLE class=tabtable width="100%">			    
			    <TR>
			    	<TD width="25%"><hdiits:caption captionid="PS.EXACTDATEOFMEETING1" bundle="${commonform}"/></TD>
				   
				    <TD ><hdiits:text name="outcm_meetdate" default="${outcmMeetDate}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; "/></TD>
				  	<TD width="25%"><hdiits:caption captionid="PS.EXACTTIMEOFMEETING1" bundle="${commonform}"/></TD>
				    <TD class=fieldLabel ><hdiits:text name="outcm_meetTime" default="${outcmMeetTime}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; "/></TD>
				    
			  	 
			  </TR>
</TABLE>
<hr>			 
 <hdiits:fieldGroup titleCaptionId="PS.PRESENTOFFICER" bundle="${commonform}" id="PS.PRESENTOFFICERId" collapseOnLoad="false">	 
<TABLE class=tabtable>

			 <!--  <tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><fmt:message key="PS.PRESENTOFFICER" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>	
				 -->
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
	</tr>
		<c:forEach var="officersPrsntDtlsMap" items="${officersPrsntDtls }">
			<tr>
				<td><c:out value="${officersPrsntDtlsMap.officer_name }" /></td>
				<td><c:out value="${officersPrsntDtlsMap.officer_desgn }" /></td>
				<td><c:out value="${officersPrsntDtlsMap.officer_OFFICE }" /></td>
			</tr>	
		</c:forEach>
	
	
</TABLE></td>
	<td width="15%">
					</td>
				</tr>
				
</TABLE>	
</hdiits:fieldGroup>			
<hr>	
 <hdiits:fieldGroup titleCaptionId="PS.DECISION" bundle="${commonform}" id="PS.DECISIONId" collapseOnLoad="false">	 		  
<TABLE class=tabtable>	

			<!-- 	<tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><fmt:message key="PS.DECISION" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>	
				 -->		  		
					<tr>
						<td class=fieldLabel width="25%"><hdiits:caption captionid="PS.PunishGiven" bundle="${commonform}"/></td>
						<c:if test="${resValue.PunishGivenOrNot=='Yes' }">	
							<td class=fieldLabel width="25%"><hdiits:caption captionid="MM.YES" bundle="${commonform}"/></td>
						</c:if>
						<c:if test="${resValue.PunishGivenOrNot=='No' }">	
							<td class=fieldLabel width="25%"><hdiits:caption captionid="MM.NO" bundle="${commonform}"/></td>
						</c:if>
					</tr>
			  <TR>
					<TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.DECISION" bundle="${commonform}"/></TD>
					<TD class=fieldLabel width="25%"><hdiits:textarea name="decisionDtls" default="${outcomeDtls}" readonly="true" rows="4" cols="34" style="color: black; font-family: Verdana;  font-size: 12px; "></hdiits:textarea><hdiits:a href="#" onclick="openTextAreaDesc('HRMS.Punishment','decisionDtls','1000','readonly')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a></td>
					<TD class=fieldLabel width="25%"></td>
					<TD class=fieldLabel width="25%"></td>					
				</tr>	
				 <TR>
				    <TD class=fieldLabel1 width="25%"><hdiits:caption captionid="FIR.OUTCOMENO" bundle="${commonform}"/></TD>
				    <c:if test="${orderNo!='0' }">
				    <TD width="25%"><hdiits:text name="orderNo" default="${orderNo}" readonly="true" style="color: black; font-family: Verdana;  font-size: 12px; "/></TD>
				    </c:if>
				    <c:if test="${orderNo=='0' }">
				     <TD width="25%"><b><hdiits:caption captionid="MM.NO" bundle="${commonform}"/></b></TD>
				    </c:if>
				    <TD></TD>
				    <TD>&nbsp;</TD>
				</TR>
			    
			    <TR>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.ATTACH" bundle="${commonform}"/></TD>
				    </TR>
				 </TABLE>
</hdiits:fieldGroup>
				 <table width="100%">
				 
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

	<script>

				document.getElementById('target_uploadoutcomeAttach').style.display='none';
				document.getElementById('formTable1outcomeAttach').firstChild.firstChild.style.display='none';
	</script>
	<hdiits:hidden name="pk_value" default="${pk_value }"/>
	
	<table id="noOfEmp">
	</table>
	
	<table align="center">
	<tr>
							  
		<td>
			<hdiits:button name="Closey1" type="button" captionid="PS.CLOSE" bundle="${commonform}" onclick="window.close()"  />
		</td>
	</tr>
	</table>	


</div>
	</div>
	<hdiits:validate controlNames="test"
		locale='<%=(String)session.getAttribute("locale")%>' />
	
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
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


<c:set var="punishmentDtls" value="${resValue.punishmentDtls}" ></c:set>
<c:set var="punishmentMeetCalled" value="${resValue.punishmentMeetCalled}" ></c:set>
<c:set var="punishmentMeetPlace" value="${resValue.punishmentMeetPlace}" ></c:set>
<c:set var="punishmentMeetDetails" value="${resValue.punishmentMeetDetails}" ></c:set>
<fmt:formatDate value="${resValue.punishmentMeetDateTime}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="meetdate" />
<fmt:formatDate value="${resValue.punishmentMeetDateTime}" pattern="HH:mm"
	dateStyle="medium" var="meettime" />
<c:set var="meetdate" value="${meetdate}" ></c:set>
<c:set var="meettime" value="${meettime}" ></c:set>

<script>

			
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
				
				document.frmNC.action="./hrms.htm?actionFlag=DefenceSubmit";
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
			
			function validateDefence()
			{
				
				var defenceStmt=document.getElementById('defenceStmt');
				
				if(defenceStmt.value=="")
				{
					
					alert("<fmt:message bundle="${commonform}" key="PS.VALIDATEGRV" />");
					defenceStmt.focus();
					return;
				}
				formSubmit1();
			}
			
			function close_redirect()
			{
	
				document.frmNC.method="POST";


				document.frmNC.action="./hdiits.htm?actionFlag=getDocListOfWorkList&elementId=300045";
				document.frmNC.submit();
			}
			
			
</script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="script/common/tabcontent.js"/>">
	</script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="PS.DEFENCE" bundle="${commonform}"/>
		</b>
		</a>
		</li>
	</ul>
</div>


<div >
	 <div id="tcontent1" tabno="0">
	 
<hdiits:form name="frmNC" validate="true" encType="multipart/form-data"><!-- body part of any page start-->


<!-------------------------------------------------------- tb1 --------------------------------------------------->

<TABLE class=tabtable>
			
			<tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><hdiits:caption captionid="PS.KD" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>
			
			
			  	 <tr>
			  	 <TD class="fieldLabel" width="25%"><hdiits:caption captionid="FIR.NAMEOFPOGIVINPUNISHMENT" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="textpersonname" default="${PSONameFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			   
			 	  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			    	<TD class="fieldLabel" width="25%"><hdiits:text name="textFirPS" default="${PSNameFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
			  <TR>
			     <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"></hdiits:caption></TD>
					<TD width="25%"><hdiits:text name="textFirDist" default="${districtFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" ></hdiits:text></TD>
			    
			    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="textbuckno" default="${gfNoDataFaulter}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD></TR>
			  <TR>
			    <TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.DATE" bundle="${commonform}"/></TD>
			    <TD class="fieldLabel" width="25%"><hdiits:text name="Dateformat" default="${date}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="PS.TIME" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:text name="txtFirDt" default="${time}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text> </TD>
			  </TR>


</TABLE>
<hr>			  
<TABLE class=tabtable>		


<tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><hdiits:caption captionid="PS.PSNDET" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>	  
			  
			  
				    
			  <TR>
				    
				    <TD class=fieldLabel1 width="25%"><hdiits:caption captionid="FIR.NAMEOFPO" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textpersonname1" default="${userName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				   <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.POLICE_STATION" bundle="${commonform}"/></TD>
			      <TD class=fieldLabel width="25%"><hdiits:text name="textFirPS1" default="${PSName}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				
				    </tr>
				
				<tr>    
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.DISHQ" bundle="${commonform}"/></TD>
			       <TD class=fieldLabel width="25%"><hdiits:text name="textFirDist1" default="${district}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" ></hdiits:text></TD>
			       <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.GPFNO" bundle="${commonform}"/></TD>
 		    <TD class=fieldLabel width="25%"><hdiits:text name="textbuckno1" default="${gfNoData}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
    		  
			       
				
				 <TR>
			  <TD class=fieldLabel1 width="25%"><hdiits:caption captionid="PS.DESIGNATION" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textpersonname2" default="${Desgn}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				
			   </tr>
		

</TABLE>
 
<hr>			  
<TABLE class=tabtable>				  
				  
				  
				  <tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><hdiits:caption captionid="PS.KV" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>
				
		
				<TR>
					<TD class=fieldLabel width="25%""><hdiits:caption captionid="PS.KV" bundle="${commonform}"/></TD>
					<TD class=fieldLabel width="25%"> <hdiits:textarea name="punishDtls" default="${punishmentDtls}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:textarea></td>
					<TD class=fieldLabel width="25%"></td>
					<TD class=fieldLabel width="25%"></td>					
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
				    <TD class=fieldLabel width="25%"><hdiits:text name="textFirPS2" default="${meetdate}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
				  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.TIMEOFMEETING1" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:text name="textFirPS3" default="${meettime}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:text></TD>
			  </TR>		
			 <TR>
			    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.PLACE" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetPlace" default="${punishmentMeetPlace}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:textarea></TD>
			    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.REMARK" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetDetails" default="${punishmentMeetDetails}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:textarea></TD>
			  </TR>
		</c:if>
		
		<c:if test="${punishmentMeetCalled=='N' }">	
				
		<td width="25%"><hdiits:caption captionid="MM.NO" bundle="${commonform}"/></td>
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.REMARK" bundle="${commonform}"/></TD>
			    <TD width="25%"><hdiits:textarea name="meetDetails" default="${punishmentMeetDetails}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"></hdiits:textarea></TD>
			
			    </tr>
		<tr>
		</tr>
		</c:if>
</TABLE>
<script>

				document.getElementById('target_uploadpunishmentAttach').style.display='none';
				document.getElementById('formTable1punishmentAttach').firstChild.firstChild.style.display='none';
	</script>
<hr>			  
<TABLE class=tabtable>

				  <tr bgcolor="#386CB7">
						<td class="fieldLabel" colspan="6"><center>
						<font color="#ffffff">
						<b><u><hdiits:caption captionid="PS.DEFENSESTATEMENTS" bundle="${commonform}"/></u></b>
						</font></center>
						</td>
					</tr>
				
			   <TR>
			  
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.DEFENSESTATEMENTS" bundle="${commonform}"/></TD>
				    <TD width="25%"><hdiits:textarea name="defenceStmt" id="defenceStmt" rows="4" cols="60" onblur="chkSpChars(this)" maxlength="1000" mandatory="true"></hdiits:textarea></TD>
				    <TD class=fieldLabel width="25%"></TD>
				    <TD width="25%"></TD>
			  </TR>
			  <TR>
				    <TD class=fieldLabel width="25%"><hdiits:caption captionid="PS.ATTACH" bundle="${commonform}"/></TD>
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
<table align="center">
	<tr>
		<td >
			<hdiits:button name="submit1" type="button" captionid="PS.SUBMIT" bundle="${commonform}"  onclick="validateDefence()" ></hdiits:button>
		</td>					  
		<td>
			<hdiits:button name="Closey1" type="button" captionid="PS.CLOSE" bundle="${commonform}"  onclick="window.close()"  />
		</td>
	</tr>
	</table>	  
		
			<hdiits:hidden name="pk_value" default="${pk_value}"/>

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

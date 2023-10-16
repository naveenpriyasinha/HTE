 <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/leave/DateDifference.js"></script> 
	<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/leave/DateVal.js"/>"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
	<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="common/script/tabcontent.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.foreigntourtravel.foreigntourtravel" var="commonLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
	
<c:set var="EmpDetVO" value="${resValue.EmpDet}" />
<c:set var="hrFtourReqDtl" value="${resValue.hrFtourReqDtl}" />
<c:set var="expIncuredByList" value="${resValue.expIncuredByList}" />
<c:set var="conveyanceModeList" value="${resValue.conveyanceModeList}" />
<c:set var="countryList" value="${resValue.countryList}" />
<c:set var="userId" value="${resValue.userId}" />
<c:set var="currentDate" value="${resValue.currentDate}" />
<c:set var="dAForCountry" value="${resValue.dAForCountry}" />
<c:set var="dAForCountryPerDay" value="${resValue.dAForCountryPerDay}" />
<c:set var="country" value="${resValue.country}" />
<c:set var="countryId" value="${resValue.countryId}" />
<c:set var="expIncurredBy" value="${resValue.expIncurredBy}" />
<c:set var="expIncurredById" value="${resValue.expIncurredById}" />
<c:set var="conveyanceMode" value="${resValue.conveyanceMode}" />
<c:set var="conveyanceModeId" value="${resValue.conveyanceModeId}" />

<c:set var="hrFtourReqDtl" value="${resValue.hrFtourReqDtl}" />
<c:set var="dateDiff" value="${resValue.dateDiff}" />
<c:set var="totDailyAll" value="${resValue.totDailyAll}" />
<c:set var="rateOfDollar" value="${resValue.rateOfDollar}" />
<c:set var="accUserInfo" value="${resValue.memDtlList}" />
<script><!--

function save()
{	
	
	returnFTTReq.action="./hrms.htm?actionFlag=setReturnAnnextureDtl";
	document.getElementById("save").disabled=true;
	returnFTTReq.submit();
}


function chechAnnextureDtl()
{
	document.getElementById("tcontenthplnk1").click();
}


function goBack()
{
	if(document.getElementById('goBackFlag').value=='1')
	{
		returnFTTReq.action="./hrms.htm?actionFlag=allTourListDtl&userId="+'${userId}';
		returnFTTReq.submit();
	}
	if(document.getElementById('goBackFlag').value=='2')
	{
		document.getElementById("tcontenthplnk1").click();
	}
}


function openAnnexurePage()
{
	
	document.getElementById("tcontenthplnk2").click();
	
}


function calTotalDailyAllInRs()
{
	document.forms[0].totalDailyAllRs.value=(document.forms[0].totalDailyAll.value)*(document.forms[0].rateOfDollar.value);
	document.forms[0].entAllRs.value=(document.forms[0].entAll.value)*(document.forms[0].rateOfDollar.value);
}

function dateDifferenceWOAlert(startDate,endDate)
				{

			diffDays=0;
				   if (startDate < endDate) {
				     var s = startDate;
				      var e = endDate;

				   } 
				   else if(startDate > endDate)
					{
					//	alert(alertMsgDates[4]);
					document.forms[0].todate.focus();
					document.forms[0].todate.selected;
					return -1;   
				   }
				  else {
				      var s = startDate;
				      var e = endDate;
				      				
				   }
				   var diffDays = Math.floor((e - s) / 86400000);
				
				 if(diffDays==0){
				 diffDays=1;
				 }
				 else{
				 diffDays+=1;
				 }

					 return diffDays;		
					 		}



function validateFTTData()
{
	
	if( (document.forms[0].retDate.value!='') || (document.forms[0].dateOfDep.value!='') )
	{
	var depDate_txt=document.forms[0].dateOfDep.value;
	var depDateArr=depDate_txt.split('/');
	var depDate=new Date(depDateArr[1]+"/"+depDateArr[0]+"/"+depDateArr[2]);
	
	var retDate_txt=document.forms[0].retDate.value;
	var retDateArr=retDate_txt.split('/');
	var retDate=new Date(retDateArr[1]+"/"+retDateArr[0]+"/"+retDateArr[2]);
	
	
	currDate=new Date('${currentDate}');
	
	if( (document.forms[0].dateOfDep.value!='') || (document.forms[0].retDate.value=='') )
	{
		if( depDate < currDate )
		{
			alert('<fmt:message  bundle="${commonLables}" key="HRMS.detDateGtSysDate"/>');
			document.getElementById('dateOfDep').value='';
			if(retDate > currDate)
			{
				document.getElementById('retDate').value='';
			}
			return;
		}
		
		if( retDate!='' )
		{
			if(retDate < depDate )
			{
				alert('<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDate"/>');
				document.getElementById('retDate').value='';
				return;
			}
		}
				
	}
	if ( document.forms[0].retDate.value!='' )
	{
		if( depDate!='' )
		{
			if( retDate < depDate )
			{
				alert('<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDate"/>');
				document.getElementById('retDate').value='';
				return;
			}
		}
	}
	
	if( (document.forms[0].retDate.value!='') && (document.forms[0].dateOfDep.value!='') )
	{
	if(document.forms[0].disTotalDailyAllInDollar.value=='')
	{
		//document.forms[0].disTotalDailyAll.focus();
	}
	else
	{
	var dateDiff=dateDifferenceWOAlert(depDate,retDate);
	var dispDAString=(document.forms[0].disTotalDailyAllInDollar.value).toString()+"*"+dateDiff.toString();
	var dA=eval((document.forms[0].disTotalDailyAllInDollar.value)*dateDiff);
	
	document.forms[0].stayDur.value=dateDiff;
	document.forms[0].disTotalDailyAll.value=dispDAString;
	document.forms[0].totalDailyAll.value=dA;
	//document.forms[0].durDays.value=dateDiff;
	var totalAllInRs=(dA)*(document.forms[0].rateOfDollar.value);
	document.forms[0].totalDailyAllRs.value=totalAllInRs;
	}
	}
	}
	else
	{
		return;
	}	
}


</script>



<hdiits:form name="returnFTTReq" id="returnFTTReq" validate="true" method="post" >
<hdiits:hidden name="goBackFlag" id="goBackFlag" />
<br>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1" id="tcontenthplnk1"><b>
			<hdiits:caption captionid="HRMS.tourReqDtl" bundle="${commonLables}"></hdiits:caption>
		</b></a></li>
		<li class="selected"><a href="#" id="tcontenthplnk2" rel="tcontent2"><b>
			<hdiits:caption captionid="HRMS.tourReturnDtl" bundle="${commonLables}"></hdiits:caption>
		</b></a></li>
	</ul>
</div>

<div id="FttRequest" name="FttRequest">

<div id="tcontent1" class="tabcontent" tabno="0">

<table class="tabtable" name="travelRequest" id="travelRequest">


	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center" width="25%">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.tourdtl"/></u></strong>
			</font>
		</td>
	</tr>
	<hdiits:hidden name="userId" id="userId" />
	<hdiits:hidden name="tourId" id="tourId" default="${hrFtourReqDtl.tourId}"/>
	<tr>
 		<td width="25%">
 			<b><fmt:message key="HRMS.tourname"/>:</b>
 		</td>
    	
    	<td width="25%">
    		${hrFtourReqDtl.tourName}
  		</td> 
  		
  		<td width="25%">
  			<b><fmt:message key="HRMS.Reffileno"/>&nbsp;:</b>
  		</td>
    	
    	<td width="25%">
    		<b>${hrFtourReqDtl.refFileNo}</b>
  		</td> 
   </tr>
   
   <tr>      
	  <td width="25%">
	  	<b><fmt:message key="HRMS.purpose" /></b>
	  </td>
	  
	  <td width="25%">
		<hdiits:textarea rows="2" cols="30" name="purpose" tabindex="7" id="purpose" default="${hrFtourReqDtl.purpose}" readonly="true"/>
	  </td>
   	  
   	  <td>
 		<b><fmt:message key="HRMS.sendorgdtl" />&nbsp;:</b>
 	  </td>
 	  
 	  <td>
    	${hrFtourReqDtl.sendOrgDtl}
  	  </td>
  	  
	<tr>	  
  	  <td align="left" width="13%">
			<b><fmt:message key="HRMS.expenditure" /></b>
		</td>
  	  
  	  <td>
    	${expIncurredBy}
  	  </td>
  	  
  	  <td>
	  	<b><fmt:message key="HRMS.entall" />&nbsp;:</b>	
  	</td>
    <td>
    	<c:set var="diff" value="${hrFtourReqDtl.entAll/rateOfDollar}"></c:set>
    	<hdiits:number captionid ="HRMS.entall" name="entAll" id="entAll" default="${diff}"/>
  	</td>  
      
  	</tr>  
  	<tr> 
  	  <td>
		<b><fmt:message key="HRMS.embassy" /></b>
	  </td>
	  
	  <td>
	  <c:if test="${hrFtourReqDtl.indEmbCareFlag =='0'}">
	  	<fmt:message key="HRMS.no" />
	  </c:if>
	  <c:if test="${hrFtourReqDtl.indEmbCareFlag =='1'}">
	  	<fmt:message key="HRMS.yes" />
	  </c:if>
	  </td>
	  
	  <td>
		<b><fmt:message key="HRMS.visited" /></b>
	  </td>
	
	  <td>
	  <c:if test="${hrFtourReqDtl.visitedPlaceFlag =='0'}">
	  	<fmt:message key="HRMS.no" />
	  </c:if>
	  <c:if test="${hrFtourReqDtl.visitedPlaceFlag =='1'}">
	  	<fmt:message key="HRMS.yes" />
	  </c:if>
	  </td>
	  
  	</tr>
  	  
  	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.journeydtl"/></u></strong>
			</font>
		</td>
	</tr>  
  	
  	<tr>
		<td align="left" width="13%">
			<b><fmt:message key="HRMS.country" /></b>
		</td>  
  	 	
  	 	<td>
  	 		${country}
  	 	</td>
  	
  		<td>
			<b><fmt:message key="HRMS.city" />&nbsp;:</b>
		</td>
		
		<td>
			${hrFtourReqDtl.city}
		</td>  
	</tr>
	
	<tr>
	<td>
		<b><fmt:message key="HRMS.dateofdep" /></b>
	</td>
	
	<td>
	
		<hdiits:dateTime name="dateOfDep" captionid="HRMS.dateofdep" bundle="${commonLables}" default="${hrFtourReqDtl.dateOfDep} 00:00:00"
		afterDateSelect="" onblur="validateFTTData();" maxvalue="31/12/9999"/>
	</td>
	
	<td>
		<b><fmt:message key="HRMS.retdt" /></b>
	</td>
	
	<td>
		
		<hdiits:dateTime name="retDate" captionid="HRMS.retdt" bundle="${commonLables}" default="${hrFtourReqDtl.dateOfRet} 00:00:00"
		afterDateSelect="" onblur="validateFTTData();" maxvalue="31/12/9999" />
		
	</td>
  	</tr>
  	
  	<tr>
	<td>
		<b><fmt:message key="HRMS.staydur" />&nbsp;:</b>
	</td>
    
    <td>
    	<hdiits:number captionid ="HRMS.staydur" name="stayDur" id="stayDur" default="${hrFtourReqDtl.stayDur}" readonly="true"/>
  	</td>   
  	
  	<td align="left" width="13%">
  		<b><fmt:message key="HRMS.convmode" /></b>
  	</td>
  	
  	<td>
  		  ${conveyanceMode}
  	</td>
  	 
</tr>

<tr>
			<td><b><fmt:message	key="HRMS.totdailyallInDollar" />:</b></td>
			<td><hdiits:number captionid="HRMS.totdailyallInDollar" readonly="false" floatAllowed="true" validation="txt.isrequired" bundle="${foreignTourLables}"
				name="disTotalDailyAllInDollar" maxlength="10" id="disTotalDailyAllInDollar" tabindex="16" mandatory="true" size="2" onblur="validateFTTData();" default="${dAForCountryPerDay}" />
			</td>
		</tr>

<tr>
	<td><b><fmt:message key="HRMS.totdailyall" />&nbsp;:</b></td>
	<td><hdiits:number captionid="HRMS.totdailyall" readonly="true" floatAllowed="true" default="${dAForCountryPerDay}*${dateDiff}"
				name="disTotalDailyAll" id="disTotalDailyAll" mandatory="false" size="5" style="background-color:lightblue" />
		<hdiits:number captionid="HRMS.totdailyall" readonly="true" style="background-color:lightblue"
				name="totalDailyAll" id="totalDailyAll" mandatory="false" size="8" default="${dAForCountry}"/>
			</td>	
			</tr>

<tr>
	<td>
		<b><fmt:message key="HRMS.rateofdol" />&nbsp;:</b>
	</td>
    
    <td>
    	<hdiits:number captionid ="HRMS.rateofdol" mandatory="true" floatAllowed="true" name="rateOfDollar" id="rateOfDollar" size="5" default="${rateOfDollar}"/> 		
  		<hdiits:button id="convert" type="button" name="convert" captionid="HRMS.convert" bundle="${commonLables}" onclick="calTotalDailyAllInRs();" />
  	</td>
  </tr>
  
  <tr>
  <td>
		<b><fmt:message key="HRMS.totdailyallrs" />&nbsp;:</b>
	</td>
    
    <td>
    	<hdiits:text captionid="HRMS.totdailyallrs" name="totalDailyAllRs" id="totalDailyAllRs" default="${hrFtourReqDtl.dailyAll}" readonly="true" style="background-color:lightblue" />
  	</td> 
  	<td>
  		<b><fmt:message key="HRMS.entallrs" />&nbsp;:</b>
  	</td>
    
    <td>
    	<hdiits:text captionid="HRMS.entallrs" name="entAllRs" id="entAllRs" default="${hrFtourReqDtl.entAll}" readonly="true" style="background-color:lightblue" />
   	</td> 
  </tr>
	<c:if test="${not empty hrFtourReqDtl.hrForeigntourmemTxns}"> 
	<tr bgcolor="#386CB7" style="">
			<td class="fieldLabel" colspan="5" align="center"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.membdtl" /></u></strong> </font></td>
		</tr>

	 <tr id="travelTable" style="" colspan="8" align="center">
			<td colspan="4" align="center">
			
			<table id="travelTableData" border="1" style=""
				cellpadding="0" cellspacing="0" BGCOLOR="WHITE" width="80%" align="center">
				<tr colspan="4">
					<td align="center" width="13%"><b><fmt:message
						key="HRMS.srno" /></b></td>
					<td align="center" width="13%"><b><fmt:message
						key="HRMS.name" /></b></td>
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.desig" /></b></td>
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.dept" /></b></td>
				</tr>
				
				<c:set var="srNo" value="1" />
				
				<c:forEach var="accUserInfo" items="${accUserInfo}">
				<tr>
				
					<td>
						${srNo}
					</td>
					
					<td>
						${accUserInfo.empName}
					</td>
					
					<td>
						 ${accUserInfo.departmentName} 
					</td>
					
					<td>
						${accUserInfo.designationName}
					</td>
					
				</tr>
				<c:set var="srNo" value="${srNo+1}" />
				</c:forEach>
				</table>
				
			</td>
		</tr>
		</c:if>
		
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.addremark" /></u></strong> </font></td>
		</tr>
		
		<tr>
			<td>
				<b><fmt:message key="HRMS.retdtl" /> :</b>
				
			</td>
			<td>
				<hdiits:button type="button" name="returnDetails" id="returnDetails" onclick="openAnnexurePage();" captionid="HRMS.clickhere" bundle="${commonLables}"/>
			</td>
		</tr>
		
 <br>
 <tr>
	
	<td colspan="4" align="center">
		<hdiits:button type="button" name="Save" id="Save" captionid="HRMS.save" bundle="${commonLables}" onclick="save();"/>
		<hdiits:button type="button" id="Close" name="Close" captionid="HRMS.close" bundle="${commonLables}" onclick="document.getElementById('goBackFlag').value=1;goBack();" />
	</td>
</tr>   
</table>


</div>


<div id="tcontent2" class="tabcontent" tabno="1">

<%@ include file="/WEB-INF/jsp/hrms/hr/foreignTourTravel/annexture.jsp"%>


</div>


</div>

<script type="text/javascript" >

initializetabcontent("maintab");
</script>


<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>

<%
}catch(Exception e){
	e.printStackTrace();
}
%>
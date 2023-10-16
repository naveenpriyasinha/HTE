<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.foreigntourtravel.foreigntourtravel" var="foreignTourLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="expIncuredByList" value="${resValue.expIncuredByList}" />
<c:set var="conveyanceModeList" value="${resValue.conveyanceModeList}" />
<c:set var="countryList" value="${resValue.countryList}" />
<c:set var="userId" value="${resValue.userId}" />
<c:set var="currentDate" value="${resValue.currentDate}" />
<c:set var="dAForCountry" value="${resValue.dAForCountry}" />


<script>

function validateFloat(txtFloatNumber){
	
	if(isNaN(txtFloatNumber.value)){
		alert('<fmt:message key="HRMS.entValidNo" />');
		return false;
	}
	else{
		return true;	
	}
}



function goBack()
	{
		document.foreignTourtravel.action="./hdiits.htm?actionFlag=getHomePage";
		document.foreignTourtravel.submit();
	}


function SearchEmp()
{
	var href='hdiits.htm?actionFlag=allData';
	window.open(href,'chield','width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');

}

function memberSelect()
{
	var checkboxes=document.forms[0].elements['check'];
	if(isNaN(checkboxes.length))
	{
		document.forms[0].elements['check'].checked=true;
	}
	else
	{
		for(i=0;i<checkboxes.length;i++)
		{
			if(document.forms[0].MemSel.status){
				checkboxes[i].checked=true;
			}
			else{
				checkboxes[i].checked=false;
			}
		}
	}
}


function deleteMember()
{

	var tb = document.getElementById('travelTableData');
	var deletedRow= new Array();
	var checkboxes=document.forms[0].elements['check'];
	var	j=0;
	if(isNaN(checkboxes.length))
	{
			document.getElementById('travelTable').style.display='none';
			document.getElementById('deleteMem').style.display='none';
			if(checkboxes.checked)
			{
				var index = eval(checkboxes.getAttribute("indexPos"));
				uniqueuserId=new Array();		
				k=0;
				document.getElementById("row_"+index).parentNode.removeChild(document.getElementById("row_"+index));
			}
	}
	else
	{	
		
		
		var checkedCounter=0;		
		for(i=0;i<checkboxes.length;i++)
		{
		
			if(checkboxes[i].checked)
			{
				var a=0;
				checkedCounter++;
				var index = eval(checkboxes[i].getAttribute("indexPos"));
				deletedRow[j]=document.getElementById("row_"+index);
				var tempArray=new Array();
				for(var y=0;y<uniqueUserId.length;y++)
				{
					if( document.getElementById('accUserId'+index).value != uniqueUserId[y])
					{
						tempArray[a]=uniqueUserId[y];
						a++;
					}
					
				}
				uniqueUserId = tempArray;
				k = uniqueUserId.length;
				j++;
				
			}
			
		}
		
		if( checkedCounter == document.forms[0].elements['check'].length )
		{
			document.getElementById('travelTable').style.display='none';
			document.getElementById('deleteMem').style.display='none';
		}
	}		
		j=0;
	deleteRowOfMembers(deletedRow);
	
}

function deleteRowOfMembers(delMember)
{
	for(d=0;d<delMember.length;d++)
	{
		
		delMember[d].parentNode.removeChild(delMember[d]);
		
	}
}



var empArray = new Array();
var emparray = new Array();
var uniqueUserId = new Array();
var empcount = 0 ;
var finalEmpArr = new Array();
var ctr=1;
var records=new Array();
var tempUserID=0;
var k=0;


function copyUserId(tempUserID)
{
	uniqueUserId[k]=tempUserID;
	k++;
	
}

function checkDuplicate(addUserId)
{
	var temp=0;
	for(var x=0;x<uniqueUserId.length;x++)
	{
		if( addUserId == uniqueUserId[x] )
		{
			temp++;
		}
	}
	if(temp>1)
	{
		return false;
	}
	else
	{
		return true;
	}
}



function empSearch(from)		
{
	
	for(var i=0; i<from.length; i++)
	{
	
	empArray[i] = from[i].split("~"); 
	records=empArray[i];
	tempUserID=records[4];
	copyUserId(tempUserID);
	
	}
	
		
	
		
	for(var j=0; j<from.length; j++)
	{
		var single = empArray[j];
		if(checkDuplicate(single[4]))
		{
		
	
	
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
		var depName=single[11];
	
		var tb = document.getElementById('travelTableData');
		var tableLength = tb.rows.length;
		var nextId = tableLength-1;
	
	
	
		var row = tb.insertRow(tableLength);
		row.setAttribute("id","row_"+nextId);
	
		row.setAttribute('bgColor','#F8F8FF');
	
		//1st cell
	 	var cell1 = row.insertCell(0);
	
	  	cell1.innerHTML+="<INPUT type='checkbox' id='check"+nextId+"' name='check' indexPos="+nextId+" onclick='document.forms[0].MemSel.status=false'>";
		
	  
		//2rd cell
	 	 var cell2 = row.insertCell(1);
	  	cell2.innerHTML+="<label>"+empName+"</label>";
	  	cell2.innerHTML+="<INPUT type='hidden'  id='empName"+nextId+"' value='"+empName+"' name='empName"+nextId+"'  maxlength=70>";
		//3th cell
	  	var cell3 = row.insertCell(2);
	  	cell3.innerHTML+="<label>"+depName+"</label>";
	  	cell3.innerHTML+="<INPUT type='hidden'  id='depName"+nextId+"' value='"+depName+"'  name='depName"+nextId+"'  maxlength=70>";
		//4th cell
	  	var cell4 = row.insertCell(3);
	  	cell4.innerHTML+="<label>"+desigName+"</label>";
	  	cell4.innerHTML+="<INPUT TYPE='hidden' id='desigName"+nextId+"' value='"+desigName+"'  name='desigName"+nextId+"' maxlength=70>";
  	  	cell4.innerHTML+="<INPUT TYPE='hidden' id='accUserId"+nextId+"' value='"+userid+"'  name='accUserId"+nextId+"' >";
  	  	
		ctr++;
		document.getElementById('nextId').value=nextId;
		if(document.getElementById('travelTable').style.display='none')
		{
			document.getElementById('MemSel').checked=false;
		}
		document.getElementById('travelTable').style.display='';
		document.getElementById('deleteMem').style.display='';
		}
	}
	
}
/*// Employee Search code End */


function calTotalDailyAllInRs()
{
		if( isNaN(document.forms[0].entAll.value) ) 
		{
			alert('<fmt:message key="HRMS.entValidNo" />');
			document.forms[0].entAll.focus();
			return false;
		}
		if( isNaN(document.forms[0].rateOfDollar.value) ) 
		{
			alert('<fmt:message key="HRMS.entValidNo" />');
			document.forms[0].rateOfDollar.focus();
			return false;
		}
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




function checkOrg(org)
{

	if(org.value=='300819')
	{
		document.getElementById('embassy_no').checked=true;
		document.getElementById('embassy_yes').disabled=true;
	}
	else
	{
		document.getElementById('embassy_yes').disabled=false;
	}
}

function checkPrevVisit(visit)
{

	if(visit.value=='0')
	{
		document.getElementById('prevDtlTD').style.display='none';
		document.getElementById('prevDtlBlack').style.display='none';
		document.getElementById('prevDtl').value='';
		document.getElementById('prevDtl').mandatory=false;		
		//document.getElementById('prevDtlGrey').style.display='';
		
	}

	if(visit.value=='1')
	{
		document.getElementById('prevDtlTD').style.display='';
		document.getElementById('prevDtlBlack').style.display='';

		document.getElementById('prevDtl').mandatory=true;
		//document.getElementById('prevDtlGrey').style.display='none';
	}
}

function validateFTTData()
{

	if( document.forms[0].disTotalDailyAllInDollar.value!='' )
	{
		if( isNaN(document.forms[0].disTotalDailyAllInDollar.value) )
		{
			alert('<fmt:message key="HRMS.entValidNo" />');
			document.forms[0].disTotalDailyAllInDollar.focus();
		}
	}
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
			alert('<fmt:message  bundle="${foreignTourLables}" key="HRMS.detDateGtSysDate"/>');
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
				alert('<fmt:message  bundle="${foreignTourLables}" key="HRMS.retDateLessDepDate"/>');
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
				alert('<fmt:message  bundle="${foreignTourLables}" key="HRMS.retDateLessDepDate"/>');
				document.getElementById('retDate').value='';
				return;
			}
		}
	}
	
	if( (document.forms[0].retDate.value!='') && (document.forms[0].dateOfDep.value!='') )
	{
	
	if(document.forms[0].disTotalDailyAllInDollar.value=='')
	{
		//document.getElementById('disTotalDailyAllInDollar').focus();
		//alert('<fmt:message  bundle="${foreignTourLables}" key="HRMS.entTotalDAInDollar"/>');
		
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


<hdiits:form name="foreignTourtravel" validate="true"
	action="./hrms.htm?actionFlag=submitFTTReq" method="post">

	<br>

	<table class="tabtable" name="travelRequest" id="travelRequest">

		<hdiits:hidden name="nextId" id="nextId"/>
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center" width="25%"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.tourdtl" /></u></strong> </font></td>
		</tr>

		<tr>
			<td width="25%"><b><fmt:message key="HRMS.tourname" />:</b></td>

			<td width="25%"><hdiits:text captionid="HRMS.tourname"
				name="tourName" id="tourName" mandatory="true"
				validation="txt.isrequired" bundle="${foreignTourLables}" tabindex="1" maxlength="200"/></td>

			<td width="25%"><b><fmt:message key="HRMS.Reffileno" />&nbsp;:</b>
			</td>

			<td width="25%"><hdiits:text captionid="HRMS.Reffileno"
				name="refFileNo" id="refFileNo" tabindex="2" maxlength="50"/></td>
		</tr>

		<tr>
			<td width="25%"><b><fmt:message key="HRMS.purpose" /></b></td>

			<td width="25%"><hdiits:textarea mandatory="true" rows="2"
				cols="30" name="purpose" tabindex="3" id="purpose"
				validation="txt.isrequired" captionid="HRMS.purpose" maxlength="2000" bundle="${foreignTourLables}" /></td>

			<td><b><fmt:message key="HRMS.sendorgdtl" />&nbsp;:</b></td>

			<td><hdiits:text captionid="HRMS.sendorgdtl" name="sendOrgDtl"
				id="sendOrgDtl" tabindex="4" bundle="${foreignTourLables}" maxlength="100"/></td>
		</tr>

		<tr>
			<td align="left" width="13%"><b><fmt:message
				key="HRMS.expenditure" /></b></td>

			<td><hdiits:select name="expenditure" size="1" id="expenditure"
				captionid="HRMS.expenditure" mandatory="true" validation="sel.isrequired" onchange="checkOrg(this);" bundle="${foreignTourLables}" tabindex="5">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="expIncuredBy" items="${expIncuredByList}">
					<hdiits:option value="${expIncuredBy.lookupId}">
								${expIncuredBy.lookupDesc}
							</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>


		<tr>
			<td><b><fmt:message key="HRMS.embassy" /></b></td>

			<td><hdiits:radio name="embassy" id="embassy_yes" value="1"
				mandatory="false" tabindex="6"/>
				 <b><fmt:message key="HRMS.yes" /></b> 
				 <hdiits:radio	name="embassy" id="embassy_no" value="0" default="0"
				mandatory="false" tabindex="7"/>
				 <b><fmt:message key="HRMS.no"  /></b></td>
		</tr>

		<tr>
			<td><b><fmt:message key="HRMS.invitation" /></b></td>
			<td><hdiits:radio name="invitation" id="invitation_yes"
				value="1" mandatory="false" tabindex="8"/>
				 <b><fmt:message key="HRMS.yes" /></b>
				<hdiits:radio name="invitation" id="invitation_no" value="0"
				default="0" mandatory="false" tabindex="9"/>
				 <b><fmt:message key="HRMS.no" /></b>
			</td>
		</tr>

		<tr>
			<td><b><fmt:message key="HRMS.visited" /></b></td>

			<td><hdiits:radio name="visited" id="visited_yes" value="1" 
				mandatory="false" onclick="checkPrevVisit(this);" tabindex="10" />
				 <b><fmt:message key="HRMS.yes"/></b>
				 <hdiits:radio name="visited" id="visited_no" value="0"  mandatory="false"
				onclick="checkPrevVisit(this);" default="0" tabindex="11"/>
				 <b><fmt:message key="HRMS.no" /></b></td>
		</tr>

		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.journeydtl" /></u></strong> </font></td>
		</tr>

		<tr>
			<td align="left" width="13%"><b><fmt:message
				key="HRMS.country" /></b></td>

			<td><hdiits:select name="country" size="1" id="country" captionid="HRMS.country" validation="sel.isrequired" mandatory="true" bundle="${foreignTourLables}" tabindex="12">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="country" items="${countryList}">
					<hdiits:option value="${country.countryCode}">
								${country.countryName}
							</hdiits:option>
				</c:forEach>
			</hdiits:select></td>

			<td><b><fmt:message
				key="HRMS.city" />&nbsp;:</b></td>

			<td><hdiits:text captionid="HRMS.city" name="city" id="city" tabindex="13" maxlength="200"/></td>
		</tr>

		<tr>
			<td><b><fmt:message key="HRMS.dateofdep" /></b></td>

			<td><hdiits:hidden name="dateFlag" id="dateFlag" /> 
			<hdiits:dateTime name="dateOfDep" captionid="HRMS.dateofdep" validation="txt.isrequired" maxvalue="31/12/9999"  bundle="${foreignTourLables}"
				afterDateSelect="" onblur="validateFTTData();" mandatory="true" tabindex="14"/></td>

			<td><b><fmt:message key="HRMS.retdt" /></b></td>

			<td><hdiits:dateTime name="retDate" captionid="HRMS.retdt" validation="txt.isrequired" maxvalue="31/12/9999"  mandatory="true" bundle="${foreignTourLables}"
				afterDateSelect="" onblur="validateFTTData();" tabindex="15"/></td>
		</tr>

		<tr>
			<td><b><fmt:message	key="HRMS.totdailyallInDollar" />:</b></td>
			<td><hdiits:number captionid="HRMS.totdailyallInDollar" readonly="false" floatAllowed="true" validation="txt.isrequired" bundle="${foreignTourLables}"
				name="disTotalDailyAllInDollar" maxlength="10" id="disTotalDailyAllInDollar" tabindex="16" mandatory="true" size="2" onblur="validateFTTData();"/>
		</tr>

		<tr>
			<td><b><fmt:message key="HRMS.staydur" />&nbsp;:</b></td>

			<td><hdiits:text captionid="HRMS.staydur" name="stayDur" readonly="true"
				id="stayDur"/></td>

			<td align="left" width="13%">
			<b><fmt:message	key="HRMS.convmode" /></b></td>

			<td><hdiits:select name="convMode" size="1" id="convMode"
				captionid="HRMS.convmode" validation="sel.isrequired" mandatory="true" bundle="${foreignTourLables}" tabindex="17">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="conveyanceMode" items="${conveyanceModeList}">
					<hdiits:option value="${conveyanceMode.lookupId}">
								${conveyanceMode.lookupDesc}
							</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		
		
		
		
		
		<tr>
			<td><b><fmt:message	key="HRMS.totdailyall" />:</b></td>

			<td><hdiits:number captionid="HRMS.totdailyall" readonly="true" floatAllowed="true"
				name="disTotalDailyAll" id="disTotalDailyAll" mandatory="false" size="5" style="background-color:lightblue" />
				<hdiits:number captionid="HRMS.totdailyall" readonly="true"
				name="totalDailyAll" id="totalDailyAll" mandatory="false" size="8" style="background-color:lightblue"/>
			</td>

			<td><b><fmt:message	key="HRMS.entall" />&nbsp;:</b></td>

			<td><hdiits:number captionid="HRMS.entall" name="entAll"
				id="entAll" mandatory="false" tabindex="18" bundle="${foreignTourLables}" maxlength="10"/></td>
		</tr>

		<tr>
			<td><b><fmt:message	key="HRMS.rateofdol" />&nbsp;:</b></td>

			<td><hdiits:number captionid="HRMS.rateofdol"
				name="rateOfDollar" id="rateOfDollar" tabindex="19" floatAllowed="true" default="45.0" maxlength="5" /> <hdiits:button
				id="convert" type="button" tabindex="20"  name="convert" captionid="HRMS.convert"
				bundle="${foreignTourLables}" onclick="calTotalDailyAllInRs();"/></td>
		</tr>

		<tr>
			<td><b><fmt:message	key="HRMS.totdailyallrs" />&nbsp;:</b></td>

			<td><hdiits:number readonly="true" captionid="HRMS.totdailyallrs" style="background-color:lightblue"
				name="totalDailyAllRs" id="totalDailyAllRs" /></td>

			<td><b><fmt:message	key="HRMS.entallrs" />&nbsp;:</b></td>

			<td><hdiits:number readonly="true" captionid="HRMS.entallrs" name="entAllRs" style="background-color:lightblue"
				id="entAllRs" /></td>
		</tr>


		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.membdtl" /></u></strong> </font></td>
		</tr>

		<tr>
			<td><b><fmt:message	key="HRMS.memb" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><hdiits:button id="add" type="button" name="add" tabindex="21"
				captionid="HRMS.add" bundle="${foreignTourLables}" onclick="SearchEmp();" /></td>
		</tr>


		<tr id="travelTable" colspan="8" style="display:none">
			<td colspan="4" align="center">
			<table id="travelTableData" border="1"
				cellpadding="0" cellspacing="0" BGCOLOR="#A9A9A9" width="80%" align="center">
				<tr colspan="4">
					<td align="center" width="8%"><b><hdiits:checkbox value="0" name="MemSel" id="MemSel" onclick="memberSelect();" /></b></td>
					<td align="center" width="15%"><b><fmt:message
						key="HRMS.name" /></b></td>
					<td align="center" width="15%"><b><fmt:message
						key="HRMS.dept" /></b></td>
					<td align="center" width="15%"><b><fmt:message
						key="HRMS.desig" /></b></td>
					
				</tr>

			</table>
			</td>
		</tr>

		<tr style="display:none" id="deleteMem">
			<td class="fieldLabel" colspan="5" align="center" >
			<hdiits:button type="button" captionid="HRMS.delete" bundle="${foreignTourLables}" name="deleteTourMem" id="deleteTourMem"
					onclick="deleteMember();" />
				
			</td>
		</tr>
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font
				color="#ffffff"> <strong><u><fmt:message
				key="HRMS.addremark" /></u></strong> </font></td>
		</tr>

		<tr>
			<td id="prevDtlBlack" style="display:none" ><b><fmt:message key="HRMS.prevdtl"></fmt:message></b></td>
				<td style="display:none" id="prevDtlTD"><hdiits:textarea mandatory="false" rows="2" cols="30"
				name="prevDtl" tabindex="22" id="prevDtl"
				captionid="HRMS.prevdtl"  bundle="${foreignTourLables}" /></td>

			<td><b><fmt:message key="HRMS.reason"></fmt:message></b></td>
			<hdiits:hidden name="userId" default="2" />
			<td><hdiits:textarea mandatory="true" rows="2" cols="30"
				name="reason" tabindex="23" id="reason" validation="txt.isrequired"
				captionid="HRMS.reason" bundle="${foreignTourLables}" /></td>
		</tr>

		<tr>
			<td align="center" colspan="4"><hdiits:formSubmitButton id="fwd"
				type="button" name="fwd" tabindex="24" captionid="HRMS.fwd"
				bundle="${foreignTourLables}" /> <hdiits:resetbutton title="Reset" value="Reset" name="Reset" type="button" tabindex="25"/>
					<script>
						  var resetButton='<fmt:message key="HRMS.reset"/>';
					      document.forms[0].Reset.value=resetButton;
				    </script>
				 <hdiits:button	type="button" id="Close" name="Close" tabindex="26" captionid="HRMS.close"
				bundle="${foreignTourLables}" onclick="goBack();" /></td>
		</tr>

	</table>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

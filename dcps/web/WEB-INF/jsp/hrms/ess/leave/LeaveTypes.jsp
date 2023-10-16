<%@ include file="./alertMessage.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%
try {
%>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="LeaveTList" value="${resultValue.leaveTList}">
</c:set>
<c:set var="combLeaveDtl" value="${resultValue.combLeaveVO}">
</c:set>
<c:set var="combEdit" value="${combLeaveDtl}">
</c:set>
<c:set var="msg" value="${resultValue.msg}"></c:set>
<c:set var="holidayList" value="${resultValue.holidayList}">
</c:set>
<c:set var="isForApproval" value="${resultValue.isForApproval}">
</c:set>
<c:set var="gender" value="${resultValue.gender}">
</c:set>
<c:set var="isSameLeaveAllowed"
	value="${resultValue.isSameLeaveAllowed}">
</c:set>

<fmt:setBundle basename="resources.ess.leave.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption"
	var="leaveCaption" scope="request" />
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/leavetypes.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/cmntableheader.js"/>"></script>
<script>

var win;  
/*window.onunload = function(){
setInterval('closeWindow_toolbar',1);
closeWindow_toolbar();
if(win){
window.close();
}
}*/
var MsgConst;
var tempArr=new Array();
var leaveArr_Loc=new Array();
var natureofleave=null;
var fromdate=null;
var todate=null;
var noofdays=null;
var arrData=null;
var toDateArr= new Array();
var fromDateArr= new Array();
var editedObject;
var toDate;
var fromDate;
var nod=0; 
var startDateArr=new Array();// for storing start date in dd/mm/yyyy format
var endDateArr=new Array();// for storing end date in dd/mm/yyyy format
var leaveDataArr= new Array();
var duplicateLeave=false;
var counter =0;
var isSameLeaveAllowed="${isSameLeaveAllowed}";

		if(window.opener.leaveArr.length>0){
				leaveArr_Loc=window.opener.leaveArr;	
				leaveDataArr=window.opener.leaveDataArrParent;
				counter =eval(window.opener.counter);
				toDateArr= window.opener.toDateArr;
				fromDateArr= window.opener.fromDateArr;
				editedObject= window.opener.editedObject;
				toDate= window.opener.toDate;
				fromDate= window.opener.fromDate;
				duplicateLeave= window.opener.duplicateLeave;
		}else{
				leaveArr_Loc=new Array();
				var toDateArr= new Array();
				var fromDateArr= new Array();

		}

document.onclick=checkHalfDay;
document.onkeydown=checkHalfDay;







function deleteRow(actionFlag)
{
document.forms[0].Delete.disabled=false;

var delimiter="~";
var deletedObject="";	
var nondeletedObject="";
var checkCounter=0;
	var checks=document.getElementsByTagName("INPUT");
		for(j=0;j<checks.length;j++){
			if(checks[j].type=="checkbox"){
				if(checks[j].name=="cmb_leave"){
					if(checks[j].checked){
						editedObject=document.getElementById("row"+checks[j].id);
						//window.opener.parent.document.getElementById(checks[j].id).status=true;
						deletedObject+="row"+checks[j].id+delimiter;
						checkCounter+=1;
							}
						else
						{
 						 nondeletedObject+="row"+checks[j].id+delimiter;
						}	
							
					}
				}
			}

		if(actionFlag==2){
			delRow(deletedObject,nondeletedObject);
		}
		else{
			if(checkCounter>1){
				alert('<fmt:message  bundle="${alertLables}" key="HRMS.MULTIPLE_EDIT"/>');
			}
		else if(checkCounter<1){
				alert('<fmt:message  bundle="${alertLables}" key="HRMS.SELECT_LEAVE"/>');
		}
		else{

				value=document.getElementById("txt"+editedObject.cnt+editedObject.counter).value;
				document.forms[0].rowUpdateCounter.value=editedObject.counter;//to trace which row is edited
				fillData(value);
				document.forms[0].Delete.disabled=true;
				document.forms[0].updateData.style.display="";
				document.getElementById("updateButton").style.display="";
			if("${combEdit[1]}"!=''){
				document.forms[0].save.disabled=true;
				document.forms[0].save.style.display="none";
				document.getElementById("saveButton").style.display="none";
				}else{
				document.forms[0].save.style.display="none";
				document.getElementById("saveButton").style.display="none";
				}
		}
	}
}

function delRow(deletedObject,nondeletedObject){
var delCounter=0;
var temp="";
var tbody = document.getElementById("lvdtl").getElementsByTagName("tbody")[0]; 
var rows=deletedObject.split("~");
	for(m=0;m<rows.length-1;m++){
			temp+=document.getElementById(rows[m]).cnt+"~";
						for(l=0;l<leaveArr_Loc.length;l++){
							if(document.getElementById(rows[m]).cnt==leaveArr_Loc[l]){
									leaveArr_Loc.splice(l,1);
									toDateArr.splice(l,1);
									fromDateArr.splice(l,1);
									leaveDataArr.splice(l,1);

								}
							}
				counter=eval(counter-1);					
			delCounter+=1;	
			tbody.removeChild(document.getElementById(rows[m]));
	
		}
	  
	  if(delCounter>0){
							var serialUpdate=nondeletedObject.split("~");
							for(k=0;k<serialUpdate.length-1;k++){
							currentRow=document.getElementById(serialUpdate[k]);
								var cell=document.getElementById("sr_"+document.getElementById(serialUpdate[k]).cnt+document.getElementById(serialUpdate[k]).counter);
								var checkbox="chk"+document.getElementById(serialUpdate[k]).cnt+document.getElementById(serialUpdate[k]).counter;
	                            document.getElementById("txt"+currentRow.cnt+currentRow.counter).setAttribute("id","txt"+currentRow.cnt+k);								
								document.getElementById(serialUpdate[k]).setAttribute("counter",k);
								document.getElementById(checkbox).setAttribute("counter",k);
								cell.innerHTML=eval(k+1);
								document.forms[0].save.style.display="none";
								document.forms[0].updateData.style.display="none";						
						
							}
							
							
						}
	  
	/* 
	The below case executed when all the rows are deleted so the counter is reset to 0;
	and making the table for combintational leave invisible.
	*/
	if(nondeletedObject==""){
		tbody.innerText="";
		document.getElementById("lvdtl").style.display="none";
		counter=0;
	}
}
function submitData(){
	window.opener.parent.document.getElementById('lvdtl').style.display='';
	window.opener.visibleAllRows(window.opener.parent.document.getElementById('lvdtl'));
	if(window.opener.addRow(0,leaveDataArr))
	{
			window.close();
	}
}

	function showOrd(combo){

						if(combo.value=="3_3_5" || combo.value=="3_3_6" ){
								document.getElementById("ordinarycir").style.display="";
				
						}else{
								document.getElementById("ordinarycir").style.display="none";
						}
				}


	function editData(isFromChild){
					natureofleave=document.frmleaveapply.natureofleave.value.split('_');
					fromdate=document.frmleaveapply.fromdate.value;
					todate=document.frmleaveapply.todate.value;
					ordinaryCir="-1";	
					var noofdays=0;
					var delimiter="~";
					document.frmleaveapply.action="hdiits.htm?actionFlag=viewLeaveTypes&flag=0";
					var Date1=document.frmleaveapply.fromdate.value.split("/");
					var Date2=document.frmleaveapply.todate.value.split("/");
					Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
					Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
			if(natureofleave[0]=='1'){
				if((noofdays=dateDifferenceWOAlert(new Date(Date1),new Date(Date2)))>0){
					noofdays=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,"${holidayList}",document.frmleaveapply,alertMsgDates);
					document.frmleaveapply.noofdays.value=noofdays;
			}else{
					alert('<fmt:message  bundle="${alertLables}" key="HRMS.FROMDATE_LESSTHAN_ENDDATE"/>');
					return;
			}
			}
			else if(natureofleave[0]=='2')
			{
			    noofdays=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply,alertMsgDates);
				if(!isNaN(eval(noofdays))){
					document.frmleaveapply.noofdays.value=noofdays;
				}
			}
			else{
				noofdays=dateDifferenceWOAlert(new Date(Date1),new Date(Date2));
				document.frmleaveapply.noofdays.value=noofdays;
			}
				if(eval(noofdays)>0){
				var previousValue="";
				if(isFromChild==1){
					previousValue=document.getElementById("txt"+arrData[0]+document.forms[0].rowUpdateCounter.value).value;
					if(natureofleave[0]=='3'){
						if(document.frmleaveapply.ordcir[0].status){
						//document.getElementById("txt"+arrData[0]+document.forms[0].rowUpdateCounter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+document.frmleaveapply.ordcir[0].value;		
						ordinaryCir=document.frmleaveapply.ordcir[0].value;
		}
		else{
			
			//document.getElementById("txt"+arrData[0]+document.forms[0].rowUpdateCounter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+document.frmleaveapply.ordcir[1].value;
			ordinaryCir=document.frmleaveapply.ordcir[1].value;
		}
   }
   else{
   		//document.getElementById("txt"+arrData[0]+document.forms[0].rowUpdateCounter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+"-1";
		   ordinaryCir="-1";
   }


				}else{
				previousValue=document.getElementById("txt"+arrData[0]+window.opener.document.forms[0].counter.value).value;
				if(natureofleave[0]=='3'){
		if(document.frmleaveapply.ordcir[0].status){
			///document.getElementById("txt"+arrData[0]+document.forms[0].rowUpdateCounter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+document.frmleaveapply.ordcir[0].value;		
			//document.getElementById("txt"+arrData[0]+window.opener.document.forms[0].counter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+document.frmleaveapply.ordcir[0].value;
			ordinaryCir=document.frmleaveapply.ordcir[0].value;
		}
		else{
			
			///sdocument.getElementById("txt"+arrData[0]+document.forms[0].rowUpdateCounter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+document.frmleaveapply.ordcir[1].value;
			ordinaryCir=document.frmleaveapply.ordcir[1].value;
			//document.getElementById("txt"+arrData[0]+window.opener.document.forms[0].counter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+document.frmleaveapply.ordcir[1].value;
		}
   }
   else{

   		    //document.getElementById("txt"+arrData[0]+document.forms[0].rowUpdateCounter.value).value=document.forms[0].natureofleave.value+"~" + fromdate+"~"+todate+"~"+noofdays+"~"+"-1";
			ordinaryCir="-1";

   }
				}
				
  editRow(arrData[0],document.forms[0].natureofleave.value,previousValue,noofdays,fromdate,todate,isFromChild,ordinaryCir);
				
				if("${combEdit[1]}"!='')
				document.forms[0].save.disabled=true;
				
}



}
function addLeaveDetail(frm,oprFlag){
var mainPgFieldArray= new Array('natureofleave');
			statusMainPgValidation =  validateSpecificFormFields(mainPgFieldArray);
				if(!statusMainPgValidation)
						{
							return;
						}

natureofleave=frm.natureofleave.value.split('_');
fromdate=frm.fromdate.value;
todate=frm.todate.value;
getValues(oprFlag);
return true;
}
var dataCounter=0;
function getValues(oprFlag){
			
leavetype="CL";

	if(natureofleave[0]==eval(1)){
			if(document.frmleaveapply.halfday[0].status){
				//window.opener.document.forms[0].elements["halfday_comb"].value=document.frmleaveapply.halfday[0].value;
			}
			else{
			//window.opener.document.forms[0].elements["halfday_comb"].value=document.frmleaveapply.halfday[1].value;
			}
			if(document.frmleaveapply.before2[0].status){
				//window.opener.document.forms[0].elements["before2_comb"].value=document.frmleaveapply.before2[0].value;
			}
			else{
					//window.opener.document.forms[0].elements["before2_comb"].value=document.frmleaveapply.before2[1].value;
			}
			if(document.frmleaveapply.first_last_day[0].status){
				//window.opener.document.forms[0].elements["firstDay_comb"].value=document.frmleaveapply.first_last_day[0].value;
			}
			else{
				//window.opener.document.forms[0].elements["firstDay_comb"].value=document.frmleaveapply.first_last_day[1].value;
			}
				
		}
	else{
		if(window.opener.document.forms[0].elements["before2_comb"].value!="" && window.opener.document.forms[0].elements["firstDay_comb"].value!="0"){
			var beforeValue=window.opener.document.forms[0].elements["before2_comb"].value;
			var firstDay=window.opener.document.forms[0].elements["firstDay_comb"].value;
				if(beforeValue==1 && firstDay==0 ){
						alert("You can't take leave having halfday at enddate i.e before 2 p.m.")
						return ;
				}					

		}
	 else if(natureofleave[0]==eval(3)){
		if(document.frmleaveapply.ordcir[0].status){
			window.opener.document.forms[0].elements["ordi_comb"].value=document.frmleaveapply.ordcir[0].value;
		}
		else{
			window.opener.document.forms[0].elements["ordi_comb"].value=document.frmleaveapply.ordcir[1].value;
		}
	 }
	}
					var noofdays=0;
					var delimiter="~";
					document.frmleaveapply.action="hdiits.htm?actionFlag=viewLeaveTypes&flag=0";
					var Date1=document.frmleaveapply.fromdate.value.split("/");
					var Date2=document.frmleaveapply.todate.value.split("/");
					Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
					Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
	if(natureofleave[0]=='1'){
			if((noofdays=dateDifferenceWOAlert(new Date(Date1),new Date(Date2)))>0){
					noofdays=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,"${holidayList}",document.frmleaveapply,alertMsgDates);
					document.frmleaveapply.noofdays.value=noofdays;
			}else{
					alert('<fmt:message  bundle="${alertLables}" key="HRMS.FROMDATE_LESSTHAN_ENDDATE"/>');
					return;
			}
	}
	else if(natureofleave[0]=='2')
			{
			    noofdays=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply,alertMsgDates);
				if(!isNaN(eval(noofdays))){
					document.frmleaveapply.noofdays.value=noofdays;
				}
			}
	else{
				noofdays=dateDifferenceWOAlert(new Date(Date1),new Date(Date2));
				document.frmleaveapply.noofdays.value=noofdays;
	}
	if(eval(noofdays)>0){
			var leavedetails=document.forms[0].natureofleave.value+delimiter+fromdate+delimiter+todate+delimiter+noofdays;
	 if(natureofleave[0]==eval(3)){
	  
		if(document.frmleaveapply.ordcir[0].status){
		leavedetails=leavedetails+delimiter+document.forms[0].ordcir[0].value;
		}
		else{
		leavedetails=leavedetails+delimiter+document.forms[0].ordcir[1].value;
		}
	 }
	 else{
	 leavedetails=leavedetails+delimiter+"-1";
	 }


			if(oprFlag==1){

				leaveDataArr[eval(eval(leaveDataArr.length))]=leavedetails;
					
					dataCounter++;
					document.forms[0].elements["leavedtls"].value=leavedetails;
					counter=eval(counter)+1;
					addRow();
					document.frmleaveapply.natureofleave.options[0].selected=true;						
					document.frmleaveapply.fromdate.value='';
					document.frmleaveapply.todate.value='';
					document.getElementById("nod").innerHTML="-";
					self.focus();
			}
			else{
				var previousValue=window.opener.document.getElementById("txt"+arrData[0]+window.opener.document.forms[0].counter.value).value;
				window.opener.document.getElementById("txt"+arrData[0]+window.opener.document.forms[0].counter.value).value="";
				window.opener.document.getElementById("txt"+arrData[0]+window.opener.document.forms[0].counter.value).value=leavedetails;
			if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.SAVE"/>')){
						editData(0);
					if(window.opener.addRow(0,leaveDataArr)){
						self.close(); 
					}
					else{
						window.location.reload();
					}
				
				}
		}
		}
		else{
		if(document.frmleaveapply.fromdate.value!="" && document.frmleaveapply.todate.value!=""){
			alert('<fmt:message  bundle="${alertLables}" key="HRMS.FROMDATE_LESSTHAN_ENDDATE"/>');
		}
		}
	}
function checkHalfDay()
{
var  nod;
if(document.frmleaveapply.fromdate.value!="" &&  document.frmleaveapply.todate.value!=""){



	if(document.frmleaveapply.natureofleave.value=="1_1"){
		 nod=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply);
		 document.getElementById("halfday").style.display="";
 		 document.getElementById("first_second_day").style.display="";
 		 
		if(document.frmleaveapply.halfday[0].status){
			document.getElementById('before2_tbl').style.display="";
			document.getElementById('first_second_Day').style.display="";
		}
		else if(document.frmleaveapply.halfday[1].status){
			document.getElementById('before2_tbl').style.display="none";
			document.getElementById('first_second_Day').style.display="none";
		}



			/*if(eval(nod)==1 || eval(nod)==0.5){
				document.getElementById("halfday").style.display="";		
			}
			else{
				document.getElementById("halfday").style.display="none";
			}*/
	}
	else{
		document.getElementById("halfday").style.display="none";
		var Date1=document.frmleaveapply.fromdate.value.split("/");
		var Date2=document.frmleaveapply.todate.value.split("/");
		Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
		Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
		
		nod=dateDifference(new Date(Date1),new Date(Date2),alertMsgDates);
	}

document.getElementById("nod").innerHTML=eval(nod);
document.getElementById("noofdays").style.display="";


}
}

function closeWindow(){
	window.close();
}


function closeWindow_toolbar(){

if(counter>1){
if(document.getElementById("checkAll")!=null){
document.getElementById("checkAll").status=true;
checkAll();
deleteRow(2);
var state=window.opener.deleteRow(2);
if(!state){
window.close();
}else
window.close();
}
}
}
</script>

<hdiits:form name="frmleaveapply" validate="true" method="POST">
	<br />
	<br />
	<br />
	<br />
	<div><hdiits:fieldGroup bundle="${leaveCaption}"
		expandable="true" titleCaptionId="HRMS.CombinationLeaveDtl"
		id="combinationLeaveGrp">
		<table border="0" width="90%" align="center">

			<tr>
				<td><hdiits:caption captionid="HRMS.NatureOfLeave"
					bundle="${leaveCaption}" /></td>
				<td><c:set var="readOnly" value="false" /> <c:if
					test="${isForApproval=='1'}">
					<c:set var="readOnly" value="true" />
				</c:if> <hdiits:select name="natureofleave" size="1"
					captionid="HRMS.natureofleave" validation="sel.isrequired"
					sort="false" mandatory="true" onchange="showOrd(this);"
					tabindex="1" readonly="${readOnly}" bundle="${leaveCaption}">
					<hdiits:option value="0">
						<fmt:message key="HRMS.select" />
					</hdiits:option>
					<c:forEach var="leavetypes" items="${LeaveTList}">

						<hdiits:option
							value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}"
							id="${leavetypes.leaveTypeid}">
										${leavetypes.leaveTypeName}
										</hdiits:option>
					</c:forEach>
				</hdiits:select></td>

				<td>&nbsp;</td>

				<td id="noofdays" style="display:none"><hdiits:caption
					captionid="HRMS.NoD1" bundle="${leaveCaption}" />&nbsp;: <label
					id="nod"></label></td>
			</tr>

			<tr>
				<td><hdiits:caption captionid="HRMS.FromDate"
					bundle="${leaveCaption}" /></td>
				<td><hdiits:dateTime validation="txt.isrequired"
					name="fromdate" captionid="HRMS.fromdate" bundle="${leaveCaption}"
					mandatory="true" tabindex="4" maxvalue="31/12/9999"></hdiits:dateTime></td>
				<td>&nbsp;</td>
				<td align="left"><hdiits:caption captionid="HRMS.ToDate"
					bundle="${leaveCaption}" /> <hdiits:dateTime
					validation="txt.isrequired" name="todate" captionid="HRMS.todate"
					bundle="${leaveCaption}" mandatory="true" tabindex="5"
					maxvalue="31/12/9999"></hdiits:dateTime></td>
			</tr>
			<tr id="ordinarycir" style="display:none">
				<td><hdiits:caption captionid="HRMS.ordcir"
					bundle="${leaveCaption}" /></td>
				<td><hdiits:radio captionid="HRMS.Yes" bundle="${leaveCaption}"
					name="ordcir" value="1" mandatory="false" tabindex="2" />
				&nbsp;&nbsp; <hdiits:radio captionid="HRMS.No"
					bundle="${leaveCaption}" name="ordcir" value="0" mandatory="false"
					tabindex="3" default="0"/> &nbsp;&nbsp;</td>

			</tr>
			<tr id="halfday" style="display:none">
				<td><hdiits:caption captionid="HRMS.halfday"
					bundle="${leaveCaption}" /></td>
				<td><hdiits:radio captionid="HRMS.Yes" bundle="${leaveCaption}"
					name="halfday" value="1" mandatory="false" tabindex="6" /> <hdiits:radio
					captionid="HRMS.No" bundle="${leaveCaption}" name="halfday"
					value="0" mandatory="false" default="0" tabindex="7" /></td>
				<td>
				<table id="before2_tbl" width="100%" style="display:none">
					<tr>
						<td><hdiits:caption captionid="HRMS.before2"
							bundle="${leaveCaption}" /></td>
						<td><hdiits:radio name="before2" id="before2Yes"
							bundle="${leaveCaption}" value="1" mandatory="false" tabindex="8" /><hdiits:caption
							captionid="HRMS.Yes" bundle="${leaveCaption}" /> <hdiits:radio
							captionid="HRMS.No" id="before2No" bundle="${leaveCaption}"
							name="before2" value="0" tabindex="9" mandatory="false"
							default="0" /></td>
					</tr>
				</table>

				</td>

			</tr>

			<tr>
				<td align="center" colspan="4">&nbsp; <hdiits:hidden
					name="noofdays" caption="noofdays" default="0" /> <hdiits:hidden
					name="leavedtls" caption="a" /></td>
				<hdiits:hidden name="rowUpdateCounter" caption="rowupdatecounter"
					default="0" />
			</tr>

			<tr>
				<td id="editRowTD"></td>
			</tr>
			<tr>
				<td align="center" colspan="4">
				<table border=0>
					<tr>
						<td><hdiits:button type="button" id="addButton"
							captionid="HRMS.Add1" bundle="${leaveCaption}" name="Submit"
							tabindex="11" onclick="return addLeaveDetail(this.form,1);" /></td>
						<td style="display:none" id="saveButton"><hdiits:button
							type="button" captionid="HRMS.SaveButton" tabindex="10"
							bundle="${leaveCaption}" name="save"
							onclick="return addLeaveDetail(this.form,2);" /></td>
						<td style="display:none" id="updateButton"><hdiits:button
							type="button" captionid="HRMS.BtnUpdate" bundle="${leaveCaption}"
							name="updateData" tabindex="13" onclick="return editData(1);" />
						</td>
						<td><hdiits:button type="button" id="returnDataButton"
							captionid="HRMS.ReturnLeaveData1" bundle="${leaveCaption}"
							name="submitLeaveData" tabindex="12" onclick="submitData();" />
						</td>
						<td><hdiits:button type="button" captionid="HRMS.Close"
							bundle="${leaveCaption}" name="close" value="close" tabindex="14"
							onclick="closeWindow();" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
	</hdiits:fieldGroup></div>
	<br>
	<br>

	<table id="lvdtl" width="100%"
		style="border-collapse: collapse; display:none" borderColor="BLACK"
		border=1>
		<tbody>
		</tbody>
		<tbody align="center">
			<tr align="center">
				<td align="center" colspan="6"><hdiits:button type="button"
					name="edit" captionid="HRMS.Edit" bundle="${leaveCaption}"
					onclick="deleteRow(1);" /> <hdiits:button type="button"
					name="Delete" captionid="HRMS.Delete" bundle="${leaveCaption}"
					onclick="deleteRow(2);" /> <!-- window.opener.deleteRow(2);--></td>
			</tr>

		</tbody>


		<hdiits:select name="nol" size="1" caption="drop_down"
			validation="sel.isrequired" sort="false" mandatory="false"
			readonly="${readOnly}" style="display:none">
			<hdiits:option value="0" selected="true">
				<fmt:message key="HRMS.select" />
			</hdiits:option>
			<c:forEach var="leavetypes" items="${LeaveTList}">
				<c:if
					test="${leavetypes.leaveTypeid != 1 && leavetypes.leaveTypeid != 7  && leavetypes.leaveTypeid != 11}">
					<hdiits:option
						value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}"
						id="${leavetypes.leaveTypeid}">
										${leavetypes.leaveTypeName}
										</hdiits:option>
				</c:if>
			</c:forEach>
		</hdiits:select>

	</table>
	<script>
				redisplayAddRow(0,leaveDataArr);
</script>
	<c:if test="${not empty combEdit[1]}">
		<script>
					fillData("${combEdit[1]}");
				</script>
	</c:if>


	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="natureofleave" />
</hdiits:form>
<script>
function editRow(oldcomponentid,newcomponentid,previousValue,noofdays,fromdate,todate,isFromChild,ordinaryCir)
{
		var lvldtl=previousValue;
		var rowcounter="";
		var lvdtl="";
		if(isFromChild==1){
		rowcounter=document.forms[0].rowUpdateCounter.value;
		}else{
		rowcounter=window.opener.document.forms[0].counter.value;
		}
		lvdtl=document.getElementById("txt"+oldcomponentid+rowcounter).value;
		
		var lvldtl=lvldtl.split("~");
		var newlvldtl=lvdtl.split("~");
		for(k=0;k<leaveArr_Loc.length;k++){
	
			if(leaveArr_Loc[k]!=lvldtl[0]){
				duplicateLeave=true;
			}
			else if(leaveArr_Loc[k]==lvldtl[0]){
				
				duplicateLeave=false;
				break;
			}
		}
				if(!duplicateLeave){
			var chk=eval(document.getElementById("sr_"+lvldtl[0]+rowcounter).innerHTML)-1;

			if(eval("${isSameLeaveAllowed}")==0){
			for(k=0;k<leaveArr_Loc.length;k++){

					if(k!=chk){
	
				/*put case in below if condition if multiple leaves allowed than put this condition k==window.opener.document.forms[0].counter.value*/
				  if(leaveArr_Loc[k]==newcomponentid/*newlvldtl[0]*/){
				    alert('<fmt:message  bundle="${alertLables}" key="HRMS.SIMILAR_COMBINATIONAL_LEAVE"/>');
				    document.getElementById("txt"+oldcomponentid+rowcounter).value=previousValue;
				    return false;
				  }
				}
			}
}
		}
				var continuationFlag=true;
				if(eval(eval(document.getElementById("sr_"+lvldtl[0]+rowcounter).innerHTML))> 1){
					var tempDate=newlvldtl[1].split("/");
					tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
				}
				else{
					if(counter>2){
						var tempDate=newlvldtl[2].split("/");
						tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
					}
				}

			document.getElementById("txt"+oldcomponentid+rowcounter).setAttribute("id","txt"+newcomponentid+rowcounter);
			document.getElementById("rowchk"+oldcomponentid+rowcounter).setAttribute("cnt",newcomponentid);
			document.getElementById("rowchk"+oldcomponentid+rowcounter).setAttribute("id","rowchk"+newcomponentid+rowcounter);
			document.getElementById("chk"+oldcomponentid+rowcounter).setAttribute("id","chk"+newcomponentid+rowcounter);
			editedObject=document.getElementById("rowchk"+newcomponentid+rowcounter);
			document.getElementById("leavetype_"+oldcomponentid+rowcounter).setAttribute("id","leavetype_"+newcomponentid+rowcounter);
			document.getElementById("fromdate_"+oldcomponentid+rowcounter).setAttribute("id","fromdate_"+newcomponentid+rowcounter);
			document.getElementById("todate_"+oldcomponentid+rowcounter).setAttribute("id","todate_"+newcomponentid+rowcounter);
			document.getElementById("nod_"+oldcomponentid+rowcounter).setAttribute("id","nod_"+newcomponentid+rowcounter);
			document.getElementById("sr_"+oldcomponentid+rowcounter).setAttribute("id","sr_"+newcomponentid+rowcounter);
		//	var lvdtl=document.getElementById("txt"+newcomponentid).value;
			var lvdtl=newcomponentid+"~"+fromdate+"~"+todate+"~"+noofdays+"~"+ordinaryCir;
			document.getElementById("txt"+newcomponentid+rowcounter).value=lvdtl;
			var lvdtlArr=lvdtl.split("~");
			document.getElementById("leavetype_"+newcomponentid+rowcounter).innerHTML=document.frmleaveapply.natureofleave.options[document.frmleaveapply.natureofleave.selectedIndex].innerHTML; 
			document.getElementById("fromdate_"+newcomponentid+rowcounter).innerHTML=lvdtlArr[1];
			document.getElementById("todate_"+newcomponentid+rowcounter).innerHTML=lvdtlArr[2];
			var inputtag=document.getElementById("nod_"+newcomponentid+rowcounter).innerHTML;
			
			var splited=inputtag.split("<");
			document.getElementById("nod_"+newcomponentid+rowcounter).innerHTML=lvdtlArr[3]+"<"+ splited[1];
			for(k=0;k<leaveArr_Loc.length;k++){
				if(leaveArr_Loc[k]==oldcomponentid && k==rowcounter){
					leaveArr_Loc[k]=newcomponentid;
					leaveDataArr[k]=lvdtl;// added for setting the updated value in specified index
				}
			}
		document.forms[0].Delete.disabled=false;
		document.forms[0].natureofleave.value="";
		document.getElementById("updateButton").style.display="none";
		document.forms[0].fromdate.value="";
		document.forms[0].todate.value="";
		document.getElementById("nod").innerHTML="-";
		}
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();

	}
%>





<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions"%>
	<c:set var="LeaveTList" value="${resultValue.leaveTypeList}"> </c:set>
	<c:set var="app_penLst" value="${resultValue.approve_pendingList}"> </c:set>
		<c:set var="holidayList" value="${resultValue.holidayList}"> </c:set>
	<c:set var="LeaveBal" value="${resultValue.leaveBal}"> </c:set>
	<c:set var="ajaxKey" value="${resultValue.ajaxKey}"> </c:set>
	<c:set var="leaveDet" value="${resultValue.leaveDet}"> </c:set>
	<c:set var="gender" value="${resultValue.gender}"> </c:set>
	<c:set var="ruleKey" value="${resultValue.Key}"> </c:set>
	<c:set var="alreadyApplied" value="${resultValue.appliedBefore}"> </c:set>
	<c:set var="previousXML" value="${resultValue.previousXML}"> </c:set>
	<c:set var="selectedIndex" value="${resultValue.selectedIndex}"> </c:set>	
	
<script>
var counter =1;
var leaveArr=new Array();
var leaveArr_disp=new Array();
var toDateArr= new Array();
var fromDateArr= new Array();
var leavetype_bal=new Array();
var combFromArr = new Array();
var combToArr=new Array();
var copyofpreviousXML="";
//document.onclick=checkFirstHalfDay;
//document.onkeydown=checkFirstHalfDay;

var tempSaveTotalDays=0.0;
var editedObject;
var toDate;
var fromDate;
var nod=0; 
var duplicateLeave=false;


function deleteLeave()
{
deleteRow(2,'',alertMsg);

}


var disp_counter=1;
function addRow_display(mode,previousXML) 
{ 

var chkCont_dt = true;

var lvdtl_disp=document.frmleaveapply.leavedtls.value;
var lvdtlArr_disp=lvdtl_disp.split("~");

var Date1=lvdtlArr_disp[1].split("/");
var Date2=lvdtlArr_disp[2].split("/");

Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];



if(lvdtl[3]!=0){
//document.getElementById("lvdtl").style.display="";
var tbody = document.getElementById("displayTable").getElementsByTagName("tbody")[0]; 
if(previousXML==""){
leaveArr_disp=leaveArr;
}

		if(leaveArr_disp.length==1){
			var heading = document.createElement("TR"); 
			var row = document.createElement("TR"); 
			var cell2 = document.createElement("TH"); 
			var cell3 = document.createElement("TH"); 
			var cell4 = document.createElement("TH"); 
			var cell5 = document.createElement("TH"); 
			var cell6 = document.createElement("TH"); 
			var cell7 = document.createElement("TH"); 
			var cell8 = document.createElement("TH"); 
			var cell9 = document.createElement("TH"); 
			

			//cell1.innerHTML="<input type='checkbox'  id='checkAll' name='chk' onchange=\"checkAll();\">";


			cell2.innerHTML="Sr.No.";
			cell2.setAttribute("class","datatableheader"); 					
			cell3.innerHTML="Leave Type";
			cell3.setAttribute("class","datatableheader"); 					
			cell4.innerHTML="From Date";
			cell4.setAttribute("class","datatableheader"); 
			cell5.innerHTML="To Date";
			cell5.setAttribute("class","datatableheader"); 
			cell6.innerHTML="No. Of Days";
			cell6.setAttribute("class","datatableheader"); 
			cell7.innerHTML="Sanction From Date";
			cell7.setAttribute("align","center");
			cell7.setAttribute("class","datatableheader"); 
			cell8.innerHTML="Sanction To Date";
			cell8.setAttribute("class","datatableheader"); 
			cell8.setAttribute("align","center");
			cell9.innerHTML="Sanction Days";
			cell9.setAttribute("class","datatableheader"); 
			cell9.setAttribute("align","center");


			row.appendChild(cell2);
			row.appendChild(cell3);
			row.appendChild(cell4);
			row.appendChild(cell5);
			row.appendChild(cell6);
			row.appendChild(cell7);
			row.appendChild(cell8);
			row.appendChild(cell9);
			row.bgColor='#C9DFFF';
				if(mode==1)
				row.style.display='none';
				else
				row.style.display='';
				tbody.appendChild(heading);
				tbody.appendChild(row);
			 
		}

			row = document.createElement("TR"); 
			var cell2 = document.createElement("TD"); 
			//cell2.setAttribute("id","sr_"+lvdtlArr_disp[0]);
			cell2.innerHTML = disp_counter; 
			cell2.setAttribute("class","tablecelltext"); 					
			var cell3 = document.createElement("TD"); 

			leaveType=lvdtlArr_disp[0].split('_');
			cell3.innerHTML = document.frmleaveapply.natureofleave.options[leaveType[1]].innerHTML; 			
			
			//cell3.setAttribute("id","leavetype_"+lvdtlArr_disp[0]);
			cell3.setAttribute("class","tablecelltext"); 					
			//leaveArr[disp_counter-1]=lvdtlArr_disp[0];
			var cell4 = document.createElement("TD"); 
			cell4.innerHTML = lvdtlArr_disp[1]; 
			
			//cell4.setAttribute("id","fromdate_"+lvdtlArr_disp[0]);
			cell4.setAttribute("class","tablecelltext"); 					
			var cell5 = document.createElement("TD"); 
			cell5.innerHTML = lvdtlArr_disp[2]; 
			//cell5.setAttribute("id","todate_"+lvdtlArr_disp[0]);
			cell5.setAttribute("class","tablecelltext"); 					
			var cell6 = document.createElement("TD"); 
			cell6.innerHTML = lvdtlArr_disp[3];
			
			//cell6.setAttribute("id","nod_"+lvdtlArr_disp[0]);
			cell6.setAttribute("class","tablecelltext"); 					
			var value=lvdtlArr_disp[0]+"~"+lvdtlArr_disp[1]+"~"+lvdtlArr_disp[2]+"~"+lvdtlArr_disp[3];

			cell6.innerHTML =lvdtlArr_disp[3]; 
			
			var cell7 = document.createElement("TD"); 


			cell7.setAttribute("id","sancFrom_"+lvdtlArr_disp[0]);
			cell7.setAttribute("class","tablecelltext"); 					
			cell7.setAttribute("align","center");


			var cell8 = document.createElement("TD"); 
			cell8.setAttribute("id","sancTo_"+lvdtlArr_disp[0]);
			cell8.setAttribute("class","tablecelltext"); 					
			cell8.setAttribute("align","center");






			var cell9 = document.createElement("TD"); 
			cell9.setAttribute("id","sancDays_"+lvdtlArr_disp[0]);
			cell9.setAttribute("class","tablecelltext"); 					
			cell9.setAttribute("align","center");

			if (mode==1){
				tempSaveTotalDays=eval(tempSaveTotalDays)+eval(lvdtlArr_disp[3]);
				row.style.display='none';
			}
	
	else
{
			row.style.display='';
			document.getElementById("totalDays").innerHTML=eval(document.getElementById("totalDays").innerHTML)+eval(lvdtlArr_disp[3]);

}
			row.appendChild(cell2); 
			row.appendChild(cell3); 
			row.appendChild(cell4); 
			row.appendChild(cell5); 
			row.appendChild(cell6)
			row.appendChild(cell7); 
			row.appendChild(cell8); 
			row.appendChild(cell9)
	
			row.setAttribute("id","rowchk"+lvdtlArr_disp[0]);
			//alert("cnt ::"+ lvdtlArr_disp[0]);
			row.setAttribute("cnt",lvdtlArr_disp[0]);
			disp_counter+=1;
			
			if (mode==1){
				row.style.display='none';
			}
			tbody.appendChild(row); 
	}
//alert(row.innerHTML); 
return true;
}





function checkData(leavedetails)
{


var lvldtl=leavedetails.split("~");

for(k=0;k<leaveArr.length;k++)
{
	if(leaveArr[k]!=lvldtl[0]){
	duplicateLeave=true;
    }
	else if(leaveArr[k]==lvldtl[0]){
	duplicateLeave=false;
	break;
    }
}


if(duplicateLeave){
return true;
}else{
		//alert(eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1);
	for(k=0;k<leaveArr.length;k++){
			if(k!=(eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1)){
			if(leaveArr[k]==lvldtl[0]){
				alert('<fmt:message  bundle="${alertLables}" key="HRMS.SIMILAR_COMBINATIONAL_LEAVE"/>');
				return false;
			}
		}
	}
	}
	/*if(counter>1){
	

		var splitedDate=lvldtl[1].split("/");
		splitedDate=splitedDate[1]+"/"+splitedDate[0]+"/"+splitedDate[2];	
	
	alert("TO dATE :" + toDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-2]+ "splitted Date :" +  splitedDate);
	if(eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)==1){
	
	}
	return continuationDate(toDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-2],new Date(splitedDate));
	
	}*/
	}

/*

function continuationDate(todate,nextDate){

var diff=dateDifference(todate,nextDate);
//alert("differnece : " + diff);
	if(diff>0){
	if(diff ==2){
	 toDate=toDate; 
		return true;
		}
	else{
	toDate=todate; 
alert('<fmt:message  bundle="${alertLables}" key="HRMS.CONTINUATION_DATE"/>');
	return false;
	}	
}else{
toDate=todate; 
return false;
}

}*/


function editRow(oldcomponentid,newcomponentid,previousValue)
{
var lvldtl=previousValue;
var lvdtl=document.getElementById("txt"+oldcomponentid).value;

var lvldtl=lvldtl.split("~");
var newlvldtl=lvdtl.split("~");

for(k=0;k<leaveArr.length;k++)
{
	if(leaveArr[k]!=lvldtl[0]){
	duplicateLeave=true;
    }
	else if(leaveArr[k]==lvldtl[0]){
	duplicateLeave=false;
	break;
    }
}


if(duplicateLeave==false){
		var chk=eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1;

	for(k=0;k<leaveArr.length;k++){

			if(k!=chk){

			if(leaveArr[k]==newlvldtl[0]){
				alert('<fmt:message  bundle="${alertLables}" key="HRMS.SIMILAR_COMBINATIONAL_LEAVE"/>');
				document.getElementById("txt"+oldcomponentid).value=previousValue;
				return false;
			}
		}
	}

}



var continuationFlag=true;
if(eval(eval(document.getElementById("sr_"+lvldtl[0]).innerHTML))> 1){
var tempDate=newlvldtl[1].split("/");
tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
//continuationFlag=continuationDate(toDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-2],new Date(tempDate));	
}
else{
if(counter>2){
var tempDate=newlvldtl[2].split("/");
tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
//continuationFlag=continuationDate(new Date(tempDate),fromDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)]);	
}
}

//if(continuationFlag){
var previousDays=lvldtl[3];
document.getElementById("txt"+oldcomponentid).setAttribute("id","txt"+newcomponentid);
document.getElementById("rowchk"+oldcomponentid).setAttribute("cnt",newcomponentid);
document.getElementById("rowchk"+oldcomponentid).setAttribute("id","rowchk"+newcomponentid);
document.getElementById("chk"+oldcomponentid).setAttribute("id","chk"+newcomponentid);
editedObject=document.getElementById("rowchk"+newcomponentid);

document.getElementById("leavetype_"+oldcomponentid).setAttribute("id","leavetype_"+newcomponentid);
document.getElementById("fromdate_"+oldcomponentid).setAttribute("id","fromdate_"+newcomponentid);
document.getElementById("todate_"+oldcomponentid).setAttribute("id","todate_"+newcomponentid);
document.getElementById("nod_"+oldcomponentid).setAttribute("id","nod_"+newcomponentid);
document.getElementById("sr_"+oldcomponentid).setAttribute("id","sr_"+newcomponentid);
var lvdtl=document.getElementById("txt"+newcomponentid).value;
var lvdtlArr=lvdtl.split("~");
leaveType=lvdtlArr[0].split('_');
document.getElementById("leavetype_"+newcomponentid).innerHTML=document.frmleaveapply.natureofleave.options[leaveType[1]].innerHTML; 
document.getElementById("fromdate_"+newcomponentid).innerHTML=lvdtlArr[1];
document.getElementById("todate_"+newcomponentid).innerHTML=lvdtlArr[2];

var inputtag=document.getElementById("nod_"+newcomponentid).innerHTML;

document.getElementById("totalDays").innerHTML=eval(document.getElementById("totalDays").innerHTML)-eval(previousDays)+eval(lvdtlArr[3]);
var splited=inputtag.split("<");
//var substring = inputtag.substring(inputtag.indexOf(document.getElementById("nod_"+newcomponentid).innerText)+1,inputtag.length);
//alert(document.getElementById("nod_"+newcomponentid).innerText);
//alert(inputtag.indexOf(document.getElementById("nod_"+newcomponentid).innerText));
document.getElementById("nod_"+newcomponentid).innerHTML=lvdtlArr[3]+"<"+ splited[1];




for(k=0;k<leaveArr.length;k++){
if(leaveArr[k]==oldcomponentid){
leaveArr[k]=newcomponentid;
}
}
return true;
/* work here for updating the innerHTML of tds*/

/*}else{
document.getElementById("txt"+oldcomponentid).value=previousValue;
return false;
}

*/
}

			

			function calc_diff(){
				var S1 = document.frmleaveapply.fromdate.value;
				var S2 = document.frmleaveapply.todate.value;
				 nod=datediff(S1,S2);

					var flag=0;
				if(document.frmleaveapply.natureofleave.value=="1"){
					if(eval(nod)>6){
						alert("Casual Leaves cannot be applied for more than 6 days at a time");
						document.frmleaveapply.todate.value="";
						document.frmleaveapply.focus();
						flag=1;
					}
				
				
				}
				if(flag==0)
				{
				document.getElementById("tag").style.display="";
				document.getElementById("nod").style.display="";
				document.getElementById("nod").innerHTML="";
				document.getElementById("nod").innerHTML=nod;
				document.frmleaveapply.noofdays.value=nod;
				}


/*S1="07/24/2007";
S2="07/29/2007";
*/
var Date1=S1.split("/");
var Date2=S2.split("/");
Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];

//weekdaysBetween(new Date(Date1),new Date(Date2));

return true;
}


 

var win;
function checkHalfDay()
{
 var nod=0;
 var  natureofleave=document.frmleaveapply.natureofleave.value.split('_');
	if(document.frmleaveapply.fromdate.value!="" &&  document.frmleaveapply.todate.value!=""){
			if(natureofleave[0]=='1'){
				nod=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply,alertMsgDates);
				if(document.getElementById('halfdayYes').status==true){
						document.getElementById("halfday").style.display="";
						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";
				}
				else{
				document.getElementById("tag").style.display="";
				document.getElementById("nod").style.display="";
				document.getElementById("nod").innerHTML="";
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("nod").innerHTML=eval(nod);
				document.frmleaveapply.noofdays.value=eval(nod);				
				}
				document.getElementById("halfday").style.display="none";
				}
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("nod").innerHTML=eval(nod);
				document.frmleaveapply.noofdays.value=eval(nod);
				}

			
			}		
		else if(natureofleave[0]=='2')
			{
			nod=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply,alertMsgDates);
							document.getElementById("nod").innerHTML="";
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("tag").style.display="";
				document.getElementById("nod").innerHTML="";
				
				document.getElementById("nod").innerHTML=eval(nod);
				document.frmleaveapply.noofdays.value=eval(nod);
				}
			
			}
			else{
				var Date1=document.frmleaveapply.fromdate.value.split("/");
				var Date2=document.frmleaveapply.todate.value.split("/");
				Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
				Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
				nod=dateDifference(new Date(Date1),new Date(Date2),alertMsgDates);
				//document.getElementById("halfday").style.display="none";
				document.getElementById("tag").style.display="";
				document.getElementById("nod").style.display="";
				document.getElementById("nod").innerHTML="";
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("nod").innerHTML=eval(nod);
				document.frmleaveapply.noofdays.value=eval(nod);				
				}

			}
	

	if(eval(nod) <=eval(0)){
	 document.forms[0].Submit.disabled=false;
	return false;
	}
	else{
	return true;
	}
}
}



			function applyLeaveDetail()
			{	
				if(counter>1)
			{				document.getElementById('checkAll').status=false;
				checkAll();
				}				///document.getElementById("applLeaveDtl").style.display="";
				
				///document.getElementById("applLeaveDtl").style.display="";
				
				//action="hdiits.htm?actionFlag=insertLeave"
				win=window.open("hdiits.htm?actionFlag=viewLeaveTypes&flag=0","LeaveTypes","width=750,height=400,status=yes,resizable=yes,top=100,left=100");
				//document.forms[0].action="hdiits.htm?actionFlag=viewLeaveTypes&flag=0";	
				//document.forms[0].target="_blank";
				//document.forms[0].submit();
			}

			function showbutton(component,flag){
			var appliedBetween_index=document.forms[0].appliedbetween.selectedIndex;
			if(flag==1){
				if(component.value=='1'){
					if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.LEAVEDETAIL_REMOVED"/>')){
						document.getElementById("leavedetail").style.display="";
						document.getElementById("natureofleave").style.display="none";
						document.getElementById("fromdate").style.display="none";
						document.getElementById("ordinarycir").style.display="none";
						var erase="";
							document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
						/*var tbody = document.getElementById("displayTable").getElementsByTagName("tbody")[0]; 
						tbody.innerText="";*/
						
											
											document.getElementById("yes1").style.display="none";
											document.getElementById("yes2").style.display="none";
											document.getElementById("tag").style.display="none";
											document.getElementById("nod").innerHTML="";
											document.getElementById("nod").style.display="none";

											document.forms[0].reset();
											component.status=true;
											document.forms[0].appliedbetween.options[appliedBetween_index].selected=true;
												
												
					}
				}
				else{
					if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.LEAVEDETAIL_REMOVED"/>')){
							document.getElementById("natureofleave").style.display="";
							document.getElementById("fromdate").style.display="";
							document.getElementById("leavedetail").style.display="none";
							document.getElementById("lvdtl").style.display="none";
							var erase="";
							document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
							leaveArr=null;
							leaveArr=new Array();
							/*var tbody = document.getElementById("displayTable").getElementsByTagName("tbody")[0]; 
							tbody.innerText="";*/		
							document.getElementById("yes1").style.display="none";
							document.getElementById("yes2").style.display="none";
							document.getElementById("tag").style.display="none";
							document.getElementById("nod").innerHTML="";
							document.getElementById("nod").style.display="none";
							document.forms[0].reset();
							component.status=true;
							document.forms[0].appliedbetween.options[appliedBetween_index].selected=true;
					}
				}
			}
		else{
					if(component.value=='1'){
						document.getElementById("leavedetail").style.display="";
						document.getElementById("natureofleave").style.display="none";
						document.getElementById("fromdate").style.display="none";
						document.getElementById("ordinarycir").style.display="none";
					}
				else{
						document.getElementById("natureofleave").style.display="";
						document.getElementById("fromdate").style.display="";
						document.getElementById("leavedetail").style.display="none";
						document.getElementById("lvdtl").style.display="none";
						var erase="";
						document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
						leaveArr=null;
						leaveArr=new Array();
				}
				
				
				}
	}
			function showPrefixDate(l)
			{	
				var id = l.value;
				if(id =='1')
				{
		if(document.frmleaveapply.halfday[0].status && document.frmleaveapply.before2[0].status){
		document.frmleaveapply.prefix[1].status=true;
		alert("in case of half day at after 2 p.m. prefix is not allowed");
		document.getElementById('yes1').style.display='none';					
	}else{
		document.getElementById('yes1').style.display='';					
	}
	
					
		
					
//					document.getElementById('no1').style.display='none';
				}
				else if(id == '0')
				{
				
					document.getElementById('yes1').style.display='none';
					document.frmleaveapply.prefixfromdate.value='';
					document.frmleaveapply.prefixtodate.value='';
	//				document.getElementById('no1').style.display='';
				}

			}
			function showSuffixDate(l)
			{	
				var id = l.value;
				if(id == '1')
				{

			if(document.frmleaveapply.halfday[0].status && document.frmleaveapply.before2[1].status){
					document.frmleaveapply.suffix[1].status=true;
					alert("in case of half day at before 2 p.m. suffix is not allowed");
					document.getElementById('yes2').style.display='none';
			}else{
					document.getElementById('yes2').style.display='';	
			}


		//			document.getElementById('no2').style.display='none';
				}
				else if(id == '0')
				{
					document.getElementById('yes2').style.display='none';
			//		document.getElementById('no2').style.display='';
				}
			}
							
					
function temprorySave()
{
				document.frmleaveapply.noofdays.value="";

			/*var nod;
			var Date1=document.frmleaveapply.fromdate.value.split("/");
			var Date2=document.frmleaveapply.todate.value.split("/");
			Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
			Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
			nod=dateDifference(new Date(Date1),new Date(Date2));


			document.frmleaveapply.noofdays.value=eval(nod);
			document.getElementById("tag").style.display="";
			document.getElementById("nod").style.display="";
			document.getElementById("nod").innerHTML="";
			document.getElementById("nod").innerHTML=eval(nod);*/
			document.frmleaveapply.action="hdiits.htm?actionFlag=insertLeave";
	
			

//			document.frmleaveapply.submit();

if(counter==1 && document.forms[0].combinationleave[0].status)
			{
			alert("Select  leaves for combinational Leave");
			}	
			else{
			
		    if(validateBalance(leavetype_bal,document.frmleaveapply,'<fmt:message  bundle="${alertLables}" key="HRMS.AVAILABLE_BALANCE"/>',1)){
				if(validateForm_frmleaveapply(),alertMsg){
			if(validateForm(document.forms[0],alertMsgDates,requiredMsg,components,'${holidayList}')){
						    returnFromDateArr();
							if(validateDates(combFromArr,combToArr,counter,'<fmt:message  bundle="${alertLables}" key="HRMS.CONTINUATION_DATE"/>',alertMsgDates)){
				if(checkFirst_LastDayCondition(alertMsg[3])){
								if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.SUBMITCONFIRMATION"/>'))
											{
												document.frmleaveapply.submit();
											}
						}
						}
							}				
							
					}
			
			}
}
}





							
							

</script>		


			



  
<hr>
<table id="applLeaveDtl" border=0 >
<tr>
	<td><hdiits:caption captionid="HRMS.NatureOfLeave" bundle="${leaveCaption}"/></td>
	<td>


<hdiits:select sort="false"  name="nol" tabindex="1" size="1" caption="drop_down"  onchange="checkHalfDay(this);">
               <hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="leavetypes" items="${LeaveTList}">
				<hdiits:option value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}">
				${leavetypes.leaveTypeName}
				</hdiits:option>
				</c:forEach>
	        </hdiits:select>
	</td>
</tr>
</table>


<!-- ******************** -->

<div id="leave" name="leave" >	
<table  class="tabtable" border=0>
<tr id="messages" style="display:none">
<td colspan=5>
<span id="alertMsg">
<br/>
<br/>
<br/>
<c:if test="${alreadyApplied==true}">
<font color="red"><b><fmt:message  bundle="${alertLables}" key="HRMS.alreadyApplied"/></b></font>
<script>
document.getElementById("messages").style.display="";
</script>
</c:if>
</span>

</td>
</tr>
<br>
<tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="6">
<font color="#ffffff">
<strong><u><fmt:message key="HRMS.Balanceavailable"/></u></strong>
		</font>
		</td>
		</tr>

<tr>
	<td><b><fmt:message key="HRMS.CL"/></td>
	<td><b><fmt:message key="HRMS.RH"/></td>
	<td><b><fmt:message key="HRMS.EL"/></td>
	<td><b><fmt:message key="HRMS.HPL"/></td>
</tr>
<tr>
	<c:set var="counter" value="0"/>
	<c:forEach var="leaveBal" items="${LeaveBal}">
	
	<c:if test="${counter<4}">
	<script>
		leavetype_bal["${counter}"]=${leaveBal.availableBal};
	</script>
	<td><c:out value="${leaveBal.availableBal}"/></td>	
	</c:if>
	<c:set var="counter" value="${counter+1}"/>
	</c:forEach>


</tr><br>
	<br>
<tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="5">
<font color="#ffffff">
<strong><u><fmt:message key="HRMS.leave_entry_detail1"/></u></strong>
		</font>
		</td>
		</tr>
		<tr>
	<td colspan="5">
	
	<table border=0 width="100%"> 
	<tr>
	<td width="20%">
    <hdiits:caption captionid="HRMS.appliedbetween1" bundle="${leaveCaption}"/>
</td>
<td>
&nbsp;
</td>
    <td id="sanction_days1" style="display:none">
    <hdiits:caption captionid="HRMS.sanc_days1" bundle="${leaveCaption}"/>
    </td>
    <td  id="sanction_days" style="display:none">

    </td>
	</tr>
	<tr id="leavetype" style="display:none">
	<td>
	<hdiits:caption captionid="HRMS.NatureOfLeave" bundle="${leaveCaption}"/>
	</td>
	<td id="leavetype_lbl"></td>
	<td><hdiits:caption captionid="HRMS.NoD1" bundle="${leaveCaption}"/>
	</td>
	<td id="noofdays_lbl">

	</td>
	</tr>
	
	
	 <tr id="sanction_TR" style="display:none">
 <td id="sanctionFromDate">
 <hdiits:caption captionid="HRMS.sanc_fromdate1" bundle="${leaveCaption}"/>
  
  </td>
  <td id="sanc_fromdate">

  </td>
    <td>
	<hdiits:caption captionid="HRMS.sanc_todate1" bundle="${leaveCaption}"/>

  </td>
<td id="sanc_todate" align="left">

</td>  
	</tr>
	</table>
	</td>
	</tr>	
	<tr>
	<td colspan="4">
	<table id="displayTable"  width="100%"  name="lvdtl" style="border-collapse: collapse; display:none" borderColor="BLACK"  border=1>
<tbody>

</tbody>
<tbody>
</tbody>
</table>
	
	
	</td>
	</tr>
	
	
	
<br>
<tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="5">
<font color="#ffffff">
<strong><u><fmt:message key="HRMS.LeaveEntryForm"/></u></strong>
		</font>
		</td>
		</tr>

<tr>
<td><hdiits:caption captionid="HRMS.comb_leave" bundle="${leaveCaption}"/></td>
<td>
<hdiits:radio captionid="HRMS.Yes" name="combinationleave" value="1" mandatory="false"  onclick="showbutton(this,1);" tabindex="1"/><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
<hdiits:radio captionid="HRMS.No" name="combinationleave" value="0" mandatory="false" default="0"  onclick="showbutton(this,1);" tabindex="2"/><hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>

</td>
<td align="left" id="leavedetail" style="display:none">
<hdiits:button type="button" name="leaveDetail"   captionid="HRMS.EnterLeaveDetail1"  bundle="${LeaveCaption}" onclick="return applyLeaveDetail();" tabindex="3"/>
</td>
<td colspan="3">
<!-- <a href="#" onclick="viewSubmittedReq();">View Submitted Request</a> -->
</td>


</tr>



<tr id="fromdate">
	<td width="20%"><hdiits:caption captionid="HRMS.FromDate" bundle="${leaveCaption}"/>(DD/MM/YYYY)</td>
	<td width="42%"><hdiits:dateTime  name="fromdate" caption="dateTime" mandatory="true" afterDateSelect="checkHalfDay();" onblur="checkHalfDay();" ></hdiits:dateTime></td>
	<td><hdiits:caption captionid="HRMS.ToDate" bundle="${leaveCaption}"/> (DD/MM/YYYY)</td>
	<td><hdiits:dateTime  name="todate" caption="dateTime" mandatory="true"  afterDateSelect="checkHalfDay();" onblur="checkHalfDay();" /></td>
</tr>
<tr>
<td ><hdiits:caption captionid="HRMS.ContactAddress" bundle="${leaveCaption}"/></td>
	<td><hdiits:textarea mandatory="true" rows="2" cols="50"  
                                    name="contactaddress" tabindex="7" id="c_strNames" 
                                    validation ="txt.isrequired"   caption="contact address" /> 
	</td>
	<td>
	<hdiits:caption captionid="HRMS.Telephone" bundle="${leaveCaption}"/>
	</td>
	<td>
	<hdiits:text mandatory="false"  name="telecode" tabindex="8" id="c_strNames" 
                                    validation ="txt.isnumber"  captionid="HRMS.Tele_code"  bundle="${LeaveCaption}" maxlength="4" size="2"/> - 
	<hdiits:text mandatory="false"  name="telephone" tabindex="8" id="c_strNames" 
                                    validation ="txt.isnumber"  captionid="HRMS.telephone"  bundle="${LeaveCaption}" maxlength="10"/></td>

	
	
</tr>


<tr id="ordinarycir" style="display:none">
<td><hdiits:caption captionid="HRMS.ordcir" bundle="${leaveCaption}"/>
	</td>
	<td align="left" >
	<hdiits:radio caption=""  name="ordcir" value="1" mandatory="false" /><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>&nbsp;&nbsp;&nbsp;
	<hdiits:radio caption="" value="0" name="ordcir"  mandatory="false" default="0"/><hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>&nbsp;&nbsp;</td>
	<td></td>
</tr>

<tr id="halfday" style="display:none">
	<td><hdiits:caption captionid="HRMS.halfday" bundle="${leaveCaption}"/></td>
	<td><hdiits:radio  name="halfday" value="1" id="halfdayYes" mandatory="false" onclick="checkHalfDay();" /><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
	<hdiits:radio name="halfday" value="0"  id="halfdayNo" mandatory="false" default="0" onclick="checkHalfDay();"/><hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
	</td>
		<td id="before2_lbl">
	<hdiits:caption captionid="HRMS.before2" bundle="${leaveCaption}"/></td>
	<td id="before2_radio">
	<hdiits:radio  name="before2" id="before2Yes" bundle="${leaveCaption}"  value="1" mandatory="false" onclick="checkHalfDay();"/><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
	<hdiits:radio  id="before2No"  bundle="${leaveCaption}" name="before2" value="0" mandatory="false" default="0" onclick="checkHalfDay();"/><hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
	</td>
	
</tr>	

			<tr id="First_Day" style="display:none">
				<td id ="firstday_halfday"><b></b></td>
				<td><hdiits:radio bundle="${leaveCaption}" name="firstday" id="firstdayhalfdayYes" value="1" mandatory="false" onclick="checkHalfDay();"/><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
				<hdiits:radio   bundle="${leaveCaption}" name="firstday" id="firstdayhalfdayNo" value="0" mandatory="false" default="0" onclick="checkHalfDay();"/><hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
				</td>
				<td id="before2_radio_firstday">  
				<hdiits:caption captionid="HRMS.before2" bundle="${leaveCaption}"/></td>
			</tr>	


			<tr id="Last_Day" style="display:none">
				<td id ="lastday_halfday"><b></b></td>
				<td>
				<hdiits:radio  bundle="${leaveCaption}" name="lastday" id="lastdayhalfdayYes" value="1" mandatory="false" onclick="checkHalfDay();"/><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
				<hdiits:radio  bundle="${leaveCaption}" name="lastday" id="lastdayhalfdayNo" value="0" mandatory="false" default="0" onclick="checkHalfDay();"/><hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
				</td>
				<td id="before2_radio_lastday">
					<hdiits:caption captionid="HRMS.before2" bundle="${leaveCaption}"/>></td>
			</tr>	


<tr>
	<td><hdiits:caption captionid="HRMS.Prefix" bundle="${leaveCaption}"/>
	</td>
	<td align="left">
	<hdiits:radio caption=""  name="prefix" value="1"  onclick="return showPrefixDate(this);" /><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
	<hdiits:radio caption="" name="prefix" value="0"  default="0" onclick="return showPrefixDate(this);"/><hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
	</td>
	</tr>
<tr name="yes1" id="yes1"  width="100%" border=0  style="display:none">
<td><hdiits:caption captionid="HRMS.PrefixFromDate" bundle="${leaveCaption}"/> (DD/MM/YYYY)</td>
	<td><hdiits:dateTime  name="prefixfromdate" mandatory="true" caption="PrefixFromDate" ></hdiits:dateTime> </td>
	<td><hdiits:caption captionid="HRMS.PrefixToDate" bundle="${leaveCaption}"/>(DD/MM/YYYY)</td>
	<td><hdiits:dateTime  name="prefixtodate"  mandatory="true" caption="PrefixToDate"></hdiits:dateTime> 
	</td>

</tr>
<tr>
	<td><hdiits:caption captionid="HRMS.Suffix" bundle="${leaveCaption}"/></td>
	<td align="left">
	<hdiits:radio caption="" name="suffix" value="1"  onclick="return showSuffixDate(this);"/><hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
	<hdiits:radio caption="" name="suffix" value="0"  onclick="return showSuffixDate(this)" default="0"  />
	<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/></td>
	<td></td>
</tr>

<tr name="yes2" id="yes2" style="display:none">
<td><hdiits:caption captionid="HRMS.SuffixFromDate" bundle="${leaveCaption}"/> (DD/MM/YYYY)</td>
<td><hdiits:dateTime name="suffixfromdate" mandatory="true" caption="SuffixFromDate" ></hdiits:dateTime> </td>
<td><hdiits:caption captionid="HRMS.SuffixToDate" bundle="${leaveCaption}"/>(DD/MM/YYYY)</td>
<td><hdiits:dateTime  name="suffixtodate"  mandatory="true" caption="SuffixToDate" ></hdiits:dateTime> </td>
	
</tr>
<tr>
	<td><hdiits:caption captionid="HRMS.Remarks" bundle="${leaveCaption}"/></td>
	<td><hdiits:textarea mandatory="false" rows="5" cols="30"  
                                    name="joiningRemarks" tabindex="9" id="c_strNames" 
                                    validation ="txt.isrequired"   caption="remarks" /></td>

<td><hdiits:caption captionid="HRMS.LeaveReason" bundle="${leaveCaption}"/></td>
	<td colspan="4"><hdiits:textarea mandatory="true" rows="5" cols="30"  
                                    name="leavereason" tabindex="7" id="c_strNames" 
                                    validation ="txt.isrequired"  caption="leavereason" /></td>	
</tr>


<tr>
<td>
<table class="tabtable" name="no2" id="no2" style="display:none">
<tr><td></td></tr>
</table>

</td>
</tr>


</table>
<table id="lvdtl"  width="100%"  name="lvdtl" style="border-collapse: collapse; display:none" borderColor="BLACK"  border=1>
<tbody>

</tbody>
<tbody>
<tr style="display:none">
<td colspan="5" align="right" style="display:none">
<font color="Red" ><b><hdiits:caption captionid="HRMS.TotalDays" bundle="${leaveCaption}"/></font>
</td>
<td id="totalDays"  style="display:none" >
0
</td>
</tr>
<tr><td align="center" colspan="7">
<hdiits:button type="button" name="edit" caption="Edit" value="Edit" onclick="deleteRow(1,'',alertMsg);"/>
<hdiits:button type="button" name="Delete" value="Delete" caption="Delete" onclick="deleteLeave();"/> 

</td></tr>

</tbody>

</table>

<table border="0" width="100%">
<tbody>
<tr style="display:none">
	<td>For officers of level SP informed the concerned DIG/IG 

	<hdiits:radio caption="" name="informsuperior" value="1"  default="1"  mandatory="false"  />&nbsp;&nbsp;<fmt:message key="HRMS.Yes"/>
	<hdiits:radio caption="" name="informsuperior" value="0" mandatory="false" />&nbsp;&nbsp;<fmt:message key="HRMS.No"/>
	<hdiits:hidden name="noofdays" caption="nod" default="0"></hdiits:hidden> 
    <hdiits:hidden name="tempsave" caption="tempsave" default="0"></hdiits:hidden> 
    <hdiits:hidden name="firstDay_comb" caption="firstDay" />
    <hdiits:hidden name="halfday_comb" caption="halfday" ></hdiits:hidden> 
    <hdiits:hidden name="before2_comb" caption="before2" ></hdiits:hidden> 
    <hdiits:hidden name="ordi_comb" caption="ordinary_comb"></hdiits:hidden> 
	<hdiits:hidden name="parentid" caption="tempsave" default="0"/>
    <hdiits:hidden name="status" caption="status" default="0"></hdiits:hidden> 
    <hdiits:text name="leaveid" caption="leaveid" /> 
    <hdiits:hidden name="ismodified" caption="modified" default="1"></hdiits:hidden> 
    <hdiits:hidden name="srno" caption="srno"></hdiits:hidden> 
    <hdiits:hidden name="selectedIndex" caption="selectedIndex"/>
	</td>
    
			<hdiits:hidden name="leavedtls" caption="a"/>
			

			<td colspan="2">
			&nbsp;
			</td>
</tr>
<tr>

	<td colspan="4">
		<table border='0' width="100%">
			<tr>
				<td>
					<!-- (Example-Scan and Attach Medical Certificate In Case Any) -->
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="LeaveAttachment" />
						<jsp:param name="formName" value="frmleaveapply" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />                
						</jsp:include>
					<!-- For attachment : End-->
				</td>
			</tr>
		</table>
	</td>


</tr>

</tbody>
</table>
</div>
<c:if test="${func:trim(ruleKey) ne ''}"> 
<script>
alertMessages('${ruleKey}');
populateModifyForm(document.forms[0],lineBreak('${leaveDet}'),lineBreak('${previousXML}'),'${selectedIndex}');
</script>


</c:if>



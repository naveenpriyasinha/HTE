
function deleteLeave()
{
//if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.DELETIONCONFIRMATION"/>')){
deleteRow(2,'',alertMsg);
}

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


if(!duplicateLeave){
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
fromDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1]=new Date(tempDate);
tempDate=newlvldtl[2].split("/");
tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
toDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1]=new Date(tempDate);
}
else{
if(counter>2){
var tempDate=newlvldtl[2].split("/");
tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
toDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1]=new Date(tempDate);
var tempDate=newlvldtl[1].split("/");
tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
fromDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1]=new Date(tempDate);
}
}

var previousDays=lvldtl[3];
document.getElementById("txt"+oldcomponentid).setAttribute("id","txt"+newcomponentid+counter);
document.getElementById("rowchk"+oldcomponentid).setAttribute("cnt",newcomponentid);
document.getElementById("rowchk"+oldcomponentid).setAttribute("id","rowchk"+newcomponentid+counter);
document.getElementById("chk"+oldcomponentid).setAttribute("id","chk"+newcomponentid+counter);
editedObject=document.getElementById("rowchk"+newcomponentid);
document.getElementById("leavetype_"+oldcomponentid).setAttribute("id","leavetype_"+newcomponentid+counter);
document.getElementById("fromdate_"+oldcomponentid).setAttribute("id","fromdate_"+newcomponentid+counter);
document.getElementById("todate_"+oldcomponentid).setAttribute("id","todate_"+newcomponentid+counter);
document.getElementById("nod_"+oldcomponentid).setAttribute("id","nod_"+newcomponentid+counter);
document.getElementById("sr_"+oldcomponentid).setAttribute("id","sr_"+newcomponentid+counter);
var lvdtl=document.getElementById("txt"+newcomponentid).value;
var lvdtlArr=lvdtl.split("~");
leaveType=lvdtlArr[0].split('_');
document.getElementById("leavetype_"+newcomponentid).innerHTML=document.forms[0].natureofleave.options[leaveType[1]].innerHTML; 
document.getElementById("fromdate_"+newcomponentid).innerHTML=lvdtlArr[1];
document.getElementById("todate_"+newcomponentid).innerHTML=lvdtlArr[2];
var inputtag=document.getElementById("nod_"+newcomponentid).innerHTML;
document.getElementById("totalDays").innerHTML=eval(document.getElementById("totalDays").innerHTML)-eval(previousDays)+eval(lvdtlArr[3]);
var splited=inputtag.split("<");
document.getElementById("nod_"+newcomponentid).innerHTML=lvdtlArr[3]+"<"+ splited[1];
for(k=0;k<leaveArr.length;k++){
if(leaveArr[k]==oldcomponentid){
leaveArr[k]=newcomponentid;
}
}
return true;
}

var win;



			function applyLeaveDetail()
			{	
			if(counter>1)
			{				
				document.getElementById('checkAll').status=false;
				checkAll();
				}				///document.getElementById("applLeaveDtl").style.display="";
				
				//action="hdiits.htm?actionFlag=insertLeave"
				win=window.open("hdiits.htm?actionFlag=viewLeaveTypes&flag=0","LeaveTypes","width=750,height=300,toolbar=no,status=yes,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,dependent=no");
				//document.forms[0].action="hdiits.htm?actionFlag=viewLeaveTypes&flag=0";	
				//document.forms[0].target="_blank";
				//document.forms[0].submit();
			}

			

			function showPrefixDate(l)
			{	
				var id = l.value;
				if(id =='1')
				{
		if(document.forms[0].halfday[0].status && document.forms[0].before2[0].status){
		document.forms[0].prefix[1].status=true;
		alert(alertMsgDates[15]);
		document.getElementById('yes1').style.display='none';					
	}else{
		document.getElementById('yes1').style.display='';					
	}
	
					
		
					
//					document.getElementById('no1').style.display='none';
				}
				else if(id == '0')
				{
				
					document.getElementById('yes1').style.display='none';
					document.forms[0].prefixfromdate.value='';
					document.forms[0].prefixtodate.value='';
	//				document.getElementById('no1').style.display='';
				}

			}
			function showSuffixDate(l)
			{	
				var id = l.value;
				if(id == '1')
				{

			if(document.forms[0].halfday[0].status && document.forms[0].before2[1].status){
					document.forms[0].suffix[1].status=true;
					alert(alertMsgDates[16]);
					document.getElementById('yes2').style.display='none';
			}else{
					document.getElementById('yes2').style.display='';	
			}


		//			document.getElementById('no2').style.display='none';
				}
				else if(id == '0')
				{
					document.forms[0].suffixfromdate.value='';
					document.getElementById('yes2').style.display='none';
					document.forms[0].suffixtodate.value='';
			//		document.getElementById('no2').style.display='';
				}
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

	function checkTelephoneNo(){
	
		if(document.forms[0].telecode.value.trim()!="" ){
			if(!validate('RNull',"$CPTN" + " " + requiredMsg[0],'telephone',components[8])){ 
				return false;
			}
			else {
			return true;
			}
			
		}
		else{
		return true;
		}
		
	
	}
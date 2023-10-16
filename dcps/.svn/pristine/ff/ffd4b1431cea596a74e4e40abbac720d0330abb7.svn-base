
var counter =1;
var leaveArr=new Array();
var leaveArr_disp=new Array();
var toDateArr= new Array();
var fromDateArr= new Array();
var leavetype_bal=new Array();
var combFromArr = new Array();
var leaveDataArrParent= new Array();
var combToArr=new Array();
var copyofpreviousXML="";
document.onclick=checkFirstHalfDay;
document.onkeydown=checkFirstHalfDay;

var tempSaveTotalDays=0.0;
var editedObject;
var toDate;
var fromDate;
var nod=0; 
var duplicateLeave=false;

/*
function addRow_display(mode,previousXML,cmbLeaveArrP)  is used to display previous
leave data entered by the user in jsp page.
*/
var disp_counter=1;
function addRow_display(mode,previousXML,cmbLeaveArrP) 
{ 
var chkCont_dt = true;
var lvdtl_disp=document.forms[0].leavedtls.value;
var lvdtlArr_disp=lvdtl_disp.split("~");
var Date1=lvdtlArr_disp[1].split("/");
var Date2=lvdtlArr_disp[2].split("/");
Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
if(lvdtl[3]!=0){
var tbody = document.getElementById("displayTable"); 
if(previousXML==""){
leaveArr_disp=cmbLeaveArrP;
}
		if(leaveArr_disp.length==1){
			var heading = document.createElement("TR"); 
			var row1 = document.createElement("TR"); 
			var cell2 = document.createElement("TH"); 
			cell2.setAttribute("align","center");
			var cell3 = document.createElement("TH"); 
			cell3.setAttribute("align","center");
			var cell4 = document.createElement("TH"); 
			cell4.setAttribute("align","center");
			var cell5 = document.createElement("TH"); 
			cell5.setAttribute("align","center");
			var cell6 = document.createElement("TH"); 
			cell6.setAttribute("align","center");
			var cell7 = document.createElement("TH"); 
			cell7.setAttribute("align","center");			
			var cell8 = document.createElement("TH"); 
			cell8.setAttribute("align","center");
			var cell9 = document.createElement("TH"); 
			cell9.setAttribute("align","center");			

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


			row1.appendChild(cell2);
			row1.appendChild(cell3);
			row1.appendChild(cell4);
			row1.appendChild(cell5);
			row1.appendChild(cell6);
			row1.appendChild(cell7);
			row1.appendChild(cell8);
			row1.appendChild(cell9);
			row1.bgColor='#C9DFFF';
				if(mode==1)
				row1.style.display='none';
				else
				row1.style.display='';
				tbody.appendChild(heading);
				tbody.appendChild(row1);
			 
		}

			row1 = document.createElement("TR"); 
			var cell2 = document.createElement("TD"); 
			cell2.innerHTML = disp_counter; 
			cell2.setAttribute("class","tablecelltext"); 
			cell2.setAttribute("align","center");
			var cell3 = document.createElement("TD"); 
			leaveType=lvdtlArr_disp[0].split('_');
			cell3.innerHTML = document.forms[0].natureofleave.options[leaveType[1]].innerHTML; 			
			cell3.setAttribute("class","tablecelltext"); 					
			cell3.setAttribute("align","center");
			var cell4 = document.createElement("TD"); 
			cell4.innerHTML = lvdtlArr_disp[1]; 
			cell4.setAttribute("class","tablecelltext"); 					
			cell4.setAttribute("align","center");
			var cell5 = document.createElement("TD"); 
			cell5.innerHTML = lvdtlArr_disp[2]; 
			cell5.setAttribute("class","tablecelltext"); 					
			cell5.setAttribute("align","center");
			var cell6 = document.createElement("TD"); 
			cell6.innerHTML = lvdtlArr_disp[3];
			cell6.setAttribute("class","tablecelltext"); 					
			cell6.setAttribute("align","center");
			var value=lvdtlArr_disp[0]+"~"+lvdtlArr_disp[1]+"~"+lvdtlArr_disp[2]+"~"+lvdtlArr_disp[3];
			cell6.innerHTML =lvdtlArr_disp[3]; 
			var cell7 = document.createElement("TD"); 
			cell7.setAttribute("id","sancFrom_"+lvdtlArr_disp[0]+eval(disp_counter-1));
			cell7.setAttribute("class","tablecelltext"); 					
			cell7.setAttribute("align","center");
			var cell8 = document.createElement("TD"); 
			cell8.setAttribute("id","sancTo_"+lvdtlArr_disp[0]+eval(disp_counter-1));
			cell8.setAttribute("class","tablecelltext"); 					
			cell8.setAttribute("align","center");
			var cell9 = document.createElement("TD"); 
			cell9.setAttribute("id","sancDays_"+lvdtlArr_disp[0]+eval(disp_counter-1));
			cell9.setAttribute("class","tablecelltext"); 					
			cell9.setAttribute("align","center");
			if (mode==1){
				tempSaveTotalDays=eval(tempSaveTotalDays)+eval(lvdtlArr_disp[3]);
				row1.style.display='none';
			}
	
	else
{
			row1.style.display='';
			document.getElementById("totalDays").innerHTML=eval(document.getElementById("totalDays").innerHTML)+eval(lvdtlArr_disp[3]);

}
			row1.appendChild(cell2); 
			row1.appendChild(cell3); 
			row1.appendChild(cell4); 
			row1.appendChild(cell5); 
			row1.appendChild(cell6)
			row1.appendChild(cell7); 
			row1.appendChild(cell8); 
			row1.appendChild(cell9)

			row1.setAttribute("id","row"+lvdtlArr_disp[0]+eval(disp_counter-1));

			row1.setAttribute("cnt",lvdtlArr_disp[0]);
			disp_counter+=1;
			
			if (mode==1){
				row1.style.display='none';
			}
			tbody.appendChild(row1); 
	}
return true;
}

function addRow(mode,leaveDataArr) 
{ 

		var tbody=document.getElementById("lvdtl").getElementsByTagName("tbody")[0];
		tbody.innerText="";
		document.getElementById("lvdtl").style.display="none";
		var chkCont_dt = true;
				for(z=0;z<leaveDataArr.length;z++){
				var lvdtl=leaveDataArr[z];
				leaveDataArrParent[z]=lvdtl;
				var lvdtlArr=lvdtl.split("~");
				var Date1=lvdtlArr[1].split("/");
				var Date2=lvdtlArr[2].split("/");
				Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
				Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
			if(z>1){
					toDate=new Date(Date2);
					toDateArr[eval(z-1)]=toDate;
					fromDate=new Date(Date1);
					fromDateArr[eval(z-1)]=fromDate;
			}
				else if(eval(z)==1){
				toDate=new Date(Date2);
				toDateArr[eval(z-1)]=toDate;
				fromDate=new Date(Date1);
				fromDateArr[eval(z-1)]=fromDate;
				}
if(lvdtlArr[3]!=0){
document.getElementById("lvdtl").style.display="";
var tbody = document.getElementById("lvdtl").getElementsByTagName("tbody")[0]; 
		if(eval(z)==eval(0)){		
		
			generateTableHeader(0,tbody,true);// common function to create table header
			
			 
		}
			row = document.createElement("TR"); 
			cell1 = document.createElement("TD"); 
			var inp1 =  document.createElement("INPUT"); 
			inp1.setAttribute("type","checkbox"); 
			inp1.setAttribute("id","chk"+lvdtlArr[0]+z); 
			inp1.setAttribute("counter",z);
			inp1.setAttribute("name","cmb_leave"); 
			inp1.setAttribute("onclick",function (){changeStatusOfParentChkbox(this)}); 
			cell1.appendChild(inp1); 
			cell1.setAttribute("align","center");
			var cell2 = document.createElement("TD"); 
			cell2.setAttribute("id","sr_"+lvdtlArr[0]+z);
			cell2.innerHTML = eval(z+1); 
			cell2.setAttribute("class","tablecelltext"); 					
			cell2.setAttribute("align","center");
			var cell3 = document.createElement("TD"); 
			var	leaveType=lvdtlArr[0].split('_');
			cell3.innerHTML = document.forms[0].natureofleave.options[leaveType[1]].innerHTML; 
			cell3.setAttribute("id","leavetype_"+lvdtlArr[0]+z);
			cell3.setAttribute("class","tablecelltext"); 					
			cell3.setAttribute("align","center");	
			leaveArr[eval(z)]=lvdtlArr[0];
			var cell4 = document.createElement("TD"); 
			cell4.innerHTML = lvdtlArr[1]; 
			cell4.setAttribute("id","fromdate_"+lvdtlArr[0]+z);
			cell4.setAttribute("class","tablecelltext"); 					
			cell4.setAttribute("align","center");
			var cell5 = document.createElement("TD"); 
			cell5.innerHTML = lvdtlArr[2]; 
			cell5.setAttribute("id","todate_"+lvdtlArr[0]+z);
			cell5.setAttribute("class","tablecelltext"); 					
			cell5.setAttribute("align","center");
			var cell6 = document.createElement("TD"); 
			cell6.innerHTML = lvdtlArr[3];
			cell6.setAttribute("id","nod_"+lvdtlArr[0]+z);
			cell6.setAttribute("class","tablecelltext"); 					
			cell6.setAttribute("align","center");
			var value=lvdtlArr[0]+"~"+lvdtlArr[1]+"~"+lvdtlArr[2]+"~"+lvdtlArr[3];
			cell6.innerHTML =lvdtlArr[3] + "<input type='hidden' name='combLeave' id='txt"+lvdtlArr[0]+z+"'  value='"+value+"'\>"
			row.appendChild(cell1); 
			row.appendChild(cell2); 
			row.appendChild(cell3); 
			row.appendChild(cell4); 
			row.appendChild(cell5); 
			row.appendChild(cell6)
			row.setAttribute("id","rowchk"+lvdtlArr[0]+z);
			row.setAttribute("cnt",lvdtlArr[0]);
			row.setAttribute("counter",z);
			counter=eval(z+1);
			if (mode==1){
				//row.style.display='none';
			}
			else
				row.style.display='';
				tbody.appendChild(row); 
	}
}
return true;

}//end of function addRow()






			function applyLeaveDetail()
			{	
				if(counter>1)
			{				document.getElementById('checkAll').status=false;
				checkAll();
				}				///document.getElementById("applLeaveDtl").style.display="";
				
				///document.getElementById("applLeaveDtl").style.display="";
				//action="hdiits.htm?actionFlag=insertLeave"
				win=window.open("hdiits.htm?actionFlag=viewLeaveTypes&flag=0","LeaveTypes","width=750,height=300,status=yes,scrollbars=yes,resizable=yes,top=100,left=100");
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

			if(document.forms[0].halfday[0].status && document.forms[0].before2

[1].status){
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
					document.getElementById('yes2').style.display='none';
			//		document.getElementById('no2').style.display='';
				}
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
	for(k=0;k<leaveArr.length;k++){
			if(k!=(eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1)){
			if(leaveArr[k]==lvldtl[0]){
				alert(alertMsgDates[17]);
				return false;
			}
		}
	}
	}
	
	}
	

 

var win;


			
					
function submitData()
{
				document.forms[0].noofdays.value="";

			
if(counter==1 && document.forms[0].combinationleave[0].status)
			{
			alert(alertMsgDates[18]);
			}	
			else{
			
				var mainPgFieldArray= new Array('appliedbetween');
					statusMainPgValidation =  validateSpecificFormFields(mainPgFieldArray);
								if(!statusMainPgValidation)
								{
								return;
								}
			 if(validateForm(document.forms[0],alertMsgDates,requiredMsg,components,'${holidayList}')){
				if(validateForm_frmleaveapply()){
		    if(validateBalance(leavetype_bal,document.forms[0],alertMsgDates[19],1)){
						    returnFromDateArr();
							combFromArr.sort(dmyOrdA); // sort Array in ascending order
							combToArr.sort(dmyOrdA); // Sort Array in ascending  order
							if(validateDates(combFromArr,combToArr,counter,alertMsgDates[20],alertMsgDates)){
				if(checkFirst_LastDayCondition(alertMsg[3])){
							
							statusMainPgValidation =  checkTelephoneNo();
										if(!statusMainPgValidation){
										return;
										}		
								if(confirm(alertMsgDates[21]))
											{
												document.forms[0].submit();
											}
						}
						}
							}				
							
					}
			
			}
}
}

function showbutton(component,flag){
			var appliedBetween_index=document.forms[0].appliedbetween.selectedIndex;
			
			if(flag==1){ // to trace if from page radio is selected value=1
				if(component.value=='1'){
				
				
					if(confirm(alertMsg[4])){

						document.getElementById("leavedetail").style.display="";
						document.getElementById("natureofleave").style.display="none";
						document.getElementById("fromdate").style.display="none";
						document.getElementById("ordinarycir").style.display="none";
						var erase="";
							document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
						
											
											document.getElementById("yes1").style.display="none";
											document.getElementById("yes2").style.display="none";
											document.getElementById("tag").style.display="none";
											document.getElementById("nod").innerHTML="";
											document.getElementById("nod").style.display="none";
											document.forms[0].reset();
											component.status=true;
											document.forms[0].appliedbetween.options[appliedBetween_index].selected=true;
											document.forms[0].parentid.value=document.forms[0].appliedbetween.options[appliedBetween_index].value;
												
					}
					
					else{
						
							document.forms[0].combinationleave[1].status=true;
					}
					
				}
				else{
					if(confirm(alertMsg[4])){
							document.getElementById("natureofleave").style.display="";
							document.getElementById("fromdate").style.display="";
							document.getElementById("leavedetail").style.display="none";
							document.getElementById("lvdtl").style.display="none";
							var erase="";
							document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
							leaveArr=null;
							leaveArr=new Array();
								
							document.getElementById("yes1").style.display="none";
							document.getElementById("yes2").style.display="none";
							document.getElementById("tag").style.display="none";
							document.getElementById("nod").innerHTML="";
							document.getElementById("nod").style.display="none";
							document.forms[0].reset();
							component.status=true;
							document.forms[0].appliedbetween.options[appliedBetween_index].selected=true;
							document.forms[0].parentid.value=document.forms[0].appliedbetween.options[appliedBetween_index].value;
					}
					
						else{
						
							document.forms[0].combinationleave[0].status=true;
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

/*
used in leavetypes.jsp and cancelleave.jsp

*/



function addRow(showSanctionCol) 
{ 
var chkCont_dt;
var lvdtl=document.forms[0].leavedtls.value;
var lvdtlArr=lvdtl.split("~");

var Date1=lvdtlArr[1].split("/");
var Date2=lvdtlArr[2].split("/");
Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
if(counter>1){
toDate=new Date(Date2);
toDateArr[counter-1]=toDate;
fromDate=new Date(Date1);
fromDateArr[counter-1]=fromDate;
toDateArr[counter-1]=toDate;
fromDateArr[counter-1]=fromDate;
}
else if(counter==1){
toDate=new Date(Date2);
toDateArr[counter-1]=toDate;
fromDate=new Date(Date1);
fromDateArr[counter-1]=fromDate;
}




if(lvdtl[3]!=0){

/*for(var k=0;k<leaveArr.length;k++)
{
	if(leaveArr[k]==lvdtlArr[0]){
	
	alert('<fmt:message  bundle="${alertLables}" key="HRMS.SIMILAR_COMBINATIONAL_LEAVE"/>');
	return ;
    }
}
*/
document.getElementById("lvdtl").style.display="";
var tbody = document.getElementById("lvdtl").getElementsByTagName("tbody")[0]; 
		if(leaveArr.length<1){

			generateTableHeader(1,tbody,false);// common function to create table header
		}

			row = document.createElement("TR"); 

			var cell2 = document.createElement("TD"); 
			cell2.setAttribute("id","sr_"+lvdtlArr[0]);
			cell2.innerHTML = counter; 
			cell2.setAttribute("class","tablecelltext"); 					
			cell2.setAttribute("align","center"); 					
			var cell3 = document.createElement("TD"); 

			lvType=lvdtlArr[0].split('_');

			selectedIndexOfCombo(document.forms[0].natureofleave,lvdtlArr[0]);
			cell3.innerHTML = document.forms[0].natureofleave.options[document.forms[0].natureofleave.selectedIndex].innerHTML; 			
			cell3.setAttribute("id","leavetype_"+lvdtlArr[0]);
			cell3.setAttribute("class","tablecelltext"); 					
			cell3.setAttribute("align","center"); 					
			leaveArr[counter-1]=lvdtlArr[0];
			var cell4 = document.createElement("TD"); 
			cell4.innerHTML = lvdtlArr[1]; 
			
			cell4.setAttribute("id","fromdate_"+lvdtlArr[0]);
			cell4.setAttribute("class","tablecelltext"); 					
			cell4.setAttribute("align","center"); 					
			var cell5 = document.createElement("TD"); 
			cell5.innerHTML = lvdtlArr[2]; 
			cell5.setAttribute("id","todate_"+lvdtlArr[0]);
			cell5.setAttribute("class","tablecelltext"); 					
			cell5.setAttribute("align","center"); 					
			var cell6 = document.createElement("TD"); 
			cell6.innerHTML = lvdtlArr[3];
			
			cell6.setAttribute("id","nod_"+lvdtlArr[0]);
			cell6.setAttribute("class","tablecelltext"); 	
			cell6.setAttribute("align","center"); 									
			var value=lvdtlArr[0]+"~"+lvdtlArr[1]+"~"+lvdtlArr[2]+"~"+lvdtlArr[3];

			cell6.innerHTML =lvdtlArr[3] + "<input type='hidden' name='combLeave' id='txt"+lvdtlArr[0]+"'  value='"+value+"'\>"
			

			row.appendChild(cell2); 
			row.appendChild(cell3); 
			row.appendChild(cell4); 
			row.appendChild(cell5); 
			row.appendChild(cell6)




		if(showSanctionCol==1){
			var cell7 = document.createElement("TD"); 
			cell7.setAttribute("id","sancFrom_"+lvdtlArr[0]+eval(counter-1));
			cell7.setAttribute("class","tablecelltext"); 					
			cell7.setAttribute("align","center");
			var cell8 = document.createElement("TD"); 
			cell8.setAttribute("id","sancTo_"+lvdtlArr[0]+eval(counter-1));
			cell8.setAttribute("class","tablecelltext"); 					
			cell8.setAttribute("align","center");
			var cell9 = document.createElement("TD"); 
			cell9.setAttribute("id","sancDays_"+lvdtlArr[0]+eval(counter-1));
			cell9.setAttribute("class","tablecelltext"); 					
			cell9.setAttribute("align","center");
			row.appendChild(cell7); 
			row.appendChild(cell8); 
			row.appendChild(cell9)
	}
			row.setAttribute("id","rowchk"+lvdtlArr[0]+eval(counter-1));
			row.setAttribute("cnt",lvdtlArr[0]);
			counter+=1;
			tbody.appendChild(row); 
	}


}







function redisplayAddRow(mode,leaveDataArr) 
{ 
		var tbody=document.getElementById("lvdtl").getElementsByTagName("tbody")[0];
		tbody.innerText="";
		document.getElementById("lvdtl").style.display="none";
			for(z=0;z<leaveDataArr.length;z++){
				document.getElementById("lvdtl").style.display="";
			var tbody = document.getElementById("lvdtl").getElementsByTagName("tbody")[0]; 
				var lvdtl=leaveDataArr[z];
				var lvdtlArr=lvdtl.split("~");

		if(eval(z)==eval(0)){
		generateTableHeader(0,tbody,true); 
		}
			row = document.createElement("TR"); 
			cell1 = document.createElement("TD"); 
			cell1.setAttribute("align","center"); 
			var inp1 =  document.createElement("INPUT"); 
			inp1.setAttribute("type","checkbox"); 
			inp1.setAttribute("id","chk"+lvdtlArr[0]+z); 
			inp1.setAttribute("name","cmb_leave"); 
			inp1.setAttribute("onclick",function (){changeStatusOfParentChkbox(this)}); 
			cell1.appendChild(inp1); 
			var cell2 = document.createElement("TD"); 
			cell2.setAttribute("id","sr_"+lvdtlArr[0]+z);
			cell2.innerHTML = eval(z+1); 
			cell2.setAttribute("class","tablecelltext"); 					
			cell2.setAttribute("align","center");
			var cell3 = document.createElement("TD"); 
			leaveType=lvdtlArr[0].split('_');
			selectedIndexOfCombo(document.forms[0].nol,lvdtlArr[0]);
			cell3.innerHTML = document.forms[0].natureofleave.options[document.forms[0].nol.selectedIndex].innerHTML; 
			cell3.setAttribute("id","leavetype_"+lvdtlArr[0]+z);
			cell3.setAttribute("class","tablecelltext"); 					
			cell3.setAttribute("align","center");	
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
			var value=lvdtlArr[0]+"~"+lvdtlArr[1]+"~"+lvdtlArr[2]+"~"+lvdtlArr[3]+"~"+lvdtlArr[4];
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

			}
			else
				row.style.display='';
	
				tbody.appendChild(row); 

	}

}//end of function redisplayAddRow()
		
		
		

function addRow() 
{
				
				var chkCont_dt;
				var lvdtl=document.frmleaveapply.leavedtls.value;
				var lvdtlArr=lvdtl.split("~");
				var Date1=lvdtlArr[1].split("/");
				var Date2=lvdtlArr[2].split("/");
				Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
				Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
			if(leaveDataArr.length>1){
				toDate=new Date(Date2);
				toDateArr[eval(counter-1)]=toDate;
				fromDate=new Date(Date1);
				fromDateArr[eval(counter-1)]=fromDate;
				toDateArr[eval(counter-1)]=toDate;
				fromDateArr[eval(counter-1)]=fromDate;
				startDateArr[eval(counter-1)]=lvdtlArr[1];
				endDateArr[eval(counter-1)]=lvdtlArr[2];

			}
			else if(leaveDataArr.length==1){
				toDate=new Date(Date2);
				toDateArr[eval(counter-1)]=toDate;
				endDateArr[eval(counter-1)]=lvdtlArr[2];
				fromDate=new Date(Date1);
				fromDateArr[eval(counter-1)]=fromDate;
				startDateArr[eval(counter-1)]=lvdtlArr[1];
			}
			if(lvdtl[3]!=0){
					/* check from rule engine that same leave type is allowed in combination leave 
					-	${isSameLeaveAllowed}== 0 than not allowed.
					-	${isSameLeaveAllowed}== 1 than allowed.
					*/		
					
					if(isSameLeaveAllowed==0){			
					for(var k=0;k<leaveArr_Loc.length;k++)
							{
								if(leaveArr_Loc[k]==lvdtlArr[0]){
										alert(alertMsgDates[17]);
										leaveDataArr.splice(leaveDataArr.length-1,1);
											counter=eval(counter)-1;
										return ;
    							}
							}
					}		
								document.getElementById("lvdtl").style.display="";
								var tbody = document.getElementById("lvdtl").getElementsByTagName("tbody")[0]; 

			if(leaveDataArr.length==1){
					
					generateTableHeader(0,tbody,true);
					
		}

			row = document.createElement("TR"); 
			cell1 = document.createElement("TD"); 
			cell1.setAttribute("align","center");
			var inp1 =  document.createElement("INPUT"); 
			inp1.setAttribute("type","checkbox"); 
			inp1.setAttribute("id","chk"+lvdtlArr[0]+eval(counter-1)); 
			inp1.setAttribute("name","cmb_leave"); 
			inp1.setAttribute("onclick",function (){changeStatusOfParentChkbox(this)}); 
			
			cell1.appendChild(inp1); 
			var cell2 = document.createElement("TD"); 
			cell2.setAttribute("id","sr_"+lvdtlArr[0]+eval(counter-1));
			cell2.setAttribute("align","center");
			cell2.innerHTML = counter; 
			cell2.setAttribute("class","tablecelltext"); 					
			var cell3 = document.createElement("TD"); 
			leaveType=lvdtlArr[0].split('_');
			cell3.innerHTML = document.frmleaveapply.natureofleave.options[document.frmleaveapply.natureofleave.selectedIndex].innerHTML; 
			cell3.setAttribute("id","leavetype_"+lvdtlArr[0]+eval(counter-1));
			cell3.setAttribute("class","tablecelltext"); 					
			cell3.setAttribute("align","center");
			leaveArr_Loc[eval(counter-1)]=lvdtlArr[0];
			var cell4 = document.createElement("TD"); 
			cell4.innerHTML = lvdtlArr[1]; 
			
			cell4.setAttribute("id","fromdate_"+lvdtlArr[0]+eval(counter-1));
			cell4.setAttribute("class","tablecelltext"); 					
			cell4.setAttribute("align","center");
			var cell5 = document.createElement("TD"); 
			cell5.innerHTML = lvdtlArr[2]; 
			cell5.setAttribute("id","todate_"+lvdtlArr[0]+eval(counter-1));
			cell5.setAttribute("class","tablecelltext"); 					
			cell5.setAttribute("align","center");
			var cell6 = document.createElement("TD"); 
			cell6.innerHTML = lvdtlArr[3];
			
			cell6.setAttribute("id","nod_"+lvdtlArr[0]+eval(counter-1));
			cell6.setAttribute("class","tablecelltext");
			cell6.setAttribute("align","center");
			var value=lvdtlArr[0]+"~"+lvdtlArr[1]+"~"+lvdtlArr[2]+"~"+lvdtlArr[3]+"~"+lvdtlArr[4];

			cell6.innerHTML =lvdtlArr[3] + "<input type='hidden' name='combLeave' id='txt"+lvdtlArr[0]+eval(counter-1)+"'  value='"+value+"'\>";
			row.appendChild(cell1); 
			row.appendChild(cell2); 
			row.appendChild(cell3); 
			row.appendChild(cell4); 
			row.appendChild(cell5); 
			row.appendChild(cell6)
			row.setAttribute("counter",eval(counter-1));
			row.setAttribute("id","rowchk"+lvdtlArr[0]+eval(counter-1));
			row.setAttribute("cnt",lvdtlArr[0]);
			tbody.appendChild(row); 
	}

tempArr=leaveArr_Loc;
document.forms[0].Delete.disabled=false;
return true;

}//end of function addRow()
		
	
		


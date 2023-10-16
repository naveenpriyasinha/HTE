function showbutton(component,flag){

			if(flag==1){
				if(component.value=='1'){
					if(confirm(alertMsg[4])){
						document.getElementById("leavedetail").style.display="";
						document.getElementById("natureofleave").style.display="none";
						document.getElementById("fromdate").style.display="none";
						document.getElementById("ordinarycir").style.display="none";
				document.getElementById("halfday").style.display="none";
				document.getElementById("halfdayNo").status=true;
				document.getElementById('firstdayhalfdayNo').status=true; 
				document.getElementById('lastdayhalfdayNo').status =true;
				document.getElementById('before2_lbl').style.display="none";
				document.getElementById('before2_radio').style.display="none";
				document.getElementById('First_Day').style.display="none";
				document.getElementById('Last_Day').style.display="none";



						var erase="";
							document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
						//var tbody = document.getElementById("displayTable").getElementsByTagName("tbody")[0]; 
						//tbody.innerText="";		
				document.getElementById("yes1").style.display="none";
				document.getElementById("yes2").style.display="none";
				document.forms[0].reset();
				component.status=true;												
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
							//var tbody = document.getElementById("displayTable").getElementsByTagName("tbody")[0]; 
							//tbody.innerText="";		

							document.getElementById("yes1").style.display="none";
							document.getElementById("yes2").style.display="none";

												document.forms[0].reset();
												component.status=true;
									
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
			generateTableHeader(0,tbody,true);
			 
		}
			row = document.createElement("TR"); 
			cell1 = document.createElement("TD"); 
			var inp1 =  document.createElement("INPUT"); 
			inp1.setAttribute("type","checkbox"); 
			inp1.setAttribute("id","chk"+lvdtlArr[0]+z); 
			inp1.setAttribute("counter",z);
			inp1.setAttribute("name","cmb_leave"); 
			inp1.setAttribute("onclick",function (){changeStatusOfParentChkbox(this)}); 

			cell1.setAttribute("align","center");
			cell1.appendChild(inp1); 
			var cell2 = document.createElement("TD"); 
			cell2.setAttribute("id","sr_"+lvdtlArr[0]+z);
			cell2.setAttribute("align","center");
			cell2.innerHTML = eval(z+1); 
			cell2.setAttribute("class","tablecelltext"); 					
			var cell3 = document.createElement("TD"); 
			var	leaveType=lvdtlArr[0].split('_');
			cell3.setAttribute("align","center");
			cell3.innerHTML = document.forms[0].natureofleave.options[leaveType[1]].innerHTML; 
			cell3.setAttribute("id","leavetype_"+lvdtlArr[0]+z);
			cell3.setAttribute("class","tablecelltext"); 					
			
			leaveArr[eval(z)]=lvdtlArr[0];
			var cell4 = document.createElement("TD"); 
			cell4.setAttribute("align","center");
			cell4.innerHTML = lvdtlArr[1]; 

			cell4.setAttribute("id","fromdate_"+lvdtlArr[0]+z);
			cell4.setAttribute("class","tablecelltext"); 					
			var cell5 = document.createElement("TD"); 
			cell5.setAttribute("align","center");
			cell5.innerHTML = lvdtlArr[2]; 
			cell5.setAttribute("id","todate_"+lvdtlArr[0]+z);
			cell5.setAttribute("class","tablecelltext"); 					
			var cell6 = document.createElement("TD"); 
			cell6.setAttribute("align","center");
			cell6.innerHTML = lvdtlArr[3];
			cell6.setAttribute("id","nod_"+lvdtlArr[0]+z);
			cell6.setAttribute("class","tablecelltext"); 		

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
				//row.style.display='none';
			}
			else
				row.style.display='';
				tbody.appendChild(row); 
				
	}
}
return true;

}//end of function addRow()






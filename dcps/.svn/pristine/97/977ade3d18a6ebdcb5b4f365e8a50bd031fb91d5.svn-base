function populateLeaveData(frm,ajaxKey,labels) 
	{ var re5digit=/.5/ //regular expression defining a 5 digit number
				counter =1;
			   	var xmlDoc=ajaxKey;

//		    	var xmlDOM=getDOMFromXML(xmlDoc);	

				var leaveDetails=xmlDoc.split('$$$prevLeaveDetail$$$');	
		    	var appDesXML = leaveDetails[0].split('$$$ATTACHMENT_XML$$$');
		    	var xmlDOM=getDOMFromXML(lineBreak(appDesXML[0]));	

	    		var leave_other_dtl="";
	    		var leaveDetail="";
	    		var comb_leave="";
				var leave_status="";
		    	leaveDetail = xmlDOM.getElementsByTagName('LEAVE_DETAIL');      // getting an element from XML file				    	
				 comb_leave = xmlDOM.getElementsByTagName('COMB_FLAG');      // getting an element from XML file				    	
				 leave_status = xmlDOM.getElementsByTagName('STATUS');      // getting an element from XML file				    	
				 leave_other_dtl = xmlDOM.getElementsByTagName('LEAVE_OTHER_DTL');

				
				var childs;					
				
					// not a combination leave
					
					
				document.getElementById("natureofleave").style.display="";
				document.getElementById("lvdtl").style.display="none";
				var erase="";
				document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
				leaveArr=null;
				leaveArr=new Array();
				//document.getElementById("appliedBetween").innerText=": "+ DDMMYYYY(leaveDetail[0].childNodes[8].text) +" <fmt:message key='HRMS.Dash'/> " + DDMMYYYY(leaveDetail[0].childNodes[9].text);
					if(leaveDetail[0].childNodes[18].text!="null"){
							
						var phone=leaveDetail[0].childNodes[18].text.split("-");
						if(phone[0]!="xxxx")
						document.getElementById("telephone_txt").innerHTML=": " + phone[0]+"-";
						
						document.getElementById("telephone_txt").innerHTML=document.getElementById("telephone_txt").innerHTML+phone[1];
						document.getElementById("telephone").style.display="";
						
					}
					else{
					document.getElementById("telephone").style.display="none";
					}
					if('${joiningVO.status}'!='0')
					{
					}else{

					}

				
						halfDayFacility(leave_other_dtl);

					for(k=0;k<leave_other_dtl.length;k++)
					{
					if(leave_other_dtl[k].childNodes[5].text.search(re5digit)!=-1 && leave_other_dtl[k].childNodes[8].text=='1'){
								document.getElementById("halfday").style.display="";
		//						alert(leaveDetail[0].childNodes[16].text);

						if(leave_other_dtl[k].childNodes[10].text=="1"){
							document.getElementById("halfday_radio").innerText=":"+' <fmt:message key="HRMS.Yes"/>';
						//document.getElementById("first_second_Day").style.display="";
						if(leave_other_dtl[k].childNodes[11].text=="1"){
							document.getElementById("before_radio").innerText=":"+ ' <fmt:message key="HRMS.Yes"/>';
						}
						else{
							//document.getElementById("before_radio").innerText=":"+ '<fmt:message key="HRMS.No"/>';
						}
					}
					else{
						document.getElementById("halfday_radio").innerText=": "+ '<fmt:message key="HRMS.No"/>';
						document.getElementById("before_radio").innerText=": "+'<fmt:message key="HRMS.No"/>';
						//document.getElementById("halfday").style.display="none";

					}
					}
					else{
					
						document.getElementById("before_radio").innerText=": "+'<fmt:message key="HRMS.No"/>';
						document.getElementById("halfday_radio").innerText=": "+'<fmt:message key="HRMS.No"/>';
						document.getElementById("halfday").style.display="none";
						document.getElementById("halfday").style.display="none";
					}
					
					
					if(leave_other_dtl.length==1 && comb_leave[0].childNodes[0].text==0){
					document.getElementById("natureofleave").style.display="";
					document.getElementById("leaveType").innerHTML=": "+ frm.natureofleave.options[leave_other_dtl[k].childNodes[9].text].innerHTML;
					
					//frm.fromdate.value=DDMMYYYY(leave_other_dtl[k].childNodes[0].text);
					//frm.todate.value=DDMMYYYY(leave_other_dtl[k].childNodes[1].text);
					document.getElementById("tag").style.display="";
					document.getElementById("nod").style.display="";
					document.getElementById("nod").innerHTML="";
					document.getElementById("nod").innerHTML=": "+leave_other_dtl[k].childNodes[5].text;


					//frm.natureofleave.options[leave_other_dtl[k].childNodes[8].text].selected=true;
					document.getElementById("sanction_TR").style.display="";
					document.getElementById("sanction_days").style.display="";
					document.getElementById("sanction_days1").style.display="";
					document.getElementById("sanction_days").innerHTML=": "+leave_other_dtl[k].childNodes[6].text;
					if(leave_other_dtl[k].childNodes[2].text!="null")
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sanc_fromdate"));
					else
						document.getElementById("sanc_fromdate").innerHTML="-";
					
					if(leave_other_dtl[k].childNodes[2].text!="null")
							FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sanc_todate"));
					else
						document.getElementById("sanc_todate").innerHTML="-";
					}else{

						document.getElementById("natureofleave").style.display="none";
					
					// code for combinational leave
					document.getElementById("sanction_TR").style.display="none";
					document.getElementById("sanction_days").style.display="none";
					document.getElementById("sanction_days1").style.display="none";

					var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"_"+leave_other_dtl[k].childNodes[13].text+ "~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
					leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
					frm.leavedtls.value=leave_dtl;
					
					addRow(1);	
					var	str="_";
					componentIds=leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text+str+leave_other_dtl[k].childNodes[13].text+k;		
					
					
					if(leave_other_dtl[k].childNodes[2].text!="-"){
					
					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[2].text,document.getElementById("sancFrom_"+componentIds));
					}else
					{
							
					document.getElementById("sancFrom_"+componentIds).innerHTML='-';
					}
					
					if(leave_other_dtl[k].childNodes[3].text!="-"){

					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[3].text,document.getElementById("sancTo_"+componentIds));

					}else{

					document.getElementById("sancTo_"+componentIds).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[6].text!="-"){
					document.getElementById("sancDays_"+componentIds).innerHTML=leave_other_dtl[k].childNodes[5].text;
					}else
					{
						document.getElementById("sancDays_"+componentIds).innerHTML='-';
					}
					}
					

				}	
				
					populateAttachmentForEditing(appDesXML[1],"LeaveAttachment");


}

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
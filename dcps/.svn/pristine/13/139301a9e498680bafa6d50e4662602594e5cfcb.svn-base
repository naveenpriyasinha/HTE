var re5digit=/.5/ //regular expression defining a 5 digit number

function goToMainPage(frm){
frm.action="hdiits.htm?actionFlag=getHomePage";
frm.method="POST";
frm.submit();
}

function  checkFirstHalfDay(){
//checkHalfDay2();

 var  natureofleave=document.frmleaveapply.natureofleave.value.split('_');
	if(document.frmleaveapply.fromdate.value!="" &&  document.frmleaveapply.todate.value!=""){
			if(natureofleave[0]=='1'){
				var Date1=document.frmleaveapply.fromdate.value.split("/");
				var Date2=document.frmleaveapply.todate.value.split("/");
				Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
				Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
				document.getElementById("halfday").style.display="";
				document.getElementById("tag").style.display="";
				document.getElementById("nod").style.display="";

        	if(document.frmleaveapply.halfday[0].status){

				if(document.frmleaveapply.fromdate.value == document.frmleaveapply.todate.value){
						document.getElementById('before2_lbl').style.display="";
						document.getElementById('before2_radio').style.display="";
						document.getElementById('firstdayhalfdayNo').status=true; 
						document.getElementById('lastdayhalfdayNo').status =true;
						document.getElementById('First_Day').style.display="none";
						document.getElementById('Last_Day').style.display="none";
						document.getElementById("firstday_halfday").innerHTML="";
						document.getElementById("lastday_halfday").innerHTML="";
				}

				else if( document.frmleaveapply.fromdate.value != document.frmleaveapply.todate.value){
				document.getElementById('First_Day').style.display="";
				document.getElementById('Last_Day').style.display="";
				document.getElementById("firstday_halfday").innerHTML="<b>" + labels[8] +" "+ document.forms[0].fromdate.value +"</b>";
				document.getElementById("lastday_halfday").innerHTML="<b>" +labels[8] +" "+ document.forms[0].todate.value +"</b>";
				document.getElementById('before2_lbl').style.display="none";
				document.getElementById('before2_radio').style.display="none";
				document.getElementById('before2No').status=true;
	
				if(document.getElementById('firstdayhalfdayYes').status && document.getElementById('lastdayhalfdayYes').status && document.getElementById('halfdayYes').status){
					document.getElementById('before2_radio_firstday').innerHTML="<b>" +labels[6] + "</b>";
					document.getElementById('before2_radio_lastday').innerHTML="<b>"+labels[7]+ "</b>";
				}
				else if(document.getElementById('firstdayhalfdayYes').status && !document.getElementById('lastdayhalfdayYes').status && document.getElementById('halfdayYes').status){
					document.getElementById('before2_radio_firstday').innerHTML="<b>"+labels[6]+"</b>";
					document.getElementById('before2_radio_lastday').innerHTML="";
				}
				else if(!document.getElementById('firstdayhalfdayYes').status && document.getElementById('lastdayhalfdayYes').status && document.getElementById('halfdayYes').status){
					document.getElementById('before2_radio_firstday').innerHTML="";
					document.getElementById('before2_radio_lastday').innerHTML="<b>"+labels[7]+"</b>";
				}
				else if(!document.getElementById('firstdayhalfdayYes').status && !document.getElementById('lastdayhalfdayYes').status && document.getElementById('halfdayYes').status){
					document.getElementById('before2_radio_firstday').innerHTML="";
					document.getElementById('before2_radio_lastday').innerHTML="";
				}
				}
				else if(document.frmleaveapply.halfday[1].status){
				document.getElementById('before2_lbl').style.display="none";
				document.getElementById('before2_radio').style.display="none";
				document.getElementById('First_Day').style.display="none";
				document.getElementById('Last_Day').style.display="none";
			}	 		 
			}else{
				document.getElementById('firstdayhalfdayNo').status=true; 
				document.getElementById('lastdayhalfdayNo').status =true;
				document.getElementById('before2_lbl').style.display="none";
				document.getElementById('before2_radio').style.display="none";
				document.getElementById('First_Day').style.display="none";
				document.getElementById('Last_Day').style.display="none";
	 		 }

	

	}
	else {

		if(natureofleave[0]=='3'){
		showOrd(document.frmleaveapply.natureofleave);
	}
	
	
				document.getElementById("halfday").style.display="none";
				document.getElementById("halfdayNo").status=true;
				document.getElementById('firstdayhalfdayNo').status=true; 
				document.getElementById('lastdayhalfdayNo').status =true;
				document.getElementById('before2_lbl').style.display="none";
				document.getElementById('before2_radio').style.display="none";
				document.getElementById('First_Day').style.display="none";
				document.getElementById('Last_Day').style.display="none";
	}
			}


}

	function  checkFirst_LastDayCondition(alertMsg){

//alert(document.frmleaveapply.noofdays.value +  " ::: " + document.frmleaveapply.noofdays.value.search(re5digit));
if(document.getElementById('halfdayYes').status && document.getElementById('firstdayhalfdayNo').status && document.getElementById('lastdayhalfdayNo').status && (document.frmleaveapply.fromdate.value != document.frmleaveapply.todate.value)){
alert(alertMsg);
return false;
}
else 
return true;
}
function checkHalfDayCondition(radioButton){
/*if(document.getElementById('firstlastdayYes').status){
document.getElementById('before2No').status=true;

}
else{
document.getElementById('before2Yes').status=true;
}*/
}




/**
*  fillData method is called when user edits the combination leave.
*  argument(data)=data is a parameter which is passed from parent window using query string.
*  
*
*/


function fillData(data){

arrData=data.split('~');

for(k=1;k<document.frmleaveapply.natureofleave.options.length;k++){
//alert(document.frmleaveapply.natureofleave.options[k].value + "= = " + arrData[0]);
if(document.frmleaveapply.natureofleave.options[k].value==arrData[0]){
document.frmleaveapply.noofdays.value=arrData[3];
document.getElementById("nod").innerHTML=eval(arrData[3]);
document.getElementById("noofdays").style.display="";

if(arrData[0]=="1_1" && arrData[3]==eval(0.5))
{
 document.getElementById("halfday").style.display="";
 if(window.opener.document.forms[0].elements["halfday_comb"].value==eval(1)){
 document.frmleaveapply.halfday[0].status=true;
 
if(window.opener.document.forms[0].elements["before2_comb"].value==eval(1)){
document.frmleaveapply.before2[0].status=true;
}
else{
document.frmleaveapply.before2[1].status=true;
}
 }
 else{
 document.frmleaveapply.halfday[1].status=true;
 }
 
}
else{
 document.getElementById("halfday").style.display="none";

if(arrData[0]=="3_3"){
if(window.opener.document.forms[0].elements["ordi_comb"].value==eval(1)){
	document.getElementById("ordinarycir").style.display="";
	document.frmleaveapply.ordcir[0].status=true;
}
else{
	document.getElementById("ordinarycir").style.display="none";
	document.frmleaveapply.ordcir[1].status=true;
	document.getElementById("ordinarycir").style.display="";
}

}


}
document.frmleaveapply.natureofleave.options[k].selected=true;
break;
}
}
document.frmleaveapply.fromdate.value=arrData[1];
document.frmleaveapply.todate.value=arrData[2];

}










function returnFromDateArr(){
var combTxtBox=document.getElementsByName("combLeave");
for (i=0;i<document.getElementsByName("combLeave").length;i++){
var lvdtl=combTxtBox[i].value;
var lvdtlArr=lvdtl.split("~");
var Date1=lvdtlArr[1].split("/");
var Date2=lvdtlArr[2].split("/");
Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
combFromArr[i]=new Date(Date1);
combToArr[i]=new Date(Date2);
}
}



var prefixStatus=true;

function  visibleAllRows(table){

var rows = table.getElementsByTagName("tr");   
for(i = 0; i < rows.length; i++){
rows[i].style.display='';           
}
}
function deleteAllRowsFromTable(mytable){
   for(i=mytable.rows.length-1; i > 0; i--)
       mytable.deleteRow(i); 
}


function continuationDate(todate,nextDate,msg){

var diff=dateDifference(todate,nextDate);

	if(diff>0){
	if(diff==2){
	 toDate=toDate; 
		return true;
		}
	else{
	toDate=todate; 
	alert(msg);
	return false;
	}	
}else{
toDate=todate; 
return false;
}

}



function validateDates(fromDateArr,toDateArr,counter,msg,alertMsgDates){
var status=true;

	if(counter>1){
	
	if(counter==2){
	alert(alertMsgDates[12]);	
	return false;
	}
			for(i=0;i<toDateArr.length;i++){
	
	
					if(i<toDateArr.length-1){
					
						status=continuationDate(toDateArr[i],fromDateArr[i+1],msg);
							
						if(status==false){
						 document.forms[0].Submit.disabled=false;
						return false;
						}
					}
			}
	}
	else{
//alert(counter);
status=checkHalfDay();
if(status==false){
 document.forms[0].Submit.disabled=false;
}

	}
	return status;
}

			function showOrd(combo){

						if(combo.value=='3_3'){

								document.getElementById("ordinarycir").style.display="";
				
						}else{
								document.getElementById("ordinarycir").style.display="none";
						}
				}




	function validateForm(frm,alertMsgDates,requiredMsg,components,holidayList){

		if(frm.prefix[0].status==true){
			if(!validate('RDate',requiredMsg[1],'prefixfromdate',components[1])){
				return false;
			}
			if(!validate('RNull',requiredMsg[0],'prefixfromdate',components[1])){ 
				return false;
			}
			if(!validate('RDate',requiredMsg[1],'prefixtodate',components[2])){ 
				return false;
			}
			if(!validate('RNull',requiredMsg[0],'prefixtodate',components[2])){ 
				return false;
			}
			else{
			
			if(frm.combinationleave[0].status==false){
			prefixStatus= checkPrefix(frm,alertMsgDates,0);
						if(prefixStatus==false)return prefixStatus;
			prefixStatus=checkDatesFalls_in_GeneralHoliday(frm.prefixfromdate,frm.prefixtodate,holidayList,frm,alertMsgDates[13]);
			if(prefixStatus==false)return prefixStatus;
			}
			else{/* in case of combination Leave*/
			prefixStatus= checkPrefix(frm,alertMsgDates,1);
			if(prefixStatus==false)return prefixStatus;
			prefixStatus=checkDatesFalls_in_GeneralHoliday(frm.prefixfromdate,frm.prefixtodate,holidayList,frm,alertMsgDates[13]);
						if(prefixStatus==false)return prefixStatus;
			}
			}
		}
		else{
		prefixStatus=true;
		}
		
		if(frm.suffix[0].status==true){
		
//		alert("in suffix");
			if(!validate('RDate',requiredMsg[1],'suffixfromdate',components[3])){ 
				return false;
			}
			if(!validate('RNull',requiredMsg[0],'suffixfromdate',components[3])){ 
				return false;
			}
			if(!validate('RDate',requiredMsg[1],'suffixtodate',components[4])){ 
				return false;
			}
			if(!validate('RNull',requiredMsg[0],'suffixtodate',components[4])){ 
				return false;
			}
			else{
						if(frm.combinationleave[0].status==false){
			prefixStatus= checkSuffix(frm,alertMsgDates,0);
						if(prefixStatus==false)return prefixStatus;
						prefixStatus=(!checkDatesFalls_in_GeneralHoliday(frm.suffixfromdate,frm.suffixtodate,holidayList,frm,alertMsgDates[14]));
			if(prefixStatus==false)return prefixStatus;
			}
			else{/* in case of combination Leave*/
			prefixStatus= checkSuffix(frm,alertMsgDates,1);
				if(prefixStatus==false)return prefixStatus;
			prefixStatus=checkDatesFalls_in_GeneralHoliday(frm.suffixfromdate,frm.suffixtodate,holidayList,frm,alertMsgDates[14]);
						if(prefixStatus==false)return prefixStatus;
			}
				
			}
		}
		else{
		prefixStatus=true;
		}


			if(frm.combinationleave[0].status==false){
				if(!validate('RDropMenu',requiredMsg[2],'natureofleave',components[0])){ 
					return false;
			}
			if(!validate('RDate',requiredMsg[1],'fromdate',"dateTime"))
			{ 
			return false;
			}
			 
			if(!validate('RNull',requiredMsg[0],'fromdate',"dateTime"))
			{ 
			return false;
			}
			 
			if(!validate('RDate',requiredMsg[1],'todate',"dateTime"))
			{ 
			return false;
			}
			 
			if(!validate('RNull',requiredMsg[0],'todate',"dateTime"))
			{ 
			return false;
			}
 
		}

return prefixStatus;

		}

var leaveFromDate;
var prefixToDate;
var toDate_combi;
var fromDate_combi;


function checkPrefix(frm,alertMsg,leaveType){
       //Code to check for Prefix Dates Start
		if(leaveType==0){
         leaveFromDate = DateToMMDDYYYY(frm.fromdate.value);
         prefixToDate = DateToMMDDYYYY(frm.prefixtodate.value);

		leaveFromDate=new Date(DateToMMDDYYYY(frm.fromdate.value));        
		prefixToDate=new Date(DateToMMDDYYYY(frm.prefixtodate.value));
        }
        else{
         toDate_combi=toDateArr[0].getDate() +"/"+ eval(toDateArr[0].getMonth()+1) +"/" + toDateArr[0].getYear();
	     fromDate_combi=fromDateArr[0].getDate() +"/"+ eval(fromDateArr[0].getMonth()+1) +"/" + fromDateArr[0].getYear();
         leaveFromDate = DateToMMDDYYYY(fromDate_combi);
         prefixToDate = DateToMMDDYYYY(frm.prefixtodate.value);
        }
		noofDaysDiff=DateDifferenceInDays(prefixToDate,leaveFromDate,'d');
        
         if(noofDaysDiff<=0)
         {
           alert(alertMsg[5]);
           frm.prefixtodate.focus();
           frm.prefixtodate.value="";
           frm.prefixfromdate.value.value="";
           return false;
         }   
         if(noofDaysDiff>1)
         {
           alert(alertMsg[6]);
		   frm.prefixtodate.focus();
           frm.prefixtodate.value=""
           frm.prefixfromdate.value=""
           return false;
         }

         prefixFromDate = DateToMMDDYYYY(frm.prefixfromdate.value);
         noofDaysDiff=DateDifferenceInDays(prefixFromDate,prefixToDate,'d');
         if(noofDaysDiff<0)
         {
           alert(alertMsg[7]);
           frm.prefixfromdate.focus();
           frm.prefixfromdate.value="";
           return false;
         }
       return true;
       //Code to check for Prefix Dates End
    }   
  var leaveToDate;
  var suffixFromDate;

    function checkSuffix(frm,alertMsg,leaveType){
       //Code to check for Suffix Dates Start
         suffixFromDate = DateToMMDDYYYY(frm.suffixfromdate.value);       
       if(leaveType==0){
         leaveToDate = DateToMMDDYYYY(frm.todate.value);
         }else
         {
         toDate_combi=toDateArr[0].getDate() +"/"+ eval(toDateArr[0].getMonth()+1) +"/" + toDateArr[0].getYear();
	     toDate_combi=toDateArr[toDateArr.length-1].getDate() +"/"+ eval(toDateArr[toDateArr.length-1].getMonth()+1) +"/" + toDateArr[toDateArr.length-1].getYear();
         prefixToDate = DateToMMDDYYYY(frm.prefixtodate.value);
         leaveToDate = DateToMMDDYYYY(toDate_combi);
         }

         noofDaysDiff=DateDifferenceInDays(leaveToDate,suffixFromDate,'d');
        // alert("suffix :: " + noofDaysDiff);

         if(noofDaysDiff<=0)
         {
           alert(alertMsg[8]);
           frm.suffixfromdate.focus();
           frm.suffixfromdate.value="";
           frm.suffixtodate.value="";
           return false;
         }   
         if(noofDaysDiff>1)
         {
           alert(alertMsg[9]);
           frm.suffixfromdate.focus();
           frm.suffixfromdate.value="";
           frm.suffixtodate.value="";
           return false;
         }
         suffixToDate = DateToMMDDYYYY(frm.suffixtodate.value);
         noofDaysDiff=DateDifferenceInDays(suffixFromDate,suffixToDate,'d');
         if(noofDaysDiff<0)
         {
           alert(alertMsg[10]);
           frm.suffixtodate.focus();
           frm.suffixtodate.value="";
           return false;
         }
       
      return true;
}



 //Code to check for Siffix Dates End  
       //ADDED BY Manvir AS ON 15TH OCT 2007 END


function validateBalance(balanceLst,frm,msg,callCheckHalfDay_func){
		var  status=true;
		if(callCheckHalfDay_func==1){
		status=checkHalfDay();
		if(status==false){
		return false;}
		}
	if(frm.combinationleave[1].status || frm.combinationleave.value=="1"){
	
	for(var i=0;i<balanceLst.length;i++){	
//	alert(balanceLst[i]);
			var splitleavetypebal=balanceLst[i].split("~");
		var leaveType=frm.natureofleave.value.split('_');
			if(eval(splitleavetypebal[1])== eval(leaveType[0])){
	//			alert(splitleavetypebal[0]);						
		//		alert(splitleavetypebal[1]+" == " +leaveType[0] +" : " + frm.noofdays.value);						
			//	alert(eval(splitleavetypebal[0]) < eval(frm.noofdays.value));

				if(eval(splitleavetypebal[0]) < eval(frm.noofdays.value)){
				alert(msg);
				return false;
			}
		}
							

}
}
	else{

if(counter>1){
		

var combTxtBox=document.getElementsByName("combLeave");

		for(var j=0;j<combTxtBox.length;j++){
			for(var i=0;i<balanceLst.length;i++){	
			var splitleavetypebal=balanceLst[i].split("~");
			combLeave=combTxtBox[j].value.split("~");
			
//		alert( "bbb :" + splitleavetypebal[1] +" : "+ combLeave);
		var	leavetype=combLeave[0].split('_');
				if(eval(splitleavetypebal[1])==eval(leavetype[0])){
				
					if(eval(splitleavetypebal[0])< eval(combLeave[3])){
						alert(msg);
						return false;
					}
					break;
				}

		}
		
	}
	}
	else{
	return false;
	}
 }
// alert(1);
 return true;
}


var frm;

function getLeaveData(component,form){
frm=form;
if(component.value!=0){
xmlHttp = GetXmlHttpObject(); 
var time=new Date().getTime();

document.getElementById("totalDays").innerHTML="";
document.getElementById("totalDays").innerHTML="0";


		   var url='hdiits.htm?actionFlag=getParticularLeaveData&appliedBetween='+component.value+"&time="+time;
		   //data base entry is done only logic for building xml is left
		   xmlHttp.open("POST",encodeURI(url),true);  
		   xmlHttp.onreadystatechange=populateDataForModify;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));

		 //  xmlHttp.send(url); 


}		

}


	function populateDataForModify() 
	{ 
		if(xmlHttp.readyState == 4)
		{ 

			if(xmlHttp.status == 200) 
			{   

			 document.forms[0].Submit.disabled=false;
			//window.location.reload(true);
			//ajaxfunctionLeaveAttachment();

   
				counter =1;
			   	var xmlDoc=xmlHttp.responseText;


				var appDesXML = xmlDoc.split('$$$ATTACHMENT_XML$$$');	


		
		    	var xmlDOM=getDOMFromXML(appDesXML[0]);	
	    		var leave_other_dtl="";
	    		var leaveDetail="";
	    		var comb_leave="";
				var leave_status="";
		    	leaveDetail = xmlDOM.getElementsByTagName('LEAVE_DETAIL');      // getting an element from XML file				    	
				 comb_leave = xmlDOM.getElementsByTagName('COMB_FLAG');      // getting an element from XML file				    	
				 leave_status = xmlDOM.getElementsByTagName('STATUS');      // getting an element from XML file				    	
				 leave_other_dtl = xmlDOM.getElementsByTagName('LEAVE_OTHER_DTL');

				
				var childs;					
					frm.selectedIndex.value=frm.appliedbetween.selectedIndex;
					// not a combination leave
					
					// combination leave with more than 1 leave
					//childs=leave_other_dtl[0].childNodes.length;
					var erase="";
					document.getElementById("displayTable").getElementsByTagName("tbody")[0].innerText=erase; 

					frm.combinationleave[1].status=true;
					showbutton(frm.combinationleave[1],0);
					frm.parentid.value=leaveDetail[0].childNodes[0].text;
					frm.leavereason.value=leaveDetail[0].childNodes[1].text;


					
					if(leaveDetail[0].childNodes[3].text=='null'){
					frm.prefix[1].status=true;
					document.getElementById('yes1').style.display='none';
//					document.getElementById('no1').style.display='';
					}else{
					frm.prefix[0].status=true;
					document.getElementById('yes1').style.display='';
	///				document.getElementById('no1').style.display='none';
					document.frmleaveapply.prefixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[3].text);
					frm.prefixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[4].text);
					}
					
					if(leaveDetail[0].childNodes[6].text=='null'){
					frm.suffix[1].status=true;
					document.getElementById('yes2').style.display='none';
	//				document.getElementById('no2').style.display='';
					}else{
					frm.suffix[0].status=true;
					document.getElementById('yes2').style.display='';
		//			document.getElementById('no2').style.display='none';
					frm.suffixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[6].text);
					frm.suffixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[7].text);
					}
					frm.remarks.value=leaveDetail[0].childNodes[5].text;
					frm.contactaddress.value=leaveDetail[0].childNodes[12].text;
					if(leaveDetail[0].childNodes[11].text==1){
							frm.informsuperior[0].status=true;
					}else
					{
						frm.informsuperior[1].status=true;
					}
						
						
					if(leaveDetail[0].childNodes[16].text=="1"){
						frm.halfday_comb.status=true;
					}
					if(leaveDetail[0].childNodes[17].text=="1"){
						frm.before2_comb.status=true;
					}
					if(leaveDetail[0].childNodes[18].text!="null"){
						var phone=leaveDetail[0].childNodes[18].text.split("-");

						if(phone[0]!="xxxx")
						frm.telecode.value=phone[0];

						frm.telephone.value=phone[1];
					}



					for(k=0;k<leave_other_dtl.length;k++)
					{
					if(leave_other_dtl.length==1 && comb_leave[0].childNodes[0].text==0){
						if(leave_other_dtl[k].childNodes[8].text=='3'){
							if(leaveDetail[0].childNodes[2].text==0){
							frm.ordcir[1].status=true;
							frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
							document.getElementById("ordinarycir").style.display="";					
							}
							else{
							document.getElementById("ordinarycir").style.display="";					
							frm.ordcir[0].status=true;
							frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
							}
					}
						frm.fromdate.value=DDMMYYYY(leave_other_dtl[k].childNodes[0].text);
						frm.todate.value=DDMMYYYY(leave_other_dtl[k].childNodes[1].text);
						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";
						document.getElementById("nod").innerHTML="";
						document.getElementById("nod").innerHTML=" :"+leave_other_dtl[k].childNodes[5].text;
						frm.noofdays.value=leave_other_dtl[k].childNodes[5].text;
					

						if(leave_other_dtl[k].childNodes[8].text=='1'){
						document.getElementById("halfday").style.display="";
//						alert(leaveDetail[0].childNodes[16].text);

						if(leave_other_dtl[k].childNodes[10].text=="1"){
						frm.halfday[0].status=true;
						/*if (leave_other_dtl[k].childNodes[5].text!="0.5"){
						document.getElementById("first_second_Day").style.display="";
						}*/


					/****************************************************************************************************************/


						if(leave_other_dtl[k].childNodes[11].text=="1"){
						frm.before2[0].status=true;
						}
						else{
						frm.before2[1].status=true;
						}
						if(leave_other_dtl[k].childNodes[12].text=="1_1"){
			
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML=labels[8]+" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text);
						document.getElementById("lastday_halfday").innerHTML=labels[8]+" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text);
						document.getElementById('firstdayhalfdayYes').status=true;
						document.getElementById('lastdayhalfdayYes').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="<b>"+ labels[6]+"</b>";
						document.getElementById('before2_radio_lastday').innerHTML="<b>"+labels[7]+"</b>";
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_radio').style.display="none";
						document.getElementById('before2No').status=true;
			
						}
						else if (leave_other_dtl[k].childNodes[12].text=="1_0"){
						
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML=labels[8]+" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text);
						document.getElementById("lastday_halfday").innerHTML=labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text);
						document.getElementById('firstdayhalfdayYes').status=true;
						document.getElementById('lastdayhalfdayNo').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="<b>"+labels[6]+"</b>";
						document.getElementById('before2_radio_lastday').innerHTML="";
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_radio').style.display="none";
						document.getElementById('before2No').status=true;
						
						
						}

						else if (leave_other_dtl[k].childNodes[12].text=="0_1"){
						
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML=labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text);
						document.getElementById("lastday_halfday").innerHTML=labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text);
						document.getElementById('firstdayhalfdayNo').status=true;
						document.getElementById('lastdayhalfdayYes').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="";
						document.getElementById('before2_radio_lastday').innerHTML="Before 2 p.m";
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_radio').style.display="none";
						document.getElementById('before2No').status=true;
						
						
						}
						else if (leave_other_dtl[k].childNodes[12].text=="0_0"){
						document.getElementById("First_Day").style.display="none";
						document.getElementById("Last_Day").style.display="none";
						document.getElementById("firstday_halfday").innerHTML="";
						document.getElementById("lastday_halfday").innerHTML="";
						document.getElementById('firstdayhalfdayNo').status=true;
						document.getElementById('lastdayhalfdayNo').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="";
						document.getElementById('before2_radio_lastday').innerHTML="";
						}
				
				/****************************************************************************************************************/					
		
					}
					else{
						frm.halfday[1].status=true;
						frm.before2[1].status=true;
			
						frm.before2[1].status=true;
//						document.getElementById("first_second_Day").style.display="none";
						//document.getElementById("halfday").style.display="none";

					}
					}
					else{
						frm.halfday[0].checked=true;
						frm.before2[0].checked=true;
						document.getElementById("halfday").style.display="none";
//						document.getElementById("first_second_Day").style.display="none";
					}
				// ordinary circumstances radio button get displayed when leave is earned leave ends
					
						
						frm.natureofleave.options[leave_other_dtl[k].childNodes[9].text].selected=true;
						document.getElementById("leavetype").style.display="";
						
						document.getElementById("leavetype_lbl").innerHTML=": " +frm.natureofleave.options[leave_other_dtl[k].childNodes[9].text].innerText;
						document.getElementById("noofdays_lbl").innerHTML=": "+leave_other_dtl[k].childNodes[5].text;

						document.getElementById("sanction_TR").style.display="";
						document.getElementById("sanction_days").style.display="";
						document.getElementById("sanction_days1").style.display="";
						document.getElementById("sanction_days").innerHTML=": "+leave_other_dtl[k].childNodes[6].text;

					if(leave_other_dtl[k].childNodes[2].text!="null")
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sanc_fromdate"));
					else
						document.getElementById("sanc_fromdate").innerHTML="-";
					if(leave_other_dtl[k].childNodes[2].text!="null")
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[3].text,document.getElementById("sanc_todate"));
					else
						document.getElementById("sanc_todate").innerHTML="-";

	//					document.getElementById("halfday").style.display="";

	
					}else{

						// code for combinational leave
						document.getElementById("sanction_TR").style.display="none";
						document.getElementById("sanction_days").style.display="none";
						document.getElementById("sanction_days1").style.display="none";
						document.getElementById("leavetype").style.display="none";
						frm.combinationleave[0].status=true;
						showbutton(frm.combinationleave[0],0);
						leave_other_dtl[k].childNodes[0].text
						//alert("From Date ::"+ DDMMYYYY(leave_other_dtl[k].childNodes[0].text) + ":"+ "ToDate :" +  DDMMYYYY(leave_other_dtl[k].childNodes[1].text));
						var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
						leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
						frm.leavedtls.value=leave_dtl;

						addRow(0);
						document.getElementById("lvdtl").style.display="";
						addRow_display(0,"");
						document.getElementById("displayTable").style.display="";
		//				addRow(0,document.getElementById("lvdtl"),2);		
					var	str="_";
					if(leave_other_dtl[k].childNodes[2].text!="null"){
												
					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[2].text,document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text));
					}else
					{
							
					document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					
					if(leave_other_dtl[k].childNodes[3].text!="null"){

					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[3].text,document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text));

					}else{

					document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[6].text!="0.0"){
					document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML=leave_other_dtl[k].childNodes[5].text;
					}else
					{

					document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}

					}
					

				}	
		

			disp_counter=1;
				populateAttachmentForEditing(appDesXML[1]);
		 
		 }
		 else{
			 xmlDoc=null;
			 xmlHttp=null;
			 }
	}
	else{
		//disable();
	}

}


function populateRecord(frm,xmlDoc,labels)
{

		     	counter =1;
//			   	var xmlDoc=xmlHttp.responseText;
				var appDesXML = xmlDoc.split('$$$ATTACHMENT_XML$$$');	
		    	var xmlDOM=getDOMFromXML(appDesXML[0]);	
//				alert(xmlDoc);


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
					
					// combination leave with more than 1 leave
					//childs=leave_other_dtl[0].childNodes.length;



		
					frm.leaveid.value=leaveDetail[0].childNodes[0].text;


					if(comb_leave[0].text=="0"){
					document.getElementById('combinationleave').innerHTML=": "+labels[5];
					frm.combinationleave[1].status=true;
					
					}
					else
					{
					document.getElementById('combinationleave').innerHTML=": "+labels[4];
					frm.combinationleave[0].status=true;
					}


					//showbutton(frm.combinationleave[1]);

	//				frm.parentid.value=leaveDetail[0].childNodes[0].text;
						
						if(leaveDetail[0].childNodes[20].text!='null'){
							frm.parentid.value=leaveDetail[0].childNodes[20].text;
						}
					frm.leavereason.value=leaveDetail[0].childNodes[1].text;


					if(leaveDetail[0].childNodes[2].text==0){
//					frm.ordcir[1].status=true;
					frm.ordcir.value="0";
					document.getElementById("ordi_radio").innerText=": "+labels[5];
					frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
					}
					else{
					document.getElementById("ordi_radio").innerText=": "+labels[4];
					frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
					frm.ordcir.value="1";
					}
															
					if(leaveDetail[0].childNodes[3].text=='null'){

					//frm.prefix[1].status=true;
		//			alert("nu");
					document.getElementById("prefix_radio").innerText=": "+labels[5];
					document.getElementById('yes1').style.display='none';
//					document.getElementById('no1').style.display='';
					}else{
					//frm.prefix[0].status=true;
					document.getElementById("prefix_radio").innerText=": "+labels[4];
					document.getElementById('yes1').style.display='';
//					document.getElementById('no1').style.display='none';
					document.frmleaveapply.prefixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[3].text);
					frm.prefixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[4].text);
					}

				//	alert("in" + leaveDetail[0].childNodes[6].text);

					if(leaveDetail[0].childNodes[6].text=='null'){
					//frm.suffix[1].status=true;
					document.getElementById("suffix_radio").innerText=": "+labels[5];
					document.getElementById('yes2').style.display='none';
	//				document.getElementById('no2').style.display='';
				
					}else{
					//frm.suffix[0].status=true;
					document.getElementById("suffix_radio").innerText=": "+labels[4];
					document.getElementById('yes2').style.display='';
//					document.getElementById('no2').style.display='none';
					frm.suffixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[6].text);
					frm.suffixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[7].text);
					}
					frm.remarks.value=leaveDetail[0].childNodes[5].text;
					//frm.contactaddress.value=leaveDetail[0].childNodes[12].text;


					if(leaveDetail[0].childNodes[11].text==1){

					document.getElementById("informsuperior_radio").innerText=": "+labels[5];
											
					}else
					{
					document.getElementById("informsuperior_radio").innerText=": "+labels[4];
					}
					if(leaveDetail[0].childNodes[19].text!="null"){
					frm.userId.value=leaveDetail[0].childNodes[19].text;
					}

					frm.status.value=leaveDetail[0].childNodes[14].text;
					
					if(leaveDetail[0].childNodes[14].text!="0")
					{
						document.getElementById("TDbuttons").innerHTML="<b>" +labels[9] +"</b>";
						document.getElementById("balance_hdr").style.display="none";
						document.getElementById("leaveType_hdr").style.display="none";
						document.getElementById("balance_values").style.display="none";
						
					}else{
						document.getElementById("TDbuttons").style.display="";
						document.getElementById("balance_hdr").style.display="";
						document.getElementById("leaveType_hdr").style.display="";
						document.getElementById("balance_values").style.display="";
						
						

					}
								
					if(leaveDetail[0].childNodes[18].text!="null"){

					var phone=leaveDetail[0].childNodes[18].text.split("-");
					colon=" :";
						if(phone[0]!="xxxx"){

						 document.getElementById("telephone_txt").innerHTML=phone[0]+"-";

						}
						else{
						colon="";
						}
						 
						document.getElementById("telephone_txt").innerHTML=colon+" "+document.getElementById("telephone_txt").innerHTML+phone[1];						
						document.getElementById("telephone").style.display="";
					}
					else{
							document.getElementById("telephone_txt").innerHTML=":"+"   "+"---";																	
		//					document.getElementById("telephone").style.display="none";
											
					}

					frm.contactaddress.value=leaveDetail[0].childNodes[12].text;
					frm.remarks.value=leaveDetail[0].childNodes[5].text;

					
					halfDayFacility(leave_other_dtl);

					for(k=0;k<leave_other_dtl.length;k++)
					{


					if(leave_other_dtl[k].childNodes[8].text=="3"){
														document.getElementById("ordinarycir").style.display="";
								}
								else{
														document.getElementById("ordinarycir").style.display="none";

								}
						
						
						if(leave_other_dtl.length==1 && comb_leave[0].childNodes[0].text==0){
						
						frm.fromdate.value=DDMMYYYY(leave_other_dtl[k].childNodes[0].text);
						frm.todate.value=DDMMYYYY(leave_other_dtl[k].childNodes[1].text);
						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";
						document.getElementById("nod").innerHTML="";
						document.getElementById("nod").innerHTML=": "+leave_other_dtl[k].childNodes[5].text;
						frm.noofdays.value=leave_other_dtl[k].childNodes[5].text;
						frm.natureofleave.options[eval(leave_other_dtl[k].childNodes[9].text)].selected=true;
					
					}else{
						// code for combinational leave

						document.getElementById('combinationleave').innerHTML=": "+labels[4];
						document.getElementById('fromdate').style.display='none';
						document.getElementById('toDate').style.display='none';
						document.getElementById('fromdate').style.display='none';
						document.getElementById('natureofleave').style.display='none';						
						frm.combinationleave[0].status=true;
										
						//showbutton(frm.combinationleave[0]);
						leave_other_dtl[k].childNodes[0].text
						//alert("From Date ::"+ DDMMYYYY(leave_other_dtl[k].childNodes[0].text) + ":"+ "ToDate :" +  DDMMYYYY(leave_other_dtl[k].childNodes[1].text));
						var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
						leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
						frm.leavedtls.value=leave_dtl;

						addRow(0);	
					
					var	str="_";
					if(leave_other_dtl[k].childNodes[2].text!="null"){
					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[2].text,document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text));
					}else
					{
							
					document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[3].text!="null"){
					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[3].text,document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text));

					}else{

					document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[6].text!="0.0"){

					document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML=leave_other_dtl[k].childNodes[5].text;
					}else
					{

					document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}

					}

						populateAttachmentForEditing(appDesXML[1]);

				}	
		


}






function populateRecordInReqPage(frm,xmlDoc){
					//alert("Leave Request Page ::: " +xmlDoc);
					var xmlDOM=getDOMFromXML(xmlDoc);	
					

					
					var leave_other_dtl="";
	    		var leaveDetail="";
	    		var comb_leave="";
				var leave_status="";
		    	leaveDetail = xmlDOM.getElementsByTagName('LEAVE_DETAIL');      // getting an element from XML file				    	
				 comb_leave = xmlDOM.getElementsByTagName('COMB_FLAG');      // getting an element from XML file				    	
				 leave_status = xmlDOM.getElementsByTagName('STATUS');      // getting an element from XML file				    	
				 leave_other_dtl = xmlDOM.getElementsByTagName('LEAVE_OTHER_DTL');

				
				var childs;					
					
					
					
					
					
					frm.combinationleave[1].status=true;
					showbutton(frm.combinationleave[1]);
					//frm.parentid.value=leaveDetail[0].childNodes[0].text;
					frm.leavereason.value=leaveDetail[0].childNodes[1].text;
					//alert(1);
					if(leaveDetail[0].childNodes[2].text==0){
					frm.ordcir[1].status=true;
					frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
					document.getElementById("ordinarycir").style.display="";					
					}
					else{
					document.getElementById("ordinarycir").style.display="";					
					frm.ordcir[0].status=true;
					frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
					}

					
					if(leaveDetail[0].childNodes[3].text=='null'){
					frm.prefix[1].status=true;
					document.getElementById('yes1').style.display='none';
//					document.getElementById('no1').style.display='';
					}else{
					frm.prefix[0].status=true;
					document.getElementById('yes1').style.display='';
	//				document.getElementById('no1').style.display='none';
					document.frmleaveapply.prefixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[3].text);
					frm.prefixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[4].text);
					}
					
					if(leaveDetail[0].childNodes[6].text=='null'){
					frm.suffix[1].status=true;
					document.getElementById('yes2').style.display='none';
//					document.getElementById('no2').style.display='';
					}else{
					frm.suffix[0].status=true;
					document.getElementById('yes2').style.display='';
//					document.getElementById('no2').style.display='none';
					frm.suffixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[6].text);
					frm.suffixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[7].text);
					}
					frm.remarks.value=leaveDetail[0].childNodes[5].text;
					frm.contactaddress.value=leaveDetail[0].childNodes[12].text;
					if(leaveDetail[0].childNodes[11].text==1){
							frm.informsuperior[0].status=true;
					}else
					{
						frm.informsuperior[1].status=true;
					}
						
						
					if(leaveDetail[0].childNodes[16].text=="1"){
						frm.halfday_comb.status=true;
					}
					if(leaveDetail[0].childNodes[17].text=="1"){
						frm.before2_comb.status=true;
					}
					if(leaveDetail[0].childNodes[18].text!="null"){
							var phone=leaveDetail[0].childNodes[18].text.split("-");

						if(phone[0]!="xxxx")
						frm.telecode.value=phone[0];

						frm.telephone.value=phone[1];
						

					}
					for(k=0;k<leave_other_dtl.length;k++)
					{
						
					
					
					if(leave_other_dtl.length==1 && comb_leave[0].childNodes[0].text==0){
						frm.fromdate.value=DDMMYYYY(leave_other_dtl[k].childNodes[0].text);
						frm.todate.value=DDMMYYYY(leave_other_dtl[k].childNodes[1].text);
						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";
						document.getElementById("nod").innerHTML="";
						document.getElementById("nod").innerHTML=leave_other_dtl[k].childNodes[5].text;
						frm.noofdays.value=leave_other_dtl[k].childNodes[5].text;
					if(leave_other_dtl[k].childNodes[5].text==0.5){
						document.getElementById("halfday").style.display="";
						frm.halfday[0].checked=true;
						frm.before2[0].checked=true;
					}
					else{
						frm.halfday[0].checked=false;
						frm.before2[0].checked=false;
						document.getElementById("halfday").style.display="none";
					}
						frm.natureofleave.options[leave_other_dtl[k].childNodes[9].text].selected=true;
						document.getElementById("sanction_TR").style.display="";
						document.getElementById("sanction_days").style.display="";
						document.getElementById("sanction_days1").style.display="";
						document.getElementById("sanction_days").innerHTML=leave_other_dtl[k].childNodes[6].text;
					if(leave_other_dtl[k].childNodes[2].text!="null")
						document.getElementById("sanc_fromdate").innerHTML=leave_other_dtl[k].childNodes[2].text;
					else
						document.getElementById("sanc_fromdate").innerHTML="-";
					if(leave_other_dtl[k].childNodes[2].text!="null")
						document.getElementById("sanc_todate").innerHTML=leave_other_dtl[k].childNodes[2].text;
					else
						document.getElementById("sanc_todate").innerHTML="-";

	//					document.getElementById("halfday").style.display="";
					}else{
						// code for combinational leave
						document.getElementById("sanction_TR").style.display="none";
						document.getElementById("sanction_days").style.display="none";
						document.getElementById("sanction_days1").style.display="none";
						frm.combinationleave[0].status=true;
						showbutton(frm.combinationleave[0]);
						leave_other_dtl[k].childNodes[0].text
						//alert("From Date ::"+ DDMMYYYY(leave_other_dtl[k].childNodes[0].text) + ":"+ "ToDate :" +  DDMMYYYY(leave_other_dtl[k].childNodes[1].text));
						var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
						leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
						frm.leavedtls.value=leave_dtl;
						addRow(0);	
					if(leave_other_dtl[k].childNodes[2].text!="null"){
						document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text).innerHTML=leave_other_dtl[k].childNodes[2].text;
					}else
					{
						document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[3].text!="null"){
						document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text).innerHTML=leave_other_dtl[k].childNodes[3].text;
					}else{
						document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[6].text!="0.0"){
						document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text).innerHTML=leave_other_dtl[k].childNodes[5].text;
					}else
					{
						document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text).innerHTML='-';
					}
	
					}
					

				}	
		
					 enable();		
					 }























function checkAll() {

var checks=document.getElementsByTagName("INPUT");
	var flag=false;
		for(j=0;j<checks.length;j++){
			if(checks[j].type=="checkbox"){
				if(checks[j].name=="chk")
				{
				 	if(checks[j].checked){
				 	flag=true;
				 	}
				 	else{
				 	flag=false;
				 	}
				 }
				if(flag){
				checks[j].checked=true;

				}
				else{
				checks[j].checked=false;
				
				}
				
				
				
				}
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



function deleteRow(actionFlag,state,alertMsg)
{
//alert('<fmt:message  bundle="${alertLables}" key="HRMS.MULTIPLE_EDIT"/>');
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

//alert("checkCounter" + checkCounter);
	if(checkCounter >= 1){
		delRow(deletedObject,nondeletedObject);
	}
	else if(checkCounter<1){
		alert(alertMsg[1]);
}

}
else{
if(checkCounter>1){
alert(alertMsg[0]);
}
else if(checkCounter<1){
alert(alertMsg[1]);
}
else{

value=document.getElementById("txt"+editedObject.cnt).value;
//alert(value+":"+ state);
//fillData(value);
//alert("hdiits.htm?actionFlag=viewLeaveTypes&flag=0&editedValue="+value+state,"LeaveTypes","width=700,height=240,menubar=yes,status=yes,resizable=yes,top=100,left=100");
var popupwindow=window.open("hdiits.htm?actionFlag=viewLeaveTypes&flag=0&editedValue="+value+state,"LeaveTypes","width=700,height=240,menubar=yes,toolbar=no,status=yes,resizable=yes,top=100,left=100");




}
}
return true;
}
function delRow(deletedObject,nondeletedObject){

var delCounter=0;
var temp="";
var tbody = document.getElementById("lvdtl").getElementsByTagName("tbody")[0]; 
var rows=deletedObject.split("~");

	for(m=0;m<rows.length-1;m++){
			temp+=document.getElementById(rows[m]).cnt+"~";
						for(l=0;l<leaveArr.length;l++){
							if(document.getElementById(rows[m]).cnt==leaveArr[l]){
						
									leaveArr.splice(l,1);
									toDateArr.splice(l,1);
									fromDateArr.splice(l,1);
						
								}
							}
			counter-=1;
			delCounter+=1;	
			tbody.removeChild(document.getElementById(rows[m]));

		}
if(delCounter>0){
var serialUpdate=nondeletedObject.split("~");

for(k=0;k<serialUpdate.length-1;k++){
var cell=document.getElementById("sr_"+document.getElementById(serialUpdate[k]).cnt);
var srno=eval((cell.innerHTML)-delCounter);
if(srno>0){
//srno=1;
cell.innerHTML=srno;
}
}
}
if(nondeletedObject==""){
tbody.innerText="";
document.getElementById("lvdtl").style.display="none";
counter=1;
}
}

function viewSubmittedReq(){
window.open("hrms.htm?actionFlag=viewPendingLeaveReq&sanction=1","SubmittedRequest","width=700,height=240,status=yes,menubar=yes,resizable=yes,top=100,left=100");

}


function  validateLeaveRules(frm){
//frm=form;
xmlHttp = GetXmlHttpObject(); 


		   var url='hdiits.htm?actionFlag=validateLeaveData&leaveType='+frm.natureofleave.value;
		   //data base entry is done only logic for building xml is left

			 xmlHttp.open("POST",encodeURI(url),true);  		  
			xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xmlHttp.onreadystatechange=validateDataAsPerRule;
			parameters='fromdate='+frm.fromdate.value+'&todate='+frm.todate.value+'&natureofleave='+frm.natureofleave.value+'&noofdays='+frm.noofdays.value;
//			alert(parameters);
			xmlHttp.send(parameters);

		 //  xmlHttp.send(url); 



/*xmlhttp.open( "post", url, false ); 
xmlhttp.setRequestHeader(     'Content-Type',     'application/x-www-form-urlencoded; charset=UTF-8' ); 
xmlhttp.send("apples=yes&bananas=no")
*/



}		




	function validateDataAsPerRule() 
	{ 
	


		if(xmlHttp.readyState == 4)
		{ 

			if(xmlHttp.status == 200) 
			{   
			  MsgConst=null;

				counter =1;
			   	var xmlDoc=xmlHttp.responseText;
//		    	alert(xmlDoc);
		    	var xmlDOM=getDOMFromXML(xmlDoc);	
	    		var leave_other_dtl="";
	    		var leaveDetail="";
	    		var cl_rule="";
				var leave_status="";
		    	leaveRuleDetail = xmlDOM.getElementsByTagName('MSG_CONSTANT');      // getting an element from XML file				    	
	
					/*	casualRuleBuffer.append("<ALL_APPLIED_LEAVES>");
						casualRuleBuffer.append("<LEAVE_TYPE>");
						casualRuleBuffer.append("</LEAVE_TYPE>");
						casualRuleBuffer.append("<noofdaysAllowed>");
						casualRuleBuffer.append("</noofdaysAllowed>");
						casualRuleBuffer.append("<designationValidationRule>");
						casualRuleBuffer.append("</designationValidationRule>");
						casualRuleBuffer.append("</ALL_APPLIED_LEAVES>");	
						casualRuleBuffer.append("</LEAVE_RULE_DETAIL>");*/
					
						
						MsgConst=leaveRuleDetail[0].text;
					
						
						alert(MsgConst);
//						enable();


		 }
		 else{
			 xmlDoc=null;
			 xmlHttp=null;
			 }
	}
	else{
//disable();
	}
	

}



function populateLeaveRequestPage(frm,xml) 
	{ 
		
				counter =1;

			    var appDesXML = xml.split('$$$ATTACHMENT_XML$$$');	
		    	var xmlDOM=getDOMFromXML(appDesXML[0]);	
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
					
					// combination leave with more than 1 leave
					//childs=leave_other_dtl[0].childNodes.length;

					frm.combinationleave[1].status=true;
					showbutton(frm.combinationleave[1],0);
					frm.parentid.value=leaveDetail[0].childNodes[0].text;
					frm.leavereason.value=leaveDetail[0].childNodes[1].text;


					
					if(leaveDetail[0].childNodes[3].text=='null'){
					frm.prefix[1].status=true;
					document.getElementById('yes1').style.display='none';
//					document.getElementById('no1').style.display='';
					}else{
					frm.prefix[0].status=true;
					document.getElementById('yes1').style.display='';
	///				document.getElementById('no1').style.display='none';
					document.frmleaveapply.prefixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[3].text);
					frm.prefixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[4].text);
					}
					
					if(leaveDetail[0].childNodes[6].text=='null'){
					frm.suffix[1].status=true;
					document.getElementById('yes2').style.display='none';
	//				document.getElementById('no2').style.display='';
					}else{
					frm.suffix[0].status=true;
					document.getElementById('yes2').style.display='';
		//			document.getElementById('no2').style.display='none';
					frm.suffixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[6].text);
					frm.suffixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[7].text);
					}
					frm.remarks.value=leaveDetail[0].childNodes[5].text;
					frm.contactaddress.value=leaveDetail[0].childNodes[12].text;
					if(leaveDetail[0].childNodes[11].text==1){
							frm.informsuperior[0].status=true;
					}else
					{
						frm.informsuperior[1].status=true;
					}
						
						
					if(leaveDetail[0].childNodes[16].text=="1"){
						frm.halfday_comb.status=true;
					}
					if(leaveDetail[0].childNodes[17].text=="1"){
						frm.before2_comb.status=true;
					}
					if(leaveDetail[0].childNodes[18].text!="null"){
						var phone=leaveDetail[0].childNodes[18].text.split("-");

						if(phone[0]!="xxxx")
						frm.telecode.value=phone[0];

						frm.telephone.value=phone[1];
					}



					for(k=0;k<leave_other_dtl.length;k++)
					{
					if(leave_other_dtl.length==1 && comb_leave[0].childNodes[0].text==0){
						if(leave_other_dtl[k].childNodes[8].text=='3'){
							if(leaveDetail[0].childNodes[2].text==0){
							frm.ordcir[1].status=true;
							frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
							document.getElementById("ordinarycir").style.display="";					
							}
							else{
							document.getElementById("ordinarycir").style.display="";					
							frm.ordcir[0].status=true;
							frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
							}
					}
						frm.fromdate.value=DDMMYYYY(leave_other_dtl[k].childNodes[0].text);
						frm.todate.value=DDMMYYYY(leave_other_dtl[k].childNodes[1].text);
						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";
						document.getElementById("nod").innerHTML="";
						document.getElementById("nod").innerHTML=" :"+leave_other_dtl[k].childNodes[5].text;

						frm.noofdays.value=leave_other_dtl[k].childNodes[5].text;

						if(leave_other_dtl[k].childNodes[8].text=='1'){
				
						document.getElementById("halfday").style.display="";

//						alert(leaveDetail[0].childNodes[16].text);

						if(leave_other_dtl[k].childNodes[10].text=="1"){
						frm.halfday[0].status=true;
						/*if (leave_other_dtl[k].childNodes[5].text!="0.5"){
						document.getElementById("first_second_Day").style.display="";
						}*/

		
					/****************************************************************************************************************/


						if(leave_other_dtl[k].childNodes[11].text=="1"){
						frm.before2[0].status=true;
						}
						else{
						frm.before2[1].status=true;
						}
						if(leave_other_dtl[k].childNodes[12].text=="1_1"){
			
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML="<b>" + labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text)+"</b>";
						document.getElementById("lastday_halfday").innerHTML="<b>" +labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text)+"</b>";
						document.getElementById('firstdayhalfdayYes').status=true;
						document.getElementById('lastdayhalfdayYes').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="<b>"+labels[6]+"</b>";
						document.getElementById('before2_radio_lastday').innerHTML="<b>"+labels[7]+"</b>";
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_radio').style.display="none";
						document.getElementById('before2No').status=true;
			
						}
						else if (leave_other_dtl[k].childNodes[12].text=="1_0"){
						
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML="<b>"+labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text) +"</b>";
						document.getElementById("lastday_halfday").innerHTML="<b>"+labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text)+"</b>";
						document.getElementById('firstdayhalfdayYes').status=true;
						document.getElementById('lastdayhalfdayNo').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="<b>"+ labels[6]+"</b>";
						document.getElementById('before2_radio_lastday').innerHTML="";
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_radio').style.display="none";
						document.getElementById('before2No').status=true;
						
						
						}

						else if (leave_other_dtl[k].childNodes[12].text=="0_1"){
						
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML="<b>" + labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text)+"</b>";
						document.getElementById("lastday_halfday").innerHTML="<b>" + labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text)+"</b>";
						document.getElementById('firstdayhalfdayNo').status=true;
						document.getElementById('lastdayhalfdayYes').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="";
						document.getElementById('before2_radio_lastday').innerHTML="<b>"+labels[7]+"</b>";
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_radio').style.display="none";
						document.getElementById('before2No').status=true;
						
						
						}
						else if (leave_other_dtl[k].childNodes[12].text=="0_0"){
						document.getElementById("First_Day").style.display="none";
						document.getElementById("Last_Day").style.display="none";
						document.getElementById("firstday_halfday").innerHTML="";
						document.getElementById("lastday_halfday").innerHTML="";
						document.getElementById('firstdayhalfdayNo').status=true;
						document.getElementById('lastdayhalfdayNo').status=true;
						document.getElementById('before2_radio_firstday').innerHTML="";
						document.getElementById('before2_radio_lastday').innerHTML="";
						}
				
				/****************************************************************************************************************/					
		
					}
					else{
						frm.halfday[1].status=true;
						frm.before2[1].status=true;

						frm.before2[1].status=true;
//						document.getElementById("first_second_Day").style.display="none";
						//document.getElementById("halfday").style.display="none";

					}
					}
					else{
						frm.halfday[0].checked=true;
						frm.before2[0].checked=true;
						document.getElementById("halfday").style.display="none";
					
					}
				// ordinary circumstances radio button get displayed when leave is earned leave ends
					
						frm.natureofleave.options[leave_other_dtl[k].childNodes[9].text].selected=true;
					}else{

						// code for combinational leave
						frm.combinationleave[0].status=true;
						showbutton(frm.combinationleave[0],0);
						leave_other_dtl[k].childNodes[0].text
						//alert("From Date ::"+ DDMMYYYY(leave_other_dtl[k].childNodes[0].text) + ":"+ "ToDate :" +  DDMMYYYY(leave_other_dtl[k].childNodes[1].text));
						var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
						leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
						frm.leavedtls.value=leave_dtl;
						addRow(0);	
						document.getElementById("lvdtl").style.display=""

					}
					

				}	

populateAttachmentForEditing(appDesXML[1]);

}


/*********************************************************************************************************/
//this function is called when Rules engine validates the  modify form and pupoulate the fields back 
/*********************************************************************************************************/
function populateModifyForm(frm,xml,previousXML,selectedIndex) 
	{ 
		
			//window.location.reload(true);
	//		ajaxfunctionLeaveAttachment();


				//alert(xml);
				counter =1;
				//var xmlDOM=getDOMFromXML(xml);			
			    var appDesXML = xml.split('$$$ATTACHMENT_XML$$$');	
		    	var xmlDOM=getDOMFromXML(appDesXML[0]);	

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
					
					// combination leave with more than 1 leave
					//childs=leave_other_dtl[0].childNodes.length;


					frm.combinationleave[1].status=true;
					
					showbutton(frm.combinationleave[1]);
					frm.parentid.value=leaveDetail[0].childNodes[0].text;
					frm.leavereason.value=leaveDetail[0].childNodes[1].text;
					
					if(leaveDetail[0].childNodes[3].text=='null'){
					frm.prefix[1].status=true;

					document.getElementById('yes1').style.display='none';

					//document.getElementById('no1').style.display='';

					}else{

					frm.prefix[0].status=true;
					document.getElementById('yes1').style.display='';
					//document.getElementById('no1').style.display='none';
					document.frmleaveapply.prefixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[3].text);
					frm.prefixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[4].text);
					}
					
					if(leaveDetail[0].childNodes[6].text=='null'){
					frm.suffix[1].status=true;
					document.getElementById('yes2').style.display='none';
//					document.getElementById('no2').style.display='';
					}else{
					frm.suffix[0].status=true;
					document.getElementById('yes2').style.display='';
//					document.getElementById('no2').style.display='none';
					frm.suffixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[6].text);
					frm.suffixtodate.value=DDMMYYYY(leaveDetail[0].childNodes[7].text);
					}


					frm.remarks.value=leaveDetail[0].childNodes[5].text;
					frm.contactaddress.value=leaveDetail[0].childNodes[12].text;

					if(leaveDetail[0].childNodes[11].text==1){
							frm.informsuperior[0].status=true;
					}else
					{
						frm.informsuperior[1].status=true;
					}
						
						
					if(leaveDetail[0].childNodes[16].text=="1"){
						frm.halfday_comb.status=true;
					}

					if(leaveDetail[0].childNodes[17].text=="1"){
						frm.before2_comb.status=true;
					}

					if(leaveDetail[0].childNodes[18].text!="null"){
							var phone=leaveDetail[0].childNodes[18].text.split("-");

						if(phone[0]!="xxxx")
						frm.telecode.value=phone[0];

						frm.telephone.value=phone[1];
						

					}

					for(k=0;k<leave_other_dtl.length;k++)
					{
					
					
					if(leave_other_dtl[k].childNodes[8].text=='3'){
							if(leaveDetail[0].childNodes[2].text==0){
							frm.ordcir[1].status=true;
							frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
							document.getElementById("ordinarycir").style.display="";					
							}
							else{
							document.getElementById("ordinarycir").style.display="";					
							frm.ordcir[0].status=true;
							frm.ordi_comb.value=leaveDetail[0].childNodes[2].text;
							}
					}
					
					
					if(leave_other_dtl.length==1 && comb_leave[0].childNodes[0].text==0){
						frm.fromdate.value=DDMMYYYY(leave_other_dtl[k].childNodes[0].text);
						frm.todate.value=DDMMYYYY(leave_other_dtl[k].childNodes[1].text);
						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";
						document.getElementById("nod").innerHTML="";
						document.getElementById("nod").innerHTML=leave_other_dtl[k].childNodes[5].text;
						frm.noofdays.value=leave_other_dtl[k].childNodes[5].text;

						if(leave_other_dtl[k].childNodes[5].text.search(re5digit)!=-1 && leave_other_dtl[k].childNodes[8].text=='1'){
						document.getElementById("halfday").style.display="";
//						alert(leaveDetail[0].childNodes[16].text);

				if(leave_other_dtl[k].childNodes[10].text=="1"){

						frm.halfday[0].status=true;
//						document.getElementById("first_second_Day").style.display="";
						if(leave_other_dtl[k].childNodes[11].text=="1"){
						frm.before2[0].status=true;
						}
						else{
						frm.before2[1].status=true;
						}
						if(leave_other_dtl[k].childNodes[12].text=="1"){

//						document.getElementById("first_second_Day").style.display="";
						}
						else{

						}
						
						
					}
					else{
						frm.halfday[1].status=true;
						frm.before2[1].status=true;

						frm.before2[1].status=true;
//						document.getElementById("first_second_Day").style.display="none";
						//document.getElementById("halfday").style.display="none";

					}
					}
					else{
						frm.halfday[0].checked=true;
						frm.before2[0].checked=true;
						document.getElementById("halfday").style.display="none";
//						document.getElementById("first_second_Day").style.display="none";
					}
						frm.natureofleave.options[leave_other_dtl[k].childNodes[9].text].selected=true;
						document.getElementById("sanction_TR").style.display="";
						document.getElementById("sanction_days").style.display="";
						document.getElementById("sanction_days1").style.display="";
						document.getElementById("sanction_days").innerHTML=leave_other_dtl[k].childNodes[6].text;
					if(leave_other_dtl[k].childNodes[2].text!="null")
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sanc_fromdate"));
					else
						document.getElementById("sanc_fromdate").innerHTML="-";
					if(leave_other_dtl[k].childNodes[2].text!="null")
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sanc_todate"));
					else
						document.getElementById("sanc_todate").innerHTML="-";

	//					document.getElementById("halfday").style.display="";
					}else{
						// code for combinational leave
						document.getElementById("sanction_TR").style.display="none";
						document.getElementById("sanction_days").style.display="none";
						document.getElementById("sanction_days1").style.display="none";
						frm.combinationleave[0].status=true;
						showbutton(frm.combinationleave[0]);
						leave_other_dtl[k].childNodes[0].text
						//alert("From Date ::"+ DDMMYYYY(leave_other_dtl[k].childNodes[0].text) + ":"+ "ToDate :" +  DDMMYYYY(leave_other_dtl[k].childNodes[1].text));
						var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
						leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
						frm.leavedtls.value=leave_dtl;
						addRow(0);	
						document.getElementById("lvdtl").style.display="";
						if(previousXML==""){
						addRow_display(0,previousXML);
						document.getElementById("displayTable").style.display="";
						}
					if(leave_other_dtl[k].childNodes[2].text!="null"){
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text));
					}else
					{
						//document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[3].text!="null"){
						FormatDDMMYYYY(leave_other_dtl[k].childNodes[3].text,document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text));

					}else{
						//document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[6].text!="0.0"){
						document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text).innerHTML=leave_other_dtl[k].childNodes[5].text;
					}else
					{
						//document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text).innerHTML='-';
					}
	
					}
					

				}	
		

	populateAttachmentForEditing(appDesXML[1]);

	if(previousXML!=""){
	 populateModifyPreviousForm(frm,previousXML,selectedIndex);
	}
}









/*********************************************************************************************************/
//this function is called when Rules engine validates the  modify form and pupoulate the fields back 
/*********************************************************************************************************/
function populateModifyPreviousForm(frm,xml,selectedIndex) 
	{ 


			//window.location.reload(true);
			//ajaxfunctionLeaveAttachment();
				leaveArr_disp=null;
				leaveArr_disp=new Array();
				leaveArr_disp[0]="1";
				//counter =1;
				//var xmlDOM=getDOMFromXML(xml);			
			    var appDesXML = xml.split('$$$ATTACHMENT_XML$$$');	
		    	var xmlDOM=getDOMFromXML(appDesXML[0]);	

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
					
					frm.parentid.value=leaveDetail[0].childNodes[0].text;
					if(selectedIndex!=""){
					frm.appliedbetween.options[selectedIndex].selected=true;
					
					}
					if(leaveDetail[0].childNodes[3].text=='null'){
					document.getElementById('yes1').style.display='none';
					}else{
					document.getElementById('yes1').style.display='';
					document.frmleaveapply.prefixfromdate.value=DDMMYYYY(leaveDetail[0].childNodes[3].text);
					}
					if(leaveDetail[0].childNodes[6].text=='null'){
					
					document.getElementById('yes2').style.display='none';
//					document.getElementById('no2').style.display='';
					}else{
					document.getElementById('yes2').style.display='';
					}
					if(leaveDetail[0].childNodes[11].text==1){
					
					}else
					{
					
					}
						
						
					if(leaveDetail[0].childNodes[16].text=="1"){

					}

					if(leaveDetail[0].childNodes[17].text=="1"){

					}

					if(leaveDetail[0].childNodes[18].text!="null"){
					}
						
					for(k=0;k<leave_other_dtl.length;k++)
					{
					
					document.getElementById("noofdays_lbl").innerHTML=": "+leave_other_dtl[k].childNodes[5].text;		
					if(leave_other_dtl.length==1 && comb_leave[0].childNodes[0].text==0){


						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";

		
		

						if(leave_other_dtl[k].childNodes[5].text.search(re5digit)!=-1 && leave_other_dtl[k].childNodes[8].text=='1'){
						document.getElementById("halfday").style.display="";
//						alert(leaveDetail[0].childNodes[16].text);

						if(leave_other_dtl[k].childNodes[10].text=="1"){


//						document.getElementById("first_second_Day").style.display="";
						if(leave_other_dtl[k].childNodes[11].text=="1"){

						}
						else{

						}
						if(leave_other_dtl[k].childNodes[12].text=="1"){

//						document.getElementById("first_second_Day").style.display="";
						}
						else{

						}
						
						
					}
					
					}
					else{
						document.getElementById("halfday").style.display="none";
//						document.getElementById("first_second_Day").style.display="none";
					}

						document.getElementById("sanction_TR").style.display="";
						document.getElementById("sanction_days").style.display="";
						document.getElementById("sanction_days1").style.display="";
						document.getElementById("sanction_days").innerHTML=leave_other_dtl[k].childNodes[6].text;
						document.getElementById("leavetype").style.display="";
						document.getElementById("leavetype_lbl").innerHTML=": " +frm.natureofleave.options[leave_other_dtl[k].childNodes[9].text].innerText;

						
					if(leave_other_dtl[k].childNodes[2].text!="null")
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sanc_fromdate"));
					else
						document.getElementById("sanc_fromdate").innerHTML="-";
					if(leave_other_dtl[k].childNodes[2].text!="null")
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sanc_todate"));
					else
						document.getElementById("sanc_todate").innerHTML="-";

	//					document.getElementById("halfday").style.display="";
					}else{
						// code for combinational leave
						document.getElementById("sanction_TR").style.display="none";
						document.getElementById("sanction_days").style.display="none";
						document.getElementById("sanction_days1").style.display="none";

						//showbutton(frm.combinationleave[0]);
						leave_other_dtl[k].childNodes[0].text
						//alert("From Date ::"+ DDMMYYYY(leave_other_dtl[k].childNodes[0].text) + ":"+ "ToDate :" +  DDMMYYYY(leave_other_dtl[k].childNodes[1].text));
						var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
						leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
						frm.leavedtls.value=leave_dtl;

						document.getElementById("lvDtl").style.display="";
						addRow_display(0);
						leaveArr_disp[1]="1";
						document.getElementById("displayTable").style.display="";
			
					if(leave_other_dtl[k].childNodes[2].text!="null"){
					FormatDDMMYYYY(leave_other_dtl[k].childNodes[2].text,document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text));
					}else
					{
						document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[3].text!="null"){
						FormatDDMMYYYY(leave_other_dtl[k].childNodes[3].text,document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text));

					}else{
					
						document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[6].text!="0.0"){
						document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text).innerHTML=leave_other_dtl[k].childNodes[5].text;
					}else
					{
						document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
	
					}
					

				}	
			disp_counter=1;		

//populateAttachmentForEditing(appDesXML[1]);
	

}











function populateCancelApproval(frm,ajaxKey,status) 
	{ 


		    var re5digit=/.5/ //regular expression defining a 5 digit number
				counter =1;
			   	var xmlDoc=ajaxKey;
//		    	alert(xmlDoc);
//		    	var xmlDOM=getDOMFromXML(xmlDoc);	

				var appDesXML = xmlDoc.split('$$$ATTACHMENT_XML$$$');	
		    	var xmlDOM=getDOMFromXML(appDesXML[0]);	
	    		var leave_other_dtl="";
	    		var leaveDetail="";
	    		var comb_leave="";
				var leave_status="";
				var childs;					
		    	leaveDetail = xmlDOM.getElementsByTagName('LEAVE_DETAIL');      // getting an element from XML file				    	
				comb_leave = xmlDOM.getElementsByTagName('COMB_FLAG');      // getting an element from XML file				    	
				leave_status = xmlDOM.getElementsByTagName('STATUS');      // getting an element from XML file				    	
				leave_other_dtl = xmlDOM.getElementsByTagName('LEAVE_OTHER_DTL');

				frm.combinationleave.value=comb_leave[0].childNodes[0].text;
				frm.leaveid.value=leaveDetail[0].childNodes[0].text;
				frm.userId.value=leaveDetail[0].childNodes[19].text;
				//alert(leaveDetail[0].childNodes[19].text);

				// not a combination leave
					
				document.getElementById("natureofleave").style.display="";
				document.getElementById("lvdtl").style.display="none";
				var erase="";
				document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
				leaveArr=null;
				leaveArr=new Array();
				document.getElementById("appliedBetween").innerText=": "+ DDMMYYYY(leaveDetail[0].childNodes[8].text) +" " +labels[10]+" "+ DDMMYYYY(leaveDetail[0].childNodes[9].text);
				
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
					document.getElementById("noofdays").value=leave_other_dtl[k].childNodes[5].text;


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
							FormatDDMMYYYY(leave_other_dtl[k].childNodes[3].text,document.getElementById("sanc_todate"));
					else
						document.getElementById("sanc_todate").innerHTML="-";
					}else{

						document.getElementById("natureofleave").style.display="none";
					
					// code for combinational leave
					document.getElementById("sanction_TR").style.display="none";
					document.getElementById("sanction_days").style.display="none";
					document.getElementById("sanction_days1").style.display="none";

					var leave_dtl=leave_other_dtl[k].childNodes[8].text+"_"+leave_other_dtl[k].childNodes[9].text+"~"+DDMMYYYY(leave_other_dtl[k].childNodes[0].text)+"~";
					leave_dtl+=DDMMYYYY(leave_other_dtl[k].childNodes[1].text)+"~"+leave_other_dtl[k].childNodes[5].text;
					
					frm.leavedtls.value=leave_dtl;
					
					addRow();	
					var	str="_";
					if(leave_other_dtl[k].childNodes[2].text!="null"){
												
					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[2].text,document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text));
					}else
					{
							
					document.getElementById("sancFrom_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[3].text!="null"){

					FormatDDMMYYYY_WO_COLON(leave_other_dtl[k].childNodes[3].text,document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text));

					}else{

					document.getElementById("sancTo_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					if(leave_other_dtl[k].childNodes[6].text!="0.0"){

					document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML=leave_other_dtl[k].childNodes[5].text;
					}else
					{

					document.getElementById("sancDays_"+leave_other_dtl[k].childNodes[8].text+str+leave_other_dtl[k].childNodes[9].text).innerHTML='-';
					}
					}
	
	
						if(status=='1'|| status=='2')
					{
					document.getElementById("TDbuttons").innerHTML="<b>" +labels[9] +"</b>";
					document.getElementById("balance_hdr").style.display="none";
					document.getElementById("leaveType_hdr").style.display="none";
					document.getElementById("balance_values").style.display="none";
					}else if(status=='0'){
						document.getElementById("TDbuttons").style.display="";
						document.getElementById("balance_hdr").style.display="";
						document.getElementById("leaveType_hdr").style.display="";
						document.getElementById("balance_values").style.display="";
					}
					else{

					document.getElementById("balance_hdr").style.display="none";
					document.getElementById("leaveType_hdr").style.display="none";
					document.getElementById("balance_values").style.display="none";
					}
							

				}	
				
						
}










function populateAttachmentForEditing(decXML)
{
		//alert("string ::: " + decXML);
		deleteAllRowsFromTable(document.getElementById('myTableLeaveAttachment'));

//		var xmlDOMReturn = getDOMFromXML(decXML);

		var xmlDOM = getDOMFromXML(decXML);
		//alert('in 4 attachment');	
		if(xmlDOM!=null)
		{		
			var attachmentName = xmlDOM.getElementsByTagName('attachmentName')[0].childNodes[0].text;
		    var entries = xmlDOM.getElementsByTagName('void');		 
		    
			var propName;
			var fileName;
			var fileDescription;
			var viewURL;
			
			//removeRowFromTableattachmentBiometric(0);    
			
			for (var i = 0; i < entries.length; i++)  
			{    			
				fileName = entries[i].childNodes[0].text;
				fileDescription = entries[i].childNodes[1].text;
				viewURL = entries[i].childNodes[2].text;
				viewURL = viewURL.replace("$","&");
				viewURL = viewURL.replace("$","&");
				var j = i + 1;
				var insertRow = 'insRowForAdd' + attachmentName + '(" '+  fileName + ' ","'+fileDescription+'","'+j+'",true,"'+viewURL+'") ';
				eval(insertRow);
				//insRowForAddattachmentBio(fileName,fileDescription,i+1,true,viewURL);
			}

		}
		
   
}



				function halfDayFacility(leave_other_dtl){

					for(k=0;k<leave_other_dtl.length;k++){
	//				alert(leave_other_dtl[k].childNodes[12].text);
	
						document.getElementById("halfday").style.display="";
						//

						if(leave_other_dtl[k].childNodes[12].text=="1_1"){
						document.getElementById("halfday_radio").innerText=": "+labels[4];
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML="<b>" +labels[8] +" "+  FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text)+"</b>";
						document.getElementById("lastday_halfday").innerHTML="<b>" +labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text)+"</b>";
						document.getElementById("first_halfday_rad").innerHTML=": "+labels[4];
						document.getElementById("last_halfday_rad").innerHTML=": "+labels[4];
						document.getElementById('before2_radio_firstday').innerHTML="<b>"+labels[6]+"</b>";
						document.getElementById('before2_radio_lastday').innerHTML="<b>"+labels[7]+"</b>";
						}
						else if (leave_other_dtl[k].childNodes[12].text=="1_0"){
						document.getElementById("halfday_radio").innerText=": "+labels[4];						
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML="<b>" +labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text)+"</b>";
						document.getElementById("lastday_halfday").innerHTML="<b>" +labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text)+"</b>";
						document.getElementById("first_halfday_rad").innerHTML=": "+labels[4];
						document.getElementById("last_halfday_rad").innerHTML=": "+labels[5];

						document.getElementById('before2_radio_firstday').innerHTML="<b>"+labels[6]+"</b>";
						document.getElementById('before2_radio_lastday').innerHTML="";
						}

						else if (leave_other_dtl[k].childNodes[12].text=="0_1"){
						document.getElementById("halfday_radio").innerText=": "+labels[4];
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById("First_Day").style.display="";
						document.getElementById("Last_Day").style.display="";
						document.getElementById("firstday_halfday").innerHTML="<b>" +labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[0].text)+"</b>";
						document.getElementById("lastday_halfday").innerHTML="<b>" +labels[8] +" "+ FormatDDMMYYYY_WO_COLON_Ver2(leave_other_dtl[k].childNodes[1].text)+"</b>";
						document.getElementById("first_halfday_rad").innerHTML=": "+labels[5];
						document.getElementById("last_halfday_rad").innerHTML=": "+labels[4];

						document.getElementById('before2_radio_firstday').innerHTML="";
						document.getElementById('before2_radio_lastday').innerHTML="<b>"+labels[7]+"</b>";
						}
						else if (leave_other_dtl[k].childNodes[12].text=="0_0" || leave_other_dtl[k].childNodes[12].text=="null"  ){
						document.getElementById("halfday_radio").innerText=": "+labels[4];						
						document.getElementById("halfday").style.display="none";
						document.getElementById("First_Day").style.display="none";
						document.getElementById("Last_Day").style.display="none";
						document.getElementById("firstday_halfday").innerHTML="";
						document.getElementById("lastday_halfday").innerHTML="";
						document.getElementById('before2_radio_firstday').innerHTML="";
						document.getElementById('before2_radio_lastday').innerHTML="";
						document.getElementById("first_halfday_rad").innerHTML=": "+labels[5];
						document.getElementById("last_halfday_rad").innerHTML=": "+labels[5];

						}
					else{

						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_lbl').style.display="none";
						document.getElementById('before2_radio').style.display="none";
						document.getElementById('First_Day').style.display="none";
						document.getElementById('Last_Day').style.display="none";
					}
		}
	}



function checkHalfDay2()
{
 var nod=0;
 var  natureofleave=document.frmleaveapply.natureofleave.value.split('_');
	if(document.frmleaveapply.fromdate.value!="" &&  document.frmleaveapply.todate.value!=""){
			if(natureofleave[0]=='1'){
				nod=NoOfDaysFromTwoDates(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply,alertMsgDates);
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
			nod=NoOfDaysFromTwoDates(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply,alertMsgDates);
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
				nod=dateDifferenceWOAlert(new Date(Date1),new Date(Date2),alertMsgDates);
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


/********************/
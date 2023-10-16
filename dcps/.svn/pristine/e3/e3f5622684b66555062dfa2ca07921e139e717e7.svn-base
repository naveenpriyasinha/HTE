	function hidden(flag){
	
		if(flag==1){
			document.frmPayInc.NO.status=false;
			document.frmPayInc.userComp.checked=false;
			document.frmPayInc.SystemComp.checked=false;
			document.getElementById("syscomp").style.display="none";
			document.getElementById("defferd").style.display="";
			chDate();
		    
		
		
		}
		else
		{	
		
			document.frmPayInc.userComp.checked=false;
			
			var lwp =document.getElementById("efelwp").value;
		    var edate=document.getElementById("noeffdate").value;
		    var Edate =new Date(edate);
		    document.getElementById("Lwp").value=lwp;
			document.frmPayInc.YES.status=false;
			document.frmPayInc.SystemComp.checked=true;
		     document.frmPayInc.Systemdate.disabled =false;
		    document.getElementById("Sysfixedate").style.backgroundColor='white';
			document.frmPayInc.SystemBasic.disabled = false;
			 document.getElementById("Sysfixed").style.backgroundColor='white';
			 document.frmPayInc.fromdate.value="";
		    document.frmPayInc.payfixed.value="";  
			document.frmPayInc.payfixed.disabled=true;
	        document.getElementById("payFix").style.backgroundColor='CCCCCC';
		    document.frmPayInc.explanation.disabled=true;
		    document.getElementById("explanation").style.backgroundColor='CCCCCC';
			document.getElementById("syscomp").style.display="";
			document.getElementById("defferd").style.display="none";
			document.getElementById("effectdate").innerHTML="";
	        document.getElementById("effectdate").innerHTML=Edate.getDate()+"/"+eval(Edate.getMonth()+1) +"/"+Edate.getYear();
		    document.frmPayInc.fromdate.disabled=true;
		    document.frmPayInc.fromdate.style.backgroundColor='CCCCCC'
		
		}
	}
	
	
	function validate()
	{
	if(document.frmPayInc.YES.checked==true)
	
	{
	var UserexplanationDffd=document.getElementById("dexplanation").value
	
	if(UserexplanationDffd.length==0)
	{
	
	alert("explation note is mandatory");
	}
	else {
	var lwp = document.getElementById("Lwp").value;
	if(isNaN(lwp)||(lwp.length==0)){
	alert("enter valid number fro LWP");
	}
	else{
	alert("you have selected  Deferred mode");
	document.frmPayInc.action="hrms.htm?actionFlag=Update";
	document.frmPayInc.submit();
	}}}
	if(document.frmPayInc.userComp.checked==true)
	{
	
	var Usercompay=document.getElementById("payFix").value
	var Userexplanation=document.getElementById("explanation").value
	var nxtincdate =document.getElementsByName("fromdate").value
	 
	
	   
	if(Usercompay.length==0){
	alert("enter salary");
	}
	else{
	if (isNaN(Usercompay))
	 {
	 alert("The salary must be number");
	
	 }
	}
	if(Userexplanation.length==0)
	{
	alert("explation note is mandatory");
	}
	else{
	alert("you have selected User Computed mode");
	document.frmPayInc.action="hrms.htm?actionFlag=Update";
	document.frmPayInc.submit();
	}
	}
	
	if(document.frmPayInc.SystemComp.checked==true)
	{
	alert("you have selected System computed");
	document.frmPayInc.action="hrms.htm?actionFlag=Update";
	document.frmPayInc.submit();
	}
	
	}
	
	function changeSystem()
	{    
		if(document.frmPayInc.SystemComp.checked==true)
		{ 
		   // document.frmPayInc.accept.checked=false;
		    document.frmPayInc.userComp.checked=false;
			document.frmPayInc.payfixed.disabled=true;
			document.frmPayInc.payfixed.value="";
	        document.getElementById("payFix").style.backgroundColor='CCCCCC';
		    document.frmPayInc.explanation.disabled=true;
		    document.getElementById("explanation").style.backgroundColor='CCCCCC';
		    document.frmPayInc.SystemBasic.disabled = false;
		    document.frmPayInc.SystemBasic.disabled = false;
		    document.frmPayInc.fromdate.disabled=true;
		    document.frmPayInc.fromdate.style.backgroundColor='CCCCCC'
		    document.getElementById("Sysfixed").style.backgroundColor='white';
		    document.frmPayInc.Systemdate.disabled =false;
		    document.getElementById("Sysfixedate").style.backgroundColor='white';
		    document.frmPayInc.fromdate.value="";
		    document.frmPayInc.payfixed.value="";  
		    }
		else
		{
			document.frmPayInc.SystemBasic.disabled=true;	
				
		}
	}
	
	
	function chDate(){
	var effcvdatee =null;
	var lwp = document.getElementById("Lwp").value;
	if(isNaN(lwp)||(lwp.length==0)){
	alert("enter valid number fro LWP");
	} 
	else{
	
	var dateee=document.frmPayInc.actIncDate1.value;
	var dateArr;
	dateArr=dateee.split('/');
	var formattedDate= dateArr[2]+'/'+dateArr[1]+'/'+dateArr[0];
	
	
	var roundDate =01;
	var dateeact=new Date(formattedDate);
	
	
	
	var millsec=dateeact.getTime();
	
	var milisec1=86400000*lwp;
	var time =millsec + milisec1;
	var newdate=new Date(time);
	
	
	var effcvdate =  newdate.getDate();
	var effecmnt=newdate.getMonth();
	var effecyear=newdate.getYear();
	
	if(effcvdate>15){
	var totaldays = 32 - new Date(effecyear, effecmnt, 32).getDate();
	
	
	
	var diffdays=totaldays-effcvdate;
	
	var latestdate =newdate.setDate(newdate.getDate()+diffdays+1);
	var newdatelwp=new Date(latestdate);
	
	
	
	
	
	var effcvdatee=newdatelwp.getDate()+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	
	document.getElementById("effectdate").innerHTML="";
	document.getElementById("effectdate").innerHTML=newdatelwp.getDate()+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	document.frmPayInc.dffdincdate.value=newdatelwp.getDate()+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	var edate=newdatelwp.getDate()+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	alert("effective date of increment ="+edate);
	}
	else
	{
	
	var effcvdatee=(eval(roundDate) +"/"+eval(eval(newdate.getMonth())+1) +"/"+ newdate.getYear());
	
	document.getElementById("effectdate").innerHTML="";
	document.getElementById("effectdate").innerHTML= (01 +"/"+eval(eval(newdate.getMonth())+1) +"/"+ newdate.getYear());
	document.frmPayInc.dffdincdate.value=(01 +"/"+eval(eval(newdate.getMonth())+1) +"/"+ newdate.getYear());
	var edate=(01 +"/"+eval(eval(newdate.getMonth())+1) +"/"+ newdate.getYear());
	alert("effective date of increment ="+edate);
	}
	var salary =document.getElementById("Sysfixed").value;
	
	document.getElementById("payFix").value=salary;
	
	
	
	
	
	
	}
	  
	 }
	function forLWP(){
	
	
	document.frmPayInc.YES.checked=true;
	
	if(document.frmPayInc.YES.checked==true)
	{
	
	document.frmPayInc.userComp.checked=false;
	document.frmPayInc.SystemComp.checked=false;
	hidden(1);
	}
	
	}
	function changeUser()
	{   
		if(document.frmPayInc.userComp.checked==true)
		{  
		  //  document.frmPayInc.accept.checked=false;
		    document.frmPayInc.SystemComp.checked=false;
		    document.frmPayInc.SystemBasic.disabled =false;
		    document.getElementById("Sysfixed").style.backgroundColor='CCCCCC';
		    document.frmPayInc.Systemdate.disabled =false;
		    document.getElementById("Sysfixedate").style.backgroundColor='CCCCCC';
		    document.frmPayInc.payfixed.disabled=false;
		    document.getElementById("payFix").style.backgroundColor='white';
		    document.getElementById("explanation").style.backgroundColor='white';
		    document.getElementById("explanation").disabled=false;
		    document.frmPayInc.fromdate.disabled=false;
		    document.frmPayInc.fromdate.style.backgroundColor='white';
		    var usdate= document.frmPayInc.Systemdate.value;
		    //var userdate=new Date(usdate);
		    document.frmPayInc.payfixed.value=document.frmPayInc.SystemBasic.value;
		    document.frmPayInc.fromdate.value=usdate;
		}
		else
		{
			document.frmPayInc.SystemBasic.disabled=true;	
		
		}
	
	
	
	
	
	}
	function erase(){
	
	 document.frmPayInc.dexplanation.value="";
	
	
	document.frmPayInc.explanation.value="";
	}
	
	
	function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit){ // if too long...trim it!
	field.value = field.value.substring(0, maxlimit);
	alert("Max limit Of 100 words allowed");
	
	}
	// otherwise, update 'characters left' counter
	else 
	countfield.value = maxlimit - field.value.length;
	}
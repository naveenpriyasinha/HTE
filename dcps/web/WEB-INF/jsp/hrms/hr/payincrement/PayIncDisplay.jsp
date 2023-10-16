
<%
try {
%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>

<fmt:setBundle basename="resources.hr.payincrement.PayInc" var="pyinc"
	scope="request" />



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<!-- This ActionList Come From PayIncrementServiceImpl -->
<c:set var="actionList" value="${resValue.actionList}" />
<c:set var="eisPayincTran" value="${resValue.eisPayincTran}">
</c:set>
<c:set var="empInfoVO" value="${resValue.empInfoVO}">
</c:set>

<script type="text/javascript">
function DDMMYYYY(date){
var arr=date.split("-");
return arr[2]+"/"+arr[1]+"/"+arr[0];
}


function Button()
{
var appflag = document.frmPayInc.appflag.value;
 if(appflag==('F'))
 {

 document.getElementById("status1").style.display="none";

}
else if(appflag==('A')){
 
 document.getElementById("status1").style.display="";
}
else if(appflag==('D')){
 
 document.getElementById("status1").style.display="";
}
else if(appflag==('DF')){

 document.getElementById("status1").style.display="none";
 }
else
{
document.getElementById("status1").style.display="none";
}



}

function status()

{


var appflag = document.frmPayInc.appflag.value;
var comflag=document.frmPayInc.comflag.value;

if(comflag==('Deferred')){
document.frmPayInc.LWP.disabled=true;


            Button();
            document.frmPayInc.YES.checked=true;
            document.frmPayInc.NO.checked=false;
            document.getElementById("syscomp").style.display="none";
            document.getElementById("defferd").style.display="";
            document.frmPayInc.YES.disabled =true;
			document.frmPayInc.NO.disabled =true;
			document.frmPayInc.SystemComp.disabled =true;
			document.frmPayInc.userComp.disabled =true;
		    document.frmPayInc.dexplanation.value=document.frmPayInc.rem.value;
            document.frmPayInc.dffdincdate.value=DDMMYYYY(document.frmPayInc.dte.value); 
            document.frmPayInc.dffdincdate.disabled=true;
            document.frmPayInc.dexplanation.disabled=true;
}
else 
if(comflag=='UserComp')
{
document.frmPayInc.LWP.disabled=true;


            document.frmPayInc.YES.disabled =true;
			document.frmPayInc.NO.disabled =true;
			document.frmPayInc.SystemComp.disabled =true;
			document.frmPayInc.userComp.checked =true;
			document.frmPayInc.userComp.disabled =true;
            document.frmPayInc.SystemComp.checked=false;
		    document.frmPayInc.SystemBasic.disabled =false;
		    document.getElementById("Sysfixed").style.backgroundColor='CCCCCC';
		    document.frmPayInc.Systemdate.disabled =false;
		    document.getElementById("Sysfixedate").style.backgroundColor='CCCCCC';
		    document.frmPayInc.payfixed.disabled=true;
		    document.getElementById("payFix").style.backgroundColor='white';
		    document.getElementById("explanation").style.backgroundColor='white';
		    document.getElementById("explanation").disabled=true;
		    document.frmPayInc.fromdate.disabled=true;
		    document.frmPayInc.fromdate.style.backgroundColor='white';
		    Button();
		    document.frmPayInc.fromdate.value=DDMMYYYY(document.frmPayInc.ute.value); 
		    document.frmPayInc.payfixed.value= document.frmPayInc.sal.value;
		    document.frmPayInc.explanation.value=document.frmPayInc.rem.value;
}
else if(comflag=='SysComp'){
document.frmPayInc.LWP.disabled=true;

 
            document.frmPayInc.userComp.checked=false;
			document.frmPayInc.payfixed.disabled=true;
			document.frmPayInc.YES.disabled =true;
			document.frmPayInc.NO.disabled =true;
			document.frmPayInc.SystemComp.disabled =true;
			document.frmPayInc.userComp.disabled =true;
			Button();
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
		    }

    else{
    // document.getElementById("status").style.display="none";
    
    }

}


	function hidden(flag){
	
		if(flag==1){
			document.frmPayInc.NO.status=false;
			document.frmPayInc.userComp.checked=false;
			document.frmPayInc.SystemComp.checked=false;
			document.getElementById("syscomp").style.display="none";
			document.getElementById("defferd").style.display="";
			//chDate();
		   
		}
		else
		{	
		
		   document.frmPayInc.userComp.checked=false;
			
			var lwp =document.getElementById("efelwp").value;
		    var edate=document.getElementById("noeffdate").value;
		   
		    //var Edate =new Date(edate);
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
			//document.getElementById("effectdate").innerHTML="";
	        //document.getElementById("effectdate").innerHTML=edate;
		    document.frmPayInc.fromdate.disabled=true;
		    document.frmPayInc.fromdate.style.backgroundColor='CCCCCC'
		    //chDate();
		    
		}
	}
	
function forward()
	
	{

	if(document.frmPayInc.YES.checked==true)
	
	{
	dodacheck(document.getElementById('dexplanation'));
	var UserexplanationDffd=document.getElementById("dexplanation").value
	var deffrdte=document.frmPayInc.dffdincdate.value;
	if(UserexplanationDffd.length==0)
	{
	////alert('<fmt:message  bundle="${pyinc}" key="payinc.explnote"/>');
	alert('<fmt:message  bundle="${pyinc}" key="payinc.alert3"/>');
	return false;
	}
	else 
	if(deffrdte.length==0)
	{
	alert('<fmt:message  bundle="${pyinc}" key="payinc.nxtincdte"/>');
	return false;
	}
	
	else {
	var lwp = document.getElementById("Lwp").value;
	if(isNaN(lwp)||(lwp.length==0)){
	alert('<fmt:message  bundle="${pyinc}" key="payinc.alert4"/>');
	return false;
	}
	else{
	alert('<fmt:message  bundle="${pyinc}" key="painc.alert5"/>');
	return true;
	}}}
	if(document.frmPayInc.userComp.checked==true)
	{
	dodacheck(document.getElementById('explanation'));
	var Usercompay=document.getElementById("payFix").value;
	var Userexplanation=document.getElementById("explanation").value;
	var nxtincdate = document.frmPayInc.fromdate.value;
	 
	
	   
	if(Usercompay.length==0){
alert('<fmt:message  bundle="${pyinc}" key="painc.alert6"/>');
return false;
	}
	else
	if (isNaN(Usercompay))
	 {
	alert('<fmt:message  bundle="${pyinc}" key="painc.alert7"/>');
	return false;
	 }
	else
	if(Userexplanation.length==0)
	{
alert('<fmt:message  bundle="${pyinc}" key="payinc.alert3"/>');	}
	else 
	if(nxtincdate.length==0)
	{
	alert('<fmt:message  bundle="${pyinc}" key="payinc.nxtincdte"/>');
	return false;
	}
	
	
	
	else{
alert('<fmt:message  bundle="${pyinc}" key="painc.alert8"/>');

 return true;
	}
	}
	
	if(document.frmPayInc.SystemComp.checked==true)
	{
alert('<fmt:message  bundle="${pyinc}" key="painc.alert9"/>');
	return true;
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
    alert('<fmt:message  bundle="${pyinc}" key="payinc.alert4"/>');
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

	var datem=eval(newdatelwp.getMonth()+1);
	if(datem<10){
		document.frmPayInc.dffdincdate.value="01"+"/"+"0"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	
	}
	
	else{
	document.frmPayInc.dffdincdate.value="01"+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	
	}
	
	var effcvdatee=newdatelwp.getDate()+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	
	document.getElementById("effectdate").innerHTML="";    
	document.getElementById("effectdate").innerHTML="01"+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
  
  
	
   
	var edate=newdatelwp.getDate()+"/"+eval(newdatelwp.getMonth()+1) +"/"+newdatelwp.getYear();
	
	}
	
	else
	{
	
	var effcvdatee=(eval(roundDate) +"/"+eval(eval(newdate.getMonth())+1) +"/"+ newdate.getYear());
	
	document.getElementById("effectdate").innerHTML="";
	document.getElementById("effectdate").innerHTML= ("01" +"/"+eval(newdate.getMonth()+1) +"/"+ newdate.getYear());
	var datem=eval(newdate.getMonth()+1);
	if(datem<10){
	document.frmPayInc.dffdincdate.value=("01" +"/"+"0"+eval(newdate.getMonth()+1) +"/"+ newdate.getYear());
	
	}
	else{
	
 	document.frmPayInc.dffdincdate.value=("01" +"/"+eval(newdate.getMonth()+1) +"/"+ newdate.getYear());
	}
  
	var edate=(01 +"/"+eval(eval(newdate.getMonth())+1) +"/"+ newdate.getYear());
	
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
function editt()
{

status();
document.frmPayInc.YES.disabled =false;
document.frmPayInc.NO.disabled =false;
document.frmPayInc.dffdincdate.disabled=false;
document.frmPayInc.dexplanation.disabled=false;
document.frmPayInc.userComp.disabled =false;
document.getElementById("explanation").disabled=false;
document.frmPayInc.fromdate.disabled=false;
document.frmPayInc.payfixed.disabled=false;
document.frmPayInc.LWP.disabled=false;
document.frmPayInc.LWP.disabled=false;

document.frmPayInc.SystemComp.disabled=false;
}
	
	
	
	
	

	
	
	
	function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit){ // if too long...trim it!
	field.value = field.value.substring(0, maxlimit);

	alert("Max limit Of 100 words allowed");
	
	}
	// otherwise, update 'characters left' counter
	else {
	countfield.value = maxlimit - field.value.length;
	}
	}
	
	
	var mikExp = /[$\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\=\|]/;
function dodacheck(val) {
var strPass = val.value;
var strLength = strPass.length;
var lchar = val.value.charAt((strLength) - 1);
if(lchar.search(mikExp) != -1) {
var tst = val.value.substring(0, (strLength) - 1);
val.value = tst;
   }
}
</script>

<html>

<hdiits:form name="frmPayInc" validate="true" method="POST"
	action="./hrms.htm?actionFlag=recforwd" encType="text/form-data">
	<hdiits:hidden name="rem" default="${eisPayincTran.remarks}" id="rem" />
		<hdiits:hidden name="sal" default="${eisPayincTran.userComSalary}" id="sal" />
		<hdiits:hidden name="dte" default="${eisPayincTran.defferedDate}" id="dte" />
		<hdiits:hidden name="ute" default="${eisPayincTran.userComNxtincDate}" id="ute" />
		<hdiits:hidden name="comflag" default="${eisPayincTran.compFlag}"
			id="comflag" />
		<hdiits:hidden name="appflag" default="${eisPayincTran.approvalFlag}"
			id="appflag" />
		<hdiits:hidden name="empid" default="${eisPayincTran.orgUserMst.userId}" />
		<hdiits:hidden name="reqid" default="${eisPayincTran.reqTranId}" />
		<hdiits:hidden name="noeffdate" id="noeffdate"
			default="${empInfoVO.formatedeffDate}" />
		<hdiits:hidden name="efelwp" id="efelwp"
			default="${empInfoVO.lwp}" />
<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br><br>
<hdiits:fieldGroup titleCaptionId="payinc.dtls" bundle="${pyinc}" mandatory="true" >
	

	<table width="100%">

		<tr>
			<td><b><hdiits:caption captionid="payinc.service status"
				bundle="${pyinc}"></hdiits:caption></b></td>
			<td width="25%">Y</td>
			<td width="25%"><b><hdiits:caption
				captionid="payinc.present pay scale" bundle="${pyinc}"></hdiits:caption></b></td>
			<td><c:out value="${empInfoVO.payscale}" default=""></c:out></td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="payinc.last inc date"
				bundle="${pyinc}" /></b></td>
			<td width="25%"><fmt:formatDate
				value="${eisPayincTran.lastIncDate}" pattern="dd/MM/yyyy" /></td>
			<td width="25%"><b><hdiits:caption captionid="payinc.lwp"
				bundle="${pyinc}" /></b></td>


			<td><INPUT name="LWP" id="Lwp" type="text"
				value="${empInfoVO.lwp}" width="5%" size="5%"
				onfocus="forLWP();" onchange="chDate();" readonly="readonly"  /></td>
		</tr>


		<tr>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption
				captionid="payinc.act inc date" bundle="${pyinc}" /></b></td>
			<td id="actualDate"><fmt:formatDate var="actIncDate1"
				value="${eisPayincTran.actIncDate}" pattern="dd/MM/yyyy" /> <c:out
				value="${actIncDate1}"></c:out> <hdiits:hidden name="actIncDate1"
				id="actIncDate1" default="${actIncDate1}" /></td>
			<td><b><hdiits:caption captionid="payinc.effcdt"
				bundle="${pyinc}"></hdiits:caption></b></td>
			<td id="effectdate"><fmt:formatDate var="effectdate1"
				value="${empInfoVO.effectiveincdate}" pattern="dd/MM/yyyy" /> <c:out
				value="${effectdate1}"></c:out> <hdiits:hidden name="effectdate1"
				id="effectdate1" default="${effectdate1}" />
		</tr>
		<tr height="25%">

		</tr>


		<tr height="25%">

		</tr>

		<tr align="left">
			<td><u><b><hdiits:caption
				captionid="payinc.deffComRadio" bundle="${pyinc}" /></b></u><hdiits:radio
				name="YES" value="5" captionid="payinc.YES" bundle="${pyinc}"
				onclick="hidden(1);" default="" /> <hdiits:radio name="NO"
				value="2" captionid="payinc.NO" bundle="${pyinc}"
				onclick="hidden(2);" default="" /></td>
		</tr>

	</table>

<br>
	<table id="syscomp">
		<tr>
			<td width="20%"><b> <hdiits:radio name="SystemComp"
				value="1" mandatory="true" captionid="payinc.syscomp"
				bundle="${pyinc}" onclick="changeSystem();" default="" /></b></td>
		</tr>

		<tr>
			<td width="24%"><b><hdiits:caption
				captionid="payinc.PAY FIXED" bundle="${pyinc}" /></b></td>
			<td><INPUT name="SystemBasic" type="text"
				value="${empInfoVO.salary}" readonly="readonly" size="10%"
				id="Sysfixed" /></td>

			<td width="25%"><b><hdiits:caption
				captionid="payinc.Nxt inc date" bundle="${pyinc}" /></b></td>
			<td><INPUT type="text" name="Systemdate"
				value="<fmt:formatDate value="${empInfoVO.nxtincdate}"	pattern="dd/MM/yyyy" />"
				readonly="readonly" size="10%" id="Sysfixedate" /></td>
		</tr>

		<tr>
			<td><b> <hdiits:radio name="userComp" value="4"
				mandatory="true" captionid="payinc.usercomp" bundle="${pyinc}"
				onclick="changeUser();" default="" /></b></td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="payinc.PAY FIXED"
				bundle="${pyinc}" /></b></td>
			<td><hdiits:number name="payfixed" size="10%" id="payFix" /></td>


			<td><b><hdiits:caption captionid="payinc.Nxt inc date"
				bundle="${pyinc}" /></b></td>
			<td><hdiits:dateTime name="fromdate"
				captionid="payinc.exp" bundle="${pyinc}" default="" maxvalue="31/12/2099" /></td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="payinc.exp"
				bundle="${pyinc}" /></b></td>
			<td><hdiits:textarea name="explanation" mandatory="true"
				id="explanation" cols="32" rows="6"  captionid="payinc.exp" bundle="${pyinc}"  onkeypress="dodacheck(this)"></hdiits:textarea></td>
		</tr>
	</table>


	<table id="defferd">

		<tr>
			<td width="25%"><b><hdiits:caption captionid="payinc.exp"
				bundle="${pyinc}" /></b></td>
			<td><hdiits:textarea name="dexplanation" mandatory="true"
				id="dexplanation" cols="28" rows="6"  captionid="payinc.exp" bundle="${pyinc}"  onkeypress="dodacheck(this)"></hdiits:textarea></td>
			<td></td>
		
			<td></td>

			<td width="25%"><b><hdiits:caption
				captionid="payinc.dffdincdate" bundle="${pyinc}" /></b></td>
			<td width="25%"><hdiits:dateTime name="dffdincdate"
				captionid="payinc.dffdincdate" bundle="${pyinc}" default="${eisPayincTran.defferedDate} 00:00:00"  maxvalue="31/12/2099"/></td>
		</tr>

	</table>

</hdiits:fieldGroup>

	
	
	<table align="center" id="status1">
		<tr>

			<td width="33%" align="center"><font class="Label3"
				align="center" color="red"><b><hdiits:caption
				captionid="payinc.status" bundle="${pyinc}" captionLang="single"/></b></font></td>
		</tr>


	</table>
	

	<script>Button(); </script>
				
	<script>document.frmPayInc.NO.status=true;
				 	hidden(2);
				 </script>
	<script>
	editt();
				 </script>

	<hdiits:jsField name="Dtlssubmit" jsFunction="forward()"/>

	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />


</hdiits:form>

</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
		
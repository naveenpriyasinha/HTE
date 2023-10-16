<%
try {
	
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>


<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>
<fmt  pattern="dd/MM/yyyy"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trans" value="${resValue.trans}"> </c:set>
<c:set var="EmpDetVO1" value="${resValue.EmpDet123}" />
<c:set var="date" value="${resValue.date}" />
<c:set var="nxtdate" value="${resValue.nxtdate}" />
<c:set var="modgs" value="${resValue.modgs}" />
<c:set var="designationMst" value="${resValue.designationMst}" />
<c:set var="reasonlookupMst" value="${resValue.reasonlookupMst}" />

<script type="text/javascript">
function approved(compflag,approve)
{
if(approve=='a')
{
    if(compflag=='s')
    {
     document.getElementById("buttons3").style.display="none";
     document.getElementById("syscomputed").style.display="none";
     document.getElementById("usercomputed").style.display="none";
     document.getElementById("usercomputed1").style.display="none";
     document.getElementById("approvalmsg").style.display="";
    }
    else if(compflag=='u')
    {
    
    
    	document.getElementById("buttons3").style.display="none";
   
    	document.getElementById("syscomputed").style.display="none";
		document.getElementById("syscomputed1").style.display="none";
		document.getElementById("usercomputed").style.display="none";
		document.getElementById("approvalmsg").style.display="";
		document.frmVac.user_nxt_incr_date.disabled=true;
		document.frmVac.explanation.disabled=true;
		document.frmVac.payfixed.disabled=true;
    }
    }
    else if(approve=='p')
    {
    
    if(compflag=='u')
   {
		document.frmVac.UserComp.checked="true";
 		document.frmVac.SystemComp.checked= false;
		document.frmVac.payfixed.disabled=false;
		document.frmVac.user_nxt_incr_date.disabled=false;
		document.frmVac.explanation.disabled=false;
		document.getElementById("payfixed").style.backgroundColor='white';
		document.getElementById("explanation").style.backgroundColor='white';
		document.frmVac.user_nxt_incr_date.style.backgroundColor='white';
		document.getElementById("SystemBasic").style.backgroundColor='CCCCCC';
		document.getElementById("SystemDate").style.backgroundColor='CCCCCC';
		
}
else if(compflag=='s')
{

		document.frmVac.SystemComp.checked="true";
		document.frmVac.payfixed.disabled=true;
		document.getElementById("payfixed").style.backgroundColor='CCCCCC';
		
		document.frmVac.UserComp.checked=false;
		document.frmVac.user_nxt_incr_date.disabled=true;
		document.frmVac.explanation.disabled=true;
          document.frmVac.user_nxt_incr_date.style.backgroundColor='CCCCCC';
		document.getElementById("explanation").style.backgroundColor='CCCCCC';
		document.getElementById("SystemBasic").style.backgroundColor='white';
		document.getElementById("SystemDate").style.backgroundColor='white';
}
    }
else if(approve=='r')
{
document.getElementById("buttons3").style.display="none";
     document.getElementById("syscomputed").style.display="none";
     document.getElementById("usercomputed").style.display="none";
     document.getElementById("usercomputed1").style.display="none";
     
      document.getElementById("rejectmsg").style.display="";

}
}
function changeSystem()
{
	if(document.frmVac.SystemComp.checked==true)
	{
		document.frmVac.payfixed.disabled=true;
		document.getElementById("payfixed").style.backgroundColor='CCCCCC';
		document.frmVac.UserComp.checked=false;
		document.frmVac.user_nxt_incr_date.disabled=true;
		document.frmVac.explanation.disabled=true;
         document.frmVac.user_nxt_incr_date.style.backgroundColor='CCCCCC';
		document.getElementById("explanation").style.backgroundColor='CCCCCC';
		document.getElementById("SystemBasic").style.backgroundColor='white';
		document.getElementById("SystemDate").style.backgroundColor='white';
	
	}
	else
	{
		document.frmVac.SystemBasic.disabled=false;		
	}
}
function changeUser()
{
	if(document.frmVac.UserComp.checked==true)
	{   document.frmVac.SystemComp.checked= false;
		document.frmVac.payfixed.disabled=false;
		document.frmVac.user_nxt_incr_date.disabled=false;
		document.frmVac.explanation.disabled=false;
		document.getElementById("payfixed").style.backgroundColor='white';
		document.getElementById("explanation").style.backgroundColor='white';
		document.frmVac.user_nxt_incr_date.style.backgroundColor='white';
		document.getElementById("SystemBasic").style.backgroundColor='CCCCCC';
		document.getElementById("SystemDate").style.backgroundColor='CCCCCC';
		 
		 
	}
	else
	{
		document.frmVac.payfixed.readonly=true;		
	}
}
function checkedbox(chk)
{

if(chk=='u')
{
		document.frmVac.UserComp.checked="true";
 		document.frmVac.SystemComp.checked= false;
		document.frmVac.payfixed.disabled=false;
		document.frmVac.user_nxt_incr_date.disabled=false;
		document.frmVac.explanation.disabled=false;
		document.getElementById("payfixed").style.backgroundColor='white';
		document.getElementById("explanation").style.backgroundColor='white';
		document.frmVac.user_nxt_incr_date.style.backgroundColor='white';
		document.getElementById("SystemBasic").style.backgroundColor='CCCCCC';
		document.getElementById("SystemDate").style.backgroundColor='CCCCCC';
}
else if(chk=='s')
{

		document.frmVac.SystemComp.checked="true";
		document.frmVac.payfixed.disabled=true;
		document.getElementById("payfixed").style.backgroundColor='CCCCCC';
		document.frmVac.UserComp.checked=false;
		document.frmVac.user_nxt_incr_date.disabled=true;
		document.frmVac.explanation.disabled=true;
         document.frmVac.user_nxt_incr_date.style.backgroundColor='CCCCCC';
		document.getElementById("explanation").style.backgroundColor='CCCCCC';
		document.getElementById("SystemBasic").style.backgroundColor='white';
		document.getElementById("SystemDate").style.backgroundColor='white';
		
}
}
function check()
{
if(document.frmVac.SystemComp.checked==true)
{
document.frmVac.action="hrms.htm?actionFlag=approval";
document.frmVac.submit();
}
if(document.frmVac.UserComp.checked==true)
{
dodacheck(document.getElementById('explanation'));
var pf=document.getElementById("payfixed").value
var expl=document.getElementById("explanation").value

if(pf.length==0)
{

alert('<fmt:message bundle="${PayLab}" key="Pf.enterpayfixed"/>');
setFocusSelection('payfixed');
}
else if(isNaN(pf))
{

alert('<fmt:message bundle="${PayLab}" key="Pf.shldbenm"/>');
setFocusSelection('payfixed');
}
else if(expl.length==0)
{

alert('<fmt:message bundle="${PayLab}" key="Pf.explmand"/>');
setFocusSelection('explanation');
}
else if(document.frmVac.user_nxt_incr_date.value.length==0)
{
alert('<fmt:message bundle="${PayLab}" key="Pf.datemandatory"/>');
setFocusSelection('user_nxt_incr_date');
}
else
{
document.frmVac.action="hrms.htm?actionFlag=approval";
document.frmVac.submit();
}
}
}
function getDate1()
   {
   		var dt= new Date();
   		var dd1=dt.getDate();
   		var mm1=dt.getMonth()+1;
   		var yy1=dt.getYear();
   		var dt1=dd1+'/'+mm1+'/'+yy1;
		document.getElementById("date1").innerHTML=dt1;

   }
function formatDate(date1){
var dateArr;			
dateArr=date1.split('/');
var formattedDate= dateArr[2]+'/'+dateArr[1]+'/'+dateArr[0] ;
return formattedDate;
}
  
   

function insert(){
document.frmVac.action="hrms.htm?actionFlag=transac";
document.frmVac.submit();

}
function reject(){
if(document.frmVac.SystemComp.checked==true)
{
document.frmVac.action="hrms.htm?actionFlag=reject";
document.frmVac.submit();
}
if(document.frmVac.UserComp.checked==true)
{
dodacheck(document.getElementById('explanation'));
var pf=document.getElementById("payfixed").value
var expl=document.getElementById("explanation").value

if(pf.length==0)
{

alert('<fmt:message bundle="${PayLab}" key="Pf.enterpayfixed"/>');
}
else if(isNaN(pf))
{

alert('<fmt:message bundle="${PayLab}" key="Pf.shldbenm"/>');
}
else if(expl.length==0)
{

alert('<fmt:message bundle="${PayLab}" key="Pf.explmand"/>');
}
else if(document.frmVac.user_nxt_incr_date.value.length==0)
{
alert('<fmt:message bundle="${PayLab}" key="Pf.datemandatory"/>');

}
else
{
document.frmVac.action="hrms.htm?actionFlag=reject";
document.frmVac.submit();
}
}
}
function forward(){
if(document.frmVac.SystemComp.checked==true)
{
return true;
}
else if(document.frmVac.UserComp.checked==true)
{
dodacheck(document.getElementById('explanation'));
var pf=document.getElementById("payfixed").value
var expl=document.getElementById("explanation").value

if(pf.length==0)
{

alert('<fmt:message bundle="${PayLab}" key="Pf.enterpayfixed"/>');
setFocusSelection('payfixed');
return false;
}
else if(isNaN(pf))
{

alert('<fmt:message bundle="${PayLab}" key="Pf.shldbenm"/>');
setFocusSelection('payfixed');
return false;
}
else if(expl.length==0)
{

alert('<fmt:message bundle="${PayLab}" key="Pf.explmand"/>');
setFocusSelection('explanation');

return false;
}
else if(document.frmVac.user_nxt_incr_date.value.length==0)
{
alert('<fmt:message bundle="${PayLab}" key="Pf.datemandatory"/>');
setFocusSelection('user_nxt_incr_date');
return false;
}
else
{
return true;
}
}
else
{
return false;
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


<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?actionFlag=submitpayFixation" encType="text/form-data" >


	
	
	
	<hdiits:hidden name="actionFlag" default="empseldate"/>
	<hdiits:hidden name="eid" default="${EmpDetVO1.empId}"/>
<hdiits:hidden name="desigid" default="${EmpDetVO1.desigid}"/>
<hdiits:hidden name="reqid" default="${trans.reqId}"/>
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
  <br>
  <hdiits:fieldGroup titleCaptionId="Pf.fix" bundle="${PayLab}" mandatory="true" id="payfixfieldGrp1">
  
	<br>
	<table width="100%">
	<tr>
	<td width="25%">
	<b><hdiits:caption captionid="Pf.adddate" bundle="${PayLab}"></hdiits:caption></b>
	</td>
	<td width="25%"><fmt:formatDate value="${trans.hrPayFixTxn.applDate}" pattern="dd/MM/yyyy"/></td>
	  <td width="25%">
		<b><hdiits:caption captionid="Pf.prepay" bundle="${PayLab}"></hdiits:caption></b></td>
		   <td width="25%"><c:out  value="${modgs.basicSal}"default=""></c:out>
	       </td>
	       </tr>
	       
		
		
		<tr>
		<td width="25%">
		<b><hdiits:caption captionid="Pf.presentscale" bundle="${PayLab}"></hdiits:caption></b>
	       </td>
	       <td width="25%">
	        <c:out value="${modgs.scaleId.scaleStartAmt}"default=""></c:out><c:out value="-"/><c:out  value="${modgs.scaleId.scaleIncrAmt}"default=""/><c:out value="-"/><c:out  value="${modgs.scaleId.scaleEndAmt}"default=""/>
	       </td>
	       <td width="25%">
		<b><hdiits:caption captionid="Pf.scale" bundle="${PayLab}"></hdiits:caption></b>
	       </td>
	       
	<td width="25%">
	<c:out  value="${trans.hrPayFixTxn.revScaleId.scaleStartAmt}"default=""/><c:out value="-"/><c:out  value="${trans.hrPayFixTxn.revScaleId.scaleIncrAmt}"default=""/><c:out value="-"/><c:out  value="${trans.hrPayFixTxn.revScaleId.scaleEndAmt}"default=""/>
	</td>
	       	
     
            </tr>
            
            
            
            
            <tr>
		<td width="25%">
		<b><hdiits:caption captionid="Pf.Pay.reason" bundle="${PayLab}"></hdiits:caption></b>
	       </td>
	       <td width="25%">
	        <c:out value="${reasonlookupMst}"default=""></c:out>
	       </td>
	       <td width="25%">
		<b><hdiits:caption captionid="Pf.Pay.date" bundle="${PayLab}"></hdiits:caption></b>
	       </td>
	       
	<td width="25%">
	<fmt:formatDate value="${date}" pattern="dd/MM/yyyy" />
	
	</td>
	       	
     
            </tr>
	
	</table>
	<br><br>
	      <table  width="100%" id="syscomputed" style="display:true ">
  	  
 <tr>
      <td><b><hdiits:radio name="SystemComp" value="1"   captionid="Pf.System.Computed" bundle="${PayLab}" default="" onclick="changeSystem();" id="SystemComp"/></b></td>
      
      </tr>
      </table>
      
      <table  width="100%" id="syscomputed1" style="display:true ">
      <tr>
      <td width="25%"><hdiits:caption captionid="Pf.Payfixed" bundle="${PayLab}" /></td><td width="25%"><INPUT type="text" name="SystemBasic"  id="SystemBasic" value="${trans.sysCompNewBasicSal}" readonly="true" /></td>
       <td width="25%"> <hdiits:caption captionid="Pf.nextincr" bundle="${PayLab}"/></td><td width="25%"><INPUT type="text" name="SystemDate" id="SystemDate" value="<fmt:formatDate value="${trans.sysCompNxtIncrDate}" pattern="dd/MM/yyyy"  />" readonly="true"  ></INPUT></td>
      </tr>
      
      </table>
      <table width="100%" id="usercomputed" style="display:true ">
      <tr>
      <td><b><hdiits:radio name="UserComp" value="2" captionid="Pf.User.Computed" bundle="${PayLab}" default=""  onclick="changeUser();" id="UserComp"/></b></td>
      
      </tr>
      </table>
       <table width="100%" id="usercomputed1" style="display:true " >
      <tr>
      <td width="25%"><hdiits:caption captionid="Pf.Payfixed" bundle="${PayLab}" /></td><td width="25%"><hdiits:number name="payfixed" id="payfixed" default="${trans.userCompNewBasicSal}" /></td>
      
    <td width="25%"> <hdiits:caption captionid="Pf.nextincr" bundle="${PayLab}"/></td><td width="25%"><hdiits:dateTime name="user_nxt_incr_date" captionid="Pf.fixDate" bundle="${PayLab}" validation="txt.isdt" default="${nxtdate}" maxvalue="31/12/2099"></hdiits:dateTime></td>
      </tr>
	
	<tr>
      <td width="25%"><hdiits:caption captionid="Pf.Explanation" bundle="${PayLab}" /></td><td width="25%"><hdiits:textarea name="explanation" id="explanation" default="${trans.userRemarks}" rows="6" cols="36" maxlength="400" onkeypress="dodacheck(this)"/></td>
      
      </tr>
	
	
	</table>
	</hdiits:fieldGroup>
	<br><br><br>
<table id="approvalmsg" style="display:none" align="center">
<tr>
<td align="center">
<font color="red">
<b><hdiits:caption captionid="Pf.approvalmsg" bundle="${PayLab}" captionLang="single"/></b></font>
</td>
</tr>
</table>
<table id="rejectmsg" style="display:none" align="center">
<tr>
<td align="center">
<font color="red">
<b><hdiits:caption captionid="Pf.rejectmsg" bundle="${PayLab}" captionLang="single"/></b></font>
</td>
</tr>
</table>

	
	<hdiits:jsField name="Dtlssubmit" jsFunction="forward()"/>
	<table align="center" id="buttons3" style="display: true">

	

	</table>
	<script>approved('${trans.compFlag}','${trans.approve}');</script>
 	
	
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>

<html>
<head>
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US"
	var="commonLables" scope="request" />
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<script>

function trim(s) 
{
// Remove leading spaces and carriage returns
//  s = s.replace(/&nbsp;/gi,'');

 while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
  {
    s = s.substring(1,s.length);   
  }

  // Remove trailing spaces and carriage returns

  while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
  {
    s = s.substring(0,s.length-1);
  }
  return s;
}

function chkLoanName()
{ 
  if(!trim(document.frmLoanMaster.loanName.value) == '')
  {
  var name =document.frmLoanMaster.loanName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&loanName='+ document.frmLoanMaster.loanName.value;
		  var actionf="chkLoanName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
 //        alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=chk_loanName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	}
}

function chk_loanName()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('loan-name');
   //                 alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
                    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].firstChild.nodeValue!='0')
                    {                    
                     alert('Loan Name already exists.');
                     document.frmLoanMaster.loanName.value = '';
                     document.frmLoanMaster.loanName.focus();
                    }
  }
}
//added by khusal
function chkNumber()
{
	var maxNum = document.frmLoanMaster.txtMaxNoInstall.value;
	var minNum = document.frmLoanMaster.txtMinNoInstall.value;
	if((maxNum != null && maxNum != "") && (minNum != null && minNum != "")){
		maxNum = parseInt(maxNum, 10);
		minNum = parseInt(minNum, 10);
		if(minNum>maxNum)
		{
	    alert("Minimum number of installment should be less than Maximum number of installment");
	    document.frmLoanMaster.txtMinNoInstall.value='';
	    document.frmLoanMaster.txtMinNoInstall.focus();
		}
	}	
}

function chkMaxNoAmount()
{
	var MaxNoAmount=document.frmLoanMaster.txtMaxNoAmount.value;
	MaxNoAmount=parseInt(MaxNoAmount, 10);
	if(MaxNoAmount==0)
	{
		alert("Maximum amount should be greater than Zero");
		document.frmLoanMaster.txtMaxNoAmount.value='';
		document.frmLoanMaster.txtMaxNoAmount.focus();
	}
	
}

function chkNoInstall()
{
var MaxNoInstall=document.frmLoanMaster.txtMaxNoInstall.value;
var MinNoInstall=document.frmLoanMaster.txtMinNoInstall.value;


MaxNoInstall=parseInt(MaxNoInstall, 10);
MinNoInstall=parseInt(MinNoInstall, 10);
if( MaxNoInstall == 0)
{
alert("Maximum number of installment should be greater than Zero");
document.frmLoanMaster.txtMaxNoInstall.value='';
document.frmLoanMaster.txtMaxNoInstall.focus();
	
}
if( MinNoInstall==0)
{
alert("Minimun number of installment should be greater than Zero");
document.frmLoanMaster.txtMinNoInstall.value='';
document.frmLoanMaster.txtMinNoInstall.focus();
}
}
function chkRadio()
{
	alert("Do you want to change");
	
}

//ended by khushal
</script>

<hdiits:form name="frmLoanMaster" validate="true" method="POST"
	action="./hrms.htm?actionFlag=InsertLoanAdvMasterData&edit=N"
	encType="text/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
			captionid="LM.insertLoanAdvMaster" bundle="${commonLables}" style=""/></b></a></li>
		<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
	</ul>
	</div>
	<br/>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<br>
	<table align="center" cellsapcing="2" cellpadding="2">
		<tr/><tr/>
		<tr>
			<td><b><hdiits:caption captionid="LM.loanName"
				bundle="${commonLables}" /><b></td>
			<td><hdiits:text name="loanName" default="" caption="Loan Name"
				maxlength="50" validation="txt.isrequired" onblur="chkLoanName()"
				mandatory="true"
				onkeypress="if(event.keyCode == 13) event.returnValue = false;" /></td>
		</tr>
		<tr/><tr/>
		<tr>
			<td><b><hdiits:caption captionid="LM.maxNoAmount"
				bundle="${commonLables}" /></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td ><hdiits:number name="txtMaxNoAmount" default=""
				caption="Max No Of Amount" maxlength="50"
				validation="txt.isrequired,txt.isnumber" mandatory="true" onblur="chkMaxNoAmount()" /></td>
		</tr>
		<tr/><tr/>
		<tr>
			<td><b><hdiits:caption captionid="LM.maxNoInstall"
				bundle="${commonLables}" /></b></td>
			<td><hdiits:number name="txtMaxNoInstall" default=""
				caption="Max No of Installment" maxlength="50"
				validation="txt.isrequired,txt.isnumber" mandatory="true" onblur="chkNoInstall()" /></td>
		</tr>
		<tr/><tr/>

		<tr>
			<td><b><hdiits:caption captionid="LM.minNoInstall"
				bundle="${commonLables}" /></b></td>
			<td><hdiits:number name="txtMinNoInstall" default=""
				caption="Min No of Installment" maxlength="50"
				validation="txt.isrequired,txt.isnumber" mandatory="true"
				onblur="chkNumber(); chkNoInstall()" /></td>
		</tr>
		<tr/><tr/>
		<tr>
			<td><hdiits:radio captionid="LM.byAccountantGeneral"
				caption="YES" bundle="${commonLables}" value="2500382" name="group1" mandatory="true" default="2500382" onclick="chkRadio()"/>
				
			<hdiits:radio captionid="LM.byTreasury" caption="NO"
				bundle="${commonLables}" value="2500381" name="group1" mandatory="true" onclick="chkRadio()" /></td>
		</tr>

		<tr/><tr/><tr/><tr/>
	</table>
	
	<br/><br/><br/>
	
	<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables"
		scope="request" /> <hdiits:hidden default="getLoanAdvMstView"
		name="givenurl" /> <jsp:include page="../../core/PayTabnavigation.jsp" />
		<br/><br/>
		</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getLoanAdvMstView";
			document.frmLoanMaster.action=url;
			document.frmLoanMaster.submit();
		}
		
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</html>

<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import ="com.tcs.sgv.eis.valueobject.HrEisBankMst, 
				  java.text.SimpleDateFormat" %>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="commonLables" scope="request"/>
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

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.actionList}" ></c:set>

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

function chkBankName()
{ 
  if(!trim(document.frmBankEdit.bankName.value)== '')
  {
   
  var name = document.frmBankEdit.bankName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+ '&oldname=${actionList.bankName}&bankName=' + document.frmBankEdit.bankName.value;
		  var actionf="chkBankName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
//        alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=chk_bankName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	}
}

function chk_bankName()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('bank-name');
   //                 alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
                    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
                    {                    
                     alert('Bank Name already exists.');
                     document.frmBankEdit.bankName.value = '';
                     document.frmBankEdit.bankName.focus();
                    }
  }
}

</script>




<% 
				HrEisBankMst empMst = (HrEisBankMst)pageContext.getAttribute("actionList"); 
			
     	%>

<hdiits:form name="frmBankEdit" validate="true" method="POST"
	action="./hrms.htm?actionFlag=InsertBankMasterData&edit=Y" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="BM.updateBankMaster" bundle="${commonLables}"/></b></a></li>
			<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<br> <br> <br>	
	<table align="center" cellsapcing="2" cellpadding="2">
		<tr>
			<td><!--<b><hdiits:caption captionid="BM.bankId" bundle="${commonLables}"/></b>-->&nbsp;</td>
			<td><hdiits:hidden name="bankId" caption="bankId"  style="BACKGROUND:GRAY" default ="${actionList.bankId}"/></td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="BM.bankName" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="bankName" caption="Bank Name"  maxlength="39"  validation="txt.isrequired" default ="${actionList.bankName}" 
			onblur="chkBankName()" onkeypress="if(event.keyCode == 13) event.returnValue = false;"/></td>
		</tr>
	</table>
 	
 	<br> <br> <br>	
 	<hdiits:hidden default="getBankView" name="givenurl"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	<br> <br> <br>	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	
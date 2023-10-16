<html>
<head>
<%
	try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="common/script/commonfunctions.js">
</script>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="actionList" value="${resValue.actionList}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<script>
function chkBankName()
{ 
  if(!trim(document.frmBankMaster.bankName.value)== '')
  if(placeFloat(document.frmBankMaster.bankName))
  {
  var name =document.frmBankMaster.bankName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  if(${actionList.bankName eq 'null'} || ${actionList.bankName eq ''})
  		   url= uri+'&bankName='+ document.frmBankMaster.bankName.value;
  		  else		  
		   url= uri+ '&oldname=${actionList.bankName}&bankName=' + document.frmBankMaster.bankName.value;
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
                 // alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].firstChild.nodeValue);
                    if(namesEntries.length != 0 && namesEntries.length != undefined && namesEntries[0].childNodes[0].firstChild.nodeValue!='0')
                    {                    
                     alert('Bank Name already exists.');
                     document.frmBankMaster.bankName.value = '';
                     document.frmBankMaster.bankName.focus();
                    }
  }
}

function validateForm()
{
 var uri = "./hrms.htm?actionFlag=";
 var url = uri + document.frmBankMaster.txtAction.value;

 document.frmBankMaster.action = url;
 document.frmBankMaster.submit();
}
function onlyAlphaWithSpecialChar(control) 
{                
              var iChars = "QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq1234567890/ ";
              var value="";
              var valid=true;
              
              for (var i=0; i<control.value.length;i++) 
               {  
               if (iChars.indexOf(control.value.charAt(i))!=-1) 
                 {
            	   re = /['QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq']/;
            	   
            	    if((!re.test(control.value)) ) {
            	      alert("IFSC code must contain at least one alphabet!");
            	      control.focus();
            	      return false;
            	    }
            	    
                    value=value+control.value.charAt(i);
                 }               
                 else
                 {                 
                    valid=false;
                 }
              }                   
              if(!valid)
              {              
                   control.value="";
                  alert('Special characters are not allowed.');             
                  control.value="";
                  control.focus();
                  return false;
              }
              return true;              
}

</script>


<hdiits:form name="frmBankMaster" validate="true" method="POST"
	action="javascript:validateForm()" encType="text/form-data">
	<c:choose>
		<c:when test="${actionList ne null}">
			<hdiits:hidden name="txtAction" default="InsertBankMasterData&edit=Y" />
		</c:when>
		<c:otherwise>
			<hdiits:hidden name="txtAction" default="InsertBankMasterData&edit=N" />
		</c:otherwise>
	</c:choose>
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<c:choose>
			<c:when test="${actionList ne null}">
				<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
					key="BM.updateBankMaster" bundle="${commonLables}" /></b></a></li>
			</c:when>
			<c:otherwise>
				<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
					key="BM.insertBankMaster" bundle="${commonLables}" /></b></a></li>
			</c:otherwise>
		</c:choose>
		<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
	</ul>
	</div>
	
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin;"  ><br>
	<br>
	<br>
	<br>
	<table align="center" cellsapcing="2" cellpadding="2">
		<c:if test="${actionList ne null}">
			<tr>

				<td><hdiits:hidden name="bankId" caption="bankId"
					default="${actionList.bankId}" /></td>
			</tr>

		</c:if>
		<tr>
			<td width="2%"></td>
			<TD align="left" width="8%">
			<td><b><fmt:message key="BM.bankName"
				bundle="${commonLables}" /><b>&nbsp;&nbsp;</td>
			<td><hdiits:text id="bankName" name="bankName"
				caption="Bank Name" mandatory="true"
				validation="txt.isrequired,txt.isname" maxlength="39" size="30"
				onblur="chkBankName()" default="${actionList.bankName}"
				onkeypress="if(event.keyCode == 13) event.returnValue = false;" /></td>

			<td width="2%"></td>
			<TD align="left" width="8%"><b>Bank Code</b></TD>
			<TD width="20%"><hdiits:number name="bankCode"
				caption="BankCode" mandatory="true" default="${actionList.bankCode}"
				validation="txt.isrequired,txt.isnumber" maxlength="10" size="25" />
			</td>

			</TD>
		</tr>
		<tr>
			<td width="2%"></td>
			<td align="left" width="8%">
			
			<td><b><fmt:message key="BR.MICR" bundle="${commonLables}" /></b></TD>
			<td><hdiits:text name="txtMicrCode" id="txtMicrCode"
				mandatory="true" caption="MICR Code"
				default="${actionList.micrCode}" bundle="${commonLables}"
				validation="txt.isrequired" maxlength="25"
				onblur="onlyAlphaWithSpecialChar(this)"></hdiits:text></td>
				
			<td width="2%"></td>
			
			<TD align="left" width="8%"><b><fmt:message key="BR.ADD"
				bundle="${commonLables}" /></b></TD>
			<TD width="20%"><hdiits:textarea rows="3" cols="32"
				name="txtBankhAdd" default="${actionList.bankAddress}"
				maxlength="400" caption="Address">
			</hdiits:textarea></TD>
		</tr>
	</table>
	
	<br/><br/><br/>
	
	<hdiits:hidden default="getBankView" name="givenurl" /> <c:choose>

		<c:when test="${actionList ne null}">
			<fmt:setBundle basename="resources.payroll.payrollLables_en_US"
				var="Lables" scope="request" />
		</c:when>
		<c:otherwise>
			<fmt:setBundle basename="resources.eis.eis_common_lables"
				var="Lables" scope="page" />
		</c:otherwise>
	</c:choose> <jsp:include page="../../core/PayTabnavigation.jsp" />
	<br/><br/><br/>
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		document.frmBankMaster.bankName.focus();
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getBankView";
			document.frmBankMaster.action=url;
			document.frmBankMaster.submit();
		}
		</script>
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

</html>

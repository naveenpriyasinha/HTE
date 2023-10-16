<%
try {
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="request"/>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="checkMsg" value="${resValue.checkMsg}" ></c:set>
<c:set var="fileNumber" value="${param.fileNumber}" ></c:set>
<c:set var="bhid" value="${param.bhid}" ></c:set>
<c:set var="Month" value="${param.Month}" ></c:set>
<c:set var="Year" value="${param.Year}" ></c:set>


<script type="text/javascript">
//alert("msg is");
if('${msg}'!= null && '${msg}'!='')
{
	//alert("msg is"+'${msg}');
	alert("${msg}");
	
	GoToClose();
	
}

if('${checkMsg}'!= null && '${checkMsg}'!='') 
{
	alert('${checkMsg}');
	///var uri = "ifms.htm?actionFlag=getLegactFileValidation";
	//window.location.href=uri;
	GoToClose();
}

function checkSave()
{

	 
	if(document.UpdateRegbankRef.BankRefNo.value=='')
	{
		alert('Please Enter Bank Refference Number');
		document.uploadLoan.BankRefNo.focus();
		return false;
	}
	

	bankRefNo = document.UpdateRegbankRef.BankRefNo.value;
	var fileNumber  ='${fileNumber}';
	var bhid  ='${bhid}';
	if(fileNumber != '' && bhid!= '0')
	{
			document.UpdateRegbankRef.action = "./hrms.htm?actionFlag=approveNPSRegular&month="+'${Month}'+"&year="+'${Year}'+"&fileNumber="+'${fileNumber}'+"&bhid="+'${bhid}'+"&bankRefNo="+bankRefNo;
			document.UpdateRegbankRef.submit();
			document.UpdateRegbankRef.formSubmitButton.disabled=true; 
			showProgressbar("Please wait<br>While Approving Bill...");
		
	}
	
}

function GoToClose()
{
	//alert("GoToClose...");
	var result = null;
	result = window.opener.ShowTokenDetails();
	window.close();
}

</script>


<hdiits:form name="UpdateRegbankRef" validate="true" method="POST" action="" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			Approve Bill</b></a></li>
			</ul>
</div>

<div class="halftabcontentstyle">
<div id="tcontent1" class="halftabcontent" tabno="0">
	<table align="center">
		<tr>
			<td><b><hdiits:caption captionid="EL.BankRefNo" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="BankRefNo" caption="BankRefNo"  style="text-align:right" captionid="EL.BankRefNo"  validation="txt.isrequired,txt.isnumber"  maxlength="20"  mandatory="true" size="20" />  </td>
	    </tr>
	<tr>
		<td align="center" >
		<hdiits:button name="formSubmitButton" value="Save" type="button"	captionid="eis.save" bundle="${commonLables}" onclick="checkSave()" ></hdiits:button>
		</td>
		<td align="center" >
		<hdiits:button name="formCloseButton" value="Close" type="button"	captionid="eis.close" bundle="${commonLables}" onclick="GoToClose()"></hdiits:button> 
		</td>
		
			
	</tr>

</table>
	 </div>
		</div>

	
<script type="text/javascript"> initializetabcontent("maintab");</script>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


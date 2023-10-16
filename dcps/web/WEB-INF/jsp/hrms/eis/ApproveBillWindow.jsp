<%
try {
%>



<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>




<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="request"/>





<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="trnbill" value="${param.trnbill}" ></c:set>
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

function chekc()
{

	if(document.uploadLoan.VoucherNo.value=='')
	{
		alert('Please Enter VoucherNo');
		document.uploadLoan.VoucherNo.focus();
		return false;
	}

	if(document.uploadLoan.Voucherdate.value=='')
	{
		alert('Please Enter Voucherdate');
		document.uploadLoan.Voucherdate.focus();
		return false;
	}
	
	var vouchenumber = document.uploadLoan.VoucherNo.value;
	var vouchedate = document.uploadLoan.Voucherdate.value;
	var trnbill  ='${trnbill}';
	
	if(trnbill != '-1' && trnbill!= 'false')
	{
			document.uploadLoan.action = "./hrms.htm?actionFlag=approvePayBill&month="+'${Month}'+"&year="+'${Year}'+"&billNo="+'${trnbill}'+"&voucherNo="+vouchenumber+"&voucherDate="+vouchedate;// A= Approve
			document.uploadLoan.submit();
			document.uploadLoan.formSubmitButton.disabled=true; 
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


<hdiits:form name="uploadLoan" validate="true" method="POST" action="" encType="multipart/form-data">

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
			<td><b><hdiits:caption captionid="EL.VoucherNo" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="VoucherNo" caption="VoucherNo"  style="text-align:right" captionid="EL.VoucherNo"  validation="txt.isrequired,txt.isnumber"  maxlength="10"  mandatory="true" size="20" />  </td>
	    </tr>
	    
	    
	    
	     <tr>
			<td>
					<b>
					   <hdiits:caption captionid="EL.VoucerDate" bundle="${commonLables}"/>
					</b>
			</td>
			
			
			<td>
				<hdiits:dateTime captionid="EL.VoucerDate"  bundle="${commonLables}" name="Voucherdate" validation="txt.isdt,txt.isrequired" mandatory="true" minvalue=""/>
			</td>	
		</tr>
		
	
  
	

	<tr>
		<td align="center" >
		<hdiits:button name="formSubmitButton" value="Save" type="button"	captionid="eis.save" bundle="${commonLables}" onclick="chekc()" ></hdiits:button>
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


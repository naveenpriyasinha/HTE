

<%
try {
%>

<html>
	<head>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="request"/>


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
	<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>

<c:set var="tokenNumber" value='<%=request.getParameter("tokenNumber")%>' />
<c:set var="tokenDate" value='<%=request.getParameter("tokenDate")%>' />
<c:set var="trnBillNumber" value='<%=request.getParameter("trnBillNumber")%>' />

<%
	// VARUN SHARMA: For bill number
	
	String billNo = "";

//System.out.println("is session.getAttribute() value null? " +session.getAttribute("VerifyMap").toString().equals(null) );


if ( session.getAttribute("VerifyMap") != null)
	{
		java.util.Map map = (java.util.Map)session.getAttribute("VerifyMap");
		String billControlNo = null;
		
		//System.out.println("is bill control no null? " + map.get("BillCntrNo").toString().equals(null) );
		
		if( !map.get("BillCntrNo").toString().equals(null) )
				billControlNo = map.get("BillCntrNo").toString();
		
		billNo = billControlNo.substring( billControlNo.indexOf('(')+1, billControlNo.indexOf('/')+6 );
		//System.out.println(" bill No is: " +billNo);
	
		pageContext.setAttribute("billNo",billNo);
			
	}
		
%>



<script type="text/javascript">
	function submitForm()
	{
		var voucherNo=document.frmVoucherNo.voucherNo.value;
		var voucherDate=document.frmVoucherNo.voucherDate.value;
	 	document.forms[0].action="./hrms.htm?actionFlag=insertVoucherNo&billNo=${trnBillNumber}&voucherNo="+voucherNo+"&voucherDate="+voucherDate;
	  	document.forms[0].submit();
	}
</script>

</head>

<br>
<hdiits:form name="frmVoucherNo" validate="true" >
<table align="center" >
	<tr>
		<td align="left" class="Label"> <font color="black" size="4"> <fmt:message key="VN.BillNo" bundle="${commonLables}" /> </td>
		<td align="right"><b><font color="black" size="4" > <c:out value="${billNo}"></c:out></font></b></td>
	</tr>
</table>

<br>
<br>

<table align="center" cellspacing="2" cellpadding="2" >
	<tr></tr>
	<tr></tr>	

	<tr>
		<td align="center" class="Label"> <fmt:message key="VN.VoucherNo" bundle="${commonLables}" />	</td>
			<td><hdiits:number id = "voucherNo" name="voucherNo" captionid="VN.VoucherNo"  default="${tokenNumber}" bundle="${commonLables}" maxlength="20" size="20" validation="txt.isnumber" /></td>
  </tr>
    
  <tr>
    <td align="center" class="Label"> <fmt:message key="VN.VoucherDate" bundle="${commonLables}" />	
		<td><hdiits:dateTime captionid="VN.VoucherDate"  bundle="${commonLables}" name="voucherDate"  default="${tokenDate}" validation="txt.isdt" /></TD>				
  </tr>

	<tr>
    <td></td>
    <td align="left" class="Label"> <hdiits:button name="Save" value="Save" type="button" caption="Save"  onclick="submitForm()" /></td>
	</tr>
	

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

</table>
</hdiits:form>
</html>

<%
		} catch (Exception e) {
		//System.out.println("Exception in voucherNo");
		e.printStackTrace();
	}
%>

	
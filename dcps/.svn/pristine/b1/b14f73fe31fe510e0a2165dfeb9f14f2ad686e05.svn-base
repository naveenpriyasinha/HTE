<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <!-- For Taglib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@page import="com.tcs.sgv.common.valueobject.ChequeVitoVO"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
<link rel="stylesheet" type="text/css" href="TableBorders.css">
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
<c:set var="vitoList" value="${resValue.vitoList}"> </c:set>
<script language="javascript">

function printDetailsfor80()
{
	window.open("ifms.htm?actionFlag=getDelAccDtls&PrintReport=YES&ReportType=80","","width=900,height=700,resizable=1,scrollbars=1");
//	document.frmBillTranRegAftAudRpt.action="ifms.htm?actionFlag=genBillTranRegAfterAudit&vitoType=75&PrintReport=YES";
//	document.frmBillTranRegAftAudRpt.submit();
}
function printDetailsfor132()
{
	window.open("ifms.htm?actionFlag=getDelAccDtls&PrintReport=YES&ReportType=132","","width=900,height=700,resizable=1,scrollbars=1");
//	document.frmBillTranRegAftAudRpt.action="ifms.htm?actionFlag=genBillTranRegAfterAudit&vitoType=75&PrintReport=YES";
//	document.frmBillTranRegAftAudRpt.submit();
}
</script>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<hdiits:form name="frmaudWiseDailyRpt" validate="true" method="post">
<table border="1" cellspacing="0" cellpadding="0" align="center" width="90%">
	<tr class="datatableheader"> 
	    <td  align="center">
	    <fmt:message key="CHQDELIVERYACCVITO.TITLE" bundle="${billprocLabels}"></fmt:message>
	    </td>
	</tr>
	<tr>
	<td>
	<img src="images/printer.gif" onclick="printDetailsfor80()" alt="Print">(80)&nbsp;
	<img src="images/printer.gif" onclick="printDetailsfor132()" alt="Print">(132)
	</td>
	</tr>
	<tr>
	
		<td>
		<table width="100%" cellpadding="0" cellspacing="0">
			 <c:choose>
			  <c:when test="${vitoList!=null}">
			   <%
				 double totalChqAmount=0;
				 int noOfVoucher=0;
				 int noOfCheque=0;				 
			   %>
			  <tr height="20">
			  		<td>
			  		</td>
			  </tr>
			  <tr>  			  	
				<td>
					<table align="center" width="80%" border="1" cellpadding="0" cellspacing="0">
						<tr height="20" align="center" class="datatableheader">
							<td width="10%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.SR_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
							<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.MAJOR_HEAD" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
							<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOTAL_VOUCHERS" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>		
							<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOTAL_CHEQUES" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
							<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOTAL_AMOUNT" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>		
					   </tr>
					   <c:forEach var="vouch" items="${vitoList}" >
					   
					    <%
					    	ChequeVitoVO chqVitoVo =(ChequeVitoVO)pageContext.getAttribute("vouch");
				   			totalChqAmount=totalChqAmount + Double.parseDouble(chqVitoVo.getTotalAmount().toString());
				   			noOfVoucher=noOfVoucher + Integer.parseInt(chqVitoVo.getTotalVoucher().toString());
				   			noOfCheque=noOfCheque + Integer.parseInt(chqVitoVo.getTotalcheques().toString());
						%>
					  	<tr align="center">
							<td width="10%"><c:out value="${vouch.srNo}"></c:out></td>	
							<td width="30%" align="right"><a href="ifms.htm?majorhead=${vouch.majorHead}&actionFlag=getTrackDtlsByMjrHead"><c:out value="${vouch.majorHead}" ></c:out></a></td>
							<td width="20%" align="right"><c:out value="${vouch.totalVoucher}"></c:out></td>
							<td width="20%" align="right"><c:out value="${vouch.totalcheques}"></c:out></td>	
							<td width="20%" align="right"><c:out value="${vouch.totalAmount}"></c:out></td>	
							<hdiits:hidden name="select" default="${vouch.majorHead}"/>
						</tr>
						</c:forEach>
						<tr class="Label2">
							<td colspan="2">
							<fmt:message key="VITOCOMMON.TOTAL" bundle="${billprocLabels}"></fmt:message>
							: </td>
							<td align="right"><%=noOfVoucher%></td>
							<td align="right"><%=noOfCheque%></td>
							<td align="right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2"><%=totalChqAmount%></fmt:formatNumber></td>
						</tr>		
					</table>
			   </td>
			</tr>
			 <tr height="20">
			  		<td>
			  		</td>
			  </tr>
			<tr>
				<td align="center">		
					<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?viewName=homePage'"></hdiits:button>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td height="20" align="center">
					<fmt:message key="VITOCOMMON.CHEQUENOTAVAILABLE" bundle="${billprocLabels}"></fmt:message>
					</td>
				</tr>
				<tr>
					<td height="20" align="center"></td>
				</tr>
				<tr>
					<td height="20" align="center"><input name="btnClose" type="button" value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?viewName=homePage'"/></td>
				</tr>
			</c:otherwise>
			</c:choose>
		</table>
		</td>
	</tr>
</table>

</hdiits:form>
	






	
	

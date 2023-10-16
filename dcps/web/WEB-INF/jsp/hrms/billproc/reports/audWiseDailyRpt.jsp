<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<!-- For Taglib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
    
<%@page import="com.tcs.sgv.common.valueobject.BillVitoVO"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
<link rel="stylesheet" type="text/css" href="TableBorders.css">

<script type="text/javascript">
function validate() 
{
   	document.frmaudWiseDailyRpt.submit();
}	
function printDetailsfor80()
{
	window.open("ifms.htm?actionFlag=genAudWiseDailyVito&vitoType=71&PrintReport=YES&ReportType=80","","width=1200,height=1200,resizable=1,scrollbars=1");
//	document.frmaudWiseDailyRpt.action="ifms.htm?actionFlag=genAudWiseDailyVito&vitoType=71&PrintReport=YES";
//	document.frmaudWiseDailyRpt.submit();
}
function printDetailsfor132()
{
	window.open("ifms.htm?actionFlag=genAudWiseDailyVito&vitoType=71&PrintReport=YES&ReportType=132","","width=1200,height=1200,resizable=1,scrollbars=1");
//	document.frmaudWiseDailyRpt.action="ifms.htm?actionFlag=genAudWiseDailyVito&vitoType=71&PrintReport=YES";
//	document.frmaudWiseDailyRpt.submit();
}
</script>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
<c:set var="vitoList" value="${resValue.vitoList}"> </c:set>
<c:set var="vitoCode" value="${resValue.vitoCode}"> </c:set>
<c:set var="statusMsg" value="${resValue.statusMsg}"> </c:set>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<hdiits:form name="frmaudWiseDailyRpt" validate="true" method="post" action="ifms.htm?actionFlag=insertVitoDtls&vitoType=71">
<table border="1" cellspacing="0" cellpadding="0" align="center" width="90%">
	<tr class="datatableheader" > 
	    <td  align="center"><fmt:message key="AUDITORWISEDAILYRPT.TITLE" bundle="${billprocLabels}"></fmt:message></td>
	</tr>
	<tr>
	<td>
	<img src="images/printer.gif" onclick="printDetailsfor80()">(80)&nbsp;	
	
	<img src="images/printer.gif" onclick="printDetailsfor132()">(132)
	</td>
	</tr>
	<tr>
		
	<td>
	<table align="center" width="100%">
		<tr height="20">
			<td><br></td>
		</tr>	
		<tr>
	
			<td>
			<table width="100%">
			 <c:choose>
			 <c:when test="${vitoList!=null}">
			 <%
			 double totalAmount=0;
			 int noOfBill=0;
			 %>
			 <tr class="Label2">
			 	<td align="center">
			 	<fmt:message key="AUDITORWISEDAILYRPT.VITOCODE" bundle="${billprocLabels}"></fmt:message>
					 : <c:out value="${vitoCode}"></c:out> 
			 	</td>
			 </tr>
			<tr>  			  	
				<td>
					<table align="center" border="1" cellspacing="0" cellpadding="0" width="90%">
							<tr height="20" align="center"  class="datatableheader">
								<td width="12%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.SR_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.AUDITOR_NAME" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="12%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.NO_OF_BILLS" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
								<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOTAL_AMOUNT" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
						   </tr>
						   <c:forEach var="vouch" items="${vitoList}" >
						   <% BillVitoVO billVO =(BillVitoVO)pageContext.getAttribute("vouch");
						   			System.out.println("no of bill is:-"+billVO.getTotalAmount());
						   			
						   			totalAmount=totalAmount + Double.parseDouble(billVO.getTotalAmount().toString());
						   			noOfBill=noOfBill+ Integer.parseInt(billVO.getNoOfBills().toString());
							%>
						  	<tr align="center">
								<td width="12%" align="center"><c:out value="${vouch.srNo}"></c:out></td>	
								<td width="15%" align="center"><c:out value="${vouch.auditorName}"></c:out></td>	
								<td width="12%" align="right"><c:out value="${vouch.noOfBills}"></c:out></td>
								<td width="15%" align="right"><c:out value="${vouch.totalAmount}"></c:out></td>
	  							<hdiits:hidden name="audUserId" default="${vouch.audUserId}"/>
							</tr>
							</c:forEach>	
							<tr class="Label2">
								<td colspan="2"><fmt:message key="VITOCOMMON.TOTALNOOFBILLS" bundle="${billprocLabels}"></fmt:message> : </td>
								<td align="right"> <%=noOfBill%></td>
								<td align="right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2"><%=totalAmount%></fmt:formatNumber></td>
							</tr>	
					</table>
				</td>
			</tr>
			<tr height="20">
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="11" align="center">	
					<hdiits:button name="btnGenVito" type="button" value='<%=buttonBundle.getString("COMMON.GENERATEVITO")%>' onclick="validate()"/>	
					<hdiits:button type="button" name="btnclose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?actionFlag=getAuditorsList&pageName=audWiseVito'"></hdiits:button>					
				</td>
			</tr>
			<tr height="20">
				<td>
				</td>
			</tr>
			</c:when>
			
			<c:otherwise>
				<tr>
					<td height="20" align="center"><fmt:message key="VITOCOMMON.BILLSNOTAVAILABLE" bundle="${billprocLabels}"></fmt:message></td>
				</tr>
				<tr>
					<td height="20" align="center"></td>
				</tr>
				<tr>
					<td height="20" align="center">
					<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?actionFlag=getAuditorsList&pageName=audWiseVito'"></hdiits:button>
					</td>
				</tr>
			</c:otherwise>
			</c:choose>
			
		</table>
		 <c:if test="${statusMsg!=null}">
			 <script>
				alert('${statusMsg}');
			</script>
		</c:if>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
</hdiits:form>





	
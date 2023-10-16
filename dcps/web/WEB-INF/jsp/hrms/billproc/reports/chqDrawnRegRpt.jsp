<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <!-- For Taglib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@page import="com.tcs.sgv.common.valueobject.ChequeVitoVO"%>
<%@page import="com.tcs.sgv.common.valueobject.ChequeVitoVOString"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
<link rel="stylesheet" type="text/css" href="TableBorders.css">
<script type="text/javascript">
function validate() 
{
  	document.frmChequeDrVito.submit();
}	
function printDetailsfor80()
{
	window.open("ifms.htm?actionFlag=getChqDrawnRegRpt&PrintReport=YES&ReportType=80","","width=900,height=900,resizable=1,scrollbars=1");
//	document.frmChequeDrVito.action="ifms.htm?actionFlag=getChqDrawnRegRpt&PrintReport=YES&ReportType=80";
//	document.frmChequeDrVito.submit();
}
function printDetailsfor132()
{
	window.open("ifms.htm?actionFlag=getChqDrawnRegRpt&PrintReport=YES&ReportType=132","","width=900,height=900,resizable=1,scrollbars=1");
//	document.frmChequeDrVito.action="ifms.htm?actionFlag=getChqDrawnRegRpt&PrintReport=YES&ReportType=132";
//	document.frmChequeDrVito.submit();
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

<hdiits:form name="frmChequeDrVito" validate="true" method="post" action="ifms.htm?actionFlag=insertChqDrawnReg&vitoType=76">
<table border="1" cellspacing="0" cellpadding="0" align="center" width="90%">
	<tr class="datatableheader"> 
	    <td  align="center">
	    <fmt:message key="CHQDRAWNREGVITORPT.TITLE" bundle="${billprocLabels}"></fmt:message>
	    </td>
	</tr>
	<tr><td>

<img src="images/printer.gif" alt="Print" onclick="printDetailsfor80()">(80)&nbsp;
<img src="images/printer.gif" alt="Print" onclick="printDetailsfor132()">(132)
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
			 int count=0;
			 %>
			 <tr class="Label2">
			 	<td align="center">
			 	<fmt:message key="VITOCOMMON.VITOCODE" bundle="${billprocLabels}"></fmt:message>
			 		 : <c:out value="${vitoCode}"></c:out> 
			 	</td>
			 </tr>
			<tr>  			  	
				<td>
					<table align="center" border="1" cellspacing="0" cellpadding="0" width="90%">
							<tr height="20" align="center" class="datatableheader">
								<td width="12%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.SR_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.CHEQUE_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="12%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOKEN_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
								<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CHQ.PRINT_DATE" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
								<td width="25%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.PAYEE_NAME" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
								<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CNTR.BILL_AMOUNT" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>		
								<td width="25%">&nbsp;&nbsp;<hdiits:caption captionid="CHQ.CHEQUE_AMOUNT" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>		
						   </tr>
						   <c:forEach var="vouch" items="${vitoList}" >
						   <% ChequeVitoVOString chqVitoVo =(ChequeVitoVOString)pageContext.getAttribute("vouch");
									System.out.println(chqVitoVo.getChequeAmount().toString());						   			
						   			totalAmount=totalAmount + Double.parseDouble(chqVitoVo.getChequeAmount().toString());
						   			String strAmount=chqVitoVo.getBillAmount();
						   			String[] amountData=strAmount.split("-");
						   			String strToken=chqVitoVo.getTokenNo();
						   			String[] tokenData=strToken.split("-");
						   			count++;
							%>
						  	<tr align="center">
								<td width="12%" align="center"><c:out value="${vouch.srNo}"></c:out></td>	
								<td width="15%" align="right"><c:out value="${vouch.chequeNo}"></c:out></td>	
								
								<td width="12%" align="right">
								<%
									for(int c=0;c<tokenData.length;c++)
									{
										String strTokenData=tokenData[c].toString();
									%>
										<%=strTokenData %>
										<br>
									<%
									}
									%>
								</td>
								<td width="15%" align="center"><c:out value="${vouch.chequeDate}"></c:out></td>
								<td width="25%" align="center"><c:out value="${vouch.payeeName}"></c:out></td>	
								<td width="15%" align="right">
									<%
									for(int c=0;c<amountData.length;c++)
									{
										String strData=amountData[c].toString();
									%>
										<%=strData %>
										<br>
									<%
									}
									%>

								</td>
								<td width="25%" align="right"><c:out value="${vouch.chequeAmount}"></c:out></td>		
	  							<hdiits:hidden name="chequeNo" default="${vouch.chequeNo}"/>
							</tr>
							</c:forEach>	
							<tr class="Label2">
								<td colspan="6">
								<fmt:message key="VITOCOMMON.TOTALNOOFCHEQUE" bundle="${billprocLabels}"></fmt:message>
								 : <%=count%></td>
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
					<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?viewName=homePage'"></hdiits:button>
				</td>
			</tr>
			<tr height="20">
				<td>
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
					<td height="20" align="center">
					<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?viewName=homePage'"></hdiits:button>
					</td>
				</tr>
			</c:otherwise>
			</c:choose>
		</table>
		</td>
	</tr>
</table>
<c:if test="${statusMsg!=null}">
			 <script>
				alert('${statusMsg}');
			</script>
		</c:if>
</td>
</tr>
</table>

</hdiits:form>
	






	
	

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
  	document.frmMajorHeadDtlVito.submit();
}
</script>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
<c:set var="vitoList" value="${resValue.vitoList}"> </c:set>
<c:set var="vitoCode" value="${resValue.vitoCode}"> </c:set>
<c:set var="statusMsg" value="${resValue.statusMsg}"> </c:set>
<hdiits:form name="frmMajorHeadDtlVito" validate="true" method="post" action="ifms.htm?actionFlag=insertChqDelAccReg&vitoType=77">
<table border="1" cellspacing="0" cellpadding="0" align="center" width="90%">
	<tr class="datatableheader"> 
	    <td  align="center">Cheque Delivery Account Details By Major Head</td>
	</tr>
	<tr>
		<td>
		<table width="100%">
		 <c:choose>
		  <c:when test="${vitoList!=null}">
		   <%
				 double totalChqAmount=0;
				 			 
			 %>
			<tr class="Label2">
			 	<td align="center">
			 		Vito Code : <c:out value="${vitoCode}"></c:out> 
			 	</td>
			 </tr>
		  <tr height="20">
		  		<td>
		  		</td>
		  </tr>
		<tr>  			  	
			<td>
				<table align="center" border="1" cellspacing="0" cellpadding="0" width="90%">
					<tr height="20" align="center" class="datatableheader">
						<td width="10%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.SR_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
						<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.ADVICE_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
						<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.CHEQUE_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
						<td width="25%">&nbsp;&nbsp;<hdiits:caption captionid="CHQ.PARTYNAME" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
						<td width="25%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.BILL_TYPE" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
						<td width="10%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOKEN_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
						<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CNTR.GROSS_AMOUNT" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>		
						
				   </tr>
				   <c:forEach var="vouch" items="${vitoList}" >
				   	<% ChequeVitoVOString chqVitoVo =(ChequeVitoVOString)pageContext.getAttribute("vouch");
			   			totalChqAmount=totalChqAmount + Double.parseDouble(chqVitoVo.getChequeAmount().toString());
			   			String strPartyname=chqVitoVo.getPayeeName();
			   			String strBillType=chqVitoVo.getBillType();
			   			String strTokenNumber=chqVitoVo.getTokenNo();
			   			String[] partyName=strPartyname.split("-");
			   			String[] billType=strBillType.split("-");
			   			String[] tokenNumber=strTokenNumber.split("-");
			   			
					%>
				  	<tr align="center">
						<td width="10%"><c:out value="${vouch.srNo}"></c:out></td>	
						<td width="15%"><c:out value="${vouch.adivceNo}"></c:out></td>	
						<td width="15%"><c:out value="${vouch.chequeNo}"></c:out></td>
						<td width="25%" align="">
						<%
							for(int c=0;c<partyName.length;c++)
							{
								String strPartyName=partyName[c].toString();
						%>
						<%=strPartyName %>
						<br>
						<%
							}
						%>
						
						
						
						
						</td>
						<td width="25%" align="center">
						<%
							for(int c=0;c<billType.length;c++)
							{
								String strBilltype=billType[c].toString();
						%>
						<%=strBilltype %>
						<br>
						<%
							}
						%>
						
						
						</td>
						<td width="10%" align="right">
												<%
							for(int c=0;c<tokenNumber.length;c++)
							{
								String strToken=tokenNumber[c].toString();
						%>
						<%=strToken %>
						<br>
						<%
							}
						%>
						

						
						</td>
						<td width="15%" align="right"><c:out value="${vouch.chequeAmount}"></c:out></td>	
						<hdiits:hidden name="chequeNo" default="${vouch.chequeNo}"/>
					</tr>
					</c:forEach>	
					<tr class="Label2">
							<td colspan="6">Total : </td>
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
				<hdiits:button name="btnGenVito" type="button" value="GenerateVito" onclick="validate()"/>	
				<hdiits:button type="button" name="btnClose"  value="Close" onclick="window.location.href='ifms.htm?actionFlag=getDelAccDtls'"></hdiits:button>
			</td>
		</tr>
		</c:when>
		
		<c:otherwise>
			<tr>
				<td height="20" align="center">Cheques not available.</td>
			</tr>
			<tr>
				<td height="20" align="center"></td>
			</tr>
			<tr>
				<td height="20" align="center">
					<hdiits:button type="button" name="btnClose"  value="Close" onclick="window.location.href='ifms.htm?actionFlag=getDelAccDtls'"></hdiits:button>
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
</hdiits:form>
	






	
	

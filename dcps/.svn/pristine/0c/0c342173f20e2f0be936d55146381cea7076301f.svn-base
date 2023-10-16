<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tcs.sgv.common.valueobject.BillVitoVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
    

<%@page import="java.util.Iterator"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
<link rel="stylesheet" type="text/css" href="TableBorders.css">

<script type="text/javascript">
function validate() 
{
  	document.frmaudWiseBillTyRpt.submit();
}	
function printDetailsfor80()
{
	window.open("ifms.htm?actionFlag=genAudWiseBillTypeVito&vitoType=73&PrintReport=YES&ReportType=80","","width=900,height=900,resizable=1,scrollbars=1");	
//	document.frmaudWiseBillTyRpt.action="ifms.htm?actionFlag=genAudWiseBillTypeVito&vitoType=73&PrintReport=YES";
//	document.frmaudWiseBillTyRpt.submit();
}
function printDetailsfor132()
{
	window.open("ifms.htm?actionFlag=genAudWiseBillTypeVito&vitoType=73&PrintReport=YES&ReportType=132","","width=900,height=900,resizable=1,scrollbars=1");	
//	document.frmaudWiseBillTyRpt.action="ifms.htm?actionFlag=genAudWiseBillTypeVito&vitoType=73&PrintReport=YES";
//	document.frmaudWiseBillTyRpt.submit();
}
</script>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
<c:set var="vitoList" value="${resValue.vitoList}"> </c:set>
<c:set var="vitoListSize" value="${resValue.vitoListSize}"> </c:set>
<c:set var="vitoCode" value="${resValue.vitoCode}"> </c:set>
<c:set var="statusMsg" value="${resValue.statusMsg}"> </c:set>
<%System.out.println("----------------Inside The JSP Page ------------------"); %>
<%@page import="java.util.ResourceBundle"%>
<%
ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>


<hdiits:form name="frmaudWiseBillTyRpt" validate="true" method="post" action="ifms.htm?actionFlag=insAudWiseBillTypeDtls&vitoType=73">
<table border="1" cellspacing="0" cellpadding="0" align="center" width="90%">
	<tr class="TableHeaderBG"> 
	    <td  align="center"><fmt:message key="AUDITORWISEBILLTYPEVITORPT.TITLE" bundle="${billprocLabels}"></fmt:message></td>
	</tr>
	<tr>
	<td>
	<img src="images/printer.gif" onclick="printDetailsfor80()">(80)&nbsp;
		<img src="images/printer.gif" onclick="printDetailsfor132()">(132)
	</td>
	</tr>
	<tr>
	<td>
	<table width="100%">
	   <tr height="20">
	   	<td>
	  		<hdiits:hidden name="cmbAuditors" default="${param.cmbAuditors}"/>
		  	<hdiits:hidden name="cmbBillType" default="${param.cmbBillType}"/>
		</td>
	   </tr>
	  <c:choose>
	  <c:when test="${vitoListSize>0}">
	  		<tr class="Label2">
			 	<td align="center">
			 	<fmt:message key="VITOCOMMON.VITOCODE" bundle="${billprocLabels}"></fmt:message>
			 		: <c:out value="${vitoCode}"></c:out> 
			 	</td>
			 </tr>
		  <c:forEach var="vito" items="${vitoList}" >
		  	 <c:if test="${vito!=null}">
			<% List innerList =(ArrayList)pageContext.getAttribute("vito");
				Iterator it = innerList.iterator();
				BillVitoVO vo=(BillVitoVO)it.next();
				double grossAmount=0;
				int noOfBill=0;
			%>
			  <tr height="20">
			  
			  		<td> 	<hdiits:hidden name="audUserId" default="<%=vo.getAudUserId().toString()%>" /></td>
			  </tr>
		  	  <tr height="20">
			  		<td align="center" class="Label2"><%=vo.getAuditorName()%></td>	
			  </tr>
			  <tr height="5">
			  		<td></td>
			  </tr>
			 
			  <tr>
					<td>     
						<table align="center" border="1" cellspacing="0" cellpadding="0" width="80%">
							<tr height="20" align="center" class="TableHeaderBG">
								<td width="10%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.SR_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="30%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.BILL_TYPE" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.NO_OF_BILLS" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
								<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOTAL_AMOUNT" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
						   </tr>
						   <c:forEach var="vouch" items="${vito}" >
						   	<% BillVitoVO billVitoVo =(BillVitoVO)pageContext.getAttribute("vouch");
						   			System.out.println("no of bill is:-"+billVitoVo.getTotalAmount());
						   			
						   			grossAmount=grossAmount + Double.parseDouble(billVitoVo.getTotalAmount().toString());
						   			noOfBill=noOfBill + Integer.parseInt(billVitoVo.getNoOfBills().toString());
						   			System.out.println("Gross Amount:-"+grossAmount);
							%>
						  	<tr align="center">
								<td width="10%" align="center"><c:out value="${vouch.srNo}"></c:out></td>	
								<td width="30%" align="center"><c:out value="${vouch.billType}"></c:out></td>	
								<td width="20%" align="right"><c:out value="${vouch.noOfBills}"></c:out></td>
								<td width="20%" align="right"><c:out value="${vouch.totalAmount}"></c:out></td>	
							</tr>
							</c:forEach>	
							<tr class="Label2">
								<td colspan="2">
								<fmt:message key="VITOCOMMON.TOTALNOOFBILLS" bundle="${billprocLabels}"></fmt:message>
								 :</td>
								<td align="right"><%=noOfBill%></td>
								<td align="right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2"><%=grossAmount%></fmt:formatNumber></td>
							</tr>	
						</table>
					</td>
			</tr>
			</c:if>
		</c:forEach>
	
		<tr height="20">
				  		<td></td>
		</tr>
		<tr>
			<td colspan="11" align="center">	
				<hdiits:button name="btnGenVito" type="button" value='<%=buttonBundle.getString("COMMON.GENERATEVITO")%>'  onclick="validate()"/>	
				<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>'  onclick="window.location.href='ifms.htm?actionFlag=getAudWiseBillType'"></hdiits:button>
			</td>
		</tr>
	
		</c:when>	  
		<c:otherwise>
			<tr>
				<td height="20" align="center">
				<fmt:message key="VITOCOMMON.BILLSNOTAVAILABLE" bundle="${billprocLabels}"></fmt:message>
				</td>
			</tr>
			<tr>
				<td height="20" align="center"></td>
			</tr>
			<tr>
				<td height="20" align="center">
				<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?actionFlag=getAudWiseBillType'"></hdiits:button>
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
</hdiits:form>
				
	
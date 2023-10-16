<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript">
		function showBill(url)
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
		}
		function showChqPrepare(url)
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=10,left=30,width=900,height=600"); 
		}
		</script>
</head>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<body>
<hdiits:form name="frmAcptRjctdBills" validate="true">
<hdiits:table width="97%" align="center">
	<hdiits:tr>
		<hdiits:td align="center">
			<b>Treasury > Bill Processing > Cheque Branch > Cheque Preparation</b>
		</hdiits:td>
	</hdiits:tr>
		<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
		<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>		
	<hdiits:tr>
		<hdiits:td align="center">
			<b><fmt:message key="CHQPREPARATION.TITLE" bundle="${billprocLabels}"></fmt:message></b>
		</hdiits:td>
	</hdiits:tr>
	
	<hdiits:tr>
		<hdiits:td>	
			<jsp:include page="../common/include/billproc/billSearch.jsp" />
			<jsp:include page="../common/include/billproc/pagination.jsp" />
		</hdiits:td>
	</hdiits:tr>
	
	<hdiits:tr>
		<hdiits:td>
			<hdiits:table width="97%" align="center">
   				<hdiits:tr>
   				<hdiits:td>
   					<hdiits:checkbox name="chkSelect" value=""></hdiits:checkbox>
   				</hdiits:td>
			    <hdiits:td align="center">
			    	<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />
			    </hdiits:td>
			    <hdiits:td align="center">
   					<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />
   				</hdiits:td>
   				<hdiits:td align="center">
   					<fmt:message key="CMN.BILL_AMOUNT" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />   					
   				</hdiits:td>	
   				<hdiits:td align="center">
   					<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />   					
   				</hdiits:td>
   				<hdiits:td align="center">
   					<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />   					
   				</hdiits:td>	      
   				<hdiits:td align="center">
	   				<fmt:message key="CNTR.DDO_NO" bundle="${billprocLabels}"></fmt:message>
   					(<fmt:message key="CMN.CARDEX_NO" bundle="${billprocLabels}"></fmt:message>)
			    	<jsp:include page="/common/include/billproc/sort.jsp" />
   				</hdiits:td>	
			    <hdiits:td align="center">	
   					<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />
   				</hdiits:td>	
   				<hdiits:td align="center">
   					<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />   					
   				</hdiits:td>	
   				<hdiits:td align="center">
   					<fmt:message key="CMN.AUDITOR_NAME" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />   					
   				</hdiits:td>	
   				<hdiits:td align="center">
   					<fmt:message key="CMN.BILL_CATEGORY" bundle="${billprocLabels}"></fmt:message>
			    	<jsp:include page="/common/include/billproc/sort.jsp" />   					
   				</hdiits:td>
   				
   			</hdiits:tr>
   			<%// for(int i=0;i<4;i++) { %>
   			<hdiits:tr> 
   			<hdiits:td>
   				<hdiits:checkbox name="chkSelect1" value=""></hdiits:checkbox>
   			</hdiits:td>
			<hdiits:td>
				<hdiits:a href="javascript:void(0)" onclick="javascript:showBill('ifms.htm?viewName=MRBillPage')">BIL001</hdiits:a>
			</hdiits:td>
			<hdiits:td>TKN111</hdiits:td>	
			<hdiits:td>85777.00</hdiits:td>	
			<hdiits:td>Medical Reimbursement</hdiits:td>
			<hdiits:td>19/09/2006</hdiits:td>	      
			<hdiits:td>DDO101(CDX1)</hdiits:td>	
			<hdiits:td>Sanjeev Shah</hdiits:td>	
			<hdiits:td>2772</hdiits:td>	
			<hdiits:td>K.C. Patel</hdiits:td>	
			<hdiits:td>Nil</hdiits:td>
		</hdiits:tr>
 		<% //} %>
   	</hdiits:table>
   </hdiits:td>
  </hdiits:tr>
   <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
   <hdiits:tr>
   	<hdiits:td>
   	<center>
   		<hdiits:button type="button" name="btnPrepareChq" value='<%=buttonBundle.getString("CHQPREPARE.PREPARE")%>' onclick="javascript:showChqPrepare('ifms.htm?actionFlag=chqWriteChq')"></hdiits:button>
   		<hdiits:button type="button" name="btnClose" value='<%=buttonBundle.getString("CHQCOMMON.CLOSE")%>' onclick="window.location.href='index.jsp'"></hdiits:button>
   	</center>
   </hdiits:td>
  </hdiits:tr>
 </hdiits:table>
   </hdiits:form>
</body>
</html>
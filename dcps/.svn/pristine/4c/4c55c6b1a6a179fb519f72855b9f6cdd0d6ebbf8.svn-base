<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<html>
	<head>
		<title>Applications</title>
		<script language="javascript">
			function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
			}
		</script>
	</head>
	
	<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
	<link rel="stylesheet" type="text/css" href="TableBorders.css">
	
	<body background="images/1page_BG.gif" >
		<hdiits:form name="frmTrackRpt" validate="true">
			<hdiits:table width="100%">
			  <hdiits:tr>
				  <hdiits:td>	
				  </hdiits:td>
			  </hdiits:tr>
			  <hdiits:tr><hdiits:td>&nbsp;</hdiits:td></hdiits:tr>
			  <hdiits:tr><hdiits:td>&nbsp;</hdiits:td></hdiits:tr>
			  <hdiits:tr><hdiits:td>&nbsp;</hdiits:td></hdiits:tr>
  			  <hdiits:tr>  			  	
				<hdiits:td>
					<hdiits:table align="center" width="100%">
						<hdiits:tr>
							<hdiits:td>
								<fmt:message key="CMN.SR_NO" bundle="${billprocLabels}" ></fmt:message>
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.BILL_AMOUNT" bundle="${billprocLabels}" ></fmt:message>(in Rs.)
								<jsp:include page="/common/include/billproc/sort.jsp" />
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.CARDEX_NO" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CHQ.INWARDED_DATE" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
							<hdiits:td>
								<fmt:message key="CMN.BILL_STATUS" bundle="${billprocLabels}" ></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />								
							</hdiits:td>
						</hdiits:tr>

						<hdiits:tr> 
							<hdiits:td>1</hdiits:td>
							<hdiits:td>
								<hdiits:a href="javascript:void(0)" onclick="javascript:showBill('ifms.htm?viewName=MRBillPage')">BC001</hdiits:a>
							</hdiits:td>
							<hdiits:td>85777.00</hdiits:td>
							<hdiits:td>Medical Reimbursement</hdiits:td>
							<hdiits:td>19/09/2006</hdiits:td>
							<hdiits:td>CDX1</hdiits:td>
							<hdiits:td>Sanjeev Shah</hdiits:td>
							<hdiits:td>29/09/2006</hdiits:td>
							<hdiits:td>2772</hdiits:td>
							<hdiits:td>TKN111</hdiits:td>
							<hdiits:td>Approved</hdiits:td>
						</hdiits:tr>

						<hdiits:tr>
							<hdiits:td>2</hdiits:td>
							<hdiits:td>
								<hdiits:a href="javascript:void(0)" onclick="javascript:showBill('ifms.htm?viewName=MRBillPage')">BC007</hdiits:a>
							</hdiits:td>
							<hdiits:td>6000.00</hdiits:td>
							<hdiits:td>Pay Bill</hdiits:td>
							<hdiits:td>25/09/2006</hdiits:td>
							<hdiits:td>CDX2</hdiits:td>
							<hdiits:td>Rajeev Shah</hdiits:td>
							<hdiits:td>30/09/2006</hdiits:td>
							<hdiits:td>2884</hdiits:td>
							<hdiits:td>TKN222</hdiits:td>
							<hdiits:td>Approved</hdiits:td>
						</hdiits:tr>	
					</hdiits:table>
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			<hdiits:tr><hdiits:td><jsp:include page="/common/include/billproc/report.jsp" /></hdiits:td></hdiits:tr>
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			<hdiits:tr>
				<hdiits:td colspan="11" align="center">
					
					<hdiits:button type="button" name="btnClose"  value="Close" onclick="window.close()"></hdiits:button>
				</hdiits:td>
			</hdiits:tr>
		</hdiits:table>
		</hdiits:form>
	</body>
</html>
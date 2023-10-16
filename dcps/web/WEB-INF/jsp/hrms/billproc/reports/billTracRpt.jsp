<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
    
<%@page import="com.tcs.sgv.billproc.counter.valueobject.BillTracVo"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
<link rel="stylesheet" type="text/css" href="TableBorders.css">
<hdiits:form name="frmBillTrackRpt" validate="true">
	<table width="100%">
	  <tr height="20"><td>&nbsp;</td></tr>
	  <tr>  			  	
		<td>
			<table align="center" width="100%">
				<tr>
					<td><fmt:message key="CMN.SR_NO" bundle="${billprocLabels}" ></fmt:message></td>
					<td><fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}" ></fmt:message></td>
					<td><fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}" ></fmt:message>(in Rs.)</td>
					<td><fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}" ></fmt:message></td>
					<td><fmt:message key="CMN.OFFICER_NAME" bundle="${billprocLabels}" ></fmt:message></td>
					<td><fmt:message key="CMN.BRANCH" bundle="${billprocLabels}" ></fmt:message></td>
					<td><fmt:message key="CMN.RECEIVED_DATE" bundle="${billprocLabels}" ></fmt:message></td>
					<td><fmt:message key="CMN.SEND_DATE" bundle="${billprocLabels}" ></fmt:message></td>
				</tr>
				<c:forEach var="trac" items="${billTrackList}" >
					<% BillTracVo vo =(BillTracVo)pageContext.getAttribute("trac");%>
					<tr align="center">
						<td align="center">1</td>
						<td align="center"><%=vo.getBillCntrlNo()%></td>	
						<td align="center"><%=vo.getDdoName()%></td>	
						<td align="center"><%=vo.getTokenNum()%></td>	
						<td align="center"><%=vo.getOfficeName()%></td>	
						<td align="center"><%=vo.getBranch()%></td>	
						<td align="center"><%=vo.getReceiveDate()%></td>	
						<td align="center"><%=vo.getSentDate()%></td>		
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>	
	<tr>
		<td colspan="11" align="center">	
			<hdiits:button type="button" name="btnClose"  value="Close" onclick="window.close()"></hdiits:button>
		</td>
	</tr>
</table>
</hdiits:form>
	
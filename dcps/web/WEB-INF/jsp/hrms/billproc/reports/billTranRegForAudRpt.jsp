<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
    

<%@page import="java.util.Iterator"%>
<%@page import="com.tcs.sgv.common.valueobject.BillTransitVO"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
<link rel="stylesheet" type="text/css" href="TableBorders.css">
<script type="text/javascript">
function validate() 
{
  	document.frmBillTranRegForAudRpt.submit();
}
function printDetailsfor80()
{
	window.open("ifms.htm?actionFlag=genBillTranRegForAudit&vitoType=74&PrintReport=YES&ReportType=80","","width=900,height=900,resizable=1,scrollbars=1");
//	document.frmBillTranRegForAudRpt.action="ifms.htm?actionFlag=genBillTranRegForAudit&vitoType=74&PrintReport=YES";
//	document.frmBillTranRegForAudRpt.submit();
}
function printDetailsfor132()
{
	window.open("ifms.htm?actionFlag=genBillTranRegForAudit&vitoType=74&PrintReport=YES&ReportType=132","","width=900,height=900,resizable=1,scrollbars=1");
//	document.frmBillTranRegForAudRpt.action="ifms.htm?actionFlag=genBillTranRegForAudit&vitoType=74&PrintReport=YES";
//	document.frmBillTranRegForAudRpt.submit();
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

<hdiits:form name="frmBillTranRegForAudRpt" validate="true" method="post" action="ifms.htm?actionFlag=insBillTranRegForAudit&vitoType=74">
<table border="1" cellspacing="0" cellpadding="0" align="center" width="90%">
	<tr class="datatableheader"> 
	    <td  align="center">
	    <fmt:message key="BILLTRANREGFORAUDITRPT.TITLE" bundle="${billprocLabels}"></fmt:message>
	    </td>
	</tr>
	<tr><td>

<img src="images/printer.gif" onclick="printDetailsfor80()">(80)&nbsp;
<img src="images/printer.gif" onclick="printDetailsfor132()">(132)&nbsp;
</td>
</tr>
	<tr>
	<td>
	<table width="100%">
	   <tr height="20"><td>
	  	<hdiits:hidden name="cmbAuditors" default="${param.cmbAuditors}"/>
	  </td></tr>
	  <c:choose>
	  <c:when test="${vitoListSize>0}">
	  		<tr class="Label2">
			 	<td align="center">
			 	<fmt:message key="VITOCOMMON.VITOCODE" bundle="${billprocLabels}"></fmt:message>
			 		 : <c:out value="${vitoCode}"></c:out> 
			 	</td>
			 </tr>
	  		<%System.out.println("Inside The True Conditon"); %>
		  <c:forEach var="vito" items="${vitoList}" >
		  	 <c:if test="${vito!=null}">
			<% List innerList =(ArrayList)pageContext.getAttribute("vito");
				Iterator it = innerList.iterator();
				BillTransitVO vo=(BillTransitVO)it.next();
				double grossAmount=0;
				int count=0;
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
						<table align="center" border="1" cellspacing="0" cellpadding="0" width="95%">
							<tr height="20" align="center" class="datatableheader">
							<%System.out.println("----------------Inside The JSP Page - di-----------------"); %>
							<td width="15%">&nbsp;<hdiits:caption captionid="CMN.RefNumber" bundle="${billprocLabels}"/>&nbsp;</td>	
								<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="10%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOKEN_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="10%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.MAJOR_HEAD" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>								
								<td width="20%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.BILL_TYPE" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
								<td width="10%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.CARDEX_NO" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
								<td width="30%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.DDO_NAME" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>
								<td width="15%">&nbsp;&nbsp;<hdiits:caption captionid="CMN.TOTAL_AMOUNT" bundle="${billprocLabels}"/>&nbsp;&nbsp;</td>	
						   </tr>
						   <c:forEach var="inner" items="${vito}" >
						   <% BillTransitVO billTranVo =(BillTransitVO)pageContext.getAttribute("inner");
						   			System.out.println("no of bill is:-"+billTranVo.getBillGrossAmount());
						   			count++;
						   			grossAmount=grossAmount + Double.parseDouble(billTranVo.getBillGrossAmount().toString());
						   			System.out.println("Gross Amount:-"+grossAmount);
								%>
						  	<tr align="center">
						  		<%System.out.println("----------------Inside The JSP Page - fro loop-----------------"); %>
						  		<td width="15%" align="center"><c:out value="${inner.refNum}"></c:out></td>	
								<td width="20%" align="center"><c:out value="${inner.billCntrlNo}"></c:out></td>	
								<td width="10%" align="right"><c:out value="${inner.tokenNo}"></c:out></td>	
								<td width="10%" align="right"><c:out value="${inner.majorHead}"></c:out></td>
								<td width="20%" align="center"><c:out value="${inner.billType}"></c:out></td>	
								<td width="10%" align="right"><c:out value="${inner.cardexNo}"></c:out></td>	
								<td width="30%" align="center"><c:out value="${inner.ddoName}"></c:out></td>	
								<td width="15%" align="right"><c:out value="${inner.billGrossAmount}"></c:out></td>	
							</tr>
							</c:forEach>	
							<tr class="Label2">
								<td colspan="7">&nbsp;&nbsp;
								<fmt:message key="VITOCOMMON.TOTALNOOFBILLS" bundle="${billprocLabels}"></fmt:message>
								 :<%=count%> </td>
								<td align="right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2"><%=grossAmount%></fmt:formatNumber></td>
								
							</tr>	
						</table>
					</td>
			</tr>
			</c:if>
		</c:forEach>
			<%--  
			  <tr>  			  	
				<td>
					<display:table list="${vitoList}" pagesize="12" requestURI="ifms.htm?actionFlag=insertVitoDtls&vitoType=146"
							   id="vo" excludedParams="ajax" varTotals="totals" export="flase" partialList="" style="width:100%">
							<display:setProperty name="paging.banner.placement" value="bottom"/>    
							<display:column class="oddcentre" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" headerClass="datatableheader" ><input name="audUserId" value="${vo.audUserId}" type="checkbox" > </display:column>	    
							<display:column property="srNo" class="oddcentre" titleKey="CMN.SR_NO" sortable="true" headerClass="datatableheader"  />
							<display:column property="auditorName" class="oddcentre" titleKey="CMN.AUDITOR_NAME" sortable="true" headerClass="datatableheader"></display:column>
							<display:column property="noOfBills" class="oddcentre" titleKey="CMN.NO_OF_BILLS" sortable="true" headerClass="datatableheader"  />
							<display:column property="totalAmount" class="oddcentre" titleKey="CMN.TOTAL_AMOUNT" sortable="true" headerClass="datatableheader"  />
						    <display:footer media="html">
						  </display:footer>
					</display:table>
				</td>
			</tr>	--%>
		<tr height="20">
				 <td></td>
		</tr>
		<tr>
			<td colspan="11" align="center">	
				<hdiits:button name="btnGenVito" type="button" value='<%=buttonBundle.getString("COMMON.GENERATEVITO")%>' onclick="validate()"/>	
				<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?actionFlag=getAuditorsList&pageName=forAudit'"></hdiits:button>
			</td>
		</tr>
		<tr height="20">
				 <td></td>
		</tr>
		</c:when>	  
		<c:otherwise>
			<%System.out.println("Inside The False Conditon"); %>
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
				<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?actionFlag=getAuditorsList&pageName=forAudit'"></hdiits:button>
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
				
	
	
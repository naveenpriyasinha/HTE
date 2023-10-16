 <%
try {
	
%>
<%@page import='com.tcs.sgv.eis.util.ConvertNumbersToWord ,java.util.Date'%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.math.MathContext"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.eis.valueobject.GPDifferenceArrearCustomVO"%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>

<c:set var="billNo" value="${resValue.billNo}" ></c:set>
<c:set var="deptName" value="${resValue.deptName}" ></c:set>
<c:set var="cityName" value="${resValue.cityName}" ></c:set>
<c:set var="desigName" value="${resValue.desigName}" ></c:set>
<c:set var="cardexCode" value="${resValue.cardexCode}" ></c:set>
<c:set var="empDataList" value="${resValue.empDataList}" ></c:set>
<c:set var="hrPaySalRevMst" value="${resValue.hrPaySalRevMst}" ></c:set>
<c:set var="employeeIdList" value="${resValue.employeeIdList}" ></c:set>
<c:set var="empRecCounterLst" value="${resValue.empRecCounterLst}" ></c:set>

<c:set var="empDtlVO"></c:set>
<c:set var="emptrnBillDtlVO"></c:set>

<script type="text/javascript" src="<c:url value="/script/eis/Address.js"/>"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>
<fmt:setBundle basename="resources.Payroll" var="payroll" scope="request" />
<hdiits:form name="outerGPDiffArrearPrint" validate="true" method="POST" action="">
<%
List empRecCounterLst = (List) pageContext.getAttribute("empRecCounterLst");
List empIdList = (List) pageContext.getAttribute("employeeIdList");
int empIdListSize = 0;
if(empIdList != null)
{
	empIdListSize = empIdList.size();
}
pageContext.setAttribute("empIdListSize",empIdListSize);

List empDataList = (List) pageContext.getAttribute("empDataList");
int empDataListSize = 0;
if(empDataList != null)
{
	empDataListSize = empDataList.size();
}
pageContext.setAttribute("empRecCounterLst",empRecCounterLst);

ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
int Employees_Per_Page = resourceBundle.getString("Employee_Per_Page") != null && !resourceBundle.getString("Employee_Per_Page").equals("") ? Integer.parseInt(resourceBundle.getString("Employee_Per_Page")) : 2 ;
long empId = 0;
long prevEmpId=-1;
int i = 0;
int j = 0;
int tmpEmpIDList = empIdListSize;
if(empIdListSize < Employees_Per_Page)
	Employees_Per_Page = empIdListSize;
int counter = 0;
int counter2 = 0; // this will give u the counter of for(k) loop to giv u per employee records
int counter3 = 1; // this will give u the counter of employees in for(j) loop to know u about last page employees
int pagePrint = (int) (Math.ceil(Double.valueOf((Double.valueOf(empIdListSize))/Employees_Per_Page)));
//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx pagePrint = " + pagePrint);
%>

<c:set var = "totalBasic" value="0" ></c:set>
<c:set var = "totalGP" value="0" ></c:set>
<c:set var = "totalDA" value="0" ></c:set>
<c:set var = "totalHRA" value="0" ></c:set>
<c:set var = "totalTotal" value="0" ></c:set>
<fmt:formatDate var="orderDate" pattern="dd/MM/yyyy" value="${hrPaySalRevMst.revOrderDate}"/>



 <%
for(i = 0 ; i < pagePrint ; i++)
	{
%>
	
<table width="100%"  align="center" >
<tr><td align = "center">

	<font size="5" face="Arial" >GENERAL ADMINISTRATION DEPARTMENT</font><br>

	<font size="2"  face="Arial" >Statements showing the ${hrPaySalRevMst.revReason}  vide F.D.G.R. No. ${hrPaySalRevMst.revOrderNo} ,  DATED :  ${orderDate} </font>
	&nbsp;&nbsp;&nbsp;&nbsp;
<font size="2">BILL NO.:-&nbsp;&nbsp; ${billNo}</font>

</td></tr>
</table>
<table width="100%" border =1   bordercolor="black"  style="BORDER-COLLAPSE: collapse;" >
<tr>
	<td width="20%" align="left">
		<font size="2" face="Arial">
			<table width="100%" cellpadding="0" cellspacing ="0">
				<tr>
					<td width="8%">Month</td>
					<td width="8%">Token Vr.No.</td>
					<td width="8%">Token Date</td>
					<td width="8%">Bill Amount</td>
				</tr>
			</table>
		</font>
	</td>
	<td align="center" width="5%"><font size="2" face="Arial">Basic Payable</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">GP Payable</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">DA Payable</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">HRA Payable</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">Total Payable</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">Basic Paid</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">GP Paid</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">DA Paid</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">HRA Paid</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">Total Paid</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">Basic Diff.</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">GP Diff.</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">DA Diff.</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">HRA Diff.</font></td>
	<td align="center" width="5%"><font size="2" face="Arial">Total Diff.</font></td>
	
</tr>
</table>
 <%
 GPDifferenceArrearCustomVO empDtlVOTemp = new GPDifferenceArrearCustomVO();
for(j = 0 ; j < Employees_Per_Page ; j++)
{
%>
<table width="100%" border =1   bordercolor="black"  style="BORDER-COLLAPSE: collapse;" cellpadding="0" cellspacing="0">
<%
for(int k = 0 ; k < Integer.parseInt(empRecCounterLst.get(counter2).toString()); k++)
{
	
	empDtlVOTemp = (GPDifferenceArrearCustomVO)	empDataList.get(counter);
	pageContext.setAttribute("empDtlVO",empDtlVOTemp);
	if(k == 0)
	{
%>

 <tr>
 <td colspan="16">
  <table width="100%" cellpadding="0" cellspacing="0">
 	<tr>
 		<td width="52%"><font size="2"  face="Arial" ><b>${empDtlVO.psrNo}. ${empDtlVO.empFullName}, ${empDtlVO.empDsgn}</font></b> </td>
 		<td width = "24%"><font size="2"  face="Arial" >GPF A/C No. :${empDtlVO.gpfNumber}</font></td>
 		<td width = "24%"><font size="2"  face="Arial" >PAN No.: ${empDtlVO.panCardNum}</font></td>
 	</tr>
 	</table>
 </td>
 </tr>
 <%
	}
 %>
<tr>
<td   width="21%" align="left"   >
<table width="100%" bordercolor="red">
<tr>
	<td width="8%">${empDtlVO.paidMonth}/${empDtlVO.paidYear}</td>
	<td width="8%">
				<c:choose>
				   <c:when test="${empDtlVO.billCntrlNo > 0}">
				     ${empDtlVO.billCntrlNo}
				   </c:when>
				   <c:otherwise>
				     &nbsp;
				   </c:otherwise>
                 </c:choose>
	</td>
	<fmt:formatDate var="trnbillDate" pattern="dd/MM/yyyy" value="${empDtlVO.billDate}"/>
	<td width="8%">${trnbillDate}</td>&nbsp;&nbsp;
	<td width="8%" align="right">
		<c:choose>
		   <c:when test="${empDtlVO.netBillAmount > 0}">
		    ${empDtlVO.netBillAmount}
		   </c:when>
		   <c:otherwise>
		     &nbsp;
		   </c:otherwise>
           </c:choose>				
	</td>
</tr>
</table>
</td>			
<%--    Data For Payable Amount    --%>
<td  align="right" width="5%"  >${empDtlVO.basicPayable}</td>
<td  align="right" width="5%"  >${empDtlVO.GPPayable}</td>
<td  align="right" width="5%"  >${empDtlVO.DAPayable}</td>
<td   align="right" width="5%" >${empDtlVO.HRAPayable}</td>
<c:set var="totalPayable" value = "${empDtlVO.basicPayable + empDtlVO.GPPayable + empDtlVO.DAPayable + empDtlVO.HRAPayable}"></c:set>
<td  align="right" width="5%"  >${totalPayable}</td>

<%--    Data For Paid Amount    --%>
<td  align="right" width="5%"  >${empDtlVO.basicPaid}</td>
<td  align="right" width="5%"  >${empDtlVO.GPPaid}</td>
<td  align="right" width="5%"  >${empDtlVO.DAPaid}</td>
<td   align="right" width="5%" >${empDtlVO.HRAPaid}</td>
<c:set var="totalPaid" value = "${empDtlVO.basicPaid + empDtlVO.GPPaid + empDtlVO.DAPaid + empDtlVO.HRAPaid}"></c:set>
<td  align="right" width="5%"  >${totalPaid}</td>

<%--    Data For Difference Amount    --%>
<c:set var="basicDiff" value = "${empDtlVO.basicPayable-empDtlVO.basicPaid}"></c:set>
<td  align="right" width="5%"  >${basicDiff}</td>
<c:set var="GPDiff" value = "${empDtlVO.GPPayable-empDtlVO.GPPaid}"></c:set>
<td  align="right" width="5%"  >${GPDiff}</td>
<c:set var="DADiff" value = "${empDtlVO.DAPayable-empDtlVO.DAPaid}"></c:set>
<td  align="right" width="5%"  >${DADiff}</td>
<c:set var="HRADiff" value = "${empDtlVO.HRAPayable-empDtlVO.HRAPaid}"></c:set>
<td   align="right" width="5%" >${HRADiff}</td>
<c:set var="totalPaidPayableDiff" value = "${(empDtlVO.basicPayable + empDtlVO.GPPayable + empDtlVO.DAPayable + empDtlVO.HRAPayable) - (empDtlVO.basicPaid + empDtlVO.GPPaid + empDtlVO.DAPaid + empDtlVO.HRAPaid)}"></c:set>
<td  align="right" width="5%"  >${totalPaidPayableDiff}</td>

<c:set var = "totalBasic" value = "${totalBasic + empDtlVO.basicPayable - empDtlVO.basicPaid}"></c:set>
<c:set var = "totalGP" 	  value = "${totalGP    + empDtlVO.GPPayable - empDtlVO.GPPaid}"></c:set>
<c:set var = "totalDA"    value = "${totalDA    + empDtlVO.DAPayable - empDtlVO.DAPaid}"></c:set>
<c:set var = "totalHRA"   value = "${totalHRA   + empDtlVO.HRAPayable - empDtlVO.HRAPaid}"></c:set>
<c:set var = "totalTotal" value = "${totalTotal + (empDtlVO.basicPayable + empDtlVO.GPPayable + empDtlVO.DAPayable + empDtlVO.HRAPayable) - (empDtlVO.basicPaid + empDtlVO.GPPaid + empDtlVO.DAPaid + empDtlVO.HRAPaid)}"></c:set>
</tr>


<%	
	
if(k == (Integer.parseInt(empRecCounterLst.get(counter2).toString()) - 1))
{
%>

<tr>
<td  colspan="8"><b> Rs. ${totalTotal}/-  to be credited in Cash.</b></td>			
<td  colspan="3" ><b>Total : </b></td>
<td   align = "right"  width = "5%"  ><b>${totalBasic}</b></td>
<td   align = "right"  width = "5%"  ><b>${totalGP}</b></td>
<td   align = "right"  width = "5%"  ><b>${totalDA}</b></td>
<td   align = "right"  width = "5%"  ><b>${totalHRA}</b></td>
<td   align = "right"  width = "5%"  ><b>${totalTotal}</b></td>


</tr>
</table>
<%
}
counter++;
}

%>
<c:set var = "totalBasic" value="0" ></c:set>
<c:set var = "totalGP" value="0" ></c:set>
<c:set var = "totalDA" value="0" ></c:set>
<c:set var = "totalHRA" value="0" ></c:set>
<c:set var = "totalTotal" value="0" ></c:set>
<%
counter2++;
 }
%>
<table  width="100%">
 <tr>
 <td align="right" >
 <table  width="25%">
 <tr align="right" ><td align="left"><br><br><br>${desigName}</td></tr>
  <tr  align="right" ><td align="left">${deptName}</td></tr>
   <tr align="right" ><td align="left">${cityName}</td></tr>
  <tr  align="right" ><td align="left">${cardexCode}</td></tr>
  </table>
  </td></tr>
  </table>
  <%
  if(i != (pagePrint - 1))
  {
  %>
 <DIV style="page-break-after:always">&nbsp; </DIV>
 <%
  }
 tmpEmpIDList = tmpEmpIDList - Employees_Per_Page;
 if(tmpEmpIDList < Employees_Per_Page)
 {
	 Employees_Per_Page = tmpEmpIDList;
 } 
	}
%>
<script type="text/javascript">
if ("${msg}" != null && "${msg}" != '') 
{
	alert("${msg}");
	var url = "./hrms.htm?actionFlag=getHomePage";
	document.outerArrearPrint.action = url;
	document.outerArrearPrint.submit();
}
</script>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
 <%
try {		
	
%>
<%@ page import='com.tcs.sgv.eis.util.ConvertNumbersToWord ,java.util.Date'%>


<%@page import="java.util.ResourceBundle"%>
<%@page import="java.math.MathContext"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrPayArrearPaybill,com.tcs.sgv.eis.valueobject.PaybillHeadMpg"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.eis.valueobject.ArrearReportCustomVO"%>
<html>
<head>
<title>
Outer Bill</title>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="hrpayArrearBill" value="${resValue.hrArrearPaybillList}" ></c:set>

<c:set var="designationList" value="${resValue.designationList}" ></c:set>
<c:set var="orderDate" value="${resValue.orderDate}" ></c:set>
<c:set var="arrearCustomVoList" value="${resValue.arrearCustomVoList}" ></c:set>

<c:set var="arrearBillListSize" value="${resValue.arrearBillListSize}" ></c:set>
<c:set var="orderName" value="${resValue.orderName}" ></c:set>
<c:set var="billNo" value="${resValue.billNo}" ></c:set>


<c:set var="desigName" value="${resValue.desigName}" ></c:set>
<c:set var="deptName" value="${resValue.deptName}" ></c:set>
<c:set var="cardexCode" value="${resValue.cardexCode}" ></c:set>
<c:set var="cityName" value="${resValue.cityName}" ></c:set>
<c:set var="ArrearDisplayName" value="${resValue.ArrearDisplayName}" ></c:set>


<c:set var="msg" value="${resValue.msg}" ></c:set>

 <script type="text/javascript" src="<c:url value="/script/eis/Address.js"/>">
</script>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
	
	<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>

<fmt:setBundle basename="resources.Payroll" var="payroll" scope="request" />







<%
int count=0; 
int inPageCount=0;
long empId = 0;
long prevEmpId=-1;
long firstEmpId = -1;
long showTotal=-1;

long finalPagesize=15;

double empArreardiff = 0;
double empPtDiff = 0;
double empDaInGpf = 0;
double empDaInCash= 0;

long counter = 0;

long pagebreak=1;
int skiptotal = 0;
boolean isNewPage=true;
boolean isNewEmployee=true;
long nxtEmpId=0;
%>
 <fmt:formatDate var="orderDate" pattern="dd/MM/yyyy" value="${orderDate}"/>
 <table width=100% align="center">
<tr><td colspan = 8>
<table width="100%"    align="center" >
<tr>
	<td align = "center">

	<font size="5" face="Arial" >GENERAL ADMINISTRATION DEPARTMENT</font><br>

	<font size="2"  face="Arial" >Statements showing the ${ArrearDisplayName} diff. vide F.D.G.R. No.${orderName},DATED: ${orderDate}</font>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<font size="2">BILL NO.:-&nbsp;&nbsp; ${billNo}</font>
	
	</td>
</tr>
</table>


<table width="100%" border =1   bordercolor="black"  style="BORDER-COLLAPSE: collapse;" >
<tr >
	<td width="27%" align="left">
	<font size="2" face="Arial">
	<table width="100%" cellpadding="0" cellspacing ="0">
	<tr>
		<td width="25%">Month</td>
		<td width="25%">Token Vr.No.</td>
		<td width="25%">Token Date</td>
		<td width="25%">Bill Amount</td>
	</tr>
	</table>
	</font>
	</td>
	<td align="center" width="18%"><font size="2" face="Arial">Basic </font></td>
	<td align="center" width="9%"><font size="2" face="Arial">${ArrearDisplayName} P'ble</font></td>
 	<td align="center" width="9%"><font size="2" face="Arial">${ArrearDisplayName} Paid</font></td>
  	<Td align="center" width="9%"><font size="2" face="Arial">${ArrearDisplayName} Diff.</font></Td>
  	<td  align="center" width="9%"><font size="2" face="Arial">PT</font></td>
 	<td align="center" width="9%"><font size="2" face="Arial">In GPF</font></td>
  	<td align="center" width="9%"><font size="2" face="Arial">In Cash</font></td>
</tr>
</table>




 <c:forEach var="arrearCustomVo" items="${arrearCustomVoList}">

<c:set var="trnBillRegister" value="${arrearCustomVo.paybillBillRegMpg.trnBillRegister}" ></c:set>


<%


ArrearReportCustomVO arrearCustomVo = (ArrearReportCustomVO)pageContext.getAttribute("arrearCustomVo");
ArrearReportCustomVO arrearCustomVoNew=null;
List arrearCustomVoList = (List)pageContext.getAttribute("arrearCustomVoList");
int recordCounter=1;
//System.out.print("::::::::::::::::::::::::::" +arrearCustomVoList.size());
long arrearBillListSize = Long.parseLong(pageContext.getAttribute("arrearBillListSize").toString());

long month = Math.round(arrearCustomVo.getPaybillHeadMpg().getMonth());
long year = Math.round(arrearCustomVo.getPaybillHeadMpg().getYear());
long allow0119old = Math.round(arrearCustomVo.getArrearBill().getPaybillId().getAllow0119());
long basic0102 = Math.round(arrearCustomVo.getArrearBill().getPaybillId().getBasic0102())+Math.round(arrearCustomVo.getArrearBill().getPaybillId().getBasic0101());
long basic0101 = Math.round(arrearCustomVo.getArrearBill().getPaybillId().getBasic0101());

long TotalPay = Math.round(basic0102+arrearCustomVo.getArrearBill().getPaybillId().getLs());
long arrearDiff = arrearCustomVo.getArrearAmtByEdpCoode(); 

long prevPaidAmt = arrearCustomVo.getPrevAmtByEdpCoode();  


long arrearNew = Math.round( arrearDiff + prevPaidAmt );

long PTax = Math.round(arrearCustomVo.getArrearBill().getDeduc9570());

long totalGPF =  Math.round(arrearCustomVo.getArrearBill().getDeduc9999() +
							arrearCustomVo.getArrearBill().getDeduc9998() +
							arrearCustomVo.getArrearBill().getDeduc9670() +
							arrearCustomVo.getArrearBill().getDeduc9620() +
							arrearCustomVo.getArrearBill().getDeduc9534() +
							arrearCustomVo.getArrearBill().getDeduc9531() 
                            );
long cashPaid = Math.round(arrearCustomVo.getArrearBill().getNetTotal());
count++;

empId = arrearCustomVo.getArrearBill().getHrEisEmpMst().getEmpId();

double grossAmt = arrearCustomVo.getArrearBill().getGrossAmt();
double netAmt = arrearCustomVo.getArrearBill().getNetTotal();

int index = arrearCustomVoList.indexOf(arrearCustomVo);

if(index<(arrearCustomVoList.size()-1))
{
	
	arrearCustomVoNew=(ArrearReportCustomVO)arrearCustomVoList.get(index+1);
	nxtEmpId=arrearCustomVoNew.getArrearBill().getHrEisEmpMst().getEmpId();
	//System.out.println("nxtEmpId" + nxtEmpId);
}
else
{
	
	nxtEmpId = -1;
}
if(index>0)
{
	arrearCustomVoNew=(ArrearReportCustomVO)arrearCustomVoList.get(index-1);
	prevEmpId =arrearCustomVoNew.getArrearBill().getHrEisEmpMst().getEmpId();
}
if(empId!=prevEmpId)
{
	
	isNewEmployee= true;
	
	
	
	boolean flag=true;
	while(flag)
	{
		
		if(index+recordCounter<arrearCustomVoList.size())	
		{
			arrearCustomVoNew = (ArrearReportCustomVO)arrearCustomVoList.get(index+recordCounter);
			long nextEmpId=arrearCustomVoNew.getArrearBill().getHrEisEmpMst().getEmpId();
			if(nextEmpId==empId)
			{
				recordCounter++;
			}
			else
			{
				flag=false;
			}
		}
		else
		{
			break;
		}
	}
	
	if((inPageCount+recordCounter)<=finalPagesize)
	{
		isNewPage=false;
		
	}
	else
	{
		isNewPage=true;
		
	}
}
else
{
	isNewEmployee = false;
}


%>

<%

if(isNewPage==true){
	inPageCount=0;%>


<table  width="100%" border="0">
 <tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
<tr>
 <td width ="75%">
</td> 
 <td align="right" >
 <table  width="100%" border ="0">
  <tr align="right" ><td align="left">${desigName}</td></tr>
  <tr  align="right" ><td align="left">${deptName}</td></tr>
   <tr align="right" ><td align="left">${cityName}</td></tr>
  <tr  align="right" ><td align="left">${cardexCode}</td></tr>
  </table>
  </td></tr>
  </table>
  </td></tr></table>
 <DIV style="page-break-after:always"></DIV> <!-- for page break in printing. -->
 <br/>
 <br/>

<table width=100% align="center">
<tr><td colspan = 8>
 
<table width="100%"    align="center" >
<tr><td align = "center">

	<font size="5" face="Arial" >GENERAL ADMINISTRATION DEPARTMENT</font><br>

	<font size="2"  face="Arial" >Statements showing the ${ArrearDisplayName} diff. vide F.D.G.R. No.${orderName},DATED: ${orderDate}</font>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<font size="2">BILL NO.:-&nbsp;&nbsp; ${billNo}</font>
	
	</td></tr>
 </table>


<table width="100%" border =1   bordercolor="black"  style="BORDER-COLLAPSE: collapse;" >
<tr >
<td width="27%" align="left"><font size="2" face="Arial">
<table width="100%" cellpadding="0" cellspacing ="0">
<tr>
	<td width="25%">Month</td>
		<td width="25%">Token Vr.No.</td>
			<td width="25%">Token Date</td>
				<td width="25%">Bill Amount</td>
</tr>
</table>
</font></td>
<td align="center" width="18%"><font size="2" face="Arial">Basic </font></td>
<td align="center" width="9%"><font size="2" face="Arial">${ArrearDisplayName} P'ble</font></td>
 <td align="center" width="9%"><font size="2" face="Arial">${ArrearDisplayName} Paid</font></td>
  <Td align="center" width="9%"><font size="2" face="Arial">${ArrearDisplayName} Diff.</font></Td>
  <td  align="center" width="9%"><font size="2" face="Arial">PT</font></td>
  <td align="center" width="9%"><font size="2" face="Arial">In GPF</font></td>
  <td align="center" width="9%"><font size="2" face="Arial">In Cash</font></td>
</tr>
</table>




<%
isNewPage=false;
} %>

<table width="100%" border =1   bordercolor="black"  style="BORDER-COLLAPSE: collapse;" cellpadding="0" cellspacing="0">

<%

if(isNewEmployee){
	//out.print("inPagecouunt>>"+inPageCount+"  recordcount"+recordCounter);
%>

	<tr >
 		<td colspan="8">
 			<br>
 		</td>
	</tr>
	<tr>
 	<td colspan="8">

 		<table width="100%" cellpadding="0" cellspacing="0">
 		<tr>
 			<td width="52%"><font size="2"  face="Arial" ><b>${arrearCustomVo.arrearBill.psrNo}. ${arrearCustomVo.arrearBill.hrEisEmpMst.orgEmpMst.empPrefix} ${arrearCustomVo.arrearBill.hrEisEmpMst.orgEmpMst.empFname} ${arrearCustomVo.arrearBill.hrEisEmpMst.orgEmpMst.empMname} ${arrearCustomVo.arrearBill.hrEisEmpMst.orgEmpMst.empLname}, ${arrearCustomVo.orgDesignationMst.dsgnShrtName }</font></b> </td>
 			<td width = "24%"><font size="2"  face="Arial" >
				GPF A/C No. :${arrearCustomVo.arrearBill.hrEisEmpMst.orgEmpMst.empGPFnumber}</font></td>
 			<td width = "24%"><font size="2"  face="Arial" >
				PAN No.: ${arrearCustomVo.panNo}</font></td>
 		</tr>
 		</table>
 
	</td>
 	</tr>
 <%  firstEmpId = 1; 
}


%>


	<tr>
 
		<td   width="15%" align="left"   >
			<table width="100%" cellpadding="0" cellspacing ="0">
				<tr>
					<td width="25%"><%=month%>/<%= year%></td>
					<td width="25%">
					<c:choose>
				   		<c:when test="${trnBillRegister.tokenNum>0}">
				    		 ${trnBillRegister.tokenNum}
				  		</c:when>
				   		<c:otherwise>
				    	 	&nbsp;
				   		</c:otherwise>
                 	</c:choose>
					</td>
		 	<fmt:formatDate var="trnbillDate" pattern="dd/MM/yyyy" value="${trnBillRegister.billDate}"/>
				<td width="25%">${trnbillDate}</td>&nbsp;&nbsp;
				<td width="25%" align="right">
				<c:choose>
				   <c:when test="${trnBillRegister.billNetAmount>0}">
				    ${trnBillRegister.billNetAmount}
				   </c:when>
				   <c:otherwise>
				     &nbsp;
				   </c:otherwise>
                 </c:choose>				
				</td>
			</tr>
		</table>


	</td>

<c:choose>
<c:when test="${arrearCustomVo.arrearBill.paybillId.basic0101 le 0}">

<c:choose>
<c:when test="${arrearCustomVo.arrearBill.paybillId.allow0119 le 0}">
<!-- <td   align="right" width="10%"   >< %=basic0102%>&nbsp;&nbsp;< %=allow0120old%>&nbsp;&nbsp;< %=TotalPay%></th>-->
<td   align="right" width="10%"   ><%= prevPaidAmt>0?TotalPay:""%></th>
</c:when>
<c:otherwise>
<!-- <td   align="right" width="10%"   >< %=basic0102%>&nbsp;&nbsp;< %=allow0119old%>&nbsp;&nbsp;< %=TotalPay%></th>-->
<td   align="right" width="10%"   ><%= prevPaidAmt>0?TotalPay:""%></th>
</c:otherwise>
</c:choose>
</c:when>
<c:otherwise>
<c:choose>
<c:when test="${arrearCustomVo.arrearBill.paybillId.allow0119 le 0}">
<!-- <td   align="right" width="10%"   >< %=basic0102%>&nbsp;&nbsp;< %=allow0120old%>&nbsp;&nbsp;< %=TotalPay%></th>-->
<td   align="right" width="10%"   ><%= prevPaidAmt>0?TotalPay:""%></th>
</c:when>
<c:otherwise>
<!-- <td   align="right" width="10%"   >< %=basic0102%>&nbsp;&nbsp;< %=allow0119old%>&nbsp;&nbsp;< %=TotalPay%></th>-->
<td   align="right" width="10%"   ><%= prevPaidAmt>0?TotalPay:""%></th>
</c:otherwise>
</c:choose>

</c:otherwise>
</c:choose>
<td  align="right" width="5%"  >
<%= prevPaidAmt>0?arrearNew:""%></td>
<td  align="right" width="5%"  ><%= prevPaidAmt>0?prevPaidAmt:""%></td>

<td  align="right" width="5%"   ><%=arrearDiff %></td>
<td   align="right" width="5%"   ><%=PTax%></td>
<td  align="right" width="5%"   ><%=totalGPF %></td>
<td  align="right" width="5%"   ><%=cashPaid%></td>
  </tr>
  <%inPageCount++;%>
 <%//inPageCount%>
 <% 
  count++;
 empArreardiff += arrearDiff;
 empPtDiff += PTax;
 empDaInGpf += totalGPF;
 empDaInCash += cashPaid;

  
  %>
 





<% 
	if(empId!=nxtEmpId ||nxtEmpId == -1){
%>


 
 <tr>
  <td width="25%" colspan=2><font size="2">
  <table width="100%" cellpadding="0" cellspacing ="0">
<tr>
	<td width="2%">Rs.</td>
		<td width="10%"  align="right"><b><%= empDaInGpf%>/-</b></td>
		<td width="10px">&nbsp;</td>
			<td>to be credited into G.P.F.</td>
				
</tr>
<tr>
	<td width="2%">Rs.</td>
		<td width="10%" align="right"><b><%=empDaInCash %>/-</b></td>
		<td width="px">&nbsp;</td>
			<td>to be paid in cash</td>
				
</tr>
</table>
</font>
   </td>
   <td colspan="2" align="center"  width="15%"><b>TOTAL  - ></b></td>
   <td colspan="1" align="right"  width="5%"><b><%=empArreardiff %></b></td>
   <td colspan="1" align="right"  width="5%"><b><%=empPtDiff%></b></td>
   <td colspan="1" align="right" width="5%"><b><%=empDaInGpf %></b></td>
      <td colspan="1" align="right" width="5%"><b><%=empDaInCash%></b></td>
  </tr>
 <%
 empArreardiff = 0;
 empPtDiff = 0;
 empDaInGpf = 0;
 empDaInCash= 0;
 
 } 
 else
 {
	 skiptotal=0; 
 }

 %>
</table>
</c:forEach>

<table  width="100%" border="0">
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
 <tr>
 <td width ="75%">
</td> 
 <td align="right" >
 <table  width="100%" border ="0">
  <tr align="right" ><td align="left"><br><br><br><br><br>${desigName}</td></tr>
  <tr  align="right" ><td align="left">${deptName}</td></tr>
   <tr align="right" ><td align="left">${cityName}</td></tr>
  <tr  align="right" ><td align="left">${cardexCode}</td></tr>
  </table>
  </td></tr>
  </table>

  
<script type="text/javascript">
<!--
if("${msg}"!=null&&"${msg}"!=''){
			alert("${msg}");
			window.close();
			}
			
			

//-->
</script>
<script>
//window.print();
</script>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</body>
</html>
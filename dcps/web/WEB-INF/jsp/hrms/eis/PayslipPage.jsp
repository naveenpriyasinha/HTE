 <%
try {
%>
<%@page import="java.util.List" %>
<%@page import="java.util.Date" %>
<html>
<head>
<title>
Payslip</title>
<%@ page import="com.tcs.sgv.eis.util.ConvertNumbersToWord" %>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%!


long grossAmt=0;
long totDeduc=0;
long totalDeduc=0;
private String getDisplayName(int month){
	switch(month){	
		case 1: return "January";
		case 2: return "February";
		case 3: return "March";
		case 4: return "April";
		case 5: return "May";
		case 6: return "June";
		case 7: return "July";
		case 8: return "August";
		case 9: return "September";
		case 10: return "October";
		case 11: return "November";
		case 12: return "December";
		default : return "None";//will never come here	
	}
}
 
%>

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

	</head>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="paySlipArgsAllowList" value="${resValue.paySlipAgrsAllowList}" > </c:set>
<c:set var="paySlipArgsDeducList" value="${resValue.paySlipAgrsDeducList}" > </c:set>
<c:set var="lstPayslipCustomVO" value="${resValue.lstPayslipCustomVO}" > </c:set>

<%

List payslipList = (List)pageContext.getAttribute("lstPayslipCustomVO");
long payslipSize=0;
long size=0;
if(	payslipList!=null)
{
	payslipSize = payslipList.size();
}
%>

<hdiits:form name="frmPayslip" validate="true" method="POST"
	action="">

<c:forEach items="${lstPayslipCustomVO}" var="payslipCustomVO">
<c:set var="paySlipArgsAllowList" value="${resValue.paySlipAgrsAllowList}" > </c:set>
<c:set var="paySlipArgsDeducList" value="${resValue.paySlipAgrsDeducList}" > </c:set>
<c:set var="loanArgsType" value="l" > </c:set>
<fmt:formatDate value="${payslipCustomVO.hrPayPayslip.paySlipDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="payslipDate"/>

    <table width="100%">
     <tr>
      <td align="center"> <h1>
       GOVERNMENT OF MAHARASHTRA </h1>
      </td>
     </tr>
     
     <tr>
      <td  width="100%" align="center"> <h2> Finance Department </h2> </td> 
     </tr>
     
     <tr>
      <td align="center"> Mantralaya, Mumbai </td>
     </tr>
     <tr> 
      <td>
      <table width="100%">
      <tr>
      <td align="right"  width="58%" ><h2>Employee's Payslip</h2> </td>
      <td width="17%"> </td>
      <td width="25%"><b>No.</b></td></tr>
    
     <c:set var="monthDigit" value="${payslipCustomVO.hrPayPayslip.month}" > </c:set>
     <%
        int monthDigit = new Integer(pageContext.getAttribute("monthDigit").toString());
        String monthName = getDisplayName(monthDigit);
        pageContext.setAttribute("month",monthName);
      %>
      <tr><td><b>Month:-</b> ${month} / ${payslipCustomVO.hrPayPayslip.year}</td> 
      <td width="17%"> </td>
      <td><b>Bill No. </b> ${payslipCustomVO.hrPayPayslip.billNo}</td>
      </tr>
      </table>
      </td>
     </tr>     
     <tr>     
    </table>
    <hr>
    <table cellpadding="0" cellspacing="0" border="0">
     <tr>
      <td colspan="2"> <b>Name: </b>${payslipCustomVO.hrPayPayslip.hrEisEmpMst.orgEmpMst.empFname} ${payslipCustomVO.hrPayPayslip.hrEisEmpMst.orgEmpMst.empMname} ${payslipCustomVO.hrPayPayslip.hrEisEmpMst.orgEmpMst.empLname},
      ${payslipCustomVO.designationName}         
       </td>
     </tr>
     <tr> </tr>
     <tr>
     <td width="29%"> <b> Increment Date &nbsp;: </b>  ${payslipCustomVO.incrementDate} </td>         
     <td width="29%"> <b> Quarter No.</b>&nbsp;&nbsp;${payslipCustomVO.hrPayPayslip.hrQuaterTypeMst.quaType} </td>
     <td width="29%"> <b>PAN No. </b>${payslipCustomVO.hrPayPayslip.itAccNo} </td> </td>
    </tr>

    <tr>
    
    <td><b>GPF A/C No.: </b>  ${payslipCustomVO.hrPayPayslip.gpfAccNo}     </td>     
     <td> <b>HBA A/C No.:</b><c:out value="${payslipCustomVO.hbaAccNo}"/></td>
     <td> <b>MCA A/C No.: </b><c:out value="${payslipCustomVO.mcaAccNo}"/></td>
    </tr>
    <tr>
     <td> <b> Token No. </b></td>
     <td> <b> Date: </b>${payslipDate} </td>
     <td> <b> Bill Amt. Rs. </b>${payslipCustomVO.hrPayPayslip.billAmt}</td>
     <td> <b> Budget Head </b>${payslipCustomVO.hrPayPayslip.budgetHead}</td>
    </tr>
    </table>
    <hr>
    <table width="100%" border="0">
    <tr>
     <td width="30%" > <b> EARNING: </b></td>
     <td width="37%"> <b>GOVT. DEDUCTION: </b></td>
     <td width="33%"> <b> NON GOVT. DEDUCTION:</b> </td>
    </tr>
   </table>
   <hr>
   <table width="100%" cellspacing="0" border="0">
    <tr>
     <td width="8%"> <b> Details: </b></td>
     <td width="12%" align="right"> <b> Amount: </b></td>
     <td width="2%">&nbsp; </td>
     <td width="11%"><b> Details: </b></td>
     <td width="10%" align="right"> <b> Amount: </b></td>
     <td width="5%" align="center"> <b>Inst. No</b></td>
     <td width="1%">&nbsp; </td>
     <td width="8%"> <b> Details: </b></td>
     <td width="8%" align="right"> <b> Amount: </b></td>
     <td width="8%">&nbsp;</td>
    </tr>
   </table>
     <hr>

    <table width="95%" cellspacing="2" border="0">
     <tr>
      <td width="26%" valign="top">
     <% grossAmt=0; %>
       <table width="100%" border="0">
       <c:forEach items="${paySlipArgsAllowList}" var="paySlipArgsAllowList">
       <tr>
        <td width="30%"> <b>${paySlipArgsAllowList.displayName}:</b></td>
        <td width="40%" align="right"> ${payslipCustomVO.hrPayPayslip[paySlipArgsAllowList.propertyName]} </td>
        <c:set var="allowArgs" value="${payslipCustomVO.hrPayPayslip[paySlipArgsAllowList.propertyName]}"> </c:set>
        <%
          long allowArgs = new Long(pageContext.getAttribute("allowArgs").toString());
          grossAmt = grossAmt + allowArgs;
        %>
       </tr>
      </c:forEach>
      
      <tr>
        <td colspan="2"> <hr> </td>  
      </tr>

      <tr>
       <td> <b>Gross Amt. </b></td>
       <td align="right"> <%= grossAmt %> </td>       
     </tr>
     <tr>
       <td colspan="2"> <hr> </td>
     </tr>
     </table>
    
     </td>
     
     <td width="2%"></td>
     
     <td width="35%" valign="top">               
    <% totDeduc=0; %> 
     <table width="100%" border="0">
      <c:forEach items="${paySlipArgsDeducList}" var="paySlipArgsDeducList">
       <tr>
        <td width="40%"> <b>${paySlipArgsDeducList.displayName}:</b></td>
        <td width="40%" align="right" width="30%"> ${payslipCustomVO.hrPayPayslip[paySlipArgsDeducList.propertyName]} </td>
        <c:choose>
        <c:when test="${paySlipArgsDeducList.argsType == loanArgsType}" >
  	        <td width="20%" align="right"> <c:set var="argsName" value="${paySlipArgsDeducList.propertyName}Inst" /> ${payslipCustomVO[argsName]}</td> 
        </c:when>
        <c:otherwise>
	        <td width="20%" align="right">  </td> 
        </c:otherwise> 
        </c:choose>    
        <c:set var="deducArgs" value="${payslipCustomVO.hrPayPayslip[paySlipArgsDeducList.propertyName]}"> </c:set>
        <%
          long deducArgs = new Long(pageContext.getAttribute("deducArgs").toString());
          totDeduc = totDeduc + deducArgs;
        %>
       </tr>
      </c:forEach>
          
      <tr>
    <td colspan="2">
     <hr>
    </td>
    </tr>
               
    <tr>
     <td> <b>Total Deduc.: </b></td>
    <td align="right"> <%= totDeduc %> </td>
   </tr>
   
    <tr>
    <td colspan="2">
     <hr>
    </td>
    </tr>
 
     <tr>
      <td> <b>Net Amount.: </b></td>
   <td align="right"> ${payslipCustomVO.hrPayPayslip.netTotal} </td> 
   <td align="right"> </td>
     </tr>
     </table>
     
    </td>
             
    <td width="22%" valign="top">
    <table width="100%" border="0">
     <tr>     
     <td width="45%"> <b>Society - Old :</b> </td>     
          <td width="25%" align="right"> ${payslipCustomVO.oldSocietyAmt} </td>
    </tr>
    
    <tr>     
     <td> <b>Society - New :</b> </td>     
      <td align="right"> ${payslipCustomVO.newSocietyAmt} </td>
    </tr>
    
    <tr>    
     <td> <b>KARM.BANK:</b> </td>     
      <td align="right"> ${payslipCustomVO.karmBankAmt} </td>
    </tr>
    
    <tr>          
     <td> <b>NAGR.BANK:</b> </td>          
      <td align="right"> ${payslipCustomVO.nagBankAmt} </td>
    </tr>
    
    <tr>             
     <td> <b>L.I.C:</b> </td>
      <td align="right"> ${payslipCustomVO.licAmount} </td>
    </tr>    

      <tr>     

     <td colspan="2">  <hr></td>
    </tr>
     
    <tr>    
     <td align="center"> <b>Total Deduc.</b> </td>
     <td align="right"> ${payslipCustomVO.totNonGovDedcAmt} </td>
    </tr>
    
    <tr>     

     <td colspan="2">  <hr></td>
    </tr>

     <tr>
     <td align="center"> <b>Net Payable</b> </td>
      <td align="right"> ${payslipCustomVO.netPayableAmt} </td>
    </tr>
    
    

    <tr>      
     <td colspan="2"> <hr> </td>  
    </tr>
     
    <tr>
      <td colspan="2"> <hr> </td>   
    </tr>
    
   <tr>
      <td colspan="2"> </td>   
    </tr>
    
       <tr>
      <td colspan="2"> </td>   
    </tr>
    
       <tr>
      <td colspan="2"> </td>   
    </tr>
    
    <tr>
      <td colspan="2">  </td>   
    </tr>
    
   <tr>
      <td colspan="2"> </td>   
    </tr>
    
       <tr>
      <td colspan="2"> </td>   
    </tr>
    
       <tr>
      <td colspan="2"> </td>   
    </tr>
    
    <tr>
     <td colspan="2" align="right">  <b>SECTION OFFICER </b>  </td>
    </tr>
    
    <tr>
     <td colspan="2" align="right"> <b>GENERAL ADMN. DEPT </b> </td>
    </tr>
    </table>
    </td>
    <td width="5%"></td>

   </table>

   <hr>
    <table width="100%">
    <tr>
        <td> <b> Rupees</b></td> <td><b> ${payslipCustomVO.strNetPayableAmt} only. </b></td> </tr>
 </table>
   
    <hr>
    <table width="70%">
    <tr>
     <td width="15%">
     <b> Bank A/C: </b> ${payslipCustomVO.bankAccNo}
     </td>
          
     
    </tr>
    </table>
    <table width="100%" border="0" align="center">
       <tr>
     <td align="center" colspan="11"> <h1> Special Information </h1> </td>
    </tr>
    <tr>
     <td colspan="8"> </td>
    </tr>
    <tr>
      <td colspan="11" align="center"> For Record Only. Not Given as Official Record </td>
    </tr>
        <tr>
     <td colspan="8"> </td>
    </tr>
    <tr align="center">
    <td width="9%" align="center"> <b> Advance </b> </td>
    <td width="9%" align="right"> <b> Total </b> </td> 
    <td width="9%" align="right"> <b> Reco. </b> </td>
    <td width="3%"> </td>
    <td width="9%" align="center"> <b> Advance </b> </td>
    <td width="9%" align="right"> <b> Total </b> </td>
    <td width="9%" align="right"> <b> Reco. </b> </td>
    <td width="3%"> </td>
    <td width="9%" align="center"> <b> Advance </b> </td>
    <td width="9%" align="right"> <b> Total </b> </td>
    <td width="9%" align="right"> <b> Reco. </b> </td>
   </tr>
    
   <tr> <td> </td> </tr>
   
   <tr>
   <td align="center"> G.P.F. </td>
   <td align="right"> ${payslipCustomVO.totalGpfAdvAmt} </td>
   <td align="right"> ${payslipCustomVO.recGpfAdvAmt} </td>
   <td> </td>
   <td align="center"> Oil GPF </td>
   <td align="right"> 0 </td>
   <td align="right"> 0 </td>
   <td> </td>
   <td align="center"> Festival </td>
   <td align="right"> ${payslipCustomVO.totalFestAdvAmt} </td>
   <td align="right"> ${payslipCustomVO.recFestAdvAmt} </td>
   <td> </td>
   </tr>
   
   <tr>   
   <td align="center"> HBA Adv.</td>
   <td align="right"> ${payslipCustomVO.totalHbaPrinAmt} </td>
   <td align="right"> ${payslipCustomVO.recHbaPrinAmt} </td>
   <td> </td>
   <td align="center"> MCA Adv.</td>
   <td align="right"> ${payslipCustomVO.totalMcaPrinAmt} </td>
   <td align="right"> ${payslipCustomVO.recMcaPrinAmt} </td>
   <td> </td>
   <td align="center"> Foodgrain </td>
  <td align="right"> ${payslipCustomVO.totalFoodAdvAmt} </td>
   <td align="right"> ${payslipCustomVO.recFoodAdvAmt} </td>
   <td> </td>
   </tr>
   
   <tr>   
   <td align="center"> HBA Int.</td>
   <td align="right"> ${payslipCustomVO.totalHbaIntAmt} </td>
   <td align="right"> ${payslipCustomVO.recHbaIntAmt} </td>
   <td> </td>
   <td align="center"> MCA Int.</td>
   <td align="right"> ${payslipCustomVO.totalMcaIntAmt} </td>
   <td align="right"> ${payslipCustomVO.recMcaIntAmt} </td>
   <td> </td>
   <td align="center"> Fan Adv. </td>
  <td align="right"> ${payslipCustomVO.totalFanAdvAmt} </td>
   <td align="right"> ${payslipCustomVO.recFanAdvAmt} </td>
   <td> </td>
   </tr>
   </table>
    
  
  <hr> 
  <table width="100%">
  <tr>
  <td align="center" colspan="9"> <h2> DEATAILS UPTO THIS MONTH THROUGH PAYBILLS ONLY FOR TAX PLANNING </h2> </td>
  </tr>
  <tr>
  <td colspan="9"> <hr> </td>
  </tr>
  
  <tr>
   <td width="5%"> <h3>Gross</h3> </td>
 
   <td> <b>T.A.</b></td>
   <td> <b>P.T.</b> </td>
   <td> <b>G.P.F.</b> </td>
   <td> <b>H.B.A </b></td>
   <td> <b>HBA Int.</b> </td>
   <td> <b>SGI</b> </td>
   <td> <b>LIC</b> </td>
   <td> <b>I.Tax</b> </td>
  </tr>

  <tr> <td><h3> Salary </h3></td>
  <td colspan="8"> <hr> </td></tr>
  <tr>
  <td> ${payslipCustomVO.currYearGrossAmt} </td>
  <%--
  <td> <%=totalTA %></td>
   <td> <%=totalPT%></b> </td>
   <td> <%=totalGPF %> </td>
   <td> <%=totalHBA%></td>
   <td>  0  </td>
   <td>  <%= totalSGI %>  </td>
   <td> <%=totalLIC %> </td>
   <td> <%=totalIT %> </td>
   --%>
     <td> ${payslipCustomVO.currYearTraAllowAmt}</td>
   <td> ${payslipCustomVO.currYearProffTaxwAmt} </td>
   <td> ${payslipCustomVO.currYearPfAmt} </td>
   <td> ${payslipCustomVO.currYearHbaPrinAmt} </td>
   <td> ${payslipCustomVO.currYearHbaIntAmt} </td>
   <td>   ${payslipCustomVO.currYearSgiAmt}  </td>
   <td> ${payslipCustomVO.currYearLicAmt} </td>
   <td> ${payslipCustomVO.currYearIncomeTaxAmt} </td>
   
     </tr>
  </table>
   <hr width="100%">
   <%
   size++;
   if(size<payslipSize)
   {
   %>

   <DIV style="page-break-after:always"></DIV> <!-- for page break in printing. -->
<%} %>
 </c:forEach>

 </div>	
 
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
</html>
     
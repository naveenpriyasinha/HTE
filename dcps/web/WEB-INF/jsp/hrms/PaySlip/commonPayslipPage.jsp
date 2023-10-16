 <%
try {
System.out.println("In Payslip page");
%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>
<%!  long location_Id=0;
     long Dept_Id=0;  %>
<html>
<head>
<title>
Payslip</title>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%
long grossAmt=0;
long totDeduc=0;
long totalDeduc=0;
long nonGovDeduc=0;
%>

<%
Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	location_Id=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
	System.out.println("Location Id is :--->"+location_Id);
 	pageContext.setAttribute("location_Id",location_Id);
 	
 	  ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");
 		
 	 Dept_Id=Integer.parseInt(constantsBundle.getString("GAD")); 
  	pageContext.setAttribute("Dept_Id",Dept_Id);
	System.out.println("Dept Id is " + Dept_Id);
	%>

 <script type="text/javascript" src="<c:url value="/script/eis/Address.js"/>">
</script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="commonLables" scope="request"/>
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
<c:set var="paySlipDataList" value="${resValue.paySlipDataList}" > </c:set>
<c:set var="signDataName" value="${resValue.signDataName}" > </c:set>
<c:set var="signDsgnName" value="${resValue.signDsgnName}" > </c:set>
<c:set var="signDeptName" value="${resValue.signDeptName}" > </c:set>

<%
List payslipList = (List)pageContext.getAttribute("paySlipDataList");
long payslipSize=0;
long size=0;
long lDeducArgs=0;
if(	payslipList!=null)
{
	payslipSize = payslipList.size();
}
%>

<hdiits:form name="frmPayslip" validate="true" method="POST"
	action="">

<c:forEach items="${paySlipDataList}" var="paySlipDataList">
<c:set var="paySlipArgsAllowList" value="${resValue.paySlipArgsAllowList}" > </c:set>
<c:set var="paySlipArgsDeducList" value="${resValue.paySlipArgsDeducList}" > </c:set>
<c:set var="paySlipAgrsNonGovDeducList" value="${resValue.paySlipAgrsNonGovDeducList}" > </c:set>
<table width="99%" border= "0" style="font-size:10.5px;">
	<tr>
    	<td>
		   <table width="100%" border= "0"  style="font-size:10.5px;">
     <tr>
      <td align="center" colspan="9">
       <h1>
       PAY SLIP 
       </h1>
       <h1>
       GOVERNMENT OF GUJARAT 
       </h1>
      </td>
     </tr>
     <tr>
      <td width="18%"><b>NAME OF OFFICE </b></td><td width="1%">:-</td><td  width="33%" align="left" colspan=2>&nbsp;${paySlipDataList.deptName}</td>
      <td width="15%" colspan=2>&nbsp;</td>
      <c:set var="billNo" value="${paySlipDataList.billNo}" />
      <%
      String billNumber = (String)pageContext.getAttribute("billNo");
      StringTokenizer st = new StringTokenizer(billNumber,"(");
      if(st.countTokens()>1)
      {	
      st.nextToken();
      String billNo = st.nextToken();
      pageContext.setAttribute("billNumber",billNo);
      
      }
      else
      {
    	  pageContext.setAttribute("billNumber",billNumber);
      }	  
      
      
      %>
      <td width="15%"><b>BILL NO</b></td><td width="1%">:-</td><td  width="17%" align="left">&nbsp;${billNumber}</td>
       </tr>
      <tr>
      <td><b>FOR THE MONTH OF</b></td><td>:-</td><td align="left" colspan=2><b>&nbsp; ${paySlipDataList.billMonth}-${paySlipDataList.billYear}</b></td>
      <td colspan=2>&nbsp;</td>
      <!--  <td><b>BILL DATE</b></td><td>:-</td><td align="left">${paySlipDataList.billDate} </td>       -->
      <td><b>BILL DATE</b></td><td>:-</td><td align="left"> &nbsp; </td> 
      </tr>
     
    
  <tr>
    <td colspan="9"><hr></td>
  </tr>
    
      <tr>
      <td><b>NAME OF EMPLOYEE </b></td><td>:-</td><td align="left" colspan=2>&nbsp;<b>${paySlipDataList.empName}</b></td>
      <td colspan=2>&nbsp;</td>
      <td ><b>EMP NO</b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.empNo}</td>
       </tr>
      <tr>
      <td><b>DESIGNATION</b></td><td>:-</td><td align="left" colspan=2>&nbsp; ${paySlipDataList.dsgnName}</td>
      <td colspan=2>&nbsp;</td>
      <td>&nbsp;</td><td>&nbsp;</td><td align="left">&nbsp; </td>      
      </tr>
      <tr>
    <td colspan="9"><hr></td>
  </tr>
      <tr>
      <td><b>BIRTH DATE </b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.birthDate}</td>
      <td width="18%" align="left"><b>P.A.N. NO.</b></td><td width="1%">:-</td><td align="left" width="15%">&nbsp;${paySlipDataList.panNo}</td>
      <td><b>GPF A/C NO.</b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.GPFAccNo}</td>
      </tr>
      <tr>
      <td><b>JOIN DATE </b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.doj}</td>
      <td><b>QUARTER NO.</b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.quarterNo}</td>
      <td><b>HBA A/C NO.</b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.HBAAccNo}</td>
      </tr>
      <tr>
      <td><b>INCREMENT DATE </b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.incrDate}</td>
      <td></td><td></td><td></td>
      <td><b>MCA A/C NO.</b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.MCAAccNo}</td>
      </tr>
      <tr>
      <td><b>PAY SCALE </b></td><td>:-</td><td align="left">&nbsp; ${paySlipDataList.scale}</td>
      <td align="left" colspan="3" valign="middle"><b>&nbsp;${paySlipDataList.branch}</b></td>
      <td><b>BANK A/C NO.</b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.bankAccNo}</td>
      </tr>
    <td colspan="9"><hr></td>
      <tr>
      <td><b>BUDGET HEAD</b></td><td>:-</td><td align="left">&nbsp; ${paySlipDataList.budgetHead}</td>
      <td  align="left"><b>VOUCH. NO/DATE</b></td> <td width="1%">:-</td><td align="left">&nbsp;${paySlipDataList.vouchDtls}</td>
      <td><b>AMT. OF BILL</b></td><td>:-</td><td align="left">&nbsp;${paySlipDataList.billNetAmt}</td>
      </tr>      
    </table>
    <hr>
    <table width="100%" border="0"  style="font-size:11.5px;">
    <tr>
     <td width="3%" > <b> Sr. </b></td>
     <td width="5%"> <b>Allowances </b></td>
     <td width="10%" align="right"> <b>Rs.</b> </td>
     <td width="7%">&nbsp;</td>
     <td width="3%" > <b> Sr. </b></td>
     <td width="5%"> <b>Allowances </b></td>
     <td width="10%" align="right"> <b>Rs.</b> </td>
     <td width="30%">&nbsp;</td>
    </tr>
   </table>
   <hr>

    <table width="100%" cellspacing="2" border="0">
     <tr>
      <td width="35%" valign="top">
     <% grossAmt=0; %>
       <table width="100%" border="0">
       <c:forEach items="${paySlipArgsAllowList}" var="paySlipArgsAllowList">
       <c:if test="${paySlipArgsAllowList.dispOrder<=10}">
       <tr>
        <td width="5%"> <b>${paySlipArgsAllowList.dispOrder}</b></td>
        <td width="30%"> <b>${paySlipArgsAllowList.displayName}</b></td>
        <td width="1%">&nbsp;:-</td>
        <td width="10%" align="right"> ${paySlipDataList[paySlipArgsAllowList.propertyName]} </td>
        <td width="15%">&nbsp;</td>
        <c:set var="allowArgs" value="${paySlipDataList[paySlipArgsAllowList.propertyName]}"> </c:set>
        <%
          long allowArgs = new Long(pageContext.getAttribute("allowArgs").toString());
          grossAmt = grossAmt + allowArgs;
        %>
       </tr>
       </c:if>
      </c:forEach>
      </table>
      </td>
      <td width="35%" valign="top">
       <table width="100%" border="0"  style="font-size:10.5px;">
       <c:set var="paySlipArgsAllowList" value="${resValue.paySlipArgsAllowList}" > </c:set>
       
       <c:forEach items="${paySlipArgsAllowList}" var="paySlipArgsAllowList">
       <c:if test="${paySlipArgsAllowList.dispOrder>10}">
       <tr>
        <td width="5%"> <b>${paySlipArgsAllowList.dispOrder}</b></td>
        <td width="35%"> <b>${paySlipArgsAllowList.displayName}</b></td>
        <td width="1%">:-</td>
        <td width="10%" align="right"> ${paySlipDataList[paySlipArgsAllowList.propertyName]} </td>
        <td width="15%">&nbsp;</td>
        <c:set var="allowArgs" value="${paySlipDataList[paySlipArgsAllowList.propertyName]}"> </c:set>
        <%
          long allowArgs = new Long(pageContext.getAttribute("allowArgs").toString());
          grossAmt = grossAmt + allowArgs;
        %>
       </tr>
       </c:if>
      </c:forEach>
        <%
          for(int allw=18;allw<21;allw++)
          {	  
        %>
       <tr>
        <td width="5%"> <b><%=allw %></b></td>
        <td width="35%"> &nbsp;</td>
        <td width="1%">:-</td>
        <td width="10%" align="right"> &nbsp; </td>
        <td width="15%">&nbsp;</td>
       </tr>
        <%
           }
        %>
      </table>
      </td>

      <td width="30%" valign="top">
       <table width="100%" border="0"  style="font-size:10.5px;">
       <tr>
        <td width="5%">&nbsp;</td>
        <td width="10%">&nbsp;</td>
        <td width="20%" align="right">&nbsp;</td>
        <td width="15%">&nbsp;</td>  
       </tr>       
      </table>
      </td>

      <tr>
        <td colspan="5"> <hr> 
 		
 		<table width="100%" border="0"  style="font-size:11.5px;">
      <tr>
     <td width="37%" ><b>GROSS PAY. </b></td>
     <td width="10%" align="right"> <b> <%= grossAmt %></b> </td>
     <td width="30%" >&nbsp;</td> 
     </table>
 		
     
     <tr>
       <td colspan="5"> <hr> </td>
     </tr>
     </table>
    
    <table width="100%" border="0"  style="font-size:11.5px;">
    <tr>
     <td width="3%" > <b> Sr. </b></td>
     <td width="5%"> <b>Deductions </b></td>
     <td width="10%" align="right"> <b>Rs.</b> </td>
     <td width="7%">&nbsp;</td>       
     <td width="3%" > <b> Sr. </b></td>
     <td width="5%"> <b>Deductions </b></td>
     <td width="10%" align="right"> <b>Rs.</b> </td>
     <td width="30%">&nbsp;</td>   
      
    </tr>
   </table>
   <hr>
    <table width="100%" cellspacing="2" border="0"  style="font-size:10.5px;">
     <tr>
      <td width="35%" valign="top">
     <% totDeduc=0; %>
       <table width="100%" border="0"  style="font-size:10.5px;">
       <c:forEach items="${paySlipArgsDeducList}" var="paySlipArgsDeducList">
       <c:if test="${paySlipArgsDeducList.dispOrder<=15}">
       <tr>
        <td width="5%"> <b>${paySlipArgsDeducList.dispOrder}</b></td>
        <td width="30%"> <b>${paySlipArgsDeducList.displayName}</b></td>
        <td width="1%">:-</td>
        <td width="10%" align="right"> ${paySlipDataList[paySlipArgsDeducList.propertyName]} </td>
        <td width="15%">&nbsp;</td>  
        <c:set var="deducArgs" value="${paySlipDataList[paySlipArgsDeducList.propertyName]}"> </c:set>
        <%
          long deducArgs = new Long(pageContext.getAttribute("deducArgs").toString());
        totDeduc = totDeduc + deducArgs;
        %>
       </tr>
       </c:if>
      </c:forEach>
      </table>
      </td>
      <td width="35%" valign="top">
       <table width="100%" border="0"  style="font-size:10.5px;">
       <c:set var="paySlipArgsDeducList" value="${resValue.paySlipArgsDeducList}" > </c:set>
       
       <c:forEach items="${paySlipArgsDeducList}" var="paySlipArgsDeducList">
       <c:if test="${paySlipArgsDeducList.dispOrder>15}">
       <tr>
        <td width="5%"> <b>${paySlipArgsDeducList.dispOrder}</b></td>       
        <td width="35%"> <b>${paySlipArgsDeducList.displayName}</b></td>
        <td width="1%">:-</td>
        <c:set value="payRecv" var="payRecv"></c:set>
        <td width="10%" align="right">${paySlipDataList[paySlipArgsDeducList.propertyName]} </td>
        <td width="15%">&nbsp;</td>   
        <c:set var="deducArgs" value="${paySlipDataList[paySlipArgsDeducList.propertyName]}"> </c:set>
        <%
          
          lDeducArgs = new Long(pageContext.getAttribute("deducArgs").toString());
      	  totDeduc = totDeduc + lDeducArgs;
        %>
       </tr>
       </c:if>
      </c:forEach>
        <%
          for(int ded=26;ded<31;ded++)
          {	  
        %>
       <tr>
        <td width="5%"> <b><%=ded %></b></td>
        <td width="35%"> &nbsp;</td>
        <td width="1%"> &nbsp;</td>
        <td width="10%" align="right"> &nbsp; </td>
        <td width="15%">&nbsp;</td>
       </tr>
        <%
           }
        %>
      </table>
      </td>
      
      <td width="30%" valign="top">
       <table width="100%" border="0"  style="font-size:10.5px;"> 
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.ocaCurrInst}/${paySlipDataList.ocaTotalInst}</td>
        <td width="20%" align="right" >${paySlipDataList.ocaRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.ocaPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.ocaIntCurrInst}/${paySlipDataList.ocaIntTotalInst}</td>
        <td width="20%" align="right" >${paySlipDataList.ocaIntRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.ocaIntPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.hbaCurrInst}/${paySlipDataList.hbaTotalInst}</td>
        <td width="20%"  align="right">${paySlipDataList.hbaRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.hbaPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.hbaIntCurrInst}/${paySlipDataList.hbaIntTotalInst}</td>
        <td width="20%"  align="right">${paySlipDataList.hbaIntRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.hbaIntPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.mcaCurrInst}/${paySlipDataList.mcaTotalInst}</td>
        <td width="20%"  align="right">${paySlipDataList.mcaRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.mcaPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.mcaIntCurrInst}/${paySlipDataList.mcaIntTotalInst}</td>
        <td width="20%" align="right" >${paySlipDataList.mcaIntRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.mcaIntPrin}</td>  
       </tr>

        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">&nbsp;</td>
        <td width="20%" >&nbsp;</td>
        <td width="15%">&nbsp;</td>  
       </tr>
       
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">&nbsp;</td>
        <td width="20%" >&nbsp;</td>
        <td width="15%">&nbsp;</td>  
       </tr>
       
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">&nbsp;</td>
        <td width="20%" >&nbsp;</td>
        <td width="15%">&nbsp;</td>  
       </tr>
       
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.gpfCurrInst}/${paySlipDataList.gpfTotalInst}</td>
        <td width="20%"  align="right">${paySlipDataList.gpfRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.gpfPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">&nbsp;</td>
        <td width="20%" >&nbsp;</td>
        <td width="15%">&nbsp;</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.festCurrInst}/${paySlipDataList.festTotalInst}</td>
        <td width="20%" align="right" >${paySlipDataList.festRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.festPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.foodCurrInst}/${paySlipDataList.foodTotalInst}</td>
        <td width="20%" align="right" >${paySlipDataList.foodRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.foodPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.fanCurrInst}/${paySlipDataList.fanTotalInst}</td>
        <td width="20%"  align="right">${paySlipDataList.fanRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.fanPrin}</td>  
       </tr>
        <tr>       
        <td width="5%">&nbsp;</td>
        <td width="10%">${paySlipDataList.fanIntCurrInst}/${paySlipDataList.fanIntTotalInst}</td>
        <td width="20%"  align="right">${paySlipDataList.fanIntRecvd}</td>
        <td width="15%" align="right">${paySlipDataList.fanIntPrin}</td>  
       </tr>
      </table>
      </td>
      
 
      <tr>
        <td colspan="5"> <hr> </td>  
      </tr>
      
      
	<table width="100%" border="0"  style="font-size:11.5px;">
      <tr>
     <td width="33%" align="center"> <b>GROSS PAY:- &nbsp;<%= grossAmt %></b> </td>
    <td width="33%" align="center"> <b>TOTAL DEDUC:- &nbsp; <%= totDeduc %></b> </td>
     <td width="34%" align="center"  colspan="2"><b>NET PAY:- &nbsp; ${paySlipDataList.netPay}<b></td> 
     </tr>
     </table>
      
<table width="100%" border="0"  style="font-size:11.5px;">
      <tr>
     <td width="100%" align="center" ><hr></td> 
     </tr>
     </table>
    
    

    <table width="100%" cellspacing="2" border="0"  style="font-size:10.5px;">
     <tr>
      <td width="50%" valign="top">
       <table width="100%" border="0">
        <% nonGovDeduc=0; %>
       <c:forEach items="${paySlipAgrsNonGovDeducList}" var="paySlipAgrsNonGovDeducList">
       <c:set var="nonGovArgs" value="${paySlipDataList[paySlipAgrsNonGovDeducList.propertyName]}"> </c:set>
       <%
          long nonGovArgs = new Long(pageContext.getAttribute("nonGovArgs").toString());
        nonGovDeduc = nonGovDeduc + nonGovArgs;
        pageContext.setAttribute("nonGovDeduc",nonGovDeduc);
        %>
       <c:if test="${paySlipAgrsNonGovDeducList.dispOrder<=5}">
       <tr>
        <td width="5%"> <b>&nbsp;</b></td>
        <td width="40%"> <b>${paySlipAgrsNonGovDeducList.displayName}</b></td>
        <td width="5%" align="right"> ${paySlipDataList[paySlipAgrsNonGovDeducList.propertyName]} </td>
        <c:set var="nonGovArgs" value="${paySlipDataList[paySlipAgrsNonGovDeducList.propertyName]}"> </c:set>
        <%
          /*long nonGovArgs = new Long(pageContext.getAttribute("nonGovArgs").toString());
        nonGovDeduc = nonGovDeduc + nonGovArgs;
        pageContext.setAttribute("nonGovDeduc",nonGovDeduc);*/
        %>
       </tr>
       </c:if>
      </c:forEach>
      
      
      <%
       	long totalNetAmtAFterNonGovt =  grossAmt-totDeduc-nonGovDeduc; 
      pageContext.setAttribute("totalNetAmtAFterNonGovt",totalNetAmtAFterNonGovt);
       
        %>   
      <tr>
        <td colspan="2" style="padding-left:25px;font-size:11.5px;"> <b> NET PAYMENT IN BANK </b></td> <td align="right"><span id="netAmountInBank"><b>${totalNetAmtAFterNonGovt}  </span>  </b></td>
     </tr>
     
     <tr>
     <td colspan="4"><font color="red">*</font> Generated by TCS - IWDMS.</td>
     </tr>
      
      </table>
      
      
      
      </td>
      <td width="50%" valign="top">
       <table width="100%" border="0" cellpadding="0" cellspacing="0">
       <c:set var="paySlipAgrsNonGovDeducList" value="${resValue.paySlipAgrsNonGovDeducList}" > </c:set>
       <c:forEach items="${paySlipAgrsNonGovDeducList}" var="paySlipAgrsNonGovDeducList">
       <c:if test="${paySlipAgrsNonGovDeducList.dispOrder>5}">
       <tr>
       <td width="5%"> <b>&nbsp;</b></td>
         <td width="40%"> <b>${paySlipAgrsNonGovDeducList.displayName}</b></td>
        <td width="5%" align="right"> ${paySlipDataList[paySlipAgrsNonGovDeducList.propertyName]} </td>
        <c:set var="nonGovArgs" value="${paySlipDataList[paySlipAgrsNonGovDeducList.propertyName]}"> </c:set>
        <%
        lDeducArgs=0;
         lDeducArgs = new Long(pageContext.getAttribute("nonGovArgs").toString());
         //nonGovDeduc = nonGovDeduc + lDeducArgs;  commented by Urvin.
        pageContext.setAttribute("nonGovDeduc",nonGovDeduc);
        %>
       
       </tr>
       </c:if>
      </c:forEach>
      <%
		long totalNetAmountAFterNonGovt =  grossAmt-totDeduc-nonGovDeduc; 
      pageContext.setAttribute("totalNetAmountAFterNonGovt",totalNetAmountAFterNonGovt);
       
        %> 
      <hdiits:hidden id="totalNetAmount" name="totalNetAmount" default="${totalNetAmountAFterNonGovt}"/>
     <tr>
     <td colspan="3" align="center" style="padding-bottom:5px;align-vertical:middle"><b><br><br><br><br><br>(${signDataName})<br>
     				${signDsgnName},<br>
     				${signDeptName}.
     </b></td></tr>
      </table></td>
      <%--<tr>
        <td colspan="2"> <hr> </td>  
      </tr>

      <tr>
       <td> <b>non Government Deduction </b></td>
       <td align="right"> <%= nonGovDeduc %> </td>    --%>   
     </tr>
   </table>
    

<script>
	document.getElementById("netAmountInBank").innerHTML = <b>document.getElementById("totalNetAmount").value;
</script>
     

    </td>
             

     
    </table>
</td></tr></table>
   <%--<hr>--%>
    
  
   <%
   size++;
   if(size<payslipSize)
   {
   %>
   <DIV style="page-break-after:always"> &nbsp; </DIV> 
<%} %>
 </c:forEach>
   <%
    if(size==0)
   {
   %>
     <br>
     <br>
     <table width="100%">
     <tr>
       <td align="center">
       <b>
        No Records found
       </b> 
        </td>
     </tr>
     </table>
    
  <%
   } 
  %>
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
     
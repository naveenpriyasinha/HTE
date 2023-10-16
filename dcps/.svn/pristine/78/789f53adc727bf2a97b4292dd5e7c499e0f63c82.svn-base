 <%
try {		
	
%>
<%@ page import='com.tcs.sgv.eis.util.ConvertNumbersToWord ,java.util.Date'%>


<%@page import="java.util.ResourceBundle"%>
<html>
<head>
<title>
Outer Bill</title>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


 <script type="text/javascript" src="<c:url value="/script/eis/Address.js"/>">
</script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.CommonLables_en_US" var="commonLables_en_US" scope="page"/>
<fmt:message var="DEPARTMENT" key="DEPARTMENT" bundle="${commonLables_en_US}" scope="request"> </fmt:message>
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
	<%!
	   long totalExp=0;
	   long totalRec=0;
	   long totDeducA=0;
	   long totDeducB=0;
	   long totalNetAmt=0;
	   
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
	<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>
<%!  long location_Id=0,Dept_Id=0;  %>
<fmt:setBundle basename="resources.Payroll" var="payroll" scope="request" />
	<%
Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	location_Id=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
	//System.out.println("Location Id is :--->"+location_Id);
 	pageContext.setAttribute("location_Id",location_Id);
 	
 	  ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");
 		
 	 //Dept_Id=Integer.parseInt(constantsBundle.getString("GAD")); 
  	pageContext.setAttribute("Dept_Id",Dept_Id);
	
	%>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="expObjHds" value="${resValue.expObjHds}" > </c:set>
<c:set var="expEdpList" value="${resValue.expEdpList}" > </c:set>
<c:set var="rcptObjHds" value="${resValue.rcptObjHds}" > </c:set>
<c:set var="recObjHds" value="${resValue.recObjHds}" > </c:set>
<c:set var="classtoprint" value="${resValue.classtoprint}" > </c:set> 
<c:set var="lObjTrnBudHdDtls" value="${resValue.lObjTrnBudHdDtls}" > </c:set>

<c:set var="totalExpenditure" value="${0}"/>
<c:set var="totalRecovery" value="${0}"/>
<c:set var="totalDeducA" value="${0}"/>
<c:set var="totalDeducB" value="${0}"/>
<c:set var="netAmt" value="${0}"/>

<%-- Added By Urvin Shah --%>

<c:set var="desigName" value="${resValue.desigName}"/>
<c:set var="deptName" value="${resValue.deptName}"/>
<c:set var="cardexCode" value="${resValue.cardexCode}"/>
<c:set var="cityName" value="${resValue.cityName}"/>
<c:set var="headChargable" value="${resValue.headChargable}"/>
<c:set var="billNo" value="${resValue.billNo}"/>
<%-- Ended Urvin Shah --%>
<c:set var="demandCode" value="${resValue.demandCode}"/>
<c:set var="mjrHeadCode" value="${resValue.mjrHeadCode}"/>
<c:set var="subMjrCode" value="${resValue.subMjrCode}"/>
<c:set var="mnrCode" value="${resValue.mnrCode}"/>
<c:set var="subHeadCode" value="${resValue.subHeadCode}"/>
<c:set var="ddoCode" value="${resValue.ddoCode}"/> 
<c:set var="billSelDate" value="${resValue.billSelDate}"/>
<c:set var="subHeadDesc" value="${resValue.subHeadDesc}"/>
<c:set var="mjrHeadDesc" value="${resValue.mjrHeadDesc}"/>
<c:set var="subMjrHeadDesc" value="${resValue.subMjrHeadDesc}"/>
<c:set var="mnrHeadDesc" value="${resValue.mnrHeadDesc}"/>
<c:set var="demandDesc" value="${resValue.demandDesc}"/>
<c:set var="billCtrlNo" value="${resValue.billCtrlNo}"/>
<c:set var="monthNo" value="${resValue.monthNo}"/>
<c:set var="yearSel" value="${resValue.yearSel}"/>

<c:set var="mca" value="${resValue.mca}"/>
<c:set var="mcaInt" value="${resValue.mcaInt}"/>
<c:set var="hba" value="${resValue.hba}"/>
<c:set var="hbaInt" value="${resValue.hbaInt}"/>

<c:set var="billType" value="${resValue.billType}"/>
<c:set var="arrearbill" value="${resValue.arrearbill}"/>

<c:set var="selected_expenditure" value="${resValue.selected_expenditure}"/>
<c:set var="selected_Fund" value="${resValue.selected_Fund}"/>
<c:set var="budTypeSel" value="${resValue.budTypeSel}"/>
<hdiits:form name="frmPrintOuter" validate="true" method="POST"
	action="">
<%
long totalExp=0;
long totalRec=0;
long totDeducA=0;
long totDeducB=0;
long totalNetAmt=0;
long totalNetAmtTemp=0;
long totDeducBTemp=0;
long totDeducA_Temp=0;
%>
	<c:set var="monthDigit" value="${monthNo}" > </c:set>
    <%
	    int monthDigit = new Integer(pageContext.getAttribute("monthDigit").toString());
	    String yearDisplay = pageContext.getAttribute("yearSel").toString();
	    if(yearDisplay.length()>3)
	    yearDisplay=yearDisplay.substring(2,4);
	    String monthDisplay = "0"+monthDigit;
	    if(monthDigit>10)
	    	monthDisplay=""+monthDigit;	
        String monthName = getDisplayName(monthDigit);
        pageContext.setAttribute("month",monthName);
        pageContext.setAttribute("monthDisplay",monthDisplay);
        pageContext.setAttribute("yearDisplay",yearDisplay);
      %>

<table width="97%"  border="1" style="font-size: 11px" align="right"> 
<tr>
<!-- table for Left part of Outer-->
<td width="50%">
<!-- for Heading -->${classtoprint}
 <table  border="0" align="center" width="100%">
  <tr>
   <td align="center" colspan="2"> <h1> <!--BILL TRANSIT REGISTER SR.NO.__________ DATE_______ --></h1> </td>
  </tr>
  <tr>
   <%-- <td align="center"><font size="2"><b>FORM G. T. R. 30</b></font> <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Bill Register No. ${billNo}   </b></td>
   --%>
   <td align="center"><font size="2"><b>FORM G. T. R. 30</b><br> <b>Bill No</b> :${billNo} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Bill Register No.    </b></font></td>
   </tr>
   <tr>
      <td colspan="2" align="center">  <i> (See Rule 176(1)) </i> </td>
      </tr>

   
      <tr>
         <td colspan="2">Pay Bill for the Establishment__________________Name of Office <u><c:out value="${deptName}"></c:out>,<c:out value="${cityName}"></c:out></u></td>
         </tr>

       <c:choose>

    <c:when test="${billType eq 'paybill' or billType eq '2500339' or billType eq '2500340' or billType eq '2500341' or billType eq ''}">
      <tr>
      	<td colspan="2">For the month of <b>${month}-${yearSel}</b><hr></td></tr>
  </c:when>
  <c:otherwise>
   <fmt:formatDate value="${arrearbill.salRevId.revEffcFrmDate}" var="fromDate" pattern="MMM-yyyy"/>
   <fmt:formatDate value="${arrearbill.salRevId.revEffcToDate}" var="toDate" pattern="MMM-yyyy"/>
   
      <tr>
      	<td colspan="2"><b>${arrearbill.salRevId.revReason}</b><hr></td></tr>
  
  
  </c:otherwise>
    
    </c:choose>

 </table>
  <!--HEading table end -->
 
 <!-- for under Heading Part -->
 <table border="0" width="100%">
  <tr>
  <td  colspan="7" align="center">
    <table brder="5" width="100%" align="center">
  	<tr align="center">
  	<td align="center" colspan="4">
	<b><i> For use in Treasury</i><b></td>

  	</tr>
  	<tr>
  	<td align="left"><b> Name of the Treasury </b>
  	</td>
  	</tr>
  	<tr>
  	<td align="left"> Bill Transit Reg. Sr. No.
  	</td>
  	<td align="left"> Date :
  	</td>
  	<td align="left"> Token No.
  	</td>
  	<td align="left"> Date :
  	</td>
  	
  	</tr>
  	<tr>
  	<td align="left" >
  	</td>
  	</tr>
  	<tr>
  	<td align="left"> Bill Transit Reg. Sr. No.
  	</td>
  	<td align="left"> Date :
  	</td>
  	<td align="left"> Token No.
  	</td>
  	<td align="left"> Date :
  	</td>
  	
  	</tr>
  	<tr>
  	<td align="left" >
  	</td>
  	</tr>
  	<tr>
  	<td align="left"> Bill Transit Reg. Sr. No.
  	</td>
  	<td align="left"> Date :
  	</td>
  	<td align="left"> Token No.
  	</td>
  	<td align="left"> Date :
  	</td>
  	
  	</tr>
  	
  	
  	
  </table>
  
</td></tr>
  <tr>
 <td colspan="7">
 <hr>
 </td>
            </tr>
  </table>
 
 
 <!-- Under Heading Part End -->
 
 <!-- Third table "Computer Input Data" Start -->
 <table  border="0" width="100%" align="center">
 <tr align="center"><td colspan="2"> <font size="2"> COMPUTER INPUT DATA</font> </td> </tr>
<tr align="center">
  <td align="center" colspan="2"><b><i> (To be filled in by Treasury)</b></i></td>
 <tr>
 <td>
 1.&nbsp; District &nbsp;<b> 71 </b> </td>
 <td>
 2.&nbsp; Month & Year  &nbsp;<b>${monthDisplay} ${yearDisplay}</b>
 <%--
  <td rowspan="1"> 1.&nbsp; District &nbsp;<b> 71 </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   
  2.&nbsp; Month & Year  &nbsp;<b>${billSelDate}</b></td> --%>
 </tr>
<tr>
<td> </td>
 <td> 3.&nbsp; Voucher No. </td>
 
</tr>
<tr>
 <td colspan="2"> <hr> </td>
 </tr>



 <!-- Third table end -->
 
 <!-- Fourth Table for Details -->

<td width="50%" valign="top"> 
<%--<table width="100%"  border="0" style="border-collapse: collapse; border: dashed;"> --%>
<table width="100%"  border="0"> 

 <tr>
  <td width="50%"> <b>4. Controlling Officer </b></td>
  <td width="50%"> 0000 </td>
 </tr>
 <tr>  
  <td> <b> 5. Class Of Expenditure </b> </td>
  <td><b> 1 </b></td>
 </tr> 
 <tr>
  <td> <b> 6. Fund  </b></td>
  <td><b> 3 </b></td>
 </tr>
 <tr>
  <td> <b> 7. Drawing Officer </b> </td>
  <td> 012 </td>
 </tr>
 <tr>
  <td> <b> 8. Demand No. </b> </td>
  <td> ${demandCode} </td>
 </tr>
 <tr>
  <td> <b> 9. Type of Budget. </b> </td>
  <td>   <b>${budTypeSel}</b></td>
 </tr>
 <tr>
  <td> <b> 10. Scheme No. </b> </td>
  <td> ${resValue.TrnBillBudheadDtls.schemeNo} </td>
 </tr>
 <tr>
  <td> <b> 11. Head Chargeable</b> </td>
  <td> ${headChargable}  </td>
 </tr>

  <tr>
  <td> <b> Sector </b></td>
  <td> </td>
 </tr>
  <tr>
  <td> <b> Demand No </b> </td>
  <td> ${demandCode}<!---${demandDesc}--></td>
 </tr>
   <tr>
  <td> <b> Major Head </b> </td>
  <td> ${mjrHeadCode}-${mjrHeadDesc} </td>
 </tr>
  <tr>
  <td> <b> Sub M.Head </b></td>
  <td>   
  <c:choose>
  <c:when  test="${subMjrCode eq 00}">
    ${subMjrCode}- NIL
  </c:when>
  <c:otherwise>
    ${subMjrCode}-${subMjrHeadDesc} 
  </c:otherwise>
  </c:choose> 
  </td>
 </tr>
  <tr>
  <td> <b> Minor Head </b> </td>
  <td> ( ${mnrCode} ) -${mnrHeadDesc} </td>
 </tr>
  <tr>
  <td> <b> Sub Head  </b> </td>
  <td> ${subHeadCode}-${subHeadDesc}</td>
 </tr>
 <tr>
 <td> <b> Detailed Head </b></td>
 <!-- <td> 00 </td>
 -->
 <td> Salaries </td>
 </tr>
  
 <tr>
 <td colspan="2">
  <hr> 
  </td>
  </tr>
  </table>
  <table width="100%" border="0" style="border-collapse: collapse; border: line;">
  <tr>
   <td align="right"></td>
   <td> Rs. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ps. </td>
  </tr>
  <tr>
   <td width="100%" > 
    Budget Allotment for 20&nbsp;&nbsp;&nbsp;&nbsp;-20&nbsp;&nbsp;&nbsp;&nbsp;    </td>
     <td> </td>
  </tr>
  <tr>
   <td  width="100%" > Expenditure Including this bill    </td>
     <td> ____________ </td>
  </tr>
  <tr>
   <td  width="100%"  colspan="2" > </td>
  </tr>
  <tr> 
   <td  width="100%" > Balance available </td>
   <td> ____________ </td>
  </tr>
  <tr>
   <td  width="100%"  colspan="2" >
    <hr> 
   </td>
  </tr>
  <tr>
   <td align="center" width="100%"  colspan="2" >
    For Use in A.G. Office 
    </td>    
   </tr>
   <tr>
    <td > Admitted for Rs. </td>
    <td> ____________ </td>
   </tr>
   <tr>
    <td> Objected for Rs. </td>
    <td> ____________ </td>
   </tr>
   <tr>
    <td> Reasons For Objection </td>
    <td> ____________ </td>
   </tr>
  
   
   
   <tr>
   <td align="left" width="33%" colspan="2">
   <br><br><br><table width="100%">
   <tr><td> Auditor </td>
   
   
   
    <td align="center"> Section Officer</td>
   
    <td align="center"> Gazetted Officer</td>
   
 
   </tr> </table></td> </tr>

</table>
</td>
 
 <td width="70%" valign="top">
  <table width="100%" align="center"  border="1" style="border-collapse: collapse; border: dashed; font-size: 11px">
   <tr>
	 <th align="center"> Budget Code </th>
     <th width="40%" align="center"> Object of Expenditure </th>
    <th align="center"> Edp Code </th>
    <th align="center"> Amount </th>
   </tr>
   <tr>
   <td>&nbsp;&nbsp;0100</td><td  colspan="3"><b>&nbsp;&nbsp;Salaries :</b></td>
   </tr>
   <c:forEach var="billEdpVO" items="${expObjHds}" varStatus="dExpcounter">
   <tr>   
   <td width="10%">
    &nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
   </td>	
   <td width="24%">
    &nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;    
   </td>
   <td width="16%">
    &nbsp;&nbsp;<c:out value="${billEdpVO.edpCode} [+]"/>&nbsp;
   </td>	
   <td align="right" width="12%">
	&nbsp;<c:out value="${billEdpVO.edpAmt}"/> 
	 <c:set var="totalExpenditure" value="${billEdpVO.edpAmt}" > </c:set> 
        <%
           totalExp = totalExp +  new Long(pageContext.getAttribute("totalExpenditure").toString());
         %>
	</td>   
   </tr>
   </c:forEach>
   
   <tr>
    <td> </td>
    <td align="center"> <b>  </b> </td> <td></td>
    <td align="right"><b>  <%=totalExp %> </b> </td>
    </tr>
    <tr> <td> </td> </tr>
    <c:forEach var="billEdpVO" items="${recObjHds}">
   <tr>   
   
   
   
   <td width="10%">
   <c:choose>
      <c:when  test="${billEdpVO.edpCode eq '0105'}">
      		&nbsp;&nbsp;<c:out value="0102"/>&nbsp;
      </c:when>
      <c:otherwise>
      	&nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
      </c:otherwise>
      </c:choose>

   </td>	<c:choose>
      <c:when  test="${billEdpVO.edpCode eq '0101'}">
   			<td>
    &nbsp;&nbsp;Recovery of Pay.&nbsp;    
   </td>	
   </c:when>   
   <c:otherwise>
   <td>
    &nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;    
   </td>	
   </c:otherwise>
   </c:choose>
      <td>
      
      <c:choose>
      <c:when  test="${billEdpVO.edpCode eq '0105'}">
      		&nbsp;&nbsp;<c:out value="0102 [-]"/>&nbsp;
      </c:when>
      <c:otherwise>
      	&nbsp;&nbsp;<c:out value="${billEdpVO.edpCode} [-]"/>&nbsp;
      </c:otherwise>
      </c:choose>
      
   </td>	
   <td align="right">
	&nbsp;<c:out value="${billEdpVO.edpAmt}"/> 
	 <c:set var="totalRecovery" value="${billEdpVO.edpAmt}" > </c:set> 
        <%
           totalRec = totalRec +  new Long(pageContext.getAttribute("totalRecovery").toString());
         %>
	</td>   
   </tr>
   </c:forEach>
   
   <c:forEach var="billEdpVO" items="${rcptObjHds}">
 <c:set var="edpCat" value="${billEdpVO.edpCategory}"/>
 <%
    String edpCat = (String)pageContext.getAttribute("edpCat");
 pageContext.setAttribute("edpCat",edpCat);
 %>
   <c:if test="${edpCat == 'A'}">
	 <c:set var="totalDeducA_Temp" value="${billEdpVO.edpAmt}" > </c:set> 
        <%
        totDeducA_Temp = totDeducA_Temp +  new Long(pageContext.getAttribute("totalDeducA_Temp").toString());
         %>
   </c:if>
   </c:forEach>
   
   

 <c:forEach var="billEdpVO" items="${rcptObjHds}">
 <c:set var="edpCatB" value="${billEdpVO.edpCategory}"/>
 <%
    String edpCatB = (String)pageContext.getAttribute("edpCatB");
 pageContext.setAttribute("edpCatB",edpCatB);
 %>
   <c:if test="${edpCatB == 'B'}">
   
	 <c:set var="totalDeducB_Temp" value="${billEdpVO.edpAmt}" > </c:set> 
        <%
        totDeducBTemp = totDeducBTemp +  new Long(pageContext.getAttribute("totalDeducB_Temp").toString());
         %>
   </c:if>
   </c:forEach>
   
   <%
     totalNetAmtTemp = (totalExp - totalRec) - (totDeducA_Temp + totDeducBTemp);
    
   %>
    
   <tr>
    <td> </td> <td></td>
     <td align="center"> <b> Gross Total : </b> </td>
    <td align="right"><b>  <%=totalExp - totalRec %> </b> </td>
    </tr></table>
  *Please See Note for other object head of expenditure as shown reverse.
  <br><center><i>Please Issue Cheques shown below  </i></center>
  <table width="100%" border='0'>
    <tr><td> 1 In favour of Drawing Officer for Rs._______________________&nbsp; Ps._____ </td><td> </td></tr>
    <tr><td> 2 In favour of Officers.</td><td> </td></tr>
      <tr><td>(I)Shri____________________ for Rs._________________________&nbsp; Ps._____</td></tr>
	<tr><td>(II)Smt.___________________ for Rs.______________________&nbsp; Ps._____</td></tr>
   <tr><td> 
<%--     	(III)As per cheque list attached for<b> Rs.______<u><%=totalExp - totalRec %></u>&nbsp; Ps._____</b> --%>
(III)As per cheque list attached for &nbsp;&nbsp;&nbsp;  <b> Rs. <%=totalNetAmtTemp %><u></u></td></tr>

<tr><td colspan="2" align="right" style="padding-right: 30px">Total<b>&nbsp; Rs._________________________<u>
      <%--	<%=totalNetAmtTemp %> --%></u></b></td></tr>
<tr><td colspan="2" align="right"><b><br><br><br>
     <table width="100%" border='0' align="left">
      <tr>
    <td align="left" colspan="2" width="100%" style="padding-left:85px"><br><b> ${desigName} </b> </td></tr>
  
  <!-- modification by ravysh -->
  
  <tr><td align="left" colspan="2" style="padding-left:85px"> <b> ${deptName}, </b> </td></tr>
   	<tr><td align="left" colspan="2" style="padding-left:85px"> <b> ${cardexCode}  </b> </td>
   </tr>
   <!-- <tr><td align="left" colspan="2" style="padding-left:85px"> <b> Gene.Admin.Deptt., </b> </td></tr>
   	<tr><td align="left" colspan="2" style="padding-left:85px"> <b> P.G. Code : 03  </b> </td>
   </tr> -->
   <!-- modification ends -->
   </table>
      </td>
    </tr>
 </table>
  </td>
</table>
 </td>

 <!--  Fourth Details table End -->


<td width="25%" valign="top">

<table  border="1" style="border-collapse: collapse; border: dashed;">
 <tr>
 <th width="58%" align="center"> Deduction 'A' </th>
 <th width="21%" align="center"> Edp Code </th>
 <th  width="21%" align="center"> Amount </th>
 </tr>
 <tr>
 <c:forEach var="billEdpVO" items="${rcptObjHds}">
 <c:set var="edpCat" value="${billEdpVO.edpCategory}"/>
 <%
    String edpCat = (String)pageContext.getAttribute("edpCat");
 pageContext.setAttribute("edpCat",edpCat);
 %>
   <c:if test="${edpCat == 'A'}">
   <tr>      
   <td>
    &nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp; <br>
    <c:choose>
      <c:when  test="${billEdpVO.edpCode eq '9591'}">
      		   <span style="padding-left:100px"> Prin: <c:out value="${hba}"/></span>
      		<br><span style="padding-left:100px"> &nbsp;Int: <c:out value="${hbaInt}"/></span>
      </c:when>
      <c:when  test="${billEdpVO.edpCode eq '9592'}">
      		 <span style="padding-left:100px"> Prin: <c:out value="${mca}"/></span>
      		<br><span style="padding-left:100px">&nbsp;Int: <c:out value="${mcaInt}"/></span>
      </c:when>
      </c:choose>   
   </td>	
   <td>
    &nbsp;&nbsp;<c:out value="${billEdpVO.edpCode} [-]"/>&nbsp;
   </td>	
   <td align="right">
    <%-- :::::::::::::::::: hemant ::::::::::::::::::::--%>
	
    <c:choose>  
      <c:when  test="${billEdpVO.edpCode eq '9583' and deptName eq DEPARTMENT and billEdpVO.edpAmt gt 0}">
      		 &nbsp;<c:out value="36"/> 
      		 <c:set var = "forAisSIS" value="YES"></c:set>
      </c:when>
      <c:when  test="${billEdpVO.edpCode eq '9584' and deptName eq DEPARTMENT and forAisSIS eq 'YES'}">
      		 &nbsp;<c:out value="84"/> 
      		<c:set var = "forAisSIS" value=" "></c:set>
      </c:when>
      <c:otherwise>
		&nbsp;<c:out value="${billEdpVO.edpAmt}"/> 
      </c:otherwise>
      </c:choose>


		 <c:set var="totalDeducA" value="${billEdpVO.edpAmt}" > </c:set> 
        <%
        totDeducA = totDeducA +  new Long(pageContext.getAttribute("totalDeducA").toString());
         %>
	</td>   
   </tr>
   </c:if>
   </c:forEach>
   <tr>
    <td align="center"> <b> Total A </b> </td>
     <td> </td>
    <td align="right"><b> <%=totDeducA %> </b></td>
   </tr>
   </table>
      <br> <br>
   <table width="100%">
   <tr> </tr>
   <tr> 
    <td colspan="2" align="center"> <i> For use in P.A.O/Treasury </i></td>
   </tr>
   
    
   
   <tr>  
    <td colspan="2" align="center">  </td>
   </tr>
   <tr>
    <td> <b>  Pay Rs. ( ____________________________ ) </b> </td>
    </tr>
   
   <tr>
   
   
 
   
    
   
   
   <br>
   <%--
    <td colspan="2"><b>Rs.(<%
       String totalDeducA = ConvertNumbersToWord.convert(totalNetAmtTemp);    
       pageContext.setAttribute("totalDeducA",totalDeducA);
    %>${totalDeducA} only)</b> In Cash</td> --%>
    <td colspan="2"><b>Rs.( ________________________________________________ )</b> In Cash</td>
   </tr>
   <tr>
    <td colspan="2"> <b> Rs..(    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     ) by T.C. as at "A"  </b> <br></td>
  
   </tr>
   <tr>
    <td><b> Total : __________________________</td>
   <!-- <td> <b> Total:) </b> </td>-->
   </tr>
  <!-- <tr> <td> <br/> <br/> <br/> </td> </tr>-->

    <tr>
    <td>  <b> Date :  </b> </td></tr>
    <br><br><br><br><br><br><br><br>
    <!--<tr><td> <b> Accountant/Treasury Officer/Sub-Treasury Officer  
Pay and Accounts Officer.  
 </b> </td>
   </tr>-->
   <tr align="center">   <td colspan="2" align="center"> <b>
   Accountant/Treasury Officer/Sub-Treasury Officer / </b> </td>
   </tr>
   <tr align="center"> <td colspan="2" align="center" style="padding-right:20px"> <b>
    Pay and Accounts Officer. </b> </td>
   </tr>
   
</table>
</td>

<td  width="25%" valign="top"> 

<table  border="1" width="100%" style="border-collapse: collapse; border: dashed;">
 <tr>
 <th width="45%" align="center"> Deduction 'B' </th>
 <th  width="28%" align="center"> Edp Code </th>
 <th width="25%" align="center"> Amount </th>
 </tr>
 <tr>
 <c:forEach var="billEdpVO" items="${rcptObjHds}">
 <c:set var="edpCatB" value="${billEdpVO.edpCategory}"/>
 <%
    String edpCatB = (String)pageContext.getAttribute("edpCatB");
 pageContext.setAttribute("edpCatB",edpCatB);
 %>
   <c:if test="${edpCatB == 'B'}">
   <tr>      
   <td>
    &nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;    
   </td>	
      <td>
    &nbsp;&nbsp;<c:out value="${billEdpVO.edpCode} [-]"/>&nbsp;
   </td>	
   <td align="right">
	&nbsp;<c:out value="${billEdpVO.edpAmt}"/> 
	 <c:set var="totalDeducB" value="${billEdpVO.edpAmt}" > </c:set> 
        <%
        totDeducB = totDeducB +  new Long(pageContext.getAttribute("totalDeducB").toString());
         %>
	</td>   
   </tr>
   </c:if>
   </c:forEach>
   <tr>
   	<td>&nbsp; </td>
	<td>&nbsp; </td>
    <td> &nbsp;</td>
   </tr>      
      <tr>
   	<td>&nbsp; </td>
	<td>&nbsp; </td>
    <td> &nbsp;</td>
   </tr>      
      <tr>
   	<td>&nbsp; </td>
	<td>&nbsp; </td>
    <td> &nbsp;</td>
   </tr>        
      <tr>
   	<td>&nbsp; </td>
	<td>&nbsp; </td>
    <td> &nbsp;</td>
   </tr>      
   <tr>

   	<td>&nbsp; </td>
	<td>&nbsp; </td>
    <td> &nbsp;</td>
   </tr>      

  
   <tr>
   	<td>&nbsp; </td>
	<td>&nbsp; </td>
    <td> &nbsp;</td>
   </tr>      

     
   <!--<tr> 
    <td align="center"> <b> Total 'B': </b> </td>
    <td> </td>
    <td align="right"> <b> <%=totDeducB %>  </b> </td>
   </tr>
   -->
   <tr><!--
    <td> <b> Total Deduction (A+B) </b> </td>
    -->
    
     <td> <b> Total Deductions:  </b> </td><td> </td>
    <td align="right"> <b>  <%= totDeducA + totDeducB %> </b> </td>
   </tr>

   <tr> 
   <td> <b>  Net Total:</b> </td>
   <%
     totalNetAmt = (totalExp - totalRec) - (totDeducA + totDeducB);
   %>
   <td> </td>
   <td align="right"> <b>  <% if(totalNetAmt<=0) out.println("NIL"); else out.println(totalNetAmt); %> </b> </td>
   </tr>
   <tr>
    <td colspan="3"> <b> i.e (Rupees 
    <%
       String netPayableStr = "";
    if(totalNetAmt<=0) netPayableStr = "NIL";  else netPayableStr = ConvertNumbersToWord.convert(totalNetAmt); 
           
       pageContext.setAttribute("netAmt",netPayableStr);
    %>
    ${netAmt} only )</b>
    </td>
   </table>

   <br> <br> <br>   <br>
   <table width="100%" border='0' align="left">
   <tr>
    <td> </td>
   </tr>
   <tr> </tr>
   <tr> </tr>
   <tr> </tr>
     <tr>
<%--    <td align="right" colspan="2" style="padding-left:210px"> <b> Signature </b> </td> --%>
    <td align="left" colspan="2" style="padding-left:105px"> <b> ${desigName} </b> </td> 
   </tr>
   <tr>
<%--    <td align="right" colspan="2" style="padding-left:50px"> <b> Drawing Officer,</b> </td> --%>
    <!-- modified by ravysh -->
    <!-- <td align="left" colspan="2" style="padding-left:105px"> <b> Gene.Admin.Deptt.,</b> </td>-->
   <td align="left" colspan="2" style="padding-left:105px"> <b> ${deptName},</b> </td>
   </tr>
  <!--   <tr>
    <td align="right" colspan="2" style="padding-left:50px"> <b> Cardex Code No.  </b> </td>
   <td align="left" colspan="2" style="padding-left:105px"> <b> ${cityName}  </b> </td> 
   </tr>
  --> 
   <tr>
    <!-- <td align="left" colspan="2" style="padding-left:105px"> <b> P.G. Code : 03  </b> </td>-->
    <td align="left" colspan="2" style="padding-left:105px"> <b> ${cardexCode}  </b> </td>
    <!-- modification ends -->
   </tr>
 
   <tr> <br> </tr>
   <tr>
   </tr>
   <tr align="left">
    <td colspan="2" align="left" style="padding-left:30px"> <br><b> Space for Specimen Signature Verification endorsement By T.O./P.A.O. </b> </td>
   </tr>
  
   <tr> </tr>
   <tr> </tr>
   <tr> </tr>
   
   <tr>
    <td colspan="2" style="padding-left:30px"> <br><b> D.A/H.A/A.T.O in charge of Cardex </b> </td>
   </tr> 
   <tr>
    <td colspan="2"> <hr> </td>
   </tr>
   <tr>
    <td  colspan="2"><b>Paid on Dt. </b></td>
   </tr>
   <tr>
    <td  colspan="2"> <hr> </td>
   </tr>
   <tr>
   <td> <b> Advice No.</b> </td>
   <td> <b> Dt.</b> </td>
   </tr>
   <tr>
    <td colspan="2"> <hr> </td>
   </tr>
   <tr>
   <td> <b> Cheque No.</b> </td>
   <td></td>
   </tr>
   <tr>
    <td colspan="2"> <hr> </td>
   </tr>
   <tr> <td> <br/> </td> </tr>
   <tr> <td> <br/> </td> </tr>
   <tr>
    <td colspan="2"></td>
   </tr>
   <tr></tr>
   <tr></tr>
   <tr></tr>
   <tr></tr>
  <!--<tr>   <td colspan="2" align="right"> <b>
   Accountant/Treasury Officer/Sub-Treasury Officer </b> </td>
   </tr>
   <tr> <td colspan="2" align="right" style="padding-right:20px"> <b>
    Pay and Accounts Officer. </b> </td>
   </tr>-->
     
</table>
</td>
</tr>
</table>

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
</html>
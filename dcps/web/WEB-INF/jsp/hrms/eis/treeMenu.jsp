<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="commonLables" scope="request"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="empId" value="${resultValue.empId}" > </c:set>
<c:set var="hrEisEmpMst" value="${resultValue.hrEisEmpMst}" > </c:set>

<%long empId= Long.parseLong(request.getParameter("empId").toString()); 
pageContext.setAttribute("empId",empId);

%>

<style type="text/css">
A:link {text-decoration:ul; color:blue;}
A:visited {text-decoration:ul; color:blue;}
A:active{text-decoration:ul; color: blue;}
A:hover {font-size:34; font-weight:bold; color: red;}

</style> 
 

<html>


<body class="tabFrame1" >
<ul  >
<li ><a href="./hrms.htm?actionFlag=getEmpData&Employee_ID_EmpInfoSearch=${empId}&empAllRec=Y" target="right" id="empDtlsLink" style=""> Emp Details </a></li>
<li> <a href="./hrms.htm?actionFlag=getOtherData&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true" target="right"> Basic Details </a> </li>
<li> <a href="./hrms.htm?actionFlag=getLoanValue&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" target="right"> Loan </a> </li>
<li> <a href="./hrms.htm?actionFlag=getEmpLeaveData&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" target="right"> Leave </a></li>
<li> <a href="./hrms.htm?actionFlag=getNonGovDeductionMaster&Employee_ID_NonGovEmpSearch=${empId}&empAllRec=Y" target="right"> Non Govern Deduc </a> </li>
<!--  <li> <a href="./hrms.htm?actionFlag=getMiscData&Employee_ID_miscSearch=${empId}&empAllRec=Y" target="right"> Misc Recovery </a> </li>-->
<li> <a href="./hrms.htm?actionFlag=getVPFView&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" target="right"> Voluntary PF </a> </li>
</ul>
</body>
</html>
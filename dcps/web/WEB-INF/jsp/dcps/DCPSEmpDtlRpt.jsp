<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REPORT</title>
</head>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
<c:set var="EmpVO" value="${resValue.lObjEmpData}"> </c:set>
<c:set var="NomineeList" value="${resValue.NomineesList}"> </c:set>
<c:set var="totalNominees" value="${resValue.totalNominees}"> </c:set>
<c:set var="lArrayAgeNominees" value="${resValue.lArrayAgeNominees}"> </c:set>
<%SimpleDateFormat simpleDateFormatObj = new SimpleDateFormat("dd/MM/yyyy"); %>
<body>

<form>
	<center>
		<font size=4> <b> <u> OFFICE OF THE SRKA,FORM-1 </u> </b> </font> <br>
				<i>(As referred to in para no.8 Government Resolution, Finance Department, No.CPS 1007/18/SER-4,dated 7 July,2007)</i> <br>
		<font size=4> <b> EMPLOYEE APPOINTED ON OR AFTER 01/11/2005 </b> </font> <br>
	</center>
	
	<table align="center" width="80%" border="0"> 
	<tr>
		<th>	Sub / Treasury Code:			</th>
		<th>	DDO Code: 					</th>
		<th>	Dept. Code:					</th>
	</tr>
	</table>
	
	<br/>
	
	<table align="center"  width="80%" cellpadding="8" cellspacing="4"  >
		<tr>
			<td width="30%">1. Name of the Employee</td>
			<td width="30%">:  <c:out value="${EmpVO.dcpsEmpFname} ${EmpVO.dcpsEmpMname} ${EmpVO.dcpsEmpLname}"></c:out></td>
			<td rowspan=4 width="40%" style="width: 100px; height: 110px; border: 2px solid black;text-align: center">
			Photo Attested by DDO
			 <td>
		</tr>
	<tr>
			<td>2. Gender</td>
			<td>:  <c:out value="${EmpVO.dcpsEmpGender}"></c:out></td>
	</tr>
	<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EmpVO.dcpsEmpDob}" var="birthDate"/>
	<tr>
			<td>3. Date of Birth(DD/MM/YYYY)</td>
			<td>:  <c:out value="${birthDate}"> </c:out></td>
			
	</tr>
	<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EmpVO.dcpsEmpDoj}" var="joiningDate"/>
	<tr>
			<td>4. Date of Joining Govt. Service</td>
			<td>:  <c:out value="${joiningDate}"></c:out>	</td>		
	</tr>
	
		<tr>
			<td width="30%">5. Name and Full Address of Office:</td>
			<td >:  <c:out value="${EmpVO.dcpsEmpCurrOff}"></c:out>		</td>
			<td></td>
			<td></td>
	</tr>
	<tr>
			<td width="30%">6. Post Cadre	</td>
			<td>: <c:out value="${EmpVO.dcpsEmpCadre}"></c:out>	</td>
			<td width="10%">Group 			</td>
			<td width="10%">: <c:out value="${EmpVO.dcpsEmpGroup}"></c:out> </td>
	</tr>
	<tr>
	
			<td width="30%">7. Designation & Payscale	</td>
			<td >:  <c:out value="${EmpVO.dcpsEmpDesignation}"></c:out>		</td>
			<td width="10%"> <c:out value="${EmpVO.dcpsEmpPayScale}"></c:out>			</td>
			<td width="10%">
	</tr>
	<tr>
			<td width="30%">8. Residential Address with Phone No.</td>
			<td >:  <c:out value ="${EmpVO.dcpsEmpAddress1}"></c:out><br/>&nbsp;&nbsp;<c:out value="${EmpVO.dcpsEmpAddress2}"></c:out>	</td>
			<td width="10%">Phone 							    				</td>
			<td width="10%"> :<c:out value="${EmpVO.dcpsEmpCntctNo}"></c:out>		</td>
	</tr>
	<tr>
		<td colspan = "4">
			9.	a)Whether previously in working Government office or any other Organization/Institution to which new D.C.P.S is applicable.<br>
&nbsp;&nbsp;&nbsp;   b)If so,The Pension Account Number allotted earlier.<br><br>
9. Details of Nominee(s) (For accumlation under the Pension Account):a
		</td>
	</tr>
	<tr>
		<td colspan = "4">
	





<table width="80%" align="center" border="1">
	<tr>
			<th>Sr. No.</th>
			<th>Name and full Address of Nominee(s)</th>
			<th>Age</th>
			<th>Date of Birth</th>
			<th>Percentage of share Payble</th>
			<th>Relationship with Government servent</th>
	</tr>
	
	
	<c:set var="counter" value="1"></c:set>
	<% int counter = 0 ;%>
	
	<c:forEach var="nominee" items="${resValue.NomineesList}" varStatus="Counter1" >
	<c:forEach var="age" items="${resValue.lArrayAgeNominees}" varStatus="Counter2">
	
	<c:if test="${Counter1.index == Counter2.index}">
	<tr>
			<td><%= counter+1 %></td>
			
			<td>${nominee.dcpsNmnFname}  ${nominee.dcpsNmnMname}  ${nominee.dcpsNmnLname}<br/> ${nominee.dcpsNmnAddress1}</td>
		 	<td>  <c:out value="${age}"></c:out>	</td>  
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${nominee.dcpsNmnDob}" var="nomineeBirthDate"/>
			<td><c:out value="${nomineeBirthDate}"></c:out>       </td>
			<td>${nominee.dcpsNmnShare}		</td>
			<td>${nominee.dcpsNmnRlt}		</td>
			
	</tr>
	
	<%counter++; %>
	</c:if>
	</c:forEach>
	</c:forEach>
</table>
</td>
</tr>



	<tr>
		<td colspan="4">
			10. I Shri/Smt. <c:out value="${EmpVO.dcpsEmpFname} ${EmpVO.dcpsEmpMname} ${EmpVO.dcpsEmpLname}"></c:out>,am aware that till the Central record Keeping Agency is appointed by central Government, any action /decision taken by the State Record Keeping Agency in consulation with
    government, will be binding on me. I also understand that after appointment of Central  Record Keeping Agency ,the total amount standing to my credit at that time will be transferred to the said Agency.
   
		</td>
	</tr>

	<tr>
			<td colspan="4">Place-</td>
	</tr>
	
	<tr>
			<td colspan="4">Date-</td>
	</tr>
	<tr>
			<td colspan="4" align="right">Signature  of the Employee</td>
	</tr>
	<tr>
			<td colspan="4" align = "center" style="text-decoration: underline;font-weight: bold" >To be Furnished by DDO </td>
	</tr>
	
	<tr>
			<td colspan = "4">
			 Certified that shri/smt./Kum <c:out value="${EmpVO.dcpsEmpFname} ${EmpVO.dcpsEmpMname} ${EmpVO.dcpsEmpLname}"></c:out> has been appointed in Additional District Court. The particulars given above are correct. I have also ascertained that he/she also has not worked in government office or Zilla parisad or aided secondary educational institution or non agriculture university/college affiliated threto or agriculture university or in any organization or institution to which  the New Defined Contribution Pension Scheme is applicable and that he/she has not been alloted the Pension Account Number previously.
			
			
			</td>
	</tr>
</table>

											                                                                         



</form>
</body>
</html>
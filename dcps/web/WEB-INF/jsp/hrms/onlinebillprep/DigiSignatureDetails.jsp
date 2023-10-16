<%@ include file="../../core/include.jsp" %>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<script>
	function closeBill()
	{
		window.close();
	}
</script>

<c:set var ="DigiVerificationResList" value="${resValue.DigiVerificationResList}"></c:set>
<c:set var="DigiTampered" value="${resValue.Tampered}"> </c:set>

<center>
<font size="3" face="Times" class="HLabel"> <u> Digital Signature Verification Result For Bill Control No : <c:out  value="${resValue.BillCntrNo}" /> </u> </font>
<br>
<br>
<c:choose>
	<c:when test="${DigiTampered == 'YES'}">
		<table>
			<tr>
				<td class="HLabel">
					<img src="images/tampered.gif">
			  	   <font color="red" size="3"> Data has been Tampered </font>
			 	 </td>
			</tr>
		</table>	      
		 <br>
		 	<font color="red" size="3" >
				Following Fields are Tampered :
			</font>
		 <br>
	 
		 <table>
		 	 <c:forEach var="lList" items="${DigiVerificationResList}">
				<tr>
					<td>
						<font color="red" size="2">
							 <c:out value="${lList}"/>
						</font>
				    </td> 
				</tr>        	
			 </c:forEach>
	 	</table>    
	</c:when>
	<c:otherwise>
		<table>
			<tr>  
		  		<td width=30% align=right>
		  			<img src="images/tick.jpg">
		 		 </td>
	      
	      		<td align=left class="HLabel"> 
	      			<font color="Green" size="3">  Data has not been Tampered  </font>
	     		 </td>
	     
	     	</tr>
		</table>    
	</c:otherwise>
</c:choose>
<br>
<br>
<hdiits:button type="button" name="btnClose" value=" Close " onclick="closeBill()"/>

</center>


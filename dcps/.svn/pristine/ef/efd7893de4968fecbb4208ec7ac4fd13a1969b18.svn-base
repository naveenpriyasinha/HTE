
<%@ include file="../../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>
<c:set var="desigName" value="${resValue.desigName}"/>
<c:set var="deptName" value="${resValue.deptName}"/>
<c:set var="cardexCode" value="${resValue.cardexCode}"/>
<c:set var="cityName" value="${resValue.cityName}"/>

<head>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="script/common/tabcontent.js">
</script>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript" 
	src="script/hod/ps/common.js">
</script>
<script type="text/javascript" 
	src="script/common/person.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/onlinebillprep/Common.js"/>">
</script>

<script type="text/javascript">

function Open_PrintCertificate()
		{
		
			var height = screen.height - 150;
	    	var width = screen.width - 50;  
	    	
            var URL = "ifms.htm?actionFlag=printCertificate&billNo=${resValue.TrnBillRegister.billNo}";

			window.open(URL,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,height=600,width=800"); 			
		}
		
</script>
<hdiits:form name="orderHeadMaster" validate="true" method="POST" >
		

<table width="100%" style="font-size:18px" border="0">
	<tr>
		<td width="100%">  		
			<table width="40%" style="font-size:18px" border="0">
				<tr>
					<td colspan="2" align="center"><font size="6px"><b>CERTIFICATE</b></font></td>
				</tr>
				
				<tr>		
					<td colspan="2" align="justify"><br>1. Received Contents Rs.<b><u>
					<c:choose >
						<c:when test="${resValue.TrnBillRegister.billNetAmount le 0}">
							NIL
						</c:when>
						<c:otherwise>
									${resValue.TrnBillRegister.billNetAmount}.00
						</c:otherwise>
					</c:choose>
				</u></b>
						<br><b>Rupees  :<u><script>if(${resValue.TrnBillRegister.billNetAmount}<=0)
						document.write("NIL");
						else
						document.write(getAmountInWords(${resValue.TrnBillRegister.billNetAmount}))</script></u>   Only.</b></td>
				</tr>
				<tr>		
					<td colspan="2" align="justify"><br><p>2. Certified that pay and Allowances drawn in this bill are due and admissible as per rules and authority in force.
					</p></td>	
				</tr>
				
				
				<tr>		
					<td colspan="2" align="justify"><p><br>3. Certified that I have satisfied myself that all emoluments in bill drawn 1/2/3 months previous to this date except	those which have been short drwan in this bill or kept in my personal custody have been disbursed to the proper persons and acquittances taken and filed in my office with recepit stamp duly cancelled for every payment in  excess of Rs.5000-00.
					</p></td>	
				</tr>
				<tr>		
					<td colspan="2" align="justify"><p><br>4. Certified that all appoinments and promotions grant of leave(Departure on and return from) and the period of suspension		and deputation and other events which are required to be recorded have been recorded in the Service Book and leave			account.
					</p></td>	
				</tr>
				<tr>		
					<td colspan="2" align="justify"><p><br>5. Certified that persons who have been newly appointed possess the required qualifications and are within the prescribed		age limit and medical certificate obtained in respect of all persons who have completed six months service.
					</p></td>	
				</tr>
				<tr>		
					<td colspan="2" align="justify"><br>
								<!--<br>
						 6.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Certified that the necessary entries have been made in the<br>
						&nbsp;&nbsp;&nbsp;office copy of the original bill.<br>-->
						<!--<br>
						7.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Certified that the claims has not been preffered for the <br>
						&nbsp;&nbsp;&nbsp;said period previously and amount shown in the bill has been<br>
						&nbsp;&nbsp;&nbsp;correctly worked out.<br>-->
						<!--<br>
						8.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Certified that the washing allowance calimed in respect of<br>
						&nbsp;&nbsp;&nbsp;Class-IV employees who are regularly wearing uniform.<br>-->
						<!--<br>
						9.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Certified that  the Transportation Allowance calimed in the<br>
						&nbsp;&nbsp;&nbsp;bill in respect of incumbents who are not residing within a<br>
						&nbsp;&nbsp;&nbsp;distance of one kilometer from the place of work or within a<br>
						&nbsp;&nbsp;&nbsp;campus housing the places of work and residence.<br>-->
						<!--<br>
						10.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Certified that the portion of D.A creditable to Provident<br>
						&nbsp;&nbsp;&nbsp;Fund is included in Provident Fund  deduction.<br>-->
					</td>
				</tr>
				<tr>
					<td  width="50%" valign="top" rowspan="2">
					Station	:GANDHINAGAR</td>	
					<td  width="50%" style="padding-left:35px"><b><br><br>
					<br> ${desigName}</b> </td>
				</tr>
				<tr>
				 <!-- modified by ravysh -->
					<!-- <td  style="padding-left:35px"> <b> Gene.Admin.Deptt., </b> </td> -->
					<td  style="padding-left:35px"> <b> ${deptName}, </b> </td>
				</tr>
				<!-- <tr>
					<td  style="padding-left:35px"> <b> ${cityName},</b> </td>
				</tr> -->
   				<tr>
					<td><fmt:formatDate value="${resValue.TrnBillRegister.billDate}" var="date1" pattern="dd-MM-yyyy"/>
						Dated	&nbsp;&nbsp;&nbsp;&nbsp;:${date1}</td>
					<!-- <td  style="padding-left:35px"> <b> P.G. Code : 03  </b> </td> -->
					<td  style="padding-left:35px"> <b> ${cardexCode} </b> </td> 
				</tr>
				<tr>
					<td>
						<!--<fmt:formatDate value="${resValue.TrnBillRegister.billDate}" var="date1" pattern="dd-MM-yyyy"/>

						Dated	&nbsp;&nbsp;&nbsp;&nbsp;:${date1}
						--></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" border=0 >
							<tr>
								<td width=50%></td><td width=50%></td>
							</tr>
							<tr>
								<td colspan=2>Please pay to _________________________________ who has signed before me.</td>
							</tr>
							<tr>
								<td  rowspan=4></td><td ><br><br><br>
					<br><b> ${desigName} </b> </td>
							</tr>
						   <tr>
								<!-- <td  > <b> Gene.Admin.Deptt.,</b> </td> -->
								<td> <b> ${deptName},</b> </td>
						   </tr>
						   <!--  <tr>
								<td  > <b> ${cityName}</b> </td>
							</tr>-->
							<tr>
								<!-- <td   > <b> P.G. Code : 03 </b> </td> -->
								<td> <b> ${cardexCode} </b> </td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


</hdiits:form>
<script>
window.print();
</script>
</html>
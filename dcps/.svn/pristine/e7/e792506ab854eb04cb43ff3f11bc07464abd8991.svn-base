
<%
	try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="http://cdn.webrupee.com/font">
</head>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="netInWrds" value="${resultValue.netPayInWords}">
</c:set>
<c:set var="grossInWrds" value="${resultValue.grossPayInWords}">
</c:set>
<c:set var="month" value="${resultValue.month}">
</c:set>
<c:set var="year" value="${resultValue.year}">
</c:set>
<c:set var="deptName" value="${resultValue.deptName}">
</c:set>
<c:set var="majorhead " value="${resultValue.majorhead }">
</c:set>
<c:set var="minorHead " value="${resultValue.minorHead }">
</c:set>
<c:set var="subHead " value="${resultValue.subHead }">
</c:set>
<c:set var="schemeName " value="${resultValue.schemeName }">
</c:set>
<c:set var="schemeCode " value="${resultValue.schemeCode }">
</c:set>
<c:set var="demandCode " value="${resultValue.demandCode }">
</c:set>
<c:set var="net " value="${resultValue.net }">
</c:set>
<c:set var="gross " value="${resultValue.gross }">
</c:set>
<c:set var="pt " value="${resultValue.pt }">
</c:set>
<c:set var="pf" value="${resultValue.pf">
</c:set>
<!-- Added By Shivram lekhchand 28092020  and comment by subschme code -->
 <c:set var="revenueStamp" value="${resultValue.revenueStamp}" > </c:set> 
<c:set var="subSchemeCodeME" value="${resultValue.subSchemeCodeME}">
</c:set>
<c:set var="TresaryName" value="${resultValue.TresaryName}">
</c:set>
<c:set var="TresaryCode" value="${resultValue.TresaryCode}">
</c:set>
<c:set var="DDOofficeName" value="${resultValue.DDOName}">
</c:set>
<c:set var="DDOCode" value="${resultValue.DDOCode}">
</c:set> 
<c:set var="DDOPostName" value="${resultValue.DDOPostName}">
</c:set> 
<!-- Ended By Shivram lekhchand  for sub scheme code28092020 -->
<%java.util.Date date = new java.util.Date();
pageContext.setAttribute("date", date);%>
<c:set var="date" value="${date}" />
<%
	Map allowMap = (Map) pageContext.getAttribute("allowMap");
%>
<style type="text/css">
.Label5 {
	font-family: Arial, Helvetica, sans-serif;
	text-decoration: none;
	font-size: 12px;
	font-weight: normal;
	color: Black;
	border-bottom: 1px solid #000000;
	border-right: 1px solid #000000;
	border-top: 0px solid #000000;
	border-left: 0px solid #000000;
}

.frmlrbBorder {
	border-bottom: 1px solid #000000;
	border-right: 1px solid #000000;
	border-top: 0px solid #000000;
	border-left: 1px solid #000000;
}

.frmlftBorder {
	border-left: 1px solid #000000;
}

.TopBorder {
	border-top: 1px solid #000000;
	border-left: 1px solid #000000;
}

border-bottom {
	border-bottom: 1px solid #000000;
}

.RightBorder {
	border-right: 1px solid #000000;
}

.TopBottomBorder {
	border-top: 1px solid #000000;
	border-bottom: 1px solid #000000;
}

#leftstart {
	width: 10%;
	align: left;
}

#subsisides {
	width: 40%;
}
</style>
<script type="text/javascript">
	function GoToPage() {
		document.ViewOuterFirst.action = "./ifms.htm?actionFlag=ViewReportingJsp";
		document.ViewOuterFirst.submit();
	}
	function printReport() 
	{

		document.getElementById('Print').style.visibility = 'hidden'; // hide
		document.getElementById('Back').style.visibility = 'hidden'; // hide   
		document.getElementById('Save').style.visibility = 'hidden'; // hide   
		window.print();
		document.getElementById('Print').style.visibility = 'visible'; // show 
		document.getElementById('Back').style.visibility = 'visible'; // show 
		document.getElementById('Save').style.visibility = 'visible'; // show 

		
	}
		function doSaveAs() 
	{
		document.getElementById('Print').style.visibility = 'hidden'; // hide
		document.getElementById('Back').style.visibility = 'hidden'; // hide   
		document.getElementById('Save').style.visibility = 'hidden'; // hide

		
		document.execCommand("SaveAs");
		document.getElementById('Print').style.visibility = 'visible'; // show 
		document.getElementById('Back').style.visibility = 'visible'; // show 
		document.getElementById('Save').style.visibility = 'visible'; // show 
	}
</script>



<table cellpadding="2" cellspacing="0" border="0" frame="box"
	align="CENTER" width="100%">
<!-- added by lekhchand MTR A report  -->
	<tr>
		<td align="center">
			<p>
			<h1>GOVERNMENT OF MAHARASHTRA</h1> <BR> M.T.R 44 -A <br>
			(Sec Rule 391)<br>
			(à¤µà¥‡à¤¤à¤¨ à¤¦à¥‡à¤¯à¤•à¤¾à¤¸à¤¾à¤ à¥€ à¤µà¤¾à¤ªà¤°à¤¾à¤µà¤¯à¤¾à¤šà¤¾ à¤¨à¤®à¥à¤¨à¤¾)<br> (Form for Salary Payment)<br>
			à¤¬à¤¿à¤¨à¤¶à¤°à¥à¤¤(Unconditional)<br> à¤‰à¤ªà¤¯à¥‹à¤—à¤¿à¤¤à¤¾ à¤ªà¥à¤°à¤®à¤¾à¤£à¤ªà¤¤à¥à¤° à¤†à¤µà¤¶à¥à¤¯à¤• à¤¨à¤¾à¤¹à¥€.<br>
			
		<h1 align="left" style="color:black;"> à¤¸à¤¹à¤¾à¤¯à¥à¤¯à¤• à¤…à¤¨à¥à¤¦à¤¾à¤¨à¤¾à¤šà¥‡ à¤¦à¥‡à¤¯à¤• (Grant in Aid Bill) <br> </h1>
		<hr>
		<p>		
		<h1  style="float:left; color:black;"> Tresary Code : <c:out value="${TresaryCode}" /></h1> 
		<h1 style="float: right; color:black;">Tresary Name : <c:out value="${TresaryName}" /></h1><br>
		<p>
		<h1  style="float: left; color:black;" >à¤†.à¤¸à¤‚. à¤…à¤§à¤¿à¤•à¤¾à¤°à¥€ à¤¸à¤¾à¤‚à¤•à¥‡à¤¤à¤¾à¤‚à¤•  : <c:out value="${DDOCode}" /></h1>
       <h1 style="float:right; color:black;" >à¤†.à¤¸à¤‚. à¤…à¤§à¤¿à¤•à¤¾à¤°à¥€ à¤ªà¤¦à¤¾à¤¨à¤¾à¤®  : <c:out value="${DDOPostName}" /></h1><br>        
       <h1  style="float: left; color:black;" >à¤•à¥‹à¤·à¤¾à¤—à¤¾à¤° à¤¦à¥‡à¤¯à¤• à¤•à¥à¤°./ à¤“à¤³à¤– à¤šà¤¿à¤¨à¥à¤¹ à¤•  : <c:out value="[ --   ]" /></h1>
       <br><hr>
       <p>
     <h1 style="color:black;">  à¤²à¥‡à¤–à¤¾à¤‚à¤•à¤¨ à¤¤à¤ªà¤¶à¥€à¤² <br> à¤ªà¥à¤°à¤¤à¤¿à¤¬à¤§à¥à¤¦/à¤µà¥à¤¯à¤¯ [Committed/Expenditure], à¤­à¤¾à¤°à¤¿à¤¤/à¤¦à¤¤à¥à¤¤à¤®à¤¤ [Charged/Voted],<br> à¤†à¤•à¤¸à¥à¤®à¤¿à¤•à¤¤à¤¾/à¤à¤•à¤¤à¥à¤°à¤¿à¤¤ à¤¨à¤¿à¤§à¥€ Contingency Consolidated fund.  </h1> 
 		</td>
	</tr>
<!-- ended by lekhchand MTR A report  -->
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<table cellpadding="0" cellspacing="0" align="CENTER" width="100%"
				border="1">
				<tr>
					<td width="50%">
						<table align="left">
							<tr>
								<td align="left">

									<table>
										<tr>
											<td><center>
													<u>HEAD OF ACCOUNT</u>
												</center></td>
											<td></td>
										</tr>
										<tr>
											<td>Administrative Department-</td>
											<td><c:out value="${deptName }" /></td>
										</tr>
										<tr>
											<td>Demand No.</td>
											<td>:<c:out value="${demandCode }" />
											</td>
										</tr>
										<tr>
											<td>Sector</td>
											<td>:<c:out value=" " />
											</td>
										</tr>
										<tr>
											<td>Sub-Sector</td>
											<td>:<c:out value="" />
											</td>
										</tr>
										<tr>
											<td>Major Head</td>
											<td>:<c:out value="${majorhead}" />
											</td>
										</tr>

										<tr>
											<td>Minor Head</td>
											<td>:0<c:out value="${minorHead}" />
											</td>
										</tr>
										<tr>
											<td>Sub-Head</td>
											<td>:0<c:out value="${subHead }" />
											</td>
										</tr>
										<tr>
											<td>Detailed-Head</td>
											<td>:</td>
										</tr>
										<tr>
											<td>(Object of Expenditure)</td>
											<td></td>
										</tr>
										<!-- added by lekhchand by sub scheme code  -->
										<tr>
											<td>Sub Scheme Code</td>
											<td>:<c:out value="${subSchemeCodeME}" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td width="50%" valign="top" height="100px">
						<table cellpadding="2" cellspacing="0" border="1" height="100%"
							width="100%">

							<tr>
								<td colspan="2"><center>For Use In Treasury</center></td>
							</tr>
							<tr align="left">
								<td>Voucher NO.</td>
								<td>Date</td>
							</tr>

						</table> <br>
					<br>

						<table cellpadding="2" cellspacing="0" BORDER="1" height="100%"
							width="100%">
							<tr>
								<td colspan="2"><center>Name Of
										Treasury/Sub-Treasury</center></td>
							</tr>
							<tr>
								<td colspan="2" rowspan="3" align="left">Token No. <br></td>
							</tr>
							<tr>
								<td colspan="2" rowspan="3" align="left">Date <br>
								</td>
							</tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
						</table>
					</td>
				</tr>
				<tr>

					<td width="50%" valign="top">

						<table cellpadding="2" cellspacing="0" border="0" height="100%"
							width="100%">
							<tr>
								<td><br>
									<center>Detailed Head</center></td>
							</tr>
						</table>
					</td>
					<td width="50%" valign="top"><span style="align: right">
							<center>Amount</center>
					</span>
						<table cellpadding="2" cellspacing="0" border="1" height="100%"
							width="100%">
							<tr>
								<td width="50%">
									<center>Plan</center>
								</td>
								<td width="50%">
									<center>Non-Plan</center>
								</td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td width="50%" valign="top">
						<table cellpadding="2" cellspacing="0" border="1" width="100%">
							<tr>

								<td width="20%"><span id="leftstart"> <c:out
											value="${grossInWrds}" />
								</span></td>
								<td width="80%"><span id="subsisides">
										<center>
											Grant-in-aid/Contribution/Subsidies <br> (Full Details
											of the Claim)<br> <b> Salary For The
												Month&nbsp;&nbsp;<c:out value="${month}" />&nbsp;<c:out
													value="${year}" />
											</b> <b><br> To SECONDARY SCHOOL (Grant-in-Aid Bill)
										</center>
										</b>

								</span></td>
							</tr>
						</table>
					</td>
					<td width="50%" valign="top">
						<table cellpadding="2" cellspacing="0" border="1" height="100%"
							width="100%">
							<tr>
								<td width="50%">
									<center>Rs</center>
								</td>
								<td width="50%">
									<center>Rs</center>
								</td>
							</tr>
							<tr>
								<td width="50%"><b><center>Gross</center></b></td>
								<td width="50%">
									<center>
										<c:out value="${gross}" />
									</center>
								</td>
							</tr>
							<tr>
								<td width="50%"><b><center>Prov. Fund</center></b></td>
								<td width="50%">
									<center>
										<c:out value="${pf]}" />
									</center>
								</td>
							</tr>
							<tr>
								<td width="50%"><b><center>P T</center></b></td>
								<td width="50%">
									<center>
										<c:out value="${pt}" />
									</center>
								</td>
							</tr>
						</table>

					</td>
				</tr>
				<tr>
					<td width="50%" valign="top">
						<table cellpadding="2" cellspacing="0" border="0" height="100%"
							width="100%">
							<tr>
								<td align="right">Total Claim...</td>
							</tr>
						</table>
					</td>

					<td width="50%" valign="top">

						<table cellpadding="2" cellspacing="0" border="1" height="100%"
							width="100%">
							<tr>
								<td width="50%"><b><center>&nbsp;</center></b></td>
								<td width="50%">

									<center>
										<c:out value="${net}"/>
									</center>
								</td>
							</tr>


						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="100%" align="left">
						<p> 
							Amount&nbsp;&nbsp;(in Words) Rupees &nbsp;&nbsp;&nbsp;<b><c:out
									value="${net+dcps}" />/-&nbsp;&nbsp; <c:out value="${netInWrds}" />
								Only</b>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="100%" align="left">
						<p> 
							Received The Sum Of Rs.&nbsp;&nbsp;&nbsp;<b> <c:out
									value="${gross}" />/-&nbsp;&nbsp; <c:out
									value="${grossInWrds}" /> Only
							</b> <br>
							<br> being the amount sactioned for the period<b> <c:out
									value="${month}" />&nbsp;<c:out value="${year}" />
							</b>under<b> <c:out value="${deptName}" /></b> <br> No. Pay
							Unit/Salary &nbsp;&nbsp;&nbsp;dated<b> &nbsp;<fmt:formatDate
									pattern="yyyy-MM-dd" value="${date}" />
							</b>&nbsp;&nbsp;&nbsp;
							<c:out value="${currentDate}" />
							&nbsp;&nbsp;&nbsp;&nbsp;(Copy enclosed) for disbursememnt to
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="100%" align="left"><b>Certified
							That:- <br> (i)The grantee has executed the requisite bond/
							has bee exempted from executing a bond.In Consultation with
							Finance Department/Law and Judiciary Department. <br> (ii)I
							have no reason to belebve that the grantee institution is
							involved in corrupt practices.<br> <b>Place: <br>
								Date:-&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${date}" />
						</b>
					</b></td>
				</tr>


			</table> <hdiits:form name="ViewOuterFirst" validate="true" method="POST"
				action="" encType="multipart/form-data">
				<table align="center">
					<tr>

						<td><input name="Back" type="button" Value="Back"
							onclick=GoToPage();></input></td>
						<td><input name="Print" type="button" value="Print" onClick="printReport();"></input></td>
						<td><input name="Save" value="Save" type="button" id="saveButton" onclick="doSaveAs();"></td>
					</tr>
				</table>
			</hdiits:form>


			</html> <%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
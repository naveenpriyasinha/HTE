
<%
	try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import="java.util.Map"%>
<fmt:setLocale value="${sessionScope.locale}" />
<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="http://cdn.webrupee.com/font">
<style>
.dotedBor {
    height: 35px;
    border-bottom: 1px dotted #000;
       margin: 10px 0 50px 0;
     position: relative;
}
.dotedBor:after {
    content: "";
    width: 90%;
    margin: 0 auto;
    border-bottom: 1px dotted;
    position: absolute;
       top: 50px;
    left: 5%;
}
.text-center{
TEXT-ALIGN:CENTER !IMPORTANT;
}
</style>
</head>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="netInWrds" value="${resultValue.netInWrds}">
</c:set>
<c:set var="grossInWrds" value="${resultValue.grossInWrds}">
</c:set>
<c:set var="month" value="${resultValue.month}">
</c:set>
<c:set var="year" value="${resultValue.year}">
</c:set>
<c:set var="deptName" value="${resultValue.deptName}">
</c:set>
<c:set var="majorhead" value="${resultValue.majorhead}">
</c:set>
<c:set var="minorHead" value="${resultValue.minorHead}">
</c:set>
<c:set var="subHead" value="${resultValue.subHead}">
</c:set>
<c:set var="schemeName" value="${resultValue.schemeName}">
</c:set>
<c:set var="schemeCode" value="${resultValue.schemeCode}">
</c:set>
<c:set var="demandCode" value="${resultValue.demandCode}">
</c:set>
<c:set var="subSchemeCode" value="${resultValue.subSchemeCode}">
</c:set>
<c:set var="subSchemeName" value="${resultValue.subSchemeName}">
</c:set>
<c:set var="net" value="${resultValue.net}">
</c:set>
<c:set var="gross" value="${resultValue.gross}">
</c:set>
<c:set var="sumabc" value="${resultValue.sumabc}">
</c:set>
<c:set var="sumD" value="${resultValue.sumD}">
</c:set>
<c:set var="pt" value="${resultValue.pt}">
</c:set>
<c:set var="pf" value="${resultValue.pf}">
</c:set>
<c:set var="gis" value="${resultValue.gis}">
</c:set>
<c:set var="accPolicy" value="${resultValue.accPolicy}">
</c:set>
<c:set var="revenueStamp" value="${resultValue.revenueStamp}">
</c:set>
<c:set var="it" value="${resultValue.it}">
</c:set>
<c:set var="dcps" value="${resultValue.dcps}">
</c:set>
<c:set var="gpfAbc" value="${resultValue.gpfAbc}">
</c:set>
<c:set var="gpfD" value="${resultValue.gpfD}">
</c:set>
<c:set var="sgGpfD" value="${resultValue.sgGpfD}">
</c:set>
<c:set var="sgGpfAbc" value="${resultValue.sgGpfAbc}">
</c:set>
<c:set var="consolidatedLoanMap"
	value="${resultValue.consolidatedLoanMap}">
</c:set>
<c:set var="allow" value="${resultValue.allowDed}">
</c:set>
<c:set var="lstGpf" value="${resultValue.lstGpf}">
</c:set>
<c:set var="consolidatedGpfMap" value="${resultValue.consolidatedGpfMap}">
</c:set>
<c:set var="provFundNormalMap"
	value="${resultValue.provFundNormalMap}">	
</c:set>
<c:set var="emptyVar" value="-">
</c:set>

<!--<% //added by samadhan %>-->
<c:set var="subMajorhead" value="${resultValue.subMajorhead}">
</c:set>
<c:set var="subMinorhead" value="${resultValue.subMinorhead}">
</c:set>

<!--  Added BY Naveen MTR44-A -->
<c:set var="hrr" value="${resultValue.hrr}">
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
<!--  Ended BY Naveen  -->

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
		document.ViewOuterFirst.action = "./ifms.htm?actionFlag=viewConsolidatedBill";
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

	<!-- <tr>
		<td align="center">
		<p>GOVERNMENT OF MAHARASHTRA <BR>
		M.T.R 44(Sec Rule 391)<br>
		Grant-in-Aid Bill <br>
		CONSOLIDATED FUND</p>
		</td>
	</tr> -->
	<!-- added by Naveen Sinha MTR A report  -->
	<tr>
		<td align="center">
			<p>
			<h1>GOVERNMENT OF MAHARASHTRA</h1> <BR> M.T.R 44 -A <br>
			(Sec Rule 391)<br>
			(वेतन देयकासाठी वापरावयाचा नमुना)<br> (Form for Salary Payment)<br>
			बिनशर्त(Unconditional)<br> उपयोगिता प्रमाणपत्र आवश्यक नाही.<br>
			
		<h1 align="left" style="color:black;"> सहाय्यक अनुदानाचे देयक (Grant in Aid Bill) <br> </h1>
		<hr>
		<p>		
		<h1  style="float:left; color:black;"> Tresary Code : <c:out value="${TresaryCode}" /></h1> 
		<h1 style="float: center; color:black;">Tresary Name : <c:out value="${TresaryName}" /></h1><br>
		<p>
	    <h1 style="text-align:left; color:black;clear:both;"></h1>
		<h1 style="float:right; color:black;" >आ.सं. अधिकारी सांकेतांक  : <c:out value="${DDOCode}" /></h1><br>
       
      <p><h1 style="text-align:left; color:black;clear:both;">कोषागार देयक क्र./ ओळख चिन्ह क  : <c:out value="[     ]  " /></h1>
      <h1 style="float:right; color:black;" >आ.सं. अधिकारी पदानाम  : <c:out value="${DDOPostName}" /></h1>
       <br> <hr>
       <p>
     <h1 style="color:black;">  लेखांकन तपशील <br> प्रतिबध्द/व्यय [Committed/Expenditure], भारित/दत्तमत [Charged/Voted],<br> आकस्मिकता/एकत्रित निधी Contingency Consolidated fund.  </h1> 
 		</td>
	</tr>
<!-- ended by Naveen MTR A report  -->

	<tr>
		<td>&nbsp;</td>
	</tr>
	<!--added by samadhan-->
<!-- <tr align="right">
		<td>
		<table align="right" cellspacing="0">
			<tr>
				<td
					style="border-bottom: solid; border-left: solid; border-top: solid; border-color: black; border-width: 1px">
				<c:choose>
							<c:when test="${(year==2017 && month>3) || year>2017}">
								<h5 align="right">Scheme/Committed</h5>
							</c:when>
							<c:otherwise>
								<h5 align="right">PLAN/NON PLAN</h5>
							</c:otherwise>
						</c:choose>
				</td>
			</tr>
			<tr>
				<td
					style="border-bottom: solid; border-left: solid; border-top: solid; border-color: black; border-width: 1px">
				<h5 align="right">Charged / Voted</h5>
				</td>
			</tr>
		</table>
		</td>
	</tr> -->
	<!--added by samadhan end-->
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
								<td>
								<center><u>HEAD OF ACCOUNT</u></center>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>Administrative Department-</td>
								<td></td>
							</tr>
							<tr>
								<td>Demand No.</td>
								<td>:<c:out value="${demandCode}" /></td>
							</tr>
							<tr>
								<td>Sector</td>
								<td>:<c:out value=" " /></td>
							</tr>
							<tr>
								<td>Sub-Sector</td>
								<td>:<c:out value="" /></td>
							</tr>
							<tr>
								<td>Major Head</td>
								<td>:<c:out value="${majorhead}" /></td>
							</tr>
							<tr>
								<td>Sub Major Head</td>
								<td>:<c:out value="${subMajorhead}" /></td>
							</tr>
							<tr>
								<td>Minor Head</td>
								<td>:0<c:out value="${minorHead}" /></td>
							</tr>
							<tr>
								<td>Sub Minor Head</td>
								<td>:0<c:out value="${subMinorhead}" /></td>
							</tr>
							<tr>
								<td>Sub-Head</td>
								<td>:0<c:out value="${subHead}" /></td>
							</tr>
							<tr>
								<td>Detailed-Head</td>
								<td>:</td>
							</tr>
							<tr>
								<td>(Object of Expenditure)</td>
								<td></td>
							</tr>
							
							<tr>
								<td>Scheme Code</td>
								<td>:<c:out value="${schemeName}(${schemeCode})" /></td>

							</tr>
							<tr>
								<c:if test="${subSchemeCode!='-' && subSchemeCode!='null'}">
									<td>Sub Scheme Code</td>
									<td>:<c:out value="${subSchemeName}(${subSchemeCode})" />
									</td>
								</c:if>
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
						<td colspan="2">
						<center>For Use In Treasury</center>
						</td>
					</tr>
					<tr align="left">
						<td>Voucher NO.</td>
						<td>Date</td>
					</tr>

				</table>
				<br>
				<br>

				<table cellpadding="2" cellspacing="0" BORDER="1" height="100%"
					width="100%">
					<tr>
						<td colspan="2">
						<center>Name Of Treasury/Sub-Treasury</center>
						</td>
					</tr>
					<tr>
						<td colspan="2" rowspan="3" align="left">Token No. <br>
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr></tr>
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
						<center>Detailed Head</center>
						</td>
					</tr>
				</table>
				</td>
				<td width="50%" valign="top"><span style="align: right">
				<center>Amount</center>
				</span> <!--<table cellpadding="2" cellspacing="0" border="1" height="100%"
							width="100%">
							<tr>
							<td width="50%">	
							<center>Plan</center>				
							</td>
								<td width="50%">
							<center>Non-Plan</center>
							</td>
							</tr>
				</table>--></td>
			</tr>
			<tr>
				<td width="50%" valign="top">
				<table cellpadding="2" cellspacing="0" border="1" width="100%">
					<tr>

						<td width="20%"><span id="leftstart"> <c:out
							value="${grossInWrds}" /> </span></td>
						<td width="80%"><span id="subsisides">
						<center>Grant-in-aid/Contribution/Subsidies <br>
						(Full Details of the Claim)<br>
						<b> Salary For The Month &nbsp;&nbsp;<c:out value="${month}" /> &nbsp; 
						<c:out value="${year}" /></b> <b><br>

						(Grant-in-Aid Bill)</center>
						</b> </span></td>
					</tr>
				</table>
				</td>
				<td width="50%" valign="top">
				<table cellpadding="2" cellspacing="0" border="1" height="100%"
					width="100%">
					<tr>


						<td width="25%">
						<center></center>
						</td>

						<td width="25%">
						<center>Head Of Account Code</center>
						</td>

						<td width="25%">
						<center>Rs</center>
						</td>
					</tr>
					<tr>
						<td width="25%"><b>
						<center>Gross</center>
						</b></td>

						<td width="25%">
						<center><c:out value="${emptyVar}" /></center>
						</td>


						<td width="25%">
						<center><c:out value="${gross}" /></center>
						</td>
					</tr>
					<!--
							
								<c:forEach items = "${consolidatedGpfMap}" var = "gpf">
								<tr>
									<td width="25%">	
									<b><center>Prov. Fund</center></b>				
									</td>
									<td width="25%">
									<center><b><c:out value="${gpf.key}" /></b></center>
									</td>
									<td width="25%">
									<center><c:out value="${gpf.value}" /></center>
									</td>
							</tr>	
								
								
								</c:forEach>
							-->

					<tr>
						<td width="25%"><b>
						<center>GOV Prov. Fund(A,B,C)</center>
						</b></td>
						<td width="25%"><b>
						<center>8009501201</center>
						</b></td>

						<td width="25%">

						<center><c:out value="${sgGpfAbc}" /></center>

						</td>
					</tr>


					<tr>

						<td width="25%"><b>
						<center>GOV Prov. Fund(D)</center>
						</b></td>
						<td width="25%"><b>
						<center>8009517301</center>
						</b></td>

						<td width="25%">


						<center><c:out value="${sgGpfD}" /></center>

						</td>

					</tr>




					<%--<tr>

						<td width="25%"><b>
						<center>Prov123. Fund Normal</center>
						</b></td>

						<td width="25%"><b>
						<center>8336501101</center>
						</b></td>


						<td width="25%">

						<center><c:out value="${sumabc}" /></center>

						</td>
					</tr>





					<tr>
						<td width="25%"><b>
						<center>Prov. Fund Normal(D)</center>
						</b></td>
						<td width="25%"><b>
						<center>8336501101</center>
						</b></td>

						<td width="25%">

						<center><c:out value="${sumD}" /></center>

						</td>
					</tr>--%>
					
					<c:forEach items="${provFundNormalMap}" var="provFundNormal">
					
			
						<c:if test="${provFundNormal.value ne 0}">
							<tr>

								<td width="50%">
								<center><b>Prov. Fund Normal</b></center>
								</td>
								<td width="25%">
								<center><b><c:out value="${provFundNormal.key}" /></b></center>
								</td>
								<td width="25%">
								<center><c:out value="${provFundNormal.value}" /></center>
								</td>
							</tr>
						</c:if>
					
					    
					</c:forEach>
					
					<tr>
						<td width="25%"><b>
						<center>Accidental POLICY</center>
						</b></td>

						<td width="25%"><b>
						<center>8121507503</center>
						</b></td>

						<td width="25%">
						<center><c:out value="${accPolicy}" /></center>
						</td>
					  </tr>


						<tr>
							<td width="25%">	
							<center><b>Revenue Stamp</b></center>					
							</td>
							<td width="25%">	
							<center><b>8121507501</b></center>				
							</td>
								<td width="25%">						 
								<center><c:out value="${revenueStamp}" /></center>
							
						</tr>




					<tr>

						<td width="25%"><b>
						<center>P T</center>
						</b></td>

						<td width="25%">
						<center><c:out value="0028001201" /></center>
						</td>

						<td width="25%">
						<center><c:out value="${pt}" /></center>
						</td>
					</tr>

					<tr>
						<td width="25%"><b>
						<center>DCPS</center>
						</b></td>


						<td width="25%">
						
				        <b><center>8342513201/8342519701</center></b>						
				        </td>


						<td width="25%">
						<center><c:out value="${dcps}" /></center>
						</td>
					</tr>

					<tr>
						<td width="25%"><b>
						<center>GIS</center>
						</b></td>

						<td width="25%"><b>
						<center>8011502300</center>
						</b></td>



						<td width="25%">
						<center><c:out value="${gis}" /></center>
						</td>
					</tr>


					<tr>
						<td width="25%"><b>
						<center>HRR</center>
						</b></td>

						<td width="25%"><b>
						<center>216001300</center>
						</b></td>

						<td width="25%">
						<center><c:out value="${allow[0][4]}" /></center>
						</td>
					</tr>

				
					
					<c:set var="counter" value="0"></c:set>
					<c:forEach items="${consolidatedLoanMap}" var="loans">
					
			
						<c:if test="${loans.value[0] ne 0}">
							<tr>

								<td width="50%">
								<center><b><c:out value="${loans.key}" /></b></center>
								</td>
								<td width="25%">
								<center><b><c:out value="${loans.value[1]}" /></b></center>
								</td>
								<td width="25%">
								<center><c:out value="${loans.value[0]}" /></center>
								</td>
							</tr>
						</c:if>
					
					    
					</c:forEach>
		       
		         

				
						
					<!--	<tr>
							<td width="50%">	
							<b><center>I T</center></b>				
							</td>
							<td width="50%">
							<center><c:out value="${it}" /></center>
							</td>
							</tr>
				-->
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
						<td width="25%"><b>
						<center>&nbsp;</center>
						</b></td>
						<td width="25%"><b>
						<center>&nbsp;</center>
						</b></td>

						<td width="25%">

						<center><c:out value="${net}" /></center>
						</td>
					</tr>


				</table>
				</td>
			</tr>
			<tr>
					<td colspan="2" width="100%" align="left">
						<p>
							Amount&nbsp;&nbsp;(in Words) Rupees &nbsp;&nbsp;&nbsp;<b><c:out
									value="${net}" />/-&nbsp;&nbsp; <c:out value="${netInWrds}" />
								Only</b>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="100%" align="left">
						<p>
							Received The Sum Of Rs.&nbsp;&nbsp;&nbsp;<b> <c:out
									value="${net}" />/-&nbsp;&nbsp; <c:out value="${netInWrds}" />
								Only
							</b> <br> <br> being the amount sactioned for the period<b>
								<c:out value="${month}" />&nbsp;<c:out value="${year}" />
							</b>under <b><c:out value="${sanctionBy}" /></b>, &nbsp;&nbsp;<b><c:out
									value="${deptName}" /></b> <br> No. Pay Unit/Salary
							&nbsp;&nbsp;&nbsp;dated<b> &nbsp;<fmt:formatDate
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


			</table>
		</td>
	</tr>
</table>


<table cellpadding="4" cellspacing="0" border="1" align="CENTER"
	width="100%">

	<tr height="75%">
		<td width="25%" align="left" style="vertical-align: center"
			rowspan="2">Allotment for ${finYear}</td>
		<td width="25%">Rs.</td>
		<td width="25%">Ps.</td>
	</tr>
	<tr height="50%">
		<td colspan="2" width="25%">&nbsp;</td>
	</tr>



	<tr height="75%">
		<td align="left" style="vertical-align: center">Expenditure	including this bill -- -- -- --</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr height="75%">
		<td align="left" style="vertical-align: center">Balance available -- -- -- -- --</td>
		<td colspan="2">&nbsp;</td>
	</tr>
<tr> <td colspan="3">अ)प्रमाणित करण्यात येते की, सदर मागणी तयार करताना मु.वि. नि. 1959, म.को. नि. 1968 व त्या अंतर्गत आज अखेरपर्यंत झालेल्या सुधारणा 
                                                             तसेच याविषयी आज अखेरपर्यंत निर्गमित करण्यात आलेले सर्व शासन निर्णय/शासन परिपत्रकामधील लागू असलेल्या सर्व तरतूदींची खात्री करण्यात आलेली असून 
                                                              उपरोक्त सर्व नियमांमधील विहित अटी व शर्तीचे व वित्तीय औचित्याच्या सुत्राचे पालन करुन हे देयक प्रदानार्थ कोषागारास सादर करण्यात येत आहे. </td> </tr>
                                                              
 <tr> <td colspan="3">ब) प्रमाणित करण्यात येते की, सदरची मागणी तयार करताना आदात्यांच्या बँक खाते क्रमांकाचा तपशील योग्य असल्याची खात्री करण्यात आलेली
                                                             असून त्याप्रमाणे प्रदान करण्यास संमती देण्यात येत आहे. </td> </tr>
<tr> <td colspan="3">क) प्रमाणित करण्यात येते की, सदरचे देयक यापूर्वी उपकोषागारे/कोषागारे/अधिदान व लेखा कार्यालयाने पारित करुन प्रदान केले नसल्याबाबत
                                                                       या कार्यालयाच्या देयक नोदवहीवरुन तथा पारगमन नोदवहीवरुन  खात्री करण्यात आलेली आहे.</td> </tr>
<tr> <td colspan="3">ड) प्रमाणित करण्यात येते की, देयकान्वये मंजुर करण्याचे अनुदान हे लोकहितार्थ तथा शासकीय कामकाजास्तव आवश्यक आहे. खर्चास सक्षम
प्राधिकाऱ्यांनी मंजुरी दिलेली असून खर्च मंजुरी अधिकारी अशी मंजुरी देण्यास सक्षम आहेत.</td> </tr>
</table>

<div class="dotedBor"></div>
<table align="CENTER" width="100%" class="iireportt1">

	<tr>
		<td colspan="3" >
			<div class="text-center"><b>For use of Treasury/Sub Treasury/Pay &
				Accounts Office</b></div></td>
	</tr>
	<tr>
		<td colspan="3" align="left">Pay Rs.__________________</td>
	</tr>
	<tr>
		<td colspan="3">(in words) Rupees______________________________________</td>
	</tr>
	<tr height="20%">
		<td >Auditor_____________ </td>
		<td align="center">Supervisor__________ </td>
		<td align="right">STO/ATO/TO/APAO________</td>
		
	</tr>
	
	<tr>
		<td colspan="3"><div class="dotedBor"></div></td>
	</tr>
	<tr height="20%">
	
		<td >Cheque/e-Payment:__________  </td>
		<td align="center">Date_________ </td>
		<td align="right">Cheque/e-Payment advise slip_</td>
		
	</tr>
	<tr height="20%">
	
		<td colspan="2">Payment Advise No.:_ _ _ _ _ _ _ _</td>
		<td align="right">Delivered on Date :_ _ _ _ _ _ _ _</td>
		
	</tr>
	<tr height="20%">
	 
		<td colspan="2">STO/ATO/TO/APAO :_ _ _ _ _ _ _ _</td>
		<td align="right">Delivery Clerk :_ _ _ _ _ _ _ _</td>
		
	</tr>
	<tr>
		<td colspan="3"><div class="dotedBor"></div></td>
	</tr>
	<tr>
		<td colspan="3">
			<div class="text-center"><b>For use of Audit Office</b></div>
		</td>
	</tr>
	
	<tr height="20%">
	 
		<td colspan="2">Admitted for Rs. :_ _ _ _ _ _ _ _ </td>
		<td align="right">Objected for Rs. :_ _ _ _ _ _ _ _ </td>
		
	</tr>
	<tr height="20%">
	 
		<td colspan="3">Reasons for objection :_ _ _ _ _ _ _ _</td>
	</tr>
	<tr height="20%">
	
		<td >Auditor_ _ _ _ _ _ _ _</td>
		<td align="center">Section Officer_ _ _ _ _ _ _ _</td>
		<td align="right">Accounts Officer_ _ _ _ _ _ _ _<br> <br></td>
		
	</tr>
	<tr>
		<td colspan="3"><div class="dotedBor"></div></td>
	</tr>
	
</table>

<hdiits:form name="ViewOuterFirst" validate="true" method="POST"
	action="" encType="multipart/form-data">
	<table align="center">
		<tr>

			<td><input name="Back" type="button" Value="Back"
				onclick="GoToPage();"/></td>
			<td><input name="Print" type="button" value="Print"
				onClick="printReport();"/></td>
			<td>
			<input name="Save" value="Save" type="button" id="saveButton" onclick="doSaveAs();"/>
			</td>
		</tr>
	</table>
</hdiits:form>


</html>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>


<%
	try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import="java.util.Map;"%>
<fmt:setLocale value="${sessionScope.locale}" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="http://cdn.webrupee.com/font">
</head>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="OuterVo" value="${resValue.outerVo}">
</c:set>
<c:set var="gradeCount" value="${OuterVo.gradeCount}">
</c:set>
<c:set var="totalCount" value="${OuterVo.totalCount}">
</c:set>
<c:set var="allowMap" value="${OuterVo.outerMap}"></c:set>

<c:set var="vacantCount" value="${OuterVo.vacantCount}">
</c:set>

<c:set var="netPayInWords" value="${resValue.netPayInWords}"></c:set>
<c:set var="netPayInWordsPlusOne" value="${resValue.netPayInWordsPlusOne}"></c:set>
<c:set var="deducAgInWords" value="${resValue.deducAgInWords}"></c:set>
<c:set var="deducTryInWords" value="${resValue.deducTryInWords}"></c:set>
<c:set var="deducTotalInWords" value="${resValue.deducTotalInWords}"></c:set>

<c:set var="TresuryCode" value="${resValue.TresuryCode}"></c:set>
<c:set var="TresuryName" value="${resValue.TresuryName}"></c:set>

<c:set var="officeDetails" value="${resValue.officeDetails}"></c:set>

<c:set var="VerificationDate" value="${resValue.VerificationDate}"></c:set>
<c:set var="headAccNoList" value="${resValue.HeadAccNoList}"></c:set>
<c:set var="headAccNoListNew" value="${resValue.HeadAccNoListNew}"></c:set>
<c:set var="ddocode" value="${resValue.ddocode}"></c:set>
<c:set var="outerCustomVOList" value="${resValue.outerCustomVOList}"></c:set>
<c:set var="outerCustomVOListSize" value="${resValue.outerCustomVOListSize}"></c:set>

<c:set var="DeptName" value="${resValue.DeptName}"></c:set>
<c:set var="dsgnNameOfDDO" value="${resValue.dsgnNameOfDDO}"></c:set>
<c:set var="row" value="2"></c:set>
<c:set var="subSchemeCode" value="${resValue.subSchemeCode}" ></c:set>


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
</style>
<script type="text/javascript">
	function GoToPage() {
		document.ViewOuterFirst.action = "./hrms.htm?actionFlag=getBillMonthYearData&nextPage=outer";
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
<%
	Map allowMap = (Map) pageContext.getAttribute("allowMap");
%>

<p style="page-break-after: always">

	<c:if test="${outerCustomVOListSize gt 0}">
		<c:forEach items="${outerCustomVOList}" var="name">

			<tr>

				<td><font size="2"> <c:out value="${name.dsgnName}" />
						<td>&nbsp;&nbsp;</td> - &nbsp;sanctioned upto &nbsp;&nbsp;&nbsp;-
						<c:out value="${name.postendDate}" />
						<td>&nbsp;</td>vide GR. No. <c:out value="${name.orderName}" />&nbsp;&nbsp;dated:<c:out
							value="${name.orderDate}" /> </font></td>



			</tr>
			<br>
		</c:forEach>
	</c:if>

</p>

<table cellpadding="0" cellspacing="0" border="0" frame="box"
	align="CENTER" width="100%">

	<tr>
		<td align="center">
			<p>
				MTR 19<br> [Rule 259<1>]
			</p></td>
	</tr>
	<tr>
		<td align="left">
			<p>
				Bill For :
				<c:out value="${resValue.officeDetails}" />
				-A,B,B N Gz,C,D-Both Permanent<br /> <br /> Name of Office :
				<c:out value="${resValue.officeDetails}" />
				Month :
				<c:out value="${OuterVo.month}" />
				Year :
				<c:out value="${OuterVo.year}" />
				Bill Id :
				<c:out value="${OuterVo.billId}" />
			</p></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" border="1" align="CENTER"
				width="100%">
				<tr>
					<td width="30%" valign="top">
						<table cellpadding="5" cellspacing="0" border="0" height="100%"
							width="100%">

							<tr align="left">
								<td>For The Treasury
									<hr /></td>
							</tr>
							<tr>
								<td align="left">
									<p>
										Treasury Code/
										<c:out value="${TresuryCode}" />
										&nbsp;
										<c:out value="${TresuryName}" />
									</p>
									<p>Sub Treasury</p>
									<p>
										Drawing Officer's &nbsp;
										<c:out value="${ddocode} ${resValue.officeDetails}"></c:out>
									</p>
									<hr /></td>
							</tr>
							<tr>
								<td align="left"><br></br> <br></td>
							</tr>
						</table></td>
					<td width="50%" style="border-right: none">
						<table align="left">
							<tr>
								<td align="left">

									<table>
										<tr>
											<td>HEAD OF ACCOUNT</td>
											<td></td>
										</tr>
										<tr>
											<td>Administrative Department-</td>
											<td></td>
										</tr>
										<tr>
											<td>Demand No.</td>
											<td>:<c:out value="${OuterVo.mstScheme.demandCode}" />
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
											<td>:<c:out value="${OuterVo.mstScheme.majorHead}" />
											</td>
										</tr>
										<tr>
											<td>Sub-Major Head</td>
											<td>:0<c:out value="${OuterVo.mstScheme.subMajorHead}" />
											</td>
										</tr>
										<tr>
											<td>Minor Head</td>
											<td>:0<c:out value="${OuterVo.mstScheme.minorHead}" />
											</td>
										</tr>
										<tr>
											<td>Sub-Minor Head</td>
											<td>:0<c:out value="${OuterVo.mstScheme.subMinorHead}" />
											</td>
										</tr>
										<tr>
											<td>Sub-Head</td>
											<td>:0<c:out value="${OuterVo.mstScheme.subHead}" />
											</td>
										</tr>
										<tr>
											<td>Detail-Head</td>
											<td>:36 Grant in Aid (Salary) &nbsp; &nbsp;Scheme
												Code : <c:out value="${OuterVo.mstScheme.schemeCode} " />
											</td>
											
											<c:if test="${subSchemeCode!=null}">
											<td>: &nbsp; &nbsp; Sub Scheme
												Code : <c:out value="${subSchemeCode}" />
											</td></c:if>
										</tr>
										<tr>
											<td>(Object of Expenditure)</td>
											<td></td>
										</tr>
									</table></td>
							</tr>
						</table></td>
					<td width="20%" valign="top" style="border-left: none">
						<table align="right" cellspacing="0">
							<tr>
								<td>
									<h5 align="right">CONSOLIDATED FUND</h5></td>
							</tr>

							<tr>
								<td align="left"
									style="border-bottom: solid; border-left: solid; border-top: solid; border-color: black; border-width: 1px">Voucher
									No:&nbsp;&nbsp;&nbsp;<c:out value="${OuterVo.voucherNo}" />
								</td>
							</tr>
							<tr>
								<td align="left"
									style="border-left: solid; border-bottom: solid; border-color: black; border-width: 1px">Date:&nbsp;&nbsp;&nbsp;</td>
							</tr>
						</table></td>
				</tr>
			</table> <br />
			<table cellpadding="4" cellspacing="0" border="1" align="CENTER"
				width="100%">
				<tr>
					<td width="5%" align="center">1</td>
					<td width="25%">Detailed Head</td>
					<td width="20%">Sub-Detailed Head</td>
					<td width="15%">Row</td>
					<td width="15%"">Amount <Font face='Rupee Foradian'>`</Font>* Non
						Plan</td>
					<td width="20%">Head Of Account Code</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">Basic<br> <c:forEach
							items="${OuterVo.allowEdpCompoList}" var="allow">

							<c:out value="${allow.rltBillTypeEdp.edpDesc}">
							</c:out>
							<br>
						</c:forEach>
					</td>
					<td>1<br /> <c:forEach items="${OuterVo.allowEdpCompoList}"
							var="allow">
							<c:if test="${allow.subDtlHead ne -1}">
								<c:out value="${allow.subDtlHead}">
								</c:out>
							</c:if>
							<br>
						</c:forEach>
					</td>
					<td align="center">1<br /> <c:forEach
							items="${OuterVo.allowEdpCompoList}" var="allow">
							<c:out value="${row}">
							</c:out>
							<br>
							<c:set var="row" value="${row+1}" />
						</c:forEach>
					</td>
					<td align="right"><%=allowMap.get("0101~BSC")%><br /> <c:forEach
							items="${OuterVo.allowEdpCompoList}" var="allow">
							<c:set var="edpCode" value="${allow.rltBillTypeEdp.edpCode}" />
							<%
								//System.out.println("Key is "+pageContext.getAttribute("edpCode").toString().trim()+"~EXP");
							%>
							<%=allowMap.get(pageContext.getAttribute("edpCode")
							.toString().trim()
							+ "~EXP")%>
							<br />
						</c:forEach>
					</td>
					<td align="right"><c:out
							value="${OuterVo.mstScheme.schemeCode}" />01</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="left">Total Salary &nbsp; &nbsp; &nbsp; &nbsp;</td>
					<td><c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right"><c:out value="${OuterVo.totalSalary}" />
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;</td>
					<td align="left"><-->Advances &nbsp; :-</td>
					<td><c:out value=""></c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><c:forEach
							items="${OuterVo.allowAdvEdpCompoList}" var="allowAdv">

							<c:out value="${allowAdv.rltBillTypeEdp.edpShortName}">
							</c:out>
							<br>
						</c:forEach>
					</td>
					<td align="left">&nbsp; </t>
					</td>
					<td><c:forEach items="${OuterVo.allowAdvEdpCompoList}"
							var="allow">
							<c:out value="${row}">
							</c:out>
							<br>
							<c:set var="row" value="${row+1}" />
						</c:forEach>
					</td>
					<td align="right"><c:forEach
							items="${OuterVo.allowAdvEdpCompoList}" var="allow">
							<c:set var="edpCode" value="${allow.rltBillTypeEdp.edpCode}" />
							<c:set var="rceExp" value="${allow.rltBillTypeEdp.expRcpRec}" />
							<%=allowMap.get(pageContext.getAttribute("edpCode")
							.toString()
							+ "~"
							+ pageContext.getAttribute("rceExp").toString())%>


							<br />
						</c:forEach>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="left">GrossSalary</t>
					</td>
					<td><c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right"><c:out value="${OuterVo.gross}" />
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td>003</td>
					<td>Gross Amount</td>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right"><c:out value="${OuterVo.gross}" />
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>004</td>
					<td colspan="1" align="left">Deductions Adj. By Accountant
						General</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><c:forEach
							items="${OuterVo.dedycAgEdpCompoList}" var="allow">
							<c:choose>
								<c:when
									test="${allow.rltBillTypeEdp.edpShortName eq 'HBA House' }">

						HBA PRINCIPLE
					</c:when>
								<c:when
									test="${allow.rltBillTypeEdp.edpShortName eq 'GPF_ADV_GRP_ABC' }">

						GPF_GRP_ABC
					</c:when>

								<c:when
									test="${allow.rltBillTypeEdp.edpShortName eq 'GPF_ADV_GRP_D' }">

						GPF_GRP_D
					</c:when>

								<c:otherwise>
									<c:out value="${allow.rltBillTypeEdp.edpShortName}">
									</c:out>
								</c:otherwise>
							</c:choose>
							<br>
						</c:forEach>
					</td>
					<td><c:forEach items="${OuterVo.dedycAgEdpCompoList}"
							var="allow">
       							 
	    		 					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						</c:forEach>
					</td>
					<td><c:forEach items="${OuterVo.dedycAgEdpCompoList}"
							var="allow">
							<c:out value="${row}">
							</c:out>
							<br>
							<c:set var="row" value="${row+1}" />
						</c:forEach>
					</td>
					<td align="right"><c:forEach
							items="${OuterVo.dedycAgEdpCompoList}" var="allow">
							<c:set var="edpCode" value="${allow.rltBillTypeEdp.edpCode}" />
							<c:set var="rceExp" value="${allow.rltBillTypeEdp.expRcpRec}" />
							<%=allowMap.get(pageContext.getAttribute("edpCode")
							.toString()
							+ "~"
							+ pageContext.getAttribute("rceExp").toString())%>
							<br />
						</c:forEach>
					</td>

					<td align="right"><c:forEach
							items="${OuterVo.dedycAgEdpCompoList}" var="allow">
							<c:if test="${allow.headOfAcc ne 0 }">

								<c:out value="${allow.headOfAcc}" />
							</c:if>
							<br />
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>005</td>
					<td align="left">Total(A)</td>
					<td align="left">AG. DED
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right"><c:out value="${OuterVo.totalA}" />
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>006</td>
					<td align="left" colspan="1">Deductions Adj. By Treasury</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><c:set var="sizeOfHeadAC" value="0" /> <c:forEach
							items="${OuterVo.dedycTryEdpCompoList}" var="allow">
							<c:if test="${headAccNoListNew ne '' }">
								<c:set var="lstrOfHeadAC"
									value="${headAccNoListNew[sizeOfHeadAC]}" />
								<c:if test="${lstrOfHeadAC ne ' ' and lstrOfHeadAC ne 0}">
									<c:out value="${lstrOfHeadAC}" />
								-
							</c:if>
							</c:if>

							<c:choose>
								<c:when
									test="${allow.rltBillTypeEdp.edpShortName eq 'HBA House Int' }">
							HBA INTEREST
						</c:when>
								<c:otherwise>
									<c:out value="${allow.rltBillTypeEdp.edpShortName}"></c:out>
								</c:otherwise>
							</c:choose>
							<br>
							<c:set var="sizeOfHeadAC" value="${sizeOfHeadAC+1}" />
						</c:forEach></td>
					<td><c:forEach items="${OuterVo.dedycTryEdpCompoList}"
							var="allow">
       							 
	    		 					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						</c:forEach>
					</td>
					<td><c:forEach items="${OuterVo.dedycTryEdpCompoList}"
							var="allow">
							<c:out value="${row}">
							</c:out>
							<br>
							<c:set var="row" value="${row+1}" />
						</c:forEach>
					</td>
					<td align="right"><c:forEach
							items="${OuterVo.dedycTryEdpCompoList}" var="allow">
							<c:set var="edpCode" value="${allow.rltBillTypeEdp.edpCode}" />
							<c:set var="rceExp" value="${allow.rltBillTypeEdp.expRcpRec}" />
							<%=allowMap.get(pageContext.getAttribute("edpCode")
							.toString()
							+ "~"
							+ pageContext.getAttribute("rceExp").toString())%>
							<br />
						</c:forEach>
					</td>
					<td align="right"><c:forEach items="${headAccNoList}"
							var="headAccNoList">
							<c:if test="${headAccNoList ne '' }">
								<c:out value="${headAccNoList}" />
							</c:if>
							<br>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>007</td>
					<td align="left">Total(B)</td>
					<td align="left">TR. DED
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right"><c:out value="${OuterVo.totalB}" />
					</td>
					<td>&nbsp;</td>

				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="left">Total Deductions:</td>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right"><c:out value="${OuterVo.totalDed}" />
					</td>
					<td>&nbsp;</td>

				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="left">Net Pay:</td>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right"><c:out value="${OuterVo.netPay}" />
					</td>
					<td>&nbsp;</td>

				</tr>
			</table></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>

	<tr>

		<td align="center">


			<table width="80%" border="1" cellpadding="5" cellspacing="0">
				<tr>
					<td width="40%">&nbsp;</td>
					<td>A</td>
					<td>B</td>
					<td>B N Gz</td>
					<td>C</td>
					<td>D</td>
				</tr>
				<tr>
					<td>Sanctioned Post</td>
					<td><c:out value="${vacantCount[0]}"></c:out>
					</td>
					<td><c:out value="${vacantCount[1]}"></c:out>
					</td>
					<td><c:out value="${vacantCount[2]}"></c:out>
					</td>
					<td><c:out value="${vacantCount[3]}"></c:out>
					</td>
					<td><c:out value="${vacantCount[4]}"></c:out>
					</td>
				</tr>
				<tr>
					<td>Payment Drawn for No. of post</td>
					<td><c:out value="${gradeCount[0]}"></c:out>
					</td>
					<td><c:out value="${gradeCount[1]}"></c:out>
					</td>
					<td><c:out value="${gradeCount[2]}"></c:out>
					</td>
					<td><c:out value="${gradeCount[3]}"></c:out>
					</td>
					<td><c:out value="${gradeCount[4]}"></c:out>
					</td>
				</tr>
				<tr>
					<td>Payment Drawn for Actual post</td>
					<td><c:out value="${totalCount[0]}"></c:out>
					</td>
					<td><c:out value="${totalCount[1]}"></c:out>
					</td>
					<td><c:out value="${totalCount[2]}"></c:out>
					</td>
					<td><c:out value="${totalCount[3]}"></c:out>
					</td>
					<td><c:out value="${totalCount[4]}"></c:out>
					</td>
				</tr>
			</table></td>

	</tr>


	<table align="right">
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr align="right">
			<td style="padding-right: 80px"><p>
					<c:out value="${resValue.dsgnNameOfDDO}"></c:out>
					, <br>
					<c:out value="${resValue.DeptName}"></c:out>
				</p>
			</td>

		</tr>
	</table>


	<table>
		<br></br>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr style="padding-left: 80px">

			<br>
			<td>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		Note :-
		</td>
		</tr>
	</table>
	<tr>
		<td><pageBreak>
		</td>
	</tr>


	<table>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>
			<td>BILL GENERATION TIME: <c:out
					value="${resValue.VerificationDate}"></c:out>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td><p style="page-break-after: always"></p>
			</td>
		</tr>
	</table>

	<tr>
		<td>
			<hr /> Under Rupees (In words) : <c:out
				value="${netPayInWordsPlusOne}" /> Only
			<hr />
		</td>
	</tr>

	<tr>
		<td>
			<table cellpadding="2" cellspacing="0" border="0" frame="box"
				align="CENTER" width="110%">
				<tr>
					<td>
						<table width="150%">
							<tr>
								<td align="left">Net Amount Required for Payment</td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<td
									style="border: solid; border-color: black; border-bottom: solid; border-top: solid; border-left: solid; border-right: solid; border-width: 1px;"
									height="0.15px"><c:out value="${OuterVo.netPay}/-" /></td>
							</tr>

							<tr>
								<td>(Brought forward from First Page)</td>
							</tr>

							<tr>
								<td>Rupees(In words) : <c:out value="${netPayInWords}" />
									Only</td>
							</tr>
							<tr>
								<td><br /> In
									Cash&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<Font face='Rupee Foradian'>`</Font> <c:out
										value="${OuterVo.netPay}" /> &nbsp;&nbsp;(in
									words)Rupees&nbsp; <c:out value="${netPayInWords}" /> Only</td>
							</tr>

							<tr>
								<td>By RTR/Bank Draft&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <Font face='Rupee Foradian'>`</Font> <c:out value="${OuterVo.netPay}" />
									&nbsp;&nbsp;(in words)Rupees&nbsp; <c:out
										value="${netPayInWords}" /> Only</td>
							</tr>


							<tr>
								<td>By Adjustment(AG.) &nbsp;&nbsp;<Font face='Rupee Foradian'>`</Font>
									<c:out value="${OuterVo.totalA}" />&nbsp;&nbsp; (in
									Words)Rupees&nbsp;<c:out value="${deducAgInWords}" /> Only</td>
							</tr>
							<tr>
								<td>By Adjustment(TRY.)&nbsp;&nbsp;<Font face='Rupee Foradian'>`</Font>
									<c:out value="${OuterVo.totalB}" />&nbsp;&nbsp;&nbsp;&nbsp;(in
									Words)Rupees&nbsp;<c:out value="${deducTryInWords}" /> Only</td>
							</tr>

							<tr>
								<td>By Adjustment(Total)&nbsp; <Font face='Rupee Foradian'>`</Font>
									<c:out value="${OuterVo.totalDed}" /> &nbsp;&nbsp;(in
									Words)Rupees&nbsp;<c:out value="${deducTotalInWords}" /> Only</td>
							</tr>
							<tr>
								<td><br /> Received contents and certified that i have
									satisfied myself that all emoluments included in bills drawn 1
									month/2 month/3 *</td>
							</tr>

							<tr>
								<td>previous to this date with the exception of those
									detaild below (of which the total has been refunded by
									deduction from this</td>
							</tr>

							<tr>
								<td>bill) have been disbursed to the proper and their
									acquittances have been taken and filled in my office with
									receipt stamps dully</td>
							</tr>

							<tr>
								<td>cancelled for every payment in excess of <Font face='Rupee Foradian'>`</Font> 500.</td>
							</tr>

							<tr>
								<td>*One line to be used and the other scored out.</td>
							</tr>

						</table>
					</td>
				</tr>
				<tr>
					<td align="center" width="55%" style="padding-left: 80Px;">
						<table border="1" align="right" cellspacing="0">
							<caption>Details of Pay Absentee Refunded</caption>
							<tr>

								<td width="10%" align="center">Section of Establishment</td>
								<td width="10%" align="center">Name</td>
								<td width="10%" align="center">Period</td>
								<td width="10%" align="center">Rupees</td>
							</tr>
							<tr>

								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
							</tr>
							<tr>

								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
							</tr>
							<tr>

								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
							</tr>
							<tr>

								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
								<td style="border-bottom: none; border-top: none;">&nbsp;</td>
							</tr>

						</table> <td>
						<table width="85%" cellspacing="0">
							<tr>
								<td width="35%">Finacial Year</td>
								<td width="65%">:</td>
							</tr>
							<tr>
								<td width="35%">Sacntioned Grats</td>
								<td width="65%">:</td>
							</tr>
							<tr>
								<td width="35%">Expen. Incu. this Bill</td>
								<td width="65%">:</td>
							</tr>
							<tr>
								<td width="35%">Amount of Bill</td>
								<td width="65%">:</td>
							</tr>
							<tr>
								<td width="35%">Balance Amount</td>
								<td width="65%">:</td>

							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td>
						<table border="1" width="161%" cellspacing="0">
							<tr height="70px">
								<td width="48%">
									<p>FOR USE OF TREASURY</p> <br /> <br />
									<p>
										Pay <Font face='Rupee Foradian'>`</Font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										(in
										words)Rupees:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</p>
								</td>
								<td>
									<p>Drawing And Disbursing officer
										:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> <br /> <br />
									<p>Date
										:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Section
										Officer,Mumbai :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
								</td>
							</tr>
							<tr>
								<td>
									<table cellspacing="0" cellpadding="1">
										<tr>
											<td width="90%">Paid by transfer credit
												Rs.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br /> As
												detailed below:-</td>
											<td width="10%" align="center"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">
												<b>Rupees:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
											</td>
										</tr>
										<tr>
											<td width="90%">0021 Taxes on Income</td>
											<td width="10%" align="CENTER"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
										<tr>
											<td width="90%">0028 Tax on Employement(Profession Tax)</td>
											<td width="10%" align="CENTER"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
										<tr>
											<td width="90%">0216 Housing</td>
											<td width="10%" align="CENTER"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
										<tr>
											<td width="90%">6216 Loans for Housing</td>
											<td width="10%" align="CENTER"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
										<tr>
											<td width="90%">8011 Insurance and Pension
												Fund(M.S.L.I.)</td>
											<td width="10%" align="CENTER"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
										<tr>
											<td width="90%">8011 Insurance and Pension
												Fund(GIS,1982)</td>
											<td width="10%" align="CENTER"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
										<tr>
											<td width="90%">8711 Adjusting Account with P & T -
												P.L.I</td>
											<td width="10%" align="CENTER"
												style="border-bottom: solid; border-left: solid; border-width: 1px; border-color: black;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
										<tr>
											<td width="90%">Total</td>
											<td width="10%" align="CENTER"
												style="border-left: solid; border-width: 1px; border-color: black">&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
									</table>
								</td>
								<td>
									<p>Paid by Transfer
										:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

									<p>Accountant :</p>

									<hr />
									<p>Cheque No :</p>

									<p>Drawn on :</p>

									<p>A.T.O :</p>

									<hr />
									<p>Cheque delivered on : :</p>

									<p>Treasury Clerck :</p>
								</td>
							</tr>
							<tr>
								<td>
									<p>Date :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> <br />
									<p>Head Accountant :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u> <br />
									<p>Deputy Accountant :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

									<br />
									<p>
										<c:out value="${resValue.officeDetails}:" />
									</p> <!--<p>Asst. Pay & Account Officer / Treasury Officer
								:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
								-->
									<br />
								</td>
								<td>
									<p>For Audit Office:</p> <br />
									<p>Admitted Rs :</p> <br />
									<p>Disallowed Rs :</p>

									<p>Objected Rs :</p> <br /> <br /> <br />
									<p>Reasons for Objection :</p> <br /> <br />
									<p>Auditor/Section Officer/Accountants Officer:</p>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
			
			
		


			<hdiits:form name="ViewOuterFirst" validate="true" method="POST"
							action="" encType="multipart/form-data">
							<table align="center">
					<tr>
						
							<td><input name="Back" type="button" Value="Back"
										onclick=GoToPage();></input>
							</td>
							<td>
							<input name="Print" type="button" value="Print"
										onClick=printReport();></input>
							</td>
							<td>
			                 <input name="Save" value="Save" type="button"
										id="saveButton" onclick=doSaveAs()>
							</td>
					</tr>
				</table>
			</hdiits:form>

					</table>
</html>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

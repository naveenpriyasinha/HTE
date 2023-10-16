
<%
	try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import="java.util.Map"%>
<fmt:setLocale value="${sessionScope.locale}" />
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<c:set var="gpfBifurcatedList" value="${resValue.gpfBifurcatedList}"></c:set>

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


<table cellpadding="0" cellspacing="0" border="0" frame="box"
	align="CENTER" width="100%">

	<tr>
		<td align="center">
			<p>
				Outer Page Of Monthly Pay Bill<br> (As per Govt Resolution No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           Dated      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               )
				
			</p></td>
	</tr>
	<tr>
		<td align="left">
			<p>
				Bill For :
				<c:out value="${resValue.officeDetails}" />
				-A,B,B N Gz,C,D-Both Permanent<br /> <br /> Name of Office :
				<c:out value="${resValue.officeDetails}" /><br>
				Month :
			<b>	<c:out value="${OuterVo.month}" /></b>
				Year :
			<b>	<c:out value="${OuterVo.year}" /></b>
				Bill Id :
			<b>	<c:out value="${OuterVo.billId}" /></b>
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
								
							</tr>
							<tr>
								<td align="left">
									<p>
										Treasury/Sub Treasury Code:
										<c:out value="${TresuryCode}" />
										&nbsp;<br>
										Treasury/Sub Treasury Name:
										<c:out value="${TresuryName}" />
									</p>
									<br>
									<p>
										Drawing Officer's Code &nbsp;
										<c:out value="${ddocode} "></c:out>
									
									<br>
									Drawing Officer's Designation &nbsp;
										<c:out value="${resValue.dsgnNameOfDDO}"></c:out>
										<br>
									Name Of Cluster/Beat/Block/Group: &nbsp;
									
										<br>
									Name Of School: &nbsp;
									
										
										<br>
									Percentage of Grant(%): &nbsp;20/40/60/80/100
									
										<br>
								School Code: &nbsp;
									<br>
								Bank Name/Branch Name: &nbsp;
									
									</td>
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
											<td>:<c:out value="01 SALARY " /> &nbsp; &nbsp;Scheme
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
								<td style="border-bottom: solid; border-left: solid; border-top: solid; border-color: black; border-width: 1px">
										<!-- <h5 align="right">PLAN/NON PLAN</h5></td> -->
									<c:choose>
							<c:when
								test="${((OuterVo.year==2017 && (OuterVo.month eq 'January' || OuterVo.month eq 'February' || OuterVo.month eq 'March')) || OuterVo.year<2017)}">
								<h5 align="right">Plan/Non-Plan</h5>
							</c:when>
							<c:otherwise>
								<h5 align="right">Scheme/Committed</h5>
							</c:otherwise>
						</c:choose>
							</tr>
							<tr>
								<td style="border-bottom: solid; border-left: solid; border-top: solid; border-color: black; border-width: 1px">
									<h5 align="right">Charged / Voted</h5></td>
							</tr>

							<tr>
								<td align="left" style="border-bottom: solid; border-left: solid; border-top: solid; border-color: black; border-width: 1px"
									>Voucher
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
					<td width="15%"">Amount <Font face='Rupee Foradian'>`</Font></td>
					<td width="20%">Head Of Account Code</td>
				</tr>
					<tr>
					<td width="5%" align="center">&nbsp;</td>
					<td width="25%">A</td>
					<td width="20%">&nbsp;</td>
					<td width="15%">&nbsp;</td>
					<td width="15%"">&nbsp;</td>
					<td width="20%">&nbsp;</td>
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
					<td>Total Of 1</td>
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
					<td align="left"> Advances &nbsp; :-</td>
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
				<td>&nbsp;</td>
					<td colspan="3" align="left">B</td>
				
				</tr>
				<tr>
					<td>004</td>
					<td colspan="1" align="left">Deductions Adj. By CAFO/Supri./Admin. Officer</td>
					<td>&nbsp;</td>
					<td colspan="1" align="left">&nbsp;</td>
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td align="left"><c:forEach	items="${gpfBifurcatedList}" var="gpfList">
							<c:if test="${gpfList[1]!=0}">
							<c:out value="GPF_ABC">
									</c:out>
							<br /></c:if>
							</c:forEach>
					</td>
					<td><c:forEach items="${gpfBifurcatedList}"	 var="gpfList">
       							 	<c:if test="${gpfList[1]!=0}">
	    		 					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br></c:if>
						</c:forEach>
					</td>
						<td><c:forEach items="${gpfBifurcatedList}"
							var="gpfList">
								<c:if test="${gpfList[1]!=0}">
							<c:out value="${row}">
							</c:out>
							<br></c:if>
							<c:set var="row" value="${row+1}" />
						</c:forEach>
					</td>
					<td align="right"><c:forEach
							items="${gpfBifurcatedList}" var="gpfList">
						<c:if test="${gpfList[1]!=0}">
						<c:out value="${gpfList[1]}"></c:out>
						
							<br /></c:if>
						</c:forEach>
					</td>

					<td align="right"><c:forEach
							items="${gpfBifurcatedList}" var="gpfList">
								<c:if test="${gpfList[1]!=0}">
							<c:out value="${gpfList[0]}"></c:out>
							
							<br /></c:if>
						</c:forEach>
					</td>
				</tr>
				
				<tr>
				<td>&nbsp;</td>
					<td align="left"><c:forEach
							items="${gpfBifurcatedList}" var="gpfList">
							<c:if test="${gpfList[2]!=0}">
							<c:out value="GPF_D">
									</c:out>
							<br /></c:if>
							</c:forEach>
					</td>
					<td><c:forEach items="${gpfBifurcatedList}"	 var="gpfList">
       							 	<c:if test="${gpfList[2]!=0}">
	    		 					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br></c:if>
						</c:forEach>
					</td>
						<td><c:forEach items="${gpfBifurcatedList}"
							var="gpfList">
								<c:if test="${gpfList[2]!=0}">
							<c:out value="${row}">
							</c:out>
							<br></c:if>
							<c:set var="row" value="${row+1}" />
						</c:forEach>
					</td>
					<td align="right"><c:forEach
							items="${gpfBifurcatedList}" var="gpfList">
								<c:if test="${gpfList[2]!=0}">
						<c:out value="${gpfList[2]}"></c:out>
							<br /></c:if>
						</c:forEach>
					</td>

					<td align="right"><c:forEach
							items="${gpfBifurcatedList}" var="gpfList">
								<c:if test="${gpfList[2]!=0}">
							<c:out value="${gpfList[0]}"></c:out>
							
							<br /></c:if>
						</c:forEach>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="left"><c:forEach
							items="${OuterVo.dedycAgEdpCompoList}" var="allow">
							<c:if test="${allow.rltBillTypeEdp.edpCode ne '5054' && allow.rltBillTypeEdp.edpCode ne '5055' && allow.rltBillTypeEdp.edpCode ne '9702' && allow.rltBillTypeEdp.edpCode ne '9703' && allow.rltBillTypeEdp.edpCode ne '9780' && allow.rltBillTypeEdp.edpCode ne '9583'}">
							<c:choose>
								<c:when
									test="${allow.rltBillTypeEdp.edpShortName eq 'HBA House' }">

						HBA PRINCIPLE
								</c:when>
								

								<c:otherwise>
								
									<c:out value="${allow.rltBillTypeEdp.edpShortName}"></c:out>
									
								</c:otherwise>
							</c:choose>
							<br>
							</c:if>
						</c:forEach>
					</td>
					<td><c:forEach items="${OuterVo.dedycAgEdpCompoList}"
							var="allow">
       						<c:if test="${allow.rltBillTypeEdp.edpCode ne '5054' && allow.rltBillTypeEdp.edpCode ne '5055' && allow.rltBillTypeEdp.edpCode ne '9702' && allow.rltBillTypeEdp.edpCode ne '9703' && allow.rltBillTypeEdp.edpCode ne '9780' && allow.rltBillTypeEdp.edpCode ne '9583'}">	 
	    		 					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
	    		 			</c:if>
						</c:forEach>
					</td>
					<td><c:forEach items="${OuterVo.dedycAgEdpCompoList}"
							var="allow">
							<c:if test="${allow.rltBillTypeEdp.edpCode ne '5054' && allow.rltBillTypeEdp.edpCode ne '5055' && allow.rltBillTypeEdp.edpCode ne '9702' && allow.rltBillTypeEdp.edpCode ne '9703' && allow.rltBillTypeEdp.edpCode ne '9780' && allow.rltBillTypeEdp.edpCode ne '9583'}">
							<c:out value="${row}">
							</c:out>
							
							
							<br>
							<c:set var="row" value="${row+1}" />
							</c:if>
						</c:forEach>
					</td>
					<td align="right"><c:forEach
							items="${OuterVo.dedycAgEdpCompoList}" var="allow">
							<c:if test="${allow.rltBillTypeEdp.edpCode ne '5054' && allow.rltBillTypeEdp.edpCode ne '5055' && allow.rltBillTypeEdp.edpCode ne '9702' && allow.rltBillTypeEdp.edpCode ne '9703' && allow.rltBillTypeEdp.edpCode ne '9780' && allow.rltBillTypeEdp.edpCode ne '9583'}">
							<c:set var="edpCode" value="${allow.rltBillTypeEdp.edpCode}" />
							<c:set var="rceExp" value="${allow.rltBillTypeEdp.expRcpRec}" />
							<%=allowMap.get(pageContext.getAttribute("edpCode")
							.toString()
							+ "~"
							+ pageContext.getAttribute("rceExp").toString())%>
							
						
							<br />
							</c:if>
						</c:forEach>
					</td>

					<td align="right"><c:forEach
							items="${OuterVo.dedycAgEdpCompoList}" var="allow">
							<c:if test="${allow.rltBillTypeEdp.edpCode ne '5054' && allow.rltBillTypeEdp.edpCode ne '5055' && allow.rltBillTypeEdp.edpCode ne '9702' && allow.rltBillTypeEdp.edpCode ne '9703' && allow.rltBillTypeEdp.edpCode ne '9780' && allow.rltBillTypeEdp.edpCode ne '9583'}">
							<c:if test="${allow.headOfAcc ne 0 }">
						
										<c:out value="${allow.headOfAcc}" />
															
							<br />
							</c:if>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>005</td>
					<td align="left">Total(B)</td>
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
					<td align="left" colspan="1">C</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">
					<c:set var="sizeOfHeadAC" value="0" /> <c:forEach
							items="${OuterVo.dedycTryEdpCompoList}" var="allow"> 
							<c:if test="${headAccNoListNew ne '' }"> 
								<c:set var="lstrOfHeadAC" value="${headAccNoListNew[sizeOfHeadAC]}" />
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
					<td align="left">Total(C)</td>
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
					<td align="left">(B+C)</td>
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
				<tr>
					<td>&nbsp;</td>
					<td align="left">Fee Details</td>
					<td align="left">&nbsp;</td>
					<td>&nbsp;<c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right">&nbsp;
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">Amount Recoverable during the year</td>
					<td align="left">&nbsp;</td>
					<td>&nbsp;<c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right">&nbsp;
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">Total Amount Paid Till Last Month</td>
					<td align="left">&nbsp;</td>
					<td>&nbsp;<c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right">&nbsp;
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">Balance To Be Credited</td>
					<td align="left">&nbsp;</td>
					<td>&nbsp;<c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right">&nbsp;
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">Amount Credited During This Month</td>
					<td align="left">&nbsp;</td>
					<td>&nbsp;<c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right">&nbsp;
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">Balance Fee PAyable</td>
					<td align="left">&nbsp;</td>
					<td>&nbsp;<c:out value="${row}">
						</c:out> <c:set var="row" value="${row+1}" />
					</td>
					<td align="right">&nbsp;
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
<%--

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
			</table>
		 --%>
			</td>

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

			
		


			<hdiits:form name="ViewOuterFirst" validate="true" method="POST"
							action="" encType="multipart/form-data">
							<table align="center">
					<tr>
						
							<td><input name="Back" type="button" Value="Back"
										onclick="GoToPage();"></input>
							</td>
							<td>
							<input name="Print" type="button" value="Print"
										onClick="printReport();"></input>
							</td>
							<td>
			                 <input name="Save" value="Save" type="button" id="saveButton" onclick="doSaveAs();"/>
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

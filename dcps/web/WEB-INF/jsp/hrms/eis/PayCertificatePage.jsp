 <%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="PayCertificateList" value="${resValue.PayCertificatedata}" ></c:set>
<c:set var="getPayCertificateLoan" value="${resValue.getPayCertificateLoan}" ></c:set>
<c:set var="signDesigName" value="${resValue.signDesigName}" ></c:set>
<c:set var="signDeptName" value="${resValue.signDeptName}" ></c:set>
<c:set var="signCardexCode" value="${resValue.signCardexCode}" ></c:set>
<c:set var="signCityName" value="${resValue.signCityName}" ></c:set>

<%
 List PayCertificateList = (List)pageContext.getAttribute("PayCertificateList");
if(PayCertificateList!=null && PayCertificateList.size()==0)
 {
   	pageContext.setAttribute("PayCertificateStatus","1");
 }
  %>
<style>
	.padding{padding-left:10px;padding-top:10px;padding-bottom:5px;};
	.paddingborder{
					padding-left:10px;
					padding-top:10px;
					padding-bottom:5px;
					border-top-style:dashed;
					border-top-width:1px;
					border-top-color:black;};
	.paddingright{padding-right:30px;};
	
</style>
<script type="text/javascript">
var point2=window.opener.document.getElementById("point2").value;
var point3=window.opener.document.getElementById("point3").value;
var no=window.opener.document.getElementById("no").value;
var of=window.opener.document.getElementById("of").value;
var vide=window.opener.document.getElementById("vide").value;
var transfer=window.opener.document.getElementById("transfer").value;

function onload()
{	var v_name= window.opener.document.frmPayCertificate.vide.value; 
	var ofc =window.opener.document.frmPayCertificate.of.value; 
	document.getElementById("trans").innerHTML = " of the ${signDeptName} proceeding on TRANSFER / RETIREMENT <b>" + window.opener.document.frmPayCertificate.transfer.value  +  "</b>&nbsp;&nbsp;"  + "Vide Order No: " + v_name;
//	document.getElementById("nums").innerHTML = " No. " + window.opener.document.frmPayCertificate.no.value + "  OF  20"+ ofc;
	document.getElementById("pointt2").innerHTML = " 2.  He/She has been paid upto " +  window.opener.document.frmPayCertificate.point2.value + " to the following rates: " 
	document.getElementById("pointt3").innerHTML = " 3.  He/She made over charge of the office of ${signDeptName} " + " " + window.opener.document.frmPayCertificate.point3.value;

}
	
</script>

<hdiits:form name="frmPayCertificate" validate="true" method="POST"
	action="#" encType="multipart/form-data">
<!--<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="PayCertificate" caption="PayCertificate"/> </b></a></li>
	</ul>
	</div>
	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<div align="center">
	-->
	<c:choose>
		<c:when test="${ PayCertificateStatus eq '1'}">
			<center><br><br><br><br><c:out value="No Record Found. "/></center>
		</c:when>
		<c:otherwise>
	<c:forEach var="PayCertificatedata" items="${PayCertificateList}">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="Center" class="padding" >
				<font size="3"><b><u> Last Pay Certificate</u></b></font><br><br>
				 
			</td></tr>
			<tr>
			<td align="Center" class="padding" id="nums" style="font-weight: bold;"></td >
			
		</tr>
		<tr>
			<td align="left" class="padding">
				LAST PAY CERTIFICATE OF <b><c:out value="${PayCertificatedata[0]}"/></b>,<b><c:out value="${PayCertificatedata[26]}"/></b><br> 
			</td>
		</tr>
		<tr>	
				<td align="left" class="padding" id = "trans">
				</td>
				<td align="left" width="2%" id = "vdorder" >
				</td>
				
		</tr>
		<tr>
			<td width="55%" class="padding" align="left" id="pointt2" >
			</td>
		</tr>
		<tr>
			<td valign="top" class="padding">
				<table width="98%" align="left" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td align="left" class="paddingborder" width="25%">
							PARTICULARS
						</td>
						<td align="left" class="paddingborder" width="25%">
							RATE
						</td>
						<td align="left" class="paddingborder" width="25%">
							PARTICULARS
						</td>
						<td align="left" class="paddingborder" width="25%">
							RATE
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder">
							Basic Pay
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[1]}"/>
						</td>
						<td align="left" class="paddingborder">
							C.L.A.
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[6]}"/>
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder">
							Grade Pay
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[35]}"/>
						</td>
						<td align="left" class="paddingborder">
							H.R.A.
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[7]}"/>
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder">
							Dearness Allowance
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[3]}"/>
						</td>
						<td align="left" class="paddingborder">
							M.A.
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[8]}"/>
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder">
							Special Pay
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[4]}"/>
						</td>
						<td align="left" class="paddingborder">
							W.A.
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[9]}"/>
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder">
							Personal Pay
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[5]}"/>
						</td>
						<td align="left" class="paddingborder">
							Trans. Allowance
						</td>
						<td align="left" class="paddingborder">
							<c:out value="${PayCertificatedata[10]}"/>
						</td>
					</tr>
					<tr>
						<td class="paddingborder" colspan="2" align="left">
							Pay Scale : <c:out value="${PayCertificatedata[17]}-${PayCertificatedata[18]}"></c:out>
						</td>
						<td width="55%" class="paddingborder" colspan="2" align="left">
							Increment : <c:out value="${PayCertificatedata[23]}"></c:out>	<br>
						</td>
					</tr>
					<tr>
						<td class="padding" colspan="4" align="left">
							Bank A/c: <c:out value="${PayCertificatedata[19]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${PayCertificatedata[20]}"> </c:out>&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${PayCertificatedata[21]}"></c:out> &nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${PayCertificatedata[22]}"></c:out>
						</td>
					</tr>
					<tr>
						<td class="paddingborder" colspan="4" align="left" height="1px">
						 &nbsp;
						</td>
					</tr>
				</table>		
			</td>
		</tr>
		<tr> 
			<td align="left" class="padding" id="pointt3">
				
			</td>
		</tr>
		<tr>
			<td width="55%" class="padding" align="left">4.  Recoveries are to be made from the paybill of 
			<c:choose>
			 <c:when test='${PayCertificatedata[27] ne 12}'>
		
	<b><c:out value="${PayCertificatedata[27] + 1}"></c:out>/<c:out value="${PayCertificatedata[28]}"></c:out></b> 
	</c:when>
	
	<c:otherwise><c:out value="1"></c:out>/<c:out value="${PayCertificatedata[28]+1}"></c:out></c:otherwise>
	
	</c:choose>of the Govt. Services as detailed below:
			</td>
		</tr>
		<tr>
			<td valign="top" class="padding">
				<table width="98%" align="left" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td align="left" class="paddingborder" width="20%">
							PARTICULARS & A/c No.
						</td>
						<td align="left" class="paddingborder" width="10%">
							Monthly Deduction
						</td>
						<td align="left" class="paddingborder" width="20%">
							Installment No.
						</td>
						<td align="left" class="paddingborder" width="20%">
							Total Advance
						</td>
						<td align="left" class="paddingborder" width="20%">
							Recovered Upto <c:out value="${PayCertificatedata[29]}"></c:out>/<c:out value="${PayCertificatedata[28]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="10%">
							Remarks
						</td>
					</tr>
					
				<%-- 	<tr>
						<td align="left" class="paddingborder" width="20%">
							Pan 
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					--%>
					
					<tr>
						<td align="left" class="paddingborder" width="20%">
							I.Tax : <c:out value="${PayCertificatedata[25]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[11]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp; 
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>

					<tr>
						<td align="left" class="paddingborder" width="20%">
							Prof. Tax
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[12]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>

					<tr>
						<td align="left" class="paddingborder" width="20%">
							S.I.S./G.I.S.
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[13]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder" width="20%">
							GPF : <c:out value="${PayCertificatedata[24]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[14]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder" width="20%">
							DA in GPF
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[34]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					<c:forEach var="getPayCertificateLoan" items="${getPayCertificateLoan}">
						<tr>
							<td align="left" class="paddingborder" width="20%">
								<c:out value="${getPayCertificateLoan[7]}"></c:out> : <c:out value="${getPayCertificateLoan[8]}"></c:out>
							</td>
							<td align="left" class="paddingborder" width="10%">
								<c:out value="${getPayCertificateLoan[4]}"></c:out>
							</td>
							<td align="left" class="paddingborder" width="20%">
								<c:out value="${getPayCertificateLoan[6]}"></c:out>/<c:out value="${getPayCertificateLoan[3]}"></c:out> <c:out value="${getPayCertificateLoan[1]}"></c:out>
							</td>
							<td align="left" class="paddingborder" width="20%">
								<c:out value="${getPayCertificateLoan[2]}"></c:out>
							</td>
							<td align="left" class="paddingborder" width="20%">
								<c:out value="${getPayCertificateLoan[5]}"></c:out>
							</td>
							<td align="left" class="paddingborder" width="10%">
								&nbsp;
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td align="left" class="paddingborder" width="20%">
							Pay Recovery
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[30]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder" width="20%">
							H.R.R.
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[33]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder" width="20%">
							Jeep Rent
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[32]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="left" class="paddingborder" width="20%">
							Misc. Recovery
						</td>
						<td align="left" class="paddingborder" width="10%">
							<c:out value="${PayCertificatedata[31]}"></c:out>
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="20%">
							&nbsp;
						</td>
						<td align="left" class="paddingborder" width="10%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="paddingborder" colspan="6" align="right">
						&nbsp;
						</td>
					</tr>
					<tr>
						<td c colspan="4" align="left" width="72%">
							Date: 
						</td>
						<td c colspan="1" align="center" width="28%">
                        ${signDesigName}
						</td>
					</tr>
					<tr>
						<td c colspan="4" align="left" width="72%">
							&nbsp;
						</td>
						<td c colspan="1" align="center" width="28%">
							${signDeptName}
						</td>
					</tr>
					<tr>
						<td c colspan="4" align="left" width="72%">
							Place: <hdiits:caption captionid="gandhinagar" bundle="${commonLables}"/>
						</td>
						<td c colspan="1" align="center" width="28%">
                        ${signCardexCode}
						</td>
					</tr>
				</table>		
			</td>
		</tr>
	</table>
	<br><br>
	
</c:forEach>
</c:otherwise>
</c:choose>












<script type="text/javascript">
onload();
</script>
<!--</div>
 
	
	</div>
	</div>--></hdiits:form>
	<!--<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
--><%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels"
	var="PensionLabels" scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="getDDODtls" value="${resValue.getDDODtls}" />
<c:set var="gpfAccNo" value="${resValue.gpfAccNo}" />
<c:set var="NomineeDtls" value="${resValue.NomineeDtls}" />
<c:set var="PensionerAddr" value="${resValue.PensionerAddr}" />
<c:set var="AddrAfterRetirement" value="${resValue.AddrAfterRetirement}" />
<c:set var="FamilyDtls" value="${resValue.FamilyDtls}" />
<c:set var="famDtlsSize" value="${resValue.famDtlsSize}" />
<c:set var="PensionerNomDtls" value="${resValue.PensionerNomDtls}" />
<c:set var="NomDtlsSize" value="${resValue.NomDtlsSize}" />
<c:set var="NonineeName" value="${resValue.NonineeName}" />
<c:set var="relation" value="${resValue.relation}" />
<c:set var="AvgPayDtls" value="${resValue.AvgPayDtls}" />
<c:set var="AvgPaySize" value="${resValue.AvgPaySize}" />
<c:set var="Avgtotal" value="${resValue.Avgtotal}" />
<c:set var="avgPay" value="${resValue.avgPay}" />
<c:set var="AdvanceDtls" value="${resValue.AdvanceDtls}" />
<c:set var="AdvanceSize" value="${resValue.AdvanceSize}" />
<c:set var="ServiceBrkDtls" value="${resValue.ServiceBrkDtls}" />
<c:set var="ServiceBrkSize" value="${resValue.ServiceBrkSize}" />
<c:set var="PensionerPayDtls" value="${resValue.PensionerPayDtls}" />
<c:set var="typeName" value="${resValue.typeName}" />
<c:set var="PensionCalcDtls" value="${resValue.PensionCalcDtls}" />
<c:set var="ProvDtls" value="${resValue.ProvDtls}" />
<c:set var="year" value="${resValue.year}" />
<c:set var="month" value="${resValue.month}" />
<c:set var="days" value="${resValue.days}" />
<c:set var="gratDdoDtls" value="${resValue.gratDdoDtls}" />
<c:set var="familyPensioner" value="${resValue.familyPensioner}" />
<c:set var="ProvPension" value="${resValue.ProvPension}" />
<c:set var="ProvGratuity" value="${resValue.ProvGratuity}" />
<c:set var="ProvPensionSize" value="${resValue.ProvPensionSize}" />
<c:set var="ProvGratuitySize" value="${resValue.ProvGratuitySize}" />
<c:set var="EmpFname" value="${resValue.EmpFname}" />
<c:set var="EmpMname" value="${resValue.EmpMname}" />
<c:set var="EmpLname" value="${resValue.EmpLname}" />
<c:set var="EmpFathrname" value="${resValue.EmpFathrname}" />
<!-- added by ankita-->
<c:set var="QSerDtls" value="${resValue.QSerDtls}" />
<c:set var="AddtnCVPDtls" value="${resValue.AddtnCVPDtls}" />
<style type="text/css">
td
{
font-size: 15px;
font-family: 'sans-serif'
}
</style>


<script type="text/javascript">
	function printReport() {

		document.getElementById('Print').style.visibility = 'hidden'; // hide
		window.print();
		document.getElementById('Print').style.visibility = 'visible'; // show 

	}
</script>
<script>

</script>




<hdiits:form name="loadPensionCaseForm" validate="true" method="post" action=" ">


<table align="center" width="100%" border="1px #000000"  style="border: none; border-collapse: collapse;"
			cellspacing="1" cellpadding="1">
<tr>
<td colspan="2" align="center" style="border: none;"><b><u><fmt:message key="PPROC.PENLETTER"
						bundle="${PensionLabels}"></fmt:message></u></b></td>
</tr>
<tr>
<td colspan="2" style="border: none;"><br></br></td>
</tr>
<tr>
<td><fmt:message key="PPROC.DDO10DIG"
						bundle="${PensionLabels}"></fmt:message></td>
<td>${getDDODtls[0]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.DEPTOFFC"
						bundle="${PensionLabels}"></fmt:message></td>
<td>${getDDODtls[1]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.MAIL"
						bundle="${PensionLabels}"></fmt:message></td>
<td>${getDDODtls[2]}</td>
<tr>
<td><fmt:message key="PPROC.TELEPHONE"
						bundle="${PensionLabels}"></fmt:message></td>
<td>${getDDODtls[3]}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="PPROC.FAX"
						bundle="${PensionLabels}"></fmt:message></b>&nbsp;&nbsp;&nbsp;&nbsp;${getDDODtls[4]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.WEBSITE"
						bundle="${PensionLabels}"></fmt:message></td>
<td>&nbsp;</td>
</tr>
<!-- </table>

<table align="center" width="100%" cellspacing="5" cellpadding="1"> -->
<tr>
<td colspan="2" style="border: none;"><br></br></td>
</tr>
<tr>
<td style="border: none;">NO: &nbsp;${getDDODtls[5]}</td>
<td style="border: none;">DATE:</td>
</tr>
<tr>
<td colspan="2" style="border: none;"><br></br></td>
</tr>
<tr>
<td colspan="2" style="text-indent: 20px ;border: none;">To,</td>
</tr>
<tr>
<td colspan="2" style="text-indent: 20px ;border: none;">The Pr.Accountant General,</td>
</tr>
<tr>
<td colspan="2" style="border: none;"><br></br></td>
</tr>
<tr>
<td colspan="2" style="border: none;"><br></br></td>
</tr>
<tr>
<td colspan="2" style="text-indent: 30px ;border: none;">Subject:&nbsp;Pension papers of &nbsp;<u>${getDDODtls[6]}</u></td>
</tr>
<tr>
<td colspan="2" style="text-indent: 50px ;border: none;">For authorization of Pension </td>
</tr>
<tr>
<td colspan="2" style="border: none;"><br></br></td>
</tr>
<tr>
<td colspan="2" style="text-indent: 20px ;border: none;">Sir, </td>
</tr>
<tr>
<td colspan="2" style="text-indent: 20px ;border: none;">I am directed to forward herewith the pension papers of &nbsp;<u>${getDDODtls[6]}</u>&nbsp; of this Department/Office for further necessary action </td>
</tr>
<tr>
<td colspan="2" style="text-indent: 20px ;border: none;" align="center">Yours faithfully, </td>
</tr>
<tr>
<td colspan="2" style="border: none;"><br></br></td>
</tr>
<tr>
<td colspan="2" style="text-indent: 20px ;border: none;" align="center">Head of the Office, </td>
</tr>
<tr>
<td colspan="2" style="text-indent: 20px ;border: none;" align="center">(Pension Sanctioning Authority) </td>
</tr>
</table>


<div style="page-break-after: always"></div>
<br></br>

<table align="center" width="100%" border="1px #000000"  style="border: none; border-collapse: collapse;"
			cellspacing="1" cellpadding="1">
<tr>
<td colspan="10"  align="center"><b>Section A</b></td>
</tr>			
<tr>
<td colspan="10" align="center"><b>(To be filled in by the applicant)</b></td>
</tr>
<tr>
<td>UID:</td>
<td colspan="1">${getDDODtls[7]}</td>
<td>EID:</td>
<td colspan="7">${getDDODtls[8]}</td>
</tr>
<tr>
<td>1</td>
<td><fmt:message key="PPROC.EMPCODE"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${getDDODtls[5]}</td>
</tr>
<tr>
<td rowspan="2">2</td>
<td rowspan="2"><fmt:message key="PPROC.GOVSERVNAME"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">First Name</td>
<td colspan="3">Middle Name</td>
<td colspan="4">Surname</td>
</tr>			
<tr>
<!--<td colspan="3">${getDDODtls[9]}</td>
<td colspan="3">${getDDODtls[10]}</td>
<td colspan="4">${getDDODtls[11]}</td>
-->
<td colspan="3">${EmpFname}</td>
<td colspan="3">${EmpMname}</td>
<td colspan="4">${EmpLname}</td></tr><tr>
<td>3</td>
<td><fmt:message key="PPROC.FATHERHUSNAME"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${EmpFathrname}</td>
</tr>
<tr>
<td>4</td>
<td><fmt:message key="PPROC.DATEOFBIRTH"
						bundle="${PensionLabels}"></fmt:message>(by Christian era)</td>
<td colspan="8">${getDDODtls[12]}</td>
</tr>
<tr>
<td>5</td>
<td><fmt:message key="PPROC.FAMPENSIN"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${familyPensioner[0]}</td>
</tr>
<tr>
<td>6</td>
<td><fmt:message key="PPROC.RELGOV"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${familyPensioner[1]}</td>
</tr>
<tr>
<td>7</td>
<td><fmt:message key="PPROC.RELIGION1"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="4">${getDDODtls[16]}</td>
<td colspan="2">Sex:</td>
<c:choose>
<c:when test="${getDDODtls[17]==77}">
<td colspan="2">Male</td>
</c:when>
<c:otherwise>
<td colspan="2">Female</td>
</c:otherwise>
</c:choose>
</tr>
<tr>
<td>8</td>
<td><fmt:message key="PPROC.MOBILENO"
						bundle="${PensionLabels}"></fmt:message></td>
<td>+91</td>
<td colspan="7">${getDDODtls[14]}</td>
</tr>
<tr>
<td>9</td>
<td><fmt:message key="PPROC.EMAILID"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${getDDODtls[15]}</td>
</tr>
<tr>
<td>10</td>
<td><fmt:message key="PPROC.GPFACCNO"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${gpfAccNo}</td>
</tr>
<tr>
<td rowspan="2" valign="top">11</td>
<td><fmt:message key="PPROC.RESADDR"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerAddr[1]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.STD"
						bundle="${PensionLabels}"></fmt:message></td>
<td>STD Code</td>
<td colspan="8">${PensionerAddr[2]}</td>
</tr>
<tr>
<td rowspan="2" valign="top">12</td>
<td><fmt:message key="PPROC.PERADDR"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${AddrAfterRetirement[1]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.STD"
						bundle="${PensionLabels}"></fmt:message></td>
<td>STD Code</td>
<td colspan="8">${AddrAfterRetirement[2]}</td>
</tr>



<tr>
<td rowspan="${famDtlsSize+3}" valign="top">13</td>
<td colspan="10"><fmt:message key="PPROC.CASEFAMDTLS"
						bundle="${PensionLabels}"></fmt:message></td>
</tr>
<tr>
<td>Sr No.</td>
<td colspan="3">Name of the members of family</td>
<td colspan="3"><fmt:message key="PPROC.DATEOFBIRTH"
						bundle="${PensionLabels}"></fmt:message></td>
<td>Relationship</td>
<td colspan="2">If Handicapped or mentally retarded, Pl. mention</td>
</tr>
<c:set var="count" value="1"></c:set>
<c:forEach var="famDtls" items="${FamilyDtls}">
<tr>
<td>${count}</td>
<td colspan="3">${famDtls[0]}</td>
<td colspan="3">${famDtls[1]}</td>
<td>${famDtls[2]}</td>
<td colspan="2">${famDtls[3]}</td>
</tr>
<c:set var="count" value="${count+1}"></c:set>
</c:forEach>
<tr><td colspan="9">&nbsp;</td></tr>


<tr><td style="border: none;"><div style="page-break-after: always"></div></td></tr>

<tr>
<td rowspan="6" valign="top">14</td>
<td rowspan="6" valign="top"><fmt:message key="PPROC.PAYPLACE"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">Treasury:&nbsp;${PensionerAddr[7]}</td>
</tr>
<tr>
<td colspan="8">Bank Name:&nbsp;${PensionerAddr[3]}</td>
</tr>
<tr>
<td colspan="8">Branch Name:&nbsp;${PensionerAddr[4]}</td>
</tr>
<tr>
<td colspan="8">IFSC Code No.:&nbsp;${PensionerAddr[5]}</td>
</tr>
<tr>
<td colspan="8">Account No:&nbsp;${PensionerAddr[6]}</td>
</tr>
<tr>
<td colspan="8">(zerox copy of 1st  page of Bank Pass Book to be enclosed)
Name on Pass Book must tally with the Name on the First Page of the Service Book.</td>
</tr>
<tr>
<td>15</td>
<td>Signature of the applicant</td>
<td colspan="8"></td>
</tr>

<tr>
<td colspan="10" align="center"><b>Section B</b></td>
</tr>
<tr>
<td colspan="10" align="center"><b>(to be filled in by the department)</b></td>
</tr>
<tr>
<td>16</td>
<td><fmt:message key="PPROC.DOACASE"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerAddr[8]}</td>
</tr>
<tr>
<td>17</td>
<td><fmt:message key="PPROC.DAILYWAGE"
						bundle="${PensionLabels}"></fmt:message></td>
						<td colspan="8">From  ${PensionerAddr[8]}  To  ${PensionerAddr[9]}</td>
<!--<td>From</td>
<td>DD</td>
<td>MM</td>
<td>YYYY</td>${PensionerAddr[8]}
<td>To</td>
<td>DD</td>
<td>MM</td>
<td>YYYY</td>${PensionerAddr[9]}
-->
</tr>

<tr>
<td>18</td>
<td><fmt:message key="PPROC.CONFIRMDATE"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="2">${PensionerAddr[15]}</td>

</tr>
<tr>
<td>19</td>
<td><fmt:message key="PPROC.DATEOFRETIREMENT"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerAddr[9]} &nbsp;&nbsp;&nbsp;${PensionerAddr[14]} </td>
<!-- <td colspan="3">MM</td>
<td colspan="3">YYYY</td> -->
</tr>

<tr>
<td>20</td>
<td><fmt:message key="PPROC.DOD"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerAddr[10]}</td>
<!-- <td colspan="3">MM</td>
<td colspan="3">YYYY</td> -->
</tr>
<tr>
<td>21</td>
<td><fmt:message key="PPROC.PENSCLASS"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${typeName}</td>
</tr>
<tr>
<td>22</td>
<td><fmt:message key="PPROC.DESGPOST"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerAddr[12]}</td>
</tr>
<tr>
<td rowspan="4" valign="top">23</td>
<td rowspan="2" valign="top"><fmt:message key="PPROC.DDOLASTSERVD"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${getDDODtls[0]}</td>
</tr>
<tr>
<td colspan="9">${getDDODtls[1]}</td>
</tr>
<tr>
<td>Telephone No. of Office</td>
<td colspan="9">${getDDODtls[3]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.MOBILENO"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="9">${getDDODtls[18]}</td>
</tr>
<tr>
<td rowspan="4" valign="top">24</td>
<td rowspan="2" valign="top"><fmt:message key="PPROC.DDOPAYGRAT"
						bundle="${PensionLabels}"></fmt:message></td>

<td colspan="8">${gratDdoDtls[0]}</td>
</tr>
<tr>
<td colspan="9">${getDDODtls[1]}</td>
</tr>
<tr>
<td>Telephone No. of Office</td>
<td colspan="9">${getDDODtls[3]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.MOBILENO"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="9">${getDDODtls[18]}</td>
</tr>
<tr>
<td>25</td>
<td><fmt:message key="PPROC.TREASPAYGRAT"
						bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${getDDODtls[20]}</td>
</tr>
<tr>
<c:choose>
<c:when test="${NomDtlsSize > 0 }">
<td rowspan="${NomDtlsSize+4}" valign="top">26</td>
</c:when>
<c:otherwise>
<td rowspan="${NomDtlsSize+3}" valign="top">26</td>
</c:otherwise>
</c:choose>
<td colspan="9"><fmt:message key="PPROC.NOMDTLSGRAT"
						bundle="${PensionLabels}"></fmt:message></td>
</tr>
<tr>
<td><fmt:message key="PPROC.NOMINEENAME"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="2"><fmt:message key="PPROC.GOVREL"
bundle="${PensionLabels}"></fmt:message></td>
<td><fmt:message key="PPROC.AMTSHARE"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="4"><fmt:message key="PPROC.ALTNOMINEE"
bundle="${PensionLabels}"></fmt:message></td>
<td><fmt:message key="PPROC.AMTSHARE"
bundle="${PensionLabels}"></fmt:message></td>
</tr>
<c:forEach var="NomDtls" items="${PensionerNomDtls}">
<tr>
<td>${NomDtls[0]}</td>
<td colspan="2" rowspan="2">${NomDtls[2]}</td>
<td rowspan="2">${NomDtls[3]}</td>
<td colspan="4" rowspan="2">${NomDtls[4]}</td>
<td rowspan="2">${NomDtls[3]}</td>
</tr>
<tr><td>${NomDtls[1]}</td></tr>
</c:forEach>
<tr><td colspan="9">&nbsp;</td></tr>
<tr>
<tr><td style="border: none;"><div style="page-break-after: always"></div></td></tr>
<td>27</td>
<td><fmt:message key="PPROC.FOREIGNSERV"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>



<tr>
<td>28</td>
<td><fmt:message key="PPROC.PENSPERIODMILT"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td>29</td>
<td><fmt:message key="PPROC.PENSFORMILT"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td>30</td>
<td><fmt:message key="PPROC.FAMPENSMIL"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td>31</td>
<td><fmt:message key="PPROC.PENSANY"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>

<td>32</td>
<td><fmt:message key="PPROC.RENDERSERV"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>

<tr>
<td rowspan="${ServiceBrkSize+4}" valign="top">33</td>
<td><fmt:message key="PPROC.GQS"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">From</td>
<td colspan="2">To</td>
<td>Years</td>
<td>Months</td>
<td>Days</td>
</tr>
<tr>
<td>Less:</td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>${QSerDtls[0]}</td>
<td>${QSerDtls[1]}</td>
<td>${QSerDtls[2]}</td>
</tr>
<c:forEach var="ServiceBrkDtls" items="${ServiceBrkDtls}">
<c:set var="count" value="0"></c:set>
<tr>
<td>${ServiceBrkDtls[3]}</td>
<td colspan="3">${ServiceBrkDtls[0]}</td>
<td colspan="2">${ServiceBrkDtls[1]}</td>
<td>${year[count]}</td>
<td>${month[count]}</td>
<td>${days[count]}</td>
</tr>
<c:set var="count" value="${count+1}"></c:set>
</c:forEach>
<%-- <tr>
<td><fmt:message key="PPROC.HALFWAGE"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.SERVINTERUPT"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.EXLEAVE"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.EXLEAVEPRIVATE"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.SUSPENSIONQS"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.ANYSERVICE"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr> --%>
<tr>
<td><fmt:message key="PPROC.TNQS"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>${QSerDtls[3]}</td>
<td>${QSerDtls[4]}</td>
<td>${QSerDtls[5]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.WEIGHTNQS"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td>${QSerDtls[6]}</td>
<td>${QSerDtls[7]}</td>
<td>${QSerDtls[8]}</td>
</tr>
<tr>
<td>34</td>
<td><fmt:message key="PPROC.LASTGP"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerPayDtls[3]} + ${PensionerPayDtls[1]}</td>
</tr>



<tr>
<td>35</td>
<td><fmt:message key="PPROC.PBGP"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerPayDtls[0]}</td>
</tr>

<tr>
<td>36</td>
<td><fmt:message key="PPROC.PAYRECK"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerPayDtls[2]}</td>
</tr>

<tr>
<td rowspan="${AvgPaySize+4}" valign="top">37</td>
<td colspan="9" align="center"><fmt:message key="PPROC.AVGPENPAY"
bundle="${PensionLabels}"></fmt:message></td>
</tr>
<tr>
<td>From</td>
<td colspan="2">To</td>
<td colspan="2">Pay+DP/GP</td>
<td colspan="2">NPA if admissible</td>
<td>Total</td>
<td>Amount</td>
</tr>
<c:forEach var="AvgPayDtls" items="${AvgPayDtls}">
<tr>
<td>${AvgPayDtls[0]}</td>
<td colspan="2">&nbsp;</td>
<td colspan="2">${AvgPayDtls[1]+AvgPayDtls[2]}</td>
<td colspan="2">${AvgPayDtls[3]}</td>
<td>${AvgPayDtls[4]}</td>
<td>&nbsp;</td>
</tr>
</c:forEach>
<tr>
<td>&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td colspan="2">Grand Total</td>
<td>${Avgtotal}</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td colspan="2">&nbsp;</td>
<td colspan="2">Pensionable Pay</td>
<td>${avgPay}</td>
<td>&nbsp;</td>
</tr>
<tr><td style="border: none;"><div style="page-break-after: always"></div></td></tr>
<tr>
<td>38</td>
<td><fmt:message key="PPROC.PROPPEN"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionCalcDtls[7]}</td>
</tr>

<tr>
<td>39</td>
<td><fmt:message key="PPROC.DOC"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionerAddr[13]}</td>
</tr>

<tr>
<td>40</td>
<td><fmt:message key="PPROC.PROPRET"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${PensionCalcDtls[6]}</td>
</tr>
<tr>
<td rowspan="5" valign="top">41</td>
<td>Details of commutation of pension  as per Form A/B/C
</td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td>Date of application for Commutation of Pension</td>
<td colspan="8">${PensionCalcDtls[0]}</td>
</tr>
<tr>
<td>Percentage of amount of monthly pension commuted</td>
<td colspan="8">${PensionCalcDtls[1]}</td>
</tr>
<tr>
<td>Amount of Commuted Value admissible</td>
<!-- <td colspan="8">${PensionCalcDtls[2]}</td> -->
<td colspan="8">${AddtnCVPDtls[0]}</td>
</tr>
<tr>
<td>Reduced pension amount</td>
<!--  <td colspan="8">${PensionCalcDtls[3]}</td>-->
<td colspan="8">${AddtnCVPDtls[1]}</td>
</tr>
<tr>
<td rowspan="5" valign="top">42</td>
<td><fmt:message key="PPROC.FAM1964"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td>i) Pay reckoning for the family pension</td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td>ii)The amount of the family pension becoming payable to the family of the employee, if death takes place after retirement</td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td>a) Before attaining the age of 65 yr. or</td>
<!-- <td colspan="8">${PensionCalcDtls[4]}</td>-->
<td colspan="8">${AddtnCVPDtls[2]}</td>
</tr>
<tr>
<td>b) After attaining the age of 65 years.</td>
<!-- <td colspan="8">${PensionCalcDtls[5]}</td>-->
<td colspan="8">${AddtnCVPDtls[3]}</td>
</tr>

<tr>
<td rowspan="3" valign="top">43</td>
<td><fmt:message key="PPROC.ELIGPEN"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${familyPensioner[0]}</td>
</tr>
<tr>
<td><fmt:message key="PPROC.DEFACTONAME"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8"></td>
</tr>
<tr>
<td style="font-size: 17px"><fmt:message key="PPROC.DEFACTODTLS"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8"></td>
</tr>



<tr>
<td valign="top">44</td>
<td><fmt:message key="PPROC.DOBFAM"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>
<tr>
<td valign="top">45</td>
<td><fmt:message key="PPROC.RECEPTFAM"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>

<tr>
<td  valign="top">46</td>
<td colspan="10">
<table>

<tr>
<td><fmt:message key="PPROC.PROVFAMPENS" bundle="${PensionLabels}"></fmt:message></td>
</tr>

<tr>
<td>Sr No.</td>
<td colspan="2">Sanctioning Authority Name</td>
<td colspan="2">Sanction Date</td>
<td colspan="2">Amount</td>
<td colspan="2">Voucher No</td>
<td colspan="2">Voucher Date</td></tr>


<c:set var="count" value="1"></c:set>
<c:forEach var="ProvPension" items="${ProvPension}">
<tr>
<td>${count}</td>
<td colspan="2">${ProvPension[0]}</td>
<td colspan="2">${ProvPension[1]}</td>
<td colspan="2">${ProvPension[2]}</td>
<td colspan="2">${ProvPension[3]}</td>
<td colspan="2">${ProvPension[4]}</td>
</tr>
<c:set var="count" value="${count+1}"></c:set>
</c:forEach>


</table>


</td>


</tr>



<tr>
<td  valign="top">47</td>

<td colspan="10">
<table>
<tr>
<td>
<fmt:message key="PPROC.PROVRETGRAT"
bundle="${PensionLabels}"></fmt:message></td></tr>
<tr>
<td>Sr No.</td>
<td colspan="2">Sanctioning Authority Name</td>
<td colspan="2">Sanction Date</td>
<td colspan="2">Amount</td>
<td colspan="2">Voucher No</td>
<td colspan="2">Voucher Date</td></tr>

<c:set var="count" value="1"></c:set>
<c:forEach var="ProvGratuity" items="${ProvGratuity}">
<tr>
<td>${count}</td>
<td colspan="2">${ProvGratuity[0]}</td>
<td colspan="2">${ProvGratuity[1]}</td>
<td colspan="2">${ProvGratuity[2]}</td>
<td colspan="2">${ProvGratuity[3]}</td>
<td colspan="2">${ProvGratuity[4]}</td>
</tr>
<c:set var="count" value="${count+1}"></c:set>
</c:forEach>

</table>


</td>


</tr>





<tr>
<td rowspan="${AdvanceSize+3}" valign="top">48</td>
<td colspan="9"><u><fmt:message key="PPROC.RECGOVDUES"
bundle="${PensionLabels}"></fmt:message></u></td>
</tr>


<tr>
<td><fmt:message key="PPROC.GOVDUESDTLS"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">Amount</td>
<td colspan="5">Major Head</td>
</tr>
<c:forEach var="AdvDtls" items="${AdvanceDtls}">
<tr>
<td>${AdvDtls[0]}</td>
<td colspan="3">${AdvDtls[1]}</td>
<td colspan="5">${AdvDtls[2]}</td>
</tr>
</c:forEach>
<%-- <tr>
<td><fmt:message key="PPROC.DUES134"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.HBABAL"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.HBAINT"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.CONVBAL"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.CONVINT"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.OVERPAY"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.INCTAX"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.LICFEEAMT"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.ANYDUES"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>
<tr>
<td><fmt:message key="PPROC.RECPF"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr> --%>
<tr>
<td><u>TOTAL</u></td>
<td colspan="3">&nbsp;</td>
<td colspan="5">&nbsp;</td>
</tr>



<tr>
<td valign="top">49</td>
<td><fmt:message key="PPROC.GRATRECDUES"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">${AddtnCVPDtls[4]}</td>
</tr>

<tr>
<td valign="top">50</td>
<td><fmt:message key="PPROC.SPECSIGN"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>
<tr><td style="border: none;"><div style="page-break-after: always"></div></td></tr>
<tr>
<td valign="top">51</td>
<td><fmt:message key="PPROC.HOA"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">2071 Pension & other retirement benefits</td>
</tr>
<tr>
<td>52</td>
<td><fmt:message key="PPROC.ENCL"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>

<tr>
<td valign="top">53</td>
<td><fmt:message key="PPROC.ANYINFO"
bundle="${PensionLabels}"></fmt:message></td>
<td colspan="8">&nbsp;</td>
</tr>

<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td style="border: none;">&nbsp;</td>
<td colspan="9" style="border: none;">It is certified that</td>
</tr>
<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td style="border: none;">&nbsp;</td>
<td colspan="9" style="border: none;"><fmt:message key="PPROC.DEPTINQUIRY"
bundle="${PensionLabels}"></fmt:message>&nbsp;</td>
</tr>
<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td style="border: none;">&nbsp;</td>
<td colspan="9" style="border: none;"><fmt:message key="PPROC.NODEPTINQUIRY"
bundle="${PensionLabels}"></fmt:message>&nbsp;${getDDODtls[6]}<fmt:message key="PPROC.ASCERTNO"
bundle="${PensionLabels}"></fmt:message>&nbsp;dt&nbsp;issued by&nbsp;</td>
</tr>
<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td style="border: none;">&nbsp;</td>
<td colspan="9" style="border: none;"><fmt:message key="PPROC.NOGOVDUES"
bundle="${PensionLabels}"></fmt:message>&nbsp;or Amount of Rs.&nbsp;<fmt:message key="PPROC.REC48"
bundle="${PensionLabels}"></fmt:message></td>
</tr>
<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td style="border: none;">&nbsp;</td>
<td colspan="9" style="border: none;">d) Shri/Smt.&nbsp;is full time&nbsp; in the schhol/College from &nbsp; to &nbsp; ,<fmt:message key="PPROC.RECGOM"
bundle="${PensionLabels}"></fmt:message></td>
</tr>
<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td style="border: none;">&nbsp;</td>
<td colspan="9" style="border: none;"><fmt:message key="PPROC.VERIFYDTLS"
bundle="${PensionLabels}"></fmt:message></td>
</tr>
<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td colspan="10" style="border: none;"><br></br></td>
</tr>
<tr>
<td colspan="10" style="border: none;" align="center">Signature:</td>
</tr>
<tr>
<td colspan="10" style="border: none;" align="center">Full Name:&nbsp;${getDDODtls[6]}</td>
</tr>
<tr>
<td colspan="10" style="border: none;" align="center">Designation of the Pension:&nbsp;${PensionerAddr[12]}</td>
</tr>
<tr>
<td colspan="10" style="border: none;" align="center"><br></br>
<br></br>

Sanctioning Authority&nbsp;${getDDODtls[19]}</td>
</tr>
<tr>
<td colspan="10" style="border: none;" align="center">/Head of the office.</td>
</tr>
</table>
<br></br>
<div style="page-break-after: always"></div>
<font size="2.5px">
<b>List of Enclosures:</b>
<br></br>
<fmt:message key="PPROC.ENCL1"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL2"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL3"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL4"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL5"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL6"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL7"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL8"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL9"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL10"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.ENCL11"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<b>NOTE:</b>
<br></br>
<fmt:message key="PPROC.NOTE1"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.NOTE2"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.NOTE3"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.NOTE4"
bundle="${PensionLabels}"></fmt:message>
<br></br>
<fmt:message key="PPROC.NOTE5"
bundle="${PensionLabels}"></fmt:message>
<br></br>
</font>
<center>
		<hdiits:button name="Print" id="Print" type="button"
			captionid="BTN.PRINT" bundle="${PensionLabels}"
			onclick="printReport();" />
	</center>
</hdiits:form>
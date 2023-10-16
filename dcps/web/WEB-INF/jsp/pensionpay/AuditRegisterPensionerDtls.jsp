<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="pnsnAuditRegisterVO" value="${resValue.PensionAuditRegisterVO}"></c:set>

<table id="tblPensionerDtls" width="100%">
<tr>
<td align="center" colspan="2">
 <b>Audit Register of Pension paid by Pay and Account Office</b>
</td>
</tr>
<tr>
</tr>
<tr>
</tr>
<tr>
</tr>
<tr>
<td width="50%">
P.P.O. No. : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.ppoNo}
</td>
<td width="50%" colspan="3" rowspan="3">
<table border="1"  id="tblAlloc">
<tr>
<td>
Basic
</td>
<td>
B 1-4-1936<br/>
Rs.
</td>
<td>
A 1-4-1936<br/>
Rs.
</td>
<td>
A 1-11-1956<br/>
Rs.
</td>
<td>
A 1-05-1960<br/>
Rs.
</td>
<td>
Zilla Parishad<br/>
Rs.
</td>
<td>
Total<br/>
Rs.
</td>
</tr>
<tr>
<td>
${pnsnAuditRegisterVO.basicPensionAmount}
</td>
<td>
${pnsnAuditRegisterVO.orgBf1436}
</td>
<td>
${pnsnAuditRegisterVO.orgAf1436}
</td>
<td>
${pnsnAuditRegisterVO.orgAf1156}
</td>
<td>
${pnsnAuditRegisterVO.orgBf1560}
</td>
<td>
${pnsnAuditRegisterVO.orgAfZp}
</td>
<td>
${pnsnAuditRegisterVO.orgBf1436 + pnsnAuditRegisterVO.orgAf1436 + pnsnAuditRegisterVO.orgAf1156 + pnsnAuditRegisterVO.orgBf1560 + pnsnAuditRegisterVO.orgAfZp}
</td>
</tr>

</table>

</td>
</tr>

<tr>
<td width="50%">
Name of Pensioner : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pensionerName}
</td>
<td width="50%">
 
</td>
</tr>
<tr>
<td width="50%">
Name of Guardian : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.guardianName}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
Pension Sanctioning Authority :  &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pnsnSanctionAuthority}
</td>
<td width="50%">
Volume :  &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.ledgerNo}&nbsp;&nbsp;&nbsp;&nbsp; Page :  &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pageNo}&nbsp;&nbsp;&nbsp;&nbsp; Library No :

</td>
</tr>
<tr>
<td width="50%">
Class of Pension : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pensionType}
</td>
<td width="50%">
Application No.  &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.applicationNo}&nbsp;&nbsp;&nbsp;&nbsp;    File No : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pnsrFileNo}

</td>
</tr>
<tr>
<td width="50%">
Series :                          &nbsp;&nbsp;&nbsp;&nbsp;Sub-Type : 
</td>
<td width="50%" >
Name of the Bank : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.bankName}

</td>
</tr>
<tr>
<td width="50%">
Designation : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.designation}
</td>
<td width="50%">
Branch : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.branchName}
</td>
</tr>
<tr>
<td width="50%">
Appointment Date :  &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${pnsnAuditRegisterVO.joiningDate}"/>&nbsp;&nbsp;&nbsp;&nbsp;  Retirement Date : &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${pnsnAuditRegisterVO.retirementDate}"/>
</td>
<td width="50%">
Bank Account No. : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.accountNo}
</td>
</tr>
<tr>
<td width="50%">
Date of Birth : &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${pnsnAuditRegisterVO.birthDate}"/>&nbsp;&nbsp;&nbsp;&nbsp; Identification Date :&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${pnsnAuditRegisterVO.identificationDate}"/>
</td>
<td width="50%" colspan="3" rowspan="3" >
<table id="tblNomineeDtls" border="1" >
<tr>
<td  align="center" colspan="3">
Nomination
<td>
</td>
</tr>
	<tr style="width: 100%" >
	<td width="20%" align="Center">
	Serial
	</td>
	<td width="50%" align="Center">
	Nominee Name
	</td>
	<td width="30%" align="Center">
	Percentage
	</td>
		
	</tr>
	<c:choose>

		<c:when test="${resValue.PensionerNomineeDtls !=null}">

			<c:forEach var="nomineeDtlsVO"
				items="${resValue.PensionerNomineeDtls}" varStatus="Counter">
				<tr>
					<td align="center">
					${Counter.index +1}
					</td>
					<td  align="left">
					${nomineeDtlsVO[1]}
					</td>
					<td align="center">
					${nomineeDtlsVO[2]}
					</td>
				</tr>
				
			</c:forEach>
		</c:when>

	</c:choose>
</table>

</td>
</tr>
<tr>
<td width="50%">
Personal Remarks :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.identityMark}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
H No. :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pnsnrAddr1} &nbsp; ${pnsnAuditRegisterVO.pnsnrAddr2}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
Street :
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
City : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pnsnrAddrTown}, &nbsp;${pnsnAuditRegisterVO.districtName},${pnsnAuditRegisterVO.stateName}&nbsp;&nbsp;&nbsp;&nbsp;   Pin :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pinCode}
</td>
<td width="50%"  colspan="3" rowspan="7">
<input type="hidden" name="hidPhotoUrl" id="hidPhotoUrl" >
<input type="hidden" name="hidSignUrl" id="hidSignUrl" >
<input type="hidden" name="attachmentName1" id="attachmentName1" >

	<table width="100%"> 	
		<tr>
		
			
			<td align="left">
				
				Photograph
				<c:choose>
						<c:when test="${resValue.PhotoId != null}" >
							<div id="prewPhoto" style="width: 180px;height: 150px;">
								<a href="#" id="Photo" name="Photo"  >	
									<img style="width: 180px;height: 150px;" id="Photo"  src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}">
								</a>
								<script type="text/javascript">
									document.getElementById("hidPhotoUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}"
								</script>
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewPhoto" style="width: 180px;height: 150px;">
							
							</div>	
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;&nbsp;
					<c:set value="Upload?attachmentNameHidden=Photo&attachmentMpgSrNo=${resValue.srNo}" var="url"></c:set>			
				
			</td>
			<td>
				Signature
						<c:choose>
						<c:when test="${resValue.SignId != null}" >
							<div id="prewSign" style="width: 180px;height: 150px;">
								<a href="#" name="Sign" id="Sign"  >	
									<img  style="width: 180px;height: 150px;" src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}">
								</a>
								<script type="text/javascript">
									document.getElementById("hidSignUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}"
								</script>
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewSign" style="width: 180px;height: 150px;">
							</div>	
						</c:otherwise>
					</c:choose>
    			
		   </td>
		</tr>
	</table>
</td>
</tr>
<tr>
<td width="50%">
Pan No : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.panNo}&nbsp;&nbsp;&nbsp;&nbsp;   Mobile No :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.mobileNo}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
Email : &nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.pnsnrEmailId}&nbsp;&nbsp;&nbsp;&nbsp;     Tel. No :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.teleNo}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
Basic Pension :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.basicPensionAmount}&nbsp;&nbsp;&nbsp;&nbsp;    Date From :&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${pnsnAuditRegisterVO.commencementDate}"/>
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
Amount of Commutation :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.cvpAmount}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
Commutation Authority :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.commutationAuthority}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
Pension after Commutation :&nbsp;&nbsp;&nbsp;&nbsp;${pnsnAuditRegisterVO.reducedPension}
</td>
<td width="50%">

</td>
</tr>
<tr>
<td width="50%">
<table id="tblProvisionalDtls" border="1" >
<tr>
<td align='center'>
Serial
</td>
<td align='center'>
GPO No.
</td>
<td align='center'>
Provisional Gratuity
</td>
</tr>
	<c:choose>

		<c:when test="${resValue.TrnProvisionalPensionDtls !=null}">

			<c:forEach var="TrnProvisionalPensionDtlsVO"
				items="${resValue.TrnProvisionalPensionDtls}" varStatus="Counter">
				
				<tr>
					<td align="center">
					${Counter.index +1}
					</td>
					<td  align="left">
					${TrnProvisionalPensionDtlsVO.provPensionAuthorityNo}
					</td>
					<td align="center">
					${TrnProvisionalPensionDtlsVO.basicPensionAmount}
					</td>
				</tr>
				
			</c:forEach>
		</c:when>

	</c:choose>
</table>
</td>
</tr>
<tr>
<td width="50%">
<br/>
Family Pension Details :-
</td>
<td width="50%">

</td>
</tr>
<br/>
<tr>
<td width="50%">
  In the event of death of Mr./Smt. &nbsp;${pnsnAuditRegisterVO.pensionerName}&nbsp; Family Pension of Rs. &nbsp;${pnsnAuditRegisterVO.fp1Amount}/-&nbsp;          <br/>
upto &nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${pnsnAuditRegisterVO.fp1Date}"/>&nbsp; and Rs.  &nbsp;${pnsnAuditRegisterVO.fp2Amount} &nbsp; with effect from &nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${pnsnAuditRegisterVO.fp2Date}"/>&nbsp; may please be paid to Shri &nbsp;${pnsnAuditRegisterVO.familyMemName}&nbsp;<br/> 
till date of his/her remarriage or death whichever is earlier on receipt of death certificate and form of <br/>
application from widower/widow.
</td>

</table>
<br/>



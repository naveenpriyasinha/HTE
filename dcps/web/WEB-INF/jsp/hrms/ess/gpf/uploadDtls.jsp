<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>



<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="actionList" value="${resValue.actionList}" > </c:set>	
<c:set var="success" value="${resValue.successList}"></c:set>
<c:set var="fail" value="${resValue.failList}"></c:set>
<c:set var="upload" value="${resValue.upload}"></c:set>
<c:set var="update" value="${resValue.updateList}"></c:set>
<c:set var="balanceList" value="${resValue.balanceList}"></c:set>
<c:set var="balanceListAnn" value="${resValue.balanceListAnn}"></c:set>
<c:set var="status" value="${resValue.status}"></c:set>

<hdiits:form name="frmBF" validate="true" method="POST"
encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs"> 
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="GPF.uploadDtls"/></a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">

<table width="100%">	

	<tr>
	<td>
		<p id="linkMonthly" style="display:none" align="right">
		<A href="hrms.htm?viewName=gpfMonthlyUpload"><fmt:message key="GPF.backToMonthly"/></A>
		</p>
		
		<p id="linkAnnual" style="display:none" align="right">
		<A href="hrms.htm?viewName=gpfAnnualUpload"><fmt:message key="GPF.backToAnnual"/></A>
		</p>
		
		<p id="unknown" style="display:none">
		<font color="RED" size="2">
		<hdiits:caption captionid="GPF.unknownError" bundle="${gpfLables}"/>
		</font> </p>
		
		<p id="file" style="display:none">
		<font color="RED" size="2">
		<hdiits:caption captionid="GPF.fileFormatError" bundle="${gpfLables}"/>
		</font> </p>
		
		<p id="selectFile" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.selFile" bundle="${gpfLables}"/></font>
		</p>
		<p id="numberFormat" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.numberFormat" bundle="${gpfLables}"/></font>
		</p>
	</td>
	</tr>	
	<tr><td>
	<c:if test="${ empty fail=='false'}">
	
	<hdiits:caption captionid="GPF.uploadFail" bundle="${gpfLables}"/>
	
	</td></tr>
	
	<c:forEach var="fail" items="${fail}">
	<tr> <td>
	<b> <font color="RED"> <c:out value="${fail}"/> </font>	</b>
	</td></tr>
	</c:forEach>
    </c:if>
	
	<tr>
		<td>
		<c:if test="${ empty success=='false'}">
		<hdiits:caption captionid="GPF.uploadSuccess" bundle="${gpfLables}"/>
		</td></tr>
	<tr>
		<td>	
		</c:if>
		</td>
	</tr>

	
	<tr>
		<td>
		<c:if test="${ empty update=='false'}">
		<c:if test="${upload=='Annual'}"> 
			<hdiits:caption captionid="GPF.uploadUpdate" bundle="${gpfLables}"/>
			</c:if>
		</c:if>
		</td>
	</tr>

</table>

<table id="monthlyDetails" width="100%" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse" style="display:none" >
		<tr  align="center"  style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;">
			<th><hdiits:caption captionid="GPF.accNo" bundle="${gpfLables}"/></th>
			<th><hdiits:caption captionid="GPF.subRate" bundle="${gpfLables}"/></th>
			<th><hdiits:caption captionid="GPF.refAmt" bundle="${gpfLables}"/></th>
			<th><hdiits:caption captionid="GPF.instNo" bundle="${gpfLables}"/></th>
			<th><hdiits:caption captionid="GPF.mnth" bundle="${gpfLables}"/></th>
											
																
		</tr>
	<c:forEach var="balanceList" items="${balanceList}">
		<tr>
			<td  align="center">${balanceList.gpfAccNo}</td>
			<td  align="center">${balanceList.subRate}</td>
			<td  align="center">${balanceList.refundMon}</td>
			<td  align="center">${balanceList.installNo}</td>
			<td  align="center">${balanceList.month}</td>
		</tr>
	</c:forEach>
</table>

<table id="annualDetails" width="100%" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse" style="display:none">
	<tr  align="center"  style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;">
		<th><hdiits:caption captionid="GPF.accNo" bundle="${gpfLables}"/></th>
		<th><hdiits:caption captionid="GPF.cloBal" bundle="${gpfLables}"/></th>
		<th><hdiits:caption captionid="GPF.totCreditAmt" bundle="${gpfLables}"/></th>
		<th><hdiits:caption captionid="GPF.totRef" bundle="${gpfLables}"/></th>
		<th><hdiits:caption captionid="GPF.withdr" bundle="${gpfLables}"/></th>
		<th><hdiits:caption captionid="GPF.advBalOut" bundle="${gpfLables}"/></th>
		<th><hdiits:caption captionid="GPF.highAmtPay" bundle="${gpfLables}"/></th>			
		<th><hdiits:caption captionid="GPF.inter" bundle="${gpfLables}"/></th>			
		<th><hdiits:caption captionid="GPF.finyr" bundle="${gpfLables}"/></th>			
															
	</tr>
	
	<c:forEach var="balanceListAnn" items="${balanceListAnn}">
	
	<tr>
		<td  align="center">${balanceListAnn.gpfAccNo}</td>
		<td  align="center">${balanceListAnn.closingBalance}</td>
		<td  align="center">${balanceListAnn.creditAmt}</td>
		<td  align="center">${balanceListAnn.refundAmt}</td>
		<td  align="center">${balanceListAnn.withdrawal}</td>
		<td  align="center">${balanceListAnn.advBalanceOutstanding}</td>
		<td  align="center">${balanceListAnn.higherPayAmt}</td>
		<td  align="center">${balanceListAnn.interest}</td>
		<td  align="center">${balanceListAnn.financialYear}</td>			
	</tr>
	
	</c:forEach>
</table>

</div>

</div>

<script>

	if("${upload}"=='Annual' && (("${status}"=='showTable')|| ("${status}"=='a')))
		{
		document.getElementById("annualDetails").style.display='block';
		}
	else if("${upload}"=='Monthly' && "${status}"=='showTable')
		{
		document.getElementById("monthlyDetails").style.display='block';
		}
</script>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<script>

function dispErr()
{

if("${status}"=='fileErr')
	{
	document.getElementById("file").style.display='block';
	}
	
else if("${status}"=='noFile')
	{
	document.getElementById("selectFile").style.display='block';
	}	
else if("${status}"=='unknown')
	{
	document.getElementById("selectFile").style.display='block';
	}	
else if("${status}"=='numberFormat' || "${status}"=="")
	{
	document.getElementById("numberFormat").style.display='block';
	//document.getElementById("uploadFail").style.display='none';
	}	
if("${upload}"=='Annual')
	{
	document.getElementById("linkAnnual").style.display='block';
	}
else
	{
	document.getElementById("linkMonthly").style.display='block';
	}
if("${status}"=='unknown')
	{
	document.getElementById("unknown").style.display='block';
	}

}

dispErr();
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
    

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/pensionpay/PensionBill.js"></script>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>




<div id="tabmenu">
     <ul id="maintab" class="shadetabs" >
	     	<li class="selected">
		    	<a href="#" rel="tcontent1" >
		  			<fmt:message key="PPMT.CVPDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	
	 		<li>
		        <a href="#" rel="tcontent2">
					<fmt:message key="PPMT.BUDGETDETAILS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	   </ul>      
</div>	 


<div class="tabcontentstyle">

	<!-- ------------------CVP Details--------------- -->
<div id="tcontent1" class="tabcontent" align="left" >
	<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg">	
		<b><fmt:message key="PPMT.CVPDTLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<table align="center" width="98%" border="0" class="Label" bordercolor="#C9DFFF" >		
		<tr class="datatableheader" height="25" >
			<td colspan="4" align="center" height="15" class="HLabel" style="border: 2px solid;" >
				 <b><fmt:message key="PPMT.PENSIONERDTLS" bundle="${pensionLabels}"></fmt:message></b> 
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtPPONo"  size="20"  name="txtPPONo" value="${resValue.ppoNo}" disabled="true"/>
			</td>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.DATE" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtDate"  size="20"  name="txtDate" value='<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.currDate}"/>' disabled="true" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.PENSIONERNAME" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtPensionerName"  size="20"  name="txtPensionerName" value="${resValue.firstName}" disabled="true"/>
			</td>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.OFFADDRESS" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left" rowspan="2">
		  		<textarea rows="" cols="" name="txtAreaOfficeAddr" disabled="true">${resValue.officeAddr}</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.DESIGNATION" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtDesignation"  size="20"  name="txtDesignation" value="${resValue.designation}" disabled="true"/>
			</td>
		</tr>
		<tr height="20" class="datatableheader">
			<td colspan="4" style="border: 2px solid;">
				<fmt:message key="PPMT.CVPDTLS" bundle="${pensionLabels}"></fmt:message>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.CVPAMOUNT" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtCommAmount"  size="20"  name="txtCommAmount" value="${resValue.cvpAmount}" style="text-align:right" disabled="true"/>
			</td>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.RECOVERYAMT" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtRecoveryAmt"  size="20"  name="txtRecoveryAmt" value="0.0" style="text-align:right" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.NETAMOUNT" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left">
		  		<input type="text" id="txtNetAmnt"  size="20"  name="txtNetAmnt" value="${resValue.cvpAmount}" style="text-align:right" disabled="true"/>
			</td>
		</tr>	
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.NETAMOUNTINWORDS" bundle="${pensionLabels}" ></fmt:message>
		    </td>
			<td width="30%" align="left">
			<label id="txtNetAmtInword"></label>
			<b><script>document.write(getAmountInWords('${resValue.cvpAmount}'));</script></b>
			</td>
			
		</tr>
		
</table>	
</fieldset>
<fieldset style="width:100%" class="tabstyle">
	<legend id="headingMsg">	
		<b><fmt:message key="PPMT.BILLREMARKDETAILS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	
 <table>
 		<tr>
 			<td width="20%" align="left" >
				<fmt:message key="PPMT.BILLREAMRKS" bundle="${pensionLabels}"></fmt:message>
		    </td>
			<td width="30%" align="left" >
		  		<textarea rows="" cols="40" name="txtBillRemarks" ></textarea>
			</td>
		</tr>
 </table>
</fieldset>
</div>
<div id="tcontent2" class="tabcontent" align="left">
	<jsp:include page="/WEB-INF/jsp/pensionpay/budgetDetails.jsp" />
</div>
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
</script>
<table align="center">

		<hdiits:button name="btnSave" type="button"
			captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveCVPBill()"/>
		<hdiits:button name="btnClose" type="button" captionid="PPMT.CLOSE"
			bundle="${pensionLabels}" />	
			
</table>
<script>
function saveCVPBill()
{
	

}
</script>

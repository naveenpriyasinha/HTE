<%try{ %>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US" var="pensionLabels" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>

<hdiits:form name="monthlyBillDtlsForm" id="PensionInwardForm"
	method="POST" action="" encType="multipart/form-data" validate="true">
<table width="100%" align="center">
  <tr>
		<td width="20%" align="right" >
			
		</td>
		<td width="30%" align="right">
		   <a href=""  ><fmt:message key="PPMT.PRINTBILL" bundle="${pensionLabels}"></fmt:message></a>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			
		</td>
		<td width="30%" align="right">
		   <fmt:message key="PPMT.BILLCONTROLNO" bundle="${pensionLabels}"></fmt:message>
		</td>
 </tr>
 <tr>
		<td width="20%" align="left" >
			 <fmt:message key="PPMT.MONTHLYPNSNBILL" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		  
		</td>
 </tr>
   
</table>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPMT.HEADCODEWISESUMMARY"
	bundle="${pensionLabels}"></fmt:message></b></legend>

<table width="100%" align="center">
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.FORMONTH" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		    March 2000
		</td>
		<td width="10%" align="left" >
			
		</td>
		<td width="30%" align="left">
		  
		</td>
		<td width="30%" align="left" >
			<fmt:message key="PPMT.TOKENNO" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="10%" align="left">
		   <input type="text" id="txtTokenNo"  size="20"  name="txtTokenNo" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.BANK" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		    Punjab National Bank
		</td>
		<td width="10%" align="left" >
			<fmt:message key="PPMT.BRANCH" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		    Industrial Eva
		</td>
</tr>
   
</table>
<table width="100%" align="center" id="ExpDetPostTbl" border="1" bordercolor="#C9DFFF" cellspacing="0">	
	<tbody>
		<tr class="datatableheader" height="20">
			<td align="center" class="HLabel"><fmt:message key="PPMT.HEADCODE" bundle="${pensionLabels}"></fmt:message></td>
			<td align="center" class="HLabel"><fmt:message key="PPMT.DESCRIPTION" bundle="${pensionLabels}"></fmt:message></td>
			<td align="center" class="HLabel"><fmt:message key="PPMT.GROSSAMOUNT" bundle="${pensionLabels}"></fmt:message></td>
			<td align="center" class="HLabel"><fmt:message key="PPMT.DEDUCTIONA" bundle="${pensionLabels}"></fmt:message></td>
			<td align="center" class="HLabel"><fmt:message key="PPMT.DEDUCTIONB" bundle="${pensionLabels}"></fmt:message></td>
			<td align="center" class="HLabel"><fmt:message key="PPMT.NETAMOUNT" bundle="${pensionLabels}"></fmt:message></td>
		</tr>
		<tr class="datatableheader" height="20">
			<td colspan="6" align="center" class="HLabel">Pension Bill Details</td>
		</tr>
		<tr class="odd">
			<td> &nbsp; </td>
			<td align="right"><b> Sub Total </b>&nbsp;&nbsp;</td>
			<td colspan="2" align="center">0</td>
			<td> &nbsp; </td>
			<td align="right">0</td>
		</tr>
		<tr class="odd">
		    <td align="left"><b><fmt:message key="PPMT.TOTALNETAMOUNT" bundle="${pensionLabels}"></fmt:message></b>&nbsp;&nbsp;</td>
			<td align="left" colspan="5"> <b> Rs.0</b> </td>
			
		</tr>
		<tr class="odd">
		    <td align="left"><b><fmt:message key="PPMT.TOTALNETAMOUNTINWORD" bundle="${pensionLabels}"></fmt:message> </b>&nbsp;&nbsp;</td>
			<td align="left" colspan="5"> <b> Rupees Zero Only</b> </td>
			
		</tr>
	</tbody>
</table>
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPMT.MONTHLYPNSNLIST"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	<table width="98%" id="tblMonthlyPensionList">
			<tr>
				<td>
					<display:table list="" size="10" id="vo" pagesize="10"
					cellpadding="5" style="width:100%" requestURI="">
					<display:column style="height:35;text-align: left;"
						class="tablecelltext" titleKey="PPMT.SRNO"
						headerClass="datatableheader" sortable="true" >
						1
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="tablecelltext" titleKey="PPMT.PPONO"
						headerClass="datatableheader" sortable="true" >
						11100111294
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="tablecelltext" titleKey="PPMT.PENSIONERNAME"
						headerClass="datatableheader" sortable="true" >
						VASANT BALWANT GHORPADE
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="tablecelltext" titleKey="PPMT.ACCOUNTNUMBER"
						headerClass="datatableheader" sortable="true" >
						500010006
						
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.BASICPENSION"
						headerClass="datatableheader" sortable="true" >
						10000.00
								
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.DP"
						headerClass="datatableheader" sortable="true" >
						0
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.ADP"
						headerClass="datatableheader" sortable="true" >
						0
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.DA"
						headerClass="datatableheader" sortable="true" >
						3452.00
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.IR"
						headerClass="datatableheader" sortable="true" >
						1200.00
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.ARREAR"
						headerClass="datatableheader" sortable="true" >
						4500.00
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.GROSSAMOUNT"
						headerClass="datatableheader" sortable="true" >
						19452.00
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.DEDUCTIONAMOUNT"
						headerClass="datatableheader" sortable="true" >
						0.00
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="tablecelltext" titleKey="PPMT.NETAMOUNT"
						headerClass="datatableheader" sortable="true" >
						19452.00
					</display:column>
					</display:table>
				</td>
			</tr>
		</table>
</fieldset>
<fieldset class="tabstyle">
<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
	<jsp:param name="attachmentName" value="scan" />
	<jsp:param name="formName" value="monthlyBillDtlsForm" />
	<jsp:param name="attachmentType" value="Document" />
	<jsp:param name="multiple" value="N" />
	<jsp:param name="removeAttachmentFromDB" value="Y" />
</jsp:include>
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPMT.BILLREMARKSDTLS"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="50%" align="left">
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.BILLREMARKS" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		    <textarea rows="" name="txtBillRemarks" cols="" style="width: 300px"></textarea>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.ATTACHMENT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		     <img border="0" src="images/doclink.gif" />
		</td>
  </tr>
</table>
</fieldset>
</hdiits:form>
<% }catch(Exception e){
e.printStackTrace();
}%>
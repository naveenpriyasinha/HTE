
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>



<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<hdiits:form name="frmDraftBills"  method="post" validate="">
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.VIEWBILLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
<div style="width:100%;overflow:auto;height:100%;" >
<display:table list="" size="10" id="vo" pagesize="10"
					cellpadding="5" requestURI="" >
					
					
	<display:column title="<input name='chkSelect'type='checkbox' onclick=''/>"
			headerClass="datatableheader" style="width:2%" >
			<input type="checkbox" align="middle" name="chkbxDraftBills"
				id="chkbxDraftBills"
				onclick="" value="" />
	</display:column>	
	
			 
	<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:center" ><a href="javascript:void(0)" onclick="javascript:window.open('ifms.htm?viewName=monthlyBillDetails');">11100111294</a></display:column>
	<display:column titleKey="PPMT.BENNAME" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:center" >Punjab National Bank
	</display:column>			
	<display:column titleKey="PPMT.BILLTYPE" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:center" >Pension Bill
	</display:column>	
	<display:column titleKey="PPMT.BILLDATE" headerClass="datatableheader" 
			style="width:10%;text-align:center" sortable="true">05/02/2010
	</display:column>			
	<display:column titleKey="PPMT.NETAMOUNT" headerClass="datatableheader"
			style="width:10%;text-align:center" sortable="true" >20000.00
	</display:column>
	
	<display:column titleKey="PPMT.BILLCATEGORY" headerClass="datatableheader"
			style="width:10%;text-align:center" sortable="true">First Pay 
	</display:column>
	<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"
			style="width:10%;text-align:center" sortable="true">Shambhurao Patil
	</display:column>
	
	
</display:table>
</div>
</fieldset>
<div align="center">
	<hdiits:button type="button" name="btnApprove" captionid="PPMT.APPROVE"  bundle="${pensionLabels}" />	
	<hdiits:button type="button" name="btnReject" captionid="PPMT.REJECT"  bundle="${pensionLabels}" />													
	<hdiits:button type="button" name="btnClose" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="javascript:pageClose()"/>
</div>	

</hdiits:form>
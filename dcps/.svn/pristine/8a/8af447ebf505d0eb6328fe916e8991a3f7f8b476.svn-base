
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>



<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<hdiits:form name="frmRejectedCase"  method="post" validate="">

<div style="width:100%;overflow:auto;height:100%;" >
<display:table list="" size="10" id="vo" pagesize="10"
					cellpadding="5" requestURI="" >
					
					
	<display:column title="<input name='chkSelect'type='checkbox' onclick=''/>"
			headerClass="datatableheader" style="width:2%" >
			<input type="checkbox" align="middle" name="chkbxRejectedCase"
				id="chkbxRejectedCase"
				onclick="" value="" />
	</display:column>			
	<display:column titleKey="PPMT.SRNO" headerClass="datatableheader"
			sortable="true" style="width:10%" >1234
			
	</display:column>		 
	<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:10%" >ABS</display:column>
	<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:10%" >sfsd
	</display:column>			
	<display:column titleKey="PPMT.OLDTREASURY" headerClass="datatableheader"
			sortable="true" style="width:10%" >sdfsd
	</display:column>				
	<display:column titleKey="PPMT.BANKCODE" headerClass="datatableheader"
			style="width:10%" sortable="true" >abc
	</display:column>	
	<display:column titleKey="PPMT.BANK" headerClass="datatableheader" 
			style="width:10%">SBI
	</display:column>
	<display:column titleKey="PPMT.BRANCH" headerClass="datatableheader"
			style="width:10%" >abcd
	</display:column>
	<display:column titleKey="PPMT.ACCOUNTNO" headerClass="datatableheader"
			style="width:10%" >abc
	</display:column>
	<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"
			style="width:10%" > <select name="cmbAuditorName" id="cmbAuditorName" onChange="">
				<option value='-1'>---Select---</option></select>
	</display:column>
	<display:column titleKey="PPMT.REMARKS" headerClass="datatableheader"
			style="width:10%" ><a href="View">View</a>
	</display:column>
	
</display:table>
</div>
<table align="center">
<tr>
		
		<td  align="center">
			<hdiits:button type="button" captionid="PPMT.MOVETO" style="width:100%" bundle="${pensionLabels}" id="btnSubmit" name="btnSubmit" onclick=""/>
		</td>
		
		<td  align="center">
			<hdiits:button type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" id="btnClear" name="btnClear" onclick="" />
		</td>
		
	</tr>
</table>

</hdiits:form>
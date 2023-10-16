<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="SearchFieldName" value="${resultValue.strSearchFieldName}"></c:set>

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request"/>
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request"/>

<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/modalDialog.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/SearchBearerBank.js"/>"></script>
		
<script language="javascript">

	var searchBearerBankArray = new Array();
	searchBearerBankArray[0]="<fmt:message bundle='${NOCAlerts}' key='CTRY.AtleastOne'/>";

</script>  

<hdiits:form name="frmSearch" validate="true" method="post">
	
	<fieldset class="tabstyle">
		<legend  id="headingMsg"><hdiits:caption captionid="NOC.BankDtls" bundle="${NOCLables}" /></legend>
			
			<display:table list="${resultValue.lstBankAndLookupDtls}" id="bankAndLookup" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1">	
				<display:setProperty name="paging.banner.placement" value="bottom"/>
					
					<display:column class="oddcentre" headerClass="datatableheader" style="text-align:center"><input type="radio" name="chkBox" id="chkBox"  value="${bankAndLookup[0].bankDtlId}°${bankAndLookup[0].hrEisBankMst.bankName}°${bankAndLookup[0].hrEisBranchMst.branchName}°${bankAndLookup[1].lookupDesc}°${bankAndLookup[0].bankAcctNo}"/></display:column>
					<display:column style="text-align: center;" class="tablecelltext" titleKey="NOC.BankName" headerClass="datatableheader" sortable="true">${bankAndLookup[0].hrEisBankMst.bankName}</display:column>
					<display:column style="text-align: center;" class="tablecelltext" titleKey="NOC.BranchName" headerClass="datatableheader" sortable="true" >${bankAndLookup[0].hrEisBranchMst.branchName}</display:column>	
					<display:column style="text-align: center;" class="tablecelltext" titleKey="NOC.AcctType" headerClass="datatableheader" sortable="true" >${bankAndLookup[1].lookupDesc}</display:column>	    
					<display:column style="text-align: center;" class="tablecelltext" titleKey="NOC.AcctNo" headerClass="datatableheader" >${bankAndLookup[0].bankAcctNo}</display:column>	        
					
				<display:footer media="html"></display:footer>		
			</display:table>
			
	</fieldset>
	
	<center>
		<hdiits:button  name="btnSelect" type="button" captionid="CTRY.Ok" bundle="${NOCLables}" onclick="selectBank()"  />
		<hdiits:button  name="btnClose" type="button" captionid="CTRY.Close" bundle="${NOCLables}" onclick="window.close();"/>
	</center>

	
</hdiits:form>
	
<script type="text/javascript">

	setWindowName(window, document.frmSearch);

</script>	
	
								
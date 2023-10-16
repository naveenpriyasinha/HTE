<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts" scope="request"/>

<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsSrka.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="OrgList" value="${resValue.OrgDsplyDtls}"></c:set>
<c:set var="counter" value="1"></c:set>


<hdiits:form name="dcpsOrgInfo" encType="multipart/form-data"
	validate="true" method="post">
<fieldset style="width:100%" class="tabstyle">
<legend><fmt:message key="CMN.ORGANIZATIONMST" bundle="${dcpsLabels}"></fmt:message></legend>
<table id="tbl1" cellpadding="4" cellspacing="4" width="100%" align="center">
<tr>
	<td width="80%" valign="top">
	<table>
	<tr>
		<td width="30%" align="left" ><b><fmt:message key="CMN.ORGANIZATIONTYPE" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtOrgType" id="txtOrgType" size="20"></input></td>
		<td width="20%" align="left" ><b><fmt:message key="CMN.ORGANIZATIONALDESC" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtOrgDesc" id="txtOrgDesc" size="20"></input></td>
	</tr>
	
	<tr>
		<td width="30%" align="left" ><b><fmt:message key="CMN.EMPLOYEEHEADACC" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtEmpHeadAcc" id="txtEmpHeadAcc" size="20"></input></td>
		<td width="20%" align="left" ><b><fmt:message key="CMN.EMPLOYEESCHEMECODE" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtEmpSchemeCode" id="txtEmpSchemeCode" size="20"></input></td>
	</tr>
	
	<tr>
		<td width="30%" align="left" ><b><fmt:message key="CMN.EMPLOYERHEADACC" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtEmplrHeadAcc" id="txtEmplrHeadAcc" size="20"></input></td>
		<td width="20%" align="left" ><b><fmt:message key="CMN.EMPLOYERSCHEMECODE" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtEmplrSchemeCode" id="txtEmplrSchemeCode" size="20"></input></td>
	</tr>
	
	<tr>
		<td width="30%" align="left" ><b><fmt:message key="CMN.DEPTEMPLOYEEHEADACC" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtDeptEmpHeadAcc" id="txtDeptEmpHeadAcc" size="20"></input></td>
		<td width="20%" align="left" ><b><fmt:message key="CMN.DEPTEMPLOYEESCHEMECODE" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtDeptEmpSchemeCode" id="txtDeptEmpSchemeCode" size="20"></input></td>
	</tr>
	
	<tr>
		<td width="30%" align="left" ><b><fmt:message key="CMN.DEPTEMPLOYERHEADACC" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtDeptEmplrHeadAcc" id="txtDeptEmplrHeadAcc" size="20"></input></td>
		<td width="20%"  align="left" ><b><fmt:message key="CMN.DEPTEMPLOYERSCHEMECODE" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td align="left"><input type="text" name="txtDeptEmplrSchemeCode" id="txtDeptEmplrSchemeCode" size="20"></input></td>
	</tr>
	</table><div>&nbsp;</div>
	<div>
	<table width="65%" align="right">
		<tr>

			<td width="10%" align="left">
	 
		<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE"  bundle="${dcpsLabels}" onclick="saveOrgInfo();" />
		
	</td>
<td width="40%" align="left">	
	
		<hdiits:button	name="btnClearOrgInfo" id="btnClearOrgInfo" type="button" captionid="BTN.CANCEL" bundle="${dcpsLabels}" onclick="clearAllValues();" />
	
	</td>
	</tr>
	</table>
	</div>
	</td>
	</tr>
</table><br></br>

<table width="98%" id="tblOrgDtls">

	<tr>
	<td>
		<display:table list="${OrgList}" size="10" id="vo" pagesize="10" cellpadding="5" style="width:100%" requestURI="">
		
				<display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.ORGEMPSRNO" headerClass="datatableheader"
				sortable="true" value="${counter}" >						
			    </display:column>
			
				<display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.ORGANIZATIONTYPE" headerClass="datatableheader"
				sortable="true" value="${vo[1]}" >						
			    </display:column>
				
				<display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.ORGANIZATIONALDESC" headerClass="datatableheader"
				sortable="true" value="${vo[2]}">		
			    </display:column>
			    
			    <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.EMPLOYEEHEADACC" headerClass="datatableheader"
				sortable="true" value="${vo[3]}">				
			    </display:column>
			    
			    <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.EMPLOYEESCHEMECODE" headerClass="datatableheader"
				sortable="true" value="${vo[4]}">				
			    </display:column>
			    
			    <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.EMPLOYERHEADACC" headerClass="datatableheader"
				sortable="true" value="${vo[5]}">				
			    </display:column>
			    
			    <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.EMPLOYERSCHEMECODE" headerClass="datatableheader"
				sortable="true" value="${vo[6]}">				
			    </display:column>
			    
			      <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.DEPTEMPLOYEEHEADACC" headerClass="datatableheader"
				sortable="true" value="${vo[7]}">				
			    </display:column>
			    
			      <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.DEPTEMPLOYEESCHEMECODE" headerClass="datatableheader"
				sortable="true" value="${vo[8]}">				
			    </display:column>
			    
			      <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.DEPTEMPLOYERHEADACC" headerClass="datatableheader"
				sortable="true" value="${vo[9]}">				
			    </display:column>
			    
			      <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.DEPTEMPLOYERSCHEMECODE" headerClass="datatableheader"
				sortable="true" value="${vo[10]}">		
				<c:set var="counter" value="${counter+1}"></c:set>	
				<input type="hidden" name="hidPkVal" id="PkVal" value="${vo[0]}"></input>
			    </display:column>
			    
			      
			</display:table>
		</td>
	</tr>
</table>
	

</fieldset><br></br>
</hdiits:form>
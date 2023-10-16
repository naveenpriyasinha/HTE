<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript">
function getRecords()
{

	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var schemeName = document.getElementById("schemeName").value;
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
			+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+schemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode ;
}
</script>

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmDCPSRecordsToBeChecked" encType="multipart/form-data"
	validate="true" method="post">
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="records" value="${resValue.RecordList}" />

<input type="hidden"  name='txtDdoCode' id="txtDdoCode" style="text-align: left" value="${resValue.DDOCODE}" />

<br/>

<fieldset  class="tabstyle">

<table id="table1" width="70%" align="center">	
	<tr>
		<td width="10%">
		</td>
		<td width="10%" align="left" ><fmt:message key="DCPS.FINYEAR" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="20%" align="left">
			<select name="cmbFinYear" id="cmbFinYear" >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="finYear" items="${resValue.lLstFinYear}" >
				<option value="${finYear.id}" ><c:out value="${finYear.desc}"></c:out></option>				         					
			</c:forEach>
			
			</select>
		</td>
		<td width="50%" align="left">
			<hdiits:button type="button" captionid="BTN.SUBMIT" bundle="${DCPSLables}" id="submit" name="submit" onclick="getRecords();"></hdiits:button>
		</td>
	</tr>
</table>
	
</fieldset></br>
<c:if test="${records!=null}">
<fieldset class="tabstyle">

	<display:table list="${records}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="5">	

		<display:setProperty name="paging.banner.placement" value="bottom" />

		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.SRNO">
			<c:out value="${vo_rowNum}"></c:out>
		</display:column>
				
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.TREASURYNAME" >    
    			
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.DCPSAMOUNT">
			
		</display:column>
	</display:table>

</fieldset>
</c:if>
</hdiits:form>
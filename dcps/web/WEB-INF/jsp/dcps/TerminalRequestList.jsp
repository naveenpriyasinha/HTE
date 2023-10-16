<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script language="JavaScript" src="script/dcps/TerminalRequest.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="TerminalRequestListForm" id="TerminalRequestListForm" encType="multipart/form-data" validate="true" method="post">

<fieldset class="tabstyle"><legend><fmt:message key="CMN.TERMINATIONREQUESTLIST" bundle="${dcpsLables}" /></legend>

					<div style="width:100%;overflow:auto;" >
					<br>	
						<display:table list="${resValue.listTerminalRequests}" size="10"  id="vo" pagesize="10" cellpadding="5" style="width:30%" requestURI="" >
							<display:setProperty name="paging.banner.placement" value="none" />
	
							<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.EMPNAME" headerClass="datatableheader" sortable="true" >
							  <a href=# onclick="popUpTerminalDetails('${vo[1]}','${resValue.hidUser}','${resValue.hidUse}');"><c:out value="${vo[0]}"/></a>
							</display:column>
						</display:table>
						
						<br>
						<br>
						<hdiits:button  name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" style="width:10%" onclick="ReturnLoginPage();"/>
					</div>
					
					
					
</fieldset>

</hdiits:form>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.pdpla.PdPlaLabels" var="paybillLables" scope="request"/>

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data" validate="true" method="post"  >
<fieldset class="tabstyle">
<table width="98%">
		  		<tr>
		  			<td>		
						<display:table list="" size="10"  id="" pagesize="<%=Constants.PDWL_PAGE_SIZE %>"cellpadding="5" style="width:100%" requestURI="" >

						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true" value="1351" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.EMPLYEENAME" headerClass="datatableheader" value="Akshita Ashok Narvekar" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.GENDER" headerClass="datatableheader" value="F" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.DOB" headerClass="datatableheader" value="" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.VERFICATIONTIME" headerClass="datatableheader" value="" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.DESIGNATION" headerClass="datatableheader" value="Clerk Cum Typist" sortable="true" ></display:column>
						</display:table>
				</td>
		  	</tr>
	</table>
	<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="100%" align="center">
			<hdiits:button name="btnView" id="btnView" type="button"  captionid="BTN.VIEW" bundle="${paybillLables}" onclick="ValidateUpdateData();"/>
			<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.APPROVE" bundle="${paybillLables}"  onclick="" />
			<hdiits:button name="btnReject" id="btnReject" type="button"  captionid="BTN.REJECT" bundle="${paybillLables}" onclick=""/>
		</td>
	</tr>	
</table>
</fieldset>
</hdiits:form>	
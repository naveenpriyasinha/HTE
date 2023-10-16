<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
    

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<hdiits:form name="frmApprovalOfForm1" encType="multipart/form-data" validate="true" method="post">
<table width="100%" align="center">
<tr>
	<td>
<fieldset class="tabstyle"><legend> <fmt:message key="CMN.APPROVALOFFORM" bundle="${dcpsLables}"></fmt:message></legend>

 
		
<table id="tblMain" align="center" cellpadding="4" cellspacing="4" width = "50%">	
<tr>
	<td colspan = "2"><b>Select the treasury for which the data is to be completed</b></td>
</tr>
	<tr>
		<td width="25%">
			<fmt:message key="CMN.TREASURYCODE" bundle="${dcpsLables}"></fmt:message>
		</td>
		<td>
		<select name="cmblstTreasuryCode" id="cmblstTreasuryCode" style="width: 50%">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>	
		</select>
		</td>
	</tr>
	<tr>
		<td width="25%">
			<fmt:message key="CMN.SELECTTREASURY" bundle="${dcpsLables}"></fmt:message>				
		</td>
		<td>
				<select name="cmblstTreasuryName" id="cmblstTreasuryName" style="width: 50%">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>	
				</select>		
		</td>
	
	</tr>
	<tr>
	<td>
	<fmt:message key="CMN.SELECTDDO" bundle="${dcpsLables}"></fmt:message>	
	
	</td>
	<td>
	<select name="cmblstSelectDdo" id="cmblstSelectDdo" style="width: 50%">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>	
		</select>
	
	</td>
</tr>

</table>	
</fieldset>
<br/><br/>
		<div align="center">
			<hdiits:button type="button" captionid="BTN.SUBMIT" bundle="${dcpsLables}" name="btnSubmit" id="btnSubmit"/>
		</div>

</td>
</tr>
</table>
</hdiits:form>
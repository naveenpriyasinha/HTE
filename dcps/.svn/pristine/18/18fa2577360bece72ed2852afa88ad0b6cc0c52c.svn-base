<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
    

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<hdiits:form name="frmInterestCalculation" encType="multipart/form-data" validate="true" method="post">
<table width="100%" align="left">
<tr>
	<td>
<fieldset class="tabstyle"><legend> <fmt:message key="CMN.CHANGEPASSWORD" bundle="${dcpsLables}"></fmt:message></legend>

<table id="tblMain" align="left" cellpadding="4" cellspacing="4" width = "50%">	

	<tr>
		<td width="25%">
			<fmt:message key="CMN.USERNAME" bundle="${dcpsLables}"></fmt:message>
		</td>
		<td>
			<input type="text" name="txtUserName" id="txtUserName" />
		</td>
	</tr>
	<tr>
		<td width="25%">
			<fmt:message key="CMN.OLDPASSWORD" bundle="${dcpsLables}"></fmt:message>				
		</td>
		<td>
			<input type="text" name="txtOldPassword" id="txtOldPassword" />
		</td>
	
	</tr>
	<tr>
		<td width="25%">
			<fmt:message key="CMN.NEWPASSWORD" bundle="${dcpsLables}"></fmt:message>				
		</td>
		<td>
			<input type="text" name="txtNewPassword" id="txtNewPassword" />
		</td>
	
	</tr>
	<tr>
		<td width="25%">
			<fmt:message key="CMN.CONFIRMPASSWORD" bundle="${dcpsLables}"></fmt:message>				
		</td>
		<td>
			<input type="text" name="txtConfirmPassword" id="txtConfirmPassword" />
		</td>
	
	</tr>
</table></fieldset><br/>
<table align = "left" width = "20%">
	<tr>
		<td>
			<hdiits:button type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" name="btnOk" id="btnOk"/>
		</td>
		<td>
			<hdiits:button type="button" captionid="BTN.CANCEL" bundle="${dcpsLables}" name="btnCancel" id="btnCancel"/>			
		</td>
		
	</tr>
	
</table>

</td>
</tr>
</table>
</hdiits:form>
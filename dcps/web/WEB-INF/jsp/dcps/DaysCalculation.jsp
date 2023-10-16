<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>


<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<hdiits:form name="frmDaysCalculation" encType="multipart/form-data" validate="true" method="post">
<table width="70%" align="center">
<tr>
	<td>
		<fieldset class="tabstyle"><legend> <fmt:message key="CMN.DAYSCALCULATION" bundle="${dcpsLables}"></fmt:message></legend>
			<table id="tblMain" align="center" cellpadding="4px" cellspacing="4px">	
				<tr>
					<td width="25%">
						<fmt:message key="CMN.FINANCIALYEAR" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td>
						<select name="cmblstFinancialYear" id="cmblstFinancialYear" style="width: 45%">
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>	
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</td>
</tr>
</table>
</hdiits:form>
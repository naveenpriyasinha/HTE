<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<script language="JavaScript" src="script/paybill/paybillvalidation.js"></script>

<fmt:setBundle basename="resources.paybill.PayBillLabels" var="paybillLables" scope="request"/>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />


<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data" validate="true" method="post"  >
	<fieldset class="tabstyle">
	<table width="70%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.DATE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><fmt:message key="CMN.TO" bundle="${dcpsLables}"></fmt:message>			
				<input type="text" name="txtToDate" id="txtToDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="compareDates(this,currDate,'Error','>');" value="" />
				<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtBirthDateOfNominee",375,570)' style="cursor: pointer;" }/>		
			</td>
			<td width="20%" align="left"><fmt:message key="CMN.FROM" bundle="${dcpsLables}"></fmt:message>			
				<input type="text" name="txtFromDate" id="txtFromDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="compareDates(this,currDate,'Error','>');" value="" />
				<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtBirthDateOfNominee",375,570)' style="cursor: pointer;" }/>		
			</td>
		</tr>
			<tr>
		<td width="10%" align="left"><fmt:message key="CMN.DCPSID" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbDCPSid" id="cmbDCPSid" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.PARENTFIELDDEPT" bundle="${dcpsLables}"></fmt:message></td>
		<td>		
			<select name="cmbParentFieldDept" id="cmbParentFieldDept" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CADRE" bundle="${dcpsLables}"></fmt:message></td>
		<td>		
			<select name="cmbCadre" id="cmbCadre" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	</table>
</fieldset>
<table width="98%">
		  		<tr>
		  			<td>		
						<display:table list="" size="10"  id="" pagesize="<%=Constants.PDWL_PAGE_SIZE %>"cellpadding="5" style="width:100%" requestURI="" >

						<display:column style="height:35;text-align: left;"  class="HLabel" titleKey="CMN.SRNO" headerClass="datatableheader" sortable="true" value="1" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.DCPSID" headerClass="datatableheader" value="1351" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.NAMEOFEMPLOYEE" headerClass="datatableheader" value="Akshita Ashok Narvekar" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.DOB" headerClass="datatableheader" value="" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.PARENTFIELDDEPT" headerClass="datatableheader" value="" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.CADRE" headerClass="datatableheader" value="" sortable="true" ></display:column>
						<display:column style="height:35;text-align: left;"  class="tablecelltext" titleKey="CMN.STATUS" headerClass="datatableheader" value="" sortable="true" ></display:column>
					</display:table>
				</td>
		  	</tr>
	</table>
</hdiits:form>
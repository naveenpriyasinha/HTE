<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/changeEmpDept.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="lLstDept" value="${resValue.listParentDept}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmChangeEmpDept" encType="multipart/form-data"
	validate="true" method="post">
	
<br/>

<fieldset class="tabstyle">
<legend> <b><fmt:message key="CMN.CHANGEEMPDPTMNT" bundle="${DCPSLables}"></fmt:message></b> </legend>
<table id="table1" width=70%" align="left">	
	<tr>
		<td width="15%" align="left" >	
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.DCPSID" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="30%" name='txtDCPSId' id="txtDCPSId" style="text-align: left" onblur="populateEmpData();"/>
		</td>
		
	</tr>
	<tr>
		<td colspan="3" align="left">
			<input type="hidden"  size="30%" name='txtEmpId' id="txtEmpId" style="text-align: left" disabled="disabled"/>
		</td>
		
	</tr>
	<tr>
		<td width="15%" align="left" >	
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.NAME" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="30%" name='txtName' id="txtName" style="text-align: left" disabled="disabled"/>
		</td>
		
	</tr>
	
	<tr>
		<td width="15%" align="left" >	
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.TREASURY" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="40%" align="left">
			<input type="text"  size="50%" name='txtTreasuryCode' id="txtTreasuryCode" style="text-align: left" disabled="disabled"/>
		</td>
		
	</tr>
	<tr>
		<td width="15%" align="left" >	
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.DDOCODE" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="30%" name='txtDDOCode' id="txtDDOCode" style="text-align: left" disabled="disabled"/>
		</td>
		
	</tr>
	
	<tr>
		<td width="15%" align="left" >	
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.CURRPARENTDEPT" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left">
			<input type="text"  size="30%" name='txtCurrParentDept' id="txtCurrParentDept" style="text-align: left" disabled="disabled"/>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" >	
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.NEWPARENTDEPT" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="30%" align="left" >
			<select name="cmbDept" id="cmbDept" style="width:50%">
			<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="deptName" items="${lLstDept}" >
					<option value="${deptName.lookupDesc}"><c:out value="${deptName.lookupDesc}"></c:out></option>					         					
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr></tr>
	<tr></tr>
</table>
<br/>
<br/>
</fieldset>
<br/>
<br/>
<div align="left" style="padding-left: 250px" >
<hdiits:button type="button" captionid="BTN.SAVE" bundle="${DCPSLables}" id="btnSave" name="btnSubmit" onclick="SaveChanges();"></hdiits:button>
<hdiits:button type="button" captionid="BTN.CLEAR" bundle="${DCPSLables}" id="btnClear" name="btnClear" onclick="ClearData();"></hdiits:button>
</div>

</hdiits:form>
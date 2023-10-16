<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<hdiits:form name="frmContactAddr" encType="multipart/form-data" validate="true" method="post">
<table id="tblInfo" width="90%" align="right" cellpadding="4px" cellspacing="4px" class="tabstyle">
<tr>
<td colspan="4">
<table align="center" width="1000px" height="10px">
<tr>
<th align="center">
Change In Employee Address
</th>
</tr>
<tr>
<td>
<fieldset style="align:center width:400px;" class="tabstyle">
<legend>Current Details	</legend>
<table align="center" width="500px"  height="10px"  border="0">

<tr>
<td align="left">
Employee Address    : 
</td>
<td>
<textarea id="txtAddress" rows="5" cols="35" ></textarea><br />
</td>
</tr>

<tr></tr>

<tr>
<td align="left">
Phone      :	
</td>
<td>
<input type="text" id="txtEmpPhone" size="30"  name="txtEmpPhone" value="" /><br />
</td>
</tr>
</table>
</fieldset>

</td>
</tr>
<tr></tr>

<tr>
<td>
<fieldset style="align:left width:400px;" class="tabstyle">
<legend>Revised Details	</legend>
<table align="center" width="500px"  height="10px"  border="0">
<tr>
<td width="120px" align="left">
Address 1    : 
</td>
<td>
<textarea id="txtAddress1" rows="5" cols="35"></textarea><br />
</td>
</tr>
<tr>
<td width="120px" align="left">
Address 2     :	 
</td>
<td>
<textarea id="txtAddress2" rows="5" cols="35"></textarea><br />
</td>
</tr>
<tr>
<td width="120px" align="left">
Phone   :	 
</td>
<td>
<input type="text" id="txtPhone" size="30"  name="txtPhone" value="" /><br />
</td>
</tr>
</table>
</fieldset>
</td>
</tr>
<tr>
</tr>
<tr>
<td>
<fieldset style="align:left width:400px;" class="tabstyle">
<legend>Order Ref. Letter Details</legend>
<table align="center" width="500px" height="10px"  border="0">
<tr>
<td align="left">
Authority  :
</td>
<td>
 <input type="text" id="txtAuthority" size="30"  name="txtAuthority" value="" /><br />
</td>
</tr>
<tr></tr>
<tr>
<td align="left">
Ref Letter No     :	 
</td>
<td>
 <input type="text" id="txtRefLetter" size="30"  name="txtRefLetter" value="" /><br />
</td>
</tr>
<tr></tr>
<tr>
<td align="left">
Date :
</td>
<td>
	<input type="text" name="txtCntctDate" id="txtCntctDate" maxlength="15" onkeypress="digitFormat(this);dateFormat(this);" /> 
	<img src='images/CalendarImages/ico-calendar.gif' width='15' onClick='window_open("txtCntctDate",375,570)' style="cursor: pointer;" />
</td>
</tr>
</table>

</fieldset>
</td>
</tr>

</table>

</td>
</tr>

<tr>
</tr>
<tr align="center">
<td align="center" width="50%">

</td>
</tr>

</table>


</hdiits:form> 
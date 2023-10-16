<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<fmt:setBundle	basename="resources.dcps.DCPSConstants" var="DCPSLables" scope="request" />
<hdiits:form name="frmGenderInfo" encType="multipart/form-data" validate="true" method="post">


<table id="tblInfo" width="90%" align="right" cellpadding="4px" cellspacing="4px"  class="tabstyle">
<tr>
<td colspan="4">
<table align="center" width="1000px" height="10px"  >
<tr>
<th align="center">
Change In Employee Gender
</th>
</tr>

<tr>
<td>
<fieldset style="text-align:left;" class="tabstyle">
<legend>Current Details</legend>
<table align="center" width="800px" height="10px" >
<tr>
<td align="left" width="42.5%">
Employee Gender   : 
</td>
 <td><input type="text" name="txtGender" id="txtGender"/>
</td>
</tr>


</table>
</fieldset>
</td>
</tr>
<tr>
<td>
<fieldset style="text-align:left;align:center" class="tabstyle">
<legend>Revised Details	</legend>
<table align="center" width="800px" height="10px" >
<tr>
<td align="left">
Select Gender    : 
</td>
<td>

				     <input type="radio" id="RadioButtonAg" name="RadioButtonAg" value="Male" />
				     Male
	
	
				     <input type="radio" id="RadioButtonAg" name="RadioButtonAg" value="Female" />
				     Female
</td>
</tr>

</table>
</fieldset>
</td>
</tr>

<tr>
<td>
<fieldset style="text-align:left;" class="tabstyle">
<legend>Order Ref. Letter Details</legend>
<table align="center" width="800px" height="10px" >
<tr>
<td  align="left" width="42.5%">
Authority / Ref Letter No     :
</td>
<td>
 <input type="text" id="txtAuthority"  name="txtAuthority" value="" /><br />
</td>
</tr>
<tr>
<td align="left">
Date :
</td>
<td>
	<input type="text" name="txtGenderDate" id="txtGenderDate" maxlength="15" onkeypress="digitFormat(this);dateFormat(this);"/> 
	<img  src='images/CalendarImages/ico-calendar.gif' width='15' onClick='window_open("txtGenderDate",375,570)' style="cursor: pointer;" /><br />
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
<td align="right" width="40%">

</td>
</tr>
</table>





</hdiits:form>
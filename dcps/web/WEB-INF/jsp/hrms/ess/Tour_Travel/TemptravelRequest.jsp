<%-- 
/**
 * FILENAME       : TravelRequest.jsp
 * VERSION        : 1.0
 * AUTHOR         : Hardik Patel
 * DATE           : 06/09/2007
 * REV. HISTORY   :
 *
 **/
 --%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ page import="java.util.*"%>
<%@page language="java"
	import="com.tcs.sgv.core.valueobject.ResultObject"%>

<%-- IMPORTED JAVA CLASSES --%>

<%@ page language="java"
	import="java.util.Collection,java.util.ArrayList,java.util.Hashtable,java.util.Calendar,java.util.Date"
	isErrorPage="false"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setHeader("Expires", "0"); //prevents caching at the proxy server
%>

<%-------------important Scripts -----------%>
<script type="text/javascript" src="common/script/tagLibValidation.js">
         </script>

<script type="text/javascript" src="common/script/commonfunctions.js">
</script>
<script type="text/javascript" src=" common/script/CalendarPopup.js">
       </script>

<script language="javascript">
	    	   
		      function loadcalendar(name,img)
		      {
		      
			   var cal1 = new CalendarPopup();
			   cal1.select(name,img,'dd/MM/yyyy'); return false;
			   
		      }
		      alert('windwos fun is called');
		      window.screen.width=='800';
		      
	  </script>


<html>

<head>
<title>Travel Request</title>

</head>
<body>
<hdiits:form name="frmleaveapply" validate="true" method="post" onload="checksize(); ">
	<br>
	<!--  <form name="SPCP" method="POST"> -->


	
	<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" ><font color="#ffffff">
			<strong><u><fmt:message key="HRMS.Tour_Details" /></u></strong> </font></td>
		</tr>
		<tr>
			<td><b><fmt:message key="HRMS.Purpose"></fmt:message></b></td>
			<td><hdiits:textarea mandatory="false" rows="3" cols="25"
				name="contactaddress" tabindex="1" id="c_strNames"
				validation="txt.isrequired" caption="contact address" /></td>
			<td><b><fmt:message key="HRMS.Ref_file_no" /></td>
			<td><hdiits:text 
				name="leavereason" tabindex="2" id="c_strNames"
				validation="txt.isrequired" caption="leavereason" /></td>
		</tr>
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" ><font color="#ffffff">
			<strong><u><fmt:message key="HRMS.Journey_Details" /></u></strong> </font></td>
		</tr>
		<tr title="firsttable">
			
			<table class="tabtable" border="1" >
				<tr>
					<td title="srno" >Sr. No </td>
					<td title="Departure" >
						<table class="tabtable" border="1">
							<tr><strong><fmt:message key="HRMS.Departure" /></strong></tr>
							<tr>
								<td><fmt:message key="HRMS.Place" /></td>
								<td><fmt:message key="HRMS.Date" /></td>
								<td><fmt:message key="HRMS.Time" /></td>
							</tr>
						</table>
					</td>
					<td title="Arrival" >
						<table class="tabtable" border="1">
							<tr align="center"><strong><fmt:message key="HRMS.Arrival" /></strong></tr>
							<tr>
								<td><fmt:message key="HRMS.Place" /></td>
								<td><fmt:message key="HRMS.Date" /></td>
								<td><fmt:message key="HRMS.Time" /></td>
							</tr>
						</table>
					</td>
					<td ><fmt:message key="HRMS.No_of_days" /></td>
					<td ><fmt:message key="HRMS.Conveyance_Mode" /></td>
					<td ><fmt:message key="HRMS.Sub_Items" /></td>
					<td  align="center"><fmt:message key="HRMS.Distance" /></td>
					<td  align="center"><fmt:message key="HRMS.Total_Cost" /></td>
					<td><fmt:message key="HRMS.Accomodation" /></td>
					<td><fmt:message key="HRMS.Purose_Of_Stay" /></td>
					<td><fmt:message key="HRMS.Remark" /></td>
					<td>
				</tr>
			</table>
				
		</tr>

	</table>
	
</hdiits:form>


</body>
</html>

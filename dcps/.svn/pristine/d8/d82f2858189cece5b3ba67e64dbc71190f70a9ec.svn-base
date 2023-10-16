
<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.dss.dss_en_US" var="dssLabels" scope="application"/>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
		<script type="text/javascript"	src="<c:url value="script/common/tabcontent.js"/>"></script>
		<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
		<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
		<script>
			function loadcalendar(name,img)
		      {
			   alert('came in load calender' );
			   var cal1 = new CalendarPopup();
			   alert('Calendar instantiated' );
			   cal1.select(name,img,'dd/MM/yyyy'); 
			   return false;
			   
		      }
		
		</script>

<script type="text/javascript">
	
   function IsEmpty(aTextField)
   {
  	 if ((aTextField.value.length==0) || (aTextField.value==null)) 
  	 {
     	 return true;
  	 }
  	 else
  	  { 
  	  	return false; 
  	  }
	}	

	
	function genrateReport()
	{
		var fromDate = document.forms[0].txtFromDate.value;
		var toDate = document.forms[0].txtToDate.value;
		var location = document.forms[0].cmbLocation.value;
		var ddo = document.forms[0].cmbDDO.value;
		var finYear = document.forms[0].cmbFinyear.value;
		
		if(finYear == 0 )
		{
			alert ('Please select finYear');
		}
		else
		{
		document.forms[0].action = 'ifms.htm?actionFlag=reportService&reportCode=160039&action=generateReport&fromDate='+fromDate+'&toDate='+toDate+ '&office='+location+'&ddo='+ddo+'&finYear='+finYear+'&dynamicReport=True';
		document.forms[0].submit();
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript">document.getElementById("content").style.backgroundColor="#EDF1F2";</script>

<form name="ddo" id="ddo" method="post">
	<table width="100%"  align="center" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" >
		
			<tr>
				 <td  colspan=10 align="center"  class="titles TableBorderBN">DDO Report</td>
			</tr>
			
			<tr>
				<td align="left">	
					<table width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" >
						<tr>			
							<td>
								<b>
									&nbsp;&nbsp;&nbsp;&nbsp;
										<font class="Label3">From Date
								</b>
						
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="text" name="txtFromDate" value="" maxlength="10" size="10"  />
												<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtFromDate",375,570)' >
					
							 	<b>
									 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<font class="Label3">To Date
								</b>
					
										<%
										String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
										 request.setAttribute("dateTime",dateTime);
										%>								
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;										
										<input type="text" name="txtToDate" value="<%=dateTime%>" maxlength="10" size="10"  />
										<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtTODate",375,570)' >
							</td>
			</tr>	
			
			
				
			<tr>
				<td><b>
				&nbsp;&nbsp;&nbsp;&nbsp;
						<font class="Label3"><fmt:message key="CMN.FIN_YEAR" bundle="${dssLabels}"></fmt:message>
						</b><font color="red">*</font>
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
					<select name="cmbFinyear" id="cmbFinyear" style="width:225px">
						<option value="0">--Select--</option>
								<c:forEach var="lfinYear" items="${resValue.finYear}">
									<option value='<c:out value="${lfinYear.finYrID}"/>'>
										<c:out value="${lfinYear.finYrDesc}"></c:out>
									</option>
								</c:forEach>
					</select>	
				</td>	
								<td colspan="2"> 	 </td>	
			</tr>
			
			
			<tr>
				<td>
					<b>
					&nbsp;&nbsp;&nbsp;&nbsp;
						<font class="Label3"><fmt:message key="CMN.TREASURY_OFFICE" bundle="${dssLabels}"></fmt:message>
						</b>
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
					<select name="cmbLocation" id="cmbLocation" style="width:225px">
						<option value="0">--Select--</option>
								<c:forEach var="lLocation" items="${resValue.location}">
									<option value='<c:out value="${lLocation.id}"/>'>
										<c:out value="${lLocation.desc}"></c:out>
									</option>
								</c:forEach>
					</select>	
				</td>	
				<td colspan="2"> 	 </td>
			</tr>
			
			
			<tr>
				<td>
					<b>
					&nbsp;&nbsp;&nbsp;&nbsp;
						<font class="Label3"><fmt:message key="CMN.DDO" bundle="${dssLabels}"></fmt:message>
					</b>
				
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="cmbDDO" id="cmbDDO" style="width:225px">
						<option value="0">--Select--</option>
								<c:forEach var="lDDO" items="${resValue.DDO}">
									<option value='<c:out value="${lDDO.id}"/>'>
										<c:out value="${lDDO.desc}"></c:out>
									</option>
								</c:forEach>
					</select>	
					</table>		
				</td>	
				
								<td colspan="2"> 	 </td>	
							
			</tr>	
			
		<table width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1" >			
		
			<tr >
	       		<td align="left"><I><font class="Label3"><b> Note:</b> Parameter marked with a <font color="red"> '*' </font> is mandatory</I></td>				
	       </tr>
			<tr align="center" rowspan=6>

	       		<td colspan=2><input type="button" value="Generate Report" name="generate_report" onclick="genrateReport();">
				<input type="reset"></td>	
	       </tr>
	 </tbale>      
	</table>
</form>
</body>
<script language="javascript">;
function emptyList()
{
}
function postMsg()
{
}
</script>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=ajDDO" 
	source="cmbLocation" 
	target="cmbDDO" 
	parameters="locId={cmbLocation}" 
	emptyFunction="emptyList"  
	postFunction="postMsg">
</ajax:select>
</html>
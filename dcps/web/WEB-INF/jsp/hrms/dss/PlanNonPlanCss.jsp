 <%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.app.diary.DiaryLables" var="diaryLables" scope="request"/>
<% System.out.println("Inside PlanNonPlan Jsp"); %>
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
			 // alert('came in load calender' );
			   var cal1 = new CalendarPopup();
			 // alert('Calendar instantiated' );
			   cal1.select(name,img,'dd/MM/yyyy'); 
			   return false;
			   
		      }
		
		</script>
		<% System.out.println("Inside generate report"); %>
		
<script type="text/javascript">

function genrateReport()
	{
		
		var finyear=document.forms[0].cmbYrOption.value;
		if(finyear==0)
		{
			alert('Please select a value from \'Financial Year\' list');
			cmbYrOption.focus();
			return false;
		}
		
	
		var budType = document.forms[0].cmbBudOption.value;
		//var budget=document.forms[0].cmbBudOption.value;
		var lFromDate = document.forms[0].txtFromDate.value;
		// alert(lFromDate);
		var lToDate = document.forms[0].txtToDate.value;
		if(lToDate<lFromDate)
		{
			alert('Please select \'From Date\' less than or equal to \'To Date\' ');
			cmbYrOption.focus();
			return false;
		}
		
		// alert(budType);
		if(budType == 0)
		{
			document.forms[0].action = 'ifms.htm?actionFlag=reportService&action=generateReport&reportCode=160003&plantype='+ budType +'&finYear='+ finyear+'&Datefrom='+ lFromDate+ ' &Dateto='+ lToDate +'&dynamicReport=True';
			//alert(document.forms[0].action);
			document.forms[0].submit();
		}
		else
		{
			<% System.out.println("------Inside else in JSP----------"); %>
			//alert(budType);
			document.forms[0].action = 'ifms.htm?actionFlag=reportService&action=generateReport&reportCode=160007&plantype='+ budType +'&finYear='+ finyear+'&Datefrom='+ lFromDate+ ' &Dateto='+ lToDate +'&dynamicReport=True';
			 // alert(document.forms[0].action);
			document.forms[0].submit();
		}
		
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert details  here</title>
</head>
<body> 

<script type="text/javascript">document.getElementById("content").style.backgroundColor="#EDF1F2";</script>

<form name="PlNpCssfrm" validate="true" method="post" >

<c:set var="resultObj" value="${result}"></c:set>
		<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
				
 <table  width="100%"  align="center"   class="TableBorderLTRBN" border="1"  cellpadding="5" bgcolor="#FFFFFF" >
			<tr>
				 <td  colspan="4" align="center"  class="titles TableBorderBN"><b>Department wise Plan/NonPlan/CSS Report</b></td>
			</tr>
			<tr> <td>
		<table  width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" >
			
			<tr>
				<td colspan="4" align="left" ><font class="Label3">Financial Year </font> <font color="red">*</font>
					
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;<select name="cmbYrOption" id="cmbYrOption" style="width:100px">
					
						<option value="0">--Select--</option>
								<c:forEach var="loptionYear" items="${resValue.FinYear}">
									<option value='<c:out value="${loptionYear.finYrID}"/>'>
										<c:out value="${loptionYear.finYrDesc}"></c:out>
									</option>
								</c:forEach>
					</select>			
				</td>		
			</tr>
		<tr>
					<td colspan="4" align="left" ><font class="Label3">From Date</font><font color="red"></font>	
						
						<%
							String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);							
						%>								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="txtFromDate" id="txtFromDate" value="" maxlength="8"  class="TextCss"  size="8" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtFromDate",375,570)>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					To Date<font color="red"></font>							
						<%
							String DateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",DateTime);							
						%>	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;							
							<input type="text" name="txtToDate" value="" maxlength="8"  class="TextCss"  size="7"  /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtToDate",375,570)>
					</td>	
			</tr>
			
			<tr><br></tr>
			
			<tr>
				<td><font class="Label3">Plan / Non Plan</font><font color="red"></font>
								
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="cmbBudOption" id="cmbBudOption" style="width:100px">
						<option value="0">--Select--</option>
								<c:forEach var="loptionBudget" items="${resValue.Budget_Type}">
									<option value='<c:out value="${loptionBudget.lookupId}"/>'>
										<c:out value="${loptionBudget.lookupName}"></c:out>
									</option>
								</c:forEach>
								<option value = '-1'> Total </option>
					</select>				
										
					</td>	
			</tr>
		</table></td>
		</tr>
		</table>
			 <table  width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="5"  >
		 	
			
			<tr>
	       		<td align="left"><I><font class="Label3"><b> Note:</b> <I>Parameter marked with a </I><font color="red"> '*' </font><I> is mandatory</I></td>
	       </tr>
	       
	       <tr align="center" rowspan=6>
				<td colspan=2><input type="button" value="Generate Report" name="generate_report" onclick="genrateReport()">
				<input type="reset"></td>	
	       </tr>
		
	</table>
		
	</form>	
		
 </body> 
</html>
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
		   alert('came in load calender' );
		   var cal1 = new CalendarPopup();
		   alert('Calendar instantiated' );
		   cal1.select(name,img,'dd/MM/yyyy'); 
		   return false;
		   
	    }

		function genrateReport()
		{
			
			var dept=document.forms[0].cmbDepartment.value;
			var planNpType = document.forms[0].cmbPlanNonPlan.value;
			var revenueCapType=document.forms[0].cmbRevenueCapital.value;
			var lFromDate = document.forms[0].txtFromDate.value;
			var lToDate = document.forms[0].txtToDate.value;			
			if(document.forms[0].radioBtn[0].checked)
			{
				var lRadioBtn=0;
				document.forms[0].action = 'ifms.htm?actionFlag=reportService&action=generateReport&reportCode=150014&department='+ dept +'&planNonPlan='+ planNpType+'&revenueCapital='+ revenueCapType + '&datefrom='+ lFromDate +'&dateto='+ lToDate + '&deptWiseTsryWise='+ lRadioBtn + '&dynamicReport=True';			
				document.forms[0].submit();
			}
			else
			{	
				var lRadioBtn=1;
				document.forms[0].action = 'ifms.htm?actionFlag=reportService&action=generateReport&reportCode=150016&department='+ dept +'&planNonPlan='+ planNpType+'&revenueCapital='+ revenueCapType + '&datefrom='+ lFromDate +'&dateto='+ lToDate + '&deptWiseTsryWise='+ lRadioBtn + '&dynamicReport=True';			
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
				
 <table width="100%"  align="center" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" > 
			<tr>
				 <td  colspan="4" align="center" class="titles TableBorderBN"><b>LC Expenditure Tracking Report (DAT)</b></td>
			</tr>			
			
			<tr> 
			<td>
			
			<table width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" bordercolor="#000000"> 
		   
		    <tr>
					<td colspan="4" align="left" >From Date						
						<%
							String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							request.setAttribute("dateTime",dateTime);							
						%>								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							<input type="text" name="txtFromDate" id="txtFromDate" value="<%=dateTime%>" maxlength="8"  class="TextCss"  size="8" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtFromDate",375,570)>
				
					
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						To Date							
						<%
							String DateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);							
						%>	
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;							
							<input type="text" name="txtToDate" value="<%=DateTime%>" maxlength="8"  class="TextCss"  size="7"  /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtToDate",375,570)>
					</td>	
			</tr>
			
			
			<tr>
				<td colspan="4" align="left" >Department 
					
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="cmbDepartment" id="cmbDepartment" style="width:100px">
					
						<option value="-1">--Select--</option>
						<option value="150001">RnB</option>
						<option value="150002">Irrigation</option>
						<option value="150003">Forest</option>
								
					</select>			
				</td>		
			
				<td>Plan / Non Plan
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="cmbPlanNonPlan" id="cmbPlanNonPlan" style="width:100px">
						<option value="-1">--Select--</option>
						<option value="0">Plan</option>		
						<option value="1">Non-Plan</option>		
					</select>				
										
				</td>	
			</tr>
			
			<tr>
				<td colspan="4" align="left" >Revenue/Capital 
					
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="cmbRevenueCapital" id="cmbRevenueCapital" style="width:100px">
					
						<option value="-1">--Select--</option>
						<option value="0">Revenue</option>
						<option value="1">Capital</option>						
								
					</select>			
				</td>
				<td>
				</td>
		   </tr>
		
			
			<tr>
			   <td>
			  	 <input type="radio" name="radioBtn"  checked> DepartmentWise</td>
			   <td>	<input type="radio" name="radioBtn"  > TreasuryWise
			   </td>
			</tr>
		    
		   </table>
		
	</td>  </tr>
	</table>
	   
	    <table  width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="5"  >
		 	<tr><td><br></td></tr>
		 	<tr align="center" rowspan=6>
					<td colspan=2><input type="button" value="Generate Report" name="generate_report" onclick="genrateReport()">
					<input type="reset"></td>	
		    </tr>	
		 </table>
		 	
	</form>	
		
 </body> 
</html>
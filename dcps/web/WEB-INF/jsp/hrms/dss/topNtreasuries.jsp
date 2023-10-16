
<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<% System.out.println("-------------------------01"); %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.app.diary.DiaryLables" var="diaryLables" scope="request"/>
<% System.out.println("-------------------------02"); %>
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
	
	function IsEmpty(aTextField) {
   if ((aTextField.value.length==0) ||
   (aTextField.value==null)) {
      return true;
   }
   else { return false; }
}	

	function genrateReport1()
	{
		
			var topN = document.forms[0].topN.value;
			var toDay = document.forms[0].txtToday.value;
			var tillDate = document.forms[0].txtTillDate.value;
			
			document.forms[0].action = 'ifms.htm?actionFlag=reportService&reportCode=160021&action=generateReport&topN='+topN+'&today='+toDay+'&tillDate='+tillDate+'&dynamicReport=True';
			
			document.forms[0].submit();
	}
	function genrateReport()
	{
		var topN;
		var option;
		
		var timeDuration;
		topN = document.forms[0].topN.value;
		option = document.forms[0].initial.value;
		timeDuration = document.forms[0].cmbdateOption.value;
		
		if(timeDuration == 0)
		{
			alert('select duration..');
		}
		
		else if(IsEmpty(document.forms[0].topN))
		{
			alert('Please enter topN');
		}
		else
		{
		
		if(option == 'Expenditure')
		{
			document.forms[0].action = 'ifms.htm?actionFlag=reportService&reportCode=160021&action=generateReport&topN='+topN+'&duration='+timeDuration+'&dynamicReport=True';
			document.forms[0].submit();
		}
		else if(option == 'Receipt')
		{
			
			document.forms[0].action = 'ifms.htm?actionFlag=reportService&reportCode=160023&action=generateReport&topN='+topN+'&duration='+timeDuration+'&dynamicReport=True';
			document.forms[0].submit();
		}
		else
		{
			
			document.forms[0].action = 'ifms.htm?actionFlag=reportService&reportCode=160025&action=generateReport&topN='+topN+'&duration='+timeDuration+'&dynamicReport=True';
			document.forms[0].submit();
		}
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript">document.getElementById("content").style.backgroundColor="#EDF1F2";</script>

<form name="topNTreasuries" id="topNTreasuries" method="post">
	<table  width="100%"  align="center"   class="TableBorderLTRBN" border="1"  cellpadding="5" bgcolor="#FFFFFF" >
		
		<% System.out.println("-----------------------------1"); %>
		<tr>
				 <td  colspan=10 align="center"  class="titles TableBorderBN">TopNTresury Report</td>
			</tr>
		 <tr>	<td>
		<table  width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="5" bgcolor="#FFFFFF" >
		
		<tr><br></tr>
			
			
			<tr>
				<td colspan="10" align="Left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="Label3">Date Option<font color="red">*</font>	
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="cmbdateOption" id="cmbdateOption" style="width:100px">
						<option value="0">--Select--</option>
								<c:forEach var="loptionDate" items="${resValue.optionDate}">
									<option value='<c:out value="${loptionDate.lookupName}"/>'>
										<c:out value="${loptionDate.lookupName}"></c:out>
									</option>
								</c:forEach>
					</select>					
				</td>		
			
			
				<td  colspan="4" align="Left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="Label3">TopN<font color="red">*</font>
				&nbsp;&nbsp;&nbsp;&nbsp;
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="topN" id="topN"></td>
			</tr>
			
			
			
			<tr>
			
				<td  colspan="10" align="Left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="Label3"> Expenditure/Receipts
				  &nbsp; 
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="initial" id="initial" style="width:100px">
						<option value="0">--Select--</option>
								<c:forEach var="l_exp_rec" items="${resValue.exp_rec}">
									<option value='<c:out value="${l_exp_rec.lookupName}"/>'>
										<c:out value="${l_exp_rec.lookupName}"></c:out>
									</option>
								</c:forEach>
					</select>	
							
				</td>		
			</tr>
			
			
			
			</table></td>
			</tr>
			
			</table>
		 <table  width="100%"  align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="5"  >
		 	
			
			<tr>
	       		<td align="left"><I><font class="Label3"><b> Note:</b> Parameter marked with a <font color="red"> '*' </font> is mandatory</I></td>
	       </tr>
	       
	       <tr align="center" rowspan=6>
				<td colspan=2><input type="button" value="Generate Report" name="generate_report" onclick="genrateReport();">
				<input type="reset"></td>	
	       </tr>
		
	</table>	
</form>
</body>
</html>
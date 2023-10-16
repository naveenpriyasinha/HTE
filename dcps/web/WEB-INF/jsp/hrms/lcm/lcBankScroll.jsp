<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.lcm.lcexp_en_US" var="lcprocLabels" scope="application"/>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>LC Account Cheque Book master </title>
		<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
		<script type="text/javascript"	src="<c:url value="script/common/tabcontent.js"/>"></script>
		<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
		<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
		<script  type="text/javascript"  src="script/common/LCCommonFunction.js"></script>
		<script>
			function loadcalendar(name,img)
		      {
			   alert('came in load calender' );
			   var cal1 = new CalendarPopup();
			   alert('Calendar instantiated' );
			   cal1.select(name,img,'dd/MM/yyyy'); 
			   return false;
			   
		      }
		      
		      function closeWindow()
		      {
		      	self.close();
				var contextPath = '<%=request.getContextPath()%>';			
				var newURL = contextPath +'/ifms.htm?viewName=homePage&theme=ifms' ;
		 	    document.forms[0].action=newURL;
		 		if(confirm('Do You Want to Close This Window ?'))
		 		{
		 		  document.forms[0].submit();
		 		} 	
		      }
		
		</script>

	</head>
		
	<link rel="stylesheet" href="/web/themes/hdiits/hdiits.css" type="text/css" />
	<link rel="stylesheet" href="/web/themes/hdiits/WebGUIStandards.css" type="text/css" />
	<body>
	
		<hdiits:form method="post" name="frmlcChequeBookMst"  validate="true">
    	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		

					<table width="80%" align="center" class="TableBorderLTRBN" border="0">
							
					<tr class="TableHeaderBG">
						<td colspan="4" align="center">
							<b>&nbsp;
								<fmt:message key="LC.INBANKSCROLL" bundle="${lcprocLabels}"></fmt:message>
							</b>
						</td>
					</tr>
					<tr><td><br></td></tr>
						<tr>
							<td align="right">
									<fmt:message key="LC.DATE" bundle="${lcprocLabels}"></fmt:message>
							</td>
							<td align="left">			
										
									<%
										String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
										 request.setAttribute("dateTime",dateTime);							
									%>								
									:&nbsp;<input type="text" name="txtScrollDate" value="<%=dateTime%>" maxlength="10"  class="TextCss"  size="10" value="" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtScrollDate",375,570)>
							</td>
						</tr>
						
						<tr>
							<td align="right">
									<fmt:message key="LC.BANK" bundle="${lcprocLabels}"></fmt:message>
								</td>
							<td align="left">
								:&nbsp;<hdiits:select name="lcBank" id="id_Bank" >
										<option value="0" size="20">--Select--</option>
										</hdiits:select>
							</td>
							</tr>
							
							<tr>
								<td align="right">
									<fmt:message key="LC.UPLOAD_BANK_SCROLL" bundle="${lcprocLabels}"></fmt:message>
								</td>
								
								<td align="left">
										:&nbsp;<hdiits:text name="txtUpload" size="20" readonly="false" ></hdiits:text>			
										<hdiits:button name="Browse" value="Browse" id="browse" type="button"/>							
							</td>
							</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td align="right">						
							<hdiits:button name="btnSave" type="button" value="Upload Bank Scroll" onclick="" />							
					</td>
					<td align="left">
					       <hdiits:button name="btnClose" type="button" value="Cancel" onclick="closeWindow()"/>
					</td>
				</tr>
				
					</table>
	</hdiits:form>
</body>
</html>
	

	


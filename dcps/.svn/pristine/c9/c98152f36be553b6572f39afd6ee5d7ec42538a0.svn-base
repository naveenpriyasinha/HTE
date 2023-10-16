<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.lcm.lcexp_en_US" var="lcexpLabels" scope="application"/>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>



<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>LC Cheque Cancellation </title>
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
		 function ChqValidation()
		 {
		 	    var chqNo = eval('document.getElementById("txtChqNo")');
		 	    var advNo = eval('document.getElementById("txtAdviceNo")');
		 	    var chqAmt = eval('document.getElementById("txtChqAmt")');
		 	    
			 	if(chqNo.value=='' || chqNo.value == 0 )
			 	{
			 		alert('Please Enter Cheque No.');
			 		chqNo.focus();
			 		return false;
			 	}
			 	if(advNo.value == '-')
			 	{
			 	  alert('Please Enter Right Cheque No.');
			 	  chqNo.focus();
			 	  return false;
			 	}
			 	if(chqAmt.value == '' || chqAmt.value == 0)
			 	{
			 	  alert('Please Click on Search Button to get Cheque Details..');
			 	  chqNo.focus();
			 	  return false;
			 	}
			 return true;
		 }
		 
		 function updateChequeDtls()
		 {
		 	
		 	if(ChqValidation())
		 	{
		 		
		 		var contextPath = '<%=request.getContextPath()%>';
				newURL = contextPath +'/ifms.htm?actionFlag=updateLcChequeDtls' ;
		 		document.forms[0].action=newURL;
		 		if(confirm('Do u Want to Cancel the Cheque..?'))
		 		{
		 		  showProgressbar();
		 		  document.forms[0].btnCancelChq.disabled=true;
		 		  document.forms[0].submit();
		 		}
		 	}
		 }
		 
		 function closeWindow()
		 {
		 	   // self.close();
				var contextPath = '<%=request.getContextPath()%>';			
				var newURL = contextPath +'/ifms.htm?viewName=homePage&theme=ifmsblue' ;
		 	    document.forms[0].action=newURL;
		 		if(confirm('Do You Want to Close This Window ?'))
		 		{
		 		  document.forms[0].submit();
		 		} 	
		 }
		 
		 function alertMsg()
		 {
		 	var advNo = eval('document.getElementById("txtAdviceNo").value');
		 	
		 	if(advNo == '-')
		 	{
		 	  alert('Please Enter Right Cheque No.');			 	  
		 	}
		 	if(advNo == '--')
		 	{
		 	  alert('This Cheque No. is Already Cancelled');			 	  
		 	}
		 	if(advNo == '---')
		 	{
		 	  alert('This Cheque No. is Already Paid By Bank , So You Can not Cancel ');			 	  
		 	}
		 	
		 }
		 
		 </script>
		 
		 <style >
		.tabstyle {
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid ;
			}
			
		legend {
			padding-left:5px;
			padding-right:5px;
			font-weight:normal; 
			font-family:verdana;
				
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid ;
			}
		
		</style>
		 
		 
	</head>
	
	<body>
		<hdiits:form name="frmLcChqCancelMst" validate="true" method="post">
		<fieldset  style = "width:965px" class="tabstyle">
	    <legend  id="headingMsg"><fmt:message key="LC.CHQ_CANCEL" bundle="${lcexpLabels}"></fmt:message></legend>
	    <p>
	    
		<table width="90%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
			
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.CHQ_NO" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input type="text" name="txtChqNo" id="txtChqNo" class="TextCss" maxlength="8"  size="15" onchange="isNumeric(this)" />
							&nbsp;*
									
				   </td>
				   <td align="left">
				   			<fmt:message key="LC.ADVICENO" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input readonly type="text" name="txtAdviceNo" id="txtAdviceNo" class="TextCss"  size="15"  />
							<input  type="hidden" name="txtDivisionId" id="txtDivisionId" maxlength="6" class="TextCss"  size="15"  />
				   </td>
			</tr> 
			<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.CHQ_AMT" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input readonly type="text" name="txtChqAmt" id="txtChqAmt" class="TextCss"  size="15"  />
							
									
				   </td>
				   <td align="left">
				   			<fmt:message key="LC.CHQ_DT" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input readonly type="text" name="txtChqDt" id="txtChqDt" class="TextCss"  size="15"  />
				   </td>
			</tr>
			<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.IN_FAVOUR" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input readonly type="text" name="txtInFavourOf" id="txtInFavourOf" class="TextCss"  size="15"  />
							
									
				   </td>
				   <td align="left">
				   			<fmt:message key="LC.DIVISION_CODE" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;
							&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input readonly type="text" name="txtDivisionCode" id="txtDivisionCode" class="TextCss"  size="15"  />
				   </td>
			</tr>
			<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.REASON" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<hdiits:textarea name="txtReason" maxlength="2000" rows="2"  cols="13"> </hdiits:textarea>
							
									
				   </td>				   
			</tr>
			<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.BALANCE_AMT" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input readonly type="text" name="txtBalanceAmt" id="txtBalanceAmt" class="TextCss"  size="15"  />
							
									
				   </td>
				   <td align="left">
				   			New &nbsp;<fmt:message key="LC.BALANCE_AMT" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<input readonly type="text" id="txtNewBalanceAmt" name="txtNewBalanceAmt"  class="TextCss"  size="15"  />
				   </td>
			</tr>
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			<tr align="center">
			
				<td colspan="6">
					
						<hdiits:button type="button" id="btnSrch" name="btnSrch" value="Search" style="width:80px"  ></hdiits:button>
						<hdiits:button type="button" name="btnCancelChq" value="Cancel Cheque" style="width:110px" onclick="updateChequeDtls()" ></hdiits:button>
						<hdiits:button type="button" name="btnClose" value="Close" style="width:80px" onclick="closeWindow()" ></hdiits:button>
					
				</td>
			</tr>
			
			</table>
		</fieldset>
	</hdiits:form>

</body>

<%  String lStrResult = "";
	if(request.getAttribute("Result") != null)
	{
		lStrResult=request.getAttribute("Result").toString();
	}
	if(lStrResult != null && lStrResult.equals("true") )
	  { 
	  %>
	     <script>  alert('Cheque Cancelled..');</script>
	 <%
	  }
	  else if(lStrResult != null && lStrResult.equals("false") )
	  {
	  %>
	     <script>  alert('Cheque Not Cancelled Due to Invalid Cheque No. !!!');</script>
	  <%
	  }
	 %>



<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getChequeCancelDtls" 
	   		action="btnSrch"  source="btnSrch" target="txtAdviceNo,txtChqAmt,txtChqDt,txtInFavourOf,txtDivisionCode,txtBalanceAmt,txtNewBalanceAmt,txtDivisionId" 
		   	parameters="txtChqNo={txtChqNo}" 
		   	postFunction="alertMsg" >
</ajax:updateField>
</html>
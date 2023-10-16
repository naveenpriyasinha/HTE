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
		<title>LC Cheque Reconciliation </title>
		<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
		<script type="text/javascript"	src="<c:url value="script/common/tabcontent.js"/>"></script>
		<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
		<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
		<script  type="text/javascript"  src="script/common/LCCommonFunction.js"></script>
		<script><!--
		
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
	 	    var chqClrDt = eval('document.getElementById("txtChqPaidDt")');
	 	    var lcChqAmt = eval('document.getElementById("txtLcChqAmt")');
	 	    var chqAmt = eval('document.getElementById("txtChqAmt")');
	 	   
		 	if(chqNo.value=='' || chqNo.value == 0 )
		 	{
		 		alert('Please Enter Cheque No.');
		 		chqNo.focus();
		 		return false;
		 	}
		 	if(chqClrDt.value=='' )
		 	{
		 		alert('Please Enter Cheque Paid Date');
		 		chqClrDt.focus();
		 		return false;
		 	}
		 			 	
			if(chqAmt.value == '' || chqAmt.value == 0)
		 	{
		 	  alert('Please Enter Cheque Amount..');
		 	  chqAmt.focus();
		 	  return false;
		 	}
		 	if(chqAmt.value != lcChqAmt.value)
		 	{
		 	  alert('Difference in Cheque Amount , Reconciliation Failed !!');
		 	  chqAmt.focus();
		 	  return false;
		 	}		 	
			return true; 
		 }
		 
		 function updateChequeDtls()
		 {
		 	
		 	if(ChqValidation())
		 	{
		 		
		 		var contextPath = '<%=request.getContextPath()%>';
				newURL = contextPath +'/ifms.htm?actionFlag=updateLcChequeReconciliation ' ;
		 		document.forms[0].action=newURL;
		 		if(confirm('Do u Want to Reconciliation the Cheque..?'))
		 		{
		 		  showProgressbar();
		 		  document.forms[0].btnSubmit.disabled=true;
		 		  document.forms[0].btnClose.disabled=true;
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
		 
		 
		 
		 function getChqDtls(obj)
		 {	 	
		 	 var chqNo = eval('document.getElementById("txtChqNo")');
		 	 if(isNumeric(chqNo))
		 	 {		 	 	
		 	 	sendRequest();
		 	 }		 	
		 }
		 
		 function chkDateFormat(dtObj)
		{
			var dtVal = dtObj.value;
			if(dtVal != '')
				{
					
					if(isValidDtChars(dtObj))
					{			
							if(eval(dtVal.length) != 10)
							{
								alert('Please Enter Date In Given Format');
								dtObj.focus();
								dtObj.select();
								return false;
							}
							if(dtVal.indexOf('/')!=2)
							{
								
								alert('Please Enter Date In Given Format');
								dtObj.focus();
								dtObj.select();
								return false;
							}
							var restDt=dtVal.substr(3,10);
						
							if(restDt.indexOf('/')!=2)
							{
								
								alert('Please Enter Date In Given Format');
								dtObj.focus();
								dtObj.select();
								return false;
							}
							return true;
					
					}
					else
					{
						alert('Please Enter Date In Given Format');
						dtObj.focus();
						dtObj.select();
						return false;
					}
				}
				
		}		
		function isValidDtChars(obj)
		{
			
			var iChars = "0123456789/";   
			var fieldVal = obj.value;
			
			for (var i = 0; i < fieldVal.length; i++) 
			{
			  if (iChars.indexOf(fieldVal.charAt(i)) == -1) 
			  { 
				obj.focus();  	   
				obj.select();
			    return false;
			  }
			  
			}   
				return true;			
		}
		
		
	//---------------- AJAX CODE FOR CHECK HEAD STRUCTURE VALIDITY---START--------------------------------
			
	function createXMLHttpRequest() 
	{ 
		var ua; 
			
		if(window.XMLHttpRequest) 
		{ 
			ua = new XMLHttpRequest(); 
		} 
		else if(window.ActiveXObject) 
		{ 
			ua = new ActiveXObject("Microsoft.XMLHTTP"); 
		} 

		return ua; 
	} 
    
 	function sendRequest() 
	{ 
		
			req = createXMLHttpRequest();
			if(req != null)
			{
				var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=getChequeReconciliationDtls";
				baseUrl += "&txtChqNo=" + document.getElementById("txtChqNo").value;		
								
				req.open("post", baseUrl, false); 
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = handleResponse; 
				req.send(baseUrl);
			}
			else
			{
				alert ("Your browser does not support AJAX!");
			} 
			
	} 
  
	function handleResponse() 
	{ 
		if(req.readyState == 4)
		{ 
			if(req.status == 200)
			{	
			
				var XMLDoc = req.responseXML.documentElement;				
				var XMLEntries = XMLDoc.getElementsByTagName('chqReconciliation');
				var chqIssueDt=XMLEntries[0].text;
				var chqAmt=XMLEntries[1].text;
								
				//alert("  "+chqIssueDt+"  "+chqAmt);
				
				document.forms[0].txtChqIssueDt.value=chqIssueDt;
				document.forms[0].txtLcChqAmt.value=chqAmt;
			 	
			 	if(chqIssueDt == '-')
			 	{
			 	  alert('Please Enter Right Cheque No..');
			 	 // chqNo.focus();		 	 
			 	}
			 	if(chqIssueDt == '--')
			 	{
			 	  alert('Please Enter Another Cheque No, Its Cancelled Cheque..');
			 	  document.forms[0].txtChqNo.focus();		 	 
			 	}
			 	if(chqIssueDt == '---')
			 	{
			 	  alert('Enter Cheque No is Already Paid/Cleared..');
			 	  document.forms[0].txtChqNo.focus();				 	 
			 	}
						
				
			}						
		}	
		return true;		
	}
	//---------------- AJAX CODE FOR CHECK HEAD STRUCTURE VALIDITY ----END-----------------------------------
		 
		 <%  
		    String lStrIssueDt = "";
			if(request.getAttribute("lStrChqClrDt") != null)
			{
				lStrIssueDt=request.getAttribute("lStrChqClrDt").toString();
			}	
		%>
		 
		 --></script>
		 
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
	
		<hdiits:form name="frmLcChqReconciliation" validate="true" method="post">
		<fieldset  style = "width:965px" class="tabstyle">
	    <legend  id="headingMsg"><fmt:message key="LC.CHQ_RECONCILIATION" bundle="${lcexpLabels}"></fmt:message></legend>
	    <p>
	    
		<table width="90%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
			
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.CHQ_NO" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input type="text" name="txtChqNo" id="txtChqNo" class="TextCss" maxlength="8"  size="15" onblur="getChqDtls(this)" />
							&nbsp;*
									
				   </td>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.CHQ_ISSUE_DT" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input readonly type="text" name="txtChqIssueDt" id="txtChqIssueDt" class="TextCss"  size="15"  />
							<input type="hidden" name="txtLcChqAmt" id="txtLcChqAmt" class="TextCss"  size="15"  />
							
				   </td>
			</tr> 
			
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			
			<tr>
				    <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC_PAID_DT" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;
							&nbsp;&nbsp;:&nbsp;&nbsp;
							<%
									 String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
									 request.setAttribute("dateTime",dateTime);							
							%>								
									
							<input type="text" name="txtChqPaidDt" value="<%=lStrIssueDt%>" maxlength="10"  class="TextCss"  size="10" onblur="chkDateFormat(this)" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtChqPaidDt",375,570)>
							 * (dd/mm/yyyy)
				   </td>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.CHQ_AMT" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input type="text" name="txtChqAmt" id="txtChqAmt" style="text-align:right" maxlength="19" class="TextCss"  size="15" onblur="isNumericWD(this)" /> *
									
				   </td>
				  
			</tr>
			
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			
			<tr align="center">
			
				<td colspan="6">					
						
						<hdiits:button type="button" name="btnSubmit" value="Submit" style="width:110px" onclick="updateChequeDtls()" ></hdiits:button>
						<hdiits:button type="button" name="btnClose" value="Close" style="width:80px" onclick="closeWindow()" ></hdiits:button>
					
				</td>
			</tr>
			
			</table>
		</fieldset>
	</hdiits:form>

</body>
 <script>

<%  String lStrResult = "";
	if(request.getAttribute("Result") != null)
	{
		lStrResult=request.getAttribute("Result").toString();
	}
	if(lStrResult != null && lStrResult.equals("true") )
	  { 
	  %>
	       alert('Cheque Reconciliation Successfully..');
	 <%
	  }
	  else if(lStrResult != null && lStrResult.equals("false") )
	  {
	  %>
	       alert('Cheque Reconciliation Failed !!!');
	  <%
	  }
%>

 </script>
 

</html>
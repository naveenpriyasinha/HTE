<%System.out.println("-----CHQ BOOK JSP 1-------------"); %>
<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.lcm.lcexp_en_US" var="lcexpLabels" scope="application"/> 
<fmt:setBundle basename="resources.lcm.LcmConstants" var="lcConstant" scope="application"/> 

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
		      
		      function saveValidity()
		      {		      	
		      	//alert('bye');
		      	var divCode=eval('document.getElementById("txtDivisionCode").value') ;			 	
			 	var chqIssueDt=eval('document.getElementById("txtIssueDate")') ;			 	
			 	var chqSrFrom=eval('document.getElementById("txtStartChkSeries")') ;
			 	var chqSrUpto=eval('document.getElementById("txtEndChkSeries")') ;
			 	
			 	if(divCode.length < 1)
			 	{
			 	   alert('Please Enter Division Code !!');
			 	   return false;
			 	}
			 	if(chqIssueDt.value.length < 1)
			 	{
			 	   alert('Please Enter Cheque Book Issue Date !!');
			 	   chqIssueDt.focus();
			 	   return false;
			 	}
			 	if(chqSrFrom.value.length < 1 || chqSrFrom.value==0)
			 	{
			 	   alert('Please Enter Cheque Series Start No. !!');
			 	   chqSrFrom.focus();
			 	   return false;
			 	}
			 	if(chqSrUpto.value.length < 1 || chqSrUpto.value==0)
			 	{
			 	   alert('Please Enter Cheque Series End No. !!');	
			 	   chqSrUpto.focus();		 	   
			 	   return false;
			 	}
			 	if(chqSrFrom.value == chqSrUpto.value)
			 	{
			 	   alert('Cheque Series Start and End No. Can\'t be Same !! ') ;
			 	   chqSrFrom.focus();
			 	   return false ;
			 	}			 	
			 	if(eval(chqSrFrom.value) > eval(chqSrUpto.value))
			 	{
			 	   alert('Cheque Series Start No. Cant be Greater than Series End No. !!');
			 	   chqSrFrom.focus();
			 	   return false;
			 	}
			 	
			 	return true;
		      }
		      
		      function saveLcChequeBook()
		      {		      	
		      	//alert('hi');
		      	if(saveValidity())
		      	{			 	
				 	var contextPath = '<%=request.getContextPath()%>';
					newURL = contextPath +'/ifms.htm?actionFlag=insertLcChequeBook' ;
			 		document.forms[0].action=newURL;
			 		if(confirm('Do Tou Want To Save Cheque Series Details'))
			 		{
			 		  showProgressbar();
			 		  document.forms[0].btnSave.disabled=true;
			 		  document.forms[0].btnClose.disabled=true;
			 		  document.forms[0].submit();
			 		} 
			 	}	
		      }
		      
		      function getDivisionDtls()
		      {
		      	//alert('hi');	
		      	var divCode=eval('document.getElementById("txtDivisionShrtNm").value') ;		
		      	
		      	if(divCode.trim() != '')
		      	{	 				 	
				 	var contextPath = '<%=request.getContextPath()%>';
					newURL = contextPath +'/ifms.htm?actionFlag=getDivisionChqDtls' ;
			 		document.forms[0].action=newURL;
			 		//alert(newURL);
			 		document.forms[0].submit();
			 	}
			 	else
			 	{
			 	  alert('Please Enter Division Code !!');
			 	}
		      }
		      
		      function closePage()
		      {		        
				//self.close();
				var contextPath = '<%=request.getContextPath()%>';			
				var newURL = contextPath +'/ifms.htm?viewName=homePage&theme=ifmsblue' ;
		 	    document.forms[0].action=newURL;
		 		if(confirm('Do You Want to Close This Window ?'))
		 		{
		 		  document.forms[0].submit();
		 		} 		
		      }
		      
		      function ChqSrAddition()
		      {
		        var addValue=49;
		        var chqSrStrt= eval('document.getElementById("txtStartChkSeries").value') ;
		        var chqSrEnd=eval(addValue)+eval(chqSrStrt);
		        //alert(chqSrEnd);
		        document.forms[0].txtEndChkSeries.value=eval(chqSrEnd);		      
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
		
		
		
		<c:set var="resultObj" value="${result}" > </c:set>	
		<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
		<c:set var="lArrChequeBook" value="${resValue.lArrChequeBook}"> </c:set>
		
	</head>		
	<link rel="stylesheet" href="/web/themes/hdiits/hdiits.css" type="text/css" />
	<link rel="stylesheet" href="/web/themes/hdiits/WebGUIStandards.css" type="text/css" />
	<body>
	
		<hdiits:form method="post" name="frmlcChequeBookMst"  validate="true">
    	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
	    <fieldset  style = "width:965px" class="tabstyle">
	    <legend  id="headingMsg"><fmt:message key="LC.INCHEQUEBOOKMAST" bundle="${lcexpLabels}"></fmt:message></legend>
	    <p>
					<table width="90%" align="center" class="TableBorderLTRBN" border="0">
					
					<tr><td><br></td></tr>
						<tr>
							<td align="left" nowrap="true">
								<fmt:message key="LC.DIVISION_CODE" bundle="${lcexpLabels}"></fmt:message>							    
							</td>
							<td  align="left">
								
								<c:set var="DivVo" value="${lArrChequeBook[0]}" > </c:set>
								:&nbsp;&nbsp;<input type="text"  name="txtDivisionShrtNm" value="${DivVo.departmentCode}" id="txtDivisionShrtNm" class="TextCss" maxlength="7" size="13" onblur="getDivisionDtls()" /> *
								<input type="hidden"  name="txtDivisionCode" value="${DivVo.divisionId}" id="txtDivisionCode" class="TextCss" maxlength="7" size="15"  />
								<input type="hidden"  name="txtLcDivAccNo" value="${DivVo.lc_order_id}" id="txtLcDivAccNo" class="TextCss"  size="15" value="100000005" />
							</td>
							<td align="left">
								<fmt:message key="LC.DIVISION_NM" bundle="${lcexpLabels}"></fmt:message>
							</td>
							<td align="left">								
						
							        <c:set var="DivVo" value="${lArrChequeBook[0]}" > </c:set> :						        
									<c:out value="${DivVo.division_name}"/>
							</td>
							</tr>
							<tr>
								<td align="left">
									<fmt:message key="LC.ISSUE_DATE" bundle="${lcexpLabels}"></fmt:message>									
								</td>
								<td align="left">			
										
										<%
											String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
											 request.setAttribute("dateTime",dateTime);							
										%>								
										:&nbsp;&nbsp;<input type="text" name="txtIssueDate" value="<%=dateTime%>" maxlength="10"  class="TextCss"  size="10" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtIssueDate",375,570)>
										 *
									</td>
							</tr>
							<tr>
								<td align="left">
									<fmt:message key="LC.STARTING_CHEQUE_SERIES" bundle="${lcexpLabels}"></fmt:message>									
								</td>
									<td align="left">
										:&nbsp;&nbsp;<hdiits:text name="txtStartChkSeries" size="13" maxlength="8" readonly="false" onblur="isNumeric(this);ChqSrAddition()" ></hdiits:text> *			
									</td>
									
									<td align="left">
									<fmt:message key="LC.ENDING_CHEQUE_SERIES" bundle="${lcexpLabels}"></fmt:message>									
								</td>
									<td align="left">
										:&nbsp;&nbsp;<hdiits:text name="txtEndChkSeries" size="13" maxlength="8" readonly="false" onblur="isNumeric(this)" ></hdiits:text> *			
									</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<table width="90%" align="center" id="ReceiptJottingTbl" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="1">
							  
								<tr class="TableHeaderBG">							
									
									<td align="center" >
										<fmt:message key="LC.ISSUE_DATE" bundle="${lcexpLabels}"></fmt:message>
									</td>
									<td align="center" >
										<fmt:message key="LC.STARTING_CHEQUE_SERIES" bundle="${lcexpLabels}"></fmt:message>
									</td>
									<td align="center" >
										<fmt:message key="LC.ENDING_CHEQUE_SERIES" bundle="${lcexpLabels}"></fmt:message>
									</td>									
								</tr>	
								
								<c:forEach var="DivVo" items="${lArrChequeBook}" >																	
									<hdiits:tr>										
										<hdiits:td align="center">
											<c:out value="${DivVo.lc_valid_from}"/>
										</hdiits:td>
										<hdiits:td align="center">
											<c:out value='${DivVo.department_name}'/>
										</hdiits:td>
										<hdiits:td align="center">
											<c:out value='${DivVo.district_name}'/>
										</hdiits:td>	
									</hdiits:tr>
								</c:forEach>
								
														
						 	  <!--<tbody>
						 	  
						 	  
							  </tbody>				
								--><!--<tr >
									<td align="left" >	
										<input readonly type="text" name="txtDivCodeTab"  class="TextCss"  size="15"  />				
									</td>
									<td align="left" >	
										<input readonly type="text" name="txtIssueDtTab"  class="TextCss"  size="15"  />				
									</td>
									<td align="left" >	
										<input readonly type="text" name="txtStrtChqSrTab"  class="TextCss"  size="15"  />				
									</td>
									<td align="left" >	
										<input readonly type="text" name="txtEndChqSrTab"  class="TextCss"  size="15"  />				
									</td>									
								</tr>								
								-->
								</table>
					
				<tr><td>&nbsp;</td></tr>
				<table width="80%" align="center" id="ReceiptJottingTb2"  border="0" cellspacing="0" cellpadding="1">
				
				<tr>
					<td></td>
					<td align="right">						
							<hdiits:button name="btnSave" type="button" value="Save" onclick="saveLcChequeBook()" style="width:80px"/>
					</td>
					<td align="left">
					      <hdiits:button name="btnClose" type="button" value="Close" onclick="closePage()" style="width:80px"/>						
					</td>
					<td></td>
				</tr>
				 </table>
			</table>
			</fieldset>
	</hdiits:form>
</body>
<%  String lStrResult = "";
	if(request.getAttribute("SeriesValidation") != null)
	{
		lStrResult=request.getAttribute("SeriesValidation").toString();
	}
	if(lStrResult != null && lStrResult.equals("true") )
	  { 
	  %>
	     <script>  alert('Cheque Book Issued for given Division..');</script>
	 <%
	  }
	  else if(lStrResult != null && lStrResult.equals("false") )
	  {
	  %>
	     <script>  alert('Invalid Cheque Series  !!!');</script>
	  <%
	  }
	 %>

<%  String lStrResultDivCOde = "";
	if(request.getAttribute("Result") != null)
	{
		lStrResultDivCOde=request.getAttribute("Result").toString();
	}
	if(lStrResultDivCOde != null && lStrResultDivCOde.equals("false") )
	  { 
	  %>
	     <script>  alert('There is No Account for this Division Code !!!');</script>
	 <%
	  }	 
	 %>

</html>
	

	


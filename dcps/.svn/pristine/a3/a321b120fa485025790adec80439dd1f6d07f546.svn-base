<%@ include file="../core/include.jsp" %>	

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%> 
	
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.tcs.sgv.common.valuebeans.CommonVO"%>
<%@page import="com.tcs.sgv.apps.common.valuebeans.ComboValuesVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
   
<fmt:setBundle basename="resources.lcm.lcexp_en_US" var="lcexpLabels" scope="application"/>


<html>
	<head>
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
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
		
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
		<script type="text/javascript"	src="<c:url value="script/common/tabcontent.js"/>"></script>
		<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
		<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
		<script  type="text/javascript"  src="script/common/tagLibValidation.js"></script>
		<script language="javascript"><!--
		
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
	
	
		function loadcalendar(name,img)
		{
			   alert('came in load calender' );
			   var cal1 = new CalendarPopup();
			   alert('Calendar instantiated' );
			   cal1.select(name,img,'dd/MM/yyyy'); 
			   return false;
			   
		 }
		 
		 function saveValidation()
		 {
		 	    var AccDate = document.getElementById("txtAccDate").value;
		 		var deptName= document.getElementById("id_dept").value;
		 		var divNm= document.getElementById("txtDivName").value;
		 		var divCode= document.getElementById("txtDivCode").value;
		 		var divName = document.getElementById("id_division").value;
		 		var underCode = document.getElementById("txtUnderCode").value;
		 		var underCodeDesc = document.getElementById("txtUnderCodeDesc").value;
		 		var mjrHd=document.getElementById("txtMjrHd").value;
		 		var bnkDrnch=document.getElementById("id_branch").value;
		 		
		 		if(AccDate.length == 0)
		 		{
		 			alert('Please Enter Account Date');
		 			return false;
		 		}
		 		if(deptName == 0)
		 		{
		 			alert('Please Select Department Name');
		 			return false;
		 		}
		 		if(divName == 0)
		 		{
		 			alert('Please Select Division Office');
		 			return false;
		 		}
		 		if(divNm.length == 0)
		 		{
		 			alert('Please Enter Division Name');
		 			return false;
		 		}
		 		if(divCode.length == 0)
		 		{
		 			alert('Please Enter Division Code');
		 			return false;
		 		}
		 		
		 		if(bnkDrnch == 0)
		 		{
		 			alert('Please Select Bank Details');
		 			return false;
		 		}
		 		if(mjrHd.length == 0)
		 		{
		 			alert('Please Enter Major Head');
		 			return false;
		 		}
		 		return true;
		 }
		 
		 function saveData()
		 {		 		
		 	 if(saveValidation())
		 	 {	
		 		sendRequest();
			    var budHdResult=document.forms[0].txtBudHdStr.value;
		 		if(budHdResult == 'true')
		 		 {
			 		var contextPath = '<%=request.getContextPath()%>';
					var newURL = contextPath +'/ifms.htm?actionFlag=saveLcAccData' ;
			 	    document.forms[0].action=newURL;
			 		if(confirm('Do You Want to Save Account Data ?'))
			 		{
			 		  document.forms[0].submit();
			 		}  
			 	}
		 	 }
		 	return false;
		 }
		
		
		
			function sendRequest() 
	{ 
		
			req = createXMLHttpRequest();
			if(req != null)
			{
				var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=verifyDistributionHeadStr";
						
				baseUrl += "&txtMjrHd=" + document.getElementById("txtMjrHd").value;	
				baseUrl += "&txtSubMjrHd=" + document.getElementById("txtSubMjrHd").value;	
				baseUrl += "&txtMinHd=" + document.getElementById("txtMinHd").value;	
				baseUrl += "&txtSubHd=" + document.getElementById("txtSubHd").value;	
				baseUrl += "&txtDtlHd=" + document.getElementById("txtDtlHd").value;	
				baseUrl += "&txtObjHd=" + document.getElementById("txtObjHd").value;	
								
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
				var XMLEntries = XMLDoc.getElementsByTagName('budHdValidation');
				var budHdVerifyResult=XMLEntries[0].text;				
				//alert("---"+budHdVerifyResult);
				//var resName=document.forms[0].txtChqValidate.value;
				//var resObj=document.getElementById(resName);
				
				if(budHdVerifyResult=='false')
				{
				   alert("Invalid Budget Head Structure!!");	
				   document.forms[0].txtBudHdStr.value="false";			  
				}  
				else if(budHdVerifyResult=='falseDemand')
				{
				   alert("Please Enter Right Demand Code");	
				   document.forms[0].txtBudHdStr.value="false";						   
				} 
				else
				{
				 document.forms[0].txtBudHdStr.value="true";
				}  				
				
			}						
		}	
		return true;		
	}
		
		
		
		
		function clearPage()
		{
			var txtAccDate = document.getElementById("txtAccDate");
			txtAccDate.value="";
			
			var id_dept = document.getElementById("id_dept");
			id_dept.value=0;
			
			
			
			var id_division = document.getElementById("id_division");
			id_division.value=0;
			
			var txtAddress1 = document.getElementById("txtDivCode");
			txtAddress1.value="";
			
			var txtAddress1 = document.getElementById("txtAddress1");
			txtAddress1.value="";
			
			var txtAddress2 = document.getElementById("txtAddress2");
			txtAddress2.value="";
			
			var txtPin = document.getElementById("txtPin");
			txtPin.value="";
			
			var txtUnderCode = document.getElementById("txtUnderCode");
			txtUnderCode.value="";
			
			var txtUnderCodeDesc = document.getElementById("txtUnderCodeDesc");
			txtUnderCodeDesc.value="";
			
			var id_bank = document.getElementById("id_bank");
			id_bank.value=0;
			
			var id_branch = document.getElementById("id_branch");
			id_branch.value=0;
			
			var id_branch = document.getElementById("txtMjrHd");
			id_branch.value='';
			var id_branch = document.getElementById("txtSubMjrHd");
			id_branch.value='';
			var id_branch = document.getElementById("txtMinHd");
			id_branch.value='';
			var id_branch = document.getElementById("txtSubHd");
			id_branch.value='';
			var id_branch = document.getElementById("txtDtlHd");
			id_branch.value='00';
			var id_branch = document.getElementById("txtObjHd");
			id_branch.value='';
		}
		








		function closeWindow()
		{
			if(confirm('Do You Want to Close This Window ?'))
	 		{
	 		  self.close();
	 		}
			
			
		}
	   
		--></script>
			
	</head>
	
	<c:set var="resultObj" value="${result}" > </c:set>	
	<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
	<c:set var="lArrDeptDtls" value="${resValue.lArrDeptDtls}"> </c:set>
	<c:set var="districtname" value="${resValue.districtname}"> </c:set>
	<c:set var="lArrBankDtls" value="${resValue.lArrBankDtls}"> </c:set>
	
	<body>
	
		<hdiits:form name="frmLcAccMst" method="post" validate="true" >
	<fieldset  style = "width:740px" class="tabstyle">
	<legend  id="headingMsg"><fmt:message key="LC.ACCMASTER" bundle="${lcexpLabels}"></fmt:message></legend>
		
		<table width="100%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
			
		
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
		        <tr>				
					<td align="left">							
							&nbsp;&nbsp;&nbsp;<fmt:message key="LC.ACCOUNTDATE" bundle="${lcexpLabels}"></fmt:message>
							
							
							<%
						     String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);
							%>								
						   &nbsp;&nbsp;&nbsp;:&nbsp;<input type="text" value="<%=dateTime%>" id="txtAccDate" name="txtAccDate"  maxlength="10"  class="TextCss"  size="12" readonly  /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtAccDate",375,570) >*
					 
					</td>
				</tr>
				<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DEPT" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<hdiits:select name="id_dept" id="id_dept"  style="width:180px">
									<option value="0">--Select--</option>
					        	<c:forEach var="cmnvo" items="${lArrDeptDtls}">
											<hdiits:option value="${cmnvo.commonId}">											
												<c:out value="${cmnvo.commonDesc}"></c:out>
											</hdiits:option>
										</c:forEach>
								</hdiits:select>*	
								
							
				   <td align="left">
				   			<fmt:message key="LC.DISTRICT" bundle="${lcexpLabels}"></fmt:message>
				   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
							
							<b><c:out value="${districtname}"/>		</b>
								  
									
				   </td>
				</tr>
				<tr colspan=2>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DIVISION_OFF" bundle="${lcexpLabels}"></fmt:message>
				   			
							&nbsp;:&nbsp;<hdiits:select name="id_division" id="id_division"  style="width:340px">
									<option value="0">--Select--</option>
								
								   </hdiits:select>*
									
				   </td>
				   
				</tr>
				<tr colspan=2>
					<td align="left">
					   &nbsp;&nbsp;&nbsp;<fmt:message key="LC.DIVISION_NM" bundle="${lcexpLabels}"></fmt:message>
					   :&nbsp;<input type="text" id="txtDivName" name="txtDivName"  class="TextCss"  size="24"  />
				   
					</td>
				</tr>
				<tr colspan=2>
					<td align="left">
					   &nbsp;&nbsp;&nbsp;<fmt:message key="LC.DIVISION_CD" bundle="${lcexpLabels}"></fmt:message>
					   &nbsp;:&nbsp;<input type="text" id="txtDivCode" name="txtDivCode"  class="TextCss"  size="24"  />
				   
					</td>
				</tr>
				
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;<fmt:message key="LC.ADDRESS1" bundle="${lcexpLabels}"></fmt:message>&nbsp;
				   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<hdiits:textarea id="txtAddress1" name="txtAddress1"   />
				   </td>
				</tr>
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;<fmt:message key="LC.ADDRESS2" bundle="${lcexpLabels}"></fmt:message>&nbsp;
				   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<hdiits:textarea id="txtAddress2" name="txtAddress2"     />
				   </td>
				</tr>
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;<fmt:message key="LC.PIN_CODE" bundle="${lcexpLabels}"></fmt:message>&nbsp;
				   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<input type="text" id="txtPin" name="txtPin" class="TextCss"  size="15"  />
				   </td>
				</tr>	
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;<fmt:message key="LC.UNDERCODE" bundle="${lcexpLabels}"></fmt:message>&nbsp;				  
				   	&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
								   <hdiits:select name="txtUnderCode" id="txtUnderCode"  style="width:340px">
									<option value="-1">--Select--</option>
								
								   </hdiits:select>
				   </td>
				   <td align="left">
				   <fmt:message key="LC.DESCRIPTION" bundle="${lcexpLabels}"></fmt:message>&nbsp;				   
				   	&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<hdiits:textarea id="txtUnderCodeDesc" name="txtUnderCodeDesc"  />
				   </td>
				</tr>
				
				<tr>
				   <td align="left">
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.BANK" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
				   			
							<hdiits:select name="id_bank" id="id_bank"  style="width:180px">
											<option value="0">--Select--</option>
					        	<c:forEach var="cmnvo" items="${lArrBankDtls}">
											<hdiits:option value="${cmnvo.commonId}">											
												<c:out value="${cmnvo.commonDesc}"></c:out>
											</hdiits:option>
										</c:forEach>
							</hdiits:select>	
								   
															
									
				   </td>
				   <td align="left">
				   <fmt:message key="LC.BRANCH" bundle="${lcexpLabels}"></fmt:message>				   
				   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
				   	<hdiits:select name="id_branch" id="id_branch"  style="width:180px">
											<option value="0">--Select--</option>
					        	
							</hdiits:select>
				   </td>
				</tr>	
		
			<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>		
		
		</table>
		</fieldset>
<br><br>
		<fieldset  style = "width:740px" class="tabstyle">
	    <legend  id="headingMsg"><fmt:message key="LC.HEAD_DTL" bundle="${lcexpLabels}"></fmt:message></legend>
	    <p>
		
		<table width="90%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
				
			   
			   <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
   
			   <tr>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.MJR_HD" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input type="text" name="txtMjrHd" id="txtMjrHd" style="text-align:right" maxlength="4" class="TextCss"  size="15" onblur="isNumeric(this)" /> *
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.SUB_MJR_HD" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;:&nbsp;
							<input type="text" name="txtSubMjrHd"  id="txtSubMjrHd" id="txtSubMjrHd" style="text-align:right" class="TextCss"  maxlength="2" size="15"  onblur="isNumeric(this)"/>
				   </td>
				</tr>
				
				<tr>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.MINOR_HD" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input type="text" name="txtMinHd" id="txtMinHd" style="text-align:right" class="TextCss" maxlength="3" size="15" onblur="isNumeric(this)" />
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.SUB_HD" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
							<input type="text" name="txtSubHd" id="txtSubHd" style="text-align:right" class="TextCss"  maxlength="2" size="15" onblur="isNumeric(this)" />
				   </td>
				</tr>
				
				<tr>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DTL_HD" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input type="text" name="txtDtlHd" id="txtDtlHd" style="text-align:right" class="TextCss" maxlength="5" value="00" size="15" onblur="isNumeric(this)" />
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.OBJ_HD" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
							<input type="text" name="txtObjHd"  id="txtObjHd" style="text-align:right" class="TextCss"  maxlength="5" size="15" onblur="isNumeric(this)" />
				   </td>
				</tr>
			<tr>
			<td colspan="4"> <br></td>
			</tr>	
		   
</table>
</fieldset>		
		 <input  type="hidden" name="txtBudHdStr" id="txtBudHdStr" class="TextCss"  size="20"  />
			<br><br>
		<hdiits:button type="button" name="btnSave" value="Save" style="width:80px" onclick="javascript:saveData();"></hdiits:button>
						<hdiits:button type="button" name="btnClear" value="Clear" style="width:80px" onclick="javascript:clearPage()" ></hdiits:button>
   						<hdiits:button type="button" name="btnCancel" value="Cancel" style="width:80px" onclick="closeWindow()"></hdiits:button>
					
	</hdiits:form>
	
</body>

<%  String lStrResult = "";
	String lStrAccNo="";
	if(request.getAttribute("Result") != null)
	{
		lStrResult=request.getAttribute("Result").toString();
	}
	if(request.getAttribute("AccNo") != null)
	{
		lStrAccNo=request.getAttribute("AccNo").toString();
	}
	if(lStrResult != null && lStrResult.equals("true") )
	  { 
	  %>
	     <script>  alert('Account has been Opened for given Division ');
	     window.opener.location.reload();
	 	 self.close();
	     </script>
	 <%
	  }
	  else if(lStrResult != null && lStrResult.equals("false") )
	  {
	  %>
	     <script>  alert('Account Already Exist for given Division ');
	 			
	     </script>
	     
	  <%
	  }
	 %>
	 
	 <ajax:select 
	  baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDivision"
	  source="id_dept"
	  target="id_division"
	  parameters="id_dept={id_dept}"  
	  />
      
      <ajax:select 
	  baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getUnderCodes"
	  source="id_division"
	  target="txtUnderCode"
	  parameters="id_dept={id_dept}"  
	  />
     
      
	  <ajax:select 
	  baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranch"
	  source="id_bank"
	  target="id_branch"
	  parameters="id_bank={id_bank}"  
	  />
	  <ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDivisionDtls" 
	   		action="id_division"  source="id_division" target="txtAddress1,txtAddress2,txtPin" 
		   	parameters="id_division={id_division}"  ></ajax:updateField>
	  
	  </html>
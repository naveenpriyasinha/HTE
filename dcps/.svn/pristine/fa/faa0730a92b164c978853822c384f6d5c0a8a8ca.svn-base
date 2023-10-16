<%System.out.println("----in jsp distribution 22-----");%>
<%@ include file="../core/include.jsp" %>

<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@page import="java.text.SimpleDateFormat,javax.servlet.http.HttpServletRequest" %>
<%@page import="java.util.Calendar"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.lcm.lcexp_en_US" var="lcexpLabels" scope="application"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>LC Distribution Master </title>
		<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
		<script type="text/javascript"	src="<c:url value="script/common/tabcontent.js"/>"></script>
		<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
		<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
		<script  type="text/javascript"  src="script/common/tagLibValidation.js"></script>
		<script  type="text/javascript"  src="script/common/LCCommonFunction.js"></script>
		
		   
	    
	    
		<script>		
		
		var RowNo=0;

		function loadcalendar(name,img)
		{
			   alert('came in load calender' );
			   var cal1 = new CalendarPopup();
			   alert('Calendar instantiated' );
			   cal1.select(name,img,'dd/MM/yyyy'); 
			   return false;
			   
		 }
		 
		  function isValidLength(obj,lng)
		 {
		        var flag=0;
				var valueOf=obj.value;
				alert(valueOf.length+"---------"+lng);
				if(valueOf.length != lng)
				{						
					alert("Must Be A "+lng+" Digit Number");
					obj.focus();					
				}				
		 }
		  
		 function saveValidation()
		 {
		 	var divCode=eval('document.getElementById("txtDivisionCode")') ;
		 	var divName=eval('document.getElementById("txtDivisionName")') ;
		 	var lcIssueDt=eval('document.getElementById("txtLcIssueDate")') ;
		 	var entryType=document.getElementById("cmbEntryType");
		 	var lcvalidFrom=eval('document.getElementById("txtLcValidFrom")') ;
		 	var lcValidUpto=eval('document.getElementById("txtLcValidUpto")') ;
		 	var inwardNo=eval('document.getElementById("txtInwardNo")') ;
		 	var inwardDt=eval('document.getElementById("txtLcInwardDt")') ;
		 	var partyRefNo=eval('document.getElementById("txtPartyRefNo")') ;
		 	var partyRefDt=eval('document.getElementById("txtPartyRefDt")') ;
		 	var grntOrdNo=eval('document.getElementById("txtGrntOrdNo")') ;
		 	var grntOrdDt=eval('document.getElementById("txtGrntOrdDt")') ;
		 	
		 	var dtVal = lcvalidFrom.value;
			var yr = dtVal.substr(6,4);
			var mnth = dtVal.substr(3,2)-1;
			var day = dtVal.substr(0,2);
	
			var enteredDate = new Date(yr,mnth,day);

			 dtVal = lcValidUpto.value;
			 yr = dtVal.substr(6,4);
			 mnth = dtVal.substr(3,2)-1;
			 day = dtVal.substr(0,2);
	
			var toDate = new Date(yr,mnth,day);

			if(toDate < enteredDate)
			{
		 		alert('LC Valid Upto Date Can Not be Less Than Lc Valid From Date');
		 		lcValidUpto.focus();
		 		lcValidUpto.select();
		 		return false;
		 	}
		 	
		 	if(divCode.value.length < 1)
		 	{
		 	   alert('Please Enter Division Code !!');
		 	   divCode.focus();
		 	   return false;
		 	}
		 	if(divName.value.length < 2 )
		 	{
		 	   alert('Please Enter Right Division Code !!');
		 	   divCode.focus();
		 	   return false;
		 	}
		 	if(lcIssueDt.value.length < 1)
		 	{
		 	   alert('Please Enter Issue Date !!');
		 	   lcIssueDt.focus();
		 	   return false;
		 	}		 	
		 	if(lcvalidFrom.value.length < 1)
		 	{
		 	   alert('Please Enter Lc Valid From Date !!');
		 	   lcvalidFrom.focus();
		 	   return false;
		 	}
		 	if(lcValidUpto.value.length < 1)
		 	{
		 	   alert('Please Enter Lc Valid Upto Date !!');
		 	   lcValidUpto.focus();
		 	   return false;
		 	}
		 	if(inwardNo.value.length < 1)
		 	{
		 	   alert('Please Enter Inward No !!');
		 	   inwardNo.focus();
		 	   return false;
		 	}
		 	if(inwardDt.value.length < 1)
		 	{
		 	   alert('Please Enter Inward Date !!');
		 	   grntOrdDt.focus();
		 	   return false;
		 	}
		 	if(partyRefNo.value.length < 1)
		 	{
		 	   alert('Please Enter Party Reference No !!');
		 	   partyRefNo.focus();
		 	   return false;
		 	}
		 	if(partyRefDt.value.length < 1)
		 	{
		 	   alert('Please Enter Party Refrence Date !!');
		 	   partyRefDt.focus();
		 	   return false;
		 	}
		 	if(grntOrdNo.value.length < 1)
		 	{
		 	   alert('Please Enter Grant Order No !!');
		 	   grntOrdNo.focus();
		 	   return false;
		 	}
		 	if(grntOrdDt.value.length < 1)
		 	{
		 	   alert('Please enter Grant Order Date !!');
		 	   grntOrdDt.focus();
		 	   return false;
		 	}
		 	if(RowNo==0)
		 	{
		 	   alert('Please Enter Atleast one Head Row');
		 	   return false;
		 	}

		 	return true;
		 	
		 }
		 
		 
		 function saveLcDistribution()
		 {
		 	if(saveValidation())
		 	{
		 		
			 	document.forms[0].totalHdRow.value=RowNo;			 	
			 	var contextPath = '<%=request.getContextPath()%>';
				newURL = contextPath +'/ifms.htm?actionFlag=insertLcDistributionMst' ;
		 		document.forms[0].action=newURL;		 		
		 		if(confirm('Do You Want To Save LC Distribution Data'))
				{		 			
		 			//disable();
			        showProgressbar();			        
		 			document.forms[0].btnSubmit.disabled=true;
		 			document.forms[0].btnClose.disabled=true;
		 			document.forms[0].submit();
		 		}
	 		}
		 	
		 }	 
		 
		function addHdRowValidation()
		{
		  var budgetType=eval('document.getElementById("cmbBudgetType")');
		  var demandNo=eval('document.getElementById("txtDemandNo")');
		  var mjrHdCode=eval('document.getElementById("txtMjrHd")') ;
		 
		  
		    if(budgetType.value == -1)
		 	{
		 	   alert('Please Select Budget Type !!');	
		 	   budgetType.focus();	 	  
		 	   return false;
		 	}
		 	if(demandNo.value.length < 1)
		 	{
		 	   alert('Please Enter Demand No !!');	
		 	   demandNo.focus();	 	  
		 	   return false;
		 	}
		    if(mjrHdCode.value.length < 1)
		 	{
		 	   alert('Please Enter Major Head No !!');	
		 	   mjrHdCode.focus();	 	  
		 	   return false;
		 	}		 	
		  
		  return true;
		} 
		 
		 function checkLcAmount()
		 {
		    var hdAmt=eval('document.getElementById("txtHeadAmt")') ;
		 	  if(hdAmt.value.length < 1 || hdAmt.value==0)
			 	{
			 	   alert('Please Enter Amount (in Rs.)');
			 	   hdAmt.focus();
			 	   return false;					 	  
			 	}
		   return true;
		 }
		function addHeadRow()
		{
		  // Code for add dynamic row in budget head table
		  //==========================================================
		  
			  if(addHdRowValidation())
			  {
				   sendRequest();
				   var budHdResult=document.forms[0].txtBudHdStr.value;
				   // alert(budHdResult);
				 if(budHdResult == 'true')
		 		 {
				 	if(checkLcAmount())
				 	{ 
				 
					  RowNo++;  
					  var tbody = document.getElementById('ReceiptJottingTbl').getElementsByTagName('tbody')[0]; 
					  var row = document.createElement('TR'); 
					
					  var cell1 = document.createElement('TD'); 
					  var cell2 = document.createElement('TD'); 
					  var cell3 = document.createElement('TD'); 
					  var cell4 = document.createElement('TD'); 
					  var cell5 = document.createElement('TD'); 
					  var cell6 = document.createElement('TD'); 
					  var cell7 = document.createElement('TD'); 
					  var cell8 = document.createElement('TD'); 
					  var cell9 = document.createElement('TD'); 
					  var cell10 = document.createElement('TD'); 
					  var cell11= document.createElement('TD');
					  
					  var temp = "chkDt"+RowNo;
					 
					  cell1.innerHTML = "<input readonly type=\"checkbox\" name=\"chk"+RowNo+"\" value=RowNo\">"; 
					  cell2.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"budgetType"+RowNo+"\" >"; 
					  cell3.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"demandNo"+RowNo+"\" >"; 
					  cell4.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"mjrhd"+RowNo+"\"  align='absmiddle' >"; 
					  cell5.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"submjrhd"+RowNo+"\" >"; 
					  cell6.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"minhd"+RowNo+"\" >"; 
					  cell7.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"subhd"+RowNo+"\" >"; 
					  cell8.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"dtlHd"+RowNo+"\" >"; 
					  cell9.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"objHd"+RowNo+"\" >"; 
					  cell10.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:70\" name=\"schemeNo"+RowNo+"\" >"; 
					  cell11.innerHTML = "<input readonly type=\"text\" style=\"text-align:right\" style=\"width:115\" name=\"hdamt"+RowNo+"\" >"; 
					
					  row.appendChild(cell1); 
					  row.appendChild(cell2); 
					  row.appendChild(cell3); 
					  row.appendChild(cell4); 
					  row.appendChild(cell5); 
					  row.appendChild(cell6); 
					  row.appendChild(cell7); 
					  row.appendChild(cell8); 
					  row.appendChild(cell9); 
					  row.appendChild(cell10); 
					  row.appendChild(cell11); 
					  
					  tbody.appendChild(row); 
					  //======================================================================
					 
					  // code for copy data from textbox to table
					  var budType = eval('document.forms[0].budgetType'+RowNo);		  
					  budType.value=eval('document.forms[0].cmbBudgetType.value');
					  var demandNo = eval('document.forms[0].demandNo'+RowNo);		  
					  demandNo.value=eval('document.forms[0].txtDemandNo.value');
					  
					  var mjrHd = eval('document.forms[0].mjrhd'+RowNo);		  
					  mjrHd.value=eval('document.forms[0].txtMjrHd.value');
					  var subMjrHd = eval('document.forms[0].submjrhd'+RowNo);		  
					  subMjrHd.value=eval('document.forms[0].txtSubMjrHd.value');
					  var minHd = eval('document.forms[0].minhd'+RowNo);		  
					  minHd.value=eval('document.forms[0].txtMinHd.value');
					  var subHd = eval('document.forms[0].subhd'+RowNo);		  
					  subHd.value=eval('document.forms[0].txtSubHd.value');
					  
					   var dtlHd = eval('document.forms[0].dtlHd'+RowNo);		  
					  dtlHd.value=eval('document.forms[0].txtDtlHd.value');
					   var objHd = eval('document.forms[0].objHd'+RowNo);		  
					  objHd.value=eval('document.forms[0].txtObjHd.value');
					   var schemeNo = eval('document.forms[0].schemeNo'+RowNo);		  
					  schemeNo.value=eval('document.forms[0].txtSchemeNo.value');
					  
					  var hdAmt = eval('document.forms[0].hdamt'+RowNo);		  
					  hdAmt.value=eval('document.forms[0].txtHeadAmt.value');
					  
					  //code for showing total amt in lc amt field
					  
					  var lcAmt = eval(document.forms[0].txtLcAmt.value);
					 	 
					  document.forms[0].txtLcAmt.value=lcAmt+eval(hdAmt.value);		 
					  var progAmt = eval(document.forms[0].txtProgressiveAmt.value);
					  	
					  var aa = eval(document.getElementById("cmbEntryType").value);
					  if(aa==150006)	
					  {  		    
					     document.forms[0].txtProgressiveAmt.value=eval(progAmt)+eval(hdAmt.value);
					  }
					  else if(aa==150007)
					  {	 
					  	document.forms[0].txtProgressiveAmt.value=eval(progAmt)-eval(hdAmt.value);
					  }
					  
					  document.forms[0].txtHdTotal.value=eval(document.forms[0].txtLcAmt.value);
					  
					   //code for clearing the head field
					    document.forms[0].cmbBudgetType.value="-1";
						document.forms[0].txtDemandNo.value="";
						document.forms[0].txtMjrHd.value="";
						document.forms[0].txtSubMjrHd.value="";
						document.forms[0].txtMinHd.value="";
						document.forms[0].txtSubHd.value="";
						document.forms[0].txtDtlHd.value="00";
						document.forms[0].txtObjHd.value="";
						document.forms[0].txtSchemeNo.value="";
						document.forms[0].txtHeadAmt.value=0;
					}
				}
			}			
		}
		
		function clearHeadField()
		{
		    document.forms[0].cmbBudgetType.value="-1";
		    document.forms[0].txtDemandNo.value="";
		    document.forms[0].txtMjrHd.value="";
			document.forms[0].txtSubMjrHd.value="";
			document.forms[0].txtMinHd.value="";
			document.forms[0].txtSubHd.value="";
			document.forms[0].txtDtlHd.value="00";
			document.forms[0].txtObjHd.value="";
			document.forms[0].txtSchemeNo.value="";
			document.forms[0].txtHeadAmt.value=0;
		}
		
		//code for delete any selected row from head table.
		function deleteHeadRow()
		{
		    var j=1;
		    var count=0;
		    var RowNotemp=RowNo;
		     
		    for( i=1;i<=RowNotemp;i++,j++)
		    {        
		       var temp = eval('document.forms[0].chk'+j);
		       if(temp==null || temp==' ')
		            count++;
		       if(temp)
		       {
		           if(temp.checked)
		           {
		              		              
		              //code for showing total amt in lc amt field
		              var tmp=i-count;
					  var lcAmt = eval(document.forms[0].txtLcAmt.value);
					  //alert(eval('document.forms[0].hdamt'+tmp+'.value'));		 
					  document.forms[0].txtLcAmt.value=eval(lcAmt)-eval('document.forms[0].hdamt'+tmp+'.value');
					  var progAmt = eval(document.forms[0].txtProgressiveAmt.value);					  
					  document.forms[0].txtProgressiveAmt.value=eval(progAmt)-eval('document.forms[0].hdamt'+tmp+'.value');
					  
					  document.forms[0].txtHdTotal.value=eval(document.forms[0].txtLcAmt.value);
		              
		              ReceiptJottingTbl.deleteRow(i-count);		             
		              RowNotemp=RowNotemp-1;
		              i=i-1; 
		           }
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
		
		function chkDateFormat(dtObj)
		{
			var dtVal = dtObj.value;
			if(dtVal != '')
			{
				
				if(isValidDtChars(dtObj))
				{			
						if(eval(dtVal.length) != 10)
						{
							alert('Please Enter Date In '+'DD/MM/YYYY'+' Format');
							dtObj.focus();
							dtObj.select();
							return false;
						}
						if(dtVal.indexOf('/')!=2)
						{
							
							alert('Please Enter Date In DD/MM/YYYY Format');
							dtObj.focus();
							dtObj.select();
							return false;
						}
						var restDt=dtVal.substr(3,10);
					
						if(restDt.indexOf('/')!=2)
						{
							
							alert('Please Enter Date In DD/MM/YYYY Format');
							dtObj.focus();
							dtObj.select();
							return false;
						}
						return true;
				
				}
				else
				{
					alert('Please Enter Date In DD/MM/YYYY Format');
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
	
	 function invalidDivision()
	{
	   var divNm = document.getElementById("txtDivisionName").value;			  
	   if(divNm == '-')
	       alert('There is no Account for this Division Code ..');	
	   if(divNm == '--')
	       alert('Please Enter Division Code ..');	  
	}
	
	function invalidUnderCode()
	{
	   var divNm = document.getElementById("txtSenderCodeDesc").value;			  
	   if(divNm == '-')
	       alert('Please Enter Right Sender Code...');	  
	   if(divNm == '--')
	       alert('Please First Enter Division Code Than Sender Code...');	  
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
				var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=verifyDistributionHeadStr";
				baseUrl += "&txtDemandNo=" + document.getElementById("txtDemandNo").value;		
				baseUrl += "&txtMjrHd=" + document.getElementById("txtMjrHd").value;	
				baseUrl += "&txtSubMjrHd=" + document.getElementById("txtSubMjrHd").value;	
				baseUrl += "&txtMinHd=" + document.getElementById("txtMinHd").value;	
				baseUrl += "&txtSubHd=" + document.getElementById("txtSubHd").value;	
				baseUrl += "&txtDtlHd=" + document.getElementById("txtDtlHd").value;	
				baseUrl += "&txtObjHd=" + document.getElementById("txtObjHd").value;	
				baseUrl += "&txtSchemeNo=" + document.getElementById("txtSchemeNo").value;	
				
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
	//---------------- AJAX CODE FOR CHECK HEAD STRUCTURE VALIDITY ----END-----------------------------------
    
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
	
	<c:set var="resultObj" value="${result}" > </c:set>	
	<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
	<c:set var="lArrDistributionTypeDtls" value="${resValue.lArrDistributionTypeDtls}"> </c:set>	
	<c:set var="LcOrdNo" value="${resValue.LcOrdNo}"> </c:set>
	
	<body>	
		
		<hdiits:form name="frmLcDistributionMst" validate="true" method="post">
		
		<fieldset  style = "width:965px" class="tabstyle">
	    <legend  id="headingMsg"><fmt:message key="LC.DISTRIBUTION_MST" bundle="${lcexpLabels}"></fmt:message></legend>
	    <p>
		
		<table width="90%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
		        
		        <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>			
		        <tr>				
					<td align="left">	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;					
							&nbsp;&nbsp;&nbsp;<fmt:message key="LC.LC_NO" bundle="${lcexpLabels}"></fmt:message>							
							&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input readonly type="text" name="txtLcNo" value="${resValue.LcOrdNo}" maxlength="18" class="TextCss"  size="15"  />
					</td>
				</tr>
				
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DIVISION_CD" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input type="text" name="txtDivisionCode" id="txtDivisionCode" class="TextCss" maxlength="7" size="15"  /> *
				   </td>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.DIVISION_NM" bundle="${lcexpLabels}"></fmt:message>				   			
							&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input readonly type="label" name="txtDivisionName" id="txtDivisionName" class="LabelCss"  size="30"  />
				            <input  type="hidden" name="txtDivisionId" id="txtDivisionId" class="TextCss"  size="20"  />
				            <input  type="hidden" name="txtBudHdStr" id="txtBudHdStr" class="TextCss"  size="20"  />
				   </td>
				</tr>
				
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.ISSUE_DT" bundle="${lcexpLabels}"></fmt:message>
				   			
							<%
						     String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);
							 SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
							 Date dt=new Date();
							 String lStrCurDt = sdf1.format(dt);
															
							%>								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input type="text" name="txtLcIssueDate" id="txtLcIssueDate" value="<%=lStrCurDt%>" maxlength="10"  class="TextCss" size="12" onblur="chkDateFormat(this)"  /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtLcIssueDate",375,570) >
											* (dd/mm/yyyy)
				   </td>
				   <td>
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   <fmt:message key="LC.ENTRY_TYPE" bundle="${lcexpLabels}"></fmt:message>				   
					   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
					   	<hdiits:select name="cmbEntryType" id="cmbEntryType"  style="width:130px" >					   	
							
							<c:forEach var="cmnvo" items="${lArrDistributionTypeDtls}">									
									<hdiits:option value="${cmnvo.commonId}">
										<c:out value="${cmnvo.commonDesc}"></c:out>
									</hdiits:option>
							</c:forEach>
							
					    </hdiits:select>
				   </td>
				</tr>
				
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.VALID_FROM" bundle="${lcexpLabels}"></fmt:message>
				   			
							<%
						     dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);															
							%>								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input type="text" name="txtLcValidFrom" value="<%=lStrCurDt%>" id="txtLcValidFrom" maxlength="10"  class="TextCss"  size="12" onblur="chkDateFormat(this)" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtLcValidFrom",375,570) >
									* (dd/mm/yyyy)
									
				   </td>
				
				  <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			 <fmt:message key="LC.VALID_UPTO" bundle="${lcexpLabels}"></fmt:message>
				   			
							<%
						      dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);															
							%>								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input type="text" value="<%=lStrCurDt%>" name="txtLcValidUpto" id="txtLcValidUpto"  maxlength="10"  class="TextCss"  size="12" onblur="chkDateFormat(this)" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtLcValidUpto",375,570) >
										* (dd/mm/yyyy)
				   </td>
				</tr>
				
				<tr>
				    <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.INWARD_NO" bundle="${lcexpLabels}"></fmt:message>				   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input type="text" name="txtInwardNo" id="txtInwardNo" class="TextCss" maxlength="10" size="15" onblur="isNumeric(this)" /> *
				   </td>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.INWARD_DT" bundle="${lcexpLabels}"></fmt:message>
				   			
							<%
						      dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);															
							%>								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input type="text" name="txtLcInwardDt" id="txtLcInwardDt" value="<%=dateTime%>" maxlength="10"  class="TextCss"  size="12" onblur="chkDateFormat(this)" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtLcInwardDt",375,570) >
										* (dd/mm/yyyy)
				   </td>
				</tr>	
				
				<tr>
				   <td>
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
					     &nbsp;&nbsp;&nbsp;<fmt:message key="LC.SENDER_CD" bundle="${lcexpLabels}"></fmt:message>&nbsp;
					    
					   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
					   	<input type="text" name="txtSenderCode" class="TextCss" maxlength="7" size="15"  />
				   </td>
				   
				   <td>
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <fmt:message key="LC.DESCRIPTION" bundle="${lcexpLabels}"></fmt:message>&nbsp;				   
					   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
					   	<input readonly type="text" name="txtSenderCodeDesc" class="TextCss" maxlength="120" size="30"  />
				   </td>
				</tr>
				
				<tr>
				   <td>
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
					    &nbsp;&nbsp;&nbsp;<fmt:message key="LC.PARTY_REF_NO" bundle="${lcexpLabels}"></fmt:message>&nbsp;					   
					   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
					   	<input type="text" name="txtPartyRefNo" id="txtPartyRefNo" class="TextCss" maxlength="15" size="15" onblur="isAlphaNumericWithSlace(this)" /> *
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.PARTY_REF_DT" bundle="${lcexpLabels}"></fmt:message>
				   			
							<%
						      dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);															
							%>								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
							<input type="text" value="<%=lStrCurDt%>" name="txtPartyRefDt" id="txtPartyRefDt" maxlength="10"  class="TextCss"  size="12" onblur="chkDateFormat(this)" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtPartyRefDt",375,570) >
									* (dd/mm/yyyy)
				   </td>
				</tr>	
		     
		        <tr>
				   <td>
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
					    &nbsp;&nbsp;&nbsp;<fmt:message key="LC.GRNT_ORD_NO" bundle="${lcexpLabels}"></fmt:message>&nbsp;					   
					   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
					   	<input type="text" name="txtGrntOrdNo" id="txtGrntOrdNo" class="TextCss" maxlength="20" size="15"  onblur="isAlphaNumericWithSlace(this)"/> *
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.GRNT_ORD_DT" bundle="${lcexpLabels}"></fmt:message>
				   			
							<%
						      dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							 request.setAttribute("dateTime",dateTime);															
							%>								
							&nbsp;:&nbsp;&nbsp;<input type="text" value="<%=lStrCurDt%>" name="txtGrntOrdDt" id="txtGrntOrdDt" maxlength="10"  class="TextCss"  size="12" onblur="chkDateFormat(this)" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtGrntOrdDt",375,570) >
					        * (dd/mm/yyyy)
				   </td>
				</tr>	
				
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.LC_AMT" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input type="text" value=0 readonly name="txtLcAmt" style="text-align:right" class="TextCss"  size="15"  />
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.BALANCE_AMT" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;:&nbsp;&nbsp;
							<input type="text" name="txtLcBalanceAmt" style="text-align:right" id="txtLcBalanceAmt" value=0 readonly class="TextCss"  size="16"  />
				   </td>
				</tr>
				
				<tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.PROG_AMT" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
				   			<input type="text" value=0 readonly name="txtProgressiveAmt" style="text-align:right" id="txtProgressiveAmt" class="TextCss"  size="15"  />
								
				   </td>
				</tr>
				
				 <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>				 
			
			</table>
		</fieldset>	
		
		<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
		
		<fieldset  style = "width:965px" class="tabstyle">
	    <legend  id="headingMsg"><fmt:message key="LC.DIV_HEAD_DTLS" bundle="${lcexpLabels}"></fmt:message></legend>
	    <p>
		<table width="90%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
			 <tr>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.MJR_HD" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input readonly type="text" name="txtDivMjrHd" id="txtDivMjrHd" style="text-align:right" maxlength="4" class="TextCss"  size="15"  /> 
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.SUB_MJR_HD" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;:&nbsp;
							<input readonly type="text" name="txtDivSubMjrHd"  id="txtDivSubMjrHd" id="txtSubMjrHd" style="text-align:right" class="TextCss"  maxlength="2" size="15"  />
				   </td>
			</tr>
				
				<tr>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.MINOR_HD" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input readonly type="text" name="txtDivMinHd" id="txtDivMinHd" style="text-align:right" class="TextCss" maxlength="3" size="15"  />
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.SUB_HD" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
							<input readonly type="text" name="txtDivSubHd" id="txtDivSubHd" style="text-align:right" class="TextCss"  maxlength="2" size="15"  />
				   </td>
				</tr>
				
				<tr>
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DTL_HD" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input readonly type="text" name="txtDivDtlHd" id="txtDivDtlHd" style="text-align:right" class="TextCss" maxlength="5" size="15"  />
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.OBJ_HD" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
							<input readonly type="text" name="txtDivObjHd"  id="txtDivObjHd" style="text-align:right" class="TextCss"  maxlength="5" size="15"  />
				   </td>
				</tr>
		
		
		
		</table>
		</fieldset>
		
		<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
		<fieldset  style = "width:965px" class="tabstyle">
	    <legend  id="headingMsg"><fmt:message key="LC.HEAD_DTL" bundle="${lcexpLabels}"></fmt:message></legend>
	    <p>
		
		<table width="90%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
				
			   
			   <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			   
			   				
			   <tr>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.BUD_TYPE" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<hdiits:select name="cmbBudgetType" id="cmbBudgetType"  style="width:125px" >				   	
								<option value="-1">--Select--</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>															
					        </hdiits:select> *
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.DEMAND_NO" bundle="${lcexpLabels}"></fmt:message>			   			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
							<input type="text" name="txtDemandNo" id="txtDemandNo" style="text-align:right" class="TextCss"  maxlength="3" size="15"  onblur="isNumeric(this)"/> *
				   </td>
				</tr>
			   
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
				   <td align="left">
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  
				   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.SCHEME_NO" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input type="text" name="txtSchemeNo" id="txtSchemeNo" style="text-align:right" class="TextCss" maxlength="7" size="15" onblur="isNumeric(this)" />
							
									
				   </td>
				   <td align="left">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   			<fmt:message key="LC.AMT" bundle="${lcexpLabels}"></fmt:message>
				   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				   			<input type="text" value=0 name="txtHeadAmt" style="text-align:right" class="TextCss" maxlength="19" size="15" onblur="isNumericWD(this)" /> *
								
				   </td>
				</tr>				 
	           
				
			     <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
				<tr align="center" id="bttnADD">
				
					<td colspan="6">
						
							<hdiits:button type="button" name="btnAdd" id="btnAdd" value="Add" style="width:80px" onclick="addHeadRow()"></hdiits:button>								
							<hdiits:button type="button" name="btnClear" value="Clear" style="width:80px" onclick="clearHeadField()"></hdiits:button>
						
					</td>
				</tr>
				<!--<tr align="center" id="bttnDEDUCT" >
				
					<td colspan="6" >
						
							<hdiits:button type="button" name="btnDeduct" id="btnDeduct" value="Duduct" style="width:80px" onclick="addHeadRow()"></hdiits:button>							
							<hdiits:button type="button" name="btnClear1" value="Clear" style="width:80px" onclick="clearHeadField()"></hdiits:button>
						
					</td>
				</tr>			
			-->
			<tr></tr>
			
			<table width="90%" align="center" id="ReceiptJottingTbl" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="1">
			  <tbody>
				<tr class="TableHeaderBG">
					<td align="center" >
						<input disabled type="checkbox" >
					</td>
					<td align="center" >
						<fmt:message key="LC.BUD_TYPE" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.DEMAND_NO" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.MJR_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.SUB_MJR_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.MINOR_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.SUB_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.DTL_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.OBJ_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.SCHEME_NO" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.AMT" bundle="${lcexpLabels}"></fmt:message>(in Rs.)
					</td>
				</tr>
				
		 	
			  </tbody>				
				<tr >
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="right" >
						Total :
					</td>
					<td align="left" >
						<input readonly type="text" name="txtHdTotal" style="text-align:right" class="TextCss"  size="15"  />
						<input  type="hidden" name="totalHdRow" id="totalHdRow" class="TextCss"  size="15"  />
					</td>
				</tr>
			    		    
			    <tr align="center">
				   <td colspan="11">
						<hdiits:button type="button" name="btnDelete" value="Delete" style="width:80px" onclick="deleteHeadRow()"></hdiits:button>
				   </td>
			   </tr> 
			
				
			<hdiits:tr><hdiits:td colspan="6"></hdiits:td></hdiits:tr>	<hdiits:tr><hdiits:td colspan="6"></hdiits:td></hdiits:tr>
			<hdiits:tr><hdiits:td colspan="6"></hdiits:td></hdiits:tr>	
				
			<tr align="center">
			
				<td colspan="11">
					
						<hdiits:button type="button" name="btnSubmit" value="Submit" style="width:80px" onclick="saveLcDistribution()"></hdiits:button>
						<hdiits:button type="button" name="btnClose" value="Close" style="width:80px" onclick="closeWindow()"></hdiits:button>
										
				</td>
			</tr>
			
			</table>	
		</table>
		</fieldset>
	</hdiits:form>

</body>

<%  String lStrResult = "";
	if(request.getAttribute("distributionResult") != null)
	{
		lStrResult=request.getAttribute("distributionResult").toString();
	}
	if(lStrResult != null && lStrResult.equals("true") )
	  { 
	  %>
	     <script>  alert('LC Distributed/Withdraw Successfully..');</script>
	 <%
	  }
	  else if(lStrResult != null && lStrResult.equals("false") )
	  {
	  %>
	     <script>  alert('There is Some Problem in LC Distribution/Withdraw!!!');</script>
	  <%
	  }
%>

<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDivNameAndBal" 
	   		action="txtDivisionCode"  source="txtDivisionCode" 
	   		target="txtDivisionName,txtLcBalanceAmt,txtProgressiveAmt,txtDivisionId,txtDivMjrHd,txtDivSubMjrHd,txtDivMinHd,txtDivSubHd,txtDivDtlHd,txtDivObjHd" 
		   	parameters="txtDivCode={txtDivisionCode}" 
		   	postFunction ="invalidDivision" eventType="blur" >
</ajax:updateField>

<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getUnderCodeDesc" 
	   		action="txtSenderCode"  source="txtSenderCode" target="txtSenderCodeDesc" 
		   	parameters="txtSenderCode={txtSenderCode},txtDivCode={txtDivisionCode}" 
		   	postFunction ="invalidUnderCode" eventType="blur" >
</ajax:updateField>

</html>
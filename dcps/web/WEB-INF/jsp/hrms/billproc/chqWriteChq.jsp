<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map,com.tcs.sgv.billproc.common.valueobject.TrnShowBill,com.tcs.sgv.billproc.cheque.valueobject.ChequeVO"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.tcs.sgv.billproc.cheque.valueobject.RltBillCheque"%>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<%@page import="java.text.DecimalFormat"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="CHQHEADER.TITLE" bundle="${billprocLabels}"></fmt:message></title>
		<script type="text/javascript" src="script/common/calendar.js"> </script>
		<script type="text/javascript" src="script/common/tabcontent.js"> </script>
		<script type="text/javascript" src="script/common/commonfunctions.js"> </script>
		<script type="text/javascript" src="script/billproc/validation.js"> </script>
		<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
		<script language="javascript">
		var billLst;
		var billCntrlLst;
		var billAmt;
		var ddoList ;
		var billDates;
		function loadcalendar(name,img)
  	    {			  
		   var cal1 = new CalendarPopup();			   
		   cal1.select(name,img,'dd/MM/yyyy'); 
		   return false;			   
	    }
					
		function showBill(url)
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
		}
				
		function displ()
		{						
			if(submitCheques()== true)
			{						
				return true;
			}
		}
		function submitCheques()
		{	
			var tbl = document.getElementById("tblchklist").rows.length;
			if(parseInt(tbl)<2)
			{
				alert("No Cheques to Save");
				return false;
			}
			document.frmWriteChq.action ='ifms.htm?actionFlag=writeCheque';
			document.frmWriteChq.btnSave.disabled = true;	
			document.frmWriteChq.btnClose.disabled = true;
			document.frmWriteChq.submit();
			return true;
		}
		function deleteCheques()
		{
			var indx  =0;
	    	for(i=0;i < document.frmWriteChq.elements.length;i++)
			{
				if(document.frmWriteChq.elements[i].type =="checkbox" && document.frmWriteChq.elements[i].name != "chkSelect")
				{
					if(document.frmWriteChq.elements[i].checked)
					{
						indx++;
					} 
				}
			}
	    	
	    	if(indx==0)
	    	{
	    	 alert("Select atleast one checkbox");
	    	 return false;
	    	}
			document.frmWriteChq.action ='ifms.htm?actionFlag=chequeInSession&flag=REMOVE';
			document.frmWriteChq.submit();
		}
		 function DeleteThis(obj,tblId)
		 {	   	 	 
		      var rowID = showRowCell(obj);            
		      var tbl = document.getElementById(tblId);    
		      tbl.deleteRow(rowID);  
		      CaluculateTotal();		      
		 }
		 function showRowCell (element)
		 {
		    var cell, row;    
		    if (element.parentNode) 
		    {
		      do
		      cell = element.parentNode;
		      while (cell.tagName.toLowerCase() != 'td')
		      row = cell.parentNode;
		    }
		    else if (element.parentElement) 
		    {
		      do
		      cell= element.parentElement;
		      while (cell.tagName.toLowerCase() != 'td')
		      row = cell.parentElement;
		    }
		    return row.rowIndex;
		}	  
			function checkUncheckAll(theElement) {
	     var theForm = theElement.form, z = 0;
		 for(z=0; z<theForm.length;z++){
	
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
		  theForm[z].checked = theElement.checked;
		  }
	     }
	     for(z=0; z<theForm.length;z++){
	      
	      if(theForm[z].type == 'select-one'){
		  //alert("Name"+ theForm[z].name +"VALUE"+theForm[z].value);
		  }
	     }
	     }
		</script>
	</head>	
	<body>
	   	   	<hdiits:form name="frmWriteChq" validate="true"  method="post">
	   	   	<input type="hidden" name="toPost" />
   	   	<input type="hidden" name="toUser" />
   	   	<input type="hidden" name="SendTo" value="-1">
		<%
			ResultObject resObj = (ResultObject)request.getAttribute("result");
			Map map = (Map)resObj.getResultValue();
			List BillPartyList = (List)map.get("BillPartyList");
			List billList = (List)session.getAttribute("BillList");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		%>	
		<script> 
			billLst = new Array('<%=billList.size()%>');		
			billCntrlLst = new Array('<%=billList.size()%>');
			billAmt = new Array('<%=billList.size()%>');
			ddoList = new Array('<%=billList.size()%>');
			billDates = new Array('<%=billList.size()%>');
		</script>
		<%
			 double billAmount = 0;
				if(billList != null)
				{
					for(int i=0;i< billList.size();i++)
					{
						TrnShowBill trnShowVO = (TrnShowBill)billList.get(i);
					%>						
						<script>
						var billCheqAmt =0;
						billLst[<%=i%>] = '<%=trnShowVO.getBillNo()%>';						
						billCntrlLst[<%=i%>] = '<%=trnShowVO.getBillCntrlNo()%>';
						billAmt[<%=i%>] = '<%=trnShowVO.getBillNetAmount().intValue()%>';								
						ddoList[<%=i%>] = '<%=trnShowVO.getDdoName()%>';
						billDates[<%=i%>] = '<%=sdf.format(trnShowVO.getBillDate())%>';
						</script>
						<input type="hidden" name="bill<%=trnShowVO.getBillNo()%>" >
					<%
						billAmount = billAmount + trnShowVO.getBillNetAmount().doubleValue();											
					}
				}
				
				List chequeList = (List)session.getAttribute("ChequeList");
				double chequeAmt =0;
				
				if(chequeList != null)
				{
					for(int i=0;i< chequeList.size();i++)
					{
						ChequeVO cheque = (ChequeVO)chequeList.get(i);
						chequeAmt = chequeAmt + cheque.getTrnChequeDtls().getChequeAmt().doubleValue();				
						for(int j=0;j< cheque.getBillChequeRlt().size();j++)
						{							
						   RltBillCheque billCheque = (RltBillCheque)cheque.getBillChequeRlt().get(j);
							%>							
							<script>
								var billVal = eval("document.forms[0].bill"+<%=billCheque.getBillNo()%>);
								billCheqAmt = parseFloat(billCheqAmt) + parseFloat(billVal.value);							
								billVal.value  = billCheqAmt;
							</script>							
							<%
							
						}
					}
				}
				DecimalFormat dFormat=new DecimalFormat();
				dFormat.applyPattern("#0.00");
				String chequeAmtString=dFormat.format(chequeAmt);
				
				dFormat.applyPattern("#0.00");
				String billAmtString=dFormat.format(billAmount);
			%>
			
					
		<c:set var="resultObj" value="${result}"> </c:set>		
		<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>			
		<c:set var="billList" value="${sessionScope.BillList}" scope="session"> </c:set>			
		<c:set var="ChequeList" value="${sessionScope.ChequeList}" scope="session"> </c:set>			
		<c:set var="BillPartyList" value="${resValue.BillPartyList}" > </c:set>			

		<table align="center" width="90%">
			<tr>
				<td align="center">
				</td>
			</tr>
		</table>
		<br><br>
		<table align="center" width="90%">
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>		
			<tr>
				<td align="center" colspan="8">
				<center><b>
					<fmt:message key="CHQWRITE.TITLE" bundle="${billprocLabels}"></fmt:message>
				</b></center>
				</td>
			</tr>
				<tr><td><br></td></tr>		
			<tr>
				<td align="center" colspan="8">
				<fmt:message key="CHQWRITE.BILLINFO" bundle="${billprocLabels}"></fmt:message>

				</td>
			</tr>
			<tr>
			   <td class="datatableheader"> 
			   
					<fmt:message key="CMN.SR_NO" bundle="${billprocLabels}"></fmt:message>
			   
			   </td>
			   <td class="datatableheader">
  				
  					<fmt:message key="CMN.BILL_NO" bundle="${billprocLabels}"></fmt:message>
  				
  				</td>
  			   <td class="datatableheader">
   		
   					<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
   		
   				</td>	
  				<td class="datatableheader">
  				
   				
   					<fmt:message key="CHQ.HEAD_NO" bundle="${billprocLabels}"></fmt:message>
   				
   				</td>	
					
   				<td class="datatableheader">
   				
   					<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
   				
   				</td>
   				<td class="datatableheader">
			     	<fmt:message key="CMN.BILL_AMOUNT" bundle="${billprocLabels}"></fmt:message>
			    </td>	
				<td class="datatableheader">
			    	<fmt:message key="CHQ.ACTUAL_AMOUNT" bundle="${billprocLabels}"></fmt:message>
			    
			    </td>	   			
	   		</tr>
 	<% int jj=0; %>
   <c:forEach var="bill" items="${billList}" varStatus="counter">
   
   			<tr class="odd">    				
				<td><c:out value="${counter.count}"></c:out></td>	
				<td><c:out value="${bill.billCntrlNo}" ></c:out></td>
	   			<td><c:out value="${bill.tokenNum}" ></c:out></td>
   				<td><c:out value="${bill.budmjrHd}" ></c:out></td>	         				
   				<td><c:out value="${bill.billType}" ></c:out></td>	
   				<td align="right"><fmt:formatNumber type="number" pattern=".00" value="${bill.billGrossAmount}"></fmt:formatNumber></td>   				
   				<td align="right"><fmt:formatNumber type="number" pattern=".00" value="${bill.billNetAmount}" ></fmt:formatNumber></td>
   				
   				<input type="hidden" name="${bill.billNo}" id="${bill.billNo}" value="${bill.ddoName}~${bill.billNetAmount}" > 
   				<input type="hidden" name="ddos<%=jj%>" id="ddos<%=jj%>" value="${bill.ddoName}">
   			</tr>
   			<% jj++; %>
   	</c:forEach>
   	<input type="hidden" name="ddolength" id="ddolength" value="<%=jj%>">
   	</table>

   <table align="center" width="90%">
		<tr class="even">
			<td colspan ="7" width="80%" align="right">Bill Total :</td>
			 <td align="right">&nbsp;<%=billAmtString%></td>
		</tr>	
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>

		<table align="center" width="90%">
		<tr>
		<td>&nbsp;
		</td>
		</tr>
   			<tr>
	   			<td  class="datatableheader">
				<fmt:message key="CHQWRITE.CHEQUES" bundle="${billprocLabels}"></fmt:message> :
   				</td>
				<td align="left"  class="odd">
					<input type="radio"  name="rdoTransReg" value="Single" checked="checked" onclick="DisableButton()">
					<fmt:message key="CHQWRITE.SINGLE" bundle="${billprocLabels}"></fmt:message>
				</td>
				<td align="left"  class="odd">
					<hdiits:radio name="rdoTransReg" value="Multiple"  onclick="EnableButton()"></hdiits:radio>
					<fmt:message key="CHQWRITE.MULTIPLE" bundle="${billprocLabels}"></fmt:message>

				</td>
				
											
				
			</tr>
		</table>		
		<table align="center">
		<br>
		<hdiits:button name="btnAddRow" value="Add Row" type="button"  readonly="true" onclick="AddRowToTable()"/>
		<br>
		</table>
		<table align="center" width="90%" id="table3">

 			<tr>
				 <td class="datatableheader">
				  
						<fmt:message key="CHQ.CHEQUE_DATE" bundle="${billprocLabels}"></fmt:message>				  
			   </td>		   
			   
   				<td class="datatableheader">
   				<fmt:message key="CHQCOMMON.TYPE" bundle="${billprocLabels}"></fmt:message>
   				</td>	
			    <td class="datatableheader">
   				
   					<fmt:message key="CMN.IN_FAVOUR_OF" bundle="${billprocLabels}"></fmt:message>
   				
   				</td>
   				 <td class="datatableheader">
  				
  					<fmt:message key="CHQ.ADDRESS" bundle="${billprocLabels}"></fmt:message>
  				
  				</td>
   				 <td class="datatableheader">
  			
  					<fmt:message key="CHQ.ACCOUNT_NO" bundle="${billprocLabels}"></fmt:message>
  				
  				</td>  				
  				 <td class="datatableheader">
   			
   					<fmt:message key="CHQ.CHEQUE_AMOUNT" bundle="${billprocLabels}"></fmt:message>
   				
   				</td>		
   					
			</tr>
			
   			<%
   			
			double remainAmt = Double.parseDouble(billAmtString) - Double.parseDouble(chequeAmtString);

   				int nextIndex =1;   			
				Date todayDt = new Date(System.currentTimeMillis());
				String todayDate = sdf.format(todayDt);		
				if(BillPartyList!=null && BillPartyList.size()>0)
				{
   			%>
   			 <c:forEach var="BillParty" items="${BillPartyList}">
   				<tr> 
					<td><input type="text" name="txtDate<%=nextIndex%>" attrTitle="Cheque Date"  maxlength="10"  class="TextCss"  size="10" value="<%=todayDate%>" /><img src="images/CalendarImages/ico-calendar.gif" width="20" onClick=window_open("txtDate<%=nextIndex%>",375,570) >
					</td>					
					<td>
					 <select class="TextCss" attrTitle="Type" name="cmbType<%=nextIndex%>" onchange="javascript:selectDdo('<%=nextIndex%>')">
						 <option value="PARTY"><fmt:message key="CHQWRITE.PARTYCHEQUE" bundle="${billprocLabels}"></fmt:message></option>
						 <option value="DDO"><fmt:message key="CHQWRITE.DDOCHEQUE" bundle="${billprocLabels}"></fmt:message></option>
					 </select>
	   				</td>
	 	   			<td>
	 	   				<input type="text" name="txtPartyName<%=nextIndex%>" attrTitle="Party Name" value="${BillParty.partyName}" class="TextCss" size="18" /><img src="images/party.gif" onclick="ShowAllParty('<%=nextIndex%>')">
		   			</td>
		   			 <td>
	 	   				<input type="text"  name="txtAddress<%=nextIndex%>" attrTitle="Address" value="${BillParty.partyAddr}" class="TextCss" size="16" />
		   			</td>
		   			<td>
	 	   				<input type="text" name="txtAccountNo<%=nextIndex%>" attrTitle="AccountNo"  value="${BillParty.accntNum}" class="TextCss" size="16" />
		   			</td> 
	   				<td>					
						<input type="text" name="txtChqAmt<%=nextIndex%>" attrTitle="Cheque Amount" value="${BillParty.partyAmt}" onBlur="CaluculateTotal()"  class="TextCss" size="14" /><img src="images/CalendarImages/DeleteIcon.gif" onclick="DeleteThis(this,'table3')" />
						<input type="hidden" name="partyCode<%=nextIndex%>"  value="${BillParty.partyCode}" >
	   				</td>	   				     
	   			</tr>

	   			<%	   				
		   			nextIndex++;
	   				%>
   			</c:forEach>
	   				<%
					}
					else
					{
						
	   			%>
	   							
   				<tr> 
				<td>
				
					<input type="text" name="txtDate1" attrTitle="Cheque Date"   maxlength="10"  class="TextCss"  size="10" value="<%=todayDate%>" /><img src="images/CalendarImages/ico-calendar.gif" width="20" onClick=window_open("txtDate1",375,570) >
					</td>					
					<td>
					 <select class="TextCss" attrTitle="Type" name="cmbType1" onchange="javascript:selectDdo(1)">
						<option value="PARTY"><fmt:message key="CHQWRITE.PARTYCHEQUE" bundle="${billprocLabels}"></fmt:message></option>						
						<option value="DDO"><fmt:message key="CHQWRITE.DDOCHEQUE" bundle="${billprocLabels}"></fmt:message></option>
					</select>
   				</td>
 	   			<td>
 	   				<input type="text" name="txtPartyName1" attrTitle="Party Name" class="TextCss" size="18" /><img src="images/party.gif" onclick="ShowAllParty('1')">
	   			</td>
	   			 <td>
 	   				<input type="text"  name="txtAddress1" attrTitle="Address" class="TextCss" size="16" />
	   			</td>
	   			<td>
 	   				<input type="text" name="txtAccountNo1" attrTitle="AccountNo" class="TextCss" size="16" />
	   			</td>  			
				
   				<td>					
					<input type="text" name="txtChqAmt1" attrTitle="Cheque Amount" class="TextCss" size="16" value="<%=dFormat.format(remainAmt)%>" onBlur="CaluculateTotal()" />
					<input type="hidden" name="partyCode1" value="" >					
   				</td>   				     
   			</tr>
   			
   			<%
				}
   			%>
   			
   		</table>   		
		<table width="90%">
		
		<tr class="even" >
			<td align="right" colspan="5" width="82%"><fmt:message key="CHQWRITE.RemainingAmount" bundle="${billprocLabels}"></fmt:message>:</td>
		 	<td id="CheqAmtId" align="right"><%=dFormat.format(remainAmt)%>
		 	</td>
		</tr>
		
		</table>
   		
   		<table align="center" width="90%" >
			<tr><td><br></td></tr>
  			<tr>
  				<td colspan="8" align="center">
  					<hdiits:button type="button" name="btnSubmit" value="Add" onclick="javascript:showValue()"/>
  				</td>
			</tr>
		</table>
		<br>
		<table align="center" width="97%" id="tblchklist">
			<tr>
			 <td class="datatableheader">
			   		<hdiits:checkbox name="chkSelect" value="" onclick="checkUncheckAll(this);"></hdiits:checkbox>
			   </td>
			   <td class="datatableheader">
   					<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message>   					
   				</td>
   				 <td class="datatableheader">
   					<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>   					
   				</td>   				
	   			 <td class="datatableheader">
	   				<fmt:message key="CHQ.CHEQUE_DATE" bundle="${billprocLabels}"></fmt:message>	   				
	   		 	</td>	
   				 <td class="datatableheader">  					
   					<fmt:message key="CMN.IN_FAVOUR_OF" bundle="${billprocLabels}"></fmt:message>   					
   				</td>   					
	   			 <td class="datatableheader">  				
  					<fmt:message key="CHQ.ADDRESS" bundle="${billprocLabels}"></fmt:message>  				
  				</td>
   				 <td class="datatableheader">  			
  					<fmt:message key="CHQ.ACCOUNT_NO" bundle="${billprocLabels}"></fmt:message>  				
  				</td>  	
  				 <td class="datatableheader">   				
   					<fmt:message key="CMN.CHEQUE_AMOUNT" bundle="${billprocLabels}"></fmt:message>   					
	   			</td>	
		   </tr>
		<%int jjj=1; %>
		<c:forEach var="chequeVo" items="${ChequeList}" varStatus="counter">
		 <c:set var="trnCheqDtls" value="${chequeVo.trnChequeDtls}" />
		   <tr>
			    <td class="odd">
			   		<hdiits:checkbox name="chkSelect1" value="${counter.count}"></hdiits:checkbox>
			    </td>
			    <td  class="odd">
			   	<table>
			   	 <c:forEach var="bcNum" items="${chequeVo.billCntrlNo}" >
			   	  <tr>
			   	  <td class="odd">
			   	    <c:out  value="${bcNum}" ></c:out>
			   	  </td>
			   	  </tr>
			   	 </c:forEach>
			   	</table>
				</td>
			   	<td id="tkn" class="odd">
			   	<table>
			   	 <c:forEach var="token" items="${chequeVo.tokenNo}" >
			   	  <tr>
			   	  <td class="odd">
			   	    <c:out  value="${token}" ></c:out>
			   	  </td>
			   	  </tr>
			   	 </c:forEach>
			   	</table>
				</td>				
				<td id="date" class="odd"><fmt:formatDate value="${trnCheqDtls.fromDt}" type="DATE" pattern="dd/MM/yyyy"/></td>
			   	<td  class="odd" id="pname"><c:out value="${trnCheqDtls.partyName}"></c:out></td>
			   	
			   	<input type="hidden" name="partyArrLst<%=jjj%>" value="${trnCheqDtls.partyName}" >
			   	<input type="hidden" name="accountArrLst<%=jjj%>" value="${trnCheqDtls.accntNum}" >
			   	<td  class="odd" id="paddrs"><c:out value="${trnCheqDtls.partyAddr}"></c:out></td>
			   	<td  class="odd" id="pacnt"><c:out value="${trnCheqDtls.accntNum}"></c:out></td>
			   	<td align="right" class="odd" id="chqamt"> <fmt:formatNumber type="number" pattern=".00" value="${trnCheqDtls.chequeAmt}"></fmt:formatNumber></td>
			</tr>
			<% jjj++; %>	
		</c:forEach>	

		   		
		</table>
		<table width="97%" align="center">
		<tr class="even">
		 <td colspan="6"  width ="70%" align="right"><fmt:message key="CHQWRITE.TotalAmount" bundle="${billprocLabels}"></fmt:message> :</td>
		 <td align="right"><%=chequeAmtString%></td>
		</tr>	
		</table>
		<input type="hidden" name="rowCount" value ="0"/>
		<br>
		<table align="center" width="90%">
			<tr>
				<td align="center">
					<hdiits:button name="btnDelete" value='<%=buttonBundle.getString("COMMON.DELETE")%>' type="button" onclick="deleteCheques()" />&nbsp;
					<hdiits:button name="btnSave" value='<%=buttonBundle.getString("COMMON.SAVE")%>' type="button" onclick="submitCheques()"/>&nbsp;
   					<hdiits:button type="button" name="btnClose" value='<%=buttonBundle.getString("CHQCOMMON.CLOSE")%>' onclick="window.close()" />
				</td>
			</tr>
		</table>

	<script><!--
		//var accNumbers='';
		//var partyNames='';
		function CaluculateTotal()
		{
			var tbl = document.getElementById("table3");
			var rowSize = tbl.rows.length;
			var cAmt =0;
			var ii;
  	        for(ii=1;ii<rowSize;ii++)
  	        {
  	        	var vlau1 = eval("document.frmWriteChq.txtChqAmt"+(ii));
  	        	if(vlau1.value != '')
  	        	{
  	        		if(!validates(vlau1.value))
	  	        	{
		  	             alert("Cheque Amount should be Numeric");
		  	             vlau1.value="";
		  	             vlau1.focus();
		  	        	 return false;
	  	        	}
	  	        	else
	  	        	{
		        		cAmt = parseFloat(cAmt) + parseFloat(vlau1.value);  
		        	}
        		}
  	        }  	        
  	        var remainingAmt = parseFloat('<%=remainAmt%>') - parseFloat(cAmt);
		    document.getElementById("CheqAmtId").innerHTML = remainingAmt;
		    
		    if(document.frmWriteChq.rdoTransReg[1].checked ==true)
		    {
		    	if(remainingAmt > 0)
		    	{
		    		AddRowToTable();
		    	}
		    }
		}
		function EnableButton()
		{
			document.forms[0].btnAddRow.disabled = false;
		}
		function DisableButton()
		{
			var tbl = document.getElementById("table3").rows.length;
			
			if(parseInt(tbl)>2)
			{
				var conf = confirm('Single Chque is selected Do you want to delete Other Chque Informantion');
				if(conf)
				{	
					for(i=document.getElementById("table3").rows.length-1; i > 1; i--)
					{
      					 document.getElementById("table3").deleteRow(i); 			
      				}	 
      				document.forms[0].btnAddRow.disabled = true;
					document.frmWriteChq.txtChqAmt1.value='<%=billAmount%>';
					
				}
				else
				{
					document.frmWriteChq.rdoTransReg[1].checked =true;
					document.forms[0].btnAddRow.disabled = false;
				}
			}
			//document.forms[0].btnAddRow.disabled = true;
		}
		function forwardCheques()
		{
			var billAmt = '<%=billAmount%>';
			var cheqAmt = '<%=chequeAmt%>';		
			
			var tbl = document.getElementById("tblchklist").rows.length;
			
			if(parseFloat(tbl)==1)
			{
				alert("You have not written any Cheque(s).Please enter some data and then try to Save");
				return false;
			}

            document.forms[0].chkSelect.click();
	    	var indx  =0;
	    	for(i=0;i < document.frmWriteChq.elements.length;i++)
			{
				if(document.frmWriteChq.elements[i].type =="checkbox" && document.frmWriteChq.elements[i].name != "chkSelect")
				{
					document.frmWriteChq.elements[i].checked=true;
					if(document.frmWriteChq.elements[i].checked)
					{
						
						indx++;
					} 
				}
			}
	    	
	    	if(indx != (tbl-1))
	    	{
	    	 alert("You need to select all cheques in order to save them");
	    	 return false;
	    	}
			if(parseFloat(billAmt) > parseFloat(cheqAmt))
			{
				alert("Cheque Amount cannot be less than Bill Amount");				
				return false;				
			}
			
			var tbl = document.getElementById("tblchklist");
  	        var rowSize = tbl.rows.length;
  	        
  	        
  	        //var vlau1 = eval("document.frmWriteChq.partyArrLst"+(ii));
  	        //var acc = eval("document.frmWriteChq.accountArrLst"+(ii));    
  	        
  	        for(ii=1;ii<rowSize;ii++)
  	        {
  	        	var vlau1 = eval("document.frmWriteChq.partyArrLst"+(ii));
  	        	var acc = eval("document.frmWriteChq.accountArrLst"+(ii));
  	        	
  	        	if(acc.value!='')
  	        	{
  	        		 var accMatch=0;
  	        			for(iii=1;iii<rowSize;iii++)
  	        			{
			  	        	var vlau11 = eval("document.frmWriteChq.partyArrLst"+(iii));
  	        				var acc1 = eval("document.frmWriteChq.accountArrLst"+(iii));
  	        				
  	        				if(acc1.value==acc.value)
  	        				{
  	        					if(vlau1.value!=vlau11.value)
  	        					{
  	        						accMatch=accMatch+1;
  	        					}
  	        					if(accMatch>0)
  	        					{
  	        						alert('Account Number can not be same for different parties ');
  	        						return false;
  	        					}
  	        				}
  	        				
  	        			}  	        		
  	        	}
  	        }
  	        	
			document.frmWriteChq.btnSave.disabled = true;	
			document.frmWriteChq.btnClose.disabled = true;

			window.open('ifms.htm?actionFlag=getHyrUsers&statusFlag=CHQ_WRTN&BillNo='+billLst[0]+'&page=savedBillsUpdate','','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');			
			
		}
		function AddRowToTable()
	  	{
	      var tbl = document.getElementById("table3");
	      var rowSize = tbl.rows.length;     
	      var row = tbl.insertRow(rowSize);       
	      document.frmWriteChq.rowCount.value = rowSize;
	      var cellDate = row.insertCell(-1);
	      cellDate.align ="center";
	      cellDate.innerHTML = '<input type="text" attrTitle="Cheque Date" name="txtDate'+rowSize+'"   maxlength="10"  class="TextCss"  size="10" value="<%=todayDate%>" /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtDate'+rowSize+'",375,570) >';
	    
	       var cheType = row.insertCell(-1);
	      cheType.align = "center";
	      cheType.innerHTML = '<select name="cmbType'+rowSize+'" attrTitle="Type"  onchange="javascript:selectDdo('+rowSize+')">' +
	      		'<option value="PARTY">Party Cheque</option>'+
	      		'<option value="DDO">DDO Cheque</option>' +
	      		'</select>';	      
	      var cellPartyName = row.insertCell(-1);  
	      cellPartyName.align ="center";
	      cellPartyName.innerHTML = '<input class="TextCss" type="text" attrTitle="In Favour of." name="txtPartyName'+rowSize+'" size="18" ><img src="images/party.gif" onclick="ShowAllParty('+rowSize+')" />';	     
	      
	      var cellPartyAdd = row.insertCell(-1);
	      cellPartyAdd.align ="center";
	      cellPartyAdd.innerHTML = '<input  class="TextCss" type="text" attrTitle="Address" name="txtAddress'+rowSize+'" size="16">';
	      
	      var cellPartyAcc = row.insertCell(-1);
	      cellPartyAcc.align ="center";
	      cellPartyAcc.innerHTML = '<input  class="TextCss" type="text" attrTitle="AccountNo" name="txtAccountNo'+rowSize+'" size="16">';	      
	      
	      var cheqAmt = row.insertCell(-1);
	      cheqAmt.align = "center";
	      cheqAmt.innerHTML = '<input type="text" class="TextCss"  attrTitle="Cheque Amount" name="txtChqAmt'+rowSize+'" onBlur="CaluculateTotal()" size="15"><img src=images/CalendarImages/DeleteIcon.gif onclick="DeleteThis(this,\'table3\')" /><input type="hidden" name="partyCode'+rowSize+'" value="" > ';
	      
			
	  }
	   function showValue()
		{	
			var remainingAmt = document.getElementById("CheqAmtId").innerHTML;
		   	   
			if(document.frmWriteChq.rdoTransReg[0].checked == false && document.frmWriteChq.rdoTransReg[1].checked== false)
			{
				alert("Please select atleast Single or Multiple Option");
				return false;
			}
			
			document.frmWriteChq.action ='ifms.htm?actionFlag=chequeInSession&flag=ADD';
			var tbl = document.getElementById("table3");
  	        var rowSize = tbl.rows.length;    
  	        var ii;
  	        var iii;
  	        var cAmt =0;
  	        var accArr ='';
  	        var tblchqlist = document.getElementById("tblchklist");
  	        var rowSizeOftblchklist = tblchqlist.rows.length;
  	        var addRows = rowSize + rowSizeOftblchklist-2;  	        
  	        //var partyTild='';
  	        
  	        if(document.frmWriteChq.rdoTransReg[0].checked == true )
  	        {	
  	        	if(parseFloat(remainingAmt) != 0 )
  	        	{
  	        		 alert("You can not make a single cheque with amount other than bill amount");
		  	         return false;
  	        	}
				if(parseInt(addRows) > 1)
  	        	{
	  	        	alert("You can not add multiple rows for a single cheque");
  		        	return false;  	        		
  	        	}

  	        }
  	        
  	        for(ii=1;ii<rowSize;ii++)
  	        {
  	        	var vlau1 = eval("document.frmWriteChq.txtPartyName"+(ii));
  	        	var acc = eval("document.frmWriteChq.txtAccountNo"+(ii));  	        	
  	        	if(acc.value!='')
  	        	{
  	        		 var accMatch=0;
  	        			for(iii=1;iii<rowSize;iii++)
  	        			{
			  	        	var vlau11 = eval("document.frmWriteChq.txtPartyName"+(iii));
  	        				var acc1 = eval("document.frmWriteChq.txtAccountNo"+(iii));
  	        				//alert(vlau1.value+' '+  vlau11.value+' '+acc.value+' '+acc1.value);

  	        				if(acc1.value==acc.value)
  	        				{
  	        					if(vlau1.value!=vlau11.value)
  	        					{
  	        						accMatch=accMatch+1;
  	        					}
  	        					if(accMatch>0)
  	        					{
  	        						alert('Account Number can not be same for different parties ');
  	        						return false;
  	        					}
  	        				}
  	        				
  	        			}
  	        		
  	        	}
  	        }
  	        
  	        /* For Cheque Amount to be validated */
  	        for(ii=1;ii<rowSize;ii++)
  	        {
  	        	var vlau1 = eval("document.frmWriteChq.txtChqAmt"+(ii));
  	        	
  	        	if(!validates(vlau1.value))
  	        	{
  	             alert("Cheque Amount should be Numeric");
  	             vlau1.value="";
  	             vlau1.focus();
  	        	 return false;
  	        	}
  	        	else
  	        	{
  	        		cAmt = parseFloat(cAmt) + parseFloat(vlau1.value);
  	        	}
  	        	
  	        }
  	        
  	        for(ii=1;ii<rowSize;ii++)
  	        {
  	        	var vlau1 = eval("document.frmWriteChq.txtDate"+(ii));  
  	        	for(li=0;li<billDates.length;li++)
  	        	{
					if(compareBillCheqDates(vlau1,billDates[li]) == false)
					{
					  return false;					
					}
  	        	}

  	        }
  	        var totalAmt = 0; 	        
			
	        document.frmWriteChq.rowCount.value = rowSize-1;	        

			var remAmt =  document.getElementById("CheqAmtId").innerHTML;
			if(parseInt(remAmt) < 0)
			{
				alert( 'Bill amount is less than Total Cheque amount.Please make changes accordingly');	
				return false;
			}
			if(checkForNull() == true)
			{		

				document.frmWriteChq.submit();
				document.frmWriteChq.btnSubmit.disabled = true;
			}
			else
			{
				return false;
			}
		}
		
		function validates(rmrks)
			{
				var numaric = rmrks;
				for(var j=0; j<numaric.length; j++)
				{
 				    var hh = numaric.charAt(j);
				    if(hh >= '0' && hh <= '9' ||  hh=='.' )
		  			{
				    }
					else	
					{
					    return false;
		            }
				}
				return true;				
			}
		
		function checkForNull()
		{
			for(i=0;i < document.frmWriteChq.elements.length;i++)
			{
			
 			 if(document.frmWriteChq.elements[i].attrTitle!= 'Address' && document.frmWriteChq.elements[i].attrTitle != 'AccountNo')
 			 {
				if(document.frmWriteChq.elements[i].type =="text")
				{				
					if(document.frmWriteChq.elements[i].value =="")
					{
						alert(document.frmWriteChq.elements[i].attrTitle+  ' cannot be empty');						
						document.frmWriteChq.elements[i].focus();
						return false;
					}
					if(parseFloat(document.frmWriteChq.elements[i].value) <= 0)
					{
						alert(document.frmWriteChq.elements[i].attrTitle+  ' should be greater than Zero');						
						document.frmWriteChq.elements[i].value='';
						document.frmWriteChq.elements[i].focus();
						return false;
					}
			   }
			   if(document.frmWriteChq.elements[i].attrTitle == 'Type')
			   {
			   	   if(document.frmWriteChq.elements[i].value =="")
					{
						alert(document.frmWriteChq.elements[i].attrTitle+  ' cannot be empty');						
						document.frmWriteChq.elements[i].focus();
						return false;
					}
			   }
			   			   
			  }	
			
			}
			return true;
		}
		
		
		function selectDdo(cmbNum)
		{
			var cmbName =document.getElementById("cmbType"+cmbNum);
			if(cmbName.value=='DDO')
			{
				ShowAllParty(cmbNum);
			//	document.getElementById("txtPartyName"+cmbNum).value =parts[0];				
			}
			else
			{
				document.getElementById("txtPartyName"+cmbNum).value ='';
				//ShowAllParty(cmbNum);
			}
		}
		
		function selectBill(cmbNum)
		{
			var cmbName =document.getElementById("cmbType"+cmbNum);			
			var billnum = document.getElementById("cmbBillNo"+cmbNum).value;
			var vals = document.getElementById(billnum).value;				
			var parts = vals.split("~");				
			document.getElementById("txtChqAmt"+cmbNum).value =parts[1];
			if(cmbName.value=='DDO')
			{
				document.getElementById("txtPartyName"+cmbNum).value =parts[0];				
			}
			
		}
		function ShowAllParty(indx)
		{
			var i;
			var cmbName =document.getElementById("cmbType"+indx);
			var arr = new Array(eval(document.getElementById("ddolength").value));
			var strDDOS='';
			if(cmbName.value=='DDO')
			{
				if(document.getElementById("ddolength").value==1)
				{
					eval("document.forms[0].txtPartyName"+indx).value=document.getElementById("ddos0").value;
					eval("document.forms[0].txtAddress"+indx).value='';
        	    	eval("document.forms[0].txtAccountNo"+indx).value='';
        	    	eval("document.forms[0].partyCode"+indx).value='';
					return;
				}
				var chkDDONUM=1;
				var chkDDOLIST=document.getElementById("ddos0").value;
				for(i=1;i<document.getElementById("ddolength").value;i++)
				{
					if(chkDDOLIST==document.getElementById("ddos"+i).value)
					{
						chkDDONUM++;
					}	
				}
				if(chkDDONUM==document.getElementById("ddolength").value)
				{
					eval("document.forms[0].txtPartyName"+indx).value=document.getElementById("ddos0").value;
					eval("document.forms[0].txtAddress"+indx).value='';
        	    	eval("document.forms[0].txtAccountNo"+indx).value='';
        	    	eval("document.forms[0].partyCode"+indx).value='';
					return;
				}
				for(i=0;i<document.getElementById("ddolength").value;i++)
				{
					arr[i]=document.getElementById("ddos"+i).value;
					strDDOS=strDDOS+arr[i];
					if(i+1<document.getElementById("ddolength").value)
					{
						strDDOS=strDDOS+'~';
					}
				}
			urlParty="ifms.htm?actionFlag=getAllParties&ddo=y&Index="+indx+"&ddos="+strDDOS;
			}
			else
			{
			urlParty ="ifms.htm?actionFlag=getAllParties&&ddo=n&Index="+indx;
			}
		 	window.open(urlParty,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=350"); 
		}
		
		
	--></script>	
	<%
		String chequeStat = (String)map.get("ChequeStatus");
		if(chequeStat != null && !chequeStat.equals(""))
		{		
	%>
		<script>
			alert('<%=chequeStat%>');
			self.close();
			window.opener.document.forms[0].parentUrl1.value='';			 
			window.opener.document.forms[0].action ='ifms.htm?actionFlag=getSavedBill&statusFlag=BAPPRV_AUD&updStatusFlag=CHQ_WRTN&recFlag=1';
			window.opener.document.forms[0].submit();
			
		</script>
	<%
		}
	%>
	<script>
  				CaluculateTotal();
 			</script>
	</hdiits:form>				
  </body>
</html>
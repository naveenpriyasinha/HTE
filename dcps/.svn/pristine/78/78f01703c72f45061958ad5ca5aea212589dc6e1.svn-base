<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map,com.tcs.sgv.billproc.common.valueobject.TrnShowBill"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/validation.js"></script>
<script type="text/javascript" src="script/billproc/validation.js"></script>    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle bundle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
	Map map = (Map)((ResultObject)request.getAttribute("result")).getResultValue();
//For Written Cheques : Hiral
	int writeFlag = 0;
	System.out.println("Value of map from resultObject on JSP : " +map.toString());
	System.out.println("Value of write Flag in jsp : " +request.getParameter("writeFlag"));
	if(request.getParameter("writeFlag") !=null)
	{
		System.out.println("Value of write Flag in jsp in request : " +request.getParameter("writeFlag"));
		writeFlag = Integer.parseInt(request.getParameter("writeFlag"));
	}
	if(map.get("writeFlag")!=null)
	{
		System.out.println("Value of write Flag in jsp MAP : " +request.getParameter("writeFlag"));
		writeFlag = Integer.parseInt(map.get("writeFlag").toString());
	}
//Ends :   For Written Cheques : Hiral		
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script language="javascript">
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
		
		function searching()
		{
			var str= document.frmChqPrinted.txtSearch.value;
			if(str=='')
			{
			 	alert('Search Text is Empty');
			 	return;
			}
			if(document.frmChqPrinted.cmbSearch.value =='fromDt')
				{
					if(!isDate(document.frmChqPrinted.txtSearch.value))
					{
						return;
					}
				}
				document.frmChqPrinted.action='ifms.htm?actionFlag=getCheques&StatusFlag='+'<%=bundle.getString("STATUS.CheqPrinted")%>';
			
			document.frmChqPrinted.displ.value=str;			
			document.frmChqPrinted.method='post';
			document.frmChqPrinted.submit();
		}
			
		function showChqNo()
		{
			url="ifms.htm?viewName=chqInsertChqNo";
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=400,height=400"); 		
		}	
		function generateAdvice()
		{
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmChqPrinted.elements.length;i++)
		    {
		        if(document.frmChqPrinted.elements[i].type=="checkbox" && document.frmChqPrinted.elements[i].name !='chkSelect')
		        {	
		          if(document.frmChqPrinted.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmChqPrinted.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Please Check atleast one checkbox ');
		       return false;
		    }
			insertdt();  
		}	
		function sendToCounter(type)
		{
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmChqPrinted.elements.length;i++)
		    {
		        if(document.frmChqPrinted.elements[i].type=="checkbox" && document.frmChqPrinted.elements[i].name!='chkSelect')
		        {	
		          if(document.frmChqPrinted.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmChqPrinted.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert('Please Check atleast one checkbox');
		       return false;
		    }
		    var bill = arr[0].split("~");
//	For Written Cheques : Hiral		    
		    <% if(writeFlag!=1) {%>
		    url = 'ifms.htm?actionFlag=getHyrUsers&BillNo='+bill[2]+'&statusFlag=CHQ_PRNT&ChequeArr='+arr;
		    <% } else {%>
		    url = 'ifms.htm?actionFlag=getHyrUsers&statusFlag=CHQ_WRTN&BillNo='+bill[2]+'&page=savedBillsUpdate&ChequeArr='+arr;
		    <% } %>
//For Written Cheques : Hiral		    
		    if(type == 'Forward')
		    {
		    	url = url+'&sendTo=H';
		    }
		    else if(type=='Peer')
		    {
		    	url = url+'&sendTo=P';		    	
		    }
			window.open(url,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');
		}
		
		function displ()
		{
			document.frmChqPrinted.actionFlag.value="sendToCounter";
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmChqPrinted.elements.length;i++)
		    {
		        if(document.frmChqPrinted.elements[i].type=="checkbox" && document.frmChqPrinted.elements[i].name !='chkSelect')
		        {	
		          if(document.frmChqPrinted.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmChqPrinted.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert('Please Check atleast one checkbox');
		       return false;
		    }
			//insertdt();
//	For Written Cheques : Hiral			
			<%if(writeFlag!=1) { %>
				document.frmChqPrinted.action = 'ifms.htm?actionFlag=sendToCounter&StatusFlag='+'<%=bundle.getString("STATUS.CheqPrinted")%>';
			<% } else {%>
				document.frmChqPrinted.action ='ifms.htm?actionFlag=sendToCounter&StatusFlag='+'<%=bundle.getString("STATUS.CheqWritten")%>';
			<% } %>
//	Ends : For Written Cheques : Hiral			
			document.frmChqPrinted.submit();
		}
		
		function returnBill()
		{
			document.frmChqPrinted.actionFlag.value="sendToCounter";
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmChqPrinted.elements.length;i++)
		    {
		        if(document.frmChqPrinted.elements[i].type=="checkbox" && document.frmChqPrinted.elements[i].name !='chkSelect')
		        {	
		          if(document.frmChqPrinted.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmChqPrinted.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert('Please Check atleast one checkbox');
		       return false;
		    }
			//insertdt();
			if(confirm('Existing cheques will be cancelled.Do you still want to proceed ?'))
			{
				document.frmChqPrinted.action = 'ifms.htm?actionFlag=sendToCounter&ReturnBill=Yes&StatusFlag='+'<%=bundle.getString("STATUS.CheqPrinted")%>';
				document.frmChqPrinted.submit();
			}
		}
		function showDtPic()
  			{
  				if(document.frmChqPrinted.cmbSearch.value=="fromDt")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
  				else
  				{
  					document.getElementById("dtpicker").style.display="none";
  				}
  			}
  			
  			function onSearch(e)
  			{  				
  				if(e.keyCode==13)
  				{
  					var str= document.frmChqPrinted.txtSearch.value;
					if(str=='')
					{					
						alert("Search Text is Empty");
						return false;
					}
					else
					{
  						searching();
  					}
  				}
  			}
  			function showBills(BillNo, BillStatus)
			{
				var newWindow;
		    	var height = screen.height - 100;
		    	var width = screen.width;
		    	var urlstring = "ifms.htm?actionFlag=getBillData&isChq=1&billNo=" + BillNo + "&billStatus=" + BillStatus;
		    	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		    	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
			}
			
			function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,top=10,left=10,width=1010,height=670");
			//window.open(url);
			}
		</script>
	</head>

	<body>
		<c:set var="resultObj" value="${result}" > </c:set>		
		<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>			
		<c:set var="cheqList" value="${resValue.ChequeList}"> </c:set>			
		<hdiits:form name="frmChqPrinted" validate="true"  method="POST">
    	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
		<div id="progressImage" style="display:none"></div>		
		<input type="hidden" value="generateVouchAdvc" name="actionFlag" id="actionFlag" >
		<input type="hidden" name="Cheque" >
		<input type="hidden" name="toPost">
		<input type="hidden" name="toUser">
		<input type="hidden" name="SendTo" value="-1">
		<input type="hidden"  name="displ" value="" />
		
		<table width="90%" align="center">
			<tr>
				<td align="center">
					<table align="center" width="100%">						
						<tr><td><br></td></tr>							
						<tr>
							<td align="center" class="datatableheader">
<!-- 	Changes for Written Cheques : Hiral		 -->							
								<% if(writeFlag!=1) {%>
								<b><fmt:message key="CHQPRINTED.TITLE" bundle="${billprocLabels}"></fmt:message></b>
								<%  } else { %>
								<b><fmt:message key="CHQWRITTEN.TITLE" bundle="${billprocLabels}"></fmt:message></b>
								<% } %>
<!-- Ends : 	Changes for Written Cheques : Hiral		 -->															
							</td>
						</tr>								
						<tr><td><br></td></tr>
						<tr><td><br></td></tr>
					<tr>
						<td>		
							<table align="center" width="90%">
								<tr>
									<td width="47%">
									</td>
									<td width="8%" align="left">
											<fmt:message key="CHQCOMMON.SEARCH" bundle="${billprocLabels}"></fmt:message> :
									</td>
									<td >
										<input type="text" name="txtSearch" onkeypress="javascript:onSearch(event);" size="15" value="<% if(request.getParameter("txtSearch")!=null ){  out.print(request.getParameter("txtSearch")); } %>">
										<div id="dtpicker" style="display:none"><img src="images/CalendarImages/ico-calendar.gif" id='dtpicker'  width="20" onClick=window_open("txtSearch",375,570) ></div>
									</td>
					<td width="*">
							<select name="cmbSearch" onchange="showDtPic()">
							<hdiits:option value="0">-----Select-----</hdiits:option>
							<option value="billCntrlNo" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("billCntrlNo") ) {  out.print("selected"); }  %> >
								<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message>
							</option>						
							<option value="chequeNo" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("chequeNo") ) {  out.print("selected"); }  %>>
								<fmt:message key="CMN.CHEQUE_NO" bundle="${billprocLabels}"></fmt:message>
							</option>
							<option value="partyName" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("partyName") ) {  out.print("selected"); }  %> >
								<fmt:message key="CMN.PARTY_NAME" bundle="${billprocLabels}"></fmt:message>
							</option>
			
							<option value="fromDt" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("fromDt") ) {  out.print("selected"); }  %>>
								<fmt:message key="CHQ.CHEQUE_DATE" bundle="${billprocLabels}"></fmt:message>
							</option>
							
							<option value="tokSrc" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("tokSrc") ) {  out.print("selected"); }  %>>
								<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
							</option>	
							
							<option value="writerName" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("writerName") ) {  out.print("selected"); }  %> >
								<fmt:message key="CHQ.WRITER_NAME" bundle="${billprocLabels}"></fmt:message>
							</option>																		
						</select>

					</td>
					<td width="2%">
						<a href="#" onclick="javascript:searching();"><img src="common/images/search.gif" align="right" height="16" width="16"  /></a>
					</td>
				</tr>
			</table>	

		</td>
	</tr>
					</table>
				</td>
			</tr>
		
			
		   	
		   	<tr>
		   		<td>
		   				<%
							int i=0;
							int j=0;
						%>
			   	<display:table list="${cheqList}" pagesize="25" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
					   	id="VO" excludedParams="ajax" varTotals="totals" partialList="" style="width:100%">
						<display:setProperty name="paging.banner.placement" value="bottom"/>    
						<display:column class="oddcentre" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" headerClass="datatableheader" ><div align=center><input name="chkbox" value="${VO.trnChequeDtls.chequeId}~${VO.trnChequeDtls.groupId}" type="checkbox"/></div> 
						<c:forEach var="billCheq" items="${VO.billChequeRlt}">						
						<input name="billId<%=i%>" type="hidden" value="${billCheq.billNo}">
						<script>
							if('<%=j%>' == 0)
							{
								document.forms[0].chkbox.value = document.forms[0].chkbox.value +"~"+ eval("document.forms[0].billId"+<%=i%>).value;									
							}
							else
							{
								document.forms[0].chkbox['<%=j%>'].value = document.forms[0].chkbox['<%=j%>'].value +"~"+ eval("document.forms[0].billId"+<%=i%>).value;	
							}
						</script>				
							<%
								i++;								
							%>
						</c:forEach>
						</display:column>
		
						<display:column class="oddcentre" titleKey="CMN.BILL_CONTROL_NO" headerClass="datatableheader" sortable="true" style="text-align:center">
							<table>						
							<c:forEach var="billCheq" items="${VO.trnBillRegisterData}">
							<tr><td>		
									<c:choose>
										<c:when test="${billCheq.phyBill==1}">
											<c:set var="URLLink" value="ifms.htm?actionFlag=showBill&sessionFlag=1&isChq=1&BillNo=${billCheq.billNo}"  ></c:set>											
											<a href="javascript:void(0)" onclick="javascript:showBill('${URLLink}')">
												<c:out value="${billCheq.billCntrlNo}"/>
											</a>
										</c:when>
										<c:otherwise>
											<c:set var="URLLink" value="ifms.htm?actionFlag=getBillData&billNo=${billCheq.billNo}&isChq=1&billStatus=BSNT_TO"  ></c:set>
											<a href="javascript:void(0)" onclick="javascript:showBills('${billCheq.billNo}','<%=request.getParameter("statusFlag")%>')">
												<c:out value="${billCheq.billCntrlNo}"/>
											</a>
										</c:otherwise>
									</c:choose>	
							</td></tr>
							
	   						</c:forEach>
	   						</table>
						</display:column>						
						<display:column class="oddcentre" titleKey="CMN.TOKEN_NO" sortable="true" headerClass="datatableheader" style="text-align:center">
							<table>
		   							<c:forEach var="token" items="${VO.tokenNo}">
		   							<tr><td>
		   							<c:out value="${token}"></c:out>
		   							</td></tr>
		   							</c:forEach>
		   					</table>
						</display:column>
						<display:column class="oddcentre" titleKey="CMN.RefNumber" sortable="true" headerClass="datatableheader" style="text-align:center" >
						<table>
	   							<c:forEach var="refNumber" items="${VO.trnBillRegisterData}">
	   							<tr><td>
	   							<c:out value="${refNumber.refNum}"></c:out>
	   							</td></tr>
	   							</c:forEach>
	   					</table>
						</display:column>
<!--  To show/hide 'Cheque No' column based on condition : Hiral -->						
						<% 	if(writeFlag!=1) {%>	
							<display:column property="trnChequeDtls.chequeNo" class="oddcentre" titleKey="CMN.CHEQUE_NO" sortable="true" headerClass="datatableheader" style="text-align:center"/>
						<% } %>
<!-- Ends  To show/hide 'Cheque No' column based on condition : Hiral -->												
						<display:column property="trnChequeDtls.partyName" class="oddcentre" titleKey="CMN.PARTY_NAME" sortable="true" headerClass="datatableheader" style="text-align:center" />
						<display:column property="trnChequeDtls.chequeAmt" class="oddcentre" titleKey="CMN.CHEQUE_AMOUNT" sortable="true" headerClass="datatableheader" style="text-align:center" />
						<display:column property="trnChequeDtls.fromDt" class="oddcentre" format="{0,date,dd/MM/yyyy}" titleKey="CHQ.CHEQUE_DATE" sortable="true" headerClass="datatableheader" style="text-align:center" />
						 <display:column property="writerName" class="oddcentre" titleKey="CHQ.WRITER_NAME" sortable="true" headerClass="datatableheader" />
						
		  				<display:footer media="html">
						</display:footer>
					<%
						j++;
					%>
			  		</display:table> 
		   		</td>
		   	</tr>
		   	<tr>
   				<td>
		   			<center>
					   	<hdiits:button type="button" name="btnReturn" value='<%=buttonBundle.getString("COMMON.RETURN")%>'  onclick="returnBill()"></hdiits:button>&nbsp;
   						<hdiits:button type="button" name="btnSndCntr" value='<%=buttonBundle.getString("COMMON.SENDTOPEER")%>'  onclick="sendToCounter('Peer')"></hdiits:button>&nbsp;
   						<hdiits:button type="button" name="btnFwdCntr" value='<%=buttonBundle.getString("COMMON.FORWARD")%>'  onclick="sendToCounter('Forward')"></hdiits:button>
   					

   					</center>
		   		</td>
		   	</tr>
		</table>
		<script>
	  	if(document.frmChqPrinted.txtSearch.value!=null && document.frmChqPrinted.cmbSearch.value=="fromDt")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
	  	</script>
	  	<%
	  	if(map.get("ChequeCombination") != null )
		{
			String cheqCombi = (String)map.get("ChequeCombination");
			if(cheqCombi!=null)
			{
				%>
					<script>
						alert('<%=cheqCombi%>');
					</script>
				<%
				}
		}
	  	%>
	</hdiits:form>
	
</body>
</html>